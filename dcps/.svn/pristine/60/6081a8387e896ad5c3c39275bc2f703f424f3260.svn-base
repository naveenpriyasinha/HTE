/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 19, 2011		Bhargav Trivedi								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoInfoDAO;
import com.tcs.sgv.dcps.dao.DdoInfoDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

/**
 * Class Description -
 * 
 * 
 * @author Bhargav Trivedi
 * @version 0.1
 * @since JDK 5.0 Mar 19, 2011
 */
public class DdoInfoServiceImpl extends ServiceImpl implements DdoInfoService
{

    /* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    private Long gLngLangId = null; /* LANG ID */
    private HttpServletRequest request = null; /* REQUEST OBJECT */
    private ServiceLocator serv = null; /* SERVICE LOCATOR */
    private Date gDtCurDate = null; /* CURRENT DATE */

    /* Resource bundle for the constants */
    private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");

    private final static Logger gLogger = Logger.getLogger(DdoInfoServiceImpl.class);

    // added by samadhan for approve ddo history ApproveDDOHistory
    public ResultObject ApproveDDOHistory(final Map inputMap) throws Exception
    {

        final ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {
            this.setSessionInfo(inputMap);

            final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());
            final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, this.serv.getSessionFactory());
            final String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(this.gLngPostId);

            gLogger.info("PostId: " + this.gLngPostId + " DdoCode: " + lStrDdoCode);

            String ddo = null;
            List ddoHistory = null;
            String ddoType = null;

         //   gLogger.info("lStrDdoCode.isEmpty()" + lStrDdoCode.isEmpty());
            if (lStrDdoCode.isEmpty())
            {
                gLogger.info("+++++++++ddoCode is null!!!+++++++++");
                ddoHistory = null;
                inputMap.put("ddoHistoryList", ddoHistory);
            } else
            {
                //ddoType = lObjDdoInfoDAO.getDDOtype(lStrDdoCode);

            	 
                    gLogger.info("DDO is of level 2!");
                    // get level 1 ddo list
                    final List level1DDOList = lObjDdoInfoDAO.getLevel1DDOList(lStrDdoCode);
                    gLogger.info("size of level 2 ddo list: " + level1DDOList.size());
                    // get ddo history
                    inputMap.put("ddoCode", lStrDdoCode);
                    for (int i = 0; i < level1DDOList.size(); i++)
                    {
                        ddo = ddo + "," + level1DDOList.get(i).toString();
                    }
          
                ddoHistory = lObjDdoInfoDAO.getDDoHistoryDetailsForApprove(ddo);
                inputMap.put("ddoHistoryList", ddoHistory);

            }// end of else

            resObj.setResultValue(inputMap);
            resObj.setViewName("ApproveDDOHistory");

        } catch (final Exception e)
        {
            e.printStackTrace();
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
            gLogger.error("Error In loading DDO Info Page " + e);
        }

        return resObj;
    }

    public ResultObject aprvDDOHistory(final Map inputMap) throws Exception
    {

        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        final ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {

            final String srNo = StringUtility.getParameter("srNo", request);
            final String ddoCode = StringUtility.getParameter("ddoCode", request);
            final String ddoName = StringUtility.getParameter("ddoName", request);
            final String userID = StringUtility.getParameter("userID", request);
            gLogger.info("inside aprvDDOHistory srNo: " + srNo + " ddoCode: " + ddoCode + " ddoName: " + ddoName
                    + " userID: " + userID);

            this.setSessionInfo(inputMap);
            final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());
            final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, this.serv.getSessionFactory());
            final String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(this.gLngPostId);
            List ddoHistory = null;
            String ddo = null;
            if (lStrDdoCode.isEmpty())
            {
                gLogger.info("+++++++++ddoCode is null!!!+++++++++");
                ddoHistory = null;
                inputMap.put("ddoHistoryList", ddoHistory);
            } else
            {
                //ddoType = lObjDdoInfoDAO.getDDOtype(lStrDdoCode);

            	 
                    gLogger.info("DDO is of level 2!");
                    // get level 1 ddo list
                    final List level1DDOList = lObjDdoInfoDAO.getLevel1DDOList(lStrDdoCode);
                    gLogger.info("size of level 2 ddo list: " + level1DDOList.size());
                    // get ddo history
                    inputMap.put("ddoCode", lStrDdoCode);
                    for (int i = 0; i < level1DDOList.size(); i++)
                    {
                        ddo = ddo + "," + level1DDOList.get(i).toString();
                    } 
          
                ddoHistory = lObjDdoInfoDAO.getDDoHistoryDetailsForApprove(ddo);
                inputMap.put("ddoHistoryList", ddoHistory);

            }// end of else

            //final String ddoType = lObjDdoInfoDAO.getDDOtype(lStrDdoCode);

            // APPROVE:::::update status flag and name in org_user_mst
            lObjDdoInfoDAO.updateStatus(srNo, userID, ddoName);
            lObjDdoInfoDAO.updateDDOName(srNo, userID, ddoName);

            // START added by samadhan to update ddo name in ORG_DDO_MST
            lObjDdoInfoDAO.updateDDONameORGDDOMST(ddoName, ddoCode);

            // END added by samadhan to update ddo name in ORG_DDO_MST

            // code to get ddo history
            
            
            //code to get ddo history
           /* if (ddoType.equals("100"))
            {
                gLogger.info("DDO is of level 2!");
                // get level 1 ddo list
                final List level1DDOList = lObjDdoInfoDAO.getLevel1DDOList(lStrDdoCode);
                gLogger.info("size of level 1 ddo list: " + level1DDOList.size());
                // get ddo history
                inputMap.put("ddoCode", lStrDdoCode);
                for (int i = 0; i < level1DDOList.size(); i++)
                {
                    ddo = ddo + "," + level1DDOList.get(i).toString();
                }
            } else if (ddoType.equals("010"))
            {
                gLogger.info("DDO is of level 3!");
                // get level 2 ddo list
                final List level2DDOList = lObjDdoInfoDAO.getLevel2DDOList(lStrDdoCode);
                gLogger.info("size of level 2 ddo list: " + level2DDOList.size());
                // get ddo history
                for (int i = 0; i < level2DDOList.size(); i++)
                {
                    ddo = ddo + "," + level2DDOList.get(i).toString();
                }
            } else if (ddoType.equals("001"))
            {
                gLogger.info("DDO is of level 4!");
                // get level 3 ddo list
                final List level3DDOList = lObjDdoInfoDAO.getLevel3DDOList(lStrDdoCode);
                gLogger.info("size of level 3 ddo list: " + level3DDOList.size());
                // get ddo history
                for (int i = 0; i < level3DDOList.size(); i++)
                {
                    ddo = ddo + "," + level3DDOList.get(i).toString();
                }
            }*/
            
            //gLogger.info("************ddo***********"+ddo);
            //ddoHistory = lObjDdoInfoDAO.getDDoHistoryDetailsForApprove(ddo);
            //gLogger.info("************ddoHistory***********"+ddoHistory.size());
            //inputMap.put("ddoHistoryList", ddoHistory);

            resObj.setResultValue(inputMap);
            resObj.setViewName("ApproveDDOHistory");
        } catch (final Exception e)
        {
            e.printStackTrace();
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
            gLogger.error("Error In loading DDO Info Page " + e);
        }
        return resObj;
    }

    public ResultObject displayIFSCCodeForBranch(final Map inputMap) throws Exception
    {

        final ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {

            /* Sets the Session Information */
            this.setSessionInfo(inputMap);

            /* Initializes the DAO */
            final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());

            /* Gets the branch name from request */
            final Long lLongbranchName = Long.valueOf(StringUtility.getParameter("cmbBranchName", this.request)
                    .toString().trim());

            /* gets the MICR code for the selected branch */
            Long micrCode = lObjDcpsCommonDAO.getIFSCCodeForBranch(lLongbranchName);

            /* sends the MICR Code using AJAX */
            final String lSBStatus = this.getResponseXMLDocforIFSCCode(micrCode).toString();
            final String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

            inputMap.put("ajaxKey", lStrResult);
            micrCode = null;
            objRes.setViewName("ajaxData");
            objRes.setResultValue(inputMap);

        }

        catch (final Exception e)
        {
            gLogger.error("Error is " + e, e);
            objRes.setResultValue(null);
            objRes.setThrowable(e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");
        }
        return objRes;

    }

    /*
     * 
     */

    // added by roshan for approving the ddo information
    /*
     * public ResultObject loadDDOInfoApprovalPage(Map inputMap) throws
     * Exception {
     * 
     * ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
     * String lStrDdoOffice = "Yes";
     * 
     * try { Added by ketan Sets the Session Information
     * setSessionInfo(inputMap);
     * gLogger.info("approveDdoInformationData::::::::::::method Call:::");
     * Initializes the DAOs and variables DcpsCommonDAO lObjDcpsCommonDAO = new
     * DcpsCommonDAOImpl(DcpsCommonDAOImpl.class, serv .getSessionFactory());
     * DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, serv
     * .getSessionFactory());
     * 
     * Gets List of all states List lLstState =
     * lObjDcpsCommonDAO.getStateNames(1L); List lLstDistricts =
     * lObjDcpsCommonDAO.getDistricts(15L);
     * 
     * Gets Office Class List List lstOfficeClass =
     * IFMSCommonServiceImpl.getLookupValues( "DCPS_OFFICE_CLASS",
     * SessionHelper.getLangId(inputMap), inputMap);
     * 
     * Gets DDO Code from Post Id String lStrDdoCode =
     * lObjDcpsCommonDAO.getDdoCode(gLngPostId);
     * System.out.println("login :::post id:::::"+gLngPostId); Gets All DDO
     * offices for that DDO List lLstSavedOffices =
     * lObjDdoInfoDAO.getAllOffices(lStrDdoCode);
     * 
     * if (lLstSavedOffices != null) { for (Integer lInt = 0; lInt <
     * lLstSavedOffices.size(); lInt++) { Object[] lObjListDdoOffice =
     * (Object[]) lLstSavedOffices .get(lInt); if
     * (lObjListDdoOffice[2].equals("Yes")) { lStrDdoOffice = "No"; } } }
     * 
     * List lstapproveDDOOfficeData =
     * lObjDdoInfoDAO.getApproveDdoInformation((gLngPostId));
     * if(lstapproveDDOOfficeData!=null && lstapproveDDOOfficeData.size()>0) {
     * inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData); } //String
     * lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
     * 
     * Puts all above values in the Map inputMap.put("lLstDistricts",
     * lLstDistricts); inputMap.put("DdoOfficeOrNot", lStrDdoOffice);
     * inputMap.put("STATENAMES", lLstState); inputMap.put("OFFICECLASSLIST",
     * lstOfficeClass); inputMap.put("DDOCODE", lStrDdoCode);
     * inputMap.put("OfficeList", lLstSavedOffices);
     * inputMap.put("ApproveDDOOfficeList", lstapproveDDOOfficeData);
     * 
     * resObj.setResultValue(inputMap);
     * resObj.setViewName("ApproveDDOInformation");
     * 
     * } catch (Exception e) { e.printStackTrace(); gLogger.error(" Error is : "
     * + e, e); resObj.setViewName("errorPage"); }
     * 
     * return resObj; }
     * 
     * 
     * }
     */
    // added by roshan for maintain ddo history.
    // added by roshan to change the usernameScreen
    public ResultObject getDDODetails(final Map inputMap) throws Exception
    {
        gLogger.info("hiii i m in ddoinfoSeviceImpl to getDDodtls");
        // ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        try
        {
            this.setSessionInfo(inputMap);
            final ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            final Map loginMap = (Map) inputMap.get("baseLoginMap");

            gLogger.info("hiii i m finding ddo code");
            final String loggedInPost = loginMap.get("loggedInPost").toString();
            final long langId = Long.parseLong(loginMap.get("langId").toString());
            gLogger.info("hiii i m finding logged in post Id" + loggedInPost);
            gLogger.info("hiii i m finding ddo code" + langId);
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            // code to find the logged in DDO Code
            gLogger.info("hiii i m finding ddo code" + locId);
            // long loggedInPostId =
            // Long.parseLong(inputMap.get("primaryPostId").toString());
            // logger.info("hiii i m finding ddo code"+loggedInPostId);
            final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, serv.getSessionFactory());
            new DcpsCommonDAOImpl(null, serv.getSessionFactory());
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
                gLogger.info("hiii i m finding ddo code");
            }
            String ddoCode = null;
            if (ddoMst != null)
            {
                ddoCode = ddoMst.getDdoCode();
            }
            gLogger.info("hiii i m finding ddo code");
            gLogger.info("hii The Logged in DDO Code is " + ddoCode);
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
                gLogger.info("ddo_name is " + ddoName);
                gLogger.info("fromDate is " + fromDate);
                gLogger.info("toDate is " + toDate);
                String serviceFromDate = null;
                String serviceToDate = null;
                final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                try
                {
                    serviceFromDate = sdf2.format(sdf1.parse(fromDate));
                    serviceToDate = sdf2.format(sdf1.parse(toDate));
                } catch (final Exception e)
                {
                    e.printStackTrace();
                }
                // added by samadhan for entry of pk,status and user_id
                final Long pkforHRPayDdoHistory = IFMSCommonServiceImpl.getNextSeqNum("HR_PAY_DDO_HISTORY", inputMap);
                final String strPkforHRPayDdoHistory = pkforHRPayDdoHistory.toString();
                final String strUserId = this.gLngUserId.toString();
                final String status = "0";
                gLogger.info("==================userId From LoginMap in dao==================" + strUserId);
                lObjDdoInfoDAO.insertDetails(strPkforHRPayDdoHistory, ddoCode, ddoName, serviceFromDate, serviceToDate,
                        status, strUserId);
                // lObjDdoInfoDAO.updateDdoNameInOrgEmpMst(gLngUserId,ddoName);
            }
            // added by roshan to insert the first entry for a ddo in
            // hr_pay_ddo_history
            // added by roshan to update the details.
            String srNo = null;
            String updatedDdoName = null;
            String updatedFromDate = null;
            String updatedToDate = null;

            if (StringUtility.getParameter("srNo", request) != null
                    && StringUtility.getParameter("srNo", request) != "")
            {
                srNo = StringUtility.getParameter("srNo", request);
                updatedDdoName = StringUtility.getParameter("updateDdoName", request);
                updatedFromDate = StringUtility.getParameter("updatedtxtFromDate", request);
                updatedToDate = StringUtility.getParameter("updatedtxtTodate", request);
                gLogger.info("srNo is " + srNo);
                gLogger.info("updatedDdoName is " + updatedDdoName);
                gLogger.info("updatedFromDate is " + updatedFromDate);
                gLogger.info("updatedToDate is " + updatedToDate);
                lObjDdoInfoDAO.updateDetails(Long.valueOf(srNo), updatedDdoName, updatedFromDate, updatedToDate);
                lObjDdoInfoDAO.updateDdoNameInOrgEmpMst(this.gLngUserId, updatedDdoName);
            }
            String updatedtxtNameNew = null;
            String updatedtxtFromDateNew = null;
            String updatedtxtTodateNew = null;

            if (StringUtility.getParameter("updatedtxtNameNew", request) != null
                    && StringUtility.getParameter("updatedtxtNameNew", request) != "")
            {

                updatedtxtNameNew = StringUtility.getParameter("updatedtxtNameNew", request);
                updatedtxtFromDateNew = StringUtility.getParameter("updatedtxtFromDateNew", request);
                updatedtxtTodateNew = StringUtility.getParameter("updatedtxtTodateNew", request);

                gLogger.info("updatedtxtNameNew is " + updatedtxtNameNew);
                gLogger.info("updatedtxtFromDateNew is " + updatedtxtFromDateNew);
                gLogger.info("updatedtxtTodateNew is " + updatedtxtTodateNew);

                // added by samadhan for entry of pk, status, userId
                final Long pkforHRPayDdoHistory = IFMSCommonServiceImpl.getNextSeqNum("HR_PAY_DDO_HISTORY", inputMap);
                final String strPkforHRPayDdoHistory = pkforHRPayDdoHistory.toString();
                final String strUserId = this.gLngUserId.toString();
                final String status = "0";
                gLogger.info("==================userId From LoginMap in dao==================" + strUserId);
                lObjDdoInfoDAO.insertNewDetails(strPkforHRPayDdoHistory, ddoCode, updatedtxtNameNew,
                        updatedtxtFromDateNew, updatedtxtTodateNew, status, strUserId);
            }

            long ddoHistoryCount = 0;
            ddoHistoryCount = lObjDdoInfoDAO.getDDOHistory(ddoCode);
            gLogger.info("hi history record count is" + ddoHistoryCount);
            List ddoHistory = null;
            ddoHistory = lObjDdoInfoDAO.getDDoHistoryDetails(ddoCode);
            // code to find the existing DDO Details.
            gLogger.info("logged in user id is" + this.gLngUserId);
            String ExistingDDOName = null;
            ExistingDDOName = lObjDdoInfoDAO.getDetails(this.gLngUserId);
            inputMap.put("ExistingDDODetails", ExistingDDOName);
            String ExistingDDoFromDate = null;
            ExistingDDoFromDate = lObjDdoInfoDAO.getCretaedDate(this.gLngUserId);
            gLogger.info("logged in user created date is " + ExistingDDoFromDate);
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
        }

        catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            gLogger.error("Error in loadEmpDtlsDdoWise " + e);

        }

        return resultObject;
    }

    // added and modified by roshan For CSRF

    /*
     * 
     */

    private StringBuilder getResponseXMLDoc(final Boolean flag, final Boolean ddoExistsFlag)
    {

        final StringBuilder lStrBldXML = new StringBuilder();

        lStrBldXML.append("<XMLDOC>");
        lStrBldXML.append("<Flag>");
        lStrBldXML.append(flag);
        lStrBldXML.append("</Flag>");
        lStrBldXML.append("<ddoExistsFlag>");
        lStrBldXML.append(ddoExistsFlag);
        lStrBldXML.append("</ddoExistsFlag>");
        lStrBldXML.append("</XMLDOC>");

        return lStrBldXML;
    }

    private StringBuilder getResponseXMLDocforIFSCCode(final Long lLongIFSCCode)
    {

        final StringBuilder lStrBldXML = new StringBuilder();

        lStrBldXML.append("<XMLDOC>");
        lStrBldXML.append("<IFSCCode>");
        lStrBldXML.append(lLongIFSCCode);
        lStrBldXML.append("</IFSCCode>");
        lStrBldXML.append("</XMLDOC>");

        return lStrBldXML;

    }

    /*
     * MAINTAIN DDO HISTORY
     * 
     * 
     * STATUSFirst Insert:-0New entry=3update=0approve=1reject=2
     */
    public ResultObject insertDdoDetails(final Map inputMap) throws Exception
    {
        gLogger.info("hiii i m in ddoinfoSeviceImpl to getDDodtls");
        // ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        try
        {
            this.setSessionInfo(inputMap);
            final ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            final Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");

            gLogger.info("hiii i m finding ddo code");
            final String loggedInPost = loginMap.get("loggedInPost").toString();
            final long langId = Long.parseLong(loginMap.get("langId").toString());
            gLogger.info("hiii i m finding logged in post Id" + loggedInPost);
            gLogger.info("hiii i m finding ddo code" + langId);
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            // code to find the logged in DDO Code
            gLogger.info("hiii i m finding ddo code" + locId);
            // long loggedInPostId =
            // Long.parseLong(inputMap.get("primaryPostId").toString());
            // logger.info("hiii i m finding ddo code"+loggedInPostId);
            final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, serv.getSessionFactory());
            final DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
                gLogger.info("hiii i m finding ddo code");
            }
            String ddoCode = null;
            if (ddoMst != null)
            {
                ddoCode = ddoMst.getDdoCode();
            }
            gLogger.info("hiii i m finding ddo code");
            gLogger.info("hii The Logged in DDO Code is " + ddoCode);
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
                gLogger.info("ddo_name is " + ddoName);
                gLogger.info("fromDate is " + fromDate);
                gLogger.info("toDate is " + toDate);
                String serviceFromDate = null;
                String serviceToDate = null;
                final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                try
                { 
                    if(fromDate!=null && !fromDate.equals(""))
                        serviceFromDate = sdf2.format(sdf1.parse(fromDate));
                    gLogger.info("serviceFromDate is **************" + serviceFromDate);
                    if(toDate!=null && !toDate.equals(""))
                        serviceToDate = sdf2.format(sdf1.parse(toDate));
                } catch (final Exception e)
                {
                    e.printStackTrace();
                }
                // added by samadhan for entry of pk
                final Long pkforHRPayDdoHistory = IFMSCommonServiceImpl.getNextSeqNum("HR_PAY_DDO_HISTORY", inputMap);
                final String strPkforHRPayDdoHistory = pkforHRPayDdoHistory.toString();
                final String strUserId = this.gLngUserId.toString();
                final String status = "0";
                gLogger.info("==================userId From LoginMap in dao==================" + strUserId);
                lObjDdoInfoDAO.insertDetails(strPkforHRPayDdoHistory, ddoCode, ddoName, serviceFromDate, serviceToDate,
                        status, strUserId);
                // lObjDdoInfoDAO.updateDdoNameInOrgEmpMst(gLngUserId,ddoName);
            }
            // added by roshan to insert the first entry for a ddo in
            // hr_pay_ddo_history

            long ddoHistoryCount = 0;
            ddoHistoryCount = lObjDdoInfoDAO.getDDOHistory(ddoCode);
            gLogger.info("hi history record count is" + ddoHistoryCount);
            List ddoHistory = null;
            ddoHistory = lObjDdoInfoDAO.getDDoHistoryDetails(ddoCode);
            // code to find the existing DDO Details.
            gLogger.info("logged in user id is" + this.gLngUserId);
            String ExistingDDOName = null;
            ExistingDDOName = lObjDdoInfoDAO.getDetails(this.gLngUserId);
            inputMap.put("ExistingDDODetails", ExistingDDOName);
            String ExistingDDoFromDate = null;
            ExistingDDoFromDate = lObjDdoInfoDAO.getCretaedDate(this.gLngUserId);
            gLogger.info("logged in user created date is " + ExistingDDoFromDate);
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
        }

        catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            gLogger.error("Error in loadEmpDtlsDdoWise " + e);

        }

        return resultObject;
    }

    public ResultObject loadDDOInfoPage(final Map inputMap) throws Exception
    {

        final ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {

            /* Sets the Session Information */
            this.setSessionInfo(inputMap);

            /* Initializes the DAOs and variables */
            final DdoInfoDAO ddoInfoDAO = new DdoInfoDAOImpl(null, this.serv.getSessionFactory());

            final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());
            final LocationDAOImpl lObjLocationDAO = new LocationDAOImpl(null, this.serv.getSessionFactory());
            final SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            /* Get the DDO Code corresponding to the DDO Asst logged in */
            String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(this.gLngPostId);

            /* Get the list of all the banks */
            List lLstBankNames = lObjDcpsCommonDAO.getBankNames();

            /* End : Get the list of all the Departments */

            /* Get the list of all the Designations */

            /*
             * DDOInformationDetail lObjDDOInformation = ddoInfoDAO
             * .getDdoInfo(lStrDdoCode);
             */

            OrgDdoMst lObjOrgDdoMst = ddoInfoDAO.getDdoInformation(lStrDdoCode);
            List instituteType = lObjDcpsCommonDAO.getInstituteType();

            // START added by samadhan

            final String usrName = ddoInfoDAO.getUserName(this.gLngUserId);
            inputMap.put("usrName", usrName);
            // END added by samadhan

            /* If the data of the DDO exists, get the Bank and Branch name */
            if (lObjOrgDdoMst != null)
            {

                // Get Branch name for selected banks
                if (lObjOrgDdoMst.getBankName() != null && !lObjOrgDdoMst.getBankName().equals(""))
                {
                    List lLstBrachNames = lObjDcpsCommonDAO.getBranchNamesWithBsrCodes(Long.valueOf(lObjOrgDdoMst
                            .getBankName()));
                    inputMap.put("BRANCHNAMES", lLstBrachNames);
                    lLstBrachNames = null;
                }

                String lStrAdminDept = lObjLocationDAO.getDeptNameByLocCode(lObjOrgDdoMst.getDeptLocCode(),
                        this.gLngLangId.toString());
                String lStrFieldDept = lObjLocationDAO.getDeptNameByLocCode(lObjOrgDdoMst.getHodLocCode(),
                        this.gLngLangId.toString());
                if (!lStrAdminDept.equals(""))
                {
                    final Long lLngFieldDept = Long.parseLong(lObjOrgDdoMst.getHodLocCode());
                    List lLstDesignation = lObjDcpsCommonDAO.getAllDesignation(lLngFieldDept, this.gLngLangId);
                    inputMap.put("lLstDesignation", lLstDesignation);
                    lLstDesignation = null;
                }
                inputMap.put("AdminDept", lStrAdminDept);
                inputMap.put("FieldDept", lStrFieldDept);
                inputMap.put("DdoDetails", lObjOrgDdoMst);
                inputMap.put("cmbDdoCode", lObjOrgDdoMst.getDdoId());
                lStrAdminDept = null;
                lStrFieldDept = null;
            }

            inputMap.put("instituteType", instituteType);
            inputMap.put("DdoCode", lStrDdoCode);

            inputMap.put("lDtCurDate", lObjDateFormat.format(this.gDtCurDate));
            inputMap.put("BANKNAMES", lLstBankNames);
            lLstBankNames = null;
            lStrDdoCode = null;
            lObjOrgDdoMst = null;

            resObj.setResultValue(inputMap);
            resObj.setViewName("DCPSDDOInformation");

        } catch (final Exception e)
        {
            e.printStackTrace();
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
            gLogger.error("Error In loading DDO Info Page " + e);
        }

        return resObj;
    }

    public ResultObject newEntryForDDODetails(final Map inputMap) throws Exception
    {
        gLogger.info("hiii i m in ddoinfoSeviceImpl to getDDodtls");
        // ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        try
        {
            this.setSessionInfo(inputMap);
            final ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            final Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");

            gLogger.info("hiii i m finding ddo code");
            final String loggedInPost = loginMap.get("loggedInPost").toString();
            final long langId = Long.parseLong(loginMap.get("langId").toString());
            gLogger.info("hiii i m finding logged in post Id" + loggedInPost);
            gLogger.info("hiii i m finding ddo code" + langId);
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            // code to find the logged in DDO Code
            gLogger.info("hiii i m finding ddo code" + locId);
            // long loggedInPostId =
            // Long.parseLong(inputMap.get("primaryPostId").toString());
            // logger.info("hiii i m finding ddo code"+loggedInPostId);
            final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, serv.getSessionFactory());
            final DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
                gLogger.info("hiii i m finding ddo code");
            }
            String ddoCode = null;
            if (ddoMst != null)
            {
                ddoCode = ddoMst.getDdoCode();
            }
            gLogger.info("hiii i m finding ddo code");
            gLogger.info("hii The Logged in DDO Code is " + ddoCode);

            String updatedtxtNameNew = null;
            String updatedtxtFromDateNew = null;
            String updatedtxtTodateNew = null;
            // added by roshan For Token Number Implemetation
            String genTokenNo = null;
            if (StringUtility.getParameter("genTokenNo", request) != null
                    && StringUtility.getParameter("genTokenNo", request) != "")
            {
                genTokenNo = StringUtility.getParameter("genTokenNo", request);
                gLogger.info("hiii genTokenNo is.:::::::" + genTokenNo);
            }
            String genUser = null;
            if (StringUtility.getParameter("genUser", request) != null
                    && StringUtility.getParameter("genUser", request) != "")
            {
                genUser = StringUtility.getParameter("genUser", request);
                gLogger.info("hiii genUser is.:::::::" + genUser);
            }
            String genRandomNo = null;
            if (StringUtility.getParameter("genRandomNo", request) != null
                    && StringUtility.getParameter("genRandomNo", request) != "")
            {
                genRandomNo = StringUtility.getParameter("genRandomNo", request);

                gLogger.info("hiii genRandomNo is.:::::::" + genRandomNo);
            }
            final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            if (genTokenNo.equals(genUser + "newE" + genRandomNo))
            {
                gLogger.info("inside outer if .:::::::");
                // ended by roshan for token Number Impl
                if (StringUtility.getParameter("updatedtxtNameNew", request) != null
                        && StringUtility.getParameter("updatedtxtNameNew", request) != "")
                {
                    gLogger.info("inside inner if .:::::::");
                    updatedtxtNameNew = StringUtility.getParameter("updatedtxtNameNew", request);
                    updatedtxtFromDateNew = StringUtility.getParameter("updatedtxtFromDateNew", request);
                    updatedtxtTodateNew = StringUtility.getParameter("updatedtxtTodateNew", request);
                    gLogger.info("updatedtxtNameNew is " + updatedtxtNameNew);
                    gLogger.info("updatedtxtFromDateNew is " + updatedtxtFromDateNew);
                    gLogger.info("updatedtxtTodateNew is " + updatedtxtTodateNew);

                    // added by samadhan for entry of pk, status, user_id
                    final Long pkforHRPayDdoHistory = IFMSCommonServiceImpl.getNextSeqNum("HR_PAY_DDO_HISTORY",
                            inputMap);
                    final String strPkforHRPayDdoHistory = pkforHRPayDdoHistory.toString();

                    final String strUserId = this.gLngUserId.toString();
                    gLogger.info("User Id strUserId: " + strUserId);
                    final String status = "0";

                    if (updatedtxtFromDateNew != null && updatedtxtFromDateNew != "")
                    {
                        updatedtxtFromDateNew = sdf2.format(sdf1.parse(updatedtxtFromDateNew));
                    }
                    if (updatedtxtTodateNew != null && updatedtxtTodateNew != "")
                    {
                        updatedtxtTodateNew = sdf2.format(sdf1.parse(updatedtxtTodateNew));
                    }

                    lObjDdoInfoDAO.insertNewDetails(strPkforHRPayDdoHistory, ddoCode, updatedtxtNameNew,
                            updatedtxtFromDateNew, updatedtxtTodateNew, status, strUserId);
                }
            }
            long ddoHistoryCount = 0;
            ddoHistoryCount = lObjDdoInfoDAO.getDDOHistory(ddoCode);
            gLogger.info("hi history record count is" + ddoHistoryCount);
            List ddoHistory = null;
            ddoHistory = lObjDdoInfoDAO.getDDoHistoryDetails(ddoCode);
            // code to find the existing DDO Details.
            gLogger.info("logged in user id is" + this.gLngUserId);
            String ExistingDDOName = null;
            ExistingDDOName = lObjDdoInfoDAO.getDetails(this.gLngUserId);
            inputMap.put("ExistingDDODetails", ExistingDDOName);
            String ExistingDDoFromDate = null;
            ExistingDDoFromDate = lObjDdoInfoDAO.getCretaedDate(this.gLngUserId);
            gLogger.info("logged in user created date is " + ExistingDDoFromDate);
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
        }

        catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            gLogger.error("Error in enter New Entry " + e);

        }

        return resultObject;
    }

    // ended byr roshan
    // ended byr roshan

    public ResultObject populateBranchNames(final Map<String, Object> lMapInputMap)
    {

        final ResultObject lObjResultObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        List lLstBranchNames = null;

        try
        {

            /* Sets the Session Information */
            this.setSessionInfo(lMapInputMap);

            /* Initializes the DAO */
            final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());

            /* Gets the bank name from request */
            final Long lLngBankName = Long.parseLong(StringUtility.getParameter("cmbBankName", this.request));

            /*
             * Gets the branch names from the bank name and sends them using
             * AJAX
             */

            if (StringUtility.getParameter("requestFor", this.request).equals("bsrCodes"))
            {
                lLstBranchNames = lObjDcpsCommonDAO.getBranchNamesWithBsrCodes(lLngBankName);
            } else
            {
                lLstBranchNames = lObjDcpsCommonDAO.getBranchNames(lLngBankName);
            }

            String lStrTempResult = null;
            if (lLstBranchNames != null)
            {
                lStrTempResult = new AjaxXmlBuilder().addItems(lLstBranchNames, "desc", "id", true).toString();
            }
            lMapInputMap.put("ajaxKey", lStrTempResult);
            lLstBranchNames = null;
            lObjResultObj.setResultValue(lMapInputMap);
            lObjResultObj.setViewName("ajaxData");
        } catch (final Exception e)
        {
            lObjResultObj.setResultValue(null);
            lObjResultObj.setThrowable(e);
            lObjResultObj.setResultCode(ErrorConstants.ERROR);
            lObjResultObj.setViewName("errorPage");
            gLogger.error(" Error is : " + e, e);
        }
        return lObjResultObj;
    }

    public ResultObject rjctDDOHistory(final Map inputMap) throws Exception
    {

        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        final ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        try
        {

            final String srNo = StringUtility.getParameter("srNo", request);
            final String ddoCode = StringUtility.getParameter("ddoCode", request);
            final String ddoName = StringUtility.getParameter("ddoName", request);
            final String userID = StringUtility.getParameter("userID", request);
            gLogger.info("inside aprvDDOHistory srNo: " + srNo + " ddoCode: " + ddoCode + " ddoName: " + ddoName
                    + " userID: " + userID);

            this.setSessionInfo(inputMap);
            final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());
            final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, this.serv.getSessionFactory());
            final String lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(this.gLngPostId);
            final String ddoType = lObjDdoInfoDAO.getDDOtype(lStrDdoCode);

            // APPROVE:::::update status flag and name in org_user_mst
            lObjDdoInfoDAO.rejectDDOHistory(srNo, userID, ddoName);

            // code to get ddo history
            String ddo = null;
            List ddoHistory = null;
            // code to get ddo history
            if (ddoType.equals("100"))
            {
                gLogger.info("DDO is of level 2!");
                // get level 1 ddo list
                final List level1DDOList = lObjDdoInfoDAO.getLevel1DDOList(lStrDdoCode);
                gLogger.info("size of level 1 ddo list: " + level1DDOList.size());
                // get ddo history
                inputMap.put("ddoCode", lStrDdoCode);
                for (int i = 0; i < level1DDOList.size(); i++)
                {
                    ddo = ddo + "," + level1DDOList.get(i).toString();
                }
            } else if (ddoType.equals("010"))
            {
                gLogger.info("DDO is of level 3!");
                // get level 2 ddo list
                final List level2DDOList = lObjDdoInfoDAO.getLevel2DDOList(lStrDdoCode);
                gLogger.info("size of level 2 ddo list: " + level2DDOList.size());
                // get ddo history
                for (int i = 0; i < level2DDOList.size(); i++)
                {
                    ddo = ddo + "," + level2DDOList.get(i).toString();
                }
            } else if (ddoType.equals("001"))
            {
                gLogger.info("DDO is of level 4!");
                // get level 3 ddo list
                final List level3DDOList = lObjDdoInfoDAO.getLevel3DDOList(lStrDdoCode);
                gLogger.info("size of level 3 ddo list: " + level3DDOList.size());
                // get ddo history
                for (int i = 0; i < level3DDOList.size(); i++)
                {
                    ddo = ddo + "," + level3DDOList.get(i).toString();
                }
            }
            ddoHistory = lObjDdoInfoDAO.getDDoHistoryDetailsForApprove(ddo);
            inputMap.put("ddoHistoryList", ddoHistory);

            resObj.setResultValue(inputMap);
            resObj.setViewName("ApproveDDOHistory");
        } catch (final Exception e)
        {
            e.printStackTrace();
            resObj.setResultValue(null);
            resObj.setThrowable(e);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
            gLogger.error("Error In loading DDO Info Page " + e);
        }
        return resObj;
    }

    /**
     * service method to used to save DDO Information into Databse
     * 
     * @param Map
     *            <String,Object> inputMap
     * @return ResultObject
     */
    public ResultObject saveDDOInformation(final Map inputMap)
    {

        final ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        gLogger.info("logged in user id is at 0 " + this.gLngUserId);
        Boolean lBlFlag;
        Boolean lBlDdoExistsFlag;
        try
        {
            gLogger.info("logged in user id is  " + this.gLngUserId);
            /* Sets the Session Information */
            this.setSessionInfo(inputMap);

            final String UserType = StringUtility.getParameter("UserType", this.request);
            final String lStrDdoId = StringUtility.getParameter("cmbDdoCode", this.request);

            /* Initializes the DAOs and variables */
            gLogger.info("cmbDesignation" + StringUtility.getParameter("cmbDesignation", this.request));
            final DdoInfoDAO ddoInfoDAO = new DdoInfoDAOImpl(OrgDdoMst.class, this.serv.getSessionFactory());
            lBlFlag = false;

            lBlDdoExistsFlag = false;
            final OrgDdoMst lObjDDOInfo = (OrgDdoMst) ddoInfoDAO.read(Long.parseLong(lStrDdoId));

            /* Creates or updates the VO of DDOInformation */

            if (UserType.equals("SRKA"))
            {
                final String lStrAdministrativeDept = StringUtility.getParameter("cmbAdminDept", this.request);
                final String lStrFieldHodDept = StringUtility.getParameter("cmbFieldDept", this.request);
                lObjDDOInfo.setDeptLocCode(lStrAdministrativeDept);
                lObjDDOInfo.setHodLocCode(lStrFieldHodDept);
            } else
            {
                String lStrDdoPersonalName = StringUtility.getParameter("txtDDOName", this.request);
                String lStrDdoDesignation = StringUtility.getParameter("cmbDesignation", this.request);
                final String lStrWefDate = StringUtility.getParameter("txtWEFDate", this.request);
                String lStrTanNo = StringUtility.getParameter("txtTANNo", this.request);
                String lStrItawardcircle = StringUtility.getParameter("txtITWardCircle", this.request);
                String lStrBankName = StringUtility.getParameter("cmbBankName", this.request);
                String lStrBranchName = StringUtility.getParameter("cmbBranchName", this.request);
                String lStrAccountNo = StringUtility.getParameter("txtAccountNo", this.request);
                String lStrRemarks = StringUtility.getParameter("txtRemarks", this.request);
                String lStrIfscCode = StringUtility.getParameter("txtIFSCCode", this.request);
                String instituteType = StringUtility.getParameter("instituteType", this.request);
                Long instituteId = Long.parseLong(instituteType);
                // String lStrDesigName =
                // StringUtility.getParameter("DesigName", request);
                String lStrDesigName;
                if (lStrDdoDesignation != null && !lStrDdoDesignation.equals("")
                        && Long.parseLong(lStrDdoDesignation) == 102714)
                {
                    lStrDesigName = "Headmaster";
                } else
                {
                    lStrDesigName = "Incharge HM";
                }

                Date dtWEFDate = null;

                if (lStrWefDate != null && !"".equals(lStrWefDate.trim()))
                {
                    dtWEFDate = IFMSCommonServiceImpl.getDateFromString(lStrWefDate);
                }

                lObjDDOInfo.setDdoPersonalName(lStrDdoPersonalName);
                lObjDDOInfo.setDesignCode(lStrDdoDesignation);
                lObjDDOInfo.setStartDate(dtWEFDate);
                lObjDDOInfo.setTanNo(lStrTanNo);
                lObjDDOInfo.setItaWardNo(lStrItawardcircle);
                lObjDDOInfo.setBankName(lStrBankName);
                lObjDDOInfo.setBranchName(lStrBranchName);
                lObjDDOInfo.setAccountNo(lStrAccountNo);
                lObjDDOInfo.setRemarks(lStrRemarks);
                lObjDDOInfo.setIfsCode(lStrIfscCode);
                lObjDDOInfo.setDesignName(lStrDesigName);
                lObjDDOInfo.setInstituteType(instituteId);
                gLogger.info("lStrBranchName**************" + lStrBranchName);
                /* For change in designation */
                String lStrDdoOfficeName = lObjDDOInfo.getDdoOffice();
                if (!(lStrDdoOfficeName == null))
                {
                    String lStrDdoName = null;
                    lStrDdoName = lStrDesigName.concat(", ");
                    lStrDdoName = lStrDdoName.concat(lStrDdoOfficeName);
                    lObjDDOInfo.setDdoName(lStrDdoName);

                }

                ddoInfoDAO.updateDdoName(this.gLngPostId, lStrDdoPersonalName);
                // added by roshan
                ddoInfoDAO.updateDdoNameInOrgEmpMst(this.gLngUserId, lStrDdoPersonalName);
                // end by roshan
                lStrDdoPersonalName = null;
                lStrDdoDesignation = null;
                dtWEFDate = null;
                lStrTanNo = null;
                lStrItawardcircle = null;
                lStrBankName = null;
                lStrBranchName = null;
                lStrAccountNo = null;
                lStrRemarks = null;
                lStrIfscCode = null;
                lStrDesigName = null;
                lStrDdoOfficeName = null;

            }
            lObjDDOInfo.setUpdatedBy(this.gLngUserId);
            lObjDDOInfo.setUpdatedByPost(this.gLngPostId);
            lObjDDOInfo.setUpdatedDate(this.gDtCurDate);
            ddoInfoDAO.update(lObjDDOInfo);

            lBlFlag = true;

        } catch (final Exception ex)
        {
            resObj.setResultValue(null);
            resObj.setThrowable(ex);
            resObj.setResultCode(ErrorConstants.ERROR);
            resObj.setViewName("errorPage");
            ex.printStackTrace();
            return resObj;
        }

        /* Generates the XML response and sends the success flag */
        final String lSBStatus = this.getResponseXMLDoc(lBlFlag, lBlDdoExistsFlag).toString();

        final String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

        inputMap.put("ajaxKey", lStrResult);
        resObj.setResultValue(inputMap);
        resObj.setViewName("ajaxData");
        return resObj;

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
            gLogger.error("Error in setSessionInfo of DdoInfoServiceImpl ", e);
            throw e;
        }
    }

    public ResultObject updateDdoDetails(final Map inputMap) throws Exception
    {
        gLogger.info("hiii i m in ddoinfoSeviceImpl to getDDodtls");
        // ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        try
        {
            this.setSessionInfo(inputMap);
            final ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            final Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");

            gLogger.info("hiii i m finding ddo code");
            final String loggedInPost = loginMap.get("loggedInPost").toString();
            final long langId = Long.parseLong(loginMap.get("langId").toString());
            gLogger.info("hiii i m finding logged in post Id" + loggedInPost);
            gLogger.info("hiii i m finding ddo code" + langId);
            final long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
            // code to find the logged in DDO Code
            gLogger.info("hiii i m finding ddo code" + locId);
            // long loggedInPostId =
            // Long.parseLong(inputMap.get("primaryPostId").toString());
            // logger.info("hiii i m finding ddo code"+loggedInPostId);
            final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, serv.getSessionFactory());
            final DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
            final PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
            OrgDdoMst ddoMst = null;
            if (ddoList != null && ddoList.size() > 0)
            {
                ddoMst = ddoList.get(0);
                gLogger.info("hiii i m finding ddo code");
            }
            String ddoCode = null;
            if (ddoMst != null)
            {
                ddoCode = ddoMst.getDdoCode();
            }
            gLogger.info("hiii i m finding ddo code");
            gLogger.info("hii The Logged in DDO Code is " + ddoCode);
            // added by roshan to insert the first entry for a ddo in
            // hr_pay_ddo_history
            // added by roshan to update the details.
            String srNo = null;
            String updatedDdoName = null;
            String updatedFromDate = null;
            String updatedToDate = null;
            final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (StringUtility.getParameter("srNo", request) != null
                    && StringUtility.getParameter("srNo", request) != "")
            {
                srNo = StringUtility.getParameter("srNo", request);
                updatedDdoName = StringUtility.getParameter("updateDdoName", request);
                updatedFromDate = StringUtility.getParameter("updatedtxtFromDate", request);
                updatedToDate = StringUtility.getParameter("updatedtxtTodate", request);
                gLogger.info("srNo is " + srNo);
                gLogger.info("updatedDdoName is " + updatedDdoName);
                gLogger.info("updatedFromDate is " + updatedFromDate);
                gLogger.info("updatedToDate is " + updatedToDate);


                if(updatedFromDate!=null && !updatedFromDate.equals(""))
                    updatedFromDate = sdf2.format(sdf1.parse(updatedFromDate));
                gLogger.info("serviceFromDate is **************" + updatedFromDate);
                if(updatedToDate!=null && !updatedToDate.equals(""))
                    updatedToDate = sdf2.format(sdf1.parse(updatedToDate)); 
                gLogger.info("updatedToDate is **************" + updatedToDate);
                lObjDdoInfoDAO.updateDetails(Long.valueOf(srNo), updatedDdoName, updatedFromDate, updatedToDate);
                //lObjDdoInfoDAO.updateDdoNameInOrgEmpMst(this.gLngUserId, updatedDdoName);
            }

            long ddoHistoryCount = 0;
            ddoHistoryCount = lObjDdoInfoDAO.getDDOHistory(ddoCode);
            gLogger.info("hi history record count is" + ddoHistoryCount);
            List ddoHistory = null;
            ddoHistory = lObjDdoInfoDAO.getDDoHistoryDetails(ddoCode);
            // code to find the existing DDO Details.
            gLogger.info("logged in user id is" + this.gLngUserId);
            String ExistingDDOName = null;
            ExistingDDOName = lObjDdoInfoDAO.getDetails(this.gLngUserId);
            inputMap.put("ExistingDDODetails", ExistingDDOName);
            String ExistingDDoFromDate = null;
            ExistingDDoFromDate = lObjDdoInfoDAO.getCretaedDate(this.gLngUserId);
            gLogger.info("logged in user created date is " + ExistingDDoFromDate);
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
        }

        catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            gLogger.error("Error in loadEmpDtlsDdoWise " + e);

        }

        return resultObject;
    }
}
