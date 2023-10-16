package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.openl.rules.dt.DTLoader;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnBranchMstDaoImpl;
import com.tcs.sgv.common.dao.CmnCountryMstDAOImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnDistrictMstDAOImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.CmnStateMstDAOImpl;
import com.tcs.sgv.common.dao.CmnTalukaMstDAOImpl;
import com.tcs.sgv.common.dao.CmnVillageMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnCountryMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.CmnStateMst;
import com.tcs.sgv.common.valueobject.CmnTalukaMst;
import com.tcs.sgv.common.valueobject.CmnVillageMst;
import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.common.valueobject.RltBankBranchPay;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BankMasterDAOImpl;
import com.tcs.sgv.eis.dao.BranchMasterDAO;
import com.tcs.sgv.eis.dao.BranchMasterDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.HrEisBranchDtlsDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisBranchDtls;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.ess.dao.CmnBranchlocMpgDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.CmnBranchlocMpg;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class BranchMasterServiceImpl extends ServiceImpl
{
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
	public ResultObject insertBranchMasterDtls(Map objectArgs) throws ConstraintViolationException
	{
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
		try
		{
			logger.info("Coming into the Service method");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			RltBankBranchPay branchMst = (RltBankBranchPay)objectArgs.get("branchMst");			
			logger.info( "The BranchMaster VO is " + branchMst);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			BranchMasterDAOImpl branchMasterDAO = new BranchMasterDAOImpl(RltBankBranchPay.class,serv.getSessionFactory());

			//for changing the id to englishid
			MstBankPay mstBank = (MstBankPay)objectArgs.get("bankMst");	
			long bankId=mstBank.getBankId();

			MstBankPay mstbank =(MstBankPay)branchMasterDAO.getBankCode(bankId);
			String bankCode = mstbank.getBankCode();
			logger.info("bankcode**********" + bankCode);


			logger.info("BANK ID*************" + bankId);



			//end

			String editFromVO = objectArgs.get("edit").toString();

			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());

			String locId=loginDetailsMap.get("locationId").toString();

			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());

			Date sysdate = new Date();

			long branchCode = Long.parseLong(objectArgs.get("branchCode").toString());
			
			logger.info("branchCode**********in update service"+ branchCode);
			
			if(editFromVO!=null && editFromVO.equals("Y"))
			{
				logger.info("INSIDE UPDATE");
				long branchId = branchMst.getBranchId();
				
				logger.info("branchId**************"+branchId);
				
				RltBankBranchPay branchMstVO = branchMasterDAO.read(branchId);
				
				branchMstVO.setBranchName(branchMst.getBranchName());
				branchMstVO.setBranchAddress(branchMst.getBranchAddress());
				branchMstVO.setBranchCode(branchCode);
				branchMstVO.setBankCode(Long.parseLong(bankCode));
				
				logger.info("branch code for updating" + branchMst.getBankCode());

				branchMstVO.setUpdatedDate(sysdate);
				branchMstVO.setUpdatedPostId(postId);
				branchMstVO.setUpdatedUserId(userId);

				branchMstVO.setMicrCode(branchMst.getMicrCode());								
				branchMasterDAO.update(branchMstVO);
				msg=1;
			}
			else if(editFromVO!=null && editFromVO.equals("N"))
			{
				logger.info("INSIDE*****************_________*****_____*****____*****_____**************CREATE");


				IdGenerator idGen = new IdGenerator();
				Long branchId = branchMasterDAO.getNextSeqNoLoc();

				logger.info("branchId********" + branchId);

				branchMst.setBranchId(branchId);
				branchMst.setCreatedDate(sysdate);						
				branchMst.setBankCode(Long.parseLong(bankCode));
				branchMst.setCreatedUserId(userId);
				branchMst.setCreatedPostId(postId);
				branchMst.setCreatedDate(sysdate);
				branchMst.setDbId(dbId);
				branchMst.setLangId(1L);
				branchMst.setLocationCode(locId);
				//Added for branch code
				branchMst.setBranchCode(branchId);
				//ended
				branchMasterDAO.create(branchMst);		        		        
			}
			else
			{
				throw new NullPointerException();
			}

		
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getBranchView");
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			objectArgs.put("redirectMap", redirectMap);			
			resultObject.setResultCode(ErrorConstants.SUCCESS);

			resultObject.setResultValue(objectArgs);
			if(msg==1)
				resultObject.setViewName("branchMaster");
			else
				resultObject.setViewName("branchMaster");
			logger.info("INSERTED SUCCESSFULLY");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...");
			 logger.error("Error is: "+ ne.getMessage());
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		catch(ConstraintViolationException ex)
		{
			 logger.info("Constraint violation Ocuures...");
			 ex.printStackTrace();
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		catch(Exception e){
			
			logger.info("Exception Ocuures...");
			logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "Record is not Inserted. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
		return resultObject;
	}
	
	public ResultObject checkBranchName(Map objectArgs)
	{
		
		logger.info("IN checkBranchName Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Map voToService = (Map)objectArgs.get("voToServiceMap");
        
        try
        {
        BranchMasterDAOImpl branchMasterDAO = new BranchMasterDAOImpl(RltBankBranchPay.class,serv.getSessionFactory());
       
        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
		
        String strbranchName = (String)voToService.get("branchName");
        String oldname = (String)voToService.get("oldname");
        
        long bank_id=0;
        
        if(oldname!= null && (!oldname.equals("")))    
		{
			
        	oldname= oldname.trim().toLowerCase();
		}
        long old_bankId = 0;
        
        
        if(voToService.get("old_bankId")!=null && !voToService.get("old_bankId").toString().equals(""))
        {        	
         old_bankId = Long.parseLong(voToService.get("old_bankId").toString());
        }
        
             
        
        String bankId = (String)voToService.get("bank_id");
        
        
        
        if(bankId!= null && (!bankId.equals("")))   
		{
			
			bank_id= Long.parseLong(bankId);
		}	
         
        	
        StringBuffer propertyList = new StringBuffer();
        strbranchName = strbranchName.trim().toLowerCase();
        if(bank_id!=old_bankId || !strbranchName.equals(oldname))
        {
        		List barnchName = branchMasterDAO.checkBranchName(strbranchName,bank_id,langId);
         
        propertyList.append("<branch-name>");   	
        propertyList.append("<list-size>").append(barnchName.size()).append("</list-size>");
        propertyList.append("</branch-name>"); 
        logger.info("Branch Name size " + barnchName.size());
        }
        else
        {
        	propertyList.append("<branch-name>");   	
            propertyList.append("<list-size>").append(0).append("</list-size>");
            propertyList.append("</branch-name>");
        }
        
        Map result = new HashMap();
        String branchNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
        result=objectArgs;
        result.put("ajaxKey", branchNameIdStr);
           
        resultObject.setResultValue(result);
        resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
       logger.info("After Service Called.........\n");
		}
		catch(Exception e)
		{			
			logger.error("Error is: "+ e.getMessage());			 
		}
		return resultObject;
	}


	public ResultObject getBranchMasterDtls(Map objectArgs)
	{
		logger.info("IN Branch Master Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try
		{
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            BranchMasterDAO branchMasterDAO = new BranchMasterDAOImpl(RltBankBranchPay.class,serv.getSessionFactory());
	            long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

	            BankMasterDAOImpl bankMaster = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());
	            

	            //for new id
	          //  List<RltBankBranchPay> branchList = branchMasterDAO.getAllBranchMasterData(langId);
	            /*List<RltBankBranchPay> branchMstList = new ArrayList();
	            List<MstBankPay> bankIDlist = new ArrayList();

		            for (int i=0; i<branchList.size(); i++)
	                {
		            	RltBankBranchPay hrEisBranchMst = new RltBankBranchPay();
		            	//Object hrEisBranchMst[] = (hrEisBranchMst[]) bankIDlist.get(i);
		            	hrEisBranchMst = branchList.get(i);

		            	long bankCode = hrEisBranchMst.getBankCode();
		            	List<MstBankPay> bankIdsList = bankMaster.getAllBankIds(bankCode);
		            	 for (int j=0; j<bankIdsList.size(); j++)
			                {
		            		 
		            		 MstBankPay mstbank = new MstBankPay();
		            		 mstbank = bankIdsList.get(j);
		            		 
		            		 MstBankPay mstbankids = new MstBankPay();
		            		 mstbankids.setBankId(mstbank.getBankId());
		            		 
		            		 bankIDlist.add(mstbankids);
		            		 
			                }
		            	
		            	
		            RltBankBranchPay hrEisBranchMsten = new RltBankBranchPay();
		            hrEisBranchMsten.setBankCode(hrEisBranchMst.getBankCode());	            		
	                hrEisBranchMsten.setBranchId(hrEisBranchMst.getBranchId());
	                hrEisBranchMsten.setBranchName(hrEisBranchMst.getBranchName());
	                hrEisBranchMsten.setMicrCode(hrEisBranchMst.getMicrCode());

		            	
	                	branchMstList.add(hrEisBranchMsten);
	                }//end for
*/ 				List branchList = branchMasterDAO.getAllBranchMasterData(langId);
	            List bankList = bankMaster.getAllBankMasterData(langId);


	            objectArgs.put("actionList", branchList);
	            objectArgs.put("listSize", branchList.size());
	            objectArgs.put("bankList", bankList);


	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(objectArgs);
	            resultObject.setViewName("branchMasterView");
	            
		}
		catch(Exception e)
		{
			logger.info("Exception Ocuures...");
			logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is some problem. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
	
		return resultObject;
	}

	public ResultObject getbranchdtls(Map objectArgs)
	{
		logger.info("...................................IN getbranchdtls....................................");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try
		{
	

	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(objectArgs);
	            resultObject.setViewName("CreateBranch");
	            logger.info("palak  here to go to the jsp");
		}
		catch(Exception e)
		{
			logger.info("Exception Ocuures...");
			logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is some problem. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
	
		return resultObject;
	}

	public ResultObject insertBranchDtls(Map objectArgs)
	{
		Calendar calTime = Calendar.getInstance();
		logger.info("insertBranchDtlsService starts: "+calTime.getTimeInMillis());
		logger.info("insertBranchDtlsService starts: "+calTime.getTimeInMillis());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		
		String locId=((loginMap.get("locationId")).toString());
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	
		try
		{
	
		
			CmnBranchlocMpgDaoImpl cmnBranchlocMpgDaoImpl  = new CmnBranchlocMpgDaoImpl(CmnBranchlocMpg.class,serviceLocator.getSessionFactory());

			CmnBranchMstDaoImpl cmnBranchMstDaoImpl  = new CmnBranchMstDaoImpl(CmnBranchMst.class,serviceLocator.getSessionFactory());
	
			Map mapFromVogen = (Map)objectArgs.get("dtls");
			logger.info("map from vo gen is " + mapFromVogen);
		
			HrEisBranchDtlsDAOImpl hrEisBranchDtlsDaoImpl  = new HrEisBranchDtlsDAOImpl(HrEisBranchDtls.class,serviceLocator.getSessionFactory());
		

			long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");
			
			
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			logger.info("In Service Impl " + mapFromVogen.get("branchTypeLookup").toString());
			long branchTypeLookup=Long.parseLong(mapFromVogen.get("branchTypeLookup").toString());
			logger.info("type lookup id In service is "+branchTypeLookup);
			long TypeLookup;
			
			if(branchTypeLookup==1)
			{
				 TypeLookup=Long.parseLong(resourceBundle.getString("mainbranch"));
			}
			else
			{
				 TypeLookup=Long.parseLong(resourceBundle.getString("subbranch"));
			}
			
			logger.info("---------------TypeLookup---------------"+TypeLookup);
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			CmnLookupMst cmnLookupMst = cmnLookupMstDAOImpl.read(TypeLookup);

			
			long active=Long.parseLong(mapFromVogen.get("active").toString());
			logger.info("---------------active---------------"+active);
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl1=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			CmnLookupMst cmnLookupMst1 = cmnLookupMstDAOImpl1.read(active);

			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			
			long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
			logger.info("---------------postId---------------"+postId);
			
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			
			CmnBranchMst cmn2 = new CmnBranchMst();
			IdGenerator gen = new IdGenerator();
			long branchId=gen.PKGenerator("cmn_branch_mst",objectArgs);
			
			logger.info("id mgenerated for cmn_branch_mst and that is:"+branchId);
			
			
			cmn2.setBranchId(branchId);
			cmn2.setBranchName(mapFromVogen.get("branchname").toString());
			cmn2.setActivateFlag(Long.parseLong((mapFromVogen.get("active").toString())));
			//Modified for Keeping the brnach id and Branch code equal
			
			//cmn2.setBranchCode(mapFromVogen.get("branchcode").toString());
			cmn2.setBranchDesc(mapFromVogen.get("branchdesc").toString());
			cmn2.setCmnDatabaseMst(cmnDatabaseMst);
			logger.info("--------branchdesc-----------" +mapFromVogen.get("branchdesc").toString());
			cmn2.setCmnLanguageMst(cmnLanguageMst);
			cmn2.setCmnLocationMst(cmnLocationMst);
			cmn2.setCreatedBy(userId);
			cmn2.setCreatedByPost(postId);
			cmn2.setCreatedDate(new Date());
			cmn2.setDisplayName(mapFromVogen.get("branchname").toString());
			cmn2.setEndDate(new Date());
			cmn2.setStartDate(new Date());
			cmn2.setUpdatedBy(null);
			cmn2.setUpdatedByPost(null);
			cmn2.setUpdatedDate(new Date());
			
			logger.info("set method completed in CmnBranchMst");
			
			cmnBranchMstDaoImpl.create(cmn2);
			
		/*	long srNo=gen.PKGenerator("CMN_BRANCHLOC_MPG",objectArgs);

			logger.info("id mgenerated for CMN_BRANCHLOC_MPG and that is:"+srNo);
			CmnBranchlocMpg branchloc = new CmnBranchlocMpg();
			 
			branchloc.setSrno(srNo);
			branchloc.setBranchCode(mapFromVogen.get("branchcode").toString());		
			branchloc.setLocationCode(locId);
			logger.info("--------locationCode-----------" +locId);
			branchloc.setCmnDatabaseMst(cmnDatabaseMst);
			branchloc.setCmnLookupMst(cmnLookupMst1);
			branchloc.setCreatedDate(new Date());
			branchloc.setBranchHeadUserId(userId);
			branchloc.setCmnBranchMst(cmn2);
			branchloc.setOrgPostMstCreatedByPost(orgPostMst);
			branchloc.setOrgPostMstUpdatedBypost(orgPostMst);
			branchloc.setOrgUserMstCreatedBy(orgUserMst);
			branchloc.setOrgUserMstUpdatedBy(orgUserMst);
			branchloc.setUpdatedDate(new Date());
			branchloc.setOrgPostMstBranchHead(orgPostMst);
			branchloc.setCmnLocationMst(cmnLocationMst);*/
			
			
		//	CmnBranchlocMpgDaoImpl cmnBranchlocMpgDaoImpl2=new CmnBranchlocMpgDaoImpl(CmnBranchlocMpg.class,serv.getSessionFactory());
		//	CmnBranchlocMpg cmnBranchlocMpg = cmnBranchlocMpgDaoImpl2.read(srNo);
			
		//	logger.info("set method completed in CMN_BRANCHLOC_MPG");
			
		//	cmnBranchlocMpgDaoImpl.create(branchloc);
			
			HrEisBranchDtls branchdtls = new HrEisBranchDtls();
			
			long id = gen.PKGenerator("HR_EIS_BRANCH_DTLS",objectArgs);
			
			logger.info("id mgenerated for HR_EIS_BRANCH_DTLS and that is:"+id);
			branchdtls.setId(id);
			//branchdtls.setCmnBranchlocMpg(branchloc);
			branchdtls.setCmnLanguageMst(cmnLanguageMst);
			branchdtls.setCmnLocationMst(cmnLocationMst);
			branchdtls.setCmnLookupMst(cmnLookupMst);
			branchdtls.setCreatedDate(new Date());
			branchdtls.setOrgPostMstByCreatedByPost(orgPostMst);
			branchdtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			logger.info("--------orgPostMst-----------" +orgPostMst);
			branchdtls.setOrgUserMstByCreatedBy(orgUserMst);
			branchdtls.setOrgUserMstByUpdatedBy(orgUserMst);
			branchdtls.setUpdatedDate(new Date());

			logger.info("set method completed in hrEisBranchDtls");
		
			hrEisBranchDtlsDaoImpl.create(branchdtls);
			
			logger.info("create method completed");
			
				objectArgs.put("msg", "Record Inserted Successfully");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("CreateBranch");
		}
			

		catch(Exception e)
		{			
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is some problem. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
			
			
		}
		
		return resultObject;
	}
	

	
	public ResultObject getBranchData(Map objectArgs)
	{
		logger.info("IN getBranchData Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			
	            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	            BranchMasterDAO branchMasterDAO = new BranchMasterDAOImpl(RltBankBranchPay.class,serv.getSessionFactory());
	            
	            Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());   
	            
    			Map voToService = (Map)objectArgs.get("voToServiceMap");
    			String editFlag = (String)voToService.get("edit");
    			
    			if(editFlag != null && editFlag.equals("Y"))
    			{
    				logger.info("I m in edit mode ");
    				String branchId = (String)voToService.get("branchId");

    				logger.info("update branchId*******" + branchId);
    				//  BankMasterDAOImpl bankMaster = new BankMasterDAOImpl(MstBank.class,serv.getSessionFactory());
    				//MstBank mstbank = bankMaster.getAllBankIdFromBankCode(bankCode);

    				List<RltBankBranchPay> rltBankBranch =branchMasterDAO.getBranchIdFromBankCode(branchId);
    				
    				logger.info("update rltBankBranch list size" + rltBankBranch.size());
    				
    				//long branchId =0;
    				//long Branchid =0;
    				/*
    				for (int i=0; i<rltBankBranch.size(); i++)
 	                {
 		            	RltBankBranch hrEisBranchMst = new RltBankBranch();
 		            	hrEisBranchMst = rltBankBranch.get(i);
 		            	
 		            	branchId = hrEisBranchMst.getBranchId();
 		            	Branchid =branchId;
 	                }*/
    				
    				logger.info("update branchId" + branchId);
    				
    				//long branchId = rltBankBranch.getBranchId();            		

    				//String branchId = (String)voToService.get("branchid");

    				 

    				RltBankBranchPay actionList = (RltBankBranchPay)branchMasterDAO.getBranchIdData(branchId);

    				RltBankBranchPay newResult = new RltBankBranchPay();

    				BankMasterDAOImpl bankMaster = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());

    				String bankCode = (String)voToService.get("bankCode");
    				long BankCode = Long.parseLong(bankCode);
    				
    				List<MstBankPay> BankList = bankMaster.getAllBankIdFromBankCode(BankCode);
    				long bankid = 0;
    				if(BankList != null && BankList.size() > 0)
    					bankid = BankList.get(0).getBankId();
    				List bankList = bankMaster.getAllBankMasterData(langId); 


    				//for language id change

    				BankMasterDAOImpl bankDao1 = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());

    				newResult.setBranchAddress(actionList.getBranchAddress());
    				newResult.setBranchCode(actionList.getBranchCode());
    				newResult.setBranchId(actionList.getBranchId());
    				
    				logger.info("micr codeeeeeeeee in get branh data"+actionList.getMicrCode());
    				
    				String micrCode = actionList.getMicrCode();
    				
    				newResult.setBranchName(actionList.getBranchName());
    				newResult.setMicrCode(actionList.getMicrCode());
    				newResult.setBankCode(actionList.getBankCode());
    				//end


    				Map map = new HashMap();
    				map=objectArgs;          
    				map.put("bankid",bankid);
    				map.put("micrCode",micrCode);
    				map.put("actionList", newResult);
    				map.put("bankList", bankList);
    				resultObject.setResultCode(ErrorConstants.SUCCESS);
    				resultObject.setResultValue(map);
    				resultObject.setViewName("branchEditList");
    			}
	            else
	            {
			            List <RltBankBranchPay> BranchList = branchMasterDAO.getAllBranchMasterData(langId);
			            
			            List<RltBankBranchPay> BranctMstList = new ArrayList();
			            for (int i=0;i<BranchList.size();i++)
		                {
			            	RltBankBranchPay hrEisBranchMst=new RltBankBranchPay();
			            	hrEisBranchMst=BranchList.get(i);
			            	RltBankBranchPay hrEisBranchMstgu=new RltBankBranchPay();
		                	
		            		
		                	BranchList.add(hrEisBranchMstgu);			
		                				
		                }
			            
			            
			            Map map = new HashMap();
			            map=objectArgs;
			            map.put("actionList", BranchList);
			            resultObject.setResultCode(ErrorConstants.SUCCESS);
			            resultObject.setResultValue(map);
			            resultObject.setViewName("branchMasterView");
	            }
			            
			
		}
		catch(Exception e)
		{
			logger.info("Exception Ocuures...");
			logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is some problem. Please Try Again.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");
		}
	
		return resultObject;
	}	
	//method for getting all Country list and redirect to Branch Master Page
	public ResultObject getBranchMaster(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

		try{
			CmnCountryMstDAOImpl cmnCountry = new CmnCountryMstDAOImpl(CmnCountryMst.class,serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());   
			logger.info("LangID in getBranchMaster " + langId);
			BankMasterDAOImpl bankMaster = new BankMasterDAOImpl(MstBankPay.class,serv.getSessionFactory());
			List bankList = bankMaster.getAllBankMasterData(langId); 
			
			
			Map map = new HashMap();
			map=objectArgs;
            map.put("bankList", bankList);
            resultObject.setResultCode(ErrorConstants.SUCCESS);
            resultObject.setResultValue(map);
            
            resultObject.setViewName("branchMaster");
	}
	catch(Exception e)
	{
		logger.info("Exception Ocuures...");
		logger.error("Error is: "+ e.getMessage());
		 objectArgs.put("msg", "There is some problem. Please Try Again.");
		 resultObject.setResultValue(objectArgs);
		 resultObject.setViewName("errorInsert");
	}

	return resultObject;
 }	
	
	
	
}
