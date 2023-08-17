package edu.miami.bte324.hw3.hkim;

import java.util.Date;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public interface Patient {

	public Integer getPatientID();
	public String getLastName();
	public String getFirstName();
	public String getSSN();
	public Date getBirthDate();
	public Integer getAge();
	
}
