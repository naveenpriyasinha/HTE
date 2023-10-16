/**
 * Created by Kishan Shah for Regular Increment.
 */
package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDao;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.util.query.DateUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmployeeIncrementDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.dcps.dao.EmployeeSevenIncrDaompl;
import com.tcs.sgv.dcps.dao.LedgerReportDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAO;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpDetails;
import com.tcs.sgv.eis.valueobject.PayIncrementCustomVO;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;

@SuppressWarnings("unchecked")
public class EmployeeIncrementServiceImpl extends ServiceImpl
{
	public ResultObject getInitialData(Map objectArgs)
	{
		Log logger = LogFactory.getLog(getClass());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		//added by roshan
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		//end by roshan
		try
		{
			logger.error("===INSIDE IN getInitialData===");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			EmployeeIncrementDAOImpl employeeIncrementDAOImpl = new EmployeeIncrementDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			//			Map voToService = (Map) objectArgs.get("voToServiceMap");

			//			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locationId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			
			//ADDED BY ROSHAN TO RESTRICT for annual increment.
			//logger.info("the selected location_code is "+locationId);
			//String allowedOrNot=(String) employeeIncrementDAOImpl.getStatusOfDist(locationId);
			//ENDED BY ROSHAN---
			
			List<HrPayfixMst> fixList = employeeIncrementDAOImpl.getAllDataForPayFixation(locationId);
			List billList = employeeIncrementDAOImpl.getAllBillsByLoc(locationId);
			List payCommisionList = employeeIncrementDAOImpl.getPayCommissionList();
			String incrementOrderNo = "";
			String incrementOrderDate = "";
			String status = "";
			long countOfEmployees = 0;
			List empCustomVOList = new ArrayList();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

			Object[] row = null;
			PayIncrementCustomVO customVo = null;
			for (Iterator itr = fixList.iterator(); itr.hasNext();)
			{
				row = (Object[]) itr.next();
				incrementOrderNo = row[0] != null ? row[0].toString() : "";
				incrementOrderDate = row[1] != null ? row[1].toString() : "";
				status = row[2] != null ? row[2].toString() : "";
				countOfEmployees = row[3] != null ? Long.parseLong(row[3].toString()) : 0;

				customVo = new PayIncrementCustomVO();
				customVo.setIncrementOrderNo(incrementOrderNo);
				if (!incrementOrderDate.equals(""))
					customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)));
				customVo.setStatus(status);
				customVo.setCountOfEmployees(countOfEmployees);
				empCustomVOList.add(customVo);
			}
			//objectArgs.put("allowedOrNot", allowedOrNot);
			objectArgs.put("fixList", empCustomVOList);
			objectArgs.put("fixListsize", empCustomVOList.size());
			objectArgs.put("billList", billList);
			objectArgs.put("payCommisionList", payCommisionList);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("EmployeePayIncrList");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
		}
		return resultObject;
	}

	public ResultObject showDataForNewOrderNo(Map objectArgs)
	{
		Log logger = LogFactory.getLog(getClass());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.error("showDataForNewOrderNo****enter");
		String viewName="EmployeePayIncrList";
		//added by roshan
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		//end by roshan
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			EmployeeIncrementDAOImpl employeeIncrementDAOImpl = new EmployeeIncrementDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");

			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			Date currDate = new Date();
			long scaleNxtIncrAmt = 0;
			//added by roshan
			Long locCodeSelected=null;
			if((StringUtility.getParameter("loc_code", request)!=null)&&(StringUtility.getParameter("loc_code", request)!="")){
				locCodeSelected= Long.parseLong(StringUtility.getParameter("loc_code", request));
		    	   
		    	   }
			
			//end by roshan
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);

			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());

			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			IdGenerator idGenerator = new IdGenerator();

			String save = voToService.get("save") != null ? voToService.get("save").toString() : "";
			String orderNo = voToService.get("orderNo") != null ? voToService.get("orderNo").toString() : "";
			String orderDate = voToService.get("orderdate") != null ? voToService.get("orderdate").toString() : "";
			String status = null;

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

			List duplicateList = employeeIncrementDAOImpl.getDataForDuplicateMessage(orderNo, locId, langId);

			if (duplicateList.size() > 0 && save.equalsIgnoreCase("N"))
			{
				objectArgs.put("msg", "This Increment Certificate Order No already exists !!!");
			}
			else
			{
				if ("N".equalsIgnoreCase(save))
				{
					long billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()) : 0;
					long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;

					List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
					long size = listoftable.size();
					List empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
					List empCustomVOList = new ArrayList();
					logger.error("listoftable*********" + listoftable.size());
					logger.error("empBasicList*********" + empBasicList.size());

					String empFame = "", empMame ="", empLame = "", empName = "";
					long basic = 0;
					long userid = 0;
					long scaleEndAmount = 0;
					String nextincrementDate = "";
					String empIncrementOrderDate = "";
					int empBasicListSize = empBasicList.size();

					if (empBasicList != null && empBasicListSize != 0)
					{
						Object[] rowList = null;

						for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
						{
							empIncrementOrderDate = "";
							nextincrementDate = "";

							rowList = (Object[]) itr.next();

							empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
							empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
							empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
							empName = empFame + empMame + empLame;
							basic = rowList[3] != null ? Long.parseLong(rowList[3].toString().trim()) : 0;
							userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0;
							scaleEndAmount = (rowList[5] != null && rowList[8] != null ) ? ( Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim())): 0;
							empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
							scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0;

							logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);

							if (empIncrementOrderDate != null && empIncrementOrderDate != "" && empIncrementOrderDate.length() > 0)
							{
								empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
								String tempCurrDate = sdf.format(currDate);
								String currDateArr[] = tempCurrDate.split("/");
								Long currYear = Long.parseLong(currDateArr[2]);
								currYear = currYear + 1;
								currDateArr[2] = currYear.toString();
								nextincrementDate = "01" + "/" + "07" + "/" + currYear;
							}

							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
							logger.error("nextincrementDate*********" + nextincrementDate);
							logger.error("empIncrementOrderDate*********" + empIncrementOrderDate);

							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							customVo.setNextincrementDate(nextincrementDate);
							customVo.setUserid(userid);
							customVo.setScaleEndAmount(scaleEndAmount);
							customVo.setIncrementOrderDate(empIncrementOrderDate);
							customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
							customVo.setPayCommissionId(payCommId);
							empCustomVOList.add(customVo);
							logger.error("empCustomVOList size ****************" + empCustomVOList.size());
						}
						objectArgs.put("save", save);
						objectArgs.put("fixListWithGo", empCustomVOList);
						objectArgs.put("fixListsizeWithGo", empCustomVOList.size());
						objectArgs.put("billNo", billNo);
						objectArgs.put("payCommId", payCommId);
						objectArgs.put("orderNo", orderNo);
						objectArgs.put("orderDate", orderDate);
					}
					else
					{
						objectArgs.put("msg", "There is no any employee matching selection criteria");
					}

					getInitialData(objectArgs);
				}
				else if (save.equals("edit"))
				{
					viewName="EmployeePayIncrListByLevelTwo";
					logger.info("hii i m roshan in edit functionality for annual increment ");
					long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;
					
					String empIdstoBeAttached = voToService.get("empIdstoBeAttached") != null ? voToService.get("empIdstoBeAttached").toString() : "";
					logger.error("empIdstoBeAttached are ****************" + empIdstoBeAttached);
					String empIdsStr[] = empIdstoBeAttached.split("~");
					logger.error("empIdstoBeAttached**********" + empIdsStr);

					String empIdstoBeDetached = voToService.get("empIdstoBeDetached") != null ? voToService.get("empIdstoBeDetached").toString() : "";
					logger.error("empIdstoBeDetached are ****************" + empIdstoBeDetached);
					String empIdsDetachStr[] = empIdstoBeDetached.split("~");
					logger.error("empIdstoBeDetached**********" + empIdsDetachStr);

					String empBasicSalarytoBeAttached = voToService.get("empBasicSalarytoBeAttached") != null ? voToService.get("empBasicSalarytoBeAttached").toString() : "";
					logger.error("empBasicSalarytoBeAttached are ****************" + empBasicSalarytoBeAttached);
					String empBasicStr[] = empBasicSalarytoBeAttached.split("~");
					logger.error("empBasicSalarytoBeAttached**********" + empBasicStr);

					String empWEFtoBeAttached = voToService.get("empWEFtoBeAttached") != null ? voToService.get("empWEFtoBeAttached").toString() : "";
					logger.error("empWEFtoBeAttached are ****************" + empWEFtoBeAttached);
					String empWEFStr[] = empWEFtoBeAttached.split("~");
					logger.error("empWEFtoBeAttached**********" + empWEFStr);

					String inputTagElements = voToService.get("inputTagElements") != null ? voToService.get("inputTagElements").toString() : "";
					logger.error("inputTagElements are ****************" + inputTagElements);
					String inputElementsUpdate[] = inputTagElements.split("~");
					logger.error("inputElementsUpdate**********" + inputElementsUpdate);

					Date empWEFUpdate;
					String empRemarksUpdate = "";
					long empIdsUpdate;
					Map<Long, Object> remarksDataMap = new HashMap<Long, Object>();
					for (int i = 0; i < inputElementsUpdate.length; i += 3)
					{
						logger.info("inputElementsUpdate[i]"+inputElementsUpdate[i]);
						empIdsUpdate = Long.valueOf(inputElementsUpdate[i]);
						empWEFUpdate = sdf.parse(inputElementsUpdate[i + 1]);
						if (inputElementsUpdate[i + 2] != null && inputElementsUpdate[i + 2] != "")
							empRemarksUpdate = inputElementsUpdate[i + 2];
						else
							empRemarksUpdate = "";

						Object[] dataForPut = { empWEFUpdate, empRemarksUpdate };
						remarksDataMap.put(empIdsUpdate, dataForPut);
					}

					String empNextIncrDatetoBeAttached = voToService.get("empNextIncrDatetoBeAttached") != null ? voToService.get("empNextIncrDatetoBeAttached").toString() : "";
					logger.error("empNextIncrDatetoBeAttached are ****************" + empNextIncrDatetoBeAttached);
					String empNextIncrStr[] = empNextIncrDatetoBeAttached.split("~");
					logger.error("empNextIncrDatetoBeAttached**********" + empNextIncrStr);

					String empRemarkstoBeAttached = voToService.get("empRemarkstoBeAttached") != null ? voToService.get("empRemarkstoBeAttached").toString() : "";
					logger.error("empRemarkstoBeAttached are ****************" + empRemarkstoBeAttached);
					String empRemarksStr[] = empRemarkstoBeAttached.split("~");
					logger.error("empRemarkstoBeAttached**********" + empRemarksStr);

					String empIncOrderDatetoBeAttached = voToService.get("empIncOrderDatetoBeAttached") != null ? voToService.get("empIncOrderDatetoBeAttached").toString() : "";
					logger.error("empIncOrderDatetoBeAttached are ****************" + empIncOrderDatetoBeAttached);
					String empIncOrderDateStr[] = empIncOrderDatetoBeAttached.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empIncOrderDateStr);

					String origBasictoBeAttached = voToService.get("origBasictoBeAttached") != null ? voToService.get("origBasictoBeAttached").toString() : "";
					logger.error("empOrigBasictoBeAttached are ****************" + origBasictoBeAttached);
					String empOrigBasicStr[] = origBasictoBeAttached.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empOrigBasicStr);

					String empPayCommissionId = voToService.get("empPayCommissionId") != null ? voToService.get("empPayCommissionId").toString() : "";
					logger.error("empPayCommissionId are ****************" + empPayCommissionId);
					String empPayCommissionIdStr[] = empPayCommissionId.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empPayCommissionIdStr);

					Map<Long, Long> payCommIdMap = new HashMap<Long, Long>();
					for (int i = 0; i < empPayCommissionIdStr.length; i += 2)
					{
						long empId = Long.valueOf(empPayCommissionIdStr[i]);
						long payId = Long.valueOf(empPayCommissionIdStr[i + 1]);
						payCommIdMap.put(empId, payId);
					}

					//Deleting all records against orderno and order date and then insert
					Date incrOrderDt = null;
					if (orderDate.toString() != null && orderDate.length() > 0 && orderDate.toString() != "")
					{
						incrOrderDt = sdf.parse(orderDate);
					}
					//added by roshan for location code update 
					String ddoCodeOfOrder= StringUtility.getParameter("ddoCodeOfOrder", request);
					Long locationCodeOfLevel1=employeeIncrementDAOImpl.getLocationCode(orderNo,ddoCodeOfOrder);
					logger.info("hii level 1 loc isd****************"+locationCodeOfLevel1);
					//ended by roshan :end
					employeeIncrementDAOImpl.deleteRecordsByOrderNumber(orderNo, incrOrderDt);

					if (empIdsStr != null && empIdsStr.length > 0 && empIdsStr[0] != "")
					{
						for (int i = 0; i < empIdsStr.length; i++)
						{
							long oldBasic = 0;
							long newBasic = 0;
							Date wefDate = null;
							Date nextIncrDt = null;
							String remarks = null;
							long empIdToInsert = 0;
							incrOrderDt = null;

							if (empIdsStr[i] != null && empIdsStr.length > 0 && empIdsStr[i] != "")
							{
								empIdToInsert = Long.parseLong(empIdsStr[i]);
								logger.info("hii the emp_id is "+empIdToInsert);
							}
							if (empBasicStr[i] != null && empBasicStr.length > 0 && empBasicStr[i] != "")
							{
								newBasic = Long.parseLong(empBasicStr[i]);
							}
							if (empOrigBasicStr[i] != null && empOrigBasicStr.length > 0 && empOrigBasicStr[i] != "")
							{
								oldBasic = Long.parseLong(empOrigBasicStr[i]);
								logger.error("Old Basic from empOrigBasicStr--" + oldBasic);
							}

							status = "Forwaded";
							if (empNextIncrStr[i] != null && empNextIncrStr.length > 0 && empNextIncrStr[i] != "")
							{
								nextIncrDt = sdf.parse(empNextIncrStr[i]);
							}

							Object[] data = (Object[]) remarksDataMap.get(empIdToInsert);
							wefDate = (Date) data[0];
							remarks = data[1].toString();

							if (orderDate.toString() != null && orderDate.length() > 0 && orderDate.toString() != "")
							{
								incrOrderDt = sdf.parse(orderDate);
							}

							payCommId = Long.valueOf(payCommIdMap.get(empIdToInsert).toString());

							OrgUserMst orgUserMstVO = orgUserMstDaoImpl.read(empIdToInsert);
							long payfixPkId = idGenerator.PKGenerator("HR_PAYFIX_MST", objectArgs);
							HrPayfixMst hrPayfixMst = new HrPayfixMst();
							hrPayfixMst.setCmnDatabaseMst(cmnDatabaseMst);
							hrPayfixMst.setCmnLanguageMst(cmnLanguageMst);
							//Added by roshan to edit the order from level 2
							if(locationCodeOfLevel1!=null){
								CmnLocationMstDaoImpl cmnLocationMstDaoImpl1 = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
								cmnLocationMst = cmnLocationMstDaoImpl.read(locationCodeOfLevel1);

								hrPayfixMst.setCmnLocationMst(cmnLocationMst);
							}
							//ended by roshan
							else{
							hrPayfixMst.setCmnLocationMst(cmnLocationMst);
							
							}
							hrPayfixMst.setCreatedDate(currDate);
							hrPayfixMst.setIncrementOrderDate(incrOrderDt);
							hrPayfixMst.setIncrementOrderNo(orderNo);
							hrPayfixMst.setNewBasic(newBasic);
							hrPayfixMst.setNxtIncrDate(nextIncrDt);
							hrPayfixMst.setOldBasic(oldBasic);
							hrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
							hrPayfixMst.setOrgPostMstUpdatedByPost(orgPostMst);
							hrPayfixMst.setOrgUserMstCreatedBy(orgUserMstVO);
							hrPayfixMst.setOrgUserMstUpdatedBy(orgUserMstVO);
							hrPayfixMst.setPayFixDate(wefDate);
							hrPayfixMst.setPayfixId(payfixPkId);
							hrPayfixMst.setRemarks(remarks);
							hrPayfixMst.setStatus(status);
							hrPayfixMst.setUpdatedDate(currDate);
							hrPayfixMst.setUserId(orgUserMstVO);
							hrPayfixMst.setActivateFlag(1);
							hrPayfixMst.setTrnCounter(1L);
				
							hrPayfixMst.setPayCommissionId(payCommId);
							employeeIncrementDAOImpl.create(hrPayfixMst);
							objectArgs.put("locationCodeOfLevel1", locationCodeOfLevel1);
							objectArgs.put("orderNumberToUpdate", orderNo);
							objectArgs.put("msg", "Annual increment has been modified Successfully for verification");
							
						}
					}
				}
				else if (save.equals("Yes"))
				{

					long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;

					String empIdstoBeAttached = voToService.get("empIdstoBeAttached") != null ? voToService.get("empIdstoBeAttached").toString() : "";
					logger.error("empIdstoBeAttached are ****************" + empIdstoBeAttached);
					String empIdsStr[] = empIdstoBeAttached.split("~");
					logger.error("empIdstoBeAttached**********" + empIdsStr);

					String empIdstoBeDetached = voToService.get("empIdstoBeDetached") != null ? voToService.get("empIdstoBeDetached").toString() : "";
					logger.error("empIdstoBeDetached are ****************" + empIdstoBeDetached);
					String empIdsDetachStr[] = empIdstoBeDetached.split("~");
					logger.error("empIdstoBeDetached**********" + empIdsDetachStr);

					String empBasicSalarytoBeAttached = voToService.get("empBasicSalarytoBeAttached") != null ? voToService.get("empBasicSalarytoBeAttached").toString() : "";
					logger.error("empBasicSalarytoBeAttached are ****************" + empBasicSalarytoBeAttached);
					String empBasicStr[] = empBasicSalarytoBeAttached.split("~");
					logger.error("empBasicSalarytoBeAttached**********" + empBasicStr);

					String empWEFtoBeAttached = voToService.get("empWEFtoBeAttached") != null ? voToService.get("empWEFtoBeAttached").toString() : "";
					logger.error("empWEFtoBeAttached are ****************" + empWEFtoBeAttached);
					String empWEFStr[] = empWEFtoBeAttached.split("~");
					logger.error("empWEFtoBeAttached**********" + empWEFStr);

					String inputTagElements = voToService.get("inputTagElements") != null ? voToService.get("inputTagElements").toString() : "";
					logger.error("inputTagElements are ****************" + inputTagElements);
					String inputElementsUpdate[] = inputTagElements.split("~");
					logger.error("inputElementsUpdate**********" + inputElementsUpdate);

					Date empWEFUpdate;
					String empRemarksUpdate = "";
					long empIdsUpdate;
					Map<Long, Object> remarksDataMap = new HashMap<Long, Object>();
					for (int i = 0; i < inputElementsUpdate.length; i += 3)
					{
						logger.info("inputElementsUpdate[i]"+inputElementsUpdate[i]);
						empIdsUpdate = Long.valueOf(inputElementsUpdate[i]);
						empWEFUpdate = sdf.parse(inputElementsUpdate[i + 1]);
						if (inputElementsUpdate[i + 2] != null && inputElementsUpdate[i + 2] != "")
							empRemarksUpdate = inputElementsUpdate[i + 2];
						else
							empRemarksUpdate = "";

						Object[] dataForPut = { empWEFUpdate, empRemarksUpdate };
						remarksDataMap.put(empIdsUpdate, dataForPut);
					}

					String empNextIncrDatetoBeAttached = voToService.get("empNextIncrDatetoBeAttached") != null ? voToService.get("empNextIncrDatetoBeAttached").toString() : "";
					logger.error("empNextIncrDatetoBeAttached are ****************" + empNextIncrDatetoBeAttached);
					String empNextIncrStr[] = empNextIncrDatetoBeAttached.split("~");
					logger.error("empNextIncrDatetoBeAttached**********" + empNextIncrStr);

					String empRemarkstoBeAttached = voToService.get("empRemarkstoBeAttached") != null ? voToService.get("empRemarkstoBeAttached").toString() : "";
					logger.error("empRemarkstoBeAttached are ****************" + empRemarkstoBeAttached);
					String empRemarksStr[] = empRemarkstoBeAttached.split("~");
					logger.error("empRemarkstoBeAttached**********" + empRemarksStr);

					String empIncOrderDatetoBeAttached = voToService.get("empIncOrderDatetoBeAttached") != null ? voToService.get("empIncOrderDatetoBeAttached").toString() : "";
					logger.error("empIncOrderDatetoBeAttached are ****************" + empIncOrderDatetoBeAttached);
					String empIncOrderDateStr[] = empIncOrderDatetoBeAttached.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empIncOrderDateStr);

					String origBasictoBeAttached = voToService.get("origBasictoBeAttached") != null ? voToService.get("origBasictoBeAttached").toString() : "";
					logger.error("empOrigBasictoBeAttached are ****************" + origBasictoBeAttached);
					String empOrigBasicStr[] = origBasictoBeAttached.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empOrigBasicStr);

					String empPayCommissionId = voToService.get("empPayCommissionId") != null ? voToService.get("empPayCommissionId").toString() : "";
					logger.error("empPayCommissionId are ****************" + empPayCommissionId);
					String empPayCommissionIdStr[] = empPayCommissionId.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empPayCommissionIdStr);

					Map<Long, Long> payCommIdMap = new HashMap<Long, Long>();
					for (int i = 0; i < empPayCommissionIdStr.length; i += 2)
					{
						long empId = Long.valueOf(empPayCommissionIdStr[i]);
						long payId = Long.valueOf(empPayCommissionIdStr[i + 1]);
						payCommIdMap.put(empId, payId);
					}

					//Deleting all records against orderno and order date and then insert
					Date incrOrderDt = null;
					if (orderDate.toString() != null && orderDate.length() > 0 && orderDate.toString() != "")
					{
						incrOrderDt = sdf.parse(orderDate);
					}
					
					//employeeIncrementDAOImpl.deleteRecordsByOrderNumber(orderNo, incrOrderDt);

					if (empIdsStr != null && empIdsStr.length > 0 && empIdsStr[0] != "")
					{
						for (int i = 0; i < empIdsStr.length; i++)
						{
							long oldBasic = 0;
							long newBasic = 0;
							Date wefDate = null;
							Date nextIncrDt = null;
							String remarks = null;
							long empIdToInsert = 0;
							incrOrderDt = null;

							if (empIdsStr[i] != null && empIdsStr.length > 0 && empIdsStr[i] != "")
							{
								empIdToInsert = Long.parseLong(empIdsStr[i]);
								logger.info("hii the emp_id is "+empIdToInsert);
							}
							if (empBasicStr[i] != null && empBasicStr.length > 0 && empBasicStr[i] != "")
							{
								newBasic = Long.parseLong(empBasicStr[i]);
							}
							if (empOrigBasicStr[i] != null && empOrigBasicStr.length > 0 && empOrigBasicStr[i] != "")
							{
								oldBasic = Long.parseLong(empOrigBasicStr[i]);
								logger.error("Old Basic from empOrigBasicStr--" + oldBasic);
							}

							status = "Forwaded";
							if (empNextIncrStr[i] != null && empNextIncrStr.length > 0 && empNextIncrStr[i] != "")
							{
								nextIncrDt = sdf.parse(empNextIncrStr[i]);
							}

							Object[] data = (Object[]) remarksDataMap.get(empIdToInsert);
							wefDate = (Date) data[0];
							remarks = data[1].toString();

							if (orderDate.toString() != null && orderDate.length() > 0 && orderDate.toString() != "")
							{
								incrOrderDt = sdf.parse(orderDate);
							}

							payCommId = Long.valueOf(payCommIdMap.get(empIdToInsert).toString());

							OrgUserMst orgUserMstVO = orgUserMstDaoImpl.read(empIdToInsert);
							long payfixPkId = idGenerator.PKGenerator("HR_PAYFIX_MST", objectArgs);
							HrPayfixMst hrPayfixMst = new HrPayfixMst();
							hrPayfixMst.setCmnDatabaseMst(cmnDatabaseMst);
							hrPayfixMst.setCmnLanguageMst(cmnLanguageMst);
							//if(locCodeSelected!=null){
								//hrPayfixMst.setCmnLocationMst(Long.parseLong(locCodeSelected));
							//}
							//else{
							hrPayfixMst.setCmnLocationMst(cmnLocationMst);
							
							//}
							hrPayfixMst.setCreatedDate(currDate);
							hrPayfixMst.setIncrementOrderDate(incrOrderDt);
							hrPayfixMst.setIncrementOrderNo(orderNo);
							hrPayfixMst.setNewBasic(newBasic);
							hrPayfixMst.setNxtIncrDate(nextIncrDt);
							hrPayfixMst.setOldBasic(oldBasic);
							hrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
							hrPayfixMst.setOrgPostMstUpdatedByPost(orgPostMst);
							hrPayfixMst.setOrgUserMstCreatedBy(orgUserMstVO);
							hrPayfixMst.setOrgUserMstUpdatedBy(orgUserMstVO);
							hrPayfixMst.setPayFixDate(wefDate);
							hrPayfixMst.setPayfixId(payfixPkId);
							hrPayfixMst.setRemarks(remarks);
							hrPayfixMst.setStatus(status);
							hrPayfixMst.setUpdatedDate(currDate);
							hrPayfixMst.setUserId(orgUserMstVO);
							hrPayfixMst.setActivateFlag(1);
							hrPayfixMst.setTrnCounter(1L);
				
							hrPayfixMst.setPayCommissionId(payCommId);
							employeeIncrementDAOImpl.create(hrPayfixMst);
							
							
							objectArgs.put("msg", "Annual increment has been forwarded Successfully for verification");
							
						}
					}
				}
				else if (save.equalsIgnoreCase("view"))
				{
					String empName = "";
					String empFname = "", empMname = "", empLname = "";
					long basic = 0;
					long userId = 0;
					String incrementOrderDate = "";
					long scaleEndAmount = 0;
					String nextincrementDate = "";
					String withEffectiveDate = "";
					String remarks = "";
					long newBasic = 0;
					status = "";
					long commisionId = 0;
					List empBasicList = null;

					long billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()) : 0;
					long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;

					List fixList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtlsByOrderNo(locId, orderNo);
					List empCustomVOList = new ArrayList();
					int fixLisSize = fixList.size();

					logger.error("Size of HrPayFixMst " + fixLisSize);
					Object[] dataObj = null;
					for (int i = 0; i < fixLisSize; i++)
					{
						incrementOrderDate = "";
						nextincrementDate = "";
						withEffectiveDate = "";
						status = "";
						empName = "";
						dataObj = (Object[]) fixList.get(i);
						empFname = dataObj[0] != null ? dataObj[0].toString().concat(" ") : "";
						empMname = dataObj[1] != null ? dataObj[1].toString().concat(" ") : "";
						empLname = dataObj[2] != null ? dataObj[2].toString() : "";
						empName = empFname + empMname + empLname;
						basic = dataObj[3] != null ? Long.valueOf(dataObj[3].toString()) : 0;
						userId = dataObj[4] != null ? Long.valueOf(dataObj[4].toString()) : 0;
						//scaleEndAmount = dataObj[5] != null ? Long.valueOf(dataObj[5].toString()) : 0;
						scaleEndAmount = (dataObj[5] != null && dataObj[8] != null ) ? ( Long.parseLong(dataObj[5].toString().trim()) + Long.parseLong(dataObj[8].toString().trim())): 0;
						withEffectiveDate = dataObj[6] != null ? dataObj[6].toString().trim() : "";
						remarks = dataObj[7] != null ? dataObj[7].toString().trim() : "";
						newBasic = dataObj[8] != null ? Long.valueOf(dataObj[8].toString()) : 0;
						nextincrementDate = dataObj[9] != null ? dataObj[9].toString().trim() : "";
						incrementOrderDate = dataObj[11] != null ? dataObj[11].toString().trim() : "";
						status = dataObj[12] != null ? dataObj[12].toString().trim() : "";
						commisionId = dataObj[13] != null ? Long.valueOf(dataObj[13].toString()) : 0;
						scaleNxtIncrAmt = dataObj[14] != null ? Long.valueOf(dataObj[14].toString()) : 0;

						PayIncrementCustomVO customVo = new PayIncrementCustomVO();
						customVo.setEmpName(empName);
						customVo.setScaleEndAmount(scaleEndAmount);
						customVo.setUserid(userId);
						customVo.setBasic(basic);

						if (!nextincrementDate.equals(""))
							customVo.setNextincrementDate(sdf.format(sdfParse.parse(nextincrementDate)));

						if (!withEffectiveDate.equals(""))
							customVo.setWithEffectiveDate(sdf.format(sdfParse.parse(withEffectiveDate)).toString());

						if (!incrementOrderDate.equals(""))
						{
							logger.error("Increment Order Date in View Scrren" + incrementOrderDate);
							customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)).toString());
						}
						customVo.setRemarks(remarks);
						customVo.setNewBasic(newBasic);
						customVo.setStatus(status);
						customVo.setPayCommissionId(commisionId);
						customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
						empCustomVOList.add(customVo);
					}

					objectArgs.put("fixListWithGoUpdate", empCustomVOList);
					objectArgs.put("fixListsizeWithGoUpdate", empCustomVOList.size());
					objectArgs.put("save", save);

					//for left side table

					List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
					long size = listoftable.size();
					empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
					empCustomVOList = new ArrayList();
					logger.error("listoftable*********" + listoftable.size());
					logger.error("empBasicList*********" + empBasicList.size());

					String empFame = "", empMame = "", empLame = "";
					empName = "";
					basic = 0;
					long userid = 0;
					scaleEndAmount = 0;
					nextincrementDate = "";
					String empIncrementOrderDate = "";
					int empBasicListSize = empBasicList.size();

					if (empBasicList != null && empBasicListSize != 0)
					{
						Object[] rowList = null;

						for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
						{
							empIncrementOrderDate = "";
							nextincrementDate = "";

							rowList = (Object[]) itr.next();

							empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
							empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
							empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
							empName = empFame + empMame + empLame;
							basic = rowList[3] != null ? Long.parseLong(rowList[3].toString().trim()) : 0;
							userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0;
							scaleEndAmount = (rowList[5] != null && rowList[8] != null ) ? ( Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim())): 0;
							empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
							scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0;
							logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);

							if (empIncrementOrderDate != null && empIncrementOrderDate != "" && empIncrementOrderDate.length() > 0)
							{
								empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
								String tempCurrDate = sdf.format(currDate);
								String currDateArr[] = tempCurrDate.split("/");
								Long currYear = Long.parseLong(currDateArr[2]);
								currYear = currYear + 1;
								currDateArr[2] = currYear.toString();
								nextincrementDate = "01" + "/" + "07" + "/" + currYear;
							}

							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
							logger.error("nextincrementDate*********" + nextincrementDate);
							logger.error("empIncrementOrderDate*********" + empIncrementOrderDate);

							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							customVo.setNextincrementDate(nextincrementDate);
							customVo.setUserid(userid);
							customVo.setScaleEndAmount(scaleEndAmount);
							customVo.setIncrementOrderDate(empIncrementOrderDate);
							customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
							customVo.setPayCommissionId(payCommId);
							empCustomVOList.add(customVo);
							logger.error("empCustomVOList size ****************" + empCustomVOList.size());
						}
						objectArgs.put("save", save);
						objectArgs.put("fixListWithGo", empCustomVOList);
						objectArgs.put("fixListsizeWithGo", empCustomVOList.size());
						objectArgs.put("billNo", billNo);
						objectArgs.put("payCommId", payCommId);
						objectArgs.put("orderNo", orderNo);
						objectArgs.put("orderDate", orderDate);
					}
					else
					{
						objectArgs.put("msg", "There is no any employee matching selection criteria");
					}

					getInitialData(objectArgs);
				}
				else if (save.equalsIgnoreCase("modify"))
				{
					logger.info("hii i m in modify.....");
					viewName="EmployeePayIncrListByLevelTwo";
					Long payCommId=null;
					Long billNo=null;
					
					String empName = "";
					String empFname = "", empMname = "", empLname = "";
					long basic = 0;
					long userId = 0;
					String incrementOrderDate = "";
					long scaleEndAmount = 0;
					String nextincrementDate = "";
					String withEffectiveDate = "";
					String remarks = "";
					long newBasic = 0;
					status = "";
					long commisionId = 0;
					List empBasicList = null;
					//added by roshan
					String orderNumber=null;
					if((StringUtility.getParameter("orderNo", request)!=null)&&(StringUtility.getParameter("orderNo", request)!="")){
						orderNumber= StringUtility.getParameter("orderNo", request);
						logger.info("hhii the order number to be edited is"+orderNumber);
				    }
					String ddoCodeOfOrder= StringUtility.getParameter("ddoCodeOfOrder", request);
					locId=employeeIncrementDAOImpl.getLocationCode(orderNumber,ddoCodeOfOrder);
					payCommId=employeeIncrementDAOImpl.getpayCommID(orderNumber,ddoCodeOfOrder);
					billNo=employeeIncrementDAOImpl.getBillNo(orderNumber,ddoCodeOfOrder);
					orderDate=employeeIncrementDAOImpl.getOrderDate(orderNumber,ddoCodeOfOrder);
					List billList = employeeIncrementDAOImpl.getAllBillsByDDO(ddoCodeOfOrder);
					//parsing order date
					orderDate = new DateUtility().convertToDDMMYYYY(orderDate.toString().split(" ")[0]);
					logger.info("hiii order date is "+orderDate);
					//end in parsing order date.
					logger.info("location code is roshan "+locId);
					logger.info("payCommId code is "+payCommId);
					logger.info("billNo code is "+billNo);
					//ended by roshan
					//billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()) : 0;
					//payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;
					
					List fixList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtlsByOrderNo(locId, orderNo);
					List empCustomVOList = new ArrayList();
					int fixLisSize = fixList.size();

					logger.error("Size of HrPayFixMst " + fixLisSize);
					Object[] dataObj = null;
					for (int i = 0; i < fixLisSize; i++)
					{
						incrementOrderDate = "";
						nextincrementDate = "";
						withEffectiveDate = "";
						status = "";
						empName = "";
						dataObj = (Object[]) fixList.get(i);
						empFname = dataObj[0] != null ? dataObj[0].toString().concat(" ") : "";
						empMname = dataObj[1] != null ? dataObj[1].toString().concat(" ") : "";
						empLname = dataObj[2] != null ? dataObj[2].toString() : "";
						empName = empFname + empMname + empLname;
						basic = dataObj[3] != null ? Long.valueOf(dataObj[3].toString()) : 0;
						userId = dataObj[4] != null ? Long.valueOf(dataObj[4].toString()) : 0;
						//scaleEndAmount = dataObj[5] != null ? Long.valueOf(dataObj[5].toString()) : 0;
						scaleEndAmount = (dataObj[5] != null && dataObj[8] != null ) ? ( Long.parseLong(dataObj[5].toString().trim()) + Long.parseLong(dataObj[8].toString().trim())): 0;
						withEffectiveDate = dataObj[6] != null ? dataObj[6].toString().trim() : "";
						remarks = dataObj[7] != null ? dataObj[7].toString().trim() : "";
						newBasic = dataObj[8] != null ? Long.valueOf(dataObj[8].toString()) : 0;
						nextincrementDate = dataObj[9] != null ? dataObj[9].toString().trim() : "";
						incrementOrderDate = dataObj[11] != null ? dataObj[11].toString().trim() : "";
						status = dataObj[12] != null ? dataObj[12].toString().trim() : "";
						commisionId = dataObj[13] != null ? Long.valueOf(dataObj[13].toString()) : 0;
						scaleNxtIncrAmt = dataObj[14] != null ? Long.valueOf(dataObj[14].toString()) : 0;

						PayIncrementCustomVO customVo = new PayIncrementCustomVO();
						customVo.setEmpName(empName);
						customVo.setScaleEndAmount(scaleEndAmount);
						customVo.setUserid(userId);
						customVo.setBasic(basic);

						if (!nextincrementDate.equals(""))
							customVo.setNextincrementDate(sdf.format(sdfParse.parse(nextincrementDate)));

						if (!withEffectiveDate.equals(""))
							customVo.setWithEffectiveDate(sdf.format(sdfParse.parse(withEffectiveDate)).toString());

						if (!incrementOrderDate.equals(""))
						{
							logger.error("Increment Order Date in View Scrren" + incrementOrderDate);
							customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)).toString());
						}
						customVo.setRemarks(remarks);
						customVo.setNewBasic(newBasic);
						customVo.setStatus(status);
						customVo.setPayCommissionId(commisionId);
						customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
						empCustomVOList.add(customVo);
					}

					objectArgs.put("fixListWithGoUpdate", empCustomVOList);
					objectArgs.put("fixListsizeWithGoUpdate", empCustomVOList.size());
					objectArgs.put("save", save);

					//for left side table

					List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
					long size = listoftable.size();
					empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
					empCustomVOList = new ArrayList();
					logger.error("listoftable*********" + listoftable.size());
					logger.error("empBasicList*********" + empBasicList.size());

					String empFame = "", empMame = "", empLame = "";
					empName = "";
					basic = 0;
					long userid = 0;
					scaleEndAmount = 0;
					nextincrementDate = "";
					String empIncrementOrderDate = "";
					int empBasicListSize = empBasicList.size();

					if (empBasicList != null && empBasicListSize != 0)
					{
						Object[] rowList = null;

						for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
						{
							empIncrementOrderDate = "";
							nextincrementDate = "";

							rowList = (Object[]) itr.next();

							empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
							empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
							empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
							empName = empFame + empMame + empLame;
							basic = rowList[3] != null ? Long.parseLong(rowList[3].toString().trim()) : 0;
							userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0;
							scaleEndAmount = (rowList[5] != null && rowList[8] != null ) ? ( Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim())): 0;
							empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
							scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0;
							logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);

							if (empIncrementOrderDate != null && empIncrementOrderDate != "" && empIncrementOrderDate.length() > 0)
							{
								empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
								String tempCurrDate = sdf.format(currDate);
								String currDateArr[] = tempCurrDate.split("/");
								Long currYear = Long.parseLong(currDateArr[2]);
								currYear = currYear + 1;
								currDateArr[2] = currYear.toString();
								nextincrementDate = "01" + "/" + "07" + "/" + currYear;
							}

							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
							logger.error("nextincrementDate*********" + nextincrementDate);
							logger.error("empIncrementOrderDate*********" + empIncrementOrderDate);

							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							customVo.setNextincrementDate(nextincrementDate);
							customVo.setUserid(userid);
							customVo.setScaleEndAmount(scaleEndAmount);
							customVo.setIncrementOrderDate(empIncrementOrderDate);
							customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
							customVo.setPayCommissionId(payCommId);
							empCustomVOList.add(customVo);
							logger.error("empCustomVOList size ****************" + empCustomVOList.size());
						}
						
						objectArgs.put("save", save);
						objectArgs.put("fixListWithGo", empCustomVOList);
						objectArgs.put("fixListsizeWithGo", empCustomVOList.size());
						objectArgs.put("billNo", billNo);
						objectArgs.put("payCommId", payCommId);
						objectArgs.put("orderNo", orderNo);
						objectArgs.put("orderDate", orderDate);
						logger.info("billList size is "+billList.size());
						objectArgs.put("billList1", billList);
						objectArgs.put("ddoCodeOfOrder", ddoCodeOfOrder);
						
					}
					else
					{
						objectArgs.put("msg", "");
					}

					getInitialData(objectArgs);
				}
				else if (save.equals("Verify"))
				{
					logger.error("orderNo**************** " + orderNo);
					String ddoCodeOfOrder= StringUtility.getParameter("ddoCodeOfOrder", request);
					List dataList = employeeIncrementDAOImpl.getDetailOfEmployeeByOrderNo(orderNo,ddoCodeOfOrder);
					GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrEisOtherDtls.class);
					logger.info("all ok 1");
					NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());
					logger.info("all ok 2");
					NewRegTreasuryDAO lObjNewRegTreasuryDAO1 = new NewRegTreasuryDAOImpl(MstEmpDetails.class, serv.getSessionFactory());
					logger.info("all ok 3");
					genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
					//					List userIdsList = employeeIncrementDAOImpl.getUserIdFromHrPayfixMst(orderNo);
					int dataListSize = dataList.size();
					//					String userIdStr = "";
					Map<Long, Long> amountMap = new HashMap();
					if (!dataList.isEmpty())
					{
						for (int i = 0; i < dataListSize; i++)
						{
							Object[] dataObj = (Object[]) dataList.get(i);
							long userIdL = Long.valueOf(dataObj[0].toString());
							amountMap.put(userIdL, Long.valueOf(dataObj[1].toString()));

						}
					}
					logger.info("all ok 4");
					for (int i = 0; i < dataListSize; i++)
					{logger.info("all ok 5");
						Object[] dataObj = (Object[]) dataList.get(i);
						long userIdL = Long.valueOf(dataObj[0].toString());
						if (amountMap.containsKey(userIdL))
						{logger.info("all ok 6");
							HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls) dataObj[2];
							Long amount = Long.valueOf(amountMap.get(userIdL).toString());
							/*hrEisOtherDtls.setOtherCurrentBasic(amount);*/
							logger.info("all ok 6");
							/*genericDaoHibernateImpl.update(hrEisOtherDtls);*/
							logger.info("all ok 7");
							MstEmp dcpsEmp= (MstEmp)dataObj[3];
							logger.info("**************all ok 8***********");
							MstEmpDetails dcpsEmpDetails=(MstEmpDetails) lObjNewRegTreasuryDAO1.read(dcpsEmp.getDcpsEmpId());
							logger.info("all ok 9");
							logger.info("amount is **********"+amount);
							//dcpsEmpDetails.setBasicPay(Double.parseDouble(amount.toString()));
							logger.info("all ok 10");
							logger.info("PayCommission Id >>>>>> 111 "+dcpsEmp.getPayCommission());
							/*Added By Shivram 18072019*/
							if(dcpsEmp.getPayCommission().equals("700005")){
								logger.info("PayCommission Id >>>>>> 222 "+dcpsEmp.getPayCommission());
								dcpsEmp.setSevenPcBasic(Double.valueOf(amount));
								hrEisOtherDtls.setOtherCurrentSevenBasic(amount);
							}else{
								
								logger.info("PayCommission Id >>>>>> 333 "+dcpsEmp.getPayCommission());
								dcpsEmpDetails.setBasicPay(Double.parseDouble(amount.toString()));
								hrEisOtherDtls.setOtherCurrentBasic(amount);
								genericDaoHibernateImpl.update(hrEisOtherDtls);
								dcpsEmp.setBasicPay((double)amount);
								lObjNewRegTreasuryDAO1.update(dcpsEmpDetails);
							}
							//added by roshan for pipb update
							Long gradePay=dcpsEmp.getGradePay();
							logger.info("hiii the grade pay is "+gradePay);
							logger.info("hiii the grade pay is "+dcpsEmp.getSevarthId());
							Long pipb=(amount-gradePay);
							logger.info("hiii the pipb is "+pipb);
							dcpsEmp.setPayInPayBand(pipb);
							logger.info("all ok 11");
							dcpsEmpDetails.setPayInPayBand(pipb);
							//ended by roshan for pipb update
							lObjNewRegTreasuryDAO.update(dcpsEmp);
							logger.info("all ok 12");
							/*lObjNewRegTreasuryDAO1.update(dcpsEmpDetails);*/
							logger.info("all ok 13");
							amountMap.remove(userIdL);
						}
					}
					Long gLngPostId = SessionHelper.getPostId(objectArgs);
					Date date = new Date();
					int year = date.getYear();
					int currentYear = year + 1900;
					String currYear=String.valueOf(currentYear);
					CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", 1);
					if(StringUtility.getParameter("year", request)!=null && !StringUtility.getParameter("year", request).equals("") && Long.parseLong(StringUtility.getParameter("year", request))!=-1){
						currYear=StringUtility.getParameter("year", request);
					}
					logger.info("year is ************"+currYear);
					
					
					employeeIncrementDAOImpl.updateStatusOfHrPayfixMst(orderNo,ddoCodeOfOrder);
					List<HrPayfixMst> fixList = employeeIncrementDAOImpl.getAllDataForIncrement(gLngPostId,currYear);
					objectArgs.put("currYear", currYear);
					
					objectArgs.put("yearList", yearList);
					objectArgs.put("fixList", fixList);
					objectArgs.put("msg", "Order Verified Successfully");
					viewName="ApproveEmployeePayIncrList";
				}
				else if (save.equals("Reject"))
				{
					String ddoCodeOfOrder= StringUtility.getParameter("ddoCodeOfOrder", request);
					logger.error("orderNo**************** " + orderNo);
					//List dataList = employeeIncrementDAOImpl.getDetailOfEmployeeByOrderNo(orderNo,ddoCodeOfOrder);
					employeeIncrementDAOImpl.rejectHrPayfixMst(orderNo,ddoCodeOfOrder);
					objectArgs.put("msg", "Order Rejected Successfully");
					viewName="ApproveEmployeePayIncrList";
				}
			}

			objectArgs.put("save", save);
			objectArgs.put("incrementOrderNo", orderNo);
			objectArgs.put("incrementorderDate", orderDate);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName(viewName);
		}
		catch (Exception e)
		{     
		        e.printStackTrace();
			resultObject.setResultCode(ErrorConstants.ERROR);
			logger.error(e);
		}
		return resultObject;
	}

	public ResultObject getIncPrintReport(Map objectArgs)
	{
		Log logger = LogFactory.getLog(getClass());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		logger.error("getIncPrintReport******* method for print report*****");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");

			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String strLocId = (StringUtility.getParameter("locId", request));
			if(strLocId!="")
				locId=Long.parseLong(strLocId);			
			String orderNo = voToService.get("OrderNo") != null ? voToService.get("OrderNo").toString().trim() : "";

			logger.error("incrementOrderNo**********" + orderNo);

			EmployeeIncrementDAOImpl employeeIncrementDAOImpl = new EmployeeIncrementDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
			List printReportList = employeeIncrementDAOImpl.getPrintReportDataFromIncrementOrderNo(locId, orderNo);

			List officename= employeeIncrementDAOImpl.getOfficeName(locId);
			String officenam= officename.toString()!= null ? officename.toString() : "";
			if(officenam!="")
			{
				officenam.replace('[', ' ');
				officenam.replace(']', ' ');
			}
			logger.info("Office is :"+officenam);
			
			/*for(Iterator iterator = officename.iterator(); iterator.hasNext())
			{
				
				Object[] row = (Object[]) officename.next();
				officenam = row[0] != null ? row[0].toString() : "";
				logger.info("Office is :"+officenam);
			}*/
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String employeeName = "";
			String employeeFName = "";
			String employeeMName = "";
			String employeeLName = "";

			String dsgnName = "";
			String withEffectiveDate = "";
			long oldBasic = 0;
			long newBasic = 0;
			String nextincrementDate = "";
			String empDsgnName = "";
			String incrementOrderDate = "";
			String payCommissionId = "";

			List printReportCustomList = new ArrayList();

			for (Iterator iterator = printReportList.iterator(); iterator.hasNext();)
			{

				PayIncrementCustomVO customVO = new PayIncrementCustomVO();
				Object[] row = (Object[]) iterator.next();
				employeeFName = row[0] != null ? row[0].toString().concat(" ") : "";
				employeeMName = row[1] != null ? row[1].toString().concat(" ") : "";
				employeeLName = row[2] != null ? row[2].toString() : "";
				employeeName = employeeFName + employeeMName + employeeLName;

				dsgnName = row[3] != null ? row[3].toString() : "";
				withEffectiveDate = row[4] != null ? dateFormat.format(sdf.parse((row[4].toString()))) : "";
				oldBasic = row[5] != null ? Long.parseLong(row[5].toString()) : 0;
				newBasic = row[6] != null ? Long.parseLong(row[6].toString()) : 0;
				nextincrementDate = row[7] != null ? dateFormat.format(sdf.parse((row[7].toString()))) : "";
				incrementOrderDate = row[8] != null ? dateFormat.format(sdf.parse((row[8].toString()))) : "";
				/*Added By Shivram 23072019*/
				payCommissionId = row[9] != null ? row[9].toString() : "";
				/*Endede By Shivram*/
				customVO.setEmployeeName(employeeName);
				customVO.setDsgnName(dsgnName);
				customVO.setWithEffectiveDate(withEffectiveDate);
				customVO.setBasic(oldBasic);
				customVO.setNewBasic(newBasic);
				customVO.setNextincrementDate(nextincrementDate);
				customVO.setIncrementOrderDate(incrementOrderDate);

				empDsgnName = employeeName + "(" + dsgnName + ")";
				customVO.setEmpDsgnName(empDsgnName);

				long newbasic = customVO.getNewBasic();
				long basic = customVO.getBasic();
				long percentageAmount = newbasic - basic;

				logger.error("newbasic**********" + newbasic);
				logger.error("basic**********" + basic);
				logger.error("percentageAmount**********" + percentageAmount);

				customVO.setPercentageAmount(percentageAmount);
				printReportCustomList.add(customVO);

			}
			long reportlistsize = printReportCustomList.size();

			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			logger.error("10. " + format.format(now));

			objectArgs.put("payCommissionId", payCommissionId);
			
			objectArgs.put("printReportCustomList", printReportCustomList);
			objectArgs.put("reportlistsize", reportlistsize);
			objectArgs.put("verificationTime", format.format(now));
			objectArgs.put("officename", officenam) ;
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("incPrintReport");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}

		return resultObject;
	}
	
	public ResultObject getIncrementDataForReptDDO(Map objectArgs)
	{
		Log logger = LogFactory.getLog(getClass());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		//added by roshan
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		//end by roshan
		try
		{
			logger.error("===INSIDE IN getInitialData===");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			EmployeeIncrementDAOImpl employeeIncrementDAOImpl = new EmployeeIncrementDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			//			Map voToService = (Map) objectArgs.get("voToServiceMap");

			//			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			//added by roshan
			long locationCodeToUpdate=0;
			if((StringUtility.getParameter("locationCodeToUpdate", request)!=null)&&(StringUtility.getParameter("locationCodeToUpdate", request)!="")){
				locationCodeToUpdate= Long.parseLong(StringUtility.getParameter("locationCodeToUpdate", request));
		    	   }
			String orderNumberToUpdate=null;
			if((StringUtility.getParameter("orderNumberToUpdate", request)!=null)&&(StringUtility.getParameter("orderNumberToUpdate", request)!="")){
				orderNumberToUpdate= StringUtility.getParameter("orderNumberToUpdate", request);
		    	}
			if (locationCodeToUpdate!=0){
			//	employeeIncrementDAOImpl.UpdateLocCode(orderNumberToUpdate,locationCodeToUpdate);	
			}
			//ended by roshan
			Date date = new Date();
			int year = date.getYear();
			int currentYear = year + 1900;
			String currYear= String.valueOf(currentYear);
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", 1);
			if(StringUtility.getParameter("year", request)!=null && !StringUtility.getParameter("year", request).equals("") && Long.parseLong(StringUtility.getParameter("year", request))!=-1){
				currYear=StringUtility.getParameter("year", request);
			}
			logger.info("year is ************"+currYear);
			List<HrPayfixMst> fixList = employeeIncrementDAOImpl.getAllDataForIncrement(gLngPostId,currYear);
			String incrementOrderNo = "";
			String incrementOrderDate = "";
			String status = "";
			long countOfEmployees = 0;
			List empCustomVOList = new ArrayList();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

			Object[] row = null;
			PayIncrementCustomVO customVo = null;
			for (Iterator itr = fixList.iterator(); itr.hasNext();)
			{
				row = (Object[]) itr.next();
				incrementOrderNo = row[0] != null ? row[0].toString() : "";
				incrementOrderDate = row[2] != null ? row[2].toString() : "";
				status = row[3] != null ? row[3].toString() : "";
				//countOfEmployees = row[3] != null ? row[2].toString() : "";

				customVo = new PayIncrementCustomVO();
				customVo.setIncrementOrderNo(incrementOrderNo);
				if (!incrementOrderDate.equals(""))
					customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)));
				customVo.setStatus(status);
				//customVo.setCountOfEmployees(countOfEmployees);
				empCustomVOList.add(customVo);
			}
			objectArgs.put("currYear", currYear);
			
			objectArgs.put("yearList", yearList);
			objectArgs.put("fixList", fixList);
			objectArgs.put("fixListsize", fixList.size());
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ApproveEmployeePayIncrList");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
		}
		return resultObject;
	}
	//added by roshan 
	public ResultObject EditPayIncDataForNewOrderNo(Map objectArgs)
	{
		Log logger = LogFactory.getLog(getClass());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.error("EditDataForNewOrderNo****enter");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		Long locId=null;
		Long payCommId=null;
		Long billNo=null;
		String orderDate=null;
		Date currDate = new Date();
		long scaleNxtIncrAmt = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			logger.error("===INSIDE IN getInitialData===");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			EmployeeIncrementDAOImpl employeeIncrementDAOImpl = new EmployeeIncrementDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			String orderNumber=null;
			if((StringUtility.getParameter("orderNo", request)!=null)&&(StringUtility.getParameter("orderNo", request)!="")){
				orderNumber= StringUtility.getParameter("orderNo", request);
				logger.info("hhii the order number to be edited is"+orderNumber);
		    }
		
			//locId=employeeIncrementDAOImpl.getLocationCode(orderNumber);
			//payCommId=employeeIncrementDAOImpl.getpayCommID(orderNumber);
			//billNo=employeeIncrementDAOImpl.getBillNo(orderNumber);
			//orderDate=employeeIncrementDAOImpl.getOrderDate(orderNumber);
			
			List billList = employeeIncrementDAOImpl.getAllBillsByLoc(locId);
			logger.info("hii the list Size is");
			List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
			long size = listoftable.size();
			List empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
			List empCustomVOList = new ArrayList();
			logger.error("listoftable*********" + listoftable.size());
			logger.error("empBasicList*********" + empBasicList.size());
			//---FOR DISPALYING THE LIST
			
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			List<HrPayfixMst> fixList = employeeIncrementDAOImpl.getAllDataForEditIncrement(gLngPostId,orderNumber);
			/*	String incrementOrderNo = "";
			String incrementOrderDate = "";
			String status = "";
			long countOfEmployees = 0;
			List empCustomVOList2 = new ArrayList();
		
				Object[] row = null;
			PayIncrementCustomVO customVo = null;
			for (Iterator itr = fixList.iterator(); itr.hasNext();)
			{
				row = (Object[]) itr.next();
				incrementOrderNo = row[0] != null ? row[0].toString() : "";
				incrementOrderDate = row[2] != null ? row[2].toString() : "";
				status = row[3] != null ? row[3].toString() : "";
				//countOfEmployees = row[3] != null ? row[2].toString() : "";

				customVo = new PayIncrementCustomVO();
				customVo.setIncrementOrderNo(incrementOrderNo);
				if (!incrementOrderDate.equals(""))
					customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)));
				customVo.setStatus(status);
				//customVo.setCountOfEmployees(countOfEmployees);
				empCustomVOList.add(customVo);
			}
			*/
			objectArgs.put("fixList", fixList);
			objectArgs.put("fixListsize", fixList.size());
			//FOR DISPALYING THE LIST
			String empFame = "", empMame = "", empLame = "", empName = "";
			long basic = 0;
			long userid = 0;
			long scaleEndAmount = 0;
			String nextincrementDate = "";
			String empIncrementOrderDate = "";
			int empBasicListSize = empBasicList.size();

			if (empBasicList != null && empBasicListSize != 0)
			{
				Object[] rowList = null;

				for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
				{
					empIncrementOrderDate = "";
					nextincrementDate = "";

					rowList = (Object[]) itr.next();

					empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
					empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
					empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
					empName = empFame + empMame + empLame;
					basic = rowList[3] != null ? Long.parseLong(rowList[3].toString().trim()) : 0;
					userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0;
					scaleEndAmount = (rowList[5] != null && rowList[8] != null ) ? ( Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim())): 0;
					empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
					scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0;

					logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);

					if (empIncrementOrderDate != null && empIncrementOrderDate != "" && empIncrementOrderDate.length() > 0)
					{
						empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
						String tempCurrDate = sdf.format(currDate);
						String currDateArr[] = tempCurrDate.split("/");
						Long currYear = Long.parseLong(currDateArr[2]);
						currYear = currYear + 1;
						currDateArr[2] = currYear.toString();
						nextincrementDate = "01" + "/" + "07" + "/" + currYear;
					}

					PayIncrementCustomVO customVo = new PayIncrementCustomVO();
					logger.error("nextincrementDate*********" + nextincrementDate);
					logger.error("empIncrementOrderDate*********" + empIncrementOrderDate);

					customVo.setEmpName(empName);
					customVo.setBasic(basic);
					customVo.setNextincrementDate(nextincrementDate);
					customVo.setUserid(userid);
					customVo.setScaleEndAmount(scaleEndAmount);
					customVo.setIncrementOrderDate(empIncrementOrderDate);
					customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
					customVo.setPayCommissionId(payCommId);
					empCustomVOList.add(customVo);
					logger.error("empCustomVOList size ****************" + empCustomVOList.size());
				}
				//objectArgs.put("save", save);
				objectArgs.put("fixListWithGo", empCustomVOList);
				objectArgs.put("fixListsizeWithGo", empCustomVOList.size());
				objectArgs.put("billNo", billNo);
				objectArgs.put("payCommId", payCommId);
				objectArgs.put("orderNo", orderNumber);
				objectArgs.put("orderDate", orderDate);
				objectArgs.put("billList", billList);
				
				objectArgs.put("locId", locId);
			}
			else
			{
				objectArgs.put("msg", "There is no any employee matching selection criteria");
			}

			getInitialData(objectArgs);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("EmployeePayIncrListByLevelTwo");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
			resultObject.setResultCode(ErrorConstants.ERROR);
		}
		return resultObject;
	}
	//ended by roshan

	/*Added By Shivram 11072019*/
	
	public ResultObject showPayIncDataForNewOrderNoForSevenPc(Map objectArgs)
	  {
		

		Log logger = LogFactory.getLog(getClass());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.error("showDataForNewOrderNo****enter");
		String viewName="EmployeePayIncrList";
		//added by roshan
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		//end by roshan
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			EmployeeIncrementDAOImpl employeeIncrementDAOImpl = new EmployeeIncrementDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			Map voToService = (Map) objectArgs.get("voToServiceMap");

			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			Date currDate = new Date();
			long scaleNxtIncrAmt = 0;
			//added by roshan
			Long locCodeSelected=null;
			if((StringUtility.getParameter("loc_code", request)!=null)&&(StringUtility.getParameter("loc_code", request)!="")){
				locCodeSelected= Long.parseLong(StringUtility.getParameter("loc_code", request));
		    	   
		    	   }
			
			//end by roshan
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);

			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());

			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			IdGenerator idGenerator = new IdGenerator();

			String save = voToService.get("save") != null ? voToService.get("save").toString() : "";
			String orderNo = voToService.get("orderNo") != null ? voToService.get("orderNo").toString() : "";
			String orderDate = voToService.get("orderdate") != null ? voToService.get("orderdate").toString() : "";
			String status = null;

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

			List duplicateList = employeeIncrementDAOImpl.getDataForDuplicateMessage(orderNo, locId, langId);

			if (duplicateList.size() > 0 && save.equalsIgnoreCase("N"))
			{
				objectArgs.put("msg", "This Increment Certificate Order No already exists !!!");
			}
			else
			{
				if ("N".equalsIgnoreCase(save))
				{
					long billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()) : 0;
					long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;

					List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
					long size = listoftable.size();
					List empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
					List empCustomVOList = new ArrayList();
					logger.error("listoftable*********" + listoftable.size());
					logger.error("empBasicList*********" + empBasicList.size());

					String empFame = "", empMame ="", empLame = "", empName = "";
					long basic = 0;
					long userid = 0;
					long scaleEndAmount = 0;
					String nextincrementDate = "";
					String empIncrementOrderDate = "";
					int empBasicListSize = empBasicList.size();

					if (empBasicList != null && empBasicListSize != 0)
					{
						Object[] rowList = null;

						for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
						{
							empIncrementOrderDate = "";
							nextincrementDate = "";

							rowList = (Object[]) itr.next();

							empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
							empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
							empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
							empName = empFame + empMame + empLame;
							basic = rowList[10] != null ? Long.parseLong(rowList[10].toString().trim()) : 0;
							userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0;
							scaleEndAmount = (rowList[5] != null && rowList[8] != null ) ? ( Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim())): 0;
							empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
							scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0;

							logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);

							if (empIncrementOrderDate != null && empIncrementOrderDate != "" && empIncrementOrderDate.length() > 0)
							{
								empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
								String tempCurrDate = sdf.format(currDate);
								String currDateArr[] = tempCurrDate.split("/");
								Long currYear = Long.parseLong(currDateArr[2]);
								currYear = currYear + 1;
								currDateArr[2] = currYear.toString();
								nextincrementDate = "01" + "/" + "07" + "/" + currYear;
							}

							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
							logger.error("nextincrementDate*********" + nextincrementDate);
							logger.error("empIncrementOrderDate*********" + empIncrementOrderDate);

							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							logger.info("basic 111"+basic);
							customVo.setNextincrementDate(nextincrementDate);
							customVo.setUserid(userid);
							customVo.setScaleEndAmount(scaleEndAmount);
							customVo.setIncrementOrderDate(empIncrementOrderDate);
							customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
							customVo.setPayCommissionId(payCommId);
							empCustomVOList.add(customVo);
							logger.error("empCustomVOList size ****************" + empCustomVOList.size());
						}
						objectArgs.put("save", save);
						objectArgs.put("fixListWithGo", empCustomVOList);
						objectArgs.put("fixListsizeWithGo", empCustomVOList.size());
						objectArgs.put("billNo", billNo);
						objectArgs.put("payCommId", payCommId);
						objectArgs.put("orderNo", orderNo);
						objectArgs.put("orderDate", orderDate);
					}
					else
					{
						objectArgs.put("msg", "There is no any employee matching selection criteria");
					}

					getInitialData(objectArgs);
				}
				else if (save.equals("edit"))
				{
					viewName="EmployeePayIncrListByLevelTwo";
					logger.info("hii i m roshan in edit functionality for annual increment ");
					long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;
					
					String empIdstoBeAttached = voToService.get("empIdstoBeAttached") != null ? voToService.get("empIdstoBeAttached").toString() : "";
					logger.error("empIdstoBeAttached are ****************" + empIdstoBeAttached);
					String empIdsStr[] = empIdstoBeAttached.split("~");
					logger.error("empIdstoBeAttached**********" + empIdsStr);

					String empIdstoBeDetached = voToService.get("empIdstoBeDetached") != null ? voToService.get("empIdstoBeDetached").toString() : "";
					logger.error("empIdstoBeDetached are ****************" + empIdstoBeDetached);
					String empIdsDetachStr[] = empIdstoBeDetached.split("~");
					logger.error("empIdstoBeDetached**********" + empIdsDetachStr);

					String empBasicSalarytoBeAttached = voToService.get("empBasicSalarytoBeAttached") != null ? voToService.get("empBasicSalarytoBeAttached").toString() : "";
					logger.error("empBasicSalarytoBeAttached are ****************" + empBasicSalarytoBeAttached);
					String empBasicStr[] = empBasicSalarytoBeAttached.split("~");
					logger.error("empBasicSalarytoBeAttached**********" + empBasicStr);

					String empWEFtoBeAttached = voToService.get("empWEFtoBeAttached") != null ? voToService.get("empWEFtoBeAttached").toString() : "";
					logger.error("empWEFtoBeAttached are ****************" + empWEFtoBeAttached);
					String empWEFStr[] = empWEFtoBeAttached.split("~");
					logger.error("empWEFtoBeAttached**********" + empWEFStr);

					String inputTagElements = voToService.get("inputTagElements") != null ? voToService.get("inputTagElements").toString() : "";
					logger.error("inputTagElements are ****************" + inputTagElements);
					String inputElementsUpdate[] = inputTagElements.split("~");
					logger.error("inputElementsUpdate**********" + inputElementsUpdate);

					Date empWEFUpdate;
					String empRemarksUpdate = "";
					long empIdsUpdate;
					Map<Long, Object> remarksDataMap = new HashMap<Long, Object>();
					for (int i = 0; i < inputElementsUpdate.length; i += 3)
					{
						logger.info("inputElementsUpdate[i]"+inputElementsUpdate[i]);
						empIdsUpdate = Long.valueOf(inputElementsUpdate[i]);
						empWEFUpdate = sdf.parse(inputElementsUpdate[i + 1]);
						if (inputElementsUpdate[i + 2] != null && inputElementsUpdate[i + 2] != "")
							empRemarksUpdate = inputElementsUpdate[i + 2];
						else
							empRemarksUpdate = "";

						Object[] dataForPut = { empWEFUpdate, empRemarksUpdate };
						remarksDataMap.put(empIdsUpdate, dataForPut);
					}

					String empNextIncrDatetoBeAttached = voToService.get("empNextIncrDatetoBeAttached") != null ? voToService.get("empNextIncrDatetoBeAttached").toString() : "";
					logger.error("empNextIncrDatetoBeAttached are ****************" + empNextIncrDatetoBeAttached);
					String empNextIncrStr[] = empNextIncrDatetoBeAttached.split("~");
					logger.error("empNextIncrDatetoBeAttached**********" + empNextIncrStr);

					String empRemarkstoBeAttached = voToService.get("empRemarkstoBeAttached") != null ? voToService.get("empRemarkstoBeAttached").toString() : "";
					logger.error("empRemarkstoBeAttached are ****************" + empRemarkstoBeAttached);
					String empRemarksStr[] = empRemarkstoBeAttached.split("~");
					logger.error("empRemarkstoBeAttached**********" + empRemarksStr);

					String empIncOrderDatetoBeAttached = voToService.get("empIncOrderDatetoBeAttached") != null ? voToService.get("empIncOrderDatetoBeAttached").toString() : "";
					logger.error("empIncOrderDatetoBeAttached are ****************" + empIncOrderDatetoBeAttached);
					String empIncOrderDateStr[] = empIncOrderDatetoBeAttached.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empIncOrderDateStr);

					String origBasictoBeAttached = voToService.get("origBasictoBeAttached") != null ? voToService.get("origBasictoBeAttached").toString() : "";
					logger.error("empOrigBasictoBeAttached are ****************" + origBasictoBeAttached);
					String empOrigBasicStr[] = origBasictoBeAttached.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empOrigBasicStr);

					String empPayCommissionId = voToService.get("empPayCommissionId") != null ? voToService.get("empPayCommissionId").toString() : "";
					logger.error("empPayCommissionId are ****************" + empPayCommissionId);
					String empPayCommissionIdStr[] = empPayCommissionId.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empPayCommissionIdStr);

					Map<Long, Long> payCommIdMap = new HashMap<Long, Long>();
					for (int i = 0; i < empPayCommissionIdStr.length; i += 2)
					{
						long empId = Long.valueOf(empPayCommissionIdStr[i]);
						long payId = Long.valueOf(empPayCommissionIdStr[i + 1]);
						payCommIdMap.put(empId, payId);
					}

					//Deleting all records against orderno and order date and then insert
					Date incrOrderDt = null;
					if (orderDate.toString() != null && orderDate.length() > 0 && orderDate.toString() != "")
					{
						incrOrderDt = sdf.parse(orderDate);
					}
					//added by roshan for location code update 
					String ddoCodeOfOrder= StringUtility.getParameter("ddoCodeOfOrder", request);
					Long locationCodeOfLevel1=employeeIncrementDAOImpl.getLocationCode(orderNo,ddoCodeOfOrder);
					logger.info("hii level 1 loc isd****************"+locationCodeOfLevel1);
					//ended by roshan :end
					employeeIncrementDAOImpl.deleteRecordsByOrderNumber(orderNo, incrOrderDt);

					if (empIdsStr != null && empIdsStr.length > 0 && empIdsStr[0] != "")
					{
						for (int i = 0; i < empIdsStr.length; i++)
						{
							long oldBasic = 0;
							long newBasic = 0;
							Date wefDate = null;
							Date nextIncrDt = null;
							String remarks = null;
							long empIdToInsert = 0;
							incrOrderDt = null;

							if (empIdsStr[i] != null && empIdsStr.length > 0 && empIdsStr[i] != "")
							{
								empIdToInsert = Long.parseLong(empIdsStr[i]);
								logger.info("hii the emp_id is "+empIdToInsert);
							}
							if (empBasicStr[i] != null && empBasicStr.length > 0 && empBasicStr[i] != "")
							{
								newBasic = Long.parseLong(empBasicStr[i]);
							}
							if (empOrigBasicStr[i] != null && empOrigBasicStr.length > 0 && empOrigBasicStr[i] != "")
							{
								oldBasic = Long.parseLong(empOrigBasicStr[i]);
								logger.error("Old Basic from empOrigBasicStr--" + oldBasic);
							}

							status = "Forwaded";
							if (empNextIncrStr[i] != null && empNextIncrStr.length > 0 && empNextIncrStr[i] != "")
							{
								nextIncrDt = sdf.parse(empNextIncrStr[i]);
							}

							Object[] data = (Object[]) remarksDataMap.get(empIdToInsert);
							wefDate = (Date) data[0];
							remarks = data[1].toString();

							if (orderDate.toString() != null && orderDate.length() > 0 && orderDate.toString() != "")
							{
								incrOrderDt = sdf.parse(orderDate);
							}

							payCommId = Long.valueOf(payCommIdMap.get(empIdToInsert).toString());

							OrgUserMst orgUserMstVO = orgUserMstDaoImpl.read(empIdToInsert);
							long payfixPkId = idGenerator.PKGenerator("HR_PAYFIX_MST", objectArgs);
							HrPayfixMst hrPayfixMst = new HrPayfixMst();
							hrPayfixMst.setCmnDatabaseMst(cmnDatabaseMst);
							hrPayfixMst.setCmnLanguageMst(cmnLanguageMst);
							//Added by roshan to edit the order from level 2
							if(locationCodeOfLevel1!=null){
								CmnLocationMstDaoImpl cmnLocationMstDaoImpl1 = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
								cmnLocationMst = cmnLocationMstDaoImpl.read(locationCodeOfLevel1);

								hrPayfixMst.setCmnLocationMst(cmnLocationMst);
							}
							//ended by roshan
							else{
							hrPayfixMst.setCmnLocationMst(cmnLocationMst);
							
							}
							hrPayfixMst.setCreatedDate(currDate);
							hrPayfixMst.setIncrementOrderDate(incrOrderDt);
							hrPayfixMst.setIncrementOrderNo(orderNo);
							hrPayfixMst.setNewBasic(newBasic);
							logger.info("newBasic 222"+newBasic);
							hrPayfixMst.setNxtIncrDate(nextIncrDt);
							hrPayfixMst.setOldBasic(oldBasic);
							logger.info("oldBasic 333"+oldBasic);
							hrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
							hrPayfixMst.setOrgPostMstUpdatedByPost(orgPostMst);
							hrPayfixMst.setOrgUserMstCreatedBy(orgUserMstVO);
							hrPayfixMst.setOrgUserMstUpdatedBy(orgUserMstVO);
							hrPayfixMst.setPayFixDate(wefDate);
							hrPayfixMst.setPayfixId(payfixPkId);
							hrPayfixMst.setRemarks(remarks);
							hrPayfixMst.setStatus(status);
							hrPayfixMst.setUpdatedDate(currDate);
							hrPayfixMst.setUserId(orgUserMstVO);
							hrPayfixMst.setActivateFlag(1);
							hrPayfixMst.setTrnCounter(1L);
				
							hrPayfixMst.setPayCommissionId(payCommId);
							employeeIncrementDAOImpl.create(hrPayfixMst);
							objectArgs.put("locationCodeOfLevel1", locationCodeOfLevel1);
							objectArgs.put("orderNumberToUpdate", orderNo);
							objectArgs.put("msg", "Annual increment has been modified Successfully for verification");
							
						}
					}
				}
				else if (save.equals("Yes"))
				{

					long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;

					String empIdstoBeAttached = voToService.get("empIdstoBeAttached") != null ? voToService.get("empIdstoBeAttached").toString() : "";
					logger.error("empIdstoBeAttached are ****************" + empIdstoBeAttached);
					String empIdsStr[] = empIdstoBeAttached.split("~");
					logger.error("empIdstoBeAttached**********" + empIdsStr);

					String empIdstoBeDetached = voToService.get("empIdstoBeDetached") != null ? voToService.get("empIdstoBeDetached").toString() : "";
					logger.error("empIdstoBeDetached are ****************" + empIdstoBeDetached);
					String empIdsDetachStr[] = empIdstoBeDetached.split("~");
					logger.error("empIdstoBeDetached**********" + empIdsDetachStr);

					String empBasicSalarytoBeAttached = voToService.get("empBasicSalarytoBeAttached") != null ? voToService.get("empBasicSalarytoBeAttached").toString() : "";
					logger.error("empBasicSalarytoBeAttached are ****************" + empBasicSalarytoBeAttached);
					String empBasicStr[] = empBasicSalarytoBeAttached.split("~");
					logger.error("empBasicSalarytoBeAttached**********" + empBasicStr);

					String empWEFtoBeAttached = voToService.get("empWEFtoBeAttached") != null ? voToService.get("empWEFtoBeAttached").toString() : "";
					logger.error("empWEFtoBeAttached are ****************" + empWEFtoBeAttached);
					String empWEFStr[] = empWEFtoBeAttached.split("~");
					logger.error("empWEFtoBeAttached**********" + empWEFStr);

					String inputTagElements = voToService.get("inputTagElements") != null ? voToService.get("inputTagElements").toString() : "";
					logger.error("inputTagElements are ****************" + inputTagElements);
					String inputElementsUpdate[] = inputTagElements.split("~");
					logger.error("inputElementsUpdate**********" + inputElementsUpdate);

					Date empWEFUpdate;
					String empRemarksUpdate = "";
					long empIdsUpdate;
					Map<Long, Object> remarksDataMap = new HashMap<Long, Object>();
					for (int i = 0; i < inputElementsUpdate.length; i += 3)
					{
						logger.info("inputElementsUpdate[i]"+inputElementsUpdate[i]);
						empIdsUpdate = Long.valueOf(inputElementsUpdate[i]);
						empWEFUpdate = sdf.parse(inputElementsUpdate[i + 1]);
						if (inputElementsUpdate[i + 2] != null && inputElementsUpdate[i + 2] != "")
							empRemarksUpdate = inputElementsUpdate[i + 2];
						else
							empRemarksUpdate = "";

						Object[] dataForPut = { empWEFUpdate, empRemarksUpdate };
						remarksDataMap.put(empIdsUpdate, dataForPut);
					}

					String empNextIncrDatetoBeAttached = voToService.get("empNextIncrDatetoBeAttached") != null ? voToService.get("empNextIncrDatetoBeAttached").toString() : "";
					logger.error("empNextIncrDatetoBeAttached are ****************" + empNextIncrDatetoBeAttached);
					String empNextIncrStr[] = empNextIncrDatetoBeAttached.split("~");
					logger.error("empNextIncrDatetoBeAttached**********" + empNextIncrStr);

					String empRemarkstoBeAttached = voToService.get("empRemarkstoBeAttached") != null ? voToService.get("empRemarkstoBeAttached").toString() : "";
					logger.error("empRemarkstoBeAttached are ****************" + empRemarkstoBeAttached);
					String empRemarksStr[] = empRemarkstoBeAttached.split("~");
					logger.error("empRemarkstoBeAttached**********" + empRemarksStr);

					String empIncOrderDatetoBeAttached = voToService.get("empIncOrderDatetoBeAttached") != null ? voToService.get("empIncOrderDatetoBeAttached").toString() : "";
					logger.error("empIncOrderDatetoBeAttached are ****************" + empIncOrderDatetoBeAttached);
					String empIncOrderDateStr[] = empIncOrderDatetoBeAttached.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empIncOrderDateStr);

					String origBasictoBeAttached = voToService.get("origBasictoBeAttached") != null ? voToService.get("origBasictoBeAttached").toString() : "";
					logger.error("empOrigBasictoBeAttached are ****************" + origBasictoBeAttached);
					String empOrigBasicStr[] = origBasictoBeAttached.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empOrigBasicStr);

					String empPayCommissionId = voToService.get("empPayCommissionId") != null ? voToService.get("empPayCommissionId").toString() : "";
					logger.error("empPayCommissionId are ****************" + empPayCommissionId);
					String empPayCommissionIdStr[] = empPayCommissionId.split("~");
					logger.error("empIncOrderDatetoBeAttached**********" + empPayCommissionIdStr);

					Map<Long, Long> payCommIdMap = new HashMap<Long, Long>();
					for (int i = 0; i < empPayCommissionIdStr.length; i += 2)
					{
						long empId = Long.valueOf(empPayCommissionIdStr[i]);
						long payId = Long.valueOf(empPayCommissionIdStr[i + 1]);
						payCommIdMap.put(empId, payId);
					}

					//Deleting all records against orderno and order date and then insert
					Date incrOrderDt = null;
					if (orderDate.toString() != null && orderDate.length() > 0 && orderDate.toString() != "")
					{
						incrOrderDt = sdf.parse(orderDate);
					}
					
					//employeeIncrementDAOImpl.deleteRecordsByOrderNumber(orderNo, incrOrderDt);

					if (empIdsStr != null && empIdsStr.length > 0 && empIdsStr[0] != "")
					{
						for (int i = 0; i < empIdsStr.length; i++)
						{
							long oldBasic = 0;
							long newBasic = 0;
							Date wefDate = null;
							Date nextIncrDt = null;
							String remarks = null;
							long empIdToInsert = 0;
							incrOrderDt = null;

							if (empIdsStr[i] != null && empIdsStr.length > 0 && empIdsStr[i] != "")
							{
								empIdToInsert = Long.parseLong(empIdsStr[i]);
								logger.info("hii the emp_id is "+empIdToInsert);
							}
							if (empBasicStr[i] != null && empBasicStr.length > 0 && empBasicStr[i] != "")
							{
								newBasic = Long.parseLong(empBasicStr[i]);
							}
							if (empOrigBasicStr[i] != null && empOrigBasicStr.length > 0 && empOrigBasicStr[i] != "")
							{
								oldBasic = Long.parseLong(empOrigBasicStr[i]);
								logger.error("Old Basic from empOrigBasicStr--" + oldBasic);
							}

							status = "Forwaded";
							if (empNextIncrStr[i] != null && empNextIncrStr.length > 0 && empNextIncrStr[i] != "")
							{
								nextIncrDt = sdf.parse(empNextIncrStr[i]);
							}

							Object[] data = (Object[]) remarksDataMap.get(empIdToInsert);
							wefDate = (Date) data[0];
							remarks = data[1].toString();

							if (orderDate.toString() != null && orderDate.length() > 0 && orderDate.toString() != "")
							{
								incrOrderDt = sdf.parse(orderDate);
							}

							payCommId = Long.valueOf(payCommIdMap.get(empIdToInsert).toString());

							OrgUserMst orgUserMstVO = orgUserMstDaoImpl.read(empIdToInsert);
							long payfixPkId = idGenerator.PKGenerator("HR_PAYFIX_MST", objectArgs);
							HrPayfixMst hrPayfixMst = new HrPayfixMst();
							hrPayfixMst.setCmnDatabaseMst(cmnDatabaseMst);
							hrPayfixMst.setCmnLanguageMst(cmnLanguageMst);
							//if(locCodeSelected!=null){
								//hrPayfixMst.setCmnLocationMst(Long.parseLong(locCodeSelected));
							//}
							//else{
							hrPayfixMst.setCmnLocationMst(cmnLocationMst);
							
							//}
							hrPayfixMst.setCreatedDate(currDate);
							hrPayfixMst.setIncrementOrderDate(incrOrderDt);
							hrPayfixMst.setIncrementOrderNo(orderNo);
							hrPayfixMst.setNewBasic(newBasic);
							logger.info("newBasic 444"+newBasic);
							hrPayfixMst.setNxtIncrDate(nextIncrDt);
							hrPayfixMst.setOldBasic(oldBasic);
							logger.info("oldBasic 555"+oldBasic);
							hrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
							hrPayfixMst.setOrgPostMstUpdatedByPost(orgPostMst);
							hrPayfixMst.setOrgUserMstCreatedBy(orgUserMstVO);
							hrPayfixMst.setOrgUserMstUpdatedBy(orgUserMstVO);
							hrPayfixMst.setPayFixDate(wefDate);
							hrPayfixMst.setPayfixId(payfixPkId);
							hrPayfixMst.setRemarks(remarks);
							hrPayfixMst.setStatus(status);
							hrPayfixMst.setUpdatedDate(currDate);
							hrPayfixMst.setUserId(orgUserMstVO);
							hrPayfixMst.setActivateFlag(1);
							hrPayfixMst.setTrnCounter(1L);
				
							hrPayfixMst.setPayCommissionId(payCommId);
							employeeIncrementDAOImpl.create(hrPayfixMst);
							
							
							objectArgs.put("msg", "Annual increment has been forwarded Successfully for verification");
							
						}
					}
				}
				else if (save.equalsIgnoreCase("view"))
				{
					String empName = "";
					String empFname = "", empMname = "", empLname = "";
					long basic = 0;
					long userId = 0;
					String incrementOrderDate = "";
					long scaleEndAmount = 0;
					String nextincrementDate = "";
					String withEffectiveDate = "";
					String remarks = "";
					long newBasic = 0;
					status = "";
					long commisionId = 0;
					List empBasicList = null;

					long billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()) : 0;
					long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;

					List fixList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtlsByOrderNo(locId, orderNo);
					List empCustomVOList = new ArrayList();
					int fixLisSize = fixList.size();

					logger.error("Size of HrPayFixMst " + fixLisSize);
					Object[] dataObj = null;
					for (int i = 0; i < fixLisSize; i++)
					{
						incrementOrderDate = "";
						nextincrementDate = "";
						withEffectiveDate = "";
						status = "";
						empName = "";
						dataObj = (Object[]) fixList.get(i);
						empFname = dataObj[0] != null ? dataObj[0].toString().concat(" ") : "";
						empMname = dataObj[1] != null ? dataObj[1].toString().concat(" ") : "";
						empLname = dataObj[2] != null ? dataObj[2].toString() : "";
						empName = empFname + empMname + empLname;
						basic = dataObj[10] != null ? Long.valueOf(dataObj[10].toString()) : 0;
						userId = dataObj[4] != null ? Long.valueOf(dataObj[4].toString()) : 0;
						//scaleEndAmount = dataObj[5] != null ? Long.valueOf(dataObj[5].toString()) : 0;
						scaleEndAmount = (dataObj[5] != null && dataObj[8] != null ) ? ( Long.parseLong(dataObj[5].toString().trim()) + Long.parseLong(dataObj[8].toString().trim())): 0;
						withEffectiveDate = dataObj[6] != null ? dataObj[6].toString().trim() : "";
						remarks = dataObj[7] != null ? dataObj[7].toString().trim() : "";
						newBasic = dataObj[8] != null ? Long.valueOf(dataObj[8].toString()) : 0;
						nextincrementDate = dataObj[9] != null ? dataObj[9].toString().trim() : "";
						incrementOrderDate = dataObj[11] != null ? dataObj[11].toString().trim() : "";
						status = dataObj[12] != null ? dataObj[12].toString().trim() : "";
						commisionId = dataObj[13] != null ? Long.valueOf(dataObj[13].toString()) : 0;
						scaleNxtIncrAmt = dataObj[14] != null ? Long.valueOf(dataObj[14].toString()) : 0;

						PayIncrementCustomVO customVo = new PayIncrementCustomVO();
						customVo.setEmpName(empName);
						customVo.setScaleEndAmount(scaleEndAmount);
						customVo.setUserid(userId);
						customVo.setBasic(basic);

						if (!nextincrementDate.equals(""))
							customVo.setNextincrementDate(sdf.format(sdfParse.parse(nextincrementDate)));

						if (!withEffectiveDate.equals(""))
							customVo.setWithEffectiveDate(sdf.format(sdfParse.parse(withEffectiveDate)).toString());

						if (!incrementOrderDate.equals(""))
						{
							logger.error("Increment Order Date in View Scrren" + incrementOrderDate);
							customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)).toString());
						}
						customVo.setRemarks(remarks);
						customVo.setNewBasic(newBasic);
						logger.info("newBasic 666"+newBasic);
						customVo.setStatus(status);
						customVo.setPayCommissionId(commisionId);
						customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
						empCustomVOList.add(customVo);
					}

					objectArgs.put("fixListWithGoUpdate", empCustomVOList);
					objectArgs.put("fixListsizeWithGoUpdate", empCustomVOList.size());
					objectArgs.put("save", save);

					//for left side table

					List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
					long size = listoftable.size();
					empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
					empCustomVOList = new ArrayList();
					logger.error("listoftable*********" + listoftable.size());
					logger.error("empBasicList*********" + empBasicList.size());

					String empFame = "", empMame = "", empLame = "";
					empName = "";
					basic = 0;
					long userid = 0;
					scaleEndAmount = 0;
					nextincrementDate = "";
					String empIncrementOrderDate = "";
					int empBasicListSize = empBasicList.size();

					if (empBasicList != null && empBasicListSize != 0)
					{
						Object[] rowList = null;

						for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
						{
							empIncrementOrderDate = "";
							nextincrementDate = "";

							rowList = (Object[]) itr.next();

							empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
							empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
							empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
							empName = empFame + empMame + empLame;
							basic = rowList[10] != null ? Long.parseLong(rowList[10].toString().trim()) : 0;
							userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0;
							scaleEndAmount = (rowList[5] != null && rowList[8] != null ) ? ( Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim())): 0;
							empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
							scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0;
							logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);

							if (empIncrementOrderDate != null && empIncrementOrderDate != "" && empIncrementOrderDate.length() > 0)
							{
								empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
								String tempCurrDate = sdf.format(currDate);
								String currDateArr[] = tempCurrDate.split("/");
								Long currYear = Long.parseLong(currDateArr[2]);
								currYear = currYear + 1;
								currDateArr[2] = currYear.toString();
								nextincrementDate = "01" + "/" + "07" + "/" + currYear;
							}

							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
							logger.error("nextincrementDate*********" + nextincrementDate);
							logger.error("empIncrementOrderDate*********" + empIncrementOrderDate);

							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							logger.info("basic 777"+basic);
							customVo.setNextincrementDate(nextincrementDate);
							customVo.setUserid(userid);
							customVo.setScaleEndAmount(scaleEndAmount);
							customVo.setIncrementOrderDate(empIncrementOrderDate);
							customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
							customVo.setPayCommissionId(payCommId);
							empCustomVOList.add(customVo);
							logger.error("empCustomVOList size ****************" + empCustomVOList.size());
						}
						objectArgs.put("save", save);
						objectArgs.put("fixListWithGo", empCustomVOList);
						objectArgs.put("fixListsizeWithGo", empCustomVOList.size());
						objectArgs.put("billNo", billNo);
						objectArgs.put("payCommId", payCommId);
						objectArgs.put("orderNo", orderNo);
						objectArgs.put("orderDate", orderDate);
					}
					else
					{
						objectArgs.put("msg", "There is no any employee matching selection criteria");
					}

					getInitialData(objectArgs);
				}
				else if (save.equalsIgnoreCase("modify"))
				{
					logger.info("hii i m in modify.....");
					viewName="EmployeePayIncrListByLevelTwo";
					Long payCommId=null;
					Long billNo=null;
					
					String empName = "";
					String empFname = "", empMname = "", empLname = "";
					long basic = 0;
					long userId = 0;
					String incrementOrderDate = "";
					long scaleEndAmount = 0;
					String nextincrementDate = "";
					String withEffectiveDate = "";
					String remarks = "";
					long newBasic = 0;
					status = "";
					long commisionId = 0;
					List empBasicList = null;
					//added by roshan
					String orderNumber=null;
					if((StringUtility.getParameter("orderNo", request)!=null)&&(StringUtility.getParameter("orderNo", request)!="")){
						orderNumber= StringUtility.getParameter("orderNo", request);
						logger.info("hhii the order number to be edited is"+orderNumber);
				    }
					String ddoCodeOfOrder= StringUtility.getParameter("ddoCodeOfOrder", request);
					locId=employeeIncrementDAOImpl.getLocationCode(orderNumber,ddoCodeOfOrder);
					payCommId=employeeIncrementDAOImpl.getpayCommID(orderNumber,ddoCodeOfOrder);
					billNo=employeeIncrementDAOImpl.getBillNo(orderNumber,ddoCodeOfOrder);
					orderDate=employeeIncrementDAOImpl.getOrderDate(orderNumber,ddoCodeOfOrder);
					List billList = employeeIncrementDAOImpl.getAllBillsByDDO(ddoCodeOfOrder);
					//parsing order date
					orderDate = new DateUtility().convertToDDMMYYYY(orderDate.toString().split(" ")[0]);
					logger.info("hiii order date is "+orderDate);
					//end in parsing order date.
					logger.info("location code is roshan "+locId);
					logger.info("payCommId code is "+payCommId);
					logger.info("billNo code is "+billNo);
					//ended by roshan
					//billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()) : 0;
					//payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()) : 0;
					
					List fixList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtlsByOrderNo(locId, orderNo);
					List empCustomVOList = new ArrayList();
					int fixLisSize = fixList.size();

					logger.error("Size of HrPayFixMst " + fixLisSize);
					Object[] dataObj = null;
					for (int i = 0; i < fixLisSize; i++)
					{
						incrementOrderDate = "";
						nextincrementDate = "";
						withEffectiveDate = "";
						status = "";
						empName = "";
						dataObj = (Object[]) fixList.get(i);
						empFname = dataObj[0] != null ? dataObj[0].toString().concat(" ") : "";
						empMname = dataObj[1] != null ? dataObj[1].toString().concat(" ") : "";
						empLname = dataObj[2] != null ? dataObj[2].toString() : "";
						empName = empFname + empMname + empLname;
						basic = dataObj[10] != null ? Long.valueOf(dataObj[10].toString()) : 0;
						userId = dataObj[4] != null ? Long.valueOf(dataObj[4].toString()) : 0;
						//scaleEndAmount = dataObj[5] != null ? Long.valueOf(dataObj[5].toString()) : 0;
						scaleEndAmount = (dataObj[5] != null && dataObj[8] != null ) ? ( Long.parseLong(dataObj[5].toString().trim()) + Long.parseLong(dataObj[8].toString().trim())): 0;
						withEffectiveDate = dataObj[6] != null ? dataObj[6].toString().trim() : "";
						remarks = dataObj[7] != null ? dataObj[7].toString().trim() : "";
						newBasic = dataObj[8] != null ? Long.valueOf(dataObj[8].toString()) : 0;
						nextincrementDate = dataObj[9] != null ? dataObj[9].toString().trim() : "";
						incrementOrderDate = dataObj[11] != null ? dataObj[11].toString().trim() : "";
						status = dataObj[12] != null ? dataObj[12].toString().trim() : "";
						commisionId = dataObj[13] != null ? Long.valueOf(dataObj[13].toString()) : 0;
						scaleNxtIncrAmt = dataObj[14] != null ? Long.valueOf(dataObj[14].toString()) : 0;

						PayIncrementCustomVO customVo = new PayIncrementCustomVO();
						customVo.setEmpName(empName);
						customVo.setScaleEndAmount(scaleEndAmount);
						customVo.setUserid(userId);
						customVo.setBasic(basic);

						if (!nextincrementDate.equals(""))
							customVo.setNextincrementDate(sdf.format(sdfParse.parse(nextincrementDate)));

						if (!withEffectiveDate.equals(""))
							customVo.setWithEffectiveDate(sdf.format(sdfParse.parse(withEffectiveDate)).toString());

						if (!incrementOrderDate.equals(""))
						{
							logger.error("Increment Order Date in View Scrren" + incrementOrderDate);
							customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)).toString());
						}
						customVo.setRemarks(remarks);
						customVo.setNewBasic(newBasic);
						logger.info("newBasic 888"+newBasic);
						customVo.setStatus(status);
						customVo.setPayCommissionId(commisionId);
						customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
						empCustomVOList.add(customVo);
					}

					objectArgs.put("fixListWithGoUpdate", empCustomVOList);
					objectArgs.put("fixListsizeWithGoUpdate", empCustomVOList.size());
					objectArgs.put("save", save);

					//for left side table

					List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
					long size = listoftable.size();
					empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
					empCustomVOList = new ArrayList();
					logger.error("listoftable*********" + listoftable.size());
					logger.error("empBasicList*********" + empBasicList.size());

					String empFame = "", empMame = "", empLame = "";
					empName = "";
					basic = 0;
					long userid = 0;
					scaleEndAmount = 0;
					nextincrementDate = "";
					String empIncrementOrderDate = "";
					int empBasicListSize = empBasicList.size();

					if (empBasicList != null && empBasicListSize != 0)
					{
						Object[] rowList = null;

						for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
						{
							empIncrementOrderDate = "";
							nextincrementDate = "";

							rowList = (Object[]) itr.next();

							empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
							empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
							empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
							empName = empFame + empMame + empLame;
							basic = rowList[10] != null ? Long.parseLong(rowList[10].toString().trim()) : 0;
							userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0;
							scaleEndAmount = (rowList[5] != null && rowList[8] != null ) ? ( Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim())): 0;
							empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
							scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0;
							logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);

							if (empIncrementOrderDate != null && empIncrementOrderDate != "" && empIncrementOrderDate.length() > 0)
							{
								empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
								String tempCurrDate = sdf.format(currDate);
								String currDateArr[] = tempCurrDate.split("/");
								Long currYear = Long.parseLong(currDateArr[2]);
								currYear = currYear + 1;
								currDateArr[2] = currYear.toString();
								nextincrementDate = "01" + "/" + "07" + "/" + currYear;
							}

							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
							logger.error("nextincrementDate*********" + nextincrementDate);
							logger.error("empIncrementOrderDate*********" + empIncrementOrderDate);

							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							logger.info("basic 999"+basic);
							customVo.setNextincrementDate(nextincrementDate);
							customVo.setUserid(userid);
							customVo.setScaleEndAmount(scaleEndAmount);
							customVo.setIncrementOrderDate(empIncrementOrderDate);
							customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
							customVo.setPayCommissionId(payCommId);
							empCustomVOList.add(customVo);
							logger.error("empCustomVOList size ****************" + empCustomVOList.size());
						}
						
						objectArgs.put("save", save);
						objectArgs.put("fixListWithGo", empCustomVOList);
						objectArgs.put("fixListsizeWithGo", empCustomVOList.size());
						objectArgs.put("billNo", billNo);
						objectArgs.put("payCommId", payCommId);
						objectArgs.put("orderNo", orderNo);
						objectArgs.put("orderDate", orderDate);
						logger.info("billList size is "+billList.size());
						objectArgs.put("billList1", billList);
						objectArgs.put("ddoCodeOfOrder", ddoCodeOfOrder);
						
					}
					else
					{
						objectArgs.put("msg", "");
					}

					getInitialData(objectArgs);
				}
				else if (save.equals("Verify"))
				{
					logger.error("orderNo**************** " + orderNo);
					String ddoCodeOfOrder= StringUtility.getParameter("ddoCodeOfOrder", request);
					List dataList = employeeIncrementDAOImpl.getDetailOfEmployeeByOrderNo(orderNo,ddoCodeOfOrder);
					GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrEisOtherDtls.class);
					logger.info("all ok 1");
					NewRegTreasuryDAO lObjNewRegTreasuryDAO = new NewRegTreasuryDAOImpl(MstEmp.class, serv.getSessionFactory());
					logger.info("all ok 2");
					NewRegTreasuryDAO lObjNewRegTreasuryDAO1 = new NewRegTreasuryDAOImpl(MstEmpDetails.class, serv.getSessionFactory());
					logger.info("all ok 3");
					genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
					//					List userIdsList = employeeIncrementDAOImpl.getUserIdFromHrPayfixMst(orderNo);
					int dataListSize = dataList.size();
					//					String userIdStr = "";
					Map<Long, Long> amountMap = new HashMap();
					if (!dataList.isEmpty())
					{
						for (int i = 0; i < dataListSize; i++)
						{
							Object[] dataObj = (Object[]) dataList.get(i);
							long userIdL = Long.valueOf(dataObj[0].toString());
							amountMap.put(userIdL, Long.valueOf(dataObj[1].toString()));

						}
					}
					logger.info("all ok 4");
					for (int i = 0; i < dataListSize; i++)
					{logger.info("all ok 5");
						Object[] dataObj = (Object[]) dataList.get(i);
						long userIdL = Long.valueOf(dataObj[0].toString());
						if (amountMap.containsKey(userIdL))
						{
							logger.info("all ok 6");
							HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls) dataObj[2];
							Long amount = Long.valueOf(amountMap.get(userIdL).toString());
							hrEisOtherDtls.setOtherCurrentBasic(amount);
							logger.info("setOtherCurrentBasic "+hrEisOtherDtls.getOtherCurrentBasic());
							//hrEisOtherDtls.setOtherCurrentSevenBasic(amount);
							//logger.info("setOtherCurrentSevenBasic "+hrEisOtherDtls.getOtherCurrentSevenBasic());
							logger.info("all ok 6");
							genericDaoHibernateImpl.update(hrEisOtherDtls);
							logger.info("all ok 7");
							MstEmp dcpsEmp= (MstEmp)dataObj[3];
							logger.info("**************all ok 8***********");
							MstEmpDetails dcpsEmpDetails=(MstEmpDetails) lObjNewRegTreasuryDAO1.read(dcpsEmp.getDcpsEmpId());
							logger.info("all ok 9");
							logger.info("amount is **********"+amount);
							//dcpsEmpDetails.setBasicPay(Double.parseDouble(amount.toString()));
							logger.info("all ok 10");
							
							
						
							
							
							//dcpsEmp.setBasicPay((double)amount);
							//added by roshan for pipb update
							Long gradePay=dcpsEmp.getGradePay();
							logger.info("hiii the grade pay is "+gradePay);
							Long pipb=(amount-gradePay);
							logger.info("hiii the pipb is "+pipb);
							dcpsEmp.setPayInPayBand(pipb);
							logger.info("all ok 11");
							dcpsEmpDetails.setPayInPayBand(pipb);
							//ended by roshan for pipb update
							lObjNewRegTreasuryDAO.update(dcpsEmp);
							logger.info("all ok 12");
							//lObjNewRegTreasuryDAO1.update(dcpsEmpDetails);
							logger.info("all ok 13");
							amountMap.remove(userIdL);
						}
					}
					Long gLngPostId = SessionHelper.getPostId(objectArgs);
					Date date = new Date();
					int year = date.getYear();
					int currentYear = year + 1900;
					String currYear=String.valueOf(currentYear);
					CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", 1);
					if(StringUtility.getParameter("year", request)!=null && !StringUtility.getParameter("year", request).equals("") && Long.parseLong(StringUtility.getParameter("year", request))!=-1){
						currYear=StringUtility.getParameter("year", request);
					}
					logger.info("year is ************"+currYear);
					
					
					employeeIncrementDAOImpl.updateStatusOfHrPayfixMst(orderNo,ddoCodeOfOrder);
					List<HrPayfixMst> fixList = employeeIncrementDAOImpl.getAllDataForIncrement(gLngPostId,currYear);
					objectArgs.put("currYear", currYear);
					
					objectArgs.put("yearList", yearList);
					objectArgs.put("fixList", fixList);
					objectArgs.put("msg", "Order Verified Successfully");
					viewName="ApproveEmployeePayIncrList";
				}
				else if (save.equals("Reject"))
				{
					String ddoCodeOfOrder= StringUtility.getParameter("ddoCodeOfOrder", request);
					logger.error("orderNo**************** " + orderNo);
					//List dataList = employeeIncrementDAOImpl.getDetailOfEmployeeByOrderNo(orderNo,ddoCodeOfOrder);
					employeeIncrementDAOImpl.rejectHrPayfixMst(orderNo,ddoCodeOfOrder);
					objectArgs.put("msg", "Order Rejected Successfully");
					viewName="ApproveEmployeePayIncrList";
				}
			}

			objectArgs.put("save", save);
			objectArgs.put("incrementOrderNo", orderNo);
			objectArgs.put("incrementorderDate", orderDate);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName(viewName);
		}
		catch (Exception e)
		{     
		        e.printStackTrace();
			resultObject.setResultCode(ErrorConstants.ERROR);
			logger.error(e);
		}
		return resultObject;
	
		
		
		
		
		
	    /*Log logger = LogFactory.getLog(getClass());
	    ResultObject resultObject = new ResultObject(0);
	    logger.error("showDataForNewOrderNo****enter");
	    try
	    {
	      ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	      EmployeeIncrementDAOImpl employeeIncrementDAOImpl = new EmployeeIncrementDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
	      Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
	      Map voToService = (Map)objectArgs.get("voToServiceMap");
	      
	      long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
	      long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString()).longValue();
	      long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString()).longValue();
	      long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
	      long userId = Long.parseLong(loginDetailsMap.get("userId").toString());
	      Date currDate = new Date();
	      long scaleNxtIncrAmt = 0L;
	     // long gisAppl = 0L;
	      //String returnvalue = "";
	      
	      CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
	      CmnDatabaseMst cmnDatabaseMst = (CmnDatabaseMst)cmnDatabaseMstDaoImpl.read(Long.valueOf(dbId));
	      
	      CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
	      CmnLocationMst cmnLocationMst = (CmnLocationMst)cmnLocationMstDaoImpl.read(Long.valueOf(locId));
	      
	      CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
	      CmnLanguageMst cmnLanguageMst = (CmnLanguageMst)cmnLanguageMstDao.read(Long.valueOf(langId));
	      
	      OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
	      
	      OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
	      OrgPostMst orgPostMst = (OrgPostMst)orgPostMstDaoImpl.read(Long.valueOf(postId));
	      OrgUserMst OrgUserMst = (OrgUserMst)orgUserMstDaoImpl.read(Long.valueOf(userId));
	      
	      IdGenerator idGenerator = new IdGenerator();
	      
	      String save = voToService.get("save") != null ? voToService.get("save").toString() : "";
	      String orderNo = voToService.get("orderNo") != null ? voToService.get("orderNo").toString() : "";
	      String orderDate = voToService.get("orderdate") != null ? voToService.get("orderdate").toString() : "";
	      
	      String status = null;
	      logger.error("save" + save);
	      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	      SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
	      logger.error("save123");
	      List duplicateList = employeeIncrementDAOImpl.getDataForDuplicateMessage(orderNo, locId, langId);
	      logger.error("duplicateList" + duplicateList.size());
	      if ((duplicateList.size() > 0) && (save.equalsIgnoreCase("N")))
	      {
	        objectArgs.put("msg", "This Increment Certificate Order No already exists !!!");
	        getInitialData(objectArgs);
	      }
	      else if ("N".equalsIgnoreCase(save))
	      {
	        logger.error("hell in save in N");
	        long billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()).longValue() : 0L;
	        long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()).longValue() : 0L;
	        List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
	        logger.error("listoftable" + listoftable.size());
	        long size = listoftable.size();
	        List empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
	        logger.error("empBasicList:" + empBasicList.size());
	        List empCustomVOList = new ArrayList();
	        logger.error("listoftable*********" + listoftable.size());
	        logger.error("empBasicList*********" + empBasicList.size());
	        listoftable = null;
	        
	        String empFame = "";String empMame = "";String empLame = "";String empName = "";
	        long basic = 0L;
	        long userid = 0L;
	        long scaleEndAmount = 0L;
	        String nextincrementDate = "";
	        String empIncrementOrderDate = "";
	        String payinPayBand = "";
	        String gradePay = "";
	        String sevaarthId = "";
	        String gradeId = "";
	        String withEffectiveDate = "";
	        List basicList = null;
	        List gradeIdDetailsList = null;
	        Long currYear = Long.valueOf(0L);
	        
	        int empBasicListSize = empBasicList.size();
	        if ((empBasicList != null) && (empBasicListSize != 0))
	        {
	          Object[] rowList = null;
	          for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
	          {
	            empIncrementOrderDate = "";
	            nextincrementDate = "";
	            
	            rowList = (Object[])itr.next();
	            
	            empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
	            empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
	            empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
	            empName = empFame + empMame + empLame;
	            basic = rowList[10] != null ? Long.parseLong(rowList[10].toString().trim()) : 0L;
	            userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0L;
	            scaleEndAmount = (rowList[5] != null) && (rowList[8] != null) ? Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim()) : 0L;
	            empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
	            scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0L;
	            payinPayBand = rowList[9].toString() + "-" + rowList[5].toString();
	            //gradePay = rowList[11] != null ? rowList[11].toString().trim() : "";
	            gradePay = rowList[8] != null ? rowList[8].toString().trim() : "";
	            sevaarthId = rowList[12] != null ? rowList[12].toString().trim() : "";
	            //returnvalue = rowList[13] != null ? rowList[13].toString().trim() : "";
	            logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);
	            LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
	            String tempCurrDate = sdf.format(currDate);
	            String[] currDateArr = tempCurrDate.split("/");
	            currYear = Long.valueOf(Long.parseLong(currDateArr[2]));
	            currYear = Long.valueOf(currYear.longValue() + 1L);
	            currDateArr[2] = currYear.toString();
	            if ((empIncrementOrderDate != null) && (empIncrementOrderDate != "") && (empIncrementOrderDate.length() > 0))
	            {
	              empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
	              
	              withEffectiveDate = "01/07/" + currYear;
	              
	              nextincrementDate = "01/07/" + currYear;
	            }
	            if (returnvalue.equals("jan")) {
	              nextincrementDate = "01/01/" + currYear;
	            }
	           // gisAppl = dao.getEmpDetailsList(sevaarthId);
	            //logger.info("gisAppl*****" + gisAppl);
	            if ((gisAppl == 700217L) || (gisAppl == 700218L))
	            {
	              gradeIdDetailsList = dao.getStateGradeIdDetailsList(payinPayBand, gradePay);
	              if ((gradeIdDetailsList != null) && (gradeIdDetailsList.size() > 0))
	              {
	                Iterator IT = gradeIdDetailsList.iterator();
	                Object[] lObj = null;
	                while (IT.hasNext())
	                {
	                  lObj = (Object[])IT.next();
	                  gradeId = lObj[0].toString();
	                  logger.info("**************State gradeId in service*************" + gradeId);
	                }
	              }
	              basicList = employeeIncrementDAOImpl.getBasicAsPerMatrix(gradeId, Long.valueOf(basic));
	            }
	            else
	            {
	              gradeIdDetailsList = dao.getgradeIdDetailsList(payinPayBand, gradePay);
	              if ((gradeIdDetailsList != null) && (gradeIdDetailsList.size() > 0))
	              {
	                Iterator IT = gradeIdDetailsList.iterator();
	                Object[] lObj = null;
	                while (IT.hasNext())
	                {
	                  lObj = (Object[])IT.next();
	                  gradeId = lObj[0].toString();
	                  logger.info("**************gradeId in service*************" + gradeId + "--currYear--" + currYear);
	                }
	              }
	              basicList = employeeIncrementDAOImpl.getNEwBasicAsPerMAtrix(gradeId, Long.valueOf(basic));
	            //}
	            PayIncrementCustomVO customVo = new PayIncrementCustomVO();
	            logger.error("nextincrementDate new*********" + nextincrementDate);
	            logger.error("empIncrementOrderDate*********" + empIncrementOrderDate);
	            
	            customVo.setEmpName(empName);
	            customVo.setBasic(basic);
	            customVo.setNextincrementDate(nextincrementDate);
	            customVo.setUserid(userid);
	            customVo.setScaleEndAmount(scaleEndAmount);
	            customVo.setIncrementOrderDate(empIncrementOrderDate);
	            customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
	            customVo.setPayCommissionId(payCommId);
	            customVo.setSevenPCBasicList(basicList);
	            customVo.setWithEffectiveDate(withEffectiveDate);
	            
	            empCustomVOList.add(customVo);
	            customVo = null;
	            logger.error("empCustomVOList size ****************" + empCustomVOList.size());
	            rowList = null;
	            
	            objectArgs.put("save", save);
	            objectArgs.put("fixListWithGo", empCustomVOList);
	            objectArgs.put("fixListsizeWithGo", Integer.valueOf(empCustomVOList.size()));
	            objectArgs.put("billNo", Long.valueOf(billNo));
	            objectArgs.put("payCommId", Long.valueOf(payCommId));
	            objectArgs.put("orderNo", orderNo);
	            objectArgs.put("orderDate", orderDate);
	            //objectArgs.put("gisAppl", Long.valueOf(gisAppl));
	           // objectArgs.put("returnvalue", returnvalue);
	          }
	        }
	        else
	        {
	          objectArgs.put("msg", "There is no any employee matching selection criteria");
	        }
	        getInitialData(objectArgs);
	      }
	      else if (save.equals("Yes"))
	      {
	        logger.info("in save YES method");
	        String empIdsStr2 = "";
	        EmployeeSevenIncrDaompl emIncrDaompl = new EmployeeSevenIncrDaompl(HrPayPaybill.class, serv.getSessionFactory());
	        
	        long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()).longValue() : 0L;
	        
	        String empUserIdList = voToService.get("empUserIdList") != null ? voToService.get("empUserIdList").toString() : "";
	        String newBasicList = voToService.get("newBasicList") != null ? voToService.get("newBasicList").toString() : "";
	        String oldBasicList = voToService.get("newBasicList") != null ? voToService.get("oldBasicList").toString() : "";
	        String WEF = voToService.get("WEF") != null ? voToService.get("WEF").toString() : "";
	        String nextIncrDate = voToService.get("nextIncrDate") != null ? voToService.get("nextIncrDate").toString() : "";
	        String remarks = voToService.get("remarks") != null ? voToService.get("remarks").toString() : "";
	        
	       // logger.error("empIdstoBeAttached are ****************" + empUserIdList + "--nextIncrDate--" + returnvalue);
	        logger.error("newBasicList are ****************" + newBasicList);
	        String[] empIdsStr = empUserIdList.split("~");
	        String[] newBasicStr = newBasicList.split("~");
	        String[] oldBasicStr = oldBasicList.split("~");
	        String[] remarksStr = remarks.split("~");
	        String[] WEFStr = WEF.split("~");
	        
	        logger.error("empIdstoBeAttached**********" + empIdsStr);
	        
	        String empIdsStr1 = empUserIdList.replace("~", ",");
	        
	        String empRemarksUpdate = "";
	        
	        Map<Long, Object> remarksDataMap = new HashMap();
	        
	       // Date empWEFUpdate = sdf.parse(WEF);
	        
	       
	        int hrPayFixCounter = empIdsStr.length;
	        String WEFDateStr = "";
	        logger.info("hrPayFixCounter--" + hrPayFixCounter);
	        objectArgs.put("counter", Integer.valueOf(hrPayFixCounter));
	        long payfixPkId = idGenerator.PKGenerator("HR_PAYFIX_MST", objectArgs).longValue();
	        objectArgs.remove("counter");
	        
	        String empPayCommissionId = voToService.get("empPayCommissionId") != null ? voToService.get("empPayCommissionId").toString() : "";
	        logger.error("empPayCommissionId are ****************" + empPayCommissionId);
	        String[] empPayCommissionIdStr = empPayCommissionId.split("~");
	        logger.error("empIncOrderDatetoBeAttached**********" + empPayCommissionIdStr);
	        
	        Map<Long, Long> payCommIdMap = new HashMap();
	        
	        Date incrOrderDt = null;
	        if ((orderDate.toString() != null) && (orderDate.length() > 0) && (orderDate.toString() != "")) {
	          incrOrderDt = sdf.parse(orderDate);
	        }
	        Boolean lBlIncrCount = Boolean.valueOf(employeeIncrementDAOImpl.getExistingCount(orderNo, incrOrderDt, Long.valueOf(locId)));
	        logger.error("lBlIncrCount:::" + lBlIncrCount);
	        if (lBlIncrCount.booleanValue())
	        {
	          logger.error("deleting existing records from HrPayfixMst");
	          employeeIncrementDAOImpl.deleteRecordsByOrderNumber(orderNo, incrOrderDt, locId);
	        }
	        if ((empIdsStr != null) && (empIdsStr.length > 0)) {
	          for (Integer lInt = Integer.valueOf(0); lInt.intValue() < empIdsStr.length; lInt = Integer.valueOf(lInt.intValue() + 1)) {}
	        }
	        if ((empIdsStr != null) && (empIdsStr.length > 0))
	        {
	          Date wefDate = null;
	          Date nextIncrDt = null;
	          if ((orderDate.toString() != null) && (orderDate.length() > 0) && (orderDate.toString() != "")) {
	            incrOrderDt = sdf.parse(orderDate);
	          }
	          status = "Entered";
	          Long newbasicInsert = Long.valueOf(0L);
	          Long oldbasicToInsert = Long.valueOf(0L);
	          Long currYear = Long.valueOf(0L);
	          String tempCurrDate = sdf.format(currDate);
	          String[] currDateArr = tempCurrDate.split("/");
	          currYear = Long.valueOf(Long.parseLong(currDateArr[2]));
	          currYear = Long.valueOf(currYear.longValue() + 1L);
	          currDateArr[2] = currYear.toString();
	          for (Integer lInt = Integer.valueOf(0); lInt.intValue() < empIdsStr.length; lInt = Integer.valueOf(lInt.intValue() + 1))
	          {
	           // String month = emIncrDaompl.getMonthIncrGivenUserId(empIdsStr[lInt.intValue()]);
	            payfixPkId = ++payfixPkId;
	           // logger.info("payfixPkId---" + payfixPkId + "--month-" + month);
	            userId = Long.parseLong(empIdsStr[lInt.intValue()]);
	            OrgUserMst orgUserMstVO = new OrgUserMst();
	            orgUserMstVO.setUserId(userId);
	            HrPayfixMst hrPayfixMst = new HrPayfixMst();
	            newbasicInsert = Long.valueOf(Long.parseLong(newBasicStr[lInt.intValue()]));
	            oldbasicToInsert = Long.valueOf(Long.parseLong(oldBasicStr[lInt.intValue()]));
	            
	            hrPayfixMst.setCmnDatabaseMst(cmnDatabaseMst);
	            hrPayfixMst.setCmnLanguageMst(cmnLanguageMst);
	            hrPayfixMst.setCmnLocationMst(cmnLocationMst);
	            hrPayfixMst.setCreatedDate(currDate);
	            hrPayfixMst.setIncrementOrderDate(incrOrderDt);
	            hrPayfixMst.setIncrementOrderNo(orderNo);
	            
	            hrPayfixMst.setNewBasic(newbasicInsert);
	            if (month != null)
	            {
	              if (month.equals("jan")) {
	                nextIncrDt = sdf.parse("01/01/" + currYear);
	              } else {
	                nextIncrDt = sdf.parse("01/07/" + currYear);
	              }
	            }
	            else {
	              nextIncrDt = sdf.parse("01/07/" + currYear);
	            }
	            hrPayfixMst.setNxtIncrDate(nextIncrDt);
	            hrPayfixMst.setOldBasic(oldbasicToInsert);
	            hrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
	            hrPayfixMst.setOrgPostMstUpdatedByPost(orgPostMst);
	            hrPayfixMst.setOrgUserMstCreatedBy(OrgUserMst);
	            hrPayfixMst.setOrgUserMstUpdatedBy(OrgUserMst);
	            WEFDateStr = WEFStr[lInt.intValue()];
	            logger.info("WEFDateStr:" + WEFDateStr);
	           // empWEFUpdate = sdf.parse(WEFDateStr);
	           // hrPayfixMst.setPayFixDate(empWEFUpdate);
	            hrPayfixMst.setPayfixId(Long.valueOf(payfixPkId));
	            hrPayfixMst.setRemarks(remarksStr[lInt.intValue()]);
	            hrPayfixMst.setStatus(status);
	            hrPayfixMst.setUpdatedDate(currDate);
	            hrPayfixMst.setUserId(orgUserMstVO);
	            hrPayfixMst.setActivateFlag(1);
	            hrPayfixMst.setTrnCounter(Long.valueOf(1L));
	            hrPayfixMst.setPayCommissionId(Long.valueOf(payCommId));
	            
	            employeeIncrementDAOImpl.create(hrPayfixMst);
	            objectArgs.put("msg", "Inserted Successfully");
	            
	            hrPayfixMst = null;
	          }
	        }
	        else
	        {
	          objectArgs.put("msg", "Order is deleted successfully.");
	        }
	        payCommIdMap = null;
	        remarksDataMap = null;
	      }
	      else if (save.equalsIgnoreCase("view"))
	      {
	        logger.info("in save view method");
	        String empName = "";
	        String empFname = "";String empMname = "";String empLname = "";
	        long basic = 0L;
	        
	        String incrementOrderDate = "";
	        long scaleEndAmount = 0L;
	        String nextincrementDate = "";
	        String withEffectiveDate = "";
	        String remarks = "";
	        long newBasic = 0L;
	        status = "";
	        long commisionId = 0L;
	        List empBasicList = null;
	        String payinPayBand = "";
	        String gradePay = "";
	        String sevaarthId = "";
	        List basicList = null;
	        String gradeId = "";
	        
	        long billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()).longValue() : 0L;
	        long payCommId = voToService.get("payCommId") != null ? Long.valueOf(voToService.get("payCommId").toString()).longValue() : 0L;
	        
	        LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayfixMst.class, serv.getSessionFactory());
	        EmployeeSevenIncrDaompl emIncrDaompl = new EmployeeSevenIncrDaompl(HrPayPaybill.class, serv.getSessionFactory());
	        List fixList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtlsByOrderNo(locId, orderNo, payCommId);
	        List empCustomVOList = new ArrayList();
	        int fixLisSize = fixList.size();
	        
	        logger.error("Size of HrPayFixMst " + fixLisSize);
	        Object[] dataObj = null;
	        for (int i = 0; i < fixLisSize; i++)
	        {
	          incrementOrderDate = "";
	          nextincrementDate = "";
	          withEffectiveDate = "";
	          status = "";
	          empName = "";
	          dataObj = (Object[])fixList.get(i);
	          empFname = dataObj[0] != null ? dataObj[0].toString().concat(" ") : "";
	          empMname = dataObj[1] != null ? dataObj[1].toString().concat(" ") : "";
	          empLname = dataObj[2] != null ? dataObj[2].toString() : "";
	          empName = empFname + empMname + empLname;
	          basic = dataObj[3] != null ? Long.valueOf(dataObj[3].toString()).longValue() : 0L;
	          userId = dataObj[4] != null ? Long.valueOf(dataObj[4].toString()).longValue() : 0L;
	          
	          scaleEndAmount = (dataObj[5] != null) && (dataObj[8] != null) ? Long.parseLong(dataObj[5].toString().trim()) + Long.parseLong(dataObj[8].toString().trim()) : 0L;
	          withEffectiveDate = dataObj[6] != null ? dataObj[6].toString().trim() : "";
	          remarks = dataObj[7] != null ? dataObj[7].toString().trim() : "";
	          newBasic = dataObj[8] != null ? Long.valueOf(dataObj[8].toString()).longValue() : 0L;
	          nextincrementDate = dataObj[9] != null ? dataObj[9].toString().trim() : "";
	          incrementOrderDate = dataObj[11] != null ? dataObj[11].toString().trim() : "";
	          status = dataObj[12] != null ? dataObj[12].toString().trim() : "";
	          commisionId = dataObj[13] != null ? Long.valueOf(dataObj[13].toString()).longValue() : 0L;
	          scaleNxtIncrAmt = dataObj[14] != null ? Long.valueOf(dataObj[14].toString()).longValue() : 0L;
	          payinPayBand = dataObj[15].toString() + "-" + dataObj[5].toString();
	          gradePay = dataObj[16] != null ? dataObj[16].toString().trim() : "";
	          sevaarthId = dataObj[17] != null ? dataObj[17].toString().trim() : "";
	          
	         // returnvalue = emIncrDaompl.getIncrMonth(sevaarthId);
	          //logger.info("****returnvalue***" + returnvalue);
	          
	          List gradeIdDetailsList = null;
	          
	          gisAppl = dao.getEmpDetailsList(sevaarthId);
	          logger.info("gisAppl*****" + gisAppl);
	          if ((gisAppl == 700217L) || (gisAppl == 700218L))
	          {
	            gradeIdDetailsList = dao.getStateGradeIdDetailsList(payinPayBand, gradePay);
	            if ((gradeIdDetailsList != null) && (gradeIdDetailsList.size() > 0))
	            {
	              Iterator IT = gradeIdDetailsList.iterator();
	              Object[] lObj = null;
	              while (IT.hasNext())
	              {
	                lObj = (Object[])IT.next();
	                gradeId = lObj[0].toString();
	                logger.info("**************State gradeId in service*************" + gradeId);
	              }
	            }
	            basicList = employeeIncrementDAOImpl.getBasicAsPerMatrix(gradeId, Long.valueOf(basic));
	          }
	          else
	          {
	          gradeIdDetailsList = dao.getStateGradeIdDetailsList(payinPayBand, gradePay);
	            //gradeIdDetailsList = dao.getgradeIdDetailsList(payinPayBand, gradePay);
	            if ((gradeIdDetailsList != null) && (gradeIdDetailsList.size() > 0))
	            {
	              Iterator IT = gradeIdDetailsList.iterator();
	              Object[] lObj = null;
	              while (IT.hasNext())
	              {
	                lObj = (Object[])IT.next();
	                gradeId = lObj[0].toString();
	                logger.info("**************gradeId in service*************" + gradeId);
	              }
	            }
	            basicList = employeeIncrementDAOImpl.getNEwBasicAsPerMAtrix(gradeId, Long.valueOf(basic));
	          //}
	          PayIncrementCustomVO customVo = new PayIncrementCustomVO();
	          customVo.setEmpName(empName);
	          customVo.setScaleEndAmount(scaleEndAmount);
	          customVo.setUserid(userId);
	          customVo.setBasic(basic);
	          if (!nextincrementDate.equals("")) {
	            customVo.setNextincrementDate(sdf.format(sdfParse.parse(nextincrementDate)));
	          }
	          if (!withEffectiveDate.equals("")) {
	            customVo.setWithEffectiveDate(sdf.format(sdfParse.parse(withEffectiveDate)).toString());
	          }
	          if (!incrementOrderDate.equals(""))
	          {
	            logger.error("Increment Order Date in View Scrren" + incrementOrderDate);
	            customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)).toString());
	          }
	          customVo.setRemarks(remarks);
	          customVo.setNewBasic(newBasic);
	          customVo.setStatus(status);
	          customVo.setPayCommissionId(commisionId);
	          customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
	          customVo.setSevenPCBasicList(basicList);
	          
	          empCustomVOList.add(customVo);
	        }
	        objectArgs.put("payCommId", Long.valueOf(payCommId));
	        objectArgs.put("fixListWithGo", empCustomVOList);
	        objectArgs.put("fixListsizeWithGo", Integer.valueOf(empCustomVOList.size()));
	        objectArgs.put("save", save);
	        //objectArgs.put("gisAppl", Long.valueOf(gisAppl));
	       // objectArgs.put("returnvalue", returnvalue);
	        //logger.error("returnvalue*********" + returnvalue);
	        
	        List<HrPayfixMst> listoftable = employeeIncrementDAOImpl.getAllDataForPayFixation(locId);
	        long size = listoftable.size();
	        empBasicList = employeeIncrementDAOImpl.getEmpNamesAndBasicDtls(locId, size, payCommId, billNo);
	        empCustomVOList = new ArrayList();
	        logger.error("listoftable*********" + listoftable.size());
	        logger.error("empBasicList*********" + empBasicList.size());
	        
	        String empFame = "";String empMame = "";String empLame = "";
	        empName = "";
	        basic = 0L;
	        long userid = 0L;
	        scaleEndAmount = 0L;
	        nextincrementDate = "";
	        String empIncrementOrderDate = "";
	        Long currYear = Long.valueOf(0L);
	        
	        int empBasicListSize = empBasicList.size();
	        if ((empBasicList != null) && (empBasicListSize != 0))
	        {
	          Object[] rowList = null;
	          for (Iterator itr = empBasicList.iterator(); itr.hasNext();)
	          {
	            empIncrementOrderDate = "";
	            nextincrementDate = "";
	            
	            rowList = (Object[])itr.next();
	            
	            empFame = rowList[0] != null ? rowList[0].toString().trim().concat(" ") : "";
	            empMame = rowList[1] != null ? rowList[1].toString().trim().concat(" ") : "";
	            empLame = rowList[2] != null ? rowList[2].toString().trim() : "";
	            empName = empFame + empMame + empLame;
	            basic = rowList[3] != null ? Long.parseLong(rowList[3].toString().trim()) : 0L;
	            userid = rowList[4] != null ? Long.parseLong(rowList[4].toString().trim()) : 0L;
	            scaleEndAmount = (rowList[5] != null) && (rowList[8] != null) ? Long.parseLong(rowList[5].toString().trim()) + Long.parseLong(rowList[8].toString().trim()) : 0L;
	            empIncrementOrderDate = rowList[6] != null ? rowList[6].toString() : "";
	            scaleNxtIncrAmt = rowList[7] != null ? Long.parseLong(rowList[7].toString().trim()) : 0L;
	           // returnvalue = rowList[13] != null ? rowList[13].toString().trim() : "";
	            logger.error("incrementorderDate from HrEisOtherDtls*********" + empIncrementOrderDate);
	            if ((empIncrementOrderDate != null) && (empIncrementOrderDate != "") && (empIncrementOrderDate.length() > 0))
	            {
	              logger.error("in if order date ********" + empIncrementOrderDate);
	              empIncrementOrderDate = sdf.format(sdfParse.parse(empIncrementOrderDate));
	              String tempCurrDate = sdf.format(currDate);
	              String[] currDateArr = tempCurrDate.split("/");
	              currYear = Long.valueOf(Long.parseLong(currDateArr[2]));
	              currYear = Long.valueOf(currYear.longValue() + 1L);
	              currDateArr[2] = currYear.toString();
	              
	              logger.error("in if gis appl*********" + gisAppl + "---returnvalue---" + returnvalue);
	              if (returnvalue.equals("jan"))
	              {
	                withEffectiveDate = "01/01/" + currYear;
	                
	                nextincrementDate = "01/01/" + currYear;
	              }
	              else
	              {
	                withEffectiveDate = "01/07/" + currYear;
	                
	                nextincrementDate = "01/07/" + currYear;
	              }
	            }
	            PayIncrementCustomVO customVo = new PayIncrementCustomVO();
	            logger.error("nextincrementDate*********" + nextincrementDate);
	            logger.error("empIncrementOrderDate*********" + empIncrementOrderDate + "---withEffectiveDate---" + withEffectiveDate);
	            
	            customVo.setEmpName(empName);
	            customVo.setBasic(basic);
	            customVo.setNextincrementDate(nextincrementDate);
	            customVo.setUserid(userid);
	            customVo.setScaleEndAmount(scaleEndAmount);
	            customVo.setIncrementOrderDate(empIncrementOrderDate);
	            customVo.setScaleNxtIncrAmt(scaleNxtIncrAmt);
	            customVo.setPayCommissionId(payCommId);
	            customVo.setSevenPCBasicList(basicList);
	            
	            empCustomVOList.add(customVo);
	            customVo = null;
	            logger.error("empCustomVOList size ****************" + empCustomVOList.size());
	            rowList = null;
	          }
	          //logger.info("gisAppl*****" + gisAppl + "--returnvalue---@@@" + returnvalue);
	          objectArgs.put("save", save);
	          objectArgs.put("fixListWithGo", empCustomVOList);
	          objectArgs.put("fixListsizeWithGo", Integer.valueOf(empCustomVOList.size()));
	          objectArgs.put("billNo", Long.valueOf(billNo));
	          objectArgs.put("payCommId", Long.valueOf(payCommId));
	          objectArgs.put("orderNo", orderNo);
	          objectArgs.put("orderDate", orderDate);
	          //objectArgs.put("gisAppl", Long.valueOf(gisAppl));
	        //  objectArgs.put("returnvalue", returnvalue);
	        }
	        else if (fixLisSize == 0)
	        {
	          objectArgs.put("msg", "There is no any employee matching selection criteria");
	        }
	        getInitialData(objectArgs);
	      }
	      //logger.info("gisAppl***** 444" + gisAppl + "--returnvalue--" + returnvalue);
	      objectArgs.put("save", save);
	      objectArgs.put("incrementOrderNo", orderNo);
	      objectArgs.put("incrementorderDate", orderDate);
	    //  objectArgs.put("gisAppl", Long.valueOf(gisAppl));
	      
	      resultObject.setResultValue(objectArgs);
	      resultObject.setResultCode(0);
	      resultObject.setViewName("EmployeePayIncrList");
	      
	      employeeIncrementDAOImpl = null;
	      duplicateList = null;
	      objectArgs = null;
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      resultObject.setResultCode(-1);
	      logger.error(e);
	    }
	    objectArgs = null;
	    return resultObject;
	  }*/
	
	/*Ended By Shivram*/
}
	
}
