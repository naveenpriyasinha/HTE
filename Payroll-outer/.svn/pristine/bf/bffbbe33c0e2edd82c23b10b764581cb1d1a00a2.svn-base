package com.tcs.sgv.onlinebillprep.report;

import java.util.List;

import org.hibernate.SessionFactory;
public interface ReportQueryDAO 
{	
	public void setSessionFactory(SessionFactory sessionFactory);
	
	public List getBillTrackingReportForDDo(String strFromDate,String strToDate,Long subjectId,String strLocID,String strLocationCode,Long langId,Long postId); 
	public List getBillTrackingReportForCashBranch(String strFromDate,String strToDate,Long subjectId,String strLocID,String strLocationCode,Long langId,Long postId);
}
