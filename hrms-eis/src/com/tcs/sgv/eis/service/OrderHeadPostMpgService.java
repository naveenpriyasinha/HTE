package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BillHeadDao;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.dao.OrderHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadPostmpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderMstDAO;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PsrPostMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayCustOrderHeadPost;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDao;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.dao.UserPostDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;



public class OrderHeadPostMpgService extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	public ResultObject getOrderHeadPostData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	
		try
		{
			logger.info("*********getOrderHeadPostData*********");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			    OrderHeadPostmpgDAOImpl orderheadpostmpg = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
	    	    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	            HttpSession session=request.getSession();		
	            Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	            long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	            // Modify By Urvin Shah
	            long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
	 	       CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
	   		   String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
	   		   //End.
	            Map voToService = (Map)objectArgs.get("voToServiceMap");
	            long empId=0; 
	            long userId=0;
	            long billId=0;
	            String empName = "";
				if((voToService.get("Employee_srchNameText_OHPSearch")!=null&&!voToService.get("Employee_srchNameText_OHPSearch").equals("")))
					empName=(String)voToService.get("Employee_srchNameText_OHPSearch").toString();
				logger.info("::::::::::::::::::::::::::::::: emp name" + empName);
	            /*###################################################################################*/
	            //Added By Varun For Bill Wise Search in OHP Dt.05-08-2008
	            HrPayBillHeadMpg billHeadMpg = new HrPayBillHeadMpg();
	            BillHeadMpgDAOImpl billHeadMpgDAOImpl = null;//new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class,serv.getSessionFactory());
	           List<HrPayBillHeadMpg> hrPayBillHeadMpgList = billHeadMpgDAOImpl.getAllData(locationCode,langId);
	           /*if(hrPayBillHeadMpgList!=null && hrPayBillHeadMpgList.size() >0)
	           {
	        	   billHeadMpg =hrPayBillHeadMpgList.get(0);
	        	   billId = billHeadMpg.getBillId();
	        	   logger.info("Bill Id is:-->"+billId);
	           }*/
	            objectArgs.put("billHeadMpg", hrPayBillHeadMpgList);
	            
	            //Ended By Varun For Bill Wise Search in OHP Dt.05-08-2008
	            /*###################################################################################*/
	            if(voToService.get("Employee_ID_OHPSearch")!=null && !((String)voToService.get("Employee_ID_OHPSearch")).equals("") )
	            {
	            	empId=Long.parseLong((String)voToService.get("Employee_ID_OHPSearch"));
	            	
	            	EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
	            	
	            	userId = orgEmpDao.read(empId).getOrgUserMst().getUserId();
	            	
	            }
	                
	            //Added By Mrugesh for financial year issue
	  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
	  	        Date currDate = Calendar.getInstance().getTime();
	  	        
	  			DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	  	        SimpleDateFormat sdf = sbConf.GetDateFormat();
	  	        String dt = sdf.format(currDate);
	  	        int finYrId = finYearDAOImpl.getFinYearId(dt);
	  	        //Ended By Mrugesh
	            List  resultSet = new ArrayList();
	            if(empId!=0)
	            {
	            	resultSet = (List)orderheadpostmpg.getAllData(userId,locId,langId,finYrId);
	            }
	            else
	            {
	            	resultSet = (List)orderheadpostmpg.getAllData(locId,langId,finYrId);
	            }
	            List dataList = new ArrayList();
	            long ohpid=0;
	           //added by Ankit Bhatt for Displaying View Page with Post Search
	            String postSearchFlag = "N";
	            if(voToService.get("postSearchFlag")!=null)
	            	postSearchFlag= voToService.get("postSearchFlag").toString();
	            if(postSearchFlag.equalsIgnoreCase("y"))
	            objectArgs.put("postSearchFlag","true");
	            else
	            	objectArgs.put("postSearchFlag","false");
	            //ended
				 long postid=0;
				// ArrayList temp = new ArrayList();
				 //List ohpList = new ArrayList();
	            
	            HrPayCustOrderHeadPost hrPayCustOrderHeadPost=new HrPayCustOrderHeadPost();
               // for (int i=0;i<resultSet.size();i++)
                	
                	logger.info("----------------listsize is -----------------------"+resultSet.size());
                	Object[] row;
                	if(resultSet.size() >0 && resultSet!=null)  {                		
        		    	 for(int k=0;k<resultSet.size();k++) {
	        		    	 //logger.info("orderheadpost id is " + resultSet.get(0));
        		    		 hrPayCustOrderHeadPost=new HrPayCustOrderHeadPost();
	        		    	 row = (Object[])resultSet.get(k);
	        		    	 ohpid   = Long.parseLong(row[0].toString());
	        		    //	 logger.info("orderheadpost id is  " + ohpid); 
	        		    	 hrPayCustOrderHeadPost.setOrderHeadPostId(ohpid);
	        		    	 
	        		    //	 logger.info("ordername  is " + resultSet.get(1));
	        		    	 //Object[] row1 = (Object[])resultSet.get(k);
	        		    	 String orderName   = (row[1].toString());
	        		    	 
	        		    	// logger.info("ordername id is  " + orderName); 
	        		    	 hrPayCustOrderHeadPost.setOrderName(orderName);
	        		    	 
	        		    	// logger.info("setSubHeadDesc  is " + resultSet.get(2));
	        		    	// Object[] row2 = (Object[])resultSet.get(k);
	        		    	 String subHeadDesc   = (row[2].toString());
	        		    //	 logger.info("setSubHeadDesc  is  " + subHeadDesc); 
	        		    	 hrPayCustOrderHeadPost.setSubHeadDesc(subHeadDesc);
	        		    	 
	        		    	 
	        		    //	 logger.info("post  is " + resultSet.get(3));
	        		    	// Object[] row3 = (Object[])resultSet.get(k);
	        		    	 String post   = (row[3].toString());
	        		    	 hrPayCustOrderHeadPost.setPost(post);
	        		    	 
	        		    //	 Object[] row4 = (Object [])resultSet.get(k);
	        		    	 
	        		    	 
	        		    	 //changed by manoj for vacant post issue
	        		    	 String empfname = "";
	        		    	 if(row[4]!=null && !row[4].toString().equals("") && !row[4].toString().equals(" "))
	        		    	 {
	        		    	   	 empfname = (row[4].toString());
	        		    	 }
	        		    	 else
	        		    	 {
	        		    	 	 empfname = "Vacant";
	        		    	 }
	        		    	 logger.info(" for testing empfname "+empfname);
	        		    	 hrPayCustOrderHeadPost.setEmpFName(empfname);
	        		    	 
	        		    	// Object[] row5 = (Object [])resultSet.get(k);
	        		    	 //String empmname  = (row[5]!=null?row[5].toString():"");
	        		    	 String empmname  = "";
	        	     		 hrPayCustOrderHeadPost.setEmpMName(empmname);
	        	     		 
	        		    	// Object[] row6 = (Object [])resultSet.get(k);
	        		    	// String emplname  = (row[6].toString());
	        	     		 String emplname  = "";
	        		    	 hrPayCustOrderHeadPost.setEmpLName(emplname); 
	        		    	  
	        		    	 
	        		    	 //end by manoj for vacant post issue
	        		    //	 logger.info("Post is  " + post); 
	        		    	 
	        	     		   	     		
	        		  
	        		    	           dataList.add(hrPayCustOrderHeadPost);
        		    	 
        			    }	 
        		     }
        		    	 else{
        		    		 logger.info("sevice is executed");
        		    	 }
        			    
              
           /*    	Iterator r = resultSet.iterator();
               	r.hasNext();
               	//ohpid=Long.parseLong(resultSet.get(1).toString());
               	logger.info("order head id=====>"+resultSet.get(1).toString());
                	dataList.setOrderHeadPostId(ohpid);
              	dataList.setOrderName(resultSet.get(2).toString()); 
                	logger.info("order head id=====>"+resultSet.get(2).toString());
             	dataList.setSubHeadDesc(resultSet.get(3).toString()); 
                	logger.info("order head id=====>"+resultSet.get(3).toString());
                	dataList.setPost(resultSet.get(4).toString()); 
                	logger.info("order head id=====>"+resultSet.get(4).toString());
                	logger.info("*****************"+resultSet.size());*/
                		objectArgs.put("empName",empName);
                		objectArgs.put("actionList", dataList);
                		resultObject.setResultCode(ErrorConstants.SUCCESS);
                		resultObject.setResultValue(objectArgs);  //added By ravi
                		resultObject.setViewName("OrderHeadPostmpgview");
        		     }
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
	
		return resultObject;
		}

/*	            List<HrPayCustOrderHeadPost> OrderHeadPostList = new ArrayList();
    			boolean flag=false;
	            for (int i=0;i<resultSet.size();i++)
                {
	            	flag=false;
	            	HrPayCustOrderHeadPost hrpayorderheadpostmpg=new HrPayCustOrderHeadPost();
	            	
	            	//HrEisSgdMpg hrEisSgdMpg=new HrEisSgdMpg();
	            	hrpayorderheadpostmpg=  (HrPayCustOrderHeadPost)resultSet.get(i);
	            	
	            	
	            String ordername= hrpayorderheadpostmpg.getOrderName();
	            String SubHeadDesc= hrpayorderheadpostmpg.getSubHeadDesc();
	            String Post = hrpayorderheadpostmpg.getPost();
	            long orderHeadPostId = hrpayorderheadpostmpg.getOrderHeadPostId();
	            	
                    //long amt=hrEisSgdMpg.getHrEisScaleMst().getScaleEndAmt()+hrEisSgdMpg.getHrEisScaleMst().getScaleIncrAmt()+hrEisSgdMpg.getHrEisScaleMst().getScaleStartAmt();
                	//String name=hrEisSgdMpg.getHrEisGdMpg().getOrgGradeMst().getGradeName() + hrEisSgdMpg.getHrEisGdMpg().getOrgDesignationMst().getDsgnName();
	            	
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
	            	hrEisSgdMpggu.setHrEisScaleMst(resultSet.get(i).getHrEisScaleMst());
	            	hrEisSgdMpggu.setSgdMapId(resultSet.get(i).getSgdMapId());
	            	if(flag)
                	SgdMpgList.add(hrEisSgdMpggu);			
                				
                }
       */         
	public ResultObject OrderHeadPostMaster(Map objectArgs)
	{
		logger.info("**********Inside orderheadpostMaster**************");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String editflag="";
		//long OhpMapId;
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			    HttpSession session=request.getSession();		
			    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
			    
			    long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			    logger.info("******UserId:-************"+userId);
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
				
			    
			    Map voToService = (Map)objectArgs.get("voToServiceMap");
			    String editFlag = (String)voToService.get("edit");//edit flag passing
				logger.info("Flag Name "+editFlag );
				long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
//				added by Ankit Bhatt for getting Post which is mapped with Bill No
				PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				// Modify By Urvin Shah
				long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			       CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		   		   String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
			      boolean isBillDefined = payBillDAO.isBillsDefined(locationCode,langId);
			     // logger.info("isBillDefined for " + locId + " " + isBillDefined);
			      ArrayList billList = new ArrayList();  
				if(isBillDefined)
				{
				 ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
				 long finYearId=Long.parseLong(resourceBundle.getString("finYearId"));
	    		
		    		List billNoList =null;// payBillDAO.getBillNo(finYearId,locId);
		    		logger.info("The size of billNoList is---->"+billNoList.size());
		    		  		
		    		for(Iterator itr=billNoList.iterator();itr.hasNext();)
		    		{
		    			HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
		    			Object[] row = (Object[])itr.next();
		    			String billNo = row[1].toString();
		    			String billHeadId = row[0].toString();
		    			//billNoCustomVO.setBillId(Long.parseLong(billNo));
		    			billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
		    			billList.add(billNoCustomVO);
		    		}
				}
				
				//ended by Ankit Bhatt.
				OrderHeadPostmpgDAOImpl orderHeadPostMpgDAO = new OrderHeadPostmpgDAOImpl (HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
			    OrderHeadPostmpgDAOImpl orderheadpostMasterDAO =  new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
			    OrderHeadMpgDAOImpl headMasterDAO = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
			    OrderMstDAO orderMstDAO = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
			    
			    //OrgPostDetailsRlt orgpostdetails =new OrgPostDetailsRlt();
			    /*OrgPostDetailsRltDaoImpl orgPostDetailsRltDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
			    List orgPostList = orgPostDetailsRltDaoImpl.list();*/
			   
			    UserPostDaoImpl userpostdao = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());
			    
			    EmpDAOImpl empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			    List orgEmpList = empDAO.list();
			    if(orgEmpList!=null)
			     logger.info("orgEmpList " + orgEmpList.size());
			    long loc_id =Long.parseLong(orderHeadPostMpgDAO.getLocationCode(userId,langId).get(0).toString()); 
			    logger.info("loc_id"+loc_id);
	            //Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	            //CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				//CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
	            CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory()); 
				List desigresultSet = desigMasterDAO.getAllDesgMasterData(langId);            
          	    objectArgs.put("desigresultSet", desigresultSet);	            
	            List orderresultSet = orderMstDAO.getAllData(locationCode,locationDAO.read(Long.parseLong(loginDetailsMap.get("locationId").toString())).getLocationCode());
				List headresultSet = headMasterDAO.getAllSubhdData();
				List deptList = null;
//				by rahul
				   PaybillHeadMpgDAOImpl paybillheadmpgdao = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
			       boolean isRoleAdminFlag = paybillheadmpgdao.isLoggedInUserAdmin(loginDetailsMap);			       
			       if(isRoleAdminFlag)
					{
						logger.info("true::");
						deptList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);
						logger.info("deptlist.size::"+ deptList.size());
					}
					else
					{
						logger.info("false::");
						String[] critariaArugs={"cmnLanguageMst.langId","locId"};
						Object[] valueArgus=new Object[2];
						valueArgus[0] = langId;
						valueArgus[1] = locId;
						deptList = locationDAO.getListByColumnAndValue(critariaArugs, valueArgus);
						logger.info("deptlist.size::"+ deptList.size());
					}
			       //ended by rahul
				//List userpostrltSet = headMasterDAO.getAllUserPostRltData(loc_id);
				long dsgnId=0;
				List userpostrltSet = new ArrayList();
				List ohpList = new ArrayList();
				
				
				
				
		   if(editFlag != null && editFlag.equals("Y"))
				{
				logger.info("inside update");
				

				long OhpMapId = voToService.get("OhpMapId")!=null?Long.parseLong((String)voToService.get("OhpMapId")):0;
				logger.info("order head post id===>"+OhpMapId);
				HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg = orderHeadPostMpgDAO.read(OhpMapId);
				long orderHeadId = hrPayOrderHeadPostMpg.getOrderHeadId();	
				long PostId = hrPayOrderHeadPostMpg.getPostId();
				logger.info("post id is"+PostId);
				
				OrgPostDetailsRltDaoImpl postDetailDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
				
				OrgPostDetailsRlt postDetailVo = postDetailDao.getPostDetailsRltByPostIdAndLangId(PostId,new Long(1));//hardcoded lang id
				
				long desigId = postDetailVo.getOrgDesignationMst().getDsgnId();
				
				logger.info("desigId is"+desigId);
				//by manoj for vacant post issue
				//userpostrltSet=headMasterDAO.getAllUserPostRltData(loc_id); 
				userpostrltSet=orderheadpostMasterDAO.getAllUserPostRltDatabyDesgEdit(loc_id,desigId);
				//end by manoj 
				logger.info("size of userpostrltSet"+userpostrltSet);
				
				HrPayCustOrderHeadPost hrorderheadpost = new HrPayCustOrderHeadPost();
				
				
				
				
				Object[] row;
				String post=null;
				 String empfname=null;
				 String empmname=null;
				 String emplname=null;
				 long postid=0;
				 //ArrayList temp = new ArrayList();
				 //List ohpList = new ArrayList();
	         	
				
		            				 
				 if(userpostrltSet!=null &&  userpostrltSet.size() >0 )  {                		
	 		    	 for(int k=0;k<userpostrltSet.size();k++) {
	     		    	 //logger.info("orderheadpost id is " + resultSet.get(0)); 		    		 
	     		    	
	 		    		 HrPayCustOrderHeadPost hrpay = new HrPayCustOrderHeadPost();
	 		    		 row = (Object[])userpostrltSet.get(k);
	 		    		 postid = Long.parseLong(row[0].toString());
	     		    	// logger.info("Post ID in Newly Added Loop " + postid);
	     		    	 
	 		    		 //changed by manoj for vacant post issue
        		    	
        		    	 if(row[2]!=null && !row[2].toString().equals("") && !row[2].toString().equals(" "))
        		    	 {
        		    	   	 empfname = (row[2].toString());
        		    	 }
        		    	 else
        		    	 {
        		    	 	 empfname = "Vacant";
        		    	 }
	     		    	//empfname = (row[2].toString());
	     		    	// logger.info("Emp First Name in Newly Added Loop " + empfname);
	     		    	     		    	 
	     		    	//empmname = (row[3]!=null?row[3].toString():"");
        		    	 empmname = "";
	     		    	// logger.info("Emp middle Name in Newly Added Loop " + empmname);
	     		    	//emplname = (row[4].toString());
        		    	 emplname="";
	     		    	// logger.info("Emp last Name in Newly Added Loop " + emplname); 

	     		    	    	
	     		    	 logger.info("testing by manoj for vacant post "+empfname);
	   		    	  if(row[1]!=null || !row[1].toString().equals("") ||  !row[1].toString().equals(" "))
	   		    		  post = empfname+" ( "+row[1].toString()+" )";
	   		    	 logger.info("post "+post);
	     		    	 
	     		    	hrpay.setEmpFName(empfname);
	     		    	hrpay.setEmpLName(emplname);
	     		    	hrpay.setEmpMName(empmname);
	     		    	hrpay.setPost(post);
	     		    	hrpay.setPostId(postid);
	     				/*    				
	    		    	temp.add(post);
	     		    	temp.add(empfname);
	     		    	temp.add(empmname);
	     		    	temp.add(emplname);
	     		    	temp.add(postid);*/
	     		    	ohpList.add(hrpay);
	     		    	 
	 		    	 }
	         	}				
					
					
					HrPayOrderHeadMpg hrPayOrderHeadMpg = headMasterDAO.read(orderHeadId);
					long orderId = hrPayOrderHeadMpg.getOrderId();
					List subHeadList = headMasterDAO.getListByColumnAndValue("orderId",orderId);
					long subHeadId = 0;//hrPayOrderHeadMpg.getSubheadId();
					logger.info("setting HrPayOrderHeadMpg.subHeadId as: " +subHeadId);
					SgvaBudsubhdMst sgvasubmst = new SgvaBudsubhdMst();
				
					OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
					List<OrgPostDetailsRlt> postList = orgPostDetailsRltDao.list();
					EmpDAO empDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
					List<OrgEmpMst> empList = empDao.list();  
					//Added By Mrugesh for financial year issue
		  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
		  	        Date currDate = Calendar.getInstance().getTime();
		  	        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		  	        SimpleDateFormat sdf = sbConf.GetDateFormat();
		  	        		  	        String dt = sdf.format(currDate);
		  	        int finYrId = finYearDAOImpl.getFinYearId(dt);
		  	        //Ended By Mrugesh
					//List datalist=orderHeadPostMpgDAO.getorderheadpostmpg(OhpMapId);
					
		  	        //List HeadList =  orderHeadPostMpgDAO.getheadsfromorders(orderId,finYrId); OLD
		  	        
		  	        //varun sharma
		  	        List headList =  orderHeadPostMpgDAO.getHeadsFromOrders(orderId,finYrId);
					
		  	        
		  	        //if(datalist.size()>0)
					//hrPayOrderpostHeadMpg=(HrPayOrderHeadPostMpg)datalist.get(0);
				//	logger.info("datalist size"+datalist.size());
					
					/*for (Iterator iter = HeadList.iterator(); iter.hasNext();)
			         {			 
						 SgvaBudsubhdMst sgvaBudsubhdMst = new SgvaBudsubhdMst();
						 sgvaBudsubhdMst=(SgvaBudsubhdMst) iter.next();
						 long headsId = sgvaBudsubhdMst.getBudsubhdId();
						 String headName = (String) sgvaBudsubhdMst.getBudsubhdDescLong();
						 logger.info("Heads id " + headsId);
						 logger.info("Heads id " + headName);
			         }
					  
					*/
					Map map = new HashMap();
            	    
            	    map=objectArgs;
            	    
              	 /* objectArgs.put("headName",headName);  
            	  objectArgs.put("headsId",headsId);*/
            	  objectArgs.put("HeadList", headList);
            	  objectArgs.put("desigId", desigId);
            	  objectArgs.put("orderId", orderId);
            	  objectArgs.put("subHeadId", subHeadId);
            	  objectArgs.put("PostId", PostId);
            	  
            	  //added by Ankit Bhatt for getting Bill No in Edit Mode
            	  HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
 				 PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
 				 String[] cols = new String[]{"postId","loc_id"};
 				 Object[] vals = new Object[]{PostId,locId};
 				 List hrPayPsrPostMpgList = psrPostMpgDAOImpl.getListByColumnAndValue(cols, vals);
 				 long billNo=0;
 				 if(hrPayPsrPostMpgList!=null && hrPayPsrPostMpgList.size()>0)
 				 {
 					hrPayPsrPostMpg = (HrPayPsrPostMpg)hrPayPsrPostMpgList.get(0);
 				    billNo = hrPayPsrPostMpg.getBillNo();
 				 }
            	  //ended
            	  

			    //    map.put("datalist", datalist);
            	 // HrPayOrderHeadPostMpg hrPayOrderpostHeadMpg = new HrPayOrderHeadPostMpg();
            	  //objectArgs.put("temp", temp);
            	  objectArgs.put("orderresultSet", orderresultSet);
      			  objectArgs.put("headresultSet", headresultSet);
      			  objectArgs.put("userpostrltSet", userpostrltSet);
      			  objectArgs.put("ohpList", ohpList);
      			  objectArgs.put("OhpMapId", OhpMapId);
      			  
      			  //added by Ankit Bhatt
      			     objectArgs.put("billNo", billNo);
      			  //ended
      			  
      			  //added by Ankit Bhatt
      			   objectArgs.put("billList", billList);
      			  //ended
      			
      		    //   objectArgs.put("postresultSet", hrPayOrderpostHeadMpg);
      			  objectArgs.put("orgEmpList", orgEmpList);
      			  objectArgs.put("result", "success");
      			
			        resultObject.setResultCode(ErrorConstants.SUCCESS);
			        resultObject.setResultValue(map);//passing map to result v
			        resultObject.setViewName("EditOrderHeadPostMpg");
					
				}
				//Map map = new HashMap();
				
				
	           else
	            {
                  Map map = new HashMap();
            	  map=objectArgs; 
				HrPayOrderHeadPostMpg hrPayOrderpostHeadMpg = new HrPayOrderHeadPostMpg();
				
				objectArgs.put("orderresultSet", orderresultSet);
				objectArgs.put("headresultSet", headresultSet);
				objectArgs.put("userpostrltSet", userpostrltSet);
				objectArgs.put("postresultSet", hrPayOrderpostHeadMpg);
				objectArgs.put("ohpList", ohpList);
				objectArgs.put("orgEmpList", orgEmpList);
				objectArgs.put("result", "success");
				objectArgs.put("deptList", deptList);
	            resultObject.setResultValue(objectArgs);
	            
//	          added by Ankit Bhatt
   			   objectArgs.put("billList", billList);
   			  //ended
   			   
	            //if(!editflag.equals("Y"))
	           	resultObject.setViewName("addOrderHeadPostMpg");
	            }

		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
	
		return resultObject;
	
	}
	
	
	
	public ResultObject insertOrderHeadPostMasterDtls(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
       // ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
        
		try{
			
			OrderHeadPostmpgDAOImpl orderheadpostmstDAOImpl = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());//object of DAOIMPL
			logger.info("Inside Insert Master Details Service");
			logger.info("Edit Mode From VOGEN "+objectArgs.get("updateflag"));
			//HrPayOrderHeadMpg hrPayOrderHead =(HrPayOrderHeadMpg)objectArgs.get("hrPayOrderHead");
			long orderId=Long.parseLong(StringUtility.getParameter("order", request));
			long headId =Long.parseLong(StringUtility.getParameter("head", request));
			logger.info("orderId"+orderId);
			logger.info("headId"+headId);
			long orderheadid=0;
//			Added By Mrugesh for financial year issue
  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
  	        Date currDate = Calendar.getInstance().getTime();
  	        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	        SimpleDateFormat sdf = sbConf.GetDateFormat();
	          	        String dt = sdf.format(currDate);
  	        int finYrId = finYearDAOImpl.getFinYearId(dt);
  	        //Ended By Mrugesh
			//if(orderheadpostmstDAOImpl.getorderHeadId(orderId, headId,finYrId)!=null)
			//	orderheadid=Long.parseLong(orderheadpostmstDAOImpl.getorderHeadId(orderId, headId,finYrId).get(0).toString());
			
			List orderHeadIdList  = orderheadpostmstDAOImpl.getorderHeadId(orderId, headId,finYrId);
    		if(orderHeadIdList!=null && orderHeadIdList.size()> 0 )
    		{
		    Object[] row = (Object[])orderHeadIdList.get(0);
    		orderheadid=Long.parseLong(row[0].toString());
    		}
			logger.info( "Order head ID is "+orderheadid);
			//logger.info( " head ID is " + hrPayOrderHead.getSubheadId());
			
			 HrPayOrderHeadPostMpg hrPayOrderHeadPost = (HrPayOrderHeadPostMpg)objectArgs.get("hrPayOrderHeadPostMpg");//object of VO
		//	logger.info( "Order head ID is " + hrPayOrderHeadPost.ggetOrderHeadId());
			//logger.info( "Post ID is " + hrPayOrderHeadPost.getPostId());
			//
		
			//orderheadpostmstDAOImpl.getorderHeadId();
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		//	long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());//For Language independent
			
			String editFromVO = objectArgs.get("updateflag").toString();//Flag for edit mode yes or no
		
            
            long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
		/*	long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);*/
			
		/*	long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        logger.info("insertBankMasterDtls Loc Id is:-->" + dbId + " " + locId);*/
	       
	     /*   langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);*/
			
			
			 long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			 
		
     		    	 
			// OrderHeadPostmpgDAOImpl orderheadpostdao =new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
			
			 
		//	 long orderHeadId= Long.parseLong(orderheadpostdao.getorderHeadId().toString());
			// long orderHeadId = Long.parseLong(orderheadpostdao.getorderHeadId().toString());
		//	// logger.info("+++++ orderHeadId+++++++++++++++++"+orderheadid);
			 			 
			 
	// long orderHeadId= Long.parseLong(orderheadpostdao.getOrderHeadId(order, head).toString());
			//	OrderHeadPostmpgDAOImpl orderheadpostdao =new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
			  //  HrPayOrderHeadMpg hrpayorderheadmpg = new HrPayOrderHeadMpg();
				
			 Date sysdate = new Date();
            
        if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
			{
        	
        		long orderheadpostId =Long.parseLong(objectArgs.get("ohpMapId").toString());
            
        		logger.info("The Order Head post Id  inside Update Mode is------>"+orderheadpostId);
            	
       		    HrPayOrderHeadPostMpg orderheadpostmpgVO = orderheadpostmstDAOImpl.read(orderheadpostId);//Reading orderheadpostid from Vo
				logger.info("INSIDE UPDATE OrderheadId"+hrPayOrderHeadPost.getOrderHeadId());
				logger.info("INSIDE UPDATE postId"+hrPayOrderHeadPost.getPostId());
					
				
				orderheadpostmpgVO.setOrderHeadId(hrPayOrderHeadPost.getOrderHeadId());//Setting Order head id in vo
				orderheadpostmpgVO.setPostId(hrPayOrderHeadPost.getPostId());//setting post Id in vo
				orderheadpostmpgVO.setUpdatedDate(sysdate);
				orderheadpostmpgVO.setOrgUserMstByUpdatedBy(orgUserMst);
				orderheadpostmpgVO.setOrgPostMstByUpdatedByPost(orgPostMst);
					 
				orderheadpostmstDAOImpl.update(orderheadpostmpgVO);//update Vo...VO ready to update
				
				 //added by Ankit Bhatt for getting Bill No
				 long billNo = Long.parseLong(objectArgs.get("billNo").toString());
				 logger.info("Bill No is:->"+billNo);
				 long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());				 
				 logger.info("locationId is:->"+locId);	
				 HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				 PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
				 String[] cols = new String[]{"postId","loc_id"};
				 Object[] vals = new Object[]{hrPayOrderHeadPost.getPostId(),locId};
				  List hrPayPsrPostMpgList = psrPostMpgDAOImpl.getListByColumnAndValue(cols, vals);
				 if(hrPayPsrPostMpgList!=null && hrPayPsrPostMpgList.size()>0)
					 hrPayPsrPostMpg = (HrPayPsrPostMpg)hrPayPsrPostMpgList.get(0);
				 
				 logger.info("Post Id and Location Id after reading Post_Psr table in OHP " + hrPayPsrPostMpg.getPostId() + " " + hrPayPsrPostMpg.getPsrId());
				 
				 if(hrPayPsrPostMpg!=null && hrPayPsrPostMpg.getPsrId()!=0 && hrPayPsrPostMpg.getPsrId()!=0)
				 {
					 hrPayPsrPostMpg.setBillNo(billNo);
					 psrPostMpgDAOImpl.update(hrPayPsrPostMpg);
					 
				 }
				 
				  
				 //ended by Ankit Bhatt
				 
				msg=1;//for display message for update
			
        		logger.info("Updated successfully................");
			}
            
            
			else //if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
			{
				
				
				 long order= Long.parseLong(objectArgs.get("order").toString());
				// logger.info("-------orderID--------"+order);
				 long head= Long.parseLong(objectArgs.get("head").toString());
				// logger.info("-------headID--------"+order);
				 
				 long headPostId = Long.parseLong(objectArgs.get("post").toString());
				// logger.info("-------empId--------"+empId);
				 
				 //added by Ankit Bhatt for getting Bill No
				 long billNo = Long.parseLong(objectArgs.get("billNo").toString());
				 
				 long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				 CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());				 
					
				 HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				 PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
				 String[] cols = new String[]{"postId","loc_id"};
				 Object[] vals = new Object[]{headPostId,locId};
				 List hrPayPsrPostMpgList = psrPostMpgDAOImpl.getListByColumnAndValue(cols, vals);
				 if(hrPayPsrPostMpgList!=null && hrPayPsrPostMpgList.size()>0)
					 hrPayPsrPostMpg = (HrPayPsrPostMpg)hrPayPsrPostMpgList.get(0);
				 
				 logger.info("Post Id and Location Id after reading Post_Psr table in OHP " + hrPayPsrPostMpg.getPostId() + " " + hrPayPsrPostMpg.getPsrId());
				 
				 if(hrPayPsrPostMpg!=null && hrPayPsrPostMpg.getPsrId()!=0 && hrPayPsrPostMpg.getPsrId()!=0)
				 {
					 hrPayPsrPostMpg.setBillNo(billNo);
					 psrPostMpgDAOImpl.update(hrPayPsrPostMpg);
					 
				 }
				 
				  
				 //ended by Ankit Bhatt
				 List postIdList = orderheadpostmstDAOImpl.getPostIdByEmpId(headPostId);
				 
				 Object[] row;
				 long post=0;
	         	if(postIdList.size() >0 && postIdList!=null)  {                		
	 		    	 for(int k=0;k<postIdList.size();k++) {
	     		    	 //logger.info("orderheadpost id is " + resultSet.get(0)); 		    		 
	     		    	 row = (Object[])postIdList.get(k);
	     		    	 post = Long.parseLong(row[1].toString());
	     		    	 logger.info("Post ID in Newly Added Loop " + post);
	 		    	 }
	         	}
				logger.info("INSIDE Insert Order Details");
				IdGenerator idGen = new IdGenerator();
				long orderHeadPostId = idGen.PKGenerator("hr_pay_order_head_post_mpg", objectArgs);
				HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg = new HrPayOrderHeadPostMpg();
				
				hrPayOrderHeadPostMpg.setOrderHeadPostId(orderHeadPostId);
				hrPayOrderHeadPostMpg.setOrderHeadId(orderheadid);
				hrPayOrderHeadPostMpg.setPostId(headPostId);
				hrPayOrderHeadPostMpg.setTrnCounter(new Integer(1));
				hrPayOrderHeadPostMpg.setOrgPostMstByCreatedByPost(orgPostMst);
				hrPayOrderHeadPostMpg.setOrgUserMstByCreatedBy(orgUserMst);
				hrPayOrderHeadPostMpg.setCreatedDate(sysdate);	
				orderheadpostmstDAOImpl.create(hrPayOrderHeadPostMpg);//ready to insert 
				
				
			
				logger.info("Inserted Successfully");
			}
			//Map redirectMap = new HashMap();
  			//redirectMap.put("actionFlag", "getOrderHeadPostData");
  			if(msg==1)
  				objectArgs.put("MESSAGECODE",300006);
  			else if(msg==-1)
  				objectArgs.put("MESSAGECODE",300007);
  			else  				
  				objectArgs.put("MESSAGECODE",300005);
  			//objectArgs.put("redirectMap", redirectMap);					
  			resultObject.setResultCode(ErrorConstants.SUCCESS);
  			
          			resultObject.setResultValue(objectArgs);
          			//resultObject.setViewName("redirect view");
          			resultObject.setViewName("OrderHeadPostmpgview");
          			
  		
          		if(msg==1)
          				resultObject.setViewName("EditOrderHeadPostMpg");
          		else
          				resultObject.setViewName("addOrderHeadPostMpg");
    			//objectArgs.put("MESSAGECODE",300005);
    					
    			//resultObject.setResultCode(ErrorConstants.SUCCESS);
    		
    			//resultObject.setResultValue(objectArgs);
    		    
    		//	if(msg==1)
    			//resultObject.setViewName("OrderHeadPostmpgview");//For Redirect from message to view jsp
    	      //  else
    		  //  resultObject.setViewName("addOrderHeadPostMpg");//For Redirect from message to view jsp
    		
             //   logger.info("Inserted Sucssesfully and End of Insert/Update Method");
    		
    	    }
		
		
    	catch(NullPointerException ne)
    	    {
    		logger.info("Null Pointer Exception Ocuures...insertorderData");
    		ne.printStackTrace();
    		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    		resultObject.setResultValue(objectArgs);
    		resultObject.setViewName("errorInsert");			
    	}
    	
    	catch(Exception e){
    		
    		logger.info("Exception Ocuures...insertOuterData");
    		 e.printStackTrace();
    		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    		 resultObject.setResultValue(objectArgs);
    		 resultObject.setViewName("errorInsert");
    	}
    	return resultObject;
    	}
	
	public ResultObject checkOrderheadpostAvailability(Map objectArgs){
	 	
		
		logger.info("Inside AJAX Service");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
        StringBuffer orderheadpostBf=new StringBuffer();
        String check; 
		//long langId=Long.parseLong(loginDetailsMap.get("langId").toString());//For Language Independent
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			OrderHeadPostmpgDAOImpl  orderheadpostDAO = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
           long neworderHeadId = Long.parseLong(voToService.get("neworderHeadId").toString()); 
           long newpostId = Long.parseLong(voToService.get("newpostId").toString());
            logger.info("Before The Changed OrderName is:-"+neworderHeadId);
            logger.info("Before The Changed OrderName is:-"+newpostId);
           // newOrderName=newOrderName.toLowerCase();
            //logger.info("After The Changed OrderName is:-"+newOrderName);
            List <HrPayOrderHeadPostMpg> actionList = orderheadpostDAO.checkOrderheadpostAvailability(neworderHeadId, newpostId);
            
            logger.info("The Size of List is:-"+actionList.size());
            
            if(actionList.size()==0){
            	check="false";
            	orderheadpostBf.append("<orderNameMapping>");
            	orderheadpostBf.append("<availability>").append(check).append("</availability>");
            	orderheadpostBf.append("</orderNameMapping>");            	
            }
            else {
            	check="true";
            	orderheadpostBf.append("<orderNameMapping>");
            	orderheadpostBf.append("<availability>").append(check).append("</availability>");
            	orderheadpostBf.append("</orderNameMapping>");
            }           
            String orderNameMapping = new AjaxXmlBuilder().addItem("ajax_key", orderheadpostBf.toString()).toString();
	         
            logger.info(" the string buffer is :"+orderNameMapping);
            objectArgs.put("ajaxKey", orderNameMapping);
	        resultObject.setResultCode(ErrorConstants.SUCCESS); 
	        resultObject.setResultValue(objectArgs);
	        resultObject.setViewName("ajaxData");
		}
		catch(Exception e){
			logger.error("Exception while generating VO to XML ", e);
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			retObj.setViewName("ajaxError");
			objectArgs.put("ajaxKey", "ERROR");
			retObj.setResultValue(objectArgs);
			retObj.setThrowable(e);
			return retObj;

		}
		return resultObject;
	}
	
	

	public ResultObject Getheads(Map objectArgs)
	{
		logger.info("*********Getheads*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();		
	    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		OrderHeadMpgDAOImpl orderheaddao = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
		long orderId = 0;
		
			Map voToService = (Map)objectArgs.get("voToServiceMap");
		 	orderId =Long.parseLong((String) voToService.get("orderId"));
				logger.info("orderId in Service:--->" + orderId);
//				Added By Mrugesh for financial year issue
	  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
	  	        Date currDate = Calendar.getInstance().getTime();
	  	        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	  	        SimpleDateFormat sdf = sbConf.GetDateFormat();
	  	        	  	        String dt = sdf.format(currDate);
	  	        int finYrId = finYearDAOImpl.getFinYearId(dt);
	  	        //Ended By Mrugesh
      //  Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
				OrderHeadPostmpgDAOImpl orderheadpostmpgdao = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
				List <HrPayOrderHeadPostMpg> headList = (List)orderheadpostmpgdao.getheadsfromorders(orderId,finYrId);
				//List<HrPayOrderHeadMpg> odMpgList = new ArrayList();
				/*for (int i=0;i<headList.size();i++)
				{
					HrPayOrderHeadPostMpg hrpayohpMpg=new HrPayOrderHeadPostMpg();
					hrpayohpMpg=(HrPayOrderHeadPostMpg)headList.get(i);
					
					logger.info("----------------headlist size is ----------------"+headList.get(i));
       	 //   HrPayOrderHeadMpg hrpayohpMpg1=new HrPayOrderHeadMpg();
        	//gdMpgList.add(resultSet.get(i));
        	//hrpayohpMpg1.setOrderHeadId(hrpayohpMpg.getOrderHeadId());
			//boolean flag=false;
			
    		
        	
        	//	String headElementCode= hrpayohpMpg1.getOrgDesignationMst().getDsgnCode();
				//String headElementCode= hrpayohpMpg1.getSubheadId().toString();         
				
    		//	List<OrgDesignationMst> desig = desigMasterDAO.getDesigName(gradeElementCode, LangId);
    		//	if(desig.size()>0)
    		//	{
    				
    		//		HrEisGdMpggu.setOrgDesignationMst(desig.get(0));
    		//		flag = true;
    		//	}
    		   			
    	
    	//	else
    		//{
		//	hrpayohpMpg.setOrderHeadId((hrpayohpMpg1.getSubheadId()));
			//	flag = true;
			//}
    		//if(flag)
    			//odMpgList.add(hrpayohpMpg1);			
        				
        }*/
	    
	    StringBuffer propertyList = new StringBuffer();
		 for (Iterator iter = headList.iterator(); iter.hasNext();)
         {			 
			 SgvaBudsubhdMst sgvaBudsubhdMst = new SgvaBudsubhdMst();
			 sgvaBudsubhdMst=(SgvaBudsubhdMst) iter.next();
		  long headsId = sgvaBudsubhdMst.getBudsubhdId();
		  //Added by Paurav for showing Budget Structure also
		  /*logger.info("We are showing Head Structure instead of Head Name in The combo Box.");
		  StringBuffer headStructBuffer = new StringBuffer();
		  
		  headStructBuffer.append(sgvaBudsubhdMst.getDemandCode()).append("-");
		  headStructBuffer.append(sgvaBudsubhdMst.getBudmjrhdCode()).append("-");
		  headStructBuffer.append(sgvaBudsubhdMst.getBudsubmjrhdCode()).append("-");
		  headStructBuffer.append(sgvaBudsubhdMst.getBudminhdCode()).append("-");
		  headStructBuffer.append(sgvaBudsubhdMst.getBudsubhdCode());
		  String headName = headStructBuffer.toString();
		  
		  logger.info("Head Structure Constructed");*/
		  //Ended By Paurav
		  
		  String headName = (String) sgvaBudsubhdMst.getBudsubhdDescLong();
   		  propertyList.append("<heads-mapping>");   	
          propertyList.append("<heads-Id>").append(headsId).append("</heads-Id>");
          propertyList.append("<head-name>").append("<![CDATA[").append(headName).append("]]>").append("</head-name>");               
          propertyList.append("</heads-mapping>");
         }
   	     Map result = new HashMap();
         String headData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         logger.info("The Ajax Key is:-"+headData);
         result.put("ajaxKey", headData);            
         resultObject.setResultValue(result);
         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
//        logger.info("After Service Called.........\n"+headdata);
        return resultObject;
		
	}
	
	
	
	//Added By Varun 
	
	public ResultObject GetHeadStructure(Map objectArgs)
	{
		logger.info("*********GetHeadStructure*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();		
	    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		BillHeadDao billHeadDao = new BillHeadDao(HrPayBillHeadCustomVO.class,serv.getSessionFactory());
		long subHeadId = 0;
		
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			subHeadId =Long.parseLong((String) voToService.get("subHeadId"));
				logger.info("subHeadId in Service:--->" + subHeadId);
				
      //  Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
				List headList = billHeadDao.getHeadStructure(subHeadId);
				
				//List resultSet = billHeadMpgDAO.getAllData(locId);
        		
        		HrPayBillHeadCustomVO bhMpg = new HrPayBillHeadCustomVO();
        		 StringBuffer propertyList = new StringBuffer();
        		Iterator  it = headList.iterator();
        		List HeadList = new ArrayList();
        		
	        	for(int i=0;i<headList.size();i++)
        		 {	    
	        		bhMpg = new HrPayBillHeadCustomVO();
		            Object[] rowList = (Object[]) headList.get(i);
		            
		            
		            String demandId  =rowList[0].toString();
		           // logger.info("+++++ demandId+++++"+demandId);
		            bhMpg.setDemandNo(demandId);
		            
		            String budmjrhd_code = rowList[1].toString();
		           // logger.info("+++++ budmjrhd_code+++++"+budmjrhd_code);
		            bhMpg.setMjrHead(budmjrhd_code);
		            
		            
		            String budsubmjrhd_code = rowList[2].toString();
		           // logger.info("+++++ budsubmjrhd_code+++++"+budsubmjrhd_code);
		            bhMpg.setSubMjrHead(budsubmjrhd_code);
		            
		            
		            String budminhd_code = rowList[3].toString();
		           // logger.info("+++++ budminhd_code+++++"+budminhd_code);
		            bhMpg.setMinorHead(budminhd_code);
		            
		            
		            String subHeadName = rowList[4].toString();
		           // logger.info("+++++ subHeadName+++++"+subHeadName);
		            bhMpg.setSubHeadName(subHeadName);
		            
		             
		            HeadList.add(bhMpg);
		            
		            propertyList.append("<heads-mapping>");   	
		            propertyList.append("<demandId-Id>").append(demandId).append("</demandId-Id>");
		            propertyList.append("<budmjrhd_code-Id>").append(budmjrhd_code).append("</budmjrhd_code-Id>");
		            propertyList.append("<budsubmjrhd_code-Id>").append(budsubmjrhd_code).append("</budsubmjrhd_code-Id>");
		            propertyList.append("<budminhd_code-Id>").append(budminhd_code).append("</budminhd_code-Id>");
		            propertyList.append("<subHeadName-name>").append("<![CDATA[").append(subHeadName).append("]]>").append("</subHeadName-name>");               
		            propertyList.append("</heads-mapping>");
        		 }
				
				
	    
	 
   	     Map result = new HashMap();
         String headData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         logger.info("The Ajax Key is:-"+headData);
         result.put("ajaxKey", headData);            
         resultObject.setResultValue(result);
         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
//        logger.info("After Service Called.........\n"+headdata);
        return resultObject;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Ended By Varun
	
	public ResultObject GetPostfromDesg(Map objectArgs)
	{
		logger.info("*********GetPostfromDesg*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();		
	    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		
	    Map voToService = (Map)objectArgs.get("voToServiceMap");
	    long dsgnId=0;    
	    dsgnId=Long.parseLong((String) voToService.get("dsgnId"));
	    
	    
	    String editFlag=(String) voToService.get("editFlag");
        
	    Long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	    
	    List userpostrltSet = new ArrayList();
	    OrderHeadPostmpgDAOImpl orderHeadPostMpgDAO = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
	    long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
	    long loc_id =Long.parseLong(orderHeadPostMpgDAO.getLocationCode(userId,langId).get(0).toString());
	    OrderHeadMpgDAOImpl headMasterDAO = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
		//changed by manoj
	    
	   /* if(editFlag!=null && editFlag.equals("0"))
	    {*/
	    	userpostrltSet=orderHeadPostMpgDAO.getAllUserPostRltDatabyDesg(loc_id,dsgnId);
	    /*}
	    else
	    {
	    	userpostrltSet=orderHeadPostMpgDAO.getAllUserPostRltDatabyDesgEdit(loc_id,dsgnId);
	    }*/
	    
	    
	    //end by manoj
		 Object[] row;
		 String post="";
		 String empfname="";
		 String empmname="";
		 String emplname="";
		 long postid=0;
		 StringBuffer propertyList = new StringBuffer();
		 logger.info("userpostrltSet.size() "+userpostrltSet.size());
		 
		 if(userpostrltSet!=null &&  userpostrltSet.size() >0 )
		 {      
			 logger.info("in if block userpostrltSet.size() "+userpostrltSet.size());
			 
		    	for(int k=0;k<userpostrltSet.size();k++) 
			    {
		    		  row = (Object[])userpostrltSet.get(k);
		    		  
		    		  
		    		  if(row[0]!=null)
		    		  {
		    			  logger.info("row[0].toString() "+row[0].toString());
		    			  postid = Long.parseLong(row[0].toString());
		    			  logger.info("postid "+postid);
		    		  }
		    		
	 		    	  //empmname = (row[3].toString());
	 		    	 // emplname = (row[4].toString());
	 		    	  if(row[2]==null || row[2].toString().equals("") ||  row[2].toString().equals(" ") )
	 		    	  {
	 		    		 empfname="Vacant";
	 		    	  }
	 		    	  else
	 		    	  {
	 		    		 empfname=  row[2].toString();
	 		    	  }
	 		    		logger.info("empfname "+empfname);  
	 		    	  if(row[1]!=null || !row[1].toString().equals("") ||  !row[1].toString().equals(" "))
	 		    		  post = empfname+" ( "+row[1].toString()+" )";
	 		    	  //end by manoj
			   		  propertyList.append("<post-mapping>");   	
			          propertyList.append("<postId>").append(postid).append("</postId>");
			          propertyList.append("<post>").append("<![CDATA[").append(post).append("]]>").append("</post>");               
			          propertyList.append("</post-mapping>");
			         		    	
			
			    	}
		 }
		
		 
   	     Map result = new HashMap();
         String postData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         result.put("ajaxKey", postData);
            
         resultObject.setResultValue(result);
         
         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
        logger.info("After Service Called.........\n"+postData);
        return resultObject;
		
	}

	public ResultObject chkorderheadPostMpg(Map objectArgs)
	{
		logger.info("*********chkorderheadPostMpg*********");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();		
	    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		
	    Map voToService = (Map)objectArgs.get("voToServiceMap");
	    long orderId=0;    
	    long headId=0;    
	    long postId=0;    
	    long mpgId=0;    
	    orderId=Long.parseLong((String) voToService.get("order"));
	    headId=Long.parseLong((String) voToService.get("head"));
	    postId=Long.parseLong((String) voToService.get("post"));
	    mpgId=Long.parseLong((String) voToService.get("mpgId"));
		
	     List userpostrltSet = new ArrayList();
	     OrderHeadPostmpgDAOImpl orderHeadPostMpgDAO = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
	     OrderHeadPostmpgDAOImpl orderHeadPostmpgDAOImpl = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
		 boolean mpgflag=false;
		 mpgflag=orderHeadPostmpgDAOImpl.chkorderheadPostMpg(orderId,headId,postId,mpgId); 
		 StringBuffer propertyList = new StringBuffer();   				 
		   		  
		          propertyList.append("<mpgflag>"); 
		   		  if(mpgflag)
		          propertyList.append("<flag>").append("true").append("</flag>");
		   		  else
			      propertyList.append("<flag>").append("false").append("</flag>");
		   		  propertyList.append("</mpgflag>");   	
		 
   	     Map result = new HashMap();
         String mpgData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         result.put("ajaxKey", mpgData);
            
         resultObject.setResultValue(result);
         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
        logger.info("After Service Called.........\n"+mpgData);
        return resultObject;
		
	}
	
	
	public ResultObject multipleAddOrderHeadPostData(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
       // ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
        
		try{
			
			OrderHeadPostmpgDAOImpl orderheadpostmstDAOImpl = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());//object of DAOIMPL
			logger.info("Edit Mode From VOGEN "+objectArgs.get("updateflag"));
			//HrPayOrderHeadMpg hrPayOrderHead =(HrPayOrderHeadMpg)objectArgs.get("hrPayOrderHead");
			long orderId=Long.parseLong(StringUtility.getParameter("order", request));
			long headId =Long.parseLong(StringUtility.getParameter("head", request));
			logger.info("orderId"+orderId);
			logger.info("headId"+headId);
			long orderheadid=0;
//			Added By Mrugesh for financial year issue
  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
  	        Date currDate = Calendar.getInstance().getTime();
  	        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	        SimpleDateFormat sdf = sbConf.GetDateFormat();
	          	        String dt = sdf.format(currDate);
  	        int finYrId = finYearDAOImpl.getFinYearId(dt);
  	        //Ended By Mrugesh
			//if(orderheadpostmstDAOImpl.getorderHeadId(orderId, headId,finYrId)!=null)
			//	orderheadid=Long.parseLong(orderheadpostmstDAOImpl.getorderHeadId(orderId, headId,finYrId).get(0).toString());
			
			List orderHeadIdList  = orderheadpostmstDAOImpl.getorderHeadId(orderId, headId,finYrId);
    		if(orderHeadIdList!=null && orderHeadIdList.size()> 0 )
    		{
    		    Object[] row = (Object[])orderHeadIdList.get(0);
        		orderheadid=Long.parseLong(row[0].toString());
    		}
    					
			logger.info( "Order head ID is "+orderheadid);
			
			
			 HrPayOrderHeadPostMpg hrPayOrderHeadPost = (HrPayOrderHeadPostMpg)objectArgs.get("hrPayOrderHeadPostMpg");//object of VO
		
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
			
            long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			 long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			
			 Date sysdate = new Date();
			 int counter = Integer.parseInt(((StringUtility.getParameter("postLenVal",request)!=null&&!(StringUtility.getParameter("postLenVal",request).equals(""))?(StringUtility.getParameter("postLenVal",request)):0).toString()));
			 logger.info("The value of counter is------>"+counter);
			 long headPostId = 0;
            
				/* long order= Long.parseLong(objectArgs.get("order").toString());
				// logger.info("-------orderID--------"+order);
				 long head= Long.parseLong(objectArgs.get("head").toString());
				// logger.info("-------headID--------"+order);
*/				String[] postidlist = StringUtility.getParameterValues("post",request);

				IdGenerator idGen = new IdGenerator();

				 //added by Ankit Bhatt for getting Bill No
				 long billNo = Long.parseLong(objectArgs.get("billNo").toString());
				 
				 long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				 CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());				 
					
				 HrPayPsrPostMpg hrPayPsrPostMpg = new HrPayPsrPostMpg();
				 
				 //added by manoj for psr relatd issue for home
				 long psrId=1,psrPostId=0;
				 PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
					
				 List psrList = psrPostMpgDAOImpl.getMaxPsrForLoc(locId);
				 
				 logger.info("psrList be added is "+psrList);
				 
				 if(psrList!=null && psrList.size()>0)
				 {
					 Object[] row = (Object[])psrList.get(0); 
					 
					 logger.info("row added is "+row.length);
					 if(row[0]!=null)
						 psrId = (Long)row[0];
					 
				 }
				 
				 logger.info("psr no to be added is "+psrId);
				 
				 //end added by manoj for psr relatd issue for home
				 HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg = new HrPayOrderHeadPostMpg();
					
				 //ended by Ankit Bhatt
				 String[] cols = new String[]{"postId","loc_id"};
				 List hrPayPsrPostMpgList = null;
				 
			 for(int i=0; i<counter;i++)
			 {
				 hrPayPsrPostMpg = new HrPayPsrPostMpg();
				 
				long orderHeadPostId = idGen.PKGenerator("hr_pay_order_head_post_mpg", objectArgs);
				hrPayOrderHeadPostMpg = new HrPayOrderHeadPostMpg();
				hrPayOrderHeadPostMpg.setOrderHeadPostId(orderHeadPostId);
				hrPayOrderHeadPostMpg.setOrderHeadId(orderheadid);
				hrPayOrderHeadPostMpg.setPostId(Long.parseLong(postidlist[i]));
				hrPayOrderHeadPostMpg.setTrnCounter(new Integer(1));
				hrPayOrderHeadPostMpg.setOrgPostMstByCreatedByPost(orgPostMst);
				hrPayOrderHeadPostMpg.setOrgUserMstByCreatedBy(orgUserMst);
				hrPayOrderHeadPostMpg.setCreatedDate(sysdate);	
				orderheadpostmstDAOImpl.create(hrPayOrderHeadPostMpg);//ready to insert 
				
				//added by Ankit Bhatt
				 Object[] vals = new Object[]{Long.parseLong(postidlist[i]),locId};
				 
				 //changed by manoj for PSR related issue for home
				 hrPayPsrPostMpgList = psrPostMpgDAOImpl.getListByColumnAndValue(cols, vals);
				 if(hrPayPsrPostMpgList!=null && hrPayPsrPostMpgList.size()>0)
				 {
					 hrPayPsrPostMpg = (HrPayPsrPostMpg)hrPayPsrPostMpgList.get(0);
					 hrPayPsrPostMpg.setBillNo(billNo);
					 psrPostMpgDAOImpl.update(hrPayPsrPostMpg);
				 }
				 else
				 {
					 psrPostId = idGen.PKGenerator("hr_pay_post_psr_mpg", objectArgs);
					 
					 hrPayPsrPostMpg.setPsrId(psrId);//psr no
					 hrPayPsrPostMpg.setBillNo(billNo);
					 hrPayPsrPostMpg.setLoc_id(locId);
					 hrPayPsrPostMpg.setPostId(hrPayOrderHeadPostMpg.getPostId());
					 hrPayPsrPostMpg.setPsrPostId(psrPostId);//pk
					 
					 psrPostMpgDAOImpl.create(hrPayPsrPostMpg);
					 psrId++;
				 }
					 
				 
				 logger.info("Post Id and Location Id after reading Post_Psr table in OHP " + hrPayPsrPostMpg.getPostId() + " " + hrPayPsrPostMpg.getPsrId());
				 
				 
				 //end by manoj for PSR related issue for home
					 //ended
				 
				  
			
				logger.info("Inserted Successfully");
			
			 }
  			objectArgs.put("MESSAGECODE",300005);
  			//objectArgs.put("redirectMap", redirectMap);					
  			resultObject.setResultCode(ErrorConstants.SUCCESS);
  			resultObject.setResultValue(objectArgs);
          	//resultObject.setViewName("redirect view");
          	resultObject.setViewName("addOrderHeadPostMpg");
    		}
		
		
    	catch(NullPointerException ne)
    	    {
    		logger.info("Null Pointer Exception Ocuures...multipleAddOrderHeadPostData");
    		ne.printStackTrace();
    		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    		resultObject.setResultValue(objectArgs);
    		resultObject.setViewName("errorInsert");			
    	}
    	
    	catch(Exception e){
    		
    		logger.info("Exception Ocuures...multipleAddOrderHeadPostData");
    		 e.printStackTrace();
    		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
    		 resultObject.setResultValue(objectArgs);
    		 resultObject.setViewName("errorInsert");
    	}
    	return resultObject;
    	}
	
	//added by Ankit Bhatt for Search in OHP Screen
	public ResultObject searchOrderHeadPostData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			logger.info("*********searchOrderHeadPostData*********");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			    OrderHeadPostmpgDAOImpl orderheadpostmpg = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
	    	    HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	            HttpSession session=request.getSession();		
	            Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	            long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
	            long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
	            Map voToService = (Map)objectArgs.get("voToServiceMap");
	            String orderName = "";
	            String postName = "";
	            if(voToService.get("order_srchText_")!=null)
	              orderName = voToService.get("order_srchText_").toString();
	            if(voToService.get("post_srchText_")!=null)
	            	postName = voToService.get("post_srchText_").toString();
	            logger.info("Order and Post name is " + orderName + " and " + postName);
	            
	  	        FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
	  	        Date currDate = Calendar.getInstance().getTime();
	  	        DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	  	        SimpleDateFormat sdf = sbConf.GetDateFormat();
	  	        	  	        String dt = sdf.format(currDate);
	  	        int finYrId = finYearDAOImpl.getFinYearId(dt);
	  	        
	            List searchResult = orderheadpostmpg.searchOrderPostData(orderName, postName, locId, finYrId );
	            
	            HrPayBillHeadMpg billHeadMpg = new HrPayBillHeadMpg();
	            BillHeadMpgDAOImpl billHeadMpgDAOImpl = null;//new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class,serv.getSessionFactory());
	           List<HrPayBillHeadMpg> hrPayBillHeadMpgList = billHeadMpgDAOImpl.getAllData();
	           /*if(hrPayBillHeadMpgList!=null && hrPayBillHeadMpgList.size() >0)
	           {
	        	   billHeadMpg =hrPayBillHeadMpgList.get(0);
	        	   billId = billHeadMpg.getBillId();
	        	   logger.info("Bill Id is:-->"+billId);
	           }*/
	            objectArgs.put("billHeadMpg", hrPayBillHeadMpgList);
//	          added by Ankit Bhatt for Displaying View Page with Post Search
	            String postSearchFlag = "N";
	           
	            objectArgs.put("postSearchFlag","true");
	            
	            //ended
				 long postid=0;
				// ArrayList temp = new ArrayList();
				 //List ohpList = new ArrayList();
	            long ohpid=0;
	            List dataList = new ArrayList();
	            HrPayCustOrderHeadPost hrPayCustOrderHeadPost=new HrPayCustOrderHeadPost();
               // for (int i=0;i<resultSet.size();i++)
                	
                	logger.info("----------------listsize is -----------------------"+searchResult.size());
                	Object[] row;
                	if(searchResult.size() >0 && searchResult!=null)  {                		
        		    	 for(int k=0;k<searchResult.size();k++) {
	        		    	 //logger.info("orderheadpost id is " + resultSet.get(0));
        		    		 hrPayCustOrderHeadPost=new HrPayCustOrderHeadPost();
	        		    	 row = (Object[])searchResult.get(k);
	        		    	 ohpid   = Long.parseLong(row[0].toString());
	        		    //	 logger.info("orderheadpost id is  " + ohpid); 
	        		    	 hrPayCustOrderHeadPost.setOrderHeadPostId(ohpid);
	        		    	 
	        		    //	 logger.info("ordername  is " + resultSet.get(1));
	        		    	 //Object[] row1 = (Object[])resultSet.get(k);
	        		    	 orderName   = (row[1].toString());
	        		    	 
	        		    	// logger.info("ordername id is  " + orderName); 
	        		    	 hrPayCustOrderHeadPost.setOrderName(orderName);
	        		    	 
	        		    	// logger.info("setSubHeadDesc  is " + resultSet.get(2));
	        		    	// Object[] row2 = (Object[])resultSet.get(k);
	        		    	 String subHeadDesc   = (row[2].toString());
	        		    //	 logger.info("setSubHeadDesc  is  " + subHeadDesc); 
	        		    	 hrPayCustOrderHeadPost.setSubHeadDesc(subHeadDesc);
	        		    	 
	        		    	 
	        		    //	 logger.info("post  is " + resultSet.get(3));
	        		    	// Object[] row3 = (Object[])resultSet.get(k);
	        		    	 String post   = (row[3].toString());
	        		    	 hrPayCustOrderHeadPost.setPost(post);
	        		    	 
	        		    //	 Object[] row4 = (Object [])resultSet.get(k);
	        		    	 
	        		    	 
	        		    	 //changed by manoj for vacant post issue
	        		    	 String empfname = "";
	        		    	 if(row[4]!=null && !row[4].toString().equals("") && !row[4].toString().equals(" "))
	        		    	 {
	        		    	   	 empfname = (row[4].toString());
	        		    	 }
	        		    	 else
	        		    	 {
	        		    	 	 empfname = "Vacant";
	        		    	 }
	        		    	 logger.info(" for testing empfname "+empfname);
	        		    	 hrPayCustOrderHeadPost.setEmpFName(empfname);
	        		    	 
	        		    	// Object[] row5 = (Object [])resultSet.get(k);
	        		    	 //String empmname  = (row[5]!=null?row[5].toString():"");
	        		    	 String empmname  = "";
	        	     		 hrPayCustOrderHeadPost.setEmpMName(empmname);
	        	     		 
	        		    	// Object[] row6 = (Object [])resultSet.get(k);
	        		    	// String emplname  = (row[6].toString());
	        	     		 String emplname  = "";
	        		    	 hrPayCustOrderHeadPost.setEmpLName(emplname); 
	        		    	  
	        		    	 
	        		    	 //end by manoj for vacant post issue
	        		    //	 logger.info("Post is  " + post); 
	        		    	 
	        	     		   	     		
	        		  
	        		    	           dataList.add(hrPayCustOrderHeadPost);
        		    	 
        			    }	 
        		     }
        		    	 else{
        		    		 logger.info("sevice is executed");
        		    	 }
	           
                	objectArgs.put("actionList", dataList);
	            resultObject.setResultValue(objectArgs);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setViewName("OrderHeadPostmpgview");	            	            
		}
		catch(Exception e)
		{
			logger.info("Exception Ocuures...searchOrderHeadPostData");
   		 e.printStackTrace();
   		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
   		 resultObject.setResultValue(objectArgs);
   		 resultObject.setViewName("errorInsert");
		}
		return resultObject;
	}
	
	//ended
	
	
	
	
//	Added By Varun
		
		public ResultObject GetPostfromBill(Map objectArgs)
		{
			logger.info("*********GetPostfromBill*********");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			HttpSession session=request.getSession();		
		    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
		    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			
			
		    Map voToService = (Map)objectArgs.get("voToServiceMap");
		    long billId=0;
		    String postName="";
		    long ohpID=0;
		    billId=Long.parseLong((String) voToService.get("billId"));
		    List postListFromBill = new ArrayList();
		    OrderHeadPostmpgDAOImpl orderHeadPostmpgDAOImpl = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
		    
			
		    
		    postListFromBill=orderHeadPostmpgDAOImpl.getPostFromBill(billId);
		   
		    	
	    		
	    		
	    		Iterator  it = postListFromBill.iterator();
	    		 StringBuffer propertyList = new StringBuffer();
	    		
	        	for(int i=0;i<postListFromBill.size();i++)
	    		 {	    
	        		
		            Object[] rowList = (Object[]) postListFromBill.get(i);
		            postName = "(" + Long.parseLong(rowList[1].toString()) + ")" + rowList[0].toString();
		           // logger.info("Post Name is"+postName);
		            ohpID = Long.parseLong(rowList[2].toString());
		            
		            
		            String post = rowList[1].toString();
		            
		            

			   		  propertyList.append("<post-mapping>");   	
			          propertyList.append("<postId>").append(ohpID).append("</postId>");
			          propertyList.append("<post>").append("<![CDATA[").append(postName).append("]]>").append("</post>");               
			          propertyList.append("</post-mapping>");
		                
	    		 }
		            
				         		    	
				
				    	
			 
			
			 
	   	     Map result = new HashMap();
	         String postData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
	         result.put("ajaxKey", postData);
	            
	         resultObject.setResultValue(result);
	         
	         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
	        logger.info("After Service Called.........\n"+postData);
	        return resultObject;
			
		}
		//Ended By Varun Dt.05-08-2008
}
	
	
	
	
	
	
	
	
	
	
