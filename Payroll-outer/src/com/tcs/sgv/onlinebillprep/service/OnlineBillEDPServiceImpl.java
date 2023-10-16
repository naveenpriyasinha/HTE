package com.tcs.sgv.onlinebillprep.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.BillEdpDAO;
import com.tcs.sgv.common.dao.BillEdpDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.MstEdp;
import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;
import com.tcs.sgv.exprcpt.dao.VoucherDAO;
import com.tcs.sgv.exprcpt.dao.VoucherDAOImpl;
import com.tcs.sgv.exprcpt.helper.DSSHelper;


/**
 * 
 * @author: Vipul Patel(602399)
 * 
 */

public class OnlineBillEDPServiceImpl extends ServiceImpl 
				implements OnlineBillEDPService
{
	 private Log logger = LogFactory.getLog(getClass());

	  /* Global Variable for PostId */
	  private Long gLngPostId = null;

	  /* Global Variable for UserId */
	  private Long gLngUserId = null;

	  /* Global Variable for LangId */
	  private Long gLngLangId = null;

	  /* Global Variable for DB Id */
	  private Long gLngDBId = null;

	  /* Global Variable for Location Code */
	  private String gStrLocationCode = null;
	  /* Global variable for financial year id */
	  
	  static NumberFormat df =  NumberFormat.getInstance();
	  
	  
	  /**
	   * @author Vipul Patel (602399) 
	   * @param objectArgs
	   * @return ResultObject
	   * Note: Service Name is 'ONLINE_BILL_EDP_DETAILS'
	   */
	  public void getEdpDtlsByBillType(Map objectArgs) throws Exception 
	  {
	    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
	    
	    try
	    {
	      setSessionInfo(objectArgs);
	      Long billNo=null;
	      String strBillNo=null;
	      String tcBill=null;
	      String sRcpMajorHead=null;
	      strBillNo=request.getParameter("BillNo");
	      //when called from onlinebillserviceimpl - getbilldata method - Change Start
	      if(strBillNo == null)
	      {
	        strBillNo = (String)objectArgs.get("billNo");
	      }
	      //Change End
	      if(strBillNo!=null)
	      {
	        billNo = Long.parseLong(strBillNo);
	      }
	      if(objectArgs.get("tcBill")!=null)
	      {
	        tcBill = (String)objectArgs.get("tcBill");
	      }
	      if(objectArgs.get("majorHead")!=null)
	      {
	        sRcpMajorHead = (String)objectArgs.get("majorHead");
	      }
	      
	      Long subjectId=null;
	      subjectId=Long.parseLong(objectArgs.get("subjectId").toString());
	      BillEdpDAO billEdpDAO = new BillEdpDAOImpl(TrnBillEdpDtls.class,serv.getSessionFactory());
	      objectArgs.put("rcptObjHds",billEdpDAO.getRcptEdpDtlByBillType(subjectId, billNo,gLngLangId,gStrLocationCode,tcBill,sRcpMajorHead));
	      objectArgs.put("expObjHds",billEdpDAO.getExpObjHdDtlByBillType(subjectId, billNo,gLngLangId,gStrLocationCode));
	      objectArgs.put("recObjHds",billEdpDAO.getRecObjHdDtlByBillType(subjectId, billNo,gLngLangId,gStrLocationCode));
	      List expEdpList=null;//billEdpDAO.getExpEdpDtl(subjectId,gLngLangId,gStrLocationCode,tcBill,sRcpMajorHead);
	      
	      objectArgs.put("expEdpList", expEdpList);
	      
	      if(billNo!=null)
	      {
	       /* objectArgs.put("expObjHdsAdded",billEdpDAO.getExpObjHdDtlByBillNo(billNo,gLngLangId,gStrLocationCode));
	        objectArgs.put("recObjHdsAdded",billEdpDAO.getRecObjHdDtlByBillNo(billNo,gLngLangId,gStrLocationCode));
	        objectArgs.put("rcptObjHdsAdded",billEdpDAO.getRcptObjHdDtlByBillNo(billNo,gLngLangId,gStrLocationCode));*/
	      }
	    } 
	    catch(Exception e)
	    {
	      logger.error("Error is " + e);
	      throw e;
	    }
	  }
	  
	 
	  /**
	   * @author Vipul Patel (602399) 
	   * @param inputMap
	   * @return void 
	   * Note: Service Name is 'GENERATE_ONLINE_BILL_EDP_VO'
	   */
	  public void generateEdpDtlsMap(Map p_objServiceArgs) throws Exception 
	  {
	    HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
	    
	    try
	    {
	      setSessionInfo(p_objServiceArgs);		
	      String billNo=(String) p_objServiceArgs.get("billNo");
	      String payEdpId[] =StringUtility.getParameterValues("hdPayEdpId", request);
	      String payAddDed[] =StringUtility.getParameterValues("hdPayAddDed", request);
	      String payEdpCat[] =StringUtility.getParameterValues("hdPayEdpCate", request);
	      String payEdpCode[] =StringUtility.getParameterValues("hdPayEdpCode", request);
	      List payVOlist=new ArrayList();
	      
	      for(int i=0;i<payEdpId.length;i++)
	      {
	        String amount=StringUtility.getParameter("txtAmt"+payEdpCode[i], request);
	        if(Double.parseDouble(amount)!=0)
	        {
	          TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
	          billEdpVO.setTrnCounter(Integer.valueOf(1));
	          billEdpVO.setTypeEdpId(Long.parseLong(payEdpId[i]));
	          billEdpVO.setBillNo(Long.parseLong(billNo));
	          billEdpVO.setEdpAmt(new BigDecimal(amount));
	          billEdpVO.setLocationCode(gStrLocationCode);
	          billEdpVO.setDbId(gLngDBId);
	          billEdpVO.setAddDedFlag(payAddDed[i]);
	          billEdpVO.setEdpCategory(payEdpCat[i]);
	          billEdpVO.setEdpCode(payEdpCode[i]);
	          billEdpVO.setExpRcpRec("EXP");
	          billEdpVO.setAutoAdd("Y");
	          payVOlist.add(billEdpVO);
	        }
	      }
	      
	      String expRows[] = StringUtility.getParameterValues("exprows", request);		
	      for(int i=0;i<expRows.length;i++)
	      {
	        String edpCode=StringUtility.getParameter("txtEdpCode"+expRows[i], request);
	        String addDedFlag=StringUtility.getParameter("txtAddDed"+expRows[i], request);
	        String edpCategory=StringUtility.getParameter("txtDedType"+expRows[i], request);
	        String amount=StringUtility.getParameter("txtAmt"+expRows[i], request);
	        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
	        if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
	        {
	          billEdpVO=new TrnBillEdpDtls();
	          billEdpVO.setTrnCounter(Integer.valueOf(1));
	        }
	        if(Double.parseDouble(amount)!=0)
	        {
	          if(edpCode!=null)
	          {
	            if(!edpCode.trim().equalsIgnoreCase(""))
	            {
	              billEdpVO.setBillNo(Long.parseLong(billNo));
	              billEdpVO.setEdpAmt(new BigDecimal(amount));
	              billEdpVO.setLocationCode(gStrLocationCode);
	              billEdpVO.setDbId(gLngDBId);
	              billEdpVO.setAddDedFlag(addDedFlag);
	              billEdpVO.setEdpCategory(edpCategory);
	              billEdpVO.setEdpCode(edpCode);
	              billEdpVO.setExpRcpRec("EXP");
	              billEdpVO.setAutoAdd("N");
	              payVOlist.add(billEdpVO);
	            }
	          }
	        }
	      }

	      // Recovery

	      String recEdpId[] =StringUtility.getParameterValues("hdRecEdpId", request);
	      String recAddDed[] =StringUtility.getParameterValues("hdRecAddDed", request);
	      String recEdpCat[] =StringUtility.getParameterValues("hdRecEdpCate", request);
	      String recEdpCode[] =StringUtility.getParameterValues("hdRecEdpCode", request);
	      List recVOlist=new ArrayList();
	      for(int i=0;i<recEdpId.length;i++)
	      {

	        String amount=StringUtility.getParameter("txtRecAmt"+recEdpCode[i], request);
	        if(Double.parseDouble(amount)!=0)
	        {
	          TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
	          billEdpVO.setTrnCounter(Integer.valueOf(1));
	          billEdpVO.setTypeEdpId(Long.parseLong(recEdpId[i]));
	          billEdpVO.setBillNo(Long.parseLong(billNo));
	          billEdpVO.setEdpAmt(new BigDecimal(amount));
	          billEdpVO.setLocationCode(gStrLocationCode);
	          billEdpVO.setDbId(gLngDBId);
	          billEdpVO.setAddDedFlag(recAddDed[i]);
	          billEdpVO.setEdpCategory(recEdpCat[i]);
	          billEdpVO.setEdpCode(recEdpCode[i]);
	          billEdpVO.setExpRcpRec("REC");
	          billEdpVO.setAutoAdd("Y");
	          recVOlist.add(billEdpVO);
	        }
	      }
	      
	      String recRows[] = StringUtility.getParameterValues("recRows", request);		
	      for(int i=0;i<recRows.length;i++)
	      {
	        String reEdpCode=null;
	        reEdpCode=StringUtility.getParameter("txtRecEdpCode"+recRows[i], request);
	        String recAddDedFlag=StringUtility.getParameter("txtRecAddDed"+recRows[i], request);
	        String recEdpCategory=StringUtility.getParameter("txtRecDedType"+recRows[i], request);
	        String recAmount=StringUtility.getParameter("txtRecAmt"+recRows[i], request);
	        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
	        if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
	        {
	          billEdpVO=new TrnBillEdpDtls();
	          billEdpVO.setTrnCounter(Integer.valueOf(1));
	        }
	        if(Double.parseDouble(recAmount)!=0)
	        {
	          if(reEdpCode!=null)
	          {
	            if(!reEdpCode.trim().equalsIgnoreCase(""))
	            {	
	              billEdpVO.setBillNo(Long.parseLong(billNo));
	              billEdpVO.setEdpAmt(new BigDecimal(recAmount));
	              billEdpVO.setLocationCode(gStrLocationCode);
	              billEdpVO.setDbId(gLngDBId);
	              billEdpVO.setAddDedFlag(recAddDedFlag);
	              billEdpVO.setEdpCategory(recEdpCategory);
	              billEdpVO.setEdpCode(reEdpCode);
	              billEdpVO.setExpRcpRec("REC");
	              billEdpVO.setAutoAdd("N");
	              recVOlist.add(billEdpVO);
	            }
	          }
	        }
	      }

	      // Receipt

	      String rcptEdpId[] =StringUtility.getParameterValues("hdRcptEdpId", request);
	      String rcptEdpCat[] =StringUtility.getParameterValues("hdRcptEdpCate", request);
	      String rcptEdpCode[] =StringUtility.getParameterValues("hdRcptEdpCode", request);
	      List rcptVOlist=new ArrayList();
	      
	      for(int i=0;i<rcptEdpId.length;i++)
	      {
	        String amount=StringUtility.getParameter("txtAmt"+rcptEdpCode[i], request);
	        if(Double.parseDouble(amount)!=0)
	        {
	          TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
	          billEdpVO=new TrnBillEdpDtls();
	         
	        /*  billEdpVO.setBuddtlhdCode(rcptEdpCode[i]);
	          billEdpVO.setBudminhdCode(StringUtility.getParameter("txtRcptMinHdCodeFd" + i, request));
        	  billEdpVO.setBudmjrhdCode(StringUtility.getParameter("txtRcptMjrHdCodeFd" + i, request));
        	  billEdpVO.setBudsubhdCode(StringUtility.getParameter("txtRcptSubHdCodeFd" + i, request));
        	  billEdpVO.setBudsubmjrhdCode(StringUtility.getParameter("txtRcptSubMjrHdCodeFd" + i, request));
        	  */
	          billEdpVO.setTrnCounter(Integer.valueOf(1));
	          billEdpVO.setTypeEdpId(Long.parseLong(rcptEdpId[i]));
	          billEdpVO.setBillNo(Long.parseLong(billNo));
	          billEdpVO.setEdpAmt(new BigDecimal(amount));
	          billEdpVO.setLocationCode(gStrLocationCode);
	          billEdpVO.setDbId(gLngDBId);
	          billEdpVO.setAddDedFlag("-");
	          billEdpVO.setEdpCategory(rcptEdpCat[i]);
	          billEdpVO.setEdpCode(rcptEdpCode[i]);
	          billEdpVO.setExpRcpRec("RCP");
	          billEdpVO.setAutoAdd("Y");
	          rcptVOlist.add(billEdpVO);
	        }
	      }
	      
	      String rcptRows[] = StringUtility.getParameterValues("rcptRows", request);	
	      
	      for(int i=0;i<rcptRows.length;i++)
	      {
	        String rptEdpCode=StringUtility.getParameter("txtRcptEdpCode"+rcptRows[i], request);
	        String rcptEdpCategory=StringUtility.getParameter("txtRcptDedType"+rcptRows[i], request);
	        String rcptAmount=StringUtility.getParameter("txtRcptAmt"+rcptRows[i], request);
	        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
	        if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
	        {
	          billEdpVO=new TrnBillEdpDtls();
	          billEdpVO.setTrnCounter(Integer.valueOf(1));
	        }
	        if(Double.parseDouble(rcptAmount)!=0)
	        {
	          if(rptEdpCode!=null)
	          {
	            if(!rptEdpCode.trim().equalsIgnoreCase(""))
	            {
	              billEdpVO.setBillNo(Long.parseLong(billNo));
	              billEdpVO.setEdpAmt(new BigDecimal(rcptAmount));
	              //Commented by vipul for 9999 if(rptEdpCode.trim().equalsIgnoreCase("9999"))
	              {
	            	  /*billEdpVO.setBuddtlhdCode(rptEdpCode);
	            	  billEdpVO.setBudminhdCode(StringUtility.getParameter("txtRcptMinHdCode"+rcptRows[i], request));
	            	  billEdpVO.setBudmjrhdCode(StringUtility.getParameter("txtRcptMjrHdCode"+rcptRows[i], request));
	            	  billEdpVO.setBudsubhdCode(StringUtility.getParameter("txtRcptSubHdCode"+rcptRows[i], request));
	            	  billEdpVO.setBudsubmjrhdCode(StringUtility.getParameter("txtRcptSubMjrHdCode"+rcptRows[i], request));*/
	              }
	              billEdpVO.setLocationCode(gStrLocationCode);
	              billEdpVO.setDbId(gLngDBId);
	              billEdpVO.setAddDedFlag("-");
	              billEdpVO.setEdpCategory(rcptEdpCategory);
	              billEdpVO.setEdpCode(rptEdpCode);
	              billEdpVO.setExpRcpRec("RCP");
	              billEdpVO.setAutoAdd("N");
	              rcptVOlist.add(billEdpVO);
	            }
	          }
	        }
	      }
	      
	      p_objServiceArgs.put("payVOlist", payVOlist);
	      p_objServiceArgs.put("recVOlist", recVOlist);
	      p_objServiceArgs.put("rcptVOlist", rcptVOlist);
	    }
	    catch(Exception e)
	    {
	        logger.error("Error is " + e);
	        throw e;
	    }
	  }
	  
	  
	  /**
	   * @author Vipul Patel (602399) 
	   * @param inputMap
	   * @return ResultObject 
	   * Note: Service Name is 'INSERT_ONLINE_BILL_EDP_DTLS'
	   */
	  public void insertEdpDtls(Map objectArgs) throws Exception 
	  {
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	    List PayVOList=(List) objectArgs.get("payVOlist");
	    List recVOList=(List) objectArgs.get("recVOlist");
	    List rcptVOList=(List) objectArgs.get("rcptVOlist");
	    
	    TrnBillRegister billRegVO=null;
	    
	    try
	    {
	    	if(objectArgs.containsKey("BillRegVO"))
		    {
		    	billRegVO=(TrnBillRegister)objectArgs.get("BillRegVO");
		    }
	    	
	    	  setSessionInfo(objectArgs);
		      BillEdpDAO billEdpImpl = new BillEdpDAOImpl(TrnBillEdpDtls.class,serv.getSessionFactory());
		      DssDataServiceImpl servImpl = new DssDataServiceImpl();
		      
		      List expEdpVOArrLstInsert=new ArrayList();
		      List rcptEdpArrLstInsert=new ArrayList();
		    
		      objectArgs.put("map",objectArgs);
		      objectArgs.put("expTypeCode", DSSHelper.EXP_TYPE_BILL);
		      objectArgs.put("rcptTypeCode", DSSHelper.RCPT_TYPE_BILL);
		      objectArgs.put("challanCatgCode", DSSHelper.CHALLAN_CATE_CODE);
		      
		      for(int i=0;i<PayVOList.size();i++)
		      {
		        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) PayVOList.get(i);
		        Long billEdpId = IFMSCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
		        billEdpVo.setBillEdpId(billEdpId);
		        billEdpVo.setCreatedUserId(gLngUserId);
		        billEdpVo.setCreatedPostId(gLngPostId);
		        billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
		        //billEdpImpl.create(billEdpVo);
	
		        /** code for DSS insert arraylist*/
		        if(billRegVO!=null)
		        {
		          if(billRegVO.getDemandCode().equals("999") && (Long.parseLong(billRegVO.getBudmjrHd()) > 8000))
		          {
		        	  Map rcptDssMap=new HashMap();
		              rcptDssMap.put("map",objectArgs);
		              rcptDssMap.put("rcptNo",Long.valueOf(-1));
		              rcptDssMap.put("rcptTypeCode",DSSHelper.RCPT_TYPE_BILL);
		              rcptDssMap.put("challanCatgCode",DSSHelper.CHALLAN_CATE_CODE);
		              rcptDssMap.put("trnReceiptId",billEdpVo.getBillNo());
		              RptReceiptDtls rptReceiptVO=servImpl.getReceiptData(rcptDssMap);
		              DSSHelper.updateRptEdpCodeReceiptVO(rptReceiptVO,billEdpVo);
		              rcptDssMap.put("RptReceiptVO",rptReceiptVO);
		              Map resultMap=servImpl.updateReceiptData(rcptDssMap);
		          }
		          else
		          {
		        	  objectArgs.put("expNo", billEdpVo.getBillNo());
		              objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
		              MstEdp mstEdpVo=null;//billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),gLngLangId);
		              RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
		              expEdpVOArrLstInsert.add(rptExpEdpVo);
		          }
		        }
		        else
		        {
		          objectArgs.put("expNo", billEdpVo.getBillNo());
		          objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
		          MstEdp mstEdpVo=null;//billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),gLngLangId);
		          RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
		          expEdpVOArrLstInsert.add(rptExpEdpVo);
		        }
		        /** End of DSS Code */
		      }
		     
		      for(int i=0;i<recVOList.size();i++)
		      {
		        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) recVOList.get(i);
	
		        Long billEdpId = IFMSCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
		        billEdpVo.setBillEdpId(billEdpId);
		        billEdpVo.setCreatedUserId(gLngUserId);
		        billEdpVo.setCreatedPostId(gLngPostId);
		        billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
		        //billEdpImpl.create(billEdpVo);	
	
		        /** code for DSS insert arraylist*/
	
		        objectArgs.put("expNo", billEdpVo.getBillNo());
		        objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
		        MstEdp mstEdpVo=null;//billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),gLngLangId);
	
		        RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
		        expEdpVOArrLstInsert.add(rptExpEdpVo);
		      }
	
		      for(int i=0;i<rcptVOList.size();i++)
		      {
		        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) rcptVOList.get(i);
		        Long billEdpId = IFMSCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
		        billEdpVo.setBillEdpId(billEdpId);
		        billEdpVo.setCreatedUserId(gLngUserId);
		        billEdpVo.setCreatedPostId(gLngPostId);
		        billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
		        //billEdpImpl.create(billEdpVo);
	
		        /** code for DSS insert arraylist*/
	
		        objectArgs.put("rcptNo", billEdpVo.getBillNo());
		        objectArgs.put("trnReceiptId", billEdpVo.getBillEdpId());
		        MstEdp mstEdpVo=null;//billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),gLngLangId);
	
		        RptReceiptDtls rptRceiptVO=null;//DSSHelper.createRptReceiptVOForOnLine(billEdpVo, mstEdpVo,billRegVO);
		        rcptEdpArrLstInsert.add(rptRceiptVO);
		        /** End of DSS Code */	
		      }
		      if(billRegVO!=null)
		      {
		        if(billRegVO.getDemandCode().equals("999") && (Long.parseLong(billRegVO.getBudmjrHd()) > 8000))
		        {
		        	if(expEdpVOArrLstInsert!=null && !expEdpVOArrLstInsert.isEmpty() )
		            {
		              objectArgs.put("RptExpEdpVOArrLst",expEdpVOArrLstInsert);
		              Map returnMap=servImpl.insertExpEdpData(objectArgs);
		            }
		        }
		        else
		        {
		        	if(expEdpVOArrLstInsert!=null && !expEdpVOArrLstInsert.isEmpty())
		            {
		              objectArgs.put("RptExpEdpVOArrLst",expEdpVOArrLstInsert);
		              Map returnMap=servImpl.insertExpEdpData(objectArgs);
		            }
		        }
		      }
		      else
		      {
		        if(expEdpVOArrLstInsert!=null && !expEdpVOArrLstInsert.isEmpty())
		        {
		          objectArgs.put("RptExpEdpVOArrLst",expEdpVOArrLstInsert);
		          Map returnMap = servImpl.insertExpEdpData(objectArgs);
		        }
		      }
		      if(rcptEdpArrLstInsert!=null && !rcptEdpArrLstInsert.isEmpty())
		      {
		        objectArgs.put("RptReceiptVOArrLst",rcptEdpArrLstInsert);
		        Map returnMap = servImpl.insertReceiptData(objectArgs);
		      }
		} 
	    catch(Exception e)
	    {
	      logger.error("Error is " + e);
	      throw e;
	    }
	  }
	    
	  /**
	   * @author Vipul Patel (602399) 
	   * @param inputMap
	   * @return ResultObject 
	   * Note: Service Name is 'DELETE_EXT_EDP_CODE'
	   */
	  public void deleteExtAddEdpCode(Map objectArgs) throws Exception 
	  {
		  logger.info("start");
		  
		  HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		  ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		  
		  try
		  {
			  Long billNo = null;
			  String strBillNo = StringUtility.getParameter("hidBillNo", request);
		      
		      if(strBillNo != null && strBillNo.trim().length() > 0)
		      {
		          billNo = Long.parseLong(strBillNo);
		      }
		      
		      
		      if (billNo != null) 
		      {
		    	  setSessionInfo(objectArgs);
		    	  VoucherDAO voucherImpl = null;//new VoucherDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
			     // voucherImpl.deleteExtAddEdpCode(billNo, gStrLocationCode);
			  }
		  }
		  catch(Exception e)
		  {
			  logger.error("Error is " + e);
			  throw e;
		  }
	    
		  logger.info("End");
	  }
	  
	  
	  /**
	   * @author Vipul Patel (602399) 
	   * @param inputMap
	   * @return void 
	   */
	  private void setSessionInfo(Map inputMap) throws Exception 
	  {
		  try
		  {
			  if(gLngLangId == null)
			  {
			      gLngLangId = SessionHelper.getLangId(inputMap);
			      gLngPostId = SessionHelper.getPostId(inputMap);
			      gLngUserId = SessionHelper.getUserId(inputMap);
			      gLngDBId = SessionHelper.getDbId(inputMap);
			      gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			      
			  }
		  }
		  catch(Exception e)
		  {
			  logger.error("Error is " + e);
			  throw e;
		  }
	  }
}
