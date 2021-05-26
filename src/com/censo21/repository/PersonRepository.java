package com.censo21.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.censo21.exceptions.Censo21Exceptions;
import com.censo21.model.Person;

public class PersonRepository {

	private static Connection conn;

	public PersonRepository() {
		conn = Connector.getConnection();
	}

	public boolean create(Person person) {

		boolean create = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO person "
					+ "(document_type , document_number , first_name ,  last_name , date_birth , "
					+ " sex , address , phone , occupation , monthly_income )"
					+ "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getDocumentType());
			pstmt.setString(2, person.getDocumentNumber());
			pstmt.setString(3, person.getFirstName());
			pstmt.setString(4, person.getLastName());
			pstmt.setDate(5, person.getDateBirth());
			pstmt.setString(6, person.getSex());
			pstmt.setString(7,person.getAddress());
			pstmt.setString(8, person.getPhone());
			pstmt.setString(9, person.getOccupation());
			pstmt.setFloat(10, person.getMonthlyIncome());
			pstmt.execute();
			create = true;
		} catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
			create = false;
		} 
		return create;
	}

	public List<Person> getAll() {
		ArrayList<Person> persons = new ArrayList<Person>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

			String sql = "SELECT document_type , document_number ,  first_name , last_name , date_birth , "
						+ "sex , address , phone , occupation , monthly_income "
						+ "FROM  person;";
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Person person = new Person( rs.getString("document_type"), 
												rs.getString("document_number"),
												rs.getString("first_name"),
												rs.getString("last_name"), 
												rs.getDate("date_birth"), 
												rs.getString("sex"), 
												rs.getString("address"), 
												rs.getString("phone"), 
												rs.getString("occupation"), 
												rs.getFloat("monthly_income"));
					persons.add(person);
				
				}
			} catch (SQLException e) {
	            System.out.println("ERROR: " + e.getMessage());
			} finally {
				try {
					 
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		return persons;
	}

	public Person get(String documentType, String documentNumber) {
		Person person = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT document_type , document_number , first_name , last_name , date_birth , "
						+ " sex , address , phone , occupation , monthly_income "
						+ "FROM  person "
						+ "WHERE document_type = ? and document_number = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentType);
			pstmt.setString(2, documentNumber);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				person = new Person(rs.getString("document_type"),
									rs.getString("document_number"), 
									rs.getString("first_name"), 
									rs.getString("last_name"), 
									rs.getDate("date_birth"),
									rs.getString("sex"), 
									rs.getString("address"), 
									rs.getString("phone"), 
									rs.getString("occupation"), 
									rs.getFloat("monthly_income"));
			}
		} catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
		} finally {
			try {
				 
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		
		}
		
		return person;
	}
	
	public boolean delete(String DocumentType , String DocumentNumber) throws Censo21Exceptions{
		PreparedStatement pmtst = null;
		boolean isDeleted = false;
			try {
				String sql = "DELETE FROM  person "
							+"WHERE document_type = ?"
							+"and document_number = ? ;";
				pmtst = conn.prepareStatement(sql);
				pmtst.setString(1, DocumentType);
				pmtst.setString(2, DocumentNumber);
				pmtst.execute();
				isDeleted = true;
			} catch (Exception e) {
	            System.out.println("ERROR: " + e.getMessage());
			}
			return isDeleted;
	}
	
	public boolean update(Person person) throws Censo21Exceptions{
		PreparedStatement pstmt = null;
		boolean updated = false;
		try {
			String sql = "UPDATE person SET "
					+ " document_type = ? ,"
					+ " document_number = ? ,"
					+ " first_name = ? , "
					+ " last_name = ? , "
					+ " date_birth = ? , "
					+ " sex = ? , "
					+ " address = ? , "
					+ " phone = ? , "
					+ " occupation = ? , "
					+ " monthly_income = ? "
					+ " WHERE document_type = ? "
					+ " AND  document_number = ? ;";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getDocumentType());
			pstmt.setString(2, person.getDocumentNumber());
			pstmt.setString(3, person.getFirstName());
			pstmt.setString(4, person.getLastName());
			pstmt.setDate(5, person.getDateBirth());
			pstmt.setString(6, person.getSex());
			pstmt.setString(7,person.getAddress());
			pstmt.setString(8, person.getPhone());
			pstmt.setString(9, person.getOccupation());
			pstmt.setFloat(10, person.getMonthlyIncome());
			pstmt.setString(11, person.getDocumentType());
			pstmt.setString(12, person.getDocumentNumber());
			pstmt.execute();
			updated = true;
		} catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
			updated = false;
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		}
		return updated;
	}

	public List<Person> getAllByLastName() {
		ArrayList<Person> persons = new ArrayList<Person>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT document_type, "
				+ "    document_number, "
				+ "    first_name, "
				+ "    last_name, "
				+ "    date_birth, "
				+ "    sex, "
				+ "    address, "
				+ "    phone, "
				+ "    occupation, "
				+ "    monthly_income "
				+ "FROM person "
				+ "order by last_name asc";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Person person = new Person( rs.getString("document_type"), 
												rs.getString("document_number"),
												rs.getString("first_name"),
												rs.getString("last_name"), 
												rs.getDate("date_birth"), 
												rs.getString("sex"), 
												rs.getString("address"), 
												rs.getString("phone"), 
												rs.getString("occupation"), 
												rs.getFloat("monthly_income"));
					persons.add(person);
				}
			} catch (SQLException e) {
	            System.out.println("ERROR: " + e.getMessage());
			} finally {
				try {
					 
					pstmt.close();
				} catch (SQLException e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
				
			}
		return persons;
	}

	public List<Person> getAllAdultList() {
		ArrayList<Person> persons = new ArrayList<Person>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT document_type, "
				+ "    document_number, "
				+ "    first_name, "
				+ "    last_name, "
				+ "    date_birth, "
				+ "    YEAR(CURDATE())-YEAR(`date_birth`) + IF(DATE_FORMAT(CURDATE(),'%m-%d') > DATE_FORMAT(`date_birth`,'%m-%d'), 0 , -1 ) as age, "
				+ "    sex, "
				+ "    address, "
				+ "    phone, "
				+ "    occupation,"
				+ "    monthly_income "
				+ "from person "
				+ "where YEAR(CURDATE())-YEAR(`date_birth`) + IF(DATE_FORMAT(CURDATE(),'%m-%d') > DATE_FORMAT(`date_birth`,'%m-%d'), 0 , -1 ) >= 18 "
				+ "order by document_type, document_number asc;";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Person person = new Person( rs.getString("document_type"), 
												rs.getString("document_number"),
												rs.getString("first_name"),
												rs.getString("last_name"), 
												rs.getDate("date_birth"), 
												rs.getString("sex"), 
												rs.getString("address"), 
												rs.getString("phone"), 
												rs.getString("occupation"), 
												rs.getFloat("monthly_income"));
					persons.add(person);
				}
			} catch (SQLException e) {
	            System.out.println("ERROR: " + e.getMessage());
			} finally {
				try {
					 
					pstmt.close();
				} catch (SQLException e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
				
			}
		return persons;	
	}
	
	public List<Person> getAllPovertyLine() {
		ArrayList<Person> persons = new ArrayList<Person>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT document_type, "
				+ "    document_number, "
				+ "    first_name, "
				+ "    last_name, "
				+ "    date_birth, "
				+ "    sex, "
				+ "    address, "
				+ "    phone, "
				+ "    occupation,"
				+ "    monthly_income "
				+ "from person "
				+ "where monthly_income < 5000 ;";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Person person = new Person( rs.getString("document_type"), 
												rs.getString("document_number"),
												rs.getString("first_name"),
												rs.getString("last_name"), 
												rs.getDate("date_birth"), 
												rs.getString("sex"), 
												rs.getString("address"), 
												rs.getString("phone"), 
												rs.getString("occupation"), 
												rs.getFloat("monthly_income"));
					persons.add(person);
				}
			} catch (SQLException e) {
	            System.out.println("ERROR: " + e.getMessage());
			} finally {
				try {
					pstmt.close();
				} catch (SQLException e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
				
			}
		return persons;
	}
	private ArrayList<String> getAllCountByGender() {
		ArrayList<String> aux = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT sex, COUNT(*) as total FROM person GROUP BY sex; ";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					String s = new String( "En total de: "+rs.getString("sex") + "es de:" +	rs.getString("total"));
					aux.add(s);
				}
			} catch (SQLException e) {
	            System.out.println("ERROR: " + e.getMessage());
			} finally {
				try {
					 
					pstmt.close();
				} catch (SQLException e) {
		            System.out.println("ERROR: " + e.getMessage());
				}
				
			}
		return aux;
	}

	public  File fileExists(String fileName) {

	        File file = new File("C:/reportes/", fileName);
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
	            ArrayList<String> string = this.getAllCountByGender();
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
	
	public boolean generateReportFile(List<Person> persons , String reportName) {
		Map<String, String> fileName = new HashMap<>();
		fileName.put("reportForLastName","personas_por_orden_de_apellido.txt");
		fileName.put("reportAdultList","personas_mayores_de_18.txt");
		fileName.put("reportPovertyLine","personas_con_ingresos_por_debajo_del_5000.txt");
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