package com.tcs.sgv.common.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.tcs.sgv.billproc.common.service.BillProcConstants;
import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.BptmCommonServicesDAO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.wf.valueobject.InboxSearchVo;

public class IFMSCommonServiceImpl  extends ServiceImpl implements IFMSCommonService{
	
	static Log logger = LogFactory.getLog(BptmCommonServiceImpl.class);
	
	private static SimpleDateFormat DtToStrFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public ResultObject seeUserManual(Map inputMap){		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);			
		resultObject.setViewName("ShowDocument");
		resultObject.setResultValue(inputMap);	
		return resultObject;
	}
	
	/**
	 * This method will generate list of lookup values based on lookup name.
	 * 
	 * @param String : lStrLookupName
	 * @param Long : lLngLangId
	 * @param Map : inputMap
	 * @return List
	 * 
	 */

	public static List getLookupValues(String lStrLookupName, Long lLngLangId, Map inputMap) throws Exception {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try{
			ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
			/*CmnLookupMstDAOImpl lObjCmnLookupDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			return lObjCmnLookupDAOImpl.getAllChildrenByLookUpNameAndLang(lStrLookupName, lLngLangId);*/
			BptmCommonServicesDAOImpl bptmCommonServicesDAOImpl = new BptmCommonServicesDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			return null; 
			//bptmCommonServicesDAOImpl.getAllChildrenByLookUpNameAndLang(lStrLookupName, lLngLangId);
		}catch (Exception ex) {
			setErrorProperties(logger, objRes, ex, "Error occured while getting lookup values...");
			throw ex;
		}
		
	}
	
	/**
	 * This method will generate a sequence number in an auto-incremental mode.
	 * 
	 * @param String : tableName
	 * @param Map : inputMap
	 * @return Long
	 */
	public synchronized static Long getNextSeqNum(String lStrTableName, Map inputMap) throws Exception{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			if (objRes != null) {
				return (long)IDGenerateDelegate.getNextId(lStrTableName, inputMap);
			}
		} catch (Exception ex) {
			setErrorProperties(logger, objRes, ex, "Error occured while getting next sequence number...");
			throw ex;
			
		}
		return (long) -1;
	}
	
	/**
	 * This method will return lookup id from lookup name
	 * 
	 * @param String : lookupName
	 * @param Map : inputMap
	 * @return String
	 */
	public static String getLookupIdFromName(String lookupName, Map inputMap) throws Exception{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		String lStrLookupId = "";
		try {
			ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
			Long langId = SessionHelper.getLangId(inputMap);
			BptmCommonServicesDAO lBptmObj = new BptmCommonServicesDAOImpl(TrnBillRegister.class, serv.getSessionFactory());
			lStrLookupId = lBptmObj.getLookupIdFromName(lookupName, langId);
		} catch (Exception ex) {
			setErrorProperties(logger, objRes, ex, "Error occured while getting lookup id from lookup name...");
			throw ex;
			
		}
		return lStrLookupId;
	}
	
	public static void setErrorProperties(Log lLogger, ResultObject objRes, Exception ex,  String lStrErrorLog){
		
		objRes.setResultValue(null);
		objRes.setThrowable(ex);
		objRes.setResultCode(ErrorConstants.ERROR);
		objRes.setViewName("errorPage");
		lLogger.error(lStrErrorLog + ex, ex);
		
	}
	public static Map<Object,Object> getDisplayPara(HttpServletRequest request)
    {
    	Map<Object,Object> returnMap = new HashMap<Object,Object>();
    	
    	String sOrderColumn = request.getParameter( new ParamEncoder("vo").encodeParameterName(TableTagParameters.PARAMETER_SORT));
    	String sPageNo = request.getParameter( new ParamEncoder("vo").encodeParameterName(TableTagParameters.PARAMETER_PAGE));
    	String sOrder = request.getParameter( new ParamEncoder("vo").encodeParameterName(TableTagParameters.PARAMETER_ORDER));
    	
    	
    	if(sOrderColumn != null)
    		returnMap.put(Constants.KEY_SORT_PARA,Integer.valueOf(sOrderColumn));
    	if(sPageNo !=null)
    		returnMap.put(Constants.KEY_PAGE_NO,Integer.valueOf(sPageNo));
    	else
    		returnMap.put(Constants.KEY_PAGE_NO,Integer.valueOf(1));
    	
    	if(sOrder !=null)
    	{
    		if(sOrder.equalsIgnoreCase("1"))
    			returnMap.put(Constants.KEY_SORT_ORDER,"asc");
    		else
    			returnMap.put(Constants.KEY_SORT_ORDER,"desc");
    	}
    	return returnMap;
    }
	
	public static Map<Object,Object> getDisplayParaRcpt(HttpServletRequest request)
    {
    	Map<Object,Object> returnMap = new HashMap<Object,Object>();
    	
    	String sOrderColumn = request.getParameter( new ParamEncoder("vo").encodeParameterName(TableTagParameters.PARAMETER_SORT));
    	String sPageNo = request.getParameter( new ParamEncoder("vo").encodeParameterName(TableTagParameters.PARAMETER_PAGE));
    	String sOrder = request.getParameter( new ParamEncoder("vo").encodeParameterName(TableTagParameters.PARAMETER_ORDER));
    	
    	
    	if(sOrderColumn != null)
    		returnMap.put(Constants.KEY_SORT_PARA,Integer.valueOf(sOrderColumn));
    	if(sPageNo !=null)
    		returnMap.put(Constants.KEY_PAGE_NO,Integer.valueOf(sPageNo));
    	else
    		returnMap.put(Constants.KEY_PAGE_NO,Integer.valueOf(1));
    	
    	if(sOrder !=null && sOrder.equalsIgnoreCase("1"))
    		returnMap.put(Constants.KEY_SORT_ORDER,"asc");
    	else
    		returnMap.put(Constants.KEY_SORT_ORDER,"desc");
	
    	return returnMap;
    }
	/**
	 * Method that returns the page number.
	 * 
	 * @param request
	 * @param key
	 * @return Integer
	 */
	public static Integer getPageNumber(HttpServletRequest request, String key){
		
		if(request.getParameter( new ParamEncoder(key).encodeParameterName(TableTagParameters.PARAMETER_PAGE)) != null)
			return Integer.parseInt(request.getParameter(new ParamEncoder(key).encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
		else
			return 1;
		
	}
	
	/**
	 * Method that returns the column index on which sorting requires
	 * 
	 * @param request
	 * @param key
	 * @return Integer
	 */
	public static Integer getSortIndex(HttpServletRequest request, String key){
		
		if(request.getParameter( new ParamEncoder(key).encodeParameterName(TableTagParameters.PARAMETER_SORT)) != null)
			return Integer.parseInt(request.getParameter(new ParamEncoder(key).encodeParameterName(TableTagParameters.PARAMETER_SORT)));
		else
			return -1;
		
	}
	
	/**
	 * Method that returns the order type for sorting. 1 - asc, 2- desc
	 * 
	 * @param request
	 * @param key
	 * @return Integer
	 */
	public static Integer getOrderType(HttpServletRequest request, String key){
		
		if(request.getParameter( new ParamEncoder(key).encodeParameterName(TableTagParameters.PARAMETER_ORDER)) != null)
			return Integer.parseInt(request.getParameter(new ParamEncoder(key).encodeParameterName(TableTagParameters.PARAMETER_ORDER)));
		else
			return 1;
		
	}

	/**
	 * Returns the date object from string (String format : 'DD/MM/YYYY');
	 * @param String : Date
	 * @return Date
	 */
	public static Date getDateFromString(String lStrDate) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(lStrDate);
		
	}
	/**
	 * Returns the String object from Date (String format : 'DD/MM/YYYY');
	 * @param String : Date
	 * @return Date
	 */
	public static String getStringFromDate(Date lObjDate) throws ParseException {
		
		if(lObjDate != null)
			return DtToStrFormatter.format(lObjDate);

		return null;		
	}
	
	/**
	 * Returns the formatted date object with minimum hour, minute and second
	 * @param String : Date
	 * @return Date
	 */
	public static Date getStartDateTime(Date lDate){
				
		Calendar lCal = Calendar.getInstance();
		
		lCal.setTime(lDate);
		lCal.set(Calendar.HOUR, 0);
		lCal.set(Calendar.MINUTE, 0);
		lCal.set(Calendar.SECOND, 0);
		
		return lCal.getTime();
	}
	
	/**
	 * Returns the formatted date object with maximum hour, minute and second
	 * @param String : Date
	 * @return Date
	 */
	public static Date getEndDateTime(Date lDate){
		
		Calendar lCal = Calendar.getInstance();		
		
		lCal.setTime(lDate);
		lCal.set(Calendar.HOUR, 23);
		lCal.set(Calendar.MINUTE, 59);
		lCal.set(Calendar.SECOND, 59);
				
		return lCal.getTime();
		
	}
	
	/**
	 * Method to get padded string with total length and char to be padded as given in arguments.
	 * 
	 * @param inputStr 		: Input String
	 * @param requiredLen 	: Total length of the output string
	 * @param charToPadd 	: Character to pad
	 * @param paddingFlag 	: true - Left Padding, false - Right Padding
	 * @return outStr 		: Padded String
	 */
	public static String getPaddedString(String inputStr, int requiredLen, char charToPadd, boolean paddingFlag) {
		StringBuffer outStr = new StringBuffer(inputStr);
		int strLength = outStr.length();
		if (requiredLen > 0 && requiredLen > strLength) {
			for (int i = 0; i <= requiredLen; i++) {
				if (paddingFlag) {
					if (i < requiredLen - strLength)
						outStr.insert(0, charToPadd);
				} else {
					if (i > strLength)
						outStr.append(charToPadd);
				}
			}
		}
		return outStr.toString();
	}
	
	public static InboxSearchVo getInboxSearchVo(String gStrPostId, String gStrLangId) throws Exception
    {
		InboxSearchVo inboxSearchVo = new InboxSearchVo();
		List lLstPostId = new ArrayList();
		List lLstDocId = new ArrayList();
		
		try {
			lLstPostId.add(gStrPostId);
			inboxSearchVo.setLstPost(lLstPostId);
			
			/*lLstDocId.add(DBConstants.WF_DocId_PhysicalBill);
			lLstDocId.add(DBConstants.WF_DocId_OnlineBill);
			lLstDocId.add(DBConstants.WF_DocId_PensionBill);*/
			inboxSearchVo.setDocLst(lLstDocId);
			
			inboxSearchVo.setLangId(gStrLangId);			
			inboxSearchVo.setExecuteFlag(Boolean.FALSE);
		} catch (Exception e) {
			logger.error("Error while creating InboxSearchVo. Error is ", e);
			throw e;
		}
		
		return inboxSearchVo;
    }
	
	public static String getDocId(TrnBillRegister lObjTrnBillRegister) throws Exception {

		String lStrDocId = null;

		try {
			/*if (lObjTrnBillRegister.getPhyBill() == DBConstants.CMN_PhyBillType) {
				lStrDocId = DBConstants.WF_DocId_PhysicalBill;
			} else if (lObjTrnBillRegister.getPhyBill() == DBConstants.CMN_PensionBillType) {
				lStrDocId = DBConstants.WF_DocId_PensionBill;
			} else {
				lStrDocId = DBConstants.WF_DocId_OnlineBill;
			}*/
		} catch (Exception e) {
			logger.error("Error while executing getDocId. Error is ", e);
			throw e;
		}

		return lStrDocId;
	}
}