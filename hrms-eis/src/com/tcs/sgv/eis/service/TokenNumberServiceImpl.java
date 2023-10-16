package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.util.StringTokenizer;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.dao.PaySlipDAOImpl;
import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;
import com.tcs.sgv.eis.valueobject.TokenNumberCustomVO;
import com.tcs.sgv.pension.dao.TrnBillRegisterDAOImpl;

public class TokenNumberServiceImpl extends ServiceImpl {
 	Log logger = LogFactory.getLog( getClass() );
 	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	
		public ResultObject ViewTokenNumber(Map objectArgs) 
		{
			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
			try 
			{
				int msg = 0;
				//int msg = 0;
				
				String paybillTypeId = rb.getString("paybillTypeId").toString();// paybillType Id from Property File.	
			    ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
				long langId = Long.parseLong(loginMap.get("langId").toString());
				long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
				Map voToService = (Map)objectArgs.get("voToServiceMap");
				TokenNumberDAOImpl TokenDAO = new TokenNumberDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
				TokenNumberCustomVO customVO = new TokenNumberCustomVO();
				List listedData = new ArrayList();	
				SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
				SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

				Date dt = new Date();
				String Year=(sdfObj.format(dt)).toString();
				sdfObj = new SimpleDateFormat("MM");
				int Month= Integer.parseInt((sdfObj.format(dt)).toString());
				String billNo = "";
				String billStatus = "";
				if(voToService != null)
    			{
    				if(voToService.get("Month")!=null)
    					Month=Integer.parseInt(voToService.get("Month").toString());
    				if(voToService.get("Year")!=null)
    					Year=(String)voToService.get("Year").toString();
    				if(voToService.get("billNo")!=null)
    					billNo=(String)voToService.get("billNo").toString();
    				if(voToService.get("billtype")!=null)
    					paybillTypeId = (String)voToService.get("billtype").toString();
					if(voToService.get("billStatus")!=null)
						billStatus = (String)voToService.get("billStatus").toString();
    			}
    			logger.info(":::>>>>In service class month = " + Month + "  and Year = " + Year + " and Bill Number = " + billNo + " & billtypeId = " + paybillTypeId + " billStatus = " + billStatus+"::Loc ID :: "+locId);
				List DataList = TokenDAO.getTokenDataforDisplay(Month, Year, billNo, paybillTypeId,billStatus,locId);
			
				long id  = 0;
				String schemeCode ="";
				String subSchemeCode ="";
				String schemeName   = "";
				double billGross  = 0;
				double billNetTotal  = 0;
				long billGrpid      = 0;
				String billDescip ="";
				long appFlag=0;
				long grossTotalAmount=0;
				long netTotalAmount=0;
				
				
				if( DataList!=null )
				{
					for (int i = 0; i < DataList.size(); i++) 
					{
						customVO = new TokenNumberCustomVO();
						Object rowList[] = (Object[]) DataList.get(i);
/*
						if(rowList[0] != null && !(rowList[0].toString().trim()).equals(""))
						{
							id = Long.parseLong(rowList[0].toString());
							customVO.setId(id);
						}*/
						if(rowList[0] != null && !(rowList[0].toString().trim()).equals(""))
						{
							schemeCode = rowList[0].toString();
							customVO.setSchemeCode(schemeCode);
						}
						if(rowList[1] != null && !(rowList[1].toString().trim()).equals(""))
						{
							schemeName = rowList[1].toString();
							customVO.setSchemeName(schemeName);
						}
						
						if(rowList[2] != null && !(rowList[2].toString().trim()).equals(""))
						{
							grossTotalAmount = Long.parseLong(rowList[2].toString());
							customVO.setGrossTotalAmount(grossTotalAmount);
						}
						
						if(rowList[3] != null && !(rowList[3].toString().trim()).equals(""))
						{
							netTotalAmount = Long.parseLong(rowList[3].toString());
							customVO.setNetTotalAmount(netTotalAmount);
						}
						if(rowList[4] != null && !(rowList[4].toString().trim()).equals(""))
						{
							billGrpid = Long.parseLong(rowList[4].toString());
							customVO.setBillGrpid(billGrpid);
						}
						if(rowList[5] != null && !(rowList[5].toString().trim()).equals(""))
						{
							billDescip = rowList[5].toString();
							customVO.setBillDescip(billDescip);
						}
						if(rowList[6] != null && !(rowList[6].toString().trim()).equals(""))
							appFlag = Long.parseLong(rowList[6].toString());
						
						//Added by saurabh 
						if(rowList[8] != null && !(rowList[8].toString().trim()).equals(""))
						{
							subSchemeCode = rowList[8].toString();
							customVO.setSubSchemeCode(subSchemeCode);
						}
						//Ended by saurabh 
						
						
						if(appFlag==1)
							customVO.setBillStatus("APPROVED");
						//commented by vaibhav tyagi
						/*else if(appFlag==0)
							customVO.setBillStatus("CREATED");*/
						//added by vaibhav tyagi: start
						else if(appFlag==0){
							if(Long.parseLong(rowList[7].toString())==0){
							customVO.setBillStatus("CREATED");
							}
							else if(Long.parseLong(rowList[7].toString())==4){
								customVO.setBillStatus("CONSOLIDATED");
							}
							
							else{
								customVO.setBillStatus("FORWARDED");
							}
						}
						// Added By roshan for Change Statement
						else if(appFlag==2)
							customVO.setBillStatus("REJECTED");
						//added by vaibhav tyagi: end
						else if (appFlag == 6)
							customVO.setBillStatus("CHANGE STATEMENT GENERATED");
						else if (appFlag == 7)
							customVO
									.setBillStatus("CHANGE STATEMENT FORWARDED TO SECOND LEVEL DDO");
						else if (appFlag == 10)
							customVO
									.setBillStatus("CHANGE STATEMENT FORWARDED TO THIRD LEVEL DDO");
						else if (appFlag == 9)
							customVO.setBillStatus("CHANGE STATEMENT REJECTED");
						else if (appFlag == 8)
							customVO.setBillStatus("CHANGE STATEMENT APPROVED");
						else
							customVO.setBillStatus(" -- ");
						// Added By roshan for Change Statement
						/*System.out.println("===> 1 id :: "+id);
						System.out.println("===> 2 schemeCode :: "+schemeCode);
						System.out.println("===> 3 SchemeName :: "+schemeName);
						System.out.println("===> 4 billGross :: "+billGross);
						System.out.println("===> 5 billNetTotal :: "+billNetTotal);
						System.out.println("===> 6 billGrpid :: "+billGrpid);
						System.out.println("===> 7 billDescip :: "+billDescip);
						System.out.println("===> 8 customVO.getBillStatus() :: "+customVO.getBillStatus());*/
						listedData.add(customVO);
					}//end for
				}//end if
				
				List billList = new ArrayList(); 
				List<MstDcpsBillGroup> BillList = new ArrayList();		
				billList = TokenDAO.getBillListForDisplay(locId);
				for(Iterator itr=billList.iterator();itr.hasNext();)
				{    			
					Object[] row = (Object[])itr.next();
					MstDcpsBillGroup mstDcps = new MstDcpsBillGroup();	
					mstDcps.setDcpsDdoBillGroupId(Long.parseLong(row[0].toString()));
					mstDcps.setDcpsDdoCode((row[1].toString()));		 	
					BillList.add(mstDcps);
					
				}   
				
                CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
        		List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
        		List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
        		logger.info(" bill number from service in view :" + billNo);
				objectArgs.put("monthList", monthList);
				objectArgs.put("yearList", yearList);
				objectArgs.put("curmonth", Month);
				objectArgs.put("curyear", Year);
				objectArgs.put("DataList", listedData);
				objectArgs.put("BillList",BillList);
				objectArgs.put("curbill", billNo);
				objectArgs.put("billtype", paybillTypeId);
				objectArgs.put("msg", "");
				objectArgs.put("currBillStatus", billStatus);
				resObj.setViewName("ViewTokenNumber");
				resObj.setResultCode(0);
				resObj.setResultValue(objectArgs);
			} 
			
			catch (Exception ex) {
				resObj.setThrowable(ex);
				logger.info("Token Number Screen Showing Error"+ex.getMessage());
				//System.out.println("Token Number Screen Showing Error"+ex.getMessage());
							
				logger.error("Token Number Screen Showing Error", ex);
				
				resObj.setResultCode(-1);
			}
			return resObj;
		}
		
		public ResultObject UpdateTokenNumber(Map objectArgs)
		{
			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			try
			{
				Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
				long langId = Long.parseLong(loginMap.get("langId").toString());
				List listedData = new ArrayList();
				List tokenlist =(List) objectArgs.get("tokenlist");
				List billnolist =(List) objectArgs.get("billnolist");
				List tokendatelist =(List) objectArgs.get("tokendatelist");
				int Month =Integer.parseInt(objectArgs.get("month").toString());
				long Year =Long.parseLong(objectArgs.get("year").toString());
				String curbill = objectArgs.get("billNum").toString();
				String Flag=objectArgs.get("Flag").toString();
				String billtype = (objectArgs.get("billtype").toString() != null) && !(objectArgs.get("billtype").toString().equals("")) ? objectArgs.get("billtype").toString() : "";
				String currBillStatus = (objectArgs.get("currBillStatus").toString() != null) && !(objectArgs.get("currBillStatus").toString().equals("")) ? objectArgs.get("currBillStatus").toString() : "";
				
				int listlength = 0;
				logger.info("................Flag : " + Flag);
				listlength = tokenlist.size();
				int temp = listlength;
				String msg = "";
				if(listlength > 0 && (Flag.equals("S") || Flag.equals("AS")))
				{
					for(int j = 0; j < listlength; j++)
					{
			            Date voucherDt=null;
			            long billNo=((billnolist.get(j)!=null&&!(billnolist.get(j)).equals(""))?Long.parseLong(billnolist.get(j).toString()):0);
			            String voucherNo = ((tokenlist.get(j)!=null&&!(tokenlist.get(j)).equals(""))?tokenlist.get(j).toString():"").toString();
			            String voucherDate =((tokendatelist.get(j)!=null&&!(tokendatelist.get(j)).equals(""))?tokendatelist.get(j).toString():"").toString();
			            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			            if(voucherDate != null && !voucherDate.equals(""))
			    		{
			    			voucherDt = sdf.parse(voucherDate);
			    		}
			            // Updation of the Trn_bill_register
			            TrnBillRegister trnBillVO = new TrnBillRegister();
			            TrnBillRegisterDAOImpl trnDao = new TrnBillRegisterDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
			            //if(billNo != 0 )
			            	trnBillVO = trnDao.read(billNo);
			            if(voucherNo != null && !voucherNo.equals(""))
			            	trnBillVO.setTokenNum(Long.parseLong(voucherNo));
			            if(voucherDate != null && !voucherDate.equals(""))
			            	trnBillVO.setUpdatedDate(voucherDt);
			            if(trnBillVO != null)
			            	trnDao.update(trnBillVO);
			            
			            String strBillNo = trnBillVO.getBillCntrlNo();
			            //Updation of the Hr_pay_payslip
			            PaySlipDAOImpl payslipDao = new PaySlipDAOImpl(HrPayPayslip.class,serv.getSessionFactory());
			            List<HrPayPayslip> lstPayslip = payslipDao.getListByColumnAndValue("billNo",strBillNo);
			            if(lstPayslip != null && lstPayslip.size()>0) 
			            {
			            	for(int i=0;i<lstPayslip.size();i++)
			            	{
			            		HrPayPayslip hrPayPayslip = new HrPayPayslip();
				            	hrPayPayslip = lstPayslip.get(i);
				            	hrPayPayslip.setTokenNo(voucherNo);
				            	hrPayPayslip.setUpdatedDate(voucherDt);
				            	payslipDao.update(hrPayPayslip);
			            	}
			            }
			            temp = temp - 1; //to check for the proper number of records were updated or not
					}
				}
				if(Flag.equals("AS") || Flag.equals("A"))
				{
					List billNoList = new ArrayList();
					StringTokenizer st = new StringTokenizer(curbill, ",");
				    while (st.hasMoreTokens()) 
				    {
				    	billNoList.add(st.nextToken());
				    	//System.out.println("::::::::::::::::::::::::::::::::::::::: StringTokenizer value :   " + billNoList.toString());
				    }
				    for(int i = 0; i < billNoList.size(); i++)
				    {
				    	if(objectArgs.get("billNo")!=null)
				    		objectArgs.remove("billNo");
				    	if(objectArgs.get("approveFlag")!=null)
				    		objectArgs.remove("approveFlag");
				    	objectArgs.put("billNo", billNoList.get(i));
				    	//System.out.println(":::::::::::::::::::::::::::::::: in loop for approval :::::::    TRN bill number " + billNoList.get(i));
				    	objectArgs.put("approveFlag", "FromTokenNumber");
						ResultObject resultObj = serv.executeService("APPROVE_PAYBILL", objectArgs);
						Map resultMap = (Map) resultObj.getResultValue();
				    }
				}

				if(temp == 0 && listlength > 0)
				{
					if(Flag.equals("AS"))
						msg = "Bill Successfully Saved and Approved";
					else
						msg = "Record Successfully Updated";
				}
				else if(Flag.equals("A"))
						msg = "Bill Successfully Approved";
				else
					msg = "No Record Updated";
		        CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
				List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
		        objectArgs.put("monthList", monthList);
				objectArgs.put("yearList", yearList);
				objectArgs.put("curbill", curbill);
				objectArgs.put("curmonth", Month);
				objectArgs.put("curyear", Year);
				objectArgs.put("DataList", listedData);
				objectArgs.put("msg", msg);
				objectArgs.put("billtype",billtype);
				objectArgs.put("currBillStatus",currBillStatus);
				resObj.setViewName("ViewTokenNumber");
				resObj.setResultCode(0);
				resObj.setResultValue(objectArgs);
				
				}
				catch (Exception ex) 
				{
					resObj.setThrowable(ex);
					logger.error("Token Number Screen Showing Error", ex);
					resObj.setResultCode(-1);
				}
				return resObj;
		}
}
