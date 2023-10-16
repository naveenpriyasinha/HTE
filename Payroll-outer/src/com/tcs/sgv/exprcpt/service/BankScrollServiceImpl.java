package com.tcs.sgv.exprcpt.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.cheque.dao.RltBillChequeDAOImpl;
import com.tcs.sgv.billproc.cheque.valueobject.RltBillCheque;
import com.tcs.sgv.billproc.cheque.valueobject.TrnChequeDtls;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.common.constant.MessageConstant;
import com.tcs.sgv.common.dao.BankDAOImpl;
import com.tcs.sgv.common.dao.ChequeDetailsDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.util.ExcelParser;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnAttdocMst;
import com.tcs.sgv.common.valueobject.MstBank;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.dao.DSSRptPaymentDtlsDAOImpl;
import com.tcs.sgv.dss.valueobject.RptPaymentDtls;
import com.tcs.sgv.exprcpt.dao.BankPayScrollDAOImpl;
import com.tcs.sgv.exprcpt.dao.BankRcptScrollDAOImpl;
import com.tcs.sgv.exprcpt.dao.BankScrollDAOImpl;
import com.tcs.sgv.exprcpt.valueobject.BankScrollVO;
import com.tcs.sgv.exprcpt.valueobject.TrnBankScrollDetails;
import com.tcs.sgv.exprcpt.valueobject.TrnBsPayEntries;
import com.tcs.sgv.exprcpt.valueobject.TrnBsRcptEntries;
import com.tcs.sgv.lcm.dao.LcChequePostingDAOImpl;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;
import com.tcs.sgv.pdpla.service.PDPLADataServiceImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChq;

/**
 * ClassName BankScrollServiceImpl
 * 
 * Description :- Implemantation of bank scroll service
 * Date of Creation 1 august 2007
 * @author 602409
 * Revision History ===================== Bhavesh 23-Oct-2007 For making
 * changes for code formating
 */
public class BankScrollServiceImpl extends ServiceImpl  
{
  Log logger = LogFactory.getLog(getClass());

  
  /**
   * This method to get bank details in upload bank scroll form.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject showPayUpSclForm(Map<String,Object> objectArgs)
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    BankDAOImpl bsDAOImpl = null;

    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }

      bsDAOImpl = new BankDAOImpl(MstBank.class,serv.getSessionFactory());
      List bankList = bsDAOImpl.getAllBanks(SessionHelper.getLocationCode(objectArgs));
      objectArgs.put("bankList",bankList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("bsUpload");			
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.showPayUpSclForm # \n"+e);
    }
    return objRes;
  }

  /**
   * This method to upload bank scroll on server as well as parsing a excel sheet.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject uploadPayBankScroll(Map<String,Object> objectArgs) 
  {		
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");		
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    BankDAOImpl bsDAOImpl = null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }

      TrnBankScrollDetails bkSclVO=(TrnBankScrollDetails)objectArgs.get("voTrnBankScrollDetails");	
      long bankScID = BptmCommonServiceImpl.getNextSeqNum("trn_bank_scroll_details", objectArgs);
      BankScrollDAOImpl bkSclDAO = new BankScrollDAOImpl(TrnBankScrollDetails.class,serv.getSessionFactory());

      bkSclVO.setBsType("payment");
      bkSclVO.setBsDetailsId(bankScID);
      bkSclVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
      bkSclVO.setDbId(SessionHelper.getDbId(objectArgs));
      bkSclVO.setCreatedUserId(SessionHelper.getUserId(request));
      bkSclVO.setCreatedDate(new Date(System.currentTimeMillis()));
      bkSclVO.setCreatedPostId(SessionHelper.getPostId(objectArgs));
      bkSclVO.setUpdatedUserId(SessionHelper.getUserId(request));
      bkSclVO.setUpdatedDate(new Date(System.currentTimeMillis()));
      bkSclVO.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
      Long attachmentId = bkSclVO.getAttachmentId();

      //System.out.println("Attachment id in service " +  attachmentId );
      if(attachmentId!=null)
      {				
        File tempFile = null;
        CmnAttachmentMstDAOImpl mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
        CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(attachmentId);				
        Iterator it = cmnAttachmentMst.getCmnAttachmentMpgs().iterator();

        if (it!=null && it.hasNext()) 
        {					
          CmnAttachmentMpg cmnAttachmentMpg = (CmnAttachmentMpg) it.next();
          CmnAttdocMst cmnAttDocMst = (CmnAttdocMst) cmnAttachmentMpg.getCmnAttdocMsts().iterator().next();
          cmnAttDocMst.getFinalAttachment();
          String serverPathStr = request.getSession().getServletContext().getRealPath("UPLOADED-FILES");

          File file = new File(serverPathStr);
          if (!file.exists()) 
          {
            file.mkdir();						
          }
          String tempFilePath = serverPathStr+(file.separator.equals("\\")?("\\tempFile_"):"tempFile_") + System.currentTimeMillis();					
          tempFile = new File(tempFilePath);
          new FileOutputStream(tempFilePath).write(cmnAttDocMst.getFinalAttachment(), 0, cmnAttDocMst.getFinalAttachment().length);
        }			
        /* take arraylist and parse excel file .. */

        ArrayList lArrExcelFile = ExcelParser.parseExcel(tempFile);
        BankPayScrollDAOImpl bkPaySclDAO=new BankPayScrollDAOImpl(TrnBsPayEntries.class,serv.getSessionFactory());
        ChequeDetailsDAOImpl chequeDtlsDAO = new ChequeDetailsDAOImpl(TrnChequeDtls.class,serv.getSessionFactory());
        BigDecimal totAmount = new BigDecimal("0") ;
        Integer totCheques = 0;
        Integer lcCheques = 0;
        Integer tcCheques = 0;
        Integer pdplaCheques = 0;
        List<TrnBsPayEntries> tcArrayList=new ArrayList<TrnBsPayEntries>();
        List<TrnBsPayEntries> lcArrayList=new ArrayList<TrnBsPayEntries>();
        List<TrnBsPayEntries> pdplaArrayList=new ArrayList<TrnBsPayEntries>();
        List pdPlaChqArrLst=new ArrayList();
        Short verified = new Short("1");

        if (lArrExcelFile!=null) 
        {
          //save bank scroll vo
          bkSclDAO.create(bkSclVO); 
          //iterate excelfile					
          Iterator itrExcel = lArrExcelFile.iterator();				

          while(itrExcel.hasNext()) 
          {
            ArrayList lArrExcelSheet = (ArrayList) itrExcel.next();
            if (lArrExcelSheet!=null) 
            {
              Iterator itrArrRows = lArrExcelSheet.iterator();
              if(itrArrRows.hasNext())
              {
                itrArrRows.next();
              }
              PhyBillDAOImpl phyBillDAO = new PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
              RltBillChequeDAOImpl rltBillChqDAO = new RltBillChequeDAOImpl(RltBillCheque.class,serv.getSessionFactory());
              while(itrArrRows.hasNext()) 
              {								
                ArrayList lArrCells = (ArrayList) itrArrRows.next();
                BigDecimal amount = null;
                try
                { 
                  amount = new BigDecimal(lArrCells.get(5).toString());
                }
                catch(Exception ex)
                {

                }
                Long bsPayId = BptmCommonServiceImpl.getNextSeqNum("trn_bs_pay_entries", objectArgs);
                TrnBsPayEntries vo=new TrnBsPayEntries();
                vo.setBsPayEntryId(bsPayId);
                vo.setBsDetailId(bankScID);
                vo.setPaymentNo(Integer.parseInt(lArrCells.get(0).toString()));
                vo.setPayeeName((String) lArrCells.get(1));
                vo.setChequeNo(Long.parseLong(lArrCells.get(2).toString()));
                vo.setChequeDate(new SimpleDateFormat("dd/MM/yyyy").parse(lArrCells.get(3).toString()));
                vo.setType((String) lArrCells.get(4));
                if(amount != null && amount.toString().length() < 19)
                {
                  vo.setAmount(amount);
                }
                else
                {
                  List<String> messageList=new ArrayList<String>();
                  messageList.add("Error in upload bankscroll.");
                  objectArgs.put(MessageConstant.MESSAGE_LIST,messageList);
                  List bankList = bsDAOImpl.getAllBanks(SessionHelper.getLocationCode(objectArgs));
                  objectArgs.put("bankList",bankList);
                  objRes.setResultValue(objectArgs);
                  objRes.setViewName("bsUpload");
                  return objRes;
                }
                vo.setIntl((String) lArrCells.get(6));
                vo.setRemarks((String) lArrCells.get(7));
                vo.setCreatedUserId(SessionHelper.getUserId(request));
                vo.setCreatedPostId(SessionHelper.getPostId(objectArgs));
                vo.setCreatedDate(new Date(System.currentTimeMillis()));
                vo.setUpdatedUserId(SessionHelper.getUserId(request));
                vo.setUpdatedPostId(SessionHelper.getPostId(objectArgs));
                vo.setUpdatedDate(new Date(System.currentTimeMillis()));
                vo.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                vo.setDbId(SessionHelper.getDbId(objectArgs));
                if(vo.getType().equals("TC"))
                {
                  tcArrayList.add(vo);
                }
                else if(vo.getType().equals("LC"))
                {
                  lcArrayList.add(vo);
                }
                else if(vo.getType().equals("PD/PLA"))
                {
                  pdplaArrayList.add(vo);
                }
                bkPaySclDAO.create(vo);
                boolean chequeCleared = false;
                /** Added by Bhavesh (602409)*/

                RptPaymentDtls rptPaymentVO=null;
                List paymentList = bkSclDAO.getPaymentVOByChequeNo(Long.parseLong(lArrCells.get(2).toString()));

                if(paymentList.size() > 0) rptPaymentVO=(RptPaymentDtls)paymentList.get(0);
                DSSRptPaymentDtlsDAOImpl lObjPaymentDtlsDAO = new DSSRptPaymentDtlsDAOImpl(RptPaymentDtls.class, serv.getSessionFactory());
                /** End of Bhavesh*/
                if(vo.getType().equals("TC")) 
                {
                  TrnChequeDtls chequeDtl = chequeDtlsDAO.getChequeDtlByNo(vo.getChequeNo());								
                  /** if cheque available then continue else cheque can be consider as stolen cheque .. */
                  if (chequeDtl!=null) 
                  {
                    //System.out.println("Cheque Details :" + chequeDtl + " VALUE :" +chequeDtl.getChequeAmt()+ ":" + vo.getAmount() + " compare:"+chequeDtl.getChequeAmt().compareTo(vo.getAmount())+":");
                    /** if actual cheque amount matches the scroll amount then continue for whether the cheque is dispatched or not .. */
                    if (chequeDtl.getChequeAmt().compareTo(vo.getAmount())==0) 
                    {
                      //System.out.println("Cheque Amount same");
                      /** get all bills associated with the cheque and verify that the cheque status is equal to cheque issue .. */
                      List billNos = rltBillChqDAO.getBillFromCheque(chequeDtl.getChequeId());											
                      boolean dispatch = true; // set by default cheque dispatched												
                      if (billNos!=null && billNos.size()>0) 
                      {						
                        //System.out.println("Bill Size is not null");
                        for (int i = 0; i < billNos.size(); i++) 
                        {
                          RltBillCheque rltBillChq = (RltBillCheque) billNos.get(i);
                          TrnBillRegister billRg = phyBillDAO.read(rltBillChq.getBillNo());

                          //System.out.println("STATUS : " +billRg.getChequeStatus());
                          /** if cheque status doesn't matches then mark the cheque undispatched .. */
                          if (billRg==null || billRg.getChequeStatus()==null || !billRg.getChequeStatus().equalsIgnoreCase(Constants.STAT_CHEQ_ISSUE)) 
                          {
                            //System.out.println("UnDispatched cheque");
                            dispatch = false;
                            break;
                          } 
                        }											
                        //System.out.println("Dispatch cheque : "+dispatch);
                        /** if cheque is dispatched then set the cleared date of the cheque .. */
                        if (dispatch==true) 
                        {
                          chequeDtl.setClearedDt(new SimpleDateFormat("dd/MM/yyyy").parse(lArrCells.get(3).toString()));
                          chequeDtlsDAO.update(chequeDtl);

                          if(rptPaymentVO !=null)
                          { 
                            rptPaymentVO.setChqClrDt(new SimpleDateFormat("dd/MM/yyyy").parse(lArrCells.get(3).toString()));
                            lObjPaymentDtlsDAO.update(rptPaymentVO);
                          }
                          chequeCleared = true;
                        }
                      } // end of if (billNos!=null && billNos.size()>0) 
                    } // end of if (chequeDtl.getChequeAmt().compareTo(vo.getAmount())!=0)
                  } // end of if (chequeDtl!=null) 
                }
                else if (vo.getType().equals("LC")) 
                {
                  LcChequePostingDAOImpl lcChequeDAO = new LcChequePostingDAOImpl(TrnLcChequePosting.class,serv.getSessionFactory());
                  TrnLcChequePosting lcCheqPosting =  lcChequeDAO.getChequeByNo(new BigDecimal(vo.getChequeNo()));

                  if (lcCheqPosting!=null) 
                  {
                    //System.out.println("ssssssssss ");
                    if(lcCheqPosting.getChequeAmt().compareTo(vo.getAmount())==0) 
                    {
                      //System.out.println("xxxxxxxxxxxxx ---- " + lArrCells.get(3).toString());
                      //System.out.println(" "+new SimpleDateFormat("dd/MM/yyyy").parse(lArrCells.get(3).toString()));
                      lcCheqPosting.setChqClrDt(new SimpleDateFormat("dd/MM/yyyy").parse(lArrCells.get(3).toString()));
                      lcChequeDAO.update(lcCheqPosting);
                      //Added By Bhavesh
                      if(rptPaymentVO !=null)
                      { 
                        rptPaymentVO.setChqClrDt(new SimpleDateFormat("dd/MM/yyyy").parse(lArrCells.get(3).toString()));
                        lObjPaymentDtlsDAO.update(rptPaymentVO);
                      }
                      //End By Bhavesh
                      chequeCleared = true;
                    }
                  }
                }
                else if (vo.getType().equals("PD/PLA")) 
                {
                  TrnPdChq pdPlaChqVo = new TrnPdChq();
                  pdPlaChqVo.setInventoryId(new BigDecimal(1));
                  pdPlaChqVo.setChqNo((vo.getChequeNo().intValue()));
                  pdPlaChqVo.setChqClearanceDate(vo.getChequeDate());
                  pdPlaChqVo.setPayeeNm(vo.getPayeeName());
                  pdPlaChqVo.setChqIssueDate(vo.getChequeDate());
                  pdPlaChqVo.setStatus(true);
                  pdPlaChqVo.setAmount(amount);
                  pdPlaChqArrLst.add(pdPlaChqVo);
                  //System.out.println("Inside PDPLA Cheques");
                  PDPLADataServiceImpl pdplaDataSrvc=new PDPLADataServiceImpl();
                  Map<String,Object> inputMap=new HashMap<String,Object>();
                  inputMap.put("map",objectArgs);
                  inputMap.put("TrnPdChqVOArrlst",pdPlaChqArrLst);
                  pdplaDataSrvc.insertChq(inputMap);

                }

                /** if cheque not cleared then mark the scroll unverified .. */
                if(!chequeCleared) verified=0;
                totCheques++;
                totAmount=totAmount.add(vo.getAmount());
                if(vo.getType().equalsIgnoreCase("TC")) tcCheques++;
                if(vo.getType().equalsIgnoreCase("LC")) lcCheques++;
                if(vo.getType().equalsIgnoreCase("PD/PLA")) pdplaCheques++;
              } //end of while(itrArrRows.hasNext())

              bkSclVO.setVerified(verified);
              bkSclDAO.update(bkSclVO);
            } // end of if (lArrExcelSheet!=null) 
          } // end of while(itrExcel.hasNext()) 					
        } // end of if (lArrExcelFile!=null)

        /** remove temporary file .. */
        try {if (tempFile!=null) tempFile.delete();} catch(Exception ex){}
        objectArgs.put("totAmount", totAmount);
        objectArgs.put("totCheques", totCheques);
        objectArgs.put("lcCheques", lcCheques);
        objectArgs.put("pdplaCheques", pdplaCheques);
        objectArgs.put("tcCheques", tcCheques);
        objectArgs.put("bankScrollVO",bkSclVO);
        objectArgs.put("tcArrayList", tcArrayList);
        objectArgs.put("lcArrayList", lcArrayList);
        objectArgs.put("pdplaArrayList",pdplaArrayList);
        objRes.setResultValue(objectArgs);
        objRes.setViewName("bsResult");			
      } else {
        List<String> messageList=new ArrayList<String>();
        messageList.add("Error in upload bankscroll.");
        objectArgs.put(MessageConstant.MESSAGE_LIST,messageList);
        List bankList = bsDAOImpl.getAllBanks(SessionHelper.getLocationCode(objectArgs));
        objectArgs.put("bankList",bankList);
        objRes.setResultValue(objectArgs);
        objRes.setViewName("bsUpload");
      } // end of if(attachmentId!=null) 
    }
    catch(Exception e)
    {	
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.uploadPayBankScroll # \n"+e);
    }
    return objRes;
  }


  /**
   * This method to show all bank that are not verified.
   * @param objectArgs
   * @return
   */
  public ResultObject showPayVrfForm(Map<String,Object> objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    BankDAOImpl bankImpl = null;

    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      bankImpl = new BankDAOImpl(MstBank.class,serv.getSessionFactory());
      List bankList=bankImpl.getUnVerifyBank(SessionHelper.getLocationCode(objectArgs));
      objectArgs.put("bankList", bankList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("bsVerify");	
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.showPayVrfForm # \n"+e);
    }
    return objRes;
  }

  /**
   * This method to get all date by selecting the bank(using AJAX).
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getAllDateByBank(Map<String,Object> objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    BankDAOImpl bankImpl = null;
    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      String bankCode=null;
      bankCode=request.getParameter("bankCode");
      bankImpl = new BankDAOImpl(MstBank.class,serv.getSessionFactory());
      List dateList=bankImpl.getUnVerifyDate(bankCode,SessionHelper.getLocationCode(objectArgs));
      //System.out.println("Outside Service Impl");
      String xmlData = new AjaxXmlBuilder().addItems(dateList, "bsDesc","bsDesc").toString();
      objectArgs.put("ajaxKey", xmlData);
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
      logger.error("Exception occured in BankScrollServiceImpl.getAllDateByBank # \n"+e);
    }
    return objRes;
  }


  /**
   * This method to get un-verified bank scroll details.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getUnVrfPaySclDtl(Map<String,Object> objectArgs) 
  {
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
      String bankCode=request.getParameter("cmbbank");
      String strDate=request.getParameter("cmbdate");
      Map<String,Object> map=new HashMap<String,Object>();
      map.put("bankCode",bankCode);
      map.put("date",strDate);
      map.put("userId",Long.parseLong(SessionHelper.getUserId(request).toString()));

      BankScrollDAOImpl bankScImpl = new BankScrollDAOImpl(TrnBankScrollDetails.class,serv.getSessionFactory());
      /* get list of unverified Cheques detail.. */
      Long bsDetailId= bankScImpl.getBsDetailId(map);
      List chequeList = bankScImpl.getUnVrfPaySclDtl(map);

      if ((chequeList==null) || (chequeList!=null && chequeList.size()==0)) 
      {				
        TrnBankScrollDetails bankSclVo=bankScImpl.read(bsDetailId);
        bankSclVo.setVerified(new Short("1"));
        bankScImpl.update(bankSclVo);
      }

      objectArgs.put("bsDetailId",bsDetailId);
      objectArgs.put("chequeList",chequeList);

      if(chequeList!=null && chequeList.size()>0)
      {
        objectArgs.put("chequeListSize",chequeList.size());
      }
      objRes.setResultValue(objectArgs);
      objRes.setViewName("bsVerfResult");			
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.getUnVerifiedScrollDtl # \n"+e);
    }
    return objRes;		
  }

  /**
   * This method to update correct change bank scrolls as verified.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject updateUnVrfPayDtl(Map<String,Object> objectArgs) 
  {
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
      HttpSession session=request.getSession();
      long bsDetailId=Long.parseLong(request.getParameter("bsDetailId").toString());
      long bankId=Long.parseLong(request.getParameter("cmbbank"));
      String strDate=request.getParameter("cmbdate");			
      Map<String,Object> map=new HashMap<String,Object>();
      map.put("bankCode",bankId);
      map.put("date",strDate);
      map.put("userId",SessionHelper.getUserId(request));
      Map<String,Object> objectArg=new HashMap<String,Object>();

      objectArg.put("chequesNo",request.getParameterValues("chequeNo"));
      objectArg.put("scrollsAmt",request.getParameterValues("ScrollAmt"));
      objectArg.put("clearDates",request.getParameterValues("clearDate"));

      objectArg.put("userId",Long.parseLong(session.getAttribute("userId").toString()));		
      BankScrollDAOImpl bankScImpl = new BankScrollDAOImpl(TrnBankScrollDetails.class,serv.getSessionFactory());			
      /* Update The changed Amount .. */
      bankScImpl.updateUnVrfPayDtl(objectArg);
      /* get list of unverified Cheques detail.. */
      List chequeList = bankScImpl.getUnVrfPaySclDtl(map);
      if ((chequeList==null) || (chequeList!=null && chequeList.size()==0)) {

        TrnBankScrollDetails bankSclVo=bankScImpl.read(bsDetailId);
        bankSclVo.setVerified(new Short("1"));
        bankScImpl.update(bankSclVo);
      }
      objectArgs.put("chequeList",chequeList);
      objectArgs.put("bsDetailId",bsDetailId);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("bsVerfResult");			
    } 
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.getUnVerifiedScrollDtl # \n"+e);
    }
    return objRes;		
  }

  /**
   * This method to show unverified banks ans date.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject showRecUpSclForm(Map<String,Object> objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      logger.info("Session factory is " + serv.getSessionFactory());
      BankDAOImpl bsDAOImpl = new BankDAOImpl(MstBank.class,serv.getSessionFactory());
      List bankList = bsDAOImpl.getAllBanks(SessionHelper.getLocationCode(objectArgs));
      List bsDetList = bsDAOImpl.getScrollDetails(SessionHelper.getLocationCode(objectArgs));
      Map<String,Object> result = new HashMap<String,Object>();
      result.put("bankList",bankList);
      result.put("bsDetList",bsDetList);
      objRes.setResultValue(result);
      objRes.setViewName("bsRcptUpload");			
    }
    catch(Exception e)
    {
      objRes.setResultValue(null);
      objRes.setThrowable(e);
      objRes.setResultCode(ErrorConstants.ERROR);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.showRecUpSclForm # \n"+e);
    }
    return objRes;
  }


  /**
   * This method to upload Receipt(Challan)bank scroll on server as well as parsing a excel sheet.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject uploadRecBankScroll(Map<String,Object> objectArgs) 
  {		
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

    Long userId = SessionHelper.getUserId(request);
    Long postId = SessionHelper.getPostId(objectArgs);
    long dbId = SessionHelper.getDbId(objectArgs);
    String locId = SessionHelper.getLocationCode(objectArgs);

    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }

      TrnBankScrollDetails bkSclVO=(TrnBankScrollDetails)objectArgs.get("voTrnBankScrollDetails");
      long bankScId = BptmCommonServiceImpl.getNextSeqNum("trn_bank_scroll_details", objectArgs);
      BankScrollDAOImpl bkSclDAO = new BankScrollDAOImpl(TrnBankScrollDetails.class,serv.getSessionFactory());

      bkSclVO.setBsDetailsId(bankScId);
      bkSclVO.setBsType("receipt");
      bkSclVO.setCreatedDate(new Date(System.currentTimeMillis()));
      bkSclVO.setCreatedUserId(userId.longValue());
      bkSclVO.setCreatedPostId(postId.longValue());
      bkSclVO.setUpdatedDate(new Date(System.currentTimeMillis()));
      bkSclVO.setUpdatedUserId(userId.longValue());
      bkSclVO.setUpdatedPostId(postId.longValue());			

      bkSclDAO.create(bkSclVO);
      Long attachmentId = bkSclVO.getAttachmentId();

      //System.out.println("Attachment ID : :::::::::::::::::::::::::::::::::::::::::::::::::::::::::: " + attachmentId);
      logger.error("Error Occured Before The atttachemnent Logic1" + attachmentId);

      if(attachmentId!=null) 
      {				
        File tempFile = null;
        CmnAttachmentMstDAOImpl mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
        CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(attachmentId);				
        Iterator it = cmnAttachmentMst.getCmnAttachmentMpgs().iterator();

        if (it!=null && it.hasNext()) 
        {					
          CmnAttachmentMpg cmnAttachmentMpg = (CmnAttachmentMpg) it.next();
          CmnAttdocMst cmnAttDocMst = (CmnAttdocMst) cmnAttachmentMpg.getCmnAttdocMsts().iterator().next();
          cmnAttDocMst.getFinalAttachment();
          String serverPathStr = request.getSession().getServletContext().getRealPath("UPLOADED-FILES");

          File file = new File(serverPathStr);
          if (!file.exists()) 
          {
            file.mkdir();						
          }
          String tempFilePath = serverPathStr+(file.separator.equals("\\")?("\\tempFile_"):"tempFile_") + System.currentTimeMillis();					
          tempFile = new File(tempFilePath);
          new FileOutputStream(tempFilePath).write(cmnAttDocMst.getFinalAttachment(), 0, cmnAttDocMst.getFinalAttachment().length);
        }			

        /* take arraylist and parse excel file .. */
        ArrayList lArrExcelFile = ExcelParser.parseExcel(tempFile);				
        BankRcptScrollDAOImpl brecSclImpl=new BankRcptScrollDAOImpl(TrnBsRcptEntries.class,serv.getSessionFactory());
        Integer totReceipts = new Integer(0);
        logger.error("Error Occured Before The atttachemnent Logic2--------------------------");

        if (lArrExcelFile!=null ) 
        {				
          Iterator itrExcel = lArrExcelFile.iterator();						
          while(itrExcel.hasNext())
          {
            ArrayList lArrExcelSheet = (ArrayList) itrExcel.next();
            if (lArrExcelSheet!=null) 
            {
              ArrayList wfList = new ArrayList();
              Iterator itrArrRows = lArrExcelSheet.iterator();
              if (itrArrRows.hasNext()) itrArrRows.next();
              while(itrArrRows.hasNext()) 
              {
                ArrayList lArrCells = (ArrayList) itrArrRows.next();
                Long lLngObjId = BptmCommonServiceImpl.getNextSeqNum("trn_bs_rcpt_entries", objectArgs);
                //System.out.println("Receipt data : " + lArrCells.get(0) + " -- " + lArrCells.get(1) + " == " + lArrCells.get(2) + " -- " + lArrCells.get(3));
                //method (TrnBsRcptEntries)
                TrnBsRcptEntries vo= new TrnBsRcptEntries();
                vo.setBsRcptEntryId(lLngObjId);
                vo.setBsDetailId(bankScId);
                vo.setReceiptNo(Integer.parseInt(lArrCells.get(0).toString()));
                vo.setDepositorName((String) lArrCells.get(1));
                vo.setMajorHead((String) lArrCells.get(2)); 
//              //System.out.println("Amount : " + lArrCells.get(3).toString());
                vo.setAmount(new BigDecimal(lArrCells.get(3).toString()));
                vo.setCreatedUserId(userId.longValue());
                vo.setCreatedPostId(postId.longValue());
                vo.setCreatedDate(new Date(System.currentTimeMillis()));
                vo.setUpdatedUserId(userId.longValue());
                vo.setUpdatedPostId(postId.longValue());
                vo.setUpdatedDate(new Date(System.currentTimeMillis()));
                vo.setLocationCode(locId);
                vo.setDbId(dbId);							
                brecSclImpl.create(vo);
                wfList.add(vo);

                totReceipts++;
              } //End of While
              objectArgs.put("trnBsRcptEntries",wfList);
              objectArgs.put("receivedByBank", bkSclVO.getBsDate());
              new ReceiptServiceImpl().createReceiptFromWF(objectArgs);
            } //End of if
          } //End of while
          //System.out.println("Total Receipts " + totReceipts);
          Map<String,Integer> result = new HashMap<String,Integer>();
          result.put("totReceipts",totReceipts);
          objRes.setResultValue(result);
        } //End of if
        /** remove temporary file .. */
        try {if (tempFile!=null) tempFile.delete();} catch(Exception ex){}
      }

      objRes.setResultValue(new HashMap());
      objectArgs.put("MESSAGECODE",(long)1050);
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
      logger.error("Exception occured in BankScrollServiceImpl.uploadRecBankScroll  # \n"+e);
    }
    return objRes;
  }

  /**
   * This method to get bank details for upload receipt bank scrolls.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject showRecVrfForm(Map<String,Object> objectArgs) 
  {	
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }

      Map result = new HashMap();
      objRes.setResultValue(result);
      objRes.setViewName("bsRcptVerify");			
    }
    catch(Exception e)
    {
      objRes.setThrowable(e);
      objRes.setResultCode(-1);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.showRecVrfForm  # \n"+e);
    }
    return objRes;

  }

  /**
   * this method to get mis-match scroll details.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getUnVrfRecSclDtl(Map<String,Object> objectArgs) 
  {
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
      //System.out.println("Inside The Service Impl..............\n");
      HttpSession session=request.getSession();			
      BankScrollDAOImpl bankScImpl = new BankScrollDAOImpl(TrnBankScrollDetails.class,serv.getSessionFactory());
      /* get list of unverified Cheques detail.. */
      List chequeList = bankScImpl.getUnVrfRecSclDtl(Long.parseLong(session.getAttribute("userId").toString()));
      //System.out.println("Inside out ");
      objectArgs.put("chequeList",chequeList);

      objRes.setResultValue(objectArgs);
      objRes.setViewName("bsRcptVerfResult");			
    } 
    catch(Exception e)
    {
      objRes.setThrowable(e);
      objRes.setResultCode(-1);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.getUnVrfRecSclDtl # \n"+e);
    }
    return objRes;	
  }

  /**
   * This method to update verified flag.
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject updateUnVrfRecDtl(Map<String,Object> objectArgs) 
  {
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

      HttpSession session=request.getSession();
      Map<String,Object> objectArg=new HashMap<String,Object>();
      objectArg.put("receipNos",request.getParameterValues("receipNo"));
      objectArg.put("sclMjrHeads",request.getParameterValues("sclMjrHead"));
      objectArg.put("sclAmt",request.getParameterValues("sclAmt"));
      objectArg.put("userId",Long.parseLong(session.getAttribute("userId").toString()));		

      BankScrollDAOImpl bankScImpl = new BankScrollDAOImpl(TrnBankScrollDetails.class,serv.getSessionFactory());
      /* Update The changed Amount .. */
      bankScImpl.updateUnVrfRecDtl(objectArg);
      /* get list of unverified Cheques detail.. */
      List chequeList = bankScImpl.getUnVrfRecSclDtl(Long.parseLong(session.getAttribute("userId").toString()));

      objectArgs.put("chequeList",chequeList);
      objRes.setResultValue(objectArgs);
      objRes.setViewName("bsRcptVerfResult");			
    } catch(Exception e)
    {
      objRes.setThrowable(e);
      objRes.setResultCode(-1);
      objRes.setViewName("errorPage");
      e.printStackTrace();
      logger.error("Exception occured in BankScrollServiceImpl.updateUnVrfRecDtl # \n"+e);
    }
    return objRes;	
  }



  /**
   * This method to get particular scroll details
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getAllScrollDtls(Map<String,Object> objectArgs)
  {
    //System.out.println("Inside getAllScrolls");

    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    HttpSession session=request.getSession();	
    long userId=Long.parseLong(session.getAttribute("userId").toString());

    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
      BankScrollDAOImpl bsDAOImpl = new BankScrollDAOImpl(TrnBankScrollDetails.class,serv.getSessionFactory());		
      List scrollList = bsDAOImpl.getAllScrolls(userId);
      CmnAttachmentMstDAOImpl mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());

      if(scrollList!=null) 
      {
        Iterator it = scrollList.iterator();
        while(it.hasNext())
        {
          BankScrollVO vo = (BankScrollVO) it.next();
          if (vo!=null && vo.getAttachmentId()!=null) 
          {
            try 
            {
              //System.out.println("Attachment ID : " + vo.getAttachmentId());
              CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(vo.getAttachmentId());
              CmnAttachmentMpg cmnAttachmentMpg = (CmnAttachmentMpg)cmnAttachmentMst.getCmnAttachmentMpgs().iterator().next();
              vo.setAttachmentSrNo(cmnAttachmentMpg.getSrNo());
              //System.out.println("Cmn Attachment Mpg" + cmnAttachmentMpg.getSrNo());
            }
            catch(Exception ex) 
            {
              ex.printStackTrace();
              logger.error("Exception occured in BankScrollServiceImpl.getAllScrollDtls # \n"+ex);
            }
          }
        }
      }
      Map<String,List> result = new HashMap<String,List>();
      result.put("scrollList",scrollList);

      //String sop=request.getParameter("bsDetailId");			
      objRes.setResultValue(result); 
      objRes.setViewName("BankScrollRpt");


    }
    catch(Exception e)
    {
      objRes.setThrowable(e);
      objRes.setResultCode(-1);
      objRes.setViewName("errorPage");
      logger.error("Exception occured in BankScrollServiceImpl.getAllScrollDtls # \n"+e);
      e.printStackTrace();
    }
    return objRes;

  }

  /**
   * This method to show uploaded bank scroll detils
   * @param objectArgs
   * @return ResultObject
   */
  public ResultObject getAllScrollPayRecDtls(Map objectArgs) 
  {
    ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    Map<String,Object> result = new HashMap<String,Object>();

    try
    {
      if (objRes==null || objectArgs == null )
      {				
        objRes.setResultCode(-1);
        return objRes; 
      }
      ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");			

      /* get bank scroll meta information by bank scroll detail id .. */
      BankScrollDAOImpl bsDAOImpl = new BankScrollDAOImpl(TrnBankScrollDetails.class,serv.getSessionFactory());		
      //String bsType = request.getParameter("bsType"); 			
      Long bsDetailId = new Long(request.getParameter("bsDetailId"));
      TrnBankScrollDetails bsScrollDtls = bsDAOImpl.read(bsDetailId);

      result.put("bsDtls", bsScrollDtls);

      /* depending on the type (payment or receipt) get cheques/receipt information .. */
      if(bsScrollDtls.getBsType().equalsIgnoreCase("payment"))
      {
        BankPayScrollDAOImpl bkPayDAO = new BankPayScrollDAOImpl(TrnBsPayEntries.class,serv.getSessionFactory());
        bkPayDAO.getPayDtlsByBkSclId(bsDetailId);
        List scrollList = bkPayDAO.getPayDtlsByBkSclId(bsDetailId);
        result.put("scrollList",scrollList);				
        objRes.setViewName("bankScrollPayment");			   
      } 
      else if(bsScrollDtls.getBsType().equalsIgnoreCase("receipt"))
      {
        BankRcptScrollDAOImpl bkRcptDAO = new BankRcptScrollDAOImpl(TrnBsRcptEntries.class,serv.getSessionFactory());
        List scrollList = bkRcptDAO.getRcptDtlsByBkSclId(bsDetailId);
        result.put("scrollList",scrollList);				
        objRes.setViewName("bankScrollReceipt");
      }
      objRes.setResultValue(result);
    }
    catch(Exception e)
    {
      objRes.setThrowable(e);
      objRes.setResultCode(-1);
      objRes.setViewName("errorPage");
      logger.error("Exception occured in BankScrollServiceImpl.getAllScrollPayRecDtls # \n"+e);
      e.printStackTrace();
    }
    return objRes;				
  }


}


