/**
 * <This class insert/update the application>
 *
 * @class name  	: <ZpAdminOfficeServiceImpl >
 * @author		: <Code Skeleton/Generator Tool - Developed By Durgesh (486841)/Zeal (479123)>
 * @version		: <1.0>
 */
package  com.tcs.sgv.eis.zp.zpDepartmentMst.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcs.sgv.common.dao.CmnDistrictMstDAO;
import com.tcs.sgv.common.dao.CmnDistrictMstDAOImpl;
import com.tcs.sgv.common.dao.Qualification;
import com.tcs.sgv.common.dao.QualificationDAOImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDepartmentMst.dao.ZpDepartmentDAO;
import com.tcs.sgv.eis.zp.zpDepartmentMst.dao.ZpDepartmentDAOImpl;
import com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst;
import com.tcs.sgv.eis.zp.zpDistrictOffice.dao.ZpDistrictOfficeDAO;
import com.tcs.sgv.eis.zp.zpDistrictOffice.dao.ZpDistrictOfficeDAOImpl;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;

public class ZpDepartmentMstServiceImpl extends ServiceImpl implements ZpDepartmentMstService
{
	private static Logger logger = Logger.getLogger( ZpDepartmentMstServiceImpl.class );
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/eis/eisLables_en_US");
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private final Date todayDate = new Date();
	/* Global Variable for PostId */
	private Long POST_ID = null;
	/* Global Variable for UserId */
	/* Global Variable for LangId */
	private Long LANG_ID = null;
	/* Global Variable for LocationId */
	private String LOC_ID = "";
	//private String MESSAGECODE = "56";
	
	public ResultObject saveZpDepartmentMstDtls(Map objectArgs) throws Exception//sunitha
	{
		logger.info("Entering into saveZpDepartmentMstDtls of saveZpDepartmentMstDtls");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");

		ZpDepartmentMst zpDepartmentMstVO = new ZpDepartmentMst();

		
		String seladminDept = (!StringUtility.getParameter("seladminDept",request).equalsIgnoreCase("") ? StringUtility.getParameter("seladminDept",request) : "0");
		String txtdeptName = !StringUtility.getParameter("txtdeptName",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtdeptName",request) : "";
		String txtCode = (!StringUtility.getParameter("txtCode",request).equalsIgnoreCase("") ? StringUtility.getParameter("txtCode",request) : "0");
		//added by sunitha
		String txtqualification=(!StringUtility.getParameter("qualification",request).equalsIgnoreCase("") ? StringUtility.getParameter("qualification",request) : "");//sunitha
		
		logger.info("seladminDept:::"+seladminDept);
		logger.info("seladminDept:::"+seladminDept);
		logger.info("txtdeptName:::"+txtdeptName);
		logger.info("txtCode:::"+txtCode);
		logger.info("txtqualification:::"+txtqualification);
		
		Long deptId = Long.valueOf(!StringUtility.getParameter("deptId",request).equalsIgnoreCase("") ? StringUtility.getParameter("deptId",request) : "0");
		String editFlag = !StringUtility.getParameter("edit",request).equalsIgnoreCase("") ? StringUtility.getParameter("edit",request) : "";
		logger.info("deptId::"+deptId);
		logger.info("editFlag::"+editFlag);
		
		String updateFlag = (String) objectArgs.get("updateFlag");
		Map redirectMap = new HashMap();
		redirectMap.put("actionFlag", "getDisplayMessage");

      if(editFlag != null && editFlag.equals("Y"))
        { 
    	    logger.info("in edit mode::::::");
       	    ZpDepartmentDAO zpDepartmentDAOImpl = new ZpDepartmentDAOImpl(ZpDepartmentMst.class,serviceLocator.getSessionFactory());
       	    ZpDepartmentDAO Departmentlst = new ZpDepartmentDAOImpl(ZpDepartmentMst.class,serviceLocator.getSessionFactory());
       	    ZpDepartmentMst zpDepartmentedit = (ZpDepartmentMst)Departmentlst.getDepartmentDtls(deptId);
    		
        	zpDepartmentedit.setAdmintypecode(seladminDept);
         	zpDepartmentedit.setDepartmentName(txtdeptName);
        	zpDepartmentedit.setDepartmentCode(txtCode);
        	zpDepartmentedit.setUpdatedBy(1l);
        	zpDepartmentedit.setUpdatedByPost(1l);
        	zpDepartmentedit.setUpdatedDate(new Date());
    		zpDepartmentDAOImpl.update(zpDepartmentedit);
			redirectMap.put("MESSAGECODE", 300006L);
    		
        }
        else{
			ZpDepartmentDAO ZpDepartmentDAOImpl = new ZpDepartmentDAOImpl(ZpDepartmentMst.class,serviceLocator.getSessionFactory());
			Long lLngInwardPensionId = IFMSCommonServiceImpl.getNextSeqNum("MST_ZP_DEPT",objectArgs);

			zpDepartmentMstVO.setDeptId(lLngInwardPensionId);
			zpDepartmentMstVO.setAdmintypecode(seladminDept);
			zpDepartmentMstVO.setDepartmentName(txtdeptName);
			zpDepartmentMstVO.setDepartmentCode(txtCode);
			zpDepartmentMstVO.setLangId(1l);
			zpDepartmentMstVO.setCreatedDate(todayDate);
			zpDepartmentMstVO.setCreatedBy(1l);
			zpDepartmentMstVO.setQualification(txtqualification);
			ZpDepartmentDAOImpl.create(zpDepartmentMstVO);
			redirectMap.put("MESSAGECODE", 56L);
        }
			
			 //Sucess Message
		//Insert Ends............
		
		logger.info("Exit from saveZpDepartmentDtls");
		objectArgs.put("redirectMap", redirectMap);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("redirect view");
		return objRes;
	}

	
	public ResultObject loadDepartmentMstScreen(Map objectArgs) throws Exception
	{
		
		logger.info("Entering into loadData of loadDepartmentMstScreen,by ketan");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId =1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
	    langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		CmnDistrictMstDAO districtDao = new CmnDistrictMstDAOImpl(CmnDistrictMst.class,serviceLocator.getSessionFactory());
		ZpDistrictOfficeDAO adminofficelst = new ZpDistrictOfficeDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		ZpDepartmentDAO Departmentlst = new ZpDepartmentDAOImpl(ZpDepartmentMst.class,serviceLocator.getSessionFactory());
		List<CmnDistrictMst> cmnDistrictMstList = null;
		List zpadminOffice = null; 
		Long deptId = Long.valueOf(!StringUtility.getParameter("deptId",request).equalsIgnoreCase("") ? StringUtility.getParameter("deptId",request) : "0");
		String editFlag = !StringUtility.getParameter("edit",request).equalsIgnoreCase("") ? StringUtility.getParameter("edit",request) : "";
		logger.info("distId::"+deptId);
		logger.info("editFlag::"+editFlag);
		
		 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
         ZpDistrictOfficeDAO zpdistrictOfficeDAO =new ZpDistrictOfficeDAOImpl(ZpDistrictOfficeMst.class,serv.getSessionFactory());
        
         String strLoadMethodCall = StringUtility.getParameter("callloadMethod", request);
       
 		 ZpDepartmentMst zpDepartmentedit = (ZpDepartmentMst)Departmentlst.getDepartmentDtls(deptId);
 		
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
				lLstAdminOfficetypecode =zpDepartmentDAO.getadminOfficetypeCode(Long.valueOf(seladminDeptId));
				lLstAdminOfficetypecode = lLstAdminOfficetypecode + 1l;
				logger.info("lLstAdminOfficetypecode"+lLstAdminOfficetypecode);
				lLongAdminOffice = lLstAdminOfficetypecode.toString();
				lLongAdminOffice= lLongAdminOffice.trim();
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
      //   objectArgs.put("lLongAdminOffice",lLongAdminOffice);
         objectArgs.put("callloadMethod", strLoadMethodCall);
		
		
		cmnDistrictMstList = districtDao.getAllDistrict(objectArgs, langId);
		zpadminOffice = adminofficelst.getAdminOffice();
		logger.info("zpadminOffice::"+zpadminOffice.size());
		logger.info("zpadminOffice::"+zpadminOffice.contains(2));
		
		logger.info("cmnDistrictMstList:::"+cmnDistrictMstList.size());
		
	
        if(strLoadMethodCall.trim().equalsIgnoreCase("")){
        	strLoadMethodCall = "Y";
        }else{
        	strLoadMethodCall = "N";
        }
        List allDeptLst = Departmentlst.getallDeptName();
        if(allDeptLst!=null){
       	 objectArgs.put("allDeptLst", allDeptLst);
        }
        
      //Get Qualification 
        QualificationDAOImpl quadDAO = new QualificationDAOImpl(Qualification.class, serv.getSessionFactory());
		List qualificationDetails = quadDAO.getQualification();
		objectArgs.put("QualificationList", qualificationDetails);

		objectArgs.put("zpadminOffice", zpadminOffice);
		objectArgs.put("zpDepartmentedit", zpDepartmentedit);
		objectArgs.put("cmnDistrictMstList", cmnDistrictMstList);
		objectArgs.put("deptId",deptId);
		objectArgs.put("editFlag",editFlag);
		objectArgs.put("callloadMethod", strLoadMethodCall);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("DepartmentMst");
	    return objRes;
		
	}

	public ResultObject getDepartmentMstDtls(Map objectArgs)
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
	            
	            List<ZpDepartmentMst> zpdistrictOfficelst = zpDepartmentDAO.getAllAdminDepartmentOfficeData(langId);
	            logger.info("zpdistrictOfficelst::"+zpdistrictOfficelst);
	            Map map = new HashMap();
	            map=objectArgs;
	            map.put("zpdistrictOfficelst", zpdistrictOfficelst);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	            resultObject.setViewName("DepartmentMstView");
	           
		}
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...getDepartmentMstDtls");
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
