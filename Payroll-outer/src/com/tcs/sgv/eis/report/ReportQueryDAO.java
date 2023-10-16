package com.tcs.sgv.eis.report;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

public interface ReportQueryDAO {
	public void setSessionFactory(SessionFactory sessionFactory);
	public ArrayList getLocationDtlRpt(String lLocation,String lRecursiveOption);
	public ArrayList getEmplDtlRpt(String lLocation);
	public ArrayList getEmployeeTrackDtls(Long empId); 
	public ArrayList getPostDtlRpt(String lLocation); 
	public ArrayList getPostTrackDtls(Long postId); 
}
