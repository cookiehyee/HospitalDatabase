package edu.miami.bte324.hw3.hkim;

import java.util.Comparator;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public class VisitComparator<V,T> implements Comparator<Visit<V,T>> {

	@Override
	public int compare(Visit<V, T> v1, Visit<V, T> v2) {

		int dateCompare = v1.getVisitDate().compareTo(v2.getVisitDate());
		return ((dateCompare == 0) ? v1.getVisitDate().compareTo(v2.getVisitDate()) : dateCompare);
		
	}

}
