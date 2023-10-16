package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.HrPayDdoSchemeMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrgSchemeMstDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayDDOSchemeMpgCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayDDOSchemeMpgVO;
import com.tcs.sgv.eis.valueobject.OrgSchemeAndHrpayVO;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class DDOSchemeServiceImpl extends ServiceImpl
{

	Log logger = LogFactory.getLog(getClass());

	public ResultObject getDDOSchemeDtls(Map objectArgs)
	{
		logger.info("...................in DDOSchemeServiceImpl...................");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try
		{

			HttpSession session = request.getSession();
			Map baseLoginMap = (Map) objectArgs.get("baseLoginMap");
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());


			//System.out.println("ENTER INTO DDOSchemeServiceImpl getDDOSchemeDtls");




			OrgSchemeMstDAOImpl orgSchemeMstDAOImpl = new OrgSchemeMstDAOImpl(OrgSchemeMstVO.class, serv.getSessionFactory());
			List ViewList =null; 
				//orgSchemeMstDAOImpl.getSchemeDtlsForUpadate(langId);

			String SchemeName ="";
			long HeadChargable = 0L;
			String Budname ="";
			long BudSubId=0L;
			long schemeId = 0L;
			List<OrgSchemeAndHrpayVO> passList = new ArrayList();
			for(int i=0; i<ViewList.size(); i++)
			{
				Object[] row = ((Object[])ViewList.get(i));
				SchemeName=row[0].toString();
				HeadChargable=Long.parseLong(row[1].toString());
				Budname=row[2].toString();
				BudSubId=Long.parseLong(row[3].toString());
				schemeId=Long.parseLong(row[4].toString());

				OrgSchemeAndHrpayVO orgSchemeAndHrpayVO = new OrgSchemeAndHrpayVO();
				orgSchemeAndHrpayVO.setSchemeName(SchemeName);
				/*orgSchemeAndHrpayVO.setHeadChargable(HeadChargable);
				orgSchemeAndHrpayVO.setBudName(Budname);
				orgSchemeAndHrpayVO.setBudSubId(BudSubId);
				orgSchemeAndHrpayVO.setSchemeId(schemeId);*/
				passList.add(orgSchemeAndHrpayVO);

				BudSubId = orgSchemeAndHrpayVO.getBudSubId();
			}

			//System.out.println("passList size .."+passList.size());

			//System.out.println("ENTER INTO DDOSchemeServiceImpl getDDOSchemeDtls END***********");
			objectArgs.put("budsubheadid", BudSubId);
			objectArgs.put("ViewList", ViewList);
			objectArgs.put("passList", passList);
			objectArgs.put("listSize", passList.size());



			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			//System.out.println("DDOInfoServiceImpl  ending");
			logger.info("..........after objectArgs............");
			resultObject.setViewName("DDOSchemeView");	

		}
		catch(Exception ex)
		{
			logger.info("There is some problem in DDOInfo******");
			ex.printStackTrace();
		}
		return resultObject;
	}


	public ResultObject viewDDOSchemeDtlsForEntry(Map objectArgs) 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		int msg=0;
		try 
		{
			HttpSession session = request.getSession();
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map vo = (Map)objectArgs.get("voToServiceMap");

			Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);


			long loggedInUser = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);

			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(postId);
			
			//System.out.println("insertSchemeDtls loggedInPost" + postId);
			
			OrgSchemeMstDAOImpl orgSchemeMstDAOImpl = new OrgSchemeMstDAOImpl(OrgSchemeMstVO.class, serv.getSessionFactory());
			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			
			List<OrgPostDetailsRlt> locIds = orgSchemeMstDAOImpl.getLocIds(postId);
			long locId = locIds.get(0).getCmnLocationMst().getLocId();
			//System.out.println("insertSchemeDtls location id" + locId);
			
			List<CmnLocationMst> deptList = new ArrayList<CmnLocationMst>();
			
			boolean isRoleAdminFlag = false;	 
			if (isRoleAdminFlag) 
			{
				logger.info("true::");
				deptList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId );

				logger.info("deptlist.size::" + deptList.size());
			} 
			else
			{
				logger.info("false::");
				String[] critariaArugs = { "cmnLanguageMst.langId", "locId" };
				Object[] valueArgus = new Object[2];
				valueArgus[0] = langId;
				valueArgus[1] = locId;
				deptList = locationDAO.getListByColumnAndValue(critariaArugs,valueArgus);
				//System.out.println("dept list is ::::"+deptList.get(0));
				//System.out.println("location code::::"+((CmnLocationMst)deptList.get(0)).getLocationCode());
				//System.out.println("location Id is ::::"+((CmnLocationMst)deptList.get(0)).getLocId());
				logger.info("deptlist.size::" + deptList.size());
			}

			objectArgs.put("deptList", deptList);
			
			
			
			long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());				
			CmnDatabaseMst cmnDatabaseMst= new CmnDatabaseMst();
			cmnDatabaseMst.setDbId(dbId);
			
			

			//System.out.println("ENTER INTO DDOSchemeServiceImpl viewDDOSchemeDtlsForEntry ***********");

			
			List ViewList = null;
			//orgSchemeMstDAOImpl.getSchemeNameAndHCAndSubName(langId);

			String SchemeName ="";
			long HeadChargable = 0L;
			String Budname ="";
			long BudSubId=0L;
			List<OrgSchemeAndHrpayVO> passList = new ArrayList();
			for(int i=0; i<ViewList.size(); i++)
			{
				Object[] row = ((Object[])ViewList.get(i));
				SchemeName=row[0].toString();
				HeadChargable=Long.parseLong(row[1].toString());
				Budname=row[2].toString();
				BudSubId=Long.parseLong(row[3].toString());

				OrgSchemeAndHrpayVO orgSchemeAndHrpayVO = new OrgSchemeAndHrpayVO();
				orgSchemeAndHrpayVO.setSchemeName(SchemeName);
				//orgSchemeAndHrpayVO.setHeadChargable(HeadChargable);
				orgSchemeAndHrpayVO.setBudName(Budname);
				orgSchemeAndHrpayVO.setBudSubId(BudSubId);
				passList.add(orgSchemeAndHrpayVO);

				BudSubId = orgSchemeAndHrpayVO.getBudSubId();
			}

			//System.out.println("ENTER INTO DDOSchemeServiceImpl viewDDOSchemeDtlsForEntry ***********" + BudSubId);

			//System.out.println("passList size .."+passList.size());
			objectArgs.put("budsubheadid", BudSubId);
			objectArgs.put("ViewList", ViewList);
			objectArgs.put("passList", passList);
			objectArgs.put("listSize", passList.size());
			
			
			long BudSubHeadId = 0L;
			if(StringUtility.getParameter("BUDSUBHEADID", request) != "")
			{
				
			 BudSubHeadId = Long.valueOf(StringUtility.getParameter("BUDSUBHEADID", request).toString());
			//System.out.println("BUDSUBHEADID BUDSUBHEADID ----------->>>>>>>" + BudSubHeadId);
			
			objectArgs.put("BUDSUBHEADID", BudSubHeadId);
			}
			



			HrPayDdoSchemeMpgDAOImpl hrPayDdoSchemeMpgDAOImpl =  new HrPayDdoSchemeMpgDAOImpl(HrPayDDOSchemeMpgVO.class, serv.getSessionFactory());
			//System.out.println("VERY NICE viewDDOSchemeDtlsForEntry #########################");



			if(StringUtility.getParameter("BUDSUBHEADID", request) != "")

			{

				String edit =StringUtility.getParameter("edit", request);
				//long BudSubHeadId = Long.valueOf(StringUtility.getParameter("BudSubHeadId", request).toString());
				long SchemeId = Long.valueOf(StringUtility.getParameter("SchemeId", request).toString());

				String headChargable =StringUtility.getParameter("HeadChargable", request);

				//System.out.println("viewDDOSchemeDtlsForEntry SchemeId #########################" + SchemeId);

				
				//if(SchemeId!=0)
				//{
				//	IsActive = hrPayDdoSchemeMpgDAOImpl.getIsActive(SchemeId);
					/*

					for(int i=0; i<IsactiveLst.size(); i++)
					{

						Object[] row = ((Object[])IsactiveLst.get(i));
						IsActive= Long.parseLong(row[0].toString());
					}
					 */

					//System.out.println("viewDDOSchemeDtlsForEntry IsActive #########################" + IsActive);
				//}



				//System.out.println("viewDDOSchemeDtlsForEntry edit #########################" + edit);
				//System.out.println("viewDDOSchemeDtlsForEntry BudSubHeadId #########################" + BudSubHeadId);





				List SchemeList =null;
					// hrPayDdoSchemeMpgDAOImpl.getDataFromBudSubHeadId(BudSubHeadId);

				String IsActive = "";
				
				String schemeCode ="";
				String schemeName ="";
				String budSubHeadCode ="";
				String budSubHeadDesc ="";

				String demandCode ="";
				String majorCode ="";
				String subMajorCode ="";
				String minorCode ="";

				HrPayDDOSchemeMpgCustomVO DDOSchemeCustoVo = new HrPayDDOSchemeMpgCustomVO();




				if(SchemeList!=null && SchemeList.size()>0)
				{
					Object[] row = (Object[])SchemeList.get(0); 
					if(row[0]!=null)
					{
						schemeCode = row[0].toString();
						DDOSchemeCustoVo.setSchemeCode(schemeCode);

					}

					if(row[1]!=null)
					{
						schemeName = row[1].toString();
						DDOSchemeCustoVo.setSchemeName(schemeName);

					}

					if(row[2]!=null && row[3]!=null)
					{
						budSubHeadCode = row[2].toString();
						DDOSchemeCustoVo.setBudSubHeadCode(budSubHeadCode);

						budSubHeadDesc = row[3].toString();
						//budSubHeadDesc = "(" + budSubHeadCode + ")" + budSubHeadDesc;


						budSubHeadDesc = budSubHeadCode + "(" + budSubHeadDesc + ")" ;

						DDOSchemeCustoVo.setBudSubHeadDesc(budSubHeadDesc);



					}

					if(row[4]!=null)
					{
						demandCode = row[4].toString();
						DDOSchemeCustoVo.setDemandCode(demandCode);

					}

					if(row[5]!=null)
					{
						majorCode = row[5].toString();
						DDOSchemeCustoVo.setMajorCode(majorCode);

					}

					if(row[6]!=null)
					{
						subMajorCode = row[6].toString();
						DDOSchemeCustoVo.setSubMajorCode(subMajorCode);

					}

					if(row[7]!=null)
					{
						minorCode = row[7].toString();
						DDOSchemeCustoVo.setMinorCode(minorCode);

					}
					
					if(row[8]!=null)
					{
						IsActive = row[8].toString();
						DDOSchemeCustoVo.setIsActive(IsActive);
						String isactive = DDOSchemeCustoVo.getIsActive();
						//System.out.println("isactiveisactive-" + isactive);
						//String isActive = String.valueOf(isactive);
						objectArgs.put("isactive", isactive);

					}


					

					objectArgs.put("DDOSchemeCustoVo", DDOSchemeCustoVo);

				}



				objectArgs.put("edit", edit);
				objectArgs.put("IsActive", IsActive);
				objectArgs.put("headChargable", headChargable);


			}

			resObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("End of saveGroupManagementService method:::::::: " +objectArgs.get("msg") );
			resObj.setResultValue(objectArgs);
			resObj.setViewName("DDOSchemeEntry");



		}

		catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}



	public ResultObject insertDDOSchemeDtls(Map objectArgs) 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		int msg=0;
		try 
		{
			HttpSession session = request.getSession();
			Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map vo = (Map)objectArgs.get("voToServiceMap");

			Long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);


			long loggedInUser = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);

			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(postId);

			long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());				
			CmnDatabaseMst cmnDatabaseMst= new CmnDatabaseMst();
			cmnDatabaseMst.setDbId(dbId);

			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");



			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class, serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			String dt = sdf.format(currDate);
			int finYrId = finYearDAOImpl.getFinYearId(currDate);

			//System.out.println("finYrId finYrId ----------->>>>>>>" + finYrId);

			long BUDSUBHEADID = Long.valueOf(StringUtility.getParameter("BUDSUBHEADID", request).toString());

			//System.out.println("BUDSUBHEADID BUDSUBHEADID ----------->>>>>>>" + BUDSUBHEADID);


			long SchemeCode = Long.parseLong(objectArgs.get("SchemeCode").toString());
			String schemeName = objectArgs.get("SchemeName").toString();
			long DemandNo = Long.parseLong(objectArgs.get("DemandNo").toString());
			long MajorNo = Long.parseLong(objectArgs.get("MajorNo").toString());
			long SubMajorNo = Long.parseLong(objectArgs.get("SubMajorNo").toString());
			long MinorNo = Long.parseLong(objectArgs.get("MinorNo").toString());
			String SubMinorNo =objectArgs.get("SubMinorNo").toString();
			String IsActive =objectArgs.get("Active").toString();


			int p = SubMinorNo.indexOf('(');

			String BudSubHeadCode =SubMinorNo.substring(0,p);
			String BudSubHeaDesc = SubMinorNo.substring(p+1, SubMinorNo.length()-1);

			//System.out.println("BudSubHeadCode---->>>>>>>>" + BudSubHeadCode);
			//System.out.println("BudSubHeaDesc------->>>>>>>" + BudSubHeaDesc);


			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertDDOSchemeDtls ***********");
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertDDOSchemeDtls loggedInPost***********" + postId);
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertAndUpdateDDOSchemeDtls SchemeCode***********" + SchemeCode);
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertAndUpdateDDOSchemeDtls SchemeName***********" + schemeName);
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertAndUpdateDDOSchemeDtls DemandNo***********" + DemandNo);
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertAndUpdateDDOSchemeDtls MajorNo***********" + MajorNo);
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertAndUpdateDDOSchemeDtls SubMajorNo***********" + SubMajorNo);
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertAndUpdateDDOSchemeDtls MinorNo***********" + MinorNo);
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertAndUpdateDDOSchemeDtls SubMinorNo***********" + SubMinorNo);
			//System.out.println("ENTER INTO DDOSchemeServiceImpl insertAndUpdateDDOSchemeDtls Active***********" + IsActive);

			Date syDate = new Date();
			OrgSchemeMstDAOImpl orgSchemeMstDAOImpl = new OrgSchemeMstDAOImpl(OrgSchemeMstVO.class, serv.getSessionFactory());
			HrPayDdoSchemeMpgDAOImpl hrPayDdoSchemeMpgDAOImpl =  new HrPayDdoSchemeMpgDAOImpl(HrPayDDOSchemeMpgVO.class, serv.getSessionFactory());

			/*long BudSubHeadId = hrPayDdoSchemeMpgDAOImpl.getBudSubHeadId(DemandNo, MajorNo, SubMajorNo, MinorNo, BudSubHeadCode, BudSubHeaDesc);
			//System.out.println("BudSubHeadId---->>>>>>>>" + BudSubHeadId);
			//long SchemeId = hrPayDdoSchemeMpgDAOImpl.getSchemeIdFromBudSubHeadId(BudSubHeadId);
			long SchemeId = hrPayDdoSchemeMpgDAOImpl.getSchemeIdFromBudSubHeadId(BUDSUBHEADID);*/
			//System.out.println("SchemeId---->>>>>>>>" + SchemeId);

			OrgSchemeMstVO orgSchemeMstVO = new OrgSchemeMstVO();
			//orgSchemeMstVO.setSchemeId(SchemeId);

			//System.out.println("orgSchemeMstVO  schemeId---->>>>>>>>" + orgSchemeMstVO.getSchemeId());


			List<OrgDdoMst> ddoList = orgSchemeMstDAOImpl.getDDoId(postId);
			OrgDdoMst orgDdoMst = (OrgDdoMst)ddoList.get(0);
			//System.out.println("orgDdoMstorgDdoMst" + orgDdoMst);
			HrPayDDOSchemeMpgVO  hrPayDDOSchemeMpgVO = new HrPayDDOSchemeMpgVO();

			String editFromVO = objectArgs.get("editMode").toString();


			if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
			{
				/*if(SchemeId!=0)
				{
					long ddoId = hrPayDdoSchemeMpgDAOImpl.getDDOId(SchemeId);
					hrPayDDOSchemeMpgVO.setId(ddoId);

					//System.out.println("ddoIdddoIdddoId-------->>>>>>>" + ddoId);

					List List = hrPayDdoSchemeMpgDAOImpl.getDDODataFromDDOId(ddoId);
					Date CreatedDate = new Date();
					for(int i=0; i<List.size() ; i++)
					{
						logger.info("SchemeIdList SchemeIdList SchemeIdList " +List.size());

						Object[] row = ((Object[])List.get(i));
						CreatedDate=row[4].toString();

					}

					//Date CreatedDate = List.get(0).getCreatedDate();

					hrPayDDOSchemeMpgVO.setOrgSchemeMstVO(orgSchemeMstVO);
					hrPayDDOSchemeMpgVO.setOrgDdoMst(orgDdoMst);
					hrPayDDOSchemeMpgVO.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
					hrPayDDOSchemeMpgVO.setCreatedDate(syDate);
					hrPayDDOSchemeMpgVO.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
					hrPayDDOSchemeMpgVO.setUpdatedDate(syDate);
					hrPayDDOSchemeMpgVO.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
					hrPayDDOSchemeMpgVO.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
					hrPayDDOSchemeMpgVO.setCmnLanguageMst(cmnLanguageMst);
					hrPayDDOSchemeMpgVO.setCmnLocationMst(cmnLocationMst);

					String ActiveType = "Active";
					//System.out.println("ActiveType ActiveType---->>>>>>" + ActiveType);
					if(IsActive.equalsIgnoreCase(ActiveType))
					{
						long active = 1;
						hrPayDDOSchemeMpgVO.setIsActive(active);
					}
					else
					{
						long InActive = 0L;
						hrPayDDOSchemeMpgVO.setIsActive(InActive);
					}*/

					hrPayDdoSchemeMpgDAOImpl.update(hrPayDDOSchemeMpgVO);

					objectArgs.put("msg", "Updated Successfully");

				}

				else
				{
					objectArgs.put("msg", "You Can not Update");
				}
			//}

			/*else
			{

				List ExistList = hrPayDdoSchemeMpgDAOImpl.checkDuplicateDtls(BUDSUBHEADID);

				if(ExistList.size() == 0)
				{
				
				IdGenerator idGen = new IdGenerator();
				long Id = idGen.PKGenerator("HR_PAY_DDO_SCHEME_MPG", objectArgs);
				//System.out.println("Primary Key Id---->>>>>>>>>>" + Id);

				hrPayDDOSchemeMpgVO.setId(Id);
				hrPayDDOSchemeMpgVO.setOrgSchemeMstVO(orgSchemeMstVO);
				hrPayDDOSchemeMpgVO.setOrgDdoMst(orgDdoMst);
				hrPayDDOSchemeMpgVO.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
				hrPayDDOSchemeMpgVO.setCreatedDate(syDate);
				hrPayDDOSchemeMpgVO.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
				hrPayDDOSchemeMpgVO.setCmnLanguageMst(cmnLanguageMst);
				hrPayDDOSchemeMpgVO.setCmnLocationMst(cmnLocationMst);


				String ActiveType = "Active";
				//System.out.println("ActiveType ActiveType---->>>>>>" + ActiveType);
				if(IsActive.equalsIgnoreCase(ActiveType))
				{
					long active = 1;
					hrPayDDOSchemeMpgVO.setIsActive(active);
				}
				else
				{
					long InActive = 0L;
					hrPayDDOSchemeMpgVO.setIsActive(InActive);
				}




				hrPayDdoSchemeMpgDAOImpl.create(hrPayDDOSchemeMpgVO);

				
				
				objectArgs.put("BUDSUBHEADID", "BUDSUBHEADID");
				objectArgs.put("msg", "Inserted Successfully");
				

				}
				
				else
				{
					objectArgs.put("msg", "No Duplicates are Allowed");
				}
				
				
			}*/

			
			
			ResultObject resultObject= viewDDOSchemeDtlsForEntry(objectArgs);
			resultObject.setResultValue("budsubheadid");
			resultObject.setResultValue("dtlViewListsCustomVO");
			resultObject.setResultValue("passList");
			resultObject.setResultValue("listSize");
			objectArgs.put("resultObject", resultObject);
			
			
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "getSchemeEntryDtls");
			
			
			
			
			
			
			
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("End of saveGroupManagementService method:::::::: " +objectArgs.get("msg") );
			resObj.setResultValue(objectArgs);
			resObj.setViewName("DDOSchemeEntry");



		}

		catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}
}
