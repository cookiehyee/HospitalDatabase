package edu.miami.bte324.hw3.hkim;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public class PatientImpl implements Patient, Comparable<PatientImpl> {
	
	private static Integer patientIDGenerator = 1;
	private Integer patientID;
	
	protected String lastName;
	protected String firstName;
	protected String SSN;
	protected Date birthDate;
	protected Integer age;
	
	public PatientImpl() {
		
		patientID = null;
		lastName = null;
		firstName = null;
		SSN = null;
		birthDate = null;
		age = null;
		
	}
	
	public PatientImpl(String name, String ssn, Date bDate) {
		
		this.patientID = patientIDGenerator;
		this.lastName = lastName(name);
		this.firstName = firstName(name);
		this.SSN = ssn;
		this.birthDate = bDate;
		this.age = calculateAge(bDate);
		
		++patientIDGenerator;
		
	}
	
	public Integer getPatientID() {
		return patientID;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSSN() {
		return SSN;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public Integer getAge() {
		return age;
	}
	
	private static String firstName(String name) {
		String[] names = name.split(" ");
		return names[0];
	}
	
	private static String lastName(String name) {
		String[] names = name.split(" ");
		return names[1];
	}
	
	
    static int calculateAge(Date bDate) {
		
		int age;
		Date currentDate = new Date();
		
		Calendar patientBirthDate = Calendar.getInstance();
		patientBirthDate.setTime(bDate);
		
		Calendar todayDate = Calendar.getInstance();
		todayDate.setTime(currentDate);
		
		age = todayDate.get(Calendar.YEAR) - patientBirthDate.get(Calendar.YEAR);
		
		return age;
		
    }

	public String toString() {
		
		Format date = new SimpleDateFormat ("yyyy/MM/dd");
		String formattedDate = date.format(birthDate);
	
		return String.format("%d\t %-10s %-10s %-15s %-15s %d", patientID, firstName, lastName, SSN, formattedDate, age);
		
	}
	
	public boolean equals(Object otherPatient) {
		
		if(this == otherPatient) return true;
		
		if(otherPatient == null || (this.getClass() != otherPatient.getClass()))
			return false;

		if(!(otherPatient instanceof PatientImpl))
			return false;
		
		PatientImpl p = (PatientImpl) otherPatient;
		
		return (this.patientID == p.getPatientID()) && 
				(this.firstName != null && firstName.equals(p.getFirstName())) &&
				(this.lastName != null && firstName.equals(p.getLastName())) &&
				(this.birthDate != null && birthDate.equals(p.getBirthDate())) &&
				(this.SSN != null && SSN.equals(p.getSSN()));
		
	}
	
	public int hashCode() {
		
		int result = 1;
		
		result = 31 * result + ((lastName != null) ? 0: lastName.hashCode());
		result = 31 * result + ((firstName != null) ? 0 : firstName.hashCode());
		result = 31 * result + ((birthDate != null) ? 0 : birthDate.hashCode());
		result = 31 * result + ((SSN != null) ? 0 : SSN.hashCode());
		result = 31 * result + patientID;
		
		return result;
		
	}
	
	@Override
	public int compareTo(PatientImpl otherPatient) {
		
		int i = this.lastName.compareTo(otherPatient.getLastName());
		if(i != 0) 
			return i;
		
		i = this.firstName.compareTo(otherPatient.getFirstName());
		if(i != 0) 
			return i;
		
		i = this.birthDate.compareTo(otherPatient.getBirthDate());
		if(i != 0) 
			return i;
		
		i = this.SSN.compareTo(otherPatient.getSSN());
		if(i != 0) 
			return i;

		return this.age - otherPatient.getAge();
		
	}
	
}
