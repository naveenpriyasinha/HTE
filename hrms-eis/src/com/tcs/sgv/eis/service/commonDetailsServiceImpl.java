package com.tcs.sgv.eis.service;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.commonDetailsDaoImpl;
import com.tcs.sgv.eis.util.commonDetailsServiceImplHelper;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;

public class commonDetailsServiceImpl extends ServiceImpl {
 	Log logger = LogFactory.getLog( getClass() );
 	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
 	public ResultObject commonDetailsView(Map objectArgs) 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		byte[] paybillData = new byte[0];
		ServiceLocator serviceLocator = new ServiceLocator();
		HttpServletResponse response = (HttpServletResponse)objectArgs.get("responseObj");
		
		try 
		{
		    ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		    
		    
		    Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
		    Map voToService = (Map)objectArgs.get("voToServiceMap");
		    
		    
		    long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
		    
			commonDetailsDaoImpl commondetailsDAO = new commonDetailsDaoImpl(TrnBillRegister.class,serv.getSessionFactory());
			
			commonDetailsServiceImplHelper helper = new commonDetailsServiceImplHelper(serv);
			
			List listedData = new ArrayList();	
			SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
			List billData = new ArrayList();
			
			Date dt = new Date();
			String Year=(sdfObj.format(dt)).toString();
			sdfObj = new SimpleDateFormat("MM");
			int Month= Integer.parseInt((sdfObj.format(dt)).toString());
			String billNo = "";// this is to get the selected bill head ID
			String BillNo = ""; // this is to get the selected bill number
			String exportflag = "";
			String mahina = "";
			if(voToService != null)
			{
				if(voToService.get("Month")!=null)
					Month=Integer.parseInt(voToService.get("Month").toString());
				if(voToService.get("mahina")!=null)
					mahina=(String)voToService.get("mahina").toString();
				if(voToService.get("Year")!=null)
					Year=(String)voToService.get("Year").toString();
				if(voToService.get("billNo")!=null)
					billNo=(String)voToService.get("billNo").toString();
				if(voToService.get("BillNo")!=null)
					BillNo=(String)voToService.get("BillNo").toString();
				if(voToService.get("exportflag")!=null)
					exportflag=(String)voToService.get("exportflag").toString();
			}
			String payBillGrpId = "";
			
			if(exportflag!=null && !exportflag.equals(""))
			{
				// code to get the data to be displayed in the exported .txt file
				List DataList = commondetailsDAO.getDataforDisplay(Month,Year, locId,langId, billNo);
				//List DataList = this.getDataOfAllEmployees(serv, Month, Year, locId, langId, BillNo);
				List xtraDataList = commondetailsDAO.getxtraDataforDisplay( locId , billNo, Year);
				//List xtraDataList = this.getBudgerHeadStructure(serv, locId, BillNo, Year);
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  DataList  " + DataList.size());
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  xtraDataList  " + xtraDataList.size());
				billData=commondetailsDAO.getPaybillGrpId(Month, Year, billNo, locId);
				//billData=this.getPayBillGroupId(serv, Month, Year, BillNo, locId);


				
				if( billData != null )
				{
					
					payBillGrpId =helper.getPayBillGrpId(billData, payBillGrpId);
					logger.info("payBillGrpIdpayBillGrpIdpayBillGrpId" + payBillGrpId);
					logger.info("payBillGrpIdpayBillGrpIdpayBillGrpId" +payBillGrpId);
				}
				
				List bugdetData = new ArrayList();
				List bugdetBillData = new ArrayList();
				if( xtraDataList != null )
				{
					//int rowsize = 0;
					//String budData = "";
					logger.info("xtra Data List is not empty=="+xtraDataList.size());
					
				bugdetData = helper.getBudgetData(bugdetData, xtraDataList);
					
				}
				if( DataList!=null )
				{
					int rowsize = 0;
					int netAmount = 0;
					logger.info("data list is not empty");
					
					netAmount =helper.getNetAmoutOfBill(DataList, rowsize, listedData, netAmount);
					logger.info("listedDatalistedDatalistedDatalistedDatalistedDatalistedData" + listedData.size());
					logger.info("netAmountnetAmountnetAmountnetAmountnetAmountnetAmount" + netAmount);
					//end for	
					logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   netAmount : " + netAmount);
					
					// Code for export to text file 
					      //  BufferedWriter bufferedWriter = null;
					        StringWriter strWriter = new StringWriter();
					        BufferedWriter out = new BufferedWriter(strWriter);
					        out.write("-- Common Details");
							out.newLine(); 
							out.write(mahina+ "~" + Year+ "~"+ BillNo + "~" + netAmount + "~"+locId+"~Paybill~"+payBillGrpId+"~"+bugdetData.get(0));
							out.newLine(); out.write("-- Pay Bill Details Emp Wise ");
					        out.newLine();
					        for (int j = 0 ; j < listedData.size(); j++)
					        {
					        	if(("" + listedData.get(j).toString()).equals("#"))
					        			 out.newLine();
					        	else
					        		out.write("" + listedData.get(j));
					        		
					        }
					        out.write("-- End"); 
					        out.close();
					        strWriter.close();
					        paybillData = strWriter.toString().getBytes();
					        
   			      // up to here
				}//  end if
			}
			
			//method starts
			List BillList = helper.getBillList(locId);
			List monthList =helper.getMonthList(langId);
			List yearList = helper.getYearList(langId);
			//method ends
			
            CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
    		objectArgs.put("monthList", monthList);
			objectArgs.put("yearList", yearList);
			objectArgs.put("curmonth", Month);
			objectArgs.put("curyear", Year); 
			objectArgs.put("BillList",BillList);
			objectArgs.put("curbill", billNo);
			
			if(exportflag!=null && !exportflag.equals(""))
			{
				logger.info("Length of Exported Text file is " + paybillData.length);
				objectArgs.put("buteArray", paybillData);
			    response.addHeader("Content-disposition", "attachment; filename=Export_"+Month+"_"+ Year + ".txt");
			    resObj.setViewName("viewAttachment");
			}
			else
			{
				resObj.setViewName("ViewCommonDetails");
				
			}
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
			
		    
		} catch (Exception ex) {
			resObj.setThrowable(ex);
			logger.error("common details Screen Showing Error", ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}
 	
 	
}
