package com.tcs.sgv.dcps.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class DdoInfoServiceForMdp extends ServiceImpl 
{
	private final Log logger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	//private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	//private Date gDtCurDate = null; /* CURRENT DATE */

	//private HttpServletRequest request = null; /* REQUEST OBJECT */

	//private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	/* Global Variable for PostId */
	//Long gLngPostId = null;

	/* Global Variable for UserId */
	//Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;
	private void setSessionInfo(Map inputMap)
	{
		try
		{
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
		}
		catch (Exception e)
		{

		}
	}
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
    	this.setSessionInfo(inputMap);
        final ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");


        try
        {
         //   this.setSessionInfo(inputMap);

            final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());
            final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, this.serv.getSessionFactory());
            final List lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForMdp();

            //gLogger.info("PostId: " + this.gLngPostId + " DdoCode: " + lStrDdoCode);

            String ddo = null;
            List ddoHistory = null;
            String lStrDdoCode1=null;
            for (int i = 0; i < lStrDdoCode.size(); i++)
            {
         
            	lStrDdoCode1 = lStrDdoCode1 + "," + lStrDdoCode.get(i).toString();
            }
          
                ddoHistory = lObjDdoInfoDAO.getDDoHistoryFromSecondLevel(lStrDdoCode1);
                inputMap.put("ddoHistoryList", ddoHistory);


            resObj.setResultValue(inputMap);
            resObj.setViewName("ApproveDDOHistoryForMdp");

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
    
    public ResultObject aprvDDOHistoryForMdp(final Map inputMap) throws Exception
    {
    	
    	 final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
         final ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
         final String srNo = StringUtility.getParameter("srNo", request);
         final String ddoCode = StringUtility.getParameter("ddoCode", request);
         final String ddoName = StringUtility.getParameter("ddoName", request);
         final String userID = StringUtility.getParameter("userID", request);
         gLogger.info("inside aprvDDOHistory srNo: " + srNo + " ddoCode: " + ddoCode + " ddoName: " + ddoName
                 + " userID: " + userID);

         this.setSessionInfo(inputMap);
         final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());
         final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, this.serv.getSessionFactory());
         final List lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForMdp();
         String ddo = null;
         List ddoHistory = null;
         String lStrDdoCode1=null;
         for (int i = 0; i < lStrDdoCode.size(); i++)
         {
      
         	lStrDdoCode1 = lStrDdoCode1 + "," + lStrDdoCode.get(i).toString();
         }
       
             ddoHistory = lObjDdoInfoDAO.getDDoHistoryFromSecondLevel(lStrDdoCode1);
             inputMap.put("ddoHistoryList", ddoHistory);
             
             lObjDdoInfoDAO.updateStatus(srNo, userID, ddoName);
             lObjDdoInfoDAO.updateDDOName(srNo, userID, ddoName);

             // START added by samadhan to update ddo name in ORG_DDO_MST
             lObjDdoInfoDAO.updateDDONameORGDDOMST(ddoName, ddoCode);

             resObj.setResultValue(inputMap);
             resObj.setViewName("ApproveDDOHistoryForMdp");


		return resObj;
    	
    }
    public ResultObject rjctDDOHistoryForMdp(final Map inputMap) throws Exception
    {

        final HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        final ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        final String srNo = StringUtility.getParameter("srNo", request);
        final String ddoCode = StringUtility.getParameter("ddoCode", request);
        final String ddoName = StringUtility.getParameter("ddoName", request);
        final String userID = StringUtility.getParameter("userID", request);
        gLogger.info("inside aprvDDOHistory srNo: " + srNo + " ddoCode: " + ddoCode + " ddoName: " + ddoName
                + " userID: " + userID);

        this.setSessionInfo(inputMap);
        final DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());
        final DdoInfoDAO lObjDdoInfoDAO = new DdoInfoDAOImpl(null, this.serv.getSessionFactory());
        final List lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForMdp();
        lObjDdoInfoDAO.rejectDDOHistory(srNo, userID, ddoName);
        String ddo = null;
        List ddoHistory = null;
        String lStrDdoCode1=null;
        for (int i = 0; i < lStrDdoCode.size(); i++)
        {
     
        	lStrDdoCode1 = lStrDdoCode1 + "," + lStrDdoCode.get(i).toString();
        }
      
            ddoHistory = lObjDdoInfoDAO.getDDoHistoryFromSecondLevel(lStrDdoCode1);
            inputMap.put("ddoHistoryList", ddoHistory);
            
          //  lObjDdoInfoDAO.updateStatus(srNo, userID, ddoName);
           // lObjDdoInfoDAO.updateDDOName(srNo, userID, ddoName);

            // START added by samadhan to update ddo name in ORG_DDO_MST
           // lObjDdoInfoDAO.updateDDONameORGDDOMST(ddoName, ddoCode);

            resObj.setResultValue(inputMap);
            resObj.setViewName("ApproveDDOHistoryForMdp");

        
		return resObj;
    }
}