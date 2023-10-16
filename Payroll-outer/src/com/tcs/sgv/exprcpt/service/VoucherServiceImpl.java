package com.tcs.sgv.exprcpt.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.billproc.counter.service.PhyBillVOGeneratorImpl;
import com.tcs.sgv.billproc.counter.valueobject.NewBillVO;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.constant.WorkFlowConstants;
import com.tcs.sgv.common.dao.BillEdpDAOImpl;
import com.tcs.sgv.common.dao.BillMovementDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.BudHeadDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.dao.OrganizationDAOImpl;
import com.tcs.sgv.common.dao.PostConfigurationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.helper.WorkFlowHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.BudgetHdServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.ConfigAudSelection;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.common.valueobject.MstEdp;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;
import com.tcs.sgv.exprcpt.dao.LOPLORFormulaDAOImpl;
import com.tcs.sgv.exprcpt.dao.VerifiedAccountDAOImpl;
import com.tcs.sgv.exprcpt.dao.VoucherDAOImpl;
import com.tcs.sgv.exprcpt.helper.DSSHelper;
import com.tcs.sgv.exprcpt.report.ReportQueryDAOImpl;
import com.tcs.sgv.exprcpt.valueobject.DistVoucherVO;
import com.tcs.sgv.exprcpt.valueobject.MstListPayRcpt;
import com.tcs.sgv.exprcpt.valueobject.TrnVerifiedAccount;
import com.tcs.sgv.exprcpt.valueobject.TrnVoucherDetails;
import com.tcs.sgv.exprcpt.valueobject.VoucherVO;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

/**
 * ClassName VoucherServiceImpl
 * 
 * Description Implementaton of VoucherService
 * Date of Creation 26 July 2007
 *  
 * @author 219480
 *
 * Revision History:
 * Bhavesh 30 Aug 2007
 *
 */

public class VoucherServiceImpl extends ServiceImpl implements VoucherService 
{
  Log logger = LogFactory.getLog(getClass());

  /**
   * This method returns a List<VoucherVO.class> of all vouchers for acceptance in book branch 
   * in a Map as resultValue
   * @param objectArgs Map consisting of requestObj(HttpServletRequest), serviceLocator(ServiceLocator)
   * @return ResultObject	 
   */
  public ResultObject getVouchers(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    Long postId=null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());

      /* get vouchers(bills) from work flow .. */
      postId = SessionHelper.getPostId(objectArgs);
      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);
      logger.info("postLst " + objectArgs.get("postLst"));
      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);

      /* get list of vouchers that are not accepted .. */
      List voucherList = voucherImpl.getVouchers(postId, billLst,SessionHelper.getLangId(objectArgs)
          ,SessionHelper.getLocationCode(objectArgs));						
      objectArgs.put("voucherList",voucherList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("accVouhsFromCB");			
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getVouchers # \n"+e);
    }
    return objRes;
  }

  /**
   * This method update the accepted vouchers and returns a List<VoucherVO.class> of 
   * all vouchers for acceptance in book branch in a Map as resultValue
   * @param objectArgs Map consisting of requestObj(HttpServletRequest), serviceLocator(ServiceLocator)
   * @return ResultObject	 
   */
  public ResultObject accVouchersFromChqBranch(Map objectArgs) {	
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    VoucherDAOImpl voucherImpl=null;
    Long userId=null;
    Long postId=null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }

      userId = SessionHelper.getUserId(request);
      postId  = SessionHelper.getPostId(objectArgs);
      Map objectArg = new HashMap();
      objectArg.put("billsNo",request.getParameterValues("vouchNo"));
      objectArg.put("userId",userId);
      objectArg.put("postId",postId);
      objectArg.put("slocCode", SessionHelper.getLocationCode(objectArgs));

      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      /* get vouchers(bills) from work flow .. */

      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);
      logger.info("postLst " + objectArgs.get("postLst"));
      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);

      /* Update the accepted Bills .. */
      voucherImpl.accVouchersFromChqBranch(objectArg);
      /* get list of vouchers that are not accepted .. */
      List voucherList = voucherImpl.getVouchers(postId, billLst,SessionHelper.
          getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs));	
      Map result = new HashMap();
      logger.info("\n\n**************Voucher List***** " + voucherList);
      result.put("voucherList",voucherList);
      objRes.setResultValue(result);                           
      objRes.setViewName("accVouhsFromCB");
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");	
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.accVouchersFromChqBranch # \n"+e);
    }
    return objRes;
  }

  /**
   * This method returns a List<VoucherVO.class> of all vouchers for Distribution in detail posting in a Map as resultValue
   * @param objectArgs Map consisting of requestObj(HttpServletRequest), serviceLocator(ServiceLocator)
   * @return ResultObject	 
   */
  public ResultObject getVouchersForDist(Map objectArgs) {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    Long postId = null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      /* get vouchers(bills) from work flow .. */
      postId  = SessionHelper.getPostId(objectArgs);
      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);
      logger.info("postLst " + objectArgs.get("postLst"));
      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);

      VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      PostConfigurationDAOImpl postConfigDAO = new PostConfigurationDAOImpl(ConfigAudSelection.class,serv.getSessionFactory());

      /* get major head wise voucher list .. */
      List voucherList = voucherImpl.getVouchersForDist(postId, billLst,SessionHelper.getLocationCode(objectArgs));

      /* get default employee and post associated with each major head .. */
      if (voucherList!=null) 
      {
        Iterator it = voucherList.iterator();				
        Map  map = new HashMap();
        while(it.hasNext()) {
          DistVoucherVO distVO = (DistVoucherVO)it.next();
          if(distVO!=null){						
            map.put("majorHead", distVO.getMajorHead());						
            distVO.setEmpPostV0(postConfigDAO.getSelectedEmp(SessionHelper.getLocationCode(objectArgs), DBConstants.BRCH_ID_BOOK_PAYMENT, map));
          }
        }
      }

      /* get total no of un distributed vouchers .. */
      int noOfVouchers = voucherImpl.countVouchersDist(postId, billLst,SessionHelper.getLocationCode(objectArgs));

      Map result = new HashMap();
      logger.info("\n\n**************Voucher List***** " + voucherList);
      result.put("voucherList",voucherList);
      result.put("noOfVouchers",noOfVouchers);
      result.put("empsList",postConfigDAO.getEmpsList(SessionHelper.getLocationCode(objectArgs), DBConstants.BRCH_ID_BOOK_PAYMENT));
      objRes.setResultValue(result);
      objRes.setViewName("distVouhsForDetPost");			
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getVouchersForDist # \n"+e);
    }
    return objRes;
  }

  /**
   * This method returns a List<BillMovementVO.class> of all vouchers for distribution of detail posting in a Map as resultValue
   * @param objectArgs Map consisting of requestObj(HttpServletRequest), serviceLocator(ServiceLocator)
   * @return ResultObject	 
   */
  public ResultObject distributeVouchers(Map objectArgs) {	
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    VoucherDAOImpl voucherImpl=null;
    Long postId = null;
    Long userId = null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      userId = SessionHelper.getUserId(request);
      long dbId = SessionHelper.getDbId(objectArgs);
      String locCode = SessionHelper.getLocationCode(objectArgs);

      String majorHeads[] = request.getParameterValues("chkMajorHead");
      Map mjrHdMap = new HashMap();
      if(majorHeads!=null){
        for(int i=0;i<majorHeads.length;i++) {
          try {mjrHdMap.put(majorHeads[i], new Long(request.getParameter("cmb_"+majorHeads[i])));} catch(Exception e){e.printStackTrace();}
        }
      }

      /* get vouchers(bills) from work flow .. */
      postId  = SessionHelper.getPostId(objectArgs);

      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);

      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);

      /* get all undistributed vouchers  .. */
      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      List billList = voucherImpl.getUndistributedVouchers(postId, billLst,SessionHelper.getLocationCode(objectArgs));
      logger.info("SIZE : " + billList.size());
      if (billList!=null && billList.size()>0 && majorHeads!=null) 
      {
        Iterator it=billList.iterator();
        OrganizationDAOImpl orgDAO = new OrganizationDAOImpl(Object.class,serv.getSessionFactory());
        PhyBillDAOImpl phyDAO = new PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());


        while(it.hasNext())
        {
          TrnVoucherDetails trnVouchVo=(TrnVoucherDetails)it.next();				
          Long hrchyPostId = (Long) mjrHdMap.get(trnVouchVo.getMajorHead());

          if (hrchyPostId!=null) 
          {
            TrnBillRegister billReg = phyDAO.read(trnVouchVo.getBillNo());
            Long hrchyUserId = orgDAO.getUserByPostId(hrchyPostId);
            /* create bill movement ... */					
            TrnBillMvmnt billMvnVo=new TrnBillMvmnt();
            billMvnVo.setBillNo(trnVouchVo.getBillNo());		
            billMvnVo.setStatusUpdtUserid(hrchyUserId);
            billMvnVo.setStatusUpdtPostid(hrchyPostId);
            billMvnVo.setStatusUpdtDate(new Date(System.currentTimeMillis()));
            billMvnVo.setMvmntStatus(DBConstants.ST_VCHR_DSTRBTD);
            billMvnVo.setReceivedFlag(new Short("0"));
            billMvnVo.setCreatedUserId(userId);
            billMvnVo.setCreatedPostId(postId);
            billMvnVo.setCreatedDate(new Date(System.currentTimeMillis()));
            billMvnVo.setUpdatedUserId(userId);
            billMvnVo.setUpdatedPostId(postId);
            billMvnVo.setUpdatedDate(new Date(System.currentTimeMillis()));
            billMvnVo.setLocationCode(locCode);
            billMvnVo.setDbId(dbId);
            objectArgs.put("BillMovementVO", billMvnVo);
            /** create workflow object .. */
            WorkFlowVO workFlowVO = new WorkFlowVO();
            workFlowVO.setAppMap(objectArgs);			
            if(SessionHelper.getEmpId(objectArgs)!=null) workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
            if(SessionHelper.getPostId(objectArgs)!=null) workFlowVO.setCrtPost(SessionHelper.getPostId(objectArgs).toString());
            if(SessionHelper.getUserId(request)!=null) workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
            logger.info("TO POST : " + Long.toString(hrchyPostId));
            workFlowVO.setActId(WorkFlowConstants.WF_FORWARD);
            if (billReg.getPhyBill()==1) workFlowVO.setDocId(WorkFlowConstants.BILL_DOCUMENT);
            else workFlowVO.setDocId(WorkFlowConstants.ONLINEBILL_DOCUMENT);

            workFlowVO.setJobRefId(Long.toString(trnVouchVo.getBillNo()));
            workFlowVO.setLocID(SessionHelper.getLocationCode(objectArgs));
            workFlowVO.setDbId(SessionHelper.getDbId(objectArgs));
            workFlowVO.setToPost(Long.toString(hrchyPostId));
            workFlowVO.setFromPost(SessionHelper.getPostId(objectArgs).toString());
            workFlowVO.setCrtPost(SessionHelper.getPostId(objectArgs).toString());
            workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
            workFlowVO.setHierarchyFlag(1);	

            /* get jdbc connection and set in workflow value object.. */
            Connection conn = serv.getSessionFactory().getCurrentSession().connection();
            workFlowVO.setConnection(conn);

            /* invoke work flow .. */
            WorkFlowUtility wfUtility = new WorkFlowUtility();
            workFlowVO.setAppMap(objectArgs);
            Map returnMap = wfUtility.invokeWorkFlow(workFlowVO);
            if(returnMap.containsKey("ERROR"))
            {
                throw (Exception) returnMap.get("ERROR");
            }
            WorkFlowVO wrkFlw = (WorkFlowVO)returnMap.get("WorkFlowVO");

            billReg.setCurrBillStatus(DBConstants.ST_VCHR_DSTRBTD);
            phyDAO.update(billReg);

            logger.info("SucessFully Done For :" + trnVouchVo.getBillNo());
          }

        } // end of while ..
        objectArgs.put("msg","Vouchers distributed Successfully");
      } // end of if ..
      objectArgs.putAll((Map) getVouchersForDist(objectArgs).getResultValue());
      objRes.setResultValue(objectArgs);
      objRes.setViewName("distVouhsForDetPost");			
    }
    catch(Exception e)
    {			
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.distributeVouchers # \n"+e);
    }

    return objRes;
  }

  /**
   * This method returns a List<VoucherVO.class> of all vouchers for detail posting in a Map as resultValue
   * @param objectArgs Map consisting of requestObj(HttpServletRequest), serviceLocator(ServiceLocator)
   * @return ResultObject	 
   */
  public ResultObject getVouchersForDetPost(Map objectArgs) {

    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    VoucherDAOImpl voucherImpl=null;
    Long postId=null;
    Long userId=null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }	

      HttpSession session=request.getSession();
      userId = SessionHelper.getUserId(request);
      /* get vouchers(bills) from work flow .. */
      postId  = SessionHelper.getPostId(objectArgs);
      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);
      logger.info("postLst " + objectArgs.get("postLst"));
      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);

      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      List voucherList = voucherImpl.getVouchersForDetPost(postId, billLst,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs));
      logger.info("\n\n**************Voucher List for Detail Posting***** " + voucherList);
      objectArgs.put("voucherList",voucherList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("accVouhsForDetPost");			
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getVouchersForDetPost # \n"+e);
    }
    return objRes;
  }

  /**
   * This method update the accepted vouchers and returns a List<VoucherVO.class> of all vouchers for acceptance in detail posting in a Map as resultValue
   * @param objectArgs Map consisting of requestObj(HttpServletRequest), serviceLocator(ServiceLocator)
   * @return ResultObject	 
   */
  public ResultObject accVouchersForDetPost(Map objectArgs) 
  {	

    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    VoucherDAOImpl voucherImpl=null;
    Long postId=null;
    Long userId=null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }

      userId = SessionHelper.getUserId(request);
      /* get vouchers(bills) from work flow .. */
      postId  = SessionHelper.getPostId(objectArgs);
      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);
      logger.info("postLst " + objectArgs.get("postLst"));
      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);

      Map objectArg = new HashMap();
      objectArg.put("billsNo",request.getParameterValues("vouchNo"));
      objectArg.put("userId",userId);
      objectArg.put("postId",postId);
      objectArg.put("slocCode",SessionHelper.getLocationCode(objectArgs));
      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());

      voucherImpl.accVouchersForDetPost(objectArg);

      List voucherList = voucherImpl.getVouchersForDetPost(postId, billLst,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs));
      logger.info("\n\n**************Voucher List acceptence for Deatil Posting***** " + voucherList);
      objectArgs.put("voucherList",voucherList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("accVouhsForDetPost");			
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.accVouchersForDetPost # \n"+e);
    }
    logger.info("accept vouchers from cheque branch");
    return objRes;
  }

  /**
   * This method returns List<VoucherVO.class> of vouchers that are received in Detail posting
   * @param objectArgs Map consisting of requestObj(HttpServletRequest), serviceLocator(ServiceLocator)
   * @return ResultObject	 
   */
  public ResultObject getVouchersListForDet(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    VoucherDAOImpl voucherImpl = null;
    Long userId = null;
    Long postId = null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      userId = SessionHelper.getUserId(request);
      /* get vouchers(bills) from work flow .. */
      postId  = SessionHelper.getPostId(objectArgs);
      Long lngVchrSt=Long.parseLong(request.getParameter("posted").toString());
      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);

      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);

      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());

      List voucherList = voucherImpl.getVouchersListForDet(postId, billLst,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs),lngVchrSt);
      Map result = new HashMap();
      logger.info("\nVoucher list for detail posting : " + voucherList);
      result.put("voucherList",voucherList);
      objRes.setResultValue(result);
      objRes.setViewName("vouchListForDetPost");			
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getVouchersListForDet # \n"+e);
    }
    return objRes;
  }

  /**
   * This Method to get freezed voucher(Like Record room)in book branch
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getFreezedVoucher(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    VoucherDAOImpl voucherImpl = null;
    Long postId = null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      /* get vouchers(bills) from work flow .. */
      postId  = SessionHelper.getPostId(objectArgs);
      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);
      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);
      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      List voucherList = voucherImpl.getFreezedVoucher(postId, billLst,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs));
      logger.info("\nVoucher list for detail posting : " + voucherList);
      objectArgs.put("voucherList",voucherList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("vouchListInRecordRoom");			
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getVouchersListForDet # \n"+e);
    }
    return objRes;
  }
  /**
   * This Method to get voucher details for detail posting 
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getVoucherDtls(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");			
    VoucherDAOImpl voucherImpl = null;
    Long userId = null;
    Long postId = null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      long billno=Long.parseLong(request.getParameter("BillNo"));
      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      /* get voucher detail .. */
      VoucherVO vouchVo= voucherImpl.getVoucherDtls(SessionHelper.getPostId(objectArgs),billno,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs));
      Map lObjTcMap=voucherImpl.getTCDetails(billno,SessionHelper.getLocationCode(objectArgs));
      /* get budjetHead deatil.. */
      TrnBillBudheadDtls budjetDtlVo= voucherImpl.getBujectHeadDtls(billno,SessionHelper.getLocationCode(objectArgs));
      objectArgs.put("tcBill",lObjTcMap.get("tcBill"));
      objectArgs.put("majorHead",lObjTcMap.get("majorHead"));
      objectArgs.put("vouchVo",vouchVo);
      objectArgs.put("budjetDtlVo",budjetDtlVo);

      CmnLookupMstDAOImpl cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
      List fund = cmnDAO.getAllChildrenByLookUpNameAndLang("Fund",SessionHelper.getLangId(objectArgs));
      objectArgs.put("fund",fund);
      List ClassOfExp = cmnDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp",SessionHelper.getLangId(objectArgs));
      objectArgs.put("ClassOfExp",ClassOfExp);
      List lBudjetType = cmnDAO.getAllChildrenByLookUpNameAndLang("OnlineBillBudgetType",SessionHelper.getLangId(objectArgs));
      Object[] lObjTemp = lBudjetType.toArray();
      Object[] lObjBudjetType = new Object[lObjTemp.length];
      for (Object lObj : lObjTemp)
      {
        CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
        lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
      }
      lObjTemp = null;

      objectArgs.put("BudjetType",lObjBudjetType);

      Map lMapBudTypeDtls = getCmnLookUpValueAndDescription(cmnDAO, budjetDtlVo.getBudType(),SessionHelper.getLangId(objectArgs));

      LocationDAOImpl locationImpl=new LocationDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
      String deptCode=locationImpl.getDeptCodeByLocaId(vouchVo.getBillNo(),SessionHelper.getLocale(request));
      objectArgs.put("deptCode",deptCode);

      Long subjectId=null;
      subjectId=Long.parseLong(request.getParameter("subjectId"));
      objectArgs.put("subjectId",subjectId);
      objectArgs.put("Selected_BudType",lMapBudTypeDtls);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("vouhsDetPost");			
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getVoucherDtls # \n"+e);
    }
    return objRes;
  }


  /**
   * This Method to get edp code details for particular bill type in voucher detail posting in book branch
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getEdpDtlsByBillType(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");			
    VoucherDAOImpl voucherImpl = null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
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
      BillEdpDAOImpl billEdpDAO = new BillEdpDAOImpl(TrnBillEdpDtls.class,serv.getSessionFactory());
      objectArgs.put("rcptObjHds",billEdpDAO.getRcptEdpDtlByBillType(subjectId, billNo,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs),tcBill,sRcpMajorHead));
      objectArgs.put("expObjHds",billEdpDAO.getExpObjHdDtlByBillType(subjectId, billNo,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs)));
      objectArgs.put("recObjHds",billEdpDAO.getRecObjHdDtlByBillType(subjectId, billNo,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs)));
      List expEdpList=billEdpDAO.getExpEdpDtl(subjectId,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs),tcBill,sRcpMajorHead);
      objectArgs.put("expEdpList", expEdpList);
      if(billNo!=null)
      {
        objectArgs.put("expObjHdsAdded",billEdpDAO.getExpObjHdDtlByBillNo(billNo,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs)));
        objectArgs.put("recObjHdsAdded",billEdpDAO.getRecObjHdDtlByBillNo(billNo,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs)));
        objectArgs.put("rcptObjHdsAdded",billEdpDAO.getRcptObjHdDtlByBillNo(billNo,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs)));
      }
      objRes.setResultValue(objectArgs);
      //	objRes.setViewName("vouhsDetPost");			
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getEdpDtlsByBillType # \n"+e);
    }
    return objRes;
  }
  /**
   * This method to validate a voucher head structure.
   * @param objectArgs
   * @return
   */
  public ResultObject validateVouchDetPost(Map objectArgs)
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    try
    {
      logger.info("Inside The Validate head Structure");
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      boolean lHeadsFlag = false;
      BudgetHdServiceImpl lObjBudHdSrvc = new BudgetHdServiceImpl();
      lHeadsFlag = lObjBudHdSrvc.validateHeads(objectArgs);
      logger.info("Value Of Head Structure is:-"+lHeadsFlag);
      if(lHeadsFlag == true)
      {
        objectArgs.put("MESSAGECODE",(long)2046);
      }
      else
      {
        objectArgs.put("MESSAGECODE",(long)2047);
      }
      objRes.setResultValue(objectArgs);
      objRes.setViewName("ajaxData");
    }
    catch(Exception ex)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(ex);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      ex.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.validateVouchDetPost :-"+ex);
    }
    return objRes; 
  }

  /**
   * This method to insert voucher deatails
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject insertVoucherDtls(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    VoucherVO vouchVO = (VoucherVO)objectArgs.get("vouchVo");
    TrnBillBudheadDtls budHeadVo=(TrnBillBudheadDtls)objectArgs.get("budHeadVo");
    Long lngUserId=null;
    Long lngPostId=null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      lngUserId = SessionHelper.getUserId(request);
      lngPostId = SessionHelper.getPostId(objectArgs);
      VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      BudHeadDAOImpl budHeadImpl = new BudHeadDAOImpl(TrnBillBudheadDtls.class,serv.getSessionFactory());
      BillMovementDAOImpl mvmntDAO = new BillMovementDAOImpl(TrnBillMvmnt.class,serv.getSessionFactory());
      DssDataServiceImpl servImpl = new DssDataServiceImpl();
      PhyBillDAOImpl phyBillDAO = new PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());

      Map map=new HashMap();
      map.put("vouchVo", vouchVO);
      map.put("slocCode",SessionHelper.getLocationCode(objectArgs));
      voucherImpl.updateAmount(map);
      List voucherVoLst=voucherImpl.getVoucherVO(vouchVO.getBillNo(),SessionHelper.getLocationCode(objectArgs));
      TrnVoucherDetails voucherDetailVO=(TrnVoucherDetails)voucherVoLst.get(0);
      Map dssMap = new HashMap();
      dssMap.put("map", objectArgs);
      dssMap.put("expNo", vouchVO.getBillNo());
      dssMap.put("expTypeCode", DSSHelper.EXP_TYPE_BILL);
      /** call DSS Service to retrieve expenditure VO .. */
      RptExpenditureDtls rptExpVO = servImpl.getExpData(dssMap);
      Map rcptDssMap=new HashMap();
      rcptDssMap.put("map",objectArgs);
      rcptDssMap.put("rcptNo",new Long(-1));
      rcptDssMap.put("rcptTypeCode",DSSHelper.RCPT_TYPE_BILL);
      rcptDssMap.put("challanCatgCode",DSSHelper.CHALLAN_CATE_CODE);
      rcptDssMap.put("trnReceiptId",vouchVO.getBillNo());
      RptReceiptDtls rptReceiptVO=servImpl.getReceiptData(rcptDssMap);
      if(budHeadVo.getBillBudId()!=0)
      {
        budHeadVo.setUpdatedUserId(SessionHelper.getUserId(request));
        budHeadVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
        budHeadVo.setUpdatedDate(new Date(System.currentTimeMillis()));
        budHeadImpl.update(budHeadVo);
        /** call DSS Service to update Expenditure Details .. */
        if( budHeadVo.getDmndNo().equals("999") )
        {
          DSSHelper.updateRptExpDemReceiptVO(rptReceiptVO,voucherDetailVO,budHeadVo);
          rcptDssMap.put("RptReceiptVO",rptReceiptVO);
          Map resultMap = servImpl.updateReceiptData(rcptDssMap);	
        }
        else
        {
          DSSHelper.updateRptExpVO(rptExpVO, phyBillDAO.read(vouchVO.getBillNo()),budHeadVo,voucherDetailVO);
          dssMap.put("RptExpenditureVO",rptExpVO);
          Map resultMap = servImpl.updateExpData(dssMap);
        }
      }
      /** Add By Bhavesh FOR add the funactionality of Posted voucher */
      if(request.getParameter("posted").equals("0"))
      {
        Long lLngCaseID = BptmCommonServiceImpl.getNextSeqNum("trn_bill_mvmnt", objectArgs);
        TrnBillMvmnt billMvnVo = new TrnBillMvmnt();
        billMvnVo.setBillMvmtId(lLngCaseID);
        billMvnVo.setBillNo(voucherDetailVO.getBillNo());
        billMvnVo.setMovemntId(mvmntDAO.getmaxMovementId(voucherDetailVO.getBillNo()));
        billMvnVo.setStatusUpdtUserid(lngUserId);
        billMvnVo.setStatusUpdtPostid(lngPostId);
        billMvnVo.setStatusUpdtDate(new Date(System.currentTimeMillis()));
        billMvnVo.setMvmntStatus(DBConstants.ST_DTL_PSTNG_DONE);
        billMvnVo.setReceivedFlag(new Short("0"));
        billMvnVo.setCreatedUserId(lngUserId);
        billMvnVo.setCreatedPostId(lngPostId);
        billMvnVo.setCreatedDate(new Date(System.currentTimeMillis()));
        billMvnVo.setUpdatedUserId(lngUserId);
        billMvnVo.setUpdatedPostId(lngPostId);
        billMvnVo.setUpdatedDate(new Date(System.currentTimeMillis()));
        billMvnVo.setLocationCode(SessionHelper.getLocationCode(objectArgs));
        billMvnVo.setDbId(SessionHelper.getDbId(objectArgs));

        mvmntDAO.create(billMvnVo);

        TrnBillRegister trnBillRegVO=phyBillDAO.read(voucherDetailVO.getBillNo());
        trnBillRegVO.setCurrBillStatus(DBConstants.ST_DTL_PSTNG_DONE);
        phyBillDAO.update(trnBillRegVO);
      }
      objectArgs.put("voucherVO",voucherDetailVO);
      objectArgs.put("MESSAGECODE",(long)2045);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("ajaxData");
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.insertVoucherDtls # \n"+e);
    }
    return objRes;
  }

  /**
   * This method to insert edp code details
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject insertEdpDtls(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    List PayVOList=(List) objectArgs.get("payVOlist");
    List recVOList=(List) objectArgs.get("recVOlist");
    List rcptVOList=(List) objectArgs.get("rcptVOlist");
    TrnVoucherDetails vocherVO=null;
    if(objectArgs.containsKey("voucherVO"))
    {
      vocherVO=(TrnVoucherDetails)objectArgs.get("voucherVO");
    }
    TrnBillBudheadDtls trnBudHeadVO=null;
    if(objectArgs.containsKey("budHeadVo"))
    {
      trnBudHeadVO=(TrnBillBudheadDtls)objectArgs.get("budHeadVo");
    }
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }

      VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      BillEdpDAOImpl billEdpImpl = new BillEdpDAOImpl(TrnBillEdpDtls.class,serv.getSessionFactory());
      PhyBillDAOImpl phyBillDAO = new PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
      DssDataServiceImpl servImpl = new DssDataServiceImpl();
      List expEdpVOArrLstInsert=new ArrayList();
      List rcptEdpArrLstInsert=new ArrayList();
      objectArgs.put("map",objectArgs);
      objectArgs.put("expTypeCode", DSSHelper.EXP_TYPE_BILL);
      objectArgs.put("rcptTypeCode", DSSHelper.RCPT_TYPE_BILL);
      objectArgs.put("challanCatgCode", DSSHelper.CHALLAN_CATE_CODE);
//    Map dssMap=new HashMap();
//    dssMap.put("map",objectArgs);
//    dssMap.put("expTypeCode", DSSHelper.EXP_TYPE_BILL);			
      for(int i=0;i<PayVOList.size();i++)
      {
        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) PayVOList.get(i);

        if(billEdpVo.getBillEdpId()!=0)
        {
          billEdpVo.setUpdatedUserId(SessionHelper.getUserId(request));
          billEdpVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
          billEdpVo.setUpdatedDate(new Date(System.currentTimeMillis()));
          billEdpImpl.update(billEdpVo);

          /** DSS service Call for bulk update */

          objectArgs.put("expNo", billEdpVo.getBillNo());
          objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
          RptExpEdpDtls rptExpEdpVO = servImpl.getExpEdpData(objectArgs);

          DSSHelper.updateRptExpEdpVO(rptExpEdpVO, billEdpVo);

          objectArgs.put("RptExpEdpVO", rptExpEdpVO);
          Map resMap=servImpl.updateExpEdpData(objectArgs);

          /**End of Creation of array list*/
          logger.info("Inside The Update Exp Details");
        }
        else
        {
          Long billEdpId = BptmCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
          billEdpVo.setBillEdpId(billEdpId);
          billEdpVo.setCreatedUserId(SessionHelper.getUserId(request));
          billEdpVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
          billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
          
          //ravysh
         
          
          //end ravysh
          
          
          billEdpImpl.create(billEdpVo);

          /** code for DSS insert arraylist*/
          /*if(trnBudHeadVO!=null)
          {
            if(!trnBudHeadVO.getDmndNo().equals("999"))
            {
              objectArgs.put("expNo", billEdpVo.getBillNo());
              objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
              MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));

              RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
              expEdpVOArrLstInsert.add(rptExpEdpVo);
            }
            else
            {
              Map rcptDssMap=new HashMap();
              rcptDssMap.put("map",objectArgs);
              rcptDssMap.put("rcptNo",new Long(-1));
              rcptDssMap.put("rcptTypeCode",DSSHelper.RCPT_TYPE_BILL);
              rcptDssMap.put("challanCatgCode",DSSHelper.CHALLAN_CATE_CODE);
              rcptDssMap.put("trnReceiptId",billEdpVo.getBillNo());
              RptReceiptDtls rptReceiptVO=servImpl.getReceiptData(rcptDssMap);
              DSSHelper.updateRptEdpCodeReceiptVO(rptReceiptVO,billEdpVo);
              rcptDssMap.put("RptReceiptVO",rptReceiptVO);
              Map resultMap=servImpl.updateReceiptData(rcptDssMap);
            }
          }
          else
          {
            objectArgs.put("expNo", billEdpVo.getBillNo());
            objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
            MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));
            RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
            expEdpVOArrLstInsert.add(rptExpEdpVo);
          }
          *//** End of DSS Code */
        }
      }

      for(int i=0;i<recVOList.size();i++)
      {
        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) recVOList.get(i);

        if(billEdpVo.getBillEdpId()!=0)
        {
          billEdpVo.setUpdatedUserId(SessionHelper.getUserId(request));
          billEdpVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
          billEdpVo.setUpdatedDate(new Date(System.currentTimeMillis()));
          billEdpImpl.update(billEdpVo);

          /** DSS service Call for bulk update */

          objectArgs.put("expNo", billEdpVo.getBillNo());
          objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
          RptExpEdpDtls rptExpEdpVO = servImpl.getExpEdpData(objectArgs);
          DSSHelper.updateRptExpEdpVO(rptExpEdpVO, billEdpVo);
          objectArgs.put("RptExpEdpVO", rptExpEdpVO);
          Map resMap=servImpl.updateExpEdpData(objectArgs);
          //expEdpVOArrLstUpdate.add(rptExpEdpVO);

          /** End of Creation of array list*/
          logger.info("Inside The Update Rec Details");
        }
        else
        {
          Long billEdpId = BptmCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
          billEdpVo.setBillEdpId(billEdpId);
          billEdpVo.setCreatedUserId(SessionHelper.getUserId(request));
          billEdpVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
          billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
          
        
          
          
          billEdpImpl.create(billEdpVo);	

          /** code for DSS insert arraylist*/

          objectArgs.put("expNo", billEdpVo.getBillNo());
          objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
          MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));

          RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
          expEdpVOArrLstInsert.add(rptExpEdpVo);

          /** End of DSS Code */
          logger.info("Inside The Insert Rec Details");
        }
      }

      for(int i=0;i<rcptVOList.size();i++)
      {
        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) rcptVOList.get(i);

        if(billEdpVo.getBillEdpId()!=0)
        {
          billEdpVo.setUpdatedUserId(SessionHelper.getUserId(request));
          billEdpVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
          billEdpVo.setUpdatedDate(new Date(System.currentTimeMillis()));
          billEdpImpl.update(billEdpVo);

          /** DSS service Call for bulk update */

          objectArgs.put("rcptNo", billEdpVo.getBillNo());
          objectArgs.put("trnReceiptId", billEdpVo.getBillEdpId());
          RptReceiptDtls rptRcptVO = servImpl.getReceiptData(objectArgs);

          MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));
          DSSHelper.updateRptReceiptVO(rptRcptVO, billEdpVo, mstEdpVo,vocherVO);

          objectArgs.put("RptReceiptVO", rptRcptVO);
          Map resMap=servImpl.updateReceiptData(objectArgs);


          //	rcptEdpArrLstUpdate.add(rptRcptVO);

          /**	End of Creation of array list*/

        }
        else
        {
          Long billEdpId = BptmCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
          billEdpVo.setBillEdpId(billEdpId);
          billEdpVo.setCreatedUserId(SessionHelper.getUserId(request));
          billEdpVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
          billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
          
        
          
          billEdpImpl.create(billEdpVo);

          /** code for DSS insert arraylist*/

          /*objectArgs.put("rcptNo", billEdpVo.getBillNo());
          objectArgs.put("trnReceiptId", billEdpVo.getBillEdpId());
          MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));

          RptReceiptDtls rptRceiptVO=DSSHelper.createRptReceiptVO(billEdpVo, mstEdpVo, phyBillDAO.read(billEdpVo.getBillNo()),vocherVO);
          rcptEdpArrLstInsert.add(rptRceiptVO);
          */
          /** End of DSS Code */

        }
      }
      /*if((expEdpVOArrLstUpdate!=null && expEdpVOArrLstUpdate.size()>0) || (rcptEdpArrLstUpdate!=null && rcptEdpArrLstUpdate.size()>0))
			{
				objectArgs.put("RptExpEdpVOArrLst",expEdpVOArrLstUpdate);
				objectArgs.put("RptReceiptVOArrLst",rcptEdpArrLstUpdate);
				Map returnMap=servImpl.BulkUpdateData(objectArgs); 
			}*/
      /*if(expEdpVOArrLstInsert!=null && expEdpVOArrLstInsert.size()>0 )
      {
        objectArgs.put("RptExpEdpVOArrLst",expEdpVOArrLstInsert);
        Map returnMap=servImpl.insertExpEdpData(objectArgs); 
      }
      if(rcptEdpArrLstInsert!=null && rcptEdpArrLstInsert.size()>0)
      {
        objectArgs.put("RptReceiptVOArrLst",rcptEdpArrLstInsert);
        Map returnMap=servImpl.insertReceiptData(objectArgs);
      }*/
//    Map dssMap=(HashMap)objectArgs.get("dssMap");
//    VoucherVO vouchVo=(VoucherVO)objectArgs.get("vouchVO");
//    RptExpenditureDtls rptExpVO = servImpl.getExpData(dssMap);
//    DSSHelper.updateRptExpVO(rptExpVO, phyBillDAO.read(vouchVo.getBillNo()),(TrnBillBudheadDtls)objectArgs.get("budjetVO"));
//    //dssMap.put("RptExpenditureVO",rptExpVO);
//    Map resultMap=servImpl.updateExpData(dssMap);
      objRes.setResultValue(objectArgs);
      //objRes.setViewName();
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.insertEdpDtls # \n"+e);
    }
    return objRes;
  }

  /**
   * This method to delete previously added edp code.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject deleteExtAddEdpCode(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    logger.info("Inside The Delete Method");
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      String strBillNo=null;
      Long billNo=null;
      try 
      {
        strBillNo=StringUtility.getParameter("hdbillNo", request);
        if(strBillNo!=null && strBillNo.trim().length() > 0)
        {
          billNo = Long.parseLong(strBillNo);
        }
        else
        {
          strBillNo=StringUtility.getParameter("hidBillNo", request);
          billNo = Long.parseLong(strBillNo);
        }
      } catch(Exception ex){}
      //VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      //Map retEdpIdMap=voucherImpl.deleteExtAddEdpCode(billNo);
      DssDataServiceImpl servImpl = new DssDataServiceImpl();
      logger.info("Bill No for DELETE-----------0000000000000:-----"+billNo);
      if (billNo!=null) 
      {
        VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
        Map retEdpIdMap=voucherImpl.deleteExtAddEdpCode(billNo,SessionHelper.getLocationCode(objectArgs));
        List expEdpList=(ArrayList)retEdpIdMap.get("expEdpIdLst");
        objectArgs.put("map",objectArgs);
        objectArgs.put("expNo",billNo);
        objectArgs.put("expTypeCode", DSSHelper.EXP_TYPE_BILL);
        if(expEdpList!=null)
        {
          for(int iCounter=0;iCounter<expEdpList.size();iCounter++)
          {
            logger.info("Inside The Exp deletion loop");
            objectArgs.put("trnExpEdpId",expEdpList.get(iCounter));
            Map retMap=servImpl.deleteExpEdpData(objectArgs);
          }
        }
        List rcptEdpList=(ArrayList)retEdpIdMap.get("rcptEdpIdLst");
        objectArgs.put("rcptNo",billNo);
        objectArgs.put("rcptTypeCode",DSSHelper.RCPT_TYPE_BILL);
        objectArgs.put("challanCatgCode",DSSHelper.CHALLAN_CATE_CODE);
        if(rcptEdpList!=null)
        {
          for(int iCounter=0;iCounter<rcptEdpList.size();iCounter++)
          {
            logger.info("Inside The Receipt deletion loop");
            objectArgs.put("trnReceiptId",rcptEdpList.get(iCounter));
            Map retMap=servImpl.deleteReceiptData(objectArgs);
          }
        }
        objRes.setResultValue(objectArgs);
      }
      objRes.setResultValue(objectArgs);
      //objRes.setViewName("vouchListForDetPost");	
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.deleteExtAddEdpCode # \n"+e);
    }
    return objRes;
  }

  /**
   * This method to return back to privous hierchy user. 
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject sentVoucherBack(Map objectArgs) {	
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      Long postId=SessionHelper.getPostId(objectArgs);
      PhyBillDAOImpl phyBillDAO = new PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
      BptmCommonServicesDAOImpl bptmDAO = new BptmCommonServicesDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
      VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      String strBillNo[] = request.getParameterValues("vouchNo");
      for(int iCounter=0;iCounter<strBillNo.length;iCounter++)
      {
        TrnBillMvmnt billMvnVo=new TrnBillMvmnt();
        billMvnVo.setBillNo(Long.parseLong(strBillNo[iCounter]));		
        billMvnVo.setStatusUpdtDate(new Date(System.currentTimeMillis()));
        billMvnVo.setMvmntStatus(DBConstants.ST_VCHR_GEN);
        billMvnVo.setReceivedFlag(new Short("0"));
        billMvnVo.setCreatedUserId(SessionHelper.getUserId(request));
        billMvnVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
        billMvnVo.setCreatedDate(new Date(System.currentTimeMillis()));
        billMvnVo.setUpdatedUserId(SessionHelper.getUserId(request));
        billMvnVo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
        billMvnVo.setUpdatedDate(new Date(System.currentTimeMillis()));
        billMvnVo.setLocationCode(SessionHelper.getLocationCode(objectArgs));
        billMvnVo.setDbId(SessionHelper.getDbId(objectArgs));


        WorkFlowVO workFlowVO = new WorkFlowVO();
        workFlowVO.setAppMap(objectArgs);			
        workFlowVO.setCrtEmpId(SessionHelper.getEmpId(objectArgs).toString());
        workFlowVO.setCrtPost(SessionHelper.getPostId(objectArgs).toString());
        workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
        workFlowVO.setActId(WorkFlowConstants.WF_RETURN);
        if (bptmDAO.isPhyBill(Long.parseLong(strBillNo[iCounter])))
        {
          workFlowVO.setDocId(WorkFlowConstants.BILL_DOCUMENT);
        }
        else
        {
          workFlowVO.setDocId(WorkFlowConstants.ONLINEBILL_DOCUMENT);
        }
        workFlowVO.setJobRefId(strBillNo[iCounter]);
        workFlowVO.setLocID(SessionHelper.getLocationCode(objectArgs));
        workFlowVO.setDbId(SessionHelper.getDbId(objectArgs));
        /* get jdbc connection and set in workflow value object.. */
        Connection conn = serv.getSessionFactory().getCurrentSession().connection();
        workFlowVO.setConnection(conn);

        OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
        String toPost = orgUtil.getReturnPost(workFlowVO);
        String toUsr = bptmDAO.getUserIdFromPost(toPost, SessionHelper.getLangId(objectArgs));

        billMvnVo.setStatusUpdtUserid(Long.parseLong(toUsr));
        billMvnVo.setStatusUpdtPostid(Long.parseLong(toPost));
        objectArgs.put("BillMovementVO", billMvnVo);

        /* invoke work flow .. */
        WorkFlowUtility wfUtility = new WorkFlowUtility();
        workFlowVO.setAppMap(objectArgs);
        logger.info("BEFORE INVOKE........................");
        Map returnMap = wfUtility.invokeWorkFlow(workFlowVO);
        logger.info("AFTER INVOKE........................");
        WorkFlowVO wrkFlw = (WorkFlowVO)returnMap.get("WorkFlowVO");

        /*Update status in Bill register*/
        TrnBillRegister trnBillRegVO=phyBillDAO.read(Long.parseLong(strBillNo[iCounter]));
        trnBillRegVO.setCurrBillStatus(DBConstants.ST_VCHR_GEN);
        phyBillDAO.update(trnBillRegVO);
      }
      String viewPage=request.getParameter("viewPage");
      Long lngVchrSt=Long.parseLong(request.getParameter("posted").toString());
      List postLst = new ArrayList();
      postLst.add(SessionHelper.getPostId(objectArgs).toString());
      objectArgs.put("postLst",postLst);

      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);
      long userId=SessionHelper.getUserId(request);
      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());

      if(viewPage.equalsIgnoreCase("accVouchForDet"))
      {
        List voucherList = voucherImpl.getVouchersForDetPost(postId, billLst,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs));
        objectArgs.put("voucherList",voucherList);

        objRes.setViewName("accVouhsForDetPost");	
      }
      else if(viewPage.equalsIgnoreCase("vouchListForDet"))
      {
        List voucherList = voucherImpl.getVouchersListForDet(postId, billLst,SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs),lngVchrSt);
        objectArgs.put("voucherList", voucherList);
        objRes.setViewName("vouchListForDetPost");	
      }
      objRes.setResultValue(objectArgs);
    }
    catch(Exception e)
    {	
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.sentVoucherBack # \n"+e);
    }

    return objRes;
  }
  /**
   * This method to display a list, whom freezed voucher and which date
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getFreezedDateAndPost(Map objectArgs)
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    try
    {
      VerifiedAccountDAOImpl vefiAccDAOImpl = new VerifiedAccountDAOImpl(TrnVerifiedAccount.class,serv.getSessionFactory());
      List freezeList=vefiAccDAOImpl.getFreezedDateAndPost(SessionHelper.getLocationCode(objectArgs));
      objectArgs.put("freezeList",freezeList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("frezeVoucher");
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getFreezedDateAndPost # \n"+e);

    }
    return objRes;
  }
  /**
   * This method to freeze a voucher
   * @param objectArgs
   * @return
   */
  public ResultObject freezeVoucher(Map objectArgs)
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    try
    {
      String toDate=request.getParameter("toDate");
      VerifiedAccountDAOImpl verAccImpl = new VerifiedAccountDAOImpl(TrnVerifiedAccount.class,serv.getSessionFactory());
      TrnVerifiedAccount veriAccVO=new TrnVerifiedAccount();
      Long lngAccId = BptmCommonServiceImpl.getNextSeqNum("trn_verified_account", objectArgs);
      veriAccVO.setAcId(new BigDecimal(lngAccId));
      Date endDate=verAccImpl.getEndDateFromVerAcc(SessionHelper.getLocationCode(objectArgs));
      if(endDate==null)
      {
        veriAccVO.setStartDate(new Date());
      }
      else
      {
        veriAccVO.setStartDate(endDate);
      }
      Date todate=new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
      veriAccVO.setEndDate(todate);
      veriAccVO.setActive(true);
      veriAccVO.setPostId(new BigDecimal(SessionHelper.getPostId(objectArgs)));
      veriAccVO.setCreatedBy(new BigDecimal(SessionHelper.getUserId(request)));
      veriAccVO.setCreatedByPost(new BigDecimal(SessionHelper.getPostId(objectArgs)));
      veriAccVO.setCreatedDate(new Date());
      veriAccVO.setDbId(new BigDecimal(SessionHelper.getDbId(objectArgs)));
      veriAccVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
      verAccImpl.create(veriAccVO);
      logger.info("Come out side The page");
      objRes.setResultValue(objectArgs);
      objRes.setViewName("homePage");
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      logger.error("Exception occured in VoucherServiceImpl.freezeVoucher # \n"+e);
      e.printStackTrace();

    }
    return objRes;
  }
  /**
   * This method to display a form for sub treasury voucher detail posting.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getSubTrsyFormDtls(Map objectArgs)
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");			
    VoucherDAOImpl voucherImpl = null;
    Long userId = null;
    Long postId = null;
    List expEdpVOArrLstInsert = new ArrayList();
    List rcptEdpArrLstInsert = new ArrayList();
    try
    {
      if(objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
      CmnLookupMstDAOImpl cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
      BillEdpDAOImpl billEdpDAO = new BillEdpDAOImpl(TrnBillEdpDtls.class,serv.getSessionFactory());
      ReportQueryDAOImpl queryDAO = new ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());

      List fund = cmnDAO.getAllChildrenByLookUpNameAndLang("Fund",SessionHelper.getLangId(objectArgs));
      objectArgs.put("fund",fund);
      List ClassOfExp = cmnDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp",SessionHelper.getLangId(objectArgs));
      objectArgs.put("ClassOfExp",ClassOfExp);
      List lBudjetType = cmnDAO.getAllChildrenByLookUpNameAndLang("OnlineBillBudgetType",SessionHelper.getLangId(objectArgs));
      Object[] lObjTemp = lBudjetType.toArray();
      Object[] lObjBudjetType = new Object[lObjTemp.length];
      for (Object lObj : lObjTemp)
      {
        CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
        lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
      }
      lObjTemp = null;
      objectArgs.put("BudjetType",lObjBudjetType);
      List lLstGoNgo = BptmCommonServiceImpl.getLookupValues("go_ngo", SessionHelper.getLangId(objectArgs), objectArgs);
      objectArgs.put("goNgoList",lLstGoNgo);
      List lLstBillType = lObjCmnSrvcDAOImpl.getBillType(SessionHelper.getLangId(objectArgs));
      objectArgs.put("BillType", lLstBillType);
      List lLstTcBill = BptmCommonServiceImpl.getLookupValues("TcBillType",SessionHelper.getLangId(objectArgs),objectArgs);			
      objectArgs.put("TcBillList",lLstTcBill);
      List expEdpList=billEdpDAO.getExpEdpDtl(SessionHelper.getLangId(objectArgs));
      objectArgs.put("expEdpList", expEdpList);
      List subTrsyList=queryDAO.getSubTsry(SessionHelper.getLocationCode(objectArgs));
      objectArgs.put("subTrsyList", subTrsyList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("subTreaVouhsDetPost");			
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getSubTrsyFormDtls # \n"+e);
    }
    return objRes;
  }

  /**
   * This method to insert sub-treasury vocuher details.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject insertSubTrsyDtls(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    TrnBillBudheadDtls budHeadVo = (TrnBillBudheadDtls)objectArgs.get("budHeadVo");
    TrnBillRegister lobjBillRegister = (TrnBillRegister)objectArgs.get("billRegVO");
    TrnVoucherDetails lObjVoucherDtls = (TrnVoucherDetails) objectArgs.get("voucherDtlsVO");
    List PayVOList=(List) objectArgs.get("payVOlist");
    List recVOList=(List) objectArgs.get("recVOlist");
    List rcptVOList=(List) objectArgs.get("rcptVOlist");
    List expEdpVOArrLstInsert=new ArrayList();
    List rcptEdpArrLstInsert=new ArrayList();
    Long lngUserId=null;
    Long lngPostId=null;

    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      objectArgs.put("map",objectArgs);
      objectArgs.put("expTypeCode", DSSHelper.EXP_TYPE_BILL);
      objectArgs.put("rcptTypeCode", DSSHelper.RCPT_TYPE_BILL);
      objectArgs.put("challanCatgCode", DSSHelper.CHALLAN_CATE_CODE);
      objectArgs.put("expNo",lobjBillRegister.getBillNo());
      objectArgs.put("trnReceiptId",lobjBillRegister.getBillNo());
      objectArgs.put("rcptNo",new Long(-1));

      PhyBillDAOImpl phyBillDAO = new PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
      BudHeadDAOImpl budHeadImpl = new BudHeadDAOImpl(TrnBillBudheadDtls.class,serv.getSessionFactory());
      VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      BillEdpDAOImpl billEdpImpl = new BillEdpDAOImpl(TrnBillEdpDtls.class,serv.getSessionFactory());
      DssDataServiceImpl servImpl = new DssDataServiceImpl();



      if(objectArgs.get("BillNo")!=null && !objectArgs.get("BillNo").equals(""))
      {
        phyBillDAO.update(lobjBillRegister);
        budHeadImpl.update(budHeadVo);
        voucherImpl.update(lObjVoucherDtls);
        logger.info("Inside the nullllllllllll");
        if( budHeadVo.getDmndNo().equals("999"))
        {
          RptReceiptDtls rptReceiptVO=servImpl.getReceiptData(objectArgs);
          DSSHelper.updateRptExpDemReceiptVO(rptReceiptVO,lObjVoucherDtls,budHeadVo);
          objectArgs.put("RptReceiptVO",rptReceiptVO);
          Map resultMap = servImpl.updateReceiptData(objectArgs); 
        }
        else
        {
          RptExpenditureDtls rptExpVO = servImpl.getExpData(objectArgs);
          DSSHelper.updateRptExpVO(rptExpVO, phyBillDAO.read(lobjBillRegister.getBillNo()),budHeadVo,lObjVoucherDtls);
          objectArgs.put("RptExpenditureVO",rptExpVO);
          Map resultMap = servImpl.updateExpData(objectArgs);
        }

      }
      else
      {
        phyBillDAO.create(lobjBillRegister);
        budHeadImpl.create(budHeadVo);
        voucherImpl.create(lObjVoucherDtls);
        List rcptVoList = new ArrayList();
        if( budHeadVo.getDmndNo().equals("999"))
        {
          RptReceiptDtls rptReceiptVO = DSSHelper.createRptExpDemReceiptVO(lObjVoucherDtls, budHeadVo, lobjBillRegister);
          rcptVoList.add(rptReceiptVO);
          objectArgs.put("RptReceiptVOArrLst",rcptVoList);
          Map resultMap = servImpl.insertReceiptData(objectArgs); 
        }
        else
        {
          objectArgs.put("lObjTrnBillRegisterVO", lobjBillRegister);
          RptExpenditureDtls lObjRptExpDtls  = null;
          PhyBillVOGeneratorImpl lObjPhyBillVoGen = new PhyBillVOGeneratorImpl();
          lObjRptExpDtls = lObjPhyBillVoGen.generateTrnExpVO(objectArgs);
          lObjRptExpDtls.setExpNo(new BigDecimal(lobjBillRegister.getBillNo()));
          lObjRptExpDtls.setClsExpCode(budHeadVo.getClsExp());
          lObjRptExpDtls.setFundTypeCode(budHeadVo.getFund());
          lObjRptExpDtls.setExpStatusCode(DBConstants.ST_DTL_PSTNG_DONE);
          lObjRptExpDtls.setExpStatusDt(new Date());
          lObjRptExpDtls.setExpDt(lObjVoucherDtls.getVoucherDate());
          lObjRptExpDtls.setDeduction(new BigDecimal(0));
          lObjRptExpDtls.setTsryCode(lobjBillRegister.getLocationCode());
          objectArgs.put("RptExpenditureVO", lObjRptExpDtls);
          Map lMapNew = servImpl.insertExpData(objectArgs);
        }
      }
      for(int i=0;i<PayVOList.size();i++)
      {
        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) PayVOList.get(i);
        Long billEdpId = BptmCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
        billEdpVo.setBillEdpId(billEdpId);
        billEdpVo.setCreatedUserId(SessionHelper.getUserId(request));
        billEdpVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
        billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
        billEdpImpl.create(billEdpVo);

        if(budHeadVo!=null)
        {
          if(!budHeadVo.getDmndNo().equals("999"))
          {
            objectArgs.put("expNo", billEdpVo.getBillNo());
            objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
            MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));

            RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
            expEdpVOArrLstInsert.add(rptExpEdpVo);
          }
          else
          {
            Map rcptDssMap=new HashMap();
            rcptDssMap.put("map",objectArgs);
            rcptDssMap.put("rcptNo",new Long(-1));
            rcptDssMap.put("rcptTypeCode",DSSHelper.RCPT_TYPE_BILL);
            rcptDssMap.put("challanCatgCode",DSSHelper.CHALLAN_CATE_CODE);
            rcptDssMap.put("trnReceiptId",billEdpVo.getBillNo());
            RptReceiptDtls rptReceiptVO=servImpl.getReceiptData(rcptDssMap);
            DSSHelper.updateRptEdpCodeReceiptVO(rptReceiptVO,billEdpVo);
            rcptDssMap.put("RptReceiptVO",rptReceiptVO);
            Map resultMap=servImpl.updateReceiptData(rcptDssMap);
          }
        }
        else
        {
          objectArgs.put("expNo", billEdpVo.getBillNo());
          objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
          MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));
          RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
          expEdpVOArrLstInsert.add(rptExpEdpVo);
        }
      }
      for(int i=0;i<recVOList.size();i++)
      {
        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) recVOList.get(i);
        Long billEdpId = BptmCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
        billEdpVo.setBillEdpId(billEdpId);
        billEdpVo.setCreatedUserId(SessionHelper.getUserId(request));
        billEdpVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
        billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
        billEdpImpl.create(billEdpVo);

        objectArgs.put("expNo", billEdpVo.getBillNo());
        objectArgs.put("trnExpEdpId", billEdpVo.getBillEdpId());
        MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));

        RptExpEdpDtls rptExpEdpVo=DSSHelper.createRptExpEdpVO(billEdpVo,mstEdpVo);
        expEdpVOArrLstInsert.add(rptExpEdpVo);

      }
      for(int i=0;i<rcptVOList.size();i++)
      {
        TrnBillEdpDtls billEdpVo=(TrnBillEdpDtls) rcptVOList.get(i);
        Long billEdpId = BptmCommonServiceImpl.getNextSeqNum("trn_bill_edp_dtls", objectArgs);
        billEdpVo.setBillEdpId(billEdpId);
        billEdpVo.setCreatedUserId(SessionHelper.getUserId(request));
        billEdpVo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
        billEdpVo.setCreatedDate(new Date(System.currentTimeMillis()));
        billEdpImpl.create(billEdpVo);

        objectArgs.put("rcptNo", billEdpVo.getBillNo());
        objectArgs.put("trnReceiptId", billEdpVo.getBillEdpId());
        MstEdp mstEdpVo=billEdpImpl.getMstEdpVoDtls(billEdpVo.getEdpCode(),SessionHelper.getLangId(objectArgs));

        RptReceiptDtls rptRceiptVO=DSSHelper.createRptReceiptVO(billEdpVo, mstEdpVo, phyBillDAO.read(billEdpVo.getBillNo()),lObjVoucherDtls);
        rcptEdpArrLstInsert.add(rptRceiptVO);
      }

      if(expEdpVOArrLstInsert!=null && expEdpVOArrLstInsert.size()>0 )
      {
        objectArgs.put("RptExpEdpVOArrLst",expEdpVOArrLstInsert);
        Map returnMap=servImpl.insertExpEdpData(objectArgs); 
      }
      if(rcptEdpArrLstInsert!=null && rcptEdpArrLstInsert.size()>0)
      {
        objectArgs.put("RptReceiptVOArrLst",rcptEdpArrLstInsert);
        Map returnMap=servImpl.insertReceiptData(objectArgs);
      }
      objRes.setResultValue(objectArgs);
      objectArgs.put("MESSAGECODE",(long)2045);
      objRes.setViewName("ajaxData");
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.insertSubTrsyDtls # \n"+e);
    }
    return objRes;
  }

  
  public ResultObject getSearchVoucher(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    HttpSession hs = request.getSession();
    String searchBy=request.getParameter("searchby");
    String searchVal=request.getParameter("txtSearch");
    String viewPage=request.getParameter("viewPage");
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }

      Long userId = SessionHelper.getUserId(request);

      /* get vouchers(bills) from work flow .. */
      Long postId  = SessionHelper.getPostId(objectArgs);
      List postLst = new ArrayList();
      postLst.add(postId.toString());
      objectArgs.put("postLst",postLst);
      logger.info("postLst " + objectArgs.get("postLst"));
      List billLst = WorkFlowHelper.getBillsFromWorkFlow(objectArgs);

      Map map=new HashMap();
      map.put("viewPage", viewPage);
      map.put("searchBy", searchBy);
      map.put("searchValue", searchVal);
      map.put("userId",userId);
      map.put("postId",postId);
      VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      List voucherList=voucherImpl.getSearchVoucher(map, billLst);
      objectArgs.put("voucherList", voucherList);
      objRes.setResultValue(objectArgs);
      if(viewPage.equalsIgnoreCase("accVouchFrmCB"))
      {
        objRes.setViewName("accVouhsFromCB");	
      }
      else if(viewPage.equalsIgnoreCase("accVouchForDet"))
      {
        objRes.setViewName("accVouhsForDetPost");	
      }
      else if(viewPage.equalsIgnoreCase("vouchListForDet") || viewPage.equalsIgnoreCase("vouchListPosted"))
      {
        objRes.setViewName("vouchListForDetPost");	
      }
      else if(viewPage.equalsIgnoreCase("vouchListRecordRoom"))
      {
        objRes.setViewName("vouchListInRecordRoom");	
      }
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getSearchVoucher # \n"+e);
    }
    return objRes;
  }

  public ResultObject UnfreezeVoucher(Map objectArgs)
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    try
    {
      VerifiedAccountDAOImpl veriAccDAOImpl = new VerifiedAccountDAOImpl(TrnVerifiedAccount.class,serv.getSessionFactory());
      veriAccDAOImpl.updateUnFreezeVoucher(SessionHelper.getLocationCode(objectArgs));
      List freezeList=veriAccDAOImpl.getFreezedDateAndPost(SessionHelper.getLocationCode(objectArgs));
      objectArgs.put("freezeList",freezeList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("frezeVoucher");
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getFreezedDateAndPost # \n"+e);

    }
    return objRes;
  }

  /**
   * This method to get sub-Treasury posted voucher
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getSubTrsyPostedVouch(Map objectArgs)
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    try
    {
      VoucherDAOImpl voucherDAOImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      List voucherList = voucherDAOImpl.getSubTrsyPostedVouch(SessionHelper.getLangId(objectArgs),SessionHelper.getLocationCode(objectArgs));						
      objectArgs.put("voucherList",voucherList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("subTrsyPostedVouchs");
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getSubTrsyPostedVouch # \n"+e);

    }
    return objRes;
  }

  /**
   * This method to get sub-Treasury voucher details for update a voucher details
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getSubTrsyVoucherDtls(Map objectArgs)
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    try
    {
      String sLocCode = SessionHelper.getLocationCode(objectArgs);
      Long sLangId = SessionHelper.getLangId(objectArgs);
      Long lngBillNo = Long.parseLong(request.getParameter("BillNo"));
      VoucherDAOImpl voucherImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
      CmnLookupMstDAOImpl cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
      BillEdpDAOImpl billEdpDAO = new BillEdpDAOImpl(TrnBillEdpDtls.class,serv.getSessionFactory());
      ReportQueryDAOImpl queryDAO = new ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory());

      NewBillVO lObjBillVO = voucherImpl.getSubTrsyVoucherDtls(lngBillNo,sLocCode,sLangId);
      logger.info("bud tupe is :-"+lObjBillVO.getBudType());
      List fund = cmnDAO.getAllChildrenByLookUpNameAndLang("Fund",SessionHelper.getLangId(objectArgs));
      objectArgs.put("fund",fund);
      List ClassOfExp = cmnDAO.getAllChildrenByLookUpNameAndLang("ClassOfExp",SessionHelper.getLangId(objectArgs));
      objectArgs.put("ClassOfExp",ClassOfExp);

      TrnBillBudheadDtls budjetDtlVo= voucherImpl.getBujectHeadDtls(lngBillNo,SessionHelper.getLocationCode(objectArgs));
      objectArgs.put("budjetDtlVo",budjetDtlVo);

      List lBudjetType = cmnDAO.getAllChildrenByLookUpNameAndLang("OnlineBillBudgetType",SessionHelper.getLangId(objectArgs));
      Object[] lObjTemp = lBudjetType.toArray();
      Object[] lObjBudjetType = new Object[lObjTemp.length];
      for (Object lObj : lObjTemp)
      {
        CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
        lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
      }
      lObjTemp = null;
      objectArgs.put("BudjetType",lObjBudjetType);
      Map lMapBudTypeDtls =getCmnLookUpValueAndDescription(cmnDAO, lObjBillVO.getBudType(),SessionHelper.getLangId(objectArgs));
      objectArgs.put("Selected_BudType",lMapBudTypeDtls);
      objectArgs.put("newBillVO", lObjBillVO);
      List lLstGoNgo = BptmCommonServiceImpl.getLookupValues("go_ngo", SessionHelper.getLangId(objectArgs), objectArgs);
      objectArgs.put("goNgoList",lLstGoNgo);
      List lLstBillType = lObjCmnSrvcDAOImpl.getBillType(SessionHelper.getLangId(objectArgs));
      objectArgs.put("BillType", lLstBillType);
      List lLstTcBill = BptmCommonServiceImpl.getLookupValues("TcBillType",SessionHelper.getLangId(objectArgs),objectArgs);			
      objectArgs.put("TcBillList",lLstTcBill);
      List expEdpList=billEdpDAO.getExpEdpDtl(SessionHelper.getLangId(objectArgs));
      objectArgs.put("expEdpList", expEdpList);
      List subTrsyList=queryDAO.getSubTsry(SessionHelper.getLocationCode(objectArgs));
      objectArgs.put("subTrsyList", subTrsyList);
      objectArgs.put("subjectId", lObjBillVO.getBillType());
      serv.executeService("GET_EDP_DTLS", objectArgs);

      objRes.setResultValue(objectArgs);
      objRes.setViewName("subTreaVouhsDetPost");
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.getSubTrsyVoucherDtls # \n"+e);

    }
    return objRes;
  }

  /**
   * This method to update LOP/LOR configure details.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject updateLopLorCongDtls(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    List lstFormula=(List) objectArgs.get("lstFormula");
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      LOPLORFormulaDAOImpl loplorDaoImpl=new LOPLORFormulaDAOImpl(MstListPayRcpt.class,serv.getSessionFactory());
      for(int i=0;i<lstFormula.size();i++)
      {
        MstListPayRcpt lObjFormula = (MstListPayRcpt)lstFormula.get(i);
        MstListPayRcpt lobjnewFormula=loplorDaoImpl.read(lObjFormula.getId());
        lobjnewFormula.setFormula(lObjFormula.getFormula());
        loplorDaoImpl.update(lobjnewFormula);
      }
      objRes.setResultValue(objectArgs);
      objRes.setViewName("homePage");
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in VoucherServiceImpl.updateLopLorCongDtls # \n"+e);
    }
    return objRes;
  }

  /**
   * This method to get lookup value and description by passing lookup name.
   * @param objectArgs
   * @return ResultObject
   */
  
  private Map getCmnLookUpValueAndDescription(CmnLookupMstDAO lDAOCmnLkUpMst, String lStrLookupName,Long lstrlangId)
  {
    Map lMapValueAndDesc=null;
    logger.info("lookup Name is:-"+lStrLookupName);
    if(lStrLookupName!=null)
    {
      CmnLookupMst lStrCmnLookUpDescVO = lDAOCmnLkUpMst.getLookUpVOByLookUpNameAndLang(lStrLookupName,lstrlangId);
      String lStrCmnLookUpDesc = lStrCmnLookUpDescVO.getLookupDesc();
      String lStrCmnLookUpShrtName = lStrCmnLookUpDescVO.getLookupShortName();
      logger.info("insode the llllllllllloooooooooookkkkkk");
      lMapValueAndDesc = new HashMap();
      lMapValueAndDesc.put("Value", lStrLookupName);
      lMapValueAndDesc.put("Desc", lStrCmnLookUpDesc);
      lMapValueAndDesc.put("Label", lStrCmnLookUpShrtName);
    }

    return lMapValueAndDesc;
  }

}

