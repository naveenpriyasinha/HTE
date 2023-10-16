package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.dao.CmnBranchMstDaoImpl;
import com.tcs.sgv.common.dao.CmnCityMstDAOImpl;
import com.tcs.sgv.common.dao.CmnDistrictMstDAO;
import com.tcs.sgv.common.dao.CmnDistrictMstDAOImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDao;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.displaytable.PaginatedListImpl;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoOfficeDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstDcpsDesignation;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAO;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PayrollEmpSearchDAOImpl;
import com.tcs.sgv.eis.dao.SearchEmpDAOImplPayroll;
import com.tcs.sgv.eis.valueobject.EmployeeCustomVO;
import com.tcs.sgv.eis.valueobject.EmployeeSearchCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadCustomMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.SearcheEmployeeCriteria;
import com.tcs.sgv.eis.valueobject.SearcheEmployeeCustomVO;
import com.tcs.sgv.ess.dao.CmnBranchlocMpgDaoImpl;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDao;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.valueobject.CmnBranchlocMpg;
import com.tcs.sgv.ess.valueobject.EmployeeSearchCriteria;
import com.tcs.sgv.ess.valueobject.EmployeeSearchVO;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;

/**
 * This Class used for Employee Service 
 * @version 1.0
 * 
 *  
 */
public class PayrollEmpSearchService extends ServiceImpl
{		
	private static final Logger logger = Logger.getLogger(PayrollEmpSearchService.class.getName());

	/**
	 * This method is used to get all values for Search Employee Application
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */	
	public ResultObject searchEmployeeByAll(Map objectArgs)
	{
		logger.info(" method for with employeenamee searching**************.....");
		logger.info(" Method searchEmployeeByAll service Start.....");
		ResultObject  resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		List<SearcheEmployeeCustomVO> finalEmpList =new ArrayList();
		long lngEmpId=0;
		boolean boolFromSession=false;
		try
		{
			if (resultObject==null || objectArgs == null )
			{				
				resultObject.setResultCode(-1);
				return resultObject; 
			}			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			CmnLocationMstDaoImpl objCmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactorySlave());
			CmnDistrictMstDAOImpl objDistrictMst = new CmnDistrictMstDAOImpl(CmnDistrictMst.class, serv.getSessionFactorySlave());
			CmnCityMstDAOImpl objCityMstDAOImpl = new CmnCityMstDAOImpl(CmnCityMst.class,serv.getSessionFactorySlave());
			CmnBranchMstDaoImpl cmnBranchMstDaoImpl=new CmnBranchMstDaoImpl(CmnBranchMst.class,serv.getSessionFactorySlave());
			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());
			EmployeeSearchCriteria objEmpSrchCrit = new EmployeeSearchCriteria();
			//----------------------- Retrieving parameters from request object---------------
			String tempempName = StringUtility.getParameter("empFname",request);
			String empSrchName = StringUtility.getParameter("SearchEmployee",request);
			String tempempId = StringUtility.getParameter("empId",request);
			String tempempLname = StringUtility.getParameter("empLname",request);
			
		//	String tempempName = StringUtility.getParameter("empFname",request);
		
			String srchNameText = StringUtility.getParameter("hidden_srchNameText",request);

			if(srchNameText!=null && srchNameText.trim().indexOf(" ")!=-1)
			{
				String[] nameArr = srchNameText.split(" "); 
				tempempName = nameArr[0];
				tempempLname = nameArr[1];
			}
			else if(!"".equals(srchNameText))
			{
				tempempName = srchNameText;
			}
			String hidnSpecLocId = StringUtility.getParameter("hidden_hidnSpecLocId", request);
			String hidnSpecDsgnCodes = StringUtility.getParameter("hiddenSpecDsgnCodes", request);
			String checkForId = StringUtility.getParameter("checkForId", request);
			String hidnEmpExpDateSrchFlag = StringUtility.getParameter("hidden_empExpDateSrchFlag", request);
			String childLocSrchParam = StringUtility.getParameter("hidden_childLocSrchParam",request);
			String locationCode = StringUtility.getParameter("locPlacename", request);
			String BranchCode = StringUtility.getParameter("Branchname", request);
			String citycode =StringUtility.getParameter("city", request);
			String dsgncode = StringUtility.getParameter("dsgn", request);
			String ownLocationParmater = StringUtility.getParameter("hidden_OwnLocationParam", request);
			String functionName = StringUtility.getParameter("hidden_functionName",request);
			String searchFlag = StringUtility.getParameter("hidden_SearchFlag", request);
			String numberResultsPerPage = StringUtility.getParameter("lstResultsPerPage", request);
			String postLevelSortParam = StringUtility.getParameter("hidden_levelSortParam",request);
			String multiEmplSelect = StringUtility.getParameter("hidden_multiSelStatus",request);
			String seldEmployeeArray = StringUtility.getParameter("hidden_employeeSelArray", request);
			String seldEmplsNameArray = StringUtility.getParameter("hidden_selEmpsNameArray", request);
			String seldEmplsDesigArray = StringUtility.getParameter("hidden_selEmpsDesigArray", request);
			String seldEmplsPostArray = StringUtility.getParameter("hidden_selEmpsPostArray", request);
			String forReports = StringUtility.getParameter("forReports",request);
			String srchTagName = StringUtility.getParameter("srchTagName",request);
			String ajaxCallFlag = StringUtility.getParameter("ajaxCallFlag",request);
			String hiddenSpecDepttCodes = StringUtility.getParameter("hiddenSpecDepttCodes",request);
			
			
			List dsgnList = new ArrayList();
			objEmpSrchCrit.setPostLevelSortParam(postLevelSortParam);
			objEmpSrchCrit.setSrchnameGPFtext(srchNameText);
			//LoginDetails objLoginDetails =(LoginDetails)request.getSession().getAttribute("loginDetails");

			//Extracting login details from baseloginMap  
			Map loginMap =(Map)objectArgs.get("baseLoginMap");
			Long loggedInlocationId=(Long)loginMap.get("locationId");
			Long langId = (Long)loginMap.get("langId");
			
			
			logger.info("tempempName-----"+tempempName);
			logger.info("tempempLname-----"+tempempLname);
			logger.info("tempempId-----"+tempempId);
			logger.info("hidnSpecDsgnCodes value is "+hidnSpecDsgnCodes);
			logger.info("check for id concept value is "+checkForId);
			logger.info("hidnEmpExpDateSrchFlag concept value is "+hidnEmpExpDateSrchFlag);
			logger.info("childLocSrchParam-----"+childLocSrchParam);
			logger.info("hidnSpecLocId-------"+hidnSpecLocId);
			logger.info("BranchCode-------"+BranchCode);
			logger.info("citycode-----"+citycode);
			logger.info("dsgncode-----"+dsgncode);
			logger.info("numberResultsPerPage==>"+numberResultsPerPage);
			logger.info("searchFlag-----"+searchFlag);
			logger.info("ownLocationParmater-------"+ownLocationParmater);
		    logger.info("----temp id is-----"+tempempId);
			logger.info("--logged in location id is -----"+loggedInlocationId);
			logger.info("seldEmplsPostArray -----"+seldEmplsPostArray);

			//Declaring variables and creating objects required for processing
			Long loggedInCityId = null;
			String listReadOnlyParam = null;
			String employeeId="";
			String SearchEmployeekey="";
			List searchEmpList = new ArrayList();
			OrgEmpMst objOrgEmpMst= new OrgEmpMst();
			objOrgEmpMst.setEmpFname(tempempName);
			objOrgEmpMst.setEmpLname(tempempLname);
			PaginatedListImpl   paginatelist = new PaginatedListImpl();
			int count = 0;

			//The following code is executed if employee service is used for Employee Search
			if("true".equals(searchFlag))
			{
				if("Yes".equals(ownLocationParmater))
				{
					listReadOnlyParam = "true";
					CmnLocationMst objCmnLocationMst=objCmnLocationMstDaoImpl.read(loggedInlocationId);	
					if(objCmnLocationMst.getLocCityId() != null)
					{		
						loggedInCityId = objCmnLocationMst.getLocCityId();
						if(loggedInCityId != null )
						{
							CmnCityMst objCityMst = new CmnCityMst();
							objCityMst = objCityMstDAOImpl.read(loggedInCityId);				
							objectArgs.put("loggedInCityCode",objCityMst.getCityCode());
						}
					}
					objectArgs.put("loggedInLocationcode",objCmnLocationMst.getLocationCode());
				}
				// Put the values from request Paramters  
				else
				{
					listReadOnlyParam = "false";
					objectArgs.put("loggedInCityCode",citycode);
					objectArgs.put("loggedInLocationcode",locationCode);

					if("".equals(citycode))
					{
						logger.info("--citycode not opening from normal search -----"+loggedInlocationId);
						CmnLocationMst objCmnLocationMst = objCmnLocationMstDaoImpl.read(loggedInlocationId);
						CmnCityMst objCityMst = null;
						Long LoggedInCityId = null;
						if(objCmnLocationMst.getLocCityId() != null)
						{		
							LoggedInCityId = objCmnLocationMst.getLocCityId();
						}

						Long loggedInDistrictId = objCmnLocationMst.getLocDistrictId();

						if(LoggedInCityId != null )
						{
							objCityMst = objCityMstDAOImpl.read(LoggedInCityId);				
							objectArgs.put("loggedInCityCode",objCityMst.getCityCode());
						}
						objectArgs.put("loggedInLocationcode", objCmnLocationMst.getLocationCode());

					}
				}
				
				logger.info("no of records as enterd by user"+numberResultsPerPage);
				
				if("-1".equals(numberResultsPerPage) || numberResultsPerPage.trim().length()==0 )
				{
					logger.info("in IF--no of records as enterd by user"+numberResultsPerPage);
					numberResultsPerPage="10";
				}

				
				logger.info("functionNameParameter-----"+functionName);
				logger.info("forReports-----"+forReports);
				logger.info("dsgncode: -->"+dsgncode);
				logger.info("citycode:-->"+citycode);
				logger.info("locationCode  -->"+locationCode);
				
				
				objectArgs.put("defEmpfnameTxtVal", tempempName);
				objectArgs.put("childLocSrchParam",childLocSrchParam);
				objectArgs.put("tempempLname", tempempLname);
				objectArgs.put("numberResultsPerPage", numberResultsPerPage);
				objectArgs.put("levelSortParam", postLevelSortParam);
				objectArgs.put("listReadOnlyParam",listReadOnlyParam);
				objectArgs.put("ownLocationKey", ownLocationParmater);
				objectArgs.put("designationCode",dsgncode);
				objectArgs.put("hidnSpecLocId", hidnSpecLocId);
				objectArgs.put("seldEmployeeArray", seldEmployeeArray);
				objectArgs.put("seldEmplsNameArray", seldEmplsNameArray);
				objectArgs.put("seldEmplsDesigArray", seldEmplsDesigArray);
				objectArgs.put("seldEmplsPostArray", seldEmplsPostArray);
				objectArgs.put("multiEmplSelect", multiEmplSelect);
				objectArgs.put("empSrchName",empSrchName);
				objectArgs.put("functionName",functionName);
				objectArgs.put("forReports",forReports);
				objectArgs.put("srchTagName",srchTagName);

				

				//TODO -- If district or city can be the only search criteria entered by user 
				CmnDistrictMst objCmnDistrictMst = new CmnDistrictMst();
				objCmnDistrictMst = null;
				int districtNullflag =0;
				CmnCityMst objCmnCityMst = new CmnCityMst();
				objCmnCityMst = null;
				int cityNullFlag = 0 ;
				
				if(!"Yes".equals(ownLocationParmater))
				{
					

					//Setting city object for the city code entered by user
					if(!"".equals(citycode) && !"-1".equals(citycode))
					{
						cityNullFlag = 1;
						logger.info("city code found-----setting object"+citycode);
						objCmnCityMst = objCityMstDAOImpl.getCityVOByCityCodeAndLangId(citycode, langId);
						logger.info("city code for city object set----"+objCmnCityMst.getCityCode());
						logger.info("city ID for city object set----"+objCmnCityMst.getCityId());
					}
				}

				CmnLocationMst objCmnLocationMst = new CmnLocationMst();
				objCmnLocationMst=null;
				List locSrchList = new ArrayList();
				logger.info("location code from list box is -----"+locationCode);
				// Setting location object according to value selected by user from location listbox
				if(!"".equals(locationCode) && !"-1".equals(locationCode))
				{	
					logger.info("location code found-----setting object"+locationCode);
					objCmnLocationMst = objCmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId(locationCode,langId);
					logger.info("location code for location object set----"+objCmnLocationMst.getLocationCode());
					logger.info("location ID for location object set----"+objCmnLocationMst.getLocId());
					if(!"Yes".equals(childLocSrchParam))
					{
						locSrchList.add(objCmnLocationMst);
					}
				}
				// logged in location is taken as default if user doesn't select a location
				if(districtNullflag==0 && cityNullFlag==0)
				{
					logger.info("no location selected by user-----setting looged in location ");
					// reading langid specific location object in case user doesn't specify location search criteria
					objCmnLocationMst=objCmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId
					(objCmnLocationMstDaoImpl.read(loggedInlocationId).getLocationCode(), langId);
					logger.info("location code for location object set----"+objCmnLocationMst.getLocationCode());
					logger.info("location ID for location object set----"+objCmnLocationMst.getLocId());
					if(!"Yes".equals(childLocSrchParam))
					{
						locSrchList.add(objCmnLocationMst);
					}
				}
				if(hidnSpecLocId != null && !"".equals(hidnSpecLocId.trim()))
				{
					logger.info("some hidden specific Location present in hidden field");
					// reading langid specific location object in case user doesn't specify location search criteria
					objCmnLocationMst = objCmnLocationMstDaoImpl.read(Long.valueOf(hidnSpecLocId));
					logger.info("location code for location object set----"+objCmnLocationMst.getLocationCode());
					logger.info("location ID for location object set----"+objCmnLocationMst.getLocId());
					if(!"Yes".equals(childLocSrchParam))
					{
						locSrchList.add(objCmnLocationMst);
					}
				}

				if("Yes".equals(childLocSrchParam))
				{
					logger.info("Search in child location also");
					if(objCmnLocationMst != null)
					{
						logger.info("Location object exists");
						locSrchList = objCmnLocationMstDaoImpl.getChildLocations(objCmnLocationMst.getLocId());
						logger.info("Search in child location also and child location list size is "+locSrchList.size());
						locSrchList.add(objCmnLocationMst);
					}
					else
					{
						logger.info("Considering logged in location");
						objCmnLocationMst = objCmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId
						(objCmnLocationMstDaoImpl.read(loggedInlocationId).getLocationCode(), langId);
						locSrchList = objCmnLocationMstDaoImpl.getChildLocations(objCmnLocationMst.getLocId());
						logger.info("Search in child location also and child location list size is "+locSrchList.size());
						locSrchList.add(objCmnLocationMst);
					}
				}

				// Setting Designation object for search
				OrgDesignationMstDaoImpl objOrgDsgnImpl = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,
						serv.getSessionFactory());
				OrgDepartmentMstDao objOrgDepttImpl = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,
						serv.getSessionFactory());
				List dsgnCodes = new ArrayList();
				List depttCodes = new ArrayList();

				OrgDesignationMst objOrgDesignationMst= new OrgDesignationMst();		
				objOrgDesignationMst = null;		
				if(!"".equals(dsgncode) && !"-1".equals(dsgncode))
				{	
					logger.info("-----designation code in if-----"+dsgncode);
					objOrgDesignationMst = objOrgDsgnImpl.getDesignationVOByDsgnCodeAndLangId(dsgncode, langId);
				}
				if(hidnSpecDsgnCodes!=null && !"".equals(hidnSpecDsgnCodes))
				{
					logger.info("hidden for designation codes");
					if(hidnSpecDsgnCodes.indexOf(',')!= -1)
					{
						String[] hidnDsgnCodesArr = hidnSpecDsgnCodes.split(",");
						dsgnCodes = Arrays.asList(hidnDsgnCodesArr);
						objEmpSrchCrit.setDsgnSpecCodesList(dsgnCodes);
					}
					dsgnList = objOrgDsgnImpl.getDesignationByDesigCodesAndLang(dsgnCodes,langId);
				}
				else
				{
					//dsgnList = objOrgDsgnImpl.getAllDesignation(objectArgs,langId);
					AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(MstDcpsDesignation.class, serv.getSessionFactory());
					dsgnList =adminDao.getActiveDsgnList(loggedInlocationId);
				}
				if(hiddenSpecDepttCodes!=null && !"".equals(hiddenSpecDepttCodes))
				{
					logger.info("hiddenSpecDepttCodes for designation codes");
					depttCodes = objOrgDepttImpl.getAllDepttForDeppCodes(hiddenSpecDepttCodes,langId,"depttIds");
					objEmpSrchCrit.setDepttSpecCodesList(depttCodes);
				}

				//Setting Language object for search according to login language
				CmnLanguageMst objCmnLanguageMst=null;
				CmnLanguageMstDaoImpl objCmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				objCmnLanguageMst=objCmnLanguageMstDaoImpl.read(langId);

				logger.info("---language set----post now----->>>");
				OrgPostDetailsRltDaoImpl detailsRltDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
				Long userId = (Long)loginMap.get("userId");
				OrgPostDetailsRlt objOrgPostDetailsRlt = detailsRltDaoImpl.getPostDetailsRltFromUserIdAndLangId(userId, langId);
				OrgPostMst objLoggedInPostMst = objOrgPostDetailsRlt.getOrgPostMst();
				logger.info("logged in post level id----->>>>"+objLoggedInPostMst.getPostLevelId());
				objectArgs.put("post", objLoggedInPostMst.getPostLevelId());
				objEmpSrchCrit.setLocSrchList(locSrchList);
				objEmpSrchCrit.setCmnLanguageMst(objCmnLanguageMst);
				objEmpSrchCrit.setOrgDesignationMst(objOrgDesignationMst);
				objEmpSrchCrit.setOrgEmpMst(objOrgEmpMst);
				objEmpSrchCrit.setCmnDistrictMst(objCmnDistrictMst);
				objEmpSrchCrit.setCmnCityMst(objCmnCityMst);
				objEmpSrchCrit.setOrgPostMst(objLoggedInPostMst);
				objEmpSrchCrit.setEmpServiceExpDateFlag(hidnEmpExpDateSrchFlag);


				List<CmnBranchMst> cmnBranchMstLst=cmnBranchMstDaoImpl.getListByColumnAndValue("branchCode",BranchCode);
				if(!cmnBranchMstLst.isEmpty())
				{	
					for(int i=0;i<cmnBranchMstLst.size();i++)
					{
						if(cmnBranchMstLst.get(i).getCmnLanguageMst().getLangId()==langId)
						{
							objEmpSrchCrit.setCmnBranchMst(cmnBranchMstLst.get(i));
						}
					}
					objectArgs.put("loggedInBranchCode",cmnBranchMstLst.get(0).getBranchCode());
				}
				paginatelist.setRequest(request);
				String page= request.getParameter("page");
				logger.info("page aaaaaaa"+page);

				if(page != null)
				{
					page = request.getParameter("page"); 

				} 
				else
				{
					page="1";
				}


				paginatelist = paginatelist.getPaginatedListObject(Integer.parseInt(numberResultsPerPage),page);

				int startIndex=paginatelist.getFirstRecordIndex();
				int pageSize = paginatelist.getPageSize();
				List<OrgEmpMst> searchEmpListcount  = empImpl.SearchEmployeeUpdate(objEmpSrchCrit,startIndex,pageSize,false);
				count = Integer.valueOf(searchEmpListcount.size());
				searchEmpList = empImpl.SearchEmployeeUpdate(objEmpSrchCrit,startIndex,pageSize,true);	
			
				//searchEmpList = empImpl.SearchEmployeeNew(objOrgEmpMst, objCmnLocationMst,objCmnLanguageMst,objOrgDesignationMst,objCityMst,objCmnDistrictMst );
				


				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				String OfficeName = payBillDAOImpl.getOffice(locId);
				objectArgs.put("OfficeName",OfficeName);
				
				
				logger.info("startIndex  in searchEmployeeByAll===>"+ startIndex);
				logger.info("pageSize  in searchEmployeeByAll===>"+ pageSize);
				logger.info("page no in searchEmployeeByAll===>"+page);
				logger.info("searchEmpListcount in searchEmployeeByAll==>"+searchEmpListcount.size());
				logger.info("SearchEmpList in searchEmployeeByAll:-->"+searchEmpList.size());
				logger.info("SearchEmpList:-->"+searchEmpList.size());
				
				
				
				
				if("true".equals(ajaxCallFlag))
				{
					StringBuffer srchResult = new StringBuffer();

					String sevarthEmpCode ="";
					
					logger.info("ajax calling is true");
					srchResult.append("<srchResults>");
					
					if(searchEmpList.size() == 1)
					{
						logger.info("result list size is 1");
						EmployeeSearchVO resultEmpVo = new EmployeeSearchVO();
						resultEmpVo = getEmployeeSearchVO((OrgEmpMst)searchEmpList.get(0), objectArgs);
						
						long EMPLOYEEID = resultEmpVo.getEmpId();
						//String GPFACCTNO =payBillDAOImpl.getGpfAcctNuFromEmpId(EMPLOYEEID);
						String GPFACCTNO = "";
						
						String gpfActNo = "";
						String pfSeries = "";
						
						List GpfAndSeriesList  = payBillDAOImpl.getGPFAcctNoPfSeriesFromEployeeId(EMPLOYEEID);
						
						if(GpfAndSeriesList.size()>0)
						{
							for(int i=0;i<GpfAndSeriesList.size();i++)
							{
								Object[] rowlist = (Object[])GpfAndSeriesList.get(i);
								
								if(rowlist[0]!=null)
								{
									gpfActNo=rowlist[0].toString();
								}
								if(rowlist[1]!=null)
								{
									pfSeries = rowlist[1].toString();
								}
								
								if(rowlist[0]!=null && rowlist[1]!=null) 
								{
									if(pfSeries.equalsIgnoreCase("dcps"))
									{
										GPFACCTNO = gpfActNo;
									}
									else
									{
										GPFACCTNO = pfSeries+"/"+gpfActNo;
									}
								}
								else if(rowlist[0]==null && rowlist[1]==null) 
								{
									GPFACCTNO = pfSeries+"/"+gpfActNo;
								}
								else if(rowlist[0]!=null && rowlist[1]==null)
								{
									GPFACCTNO=gpfActNo;
								}
								else if(rowlist[0]==null && rowlist[1]!=null)
								{
									GPFACCTNO=pfSeries;
								}
							}
							
							
						}
						
						//String USERNAME = resultEmpVo.getUserName();
						
						logger.info("GPFACCTNO************"+GPFACCTNO);
						logger.info("pfSeries************"+pfSeries);
						logger.info("emplid*********"+resultEmpVo.getEmpId());
						logger.info("emplname***********"+resultEmpVo.getEmpLname());
						logger.info("locdistrictname*******"+resultEmpVo.getLocDistrictName());
						logger.info("username**********"+resultEmpVo.getUserName());
						logger.info("userid*********"+resultEmpVo.getUserId());
						logger.info("funname************"+functionName);
						
						
						
						
						logger.info("Employee id*************"+ resultEmpVo.getEmpId());
						long empId = resultEmpVo.getEmpId();
						
						List empList = empImpl.getEmpListFromHreisempMst(empId);
						logger.info("empList:::::::::::::: " +empList.size());
						long EmployeeId =0;
						
						for(Iterator itr=empList.iterator();itr.hasNext();)
						{
							Object[] rowList = (Object[])itr.next();
	
							if(rowList[0] != null)
							{
								EmployeeId = Long.parseLong(rowList[0].toString().trim());
								sevarthEmpCode = rowList[1].toString().trim();
								logger.info("sevarthEmpCode in Employee search ******" + sevarthEmpCode);
								logger.info("EmployeeId in Employee search::::::::::::::" + EmployeeId);
							}
							if(rowList[1] != null)
							{
								sevarthEmpCode = rowList[1].toString().trim();
								logger.info("sevarthEmpCode******" + sevarthEmpCode);
							}
						
						}
						
						logger.info("EmployeeId******" + EmployeeId);
						logger.info("getusername is ****************"+resultEmpVo.getUserName());
						
						srchResult.append("<empSrchName>").append(empSrchName).append("</empSrchName>");
						srchResult.append("<srchResNo>").append("One").append("</srchResNo>"); 
						srchResult.append("<empId>").append(resultEmpVo.getEmpId()).append("</empId>");
						//srchResult.append("<empId>").append(EmployeeId).append("</empId>");
						
						srchResult.append("<empName>").append(resultEmpVo.getEmpPrf()+"."+
								resultEmpVo.getEmpFname() + " " + resultEmpVo.getEmpLname()).append("</empName>");
						//srchResult.append("<empLoc>").append(resultEmpVo.getLocPlacename())	.append("</empLoc>");
						srchResult.append("<empLoc>").append(OfficeName).append("</empLoc>");
						srchResult.append("<empLocDistr>").append(resultEmpVo.getLocDistrictName())
						.append("</empLocDistr>");
						srchResult.append("<empDesig>").append(resultEmpVo.getDsgnName())
						.append("</empDesig>");
						if(!resultEmpVo.getUserName().equals("") && resultEmpVo.getUserName()!=null && !resultEmpVo.getUserName().equals(" ") )
						{
						srchResult.append("<empUserName>").append(resultEmpVo.getUserName())
						.append("</empUserName>");
						}
						else
						{
							srchResult.append("<empUserName>").append(GPFACCTNO)
							.append("</empUserName>");
						}
						srchResult.append("<empUserId>").append(resultEmpVo.getUserId())
						.append("</empUserId>");
						srchResult.append("<empFuncName>").append(functionName)
						.append("</empFuncName>");
						srchResult.append("<empPostId>").append(sevarthEmpCode)
						.append("</empPostId>");
					}
					else if (searchEmpList.isEmpty())
					{
						srchResult.append("<empSrchName>").append(empSrchName).append("</empSrchName>");
						srchResult.append("<srchResNo>").append("Zero").append("</srchResNo>"); 


					}
					else
					{
						srchResult.append("<empSrchName>").append(empSrchName).append("</empSrchName>");
						srchResult.append("<srchResNo>").append("MoreThanOne").append("</srchResNo>"); 
						srchResult.append("<empId>").append("").append("</empId>");
						srchResult.append("<empName>").append("").append("</empName>");
						srchResult.append("<empLoc>").append("").append("</empLoc>");
						srchResult.append("<empLocDistr>").append("").append("</empLocDistr>");
						srchResult.append("<empDesig>").append("").append("</empDesig>");
						srchResult.append("<empUserName>").append("").append("</empUserName>");
						srchResult.append("<empUserId>").append("").append("</empUserId>");
						srchResult.append("<empFuncName>").append("").append("</empFuncName>");
						srchResult.append("<empPostId>").append("").append("</empPostId>");
					}
					srchResult.append("</srchResults>");	

					String tempStr = new AjaxXmlBuilder().addItem("key_ajax", srchResult.toString()).toString();
					logger.info("generated search result xml is "+tempStr);
					objectArgs.put("ajaxKey", tempStr);
					resultObject.setResultCode(ErrorConstants.SUCCESS);
					resultObject.setResultValue(objectArgs);
					resultObject.setViewName("ajaxData");
					return resultObject;
				}
			}
			
			else
			{
				logger.info("Employee service has been called to view Employee Details--->");
				employeeId=(String)objectArgs.get("employeeId");		
				SearchEmployeekey=(String)objectArgs.get("SearchEmployee");
				logger.info("Employee service has been called to view Employee Details--->"+employeeId);
				logger.info("Employee service has been called to view Employee SearchEmployeekey--->"+SearchEmployeekey);
				
				
				if(objectArgs.get("reqLangId") != null)
				{
					Long reqLangId = (Long) objectArgs.get("reqLangId");
					if (reqLangId != null)// TODO -- check for other values
					{
						langId = reqLangId;
					}
				}
				tempempId=employeeId;
				if(tempempId!= null)
				{
					lngEmpId = Long.valueOf(tempempId).longValue();
				}
				boolFromSession=true;
				objOrgEmpMst.setEmpId(lngEmpId);
				objectArgs.put("SearchEmployee",SearchEmployeekey);
				logger.info("boolean  from session-----VIew mode yes-----"+boolFromSession);
				//OrgEmpMst objEmpMstForView = empImpl.getEmployeeForView(objOrgEmpMst, objOrgEmpMst.getCmnLanguageMst().getLangId());
				OrgEmpMst objEmpMstForView = empImpl.read(objOrgEmpMst.getEmpId());
				searchEmpList.add(objEmpMstForView);
			}

			
			
			
			objectArgs.put("functionName",functionName);
			
			objectArgs.put("checkForId",checkForId);
			objectArgs.put("hiddenDsgnCodes",hidnSpecDsgnCodes);
			objectArgs.put("empExpSrchFlag",hidnEmpExpDateSrchFlag);
			
			
			//following code calls static method which returns a list of EmployeeSearch Objects
			finalEmpList = PayrollEmpSearchService.createEmployeeSearchResultList(searchEmpList, boolFromSession, objectArgs, langId);

			
			objectArgs.put("dsgnList", dsgnList);
			logger.info("employee search fetched the following number of results:::-->"+finalEmpList.size());
			objectArgs.put("finalEmpList", finalEmpList);
			logger.info("finalEmpList size"+finalEmpList.size());
			paginatelist.setList(finalEmpList);
			paginatelist.setTotalNumberOfRows(count);
			objectArgs.put("paginatelist", paginatelist);
			
			
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List officeLList = payBillDAOImpl.getOfficeList(locId);
			objectArgs.put("officeList",officeLList);
			
			
			if(boolFromSession)
			{
				logger.info("employee session key:-->"+"empSearchVO"+SearchEmployeekey);		 
				if (!finalEmpList.isEmpty()) 
				{
					objectArgs.put("empSearchVO"+SearchEmployeekey, finalEmpList.get(0));
					resultObject.setSessionValues("empSearchVO"+SearchEmployeekey, finalEmpList.get(0));
					logger.info("after put in session:--> "+finalEmpList.size());
				}
			}
			String requesturi="./hdiits.htm?actionFlag=searchEmployeeByAll";
			objectArgs.put("requesturi", requesturi);
			resultObject.setResultValue(objectArgs);
			logger.info("Processing in searchEmployeesByAll method of EMPServiceImpl is over");	

			resultObject.setViewName("findemployee");
		}
		catch (Exception e)
		{
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error( "ERROR : Some exception has occured...process cannot continue",e);
		}
		return resultObject;
	}


	
	/*public ResultObject searchEmployeeByAll(Map objectArgs)
	{
		logger.info(" Method searchEmployeeByAll service Start.....");
		ResultObject  resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		List<SearcheEmployeeCustomVO> finalEmpList =new ArrayList();
		long lngEmpId=0;
		boolean boolFromSession=false;
		try
		{
			if (resultObject==null || objectArgs == null )
			{				
				resultObject.setResultCode(-1);
				return resultObject; 
			}			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			CmnLocationMstDaoImpl objCmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactorySlave());
			//CmnDistrictMstDAOImpl objDistrictMst = new CmnDistrictMstDAOImpl(CmnDistrictMst.class, serv.getSessionFactorySlave());
			CmnCityMstDAOImpl objCityMstDAOImpl = new CmnCityMstDAOImpl(CmnCityMst.class,serv.getSessionFactorySlave());
			CmnBranchMstDaoImpl cmnBranchMstDaoImpl=new CmnBranchMstDaoImpl(CmnBranchMst.class,serv.getSessionFactorySlave());
			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());
			
			
			SearcheEmployeeCriteria objEmpSrchCrit = new SearcheEmployeeCriteria();
			//----------------------- Retrieving parameters from request object---------------
			String tempempName = StringUtility.getParameter("empFname",request);
			String empSrchName = StringUtility.getParameter("SearchEmployee",request);
			String tempempId = StringUtility.getParameter("empId",request);
			String tempempLname = StringUtility.getParameter("empLname",request);
			
		//	String tempempName = StringUtility.getParameter("empFname",request);
			logger.info("tempempName-----"+tempempName);
			logger.info("tempempLname-----"+tempempLname);
			logger.info("tempempId-----"+tempempId);

			String srchNameText = StringUtility.getParameter("hidden_srchNameText",request);

			if(srchNameText!=null && srchNameText.trim().indexOf(" ")!=-1)
			{
				String[] nameArr = srchNameText.split(" "); 
				tempempName = nameArr[0];
				tempempLname = nameArr[1];
			}
			else if(!"".equals(srchNameText))
			{
				tempempName = srchNameText;
			}
			String hidnSpecLocId = StringUtility.getParameter("hidden_hidnSpecLocId", request);
			String hidnSpecDsgnCodes = StringUtility.getParameter("hiddenSpecDsgnCodes", request);
			logger.info("hidnSpecDsgnCodes value is "+hidnSpecDsgnCodes);
			String checkForId = StringUtility.getParameter("checkForId", request);
			logger.info("check for id concept value is "+checkForId);
			objectArgs.put("checkForId",checkForId);
			objectArgs.put("hiddenDsgnCodes",hidnSpecDsgnCodes);
			String hidnEmpExpDateSrchFlag = StringUtility.getParameter("hidden_empExpDateSrchFlag", request);
			logger.info("hidnEmpExpDateSrchFlag concept value is "+hidnEmpExpDateSrchFlag);
			objectArgs.put("empExpSrchFlag",hidnEmpExpDateSrchFlag);
			String childLocSrchParam = StringUtility.getParameter("hidden_childLocSrchParam",request);
			logger.info("childLocSrchParam-----"+childLocSrchParam);
			String locationCode = StringUtility.getParameter("locPlacename", request);
			logger.info("hidnSpecLocId-------"+hidnSpecLocId);

			String BranchCode = StringUtility.getParameter("Branchname", request);
			logger.info("BranchCode-------"+BranchCode);
			String districtcode = StringUtility.getParameter("district", request);
			String citycode =StringUtility.getParameter("city", request);
			String dsgncode = StringUtility.getParameter("dsgn", request);
			logger.info("districtcode-----"+districtcode);
			logger.info("citycode-----"+citycode);
			logger.info("dsgncode-----"+dsgncode);
			String ownLocationParmater = StringUtility.getParameter("hidden_OwnLocationParam", request);
			String functionName = StringUtility.getParameter("hidden_functionName",request);
			String searchFlag = StringUtility.getParameter("hidden_SearchFlag", request);
			String numberResultsPerPage = StringUtility.getParameter("lstResultsPerPage", request);
			logger.info("numberResultsPerPage==>"+numberResultsPerPage);
			String postLevelSortParam = StringUtility.getParameter("hidden_levelSortParam",request);
			String multiEmplSelect = StringUtility.getParameter("hidden_multiSelStatus",request);
			String seldEmployeeArray = StringUtility.getParameter("hidden_employeeSelArray", request);
			String seldEmplsNameArray = StringUtility.getParameter("hidden_selEmpsNameArray", request);
			String seldEmplsDesigArray = StringUtility.getParameter("hidden_selEmpsDesigArray", request);
			String seldEmplsPostArray = StringUtility.getParameter("hidden_selEmpsPostArray", request);
			String forReports = StringUtility.getParameter("forReports",request);
			String srchTagName = StringUtility.getParameter("srchTagName",request);
			String ajaxCallFlag = StringUtility.getParameter("ajaxCallFlag",request);
			String hiddenSpecDepttCodes = StringUtility.getParameter("hiddenSpecDepttCodes",request);
			List dsgnList = new ArrayList();

			objEmpSrchCrit.setPostLevelSortParam(postLevelSortParam);
			objEmpSrchCrit.setSrchnameGPFtext(srchNameText);
			//LoginDetails objLoginDetails =(LoginDetails)request.getSession().getAttribute("loginDetails");
			logger.info("own location paramter-------"+ownLocationParmater);
			//logger.info("----temp id is-----"+tempempId);

			//Extracting login details from baseloginMap  
			Map loginMap =(Map)objectArgs.get("baseLoginMap");
			Long loggedInlocationId=(Long)loginMap.get("locationId");
			Long langId = (Long)loginMap.get("langId");
			logger.info("--logged in location id is -----"+loggedInlocationId);
			logger.info("seldEmplsPostArray -----"+seldEmplsPostArray);

			//Declaring variables and creating objects required for processing
			String loggedInCityId = "";
			String listReadOnlyParam = null;
			String employeeId="";
			String SearchEmployeekey="";
			List searchEmpList = new ArrayList();
			OrgEmpMst objOrgEmpMst= new OrgEmpMst();
			objOrgEmpMst.setEmpFname(tempempName);
			objOrgEmpMst.setEmpLname(tempempLname);
			PaginatedListImpl   paginatelist = new PaginatedListImpl();
			int count = 0;

			//The following code is executed if employee service is used for Employee Search
			if("true".equals(searchFlag))
			{
				
				 Default values for ListBoxes are put in map by the following code
			 Retrieve details from object if own location search 
				
				DdoOfficeDAOImpl ddoOfficeDAOImpl = new DdoOfficeDAOImpl(DdoOffice.class,serv.getSessionFactory());
				
				
				if("Yes".equals(ownLocationParmater))
				{
					listReadOnlyParam = "true";
					CmnLocationMst objCmnLocationMst=objCmnLocationMstDaoImpl.read(loggedInlocationId);	
					if(objCmnLocationMst.getLocCityId() != null)
					{		
						loggedInCityId = objCmnLocationMst.getLocCityId();
						if(loggedInCityId != null )
						{
							CmnCityMst objCityMst = new CmnCityMst();
							objCityMst = objCityMstDAOImpl.read(loggedInCityId);				
							objectArgs.put("loggedInCityCode",objCityMst.getCityCode());
						}
					}
					
				
					DdoOffice objCityMst = new DdoOffice();
					objCityMst = (DdoOffice)ddoOfficeDAOImpl.read(citycode);
					objectArgs.put("loggedInCityCode",objCityMst.getDcpsDdoCode());
				
					
					
				//	Long loggedInDistrictId = objCmnLocationMst.getLocDistrictId();
					//CmnDistrictMst objCmnDistrictMst =objDistrictMst.read(loggedInDistrictId);
					//objectArgs.put("loggedInDistrictCode",objCmnDistrictMst.getDistrictCode());
					objectArgs.put("loggedInLocationcode",objCmnLocationMst.getLocationCode());
				}
				// Put the values from request Paramters  
				else
				{
					listReadOnlyParam = "false";
					objectArgs.put("loggedInCityCode",citycode);
				//	objectArgs.put("loggedInDistrictCode",districtcode);
					objectArgs.put("loggedInLocationcode",locationCode);

					//if("".equals(citycode) || "".equals(districtcode))
					if("".equals(citycode))
					{
						logger.info("--citycode not opening from normal search -----"+loggedInlocationId);
						CmnLocationMst objCmnLocationMst = objCmnLocationMstDaoImpl.read(loggedInlocationId);
						DdoOffice objCityMst = null;
						Long LoggedInCityId = null;
						if(objCmnLocationMst.getLocCityId() != null)
						{		
							LoggedInCityId = Long.parseLong(citycode);
						}

					//	Long loggedInDistrictId = objCmnLocationMst.getLocDistrictId();
					//	CmnDistrictMst objCmnDistrictMst =objDistrictMst.read(loggedInDistrictId);

						if(citycode != null )
						{
							objCityMst = (DdoOffice)ddoOfficeDAOImpl.read(citycode);			
							objectArgs.put("loggedInCityCode",objCityMst.getDcpsDdoCode());
						}
					//	objectArgs.put("loggedInDistrictCode",objCmnDistrictMst.getDistrictCode());
						objectArgs.put("loggedInLocationcode", objCmnLocationMst.getLocationCode());

					}
				}
				logger.info("no of records as enterd by user"+numberResultsPerPage);
				if("-1".equals(numberResultsPerPage) || numberResultsPerPage.trim().length()==0 )
				{
					logger.info("in IF--no of records as enterd by user"+numberResultsPerPage);
					numberResultsPerPage="10";
				}

				objectArgs.put("defEmpfnameTxtVal", tempempName);
				objectArgs.put("childLocSrchParam",childLocSrchParam);
				objectArgs.put("tempempLname", tempempLname);
				objectArgs.put("numberResultsPerPage", numberResultsPerPage);
				objectArgs.put("levelSortParam", postLevelSortParam);
				objectArgs.put("listReadOnlyParam",listReadOnlyParam);
				objectArgs.put("ownLocationKey", ownLocationParmater);
				objectArgs.put("designationCode",dsgncode);
				objectArgs.put("hidnSpecLocId", hidnSpecLocId);
				objectArgs.put("seldEmployeeArray", seldEmployeeArray);
				objectArgs.put("seldEmplsNameArray", seldEmplsNameArray);
				objectArgs.put("seldEmplsDesigArray", seldEmplsDesigArray);
				objectArgs.put("seldEmplsPostArray", seldEmplsPostArray);
				objectArgs.put("multiEmplSelect", multiEmplSelect);
				objectArgs.put("empSrchName",empSrchName);
				logger.info("functionNameParameter-----"+functionName);
				logger.info("forReports-----"+forReports);
				objectArgs.put("functionName",functionName);
				objectArgs.put("forReports",forReports);
				objectArgs.put("srchTagName",srchTagName);

				logger.info("dsgncode: -->"+dsgncode);
				//logger.info("districtcode:-->"+districtcode);
				logger.info("citycode:-->"+citycode);
				logger.info("locationCode  -->"+locationCode);

				//TODO -- If district or city can be the only search criteria entered by user 
				CmnDistrictMst objCmnDistrictMst = new CmnDistrictMst();
				objCmnDistrictMst = null;
				int districtNullflag =0;
				DdoOffice objCmnCityMst = new DdoOffice();
				objCmnCityMst = null;
				int cityNullFlag = 0 ;
				District and City objects are included in search criteria only when user wants to search in 
			location other than his logged in location
				if(!"Yes".equals(ownLocationParmater))
				{
					//Setting district object for the district code entered by user 
					if(!"".equals(districtcode) && !"-1".equals(districtcode))
					{
						districtNullflag = 1;
						logger.info("district code found-----setting object"+districtcode);
						//objCmnDistrictMst = objDistrictMst.getDistrictVOByDistrictCodeAndLangId(districtcode, langId);
						logger.info("district code for district object set----"+objCmnDistrictMst.getDistrictCode());
						logger.info("district ID for district object set----"+objCmnDistrictMst.getDistrictId());
					}

					//Setting city object for the city code entered by user
					if(!"".equals(citycode) && !"-1".equals(citycode))
					{
						cityNullFlag = 1;
						logger.info("city code found-----setting object"+citycode);
						objCmnCityMst = (DdoOffice) ddoOfficeDAOImpl.getListByColumnAndValue("dcpsDdoOfficeIdPk", citycode);
						//getCityVOByCityCodeAndLangId(citycode, langId);
						logger.info("city code for city object set----"+objCmnCityMst.getDcpsDdoCode());
						logger.info("city ID for city object set----"+objCmnCityMst.getDcpsDdoOfficeIdPk());
					}
				}

				CmnLocationMst objCmnLocationMst = new CmnLocationMst();
				objCmnLocationMst=null;
				List locSrchList = new ArrayList();
				logger.info("location code from list box is -----"+locationCode);
				// Setting location object according to value selected by user from location listbox
				if(!"".equals(locationCode) && !"-1".equals(locationCode))
				{	
					logger.info("location code found-----setting object"+locationCode);
					objCmnLocationMst = objCmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId(locationCode,langId);
					logger.info("location code for location object set----"+objCmnLocationMst.getLocationCode());
					logger.info("location ID for location object set----"+objCmnLocationMst.getLocId());
					if(!"Yes".equals(childLocSrchParam))
					{
						locSrchList.add(objCmnLocationMst);
					}
				}
				// logged in location is taken as default if user doesn't select a location
				//if(districtNullflag==0 && cityNullFlag==0)
				if(cityNullFlag==0)
				{
					logger.info("no location selected by user-----setting looged in location ");
					// reading langid specific location object in case user doesn't specify location search criteria
					objCmnLocationMst=objCmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId
					(objCmnLocationMstDaoImpl.read(loggedInlocationId).getLocationCode(), langId);
					logger.info("location code for location object set----"+objCmnLocationMst.getLocationCode());
					logger.info("location ID for location object set----"+objCmnLocationMst.getLocId());
					if(!"Yes".equals(childLocSrchParam))
					{
						locSrchList.add(objCmnLocationMst);
					}
				}
				if(hidnSpecLocId != null && !"".equals(hidnSpecLocId.trim()))
				{
					logger.info("some hidden specific Location present in hidden field");
					// reading langid specific location object in case user doesn't specify location search criteria
					objCmnLocationMst = objCmnLocationMstDaoImpl.read(Long.valueOf(hidnSpecLocId));
					logger.info("location code for location object set----"+objCmnLocationMst.getLocationCode());
					logger.info("location ID for location object set----"+objCmnLocationMst.getLocId());
					if(!"Yes".equals(childLocSrchParam))
					{
						locSrchList.add(objCmnLocationMst);
					}
				}

				if("Yes".equals(childLocSrchParam))
				{
					logger.info("Search in child location also");
					if(objCmnLocationMst != null)
					{
						logger.info("Location object exists");
						locSrchList = objCmnLocationMstDaoImpl.getChildLocations(objCmnLocationMst.getLocId());
						logger.info("Search in child location also and child location list size is "+locSrchList.size());
						locSrchList.add(objCmnLocationMst);
					}
					else
					{
						logger.info("Considering logged in location");
						objCmnLocationMst = objCmnLocationMstDaoImpl.getLocationVOByLocationCodeAndLangId
						(objCmnLocationMstDaoImpl.read(loggedInlocationId).getLocationCode(), langId);
						locSrchList = objCmnLocationMstDaoImpl.getChildLocations(objCmnLocationMst.getLocId());
						logger.info("Search in child location also and child location list size is "+locSrchList.size());
						locSrchList.add(objCmnLocationMst);
					}
				}

				// Setting Designation object for search
				OrgDesignationMstDaoImpl objOrgDsgnImpl = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,
						serv.getSessionFactory());
				OrgDepartmentMstDao objOrgDepttImpl = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class,
						serv.getSessionFactory());
				List dsgnCodes = new ArrayList();
				//List depttCodes = new ArrayList();

				OrgDesignationMst objOrgDesignationMst= new OrgDesignationMst();		
				objOrgDesignationMst = null;		
				if(!"".equals(dsgncode) && !"-1".equals(dsgncode))
				{	
					logger.info("-----designation code in if-----"+dsgncode);
					objOrgDesignationMst = objOrgDsgnImpl.getDesignationVOByDsgnCodeAndLangId(dsgncode, langId);
				}
				if(hidnSpecDsgnCodes!=null && !"".equals(hidnSpecDsgnCodes))
				{
					logger.info("hidden for designation codes");
					if(hidnSpecDsgnCodes.indexOf(',')!= -1)
					{
						String[] hidnDsgnCodesArr = hidnSpecDsgnCodes.split(",");
						dsgnCodes = Arrays.asList(hidnDsgnCodesArr);
						objEmpSrchCrit.setDsgnSpecCodesList(dsgnCodes);
					}
					dsgnList = objOrgDsgnImpl.getDesignationByDesigCodesAndLang(dsgnCodes,langId);
				}
				else
				{
					dsgnList = objOrgDsgnImpl.getAllDesignation(objectArgs,langId);
				}
				if(hiddenSpecDepttCodes!=null && !"".equals(hiddenSpecDepttCodes))
				{
					logger.info("hiddenSpecDepttCodes for designation codes");
					depttCodes = objOrgDepttImpl.getAllDepttForDeppCodes(hiddenSpecDepttCodes,langId,"depttIds");
					objEmpSrchCrit.setDepttSpecCodesList(depttCodes);
				}

				//Setting Language object for search according to login language
				CmnLanguageMst objCmnLanguageMst=null;
				CmnLanguageMstDaoImpl objCmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				objCmnLanguageMst=objCmnLanguageMstDaoImpl.read(langId);

				logger.info("---language set----post now----->>>");
				OrgPostDetailsRltDaoImpl detailsRltDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
				Long userId = (Long)loginMap.get("userId");
				OrgPostDetailsRlt objOrgPostDetailsRlt = detailsRltDaoImpl.getPostDetailsRltFromUserIdAndLangId(userId, langId);
				OrgPostMst objLoggedInPostMst = objOrgPostDetailsRlt.getOrgPostMst();
				logger.info("logged in post level id----->>>>"+objLoggedInPostMst.getPostLevelId());
				objectArgs.put("post", objLoggedInPostMst.getPostLevelId());
				objEmpSrchCrit.setLocSrchList(locSrchList);
				objEmpSrchCrit.setCmnLanguageMst(objCmnLanguageMst);
				objEmpSrchCrit.setOrgDesignationMst(objOrgDesignationMst);
				objEmpSrchCrit.setOrgEmpMst(objOrgEmpMst);
				//objEmpSrchCrit.setCmnDistrictMst(objCmnDistrictMst);
			//	objEmpSrchCrit.setCmnCityMst(objCmnCityMst);
				objEmpSrchCrit.setOrgPostMst(objLoggedInPostMst);
				objEmpSrchCrit.setEmpServiceExpDateFlag(hidnEmpExpDateSrchFlag);


				List<CmnBranchMst> cmnBranchMstLst=cmnBranchMstDaoImpl.getListByColumnAndValue("branchCode",BranchCode);
				if(!cmnBranchMstLst.isEmpty())
				{	
					for(int i=0;i<cmnBranchMstLst.size();i++)
					{
						if(cmnBranchMstLst.get(i).getCmnLanguageMst().getLangId()==langId)
						{
							objEmpSrchCrit.setCmnBranchMst(cmnBranchMstLst.get(i));
						}
					}
					objectArgs.put("loggedInBranchCode",cmnBranchMstLst.get(0).getBranchCode());
				}
				paginatelist.setRequest(request);
				String page= request.getParameter("page");
				logger.info("page aaaaaaa"+page);

				if(page != null)
				{
					page = request.getParameter("page"); 

				} 
				else
				{
					page="1";
				}


				paginatelist = paginatelist.getPaginatedListObject(Integer.parseInt(numberResultsPerPage),page);

				int startIndex=paginatelist.getFirstRecordIndex();
				int pageSize = paginatelist.getPageSize();
				List<OrgEmpMst> searchEmpListcount  = empImpl.SearchEmployeeUpdate(objEmpSrchCrit,startIndex,pageSize,false);
				count = Integer.valueOf(searchEmpListcount.size());
				searchEmpList = empImpl.SearchEmployeeUpdate(objEmpSrchCrit,startIndex,pageSize,true);	
				logger.info("startIndex  in searchEmployeeByAll===>"+ startIndex);
				logger.info("pageSize  in searchEmployeeByAll===>"+ pageSize);
				logger.info("page no in searchEmployeeByAll===>"+page);
				logger.info("searchEmpListcount in searchEmployeeByAll==>"+searchEmpListcount.size());
				logger.info("SearchEmpList in searchEmployeeByAll:-->"+searchEmpList.size());
				//searchEmpList = empImpl.SearchEmployeeNew(objOrgEmpMst, objCmnLocationMst,objCmnLanguageMst,objOrgDesignationMst,objCityMst,objCmnDistrictMst );
				logger.info("SearchEmpList:-->"+searchEmpList.size());


				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				String OfficeName = payBillDAOImpl.getOffice(locId);
				objectArgs.put("OfficeName",OfficeName);
				
				

				if("true".equals(ajaxCallFlag))
				{
					StringBuffer srchResult = new StringBuffer();
					logger.info("ajax calling is true");
					srchResult.append("<srchResults>");
					if(searchEmpList.size() == 1)
					{
						logger.info("result list size is 1");
						EmployeeSearchVO resultEmpVo = new EmployeeSearchVO();
						resultEmpVo = getEmployeeSearchVO((OrgEmpMst)searchEmpList.get(0), objectArgs);

						srchResult.append("<empSrchName>").append(empSrchName).append("</empSrchName>");
						srchResult.append("<srchResNo>").append("One").append("</srchResNo>"); 
						srchResult.append("<empId>").append(resultEmpVo.getEmpId()).append("</empId>");
						
						srchResult.append("<empName>").append(resultEmpVo.getEmpPrf()+"."+
								resultEmpVo.getEmpFname() + " " + resultEmpVo.getEmpLname()).append("</empName>");
						//srchResult.append("<empLoc>").append(resultEmpVo.getLocPlacename())	.append("</empLoc>");
						srchResult.append("<empLoc>").append(OfficeName).append("</empLoc>");
						srchResult.append("<empLocDistr>").append(resultEmpVo.getLocDistrictName())
						.append("</empLocDistr>");
						srchResult.append("<empDesig>").append(resultEmpVo.getDsgnName())
						.append("</empDesig>");
						srchResult.append("<empUserName>").append(resultEmpVo.getUserName())
						.append("</empUserName>");
						srchResult.append("<empUserId>").append(resultEmpVo.getUserId())
						.append("</empUserId>");
						srchResult.append("<empFuncName>").append(functionName)
						.append("</empFuncName>");
						srchResult.append("<empPostId>").append(resultEmpVo.getPostId())
						.append("</empPostId>");
					}
					else if (searchEmpList.isEmpty())
					{
						srchResult.append("<empSrchName>").append(empSrchName).append("</empSrchName>");
						srchResult.append("<srchResNo>").append("Zero").append("</srchResNo>"); 


					}
					else
					{
						srchResult.append("<empSrchName>").append(empSrchName).append("</empSrchName>");
						srchResult.append("<srchResNo>").append("MoreThanOne").append("</srchResNo>"); 
						srchResult.append("<empId>").append("").append("</empId>");
						srchResult.append("<empName>").append("").append("</empName>");
						srchResult.append("<empLoc>").append("").append("</empLoc>");
						srchResult.append("<empLocDistr>").append("").append("</empLocDistr>");
						srchResult.append("<empDesig>").append("").append("</empDesig>");
						srchResult.append("<empUserName>").append("").append("</empUserName>");
						srchResult.append("<empUserId>").append("").append("</empUserId>");
						srchResult.append("<empFuncName>").append("").append("</empFuncName>");
						srchResult.append("<empPostId>").append("").append("</empPostId>");
					}
					srchResult.append("</srchResults>");	

					String tempStr = new AjaxXmlBuilder().addItem("key_ajax", srchResult.toString()).toString();
					logger.info("generated search result xml is "+tempStr);
					objectArgs.put("ajaxKey", tempStr);
					resultObject.setResultCode(ErrorConstants.SUCCESS);
					resultObject.setResultValue(objectArgs);
					resultObject.setViewName("ajaxData");
					return resultObject;
				}
			}
			 Following code is executed when employee service is called to view employee details 
		   for a particular employee 
			else
			{
				logger.info("Employee service has been called to view Employee Details--->");
				employeeId=(String)objectArgs.get("employeeId");		
				SearchEmployeekey=(String)objectArgs.get("SearchEmployee");
				logger.info("Employee service has been called to view Employee Details--->"+employeeId);
				logger.info("Employee service has been called to view Employee SearchEmployeekey--->"+SearchEmployeekey);
				
				 * following code snippet is used to return employee record for
				 * requested langId.
				 
				if(objectArgs.get("reqLangId") != null)
				{
					Long reqLangId = (Long) objectArgs.get("reqLangId");
					if (reqLangId != null)// TODO -- check for other values
					{
						langId = reqLangId;
					}
				}
				// logger.info("employeeId:-->"+employeeId);
				//	logger.info("key:-->"+SearchEmployeekey);
				tempempId=employeeId;
				if(tempempId!= null)
				{
					lngEmpId = Long.valueOf(tempempId).longValue();
				}
				boolFromSession=true;
				objOrgEmpMst.setEmpId(lngEmpId);
				objectArgs.put("SearchEmployee",SearchEmployeekey);
				logger.info("boolean  from session-----VIew mode yes-----"+boolFromSession);
				//OrgEmpMst objEmpMstForView = empImpl.getEmployeeForView(objOrgEmpMst, objOrgEmpMst.getCmnLanguageMst().getLangId());
				OrgEmpMst objEmpMstForView = empImpl.read(objOrgEmpMst.getEmpId());
				searchEmpList.add(objEmpMstForView);
			}

			//following code calls static method which returns a list of EmployeeSearch Objects
			finalEmpList = PayrollEmpSearchService.createEmployeeSearchResultList(searchEmpList, boolFromSession, objectArgs, langId);


			//Retieving district and designation listbox values from database and putting the same in map
			//List<CmnDistrictMst> districtlist = objDistrictMst.getAllDistrict(objectArgs, langId);


			//get all branch of location
		CmnBranchlocMpgDaoImpl cmnBranchlocMpgDaoImpl=new CmnBranchlocMpgDaoImpl(CmnBranchlocMpg.class,serv.getSessionFactory());
		List<CmnBranchlocMpg> cmnBranchLocLst=cmnBranchlocMpgDaoImpl.getListByColumnAndValue("","");

			
			//objectArgs.put("districtlist",districtlist);	
			objectArgs.put("dsgnList", dsgnList);
			logger.info("employee search fetched the following number of results:::-->"+finalEmpList.size());
			objectArgs.put("finalEmpList", finalEmpList);
			logger.info("finalEmpList size"+finalEmpList.size());
			paginatelist.setList(finalEmpList);
			paginatelist.setTotalNumberOfRows(count);
			objectArgs.put("paginatelist", paginatelist);
			following code is for view functionality of employee.It will put the required employee details in 
		session and forward to calling service
			
			
			if(boolFromSession)
			{
				logger.info("employee session key:-->"+"empSearchVO"+SearchEmployeekey);		 
				if (!finalEmpList.isEmpty()) 
				{
					objectArgs.put("empSearchVO"+SearchEmployeekey, finalEmpList.get(0));
					resultObject.setSessionValues("empSearchVO"+SearchEmployeekey, finalEmpList.get(0));
					logger.info("after put in session:--> "+finalEmpList.size());
				}
			}
			String requesturi="./hdiits.htm?actionFlag=searchEmployeeByAll";
			objectArgs.put("requesturi", requesturi);
			resultObject.setResultValue(objectArgs);
			logger.info("Processing in searchEmployeesByAll method of EMPServiceImpl is over");	

			resultObject.setViewName("findemployee");
		}
		catch (Exception e)
		{
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error( "ERROR : Some exception has occured...process cannot continue",e);
		}
		return resultObject;
	}
*/

	/**
	 * This method returns a list of EmployeeSearchVOs.
	 * @author 206152
	 * @param  List searchEmpList,Boolean boolFromSession,Map objectArgs,Long langId
	 * @throws Exception 
	 * @return ArrayList
	 * 
	 *  
	 */	

	/*public static List<EmployeeSearchVO> createEmployeeSearchResultList(List searchEmpList,Boolean boolFromSession,Map objectArgs,Long langId) throws Exception
	{
		try 
		{
			List employeeVOList = new ArrayList();
			EmployeeSearchVO objEmpSearchVO = new EmployeeSearchVO();
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			//CmnDistrictMstDAOImpl objDistrictMst = new CmnDistrictMstDAOImpl(CmnDistrictMst.class, serv.getSessionFactorySlave());
			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());
			long userId = 0;
			long empId = 0;
			String empPrf= "";
			String empFname= "";
			String empMname= "";
			String empLname= "";

			Follwing code iterates the list of employess returned from empDaoImpl.It extracts the values and 
		puts the same in EmployeeSearchVOs.These objects are put in an arraylist
			if(!searchEmpList.isEmpty())
			{		
				Iterator empIterator = searchEmpList.iterator();
				while (empIterator.hasNext())
				{				
					OrgEmpMst orgEmpMst = (OrgEmpMst) empIterator.next();
					if(boolFromSession)
					{
						orgEmpMst = empImpl.getEngGujEmployee(orgEmpMst, langId);
					}
					userId=orgEmpMst.getOrgUserMst().getUserId();

					 //code to display guj when billigual sarch apply
				 logger.info("user iddddddddd==>"+userId);
				 logger.info("lang iddddddddd==>"+langId);
				 EmpDAOImpl empDAOImpl=new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				 List<OrgEmpMst> list=new ArrayList<OrgEmpMst>();
				 String[] strarr=new String[2];
				 Object[] valarr=new Object[2];
				 strarr[0]="cmnLanguageMst.langId";
				 strarr[1]="orgUserMst.userId";
				 valarr[0]=langId;

				 if(userId!=0)
				 {

					 valarr[1]=userId;
					 list=empDAOImpl.getListByColumnAndValue(strarr, valarr);
					 logger.info("list size"+list.size());
					 if(!list.isEmpty())
					 {
						 for(int i=0;i<list.size();i++)
						 {
							 if(list.get(i).getCmnLanguageMst().getLangId()==langId)
							 {
								 orgEmpMst= list.get(i);
								 logger.info("emp id in if"+orgEmpMst.getEmpId());
							 }
						 }
					 }
				 }

				 //code to display guj when billigual sarch apply
					 		    	
					 empId=orgEmpMst.getEmpId();
					 logger.info("empId iddddddddd==>"+empId);
					 
					 Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
						long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
						PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
						String officeName = payBillDAOImpl.getOffice(locId);
						objectArgs.put("OfficeName",officeName);
						
						
						//List<OrgEmpMst> userList = payBillDAOImpl.getUserList(empId);
						//long userId = userList.get(0).getOrgUserMst().getUserId();
						
						
					 
					 empPrf=orgEmpMst.getEmpPrefix();
					 empFname=orgEmpMst.getEmpFname();
					 empMname=orgEmpMst.getEmpMname();
					 empLname=orgEmpMst.getEmpLname();
					  	 logger.info("employee details -------"+orgEmpMst.getEmpLname());
		    	 logger.info("employee details -------"+orgEmpMst.getEmpFname());
		    	 logger.info("employee details -------"+orgEmpMst.getEmpMname());
		    	 logger.info("employee details -------"+orgEmpMst.getCmnLanguageMst().getLangId());

					 String userName="";
					 if(orgEmpMst.getEmpGPFnumber()!=null)
					 {
						 userName = orgEmpMst.getEmpGPFnumber(); 
					 }


					 //	 logger.info("GPF Name:-->"+userName);

					 objEmpSearchVO = new EmployeeSearchVO();                 	 	
					 objEmpSearchVO.setEmpPrf(empPrf);
					 objEmpSearchVO.setEmpFname(empFname);
					 objEmpSearchVO.setEmpMname(empMname);
					 objEmpSearchVO.setEmpLname(empLname);
					 objEmpSearchVO.setEmpId(empId);
					 objEmpSearchVO.setUserId(userId);
					 objEmpSearchVO.setUserName(userName);
					 Set userPostRlts = orgEmpMst.getOrgUserMst().getOrgUserpostRlts();
					 logger.info("emp idddddddddddd==>"+orgEmpMst.getEmpId());
					 // checking for userPostRlts for an employee
					 
					 	
						
						
					 if(userPostRlts!=null && !userPostRlts.isEmpty())
					 {
						 logger.info("user post rlts for employee----size:::::  "+userPostRlts.size());
						 Iterator iterator = userPostRlts.iterator();
						 while(iterator.hasNext())
						 {
							 logger.info("---in while---");
							 OrgUserpostRlt orgUserpostRlt = (OrgUserpostRlt)iterator.next();
							 logger.info(" pront usei " + orgUserpostRlt.getOrgUserMst().getUserId());
							 OrgPostMst orgPostMst = orgUserpostRlt.getOrgPostMstByPostId();
							 //checking for postMst in userPostRlt
							 if(orgPostMst.getOrgPostDetailsRlt()!=null & orgPostMst.getOrgPostDetailsRlt().size()>0)
							 {
								 logger.info("user post rlts for User----size:::::  "+orgPostMst.getOrgPostDetailsRlt().size());
								 Iterator postIterator = orgPostMst.getOrgPostDetailsRlt().iterator();
								 logger.info("orgPostMst.getOrgPostDetailsRlt() " + orgPostMst.getOrgPostDetailsRlt().size());

								 while(postIterator.hasNext())
								 {
									 logger.info("in user post while");
									 OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt)postIterator.next();
									 logger.info(" ininin  " + orgPostDetailsRlt.getOrgPostMst().getPostId());
									 
									 long locID = orgPostDetailsRlt.getCmnLocationMst().getLocId();
									 
									  officeName = payBillDAOImpl.getOffice(locID);
									  
									  List<HrPayGpfBalanceDtls> gpfAccountNoList = payBillDAOImpl.getGpfAcctNo(locID);
									  
									  String gpfAccountNo ="";
									  if(gpfAccountNoList.size()>0)
									  gpfAccountNo = gpfAccountNoList.get(0).getGpfAccNo();
									  
									  logger.info("officename********" + officeName);
									  logger.info("gpfAccountNo********" + gpfAccountNo);
									  
									 
									 //checking for nullability and primary post in orgPstDetailsMst
									 if(orgPostDetailsRlt.getOrgPostMst()!=null && 
											 orgPostDetailsRlt.getOrgPostMst().getCmnLookupMst().getLookupName().equals("Primary_Post"))
									 {
										 logger.info("--primary post found--setting custom object");
										 //Retirving langId specific designation details
										 if(orgPostDetailsRlt.getOrgDesignationMst().getCmnLanguageMst().getLangId()==langId)
										 {
											 objEmpSearchVO.setDsgnName(orgPostDetailsRlt.getOrgDesignationMst().getDsgnName());
										 }
										 //Retirving langId specific location and district details



										 if(orgPostDetailsRlt.getCmnLocationMst().getCmnLanguageMst().getLangId()==langId)
										 {
											 objEmpSearchVO.setLocPlacename(orgPostDetailsRlt.getCmnLocationMst().getLocName());
											// objEmpSearchVO.setLocDistrictName(objDistrictMst.read(orgPostDetailsRlt.getCmnLocationMst().getLocDistrictId()).getDistrictName());
										 }
										 objEmpSearchVO.setPostId(orgPostDetailsRlt.getOrgPostMst().getPostId());
									 }
								 }
							 }
						 } 
					 }
					 employeeVOList.add(objEmpSearchVO);  
				}
			}

			return employeeVOList;
		}
		catch (Exception e) 
		{
			logger.info("Exception occured in createEmployeeSearchResultList method of EMPServiceImpl"+e);
			throw e;
		}
	}

	*/
	
	
	public static List<SearcheEmployeeCustomVO> createEmployeeSearchResultList(List searchEmpList,Boolean boolFromSession,Map objectArgs,Long langId) throws Exception
	{
		try 
		{
			List employeeVOList = new ArrayList();
			 
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			//CmnDistrictMstDAOImpl objDistrictMst = new CmnDistrictMstDAOImpl(CmnDistrictMst.class, serv.getSessionFactorySlave());
			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());
			long userId = 0;
			long empId = 0;
			String empPrf= "";
			String empFname= "";
			String empMname= "";
			String empLname= "";

			/*Follwing code iterates the list of employess returned from empDaoImpl.It extracts the values and 
		puts the same in EmployeeSearchVOs.These objects are put in an arraylist*/
			if(!searchEmpList.isEmpty())
			{		
				Iterator empIterator = searchEmpList.iterator();
				SearcheEmployeeCustomVO objEmpSearchVO = null;
				while (empIterator.hasNext())
				{	
					
					
					objEmpSearchVO =new  SearcheEmployeeCustomVO();
					OrgEmpMst orgEmpMst = (OrgEmpMst) empIterator.next();
					if(boolFromSession)
					{
						orgEmpMst = empImpl.getEngGujEmployee(orgEmpMst, langId);
					}
					userId=orgEmpMst.getOrgUserMst().getUserId();

				 //code to display guj when billigual sarch apply
					 		    	
					 empId=orgEmpMst.getEmpId();
					 logger.info("empId iddddddddd==>"+empId);
					 
					 PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
					 
					 String gpfAccountNo ="";
					 String pfSeries ="";
					List<HrPayGpfBalanceDtls> gpfAccountNos =  null;
					gpfAccountNos = payBillDAOImpl.getGpfAccoNos(userId);

					
						
					//added by abhilash for gpfacctno and pfseries
					
					/*if(gpfAccountNos.size()>0)
					{
						gpfAccountNo= gpfAccountNos.get(0).getGpfAccNo();
						objEmpSearchVO.setGpfAccountNo(gpfAccountNo);
					}
					else
					{
						gpfAccountNo="-";
						objEmpSearchVO.setGpfAccountNo(gpfAccountNo);
					}*/
					
					if(gpfAccountNos.size()>0)
					{

						if(gpfAccountNos.get(0).getGpfAccNo()!=null)
						{
							gpfAccountNo=gpfAccountNos.get(0).getGpfAccNo();
						}
						if(gpfAccountNos.get(0).getPfSeries()!=null)
						{
							pfSeries = gpfAccountNos.get(0).getPfSeries();
						}

						if(gpfAccountNos.get(0).getGpfAccNo()!=null && gpfAccountNos.get(0).getPfSeries()!=null) 
						{
							if(pfSeries.equalsIgnoreCase("dcps"))
							{
								gpfAccountNo = gpfAccountNo;
							}
							else
							{
							gpfAccountNo = pfSeries+"/"+gpfAccountNo;
							}
							objEmpSearchVO.setGpfAccountNo(gpfAccountNo);
						}
						else if(gpfAccountNos.get(0).getGpfAccNo()==null && gpfAccountNos.get(0).getPfSeries()==null) 
						{
							gpfAccountNo = pfSeries+"/"+gpfAccountNo;
							objEmpSearchVO.setGpfAccountNo(gpfAccountNo);
						}
						else if(gpfAccountNos.get(0).getGpfAccNo()!=null && gpfAccountNos.get(0).getPfSeries()==null)
						{
							gpfAccountNo=gpfAccountNo;
							objEmpSearchVO.setGpfAccountNo(gpfAccountNo);
						}
						else if(gpfAccountNos.get(0).getGpfAccNo()==null && gpfAccountNos.get(0).getPfSeries()!=null)
						{
							gpfAccountNo=pfSeries;
							objEmpSearchVO.setGpfAccountNo(gpfAccountNo);
						}

					}
					else
					{
						gpfAccountNo="-";
						objEmpSearchVO.setGpfAccountNo(gpfAccountNo);
					}
					//ended by abhilash for gpfacctno and pfseries
					
					
					//added by abhilash for sevarth id
					
					List empList = empImpl.getEmpListFromHreisempMst(empId);
					logger.info("empList:::::::::::::: " +empList.size());
					long EmployeeId =0;
					String sevarthEmpCode ="";
					
					for(Iterator itr=empList.iterator();itr.hasNext();)
					{
						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							EmployeeId = Long.parseLong(rowList[0].toString().trim());
							sevarthEmpCode = rowList[1].toString().trim();
							logger.info("sevarthEmpCode in Employee search more than one employee******" + sevarthEmpCode);
							logger.info("EmployeeId in Employee search for morethan one employee::::::::::::::" + EmployeeId);
							objEmpSearchVO.setSevarthEmpCode(sevarthEmpCode);
						}
						if(rowList[1] != null)
						{
							sevarthEmpCode = rowList[1].toString().trim();
							logger.info("sevarthEmpCode for more than one employee******" + sevarthEmpCode);
							objEmpSearchVO.setSevarthEmpCode(sevarthEmpCode);
						}
					
					}
					
					logger.info("EmployeeId******" + EmployeeId);
					
					//ended by abhilash for sevarth id
					
					 
					 
					 
					 	Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
						long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
						
						//String officeName = payBillDAOImpl.getOffice(locId);
						//objectArgs.put("OfficeName",officeName);
						
						 String officeName ="";
						List<DdoOffice> officeList =  null;
						
						officeList = payBillDAOImpl.getOfficeListFromEmpId(empId, locId);
						
						if(officeList.size()>0)
						{
							officeName= officeList.get(0).getDcpsDdoOfficeName();
							 objEmpSearchVO.setOfficeName(officeName);
						}
						else
						{
							officeName="-";
							 objEmpSearchVO.setOfficeName(officeName);
						}
						
						  logger.info("officename********" + officeName);
						  
						
					 
					 empPrf=orgEmpMst.getEmpPrefix();
					 empFname=orgEmpMst.getEmpFname();
					 empMname=orgEmpMst.getEmpMname();
					 empLname=orgEmpMst.getEmpLname();
					 
					 
					 StringBuffer stringBuffer = new StringBuffer();
					 
					 stringBuffer.append(empPrf);
					 stringBuffer.append(' ');
					 stringBuffer.append(empFname);
					 stringBuffer.append(' ');
					 stringBuffer.append(empMname);
					 stringBuffer.append(' ');
					 stringBuffer.append(empLname);
					 
					 String empFullname = stringBuffer.toString();
					 
					 
			

					 String userName="";
					 if(orgEmpMst.getEmpGPFnumber()!=null)
					 {
						 userName = orgEmpMst.getEmpGPFnumber(); 
					 }

					   	 	
					 objEmpSearchVO.setEmpPrf(empPrf);
					 objEmpSearchVO.setEmpFname(empFname);
					 objEmpSearchVO.setEmpMname(empMname);
					 objEmpSearchVO.setEmpLname(empLname);
					 objEmpSearchVO.setEmpId(empId);
					 objEmpSearchVO.setUserId(userId);
					 objEmpSearchVO.setUserName(userName);
					 
					 objEmpSearchVO.setEmpFullname(empFullname);
					 
					 
					 Set userPostRlts = orgEmpMst.getOrgUserMst().getOrgUserpostRlts();
					 logger.info("emp idddddddddddd==>"+orgEmpMst.getEmpId());
					 // checking for userPostRlts for an employee
					 
					 	
						
						
					 if(userPostRlts!=null && !userPostRlts.isEmpty())
					 {
						 logger.info("user post rlts for employee----size:::::  "+userPostRlts.size());
						 Iterator iterator = userPostRlts.iterator();
						 while(iterator.hasNext())
						 {
							 logger.info("---in while---");
							 OrgUserpostRlt orgUserpostRlt = (OrgUserpostRlt)iterator.next();
							 logger.info(" pront usei " + orgUserpostRlt.getOrgUserMst().getUserId());
							 OrgPostMst orgPostMst = orgUserpostRlt.getOrgPostMstByPostId();
							 //checking for postMst in userPostRlt
							 if(orgPostMst.getOrgPostDetailsRlt()!=null & orgPostMst.getOrgPostDetailsRlt().size()>0)
							 {
								 logger.info("user post rlts for User----size:::::  "+orgPostMst.getOrgPostDetailsRlt().size());
								 Iterator postIterator = orgPostMst.getOrgPostDetailsRlt().iterator();
								 logger.info("orgPostMst.getOrgPostDetailsRlt() " + orgPostMst.getOrgPostDetailsRlt().size());

								 while(postIterator.hasNext())
								 {
									 logger.info("in user post while");
									 OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt)postIterator.next();
									 logger.info(" ininin  " + orgPostDetailsRlt.getOrgPostMst().getPostId());
									 
									 long locID = orgPostDetailsRlt.getCmnLocationMst().getLocId();
									 
									//  officeName = payBillDAOImpl.getOffice(locID);
									  
									  logger.info("locIDlocIDlocID" + locID);
									  
									//  List<HrPayGpfBalanceDtls> gpfAccountNoList = payBillDAOImpl.getGpfAcctNo(locID);
									  
									
									 
									 //checking for nullability and primary post in orgPstDetailsMst
									 if(orgPostDetailsRlt.getOrgPostMst()!=null && 
											 orgPostDetailsRlt.getOrgPostMst().getCmnLookupMst().getLookupName().equals("Primary_Post"))
									 {
										 logger.info("--primary post found--setting custom object");
										 //Retirving langId specific designation details
										 if(orgPostDetailsRlt.getOrgDesignationMst().getCmnLanguageMst().getLangId()==langId)
										 {
											 objEmpSearchVO.setDsgnName(orgPostDetailsRlt.getOrgDesignationMst().getDsgnName());
										 }
										
										 if(orgPostDetailsRlt.getCmnLocationMst().getCmnLanguageMst().getLangId()==langId)
										 {
											 objEmpSearchVO.setLocPlacename(orgPostDetailsRlt.getCmnLocationMst().getLocName());
											// objEmpSearchVO.setLocDistrictName(objDistrictMst.read(orgPostDetailsRlt.getCmnLocationMst().getLocDistrictId()).getDistrictName());
										 }
										 objEmpSearchVO.setPostId(orgPostDetailsRlt.getOrgPostMst().getPostId());
									 }
								 }
							 }
						 } 
					 }
					 employeeVOList.add(objEmpSearchVO);  
				}
			}

			return employeeVOList;
		}
		catch (Exception e) 
		{
			logger.info("Exception occured in createEmployeeSearchResultList method of EMPServiceImpl"+e);
			throw e;
		}
	}

	
	
	/**
	 * This method is used to get List of All policeStation on base of City Code
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */	

	public ResultObject getAllPoliceStation(Map objectArgs)
	{
		logger.info("--getAllPoliceStation srvc start--");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR, "FAIL");
		StringBuffer sbLocationValues = new StringBuffer(250);
		Map result = new HashMap();
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");   
		long strCityId=0;
		try
		{
			ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");
			CmnDistrictMstDAO objDistrictMstDaoImpl = new CmnDistrictMstDAOImpl(CmnDistrictMst.class,
					servLoc.getSessionFactorySlave());
			String strParameter = StringUtility.getParameter("cityCode",request);
			String strDistrCode = StringUtility.getParameter("districtCodeCity",request);
			logger.info("city code:"+strParameter);
			logger.info("strDistrCode code:"+strDistrCode);
			if (objRes == null || objectArgs == null)
			{
				objRes.setResultCode(-1);
				return objRes;
			}

			Map loginMap=(Map)objectArgs.get("baseLoginMap");

			CmnCityMstDAOImpl cityImpl = new CmnCityMstDAOImpl(CmnCityMst.class,servLoc.getSessionFactorySlave()) ;
			CmnCityMst objCityMst = cityImpl.getCityVOByCityCodeAndLangId(strParameter, (Long)loginMap.get("langId"));
			CmnLocationMstDao objCmnLocDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,
					servLoc.getSessionFactorySlave());
			if(objCityMst!= null)
			{
				logger.info("city master object is not null city id is"+objCityMst.getCityId());
				strCityId = objCityMst.getCityId();
			}
			logger.info("city id for search is "+strCityId);



			String str[] = new String[2];
			Object val[] = new Object[2];
			str[0]="activateFlag";
			val[0]=1l;
			str[1]="locCityId";
			val[1]=strCityId;

			List<CmnLocationMst> locationList = objCmnLocDaoImpl.getListByColumnAndValue(str, val);
			logger.info("locationList with city id is "+locationList.size());

			CmnDistrictMst objDistrictMst = objDistrictMstDaoImpl.getDistrictVOByDistrictCodeAndLangId
			(strDistrCode, (Long)loginMap.get("langId"));
			if(locationList.isEmpty() && objDistrictMst!=null)
			{
				str[1]="locDistrictId";
				val[1]= Long.valueOf(objDistrictMst.getDistrictId());
				locationList = objCmnLocDaoImpl.getListByColumnAndValue(str,val);
				logger.info("retrieving locations from district as no city locations exist"+locationList.size());
			}
			// logger.info("locationList: -->"+locationList);
			Iterator itr = locationList.iterator();
			while (itr.hasNext())
			{
				CmnLocationMst locationMst = (CmnLocationMst) itr.next();
				sbLocationValues.append("<element>");
				sbLocationValues.append("<elecode>").append("<![CDATA[").append(locationMst.getLocationCode())
				.append("]]>").append("</elecode>");
				sbLocationValues.append("<elename>").append("<![CDATA[").append(locationMst.getLocName())
				.append("]]>").append("</elename>");
				sbLocationValues.append("</element>");
			}

			//get branch alll brnach list
			long locId=0;
			logger.info("locCode==>"+StringUtility.getParameter("locCode",request));
			if(StringUtility.getParameter("locCode",request)!=null && !StringUtility.getParameter("locCode",request).toString().equals(""))
			{	
				logger.info("call iff***************");
				String locCode=StringUtility.getParameter("locCode",request).toString();
				List<CmnLocationMst>  list= objCmnLocDaoImpl.getListByColumnAndValue("locationCode", locCode);
				if(!list.isEmpty())
				{
					for(int i=0;i<list.size();i++)
					{
						if((Long)loginMap.get("langId")==list.get(i).getCmnLanguageMst().getLangId())
						{
							locId=list.get(i).getLocId();
						}
					}
				}

			}
			else
			{
				logger.info("call elssss**********");
				locId=Long.parseLong(loginMap.get("locationId").toString());
			}	
			String str1[] = new String[3];
			Object val1[] = new Object[3];
			str1[0]="activateFlag";
			val1[0]=1l;
			str1[1]="cmnLanguageMst.langId";
			val1[1]=(Long)loginMap.get("langId");

			logger.info("location iddd"+locId);

			CmnLocationMst cmnLocationMst=objCmnLocDaoImpl.read(locId);

			CmnBranchlocMpgDaoImpl cmnBranchlocMpgDaoImpl=new CmnBranchlocMpgDaoImpl(CmnBranchlocMpg.class,servLoc.getSessionFactorySlave());
			List<CmnBranchlocMpg> cmnBranchLocLst=new ArrayList<CmnBranchlocMpg>();

			CmnBranchMstDaoImpl cmnBranchMstDaoImpl=new CmnBranchMstDaoImpl(CmnBranchMst.class,servLoc.getSessionFactorySlave());
			List<CmnBranchMst> cmnBranchMstLst=new ArrayList<CmnBranchMst>();
			if(cmnLocationMst!=null)
			{
				logger.info("loccode==>"+cmnLocationMst.getLocationCode());
				cmnBranchLocLst=cmnBranchlocMpgDaoImpl.getListByColumnAndValue("locationCode",String.valueOf(cmnLocationMst.getLocationCode()));
			}


			if(!cmnBranchLocLst.isEmpty())
			{
				for(int i=0;i<cmnBranchLocLst.size();i++)
				{
					String branchCode=cmnBranchLocLst.get(i).getBranchCode();
					str1[2]="branchCode";
					val1[2]=branchCode;
					cmnBranchMstLst=cmnBranchMstDaoImpl.getListByColumnAndValue(str1, val1);
					if(!cmnBranchMstLst.isEmpty())
					{
						logger.info("Branch Code==>"+cmnBranchMstLst.get(0).getBranchCode());
						logger.info("Branch Name==>"+cmnBranchMstLst.get(0).getBranchName());
						sbLocationValues.append("<BranchList>");
						sbLocationValues.append("<branchcode>").append("<![CDATA[").append(cmnBranchMstLst.get(0).getBranchCode())
						.append("]]>").append("</branchcode>");
						sbLocationValues.append("<branchname>").append("<![CDATA[").append(cmnBranchMstLst.get(0).getBranchName())
						.append("]]>").append("</branchname>");
						sbLocationValues.append("</BranchList>");
					}
				}
			}

			sbLocationValues.append("<SelectedLocation>");
			sbLocationValues.append("<locationcode>").append("<![CDATA[").append(cmnLocationMst.getLocationCode())
			.append("]]>").append("</locationcode>");
			sbLocationValues.append("<locationname>").append("<![CDATA[").append(cmnLocationMst.getLocName())
			.append("]]>").append("</locationname>");
			sbLocationValues.append("</SelectedLocation>");

			//get branch alll brnach list

			String tempStr = new AjaxXmlBuilder().addItem("key_ajax", sbLocationValues.toString()).toString();
			logger.info("generated police stations xml is "+tempStr);
			result.put("ajaxKey", tempStr);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(result);
			objRes.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);
		}
		logger.info("--getAllPoliceStation srvc end--");
		return objRes;
	}

	/**
	 * This method is used to get List of All Designation
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */	

	public ResultObject getAllDesignation(Map objectArgs)
	{
		logger.info("getAllDesignation called....");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		StringBuffer sbDsgnValues = new StringBuffer(70);
		Map result = new HashMap();  
		try
		{        
			if (objRes == null || objectArgs == null)
			{
				objRes.setResultCode(-1);
				return objRes;
			}
			Map loginMap=(Map)objectArgs.get("baseLoginMap");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			OrgDesignationMstDaoImpl dsgnImpl = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,serv.getSessionFactorySlave()); 
			List<OrgDesignationMst> dsgnList = dsgnImpl.getAllDesignation(objectArgs,(Long)loginMap.get("langId"));
			

			Iterator itr = dsgnList.iterator();
			while (itr.hasNext())
			{	OrgDesignationMst dsgnMst = (OrgDesignationMst)itr.next();

			sbDsgnValues.append("<element>");
			sbDsgnValues.append("<elecode>").append(dsgnMst.getDsgnCode().toString()).append("</elecode>");
			sbDsgnValues.append("<elename>").append(dsgnMst.getDsgnName().toString()).append("</elename>");
			sbDsgnValues.append("</element>");
			}
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax", sbDsgnValues.toString()).toString();
			//logger.info("tempStr: -->"+tempStr);
			result.put("ajaxKey", tempStr);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(result);
			objRes.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);

		}
		logger.info("getAllDesignation ended....");
		return objRes;

	}
	/**
	 * This method is used to populate all combo on findEmployee.jsp   
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */	

	public ResultObject getAllEmployees(Map objectArgs)
	{	
		logger.info("getAllEmployees method of EMPSericeImpl called.......");
		Log logger = LogFactory.getLog(getClass());	
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);	

		try
		{			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
					
			OrgDesignationMstDaoImpl objOrgDsgnImpl = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,
					serv.getSessionFactorySlave());

			//Retireving login details from base login map
			Map loginMap =(Map)objectArgs.get("baseLoginMap");
			String logged_postId=loginMap.get("loggedInPost").toString();
			Long loggedInlocationId = (Long)loginMap.get("locationId");
			Long langId =(Long)loginMap.get("langId");
			Long LoggedInCityId = null;
			CmnCityMst objCityMst = null;
			String defEmpfnameTxtVal = "%";
			String employeeSrchName = StringUtility.getParameter("SearchEmployee",request);
			String forReports = StringUtility.getParameter("ForReports",request);
			String srchTagName = StringUtility.getParameter("SearchTagName",request);
			String hiddenLocParamName = "hidden_locSpec_"+employeeSrchName;
			String hiddenDsgnCodesName = StringUtility.getParameter("hiddenSpecDsgnCodes",request);
			String empExpSrchFlag = StringUtility.getParameter("empExpDateSrchFlag", request);
			String hidnSpecLocId = StringUtility.getParameter(hiddenLocParamName,request);
			List<OrgDesignationMst> dsgnList = null;
			List dsgnCodes = new ArrayList();
			
			
			
			logger.info("forReports-----"+forReports);
			
			if(forReports!=null && !(" ").equals(forReports.trim()))
			{
				forReports = "Yes";
			}
			
			logger.info("hiddenDsgnCodesName-----"+hiddenDsgnCodesName);
			logger.info("empExpSrchFlag concept value is "+empExpSrchFlag);
			logger.info("forReports-----"+forReports);
			logger.info("srchTagName-----"+srchTagName);
			logger.info("forReports-----"+forReports);

			if(hiddenDsgnCodesName!=null && !"".equals(hiddenDsgnCodesName))
			{
				logger.info("hidden for designation codes");
				if(hiddenDsgnCodesName.indexOf(',')!= -1)
				{
					String[] hidnDsgnCodesArr = hiddenDsgnCodesName.split(",");
					dsgnCodes = Arrays.asList(hidnDsgnCodesArr);
				}
				dsgnList = objOrgDsgnImpl.getDesignationByDesigCodesAndLang(dsgnCodes,langId);
			}
			else
			{
				//dsgnList = objOrgDsgnImpl.getAllDesignation(objectArgs,langId);
				AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(MstDcpsDesignation.class, serv.getSessionFactory());
				dsgnList =adminDao.getActiveDsgnList(loggedInlocationId);
			}
			
			
			
			
			
			if(srchTagName!=null && "".equals(srchTagName.trim()))
			{
				srchTagName = "No";
			}
			
			logger.info("designation list size is "+dsgnList.size());
			logger.info("srchTagName-----"+srchTagName);
			logger.info("hidnSpecLocId-----"+hidnSpecLocId);
			
			
			if(hidnSpecLocId!=null && !"".equals(hidnSpecLocId.trim()))
			{
				logger.info("hidnSpecLocId-----"+hidnSpecLocId);
				loggedInlocationId = Long.valueOf(hidnSpecLocId);
			}
			else
			{
				hidnSpecLocId = "";
			}
			logger.info("hidnSpecLocId-----"+hidnSpecLocId);

			
			
			String srchNameText = StringUtility.getParameter("name_srchNameText",request);
			String ownLocationParameter = StringUtility.getParameter("ownlocation",request);
			String childLocSrchParam = StringUtility.getParameter("childLocSearch",request);
			String functionName = StringUtility.getParameter("functionName",request);
			String levelSortParam = StringUtility.getParameter("levelSortParam",request);
			String multiEmplSelect = StringUtility.getParameter("multiEmplSelect",request);
			
			logger.info("srchNameText-----"+srchNameText);
			logger.info("multiEmplSelect-----"+multiEmplSelect);
			logger.info("functionNameParameter-----"+functionName);
			logger.info("levelSortParam-----"+levelSortParam);
			logger.info("childLocSrchParam-----"+childLocSrchParam);
			
			
			if("".equals(multiEmplSelect))
			{
				multiEmplSelect = "none";
			}
			if("".equals(levelSortParam))
			{
				levelSortParam = "none";
			}
			if("".equals(functionName))
			{
				functionName = "testCall";
			}
			if(!"".equals(srchNameText))
			{
				logger.info("IF.....srchNameText-----"+srchNameText);
				objectArgs.put("srchNameTextFlag","Yes");
			}
			else
			{
				logger.info("ELSE srchNameText-----"+srchNameText);
				objectArgs.put("srchNameTextFlag","No");
			}
			
			String listReadOnlyParam = null;
			if("Yes".equals(ownLocationParameter))
			{
				listReadOnlyParam = "true";
			}
			else
			{
				listReadOnlyParam = "false";
			}
			

			
			

			
			forReports="No";
			
			logger.info("srchNameText-----"+srchNameText);
			logger.info("multiEmplSelect-----"+multiEmplSelect);
			logger.info("functionNameParameter-----"+functionName);
			logger.info("levelSortParam-----"+levelSortParam);
			logger.info("childLocSrchParam-----"+childLocSrchParam);
			logger.info("ownLocationParameter-----"+ownLocationParameter);
			
			objectArgs.put("dsgnList",dsgnList);
			objectArgs.put("ownLocationKey",ownLocationParameter);
			objectArgs.put("srchTagName",srchTagName);
			objectArgs.put("srchNameText",srchNameText);
			objectArgs.put("forReports",forReports);
			objectArgs.put("defEmpfnameTxtVal",defEmpfnameTxtVal);
			objectArgs.put("childLocSrchParam",childLocSrchParam);
			objectArgs.put("multiEmplSelect",multiEmplSelect);
			objectArgs.put("functionName",functionName);
			objectArgs.put("levelSortParam",levelSortParam);
			objectArgs.put("empExpSrchFlag",empExpSrchFlag);
			objectArgs.put("hiddenDsgnCodes",hiddenDsgnCodesName);
			objectArgs.put("listReadOnlyParam",listReadOnlyParam);
			objectArgs.put("empSrchName",employeeSrchName);
			objectArgs.put("hidnSpecLocId",hidnSpecLocId);
			

			EmployeeSearchCriteria objEmpSrchCrit = new EmployeeSearchCriteria();
			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());
			boolean boolFromSession=true;
			
			CmnBranchMstDaoImpl cmnBranchMstDaoImpl=new CmnBranchMstDaoImpl(CmnBranchMst.class,serv.getSessionFactorySlave());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(Long.parseLong(logged_postId));
			
			logger.info("logged_postId*********" + Long.parseLong(logged_postId));
			logger.info("langId*********" + langId);			
			
			
			String branchCode="";
			if(orgPostMst!=null)
			{
				if(orgPostMst.getBranchCode()!=null)
				{
					branchCode=orgPostMst.getBranchCode();
					logger.info("inside branch codee ************** "+branchCode);
					objectArgs.put("loggedInBranchCode",branchCode);
				}
			}
			
			if(branchCode.trim().length()>0)
			{

				List<CmnBranchMst> cmnBranchMstLst=cmnBranchMstDaoImpl.getListByColumnAndValue("branchCode", branchCode);
				if(!cmnBranchMstLst.isEmpty())
				{
					for(int k=0;k<cmnBranchMstLst.size();k++)
					{
						if(cmnBranchMstLst.get(k).getCmnLanguageMst().getLangId()==langId)
						{
							objEmpSrchCrit.setCmnBranchMst(cmnBranchMstLst.get(k));
							logger.info("cmnBranchMstLst id"+cmnBranchMstLst.get(k).getBranchId());
						}
					}

				}
			}
			
			
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactorySlave());
			CmnLocationMstDaoImpl location = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactorySlave());
			CmnLocationMst objCmnLocationMst=location.read(loggedInlocationId);
			objectArgs.put("loggedInLocationcode", objCmnLocationMst.getLocationCode());
			
			
			OrgEmpMst objOrgEmpMst= new OrgEmpMst();
			objOrgEmpMst.setEmpFname("");
			objOrgEmpMst.setEmpLname("");
			
			List locSrchList=new ArrayList();
			locSrchList.add(objCmnLocationMst);
			
			
			objEmpSrchCrit.setCmnLanguageMst(cmnLanguageMstDaoImpl.read(langId));
			objEmpSrchCrit.setOrgEmpMst(objOrgEmpMst);
			objEmpSrchCrit.setLocSrchList(locSrchList);

			
			logger.info("loggedInLocationcode*********"+objCmnLocationMst.getLocationCode());
			logger.info("lanidddddd*******************"+objEmpSrchCrit.getCmnLanguageMst().getLangId());
			logger.info("Employee idddddd*************"+objEmpSrchCrit.getOrgEmpMst().getEmpId());
			logger.info("size of gocsrchlist**********"+objEmpSrchCrit.getLocSrchList().size());
			
			
			
			
			
			// start of code to provide partial list by nikunj
			PaginatedListImpl   paginatelist = new PaginatedListImpl();
			paginatelist.setRequest(request);
			String page= request.getParameter("page");
			logger.info("page aaaaaaa"+page);

			if(page != null)
			{
				page = request.getParameter("page"); 

			} 
			else
			{
				page="1";
			}
			int count = 0;
			paginatelist = paginatelist.getPaginatedListObject(10,page);

			int startIndex=paginatelist.getFirstRecordIndex();
			int pageSize = paginatelist.getPageSize();

			
			
			
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			String desgnCode ="";
			String employeeId ="";
			String EmpFullname = "";
			String dcpsDdoOfficeIdPk ="";
			long designationId=0;
			String GPFAcctNo = "";
			PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			
			List dtlsList = new ArrayList();
			dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
			
			long empId=0;
			String empPrf="";
			String empFname="";
			String empMname="";
			String empLName ="";
			String empFullname="";
			String dsgnName="";
			String gpfAccountNo="";
			String officeName="";
			String userId="";
			
			String userName="";
			
			String postId="123";

			String sevarthEmpCode = "";
			String pfSeries="";
			List finalEmpList = new ArrayList();
				EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
			
			
		
				
				if(dtlsList!=null && dtlsList.size()!=0)
				{
				//	for(int i=0;i<dtlsList.size();i++)
						for(Iterator itr=dtlsList.iterator();itr.hasNext();)
					{

						empCustomVO=new EmployeeSearchCustomVO();
						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							empId = Long.parseLong(rowList[0].toString().trim());
							empCustomVO.setEmpId(empId);
						}

						if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
							empFullname = rowList[1].toString();
							empCustomVO.setEmpFullname(empFullname);
							logger.info("empFullname::"+empFullname);
						}
						if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
							dsgnName = rowList[2].toString();
							empCustomVO.setDsgnName(dsgnName);
							logger.info("dsgnName::"+dsgnName);
						}
						if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
							if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
						 		pfSeries = rowList[12].toString();
						 		logger.info("pfSeries::::::::::::::: "+pfSeries);
						 		
						 			
								 	      if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
								 	      {
								 	    	 //gpfAccountNo = pfSeries+"/"+rowList[3].toString();
								 	    	 gpfAccountNo = rowList[3].toString();
								 	      }
								 	      else
								 	      {
								 	    	 gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
								 	      }
						 			}
						 	
						 	else
						 	{
						 		gpfAccountNo = rowList[3].toString(); 
						 	}
							empCustomVO.setGpfAccountNo(gpfAccountNo);
							
							logger.info("gpfAccountNo::"+gpfAccountNo);
						}
						if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
							officeName = rowList[4].toString();
							empCustomVO.setOfficeName(officeName);
							logger.info("officeName::"+officeName);
						}
						if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
							empPrf = rowList[5].toString();
							empCustomVO.setEmpPrf(empPrf);
							logger.info("empPrf::"+empPrf);
						}
						if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
							empFname = rowList[6].toString();
							empCustomVO.setEmpFname(empFname);
							logger.info("empFname::"+empFname);
						}
						if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
							empLName = rowList[7].toString();
							empCustomVO.setEmpLName(empLName);
							logger.info("empLName::"+empLName);
						}
						if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
							userId = rowList[8].toString();
							empCustomVO.setUserId(userId);
							logger.info("userId::"+userId);
						}
						if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
							userName = rowList[9].toString();
							empCustomVO.setUserName(userName);
							logger.info("userName::"+userName);
						}
						
						if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
							sevarthEmpCode = rowList[11].toString();
							empCustomVO.setSevarthEmpCode(sevarthEmpCode);
							logger.info("sevarthEmpCode::::::"+sevarthEmpCode);
						}
						
						empCustomVO.setPostId(postId);
						
						finalEmpList.add(empCustomVO);

					}
				}
				
				paginatelist.setList(finalEmpList);
				logger.info("finalEmpList size"+finalEmpList.size());
				logger.info("count==>"+count);
				
				logger.info("finalEmpList size"+finalEmpList.size());
				objectArgs.put("finalEmpList", finalEmpList);
				objectArgs.put("empId",empId);
			
				String numberResultsPerPage = StringUtility.getParameter("lstResultsPerPage", request);
				logger.info("numberResultsPerPage==>:::::::::"+numberResultsPerPage);
				logger.info("no of records as enterd by user::::::::::"+numberResultsPerPage);
				if("-1".equals(numberResultsPerPage) || numberResultsPerPage.trim().length()==0 )
				{
					logger.info("in IF--no of records as enterd by user::::::::"+numberResultsPerPage);
					numberResultsPerPage="10";
				}

				objectArgs.put("numberResultsPerPage", numberResultsPerPage);
		
		
		
		
			String requesturi="./hdiits.htm?actionFlag=findemployee";
			objectArgs.put("requesturi", requesturi);
			// end of code to provide partial list by nikunj

			
			
			List<OrgEmpMst> searchEmpList  = empImpl.SearchEmployeeUpdate(objEmpSrchCrit,startIndex,pageSize,true);
			List<OrgEmpMst> searchEmpListcount  = empImpl.SearchEmployeeUpdate(objEmpSrchCrit,startIndex,pageSize,false);
			count = Integer.valueOf(searchEmpListcount.size());
			paginatelist.setTotalNumberOfRows(count);
			objectArgs.put("paginatelist", paginatelist);

		/*	List<SearcheEmployeeCustomVO> finalEmpList =new ArrayList();
			finalEmpList = PayrollEmpSearchService.createEmployeeSearchResultList(searchEmpList, boolFromSession, objectArgs, langId);
			paginatelist.setList(finalEmpList);
			logger.info("searchEmpList size"+searchEmpList.size());
			logger.info("finalEmpList size"+finalEmpList.size());
			logger.info("searchEmpListcount==>"+searchEmpListcount.size());
			logger.info("count==>"+count);
			
			long empId = finalEmpList.get(0).getEmpId();
			String empLastName =finalEmpList.get(0).getEmpLname();
			logger.info("finalEmpList size"+finalEmpList.size());
			objectArgs.put("finalEmpList", finalEmpList);
			objectArgs.put("finalEmpList", finalEmpList);
			objectArgs.put("empId",empId);
			objectArgs.put("empLastName",empLastName);*/
			
			
			
			
		//	Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		//	PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List officeLList = payBillDAOImpl.getOfficeList(locId);
			objectArgs.put("officeList",officeLList);
			
			
			
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);		
			objRes.setViewName("findemployee");
		}
		catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error( "ERROR : Some exception has occured...process cannot continue",e);
		}
		logger.info("getAllEmployees  service ended.......");
		return objRes;
	}


	public ResultObject getCityOrLocForDistrict(Map objectArgs)
	{
		logger.info("--getCityOrLocForDistrict srvc start--");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR, "FAIL");
		Map result = new HashMap();
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator servLoc = (ServiceLocator)objectArgs.get("serviceLocator");
		StringBuffer sbCityValues = new StringBuffer(200);
		String strDistrictId = null;
		Long districtId = null;
		try
		{
			CmnLocationMstDao objCmnLocMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,
					servLoc.getSessionFactorySlave());
			String strParameter = StringUtility.getParameter("districtCode",request);
			logger.info("the district code is"+strParameter);
			if (objRes == null || objectArgs == null)
			{
				objRes.setResultCode(-1);
				return objRes;
			}
			Map loginMap = (Map)objectArgs.get("baseLoginMap");
			CmnDistrictMstDAOImpl districtImpl = new CmnDistrictMstDAOImpl(CmnDistrictMst.class,
					servLoc.getSessionFactorySlave());

			CmnDistrictMst objCmnDistrictMst = districtImpl.getDistrictVOByDistrictCodeAndLangId(strParameter,
					(Long)loginMap.get("langId"));
			if(objCmnDistrictMst!= null)
			{
				strDistrictId = Long.valueOf(objCmnDistrictMst.getDistrictId()).toString();
				districtId = objCmnDistrictMst.getDistrictId();
			}

			objectArgs.put("districtId",strDistrictId);
			CmnCityMstDAOImpl cityImpl = new CmnCityMstDAOImpl(CmnCityMst.class, servLoc.getSessionFactorySlave());
			List<CmnCityMst> cityList = cityImpl.getAllCity(objectArgs,(Long)loginMap.get("langId"));
			logger.info("the cityList list is"+cityList.size());
			Iterator itr = cityList.iterator();
			while (itr.hasNext())
			{
				CmnCityMst cityMst = (CmnCityMst) itr.next();
				sbCityValues.append("<element>");
				sbCityValues.append("<elecode>").append(cityMst.getCityCode().toString()).append("</elecode>");
				sbCityValues.append("<elename>").append(cityMst.getCityName().toString()).append("</elename>");
				sbCityValues.append("<eletype>").append("city").append("</eletype>");
				sbCityValues.append("</element>");
			}

			List locListForDistr = objCmnLocMstDaoImpl.getListByColumnAndValue("locDistrictId",districtId);
			logger.info("the locListForDistr list is"+locListForDistr.size());
			Iterator<CmnLocationMst> locListIter = locListForDistr.iterator();
			while (locListIter.hasNext())
			{
				CmnLocationMst cmnLocMst = locListIter.next();
				sbCityValues.append("<element>");
				sbCityValues.append("<elecode>").append("<![CDATA[").append(cmnLocMst.getLocationCode()).append("]]>")
				.append("</elecode>");
				sbCityValues.append("<elename>").append("<![CDATA[").append(cmnLocMst.getLocName()).append("]]>").
				append("</elename>");
				sbCityValues.append("<eletype>").append("location").append("</eletype>");
				sbCityValues.append("</element>");
			}
			String tempStr = new AjaxXmlBuilder().addItem("key_ajax", sbCityValues.toString()).toString();
			logger.info("the xml her is "+tempStr);
			result.put("ajaxKey", tempStr);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(result);
			objRes.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);
		}
		logger.info("--getCityOrLocForDistrict srvc end--");
		return objRes;
	}


	/**
	 * This method is used to get all Policestation List   
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */	

	public ResultObject searchEmployeeLocation(Map objectArgs) 
	{		
		logger.info("searchEmployeeLocation called........");
		ResultObject  resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	

		try{

			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			CmnLocationMstDaoImpl location = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactorySlave());

			List<CmnLocationMst> locationList = location.list();	
			logger.info("location List::  ---  "+locationList);	
			Map loginMap =(Map)objectArgs.get("baseLoginMap");
			Long loggedInlocationId=(Long)loginMap.get("locationId");
			CmnLocationMst objCmnLocationMst=location.read(loggedInlocationId);

			objectArgs.put("loggedInLocation", objCmnLocationMst.getLocationCode());
			objectArgs.put("locationList",locationList);
			logger.info(" location List Size ::---"+locationList.size());			

			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("findemployee");
		}
		catch(Exception e)
		{
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error( "ERROR : ",e);
		}

		return resultObject;
	}

	/*public ResultObject searchEmployeeDistrict(Map objectArgs) 
{		
	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	ResultObject  resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	

	try{		
	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");	
	CmnDistrictMstDAOImpl district = new CmnDistrictMstDAOImpl(CmnDistrictMst.class,serv.getSessionFactory());

	List<CmnDistrictMst> districList = district.list();	
	logger.info("districList::  ---  "+districList);	
	Map loginMap =(Map)objectArgs.get("baseLoginMap");
	Long loggedInlocationId=(Long)loginMap.get("locationId");
	objectArgs.put("loggedInLocation", loggedInlocationId);
	objectArgs.put("locationList",districList);
	logger.info(" location List Size ::---"+districList.size());			

	resultObject.setResultValue(objectArgs);
	resultObject.setViewName("findemployee");
	}
	catch(Exception e)
	{
		resultObject.setThrowable(e);
		resultObject.setResultCode(-1);
		resultObject.setViewName("errorPage");
		logger.error( "ERROR : ",e);
		logger.error("Error is: "+ e.getMessage());
	}

	return resultObject;
}

	 */


	/**
	 * This method is used to get all Employee On base of Fname  
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */	
	public ResultObject getAllEmployeesByname(Map objectArgs)
	{
		// For Get ALLEmployees by empId

		logger.info("getAllEmployeesByname service called");

		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	


		try{			
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}

			String tempempName = StringUtility.getParameter("empFname",request);
			//String  empName = Long.parseLong(tempempName);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());
			List empList = empImpl.getAllEmployeesByname(tempempName);

			//Iterator empIterator = empList.iterator();
			Map resultValue =  new HashMap();
			resultValue.put("empList",empList);
			objRes.setResultValue(resultValue);
			objRes.setViewName("findemployee");


		}catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error( "ERROR : Some exception has occured...process cannot continue",e);
		}
		return objRes;

	}

	/*public ResultObject getMyProfile(Map objectArgs)
	{
		Log logger = LogFactory.getLog(getClass());

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	

		try{			
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			OrgEmpMst mstEmployeeVO = (OrgEmpMst) objectArgs.get("empVO");
			Long EmpID = mstEmployeeVO.getEmpId();
			EmpDAOImpl empImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			List empList = empImpl.findByEmpID(EmpID);
			Iterator empIterator = empList.iterator();

			while (empIterator.hasNext()) 
			{
				Object[] tuple = (Object[]) empIterator.next();

			}

			for(int i=0;i<empList.size();i++)
			{
			logger.info("emp list"+empList.get(i).toString());
			}
			Map resultValue =  new HashMap();
			resultValue.put("empList",empList);
		    logger.info("emplisst"+empList);
			objRes.setResultValue(resultValue);
			//objRes.setViewName("viewMyProfile");
		}catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			logger.error( "ERROR : Some exception has occured...process cannot continue",e);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

public ResultObject getAllProfile(Map objectArgs)
	{

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	

		try{			
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			EmpDAOImpl empImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			List empList = empImpl.getAllEmployeeProfile();
            //Iterator empIterator = empList.iterator();
			Map resultValue =  new HashMap();
			resultValue.put("empList",empList);
			objRes.setResultValue(resultValue);
			//objRes.setViewName("viewAllProfile");
		}catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error("ERROR : ",e);
			logger.error("Error is: "+ e.getMessage());
		}
		return objRes;
	}*/

	/**
	 * This method is used to get  Employee details by Employee ID
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */		

	public ResultObject getAllEmployeesByempId(Map objectArgs)
	{
		// For Get ALLEmployees by empId

		logger.info("getAllEmployeesByempId service called");

		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	


		try{			
			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}
			String tempempId = StringUtility.getParameter("empid",request);
			long  empId = Long.parseLong(tempempId);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());
			List<OrgEmpMst> empVOList = (List<OrgEmpMst>) empImpl.getEmployeeVO(empId);
			//Iterator empIterator = empList.iterator();
			Map resultValue =  new HashMap();
			resultValue.put("empVOList",empVOList);
			objRes.setResultValue(resultValue);
			//objRes.setViewName("viewAllProfile");

		}catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error( "ERROR : Some exception has occured...process cannot continue",e);
		}
		return objRes;

	}

	/**
	 * This method is used to get Employee details by User ID
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */		

	public ResultObject getAllEmployeesByUserId(Map objectArgs)
	{
		//For Get ALLEmployees by user ID	

		logger.info("getAllEmployeesByUserId service called");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	


		try{

			if (objRes==null || objectArgs == null )
			{				
				objRes.setResultCode(-1);
				return objRes; 
			}

			//long userId=100100001;
			//long langId = 1;

			String tempuserId = StringUtility.getParameter("orgUserMst.userId",request);
			String templangId = StringUtility.getParameter("cmnLanguageMst.langId",request);

			long  userId = Long.parseLong(tempuserId);
			long  langId = Long.parseLong(templangId);

			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());
			List employeeVOList = empImpl.getAllEmployees(userId, langId);

			Map resultValue =  new HashMap();
			resultValue.put("employeeVOList",employeeVOList);
			objRes.setResultValue(resultValue);

			//objRes.setViewName("viewAllProfile");

		}catch (Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			logger.error( "ERROR : Some exception has occured...process cannot continue",e);
		}
		return objRes;

	}

	/**
	 * This method is used to get Designation Using  orgPostMst and LangID
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */	

	private String getDesignationName(OrgPostMst orgPostMst, long langId)
	{
		String designationName = "";
		Set orgPostDetailsSet = orgPostMst.getOrgPostDetailsRlt();
		Iterator orgPostDetailsSetIterator = orgPostDetailsSet.iterator();
		while(orgPostDetailsSetIterator.hasNext())
		{
			OrgPostDetailsRlt orgPostDetails = (OrgPostDetailsRlt) orgPostDetailsSetIterator.next();
			if(orgPostDetails.getCmnLanguageMst().getLangId() == langId)
			{
				designationName = orgPostDetails.getOrgDesignationMst().getDsgnName();
			}
		}
		return designationName;
	}

	/**
	 * This method is used to get Location Using  orgPostMst and LangID
	 * @param  Map objectArgs 
	 * @return ResultObject
	 * 
	 *  
	 */

	private CmnLocationMst getLocation(OrgPostMst orgPostMst, long langId)
	{
		CmnLocationMst cmnLocationMst = null;
		Set orgPostDetailsSet = orgPostMst.getOrgPostDetailsRlt();
		Iterator orgPostDetailsSetIterator = orgPostDetailsSet.iterator();
		while(orgPostDetailsSetIterator.hasNext())
		{
			OrgPostDetailsRlt orgPostDetails = (OrgPostDetailsRlt) orgPostDetailsSetIterator.next();
			if(orgPostDetails.getCmnLanguageMst().getLangId() == langId)
			{
				cmnLocationMst = orgPostDetails.getCmnLocationMst();
			}
		}
		return cmnLocationMst;
	}
	public ResultObject checkStatus(Map objectArgs)
	{
		logger.info("inside checkStatus method::::::::::");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR, "FAIL");
		Map result = new HashMap();
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try
		{
			String userId = StringUtility.getParameter("userId",request);
			String status = null;
			StringBuffer statusString = new StringBuffer();
			logger.info("userId::"+userId);
			if (objRes == null || objectArgs == null)
			{
				objRes.setResultCode(-1);
				return objRes;
			}
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap=(Map)objectArgs.get("baseLoginMap");
			if(userId != null && !("").equals(userId))
			{
				objectArgs.put("Emp_userId", userId);
			}  
			objRes = serv.executeService("check_User_Leave_Status", objectArgs);
			objectArgs = (Map) objRes.getResultValue();
			if (objectArgs.containsKey("LEAVE_"+userId))
			{
				status = (String) objectArgs.get("LEAVE_"+userId);
			}
			statusString.append("<element>");
			statusString.append("<elecode>").append(status).append("</elecode>");
			statusString.append("</element>");

			String tempStr = new AjaxXmlBuilder().addItem("key_ajax", statusString.toString()).toString();
			logger.info("tempStr"+tempStr.toString());

			result.put("ajaxKey", tempStr);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(result);
			objRes.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);

		}
		return objRes;

	}
	
	public static EmployeeSearchVO getEmployeeSearchVO (OrgEmpMst objEmpMst,Map objectArgs) throws Exception
	{
		try 
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			EmployeeSearchVO empVO  = new EmployeeSearchVO();
			empVO.setEmpId(objEmpMst.getEmpId());
			empVO.setEmpFname(objEmpMst.getEmpFname());
			empVO.setEmpMname(objEmpMst.getEmpMname());
			empVO.setEmpLname(objEmpMst.getEmpLname());
			empVO.setEmpPrf(objEmpMst.getEmpPrefix());
			empVO.setUserId(objEmpMst
									.getOrgUserMst().getUserId());
			
			
			logger.info("gpfnumberrrrrr************"+objEmpMst.getEmpGPFnumber());
			if(objEmpMst.getEmpGPFnumber()!=null)
			 {
				empVO.setUserName(objEmpMst
						.getEmpGPFnumber());
			 }
			 else
			 {
				 empVO.setUserName("");
			 }
			

			PayrollEmpSearchDAOImpl empImpl = new PayrollEmpSearchDAOImpl(OrgEmpMst.class,serv.getSessionFactorySlave());			

			OrgPostDetailsRlt objPostDetailsRlt = empImpl.getPostDetailsRltFromEmployee (objEmpMst);

			String DsgnNM = objPostDetailsRlt.getOrgDesignationMst().getDsgnName();
			String PolicestationNM = objPostDetailsRlt.getCmnLocationMst().getLocName();

			
			logger.info("::::::::::::::Here It is:::::::::::::::::");
			CmnDistrictMstDAOImpl objMstDAOImpl = new CmnDistrictMstDAOImpl(CmnDistrictMst.class, serv.getSessionFactory());
			logger.info("::::::::::::::Here It is:::::::::::::::::");
			/*CmnDistrictMst objCmnDistrictMst = objMstDAOImpl.read(objPostDetailsRlt.getCmnLocationMst().getLocDistrictId());
			String LocDistNM = objCmnDistrictMst.getDistrictName();*/
			logger.info("::::::::::::::Here It is:::::::::::::::::");
			
			/*empVO.setLocDistrictName(LocDistNM);*/
			empVO.setLocPlacename(PolicestationNM);
			logger.info("::::::::::::::Here It is:::::::::::::::::");
			empVO.setDsgnName(DsgnNM);
			if(objPostDetailsRlt.getOrgPostMst() != null)
			{
				empVO.setPostId(objPostDetailsRlt.getOrgPostMst().getPostId());
			}
			return empVO;
		}
		catch (Exception e) 
		{
			logger.info("Exception found in getEmployeeSearchVO method of EmployeeUtility class"+e);
			throw e;
		}
	}
	public ResultObject showEmployeeDetailsFromDept(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try{
			logger.info("************Inside showEmployeeDetailsFromDept*****************");			
			if (resultObject != null && objectArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				
				logger.info("Inside showEmployeeDetailsFromDept");
				//String desgnCode = StringUtility.getParameter("desgnCode",request);
				//String dcpsDdoOfficeIdPk = StringUtility.getParameter("city",request);
				//String employeeId = StringUtility.getParameter("empId",request);
				
				String desgnCode ="";
				String employeeId ="";
				String EmpFullname = "";
				String dcpsDdoOfficeIdPk ="";
				long designationId=0;
				String GPFAcctNo = "";
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				/*String desgnCode ="";
				String dcpsDdoOfficeIdPk ="";
				String employeeId="";
				String EmpFullname="";
				*/
				logger.info("desgnCode*********" + desgnCode);
				logger.info("dcpsDdoOfficeIdPk*********" + dcpsDdoOfficeIdPk);
				logger.info("employeeId*********" + employeeId.toString());
				logger.info("empFname*********" + EmpFullname);
				
				
				List dtlsList = new ArrayList();
				List finalEmpList = new ArrayList();
			
				
				//String GPFAcctNo = StringUtility.getParameter("GPFAcctNo",request);
				
				logger.info("GPFAcctNo*********" + GPFAcctNo);
				
				
				if (StringUtility.getParameter("empFname",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empFname",request)) &&!StringUtility.getParameter("empFname",request).equals("-1") && !StringUtility.getParameter("desgnCode",request).equals("-1") && !(StringUtility.getParameter("city",request)).equals("-1"))
				{
					
					logger.info("Enter into checking for EmpFullname and desgnCode and Office");
					
					
					String empfullnameinlocallanguage = "";
					String empLocalFName ="";
					String empLocalMName ="";
					String empLocalLName ="";
					
					 if(StringUtility.getParameter("empFname", request)!=null && !StringUtility.getParameter("empFname", request).equals("")) 
			            {
						 empfullnameinlocallanguage = StringUtility.getParameter("empFname", request) != null ? StringUtility.getParameter("empFname", request) : "";
			            }
					 
					 
					 String b[] = empfullnameinlocallanguage.split(" ");

						if(b.length==3)
						{
							logger.info("Employee first name:"+b[0]);
							logger.info("Employee M name:"+b[1]);
							logger.info("Employee Last name:"+b[2]);
							empLocalFName = b[0];
							empLocalMName = b[1];
							empLocalLName = b[2];
						}
						else if(b.length==2)
						{
							logger.info("Employee first name:"+b[0]);
							logger.info("Employee Last name:"+b[1]);
							empLocalFName = b[0];
							empLocalLName = b[1];
						}
						else if(b.length==1)
						{
							logger.info("Employee first name:"+b[0]);
							empLocalFName = b[0];
						}
						else
						{
							logger.info("Employee First name:"+b[0]);
							empLocalFName = b[0];
							String EmpLocalMName="";
							for(int i=1;i<b.length-1;i++)
							{
								EmpLocalMName+=" "+b[i];
							}
							logger.info("Employee M name :"+EmpLocalMName);
							empLocalMName = EmpLocalMName;
							empLocalLName = b[b.length-1];
							logger.info("Employee Last name:"+b[b.length-1]);
						}
					
					
					Object[] postDtlsLst = null; 
					List employeeIdUserList = new ArrayList();
					employeeIdUserList  = payBillDAOImpl.getEmployeeIdListFromEployeeName(empLocalFName, empLocalMName, empLocalLName);
					
					String emppId="";
					long userrId=0;
					String sevarthid = "";
					if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
					{
						for(int j=0; j<employeeIdUserList.size();j++)
						{

							Object[] rowList1 = (Object[])employeeIdUserList.get(j);

							if(rowList1[0] != null)
							{
								emppId = rowList1[0].toString().trim();
							}
							if(rowList1[1] != null)
							{
								userrId = Long.parseLong(rowList1[1].toString().trim());
							}
							
								long DesignationId = payBillDAOImpl.getDesignationIdEployeeId(emppId);


								String DesgnCode = StringUtility.getParameter("desgnCode",request);
								long DESIGNATIONId = payBillDAOImpl.getDesignationIdFromDsgnCode(DesgnCode);



								dcpsDdoOfficeIdPk = StringUtility.getParameter("city",request);
								//	String EMPLOYEEID = payBillDAOImpl.getEmployeeIdFromOfficeId(emppId, dcpsDdoOfficeIdPk);

								logger.info("EmpFullname and dsgn and office**emppId******" + emppId);
								logger.info("EmpFullname and dsgn and office****userrId****" + userrId);
								logger.info("EmpFullname and dsgn and office DesignationId**** " + DesignationId);
								logger.info("EmpFullname and dsgn and office DESIGNATIONId****" + DESIGNATIONId);
								logger.info("EmpFullname and dsgn and office OfficeId****" + dcpsDdoOfficeIdPk);




								if(DESIGNATIONId==DesignationId)
								{

									//desgnCode=DesgnCode;
									designationId=DesignationId;
									employeeId=emppId;

									//for multiple
									EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
									dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
									logger.info("dtlsList size is.."+dtlsList.size());
									long empId=0;
									String empPrf="";
									String empFname="";
									String empMname="";
									String empLName ="";
									String empFullname="";
									String dsgnName="";
									String gpfAccountNo="";
									String officeName="";
									String userId="";

									String userName="";

									String postId="123";

									String sevarthId="";
									String pfSeries="";

									if(!dtlsList.isEmpty())
									{
										for(int p=0;p<dtlsList.size();p++)
										{
											Object[] rowList = (Object[])dtlsList.get(p);
											empCustomVO =new EmployeeSearchCustomVO();
											if(rowList[0] != null)
											{
												empId = Long.parseLong(rowList[0].toString().trim());
												empCustomVO.setEmpId(empId);
											}

											if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
												empFullname = rowList[1].toString();
												empCustomVO.setEmpFullname(empFullname);
												logger.info("empFullname::"+empFullname);
											}
											if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
												dsgnName = rowList[2].toString();
												empCustomVO.setDsgnName(dsgnName);
												logger.info("dsgnName::"+dsgnName);
											}
											if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
												if(rowList[12] != null && !(rowList[12].toString().trim()).equals(""))
												{
													pfSeries = rowList[12].toString();
													logger.info("pfSeries::::::::::::::: "+pfSeries);


													if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
													{
														//gpfAccountNo = pfSeries+"/"+rowList[3].toString(); 
														gpfAccountNo = rowList[3].toString();
													}
													else
													{
														gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
													}
												}

												else
												{
													gpfAccountNo = rowList[3].toString(); 
												}

												empCustomVO.setGpfAccountNo(gpfAccountNo);

												logger.info("gpfAccountNo::"+gpfAccountNo);
											}
											if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
												officeName = rowList[4].toString();
												empCustomVO.setOfficeName(officeName);
												logger.info("officeName::"+officeName);
											}
											if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
												empPrf = rowList[5].toString();
												empCustomVO.setEmpPrf(empPrf);
												logger.info("empPrf::"+empPrf);
											}
											if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
												empFname = rowList[6].toString();
												empCustomVO.setEmpFname(empFname);
												logger.info("empFname::"+empFname);
											}
											if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
												empLName = rowList[7].toString();
												empCustomVO.setEmpLName(empLName);
												logger.info("empLName::"+empLName);
											}
											if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
												userId = rowList[8].toString();
												empCustomVO.setUserId(userId);
												logger.info("userId::"+userId);
											}
											if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
												userName = rowList[9].toString();
												empCustomVO.setUserName(userName);
												logger.info("userName::"+userName);
											}
											if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
												sevarthId = rowList[11].toString();
												empCustomVO.setSevarthEmpCode(sevarthId);
												logger.info("sevarthId:::::::::::::"+sevarthId);
											}							
											empCustomVO.setPostId(postId);
										}
										finalEmpList.add(empCustomVO);
									}

								}

							}
						}


					}
				
				//for employeename and employeeid
				else if (StringUtility.getParameter("empFname",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empFname",request)) &&!StringUtility.getParameter("empFname",request).equals("-1") && StringUtility.getParameter("empId",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empId",request)) &&!StringUtility.getParameter("empId",request).equals("-1") )
				{

					logger.info("Enter into checking for EmpFullname and employeeId");


					String empfullnameinlocallanguage = "";
					String empLocalFName ="";
					String empLocalMName ="";
					String empLocalLName ="";

					if(StringUtility.getParameter("empFname", request)!=null && !StringUtility.getParameter("empFname", request).equals("")) 
					{
						empfullnameinlocallanguage = StringUtility.getParameter("empFname", request) != null ? StringUtility.getParameter("empFname", request) : "";
					}


					String b[] = empfullnameinlocallanguage.split(" ");

					if(b.length==3)
					{
						logger.info("Employee first name:"+b[0]);
						logger.info("Employee M name:"+b[1]);
						logger.info("Employee Last name:"+b[2]);
						empLocalFName = b[0];
						empLocalMName = b[1];
						empLocalLName = b[2];
					}
					else if(b.length==2)
					{
						logger.info("Employee first name:"+b[0]);
						logger.info("Employee Last name:"+b[1]);
						empLocalFName = b[0];
						empLocalLName = b[1];
					}
					else if(b.length==1)
					{
						logger.info("Employee first name:"+b[0]);
						empLocalFName = b[0];
					}
					else
					{
						logger.info("Employee First name:"+b[0]);
						empLocalFName = b[0];
						String EmpLocalMName="";
						for(int i=1;i<b.length-1;i++)
						{
							EmpLocalMName+=" "+b[i];
						}
						logger.info("Employee M name :"+EmpLocalMName);
						empLocalMName = EmpLocalMName;
						empLocalLName = b[b.length-1];
						logger.info("Employee Last name:"+b[b.length-1]);
					}


					Object[] postDtlsLst = null; 
					List employeeIdUserList = new ArrayList();
					employeeIdUserList  = payBillDAOImpl.getEmployeeIdListFromEployeeName(empLocalFName, empLocalMName, empLocalLName);

					logger.info("size of employeeIdUserList for employeename is******"+employeeIdUserList.size());

					long emppId=0;
					long userrId=0;
					String EmployeeId= "";
					if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
					{
						for(int k=0;k<employeeIdUserList.size();k++)
						{

							Object[] rowList1 = (Object[])employeeIdUserList.get(k);

							if(rowList1[0] != null)
							{
								emppId = Long.parseLong(rowList1[0].toString().trim());
							}
							if(rowList1[1] != null)
							{
								userrId = Long.parseLong(rowList1[1].toString().trim());
							}


							logger.info("EmpFullname emppId********" + emppId);
							logger.info("EmpFullname userrId********" + userrId);

							String sevarthid =StringUtility.getParameter("empId",request);
							//String EmployeeId = payBillDAOImpl.getEmployeeIdFromSevarthId(sevarthid);

							List employeeIdList = payBillDAOImpl.getEmployeeIdsFromSevarthId(sevarthid);



							for(int i=0;i<employeeIdList.size();i++)
							{

								Object[] rowList11 = (Object[])employeeIdList.get(i);

								if(rowList11[0] != null)
								{
									EmployeeId = rowList11[0].toString().trim();
								}

								if(Long.parseLong(EmployeeId)==emppId)
								{
									designationId = payBillDAOImpl.getDesignationIdEployeeId(EmployeeId);
									employeeId=EmployeeId;
									logger.info("EmpFullname designationId" + designationId);
									logger.info("EmpFullname employeeId" + employeeId);


									//for multiple
									EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
									dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
									logger.info("dtlsList size is.."+dtlsList.size());
									long empId=0;
									String empPrf="";
									String empFname="";
									String empMname="";
									String empLName ="";
									String empFullname="";
									String dsgnName="";
									String gpfAccountNo="";
									String officeName="";
									String userId="";

									String userName="";

									String postId="123";

									String sevarthId="";
									String pfSeries="";

									if(!dtlsList.isEmpty())
									{
										for(int p=0;p<dtlsList.size();p++)
										{
											Object[] rowList = (Object[])dtlsList.get(p);
											empCustomVO =new EmployeeSearchCustomVO();
											if(rowList[0] != null)
											{
												empId = Long.parseLong(rowList[0].toString().trim());
												empCustomVO.setEmpId(empId);
											}

											if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
												empFullname = rowList[1].toString();
												empCustomVO.setEmpFullname(empFullname);
												logger.info("empFullname::"+empFullname);
											}
											if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
												dsgnName = rowList[2].toString();
												empCustomVO.setDsgnName(dsgnName);
												logger.info("dsgnName::"+dsgnName);
											}
											if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
												if(rowList[12] != null && !(rowList[12].toString().trim()).equals(""))
												{
													pfSeries = rowList[12].toString();
													logger.info("pfSeries::::::::::::::: "+pfSeries);


													if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
													{
														//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
														gpfAccountNo = rowList[3].toString();
													}
													else
													{
														gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
													}
												}

												else
												{
													gpfAccountNo = rowList[3].toString(); 
												}

												empCustomVO.setGpfAccountNo(gpfAccountNo);

												logger.info("gpfAccountNo::"+gpfAccountNo);
											}
											if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
												officeName = rowList[4].toString();
												empCustomVO.setOfficeName(officeName);
												logger.info("officeName::"+officeName);
											}
											if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
												empPrf = rowList[5].toString();
												empCustomVO.setEmpPrf(empPrf);
												logger.info("empPrf::"+empPrf);
											}
											if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
												empFname = rowList[6].toString();
												empCustomVO.setEmpFname(empFname);
												logger.info("empFname::"+empFname);
											}
											if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
												empLName = rowList[7].toString();
												empCustomVO.setEmpLName(empLName);
												logger.info("empLName::"+empLName);
											}
											if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
												userId = rowList[8].toString();
												empCustomVO.setUserId(userId);
												logger.info("userId::"+userId);
											}
											if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
												userName = rowList[9].toString();
												empCustomVO.setUserName(userName);
												logger.info("userName::"+userName);
											}
											if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
												sevarthId = rowList[11].toString();
												empCustomVO.setSevarthEmpCode(sevarthId);
												logger.info("sevarthId:::::::::::::"+sevarthId);
											}							
											empCustomVO.setPostId(postId);
										}
										finalEmpList.add(empCustomVO);
									}
								}
							}
						}
					}

				}
				
				//For employeename and dsgnid
				
				else if (StringUtility.getParameter("empFname", request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empFname", request)) &&!StringUtility.getParameter("empFname", request).equals("-1") && !StringUtility.getParameter("desgnCode",request).equals("-1") )
				{
					
					logger.info("Enter into checking for EmpFullname and desgnCode");
					
					
					String empfullnameinlocallanguage = "";
					String empLocalFName ="";
					String empLocalMName ="";
					String empLocalLName ="";
					
					 if(StringUtility.getParameter("empFname", request)!=null && !StringUtility.getParameter("empFname", request).equals("")) 
			            {
						 empfullnameinlocallanguage = StringUtility.getParameter("empFname", request) != null ? StringUtility.getParameter("empFname", request) : "";
			            }
					 
					 
					 String b[] = empfullnameinlocallanguage.split(" ");

						if(b.length==3)
						{
							logger.info("Employee first name:"+b[0]);
							logger.info("Employee M name:"+b[1]);
							logger.info("Employee Last name:"+b[2]);
							empLocalFName = b[0];
							empLocalMName = b[1];
							empLocalLName = b[2];
						}
						else if(b.length==2)
						{
							logger.info("Employee first name:"+b[0]);
							logger.info("Employee Last name:"+b[1]);
							empLocalFName = b[0];
							empLocalLName = b[1];
						}
						else if(b.length==1)
						{
							logger.info("Employee first name:"+b[0]);
							empLocalFName = b[0];
						}
						else
						{
							logger.info("Employee First name:"+b[0]);
							empLocalFName = b[0];
							String EmpLocalMName="";
							for(int i=1;i<b.length-1;i++)
							{
								EmpLocalMName+=" "+b[i];
							}
							logger.info("Employee M name :"+EmpLocalMName);
							empLocalMName = EmpLocalMName;
							empLocalLName = b[b.length-1];
							logger.info("Employee Last name:"+b[b.length-1]);
						}
					
					
					Object[] postDtlsLst = null; 
					List employeeIdUserList = new ArrayList();
					employeeIdUserList  = payBillDAOImpl.getEmployeeIdListFromEployeeName(empLocalFName, empLocalMName, empLocalLName);
					
					String emppId="";
					long userrId=0;
					if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
					{
						for(Iterator itr=employeeIdUserList.iterator();itr.hasNext();)
						{

							Object[] rowList1 = (Object[])itr.next();

							if(rowList1[0] != null)
							{
								emppId = rowList1[0].toString().trim();
							}
							if(rowList1[1] != null)
							{
								userrId = Long.parseLong(rowList1[1].toString().trim());
							}

						
					
					logger.info("EmpFullname and dsgn  emppId********" + emppId);
					logger.info("EmpFullname and dsgn userrId********" + userrId);
					
					
					String DesgnCode = StringUtility.getParameter("desgnCode",request);
					long DesignationId = payBillDAOImpl.getDesignationIdEployeeId(emppId);
					long DESIGNATIONId = payBillDAOImpl.getDesignationIdFromDsgnCode(DesgnCode);
					
					
					logger.info("EmpFullname and dsgn DesignationId" + DesignationId);
					logger.info("EmpFullname and dsgn DESIGNATIONId" + DESIGNATIONId);
					
					if(DESIGNATIONId==DesignationId)
					{
					
						//desgnCode=DesgnCode;
						designationId=DesignationId;
						employeeId=emppId;
						
				
					
					
					//for multiple
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					logger.info("dtlsList size is.."+dtlsList.size());
					long empId=0;
					String empPrf="";
					String empFname="";
					String empMname="";
					String empLName ="";
					String empFullname="";
					String dsgnName="";
					String gpfAccountNo="";
					String officeName="";
					String userId="";
					
					String userName="";
					
					String postId="123";
					
					String sevarthId="";
					String pfSeries="";
					
					if(!dtlsList.isEmpty())
					{
						for(int p=0;p<dtlsList.size();p++)
						{
							Object[] rowList = (Object[])dtlsList.get(p);
							empCustomVO =new EmployeeSearchCustomVO();
							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}
	
							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
									pfSeries = rowList[12].toString();
									logger.info("pfSeries::::::::::::::: "+pfSeries);
	
	
									if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
									{
										//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
										gpfAccountNo = rowList[3].toString();
									}
									else
									{
										gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
									}
								}
	
								else
								{
									gpfAccountNo = rowList[3].toString(); 
								}
	
								empCustomVO.setGpfAccountNo(gpfAccountNo);
	
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
								sevarthId = rowList[11].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}							
							empCustomVO.setPostId(postId);
						}
						 finalEmpList.add(empCustomVO);
					}
						}
					//end for multiple
						}
					}
					
				}
				
				//For employee name and gpfacctnumber
				else if (StringUtility.getParameter("empFname", request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empFname", request) ) &&!StringUtility.getParameter("empFname", request) .equals("-1") && StringUtility.getParameter("GPFAcctNo", request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("GPFAcctNo", request) ) && !StringUtility.getParameter("GPFAcctNo",request).equals("-1") )
				{

					logger.info("Enter into checking for EmpFullname and GPFAcctNo");


					String empfullnameinlocallanguage = "";
					String empLocalFName ="";
					String empLocalMName ="";
					String empLocalLName ="";

					if(StringUtility.getParameter("empFname", request)!=null && !StringUtility.getParameter("empFname", request).equals("")) 
					{
						empfullnameinlocallanguage = StringUtility.getParameter("empFname", request) != null ? StringUtility.getParameter("empFname", request) : "";
					}


					String b[] = empfullnameinlocallanguage.split(" ");

					if(b.length==3)
					{
						logger.info("Employee first name:"+b[0]);
						logger.info("Employee M name:"+b[1]);
						logger.info("Employee Last name:"+b[2]);
						empLocalFName = b[0];
						empLocalMName = b[1];
						empLocalLName = b[2];
					}
					else if(b.length==2)
					{
						logger.info("Employee first name:"+b[0]);
						logger.info("Employee Last name:"+b[1]);
						empLocalFName = b[0];
						empLocalLName = b[1];
					}
					else if(b.length==1)
					{
						logger.info("Employee first name:"+b[0]);
						empLocalFName = b[0];
					}
					else
					{
						logger.info("Employee First name:"+b[0]);
						empLocalFName = b[0];
						String EmpLocalMName="";
						for(int i=1;i<b.length-1;i++)
						{
							EmpLocalMName+=" "+b[i];
						}
						logger.info("Employee M name :"+EmpLocalMName);
						empLocalMName = EmpLocalMName;
						empLocalLName = b[b.length-1];
						logger.info("Employee Last name:"+b[b.length-1]);
					}


					Object[] postDtlsLst = null; 
					List employeeIdUserList = new ArrayList();
					employeeIdUserList  = payBillDAOImpl.getEmployeeIdListFromEployeeName(empLocalFName, empLocalMName, empLocalLName);

					
					
					
					String emppId="";
					long userrId=0;
					if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
					{
						for(int i=0;i<employeeIdUserList.size();i++)
						{

							Object[] rowList2 = (Object[])employeeIdUserList.get(i);

							if(rowList2[0] != null)
							{
								emppId = rowList2[0].toString().trim();
							}
							if(rowList2[1] != null)
							{
								userrId = Long.parseLong(rowList2[1].toString().trim());
							}





							long deIdFromEName = payBillDAOImpl.getDesignationIdEployeeId(emppId);
							logger.info("EmpFullname and GPFAcctNo  emppId********" + emppId);
							logger.info("EmpFullname and GPFAcctNo userrId********" + userrId);
							logger.info("EmpFullname and GPFAcctNo designationIdFromEmployeeName********" + deIdFromEName);



							String gpfaccotNo = StringUtility.getParameter("GPFAcctNo",request);
							String Flag = StringUtility.getParameter("Flag",request);
							
							String GPFACctNo ="";
							String PfSeries  = "";
							
							long number = gpfaccotNo.lastIndexOf("/");
							String[] charrarray={"0","1","2","3","4","5","6","7","8","9"};
							
							if(Flag.equals("true"))
							{
								String[] gpfAccotNo = gpfaccotNo.split("/");
								GPFACctNo = gpfAccotNo[gpfAccotNo.length-1];
								PfSeries="-1";
								logger.info("For numeric GPFAcctNo*********"+GPFACctNo );
							}
							
							else if(Flag.equals("false"))
							{
								if(number>0)
								{
									boolean containsFlag=false;
									for(int k=0;k<charrarray.length;k++)
									{
										if(gpfaccotNo.contains(charrarray[k]))
										{
											containsFlag=true;
											break;
										}
									}
									if(containsFlag==true)
									{
										String[] gpfAAccotNo = gpfaccotNo.split("/");
										GPFACctNo = gpfAAccotNo[gpfAAccotNo.length-1];
										logger.info(" number gpfaccotNo is with /************* "+GPFACctNo);

										PfSeries =gpfaccotNo.substring(0, gpfaccotNo.lastIndexOf("/"));
										logger.info("PfSeries is with /************* "+PfSeries);
									}
									else
									{

										PfSeries =gpfaccotNo;
										logger.info("PfSeries is without /************* "+PfSeries);
									}

								}
								else
								{
									if(gpfaccotNo.equalsIgnoreCase("dcps"))
									{
										PfSeries=gpfaccotNo;
									}
									else
									{
									GPFACctNo=gpfaccotNo;
									}
									logger.info("GPFACctNo singl account number is ******"+GPFACctNo);
								}
							}


							//String GPFACctNo = StringUtility.getParameter("GPFAcctNo",request);
							List GPFAccountNosUserList = new ArrayList();
							List gpfPfseriesList = new ArrayList();
							// GPFAccountNosUserList = payBillDAOImpl.getEmployeeIdAndUserIDFromGPFAcctNo(GPFACctNo);

							GPFAccountNosUserList = payBillDAOImpl.getEmployeeIdAndUserIDFromGPFAcctNo(GPFACctNo,PfSeries);

							String EmpId="";
							long UserrId=0;
							/*if(GPFAccountNosUserList!=null && GPFAccountNosUserList.size()!=0)
							{
								for(int j=0;j<GPFAccountNosUserList.size();j++)
								{

									Object[] rowList1 = (Object[])GPFAccountNosUserList.get(j);

									if(rowList1[0] != null)
									{
										EmpId = rowList1[0].toString().trim();
									}
									if(rowList1[1] != null)
									{
										UserrId = Long.parseLong(rowList1[1].toString().trim());
									}




									long dIdFromGPNo = payBillDAOImpl.getDesignationIdEployeeId(EmpId);

									logger.info("EmpFullname and GPFAcctNo emppId********" + EmpId);
									logger.info("EmpFullname and GPFAcctNo userrId********" + UserrId);
									logger.info("EmpFullname and GPFAcctNo designationIdFromGPFAcctNo********" + dIdFromGPNo);

									if(deIdFromEName>0 && dIdFromGPNo>0 && deIdFromEName==dIdFromGPNo && !EmpId.equals("") && !emppId.equals("") && EmpId.equals(emppId)) 
									{*/

							//with using gpfacctno or pfseries
							
							 gpfPfseriesList = payBillDAOImpl.getGPFAcctNoPfSeriesFromEployeeId(emppId);
							String gpfAcctNo="";
							String pfSeriesone="";
							if(gpfPfseriesList!=null && gpfPfseriesList.size()!=0)
							{
								for(int k=0;k<gpfPfseriesList.size();k++)
								{

									Object[] rowList1 = (Object[])gpfPfseriesList.get(k);

									if(rowList1[0] != null)
									{
										gpfAcctNo = rowList1[0].toString().trim();
									}
									if(rowList1[1] != null)
									{
										pfSeriesone = rowList1[1].toString().trim();
									}

						
							
							
							if(GPFACctNo.equals(gpfAcctNo) || PfSeries.equalsIgnoreCase(pfSeriesone))
							{
										designationId=deIdFromEName;
										employeeId=emppId;

										logger.info("EmpFullname and GPFAcctNo desgnCode" + designationId);
										logger.info("EmpFullname and GPFAcctNo employeeId" + employeeId);
										
										//for multiple
										EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
										dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
										logger.info("dtlsList size is.."+dtlsList.size());
										long empId=0;
										String empPrf="";
										String empFname="";
										String empMname="";
										String empLName ="";
										String empFullname="";
										String dsgnName="";
										String gpfAccountNo="";
										String officeName="";
										String userId="";

										String userName="";

										String postId="123";

										String sevarthId="";
										String pfSeries="";

										if(!dtlsList.isEmpty())
										{
											for(int p=0;p<dtlsList.size();p++)
											{
												Object[] rowList = (Object[])dtlsList.get(p);
												empCustomVO =new EmployeeSearchCustomVO();
												if(rowList[0] != null)
												{
													empId = Long.parseLong(rowList[0].toString().trim());
													empCustomVO.setEmpId(empId);
												}

												if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
													empFullname = rowList[1].toString();
													empCustomVO.setEmpFullname(empFullname);
													logger.info("empFullname::"+empFullname);
												}
												if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
													dsgnName = rowList[2].toString();
													empCustomVO.setDsgnName(dsgnName);
													logger.info("dsgnName::"+dsgnName);
												}
												if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
													if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
														pfSeries = rowList[12].toString();
														logger.info("pfSeries::::::::::::::: "+pfSeries);


														if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
														{
															//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
															gpfAccountNo = rowList[3].toString();
														}
														else
														{
															gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
														}
													}

													else
													{
														gpfAccountNo = rowList[3].toString(); 
													}

													empCustomVO.setGpfAccountNo(gpfAccountNo);

													logger.info("gpfAccountNo::"+gpfAccountNo);
												}
												if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
													officeName = rowList[4].toString();
													empCustomVO.setOfficeName(officeName);
													logger.info("officeName::"+officeName);
												}
												if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
													empPrf = rowList[5].toString();
													empCustomVO.setEmpPrf(empPrf);
													logger.info("empPrf::"+empPrf);
												}
												if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
													empFname = rowList[6].toString();
													empCustomVO.setEmpFname(empFname);
													logger.info("empFname::"+empFname);
												}
												if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
													empLName = rowList[7].toString();
													empCustomVO.setEmpLName(empLName);
													logger.info("empLName::"+empLName);
												}
												if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
													userId = rowList[8].toString();
													empCustomVO.setUserId(userId);
													logger.info("userId::"+userId);
												}
												if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
													userName = rowList[9].toString();
													empCustomVO.setUserName(userName);
													logger.info("userName::"+userName);
												}
												if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
													sevarthId = rowList[11].toString();
													empCustomVO.setSevarthEmpCode(sevarthId);
													logger.info("sevarthId:::::::::::::"+sevarthId);
												}							
												empCustomVO.setPostId(postId);
											}
											finalEmpList.add(empCustomVO);
										}
										
									}

								//}
						//	}
						}
					}
						}
					}

				}
				//FOR EMPFNAME AND OFFICE
				
				else if (StringUtility.getParameter("empFname", request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empFname", request)) &&!StringUtility.getParameter("empFname", request).equals("-1") && !StringUtility.getParameter("city", request).equals("-1"))
				{
					
					//if(StringUtility.getParameter("empFname", request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empFname", request)) &&!StringUtility.getParameter("empFname", request).equals("-1"))
						
					
					logger.info("Enter into checking for EmpFullname and Office");

					String empfullnameinlocallanguage = "";
					String empLocalFName ="";
					String empLocalMName ="";
					String empLocalLName ="";
					
					 if(StringUtility.getParameter("empFname", request)!=null && !StringUtility.getParameter("empFname", request).equals("")) 
			            {
						 empfullnameinlocallanguage = StringUtility.getParameter("empFname", request) != null ? StringUtility.getParameter("empFname", request) : "";
			            }
					 
					 
					 String b[] = empfullnameinlocallanguage.split(" ");

						if(b.length==3)
						{
							logger.info("Employee first name:"+b[0]);
							logger.info("Employee M name:"+b[1]);
							logger.info("Employee Last name:"+b[2]);
							empLocalFName = b[0];
							empLocalMName = b[1];
							empLocalLName = b[2];
						}
						else if(b.length==2)
						{
							logger.info("Employee first name:"+b[0]);
							logger.info("Employee Last name:"+b[1]);
							empLocalFName = b[0];
							empLocalLName = b[1];
						}
						else if(b.length==1)
						{
							logger.info("Employee first name:"+b[0]);
							empLocalFName = b[0];
						}
						else
						{
							logger.info("Employee First name:"+b[0]);
							empLocalFName = b[0];
							String EmpLocalMName="";
							for(int i=1;i<b.length-1;i++)
							{
								EmpLocalMName+=" "+b[i];
							}
							logger.info("Employee M name :"+EmpLocalMName);
							empLocalMName = EmpLocalMName;
							empLocalLName = b[b.length-1];
							logger.info("Employee Last name:"+b[b.length-1]);
						}
					
					
					Object[] postDtlsLst = null; 
					List employeeIdUserList = new ArrayList();
					employeeIdUserList  = payBillDAOImpl.getEmployeeIdListFromEployeeName(empLocalFName, empLocalMName, empLocalLName);
					
					String EmployeeId="";
					long userrId=0;
					String EMPLOYEEID = "";
					String DesignationCode ="";
					logger.info("employeeIdUserList for both employee and office name  sizeee**"+employeeIdUserList.size());
					if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
					{
						for(int i=0;i<employeeIdUserList.size();i++)
						{

							Object[] rowList1 = (Object[])employeeIdUserList.get(i);

							if(rowList1[0] != null)
							{
								EmployeeId = rowList1[0].toString().trim();
							}
							if(rowList1[1] != null)
							{
								userrId = Long.parseLong(rowList1[1].toString().trim());
							}

						
					
					DesignationCode = payBillDAOImpl.getdesignationIdEployeeId(EmployeeId);
															
					
					dcpsDdoOfficeIdPk = StringUtility.getParameter("city",request);
					//String EMPLOYEEID = payBillDAOImpl.getEmployeeIdFromOfficeId(EmployeeId, OfficeId);
					
					//List officeList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,OfficeId,employeeId,designationId,GPFAcctNo);
					//System.out.println("officeList for both employee and office name size is*********"+officeList.size());
					
					/*for(int j=0;j<officeList.size();j++)
					{
						Object[] rowlist3 = (Object[])officeList.get(j);
						
						if(rowlist3[0]!=null)
						{
							EMPLOYEEID=	rowlist3[0].toString();
						}*/
						
					
					
					logger.info("Enter into checking for EmpFullname and office EmployeeId" +EmployeeId);
					logger.info("Enter into checking for EmpFullname and office  DesignationCode" +DesignationCode);
					logger.info("Enter into checking for EmpFullname and office  EMPLOYEEID" +EMPLOYEEID);
					
					
					/*if(EmployeeId.equals(EMPLOYEEID))
					{*/
						
						employeeId = EmployeeId;
						desgnCode=DesignationCode;
						logger.info("both EmpFullname and office employeeId "+employeeId);
						
						
					
					//for multiple
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					logger.info("dtlsList size is.."+dtlsList.size());
					long empId=0;
					String empPrf="";
					String empFname="";
					String empMname="";
					String empLName ="";
					String empFullname="";
					String dsgnName="";
					String gpfAccountNo="";
					String officeName="";
					String userId="";
					
					String userName="";
					
					String postId="123";
					
					String sevarthId="";
					String pfSeries="";
					
					if(!dtlsList.isEmpty())
					{
						for(int p=0;p<dtlsList.size();p++)
						{
							Object[] rowList = (Object[])dtlsList.get(p);
							empCustomVO =new EmployeeSearchCustomVO();
							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}
	
							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
									pfSeries = rowList[12].toString();
									logger.info("pfSeries::::::::::::::: "+pfSeries);
	
	
									if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
									{
										//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
										gpfAccountNo = rowList[3].toString();
									}
									else
									{
										gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
									}
								}
	
								else
								{
									gpfAccountNo = rowList[3].toString(); 
								}
	
								empCustomVO.setGpfAccountNo(gpfAccountNo);
	
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
								sevarthId = rowList[11].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}							
							empCustomVO.setPostId(postId);
						}
						 finalEmpList.add(empCustomVO);
					}
						//}
					//end for multiple
					
						//}
					}
					}
					
					
				}
				
				//END
				//For employeename only
				else if (StringUtility.getParameter("empFname", request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empFname", request)) &&!StringUtility.getParameter("empFname", request).equals("-1"))
				{
					
					//if(StringUtility.getParameter("empFname", request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empFname", request)) &&!StringUtility.getParameter("empFname", request).equals("-1"))
						
					
					logger.info("Enter into checking for EmpFullname");

					String empfullnameinlocallanguage = "";
					String empLocalFName ="";
					String empLocalMName ="";
					String empLocalLName ="";
					
					 if(StringUtility.getParameter("empFname", request)!=null && !StringUtility.getParameter("empFname", request).equals("")) 
			            {
						 empfullnameinlocallanguage = StringUtility.getParameter("empFname", request) != null ? StringUtility.getParameter("empFname", request) : "";
			            }
					 
					 
					 String b[] = empfullnameinlocallanguage.split(" ");

						if(b.length==3)
						{
							logger.info("Employee first name:"+b[0]);
							logger.info("Employee M name:"+b[1]);
							logger.info("Employee Last name:"+b[2]);
							empLocalFName = b[0];
							empLocalMName = b[1];
							empLocalLName = b[2];
						}
						else if(b.length==2)
						{
							logger.info("Employee first name:"+b[0]);
							logger.info("Employee Last name:"+b[1]);
							empLocalFName = b[0];
							empLocalLName = b[1];
						}
						else if(b.length==1)
						{
							logger.info("Employee first name:"+b[0]);
							empLocalFName = b[0];
						}
						else
						{
							logger.info("Employee First name:"+b[0]);
							empLocalFName = b[0];
							String EmpLocalMName="";
							for(int i=1;i<b.length-1;i++)
							{
								EmpLocalMName+=" "+b[i];
							}
							logger.info("Employee M name :"+EmpLocalMName);
							empLocalMName = EmpLocalMName;
							empLocalLName = b[b.length-1];
							logger.info("Employee Last name:"+b[b.length-1]);
						}
					
					
					Object[] postDtlsLst = null; 
					List employeeIdUserList = new ArrayList();
					employeeIdUserList  = payBillDAOImpl.getEmployeeIdListFromEployeeName(empLocalFName, empLocalMName, empLocalLName);
					
					String EmployeeId="";
					long userrId=0;
					if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
					{
						for(int i=0;i<employeeIdUserList.size();i++)
						{

							Object[] rowList1 = (Object[])employeeIdUserList.get(i);

							if(rowList1[0] != null)
							{
								EmployeeId =  rowList1[0].toString().trim();
							}
							if(rowList1[1] != null)
							{
								userrId = Long.parseLong(rowList1[1].toString().trim());
							}

						
					
					
					logger.info("EmpFullname EmployeeId********" + EmployeeId);
					logger.info("EmpFullname userrId********" + userrId);
					
					if(EmployeeId!=null && !EmployeeId.equals("") && !EmployeeId.equals("-1"))
					{
					designationId = payBillDAOImpl.getDesignationIdEployeeId(EmployeeId);
					employeeId = EmployeeId;
					logger.info("EmpFullname designationId" + designationId);
					}
					
					//for multiple
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					logger.info("dtlsList size is.."+dtlsList.size());
					long empId=0;
					String empPrf="";
					String empFname="";
					String empMname="";
					String empLName ="";
					String empFullname="";
					String dsgnName="";
					String gpfAccountNo="";
					String officeName="";
					String userId="";
					
					String userName="";
					
					String postId="123";
					
					String sevarthId="";
					String pfSeries="";
					
					if(!dtlsList.isEmpty())
					{
						for(int p=0;p<dtlsList.size();p++)
						{
							Object[] rowList = (Object[])dtlsList.get(p);
							empCustomVO =new EmployeeSearchCustomVO();
							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}
	
							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								if(rowList[12] != null && !(rowList[12].toString().trim()).equals(""))
								{
									pfSeries = rowList[12].toString();
									logger.info("pfSeries::::::::::::::: "+pfSeries);
	
	
									if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
									{
										//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
										gpfAccountNo = rowList[3].toString();
									}
									else
									{
										gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
									}
								}
	
								else
								{
									gpfAccountNo = rowList[3].toString(); 
								}
	
								empCustomVO.setGpfAccountNo(gpfAccountNo);
	
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
								sevarthId = rowList[11].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}							
							empCustomVO.setPostId(postId);
						}
						 finalEmpList.add(empCustomVO);
					}
						}
					}
				}
				
				
				//for employee id and gpfacctno
				
				else if(StringUtility.getParameter("empId",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empId",request)) && !StringUtility.getParameter("empId",request).equals("-1") &&  StringUtility.getParameter("GPFAcctNo", request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("GPFAcctNo", request) ) && !StringUtility.getParameter("GPFAcctNo",request).equals("-1") )
				{
					
					logger.info("Enter into checking for employeeid and GPFAcctNo");
					
					List gpfPfseriesList = new ArrayList();


					String gpfaccotNo = StringUtility.getParameter("GPFAcctNo",request);
					String Flag = StringUtility.getParameter("Flag",request);
					
					String GPFACctNo ="";
					String PfSeries  = "";
					
					long number = gpfaccotNo.lastIndexOf("/");
					String[] charrarray={"0","1","2","3","4","5","6","7","8","9"};
					
					if(Flag.equals("true"))
					{
						String[] gpfAccotNo = gpfaccotNo.split("/");
						GPFACctNo = gpfAccotNo[gpfAccotNo.length-1];
						PfSeries="-1";
						logger.info("For numeric GPFAcctNo*********"+GPFACctNo );
					}
					
					else if(Flag.equals("false"))
					{
						if(number>0)
						{
							boolean containsFlag=false;
							for(int k=0;k<charrarray.length;k++)
							{
								if(gpfaccotNo.contains(charrarray[k]))
								{
									containsFlag=true;
									break;
								}
							}
							if(containsFlag==true)
							{
								String[] gpfAAccotNo = gpfaccotNo.split("/");
								GPFACctNo = gpfAAccotNo[gpfAAccotNo.length-1];
								logger.info(" number gpfaccotNo is with /************* "+GPFACctNo);

								PfSeries =gpfaccotNo.substring(0, gpfaccotNo.lastIndexOf("/"));
								logger.info("PfSeries is with /************* "+PfSeries);
							}
							else
							{

								PfSeries =gpfaccotNo;
								logger.info("PfSeries is without /************* "+PfSeries);
							}

						}
						else
						{
							if(gpfaccotNo.equalsIgnoreCase("dcps"))
							{
								PfSeries=gpfaccotNo;
							}
							else
							{
							GPFACctNo=gpfaccotNo;
							}
							logger.info("GPFACctNo singl account number is ******"+GPFACctNo);
						}
					}
					
					
					
					//added
					
					String sevarthid = StringUtility.getParameter("empId",request);
					List employeeIdList = payBillDAOImpl.getEmployeeIdsFromSevarthId(sevarthid);
					
					String employeeid= "";
					
					for(int i=0;i<employeeIdList.size();i++)
					{
						
						Object[] rowList1 = (Object[])employeeIdList.get(i);
						
						if(rowList1[0] != null)
						{
							employeeid = rowList1[0].toString().trim();
						}
						
						logger.info("employeeid is *******"+employeeid);
					 
					
						logger.info("Enter into checking for employeeid and desgnCode EmployeeId" +employeeid);
						
					//with using gpfacctno or pfseries
						
					 gpfPfseriesList = payBillDAOImpl.getGPFAcctNoPfSeriesFromEployeeId(employeeid);
					String gpfAcctNo="";
					String pfSeriesone="";
					if(gpfPfseriesList!=null && gpfPfseriesList.size()!=0)
					{
						for(int k=0;k<gpfPfseriesList.size();k++)
						{

							Object[] rowList2 = (Object[])gpfPfseriesList.get(k);

							if(rowList2[0] != null)
							{
								gpfAcctNo = rowList2[0].toString().trim();
							}
							if(rowList2[1] != null)
							{
								pfSeriesone = rowList2[1].toString().trim();
							}

				
					
					
					if(GPFACctNo.equals(gpfAcctNo) || PfSeries.equalsIgnoreCase(pfSeriesone))
					{
						employeeId = employeeid;
						GPFACctNo = GPFAcctNo;
						logger.info("both empid and dsgnid employeeId "+employeeId);
						
						//for multiple
						EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
						dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
						logger.info("dtlsList size is.."+dtlsList.size());
						long empId=0;
						String empPrf="";
						String empFname="";
						String empMname="";
						String empLName ="";
						String empFullname="";
						String dsgnName="";
						String gpfAccountNo="";
						String officeName="";
						String userId="";
						
						String userName="";
						
						String postId="123";
						
						String sevarthId="";
						String pfSeries="";
						
						if(!dtlsList.isEmpty())
						{
							for(int p=0;p<dtlsList.size();p++)
							{
								Object[] rowList = (Object[])dtlsList.get(p);
								empCustomVO =new EmployeeSearchCustomVO();
								if(rowList[0] != null)
								{
									empId = Long.parseLong(rowList[0].toString().trim());
									empCustomVO.setEmpId(empId);
								}
		
								if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
									empFullname = rowList[1].toString();
									empCustomVO.setEmpFullname(empFullname);
									logger.info("empFullname::"+empFullname);
								}
								if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
									dsgnName = rowList[2].toString();
									empCustomVO.setDsgnName(dsgnName);
									logger.info("dsgnName::"+dsgnName);
								}
								if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
									if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
										pfSeries = rowList[12].toString();
										logger.info("pfSeries::::::::::::::: "+pfSeries);
		
		
										if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
										{
											//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
											gpfAccountNo = rowList[3].toString();
										}
										else
										{
											gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
										}
									}
		
									else
									{
										gpfAccountNo = rowList[3].toString(); 
									}
		
									empCustomVO.setGpfAccountNo(gpfAccountNo);
		
									logger.info("gpfAccountNo::"+gpfAccountNo);
								}
								if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
									officeName = rowList[4].toString();
									empCustomVO.setOfficeName(officeName);
									logger.info("officeName::"+officeName);
								}
								if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
									empPrf = rowList[5].toString();
									empCustomVO.setEmpPrf(empPrf);
									logger.info("empPrf::"+empPrf);
								}
								if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
									empFname = rowList[6].toString();
									empCustomVO.setEmpFname(empFname);
									logger.info("empFname::"+empFname);
								}
								if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
									empLName = rowList[7].toString();
									empCustomVO.setEmpLName(empLName);
									logger.info("empLName::"+empLName);
								}
								if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
									userId = rowList[8].toString();
									empCustomVO.setUserId(userId);
									logger.info("userId::"+userId);
								}
								if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
									userName = rowList[9].toString();
									empCustomVO.setUserName(userName);
									logger.info("userName::"+userName);
								}
								if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
									sevarthId = rowList[11].toString();
									empCustomVO.setSevarthEmpCode(sevarthId);
									logger.info("sevarthId:::::::::::::"+sevarthId);
								}							
								empCustomVO.setPostId(postId);
							}
							 finalEmpList.add(empCustomVO);
						}
					}
					
					}
				}
				}
					
				}
				
				//for employee name and dsgnname
				
				else if(StringUtility.getParameter("empId",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empId",request)) && !StringUtility.getParameter("empId",request).equals("-1") && !StringUtility.getParameter("desgnCode",request).equals("-1") )
				{
					
					logger.info("Enter into checking for employeeid and desgnCode");
					
				
					
					String sevarthid = StringUtility.getParameter("empId",request);
					
					
					//added
					List employeeIdList = payBillDAOImpl.getEmployeeIdsFromSevarthId(sevarthid);
					
					String EmployeeId= "";
					
					for(int i=0;i<employeeIdList.size();i++)
					{
						
						Object[] rowList1 = (Object[])employeeIdList.get(i);
						
						if(rowList1[0] != null)
						{
							EmployeeId = rowList1[0].toString().trim();
						}
						
						logger.info("EmployeeId is *******"+EmployeeId);
					
					//ended
					
					
					//String EmployeeId = payBillDAOImpl.getEmployeeIdFromSevarthId(sevarthid);
					String DesignationCode = payBillDAOImpl.getdesignationIdEployeeId(EmployeeId);
					
					String DesgnCode = StringUtility.getParameter("desgnCode",request);
					String DESIGNATIONCODE = DesgnCode;
					
					
					
					logger.info("Enter into checking for employeeid and desgnCode EmployeeId" +EmployeeId);
					logger.info("Enter into checking for employeeid and desgnCode  DesignationCode" +DesignationCode);
					logger.info("Enter into checking for employeeid and desgnCode  DesignationCode" +DESIGNATIONCODE);
					
					
					if(DesignationCode.equals(DESIGNATIONCODE))
					{
						
						employeeId = EmployeeId;
						desgnCode=DesgnCode;
						logger.info("both empid and dsgnid employeeId "+employeeId);
						
						//for multiple
						EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
						dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
						logger.info("dtlsList size is.."+dtlsList.size());
						long empId=0;
						String empPrf="";
						String empFname="";
						String empMname="";
						String empLName ="";
						String empFullname="";
						String dsgnName="";
						String gpfAccountNo="";
						String officeName="";
						String userId="";
						
						String userName="";
						
						String postId="123";
						
						String sevarthId="";
						String pfSeries="";
						
						if(!dtlsList.isEmpty())
						{
							for(int p=0;p<dtlsList.size();p++)
							{
								Object[] rowList = (Object[])dtlsList.get(p);
								empCustomVO =new EmployeeSearchCustomVO();
								if(rowList[0] != null)
								{
									empId = Long.parseLong(rowList[0].toString().trim());
									empCustomVO.setEmpId(empId);
								}
		
								if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
									empFullname = rowList[1].toString();
									empCustomVO.setEmpFullname(empFullname);
									logger.info("empFullname::"+empFullname);
								}
								if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
									dsgnName = rowList[2].toString();
									empCustomVO.setDsgnName(dsgnName);
									logger.info("dsgnName::"+dsgnName);
								}
								if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
									if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
										pfSeries = rowList[12].toString();
										logger.info("pfSeries::::::::::::::: "+pfSeries);
		
		
										if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
										{
											//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
											gpfAccountNo = rowList[3].toString();
										}
										else
										{
											gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
										}
									}
		
									else
									{
										gpfAccountNo = rowList[3].toString(); 
									}
		
									empCustomVO.setGpfAccountNo(gpfAccountNo);
		
									logger.info("gpfAccountNo::"+gpfAccountNo);
								}
								if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
									officeName = rowList[4].toString();
									empCustomVO.setOfficeName(officeName);
									logger.info("officeName::"+officeName);
								}
								if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
									empPrf = rowList[5].toString();
									empCustomVO.setEmpPrf(empPrf);
									logger.info("empPrf::"+empPrf);
								}
								if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
									empFname = rowList[6].toString();
									empCustomVO.setEmpFname(empFname);
									logger.info("empFname::"+empFname);
								}
								if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
									empLName = rowList[7].toString();
									empCustomVO.setEmpLName(empLName);
									logger.info("empLName::"+empLName);
								}
								if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
									userId = rowList[8].toString();
									empCustomVO.setUserId(userId);
									logger.info("userId::"+userId);
								}
								if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
									userName = rowList[9].toString();
									empCustomVO.setUserName(userName);
									logger.info("userName::"+userName);
								}
								if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
									sevarthId = rowList[11].toString();
									empCustomVO.setSevarthEmpCode(sevarthId);
									logger.info("sevarthId:::::::::::::"+sevarthId);
								}							
								empCustomVO.setPostId(postId);
							}
							 finalEmpList.add(empCustomVO);
						}
						
					}
					}
					
					
				}
				
				//For employeename and office
				
				else if(StringUtility.getParameter("empId",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empId",request)) && !StringUtility.getParameter("empId",request).equals("-1") && !StringUtility.getParameter("city",request).equals("-1") )
				{
					
					logger.info("Enter into checking for employeeid and office");
					
				
					
					String sevarthid = StringUtility.getParameter("empId",request);
					
					List employeeIdList = payBillDAOImpl.getEmployeeIdsFromSevarthId(sevarthid);
					String employeeid= "";
					for(int i=0;i<employeeIdList.size();i++)
					{
						
						Object[] rowList1 = (Object[])employeeIdList.get(i);
						
						if(rowList1[0] != null)
						{
							employeeid = rowList1[0].toString().trim();
						}
						
						logger.info("employeeid is *******"+employeeid);
					
					//String EmployeeId = payBillDAOImpl.getEmployeeIdFromSevarthId(sevarthId);
					
					String EmployeeId=employeeid;
					String DesignationCode = payBillDAOImpl.getdesignationIdEployeeId(EmployeeId);
					dcpsDdoOfficeIdPk = StringUtility.getParameter("city",request);
					//String EMPLOYEEID = payBillDAOImpl.getEmployeeIdFromOfficeId(EmployeeId, OfficeId);
					
					logger.info("Enter into checking for employeeid and office EmployeeId" +EmployeeId);
					logger.info("Enter into checking for employeeid and office  DesignationCode" +DesignationCode);
					logger.info("Enter into checking for employeeid and office  dcpsDdoOfficeIdPk" +dcpsDdoOfficeIdPk);
					
						employeeId = EmployeeId;
						desgnCode=DesignationCode;
						logger.info("both empid and dsgnid employeeId "+employeeId);
					
					//for multiple
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					logger.info("dtlsList size is.."+dtlsList.size());
					long empId=0;
					String empPrf="";
					String empFname="";
					String empMname="";
					String empLName ="";
					String empFullname="";
					String dsgnName="";
					String gpfAccountNo="";
					String officeName="";
					String userId="";
					
					String userName="";
					
					String postId="123";
					
					String sevarthId="";
					String pfSeries="";
					
					if(!dtlsList.isEmpty())
					{
						for(int p=0;p<dtlsList.size();p++)
						{
							Object[] rowList = (Object[])dtlsList.get(p);
							empCustomVO =new EmployeeSearchCustomVO();
							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}
	
							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
									pfSeries = rowList[12].toString();
									logger.info("pfSeries::::::::::::::: "+pfSeries);
	
	
									if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
									{
										//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
										gpfAccountNo = rowList[3].toString();
									}
									else
									{
										gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
									}
								}
	
								else
								{
									gpfAccountNo = rowList[3].toString(); 
								}
	
								empCustomVO.setGpfAccountNo(gpfAccountNo);
	
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
								sevarthId = rowList[11].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}							
							empCustomVO.setPostId(postId);
						}
						 finalEmpList.add(empCustomVO);
					}
					//end for multiple
					
					}
					
				}
				
				//end
				
				//only for employeeid
				
				else if(StringUtility.getParameter("empId",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("empId",request)) &&!StringUtility.getParameter("empId",request).equals("-1")  )
				{
					
					
					String sevarthid = StringUtility.getParameter("empId",request);
					
					//String employeeid = payBillDAOImpl.getEmployeeIdFromSevarthId(sevarthid);
					List employeeIdList = payBillDAOImpl.getEmployeeIdsFromSevarthId(sevarthid);
					
					String employeeid= "";
					
					for(int i=0;i<employeeIdList.size();i++)
					{
						
						Object[] rowList1 = (Object[])employeeIdList.get(i);
						
						if(rowList1[0] != null)
						{
							employeeid = rowList1[0].toString().trim();
						}
						
						logger.info("employeeid is *******"+employeeid);
					 employeeId = employeeid;
					 
					long DesignationId = payBillDAOImpl.getDesignationIdEployeeId(employeeId);
					
					
					if(DesignationId>0)
					{
					
						designationId =DesignationId;
						
					logger.info("enter into for checking employeeid*********" );
					logger.info("employeeId*********" + employeeId);
					logger.info("designationId*********" + designationId);
					
					}

					//for multiple
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					logger.info("dtlsList size is.."+dtlsList.size());
					long empId=0;
					String empPrf="";
					String empFname="";
					String empMname="";
					String empLName ="";
					String empFullname="";
					String dsgnName="";
					String gpfAccountNo="";
					String officeName="";
					String userId="";
					
					String userName="";
					
					String postId="123";
					
					String sevarthId="";
					String pfSeries="";
					
					if(!dtlsList.isEmpty())
					{
						for(int p=0;p<dtlsList.size();p++)
						{
							Object[] rowList = (Object[])dtlsList.get(p);
							empCustomVO =new EmployeeSearchCustomVO();
							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}
	
							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
									pfSeries = rowList[12].toString();
									logger.info("pfSeries::::::::::::::: "+pfSeries);
	
	
									if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
									{
										//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
										gpfAccountNo = rowList[3].toString();
									}
									else
									{
										gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
									}
								}
	
								else
								{
									gpfAccountNo = rowList[3].toString(); 
								}
	
								empCustomVO.setGpfAccountNo(gpfAccountNo);
	
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
								sevarthId = rowList[11].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}							
							empCustomVO.setPostId(postId);
						}
						 finalEmpList.add(empCustomVO);
					}
					}
					//end multiple
					
					
				}
				
				
				//for gpfacctno and dsgncode
				else if(StringUtility.getParameter("GPFAcctNo",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("GPFAcctNo",request)) &&!StringUtility.getParameter("GPFAcctNo",request).equals("-1") && !StringUtility.getParameter("desgnCode",request).equals("-1"))
				{

					logger.info("Enter into checking for GPFAcctNo and desgnCode");


					List employeeIdUserList = new ArrayList();
					String gpfaccotNo = StringUtility.getParameter("GPFAcctNo",request);
					String Flag = StringUtility.getParameter("Flag",request);
					
					String GPFACctNo ="";
					String PfSeries  = "";
					
					long number = gpfaccotNo.lastIndexOf("/");
					String[] charrarray={"0","1","2","3","4","5","6","7","8","9"};
					
					if(Flag.equals("true"))
					{
						String[] gpfAccotNo = gpfaccotNo.split("/");
						GPFACctNo = gpfAccotNo[gpfAccotNo.length-1];
						PfSeries="-1";
						logger.info("For numeric GPFAcctNo*********"+GPFACctNo );
					}
					
					else if(Flag.equals("false"))
					{
						if(number>0)
						{
							boolean containsFlag=false;
							for(int i=0;i<charrarray.length;i++)
							{
								if(gpfaccotNo.contains(charrarray[i]))
								{
									containsFlag=true;
									break;
								}
							}
							if(containsFlag==true)
							{
								String[] gpfAAccotNo = gpfaccotNo.split("/");
								GPFACctNo = gpfAAccotNo[gpfAAccotNo.length-1];
								logger.info(" number gpfaccotNo is with /************* "+GPFACctNo);

								PfSeries =gpfaccotNo.substring(0, gpfaccotNo.lastIndexOf("/"));
								logger.info("PfSeries is with /************* "+PfSeries);
							}
							else
							{

								PfSeries =gpfaccotNo;
								logger.info("PfSeries is without /************* "+PfSeries);
							}

						}
						else
						{
							if(gpfaccotNo.equalsIgnoreCase("dcps"))
							{
								PfSeries=gpfaccotNo;
							}
							else
							{
							GPFACctNo=gpfaccotNo;
							}
							logger.info("GPFACctNo singl account number is ******"+GPFACctNo);
						}
					}

					employeeIdUserList = payBillDAOImpl.getEmployeeIdAndUserIDFromGPFAcctNo(GPFACctNo,PfSeries);
					
					String emppId="";
					long userrId=0;
					if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
					{
						for(Iterator itr=employeeIdUserList.iterator();itr.hasNext();)
						{

							Object[] rowList1 = (Object[])itr.next();

							if(rowList1[0] != null)
							{
								emppId = rowList1[0].toString().trim();
							}
							if(rowList1[1] != null)
							{
								userrId = Long.parseLong(rowList1[1].toString().trim());
							}


							//String DesignationCode = payBillDAOImpl.getDesignationIdEployeeId(empId);

							String DesignationCode = payBillDAOImpl.getdesignationIdEployeeId(emppId);

							String DESIGNATIONCODE = StringUtility.getParameter("desgnCode",request);


							logger.info("GPFAcctNo desgnCode***DesignationCode*****" + DesignationCode);
							logger.info("GPFAcctNo desgnCode****DESIGNATIONCODE****" + DESIGNATIONCODE);



							if(DesignationCode.equals(DESIGNATIONCODE))
							{
								employeeId=emppId;
								desgnCode=DESIGNATIONCODE;

								logger.info("both GPFAcctNo and dsgnid desgnCode "+DESIGNATIONCODE);
								logger.info("both GPFAcctNo and dsgnid GPFAcctNo "+GPFAcctNo);


								EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
								dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
								logger.info("dtlsList size is.."+dtlsList.size());
								long empId=0;
								String empPrf="";
								String empFname="";
								String empMname="";
								String empLName ="";
								String empFullname="";
								String dsgnName="";
								String gpfAccountNo="";
								String officeName="";
								String userId="";

								String userName="";

								String postId="123";

								String sevarthId="";
								String pfSeries="";

								if(!dtlsList.isEmpty())
								{
									for(int p=0;p<dtlsList.size();p++)
									{
										Object[] rowList = (Object[])dtlsList.get(p);
										empCustomVO =new EmployeeSearchCustomVO();
										if(rowList[0] != null)
										{
											empId = Long.parseLong(rowList[0].toString().trim());
											empCustomVO.setEmpId(empId);
										}

										if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
											empFullname = rowList[1].toString();
											empCustomVO.setEmpFullname(empFullname);
											logger.info("empFullname::"+empFullname);
										}
										if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
											dsgnName = rowList[2].toString();
											empCustomVO.setDsgnName(dsgnName);
											logger.info("dsgnName::"+dsgnName);
										}
										if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
											if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
												pfSeries = rowList[12].toString();
												logger.info("pfSeries::::::::::::::: "+pfSeries);


												if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
												{
													//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
													gpfAccountNo = rowList[3].toString();
												}
												else
												{
													gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
												}
											}

											else
											{
												gpfAccountNo = rowList[3].toString(); 
											}

											empCustomVO.setGpfAccountNo(gpfAccountNo);

											logger.info("gpfAccountNo::"+gpfAccountNo);
										}
										if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
											officeName = rowList[4].toString();
											empCustomVO.setOfficeName(officeName);
											logger.info("officeName::"+officeName);
										}
										if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
											empPrf = rowList[5].toString();
											empCustomVO.setEmpPrf(empPrf);
											logger.info("empPrf::"+empPrf);
										}
										if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
											empFname = rowList[6].toString();
											empCustomVO.setEmpFname(empFname);
											logger.info("empFname::"+empFname);
										}
										if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
											empLName = rowList[7].toString();
											empCustomVO.setEmpLName(empLName);
											logger.info("empLName::"+empLName);
										}
										if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
											userId = rowList[8].toString();
											empCustomVO.setUserId(userId);
											logger.info("userId::"+userId);
										}
										if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
											userName = rowList[9].toString();
											empCustomVO.setUserName(userName);
											logger.info("userName::"+userName);
										}
										if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
											sevarthId = rowList[11].toString();
											empCustomVO.setSevarthEmpCode(sevarthId);
											logger.info("sevarthId:::::::::::::"+sevarthId);
										}							
										empCustomVO.setPostId(postId);
									}
									finalEmpList.add(empCustomVO);
								}
								//end multiple
							}
						}
					}

				}
				
				
				//For gpfacctno and office
				
				else if(StringUtility.getParameter("GPFAcctNo",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("GPFAcctNo",request)) &&!StringUtility.getParameter("GPFAcctNo",request).equals("-1") && !StringUtility.getParameter("city",request).equals("-1"))
				{

					logger.info("Enter into checking for GPFAcctNo and Office");


					List employeeIdUserList = new ArrayList();
					String gpfaccotNo = StringUtility.getParameter("GPFAcctNo",request);
					String Flag = StringUtility.getParameter("Flag",request);
					
					String GPFACctNo ="";
					String PfSeries  = "";
					
					long number = gpfaccotNo.lastIndexOf("/");
					String[] charrarray={"0","1","2","3","4","5","6","7","8","9"};
					
					if(Flag.equals("true"))
					{
						String[] gpfAccotNo = gpfaccotNo.split("/");
						GPFACctNo = gpfAccotNo[gpfAccotNo.length-1];
						PfSeries="-1";
						logger.info("For numeric GPFAcctNo*********"+GPFACctNo );
					}
					
					else if(Flag.equals("false"))
					{
						if(number>0)
						{
							boolean containsFlag=false;
							for(int i=0;i<charrarray.length;i++)
							{
								if(gpfaccotNo.contains(charrarray[i]))
								{
									containsFlag=true;
									break;
								}
							}
							if(containsFlag==true)
							{
								String[] gpfAAccotNo = gpfaccotNo.split("/");
								GPFACctNo = gpfAAccotNo[gpfAAccotNo.length-1];
								logger.info(" number gpfaccotNo is with /************* "+GPFACctNo);

								PfSeries =gpfaccotNo.substring(0, gpfaccotNo.lastIndexOf("/"));
								logger.info("PfSeries is with /************* "+PfSeries);
							}
							else
							{

								PfSeries =gpfaccotNo;
								logger.info("PfSeries is without /************* "+PfSeries);
							}

						}
						else
						{
							if(gpfaccotNo.equalsIgnoreCase("dcps"))
							{
								PfSeries=gpfaccotNo;
							}
							else
							{
							GPFACctNo=gpfaccotNo;
							}
							logger.info("GPFACctNo singl account number is ******"+GPFACctNo);
						}
					}

					employeeIdUserList = payBillDAOImpl.getEmployeeIdAndUserIDFromGPFAcctNo(GPFACctNo,PfSeries);


					String emppId="";
					long userrId=0;
					if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
					{
						for(Iterator itr=employeeIdUserList.iterator();itr.hasNext();)
						{

							Object[] rowList1 = (Object[])itr.next();

							if(rowList1[0] != null)
							{
								emppId = rowList1[0].toString().trim();
							}
							if(rowList1[1] != null)
							{
								userrId = Long.parseLong(rowList1[1].toString().trim());
							}

						


					String DesignationCode = payBillDAOImpl.getdesignationIdEployeeId(emppId);

					dcpsDdoOfficeIdPk = StringUtility.getParameter("city",request);
					//String EMPLOYEEID = payBillDAOImpl.getEmployeeIdFromOfficeId(emppId, OfficeId);

					logger.info("Enter into checking for GPFAcctNo and office EmployeeId" +emppId);
					logger.info("Enter into checking for GPFAcctNo and office  DesignationCode" +DesignationCode);


						employeeId = emppId;
						desgnCode=DesignationCode;
					
					
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					logger.info("dtlsList size is.."+dtlsList.size());
					long empId=0;
					String empPrf="";
					String empFname="";
					String empMname="";
					String empLName ="";
					String empFullname="";
					String dsgnName="";
					String gpfAccountNo="";
					String officeName="";
					String userId="";
					
					String userName="";
					
					String postId="123";
					
					String sevarthId="";
					String pfSeries="";
					
					if(!dtlsList.isEmpty())
					{
						for(int p=0;p<dtlsList.size();p++)
						{
							Object[] rowList = (Object[])dtlsList.get(p);
							empCustomVO =new EmployeeSearchCustomVO();
							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}
	
							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
									pfSeries = rowList[12].toString();
									logger.info("pfSeries::::::::::::::: "+pfSeries);
	
	
									if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
									{
										//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
										gpfAccountNo = rowList[3].toString();
									}
									else
									{
										gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
									}
								}
	
								else
								{
									gpfAccountNo = rowList[3].toString(); 
								}
	
								empCustomVO.setGpfAccountNo(gpfAccountNo);
	
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
								sevarthId = rowList[11].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}							
							empCustomVO.setPostId(postId);
						}
				finalEmpList.add(empCustomVO);
					}
					//end multiple
						}
					}

				}
				
				//end
				
				
				//only for gpfacctno
				
				else if(StringUtility.getParameter("GPFAcctNo",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("GPFAcctNo",request)) &&!StringUtility.getParameter("GPFAcctNo",request).equals("-1") )
				{
					
					logger.info("enter into for checking GPFAcctNo*********" );
					
					
					
				List employeeIdUserList = new ArrayList();
				String gpfaccotNo = StringUtility.getParameter("GPFAcctNo",request);
				String Flag = StringUtility.getParameter("Flag",request);
				
				String GPFACctNo ="";
				String PfSeries  = "";
				
				long number = gpfaccotNo.lastIndexOf("/");
				String[] charrarray={"0","1","2","3","4","5","6","7","8","9"};
				
				if(Flag.equals("true"))
				{
					String[] gpfAccotNo = gpfaccotNo.split("/");
					GPFACctNo = gpfAccotNo[gpfAccotNo.length-1];
					PfSeries="-1";
					logger.info("For numeric GPFAcctNo*********"+GPFACctNo );
				}
				
				else if(Flag.equals("false"))
				{
					if(number>0)
					{
						boolean containsFlag=false;
						for(int i=0;i<charrarray.length;i++)
						{
							if(gpfaccotNo.contains(charrarray[i]))
							{
								containsFlag=true;
								break;
							}
						}
						if(containsFlag==true)
						{
							String[] gpfAAccotNo = gpfaccotNo.split("/");
							GPFACctNo = gpfAAccotNo[gpfAAccotNo.length-1];
							logger.info(" number gpfaccotNo is with /************* "+GPFACctNo);

							PfSeries =gpfaccotNo.substring(0, gpfaccotNo.lastIndexOf("/"));
							logger.info("PfSeries is with /************* "+PfSeries);
						}
						else
						{

							PfSeries =gpfaccotNo;
							logger.info("PfSeries is without /************* "+PfSeries);
						}

					}
					else
					{
						if(gpfaccotNo.equalsIgnoreCase("dcps"))
						{
							PfSeries=gpfaccotNo;
						}
						else
						{
						GPFACctNo=gpfaccotNo;
						}
						logger.info("GPFACctNo singl account number is ******"+GPFACctNo);
					}
				}
				/*
				
				else
				{
					
					long number = gpfaccotNo.lastIndexOf("/");
					
					if(number>0)
					{
					PfSeries = gpfaccotNo.substring(0, gpfaccotNo.lastIndexOf("/"));
					System.out.println("PfSeries is ************* "+gpfaccotNo.substring(0, gpfaccotNo.lastIndexOf("/")));
					}
					else
					{
						System.out.println("PfSeries is ************* ");	
					}
					String[] gpfAccotNo = gpfaccotNo.split("/");
					GPFACctNo = gpfAccotNo[gpfAccotNo.length-1];
					logger.info("For non numeric GPFAcctNo*********"+GPFACctNo );
				}*/
				
				employeeIdUserList = payBillDAOImpl.getEmployeeIdAndUserIDFromGPFAcctNo(GPFACctNo,PfSeries);
				
				logger.info("employeeIdUserList size iss*********"+employeeIdUserList.size() );
				String emppId="";
				long userrId=0;

				EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
				if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
				{
					
					for(int i =0;i<employeeIdUserList.size();i++)
					{

						logger.info("i value is************"+i);
						
						Object[] rowList1 = (Object[])employeeIdUserList.get(i);

						if(rowList1[0] != null)
						{
							emppId = rowList1[0].toString().trim();
						}
						if(rowList1[1] != null)
						{
							userrId = Long.parseLong(rowList1[1].toString().trim());
						}
				
						logger.info("GPFAcctNo emppId********" + emppId);
						logger.info("GPFAcctNo userrId********" + userrId);
						long DesignationId = payBillDAOImpl.getDesignationIdEployeeId(emppId);
						logger.info("designationId is in loop*****"+DesignationId);
						if(DesignationId>0)
						{
							designationId = DesignationId;
							employeeId =emppId;
							//GPFAcctNo=GPFACctNo;
						
						
						dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
						logger.info("dtlsList size is.."+dtlsList.size());
						long empId=0;
						String empPrf="";
						String empFname="";
						String empMname="";
						String empLName ="";
						String empFullname="";
						String dsgnName="";
						String gpfAccountNo="";
						String officeName="";
						String userId="";
						
						String userName="";
						
						String postId="123";
						
						String sevarthId="";
						String pfSeries="";
						
						if(!dtlsList.isEmpty())
						{
							for(int p=0;p<dtlsList.size();p++)
							{
								Object[] rowList = (Object[])dtlsList.get(p);
								empCustomVO =new EmployeeSearchCustomVO();
								if(rowList[0] != null)
								{
									empId = Long.parseLong(rowList[0].toString().trim());
									empCustomVO.setEmpId(empId);
								}
		
								if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
									empFullname = rowList[1].toString();
									empCustomVO.setEmpFullname(empFullname);
									logger.info("empFullname::"+empFullname);
								}
								if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
									dsgnName = rowList[2].toString();
									empCustomVO.setDsgnName(dsgnName);
									logger.info("dsgnName::"+dsgnName);
								}
								if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
									if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
										pfSeries = rowList[12].toString();
										logger.info("pfSeries::::::::::::::: "+pfSeries);
		
		
										if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
										{
											//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
											gpfAccountNo = rowList[3].toString();
										}
										else
										{
											gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
										}
									}
		
									else
									{
										gpfAccountNo = rowList[3].toString(); 
									}
		
									empCustomVO.setGpfAccountNo(gpfAccountNo);
		
									logger.info("gpfAccountNo::"+gpfAccountNo);
								}
								if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
									officeName = rowList[4].toString();
									empCustomVO.setOfficeName(officeName);
									logger.info("officeName::"+officeName);
								}
								if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
									empPrf = rowList[5].toString();
									empCustomVO.setEmpPrf(empPrf);
									logger.info("empPrf::"+empPrf);
								}
								if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
									empFname = rowList[6].toString();
									empCustomVO.setEmpFname(empFname);
									logger.info("empFname::"+empFname);
								}
								if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
									empLName = rowList[7].toString();
									empCustomVO.setEmpLName(empLName);
									logger.info("empLName::"+empLName);
								}
								if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
									userId = rowList[8].toString();
									empCustomVO.setUserId(userId);
									logger.info("userId::"+userId);
								}
								if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
									userName = rowList[9].toString();
									empCustomVO.setUserName(userName);
									logger.info("userName::"+userName);
								}
								if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
									sevarthId = rowList[11].toString();
									empCustomVO.setSevarthEmpCode(sevarthId);
									logger.info("sevarthId:::::::::::::"+sevarthId);
								}							
								empCustomVO.setPostId(postId);
							}
					finalEmpList.add(empCustomVO);
						}
						}
						//end multiple
				}
					logger.info("finalEmpList nsize is..."+finalEmpList.size());	
				}
				objectArgs.put("sizeoffinalEmpList", finalEmpList.size());
				logger.info("GPFAcctNo designationId*********" + designationId);
				}
				
				
				
				//for dsgncode and city
				else if (StringUtility.getParameter("desgnCode",request)!= null && !(" ").equalsIgnoreCase(StringUtility.getParameter("desgnCode",request)) &&!StringUtility.getParameter("desgnCode",request).equals("-1") && !StringUtility.getParameter("city",request).equals("-1"))
				{
					
					logger.info("enter into for checking desgnCode and Office*********" );
					logger.info("StringUtility.getParameter" +StringUtility.getParameter("desgnCode",request));;
					
					 String DesgnCode = StringUtility.getParameter("desgnCode",request);
					 long DESIGNATIONId = payBillDAOImpl.getDesignationIdFromDsgnCode(DesgnCode);
					 designationId=DESIGNATIONId;
					 
					 dcpsDdoOfficeIdPk = StringUtility.getParameter("city",request);
					
					 EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					// List employeeidlist = payBillDAOImpl.getEmployeeIdListFromDsgnId(DESIGNATIONId);
					// String EMPLOYEEid ="";
					/* for(int i=0;i<employeeidlist.size();i++)
					 {
						 
						 Object[] rowlist1 = (Object[])employeeidlist.get(i);
					 
						 
					  //String EMPLOYEEid = payBillDAOImpl.getEmployeeIdFromDsgnId(DESIGNATIONId);
						 
						 if(rowlist1[0]!=null)
						 {
						 EMPLOYEEid = rowlist1[0].toString();
						 }
					 
						 System.out.println("EMPLOYEEid for both dsgn and office ************"+EMPLOYEEid);*/
					 
					 
						//String EMPLOYEEID = payBillDAOImpl.getEmployeeIdFromOfficeId(EMPLOYEEid, OfficeId);
						
						
						logger.info("Enter into checking for desgnCode and office  DesignationCode" +DesgnCode);
						
						/*if(EMPLOYEEid.equals(EMPLOYEEID))
						{
							
							employeeId = EMPLOYEEid;*/
							//desgnCode=DesgnCode;
							
							logger.info("both desgnCode and office employeeId "+employeeId);
							
						
						
						//for multiple
							
						dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
						logger.info("dtlsList size is.."+dtlsList.size());
						long empId=0;
						String empPrf="";
						String empFname="";
						String empMname="";
						String empLName ="";
						String empFullname="";
						String dsgnName="";
						String gpfAccountNo="";
						String officeName="";
						String userId="";
						
						String userName="";
						
						String postId="123";
						
						String sevarthId="";
						String pfSeries="";
						
						if(!dtlsList.isEmpty())
						{
							for(int p=0;p<dtlsList.size();p++)
							{
								Object[] rowList = (Object[])dtlsList.get(p);
								empCustomVO =new EmployeeSearchCustomVO();
								if(rowList[0] != null)
								{
									empId = Long.parseLong(rowList[0].toString().trim());
									empCustomVO.setEmpId(empId);
								}
		
								if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
									empFullname = rowList[1].toString();
									empCustomVO.setEmpFullname(empFullname);
									logger.info("empFullname::"+empFullname);
								}
								if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
									dsgnName = rowList[2].toString();
									empCustomVO.setDsgnName(dsgnName);
									logger.info("dsgnName::"+dsgnName);
								}
								if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
									if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
										pfSeries = rowList[12].toString();
										logger.info("pfSeries::::::::::::::: "+pfSeries);
		
		
										if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
										{
											//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
											gpfAccountNo = rowList[3].toString();
										}
										else
										{
											gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
										}
									}
		
									else
									{
										gpfAccountNo = rowList[3].toString(); 
									}
		
									empCustomVO.setGpfAccountNo(gpfAccountNo);
		
									logger.info("gpfAccountNo::"+gpfAccountNo);
								}
								if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
									officeName = rowList[4].toString();
									empCustomVO.setOfficeName(officeName);
									logger.info("officeName::"+officeName);
								}
								if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
									empPrf = rowList[5].toString();
									empCustomVO.setEmpPrf(empPrf);
									logger.info("empPrf::"+empPrf);
								}
								if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
									empFname = rowList[6].toString();
									empCustomVO.setEmpFname(empFname);
									logger.info("empFname::"+empFname);
								}
								if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
									empLName = rowList[7].toString();
									empCustomVO.setEmpLName(empLName);
									logger.info("empLName::"+empLName);
								}
								if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
									userId = rowList[8].toString();
									empCustomVO.setUserId(userId);
									logger.info("userId::"+userId);
								}
								if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
									userName = rowList[9].toString();
									empCustomVO.setUserName(userName);
									logger.info("userName::"+userName);
								}
								if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
									sevarthId = rowList[11].toString();
									empCustomVO.setSevarthEmpCode(sevarthId);
									logger.info("sevarthId:::::::::::::"+sevarthId);
								}							
								empCustomVO.setPostId(postId);
								finalEmpList.add(empCustomVO);
							}
							
						}
						//}
						//end multiple
						
				//}
					
				}
				
				
				//only for dsgncode
				else if (StringUtility.getParameter("desgnCode",request)!= null && !(" ").equalsIgnoreCase(StringUtility.getParameter("desgnCode",request)) &&!StringUtility.getParameter("desgnCode",request).equals("-1"))
				{
					
					logger.info("enter into for checking desgnCode*********" );
					
					logger.info("StringUtility.getParameter" +StringUtility.getParameter("desgnCode",request));;
					 String DesgnCode = StringUtility.getParameter("desgnCode",request);
					 long DESIGNATIONId = payBillDAOImpl.getDesignationIdFromDsgnCode(DesgnCode);
					 
					// long EMPLOYEEID = payBillDAOImpl.getEmployeeIdFromDsgnId(DESIGNATIONId);
					 
					 
					 
					logger.info("desgnCode codeeeeee" +DesgnCode);
					logger.info("DESIGNATIONId DESIGNATIONId******" +DESIGNATIONId);
					 if(DESIGNATIONId>0)
					 {
						 desgnCode=DesgnCode;
						 designationId=DESIGNATIONId;
						/* dtlsList = payBillDAOImpl.getEmployeeDetailsList(DesgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,DESIGNATIONId,GPFAcctNo);
						 if(dtlsList.size()>0)
						 {
							 desgnCode=DesgnCode;
							 designationId=DESIGNATIONId;
						 }*/
					 
					
					
					 EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
						dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
						logger.info("dtlsList size is.."+dtlsList.size());
						long empId=0;
						String empPrf="";
						String empFname="";
						String empMname="";
						String empLName ="";
						String empFullname="";
						String dsgnName="";
						String gpfAccountNo="";
						String officeName="";
						String userId="";
						
						String userName="";
						
						String postId="123";
						
						String sevarthId="";
						String pfSeries="";
						
						if(!dtlsList.isEmpty())
						{
							for(int p=0;p<dtlsList.size();p++)
							{
								Object[] rowList = (Object[])dtlsList.get(p);
								empCustomVO =new EmployeeSearchCustomVO();
								if(rowList[0] != null)
								{
									empId = Long.parseLong(rowList[0].toString().trim());
									empCustomVO.setEmpId(empId);
								}
		
								if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
									empFullname = rowList[1].toString();
									empCustomVO.setEmpFullname(empFullname);
									logger.info("empFullname::"+empFullname);
								}
								if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
									dsgnName = rowList[2].toString();
									empCustomVO.setDsgnName(dsgnName);
									logger.info("dsgnName::"+dsgnName);
								}
								if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
									if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
										pfSeries = rowList[12].toString();
										logger.info("pfSeries::::::::::::::: "+pfSeries);
		
		
										if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
										{
											//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
											gpfAccountNo = rowList[3].toString();
										}
										else
										{
											gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
										}
									}
		
									else
									{
										gpfAccountNo = rowList[3].toString(); 
									}
		
									empCustomVO.setGpfAccountNo(gpfAccountNo);
		
									logger.info("gpfAccountNo::"+gpfAccountNo);
								}
								if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
									officeName = rowList[4].toString();
									empCustomVO.setOfficeName(officeName);
									logger.info("officeName::"+officeName);
								}
								if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
									empPrf = rowList[5].toString();
									empCustomVO.setEmpPrf(empPrf);
									logger.info("empPrf::"+empPrf);
								}
								if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
									empFname = rowList[6].toString();
									empCustomVO.setEmpFname(empFname);
									logger.info("empFname::"+empFname);
								}
								if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
									empLName = rowList[7].toString();
									empCustomVO.setEmpLName(empLName);
									logger.info("empLName::"+empLName);
								}
								if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
									userId = rowList[8].toString();
									empCustomVO.setUserId(userId);
									logger.info("userId::"+userId);
								}
								if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
									userName = rowList[9].toString();
									empCustomVO.setUserName(userName);
									logger.info("userName::"+userName);
								}
								if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
									sevarthId = rowList[11].toString();
									empCustomVO.setSevarthEmpCode(sevarthId);
									logger.info("sevarthId:::::::::::::"+sevarthId);
								}							
								empCustomVO.setPostId(postId);
								finalEmpList.add(empCustomVO);
							}
					
						}
					 }
						
					
				}
				//only for office
				else if (StringUtility.getParameter("city",request) != null && !(" ").equalsIgnoreCase(StringUtility.getParameter("city",request)) &&!StringUtility.getParameter("city",request).equals("-1"))
				{
					
					logger.info("enter into for checking city*********" );
					dcpsDdoOfficeIdPk = StringUtility.getParameter("city",request);
					logger.info("dcpsDdoOfficeIdPk ***************" +dcpsDdoOfficeIdPk);
					
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					logger.info("dtlsList size is.."+dtlsList.size());
					long empId=0;
					String empPrf="";
					String empFname="";
					String empMname="";
					String empLName ="";
					String empFullname="";
					String dsgnName="";
					String gpfAccountNo="";
					String officeName="";
					String userId="";
					
					String userName="";
					
					String postId="123";
					
					String sevarthId="";
					String pfSeries="";
					
					if(!dtlsList.isEmpty())
					{
						for(int p=0;p<dtlsList.size();p++)
						{
							Object[] rowList = (Object[])dtlsList.get(p);
							empCustomVO =new EmployeeSearchCustomVO();
							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}
	
							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
									pfSeries = rowList[12].toString();
									logger.info("pfSeries::::::::::::::: "+pfSeries);
	
	
									if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
									{
										//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
										gpfAccountNo = rowList[3].toString();
									}
									else
									{
										gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
									}
								}
	
								else
								{
									gpfAccountNo = rowList[3].toString(); 
								}
	
								empCustomVO.setGpfAccountNo(gpfAccountNo);
	
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
								sevarthId = rowList[11].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}							
							empCustomVO.setPostId(postId);
							finalEmpList.add(empCustomVO);
						}
				
					}
					else
					{
						logger.info("Does Not Mapped");
						String msg ="No Results Found";
						objectArgs.put("msg",msg);
					}
				}
				else
				{
					
					logger.info("You Did't Select Any One");
					//dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
					dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
					logger.info("dtlsList size is.."+dtlsList.size());
					long empId=0;
					String empPrf="";
					String empFname="";
					String empMname="";
					String empLName ="";
					String empFullname="";
					String dsgnName="";
					String gpfAccountNo="";
					String officeName="";
					String userId="";
					
					String userName="";
					
					String postId="123";
					
					String sevarthId="";
					String pfSeries="";
					
					if(!dtlsList.isEmpty())
					{
						for(int p=0;p<dtlsList.size();p++)
						{
							Object[] rowList = (Object[])dtlsList.get(p);
							empCustomVO =new EmployeeSearchCustomVO();
							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}
	
							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){
									pfSeries = rowList[12].toString();
									logger.info("pfSeries::::::::::::::: "+pfSeries);
	
	
									if(pfSeries.equalsIgnoreCase("dcps") || pfSeries.equals(""))
									{
										//gpfAccountNo = pfSeries+"/"+rowList[3].toString();
										gpfAccountNo = rowList[3].toString();
									}
									else
									{
										gpfAccountNo = rowList[12].toString() +"/"+ rowList[3].toString();  
									}
								}
	
								else
								{
									gpfAccountNo = rowList[3].toString(); 
								}
	
								empCustomVO.setGpfAccountNo(gpfAccountNo);
	
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
								sevarthId = rowList[11].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}							
							empCustomVO.setPostId(postId);
							finalEmpList.add(empCustomVO);
						}
				
					}
				}
				
				//Object[] postDtlsLst = null; 
				
				//dtlsList = payBillDAOImpl.getEmployeeDetailsList(desgnCode, locId,langId,dcpsDdoOfficeIdPk,employeeId,designationId,GPFAcctNo);
				
				
				
					
					
					List officeLList = payBillDAOImpl.getOfficeList(locId);
					//List dsgnList = payBillDAOImpl.getDesignationList(langId);
					AdminOrgPostDtlDaoImpl adminDao = new AdminOrgPostDtlDaoImpl(MstDcpsDesignation.class, serv.getSessionFactory());
					List<OrgDesignationMst> dsgnList = adminDao.getActiveDsgnList(locId);
					
					
					String numberResultsPerPage = StringUtility.getParameter("lstResultsPerPage", request);
					logger.info("numberResultsPerPage==>"+numberResultsPerPage);
					logger.info("no of records as enterd by user"+numberResultsPerPage);
					if("-1".equals(numberResultsPerPage) || numberResultsPerPage.trim().length()==0 )
					{
						logger.info("in IF--no of records as enterd by user"+numberResultsPerPage);
						numberResultsPerPage="10";
					}

					objectArgs.put("numberResultsPerPage", numberResultsPerPage);

					String multiSelectStatus="No";
					String forReports ="No";
					String multiEmplSelect="none";
					String functionName ="submitFormAuto";
					String levelSortParam ="testCall";

					logger.info("multiEmplSelect-----"+multiEmplSelect);
					logger.info("functionNameParameter-----"+functionName);
					logger.info("levelSortParam-----"+levelSortParam);
					
					objectArgs.put("multiEmplSelect",multiEmplSelect);
					objectArgs.put("functionName",functionName);
					objectArgs.put("levelSortParam",levelSortParam);
					
					
					objectArgs.put("forReports",forReports);
					
					objectArgs.put("name",forReports);
					String employeeSrchName = StringUtility.getParameter("SearchEmployee",request);
					objectArgs.put("empSrchName",employeeSrchName);
					objectArgs.put("officeList",officeLList);
					objectArgs.put("multiSelectStatus",multiSelectStatus);
					objectArgs.put("dsgnList",dsgnList);
					objectArgs.put("finalEmpList", finalEmpList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("findemployee");     
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getSelectedPostDtls service******");
			logger.error("Error In getSelectedPostDtls service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}

		logger.info("getSelectedPostDtls ended***********************");
		return resultObject;
	}
	
	
	public ResultObject showEmployeeDtlsFromOffice(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try{
			logger.info("************Inside showEmployeeDetailsFromDept*****************");			
			if (resultObject != null && objectArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				
				logger.info("Inside showEmployeeDtlsFromOffice");
				String dcpsDdoOfficeIdPk = StringUtility.getParameter("dcpsDdoOfficeIdPk",request);
				
				logger.info("dcpsDdoOfficeIdPk*********" + dcpsDdoOfficeIdPk);
				
				//ArrayList dtlsList = new ArrayList();
				Object[] postDtlsLst = null; 
				
				
				List dtlsList = new ArrayList();
				
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				dtlsList = payBillDAOImpl.getEmployeeDetailsListFromOffice(dcpsDdoOfficeIdPk, locId, langId);
				
				logger.info("dtlsList sizee******" + dtlsList.size());
				long empId=0;
				String empPrf="";
				String empFname="";
				String empMname="";
				String empLName ="";
				String empFullname="";
				String dsgnName="";
				String gpfAccountNo="";
				String officeName="";
				String userId="";
				
				String userName="";
				
				String postId="";
				
				List finalEmpList = new ArrayList();
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
				
				
			
					
					if(dtlsList!=null && dtlsList.size()!=0)
					{
							for(Iterator itr=dtlsList.iterator();itr.hasNext();)
						{

							empCustomVO=new EmployeeSearchCustomVO();
							Object[] rowList = (Object[])itr.next();

							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}

							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								gpfAccountNo = rowList[3].toString();
								empCustomVO.setGpfAccountNo(gpfAccountNo);
								
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							
							if(rowList[10] != null && !(rowList[10].toString().trim()).equals("")){	
								postId = rowList[10].toString();
								empCustomVO.setPostId(postId);
								logger.info("postId::"+postId);
							}

							
							finalEmpList.add(empCustomVO);

						}
					}
					
					
					List officeLList = payBillDAOImpl.getOfficeList(locId);
					List dsgnList = payBillDAOImpl.getDesignationList(langId);
					
					String numberResultsPerPage = StringUtility.getParameter("lstResultsPerPage", request);
					logger.info("numberResultsPerPage==>"+numberResultsPerPage);
					logger.info("no of records as enterd by user"+numberResultsPerPage);
					if("-1".equals(numberResultsPerPage) || numberResultsPerPage.trim().length()==0 )
					{
						logger.info("in IF--no of records as enterd by user"+numberResultsPerPage);
						numberResultsPerPage="10";
					}

					objectArgs.put("numberResultsPerPage", numberResultsPerPage);

					String multiSelectStatus="No";
					String forReports ="No";
					String multiEmplSelect="none";
					String functionName ="testCall";
					String levelSortParam ="testCall";

					logger.info("multiEmplSelect-----"+multiEmplSelect);
					logger.info("functionNameParameter-----"+functionName);
					logger.info("levelSortParam-----"+levelSortParam);
					
					objectArgs.put("multiEmplSelect",multiEmplSelect);
					objectArgs.put("functionName",functionName);
					objectArgs.put("levelSortParam",levelSortParam);
					
					
					objectArgs.put("forReports",forReports);
					objectArgs.put("officeList",officeLList);
					objectArgs.put("multiSelectStatus",multiSelectStatus);
					
					objectArgs.put("officeList",officeLList);
					objectArgs.put("dsgnList",dsgnList);
					objectArgs.put("finalEmpList", finalEmpList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("findemployee");     
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getSelectedPostDtls service******");
			logger.error("Error In getSelectedPostDtls service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}

		logger.info("getSelectedPostDtls ended***********************");
		return resultObject;
	}
	
	
	public ResultObject showEmployeeDtlsFromEmployeeName(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try{
			logger.info("************Inside showEmployeeDtlsFromEmployeeName*****************");			
			if (resultObject != null && objectArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				
				logger.info("Inside showEmployeeDtlsFromEmployeeName");
				String EmpFullname = StringUtility.getParameter("empFname",request);
				
				logger.info("empFname*********" + EmpFullname);
				
				
				
				

				
				
				String empfullnameinlocallanguage = "";
				String empLocalFName ="";
				String empLocalMName ="";
				String empLocalLName ="";
				
				
				 if(StringUtility.getParameter("empFname", request)!=null && !StringUtility.getParameter("empFname", request).equals("")) 
		            {
					 empfullnameinlocallanguage = StringUtility.getParameter("empFname", request) != null ? StringUtility.getParameter("empFname", request) : "";
		            }
				 
				 
				 String b[] = empfullnameinlocallanguage.split(" ");

					if(b.length==3)
					{
						logger.info("Employee first name:"+b[0]);
						logger.info("Employee M name:"+b[1]);
						logger.info("Employee Last name:"+b[2]);
						empLocalFName = b[0];
						empLocalMName = b[1];
						empLocalLName = b[2];
					}
					else if(b.length==2)
					{
						logger.info("Employee first name:"+b[0]);
						logger.info("Employee Last name:"+b[1]);
						empLocalFName = b[0];
						empLocalLName = b[1];
					}
					else if(b.length==1)
					{
						logger.info("Employee first name:"+b[0]);
						empLocalFName = b[0];
					}
					else
					{
						logger.info("Employee First name:"+b[0]);
						empLocalFName = b[0];
						String EmpLocalMName="";
						for(int i=1;i<b.length-1;i++)
						{
							EmpLocalMName+=" "+b[i];
						}
						logger.info("Employee M name :"+EmpLocalMName);
						empLocalMName = EmpLocalMName;
						empLocalLName = b[b.length-1];
						logger.info("Employee Last name:"+b[b.length-1]);
					}
				
				
				
				
				
				Object[] postDtlsLst = null; 
				List employeeIdUserList = new ArrayList();
				
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				employeeIdUserList  = payBillDAOImpl.getEmployeeIdFromEployeeName(empLocalFName, empLocalMName, empLocalLName);
				
				long emppId=0;
				long userrId=0;
				if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
				{
					for(Iterator itr=employeeIdUserList.iterator();itr.hasNext();)
					{

						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							emppId = Long.parseLong(rowList[0].toString().trim());
						}
						if(rowList[1] != null)
						{
							userrId = Long.parseLong(rowList[1].toString().trim());
						}

					}
				}
				
				logger.info("emppId********" + emppId);
				logger.info("userrId********" + userrId);
				
			long designationId = payBillDAOImpl.getDesignationIdEployeeName(userrId);
				
			logger.info("designationId********" + designationId);
				
				
				
				long empId=0;
				String empPrf="";
				String empFname="";
				String empMname="";
				String empLName ="";
				String empFullname="";
				String dsgnName="";
				String gpfAccountNo="";
				String officeName="";
				String userId="";
				
				String userName="";
				
				String postId="123";
				
				List dtlsList = new ArrayList();
				List finalEmpList = new ArrayList();
				dtlsList = payBillDAOImpl.getEmployeeDetailsListFromEmployeeName(designationId, locId, langId,emppId);
				
				logger.info("dtlsList sizeee********" + dtlsList.size());
				
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
				
				
			
					
					if(dtlsList!=null && dtlsList.size()!=0)
					{
							for(Iterator itr=dtlsList.iterator();itr.hasNext();)
						{

							empCustomVO=new EmployeeSearchCustomVO();
							Object[] rowList = (Object[])itr.next();

							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}

							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								gpfAccountNo = rowList[3].toString();
								empCustomVO.setGpfAccountNo(gpfAccountNo);
								
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							
							empCustomVO.setPostId(postId);
							
							finalEmpList.add(empCustomVO);

						}
					}
					
					
					List officeLList = payBillDAOImpl.getOfficeList(locId);
					List dsgnList = payBillDAOImpl.getDesignationList(langId);
					
					
					String numberResultsPerPage = StringUtility.getParameter("lstResultsPerPage", request);
					logger.info("numberResultsPerPage==>"+numberResultsPerPage);
					logger.info("no of records as enterd by user"+numberResultsPerPage);
					if("-1".equals(numberResultsPerPage) || numberResultsPerPage.trim().length()==0 )
					{
						logger.info("in IF--no of records as enterd by user"+numberResultsPerPage);
						numberResultsPerPage="10";
					}

					objectArgs.put("numberResultsPerPage", numberResultsPerPage);

					String multiSelectStatus="No";
					String forReports ="No";
					String multiEmplSelect="none";
					String functionName ="testCall";
					String levelSortParam ="testCall";

					logger.info("multiEmplSelect-----"+multiEmplSelect);
					logger.info("functionNameParameter-----"+functionName);
					logger.info("levelSortParam-----"+levelSortParam);
					
					objectArgs.put("multiEmplSelect",multiEmplSelect);
					objectArgs.put("functionName",functionName);
					objectArgs.put("levelSortParam",levelSortParam);
					
					
					objectArgs.put("forReports",forReports);
					objectArgs.put("officeList",officeLList);
					objectArgs.put("multiSelectStatus",multiSelectStatus);
					
					objectArgs.put("officeList",officeLList);
					objectArgs.put("dsgnList",dsgnList);
					objectArgs.put("finalEmpList", finalEmpList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("findemployee");     
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getSelectedPostDtls service******");
			logger.error("Error In getSelectedPostDtls service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}

		logger.info("getSelectedPostDtls ended***********************");
		return resultObject;
	}
	
	
	public ResultObject showEmployeeDtlsFromEmployeeId(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try{
			logger.info("************Inside showEmployeeDetailsFromDept*****************");			
			if (resultObject != null && objectArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				
				logger.info("Inside showEmployeeDtlsFromOffice");
				String employeeId = StringUtility.getParameter("empId",request);
				
				logger.info("employeeId*********" + employeeId);
				
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				
				long designationId = payBillDAOImpl.getDesignationIdEployeeId(employeeId);
				
				
				
				List dtlsList = new ArrayList();
				
				
				dtlsList = payBillDAOImpl.getEmployeeDetailsListFromEmployeeId(designationId, locId, langId, Long.parseLong(employeeId));
				
				
				logger.info("dtlsList sizee******" + dtlsList.size());
				long empId=0;
				String empPrf="";
				String empFname="";
				String empMname="";
				String empLName ="";
				String empFullname="";
				String dsgnName="";
				String gpfAccountNo="";
				String officeName="";
				String userId="";
				
				String userName="";
				
				String postId="123";
				
				String sevarthId = ""; 
				
				List finalEmpList = new ArrayList();
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
				
				
			
					
					if(dtlsList!=null && dtlsList.size()!=0)
					{
							for(Iterator itr=dtlsList.iterator();itr.hasNext();)
						{

							empCustomVO=new EmployeeSearchCustomVO();
							Object[] rowList = (Object[])itr.next();

							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}

							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								gpfAccountNo = rowList[3].toString();
								empCustomVO.setGpfAccountNo(gpfAccountNo);
								
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							if(rowList[10] != null && !(rowList[10].toString().trim()).equals("")){	
								sevarthId = rowList[10].toString();
								empCustomVO.setSevarthEmpCode(sevarthId);
								logger.info("sevarthId:::::::::::::"+sevarthId);
							}
							
							empCustomVO.setPostId(postId);
							
							finalEmpList.add(empCustomVO);

						}
					}
					
					
					List officeLList = payBillDAOImpl.getOfficeList(locId);
					List dsgnList = payBillDAOImpl.getDesignationList(langId);
					
					String numberResultsPerPage = StringUtility.getParameter("lstResultsPerPage", request);
					logger.info("numberResultsPerPage==>"+numberResultsPerPage);
					logger.info("no of records as enterd by user"+numberResultsPerPage);
					if("-1".equals(numberResultsPerPage) || numberResultsPerPage.trim().length()==0 )
					{
						logger.info("in IF--no of records as enterd by user"+numberResultsPerPage);
						numberResultsPerPage="10";
					}

					objectArgs.put("numberResultsPerPage", numberResultsPerPage);

					String multiSelectStatus="No";
					String forReports ="No";
					String multiEmplSelect="none";
					String functionName ="testCall";
					String levelSortParam ="testCall";

					logger.info("multiEmplSelect-----"+multiEmplSelect);
					logger.info("functionNameParameter-----"+functionName);
					logger.info("levelSortParam-----"+levelSortParam);
					
					objectArgs.put("multiEmplSelect",multiEmplSelect);
					objectArgs.put("functionName",functionName);
					objectArgs.put("levelSortParam",levelSortParam);
					
					
					objectArgs.put("forReports",forReports);
					objectArgs.put("officeList",officeLList);
					objectArgs.put("multiSelectStatus",multiSelectStatus);
					
					objectArgs.put("officeList",officeLList);
					objectArgs.put("dsgnList",dsgnList);
					objectArgs.put("finalEmpList", finalEmpList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("findemployee");     
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getSelectedPostDtls service******");
			logger.error("Error In getSelectedPostDtls service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}

		logger.info("getSelectedPostDtls ended***********************");
		return resultObject;
	}
	
	public ResultObject showEmployeeDtlsFromGPFAcctNo(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try{
			logger.info("************Inside showEmployeeDtlsFromGPFAcctNo*****************");			
			if (resultObject != null && objectArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				
				logger.info("Inside showEmployeeDtlsFromGPFAcctNo");
				String GPFAcctNo = StringUtility.getParameter("GPFAcctNo",request);
				
				logger.info("GPFAcctNo*********" + GPFAcctNo);
				
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				
				
				List employeeIdUserList = new ArrayList();
				employeeIdUserList = payBillDAOImpl.getEmployeeIdAndUserIDFromGPFAcctNo(GPFAcctNo);
				long emppId=0;
				long userrId=0;
				if(employeeIdUserList!=null && employeeIdUserList.size()!=0)
				{
					for(Iterator itr=employeeIdUserList.iterator();itr.hasNext();)
					{

						Object[] rowList = (Object[])itr.next();

						if(rowList[0] != null)
						{
							emppId = Long.parseLong(rowList[0].toString().trim());
						}
						if(rowList[1] != null)
						{
							userrId = Long.parseLong(rowList[1].toString().trim());
						}

					}
				}
				
				logger.info("emppId********" + emppId);
				logger.info("userrId********" + userrId);
				
				
				
				long designationId = payBillDAOImpl.getDesignationIdEployeeId(emppId);
				
				
				
				List dtlsList = new ArrayList();
				
				
				dtlsList = payBillDAOImpl.getEmployeeDetailsListFromEmployeeId(designationId, locId, langId, emppId);
				
				
				logger.info("dtlsList sizee******" + dtlsList.size());
				long empId=0;
				String empPrf="";
				String empFname="";
				String empMname="";
				String empLName ="";
				String empFullname="";
				String dsgnName="";
				String gpfAccountNo="";
				String officeName="";
				String userId="";
				
				String userName="";
				
				String postId="123";
				
				List finalEmpList = new ArrayList();
					EmployeeSearchCustomVO empCustomVO = new EmployeeSearchCustomVO();
				
				
			
					
					if(dtlsList!=null && dtlsList.size()!=0)
					{
							for(Iterator itr=dtlsList.iterator();itr.hasNext();)
						{

							empCustomVO=new EmployeeSearchCustomVO();
							Object[] rowList = (Object[])itr.next();

							if(rowList[0] != null)
							{
								empId = Long.parseLong(rowList[0].toString().trim());
								empCustomVO.setEmpId(empId);
							}

							if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
								empFullname = rowList[1].toString();
								empCustomVO.setEmpFullname(empFullname);
								logger.info("empFullname::"+empFullname);
							}
							if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
								dsgnName = rowList[2].toString();
								empCustomVO.setDsgnName(dsgnName);
								logger.info("dsgnName::"+dsgnName);
							}
							if(rowList[3] != null && !(rowList[3].toString().trim()).equals("")){	
								gpfAccountNo = rowList[3].toString();
								empCustomVO.setGpfAccountNo(gpfAccountNo);
								
								logger.info("gpfAccountNo::"+gpfAccountNo);
							}
							if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
								officeName = rowList[4].toString();
								empCustomVO.setOfficeName(officeName);
								logger.info("officeName::"+officeName);
							}
							if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
								empPrf = rowList[5].toString();
								empCustomVO.setEmpPrf(empPrf);
								logger.info("empPrf::"+empPrf);
							}
							if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
								empFname = rowList[6].toString();
								empCustomVO.setEmpFname(empFname);
								logger.info("empFname::"+empFname);
							}
							if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
								empLName = rowList[7].toString();
								empCustomVO.setEmpLName(empLName);
								logger.info("empLName::"+empLName);
							}
							if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
								userId = rowList[8].toString();
								empCustomVO.setUserId(userId);
								logger.info("userId::"+userId);
							}
							if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
								userName = rowList[9].toString();
								empCustomVO.setUserName(userName);
								logger.info("userName::"+userName);
							}
							
							empCustomVO.setPostId(postId);
							
							finalEmpList.add(empCustomVO);

						}
					}
					
					
					List officeLList = payBillDAOImpl.getOfficeList(locId);
					List dsgnList = payBillDAOImpl.getDesignationList(langId);
					
					
					String numberResultsPerPage = StringUtility.getParameter("lstResultsPerPage", request);
					logger.info("numberResultsPerPage==>"+numberResultsPerPage);
					logger.info("no of records as enterd by user"+numberResultsPerPage);
					if("-1".equals(numberResultsPerPage) || numberResultsPerPage.trim().length()==0 )
					{
						logger.info("in IF--no of records as enterd by user"+numberResultsPerPage);
						numberResultsPerPage="10";
					}

					objectArgs.put("numberResultsPerPage", numberResultsPerPage);

					String multiSelectStatus="No";
					String forReports ="No";
					String multiEmplSelect="none";
					String functionName ="testCall";
					String levelSortParam ="testCall";

					logger.info("multiEmplSelect-----"+multiEmplSelect);
					logger.info("functionNameParameter-----"+functionName);
					logger.info("levelSortParam-----"+levelSortParam);
					
					objectArgs.put("multiEmplSelect",multiEmplSelect);
					objectArgs.put("functionName",functionName);
					objectArgs.put("levelSortParam",levelSortParam);
					
					
					objectArgs.put("forReports",forReports);
					objectArgs.put("officeList",officeLList);
					objectArgs.put("multiSelectStatus",multiSelectStatus);
					
					
					objectArgs.put("officeList",officeLList);
					objectArgs.put("dsgnList",dsgnList);
					objectArgs.put("finalEmpList", finalEmpList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("findemployee");     
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getSelectedPostDtls service******");
			logger.error("Error In getSelectedPostDtls service: " , ex);
			logger.error("Error is: "+ ex.getMessage());
		}

		logger.info("getSelectedPostDtls ended***********************");
		return resultObject;
	}
	
	
	ResourceBundle constantsBundle = ResourceBundle.getBundle("../resources/Payroll");

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
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");
	
	private void setSessionInfo(Map inputMap) 
	{

		try 
		{
			logger.info("Executing setSessionInfo");
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = Long.parseLong(SessionHelper.getUserId(inputMap).toString());
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			logger.info("gStrPostId"+gStrPostId+"gLngUserId"+gLngUserId+"gDtCurDate"+gDtCurDate);
			logger.info("Execution of setSessionInfo Completed******");
		} catch (Exception e) {

		}

	}
	 
public ResultObject getEmpNameForAutoCompletePayrollSearch(Map<String, Object> inputMap)
{
		
		logger.info("Inside getEmpNameForAutoCompletePayrollSearch****** ");

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		List finalList  = new ArrayList<ComboValuesVO>();
		List finalListForSevarthId  = new ArrayList<ComboValuesVO>();
		List finalListFromEmpLname  = new ArrayList<ComboValuesVO>();
		
		String lStrEmpName = null;
		String lStrSearchBy = null;
		String lStrDDOCode = null;
		
		try 
		{
			setSessionInfo(inputMap);
			SearchEmpDAOImplPayroll lObjSearchEmployeeDAO = new SearchEmpDAOImplPayroll(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lStrEmpName = StringUtility.getParameter("searchKey", request).toString().trim();
			
			lStrSearchBy = StringUtility.getParameter("searchBy", request).toString().trim();

			if(lStrSearchBy.equals("searchByDDOAsst"))  
			{
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			}
			if(lStrSearchBy.equals("searchFromDDOSelection") || lStrSearchBy.equals("searchFromDDODeSelection"))
			{
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			if(lStrSearchBy.equals("searchBySRKA"))
			{
				lStrDDOCode = null;
				logger.info("Inside Search By SRKA----->"+lStrEmpName.toString());
			}
					
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			String locId=loginDetailsMap.get("locationId").toString();
			
			finalList = lObjSearchEmployeeDAO.getEmpNameForAutoCompleteForPayroll(lStrEmpName.toUpperCase(),locId);
			long langId=1;
			finalListForSevarthId = lObjSearchEmployeeDAO.getEmpNameForAutoCompleteFromSevarthId(lStrEmpName.toUpperCase(), locId,langId);
			finalListFromEmpLname = lObjSearchEmployeeDAO.getEmpNameForAutoCompleteForPayrollFromEmpLname(lStrEmpName.toUpperCase(), locId,langId);
			
			logger.info("finalList size is **********"+finalList.size());
			logger.info("finalListForSevarthId size is **********"+finalListForSevarthId.size());
			logger.info("finalListFromEmpLname size is **********"+finalListFromEmpLname.size());

			String lStrTempResult = null;
			if (finalList != null && finalList.size()>0 ) {
				lStrTempResult = new AjaxXmlBuilder().addItems(finalList, "desc", "id", true).toString();
			}
			else if (finalListFromEmpLname != null && finalListFromEmpLname.size()>0 ) {
				lStrTempResult = new AjaxXmlBuilder().addItems(finalListFromEmpLname, "desc", "id", true).toString();
			}
			else
			{
				lStrTempResult = new AjaxXmlBuilder().addItems(finalListForSevarthId, "desc", "id", true).toString();
			}
			inputMap.put("ajaxKey", lStrTempResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");

		} catch (Exception ex) {
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error("Error is: "+ ex.getMessage());
			return objRes;
		}

		return objRes;

	}
	
}