package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.util.DesigMasterServiceImplHelper;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class DesigMasterServiceImpl extends ServiceImpl{
	
	
	Log logger = LogFactory.getLog( getClass() );
	public ResultObject getDesigDataDetail(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method");
					
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			DesigMasterDAO desinDAO = new DesigMasterDAO(OrgDesignationMst.class, serv.getSessionFactory());
			StringBuffer data=new StringBuffer();
			Map voToService = (Map)objectArgs.get("voToServiceMap");
//			for the ajax request of checking the unique desig name
			
			
			//for logindetailmap
			//HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			//HttpSession session=request.getSession();	
			 //end here for logindetailmap
			
			
			//Map loginMap =(Map)session.getAttribute("loginMap");
		    Map loginMap = (Map) objectArgs.get("baseLoginMap");

			
			//long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			logger.info("***********langId**************"+langId);
			
			String chk = "";
			String dname = "";
			String editname="";
			String edit="";
			
			
			if(voToService.get("chk")!=null)
			{
				chk = (String)voToService.get("chk").toString();
			}
			if(voToService.get("dname")!=null)
			{
				dname = (String)voToService.get("dname").toString();
			}
			if(voToService.get("editname")!=null)
			{
				editname=(String)voToService.get("editname").toString();
			}
			if(voToService.get("edit")!=null)
			{
				edit = (String)voToService.get("edit").toString();
			}
			logger.info("the value of edit is "+edit);
			if(!chk.equals(null) && chk.equals("1"))
			{
				
				logger.info("I m in ajax checking mode getDesigDataDetail");   
				logger.info(" the dname is "+dname+" and the editname is "+editname);   	          		
            		/*if(!StringUtility.getParameter("editname",request).equals(""))
            		{
            			
            			editname=StringUtility.getParameter("editname",request).toLowerCase();
            		}*/
            		
            		if(!dname.equals(editname))
            		{
            		
            			
            			List<OrgDesignationMst> actionList = desinDAO.getAllDesigDescData(dname);
            			
            			if(actionList.size()!=0)
						{	
            				logger.info("I m in ajax checking mode getDesigDataDetail record exists");
	            			data.append("<desigdetails>");
	            			data.append("<designame>").append("<![CDATA[").append(actionList.get(0).getDsgnName()).append("]]>").append("</designame>");
	            			data.append("</desigdetails>");

						} 
            			else
            			{
            				logger.info("I m in ajax checking mode getDesigDataDetail record does not exist");
            				data.append("<desigdetails>");
	            			data.append("<designame>"+null+"</designame>");
	            			data.append("</desigdetails>");
            			}
            		}
            		else
            		{
            			logger.info("I m in ajax checking mode getDesigDataDetail same name in edit mode");
            			data.append("<desigdetails>");
            			data.append("<designame>"+null+"</designame>");
            			data.append("</desigdetails>");
            		}		
				
				
						
							
					         String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", data.toString()).toString();
					         
					        
					         objectArgs.put("ajaxKey", stateNameIdStr);
					         resultObject.setResultCode(ErrorConstants.SUCCESS); 
					         resultObject.setResultValue(objectArgs);
					         resultObject.setViewName("ajaxData");    
					        
				logger.info(" the string buffer is :"+stateNameIdStr);
            }
            		
            
  
			
			//for the edit mode
			
			
			else if(!edit.equals(null) && edit.equalsIgnoreCase("Y"))
			{
            		logger.info("I m in edit mode getDesigDataDetail");
            		long desigid = Long.parseLong(voToService.get("desigid").toString());
            		OrgDesignationMst actionList = (OrgDesignationMst)desinDAO.getDesigIdData(desigid);
            	    objectArgs.put("actionList", actionList);
			        resultObject.setResultCode(ErrorConstants.SUCCESS);
			        resultObject.setResultValue(objectArgs);
			        resultObject.setViewName("DesigEditList");
            }
			else
			{
				List <OrgDesignationMst> actionList = desinDAO.getAllDesgMasterData(cmnLanguageMst.getLangId());
				objectArgs.put("actionList", actionList);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(objectArgs);
	            resultObject.setViewName("dsgnMaster");
			}
		}
		catch(Exception e){
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	
	
	
	public ResultObject insertDesigDataDetail(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		//IDGenerateDelegate.getNextId("hd_case_mst",p_objServiceArgs);
		int msg=0;
		try{
			logger.info("Coming into the insert insertDesigDataDetail Service method");
			OrgDesignationMst desigMst = (OrgDesignationMst)objectArgs.get("DesigMst");
			
			DesigMasterDAO desinDAO = new DesigMasterDAO(OrgDesignationMst.class, serv.getSessionFactory());
			Date sysdate = new Date();
//			for logindetailmap
			//HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			//HttpSession session=request.getSession();	
			 //end here for logindetailmap
			
			
			
			//Map loginMap =(Map)session.getAttribute("loginMap");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			
			//long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			
			long desigid = Long.parseLong(objectArgs.get("desigid").toString());
			String desgName= objectArgs.get("desg_name").toString();
			String desgShortName =objectArgs.get("desg_short_name").toString();
			
			
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			long locId=StringUtility.convertToLong(loginMap.get("locationId").toString());
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			
			
			DesigMasterServiceImplHelper helper = new DesigMasterServiceImplHelper(serv);
			
			//for update purpose
			
			
			if(objectArgs.get("edit")!= null && objectArgs.get("edit").toString().equalsIgnoreCase("Y"))
			{
				logger.info("INSIDE UPDATE OF DESIGNATION MASTER DETAILS");
			
				helper.updateDesignationMasterDtls(postId, desigMst, desigid, desgName, desgShortName);
				msg=1;
			}
			//for insert mode
			else
			{
				logger.info("inside trial else service-----insertDesigDataDetail---------insert mode--------");
				
				helper.insertDesignationMasterDtls(userId, langId, locId, desigMst, postId);
						
			}
			if(desigMst!=null)
				objectArgs.put("status", "1");
			else
				objectArgs.put("status", "0");
			
			//added by Ankit Bhatt for "redirectMap" to avoid "paging" problem
			
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getDesigData");
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			objectArgs.put("redirectMap", redirectMap);			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			if(msg==1)
				resultObject.setViewName("DesigEditList");
			else
				resultObject.setViewName("dsgnMasteradd");
			//ended by Ankit Bhatt.
			logger.info("INSETED SUCCESSFULLY");
		}
		catch(Exception e){
			
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	
	
	
}
