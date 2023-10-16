package com.tcs.sgv.eis.vacancy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;

public class VacancyVOGENImpl extends ServiceImpl implements VacancyVOGEN{
	Log logger = LogFactory.getLog(VacancyVOGENImpl.class.getClass());
	
	public ResultObject getRequestData(Map objectArgs){
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		 logger.info("Domestic Tours And Travel Application::::>>>>resolveTravelRequest:::Start");
		 
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get(REQUEST_OBJ);
			long cmbDept=0l;
			String cmbLoc="";
			long cmbDsgn=0l;
			
			if((request.getParameter("cmbDept")!=null)&&(!request.getParameter("cmbDept").equals(""))){
				cmbDept=StringUtility.convertToLong(request.getParameter("cmbDept"));
				objectArgs.put("cmbDept",cmbDept);
			}
			
			if((request.getParameter("cmbLoc")!=null)&&(!request.getParameter("cmbLoc").equals(""))){
				cmbLoc= request.getParameter("cmbLoc");
				objectArgs.put("cmbLoc",cmbLoc);
			}
			
			if((request.getParameter("cmbDsgn")!=null)&&(!request.getParameter("cmbDsgn").equals(""))){
				cmbDsgn=StringUtility.convertToLong(request.getParameter("cmbDsgn"));
				objectArgs.put("cmbDsgn",cmbDsgn);
			}
			
			objRes.setResultValue(objectArgs);
		} catch (Exception e) {
	//		logger.error("Error is: "+ e.getMessage());
			objRes.setResultCode(ErrorConstants.ERROR);
			logger.info("Domestic Tours And Travel Application::::>>>>resolveTravelRequest:::Error");
		}
		logger.info("Domestic Tours And Travel Application::::>>>>resolveTravelRequest:::Start");
		 
		return objRes;
	}

	public ResultObject getDesignationforVacancy(Map objectArgs){
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		String xmlpath = "";
		long sanctionedPost = 0l;
		long cmbDept=0l;
		long locId=0l;
		String locCode = "";
//		long cmbDsgn=0l;
		//String location = "";
		int tableSize=0;
		List arrayList=new ArrayList();
		List hiddenXMLPath=new ArrayList();
		
		try{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get(REQUEST_OBJ);
			
			if((request.getParameter("tableSize")!=null)&&(!request.getParameter("tableSize").equals(""))){
				tableSize=Integer.parseInt(request.getParameter("tableSize"));
				for(int i=1;i<tableSize;i++){
					hiddenXMLPath.add(request.getParameter("hiddenXMLPath"+i));
				}
				objectArgs.put("hiddenXMLPath",hiddenXMLPath);
			}
			if((request.getParameter("tableSize")!=null)&&(!request.getParameter("tableSize").equals(""))){
				tableSize=Integer.parseInt(request.getParameter("tableSize"));
				for(int i=1;i<tableSize;i++){
					arrayList.add(request.getParameter("sanctionNumber"+i));
				}
				objectArgs.put("arrayList",arrayList);
			}
			
			if((request.getParameter("cmbDept")!=null)&&(!request.getParameter("cmbDept").equals(""))){
				cmbDept=StringUtility.convertToLong(request.getParameter("cmbDept"));
				objectArgs.put("cmbDept",cmbDept);
			}
			
			if((request.getParameter("cmbLoc")!=null)&&(!request.getParameter("cmbLoc").equals(""))){
				String[] location = request.getParameter("cmbLoc").toString().split("~");
				locId = Long.valueOf(location[0]);
				locCode = location[1];
				objectArgs.put("locCode",locCode);
			}
			
			if((request.getParameter("hidxmlKey")!=null)&&(!request.getParameter("hidxmlKey").equals(""))){
				xmlpath = request.getParameter("hidxmlKey");
				objectArgs.put("xmlpath", xmlpath);
			}
			
			if((request.getParameter("hiddentxtBox")!=null)&&(!request.getParameter("hiddentxtBox").equals(""))){
				sanctionedPost = Long.valueOf(request.getParameter("hiddentxtBox").toString());
				objectArgs.put("sanctionedPost", sanctionedPost);
			}
			
		}catch(Exception e){
	//		logger.error("Error is: "+ e.getMessage());
			logger.error("ERROR in getDesignationforVacancy mehtod >>> "+e);
		}
		
		return objRes;
	}
	
}
