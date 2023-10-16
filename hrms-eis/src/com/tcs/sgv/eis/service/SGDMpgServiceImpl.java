package com.tcs.sgv.eis.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.eis.dao.ScaleMasterDAOImpl;
import com.tcs.sgv.eis.util.SGDMpgServiceImplHelper;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class SGDMpgServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	public ResultObject getGradDesgScaleMap(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("*********getGradDesgScaleMap*********");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            GradDesgScaleMapDAO gradDesgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
	    	    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	            HttpSession session=request.getSession();		
	            Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	            Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());	            
	            String lDsgnname=request.getParameter("Dsgn_srchText_");
	            List<HrEisSgdMpg>  resultSet = (List)gradDesgScaleMapDAO.getAllMasterData(lDsgnname);
	            Map map = new HashMap();
/*                for (int i=0;i<resultSet.size();i++)
                {
                	logger.info("*****************"+resultSet.size());
                	HrEisSgdMpg dataList=new HrEisSgdMpg();
                	dataList=	resultSet.get(i) ;
                    long amt=dataList.getHrEisScaleMst().getScaleEndAmt()+dataList.getHrEisScaleMst().getScaleIncrAmt()+dataList.getHrEisScaleMst().getScaleStartAmt();
                	String name=dataList.getHrEisGdMpg().getOrgGradeMst().getGradeName() + dataList.getHrEisGdMpg().getOrgDesignationMst().getDesigName();
                }*/
	            GradeMasterDAO gradeMasterDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
	            DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
	            List<HrEisSgdMpg> SgdMpgList = new ArrayList();
    			boolean flag=false;
	            for (int i=0;i<resultSet.size();i++)
                {
	            	flag=false;
	            	HrEisSgdMpg hrEisSgdMpg=new HrEisSgdMpg();
	            	//HrEisSgdMpg hrEisSgdMpg=new HrEisSgdMpg();
	            	hrEisSgdMpg=resultSet.get(i);
	            	
                    long amt=hrEisSgdMpg.getHrEisScaleMst().getScaleEndAmt()+hrEisSgdMpg.getHrEisScaleMst().getScaleIncrAmt()+hrEisSgdMpg.getHrEisScaleMst().getScaleStartAmt();
                	String name=hrEisSgdMpg.getHrEisGdMpg().getOrgGradeMst().getGradeName() + hrEisSgdMpg.getHrEisGdMpg().getOrgDesignationMst().getDsgnName();
	            	
	            	HrEisSgdMpg hrEisSgdMpggu=new HrEisSgdMpg();
                	//gdMpgList.add(resultSet.get(i));
    				hrEisSgdMpggu.setSgdMapId(hrEisSgdMpg.getSgdMapId());
                	if(langId!=1)
            		{
            			String gradeElementCode= resultSet.get(i).getHrEisGdMpg().getOrgGradeMst().getGradeCode();
            			String desigElementCode= resultSet.get(i).getHrEisGdMpg().getOrgDesignationMst().getDsgnCode();
            			
            			
            			List<OrgGradeMst> grade = gradeMasterDAO.getGradeName(gradeElementCode, langId);
            			List<OrgDesignationMst> desig=desigMasterDAO.getDesigName(desigElementCode, langId);
    	            	HrEisGdMpg hrEisGdMpg=new HrEisGdMpg();
            			if(desig.size()>0&& grade.size()>0)
            			{
        	            	hrEisGdMpg.setOrgDesignationMst(desig.get(0));
            				hrEisGdMpg.setOrgGradeMst(grade.get(0));
        	            	hrEisSgdMpggu.setHrEisGdMpg(hrEisGdMpg);
        	            	flag = true;
            			}
            			
            			
            		}
        			else
        			{
    	            	hrEisSgdMpggu.setHrEisGdMpg(resultSet.get(i).getHrEisGdMpg());
    	            	flag = true;
        			}
                	
                	
                	HrEisScaleMst hrEisScaleMst=resultSet.get(i).getHrEisScaleMst();
                	
                	String tempBuffer="";
            		if(hrEisScaleMst.getScaleStartAmt()!=0)
            		{
            		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
                    //System.out.println( "Formatted as currency: " +currencyFormatter.format(hrEisScaleMst.getScaleStartAmt()) );
                    tempBuffer=currencyFormatter.format(hrEisScaleMst.getScaleStartAmt()).replace("$", "");
                    hrEisScaleMst.setCurrencyStartAmount(tempBuffer.replace(".00", ""));
            		}
                    
                    if(hrEisScaleMst.getScaleEndAmt()!=0)
                    {
                    //hrEisScaleMst.setCurrencyEndAmount(currencyEndAmount) ; 
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
                    currencyFormatter.setParseIntegerOnly(true);
                    //System.out.println( "Formatted as currency: " +currencyFormatter.format(hrEisScaleMst.getScaleEndAmt()) );
                    tempBuffer=currencyFormatter.format(hrEisScaleMst.getScaleEndAmt()).replace("$", "");
                    hrEisScaleMst.setCurrencyEndAmount(tempBuffer.replace(".00", ""));
                  //hrEisScaleMst.setCurrencyEndAmount(currencyFormatter.format(hrEisScaleMst.getScaleEndAmt()).replace("$", ""));
                    }      		
                    
                    if (hrEisScaleMst.getScaleHigherEndAmt()!=0)
                    {
                    	  NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
                    	  currencyFormatter.setParseIntegerOnly(true);
                          //System.out.println( "Formatted as currency: " +currencyFormatter.format(hrEisScaleMst.getScaleHigherEndAmt()) );
                          tempBuffer=currencyFormatter.format(hrEisScaleMst.getScaleHigherEndAmt()).replace("$", "");
                          hrEisScaleMst.setCurrencyHigherEndAmount(tempBuffer.replace(".00", ""));
                      //   hrEisScaleMst.setCurrencyHigherEndAmount(currencyFormatter.format(hrEisScaleMst.getScaleHigherEndAmt()).replace("$", ""));
                    }
         	
                	
	            	hrEisSgdMpggu.setHrEisScaleMst(hrEisScaleMst);
	            	hrEisSgdMpggu.setSgdMapId(resultSet.get(i).getSgdMapId());
	            	if(flag)
                	SgdMpgList.add(hrEisSgdMpggu);			
                				
                }
                
	            objectArgs.put("resultSet", SgdMpgList);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(objectArgs);
	            resultObject.setViewName("getGradDesgScaleMap");
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
	
		return resultObject;
	}
	public ResultObject GradeDesignationScaleMaster(Map objectArgs)
	{
		logger.info("*********GradeDesignationScaleMaster*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String editflag="";
		long SgdMapId=0;
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		long gradeID=0;
		try
		{
			    HttpSession session=request.getSession();		

			    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
			    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			    GradDesgScaleMapDAO gradDesgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
			    GradeMasterDAO gradeMasterDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
			    DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
			    ScaleMasterDAOImpl scaleMasterDAO = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
	            Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
				List graderesultSet = gradeMasterDAO.getAllGradeMasterData(cmnLanguageMst);
				List desigresultSet = desigMasterDAO.getAllDesgMasterData(langId);
				List scaleresultSet = scaleMasterDAO.getAllScaleData();
				HrEisSgdMpg graddesgscaleresultSet = new HrEisSgdMpg();
				
	            List<OrgGradeMst> gdList = new ArrayList();
	            for (int i=0;i<graderesultSet.size();i++)
                {
	            	OrgGradeMst orgGradeMst=new OrgGradeMst();
	            	orgGradeMst=(OrgGradeMst)graderesultSet.get(i);
	            	OrgGradeMst orgGradeMstgu=new OrgGradeMst();
                	//gdMpgList.add(resultSet.get(i));
    				orgGradeMstgu.setGradeName(orgGradeMst.getGradeName());
        			boolean flag=false;
    				if(langId!=1)
            		{
                		long LangId=1;
                		String gradeElementCode= orgGradeMst.getGradeCode();
            			            			            			
            			List<OrgGradeMst> grade = gradeMasterDAO.getGradeName(gradeElementCode, LangId);
            			if(grade.size()>0)
            			{
            				orgGradeMstgu.setGradeId(grade.get(0).getGradeId());
            				flag = true;
            			}
            			
            			
            		}
            		else
            		{
            			orgGradeMstgu.setGradeId(orgGradeMst.getGradeId());
        				flag = true;
        			}
            		if(flag)
                	gdList.add(orgGradeMstgu);			
                				
                }
				
				  Map voToService = (Map)objectArgs.get("voToServiceMap");
				  editflag=voToService.get("edit")!=null?(String)voToService.get("edit"):"";
				  if(! editflag.equals(""))
				{
					  if( voToService.get("SgdMapId")!=null)
					  SgdMapId =Long.parseLong((String)voToService.get("SgdMapId"));// Long.parseLong((request.getParameter("SgdMapId")));
				logger.info("********SgdMapId*****"+SgdMapId);
				graddesgscaleresultSet=(HrEisSgdMpg)gradDesgScaleMapDAO.getScaleGradeDesgMasterData(SgdMapId);
				if(graddesgscaleresultSet!=null)
				{
				 HrEisGdMpg hrEisGdMpg = graddesgscaleresultSet.getHrEisGdMpg();
				 long gradeId = hrEisGdMpg.getOrgGradeMst().getGradeId();
				}
				}
				Map map = new HashMap();
	            map.put("graderesultSet", gdList);
	            map.put("desigresultSet", desigresultSet);
	            map.put("scaleresultSet", scaleresultSet);
	            map.put("graddesgscaleresultSet", graddesgscaleresultSet);
	            map.put("result", "success");
	            resultObject.setResultValue(map);
	            if(!editflag.equals("Y"))
	            resultObject.setViewName("ADDGradDesgScaleMst");
	            else
		        resultObject.setViewName("EditGradDesgScaleMst");
	            
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
	
		return resultObject;
	}
	
	public ResultObject GetDesignations(Map objectArgs)
	{
		logger.info("*********GetDesignations*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();		
	    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
		long gradeId = 0;
		
		  Map voToService = (Map)objectArgs.get("voToServiceMap");
	       logger.info("***************************"+voToService); 
		  gradeId =Long.parseLong((String) voToService.get("gradeID"));
				logger.info("gradeId in Service:--->" + gradeId);
        Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	    GradDesgScaleMapDAO gradDesgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
	    List <HrEisGdMpg> desigList = (List)gradDesgScaleMapDAO.getdesigsfromgrades(gradeId,langId);
	   
        List<HrEisGdMpg> gdMpgList = new ArrayList();
        for (int i=0;i<desigList.size();i++)
        {
        	HrEisGdMpg hrEisGdMpg=new HrEisGdMpg();
        	hrEisGdMpg=(HrEisGdMpg)desigList.get(i);
        	HrEisGdMpg HrEisGdMpggu=new HrEisGdMpg();
        	//gdMpgList.add(resultSet.get(i));
        	HrEisGdMpggu.setGdMapId(hrEisGdMpg.getGdMapId());
			boolean flag=false;
			if(langId!=1)
    		{
        		long LangId=2;
        		String gradeElementCode= hrEisGdMpg.getOrgDesignationMst().getDsgnCode();
    			            			            			
    			List<OrgDesignationMst> desig = desigMasterDAO.getDesigName(gradeElementCode, LangId);
    			if(desig.size()>0)
    			{
    				
    				HrEisGdMpggu.setOrgDesignationMst(desig.get(0));
    				flag = true;
    			}
    			
    			
    		}
    		else
    		{
    			HrEisGdMpggu.setOrgDesignationMst(hrEisGdMpg.getOrgDesignationMst());
				flag = true;
			}
    		if(flag)
    			gdMpgList.add(HrEisGdMpggu);			
        				
        }
	    
	    StringBuffer propertyList = new StringBuffer();
		 for (Iterator iter = gdMpgList.iterator(); iter.hasNext();)
         {			 
		  HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
		  hrEisGdMpg=(HrEisGdMpg) iter.next();
		  long DesignationsId = hrEisGdMpg.getGdMapId();
		  String DesignationsName = (String) hrEisGdMpg.getOrgDesignationMst().getDsgnName();
   		  propertyList.append("<Designations-mapping>");   	
          propertyList.append("<Designations-Id>").append(DesignationsId).append("</Designations-Id>");
          propertyList.append("<Designations-name>").append("<![CDATA[").append(DesignationsName).append("]]>").append("</Designations-name>");               
          propertyList.append("</Designations-mapping>");
         }
   	     Map result = new HashMap();
         String Desigdata = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         result.put("ajaxKey", Desigdata);
            
         resultObject.setResultValue(result);
         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
        logger.info("After Service Called.........\n"+Desigdata);
        return resultObject;
		
	}
	
	public ResultObject GetScalesFromGD(Map objectArgs)
	{
		logger.info("*********GetScalesFromGD*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();		
	    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		long gdMapId = 0;
		//if(request.getParameter("gdMapId")!=null)
		//	gdMapId=Long.parseLong(request.getParameter("gdMapId"));
		  Map voToService = (Map)objectArgs.get("voToServiceMap");
	       logger.info("***************************"+voToService); 
	       gdMapId=Long.parseLong((String) voToService.get("gdMapId"));
		logger.info("gradeId in Service:--->" + gdMapId);
        Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	    GradDesgScaleMapDAO gradDesgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
	    List <HrEisSgdMpg> scaleList = (List)gradDesgScaleMapDAO.getScalefromGD(gdMapId,langId);
		StringBuffer propertyList = new StringBuffer();
		 for (Iterator iter = scaleList.iterator(); iter.hasNext();)
         {			 
			 HrEisSgdMpg hrEisSgdMpg = new HrEisSgdMpg();
			 hrEisSgdMpg=(HrEisSgdMpg) iter.next();
		  long DesignationsId = hrEisSgdMpg.getSgdMapId();
		  long startAmt =  hrEisSgdMpg.getHrEisScaleMst().getScaleStartAmt(); 
		  long incrAmt = hrEisSgdMpg.getHrEisScaleMst().getScaleIncrAmt();
		  long endAmt = hrEisSgdMpg.getHrEisScaleMst().getScaleEndAmt();
		  String scaleName = startAmt + "-" + incrAmt + "-" + endAmt;
		  
		  if(hrEisSgdMpg.getHrEisScaleMst().getScaleHigherEndAmt()!=0)
			  scaleName+="-"+hrEisSgdMpg.getHrEisScaleMst().getScaleHigherIncrAmt()+ "-"+hrEisSgdMpg.getHrEisScaleMst().getScaleHigherEndAmt();
		  
   		  propertyList.append("<Scale-mapping>");   	
          propertyList.append("<Scales-Id>").append(DesignationsId).append("</Scales-Id>");
          propertyList.append("<Scales-name>").append("<![CDATA[").append(scaleName).append("]]>").append("</Scales-name>");               
          propertyList.append("</Scale-mapping>");
         }
   	     Map result = new HashMap();
         String scaleData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         result.put("ajaxKey", scaleData);
            
         resultObject.setResultValue(result);
         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
        logger.info("After Service Called.........\n"+scaleData);
        return resultObject;
		
	 }
	
	public ResultObject AddGradeDesignationScalempg(Map objectArgs)  throws ConstraintViolationException
	{
		logger.info("*********AddGradeDesignationScalempg*********");
		ResultObject  resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");	
		HrEisSgdMpg hrEisSgdMpg=	(HrEisSgdMpg)objectArgs.get("hrEisSgdMpg");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String updateflag="";
        Map map = new HashMap();
		try
		{
			HttpSession session=request.getSession();
			Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
			
			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
            CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
            
           
            long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			 long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);		
			
			SGDMpgServiceImplHelper helper = new SGDMpgServiceImplHelper(serv);
			updateflag = (String)objectArgs.get("updateflag");;
		
		//if(hrEisSgdMpg!=null)
		{
			long  SgdMapId =0;
			GradDesgScaleMapDAO gradDesgScaleMapDAO=new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
			if(updateflag.equals("true"))
			{	
			SgdMapId = (Long) objectArgs.get("SgdMapId");
			hrEisSgdMpg=gradDesgScaleMapDAO.read(SgdMapId);
			}
			Date lstrDate =new Date();

            GenericDaoHibernateImpl<HrEisSgdMpg, Long> daoFortrial = new GenericDaoHibernateImpl<HrEisSgdMpg, Long>(HrEisSgdMpg.class);
			daoFortrial.setSessionFactory(serv.getSessionFactory());
			gradDesgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
			logger.info("*********add/update*********"+updateflag);
			//Transaction tx=s
            long SgdMapID=0;
            GenericDaoHibernateImpl gImpl = null;
            gImpl = new GenericDaoHibernateImpl(HrEisGdMpg.class);
            gImpl.setSessionFactory(serv.getSessionFactory());
        	long gdMapId=(Long)objectArgs.get("gdMapId");
            logger.info("*********"+gdMapId);
            HrEisGdMpg hrEisGdMpg=new HrEisGdMpg();
            hrEisGdMpg = (HrEisGdMpg) gImpl.read(gdMapId);
        	long scaleId=(Long)objectArgs.get("scaleId");
        	
        	List checkData= gradDesgScaleMapDAO.getScaleGradeDesgMasterData(gdMapId,scaleId); 
        	logger.info("the list size is "+checkData.size());
        	logger.info(" and the update flag is "+updateflag);
        	if(checkData.size()<=0)
        	{	
            HrEisScaleMst hrEisScaleMst=new HrEisScaleMst();
            gImpl = new GenericDaoHibernateImpl(HrEisScaleMst.class);
            gImpl.setSessionFactory(serv.getSessionFactory());
            hrEisScaleMst = (HrEisScaleMst) gImpl.read(scaleId);
            hrEisSgdMpg.setHrEisGdMpg(hrEisGdMpg);
            hrEisSgdMpg.setHrEisScaleMst(hrEisScaleMst);
            hrEisSgdMpg.setCmnLocationMst(cmnLocationMst);
            hrEisSgdMpg.setCmnDatabaseMst(cmnDatabaseMst);
            hrEisSgdMpg.setOrgUserMstByUpdatedBy(orgUserMst);
			hrEisSgdMpg.setUpdatedDate(lstrDate);
            hrEisSgdMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
            
            if(updateflag.equals("true"))
            {
            	logger.info("INSIDE UPDATE");
            	helper.updateGradeDesingnScaleMpg(hrEisSgdMpg, SgdMapID);
	           objectArgs.put("msg", "Record Updated Successfully");
	            msg=1;
	            objectArgs.put("result","1");
            }
            else
            {
            	logger.info("INSIDE CREATE");
            	helper.insertGradeDesingnScaleMpg(hrEisSgdMpg, postId, userId, langId, locId);
	           objectArgs.put("msg", "Record Inserted Successfully");
                objectArgs.put("result","0");
            }
    		    
        	}
        	else
        	{
    			objectArgs.put("result","-1");
    			msg=-1;
        	}
		}
		GradDesgScaleMapDAO gradDesgScaleMapDAO=new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
       // Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
        List<HrEisSgdMpg>  resultSet = gradDesgScaleMapDAO.getAllMasterData();
        /*for (int i=0;i<resultSet.size();i++)
        {
        	HrEisSgdMpg dataList=new HrEisSgdMpg();
        	dataList=	resultSet.get(i) ;
            long amt=dataList.getHrEisScaleMst().getScaleEndAmt()+dataList.getHrEisScaleMst().getScaleIncrAmt()+dataList.getHrEisScaleMst().getScaleStartAmt();
        	String name=dataList.getHrEisGdMpg().getOrgGradeMst().getGradeName() + dataList.getHrEisGdMpg().getOrgDesignationMst().getDesigName();
        	
        }*/
        GradeMasterDAO gradeMasterDAO = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
        DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());
        List<HrEisSgdMpg> SgdMpgList = new ArrayList();
		boolean flag=false;
		for (int i=0;i<resultSet.size();i++)
        {
			HrEisSgdMpg hrEissgdMpg=new HrEisSgdMpg();
        	//HrEisSgdMpg hrEisSgdMpg=new HrEisSgdMpg();
        	long SgdMapID=resultSet.get(i).getSgdMapId();
        	hrEissgdMpg = gradDesgScaleMapDAO.read(SgdMapID);

        	flag=false;
        	long amt=hrEissgdMpg.getHrEisScaleMst().getScaleEndAmt()+hrEissgdMpg.getHrEisScaleMst().getScaleIncrAmt()+hrEissgdMpg.getHrEisScaleMst().getScaleStartAmt();
			
            String name=hrEissgdMpg.getHrEisGdMpg().getOrgGradeMst().getGradeName()!=null?hrEissgdMpg.getHrEisGdMpg().getOrgGradeMst().getGradeName():""; 
            name= hrEissgdMpg.getHrEisGdMpg().getOrgDesignationMst().getDsgnName();
        	HrEisSgdMpg hrEisSgdMpggu=new HrEisSgdMpg();
        	//gdMpgList.add(resultSet.get(i));
			hrEisSgdMpggu.setSgdMapId(hrEissgdMpg.getSgdMapId());
        	if(langId!=1)
    		{
    			String gradeElementCode= resultSet.get(i).getHrEisGdMpg().getOrgGradeMst().getGradeCode();
    			String desigElementCode= resultSet.get(i).getHrEisGdMpg().getOrgDesignationMst().getDsgnCode();
    			
    			List<OrgGradeMst> grade = gradeMasterDAO.getGradeName(gradeElementCode, langId);
    			List<OrgDesignationMst> desig=desigMasterDAO.getDesigName(desigElementCode, langId);
            	HrEisGdMpg hrEisGdMpg=new HrEisGdMpg();
            	if(desig.size()>0&& grade.size()>0)
    			{
	            	hrEisGdMpg.setOrgDesignationMst(desig.get(0));
    				hrEisGdMpg.setOrgGradeMst(grade.get(0));
	            	hrEisSgdMpggu.setHrEisGdMpg(hrEisGdMpg);
	            	flag = true;
    			}
    			
    		}
			else
			{
				hrEisSgdMpggu.setHrEisGdMpg(resultSet.get(i).getHrEisGdMpg());
            	flag = true;
			}
        	hrEisSgdMpggu.setHrEisScaleMst(resultSet.get(i).getHrEisScaleMst());
        	hrEisSgdMpggu.setSgdMapId(resultSet.get(i).getSgdMapId());
        	if(flag)
        	SgdMpgList.add(hrEisSgdMpggu);			
        				
        }
        objectArgs.put("resultSet", SgdMpgList);
        
//      changes by Ankit Bhatt for "redirectMap"
		objectArgs.put("resultofmapping","success");
		
		Map redirectMap = new HashMap();
		redirectMap.put("actionFlag", "getGradDesgScaleMap");
		if(msg==1)
			objectArgs.put("MESSAGECODE",300006);
		else if(msg==-1)
			objectArgs.put("MESSAGECODE",300007);
		else
			
			objectArgs.put("MESSAGECODE",300005);
		objectArgs.put("redirectMap", redirectMap);			
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		
		resultObject.setResultValue(objectArgs);
		//resultObject.setViewName("redirect view");
		if(msg==1)
			resultObject.setViewName("EditGradDesgScaleMst");
		else
			resultObject.setViewName("ADDGradDesgScaleMst");
		/*resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("getGradDesgScaleMap");
		objectArgs.put("resultofmapping","success");*/
		//ended by Ankit Bhatt.
		
		
		
		}
		/*catch(ConstraintViolationException c)
		{
			System.out.println("Mapping Already Exists");
			objectArgs.put("result","Mapping Already Exists");
			resultObject=getGradDesgScaleMap(objectArgs);
		}*/
		catch(Exception e)
		{
			objectArgs.put("result","Mapping Already Exists");
			resultObject.setViewName("getGradDesgScaleMap");
		}
		return resultObject;
		
		
	}
	
	
}
