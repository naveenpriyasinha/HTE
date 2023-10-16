package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.PsrBillNumberDaoImpl;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.eis.valueobject.PsrBillNumberCustomVO;





public class ShowPsrNumberServiceImpl extends ServiceImpl {
 	Log logger = LogFactory.getLog( getClass() );
	public int flag = 0;
	
	
		public ResultObject showEmpDtl(Map objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		StringBuffer propertyList = new StringBuffer();		
		try {
				int msg = 0;

			    ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
				long langId = Long.parseLong(loginMap.get("langId").toString());
				long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
				HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
				String empId=objectArgs.get("empId").toString();
				String Psr=objectArgs.get("PsrNo").toString();
				String Bill=objectArgs.get("billno").toString();
				String Dsgn=objectArgs.get("Dsgn").toString();
				logger.info("In service Impl : billno " + Bill + " psr no " + Psr + " Dsgn " + Dsgn + " empid " + empId);
				if(Dsgn != null && !Dsgn.equals(""))
					Dsgn = Dsgn.trim();
				PsrBillNumberCustomVO customVO = new PsrBillNumberCustomVO();	
				PsrBillNumberDaoImpl PsrDAO = new PsrBillNumberDaoImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
				List DetailList = new ArrayList(); 
				List DetailValueList = new ArrayList();
				if(empId != null && !empId.equals("")){
					DetailList = PsrDAO.getEmpDtls(locId, empId, langId);
				}else if((Bill != null && !(Bill.trim()).equals("")) || (Psr != null && !(Psr.trim()).equals("")) || (Dsgn != null && !(Dsgn.trim()).equals("")))
					DetailList = PsrDAO.getDetailListForDisplay(locId, langId, Bill, Psr, Dsgn);
				String BillNo = "";
				long PsrNo = 0;
				long PsrId = 0;
				String  empFullName = "";	
				String dsgnName = "";
				if( DetailList!=null )
				{
					for (int i = 0; i < DetailList.size(); i++) 
					{
						customVO = new PsrBillNumberCustomVO();
						Object rowList[] = (Object[]) DetailList.get(i);
						
						if(rowList[0] != null && !(rowList[0].toString().trim()).equals("")){	
							 empFullName = rowList[0].toString();
						} else{
						 	empFullName = "VACANT";
						}
						customVO.setEmpFullName(empFullName);
						
						dsgnName = rowList[1].toString();
						if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){
						customVO.setDsgnName(dsgnName);
						}	
						PsrNo = Long.parseLong(rowList[2].toString());
						if(rowList[2]!= null){
							customVO.setPsrNo(PsrNo);
						}
						//customVO.setPsrNo(PsrNo);
						
						if(rowList[3] != null){
						BillNo = rowList[3].toString();
						}
						customVO.setBillNo(BillNo);
						
						
						if(rowList[4]!= null){
						PsrId = Long.parseLong(rowList[4].toString());
						}
						customVO.setPsrId(PsrId);
						
						DetailValueList.add(customVO);	
					}//end for
				}//end if
				objectArgs.put("msg", "");
				objectArgs.put("recordList", DetailValueList);
				resObj.setViewName("UpdatePsrNo");
				resObj.setResultCode(0);
				resObj.setResultValue(objectArgs);
			} catch (Exception ex) {
				resObj.setThrowable(ex);
				logger.error("UpdatePsrNo Screen Showing all Post Error", ex);
				resObj.setResultCode(-1);
			}
			return resObj;
		}
		
		public ResultObject UpdatePsrNumber(Map objectArgs) 
		{
			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
			try {
					ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
					Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
					long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
					PsrBillNumberDaoImpl PsrDAO = new PsrBillNumberDaoImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
					List DetailValueList = new ArrayList();
					List PKidList = (List) objectArgs.get("pkids");
					List psrlist =(List) objectArgs.get("psrlist");
					long psrPostId = 0;
					int temp = psrlist.size();
					String msg = "";
					if(psrlist.size() == PKidList.size() && psrlist.size() > 0) 
					{
							for(int i = 0; i < PKidList.size(); i++)
							{	
								if((PKidList.get(i).toString())!=null && !(PKidList.get(i).toString()).equals(""))
								{
									psrPostId = Long.parseLong(PKidList.get(i).toString());
								}
									HrPayPsrPostMpg emppsrdtls = PsrDAO.read(psrPostId);
								if((psrlist.get(i).toString())!=null && !(psrlist.get(i).toString()).equals(""))
								{
									emppsrdtls.setPsrId(Long.parseLong(psrlist.get(i).toString()));
									PsrDAO.update(emppsrdtls);
									temp = temp - 1; //to check for the proper number of records were updated or not
								}
								logger.info("psrPostId : " + psrPostId + "psr       : " + (psrlist.get(i).toString()));
							}
					}
					if(temp == 0 && psrlist.size() > 0)
						msg = "Successfully Completed";
					objectArgs.put("msg", msg);
					objectArgs.put("recordList", DetailValueList);
					resObj.setViewName("UpdatePsrNo");
					resObj.setResultCode(0);
					resObj.setResultValue(objectArgs);
				} catch (Exception ex) {
				resObj.setThrowable(ex);
				logger.error("UpdatePsrNo Screen Showing all Post Error", ex);
				resObj.setResultCode(-1);
			}
		return resObj;
		}
		public ResultObject AjaxPsrNumber(Map objectArgs) 
		{
			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
			StringBuffer propertyList = new StringBuffer();		
			try {
					ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
					Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
					long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
					
					PsrBillNumberDaoImpl PsrDAO = new PsrBillNumberDaoImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
					String NewPsrNumber = objectArgs.get("NewPsrNumber").toString();
					List PsrList = PsrDAO.checkPsrNumber(NewPsrNumber, locId);
					if(PsrList.size() > 0)
					{
						propertyList.append("<Psr-Number>");   	
			            propertyList.append("<list-size>").append(PsrList.size()).append("</list-size>");			            
			            propertyList.append("</Psr-Number>"); 
			            logger.info("Matched Psr List size is :  " + PsrList.size());
					}
			        else
			        {
			        	propertyList.append("<Psr-Number>");   	
			            propertyList.append("<list-size>").append(0).append("</list-size>");
			            propertyList.append("</Psr-Number>");
			         }
					
					  Map result = new HashMap();
			          String psrnumStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			          result=objectArgs;
			          result.put("ajaxKey", psrnumStr);
			          
			          resObj.setResultValue(result);
			          resObj.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
			          logger.info("checkBankName After Service Called.........\n");
					}
				catch(Exception e)
				{
					logger.info("Some Exception Ocuures...insertBankMasterDtls");
					logger.error("Error is: "+ e.getMessage());
					objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
				}
			return resObj;
		}
}























