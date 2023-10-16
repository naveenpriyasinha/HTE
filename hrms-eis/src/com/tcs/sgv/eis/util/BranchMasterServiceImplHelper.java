package com.tcs.sgv.eis.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BranchMasterDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
/**
 * Created by Japen Pathak for for inserting / updating data in hr_eis_branch_mst tables.
 */
public class BranchMasterServiceImplHelper 
{
	Log logger = LogFactory.getLog(getClass());
	ServiceLocator serv;
	
	public BranchMasterServiceImplHelper(ServiceLocator serv) 
	{
		super();
		this.serv = serv;
	}
	
//Added by Abhilash
	
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return bankVo from HrEisBranchMst  object.
	 * @param    HrEisBranchMst branchMst,long EnglangId
	 * @return   HrEisBankMst
	 */
	public HrEisBankMst getHrEisBankMstByBankTypeCode(HrEisBranchMst branchMst,long EnglangId)  throws Exception
	{
		BankMasterDAOImpl bankDao = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());
		/*long bankTypeCode=bankDao.read(branchMst.getHrEisBankMst().getBankId()).getBankTypeCode();
		logger.info("the bankTypeCode is after"+bankTypeCode);
		HrEisBankMst bankVo =bankDao.getDataFromTypeCode(bankTypeCode,EnglangId);*/
		HrEisBankMst bankVo = null;
		return bankVo;
	}
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in hr_eis_branch_mst table.
	 * @param    HrEisBranchMst branchMst,long langId,long dbId,long postId,long userId,long locId
	 * @return   void
	 */
	/*public void insertBranchDtls(RltBankBranchPay branchMst,long branchId,long langId,long dbId,long postId,long userId,long locId)  throws Exception
	{
		
		logger.info("INSIDE Update BranchMasterServiceImplHelper insertBranchDtls");
		Date sysdate = new Date();
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);

		BranchMasterDAOImpl branchMasterDAO = null;//new BranchMasterDAOImpl(RltBankBranchPay.class,serv.getSessionFactory());
		logger.info("INSIDE Update BankMasterServiceImplHelper insertBranchDtls");;
		branchMst.setCreatedDate(sysdate);						
		branchMst.setCmnLanguageMst(cmnLanguageMst);
		branchMst.setCmnDatabaseMst(cmnDatabaseMst);
		branchMst.setOrgPostMstByUpdatedByPost(orgPostMst);
		branchMst.setOrgPostMstByCreatedByPost(orgPostMst);
		branchMst.setCmnLocationMst(cmnLocationMst);
		branchMst.setOrgUserMstByUpdatedBy(orgUserMst);
		branchMst.setOrgUserMstByCreatedBy(orgUserMst);		
		
		IdGenerator idGen = new IdGenerator();
		long branchId = idGen.PKGeneratorWODBLOC("hr_eis_branch_mst", objectArgs);
		
		branchMst.setBranchId(branchId);
		branchMst.setBranchTypeCode(branchId);//temporary as this record will be entered manually
		branchMasterDAO.create(branchMst);	
	}*/
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will update data in hr_eis_branch_mst table.
	 * @param    HrEisBranchMst branchMst,long postId,long userId
	 * @return   void
	 */
	public void updateBranchDtls(HrEisBranchMst branchMst,long postId,long userId)  throws Exception
	{
		Date sysdate = new Date();
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		
		BranchMasterDAOImpl branchMasterDAO = null;//new BranchMasterDAOImpl(RltBankBranchPay.class,serv.getSessionFactory());
		logger.info("INSIDE Update BranchMasterServiceImplHelper updateBranchDtls");
		long branchId = branchMst.getBranchId();
		//RltBankBranchPay branchMstVO = branchMasterDAO.read(branchId);
		/*branchMstVO.setBranchName(branchMst.getBranchName());
		branchMstVO.setBranchAddr(branchMst.getBranchAddr());
		branchMstVO.setBranchCntryId(branchMst.getBranchCntryId());
		branchMstVO.setBranchCode(branchMst.getBranchCode());
		branchMstVO.setBranchDistId(branchMst.getBranchDistId());
		branchMstVO.setBranchId(branchMst.getBranchId());
		branchMstVO.setBranchStateId(branchMst.getBranchStateId());
		branchMstVO.setBranchTalId(branchMst.getBranchTalId());
		branchMstVO.setBranchVlgeId(branchMst.getBranchVlgeId());								
	   	branchMstVO.setHrEisBankMst(branchMst.getHrEisBankMst());								
		
	   	branchMstVO.setUpdatedDate(sysdate);
	   	branchMstVO.setOrgPostMstByUpdatedByPost(orgPostMst);
	   	branchMstVO.setOrgUserMstByUpdatedBy(orgUserMst);
	   	
	   	branchMstVO.setMicrCode(branchMst.getMicrCode());	
		branchMstVO.setIfscCode(branchMst.getIfscCode());
		branchMasterDAO.update(branchMstVO);*/
	}
	
	//Ended by Abhilash
	
	
	
}