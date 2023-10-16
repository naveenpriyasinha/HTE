package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;
import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.allowance.service.IncomeTaxRules;
import com.tcs.sgv.common.dao.CmnCityMstDAOImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpExemptDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpInvestmentDtlsDAOImpl;
import com.tcs.sgv.eis.dao.HrForm16ArgsDao;
import com.tcs.sgv.eis.dao.HrForm16BankMstDao;
import com.tcs.sgv.eis.dao.HrForm16BankMstDaoImpl;
import com.tcs.sgv.eis.dao.HrForm16DtlsDao;
import com.tcs.sgv.eis.dao.HrForm16DtlsDaoImpl;
import com.tcs.sgv.eis.dao.HrForm16HeaderDao;
import com.tcs.sgv.eis.dao.HrForm16TaxDeducDao;
import com.tcs.sgv.eis.dao.HrForm16TaxDeducDaoImpl;
import com.tcs.sgv.eis.dao.LoanEmpPrinRecvDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.SectionMasterDAOImpl;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.Form16CustomVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.eis.valueobject.HrForm16Args;
import com.tcs.sgv.eis.valueobject.HrForm16BankMst;
import com.tcs.sgv.eis.valueobject.HrForm16CustomVO;
import com.tcs.sgv.eis.valueobject.HrForm16Dtls;
import com.tcs.sgv.eis.valueobject.HrForm16Header;
import com.tcs.sgv.eis.valueobject.HrForm16TaxDeducDtls;
import com.tcs.sgv.eis.valueobject.HrInvestEmpDtls;
import com.tcs.sgv.eis.valueobject.HrItExemptEmpDtls;
import com.tcs.sgv.eis.valueobject.HrItSectionMst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverHst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverHst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDao;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.dao.UserPostDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;


public class Form16ServiceImpl extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());

	public ResultObject getForm16para(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Coming into the Service method getForm16para of Form16ServiceImpl");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Calendar c = Calendar.getInstance();
			c.set(Calendar.MONTH, 9);
			c.set(Calendar.YEAR, 2007);	    
			logger.info("Maximum days in the" + c.getActualMaximum(Calendar.DATE));
			//Added by Mrugesh
			//Map loginMap = (Map) objectArgs.get("baseLoginMap");	
			//long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long languageId=1;
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(languageId);
			//Ended	

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			List<CmnLocationMst> newCmnList = new ArrayList();
			PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List empList =null; //payBillDAO.getAllEmpData();
			HrEisEmpMst hrEisEmpMst=null; 

			CmnLocationMstDaoImpl cmnLocDao = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());

			List cmnLocVoList = cmnLocDao.getLocationList(langId);

			if(cmnLocVoList!=null && cmnLocVoList.size()!=0)
			{
				logger.info("the loc list size is " + cmnLocVoList.size()); 

				//Collections.sort(cmnLocVoList);
				newCmnList =cmnLocVoList;

				/*for(Iterator it1=newCmnList.iterator();it1.hasNext();)
				 {
					 CmnLocationMst cmnLocVo = (CmnLocationMst)it1.next();	            
		          logger.info("To Avoid LazyInitialization in getPayslipParameterPage" + cmnLocVo.getLocName());                    
				 }*/
			}


			if(empList!=null && empList.size()!=0)
			{
				for(Iterator it=empList.iterator();it.hasNext();)
				{
					hrEisEmpMst = (HrEisEmpMst)it.next();	            
					logger.info("To Avoid LazyInitialization in getPayslipParameterPage" + hrEisEmpMst.getOrgEmpMst());                    
				}
			}

			/*EmpInfoDAOImpl empInfoDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());		
			List empList = empInfoDAO.getEmpNamesFromOtherDtls();*/

			//Object [] column = 
			
			OrgGradeMstDao gradeMstDao = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());
			List<OrgGradeMst> orgGradeList = new ArrayList<OrgGradeMst>();
			
			
			orgGradeList.add(gradeMstDao.getGradeVOByGradeCodeAndLangId("Cls1", langId));
			orgGradeList.add(gradeMstDao.getGradeVOByGradeCodeAndLangId("Cls2", langId));
			orgGradeList.add(gradeMstDao.getGradeVOByGradeCodeAndLangId("Cls3", langId));
			orgGradeList.add(gradeMstDao.getGradeVOByGradeCodeAndLangId("Cls4", langId));
			orgGradeList.add(gradeMstDao.getGradeVOByGradeCodeAndLangId("AIS", langId));
			
			
			
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			//List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
			List financialYearList = lookupDAO.getAllChildrenByLookUpNameAndLang("FinancialYear", langId);

			//objectArgs.put("monthList", monthList);
			logger.info("the value of financialYearList size is "+financialYearList.size());
			objectArgs.put("financialYearList", financialYearList);
			objectArgs.put("empList", empList);
			objectArgs.put("cmnLocVoList", newCmnList);
			objectArgs.put("orgGradeList", orgGradeList);
			//logger.info("Emp List is " + empList.size());
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("form16para");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}

	@SuppressWarnings("unchecked")
	public ResultObject getForm16Data(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Coming into the Service method getForm16Data of Form16ServiceImpl");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");


			//Added by Mrugesh
			long languageId=1;
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMstEng=cmnLanguageMstDaoImpl.read(languageId);
			//Ended	

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);

			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			/*
			 */

			String txtEmp=objectArgs.get("EmpId").toString();


			long headerId = Long.parseLong(objectArgs.get("headerId").toString()); 
			// long EmpId=Long.parseLong(objectArgs.get("EmpId").toString());
			long year=Long.parseLong(objectArgs.get("year").toString());
			long office_add=Long.parseLong(objectArgs.get("office_add").toString());
			long deptId=Long.parseLong(objectArgs.get("deptId").toString());			
			String strTanNum=objectArgs.get("strTanNum").toString();
			String strITO=objectArgs.get("strITO").toString();
			String strQuarter1=objectArgs.get("strQuarter1").toString();
			String strQuarter2=objectArgs.get("strQuarter2").toString();
			String strQuarter3=objectArgs.get("strQuarter3").toString();
			String strQuarter4=objectArgs.get("strQuarter4").toString();
			String isInserted=objectArgs.get("isInserted").toString();
			String isUpdate=objectArgs.get("isUpdate").toString();



			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(deptId);

			Date date = new Date();

			HrForm16Header hrForm16Header = new HrForm16Header();
			GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(HrForm16Header.class);
			genDao.setSessionFactory(serv.getSessionFactory());

			String[] columnsNew={"year","deptId"};
			Object[] valuesNew={year,cmnLocationMst};

			List columnValues=genDao.getListByColumnAndValue(columnsNew,valuesNew );



			logger.info("isInserted is "+isInserted+" and isUpdate is "+isUpdate);

			if(isInserted.equalsIgnoreCase("n") && columnValues.size()==0)
			{
				//code for inserting the header information in the table

				IdGenerator idGen = new IdGenerator();
				headerId =idGen.PKGeneratorWODBLOC("hr_form16_header", objectArgs);

				hrForm16Header.setCreatedDate(date);
				hrForm16Header.setDdoId(office_add);
				hrForm16Header.setDeptId(cmnLocationMst);
				hrForm16Header.setId(headerId);
				hrForm16Header.setIto(strITO);
				hrForm16Header.setOrgPostMstByCreatedByPost(orgPostMst);
				hrForm16Header.setOrgUserMstByCreatedBy(orgUserMst);
				hrForm16Header.setQuarter1(strQuarter1);
				hrForm16Header.setQuarter2(strQuarter2);
				hrForm16Header.setQuarter3(strQuarter3);
				hrForm16Header.setQuarter4(strQuarter4);
				hrForm16Header.setTan(strTanNum);
				hrForm16Header.setYear(year);
				logger.info("going to insert header information");
				genDao.create(hrForm16Header);
			}
			if(isUpdate.equalsIgnoreCase("y"))
			{
				//code for updating the header information in the table
				hrForm16Header=(HrForm16Header)genDao.read(headerId);
				hrForm16Header.setDdoId(office_add);
				hrForm16Header.setIto(strITO);
				hrForm16Header.setQuarter1(strQuarter1);
				hrForm16Header.setQuarter2(strQuarter2);
				hrForm16Header.setQuarter3(strQuarter3);
				hrForm16Header.setQuarter4(strQuarter4);
				hrForm16Header.setTan(strTanNum);
				logger.info("going to update header information");
				genDao.update(hrForm16Header);
			}


//			HrEisEmpMst hrEisEmpMst=new HrEisEmpMst();
//			HrEisEmpMst hrEisDdo=new HrEisEmpMst();

//			EmpInfoDAOImpl hrEisEmpDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
//			hrEisEmpMst = hrEisEmpDAO.read(EmpId);

//			// DDOInfoDAOImpl ddoDao = new DDOInfoDAOImpl(serv.getSessionFactory());

//			//OrgDdoMst ddoMst = new OrgDdoMst();

//			List ddoMstList = new ArrayList();
//			ddoMstList = hrEisEmpDAO.getDDOInfoByDdoId(office_add, langId);   
//			long ddoPostId=0;
//			if(ddoMstList!=null && ddoMstList.size()>0)
//			{
//			ddoPostId = (Long)ddoMstList.get(0);
//			}

//			OrgPostMstDaoImpl orgPostDao = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());

//			OrgPostMst postMst = orgPostDao.read(ddoPostId);

//			long user_id = hrEisEmpMst.getOrgEmpMst().getOrgUserMst().getUserId();

//			GenericDaoHibernateImpl genPanDtlsDao = new GenericDaoHibernateImpl(HrEisProofDtl.class);
//			genPanDtlsDao.setSessionFactory(serv.getSessionFactory());

//			List panDtlsList = genPanDtlsDao.getListByColumnAndValue("orgPostMstByUserId.userId", user_id);

//			String panNo  = "";

//			if(panDtlsList!=null && panDtlsList.size()>0)
//			{
//			HrEisProofDtl panDtlsVo = (HrEisProofDtl)panDtlsList.get(0);

//			panNo = panDtlsVo.getProofNum();

//			}

//			objectArgs.put("panNo",panNo);


//			String gender = String.valueOf(hrEisEmpMst.getEmpGender());

			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.MONTH, 3);
			c.set(Calendar.YEAR, (int)year);	    
			Date startDate=c.getTime();

			c.set(Calendar.DAY_OF_MONTH, 31);
			c.set(Calendar.MONTH, 2);
			c.set(Calendar.YEAR, (int)(year+1));	    
			Date endDate=c.getTime();

			String assessment_year = (year+1)+" - "+(year+2);

//			/*String strStartDate = "1-apr-"+year;
//			String strEndDate = "31-mar-"+(year+1);*/


			HrForm16ArgsDao hrForm16ArgsDao = new HrForm16ArgsDao(HrForm16Args.class,serv.getSessionFactory());
			List<HrForm16Args> hrForm16ArgsList = hrForm16ArgsDao.getForm16ArgsData();

//			PayBillDAOImpl payBillDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());       
//			List<HrPayPaybill> payBillList = payBillDao.getPayslipDataForForm16((int)year,EmpId);

//			if(payBillList!=null)
//			{
//			logger.info("the size of paySlipList list is "+payBillList.size());

//			long salary =0,less2=0,bal3=0,deduc4=0,bal6=0,add7=0,bal8=0,taxPayable=0;
//			long ta=0,ea=0,pt=0,wa=0,hbaInt=0,tds=0,gpf=0,pli=0,govtIns=0,incomeTax=0,surcharge=0,eduCess=0;
//			long relief=0,mediclaim=0,ppf=0,licOther=0,ctdUlip=0,NSC=0,NSCInt=0,ELSF=0,JeevanSuraksha=0,HBAPrin=0;
//			long EduExp=0,Others=0,donation=0,eduLoan=0,mediTreatment=0,permanentDisablity=0,others9c=0,challan=0;


//			HrForm16CustomVO hrForm16CustomVO = new HrForm16CustomVO();
//			List payBillNewList = new ArrayList();
//			PaybillHeadMpg paybillHeadMpg = new PaybillHeadMpg();
//			PaybillHeadMpgDAOImpl headMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
//			List lstPaybillHeadMpg = new ArrayList();
//			long paybillGrpId=0;
//			for(int i=0;i<payBillList.size();i++)
//			{
//			HrPayPaybill payBill = payBillList.get(i);
//			HrPayPaybill payBillNew = new HrPayPaybill(); 

//			SimpleDateFormat sdf = new SimpleDateFormat("dd"); 
//			String day = sdf.format(payBill.getCreatedDate());

//			Calendar c1 = Calendar.getInstance();
//			paybillGrpId = payBill.getPaybillGrpId();
//			lstPaybillHeadMpg = headMpgDAOImpl.getListByColumnAndValue("hrPayPaybill", paybillGrpId);

//			int monthValue=0;
//			int yearValue=1900;
//			if(lstPaybillHeadMpg!=null && !lstPaybillHeadMpg.isEmpty()){
//			paybillHeadMpg = (PaybillHeadMpg)lstPaybillHeadMpg.get(0);
//			monthValue = (int)paybillHeadMpg.getMonth();
//			yearValue = (int)paybillHeadMpg.getYear();
//			}
//			logger.info("the day is "+day);
//			//int monthValue = (int)payBill.getMonth();
//			logger.info("the month is "+monthValue);
//			logger.info("the year is "+yearValue);


//			c1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
//			c1.set(Calendar.MONTH, (monthValue-1));
//			c1.set(Calendar.YEAR, yearValue);	    
//			Date billDate=c1.getTime();

//			logger.info("the date is "+billDate);

//			payBillNew.setCreatedDate(billDate);
//			payBillNew.setIt(payBill.getIt());
//			payBillNewList.add(payBillNew);

//			logger.info("the value is"+payBill.getGrossAmt());

//			salary+=payBill.getGrossAmt();
//			ta+=payBill.getAllow0113();
//			pt+=payBill.getDeduc9570();
//			wa+=payBill.getAllow1301();
//			tds+=payBill.getIt();
//			gpf+=payBill.getDeduc9670()+payBill.getDeduc9531();
//			pli+=payBill.getDeduc9530();
//			govtIns+=payBill.getDeduc9580()+payBill.getDeduc9581()+payBill.getDeduc9582()+payBill.getDeduc9583()+payBill.getDeduc9584();
//			//hbaInt=((Double)payBill.getLoan9591()).longValue();

//			}
			String strStartDate = "1-apr-"+year;
			String strEndDate = "31-mar-"+(year+1);
//			ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
//			LoanEmpPrinRecvDAOImpl empLoanPrinDao = new LoanEmpPrinRecvDAOImpl(HrLoanEmpPrinRecoverDtls.class,serv.getSessionFactory());
//			long loanId=Long.parseLong(constantsBundle.getString("hba"));

//			List empLoanPrincList = empLoanPrinDao.getLoandetailForForm16(loanId,EmpId,strStartDate,strEndDate);
//			long hbaPrinc=0;

//			for(int i=0;i<(empLoanPrincList.size()-1);i++)
//			{
//			HrLoanEmpPrinRecoverHst hrEmpLoanPrinHst =  (HrLoanEmpPrinRecoverHst)empLoanPrincList.get(i);

//			hbaPrinc+=hrEmpLoanPrinHst.getHrLoanEmpDtls().getLoanPrinEmiAmt();

//			}
//			logger.info("the total principal amount is "+hbaPrinc);

//			List empLoanIntList = empLoanPrinDao.getLoanIntdetailForForm16(loanId,EmpId,strStartDate,strEndDate);

//			for(int i=0;i<(empLoanIntList.size()-1);i++)
//			{
//			HrLoanEmpIntRecoverHst hrEmpLoanIntHst =  (HrLoanEmpIntRecoverHst)empLoanIntList.get(i);

//			hbaInt+=hrEmpLoanIntHst.getHrLoanEmpDtls().getLoanPrinEmiAmt();

//			}
//			logger.info("the total int amount is "+hbaInt);
//			//List empLoanPrinRcvr = empLoanPrinDao.

//			less2=ta;
//			bal3=salary-less2;
//			deduc4=ea+pt+wa+hbaInt;
//			bal6=bal3-deduc4;
//			//add7=0;
//			bal8 = bal6+add7;

//			SectionMasterDAOImpl sectionDao = new SectionMasterDAOImpl(HrItSectionMst.class,serv.getSessionFactory());

//			//List sectionMstList = sectionDao.getSectionDataByActivateFlag("300029");

//			/*if(sectionMstList!=null && sectionMstList.size()>0)
//			{
//			for(int i=0;i<sectionMstList.size();i++)
//			{
//			HrItSectionMst hrSectionMst = (HrItSectionMst)sectionMstList.get(0);

//			if(hrSectionMst.getActivateFlag()==300029)
//			{

//			}
//			}
//			}*/




//			hrForm16CustomVO.setGrossSalary(salary);
//			hrForm16CustomVO.setLess2(less2);
//			hrForm16CustomVO.setBalance3(bal3);
//			hrForm16CustomVO.setDeduc4a(ea);
//			hrForm16CustomVO.setDeduc4b(pt);
//			hrForm16CustomVO.setDeduc4c(wa);
//			hrForm16CustomVO.setDeduc4d(hbaInt);
//			hrForm16CustomVO.setTotal5(deduc4);
//			hrForm16CustomVO.setBal6(bal6);
//			hrForm16CustomVO.setAdditionalIncome7(add7);
//			hrForm16CustomVO.setGross8(bal8);


//			long mediClaimCode=Long.parseLong(constantsBundle.getString("mediclaim"));
//			long mediHandiCode=Long.parseLong(constantsBundle.getString("mediHandi"));
//			long donationCode=Long.parseLong(constantsBundle.getString("donation"));
//			long perDisablityCode=Long.parseLong(constantsBundle.getString("disablity"));
//			long eduLoansCode=Long.parseLong(constantsBundle.getString("eduLoans"));

//			long ppfCode=Long.parseLong(constantsBundle.getString("ppf"));
//			long licCode=Long.parseLong(constantsBundle.getString("lic"));
//			long ctdCode=Long.parseLong(constantsBundle.getString("ctd"));
//			long nscCode=Long.parseLong(constantsBundle.getString("nsc"));
//			long nscIntCode=Long.parseLong(constantsBundle.getString("nscInt"));
//			long elSavFundCode=Long.parseLong(constantsBundle.getString("elSavFund"));
//			long pensionCode=Long.parseLong(constantsBundle.getString("pension"));
//			long eduExpCode=Long.parseLong(constantsBundle.getString("eduExp"));
//			long mutualFundsCode=Long.parseLong(constantsBundle.getString("mutualFunds"));

//			EmpExemptDtlsDAOImpl empExemDao = new EmpExemptDtlsDAOImpl(HrItExemptEmpDtls.class,serv.getSessionFactory());
//			EmpInvestmentDtlsDAOImpl empInvestDao = new EmpInvestmentDtlsDAOImpl(HrInvestEmpDtls.class,serv.getSessionFactory());
//			List empExemptionList = new ArrayList();
//			List empInvestmentList = new ArrayList();

//			//for ppf
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, ppfCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			ppf = (Long)empInvestmentList.get(0);
//			}
//			}
//			//for lic
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, licCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			licOther = (Long)empInvestmentList.get(0);
//			}
//			}
//			//for ctd
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, ctdCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			ctdUlip = (Long)empInvestmentList.get(0);
//			}
//			}
//			//for nsc
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, nscCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			NSC = (Long)empInvestmentList.get(0);
//			}
//			}

//			//for nscInt
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, nscIntCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			NSCInt = (Long)empInvestmentList.get(0);
//			}
//			}
//			//for elSavFund
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, elSavFundCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			ELSF = (Long)empInvestmentList.get(0);
//			}
//			}
//			//for pension
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, pensionCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			JeevanSuraksha = (Long)empInvestmentList.get(0);
//			}
//			}
//			//for eduExp
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, eduExpCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			EduExp = (Long)empInvestmentList.get(0);
//			}
//			}
//			//for mutualFunds
//			empInvestmentList = empInvestDao.getEmpInvestmentData(EmpId,langId, mutualFundsCode,strStartDate,strEndDate);
//			if(empInvestmentList!=null && empInvestmentList.size()>0)
//			{
//			if(empInvestmentList.get(0)!=null)
//			{
//			Others = (Long)empInvestmentList.get(0);
//			}
//			}


//			//for mediclaim
//			empExemptionList = empExemDao.getEmpExemptionData(EmpId,langId, mediClaimCode);
//			if(empExemptionList!=null && empExemptionList.size()>0)
//			{
//			if(empExemptionList.get(0)!=null)
//			{
//			mediclaim = (Long)empExemptionList.get(0);
//			}
//			}
//			//for medical treatment for handicapped dependants
//			empInvestmentList = new ArrayList();

//			empExemptionList = empExemDao.getEmpExemptionData(EmpId,langId, mediHandiCode);
//			if(empExemptionList!=null && empExemptionList.size()>0)
//			{
//			if(empExemptionList.get(0)!=null)
//			{
//			mediTreatment = (Long)empExemptionList.get(0);
//			}
//			}

//			//for donation
//			empInvestmentList = new ArrayList();

//			empExemptionList = empExemDao.getEmpExemptionData(EmpId,langId, donationCode);
//			if(empExemptionList!=null && empExemptionList.size()>0)
//			{
//			if(empExemptionList.get(0)!=null)
//			{
//			donation = (Long)empExemptionList.get(0);
//			}
//			}
//			//for permanent Disablity
//			empInvestmentList = new ArrayList();

//			empExemptionList = empExemDao.getEmpExemptionData(EmpId,langId, perDisablityCode);
//			if(empExemptionList!=null && empExemptionList.size()>0)
//			{
//			if(empExemptionList.get(0)!=null)
//			{
//			permanentDisablity = (Long)empExemptionList.get(0);
//			}
//			}
//			//for education Loans
//			empInvestmentList = new ArrayList();

//			empExemptionList = empExemDao.getEmpExemptionData(EmpId,langId, eduLoansCode);
//			if(empExemptionList!=null && empExemptionList.size()>0)
//			{
//			if(empExemptionList.get(0)!=null)
//			{
//			eduLoan = (Long)empExemptionList.get(0);
//			}
//			}


//			hrForm16CustomVO.setDeduc9A(mediclaim);
//			hrForm16CustomVO.setDeduc9Bi(gpf);
//			hrForm16CustomVO.setDeduc9Bii(govtIns);
//			hrForm16CustomVO.setDeduc9Biii(ppf);
//			hrForm16CustomVO.setDeduc9Biv(licOther);
//			hrForm16CustomVO.setDeduc9Bv(pli);
//			hrForm16CustomVO.setDeduc9Bvi(ctdUlip);
//			hrForm16CustomVO.setDeduc9Bvii(NSC);
//			hrForm16CustomVO.setDeduc9Bviii(NSCInt);
//			hrForm16CustomVO.setDeduc9Bix(ELSF);
//			hrForm16CustomVO.setDeduc9Bx(JeevanSuraksha);
//			hrForm16CustomVO.setDeduc9Bxi(hbaPrinc);
//			hrForm16CustomVO.setDeduc9Bxii(EduExp);
//			hrForm16CustomVO.setDeduc9Bxiii(Others);

//			long total9B = gpf + govtIns + ppf + licOther + pli + ctdUlip + NSC + NSCInt + ELSF + JeevanSuraksha + hbaPrinc + EduExp + Others;
//			hrForm16CustomVO.setTotal9B(total9B);


//			hrForm16CustomVO.setDeduc9Ca(donation);
//			hrForm16CustomVO.setDeduc9Cb(eduLoan);
//			hrForm16CustomVO.setDeduc9Cc(mediTreatment);
//			hrForm16CustomVO.setDeduc9Cd(permanentDisablity);
//			hrForm16CustomVO.setDeduc9Ce(others9c);

//			long total9C = donation + eduLoan + mediTreatment + permanentDisablity + others9c;

//			hrForm16CustomVO.setTotal9C(total9C);

//			long total10 = mediclaim + total9B + total9C;
//			hrForm16CustomVO.setTotal10(total10);

//			long total11 = bal8 - total10;
//			int level=1;
//			total11=Math.abs(total11);
//			hrForm16CustomVO.setTotal11(total11);

//			IncomeTaxRules itRule = new IncomeTaxRules();


//			incomeTax=((Double)itRule.calculateIncomeTax(total11, level, gender)).longValue();
//			surcharge=((Double)itRule.calculateSurchargeAmt(total11)).longValue();
//			eduCess=((Double)itRule.calculateEduCess(incomeTax+surcharge)).longValue();



//			hrForm16CustomVO.setTotalIncomeTax(incomeTax);
//			hrForm16CustomVO.setTotalSurcharge(surcharge);
//			hrForm16CustomVO.setTotalEduCess(eduCess);

//			long totalTaxPayable = incomeTax + surcharge + eduCess;
//			hrForm16CustomVO.setTotalTaxPayable(totalTaxPayable);

//			//relief=;

//			hrForm16CustomVO.setRelief(relief);

//			taxPayable = totalTaxPayable - relief;
//			hrForm16CustomVO.setTaxPayable(taxPayable);


//			hrForm16CustomVO.setLess18a(tds);
//			hrForm16CustomVO.setLess18b(challan);

//			hrForm16CustomVO.setTaxPayRefund(taxPayable -  tds - challan);

//			String taxInWords="Nil";
//			if(hrForm16CustomVO.getTaxPayable()>0)
//			taxInWords=ConvertNumbersToWord.convert(hrForm16CustomVO.getTaxPayable());

//			hrForm16CustomVO.setStrTaxPayRefund(taxInWords);

//			/*



//			bal9b=gpf+govtIns+pli;

//			bal10=bal9a+bal9b+bal9c;
//			bal11=bal8-bal10;
//			*/

//			objectArgs.put("hrForm16CustomVO",hrForm16CustomVO);

//			objectArgs.put("payBillList",payBillNewList);	

//			}
			objectArgs.put("hrForm16ArgsList",hrForm16ArgsList);

			objectArgs.put("isInserted",isInserted);
			objectArgs.put("year",assessment_year);
			objectArgs.put("startDate",startDate);
			objectArgs.put("endDate",endDate);
			objectArgs.put("office_add",office_add);

//			/*objectArgs.put("strTanNum",strTanNum);
//			objectArgs.put("strITO",strITO);
//			objectArgs.put("strQuarter1",strQuarter1);
//			objectArgs.put("strQuarter2",strQuarter2);
//			objectArgs.put("strQuarter3",strQuarter3);
//			objectArgs.put("strQuarter4",strQuarter4);*/
//			objectArgs.put("hrEisEmpMst", hrEisEmpMst);


//			// for ddo address
//			logger.info("the ddo id is "+ddoPostId);
//			/*Set orgUserPostRltSetDdo = hrEisDdo.getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();
//			Iterator itDdo = orgUserPostRltSetDdo.iterator();

//			while(itDdo.hasNext())
//			{
//			OrgUserpostRlt orgUserpostRltDdo = (OrgUserpostRlt)itDdo.next();
//			logger.info("orgUserpostRltDdo.getEndDate() "+orgUserpostRltDdo.getEndDate()+" orgUserpostRltDdo.getStartDate() "+orgUserpostRltDdo.getStartDate());
//			if(orgUserpostRltDdo.getEndDate()==null || orgUserpostRltDdo.getEndDate().compareTo(orgUserpostRltDdo.getStartDate())==0)
//			{*/
//			if(postMst!=null)
//			{
//			Set orgPostDtlsSetDdo = postMst.getOrgPostDetailsRlt();

//			Iterator itDdo1 = orgPostDtlsSetDdo.iterator();

//			while(itDdo1.hasNext())
//			{
//			OrgPostDetailsRlt orgPostDetailRltDdo = (OrgPostDetailsRlt)itDdo1.next();

//			if(orgPostDetailRltDdo.getCmnLanguageMst().getLangId()==langId)
//			{
//			logger.info("the value of post is for ddo "+orgPostDetailRltDdo.getPostName());
//			logger.info("the value of location name is for ddo "+orgPostDetailRltDdo.getCmnLocationMst().getLocName());
//			logger.info("the value of location address is for ddo "+orgPostDetailRltDdo.getCmnLocationMst().getLocAddr1());
//			logger.info("the value of location address 2 is for ddo "+orgPostDetailRltDdo.getCmnLocationMst().getLocAddr2());
//			logger.info("the value of location pin is for ddo "+orgPostDetailRltDdo.getCmnLocationMst().getLocPin());
//			logger.info("the value of designation is for ddo "+orgPostDetailRltDdo.getOrgDesignationMst().getDsgnName());

//			objectArgs.put("ddoDesignation",orgPostDetailRltDdo.getOrgDesignationMst().getDsgnName());
//			objectArgs.put("ddoDepartment",orgPostDetailRltDdo.getCmnLocationMst().getLocName());
//			objectArgs.put("ddoLocAdd1",orgPostDetailRltDdo.getCmnLocationMst().getLocAddr1());
//			objectArgs.put("ddoLocAdd2",orgPostDetailRltDdo.getCmnLocationMst().getLocAddr2());
//			objectArgs.put("ddoLocPin",orgPostDetailRltDdo.getCmnLocationMst().getLocPin());

//			CmnCityMstDAOImpl cityMstDao = new CmnCityMstDAOImpl(CmnCityMst.class,serv.getSessionFactory());
//			CmnCityMst cmnCityMstDdo = cityMstDao.read(orgPostDetailRltDdo.getCmnLocationMst().getLocCityId());

//			objectArgs.put("ddoLocCity",cmnCityMstDdo.getCityName());
//			}	
//			}
//			//}

//			//}

//			UserPostDaoImpl userPostDao = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());
//			List<OrgUserpostRlt> orgUserpostRltList=userPostDao.getUserFromPost(postMst.getPostId());


//			Iterator itDdo11 = orgUserpostRltList.iterator();

//			OrgEmpMst orgDDOEmp = new OrgEmpMst();

//			while(itDdo11.hasNext())
//			{
//			OrgUserpostRlt userPostRlt = (OrgUserpostRlt)itDdo11.next();
//			Set orgEmpMstSet = userPostRlt.getOrgUserMst().getOrgEmpMsts();

//			Iterator empMstIt = orgEmpMstSet.iterator();

//			while(empMstIt.hasNext())
//			{
//			OrgEmpMst orgEmpDdo = (OrgEmpMst)empMstIt.next();


//			if(orgEmpDdo.getCmnLanguageMst().getLangId()==langId)
//			{
//			orgDDOEmp = orgEmpDdo;
//			}
//			}
//			}	

//			objectArgs.put("hrEisDdo", orgDDOEmp);
//			}
//			//end for ddo address

//			//for emp address
//			logger.info("the emp id is "+hrEisEmpMst.getOrgEmpMst().getEmpId());
//			Set orgUserPostRltSet = hrEisEmpMst.getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();
//			Iterator it = orgUserPostRltSet.iterator();

//			while(it.hasNext())
//			{
//			OrgUserpostRlt orgUserpostRlt = (OrgUserpostRlt)it.next();


//			if(orgUserpostRlt.getEndDate()==null  || orgUserpostRlt.getEndDate().compareTo(orgUserpostRlt.getStartDate())==0)
//			{

//			Set orgPostDtlsSet = orgUserpostRlt.getOrgPostMstByPostId().getOrgPostDetailsRlt();

//			Iterator it1 = orgPostDtlsSet.iterator();

//			while(it1.hasNext())
//			{
//			OrgPostDetailsRlt orgPostDetailRlt = (OrgPostDetailsRlt)it1.next();

//			if(orgPostDetailRlt.getCmnLanguageMst().getLangId()==langId)
//			{
//			logger.info("the value of post is for test "+orgPostDetailRlt.getPostName());
//			logger.info("the value of location name is for test "+orgPostDetailRlt.getCmnLocationMst().getLocName());
//			logger.info("the value of location address is for test "+orgPostDetailRlt.getCmnLocationMst().getLocAddr1());
//			logger.info("the value of location address 2 is for test "+orgPostDetailRlt.getCmnLocationMst().getLocAddr2());
//			logger.info("the value of location pin is for test "+orgPostDetailRlt.getCmnLocationMst().getLocPin());
//			logger.info("the value of designation is for test "+orgPostDetailRlt.getOrgDesignationMst().getDsgnName());

//			objectArgs.put("designation",orgPostDetailRlt.getOrgDesignationMst().getDsgnName());
//			objectArgs.put("department",orgPostDetailRlt.getCmnLocationMst().getLocName());
//			objectArgs.put("locAdd1",orgPostDetailRlt.getCmnLocationMst().getLocAddr1());
//			objectArgs.put("locAdd2",orgPostDetailRlt.getCmnLocationMst().getLocAddr2());
//			objectArgs.put("locPin",orgPostDetailRlt.getCmnLocationMst().getLocPin());

//			CmnCityMstDAOImpl cityMstDao = new CmnCityMstDAOImpl(CmnCityMst.class,serv.getSessionFactory());
//			CmnCityMst cmnCityMst = cityMstDao.read(orgPostDetailRlt.getCmnLocationMst().getLocCityId());

//			objectArgs.put("locCity",cmnCityMst.getCityName());
//			}	
//			}
//			}

//			}
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
			PayBillDAOImpl payBillDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());     
			List qryList=new ArrayList();
			if(locId==300022)
				qryList=payBillDao.getForm16Data(locId, txtEmp, year);// not used currently as not sufficcient data for cash2
			else if(locId==300024)
				qryList=payBillDao.getForm16DataByUser(locId, txtEmp, year);

			List form16Data=new ArrayList();

			// int level=1;
			//IncomeTaxRules itRule = new IncomeTaxRules();
			long incomeTax=0;
			//long surcharge=0;
			long eduCess=0;
			long total11=0;
			String gender="M";
			String taxInWords="Nil";
			@SuppressWarnings("unused")
			Map taxDeducDtl=new HashMap();
			int count=0;
			int countSecond=0;
			long itAtSrc=0;
			int hbaIntClmd=0;
			int hbaRepayClmd=0;
			for (Iterator it = qryList.iterator(); it.hasNext();) 
			{
				incomeTax=0;
				// surcharge=0;
				eduCess=0;
				total11=0;
				taxInWords="Nil";
				itAtSrc=0;
				taxDeducDtl=new HashMap();
				HrForm16CustomVO form16VO = new HrForm16CustomVO();
				count=0;
				countSecond=0;
				Object[] rowList = (Object[]) it.next();
				form16VO.setEmpName(rowList[1].toString());
				form16VO.setDesgnName(rowList[2]!=null?rowList[2].toString():"");
				form16VO.setPanCard(rowList[3].toString());
				form16VO.setGrossSalary(Long.parseLong(rowList[4]!=null?rowList[4].toString():"0")+Long.parseLong(rowList[37]!=null?rowList[37].toString():"0"));

				form16VO.setLess2(Long.parseLong(rowList[5]!=null?rowList[5].toString():"0"));
				form16VO.setBalance3(form16VO.getGrossSalary()-form16VO.getLess2());

				form16VO.setDeduc4c((Long.parseLong(rowList[6]!=null?rowList[6].toString():"0"))+(Long.parseLong(rowList[38]!=null?rowList[38].toString():"0")));
				form16VO.setDeduc4b(Long.parseLong(rowList[9]!=null?rowList[9].toString():"0"));
				form16VO.setDeduc4d(Long.parseLong(rowList[8]!=null?rowList[8].toString():"0"));
				hbaIntClmd=Integer.parseInt(rowList[86]!=null?rowList[86].toString():"0");
				hbaRepayClmd=Integer.parseInt(rowList[87]!=null?rowList[87].toString():"0");
				//to be added by hemant for hba interest
				if(hbaIntClmd==1)
					form16VO.setDeduc4d(Long.parseLong(rowList[88]!=null?rowList[88].toString():"0"));

				form16VO.setTotal5(form16VO.getDeduc4b()+form16VO.getDeduc4c()+form16VO.getDeduc4d());
				form16VO.setBal6(form16VO.getBalance3()-form16VO.getTotal5());
				form16VO.setDeduc4a(0);// entertainment allowance is 0
				form16VO.setAdditionalIncome7(0);// additional income is 0
				form16VO.setGross8(form16VO.getBal6()+form16VO.getAdditionalIncome7());

				form16VO.setDeduc9A(Long.parseLong(rowList[29]!=null?rowList[29].toString():"0"));
				form16VO.setDeduc9Bi(Long.parseLong(rowList[41]!=null?rowList[41].toString():"0"));
				form16VO.setDeduc9Bii(Long.parseLong(rowList[42]!=null?rowList[42].toString():"0"));
				form16VO.setDeduc9Biii(Long.parseLong(rowList[14]!=null?rowList[14].toString():"0"));
				form16VO.setDeduc9Biv(Long.parseLong(rowList[12]!=null?rowList[12].toString():"0"));
				form16VO.setDeduc9Bv(Long.parseLong(rowList[11]!=null?rowList[11].toString():"0"));
				form16VO.setDeduc9Bvi(Long.parseLong(rowList[15]!=null?rowList[15].toString():"0"));
				form16VO.setDeduc9Bvii(Long.parseLong(rowList[13]!=null?rowList[13].toString():"0"));
				form16VO.setDeduc9Bviii(Long.parseLong(rowList[16]!=null?rowList[16].toString():"0"));
				form16VO.setDeduc9Bix(Long.parseLong(rowList[21]!=null?rowList[21].toString():"0"));
				form16VO.setDeduc9Bx(Long.parseLong(rowList[17]!=null?rowList[17].toString():"0"));
				form16VO.setDeduc9Bxi(Long.parseLong(rowList[7]!=null?rowList[7].toString():"0"));

				//to be added by hemant for hba principle
				if(hbaRepayClmd==1)
					form16VO.setDeduc9Bxi(Long.parseLong(rowList[89]!=null?rowList[89].toString():"0"));

				form16VO.setDeduc9Bxii(Long.parseLong(rowList[20]!=null?rowList[20].toString():"0"));

				form16VO.setDeduc9Bxiii(Long.parseLong(rowList[23]!=null?rowList[23].toString():"0"));

				form16VO.setTotal9B(form16VO.getDeduc9Bi()+form16VO.getDeduc9Bii()+form16VO.getDeduc9Biii()+
						form16VO.getDeduc9Biv()+form16VO.getDeduc9Bv()+form16VO.getDeduc9Bvi()+form16VO.getDeduc9Bvii()
						+form16VO.getDeduc9Bviii()+form16VO.getDeduc9Bix()+form16VO.getDeduc9Bx()+form16VO.getDeduc9Bxi()
						+form16VO.getDeduc9Bxii()+form16VO.getDeduc9Bxiii());

				if(form16VO.getTotal9B()>100000)
					form16VO.setTotal9B(100000);

				form16VO.setDeduc9Ca(Long.parseLong(rowList[31]!=null?rowList[31].toString():"0"));
				form16VO.setDeduc9Cb(Long.parseLong(rowList[33]!=null?rowList[33].toString():"0"));
				form16VO.setDeduc9Cc(Long.parseLong(rowList[30]!=null?rowList[30].toString():"0"));
				form16VO.setDeduc9Cd(Long.parseLong(rowList[32]!=null?rowList[32].toString():"0"));
				form16VO.setDeduc9Ce(0); // others 9ce set as 0 may require updation

				form16VO.setTotal9C(form16VO.getDeduc9Ca()+form16VO.getDeduc9Cb()+form16VO.getDeduc9Cc()+form16VO.getDeduc9Cd()+form16VO.getDeduc9Ce());

				form16VO.setTotal10(form16VO.getDeduc9A()+form16VO.getTotal9B()+form16VO.getTotal9C());

				form16VO.setTotal11(form16VO.getGross8()-form16VO.getTotal10());
				gender=rowList[43]!=null?rowList[43].toString():"M";

				for(int i=1;i<=28;i++)
				{
					if(i%2!=0)
					{
						count++;
						taxDeducDtl.put("bank_name"+count, rowList[44+i]!=null?rowList[44+i].toString():"");
					}
					else
					{
						countSecond++;

						taxDeducDtl.put("bank_date"+countSecond, rowList[44+i]!=null?sdfDate.format(rowList[44+i]):"");
					}
				}
				for(int i=1;i<=12;i++)
				{
					taxDeducDtl.put("month_tax"+i, rowList[72+i]!=null?rowList[72+i].toString():"0");
					itAtSrc=itAtSrc+Long.parseLong(rowList[72+i]!=null?rowList[72+i].toString():"0");
				}
				taxDeducDtl.put("month_tax13", rowList[40]!=null?rowList[40].toString():"0");
				taxDeducDtl.put("month_tax14", rowList[39]!=null?rowList[39].toString():"0");

				form16VO.setTaxDeducDtl(taxDeducDtl);

				//added to display psrNo
				form16VO.setPsrSeq(rowList[85]!=null?rowList[85].toString():"");


				total11=Math.abs(form16VO.getTotal11());
				if(total11%10>=5)
					total11=total11+(10-total11%10);
				else if(total11%10<5)
					total11=total11-(total11%10);
				// form16 rules
				if(gender.equalsIgnoreCase("M"))
				{
					if(total11<=160000)
						incomeTax=0;
					else if(total11>160000 && total11<=300000)
						incomeTax=Math.round((10*(total11-160000))/100);
					else if(total11>300000 && total11<=500000)
						incomeTax=Math.round((20*(total11-300000))/100)+14000;
					else if(total11>500000)
						incomeTax=Math.round((30*(total11-500000))/100)+54000;


					eduCess=Math.round(0.03*incomeTax);
					form16VO.setTotalIncomeTax(incomeTax);
					form16VO.setTotalSurcharge(0);
					form16VO.setTotalEduCess(eduCess);

					form16VO.setTotalTaxPayable(incomeTax+eduCess);

				}
				else if(gender.equalsIgnoreCase("F"))
				{

					if(total11<=190000)
						incomeTax=0;
					else if(total11>190000 && total11<=300000)
						incomeTax=Math.round((10*(total11-190000))/100);
					else if(total11>300000 && total11<=500000)
						incomeTax=Math.round((20*(total11-300000))/100)+11000;
					else if(total11>500000)
						incomeTax=Math.round((30*(total11-500000))/100)+51000;


					eduCess=Math.round(0.03*incomeTax);
					form16VO.setTotalIncomeTax(incomeTax);
					form16VO.setTotalSurcharge(0);
					form16VO.setTotalEduCess(eduCess);


					form16VO.setTotalTaxPayable(incomeTax+eduCess);
				}

				form16VO.setRelief(Long.parseLong(rowList[44]!=null?rowList[44].toString():"0"));// relief set to zero
				form16VO.setTaxPayable(form16VO.getTotalTaxPayable()-form16VO.getRelief());
				//changed for employees from other dept 
				//form16VO.setLess18a(Long.parseLong(rowList[10]!=null?rowList[10].toString():"0"));
				form16VO.setLess18a(itAtSrc + Long.parseLong(rowList[40]!=null?rowList[40].toString():"0"));
				form16VO.setChallanNumber(rowList[90]!=null?rowList[90].toString():"");
				form16VO.setLess18b(Long.parseLong(rowList[39]!=null?rowList[39].toString():"0"));
				//form16VO.setArrearTax(Long.parseLong(rowList[40]!=null?rowList[40].toString():"0"));



				form16VO.setTaxPayable(Math.abs(form16VO.getTaxPayable()));

				form16VO.setTaxPayRefund(form16VO.getTaxPayable()-form16VO.getLess18a()-form16VO.getLess18b()-form16VO.getArrearTax());


				if(form16VO.getLess18a()+form16VO.getLess18b()+form16VO.getArrearTax()>0)
					taxInWords=ConvertNumbersToWord.convert(Math.round(form16VO.getLess18a()+form16VO.getLess18b()+form16VO.getArrearTax())) + " Only";

				form16VO.setStrTaxPayRefund(taxInWords);

				form16Data.add(form16VO);
			}


			List signatureData= payBillDao.getSignaturebyMonthandLocId(locId,4,(int)year);
			String signDataName="";
			String signDsgnName="";
			String signDeptName="";
			String cityPin="";
			String cityName="";
			String locAddr1="";
			String signDataFullName="";
			if(signatureData!=null && signatureData.size()==1)
			{
				Object[] signDataList = new Object[3];
				signDataList = (Object[])signatureData.get(0);
				signDataName=signDataList[3].toString();
				signDsgnName=signDataList[2].toString();
				signDeptName=signDataList[4].toString();
				cityPin=signDataList[5]!=null?signDataList[5].toString():"";
				cityName=signDataList[6]!=null?signDataList[6].toString():"";
				locAddr1=signDataList[7]!=null?signDataList[7].toString():"";
				signDataFullName=signDataList[8]!=null?signDataList[8].toString():"";
			}

			objectArgs.put("signDataName",signDataName);
			objectArgs.put("signDsgnName",signDsgnName);
			objectArgs.put("signDeptName",signDeptName);
			objectArgs.put("signcityPin",cityPin);
			objectArgs.put("signcityName",cityName);
			objectArgs.put("signlocAddr1",locAddr1);
			objectArgs.put("signDataFullName",signDataFullName);


			String dateNew=sdfDate.format(date);
			objectArgs.put("todayDate",dateNew);


			objectArgs.put("form16DataNew",form16Data);


			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("form16Page");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){

			logger.info("Exception Ocuures...");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}


	public ResultObject chkForm16Data(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Coming into the Service method chkForm16Data of Form16ServiceImpl");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			long languageId=1;
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(languageId);
			//Ended	

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			StringBuffer propertyList = new StringBuffer();	
			Map voToService = (Map)objectArgs.get("voToServiceMap");

			String deptId="";
			long departmentId=0;
			if(voToService.get("deptId")!=null && !voToService.get("deptId").toString().equals(""))
			{

				deptId = voToService.get("deptId").toString();

				if(!deptId.equals(""))
				{
					departmentId=Long.parseLong(deptId);
				} 
				logger.info("the dept id is "+departmentId);
			}    

			String strYear="";
			long year=0;
			if(voToService.get("year")!=null && !voToService.get("year").toString().equals(""))
			{
				strYear = voToService.get("year").toString();

				if(!strYear.equals(""))
				{
					year=Long.parseLong(strYear);
				} 	
				logger.info(" from service is the year is "+year);
			} 

			//to be read from the form 16 header table.
			HrForm16HeaderDao form16Dao = new HrForm16HeaderDao(HrForm16Header.class,serv.getSessionFactory());

			HrForm16Header hrForm16Header = form16Dao.getForm16HeaderInfo(year, departmentId);

			String TanNo="";
			String ITO = "";
			String Q1="";
			String Q2="";
			String Q3="";
			String Q4="";
			long ddoId = 0;
			long headerId=0;
			String isInserted="n";

			if(hrForm16Header!=null)
			{
				logger.info("the hrForm16Header is "+hrForm16Header);
				TanNo=hrForm16Header.getTan();
				ITO = hrForm16Header.getIto();
				Q1=hrForm16Header.getQuarter1();
				Q2=hrForm16Header.getQuarter2();
				Q3=hrForm16Header.getQuarter3();
				Q4=hrForm16Header.getQuarter4();
				ddoId= hrForm16Header.getDdoId();
				headerId= hrForm16Header.getId();
				isInserted="y";
			}
			else
			{
				logger.info(" from else part the hrForm16Header is null ");
			}
			propertyList.append("<form16-header>");
			propertyList.append("<TanNo>").append(TanNo).append("</TanNo>");
			propertyList.append("<ITO>").append(ITO).append("</ITO>");             
			propertyList.append("<Q1>").append(Q1).append("</Q1>");
			propertyList.append("<Q2>").append(Q2).append("</Q2>");
			propertyList.append("<Q3>").append(Q3).append("</Q3>");
			propertyList.append("<Q4>").append(Q4).append("</Q4>");
			propertyList.append("<DDOID>").append(ddoId).append("</DDOID>");
			propertyList.append("<headerId>").append(headerId).append("</headerId>");
			propertyList.append("<isInserted>").append(isInserted).append("</isInserted>");
			propertyList.append("</form16-header>");

			//}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			result.put("ajaxKey", stateNameIdStr);               
			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData"); 
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}
	public ResultObject displayITOtherDtl(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		LoginDetails objLoginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			HrForm16BankMstDao bankMstDao = new HrForm16BankMstDaoImpl(HrForm16BankMst.class,serv.getSessionFactory());
			List<HrForm16BankMst> list = bankMstDao.getBankData();

			logger.info("The Bank List is : " + list);
			//logger.info("The Bank List is : " + list);
			objectArgs.put("bankList", list);

			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List financialYearList = lookupDAO.getAllChildrenByLookUpNameAndLang("FinancialYear",objLoginDetails.getLangId());
			logger.info("the value of financialYearList size is "+financialYearList.size());
			objectArgs.put("financialYearList", financialYearList);

			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ITOtherDetails");

		}catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}

	public ResultObject insertForm16Details(Map objectArgs)
	{
		//logger.info("In the insert For Other Details"); 
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		LoginDetails objLoginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		try
		{
			String strChk = StringUtility.getParameter("chk", request);
			if(strChk != null && !"".equals(strChk) && "true".equals(strChk)){

				HrForm16DtlsDaoImpl daoImpl = new HrForm16DtlsDaoImpl(HrForm16Dtls.class,serv.getSessionFactory());
				EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				HrEisEmpMst setHrEisEmpMst = new HrEisEmpMst();
				OrgEmpMst orgEmpMst = new OrgEmpMst();
				EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				

				String strEmpId = StringUtility.getParameter("empId", request);
				String strYear = StringUtility.getParameter("year", request);

				long empId  = 0;
				
				if(strEmpId != null && !"".equals(strEmpId)){
					CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					CmnLookupMst lookupMst = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strYear, objLoginDetails.getLangId());

					String lookupShortName = lookupMst.getLookupShortName();
					
					orgEmpMst = empDAOImpl.read(Long.parseLong(strEmpId));
					List<HrEisEmpMst> eisList  = empInfoDAOImpl.getHrEmpFromOrgEmp(orgEmpMst);
					empId = ((HrEisEmpMst)eisList.get(0)).getEmpId();
					
					HrForm16Dtls dtls = daoImpl.getForm16Rec(lookupShortName, empId);
					StringBuffer strBuffer = new StringBuffer();
					strBuffer.append("<formDtls>");
					if(dtls != null){
						strBuffer.append("present");
					}else{
						strBuffer.append("absent");
					}
					strBuffer.append("</formDtls>");

					String response = new AjaxXmlBuilder().addItem("ajax_key", strBuffer.toString()).toString();
					logger.info("orgNameString" + response);

					objectArgs.put("ajaxKey", response);
					resultObject.setResultValue(objectArgs);
					resultObject.setViewName("ajaxData");
				}
			}else{
				String[] encInvestXMLStr = StringUtility.getParameterValues("encAddForm16XML", request);
				List listForm16CustomVO = FileUtility.xmlFilesToVoByXStream(encInvestXMLStr);

				logger.info("size of the list " + listForm16CustomVO.size());
				//logger.info("size of the list " + listForm16CustomVO.size());

				List <HrForm16TaxDeducDtls> listTaxDed = null;

				HrForm16DtlsDaoImpl form16DtlsDaoImpl = new HrForm16DtlsDaoImpl(HrForm16Dtls.class,serv.getSessionFactory());
				HrForm16TaxDeducDaoImpl taxDeducDaoImpl = new HrForm16TaxDeducDaoImpl(HrForm16TaxDeducDtls.class,serv.getSessionFactory());

				HrForm16Dtls form16Dtls;
				HrForm16Dtls Dupform16Dtls;
				Form16CustomVO customVO;
				//HrForm16TaxDeducDtls dedDtls = new HrForm16TaxDeducDtls();
				for (Iterator i = listForm16CustomVO.iterator(); i.hasNext();)
				{
					logger.debug("getting objects one by one from list");
					//logger.info("getting objects one by one from list");

					Object form16CustVO = i.next();

					customVO = new Form16CustomVO();
					customVO = (Form16CustomVO) form16CustVO;

					form16Dtls = new HrForm16Dtls();

					form16Dtls = customVO.getForm16Dtls();

					listTaxDed =  new ArrayList<HrForm16TaxDeducDtls>();
					listTaxDed = customVO.getList();

					IdGenerator idGen1 = new IdGenerator();
					IdGenerator idGen2 = new IdGenerator();
					long form16DtlId = idGen1.PKGenerator("HR_FORM16_DTLS", objectArgs);


					//logger.info("form16DtlId" + form16DtlId);

					form16Dtls.setForm16DtlId(form16DtlId);
					//logger.info("the Emp Info " + form16Dtls.getHrEisEmpMst());
					form16DtlsDaoImpl.create(form16Dtls);

					//Set<HrForm16TaxDeducDtls> set = new HashSet<HrForm16TaxDeducDtls>(0);

					for(Iterator innerIter = listTaxDed.iterator(); innerIter.hasNext();){

						HrForm16TaxDeducDtls dedDtls = (HrForm16TaxDeducDtls) innerIter.next();

						//logger.info("Query " + dedDtls.getDeducDtlsId() + dedDtls.getDeducDtlsMonth());
						logger.info("Query " + dedDtls.getDeducDtlsId() + dedDtls.getDeducDtlsMonth());

						dedDtls.setDeducDtlsId(IDGenerateDelegate.getNextId("HR_FORM16_TAX_DEDUC_DTLS", objectArgs));
						//logger.info("List new Inside the Tax Ded Table" + dedDtls.getDeducDtlsId());

						dedDtls.setForm16DtlId(form16Dtls);
						//set.add(dedDtls);
						taxDeducDaoImpl.create(dedDtls);

					}

				}
				logger.info("INSERTED SUCCESSFULLY");
				//logger.info("The insert operation is successfully completed");

				/*Map redirectMap = new HashMap();
				redirectMap.put("actionFlag", "Form16Details");*/
				//objectArgs.put("redirectMap", redirectMap);			

				resultObject = serv.executeService("getForm16Details", objectArgs);
				objectArgs.put("MESSAGECODE",300005);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				//resultObject.setViewName(null);

			}






		}catch (Exception e) {
			logger.info("There is some error at editting or inserting time");			
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	public ResultObject getForm16Details(Map objectArgs)
	{
		//logger.info("In the get Method For Other Details"); 
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		LoginDetails objLoginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		try
		{
			HrForm16DtlsDao form16DtlsDao = new HrForm16DtlsDaoImpl(HrForm16Dtls.class,serv.getSessionFactory());
			List<HrForm16Dtls> list = form16DtlsDao.getForm16DetailsFromDB(objLoginDetails.getLocation().getLocId());

			for(HrForm16Dtls dtls : list){
				dtls.getHrEisEmpMst();
				dtls.getFinYrId();
				if(dtls.getHrEisEmpMst() != null){
					dtls.getHrEisEmpMst().getOrgEmpMst();
					if(dtls.getHrEisEmpMst().getOrgEmpMst() != null){
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname();
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpMname();
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpLname();
					}
				}

			}

			objectArgs.put("dataList", list);

			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("Form16Details");

		}catch (Exception e) {
			logger.info("There is some error at getting time");			
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	public ResultObject updateForm16Details(Map objectArgs)
	{
		//logger.info("In the update Method For Other Details"); 
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		LoginDetails objLoginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		try
		{
			HrForm16DtlsDaoImpl daoImpl = new HrForm16DtlsDaoImpl(HrForm16Dtls.class,serv.getSessionFactory());
			String directUpdate  = StringUtility.getParameter("directUpdate", request);
			String strUpdate  = StringUtility.getParameter("update", request);

			if(directUpdate != null && "Y".equalsIgnoreCase(directUpdate)){
				String strForm16DtlId = StringUtility.getParameter("form16DtlId", request); 
				if(strForm16DtlId != null){
					long form16DtlId = Long.parseLong(strForm16DtlId);
					HrForm16Dtls dtls = (HrForm16Dtls) daoImpl.read(form16DtlId);

					dtls.getHrEisEmpMst().getOrgEmpMst();
					if(dtls.getHrEisEmpMst().getOrgEmpMst() != null){
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname();
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpMname();
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpLname();
					}

					HrForm16TaxDeducDao deducDao = new HrForm16TaxDeducDaoImpl(HrForm16TaxDeducDtls.class,serv.getSessionFactory());
					List<HrForm16TaxDeducDtls> list = deducDao.getDeducDtls(dtls);
					objectArgs.put("Form16Dtls",dtls);

					CmnLookupMstDAOImpl lookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					List<CmnLookupMst> cmnLookupMstList = lookupMstDAOImpl.getListByColumnAndValue("lookupShortName", dtls.getFinYrId());
					if(!cmnLookupMstList.isEmpty()){
						CmnLookupMst cmnLookupMst = (cmnLookupMstList.size() > 1) ? cmnLookupMstList.get(1) : cmnLookupMstList.get(0);
						logger.info("cmnLookupMstForYear  " + cmnLookupMst.getLookupId() + " " + cmnLookupMst.getLookupShortName());
						objectArgs.put("cmnLookupMstForYear",cmnLookupMst);
					}

					HrForm16BankMstDao bankMstDao = new HrForm16BankMstDaoImpl(HrForm16BankMst.class,serv.getSessionFactory());
					List<HrForm16BankMst> bankList = bankMstDao.getBankData();
					logger.info("The Bank List is : " + bankList);
					//logger.info("The Bank List is : " + bankList);
					objectArgs.put("bankList", bankList);

					int counter = 1; //For 14 different fields
					for(HrForm16TaxDeducDtls taxDtls : list){
						objectArgs.put("Form16TaxDeducDtls"+counter,taxDtls);
						counter++;
					}

					CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					List financialYearList = lookupDAO.getAllChildrenByLookUpNameAndLang("FinancialYear", objLoginDetails.getLangId());
					logger.info("the value of financialYearList size is "+financialYearList.size());
					objectArgs.put("financialYearList", financialYearList);

					objectArgs.put("isUpdate","Yes");


					HrForm16DtlsDao dtlsDao = new HrForm16DtlsDaoImpl(HrForm16Dtls.class,serv.getSessionFactory());
					List EmpList = dtlsDao.getEmpData(dtls.getHrEisEmpMst().getEmpId(), Integer.parseInt(dtls.getFinYrId()));


					if(!EmpList.isEmpty()){
						for (Iterator it = EmpList.iterator(); it.hasNext();) 
						{
							Object[] rowList = (Object[]) it.next();
							String gross = (rowList[0]!=null && !rowList[0].equals("")) ? rowList[0].toString():"0";
							String gpf = (rowList[1]!=null && !rowList[1].equals("")) ?rowList[1].toString():"0";

							logger.info("Gross ########" + gross);
							logger.info("gpf ########" + gpf);

							objectArgs.put("GSalary", gross);
							objectArgs.put("GPF", gpf);
						}
					}else{
						logger.info("List Empty " );
					}


					resultObject.setResultValue(objectArgs);
					//	resultObject = serv.executeService("getEmpDataForm16", objectArgs);
					resultObject.setViewName("ITOtherDetails");
				}
			}else if(directUpdate != null && "N".equalsIgnoreCase(directUpdate)){
				String strYear = StringUtility.getParameter("year", request); 
				String strEmpid = StringUtility.getParameter("empId", request);

				//logger.info("strYear  " +strYear);
				//logger.info("strEmpid  " +strEmpid);

				CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lookupMst = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strYear, objLoginDetails.getLangId());
				OrgEmpMst orgEmpMst = new OrgEmpMst();
				EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				EmpInfoDAOImpl hrEisEmpDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				
				String lookupShortName = lookupMst.getLookupShortName();
				//logger.info("lookupShortName  " +lookupShortName);
				long empId  = 0;
				if(strEmpid != null ){
					//empId = Long.parseLong(strEmpid);
					orgEmpMst = empDAOImpl.read(Long.parseLong(strEmpid));
					List<HrEisEmpMst> eisList  = hrEisEmpDAO.getHrEmpFromOrgEmp(orgEmpMst);
					empId = ((HrEisEmpMst)eisList.get(0)).getEmpId();
				}

				HrForm16Dtls dtls = daoImpl.getForm16Rec(lookupShortName, empId);
				//logger.info("dtls are " +dtls);
				if(dtls != null){
					//logger.info("dtls  " +dtls.getFinYrId());

					dtls.getHrEisEmpMst().getOrgEmpMst();
					if(dtls.getHrEisEmpMst().getOrgEmpMst() != null){
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname();
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpMname();
						dtls.getHrEisEmpMst().getOrgEmpMst().getEmpLname();
					}

					HrForm16TaxDeducDao deducDao = new HrForm16TaxDeducDaoImpl(HrForm16TaxDeducDtls.class,serv.getSessionFactory());
					List<HrForm16TaxDeducDtls> list = deducDao.getDeducDtls(dtls);
					objectArgs.put("Form16Dtls",dtls);

					objectArgs.put("isUpdate","Yes");

					HrForm16BankMstDao bankMstDao = new HrForm16BankMstDaoImpl(HrForm16BankMst.class,serv.getSessionFactory());
					List<HrForm16BankMst> bankList = bankMstDao.getBankData();
					logger.info("The Bank List is : " + bankList);
					//logger.info("The Bank List is : " + bankList);
					objectArgs.put("bankList", bankList);

					int counter = 1; //For 14 different fields
					for(HrForm16TaxDeducDtls taxDtls : list){
						objectArgs.put("Form16TaxDeducDtls"+counter,taxDtls);
						counter++;
					}

					CmnLookupMstDAOImpl lookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					List<CmnLookupMst> cmnLookupMstList = lookupMstDAOImpl.getListByColumnAndValue("lookupShortName", dtls.getFinYrId());
					if(!cmnLookupMstList.isEmpty()){
						CmnLookupMst cmnLookupMst = (cmnLookupMstList.size() > 1) ? cmnLookupMstList.get(1) : cmnLookupMstList.get(0);
						//logger.info("cmnLookupMstForYear  " + cmnLookupMst.getLookupName() + " " + cmnLookupMst.getLookupShortName());
						objectArgs.put("cmnLookupMstForYear",cmnLookupMst);
					}

					CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					List financialYearList = lookupDAO.getAllChildrenByLookUpNameAndLang("FinancialYear", objLoginDetails.getLangId());
					logger.info("the value of financialYearList size is "+financialYearList.size());

					objectArgs.put("financialYearList", financialYearList);
					resultObject.setResultValue(objectArgs);

					resultObject = serv.executeService("getEmpDataForm16", objectArgs);
					resultObject.setViewName("ITOtherDetails");
				}else{
					logger.info("No Record");

					//EmpInfoDAOImpl hrEisEmpDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
					
					long eisEmpId  = 0;
					if(strEmpid != null ){
						//empId = Long.parseLong(strEmpid);
						orgEmpMst = empDAOImpl.read(Long.parseLong(strEmpid));
						List<HrEisEmpMst> eisList  = hrEisEmpDAO.getHrEmpFromOrgEmp(orgEmpMst);
						eisEmpId = ((HrEisEmpMst)eisList.get(0)).getEmpId();
					}
					
					HrEisEmpMst hrEisEmpMst = hrEisEmpDAO.read(eisEmpId);
					if(hrEisEmpMst != null){
						if(hrEisEmpMst.getOrgEmpMst() != null){
							String empLastName = hrEisEmpMst.getOrgEmpMst().getEmpLname();
							objectArgs.put("empLastName", empLastName);
						}
					}
					objectArgs.put("isFound", "No");

					CmnLookupMstDAOImpl lookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					List<CmnLookupMst> cmnLookupMstList = lookupMstDAOImpl.getListByColumnAndValue("lookupShortName", lookupShortName);
					if(!cmnLookupMstList.isEmpty()){
						CmnLookupMst cmnLookupMst = (cmnLookupMstList.size() > 1) ? cmnLookupMstList.get(1) : cmnLookupMstList.get(0);
						logger.info("cmnLookupMstForYear  " + cmnLookupMst.getLookupId() + " " + cmnLookupMst.getLookupShortName());
						objectArgs.put("cmnLookupMstForYear",cmnLookupMst);
					}

					resultObject = serv.executeService("displayITOtherDtl", objectArgs);
					/*resultObject.setResultValue(objectArgs);
 					resultObject.setViewName("ITOtherDetails");*/
				}
			}else if(strUpdate != null && "Y".equalsIgnoreCase(strUpdate)){
				//logger.info("In the final Update");
				String strForm16DtlId = StringUtility.getParameter("dtlsId", request); 
				if(strForm16DtlId != null){
					long form16DtlId = Long.parseLong(strForm16DtlId);
					HrForm16Dtls dtls = (HrForm16Dtls) daoImpl.read(form16DtlId);

					String strOtherAllowance = StringUtility.getParameter("OtherAllowance", request);
					String strForeignAllow = StringUtility.getParameter("foreignAllow", request);
					String strTaxPaidChallan = StringUtility.getParameter("TaxPaidChallan", request);
					String strTaxDedArrear = StringUtility.getParameter("TaxDedArrear", request);
					String strFinYear = StringUtility.getParameter("FinYear", request);
					String hbaIntrestClaimed = StringUtility.getParameter("hbaIntrestClaimed", request);
					String hbaRepayClaimed = StringUtility.getParameter("hbaRepayClaimed", request);
					String challanNumber = StringUtility.getParameter("challanNumber", request);
					
					String travelAllow = StringUtility.getParameter("travelAllow", request);
					String profTax = StringUtility.getParameter("profTax", request);
					String hbaIntrest = StringUtility.getParameter("hbaIntrest", request);
					String gpfCpf = StringUtility.getParameter("gpfCpf", request);
					String govtInsurance = StringUtility.getParameter("govtInsurance", request);
					String repayHba = StringUtility.getParameter("repayHba", request);

					if(strOtherAllowance != null){
						dtls.setOtherAllow(Double.parseDouble(strOtherAllowance));
					}
					if(strForeignAllow != null){
						dtls.setForeignAllow(Double.parseDouble(strForeignAllow));
					}
					if(strTaxPaidChallan != null)
					{
						dtls.setChallanTax(Double.parseDouble(strTaxPaidChallan));
					}
					if(strTaxDedArrear != null)
					{
						dtls.setArrearTax(Double.parseDouble(strTaxDedArrear));
					}
					if(strFinYear != null && !"".equals(strFinYear) && !"Select".equals(strFinYear))
					{
						CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
						CmnLookupMst lookupMst = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFinYear, objLoginDetails.getLangId());
						logger.info("Double lookupMst  " +lookupMst.getLookupId());
						dtls.setFinYrId(lookupMst.getLookupShortName());
					}

					if(travelAllow!= null){ 
						dtls.setTravelAllow(Double.parseDouble(travelAllow));
					}
					if(profTax!= null){
						dtls.setProfTax(Double.parseDouble(profTax));
					}
					if(hbaIntrest!= null){
						dtls.setHbaIntrest(Double.parseDouble(hbaIntrest));
					}
					if(gpfCpf!= null){
						dtls.setGpfCpf(Double.parseDouble(gpfCpf));
					}
					if(govtInsurance!= null){
						dtls.setGovtInsurance(Double.parseDouble(govtInsurance));
					}
					if(repayHba!= null){
						dtls.setRepayHba(Double.parseDouble(repayHba));
					}
					
						dtls.setChallanNumber(challanNumber);
			       
					if(hbaIntrestClaimed.equals(""))
					{
						dtls.setHbaInterestClaimed(0);			
					}
					else
					{
						dtls.setHbaInterestClaimed(1);	
					}
					if(hbaRepayClaimed.equals(""))
					{
						dtls.setHbaRepayClaimed(0);				
					}
					else
					{
						dtls.setHbaRepayClaimed(1);	
					}

					dtls.setUpdatedDate(new Date());
					dtls.setUpdatedBy(objLoginDetails.getUser());

					HrForm16TaxDeducDao deducDao = new HrForm16TaxDeducDaoImpl(HrForm16TaxDeducDtls.class,serv.getSessionFactory());
					List<HrForm16TaxDeducDtls> list = deducDao.getDeducDtls(dtls);

					String mDate;
					String bankDtl;
					String incomeTax;

					HrForm16TaxDeducDtls deducDtls ;
					HrForm16BankMstDaoImpl bankMstDaoImpl = new HrForm16BankMstDaoImpl(HrForm16BankMst.class,serv.getSessionFactory());

					for(int iterate = 0; iterate < 14 ; iterate++){

						if(list.size() > iterate &&  list.get(iterate) != null ){
							deducDtls = list.get(iterate);
							mDate = StringUtility.getParameter("M"+(iterate+1)+"Date", request);
							bankDtl = StringUtility.getParameter("cmbBankName"+(iterate+1), request);

							if(iterate < 12)
							{
								incomeTax = StringUtility.getParameter("it"+(iterate+1), request);

								if(incomeTax != null){
									deducDtls.setIncomeTax(Double.parseDouble(incomeTax));
								}
							}

							if(mDate != null){
								Date date = StringUtility.convertStringToDate(mDate);
								deducDtls.setDeducDtlsDate(date);
							}
							if(bankDtl != null && !"Select".equals(bankDtl)){
								HrForm16BankMst bankMst = (HrForm16BankMst) bankMstDaoImpl.read(Long.parseLong(bankDtl));
								deducDtls.setDeducDtlsBankId(bankMst);
							}

							deducDtls.setForm16DtlId(dtls);
							//deducDtls.setUpdatedDate(new Date());
							//deducDtls.setUpdatedBy(objLoginDetails.getUser());
						}
					}
					resultObject = serv.executeService("getForm16Details", objectArgs);
					objectArgs.put("MESSAGECODE",300005);
					resultObject.setResultCode(ErrorConstants.SUCCESS);
					resultObject.setResultValue(objectArgs);
				}
			}

		}catch (Exception e) {
			logger.info("There is some error at getting time");			
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
	public ResultObject getEmpData(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		LoginDetails objLoginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
		try{
			String strEmpId = StringUtility.getParameter("empId", request);
			String strYear = StringUtility.getParameter("year", request);

			String gross = "";
			String gpf = "";

			HrForm16DtlsDao dtlsDao = new HrForm16DtlsDaoImpl(HrForm16Dtls.class,serv.getSessionFactory());
			CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());			
			CmnLookupMst lookupMst = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strYear, objLoginDetails.getLangId());
			String lookupShortName = lookupMst.getLookupShortName();

			OrgEmpMst orgEmpMst = new OrgEmpMst();
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());


			long eisEmpId  = 0;
			int year = 0;
			if(strEmpId != null ){

				orgEmpMst = empDAOImpl.read(Long.parseLong(strEmpId));
				List<HrEisEmpMst> eisList  = empInfoDAOImpl.getHrEmpFromOrgEmp(orgEmpMst);
				eisEmpId = ((HrEisEmpMst)eisList.get(0)).getEmpId();
			}

			if(lookupShortName != null ){
				year = Integer.parseInt(lookupShortName);
			}

logger.info(":::::::::::::::::::::::::::::::::::::::::::::    eisEmpId, year" + eisEmpId + "  and " + year);
			List list = dtlsDao.getEmpData(eisEmpId, year);


			if(!list.isEmpty()){

				for (Iterator it = list.iterator(); it.hasNext();) 
				{
					Object[] rowList = (Object[]) it.next();

					gross = rowList[0].toString();
					gpf = rowList[1]!=null?rowList[1].toString():"";

					logger.info("Gross ########" + gross);
					logger.info("gpf ########" + gpf);


				}
			}else{
				logger.info("List Empty " );

			}

			String isAjax = StringUtility.getParameter("isAjax", request);
			if(isAjax != null && !"".equals(isAjax) && "true".equals(isAjax)){
				logger.info("Through Ajax");
				StringBuffer strBuffer = new StringBuffer();

				strBuffer.append("<EmpData>");

				strBuffer.append("<gross>");
				strBuffer.append(gross);
				strBuffer.append("</gross>");

				strBuffer.append("<gpf>");
				strBuffer.append(gpf);
				strBuffer.append("</gpf>");

				strBuffer.append("</EmpData>");

				String response = new AjaxXmlBuilder().addItem("ajax_key", strBuffer.toString()).toString();
				logger.info("EmpData" + response);

				objectArgs.put("ajaxKey", response);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ajaxData");

			}else{
				logger.info("Through Service Update");
				objectArgs.put("GSalary", gross);
				objectArgs.put("GPF", gpf);
				resultObject.setResultValue(objectArgs);
			}

		}catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
}
