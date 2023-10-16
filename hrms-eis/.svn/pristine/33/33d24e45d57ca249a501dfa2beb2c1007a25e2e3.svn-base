package com.tcs.sgv.eis.vacancy.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.vacancy.dao.VacanyDao;
import com.tcs.sgv.eis.vacancy.dao.VacanyDaoImpl;
import com.tcs.sgv.eis.vacancy.valueobject.HrEisVacancyAdminMst;
import com.tcs.sgv.ess.dao.OrgDesignationMstDao;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class VacancyServiceImpl extends ServiceImpl implements VacancyService{

	Log logger = LogFactory.getLog(VacancyServiceImpl.class.getClass());
	public final String SERVICE_LOCATOR="serviceLocator";
	public final String BASELOGINMAP="baseLoginMap";
	public final String LANG_ID="langId";
	
	public ResultObject getYear(Map objectArgs) {
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	//	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");	
		
		try {

			Date currentDate = new Date();
			String[] dateArray =  simpleDateFormat.format(currentDate).split("/") ;
			int currentYear = Integer.parseInt(dateArray[2]);
			int previousYear = currentYear - 30; 
			List yearList = new ArrayList();
			
			for(int i=1 ; i<=31; i++){
				yearList.add(previousYear);
				previousYear++;
			}

			String ajaxKey = "";
			if (!yearList.isEmpty()) {
				Iterator it=yearList.iterator();
				
				String tmp="";
				while(it.hasNext())
				{
					Integer year=(Integer)it.next();
					tmp=year+"/"+year;
					ajaxKey=tmp+"$"+ajaxKey;
				}
				
				ajaxKey=ajaxKey.substring(0, ajaxKey.length()-1);
			} else {
				ajaxKey = "error";
			}
			objectArgs.put("ajaxKey", ajaxKey);
			objRes.setViewName("ajaxData");
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
		} catch (Exception e) {
			logger.error("Error into getYear >> "+e);
	//		logger.error("Error is: "+ e.getMessage());
		}
		return objRes;
	}
	
	public ResultObject getVacancyAdminComboFill(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		OrgDepartmentMst orgDepartmentMst=(OrgDepartmentMst)loginMap.get("departmentVO");
		
		try { 
			/* login code */
			long langId = Long.parseLong(loginMap.get(LANG_ID).toString());
			
			VacanyDao vacanyDaoImpl = new VacanyDaoImpl(OrgDesignationMst.class, serv.getSessionFactory());
			List depList=vacanyDaoImpl.getDepartmentName(langId,orgDepartmentMst);
			objectArgs.put("depList", depList);	
			
			List dsgnList=vacanyDaoImpl.getDesignationList(langId);
			objectArgs.put("dsgnList", dsgnList);	
			
			
		} catch (Exception e) {
			logger.error("getVacancyAdminComboFill ERROR >>> "+e);
//			logger.error("Error is: "+ e.getMessage());
		}
		
		objRes.setViewName("sanctionPost");
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		logger.info("getVacancyAdminComboFill method exectued::::::");
		return objRes;
	}
	
	public ResultObject getLocationlist(Map objectArgs) {
		
//		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
//		long langId =  Long.valueOf(loginMap.get("langId").toString());
		List locList = new ArrayList();
		
		try {	
			long deptId = 0l;
			if((objectArgs.get("cmbDept")!=null)&&(!objectArgs.get("cmbDept").equals(""))){
				deptId=Long.parseLong(objectArgs.get("cmbDept").toString());
			}
			VacanyDaoImpl vacanyDaoImpl = new VacanyDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			locList=vacanyDaoImpl.getLocationList(deptId);
			
			String ajaxKey = "";
			if (!locList.isEmpty()) {
				Iterator it=locList.iterator();
				
				String tmp="";
				while(it.hasNext())
				{
					CmnLocationMst cmnLocationMst=(CmnLocationMst)it.next();
					tmp=cmnLocationMst.getLocId()+"~"+cmnLocationMst.getLocationCode()+"/"+cmnLocationMst.getLocName();
					ajaxKey=tmp+"$"+ajaxKey;
				}
				
				ajaxKey=ajaxKey.substring(0, ajaxKey.length()-1);
			} else {
				ajaxKey = "error";
			}
			objectArgs.put("ajaxKey", ajaxKey);
			objRes.setViewName("ajaxData");
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
		} catch (Exception e) {
			logger.error("Error into getLocationlist >> "+e);
	//		logger.error("Error is: "+ e.getMessage());
		}
		return objRes;
	}
	
	public ResultObject getQuarter(Map objectArgs) {
		
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		long langId =  Long.valueOf(loginMap.get("langId").toString());
		
		try {
			VacanyDaoImpl vacanyDaoImpl = new VacanyDaoImpl(CmnLookupMst.class,serv.getSessionFactory());
			List qtrList = new ArrayList();	

			qtrList=vacanyDaoImpl.getQuarterList(langId);

			String ajaxKey = "";
			if (!qtrList.isEmpty()) {
				Iterator it=qtrList.iterator();
				String tmp="";
				while(it.hasNext())
				{
					CmnLookupMst cmnLookupMst=(CmnLookupMst)it.next();
					tmp=cmnLookupMst.getLookupDesc()+"/"+cmnLookupMst.getLookupId();
					ajaxKey=tmp+"$"+ajaxKey;
				}
				
				ajaxKey=ajaxKey.substring(0, ajaxKey.length()-1);
			} else {
				ajaxKey = "error";
			}
			objectArgs.put("ajaxKey", ajaxKey);
			objRes.setViewName("ajaxData");
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
		} catch (Exception e) {
			logger.error("Error into getQuarter >> "+e);
	//		logger.error("Error is: "+ e.getMessage());
		}
		return objRes;
	}
	
	public ResultObject getMonth(Map objectArgs) {
		
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		long langId =  Long.valueOf(loginMap.get("langId").toString());
		
		try {
			VacanyDaoImpl vacanyDaoImpl = new VacanyDaoImpl(CmnLookupMst.class,serv.getSessionFactory());
			List qtrList = new ArrayList();	

			qtrList=vacanyDaoImpl.getMonthList(langId);

			String ajaxKey = "";
			if (!qtrList.isEmpty()) {
				Iterator it=qtrList.iterator();
				String tmp="";
				while(it.hasNext())
				{
					CmnLookupMst cmnLookupMst=(CmnLookupMst)it.next();
					tmp=cmnLookupMst.getLookupDesc()+"/"+cmnLookupMst.getLookupShortName();
					ajaxKey=tmp+"$"+ajaxKey;
				}
				
				ajaxKey=ajaxKey.substring(0, ajaxKey.length()-1);
			} else {
				ajaxKey = "error";
			}
			objectArgs.put("ajaxKey", ajaxKey);
			objRes.setViewName("ajaxData");
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
		} catch (Exception e) {
			logger.error("Error into getMonth >> "+e);
//			logger.error("Error is: "+ e.getMessage());
		}
		return objRes;
	}
	
	public ResultObject getDsgnTable(Map objectArgs){
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	//	OrgDesignationMst orgDesignationMst = null;
		
		
		try{
			VacanyDao vacanyDao = new VacanyDaoImpl(HrEisVacancyAdminMst.class,serv.getSessionFactory());
			
			long dsgnId = Long.valueOf(objectArgs.get("cmbDsgn").toString());
			OrgDesignationMstDao dao = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,serv.getSessionFactory());
			OrgDesignationMst designationMst = dao.getDesignationVO(dsgnId);
			String dsgnCode = designationMst.getDsgnCode();
			long locId = 0l;
			String locCode = "";
			long deptId =0l;
			HrEisVacancyAdminMst hrEisVacancyAdminMst = new HrEisVacancyAdminMst();
			String xmlPath = "";
			List vacancyList = vacanyDao.getVacancnyVofromDsgnCode(dsgnCode);
			if(vacancyList.isEmpty()){
				
				String cmbLoc = objectArgs.get("cmbLoc").toString();
				String[] location = cmbLoc.split("~");
				locId = Long.valueOf(location[0]);
				locCode = location[1];
				
				deptId = Long.valueOf(objectArgs.get("cmbDept").toString());
				
				hrEisVacancyAdminMst.setDsgnCode(dsgnCode);
				hrEisVacancyAdminMst.setLocCode(locCode);
				
				xmlPath = FileUtility.voToXmlFileByXStream(hrEisVacancyAdminMst);
			}else{
				hrEisVacancyAdminMst = (HrEisVacancyAdminMst)vacancyList.get(0);
				xmlPath = FileUtility.voToXmlFileByXStream(hrEisVacancyAdminMst);
			}
		
			objectArgs.put("ajaxKey", xmlPath);
			objRes.setViewName("ajaxData");
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			
		}catch(Exception e){
			logger.error("Error into getDsgnTable >> "+e);
	//		logger.error("Error is: "+ e.getMessage());
		}
		return objRes;
	}
	
	
	public ResultObject saveVacancyData(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	//	long sanctionedStrength = 0l;
		
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		
		long dbId= Long.parseLong(loginMap.get(DB_ID).toString());		
		long userId= Long.parseLong(loginMap.get(USER_ID).toString());
		long locId=Long.parseLong(loginMap.get(LOCATION_ID).toString());
		long postId = Long.parseLong(loginMap.get(LOGGEDINPOST).toString());
		
		
		CmnDatabaseMst cmnDatabaseMst=new CmnDatabaseMst();
		cmnDatabaseMst.setDbId(dbId);
		
		OrgUserMst orgUserMst=new OrgUserMst();
		orgUserMst.setUserId(userId);
		
		CmnLocationMst cmnLocationMst=new CmnLocationMst();
		cmnLocationMst.setLocId(locId);
		
		OrgPostMst orgPostMst=new OrgPostMst();
		orgPostMst.setPostId(postId);
		
		List dsgnList = new ArrayList();
		List arrayList=new ArrayList();
		List hiddenXMLPath=new ArrayList();
		VacanyDao vacanyDao = new VacanyDaoImpl(HrEisVacancyAdminMst.class,serv.getSessionFactory());
		HrEisVacancyAdminMst updatedVO=null;
		HrEisVacancyAdminMst insertedVO=null;
		List<HrEisVacancyAdminMst> mstList=null;
		HrEisVacancyAdminMst hrEisVacancyAdminMst=null;
	
		try{
			if((objectArgs.get("hiddenXMLPath")!=null)&&(!objectArgs.get("hiddenXMLPath").equals(""))){
				arrayList=(List)objectArgs.get("arrayList");
				int cnt=0;
				hiddenXMLPath=(List)objectArgs.get("hiddenXMLPath");
				if(!hiddenXMLPath.isEmpty()){
					String[] xmlContent = null;
					for (Iterator iter = hiddenXMLPath.iterator(); iter.hasNext();) {
						
						String element = (String) iter.next();
						xmlContent = new String[]{element};
						
						mstList=FileUtility.xmlFilesToVoByXStream(new String[]{element});
						if(!mstList.isEmpty())
						{
							hrEisVacancyAdminMst=mstList.get(0);
							if(hrEisVacancyAdminMst.getSrno()==0){
								//Insert Values
								insertedVO=hrEisVacancyAdminMst;
								insertedVO.setSanctionedStrength(Long.parseLong(arrayList.get(cnt).toString()));
								
								insertedVO.setCmnDatabaseMst(cmnDatabaseMst);
								insertedVO.setOrgUserMstByCreatedBy(orgUserMst);
								insertedVO.setOrgPostMstByCreatedByPost(orgPostMst);
								insertedVO.setCmnLocationMst(cmnLocationMst);
								insertedVO.setCreatedDate(new Date());
								
								long reqId=IDGenerateDelegate.getNextId("hr_eis_vacancy_admin_mst", objectArgs);
								if(reqId!=0)
								{
									insertedVO.setSrno(reqId);
								}
								
								vacanyDao.create(insertedVO);
							}else
							{
								//Update Values
								updatedVO=(HrEisVacancyAdminMst)vacanyDao.read(hrEisVacancyAdminMst.getSrno());
								updatedVO.setSanctionedStrength(Long.parseLong(arrayList.get(cnt).toString()));
								vacanyDao.update(updatedVO);
							}	
						}
						cnt++;
					}
				}
			}
		}catch(Exception e){
	//		logger.error("Error is: "+ e.getMessage());
			logger.error("Error in saveVacancyData >>> "+e);
		}
		
		OrgDepartmentMst orgDepartmentMst=(OrgDepartmentMst)loginMap.get("departmentVO");
			/* login code */
		long langId = Long.parseLong(loginMap.get(LANG_ID).toString());
			
		VacanyDao vacanyDaoImpl = new VacanyDaoImpl(OrgDesignationMst.class, serv.getSessionFactory());
		List depList=vacanyDaoImpl.getDepartmentName(langId,orgDepartmentMst);
		objectArgs.put("depList", depList);	
			
		dsgnList=vacanyDaoImpl.getDesignationList(langId);
		objectArgs.put("dsgnList", dsgnList);	
		
		objRes.setViewName("sanctionPost");
		objRes.setResultValue(objectArgs);			
		objRes.setResultCode(ErrorConstants.SUCCESS);	
		return objRes;
	}

}
