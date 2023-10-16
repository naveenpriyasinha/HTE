package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.ColumnVo;
import com.tcs.sgv.common.helper.ExcelExportHelper;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.ChangeDetailsDao;
import com.tcs.sgv.dcps.dao.ChangeDetailsDaoImpl;
import com.tcs.sgv.dcps.dao.MapDiseCodeToMultiDDODAO;
import com.tcs.sgv.dcps.dao.MapDiseCodeToMultiDDODAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.eis.dao.DisplayPendingWorkDao;
import com.tcs.sgv.eis.dao.DisplayPendingWorkDaoImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.UpdateVoucherDtlsDao;
import com.tcs.sgv.eis.dao.UpdateVoucherDtlsDaoImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ConsolidatedBillMstDaoImpl;
import com.tcs.sgv.eis.zp.ReportingDDO.valueobject.AbstarctReportVo;
import com.tcs.sgv.eis.zp.ReportingDDO.valueobject.ConsolidatedBillMst;

public class UpdateVoucherDtlsServiceImpl extends ServiceImpl{
	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */
	private Date gDtCurDate = null; /* CURRENT DATE */
	
	Log logger = LogFactory.getLog(UpdateVoucherDtlsServiceImpl.class);

	Long gLngUserId = null;
	Long gLngPostId = null;
	private Long gLngLangId = null; /* LANG ID */
	private String gStrPostId = null; /* STRING POST ID */
	
	private void setSessionInfo(Map inputMap) throws Exception {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLngPostId = SessionHelper.getPostId(inputMap);
			gLngUserId = SessionHelper.getUserId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gLngLangId = SessionHelper.getLangId(inputMap);

		} catch (Exception e) {
			logger.error("Error in setSessionInfo of changeNameServiceImpl ", e);
			throw e;
		}
	}

	public ResultObject getDataForVoucherUpdate(Map objectArgs)
    {
    	logger.info("Inside getDataForVoucherUpdate for voucher update");
    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    	
    	try{
    				
    		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
    		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
            logger.info("lng id id "+langId);
    		
    		CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());    		
    		List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
    		//Collections.reverse(yearList);
    		logger.info(yearList.size());
    		List monthList =lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
    		logger.info(monthList.size()
			);
    		UpdateVoucherDtlsDao updateVoucherObj = new UpdateVoucherDtlsDaoImpl(UpdateVoucherDtlsDaoImpl.class, serv.getSessionFactory());
    		if((StringUtility.getParameter("Flag", request)!=null)&&(StringUtility.getParameter("Flag", request)!="")){
    			String flag= StringUtility.getParameter("Flag", request);
	   			logger.info("flag******"+flag);
	   			if(Long.parseLong(flag)==1){
	   			String selBill= StringUtility.getParameter("txtBillID", request);
	   			logger.info("selBill******"+selBill);
	   			String selYear= StringUtility.getParameter("year", request);
	   			logger.info("selYear******"+selYear);
	   			String selMonth= StringUtility.getParameter("month", request);
	   			logger.info("selMonth******"+selMonth);
	   			
	   			List voucherDetails=updateVoucherObj.getVoucherData(selBill,selYear,selMonth);
	   			objectArgs.put("yearSelected", selYear);
	   			objectArgs.put("monthSelected", selMonth);
	   			objectArgs.put("selBill", selBill);
	   			objectArgs.put("voucherDetails", voucherDetails);
	   			}
	   			if(Long.parseLong(flag)==2){
	   				String payBillId= StringUtility.getParameter("payBillId", request);
	   				logger.info("payBillId******"+payBillId);
	   				String voucherNo= StringUtility.getParameter("voucherNo", request);
	   				logger.info("voucherNo******"+voucherNo);
	   				String voucherDt= StringUtility.getParameter("voucherDt", request);
	   				logger.info("voucherDt******"+voucherDt);
	   				
	   				String finYear= StringUtility.getParameter("finYear", request);
	   				logger.info("finYear******"+finYear);
	   				
	   				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	    			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    			
	    			try {
	    				voucherDt = sdf2.format(sdf1.parse(voucherDt));
	    				logger.info("voucherDt******"+voucherDt);
	    				} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	   				updateVoucherObj.updateDetails(payBillId,voucherNo,voucherDt,finYear);
	   				
	   				
	   			}
	   			
	   			
	   		}
    	
	    	objectArgs.put("yearList", yearList);
	    	objectArgs.put("monthList", monthList);
	    	
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);//put in result object
			resultObject.setViewName("updateVoucherDetails");//set view name
			
    	}catch(Exception e){
    		resultObject = new ResultObject(ErrorConstants.ERROR);
    		resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error in loadEmpDtlsDdoWise "+ e);
    	}
    	return resultObject;
    }
	//added by roshan to abstract reports :start
	public ResultObject abstractReports(Map objectArgs) throws Exception
	{
		logger.info("Entering into abstractReports");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    	Map loginMap = (Map) objectArgs.get("baseLoginMap");
    	Long langId = 1l;
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		UpdateVoucherDtlsDao updateVoucherObj = new UpdateVoucherDtlsDaoImpl(UpdateVoucherDtlsDaoImpl.class, serv.getSessionFactory());
		String billid = StringUtility.getParameter("billid", request).trim();
		List details= updateVoucherObj.viewAbstractReports(billid);
		//putting all the details in a single list : code start here 
		AbstarctReportVo reportVO = new AbstarctReportVo();	
		List reports = new ArrayList();	
		String offName="";
		
		long totalRd=0;
		long totalLic=0;
		long totalMisc=0;
		long totalGslis=0;
		long totaloSGPF=0;
		long totalosGIS=0;
		long totalQRent=0;
		long totalBankLoan=0;
		long totalSLoan=0;
		long totalToatlDed=0;
		long totalSalaryPayabale=0;
		long totalRevenue_Stamp=0;
		if( details!=null )
		{
			for (int i = 0; i < details.size(); i++) 
			{
				reportVO = new AbstarctReportVo();

				Object rowList[] = (Object[]) details.get(i);

				int j=i+1;
				reportVO.setSrNo(j);

				String ddoCode=rowList[3].toString();
				ddoCode=ddoCode.concat("(");
				if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
					offName = rowList[2].toString();
				} else{
					offName = "-";
				}
				ddoCode=ddoCode.concat(offName);
				ddoCode.concat(")");
				reportVO.setInstituteName(ddoCode);

				String billName="";

				if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
					billName = rowList[1].toString();
				} else{
					billName = "-";
				}
				reportVO.setBillname(billName);

				String totSal="";

				if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
					totSal = rowList[4].toString();
				} else{
					totSal = "0";
				}
				reportVO.setTotalSalary(totSal);

				String fa="";

				if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
					fa = rowList[5].toString();
				} else{
					fa = "0";
				}
				reportVO.setFa(fa);

				String excPay="";

				if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
					excPay = rowList[6].toString();
				} else{
					excPay = "0";
				}
				reportVO.setExcPay(excPay);

				String grossSal="";

				if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
					grossSal = rowList[7].toString();
				} else{
					grossSal = "0";
				}
				reportVO.setGrossSalary(grossSal);

				String gpf="";

				if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
					gpf = rowList[8].toString();
				} else{
					gpf = "0";
				}
				reportVO.setGpf(gpf);

				String dcpsDelay="";

				if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
					dcpsDelay = rowList[9].toString();
				} else{
					dcpsDelay = "0";
				}
				reportVO.setDcpsDelayed(dcpsDelay);

				String dcpsReg="";

				if(rowList[10] != null && !(rowList[10].toString().trim()).equals("")){	
					dcpsReg = rowList[10].toString();
				} else{
					dcpsReg = "0";
				}
				reportVO.setDcpsregular(dcpsReg);
				/*DCPS_PAY ADded By Naveen Priya Sinha*/
				String dcpsPay="";

				if(rowList[21] != null && !(rowList[21].toString().trim()).equals("")){	
					dcpsPay = rowList[21].toString();
					
				} else{
					dcpsPay = "0";
				}
				reportVO.setDcpsPay(dcpsPay);
				
				/*DCPS_PAY ADded By Naveen Priya Sinha*/	
				String it="";

				if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
					it = rowList[11].toString();
				} else{
					it = "0";
				}
				reportVO.setIt(it);
				
				String revenueStamp="";

			/*	if(rowList[20] != null && !(rowList[20].toString().trim()).equals("")){	
					revenueStamp = rowList[20].toString();
					
				} else{
					revenueStamp = "0";
				}
				totalRevenue_Stamp=totalRevenue_Stamp+Long.parseLong(revenueStamp);
				reportVO.setRevenueStamp(revenueStamp);*/
				/*NPS DEDCUTIOn By Naveen */
				String npsEmployr="";

				if(rowList[20] != null && !(rowList[20].toString().trim()).equals("")){	
					npsEmployr = rowList[20].toString();
					
				} else{
					npsEmployr = "0";
				}
				reportVO.setNpsEmployr(npsEmployr);
				
				/*NPS DEDCUTIOn By Naveen */
				String dcpsDa="";

				if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){	
					dcpsDa = rowList[12].toString();
				} else{
					dcpsDa = "0";
				}
				reportVO.setDcpsDa(dcpsDa);
				String pt="";

				if(rowList[13] != null && !(rowList[13].toString().trim()).equals("")){	
					pt = rowList[13].toString();
				} else{
					pt = "0";
				}
				reportVO.setPt(pt);

				String compAdv="";

				if(rowList[14] != null && !(rowList[14].toString().trim()).equals("")){	
					compAdv = rowList[14].toString();
				} else{
					compAdv = "0";
				}
				reportVO.setCompAdv(compAdv);

				String otherDed="";

				if(rowList[15] != null && !(rowList[15].toString().trim()).equals("")){	
					otherDed = rowList[15].toString();
				} else{
					otherDed = "0";
				}
				reportVO.setOtherDed(otherDed);

				String pli="";

				if(rowList[16] != null && !(rowList[16].toString().trim()).equals("")){	
					pli = rowList[16].toString();
				} else{
					pli = "0";
				}
				reportVO.setPli(pli);

				String gis="";

				if(rowList[17] != null && !(rowList[17].toString().trim()).equals("")){	
					gis = rowList[17].toString();
				} else{
					gis = "0";
				}
				reportVO.setGis(gis);
				
				/*DCPS_PAY ADded By Naveen Priya Sinha*/
				String accPolicy="";

				if(rowList[22] != null && !(rowList[21].toString().trim()).equals("")){	
					accPolicy = rowList[22].toString();
					
				} else{
					dcpsPay = "0";
				}
				reportVO.setAccPolicy(accPolicy);
				
				/*DCPS_PAY ADded By Naveen Priya Sinha*/	
				
				
				String totalDed="";

				if(rowList[18] != null && !(rowList[18].toString().trim()).equals("")){	
					totalDed = rowList[18].toString();
				} else{
					totalDed = "0";
				}
				reportVO.setTotalDed(totalDed);
				
				

				Long netPay=null;
				//if(rowList[19] != null && !(rowList[19].toString().trim()).equals("")){	
					//netPay = rowList[19].toString();
				//} else{
				//	netPay = "0";
				//}
				netPay=(Long.parseLong(grossSal)-Long.parseLong(totalDed));
				reportVO.setNetPay(netPay.toString());

				String rdCode="94";
				String rd=updateVoucherObj.getAmount(rdCode,rowList[0].toString(),billid);
				if(rd != null && !(rd.toString().trim()).equals("")){	
					rd = rd.toString();
				} else{
					rd = "0";
				}
				totalRd=totalRd+Long.parseLong(rd);
				reportVO.setRd(rd);

				String licCode="91";
				String lic=updateVoucherObj.getAmount(licCode,rowList[0].toString(),billid);
				if(lic != null && !(lic.toString().trim()).equals("")){	
					lic = lic.toString();
				} else{
					lic= "0";
				}
				totalLic=totalLic+Long.parseLong(lic);
				
				reportVO.setLic(lic);

				String miscCode="95";
				String misc=updateVoucherObj.getAmount(miscCode,rowList[0].toString(),billid);
				if(misc != null && !(misc.toString().trim()).equals("")){	
					misc = misc.toString();
				} else{
					misc= "0";
				}
				totalMisc=totalMisc+Long.parseLong(misc);
				reportVO.setMisc(misc);

				String gslisCode="123,106,101,82,84,83,86";
				String gslis=updateVoucherObj.getAmount(gslisCode,rowList[0].toString(),billid);
				if(gslis != null && !(gslis.toString().trim()).equals("")){	
					gslis = gslis.toString();
				} else{
					gslis= "0";
				}
				totalGslis=totalGslis+Long.parseLong(gslis);
				reportVO.setGslis(gslis);

				String oSGpfCode="124";
				String oSGpf=updateVoucherObj.getAmount(oSGpfCode,rowList[0].toString(),billid);
				if(oSGpf != null && !(oSGpf.toString().trim()).equals("")){	
					oSGpf = oSGpf.toString();
				} else{
					oSGpf= "0";
				}
				totaloSGPF=totaloSGPF+Long.parseLong(oSGpf);
				reportVO.setOtherStatGpf(oSGpf);

				String oSGisCode="126";
				String oSGis=updateVoucherObj.getAmount(oSGisCode,rowList[0].toString(),billid);
				if(oSGis != null && !(oSGis.toString().trim()).equals("")){	
					oSGis = oSGis.toString();
				} else{
					oSGis= "0";
				}
				totalosGIS=totalosGIS+Long.parseLong(oSGis);
				reportVO.setOtherStatGis(oSGis);

				String qtrRentCode="28";
				String qtrRent=updateVoucherObj.getAmount(qtrRentCode,rowList[0].toString(),billid);
				if(qtrRent != null && !(qtrRent.toString().trim()).equals("")){	
					qtrRent = qtrRent.toString();
				} else{
					qtrRent= "0";
				}
				totalQRent=totalQRent+Long.parseLong(qtrRent);
				reportVO.setQtrRent(qtrRent);

				String bankLoanCode="92";
				String bankLoan=updateVoucherObj.getAmount(bankLoanCode,rowList[0].toString(),billid);
				if(bankLoan != null && !(bankLoan.toString().trim()).equals("")){	
					bankLoan = bankLoan.toString();
				} else{
					bankLoan= "0";
				}
				totalBankLoan=totalBankLoan+Long.parseLong(bankLoan);
				reportVO.setBankLoan(bankLoan);

				String sLoanCode="125";
				String sLoan=updateVoucherObj.getAmount(sLoanCode,rowList[0].toString(),billid);
				if(sLoan != null && !(sLoan.toString().trim()).equals("")){	
					sLoan = sLoan.toString();
				} else{
					sLoan= "0";
				}
				totalSLoan=totalSLoan+Long.parseLong(sLoan);
				reportVO.setSocLoan(sLoan);


				String totalNonDed="94,91,95,123,106,101,82,84,83,86,124,125,28,92,126";
				String totalNon=updateVoucherObj.getAmount(totalNonDed,rowList[0].toString(),billid);
				if(totalNon != null && !(totalNon.toString().trim()).equals("")){	
					totalNon = totalNon.toString();
				} else{
					totalNon= "0";
				}
				totalToatlDed=totalToatlDed+Long.parseLong(totalNon);
				reportVO.setTotalNonDed(totalNon);
				logger.info("hi totalToatlDed"+totalToatlDed);
				Long salaryPayable=null;
				
				salaryPayable=netPay-Long.parseLong(totalNon);
				totalSalaryPayabale=totalSalaryPayabale+salaryPayable;
				reportVO.setSalaryPayable(salaryPayable.toString());
				reports.add(reportVO);
			}
		}
		List sum=updateVoucherObj.getTotalSum(billid);
		objectArgs.put("totalRd", totalRd);
		objectArgs.put("totalLic", totalLic);
		objectArgs.put("totalMisc", totalMisc);
		objectArgs.put("totalGslis", totalGslis);
		objectArgs.put("totaloSGPF", totaloSGPF);
		
		objectArgs.put("totalRevenue_Stamp", totalRevenue_Stamp);
	
		objectArgs.put("totalosGIS", totalosGIS);
		objectArgs.put("totalQRent", totalQRent);
		objectArgs.put("totalBankLoan", totalBankLoan);
		objectArgs.put("totalSLoan", totalSLoan);
		objectArgs.put("totalToatlDed", totalToatlDed);
		objectArgs.put("totalSalaryPayabale", totalSalaryPayabale);
		
		objectArgs.put("details", reports);
		objectArgs.put("sum", sum);
		
		objectArgs.put("billid", billid);
		System.out.println("The value of bill id in service is.........."+billid);
		reports=null;
		sum=null;
		billid=null;
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("viewAbstractReports");
		return objRes;
	}

	public ResultObject generateExcelForAbstractRep(Map objectArgs) throws Exception
	{
		logger.info("Entering into generateExcelForAbstractRep");
	
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		UpdateVoucherDtlsDao updateVoucherObj = new UpdateVoucherDtlsDaoImpl(UpdateVoucherDtlsDaoImpl.class, serviceLocator.getSessionFactory());
		String billid = StringUtility.getParameter("billid", request).trim();
		List details= updateVoucherObj.viewAbstractReports(billid);
		
		logger.info("Welcomeeeeeeeeeeeeeeeeeeeeeeee");
		//putting all the details in a single list : code start here 
		AbstarctReportVo reportVO = new AbstarctReportVo();	
		List reports = new ArrayList();	
		List<Object> reportList = new ArrayList<Object>();
		List<Object> reportListFinal = new ArrayList<Object>();
		String offName="";
		long totalRd=0;
		long totalLic=0;
		long totalMisc=0;
		long totalGslis=0;
		long totaloSGPF=0;
		long totalosGIS=0;
		long totalQRent=0;
		long totalBankLoan=0;
		long totalSLoan=0;
		long totalToatlDed=0;
		long totalSalaryPayabale=0;
		long totalRevenue_Stamp=0;
		if( details!=null )
		{
			for (int i = 0; i < details.size(); i++) 
			{
				reportVO = new AbstarctReportVo();

				Object rowList[] = (Object[]) details.get(i);

				int j=i+1;
				reportVO.setSrNo(j);

				String ddoCode=rowList[3].toString();
				ddoCode=ddoCode.concat("(");
				if(rowList[2] != null && !(rowList[2].toString().trim()).equals("")){	
					offName = rowList[2].toString();
				} else{
					offName = "-";
				}
				ddoCode=ddoCode.concat(offName);
				ddoCode.concat(")");
				reportVO.setInstituteName(ddoCode);

				String billName="";

				if(rowList[1] != null && !(rowList[1].toString().trim()).equals("")){	
					billName = rowList[1].toString();
				} else{
					billName = "-";
				}
				reportVO.setBillname(billName);

				/*String totSal="";

				if(rowList[4] != null && !(rowList[4].toString().trim()).equals("")){	
					totSal = rowList[4].toString();
				} else{
					totSal = "0";
				}
				reportVO.setTotalSalary(totSal);*/

				String fa="";

				if(rowList[5] != null && !(rowList[5].toString().trim()).equals("")){	
					fa = rowList[5].toString();
				} else{
					fa = "0";
				}
				reportVO.setFa(fa);

				String excPay="";

				if(rowList[6] != null && !(rowList[6].toString().trim()).equals("")){	
					excPay = rowList[6].toString();
				} else{
					excPay = "0";
				}
				reportVO.setExcPay(excPay);

				String grossSal="";

				if(rowList[7] != null && !(rowList[7].toString().trim()).equals("")){	
					grossSal = rowList[7].toString();
				} else{
					grossSal = "0";
				}
				reportVO.setGrossSalary(grossSal);

				String gpf="";

				if(rowList[8] != null && !(rowList[8].toString().trim()).equals("")){	
					gpf = rowList[8].toString();
				} else{
					gpf = "0";
				}
				reportVO.setGpf(gpf);

				String dcpsDelay="";

				if(rowList[9] != null && !(rowList[9].toString().trim()).equals("")){	
					dcpsDelay = rowList[9].toString();
				} else{
					dcpsDelay = "0";
				}
				reportVO.setDcpsDelayed(dcpsDelay);

				String dcpsReg="";

				if(rowList[10] != null && !(rowList[10].toString().trim()).equals("")){	
					dcpsReg = rowList[10].toString();
				} else{
					dcpsReg = "0";
				}
				reportVO.setDcpsregular(dcpsReg);

				/*NPS Dedcution */
				String npsDeduction="";

				if(rowList[21] != null && !(rowList[21].toString().trim()).equals("")){	
					npsDeduction = rowList[21].toString();
					
				} else{
					npsDeduction = "0";
				}
				reportVO.setNpsEmployr(npsDeduction);
				/*NPS Dedcution */
				
				String it="";
					
   				if(rowList[11] != null && !(rowList[11].toString().trim()).equals("")){	
					it = rowList[11].toString();
				} else{
					it = "0";
				}
				reportVO.setIt(it);
				
				String revenueStamp="";

				/*if(rowList[20] != null && !(rowList[20].toString().trim()).equals("")){	
					revenueStamp = rowList[20].toString();
					
				} else{
					revenueStamp = "0";
				}
				totalRevenue_Stamp=totalRevenue_Stamp+Long.parseLong(revenueStamp);
				reportVO.setRevenueStamp(revenueStamp);
*/
				String dcpsDa="";

				if(rowList[12] != null && !(rowList[12].toString().trim()).equals("")){	
					dcpsDa = rowList[12].toString();
				} else{
					dcpsDa = "0";
				}
				reportVO.setDcpsDa(dcpsDa);
				String pt="";

				if(rowList[13] != null && !(rowList[13].toString().trim()).equals("")){	
					pt = rowList[13].toString();
				} else{
					pt = "0";
				}
				reportVO.setPt(pt);

				String compAdv="";

				if(rowList[14] != null && !(rowList[14].toString().trim()).equals("")){	
					compAdv = rowList[14].toString();
				} else{
					compAdv = "0";
				}
				reportVO.setCompAdv(compAdv);

				String otherDed="";

				if(rowList[15] != null && !(rowList[15].toString().trim()).equals("")){	
					otherDed = rowList[15].toString();
				} else{
					otherDed = "0";
				}
				reportVO.setOtherDed(otherDed);

				String pli="";

				if(rowList[16] != null && !(rowList[16].toString().trim()).equals("")){	
					pli = rowList[16].toString();
				} else{
					pli = "0";
				}
				reportVO.setPli(pli);

				String gis="";

				if(rowList[17] != null && !(rowList[17].toString().trim()).equals("")){	
					gis = rowList[17].toString();
				} else{
					gis = "0";
				}
				reportVO.setGis(gis);

				String totalDed="";

				if(rowList[18] != null && !(rowList[18].toString().trim()).equals("")){	
					totalDed = rowList[18].toString();
				} else{
					totalDed = "0";
				}
				reportVO.setTotalDed(totalDed);
				
				

				Long netPay=null;
				//if(rowList[19] != null && !(rowList[19].toString().trim()).equals("")){	
					//netPay = rowList[19].toString();
				//} else{
				//	netPay = "0";
				//}
				netPay=(Long.parseLong(grossSal)-Long.parseLong(totalDed));
				reportVO.setNetPay(netPay.toString());

				String rdCode="94";
				String rd=updateVoucherObj.getAmount(rdCode,rowList[0].toString(),billid);
				if(rd != null && !(rd.toString().trim()).equals("")){	
					rd = rd.toString();
				} else{
					rd = "0";
				}
				totalRd=totalRd+Long.parseLong(rd);
				reportVO.setRd(rd);

				String licCode="91";
				String lic=updateVoucherObj.getAmount(licCode,rowList[0].toString(),billid);
				if(lic != null && !(lic.toString().trim()).equals("")){	
					lic = lic.toString();
				} else{
					lic= "0";
				}
				totalLic=totalLic+Long.parseLong(lic);
				
				reportVO.setLic(lic);

				String miscCode="95";
				String misc=updateVoucherObj.getAmount(miscCode,rowList[0].toString(),billid);
				if(misc != null && !(misc.toString().trim()).equals("")){	
					misc = misc.toString();
				} else{
					misc= "0";
				}
				totalMisc=totalMisc+Long.parseLong(misc);
				reportVO.setMisc(misc);

				String gslisCode="96";
				String gslis=updateVoucherObj.getAmount(gslisCode,rowList[0].toString(),billid);
				if(gslis != null && !(gslis.toString().trim()).equals("")){	
					gslis = gslis.toString();
				} else{
					gslis= "0";
				}
				totalGslis=totalGslis+Long.parseLong(gslis);
				reportVO.setGslis(gslis);

				String oSGpfCode="124";
				String oSGpf=updateVoucherObj.getAmount(oSGpfCode,rowList[0].toString(),billid);
				if(oSGpf != null && !(oSGpf.toString().trim()).equals("")){	
					oSGpf = oSGpf.toString();
				} else{
					oSGpf= "0";
				}
				totaloSGPF=totaloSGPF+Long.parseLong(oSGpf);
				reportVO.setOtherStatGpf(oSGpf);

				String oSGisCode="126";
				String oSGis=updateVoucherObj.getAmount(oSGisCode,rowList[0].toString(),billid);
				if(oSGis != null && !(oSGis.toString().trim()).equals("")){	
					oSGis = oSGis.toString();
				} else{
					oSGis= "0";
				}
				totalosGIS=totalosGIS+Long.parseLong(oSGis);
				reportVO.setOtherStatGis(oSGis);

				String qtrRentCode="97";
				String qtrRent=updateVoucherObj.getAmount(qtrRentCode,rowList[0].toString(),billid);
				if(qtrRent != null && !(qtrRent.toString().trim()).equals("")){	
					qtrRent = qtrRent.toString();
				} else{
					qtrRent= "0";
				}
				totalQRent=totalQRent+Long.parseLong(qtrRent);
				reportVO.setQtrRent(qtrRent);

				String bankLoanCode="92";
				String bankLoan=updateVoucherObj.getAmount(bankLoanCode,rowList[0].toString(),billid);
				if(bankLoan != null && !(bankLoan.toString().trim()).equals("")){	
					bankLoan = bankLoan.toString();
				} else{
					bankLoan= "0";
				}
				totalBankLoan=totalBankLoan+Long.parseLong(bankLoan);
				reportVO.setBankLoan(bankLoan);

				String sLoanCode="125";
				String sLoan=updateVoucherObj.getAmount(sLoanCode,rowList[0].toString(),billid);
				if(sLoan != null && !(sLoan.toString().trim()).equals("")){	
					sLoan = sLoan.toString();
				} else{
					sLoan= "0";
				}
				totalSLoan=totalSLoan+Long.parseLong(sLoan);
				reportVO.setSocLoan(sLoan);


				String totalNonDed="94,91,95,96,124,126,97,92,125";
				String totalNon=updateVoucherObj.getAmount(totalNonDed,rowList[0].toString(),billid);
				if(totalNon != null && !(totalNon.toString().trim()).equals("")){	
					totalNon = totalNon.toString();
				} else{
					totalNon= "0";
				}
				totalToatlDed=totalToatlDed+Long.parseLong(totalNon);
				reportVO.setTotalNonDed(totalNon);

				Long salaryPayable=null;
				
				salaryPayable=netPay-Long.parseLong(totalNon);
				totalSalaryPayabale=totalSalaryPayabale+salaryPayable;
				reportVO.setSalaryPayable(salaryPayable.toString());
				reports.add(reportVO);
			}
		}

		logger.info("List size is3333333333333333333 "+reports.size());
		if(reports!=null && reports.size()>0){
			for(int k=0; k<reports.size();k++){
				reportList=new ArrayList<Object>();
				reportVO=new AbstarctReportVo();
				reportVO=(AbstarctReportVo) reports.get(k);
				logger.info("reportVO.getSrNo() "+reportVO.getSrNo());
				reportList.add(reportVO.getSrNo());
				reportList.add(reportVO.getInstituteName());
				reportList.add(reportVO.getBillname());
				//reportList.add(reportVO.getTotalSalary());
				reportList.add(reportVO.getGrossSalary());
				reportList.add(reportVO.getFa());
				reportList.add(reportVO.getExcPay());
				reportList.add(reportVO.getGpf());
				reportList.add(reportVO.getDcpsDelayed());
				reportList.add(reportVO.getDcpsregular());
				reportList.add(reportVO.getNpsEmployr());
				reportList.add(reportVO.getIt());
				reportList.add(reportVO.getRevenueStamp());				
				reportList.add(reportVO.getDcpsDa());
				reportList.add(reportVO.getPt());
				reportList.add(reportVO.getCompAdv());
				reportList.add(reportVO.getOtherDed());
				reportList.add(reportVO.getPli());
				reportList.add(reportVO.getGis());
				reportList.add(reportVO.getTotalDed());
				reportList.add(reportVO.getNetPay());
				reportList.add(reportVO.getRd());
				reportList.add(reportVO.getLic());
				reportList.add(reportVO.getMisc());
				reportList.add(reportVO.getGslis());
				reportList.add(reportVO.getOtherStatGpf());
				reportList.add(reportVO.getOtherStatGis());
				reportList.add(reportVO.getQtrRent());
				reportList.add(reportVO.getBankLoan());
				reportList.add(reportVO.getSocLoan());
				reportList.add(reportVO.getTotalDed());
				reportList.add(reportVO.getSalaryPayable());
				
				logger.info("size is ***********"+reportList.size());
				
				reportListFinal.add(reportList);
			}
			List sum=updateVoucherObj.getTotalSum(billid);
			Object rowListTotal[] = (Object[]) sum.get(0);
			reportList=new ArrayList<Object>();
			reportList.add(" ");
			reportList.add(" ");
			reportList.add("Total");
			//reportList.add(rowListTotal[0].toString());
			reportList.add(rowListTotal[3].toString());
			reportList.add(rowListTotal[1].toString());
			reportList.add(rowListTotal[2].toString());
			//reportList.add(rowListTotal[2].toString());
			reportList.add(rowListTotal[4].toString());
			reportList.add(rowListTotal[5].toString());
			reportList.add(rowListTotal[6].toString());
			reportList.add(rowListTotal[7].toString());
			reportList.add(rowListTotal[8].toString());
			reportList.add(rowListTotal[9].toString());
			reportList.add(totalRevenue_Stamp);
			
			reportList.add(rowListTotal[10].toString());
			reportList.add(rowListTotal[11].toString());
			reportList.add(rowListTotal[12].toString());
			reportList.add(rowListTotal[13].toString());
			reportList.add(rowListTotal[14].toString());
			reportList.add(rowListTotal[15].toString());
			reportList.add(rowListTotal[16].toString());
			reportList.add(totalRd);
			
			reportList.add(totalLic);
			reportList.add(totalMisc);
			reportList.add(totalGslis);
			reportList.add(totaloSGPF);
			reportList.add(totalosGIS);
			reportList.add(totalQRent);
			reportList.add(totalBankLoan);
			reportList.add(totalSLoan);
			reportList.add(totalToatlDed);
			reportList.add(totalSalaryPayabale);
			logger.info("size is following for report"+reportList.size());
			reportListFinal.add(reportList);
			
		}
		
		logger.info("Report 1 size is defined is below"+reportList.size());
		logger.info("Report 2 size is as below"+reportListFinal.size());

		ReportExportHelper objExport = new ReportExportHelper();
		List columnvalue = new ArrayList();
		List mainValue=new ArrayList();
		Map output = new HashMap();
		String lSBOut = "";
		String exportTo=DBConstants.DIS_EXCELFILE;
		String lineSeperator = System.getProperty("line.separator");



		ColumnVo[] excelBillReportHeading=new ColumnVo[30];
		excelBillReportHeading[0]=new ColumnVo("Sr.No",2,13,0,true,true);
		excelBillReportHeading[1]=new ColumnVo("Institute Name",2,13,0,true,true);
		excelBillReportHeading[2]=new ColumnVo("Bill Name",2,13,0,true,true);
		//excelBillReportHeading[3]=new ColumnVo("Total salary",2,13,0,true,true);
		excelBillReportHeading[3]=new ColumnVo("Gross Salary", 2, 13, 0,false,true); 
		excelBillReportHeading[4]=new ColumnVo("FA",2,13,0,true,true);
		excelBillReportHeading[5]=new ColumnVo("Exc. Pay Recovery",2,13,0,true,true);
		
		excelBillReportHeading[6]=new ColumnVo("GPF",2,13,0,true,true);
		excelBillReportHeading[7]=new ColumnVo("DCPS(DCPS Delayed)",2,13,0,true,true);
		excelBillReportHeading[8]=new ColumnVo("DCPS(DCPS Regular)",2,13,0,true,true);
		excelBillReportHeading[9]=new ColumnVo("IT",2,13,0,true,true);
		excelBillReportHeading[10]=new ColumnVo("Revenue Stamp",2,13,0,true,true);
		excelBillReportHeading[11]=new ColumnVo("DCPS DA",2,13,0,true,true);
		excelBillReportHeading[12]=new ColumnVo("PT", 2, 13, 0,false,true); 
		excelBillReportHeading[13]=new ColumnVo("Comp. Advance",2,13,0,true,true);
		excelBillReportHeading[14]=new ColumnVo("Other Deduction",2,13,0,true,true);
		excelBillReportHeading[15]=new ColumnVo("PLI",2,13,0,true,true);
		excelBillReportHeading[16]=new ColumnVo("GIS",2,13,0,true,true);
		excelBillReportHeading[17]=new ColumnVo("Total Deductions",2,13,0,true,true);
		excelBillReportHeading[18]=new ColumnVo("Net Pay", 2, 13, 0,false,true); 
		excelBillReportHeading[19]=new ColumnVo("NGR(RD)",2,13,0,true,true);
		excelBillReportHeading[20]=new ColumnVo("NGR(LIC)",2,13,0,true,true);
		excelBillReportHeading[21]=new ColumnVo("NGR(MISC)",2,13,0,true,true);
		excelBillReportHeading[22]=new ColumnVo("NGR(GSLIS)",2,13,0,true,true);
		excelBillReportHeading[23]=new ColumnVo("NGR(Other State GPF)",2,13,0,true,true);
		excelBillReportHeading[24]=new ColumnVo("NGR(Other State GIS)", 2, 13, 0,false,true); 
		excelBillReportHeading[25]=new ColumnVo("NGR(Quarter Rent)",2,13,0,true,true);
		excelBillReportHeading[26]=new ColumnVo("NGR(Bank Loan)",2,13,0,true,true);
		excelBillReportHeading[27]=new ColumnVo("NGR(Society Loan)",2,13,0,true,true);
		excelBillReportHeading[28]=new ColumnVo("NGR(Total Deductions)",2,13,0,true,true);
		excelBillReportHeading[29]=new ColumnVo("Salary Payable",2,13,0,true,true);
		columnvalue.add(excelBillReportHeading);
		mainValue.add(reportListFinal);
		
		StringBuffer lSbHeader= new StringBuffer();
		String ofcName=updateVoucherObj.getOfcName(postid);
		lSbHeader.append("TABLE SHOWING ABSTARCT REPORT OF CONSOLIDATED BILL ID : "+billid);
		lSbHeader.append("\n");
		lSbHeader.append(ofcName);
		
		logger.info("lSbHeader "+lSbHeader.toString());
		String lStrFooter= "";
		int nMode= 131;

		ExcelExportHelper exph = new ExcelExportHelper();
		byte[] aryOut = null;
		String[] param = new String[1];
		List Headerdata = new ArrayList();
		List footerdata = new ArrayList();
		param[0] = "";
		
		Headerdata.add(lSbHeader.toString());
		StringBuffer footer =new StringBuffer();
		footer = footer.append("Report of consolidated bill");
		footerdata.add(footer.toString());
		
		logger.info("Header value is"+Headerdata.toString());
		logger.info("footerdata value is"+footerdata.toString());
		logger.info("param value is"+param);
		logger.info("mainValue value is"+mainValue.size());
		logger.info("columnvalue value is"+columnvalue.size());
	
		aryOut = exph.getExcelReportPrintFormat(mainValue, columnvalue, param, Headerdata, footerdata);

		String lStrExportTo = DBConstants.DIS_EXCELFILE;
  		Map lDetailMap = new HashMap();
		if ((DBConstants.DIS_ONSCREEN).equals(lStrExportTo)) {
			lDetailMap.put(DBConstants.DIS_ONSCREEN, aryOut);
		} else if ((DBConstants.DIS_EXCELFILE).equals(lStrExportTo)) {
			lDetailMap.put(DBConstants.DIS_EXCELFILE, aryOut);
		}
		objectArgs.put("FileName", "abstact report");
		objExport.getExportData(objRes, lStrExportTo, objectArgs, lDetailMap, false);
		objRes.setResultValue(objectArgs);
		return objRes;
	}
	//added by vaibhav tyagi to view consolidated bill details : start
	public ResultObject viewconsBillDetails(Map objectArgs) throws Exception
	{
		logger.info("Entering into viewconsBillDetails");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		UpdateVoucherDtlsDao updateVoucherObj = new UpdateVoucherDtlsDaoImpl(UpdateVoucherDtlsDaoImpl.class, serviceLocator.getSessionFactory());
		String billid = StringUtility.getParameter("billid", request).trim();
		List details= updateVoucherObj.viewConsBillDetails(billid);
		List totalSum= updateVoucherObj.viewConsBillDetailsSum(billid);
		objectArgs.put("details", details);
		objectArgs.put("billid", billid);
		
		objectArgs.put("totalSum", totalSum);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("viewConsolidateBillDetails");
		return objRes;
	}

	public ResultObject generateExcelForConsBill(Map objectArgs) throws Exception
	{
		logger.info("Entering into generateExcelForConsBill");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		Long langId = 1l;
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		Long postid = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		UpdateVoucherDtlsDao updateVoucherObj = new UpdateVoucherDtlsDaoImpl(UpdateVoucherDtlsDaoImpl.class, serviceLocator.getSessionFactory());
		String billid = StringUtility.getParameter("billid", request).trim();
		List details= updateVoucherObj.viewConsBillDetails(billid);
		List totalSum= updateVoucherObj.viewConsBillDetailsSum(billid);
		ReportExportHelper objExport = new ReportExportHelper();
		List columnvalue = new ArrayList();
		List mainValue=new ArrayList();
		Map output = new HashMap();
		String lSBOut = "";
		String exportTo=DBConstants.DIS_EXCELFILE;
		String lineSeperator = System.getProperty("line.separator");
		List<Object> noOfFwdEmpConf = new ArrayList<Object>();
		List<Object> noOfFwdEmpConfList = new ArrayList<Object>();
		Object objFwdTotal[] = null;

		if (details != null && !details.isEmpty()) {
			Iterator itTotal = details.iterator();
			while (itTotal.hasNext()) {
				objFwdTotal = (Object[]) itTotal.next();
				noOfFwdEmpConf = new ArrayList();

				noOfFwdEmpConf.add(objFwdTotal[0]!=null ? objFwdTotal[0] : "-");

				noOfFwdEmpConf.add(objFwdTotal[1]!=null ? objFwdTotal[1] : "-");

				noOfFwdEmpConf.add(objFwdTotal[2]!=null ? objFwdTotal[2] : "-");

				noOfFwdEmpConf.add(objFwdTotal[3]!=null ? objFwdTotal[3] : "-");

				noOfFwdEmpConf.add(objFwdTotal[4]!=null ? objFwdTotal[4] : "-");

				noOfFwdEmpConf.add(objFwdTotal[5]!=null ? objFwdTotal[5] : "-");

				noOfFwdEmpConfList.add(noOfFwdEmpConf);
			}
			
			Object rowListTotal[] = (Object[]) totalSum.get(0);
			noOfFwdEmpConf = new ArrayList();
			
			noOfFwdEmpConf.add(" ");

			noOfFwdEmpConf.add(" ");

			noOfFwdEmpConf.add("Total");

			noOfFwdEmpConf.add(rowListTotal[0]!=null ? rowListTotal[0] : "-");

			noOfFwdEmpConf.add(rowListTotal[1]!=null ? rowListTotal[1] : "-");

			noOfFwdEmpConf.add(rowListTotal[2]!=null ? rowListTotal[2] : "-");

			noOfFwdEmpConfList.add(noOfFwdEmpConf);
		}

		ColumnVo[] excelBillReportHeading=new ColumnVo[6];
		excelBillReportHeading[0]=new ColumnVo("Bill Id", 2, 13, 0,false,true); 
		excelBillReportHeading[1]=new ColumnVo("Bill Name",2,13,0,true,true);
		excelBillReportHeading[2]=new ColumnVo("Level 1 Office Name",2,13,0,true,true);
		excelBillReportHeading[3]=new ColumnVo("Gross Amount",2,13,0,true,true);
		excelBillReportHeading[4]=new ColumnVo("Total Deductions",2,13,0,true,true);
		excelBillReportHeading[5]=new ColumnVo("Net Amount",2,13,0,true,true);

		columnvalue.add(excelBillReportHeading);
		mainValue.add(noOfFwdEmpConfList);

		StringBuffer lSbHeader= new StringBuffer();
		lSbHeader.append("Table showing details of Consolidated Bill Id : "+billid);
		lSbHeader.append("\n");
		logger.info("column value is"+columnvalue.size());
		logger.info("main value is"+mainValue.size());
		logger.info("lSbHeader "+lSbHeader.toString());
		String lStrFooter= "";
		int nMode= 131;

		ExcelExportHelper exph = new ExcelExportHelper();
		byte[] aryOut = null;
		String[] param = new String[1];
		List Headerdata = new ArrayList();
		List footerdata = new ArrayList();
		param[0] = "";

		Headerdata.add(lSbHeader.toString());
		StringBuffer footer =new StringBuffer();

		footer = footer.append("");
		footerdata.add(footer.toString());
		logger.info("Header value is"+Headerdata.toString());
		logger.info("footerdata value is"+footerdata.toString());
		logger.info("param value is"+param);
		logger.info("mainValue value is"+mainValue.size());
		logger.info("columnvalue value is"+columnvalue.size());
	
		aryOut = exph.getExcelReportPrintFormat(mainValue, columnvalue, param, Headerdata, footerdata);
		String lStrExportTo = DBConstants.DIS_EXCELFILE;
		Map lDetailMap = new HashMap();
		if ((DBConstants.DIS_ONSCREEN).equals(lStrExportTo)) {
			lDetailMap.put(DBConstants.DIS_ONSCREEN, aryOut);
		} else if ((DBConstants.DIS_EXCELFILE).equals(lStrExportTo)) {
			lDetailMap.put(DBConstants.DIS_EXCELFILE, aryOut);
		}
		objectArgs.put("FileName", "Forwarded Employee Report");
		objExport.getExportData(objRes, lStrExportTo, objectArgs, lDetailMap, false);
		objRes.setResultValue(objectArgs);
		return objRes;
	}
//Added BY Roshan for Annual increment 
	public ResultObject AllwIncremennt(Map objectArgs)
    {
    	logger.info("Inside Get district list");
    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    	HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    
    	try{
    				
    		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
    	
    		List disOfcList = null;
    		
    		UpdateVoucherDtlsDao updateVoucherObj = new UpdateVoucherDtlsDaoImpl(UpdateVoucherDtlsDaoImpl.class, serv.getSessionFactory());
    		
    		

			String allowedDis=null;
		
		
			if((StringUtility.getParameter("flag", request)!=null)&&(StringUtility.getParameter("flag", request)!="") && Long.parseLong(StringUtility.getParameter("flag", request))==1){
				allowedDis=StringUtility.getParameter("allowedDis",request);
				logger.info("allowedDis***************"+allowedDis);
				if(allowedDis.length()>0)
				allowedDis=allowedDis.substring(0,allowedDis.length()-1);
    			logger.info("allowedDis***************"+allowedDis);
    			updateVoucherObj.allowDistrict(allowedDis);
			}
			List districtList= updateVoucherObj.getAllDistrict();
	    	objectArgs.put("districtList", districtList);
	    	districtList=null;
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);//put in result object
			resultObject.setViewName("AllowDistrcitForIncr");//set view name
			
    	}catch(Exception e){
    		resultObject = new ResultObject(ErrorConstants.ERROR);
    		resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error in mapDDOCodeList "+ e);
    	}
    	return resultObject;
    }
	
	public ResultObject getEmpList(Map inputMap) throws Exception
	{

		logger.info("hiii i m in UidIntegrationServiceImpl to getEmpList");
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	
    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
    	String ddoCode = null;
    	List empList=null;

  

		try
		{
			setSessionInfo(inputMap);
			ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
			Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
		
			logger.info("hiii i m finding ddo code");
			String loggedInPost= loginMap.get("loggedInPost").toString();
			long langId = Long.parseLong(loginMap.get("langId").toString());
			logger.info("hiii i m finding logged in post Id"+loggedInPost);
			logger.info("hiii i m finding ddo code"+langId);
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			logger.info("hiii i m finding ddo code"+locId);
			DcpsCommonDAOImpl commonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoList = payDAO.getDDOCodeByLoggedInlocId(locId);
			OrgDdoMst ddoMst  = null;
			if(ddoList!=null && ddoList.size()>0) {
				 ddoMst = ddoList.get(0);
				 logger.info("hiii i m finding ddo code");
			}
			
			if(ddoMst!=null)
			 ddoCode = ddoMst.getDdoCode();
			logger.info("hiii i m finding ddo code");
			logger.info("hii The Logged in DDO Code is "+ddoCode);
		

			UpdateVoucherDtlsDao updateVoucherObj = new UpdateVoucherDtlsDaoImpl(UpdateVoucherDtlsDaoImpl.class, serv.getSessionFactory());
			empList  = updateVoucherObj.getApprovedFormsForDDO(ddoCode);
			
			inputMap.put("empList", empList );
			empList =null;
			resultObject.setResultValue(inputMap);
			resultObject.setViewName("UIDValidateList");

		}
		catch (Exception e)
		{
			resultObject.setResultValue(null);
			resultObject.setThrowable(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setViewName("errorPage");
		}

		return resultObject;
	}
	
	
}
