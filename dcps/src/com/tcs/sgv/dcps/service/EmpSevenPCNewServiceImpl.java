package com.tcs.sgv.dcps.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.id.jericho.lib.html.Logger;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.MstScheme;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.DdoSchemeDAO;
import com.tcs.sgv.dcps.dao.DdoSchemeDAOImpl;
import com.tcs.sgv.dcps.dao.EmpSevenPCNewDaoImpl;
import com.tcs.sgv.dcps.dao.LedgerReportDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsDdoScheme;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class EmpSevenPCNewServiceImpl extends ServiceImpl{
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrLocationCode = null;

	private Long gLngPostId = null;

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private String gStrUserId = null; /* STRING USER ID */

	/* Global Variable for UserId */
	Long gLngUserId = null;

	private Long gLngDBId = null; /* DB ID */

	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			session = request.getSession();
			gStrPostId = SessionHelper.getPostId(inputMap).toString();
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}
	}

	public ResultObject loadEmpDetailsForSevenPCNew(Map objectArgs) throws Exception
	{

		gLogger.info("inside the method::::::::::::loadEmpDetailsForSevenPC");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			setSessionInfo(objectArgs);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			request = (HttpServletRequest) objectArgs.get("requestObj");

			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			//List bulkList=new ArrayList();
			List gradeIdDetailsList1 = null;
			String lStrSevarthId ="";
			String lStrEmpName ="";
			List empDetailsList=null;
			List empDetailsNewList = null;
			Date dob=null;
			Date doj=null;
			String desgName="";
			String cadre="";
			String group="";
			String payScale="";
			Long payinpayband=0l;
			String gradePay="";
			Long basicPay=0l;
			Long scaleStartAmount=0l;
			Long scaleEndAmount=0l;
			String flag="NO";
			String flagForEmpActiveChk="NO";
			int chkPostAndSerExp=0;
			String payCommission = "";
			String payInPAyBand="";
			String gradeId = "";
			String levelId = "";
			Long newBasicPerMatrix=0l;
			BigInteger newBasicPerScaleMatrix1 = null;
			BigInteger newBasicPerScaleMatrix2= null;
			BigInteger newBasicPerScaleMatrix3= null;
			BigInteger newBasicPerScaleMatrix4=null;
			List matrixDetails=null;
			List newMatrixDetails= null;
			List gradePayList = null;
			List gradePayDemotionList = null;
			long gisAppl = 0l;
			String empflag="Yes";
			int checkEmpRevCount= 0;
			Long newBasicAccToCal = 0l;
			int empsize = 1;
			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			 Date lDtcurDate = SessionHelper.getCurDate();
			 String haction="";
						if(StringUtility.getParameter("haction", request)!=null && StringUtility.getParameter("haction", request)!=""){
						haction=StringUtility.getParameter("haction", request).trim().toUpperCase();
						}
						gLogger.info("haction="+haction);
			if(haction.equals("LOADLEVELDATA"))	
			{
				LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
				List resList = new ArrayList();
				  String ajaxString = null;
				
				String level_id=StringUtility.getParameter("level_id", request).trim().toString();
				gLogger.info("level_id="+level_id);
				resList = dao.getStateNewBasicAsPerMAtrix(level_id);
				gLogger.info("resList="+resList);

		                ajaxString= new AjaxXmlBuilder().addItems(resList,"desc","id",true).toString();
		                gLogger.info("ajaxString="+ajaxString);
		           
		            objectArgs.put("ajaxKey", ajaxString);
		            resultObject.setResultCode(ErrorConstants.SUCCESS); 
		            resultObject.setResultValue(objectArgs);
		            resultObject.setViewName("ajaxData");
			}
			else
			{
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
			String ddoCode = dao.getDdoCode(locId);	
			if(StringUtility.getParameter("txtSevaarthId", request)!=null && StringUtility.getParameter("txtSevaarthId", request)!=""){
			lStrSevarthId = StringUtility.getParameter("txtSevaarthId", request).trim().toUpperCase();
			flag="Yes";
			chkPostAndSerExp = dao.chkPostAndSerExp(lStrSevarthId,locId,flag);
			gLogger.info("chkPostAndSerExp1:"+chkPostAndSerExp);
			if(chkPostAndSerExp>0){
				gLogger.info("chkPostAndSer   111 :"+chkPostAndSerExp);
				flagForEmpActiveChk="YES";
				empDetailsList= pcNewDaoImpl.getSevenPCStateEmpDetails(lStrSevarthId,locId,flag);	
			    gisAppl = pcNewDaoImpl.getEmpDetailsList(lStrSevarthId,flag);
				
			}
			else{
				gLogger.info("chkPostAndSerExp   22222:"+chkPostAndSerExp);
				empsize =  0;
				objectArgs.put("inactivemsg","Kindly check the post and service Expire date of the Employee  ");	
			}
			}
			else if(StringUtility.getParameter("txtEmployeeName", request)!=null && StringUtility.getParameter("txtEmployeeName", request)!=""){
			lStrEmpName = StringUtility.getParameter("txtEmployeeName", request).trim().toUpperCase();
			gLogger.info("lStrSevarthId:"+lStrSevarthId+"lStrEmpName:"+lStrEmpName);	
			flag="NO";
			chkPostAndSerExp = dao.chkPostAndSerExp(lStrEmpName,locId,flag);
			if(chkPostAndSerExp>0){
				gLogger.info("chkPostAndSerExp   3333:"+chkPostAndSerExp);
				empDetailsList= pcNewDaoImpl.getSevenPCStateEmpDetails(lStrEmpName,locId,flag);	
		 	    gisAppl = pcNewDaoImpl.getEmpDetailsList(lStrEmpName,flag);
				flagForEmpActiveChk="YES";
			}
			else{
				gLogger.info("chkPostAndSerExp   4444:"+chkPostAndSerExp);
				empsize =  0;
				objectArgs.put("inactivemsg","Kindly check the post and service Expire date of the Employee  ");	
				
			}
			}
			gLogger.info("chkPostAndSerExp3:"+chkPostAndSerExp);
			     String empSevarthID="";
				Iterator IT ;
				String resStr =  null;
				Object[] lObj = null;
				List sevenPCEmpDetailsList = null;
				
				if(flagForEmpActiveChk.equals("YES")){
					if(flag.equals("Yes")){
    					resStr = lStrSevarthId;
    					
    				}else{
    					
    					resStr = lStrEmpName;
    					
    				}
					
					checkEmpRevCount = pcNewDaoImpl.checkStateLevelIdForGivenEmp(resStr,flag);
					gLogger.info("checkEmpRevCount--:"+checkEmpRevCount);
					if(checkEmpRevCount > 0){
						empsize =  0;
						objectArgs.put("msg","Revision has been done for this Employee.");
						
					}else{
					if (empDetailsList != null && empDetailsList.size() > 0)
		        	{
				   IT = empDetailsList.iterator();
				   lObj = null;
				   
				 while (IT.hasNext())
				 {					
					lObj = (Object[]) IT.next();
					payScale=lObj[3].toString();
					gradePay=lObj[5].toString();
					basicPay=Long.parseLong(lObj[6].toString());
					scaleStartAmount=Long.parseLong(lObj[7].toString());
					scaleEndAmount=Long.parseLong(lObj[8].toString());
					empSevarthID=lObj[9].toString();
					doj=(Date) lObj[10];
					dob=(Date) lObj[11];
					
				}	
				 
			  empDetailsNewList= pcNewDaoImpl.getSevenPCEmpDetails(resStr,locId,flag);	
				gradeIdDetailsList1 = pcNewDaoImpl.getStateGradeIdDetailsList(gradePay);
					if (empDetailsNewList != null && empDetailsNewList.size() > 0)
	    			{
						 
	    				 IT = empDetailsNewList.iterator();
	    				 Iterator IT1 = empDetailsList.iterator();       
	    				while (IT.hasNext())
	    				{					
	    					lObj = (Object[]) IT.next();		
	    					gradePay=lObj[0].toString();
	    					basicPay=Long.parseLong(lObj[1].toString());
	    					empSevarthID=lObj[2].toString();
	    					
	    				}
	    	      
					Double newBasic = (basicPay*2.57);
				   newBasicAccToCal=newBasic.longValue();
					payInPAyBand = scaleStartAmount+"-"+scaleEndAmount;
					if(gradePay.equals("1650")){
						
						gradePay = "1700";	
						
					}else if(gradePay.equals("2900")){
						
						gradePay = "3000";	
					}else if(gradePay.equals("4100")){
						
						gradePay = "4200";	
					}else if(gradePay.equals("4500")){
						gradePay = "4600";	
						
					}else if(gradePay.equals("4900")){
						gradePay = "5000";	
						
					}else if(gradePay.equals("5700")){
						gradePay = "5800";	
						
					}
					gLogger.info("newBasic****" +newBasic+"newBasicAccToCal:"+newBasicAccToCal+"basicPay--"+basicPay+"--gradePay--"+gradePay+"--scaleStartAmount--"+scaleStartAmount+"--scaleEndAmount--"+scaleEndAmount+"--empSevarthID--"+empSevarthID);
					List gradeIdDetailsList = null;
				
					gLogger.info("**************gis in service*************" +gisAppl);
		
								gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(gradePay);

							gradeIdDetailsList1 = gradeIdDetailsList;
							if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
							{
								 IT = gradeIdDetailsList.iterator();
								while (IT.hasNext())
								{					
									lObj = (Object[]) IT.next();
									gradeId =lObj[0].toString();
									levelId= lObj[1].toString();					
								}
							
							matrixDetails = dao.getStateNewBasicAsPerMAtrix(gradeId,newBasicAccToCal);
							if (matrixDetails != null && matrixDetails.size() > 0)
							{
								//gLogger.info("**************matrixDetails*************" +matrixDetails.get(0));
								lObj = (Object[]) matrixDetails.get(0);
								newBasicPerMatrix =Long.parseLong(lObj[0].toString());
								gLogger.info("************** newBasicPerMatrix*************" +newBasicPerMatrix);
								newMatrixDetails = pcNewDaoImpl.getStateNEwBasicAsPerMAtrixForBunching (gradeId,newBasicPerMatrix);
								gradePayList =  pcNewDaoImpl.getStateGradePayList(Long.parseLong(levelId));
								gradePayDemotionList =  pcNewDaoImpl.getStateGradePayDemotionList(Long.parseLong(levelId));
							}
							
						
						if(newMatrixDetails != null && newMatrixDetails.size() > 0){
							
							gLogger.info("************** New matrixDetails*************" +	newMatrixDetails);
							
								newBasicPerScaleMatrix1 = (BigInteger)(newMatrixDetails.get(0));
								newBasicPerScaleMatrix2= (BigInteger)(newMatrixDetails.get(1));
								newBasicPerScaleMatrix3=(BigInteger)(newMatrixDetails.get(2));
								newBasicPerScaleMatrix4=(BigInteger)(newMatrixDetails.get(3));
								
							gLogger.info("*newBasicPerScaleMatrix1*" +	newBasicPerScaleMatrix1+"--newBasicPerScaleMatrix2--"+newBasicPerScaleMatrix2+"--newBasicPerScaleMatrix3---"+newBasicPerScaleMatrix3);
							
						   }
						}
						/*}*/
						
	    			  }
		          }else{
		        	 empsize =  0;
		        	 // objectArgs.put("msg","7th Pay Revision is only Applicable for only Grade Pay of 6000,7000,8000,9000 10000. ");
		        	 objectArgs.put("msg","7th Pay Revision is only Applicable for only Technical Teaching and Non-Teaching Employee as per GR."); // Tejashree
		        	}
					
					}
		}
				
				
			
				 
			sevenPCEmpDetailsList= pcNewDaoImpl.getSevenPCEmpNewDetails(empSevarthID,newBasicAccToCal,newBasicPerMatrix);
			List deptEmpDetailsList = null;
			List isolatedEmpDetailsList = null;
			//BigInteger month =null;
           // BigInteger year = null;
            int monthVal =0;
            
			gLogger.info("sevenPCEmpDetailsList--------------"+sevenPCEmpDetailsList.size()+"--chkPostAndSerExp--"+chkPostAndSerExp+"--empsize--"+empsize);
			
			if(sevenPCEmpDetailsList != null && sevenPCEmpDetailsList.size() > 0 && empsize == 1){
				objectArgs.put("sevenPCEmpDetailsList",sevenPCEmpDetailsList);	
				objectArgs.put("gradePayList",gradePayList);
				objectArgs.put("extLevelId",gradeId);
				objectArgs.put("doj",doj);
				objectArgs.put("dob",dob);
				objectArgs.put("empSevarthID",empSevarthID);
				objectArgs.put("newBasicPerScaleMatrix1",newBasicPerScaleMatrix1);
				objectArgs.put("newBasicPerScaleMatrix2",newBasicPerScaleMatrix2);
				objectArgs.put("newBasicPerScaleMatrix3",newBasicPerScaleMatrix3);
				objectArgs.put("newBasicPerScaleMatrix4",newBasicPerScaleMatrix4);
				objectArgs.put("gradePayDemotionList",gradePayDemotionList);
			   objectArgs.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
			   objectArgs.put("gradeIdDetailsList",gradeIdDetailsList1);
			   //gradeIdDetailsList
			  // matrixDetails
			   
				 
				resultObject.setViewName("revisionOf6PCTo7PCEmp");
				
				
			}else if(empsize == 0){
				
				resultObject.setViewName("revisionOf6PCTo7PCEmp");
			}
//				else if(sevenPCEmpDetailsList.size() == 0 && empsize == 1){
//				String sign = "<";
//				String dojDt = "01/01/2016";
//			
//				deptEmpDetailsList = pcNewDaoImpl.getSevenPCStateDeputationEmpDetails(resStr,locId,flag,sign,dojDt);	
//				if(deptEmpDetailsList != null && deptEmpDetailsList.size() > 0 ){
//					   IT = deptEmpDetailsList.iterator();
//					   lObj = null;
//				   
//					 while (IT.hasNext())
//					 {					
//					 lObj = (Object[]) IT.next();
//					 empSevarthID=lObj[9].toString();
//				     }
//					 isolatedEmpDetailsList = pcNewDaoImpl.getSevenPCIsoltedEmpNewDetails(empSevarthID);
//						gLogger.info("deptEmpDetailsList.size****" + deptEmpDetailsList.size());
//						if(isolatedEmpDetailsList != null && isolatedEmpDetailsList.size() > 0 ){
//							
//							objectArgs.put("deputMsg","Deputation Employee");	
//							objectArgs.put("empDetailsList",deptEmpDetailsList);
//							objectArgs.put("FlagDeput","0");
//							objectArgs.put("gradePayList",gradePayList);
//							objectArgs.put("extLevelId",gradeId);
//							objectArgs.put("empSevarthID",empSevarthID);
//							objectArgs.put("doj",doj);
//							 objectArgs.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
//							resultObject.setViewName("revisionDeputOf6PCto7PC");	
//							
//						}else{
//							
//							  this.gLogger.info("gradePay****" + gradePay);
//							    gradePayList =  pcNewDaoImpl.getStateGradePayList();	 
//							    objectArgs.put("gradePayList", gradePayList);
//					            objectArgs.put("empDetailsList", empDetailsList);
//					            objectArgs.put("extLevelId", gradeId);
//					            objectArgs.put("empSevarthID", empSevarthID);
//					            objectArgs.put("doj", doj);
//					            objectArgs.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
//					            resultObject.setViewName("revisionIsolateOf6PCTo7PCEmp");
//					 
//		        	}
//				}else {
//					deptEmpDetailsList = pcNewDaoImpl.getSevenPCStateDeputationMEmpDetails(resStr,locId,flag);	
//					if(deptEmpDetailsList != null && deptEmpDetailsList.size() > 0 ){
//						
//						String resStrDate = "";
//						String resStrDt = lObjDateFormat.format(doj);
//						 Date d3 = lObjDateFormat.parse(resStrDt);
//		                String[] resdt = resStrDt.split("/");
//						gLogger.info("resdt-----"+resdt);
//						String day = resdt[0];
//						String month = resdt[1];
//						String year = resdt[2];
//						String YearStr = "";
//						if(Integer.parseInt(year) == 2016){
//							
//							YearStr = "2017";
//							
//						}else if(Integer.parseInt(year) == 2017){
//							
//							YearStr = "2018";
//							
//						 }else if(Integer.parseInt(year) == 2018){
//							
//							 YearStr = "2019";
//							 
//						  }
//						 else if(Integer.parseInt(year) == 2019){
//								
//							 YearStr = "2020";
//							 
//						  }
//						gLogger.info("YearStr-----"+YearStr+"--Year----"+year);
//						
//						String Date1 = "02/01"+"/"+year;
//						String Date2 = "01/07"+"/"+year;
//						
//						String Date3 = "02/07"+"/"+year;
//						String Date4 = "01/01"+"/"+YearStr;
//						
//						String Date5 = "01/01"+"/"+year;
//						
//						  Date d1 = lObjDateFormat.parse(Date1);
//		                  Date d2 = lObjDateFormat.parse(Date2);
//		                  
//		                  Date d4 = lObjDateFormat.parse(Date3);
//		                  Date d5 = lObjDateFormat.parse(Date4);
//		                  
//		                  Date d6 = lObjDateFormat.parse(Date5);
//		                  
//		                
//						gLogger.info("d1-----"+lObjDateFormat.format(d1)+"d2--"+lObjDateFormat.format(d2)+"--doj-"+resStrDt+"d4--"+lObjDateFormat.format(d4)+"d5--"+lObjDateFormat.format(d5));
//						if(d3.compareTo(d1) >= 0 && d3.compareTo(d2) <= 0 ){
//							gLogger.info("in if 1-----"+d3);
//							resStrDate = "01/"+"01"+"/"+YearStr;
//							
//						}else if(d3.compareTo(d4) >= 0 && d3.compareTo(d5) <= 0 ){
//							
//							gLogger.info("in if 2-----"+d3);
//							resStrDate = "01/"+"07"+"/"+YearStr;
//							
//						}else if(d3.compareTo(d6) == 0){
//							
//							gLogger.info("in if 3-----"+d3);
//							resStrDate = "01/"+"07"+"/"+year;
//						}
//						gLogger.info("resStrDate-----"+resStrDate);
//						objectArgs.put("empDetailsList",deptEmpDetailsList);
//						objectArgs.put("FlagDeput","0");
//						objectArgs.put("gradePayList",gradePayList);
//						objectArgs.put("extLevelId",gradeId);
//						objectArgs.put("empSevarthID",empSevarthID);
//						objectArgs.put("doj",doj);
//						 objectArgs.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
//						objectArgs.put("DateStr",resStrDate);
//						objectArgs.put("resFlag","N");
//						objectArgs.put("ddoCode",ddoCode);
//						
//					resultObject.setViewName("revisionDeputMOf6PCto7PC");
//					
//					}else if(deptEmpDetailsList.size() == 0 && empDetailsNewList.size() == 0 && sevenPCEmpDetailsList.size() == 0){
//						 sign = ">=";
//						 dojDt = "01/01/2019"; 
//						 List gradeIdDetailsList = null;
//						deptEmpDetailsList = pcNewDaoImpl.getSevenPCStateDeputationEmpDetails(resStr,locId,flag,sign,dojDt);	
//						if (deptEmpDetailsList != null && deptEmpDetailsList.size() > 0)
//		    			{
//							
//		    			     IT = deptEmpDetailsList.iterator();
//		    				while (IT.hasNext())
//		    				{					
//		    					lObj = (Object[]) IT.next();		
//		    					gradePay=lObj[5].toString();
//		    					basicPay=Long.parseLong(lObj[6].toString());
//		    				
//		    				}
//		    	   
//						payInPAyBand = scaleStartAmount+"-"+scaleEndAmount;
//						if(gradePay.equals("1650")){
//							
//							gradePay = "1700";	
//							
//						}else if(gradePay.equals("2900")){
//							
//							gradePay = "3000";	
//						}else if(gradePay.equals("4100")){
//							
//							gradePay = "4200";	
//						}else if(gradePay.equals("4500")){
//							gradePay = "4600";	
//							
//						}else if(gradePay.equals("4900")){
//							gradePay = "5000";	
//							
//						}else if(gradePay.equals("5700")){
//							gradePay = "5800";	
//							
//						}
//			          if(gisAppl == 700217 || gisAppl == 700218){
//							
//							if(gradePay.equals("5000")){
//								
//								gradeIdDetailsList = dao.getStateGradeIdDetailsList(payInPAyBand,gradePay);
//								
//							}else{
//								
//								gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(gradePay);
//							}
//							 
//							if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
//							{
//								 IT = gradeIdDetailsList.iterator();
//								while (IT.hasNext())
//								{					
//									lObj = (Object[]) IT.next();
//									gradeId =lObj[0].toString();
//									levelId= lObj[1].toString();					
//								}
//						
//							matrixDetails = dao.getStateNewBasicAsPerMAtrix(gradeId,basicPay);
//							if (matrixDetails != null && matrixDetails.size() > 0)
//							{
//								//gLogger.info("**************matrixDetails*************" +matrixDetails.get(0));
//								lObj = (Object[]) matrixDetails.get(0);
//								newBasicPerMatrix =Long.parseLong(lObj[0].toString());
//								gLogger.info("************** newBasicPerMatrix*************" +newBasicPerMatrix);
//								newMatrixDetails = pcNewDaoImpl.getStateNEwBasicAsPerMAtrixForBunching (gradeId,newBasicPerMatrix);
//								gradePayList =  pcNewDaoImpl.getStateGradePayList(Long.parseLong(levelId));
//								gradePayDemotionList =  pcNewDaoImpl.getStateGradePayDemotionList(Long.parseLong(levelId));
//							}
//							
//						
//						if(newMatrixDetails != null && newMatrixDetails.size() > 0){
//							
//							gLogger.info("************** New matrixDetails*************" +	newMatrixDetails);
//							
//								newBasicPerScaleMatrix1 = (BigInteger)(newMatrixDetails.get(0));
//								newBasicPerScaleMatrix2= (BigInteger)(newMatrixDetails.get(1));
//								newBasicPerScaleMatrix3=(BigInteger)(newMatrixDetails.get(2));
//								newBasicPerScaleMatrix4=(BigInteger)(newMatrixDetails.get(3));
//							
//						   }
//						}
//						}
//		    			}
//						String resStrDt = lObjDateFormat.format(doj);
//						Date d3 = lObjDateFormat.parse(resStrDt);
//						Date dateDoj = lObjDateFormat.parse(dojDt);
//						gLogger.info("dateDoj-----"+dateDoj+"--sevenPCBasic--"+newMatrixDetails);
//                         if(d3.compareTo(dateDoj) >= 0){
//                        	 
//							gLogger.info("in if 3-----"+d3);
//							gLogger.info("resStrDate-----"+resStrDt);
//							objectArgs.put("empDetailsList",deptEmpDetailsList);
//							objectArgs.put("sevenPCBasic",newBasicPerMatrix);
//							objectArgs.put("sixPCBasic",basicPay);
//							objectArgs.put("FlagDeput","0");
//							objectArgs.put("gradePay",gradePay);
//							objectArgs.put("extLevelId",gradeId);
//							objectArgs.put("empSevarthID",empSevarthID);
//							objectArgs.put("doj",doj);
//							 objectArgs.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
//							objectArgs.put("DateStr",resStrDt);
//							objectArgs.put("resFlag","Y");
//							objectArgs.put("ddoCode",ddoCode);
//							resultObject.setViewName("revisionDeputMOf6PCto7PC");
//                         }else{
//							
//						  this.gLogger.info("gradePay****" + gradePay);
//						    gradePayList =  pcNewDaoImpl.getStateGradePayList();	 
//						    objectArgs.put("gradePayList", gradePayList);
//				            objectArgs.put("empDetailsList", empDetailsList);
//				            objectArgs.put("extLevelId", gradeId);
//				            objectArgs.put("empSevarthID", empSevarthID);
//				            objectArgs.put("doj", doj);
//				            objectArgs.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
//				            resultObject.setViewName("revisionIsolateOf6PCTo7PCEmp");
//				   	}    
//					}else{
//						resultObject.setViewName("revisionOf6PCTo7PCEmp");
//					}
//				
//			     }
//			}else if(empsize == 0){
//						
//						resultObject.setViewName("revisionOf6PCTo7PCEmp");
//					}
//		 
//			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			}	
		}
		catch(Exception e)
		{
			resultObject.setResultValue(null);
			resultObject.setThrowable(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setViewName("errorPage");	
		}
		
		return resultObject;
}
	
	//for Deputation employee
	
	public ResultObject getEmpDetailsForSevenPCDeputation(Map objectArgs) throws Exception
	{

		gLogger.info("inside the method::::::::::::getEmpDetailsForSevenPCDeputation");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			setSessionInfo(objectArgs);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			request = (HttpServletRequest) objectArgs.get("requestObj");

			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			//List bulkList=new ArrayList();
			String sevarthId ="";
			String gradePay="";
		    String gradeId = "";
			Long newBasicPerMatrix=0l;
			BigInteger newBasicPerScaleMatrix1 = null;
			BigInteger newBasicPerScaleMatrix2= null;
			BigInteger newBasicPerScaleMatrix3= null;
			BigInteger newBasicPerScaleMatrix4=null;
			List matrixDetails=null;
			List newMatrixDetails= null;
			List gradePayList = null;
			long gisAppl = 0l;
			String lStrResult="";
			String lSBStatus="";
			String sixPCBasicStr = "";
			StringBuilder lStrBldXML = new StringBuilder();
			String levelId = null;
			String chkFlag = "";
			Long scaleStartAmount =0l;
			Long scaleEndAmount = 0l;
			String payInPAyBand="";
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
			sevarthId=StringUtility.getParameter("sevarthId", request).trim();
			gradePay=StringUtility.getParameter("gradePay", request).trim();
			sixPCBasicStr=StringUtility.getParameter("sixPCBasic", request).trim();
			chkFlag=StringUtility.getParameter("chkFlag", request).trim();
			Long basicPay = Long.parseLong(sixPCBasicStr);
			int FlagDeput = 0;
				Iterator IT ;
				String resStr =  null;
				Object[] lObj = null;
				String flag = "Yes";
				List empDetailsList= pcNewDaoImpl.getSevenPCStateEmpDetails(sevarthId,locId,flag);	
				
				if (empDetailsList != null && empDetailsList.size() > 0)
	        	{
			   IT = empDetailsList.iterator();
			   lObj = null;
			   
				 while (IT.hasNext())
				 {					
					lObj = (Object[]) IT.next();
					scaleStartAmount=Long.parseLong(lObj[7].toString());
					scaleEndAmount=Long.parseLong(lObj[8].toString());
					
					
				    }
			 
	        	}
					
				    Double newBasic = (basicPay*2.57);
				   Long newBasicAccToCal=newBasic.longValue();
					payInPAyBand = scaleStartAmount+"-"+scaleEndAmount;
                   if(gradePay.equals("1650")){
						
						gradePay = "1700";	
						
					}else if(gradePay.equals("2900")){
						
						gradePay = "3000";	
					}else if(gradePay.equals("4100")){
						
						gradePay = "4200";	
					}else if(gradePay.equals("4500")){
						gradePay = "4600";	
						
					}else if(gradePay.equals("4900")){
						gradePay = "5000";	
						
					}else if(gradePay.equals("5700")){
						gradePay = "5800";	
						
					}
					gLogger.info("basicPay--"+basicPay+"--gradePay--"+gradePay+"--empSevarthID--"+sevarthId+"--newBasicAccToCal-"+newBasicAccToCal);
					
					gisAppl = dao.getEmpDetailsList(sevarthId);
					gLogger.info("**************gis in service*************" +gisAppl);
					List gradeIdDetailsList = null;
					//added by pooja 03-01-2019
					if(gisAppl == 700217 || gisAppl == 700218){
						
						if(gradePay.equals("5000")){
							
							gradeIdDetailsList = dao.getStateGradeIdDetailsList(payInPAyBand,gradePay);
							
						}else{
							
							gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(gradePay);
						}
						
						
							if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
							{
								 IT = gradeIdDetailsList.iterator();
								while (IT.hasNext())
								{					
									lObj = (Object[]) IT.next();
									gradeId =lObj[0].toString();
									levelId= lObj[1].toString();			
								}
							}
							if(Integer.parseInt(chkFlag) == 1){
								
								
								matrixDetails = dao.getStateNewBasicAsPerMAtrix(gradeId,newBasicAccToCal);
							}else{
								
								matrixDetails = dao.getStateNewBasicAsPerMAtrix(gradeId,basicPay);
							}
						
							if (matrixDetails != null && matrixDetails.size() > 0)
							{
								//gLogger.info("**************matrixDetails*************" +matrixDetails.get(0));
								lObj = (Object[]) matrixDetails.get(0);
								newBasicPerMatrix =Long.parseLong(lObj[0].toString());
								gLogger.info("************** newBasicPerMatrix*************" +newBasicPerMatrix);
								newMatrixDetails = pcNewDaoImpl.getStateNEwBasicAsPerMAtrixForBunching (gradeId,newBasicPerMatrix);
								gradePayList =  pcNewDaoImpl.getStateGradePayList(Long.parseLong(levelId));
							}
							
						}
						
						if(newMatrixDetails != null && newMatrixDetails.size() > 0){
							
							gLogger.info("************** New matrixDetails*************" +	newMatrixDetails);
							
								newBasicPerScaleMatrix1 = (BigInteger)(newMatrixDetails.get(0));
								newBasicPerScaleMatrix2= (BigInteger)(newMatrixDetails.get(1));
								newBasicPerScaleMatrix3=(BigInteger)(newMatrixDetails.get(2));
								newBasicPerScaleMatrix4=(BigInteger)(newMatrixDetails.get(3));
								
							gLogger.info("*newBasicPerScaleMatrix1*" +	newBasicPerScaleMatrix1+"--newBasicPerScaleMatrix2--"+newBasicPerScaleMatrix2+"--newBasicPerScaleMatrix3---"+newBasicPerScaleMatrix3);
							
						   }
						
				lStrBldXML.append("<XMLDOC>");
				lStrBldXML.append("<newBasicPerScaleMatrix1>");
				lStrBldXML.append(newBasicPerScaleMatrix1);
				lStrBldXML.append("</newBasicPerScaleMatrix1>");
				lStrBldXML.append("<newBasicPerScaleMatrix2>");
				lStrBldXML.append(newBasicPerScaleMatrix2);
				lStrBldXML.append("</newBasicPerScaleMatrix2>");
				lStrBldXML.append("<newBasicPerScaleMatrix3>");
				lStrBldXML.append(newBasicPerScaleMatrix3);
				lStrBldXML.append("</newBasicPerScaleMatrix3>");
				lStrBldXML.append("<newBasicPerScaleMatrix4>");
				lStrBldXML.append(newBasicPerScaleMatrix4);
				lStrBldXML.append("</newBasicPerScaleMatrix4>");
				lStrBldXML.append("<newBasicAccToCal>");
				if(Integer.parseInt(chkFlag) == 1){
				lStrBldXML.append(newBasicAccToCal);
				}else{
					lStrBldXML.append(basicPay);
				}
				lStrBldXML.append("</newBasicAccToCal>");
				lStrBldXML.append("<newBasicPerMatrix>");
				lStrBldXML.append(newBasicPerMatrix);
				lStrBldXML.append("</newBasicPerMatrix>");
				lStrBldXML.append("<FlagDeput>");
				lStrBldXML.append(1);
				lStrBldXML.append("</FlagDeput>");
				
				lStrBldXML.append("</XMLDOC>");
				lSBStatus = lStrBldXML.toString();
				lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
				gLogger.info("********************************************" + lStrResult);
				objectArgs.put("ajaxKey", lStrResult);
				gLogger.info("lStrResult---------" + lStrResult);
				resultObject.setResultValue(objectArgs);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setViewName("ajaxData");
				
				}catch (Exception e) {
					resultObject.setResultValue(null);
					resultObject.setThrowable(e);
					resultObject.setResultCode(ErrorConstants.ERROR);
					resultObject.setViewName("errorPage");
				}
		return resultObject;
}
	
	//For Isolated post employee
	/*public ResultObject getEmpDetailsForSevenPCIsolated(Map objectArgs) throws Exception
	{

		gLogger.info("inside the method::::::::::::getEmpDetailsForSevenPCIsolated");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			setSessionInfo(objectArgs);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			request = (HttpServletRequest) objectArgs.get("requestObj");

			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			//List bulkList=new ArrayList();
			String sevarthId ="";
			String gradePay="";
		    String gradeId = "";
			Long newBasicPerMatrix=0l;
			BigInteger newBasicPerScaleMatrix1 = null;
			BigInteger newBasicPerScaleMatrix2= null;
			BigInteger newBasicPerScaleMatrix3= null;
			BigInteger newBasicPerScaleMatrix4=null;
			List matrixDetails=null;
			List newMatrixDetails= null;
			List gradePayList = null;
			long gisAppl = 0l;
			String lStrResult="";
			String lSBStatus="";
			String sixPCBasicStr = "";
			StringBuilder lStrBldXML = new StringBuilder();
			String levelId = null;
			Long scaleStartAmount =0l;
			Long scaleEndAmount = 0l;
			String payInPAyBand="";
			String newGradeId = null;
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
			sevarthId=StringUtility.getParameter("sevarthId", request).trim();
			gradePay=StringUtility.getParameter("gradePay", request).trim();
			sixPCBasicStr=StringUtility.getParameter("sixPCBasic", request).trim();
			
			Long basicPay = Long.parseLong(sixPCBasicStr);
			int FlagDeput = 0;
				Iterator IT ;
				String resStr =  null;
				Object[] lObj = null;
				String flag = "Yes";
				List empDetailsList= pcNewDaoImpl.getSevenPCStateEmpDetails(sevarthId,locId,flag);	
				
				if (empDetailsList != null && empDetailsList.size() > 0)
	        	{
			   IT = empDetailsList.iterator();
			   lObj = null;
			   
				 while (IT.hasNext())
				 {					
					lObj = (Object[]) IT.next();
					scaleStartAmount=Long.parseLong(lObj[7].toString());
					scaleEndAmount=Long.parseLong(lObj[8].toString());
					
					
				    }
			 
	        	}
					
				    Double newBasic = (basicPay*2.57);
				   Long newBasicAccToCal=newBasic.longValue();
					payInPAyBand = scaleStartAmount+"-"+scaleEndAmount;
                   if(gradePay.equals("1650")){
						
						gradePay = "1700";	
						
					}else if(gradePay.equals("2900")){
						
						gradePay = "3000";	
					}else if(gradePay.equals("4100")){
						
						gradePay = "4200";	
					}else if(gradePay.equals("4500")){
						gradePay = "4600";	
						
					}else if(gradePay.equals("4900")){
						gradePay = "5000";	
						
					}else if(gradePay.equals("5700")){
						gradePay = "5800";	
						
					}
					gLogger.info("basicPay--"+basicPay+"--gradePay--"+gradePay+"--empSevarthID--"+sevarthId+"--newBasicAccToCal-"+newBasicAccToCal);
					
					gisAppl = dao.getEmpDetailsList(sevarthId);
					gLogger.info("**************gis in service*************" +gisAppl);
					List gradeIdDetailsList = null;
					//added by pooja 03-01-2019
					if(gisAppl == 700217 || gisAppl == 700218){
						
						if(gradePay.equals("5000")){
							
							gradeIdDetailsList = dao.getStateGradeIdDetailsList(payInPAyBand,gradePay);
							
						}else{
							
							gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(gradePay);
						}
						
						
							if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
							{
								 IT = gradeIdDetailsList.iterator();
								while (IT.hasNext())
								{					
									lObj = (Object[]) IT.next();
									gradeId =lObj[0].toString();
									levelId= lObj[1].toString();			
								}
							}
							String[] gradeIdstr =gradeId.split("_");
							String gradeStr = gradeIdstr[0];
							String gradeIds = gradeIdstr[1];
							gLogger.info("gradeStr---"+gradeStr+"--gradeIds---" +gradeIds);
							int id = Integer.parseInt(gradeIds)+1;
							gLogger.info("id---"+id);
							 newGradeId = gradeStr+"_"+id;		
							
							gLogger.info("newGradeId---"+newGradeId);
							
							matrixDetails = dao.getStateNewBasicAsPerMAtrix(newGradeId,newBasicAccToCal);
							
							if (matrixDetails != null && matrixDetails.size() > 0)
							{
								//gLogger.info("**************matrixDetails*************" +matrixDetails.get(0));
								lObj = (Object[]) matrixDetails.get(0);
								newBasicPerMatrix =Long.parseLong(lObj[0].toString());
								gLogger.info("************** newBasicPerMatrix*************" +newBasicPerMatrix);
								newMatrixDetails = pcNewDaoImpl.getStateNEwBasicAsPerMAtrixForBunching (newGradeId,newBasicPerMatrix);
								gradePayList =  pcNewDaoImpl.getStateGradePayList(Long.parseLong(levelId));
							}
							
						}
						
						if(newMatrixDetails != null && newMatrixDetails.size() > 0){
							
							gLogger.info("************** New matrixDetails*************" +	newMatrixDetails);
							
								newBasicPerScaleMatrix1 = (BigInteger)(newMatrixDetails.get(0));
								newBasicPerScaleMatrix2= (BigInteger)(newMatrixDetails.get(1));
								newBasicPerScaleMatrix3=(BigInteger)(newMatrixDetails.get(2));
								newBasicPerScaleMatrix4=(BigInteger)(newMatrixDetails.get(3));
								
							gLogger.info("*newBasicPerScaleMatrix1*" +	newBasicPerScaleMatrix1+"--newBasicPerScaleMatrix2--"+newBasicPerScaleMatrix2+"--newBasicPerScaleMatrix3---"+newBasicPerScaleMatrix3);
							
						   }
						
				lStrBldXML.append("<XMLDOC>");
				lStrBldXML.append("<newBasicPerScaleMatrix1>");
				lStrBldXML.append(newBasicPerScaleMatrix1);
				lStrBldXML.append("</newBasicPerScaleMatrix1>");
				lStrBldXML.append("<newBasicPerScaleMatrix2>");
				lStrBldXML.append(newBasicPerScaleMatrix2);
				lStrBldXML.append("</newBasicPerScaleMatrix2>");
				lStrBldXML.append("<newBasicPerScaleMatrix3>");
				lStrBldXML.append(newBasicPerScaleMatrix3);
				lStrBldXML.append("</newBasicPerScaleMatrix3>");
				lStrBldXML.append("<newBasicPerScaleMatrix4>");
				lStrBldXML.append(newBasicPerScaleMatrix4);
				lStrBldXML.append("</newBasicPerScaleMatrix4>");
				lStrBldXML.append("<newBasicAccToCal>");
				lStrBldXML.append(newBasicAccToCal);
				lStrBldXML.append("</newBasicAccToCal>");
				lStrBldXML.append("<newBasicPerMatrix>");
				lStrBldXML.append(newBasicPerMatrix);
				lStrBldXML.append("</newBasicPerMatrix>");
				lStrBldXML.append("<FlagDeput>");
				lStrBldXML.append(1);
				lStrBldXML.append("</FlagDeput>");
				lStrBldXML.append("<newGradeId>");
				lStrBldXML.append(newGradeId);
				lStrBldXML.append("</newGradeId>");
				lStrBldXML.append("</XMLDOC>");
				lSBStatus = lStrBldXML.toString();
				lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
				gLogger.info("********************************************" + lStrResult);
				objectArgs.put("ajaxKey", lStrResult);
				gLogger.info("lStrResult---------" + lStrResult);
				resultObject.setResultValue(objectArgs);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setViewName("ajaxData");
				
				}catch (Exception e) {
					resultObject.setResultValue(null);
					resultObject.setThrowable(e);
					resultObject.setResultCode(ErrorConstants.ERROR);
					resultObject.setViewName("errorPage");
				}
		return resultObject;
}*/
	
	public ResultObject getNewRevisedBasicDetails(Map objectArgs) throws Exception
	{
		gLogger.info("inside getRevisedBasicDetails");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String sevarthId ="";
		String scaleStrtAmnt ="";
		String scaleEndAmnt="";
		//String payInPAyBand="";
		//String gradePay="";
		//String gradeId ="";
		//String levelId="";
		Long  currBasic=0l;
		List matrixDetails=null;
		Long newBasicPerMatrix=0l;
		String cellValue="";
		String lStrResult="";
		String lSBStatus="";
		String emplevelId = "";
		String matrixBasicValue = "";
		String count = null;
		BigInteger newBasicPerScaleMatrix1 = null;
		BigInteger newBasicPerScaleMatrix2= null;
		BigInteger newBasicPerScaleMatrix3= null;
		StringBuilder lStrBldXML = new StringBuilder();
		try {
			setSessionInfo(objectArgs);	
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			sevarthId=StringUtility.getParameter("sevarthId", request).trim();
			emplevelId=StringUtility.getParameter("levelId", request).trim();
			matrixBasicValue=StringUtility.getParameter("matrixBasicValue", request).trim(); 
			count=StringUtility.getParameter("count", request).trim();
			Long lLngcount = Long.parseLong(count);
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	

			//payInPAyBand = scaleStrtAmnt+"-"+scaleEndAmnt;
			
			//Double newBasic = (currBasic*2.57);
			//Long newBasicAccToCal=newBasic.longValue();
			gLogger.info("**************matrixBasicValue*************" +matrixBasicValue+"--sevarthId--11"+sevarthId+"--lLngcount--"+lLngcount);
			
			long gisAppl = dao.getEmpDetailsList(sevarthId);
			
			gLogger.info("**************gis in service*************" +gisAppl);
				//added by pooja 03-01-2019
				if(gisAppl == 700217 || gisAppl == 700218){
					
					/*List gradeIdDetailsList = dao.getStateGradeIdDetailsList(payInPAyBand,gradePay);
					if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
					{
						Iterator IT = gradeIdDetailsList.iterator();
						Object[] lObj = null;
						while (IT.hasNext())
						{					
							lObj = (Object[]) IT.next();
							gradeId =lObj[0].toString();
								gLogger.info("**************State gradeId in service*************" +gradeId);
								levelId= lObj[1].toString();					
						}
					}*/
				
					matrixDetails = pcNewDaoImpl.getStateNEwBasicAsPerMAtrixForBunching (emplevelId,Long.parseLong(matrixBasicValue));
					
				}
				//else{
					/*List gradeIdDetailsList = dao.getgradeIdDetailsList(payInPAyBand,gradePay);
					if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
					{
						Iterator IT = gradeIdDetailsList.iterator();
						Object[] lObj = null;
						while (IT.hasNext())
						{					
							lObj = (Object[]) IT.next();
							gradeId =lObj[0].toString();
								gLogger.info("**************gradeId in service*************" +gradeId);
								levelId= lObj[1].toString();					
						}
					}*/
					//matrixDetails = dao.getNEwBasicAsPerMAtrix(emplevelId,Long.parseLong(matrixBasicValue));
					
				//}
			//}
			
		
		
				if(matrixDetails != null && matrixDetails.size() > 0){
					
					gLogger.info("**************matrixDetails*************" +	matrixDetails);
					
						newBasicPerScaleMatrix1 = (BigInteger)(matrixDetails.get(0));
						newBasicPerScaleMatrix2= (BigInteger)(matrixDetails.get(1));
						newBasicPerScaleMatrix3=(BigInteger)(matrixDetails.get(2));
					
					gLogger.info("*newBasicPerScaleMatrix1*" +	newBasicPerScaleMatrix1+"--newBasicPerScaleMatrix2--"+newBasicPerScaleMatrix2+"--newBasicPerScaleMatrix3---"+newBasicPerScaleMatrix3);
						
				   }
		List bunchingBasic=null;
		gLogger.info("**************newBasicPerMatrix*************" +newBasicPerMatrix+"cellValue"+cellValue);
		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<levelId>");
		lStrBldXML.append(emplevelId);
		lStrBldXML.append("</levelId>");
		lStrBldXML.append("<newBasicPerScaleMatrix1>");
		lStrBldXML.append(newBasicPerScaleMatrix1);
		lStrBldXML.append("</newBasicPerScaleMatrix1>");
		lStrBldXML.append("<newBasicPerScaleMatrix2>");
		lStrBldXML.append(newBasicPerScaleMatrix2);
		lStrBldXML.append("</newBasicPerScaleMatrix2>");
		lStrBldXML.append("<newBasicPerScaleMatrix3>");
		lStrBldXML.append(newBasicPerScaleMatrix3);
		lStrBldXML.append("</newBasicPerScaleMatrix3>");
		lStrBldXML.append("<count>");
		lStrBldXML.append(lLngcount);
		lStrBldXML.append("</count>");
		
		lStrBldXML.append("</XMLDOC>");
		lSBStatus = lStrBldXML.toString();
		lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		gLogger.info("********************************************" + lStrResult);
		objectArgs.put("ajaxKey", lStrResult);
		gLogger.info("lStrResult---------" + lStrResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		
		}catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
		
	}
	
	public ResultObject saveStateEMPRevisedBasicDtls(Map objectArgs) throws Exception
	{

		gLogger.info("inside the method::::::::::::loadEmpDetailsForSevenPC");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			request = (HttpServletRequest) objectArgs.get("requestObj");

			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			//List bulkList=new ArrayList();
			String lStrSevarthId ="";
			String lStrEmpName ="";			
			String txtAuthorityLetterNo="";
			Long existedNewbasicPaySvnPC=0l;
			String strlevelId = "";
			int count =0;
			String remarks = "";
			Long newBasicPerScaleMatrix6 = 0l;
			List levelList = null;
			String levelId = "";
			String gradePay = "";
			String txtAuthorityLetterDate =null;
			int viewFlag = 0;
			String viewFlagStr = null;
		
			if(StringUtility.getParameter("sevarthId", request)!=null && StringUtility.getParameter("sevarthId", request)!="")
			lStrSevarthId = StringUtility.getParameter("sevarthId", request).trim().toUpperCase();
			if(StringUtility.getParameter("txtAuthorityLetterNo", request)!=null && StringUtility.getParameter("txtAuthorityLetterNo", request)!="")
				txtAuthorityLetterNo = (StringUtility.getParameter("txtAuthorityLetterNo", request).trim()); 
			if(StringUtility.getParameter("txtAuthorityLetterDate", request)!=null && StringUtility.getParameter("txtAuthorityLetterDate", request)!="")
				txtAuthorityLetterDate = (StringUtility.getParameter("txtAuthorityLetterDate", request).trim()); 
			if(StringUtility.getParameter("level", request)!=null && StringUtility.getParameter("level", request)!="")
				strlevelId = (StringUtility.getParameter("level", request).trim()); 
			if(StringUtility.getParameter("newBasicPerScaleMatrix6", request)!=null && StringUtility.getParameter("newBasicPerScaleMatrix6", request)!="")
				newBasicPerScaleMatrix6 = Long.parseLong(StringUtility.getParameter("newBasicPerScaleMatrix6", request).trim()); 
			if(StringUtility.getParameter("remarks", request)!=null && StringUtility.getParameter("remarks", request)!="")
				remarks = StringUtility.getParameter("remarks", request).trim().toUpperCase();
			if(StringUtility.getParameter("viewFlag", request)!=null && StringUtility.getParameter("viewFlag", request)!="")
				viewFlagStr = StringUtility.getParameter("viewFlag", request).trim().toUpperCase();
			   viewFlag = Integer.parseInt(viewFlagStr);
			 Date dtEffDate =  simpleDateFormat.parse(txtAuthorityLetterDate.trim());
			 String strOrderDate = simpleDateFormat.format(dtEffDate);
				
			gLogger.info("lStrSevarthId:"+lStrSevarthId+"lStrEmpName:"+lStrEmpName+"--levelId--"+strlevelId+"--dtEffDate--"+dtEffDate+"--remarks--"+remarks+"--viewFlag--"+viewFlag);	
			
			
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	

			existedNewbasicPaySvnPC = dao.getExisintNEwBasic(lStrSevarthId);
			levelList = pcNewDaoImpl.getLevelId(strlevelId);
			 if (levelList != null && levelList.size() > 0)
  			{
  				Iterator IT = levelList.iterator();
  				Object[] lObj = null;
  				while (IT.hasNext())
  				{					
  					lObj = (Object[]) IT.next();
  					levelId =lObj[0].toString();
  					gradePay= lObj[1].toString();					
  				}
  			}
			gLogger.info("lStrSevarthId:"+lStrSevarthId+"levelId---:"+levelId+"--gradePay--"+gradePay);
			pcNewDaoImpl.insertOldSvnPCbasicDetails(lStrSevarthId,userId,existedNewbasicPaySvnPC,remarks,txtAuthorityLetterNo,strOrderDate);
			pcNewDaoImpl.updateNewBasicPay(lStrSevarthId,newBasicPerScaleMatrix6,dtEffDate,levelId,gradePay);
			objectArgs.put("sucessMsg","Revised Basic Pay Updated Successfully");
			
	         objectArgs.put("lStrSevarthId",lStrSevarthId);
	
			//objectArgs.put("size",size);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			gLogger.info("By By to saveEMPRevisedBasicDtls");
			//if(viewFlag == 1){
				
			resultObject.setViewName("revisionOf6PCTo7PCEmp");
			
			/*}else if(viewFlag == 2){
				
				resultObject.setViewName("revisionDeputOf6PCto7PC");
				
			}else if(viewFlag == 3){
				
				resultObject.setViewName("revisionOf6PCTo7PCPunishEmp");
				
           }else if(viewFlag == 4){
				
        		resultObject.setViewName("revisionOf6PCTo7PCOptPromo");
			}*/
		}
		catch(Exception e)
		{
			gLogger.error("Error is: "+ e.getMessage());	
		}
		return resultObject;

	}
	
	public ResultObject checkMatrixBasicValue(Map objectArgs) throws Exception
	{
		gLogger.info("inside getRevisedBasicDetails");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String sevarthId ="";
		String lSBStatus="";
		String emplevelId = "";
		String matrixBasicValue = "";
		String extBasicMatrixValue = "";
		String lStrResult="";
		int resStr = 0;
		
		StringBuilder lStrBldXML = new StringBuilder();
		try {
			setSessionInfo(objectArgs);	
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			sevarthId=StringUtility.getParameter("sevarthId", request).trim();
			emplevelId=StringUtility.getParameter("levelId", request).trim();
			matrixBasicValue=StringUtility.getParameter("matrixValue", request).trim(); 
			extBasicMatrixValue=StringUtility.getParameter("extBasicMatrixValue", request).trim();
			
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	

			gLogger.info("****matrixBasicValue****" +matrixBasicValue+"--sevarthId--11"+sevarthId+"--emplevelId--"+emplevelId+"--extBasicMatrixValue--"+extBasicMatrixValue);
			
			if(Long.parseLong(matrixBasicValue) == 0){
				
				resStr = 0;
			}else{
			resStr = pcNewDaoImpl.checkStateNEwBasicAsPerMAtrixValue(emplevelId,Long.parseLong(matrixBasicValue));
			}
			
			gLogger.info("****resStr****" +resStr);
			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<resStr>");
			lStrBldXML.append(resStr);
			lStrBldXML.append("</resStr>");
			lStrBldXML.append("<matrixBasicValue>");
			lStrBldXML.append(matrixBasicValue);
			lStrBldXML.append("</matrixBasicValue>");
			
			lStrBldXML.append("</XMLDOC>");
			lSBStatus = lStrBldXML.toString();
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			gLogger.info("********************************************" + lStrResult);
			objectArgs.put("ajaxKey", lStrResult);
			gLogger.info("lStrResult---------" + lStrResult);
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("ajaxData");
			
		}catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}
	
	
	//For Punishment Empoyee
	public ResultObject getPunishmentEmpDetails(Map objectArgs) throws Exception
	{

		gLogger.info("inside the method::::::::::::getPunishmentEmpDetails");

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			setSessionInfo(objectArgs);
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			request = (HttpServletRequest) objectArgs.get("requestObj");

			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			String lStrSevarthId ="";
			String lStrEmpName ="";
			List empDetailsList=null;
			List empDetailsNewList = null;
			Date dob=null;
			Date doj=null;
			String payScale="";
			String gradePay="";
			Long basicPay=0l;
			String flag="No";
			String payCommission = "";
			String gradeId = "";
			String levelId = "";
			Long newBasicPerMatrix=0l;
			BigInteger newBasicPerScaleMatrix1 = null;
			BigInteger newBasicPerScaleMatrix2= null;
			BigInteger newBasicPerScaleMatrix3= null;
			BigInteger newBasicPerScaleMatrix4=null;
			List matrixDetails=null;
			List newMatrixDetails= null;
			List gradePayList = null;
			long gisAppl = 0l;
			String empflag="Yes";
			Long scaleStartAmount=0l;
			Long scaleEndAmount=0l;
			int checkEmpRevCount= 0;
			Long newBasicAccToCal = 0l;
			String payInPAyBand="";
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
			String ddoCode = dao.getDdoCode(locId);	
			levelId=StringUtility.getParameter("levelId", request).trim();
			if(StringUtility.getParameter("sevarthId", request)!=null && StringUtility.getParameter("sevarthId", request)!=""){
			lStrSevarthId = StringUtility.getParameter("sevarthId", request).trim().toUpperCase();
			    flag="Yes";
			    empDetailsList= pcNewDaoImpl.getSevenPCStateEmpDetails(lStrSevarthId,locId,flag);	
			    gisAppl = pcNewDaoImpl.getEmpDetailsList(lStrSevarthId,flag);
			
			}
			gLogger.info("************** lStrSevarthId*************" +lStrSevarthId);
		        String empSevarthID="";
				Iterator IT ;
				String resStr =  null;
				Object[] lObj = null;
				String Id = null;
				List sevenPCEmpDetailsList = null;
			
					if(flag.equals("Yes")){
    					resStr = lStrSevarthId;
    					
    				}
					if (empDetailsList != null && empDetailsList.size() > 0)
		        	{
				   IT = empDetailsList.iterator();
				   lObj = null;
				
				  while (IT.hasNext())
				   {					
					lObj = (Object[]) IT.next();
					payScale=lObj[3].toString();
					gradePay=lObj[5].toString();
					basicPay=Long.parseLong(lObj[6].toString());
					scaleStartAmount=Long.parseLong(lObj[7].toString());
					scaleEndAmount=Long.parseLong(lObj[8].toString());
					empSevarthID=lObj[9].toString();
					doj=(Date) lObj[10];
					dob=(Date) lObj[11];
					
			    	}
				 
				  empDetailsNewList= pcNewDaoImpl.getSevenPCEmpDetails(resStr,locId,flag);	
					if (empDetailsNewList != null && empDetailsNewList.size() > 0)
	    			{
						
	    				 IT = empDetailsNewList.iterator();
	    				 Iterator IT1 = empDetailsList.iterator();
	    				while (IT.hasNext())
	    				{					
	    					lObj = (Object[]) IT.next();		
	    					gradePay=lObj[0].toString();
	    					basicPay=Long.parseLong(lObj[1].toString());
	    					empSevarthID=lObj[2].toString();
	    					
	    				}
	    	        } 
					Double newBasic = (basicPay*2.57);
				   newBasicAccToCal=newBasic.longValue();
				  payInPAyBand = scaleStartAmount+"-"+scaleEndAmount;
				   if(gradePay.equals("1650")){
						
						gradePay = "1700";	
						
					}else if(gradePay.equals("2900")){
						
						gradePay = "3000";	
					}else if(gradePay.equals("4100")){
						
						gradePay = "4200";	
					}else if(gradePay.equals("4500")){
						gradePay = "4600";	
						
					}else if(gradePay.equals("4900")){
						gradePay = "5000";	
						
					}else if(gradePay.equals("5700")){
						gradePay = "5800";	
						
					}
				   
					gLogger.info("newBasic****" +newBasic+"newBasicAccToCal:"+newBasicAccToCal+"basicPay--"+basicPay+"--gradePay--"+gradePay+"--empSevarthID--"+empSevarthID);
				
				
					List gradeIdDetailsList = null;
					
					gLogger.info("**************gis in service*************" +gisAppl);
						//added by pooja 03-01-2019
						if(gisAppl == 700217 || gisAppl == 700218){
							
							if(gradePay.equals("5000")){
								
								gradeIdDetailsList = dao.getStateGradeIdDetailsList(payInPAyBand,gradePay);
								
							}else{
								
								gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(gradePay);
							}
							if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
							{
								 IT = gradeIdDetailsList.iterator();
								while (IT.hasNext())
								{					
									lObj = (Object[]) IT.next();
									gradeId =lObj[0].toString();
									levelId= lObj[1].toString();					
								}
							}
							matrixDetails = dao.getStateNewBasicAsPerMAtrix(gradeId,newBasicAccToCal);
							if (matrixDetails != null && matrixDetails.size() > 0)
							{
								//gLogger.info("**************matrixDetails*************" +matrixDetails.get(0));
								lObj = (Object[]) matrixDetails.get(0);
								newBasicPerMatrix =Long.parseLong(lObj[0].toString());
								gLogger.info("************** newBasicPerMatrix*************" +newBasicPerMatrix);
								newMatrixDetails = pcNewDaoImpl.getStateNEwBasicAsPerMAtrixForBunching (gradeId,newBasicPerMatrix);
								gradePayList =  pcNewDaoImpl.getPunishmentStateGradePayList(Long.parseLong(levelId));
								if(gradePayList != null){
									 IT = gradePayList.iterator();
										while (IT.hasNext())
										{					
											lObj = (Object[]) IT.next();
										      Id = lObj[1].toString();					
										}
									
								}
								
							}
							
						}
						
						if(newMatrixDetails != null && newMatrixDetails.size() > 0){
							
							gLogger.info("************** New matrixDetails*************" +	newMatrixDetails);
							
								newBasicPerScaleMatrix1 = (BigInteger)(newMatrixDetails.get(0));
								newBasicPerScaleMatrix2= (BigInteger)(newMatrixDetails.get(1));
								newBasicPerScaleMatrix3=(BigInteger)(newMatrixDetails.get(2));
								newBasicPerScaleMatrix4=(BigInteger)(newMatrixDetails.get(3));
								
							gLogger.info("*newBasicPerScaleMatrix1*" +	newBasicPerScaleMatrix1+"--newBasicPerScaleMatrix2--"+newBasicPerScaleMatrix2+"--newBasicPerScaleMatrix3---"+newBasicPerScaleMatrix3);
							
						   }
					
				
		        }
					SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
					 Date lDtcurDate = SessionHelper.getCurDate();	
			sevenPCEmpDetailsList= pcNewDaoImpl.getSevenPCEmpNewDetails(empSevarthID,newBasicAccToCal,newBasicPerMatrix);
			gLogger.info("sevenPCEmpDetailsList--------------"+sevenPCEmpDetailsList.size());
			
				objectArgs.put("sevenPCEmpDetailsList",sevenPCEmpDetailsList);	
				objectArgs.put("gradePayList",Id);
				objectArgs.put("extLevelId",gradeId);
				objectArgs.put("doj",doj);
				objectArgs.put("dob",dob);
				objectArgs.put("empSevarthID",empSevarthID);
				objectArgs.put("newBasicPerScaleMatrix1",newBasicPerScaleMatrix1);
				objectArgs.put("newBasicPerScaleMatrix2",newBasicPerScaleMatrix2);
				objectArgs.put("newBasicPerScaleMatrix3",newBasicPerScaleMatrix3);
				objectArgs.put("newBasicPerScaleMatrix4",newBasicPerScaleMatrix4);
				 objectArgs.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
				 objectArgs.put("newBasicPerMatrix5", newBasicPerMatrix);
				 
				resultObject.setViewName("revisionOf6PCTo7PCPunishEmp");
		
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			
		}
		catch(Exception e)
		{
			gLogger.error("Error is: "+ e.getMessage());	
		}
		return resultObject;
}
	
	//For Punishment Empoyee After joining 01-01-2016
		public ResultObject getPunishmentEmpAfterDOJDetails(Map objectArgs) throws Exception
		{

			gLogger.info("inside the method::::::::::::getPunishmentEmpDetails");

			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

			try{
				setSessionInfo(objectArgs);
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				request = (HttpServletRequest) objectArgs.get("requestObj");

				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
				long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				String lStrSevarthId ="";
				String lStrEmpName ="";
				List empDetailsList=null;
				List empDetailsNewList = null;
				Date dob=null;
				Date doj=null;
				String payScale="";
				String gradePay="";
				String flag="No";
				String payCommission = "";
				String gradeId = "";
				String levelId = "";
				Long newBasicPerMatrix=0l;
				BigInteger newBasicPerScaleMatrix1 = null;
				BigInteger newBasicPerScaleMatrix2= null;
				BigInteger newBasicPerScaleMatrix3= null;
				BigInteger newBasicPerScaleMatrix4=null;
				List matrixDetails=null;
				List newMatrixDetails= null;
				List gradePayList = null;
				long gisAppl = 0l;
				String empflag="Yes";
				Long scaleStartAmount=0l;
				Long scaleEndAmount=0l;
				int checkEmpRevCount= 0;
				String payInPAyBand="";
				String sixPCBasicStr = "";
				StringBuilder lStrBldXML = new StringBuilder();
				String lStrResult="";
				String lSBStatus="";
				Long newBasicAccToCal = 0l;
				String chkFlag = "";
				
				LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
				EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
				String ddoCode = dao.getDdoCode(locId);	
				levelId=StringUtility.getParameter("levelId", request).trim();
				gradePay=StringUtility.getParameter("gradePay", request).trim();
				sixPCBasicStr=StringUtility.getParameter("sixPCBasic", request).trim();
				Long basicPay = Long.parseLong(sixPCBasicStr);
				chkFlag=StringUtility.getParameter("ChkFlag", request).trim();
				
				if(StringUtility.getParameter("sevarthId", request)!=null && StringUtility.getParameter("sevarthId", request)!=""){
				lStrSevarthId = StringUtility.getParameter("sevarthId", request).trim().toUpperCase();
				    flag="Yes";
				    empDetailsList= pcNewDaoImpl.getSevenPCStateEmpDetails(lStrSevarthId,locId,flag);	
				    gisAppl = pcNewDaoImpl.getEmpDetailsList(lStrSevarthId,flag);
				
				}
				gLogger.info("************** lStrSevarthId*************" +lStrSevarthId);
			        String empSevarthID="";
					Iterator IT ;
					String resStr =  null;
					Object[] lObj = null;
					String Id = null;
					List sevenPCEmpDetailsList = null;
				
						if(flag.equals("Yes")){
	    					resStr = lStrSevarthId;
	    					
	    				}
						if (empDetailsList != null && empDetailsList.size() > 0)
			        	{
					   IT = empDetailsList.iterator();
					   lObj = null;
					
					  while (IT.hasNext())
					   {					
						lObj = (Object[]) IT.next();
						//payScale=lObj[3].toString();
						//gradePay=lObj[5].toString();
						//basicPay=Long.parseLong(lObj[6].toString());
						scaleStartAmount=Long.parseLong(lObj[7].toString());
						scaleEndAmount=Long.parseLong(lObj[8].toString());
						empSevarthID=lObj[9].toString();
						doj=(Date) lObj[10];
						dob=(Date) lObj[11];
						
				    	}
					 
						Double newBasic = (basicPay*2.57);
					   newBasicAccToCal=newBasic.longValue();
					  payInPAyBand = scaleStartAmount+"-"+scaleEndAmount;
					   if(gradePay.equals("1650")){
							
							gradePay = "1700";	
							
						}else if(gradePay.equals("2900")){
							
							gradePay = "3000";	
						}else if(gradePay.equals("4100")){
							
							gradePay = "4200";	
						}else if(gradePay.equals("4500")){
							gradePay = "4600";	
							
						}else if(gradePay.equals("4900")){
							gradePay = "5000";	
							
						}else if(gradePay.equals("5700")){
							gradePay = "5800";	
							
						}
					   
						gLogger.info("basicPay--"+basicPay+"--gradePay--"+gradePay+"--empSevarthID--"+empSevarthID);
					
					
						List gradeIdDetailsList = null;
						
						gLogger.info("**************gis in service*************" +gisAppl);
							//added by pooja 03-01-2019
							if(gisAppl == 700217 || gisAppl == 700218){
								
								if(gradePay.equals("5000")){
									
									gradeIdDetailsList = dao.getStateGradeIdDetailsList(payInPAyBand,gradePay);
									
								}else{
									
									gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(gradePay);
								}
								if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
								{
									 IT = gradeIdDetailsList.iterator();
									while (IT.hasNext())
									{					
										lObj = (Object[]) IT.next();
										gradeId =lObj[0].toString();
										levelId= lObj[1].toString();					
									}
								}
								if(Integer.parseInt(chkFlag) == 1){
									
									
									matrixDetails = dao.getStateNewBasicAsPerMAtrix(gradeId,newBasicAccToCal);
								}else{
									
									matrixDetails = dao.getStateNewBasicAsPerMAtrix(gradeId,basicPay);
								}
							
							
								if (matrixDetails != null && matrixDetails.size() > 0)
								{
									//gLogger.info("**************matrixDetails*************" +matrixDetails.get(0));
									lObj = (Object[]) matrixDetails.get(0);
									newBasicPerMatrix =Long.parseLong(lObj[0].toString());
									gLogger.info("************** newBasicPerMatrix*************" +newBasicPerMatrix);
									newMatrixDetails = pcNewDaoImpl.getStateNEwBasicAsPerMAtrixForBunching (gradeId,newBasicPerMatrix);
									gradePayList =  pcNewDaoImpl.getPunishmentStateGradePayList(Long.parseLong(levelId));
									if(gradePayList != null){
										 IT = gradePayList.iterator();
											while (IT.hasNext())
											{					
												lObj = (Object[]) IT.next();
											      Id = lObj[1].toString();					
											}
										
									}
									
								}
								
							}
							
							if(newMatrixDetails != null && newMatrixDetails.size() > 0){
								
								gLogger.info("************** New matrixDetails*************" +	newMatrixDetails);
								
									newBasicPerScaleMatrix1 = (BigInteger)(newMatrixDetails.get(0));
									newBasicPerScaleMatrix2= (BigInteger)(newMatrixDetails.get(1));
									newBasicPerScaleMatrix3=(BigInteger)(newMatrixDetails.get(2));
									newBasicPerScaleMatrix4=(BigInteger)(newMatrixDetails.get(3));
									
								gLogger.info("*newBasicPerScaleMatrix1*" +	newBasicPerScaleMatrix1+"--newBasicPerScaleMatrix2--"+newBasicPerScaleMatrix2+"--newBasicPerScaleMatrix3---"+newBasicPerScaleMatrix3);
								
							   }
						
					
			        }
						SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
						 Date lDtcurDate = SessionHelper.getCurDate();	
				
						 
					    	 lStrBldXML.append("<XMLDOC>");
							lStrBldXML.append("<newBasicPerScaleMatrix1>");
							lStrBldXML.append(newBasicPerScaleMatrix1);
							lStrBldXML.append("</newBasicPerScaleMatrix1>");
							lStrBldXML.append("<newBasicPerScaleMatrix2>");
							lStrBldXML.append(newBasicPerScaleMatrix2);
							lStrBldXML.append("</newBasicPerScaleMatrix2>");
							lStrBldXML.append("<newBasicPerScaleMatrix3>");
							lStrBldXML.append(newBasicPerScaleMatrix3);
							lStrBldXML.append("</newBasicPerScaleMatrix3>");
							lStrBldXML.append("<newBasicPerScaleMatrix4>");
							lStrBldXML.append(newBasicPerScaleMatrix4);
							lStrBldXML.append("</newBasicPerScaleMatrix4>");
							lStrBldXML.append("<newBasicAccToCal>");
							if(Integer.parseInt(chkFlag) == 1){
								lStrBldXML.append(newBasicAccToCal);
								}else{
								lStrBldXML.append(basicPay);
								}
							lStrBldXML.append("</newBasicAccToCal>");
							lStrBldXML.append("<newBasicPerMatrix5>");
							lStrBldXML.append(newBasicPerMatrix);
							lStrBldXML.append("</newBasicPerMatrix5>");
							lStrBldXML.append("<FlagDeput>");
							lStrBldXML.append(1);
							lStrBldXML.append("</FlagDeput>");
							lStrBldXML.append("<gradePayList>");
							lStrBldXML.append(Id);
							lStrBldXML.append("</gradePayList>");
							
							lStrBldXML.append("</XMLDOC>");
							lSBStatus = lStrBldXML.toString();
							lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
							gLogger.info("********************************************" + lStrResult);
							objectArgs.put("ajaxKey", lStrResult);
							gLogger.info("lStrResult---------" + lStrResult);
							resultObject.setResultValue(objectArgs);
							resultObject.setResultCode(ErrorConstants.SUCCESS);
							resultObject.setViewName("ajaxData");
				
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				
			}catch (Exception e) {
				resultObject.setResultValue(null);
				resultObject.setThrowable(e);
				resultObject.setResultCode(ErrorConstants.ERROR);
				resultObject.setViewName("errorPage");
			}
	return resultObject;
	}
		
		//Added by pooja For Viklp Employees - 13-03-2019
	
		public ResultObject getViklpEmpDetails(Map objectArgs) throws Exception
		{

			gLogger.info("inside the method::::::::::::getViklpEmpDetails");

			ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

			try{
				setSessionInfo(objectArgs);
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				request = (HttpServletRequest) objectArgs.get("requestObj");

				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
				long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				String lStrSevarthId ="";
				String lStrEmpName ="";
				List empDetailsList=null;
				List empDetailsNewList = null;
				Date dob=null;
				Date doj=null;
				String payScale="";
				String gradePay="";
				Long basicPay=0l;
				String flag="No";
				String payCommission = "";
				String gradeId = "";
				String levelId = "";
				String levelIdStr = "";
				Long newBasicPerMatrix=0l;
				BigInteger newBasicPerScaleMatrix1 = null;
				BigInteger newBasicPerScaleMatrix2= null;
				BigInteger newBasicPerScaleMatrix3= null;
				BigInteger newBasicPerScaleMatrix4=null;
				List matrixDetails=null;
				List newMatrixDetails= null;
				List gradePayList = null;
				long gisAppl = 0l;
				String empflag="Yes";
				Long scaleStartAmount=0l;
				Long scaleEndAmount=0l;
				int checkEmpRevCount= 0;
				Long newBasicAccToCal = 0l;
				String payInPAyBand="";
				String DateStr = "";
				List gradePayDemotionList = null;
				 String resStrDt = "";
				 String month = "";
				 String year = "";
				LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
				EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
				String ddoCode = dao.getDdoCode(locId);	
				SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				levelIdStr=StringUtility.getParameter("levelId", request).trim();
				DateStr=StringUtility.getParameter("DateStr", request).trim();
				if(StringUtility.getParameter("sevarthId", request)!=null && StringUtility.getParameter("sevarthId", request)!=""){
				lStrSevarthId = StringUtility.getParameter("sevarthId", request).trim().toUpperCase();
				    flag="Yes";
				    empDetailsList= pcNewDaoImpl.getSevenPCStateEmpDetails(lStrSevarthId,locId,flag);	
				    gisAppl = pcNewDaoImpl.getEmpDetailsList(lStrSevarthId,flag);
				
				}
				gLogger.info("************** lStrSevarthId*************" +lStrSevarthId);
			        String empSevarthID="";
					Iterator IT ;
					String resStr =  null;
					Object[] lObj = null;
					String Id = null;
					List sevenPCEmpDetailsList = null;
				
						if(flag.equals("Yes")){
	    					resStr = lStrSevarthId;
	    					
	    				}
						if (empDetailsList != null && empDetailsList.size() > 0)
			        	{
					   IT = empDetailsList.iterator();
					   lObj = null;
					
					  while (IT.hasNext())
					   {					
						lObj = (Object[]) IT.next();
						payScale=lObj[3].toString();
						gradePay=lObj[5].toString();
						basicPay=Long.parseLong(lObj[6].toString());
						scaleStartAmount=Long.parseLong(lObj[7].toString());
						scaleEndAmount=Long.parseLong(lObj[8].toString());
						empSevarthID=lObj[9].toString();
						doj=(Date) lObj[10];
						dob=(Date) lObj[11];
						
				    	}
					    Date dt = lObjDateFormat.parse(DateStr);
					    gLogger.info("dt-----"+dt);
					     resStrDt = lObjDateFormat.format(dt);
					    gLogger.info("resStrDt-----"+resStrDt);
						String[] resdt = resStrDt.split("/");
						gLogger.info("resdt-----"+resdt);
						String day = resdt[0];
						 month = resdt[1];
						 year = resdt[2];
						
						gLogger.info("day-----"+day+"--month--"+month+"---year--"+year);
						
					  empDetailsNewList= pcNewDaoImpl.getSevenPCViklpEmpDetails(resStr,locId,flag,month,year);	
						if (empDetailsNewList != null && empDetailsNewList.size() > 0)
		    			{
							
		    				 IT = empDetailsNewList.iterator();
		    				 Iterator IT1 = empDetailsList.iterator();
		    				while (IT.hasNext())
		    				{					
		    					lObj = (Object[]) IT.next();		
		    					gradePay=lObj[0].toString();
		    					basicPay=Long.parseLong(lObj[1].toString());
		    					empSevarthID=lObj[2].toString();
		    					
		    				}
		    	        } 
						Double newBasic = (basicPay*2.57);
					   newBasicAccToCal=newBasic.longValue();
					  payInPAyBand = scaleStartAmount+"-"+scaleEndAmount;
					   if(gradePay.equals("1650")){
							
							gradePay = "1700";	
							
						}else if(gradePay.equals("2900")){
							
							gradePay = "3000";	
						}else if(gradePay.equals("4100")){
							
							gradePay = "4200";	
						}else if(gradePay.equals("4500")){
							gradePay = "4600";	
							
						}else if(gradePay.equals("4900")){
							gradePay = "5000";	
							
						}else if(gradePay.equals("5700")){
							gradePay = "5800";	
							
						}
					   
						gLogger.info("newBasic****" +newBasic+"newBasicAccToCal:"+newBasicAccToCal+"basicPay--"+basicPay+"--gradePay--"+gradePay+"--empSevarthID--"+empSevarthID);
					
					
						List gradeIdDetailsList = null;
						
						gLogger.info("**************gis in service*************" +gisAppl);
							//added by pooja 03-01-2019
							if(gisAppl == 700217 || gisAppl == 700218){
								
								if(gradePay.equals("5000")){
									
									gradeIdDetailsList = dao.getStateGradeIdDetailsList(payInPAyBand,gradePay);
									
								}else{
									
									gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(gradePay);
								}
								if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
								{
									 IT = gradeIdDetailsList.iterator();
									while (IT.hasNext())
									{					
										lObj = (Object[]) IT.next();
										gradeId =lObj[0].toString();
										levelId= lObj[1].toString();					
									}
								}
								matrixDetails = dao.getStateNewBasicAsPerMAtrix(gradeId,newBasicAccToCal);
								if (matrixDetails != null && matrixDetails.size() > 0)
								{
									gLogger.info("**************levelId*************" +levelId);
									lObj = (Object[]) matrixDetails.get(0);
									newBasicPerMatrix =Long.parseLong(lObj[0].toString());
									gLogger.info("************** newBasicPerMatrix*************" +newBasicPerMatrix);
									newMatrixDetails = pcNewDaoImpl.getStateNEwBasicAsPerMAtrixForBunching (gradeId,newBasicPerMatrix);
									gradePayList =  pcNewDaoImpl.getStateGradePayList(Long.parseLong(levelId));
									gradePayDemotionList =  pcNewDaoImpl.getStateGradePayDemotionList(Long.parseLong(levelId));
								
								}
								
							}
							
							if(newMatrixDetails != null && newMatrixDetails.size() > 0){
								
								gLogger.info("************** New matrixDetails*************" +	newMatrixDetails);
								
									newBasicPerScaleMatrix1 = (BigInteger)(newMatrixDetails.get(0));
									newBasicPerScaleMatrix2= (BigInteger)(newMatrixDetails.get(1));
									newBasicPerScaleMatrix3=(BigInteger)(newMatrixDetails.get(2));
									newBasicPerScaleMatrix4=(BigInteger)(newMatrixDetails.get(3));
									
								gLogger.info("*newBasicPerScaleMatrix1*" +	newBasicPerScaleMatrix1+"--newBasicPerScaleMatrix2--"+newBasicPerScaleMatrix2+"--newBasicPerScaleMatrix3---"+newBasicPerScaleMatrix3);
								
							   }
						
					
			        }
						
						 Date lDtcurDate = SessionHelper.getCurDate();	
				sevenPCEmpDetailsList= pcNewDaoImpl.getSevenPCViklpEmpNewDetails(empSevarthID,newBasicAccToCal,newBasicPerMatrix,month,year);
				gLogger.info("sevenPCEmpDetailsList--------------"+sevenPCEmpDetailsList.size()+"--empDetailsList--"+empDetailsList.size());
				
					if(sevenPCEmpDetailsList != null && sevenPCEmpDetailsList.size() > 0){
						
						objectArgs.put("sevenPCEmpDetailsList",sevenPCEmpDetailsList);
						objectArgs.put("newBasicPerScaleMatrix1",newBasicPerScaleMatrix1);
						objectArgs.put("newBasicPerScaleMatrix2",newBasicPerScaleMatrix2);
						objectArgs.put("newBasicPerScaleMatrix3",newBasicPerScaleMatrix3);
						objectArgs.put("newBasicPerScaleMatrix4",newBasicPerScaleMatrix4);
						objectArgs.put("newBasicPerMatrix5", newBasicPerMatrix);
						objectArgs.put("empFlag",1); 
					}else{
						objectArgs.put("sevenPCEmpDetailsList",empDetailsList); 
						objectArgs.put("empFlag",0); 
						
					}
						
					objectArgs.put("gradePayList",gradePayList);
					objectArgs.put("gradePayDemotionList",gradePayDemotionList);
					objectArgs.put("extLevelId",gradeId);
					objectArgs.put("doj",doj);
					objectArgs.put("dob",dob);
					objectArgs.put("ddoCode",ddoCode);
					objectArgs.put("empSevarthID",empSevarthID);
					 objectArgs.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));
					 objectArgs.put("DateStr",resStrDt);
					 
					resultObject.setViewName("revisionOf6PCTo7PCViklpEmp");
			
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				
			}
			catch(Exception e)
			{
				gLogger.error("Error is: "+ e.getMessage());	
			}
			return resultObject;
	}
		
		
		
	public ResultObject displayLevel(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrGradePay = null;
		Integer lNoOfLevels = 0;
		Iterator IT ;
		String resStr =  null;
		Object[] lObj = null;
		String levelId = null;
		List gradePayList  = null;
		String gradeId = null;
		String type = null;
		try {

			setSessionInfo(inputMap);
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
			lStrGradePay = StringUtility.getParameter("gradePay", request);
			type = StringUtility.getParameter("type", request);
			 if(lStrGradePay.equals("1650")){
					
				 lStrGradePay = "1700";	
					
				}else if(lStrGradePay.equals("2900")){
					
					lStrGradePay = "3000";	
				}else if(lStrGradePay.equals("4100")){
					
					lStrGradePay = "4200";	
				}else if(lStrGradePay.equals("4500")){
					lStrGradePay = "4600";	
					
				}else if(lStrGradePay.equals("4900")){
					lStrGradePay = "5000";	
					
				}else if(lStrGradePay.equals("5700")){
					lStrGradePay = "5800";	
					
				}
			List gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(lStrGradePay);
			if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
			{
				 IT = gradeIdDetailsList.iterator();
				while (IT.hasNext())
				{					
					lObj = (Object[]) IT.next();
					gradeId= lObj[0].toString();
					levelId= lObj[1].toString();					
				}
			}
		
			 if(type.equals("Regular")){
				 
			 gradePayList =  pcNewDaoImpl.getStateGradePayList(Long.parseLong(levelId));
			 
			 }else if(type.equals("Demotion")){
				 
			 gradePayList =  pcNewDaoImpl.getStateGradePayDemotionList(Long.parseLong(levelId));	 
			
		    }
		    else if(type.equals("Isolate")){
			 
			 gradePayList =  pcNewDaoImpl.getStateGradePayList();	 
			 }
			lNoOfLevels = gradePayList.size();

			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

		String lSBStatus = getResponseXMLDocToDisplayLevels(lNoOfLevels, gradePayList,gradeId).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}
	/*//FOr Isolated Post employee - 16-03-2019
	public ResultObject displayIsolatedPostLevel(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrGradePay = null;
		Integer lNoOfLevels = 0;
		Iterator IT ;
		String resStr =  null;
		Object[] lObj = null;
		String levelId = null;
		List gradePayList  = null;
		String gradeId = null;
		String type = null;
		try {

			setSessionInfo(inputMap);
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
			lStrGradePay = StringUtility.getParameter("gradePay", request);
			type = StringUtility.getParameter("type", request);
			 if(lStrGradePay.equals("1650")){
					
				 lStrGradePay = "1700";	
					
				}else if(lStrGradePay.equals("2900")){
					
					lStrGradePay = "3000";	
				}else if(lStrGradePay.equals("4100")){
					
					lStrGradePay = "4200";	
				}else if(lStrGradePay.equals("4500")){
					lStrGradePay = "4600";	
					
				}else if(lStrGradePay.equals("4900")){
					lStrGradePay = "5000";	
					
				}else if(lStrGradePay.equals("5700")){
					lStrGradePay = "5800";	
					
				}
			List gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(lStrGradePay);
			if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
			{
				 IT = gradeIdDetailsList.iterator();
				while (IT.hasNext())
				{					
					lObj = (Object[]) IT.next();
					gradeId= lObj[0].toString();
					levelId= lObj[1].toString();					
				}
			}
		
			 if(type.equals("Regular")){
				 
			 gradePayList =  pcNewDaoImpl.getIsolatedPostStateGradePayList(Long.parseLong(levelId));
			 
			 }else if(type.equals("Demotion")){
				 
			 gradePayList =  pcNewDaoImpl.getIsolatedPostStateGradePayDemotionList(Long.parseLong(levelId));	 
			
		    }
			lNoOfLevels = gradePayList.size();

			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

		String lSBStatus = getResponseXMLDocToDisplayLevels(lNoOfLevels, gradePayList,gradeId).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}*/
	
	private StringBuilder getResponseXMLDocToDisplayLevels(Integer lNoOfLevels, List gradePayList,String gradeId) {

		StringBuilder lStrBldXML = new StringBuilder();
		Object[] lObjLevels = null;
		lStrBldXML.append("<XMLDOC>");

		lStrBldXML.append("<lNoOfLevels>");
		lStrBldXML.append(lNoOfLevels);
		lStrBldXML.append("</lNoOfLevels>");
		
		
		for (int lInt = 0; lInt < gradePayList.size(); lInt++) {
			lObjLevels = (Object[]) gradePayList.get(lInt);
			lStrBldXML.append("<Level" + lInt + ">");
			lStrBldXML.append("<![CDATA[");
			lStrBldXML.append(lObjLevels[1].toString().trim());
			lStrBldXML.append("]]>");

			lStrBldXML.append("</Level" + lInt + ">");
			lStrBldXML.append("<levelId" + lInt + ">");
			lStrBldXML.append(lObjLevels[0].toString().trim());
			lStrBldXML.append("</levelId" + lInt + ">");
			
		}

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
	
	public ResultObject getExistLevel(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrGradePay = null;
		Iterator IT ;
		String resStr =  null;
		Object[] lObj = null;
		String levelId = null;
		String gradeId = null;
		try {

			setSessionInfo(inputMap);
			LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class,serv.getSessionFactory());		
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
			lStrGradePay = StringUtility.getParameter("gradePay", request);
			List gradeIdDetailsList = pcNewDaoImpl.getStateGradeIdDetailsList(lStrGradePay);
			if (gradeIdDetailsList != null && gradeIdDetailsList.size() > 0)
			{
				 IT = gradeIdDetailsList.iterator();
				while (IT.hasNext())
				{					
					lObj = (Object[]) IT.next();
					gradeId= lObj[0].toString();
					levelId= lObj[1].toString();					
				}
			}
		
			
			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error is " + e, e);
		}

	
		StringBuilder lStrBldXML = new StringBuilder();
		Object[] lObjLevels = null;
		lStrBldXML.append("<XMLDOC>");

		lStrBldXML.append("<gradeId>");
		lStrBldXML.append(gradeId);
		lStrBldXML.append("</gradeId>");
		lStrBldXML.append("</XMLDOC>");
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}
	
	//Added by pooja After joining 01/01/2019 Option/Promotion
	
	 public ResultObject getBunchingStateMatrixDetails(Map objectArgs)
	    {
	        gLogger.info("Entering into loadData of getBunchingStateMatrixDetails");
	         ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	        // ResultObject objRes = new ResultObject(ErrorConstants.ERROR);

	        try
	        {

	        	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				request = (HttpServletRequest) objectArgs.get("requestObj");

				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString()); 
				long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());            
	             
	     		String levelId="";
	     		Long  currBasic=0l;
	     		List matrixDetails=null;
	     		 String lStrTempResult = "";
	     		 String hidSevarthId ="";
	     		 Long cmbPayCommission=0l;
	     		 List empDetailsList=null;
	     		 String flag="Yes";
	     		String cadre="";
				String group="";
				String payScale="";
				String gradePay="";
				Long basicPay=0l;
				String empSevarthID="";
				String payCommission = "";
				
				Integer lNoOfmatrix  = 0;
				String gradeId ="";
				Long newBasicAccToCal=0l;
				EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
	     		//added for change paypost details screen
	     	
	     		if(StringUtility.getParameter("level", request)!=null && StringUtility.getParameter("level", request)!=""){
	         		gradeId = StringUtility.getParameter("level", request);
	         		}
	     		if(StringUtility.getParameter("newBasicAsPerMatrix", request)!=null && StringUtility.getParameter("newBasicAsPerMatrix", request)!=""){
	         		newBasicAccToCal = Long.parseLong(StringUtility.getParameter("newBasicAsPerMatrix", request));
	              
	     		}
	     		  gLogger.info("gradeId---"+gradeId+"--newBasicAccToCal--"+newBasicAccToCal);
	     	    matrixDetails = pcNewDaoImpl.getNEwBasicAsPerMAtrixForBunching(gradeId,newBasicAccToCal);                
	     	    lNoOfmatrix = matrixDetails.size();
	     	   gLogger.info("lNoOfmatrix---"+lNoOfmatrix);
	            if (matrixDetails != null)
	            {
	             //   lStrTempResult = new AjaxXmlBuilder().addItems(matrixDetails, "desc", "id").toString();
	            	String lSBStatus = getResponseXMLDocToDisplayMatrix(lNoOfmatrix, matrixDetails).toString();
	        		lStrTempResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

	        		
	            }
	            objectArgs.put("ajaxKey", lStrTempResult);
	            objRes.setResultValue(objectArgs);
	            objRes.setViewName("ajaxData");
	        } catch (Exception e)
	        {
	            objRes.setResultValue(null);
	            objRes.setThrowable(e);
	            objRes.setResultCode(ErrorConstants.ERROR);
	            objRes.setViewName("errorPage");
	            gLogger.error(" Error is : " + e, e);
	        }
	        return objRes;
	    }
		private StringBuilder getResponseXMLDocToDisplayMatrix(Integer lNoOfmatrix, List matrixDetails) {

			StringBuilder lStrBldXML = new StringBuilder();
			Object[] lObjLevels = null;
			lStrBldXML.append("<XMLDOC>");

			lStrBldXML.append("<lNoOfLevels>");
			lStrBldXML.append(lNoOfmatrix);
			lStrBldXML.append("</lNoOfLevels>");
			
			
			for (int lInt = 0; lInt < matrixDetails.size(); lInt++) {
				lObjLevels = (Object[]) matrixDetails.get(lInt);
				lStrBldXML.append("<Level" + lInt + ">");
				lStrBldXML.append("<![CDATA[");
				lStrBldXML.append(lObjLevels[1].toString().trim());
				lStrBldXML.append("]]>");

				lStrBldXML.append("</Level" + lInt + ">");
				lStrBldXML.append("<levelId" + lInt + ">");
				lStrBldXML.append(lObjLevels[0].toString().trim());
				lStrBldXML.append("</levelId" + lInt + ">");
				
			}

			lStrBldXML.append("</XMLDOC>");

			return lStrBldXML;
		}
		
		public ResultObject getGradePay(Map inputMap) {

			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
			String lStrGradePay = null;
			Iterator IT ;
			String resStr =  null;
			Object[] lObj = null;
			String levelId = null;
			 String gradePay = null;
			try {

				setSessionInfo(inputMap);
				EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());	
				String level = StringUtility.getParameter("level", request);
 
				  gradePay = pcNewDaoImpl.getGradePay(level);
				  gLogger.info("gradePay---"+gradePay);
				 
					resObj.setResultValue(inputMap);
				} catch (Exception e) {
					e.printStackTrace();
					resObj.setResultValue(null);
					resObj.setThrowable(e);
					resObj.setResultCode(ErrorConstants.ERROR);
					resObj.setViewName("errorPage");
					gLogger.error(" Error is " + e, e);
				}

					StringBuilder lStrBldXML = new StringBuilder();
					Object[] lObjLevels = null;
					lStrBldXML.append("<XMLDOC>");

					lStrBldXML.append("<gradePay>");
					lStrBldXML.append(gradePay);
					lStrBldXML.append("</gradePay>");
					lStrBldXML.append("</XMLDOC>");
					String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();

					inputMap.put("ajaxKey", lStrResult);
					resObj.setResultValue(inputMap);
					resObj.setViewName("ajaxData");
					return resObj;
		
		}
}
