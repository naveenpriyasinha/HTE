package com.tcs.sgv.common.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.PRTrackingDAO;
import com.tcs.sgv.common.dao.PRTrackingDAOImpl;
import com.tcs.sgv.common.helper.ColumnVo;
import com.tcs.sgv.common.helper.ExcelExportHelper;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.common.helper.SessionHelper;
//added by Saurabh S
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.OrgTicketMst;
import com.tcs.sgv.common.utils.StringUtility;
//Ended by Saurabh S
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class PRTrackingServiceImpl extends ServiceImpl
{

    Long gLngUserId = null;
    Long gLngPostId = null;
    private Long gLngLangId = null; /* LANG ID */
    private HttpServletRequest request = null; /* REQUEST OBJECT */
    private ServiceLocator serv = null; /* SERVICE LOCATOR */
    private Date gDtCurDate = null; /* CURRENT DATE */
    private final String gStrPostId = null; /* STRING POST ID */
    Log logger = LogFactory.getLog(this.getClass());

    public ResultObject addNewTicket(final Map inputMap) throws Exception
    {

        this.logger.info("hiii i m in addNewTicket ");

        final ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {

            this.setSessionInfo(inputMap);
            final PRTrackingDAO prTrackingDAO = new PRTrackingDAOImpl(PRTrackingDAOImpl.class, this.serv
                    .getSessionFactory());
            PRTrackingDAO orgTicketMstDAO = new PRTrackingDAOImpl(OrgTicketMst.class, this.serv
                    .getSessionFactory());
            //For File Attachment
			CmnAttachmentMstDAO cmnAttachmentMstDAO = null;
            OrgTicketMst orgTicketMst;
            final String flag = StringUtility.getParameter("flag", this.request);
            inputMap.put("flag", flag);
            logger.info("flag-----"+flag);
            String lStrUser = StringUtility.getParameter("lStrUser", this.request);
            inputMap.put("lStrUser", lStrUser);
            Long ticketId = Long.parseLong((StringUtility.getParameter("ticketId", this.request)!=null && !StringUtility.getParameter("ticketId", this.request).equals(""))?(StringUtility.getParameter("ticketId", this.request)):"0");
            logger.info("ticketId-----"+ticketId.toString());            	
            if(ticketId!=0)
            {
            	logger.info("update");
                orgTicketMst = (OrgTicketMst) orgTicketMstDAO.read(ticketId);
                inputMap.put("orgTicketMst", orgTicketMst);
                
                
                
                inputMap.put("strRemarks", clobStringConversion(orgTicketMst.getRemarks()));
                logger.info("strRemarks----------"+clobStringConversion(orgTicketMst.getRemarks()));
                
                prTrackingDAO.updateTicketFlag(ticketId);
              //File Attachment
				cmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
				if (orgTicketMst.getFileId() != null && orgTicketMst.getFileId().doubleValue() > 0) {
				CmnAttachmentMst CmnAttachmentMst = null;
				CmnAttachmentMst = cmnAttachmentMstDAO.findByAttachmentId(Long.parseLong(orgTicketMst.getFileId().toString()));
				inputMap.put("File", CmnAttachmentMst);
            }}


            // Screen List
            final List lLstScreen = prTrackingDAO.getScreenMaster();
            inputMap.put("lLstScreen", lLstScreen);

            // STatus List
            final List lLstStatus = IFMSCommonServiceImpl.getLookupValues("TicketStatus", SessionHelper
                    .getLangId(inputMap), inputMap);
            inputMap.put("lLstStatus", lLstStatus);

            // Priority List
           // final List lLstPriority = IFMSCommonServiceImpl.getLookupValues("TicketPriority", SessionHelper
             //       .getLangId(inputMap), inputMap);
            //inputMap.put("lLstPriority", lLstPriority);
            
            final List lLstSeverity = IFMSCommonServiceImpl.getLookupValues("TicketSeverity", SessionHelper
                    .getLangId(inputMap), inputMap);
            inputMap.put("lLstSeverity", lLstSeverity);
            
            final List lLstRootCause = IFMSCommonServiceImpl.getLookupValues("TicketRootCause", SessionHelper
                    .getLangId(inputMap), inputMap);
            inputMap.put("lLstRootCause", lLstRootCause);
            

            resultObject.setResultValue(inputMap);
            resultObject.setViewName("NewTicketEntry");

        } catch (final Exception e)
        {
            resultObject.setResultValue(null);
            resultObject.setThrowable(e);
            resultObject.setResultCode(ErrorConstants.ERROR);
            resultObject.setViewName("errorPage");
        }

        return resultObject;
    }

    public ResultObject getLoggedInTickets(final Map inputMap) throws Exception
    {

        this.logger.info("hiii i m in getLoggedInTickets ");

        final ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {

            this.setSessionInfo(inputMap);
            final Map loginMap = (Map) inputMap.get("baseLoginMap");

            this.logger.info("hiii i m finding ddo code");
            final String postid = loginMap.get("loggedInPost").toString();
            this.logger.info("hiii i m finding logged in post Id" + postid);
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            this.logger.info("hiii i m finding ddo code" + locId);

            // STatus List
            final List lLstStatus = IFMSCommonServiceImpl.getLookupValues("TicketStatus", SessionHelper
                    .getLangId(inputMap), inputMap);
            inputMap.put("lLstStatus", lLstStatus);

            String lStrUser = StringUtility.getParameter("Flag", request); 
            inputMap.put("lStrUser", lStrUser);

            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fromDate = "";
            String toDate = "";

            if(!StringUtility.getParameter("fromDate", request).toString().equals(null) && !StringUtility.getParameter("fromDate", request).toString().equals(""))
            {
                String fDate = StringUtility.getParameter("fromDate", request); 
                fromDate = df.format(sdf.parse(fDate));
                logger.info("fromDate----" + fromDate);
                inputMap.put("fromDate", fDate);
            }


            if(!StringUtility.getParameter("toDate", request).toString().equals(null) && !StringUtility.getParameter("toDate", request).toString().equals(""))
            {
                String tDate = StringUtility.getParameter("toDate", request); 
                toDate = df.format(sdf.parse(tDate));
                logger.info("toDate----" + toDate);
                inputMap.put("toDate", tDate);
            }


            // String toDate = StringUtility.getParameter("toDate", request); 
            // logger.info("toDate----" + toDate);


            long status = Long.parseLong((StringUtility.getParameter("status", this.request)!=null && !StringUtility.getParameter("status", this.request).equals(""))?(StringUtility.getParameter("status", this.request)):"0");
            inputMap.put("filterStatus", status);

            PRTrackingDAOImpl lobjPRTrackingDAOImpl=new PRTrackingDAOImpl(null,serv.getSessionFactory());
            String RoleId=lobjPRTrackingDAOImpl.getRoleID(postid);
            List details=lobjPRTrackingDAOImpl.getLoadEmpTickets(locId,RoleId,status,fromDate,toDate);
            Integer totalRecords = details.size();
            //  Integer totalRecords=lobjPRTrackingDAOImpl.getLoadEmpTicketsCount(locId,RoleId);
            inputMap.put("details", details);
            inputMap.put("totalRecords", totalRecords);

            resultObject.setResultValue(inputMap);
            resultObject.setViewName("LoadEmpTickets");

        } catch (final Exception e)
        {
            resultObject.setResultValue(null);
            resultObject.setThrowable(e);
            resultObject.setResultCode(ErrorConstants.ERROR);
            resultObject.setViewName("errorPage");
        }

        return resultObject;
    }

    public ResultObject saveNewEntry(final Map inputMap) throws Exception
    {

        this.logger.info("hiii i m in saveNewEntry ");
        Map attachMap = null;

        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        this.setSessionInfo(inputMap);
        final PRTrackingDAO prTrackingDAO = new PRTrackingDAOImpl(PRTrackingDAOImpl.class, this.serv
                .getSessionFactory());

        try
        {
            final Map loginMap = (Map) inputMap.get("baseLoginMap");
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            String ddoCode = "";
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, this.serv.getSessionFactory());
            PRTrackingDAO orgTicketMstDAO = new PRTrackingDAOImpl(OrgTicketMst.class, this.serv
                    .getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            OrgTicketMst orgTicketMst=null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
                this.logger.info("hiii i m finding ddo code");
            }

            if (ddoMst != null)
            {
                ddoCode = ddoMst.getDdoCode();
            }
            Long ticketId = Long.parseLong((StringUtility.getParameter("ticketId", this.request)!=null && !StringUtility.getParameter("ticketId", this.request).equals(""))?(StringUtility.getParameter("ticketId", this.request)):"0");
            logger.info("ticketId-----"+ticketId.toString());    
            if(ticketId!= 0)
            {
            	orgTicketMst = (OrgTicketMst) orgTicketMstDAO.read(ticketId);
            	logger.info("ticketId details-----"+ticketId.toString());   
            	//inputMap.put("orgTicketMst", orgTicketMst);
            }
            String flag = StringUtility.getParameter("flag", this.request);
            String lStrUser = StringUtility.getParameter("lStrUser", this.request);
            inputMap.put("lStrUser", lStrUser);
            String filterStatus = StringUtility.getParameter("filterStatus", this.request);
            inputMap.put("filterStatus", filterStatus);

            String temp = "###";
            String user = "";
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);
            
            ///////////////////////
            Calendar c = Calendar.getInstance(); 
            int seconds = c.get(Calendar.SECOND);
            int minutes = c.get(Calendar.MINUTE);
            int hours = c.get(Calendar.HOUR_OF_DAY);
            int years = c.get(Calendar.YEAR);
            int months = 1 + c.get(Calendar.MONTH);
            int days = c.get(Calendar.DAY_OF_MONTH);
            int AM_orPM = c.get(Calendar.AM_PM);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            int lastDayOfMonthh = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            logger.info("hours----------"+hours);
            
            if(dayOfWeek!=7 && dayOfWeek!=1 )
            {
            	
	            if(AM_orPM!=1)
	            {
	            	if(hours<=10)
	            	{
	            	c.set(Calendar.HOUR_OF_DAY, 10);
	            	c.set(Calendar.MINUTE, 00);
	            	c.set(Calendar.SECOND, 00);
	            	}
	            	
	            }
	            else
	            {
	            	if(hours>=19)
	            	{
	            		c.set(Calendar.HOUR_OF_DAY, 10);
    	            	c.set(Calendar.MINUTE, 00);
    	            	c.set(Calendar.SECOND, 00);
    	            	
	            		if(dayOfWeek==6)
	            		{
	            			c.set(Calendar.DAY_OF_MONTH, dayOfMonth+3);
	            		}
	            		else
	            		{
	            			c.set(Calendar.DAY_OF_MONTH, dayOfMonth+1);
	            		}
	            	
	            
	            	if(months==12 && dayOfMonth == lastDayOfMonthh)
	            	{
	            		c.set(Calendar.MONTH, months+1);
	            		c.set(Calendar.YEAR, years+1);
	            	}
	            	if(months!=12 && dayOfMonth == lastDayOfMonthh)
	            	{
	            		c.set(Calendar.MONTH, months+1);
	            	}
	            	}
	            }
            }
            else if (dayOfWeek==7)
            {
            	if(months==12 && dayOfMonth == lastDayOfMonthh)
            	{
            		c.set(Calendar.MONTH, months+1);
            		c.set(Calendar.YEAR, years+1);
            	}
            	if(months!=12 && dayOfMonth == lastDayOfMonthh)
            	{
            		c.set(Calendar.MONTH, months+1);
            	}
            	c.set(Calendar.DAY_OF_MONTH, dayOfMonth+2);
            	c.set(Calendar.HOUR_OF_DAY, 10);
            	c.set(Calendar.MINUTE, 00);
            	c.set(Calendar.SECOND, 00);
            }
            else
            {
            	if(months==12 && dayOfMonth == lastDayOfMonthh)
            	{
            		c.set(Calendar.MONTH, months+1);
            		c.set(Calendar.YEAR, years+1);
            	}
            	if(months!=12 && dayOfMonth == lastDayOfMonthh)
            	{
            		c.set(Calendar.MONTH, months+1);
            	}
            	c.set(Calendar.DAY_OF_MONTH, dayOfMonth+1);
            	c.set(Calendar.HOUR_OF_DAY, 10);
            	c.set(Calendar.MINUTE, 00);
            	c.set(Calendar.SECOND, 00);
            }
        
            Date acknowledgeDate = c.getTime();
            /////////////////////

            if(flag.equals("Add"))
            {
                final String title = StringUtility.getParameter("ticketTitle", this.request).trim();
                this.logger.info("title---" + title);
                final Long screen = Long.parseLong(StringUtility.getParameter("selScreen", this.request).trim());
                final String desc = StringUtility.getParameter("ticketDesc", this.request);
                final String credentials = StringUtility.getParameter("ticketCred", this.request);
                final Long contactNo = Long.parseLong(StringUtility.getParameter("ticketContactNo", this.request).trim());
                final String emailId = StringUtility.getParameter("ticketEmailId", this.request);
                final String userName = StringUtility.getParameter("ticketUserName", this.request);
                String remarks = StringUtility.getParameter("ticketRemarks", this.request);
                remarks = "User" + strDate + remarks;
                this.logger.info("title---" + title + "screen----- " + screen + "desc----- " + desc);

              //For File Attachment
				resultObject = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);

				resultObject = serv.executeService("FILE_UPLOAD_SRVC", inputMap);
				
				attachMap = (Map) resultObject.getResultValue();
				
				Long lLngAttachId = 0L;
				if ((attachMap.get("AttachmentId_File") != null)) {
					
					 lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_File")));
					System.out.println( "FileId+++"+lLngAttachId);
				}
                prTrackingDAO.insertTicketMst(title, screen, desc, credentials, contactNo, emailId, remarks, ddoCode, acknowledgeDate,
                        inputMap,lLngAttachId);
            }

            else
            {
                Long status = Long.parseLong(StringUtility.getParameter("selStatus", this.request).trim());
                logger.info("status=========="+status);
               // Long priority = Long.parseLong(StringUtility.getParameter("selPriority", this.request).trim());
               //logger.info("priority=========="+priority);
                Long severity = Long.parseLong(StringUtility.getParameter("selSeverity", this.request).trim());
                Long rootCause = Long.parseLong(StringUtility.getParameter("selRootCause", this.request).trim());
                String remarks = StringUtility.getParameter("ticketRemarks", this.request);
                String flagC = StringUtility.getParameter("flagC", this.request);
                logger.info("remarks=========="+remarks);
                logger.info("flagC=========="+flagC);
                //Added by Saurabh S
              //For File Attachment
				resultObject = serv.executeService("FILE_UPLOAD_VOGEN", inputMap);

				resultObject = serv.executeService("FILE_UPLOAD_SRVC", inputMap);

				attachMap = (Map) resultObject.getResultValue();
				Long lLngAttachId = 0L;
				if (attachMap.get("AttachmentId_File") != null) {
					lLngAttachId = Long.parseLong(String.valueOf(attachMap.get("AttachmentId_File")));
					//lObjTrnPnsnProcPnsnrDtlsVO.setPhotoAttachmentId(lLngAttachId);
					System.out.println( "FileId+++"+lLngAttachId);
				}
				//Ended by Saurabh S
				
                String prevRemarks =" "; 
                prevRemarks =prTrackingDAO.getPrevRemarks(ticketId)+" ";
                if(!prevRemarks.equals(null)){
                    if(lStrUser.equals("U"))
                    {
                        user = "User";
                        if(flagC.equals("C"))
                        {
                        	//prTrackingDAO.updateHistoryMst(orgTicketMst);
                        	status = 10001198191l;//Closed
                        }
                        else
                        {
                        	status = 10001198189l;//Open
                        }
                    }
                    else
                        user = "Resolver";

                    // prevRemarks = prevRemarks.concat(temp);
                    //  remarks =  prevRemarks.concat(remarks);
                   // remarks =  "'"+prevRemarks+"'||chr(10)||" + temp +"||chr(10)||"+ user+"||chr(10)||" + strDate+"||chr(10)||" + remarks+"||chr(10)";
                }
                prTrackingDAO.updateTicketMst(ticketId, status,/*priority,*/ remarks, inputMap, user, prevRemarks, severity, rootCause, lLngAttachId);
                //added by Saurabh S
                prTrackingDAO.updateHistoryMst(orgTicketMst);
                //Ended by saurabh s
            }

            resultObject.setResultValue(inputMap);
            resultObject.setViewName("NewTicketEntry");

        } catch (final Exception e)
        {
            resultObject.setResultValue(null);
            resultObject.setThrowable(e);
            resultObject.setResultCode(ErrorConstants.ERROR);
            resultObject.setViewName("errorPage");
        }

        return resultObject;
    }


    private void setSessionInfo(final Map inputMap) throws Exception
    {

        try
        {
            this.request = (HttpServletRequest) inputMap.get("requestObj");
            this.serv = (ServiceLocator) inputMap.get("serviceLocator");
            this.gLngPostId = SessionHelper.getPostId(inputMap);
            this.gLngUserId = SessionHelper.getUserId(inputMap);
            this.gDtCurDate = SessionHelper.getCurDate();
            this.gLngLangId = SessionHelper.getLangId(inputMap);

        } catch (final Exception e)
        {
            this.logger.error("Error in setSessionInfo of changeNameServiceImpl ", e);
            throw e;
        }
    }

    public ResultObject generateExcelForTicketDetails(Map inputMap) throws Exception
    {
        logger.info("Entering into generateExcelForPostDetails");
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        Long langId = 1l;
        this.setSessionInfo(inputMap);

        Map loginMap = (Map) inputMap.get("baseLoginMap");
        String postid = loginMap.get("loggedInPost").toString();
        long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();


        String lStrUser = StringUtility.getParameter("Flag", request); 
        inputMap.put("lStrUser", lStrUser);

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = "";
        String toDate = "";
        String createdDate = "";
        String pendingFrom = "";
        long status =0l;
        /*   
            if(!StringUtility.getParameter("fromDate", request).toString().equals(null) && !StringUtility.getParameter("fromDate", request).toString().equals(""))
            {
                String fDate = StringUtility.getParameter("fromDate", request); 
                fromDate = df.format(sdf.parse(fDate));
                logger.info("fromDate----" + fromDate);
            }


            if(!StringUtility.getParameter("toDate", request).toString().equals(null) && !StringUtility.getParameter("toDate", request).toString().equals(""))
            {
                String tDate = StringUtility.getParameter("toDate", request); 
                toDate = df.format(sdf.parse(tDate));
                logger.info("toDate----" + toDate);
            }

            Long status = Long.parseLong((StringUtility.getParameter("status", this.request)!=null && !StringUtility.getParameter("status", this.request).equals(""))?(StringUtility.getParameter("status", this.request)):"0");
            inputMap.put("filterStatus", status);*/
        PRTrackingDAO lobjPRTrackingDAOImpl = new PRTrackingDAOImpl(PRTrackingDAOImpl.class, this.serv.getSessionFactory());
        String RoleId=lobjPRTrackingDAOImpl.getRoleID(postid);
        List details=lobjPRTrackingDAOImpl.getLoadEmpTickets(locId,RoleId,status,fromDate,toDate);

        ReportExportHelper objExport = new ReportExportHelper();
        List columnvalue = new ArrayList();
        List mainValue=new ArrayList();
        Map output = new HashMap();
        String lSBOut = "";
        String exportTo=DBConstants.DIS_EXCELFILE;
        String lineSeperator = System.getProperty("line.separator");
        List<Object> noOfColumn = new ArrayList<Object>();
        List<Object> noOfTicketList = new ArrayList<Object>();
        Object objFwdTotal[] = null;

        if (details != null && !details.isEmpty()) {
            Iterator itTotal = details.iterator();
            while (itTotal.hasNext()) {
                objFwdTotal = (Object[]) itTotal.next();
                noOfColumn = new ArrayList();

                noOfColumn.add("HTE" +(objFwdTotal[0]!=null ? objFwdTotal[0] : "-"));

                noOfColumn.add(objFwdTotal[1]!=null ? objFwdTotal[1] : "-");

                noOfColumn.add(objFwdTotal[2]!=null ? objFwdTotal[2] : "-");

                noOfColumn.add(objFwdTotal[3]!=null ? objFwdTotal[3] : "-");

              /* if(objFwdTotal[4]!=null)
                {
                    createdDate = sdf.format(df.parse(objFwdTotal[4].toString()));
                    noOfColumn.add(createdDate);
                }*/
                noOfColumn.add(objFwdTotal[4]!=null ? objFwdTotal[4] : "-");

                noOfColumn.add(objFwdTotal[6]!=null ? objFwdTotal[6] : "-");

                //noOfColumn.add(objFwdTotal[5]!=null ? objFwdTotal[5] : "-");

                if(objFwdTotal[5]!=null)
                {
                    pendingFrom = (objFwdTotal[5].toString()).concat("day/s");
                    if(objFwdTotal[3].toString().equals("Closed") || objFwdTotal[3].toString().equals("Hard Closed"))
                    {
                        pendingFrom = "-";
                    }
                    noOfColumn.add(pendingFrom);
                }

                noOfColumn.add(objFwdTotal[9]!=null ? objFwdTotal[9] : "-");
                noOfColumn.add(objFwdTotal[10]!=null ? objFwdTotal[10] : "-");
                noOfColumn.add(objFwdTotal[11]!=null ? objFwdTotal[11] : "-");
                noOfColumn.add(objFwdTotal[7]!=null ? objFwdTotal[7] : "-");


                noOfTicketList.add(noOfColumn);
            }


        }

        ColumnVo[] excelBillReportHeading=new ColumnVo[11];
        excelBillReportHeading[0]=new ColumnVo("Ticket Id", 2, 13, 0,false,true); 
        excelBillReportHeading[1]=new ColumnVo("Title",2,13,0,true,true);
        excelBillReportHeading[2]=new ColumnVo("Description",2,13,0,true,true);
        excelBillReportHeading[3]=new ColumnVo("Status",2,13,0,true,true);
        excelBillReportHeading[4]=new ColumnVo("Raised Date",2,13,0,true,true);
        excelBillReportHeading[5]=new ColumnVo("Remarks",2,13,0,true,true);
       // excelBillReportHeading[6]=new ColumnVo("Priority",2,13,0,true,true);
        excelBillReportHeading[6]=new ColumnVo("Pending from",2,13,0,true,true);
        excelBillReportHeading[7]=new ColumnVo("Severity",2,13,0,true,true);
        excelBillReportHeading[8]=new ColumnVo("Root Cause",2,13,0,true,true);
        excelBillReportHeading[9]=new ColumnVo("Acknowledgement Date",2,13,0,true,true);
        excelBillReportHeading[10]=new ColumnVo("Resolution Date",2,13,0,true,true);
      //  excelBillReportHeading[11]=new ColumnVo("file id",2,13,0,true,true);
        

        columnvalue.add(excelBillReportHeading);
        mainValue.add(noOfTicketList);

        StringBuffer lSbHeader= new StringBuffer();
        lSbHeader.append("Table showing details of Issue Tracker");
        lSbHeader.append("\n");

        logger.info("lSbHeader "+lSbHeader.toString());
        String lStrFooter= "";
        int nMode= 131;

        ExcelExportHelper exph = new ExcelExportHelper();
        byte[] aryOut = null;
        String[] param = new String[1];
        List Headerdata = new ArrayList();
        List footerdata = new ArrayList();
        param[0] = "";

        Headerdata.add(lSbHeader.toString());
        StringBuffer footer =new StringBuffer();

        footer = footer.append("");
        footerdata.add(footer.toString());

        aryOut = exph.getExcelReportPrintFormat(mainValue, columnvalue, param, Headerdata, footerdata);
        String lStrExportTo = DBConstants.DIS_EXCELFILE;
        Map lDetailMap = new HashMap();
        if ((DBConstants.DIS_ONSCREEN).equals(lStrExportTo)) {
            lDetailMap.put(DBConstants.DIS_ONSCREEN, aryOut);
        } else if ((DBConstants.DIS_EXCELFILE).equals(lStrExportTo)) {
            lDetailMap.put(DBConstants.DIS_EXCELFILE, aryOut);
        }
        inputMap.put("FileName", "PR_TRACKER");
        objExport.getExportData(objRes, lStrExportTo, inputMap, lDetailMap, false);
        objRes.setResultValue(inputMap);
        return objRes;
    }
    public static String clobStringConversion(Clob clb) throws IOException, SQLException
    {
      if (clb == null)
     return  "";
            
      String str ="";
     // StringBuffer str = new StringBuffer();
      String strng="";
              
    
    BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());
   
      while ((strng=bufferRead.readLine())!=null)
          str = str + strng + "\n";
          //str.append(strng);
         // str.append(System.getProperty("line.separator"));
   
      return str;
    }        
}
