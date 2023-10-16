
package com.tcs.sgv.eis.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnCityMstDAOImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DesigMasterDAO;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.empPunishmentDAOImpl;
import com.tcs.sgv.eis.dao.getGradDesgMapDAO;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrEmpPunishmentDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PayrollCustomVO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;



public class OtherDetailServiceMerged extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	
    ResourceBundle constantsBundle = ResourceBundle.getBundle("../resources/Payroll");
    
    //long finYearId = Long.parseLong( constantsBundle.getString("currentFinYearId").toString() ); 22
    long finYearId = Long.parseLong( constantsBundle.getString("finYearId").toString() ); //21
    
	public ResultObject getOtherData(Map objectArgs)
	{
		logger.info("inside getOtherData of other detail service......new logger");
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{			
            ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
            OtherDetailDAOImpl otherDtlsDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
            HrEisOtherDtls hrEisotherDtls = new HrEisOtherDtls();
		    //// parameters for update paybill
            String updatePaybillFlg = "n";
		    int paybillMonth=0;
		    int paybillYear=0;
		    //// parameters for update paybill
		    long payCommissionId=0;
            Map loginMap = (Map) objectArgs.get("baseLoginMap");
                  	
			long locationId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long languageId=1;
			
			PayBillDAOImpl billDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			String gpf="";
			String newGpf="";
			// Modify By Urvin Shah
			//long locationId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			
			 CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");
	   		 String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
	   		 CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			
//			added by Ankit Bhatt for merging screens
			String empAllRec="";
			if(voToService.get("empAllRec")!=null)
				empAllRec = voToService.get("empAllRec").toString();
				logger.info("empAllRec ion other Details service " + empAllRec);
				//ended by Ankit Bhatt
			
			StringBuffer data=new StringBuffer();
			//added by ravysh
			String mergedScreen=(voToService.get("MergedScreen")!=null?voToService.get("MergedScreen").toString():"");
			String previewBill=(voToService.get("PreviewBill")!=null?voToService.get("PreviewBill").toString():"");
			
			logger.info("PreviewBIll at start is "+previewBill);
			String edit="";
			long gradeId=0;
			
			//by manoj for employee search
			String empIdStrLoan = (String)voToService.get("Employee_ID_EmpLoanSearch");
			String empIdStr = (String)voToService.get("Employee_ID_EmpOtherSearch");
			
			logger.info("the emp id from search employee "+empIdStr);
			//end by manoj for employee search	
			
			String employeeName="";
			if((voToService.get("Employee_srchNameText_EmpOtherSearch")!=null&&!voToService.get("Employee_srchNameText_EmpOtherSearch").equals("")))
				employeeName=(String)voToService.get("Employee_srchNameText_EmpOtherSearch").toString();
			objectArgs.put("empName",employeeName);
			
			if(voToService.get("edit")!=null)
			{
				edit = (String)voToService.get("edit").toString();
			}
			logger.info("the value of edit is "+edit);
			if(!edit.equals(null) && edit.equalsIgnoreCase("Y"))
			{
            	logger.info("I m in edit mode getOtherData");

    		    
    		    if(voToService.get("updatePaybillFlg")!=null)
    		    {	
    		    	updatePaybillFlg=voToService.get("updatePaybillFlg").toString();
    		    }
     		    if(voToService.get("paybillMonth")!=null)
    		    {	
     		    	paybillMonth=Integer.parseInt(voToService.get("paybillMonth").toString());
    		    }	
     		    if(voToService.get("paybillYear")!=null)
    		    {	
     		    	paybillYear=Integer.parseInt(voToService.get("paybillYear").toString());
    		    }	

     		    
    		    logger.info("test update paybill parameters in other details service"+updatePaybillFlg+" "+paybillMonth+" "+paybillYear);
    		    logger.info(voToService.get("otherId"));
    		    
            	long sid=0;
            	sid=Long.parseLong((voToService.get("otherId")!=null && !voToService.get("otherId").toString().equals("")? voToService.get("otherId").toString():"0"));
            	long employeeId=0;
            	
            	
            	if(sid==0){
            		employeeId=Long.parseLong((voToService.get("empId").toString()!=null && voToService.get("empId").toString()!="")? voToService.get("empId").toString():"0");
            		hrEisotherDtls = otherDtlsDAO.getOtherData(employeeId);
            	}
            	else{
            		hrEisotherDtls = otherDtlsDAO.read(sid);
            	}
            	
        		//logger.info("The Other Employee Id is:-"+hrEisotherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());
            	//HrEisEmpMst empMstVO= (HrEisEmpMst)objectArgs.get("empMstVO");
            	

                // added by ankit Bhatt for merging two screens - Other Dtls and Allow Mapping
    			
    			Map voToServiceMap = new HashMap();
    			voToServiceMap.put("EmpAllowMpg", "Y");
    			voToServiceMap.put("EmpId", String.valueOf(hrEisotherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId()));
    			
    			
    			objectArgs.put("voToServiceMap",voToServiceMap);
    			logger.info("Going to execute EmpAllowMap service from OtherDtls service");
    			ResultObject allowResultObj = serv.executeService("empAllowMap", objectArgs);
    			objectArgs = (Map)allowResultObj.getResultValue();
    			logger.info("finish EmpAllowMap service from OtherDtls service");
    			//ended by Ankit Bhatt.
    			
    			if(hrEisotherDtls!=null && hrEisotherDtls.getHrEisSgdMpg()!=null) {
                    long sgdMapId = hrEisotherDtls.getHrEisSgdMpg().getSgdMapId();
                    logger.info("inside otherdatamerged - getOtherData - Sgd Map Id " + sgdMapId);
                    objectArgs.put("sgdMapId",sgdMapId);
    			}
    			else
           		logger.info("inside otherdatamerged - getOtherData - Sgd Map Id is null");
    			
    			logger.info("inside otherdatamerged - getOtherData - cityId " + hrEisotherDtls.getCity());
    			if(hrEisotherDtls!=null && hrEisotherDtls.getCity()!=0) {
    				long cityId = hrEisotherDtls.getCity();
    				 logger.info("inside otherdatamerged - getOtherData - cityId " + cityId);
    				 objectArgs.put("cityId",cityId);    				 
    			}
    			else
    				logger.info("inside otherdatamerged - getOtherData - cityId is null");
    			
    			objectArgs.put("updatePaybillFlg", updatePaybillFlg);
    		    objectArgs.put("paybillMonth", paybillMonth);
    		    objectArgs.put("paybillYear", paybillYear);
    		    
                EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
                //EmpDAOImpl 
               long userId=hrEisotherDtls.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
            /////////////////////////////////////////////////////////////   
               long empId = hrEisotherDtls.getHrEisEmpMst().getEmpId();
			
               //added by Ankit Bhatt
               empPunishmentDAOImpl empPunishmentDAO = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,serv.getSessionFactory());
				boolean isPunished = empPunishmentDAO.isEmpPunished(empId);
				logger.info("Employee is Punished or not " + isPunished);
				
				objectArgs.put("isPunished", isPunished);
				//ended
			
			
			String gradeName="";
			String postName="";
			String desigName="";
			String empName="";
			List <OrgPostDetailsRlt> postDtlsList;
				

			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			//OrgEmpMst orgEmpMst = empDAOImpl.read(empId);
			getGradDesgMapDAO gradDesgMapDAO = new getGradDesgMapDAO(HrEisGdMpg.class,serv.getSessionFactory());
			HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
			GradDesgScaleMapDAO gradDesgScaleMapDAO = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
			EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			HrEisEmpMst empMst = empinfodao.read(empId);
			OrgEmpMst orgEmpMst = empDAOImpl.read(empMst.getOrgEmpMst().getEmpId());
			postDtlsList=otherDtlsDAO.getPostDetailData(langId,orgEmpMst.getOrgUserMst().getUserId());
			if(langId!=1) {
				long LanguageID=1;
				orgEmpMst = empInfoDAOImpl.getEmployee(orgEmpMst.getOrgUserMst(),langId);
				gradeName=orgEmpMst.getOrgGradeMst().getGradeName();
				postDtlsList=otherDtlsDAO.getPostDetailData(langId,orgEmpMst.getOrgUserMst().getUserId());				
			}
			if(orgEmpMst.getEmpFname() != null)
				empName +=  orgEmpMst.getEmpFname()+ " ";
			if(orgEmpMst.getEmpMname() != null)
				empName +=  orgEmpMst.getEmpMname()+ " ";
			if(orgEmpMst.getEmpLname() != null)
				empName +=  orgEmpMst.getEmpLname();
			//added by Mrugesh for passing Emp Type in Edit Screen 
			long empType = hrEisotherDtls.getHrEisEmpMst().getEmpType();
			//ended
			List <HrEisSgdMpg> hrEisSgdMpgList= new ArrayList();
			List<HrEisSgdMpg> scaleListForFifthPay=new ArrayList();
			List<HrEisSgdMpg> scaleListForSixthPay=new ArrayList();
			gradeName=orgEmpMst.getOrgGradeMst().getGradeName();
			gradeId=orgEmpMst.getOrgGradeMst().getGradeId();
			long gdmap=0;
			if(postDtlsList.size()>0)
			{
				postName = postDtlsList.get(0).getPostName();
				desigName = postDtlsList.get(0).getOrgDesignationMst().getDsgnName();
				hrEisGdMpg = gradDesgMapDAO.getGDMapFromGD(orgEmpMst.getOrgGradeMst(),postDtlsList.get(0).getOrgDesignationMst(),cmnLocationMst);
				if(hrEisGdMpg.getGdMapId()!=0)
				gdmap= hrEisGdMpg.getGdMapId();
				hrEisSgdMpgList = gradDesgScaleMapDAO.getScalefromGD(hrEisGdMpg.getGdMapId(), langId);
				/*if(hrEisSgdMpgList != null && hrEisSgdMpgList.getHrEisScaleMst()				{
					
				}*/
				GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
				DesigMasterDAO desigMasterDAO = new DesigMasterDAO(OrgDesignationMst.class,serv.getSessionFactory());			
				String gradeElementCode= orgEmpMst.getOrgGradeMst().getGradeCode();
				String desigElementCode= postDtlsList.get(0).getOrgDesignationMst().getDsgnCode();
				List<OrgGradeMst> grade = gradeDao.getGradeName(gradeElementCode, langId);
				List<OrgDesignationMst> desig=desigMasterDAO.getDesigName(desigElementCode, langId);
				if(desig.size()>0&& grade.size()>0)
				{
					gradeName=grade.get(0).getGradeName();
					desigName=desig.get(0).getDsgnName();
				}         			
			}
			String gdmapid=""+gdmap+"";
           
                postDtlsList=otherDtlsDAO.getPostDetailData(langId,userId);
                HrEisSgdMpg hrEisSgdMpg = hrEisotherDtls.getHrEisSgdMpg();
                
                for (Iterator iter = hrEisSgdMpgList.iterator(); iter.hasNext();)
                {		
                	String scaleDesc="";
                	HrEisSgdMpg hrEisDgdMpgObj = new HrEisSgdMpg();
                	hrEisDgdMpgObj=(HrEisSgdMpg) iter.next();
                	long scaleId = hrEisDgdMpgObj.getSgdMapId();
                	long startAmt = hrEisDgdMpgObj.getHrEisScaleMst().getScaleStartAmt();
                	long incrAmt = hrEisDgdMpgObj.getHrEisScaleMst().getScaleIncrAmt();
                	long endAmt = hrEisDgdMpgObj.getHrEisScaleMst().getScaleEndAmt();
                	long gradePay= hrEisDgdMpgObj.getHrEisScaleMst().getScaleGradePay();
                	if(gradePay>0)
                		 scaleDesc  = String.valueOf(startAmt) +"-"+ String.valueOf(endAmt) +"/"+ String.valueOf(gradePay); 
                	else
                      	 scaleDesc  = String.valueOf(startAmt) +"-"+ String.valueOf(incrAmt) +"-"+ String.valueOf(endAmt); 
         		  	
          		     
          		    if(hrEisDgdMpgObj.getHrEisScaleMst().getScaleHigherEndAmt()!=0)
          			 scaleDesc+="-"+hrEisDgdMpgObj.getHrEisScaleMst().getScaleHigherIncrAmt()+ "-"+hrEisDgdMpgObj.getHrEisScaleMst().getScaleHigherEndAmt();
                	
                	logger.info("SGD Id:-"+scaleId);
          		  	logger.info("Scale Description:-"+scaleDesc);
                }
                //added by Ankit-Mrugesh
                long scaleId=0;
                String payCommissionName = "";
                if(hrEisSgdMpg!=null)   
                {
                 scaleId=hrEisotherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleId();
                 payCommissionId=Long.valueOf(hrEisotherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getCommissionCode());
                 payCommissionName = hrEisotherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getCommissionName();
                 objectArgs.put("gradePay", hrEisotherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleGradePay());
                }
                objectArgs.put("scaleId", scaleId);
                objectArgs.put("empName", empName);
                logger.info("inside otherdetails service the size is : "+hrEisSgdMpgList.size());
                logger.info("inside otherdetails service the size is : "+hrEisSgdMpgList.size());
               // objectArgs.put("scaleList", hrEisSgdMpgList);
                  
              logger.info("size of scale list "+hrEisSgdMpgList.size());
              /*  for(int o = 0 ;o<hrEisSgdMpgList.size();o++)
                {
                	logger.info("Pay commmission is "+Integer.valueOf(hrEisSgdMpgList.get(o).getHrEisScaleMst().getHrPayCommissionMst().getCommissionCode()));
                	if(Integer.valueOf(hrEisSgdMpgList.get(o).getHrEisScaleMst().getHrPayCommissionMst().getCommissionCode())==5)
                	{
                	scaleListForFifthPay.add(hrEisSgdMpgList.get(o));
                	}
                	else
                	{
                		scaleListForSixthPay.add(hrEisSgdMpgList.get(o));
                	}
                
                }*/
              //  objectArgs.put("scaleListForFifthPay", scaleListForFifthPay);
              //  objectArgs.put("scaleList", scaleListForSixthPay);
                objectArgs.put("scaleList", hrEisSgdMpgList);
                objectArgs.put("gradeName", gradeName);    
                objectArgs.put("postName", postName);    
                objectArgs.put("desigName", desigName);
    			objectArgs.put("actionList", hrEisotherDtls); 
    			objectArgs.put("otherList", hrEisotherDtls); 
    			objectArgs.put("empType", empType);
    			objectArgs.put("gradeId", gradeId);
    			objectArgs.put("UserId", userId);
    			objectArgs.put("payCommissionId", payCommissionId);
    			objectArgs.put("payCommissionName", payCommissionName);
    			//added by manish
    			List payCommissionList=otherDtlsDAO.getPayCommission();
    			objectArgs.put("payCommissionList", payCommissionList); 
    			
    			//ended by manish
    			
    			
    			
            	//added by japen for order master 
            	
            	 
            	List<CmnLookupMst> reason=null;
            	 
            	CmnLookupMstDAOImpl lookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
            	if(payCommissionId == 5)
            	{
            		reason = lookupDao.getAllChildrenByLookUpNameAndLang ("Reason-ScaleRev-5th",1);
            		objectArgs.put("reason",	 reason);
            	
            	}
            	else if(payCommissionId ==6)
            	{
            		reason = lookupDao.getAllChildrenByLookUpNameAndLang("Reason-ScaleRev-6th",1);
            		objectArgs.put("reason",	 reason);
            	}
            	else
            	{
            		reason=new ArrayList<CmnLookupMst>();
            		objectArgs.put("reason",	 reason);
            	 
            	}
            	
            	//added by japen
    			
    			
    			
    			
    			
    			
    			resultObject.setResultCode(ErrorConstants.SUCCESS);
    			resultObject.setResultValue(objectArgs);
    			
    			
    			CmnLanguageMst cmnlanguageMst= new CmnLanguageMst();
    			cmnlanguageMst.setLangId(langId);

    			OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());         	
    			List quaterTypeList = otherdtlsDao.getQuaterType();  
    			logger.info("Quaters Types are :-"+quaterTypeList.size());
    			objectArgs.put("quaterTypeList", quaterTypeList);
    			
    			CmnCityMstDAOImpl cityDao = new CmnCityMstDAOImpl(CmnCityMst.class,serv.getSessionFactory());    			
    			List <CmnCityMst> citylist = otherdtlsDao.getAllcity(cmnlanguageMst);
    			//orgempmaster empId
                long EmpID=hrEisotherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId();
    			
                // Added by ravysh to fetch bill no and psr no
                HashMap BillPsrNo=otherdtlsDao.getBillNoPsrNoByOrgEmpId(EmpID,locationCode);
                
                if(BillPsrNo.size()>0){
                	
                objectArgs.put("CurrPsrNo", BillPsrNo.get("CurrPsrNo")!=null?(String)BillPsrNo.get("CurrPsrNo"):"");
                objectArgs.put("CurrBillNo", BillPsrNo.get("CurrBillNo")!=null?(String)BillPsrNo.get("CurrBillNo"):"");
                objectArgs.put("NextPsrNo", BillPsrNo.get("NextPsrNo")!=null?(String)BillPsrNo.get("NextPsrNo"):"");
                objectArgs.put("NextOtherId", BillPsrNo.get("NextOtherId")!=null?(String)BillPsrNo.get("NextOtherId"):"");
                objectArgs.put("NextBillNo", BillPsrNo.get("NextBillNo")!=null?(String)BillPsrNo.get("NextBillNo"):"");
                
                objectArgs.put("PreviousPsrNo", BillPsrNo.get("PreviousPsrNo")!=null?(String)BillPsrNo.get("PreviousPsrNo"):"");
                objectArgs.put("PreviousOtherId", BillPsrNo.get("PreviousOtherId")!=null?(String)BillPsrNo.get("PreviousOtherId"):"");
                objectArgs.put("PreviousBillNo", BillPsrNo.get("PreviousBillNo")!=null?(String)BillPsrNo.get("PreviousBillNo"):"");
               
			}
                //ended by ravysh
    			objectArgs.put("cityList", citylist);
    			objectArgs.put("locationCode", locationCode);
    			
    			//Added by Muneendra for sevarth Id
    			String sevarthId = hrEisotherDtls.getHrEisEmpMst().getSevarthEmpCode();
    			logger.info("Sevarth in getOtherData is :::::::::::::::"+sevarthId); 
    			objectArgs.put("sevarthId", sevarthId);
    			//Ended by Muneendra
    			
    			//for hrEmpTraMpg by manoj
    			objectArgs.put("empId", empId);
    			//added by manish for gpfAccNo
    			List<Object> gpfList = new ArrayList<Object>();
    			gpfList=billDAOImpl.getGpfAcctNuFromByEisEmpId(empId);
    			if(gpfList.size()>0) {
    				Object[] row = (Object[])gpfList.get(0);

    				String accNo  = row[0] !=null && !row[0].toString().equalsIgnoreCase("") &&
    				!row[0].toString().equalsIgnoreCase("null") ? row[0].toString():"";

    				String  pfSeries=null;
    				if(row[1] != null)
    				{
    					pfSeries = row[1]!=null && !row[1].toString().equalsIgnoreCase("") 
    					&& !row[1].toString().equalsIgnoreCase("null") && !row[1].toString().equalsIgnoreCase("DCPS")
    					? row[1].toString():"";

    				}
    				if(pfSeries!=null && !pfSeries.trim().equals(""))
    					newGpf = pfSeries + "/" + accNo;
    				else 
    					newGpf = ""+accNo;
    			}    			
    				objectArgs.put("gpf",newGpf);
				
				logger.info("gpf number in service is "+gpf);
				
				
				//ednded
    			
    			objectArgs.put("otherId", sid);
    			
    			//if(serv.executeService("getHrEmpTraMpgServiceImpl", objectArgs) != null)
    		//	{
    			/*ResultObject resultObj1 = serv.executeService("getHrEmpTraMpgServiceImpl", objectArgs);
    			objectArgs =(Map) resultObj1.getResultValue();*/
    			//}
    			
    			objectArgs.put("EmpID", EmpID);
    			objectArgs.put("PreviewBill",previewBill);
    			logger.info("prievbill at middle  is "+previewBill);
    			//end for hrEmpTraMpg by manoj
    			
    			
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        // added by Ankit Bhatt for Merging the Screens.
		       
		        
		      //added by manish khunt
		        
		        long locId=Long.valueOf(loginMap.get("locationId").toString());
		        long dbId=Long.valueOf(loginMap.get("dbId").toString());
		        
		        long  changedEmpId=EmpID;
		        long postIdNew=0;
		        OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		        List lstPostIdUserId= otherDetailDAOImpl.getPostIdUserId(changedEmpId);
		  
		 	  long userIdNew=0;
		 	  for(Iterator itr=lstPostIdUserId.iterator();itr.hasNext();)
		 	  {    
		 		Object[] row = (Object[])itr.next();
		 		postIdNew=Long.valueOf(row[0].toString());
		 		userIdNew=Long.valueOf(row[1].toString());
		 		break;
		 	 }
		        OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		        OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		        OrgPostMst orgPostMstNew =orgPostMstDaoImpl.read(postIdNew);
		        OrgUserMst orgUserMstNew =orgUserMstDaoImpl.read(userIdNew);
		     	CmnDatabaseMstDaoImpl cmnDatabaseMstDao=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		     	CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDao.read(dbId);
		 		Map mapForChangedRecords=objectArgs;
		 		logger.info("valuie updated key........value is "+objectArgs.get("valueUpdated"));
		 		//////add here
		 		
		 		if(objectArgs.get("valueUpdated")!=null)
		 		{
		 			String up = objectArgs.get("valueUpdated").toString();
		 			if(!up.equals("Y"))
		 			{
		 				logger.info("Setting map to objectArgs");
				 		mapForChangedRecords.put("changedPostId",postIdNew);
				 		mapForChangedRecords.put("changedEmpId",changedEmpId);
				 		mapForChangedRecords.put("locId", locId);
				 		mapForChangedRecords.put("serviceLocator",serv);
				 		mapForChangedRecords.put("OrgPostMst",orgPostMstNew);
				 		mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
				 		mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
				 		GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
				 		long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
				 		logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
				 		logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
		 			}
		 		}
		 		
		 		
		 		
		 	//ended by manish khunt
				if(empAllRec.equalsIgnoreCase("true")==true)
				{
					resultObject.setViewName("otherEditListempAllRec");
					objectArgs.put("empAllRec", "true");
//					if(mergedScreen.equals("YES"))
//			        {
//			        	objectArgs.put("MergedScreen", "YES");
//			        	resultObject.setViewName("otherEditListMerged");
//			        }
					
				}
				else
//					ended by Ankit Bhatt
				{
					objectArgs.put("empAllRec", "false");
		        resultObject.setViewName("otherEditList");
		        
		        if(mergedScreen.equals("YES"))
		        {
		        	objectArgs.put("MergedScreen", "YES");
		        	resultObject.setViewName("OtherEditListMerged");
		        }
		        
		        
				}
				
				
				resultObject.setResultValue(objectArgs);
				logger.info("inside 1st");
            }
			//by manoj for employee search
			else if(empIdStr!=null && !empIdStr.equals(""))
			{
				long empId = Long.parseLong(empIdStr);							
                // added by Ankit Bhatt for checking emp is Punished or not
				
				//manish
				List<Object> gpfList = new ArrayList<Object>();
    			gpfList=billDAOImpl.getGpfAcctNuFromByEisEmpId(empId);
    			if(gpfList.size()>0) {
    				Object[] row = (Object[])gpfList.get(0);
    				
    				String accNo  = row[0]!=null && !row[0].toString().equalsIgnoreCase("") &&
    				                 !row[0].toString().equalsIgnoreCase("null") ? row[0].toString():"";
    				String pfSeries = row[1]!=null && !row[1].toString().equalsIgnoreCase("") &&
	                 !row[1].toString().equalsIgnoreCase("null") && !row[1].toString().toString().equalsIgnoreCase("DCPS") ? row[1].toString():"";
	                 
	                if(pfSeries!=null && !pfSeries.equals(""))
	                   newGpf = pfSeries + "/" + accNo;
    			}    			
    				objectArgs.put("gpf",newGpf);
								
				logger.info("gpf number in service is "+gpf);
				//manish
				empPunishmentDAOImpl empPunishmentDAO = new empPunishmentDAOImpl(HrEmpPunishmentDtls.class,serv.getSessionFactory());
				boolean isPunished = empPunishmentDAO.isEmpPunished(empId);
				logger.info("Employee is Punished or not " + isPunished);
				
				//ended by Ankit Bhatt.
				List actionList=new ArrayList();
    			List otherList = otherDtlsDAO.getAllOtherData(locationCode,langId,empId);
    			// Instead of making objects of empmaster ,othedetails,dfesignation strins have been used as VO as it is only for display to avoid whole of objects 
    			 Object[] row;
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
	 		    		payrollCustomVO.setCurrentBasic(Long.parseLong(row[4]!=null?row[4].toString():"0")) ; 
	 		    		payrollCustomVO.setSevarthId(row[7]!=null?row[7].toString():"");
	 		    		logger.info("Sevarth Id in(if) getOtherData is ::::::::::::::: " + row[7]);
	 		    		actionList.add(payrollCustomVO);	    	
	 		    	 }    			
				 }

    			// Map map = new HashMap();
    			objectArgs.put("actionList", actionList);
    			
    			//added by Ankit Bhatt.
    			objectArgs.put("isPunished", isPunished);
    			//ended by Ankit Bhatt
               
                resultObject.setResultCode(ErrorConstants.SUCCESS);
                
//			      added by Ankit Bhatt for Merging the Screens.
				if(empAllRec.equalsIgnoreCase("true")==true)
				{
					resultObject.setViewName("OtherViewListEmpAllRec");
					objectArgs.put("empAllRec", "true");
					objectArgs.put("empId", empId);
//					if(mergedScreen.equals("YES"))
//			        {
//			        	objectArgs.put("MergedScreen", "YES");
//			        	resultObject.setViewName("OtherViewListMerged");
//			        }
					
					
					
				}
				else
				{
					objectArgs.put("empId", "0");
					objectArgs.put("empAllRec", "false");
					resultObject.setViewName("ViewListOther");
					if(mergedScreen.equals("YES"))
			        {
			        	objectArgs.put("MergedScreen", "YES");
			        	objectArgs.put("PreviewBill",previewBill);
			        	resultObject.setViewName("OtherViewListMerged");
			        }
					
				}
//					ended by Ankit Bhatt
                
				resultObject.setResultValue(objectArgs);
				logger.info("inside 2nd");
				
			}
			//end by manoj for employee search
			else
			{
				List actionList=new ArrayList();
    			//logger.info("the Location Id is:-"+locationId);
    			List otherList = otherDtlsDAO.getAllOtherData(locationCode,langId,0);
    			// Instead of making objects of empmaster ,othedetails,dfesignation strins have been used as VO as it is only for display to avoid whole of objects 
    			 Object[] row;
    			 Date startDate;
    			 Date endDate;
    			 Date currDate = new Date();
    			 Calendar calCurr = Calendar.getInstance();
    			 calCurr.set(currDate.getYear()+1900, currDate.getMonth()+1,currDate.getDate());
    			 
    			 calCurr.set(Calendar.DATE,calCurr.getActualMaximum(Calendar.DATE) );
    			 
    			 currDate = (Date)calCurr.getTime();
    			 currDate.setDate(currDate.getDate()-1);
    			 currDate.setMonth(currDate.getMonth()-1);
    			 
    			 
    			 if(otherList!=null &&  otherList.size() >0 )
				 {                		
	 		    	 for(int k=0;k<otherList.size();k++) 
	 		    	 {
	 		    		String tempBuffer="";
	 		    		PayrollCustomVO payrollCustomVO = new PayrollCustomVO();
	 		    		row = (Object[])otherList.get(k);
	 		    		startDate = (Date)row[5];
	 		    		endDate = (Date)row[6];
	 		    		
	 		    		if(endDate == null || endDate.after(currDate)){
	 		    			logger.info("Before execution");
		 		    		payrollCustomVO.setOtherId(Long.parseLong(row[0]!=null?row[0].toString():"0")) ; 
		 		    		payrollCustomVO.setEmpName(row[1]!=null?row[1].toString():"") ; 
		 		    		payrollCustomVO.setGradeName(row[2]!=null?row[2].toString():"") ; 
		 		    		payrollCustomVO.setDsgnName(row[3]!=null?row[3].toString():"") ; 
		 		    		
		 		    		String otherbasic="";
		 		    		String basicdtl="";
		 		    		if(Long.parseLong(row[4].toString())!=0)
		 		    		{
		 		    			
		 		    			 NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
		 		    			 tempBuffer=currencyFormatter.format(Long.parseLong(row[4].toString().replace("$", "")));
	
		 		    			 otherbasic=tempBuffer.replace(".00", "");
		 		    			 basicdtl=otherbasic.replace("$", "");
	
	
		 		    			 
		 		    		}
		 		    		
		 		    		payrollCustomVO.setSevarthId(row[7]!=null?row[7].toString():"");
		 		    		logger.info("Sevarth Id in(if) getOtherData is ::::::::::::::: " + row[7]);
		 		    		
		 		    		payrollCustomVO.setCurrencycurrentBasic(basicdtl);
		 		    		actionList.add(payrollCustomVO);
	 		    		}	 		    		
	 		    	 }    			
				 }
    			
    
            	objectArgs.put("actionList", actionList);
            	
            	//added by Ankit Bhatt for merging screens
            	objectArgs.put("empId", "0");
            	objectArgs.put("empAllRec","false");
            	
            	
            	
            	//ended by Ankit Bhatt
            	resultObject.setResultValue(objectArgs);
            	resultObject.setResultCode(ErrorConstants.SUCCESS);
            	resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ViewListOther");
				
				
				if(mergedScreen.equals("YES"))
		        {
		        	objectArgs.put("MergedScreen","YES");
		        	resultObject.setViewName("OtherViewListMerged");
		        
//		        	voToService.put("empId", empId);
//		        	objectArgs.put("voToServiceMap",voToService);
		        }
				objectArgs.put("ViewFlag","True");
				objectArgs.put("PreviewBill",previewBill);
                
                logger.info("inside last");
                logger.info("Preview Bill is " + previewBill);
                logger.info("Preview at last is "+objectArgs.get("PreviewBill"));
                
            		
            }
			logger.info("full and final value is "+previewBill);
		}
		catch(Exception e)
		{			
			logger.error("Error is: "+ e.getMessage());
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");
			
		}
		
		return resultObject;
	}
	
	
	
	
	
	/*public long getGradePay(long sgdId)
	{
		long gradePay=0;
		
		
		return gradePay;
	}*/
	

	

}//end class
