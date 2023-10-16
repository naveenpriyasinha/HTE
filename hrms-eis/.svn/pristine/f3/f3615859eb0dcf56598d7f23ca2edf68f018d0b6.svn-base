package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankDetailDAO;
import com.tcs.sgv.eis.dao.BankDetailDAOImpl;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BranchMasterDAO;
import com.tcs.sgv.eis.dao.BranchMasterDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.util.BankDetailsServiceImplHelper;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class BankDetailsServiceImpl extends ServiceImpl {
	static private final Log logger = LogFactory.getLog(BankDetailsServiceImpl.class );//Changed By Maruthi.
	ServiceLocator serviceLocator=null;
	int msg=0;
	
	public BankDetailsServiceImpl() { }
	
	public BankDetailsServiceImpl(ServiceLocator serv) {
		this.serviceLocator=serv;
	} 
	public ResultObject insertBankDtlsData(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			
			//Reading values//
			logger.info("Coming into the Service method insertBankMasterDtls");
			HrEisBankDtls bankDtls = (HrEisBankDtls)objectArgs.get("bankDtls");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			logger.info( "The BankDetail VO is " + bankDtls);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			 String editFromVO = objectArgs.get("edit").toString();
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			logger.info("insertBankMasterDtls Loc Id is:-->" + dbId);		       		        				
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			
			logger.info("BankDetailsServiceImpl userIduserIduserId" + userId);
			logger.info("BankDetailsServiceImpl dbIddbIddbIddbIddbId" + dbId);
			logger.info("BankDetailsServiceImpl langIdlangIdlangIdlangId" + langId);
			logger.info("BankDetailsServiceImpl locIdlocIdlocIdlocId" + locId);
			logger.info("BankDetailsServiceImpl postIdpostIdpostIdpostId" + postId);
			
			
			//added by ravysh
			String FromBasicDtlsNew =objectArgs.get("FromBasicDtlsNew")!=null?(String)objectArgs.get("FromBasicDtlsNew"):"";

			
			//DAO Starts//
			BankDetailDAOImpl bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
			//Added by mrugesh
    		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
    		//Ended by mrugesh
    		
           //Business Logic Starts//
            BankDetailsServiceImplHelper helper = new BankDetailsServiceImplHelper(serv);
            logger.info("editFromVO insertBankDtlsData" + editFromVO);
            
            //long empId=this.getEmpId(bankDtls);
            long empId = helper.getEmpId(bankDtls);
            logger.info("BankDetailsServiceImpl empIdempIdempIdempId" + empId);
            OrgUserMst orgUsrMst= null;
            OrgEmpMst orgEmpMst =null;
            
            logger.info("the emp id is "+empId);
            
            if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
            {	
            	//orgUsrMst = this.getOrgEmpMSTByEmpId(serv, empId).getOrgUserMst();
            	orgUsrMst = helper.getOrgEmpMSTByEmpId(empId).getOrgUserMst();
            	bankDtls.setHrEisEmpMst(helper.getHrEisEmpMstByPK(orgEmpMst));
            }
            
            logger.info("the user mst is "+orgUsrMst);
            
            if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
			{
            	//this.updateBankDtlsVO(serv, bankDtls,postId,userId);
            	helper.updateBankDtlsVO(bankDtls, postId, userId);
       		   	objectArgs.put("msg", "Record Updated Successfully");
				msg=1;
			}
			else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
			{
				logger.info("INSIDE CREATE");
				//this.insertBankDtlsVO(serv, bankDtls, postId, userId, dbId, langId, locId);
				helper.insertBankDtlsVO(bankDtls, postId, userId, dbId, langId, locId);
				objectArgs.put("msg", "Record Inserted Successfully");
			}
            else
			{
			}
			
            
            //Business Logic Ends here//
			List <HrEisBankDtls> actionList = bankDetailDAO.getAllBankDetailData();
			HrEisBankDtls hrEisBankDtls;
			if(actionList!=null && actionList.isEmpty())//Changed By Maruthi.
            {
			 for(Iterator it=actionList.iterator();it.hasNext();)
			 {
              hrEisBankDtls = (HrEisBankDtls)it.next();	            
              logger.info("To Avoid LazyInitialization in insert Bank Details Data" + hrEisBankDtls.getHrEisEmpMst().getEmpId());
			 }
            }
		
			
			
			BankMasterDAOImpl bankMaster =null;
				//new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
			List bankList = bankMaster.getAllBankMasterData(langId);
			
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
    		List accTypes = lookupDAO.getAllChildrenByLookUpNameAndLang("Account Type", langId);
    		
    		List empList = new ArrayList();
			objectArgs.put("bankList", bankList);
			objectArgs.put("accTypes", accTypes);
			objectArgs.put("empList", empList);
			
			objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
			
			logger.info("insertBankMasterDtls Coming into the Service method" + actionList);
			
			
			//added by Ankit Bhatt for "redirectMap"
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getBankDtlsView");
			if(msg==1)
			{
				objectArgs.put("MESSAGECODE",300006);
			}
			else
			{
				objectArgs.put("MESSAGECODE",300005);
			}
			objectArgs.put("redirectMap", redirectMap);			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("bankDtlsMaster");
			
			//ended by Ankit Bhatt.
			logger.info("INSERTED SUCCESSFULLY insertBankDtlsData");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankDtlsData   "+ne);//Added By Maruthi For Printstack.
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...insertBankDtlsData"+pe);//Added By Maruthi For Printstack.
			
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");				
		}
		catch(Exception e){
			
			logger.info("Exception Ocuures...insertBankDtlsData"+e);//Added By Maruthi For Printstack.
			 
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}
	
	
	
	
	
    //for Displaying view page			
	public ResultObject getBankDtlsData(Map objectArgs)
	{
		logger.info("IN  getBankDtlsData");						
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        
		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				Map voToService = (Map)objectArgs.get("voToServiceMap");
                String chk="";
                //long locationId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
                CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
                String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
                logger.info("The chk value is---->>"+voToService.get("chk"));
				
                //added by ravysh
                String FromBasicDtlsNew = voToService.get("FromBasicDtlsNew")!=null?(String)voToService.get("FromBasicDtlsNew"):"";
                
                
                if(voToService!=null&&voToService.get("chk")!=null)
				//{
                    chk=(String) voToService.get("chk");
    				if(chk!=null && chk.equals("1"))//Doubt in condition
    				{
    				String strEmpId = voToService.get("empId").toString();
    				StringBuffer empNameBf=new StringBuffer();
    				long empId=0;			
    				if(!strEmpId.equals(null) && !strEmpId.equals(""))
    				{
    					empId=Long.parseLong(strEmpId);
    				}
    				logger.info("The Employee Id is:-"+empId);
    				//long id=0;//Comment By Maruthi.
    				EmpInfoDAOImpl hrEisEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
    				List<HrEisEmpMst> eisEmpDtlsList = hrEisEmpDao.getListByColumnAndValue("orgEmpMst.empId",empId);
    				
    				if(eisEmpDtlsList==null || eisEmpDtlsList.isEmpty())
    				{
    	            	empNameBf.append("<empNameMapping>").append("<availability>").append("-1").append("</availability>").append("</empNameMapping>");//Added By Maruthi.
    				}
    				else{
    					    OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
    				
    					    HrEisOtherDtls otherVo = otherDao.getOtherData(empId);
    					    long empMstId = otherVo.getHrEisEmpMst().getEmpId();
    						if(otherVo.getOtherId()!=0)
    						{
    							BankDetailDAOImpl hrEisBankDtls = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
    	    					List bankdtlsList= hrEisBankDtls.getListByColumnAndValue("hrEisEmpMst.empId", empMstId);
    	    					if(bankdtlsList!=null&& bankdtlsList.size()>0)
    	    					{	
    	    					    empNameBf.append("<empNameMapping>").append("<availability>").append(0).append("</availability>").append("</empNameMapping>");//Added By Maruthi.
    	    		            	  
    	    					}
    	    					else
    	    					{
    	    					    empNameBf.append("<empNameMapping>").append("<availability>").append(1).append("</availability>").append("</empNameMapping>");//Added By Maruthi.
    	    		            	 
    	    					}
    						}
    						else
    						{
    							empNameBf.append("<empNameMapping>").append("<availability>").append(-2).append("</availability>").append("</empNameMapping>");//Added By Maruthi.
	    		            	
    						}
    						
    					}
					logger.info("Element size ="+eisEmpDtlsList.size());
    				Map map = new HashMap();
    	            String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();
    		         
    	            logger.info(" the string buffer is :"+empNameMapping);
    	            map.put("ajaxKey", empNameMapping);
    				resultObject.setResultCode(ErrorConstants.SUCCESS);
    				resultObject.setResultValue(map);
    				resultObject.setViewName("ajaxData");
    				logger.info(" SERVICE COMPLETE :");
    				}
    				else
    				{	
		        
	            //Added by mrugesh
	    		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
	    		//Ended by mrugesh
	            BankDetailDAO bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
	            
	            //List <HrEisBankDtls> actionList = bankDetailDAO.getAllBankDetailData();
				long empID = 0;
				empID=(voToService.get("Employee_ID_EmpLoanSearch")!=null&&!voToService.get("Employee_ID_EmpLoanSearch").equals(""))?Long.parseLong((String)voToService.get("Employee_ID_EmpLoanSearch")):0;
				List <HrEisBankDtls> actionList = new ArrayList();
				String empName="";
				if((voToService.get("Employee_srchNameText_EmpLoanSearch")!=null&&!voToService.get("Employee_srchNameText_EmpLoanSearch").equals("")))
					empName=(String)voToService.get("Employee_srchNameText_EmpLoanSearch").toString();

				actionList=bankDetailDAO.getAllBankDetailData(locationCode,empID,langId);
	            //List <HrEisBankDtls> actionList = bankDetailDAO.getAllBankDetailData();
	           
	            BankMasterDAOImpl bankMaster =null; 
	            	//new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
    			List bankList = bankMaster.getAllBankMasterData(langId);
    			
    			
	            HrEisBankDtls hrEisBankDtls = new HrEisBankDtls();
	            if(actionList!=null && actionList.isEmpty())
	            {
				 for(Iterator it=actionList.iterator();it.hasNext();)
				 {
	              hrEisBankDtls = (HrEisBankDtls)it.next();	            
	              logger.info("To Avoid LazyInitialization in the emp name is " + hrEisBankDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname());
				 }//ADded By Maruthi for  statement.
	            }
			
	            /*PaybillListServiceImpl impl = new PaybillListServiceImpl();
	            Map paybillListMap = impl.getPaybillList(objectArgs);*/
	            
	            List empList =new ArrayList();
	            objectArgs.put("empName", empName);
	            objectArgs.put("actionList", actionList);
	            objectArgs.put("empList", empList);
	            objectArgs.put("bankList", bankList);
	            objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
	            
	            //added by ravysh
	            
	            /*if(actionList != null)
	            	objectArgs.put("listSize",newBankDtlsList.size());
	            else
	            	objectArgs.put("listSize", 0);*/
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(objectArgs);
	            
	            if(FromBasicDtlsNew.equals("YES"))
	            	 resultObject.setViewName("bankDtlsViewBasicDtls");	
	            else
	            resultObject.setViewName("bankDtlsView");
	            logger.info("Service complete...");
    				}
		//}
				
		}
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls"+e);//Added By Maruthi.
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
		}
	
		return resultObject;
	}

	
	//for displaying Edit Page
	public ResultObject getBankDtlsIdData(Map objectArgs)
	{
		logger.info("getBankData IN Bank Master Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		
		try
		{
			
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
						
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				String editFlag = (String)voToService.get("edit");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	            
	            //Added by mrugesh
	            long languageId=1;
	    		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				//CmnLanguageMst cmnLanguageMstEng=cmnLanguageMstDaoImpl.read(languageId);//Commented By Maruthi.
	    		//Ended by mrugesh
		        logger.info("editFromVO in BankDetailsServiceImpl getBankDtlsIdData" + voToService.get("edit"));
		        long BankDtlsid=0;
		       
		        //added by ravysh
		        String FromBasicDtlsNew = voToService.get("FromBasicDtlsNew")!=null?(String)voToService.get("FromBasicDtlsNew"):"";
		        
		        if(voToService.get("bankDtlsId")!=null && !voToService.get("bankDtlsId").equals(""))
		        {	
		        	BankDtlsid = Long.parseLong((String)voToService.get("bankDtlsId"));
		        }	
        		logger.info("for update screen...BankDtlsId is " + BankDtlsid);
        		
        		BankDetailDAO bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
	           
        		HrEisBankDtls bankDtls = (HrEisBankDtls)bankDetailDAO.getBankDtlsIdData(BankDtlsid);
		        Date DBdate = DBUtility.getCurrentDateFromDB(); 
		        
		        long bankId=0;
		        long branchId=0;
		        long accTypeId=0;
		        String empname = "";
				if((voToService.get("empName")!=null&&!voToService.get("empName").equals("")))
					empname=voToService.get("empName").toString();

		        if(bankDtls!=null )
		        {
		        	/*bankId =bankDtls.getHrEisBankMst().getBankId();
		        	branchId=bankDtls.getHrEisBranchMst().getBranchId();*/
		        	accTypeId=bankDtls.getBankAcctType();
		        }    	        
		        CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
        		List accTypesList = lookupDAO.getAllChildrenByLookUpNameAndLang("Account Type", langId);
        		
        		BankMasterDAOImpl bankMasterDao =null;
        			//new BankMasterDAOImpl(HrEisBankMst.class,serv.getSessionFactory());
    			List bankList = bankMasterDao.getAllBankMasterData(langId);
    			
    			
    			List bankDtlsEmpIdList = bankDetailDAO.getAllBankEmpId();
    			List bankEmpIdList = new ArrayList();
    			for(int listCount=0;listCount<bankDtlsEmpIdList.size();listCount++)
    			{
    				logger.info("the size is from service is "+bankDtlsEmpIdList.size()+" and the value is "+bankDtlsEmpIdList.get(listCount));
    				bankEmpIdList.add(listCount,bankDtlsEmpIdList.get(listCount));
    			}
    			
    			
    			EmpInfoDAOImpl empInfoDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
    			
    			List<HrEisEmpMst> newEmpList = new ArrayList();
    			HrEisEmpMst hrEisEmpMst= new HrEisEmpMst();
    			
    			BranchMasterDAOImpl branchDao =null; 
    				//new BranchMasterDAOImpl(HrEisBranchMst.class,serv.getSessionFactory());
    			
    				
    			 
	            if(editFlag != null && editFlag.equals("Y"))
	            {
            		logger.info("getBankData I m in edit mode ");
            		
//	            	BranchMasterDAO branchMasterDAO = new BranchMasterDAOImpl(HrEisBranchMst.class,serv.getSessionFactory());
            		
            		
        			List branchList = branchDao.getAllBranchs(bankId, langId);
        			
        			HrEisBankDtls newBankDtls = new HrEisBankDtls();

	       			 {
	       				/*newBankDtls.setHrEisBankMst(bankDtls.getHrEisBankMst());
	       				newBankDtls.setHrEisBranchMst(bankDtls.getHrEisBranchMst());*/
	       				newBankDtls.setBankAcctType(bankDtls.getBankAcctType());
	       			 }
	       			
	   	            newBankDtls.setBankAcctNo(bankDtls.getBankAcctNo());
	   	            newBankDtls.setBankAcctStartDt(bankDtls.getBankAcctStartDt());
	   	            newBankDtls.setBankDtlId(bankDtls.getBankDtlId());
	   	            
	   	            
	   	            //by manoj for getting the emp name from org_empMSt
 	            	long userid=bankDtls.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
 	            	logger.info("before the user is "+userid);
 	            	
 	            	logger.info("before the emp id from orgmst is "+bankDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());
 	            	List<OrgEmpMst> orgEmpMstList = empInfoDAO.getOrgData(bankDtls.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst(), langId);
 	            	//HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
 	            	
 	            	hrEisEmpMst.setOrgEmpMst(orgEmpMstList.get(0));
 	            	hrEisEmpMst.setEmpId(bankDtls.getHrEisEmpMst().getEmpId());
 	            	
 	            	newBankDtls.setHrEisEmpMst(hrEisEmpMst);
 	            	logger.info("after the emp id from orgmst is "+newBankDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());
 	            	//end by manoj
	   	           
	   	            
	        			if(newBankDtls!=null)
	    	            {	    	             	          
	    	             logger.info("To Avoid LazyInitialization getBankDtlsIdData empID " + newBankDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());
	    	            }
	            		          		   
	            	   	objectArgs.put("actionList", newBankDtls);
	            	   	objectArgs.put("accTypes", accTypesList);
	            	 	objectArgs.put("branchList",branchList);
	            	   	objectArgs.put("bankList",bankList);
	            	   	objectArgs.put("empList",newEmpList);
	            	   	objectArgs.put("flag","edit");
	            	   	objectArgs.put("date",DBdate);
	            	   	objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
	            	   	
				        resultObject.setResultCode(ErrorConstants.SUCCESS);
				        resultObject.setResultValue(objectArgs);
				        //resultObject.setViewName("bankEditList");
				        
				        if(FromBasicDtlsNew.equals("YES"))
				        	 resultObject.setViewName("bankDtlsMasterBasicDtls");
				        else
				        resultObject.setViewName("bankDtlsMaster");
	            }
	            else if(editFlag != null && editFlag.equalsIgnoreCase("show"))
	            {
	            	objectArgs.put("empName", empname);
	            	objectArgs.put("accTypes", accTypesList);
	            	objectArgs.put("bankList",bankList);
	            	objectArgs.put("empList",newEmpList);
	            	objectArgs.put("flag","insert");
	            	objectArgs.put("date",DBdate);
	            	objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
	            	
				    resultObject.setResultCode(ErrorConstants.SUCCESS);
				    resultObject.setResultValue(objectArgs);
				     //resultObject.setViewName("bankEditList");
				    
				    if(FromBasicDtlsNew.equals("YES"))
			        	 resultObject.setViewName("bankDtlsMasterBasicDtls");
			        else
				    resultObject.setViewName("bankDtlsMaster");
	            }
	            else
	            {
			            List <HrEisEmpMst> actionList = bankDetailDAO.getAllBankDetailData();
			          
			            objectArgs.put("actionList", actionList);
			            objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
			            
			            resultObject.setResultCode(ErrorConstants.SUCCESS);
			            resultObject.setResultValue(objectArgs);
			            
			            if(FromBasicDtlsNew.equals("YES"))
				        	 resultObject.setViewName("bankDtlsMasterBasicDtls");
				        else
			            resultObject.setViewName("bankDtlsView");
	            }			            			
		}
		catch(PropertyValueException pe)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls"+pe);//Added By Maruthi.
			
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");		
		}
		catch(Exception e)
		{	
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls"+e);//Added By Maruthi.
			
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");				
		}	
		return resultObject;
	}	
	
	
	
	public ResultObject getBranchList(Map objectArgs)
	{
	
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		try
		{
			BranchMasterDAO branchMasterDAO =null; 
				//new BranchMasterDAOImpl(HrEisBranchMst.class,serv.getSessionFactory());
			
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());   
			
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String strBankId = (String)voToService.get("bankId");
		
			long bankId = Long.parseLong(strBankId);
			
	     
		List branchList = branchMasterDAO.getAllBranchs(bankId,langId);
		logger.info("State List size:-->" + branchList.size());
		StringBuffer propertyList = new StringBuffer();
		 for (Iterator iter = branchList.iterator(); iter.hasNext();)
         {			 
		  HrEisBranchMst hrEisBranchMst = (HrEisBranchMst)iter.next();		 
		  String branchName = hrEisBranchMst.getBranchName();		  
		  long branchId = hrEisBranchMst.getBranchId();

   		  logger.info("branchName in service iterator " + branchName);
   		  propertyList.append("<branch-mapping>").append("<branch-Id>").append(branchId).append("</branch-Id>").append("<branch-name>").append("<![CDATA[").append(branchName).append("]]>").append("</branch-name>").append("</branch-mapping>");   	
          
                             
          
         }
   	  
         String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         objectArgs.put("ajaxKey", stateNameIdStr);
            
         resultObject.setResultValue(objectArgs);
         resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
        logger.info("After Service Called.........\n");
	}
	catch(Exception e)
	{
		logger.info("Null Pointer Exception Ocuures...getBranchList"+e);
		
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("errorInsert");		
	}				
		return resultObject;
	}

	/**
	 * @return the serviceLocator
	 */
	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	/**
	 * @param serviceLocator the serviceLocator to set
	 */
	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
}
	
