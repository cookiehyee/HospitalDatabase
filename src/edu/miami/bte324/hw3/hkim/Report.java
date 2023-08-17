package edu.miami.bte324.hw3.hkim;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public abstract class Report {
	
	public static void loadPatients(ArrayList<Patient> pList, String[][] pArray) {
		
		for (int i = 0; i < pArray.length; i++) {
			
			Patient newPatient = new PatientImpl(pArray[i][0], pArray[i][1], 
					convertToDate(pArray[i][2]));
		
			pList.add(newPatient);
				
		}
		
	}
	
	public static void loadDoctors(ArrayList<DoctorImpl> dList, String[][] dArray) {
		
		for (int i = 0; i < dArray.length; i++) {
				
				Doctor newDoctor = new DoctorImpl(dArray[i][0], dArray[i][1], 
						convertToDate(dArray[i][2]),
						MedicalSpecialty.getFromString(dArray[i][3]));
				
				dList.add((DoctorImpl) newDoctor); 
			
		}
		
	}
	
	public static void loadVisits(ArrayList<Patient> pList, ArrayList<DoctorImpl> dList, ArrayList<Visit<Patient,Doctor>> vList) {
		
		Visit<Patient, Doctor> visit1 = new VisitImpl<Patient, Doctor> (pList.get(0), dList.get(0), convertToDate("11/11/2020")); 
		vList.add(visit1);
		
		Visit<Patient, Doctor> visit2 = new VisitImpl<Patient, Doctor> (pList.get(0), dList.get(0), convertToDate("11/11/2020"));
		vList.add(visit2);
		
		Visit<Patient, Doctor> visit3 = new VisitImpl<Patient, Doctor> (pList.get(3), dList.get(1), convertToDate("11/14/2020")); 
		vList.add(visit3);
		
		Patient janeDoe = new PatientImpl("Jane Doe", "582-10-9983", convertToDate("10/10/1950"));
		pList.add(janeDoe);
		Visit<Patient, Doctor> visit4 = new VisitImpl<Patient, Doctor> (janeDoe, dList.get(0), convertToDate("11/11/2020"));
		vList.add(visit4);
		
		Visit<Patient, Doctor> visit5 = new VisitImpl<Patient, Doctor> (pList.get(1), dList.get(2), convertToDate("11/09/2020")); 
		vList.add(visit5);
		
		Visit<Patient, Doctor> visit6 = new VisitImpl<Patient, Doctor> (pList.get(2), dList.get(3), convertToDate("11/09/2020"));
		vList.add(visit6);
		
		Patient bethGarcia = new PatientImpl("Beth Garcia", "343-34-9875", convertToDate("07/19/1964"));
		pList.add(bethGarcia);
		Visit<Patient, Doctor> visit7 = new VisitImpl<Patient, Doctor> (bethGarcia, dList.get(0), convertToDate("11/13/2020")); 
		vList.add(visit7);
		
		Visit<Patient, Doctor> visit8 = new VisitImpl<Patient, Doctor> (pList.get(2), dList.get(1), convertToDate("11/13/2020"));
		vList.add(visit8);
		
	}
	
	public static void iterateSortedDoctors(ArrayList<DoctorImpl> dList) {
		
		System.out.println("\nSORTED DOCTORS LIST:");
		Iterator<DoctorImpl> sortedDoctorIterator = dList.iterator();
		
		while(sortedDoctorIterator.hasNext()) {
			System.out.println(sortedDoctorIterator.next().toString());
		}	
		
	}
	
	public static void printDoctorVisits(ArrayList<Visit<Patient,Doctor>> vList, ArrayList<DoctorImpl> dList) {
		
		System.out.println("\n--------------------------------------------------");
		
		for(int i = 0; i < dList.size(); i++) {
			
			if(dList.get(i).getSpecialty() == MedicalSpecialty.GENERAL_MEDICINE) { //Used to print only doctors with GENERAL_MEDICINE
				
				System.out.println("\nDoctor: \t\t" + dList.get(i).getFirstName() + " " + dList.get(i).getLastName());
				System.out.println("Specialty: \t\t" + dList.get(i).getSpecialty());
				System.out.println("Upcoming Visits: \t");
				
				for(int j = 0; j < vList.size(); j++) { //Nested loop to print all upcoming doctor visits
					
					int visitLoc = j;
				
					if (doctorHasVisits(vList, visitLoc, dList.get(i).getLastName())) { //Used to check if doctor has upcoming visit

						Format date = new SimpleDateFormat ("yyyy/MM/dd");
						String formattedDate = date.format(vList.get(j).getVisitDate());
					
						System.out.println("\n\tVisit Date: \t\t" + formattedDate);
						System.out.println("\tFirst Name: \t\t" + vList.get(j).getVisitor().getFirstName());
						System.out.println("\tLast Name: \t\t" + vList.get(j).getVisitor().getLastName());
						System.out.println("\tSSN: \t\t\t" + vList.get(j).getVisitor().getSSN());
						System.out.println("\tAge: \t\t\t" + vList.get(j).getVisitor().getAge());
				
					}
				
					else
						continue; //Used to continue the loop if doctor does not have upcoming visits
				
				}
			
			}
			
			else
				continue;
			
			System.out.println();
		
		}
		
	}
	
	public static void printPatientVisits(ArrayList<Visit<Patient,Doctor>> vList, ArrayList<Patient> pList) {
		
		System.out.println("\n--------------------------------------------------");
		
		for(int i = 0; i < pList.size(); i++) {
			
			String patientName = pList.get(i).getLastName() + ", " + pList.get(i).getFirstName();
				
			System.out.println("\nPatient Name: \t\t" + patientName);
			System.out.println("SSN: \t\t\t" + pList.get(i).getSSN());
			System.out.println("Age: \t\t\t" + pList.get(i).getAge());
			System.out.println("Upcoming Visits: \t");
			
			for(int j = 0; j < vList.size(); j++) { //Nested loop to print all upcoming doctor visits
				
				int visitLoc = j;
				
				if (patientHasVisits(vList, visitLoc, pList.get(i).getLastName())) { //Used to check if patient has upcoming visit
					
					String doctorName = vList.get(j).getHost().getFirstName() + " " + vList.get(j).getHost().getLastName();
					
					Format date = new SimpleDateFormat ("dd MMMM yyyy");
					String formattedDate = date.format(vList.get(visitLoc).getVisitDate());
				
					System.out.println("\n\tVisit Date: \t\t" + formattedDate);
					System.out.println("\tDoctor: \t\t" + doctorName);
					System.out.println("\tSpecialty: \t\t" + vList.get(j).getHost().getSpecialty());
					
				}
				
				else
					continue; //Used to continue the loop if patient does not have upcoming visits
			
			}
			
			System.out.println();
		
		}
		
	}
	
	public static void loadPatientMap(Map<Integer, Patient> pMap, ArrayList<Patient> pList) {
		
		for(Patient p: pList) //for every patient object in the patient list
			pMap.put(p.getPatientID(), p);
		
	}
	
	public static void loadDoctorMap(Map<Integer, Doctor> dMap, ArrayList<DoctorImpl> dList) {
		
		for(Doctor d: dList) //for every doctor object in the patient list
			dMap.put(d.getDoctorID(), d);
		
	}
	
	public static void printUpcomingVisits(ArrayList<Visit<Patient,Doctor>> vList, Map<Integer, Patient> pMap, Map<Integer, Doctor> dMap) {
		
		System.out.println("\n--------------------------------------------------");
		
		for(int i = 0; i < vList.size(); i++) {
			
			Integer patientKey = vList.get(i).getVisitor().getPatientID(); //Patient ID as map key
			Integer doctorKey = vList.get(i).getHost().getDoctorID(); //Doctor ID as map key
			
			String doctorMapName = dMap.get(doctorKey).getFirstName() + " " + dMap.get(doctorKey).getLastName();
			
			Format date = new SimpleDateFormat ("MMMM dd, yyyy");
			String formattedDate = date.format(vList.get(i).getVisitDate());
			
			System.out.println("\nVisit date: \t\t" + formattedDate);
			System.out.println("Doctor: \t\t" + doctorMapName);
			System.out.println("Specialty: \t\t" + dMap.get(doctorKey).getSpecialty());
			System.out.println("Days until visit: \t" + calculateDaysLeft(vList, i));
			System.out.println("Patient: ");
			System.out.println("\tFirst Name: \t\t" + pMap.get(patientKey).getFirstName());
			System.out.println("\tLast Name: \t\t" + pMap.get(patientKey).getLastName());
			System.out.println("\tSSN: \t\t\t" + pMap.get(patientKey).getSSN());
			System.out.println("\tAge: \t\t\t" + pMap.get(patientKey).getAge() + "\n");
	
		}
		
	}
	
	private static boolean doctorHasVisits(ArrayList<Visit<Patient,Doctor>> vList, int vLoc, String lName) {
		
		if(lName.contentEquals(vList.get(vLoc).getHost().getLastName()))
			return true;
		
		return false;
		
	}
	
	private static boolean patientHasVisits(ArrayList<Visit<Patient,Doctor>> vList, int vLoc, String lName) {
			
		if(lName.contentEquals(vList.get(vLoc).getVisitor().getLastName()))
			return true;
		
		return false;
		
	}
	
	private static long calculateDaysLeft(ArrayList<Visit<Patient, Doctor>> vList, int dateCount) {
		
		long daysLeft;
		
		Date currentDate = new Date();
		Format Date = new SimpleDateFormat ("yyyy-MM-dd");
		
		String formattedCurrentDate = Date.format(currentDate);
		String formattedVisitDate = Date.format(vList.get(dateCount).getVisitDate());

		LocalDate today = LocalDate.parse(formattedCurrentDate);
		LocalDate scheduledVisit = LocalDate.parse(formattedVisitDate);
		
		daysLeft = ChronoUnit.DAYS.between(today, scheduledVisit);
		
		return daysLeft;
		
	}
	
	private static Date convertToDate(String sDate) {
		
		SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		Date convertedDate = new Date();
		
		try {convertedDate = dateformat.parse(sDate);} 
		catch(ParseException e) {e.printStackTrace();}
		
		return convertedDate;
		
	}
	
}