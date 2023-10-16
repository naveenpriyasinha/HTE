package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducExpMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class DeductionDtlsVOGenImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapForInsertEmpDeductionDtls(Map objectArgs) 
	{
		try{
		logger.info("DeductionDtlsVOGenImpl generateMapForInsertEmpDeductionDtls Called");
		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				
		Map mp = objectArgs;
		
		if(StringUtility.getParameter("batchupdate", request)!=null&&!StringUtility.getParameter("batchupdate", request).equals("")&&StringUtility.getParameter("batchupdate", request).equals("true"))
		{


			long empId = 0;
			if(!StringUtility.getParameter("txtEmpId", request).equals(""))
			{			
	    		empId = Long.parseLong(StringUtility.getParameter("txtEmpId", request));
			}
            int size=0;
			if(StringUtility.getParameter("deducSize", request)!=null&&!StringUtility.getParameter("deducSize", request).equals(""))
			{			
				/*size = Integer.parseInt(StringUtility.getParameter("size", request));*/
				size = Integer.parseInt(StringUtility.getParameter("deducSize", request));
				logger.info("SIZE IN VOGEN IS :"+size);
			}
			String editMode = StringUtility.getParameter("edit",request);
			logger.info("edit Mode in vogen is "+editMode);
			
			

			HrPayDeductionDtls empDeducDtls = new HrPayDeductionDtls();
			
			Date sysdate = new Date();	
		 	int month=sysdate.getMonth()+1;
			int year = sysdate.getYear()+1900;
			
			for(int j=1;j<=size;j++)
			{
				
				long empDeducId = 0;

				if(!StringUtility.getParameter("deduccode"+j, request).equals(""))
	     		{     			
	        		empDeducId = Long.parseLong(StringUtility.getParameter("deduccode"+j, request));
	     		}	
				logger.info("**************empDeducId*********"+empDeducId);
				if(editMode.equalsIgnoreCase("N")) 
				{						
					empDeducDtls = new HrPayDeductionDtls();
					objectArgs.put("editDeduc"+j,"N");
				}
				else
				{ 
					long DeducCode = 0; 
					String DeducId = "";
	        	
	           
					/*if(StringUtility.getParameter("txtDeducCode"+empDeducId, request)!=null&&!StringUtility.getParameter("txtDeducCode"+empDeducId, request).equals(""))
	     			{     			
	         			DeducId = StringUtility.getParameter("txtDeducCode"+empDeducId, request);
	     			}*/
	        	
					if(StringUtility.getParameter("txttDeducCode"+empDeducId, request)!=null&&!StringUtility.getParameter("txttDeducCode"+empDeducId, request).equals(""))
					{     			
						DeducId = StringUtility.getParameter("txttDeducCode"+empDeducId, request);
	     			}	
					logger.info("**************DeducId*********"+DeducId);

					if( DeducId!=null&& !DeducId.equals("")&&!DeducId.equals("txttDeducCode")){
						DeducCode = Long.parseLong(DeducId);
						objectArgs.put("editDeduc"+j,"Y");
					}
					else
						objectArgs.put("editDeduc"+j,"N");
			 
					empDeducDtls = new HrPayDeductionDtls();	
					logger.info("Deduc code in vogen is"+DeducCode);
			 
					GenericDaoHibernateImpl<HrPayDeductionDtls, Long> genDao = new GenericDaoHibernateImpl<HrPayDeductionDtls, Long>(HrPayDeductionDtls.class);
					genDao.setSessionFactory(serv.getSessionFactory());
					empDeducDtls = genDao.read(DeducCode);
					logger.info(" valuobject is "+empDeducDtls);
					//empDeducDtls.setDeducDtlsCode(DeducCode);
					//objectArgs.put("edit"+j,"Y");
				}
			
				double DeducAmount=0;	
				
	     		
				boolean flag = false;
			
				if(StringUtility.getParameter("txtt"+empDeducId, request)!=null&&!StringUtility.getParameter("txtt"+empDeducId, request).equals(""))
				{
		        	logger.info("**************txt*********"+StringUtility.getParameter("txtt"+empDeducId, request));
					 DeducAmount = Double.parseDouble(StringUtility.getParameter("txtt"+empDeducId, request));
					 logger.info("Amount for deduction is "+DeducAmount);
				}
				else
				{
					 flag=true;
				}
				
				 
				
				
				HrPayDeductionDtls empDeducDtlsNew = new HrPayDeductionDtls();
				if(empDeducDtls==null)
				{
					logger.info("japen testing ...object is null");
					empDeducDtls= new HrPayDeductionDtls();
					empDeducDtls.setDeducDtlsCode(0);

					if(flag)
				 		empDeducDtls.setEmpDeducAmount(-1);
				 	else
				 		empDeducDtls.setEmpDeducAmount(DeducAmount);
					
					HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
					GenericDaoHibernateImpl<HrPayDeducTypeMst , Long> genDao = new  GenericDaoHibernateImpl<HrPayDeducTypeMst , Long>(HrPayDeducTypeMst.class);
					genDao.setSessionFactory(serv.getSessionFactory());
				 	hrPayDeducTypeMst = genDao.read(empDeducId);
				 	empDeducDtls.setMonth(month);
				 	empDeducDtls.setYear(year);
				 	
				 	empDeducDtls.setHrPayDeducTypeMst(hrPayDeducTypeMst);
				 	 
				}
				else
				{
					logger.info("japen testing ...obj is not null ....month is "+empDeducDtls.getMonth());
					if(empDeducDtls.getMonth()==month && empDeducDtls.getYear()==year)
					{
						if(flag)
					 		empDeducDtls.setEmpDeducAmount(-1);
					 	else
					 		empDeducDtls.setEmpDeducAmount(DeducAmount);
						
					}
					else
					{
						logger.info("in else part of month if..");
						empDeducDtlsNew = new HrPayDeductionDtls();
						empDeducDtlsNew.setCmnDatabaseMst(empDeducDtls.getCmnDatabaseMst());
						empDeducDtlsNew.setCmnLocationMst(empDeducDtls.getCmnLocationMst());
						empDeducDtlsNew.setDeducDtlsCode(0);
						empDeducDtlsNew.setDeducDtlsCode(empDeducDtls.getDeducDtlsCode());//manish
						empDeducDtlsNew.setEmpCurrentStatus(1);
						
						if(flag)
						{
							//empDeducDtls.setEmpDeducAmount(-1);
							empDeducDtlsNew.setEmpDeducAmount(-1);
						}
					 	else
					 	{
					 		//empDeducDtls.setEmpDeducAmount(DeducAmount);
					 		empDeducDtlsNew.setEmpDeducAmount(DeducAmount);
					 	}
						
						logger.info("Deduction amount is "+DeducAmount+"month "+month+"year "+year+" deduc code "+empDeducDtls.getHrPayDeducTypeMst().getDeducName());
						empDeducDtlsNew.setHrEisEmpMst(empDeducDtls.getHrEisEmpMst());
						empDeducDtlsNew.setHrPayDeducTypeMst(empDeducDtls.getHrPayDeducTypeMst());
						empDeducDtlsNew.setMonth(month);
						empDeducDtlsNew.setYear(year);
						empDeducDtls = empDeducDtlsNew;
						
					}
				}
			
				 
	    	
				logger.info("**************DeducAmount*********"+DeducAmount);
				
				empDeducDtls.setEmpCurrentStatus(1); //status = active
			 
				DeducExpMasterDAOImpl deducExpr = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
				List tempList = deducExpr.getExpfromDeducCodesNew(String.valueOf(empDeducId));
				 logger.info("Japen empDeducId is "+empDeducId+" List size is :::"+tempList.size());
			 	if(tempList.size()>0)
			 	{
			 		empDeducDtls.setEmpDeducAmount(-1);
			 	}
			 	
				 
				 empDeducDtls.setUpdatedDate(sysdate);
			 						
				 logger.info("Eidt In vogen is "+objectArgs.get("editDeduc"+j));
				 logger.info("Eidt In vogen is "+empDeducDtls.getDeducDtlsCode());
				objectArgs.put("empDeducDtls"+j,empDeducDtls);
			}			
			//objectArgs.put("size",size);
			logger.info("deduc size in vogen is"+size);
			objectArgs.put("deducSize",size);
			objectArgs.put("batchupdate","true");
			objectArgs.put("empid",empId);
		}
		else
		{
			
			String editMode = StringUtility.getParameter("edit",request);
			HrPayDeductionDtls empDeducDtls = null;
			long isChecked = 1;
		
			if(editMode.equalsIgnoreCase("N")) 
			{			
				empDeducDtls = new HrPayDeductionDtls();			 								 
				objectArgs.put("edit","N");
			}
			else
			{ 
				long deducCode = 0; 
				String deducId = "";
           
				if(!StringUtility.getParameter("txtDeducCode", request).equals(""))
				{
					
					deducId = StringUtility.getParameter("txtDeducCode", request);
				}	
        	
				logger.info("txtIsChecked is " + StringUtility.getParameter("txtIsChecked", request));
				logger.info("txtIsChecked is " + StringUtility.getParameter("txtIsChecked", request));
				if(!StringUtility.getParameter("txtIsChecked", request).equals(""))
				{
    			
    				isChecked = Long.parseLong(StringUtility.getParameter("txtIsChecked", request));
    				//logger.info("If loop " + isChecked);
				}
    		
        			 
				//logger.info("DeducID in VOGen " + DeducId);
		 
				if( deducId!=null ){
					deducCode = Long.parseLong(deducId);
				}
				
				
				empDeducDtls = new HrPayDeductionDtls();

				empDeducDtls.setDeducDtlsCode(deducCode);
				objectArgs.put("edit","Y");
			}
		
			long empDeducId = 0;
			double deducAmount=0;		
			long empId = 0;
		
     	
		
		
		
     		
			if(!StringUtility.getParameter("txtEmpDeducId", request).equals(""))
			{
			
				empDeducId = Long.parseLong(StringUtility.getParameter("txtEmpDeducId", request));
			}	
		
			if(!StringUtility.getParameter("txtDeducAmount", request).equals(""))
			{
				
				deducAmount = Double.parseDouble(StringUtility.getParameter("txtDeducAmount", request));
			}
			if(!StringUtility.getParameter("txtEmpId", request).equals(""))
			{
				
	    		empId = Long.parseLong(StringUtility.getParameter("txtEmpId", request));
			}
    	
		 if(isChecked==0)	
			 empDeducDtls.setEmpCurrentStatus(0);	//status = inactive	 
		 else
			 empDeducDtls.setEmpCurrentStatus(1); //status = active
		 
		 logger.info("isChecked in service is " + isChecked + "\nemp id in service is " + empId);
		 GenericDaoHibernateImpl<HrEisEmpMst, Long>  genDaoEmp = new GenericDaoHibernateImpl<HrEisEmpMst, Long>(HrEisEmpMst.class);
		 genDaoEmp.setSessionFactory(serv.getSessionFactory());
		 HrEisEmpMst hrEisEmpMst = genDaoEmp.read(empId);
		 if(hrEisEmpMst==null)
		 {
			   hrEisEmpMst = new HrEisEmpMst();
			   hrEisEmpMst.setEmpId(empId);
		 }
		  
		 HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
		 GenericDaoHibernateImpl<HrPayDeducTypeMst, Long>  genDaoMst = new GenericDaoHibernateImpl<HrPayDeducTypeMst, Long>(HrPayDeducTypeMst.class);
		 genDaoMst.setSessionFactory(serv.getSessionFactory());
		 hrPayDeducTypeMst= genDaoMst.read(empDeducId);
		 //hrPayDeducTypeMst.setDeducCode(empDeducId);
		 
		 empDeducDtls.setEmpDeducAmount(deducAmount);
		 empDeducDtls.setHrPayDeducTypeMst(hrPayDeducTypeMst);
		 empDeducDtls.setHrEisEmpMst(hrEisEmpMst);
	 
		
		 		 		 
		 Date sysdate = new Date();	
		 empDeducDtls.setMonth(sysdate.getMonth()+1);
		 empDeducDtls.setYear(sysdate.getYear()+1900);
		 empDeducDtls.setUpdatedDate(sysdate);
		 						
		logger.info(" ****************************empDeducDtls " + empDeducDtls);
				//mp.put("empDeducDtls",empDeducDtls);
		objectArgs.put("empDeducDtls",empDeducDtls);
		
		
		}
				//retObj.setResultValue(mp);
				retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		return retObj;
		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			Map errorMap = new HashMap();
			errorMap.put("msg", "There is some problem. Please Try Again Later.");
			retObj.setResultCode(-1);
			retObj.setResultValue(errorMap);
			retObj.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return retObj;
			
		}
		
	}

}
