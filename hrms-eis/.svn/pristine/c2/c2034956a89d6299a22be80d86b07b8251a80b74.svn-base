package com.tcs.sgv.eis.service;

/*
 *   AUTHOR-ROSHAN KUMAR
 *   661776
 * */
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.eis.dao.DisplayPendingWorkDao;
import com.tcs.sgv.eis.dao.DisplayPendingWorkDaoImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class DisplayPendingWork extends ServiceImpl
{
    Long gLngUserId = null;
    Long gLngPostId = null;
    private Long gLngLangId = null; /* LANG ID */
    private HttpServletRequest request = null; /* REQUEST OBJECT */
    private ServiceLocator serv = null; /* SERVICE LOCATOR */
    private Date gDtCurDate = null; /* CURRENT DATE */

    Log logger = LogFactory.getLog(this.getClass());

    public ResultObject deleteApproveEmp(final Map objectArgs)
    {
        this.logger.info("Inside Get missed Employeeeeee");
        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        String lStrDdocode = null;
        try
        {

            lStrDdocode = StringUtility.getParameter("Ddocode", request);

            this.logger.info("DDO Code is " + lStrDdocode);
            List EmpStatistics = null;

            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());
            String empId = null;
            if (StringUtility.getParameter("empId", request) != null
                    && StringUtility.getParameter("empId", request) != "")
            {
                empId = StringUtility.getParameter("empId", request);
                this.logger.info("emp id is " + empId);
                displayPendingWorkDao.deleteSelectedEmployee(empId);
                displayPendingWorkDao.detachPost(empId);
            }

            EmpStatistics = displayPendingWorkDao.getApproveEmployeeDDOwise(lStrDdocode);

            objectArgs.put("empList", EmpStatistics);
            objectArgs.put("Ddocode", lStrDdocode);

            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);// put in result object
            resultObject.setViewName("deleteApproveEmp");// set view name

        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            this.logger.error("Error in loadEmpDtlsDdoWise " + e);
        }
        return resultObject;
    }

    public ResultObject deleteSchool(final Map objectArgs)
    {
        this.logger.info("Inside Get schoo list");
        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

        String lStrDdocode = null;
        try
        {

            lStrDdocode = StringUtility.getParameter("Ddocode", request);

            this.logger.info("DDO Code is " + lStrDdocode);
            List schoolStatList = null;

            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());

            final Blob blob = displayPendingWorkDao.getAttachment();
            objectArgs.put("blobData", blob);
            final int blobLength = (int) blob.length();
            objectArgs.put("blobLength", blobLength);

            InputStream lBytes = blob.getBinaryStream(1, blobLength);
            objectArgs.put("blobData", lBytes);
            lBytes = blob.getBinaryStream();
            String schoolIds = null;
            if (StringUtility.getParameter("schoolIds", request) != null
                    && StringUtility.getParameter("schoolIds", request) != "")
            {
                schoolIds = StringUtility.getParameter("schoolIds", request);

                final String[] lStrArrSchoolIds = schoolIds.split("~");
                final String[] ArrSchoolIds = new String[lStrArrSchoolIds.length];

                for (Integer lInt = 0; lInt < lStrArrSchoolIds.length; lInt++)
                {
                    if (lStrArrSchoolIds[lInt] != null && !"".equals(lStrArrSchoolIds[lInt]))
                    {
                        ArrSchoolIds[lInt] = lStrArrSchoolIds[lInt];
                    }
                }

                for (Integer lInt = 0; lInt < lStrArrSchoolIds.length; lInt++)
                {
                    displayPendingWorkDao.deleteSchool(ArrSchoolIds[lInt]);

                }
            }

            schoolStatList = displayPendingWorkDao.getSchoolList(lStrDdocode);

            objectArgs.put("schoolStatList", schoolStatList);
            objectArgs.put("Ddocode", lStrDdocode);

            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);// put in result object
            resultObject.setViewName("deleteSchool");// set view name

        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            this.logger.error("Error in loadEmpDtlsDdoWise " + e);
        }
        return resultObject;
    }

    public ResultObject displayInstituteNameForDiseCode(final Map inputMap) throws Exception
    {
        this.logger.info("inside displayInstituteNameForDiseCode to get institute name for dise code");
        final ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        try
        {
            this.setSessionInfo(inputMap);

            final String diseCode = StringUtility.getParameter("diseCode", request).toString().trim();
            this.logger.info("diseCode" + diseCode);

            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, this.serv.getSessionFactory());

            // int count=zpDDOObj.checkDiceCode(completeDiceCode);
            final String instituteName = displayPendingWorkDao.getInstituteNameForDiseCode(diseCode);

            this.logger.info("institute name: " + instituteName);
            final String lSBStatus = this.getResponseXMLDocforIFSCCode(instituteName).toString();
            final String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

            inputMap.put("ajaxKey", lStrResult);
            objRes.setViewName("ajaxData");
            objRes.setResultValue(inputMap);

        }

        catch (final Exception e)
        {
            this.logger.error("Exception occured in searchPensionCaseList exception is " + e, e);
            objRes.setResultValue(null);
            objRes.setThrowable(e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");
        }
        return objRes;
    }

    public ResultObject findMissedEmp(final Map objectArgs)
    {
        this.logger.info("Inside Get missed Employeeeeee");
        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        String lStrDdocode = null;
        try
        {

            lStrDdocode = StringUtility.getParameter("Ddocode", request);

            this.logger.info("DDO Code is " + lStrDdocode);
            List lLstMissedEmpStatistics = null;

            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());
            String empId = null;
            if (StringUtility.getParameter("empId", request) != null
                    && StringUtility.getParameter("empId", request) != "")
            {
                empId = StringUtility.getParameter("empId", request);
                this.logger.info("emp id is " + empId);
                displayPendingWorkDao.sendToLevel1(empId);
            }

            lLstMissedEmpStatistics = displayPendingWorkDao.getMissedEmployeeDDOwise(lStrDdocode);

            objectArgs.put("empList", lLstMissedEmpStatistics);
            objectArgs.put("Ddocode", lStrDdocode);

            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);// put in result object
            resultObject.setViewName("findMissingEmployee");// set view name

        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            this.logger.error("Error in loadEmpDtlsDdoWise " + e);
        }
        return resultObject;
    }

    /*
     * public ResultObject sendBackToDDOAsst(Map<String, Object> inputMap) {
     * 
     * logger.info("hiii i m to d=send back to level 1 ddo....****");
     * ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
     * ServiceLocator serviceLocator = (ServiceLocator)
     * inputMap.get("serviceLocator"); Boolean lBlSuccessFlag = null; MstEmp
     * lObjMstEmp = null; HttpServletRequest request = (HttpServletRequest)
     * inputMap.get("requestObj"); try { ServiceLocator serv = (ServiceLocator)
     * inputMap.get("serviceLocator"); Map loginMap = (Map) (Map)
     * inputMap.get("baseLoginMap");
     * 
     * DisplayPendingWorkDao displayPendingWorkDao = new
     * DisplayPendingWorkDaoImpl(DisplayPendingWorkDaoImpl.class,
     * serv.getSessionFactory()); String lStrDcpsEmpIds =
     * StringUtility.getParameter("draftDcpsEmpIds", request).trim();
     * logger.info
     * ("hiii the enmployee which need to sent to level1 is"+lStrDcpsEmpIds);
     * displayPendingWorkDao.sendToLevel1(lStrDcpsEmpIds); lBlSuccessFlag =
     * true; String lSBStatus = getResponseXMLDoc(lBlSuccessFlag).toString();
     * String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
     * lSBStatus).toString();
     * 
     * inputMap.put("ajaxKey", lStrResult); resObj.setViewName("ajaxData");
     * resObj.setResultValue(inputMap);
     * 
     * } catch (Exception e) { //e.printStackTrace();
     * resObj.setResultValue(null); resObj.setThrowable(e);
     * resObj.setResultCode(ErrorConstants.ERROR);
     * resObj.setViewName("errorPage"); logger.error(" Error in getDigiSig " +
     * e, e); }
     * 
     * return resObj;
     * 
     * }
     * 
     * private StringBuilder getResponseXMLDoc(Boolean flag) {
     * 
     * StringBuilder lStrBldXML = new StringBuilder();
     * 
     * lStrBldXML.append("<XMLDOC>"); lStrBldXML.append("<Flag>");
     * lStrBldXML.append(flag); lStrBldXML.append("</Flag>");
     * lStrBldXML.append("</XMLDOC>");
     * 
     * return lStrBldXML; }
     */
    // added by toshan for delete approve Employee
    // added by roshan to change the usernameScreen
    public ResultObject getDDODetails(final Map inputMap) throws Exception
    {
        this.logger.info("hiii i m in ddoinfoSeviceImpl to getDDodtls");
        // ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        try
        {
            this.setSessionInfo(inputMap);
            final ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            final Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");

            this.logger.info("hiii i m finding ddo code");
            final String loggedInPost = loginMap.get("loggedInPost").toString();
            final long langId = Long.parseLong(loginMap.get("langId").toString());
            this.logger.info("hiii i m finding logged in post Id" + loggedInPost);
            this.logger.info("hiii i m finding ddo code" + langId);
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            // code to find the logged in DDO Code
            this.logger.info("hiii i m finding ddo code" + locId);
            // long loggedInPostId =
            // Long.parseLong(inputMap.get("primaryPostId").toString());
            // logger.info("hiii i m finding ddo code"+loggedInPostId);
            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());
            new DcpsCommonDAOImpl(null, serv.getSessionFactory());
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
                this.logger.info("hiii i m finding ddo code");
            }
            String ddoCode = null;
            if (ddoMst != null)
            {
                ddoCode = ddoMst.getDdoCode();
            }
            this.logger.info("hiii i m finding ddo code");
            this.logger.info("hii The Logged in DDO Code is " + ddoCode);
            // added by roshan to insert the first entry for a ddo in
            // hr_pay_ddo_history
            String ddoName = null;
            String fromDate = null;
            String toDate = null;
            if (StringUtility.getParameter("ddoName", request) != null
                    && StringUtility.getParameter("ddoName", request) != "")
            {
                ddoName = StringUtility.getParameter("ddoName", request);
                fromDate = StringUtility.getParameter("ddoFromDate", request);
                toDate = StringUtility.getParameter("ddoToDate", request);
                this.logger.info("ddo_name is " + ddoName);
                this.logger.info("fromDate is " + fromDate);
                this.logger.info("toDate is " + toDate);
                String serviceFromDate = null;
                String serviceToDate = null;
                final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                try
                {
                    if (fromDate != null || fromDate != "")
                    {
                        serviceFromDate = sdf2.format(sdf1.parse(fromDate));
                    }
                    if (toDate != null || toDate != "")
                    {
                        serviceToDate = sdf2.format(sdf1.parse(toDate));
                    }
                } catch (final Exception e)
                {
                    e.printStackTrace();
                }
                displayPendingWorkDao.insertDetails(ddoCode, ddoName, serviceFromDate, serviceToDate);
                displayPendingWorkDao.updateDdoNameInOrgEmpMst(this.gLngUserId, ddoName);
            }
            // added by roshan to insert the first entry for a ddo in
            // hr_pay_ddo_history
            // added by roshan to update the details.
            String srNo = null;
            String updatedDdoName = null;
            String updatedFromDate = null;
            String updatedToDate = null;

            if (StringUtility.getParameter("updateDdoName", request) != null
                    && !StringUtility.getParameter("updateDdoName", request).equals(""))
            {
                srNo = StringUtility.getParameter("srNo", request);
                updatedDdoName = StringUtility.getParameter("updateDdoName", request);
                updatedFromDate = StringUtility.getParameter("updatedtxtFromDate", request);
                updatedToDate = StringUtility.getParameter("updatedtxtTodate", request);
                this.logger.info("srNo is " + srNo);
                this.logger.info("updatedDdoName is " + updatedDdoName);
                this.logger.info("updatedFromDate is " + updatedFromDate);
                this.logger.info("updatedToDate is " + updatedToDate);
                displayPendingWorkDao.updateDetails(Long.valueOf(srNo), updatedDdoName, updatedFromDate, updatedToDate);
                displayPendingWorkDao.updateDdoNameInOrgEmpMst(this.gLngUserId, updatedDdoName);
            }
            String updatedtxtNameNew = null;
            String updatedtxtFromDateNew = null;
            String updatedtxtTodateNew = null;

            if (StringUtility.getParameter("updatedtxtNameNew", request) != null
                    && !StringUtility.getParameter("updatedtxtNameNew", request).equals(""))
            {

                updatedtxtNameNew = StringUtility.getParameter("updatedtxtNameNew", request);
                updatedtxtFromDateNew = StringUtility.getParameter("updatedtxtFromDateNew", request);
                updatedtxtTodateNew = StringUtility.getParameter("updatedtxtTodateNew", request);

                this.logger.info("updatedtxtNameNew is " + updatedtxtNameNew);
                this.logger.info("updatedtxtFromDateNew is " + updatedtxtFromDateNew);
                this.logger.info("updatedtxtTodateNew is " + updatedtxtTodateNew);
                displayPendingWorkDao.insertNewDetails(ddoCode, updatedtxtNameNew, updatedtxtFromDateNew,
                        updatedtxtTodateNew);
            }

            long ddoHistoryCount = 0;
            ddoHistoryCount = displayPendingWorkDao.getDDOHistory(ddoCode);
            this.logger.info("hi history record count is" + ddoHistoryCount);
            List ddoHistory = null;
            ddoHistory = displayPendingWorkDao.getDDoHistoryDetails(ddoCode);
            // code to find the existing DDO Details.
            this.logger.info("logged in user id is" + this.gLngUserId);
            String ExistingDDOName = null;
            ExistingDDOName = displayPendingWorkDao.getDetails(this.gLngUserId);
            inputMap.put("ExistingDDODetails", ExistingDDOName);
            String ExistingDDoFromDate = null;
            ExistingDDoFromDate = displayPendingWorkDao.getCretaedDate(this.gLngUserId);
            this.logger.info("logged in user created date is " + ExistingDDoFromDate);
            // code to find the existing DDO Details.
            inputMap.put("count", ddoHistoryCount);
            inputMap.put("ddoHistoryList", ddoHistory);
            inputMap.put("ddoCode", ddoCode);
            inputMap.put("ExistingDDoFromDate", ExistingDDoFromDate);

            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(inputMap);// put in result object
            if (ddoHistoryCount == 0)
            {

                resultObject.setViewName("UpdateDDODetails");// set view name
            } else
            {
                resultObject.setViewName("maintainDDOHistory");// set view name
            }
        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            this.logger.error("Error in loadEmpDtlsDdoWise " + e);
        }
        return resultObject;
    }

    /*
     * public ResultObject deleteSelectedEmployee(Map<String, Object> inputMap)
     * {
     * 
     * logger.info("hiii i m to delete emp...****"); ResultObject resObj = new
     * ResultObject(ErrorConstants.SUCCESS, "FAIL"); ServiceLocator
     * serviceLocator = (ServiceLocator) inputMap.get("serviceLocator"); Boolean
     * lBlSuccessFlag = null; MstEmp lObjMstEmp = null; HttpServletRequest
     * request = (HttpServletRequest) inputMap.get("requestObj"); try {
     * ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
     * Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
     * 
     * DisplayPendingWorkDao displayPendingWorkDao = new
     * DisplayPendingWorkDaoImpl(DisplayPendingWorkDaoImpl.class,
     * serv.getSessionFactory());
     * 
     * 
     * String lStrDcpsEmpIds = StringUtility.getParameter("draftDcpsEmpIds",
     * request).trim();
     * logger.info("hiii the enmployee which need to delete  is"
     * +lStrDcpsEmpIds);
     * displayPendingWorkDao.deleteSelectedEmployee(lStrDcpsEmpIds);
     * lBlSuccessFlag = true; String lSBStatus =
     * getResponseXMLDoc(lBlSuccessFlag).toString(); String lStrResult = new
     * AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
     * 
     * inputMap.put("ajaxKey", lStrResult); resObj.setViewName("ajaxData");
     * resObj.setResultValue(inputMap);
     * 
     * } catch (Exception e) { //e.printStackTrace();
     * resObj.setResultValue(null); resObj.setThrowable(e);
     * resObj.setResultCode(ErrorConstants.ERROR);
     * resObj.setViewName("errorPage"); logger.error(" Error in getDigiSig " +
     * e, e); }
     * 
     * return resObj;
     * 
     * }
     */
    public ResultObject getEmployeeList(final Map objectArgs)
    {
        this.logger.info("Inside Get all employee for update super ann date....");
        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        String lStrDdocode = null;
        new SimpleDateFormat("dd/MM/yyyy");
        try
        {

            lStrDdocode = StringUtility.getParameter("Ddocode", request);

            this.logger.info("DDO Code is " + lStrDdocode);
            List allEmp = null;

            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());
            String empId = null;
            String servEnddate = null;
            if (StringUtility.getParameter("empId", request) != null
                    && StringUtility.getParameter("empId", request) != "")
            {
                empId = StringUtility.getParameter("empId", request);
                this.logger.info("emp id is " + empId);
                servEnddate = StringUtility.getParameter("servEnddate", request);
                String serviceEndDate = null;

                final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                try
                {
                    serviceEndDate = sdf2.format(sdf1.parse(servEnddate));
                } catch (final Exception e)
                {
                    e.printStackTrace();
                }

                displayPendingWorkDao.updateServiceExpiryDate(empId, serviceEndDate);
            }

            allEmp = displayPendingWorkDao.getApproveEmployeeDDOwise(lStrDdocode);

            objectArgs.put("empList", allEmp);
            objectArgs.put("Ddocode", lStrDdocode);

            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);// put in result object
            resultObject.setViewName("updateServiceExpiryDate");// set view name

        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            this.logger.error("Error in loadEmpDtlsDdoWise " + e);
        }
        return resultObject;
    }

    private StringBuilder getResponseXMLDoc(final Boolean flag)
    {

        final StringBuilder lStrBldXML = new StringBuilder();

        lStrBldXML.append("<XMLDOC>");
        lStrBldXML.append("<Flag>");
        lStrBldXML.append(flag);
        lStrBldXML.append("</Flag>");
        lStrBldXML.append("</XMLDOC>");

        return lStrBldXML;
    }

    private StringBuilder getResponseXMLDocforIFSCCode(final String instituteName)
    {

        final StringBuilder lStrBldXML = new StringBuilder();

        lStrBldXML.append("<XMLDOC>");
        lStrBldXML.append("<IFSCCode>");
        lStrBldXML.append(instituteName);
        lStrBldXML.append("</IFSCCode>");
        lStrBldXML.append("</XMLDOC>");

        return lStrBldXML;

    }

    public ResultObject getResult(final Map objectArgs)
    {
        this.logger.info("Inside Get all employee for update super ann date....");
        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        String txtQuery = null;
        new SimpleDateFormat("dd/MM/yyyy");
        try
        {

            txtQuery = StringUtility.getParameter("txtQuery", request);

            this.logger.info("DDO Code is " + txtQuery);
            List allData = null;

            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());
            allData = displayPendingWorkDao.getResult(txtQuery);

            objectArgs.put("empList", allData);
            objectArgs.put("query", txtQuery);

            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);// put in result object
            resultObject.setViewName("executeQuery");// set view name

        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            this.logger.error("Error in loadEmpDtlsDdoWise " + e);
        }
        return resultObject;
    }

    // added by samadhan to update details
    public ResultObject saveUpdatedDetails(final Map objectArgs) throws Exception
    {
        this.logger.info("Inside saveUpdatedDetails");
        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        try
        {
            final Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
            }
            String lStrDdocode = null;
            if (ddoMst != null)
            {
                lStrDdocode = ddoMst.getDdoCode();
            }
            this.logger.info("DDO Code is " + lStrDdocode);

            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());

            final String udise = StringUtility.getParameter("udise", request);
            String mobileNo = StringUtility.getParameter("mobileNo", request);
            String email = StringUtility.getParameter("email", request);
            StringUtility.getParameter("instituteName", request);
            String tanNo = StringUtility.getParameter("tanNo", request);

            displayPendingWorkDao.updateUDise(udise, lStrDdocode);
            displayPendingWorkDao.updateMobileNo(mobileNo, lStrDdocode);
            displayPendingWorkDao.updateEmail(email, lStrDdocode);
            // displayPendingWorkDao.updateInstituteName(instituteName,udise,lStrDdocode);
            displayPendingWorkDao.updateTanNo(tanNo, lStrDdocode);

            this.logger.info("DDO code to get mobile no: " + lStrDdocode);
            mobileNo = displayPendingWorkDao.getMobileNo(lStrDdocode);
            this.logger.info("DDO code to get Email: " + lStrDdocode);
            email = displayPendingWorkDao.getEmail(lStrDdocode);
            this.logger.info("DDO code to get Tan No: " + lStrDdocode);
            tanNo = displayPendingWorkDao.getTanNo(lStrDdocode);

            objectArgs.put("mobileNo", mobileNo);
            objectArgs.put("email", email);
            objectArgs.put("tanNo", tanNo);

            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);// put in result object
            resultObject.setViewName("pendingWorkForAsstDdo");// set view name

        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            this.logger.error("Error in update details " + e);
        }
        return resultObject;
    }

    // added by samadhan to update details
    public ResultObject saveUpdatedDetailsNonAst(final Map objectArgs) throws Exception
    {
        this.logger.info("Inside saveUpdatedDetailsNonAst");
        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        try
        {
            final Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
            }
            String lStrDdocode = null;
            if (ddoMst != null)
            {
                lStrDdocode = ddoMst.getDdoCode();
            }
            this.logger.info("DDO Code is " + lStrDdocode);

            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());

            String mobileNo = StringUtility.getParameter("mobileNo", request);
            String email = StringUtility.getParameter("email", request);
            final String secretQuestion = StringUtility.getParameter("secretQuestion", request);
            final String secretAnswer = StringUtility.getParameter("secretAnswer", request);

            displayPendingWorkDao.updateMobileNo(mobileNo, lStrDdocode);
            displayPendingWorkDao.updateEmail(email, lStrDdocode);
            displayPendingWorkDao.updateSecretQuestionAndAnswer(secretQuestion, secretAnswer, lStrDdocode);
            // displayPendingWorkDao.updateSecretQuestionAnswer(secretAnswer,lStrDdocode);

            this.logger.info("DDO code to get mobile no: " + lStrDdocode);
            mobileNo = displayPendingWorkDao.getMobileNo(lStrDdocode);
            this.logger.info("DDO code to get Email: " + lStrDdocode);
            email = displayPendingWorkDao.getEmail(lStrDdocode);

            objectArgs.put("mobileNo", mobileNo);
            objectArgs.put("email", email);

            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(objectArgs);// put in result object
            resultObject.setViewName("pendingWork");// set view name

        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            this.logger.error("Error in update details " + e);
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

    public ResultObject showAllPendingWork(final Map objectArgs) throws Exception
    {
        this.logger.info("Entering into showAllPendingWork of DisplayPendingWork");
        final ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        try
        {
            this.logger.info("request.getSession().getId()******************" + request.getSession().getId());
            final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            final Map loginMap = (Map) objectArgs.get("baseLoginMap");
            request.getSession().getId();
            loginMap.get("loggedInPost").toString();
            Long.parseLong(loginMap.get("langId").toString());
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            Long.parseLong(loginMap.get("primaryPostId").toString());
            new DcpsCommonDAOImpl(null, serv.getSessionFactory());
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
            }
            String ddoCode = null;
            if (ddoMst != null)
            {
                ddoCode = ddoMst.getDdoCode();
            }
            // code to Check where DDO is level 1.
            final DisplayPendingWorkDao displayPendingWorkDao = new DisplayPendingWorkDaoImpl(
                    DisplayPendingWorkDaoImpl.class, serv.getSessionFactory());

            final long countOfDDOCode = displayPendingWorkDao.checkUser(ddoCode);
            final long countOfSameDDO = displayPendingWorkDao.findUsertype(ddoCode);
            final long countOfSameDDOToCheckLevel3 = displayPendingWorkDao.findUsertypeToCheckLevel3(ddoCode);
            final long countOfSameDDOToCheckLevel4 = displayPendingWorkDao.findUsertypeToCheckLevel4(ddoCode);
            String userType = null;
            String pendingWorkForAsstDDO = null;
            if (countOfDDOCode == 1)
            {
                this.logger.info("hi i m asst ddo");

                // Code to check whethre level 1 has pending work
                final String pendingWorkForLevel1 = displayPendingWorkDao.findPendingWork(ddoCode);
                this.logger.info("pendingWorkForLevel1" + pendingWorkForLevel1);
                if (pendingWorkForLevel1 == null || Long.parseLong(pendingWorkForLevel1) == -1)
                {
                    this.logger.info("pendingWorkForLevel1" + pendingWorkForLevel1);
                    pendingWorkForAsstDDO = "true";

                } else
                {
                    this.logger.info("pendingWorkForLevel1" + pendingWorkForLevel1);
                    pendingWorkForAsstDDO = "false";

                }

            }
            userType = "MDC";
            if (countOfSameDDO != 0)
            {
                userType = "reptDDO";
            }
            if (countOfSameDDOToCheckLevel3 != 0)
            {
                userType = "finalDDO";
            }
            if (countOfSameDDOToCheckLevel4 != 0)
            {
                userType = "specialDDO";
            }
            if (countOfDDOCode != 0)
            {
                userType = "asstDDO";
            }

            this.logger.info("userType" + userType);

            // end for levele 1
            long totalInstituteVerification = 0;
            long totalEmployeeForVerificationGPF = 0;
            long totalEmployeeForVerificationDCPS = 0;
            long totalOfficeForVerification = 0;
            long totalDraftForms = 0;

            String mobileNoNonAst = null;
            String emailNonAst = null;

            if (userType == "reptDDO")
            {
                totalInstituteVerification = displayPendingWorkDao.findTotalNumberOfschool(ddoCode);
                totalEmployeeForVerificationGPF = displayPendingWorkDao.findGPFCount(ddoCode);
                totalEmployeeForVerificationDCPS = displayPendingWorkDao.findDCPSCount(ddoCode);
                totalOfficeForVerification = displayPendingWorkDao.findTotalCountOfPendingOffice(ddoCode);

                this.logger.info("DDO code to get mobile no: " + ddoCode);
                mobileNoNonAst = displayPendingWorkDao.getMobileNo(ddoCode);
                this.logger.info("mobile No.: " + mobileNoNonAst);

                this.logger.info("DDO code to get Email: " + ddoCode);
                emailNonAst = displayPendingWorkDao.getEmail(ddoCode);
                this.logger.info("email: " + emailNonAst);
            }
            if (userType == "finalDDO")
            {
                totalInstituteVerification = displayPendingWorkDao.findTotalNumberOfschool(ddoCode);
                totalEmployeeForVerificationGPF = displayPendingWorkDao.findGPFCountByLevelThree(ddoCode);
                totalEmployeeForVerificationDCPS = displayPendingWorkDao.findDCPSCountByLevelThree(ddoCode);
                totalOfficeForVerification = displayPendingWorkDao.findTotalCountOfPendingOffice(ddoCode);

                this.logger.info("DDO code to get mobile no: " + ddoCode);
                mobileNoNonAst = displayPendingWorkDao.getMobileNo(ddoCode);
                this.logger.info("mobile No.: " + mobileNoNonAst);

                this.logger.info("DDO code to get Email: " + ddoCode);
                emailNonAst = displayPendingWorkDao.getEmail(ddoCode);
                this.logger.info("email: " + emailNonAst);
            }

            this.logger.info("Code to find count All The Employee Need For verification: START");

            objectArgs.put("totalInstituteVerification", totalInstituteVerification);
            objectArgs.put("totalEmployeeForVerificationGPF", totalEmployeeForVerificationGPF);
            objectArgs.put("totalEmployeeForVerificationDCPS", totalEmployeeForVerificationDCPS);
            objectArgs.put("totalOfficeForVerification", totalOfficeForVerification);
            objectArgs.put("userType", userType);
            objectArgs.put("pendingWorkForAsstDDO", pendingWorkForAsstDDO);

            objectArgs.put("mobileNo", mobileNoNonAst);
            objectArgs.put("email", emailNonAst);

            if (userType == "MDC")
            {
                objRes.setViewName("pendingWorkForLevel1");
            } else if (userType == "asstDDO")
            {
                this.logger.info("hhhi  i m asst DDo");
                totalDraftForms = displayPendingWorkDao.findCountDraftForms(ddoCode);
                objectArgs.put("totalDraftForms", totalDraftForms);

                // START added by samadhan for update details on home page

                this.logger.info("DDO code to get mobile no: " + ddoCode);
                final String mobileNo = displayPendingWorkDao.getMobileNo(ddoCode);
                this.logger.info("mobile No.: " + mobileNo);

                this.logger.info("DDO code to get Email: " + ddoCode);
                final String email = displayPendingWorkDao.getEmail(ddoCode);
                this.logger.info("email: " + email);

                this.logger.info("DDO code to get TAN no: " + ddoCode);
                String tanNo = null;
                tanNo = displayPendingWorkDao.getTanNo(ddoCode);
                this.logger.info("tanNo: " + tanNo);

                // logger.info("DDO code to get institute Name: "+ddoCode);
                // String
                // instituteName=displayPendingWorkDao.getInstituteName(ddoCode);
                // logger.info("instituteName: "+instituteName);

                objectArgs.put("mobileNo", mobileNo);
                objectArgs.put("email", email);
                objectArgs.put("tanNo", tanNo);
                // objectArgs.put("instituteName",instituteName);
                // END added by samadhan for update details on home page
                if (totalDraftForms == 0)
                {
                    objRes.setViewName("congratulation");

                } else
                {
                    objRes.setViewName("pendingWorkForAsstDdo");
                }

            } else
            {
                if (totalInstituteVerification == 0 && totalEmployeeForVerificationGPF == 0
                        && totalEmployeeForVerificationDCPS == 0 && totalOfficeForVerification == 0)
                {
                    objRes.setViewName("congratulation");

                } else
                {
                    objRes.setViewName("pendingWork");
                }
            }
        }

        catch (final Exception e)
        {
            this.logger.info("Exception Ocuures...displaying pending work...");
            this.logger.error("Error is: " + e.getMessage());
            objectArgs.put("msg", "There is Some Problem in displaying Pending Work.");
            objRes.setResultValue(objectArgs);
            objRes.setViewName("errorInsert");
        }
        return objRes;
    }

}
