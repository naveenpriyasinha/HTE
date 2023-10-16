package com.tcs.sgv.eis.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadMpgDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * Created by Japen Pathak for for inserting / updating data in hr_pay_bill_subhead_mpg tables.
 */
public class BillHeadMasterServiceImplHelper 
{
	public final Log logger = LogFactory.getLog(getClass());
	ServiceLocator serv;
	
	/**
	 * @return the serv
	 */
	public ServiceLocator getServ() {
		return serv;
	}

	/**
	 * @param serv the serv to set
	 */
	public void setServ(ServiceLocator serv) {
		this.serv = serv;
	}

	public BillHeadMasterServiceImplHelper(ServiceLocator serv) 
	{
		super();
		this.serv = serv;
	}
	
//Added by Abhilash
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in hr_pay_bill_subhead_mpg table.
	 * @param    ServiceLocator serv,HrPayBillHeadMpg hrPayBillHeadMpgObj,String headChargable,CmnLookupMst postTypeLookupId,long userId,long postId,long subheadId,long elementCode,long finYearId,String classId,CmnLocationMst cmnLocationMst,String designaton
	 * @return   void
	 */
	public void updateBillHeadDtls(HrPayBillHeadMpg hrPayBillHeadMpgObj,String headChargable,CmnLookupMst postTypeLookupId,long userId,long postId,long subheadId,long elementCode,long finYearId,String classId,CmnLocationMst cmnLocationMst,String designaton)
	{
		long billid = hrPayBillHeadMpgObj.getBillId();
		long billheadId = hrPayBillHeadMpgObj.getBillHeadId();
		//System.out.println("The billid  inside Update Mode is------>"+ billid);
		
		BillHeadMpgDAOImpl billHeadMpgDAOImpl = null;//new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class, serv.getSessionFactory());
		HrPayBillHeadMpg headMpg = null;//billHeadMpgDAOImpl.read(billheadId);// Reading billid from Vo
		Date sysdate =new Date();
		
		logger.info("INSIDE UPDATE insertOrderMasterDtls");
		OrderHeadMpgDAOImpl orderHeadMpgDAOImpl=new
		OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());

		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		
		elementCode=subheadId;
		
		logger.info("INSIDE CREATE updateBillHeadDtls");
		logger.info("INSIDE CREATE BillHeadMasterServiceImplHelper updateBillHeadDtls");
		/*System.out.println("subheadId+"+subheadId);
		System.out.println("elementCode"+elementCode);
		System.out.println("elementCode"+elementCode);*/
		
		headMpg.setBillId(billid);
		headMpg.setFinYearId(finYearId);
		headMpg.setCmnLocationMst(cmnLocationMst);
		headMpg.setSubheadId(elementCode);
		headMpg.setUpdatedDate(sysdate);
		headMpg.setDsgn_Id(designaton);
		headMpg.setClass_Id(classId);
		headMpg.setOrgUserMstByUpdatedBy(orgUserMst);
		headMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
		headMpg.setFinYearId(21L);
		headMpg.setHeadChargable(headChargable);
		// Added By Urvin Shah.
		logger.info("the Value is:-" + postTypeLookupId);
		if (postTypeLookupId != null) 
		{
			headMpg.setPostType(postTypeLookupId);
		} else {
			headMpg.setPostType(null);
		}
		// End.
		//billHeadMpgDAOImpl.update(headMpg);// update Vo...VO ready to update
	}
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will insert data in hr_pay_bill_subhead_mpg table.
	 * @param    ServiceLocator serv,long postId,CmnLookupMst postTypeLookupId,String headChargable,long userId,long langId,long locId,long billID,long finYearId,String classId,CmnLocationMst cmnLocationMst,String designaton,long elementCode
	 * @return   void
	 */
	public void insertBillHeadDtls(long postId,CmnLookupMst postTypeLookupId,String headChargable,long userId,long langId,long locId,long billID,long finYearId,String classId,CmnLocationMst cmnLocationMst,String designaton,long elementCode)
	{
		Date sysdate = new Date();
		
		BillHeadMpgDAOImpl billHeadMpgDAOImpl = null;//new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class, serv.getSessionFactory());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
		
		
		logger.info("INSIDE CREATE insertBillHeadDtls");
		logger.info("INSIDE CREATE BillHeadMasterServiceImplHelper insertBillHeadDtls");
		
		
		IdGenerator idGen = new IdGenerator();
		long billHeadId = idGen.PKGeneratorWebService("hr_pay_bill_subhead_mpg", serv, userId, langId, locId);
		HrPayBillHeadMpg hrPayBillHeadMpg = new HrPayBillHeadMpg();
		hrPayBillHeadMpg.setBillId(billID);
		hrPayBillHeadMpg.setOrgPostMstByCreatedByPost(orgPostMst);
		hrPayBillHeadMpg.setOrgUserMstByCreatedBy(orgUserMst);
		hrPayBillHeadMpg.setCreatedDate(sysdate);
		hrPayBillHeadMpg.setBillHeadId(billHeadId);
		hrPayBillHeadMpg.setSubheadId(elementCode);
		hrPayBillHeadMpg.setFinYearId(finYearId);
		hrPayBillHeadMpg.setDsgn_Id(designaton);
		hrPayBillHeadMpg.setClass_Id(classId);
		hrPayBillHeadMpg.setCmnLocationMst(cmnLocationMst);
		// Added By Urvin Shah.
		if (postTypeLookupId != null) 
		{
			hrPayBillHeadMpg.setPostType(postTypeLookupId);
		}

		else
			hrPayBillHeadMpg.setPostType(null);
		// End.
		hrPayBillHeadMpg.setHeadChargable(headChargable);
		//billHeadMpgDAOImpl.create(hrPayBillHeadMpg);
	}
	//Ended by Abhilash
}
