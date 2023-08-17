package edu.miami.bte324.hw3.hkim;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public enum MedicalSpecialty {
	
	GENERAL_MEDICINE, PEDIATRICS, ONCOLOGY;
	
	public static MedicalSpecialty getFromString(String specialty) {
		
		return valueOf(specialty.toUpperCase());
		
	}

}
