package com.tcs.sgv.eis.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.MapEmpWithArrearDAO;
import com.tcs.sgv.eis.dao.MapEmpWithArrearDAOImpl;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAO;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PaybillRegMpgDAOImpl;
import com.tcs.sgv.eis.dao.SalRevMstDAO;
import com.tcs.sgv.eis.dao.SalRevMstDAOImpl;
import com.tcs.sgv.eis.util.ReadExcelFile;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayArrearBillpostMpg;
import com.tcs.sgv.eis.valueobject.HrPayArrearCompoAmtMpg;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.pension.dao.TrnBillRegisterDAOImpl;
import com.tcs.sgv.user.dao.UserPostDAO;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;

public class MapEmpWithArrearServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle payrollConstant = ResourceBundle.getBundle("resources.Payroll");;
	Date sysdate = new Date();

	public ResultObject fillMapEmpWithArrearScreen(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			logger.info("************Inside fillMapEmpWithArrearScreen*****************");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			logger.info("locId::"+locId);
			CmnLocationMst cmnLocationMst= (CmnLocationMst) loginDetailsMap.get("locationVO");
			String locationCode = cmnLocationMst.getLocationCode();
			CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
			cmnLanguageMst.setLangId(langId);
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			String dt = sdf.format(currDate);
			int finYrId = finYearDAOImpl.getFinYearId(dt);
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());

			//Getting Bill No List
			NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());			
			locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
			boolean isBillDefined = payBillDAO.isBillsDefined(locationCode,langId);
			ArrayList billList = new ArrayList();  
			List billNoList = new ArrayList();
			if(isBillDefined)
			{
				billNoList = newEmpConfigDAO.getBillNoData(finYrId,locId);
				logger.info("The size of billNoList is---->"+billNoList.size());

				for(Iterator itr=billNoList.iterator();itr.hasNext();)
				{
					HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
					Object[] row = (Object[])itr.next();
					String billNo = row[1].toString();
					String billHeadId = row[0].toString();
					//billNoCustomVO.setBillId(Long.parseLong(billNo));
					billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
					billList.add(billNoCustomVO);
				}
			}			
			//
			objectArgs.put("billNoList", billList);
			
			//Getting Designations List
			List arDesignationVO = new ArrayList();			
			UserPostDAO userDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
			arDesignationVO = userDAO.getAllDesgMasterData(langId);	        
			logger.info(" arDesignationVOList.size::"+arDesignationVO.size());
			objectArgs.put("arDesignationVO", arDesignationVO);			
			//Getting Designations ends
			
			//Getting Payroll Components 
			SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl (HrPaySalRevMst.class,serv.getSessionFactory());//daoimpl object
			List <RltBillTypeEdp> rltBillList = salRevMstDAO.getRltBillTypeEdpData();	
			objectArgs.put("rltBillList", rltBillList);

			// Ends
			MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
			List<HrPaySalRevMst> salRevMstVOList = new ArrayList();
			salRevMstVOList = mapEmpWithArrearDAO.getSalRevOrderData(locId);
			
			objectArgs.put("salRevMstVOList", salRevMstVOList);
			
			Map voToService = (Map)objectArgs.get("voToServiceMap");					
			long lOrderId=0;		
			String flag="N";			
			if(voToService != null && voToService.get("lOrderId")!=null)					
				lOrderId=Long.parseLong(voToService.get("lOrderId").toString());
			if(voToService != null && voToService.get("flag")!=null)					
				flag=voToService.get("flag").toString();
			
			logger.info("lOrderId::"+lOrderId);
			logger.info("flag::"+flag);
			objectArgs.put("lOrderId", lOrderId);
			objectArgs.put("flag", flag);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("mapEmpWithArrear");			
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in fillMapEmpWithArrearScreen service******");
			logger.error("Error In fillMapEmpWithArrearScreen service: " , ex);
			ex.printStackTrace();
		}
		logger.info("fillMapEmpWithArrearScreen ended***********************");
		return resultObject;
	}

	public ResultObject getEmployeeDetailsToMapWithArrear(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside getEmployeeDetailsToMapWithArrear*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());

				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				
				Map voToService = (Map)objServiceArgs.get("voToServiceMap");					
				String lBillSubHeadId="0";		
				long lDsgnId=0;		
				String orderEffDate="";
				String orderEffEndDate="";
				String compoTypeIds="";
				String rdoBtnSearchFlg="";
				
				boolean crtExcelFlag=false;
				StringBuffer propertyList = new StringBuffer();
				List dtlsList = new ArrayList();
				SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
				Object[] objDtlsLst = null;
				
				if(voToService.get("lBillSubHeadId")!=null)					
					lBillSubHeadId=voToService.get("lBillSubHeadId").toString();
				if(voToService.get("lDsgnId")!=null)
					lDsgnId=Long.parseLong(voToService.get("lDsgnId").toString());
				if(voToService.get("orderEffDate")!=null)
					orderEffDate=voToService.get("orderEffDate").toString();
				if(voToService.get("orderEffEndDate")!=null)
					orderEffEndDate=voToService.get("orderEffEndDate").toString();
				if(voToService.get("compoTypeIds")!=null)
					compoTypeIds=voToService.get("compoTypeIds").toString();
				if(voToService.get("rdoBtnSearchFlg")!=null)
					rdoBtnSearchFlg=voToService.get("rdoBtnSearchFlg").toString();
				
				
				String cmpValueArray[]=compoTypeIds.split(",");
				
				if("1".equals(rdoBtnSearchFlg))
				{
					dtlsList = mapEmpWithArrearDAO.getEmployeeByBillNoDesig(lBillSubHeadId, lDsgnId,locId,orderEffDate,orderEffEndDate);
				}
				else
				{
					
					dtlsList = mapEmpWithArrearDAO.getEmpListIndependentOfPaybill(locId,lBillSubHeadId,orderEffDate,orderEffEndDate,0);
				}
				logger.info("dtlsList size in getEmployeeDetailsToMapWithArrear service is:::::::::"+dtlsList.size());
				long empId=0;
				String empName="";
				long empPostId=0;
				String empClass="";
				String empDesig="";
				String psrNo="";
				Date sDate = new Date();
				Date eDate = new Date();
				if(dtlsList!=null && dtlsList.size()!=0)
				{
					for (int i=0;i<dtlsList.size();i++)
					{	
						objDtlsLst = (Object[]) dtlsList.get(i);
						empId = objDtlsLst[0]!= null ? Long.parseLong(objDtlsLst[0].toString()) : Long.parseLong("0");						
						empName = objDtlsLst[1]!= null ? objDtlsLst[1].toString() : "";											
						empPostId= objDtlsLst[2]!= null ? Long.parseLong(objDtlsLst[2].toString()) : Long.parseLong("0");
						empClass=objDtlsLst[3]!= null ? objDtlsLst[3].toString() : "";
						empDesig=objDtlsLst[4]!= null ? objDtlsLst[4].toString() : "";
						psrNo=objDtlsLst[5]!= null ? objDtlsLst[5].toString() : "";
						propertyList.append("<empDetails>");   	
						propertyList.append("<empId>").append("<![CDATA[").append(empId).append("]]>").append("</empId>");
						propertyList.append("<empName>").append("<![CDATA[").append(empName).append("(").append(empClass);
						propertyList.append(",").append(empDesig).append(",").append(psrNo).append(")").append("]]>").append("</empName>");
						propertyList.append("<empPostId>").append("<![CDATA[").append(empPostId).append("]]>").append("</empPostId>");
						propertyList.append("</empDetails>");						
					}
				}
				logger.info("propertyList::"+propertyList);
//				/////////////////////////////////////////
				if(voToService.get("crtExcelFlag")!=null)
					crtExcelFlag=Boolean.parseBoolean(voToService.get("crtExcelFlag").toString());
				logger.info("crtExcelFlag:::"+crtExcelFlag);
			
				ArrayList dataList =new ArrayList();				
				ArrayList rowList =new ArrayList();
				File excelFile=null;
				byte[] lBytes = null;
				long basic=0;
				if(crtExcelFlag==true)
				{
					if(dtlsList!=null && dtlsList.size()!=0)
					{
						rowList.add("Sr. No");
						rowList.add("Post Id");
						rowList.add("PSR No");
						rowList.add("Emp Name");
						rowList.add("Dsgn");
						rowList.add("Basic");
						for(int j=1;j<cmpValueArray.length;j++)
						{
							rowList.add(cmpValueArray[j]);	
						}
						
						dataList.add(rowList);	
						for (int i=0;i<dtlsList.size();i++)
						{	
							rowList=new ArrayList();
							objDtlsLst = (Object[]) dtlsList.get(i);
							empId = objDtlsLst[0]!= null ? Long.parseLong(objDtlsLst[0].toString()) : Long.parseLong("0");						
							empName = objDtlsLst[1]!= null ? objDtlsLst[1].toString() : "";											
							empPostId= objDtlsLst[2]!= null ? Long.parseLong(objDtlsLst[2].toString()) : Long.parseLong("0");
							empClass=objDtlsLst[3]!= null ? objDtlsLst[3].toString() : "";
							empDesig=objDtlsLst[4]!= null ? objDtlsLst[4].toString() : "";
							psrNo=objDtlsLst[5]!= null ? objDtlsLst[5].toString() : "";
							basic=objDtlsLst[6]!= null ? Long.parseLong(objDtlsLst[6].toString()) : Long.parseLong("0");
							//logger.info("empId:"+empId+"empName:"+empName+"empPostId:"+empPostId+"empId:"+empClass+"empClass:"+empDesig);
							rowList.add(i+1);							
							if(objDtlsLst[2]!=null && !"".equals(objDtlsLst[2].toString()))
								rowList.add(empPostId+"R"+empId);
							if(objDtlsLst[5]!=null && !"".equals(objDtlsLst[5].toString()))
								rowList.add(psrNo);
							if(objDtlsLst[1]!=null && !"".equals(objDtlsLst[1].toString()))
								rowList.add(objDtlsLst[1].toString());
							
							if(objDtlsLst[3]!=null && !"".equals(objDtlsLst[3].toString()))
								empClass = objDtlsLst[3].toString();
							if(objDtlsLst[4]!=null && !"".equals(objDtlsLst[4].toString()))
								rowList.add(empDesig+"("+empClass+")");
							if(objDtlsLst[6]!=null && !"".equals(objDtlsLst[6].toString()))
								rowList.add(basic);
							dataList.add(rowList);	
						}
					}
					
					logger.info("Excel File Creation started...");
					excelFile = writeApplicationExcelFile(dataList);
					////////////////////////////////////////////////////					
			        lBytes = getBytesFromFile(excelFile);			        
			        
			        propertyList.append("<excelDetails>");   	
						propertyList.append("<lBytes>").append("<![CDATA[").append(lBytes).append("]]>").append("</lBytes>");					
					propertyList.append("</excelDetails>");	
					
					
				    ////////////////////////////////////////////
			
					logger.info("Excel File Creation done...");
				}				
				
				///////////////////////////////////////////////
				if(crtExcelFlag==true)
				{
					HttpServletResponse response = (HttpServletResponse)objServiceArgs.get("responseObj");
					logger.info("lBytes::"+lBytes);
					objServiceArgs.put("buteArray", lBytes);
					response.addHeader("Content-disposition", "attachment; filename=Export_ArrearEmpList.xls");
					resultObject.setResultCode(ErrorConstants.SUCCESS);
					resultObject.setResultValue(objServiceArgs);
					resultObject.setViewName("viewAttachment");
				}
				else
				{
					String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
					objServiceArgs.put("ajaxKey", stateNameIdStr);

					resultObject.setResultCode(ErrorConstants.SUCCESS);
					resultObject.setResultValue(objServiceArgs);
					resultObject.setViewName("ajaxData");
				}
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getEmployeeDetailsToMapWithArrear service******");
			logger.error("Error In getEmployeeDetailsToMapWithArrear service: " , ex);
			ex.printStackTrace();
		}
		logger.info("getOrderDetails ended***********************");
		return resultObject;
	}
	
	public ResultObject saveMapEmpWithArrearData(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside saveMapEmpWithArrearData*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMst cmnLocationMst= (CmnLocationMst) loginDetailsMap.get("locationVO");
				String locationCode = cmnLocationMst.getLocationCode();
				
				long loggedInUser = Long.parseLong(loginDetailsMap.get("userId").toString());			
				OrgUserMst loggedInOrgUserMst = new OrgUserMst();
				loggedInOrgUserMst.setUserId(loggedInUser);
				long loggedInPost = Long.parseLong(loginDetailsMap.get("primaryPostId").toString());			
				OrgPostMst loggedInOrgPostMst=new OrgPostMst();
				loggedInOrgPostMst.setPostId(loggedInPost);	
				MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());

				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();		
				List empArrearDataLst = (ArrayList)objServiceArgs.get("empArrearDataLst");
				List empArrearDatafromExcel=new ArrayList();
				long salRevOrder= (Long)objServiceArgs.get("salRevOrder");
				long empCounter= (Long)objServiceArgs.get("empCounter");
				String groupBillNo= objServiceArgs.get("groupBillNo").toString();
				String staticAmtOrNot = objServiceArgs.get("staticAmtOrNot").toString();
				String uploadExcelFlag=objServiceArgs.get("uploadExcelFlag").toString();
				String rdoBtnSearchFlg=objServiceArgs.get("rdoBtnSearchFlg").toString();
				String orderEffDate=objServiceArgs.get("orderEffDate").toString();
				String orderEffEndDate=objServiceArgs.get("orderEffEndDate").toString();
				String lBillSubHeadId=objServiceArgs.get("lBillSubHeadId").toString();
				Date approveRejectDate = sysdate;
				BigDecimal trnAmt = new BigDecimal(0.0);
				Date trnCrtDate = new Date();
				trnCrtDate.setYear(2005-1900);
				GenericDaoHibernateImpl hrEisEmpMstDaoImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
				hrEisEmpMstDaoImpl.setSessionFactory(serv.getSessionFactory());
				OtherDetailDAOImpl otherDtlsDaoImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
				OrgDdoMst orgDDOMst = new OrgDdoMst();
				orgDDOMst  = mapEmpWithArrearDAO.getOrgDDOMstVO(loggedInPost);
				
				PaybillRegMpgDAOImpl pblRegMpgDAO = new PaybillRegMpgDAOImpl(PaybillBillregMpg.class,serv.getSessionFactory());
				long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());				
				CmnDatabaseMst cmnDatabaseMst= new CmnDatabaseMst();
				cmnDatabaseMst.setDbId(dbId);
				long hrEmplID=0;//To Be Set in HrPayArrearBillPostMpg Table
				long hrEmpId=0;//TO be set in ArrearBill Table
				long orgEmpId=0;
				long otherId=0;
				long billSubheadCode=0;
				long phmBillNo=0;
				long lGrpBillNo = StringUtility.convertToLong(groupBillNo);
				long psrNo=0;
				GenerateArrearBillService genArrearBillSrvc = new GenerateArrearBillService();				
				PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				PaybillHeadMpgDAOImpl pblHeadMpgDAO = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
				TrnBillRegisterDAOImpl trnBillDAO = new TrnBillRegisterDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
				///////////////////////////////////
				String empArrear = "";			
				String tempEmpAerrear="";
				StringTokenizer strTokenizer = null;				
				String[] strTempArr=null;
				String edpId="";
				
				String edpCodeValue="";
				OrgPostMst orgPostMstForArrear = new OrgPostMst();
				HrPayArrearBillpostMpg arrearBillpostMpg = new HrPayArrearBillpostMpg();
				HrPayArrearCompoAmtMpg arrearCompoAmtMpg = new HrPayArrearCompoAmtMpg();
				RltBillTypeEdp rltBillTypeEdp = new RltBillTypeEdp();
				
				HrPaySalRevMst salRevMst = new HrPaySalRevMst();				
				
				GenericDaoHibernateImpl salRevReadDaoImpl = new GenericDaoHibernateImpl(HrPaySalRevMst.class);
				salRevReadDaoImpl.setSessionFactory(serv.getSessionFactory());
				salRevMst =(HrPaySalRevMst)salRevReadDaoImpl.read(salRevOrder);
				cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
				HrPayBillHeadMpg hrPayBillHeadMpg = new HrPayBillHeadMpg();
				hrPayBillHeadMpg.setBillHeadId(lGrpBillNo);
				GenericDaoHibernateImpl arrearBillpostMpgDaoImpl = new GenericDaoHibernateImpl(HrPayArrearBillpostMpg.class);
				arrearBillpostMpgDaoImpl.setSessionFactory(serv.getSessionFactory());
				GenericDaoHibernateImpl readEdpDaoImpl = new GenericDaoHibernateImpl(RltBillTypeEdp.class);
				readEdpDaoImpl.setSessionFactory(serv.getSessionFactory());
				GenericDaoHibernateImpl crtCompoAmtMpgDaoImpl = new GenericDaoHibernateImpl(HrPayArrearCompoAmtMpg.class);
				crtCompoAmtMpgDaoImpl.setSessionFactory(serv.getSessionFactory());
				

				//Saving Arrear Data Using Excel
				ArrayList attachmentList = (ArrayList) objServiceArgs.get("attachmentList");
				
				
				/////////////////
				
				if(attachmentList != null && attachmentList.size()>0)
				{
					logger.info("Inserting data from Excel!!!!!!!!");
					Iterator attachmentListIterator = attachmentList.listIterator();
					FileItem fileItem = null;
					ReadExcelFile excelData = new ReadExcelFile();
					Workbook workBook;

					while (attachmentListIterator.hasNext())
					{
						fileItem = (FileItem) attachmentListIterator.next();
						workBook = Workbook.getWorkbook(fileItem.getInputStream());

						logger.info("details here "+fileItem.get());
						logger.info("file name"+fileItem.getName().
								substring(fileItem.getName().lastIndexOf("\\") + 1, fileItem.getName().length()));
						logger.info("file name"+fileItem.getFieldName());

						logger.info("iterating while loop.....check workbook and file iterms "+ workBook.getNumberOfSheets());

						List lstPostIds = new ArrayList();	
						List lstCompAmt = new ArrayList();	
						Map mapExcelData = new HashMap();
						int columns=0;
						int rows =0;
						String strCmpAmt="0";
						String strCell="";
						String strPostId="";
						List cmpIdList=new ArrayList();
						long tempTotal=0;
						StringBuffer strBufExcelData=new StringBuffer();
						//Getting worksheet from excel
						for(int i=0;i<1;i++)
						{
							//Getting total rows and columns of excel which is to be used
							Sheet sheet = workBook.getSheet(i);
							columns = sheet.getColumns();
							rows = sheet.getRows();
							
							////////////////For loop to traverse row
							for(int row=1;row<rows;row++)
							{								
								//Getting postId from 1st column of row
								if(sheet.getCell(1, row).getContents()!=null && !sheet.getCell(1, row).getContents().trim().equals("")) 
								{
									strPostId=sheet.getCell(1, row).getContents();									
									strBufExcelData.append(strPostId).append("Z");	//Adding postId in dataList
								}
								//For loop to traverse columns
								//Getting component amount from 1st column of row	
								tempTotal=0;
								for (int k=6;k<columns;k++)
								{		
									if(sheet.getCell(k, 0).getContents()!=null && !sheet.getCell(k, 0).getContents().trim().equals("")) 
									{
										//Getting component Id from header
										strCell=sheet.getCell(k, 0).getContents();		
										if(strCell.lastIndexOf("_")!=-1)
										{
											strBufExcelData.append(strCell.substring(strCell.lastIndexOf("_")+1)).append("E"); //Adding component Id in dataList
										}										
										if(sheet.getCell(k, row).getContents()!=null && !sheet.getCell(k, row).getContents().trim().equals("")) 
										{
											strCmpAmt=sheet.getCell(k, row).getContents();											
											//strBufExcelData.append(strCmpAmt).append("Z");
										}										
										tempTotal=tempTotal+Long.parseLong(strCmpAmt);										
										strBufExcelData.append(strCmpAmt).append("Z");
										strCmpAmt="0";
									}
								}
								
								logger.info("row!!!::"+row);
								logger.info("TempTotal!!!::"+tempTotal);
								strBufExcelData.deleteCharAt(strBufExcelData.lastIndexOf("Z"));
								if(tempTotal!=0)
									empArrearDatafromExcel.add(strBufExcelData.toString());
								logger.info("empArrearDatafromExcel.size::"+empArrearDatafromExcel.size());
								logger.info("strBufExcelData::"+strBufExcelData.toString());		
								strBufExcelData.delete(0, strBufExcelData.length());
								logger.info("strBufExcelData.length::"+strBufExcelData.length());
							}
						}


					}
				}
				//Saving Arrear Data Using Excel Ends
				
				if(!"N".equalsIgnoreCase(uploadExcelFlag))
				{				
					empArrearDataLst=empArrearDatafromExcel;
				}
				else
				{
					logger.info("Inserting data from Screen Inputs!!!!!!!!");
				}
				if (empArrearDataLst!=null)
				{
					for (int i=0;i<empArrearDataLst.size();i++)						
					{
						tempEmpAerrear = empArrearDataLst.get(i).toString();
						strTokenizer = new StringTokenizer(tempEmpAerrear,"Z");						
						logger.info("strTokenizer: " + strTokenizer );

						while( strTokenizer.hasMoreTokens())
						{
							empArrear= strTokenizer.nextToken().toString();
							logger.info("empArrear in Service Method: " + empArrear );
							long arrearBillpostId=0;
							long compoAmtMpgId=0;
							if(!empArrear.contains("E"))
							{
								orgPostMstForArrear = new OrgPostMst();
								strTempArr = new String[2];
								strTempArr = empArrear.split("R");
								orgPostMstForArrear.setPostId(StringUtility.convertToLong(strTempArr[0]));
								arrearBillpostMpg = new HrPayArrearBillpostMpg();
								HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();								
								hrEmplID=StringUtility.convertToLong(strTempArr[1]);
								hrEisEmpMst =(HrEisEmpMst)hrEisEmpMstDaoImpl.read(hrEmplID);
								arrearBillpostId=IDGenerateDelegate.getNextId("hr_pay_arrear_billpost_mpg", objServiceArgs);
								//Setiing arrearBillpostMpg VO
								arrearBillpostMpg.setArrearBillpostId(arrearBillpostId);
								arrearBillpostMpg.setOrgPostMst(orgPostMstForArrear);
								arrearBillpostMpg.setHrPayBillHeadMpg(hrPayBillHeadMpg);
								arrearBillpostMpg.setCmnLocationMst(cmnLocationMst);
								arrearBillpostMpg.setHrPaySalRevMst(salRevMst);
								arrearBillpostMpg.setHrEisEmpMst(hrEisEmpMst);
								arrearBillpostMpgDaoImpl.create(arrearBillpostMpg);
								
								// Record insertion of employees whose paybill is not generated
								/*	We are generating arrear of employees whose paybill is not generated in Payroll System.
								 *  So many parameters like head structure ,gross total,net total, tocken no, tocken date etc 
								 *  are not known. Hence many of the data entry will be static except  month and year.
								 *  
								 */
								if("2".equals(rdoBtnSearchFlg))
								{
									//Getting data to set in various vos
									List tempDataList = new ArrayList();
									
									logger.info("lBillSubHeadId:"+lBillSubHeadId);									
									logger.info("orgPostMstForArrear.getPostId():"+orgPostMstForArrear.getPostId());
									
									
									tempDataList = mapEmpWithArrearDAO.getEmpListIndependentOfPaybill(locId, lBillSubHeadId, orderEffDate, orderEffEndDate, orgPostMstForArrear.getPostId());
									
									//Getting data from query into individual necessary variables
									Object[] objDtlsLst = null;
									if(tempDataList!=null)
									{	
										objDtlsLst=(Object[])tempDataList.get(0);
										
										if(objDtlsLst!=null && objDtlsLst.length>0)
										{													
											if(objDtlsLst[0]!=null && !"".equals(objDtlsLst[0].toString()))
											{
												orgEmpId = Long.parseLong(objDtlsLst[0].toString());									
											}	
											if(objDtlsLst[5]!=null && !"".equals(objDtlsLst[5].toString()))
											{
												psrNo = Long.parseLong(objDtlsLst[5].toString());									
											}	
											if(objDtlsLst[13]!=null && !"".equals(objDtlsLst[13].toString()))
											{
												hrEmpId = Long.parseLong(objDtlsLst[13].toString());												
											}	
											if(objDtlsLst[14]!=null && !"".equals(objDtlsLst[14].toString()))
											{
												billSubheadCode = Long.parseLong(objDtlsLst[14].toString());												
											}	
											if(objDtlsLst[15]!=null && !"".equals(objDtlsLst[15].toString()))
											{
												otherId = Long.parseLong(objDtlsLst[15].toString());												
											}
											if(objDtlsLst[16]!=null && !"".equals(objDtlsLst[16].toString()))
											{
												phmBillNo = Long.parseLong(objDtlsLst[16].toString());												
											}
										}

									}
									HrEisEmpMst eisEmpMst = new HrEisEmpMst();
									eisEmpMst = (HrEisEmpMst)hrEisEmpMstDaoImpl.read(hrEmpId);
									
									HrEisOtherDtls	otherDtlsVO = new HrEisOtherDtls();
									otherDtlsVO = otherDtlsDaoImpl.read(otherId);
									//Getting data from query ends
									
									
									
									// Insertion into Paybill head mpg - conditional
									HrPayBillHeadMpg pblHeadMpgVO = new HrPayBillHeadMpg();
									//pblHeadMpgVO = mapEmpWithArrearDAO.getHrPBLSubheadMpgByBillSubheadId(phmBillNo);
									String strClass = pblHeadMpgVO.getClass_Id();
									String[] strClassArray = strClass.split(",");
									OrgGradeMst ogm = new OrgGradeMst();
									
									SgvaBudsubhdMst sgvaBudSubhdMst = new SgvaBudsubhdMst();
									sgvaBudSubhdMst.setBudsubhdId(71960);	
									long billTypeId = Long.parseLong(payrollConstant.getString("supplPaybillThird"));// 3rd Supplementary bill type id of same month 
									CmnLookupMst billTypeLookup = new CmnLookupMst();
									billTypeLookup.setLookupId(billTypeId);//		Supplementary bill which is not generated till now
									HrPayBillHeadMpg hrPblHeadMpg = new HrPayBillHeadMpg();
									hrPblHeadMpg.setBillHeadId(phmBillNo);
									List monthLst = genArrearBillSrvc.getMonthList(salRevMst.getRevPayOutDate().getMonth()+1, salRevMst.getRevPayOutDate(), salRevMst.getRevFreqMthPaid(), salRevMst.getRevEffcFrmDate());
									
									for(Iterator monthIt = monthLst.iterator();monthIt.hasNext();)
									{
										
										// Insertion into hrPaypaybill
										String monthYear = (String)monthIt.next();
										String[] monthYearArray = monthYear.split("-");

										int paybillMonth = Integer.parseInt(monthYearArray[0]);
										int paybillYear = Integer.parseInt(monthYearArray[1]);

										
										PaybillHeadMpg phmVO = new PaybillHeadMpg();
										long pblId=0;
										HrPayPaybill paybill = new HrPayPaybill();
										boolean isPaybillGenerated=mapEmpWithArrearDAO.checkIsPaybillGenerated(orgPostMstForArrear.getPostId(), locId, paybillMonth, paybillYear);
										if(!isPaybillGenerated)
										{
											phmVO  = mapEmpWithArrearDAO.checkPaybillHeadMpgData(paybillMonth, paybillYear, phmBillNo, billTypeId);	

											
										
										pblId=IDGenerateDelegate.getNextId("hr_pay_paybill", objServiceArgs);
										
										paybill.setId(pblId);
										paybill.setHrEisEmpMst(eisEmpMst);
										paybill.setPsrNo(psrNo);
										paybill.setOrgPostMst(orgPostMstForArrear);
										paybill.setPaybillGrpId(pblId);
										paybill.setTrnCounter(1);
										paybill.setHrEisOtherDtls(otherDtlsVO);
										paybill.setOtherTrnCntr(1);
										paybill.setCreatedDate(sysdate);
										approveRejectDate.setMonth(paybillMonth-1);
										approveRejectDate.setYear(paybillYear-1900);
										paybill.setApproveRejectDate(approveRejectDate);
										long paybillGrpId=0;
										if(phmVO!=null)
											paybillGrpId= phmVO.getHrPayPaybill();	
										else
											paybillGrpId=pblId;
											
										paybill.setPaybillGrpId(paybillGrpId);
										
										payDao.create(paybill);
										logger.info("Paybill data inserted::");
										// Insertion of paybill ends 
										// Insertion into Paybill head mpg
										if(phmVO==null)
										{
											for (int ar=0;ar<strClassArray.length;ar++)
											{

												PaybillHeadMpg pblHeadMpg = new PaybillHeadMpg();
												long phmID=IDGenerateDelegate.getNextId("paybill_head_mpg", objServiceArgs);

												pblHeadMpg.setId(phmID);									
												pblHeadMpg.setHrPayPaybill(paybillGrpId);
												//pblHeadMpg.setSgvaBudsubhdMst(sgvaBudSubhdMst);
												pblHeadMpg.setBillTypeId(billTypeLookup);
												//pblHeadMpg.setBillNo(hrPblHeadMpg);
												pblHeadMpg.setApproveFlag(1);
												pblHeadMpg.setHeadChargable("100");
												pblHeadMpg.setCmnDatabaseMst(cmnDatabaseMst);
												ogm = new OrgGradeMst();
												ogm.setGradeId(Long.parseLong(strClassArray[ar]));
												//pblHeadMpg.setOrgGradeMst(ogm);
												pblHeadMpg.setMonth(paybillMonth);
												pblHeadMpg.setYear(paybillYear);
												pblHeadMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
												pblHeadMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
												pblHeadMpg.setCmnLocationMst(cmnLocationMst);
												pblHeadMpg.setCreatedDate(sysdate);
												pblHeadMpgDAO.create(pblHeadMpg);
												
												logger.info("pblHeadMpg data inserted::");
											}
											// Insertion into Paybill head mpg ends 
											// Insertion into trn_bill_register - conditional
											TrnBillRegister trnBillReg = new TrnBillRegister();
											long trnBillNo = IDGenerateDelegate.getNextId("trn_bill_register", objServiceArgs);
											trnBillReg.setBillNo(trnBillNo);
											trnBillReg.setCurrBillStatus(payrollConstant.getString("trn_bill_status_approved"));
											trnBillReg.setBillDate(DBUtility.getCurrentDateFromDB());
											trnBillReg.setSubjectId(3);
											trnBillReg.setPhyBill(0);
											trnBillReg.setCreatedPostId(loggedInPost);
											trnBillReg.setCreatedUserId(loggedInUser);
											
											trnBillReg.setCreatedDate(trnCrtDate);
											trnBillReg.setLocationCode(locationCode);
											trnBillReg.setTcBill("Regular");
											trnBillReg.setDdoCode(orgDDOMst.getDdoCode());
											trnBillReg.setTokenNum(new Long(0));
											trnBillReg.setUpdatedDate(trnCrtDate);
											trnBillReg.setBillCntrlNo("( "+trnBillNo+"("+paybillMonth+"/"+paybillYear+") )");
											trnBillReg.setBillGrossAmount(trnAmt);
											trnBillReg.setBillNetAmount(trnAmt);
											trnBillReg.setDemandCode("33");
											trnBillReg.setBudmjrHd("2015");
											trnBillReg.setAuditorNetAmount(trnAmt);
											trnBillReg.setGrossInterest(trnAmt);
											trnBillDAO.create(trnBillReg);
											logger.info("trnBillReg data inserted::");
											// Insertion into trn_bill_register ends 

											// Insertion into paybill_reg_mpg - conditional
											PaybillBillregMpg pblRegMpg = new PaybillBillregMpg();
											long pblRegMpgId = IDGenerateDelegate.getNextId("PAYBILL_BILLREG_MPG", objServiceArgs);
											pblRegMpg.setId(pblRegMpgId);
											pblRegMpg.setHrPayPaybill(paybillGrpId);
											pblRegMpg.setTrnBillRegister(trnBillReg);
											pblRegMpg.setBillTypeId(billTypeLookup);
											pblRegMpg.setCmnDatabaseMst(cmnDatabaseMst);
											pblRegMpg.setCmnLocationMst(cmnLocationMst);
											pblRegMpg.setCreatedDate(sysdate);
											pblRegMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
											pblRegMpgDAO.create(pblRegMpg);
											logger.info("pblRegMpg data inserted::");
											// Insertion into paybill_reg_mpg ends
										}
										
										}
										
									}

									
								}
								// Record insertion of employees whose paybill is not generated -- ends
							}

							if(empArrear.contains("E") && "withStatic".equals(staticAmtOrNot))
							{	
								edpId = empArrear.substring(0, empArrear.indexOf("E"));

								edpCodeValue = empArrear.substring(empArrear.indexOf("E")+1, empArrear.length());
								logger.info("edpCode : " + edpId );
								logger.info("edpCodeValue : " + edpCodeValue );
								rltBillTypeEdp = (RltBillTypeEdp)readEdpDaoImpl.read(Long.parseLong(edpId));
								arrearCompoAmtMpg = new HrPayArrearCompoAmtMpg();
								compoAmtMpgId = IDGenerateDelegate.getNextId("hr_pay_arrear_compo_amt_mpg", objServiceArgs);
								arrearCompoAmtMpg.setCompoAmtMpgId(compoAmtMpgId);
								arrearCompoAmtMpg.setArrearBillpostMpg(arrearBillpostMpg);
								arrearCompoAmtMpg.setRltBillTypeEdp(rltBillTypeEdp);
								arrearCompoAmtMpg.setAmount(StringUtility.convertToLong(edpCodeValue));
								crtCompoAmtMpgDaoImpl.create(arrearCompoAmtMpg);

							}

							logger.info("empArrear : " + empArrear);
						}
					}
				}

				//////////////////////////////////////
				
				//Generating JSP again
				CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
				cmnLanguageMst.setLangId(langId);
				FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
				Date currDate = Calendar.getInstance().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
				String dt = sdf.format(currDate);
				int finYrId = finYearDAOImpl.getFinYearId(dt);
				//Getting Bill No List
				NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());
				PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());			
				
				locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
				boolean isBillDefined = payBillDAO.isBillsDefined(locationCode,langId);
				ArrayList billList = new ArrayList();  
				List billNoList = new ArrayList();
				if(isBillDefined)
				{
					billNoList = newEmpConfigDAO.getBillNoData(finYrId,locId);
					logger.info("The size of billNoList is---->"+billNoList.size());

					for(Iterator itr=billNoList.iterator();itr.hasNext();)
					{
						HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
						Object[] row = (Object[])itr.next();
						String billNo = row[1].toString();
						String billHeadId = row[0].toString();
						//billNoCustomVO.setBillId(Long.parseLong(billNo));
						billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
						billList.add(billNoCustomVO);
					}
				}			
				//
				objServiceArgs.put("billNoList", billList);
				
				//Getting Designations List
				List arDesignationVO = new ArrayList();			
				UserPostDAO userDAO =new UserPostDAO(UserPostCustomVO.class,serv.getSessionFactory());
				arDesignationVO = userDAO.getAllDesgMasterData(langId);	        
				logger.info(" arDesignationVOList.size::"+arDesignationVO.size());
				objServiceArgs.put("arDesignationVO", arDesignationVO);			
				//Getting Designations ends
				
				//Getting Payroll Components 
				SalRevMstDAO salRevMstDAO = new SalRevMstDAOImpl (HrPaySalRevMst.class,serv.getSessionFactory());//daoimpl object
				List <RltBillTypeEdp> rltBillList = salRevMstDAO.getRltBillTypeEdpData();	
				objServiceArgs.put("rltBillList", rltBillList);

				// Ends
				
				List<HrPaySalRevMst> salRevMstVOList = new ArrayList();
				salRevMstVOList = mapEmpWithArrearDAO.getSalRevOrderData(locId);
				
				objServiceArgs.put("salRevMstVOList", salRevMstVOList);
				
									
				long lOrderId=0;		
				String flag="N";	
				StringBuffer compoValueList=new StringBuffer();
				if(objServiceArgs != null && objServiceArgs.get("lOrderId")!=null)					
					lOrderId=Long.parseLong(objServiceArgs.get("lOrderId").toString());
				if(objServiceArgs != null && objServiceArgs.get("flag")!=null)					
					flag=objServiceArgs.get("flag").toString();
				if(objServiceArgs != null && objServiceArgs.get("compoValueList")!=null)					
					compoValueList=(StringBuffer)objServiceArgs.get("compoValueList");
				logger.info("lOrderId In Service::"+lOrderId);
				logger.info("flag In Service::"+flag);
				objServiceArgs.put("lOrderId", lOrderId);
				objServiceArgs.put("flag", "99");
				objServiceArgs.put("compoValueList", compoValueList.toString());				
				objServiceArgs.put("MESSAGECODE",300005);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("mapEmpWithArrear");		  
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in saveMapEmpWithArrearData service******");
			logger.error("Error In saveMapEmpWithArrearData service: " , ex);
			ex.printStackTrace();
		}
		logger.info("saveMapEmpWithArrearData ended***********************");
		return resultObject;
	}
	
	public ResultObject getSalRevOrderDetails(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside getSalRevOrderDetails*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());

				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				
				Map voToService = (Map)objServiceArgs.get("voToServiceMap");					
				long lOrderId=0;		
				
				StringBuffer propertyList = new StringBuffer();
				List dtlsList = new ArrayList();
				SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
				Object[] objDtlsLst = null;
				
				if(voToService.get("lOrderId")!=null)					
					lOrderId=Long.parseLong(voToService.get("lOrderId").toString());
				
				logger.info("lOrderId::"+lOrderId);
				
				dtlsList = mapEmpWithArrearDAO.getSalRevOrdersData(lOrderId);
				HrPaySalRevMst salRevMst = new HrPaySalRevMst();
				List<HrPaySalRevMst> salRevMstVOLst = new ArrayList();
				logger.info("dtlsList size in getSalRevOrderDetails service is:::::::::"+dtlsList.size());
				String effFromDate="";
				String effToDate="";
				String salRevReason="";
				long salRevCompoId=0;
				Date sDate = new Date();
				Date eDate = new Date();
				if(dtlsList!=null && dtlsList.size()!=0)
				{
					for (int i=0;i<dtlsList.size();i++)
					{	
						salRevMst = (HrPaySalRevMst) dtlsList.get(i);
						effFromDate = fmt.format(salRevMst.getRevEffcFrmDate());						
						effToDate = fmt.format(salRevMst.getRevEffcToDate());														
						salRevReason= salRevMst.getRevReason();	
						salRevCompoId=salRevMst.getRltBillTypeEdp().getTypeEdpId();
						propertyList.append("<salRevDetails>");   	
						propertyList.append("<effFromDate>").append("<![CDATA[").append(effFromDate).append("]]>").append("</effFromDate>");
						propertyList.append("<effToDate>").append("<![CDATA[").append(effToDate).append("]]>").append("</effToDate>");
						propertyList.append("<salRevReason>").append("<![CDATA[").append(salRevReason).append("]]>").append("</salRevReason>");
						propertyList.append("<salRevCompoId>").append("<![CDATA[").append(salRevCompoId).append("]]>").append("</salRevCompoId>");
						propertyList.append("</salRevDetails>");
						
					}
				}
				logger.info("propertyList::"+propertyList);
				
				String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
				objServiceArgs.put("ajaxKey", stateNameIdStr);

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("ajaxData");        
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getSalRevOrderDetails service******");
			logger.error("Error In getSalRevOrderDetails service: " , ex);
			ex.printStackTrace();
		}
		logger.info("getSalRevOrderDetails ended***********************");
		return resultObject;
	}
	public File writeApplicationExcelFile(ArrayList dataList)
	throws Exception
	{	
		File applicationFile = null;
		File outputFile = null;
		String inputFileName = "EmpArrearDataEmpty.xls";
		String fileName = "EmployeeArrearData.xls";
		
		payrollConstant = ResourceBundle.getBundle("resources.Payroll");
		
		String lStrFilePath = payrollConstant.getString("EMPLSTEXL.scanPath");		
		logger.info("File Path :"+lStrFilePath);
		
		URL inputUrl = MapEmpWithArrearServiceImpl.class.getClassLoader().getResource(lStrFilePath+"/"+inputFileName);
				
		applicationFile = new File(inputUrl.getFile());
		
		String inputFilePath=applicationFile.getPath();
		int lastIndex= inputFilePath.length()-inputFileName.length();
		inputFilePath=inputFilePath.substring(0, lastIndex);	
		logger.info("inputFilePath - "+inputFilePath);
		inputFilePath = inputFilePath.replaceAll("%20"," ");
		
		
		outputFile = new File(inputFilePath+File.separator+fileName);		
		logger.info("outputFile File Path :"+outputFile.getCanonicalPath());
		
		if( outputFile.exists(  ) )
		{
			outputFile.delete(  );
		}
		applicationFile = new File(inputFilePath+File.separator+inputFileName);
		//WorkbookSettings lbookSettings = new WorkbookSettings(  );
		Workbook readBook = Workbook.getWorkbook( applicationFile);

		WritableWorkbook lworkbook = Workbook.createWorkbook(outputFile,
				readBook );
		WritableSheet lwriSheet = lworkbook.getSheet( 0 );
		
		WritableCellFormat integerFormat = new WritableCellFormat (NumberFormats.INTEGER); 
		Label ldataCode;
		Number ldataNumber;
		int lidataRow = 0;
		int lidataCol = 1;
		if(dataList!=null && dataList.size() > 0)
		{
			for(int i = 0; i < dataList.size() ; i++) //Row Data loop
			{
				ArrayList rowList = (ArrayList)dataList.get(i);
				ldataNumber = new Number( 
						0,
						lidataRow,                  
						i+1,
						integerFormat);
				lwriSheet.addCell( ldataNumber );
				lidataCol=0;			
				for(int j = 0; j < rowList.size() ; j++) //Column Data loop
				{				
					ldataCode = new Label( 
							lidataCol,
							lidataRow,                  
							(String)rowList.get(j).toString());
					lwriSheet.addCell( ldataCode );					
					lidataCol++;            
				}
				lidataRow++;
			}
		}
		lworkbook.write(  );
		lworkbook.close(  );

		return outputFile;
	}
	public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
    
        // Get the size of the file
        long length = file.length();
    
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
	
	public ResultObject getEmpArrearBillMpgList(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside getEmpArrearBillMpgList*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());

				CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst lupMst = new CmnLookupMst();
				List empArrarMpgDataList = mapEmpWithArrearDAO.getEmpArrearBillMpgs(locId);
				
				objServiceArgs.put("empArrarMpgDataList", empArrarMpgDataList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("viewEmpArrearBillMpgList");        
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getEmpArrearBillMpgList service******");
			logger.error("Error In getEmpArrearBillMpgList service: " , ex);
			ex.printStackTrace();
		}
		logger.info("getEmpArrearBillMpgList ended***********************");
		return resultObject;
	}
	
	public ResultObject getEmpArrearCmpAmtData(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside getEmpArrearCmpAmtData*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				
				Map voToService = (Map)objServiceArgs.get("voToServiceMap");					
				long lOrderId=0;		
				String flag="N";			
				String orderFrmDate="";
				String orderEndDate="";
				long salRevId=0;
				long billSubheadId=0;
				if(voToService != null && voToService.get("salRevId")!=null)					
					salRevId=Long.parseLong(voToService.get("salRevId").toString());
				if(voToService != null && voToService.get("billSubheadId")!=null)					
					billSubheadId=Long.parseLong(voToService.get("billSubheadId").toString());
				if(voToService != null && voToService.get("orderFrmDate")!=null)					
					orderFrmDate=voToService.get("orderFrmDate").toString();
				if(voToService != null && voToService.get("orderEndDate")!=null)					
					orderEndDate=voToService.get("orderEndDate").toString();
				
				MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
				
				List empArrarBillPostData = mapEmpWithArrearDAO.getArrearBillPostMpgData(salRevId, billSubheadId);
				if(empArrarBillPostData!=null && empArrarBillPostData.size()>0)
					objServiceArgs.put("arrearBillPostDataLen", empArrarBillPostData.size());
				else
					objServiceArgs.put("arrearBillPostDataLen", 0);
				logger.info("arrearBillPostDataLen.size::"+empArrarBillPostData.size());
				List empArrarCmpAmtData = mapEmpWithArrearDAO.getEmpArrearCmpAmtDtls(langId, salRevId, billSubheadId,orderFrmDate,orderEndDate);
				List empArrCmpList = new ArrayList();
				int cmpLen=0;
				int dataSize=0;
				if(empArrarCmpAmtData!=null && empArrarCmpAmtData.size()>0)
				{
					logger.info("empArrarCmpAmtData.size::"+empArrarCmpAmtData.size());
					objServiceArgs.put("cmpAmtDataLen", empArrarCmpAmtData.size());
					/////////////////
					Object[] objDtlsLst = null;
					objDtlsLst=(Object[])empArrarCmpAmtData.get(0);
					long empArrpostId=0;
					
					if(objDtlsLst!=null &&  objDtlsLst.length>0)
					{
						if(objDtlsLst[2]!=null && !"".equals(objDtlsLst[2].toString()))
						{
							empArrpostId = Long.parseLong(objDtlsLst[2].toString());
							logger.info("empArrpostId::"+empArrpostId);
							empArrCmpList = mapEmpWithArrearDAO.getEmpArrearCmpCount(empArrpostId,langId, salRevId, billSubheadId);
							if(empArrCmpList != null && empArrCmpList.size()>0)
								cmpLen = empArrCmpList.size();							
						}						
					}
					//////////					
					dataSize =empArrarCmpAmtData.size();
				}
				logger.info("cmpLen::"+cmpLen);
				logger.info("dataSize::"+dataSize);
				objServiceArgs.put("salRevId", salRevId);
				objServiceArgs.put("billSubheadId", billSubheadId);
				objServiceArgs.put("empArrarCmpAmtData", empArrarCmpAmtData);
				objServiceArgs.put("cmpLen", cmpLen);
				objServiceArgs.put("dataSize", dataSize);
				objServiceArgs.put("empArrCmpList", empArrCmpList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("viewEmpArrearCmpAmtList");        
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getEmpArrearCmpAmtData service******");
			logger.error("Error In getEmpArrearCmpAmtData service: " , ex);
			ex.printStackTrace();
		}
		logger.info("getEmpArrearCmpAmtData ended***********************");
		return resultObject;
	}
	public ResultObject updateCmpAmtData(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside updateCmpAmtData*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
								
				List cmpAmtMpgList=(ArrayList)objServiceArgs.get("cmpAmtMpgList");
				
				MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
				
				List empArrCmpList = new ArrayList();
				int cmpLen=0;
				int dataSize=0;
				String[] cmpIdAmtArr=new String[2];
								
				StringTokenizer strCmpAmtTokenizer=null;
				
				GenericDaoHibernateImpl cmpAmtDaoImpl = new GenericDaoHibernateImpl(HrPayArrearCompoAmtMpg.class);
				cmpAmtDaoImpl.setSessionFactory(serv.getSessionFactory());
				long cmpAmtMpgId= 0;
				long cmpAmt=0;
				StringTokenizer strTokenizer = null;
				String tempStrCmpAmt="";
				if(cmpAmtMpgList!=null )
				{
					for (int i=0;i<cmpAmtMpgList.size();i++)
					{
						tempStrCmpAmt = cmpAmtMpgList.get(i).toString();
					
						logger.info("tempStrCmpAmt in Service Method: " + tempStrCmpAmt );
						if(tempStrCmpAmt.contains("Z"))
						{
							cmpIdAmtArr = tempStrCmpAmt.toString().split("Z");							
							HrPayArrearCompoAmtMpg cmpAmtMpgVO = new HrPayArrearCompoAmtMpg();						
							cmpAmtMpgId = Long.parseLong(cmpIdAmtArr[0]);
							cmpAmtMpgVO =(HrPayArrearCompoAmtMpg)cmpAmtDaoImpl.read(cmpAmtMpgId);
							if(cmpIdAmtArr.length> 1 && cmpIdAmtArr[1]!= null && !"".equals(cmpIdAmtArr[1].toString()))
								cmpAmt =Long.parseLong(cmpIdAmtArr[1]);
							cmpAmtMpgVO.setAmount(cmpAmt);
							cmpAmtDaoImpl.update(cmpAmtMpgVO);
						}

					}
				}
				List empArrarMpgDataList = mapEmpWithArrearDAO.getEmpArrearBillMpgs(locId);
				objServiceArgs.put("empArrarMpgDataList", empArrarMpgDataList);				
				
				objServiceArgs.put("MESSAGECODE",300006);				
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("viewEmpArrearCmpAmtList");    
				
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in updateCmpAmtData service******");
			logger.error("Error In updateCmpAmtData service: " , ex);
			ex.printStackTrace();
		}
		logger.info("updateCmpAmtData ended***********************");
		return resultObject;
	}
	public ResultObject deleteEmployeeArrearDtls(Map objServiceArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("************Inside getEmpArrearCmpAmtData*****************");			
			if (resultObject != null && objServiceArgs != null) 
			{	
				ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
				long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
				
				Map voToService = (Map)objServiceArgs.get("voToServiceMap");					
				long lOrderId=0;		
				String flag="N";			
				long salRevId=0;
				long billSubheadId=0;
				if(voToService != null && voToService.get("salRevId")!=null)					
					salRevId=Long.parseLong(voToService.get("salRevId").toString());
				if(voToService != null && voToService.get("billSubheadId")!=null)					
					billSubheadId=Long.parseLong(voToService.get("billSubheadId").toString());
				
				MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
				List empArrarData = mapEmpWithArrearDAO.getArrearBillPostMpgData(salRevId, billSubheadId);
				List empArrCmpList = new ArrayList();
				int cmpLen=0;
				int dataSize=0;
				HrPayArrearBillpostMpg	billPostMpgVO = new HrPayArrearBillpostMpg();
				HrPayArrearCompoAmtMpg  cmpAmtMpgVO = new HrPayArrearCompoAmtMpg();
				long tempArrearBillPostMpgId = 0;
				GenericDaoHibernateImpl billPostMpgDaoImpl = new GenericDaoHibernateImpl(HrPayArrearBillpostMpg.class);
				billPostMpgDaoImpl.setSessionFactory(serv.getSessionFactory());
				
				GenericDaoHibernateImpl cmpAmtDaoImpl = new GenericDaoHibernateImpl(HrPayArrearCompoAmtMpg.class);
				cmpAmtDaoImpl.setSessionFactory(serv.getSessionFactory());
				
				if(empArrarData!=null && empArrarData.size()>0)
				{
					for(Iterator itr=empArrarData.iterator();itr.hasNext();)
					{
						logger.info("empArrarData.size::"+empArrarData.size());				
						billPostMpgVO = (HrPayArrearBillpostMpg) itr.next();
						
						tempArrearBillPostMpgId = billPostMpgVO.getArrearBillpostId();						
						empArrCmpList= mapEmpWithArrearDAO.getArrearBillCmpAmtMpgData(tempArrearBillPostMpgId);
						for(Iterator itr2=empArrCmpList.iterator();itr2.hasNext();)
						{
							cmpAmtMpgVO = new HrPayArrearCompoAmtMpg();
							cmpAmtMpgVO = (HrPayArrearCompoAmtMpg)itr2.next();
							cmpAmtDaoImpl.delete(cmpAmtMpgVO);
						}
						billPostMpgDaoImpl.delete(billPostMpgVO);
					}
				}
				logger.info("cmpLen::"+cmpLen);
				logger.info("dataSize::"+dataSize);
				
				
				List empArrarMpgDataList = mapEmpWithArrearDAO.getEmpArrearBillMpgs(locId);
				objServiceArgs.put("empArrarMpgDataList", empArrarMpgDataList);
				//objServiceArgs.put("MESSAGECODE",300006);
				objServiceArgs.put("msg","Records deleted successfully");	
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("viewEmpArrearBillMpgList");        
			}
		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getEmpArrearCmpAmtData service******");
			logger.error("Error In getEmpArrearCmpAmtData service: " , ex);
			ex.printStackTrace();
		}
		logger.info("getEmpArrearCmpAmtData ended***********************");
		return resultObject;
	}		

public ResultObject checkSalRevOrderBillMpgExists(Map objServiceArgs)
{
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	try{
		logger.info("************Inside checkSalRevOrderBillMpgExists*****************");			
		if (resultObject != null && objServiceArgs != null) 
		{	
			ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			
			Map voToService = (Map)objServiceArgs.get("voToServiceMap");					
			long lOrderId=0;		
			String flag="N";			
			long salRevId=0;
			long billSubheadId=0;
			if(voToService != null && voToService.get("salRevId")!=null)					
				salRevId=Long.parseLong(voToService.get("salRevId").toString());
			if(voToService != null && voToService.get("billSubheadId")!=null)					
				billSubheadId=Long.parseLong(voToService.get("billSubheadId").toString());
			
			MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
			List empArrarData = mapEmpWithArrearDAO.getArrearBillPostMpgData(salRevId, billSubheadId);
			StringBuffer propertyList=new StringBuffer();
			String salBillMpg = "No";
			if(empArrarData!=null && empArrarData.size()>=0)
			{
				salBillMpg="Yes";
			}
			propertyList.append("<salBillMpgDetails>");   	
			propertyList.append("<salBillMpg>").append("<![CDATA[").append(salBillMpg).append("]]>").append("</salBillMpg>");

			propertyList.append("</salBillMpgDetails>");


			logger.info("propertyList::"+propertyList);
			
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			objServiceArgs.put("ajaxKey", stateNameIdStr);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objServiceArgs);
			resultObject.setViewName("ajaxData");        
		}
	}
	catch(Exception ex)
	{
		logger.info("There is some problem in checkSalRevOrderBillMpgExists service******");
		logger.error("Error In checkSalRevOrderBillMpgExists service: " , ex);
		ex.printStackTrace();
	}
	logger.info("checkSalRevOrderBillMpgExists ended***********************");
	return resultObject;
}	

public ResultObject getEmpDetailsUsingSearch(Map objServiceArgs)
{
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	try{
		logger.info("************Inside getEmpDetailsUsingSearch*****************");			
		if (resultObject != null && objServiceArgs != null) 
		{	
			ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());

			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			CmnLookupMst lupMst = new CmnLookupMst();
			
			Map voToService = (Map)objServiceArgs.get("voToServiceMap");					
			long empId=0;		
			
			long empPostId=0;
			long hrEmpId=0;
			String empName="";
			String gradeName="";
			String dsgnShortName="";
			
			StringBuffer propertyList = new StringBuffer();
			List dtlsList = new ArrayList();
			SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat parseDate =new SimpleDateFormat("yyyy-MM-dd");
			Object[] objDtlsLst = null;
			
			if(voToService.get("empId")!=null)					
				empId=Long.parseLong(voToService.get("empId").toString());
			
			logger.info("empId::"+empId);
			
			dtlsList = mapEmpWithArrearDAO.getEmpDetailsFromEmpID(empId, locId);
			
			if(dtlsList!=null && dtlsList.size()!=0)
			{
				for (int i=0;i<dtlsList.size();i++)
				{	
					objDtlsLst = (Object[])dtlsList.get(i);
					
					empPostId=objDtlsLst[0]!= null ? Long.parseLong(objDtlsLst[0].toString()) : Long.parseLong("0");
					hrEmpId=objDtlsLst[1]!= null ? Long.parseLong(objDtlsLst[1].toString()) : Long.parseLong("0");
					empName=objDtlsLst[2]!= null ? objDtlsLst[2].toString() : "";
					gradeName=objDtlsLst[3]!= null ? objDtlsLst[3].toString() : "";
					dsgnShortName=objDtlsLst[4]!= null ? objDtlsLst[4].toString() : "";
					
					propertyList.append("<empDetailsFromId>");   	
					propertyList.append("<empPostId>").append("<![CDATA[").append(empPostId).append("]]>").append("</empPostId>");
					propertyList.append("<hrEmpId>").append("<![CDATA[").append(hrEmpId).append("]]>").append("</hrEmpId>");
					propertyList.append("<empName>").append("<![CDATA[").append(empName).append("]]>").append("</empName>");
					propertyList.append("<gradeName>").append("<![CDATA[").append(gradeName).append("]]>").append("</gradeName>");
					propertyList.append("<dsgnShortName>").append("<![CDATA[").append(dsgnShortName).append("]]>").append("</dsgnShortName>");
					propertyList.append("</empDetailsFromId>");
					
				}
			}
			logger.info("propertyList::"+propertyList);
			
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			objServiceArgs.put("ajaxKey", stateNameIdStr);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objServiceArgs);
			resultObject.setViewName("ajaxData");        
		}
	}
	catch(Exception ex)
	{
		logger.info("There is some problem in getEmpDetailsUsingSearch service******");
		logger.error("Error In getEmpDetailsUsingSearch service: " , ex);
		ex.printStackTrace();
	}
	logger.info("getEmpDetailsUsingSearch ended***********************");
	return resultObject;
}

}