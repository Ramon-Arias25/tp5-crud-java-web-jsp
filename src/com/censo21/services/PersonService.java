package com.censo21.services;

import java.util.List;

import com.censo21.exceptions.Censo21Exceptions;
import com.censo21.model.Person;
import com.censo21.repository.PersonRepository;

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

	public boolean delete(String DocumentType , String DocumentNumber){
		boolean isDeleted = false;
		try {
			isDeleted = personRepository.delete(DocumentType , DocumentNumber);
		} catch (Censo21Exceptions e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return isDeleted;
	}

	public Person getOne(String DocumentType , String DocumentNumber) {
		Person person = null;
			try {
				person = personRepository.get(DocumentType , DocumentNumber);
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		return person;
	}
	public boolean ReportFile (String file) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllByLastName();
		generated  = personRepository.generateReportFile(persons, file);
		return generated;
	}

	public boolean reportAdultList(String file) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllAdultList();
		generated  = personRepository.generateReportFile(persons, file);
		return generated;
	}

	public boolean reportPovertyLine(String file) {
		boolean generated = false;
		List<Person> persons = personRepository.getAllPovertyLine();
		generated  = personRepository.generateReportFile(persons, file);
		return generated;
	}

	public boolean reportCountByGender() {
		boolean generated = false;
		generated = personRepository.generateCountByGender();
		return generated;
	}
}
