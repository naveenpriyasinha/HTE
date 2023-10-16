package com.tcs.sgv.dcps.service;

import java.util.List;
import java.util.Map;

import com.ibm.icu.math.BigDecimal;
import com.tcs.sgv.common.utils.PasswordEncryption;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.math.BigDecimal;
import com.tcs.sgv.acl.valueobject.AclPostroleRlt;
import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.Qualification;
import com.tcs.sgv.common.dao.QualificationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.helper.WorkFlowHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.PasswordEncryption;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.AttachmentHelper;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoBillGroupDAO;
import com.tcs.sgv.dcps.dao.DdoBillGroupDAOImpl;
import com.tcs.sgv.dcps.dao.DdoProfileDAO;
import com.tcs.sgv.dcps.dao.DdoProfileDAOImpl;
import com.tcs.sgv.dcps.dao.LegacyDataEntryDao;
import com.tcs.sgv.dcps.dao.LegacyDataEntryDaoImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAO;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAOImpl;
import com.tcs.sgv.dcps.dao.OfflineContriDAO;
import com.tcs.sgv.dcps.dao.OfflineContriDAOImpl;
import com.tcs.sgv.dcps.valueobject.DcpsCadreMst;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.HstEmp;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDdoAsst;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.fms.valueobject.WfHierachyPostMpg;
import com.tcs.sgv.wf.delegate.WorkFlowDelegate;


public class LegacyDataEntryServiceImpl extends ServiceImpl 
		
{
	
	
	
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");

	/*
	 * Function to save the session specific details
	 */
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
			gDtCurrDt = SessionHelper.getCurDate();
		}
		catch (Exception e)
		{

		}

	}
	
	
	
	
	
	
	
	public ResultObject LegacyDataEntry(Map objectArgs) throws Exception
	{
		Long lLngEmpId = 0l;
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		PasswordEncryption objPasswordEncryption = new PasswordEncryption();
		try
		{
			
			
			gLogger.info("in LegacyDataEntry");
			setSessionInfo(objectArgs);

			LegacyDataEntryDao lObjNewLegDdoDAO = new LegacyDataEntryDaoImpl(MstEmp.class, serv.getSessionFactory());
			String empIds=null;
			String empContri=null;
			String employerContri=null;
			String empInterest=null;
			String employerInterest=null;
			String total=null;
			gLogger.info("in LegacyDataEntry1");
			String genTokenNo=null;
	
			if((StringUtility.getParameter("empIds", request)!=null)&&(StringUtility.getParameter("empIds", request)!="")){
				//if (genTokenNo.equals(encToken)){
				gLogger.info("in LegacyDataEntry11");
				empIds= StringUtility.getParameter("empIds", request);
				empContri= StringUtility.getParameter("empContri", request);
				gLogger.info("empContri"+empContri);
				employerContri=StringUtility.getParameter("employerContri", request);
				empInterest=StringUtility.getParameter("empInterest", request);
				employerInterest=StringUtility.getParameter("employerInterest", request);
				total=StringUtility.getParameter("total", request);
					
				
				
				String[] lstrempIds = empIds.split("~");
				String[] lstrempContri = empContri.split("~");
				String[] lstremployerContri = employerContri.split("~");
				String[] lstrempInterest = empInterest.split("~");
				String[] lstremployerInterest= employerInterest.split("~");
				String[] lstrtotal = total.split("~");
				
				String[] lstrEmpIDsarr = new String[lstrempIds.length];
				String[] lstrempContriarr = new String[lstrempContri.length];
				String[] lstremployerContriarr= new String[lstremployerContri.length];
				String[] lstrempInterestarr= new String[lstrempInterest.length];
				String[] lstremployerInterestarr= new String[lstremployerInterest.length];
				String[] lstrtotalarr= new String[lstrtotal.length];
				gLogger.info("in LegacyDataEntry111");
				
				for (Integer lInt = 0; lInt < lstrempIds.length; lInt++)
				{
					if (lstrempIds[lInt] != null && !"".equals(lstrempIds[lInt]))
					{
						lstrEmpIDsarr[lInt] = lstrempIds[lInt];
						lstrempContriarr[lInt] = lstrempContri[lInt];
						lstremployerContriarr[lInt]=lstremployerContri[lInt];
						lstrempInterestarr[lInt]=lstrempInterest[lInt];
						lstremployerInterestarr[lInt]=lstremployerInterest[lInt];
						lstrtotalarr[lInt]=lstrtotal[lInt];
							
						gLogger.info("hii********** "+lstrEmpIDsarr[lInt]);
						gLogger.info("hii********** "+lstrempContriarr[lInt]);
						gLogger.info("hii********** "+lstremployerContriarr[lInt]);
						gLogger.info("hii********** "+lstrempInterestarr[lInt]);
						gLogger.info("hii********** "+lstremployerInterestarr[lInt]);
						gLogger.info("hii********** "+lstrtotalarr[lInt]);
						
					}
				}

				for (Integer lInt = 0; lInt < lstrempIds.length; lInt++)
				{
					
					lObjNewLegDdoDAO.saveData(lstrEmpIDsarr[lInt],lstrempContriarr[lInt],lstremployerContriarr[lInt],lstrempInterestarr[lInt],lstremployerInterestarr[lInt],lstrtotalarr[lInt],objectArgs);

				}
			//}
			}
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			gLogger.info("hi "+lStrDdoCode);
			List empList = lObjNewLegDdoDAO.LegacyDataEntry(lStrDdoCode);
			int empsize=empList.size();
			gLogger.info("The size of given list is:"+empList.size());
			objectArgs.put("EditForm", "N");
			objectArgs.put("empList", empList);
			objectArgs.put("ddoCode", lStrDdoCode);
			objectArgs.put("Empsize", empsize);

			//added by roshan for performance tuning
			empList=null;
			//ended by roshan for performance tuning

			resObj.setResultValue(objectArgs);
			resObj.setViewName("LegacyDataEntry");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	
	
	public ResultObject approveLegacyDataEntry(Map objectArgs) throws Exception
	{
		Long lLngEmpId = 0l;
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		PasswordEncryption objPasswordEncryption = new PasswordEncryption();
		try
		{
			
			
			gLogger.info("in approveLegacyDataEntry");
			setSessionInfo(objectArgs);

			LegacyDataEntryDao lObjNewLegDdoDAO = new LegacyDataEntryDaoImpl(MstEmp.class, serv.getSessionFactory());
			String empIds=null;
			gLogger.info("in approveLegacyDataEntry1");
			String genTokenNo=null;
	
			if((StringUtility.getParameter("empIds", request)!=null)&&(StringUtility.getParameter("empIds", request)!="")){
				//if (genTokenNo.equals(encToken)){
				gLogger.info("in LegacyDataEntry11");
				empIds= StringUtility.getParameter("empIds", request);
			
				String[] lstrempIds = empIds.split("~");
				
				String[] lstrEmpIDsarr = new String[lstrempIds.length];
				
				gLogger.info("in LegacyDataEntry111");
				
				for (Integer lInt = 0; lInt < lstrempIds.length; lInt++)
				{
					if (lstrempIds[lInt] != null && !"".equals(lstrempIds[lInt]))
					{
						lstrEmpIDsarr[lInt] = lstrempIds[lInt];
						
					}
				}

				for (Integer lInt = 0; lInt < lstrempIds.length; lInt++)
				{
					
					lObjNewLegDdoDAO.approveSavedData(lstrEmpIDsarr[lInt]);
					lObjNewLegDdoDAO.rejectSavedData(lstrEmpIDsarr[lInt]);

				}
			//}
			}
			
			String lStrDdoCode = lObjNewLegDdoDAO.getDdoCode(gLngPostId);
			gLogger.info("hi123"+lStrDdoCode);
			List empList = lObjNewLegDdoDAO.viewLegacyDataEntry(lStrDdoCode);
			int empsize=empList.size();
			gLogger.info("The size of given list is:"+empList.size());
			objectArgs.put("EditForm", "N");
			objectArgs.put("empList", empList);
			objectArgs.put("ddoCode", lStrDdoCode);
			objectArgs.put("Empsize", empsize);

			//added by roshan for performance tuning
			empList=null;
			//ended by roshan for performance tuning

			resObj.setResultValue(objectArgs);
			resObj.setViewName("approveLegacyDataEntry");

		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
