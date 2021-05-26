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
	/**
	 * Declara un objeto de tipo PersonRepository
	 */
	PersonRepository personRepository;
	/**
	 * Se inicializa el objeto PersonRepository
	 */
	public PersonService() {
		personRepository = new PersonRepository();
	}
	/**
	 * Llama al metodo create de PeronRepository para insertar en la base de datos
	 * @param un objeto de tipo Person
	 * @return true o false segun retorne el metodo PeronRepository.create
	 */
	public boolean create(Person person) {
		return personRepository.create(person);
	}
	/**
	 * Llama al metodo getAll de PeronRepository para solicitar todas las personas en la base de datos
	 * @return una lista de tipo Person
	 */

	public List<Person> getAll() {
		try {
			return personRepository.getAll();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
	}
	/**
	 * Llama al metodo update de PeronRepository para actualizar los datos de una persona en la base de datos
	 * @param un objeto de tipo Person
	 * @return true o false segun retorne el metodo PeronRepository.update
	 */
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
	/**
	 * Llama al metodo delete de PeronRepository para eliminar los datos de una persona en la base de datos
	 * @param documentType de tipo String, documentNumber de tipo String
	 * @return true o false segun retorne el metodo PeronRepository.delete
	 */
	public boolean delete(String documentType, String documentNumber) {
		boolean isDeleted = false;
		try {
			isDeleted = personRepository.delete(documentType, documentNumber);
		} catch (Censo21Exceptions e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return isDeleted;
	}
	/**
	 * Llama al metodo get de PeronRepository para soliciar una persona de la base de datos
	 * @param documentType de tipo String, documentNumber de tipo String
	 * @return un objeto de tipo Person
	 */
	public Person getOne(String documentType, String documentNumber) {
		Person person = null;
		try {
			person = personRepository.get(documentType, documentNumber);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return person;
	}
	/**
	 * Llama al metodo getAllbyLastName de PeronRepository 
	 * y al metodo generateReportFile para generar el archivo personas_por_orden_de_apellido.txt
	 * @param reportType de tipo String
	 * @return true o false segun retorne el metodo getAllByLastName
	 */
	public boolean ReportFile(String reportType) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllByLastName();
		generated = generateReportFile(persons, reportType);
		return generated;
	}
	/**
	 * Llama al metodo getAllAdultList de PeronRepository 
	 * y al metodo generateReportFile para generar el archivo personas_mayores_de_18.txt
	 * @param reportType de tipo String
	 * @return true o false segun retorne el metodo getAllAdultList
	 */
	public boolean reportAdultList(String reportType) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllAdultList();
		generated = generateReportFile(persons, reportType);
		return generated;
	}
	/**
	 * Llama al metodo getAllPovertyLine de PeronRepository 
	 * y al metodo generateReportFile para generar el archivo personas_con_ingresos_por_debajo_del_5000.txt
	 * @param reportType de tipo String
	 * @return true o false segun retorne el metodo getAllPovertyLine
	 */
	public boolean reportPovertyLine(String reportType) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllPovertyLine();
		generated = generateReportFile(persons, reportType);
		return generated;
	}
	/**
	 * Llama al metodo getCountByGener de PeronRepository 
	 * para generar el archivo personas_con_ingresos_por_debajo_del_5000.txt
	 * @return true o false segun retorne el metodo getCountByGener
	 */
	public boolean reportCountByGender() {
		boolean generated = false;
		generated = generateCountByGender();
		return generated;
	}
	/**
	 * Crea un nuevo archivo
	 * @param nombre del archivo de tipo String
	 * @return referencia del archivo a crear de tipo File
	 */
	public File fileExists(String fileName) {

		File file = new File("C:/reportes_censo/", fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return file;
	}
	/**
	 * Crea un nuevo archivo conteo_de_personas_por_sexo.txt
	 * @return true o flase si es creado
	 */
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
	/**
	 * Crea un nuevo archivo de tres opciones segun parametro
	 * "reportForLastName" --> "personas_por_orden_de_apellido.txt"
	 * "reportAdultList" --> "personas_mayores_de_18.txt"
	 * "reportPovertyLine" --> "personas_con_ingresos_por_debajo_del_5000.txt"
	 * @param  "reportForLastName", "reportAdultList" ó  "reportPovertyLine" tipo String
	 * @return true o flase si es creado
	 */
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
