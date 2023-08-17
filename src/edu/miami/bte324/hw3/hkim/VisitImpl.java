package edu.miami.bte324.hw3.hkim;

import java.util.Date;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public class VisitImpl<V, T> implements Visit<V,T>, Comparable<VisitImpl<V,T>> {
	
	private static Integer visitIDGenerator = 1;
	
	private V visitPatient;
	private T visitDoctor;
	private Date visitDate;
	private Integer visitID;
	
	public VisitImpl() {
		
		visitPatient = null;
		visitDoctor = null;
		visitDate = null;
		visitID = null;
	}
	
	public VisitImpl(V vPatient, T vDoctor, Date vDate) {
		
		this.visitPatient = vPatient;
		this.visitDoctor = vDoctor;
		this.visitDate = vDate; 
		this.visitID = visitIDGenerator;
		
		++visitIDGenerator;
	}
	
	public V getVisitor() {
		return visitPatient;
	}
	
	public T getHost() {
		return visitDoctor;
	}
	
	public Date getVisitDate() {
		return visitDate;
	}
	
	public Integer getVisitID() {
		return visitID;
	}
	
	public boolean equals(VisitImpl<V,T> other) {
		
		if(this == other) return true;
		
		if(other == null || (this.getClass() != other.getClass())) {
			return false;
		}
		
		VisitImpl<V,T> v = (VisitImpl<V, T>) other;
		
		return (this.visitPatient != null && visitPatient.equals(v.getVisitor())) && 
				(this.visitDoctor != null && visitDoctor.equals(v.getHost())) &&
				(this.visitDate != null && visitDate.equals(v.getVisitDate()));

	}
	
	public int hashCode() {
		
		int result = 0;
		
		result = 31 * result + (visitPatient != null ? visitPatient.hashCode() : 0);
		result = 31 * result + (visitDoctor != null ? visitDoctor.hashCode() : 0);
		result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
		
		return result;
		
	}
	
	public int compareTo(VisitImpl<V,T> otherVisit) {
		return this.visitID - otherVisit.visitID;
	}

}
