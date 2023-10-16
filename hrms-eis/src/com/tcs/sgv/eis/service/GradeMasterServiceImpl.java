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
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;


public class GradeMasterServiceImpl extends ServiceImpl {
	
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	public ResultObject getGradeDataDetail(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getGradeDataDetail");
					
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			GradeMasterDAO gradeDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
			
			
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			
			StringBuffer data=new StringBuffer();
			
			//for the ajax request of checking the unique grade name
            
//			for logindetailmap
			//HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			//HttpSession session=request.getSession();	
			 //end here for logindetailmap
			
			
			
			//Map loginMap =(Map)session.getAttribute("loginMap");
		    Map loginMap = (Map) objectArgs.get("baseLoginMap");

			
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			String chk = "";
			String gname = "";
			String editname="";
			String edit="";
			
			
			if(voToService.get("chk")!=null)
			{
				chk = (String)voToService.get("chk").toString();
			}
			if(voToService.get("gname")!=null)
			{
				gname = (String)voToService.get("gname").toString();
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
				
				
					/*if(!editname.equals(""))
            		{
						
						editname= StringUtility.getParameter("editname", request).toLowerCase();
            		}	*/			
					logger.info("I m in ajax checking mode getGradeDataDetail");            				
            		
            		if(!gname.equals(editname))
            		{
            			List<OrgGradeMst> actionList = gradeDAO.getAllGradeDescData(gname);
	            		if(actionList.size()!=0)
						{
	            			logger.info("I m in ajax checking mode getGradeDataDetail record exists "); 
	            			data.append("<gradedetails>");
							data.append("<gradedesc>").append("<![CDATA[").append(actionList.get(0).getGradeName()).append("]]>").append("</gradedesc>");
							data.append("</gradedetails>");
						}
						else 
						{
							logger.info("I m in ajax checking mode getGradeDataDetail record does not exist "); 
							data.append("<gradedetails>");
							data.append("<gradedesc>"+null+"</gradedesc>");
							data.append("</gradedetails>");
						}
		       
            		}
            		else
            		{
            			logger.info("I m in ajax checking mode getGradeDataDetail same name in edit mode "); 
            			data.append("<gradedetails>");
						data.append("<gradedesc>"+null+"</gradedesc>");
						data.append("</gradedetails>");
            		}
	            	Map result = new HashMap();
					String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", data.toString()).toString();
					result.put("ajaxKey", stateNameIdStr);
			        resultObject.setResultCode(ErrorConstants.SUCCESS); 
			        resultObject.setResultValue(result);
			        resultObject.setViewName("ajaxData");    
			        logger.info(" the string buffer is :"+stateNameIdStr);
            	}
            		
             
			
			//for the edit mode
			
			
			else if(!edit.equals(null) && edit.equalsIgnoreCase("Y"))
			{
            		logger.info("I m in edit mode getGradeDataDetail");
            		long gradeId = Long.parseLong(voToService.get("gradeid").toString());
            		
            		OrgGradeMst actionList = (OrgGradeMst)gradeDAO.getGradeIdData(gradeId);
            	    
            		logger.info(actionList.getGradeDesc());
            		
            	   	objectArgs.put("actionList", actionList);
			        resultObject.setResultCode(ErrorConstants.SUCCESS);
			        resultObject.setResultValue(objectArgs);
			        resultObject.setViewName("gradeMstEdit");
            }
			
			//for view mode
			else
			{
				logger.info("I m in view mode getGradeDataDetail");
				List <OrgGradeMst> actionList = gradeDAO.getAllGradeMasterData(cmnLanguageMst);
	            objectArgs.put("actionList", actionList);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(objectArgs);
	            resultObject.setViewName("gradeMstView");
			}
		}
		catch(Exception e){
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	
	
	public ResultObject insertGradeDataDetail(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		
		try{
			logger.info("Coming into the insert Service method insertGradeDataDetail");
			OrgGradeMst gradeMst = (OrgGradeMst)objectArgs.get("GradeMst");
					
			Date sysdate = new Date();
			GradeMasterDAO gradeMstDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
			
			//for logindetailmap
			//HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			//HttpSession session=request.getSession();	
			 //end here for logindetailmap
			
			
			
			//Map loginMap =(Map)session.getAttribute("loginMap");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			
			
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			
			long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			
			long locId=StringUtility.convertToLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	     
		
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			
			long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			//for update purpose
			if(objectArgs.get("edit")!= null && objectArgs.get("edit").toString().equalsIgnoreCase("Y"))
			{
				logger.info(" insertGradeDataDetail inside if service-------edit mode---------------");
				long gradeId = Long.parseLong(objectArgs.get("gradeid").toString());
				gradeMst=gradeMstDAO.read(gradeId);
				
				gradeMst.setGradeName(objectArgs.get("grade_name").toString());
				gradeMst.setGradeDesc(objectArgs.get("grade_desc").toString());
				gradeMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				gradeMst.setOrgUserMstByUpdatedBy(orgUserMst);
				gradeMst.setUpdatedDate(sysdate);
				
				gradeMstDAO.update(gradeMst);
				msg=1;
			}
				
			//for insert purpose
			else
			{
				logger.info(" insertGradeDataDetail inside else service----insert mode------------------");
				
				IdGenerator idGen = new IdGenerator();
				Long id= idGen.PKGeneratorWODBLOC("hr_eis_grade_mst",objectArgs);
				
				logger.info("****************************the id generated is "+id);
				
				gradeMst.setGradeId(id);
				gradeMst.setCmnLanguageMst(cmnLanguageMst);
				gradeMst.setOrgPostMstByCreatedByPost(orgPostMst);
				gradeMst.setOrgUserMstByCreatedBy(orgUserMst);
				gradeMst.setOrgPostMstByUpdatedByPost(orgPostMst);
				gradeMst.setOrgUserMstByUpdatedBy(orgUserMst);
				gradeMst.setCreatedDate(sysdate);
				gradeMst.setUpdatedDate(sysdate);
				
				gradeMstDAO.create(gradeMst);
							
			}
			List <OrgGradeMst> actionList = gradeMstDAO.getAllGradeMasterData(cmnLanguageMst);
			//objectArgs.put("actionList", actionList);
			if(gradeMst!=null)
				objectArgs.put("status", "1");
			else
				objectArgs.put("status", "0");
			
			logger.info("from insertGradeDataDetail size of  actionList is "+actionList.size());
			
			//added by Ankit Bhatt for using "redirectMap" to avoid "paging" problem.
//			Changed by Mrugesh for displaying (Insert/Update) message
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getGradeData");
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			objectArgs.put("redirectMap", redirectMap);			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			//resultObject.setViewName("redirect view");
			if(msg==1)
				resultObject.setViewName("gradeMstEdit");
			else
				resultObject.setViewName("gradeMstAdd");
			/*
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("gradeMstView");*/
			//Ended by Mrugesh
			//ended by Ankit Bhatt.
			logger.info("INSETED SUCCESSFULLY");
			
		}
		catch(Exception e){
			
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	
	
}
