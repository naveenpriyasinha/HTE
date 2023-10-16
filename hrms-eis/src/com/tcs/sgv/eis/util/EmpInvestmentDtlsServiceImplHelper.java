package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpInvestmentDtlsDAOImpl;
import com.tcs.sgv.eis.dao.InvestmentTypeMstDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrInvestEmpDtls;
import com.tcs.sgv.eis.valueobject.HrInvestTypeMst;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * Created by Japen Pathak for for inserting / updating data in HR_INVEST_EMP_DTLS tables.
 */
public class EmpInvestmentDtlsServiceImplHelper 
{
Log logger = LogFactory.getLog(getClass());
	
	ServiceLocator serv;
	
	public EmpInvestmentDtlsServiceImplHelper(ServiceLocator serv)
	{
	super();
	this.serv=serv;
	}
	
	// Added by Abhilash

	/**
		 * @Author	: Abhilash
		 * @Date	: 17/02/2011 
		 * Function	: This method will update data in HR_INVEST_EMP_DTLS table.
		 * @param    HrInvestEmpDtls objHrInvestEmpDtls,HrInvestEmpDtls	hrInvestEmpDtls,long empInvestDtlsId,long postId,long userId
		 * @return   void
		 */
	public void updateEmpInvestmentDtls(HrInvestEmpDtls	hrInvestEmpDtls,long empInvestDtlsId,long postId,long userId) throws Exception
	{
		Date sysDate = new Date();
		HrInvestEmpDtls objHrInvestEmpDtls = new HrInvestEmpDtls();
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		EmpInvestmentDtlsDAOImpl empInvestmentDtlsDAOImpl = new EmpInvestmentDtlsDAOImpl(HrInvestEmpDtls.class,serv.getSessionFactory());
		//System.out.println("updateEmpInvestmentDtls in EmpInvestmentDtlsServiceImplHelper");
		//System.out.println("updateEmpInvestmentDtls"+hrInvestEmpDtls.getInvestDate());
		objHrInvestEmpDtls = empInvestmentDtlsDAOImpl.read(empInvestDtlsId);
		objHrInvestEmpDtls.setAmount(hrInvestEmpDtls.getAmount());
		objHrInvestEmpDtls.setProofSubmitFlag(hrInvestEmpDtls.getProofSubmitFlag());
		objHrInvestEmpDtls.setPolicyNo(hrInvestEmpDtls.getPolicyNo());
		objHrInvestEmpDtls.setInvestDate(hrInvestEmpDtls.getInvestDate());
		objHrInvestEmpDtls.setApprovalFlag(hrInvestEmpDtls.getApprovalFlag());
		objHrInvestEmpDtls.setUpdatedDate(sysDate);				
		objHrInvestEmpDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
		objHrInvestEmpDtls.setOrgUserMstByUpdatedBy(orgUserMst);								
		empInvestmentDtlsDAOImpl.update(objHrInvestEmpDtls);		
	}
	
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_INVEST_EMP_DTLS table.
	 * @param    int counter,long postId,long dbId,List lstInvestType,List lstEmpId,HrInvestEmpDtls hrInvestEmpDtls,List lstHrInvestEmpDtls,long userId,long langId,long locId
	 * @return   void
	 */
	
	public void insertEmpInvestmentDtls(int counter,long postId,long dbId,List lstInvestType,List lstEmpId,HrInvestEmpDtls hrInvestEmpDtls,List lstHrInvestEmpDtls,long userId,long langId,long locId) throws Exception
	{
		long investTypeId=0;
		long employeeId=0;
		HrInvestTypeMst hrInvestTypeMst = null;
		InvestmentTypeMstDAOImpl investmentTypeMstDAOImpl = new InvestmentTypeMstDAOImpl(HrInvestTypeMst.class,serv.getSessionFactory());
		EmpInvestmentDtlsDAOImpl empInvestmentDtlsDAOImpl = new EmpInvestmentDtlsDAOImpl(HrInvestEmpDtls.class,serv.getSessionFactory());
		//System.out.println("insertEmpInvestmentDtls in EmpInvestmentDtlsServiceImplHelper");
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
		
		List list = new  ArrayList();
		OrgEmpMst orgEmpMst = new OrgEmpMst();
		EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
		
		EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
		for(int i=0;i<counter;i++)
		{
			hrInvestEmpDtls = (HrInvestEmpDtls)lstHrInvestEmpDtls.get(i);																		
			IdGenerator idGen = new IdGenerator();
			long investDtlsId= idGen.PKGeneratorWebService("hr_invest_emp_dtls", serv, userId, langId, locId);
			logger.info("****************************the id generated is "+investDtlsId);
			hrInvestEmpDtls.setInvestDtlsId(investDtlsId);
			employeeId=Long.parseLong(lstEmpId.get(i).toString());
			orgEmpMst = empDAOImpl.read(employeeId);
			list = empInfoDAOImpl.getHrEmpFromOrgEmp(orgEmpMst);					
			investTypeId = Long.parseLong(lstInvestType.get(i).toString());
			hrInvestTypeMst = investmentTypeMstDAOImpl.read(investTypeId);
			hrInvestEmpDtls.setHrInvestTypeMst(hrInvestTypeMst);
			hrInvestEmpDtls.setHrEisEmpMst((HrEisEmpMst)list.get(0));
			hrInvestEmpDtls.setCmnDatabaseMst(cmnDatabaseMst);
			hrInvestEmpDtls.setOrgUserMstByCreatedBy(orgUserMst);
			hrInvestEmpDtls.setOrgPostMstByCreatedByPost(orgPostMst);									
			empInvestmentDtlsDAOImpl.create(hrInvestEmpDtls);		
	}
	}
	
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_INVEST_EMP_DTLS table.
	 * @param   Iterator i,long userId,long langId,long locId,long dbId,long postId
	 * @return   void
	 */
	
	public void insertEmpInvestmentDtlsByXMLFile(Iterator i,long userId,long langId,long locId,long dbId,long postId) throws Exception
	{
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		EmpInvestmentDtlsDAOImpl empInvestmentDtlsDAOImpl = new EmpInvestmentDtlsDAOImpl(HrInvestEmpDtls.class,serv.getSessionFactory());
		
		IdGenerator idgen = new IdGenerator();
		  Object RelationVO = i.next();
          HrInvestEmpDtls hrInvestEmpDtlsVO = (HrInvestEmpDtls) RelationVO;
          hrInvestEmpDtlsVO.setInvestDtlsId(idgen.PKGeneratorWebService("hr_invest_emp_dtls", serv, userId, langId, locId));
          hrInvestEmpDtlsVO.setCmnDatabaseMst(cmnDatabaseMst);
          hrInvestEmpDtlsVO.setOrgUserMstByCreatedBy(orgUserMst);
          hrInvestEmpDtlsVO.setOrgPostMstByCreatedByPost(orgPostMst);	
          empInvestmentDtlsDAOImpl.create(hrInvestEmpDtlsVO);
	}
	//Ended by Abhilash
}
