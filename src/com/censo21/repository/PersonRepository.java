package com.censo21.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.censo21.exceptions.Censo21Exceptions;
import com.censo21.model.Person;
/**
 * @author ramon.arias
 * date: 23/05/2021
 * current version: 1
 */
public class PersonRepository {

	private static Connection conn;
	/**
	 * En el contructor llama a la clase Connector para crear una conexion a la bdd
	 */
	public PersonRepository() {
		conn = Connector.getConnection();
	}
	/**
	 * Insertar una persona a la base de datos
	 * @param un objeto de tipo Person
	 * @return true o false si inserta en la base de datos
	 */
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
		} finally {
			if (pstmt != null){					
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println("ERROR: " + e.getMessage());
				}
			}
		}
		return create;
	}
	/**
	 * Solicitar todas las personas en la base de datos
	 * @return una lista de tipo Person
	 */
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
				if (pstmt != null){					
					try {
						pstmt.close();
					} catch (SQLException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
			}
		return persons;
	}
	/**
	 * Solicita una persona de la base de datos
	 * @param documentType de tipo String y documentNumber de tipo String
	 * @return un objeto de tipo Person
	 */
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
			} finally {
				if (pstmt != null){					
					try {
						pstmt.close();
					} catch (SQLException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
			}
		
		}
		
		return person;
	}
	/**
	 * Elimina una persona de la base de datos
	 * @param documentType de tipo String y documentNumber de tipo String
	 * @return true o false si fue eliminada la persona
	 */
	public boolean delete(String DocumentType , String DocumentNumber) throws Censo21Exceptions{
		PreparedStatement pstmt = null;
		boolean isDeleted = false;
			try {
				String sql = "DELETE FROM  person "
							+"WHERE document_type = ?"
							+"and document_number = ? ;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, DocumentType);
				pstmt.setString(2, DocumentNumber);
				pstmt.execute();
				isDeleted = true;
			} catch (Exception e) {
	            System.out.println("ERROR: " + e.getMessage());
			}finally {
				if (pstmt != null){					
					try {
						pstmt.close();
					} catch (SQLException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
			}
			return isDeleted;
	}
	/**
	 * Actualiza los datos de una persona de la base de datos
	 * @param Un objeto de tipo Person
	 * @return true o false si se actualizaron los datos
	 */
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
			if (pstmt != null){					
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.out.println("ERROR: " + e.getMessage());
				}
			}
		}
		return updated;
	}
	/**
	 * Solicita Lista de personas ordenadas por apellido en orden ascendente
	 * @return una lista de tipo Person ordenadas por apellido en orden ascendete 
	 */
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
				if (pstmt != null){					
					try {
						pstmt.close();
					} catch (SQLException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
			}
		return persons;
	}
	/**
	 * Solicita Lista de personas mayores de 18 años
	 * @return una lista de tipo Person mayores de 18 años
	 */
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
				if (pstmt != null){					
					try {
						pstmt.close();
					} catch (SQLException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
			}
		return persons;	
	}
	/**
	 * Solicita Lista de personas con ingresos menores a 5000
	 * @return una lista de tipo Person con ingresos menores a 5000
	 */
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
				if (pstmt != null){					
					try {
						pstmt.close();
					} catch (SQLException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
			}
		return persons;
	}
	/**
	 * Solicita Lista de los totales de las personas por agrupados por sexo
	 * @return una lista de tipo String que contiene los totales de las personas por agrupados por sexo
	 */
	public ArrayList<String> getAllCountByGender() {
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
				if (pstmt != null){					
					try {
						pstmt.close();
					} catch (SQLException e) {
						System.out.println("ERROR: " + e.getMessage());
					}
				}
			}
		return aux;
	}
}