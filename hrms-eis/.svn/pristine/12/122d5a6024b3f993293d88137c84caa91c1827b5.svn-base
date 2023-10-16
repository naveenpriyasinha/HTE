package com.tcs.sgv.eis.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.fileupload.AttachmentHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.util.ReadExcelFile;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;

public class LoanUploadServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg=0;
	public ResultObject updateLoanDataByExcel(Map objectArgs){
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		logger.info("inside Update non Gov.Data------------>");
		try
		{
			long deducAmount=0;
			HttpServletRequest request = (HttpServletRequest)objectArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			String key = (String) request.getSession().getAttribute("name") +"uploadLoan" + request.getSession().getId();
			logger.info("generate map method key"+key);

			logger.info( "Coming into the method insertExcelData for inserting Excel Data" );

			String lStrStatus = null;
			ReadExcelFile readExcelObj = null;
			ArrayList lArrWorkSheet = null;
			ArrayList lArrDataList = new ArrayList();
	        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

			try{

				readExcelObj = new ReadExcelFile();
				AttachmentHelper attachmentHelper = new AttachmentHelper();
				List validationDetsList = new ArrayList(); 
				Map fileItemArrayListMap = attachmentHelper.fileItemArrayListMap;

				String File = "D:/UploadQuarterDetails.xls";
				//Getting Number Of WorkSheets in the Excel
				FileItem fileItem = null;
				ArrayList attachmentList = (ArrayList) fileItemArrayListMap.get(key);
				logger.info("generate map method fileItemArrayListMap"+fileItemArrayListMap);
				
				HrLoanEmpDtls empLoanVO = new HrLoanEmpDtls();
				HrLoanEmpPrinRecoverDtls loanRecvVO = new HrLoanEmpPrinRecoverDtls();
				HrLoanEmpIntRecoverDtls intLoanRecvVO = new HrLoanEmpIntRecoverDtls();
				
				long empId=0;
				long loanTypeId=0;
				long loanPrinAmt=0;
				long loanIntAmt=0;
				long loanPrinInstNo=0;
				long loanIntInstNo=0;
				long loanPrinEmiAmt=0;
				long loanIntEmiAmt=0;
				String loanAcNo="";
				String loanSancOrderNo="";
				String lnDate;
				Date loanDate=new Date(); 
				Integer loanActivateFlag=0;
				
				long loanPrinRecovAmt=0;
				long loanPrinRecovInt=0;
				long loanIntRecovAmt=0;
				long loanIntRecovInt=0;
				Map mp = objectArgs;
				long loanTypeCheckAmount=0;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(attachmentList!=null && attachmentList.size()>0)
				{
					logger.info("attachment list is not zero"+attachmentList.size());
					Iterator attachmentListIterator = attachmentList.listIterator();

					while (attachmentListIterator.hasNext())
					{
						fileItem = (FileItem) attachmentListIterator.next();
						File Filename = new File(fileItem.getName());
						lArrWorkSheet =  readExcelObj.getExcelData(Filename);
						//Getting Number Of Rows in the particular Worksheet of Excel
						ArrayList lArrRowsList = ( ArrayList )lArrWorkSheet.get(0);
						logger.info( "The Size of the Rows are ==="+lArrRowsList.size() );
						if( lArrRowsList!=null && lArrRowsList.size()>1 )
							for( int j=1;j<lArrRowsList.size(); j++)
							{
								logger.info( "The Sub Contents of the ArrayList are ==="+lArrRowsList.get(j) );
								//Getting Particular Row for the Worksheet of the Excel
								ArrayList lArrSingleRowList = ( ArrayList )lArrRowsList.get(j);
								HrLoanAdvMst loanAdvMst =new HrLoanAdvMst();
								HrEisEmpMst HrEisEmpMst=new HrEisEmpMst();
								empId=(lArrSingleRowList.get(0)!=null?Long.parseLong(lArrSingleRowList.get(0).toString()):0);
								EmpLoanDAOImpl empLoanDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
								int loanRowCounter=3;
								logger.info("empId====in excel sheet====>"+empId);
								if(empId>0)
								{
									
									//loan upload code start
									
									//Gpf Advance
									loanTypeCheckAmount=lArrSingleRowList.get(3)!=null?Long.parseLong(lArrSingleRowList.get(3).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{

						        		loanTypeId=Long.parseLong(resourceBundle.getString("gpf").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
								        		 empLoanVO = new HrLoanEmpDtls();
								 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
												 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();
								        		loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(4)!=null?Long.parseLong(lArrSingleRowList.get(4).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(5)!=null?Long.parseLong(lArrSingleRowList.get(5).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(6)!=null?Long.parseLong(lArrSingleRowList.get(6).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(7)!=null?Long.parseLong(lArrSingleRowList.get(7).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(8)!=null?lArrSingleRowList.get(8).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(9)!=null?lArrSingleRowList.get(9).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }
						        	
									
									//festival Advance
									loanTypeCheckAmount=lArrSingleRowList.get(10)!=null?Long.parseLong(lArrSingleRowList.get(10).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("festival").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

												loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(11)!=null?Long.parseLong(lArrSingleRowList.get(11).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(12)!=null?Long.parseLong(lArrSingleRowList.get(12).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(13)!=null?Long.parseLong(lArrSingleRowList.get(13).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(14)!=null?Long.parseLong(lArrSingleRowList.get(14).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(15)!=null?lArrSingleRowList.get(15).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(16)!=null?lArrSingleRowList.get(16).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }


									//foodGrain Advance
									loanTypeCheckAmount=lArrSingleRowList.get(17)!=null?Long.parseLong(lArrSingleRowList.get(17).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("foodGrain").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

								        		loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(18)!=null?Long.parseLong(lArrSingleRowList.get(18).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(19)!=null?Long.parseLong(lArrSingleRowList.get(19).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(20)!=null?Long.parseLong(lArrSingleRowList.get(20).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(21)!=null?Long.parseLong(lArrSingleRowList.get(21).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(22)!=null?lArrSingleRowList.get(22).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(23)!=null?lArrSingleRowList.get(23).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }
									

									//car Loan principal type
									loanTypeCheckAmount=lArrSingleRowList.get(24)!=null?Long.parseLong(lArrSingleRowList.get(24).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("car").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

								        		loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(25)!=null?Long.parseLong(lArrSingleRowList.get(25).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(26)!=null?Long.parseLong(lArrSingleRowList.get(26).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(27)!=null?Long.parseLong(lArrSingleRowList.get(27).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(28)!=null?Long.parseLong(lArrSingleRowList.get(28).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(29)!=null?lArrSingleRowList.get(29).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(30)!=null?lArrSingleRowList.get(30).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }
						        	
									//car Loan Intrest type
									loanTypeCheckAmount=lArrSingleRowList.get(31)!=null?Long.parseLong(lArrSingleRowList.get(31).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("car").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

												loanIntAmt=loanTypeCheckAmount;
												loanIntEmiAmt=lArrSingleRowList.get(32)!=null?Long.parseLong(lArrSingleRowList.get(32).toString()):0;//for emi amount
												loanIntRecovAmt=lArrSingleRowList.get(33)!=null?Long.parseLong(lArrSingleRowList.get(33).toString()):0;//for Recover amount
												loanIntRecovInt=lArrSingleRowList.get(34)!=null?Long.parseLong(lArrSingleRowList.get(34).toString()):0;//for Recover Installment
												loanIntInstNo=lArrSingleRowList.get(35)!=null?Long.parseLong(lArrSingleRowList.get(35).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(36)!=null?lArrSingleRowList.get(36).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(37)!=null?lArrSingleRowList.get(37).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								     }
						        	
									

									//scotter Loan principal type
									loanTypeCheckAmount=lArrSingleRowList.get(38)!=null?Long.parseLong(lArrSingleRowList.get(38).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("scooter").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

								        		loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(39)!=null?Long.parseLong(lArrSingleRowList.get(39).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(40)!=null?Long.parseLong(lArrSingleRowList.get(40).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(41)!=null?Long.parseLong(lArrSingleRowList.get(41).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(42)!=null?Long.parseLong(lArrSingleRowList.get(42).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(43)!=null?lArrSingleRowList.get(43).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(44)!=null?lArrSingleRowList.get(44).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }
						        	
									//scotter Loan Intrest type
									loanTypeCheckAmount=lArrSingleRowList.get(45)!=null?Long.parseLong(lArrSingleRowList.get(45).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("scooter").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

												loanIntAmt=loanTypeCheckAmount;
												loanIntEmiAmt=lArrSingleRowList.get(46)!=null?Long.parseLong(lArrSingleRowList.get(46).toString()):0;//for emi amount
												loanIntRecovAmt=lArrSingleRowList.get(47)!=null?Long.parseLong(lArrSingleRowList.get(47).toString()):0;//for Recover amount
												loanIntRecovInt=lArrSingleRowList.get(48)!=null?Long.parseLong(lArrSingleRowList.get(48).toString()):0;//for Recover Installment
												loanIntInstNo=lArrSingleRowList.get(49)!=null?Long.parseLong(lArrSingleRowList.get(49).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(50)!=null?lArrSingleRowList.get(50).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(51)!=null?lArrSingleRowList.get(51).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								     }

									

									//Moped Loan principal type
									loanTypeCheckAmount=lArrSingleRowList.get(52)!=null?Long.parseLong(lArrSingleRowList.get(52).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("moped").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

								        		loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(53)!=null?Long.parseLong(lArrSingleRowList.get(53).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(54)!=null?Long.parseLong(lArrSingleRowList.get(54).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(55)!=null?Long.parseLong(lArrSingleRowList.get(55).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(56)!=null?Long.parseLong(lArrSingleRowList.get(56).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(57)!=null?lArrSingleRowList.get(57).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(58)!=null?lArrSingleRowList.get(58).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }
						        	
									//moped Loan Intrest type
									loanTypeCheckAmount=lArrSingleRowList.get(59)!=null?Long.parseLong(lArrSingleRowList.get(59).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("moped").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

												loanIntAmt=loanTypeCheckAmount;
												loanIntEmiAmt=lArrSingleRowList.get(60)!=null?Long.parseLong(lArrSingleRowList.get(60).toString()):0;//for emi amount
												loanIntRecovAmt=lArrSingleRowList.get(61)!=null?Long.parseLong(lArrSingleRowList.get(61).toString()):0;//for Recover amount
												loanIntRecovInt=lArrSingleRowList.get(62)!=null?Long.parseLong(lArrSingleRowList.get(62).toString()):0;//for Recover Installment
												loanIntInstNo=lArrSingleRowList.get(63)!=null?Long.parseLong(lArrSingleRowList.get(63).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(64)!=null?lArrSingleRowList.get(64).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(65)!=null?lArrSingleRowList.get(65).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								     }

									

									//hba Loan principal type
									loanTypeCheckAmount=lArrSingleRowList.get(66)!=null?Long.parseLong(lArrSingleRowList.get(66).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("hba").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

								        		loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(67)!=null?Long.parseLong(lArrSingleRowList.get(67).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(68)!=null?Long.parseLong(lArrSingleRowList.get(68).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(69)!=null?Long.parseLong(lArrSingleRowList.get(69).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(70)!=null?Long.parseLong(lArrSingleRowList.get(70).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(71)!=null?lArrSingleRowList.get(71).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(72)!=null?lArrSingleRowList.get(72).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }
						        	
									//hba Loan Intrest type
									loanTypeCheckAmount=lArrSingleRowList.get(73)!=null?Long.parseLong(lArrSingleRowList.get(73).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("hba").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

												loanIntAmt=loanTypeCheckAmount;
												loanIntEmiAmt=lArrSingleRowList.get(74)!=null?Long.parseLong(lArrSingleRowList.get(74).toString()):0;//for emi amount
												loanIntRecovAmt=lArrSingleRowList.get(75)!=null?Long.parseLong(lArrSingleRowList.get(75).toString()):0;//for Recover amount
												loanIntRecovInt=lArrSingleRowList.get(76)!=null?Long.parseLong(lArrSingleRowList.get(76).toString()):0;//for Recover Installment
												loanIntInstNo=lArrSingleRowList.get(77)!=null?Long.parseLong(lArrSingleRowList.get(77).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(78)!=null?lArrSingleRowList.get(78).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(79)!=null?lArrSingleRowList.get(79).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								     }
						        	

									//fan Advance
									loanTypeCheckAmount=lArrSingleRowList.get(80)!=null?Long.parseLong(lArrSingleRowList.get(80).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("fan").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

								        		loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(81)!=null?Long.parseLong(lArrSingleRowList.get(81).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(82)!=null?Long.parseLong(lArrSingleRowList.get(82).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(83)!=null?Long.parseLong(lArrSingleRowList.get(83).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(84)!=null?Long.parseLong(lArrSingleRowList.get(84).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(85)!=null?lArrSingleRowList.get(85).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(86)!=null?lArrSingleRowList.get(86).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }

									//OCA_Cycle_Loan Advance
									loanTypeCheckAmount=lArrSingleRowList.get(87)!=null?Long.parseLong(lArrSingleRowList.get(87).toString()):0;
									logger.info("loanTypeId====in excel sheet====>"+loanTypeCheckAmount);

						        	if(loanTypeCheckAmount>0)
						        	{
						        		loanTypeId=Long.parseLong(resourceBundle.getString("OCA_Cycle_Loan").toString());//set Loan type According to excel sheet
										List<HrLoanEmpDtls> hrLoanEmpDtlsList = empLoanDao.getEmpLoanDetail(empId, loanTypeId+"");
										if(hrLoanEmpDtlsList.size()==0)
										{
							        		 empLoanVO = new HrLoanEmpDtls();
							 				 loanRecvVO = new HrLoanEmpPrinRecoverDtls();
											 intLoanRecvVO = new HrLoanEmpIntRecoverDtls();

								        		loanPrinAmt=loanTypeCheckAmount;
								        		loanPrinEmiAmt=lArrSingleRowList.get(88)!=null?Long.parseLong(lArrSingleRowList.get(88).toString()):0;//for emi amount
								        		loanPrinRecovAmt=lArrSingleRowList.get(89)!=null?Long.parseLong(lArrSingleRowList.get(89).toString()):0;//for Recover amount
								        		loanPrinRecovInt=lArrSingleRowList.get(90)!=null?Long.parseLong(lArrSingleRowList.get(90).toString()):0;//for Recover Installment
								        		loanPrinInstNo=lArrSingleRowList.get(91)!=null?Long.parseLong(lArrSingleRowList.get(91).toString()):0;//for Total Installment
								        		lnDate=(lArrSingleRowList.get(92)!=null?lArrSingleRowList.get(92).toString():"");//for Loan Start Date
								        		loanAcNo=(lArrSingleRowList.get(93)!=null?lArrSingleRowList.get(93).toString():"");//for Loan Account Date
								        		if(lnDate!=null && lnDate!="")
								       				loanDate=sdf.parse(lnDate);
								        		empLoanVO.setLoanDate(loanDate);
								        		empLoanVO.setLoanPrinAmt(loanPrinAmt);
								                empLoanVO.setLoanInterestAmt(loanIntAmt);
								                empLoanVO.setLoanPrinInstNo(loanPrinInstNo);
								                empLoanVO.setLoanIntInstNo(loanIntInstNo);
								                //empLoanVO.setLoanEmiAmt(loanEMI);
								                empLoanVO.setLoanPrinEmiAmt(loanPrinEmiAmt);
								                empLoanVO.setLoanIntEmiAmt(loanIntEmiAmt);
								                empLoanVO.setLoanAccountNo(loanAcNo);
								                empLoanVO.setLoanSancOrderNo(loanSancOrderNo);
								                empLoanVO.setLoanActivateFlag(loanActivateFlag);
								        		loanRecvVO.setTotalRecoveredAmt(loanPrinRecovAmt);
								        		loanRecvVO.setTotalRecoveredInst(loanPrinRecovInt);
								        		intLoanRecvVO.setTotalRecoveredInt(loanIntRecovAmt);
								        		intLoanRecvVO.setTotalRecoveredIntInst(loanIntRecovInt);
												objectArgs.put("empLoan", empLoanVO);
												objectArgs.put("loanIntRecv", intLoanRecvVO);
												objectArgs.put("loanRecv", loanRecvVO);
												objectArgs.put("edit","N");
												objectArgs.put("empId",empId);
												objectArgs.put("loanTypeId",loanTypeId);
												logger.info("before execution of Loan service");
												EmpLoanService empLoanService=new EmpLoanService();
												empLoanService.insertEmpLoanDtls(objectArgs);
												logger.info("before execution of Loan service");
										}
								       }

								}//end emp id condition
								

						}

					}
				}


				
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				logger.info("U are out of Loan Excel Upload Service");		

			}
			catch( Exception e )
			{
				logger.info("The error is:-");
				logger.error("Error is: "+ e.getMessage());
				logger.info("There is some error at editting or inserting time");
				//Map result = new HashMap();
				objectArgs.put("MESSAGECODE",300001);
				resultObject.setResultValue(objectArgs);
				resultObject.setThrowable(e);
				resultObject.setResultCode(-1);
				resultObject.setViewName("errorPage");			}
		}
		catch(Exception e)
		{
			logger.info("The error is:-");
			logger.error("Error is: "+ e.getMessage());
			logger.info("There is some error at editting or inserting time");
			//Map result = new HashMap();
			objectArgs.put("MESSAGECODE",300001);
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		resultObject.setViewName("UploadLoanExcel");
		resultObject.setResultValue(objectArgs);
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		objectArgs.put("MESSAGECODE",300005);
		
		return resultObject;
	}
}
