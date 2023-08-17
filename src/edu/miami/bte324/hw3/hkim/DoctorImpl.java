package edu.miami.bte324.hw3.hkim;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public class DoctorImpl extends PatientImpl implements Doctor {


	private static Integer doctorIDGenerator = 1;
	
	private Integer doctorID;
	private MedicalSpecialty doctorSpecialty;

	public DoctorImpl() {
		
		super();
		doctorID = null;
		doctorSpecialty = null;
		
	}
	
	public DoctorImpl(String name, String SSN, Date bDate, MedicalSpecialty specialty) {
			
		super(name, SSN, bDate);
		this.doctorID = doctorIDGenerator;
		this.doctorSpecialty = specialty;
		
		++doctorIDGenerator;
		
	}
	
	public Integer getDoctorID() {
		return doctorID;
	}

	public MedicalSpecialty getSpecialty() {
		return doctorSpecialty;
	}
	
	public String toString() {
		
		Format date = new SimpleDateFormat ("yyyy/MM/dd");
		String formattedDate = date.format(birthDate);
		return String.format("%d\t %-10s %-10s %-20s %-15s %-15s %d", doctorID, firstName, lastName, doctorSpecialty, SSN, formattedDate, age);
		
	}
	
	public boolean equals(Doctor other) {
		
		if(this == other) return true;
		
		if(other == null || (this.getClass() != other.getClass())) {
			return false;
		}
		
		Doctor d = other;
		
		return (this.doctorID == d.getDoctorID()) && 
				(this.firstName != null && firstName.equals(d.getFirstName())) &&
				(this.lastName != null && firstName.equals(d.getLastName()));

	}
	
	public int hashCode() {
		
		int result = 0;
		
		result = 31 * result + doctorID;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
		result = 31 * result + (SSN != null ? SSN.hashCode() : 0);
		
		return result;
		
	}
	
}
