package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.dao.ArrearBillDaoImpl;
import com.tcs.sgv.eis.dao.ChangeStatementDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PaybillRegMpgDAOImpl;
import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.valueobject.TokenNumberCustomVO;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ConsolidatedBillMstDaoImpl;
import com.tcs.sgv.eis.zp.ReportingDDO.valueobject.ConsolidatedBillMst;
import com.tcs.sgv.eis.zp.ZpReportingDDO.dao.ZpReportingDAO;
import com.tcs.sgv.eis.zp.ZpReportingDDO.dao.ZpReportingDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDdoOffice.dao.ZpDDOOfficeMstDAOImpl;

public class ChangeStatementServiceImpl extends ServiceImpl {
    Log logger = LogFactory.getLog( getClass() );
    ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");

    //added by shailesh for change statement
    public ResultObject ViewChangeStatament(Map objectArgs) 
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try 
        {
            int msg = 0;
            String paybillTypeId = rb.getString("paybillTypeId").toString();// paybillType Id from Property File.	
            ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
            long langId = Long.parseLong(loginMap.get("langId").toString());
            long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            Map voToService = (Map)objectArgs.get("voToServiceMap");
            TokenNumberDAOImpl TokenDAO = new TokenNumberDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
            ChangeStatementDAOImpl changeStatmntDAOImpl = new ChangeStatementDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());

            TokenNumberCustomVO customVO = new TokenNumberCustomVO();
            List listedData = new ArrayList();	
            SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

            Date dt = new Date();
            String Year=(sdfObj.format(dt)).toString();
            sdfObj = new SimpleDateFormat("MM");
            int Month= Integer.parseInt((sdfObj.format(dt)).toString());
            String billNo = "";
            String billStatus = "";
            String userType = "";
            if(voToService != null)
            {
                if(voToService.get("Month")!=null)
                    Month=Integer.parseInt(voToService.get("Month").toString());
                if(voToService.get("Year")!=null)
                    Year=(String)voToService.get("Year").toString();
                if(voToService.get("billNo")!=null)
                    billNo=(String)voToService.get("billNo").toString();
                if(voToService.get("billtype")!=null)
                    paybillTypeId = (String)voToService.get("billtype").toString();
                if(voToService.get("billStatus")!=null)
                    billStatus = (String)voToService.get("billStatus").toString();
                if(voToService.get("User")!=null)
                    userType = (String)voToService.get("User").toString();
            }
            logger.info(":::>>>>In service class month = " + Month + "  and Year = " + Year + " and Bill Number = " + billNo + " & billtypeId = " + paybillTypeId + " billStatus = " + billStatus+"::Loc ID :: "+locId+"::User Tyep :: "+userType);
            objectArgs.put("userType", userType);
            List DataList = changeStatmntDAOImpl.getChngeStmntDataToDisplay(Month, Year, billNo, paybillTypeId,billStatus,locId,userType);

            long id  = 0;
            String schemeCode ="";
            String schemeName   = "";
            double billGross  = 0;
            double billNetTotal  = 0;
            long billGrpid      = 0;
            String billDescip ="";
            long appFlag=0;
            long grossTotalAmount=0;
            long netTotalAmount=0;


            if( DataList!=null )
            {
                for (int i = 0; i < DataList.size(); i++) 
                {
                    customVO = new TokenNumberCustomVO();
                    Object rowList[] = (Object[]) DataList.get(i);
                    /*
					if(rowList[0] != null && !(rowList[0].toString().trim()).equals(""))
					{
						id = Long.parseLong(rowList[0].toString());
						customVO.setId(id);
					}*/
                    if(rowList[0] != null && !(rowList[0].toString().trim()).equals(""))
                    {
                        schemeCode = rowList[0].toString();
                        customVO.setSchemeCode(schemeCode);
                    }
                    if(rowList[1] != null && !(rowList[1].toString().trim()).equals(""))
                    {
                        schemeName = rowList[1].toString();
                        customVO.setSchemeName(schemeName);
                    }

                    if(rowList[2] != null && !(rowList[2].toString().trim()).equals(""))
                    {
                        grossTotalAmount = Long.parseLong(rowList[2].toString());
                        customVO.setGrossTotalAmount(grossTotalAmount);
                    }

                    if(rowList[3] != null && !(rowList[3].toString().trim()).equals(""))
                    {
                        netTotalAmount = Long.parseLong(rowList[3].toString());
                        customVO.setNetTotalAmount(netTotalAmount);
                    }
                    if(rowList[4] != null && !(rowList[4].toString().trim()).equals(""))
                    {
                        billGrpid = Long.parseLong(rowList[4].toString());
                        customVO.setBillGrpid(billGrpid);
                    }
                    if(rowList[5] != null && !(rowList[5].toString().trim()).equals(""))
                    {
                        billDescip = rowList[5].toString();
                        customVO.setBillDescip(billDescip);
                    }
                    if(rowList[6] != null && !(rowList[6].toString().trim()).equals(""))
                        appFlag = Long.parseLong(rowList[6].toString());
                    if(appFlag==7 && userType.equals("DDO"))
                        customVO.setBillStatus("PENDING");
                    else if(appFlag==7)
                        customVO.setBillStatus("FORWARDED");					
                    else if(appFlag==8)
                        customVO.setBillStatus("APPROVED");
                    else if(appFlag==6)
                        customVO.setBillStatus("CREATED");	
                    else if (appFlag==0)
                        customVO.setBillStatus("PAYGENERATED");
                    else if (appFlag==1)
                        customVO.setBillStatus("PAYAPPROVED");
                    else if (appFlag==9)
                        customVO.setBillStatus("REJECTED");	

                    else
                        customVO.setBillStatus(" -- ");

                    if(rowList[7] != null)
                        customVO.setReasonForRejection(rowList[7].toString());
                    else customVO.setReasonForRejection("");

                    listedData.add(customVO);
                }//end for
            }//end if

            List billList = new ArrayList(); 
            List<MstDcpsBillGroup> BillList = new ArrayList();		
            billList = TokenDAO.getBillListForDisplay(locId);
            for(Iterator itr=billList.iterator();itr.hasNext();)
            {    			
                Object[] row = (Object[])itr.next();
                MstDcpsBillGroup mstDcps = new MstDcpsBillGroup();	
                mstDcps.setDcpsDdoBillGroupId(Long.parseLong(row[0].toString()));
                mstDcps.setDcpsDdoCode((row[1].toString()));		 	
                BillList.add(mstDcps);

            }   

            CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
            List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
            List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
            logger.info(" bill number from service in view :" + billNo);
            objectArgs.put("monthList", monthList);
            objectArgs.put("yearList", yearList);
            objectArgs.put("curmonth", Month);
            objectArgs.put("curyear", Year);
            objectArgs.put("DataList", listedData);
            objectArgs.put("BillList",BillList);
            objectArgs.put("curbill", billNo);
            objectArgs.put("billtype", paybillTypeId);
            objectArgs.put("msg", "");
            objectArgs.put("currBillStatus", billStatus);				
            resObj.setViewName("viewChangeStatement");
            resObj.setResultCode(0);
            resObj.setResultValue(objectArgs);
        } 

        catch (Exception ex) {
            resObj.setThrowable(ex);
            logger.info("Token Number Screen Showing Error"+ex.getMessage());
            //System.out.println("Token Number Screen Showing Error"+ex.getMessage());

            logger.error("Token Number Screen Showing Error", ex);

            resObj.setResultCode(-1);
        }
        return resObj;
    }

    public ResultObject forwardChngStmnt(Map objectArgs){
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try 
        {
            logger.info("inside frwrd CS method");			
            ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

            long month = request.getParameter("month")!= null && !request.getParameter("month").equals("") ? Long.parseLong(request.getParameter("month").toString()):0l; 
            long year = request.getParameter("year")!= null && !request.getParameter("year").equals("") ? Long.parseLong(request.getParameter("year").toString()):0l;
            String lStrhidBillNo = request.getParameter("billNum")!= null && !request.getParameter("billNum").equals("") ? request.getParameter("billNum").toString():"";
            logger.info("====> 1.  lStrhidBillNo :: "+lStrhidBillNo);
            String[] lArrBillNoList=null;
            if(lStrhidBillNo!=null || lStrhidBillNo!="")
            {
                lArrBillNoList = lStrhidBillNo.split(",");
                logger.info("====> 1.  lArrBillNoList :: "+lArrBillNoList.length);
            }
            logger.info("===> in if...................");
            PaybillHeadMpg paybillHeadmpg=null;
            PaybillHeadMpg paybillHeadmpgNew = null;
            StringBuffer paybillSBf=new StringBuffer();

            int approveFlag=6;

            int SettingValue=7;
            String status = null;
            ChangeStatementDAOImpl changeStatmntDAOImpl = new ChangeStatementDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());

            PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
            for(int i=0;i<lArrBillNoList.length;i++)
            {
                long lbillid = Long.valueOf(lArrBillNoList[i]);
                logger.info("1111111 in discardBill i month year and bill number : "+ i+ month + " " + year + " " + lbillid);

                //status = changeStatmntDAOImpl.getApproveFlagForGivenBill(lbillid, month, year);
                List<PaybillHeadMpg> paybillHeadmpgList  = paybillHeadMpgDAOImpl.getpaybillheadFromid(lbillid,approveFlag,month,year);

                for(int k=0;k<paybillHeadmpgList.size();k++)
                {
                    paybillHeadmpg=paybillHeadmpgList.get(k);
                    paybillHeadmpg.setApproveFlag(SettingValue);
                    paybillHeadMpgDAOImpl.update(paybillHeadmpg);
                    long paybillId = paybillHeadmpg.getHrPayPaybill();
                    logger.info("==> paybillId :: "+paybillId);
                }
                status =  "Forwarded successfully.";
            }


            paybillSBf.append("<paybillMapping>");
            paybillSBf.append("<status>").append(status).append("</status>");
            paybillSBf.append("</paybillMapping>");  

            logger.info("lStrhidBillNo ="+lStrhidBillNo);

            logger.info("status ="+status);

            Map map = objectArgs ;
            String paybillStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();

            logger.info(" the string buffer is :"+paybillStatus);
            map.put("ajaxKey", paybillStatus);
            resObj.setResultCode(ErrorConstants.SUCCESS);
            //logger.info("error");
            resObj.setResultValue(map);
            resObj.setViewName("ajaxData");
            logger.info(" SERVICE COMPLETE :");


            /*ResultObject resultBill=serv.executeService("ViewTokenNumber", objectArgs);
			objectArgs = (Map)resultBill.getResultValue();

			resObj.setViewName("viewChangeStatement");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);*/
        } 

        catch (Exception ex) {
            resObj.setThrowable(ex);
            logger.info("Token Number Screen Showing Error"+ex.getMessage());
            //System.out.println("Token Number Screen Showing Error"+ex.getMessage());

            logger.error("Token Number Screen Showing Error", ex);

            resObj.setResultCode(-1);
        }
        return resObj;
    }



    public ResultObject changeStatementApproval(Map objectArgs){
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try 
        {
            logger.info("inside frwrd CS method");			
            ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

            long month = request.getParameter("month")!= null && !request.getParameter("month").equals("") ? Long.parseLong(request.getParameter("month").toString()):0l; 
            long year = request.getParameter("year")!= null && !request.getParameter("year").equals("") ? Long.parseLong(request.getParameter("year").toString()):0l;
            String lStrhidBillNo = request.getParameter("billNum")!= null && !request.getParameter("billNum").equals("") ? request.getParameter("billNum").toString():"";
            logger.info("====> 1.  lStrhidBillNo :: "+lStrhidBillNo);
            String[] lArrBillNoList=null;
            if(lStrhidBillNo!=null || lStrhidBillNo!="")
            {
                lArrBillNoList = lStrhidBillNo.split(",");
                logger.info("====> 1.  lArrBillNoList :: "+lArrBillNoList.length);
            }
            logger.info("===> in if...................");
            PaybillHeadMpg paybillHeadmpg=null;
            PaybillHeadMpg paybillHeadmpgNew = null;
            StringBuffer paybillSBf=new StringBuffer();

            int approveFlag=7;

            int SettingValue=8;
            String status = null;
            ChangeStatementDAOImpl changeStatmntDAOImpl = new ChangeStatementDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());

            PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
            for(int i=0;i<lArrBillNoList.length;i++)
            {
                long lbillid = Long.valueOf(lArrBillNoList[i]);
                logger.info("1111111 in discardBill i month year and bill number : "+ i+ month + " " + year + " " + lbillid);

                //status = changeStatmntDAOImpl.getApproveFlagForGivenBill(lbillid, month, year);
                List<PaybillHeadMpg> paybillHeadmpgList  = paybillHeadMpgDAOImpl.getpaybillheadFromid(lbillid,approveFlag,month,year);

                for(int k=0;k<paybillHeadmpgList.size();k++)
                {
                    paybillHeadmpg=paybillHeadmpgList.get(k);
                    paybillHeadmpg.setApproveFlag(SettingValue);
                    paybillHeadMpgDAOImpl.update(paybillHeadmpg);
                    long paybillId = paybillHeadmpg.getHrPayPaybill();
                    logger.info("==> paybillId :: "+paybillId);
                }
                status =  "Approved successfully.";
            }


            paybillSBf.append("<paybillMapping>");
            paybillSBf.append("<status>").append(status).append("</status>");
            paybillSBf.append("</paybillMapping>");  

            logger.info("lStrhidBillNo ="+lStrhidBillNo);

            logger.info("status ="+status);

            Map map = objectArgs ;
            String paybillStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();

            logger.info(" the string buffer is :"+paybillStatus);
            map.put("ajaxKey", paybillStatus);
            resObj.setResultCode(ErrorConstants.SUCCESS);
            //logger.info("error");
            resObj.setResultValue(map);
            resObj.setViewName("ajaxData");
            logger.info(" SERVICE COMPLETE :");


            /*ResultObject resultBill=serv.executeService("ViewTokenNumber", objectArgs);
			objectArgs = (Map)resultBill.getResultValue();

			resObj.setViewName("viewChangeStatement");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);*/
        } 

        catch (Exception ex) {
            resObj.setThrowable(ex);
            logger.info("Token Number Screen Showing Error"+ex.getMessage());
            //System.out.println("Token Number Screen Showing Error"+ex.getMessage());

            logger.error("Token Number Screen Showing Error", ex);

            resObj.setResultCode(-1);
        }
        return resObj;
    }

    public ResultObject changeStatementDelete(Map objectArgs){
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try 
        {
            logger.info("inside frwrd CS method");			
            ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

            long month = request.getParameter("month")!= null && !request.getParameter("month").equals("") ? Long.parseLong(request.getParameter("month").toString()):0l; 
            long year = request.getParameter("year")!= null && !request.getParameter("year").equals("") ? Long.parseLong(request.getParameter("year").toString()):0l;
            String lStrhidBillNo = request.getParameter("billNum")!= null && !request.getParameter("billNum").equals("") ? request.getParameter("billNum").toString():"";
            logger.info("====> 1.  lStrhidBillNo :: "+lStrhidBillNo);
            String[] lArrBillNoList=null;
            if(lStrhidBillNo!=null || lStrhidBillNo!="")
            {
                lArrBillNoList = lStrhidBillNo.split(",");
                logger.info("====> 1.  lArrBillNoList :: "+lArrBillNoList.length);
            }
            logger.info("===> in if...................");
            PaybillHeadMpg paybillHeadmpg=null;			
            StringBuffer paybillSBf=new StringBuffer();

            int approveFlag=7;

            int SettingValue= 3;
            String status = null;

            PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
            for(int i=0;i<lArrBillNoList.length;i++)
            {
                long lbillid = Long.valueOf(lArrBillNoList[i]);
                logger.info("1111111 in discardBill i month year and bill number : "+ i+ month + " " + year + " " + lbillid);

                //status = changeStatmntDAOImpl.getApproveFlagForGivenBill(lbillid, month, year);
                List<PaybillHeadMpg> paybillHeadmpgList  = paybillHeadMpgDAOImpl.getpaybillheadFromid(lbillid,approveFlag,month,year);

                for(int k=0;k<paybillHeadmpgList.size();k++)
                {
                    paybillHeadmpg=paybillHeadmpgList.get(k);
                    paybillHeadmpg.setApproveFlag(SettingValue);					
                    paybillHeadMpgDAOImpl.update(paybillHeadmpg);
                    long paybillId = paybillHeadmpg.getHrPayPaybill();
                    logger.info("==> paybillId :: "+paybillId);
                }
                status =  "Deleted successfully.";
            }


            paybillSBf.append("<paybillMapping>");
            paybillSBf.append("<status>").append(status).append("</status>");
            paybillSBf.append("</paybillMapping>");  

            logger.info("lStrhidBillNo ="+lStrhidBillNo);

            logger.info("status ="+status);

            Map map = objectArgs ;
            String paybillStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();

            logger.info(" the string buffer is :"+paybillStatus);
            map.put("ajaxKey", paybillStatus);
            resObj.setResultCode(ErrorConstants.SUCCESS);
            //logger.info("error");
            resObj.setResultValue(map);
            resObj.setViewName("ajaxData");
            logger.info(" SERVICE COMPLETE :");

        } 

        catch (Exception ex) {
            resObj.setThrowable(ex);
            logger.info("Token Number Screen Showing Error"+ex.getMessage());
            //System.out.println("Token Number Screen Showing Error"+ex.getMessage());

            logger.error("Token Number Screen Showing Error", ex);

            resObj.setResultCode(-1);
        }
        return resObj;
    }

    public ResultObject changeStatementRejection(Map objectArgs){
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try 
        {
            logger.info("inside frwrd CS method");			
            ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

            long month = request.getParameter("month")!= null && !request.getParameter("month").equals("") ? Long.parseLong(request.getParameter("month").toString()):0l; 
            long year = request.getParameter("year")!= null && !request.getParameter("year").equals("") ? Long.parseLong(request.getParameter("year").toString()):0l;
            String lStrhidBillNo = request.getParameter("billNum")!= null && !request.getParameter("billNum").equals("") ? request.getParameter("billNum").toString():"";
            String remarks = request.getParameter("remarks")!= null && !request.getParameter("remarks").equals("") ? request.getParameter("remarks").toString():"";
            logger.info("====> 1.  lStrhidBillNo :: "+lStrhidBillNo);
            String[] lArrBillNoList=null;
            if(lStrhidBillNo!=null || lStrhidBillNo!="")
            {
                lArrBillNoList = lStrhidBillNo.split(",");
                logger.info("====> 1.  lArrBillNoList :: "+lArrBillNoList.length);
            }
            logger.info("===> in if...................");
            PaybillHeadMpg paybillHeadmpg=null;			
            StringBuffer paybillSBf=new StringBuffer();

            int approveFlag=7;

            int SettingValue= 9;
            String status = null;

            PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
            for(int i=0;i<lArrBillNoList.length;i++)
            {
                long lbillid = Long.valueOf(lArrBillNoList[i]);
                logger.info("1111111 in discardBill i month year and bill number : "+ i+ month + " " + year + " " + lbillid);

                //status = changeStatmntDAOImpl.getApproveFlagForGivenBill(lbillid, month, year);
                List<PaybillHeadMpg> paybillHeadmpgList  = paybillHeadMpgDAOImpl.getpaybillheadFromid(lbillid,approveFlag,month,year);

                for(int k=0;k<paybillHeadmpgList.size();k++)
                {
                    paybillHeadmpg=paybillHeadmpgList.get(k);
                    paybillHeadmpg.setApproveFlag(SettingValue);
                    paybillHeadmpg.setReasonForRejection(remarks);
                    paybillHeadMpgDAOImpl.update(paybillHeadmpg);
                    long paybillId = paybillHeadmpg.getHrPayPaybill();
                    logger.info("==> paybillId :: "+paybillId);
                }
                status =  "Rejected successfully.";
            }


            paybillSBf.append("<paybillMapping>");
            paybillSBf.append("<status>").append(status).append("</status>");
            paybillSBf.append("</paybillMapping>");  

            logger.info("lStrhidBillNo ="+lStrhidBillNo);

            logger.info("status ="+status);

            Map map = objectArgs ;
            String paybillStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();

            logger.info(" the string buffer is :"+paybillStatus);
            map.put("ajaxKey", paybillStatus);
            resObj.setResultCode(ErrorConstants.SUCCESS);
            //logger.info("error");
            resObj.setResultValue(map);
            resObj.setViewName("ajaxData");
            logger.info(" SERVICE COMPLETE :");

        } 

        catch (Exception ex) {
            resObj.setThrowable(ex);
            logger.info("Token Number Screen Showing Error"+ex.getMessage());
            //System.out.println("Token Number Screen Showing Error"+ex.getMessage());

            logger.error("Token Number Screen Showing Error", ex);

            resObj.setResultCode(-1);
        }
        return resObj;
    }

    public ResultObject changeStatementGeneratePaybill(Map objectArgs){
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
        try 
        {
            logger.info("inside frwrd CS method");			
            ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

            long month = request.getParameter("month")!= null && !request.getParameter("month").equals("") ? Long.parseLong(request.getParameter("month").toString()):0l; 
            long year = request.getParameter("year")!= null && !request.getParameter("year").equals("") ? Long.parseLong(request.getParameter("year").toString()):0l;
            String lStrhidBillNo = request.getParameter("billNum")!= null && !request.getParameter("billNum").equals("") ? request.getParameter("billNum").toString():"";
            logger.info("====> 1.  lStrhidBillNo :: "+lStrhidBillNo);
            String[] lArrBillNoList=null;
            if(lStrhidBillNo!=null || lStrhidBillNo!="")
            {
                lArrBillNoList = lStrhidBillNo.split(",");
                logger.info("====> 1.  lArrBillNoList :: "+lArrBillNoList.length);
            }
            logger.info("===> in if...................");
            PaybillHeadMpg paybillHeadmpg=null;
            PaybillHeadMpg paybillHeadmpgNew = null;
            StringBuffer paybillSBf=new StringBuffer();

            int approveFlag=8;

            int SettingValue=0;
            String status = null;
            ChangeStatementDAOImpl changeStatmntDAOImpl = new ChangeStatementDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());

            PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
            for(int i=0;i<lArrBillNoList.length;i++)
            {
                long lbillid = Long.valueOf(lArrBillNoList[i]);
                logger.info("1111111 in discardBill i month year and bill number : "+ i+ month + " " + year + " " + lbillid);

                //status = changeStatmntDAOImpl.getApproveFlagForGivenBill(lbillid, month, year);
                List<PaybillHeadMpg> paybillHeadmpgList  = paybillHeadMpgDAOImpl.getpaybillheadFromid(lbillid,approveFlag,month,year);
                logger.info("The result of query is as folloews"+paybillHeadmpgList.size());

                for(int k=0;k<paybillHeadmpgList.size();k++)
                {
                    paybillHeadmpg=paybillHeadmpgList.get(k);
                    paybillHeadmpg.setApproveFlag(SettingValue);
                    paybillHeadMpgDAOImpl.update(paybillHeadmpg);
                    long paybillId = paybillHeadmpg.getHrPayPaybill();
                    logger.info("==> paybillId :: "+paybillId);
                }
                status =  "Paybill generated successfully.";
            }


            paybillSBf.append("<paybillMapping>");
            paybillSBf.append("<status>").append(status).append("</status>");
            paybillSBf.append("</paybillMapping>");  

            logger.info("lStrhidBillNo ="+lStrhidBillNo);

            logger.info("status ="+status);

            Map map = objectArgs ;
            String paybillStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();

            logger.info(" the string buffer is :"+paybillStatus);
            map.put("ajaxKey", paybillStatus);
            resObj.setResultCode(ErrorConstants.SUCCESS);
            //logger.info("error");
            resObj.setResultValue(map);
            resObj.setViewName("ajaxData");
            logger.info(" SERVICE COMPLETE :");


            /*ResultObject resultBill=serv.executeService("ViewTokenNumber", objectArgs);
			objectArgs = (Map)resultBill.getResultValue();

			resObj.setViewName("viewChangeStatement");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);*/
        } 

        catch (Exception ex) {
            resObj.setThrowable(ex);
            logger.info("Token Number Screen Showing Error"+ex.getMessage());
            //System.out.println("Token Number Screen Showing Error"+ex.getMessage());

            logger.error("Token Number Screen Showing Error", ex);

            resObj.setResultCode(-1);
        }
        return resObj;
    }



    public ResultObject fwdApprChangeStmt1(Map objectArgs) throws Exception 
    {

        logger.info("Entering into loadData of loadReportingDDOScreen,by ketan");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        Long langId =1l;
        ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
        Map loginMap = (Map) objectArgs.get("baseLoginMap");

        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

        CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());                       

        List<CmnLookupMst> MonthlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Month", langId);                                                                                    
        List<CmnLookupMst> YearLookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Year", langId);      
        logger.info("MonthlookUpList::"+MonthlookUpList) ;
        logger.info("YearLookUpList:::"+YearLookUpList) ;
        long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
        long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
        long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
        logger.info("postId::::"+postId);       
        logger.info("userId::::"+userId);       
        logger.info("dbId::::"+dbId);   
        ChangeStatementDAOImpl changeStatmntDAOImpl = new ChangeStatementDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());

        List ddoDtls= changeStatmntDAOImpl.getSubDDOsOffc(postId);   
        objectArgs.put("ddoDtls", ddoDtls);
        objectArgs.put("MonthlookUpList", MonthlookUpList);
        objectArgs.put("YearLookUpList", YearLookUpList);
        objRes.setResultValue(objectArgs);
        objRes.setResultCode(ErrorConstants.SUCCESS);
        objRes.setViewName("changeStatementJsp");
        return objRes;


    }




    public ResultObject fwdApprChangeStmt(Map objectArgs) throws Exception 
    {
        logger.info("Entering into viewConsolidatedBill");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        Long langId = 1l;
        ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
        Map loginMap = (Map) objectArgs.get("baseLoginMap");
        langId = StringUtility.convertToLong(loginMap.get("langId").toString());
        Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
        ChangeStatementDAOImpl changeStatmntDAOImpl = new ChangeStatementDAOImpl(PaybillHeadMpg.class,serviceLocator.getSessionFactory());
        String ddoCode = StringUtility.getParameter("ddoCode", request).trim();
        String month = StringUtility.getParameter("month", request).trim();
        String year = StringUtility.getParameter("year", request).trim();
        String flag = StringUtility.getParameter("flag", request).trim();
        

        objectArgs.put("selMonth", month);
        objectArgs.put("selYear", year);
        logger.info("ddo code is ****************"+ddoCode);
        objectArgs.put("selddoCode", ddoCode);
        Long status=7L;
        
        //To find the Loggedin DDO CODE and the level and ADMIN type
        /*String button= "A";
        long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
        PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serviceLocator.getSessionFactory());
        List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
        OrgDdoMst ddoMst  = null;
        if(ddoList!=null && ddoList.size()>0) {
                 ddoMst = ddoList.get(0);
        }
        String loggedInDdoCode = null;
        if(ddoMst!=null)
            loggedInDdoCode = ddoMst.getDdoCode();
        List DDOType=changeStatmntDAOImpl.getDDOType(loggedInDdoCode);
        String ddoDetails;
        String[] ddoDetailsArr;
        String offcType;
        String level;
        Long status=7L;
        if(DDOType.size()>0 ){
            ddoDetails=DDOType.get(0).toString();
            ddoDetailsArr= ddoDetails.split("##");
            offcType=ddoDetailsArr[0];
            level=ddoDetailsArr[1];
            if(offcType!=null && !offcType.equals("") && Long.parseLong(offcType)==2 && level !=null && !level.equals("") && Long.parseLong(level)==1){
                button="F"; 
                
            }
            else if(offcType!=null && !offcType.equals("") && Long.parseLong(offcType)==2 && level !=null && !level.equals("") && Long.parseLong(level)==0){
                button="A"; 
                status=10L;
            }
            else {
                button="A";
            }
        }
        
        objectArgs.put("button", button); */
        
        
        // end of finding the ddo code
        logger.info("flag" + flag);
        logger.info("year" + year);
        logger.info("month" + month);
        Calendar cal = Calendar.getInstance();
        String currmonth = (new Integer((cal.get(Calendar.MONTH) + 1))).toString();
        String curryear = (new Integer(cal.get(Calendar.YEAR))).toString();
        month = (month.equals("-1") || month == null || month.equals("0") || month.equals("")) ? currmonth : month;
        year = (year.equals("-1") || year == null || year.equals("0") || year.equals("")) ? curryear : year;
        if (flag.equals("") || flag == null)
        {
            month = currmonth;
            year = curryear;

            objectArgs.put("selMonth", currmonth);
            objectArgs.put("selYear", curryear);


        }
        List ddoDtls = null;
        List changeStmtList=null;
        ddoDtls=changeStatmntDAOImpl.getSubDDOsOffc( postid);
        
        changeStmtList=changeStatmntDAOImpl.getChangeStatemtnList(month,year,postid,ddoCode,status);
       // objectArgs.put("selddoCode", ddoCode);
        CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serviceLocator.getSessionFactory());
        List<CmnLookupMst> MonthlookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Month", langId);
        List<CmnLookupMst> YearLookUpList = cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Year", langId);
        ZpReportingDAO ZpReportingDAOImpl = new ZpReportingDAOImpl(ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
        objectArgs.put("changeStmtList", changeStmtList);
        objectArgs.put("size", changeStmtList.size()); 
        objectArgs.put("ddoDtls", ddoDtls);
        logger.info("ddoDtls size ***************"+ddoDtls.size());
        objectArgs.put("MonthlookUpList", MonthlookUpList);
        objectArgs.put("YearLookUpList", YearLookUpList);
        objRes.setResultValue(objectArgs);
        objRes.setResultCode(ErrorConstants.SUCCESS);
        objRes.setViewName("changeStatementJsp");

        return objRes;

    }
    
    
    
    
    public ResultObject ChangeStmntAction(Map objectArgs) throws Exception 
    {

        logger.info("Entering into loadData of loadReportingDDOScreen,by ketan");
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        Long langId =1l;
        ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
        Map loginMap = (Map) objectArgs.get("baseLoginMap");

        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        
        String action = StringUtility.getParameter("action", request).trim();
        String billNo = StringUtility.getParameter("billNo", request).trim();
        String remark = StringUtility.getParameter("remark", request).trim();
        
                
        ChangeStatementDAOImpl changeStatmntDAOImpl = new ChangeStatementDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());

        changeStatmntDAOImpl.updateCHangeStmntStatus(action,billNo,remark);   
        String msg="";
        if(action !=null && !action.equals("")){
            if(Long.parseLong(action)==9){
                msg="Change Statement has been Rejetced.";  
            }
            
            if(Long.parseLong(action)==8){
                msg="Change Statement has been Approved.";  
            }
            
  
            if(Long.parseLong(action)==10){
                msg="Change Statement has been forwarded to 3rd Level.";  
            }
  
            
            
        }
        objectArgs.put("msg", msg);
        objRes.setResultValue(objectArgs);
        objRes.setResultCode(ErrorConstants.SUCCESS);
        objRes.setViewName("changeStatementJsp");
        return objRes;


    }
    
    
}
