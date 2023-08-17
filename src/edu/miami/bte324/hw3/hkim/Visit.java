package edu.miami.bte324.hw3.hkim;

import java.util.Date;

/**
 * @author Hyun Kim
 *
 * BTE 324 Assignment 3
 */

public interface Visit<V,T> {

	public V getVisitor();
	public T getHost();
	public Date getVisitDate();
	
}
