package com.tcs.sgv.eis.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPaySignatureDtls;

public class EmployeeSignatureDtlsDAOImpl extends GenericDaoHibernateImpl  implements EmployeeSignatureDtlsDAO
{
	   Log logger = LogFactory.getLog( getClass() );
	   
		public EmployeeSignatureDtlsDAOImpl(Class  type,SessionFactory session)
		{
			super(type);
			setSessionFactory(session);
		}
		
		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		String sdate=sbConf.getSysdate();
		
		public List getAllDesignationDtlsData()
	    {
	        List  designationDtlsList = null;
	        Session hibSession = getSession();
	        String strQuery = " from OrgDesignationMst as dn order by dn.dsgnName";
	        Query designationDtlsQuery = hibSession.createQuery(strQuery);
	        designationDtlsList = designationDtlsQuery.list();
	        return designationDtlsList;
	    }
		
		public List getAllEmployeeSignatureDtlsData(long locid)
		{
			List  employeeSignatureDtlsList = null;
			Session hibSession = getSession();
			String queryString = " from  HrPaySignatureDtls as sd ";
	        queryString+=" join fetch  sd.hrEisEmpMst";            
	        queryString+=" join fetch  sd.hrEisEmpMst.orgEmpMst ";    
	        queryString+=" join fetch  sd.orgDesignationMst"; 
	        queryString+=" join fetch  sd.cmnLocationMst"; 
	        queryString+=" where sd.cmnLocationMst.locId="+ locid ;	                             	            
	        queryString+=" order by sd.hrEisEmpMst.orgEmpMst.empFname,sd.hrEisEmpMst.orgEmpMst.empMname,sd.hrEisEmpMst.orgEmpMst.empLname";
	        Query signatureDtlsQuery = hibSession.createQuery(queryString);
	        employeeSignatureDtlsList=signatureDtlsQuery.list();
	        return employeeSignatureDtlsList;
		}
		
		public List getAllEmployeeSignatureDtlsData(long locid,long empid)
		{
			List  employeeSignatureDtlList = null;
			Session hibSession = getSession();
			String queryString = " from  HrPaySignatureDtls as sd ";
	        queryString+=" join fetch  sd.hrEisEmpMst";            
	        queryString+=" join fetch  sd.hrEisEmpMst.orgEmpMst ";    
	        queryString+=" join fetch  sd.orgDesignationMst"; 
	        queryString+=" join fetch  sd.cmnLocationMst"; 
	        queryString+=" where sd.cmnLocationMst.locId="+ locid ;	 
	        if(empid!=0)
	        queryString+="and  sd.hrEisEmpMst.orgEmpMst.empId="+ empid;
	        queryString+=" order by sd.hrEisEmpMst.orgEmpMst.empFname,sd.hrEisEmpMst.orgEmpMst.empMname,sd.hrEisEmpMst.orgEmpMst.empLname";	         
	        Query signatureDtlQuery = hibSession.createQuery(queryString);
	        employeeSignatureDtlList=signatureDtlQuery.list();
	        logger.info("The Size of the EmployeeSignature Details Data is:-"+employeeSignatureDtlList.size());
	        return employeeSignatureDtlList;
		}
		
		public HrPaySignatureDtls getHrPaySignatureDtlsIdData(long Id)
		{
			HrPaySignatureDtls hrEmployeeSignatureInfo=new HrPaySignatureDtls();
			Session hibSession = getSession();
			String query1 = "from HrPaySignatureDtls as signatureLookup where signatureLookup.id = "+ Id ;
	        Query signatureDtlsIdQuery = hibSession.createQuery(query1);
	        hrEmployeeSignatureInfo = (HrPaySignatureDtls)signatureDtlsIdQuery.uniqueResult();
	        return hrEmployeeSignatureInfo;
	    }
		public List getAllEmployeeSignatureEmpId()
		{
			List  employeeSignatureDtlsList = null;
			Session hibSession = getSession();
            String strQuery1 = "select sd.hrEisEmpMst from HrPaySignatureDtls as sd join fetch sd.hrEisEmpMst.orgEmpMst";
            Query signatureEmpIDQuery = hibSession.createQuery(strQuery1);
            employeeSignatureDtlsList = signatureEmpIDQuery.list();
            return employeeSignatureDtlsList;
		}
		public List checkStartDate(String startDate,long locid,long id)
		{
			List startDateList=null;
			Session hibSession = getSession();
			String queryString = " from  HrPaySignatureDtls as sd ";
			queryString+=" join fetch  sd.cmnLocationMst"; 
			queryString+=" where sd.startDate<='"+startDate+"'";
			queryString+=" and sd.endDate>='"+startDate+"'";
			if((id!=0))
		    queryString+=" and sd.id not in("+id+")";
	        queryString+=" and sd.cmnLocationMst.locId="+ locid ;	
            Query startDateListQuery = hibSession.createQuery(queryString);
            startDateList = startDateListQuery.list();
            return startDateList;
		}
		public List checkEndDate(String endDate,long locid,long id)
		{
			List endDateList=null;
			Session hibSession = getSession();
			String queryString = " from  HrPaySignatureDtls as sd ";
			queryString+=" join fetch  sd.cmnLocationMst"; 
			queryString+=" where sd.startDate<='"+endDate+"'";
			queryString+=" and sd.endDate>='"+endDate+"'";
			if((id!=0))
		    queryString+=" and sd.id not in("+id+")";
	        queryString+=" and sd.cmnLocationMst.locId="+ locid ;	
            Query endDateListQuery = hibSession.createQuery(queryString);
            endDateList = endDateListQuery.list();
            return endDateList;
		}
		
}
		