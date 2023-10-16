package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ArrearBillDaoImpl;
import com.tcs.sgv.eis.dao.EisEmpPayIncDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAO;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.util.RevisedHrrAndHra;
import com.tcs.sgv.eis.util.getDiffDates;
import com.tcs.sgv.eis.valueobject.ArrearReportCustomVO;
import com.tcs.sgv.eis.valueobject.GPDifferenceArrearCustomVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;


public class ArrearOuterPrintService<T> extends ServiceImpl{
	Log logger = LogFactory.getLog( getClass() );	 
	public ResultObject OuterArrearPrint(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		  
		try
		{		
			logger.info("Inside ArrearOuterPrintService Service");
	        long bill_no = Long.parseLong(voToService.get("billNo").toString());
	    	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

	    	HrPayArrearPaybill hrpayArrearBill = new HrPayArrearPaybill();
	        List<HrPayArrearPaybill> hrArrearPaybillList = new ArrayList();
	        OrgDesignationMst orgDesignationMst = new OrgDesignationMst();
	        List<OrgDesignationMst> designationList = new ArrayList();
	        long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
                        
            String orderName= "";
            String billNo = "";
            Date orderDate ;
            double billamount=0 ;
            double netBillamount=0;
            
            
            ArrearBillDaoImpl arrearPaybillDaoImpl = new ArrearBillDaoImpl(HrPayArrearPaybill.class,serv.getSessionFactory());
            List<HrPayArrearPaybill> paybillGrpList = arrearPaybillDaoImpl.getPaybillGrpIdFromTrnBillRstr(bill_no);
            
            
            
            HrPayArrearPaybill hrPayArrearPaybill = null;
			 long paybill_grp_id=0;
			 if(paybillGrpList!=null && paybillGrpList.size() >0)
			 {
				 hrPayArrearPaybill = paybillGrpList.get(0);
				 paybill_grp_id = hrPayArrearPaybill.getPaybillGrpId(); 
				 logger.info("paybill_grp_id is " + paybill_grp_id);
			 }
			 else
			 {
				 logger.info("Null List get...");				   
			 }
			 
           List arrearPayBillList =  arrearPaybillDaoImpl.getArrearBillList(paybill_grp_id);
              logger.info("arrearPayBillList size is"+arrearPayBillList.size());
           if(arrearPayBillList!=null && arrearPayBillList.size() != 0  ){
        	   
        	   PaybillHeadMpg paybillHeadMpg = new PaybillHeadMpg();
        	   List arrearCustomVoList=new ArrayList();
        	  PaybillBillregMpg paybillBillRegMpg = new PaybillBillregMpg();
        	   ArrearReportCustomVO arrearReportCustomVO = new ArrearReportCustomVO();
           
        	   HrEisEmpMst hrEisEmpMst = new HrEisEmpMst(); 
        	   
        	   long size = arrearPayBillList.size();
        	   
           for(int i=0;i<arrearPayBillList.size();i++)
            {
        	   	billamount = 0;
        	   	
        	   	Object[] row = (Object[])arrearPayBillList.get(i);
        	   	arrearReportCustomVO = new ArrearReportCustomVO();
        	   	
        	   	hrpayArrearBill = new HrPayArrearPaybill();
        	   	orgDesignationMst = new OrgDesignationMst();
        	   
        	 
        	 
	        	if(row!=null && row.length>0)
	        	{
	        		hrpayArrearBill = (HrPayArrearPaybill)row[0];
		           	paybillHeadMpg = (PaybillHeadMpg)row[1];
		          	paybillBillRegMpg = (PaybillBillregMpg)row[2];
		          	
		          	arrearReportCustomVO.setPanNo(row[3]!=null?row[3].toString():"");
		          	
		           	hrEisEmpMst = hrpayArrearBill.getHrEisEmpMst();
		        	 logger.info("Emp First Name is:-->"+hrEisEmpMst.getOrgEmpMst().getEmpFname());
	            	 String mthdName ="";		            	 
		        	 String edpCode = "";
		        	 if(hrpayArrearBill.getSalRevId()!=null && hrpayArrearBill.getSalRevId().getRltBillTypeEdp()!=null)
		        	 {	 
		        	 edpCode = hrpayArrearBill.getSalRevId().getRltBillTypeEdp().getEdpCode();
		        	 mthdName = hrpayArrearBill.getSalRevId().getRltBillTypeEdp().getEdpMethodName();
		        	 }
		        	 
		             Class c = hrpayArrearBill.getClass();
		             if(!edpCode.equals(""))
		             {
		             		            	 
			        	  mthdName = "get"+mthdName;
		             Method Mthd = c.getMethod(mthdName, null);
		             long arrearAmtByEdpCoode = Math.round(Double.parseDouble(((Mthd.invoke(hrpayArrearBill, null)!=null)?(Mthd.invoke(hrpayArrearBill, null).toString()):"0")));
		           	 arrearReportCustomVO.setArrearAmtByEdpCoode(arrearAmtByEdpCoode);
		           	 
		           	 // for prev Bill AMt
		             c = hrpayArrearBill.getPaybillId().getClass();
		             Mthd = c.getMethod(mthdName, null);
		           	 arrearAmtByEdpCoode = Math.round(Double.parseDouble(((Mthd.invoke(hrpayArrearBill.getPaybillId(), null)!=null)?(Mthd.invoke(hrpayArrearBill.getPaybillId(), null).toString()):"0")));
		           	 arrearReportCustomVO.setPrevAmtByEdpCoode(arrearAmtByEdpCoode);		
		             objectArgs.put("ArrearDisplayName",hrpayArrearBill.getSalRevId().getRltBillTypeEdp().getEdpShortName());
		             }
		             else
		             {	 
			           	 arrearReportCustomVO.setArrearAmtByEdpCoode(0);
			           	 arrearReportCustomVO.setPrevAmtByEdpCoode(0);
			             objectArgs.put("ArrearDisplayName","D.A.");			           	 
		             }  	 
		        	 
		        	 arrearReportCustomVO.setArrearBill(hrpayArrearBill);
		        	 arrearReportCustomVO.setPaybillHeadMpg(paybillHeadMpg);
		        	 		       		        	
		        	 
		        	 arrearReportCustomVO.setPaybillBillRegMpg(paybillBillRegMpg);
		        	 
		        	 OrgPostDetailsRlt orgPostDetailsRlt = new OrgPostDetailsRlt(); 
			    	 OrgPostDetailsRltDaoImpl postDtlDAO = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class,serv.getSessionFactory());
			    	 orgPostDetailsRlt = postDtlDAO.getPostDetailsRltByPostIdAndLangId(hrpayArrearBill.getOrgPostMst().getPostId(),langId);		        	 
		        	 orgDesignationMst = orgPostDetailsRlt.getOrgDesignationMst();
		        	 
		        	 designationList.add(orgDesignationMst);
		        	 
		        	 billamount = hrpayArrearBill.getPaybillId().getNetTotal();
		        	 arrearReportCustomVO.setOrgDesignationMst(orgDesignationMst);
		        	 
		        	 arrearCustomVoList.add(arrearReportCustomVO);
		        	 
		        	 hrArrearPaybillList.add(hrpayArrearBill);
	        	 }
	        }
           
          ArrearBillDaoImpl arrearBillDaoImpl =new ArrearBillDaoImpl(HrPayArrearPaybill.class,serv.getSessionFactory());
          
			 
            List<TrnBillRegister> trnbillregister = arrearBillDaoImpl.getTrnBillRegister(bill_no);
            TrnBillRegister trnBillRegister = null;
			 String bill__ctrl_no="";
			 if(trnbillregister!=null && trnbillregister.size() >0)
			 {
				 trnBillRegister = trnbillregister.get(0);
				 bill__ctrl_no = trnBillRegister.getBillCntrlNo(); 
			 }
			 else
			 {
				 logger.info("Null List get...");				   
			 }
            
			 
			 objectArgs.put("arrearBillListSize",size);
			 
			 
           orderName = hrpayArrearBill.getSalRevId().getRevOrderNo();
           orderDate = hrpayArrearBill.getSalRevId().getRevOrderDate();
           
           StringTokenizer st1=new StringTokenizer(hrpayArrearBill.getBillNo(),"(");
           
           billNo = st1.nextToken();
           
           netBillamount = Math.round(netBillamount + billamount);
           
			PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			Object[] objArry = null;
			
			List lstSignInfo = new ArrayList();
			long locationId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			lstSignInfo = billDAO.getReportSignature(locationId);
			String desigName="";
			String deptName="";
			String cardexCode="";
			String cityName="";
			if(lstSignInfo.get(0)!=null)
			{

				objArry =(Object[]) lstSignInfo.get(0);
				desigName=objArry[0].toString();
				deptName=objArry[1].toString();
				cardexCode=objArry[2].toString();
				cityName=objArry[3].toString();            		
			}

            objectArgs.put("desigName",desigName);
            objectArgs.put("deptName",deptName);
            objectArgs.put("cardexCode",cardexCode);
            objectArgs.put("cityName",cityName);
           
           // objectArgs.put("paybillHeadMpgList",paybillHeadMpgList);
            objectArgs.put("designationList",designationList);
            objectArgs.put("hrArrearPaybillList",hrArrearPaybillList);
            objectArgs.put("arrearCustomVoList", arrearCustomVoList);
            objectArgs.put("netBillamount",netBillamount);
            objectArgs.put("orderDate",orderDate);
            objectArgs.put("trnBillRegister",trnBillRegister);
            objectArgs.put("orderName",orderName);
            objectArgs.put("billNo",billNo);
           // objectArgs.put("trnList",trnList);
            resultObject.setResultValue(objectArgs);
            objectArgs.put("msg", "");
            resultObject.setViewName("OuterArrearPrint");
           }
            else
            
            {
            	logger.info("Null Pointer Exception Ocuures...insertOrderMasterDtls");
    		
    			objectArgs.put("msg", "No Data Found To Display.");
    			resultObject.setResultValue(objectArgs);
    			resultObject.setViewName("OuterArrearPrint");	
            }
            	
		}
		
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	
	public ResultObject OuterGPDiffArrearPrint (Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		Map voToService = (Map)objectArgs.get("voToServiceMap");

		try
		{		
			logger.info(" ::::::::::::::::::::::::::::::::::::::::::::::   Inside OuterGPDiffArrearPrint Service");
			long bill_no = Long.parseLong(voToService.get("billNo").toString());
			long billTypeId = Long.parseLong(voToService.get("billTypeId").toString());
			
			int arrearMonth = voToService.get("billMonth")!= null && !voToService.get("billMonth").equals("")? Integer.parseInt(voToService.get("billMonth").toString()):0;
			int arrearYear  = voToService.get("billYear")!= null && !voToService.get("billYear").equals("")? Integer.parseInt(voToService.get("billYear").toString()):0;
			
			logger.info(" ::::::::::::::::::::::::::::::::::::::::::::::  TRN bill Number : "  +  bill_no);
			logger.info(" ::::::::::::::::::::::::::::::::::::::::::::::  billTypeId      : "  +  billTypeId);
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

			OrgDesignationMst orgDesignationMst = new OrgDesignationMst();
			List<OrgDesignationMst> designationList = new ArrayList();
			List empDataList = new ArrayList();
			List empRecCounterLst = new ArrayList();
			
			HrPayfixMst hrPayfixMst = new HrPayfixMst();
			List hrPayfixMstVOList = new ArrayList();
			EisEmpPayIncDAOImpl empPayIncrDAOImpl = new EisEmpPayIncDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=new OrgUserMst();
			OrgUserMstDaoImpl orgUserdao=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			
			PayBillDAOImpl paybillDaoImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString()).longValue();
			String paybillTypeId = resourceBundle.getString("paybillTypeId").toString();// paybillType Id from Property File.	
			long AprilMonthDA = Long.parseLong(resourceBundle.getString("AprilMonthDA").toString());// April Month DA Percentage from Property File.
			long April        = Long.parseLong(resourceBundle.getString("April").toString());// April Month Code Percentage from Property File.
			long July        = Long.parseLong(resourceBundle.getString("July").toString());// July Month Code Percentage from Property File.
			long August        = Long.parseLong(resourceBundle.getString("August").toString());// August Month Code Percentage from Property File.
			long currYear       = Long.parseLong(resourceBundle.getString("currYear").toString());// Current Year Code Percentage from Property File.
			
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			double billamount=0 ;
			long empIdTemp = 0;
			long EmpId = 0;        	
			long PsrNo = 0;        	
			String EmpFullName = "";        	
			String EmpDsgn = "";        	
			long BasicPaid = 0;        	
			long GPPaid = 0;        	
			long DAPaid = 0;        	
			long HRAPaid = 0;        	
			String PaidMonth = "";        	
			String PaidYear = "";        	
			long BasicPayable = 0;        	
			long GPPayable = 0;
			long NetTotal = 0;
			long UserId = 0;
			long EmpType = 0;
			long DAPayable = 0;        	
			long HRAPayable = 0;
			int isAVailedHRA = 0;
			long city = 0;
			long netTotalTemp = 0;
			int empRecCounter = 0;
			String GpfNumber = "";
			String PanCardNum = "";
			long BillNumber = 0;
			String billCntrlNo = "";
			String billDate= "";
			long netBillAmount = 0;
			Date incDate ;
			long fixMonth = 0;
			long fixYear = 0;
			int k = 0;
			long hrPayfixMstVOListLength =0;


			List employeeIdList = new ArrayList();
			SalaryRules_6thPay objSixthSalRuleEngine = new SalaryRules_6thPay();

			logger.info(" ::::::::::::::::::::::::::::::::::::::::::::::  B4 Execution of Function ");
			BillNumber = paybillDaoImpl.getBillNumberFromTRNBillNumber(bill_no, locId, billTypeId);
			logger.info(" ::::::::::::::::::::::::::::::::::::::::::::::  BillNumber  : "  +  BillNumber);

			List monthYearList = new ArrayList();
			HrPaySalRevMst hrPaySalRevMst = new HrPaySalRevMst();

			List<HrPaySalRevMst> hrPaySalRevMstArrearData  =  paybillDaoImpl.hrPaySalRevMstArrearData(bill_no, locId);
			if(hrPaySalRevMstArrearData.size() > 0)
				hrPaySalRevMst = hrPaySalRevMstArrearData.get(0);

			monthYearList = getDiffDates.getDiffDatesLst(hrPaySalRevMst.revEffcFrmDate, hrPaySalRevMst.revEffcToDate);
			List payBillList  =  paybillDaoImpl.getGPDiffArrearBillList(BillNumber, monthYearList, langId, locId, paybillTypeId, arrearMonth, arrearYear);
			logger.info(" ::::::::::::::::::::::::::::::::::::::    hrPaySalRevMstArrearData size is"+hrPaySalRevMstArrearData.size());
			if(payBillList!=null && payBillList.size() > 0  )
			{
				HrEisEmpMst hrEisEmpMst = new HrEisEmpMst(); 
				for(int i=0;i<payBillList.size();i++)
				{
					billamount = 0;
					long hraCalculated = 0;
					long daCalculated = 0;
					Object[] row = (Object[])payBillList.get(i);
					GPDifferenceArrearCustomVO gpDiffCustomVO = new GPDifferenceArrearCustomVO();

					if(row[0] != null && !(row[0].toString().trim()).equals(""))
					{
						EmpId = Long.parseLong(row[0].toString());
						gpDiffCustomVO.setEmpId(EmpId);
					}
					if(row[1] != null && !(row[1].toString().trim()).equals(""))
					{
						PsrNo = Long.parseLong(row[1].toString());
						gpDiffCustomVO.setPsrNo(PsrNo);
					}
					if(row[2] != null && !(row[2].toString().trim()).equals(""))
					{
						EmpFullName = row[2].toString();
						gpDiffCustomVO.setEmpFullName(EmpFullName);
					}
					if(row[3] != null && !(row[3].toString().trim()).equals(""))
					{
						EmpDsgn = row[3].toString();
						gpDiffCustomVO.setEmpDsgn(EmpDsgn);
					}
					if(row[4] != null && !(row[4].toString().trim()).equals(""))
					{
						BasicPaid = Long.parseLong(row[4].toString());
						gpDiffCustomVO.setBasicPaid(BasicPaid);
					}
					if(row[5] != null && !(row[5].toString().trim()).equals(""))
					{
						GPPaid = Long.parseLong(row[5].toString());
						gpDiffCustomVO.setGPPaid(GPPaid);
					}
					if(row[9] != null && !(row[9].toString().trim()).equals(""))
					{
						PaidMonth = row[9].toString();
						gpDiffCustomVO.setPaidMonth(PaidMonth);
					}
					if(row[6] != null && !(row[6].toString().trim()).equals(""))
					{
						DAPaid = Long.parseLong(row[6].toString());
						if(Long.parseLong(PaidMonth) == April)
						{
							daCalculated = AprilMonthDA;
							DAPaid = Long.valueOf(Math.round(((BasicPaid+GPPaid) * AprilMonthDA)*0.01));
						}
						gpDiffCustomVO.setDAPaid(DAPaid);
					}
					if(row[7] != null && !(row[7].toString().trim()).equals(""))
					{
						HRAPaid = Long.parseLong(row[7].toString());
						gpDiffCustomVO.setHRAPaid(HRAPaid);
					}
					if(row[8] != null && !(row[8].toString().trim()).equals(""))
					{
						PaidYear = row[8].toString();
						gpDiffCustomVO.setPaidYear(PaidYear);
					}
					if(row[11] != null && !(row[11].toString().trim()).equals(""))
					{
						GPPayable = Long.parseLong(row[11].toString());
						gpDiffCustomVO.setGPPayable(GPPayable);
					}
					if(row[13] != null && !(row[13].toString().trim()).equals(""))
					{
						UserId = Long.parseLong(row[13].toString());
						gpDiffCustomVO.setUserId(UserId);
					}
					if(row[10] != null && !(row[10].toString().trim()).equals(""))
					{
						BasicPayable = (Long.parseLong(row[10].toString())) - GPPayable;
						if(UserId > 0)
						{
							orgUserMst=orgUserdao.read(UserId);
							hrPayfixMstVOList =  empPayIncrDAOImpl.getListByColumnAndValue("userId", orgUserMst);
							if(hrPayfixMstVOList != null && !hrPayfixMstVOList.equals(""))
								hrPayfixMstVOListLength = hrPayfixMstVOList.size();
						}
						if(hrPayfixMstVOListLength > 0)
						{

							for(int j = 0; j < hrPayfixMstVOListLength; j++)
							{
								hrPayfixMst = (HrPayfixMst) hrPayfixMstVOList.get(j);
								incDate = hrPayfixMst.getPayFixDate();
								fixMonth=incDate.getMonth()+1;
								fixYear=incDate.getYear()+1900;
								if((fixMonth == July || fixMonth == August) && fixYear == currYear)
								{
									//BasicPayable = hrPayfixMst.getPrevPay() - GPPayable;
									logger.info(">>>>>>>>>>>>>>>   Employee ID = " + EmpId + " and user Id = " + UserId + " got Increament  and prev say was = " + BasicPayable);
								}
							}
						}
						logger.info("::::::::::::::::::::::::::::::::::::::::::::  BasicPayable " + BasicPayable + "  for user Id " + UserId);
						gpDiffCustomVO.setBasicPayable(BasicPayable);
					}
					if(row[12] != null && !(row[12].toString().trim()).equals(""))
					{
						NetTotal = Long.parseLong(row[12].toString());
						gpDiffCustomVO.setNetTotal(NetTotal);
					}
					if(row[14] != null && !(row[14].toString().trim()).equals(""))
					{
						EmpType = Long.parseLong(row[14].toString());
						gpDiffCustomVO.setEmpType(EmpType);
					}
					if(row[15] != null && !(row[15].toString().trim()).equals(""))
					{
						isAVailedHRA = Integer.parseInt(row[15].toString());
						gpDiffCustomVO.setIsHraAvailed(isAVailedHRA);
					}

					if(row[16] != null && !(row[16].toString().trim()).equals(""))
					{
						city = Integer.parseInt(row[16].toString());
						gpDiffCustomVO.setCity(city);
					}
					if(row[17] != null && !(row[17].toString().trim()).equals(""))
					{
						GpfNumber = row[17].toString();
						gpDiffCustomVO.setGpfNumber(GpfNumber) ;
					}
					if(row[18] != null && !(row[18].toString().trim()).equals(""))
					{
						PanCardNum = row[18].toString();
						gpDiffCustomVO.setPanCardNum(PanCardNum);
					}
					if(row[19] != null && !(row[19].toString().trim()).equals(""))
					{
						billCntrlNo = row[19].toString();
						gpDiffCustomVO.setBillCntrlNo(billCntrlNo);
					}
					if(row[20] != null && !(row[20].toString().trim()).equals(""))
					{
						billDate = row[20].toString();
						gpDiffCustomVO.setBillDate(sdfParse.parse(billDate));
						logger.info("::::::::::::::::::::::::::::::::::::::::::    billDate  = " + billDate);
					}
					if(row[21] != null && !(row[21].toString().trim()).equals(""))
					{
						netBillAmount = Long.parseLong(row[21].toString());
						gpDiffCustomVO.setNetBillAmount(netBillAmount);
					}

					if(empIdTemp == EmpId && empIdTemp > 0)
					{
						netTotalTemp = netTotalTemp + NetTotal;     
						empRecCounter++;
					}
					else if(empIdTemp > 0)
					{
						empRecCounterLst.add(empRecCounter);
						logger.info("::::::::::::::::::::::::::::::::::::::::::    netTotalTemp  = " + netTotalTemp);
						logger.info("::::::::::::::::::::::::::::::::::::::::::    empRecCounter = " + empRecCounter);
						netTotalTemp = 0;
						empRecCounter = 0;
					} 		 
					if(empIdTemp != EmpId)
					{
						netTotalTemp = 0;        			   
						empRecCounter = 0;
						empIdTemp = EmpId ;
						employeeIdList.add(empIdTemp);
						netTotalTemp = netTotalTemp + NetTotal;
						empRecCounter++;
					}
					if((i+1) == payBillList.size())
					{
						empRecCounterLst.add(empRecCounter);
					}   
					logger.info("::::::::::::::::::::::::::::::::::::::::::    value of payBillList.size() = " + payBillList.size());

					String salaryRuleClassPath = "com.tcs.sgv.allowance.service.SalaryRules_6thPay";
					Class salRules = Class.forName(salaryRuleClassPath);
					Constructor constructor =	salRules.getConstructor(null);
					Object invoker =constructor.newInstance(null); 
					String methodName="calculateDA";


					logger.info("::::::::::::::::::::::  goinh to calculate DA Payable   ::::::::::::::::::::");

					Method methodCalDA = salRules.getMethod(methodName,new Class[] {long.class});
					Object[] objArgs = new Object[1];
					objArgs[0] = EmpType;
					daCalculated = (Long) Math.round((Double)methodCalDA.invoke(invoker, objArgs));
					DAPayable = Long.valueOf(Math.round(((BasicPayable+GPPayable) * daCalculated)*0.01));

					//DAPayable = ((Double) objSixthSalRuleEngine.calculateDA(EmpType)).longValue();
					logger.info("From Salary Rulz :::::::::::::::::::::::::::::::::::::::::::  DA calc'ed " + daCalculated);
					logger.info("From Salary Rulz :::::::::::::::::::::::::::::::::::::::::::  DA Payable  " + DAPayable);

					objectArgs.put("userIdForQtr",UserId);
					objectArgs.put("monthGiven",PaidMonth);
					objectArgs.put("yearGiven",PaidYear);
					RevisedHrrAndHra revisedHrrAndHra = new RevisedHrrAndHra();
					Map hrrAndHraMap = revisedHrrAndHra.calculateHrrAndHra(objectArgs);

					List qtrId = (List) hrrAndHraMap.get("quaterIdList");
					logger.info("From Service  ::::::::::::::::::::::::::::::::::::::::::: qtrIdz = " + qtrId);
					if(qtrId != null && qtrId.size() > 0){}
						//hraCalculated = ((Double) objSixthSalRuleEngine.calculateHRA(city, Integer.parseInt(qtrId.get(0).toString()), EmpType, isAVailedHRA)).longValue();
					else
						hraCalculated = 0;

					HRAPayable = Long.valueOf(Math.round(((BasicPayable+GPPayable) * hraCalculated)*0.01));                                                
					gpDiffCustomVO.setNetTotal(NetTotal);
					gpDiffCustomVO.setDAPayable(DAPayable);
					gpDiffCustomVO.setHRAPayable(HRAPayable);
					empDataList.add(gpDiffCustomVO);
					logger.info("From Salary Rulz :::::::::::::::::::::::::::::::::::::::::::  One Cycle End Here !!!!!  \n\n");
				}


				// getting Signature details
				PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				Object[] objArry = null;

				List lstSignInfo = new ArrayList();
				long locationId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				lstSignInfo = billDAO.getReportSignature(locationId);
				String desigName="";
				String deptName="";
				String cardexCode="";
				String cityName="";
				if(lstSignInfo.get(0)!=null)
				{

					objArry =(Object[]) lstSignInfo.get(0);
					desigName=objArry[0].toString();
					deptName=objArry[1].toString();
					cardexCode=objArry[2].toString();
					cityName=objArry[3].toString();            		
				}
				//	Signature up to here 
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> employeeIdList ki llist size   = " + employeeIdList.size());

				logger.info("::::::::::::::::::::::::::::::::::::::::::    empRecCounterLst ki size   =  " + empRecCounterLst.size());
				logger.info("::::::::::::::::::::::::::::::::::::::::::    empRecCounterLst ki Values =  " + empRecCounterLst);
				objectArgs.put("desigName",desigName);
				objectArgs.put("deptName",deptName);
				objectArgs.put("cardexCode",cardexCode);
				objectArgs.put("cityName",cityName);           
				objectArgs.put("employeeIdList",employeeIdList);
				objectArgs.put("billNo",BillNumber);
				objectArgs.put("empDataList",empDataList);
				objectArgs.put("empRecCounterLst",empRecCounterLst);
				objectArgs.put("hrPaySalRevMst",hrPaySalRevMst);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("OuterGPDiffArrearPrint");
			}
			else

			{
				logger.info("Null Pointer Exception Ocuures...insertOrderMasterDtls");
				objectArgs.put("msg", "No Data Found To Display.");
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("OuterArrearPrint");	
			}
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	
}


