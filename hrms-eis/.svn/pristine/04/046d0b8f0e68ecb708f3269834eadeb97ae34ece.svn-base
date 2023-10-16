package com.tcs.sgv.eis.util;

import java.util.Date;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.SalRevMstDAO;
import com.tcs.sgv.eis.dao.SalRevMstDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
public class SalRevMasterServiceImplHelper
{

	Log logger = LogFactory.getLog(getClass());
ServiceLocator serv;
	
	public SalRevMasterServiceImplHelper(ServiceLocator serv)
	{
	super();
	this.serv=serv;
	}

	//Added by Abhilash
	public void insertSalaryRevisionMasterDtls(long postId,HrPaySalRevMst hrPaySalRevMst,long attachment_Id,long userId,long langId,long locId)
	{
		Date sysdate = new Date();
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
		SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
		
		IdGenerator idGen = new IdGenerator();
		long salRevId = idGen.PKGeneratorWebService("hr_pay_sal_rev_mst", serv, userId, langId, locId);
		hrPaySalRevMst.setSalRevId(salRevId);		
		hrPaySalRevMst.setRevAttachmentId(attachment_Id);
		hrPaySalRevMst.setCreatedDate(sysdate);
		hrPaySalRevMst.setOrgUserMstByCreatedBy(orgUserMst);
		hrPaySalRevMst.setOrgPostMstByCreatedByPost(orgPostMst);
		hrPaySalRevMst.setCmnLocationMst(cmnLocationMst);
		//salRevMstDAO.create(hrPaySalRevMst);
	}
	
	public void updateSalaryRevisionMasterDtls(long salRevId,HrPaySalRevMst hrPaySalRevMst)
	{
		SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
		HrPaySalRevMst salRevMstVO = new HrPaySalRevMst();
						
	
		salRevMstVO = salRevMstDAO.read(salRevId);
		salRevMstVO.setRevOrderNo(hrPaySalRevMst.getRevOrderNo());
		salRevMstVO.setRevOrderDate(hrPaySalRevMst.getRevOrderDate());
		salRevMstVO.setRevEffcFrmDate(hrPaySalRevMst.getRevEffcFrmDate());
		salRevMstVO.setRevEffcToDate(hrPaySalRevMst.getRevEffcToDate());
		salRevMstVO.setRevFreqMthPaid(hrPaySalRevMst.getRevFreqMthPaid());
		salRevMstVO.setRevInstallments(hrPaySalRevMst.getRevInstallments());
		salRevMstVO.setRltBillTypeEdp(hrPaySalRevMst.getRltBillTypeEdp());
		salRevMstVO.setRevPayOutDate(hrPaySalRevMst.getRevPayOutDate());
		salRevMstVO.setRevStatus(hrPaySalRevMst.getRevStatus());
		salRevMstVO.setRevReason(hrPaySalRevMst.getRevReason());
		salRevMstVO.setCashPercentage(hrPaySalRevMst.getCashPercentage());
		salRevMstVO.setOrgPostMstByUpdatedByPost(hrPaySalRevMst.getOrgPostMstByUpdatedByPost());
		salRevMstVO.setOrgUserMstByUpdatedBy(hrPaySalRevMst.getOrgUserMstByUpdatedBy());				
		salRevMstVO.setUpdatedDate(hrPaySalRevMst.getUpdatedDate());
		salRevMstVO.setPblIndependentFlg(hrPaySalRevMst.getPblIndependentFlg());
		//salRevMstDAO.update(salRevMstVO);
	}
	//Ended by Abhilash
	
	
}
