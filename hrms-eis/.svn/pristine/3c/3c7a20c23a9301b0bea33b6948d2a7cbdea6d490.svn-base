package com.tcs.sgv.eis.util;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankDetailDAO;
import com.tcs.sgv.eis.dao.BankDetailDAOImpl;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BranchMasterDAO;
import com.tcs.sgv.eis.dao.BranchMasterDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * Created by Japen Pathak for for inserting / updating data in HR_EIS_EMP_MST,HR_EIS_BANK_DTLS and HR_EIS_BANK_DTLS tables.
 */
public class BankDetailsServiceImplHelper {

	ServiceLocator serv;
	
	public BankDetailsServiceImplHelper(ServiceLocator serv) {
		super();
		this.serv = serv;
	}
	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will return  OrgEmpMst from empId  object.
	 * @param    long empId
	 * @return   OrgEmpMst
	 */
	public OrgEmpMst getOrgEmpMSTByEmpId (long empId)
	{
		GenericDaoHibernateImpl<OrgEmpMst, Long> genericDao = new GenericDaoHibernateImpl<OrgEmpMst, Long>(OrgEmpMst.class);
		genericDao.setSessionFactory(serv.getSessionFactory());
		return  genericDao.read(empId);
	}

	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will return hrEmpMstList from OrgEmpMst  object.
	 * @param    OrgEmpMst orgEmpMst
	 * @return   HrEisEmpMst
	 */
	
	public HrEisEmpMst getHrEisEmpMstByPK(OrgEmpMst orgEmpMst)
	{
		EmpInfoDAOImpl empDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
		List<HrEisEmpMst> hrEmpMstList = empDao.getListByColumnAndValue("orgEmpMst", orgEmpMst);
		if(hrEmpMstList!=null && hrEmpMstList.size()>0)//Changed By Maruthi.
		{
			return  hrEmpMstList.get(0);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will update data in HR_EIS_BANK_DTLS table.
	 * @param    HrEisBankDtls bankDtls,long postId,long userId
	 * @return   void
	 */
	public void updateBankDtlsVO(HrEisBankDtls bankDtls,long postId,long userId) throws Exception
	{
		Date sysdate = new Date();
		BankDetailDAO bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
		long bankid = bankDtls.getBankDtlId();
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		HrEisBankDtls bankDtlsVO = bankDetailDAO.read(bankid);
		//logger.info("INSIDE UPDATE insertBankMasterDtls");
		bankDtlsVO.setBankAcctNo(bankDtls.getBankAcctNo());
		bankDtlsVO.setBankAcctStartDt(bankDtls.getBankAcctStartDt());
		bankDtlsVO.setBankAcctType(bankDtls.getBankAcctType());
		/*bankDtlsVO.setHrEisBankMst(bankDtls.getHrEisBankMst());
		bankDtlsVO.setHrEisBranchMst(bankDtls.getHrEisBranchMst());*/
		//bankDtlsVO.setHrEisEmpMst(bankDtls.getHrEisEmpMst());
		bankDtlsVO.setUpdatedDate(sysdate);	
		bankDtlsVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		bankDtlsVO.setOrgUserMstByUpdatedBy(orgUserMst);
		
		bankDetailDAO.update(bankDtlsVO);
		
	}

	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in HR_EIS_BANK_DTLS table.
	 * @param    HrEisBankDtls bankDtls,long postId,long userId,long dbId,long langId,long locId
	 * @return   void
	 */
	public void insertBankDtlsVO(HrEisBankDtls bankDtls,long postId,long userId,long dbId,long langId,long locId) throws Exception
	{
		
		Date sysdate = new Date();
		BankDetailDAO bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
		long bankid = bankDtls.getBankDtlId();
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		HrEisBankDtls bankDtlsVO = bankDetailDAO.read(bankid);
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
		
			//logger.info("INSIDE CREATE insertBankMasterDtls");
			IdGenerator idGen = new IdGenerator();
			long bankDtlsId = idGen.PKGeneratorWebService("hr_eis_bank_dtls", serv, userId , langId, locId);//String tableName,ServiceLocator servLoc,long userId,long langId,long locId)
			bankDtls.setBankDtlId(bankDtlsId);
		    
			 bankDtls.setCmnDatabaseMst(cmnDatabaseMst);
			 bankDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			 bankDtls.setOrgPostMstByCreatedByPost(orgPostMst);
		
			 bankDtls.setOrgUserMstByUpdatedBy(orgUserMst);
			 bankDtls.setOrgUserMstByCreatedBy(orgUserMst);
			 bankDtls.setCreatedDate(sysdate);
			 
			bankDetailDAO.create(bankDtls);
	}



	/**
	 * @Author	: Japen Pathak
	 * @Date	: 17/02/2011 
	 * Function	: This method will return data from HR_EIS_BANK_DTLS table.
	 * @param    HrEisBankDtls bankDtls
	 * @return   long
	 */
	public long getEmpId(HrEisBankDtls bankDtls)
	{
		return bankDtls.getHrEisEmpMst().getEmpId();
	}
	
}
