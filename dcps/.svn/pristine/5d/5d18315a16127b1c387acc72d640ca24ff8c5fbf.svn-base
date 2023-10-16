//View Created Post Report Details by Swati

package com.tcs.sgv.dcps.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.helper.ColumnVo;
import com.tcs.sgv.common.helper.ExcelExportHelper;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.VacantPostDtlsDAOImpl;
import com.tcs.sgv.dcps.dao.ViewCreatedPostDtlsDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;

public class VacantPostDtlsServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantsBundle = ResourceBundle.getBundle("../resources/Payroll");
	int msg;

	public ResultObject loadVacantPostDtls(Map objectArgs) {
		logger.info("Inside Get Vacant Post Details");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Long lLngLocId = null;
		OrgDdoMst lObjDdoMst = null;
		String lStrDdocode = null;
		String lFlag = null;

		msg = 0;
		try {

			PayBillDAOImpl lObjBillDAO = new PayBillDAOImpl(null, serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			String locId = loginDetailsMap.get("locationId").toString();

			lFlag = StringUtility.getParameter("empStat", request);
			if (!"".equals(locId)) {
				lLngLocId = Long.parseLong(locId);
			}
			if (lFlag.equals("Y")) {
				List<OrgDdoMst> lLstDDOList = lObjBillDAO.getDDOCodeByLoggedInlocId(lLngLocId);
				if (!lLstDDOList.isEmpty() && lLstDDOList != null) {
					lObjDdoMst = lLstDDOList.get(0);
					lStrDdocode = lObjDdoMst.getDdoCode();
				}
			} else if (lFlag.equals("N")) {
				lStrDdocode = StringUtility.getParameter("Ddocode", request);
			}

			logger.info("DDO Code is " + lStrDdocode);
			List listVacantPost = null;

			// long locId =
			// StringUtility.convertToLong(loginMap.get("locationId").toString());
			logger.info("locId in VacantPostDtlsServiceImpl is: " + locId);

			VacantPostDtlsDAOImpl vacantPostDtlsDAOImpl = new VacantPostDtlsDAOImpl(PaybillHeadMpg.class,
					serv.getSessionFactory());
			listVacantPost = vacantPostDtlsDAOImpl.getCreatedPostDtls(lStrDdocode);

			// List listViewCreatedPost =
			if (listVacantPost != null)
				logger.info("Size of the List Fetched is -->" + listVacantPost.size());

			objectArgs.put("VacantPostDtls", listVacantPost);
			objectArgs.put("Ddocode", lStrDdocode);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);// put in result object
			resultObject.setViewName("VacantPostDtls");// set view name
		} catch (Exception e) {
			resultObject = new ResultObject(ErrorConstants.ERROR);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.info(e);
		}
		return resultObject;
	}

	public ResultObject generateVacantPostExcel(Map objectArgs) {
	     logger.info("Inside Get Created Post Details"); 
	     ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS); 
	     ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator"); 
	     HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj"); 
	      Long lLngLocId = null;
	      OrgDdoMst lObjDdoMst = null; 
	      String lStrDdocode = null; 
	      String lFlag = null;
	  
	  try{ 
		  PayBillDAOImpl lObjBillDAO = new PayBillDAOImpl(null,serv.getSessionFactory()); 
		  Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
		  String locId=loginDetailsMap.get("locationId").toString();
	  
	      lFlag = StringUtility.getParameter("empStat",request); 
	      if(!"".equals(locId)){
	      lLngLocId = Long.parseLong(locId); 
	      } 
	      if(lFlag.equals("Y")){ 
	    	  List<OrgDdoMst> lLstDDOList = lObjBillDAO.getDDOCodeByLoggedInlocId(lLngLocId);
	        if(!lLstDDOList.isEmpty() && lLstDDOList != null){
	        	lObjDdoMst = lLstDDOList.get(0); 
	        	lStrDdocode = lObjDdoMst.getDdoCode(); 
	         } 
	       }else
	        if(lFlag.equals("N")){ 
	        	lStrDdocode = StringUtility.getParameter("Ddocode",request); 
	        }
	  
	           logger.info("DDO Code is "+lStrDdocode);
	           List listVacantPost = null;
	  
	           VacantPostDtlsDAOImpl vacantPostDtlsDAOImpl = new VacantPostDtlsDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
	           listVacantPost = vacantPostDtlsDAOImpl.getCreatedPostDtls(lStrDdocode);
	  
	            List empDDONameDesg = vacantPostDtlsDAOImpl.getDDOOfcAddDesgName(locId);
	                Object[] obj1 = (Object[])empDDONameDesg.get(0);
	                logger.info("obj size.."+obj1.length);
	                logger.info("This is to certify that the information of employees of "+obj1[2].toString()+"-------------------");
	                logger.info("List Size..........."+listVacantPost.size());
	                  ReportExportHelper objExport = new ReportExportHelper(); 
	                  List columnvalue = new ArrayList(); 
	                  List mainValue=new ArrayList(); 
	                  Map output = new HashMap();
	                 String lSBOut = "";
	                 String exportTo=DBConstants.DIS_EXCELFILE; 
	                 String lineSeperator = System.getProperty("line.separator"); 
	                List<Object> lLstInnerList = new ArrayList<Object>(); 
	                List<Object> lLstArrOuter = new ArrayList<Object>(); 
	                Object Obj[]; 
	                if (listVacantPost != null && !listVacantPost.isEmpty()) { 
	                	Iterator it = listVacantPost.iterator(); 
	                	while (it.hasNext()) { 
	                	Obj = (Object[])it.next(); 
	                	lLstInnerList = new ArrayList<Object>();
	                    lLstInnerList.add(Obj[0]!=null ? Obj[0] : "-");//EName
	                    lLstInnerList.add(Obj[1]!=null ? Obj[1] : "-");//Post Name 
	                    lLstInnerList.add(Obj[3]!=null ? Obj[3] : "-");//Status FLag
	                    //Post Type
	                    if (Obj[2] != null) { 
	                        logger.info(Obj[2].toString());
	                    if(Obj[2].toString().equals("10001198130")){
	                        lLstInnerList.add("Temporary");
	                     }
	                    if(Obj[2].toString().equals("10001198129")){
	                        lLstInnerList.add("Permanent"); 
	                     } 
	                   } else {
	                         lLstInnerList.add("-"); 
	                     }
	                    lLstInnerList.add(Obj[4]!=null ? Obj[4] : "-");//Designation
	  
	                      lLstInnerList.add(Obj[5]!=null ? Obj[5] : "-");//DDO Code
	  
	                   }
	                } 
	                  ColumnVo[] excelBillReportHeading=new ColumnVo[7];
	                      excelBillReportHeading[0]=new ColumnVo("Employee Name",2,13,0,true,true); 
	                      excelBillReportHeading[1]=new ColumnVo("Post Name",2,13,0,true,true); 
	                      excelBillReportHeading[2]=new ColumnVo("Post Type",2,13,0,true,true); 
	                      excelBillReportHeading[3]=new ColumnVo("Status",2,13,0,true,true); 
	                      excelBillReportHeading[4]=new ColumnVo("Designation",2,8,0,true,true); 
	                      excelBillReportHeading[5]=new ColumnVo("DDO Code",2,9,0,true,true);
	                      excelBillReportHeading[6]=new ColumnVo("DDO Office",2,9,0,true,true);
	                     
	  
	               columnvalue.add(excelBillReportHeading); 
	               mainValue.add(lLstArrOuter);
	               logger.info("hello"+lSBOut.toString()); 
	               logger.info("hello"+exportTo);
	               StringBuffer lSbHeader= new StringBuffer();
	               lSbHeader.append("Table showing details of Employees");
	               lSbHeader.append("\n"); 
	               lSbHeader.append("of\n"); 
					/*
					 * if(obj1[2]!= null) lSbHeader.append(obj1[2].toString()+",\n"); else
					 * lSbHeader.append("N.A,\n"); if(obj1[0]!= null)
					 * lSbHeader.append(obj1[0].toString()+",\n"); else
					 * lSbHeader.append("N.A."+",\n"); if(obj1[4]!= null &&
					 * !obj1[4].toString().equals("Not Applicable") )
					 * lSbHeader.append("Taluka : "+obj1[4].toString()+",\n"); if(obj1[5]!= null &&
					 * !obj1[5].toString().equals("Not Applicable"))
					 * lSbHeader.append("District : "+obj1[5].toString()+",\n"); if(obj1[6]!= null)
					 * lSbHeader = lSbHeader.append(obj1[6].toString());
					 * logger.info("lSbHeader "+lSbHeader.toString()); String lStrFooter= ""; int
					 * nMode= 131;
					 */
	  
	                        ExcelExportHelper exph = new ExcelExportHelper(); 
	                        byte[] aryOut = null;
	                        String[] param = new String[1]; 
	                        List Headerdata = new ArrayList(); 
	                        List footerdata = new ArrayList(); 
	                        param[0] = "";
	  
	                        Headerdata.add(lSbHeader.toString()); 
	                        StringBuffer footer =new StringBuffer(); 
							/*
							 * footer =
							 * footer.append("This is to certify that the information of employees of ");
							 * if(obj1[2]!= null) footer = footer.append(obj1[2].toString()); else footer =
							 * footer.append("N.A"); footer = footer.
							 * append(" entered in the system and shown above is correct and accurate as per the records of this Institution. I understand that I am responsible for the accuracy and correctness of the above information"
							 * ); footer = footer.append("\n");
							 * 
							 * if(obj1[3]!= null) footer = footer.append(obj1[3].toString()+","); else
							 * footer = footer.append("N.A"); footer = footer.append("\n");
							 * 
							 * if(obj1[0]!= null) footer = footer.append(obj1[0].toString()+",\n"); else
							 * footer = footer.append("N.A"); if(obj1[4]!= null &&
							 * !obj1[4].toString().equals("Not Applicable")) footer =
							 * footer.append("Taluka : "+obj1[4].toString()+",\n"); if(obj1[5]!= null &&
							 * !obj1[5].toString().equals("Not Applicable")) footer =
							 * footer.append("District : "+obj1[5].toString()+",\n"); footer =
							 * footer.append(obj1[6].toString()); footerdata.add(footer.toString());
							 */
	  
	                           aryOut = exph.getExcelReportPrintFormat(mainValue, columnvalue, param,Headerdata, footerdata); 
	                           String lStrExportTo = DBConstants.DIS_EXCELFILE; 
	                           Map lDetailMap = new HashMap(); 
	                           if((DBConstants.DIS_ONSCREEN).equals(lStrExportTo)) {
	                              lDetailMap.put(DBConstants.DIS_ONSCREEN, aryOut); 
	                              } else if ((DBConstants.DIS_EXCELFILE).equals(lStrExportTo)) {
	                              lDetailMap.put(DBConstants.DIS_EXCELFILE, aryOut); 
	                              }
	                               objectArgs.put("FileName", "Vacant Post list");
	                               objExport.getExportData(resultObject, lStrExportTo, objectArgs, lDetailMap,false); 
	                               resultObject.setResultValue(objectArgs);
	  
	  
	  } catch(Exception e){ 
		  resultObject = new ResultObject(ErrorConstants.ERROR);
	      resultObject.setResultCode(-1); 
	      resultObject.setViewName("errorPage");
	      logger.info(e); 
	      }
	  
	  
	  return resultObject;
	  
	  }
	 

}
