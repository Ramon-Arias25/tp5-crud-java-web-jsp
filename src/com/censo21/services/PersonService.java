package com.censo21.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.censo21.exceptions.Censo21Exceptions;
import com.censo21.model.Person;
import com.censo21.repository.PersonRepository;
/**
 * @author ramon.arias
 * date: 24/05/2021
 * current version: 1
 */
public class PersonService {

	PersonRepository personRepository;

	public PersonService() {
		personRepository = new PersonRepository();
	}

	public boolean create(Person person) {
		return personRepository.create(person);
	}

	public List<Person> getAll() {
		try {
			return personRepository.getAll();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
	}

	public boolean update(Person person) {
		boolean updated = false;
		try {
			updated = personRepository.update(person);
		} catch (Censo21Exceptions e) {
			System.out.println("ERROR: " + e.getMessage());
			updated = false;
		}
		return updated;
	}

	public boolean delete(String DocumentType, String DocumentNumber) {
		boolean isDeleted = false;
		try {
			isDeleted = personRepository.delete(DocumentType, DocumentNumber);
		} catch (Censo21Exceptions e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return isDeleted;
	}

	public Person getOne(String DocumentType, String DocumentNumber) {
		Person person = null;
		try {
			person = personRepository.get(DocumentType, DocumentNumber);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return person;
	}

	public boolean ReportFile(String file) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllByLastName();
		generated = generateReportFile(persons, file);
		return generated;
	}

	public boolean reportAdultList(String file) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllAdultList();
		generated = generateReportFile(persons, file);
		return generated;
	}

	public boolean reportPovertyLine(String file) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllPovertyLine();
		generated = generateReportFile(persons, file);
		return generated;
	}

	public boolean reportCountByGender() {
		boolean generated = false;
		generated = generateCountByGender();
		return generated;
	}

	public File fileExists(String fileName) {

		File file = new File("C:/reportes_censo/", fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return file;
	}

	public boolean generateCountByGender() {
		boolean generated = false;
		try {
			File file = fileExists("conteo_de_personas_por_sexo.txt");
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter BufferedWriter = new BufferedWriter(fileWriter);
			ArrayList<String> string = personRepository.getAllCountByGender();
			for (String string1 : string) {
				BufferedWriter.write(string1);
				BufferedWriter.newLine();
			}
			BufferedWriter.write("Fin del Reporte.");
			BufferedWriter.close();
			generated = true;
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return generated;
	}

	public boolean generateReportFile(List<Person> persons, String reportName) {
		Map<String, String> fileName = new HashMap<>();
		fileName.put("reportForLastName", "personas_por_orden_de_apellido.txt");
		fileName.put("reportAdultList", "personas_mayores_de_18.txt");
		fileName.put("reportPovertyLine", "personas_con_ingresos_por_debajo_del_5000.txt");
		boolean generated = false;
		try {
			File file = fileExists(fileName.get(reportName));
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter BufferedWriter = new BufferedWriter(fileWriter);
			for (Person person : persons) {
				BufferedWriter.write(person.toPrint());
				BufferedWriter.newLine();
			}
			BufferedWriter.write("Fin del Reporte.");
			BufferedWriter.close();
			generated = true;
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return generated;
	}
}
