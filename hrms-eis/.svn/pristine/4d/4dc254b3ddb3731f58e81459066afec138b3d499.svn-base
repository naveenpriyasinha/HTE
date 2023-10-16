package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ArrearBillDaoImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PaybillRegMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.onlinebillprep.service.OnlineBillServiceImpl;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;

public class DeletePaybillServiceImpl  extends ServiceImpl {
	
	
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	//Added By Mrugesh
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	long arrearBillTypeId = Long.parseLong(resourceBundle.getString("arrearbillTypeId"));
	long payBillTypeId = Long.parseLong(resourceBundle.getString("paybillTypeId"));
	//Ended By Mrugesh
	public ResultObject deletePaybill(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			    
	            logger.info("**************coming into deletePaybill of DeletePaybillServiceImpl****************" );
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
				
				long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				String TokenFlag = objectArgs.get("TokenFlag") != null && !(objectArgs.get("TokenFlag").equals(""))? objectArgs.get("TokenFlag").toString():"";
	            String month= objectArgs.get("month") != null && !objectArgs.get("month").equals("")? objectArgs.get("month").toString():"";
				String year= objectArgs.get("year") != null && !objectArgs.get("year").equals("")? objectArgs.get("year").toString():"";
				String billNo= objectArgs.get("billNo") != null && !objectArgs.get("billNo").equals("")? objectArgs.get("billNo").toString():"";
				
				resultObject.setSessionValues(month,"month");
				resultObject.setSessionValues(year,"year");
				resultObject.setSessionValues(billNo,"billNo");
				resultObject.setSessionValues(TokenFlag,"TokenFlag");
				long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
				logger.info(":::::::::::::::::::>>>>>>>>>>>>>> in deletePaybill month year and bill number : " + month + " " + year + " " + billNo);
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
				
				long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
				
				Integer outerBill  = (Integer)objectArgs.get("outer");
				String trnBillIds=null;
				logger.info("the outer is "+outerBill);
				
				String billType = "paybill";
				
				if(outerBill==null)
				{
					logger.info("not from outer");
					Map voToService = (Map)objectArgs.get("voToServiceMap");
					trnBillIds = (String) voToService.get("chkSend");
				}
				else
				{
					logger.info("from outer");
					Long trnBillRegisterId = (Long)objectArgs.get("trnBillIds");
					
					PaybillRegMpgDAOImpl paybillRegMpgDao = new PaybillRegMpgDAOImpl(PaybillBillregMpg.class,serv.getSessionFactory());
					
					List paybillRegMpgList = paybillRegMpgDao.getListByColumnAndValue("trnBillRegister.billNo",trnBillRegisterId.longValue());
					
					logger.info("paybillRegMpgList "+paybillRegMpgList);
					
					
					
					if(paybillRegMpgList!=null && paybillRegMpgList.size()>0)
					{
						
						logger.info("paybillRegMpgList.size() "+paybillRegMpgList.size());
						
						for(int regMpgCnt=0;regMpgCnt<paybillRegMpgList.size();regMpgCnt++)
						{
							PaybillBillregMpg paybillRegMpg = (PaybillBillregMpg)paybillRegMpgList.get(0);
							
							logger.info("paybillRegMpg "+paybillRegMpg);
							
							long paybillId = 0;
							
							if(paybillRegMpg.getHrPayPaybill()!=0 && paybillRegMpg.getBillTypeId().getLookupId() == arrearBillTypeId)
							{
								billType = "arrears";
								paybillId =paybillRegMpg.getHrPayPaybill();
								
							}
							else
							{
								billType = "paybill";
								paybillId =  paybillRegMpg.getHrPayPaybill();
							}
						
							
							logger.info("paybillId "+paybillId+" and billType "+billType);
							
							trnBillIds=new String();
							trnBillIds=String.valueOf(paybillId);
							
						}
					}
				}
				logger.info("the trnBillId "+trnBillIds+" the size is "+trnBillIds);
				
				Date approveRejectDate = new Date();
				
				ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");
				 
				 String logicalDelete = constantsBundle.getString("logicalDelete");
				
				long delete = Long.parseLong(logicalDelete!=null?logicalDelete:"3");
				//Added By Mrugesh
				PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
				//Ended By Mrugesh
				//for(int i=0;i<trnBillIds.length;i++)
				{   
					String trnBillId = trnBillIds;
					logger.info("the trnBillId "+trnBillId);
					
					long trnBillNo = (trnBillId!=null?Long.parseLong(trnBillId):0);
					
					PayBillDAOImpl paybillDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
					ArrearBillDaoImpl arrearBillDao = new ArrearBillDaoImpl(HrPayArrearPaybill.class,serv.getSessionFactory());
					
					if(billType.equalsIgnoreCase("arrears"))
					{
						List arrearbillToBeDelete = arrearBillDao.getListByColumnAndValue("paybillGrpId",trnBillNo);
						 
						 if(arrearbillToBeDelete!=null && arrearbillToBeDelete.size()>0)
							{
								for(int cnt=0;cnt<arrearbillToBeDelete.size();cnt++)
								{
									HrPayArrearPaybill arrearbill = (HrPayArrearPaybill) arrearbillToBeDelete.get(cnt);
									//Modified By Mrugesh
									//arrearbill.setApproveFlag(delete);//3 is for logical delete
									//Ended By Mrugesh
									arrearbill.setApproveRejectDate(approveRejectDate);
									logger.info("going to delete record from arrear bill");							
									arrearBillDao.update(arrearbill);
								}
							}
					}
					//Added By Mrugesh
					/*if(billType.equalsIgnoreCase("arrears") || billType.equalsIgnoreCase("paybill"))
					{*/
						
					/*HrPayPaybill paybillVO = new HrPayPaybill();
					paybillVO.setId(trnBillNo);*/
					
					long billno=0;
					List paybillHeadMpgList = paybillHeadMpgDAOImpl.getListByColumnAndValue("hrPayPaybill", trnBillNo);
					logger.info("The list size is---->"+paybillHeadMpgList.size());
					 if(paybillHeadMpgList!=null && paybillHeadMpgList.size()>0)
						{
							for(int cnt=0;cnt<paybillHeadMpgList.size();cnt++)
							{   
								PaybillHeadMpg paybillHeadMpg = (PaybillHeadMpg) paybillHeadMpgList.get(cnt);
								
								paybillHeadMpg.setApproveFlag(delete);//3 is for logical delete
								paybillHeadMpg.setUpdatedDate(approveRejectDate);
								logger.info("going to delete record from paybill head mapping bill");							
								paybillHeadMpgDAOImpl.update(paybillHeadMpg);
								
								long Month=	(long)(paybillHeadMpg.getMonth());
								long Year=(long)(paybillHeadMpg.getYear());
						//		 billno = paybillHeadMpg.getBillNo().getBillHeadId();
								
								 //Added by Abhilash for Delete Duplicate Bill Data 
								if(Month!=0)
								{	
								
								long nextMonth = Month;
								long nextYear = Year;
								
								if(nextMonth==12) 
								{
									nextMonth=1;
									nextYear++;
									List idList = paybillDao.hrPayPayBillId(nextMonth,nextYear,locId,billno);
									
									for(int i=0; i<idList.size(); i++)
									{
									Object[] row = ((Object[])idList.get(i));
									long Id =Long.parseLong(row[0].toString());
									
									int dletedRowSize = paybillDao.deleteRecords(Id);
									
									}
									
								}
								else
								{
									nextMonth++;
									List idList = paybillDao.hrPayPayBillId(nextMonth,nextYear,locId,billno);
									
									for(int i=0; i<idList.size(); i++)
									{
									Object[] row = ((Object[])idList.get(i));
									long Id =Long.parseLong(row[0].toString());
								     int dletedRowSize = paybillDao.deleteRecords(Id);
									
									}
								}
								}
								//Ended by Abhilash
								//}
							}
							
						}
					//}
					//Ended By Mrugesh
					else
					{
						List paybillToBeDelete = paybillDao.getListByColumnAndValue("paybillGrpId",trnBillNo);
						 
						 if(paybillToBeDelete!=null && paybillToBeDelete.size()>0)
							{
								for(int cnt=0;cnt<paybillToBeDelete.size();cnt++)
								{
									HrPayPaybill paybill = (HrPayPaybill) paybillToBeDelete.get(cnt);
									//Modified By Mrugesh
									//paybill.setApproveFlag(delete);//3 is for logical delete
									//Ended By Mrugesh
									paybill.setApproveRejectDate(approveRejectDate);
									logger.info("going to delete record from paybill ");							
									paybillDao.update(paybill);
									
								}
							}
					}
					
					
					
					
					
					
					
					
				}
				
				if(outerBill==null)
				{
					logger.info("not from outer so need to call inner service");
					PaybillParaServicempl paybillParaService = new PaybillParaServicempl();
					resultObject.setResultCode(ErrorConstants.SUCCESS);
					resultObject=paybillParaService.paybillview(objectArgs);
				}
				else
				{
					logger.info("from outer so no need to call inner service");
					resultObject.setResultCode(ErrorConstants.SUCCESS);
					resultObject.setResultValue(objectArgs);
				}
               
				
		}
		catch(Exception e)
		{
			resultObject.setResultCode(ErrorConstants.ERROR);
			logger.error("Error is: "+ e.getMessage());
		}
			
		
	
		return resultObject;
	}
	
	public ResultObject checkPaybillStatus(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
	           logger.info("**************coming into checkPaybillStatus of DeletePaybillServiceImpl****************" );
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				String trnBillIds  = (String)voToService.get("trnBillId");
				
				String month  = (String)voToService.get("month");
				
				String year  = (String) voToService.get("year");
				
				
				StringTokenizer billTokens = new StringTokenizer(trnBillIds,","); //prasenjit added this for multiple bill discard 
				// long[] trnBillId = new long[billTokens.countTokens()];
				 while(billTokens.hasMoreElements())
				 {
			     String trnBillId = billTokens.nextElement().toString();
				 logger.info(" token and element is " + trnBillId);
					
				 
			      Long trnBillNo = Long.parseLong((trnBillId!=null?trnBillId:"0"));
				
				StringBuffer paybillSBf=new StringBuffer();
				List paybillList = new ArrayList();
				List paybillBillRegMpgList = new ArrayList();
				PaybillBillregMpg paybillRegVo = new PaybillBillregMpg();
				HrPayPaybill hrPayPaybill = new HrPayPaybill();
				HrPayArrearPaybill arrearBill = new HrPayArrearPaybill();
				//Added By Mrugesh
				PaybillHeadMpg paybillHeadMpg = new PaybillHeadMpg();
				//Ended by Mrugesh
				long paybillGrpId = 0;
				long status = 2;
				
				PaybillRegMpgDAOImpl paybillRegDao = new PaybillRegMpgDAOImpl(PaybillBillregMpg.class,serv.getSessionFactory());
				PayBillDAOImpl paybillDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				ArrearBillDaoImpl arrearbillDao = new ArrearBillDaoImpl(HrPayArrearPaybill.class,serv.getSessionFactory());
				//Added By Mrugesh
				PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
				//Ended By Mrugesh
				
				/*if(trnBillNo!=0)
				{
					paybillBillRegMpgList = paybillRegDao.getData(trnBillNo);
				}*/
				long Activeflag=0;
				if(trnBillNo!=null && trnBillNo!=0)
				{
							logger.info("===> before getting List :: "+trnBillIds);
							logger.info("===> before getting List :: "+trnBillNo+":: month :: "+month+"::year::"+year);
							
							String[] arColNames = {"billNo.dcpsDdoBillGroupId","approveFlag","month","year"};
							Object[] arColValues = {trnBillNo,Activeflag,Double.parseDouble(month),Double.parseDouble(year)};
							List payBillHeadMpgList = paybillHeadMpgDAOImpl.getListByColumnAndValue(arColNames,arColValues);
							if(payBillHeadMpgList!=null)
							logger.info("====> payBillHeadMpgList ::"+payBillHeadMpgList.size());
							
							if(payBillHeadMpgList!=null && payBillHeadMpgList.size()>0)
							{
								paybillHeadMpg = (PaybillHeadMpg)payBillHeadMpgList.get(0);
								
								status=paybillHeadMpg.getApproveFlag();
							}
						
				}
				
				
				paybillSBf.append("<paybillMapping>");
				paybillSBf.append("<status>").append(status).append("</status>");
				paybillSBf.append("</paybillMapping>");  
				
				logger.info("trnBillNo ="+trnBillNo);
				logger.info("paybillBillRegMpgList size ="+paybillBillRegMpgList.size());
				logger.info("paybillRegVo ="+paybillRegVo);
				logger.info("paybillGrpId ="+paybillGrpId);
				logger.info("paybillList size ="+paybillList.size());
				logger.info("hrPayPaybill ="+hrPayPaybill);
				logger.info("status ="+status);
				
				Map map = objectArgs ;
	            String paybillStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();
		         
	            logger.info(" the string buffer is :"+paybillStatus);
	            map.put("ajaxKey", paybillStatus);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				//logger.info("error");
				resultObject.setResultValue(map);
				resultObject.setViewName("ajaxData");
				logger.info(" SERVICE COMPLETE :");
		}
				 
		}
		catch(Exception e)
		{
			
			logger.error("Error is: "+ e.getMessage());
		}
		
		return resultObject;
	}
	
	
	 public ResultObject discardBill(Map objectArgs)
	    {   
		  	logger.info("inside discardbill method");
	        ResultObject lObjResult = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
	       logger.info("requestrequestrequestrequest--->>>>>>>>>>" + request);


	        /*String month = StringUtility.getParameter("month", request);
	        String year = StringUtility.getParameter("year", request);
	        String billNum = StringUtility.getParameter("billNum", request);*/
	        
	        long month = request.getParameter("month")!= null && !request.getParameter("month").equals("") ? Long.parseLong(request.getParameter("month").toString()):0l; 
	        long year = request.getParameter("year")!= null && !request.getParameter("year").equals("") ? Long.parseLong(request.getParameter("year").toString()):0l;
	   //     long billNum = request.getParameter("billNum")!= null && !request.getParameter("billNum").equals("") ? Long.parseLong(request.getParameter("billNum").toString()):0l;
	      //  long hidBillNo = request.getParameter("hidBillNo")!= null && !request.getParameter("hidBillNo").equals("") ? Long.parseLong(request.getParameter("hidBillNo").toString()):0l;
	        
	        String lStrhidBillNo=null;
			try {
				if(StringUtility.getParameter("billNum",request)!= null||!(StringUtility.getParameter("billNum",request).equals(""))){
				lStrhidBillNo = StringUtility.getParameter("billNum",request)!= null && !StringUtility.getParameter("billNum",request).equals("") ? StringUtility.getParameter("billNum",request).toString():"";
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       logger.info("====> 1.  lStrhidBillNo :: "+lStrhidBillNo);
	        String[] lArrBillNoList=null;
	        if(lStrhidBillNo!=null || lStrhidBillNo!="")
			{
	        	lArrBillNoList = lStrhidBillNo.split(",");
				logger.info("====> 1.  lArrBillNoList :: "+lArrBillNoList.length);
			}
	        /*logger.info(":::::::::::::::::::::::>>>>>>>>>>>>>>>>>>>>>>> TokenFlag : " + TokenFlag);*/
	       
	        
	     
	        PaybillHeadMpg paybillHeadmpg=null;
	        PaybillHeadMpg paybillHeadmpgNew = null;
	        
	        try
	        {
	        
	        	int approveFlag=0;
	        
	        	int nextmonthflag=4;
	        	
	        	int SettingValue=3;
	        	
	        	long nextmonth=0;
	        	long nextyear=0;
	        	
	        	if(month==12)
	        	{
	        		nextmonth=1;
	        		nextyear = nextyear + 1;
	        	}
	        	else
	        	{
	        		nextmonth = month+1;
	        		nextyear = year;
	        	}
	        	
	        	if(!lStrhidBillNo.equals(""))
	        	{
	        		logger.info("===> in if...................");
		        	PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
		        	for(int i=0;i<lArrBillNoList.length;i++)
		        	{
		        		long lbillid = Long.valueOf(lArrBillNoList[i]);
		        		logger.info("1111111 in discardBill i month year and bill number : "+ i+ month + " " + year + " " + lbillid);
		        		
		        		
			        	List<PaybillHeadMpg> paybillHeadmpgList  = paybillHeadMpgDAOImpl.getpaybillheadFromid(lbillid,approveFlag,month,year);
			        	List<PaybillHeadMpg> list = paybillHeadMpgDAOImpl.getpaybillheadFromid(lbillid,nextmonthflag,nextmonth,nextyear);
			        for(int k=0;k<paybillHeadmpgList.size();k++)
			        {
			        	
			        	paybillHeadmpg=paybillHeadmpgList.get(k);
			        	paybillHeadmpg.setApproveFlag(SettingValue);
			        	logger.info("The value of setapprovedflag is"+paybillHeadmpg.getApproveFlag());
			        	paybillHeadMpgDAOImpl.update(paybillHeadmpg);
			        	
			        	logger.info("222222 in discardBill i month year and next month : "+ i+ nextmonth + " " + nextyear + " " + nextmonthflag);
			        	
			        	
			        	if(list != null && list.size() > 0 )
			        	{	
			        		//long id = List.get(0).getId();
			        		
			        		//paybillHeadmpg = paybillHeadMpgDAOImpl.read(id);
			        		paybillHeadmpgNew= list.get(k);
			        		paybillHeadmpgNew.setApproveFlag(SettingValue);
			        		paybillHeadMpgDAOImpl.update(paybillHeadmpgNew);
			        	}
			        	
			        	
			        	long paybillId = paybillHeadmpg.getHrPayPaybill();
			        	logger.info("==> paybillId :: "+paybillId);
			        	
			        	/*paybillHeadMpgDAOImpl.deleteNonGov(paybillId);
			        	paybillHeadMpgDAOImpl.deleteLoanDtls(paybillId);*/
			        }
			        	objectArgs.put("msgDiscard", "Deleted successfully.");
		        	}
		        	
		        	 ResultObject resultBill=serv.executeService("ViewTokenNumber", objectArgs);
		        	 objectArgs = (Map)resultBill.getResultValue();
		        	
		        	
		        	lObjResult.setViewName("ViewTokenNumber");
		        	lObjResult.setResultCode(0);
		        	lObjResult.setResultValue(objectArgs);
					
	        	
	        	}
	        }
	        catch (Exception e)
	        {
	            logger.error("Error in discardBill and Error is :" + e, e);
	        }
			 
	        return lObjResult;
			 
	    }
}
