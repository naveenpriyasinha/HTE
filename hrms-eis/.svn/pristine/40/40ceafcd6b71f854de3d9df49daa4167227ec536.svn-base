package com.tcs.sgv.eis.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.util.HttpServletRequestProxy;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ArrearBillDaoImpl;
import com.tcs.sgv.eis.dao.BillHeadMpgDAO;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PaybillRegMpgDAOImpl;
import com.tcs.sgv.eis.dao.SalRevMstDAOImpl;
import com.tcs.sgv.eis.util.GenerateArrearsBillCoreLogic;
import com.tcs.sgv.eis.util.GenerateDaArrearBillCoreLogic;
import com.tcs.sgv.eis.valueobject.EdpDtlsVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtlsHst;
import com.tcs.sgv.eis.valueobject.HrPayArrearBillpostMpg;
import com.tcs.sgv.eis.valueobject.HrPayArrearCompoAmtMpg;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class GenerateArrearBillService extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	//Added By Mrugesh
	long billTypeId = Long.parseLong(resourceBundle.getString("arrearbillTypeId"));
	//Ended By Mrugesh
	
//	added by Ankit Bhatt for integrating with Outer
	 
	public final int FIN_YEAR_ID=Integer.parseInt(constantsBundle.getString("FIN_YEAR_ID")); //Financial Year for Budget Heads.
    
    long allowLookupId = Long.parseLong(resourceBundle.getString("allowLookupId"));
	long deducLookupId = Long.parseLong(resourceBundle.getString("deducLookupId"));
	int CPFdd = Integer.parseInt(resourceBundle.getString("CPFdd"));
	int CPFMM = Integer.parseInt(resourceBundle.getString("CPFMM"));
	int CPFyyyy = Integer.parseInt(resourceBundle.getString("CPFyyyy"));
	
	long gpay0101TypeID = Long.parseLong(resourceBundle.getString("GPAY_0101_ID").toString());
	
	
    
	/************************************Allowance variables*****************************/    
	 
	 double allow0101=0; //for Pay-Officer
	 double allow0102=0; //for Pay Estt.
	 double allow0103=0; //for D.A
	 double allow0104=0; //for D.A
	 double allow0105=0; //for D.A
	 double allow0107=0; //for M.A
	 double allow0108=0; //for Bonus
	 double allow0109=0; //for L.E
	 double allow0110=0; //for H.R.A
	 double allow0111=0; //for C.L.A
	 double allow0112=0; //for I.R
	 double allow0113=0; //for T.A
	 double allow0117=0; //for T.A
	 double allow0118=0; //for T.A
	 double allow0119 = 0; //for Dearness Pay GO
	 double allow0120 = 0; //for Dearness Pay NGO
	 double allow1203=0; //for T.A
	 double allow1301=0; //for W.A
	 double allow2801=0; //for W.A
	 double gpay0101=0;//pay of officer  "0101 " means GradePay Code which will be added with PO/PE
	 
	 /************************ Recovery Part ********************/
	 double rec7057=0; //for F.A Recovery
	 double rec7058=0; //for F.G.A Recovery	 
	 double rec5701=0; //for F.A Recovery
	 double rec5801=0; //for F.G.A Recovery	 
	 double rec0101=0; //for F.G.A Recovery
	 double rec0105=0; 

	
	
	 
	 /************************************Deduction variables*****************************/
	 //Deduction 'A'
	 double rcp9670=0; //GPF ADV.
	 double rcp9620=0;
	 double rcp9590=0;
	 double rcp9600=0;
	 double rcp9583=0;
	 double rcp9592=0;
	 double rcp9591=0;
	 double rcp9587=0;
	 double rcp9586=0;
	 double rcp9585=0;
	 double rcp9584=0;
	 double rcp9582=0;
	 double rcp9581=0;
	 double rcp9580=0;
	 double rcp9550=0;
	 double rcp9533=0;
	 double rcp9570=0;
	 double rcp9560=0;
	 double rcp9532=0;
	 double rcp9531=0, rcp9534=0, rcp9530=0, rcp9520=0, rcp9510=0;
	 double rcp9910=0, rcp9790=0, rcp9780=0, rcp9760=0, rcp9740=0, rcp9720=0, rcp9690=0, rcp9680=0;
	 
	 double rcp9999=0;
	 double rcp9998=0;
	 
	 
	 //Total Amount
	 double txtExpenditure=0;
		double txtRecovery=0;
		double txtReceipt=0; 
		double deducA=0; //Deduction A
		double deducB=0; //Deduction B
		boolean NON_GAZETTED_BILL = false;
	 //ended by Ankit Bhatt
		
	//	Added by Paurav for Paybill Group Id
		long paybillGrpId = 0;
		//Ended by Paurav
	public synchronized ResultObject  generateArrearsPaybill(Map objectArgs)
	{
        //added by Ankit Bhatt
		logger.info("GenerateArrearsBillService start time " + System.currentTimeMillis());
	    //if counter=0,mean it is the firs record inserting in Paybill table, then it will b incremented.	    
		int counter=0; //used for checking nextSeqNumber of hr_pay_paybill
		long payBillIdCnt = 0;//by manoj for vacant post issue change
		IdGenerator idGen = new IdGenerator();//change by manoj for vacant post issue

	    //ended by Ankit Bhatt.
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		 boolean newBillFlag= false;
		// This paybillIndependentFlag will ensure that arrear is generated with help of Hrpayarrearbillpostmapping even if pay bill for that month not generated
		 boolean paybillIndependentFlag= false;  
         boolean arrearCompoAmFlg = false;
		 
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.MONTH,CPFMM);
		cal.set(Calendar.YEAR,CPFyyyy );
		cal.set(Calendar.DATE, 1);
		
		Date CPFDate = cal.getTime();
		
		GenerateBillService billService = new GenerateBillService();

		PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());//change by manoj for vacant post issue
        long GradeCode4=Long.parseLong(constantsBundle.getString("GradeCode4"));
        long GradeCode1=Long.parseLong(constantsBundle.getString("GradeCode1"));
        long GradeCode2=Long.parseLong(constantsBundle.getString("GradeCode2"));
        long GradeCode3=Long.parseLong(constantsBundle.getString("GradeCode3"));
        long AISGradeCode = Long.parseLong(constantsBundle.getString("AISGradeCode"));
        
        
		HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
		HttpServletRequestProxy proxyReq = new HttpServletRequestProxy(request);
		
		Map loginMap = (Map) objectArgs.get("baseLoginMap");

		try
		{
	         OrgPostMst orgPostMst = new OrgPostMst();
	         
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long locId=StringUtility.convertToLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);
			
			long langId=StringUtility.convertToLong(loginMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
            CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);  
 
            OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
            long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
            orgPostMst = orgPostMstDaoImpl.read(postId);
            
            
//			Added by Ankit Bhatt.
            int monthGiven = objectArgs.get("month")!=null?Integer.parseInt(objectArgs.get("month").toString()):-1;
			 int yearGiven = objectArgs.get("year")!=null?Integer.parseInt(objectArgs.get("year").toString()):-1;
			 //Added By Mrugesh
//			Added By Mrugesh
			 PaybillHeadMpg paybillheadmpgVO = new PaybillHeadMpg();
			 if(objectArgs.get("paybillHeadMpgVO")!=null)
				 paybillheadmpgVO   =  (PaybillHeadMpg)objectArgs.get("paybillHeadMpgVO");
			 //Ended
			 if(monthGiven==-1 && yearGiven==-1)
			 {				 
				 Date DBDate = DBUtility.getCurrentDateFromDB();
				 monthGiven = DBDate.getMonth()+1;
				 yearGiven=DBDate.getYear()+1900;
				 logger.info("DB Month is " + monthGiven + "\nCurrent year is " + yearGiven);				  				  	
			 
			 }
			 
			 TrnBillRegister trnBillReg = null;
		     long trnBillNo=0; //Bill No Generated in trn_bill_register for this Paybill
			 //by manoj for retirement date issue
		     long billNo = 0;
			 List postIdList = new ArrayList();
			 
			 
			 //String deptNo = (String)objectArgs.get("deptNo");
		     String demandNo = (String)objectArgs.get("demandNo");
		     String mjrHead = (String)objectArgs.get("mjrHead");
		     String subMjrHead =  (String)objectArgs.get("subMjrHead");
		     String mnrHead = (String)objectArgs.get("mnrHead");
		     String subHead = (String)objectArgs.get("subHead");
		     String dtlHead = (String)objectArgs.get("dtlHead");
		     String strGradeIds = (String)objectArgs.get("gradeId1");
		     String cntrlNo = (String)objectArgs.get("ctrlNo");
		     String salRevId = (objectArgs.get("arrearType")!=null&&!objectArgs.get("arrearType").equals(""))?objectArgs.get("arrearType").toString():"0";
			    
		     if(objectArgs.get("trnBillReg")!=null)
		     {
		    	 trnBillReg = (TrnBillRegister)objectArgs.get("trnBillReg");
		    	 trnBillNo =trnBillReg.getBillNo();
		    	 logger.info("tenbillReg is " + trnBillReg);
		     }
		     
		     logger.info("trnBillNo is " + trnBillNo);
		     
		     
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.YEAR, yearGiven);
            cal2.set(Calendar.MONTH, monthGiven-1);
            
            java.util.Date finYrDate = cal2.getTime();
		     
		     
		     SgvcFinYearMst finYrMst = payDao.getFinYrInfo(finYrDate, langId);
		     GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(SgvaBudsubhdMst.class);
			 genDAO.setSessionFactory(serv.getSessionFactory());
            
            SalRevMstDAOImpl revisionDao = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
            
            List<HrPaySalRevMst> salRevList = revisionDao.getActiveArrears(monthGiven, yearGiven, cmnLocationMst,salRevId);
            boolean isPaid = true;
            List arrearList = new ArrayList();
            OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
				 
				HrEisOtherDtlsHst hrEisOtherDtlsHst = new HrEisOtherDtlsHst();
				PayBillDAOImpl paybillDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
   				HrPayPaybill payBillVO = new HrPayPaybill();
				
   				ArrearBillDaoImpl arrearBillDao = new ArrearBillDaoImpl(HrPayArrearPaybill.class,serv.getSessionFactory()); 
        		HrPayArrearPaybill arrearBill = new HrPayArrearPaybill();	
				long paybillGenerated =0;
				 List<HrPayEdpCompoMpg> edpList= new ArrayList();	
				 
				 long longSubHeadId=0;
				 HrPayBillHeadMpg billHeadMpg = new HrPayBillHeadMpg();
			      
				logger.info("salRevList size::::::::::::::::::::::"+salRevList.size()); 
            if(salRevList!=null && salRevList.size()>0)
            {
            	logger.info("the list size is  "+salRevList.size());
            	for(Iterator revIt = salRevList.iterator();revIt.hasNext();)
            	{
            		
            		HrPaySalRevMst hrPaySalRevMst = (HrPaySalRevMst)revIt.next();
                	
            		Date payOutStartDate = hrPaySalRevMst.getRevPayOutDate();
            		Date arrearEffectiveStartDate = hrPaySalRevMst.getRevEffcFrmDate();
            		//Date arrearEffectiveEndDate = hrPaySalRevMst.getRevEffcToDate();
            		long frequencyMonths = hrPaySalRevMst.getRevFreqMthPaid();
            		long installmentNo = hrPaySalRevMst.getRevInstallments();
            		long arrearId  = hrPaySalRevMst.getSalRevId();
            		RltBillTypeEdp billTypeEdp = hrPaySalRevMst.getRltBillTypeEdp();  
            		
                    	List monthList = getMonthList(monthGiven,payOutStartDate,frequencyMonths,arrearEffectiveStartDate);
                    													 
			 double daInCash = hrPaySalRevMst.getCashPercentage()/100.0;
			 
			  if(hrPaySalRevMst.getPblIndependentFlg()!=null && hrPaySalRevMst.getPblIndependentFlg().equalsIgnoreCase("Y"))
				  paybillIndependentFlag = true;
					  
                    	
			 String cols[] = {"demandCode","budmjrhdCode","budsubmjrhdCode","budminhdCode","budsubhdCode","langId","finYrId"};//,"finYrId"
   			 Object vals[] = {demandNo,mjrHead,subMjrHead,mnrHead,subHead,cmnLanguageMst.getLangShortName(),finYrMst.getFinYearId()};//,String.valueOf(finYrMst.getFinYearId())
   			 List<SgvaBudsubhdMst> subHeadList = genDAO.getListByColumnAndValue(cols, vals);
   			 SgvaBudsubhdMst subHeadObj=null;
   			 
   			 String subHeadId="";
   			 if(subHeadList!=null && subHeadList.size() >0)
   			 {
   			    subHeadObj = subHeadList.get(0);
   			 longSubHeadId = subHeadObj.getBudsubhdId(); 
   			subHeadId = String.valueOf(longSubHeadId);
   				 logger.info("subHeadId is " + subHeadId);
   			 }
   			 else
   			 {
   				 logger.info("Null List get...");				   
   			 }

		      String newStrGradeIds = strGradeIds.substring(1);
		      
			 StringTokenizer gradeTokens = new StringTokenizer(strGradeIds,",");
				 
			 long[] gradeIds = new long[gradeTokens.countTokens()];
			 int i=0;
			 while(gradeTokens.hasMoreElements())
			 {
				 String gradeId = gradeTokens.nextElement().toString();
					 gradeIds[i++] = Long.parseLong(gradeId);
			 }
				 
			 //Added By Paurav for getting Designations from Request
			 String strDsgnIds = "";
			 strDsgnIds=objectArgs.get("designations")!=null?objectArgs.get("designations").toString():"";

		     logger.info("Designation Ids from request is " + strDsgnIds);
			 StringTokenizer dsgnTokens = new StringTokenizer(strDsgnIds,",");
			 
			 long[] dsgnIds = new long[dsgnTokens.countTokens()];
			 
			 int dgcnt=0;
			 while(dsgnTokens.hasMoreElements())
			 {
				 String dsgnId = dsgnTokens.nextElement().toString();
					 dsgnIds[dgcnt++] = Long.parseLong(dsgnId);
			 }
           
			 
		 for(Iterator monthIt = monthList.iterator();monthIt.hasNext();)
		 {
			 String monthYear = (String)monthIt.next();
			 
			 String[] monthYearArray = monthYear.split("-");
			 
			 int paybillMonth = Integer.parseInt(monthYearArray[0]);
			 int paybillYear = Integer.parseInt(monthYearArray[1]);
			 
			 logger.info("the month is "+paybillMonth +" - "+paybillYear);
           				 
		     Map headValues= new HashMap();
		     headValues.put("demandNo", demandNo);
		     headValues.put("mjrHead", mjrHead);
		     headValues.put("subMjrHead", subMjrHead);
		     headValues.put("mnrHead", mnrHead);
		     headValues.put("subHead", subHead);
		     headValues.put("langId", cmnLanguageMst.getLangShortName());
		     
            SgvcFinYearMst FinYrMst =  payDao.getFinYrInfo(finYrDate, langId);
		     
		     headValues.put("fin_year_id", FinYrMst.getFinYearId());
		        
		     // Added By Urvin Shah.
		     long postType = 0;
		     if(objectArgs.get("paybillNo")!=null){
		    	 billNo = Long.parseLong(objectArgs.get("paybillNo").toString());
		    	 logger.info("Bill No isssss:-"+billNo);
		    	 BillHeadMpgDAO billHeadMpgDAO = null;//new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class,serv.getSessionFactory());
		    	 billHeadMpg = null;//(HrPayBillHeadMpg)billHeadMpgDAO.read(billNo);
		     }
           		     // End.
           				 
           				 postType=0;//hardcoded done as no need to chk for the post type ...post id coming from paybill table
           				 
           				 
           				GenericDaoHibernateImpl arrearBillpostMpgDAO = new GenericDaoHibernateImpl(HrPayArrearBillpostMpg.class);
           				String colsLoanEmpDtls[] = {"hrPaySalRevMst","hrPayBillHeadMpg"}; 
           				Object valsLoanEmpDtls[] = {hrPaySalRevMst,billHeadMpg}; 
           				arrearBillpostMpgDAO.setSessionFactory(serv.getSessionFactory());
           				List<HrPayArrearBillpostMpg> arrearBillpostMpgList;
           				arrearBillpostMpgList = arrearBillpostMpgDAO.getListByColumnAndValue(colsLoanEmpDtls, valsLoanEmpDtls);
           				List paybillEmpList;
           				
           				newBillFlag =(arrearBillpostMpgList!=null && arrearBillpostMpgList.size()>0);
           				
           				if(newBillFlag)
           				{
           					if(paybillIndependentFlag) 
               					paybillEmpList = paybillDao.getarrearBillDataListPaybillIndependent(paybillMonth, paybillYear,billNo,hrPaySalRevMst.getSalRevId(),locId);
           					else
               					paybillEmpList = paybillDao.getarrearBillDataList(paybillMonth, paybillYear,billNo,hrPaySalRevMst.getSalRevId(),locId);
           				}
           				else
           				 paybillEmpList = paybillDao.getPayBillDataList(paybillMonth, paybillYear, subHeadId,postType,newStrGradeIds,strDsgnIds,billNo);
           				 
        					HrPayPaybill oldPaybill = new HrPayPaybill();
        					HrPayArrearBillpostMpg hrPayArrearBillpostMpg = new HrPayArrearBillpostMpg();
           				 for(int cnt=0;cnt<paybillEmpList.size();cnt++)
           				 {           					
           					 hrEisOtherDtlsHst = new HrEisOtherDtlsHst();
           					
            					long paybillEmpId = 0;
            					long paybillPostId = 0; 
           					if(newBillFlag)
           					{
	           					Object[] row = (Object[])paybillEmpList.get(cnt);
	           					
	           					if(!paybillIndependentFlag) 
	           					oldPaybill = (HrPayPaybill)row[0]; 
	           					
	       					    hrPayArrearBillpostMpg = (HrPayArrearBillpostMpg)row[1]; 
	       					    if(hrPayArrearBillpostMpg.getHrEisEmpMst()!=null)
	       					    paybillEmpId = hrPayArrearBillpostMpg.getHrEisEmpMst().getEmpId();
	       					    if(hrPayArrearBillpostMpg.getHrEisEmpMst()!=null)
	       					    paybillPostId = hrPayArrearBillpostMpg.getOrgPostMst().getPostId();//oldPaybill.getOrgPostMst().getPostId();

           					}
           					else
           					{	
           						oldPaybill = (HrPayPaybill)paybillEmpList.get(cnt); 
               					paybillEmpId = oldPaybill.getHrEisEmpMst().getEmpId();
	       					    paybillPostId = oldPaybill.getOrgPostMst().getPostId();

           					}	
           					
           					 
           					 
           					arrearList = arrearBillDao.isArrearPaid(paybillMonth, paybillYear, arrearId,paybillEmpId,paybillPostId,billTypeId);
                    		
                    		
                    		if(arrearList!=null && arrearList.size()>0)
                    		{
                    			isPaid = true;
                    		}
                    		else
                    		{
                    			isPaid = false;
                    		}
                    		
                    		if(!isPaid)
	                    	{
	           					 
	           					 
	           					
	           					List hrOtherDtlsList = otherdtlsDao.getOtherDataFromHst(paybillEmpId,paybillMonth-1,paybillYear);
	           					
	           					 
	           					if(hrOtherDtlsList!=null && hrOtherDtlsList.size()>=0 )
	           						hrEisOtherDtlsHst = (HrEisOtherDtlsHst)hrOtherDtlsList.get(0);
	           				        
	           				       
	           				        
	           				        
	           				        
	           					if(!postIdList.contains(paybillPostId))
					    	 	{
					    	 		postIdList.add(paybillPostId);
					    	 	}
	//           				for time management
	           					
	           					HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class,serv.getSessionFactory());
	           					
	           						           					
	           					//by manoj for getting the id of the sp and pp
	           					long spID=0;
	           					long ppID=0;
	           					String advCodeID="";
	           					long edpID=0;
	           					String edpCdId="";
	           		                     					
	           					Date sysdate = new Date();
	
	           					List<HrPayEdpCompoMpg> allowList=hrEdp.getListByColumnAndValue("cmnLookupId",new Long(allowLookupId));
	           					
	           					for(int j=0;j<allowList.size();j++)
	           					{
	           						HrPayEdpCompoMpg hrEdpComp=allowList.get(j);
	           						
	           						advCodeID=String.valueOf(hrEdpComp.getTypeId());
	           						edpID=hrEdpComp.getRltBillTypeEdp().getTypeEdpId();
	           						
	           													
	           						GenericDaoHibernateImpl genericDAO = new GenericDaoHibernateImpl(RltBillTypeEdp.class);
	           						//GenericDaoHibernateImpl genericDAO = new GenericDaoHibernateImpl(RltBillTypeEdp.class,serv.getSessionFactory())
	           						genericDAO.setSessionFactory(serv.getSessionFactory());
	           						List<RltBillTypeEdp> rltEDPList = genericDAO.getListByColumnAndValue("typeEdpId", edpID);
	           						
	           						if(rltEDPList.size()>0)
	           						{
	           							if(rltEDPList.get(0).getEdpCode()!=null)
	           							{
	           								edpCdId = rltEDPList.get(0).getEdpCode();
	           								//as only one code is mapped to one type of component
	           							}
	           							if(edpCdId.equals("0101"))//Personal Pay
	           							{
	           								ppID = Long.parseLong(advCodeID);
	           							}
	           							else if(edpCdId.equals("0102"))//Special Pay
	           							{
	           								spID=Long.parseLong(advCodeID);
	           							}
	           						}
	           					}
	           					
	           					Calendar calc = Calendar.getInstance();
	           					calc.set(Calendar.MONTH, monthGiven-1);
	           					calc.set(Calendar.YEAR, yearGiven);
	           					 
	           					
	           					//Added by Paurav for taking EDP only once
	           					edpList = hrEdp.getAllData();
	           					//Ended By Paurav
	           					
	           					long maxDaysOfPost=-1;
	           					long maxDaysUserPostRltId=0;
	           					long maxDaysUserId=0;
	           					
	           					
	           						OrgUserMst userMst = hrEisOtherDtlsHst.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst();
	           						Set orguserPostrlt;
	           						
	           						if(!newBillFlag)
	           						orguserPostrlt = otherdtlsDao.getUserPostRltForEmp(userMst, paybillMonth-1,paybillYear,cmnLocationMst);
	           						else
	           						orguserPostrlt =arrearBillDao.getUserPostRltForEmp(userMst,paybillPostId,cmnLocationMst);
	           						
	           						HrEisEmpMst hrEisMst = hrEisOtherDtlsHst.getHrEisEmpMst();
	           						
	           						Map maxDaysMap = billService.checkMaxDayOfPostRecord(orguserPostrlt ,paybillMonth,paybillYear);
	           						 
	           						maxDaysOfPost=Long.parseLong(maxDaysMap.get("maxDaysOfPost").toString());
	           						maxDaysUserPostRltId=Long.parseLong(maxDaysMap.get("maxDaysUserPostRltId").toString());
	           						maxDaysUserId=Long.parseLong(maxDaysMap.get("maxDaysUserId").toString());
	           						
	           						
	           						for(Iterator upIt=orguserPostrlt.iterator();upIt.hasNext();)
	           						{
	           							payBillVO = new HrPayPaybill();
	           							arrearBill = new HrPayArrearPaybill();
	           							OrgUserpostRlt orgUPRlt = (OrgUserpostRlt)upIt.next();
	           							
	           							Map inputMap = objectArgs;
	           							
	           							inputMap.put("monthGiven",paybillMonth);
	           							inputMap.put("yearGiven",paybillYear);
	           							inputMap.put("oldPaybill",oldPaybill);
	           							inputMap.put("orgUPRlt", orgUPRlt);
	           							inputMap.put("postIdList",postIdList);
	           							inputMap.put("hrEisOtherDtlsHst",hrEisOtherDtlsHst);
	           							inputMap.put("spID", spID);
	           							inputMap.put("ppID", ppID);
	           							
	           							inputMap.put("maxDaysOfPost", maxDaysOfPost);
	           							inputMap.put("maxDaysUserPostRltId", maxDaysUserPostRltId);
	           							inputMap.put("maxDaysUserId", maxDaysUserId);
	           							
	           							inputMap.put("requestObj", request);
	           							inputMap.put("proxyReq", proxyReq);
	           							
	           							Map resultMap = new HashMap();
	           							
		           	           		    List<HrPayArrearCompoAmtMpg> arrearCompoAmtList = null;
                                        
	           							if(newBillFlag)
	           							{
		           	           				GenericDaoHibernateImpl arrearCompoAmtMpgDAO = new GenericDaoHibernateImpl(HrPayArrearCompoAmtMpg.class);
		           	           				String colsArrearCompoAmt[] = {"arrearBillpostMpg"}; 
		           	           				Object valsArrearCompoAmt[] = {hrPayArrearBillpostMpg}; 
		           	           			    arrearCompoAmtMpgDAO.setSessionFactory(serv.getSessionFactory());
		           	           			    arrearCompoAmtList = arrearCompoAmtMpgDAO.getListByColumnAndValue(colsArrearCompoAmt, valsArrearCompoAmt);
		           	           			    logger.info("size of arrearCompoAmtList::"+arrearCompoAmtList.size());
		           	           			    if(arrearCompoAmtList!=null && arrearCompoAmtList.size()>0) {
		           	           			    	arrearCompoAmFlg = true;
		           	           			    	logger.info(arrearCompoAmtList.get(0).getAmount() + " " + arrearCompoAmtList.get(0).getCompoAmtMpgId());
		           	           			    }
	           							}

	           							if(newBillFlag && arrearCompoAmFlg)
	           							{
		           							logger.info("no     ArrearBillCoreLogic");
	           							}
	           							else if(billTypeEdp.getTypeEdpId()==Long.parseLong(resourceBundle.getString("DA_EDP_ID").toString() ))
	           							{	
		           							GenerateDaArrearBillCoreLogic coreLogic = new GenerateDaArrearBillCoreLogic();
		           							logger.info("insied     GenerateDaArrearBillCoreLogic");
		           							synchronized(GenerateArrearBillService.class)
		           							{
		           							 resultMap = coreLogic.executeCoreLogic(inputMap);
		           							}
	           						    }	           							
	           						    
	           						    else
	           						    {
	           						    	GenerateArrearsBillCoreLogic coreLogic = new GenerateArrearsBillCoreLogic();
	           						    	logger.info("insied     GenerateArrearBillService");
		           							synchronized(GenerateArrearBillService.class)
		           							{
		           							 resultMap = coreLogic.executeCoreLogic(inputMap);
		           							}
	           						    }	
	           						    	
	           							long gpfGrade = 0;	           							
	           							String gpfAccNo ="";

	           							// for gpf grade no
	           							 long userIdGpf = hrEisOtherDtlsHst.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
	           								EmpGpfDtlsDAOImpl gpfDtlsDAO = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
	           								List gpfGradeList = gpfDtlsDAO.getAllGpfDetailsbyUserId(userIdGpf);
	           								if(gpfGradeList!=null && gpfGradeList.size()>0)
	           								{
	           									Iterator itr = gpfGradeList.iterator();  
	           									Object[] rowList = (Object[]) itr.next();
	           									if(rowList[3]!=null)
	           									gpfGrade=Long.parseLong(rowList[3].toString());
	           									gpfAccNo=rowList[0]!=null?rowList[0].toString():"";
	           								}

	           								           							
	           							SimpleDateFormat sdfDOJ = new SimpleDateFormat("dd-mm-yyyy");
	           							
	           							Date doj = sdfDOJ.parse((hrEisMst.getOrgEmpMst().getEmpDoj()).toString());
	           							
	           							//Map resultMap = coreLogic.executeCoreLogic(inputMap);
	           							logger.info("resultMap after core logic in GenerateBillService " + resultMap);
	           							double arrearDiff =0;
	           							if(resultMap.containsKey("payBillVO") || (newBillFlag && arrearCompoAmFlg))
	           							{
	           							 if(!(newBillFlag && arrearCompoAmFlg))	
	           							 payBillVO = (HrPayPaybill)resultMap.get("payBillVO");
	           							
	           							 String EdpCode= billTypeEdp.getEdpCode();
	           								           							 
		           			              if(newBillFlag && arrearCompoAmFlg)
		           			              {			           			            	
		           	           			    
		           	           			    Object[] objArgs;
			           			            Class arrearClass = arrearBill.getClass();
		           	           			    
			           			            if(arrearCompoAmtList!=null)
		           			            	for(int count=0;count<arrearCompoAmtList.size();count++)
		           			            	{
				           			             String mthdSetter = arrearCompoAmtList.get(count).getRltBillTypeEdp().getEdpMethodName();
				           			             
				           			             mthdSetter = "set"+mthdSetter;   	 
		           			            		 objArgs = new Object[]{new Double(arrearCompoAmtList.get(count).getAmount())};
		           			            		 
				           			             Method Mthd = arrearClass.getMethod(mthdSetter, new Class[]{double.class});
				           			             Mthd.invoke(arrearBill, objArgs);
		           			            	}
		           			             	
			           			         if(billTypeEdp.getTypeEdpId()==Long.parseLong(resourceBundle.getString("LE_EDP_ID").toString() )) {

			           			        	arrearBill.setAllow0109(arrearBill.getAllow0101() + arrearBill.getAllow0102());			           			        	
			           			        	//arrearBill.setLe(arrearBill.getAllow0101() + arrearBill.getAllow0102());
        								         
        							     }
			           			            arrearBill.setGrossAmt(
			           			            		arrearBill.getAllow0108() + 
			           			            		arrearBill.getAllow0101() + 
			           			            		arrearBill.getAllow0102() + 
			           			            		arrearBill.getBasic0101() + 
			           			            		arrearBill.getBasic0102() + 
			           			            		arrearBill.getLs() + 
			           			            		arrearBill.getLe() +
			           			            		arrearBill.getAllow0119() + 
			           			            		arrearBill.getAllow0120() + 
			           			            		arrearBill.getAllow0103() + 
			           			            		arrearBill.getAllow0110() + 
			           			            		arrearBill.getAllow0105() + 
			           			            		arrearBill.getAllow0111() + 
			           			            		arrearBill.getAllow0104() + 
			           			            		arrearBill.getAllow0107() + 
			           			            		arrearBill.getAllow1301() + 
			           			            		arrearBill.getAllow5006() + 
			           			            		arrearBill.getAllow0113() +
			           			            		arrearBill.getGpay0101() /*- 
			           			            		arrearBill.getAdv7057() - 
			           			            		arrearBill.getAdv7058() - 
			           			            		arrearBill.getDeduc0101()  */
			           			            );

		           			            }
		           			            else if(billTypeEdp.getTypeEdpId() > 0)
		           			            {
		           			            	 		           			            	 
	           			            	 String mthdGetter ="";		            	 
	           			            	 String mthdSetter ="";	
		           			            	 		           			            	 
           			            	     String methodName = billTypeEdp.getEdpMethodName();
           			            	     mthdGetter = "get"+billTypeEdp.getEdpMethodName();//for deduction arrear
           			            	     mthdSetter = "set"+methodName;//for deduction arrear			        	  
	
			           			         Class c = payBillVO.getClass();
		           			             Method Mthd = c.getMethod(mthdGetter, null);
		           			             double newAllowDeduc = Double.parseDouble(((Mthd.invoke(payBillVO, null)!=null)?(Mthd.invoke(payBillVO, null).toString()):"0"));
		           			           	 
		           			           	 // for prev Bill AMt
		           			             c = oldPaybill.getClass();
		           			             Mthd = c.getMethod(mthdGetter, null);
		           			             double oldAllowDeduc = Double.parseDouble(((Mthd.invoke(oldPaybill, null)!=null)?(Mthd.invoke(oldPaybill, null).toString()):"0"));
		           			             
		           			             arrearDiff = (newAllowDeduc - oldAllowDeduc)*installmentNo;
		           			             
		           			            
	           							 logger.info("edpCode is "+EdpCode+"old amt is "+oldAllowDeduc+" and new amt is "+newAllowDeduc +" the arrear diffence is "+arrearDiff);	           							
		           			             
		           			             Object[] objArgs = new Object[]{new Double(arrearDiff)};
		           			             c = arrearBill.getClass();
		           			             Mthd = c.getMethod(mthdSetter, new Class[]{double.class});
		           			             Mthd.invoke(arrearBill, objArgs);
		           			             
	           							 arrearBill.setDeduc9534((payBillVO.getDeduc9534()-oldPaybill.getDeduc9534())*installmentNo);//CPF
	           							 arrearBill.setDeduc9570((payBillVO.getDeduc9570()-oldPaybill.getDeduc9570())*installmentNo);//pt
	           							 arrearBill.setGrossAmt(arrearDiff);
	           							
	           							 if(!gpfAccNo.trim().equals(""))
	           							 {	 
	           							 if(gpfGrade==GradeCode4)
       							         {	           						
	           							    arrearBill.setDeduc9998(arrearBill.getGrossAmt()-Math.round(daInCash*arrearBill.getGrossAmt()+arrearBill.getDeduc9570()));
       							         }
	           							 else
	           							 {
	           								arrearBill.setDeduc9999(arrearBill.getGrossAmt()-Math.round(daInCash*arrearBill.getGrossAmt()+arrearBill.getDeduc9570()));
	           							 }
	           							 }
	           							
	           							 //end for cash  percentage
		           			             
		           			            }
		           			            else
		           			            {	 
		           			             arrearBill.setAllow0103((payBillVO.getAllow0103()-oldPaybill.getAllow0103())*installmentNo);//da	
	           							 arrearBill.setGrossAmt(arrearBill.getAllow0103());

	           							 if(!gpfAccNo.trim().equals(""))
	           							 {	 
	           							 if(gpfGrade==GradeCode4)
       							         {	           						
	           							    arrearBill.setDeduc9998(arrearBill.getGrossAmt()-Math.round(daInCash*arrearBill.getGrossAmt()+arrearBill.getDeduc9570()));
       							         }
	           							 else
	           							 {
	           								arrearBill.setDeduc9999(arrearBill.getGrossAmt()-Math.round(daInCash*arrearBill.getGrossAmt()+arrearBill.getDeduc9570()));
	           							 }
	           							 }
	           							 
		           			             logger.info("old Da is "+oldPaybill.getAllow0103()+" and new da is "+payBillVO.getAllow0103() +" the da diffence is "+(payBillVO.getAllow0103()-oldPaybill.getAllow0103()));	           							
		           			            }
	           							
	           							double basic0101 = arrearBill.getBasic0101();
	           							double basic0102 = arrearBill.getBasic0102();
	           							double deduc9999 = arrearBill.getDeduc9999();
	           							double deduc9998 = arrearBill.getDeduc9998();

	           							
	           							double totalDeduc =
	           							arrearBill.getIt()+arrearBill.getSurcharge()+arrearBill.getDeduc9550()+arrearBill.getDeduc9560()+
	           							arrearBill.getDeduc9530()+arrearBill.getDeduc9540()+arrearBill.getDeduc9570()+
	           							arrearBill.getDeduc9580()+arrearBill.getDeduc9581()+arrearBill.getDeduc9582()+
	           							arrearBill.getDeduc9583()+arrearBill.getDeduc9584()+arrearBill.getDeduc9670()+
	           							arrearBill.getDeduc9534()+arrearBill.getDeduc9620()+arrearBill.getDeduc9531()+
	           							deduc9999+deduc9998;
	           							
	           							logger.info(arrearBill.getGrossAmt()+"gross********************************the total deduc is "+totalDeduc);
	           						
	           							
	           							arrearBill.setTotalDed(totalDeduc);//total deduction
	           							arrearBill.setNetTotal(arrearBill.getGrossAmt()-totalDeduc);//net amount
	           							
	           							
	           							if(resultMap.containsKey("proxyReq"))
	           							  proxyReq = (HttpServletRequestProxy)resultMap.get("proxyReq");
	           							
	           							long arrearPayBillId;
	           				        	arrearPayBillId = idGen.PKGenerator("hr_pay_arrear_paybill", objectArgs);
	           				        	arrearBill.setId(arrearPayBillId);
	           							
	           							
	           							//Added by Paurav for saving Group Id
	           							if(paybillGrpId == 0)
	           							{
	           								paybillGrpId = arrearPayBillId;
	           								
	           							}
	           							
	           							arrearBill.setPaybillGrpId(paybillGrpId);
	           							//ended By Paurav
	           							
	           							long psrNo=arrearBillDao.getPsrNo(billNo, paybillPostId, locId);
	           							if(psrNo>0)
			           						arrearBill.setPsrNo(psrNo);//psr number
	           							else	
		           						arrearBill.setPsrNo(oldPaybill.getPsrNo());//psr number
	           							
	           							arrearBill.setHrEisEmpMst(hrEisMst);
	           					        // Also capturing postId
	           							OrgPostMst payBillPostMst = new OrgPostMst();  
	           					                  					        
	           					     payBillPostMst=orgPostMstDaoImpl.read(paybillPostId);
	           					        
	           					     arrearBill.setOrgPostMst(payBillPostMst);
	
	           					  arrearBill.setCmnDatabaseMst(cmnDatabaseMst);
	           					arrearBill.setCmnLocationMst(cmnLocationMst);
	           					arrearBill.setCreatedDate(sysdate);
	           					arrearBill.setOrgUserMstByCreatedBy(orgUserMst);
	           					arrearBill.setOrgPostMstByCreatedByPost(orgPostMst);
	           					
	           					
	           					arrearBill.setSalRevId(hrPaySalRevMst);
	           					
	           					if(!paybillIndependentFlag)
	           					arrearBill.setPaybillId(oldPaybill);
	           					
	           							//Added by Mrugesh for Trn Counter
	           					arrearBill.setTrnCounter(new Integer(1));
	           					        
	           					         //for setting the current month and year
	           					        //edited by Ankit Bhatt for setting given month & year
	           					        if(yearGiven!=-1 && monthGiven!=-1 )
	           					        {
	           					        	//Modified By Mrugesh
	           					        	paybillheadmpgVO.setMonth(monthGiven);
	           					        	paybillheadmpgVO.setYear(yearGiven);
	           					        	//Ended By Mrugesh
	           					        }
	           					        else
	           					        {
	           						        SimpleDateFormat sdf = new SimpleDateFormat("MM");
	           						        Date date = new Date();
	           						        String month= sdf.format(date);
	           						        
	           						        if(month.charAt(0)=='0')
	           						        {
	           						        	month=month.substring(1,month.length());
	           						        }
	           						        
	           						        sdf = new SimpleDateFormat("yyyy");
	           						        String year= sdf.format(date);
	           						        
	           						      //Modified By Mrugesh  
	           						     paybillheadmpgVO.setMonth(Integer.parseInt(month));
	           						  paybillheadmpgVO.setYear(Integer.parseInt(year));
	           						  //Ended By Mrguesh
	           					        }
	           							
	           					       
	           					        arrearBill.setBillNo(cntrlNo);
	           							
	           					        
	           					        HrEisOtherDtls hrEisOtherDtls = new  HrEisOtherDtls();
	           					        hrEisOtherDtls = otherdtlsDao.getOtherData(hrEisMst.getOrgEmpMst().getEmpId());	           					        
	           					        arrearBill.setHrEisOtherDtls(hrEisOtherDtls);
	           					        arrearBill.setOtherTrnCntr(hrEisOtherDtls.getTrnCounter());
	           					        
	           					        arrearBillDao.create(arrearBill);
	           							paybillGenerated++;
	           							
	           							if(counter==0)
	           							{
	           								payBillIdCnt = arrearPayBillId;						
	           								counter++;
	           							}
	           							
	           							objectArgs.put("status", "11");
	           							
	           							//by manoj for exception handling in outer
	           							objectArgs.put("paybillGenerated", "1");
	           							//end by manoj for exception handling in outer
	
	           							Map paramMap = new HashMap();
	           							if(resultMap.containsKey("paramMap"))
	           							  paramMap = (Map)resultMap.get("paramMap");
	           							else
	           							{
	           								HrPayEdpCompoMpg edpComp;
	           								long basicLookupId = Long.parseLong(resourceBundle.getString("basicLookupId"));

	           								List<HrPayEdpCompoMpg> basicList = hrEdp.getListByColumnAndValue("cmnLookupId",new Long(basicLookupId));

	           								if(basicList!=null && basicList.size()>0)
	           								{
	           									
	           									for(int cntBasic=0;cntBasic<basicList.size();cntBasic++)
	           									{
	           										try
	           										{
	           										edpComp = new HrPayEdpCompoMpg();
	           										
	           										edpComp = basicList.get(cntBasic);
	           										
	           										String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());
	           										String edpAddDedFlag = edpComp.getRltBillTypeEdp().getAddDedFlag();
	           										long typeEdpId = edpComp.getRltBillTypeEdp().getTypeEdpId();
	           										String expRcpRec = edpComp.getRltBillTypeEdp().getExpRcpRec();
	           										
	           										Double  value;
	           										String mthdName = "getBasic"+edpCode;
	           										String payBillMthdName = "setBasic"+edpCode;
	           										Class pay = arrearBill.getClass();
	           										Method payMthd = pay.getMethod(mthdName, null);
	           										value = (Double)payMthd.invoke(arrearBill, null);
	           											           										
	           										if(paramMap.containsKey(typeEdpId))
	           										{
	           											EdpDtlsVO edpDtlsVO = (EdpDtlsVO)paramMap.get(typeEdpId);
	           											double existValue = edpDtlsVO.getAmount();
	           											existValue+=value;
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(existValue);
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											//paramMap.put(edpCode,existValue);
	           											paramMap.put(typeEdpId,edpDtlsVO);
	           										}
	           										else
	           										{
	           											EdpDtlsVO edpDtlsVO = new EdpDtlsVO();
	           											edpDtlsVO.setEdpCode(edpCode);
	           											long gradeId = arrearBill.getHrEisEmpMst().getOrgEmpMst().getOrgGradeMst().getGradeId();
	           											if((GradeCode3==gradeId || GradeCode4==gradeId)) {
	           												if(edpCode.equals("0102"))
	           													edpDtlsVO.setAmount(value+arrearBill.getLs());
	           												else
	           													edpDtlsVO.setAmount(value);
	           											}
	           											else
	           											{
	           												if(edpCode.equals("0101"))
	           													edpDtlsVO.setAmount(value+arrearBill.getLs());
	           												else
	           													edpDtlsVO.setAmount(value);
	           											}
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);
	           										  //paramMap.put(edpCode,value);
	           										}

	           										}
	           										catch(Exception e)
	           										{
	           											e.printStackTrace();
	           										}
	           									}
	           								
	           								}

	           								
	           								EdpDtlsVO  edpDtlsVO = new EdpDtlsVO(); //custom VO which contains Edp-Code, Amount and Flag for Allowance/Deduction
	           								HrPayEdpCompoMpg hrPayEdpCompoMpg = new HrPayEdpCompoMpg();
	           								String edpAddDedFlag ="";
	           								String edpCode ="";
	           								String expRcpRec ="";
	           								long typeEdpId = 0;
	           								com.tcs.sgv.eis.valueobject.RltBillTypeEdp rltBill = new com.tcs.sgv.eis.valueobject.RltBillTypeEdp();
	           								for(Iterator edpIt=edpList.iterator();edpIt.hasNext();)
	           								{
	           									edpDtlsVO = new EdpDtlsVO();
	           									hrPayEdpCompoMpg = (HrPayEdpCompoMpg)edpIt.next();
	           									rltBill = hrPayEdpCompoMpg.getRltBillTypeEdp();
	           									edpAddDedFlag = rltBill.getAddDedFlag();
	           									edpCode = rltBill.getEdpCode();
	           									typeEdpId = rltBill.getTypeEdpId();
	           									expRcpRec = hrPayEdpCompoMpg.getRltBillTypeEdp().getExpRcpRec();
	           									if(hrPayEdpCompoMpg.getCmnLookupId()==allowLookupId || hrPayEdpCompoMpg.getCmnLookupId()== deducLookupId)
	           									{	
	           										System.out
															.println("from bill service "+edpCode+" and "+gpfGrade+" deduc9999 "+deduc9999 );
	           										System.out
															.println("billTypeEdp.getTypeEdpId() " + billTypeEdp.getTypeEdpId());
	           									
	           									//09-12-2010	
	           										//if(resourceBundle.getString("LE_EDP_ID").toString() )
	           									//added by Ankit Bhatt
		           								if(billTypeEdp.getTypeEdpId()==Long.parseLong(resourceBundle.getString("LE_EDP_ID").toString() ))
	           										{
	           											logger.info("Inside LE_EDP_ID 123:::::::::::::::::::::::::::::: "); 
	           										if(edpCode.equalsIgnoreCase("0101"))
	           										{
	           											if(paramMap.containsKey(typeEdpId)) {
	           												edpDtlsVO = (EdpDtlsVO) paramMap.get(typeEdpId);
	           												System.out
																	.println("0101 got from paramMap " + edpDtlsVO.getAmount());           												
	           											}
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(0);
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);
	           										}
	           										
	           										if(edpCode.equalsIgnoreCase("0102"))
	           										{
	           											if(paramMap.containsKey(typeEdpId)) {
	           												edpDtlsVO = (EdpDtlsVO) paramMap.get(typeEdpId);
	           												System.out
															.println("0102 got from paramMap " + edpDtlsVO.getAmount());
	           											}
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(0);
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);
	           										}
	           										
	           										if(edpCode.equalsIgnoreCase("0109")) {
	           											edpDtlsVO.setEdpCode("0109");
	           											edpDtlsVO.setAmount(arrearBill.getLe());
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(3L,edpDtlsVO);
	           										}
	           							 	           									    
	           										}
		           									//ended
	           										
	           										if(edpCode.equalsIgnoreCase("0103"))
	           										{
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(arrearBill.getAllow0103());
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);

	           										}
	           										else if(edpCode.equalsIgnoreCase("9570"))
	           										{
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(arrearBill.getDeduc9570());
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);

	           										}
	           										else if(edpCode.equalsIgnoreCase("9534") && arrearBill.getDeduc9534()!=0)
	           										{
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(arrearBill.getDeduc9534()+deduc9999);
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);

	           										}
	           										else if(edpCode.equalsIgnoreCase("9531") && gpfGrade==GradeCode4)
	           										{
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(arrearBill.getDeduc9531()+deduc9998);
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);

	           										}
	           										else if(edpCode.equalsIgnoreCase("9620") && gpfGrade==AISGradeCode)
	           										{
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(arrearBill.getDeduc9620()+deduc9999);
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);

	           										}
	           										else if(edpCode.equalsIgnoreCase("9670") && (gpfGrade!=AISGradeCode || gpfGrade!=GradeCode4))
	           										{
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(arrearBill.getDeduc9670()+deduc9999);
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO);

	           										}
	           										else
	           										{

	           											logger.info("*****in else edpCode in else "+edpCode);
	           											edpDtlsVO.setEdpCode(edpCode);
	           											edpDtlsVO.setAmount(0);
	           											edpDtlsVO.setAddDedFlag(edpAddDedFlag);
	           											edpDtlsVO.setExpRcpRec(expRcpRec);
	           											paramMap.put(typeEdpId,edpDtlsVO); 
	           										}
	           							      
	           									}
	           									
	           								}
	           								
	           							}
	           							
	           							long gradeId = arrearBill.getHrEisEmpMst().getOrgEmpMst().getOrgGradeMst().getGradeId();
	           							boolean officerFlg = gradeId != GradeCode3 && gradeId != GradeCode4;
	           							
	           					        EdpDtlsVO incomeTaxVO = new EdpDtlsVO();
	           					        incomeTaxVO.setEdpCode("9510");
	           					        incomeTaxVO.setAmount(arrearBill.getIt());
	           					        incomeTaxVO.setAddDedFlag("-(d)");
	           					        incomeTaxVO.setExpRcpRec("RCP");
	           							paramMap.put(20L,incomeTaxVO);

	           							//added by ankit bhatt
	           						 if(!(billTypeEdp.getTypeEdpId()==Long.parseLong(resourceBundle.getString("LE_EDP_ID").toString()))) {
	           					        if(officerFlg)
	           					        {
	           					        EdpDtlsVO POVO = new EdpDtlsVO();
	           					        POVO.setEdpCode("0101");
	           					        POVO.setAmount(basic0101+arrearBill.getLs()+arrearBill.getLe());
	           					        POVO.setAddDedFlag("A");
	           					        POVO.setExpRcpRec("EXP");
	           							paramMap.put(1L,POVO);
	           					        }
	           					        else
	           					        {
	           					        EdpDtlsVO PEVO = new EdpDtlsVO();
	           					        PEVO.setEdpCode("0102");
           					        	PEVO.setAmount(basic0102+arrearBill.getLs()+arrearBill.getLe());
	           					        PEVO.setAddDedFlag("A");
	           					        PEVO.setExpRcpRec("EXP");
	           							paramMap.put(2L,PEVO);
	           					        }
	           							
	           					        //added by Ankit
	           						 }
	           						 //ended
	           							
	           							Set paramSet = paramMap.keySet();
	           							String methodName="";
	           							
	           							Class payBill = arrearBill.getClass();
	           							
	           							Method payMthd = null;
	           							
	           							
	           							for(Iterator paramIt = paramSet.iterator();paramIt.hasNext();)
	           							{
	           								Object typeEdpId = paramIt.next(); //key will be the Type_edp_id = PK of rlt_bill_type_edp
	           								EdpDtlsVO edpDtlsVO = (EdpDtlsVO)paramMap.get(typeEdpId);
	           								logger.info("EdpDTLSVO get " + edpDtlsVO);
	           								
	           								String addDedFlag = "";
	           								if(edpDtlsVO.getAddDedFlag()!=null)
	           								 addDedFlag = edpDtlsVO.getAddDedFlag();
	           								logger.info("add ded flag is " + addDedFlag);
	           								
	           								String edpCode = edpDtlsVO.getEdpCode();
	           								logger.info("Edp code is " + edpCode);
	           								
	           								
	           								if(addDedFlag.equalsIgnoreCase("A"))
	           									methodName="getAllow"+edpCode;
	           								else if(addDedFlag.equalsIgnoreCase("-(d)"))
	           									methodName="getDeduc"+edpCode;
	           								if(edpCode.equalsIgnoreCase("0101 ") && addDedFlag.equalsIgnoreCase("A") )   //"0101 " means GradePay Code which will be added with PO/PE 
	           								{
	           									methodName="getGpay"+edpCode;
	           									methodName = methodName.trim();
	           								}	           							
	           								payMthd = payBill.getMethod(methodName, null);
		           							double value = (Double)payMthd.invoke(arrearBill, null);
	           								
	           								//double value = edpDtlsVO.getAmount(); //value will be the value of EdpCode.
	           								logger.info("value is " + value);

	           								if(edpCode.equalsIgnoreCase("9534") && gpfGrade!=AISGradeCode && gpfGrade!=GradeCode4  && CPFDate.before(doj)) 
	           								{
	           									value=value+deduc9999;
	           								}
	           								else if(edpCode.equalsIgnoreCase("9620") && gpfGrade==AISGradeCode) 
	           								{
	           									value=value+deduc9999;
	           								}
	           								else if( edpCode.equalsIgnoreCase("9531") && gpfGrade==GradeCode4)
	           								{
	           									value=value+deduc9998;	
	           								}
	           								else if( edpCode.equalsIgnoreCase(resourceBundle.getString("DAGPF_EDPCODE")) || edpCode.equalsIgnoreCase(resourceBundle.getString("DAGPF_IV_EDPCODE")) )
	           								{
	           									value=0;	
	           								}
	           								else if(edpCode.equalsIgnoreCase("9670") && gpfGrade!=AISGradeCode && gpfGrade!=GradeCode4) 
	           								{
	           									value=value+deduc9999;
	           								}	           								
	           								else if(edpCode.equalsIgnoreCase("9510"))  
	           								{
	           									value=value+arrearBill.getIt();
	           								}           							
	           								else if(edpCode.equalsIgnoreCase("0101")&& (typeEdpId+"").equalsIgnoreCase(resourceBundle.getString("PO_EDP_ID")))  
	           								{
	           									value=value+basic0101;
	           									
		           					            if(officerFlg)
			           					        	 value+=arrearBill.getLs()+arrearBill.getLe()+arrearBill.getGpay0101();	           									
	           								}
	           								else if(edpCode.equalsIgnoreCase("0102") && (typeEdpId+"").equalsIgnoreCase(resourceBundle.getString("PE_EDP_ID")))  
	           								{
	           									value=value+basic0102;
		           					            if(!officerFlg)
			           					        	 value+=arrearBill.getLs()+arrearBill.getLe()+arrearBill.getGpay0101();	           									
	           								}

	           								String expRcpRec= "";
	           								if(edpDtlsVO.getExpRcpRec()!=null)
	           								 expRcpRec = edpDtlsVO.getExpRcpRec();
	           								else if(edpDtlsVO.getExpRcpRec()==null)
	           									expRcpRec = " ";
	           								logger.info("expRcpRec is " + expRcpRec);
	           								
	           								//need to be changed if rlt_bill_type_edp contains any edp code's value blank for EXP_RCP_REC, which is not allowance.
	           								if((expRcpRec==null || expRcpRec.equals(" ")) && !edpCode.equalsIgnoreCase("9670"))
	           									expRcpRec="EXP";
	           								else if(edpCode.equalsIgnoreCase("9670"))
	           									expRcpRec="RCP";
	           								//ended
	           								
	           								Class thisClass = this.getClass();
	           								try
	           								{
	           									//added by Ankit
	           									if(billTypeEdp.getTypeEdpId()==Long.parseLong(resourceBundle.getString("LE_EDP_ID").toString() ))
           										{
	           										if(edpCode.equals("0102") && expRcpRec.equalsIgnoreCase("EXP") && !officerFlg
	           												&& (typeEdpId.toString().trim().equalsIgnoreCase("2"))) {
	           											Field leField = thisClass.getDeclaredField("allow0109");
	           											Field poField = thisClass.getDeclaredField("allow0101");
	           											Field peField = thisClass.getDeclaredField("allow0102");
	           											
	           											logger.info("PO value " + poField.get(this).toString());
	           											logger.info("PE value " + peField.get(this).toString());
	           											logger.info("LE value " + leField.get(this).toString());
	           											
	      	           									leField.set(this,new Double(leField.get(this).toString()) + arrearBill.getLe());
	      	           									logger.info("expRcpRec" + expRcpRec + " " + typeEdpId+"For PO- PE  param abcdefghj" + edpCode + "\ninitial value is " + leField.get(this));
	           										}	
	           										
	           										else if(edpCode.equals("0101") && expRcpRec.equalsIgnoreCase("EXP") && officerFlg
	           												&& (typeEdpId.toString().trim().equalsIgnoreCase("1"))) {
	           											Field leField = thisClass.getDeclaredField("allow0109");
	           											Field poField = thisClass.getDeclaredField("allow0101");
	           											Field peField = thisClass.getDeclaredField("allow0102");
	           											
	           											logger.info("PO value " + poField.get(this).toString());
	           											logger.info("PE value " + peField.get(this).toString());
	           											logger.info("LE value " + leField.get(this).toString());
	           											
	      	           									leField.set(this,new Double(leField.get(this).toString()) + arrearBill.getLe());
	      	           									logger.info("expRcpRec" + expRcpRec + " " + typeEdpId+"For PO- PE  param abcdefghj" + edpCode + "\ninitial value is " + leField.get(this));
	           										}	
           							 	           									    
           										}
	           									//ended
	           									else if(edpCode.equals("0102")  && !officerFlg  && expRcpRec.equalsIgnoreCase("EXP") && !(typeEdpId+"").equalsIgnoreCase("70"))
	           								 {
	           									edpCode="0102";
	           								  Field edpField = thisClass.getDeclaredField("allow" + edpCode);
	           								  logger.info(typeEdpId+"For PO- PE  param jhkljkgfyftyo" + edpCode + "\ninitial value is " + edpField.get(this));
	           									  edpField.set(this,new Double(edpField.get(this).toString()) + value);
	           								  }
	           								else if((edpCode.equals("0102") || edpCode.equals("0101") )  && officerFlg && (expRcpRec.equalsIgnoreCase("EXP")) && !(typeEdpId+"").equalsIgnoreCase("53"))
	           								{
	           								  edpCode="0101";
	           								  Field edpField = thisClass.getDeclaredField("allow" + edpCode);
         									  edpField.set(this,new Double(edpField.get(this).toString()) + value);
	           								  logger.info(typeEdpId+"after adding For PO- PE  param 123333" + edpCode + "\ninitial value is " + edpField.get(this));	
	           								}	           								
	           								else if(!(edpCode.equals("0101")) && !(edpCode.equals("0102")))
	           								{
	           									String fieldName = "";
	           									
	           									if(expRcpRec.equalsIgnoreCase("EXP") || expRcpRec.equals(" "))
	           										 fieldName = "allow" + edpCode;
	           									else if(expRcpRec.equalsIgnoreCase("REC"))
	           										fieldName = "rec" + edpCode;
	           									else if(expRcpRec.equalsIgnoreCase("RCP"))
	           										fieldName = "rcp" + edpCode;
	           									Field edpField = thisClass.getDeclaredField(fieldName);
	           									  edpField.set(this,new Double(edpField.get(this).toString()) + value);	
	           									  logger.info("after adding Param Values for " + edpCode + "   \ninitial value is " + edpField.get(this));
	           								}
	           								}
	           								catch(NoSuchFieldException e)
	           								{
	           									logger.info("NoSuchFieldException for edp code " + edpCode);
	           									logger.error("Exception ::"+e);
	           								}
	           							}
	           							
	           							} //wrong mapped post will go in Vacant Post List - Loop ends*/
	           						}//for 10-20 days issue by manoj
	           				 //}
	           					
	                    	}//end of isPaid Chk if loop
                    		else
                    		{
                    			logger.info("the arrear is already paid for this month");
                    			objectArgs.put("status", "13");
                    			
                    			objectArgs.put("paybillGenerated", "0");
                    			
                    		}
           					 
           				 }//end of empList loop
           				 
           			 }//end of month list loop
            	}//end of for loop for active arrears
			 
			 }//end of if to get active arrears
            else
            {
            	logger.info("********************************no arrear is active");
            	objectArgs.put("status", "12");
            	
            	objectArgs.put("paybillGenerated", "0");
            }
            	
//          Added By Mrugesh
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl=new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			CmnLookupMst cmnLookupMst=cmnLookupMstDAOImpl.read(billTypeId);
			
			BillHeadMpgDAO billHeadMpgDAO =null;// new BillHeadMpgDAOImpl(HrPayBillHeadMpg.class,serv.getSessionFactory());
			//Ended
            logger.info("the paybillGenerated++ "+paybillGenerated);
				if(paybillGenerated!=0)
				{
					
					HrPayArrearPaybill hrHeadPayArrearPaybill = new HrPayArrearPaybill();
					
					hrHeadPayArrearPaybill.setId(paybillGrpId);
					//Modified By Mrugesh
					paybillheadmpgVO.setMonth(monthGiven);
					paybillheadmpgVO.setYear(yearGiven);
					//Ended
					//added by Ankit Bhatt for Paybill And Order_Head Mapping Entry															
					long arrearBill_head_id=0; //Paybill_ID in "paybill_head_mpg" table.
					long paybill_head_pk =0;
					
					//ended by Ankit Bhatt.
			        logger.info("Counter value b4 if " + counter);
		       
					arrearBill_head_id = payBillIdCnt;						
					counter++;
				
					PaybillHeadMpg payBillHeadMpg = null;
				
				 StringTokenizer gradeTokens = new StringTokenizer(strGradeIds,",");
				 logger.info("gradeTokens.countTokens() " + gradeTokens.countTokens());	
				 long[] gradeIds = new long[gradeTokens.countTokens()];
				 int i=0;
				 
				 if(!newBillFlag)
				 {		 
				 while(gradeTokens.hasMoreElements())
				 {
					 String gradeId = gradeTokens.nextElement().toString();
						 gradeIds[i++] = Long.parseLong(gradeId);
				 }
				 }
				 else
				 {
					 gradeIds = new long[5];
					 gradeIds[0] = GradeCode1; 
					 gradeIds[1] = GradeCode2; 
					 gradeIds[2] = GradeCode3; 
					 gradeIds[3] = GradeCode4; 
					 gradeIds[4] = AISGradeCode; 
				 }
				 
					
           for(int i1=0;i1<gradeIds.length;i1++)
           {
           	synchronized(idGen)
			{
        	  paybill_head_pk=idGen.PKGenerator("paybill_head_mpg", objectArgs);
			}
        	PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory()); 
        	payBillHeadMpg = new PaybillHeadMpg();
        	SgvaBudsubhdMst sgvaBudsubhdMst = new SgvaBudsubhdMst();
        	sgvaBudsubhdMst.setBudsubhdId(longSubHeadId);
        	OrgGradeMst orgGradeMst = new OrgGradeMst();
        	orgGradeMst.setGradeId(gradeIds[i1]);

           	payBillHeadMpg.setId(paybill_head_pk);
           	payBillHeadMpg.setHrPayPaybill(paybillGrpId);
           	
           	/*payBillHeadMpg.setOrgGradeMst(orgGradeMst);
        	payBillHeadMpg.setSgvaBudsubhdMst(sgvaBudsubhdMst);*/
        	payBillHeadMpg.setCmnLocationMst(cmnLocationMst);                    	
        	payBillHeadMpg.setCmnDatabaseMst(cmnDatabaseMst);    					
        	payBillHeadMpg.setCreatedDate(new Date());
        	payBillHeadMpg.setOrgUserMstByCreatedBy(orgUserMst);
        	payBillHeadMpg.setOrgPostMstByCreatedByPost(orgPostMst);
        	//Added By Mrugesh
        	payBillHeadMpg.setApproveFlag(0);//Hard-coded
        	payBillHeadMpg.setMonth(monthGiven);
        	payBillHeadMpg.setYear(yearGiven);
        	//payBillHeadMpg.setBillNo(billHeadMpg);//billHeadMpg
        	payBillHeadMpg.setBillTypeId(cmnLookupMst);//paybillTypeId
        	//Ended by Mrugesh
        	paybillHeadMpgDAOImpl.create(payBillHeadMpg);
        	
           }
               PaybillBillregMpg paybillBillregMpg = new PaybillBillregMpg();
               PaybillRegMpgDAOImpl paybillRegMpgDAOImpl = new PaybillRegMpgDAOImpl(PaybillBillregMpg.class,serv.getSessionFactory());
		                       
		     	long paybill_billreg_pk=0;
		     synchronized(idGen)
				{
		     		paybill_billreg_pk=idGen.PKGenerator("PAYBILL_BILLREG_MPG", objectArgs); //PK for "PAYBILL_BILLREG_MPG"
				}
		     
		     PaybillBillregMpg paybillBillregMpgInsert = new PaybillBillregMpg();
               HrPayArrearPaybill hrPayarrearBill =   arrearBillDao.read(hrHeadPayArrearPaybill.getId());
               paybillBillregMpgInsert.setId(paybill_billreg_pk);
               //Modified By Mrugesh
               paybillBillregMpgInsert.setHrPayPaybill(hrPayarrearBill.getPaybillGrpId());
               paybillBillregMpgInsert.setBillTypeId(cmnLookupMst);
               //Endedd By Mrugesh
           	
               paybillBillregMpgInsert.setTrnBillRegister(trnBillReg);
               paybillBillregMpgInsert.setCmnLocationMst(cmnLocationMst);                    	
               paybillBillregMpgInsert.setCmnDatabaseMst(cmnDatabaseMst);    					
               paybillBillregMpgInsert.setCreatedDate(new Date());
               paybillBillregMpgInsert.setOrgUserMstByCreatedBy(orgUserMst);
               paybillBillregMpgInsert.setOrgPostMstByCreatedByPost(orgPostMst);
               if(trnBillReg!=null)
               paybillRegMpgDAOImpl.create(paybillBillregMpgInsert);
						        
		        //ended by Ankit Bhatt
				
				//end by manoj for vacant post issue
				HrPayEdpCompoMpg edpComp = null;
//				added by Ankit Bhatt for Outer Integration
				//if(trnBillRegister!=null)
				for(int edpCnt=0; edpCnt< edpList.size(); edpCnt++)
				{
					edpComp = new HrPayEdpCompoMpg();					
					edpComp = edpList.get(edpCnt);				
					String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());
					String edpCat = edpComp.getRltBillTypeEdp().getEdpCategory();
					String exp_rec_rec = edpComp.getRltBillTypeEdp().getExpRcpRec();
					logger.info("Edp code and category is " + edpCode + "--" + edpCat);
					String addedFlag = edpComp.getRltBillTypeEdp().getAddDedFlag();
					String typeEdpId = edpComp.getRltBillTypeEdp().getTypeEdpId()+"";
					
					if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("EXP"))
					{
					  proxyReq.removeParameter("txtAmt" + edpCode);
					  proxyReq.setParameter("hdPayEdpId", typeEdpId);
					  proxyReq.setParameter("hdPayAddDed", addedFlag);
					  proxyReq.setParameter("hdPayEdpCate", edpCat);
					  proxyReq.setParameter("hdPayEdpCode", edpCode);
					  logger.info("Inside the EXP loop of GeneratePaybill service");
					}
					else if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("RCP"))
					{				
						proxyReq.removeParameter("txtAmt" + edpCode);
					  proxyReq.setParameter("hdRcptEdpId", typeEdpId);
					  proxyReq.setParameter("hdRcptEdpCate", edpCat);
					  proxyReq.setParameter("hdRcptEdpCode", edpCode);
					  logger.info("Inside the RCP loop of GeneratePaybill service");
					}
					else if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("REC"))
					{		
					  proxyReq.removeParameter("txtRecAmt" + edpCode);
					  proxyReq.setParameter("hdRecEdpId", typeEdpId);
					  proxyReq.setParameter("hdRecAddDed", addedFlag);
					  proxyReq.setParameter("hdRecEdpCate", edpCat);
					  proxyReq.setParameter("hdRecEdpCode", edpCode);	
					}

					
					Class thisClass = this.getClass();
					
					String fieldName = "";
					if(exp_rec_rec == null)
					{
							exp_rec_rec=" ";
							logger.info("exp_rcp_rev is " + exp_rec_rec);
					}
					if(exp_rec_rec.equalsIgnoreCase("EXP"))
						 fieldName = "allow" + edpCode;
					else if(exp_rec_rec.equalsIgnoreCase("REC"))
						fieldName = "rec" + edpCode;
					else if(exp_rec_rec.equalsIgnoreCase("RCP"))
						fieldName = "rcp" + edpCode;
					else if(exp_rec_rec.equals(" ") && !edpCode.equalsIgnoreCase("9670"))
						fieldName = "allow" + edpCode;
					else if(exp_rec_rec.equals(" ") && edpCode.equalsIgnoreCase("9670"))
					{
						logger.info("inside last loop");
						fieldName="rcp"+edpCode;
					}
					if(exp_rec_rec.equalsIgnoreCase("EXP") && edpCode.equals("0101 ") && typeEdpId.equals(resourceBundle.getString("GPAY_0101_ID").toString()))
					{
						fieldName ="gpay"+edpCode;
						fieldName = fieldName.trim();
					}
					logger.info("Filed name is " + fieldName);
					Field expField = thisClass.getDeclaredField(fieldName);
					   
					if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("EXP"))
					{					  					   
					   txtExpenditure += expField.getDouble(this);
					   String paramName = "txtAmt" + edpCode;
						proxyReq.setParameter(paramName,String.valueOf(expField.getDouble(this)));
					   logger.info("Inside the Toal EXP loop of GeneratePaybill service WITH VALUE " + expField.getDouble(this));
					}
					else if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("RCP"))
					{																	 						
						txtReceipt += expField.getDouble(this);
						String paramName = "txtAmt" + edpCode;
						proxyReq.setParameter(paramName,String.valueOf(expField.getDouble(this)));
					    logger.info("Inside the Total RCP loop of GeneratePaybill service WITH VALUE " + expField.getDouble(this));
					    if(edpCat.equals("A"))
					    {
					    	deducA+= expField.getDouble(this);
					    }
					    else if(edpCat.equals("B"))
					    {
					    	deducB+= expField.getDouble(this);
					    }
					}
					else if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("REC"))
					{																  						
						txtRecovery += expField.getDouble(this);
						String paramName = "txtRecAmt" + edpCode;
						proxyReq.setParameter(paramName,String.valueOf(expField.getDouble(this)));
					}
				}
				//ended by Ankit Bhatt
				
				
				        					
				//added by Ankit Bhatt for Outer Integration
				
				logger.info("Total Expenditure is " + String.valueOf(txtExpenditure));
				logger.info("Total Recovery is " + txtRecovery);
				logger.info("Total DeducA is " + deducA);
				logger.info("Total DeducB is " + deducB);
				
			 TrnBillRegister lObjBillReg = (TrnBillRegister) objectArgs.get("BillRegVO");
				//for Total Expenditure
				proxyReq.removeParameter("txtExpenditure");
				proxyReq.setParameter("txtExpenditure",String.valueOf(txtExpenditure));
				//for Total Recovery
				proxyReq.removeParameter("txtRecovery");
				proxyReq.setParameter("txtRecovery",String.valueOf(txtRecovery));
				
				//for Total of DeductionA
				proxyReq.removeParameter("DeductionA");
				proxyReq.setParameter("DeductionA",String.valueOf(deducA));
				
				//for Total of DeductionA
				proxyReq.removeParameter("DeductionB");
				proxyReq.setParameter("DeductionB",String.valueOf(deducB));
				
				lObjBillReg.setBillGrossAmount(new BigDecimal(txtExpenditure - txtRecovery));
			     lObjBillReg.setBillNetAmount(new BigDecimal(txtExpenditure - txtRecovery - deducA - deducB));
			     objectArgs.put("BillRegVO", lObjBillReg);
				
				
				//ended by Ankit Bhatt
				
				}
				
				
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				
//				added by Ankit Bhatt.
				logger.info("GenerateBillService start time " + System.currentTimeMillis());

				objectArgs.put("demandNo", demandNo);
				objectArgs.put("mjrHead", mjrHead);
				objectArgs.put("subMjrHead", subMjrHead);
				objectArgs.put("mnrHead", mnrHead);
				objectArgs.put("subHead", subHead);
				objectArgs.put("month", monthGiven);
				objectArgs.put("year",yearGiven);
				objectArgs.put("requestObj", proxyReq);
				objectArgs.put("billType", "arrear");
				//ended by Ankit Bhatt.
				
			
				
			 
			 
	            resultObject.setResultValue(objectArgs);
	            
	                        
	            resultObject.setViewName("payroll");
	            //added by Ankit Bhatt
            
            //ended by Ankit Bhatt.
		}
		catch(Exception e)
		{
			logger.info("Exception in generate bill service-----"+e);
			resultObject.setResultCode(ErrorConstants.ERROR);
			objectArgs.put("msg", "Problem while inserting into database");
			//by manoj for exception handling in outer
			objectArgs.put("paybillGenerated", "0");
			//end by manoj for exception handling in outer
            resultObject.setResultValue(objectArgs);
            resultObject.setThrowable(e);
            e.printStackTrace();
            resultObject.setViewName("errorInsert");
		}
	
		return resultObject;
	}
	
	public List getMonthList(long currentMonth,Date payOutStartDate,long frequencyMonths,Date arrearEffectiveStartDate)
	{
		List monthList = new ArrayList();
		
		GenerateBillService billService = new GenerateBillService();
		
		int payoutStartMonth = billService.monthofDate(payOutStartDate);
		int effectiveStartMonth = billService.monthofDate(arrearEffectiveStartDate);
		int effectiveStartYear = billService.yearofDate(arrearEffectiveStartDate);
		
		long installmentPaid = currentMonth - payoutStartMonth;
		long noOfMonthAlreadyPaid = installmentPaid*frequencyMonths;
		logger.info("the noOfMonthAlreadyPaid is "+noOfMonthAlreadyPaid);
		long monthToBePaid = effectiveStartMonth+noOfMonthAlreadyPaid;
		logger.info("the monthTobePaid is "+monthToBePaid);
		for(int i=effectiveStartMonth;i<=(monthToBePaid+frequencyMonths-1);i++)
		{
			if(effectiveStartMonth>=13)
			{
				effectiveStartMonth=2;
				effectiveStartYear++;
			}
			else
			{
				effectiveStartMonth++;
			}
			logger.info("the month year to be added in list is "+(effectiveStartMonth-1)+"-"+effectiveStartYear);
			
			monthList.add((effectiveStartMonth-1)+"-"+effectiveStartYear);
		}
		
		
		return monthList;
	}
	
}
	