package com.tcs.sgv.eis.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import com.tcs.sgv.core.service.ServiceImpl;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.mapping.Array;

import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstScheme;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrEisGISDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class GisReportServiceImpl extends ServiceImpl {

	
	Log logger = LogFactory.getLog(getClass());
	
	public ResultObject generateGisReport(Map objectArgs)
	{
		//logger.info("In class finally");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		try
		{
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			CmnLookupMstDAOImpl cmnLookupMstObj = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			List lMonthList = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("Month", langId);
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			long finYearId=Long.parseLong(resourceBundle.getString("finYearId"));
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			String bill = StringUtility.getParameter("billNo", request);
			String mon =  StringUtility.getParameter("month", request);
			String yr =  StringUtility.getParameter("year", request);
			String gis = StringUtility.getParameter("GIS", request);
			long billNo = bill!=null && !bill.equals("")?Long.parseLong(bill):0;
			int month =  mon!=null && !mon.equals("")?Integer.parseInt(mon):0;
			int year =  yr!=null && !yr.equals("")?Integer.parseInt(yr):0;
			
			
			//Added by Abhilash for showing header
			
			logger.info("gis name coming from jsp"+gis);
			PayBillDAOImpl billDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			String gisName = billDAOImpl.getGisName(gis);
			logger.info("gisName is *********"+gisName);
			objectArgs.put("gisName", gisName);
			
			//Ended by Abhilash for showing header
			
			//logger.info("month + year + bill No "+month +" "+year +" "+billNo);
			PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<HrPayPaybill>  paybillVoList = payDao.getBillData( locId,  billNo,  month,  year);
			
			long temp= 340000;
			CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List<CmnLookupMst> list = cmnDao.getListByColumnAndValue(new String[]{"lookupShortName","parentLookupId"},new Object[]{String.valueOf(month),temp} );
			String monthName = list!=null&&list.size()>0?list.get(0).getLookupName():String.valueOf(month);
			
			//added by abhilash
			long groupACompositeamount=0;
			long groupBCompositeamount=0;
			long groupCCompositeamount=0;
			long groupDCompositeamount=0;
			long groupBnGzCompositeamount=0;
			long totalCompositeAmount =0;
			
			long groupAPremiumamount=0;
			long groupBPremiumamount=0;
			long groupCPremiumamount=0;
			long groupDPremiumamount=0;
			long groupBnGzPremiumamount=0;
			long totalPremiumAmount =0;
			//ended by abhilash
			
			int[] grpCompositeCount = new int[5];
			int[] grpPremiumCount = new int[5];
			long[] empNetCount = new long[5];
			double[] gisCompoAmt = new double[5];
			double[] gisPreAmt = new double[5];
			double[] gisArr = new double[5];
			Date sysDate = new Date();
			 logger.info("size of payBillVo list"+paybillVoList.size());
			for(HrPayPaybill payBillVo: paybillVoList)
			{
				//logger.info("paybill id is "+payBillVo.getId());
				int index =0;
				if(payBillVo.getHrEisOtherDtls()!=null && payBillVo.getHrEisOtherDtls().getHrEisSgdMpg()!=null &&payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg()!=null && payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst()!=null)
				{
					logger.info("inside for inside if ::::--->getHrEisOtherDtls,getHrEisSgdMpg,getOrgGradeMst() NOT NULL");
					
						//index = Integer.parseInt(payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeCode());

					long empId=payBillVo.getHrEisOtherDtls().getHrEisEmpMst().getEmpId();
					//HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls)objectArgs.get("hrEisOtherDtls");
					logger.info("EmpId-"+empId);
					HrEisGISDtls gisDtls =payDao.getGroupIdFromEmpId(empId);
				
					if(gisDtls!= null && gisDtls.getOrgGradeMst().getGradeId()!=100115)
					{
					if(gisDtls!= null)
					{
					index = Integer.parseInt(gisDtls.getOrgGradeMst().getGradeCode());
					logger.info("index if gisDtls!= null-"+index);
					}
					else
					{
						index = Integer.parseInt(payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeCode());
						logger.info("index if gisDtls = null-"+index);
					}
						Date doj = payBillVo.getHrEisEmpMst().getOrgEmpMst().getEmpDoj();
						if(doj.getYear()==sysDate.getYear())
						{
							logger.info("inside if doj.year == sysdate");
							Method method = null;
							Class cls = payBillVo.getClass();
							method =  cls.getMethod("getDeduc"+gis, null);
							double amt = (Double)method.invoke(payBillVo, null);
							if(gisPreAmt[index-1] ==0 && amt!=0)
							{
								gisPreAmt[index-1] = amt;
								logger.info("inside if gisPreAmt[index-1] ==0 && amt!=0"+amt);
							}
							if(amt!=0)
							{
								grpPremiumCount[index-1]++;
								logger.info("inside if amt > 0s"+grpPremiumCount[index-1]);
							}
							
//added by abhilash
							
							PayBillDAOImpl payBillDAO =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
							long paybillGroupid = payBillDAO.getPaybillgrpIdForOuter(billNo, month, year, locId);
							
							logger.info("Premium paybillGroupid*********"+paybillGroupid);
							
							List groupwiseList = payBillDAO.getAmountOfGroupWise(locId, paybillGroupid, month, year,empId,gis);
							logger.info("Premium groupwiseList*********"+groupwiseList.size());
					
							long groupaPremiumamount=0;
							long groupbPremiumamount=0;
							long groupcPremiumamount=0;
							long groupdPremiumamount=0;
							long groupbnGzPremiumamount=0;
							//long totalPremiumAmount =0;
							if(groupwiseList!=null && groupwiseList.size()!=0)
							{
								logger.info("inside if groupwiseList!=null && groupwiseList.size()!=0");
								for(Iterator itr=groupwiseList.iterator();itr.hasNext();)
								{

									Object[] rowList = (Object[])itr.next();

									
										if(rowList[1].toString().equals("A"))
										{
											groupaPremiumamount = ((Double)(rowList[0])).longValue();
											groupAPremiumamount+=groupaPremiumamount;
										}
																									
										if(rowList[1].toString().equals("B"))
										{
											groupbPremiumamount = ((Double)(rowList[0])).longValue();
											groupBPremiumamount+=groupbPremiumamount;
										}
																	
										if(rowList[1].toString().equals("C"))
										{
											groupcPremiumamount = ((Double)(rowList[0])).longValue();
											groupCPremiumamount+=groupcPremiumamount;
										}
																	
										if(rowList[1].toString().equals("D"))
										{
											groupdPremiumamount = ((Double)(rowList[0])).longValue();
											groupDPremiumamount+=groupdPremiumamount;
										}
																	
										if(rowList[1].toString().equalsIgnoreCase("BnZ") || rowList[1].toString().equalsIgnoreCase("B N Gz")  || rowList[1].toString().equalsIgnoreCase("BNGz"))
										{
											groupbnGzPremiumamount = ((Double)(rowList[0])).longValue();
											groupBnGzPremiumamount+=groupbnGzPremiumamount;
											logger.info("inside if in Iterator for --- B Non GZ ---"+ groupbnGzPremiumamount);
										}
																	
									}
									

								}
							
							totalPremiumAmount=groupAPremiumamount+groupBPremiumamount+groupCPremiumamount+groupDPremiumamount+groupBnGzPremiumamount;
							
							//ended by abhilash
						}
						else
						{
							logger.info("inside else groupwiseList!=null && groupwiseList.size()!=0");
							Method method = null;
							Class cls = payBillVo.getClass();
							method =  cls.getMethod("getDeduc"+gis, null);
							double amt = (Double)method.invoke(payBillVo, null);
							if(gisCompoAmt[index-1] ==0 && amt!=0)
							{
								gisCompoAmt[index-1] = amt;
								logger.info("inside if gisPreAmt[index-1] ==0 && amt!=0"+amt);
							}
							if(amt!=0)
							{
								grpCompositeCount[index-1]++;
								logger.info("inside if amt!=0"+grpCompositeCount[index-1]);
							}
							//added by abhilash
							PayBillDAOImpl payBillDAO =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
							long paybillGroupid = payBillDAO.getPaybillgrpIdForOuter(billNo, month, year, locId);
							
							logger.info("paybillGroupid*********"+paybillGroupid);
							
							List groupwiseList = payBillDAO.getAmountOfGroupWise(locId, paybillGroupid, month, year,empId,gis);
							logger.info("groupwiseList*********"+groupwiseList.size());
					
							long groupacompositeamount=0;
							long groupbcompositeamount=0;
							long groupccompositeamount=0;
							long groupdcompositeamount=0;
							long groupbnGzCompositeamount=0;
							//long totalCompositeAmount =0;
							
							if(groupwiseList!=null && groupwiseList.size()!=0)
							{
								for(Iterator itr=groupwiseList.iterator();itr.hasNext();)
								{

									Object[] rowList = (Object[])itr.next();
									
										if(rowList[1].toString().equals("A"))
										{
											groupacompositeamount = ((Double)(rowList[0])).longValue();
											groupACompositeamount+=groupacompositeamount;
										}
																									
										if(rowList[1].toString().equals("B"))
										{
											groupbcompositeamount = ((Double)(rowList[0])).longValue();
											groupBCompositeamount+=groupbcompositeamount;
										}
																	
										if(rowList[1].toString().equals("C"))
										{
											groupccompositeamount = ((Double)(rowList[0])).longValue();
											groupCCompositeamount+=groupccompositeamount;
										}
																	
										if(rowList[1].toString().equals("D"))
										{
											groupdcompositeamount = ((Double)(rowList[0])).longValue();
											groupDCompositeamount+=groupdcompositeamount;
										}
																	
										if(rowList[1].toString().equalsIgnoreCase("BnZ") || rowList[1].toString().equalsIgnoreCase("B N Gz")  || rowList[1].toString().equalsIgnoreCase("BNGz"))
										{
											groupbnGzCompositeamount = ((Double)(rowList[0])).longValue();
											groupBnGzCompositeamount+=groupbnGzCompositeamount;
											logger.info("inside else in Iterator for --- B Non GZ ---"+ groupbnGzCompositeamount);
										}
																	
									}

								}
							
							logger.info("groupACompositeamount*****"+groupACompositeamount);
							logger.info("groupBCompositeamount*****"+groupBCompositeamount);
							logger.info("groupCCompositeamount*****"+groupCCompositeamount);
							logger.info("groupDCompositeamount*****"+groupDCompositeamount);
							logger.info("groupBnGzCompositeamount*****"+groupBnGzCompositeamount);
							
							totalCompositeAmount=groupACompositeamount+groupBCompositeamount+groupCCompositeamount+groupDCompositeamount+groupBnGzCompositeamount;
							//ended by abhilash
						}
						//temp removed as we have renamed JanJulGIS to DCSP Arr
						//need to be checked again after wards
						
						if(gisName.equalsIgnoreCase("GIS"))
						{
						gisArr[index-1] += payBillVo.getDeduc9706();
						}
						//ended
						//added by abhilash
						objectArgs.put("groupAPremiumamount", groupAPremiumamount);
						objectArgs.put("groupBPremiumamount", groupBPremiumamount);
						objectArgs.put("groupCPremiumamount", groupCPremiumamount);
						objectArgs.put("groupDPremiumamount", groupDPremiumamount);
						objectArgs.put("groupBnGzPremiumamount", groupBnGzPremiumamount);
						objectArgs.put("totalPremiumAmount", totalPremiumAmount);
						
						objectArgs.put("groupACompositeamount", groupACompositeamount);
						objectArgs.put("groupBCompositeamount", groupBCompositeamount);
						objectArgs.put("groupCCompositeamount", groupCCompositeamount);
						objectArgs.put("groupDCompositeamount", groupDCompositeamount);
						objectArgs.put("groupBnGzCompositeamount", groupBnGzCompositeamount);
						objectArgs.put("totalCompositeAmount", totalCompositeAmount);
						//ended by abhilash
					}
			}
					
			}
			
			
			double[] totCompo = new double[5];
			double[] totPrem = new double[5];
			double totCompoAll=0;
			double totPremAll=0;
			int compoCountAll =0;
			int premCountAll=0;
			double totArr =0;
			for(int i=0;i<5;i++)
			{
				totCompo[i] = gisCompoAmt[i]*grpCompositeCount[i];
				totCompoAll+=totCompo[i];
				totPrem[i]= gisPreAmt[i]*grpPremiumCount[i];
				totPremAll+=totPrem[i];
				compoCountAll+=grpCompositeCount[i];
				premCountAll+=grpPremiumCount[i];
				totArr+=gisArr[i];
				//logger.info("row "+i+" compoeitr count "+grpCompositeCount[i] +" amount is "+gisCompoAmt[i]+" total "+totCompo[i]+" premium count "+grpPremiumCount[i]+" premium amount "+gisPreAmt[i]+" gis total amt "+totPrem[i] +" arr "+gisArr[i]);
			}
			//logger.info(" tot compo all "+compoCountAll+" totamt compo all "+ Math.round(totCompoAll) +" tot prem all "+premCountAll+" tot prem "+totPremAll);
			
			
			//long forWords = Long.parseLong(String.valueOf(totArr+totCompoAll+totPremAll));
			PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			String DeptName=payBillDAOImpl.getOffice(locId);
			long loggedInpostId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			/*List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
			String ddocode ="";
			if(ddoList.size()>0)
				ddocode = ddoList.get(0).getDdoCode();*/
			String ddocode ="";
			String TanNo="";
			long locactionId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locactionId);
    		if(ddoCodeList!=null)
    		 logger.info("After query execution for DDO Code " + ddoCodeList.size());
    		
    		OrgDdoMst ddoMst = null; 
    		if(ddoCodeList!=null && ddoCodeList.size()>0){
    			ddoMst = ddoCodeList.get(0);
    		}
    		
    		if(ddoMst!=null) {
    			ddocode=ddoMst.getDdoCode();
    			TanNo=ddoMst.getTanNo();
    		}
    		logger.info("DDO Code is " + ddocode);
			String TresuryName ="";
			String TresuryCode ="";
			List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);
			
			if(TreasuryList!=null && TreasuryList.size()!=0)
			{
				for(Iterator itr=TreasuryList.iterator();itr.hasNext();)
				{

					Object[] rowList = (Object[])itr.next();

					if(rowList[0] != null)
					{
						TresuryName = rowList[0].toString().trim();
					}
					if(rowList[1] != null)
					{
						TresuryCode = rowList[1].toString().trim();
					}

				}
			}
			Calendar cal = new GregorianCalendar();
			int calMonth = cal.get(Calendar.MONTH);
			int calYear = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			int millysecond = cal.get(Calendar.MILLISECOND);

			String dated=day + "/" + (month) + "/" + year;
			String verificationDate=year + "-" + (month) + "-" + day + " " + hour +":" +minute+":"+second+"."+millysecond;
			
			String officenameandddocode = DeptName+"("+ddocode+")";
			String treasurynameandcode = TresuryName+"("+TresuryCode+")";
			
			  Date now = new Date();
			  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
			 // logger.info("10. "+format.format(now));
			  
			  String dsgnName =payBillDAOImpl.getDsgnNameForFormB(locId,loggedInpostId);
			  
			  

				//For major code
				logger.info("Bill number is "+billNo+" Generating outer for month "+ month +" year "+year);
				long TypeId = 0;
				if(gis.equalsIgnoreCase("1000"))
				{
					TypeId = 82;
				}
				if(gis.equalsIgnoreCase("1001"))
				{
					TypeId = 83;
				}
				if(gis.equalsIgnoreCase("1002"))
				{
					TypeId = 84;
				}
				if(gis.equalsIgnoreCase("1004"))
				{
					TypeId = 85;
				}
				if(gis.equalsIgnoreCase("1005"))
				{
					TypeId = 86;
				}
					
				long accno = payBillDAOImpl.getAccountHeadCode(TypeId);
				if(accno>0)
				{
					String acc_no = ""+accno;
					acc_no = acc_no.substring(0, 4);
					objectArgs.put("majorCode", acc_no);
				}
				
				//Ended
				
			objectArgs.put("officenamewithddocode", officenameandddocode);
			objectArgs.put("officename", DeptName);
			objectArgs.put("dsgnName", dsgnName);
			objectArgs.put("treasury", treasurynameandcode);
			
			

			objectArgs.put("netPayInWords", ConvertNumbersToWord.convert(new Double(totArr+totalCompositeAmount+totalPremiumAmount).longValue()));
			objectArgs.put("totArr", Math.round(totArr));
			objectArgs.put("compoCountAll", compoCountAll);
			objectArgs.put("premCountAll", premCountAll);
			objectArgs.put("totCompo", totCompo);
			objectArgs.put("totPrem", totPrem);
			objectArgs.put("totCompoAll", Math.round(totCompoAll));
			objectArgs.put("totPremAll", Math.round(totPremAll));
			objectArgs.put("compoCount", grpCompositeCount);
			objectArgs.put("premCount", grpPremiumCount);
			objectArgs.put("compoAmt", gisCompoAmt);
			objectArgs.put("premAmt", gisPreAmt);
			objectArgs.put("gisArr", gisArr);
			objectArgs.put("month", monthName);
			objectArgs.put("year", year);
			objectArgs.put("Dated", dated);
			objectArgs.put("VerificationDate", format.format(now));
			resultObject.setViewName("gisReport");
			resultObject.setResultValue(objectArgs);
			
		}catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			
		}
		
		return resultObject;
	}
}
