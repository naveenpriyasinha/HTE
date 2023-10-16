/**
 * 
 */
package com.tcs.sgv.billproc.counter.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.counter.service.PhyBillVOGeneratorImpl;
import com.tcs.sgv.billproc.counter.valueobject.BillVO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.BudgetHdDtlsServiceImpl;
import com.tcs.sgv.common.service.BudgetHdServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dss.service.DssDataService;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;
import com.tcs.sgv.wf.valueobject.WfDocMvmntMstVO;


/** PhyBillDAOImpl
 *  This class is used to open inwarded bills onlien as well as physical, show saved bills, intimation ,
 *  getting Hyrarchy User
 * 	Date of Creation : 7th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *  Bhavik    23-Oct-2007   For making changes for code formating
 *  Hiral Shah   25-Oct-2007   Changes for Auditor filteration by bill type(Subject Id : 12,23,24)
 *  Hiral Shah   26-Oct-2007	Changes for Bill type code and its validation
 */
public class PhyBillDAOImpl extends GenericDaoHibernateImpl<TrnBillRegister,Long> implements PhyBillDAO  
{
	Log logger= LogFactory.getLog(getClass());
	ResourceBundle gObjRsrcBndle =ResourceBundle.getBundle("resources/billproc/BillprocConstants");	
	
	public PhyBillDAOImpl(Class<TrnBillRegister> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory); 
    }
	
	/**
	 * Method to update 'trn_bill_budhead_dtls' table
	 * @param  Map : inputMap
	 * @param  String : billNo
	 * 
	 * @return void
	 * @author vidhya
	 */	
	public void updateBudHeadDetails(Map inputMap,String billNo)
	{
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		BdgtHeadDtlsDAOImpl lObjBdgtHeadDtlsDAOImpl = new BdgtHeadDtlsDAOImpl(TrnBillBudheadDtls.class,serv.getSessionFactory());
		BptmCommonServicesDAOImpl lObjCmnSrvcDao = new BptmCommonServicesDAOImpl(TrnBillBudheadDtls.class,serv.getSessionFactory());
		TrnBillBudheadDtls lObjBudHeadVo= null;		
		
		try
		{
			String lStrDemandNo = (StringUtility.getParameter("cmbDemand", request).length()>0) ? StringUtility.getParameter("cmbDemand", request) : null;
			String lStrMjrHead = (StringUtility.getParameter("cmbMajorHead", request).length()>0) ? StringUtility.getParameter("cmbMajorHead", request) : null;			
			String lStrSubMjrHead = (StringUtility.getParameter("cmbSubMajorHead", request).length()>0) ? StringUtility.getParameter("cmbSubMajorHead", request) : null;
			String lStrMinorHead = (StringUtility.getParameter("cmbMinorHead", request).length()>0) ? StringUtility.getParameter("cmbMinorHead", request) : null;
			String lStrSubHead = (StringUtility.getParameter("cmbSubHead", request).length()>0) ? StringUtility.getParameter("cmbSubHead", request) : null;
			String lStrDetailHd = (StringUtility.getParameter("cmbDetailHead", request).length()>0) ? StringUtility.getParameter("cmbDetailHead", request) : null;
			String lStrBudType = (StringUtility.getParameter("cmbPlan", request).length()>0) ? StringUtility.getParameter("cmbPlan", request) : null;
			String lStrSchemeNo = (StringUtility.getParameter("txtSchemeCode", request).length()>0) ? StringUtility.getParameter("txtSchemeCode", request) : null;
			
			long verId = 1;
			
			Long updatedUserId = SessionHelper.getUserId(request);
			Long updatedPostId = SessionHelper.getUserId(request);		
			
			java.util.Date updatedDate = new java.util.Date();
			
			lObjBudHeadVo = lObjCmnSrvcDao.getBudHdVoFromBillNo(Long.parseLong(billNo));
			logger.info("Value of cmbDetailHead from Budget Head VO : " +lStrDetailHd);
			verId = lObjBudHeadVo.getVersionId();
			verId++;
			
			if(lObjBudHeadVo.getBillBudId()!=0)
			{
				lObjBudHeadVo = lObjBdgtHeadDtlsDAOImpl.read(lObjBudHeadVo.getBillBudId());
				lObjBudHeadVo.setDmndNo(lStrDemandNo);
				lObjBudHeadVo.setBudMjrHd(lStrMjrHead);
				lObjBudHeadVo.setBudSubmjrHd(lStrSubMjrHead);
				lObjBudHeadVo.setBudMinHd(lStrMinorHead);
				lObjBudHeadVo.setBudSubHd(lStrSubHead);
				lObjBudHeadVo.setBudDtlHd(lStrDetailHd);
				lObjBudHeadVo.setSchemeNo(lStrSchemeNo);
				lObjBudHeadVo.setVersionId(verId);
				lObjBudHeadVo.setUpdatedUserId(updatedUserId);
				lObjBudHeadVo.setUpdatedPostId(updatedPostId);
				lObjBudHeadVo.setUpdatedDate(updatedDate);
				
				lObjBudHeadVo.setBudType(lStrBudType);
				
				lObjBdgtHeadDtlsDAOImpl.update(lObjBudHeadVo);				
			}
		}
		catch(Exception e)
		{
			logger.error(" Error in updateBudHeadDetails " + e,e );
		}
	}
	/**
	 * Method to get Bills of user
	 * @param  Map : inputMap
	 *				 String lStrStatusFlag,
	 *				 String recFlag,
	 *				 List lMyBillList,
	 *				 String userId, 
	 *				 Long langId
	 * @return List
	 * @author vidhya
	 */	
	public List<BillVO> getMyBills(String lStrStatusFlag,String recFlag,List lMyBillList,String userId, Long langId,Map objectArgs)        // for getting the data to pages
    {
		logger.info("getAllBills() method Begin "); 
 		
		List<BillVO> dataList = new ArrayList<BillVO>();
		Session hibSession = getSession();
		
		String lStrTxtSearch=null;
		String lStrCmbSearch=null;
		
		//System.out.println(" Search is ::: "+ objectArgs.get("lStrTxtSearch") + " ::: "+ objectArgs.get("lStrCmbSearch"));
		if(objectArgs.get("lStrTxtSearch") !=null && objectArgs.get("lStrCmbSearch")!=null)
		{
			lStrTxtSearch=objectArgs.get("lStrTxtSearch").toString();
			lStrCmbSearch=objectArgs.get("lStrCmbSearch").toString();
		}
		
		//System.out.println(" cmbsearch : " +lStrTxtSearch +" " +lStrCmbSearch);
		
		logger.info("hibSession==>"+hibSession);
		String query=null;
		
		Query sqlquery=null;
		StringBuffer sb = new StringBuffer();
		if( lMyBillList!= null && lMyBillList.size() >0)
		{
			
			for(int i=0;i< lMyBillList.size();i++)
			{
				WfDocMvmntMstVO mvmntVO = (WfDocMvmntMstVO)lMyBillList.get(i);
				sb.append(mvmntVO.getJobRefId().toString());
				if(i+1!=lMyBillList.size())
				{
					sb.append(",");	
				}
				
			}
			query = "select  p.tcBill,p.billNo,p.billGrossAmount,p.billDate,p.ddoCode,p.budmjrHd,p.tokenNum," +
						" mb.subjectDesc,p.billCntrlNo,p.audPostId,clm.lookupName,odm.ddoName,odm.ddoNo,odm.cardexNo," +
						" p.createdDate,p.phyBill,p.billNetAmount,p.auditorNetAmount,p.refNum,p.subjectId " +
						" from CmnLookupMst  clm,TrnBillMvmnt tbm,TrnBillRegister  p,MstBillType  mb, " +
						" OrgDdoMst odm  where  mb.subjectId=p.subjectId and odm.ddoCode = p.ddoCode and " +
						" mb.langId = odm.langId  and tbm.billNo=p.billNo and  p.currBillStatus in ('" +
						lStrStatusFlag+"','BILL_EXP')  and p.billNo in ("+sb.toString()+")  " +
						" and p.tcBill=clm.lookupName and clm.cmnLanguageMst.langId="+langId+ 
						" and mb.langId=" +langId +" ";
			//if(!lStrStatusFlag.equals(gObjRsrcBndle.getString("STATUS.BillFwdCardex")) && !lStrStatusFlag.equals(gObjRsrcBndle.getString("STATUS.BillInward")))
			if(!lStrStatusFlag.equals(gObjRsrcBndle.getString("STATUS.BillInward")))
			{
				query = query +  " and tbm.receivedFlag="+ recFlag ;
			}
			
			if(lStrCmbSearch!=null &&  lStrTxtSearch!=null)
			{
				query =query+" and " +  lStrCmbSearch  ;
				if(lStrCmbSearch.equals("p.inwardDt"))
				{
					query =query + " like to_date('" + lStrTxtSearch + "','dd/mm/yyyy') " ;
				}
				else
				{
					query =query + " like '%"+ lStrTxtSearch + "%'" ;
				}
				
			}
			query = query + " and tbm.movemntId = (select max(movemntId) from TrnBillMvmnt b where b.billNo = p.billNo ) " ;
			if(objectArgs.get("lStrOnlineBill")!=null)
			{
				
				query = query + " and p.phyBill =" + objectArgs.get("lStrOnlineBill").toString() ;
			}
			query =query+" order by p.createdDate desc ";
	    
		
		
		sqlquery = hibSession.createQuery(query);
		List resList = sqlquery.list();
		if (resList!=null && resList.size()>0){
			Iterator it = resList.iterator();
			while(it.hasNext()) {
				Object[] tuple = (Object[]) it.next();
				BillVO billVO = new BillVO();
				billVO.setTcBill((tuple[0].toString()));
				billVO.setBillNo(Long.parseLong(tuple[1].toString()));
				billVO.setBillGrossAmount(new BigDecimal(tuple[2].toString()));
				billVO.setInwardDt((Date) tuple[3]);
				billVO.setDdoName(tuple[11].toString());
				billVO.setCardexVerify("Y");
				billVO.setBudmjrHd(tuple[5].toString());
				if(tuple[6]!= null && !tuple[6].toString().trim().equals(""))
				{
					billVO.setTokenNum(Long.parseLong(tuple[6].toString()));
				}
				billVO.setEmployeeName(tuple[11].toString());
				billVO.setSubjectDesc(tuple[7].toString());
			
				if(tuple[13]!= null && !tuple[13].toString().trim().equals(""))
				{
					billVO.setCardexNo(Long.parseLong(tuple[13].toString()));
				}
				if(tuple[4]!= null && !tuple[4].toString().trim().equals(""))
				{
					billVO.setDdoCode(tuple[4].toString());
				}
				if(tuple[12]!= null && !tuple[12].toString().trim().equals(""))
				{
					billVO.setDdoNO(tuple[12].toString());
				}
				billVO.setLookupName(tuple[10].toString());
				
				billVO.setBillCntrlNo(tuple[8].toString());
				if(tuple[9]!=null)						
				{				
					
					billVO.setAudPostId(Long.parseLong(tuple[9].toString()));
				
					Query audQuery=hibSession.createQuery("select oe.empPrefix,oe.empFname,oe.empLname,p.audPostId,oe.empMname from TrnBillRegister  p,OrgEmpMst  oe,OrgUserpostRlt our where oe.orgUserMst.userId=our.orgUserMst.userId and our.orgPostMstByPostId.postId=p.audPostId and p.billNo =" + billVO.getBillNo() +" and oe.cmnLanguageMst.langId=" +langId);
					List resltList = audQuery.list();
					if (resList!=null && resList.size()>0){
						Iterator itr = resltList.iterator();
						while(itr.hasNext()) {
							Object[] tuple1 = (Object[]) itr.next();
							billVO.setAudName(tuple1[0].toString()+" "+tuple1[1].toString()+" "+tuple1[4].toString()+" "+tuple1[2].toString());
						}
					}
				}
				else
				{
					
					billVO.setAudPostId((long)-1);
					billVO.setAudName("-1");
				}
				if(tuple[16]!=null)
				{
					billVO.setBillNetAmount(new BigDecimal(tuple[16].toString()));
				}
				if(tuple[17]!=null)
				{
					billVO.setAudNetAmount(new BigDecimal(tuple[17].toString()));
				}
				if(tuple[18]!=null)
				{
					billVO.setRefNum(Integer.parseInt(tuple[18].toString()));
				}
/*	Changes for auditor filteration on bill Type.   - Hiral */				
				if(tuple[19]!=null)
				{
					billVO.setSubjectId(Long.parseLong(tuple[19].toString()));
				}
/*	Ends : Changes for auditor filteration on bill Type.   - Hiral */				
				billVO.setObjCount(getObjectionsForUser(userId, Long.parseLong(tuple[1].toString())));
				billVO.setPhyBill(Long.parseLong(tuple[15].toString()));
				dataList.add(billVO);
			}
		  }
		}
        return dataList;      
        
    }
	
	/**
	 * Method to update 'trn_bill_register' table.
	 * @param  Map : inputMap
	 * @param  String : billNo
	 * 
	 * @return int
	 * @author vidhya
	 */	
	public int updateTrnBillReg(Map inputMap, String lStrBillNo)
	{
		int status=-1;
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		HttpSession session = request.getSession();
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		TrnBillRegister lObjBillRegister= new TrnBillRegister();
		TrnBillBudheadDtls lObjTrnBudHeadDtls = new TrnBillBudheadDtls();
		BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class,serv.getSessionFactory()); 
		FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
		BudgetHdServiceImpl lObjBudHdSrvc = new BudgetHdServiceImpl();
		
		Long lLngDbId = SessionHelper.getDbId(inputMap);
		Long lLngUserId = SessionHelper.getUserId(request);
		Long lLngPostId = SessionHelper.getPostId(inputMap);
		String lStrLocCode = SessionHelper.getLocationCode(inputMap);
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		
		int insertFlag=0;
		Long lLngVersionId = null;
		String lStrBillCode = null;
		String lStrDeptCode = null;
		Long lLngRcptId = null;
		try
		{	
			lObjBillRegister = read(Long.parseLong(lStrBillNo));
							
			String lStrDemandCode = (StringUtility.getParameter("cmbDemand", request).length()>0) ? StringUtility.getParameter("cmbDemand", request) : null;
			String lStrBudMjrHd = (StringUtility.getParameter("cmbMajorHead", request).length()>0) ? StringUtility.getParameter("cmbMajorHead", request) : null;
			String lStrExempted = (StringUtility.getParameter("radExempted", request).length()>0) ? StringUtility.getParameter("radExempted", request) : null;
			String lStrDdoCode = (StringUtility.getParameter("DDOCode", request).length()>0) ? StringUtility.getParameter("DDOCode", request) : null;							
			
			String lDtBillDate = StringUtility.getParameter("txtBillDate",request);
			String lStrCurrBillStatus = lObjBillRegister.getCurrBillStatus();
			
			BigDecimal lBdNetAmt = (StringUtility.getParameter("txtNetAmt", request).length()>0) ? new java.math.BigDecimal(StringUtility.getParameter("txtNetAmt", request))  : null;
			BigDecimal lBdGrossAmt = (StringUtility.getParameter("txtGrossAmt", request).length()>0) ? new java.math.BigDecimal(StringUtility.getParameter("txtGrossAmt", request)) : null;
			BigDecimal lBdGrantAmt = (StringUtility.getParameter("txtGrantAmt", request).length()>0) ? new java.math.BigDecimal(StringUtility.getParameter("txtGrantAmt", request)) : null;
			BigDecimal lBdAuditNetAmt = (StringUtility.getParameter("txtAdtNetAmt", request).length()>0) ? new java.math.BigDecimal(StringUtility.getParameter("txtAdtNetAmt", request)) : null;
			
			Long lLngAuditPost = (StringUtility.getParameter("cmbAuditorName", request).length()>0) ? Long.parseLong(StringUtility.getParameter("cmbAuditorName", request)) : null;
			String lStrBillCtgry = (StringUtility.getParameter("cmbTCCtgry", request).length()>0) ? StringUtility.getParameter("cmbTCCtgry", request) : null;
			String lStrBillType = (StringUtility.getParameter("cmbBillType", request).length()>0) ? StringUtility.getParameter("cmbBillType", request) : null;
			String lStrGoNgo = (StringUtility.getParameter("cmbGONGO", request).length()>0) ? StringUtility.getParameter("cmbGONGO", request) : null;
			String lStrPrevBillNo = (StringUtility.getParameter("txtPrevBillNo", request).length()>0) ? StringUtility.getParameter("txtPrevBillNo", request) : null;
			
			String lStrSubMjrHead = (StringUtility.getParameter("cmbSubMajorHead", request).length()>0) ? StringUtility.getParameter("cmbSubMajorHead", request) : null;
			String lStrMinorHead = (StringUtility.getParameter("cmbMinorHead", request).length()>0) ? StringUtility.getParameter("cmbMinorHead", request) : null;
			String lStrSubHead = (StringUtility.getParameter("cmbSubHead", request).length()>0) ? StringUtility.getParameter("cmbSubHead", request) : null;
			String lStrBudType = (StringUtility.getParameter("cmbPlan", request).length()>0) ? StringUtility.getParameter("cmbPlan", request) : null;
			String lStrSchemeNo = (StringUtility.getParameter("txtSchemeCode", request).length()>0) ? StringUtility.getParameter("txtSchemeCode", request) : null;
			
			lObjTrnBudHeadDtls.setBudSubmjrHd(lStrSubMjrHead);
			lObjTrnBudHeadDtls.setBudMinHd(lStrMinorHead);
			lObjTrnBudHeadDtls.setBudSubHd(lStrSubHead);
			lObjTrnBudHeadDtls.setBudType(lStrBudType);
			lObjTrnBudHeadDtls.setSchemeNo(lStrSchemeNo);
			
			Integer lIntFinYearId = lObjFinYearDAOImpl.getFinYearIdByCurDate();

			Date lDtCurDate = new java.util.Date();
			
			if(lStrExempted.equalsIgnoreCase(gObjRsrcBndle.getString("CMN.Yes")))
			{
				lStrBillCode = StringUtility.getParameter("cmbBillCode",request).length()>0 ? StringUtility.getParameter("cmbBillCode",request) : null;
				lObjBillRegister.setBillCode(lStrBillCode);
			}

	/*		To set Dept Code 		*/
			if(lStrDemandCode!=null)
				lStrDeptCode = BptmCommonServiceImpl.getDeptByDemand(lStrDemandCode, lLngLangId, lLngDbId, serv);
			//System.out.println("Value of department Code in update TrnBill register : " +lStrDeptCode);
			if(lStrDeptCode!=null)
				lObjBillRegister.setDeptCode(lStrDeptCode);
	/*		End : To set Dept Code 	*/			
			
			lObjBillRegister.setBillCntrlNo(lObjBillRegister.getBillCntrlNo());
			lObjBillRegister.setBillDate(new SimpleDateFormat("dd/MM/yyyy").parse(lDtBillDate));
/*	Changes for Bill Type Code   */			
			Long lLngSubjectId = null;
			if(lObjCmnSrvcDAOImpl.getSubIdFromBillType(lStrBillType)!=null)
			{
				logger.info("Inside PhyBillDAO , Inside if for Bill type, value of BillType : " +lStrBillType);
				lLngSubjectId = lObjCmnSrvcDAOImpl.getSubIdFromBillType(lStrBillType);				
				logger.info("In PhyBillDAO, Inside if for Bill type, value of Subject Id : " +lLngSubjectId);
				lObjBillRegister.setSubjectId(lLngSubjectId);
			}
/*	Changes for Bill Type Code   */			
//			lObjBillRegister.setSubjectId(lLngSubjectId);
			lObjBillRegister.setTokenNum(lObjBillRegister.getTokenNum());				
			lObjBillRegister.setTcBill(lStrBillCtgry);
			lObjBillRegister.setAuditorNetAmount(lBdAuditNetAmt);
			
			long lngPrevBillFlag = 0;
			boolean lHeadStatus = lObjBudHdSrvc.validateHeads(inputMap);
			//System.out.println("Value of Head status flag in update TrnBillReg : " +lHeadStatus);
			if(lHeadStatus == false)
			{
				status = 0;
				return status;
			}
/*		Validating bill type Code    */			
			if(lLngSubjectId==null)
			{
				status = -3;
				return status;
			}
/*	Ends : 	Validating bill type Code   */			
			if(lStrPrevBillNo!=null && lStrPrevBillNo.toString().length()>0)
			{
				logger.info("Value of previous BillCntrl No : " +lStrPrevBillNo.length());
				lngPrevBillFlag = lObjCmnSrvcDAOImpl.checkPreBillNo(lStrPrevBillNo);
				logger.info("Inside not null lngPrevBillFlag : " +lngPrevBillFlag);
				if(lngPrevBillFlag!=-1 && lHeadStatus == true)
				{
					logger.info("Inside correct Prevbillno");
					lObjBillRegister.setPrevBillNo(lngPrevBillFlag);
					status = 1;
				}
				else
				{					
					logger.info("Inside incorrect Prevbillno");
					return status;
				}
			}
			else
			{
				logger.info("Inside null prevBillNo");
				status=1;
				lObjBillRegister.setPrevBillNo(null);
			}
			if(lStrBillCtgry.equalsIgnoreCase("TC"))
			{				
//				lLngRcptId = Long.parseLong(lObjBillRegister.getReceiptId());
				Integer lIntRcptState = updateReceiptDetails(inputMap,Long.parseLong(lStrBillNo));
				logger.info("Value of lIntRcptState in setBillDetails : " +lIntRcptState);
				if(lIntRcptState==-1)
				{
					status = -2;
					return status;
				}
//				lObjBillRegister.setReceiptId(lLngRcptId.toString());
			}
//			else
			if(lHeadStatus==true)
			{
				status=1;
			}
			if(lObjBillRegister.getDemandCode().equals("999") && !(lStrDemandCode.equals("999")))
			{				
				insertFlag=1;
			}
			if(!lObjBillRegister.getDemandCode().equals("999") && lStrDemandCode.equals("999"))
			{
				insertFlag=2;
			}
			
			lObjBillRegister.setPhyBill(lObjBillRegister.getPhyBill());
			lObjBillRegister.setDemandCode(lStrDemandCode);
			lObjBillRegister.setBudmjrHd(lStrBudMjrHd);
			
			logger.info("Value of Department ID in update TrnBillReg : " +request.getParameter("txtDepartment"));
			if(request.getParameter("txtDepartment")!=null)
				if(!request.getParameter("txtDepartment").toString().equalsIgnoreCase("-1"))
					lObjBillRegister.setDdoDeptId(request.getParameter("txtDepartment").toString());
				else
					lObjBillRegister.setDdoDeptId(null);
			
			lObjBillRegister.setInwardDt(lDtCurDate);	
			
			lObjBillRegister.setBillGrossAmount(lBdGrossAmt);
			logger.info("Value of Gross Amount after setting in bill register : " +lBdGrossAmt);
			lObjBillRegister.setBillNetAmount(lBdNetAmt);
			logger.info("Value of Net Amount after setting in bill register : " +lBdNetAmt);
			
			lLngVersionId = lObjBillRegister.getVersionId();
			lLngVersionId++;
//				getVersionId(lStrBillNo);
			lObjBillRegister.setVersionId(lLngVersionId);
			
			lObjBillRegister.setExempted(lStrExempted);				
			lObjBillRegister.setGoNgo(lStrGoNgo);
			logger.info("Grant amount in update bill register : " +lBdGrantAmt);
			lObjBillRegister.setGrantAmount(lBdGrantAmt);
			
			lObjBillRegister.setCurrBillStatus(lStrCurrBillStatus);
			lObjBillRegister.setFinYearId(lIntFinYearId.toString());
			
			logger.info("Value of Auditor Post ID in update TrnBillRegister : " +lLngAuditPost);
			if(lLngAuditPost!=null && lLngAuditPost==-1)
				lLngAuditPost=null;
			lObjBillRegister.setAudPostId(lLngAuditPost);
			logger.info("Value of Auditor Post Id set in Bill Register : " +lObjBillRegister.getAudPostId());

			lObjBillRegister.setDdoCode(lStrDdoCode);				
			lObjBillRegister.setCurrBillStatusDate(lDtCurDate);
			
			lObjBillRegister.setUpdatedDate(lDtCurDate);
			lObjBillRegister.setUpdatedPostId(lLngPostId);
			lObjBillRegister.setUpdatedUserId(lLngUserId);
			lObjBillRegister.setLocationCode(lStrLocCode);
			lObjBillRegister.setDbId(lLngDbId);
						
			logger.info("Value of attachment in Phy bill dao, update trn_bill_register beofre if : " +session.getAttribute("AttachID"));
			if(session.getAttribute("AttachID")!=null && (session.getAttribute("AttachID").toString().length()>0))
			{
				Long lLngAttachId = (Long)session.getAttribute("AttachID");
				logger.info("Value of Attachment ID in PhyBillDAOImpl from Session(for new Attachment) :: " +lLngAttachId);
				lObjBillRegister.setAttachmentId(lLngAttachId);
				session.removeAttribute("AttachID");
			}
			else
			if(session.getAttribute("AttachID")!=null)
			{
				Long attachId = lObjBillRegister.getAttachmentId();
				lObjBillRegister.setAttachmentId(attachId);
				session.removeAttribute("AttachID");
			}
			if(lStrExempted.equalsIgnoreCase(gObjRsrcBndle.getString("CMN.Yes")))
			{			
				lStrBillCode = StringUtility.getParameter("cmbBillCode",request);
				lObjBillRegister.setBillCode(lStrBillCode);
			}
						
			logger.info("#########Department : " +lObjBillRegister.getDdoDeptId());
			logger.info("## ^^^^^^^^^ Bill Date : " +lObjBillRegister.getBillDate());
			logger.info("#########Gross Amount : " +lObjBillRegister.getBillGrossAmount());
			logger.info("^^^^^^^^#Net Amount : " +lObjBillRegister.getBillNetAmount());
			logger.info("#^^^^^^^^GO/NGO  : " +lObjBillRegister.getGoNgo());
			logger.info("^#^^^^^^^Bill Category  : " +lObjBillRegister.getTcBill());
			logger.info("^^#^^^^^^Demand No : " +lObjBillRegister.getDemandCode());
			logger.info("^^^#^^^^^Major Head : " +lObjBillRegister.getBudmjrHd());
			logger.info("^^^^#^^^^Exempted : " +lObjBillRegister.getExempted());
			logger.info("######## Receipt ID : " +lObjBillRegister.getReceiptId());			
			logger.info("######## Previous Bill nO :  " +lObjBillRegister.getPrevBillNo());
			logger.info("#^^^#^^^^ Value of Attachment ID : " +lObjBillRegister.getAttachmentId());
			logger.info("^#^#^#^#^# Value of Grant Amount : " +lObjBillRegister.getGrantAmount()); 
			logger.info("Before Updating Bill register.");
			
			update(lObjBillRegister);
			logger.info("Value of insert Flag : " +insertFlag);
			if(!(lObjBillRegister.getDemandCode().equals("999")) && (insertFlag==0))
			{
				logger.info("befoer updating valud of rpt_exp_dtls"); 
				inputMap.put("TrnBudHeadDtls", lObjTrnBudHeadDtls);
				updateRptExpDtls(inputMap, Long.parseLong(lStrBillNo), lObjBillRegister);
				logger.info("Inside");
			}			
			if(insertFlag==1)
			{
				logger.info("Inside insert expe and delete receipt expe");
				deleteRptReceipt(inputMap, Long.parseLong(lStrBillNo), lObjBillRegister);
				insertExpData(inputMap, lObjBillRegister, Long.parseLong(lStrBillNo));
			}
			if(insertFlag==2)
			{
				logger.info("Inside insert receipt and delete expe");
				deleteRptExpDtls(inputMap, Long.parseLong(lStrBillNo), lObjBillRegister);
				insertRptRcptData(inputMap, lObjBillRegister, Long.parseLong(lStrBillNo));
			}
			
			if(lStrBillCtgry.equalsIgnoreCase("TC") || lObjBillRegister.getDemandCode().equals("999"))
			{
				inputMap.put("TrnBudHeadDtls",lObjTrnBudHeadDtls);
				logger.info("Inside if update in phybil DAO ::: " +lLngRcptId);
				Integer lIntRowCount = StringUtility.getParameter("rowCount",request)!=null?Integer.parseInt(StringUtility.getParameter("rowCount",request)):null;
				logger.info("Value of rowCount in updatePhyBillDAO : " +lIntRowCount);
				List lListTrnRcptVO = lObjCmnSrvcDAOImpl.getTrnRcptFromBill(Long.parseLong(lStrBillNo), inputMap);
				logger.info("Value of List Size in updatePhyBillDAO : " +lListTrnRcptVO.size());
				
				if(lObjBillRegister.getDemandCode().equals("999"))
				{						
//					logger.info("Value of TrnReceiptDetailId from VO in 999 " +i +" : " +lObjTrnRcptDtsl.getReceiptDetailId());
//					inputMap.put("TrnReceiptDetailsVO", lObjTrnRcptDtsl);
					updateRptRcptDtls(inputMap, Long.parseLong("-1"), Long.parseLong(lStrBillNo),lObjBillRegister);
				}
				for(int i=0;i<lListTrnRcptVO.size();i++)
				{
					TrnReceiptDetails lObjTrnRcptDtsl = new TrnReceiptDetails();
					lObjTrnRcptDtsl = (TrnReceiptDetails)lListTrnRcptVO.get(i);
//					if(!(lObjBillRegister.getDemandCode().equals("999")))
					{
//						TrnReceiptDetails lObjTrnRcptDtsl = new TrnReceiptDetails();
//						lObjTrnRcptDtsl = (TrnReceiptDetails)lListTrnRcptVO.get(i);
						inputMap.put("TrnReceiptDetailsVO", lObjTrnRcptDtsl);
						logger.info("Value of TrnReceiptDetailId from VO " +i +" : " +lObjTrnRcptDtsl.getReceiptDetailId());
						updateRptRcptDtls(inputMap, Long.parseLong(lStrBillNo), lObjTrnRcptDtsl.getReceiptDetailId(),lObjBillRegister);
					}
				}
//				if(lLngRcptId!=null)
				{
/*					logger.info("Befoer updateRptRcptDtls");
					updateRptRcptDtls(inputMap, Long.parseLong(lStrBillNo), lLngRcptId,lObjBillRegister);
					logger.info("After updateRptRcptDtls");
*/					
				}
			}
			logger.info("After Updating Bill register.");
		}
		catch(Exception e)
		{
			logger.error(" Error in updateTrnBillRegister " + e,e );
		}
		return status;
	}
		
/*	public double getNetAmount(String billNo)
	{
		Session hibSession = getSession();
		Query query = hibSession.createSQLQuery("select BILL_NET_AMOUNT from trn_bill_register where bill_no = " +billNo);
		logger.info("Query for getNetAmount : " +query);
		List lst = query.list();
		Iterator it = lst.iterator();
		double netAmt = 0; 
		while(it.hasNext())
		{
			Object obj = (Object) it.next();
			logger.info("Value of Object from net amount query : " +obj.toString());
			netAmt = Double.parseDouble(obj.toString());
		}
		logger.info("Value of net amount to be returned : " +netAmt);
		return netAmt;
	}
*/		
	/**
	 * Method to update 'trn_receipt_details' table.
	 * @param  Map : inputMap
	 * @param  Long : lLngRcptId
	 * 
	 * @return Integer
	 * @author vidhya
	 */	
	public Integer updateReceiptDetails(Map inputMap, Long lLngBillNo)
	{
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		TrnRcptDtlsDAOImpl lObjRcptDtlsDAOImpl = new TrnRcptDtlsDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
		FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
		BptmCommonServicesDAOImpl lObjCmnSrvDAO = new BptmCommonServicesDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
		TrnReceiptDetails lObjRcptDtlsVo= new TrnReceiptDetails();
		BudgetHdServiceImpl lObjBudHdSrvc = new BudgetHdServiceImpl();  
		BudgetHdDtlsServiceImpl lObjBudHdDtlsSrvcImpl = new BudgetHdDtlsServiceImpl();
		
		Long lLngUserId = SessionHelper.getUserId(request);
		Long lLngPostId = SessionHelper.getPostId(inputMap);
		Long lLngDbId = SessionHelper.getDbId(inputMap);
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		Long lLngFinYearId = Long.parseLong(String.valueOf(lObjFinYearDAOImpl.getFinYearIdByCurDate()));
		
		List lListChallan = new ArrayList();
		String lStrLocCode = SessionHelper.getLocationCode(inputMap);
		String lStrReceiptNo = "";
		
		boolean lHeadsFlag = false;
		
		try
		{		
			Date rcptDate = null;		
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date lDtCurDate = new java.util.Date();
			
			Integer lIntRowCount = StringUtility.getParameter("rowCount",request)!=null?Integer.parseInt(StringUtility.getParameter("rowCount",request)):null;			
			logger.info("Value of RowCount : " +lIntRowCount);
			List lListTrnRcptVO = lObjCmnSrvDAO.getTrnRcptFromBill(lLngBillNo, inputMap);
			//System.out.println("Value of List size : " +lListTrnRcptVO.size());
			for(int i=0;i<lListTrnRcptVO.size();i++)
			{				
				TrnReceiptDetails trnRcptDtlsVO = (TrnReceiptDetails)lListTrnRcptVO.get(i);
				String rcptNo = request.getParameter("txtChallanNo"+(i+1))!=null ? request.getParameter("txtChallanNo"+(i+1)):null;
				BigDecimal lBDAmount = (StringUtility.getParameter("txtChallanAmt"+(i+1), request).length()>0) ? new java.math.BigDecimal(StringUtility.getParameter("txtChallanAmt"+(i+1), request)) : null;
				String challanDate = (StringUtility.getParameter("txtChallanDate"+(i+1), request).length()>0) ? StringUtility.getParameter("txtChallanDate"+(i+1), request) : null;
				String majorHead = (StringUtility.getParameter("txtChallanMjrHead"+(i+1), request).length()>0) ? StringUtility.getParameter("txtChallanMjrHead"+(i+1), request) : null;
				
				logger.info("In update Challan Number " +i +" : " +rcptNo);
				logger.info(" update Challan Amount " +i +" : " +lBDAmount);
				logger.info("update Challan Date " +i +" : " +challanDate);
				logger.info("update Challan Major Head " +i +" : " +majorHead);
							
				trnRcptDtlsVO.setReceiptNo(rcptNo);
				trnRcptDtlsVO.setAmount(lBDAmount);
				if(challanDate!=null)
				{
					trnRcptDtlsVO.setReceiptDate(new SimpleDateFormat("dd/MM/yyyy").parse(challanDate));
				}

				lHeadsFlag = lObjBudHdDtlsSrvcImpl.validateBudgetHeads(serv, lLngFinYearId, "", majorHead, "", "", "", "00", lLngLangId,"R");
				if(lHeadsFlag==true)
					trnRcptDtlsVO.setMajorHead(majorHead);
				else
					return -1;
							
				trnRcptDtlsVO.setUpdatedUserId(lLngUserId);
				trnRcptDtlsVO.setUpdatedPostId(lLngPostId);
				trnRcptDtlsVO.setUpdatedDate(lDtCurDate);			
				trnRcptDtlsVO.setLocationCode(lStrLocCode);
				trnRcptDtlsVO.setFinYearId(lLngFinYearId);		
				lObjRcptDtlsDAOImpl.update(trnRcptDtlsVO);
			}
		}
		catch(Exception e)
		{
			logger.error("Exception in updateReceiptDetails : " +e);
		}
		return 1;
	}
/*
			
			String lStrRcptMjrHd = StringUtility.getParameter("txtChallanMjrHead", request).length()>0 ? StringUtility.getParameter("txtChallanMjrHead", request) : lObjRcptDtlsVo.getMajorHead();
			BigDecimal lBdRcptAmt = (StringUtility.getParameter("txtChallanAmt", request).length()>0) ? new java.math.BigDecimal(StringUtility.getParameter("txtChallanAmt", request)) : lObjRcptDtlsVo.getAmount();
			String receiptDate = StringUtility.getParameter("txtChallanDate", request).length()>0 ? StringUtility.getParameter("txtChallanDate", request) : null;
			
			
			
			if(receiptDate!=null)//StringUtility.getParameter("txtChallanDate", request).length() > 0)
			{
//				String receiptDate = StringUtility.getParameter("txtChallanDate", request);	
				rcptDate = dateFormat.parse(receiptDate);				
			}
			
			if(lObjRcptDtlsVo.getReceiptNo()!=null && lObjRcptDtlsVo.getReceiptNo().length()>0)
				lStrReceiptNo = (StringUtility.getParameter("txtChallanNo", request).length()>0) ? StringUtility.getParameter("txtChallanNo", request) : lObjRcptDtlsVo.getReceiptNo();
			else
				lStrReceiptNo = (StringUtility.getParameter("txtChallanNo", request).length()>0) ? StringUtility.getParameter("txtChallanNo", request) : null;
			
			logger.info("Value of lStrReceiptNo in updateReceiptDetails : "+lStrReceiptNo);
			
			lObjRcptDtlsVo = lObjRcptDtlsDAOImpl.read(lLngRcptId); 
			
			lObjRcptDtlsVo.setAmount(lBdRcptAmt);
			logger.info("Value of Challan Amount in updateReceiptDetails : " +lObjRcptDtlsVo.getAmount());			
			Long lLngFinYearId = Long.valueOf(String.valueOf(lObjFinYearDAOImpl.getFinYearIdByCurDate()));
			
			lHeadsFlag = lObjBudHdDtlsSrvcImpl.validateBudgetHeads(serv, lLngFinYearId, "", lStrRcptMjrHd, "", "", "", "00", lLngLangId,"R");
			logger.info("Value of lHeadsFlag in setReceiptDetails in PhyBillDAOImpl : " +lHeadsFlag);
			if(lHeadsFlag==true)
				lObjRcptDtlsVo.setMajorHead(lStrRcptMjrHd);
			else
				return -1;
			lObjRcptDtlsVo.setReceiptNo(lStrReceiptNo);
			logger.info("Value of Challan No in update Receipt Details : " +lObjRcptDtlsVo.getReceiptNo());
			lObjRcptDtlsVo.setReceiptDate(rcptDate);			
			
			lObjRcptDtlsVo.setReceiptType(lObjRcptDtlsVo.getReceiptType());
			lObjRcptDtlsVo.setCreatedDate(lObjRcptDtlsVo.getCreatedDate());
			lObjRcptDtlsVo.setCreatedPostId(lObjRcptDtlsVo.getCreatedPostId());
			lObjRcptDtlsVo.setCreatedUserId(lObjRcptDtlsVo.getCreatedUserId());
			
			lObjRcptDtlsVo.setUpdatedDate(lDtCurDate);
			lObjRcptDtlsVo.setUpdatedPostId(lLngPostId);
			lObjRcptDtlsVo.setUpdatedUserId(lLngUserId);
			lObjRcptDtlsVo.setLocationCode(lStrLocCode);
			lObjRcptDtlsVo.setDbId(lLngDbId);
			logger.info("Before Update new receipts");
			lObjRcptDtlsDAOImpl.update(lObjRcptDtlsVo);
			logger.info("After Update new receipts");
		}
		catch(Exception e)
		{
			logger.error("Error occured in updateReceiptDetails : " +e,e);			
		}
		return 1;
	}
*/	
/*	public Long getReceiptDetailId(String lStrBillNo)
	{
		Session hibSession = getSession();
		Query query = hibSession.createSQLQuery("SELECT receipt_detail_id FROM trn_receipt_details t where receipt_detail_id in(select receipt_id from trn_bill_register where bill_no= " +lStrBillNo +")");
		//System.out.println("Query to getReceiptDetailId :  " +query);
		//System.out.println("Parameter Bill No : " +lStrBillNo);
		List lLstQuery = query.list();
		Long lLngRcptId = null;//new Long(0);
		if(lLstQuery.size() > 0)
		{
			Iterator it = lLstQuery.iterator();
			if(it.hasNext())
			{
				lLngRcptId = Long.parseLong((it.next().toString()));
			}
		}
		return lLngRcptId;
	}
*/	
/*	public Long getVersionId(String lStrBillNo)
	{
		Session hibSession = getSession();
		Query query = hibSession.createSQLQuery("select version_id from trn_bill_register where bill_no = " +lStrBillNo);
		logger.info("Query to get Version ID from trn_bill_register : " +query);
		logger.info("Parameter Bill No in getVersionId : " +lStrBillNo);
		List lLstResult = query.list();
		Iterator it= lLstResult.iterator();
		Long lLngVerId = new Long(1);
		while(it.hasNext())
		{
			Object obj = (Object) it.next();
			lLngVerId = Long.parseLong(obj.toString());
			lLngVerId++;
		}
		return lLngVerId;
	}	
*/	
	
	
	/**
	 *  to get all objections for bill
	 * @param  String : userId
	 * @param  Long : billNo
	 * 
	 * @return int
	 * @author vidhya
	 **/	
	
	public int getObjectionsForUser(String userId, Long billNo)
	{
		int lObjcCount =0;
		Session hibSession = getSession();
		Query query = hibSession.createSQLQuery(" SELECT COUNT(*) FROM RLT_BILL_OBJECTION WHERE BILL_NO= " +billNo);
		List lLstResult = query.list();
		Iterator it= lLstResult.iterator();		
		while(it.hasNext())
		{
			Object obj = (Object) it.next();
			if(obj != null)
			{
				lObjcCount = Integer.parseInt(obj.toString());
			}
		}
		return lObjcCount;
	}
	
	/**
	 * To get Treasury users when DDO sends bill to TO
	 * @param childHierarchy
	 * @param officeId
	 * @param refDesc
	 * @return List
	 * @author vidhya
	 */
	public List nextTsryUsers(List childHierarchy, Long officeId, String refDesc)
	{
		Session hibSession = getSession();
		String childHier ="";
		if(childHierarchy != null && childHierarchy.size()>0)
		{
			for(int i=0;i<childHierarchy.size()-1;i++)
			{
				childHier = childHier + childHierarchy.get(i).toString() +",";
			}
			childHier = childHier + childHierarchy.get(childHierarchy.size()-1).toString();
		}
		//System.out.println("========== child heirarchy------------- " + childHier);
		Query query = hibSession.createSQLQuery("SELECT POST_ID, LEVEL_ID,HP.HIERACHY_REF_ID  " +
				  "  FROM WF_HIERACHY_POST_MPG HP, WF_HIERARCHY_REFERENCE_MST HR  " +
				  " WHERE HP.HIERACHY_REF_ID IN ("+childHier+") AND HP.LOC_ID = "+officeId+" AND " +
				    "    HP.HIERACHY_REF_ID = HR.HIERACHY_REF_ID AND " +
				     "   HR.DESCRIPTION = '"+refDesc+"' AND "+
				      "  HP.LEVEL_ID = (SELECT MIN(LEVEL_ID) FROM WF_HIERACHY_POST_MPG H "+
				       "   WHERE H.HIERACHY_REF_ID = HP.HIERACHY_REF_ID)  " );
		List lLstResult = query.list();
		return lLstResult;
	}
	
	/**
	 * Method to update 'rpt_expenditure_dtls' table.
	 * @param  Map : inputMap
	 * @param  Long : lLngExpNo
	 * @param TrnBillRegister : lObjTrnBillRegsiter
	 * 
	 * @return void
	 * @author vidhya
	 */	
	public void updateRptExpDtls(Map inputMap, Long lLngExpNo, TrnBillRegister lObjTrnBillRegsiter)
	{
		logger.info("Inside  updateRptExpDtls");
		TrnBillRegister lObjTrnBillRegister = read(lLngExpNo);
		logger.info("Status in TrnBillRegister for updateRptExpDtls : " +lObjTrnBillRegister.getCurrBillStatus());
		String lStrExpTypeCode =  gObjRsrcBndle.getString("CMN.Bill");
		
		if(inputMap.get("TrnBudHeadDtls")!=null && inputMap.get("TrnBudHeadDtls").toString().length()>0)
		{
			logger.info("Setting Bud Head Vo in map");
			inputMap.put("TrnBudHeadDtls", inputMap.get("TrnBudHeadDtls"));
		}
		Map lMapPass = new HashMap();
		
		lMapPass.put("expNo", lLngExpNo);
		lMapPass.put("expTypeCode", lStrExpTypeCode);
		inputMap.put("lObjTrnBillRegisterVO", lObjTrnBillRegsiter);
		lMapPass.put("map",inputMap);
		DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
		RptExpenditureDtls lObjRptExpDtls = lObjDssDataSrvc.getExpData(lMapPass);
		logger.info("  --------------- VALUE OF RPT EXP DTLS IN updateRptExpDtls ---------");
		
		if(lObjRptExpDtls!=null)
		{
			lMapPass.put("RptExpenditureVO", lObjRptExpDtls);
			inputMap.put("RptExpenditureVO", lObjRptExpDtls);
			PhyBillVOGeneratorImpl lObjPhyBillVoGen = new PhyBillVOGeneratorImpl();
			
			lObjRptExpDtls = lObjPhyBillVoGen.generateTrnExpVO(inputMap);
			lMapPass.put("RptExpenditureVO", lObjRptExpDtls);
			inputMap.put("RptExpenditureVO", lObjRptExpDtls);
			lObjDssDataSrvc.updateExpData(lMapPass);
		}
	}

	/**
	 * Method to update 'rpt_receipt_dtls' table.
	 * @param  Map : inputMap
	 * @param  Long : lLngRcptNo
	 * @param  Long : lLngRcptId
	 * @param TrnBillRegister : lObjTrnBillRegsiter
	 * 
	 * @return void
	 * @author vidhya
	 */	
	public void updateRptRcptDtls(Map inputMap, Long lLngRcptNo, Long lLngRcptId, TrnBillRegister lObjTrnBillRegsiter)
	{
		String lStrRcptTypeCode = null;
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
//		TrnReceiptDetails lObjTrnRcptDtsl = new TrnReceiptDetails();
/*		TrnRcptDtlsDAOImpl lObjTrnRcptDao = new TrnRcptDtlsDAOImpl(TrnReceiptDetails.class,serv.getSessionFactory());
		logger.info("Value of PK for TRNRECEIPTDETAILS : " +lLngRcptId);
		TrnReceiptDetails lObjTrnRcptDtsl = lObjTrnRcptDao.read(lLngRcptId);
		logger.info("Value from TRNRECEIPTDETAILS VO : " +lObjTrnRcptDtsl.getAmount());
*/		if(lObjTrnBillRegsiter.getDemandCode().equals("999"))
		{
			lStrRcptTypeCode = gObjRsrcBndle.getString("CMN.Bill");
		}
		else
		{
			lStrRcptTypeCode = gObjRsrcBndle.getString("CMN.TcBill");			
		}
		String lStrChallanCatgCode = gObjRsrcBndle.getString("CMN.Others");
		List lLstRptRcptDtls = new ArrayList();
		
		if(inputMap.get("TrnBudHeadDtls")!=null && inputMap.get("TrnBudHeadDtls").toString().length()>0)
		{
			logger.info("Setting Bud Head Vo in map");
			inputMap.put("TrnBudHeadDtls", inputMap.get("TrnBudHeadDtls"));
		}
		
		Map lMapPass = new HashMap();
		lMapPass.put("rcptNo",lLngRcptNo);
		lMapPass.put("rcptTypeCode",lStrRcptTypeCode);
		lMapPass.put("trnReceiptId", lLngRcptId);
		lMapPass.put("challanCatgCode",lStrChallanCatgCode);
		lMapPass.put("map",inputMap);
		
		logger.info("lLngRcptNo : " +lLngRcptNo);
		logger.info("lStrRcptTypeCode : " +lStrRcptTypeCode);
		logger.info("lLngRcptId : " +lLngRcptId);
		logger.info("lStrChallanCatgCode : " +lStrChallanCatgCode);
		
		DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
		RptReceiptDtls lObjRptRcptDtls = null;
		
		lObjRptRcptDtls = lObjDssDataSrvc.getReceiptData(lMapPass);
		logger.info("Exp ID from DSSService : " +lObjRptRcptDtls);
		lLstRptRcptDtls.add(lObjRptRcptDtls);
		lMapPass.put("RptReceiptVOArrLst", lLstRptRcptDtls);
		inputMap.put("RptReceiptVOArrLst", lLstRptRcptDtls);
		lMapPass.put("RptReceiptVO",lObjRptRcptDtls);
		inputMap.put("RptReceiptVO",lObjRptRcptDtls);
		inputMap.put("lObjTrnBillRegisterVO", lObjTrnBillRegsiter);
		
//		inputMap.put("TrnReceiptDetailsVO", lObjTrnRcptDtsl);
		PhyBillVOGeneratorImpl lObjPhyBillVoGen = new PhyBillVOGeneratorImpl();	
		lObjRptRcptDtls = lObjPhyBillVoGen.generateRptRcptVO(inputMap);
		lLstRptRcptDtls.add(lObjRptRcptDtls);
		
		lMapPass.put("RptReceiptVOArrLst", lLstRptRcptDtls);
		inputMap.put("RptReceiptVOArrLst", lLstRptRcptDtls);
		lObjDssDataSrvc.updateReceiptData(lMapPass);
	}
	/**
	 * To delete RptReceipt Entries from DSS tables
	 * @param inputMap
	 * @param lLngRcptId
	 * @param lObjTrnBillRegsiter
	 * @return void
	 * @author vidhya
	 */
	
	public void deleteRptReceipt(Map inputMap, Long lLngRcptId, TrnBillRegister lObjTrnBillRegsiter)
	{
		String lStrRcptTypeCode = "";
		lStrRcptTypeCode = gObjRsrcBndle.getString("CMN.Bill");
		String lStrChallanCatgCode = gObjRsrcBndle.getString("CMN.Others");
		
		logger.info("lLngRcptNo : -1");
		logger.info("lStrRcptTypeCode" +lStrRcptTypeCode);
		logger.info("lLngRcptId" +lLngRcptId);
		logger.info("lStrChallanCatgCode" +lStrChallanCatgCode);
		
		Map lMapPass = new HashMap();
		lMapPass.put("rcptNo",-1);
		lMapPass.put("rcptTypeCode",lStrRcptTypeCode);
		lMapPass.put("trnReceiptId", lLngRcptId);
		lMapPass.put("challanCatgCode",lStrChallanCatgCode);
		
		lMapPass.put("map",inputMap);
		
		DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
		lObjDssDataSrvc.deleteReceiptData(lMapPass);
	}
	
	/**
	 * Inserting Rpt_expenditur_dtls for DSS reports
	 * @param servicemap
	 * @param lObjTrnBillRegisterVO
	 * @param lLngBillNo
	 * @return void
	 * @author vidhya
	 */
	public void insertExpData(Map servicemap, TrnBillRegister lObjTrnBillRegisterVO, Long lLngBillNo)
	{
		PhyBillVOGeneratorImpl lObjPhyBillVoGen = new PhyBillVOGeneratorImpl();
		RptExpenditureDtls lObjRptExpDtls = new RptExpenditureDtls();
		
		Map lMapNew = new HashMap();
		
		servicemap.put("lObjTrnBillRegisterVO", lObjTrnBillRegisterVO);
		lObjRptExpDtls = lObjPhyBillVoGen.generateTrnExpVO(servicemap);
		servicemap.put("RptExpenditureVO", lObjRptExpDtls);
		//System.out.println("Value of Bill nO for TrnExpVo : " +lLngBillNo);
		lObjRptExpDtls.setExpNo(new java.math.BigDecimal(lLngBillNo));
		
		Map returnDSS = lObjRptExpDtls.validateData();
		Boolean returnStatus  = (Boolean)returnDSS.get("flag");
		logger.info("Value of lObjRptExpDtls.validate() : " +returnStatus);
		if(returnStatus.booleanValue())
		{
			DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
			Map lMapPass = new HashMap();
			
			lMapPass.put("RptExpenditureVO", lObjRptExpDtls);
			lMapPass.put("map", servicemap);
			//System.out.println("Inside not equal to TC Bill");
			lMapNew = lObjDssDataSrvc.insertExpData(lMapPass);		
			logger.info("Value after insertion of RptExpenditure : " +lObjRptExpDtls.getExpId());
			logger.info("Map returned from  insertExpData : " +lMapNew.toString());
		}
	}
	/**
	 * Inserting into rpt_receipt_dtls for DSS reports for revenue
	 * @param servicemap
	 * @param lObjTrnBillRegisterVO
	 * @param lLngBillNo
	 * @return void
	 * @author vidhya
	 */
	public void insertRptRcptData(Map servicemap, TrnBillRegister lObjTrnBillRegisterVO, Long lLngBillNo)
	{
		DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
		PhyBillVOGeneratorImpl lObjPhyBillVoGen = new PhyBillVOGeneratorImpl();
		servicemap.put("lObjTrnBillRegisterVO", lObjTrnBillRegisterVO);
		//List lLstRptRcptVo = lObjPhyBillVoGen.generateRptRcptVO(servicemap);
		List lLstRptRcptVo = new ArrayList();
		//RptReceiptDtls lObjRptRcptDtls = (RptReceiptDtls)lLstRptRcptVo.get(0);
		RptReceiptDtls lObjRptRcptDtls = lObjPhyBillVoGen.generateRptRcptVO(servicemap);
		Map lMapNew = new HashMap();
		
		lObjRptRcptDtls.setRcptNo(new java.math.BigDecimal(-1));
		lObjRptRcptDtls.setTrnReceiptId(new java.math.BigDecimal(lLngBillNo));

		logger.info("Value of lObjRptRcptDtls[0].validate() : " +lObjRptRcptDtls.validateData());
		Map returnDSS1 = lObjRptRcptDtls.validateData();
		Boolean returnStatus1  = (Boolean)returnDSS1.get("flag");
		logger.info("Value of lObjRptRcptDtls[0].validate() : " +returnStatus1);
		
		if(returnStatus1.booleanValue())
		{							
			Map lMapNewPass = new HashMap();
			logger.info("expNo : " +lLngBillNo);

			lLstRptRcptVo.add(lObjRptRcptDtls);
			
			lMapNewPass.put("RptReceiptVOArrLst", lLstRptRcptVo);
			servicemap.put("RptReceiptVOArrLst", lLstRptRcptVo);
			
			lMapNewPass.put("map", servicemap);
			
			logger.info("Inside Demand is equal to 999");
			lMapNew = lObjDssDataSrvc.insertReceiptData(lMapNewPass);
			logger.info("Map returned from insertReceiptData : " +lMapNew.toString());
		}
	}
	/**
	 * Deleting from DSS tables
	 * @param inputMap
	 * @param lLngExpNo
	 * @param lObjTrnBillRegister
	 * @return void
	 * @author vidhya
	 */
	public void deleteRptExpDtls(Map inputMap, Long lLngExpNo,TrnBillRegister lObjTrnBillRegister)
	{
//		String lStrExpTypeCode = BptmCommonServiceImpl.getExpType(inputMap, gObjRsrcBndle.getString("CMN.Bill"));
		String lStrExpTypeCode = gObjRsrcBndle.getString("CMN.Bill");
		logger.info("Exp No in delete : " +lLngExpNo);
		logger.info("Exp Type Code in  delete : " +lStrExpTypeCode);
		
		Map lMapPass = new HashMap();
		lMapPass.put("expNo", lLngExpNo);
		lMapPass.put("expTypeCode", lStrExpTypeCode);
		
		inputMap.put("lObjTrnBillRegisterVO", lObjTrnBillRegister);
		lMapPass.put("map",inputMap);
				
		
		DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
		lObjDssDataSrvc.deleteExpData(lMapPass);
	}
	/**
	 * Getting next DDO Office users
	 * @param parentHierarchy
	 * @param billNo
	 * @return List
	 * @author vidhya
	 */
	public List nextDDOOfficeUsers(List parentHierarchy, Long billNo)
	{
		Session hibSession = getSession();
		String parentHier ="";
		if(parentHierarchy != null && parentHierarchy.size()>0)
		{
			for(int i=0;i<parentHierarchy.size()-1;i++)
			{
				parentHier = parentHier + parentHierarchy.get(i).toString() +",";
			}
			parentHier = parentHier + parentHierarchy.get(parentHierarchy.size()-1).toString();
		}
		//System.out.println("========== parent  heirarchy------------- " + parentHier);
		Query query = hibSession.createSQLQuery("SELECT POST_ID, LEVEL_ID,HIERACHY_REF_ID  "
						  +" FROM WF_HIERACHY_POST_MPG P  "+
						 " WHERE P.HIERACHY_REF_ID IN  ("+parentHier+") AND "+
						       " P.POST_ID IN (SELECT D.POST_ID FROM ORG_DDO_MST D, "+ 
						       " TRN_BILL_REGISTER R  WHERE D.DDO_CODE = R.DDO_CODE "+ 
						       " AND BILL_NO = "+billNo+" AND D.LANG_ID = 1)" );
		List lLstResult = query.list();
		return lLstResult;
	}	 	
	
	//added by Ankit Bhatt
	public List getBillData()
	{
		Session hibSession = getSession();
		String parentHier ="";
		
		Query query = hibSession.createSQLQuery("SELECT * FROM TRN_BILL_REGISTER");
		List lLstResult = query.list();
		return lLstResult;
	}
}
