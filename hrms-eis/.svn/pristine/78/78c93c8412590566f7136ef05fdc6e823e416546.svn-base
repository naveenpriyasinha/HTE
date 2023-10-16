package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.tcs.sgv.deduction.dao.DeducExpMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.MiscRecoverDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.VoluntryPFDAOImpl;
import com.tcs.sgv.eis.valueobject.GpfAcctDtlsVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrMiscRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;
import com.tcs.sgv.eis.valueobject.PayrollCustomVO;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class DeductionDtlsService extends ServiceImpl{
	Log logger = LogFactory.getLog( getClass() );
	
	
	public ResultObject generateMap(Map objectArgs) {
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		 Map voToService = (Map)objectArgs.get("voToServiceMap");
		logger.info("in generateMap method.." );
		try
		{
						
			
			
			
			DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());			
			DeducExpMasterDAOImpl deducExprDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory()); 			
			OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
			
			String EmpDeducDtls=(String)voToService.get("EmpDeducDtls");
			if(EmpDeducDtls==null) EmpDeducDtls="";
			long orgEmpid=0;
			long empid=0;
			HrEisEmpMst hrEisMst = new HrEisEmpMst();
			if(voToService.get("EmpId")!=null && !voToService.get("EmpId").equals(""))
			{
				
				orgEmpid= Long.parseLong((String)voToService.get("EmpId"));
				logger.info("EmpId is:-->" + orgEmpid);
				EmpInfoDAOImpl hrEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				List<HrEisEmpMst> hrEmpList = hrEmpDao.getListByColumnAndValue("orgEmpMst.empId", orgEmpid);
				if(hrEmpList!=null && hrEmpList.size()>0)
				{
					hrEisMst=hrEmpList.get(0);
					empid=hrEisMst.getEmpId();
					logger.info("hrEisEmpId is "+empid);
				}
			}
			
			
//			by manoj for loan in one screen list
			Map voToServiceMap = (Map)objectArgs.get("voToServiceMap");
			
			voToServiceMap.put("Employee_ID_EmpLoanSearch", String.valueOf(hrEisMst.getOrgEmpMst().getEmpId()));
			voToServiceMap.put("edit", "N");
			objectArgs.put("voToServiceMap",voToServiceMap);
			
			logger.info("the emp id from vo to map is "+(String)voToServiceMap.get("Employee_ID_EmpLoanSearch"));
			
			
			ResultObject resultObj = serv.executeService("getLoanValue", objectArgs);
				
			objectArgs = (Map)resultObj.getResultValue();
			
			//loan ends
			
			
			
			
			//misc recovery starts
			
			EmpInfoDAOImpl empInfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			HrMiscRecoverDtls hrMiscRecoverDtls = new HrMiscRecoverDtls();
			
			MiscRecoverDAOImpl miscRecovDao = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
			
			List miscRoverList = miscRecovDao.getAllMiscDataByEmpId(empInfoDao.read(empid));
			
			logger.info("miscRoverList "+miscRoverList);
			long miscRecov = 0;
			if(miscRoverList!=null && miscRoverList.size()>0)
			{
				for(int recovCnt=0;recovCnt<miscRoverList.size();recovCnt++)
				{
					
					hrMiscRecoverDtls=(HrMiscRecoverDtls)miscRoverList.get(recovCnt);
					long recoverInstallments=hrMiscRecoverDtls.getInstallment();
					Date recoveryDate=hrMiscRecoverDtls.getRecoverDate();	
					
					Calendar c = Calendar.getInstance();	
					Date currentDate = c.getTime(); 
					
					c.set(Calendar.YEAR, recoveryDate.getYear()+1900);
			        c.set(Calendar.MONTH, recoveryDate.getMonth());
			        c.set(Calendar.DAY_OF_MONTH, recoveryDate.getDate());
			        
			        c.add(Calendar.MONTH,(int)(recoverInstallments)+1);//1 is added to get the last month
			        
			        Date recovEndDate = c.getTime();
			        
			      
			        logger.info("The recoveryDate Date is :-"+recoveryDate);
			        logger.info("The Loan End Date is :-"+recovEndDate);
			        logger.info("The current Date is :-"+currentDate);
			        
			        long recoveredMiscAmt = hrMiscRecoverDtls.getTot_recv_amt();
			        long totalMiscAmt = hrMiscRecoverDtls.getTotal_amount();
			        long recoveredMiscInst = hrMiscRecoverDtls.getTot_recv_inst();
			        
			        
			        //1st of given month
					if(currentDate.compareTo(recoveryDate)==1 && currentDate.compareTo(recovEndDate)<=0  && hrMiscRecoverDtls.getMiscActivateFlag()==1 && recoveredMiscAmt < totalMiscAmt && recoveredMiscInst < recoverInstallments)
			        {
						miscRecov+=miscRecov+hrMiscRecoverDtls.getRecoverAmt();
					}
					
				}
				
			}
			logger.info("the miscRecov amt "+miscRecov);
			objectArgs.put("miscRecov", miscRecov);
			//end by manoj 
			
			//temp code - Ankit Bhatt
			/*DeductionDtlsDAOImpl deducDao = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			Date sysdate = new Date();
			HrPayDeductionDtls deducDtlsVo = deducDao.getHrPayDeductionDtls(hrEisMst.getOrgEmpMst().getEmpId(),36,sysdate.getMonth()+1,sysdate.getYear()+1900 ); //36=GPF_GRP_D
			long gpfGrpDVal = 0;
				if(deducDtlsVo!=null)
				{
					gpfGrpDVal = Math.round(deducDtlsVo.getEmpDeducAmount());
				}
				objectArgs.put("gpfGrpDVal", gpfGrpDVal);
				
				
				//DeductionDtlsDAOImpl deducDao = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			
				
				HrPayDeductionDtls deducDtlsVo1 = deducDao.getHrPayDeductionDtls(hrEisMst.getOrgEmpMst().getEmpId(),72,sysdate.getMonth()+1,sysdate.getYear()+1); //72=GPF_GRP_abc
				long gpfGrpAbcVal = 0;
					if(deducDtlsVo1!=null)
					{
						gpfGrpAbcVal = Math.round(deducDtlsVo1.getEmpDeducAmount());
					}
					objectArgs.put("gpfGrpAbcVal", gpfGrpAbcVal);*/
					
			//ended
			
			
			List dataList =new ArrayList();
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long locationId=Long.parseLong(loginMap.get("locationId").toString());
    		//long userId=Long.parseLong(loginMap.get("userId").toString());
    		OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
    		List otherList = otherdtlsDao.getAllOtherDataForAllow(locationId,langId,empid);
    		// Instead of making objects of empmaster ,othedetails,dfesignation strins have been used as VO as it is only for display to avoid whole of objects 
			 Object[] row;
			 long incometax=0;
			 if(otherList!=null &&  otherList.size() >0 )
			 {                		
 		    	 for(int k=0;k<otherList.size();k++) 
 		    	 {
     		    	
 		    		PayrollCustomVO payrollCustomVO = new PayrollCustomVO();
 		    		row = (Object[])otherList.get(k);
 		    		payrollCustomVO.setOtherId(Long.parseLong(row[0]!=null?row[0].toString():"0")) ; 
 		    		payrollCustomVO.setEmpName(row[1]!=null?row[1].toString():"") ; 
 		    		payrollCustomVO.setGradeName(row[2]!=null?row[2].toString():"") ; 
 		    		payrollCustomVO.setDsgnName(row[3]!=null?row[3].toString():"") ; 
 		    		payrollCustomVO.setEmpType(Long.parseLong(row[4]!=null?row[4].toString():"0")) ; 
 		    		payrollCustomVO.setEmpId(Long.parseLong(row[5]!=null?row[5].toString():"0")) ; 
 		    		
 		    		incometax=Long.parseLong(row[6]!=null?row[6].toString():"0");
 		    		
 		    		dataList.add(payrollCustomVO);	    	
 		    	 }    			
			 }

			objectArgs.put("dataList", dataList);
			
			objectArgs.put("incometax", incometax);		
			/*List<HrEisOtherDtls> otherList = otherDetailDAOImpl.getAllOtherData();
			logger.info("The Size is of other Details:-"+otherList.size());
			for(int i=0;i<otherList.size();i++){
				hrEisOtherDtls = (HrEisOtherDtls)otherList.get(i);
				if(hrEisOtherDtls.getHrEisEmpMst().getEmpType()!=300018 && hrEisOtherDtls.getHrEisEmpMst().getEmpType()!=300225)
					logger.info("The Value is:-"+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId()+" "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+ " "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpMname()+ " "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg().getOrgDesignationMst().getDsgnName()+" "+hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeName());
				else
				{
					HrEisSgdMpg sgdMpg = hrEisOtherDtls.getHrEisSgdMpg();
					HrEisGdMpg hrEisGdMpg=null;
					OrgGradeMst orgGradeMst = null;
					OrgDesignationMst orgDesigMst = null;
					String dsgnName="";
					if(sgdMpg!=null)
					{
					  hrEisGdMpg = hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg();
					  if(hrEisGdMpg!=null)
					  {
					    orgGradeMst = hrEisGdMpg.getOrgGradeMst();
					    orgDesigMst = hrEisGdMpg.getOrgDesignationMst();
					    if(orgDesigMst!=null)
					    {
					    	dsgnName = orgDesigMst.getDsgnName();
					    }
					  }
					  
					}
					
					logger.info("The Value is:-"+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId()+" "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+ " "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpMname()+ " "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
			     }
			}*/
			
		
		
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			HttpSession session=request.getSession();
			Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");			
			//List actionList = empDuducDtlsDAO.getDeductionType(); 
			//Modified by Manish
		
		
			hrEisOtherDtls=otherdtlsDao.getOtherDataByHrEis(empid);
			long payCommonissionId = 2500340,empId =0;//By default the commission id is 5th pay commission(For fixed and contractual employee)
			if(hrEisOtherDtls.getHrEisSgdMpg()!=null)
			{
				payCommonissionId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId();
				empId = hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId();
			}
			long locId=Long.parseLong(loginMap.get("locationId").toString());
			List deducActionList = empDuducDtlsDAO.getDeductionTypeDeducDtls(payCommonissionId,locId,empId);  ///will bring deduction name and its ID that are mapped with emp.
			logger.info("size in deduction dtls is"+deducActionList.size());
			
			long totalDeduc=0;
			Date sysDate = new Date();
			for(int k =0;k<deducActionList.size();k++)
			{
				Object[] arr=(Object[]) deducActionList.get(k);
				long tempAmount =empDuducDtlsDAO.getAmountForDeduction(Long.valueOf(arr[0].toString()),empid,sysDate.getMonth()+1,sysDate.getYear()+1900);
				logger.info( " amount is "+tempAmount+"for deduction  "+arr[1].toString());
				totalDeduc+=tempAmount;
			}
			
			logger.info("Total Of Deduction is "+totalDeduc);
			 
            List deducNamesFromMpg = empDuducDtlsDAO.getAllDeductionTypeFromMpg(empid,-1,-1);
            
            logger.info("size ::::::::::::::::::::"+deducNamesFromMpg.size());
            logger.info("total of deductions is "+totalDeduc);
            
            	
            /*	   for (Iterator iter = deducNamesFromMpg.iterator(); iter.hasNext();)
                   {	
            		   HrPayDeductionDtls dtls=new HrPayDeductionDtls();
            		  dtls=(HrPayDeductionDtls)iter.next();
            		  logger.info("code in service is"+dtls.getDeducDtlsCode());
                   }
                   */
            
            
            List newDeducNamesFromMpg  = new ArrayList();
            long vpfAmt = 0;
            
            VoluntryPFDAOImpl vpfDao = new VoluntryPFDAOImpl(HrPayVpfDtls.class,serv.getSessionFactory());
            
            HrPayVpfDtls vpfVo = vpfDao.getHrPayVpfByEmpId(empid);
            
            if(vpfVo!=null)
            {
            	vpfAmt=vpfVo.getVpfAmt();
            }
            
               
            GpfAcctDtlsVO gpfAccVo = new GpfAcctDtlsVO();
            
            EmpGpfDtlsDAOImpl empGpfDao = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
            
            
            List gpfAccountList = empGpfDao.getAllGpfDetailsbyUserId(empInfoDao.read(empid).getOrgEmpMst().getOrgUserMst().getUserId());
            logger.info("::::::::gpfAccountList.size:::::"+gpfAccountList.size());
        	
            long gpfAccoungFlag =0;
            long gpfFlagForZeroCheck =0;
            
            if(gpfAccountList!=null && gpfAccountList.size()>0)
            {
            	logger.info("::::::::gpfAccountList.size:11111111111::::"+gpfAccountList.size());
            	gpfFlagForZeroCheck=1;
            	int monthofDate=0;
            	int yearofDate=0;
            	Date EmpSrvcExpDate = empInfoDao.read(empid).getOrgEmpMst().getEmpSrvcExp();
            	
            	SimpleDateFormat sdfObj = new SimpleDateFormat("MM");
            	String days = sdfObj.format(EmpSrvcExpDate);
            	monthofDate=Integer.parseInt(days);
            	
            	sdfObj = new SimpleDateFormat("yyyy");
            	days = sdfObj.format(EmpSrvcExpDate);
            	yearofDate=Integer.parseInt(days);
            	
            	if(vpfVo!=null)
            	{	
            	int ZerovpfMonths=vpfVo.getZerovpfMonths();
            	gpfAccoungFlag =1;
            	
            	if(ZerovpfMonths==6)
            	monthofDate-=6;
            	else
                	monthofDate-=3;
            	}
            	else
            	monthofDate-=3;
            	

            	if(monthofDate<=0)
            	{
            	monthofDate+=12;
            	yearofDate--;
            	}

            	Date givenDate= new Date();
            	
            	sdfObj = new SimpleDateFormat("MM");
            	int monthGiven=Integer.parseInt(sdfObj.format(givenDate));

             	sdfObj = new SimpleDateFormat("yyyy");
            	int yearGiven=Integer.parseInt(sdfObj.format(givenDate));
            	
            	
            	if(yearofDate < yearGiven ||( yearofDate==yearGiven && monthofDate <= monthGiven))
            	{	
            		gpfFlagForZeroCheck=0;
             	}        	
            	else
            	{
            	for (Iterator i = gpfAccountList.iterator(); i.hasNext();)
                {
                    logger.debug("getting objects one by one from list");
                    Object[] RelationVO = (Object[])i.next();
                    String gpfAccountNumber = (String)RelationVO[0];

            	//logger.info("::::::::gpfAccountNumber:::::"+gpfAccountNumber+":::::::::");
            	//logger.info("::::::::gpfAccountNumber.length():::::"+gpfAccountNumber.length()+":::::::::");
                if(gpfAccountNumber!=null)
                {
	            	if(gpfAccountNumber.equalsIgnoreCase("")||gpfAccountNumber.equalsIgnoreCase(" "))
	            		{
	            		logger.info("::::::::in if loop::::::");
	            		gpfFlagForZeroCheck=0;
	            		}
	                }
                }
            	}
            }
            logger.info(":::::::gpfFlagForZeroCheck in objectArgs::::::"+gpfFlagForZeroCheck);
        	
            objectArgs.put("gpfFlagForZeroCheck",gpfFlagForZeroCheck);
            objectArgs.put("gpfAccoungFlag",gpfAccoungFlag);
            List exprMpg = deducExprDAO.getDeducExpMasterData(langId,payCommonissionId);	
			logger.info("Expression List size is " + exprMpg.size());
		//	logger.info("original size of "+deducActionList.size());
			objectArgs.put("deducActionList", deducActionList);	
			objectArgs.put("totalDeduc", totalDeduc);	
			objectArgs.put("vpfAmt", vpfAmt);	
			//objectArgs.put("otherList", otherList);
			objectArgs.put("hrEisMst", hrEisMst);	
			objectArgs.put("deducNamesFromMpg", deducNamesFromMpg);
			objectArgs.put("deducNamesFromExpr", exprMpg);
			objectArgs.put("deducMpgSize",deducActionList.size());
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			/*if(EmpDeducDtls.equalsIgnoreCase("Y"))
				resultObject.setViewName("empSetDeducDtls");
			else*/
			    resultObject.setViewName("empDeducDtlsView");								
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankDtlsData");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(Exception e)
		{
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			Map errorMap = new HashMap();
			errorMap.put("msg", "There is some problem. Please Try Again Later.");
			retObj.setResultCode(-1);
			retObj.setResultValue(errorMap);
			retObj.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return retObj;
			
		}								
		return resultObject;		
	}
	
	
	public ResultObject insertEmpDeducDtls(Map objectArgs)
	{
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
					
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			 
			logger.info("manish  inside insertEmpDeducDtls:::::::::::::: ");
			long locId=0;
			HttpSession session=request.getSession();
			Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");			
			long langId=Long.parseLong(loginDetailsMap.get("langId").toString());
		    CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			if(objectArgs.get("batchupdate")!=null&&!objectArgs.get("batchupdate").equals("")&&objectArgs.get("batchupdate").equals("true"))
			{

				logger.info("manish inside batch update of deduction dtls::::::::::");
				long empid=Long.parseLong(  objectArgs.get("empid").toString());
				
				 DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
				 DeducExpMasterDAOImpl deducExprDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
																
				 EmpInfoDAOImpl empDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				 
				 
					EmpInfoDAOImpl empInfoDao= new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
					List<HrEisEmpMst> hrEisEmpMstList = empInfoDao.getListByColumnAndValue("orgEmpMst.empId",empid);
					HrEisEmpMst hrEisEmpMst=  new HrEisEmpMst();
					 
					if(hrEisEmpMstList!=null && hrEisEmpMstList.size()>0)
					{
						hrEisEmpMst = hrEisEmpMstList.get(0);
					}
					
				 List exprMpg = deducExprDAO.getDeducExpMasterData(langId);
				 
				 long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
				 OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				 OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
        		
				 long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
				 CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				 CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
    			
				 long locid=Long.parseLong(loginDetailsMap.get("locationId").toString());
				 CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				 CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locid);	    	         	           			      
    			
				 long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
				 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
    			 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
    			 
    			 Date sysDate = new Date(); 
    			 PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
    				HrPayPaybill hrpaPaybillDump = payBillDAOImpl.getPaybillDataByEmpId(  empid,  4,  sysDate.getMonth()+1,  sysDate.getYear()+1900) 	;
				 
				 int size= Integer.parseInt(objectArgs.get("deducSize").toString());
				 logger.info("Deduction dtls  size is " + size);
				 for(int j=1;j<=size;j++)
				 {
				 
					 HrPayDeductionDtls empAllwMpg= (HrPayDeductionDtls)objectArgs.get("empDeducDtls"+j);
					 empAllwMpg.setHrEisEmpMst(hrEisEmpMst);
				
					 logger.info("Object for Deduc " + empAllwMpg);
					 logger.info("empAllwMpg.getEmpDeducAmount()" + empAllwMpg.getEmpDeducAmount());
					 String editFromVO = objectArgs.get("editDeduc"+j).toString();
					 logger.info("Edit flag in Deduc service " + editFromVO);
					 if(empAllwMpg.getEmpDeducAmount()!=-1)
					 {
					 
					 
				// String editFromVO = objectArgs.get("edit"+j).toString();
				 //logger.info("Edit flag in Deduc service " + editFromVO);
				 //logger.info("Edit flag in Deduc service " + editFromVO);
				 
						 HrEdpComponentMpgDAOImpl edpCompoDao = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class,serv.getSessionFactory());
							List<HrPayEdpCompoMpg> edpCompoList = edpCompoDao.getDataByEDP(  empAllwMpg.getHrPayDeducTypeMst().getDeducCode(),  2500134);
							HrPayEdpCompoMpg hrPayEdpCompoMpg = null;
							if(edpCompoList.iterator().hasNext())
								hrPayEdpCompoMpg=edpCompoList.iterator().next();
							
		    			 
		    			 Date sysdate = new Date(); 
					 
		    			 logger.info("Manish deduc code is"+empAllwMpg.getDeducDtlsCode());
		    			 if(editFromVO!=null && editFromVO.equals("Y"))
		    			 {
								logger.info("INSIDE UPDATE " + empAllwMpg.getDeducDtlsCode());
								
								
								
								 
									
									
									
									if(empAllwMpg.getDeducDtlsCode()!=0)
									{
										HrPayDeductionDtls empDeducDtls = empDuducDtlsDAO.read(empAllwMpg.getDeducDtlsCode());		
										logger.info("empDeducDtls "+empDeducDtls);
										if(empDeducDtls != null)
										{
											if(empDeducDtls.getMonth()>0 && empDeducDtls.getYear()>0 && empDeducDtls.getMonth() ==  empAllwMpg.getMonth() && empAllwMpg.getYear()==empDeducDtls.getYear())
											{
												logger.info(" finally empAllwMpg.getEmpDeducAmount() "+ empAllwMpg.getEmpDeducAmount()+" for deduc "+empAllwMpg.getHrPayDeducTypeMst().getDeducName());
													empDeducDtls.setEmpDeducAmount(empAllwMpg.getEmpDeducAmount());
													empDeducDtls.setHrPayDeducTypeMst(empAllwMpg.getHrPayDeducTypeMst());
													empDeducDtls.setHrEisEmpMst(empAllwMpg.getHrEisEmpMst());
													empDeducDtls.setEmpCurrentStatus(empAllwMpg.getEmpCurrentStatus());
											 
													empDeducDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
													empDeducDtls.setOrgUserMstByUpdatedBy(orgUserMst);
													empDeducDtls.setUpdatedDate(sysdate);
											
													empDuducDtlsDAO.update(empDeducDtls);
													
													if(hrPayEdpCompoMpg != null && hrpaPaybillDump!=null)
													{
														String edpCode = hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
														String methodName = "setDeduc"+edpCode;
														Class paybillClass = hrpaPaybillDump.getClass();
														Method method = paybillClass.getMethod(methodName, double.class);
														method.invoke(hrpaPaybillDump, empAllwMpg.getEmpDeducAmount());
														
													}
											}
										else
										{
											logger.info("inside the insert ");
											logger.info(" finally empAllwMpg.getEmpDeducAmount() "+ empAllwMpg.getEmpDeducAmount()+" for deduc "+empAllwMpg.getHrPayDeducTypeMst().getDeducName());
											IdGenerator IdGen = new IdGenerator();
							        		Long deducCode =IdGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objectArgs);
							        		empAllwMpg.setDeducDtlsCode(deducCode);
							        		        		
							        		 empAllwMpg.setCreatedDate(sysdate);						
							    			 empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
							    			 empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
							    			 empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
							    			 empAllwMpg.setUpdatedDate(sysdate);
							    			 empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
							    			 empAllwMpg.setCmnLocationMst(cmnLocationMst);		
							    			 empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
							    			 empAllwMpg.setTrnCounter(new Integer(1));
							    			  
											logger.info("INSIDE CREATE");
											empDuducDtlsDAO.create(empAllwMpg);
											 
											
										}
	
										}
										logger.info("Updated.");
										objectArgs.put("msg", "Record Updated Successfully");
									}
									else
									{
									 	logger.info("inside the insert ");
										IdGenerator IdGen = new IdGenerator();
						        		Long deducCode =IdGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objectArgs);
						        		empAllwMpg.setDeducDtlsCode(deducCode);
						        		        		
						        		 empAllwMpg.setCreatedDate(sysdate);						
						    			 empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
						    			 empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
						    			 empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
						    			 empAllwMpg.setUpdatedDate(sysdate);
						    			 empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
						    			 empAllwMpg.setCmnLocationMst(cmnLocationMst);		
						    			 empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
						    			 empAllwMpg.setTrnCounter(new Integer(1));
										logger.info("INSIDE CREATE");
										empDuducDtlsDAO.create(empAllwMpg);
										objectArgs.put("msg", "Record Inserted Successfully");
										
										if(hrPayEdpCompoMpg != null && hrpaPaybillDump!=null)
										{
											String edpCode = hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
											String methodName = "setDeduc"+edpCode;
											Class paybillClass = hrpaPaybillDump.getClass();
											Method method = paybillClass.getMethod(methodName, double.class);
											method.invoke(hrpaPaybillDump, empAllwMpg.getEmpDeducAmount());
											
										}
								}
								
		    			 	
		    			 }
		    			 else if(editFromVO!=null && editFromVO.equals("N"))
		    			 {
		    				 
		    				 if(empAllwMpg.getEmpDeducAmount()!=-1)
							 {
		    					 	logger.info("inside the insert ");
									IdGenerator IdGen = new IdGenerator();
					        		Long deducCode =IdGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objectArgs);
					        		empAllwMpg.setDeducDtlsCode(deducCode);
					        		        		
					        		 empAllwMpg.setCreatedDate(sysdate);						
					    			 empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
					    			 empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
					    			 empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
					    			 empAllwMpg.setUpdatedDate(sysdate);
					    			 empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
					    			 empAllwMpg.setCmnLocationMst(cmnLocationMst);		
					    			 empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
					    			 empAllwMpg.setTrnCounter(new Integer(1));
									logger.info("INSIDE CREATE");
									empDuducDtlsDAO.create(empAllwMpg);
									objectArgs.put("msg", "Record Inserted Successfully");
									
									if(hrPayEdpCompoMpg != null && hrpaPaybillDump!=null)
									{
										String edpCode = hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
										String methodName = "setDeduc"+edpCode;
										Class paybillClass = hrpaPaybillDump.getClass();
										Method method = paybillClass.getMethod(methodName, double.class);
										method.invoke(hrpaPaybillDump, empAllwMpg.getEmpDeducAmount());
										
									}
							 }
					 	}
					}
					//List actionList = empDuducDtlsDAO.getDeductionType();
				 
					
				 }
					//Modified by Manish
					OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
					logger.info("The info empid is:-"+empid);
					HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
					hrEisOtherDtls=otherdtlsDao.getOtherData(empid);
					long payCommonissionId = 2500340;//By default the commission id is 5th pay commission(For fixed and contractual employee)
					if(hrEisOtherDtls.getHrEisSgdMpg()!=null)
						payCommonissionId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId();
					//Ended by Manish
				 List actionList = empDuducDtlsDAO.getDeductionTypeDeducDtls(payCommonissionId,locId,empid);
				  
				 
		            List deducNamesFromMpg = empDuducDtlsDAO.getAllDeductionTypeFromMpg(empid,sysDate.getMonth()+1,sysDate.getYear()+1900);
		            List empList = empDAO.getEmpIdData(empid, cmnLanguageMst); 				
					objectArgs.put("actionList", actionList);			
					objectArgs.put("deducNamesFromMpg", deducNamesFromMpg);	
					objectArgs.put("deducMpgSize",deducNamesFromMpg.size());
					objectArgs.put("empList",empList);
					objectArgs.put("deducNamesFromExpr", exprMpg);	
					/*resultObject.setResultCode(ErrorConstants.SUCCESS);			
					resultObject.setResultValue(objectArgs);
					resultObject.setViewName("empDeducDtlsView");*/
				 
				 
				 
			}
			else
			{
				
				logger.info("inside else part of batch update in insertDeductiondtls");
				HrPayDeductionDtls empAllwMpg= (HrPayDeductionDtls)objectArgs.get("empDeducDtls");
			long empid=0;
			if(empAllwMpg.getHrEisEmpMst().getEmpId() != 0)
			   empid = empAllwMpg.getHrEisEmpMst().getEmpId();
		
			 logger.info(" emp id in service "+empid);
			 DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			 DeducExpMasterDAOImpl deducExprDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
															
			 EmpInfoDAOImpl empDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			 List empList = empDAO.getEmpIdData(empid, cmnLanguageMst);
			 List exprMpg = deducExprDAO.getDeducExpMasterData(langId);	
			 
			 String editFromVO = objectArgs.get("edit").toString();
			 
			 long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
     		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
     		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
     		
     		long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
 	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
 			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
 			
 			 locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
 			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
 			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	    	         	           			      
 			
 			 long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
 			 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
 			 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	
 			 
 			 Date sysdate = new Date(); 
			 
			 if(editFromVO!=null && editFromVO.equals("Y"))
			{
				logger.info("INSIDE UPDATE ");
				logger.info("INSIDE UPDATE of else batch insertdeductiondtls");
				if(empAllwMpg.getDeducDtlsCode()!=0)
				{
					HrPayDeductionDtls empDeducDtls = empDuducDtlsDAO.read(empAllwMpg.getDeducDtlsCode());	
					if(empDeducDtls.getMonth()>0 && empDeducDtls.getYear()>0 && empDeducDtls.getMonth() ==  empAllwMpg.getMonth() && empAllwMpg.getYear()==empDeducDtls.getYear())
					{						
						empDeducDtls.setEmpDeducAmount(empAllwMpg.getEmpDeducAmount());
						empDeducDtls.setHrPayDeducTypeMst(empAllwMpg.getHrPayDeducTypeMst());
						empDeducDtls.setHrEisEmpMst(empAllwMpg.getHrEisEmpMst());
						empDeducDtls.setEmpCurrentStatus(empAllwMpg.getEmpCurrentStatus());
					 
						empDeducDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						empDeducDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						empDeducDtls.setUpdatedDate(sysdate);
					 
						empDuducDtlsDAO.update(empDeducDtls);
					}
					else
					{
						IdGenerator IdGen = new IdGenerator();
		        		Long deducCode =IdGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objectArgs);
		        		empAllwMpg.setDeducDtlsCode(deducCode);
		        		
		    			 empAllwMpg.setCreatedDate(sysdate);						
		    			 empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
		    			 empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
		    			 empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
		    			 empAllwMpg.setUpdatedDate(sysdate);
		    			 empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
		    			 empAllwMpg.setCmnLocationMst(cmnLocationMst);		
		    			 empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
		    			 empAllwMpg.setTrnCounter(new Integer(1));
						logger.info("INSIDE CREATE");
						logger.info("INSIDE CREATE of else batch insertdeductiondtls");
						empDuducDtlsDAO.create(empAllwMpg);
					}
					objectArgs.put("msg", "Record Updated Successfully");
				}
				else
				{
					IdGenerator IdGen = new IdGenerator();
	        		Long deducCode =IdGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objectArgs);
	        		empAllwMpg.setDeducDtlsCode(deducCode);
	        		
	    			 empAllwMpg.setCreatedDate(sysdate);						
	    			 empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
	    			 empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
	    			 empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
	    			 empAllwMpg.setUpdatedDate(sysdate);
	    			 empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
	    			 empAllwMpg.setCmnLocationMst(cmnLocationMst);		
	    			 empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
	    			 empAllwMpg.setTrnCounter(new Integer(1));
	    			 logger.info("INSIDE CREATE");
	    			 logger.info("INSIDE CREATE of else batch insertdeductiondtls");
	    			 empDuducDtlsDAO.create(empAllwMpg);
				}
				
			}
			//else
			 else if(editFromVO!=null && editFromVO.equals("N"))
			{
				IdGenerator IdGen = new IdGenerator();
        		Long deducCode =IdGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objectArgs);
        		empAllwMpg.setDeducDtlsCode(deducCode);
        		
    			 empAllwMpg.setCreatedDate(sysdate);						
    			 empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
    			 empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
    			 empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
    			 empAllwMpg.setUpdatedDate(sysdate);
    			 empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
    			 empAllwMpg.setCmnLocationMst(cmnLocationMst);		
    			 empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
    			 empAllwMpg.setTrnCounter(new Integer(1));
				logger.info("INSIDE CREATE");
				logger.info("INSIDE CREATE of else batch insertdeductiondtls");
				empDuducDtlsDAO.create(empAllwMpg);
				objectArgs.put("msg", "Record Inserted Successfully");
			}
			 else
				{
					throw new NullPointerException();
				}
			
			//List actionList = empDuducDtlsDAO.getDeductionType();
				//Modified by Manish
				OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
				logger.info("The info empid is:-"+empid);
				HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
				hrEisOtherDtls=otherdtlsDao.getOtherData(empid);
				long payCommonissionId = 2500340,empId = 0 ;//By default the commission id is 5th pay commission(For fixed and contractual employee)
				if(hrEisOtherDtls.getHrEisSgdMpg()!=null)
				{
					payCommonissionId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId();
					empId = hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId();
				}
				//Ended by Manish
			
			 List actionList = empDuducDtlsDAO.getDeductionTypeDeducDtls(payCommonissionId,locId,empId);
            List deducNamesFromMpg = empDuducDtlsDAO.getAllDeductionTypeFromMpg(empid,sysdate.getMonth()+1,sysdate.getYear()+1900);
            				
			objectArgs.put("actionList", actionList);			
			objectArgs.put("deducNamesFromMpg", deducNamesFromMpg);	
			objectArgs.put("deducMpgSize",deducNamesFromMpg.size());
			objectArgs.put("empList",empList);
			objectArgs.put("deducNamesFromExpr", exprMpg);			
			/*resultObject.setResultCode(ErrorConstants.SUCCESS);			
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("empDeducDtlsView");*/
			//logger.info("INSETED SUCCESSFULLY");
		
		}
			logger.info("U are in Insert-update Mode");
			objectArgs.put("MESSAGECODE",300006);
			 resultObject.setResultCode(ErrorConstants.SUCCESS);			
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("empDeducDtlsView"); 
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...");
			 logger.error("Error is: "+ ne.getMessage());
			 Map errorMap = new HashMap();
			 errorMap.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultCode(-1);
			 resultObject.setResultValue(errorMap);
			 resultObject.setViewName("errorInsert");
		}
		catch(Exception e)
		{			
			logger.info("Null Pointer Exception Ocuures...");
			 logger.error("Error is: "+ e.getMessage());
			 Map errorMap = new HashMap();
			 errorMap.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultCode(-1);
			 resultObject.setResultValue(errorMap);
			 resultObject.setViewName("errorInsert");
		}
	
		return resultObject;
	}
	
	

}
