/**
 * <This class insert/update the application>
 *
 * @class name  	: <ZpAdminOfficeServiceImpl >
 * @author		: <Code Skeleton/Generator Tool - Developed By Durgesh (486841)/Zeal (479123)>
 * @version		: <1.0>
 */
package  com.tcs.sgv.eis.zp.ZpReportingDDO.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstScheme;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.zp.ZpReportingDDO.dao.ZpReportingDAO;
import com.tcs.sgv.eis.zp.ZpReportingDDO.dao.ZpReportingDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDepartmentMst.dao.ZpDepartmentDAO;
import com.tcs.sgv.eis.zp.zpDepartmentMst.dao.ZpDepartmentDAOImpl;
import com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst;
import com.tcs.sgv.eis.zp.zpDistrictOffice.dao.ZpDistrictOfficeDAO;
import com.tcs.sgv.eis.zp.zpDistrictOffice.dao.ZpDistrictOfficeDAOImpl;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;

public class ZpReportingDDOServiceImpl extends ServiceImpl implements ZpReportingDDOService
{
	private static Logger logger = Logger.getLogger( ZpReportingDDOServiceImpl.class );
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/eis/eisLables_en_US");
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private final Date todayDate = new Date();
	/* Global Variable for PostId */
	private Long POST_ID = null;
	/* Global Variable for UserId */
	private Long USER_ID = null;
	/* Global Variable for LangId */
	private Long LANG_ID = null;
	/* Global Variable for LocationId */
	private String LOC_ID = "";
	//private String MESSAGECODE = "56";
	


	
	public ResultObject loadReportingDDOScreen(Map objectArgs) throws Exception
	{
		
		logger.info("Entering into loadData of loadReportingDDOScreen,by ketan");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId =1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
	    langId = StringUtility.convertToLong(loginMap.get("langId").toString());
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
		ZpReportingDAO ZpReportingDAOImpl = new ZpReportingDAOImpl(ZpAdminOfficeMst.class,serv.getSessionFactory());
         
		int ddoListSize=0;
		List schemedatalstone= ZpReportingDAOImpl.getSchemeCodeByPost(postId);
		List ddoDtls= ZpReportingDAOImpl.getSubDDOByPost(postId);
		ddoListSize = ddoDtls.size();
        logger.info("schemedatalstone::::"+schemedatalstone.size());
        logger.info("Size of ddoListSize::::"+ddoListSize);
        objectArgs.put("ddoListSize", ddoListSize);
        objectArgs.put("ddoDtls", ddoDtls);
        objectArgs.put("schemedatalstone", schemedatalstone);
		objectArgs.put("MonthlookUpList", MonthlookUpList);
		objectArgs.put("YearLookUpList", YearLookUpList);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ZpReportingDDO");
	    return objRes;
		
	}

	public ResultObject getReportingSummaryPageDtls(Map objectArgs)
	{
		logger.info("getDepartmentMstDtls IN , by ketan..");						
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
   		
		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            ZpDepartmentDAO zpDepartmentDAO =new ZpDepartmentDAOImpl(ZpDepartmentMst.class,serv.getSessionFactory());
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	            langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			    long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			    //String ddoCode = StringUtility.getParameter("ddoList",request);
			    Long Month = Long.valueOf(!StringUtility.getParameter("selmonth",request).equalsIgnoreCase("") ? StringUtility.getParameter("selmonth",request) : "0");
			    Long Year = Long.valueOf(!StringUtility.getParameter("selyear",request).equalsIgnoreCase("") ? StringUtility.getParameter("selyear",request) : "0");
			    String schemecode = !StringUtility.getParameter("schemecode",request).equalsIgnoreCase("") ? StringUtility.getParameter("schemecode",request) : "0";
			    //Added by saurabh for subscheme
			    String subSchemecode = (!StringUtility.getParameter("cmbSubSchemeName",request).equalsIgnoreCase("") ? StringUtility.getParameter("cmbSubSchemeName",request) : "-");
			    String MonthSTR = !StringUtility.getParameter("selmonth",request).equalsIgnoreCase("") ? StringUtility.getParameter("selmonth",request) : "";
			    String[] ddoCodeArray  = request.getParameterValues("ddoList");
			    String ddoCode="";
			    if (ddoCodeArray != null) 
			    {
			       for (int i = 0; i < ddoCodeArray.length; i++) 
			       {
			          if(i==0)
			        	  ddoCode=ddoCodeArray[i];
			          else
			        	  ddoCode=ddoCode+","+ddoCodeArray[i];
			       }
			    }
			    logger.info("MonthSTR:::"+MonthSTR);
				logger.info("Month:::"+Month);
				logger.info("Year:::"+Year);
				logger.info("subscheme:::"+schemecode);
				logger.info("subSchemecode---------"+subSchemecode);
			    Map map = new HashMap();
	            map=objectArgs;
			    logger.info("ddocode::"+ddoCode);
			   
			    logger.info("userId::"+userId);
			    logger.info("dbId::"+dbId);
			     
			    ZpReportingDAO ZpReportingDAOImpl = new ZpReportingDAOImpl(ZpAdminOfficeMst.class,serv.getSessionFactory());
			    
			    List DDOLST = ZpReportingDAOImpl.getReptDDOBillDtls(ddoCode, Month, Year,schemecode,subSchemecode);
			    if(DDOLST!=null && DDOLST.size() > 0){
			    	logger.info("IFFFFFF DDOLST::::"+DDOLST.size());
			    	 map.put("DDOLST",DDOLST);
			    }
			    logger.info("DDOLST::::"+DDOLST.size());
	           String paybillId="0";
				if(DDOLST != null && DDOLST.size() > 0){
					  Iterator IT = DDOLST.iterator();
					  
					    Object[] lObj = null;
					  while(IT.hasNext()){
						  lObj = (Object[]) IT.next();
						  String temp=lObj[5].toString();
						   paybillId=paybillId+","+temp;
						  
						 
						  				  
					  }
				  }
			   
			    objectArgs.put("paybillId", paybillId);
			    objectArgs.put("Month", MonthSTR);
			    objectArgs.put("Year", Year);
			    objectArgs.put("schemecode", schemecode);
			    objectArgs.put("subSchemecode", subSchemecode);
			    
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	            resultObject.setViewName("ZpReportingDDOSummary");
	           
		}
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...getReportingSummaryPageDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
		
		}
	
		return resultObject;
	}

	
	public ResultObject getAdminOfficetypecodeCountDtls(Map objectArgs)
	{
		System.out.println("dgsgdgsgsgsg::::Sgsgsgsg:;SGsgs;sgsgs");
		logger.info("getAdminOfficetypecodeCountDtls IN Dept Data , by ketan..");						
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
   		
		try
		{       logger.info("getAdminOfficetypecodeCountDtls IN Dept Data , by ketan..");	
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            ZpDistrictOfficeDAO zpdistrictOfficeDAO =new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class,serv.getSessionFactory());
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	            String strLoadMethodCall = StringUtility.getParameter("callloadMethod", request);
	            ZpDistrictOfficeDAO adminofficelst = new ZpDistrictOfficeDAOImpl(ZpAdminOfficeMst.class,serv.getSessionFactory());
	    		ZpDistrictOfficeDAO DstrictOfficelst = new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class,serv.getSessionFactory());
	            List zpadminOffice = null; 
	    		Long distId = Long.valueOf(!StringUtility.getParameter("distId",request).equalsIgnoreCase("") ? StringUtility.getParameter("distId",request) : "0");
	    		String editFlag = !StringUtility.getParameter("edit",request).equalsIgnoreCase("") ? StringUtility.getParameter("edit",request) : "";
	    		logger.info("distId::"+distId);
	    		logger.info("editFlag::"+editFlag);
	    		
	    		ZpDistrictOfficeMst zpDistOfficeedit = (ZpDistrictOfficeMst)DstrictOfficelst.getDistOfficeDtls(distId);
	    		
	    		zpadminOffice = adminofficelst.getAdminOffice();
	    		logger.info("zpadminOffice::"+zpadminOffice.size());
	    		logger.info("zpadminOffice::"+zpadminOffice.contains(2));
	            if(strLoadMethodCall.trim().equalsIgnoreCase("")){
	            	strLoadMethodCall = "Y";
	            }else{
	            	strLoadMethodCall = "N";
	            }
	            
	            Long seladminDeptId =null;
	            Long lLstAdminOfficetypecode = null;
	            String lLongAdminOffice = null;
	            
				ZpDepartmentDAO zpDepartmentDAO = null;
				zpDepartmentDAO = new ZpDepartmentDAOImpl(ZpDepartmentMst.class, serv.getSessionFactory());
				if (!StringUtility.getParameter("seladminDeptId", request).equalsIgnoreCase("") && StringUtility.getParameter("seladminDeptId", request) != null) {
					seladminDeptId = Long.valueOf(StringUtility.getParameter("seladminDeptId", request));
					logger.info("seladminDeptId"+seladminDeptId);
					lLstAdminOfficetypecode =zpDepartmentDAO.getCountAdminOfficetypeCode(seladminDeptId);
					lLstAdminOfficetypecode = lLstAdminOfficetypecode + 1l;
					logger.info("lLstAdminOfficetypecode"+lLstAdminOfficetypecode);
					lLongAdminOffice = lLstAdminOfficetypecode.toString();
					if(lLongAdminOffice.length()==1){
						lLongAdminOffice = "0"+lLongAdminOffice;
						logger.info("lLongAdminOffice:::::"+lLongAdminOffice);
						objectArgs.put("lLongAdminOffice",lLongAdminOffice);
					}
					
					objectArgs.put("lLongAdminOffice",lLongAdminOffice);
				
					
				}

				Map map = new HashMap();
	            map=objectArgs;
	            objectArgs.put("zpadminOffice", zpadminOffice);
	            //objectArgs.put("lLongAdminOffice",lLongAdminOffice);
	            objectArgs.put("callloadMethod", strLoadMethodCall);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	            resultObject.setViewName("DepartmentMst");
	            
	           
		}
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...insertDistrictMPGDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
		
		}
	
		return resultObject;
	}

	
	
}
