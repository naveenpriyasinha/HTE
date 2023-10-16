package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.EmpExemptDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.ExemptTypeMstDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrItExemptEmpDtls;
import com.tcs.sgv.eis.valueobject.HrItExemptTypeMst;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


/**
 * Created by Abhilash for for inserting / updating data in HR_IT_EXEMPT_EMP_DTLS tables.
 */


public class EmpExemptDtlsServiceImplHelper 

{
	Log logger = LogFactory.getLog(getClass());
	ServiceLocator serv;

	public EmpExemptDtlsServiceImplHelper(ServiceLocator serv) 
	{
		super();
		this.serv = serv;
	}
	
//Added by Abhilash
/**
		 * @Author	: Abhilash
		 * @Date	: 17/02/2011 
		 * Function	: This method will update data in HR_IT_EXEMPT_EMP_DTLS table.
		 * @param    long postId,long userId,HrItExemptEmpDtls objHrItExemptEmpDtls,long empExemptDtlsId,HrItExemptEmpDtls hrItExemptEmpDtls
		 * @return   void
		 */
	
	public void updateEmpExemptionDtls(long postId,long userId,long empExemptDtlsId,HrItExemptEmpDtls lstHrItExemptEmpDtls)throws Exception
	{
		Date sysDate = new Date();
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		EmpExemptDtlsDAOImpl empExemptDtlsDAOImpl = new EmpExemptDtlsDAOImpl(HrItExemptEmpDtls.class,serv.getSessionFactory());
		HrItExemptEmpDtls objHrItExemptEmpDtls = new HrItExemptEmpDtls();	
		objHrItExemptEmpDtls = empExemptDtlsDAOImpl.read(empExemptDtlsId);
		objHrItExemptEmpDtls.setAmount(lstHrItExemptEmpDtls.getAmount());				
		objHrItExemptEmpDtls.setUpdatedDate(sysDate);				
		objHrItExemptEmpDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
		objHrItExemptEmpDtls.setOrgUserMstByUpdatedBy(orgUserMst);		
	}


/**
		 * @Author	: Abhilash
		 * @Date	: 17/02/2011 
		 * Function	: This method will update data in HR_IT_EXEMPT_EMP_DTLS table.
		 * @param    List lstEmpId,long postId,long dbId,int counter,List lstHrItExemptEmpDtls,long userId,long langId,long locId,List lstExemptType
		 * @return   void
		 */

	
	public void insertEmpExemptionDtls(List lstEmpId,long postId,long dbId,int counter,List lstHrItExemptEmpDtls,long userId,long langId,long locId,List lstExemptType)throws Exception
	{
		long exemptTypeId=0;
		long investTypeCode=0;
		long employeeId=0;
		List list = new  ArrayList();
		
		HrItExemptEmpDtls hrItExemptEmpDtls = null;
		HrItExemptTypeMst hrItExemptTypeMst = null;
		
		OrgEmpMst orgEmpMst = new OrgEmpMst();
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
		
		EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());				
		EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
		ExemptTypeMstDAOImpl exemptTypeMstDAOImpl = new ExemptTypeMstDAOImpl(HrItExemptTypeMst.class,serv.getSessionFactory());
		EmpExemptDtlsDAOImpl empExemptDtlsDAOImpl = new EmpExemptDtlsDAOImpl(HrItExemptEmpDtls.class,serv.getSessionFactory());
		
		for(int i=0;i<counter;i++)
		{
			hrItExemptEmpDtls = (HrItExemptEmpDtls)lstHrItExemptEmpDtls.get(i);																		
			IdGenerator idGen = new IdGenerator();
			long itexemptDtlsId= idGen.PKGeneratorWebService("hr_it_exempt_emp_dtls", serv, userId, langId, locId);
			
			logger.info("****************************the id generated is "+itexemptDtlsId);
			hrItExemptEmpDtls.setItexemptDtlsId(itexemptDtlsId);
			employeeId=Long.parseLong(lstEmpId.get(i).toString());
			orgEmpMst = empDAOImpl.read(employeeId);
			list = empInfoDAOImpl.getHrEmpFromOrgEmp(orgEmpMst);					
			exemptTypeId = Long.parseLong(lstExemptType.get(i).toString());
			hrItExemptTypeMst = exemptTypeMstDAOImpl.read(exemptTypeId);
			hrItExemptEmpDtls.setHrItExemptTypeMst(hrItExemptTypeMst);
			hrItExemptEmpDtls.setHrEisEmpMst((HrEisEmpMst)list.get(0));
			hrItExemptEmpDtls.setCmnDatabaseMst(cmnDatabaseMst);
			hrItExemptEmpDtls.setOrgUserMstByCreatedBy(orgUserMst);
			hrItExemptEmpDtls.setOrgPostMstByCreatedByPost(orgPostMst);									
			empExemptDtlsDAOImpl.create(hrItExemptEmpDtls);	
	}
	//Ended by Abhilash
}
}
