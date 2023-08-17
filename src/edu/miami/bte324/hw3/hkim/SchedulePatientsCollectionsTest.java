package edu.miami.bte324.hw3.hkim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public class SchedulePatientsCollectionsTest extends Report {
	
	private static String[][] patientArray = { 
			
			{"John Lennon", "123-45-6789", "05/01/1951"}, 
			{"Paul McCartney", "333-22-1123", "06/23/1964"},
			{"George Harrison", "567-39-9282", "09/05/1955"},
			{"Ringo Starr", "888-88-8888", "02/28/2001"}
			
	};
	
	private static String[][] doctorArray = {
			
			{"John Smith", "445-25-3382", "06/03/1941", "GENERAL_MEDICINE"},
			{"Jane Doe", "582-10-9983", "10/10/1950", "PEDIATRICS"},
			{"Mary Jones", "180-38-0038", "12/25/1965", "ONCOLOGY"},
			{"Beth Garcia", "343-34-9875", "07/19/1964", "GENERAL_MEDICINE"}
			
	};
	
	public static void main(String[] args) {
		
		SchedulePatientsCollectionsTest report = new SchedulePatientsCollectionsTest();
		report.run();
		
	}
	
	public void run() {
		
		ArrayList<Patient> patientList = new ArrayList<Patient>(); 
		//Doctor collection defined as a DoctorImpl collection in order to use Collections.sort() function
		ArrayList<DoctorImpl> doctorList = new ArrayList<DoctorImpl>(); 
		ArrayList<Visit<Patient,Doctor>> visitList = new ArrayList <Visit<Patient,Doctor>>();
		
		loadPatients(patientList, patientArray);
		loadDoctors(doctorList, doctorArray);
		loadVisits(patientList, doctorList, visitList); 
		
		Collections.sort(doctorList); //used to sort doctors
		iterateSortedDoctors(doctorList); //used to iterate/print sorted doctors list
		
		printDoctorVisits(visitList, doctorList);
		printPatientVisits(visitList, patientList);
		
		Map<Integer, Patient> patientIDMap = new HashMap<Integer, Patient>();
		Map<Integer, Doctor> doctorIDMap = new HashMap<Integer, Doctor>();
		
		loadPatientMap(patientIDMap, patientList);
		loadDoctorMap(doctorIDMap, doctorList);
		
		Collections.sort(visitList, new VisitComparator<Patient,Doctor>()); //used to sort visits
		printUpcomingVisits(visitList, patientIDMap, doctorIDMap); 
		
	}

}
