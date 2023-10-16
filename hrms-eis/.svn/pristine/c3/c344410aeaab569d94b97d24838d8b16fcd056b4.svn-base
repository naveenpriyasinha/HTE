package com.tcs.sgv.eis.service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.util.Calendar;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.RecoveryChallanDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanRecoveryChallan;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.LoanCustomVO;
import com.tcs.sgv.eis.valueobject.RecoveryChallanCustomVO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.loan.dao.LoanAdvMstDAOImpl;

public class RecoveryThroughChallanService extends ServiceImpl {
	
Log logger = LogFactory.getLog( getClass() );
	
	int msg=0;
	public ResultObject getLoanValue(Map objectArgs)
	
	{
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("inside getLoanValue------------>");
		logger.info("====> inside getLoanValue ");
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
           	EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
            LoanAdvMstDAOImpl loanDAO= new LoanAdvMstDAOImpl(HrLoanAdvMst.class,serv.getSessionFactory());
            
//    		added by Ankit Bhatt for merging Screens
            Map voToService = (Map)objectArgs.get("voToServiceMap");
            
            PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
            OrgDesignationMstDaoImpl designationMstDaoImpl = new OrgDesignationMstDaoImpl(OrgDesignationMst.class,serv.getSessionFactory());
            String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};
            
    		String empAllRec="";
    		if(voToService.get("empAllRec")!=null)
    		empAllRec = voToService.get("empAllRec").toString();
    		
    		//ended by Ankit Bhatt
    		String empName = "";
			if((voToService.get("Employee_srchNameText_EmpLoanSearch")!=null&&!voToService.get("Employee_srchNameText_EmpLoanSearch").equals("")))
				empName=(String)voToService.get("Employee_srchNameText_EmpLoanSearch").toString();
    		////////// for update pay bill
    		String updatePaybillFlg="";
    		int paybillMonth=0;
    		int paybillYear=0;

    		if (voToService.get("updatePaybillFlg") != null)
    			updatePaybillFlg = voToService.get("updatePaybillFlg").toString();
    	    if(voToService.get("paybillMonth")!=null)
    	    	paybillMonth=Integer.parseInt(voToService.get("paybillMonth").toString());
    	    if(voToService.get("paybillYear")!=null)
    	    	paybillYear=Integer.parseInt(voToService.get("paybillYear").toString());
    		
    		objectArgs.put("updatePaybillFlg",updatePaybillFlg);
    	    objectArgs.put("paybillMonth", paybillMonth);
    	    objectArgs.put("paybillYear", paybillYear);
    		
    		logger.info("*******************updatePaybillFlg*****************"+updatePaybillFlg);
    	    
           	//Added by mrugesh
			Map loginDetails=(Map)objectArgs.get("baseLoginMap");
	    	long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
	    	long languageId=1;	
	    	CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(languageId);
	    	//Ended by mrugesh
			// Added by Urvin
			//long locationId=Long.parseLong(loginDetails.get("locationId").toString());
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetails.get("locationVO");
	   		   String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
	   		   
	   		// End   	
			String editFlag = (String)voToService.get("edit");
			//by manoj for employee search
			String empIdStr = (String)voToService.get("Employee_ID_EmpLoanSearch");
			logger.info("the emp id from search employee "+empIdStr);
			//end by manoj for employee search			
           //if(request.getParameter("edit")!= null && request.getParameter("edit").equals("Y"))
			
			//added by ravysh
			String FromBasicDtlsNew = voToService.get("FromBasicDtlsNew")!=null?(String)voToService.get("FromBasicDtlsNew"):"";
			String otherId = voToService.get("otherId")!=null?(String)voToService.get("otherId"):"";
			logger.info("manish editFlag is "+editFlag);
			if(editFlag != null && editFlag.equals("Y"))
            {
				RecoveryChallanCustomVO recoveryChallanCustomVO =new RecoveryChallanCustomVO();
				
				String loanId=(String)voToService.get("empLoanId");
            	long empLoanId=Long.parseLong(loanId);
            	HrLoanEmpDtls actionList = empLoanDAO.getEmpLoanDetail(empLoanId);
            	logger.info("empLoanId is "+empLoanId);
            	
            	
            	GenericDaoHibernateImpl princiGImpl = null;
            	princiGImpl = new GenericDaoHibernateImpl(HrLoanEmpPrinRecoverDtls.class);
            	princiGImpl.setSessionFactory(serv.getSessionFactory());
            	
            	GenericDaoHibernateImpl intGImpl = null;
            	intGImpl = new GenericDaoHibernateImpl(HrLoanEmpIntRecoverDtls.class);
            	intGImpl.setSessionFactory(serv.getSessionFactory());
            	
            	long principalRecoveredAmt=0;
            	long pricipalRecoveredInt=0;
            	long intRecoveredAmt=0;
            	long intRecoveredInt=0;
            	
            	List loanPrinciRecoverList = princiGImpl.getListByColumnAndValue("hrLoanEmpDtls.empLoanId",empLoanId );
            	List loanIntRecoverList = intGImpl.getListByColumnAndValue("hrLoanEmpDtls.empLoanId",empLoanId );
            	
            	
            	logger.info("loanPrinciRecoverList "+loanPrinciRecoverList);

            	
            	if(loanPrinciRecoverList!=null && loanPrinciRecoverList.size()>0 && actionList.getLoanPrinInstNo()!=0)
            	{
            		HrLoanEmpPrinRecoverDtls  hrLoanEmpPrinRecoverDtls = (HrLoanEmpPrinRecoverDtls)loanPrinciRecoverList.get(0);
            		
            		principalRecoveredAmt=hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt();
            		pricipalRecoveredInt=hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst();
            		
            		recoveryChallanCustomVO.setLoanAmount(actionList.getLoanPrinAmt());
            		recoveryChallanCustomVO.setTotalInstallments(actionList.getLoanPrinInstNo());
            		recoveryChallanCustomVO.setInstallmentAmount(actionList.getLoanPrinEmiAmt());
            		recoveryChallanCustomVO.setOddInstallAmount(actionList.getLoanOddinstAmt());
            		recoveryChallanCustomVO.setOddInstallNo(actionList.getLoanOddinstno());
            		recoveryChallanCustomVO.setRecoveryType("Principal");             	
            		recoveryChallanCustomVO.setLastInstallRecoverd(pricipalRecoveredInt);
            		recoveryChallanCustomVO.setOutstadingAmount(actionList.getLoanPrinAmt()-principalRecoveredAmt);
            		
            	}
            	
            	if(loanIntRecoverList!=null && loanIntRecoverList.size()>0 && actionList.getLoanIntInstNo() !=0 )
            	{
            		
            		HrLoanEmpIntRecoverDtls  hrLoanEmpIntRecoverDtls = (HrLoanEmpIntRecoverDtls)loanIntRecoverList.get(0);
            		
            		intRecoveredAmt=hrLoanEmpIntRecoverDtls.getTotalRecoveredInt();
            		intRecoveredInt=hrLoanEmpIntRecoverDtls.getTotalRecoveredIntInst();
            			
            		recoveryChallanCustomVO.setLoanAmount(actionList.getLoanInterestAmt());
            		recoveryChallanCustomVO.setTotalInstallments(actionList.getLoanIntInstNo());
            		recoveryChallanCustomVO.setInstallmentAmount(actionList.getLoanIntEmiAmt());
            		recoveryChallanCustomVO.setOddInstallAmount(actionList.getLoanOddinstAmt());
            		recoveryChallanCustomVO.setOddInstallNo(actionList.getLoanOddinstno());
            		recoveryChallanCustomVO.setRecoveryType("Interest");
            		recoveryChallanCustomVO.setLastInstallRecoverd(intRecoveredInt);
            		recoveryChallanCustomVO.setOutstadingAmount(actionList.getLoanInterestAmt()-intRecoveredAmt);
                	
            	}
            	
            	recoveryChallanCustomVO.setLoanName(actionList.getHrLoanAdvMst().getLoanAdvName());
            	
            	Date date= new Date();
            	long year= date.getYear()+1900;
            	String currPayMonth=monthName[date.getMonth()]+"-"+year;
            	recoveryChallanCustomVO.setCurrPayMonth(currPayMonth);
            	recoveryChallanCustomVO.setEmpName(actionList.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+actionList.getHrEisEmpMst().getOrgEmpMst().getEmpMname()+" "+actionList.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
            	String dsgnId=payBillDAOImpl.getdesignationIdEployeeId(String.valueOf(actionList.getHrEisEmpMst().getOrgEmpMst().getEmpId()));
            	OrgDesignationMst dsgnMst= designationMstDaoImpl.read(Long.valueOf(dsgnId));
            	recoveryChallanCustomVO.setDesignation(dsgnMst.getDsgnName());
            	logger.info("principalRecoveredAmt "+principalRecoveredAmt);
            	logger.info("pricipalRecoveredInt "+pricipalRecoveredInt);
            	logger.info("intRecoveredAmt "+intRecoveredAmt);
            	logger.info("intRecoveredInt "+intRecoveredInt);
            	
            	//for lazy initialization dont remove this logger
            	logger.info("the emp name is "+actionList.getHrEisEmpMst().getOrgEmpMst().getEmpFname());
            	logger.info("the emp grade is "+actionList.getHrEisEmpMst().getOrgEmpMst().getOrgGradeMst().getGradeName());
            	
            	logger.info("actionList **********"+actionList.getVoucherDate());
            	
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
				
				  
				  
				  
				  SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
				  SimpleDateFormat ssdf = new SimpleDateFormat("yyyy-MM-dd");
				  Calendar cal=Calendar.getInstance();
				  cal.set(Calendar.YEAR, date.getYear()+1900);
					cal.set(Calendar.MONTH,date.getMonth());
					cal.set(Calendar.DAY_OF_MONTH, date.getDate());
				  String str_date= sd.format(cal.getTime());
				/*  asOn = (Date)formatter.parse(str_date);  
				  logger.info(" is "+str_date);
				  logger.info("sdf as on is "+asOn);*/
				  Date loanDate= actionList.getLoanDate();
            	String sanDate=ssdf.format(loanDate);
	            	
             	recoveryChallanCustomVO.setAsOnDate(str_date);
             	
             	String dateformated = sanDate;
    			String[] ddDatesFormated = dateformated.split("-");
    			
    			StringBuffer dateBuffer  = new StringBuffer();
    			if(ddDatesFormated != null && ddDatesFormated.length > 2){
    				dateBuffer.append(ddDatesFormated[2]).append("/").append(ddDatesFormated[1]).append("/").append(ddDatesFormated[0]);
    			}else{
    				dateBuffer.append("-");
    			}
            	recoveryChallanCustomVO.setSanctionOrderDate(String.valueOf(dateBuffer));
            
            	Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, loanDate.getYear() + 1900);
				c.set(Calendar.MONTH, loanDate.getMonth());
				c.set(Calendar.DAY_OF_MONTH, loanDate.getDate());
				c.add(Calendar.MONTH, (int) (actionList.getLoanIntInstNo() +actionList.getLoanPrinInstNo()));
				Date endDate = c.getTime();
            	
            	objectArgs.put("lloanDate", sd.format(loanDate));
            	objectArgs.put("lloanEndDate", sd.format(endDate));
            	
				//logger.info("voucher date" + actionList.getVoucherDate());
				//String vd="";
				if(actionList.getVoucherDate()!=null)
				{
					String vd  = actionList.getVoucherDate().toString();
				String voucherDate= sdf.format(sdfParse.parse(vd));
				objectArgs.put("voucherDate", voucherDate);
				}
				
            	objectArgs.put("actionList", actionList);
            	
            	objectArgs.put("principalRecoveredAmt", principalRecoveredAmt);
            	objectArgs.put("pricipalRecoveredInt", pricipalRecoveredInt);
            	objectArgs.put("intRecoveredAmt", intRecoveredAmt);
            	objectArgs.put("intRecoveredInt", intRecoveredInt);
            	//added by ravysh
            	objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
            	objectArgs.put("otherId", otherId);
            	
            	
            	HrEisEmpMst empMstVO= (HrEisEmpMst)objectArgs.get("empMstVO");
                EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
                List<HrEisEmpMst> empList =  new ArrayList();//empinfoDao.getAllEmpData(cmnLanguageMst);
                
                
                
                
                objectArgs.put("empList", empList);
                logger.info("The size of Employee list is------->"+empList.size());
                
                /*HrLoanAdvMst loanMstVO = (HrLoanAdvMst) objectArgs.get("loanMstVO");
                LoanAdvMstDAOImpl loanDAO= new LoanAdvMstDAOImpl(HrLoanAdvMst.class,serv.getSessionFactory());*/
                List <HrLoanAdvMst> loanList = loanDAO.getAllLoanAdvMasterData(languageId);
               
                
                
                
                
                              
                objectArgs.put("loanList", loanList);
                logger.info("The size of Loan List is------->"+loanList.size());
            	//added by manish
                
             
                objectArgs.put("customVO",recoveryChallanCustomVO);
                objectArgs.put("loanEmpId",empLoanId);
                
            	//ended by manish
                resultObject.setResultCode(ErrorConstants.SUCCESS);
		        
//		      added by Ankit Bhatt for merging screens
		        if(empAllRec.equalsIgnoreCase("y"))
		        {
		        	objectArgs.put("empAllRec","true");
		        	objectArgs.put("empId",actionList.getHrEisEmpMst().getEmpId());
		        	resultObject.setViewName("RecoveryChallanEdit");
		        }
		        else{
		        	//ended by Ankit Bhatt
		        	
		        	//added by ravysh
		        if(FromBasicDtlsNew.equals("YES"))
		        	resultObject.setViewName("RecoveryChallanEdit");
		        else
		        resultObject.setViewName("RecoveryChallanEdit");
		        }
		        resultObject.setResultValue(objectArgs);
            	
            }//by manoj for employee search
			else if(empIdStr!=null && !empIdStr.equals(""))
			{
				long empId = Long.parseLong(empIdStr);
				//HrEisEmpMst hrEisEmpMst =(HrEisEmpMst) empinfodao.read(empId);
				EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				//OrgEmpMst orgEmpMst=orgEmpDao.getEngGujEmployee(hrEisEmpMst.getOrgEmpMst(), languageId);
				logger.info("empId is:-"+empId);
				OrgEmpMst orgEmpMst=orgEmpDao.read(empId);
				
				List  <HrLoanEmpDtls> actionList = new ArrayList();//empLoanDAO.getEmpLoanData(orgEmpMst);
				
				
				/*long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/
				
		      	/*List userList =empinfodao.getUserListByLogin(langId,userId);
				List <OrgUserMst> orgUserList = new ArrayList();
		      	for (Iterator iter = userList.iterator(); iter.hasNext();)
		        {			 
			    Object[] row = (Object[])iter.next();	
		   		OrgUserMst userMst = new OrgUserMst();
		  		String id=row[0].toString();
		  		userMst.setUserId(Long.parseLong(id));
		  		orgUserList.add(userMst);
		        }*/
				
				//Fetch loan data for showing in basic details screen
				//Number of records found = fetchedLoanList
				List fetchedLoanList = empLoanDAO.getEmployeeLoanData(locationCode, orgEmpMst.getEmpId(), langId); 
				logger.info("basic dtls loan dtls list size is: " + fetchedLoanList.size());

				ArrayList<LoanCustomVO> customLoanVoList = new ArrayList<LoanCustomVO>();
				int loanListSize = fetchedLoanList != null ? fetchedLoanList.size() : 0;
				for( int i=0; i < loanListSize; i++) {
					
					LoanCustomVO customVo = new LoanCustomVO();
					//Object[] obj = (Object[]);
					//logger.info("i = " +i +"obj[] size: "+obj.length);

					//There will be three type of record within fetchedLoanList

					HrLoanEmpDtls objEmpLoanDtls = new HrLoanEmpDtls();
					//logger.info("is obj class compatible? : " +  obj[0].getClass().isInstance(objEmpLoanDtls));
					objEmpLoanDtls = (HrLoanEmpDtls) fetchedLoanList.get(i);
					//HrLoanEmpPrinRecoverDtls empPrinRecoverDtls =(HrLoanEmpPrinRecoverDtls)obj[6];
					HrLoanEmpPrinRecoverDtls empPrinRecoverDtls = (HrLoanEmpPrinRecoverDtls)objEmpLoanDtls.getHrLoanEmpPrinRecoverDtlses().iterator().next();
					//HrLoanEmpIntRecoverDtls empIntRecoverDtls = (HrLoanEmpIntRecoverDtls)obj[7];
					HrLoanEmpIntRecoverDtls empIntRecoverDtls = (HrLoanEmpIntRecoverDtls) objEmpLoanDtls.getHrLoanEmpIntRecoverDtlses().iterator().next();
					
					//logger.info("EmpLoanId is: " +objEmpLoanDtls.getEmpLoanId());
					customVo.setEmpLoanId( objEmpLoanDtls.getEmpLoanId() );
					
					//logger.info("loan name is: " +objEmpLoanDtls.getHrLoanAdvMst().getLoanAdvName() );
					customVo.setLoanName( objEmpLoanDtls.getHrLoanAdvMst().getLoanAdvName() );
					
					
					
					
					//logger.info("loan EMI amnt: " +objEmpLoanDtls.getLoanPrinEmiAmt() );
					customVo.setLoanPrinEmiAmt(objEmpLoanDtls.getLoanPrinEmiAmt());
					
					//logger.info("loand int emi amt: " + objEmpLoanDtls.getLoanIntEmiAmt());
					customVo.setLoanIntEmiAmt( (objEmpLoanDtls.getLoanIntEmiAmt()) );
					
					//logger.info("outstanding principle amt: " +Long.parseLong(obj[1].toString()));
					customVo.setOutstandingPrincipleAmt( objEmpLoanDtls.getLoanPrinAmt() - empPrinRecoverDtls.getTotalRecoveredAmt() );
					
					//logger.info("outstanding Interest amt: " +Long.parseLong(obj[2].toString()));
					customVo.setOutstandingInterestAmt( objEmpLoanDtls.getLoanInterestAmt() - empIntRecoverDtls.getTotalRecoveredInt() );
					
					customVo.setLoanTypeId(objEmpLoanDtls.getHrLoanAdvMst().getLoanAdvId());
					
					//05 jan 2011
					//update the values if the processed installment is the odd installment
					//for loans
					logger.info(" update the values if the processed installment is the odd installment before if");
					if(objEmpLoanDtls.getLoanPrinAmt()!=0)//it is loan
					{
						logger.info(" objEmpLoanDtls.getLoanPrinAmt()!=0");
						//HrLoanEmpPrinRecoverDtls recovery = (HrLoanEmpPrinRecoverDtls)objEmpLoanDtls.getHrLoanEmpPrinRecoverDtlses().iterator().next();
						if(empPrinRecoverDtls!= null && (empPrinRecoverDtls.getTotalRecoveredInst()+1L == objEmpLoanDtls.getLoanOddinstno()))
							customVo.setLoanPrinEmiAmt(objEmpLoanDtls.getLoanOddinstAmt());
							
					}
					if(objEmpLoanDtls.getLoanIntEmiAmt() !=0)
					{
					
					logger.info(" objEmpLoanDtls.getLoanIntEmiAmt() !=0");
					//HrLoanEmpIntRecoverDtls recovery = (HrLoanEmpIntRecoverDtls) objEmpLoanDtls.getHrLoanEmpIntRecoverDtlses().iterator().next();
					if(empIntRecoverDtls!=null && (empIntRecoverDtls.getTotalRecoveredIntInst()+1L == objEmpLoanDtls.getLoanOddinstno()))
						customVo.setLoanIntEmiAmt(objEmpLoanDtls.getLoanOddinstAmt());
					
					}
					customLoanVoList.add(customVo);
				}//end for
				
				objectArgs.put("otherDtlsLoan", customLoanVoList);
				objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
				objectArgs.put("otherId", otherId);
				
				actionList=empLoanDAO.getEmpLoanData(locationCode,orgEmpMst.getEmpId(),langId);
				
				logger.info("the size of actionList for the emp "+orgEmpMst.getEmpId()+" "+orgEmpMst.getEmpFname()+" is "+actionList.size());
				HrLoanAdvMst hrLoanAdvMst= new  HrLoanAdvMst();
    			LoanAdvMstDAOImpl loanMstDao = new LoanAdvMstDAOImpl(HrLoanAdvMst.class,serv.getSessionFactory());
				
            	
            	double loanTotal=0;
				
				String x="";
				for (int i=0;i<actionList.size();i++)
	            {
					HrLoanEmpDtls empLoan = new HrLoanEmpDtls();
					empLoan = actionList.get(i);
					if(empLoan != null)
					{
						if(empLoan.getLoanPrinAmt()!=0)//it is loan
						{
							logger.info(" objEmpLoanDtls.getLoanPrinAmt()!=0");
							HrLoanEmpPrinRecoverDtls recovery = (HrLoanEmpPrinRecoverDtls)empLoan.getHrLoanEmpPrinRecoverDtlses().iterator().next();
							if(recovery!= null && (recovery.getTotalRecoveredInst()+1L == empLoan.getLoanOddinstno()))
								loanTotal+=empLoan.getLoanOddinstAmt();
							else
								loanTotal+=empLoan.getLoanPrinEmiAmt();
								
						}
						if(empLoan.getLoanIntEmiAmt() !=0)
						{
						
						logger.info(" objEmpLoanDtls.getLoanIntEmiAmt() !=0");
						HrLoanEmpIntRecoverDtls recovery = (HrLoanEmpIntRecoverDtls) empLoan.getHrLoanEmpIntRecoverDtlses().iterator().next();
						if(recovery!=null && (recovery.getTotalRecoveredIntInst()+1L == empLoan.getLoanOddinstno()))
							loanTotal+=empLoan.getLoanOddinstAmt();
						else 
							loanTotal+=empLoan.getLoanIntEmiAmt();
						
						}
						
					}
					String tempBuffer="";
					if (empLoan.getLoanPrinAmt()!=0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
	                    logger.info( "Formatted as currency: " +currencyFormatter.format(empLoan.getLoanPrinAmt()) );
	                    tempBuffer=currencyFormatter.format(empLoan.getLoanPrinAmt()).replace("$", "");
	                    empLoan.setCurrencyloanPrinAmt(tempBuffer.replace(".00", ""));
	                    logger.info("the value of the emploan is ::"+empLoan.getCurrencyloanPrinAmt());
					}
					
					if (empLoan.getLoanInterestAmt()!=0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
	                    logger.info( "Formatted as currency: " +currencyFormatter.format(empLoan.getLoanInterestAmt()) );
	                    tempBuffer=currencyFormatter.format(empLoan.getLoanInterestAmt()).replace("$", "");
	                    empLoan.setCurrencyloanInterestAmt(tempBuffer.replace(".00", ""));
	                    logger.info("the value of the emploan is ::"+empLoan.getCurrencyloanInterestAmt());
					}
					x = empLoan.getHrEisEmpMst()+" "+empLoan.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+empLoan.getHrLoanAdvMst().getLoanAdvName()+" "+empLoan.getLoanAccountNo()+" "+empLoan.getLoanPrinEmiAmt()+" "+empLoan.getLoanIntEmiAmt()+" "+empLoan.getLoanInterestAmt()+" "+empLoan.getLoanIntInstNo()+" "+empLoan.getLoanPrinAmt()+" "+empLoan.getLoanPrinInstNo();          
	            }
				logger.info("one record is "+x);
				logger.info("The Size of List is:-"+actionList.size());
				
				objectArgs.put("actionList", actionList);
				objectArgs.put("loanList", actionList); 
				objectArgs.put("empId", empId);
				objectArgs.put("loanTotal", loanTotal);
				logger.info("loan Total "+loanTotal);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				
//				added by Ankit Bhatt for merging screens
		        if(empAllRec.equalsIgnoreCase("Y"))
		        {
		        	objectArgs.put("empId", empId);
		        	objectArgs.put("empAllRec","true");
		        	resultObject.setViewName("RecoveryThroughMaster");
		        }
		        else{
		        	//ended by Ankit Bhatt
		        	if(FromBasicDtlsNew.equals("YES"))
			        	resultObject.setViewName("RecoveryThroughMaster");
			        else
				resultObject.setViewName("RecoveryThroughMaster");
		       
		        }
		        resultObject.setResultValue(objectArgs);
			}
			//end by manoj for employee search
			else
			{
				List  <HrLoanEmpDtls> actionList = new ArrayList();//empLoanDAO.getLoanData();
				/*Map loginDetailsMap =(Map)objectArgs.get("baseLoginMap");	
			    long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/
				
/*		      	List userList =empinfodao.getUserListByLogin(langId,userId);
				List <OrgUserMst> orgUserList = new ArrayList();
		      	for (Iterator iter = userList.iterator(); iter.hasNext();)
		        {			 
			    Object[] row = (Object[])iter.next();	
		   		OrgUserMst userMst = new OrgUserMst();
		  		String id=row[0].toString();
		  		userMst.setUserId(Long.parseLong(id));
		  		orgUserList.add(userMst);
		        }*/
		      	
				actionList=empLoanDAO.getEmpLoanData(locationCode,0,langId);
				objectArgs.put("ViewFlag","True");	
				
            	
            	String x="";
				
				for (int i=0;i<actionList.size();i++)
	            {
					HrLoanEmpDtls empLoan = new HrLoanEmpDtls();
					empLoan = actionList.get(i);
					String tempBuffer="";
					if (empLoan.getLoanPrinAmt()!=0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
	                    tempBuffer=currencyFormatter.format(empLoan.getLoanPrinAmt()).replace("$", "");
	                    empLoan.setCurrencyloanPrinAmt(tempBuffer.replace(".00", ""));
	                    logger.info("the value of the emploan is ::"+empLoan.getCurrencyloanPrinAmt());
					}
					
					if (empLoan.getLoanInterestAmt()!=0)
					{
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
	                    tempBuffer=currencyFormatter.format(empLoan.getLoanInterestAmt()).replace("$", "");
	                    empLoan.setCurrencyloanInterestAmt(tempBuffer.replace(".00", ""));
	                    logger.info("the value of the emploan is ::"+empLoan.getCurrencyloanInterestAmt());
					}
	            	x = empLoan.getHrEisEmpMst()+" "+empLoan.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+empLoan.getHrLoanAdvMst().getLoanAdvName()+" "+empLoan.getLoanAccountNo()+" "+empLoan.getLoanPrinEmiAmt()+" "+empLoan.getLoanIntEmiAmt()+" "+empLoan.getLoanInterestAmt()+" "+empLoan.getLoanIntInstNo()+" "+empLoan.getLoanPrinAmt()+" "+empLoan.getLoanPrinInstNo();          
	            }
				logger.info("one record is "+x);
				logger.info("The Size of List is:-"+actionList.size());
				
				
				objectArgs.put("actionList", actionList);
				objectArgs.put("empId", "0");
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("RecoveryThroughMaster");
				
			}
			objectArgs.put("empName",empName);
			resultObject.setResultValue(objectArgs);
		}
		catch(Exception e){
			logger.info("Inside Catch.Exception occurs------>");
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	
	public ResultObject insertRecoveryChallan(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			RecoveryChallanDAOImpl recoveryChallanDAOImpl = new RecoveryChallanDAOImpl(HrLoanRecoveryChallan.class,serv.getSessionFactory());
			HrLoanRecoveryChallan challan = new HrLoanRecoveryChallan();
		
			
		
			logger.info("manish here in  insertRecoveryChallan");
			GenericDaoHibernateImpl princiGImpl = null;
        	princiGImpl = new GenericDaoHibernateImpl(HrLoanEmpPrinRecoverDtls.class);
        	princiGImpl.setSessionFactory(serv.getSessionFactory());
        	
        	GenericDaoHibernateImpl intGImpl = null;
        	intGImpl = new GenericDaoHibernateImpl(HrLoanEmpIntRecoverDtls.class);
        	intGImpl.setSessionFactory(serv.getSessionFactory());
        	long loanEmpId=Long.parseLong(objectArgs.get("loanEmpId").toString());
			//List loanPrinciRecoverList = princiGImpl.getListByColumnAndValue("hrLoanEmpDtls.empLoanId",loanEmpId );
        	//List loanIntRecoverList = intGImpl.getListByColumnAndValue("hrLoanEmpDtls.empLoanId",loanEmpId );
        	IdGenerator idgen = new IdGenerator();
        	RecoveryChallanCustomVO customVO = new RecoveryChallanCustomVO();
        	EmpLoanDAOImpl empLoanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
			HrLoanEmpDtls hrLoanEmpDtls = empLoanDAO.read(loanEmpId);
			customVO =(RecoveryChallanCustomVO)objectArgs.get("challanVO");
			int totalInstallments = objectArgs.get("totalInstallments") != null && !"".equals(objectArgs.get("totalInstallments")) ? (Integer)objectArgs.get("totalInstallments") : 0;
        	long amt = customVO.getAmount();
        	long pkId=idgen.PKGenerator("HR_LOAN_RECOVERY_CHALLAN", objectArgs);
        	
        	logger.info("generated Pk is "+pkId);
        	
        	challan.setId(pkId);
        	logger.info(" recovery type is "+customVO.getRecoveryType());
        	if(customVO.getRecoveryType().equals("Principal"))
        	{
        		//HrLoanEmpPrinRecoverDtls  hrLoanEmpPrinRecoverDtls = (HrLoanEmpPrinRecoverDtls)loanPrinciRecoverList.get(0);
        		HrLoanEmpPrinRecoverDtls  hrLoanEmpPrinRecoverDtls = (HrLoanEmpPrinRecoverDtls)hrLoanEmpDtls.getHrLoanEmpPrinRecoverDtlses().iterator().next();
        		challan.setHrLoanEmpPrinRecoverDtls(hrLoanEmpPrinRecoverDtls);
        		/*hrLoanEmpDtls.setLoanPrinAmt(Math.round(hrLoanEmpDtls.getLoanPrinAmt()-customVO.getAmount()));
        		hrLoanEmpDtls.setLoanPrinInstNo(Math.round(hrLoanEmpDtls.getLoanPrinInstNo()-(customVO.getAmount()/hrLoanEmpDtls.getLoanPrinEmiAmt())));
        		if(hrLoanEmpDtls.getLoanOddinstno() ==hrLoanEmpDtls.getLoanPrinInstNo())
        			hrLoanEmpDtls.setLoanOddinstno(hrLoanEmpDtls.getLoanOddinstno()-(customVO.getAmount()/hrLoanEmpDtls.getLoanPrinEmiAmt()));
        		empLoanDAO.update(hrLoanEmpDtls);*/
        		if(customVO.getOutstadingAmount() - amt == 0)
        			hrLoanEmpDtls.setLoanActivateFlag(0);
        		hrLoanEmpDtls.setMulLoanRecRemarks("");
        		hrLoanEmpDtls.setMulLoanRecoveryMode(0);
        		hrLoanEmpDtls.setMulLoanInstRecvd(1);
        		hrLoanEmpDtls.setMulLoanAmtRecvd(0L);
        		empLoanDAO.update(hrLoanEmpDtls);			
        		hrLoanEmpPrinRecoverDtls.setTotalRecoveredAmt(hrLoanEmpPrinRecoverDtls.getTotalRecoveredAmt() + amt);
        		hrLoanEmpPrinRecoverDtls.setTotalRecoveredInst(hrLoanEmpPrinRecoverDtls.getTotalRecoveredInst() + totalInstallments);
        		
        		logger.info("inside pricipal part ");
        	}
        	
        	if(customVO.getRecoveryType().equals("Interest") )
        	{
        	
        		//HrLoanEmpIntRecoverDtls  hrLoanEmpIntRecoverDtls = (HrLoanEmpIntRecoverDtls)loanIntRecoverList.get(0);
        		HrLoanEmpIntRecoverDtls  hrLoanEmpIntRecoverDtls = (HrLoanEmpIntRecoverDtls)hrLoanEmpDtls.getHrLoanEmpIntRecoverDtlses().iterator().next();
        		challan.setHrLoanEmpIntRecoverDtls(hrLoanEmpIntRecoverDtls);
        		/*hrLoanEmpDtls.setLoanInterestAmt(Math.round(hrLoanEmpDtls.getLoanInterestAmt()-customVO.getAmount()));
        		hrLoanEmpDtls.setLoanIntInstNo(Math.round(hrLoanEmpDtls.getLoanIntInstNo()-(customVO.getAmount()/hrLoanEmpDtls.getLoanIntEmiAmt())));
        		if(hrLoanEmpDtls.getLoanOddinstno()== hrLoanEmpDtls.getLoanIntInstNo())
        			hrLoanEmpDtls.setLoanOddinstno(hrLoanEmpDtls.getLoanOddinstno()-(customVO.getAmount()/hrLoanEmpDtls.getLoanIntEmiAmt()));
        		empLoanDAO.update(hrLoanEmpDtls);*/
        		if(customVO.getOutstadingAmount() - amt == 0)
        			hrLoanEmpDtls.setLoanActivateFlag(0);
        		hrLoanEmpDtls.setMulLoanRecRemarks("");
        		hrLoanEmpDtls.setMulLoanRecoveryMode(0);
        		hrLoanEmpDtls.setMulLoanInstRecvd(1);
        		hrLoanEmpDtls.setMulLoanAmtRecvd(0L);
        		empLoanDAO.update(hrLoanEmpDtls);	
        		hrLoanEmpIntRecoverDtls.setTotalRecoveredInt(	hrLoanEmpIntRecoverDtls.getTotalRecoveredInt() + amt);
        		hrLoanEmpIntRecoverDtls.setTotalRecoveredIntInst(hrLoanEmpIntRecoverDtls.getTotalRecoveredIntInst() + totalInstallments);
        		
        		logger.info("inside interest part");
        	}
			String recoveryOrderDate="";
			String challanDate="";
			double amtPaid=0;
			String challanNo="";
			String recoveryOrderNo="";
			
			
			logger.info("manis here "+customVO.getChallanDate() + ""+customVO.getRecoveryDate());
			challan.setAmountPaid(customVO.getAmount());
			
			//challan.setChallanDate(new Date());
			DateFormat formatter ; 
			 formatter = new SimpleDateFormat("dd/MM/yyyy");
			 Date asOn = (Date)formatter.parse(customVO.getRecoveryDate());  
			 challan.setRecoveryOrderDate(asOn);
			challan.setChallanNo(customVO.getChallnNo());
			//challan.setRecoveryOrderDate(new Date());
			challan.setRecoveryOrderNo(customVO.getRecoveryOrderNo());
			Date clnDate=(Date)formatter.parse(customVO.getChallanDate());
			challan.setChallanDate(clnDate);
			logger.info("chal "+clnDate +" rec date is "+asOn);
			recoveryChallanDAOImpl.create(challan);
			
			logger.info("end of service");
			objectArgs.put("msg","yes");
			objectArgs.put("customVO",customVO);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("RecoveryChallanEdit");
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
		
	}
	
	public ResultObject DemoTest(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			RecoveryChallanDAOImpl recoveryChallanDAOImpl = new RecoveryChallanDAOImpl(HrLoanRecoveryChallan.class,serv.getSessionFactory());
			HrLoanRecoveryChallan challan = new HrLoanRecoveryChallan();
		
			
		
			logger.info("manish here in  insertRecoveryChallan");
			GenericDaoHibernateImpl princiGImpl = null;
        	princiGImpl = new GenericDaoHibernateImpl(HrLoanEmpPrinRecoverDtls.class);
        	princiGImpl.setSessionFactory(serv.getSessionFactory());
        	
        	
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("NEW");
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
		
	}
	
	
	

}
