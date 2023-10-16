package com.tcs.sgv.allowance.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.ComponentMstDAOImpl;
import com.tcs.sgv.allowance.valueobject.HrPayComponentMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ComponentMasterServiceImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog( getClass() );
	public ResultObject getComponentDataDetail(Map objectArgs) {
		// TODO Auto-generated method stub
		//ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try{
			logger.info("Coming into the Service method getComponentDataDetail");
					
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
				
			if (resultObject==null || objectArgs == null )
			{				
				resultObject.setResultCode(-1);
				return resultObject; 
			}
			StringBuffer data=new StringBuffer();
			ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(HrPayComponentMst.class, serv.getSessionFactory());
			if(!StringUtility.getParameter("chk", request).equals(null) && StringUtility.getParameter("chk", request).equals("1"))
			{
            	
            		String desc = request.getParameter("desc").toLowerCase();
            		String editdesc="";
					
					if(!StringUtility.getParameter("editdesc", request).equals(""))
            		{
						
						editdesc= request.getParameter("editdesc").toLowerCase();
            		}	
            		
            		
            		if(!desc.equals(editdesc))
            		{
	            		List<HrPayComponentMst> actionList = componentDAO.getAllComponentDescData(desc);
	            		
	            		if(actionList.size()!=0)
						{	
							
							data.append("<componentdetails>");
							data.append("<componentdesc>").append("<![CDATA[").append(actionList.get(0).getComponentDesc()).append("]]>").append("</componentdesc>");
							data.append("</componentdetails>");
						}
						else
						{
							
							data.append("<componentdetails>");
							data.append("<componentdesc>"+null+"</componentdesc>");
							data.append("</componentdetails>");
						}
            		}
            		else
            		{
            			
						data.append("<componentdetails>");
						data.append("<componentdesc>"+null+"</componentdesc>");
						data.append("</componentdetails>");
            		}
				Map result = new HashMap();
				String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", data.toString()).toString();
		        //System.out.println(" the string buffer is :"+stateNameIdStr);
		        resultObject.setResultCode(ErrorConstants.SUCCESS); 
		        result.put("ajaxKey", stateNameIdStr);
		        resultObject.setResultValue(result);
		        resultObject.setViewName("ajaxData");    
			}
			else
			{
				List <HrPayComponentMst> actionList = componentDAO.getAllComponentMasterData();
	            Map map = new HashMap();
	            map.put("actionList", actionList);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	            resultObject.setViewName("componentMstView");
	            
			}
		}
		catch(Exception e){
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			e.printStackTrace();
		}
		return resultObject;

}
	public ResultObject getcomponentData(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method of getcomponentdata");
					
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(HrPayComponentMst.class, serv.getSessionFactory());
			if(request.getParameter("edit")!= null && (request.getParameter("edit").equals("y") || request.getParameter("edit").equals("Y")))
			{
            		//System.out.println("I m in edit mode ");
            		long componentId = Integer.parseInt(request.getParameter("componentid"));
            		List <HrPayComponentMst> componentList = componentDAO.getAllComponentMasterData();
            		HrPayComponentMst actionList = (HrPayComponentMst)componentDAO.getAllComponentIdData(componentId);
            	    
            		Map map = new HashMap();
            	    
            	    
            		objectArgs.put("actionList", actionList);
            		objectArgs.put("componentList", componentList);
			        resultObject.setResultCode(ErrorConstants.SUCCESS);
			        resultObject.setResultValue(objectArgs);
			        resultObject.setViewName("componentMstEdit");
            }
			else
			{
				List <HrPayComponentMst> actionList = componentDAO.getAllComponentMasterData();
	            Map map = new HashMap();
	            map.put("actionList", actionList);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(map);
	            resultObject.setViewName("componentMstAdd");
			}    
			
		}
		catch(Exception e){
			//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			e.printStackTrace();
		}
		return resultObject;
	}

	
	
	
	public ResultObject insertcomponentDataDetail(Map objectArgs) {
			// TODO Auto-generated method stub
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpSession session=request.getSession();
			
			try{
				logger.info("Coming into the insert Service method");
				HrPayComponentMst componentMst = (HrPayComponentMst)objectArgs.get("componentMst");
				
				GenericDaoHibernateImpl<HrPayComponentMst, Long> daoFortrial = new GenericDaoHibernateImpl<HrPayComponentMst, Long>(HrPayComponentMst.class);
				daoFortrial.setSessionFactory(serv.getSessionFactory());
				Date lstrDate =new Date();
				
				
				if(request.getParameter("edit")!= null && (request.getParameter("edit").equals("y") || request.getParameter("edit").equals("Y")))
				{
				
					String componentid = request.getParameter("componentid");
					
					componentMst.setComponentCode(componentid);
					
					Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
					
					long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
					OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
					OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
					
					long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
					OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				    OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
					 
				    
					
					
					
					
					 componentMst.setUpdatedDate(lstrDate);
					 
					 componentMst.setOrgUserMstByUpdatedBy(orgUserMst);
					 
					 componentMst.setOrgPostMstByUpdatedByPost(orgPostMst);
						
					 
					
					
					daoFortrial.update(componentMst);
				}
				else
				{
					
					
					IdGenerator idGen = new IdGenerator();
					Long id= idGen.PKGeneratorWODBLOC("hr_pay_component_mst",objectArgs);
					
					
					
					componentMst.setComponentCode(id.toString());
					
						
						
						daoFortrial.create(componentMst);
								
				}
				
				
				
				ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(HrPayComponentMst.class, serv.getSessionFactory());
				List <HrPayComponentMst> actionList = componentDAO.getAllComponentMasterData();
				
				objectArgs.put("actionList", actionList);
				logger.info("Coming into the Service method" + actionList);
				if(componentMst!=null)
					objectArgs.put("status", "1");
				else
					objectArgs.put("status", "0");
				
				
				//System.out.println("value in list is "+actionList.get(0).getDesigId());
				
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("componentMstView");
				logger.info("INSETED SUCCESSFULLY");
			}
			catch(Exception e){
				//ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
				e.printStackTrace();
			}
			return resultObject;
		}
		
}
	
	
	
