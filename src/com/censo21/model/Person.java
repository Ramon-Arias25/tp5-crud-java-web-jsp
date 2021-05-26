package com.censo21.model;

import java.io.Serializable;
import java.sql.Date;
/**
 * @author ramon.arias
 * date: 23/05/2021
 * current version: 1
 */
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	private String documentType;
	private String documentNumber;
	private String firstName;
	private String lastName;
	private Date dateBirth;
	private String sex;
	private String address;
	private String phone;
	private String occupation;
	private float monthlyIncome;

	public Person(String documentType, String documentNumber, String firstName, String lastName, Date dateBirth,
			String sex, String address, String phone, String occupation, float monthlyIncome) {
		super();
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.sex = sex;
		this.address = address;
		this.phone = phone;
		this.occupation = occupation;
		this.monthlyIncome = monthlyIncome;
	}

	public Person() {
		
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public float getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(float monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	@Override
	public String toString() {
		return "person [documentType=" + documentType + ", documentNumber=" + documentNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", dateBirth=" + dateBirth + ", sex=" + sex + ", address="
				+ address + ", phone=" + phone + ", occupation=" + occupation + ", monthlyIncome=" + monthlyIncome
				+ "]";
	}
	
	public String toPrint() {
		return "Tipo de documento:" + documentType + ", Numero de Documento:" + documentNumber + ", Nombre:"
				+ firstName + ", Apellido:" + lastName + ", Fecha de Nacimiento:" + dateBirth + ", Sexo:" + sex + ", Direccion:"
				+ address + ", Telefono:" + phone + ", Ocupacion:" + occupation + ", Ingresos Mensuales:" + monthlyIncome;
	}
}
