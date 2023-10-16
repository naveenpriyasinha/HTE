package com.tcs.sgv.eis.service;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.ExpressionMasterDAOImpl;
import com.tcs.sgv.allowance.service.Parser;
import com.tcs.sgv.allowance.service.SalaryRules;
import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.allowance.valueobject.HrPayExpressionMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.payroll.dao.PayrollCommonDAO;
import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.ChangeGISDetailsDAOImpl;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.HrPayOfficePostMpgDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.UpdatePaybillDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceCoreLogic;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.util.RevisedHrrAndHra;
import com.tcs.sgv.eis.valueobject.CommAllOtherDetailsVO;
import com.tcs.sgv.eis.valueobject.CommonAllowanceDetailsVO;
import com.tcs.sgv.eis.valueobject.EmpPaybillVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGISDtls;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PayrollCustomVO;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

@SuppressWarnings({ "unchecked", "deprecation" })
public class EmpAllwMapService extends ServiceImpl{
	Log logger = LogFactory.getLog( getClass() );
	int msg=0;
		
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");


	//	added by Ankit Bhatt for integrating with Outer

	long hrrId = Long.parseLong(resourceBundle.getString("hrrId"));
	long hraId = Long.parseLong(resourceBundle.getString("hraId"));
	long TraId = Long.parseLong(resourceBundle.getString("TraId"));
	long DaId = Long.parseLong(resourceBundle.getString("daCode"));
	long dcpsId = Long.parseLong(resourceBundle.getString("dcpsId"));

	long dcpsDaId = Long.parseLong(resourceBundle.getString("dcpsDaId"));
	long dcpsPayId = Long.parseLong(resourceBundle.getString("dcpsPayId"));
	long dcpsDelayId = Long.parseLong(resourceBundle.getString("dcpsDelayId"));

	long dedycType =  Long.parseLong(resourceBundle.getString("deducLookupId"));


	public ResultObject generateMap(Map objectArgs) {
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{
			//Added by Mrugesh for executing the deduction detail service
			objectArgs.put("onlyActiveLoans","yes");
			ResultObject deducServiceResObj = serv.executeService("GET_DEDUC_MASTER", objectArgs);
			objectArgs = (Map)deducServiceResObj.getResultValue();
			objectArgs.remove("onlyActiveLoans");
			//List deducServiceList = (List)deducServiceResObj.getResultValue();
			//Ended by Mrugesh



			EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			ExpressionMasterDAOImpl exprDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
			//OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());



			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String EmpAllowMpg="";
			long orgEmpid=0;
			//if(!StringUtility.getParameter("EmpAllowMpg", request).equals(""))
			if(voToService.get("EmpAllowMpg")!=null && !voToService.get("EmpAllowMpg").equals(""))
			{

				EmpAllowMpg= (String)voToService.get("EmpAllowMpg");
				logger.info("EmpAllowMpg is:-->" + EmpAllowMpg);
			}	
			long empid=0;
			HrEisEmpMst hrEisMst = new HrEisEmpMst();
			if(voToService.get("EmpId")!=null && !voToService.get("EmpId").equals(""))
			{			
				orgEmpid= Long.parseLong((String)voToService.get("EmpId"));
				logger.info("EmpId is:-->" + orgEmpid);
				EmpInfoDAOImpl hrEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				List<HrEisEmpMst> hrEmpList = hrEmpDao.getListByColumnAndValue("orgEmpMst.empId", orgEmpid);
				if(hrEmpList!=null && hrEmpList.size()>0)
				{
					hrEisMst=hrEmpList.get(0);
					empid=hrEisMst.getEmpId();
					logger.info("hrEisEmpId is "+empid);
				}				
			}

			HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
			// dispaly list commented as was not showing classs and designation of fixed pay

			/*List<HrEisOtherDtls> otherList = otherDao.getAllOtherData();
			logger.info("The size of OtherList is:-"+otherList.size());
			for(int i=0;i<otherList.size();i++){
				hrEisOtherDtls = (HrEisOtherDtls) otherList.get(i);
				if(hrEisOtherDtls.getHrEisEmpMst().getEmpType()!=300018 && hrEisOtherDtls.getHrEisEmpMst().getEmpType()!=300225)
					logger.info("The Value is:-"+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId()+" "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+ " "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpMname()+ " "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg().getOrgDesignationMst().getDsgnName()+" "+hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeName());
				else
				{
					HrEisSgdMpg sgdMpg = hrEisOtherDtls.getHrEisSgdMpg();
					HrEisGdMpg hrEisGdMpg=null;
					OrgGradeMst orgGradeMst = null;
					OrgDesignationMst orgDesigMst = null;
					String dsgnName="";
					if(sgdMpg!=null)
					{
					  hrEisGdMpg = hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg();
					  if(hrEisGdMpg!=null)
					  {
					    orgGradeMst = hrEisGdMpg.getOrgGradeMst();
					    orgDesigMst = hrEisGdMpg.getOrgDesignationMst();
					    if(orgDesigMst!=null)
					    {
					    	dsgnName = orgDesigMst.getDsgnName();
					    }
					  }

					}

					logger.info("The Value is:-"+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId()+" "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+ " "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpMname()+ " "+hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
			     }
			}
			 */

			List dataList =new ArrayList();
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long locationId=Long.parseLong(loginMap.get("locationId").toString());
			OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			List otherList = otherdtlsDao.getAllOtherDataForAllow(locationId,langId,empid);
			// Instead of making objects of empmaster ,othedetails,dfesignation strins have been used as VO as it is only for display to avoid whole of objects 
			Object[] row;
			if(otherList!=null &&  otherList.size() >0 )
			{                		
				for(int k=0;k<otherList.size();k++) 
				{

					PayrollCustomVO payrollCustomVO = new PayrollCustomVO();
					row = (Object[])otherList.get(k);
					payrollCustomVO.setOtherId(Long.parseLong(row[0]!=null?row[0].toString():"0")) ; 
					payrollCustomVO.setEmpName(row[1]!=null?row[1].toString():"") ; 
					payrollCustomVO.setGradeName(row[2]!=null?row[2].toString():"") ; 
					payrollCustomVO.setDsgnName(row[3]!=null?row[3].toString():"") ; 
					payrollCustomVO.setEmpType(Long.parseLong(row[4]!=null?row[4].toString():"0")) ; 
					payrollCustomVO.setEmpId(Long.parseLong(row[5]!=null?row[5].toString():"0")) ; 
					dataList.add(payrollCustomVO);	    	
				}    			
			}

			objectArgs.put("dataList", dataList);

			/*			OrgEmpMst newOrgEmpMst = hrEisMst.getOrgEmpMst();
			hrEisOtherDtls = otherDao.getOtherData(newOrgEmpMst.getEmpId());
			List actionList = empAllwMapDAO.getAllAllowanceType();
            List allowNamesFromMpg = empAllwMapDAO.getAllAllowanceTypeFromMpg(newOrgEmpMst.getEmpId());
			 */          



			hrEisOtherDtls = otherDao.getOtherData(hrEisMst.getOrgEmpMst().getEmpId());
			//Modified by Mrugesh
			long payCommonissionId = 2500340;//By default the commission id is 5th pay commission(For fixed and contractual employee)
			if(hrEisOtherDtls.getHrEisSgdMpg()!=null)
				payCommonissionId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId();
			//Ended by Mrugesh
			logger.info("===> For checking payCommonissionId :: "+payCommonissionId);
			
			if(payCommonissionId==2500340 || payCommonissionId==2500346)
			{
				payCommonissionId = 2500340;
			//Javed	
			}
			else
			{
				payCommonissionId = 2500341;
			}
			logger.info("===> After For checking payCommonissionId :: "+payCommonissionId);
			List actionList = empAllwMapDAO.getAllAllowanceType(payCommonissionId,locationId,hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());
			logger.info("The required size is "+actionList.size());
			logger.info("The required size is "+actionList.size());
			long totalAllw=0;
			Date sysdate = new Date();
			for(int k =0;k<actionList.size();k++)
			{
				Object[] arr=(Object[]) actionList.get(k);
				long amount =empAllwMapDAO.getAmountForAllowance(Long.valueOf(arr[0].toString()),empid,sysdate.getMonth()+1,sysdate.getYear()+1900);
				totalAllw+=amount;
				logger.info(" amount is for "+amount+" for "+arr[1].toString());
			}
			logger.info("total of allowance is "+totalAllw);
			logger.info("total of allowance is "+totalAllw);

			Calendar c1 = Calendar.getInstance();
			/*int month = c1.get(Calendar.MONTH)+1;
			int year = c1.get(Calendar.YEAR);*/

			List allowNamesFromMpg = new ArrayList();
			logger.info( "Org EmpID is "+hrEisMst.getOrgEmpMst().getEmpId());
			allowNamesFromMpg = empAllwMapDAO.getAllAllowanceTypeFromMpgNew(hrEisMst.getOrgEmpMst().getEmpId(),-1,-1 );
			logger.info("List Size is ::::"+allowNamesFromMpg.size());
			logger.info("emp id for allowance maping table is "+hrEisMst.getEmpId());



			double totalAllow = hrEisOtherDtls.getotherCurrentBasic();
			//double grossAmt=0;


			logger.info("finally list size is :::"+allowNamesFromMpg.size());

			for(Iterator it = allowNamesFromMpg.iterator();it.hasNext();)
			{
				Object[] row1 = (Object[])it.next();
				totalAllow=totalAllow+( Double.parseDouble( row1[2].toString() ) );

			}

			logger.info("the total allowance is "+totalAllow);



			//grossAmt = totalAllow;

			//SalaryRules salaryRules = new SalaryRules();
			/*SalaryRules_6thPay salaryRules = new SalaryRules_6thPay();
			Boolean chkPhyHandicap = new Boolean(hrEisOtherDtls.getPhyChallenged());

			Map input = new HashMap();
			input.put("basic", grossAmt);
			input.put("empType", 0);
			input.put("isHandicapped", chkPhyHandicap);

			long pt = Math.round(salaryRules.calculatePT(input));

			 payCommonissionId = 2500340;//By default the commission id is 5th pay commission(For fixed and contractual employee)
			if(hrEisOtherDtls.getHrEisSgdMpg()!=null)
				payCommonissionId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId();

			objectArgs.put("pt", pt);

			logger.info("the calculated pt is "+pt);
			 */
			//end by manoj for calcultaion of pt



			List exprMpg = exprDAO.getAllExprMasterData(langId,payCommonissionId);	
			logger.info("Expression List size is " + exprMpg.size());

			//logger.info("manish here : the size of action list is "+actionList.size());
			//logger.info(" "+actionList);
			objectArgs.put("allowList", actionList); //changed by Ankit Bhatt.
			objectArgs.put("totalAllow", totalAllow);
			objectArgs.put("totalAllw", totalAllw);
			objectArgs.put("hrEisOtherDtls", hrEisOtherDtls);
			//objectArgs.put("otherList", otherList);
			objectArgs.put("hrEisMst", hrEisMst);	
			objectArgs.put("allowNamesFromMpg", allowNamesFromMpg);
			objectArgs.put("allowNamesFromExpr", exprMpg);
			objectArgs.put("allowMpgSize",allowNamesFromMpg.size());
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			/*if(EmpAllowMpg.equalsIgnoreCase("Y"))
				resultObject.setViewName("empAllowMapView");
			else*/
			resultObject.setViewName("empAllowMapView");								
		}
		catch(Exception e)
		{
			logger.info("Error in generateMap of EmpAllowMapService.");
			objectArgs.put("msg", "There is some problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());

		}



		return resultObject;

	}




	public ResultObject insertEmpAllowMpgData(Map objectArgs)
	{

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		//HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");


		try
		{

			String PreviewBill=objectArgs.get("PreviewBill").toString();
			String MergedScreen=objectArgs.get("MergedScreen").toString();

			//// parameters for update paybill
			String updatePaybillFlg = "n";
			int paybillMonth=0;
			int paybillYear=0;
			//// parameters for update paybill
			Map voToService = new HashMap();




			if(objectArgs.get("updatePaybillFlg")!=null)
			{	
				updatePaybillFlg=objectArgs.get("updatePaybillFlg").toString();
			}
			if(objectArgs.get("paybillMonth")!=null)
			{	
				paybillMonth=Integer.parseInt(objectArgs.get("paybillMonth").toString());
			}	
			if(objectArgs.get("paybillYear")!=null)
			{	
				paybillYear=Integer.parseInt(objectArgs.get("paybillYear").toString());
			}	

			logger.info("test update paybill parameters in emp allow map service"+updatePaybillFlg+" "+paybillMonth+" "+paybillYear);		    

			if(objectArgs.get("batchupdate")!=null&&!objectArgs.get("batchupdate").equals("")&&objectArgs.get("batchupdate").equals("true"))
			{

				logger.info("inside batch update:::::::::::::::::::::");
				logger.info("inside batch update:::::::::::::::::::::");
				Date sysdate = new Date();
				int size= Integer.parseInt(  objectArgs.get("size").toString());
				long empid=0;

				long dbId=Long.parseLong(loginMap.get("dbId").toString());
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

				long locId=Long.parseLong(loginMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	

				long postId=Long.parseLong(loginMap.get("primaryPostId").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	

				long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

				long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
				CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);

				//if(objectArgs.get("empid")!=null&&!objectArgs.get("empid").equals(""))
				empid=Long.parseLong(objectArgs.get("empid").toString());
				EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				//ExpressionMasterDAOImpl exprDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
				EmpInfoDAOImpl empDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());

				//logger.info("The employee Id is:::::____"+empid); 
				EmpInfoDAOImpl empInfoDao= new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				List<HrEisEmpMst> hrEisEmpMstList = empInfoDao.getListByColumnAndValue("orgEmpMst.empId",empid);
				logger.info("Emmp Id is:-"+empid);
				HrEisEmpMst hrEisEmpMst=  new HrEisEmpMst();

				if(hrEisEmpMstList!=null && hrEisEmpMstList.size()>0)
				{
					hrEisEmpMst = hrEisEmpMstList.get(0);
				}
				logger.info("size in empallowmpg is"+size);

				Date sysDate = new Date();


				PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				HrPayPaybill hrpaPaybillDump = payBillDAOImpl.getPaybillDataByEmpId(  empid,  4,  sysDate.getMonth()+1,  sysDate.getYear()+1900) 	;


				logger.info("allow  size in empallowmpg is"+size);
				for(int j=1;j<=size;j++)
				{	

					HrPayEmpallowMpg empAllwMpg= (HrPayEmpallowMpg)objectArgs.get("empAllwMpg"+j);
					//logger.info("fatched Object is::" + empAllwMpg);
					// Add/Update Only for enabled text boxes of EmpAllowance map
					//logger.info("for loop with value of  j is"+j+" allw amt is "+empAllwMpg.getEmpAllowAmount()+"allow code"+empAllwMpg.getAllowCode());
					//logger.info( "in allow loop for j amount "+empAllwMpg.getEmpAllowAmount()+" allow desdc is "+empAllwMpg.getHrPayAllowTypeMst().getAllowDisplayName());

					empAllwMpg.setHrEisEmpMst(hrEisEmpMst);

					if(empAllwMpg.getEmpAllowAmount()!=-1)
					{
						//logger.info("amount is for "+empAllwMpg.getHrPayAllowTypeMst().getAllowDisplayName()+" amount is "+empAllwMpg.getEmpAllowAmount());
						if(empAllwMpg.getHrEisEmpMst().getEmpId() != 0)
						{
							empid = empAllwMpg.getHrEisEmpMst().getOrgEmpMst().getEmpId();

						}
						//logger.info(" emp id in service "+empid);
						logger.info(" emp id in service "+empid);
						empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
						//exprDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());


						String editFromVO = objectArgs.get("edit"+j).toString();
						logger.info("The value of edifFromVO is------->>>"+editFromVO);
						logger.info("The value of edifFromVO is------->>>"+editFromVO);
						if(editFromVO!=null && editFromVO.equals("Y"))
						{

							long allowCode = empAllwMpg.getAllowCode();
							HrPayEmpallowMpg hrEmpMpg = null;
							EmpAllwMapDAOImpl empAllowMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
							logger.info("allo code is :::"+allowCode);
							if(allowCode!=0)
							{
								hrEmpMpg =  empAllowMapDAO.read(allowCode);
							}
							logger.info("INSIDE UPDATE");
							logger.info("INSIDE UPDATE");
							//logger.info(  "Month and Year for allow is "+hrEmpMpg.getMonth()+" "+hrEmpMpg.getYear()+" empAllwMpg.getMonth() value is "+empAllwMpg.getMonth()+" "+empAllwMpg.getYear());



							HrEdpComponentMpgDAOImpl edpCompoDao = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class,serv.getSessionFactory());
							List<HrPayEdpCompoMpg> edpCompoList = edpCompoDao.getDataByEDP(  empAllwMpg.getHrPayAllowTypeMst().getAllowCode(),  2500134);
							HrPayEdpCompoMpg hrPayEdpCompoMpg = null;
							if(edpCompoList.iterator().hasNext())
								hrPayEdpCompoMpg=edpCompoList.iterator().next();



							if(hrEmpMpg!=null)
							{
								if(hrEmpMpg.getMonth()>0 && hrEmpMpg.getYear()>0 && hrEmpMpg.getMonth()==empAllwMpg.getMonth() && hrEmpMpg.getYear() == empAllwMpg.getYear())
								{
									logger.info("Record for month found::::");
									hrEmpMpg.setEmpAllowAmount(empAllwMpg.getEmpAllowAmount());
									hrEmpMpg.setHrPayAllowTypeMst(empAllwMpg.getHrPayAllowTypeMst());
									hrEmpMpg.setHrEisEmpMst(empAllwMpg.getHrEisEmpMst());
									hrEmpMpg.setEmpCurrentStatus(empAllwMpg.getEmpCurrentStatus());
									hrEmpMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
									hrEmpMpg.setOrgUserMstByUpdatedBy(orgUserMst);
									//hrEmpMpg.setMonth(empAllwMpg.getMonth());
									//hrEmpMpg.setYear(empAllwMpg.getYear());
									hrEmpMpg.setUpdatedDate(sysdate);
									empAllwMapDAO.update(hrEmpMpg);

									if(hrPayEdpCompoMpg != null && hrpaPaybillDump!=null)
									{
										String edpCode = hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
										String methodName = "setAllow"+edpCode;
										Class paybillClass = hrpaPaybillDump.getClass();
										Method method = paybillClass.getMethod(methodName, double.class);
										method.invoke(hrpaPaybillDump, empAllwMpg.getEmpAllowAmount());

									}

									//logger.info(" updating record "+hrEmpMpg.getAllowCode()+" is updated for "+empAllwMpg.getHrPayAllowTypeMst().getAllowDisplayName()+" for month "+empAllwMpg.getMonth()+" "+empAllwMpg.getYear());

								}												
								else
								{

									logger.info("Record for month NOT found:: master data :::: in else insertion");
									hrEmpMpg= new HrPayEmpallowMpg();
									IdGenerator idGen = new IdGenerator();

									allowCode = idGen.PKGenerator("HR_PAY_EMPALLOW_MPG", objectArgs);
									//allowCode = IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG", objectArgs);
									logger.info("allowCode:::"+allowCode);
									hrEmpMpg.setAllowCode(allowCode);
									hrEmpMpg.setEmpAllowAmount(empAllwMpg.getEmpAllowAmount());
									hrEmpMpg.setHrPayAllowTypeMst(empAllwMpg.getHrPayAllowTypeMst());
									hrEmpMpg.setHrEisEmpMst(empAllwMpg.getHrEisEmpMst());
									hrEmpMpg.setEmpCurrentStatus(empAllwMpg.getEmpCurrentStatus());
									hrEmpMpg.setTrnCounter(1);
									hrEmpMpg.setCmnDatabaseMst(cmnDatabaseMst);
									hrEmpMpg.setCmnLocationMst(cmnLocationMst);
									hrEmpMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
									hrEmpMpg.setOrgUserMstByCreatedBy(orgUserMst);
									hrEmpMpg.setOrgPostMstByCreatedByPost(orgPostMst);
									hrEmpMpg.setOrgUserMstByUpdatedBy(orgUserMst);
									hrEmpMpg.setMonth(empAllwMpg.getMonth());
									hrEmpMpg.setYear(empAllwMpg.getYear());
									hrEmpMpg.setCreatedDate(sysdate);
									hrEmpMpg.setUpdatedDate(sysdate);
									empAllwMapDAO.create(hrEmpMpg);

									if(hrPayEdpCompoMpg != null && hrpaPaybillDump!=null)
									{
										String edpCode = hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
										String methodName = "setAllow"+edpCode;
										Class paybillClass = hrpaPaybillDump.getClass();
										Method method = paybillClass.getMethod(methodName, double.class);
										method.invoke(hrpaPaybillDump, empAllwMpg.getEmpAllowAmount());

									}
									//logger.info(" inserting record "+hrEmpMpg.getAllowCode()+" is updated for "+empAllwMpg.getHrPayAllowTypeMst().getAllowDisplayName()+" for month "+empAllwMpg.getMonth()+" "+empAllwMpg.getYear());

								}
							}
							else
							{
								hrEmpMpg= new HrPayEmpallowMpg();
								IdGenerator idGen = new IdGenerator();
								logger.info("Record for month NOT found at all in else insertion");
								allowCode = idGen.PKGenerator("HR_PAY_EMPALLOW_MPG", objectArgs);
								//allowCode = IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG", objectArgs);
								logger.info("allowCode::::"+allowCode);
								hrEmpMpg.setAllowCode(allowCode);
								hrEmpMpg.setEmpAllowAmount(empAllwMpg.getEmpAllowAmount());
								hrEmpMpg.setHrPayAllowTypeMst(empAllwMpg.getHrPayAllowTypeMst());
								hrEmpMpg.setHrEisEmpMst(empAllwMpg.getHrEisEmpMst());
								hrEmpMpg.setEmpCurrentStatus(empAllwMpg.getEmpCurrentStatus());
								hrEmpMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
								hrEmpMpg.setOrgUserMstByUpdatedBy(orgUserMst);
								hrEmpMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
								hrEmpMpg.setOrgUserMstByCreatedBy(orgUserMst);
								hrEmpMpg.setCmnDatabaseMst(cmnDatabaseMst);
								hrEmpMpg.setCmnLocationMst(cmnLocationMst);
								hrEmpMpg.setTrnCounter(1);
								hrEmpMpg.setCreatedDate(sysdate);
								hrEmpMpg.setMonth(empAllwMpg.getMonth());
								hrEmpMpg.setCreatedDate(sysdate);
								hrEmpMpg.setYear(empAllwMpg.getYear());
								hrEmpMpg.setUpdatedDate(sysdate);
								empAllwMapDAO.create(hrEmpMpg);


								if(hrPayEdpCompoMpg != null && hrpaPaybillDump!=null)
								{
									String edpCode = hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
									String methodName = "setAllow"+edpCode;
									Class paybillClass = hrpaPaybillDump.getClass();
									Method method = paybillClass.getMethod(methodName, double.class);
									method.invoke(hrpaPaybillDump, empAllwMpg.getEmpAllowAmount());

								}
								//logger.info(" inserting record "+hrEmpMpg.getAllowCode()+" is updated for "+empAllwMpg.getHrPayAllowTypeMst().getAllowDisplayName()+" for month "+empAllwMpg.getMonth()+" "+empAllwMpg.getYear());
							}






						}
						else if(editFromVO!=null && editFromVO.equals("N"))
						{


							logger.info("INSIDE INSERT");
							IdGenerator IdGen = new IdGenerator();
							Long allowCode =IdGen.PKGenerator("HR_PAY_EMPALLOW_MPG", objectArgs);
							// Long allowCode =IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG", objectArgs);
							logger.info("allowCode::::"+allowCode);
							empAllwMpg.setAllowCode(allowCode);
							logger.info("allow id is " + empAllwMpg.getHrPayAllowTypeMst().getAllowCode());
							logger.info("emp id is "+empAllwMpg.getHrEisEmpMst().getEmpId());


							empAllwMpg.setCreatedDate(sysdate);						
							empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
							empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
							empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
							empAllwMpg.setCmnLocationMst(cmnLocationMst);		
							empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
							empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
							empAllwMpg.setUpdatedDate(sysdate);
							empAllwMpg.setMonth(sysdate.getMonth()+1);
							empAllwMpg.setYear(sysdate.getYear()+1900);
							empAllwMpg.setTrnCounter(new Integer(1));
							logger.info("INSIDE CREATE");
							empAllwMapDAO.create(empAllwMpg);	




							//				added by Ankit Bhatt for merging the screens

						}
						else
						{
							throw new NullPointerException();
						}


					}


					logger.info("end for loop with value of  j is"+j);
					logger.info("end for loop with value of  j is"+j);

				}










				///try ends

				//by Ankit bhatt

				//ended
				//manish
				ResultObject resultO=null;
				logger.info("Before deduction call");



				resultO=serv.executeService("INSERT_EMP_DEDUCT_DTLS", objectArgs);
				objectArgs = (Map)resultO.getResultValue();

				logger.info("after deduction call");


				///////this is a try
				//			added by Ankit Bhatt for merging the screens
				HttpServletRequest request =(HttpServletRequest)objectArgs.get("requestObj");
				long otherId=0;
				if(request != null && request.getParameter("otherId")!=null)
					otherId =Long.valueOf(request.getParameter("otherId").toString());
				voToService.put("edit", "N");
				voToService.put("otherId", otherId);

				voToService.put("Employee_ID_EmpOtherSearch", String.valueOf(empid));
				objectArgs.put("voToServiceMap", voToService);

				//added by ravysh
				objectArgs.put("MergedScreen", MergedScreen);
				objectArgs.put("PreviewBill", PreviewBill);

				ResultObject resultObj=null;
				if(MergedScreen.equals("YES"))
					resultObj = serv.executeService("getOtherDataMerged", objectArgs);
				else
					resultObj = serv.executeService("getOtherData", objectArgs);

				objectArgs = (Map)resultObj.getResultValue();
				//ended by Ankit Bhatt
				objectArgs.put("MESSAGECODE",300005);

				objectArgs.put("otherId", otherId);


				String pfType =""; 
				long gpfAmt =0;
				//long pfId=0;
				long gpfTextAmt =0;
				/*long PF_ID=0;
				long VPF_ID=0;
				long jeepRent=0;
				long jeepRentId=0;
				//Added by Mrugesh
				long itId=0;
				long It_Val=0;*/


				/*DeductionDtlsDAOImpl deducDao = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
				VoluntryPFDAOImpl vpfDao = new VoluntryPFDAOImpl(HrPayVpfDtls.class,serv.getSessionFactory());
				DeducTypeMasterDAOImpl deducMstDao = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());*/

				//IdGenerator IdGen = new IdGenerator();
				/*List<HrPayDeductionDtls> deducList=null;
				HrPayVpfDtls vpfVo = new HrPayVpfDtls();
				List vpfVoList = new ArrayList();
				HrPayDeductionDtls hrPayDeducDtls = new HrPayDeductionDtls();*/



				if(pfType.equalsIgnoreCase("VPF"))
				{
					logger.info("the vpf updation start ");
				}
				else if(pfType.equalsIgnoreCase("GPF"))
				{
					logger.info("the gpf to vpf updation start ");
					logger.info("thje gpfAmt is "+gpfAmt +" and gpfText Amt is "+gpfTextAmt);
					if(gpfAmt<gpfTextAmt)
					{}
				}
				//for gpf vpf issue end
				HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();

				logger.info("Before inserting non computational deductions");
				//Deduction deduction=new Deduction();



				logger.info("After inserting non computational deductions");


				EmpLoanDAOImpl empLoanDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());

				int loanSize = 0;
				if(objectArgs.get("loanSize")!=null)
					loanSize=Integer.parseInt(objectArgs.get("loanSize").toString());

				for(int loanCnt=1;loanCnt<=loanSize;loanCnt++)
				{
					long empLoanId = 0;
					String loanAmtType ="";
					long emiAmount=0;

					empLoanId=Long.parseLong(objectArgs.get("empLoanId"+loanCnt).toString());
					emiAmount=Long.parseLong(objectArgs.get("emiAmount"+loanCnt).toString());
					loanAmtType=objectArgs.get("loanAmtType"+loanCnt).toString();



					logger.info("from service empLoanId "+empLoanId+" emiAmount "+emiAmount+"loanAmtType "+loanAmtType);

					HrLoanEmpDtls loanEmpDtls = empLoanDao.read(empLoanId);
					logger.info("The HrEisEmpId is :---"+loanEmpDtls.getHrEisEmpMst().getEmpId());
					logger.info("The HrEisEmpId is :---"+loanEmpDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());
					if(loanAmtType.equalsIgnoreCase("interest"))
					{

						loanEmpDtls.setLoanIntEmiAmt(emiAmount);
					}
					else if(loanAmtType.equalsIgnoreCase("principle"))
					{
						loanEmpDtls.setLoanPrinEmiAmt(emiAmount);
					}

					empLoanDao.update(loanEmpDtls);
				}
				//end by manoj for merging of loan			


				List empList = empDAO.getEmpIdData(empid, cmnLanguageMst);
				//List exprMpg = exprDAO.getAllExprMasterData(langId,payCommonissionId);	
				long payCommonissionId = 2500340;//By default the commission id is 5th pay commission(For fixed and contractual employee)
				if(hrEisOtherDtls.getHrEisSgdMpg()!=null)
					payCommonissionId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId();

				
				logger.info("===> For checking payCommonissionId :: "+payCommonissionId);
				
				if(payCommonissionId==2500340 || payCommonissionId==2500346)
				{
					payCommonissionId = 2500340;
				//Javed	
				}
				else
				{
					payCommonissionId = 2500341;
				}
				logger.info("===> After For checking payCommonissionId :: "+payCommonissionId);
				
				List actionList =null;
				if(hrEisEmpMst!=null && hrEisEmpMst.getOrgEmpMst()!=null)
				{
					long empId = hrEisEmpMst.getOrgEmpMst().getEmpId();
					actionList = empAllwMapDAO.getAllAllowanceType(payCommonissionId,locId,empId);
				}
				else
				{
					logger.info("Problem...EmpId = 0");
					actionList = empAllwMapDAO.getAllAllowanceType(payCommonissionId,locId,0);
				}
				logger.info("after getting actionList with size"+actionList.size());
				logger.info("after getting actionList with size"+actionList.size());
				logger.info("going to call...........");
				logger.info("going to call ...............");
				Calendar cal = Calendar.getInstance();
				int month =cal.get(Calendar.MONTH)+1;
				int year =cal.get(Calendar.YEAR);
				List allowNamesFromMpg = empAllwMapDAO.getAllAllowanceTypeFromMpgNew(empid,month,year);
				//List allowNamesFromMpg = new ArrayList();

				/*for(Iterator it = allowNamesFromMpgTemp.iterator();it.hasNext();)
		            {
		            	Object[] row1 = (Object[])it.next();

		            	logger.info("the allow code is "+row1[0]+" and amt is "+row1[2]);
		            	logger.info("parsing amount: " +Double.parseDouble( row1[2].toString() ));
		            	long allowCode = Long.parseLong(row1[0].toString());
		            	logger.info("the allow  in view temp pb from empallowmpg is "+((HrPayAllowTypeMst)row1[1]).getAllowDesc()+" and amt is "+row1[2]);

		            	if(Integer.parseInt( row1[3].toString())==-1 && Integer.parseInt( row1[4].toString())==-1)
		            	{
		            		 for(int i=0;i<allowNamesFromMpgTemp.size();i++)
		            		 {
		            			 if(((Object[])allowNamesFromMpgTemp.get(i))[0].toString().equals(row1[0].toString()))
		            			 {
		            				 if(Integer.parseInt( ((Object[])allowNamesFromMpgTemp.get(i))[3].toString())>0 )
		            				 {
		            					 row1[3] = -2;
		            					 row1[4] = -2;
		            					 break;

		            				 }
		            			 }
		            		 }

		            	}



		            }

		            for(Iterator it = allowNamesFromMpgTemp.iterator();it.hasNext();)
		            {
		            	Object[] row1 = (Object[])it.next();
		            	if(Integer.parseInt( row1[3].toString())!=-2 && Integer.parseInt( row1[4].toString())!=-2)
		            	{
		            		logger.info("the allow  in final pb from empallowmpg is "+((HrPayAllowTypeMst)row1[1]).getAllowDesc()+" and amt is "+row1[2]);
		            		 allowNamesFromMpg.add(row1);
		            	}

		            }*/


				logger.info("after getting allowNamesFromMpg with size"+allowNamesFromMpg.size());
				logger.info("after getting allowNamesFromMpg with size"+allowNamesFromMpg.size());



				//added by  







				int paybillMonthNew =sysDate.getMonth()+1;
				//int paybillMonthNew = sysDate;
				int paybillYearNew=sysDate.getYear()+1900;
				logger.info("month :::::::::::::::"+ paybillMonthNew);
				logger.info("year :::::::::::"+ paybillYearNew);



				PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				HrPayPaybill hrpaPaybill = paybillDAOImpl.getPaybillDataByEmpId(  empid,  4,  paybillMonthNew,   paybillYearNew) 	;
				EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				HrEisEmpMst empMst =null;
				List newList= empinfodao.getEmpIdData(empid);
				if(newList != null&& newList.size()>0)
				{
					empMst = (HrEisEmpMst)newList.iterator().next();
				}
				long  changedUserId=0;
				if(empMst!=null )
				{

					changedUserId = empMst.getOrgEmpMst().getOrgUserMst().getUserId(); 
				}
				logger.info("Changed User Id "+changedUserId);
				//HrPayPaybill hrpaPaybill=billDAOImpl.getPaybillDataByPost(postIdNew,paybillMonthNew, paybillYearNew);
				logger.info("HrPayPaybill::::::::::::::::::::::"+hrpaPaybill);
				// logger.info("IT::::::::::::::::::"+Double.parseDouble(objectArgs.get("incomeTax").toString()));
				logger.info("HrPayPaybill::::::::::::::::::::::"+hrpaPaybill);
				if(hrpaPaybill != null)
				{

				}
				else{
					logger.info(";;;;;;;;;;in else part;;;;;;;;;;");
					logger.info(":::::::::::::::::in else part::::::::::::::::::::");
					//OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());




					//OrgUserMstDaoImpl orgUserMstDaoImpl =new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());

					//OrgUserMst orgUserMstNew=orgUserMstDaoImpl.read(userId);
					OrgUserMst tempUserMst = orgUserMstDaoImpl.read(changedUserId);
					logger.info("tempUserMst  "+tempUserMst);
					long changedPostId =0;
					GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(OrgUserpostRlt.class);
					genDao.setSessionFactory(serv.getSessionFactory());
					List postList = genDao.getListByColumnAndValue("orgUserMst.userId",tempUserMst.getUserId());
					if(postList!=null && postList.size()>0)
						changedPostId=( (OrgUserpostRlt)postList.get(0)).getOrgPostMstByPostId().getPostId();


					//BatchITGpfDetailsUpdateDaoImpl dao = new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
					OrgPostMst orgPostMstNew = orgPostMstDaoImpl.read(postId);
					//OrgPostMst changePostMst = orgPostMstDaoImpl.read(postId);
					//if(postIdNew!=0)
					//orgPostMstNew = orgPostMstDaoImpl.read(postIdNew);
					long changeEmpId =0;
					if(empMst!=null)
						changeEmpId= empMst.getEmpId();
					//long locId=Long.valueOf(loginMap.get("locationId").toString());
					//long dbId=Long.valueOf(loginMap.get("dbID").toString());
					//long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
					//CmnDatabaseMstDaoImpl cmnDatabaseMstDao = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
					//CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDao.read(dbId);
					Map newMap=new HashMap();
					newMap=objectArgs;
					if(orgPostMstNew!=null) {
						newMap.put("changedEmpId",changeEmpId);
						newMap.put("changedPostId",changedPostId);
						newMap.put("locId",locId);
						newMap.put("serviceLocator",serv);
						//newMap.put("changedPostId",postIdNew);
						newMap.put("OrgPostMst",orgPostMst);
						newMap.put("OrgUserMst",orgUserMst);
						newMap.put("cmnDatabaseMst",cmnDatabaseMst);
						GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
						long changedRecordPK=generateBillServiceHelper.insertChangedRecord(newMap);
						logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
						logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
						objectArgs.put("valueUpdated", "Y");
					}

				}
				//ended by  

				objectArgs.put("allowList", actionList);			
				objectArgs.put("allowNamesFromMpg", allowNamesFromMpg);	
				objectArgs.put("allowMpgSize",allowNamesFromMpg.size());
				objectArgs.put("empList",empList);
				//objectArgs.put("allowNamesFromExpr", exprMpg);			
				resultObject.setResultCode(ErrorConstants.SUCCESS);			
				resultObject.setResultValue(objectArgs);

				if(MergedScreen.equals("YES"))
					resultObject.setViewName("OtherEditListMerged");
				else
					resultObject.setViewName("otherEditList");



			}
			else
			{

				logger.info("inside else part of batch update:::::::::");
				logger.info("inside else part of batch update:::::::::");
				HrPayEmpallowMpg empAllwMpg= (HrPayEmpallowMpg)objectArgs.get("empAllwMpg");
				//ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				long empid=0;
				HrEisEmpMst hrEisEmp = null;
				if(empAllwMpg!=null)
				{
					hrEisEmp = empAllwMpg.getHrEisEmpMst();
				}
				if( hrEisEmp!= null)
					empid = hrEisEmp.getEmpId();
				//logger.info(" from service the allowance amount is "+empAllwMpg.getEmpAllowAmount());
				logger.info(" emp id in service "+empid);
				EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				ExpressionMasterDAOImpl exprDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
				//HttpSession session=request.getSession();

				// Map loginMap = (Map) objectArgs.get("baseLoginMap");

				//long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
				long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

				//long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
				long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
				CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);

				long dbId=Long.parseLong(loginMap.get("dbId").toString());
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

				long locId=Long.parseLong(loginMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
				//logger.info("Loc Id is:-->" + dbId + " " + locId);

				long postId=Long.parseLong(loginMap.get("primaryPostId").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	

				Date sysdate = new Date();

				EmpInfoDAOImpl empDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				List empList = empDAO.getEmpIdData(empid, cmnLanguageMst);
				OtherDetailDAOImpl otherDetailDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());

				HrEisOtherDtls hrEisOtherDtls =otherDetailDao.getOtherData(hrEisEmp.getOrgEmpMst().getEmpId());
				long payCommissionId = 2500340;	
				if(hrEisOtherDtls!=null && hrEisOtherDtls.getHrEisSgdMpg()!=null && hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst()!=null
						&& hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst()!=null)
					payCommissionId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId();
				
				logger.info("===> For checking payCommonissionId :: "+payCommissionId);
				
				if(payCommissionId==2500340 || payCommissionId==2500346)
				{
					payCommissionId = 2500340;
				//Javed	
				}
				else
				{
					payCommissionId = 2500341;
				}
				logger.info("===> After For checking payCommonissionId :: "+payCommissionId);


				List exprMpg = exprDAO.getAllExprMasterData(langId,otherDetailDao.getOtherData(hrEisEmp.getOrgEmpMst().getEmpId()).getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId());	
				//empMst = empinfodao.read(new Long(2));

				String editFromVO = objectArgs.get("edit").toString();


				//if(request.getParameter("edit").equals("Y"))
				if(editFromVO!=null && editFromVO.equals("Y"))
				{

					logger.info("inside else if part");
					logger.info("in if edit from vo");
					long allowCode = empAllwMpg.getAllowCode();
					EmpAllwMapDAOImpl empAllowMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
					HrPayEmpallowMpg hrEmpMpg =  empAllowMapDAO.read(allowCode);
					logger.info("INSIDE UPDATE");



					if(hrEmpMpg.getMonth() == empAllwMpg.getMonth() && hrEmpMpg.getMonth() == empAllwMpg.getMonth())
					{
						hrEmpMpg.setEmpAllowAmount(empAllwMpg.getEmpAllowAmount());
						hrEmpMpg.setHrPayAllowTypeMst(empAllwMpg.getHrPayAllowTypeMst());
						hrEmpMpg.setHrEisEmpMst(empAllwMpg.getHrEisEmpMst());
						hrEmpMpg.setEmpCurrentStatus(empAllwMpg.getEmpCurrentStatus());
						hrEmpMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
						hrEmpMpg.setOrgUserMstByUpdatedBy(orgUserMst);
						hrEmpMpg.setUpdatedDate(sysdate);
						empAllwMapDAO.update(hrEmpMpg);
					}
					else
					{
						hrEmpMpg = new HrPayEmpallowMpg();
						IdGenerator idgen = new IdGenerator();
						allowCode = idgen.PKGenerator("HR_PAY_EMPALLOW_MPG", objectArgs);
						//allowCode = IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG", objectArgs);
						logger.info("allowCode::::"+allowCode);
						hrEmpMpg.setAllowCode(allowCode);
						hrEmpMpg.setEmpAllowAmount(empAllwMpg.getEmpAllowAmount());
						hrEmpMpg.setHrPayAllowTypeMst(empAllwMpg.getHrPayAllowTypeMst());
						hrEmpMpg.setHrEisEmpMst(empAllwMpg.getHrEisEmpMst());
						hrEmpMpg.setEmpCurrentStatus(empAllwMpg.getEmpCurrentStatus());
						hrEmpMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
						hrEmpMpg.setMonth(sysdate.getMonth()+1);
						hrEmpMpg.setYear(sysdate.getYear()+1900);
						hrEmpMpg.setOrgUserMstByUpdatedBy(orgUserMst);
						empAllwMapDAO.create(hrEmpMpg);

					}
					logger.info("reached here");
					//added by  





					Date sysDate=new Date();

					int paybillMonthNew =sysDate.getMonth()+1;

					int paybillYearNew=sysDate.getYear()+1900;
					logger.info("month :::::::::::::::"+ paybillMonthNew);
					logger.info("year :::::::::::"+ paybillYearNew);



					PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
					HrPayPaybill hrpaPaybill = paybillDAOImpl.getPaybillDataByEmpId(  empid,  4,  paybillMonthNew,   paybillYearNew) 	;
					EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
					HrEisEmpMst empMst = empinfodao.read(empid);

					long changedUserId=0;
					if(empMst!=null && empMst.getOrgPostMst()!=null)
					{
						//postIdNew  = empMst.getOrgPostMst().getPostId();
						changedUserId = empMst.getOrgEmpMst().getOrgUserMst().getUserId(); 
					}




					OrgUserMst tempUserMst = orgUserMstDaoImpl.read(changedUserId);
					long changedPostId =0;
					GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(OrgUserpostRlt.class);
					genDao.setSessionFactory(serv.getSessionFactory());
					List postList = genDao.getListByColumnAndValue("orgUserMst.userId",tempUserMst.getUserId());
					if(postList!=null && postList.size()>0)
						changedPostId=( (OrgUserpostRlt)postList.get(0)).getOrgPostMstByPostId().getPostId();


					//long postIdNew  = empMst.getOrgPostMst().getPostId();

					logger.info("HrPayPaybill::::::::::::::::::::::"+hrpaPaybill);

					logger.info("HrPayPaybill::::::::::::::::::::::"+hrpaPaybill);
					if(hrpaPaybill != null)
					{
						long userIdNew = hrpaPaybill.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
						logger.info("in if part");
						logger.info("in if part");


						hrpaPaybill.setAllow0102((Integer)objectArgs.get("sp"));//sp
						hrpaPaybill.setAllow0105((Integer)objectArgs.get("ltc"));//leave travel consesion
						//hrpaPaybill.setA();//interim releave
						hrpaPaybill.setAllow0108((Integer)objectArgs.get("bonus"));//bonus
						//hrpaPaybill.setA();//other consesion
						//hrpaPaybill.setA();//contigency
						hrpaPaybill.setAllow0101((Integer)objectArgs.get("ppay"));//personal pay


						////not found for interim releave, other consesion,contigency , vpf 

						logger.info("IT Val is :::::::::::::: "+objectArgs.get("It_Val").toString());    
						logger.info("IT Val is :::::::::::::: "+objectArgs.get("It_Val").toString());
						hrpaPaybill.setIt(Double.parseDouble(objectArgs.get("It_Val").toString()));//it



						hrpaPaybill.setDeduc9780((Integer)objectArgs.get("jeepRent"));//jeep rent

						paybillDAOImpl.update(hrpaPaybill);

					}
					else{
						logger.info(";;;;;;;;;;in else part;;;;;;;;;;");
						logger.info(":::::::::::::::::in else part::::::::::::::::::::");

						long changeEmpId =0;
						if(empMst!=null)
							changeEmpId= empMst.getEmpId();

						//OrgUserMst  orgUserMstNew=orgUserMstDaoImpl.read(userId);
						//BatchITGpfDetailsUpdateDaoImpl dao = new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());

						//OrgPostMst orgPostMstNew = orgPostMstDaoImpl.read(postIdNew);

						Map newMap=new HashMap();
						newMap=objectArgs;
						newMap.put("changedEmpId",changeEmpId);
						newMap.put("locId",locId);
						newMap.put("serviceLocator",serv);
						newMap.put("changedPostId",changedPostId);
						newMap.put("OrgPostMst",orgPostMst);
						newMap.put("OrgUserMst",orgUserMst);
						newMap.put("cmnDatabaseMst",cmnDatabaseMst);
						GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
						long changedRecordPK=generateBillServiceHelper.insertChangedRecord(newMap);
						logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
						logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);


					}
					//ended by  

					objectArgs.put("msg", "Record Updated Successfully");

					logger.info("Create complete...");
				}
				else if(editFromVO!=null && editFromVO.equals("N"))
				{
					logger.info("inside else else-if part ");
					logger.info("inside else else-if part ");
					IdGenerator IdGen = new IdGenerator();
					Long allowCode =IdGen.PKGenerator("HR_PAY_EMPALLOW_MPG", objectArgs);
					//Long allowCode =IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG", objectArgs);
					logger.info("allowCode::::"+allowCode);
					empAllwMpg.setAllowCode(allowCode);
					logger.info("value of id is "+empAllwMpg.getAllowCode());


					empAllwMpg.setCreatedDate(sysdate);						
					empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
					empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
					empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
					empAllwMpg.setCmnLocationMst(cmnLocationMst);		
					empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
					empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
					empAllwMpg.setUpdatedDate(sysdate);
					empAllwMpg.setTrnCounter(new Integer(1));
					logger.info("INSIDE CREATE");
					empAllwMapDAO.create(empAllwMpg);
					objectArgs.put("msg", "Record Inserted Successfully");

				}
				else
				{
					throw new NullPointerException();
				}

				List actionList = empAllwMapDAO.getAllAllowanceType(payCommissionId,locId,hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId());

				Calendar cal = Calendar.getInstance();
				int month =cal.get(Calendar.MONTH)+1;
				int year =cal.get(Calendar.YEAR);
				//add logic here
				List allowNamesFromMpg = empAllwMapDAO.getAllAllowanceTypeFromMpg(empid,month,year);
				//List allowNamesFromMpg = new ArrayList();

				/* for(Iterator it = allowNamesFromMpgTemp.iterator();it.hasNext();)
            {
            	Object[] row1 = (Object[])it.next();

            	logger.info("the allow code is "+row1[0]+" and amt is "+row1[2]);
            	logger.info("parsing amount: " +Double.parseDouble( row1[2].toString() ));
            	int allowCode = Integer.parseInt(row1[0].toString());
            	logger.info("the allow  in view temp pb from empallowmpg is "+((HrPayAllowTypeMst)row1[1]).getAllowDesc()+" and amt is "+row1[2]);

            	if(Integer.parseInt( row1[3].toString())==-1 && Integer.parseInt( row1[4].toString())==-1)
            	{
            		 for(int i=0;i<allowNamesFromMpgTemp.size();i++)
            		 {
            			 if(((Object[])allowNamesFromMpgTemp.get(i))[0].toString().equals(row1[0].toString()))
            			 {
            				 if(Integer.parseInt( ((Object[])allowNamesFromMpgTemp.get(i))[3].toString())>0 )
            				 {
            					 row1[3] = -2;
            					 row1[4] = -2;

            				 }
            			 }
            		 }

            	}



            }

            for(Iterator it = allowNamesFromMpgTemp.iterator();it.hasNext();)
            {
            	Object[] row1 = (Object[])it.next();
            	if(Integer.parseInt( row1[3].toString())!=-2 && Integer.parseInt( row1[4].toString())!=-2)
            	{
            		 allowNamesFromMpg.add(row1);
            	}

            }*/



				/*if(actionList.size()>0 )
			logger.info("The actionlist data is " + actionList.size()+actionList.get(0));
			if(allowNamesFromMpg.size()>0)
			logger.info("The allowNamesFromMpg data is " + allowNamesFromMpg.get(0));*/
				objectArgs.put("allowList", actionList);			
				objectArgs.put("allowNamesFromMpg", allowNamesFromMpg);	
				objectArgs.put("allowMpgSize",allowNamesFromMpg.size());
				objectArgs.put("empList",empList);
				objectArgs.put("allowNamesFromExpr", exprMpg);									
				resultObject.setResultValue(objectArgs);


				if(MergedScreen.equals("YES")) {				
					resultObject.setViewName("OtherEditListMerged");
				}
				else
					resultObject.setViewName("otherEditList");									

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				logger.info("Service complete Ankit..");
				//logger.info("INSETED SUCCESSFULLY");

			}	
			if(updatePaybillFlg.equalsIgnoreCase("y"))
			{
				long empid=Long.parseLong(  objectArgs.get("empid").toString());
				objectArgs.put("empId",empid);
				objectArgs.put("paybillMonth", paybillMonth);
				objectArgs.put("paybillYear", paybillYear);
				ResultObject resultObj = serv.executeService("viewTemporaryPayBillSrvc", objectArgs);
				objectArgs =(Map) resultObj.getResultValue();
				ResultObject resultObj1 = serv.executeService("fillPaybillData", objectArgs);
				objectArgs =(Map) resultObj1.getResultValue();
				resultObject.setViewName("updatePaybill");
			}





		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...");			
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ ne.getMessage());
		}
		catch(Exception e)
		{			
			logger.info("Null Pointer Exception Ocuures...");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
		}

		return resultObject;
	}


	String tableName=new String();
	String selColName = new String();
	String conColName= new String();
	String conColValue;
	int resultCount =0;
	ArrayList objList ;
	Parser parExpression = new Parser();
	double parResult=0;

	public ResultObject insertEmpAllowData(Map objServiceArgs)//here
	{
		ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
		OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		// Started By Urvin shah.1
		double totalSalary=0d;			//It's a total of the Basic salary and all allowances.		
		// Ended By Urvin shah.
		try
		{
			//for logindetailmap
			///HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			//HttpSession session=request.getSession();	
			//end here for logindetailmap
			//AllowTypeMasterDAOImpl allowTypeMasterDAOImpl = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());
			
			ChangeGISDetailsDAOImpl changeGISDetailsDAOImpl = new ChangeGISDetailsDAOImpl(HrEisGISDtls.class,serv.getSessionFactory());
			HrEisGISDtls hrEisGISDtls = null;
			
			
			double dpValue =0;
			//added by manoj for 6th pay 
			double gpValue=0;
			//end by manoj for 6th pay

			
			 EmpPaybillVO empPaybill = new EmpPaybillVO();
			 Long postIdsStr =null;
			long orgEmpId;
			long eisEmpId;
			long basicAmt;
			long incomeTax;
			long desigId;
			long gradeId;
			long scaleId;
			long scaleStartAmt;
			long scaleEndAmt;
			String dcpsOrGPF;
			/*long salPostId;
			long salUserId;*/
			long salPayCommissionId;
			long salEmpType;
			long gradeCode;
			int isAvailedHRA;
			Date empDOB;
			Date empSrvcExp;
			Date empDOJ;
			String isPhyHandicapped;
			long gradePay;
			long empCity;
			long qtrRentAmt=0;
			long postPSRNo;
			long otherDtlsId;
			
			//added by ravysh
			//String PreviewBill=objServiceArgs.get("PreviewBill")!=null?objServiceArgs.get("PreviewBill").toString():"";
			String MergedScreen=objServiceArgs.get("MergedScreen")!=null?objServiceArgs.get("MergedScreen").toString():"";


			String allowId="";
			allowId=objServiceArgs.get("allowId")!=null && !objServiceArgs.get("allowId").equals("")?(objServiceArgs.get("allowId").toString()):"";
			String deducId="";
			deducId=objServiceArgs.get("deducId")!=null && !objServiceArgs.get("deducId").equals("")?(objServiceArgs.get("deducId").toString()):"";

			logger.info(deducId+"deducId ******************Inside insertEmpAllowData***************** allowId"+allowId);

			//Map loginMap =(Map)session.getAttribute("loginMap");
			Map loginMap = (Map) objServiceArgs.get("baseLoginMap");

			ResourceBundle payrollConstants = ResourceBundle.getBundle("resources.Payroll");

			long fifthPayCommLookupId = Long.parseLong(payrollConstants.getString("commissionFive"));
			long sixthPayCommLookupId = Long.parseLong(payrollConstants.getString("commissionSix"));
			long commissionFourId =  Long.parseLong(resourceBundle.getString("commissionFourId"));
			long commissionThreeId =  Long.parseLong(resourceBundle.getString("commissionThreeId"));
			long commissionTwoId =  Long.parseLong(resourceBundle.getString("commissionTwoId"));
			long commissionOneId =  Long.parseLong(resourceBundle.getString("commissionOneId"));
			long commissionSevenId =  Long.parseLong(resourceBundle.getString("commissionSevenId"));

			double scale_start_amt = 0;
			double GradePayExcell = 0;

			if (objServiceArgs.get("scale_start_amt") != null && !objServiceArgs.get("scale_start_amt").equals(""))
				scale_start_amt = Double.valueOf(objServiceArgs.get("scale_start_amt").toString());

			if (objServiceArgs.get("GradePayExcell") != null && !objServiceArgs.get("GradePayExcell").equals(""))
				GradePayExcell = Double.valueOf(objServiceArgs.get("GradePayExcell").toString());
			logger.info("::::::::::::::::::::::::::::::::::::::::  Finally the value of scale_start_amt =  " + scale_start_amt );
			logger.info("::::::::::::::::::::::::::::::::::::::::  Finally the value of GradePayExcell =  " + GradePayExcell );


			//long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			/*long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMst orgUserMst = null;
			if(loginMap.get("loggedInUserMst")!=null)
			{
				orgUserMst = (OrgUserMst)loginMap.get("loggedInUserMst");
			}
			else
			{
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				orgUserMst =orgUserMstDaoImpl.read(userId);
				loginMap.put("loggedInUserMst",orgUserMst);
			}

			//long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
			/*long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);*/
			long dbId=StringUtility.convertToLong(loginMap.get("dbId").toString());
			CmnDatabaseMst cmnDatabaseMst = null;
			if(loginMap.get("loggedInDBMst")!=null)
			{
				cmnDatabaseMst = (CmnDatabaseMst)loginMap.get("loggedInDBMst");
			}
			else
			{
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
				loginMap.put("loggedInDBMst",cmnDatabaseMst);
			}

			//long locId=StringUtility.convertToLong(loginMap.get("locationId").toString());
			long locId=StringUtility.convertToLong(loginMap.get("locationId").toString());
			/*CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);*/
			CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");

			//long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			/*long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);*/

			//long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			/*long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);*/
			long postId=StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMst orgPostMst = null;
			if(loginMap.get("primaryPostMst")!=null)
			{
				orgPostMst = (OrgPostMst)loginMap.get("primaryPostMst");
			}
			else
			{
				OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				orgPostMst = orgPostMstDaoImpl.read(postId);
				loginMap.put("primaryPostMst",orgPostMst);
			}

			Date sysdate = new Date();


			//List<HrEisOtherDtls> otherList =otherdtlsDao.getAllOtherData();


			HrEisOtherDtls otherDtlsVO= (HrEisOtherDtls)objServiceArgs.get("empOtherVO");
			CommAllOtherDetailsVO commonOtherData = (CommAllOtherDetailsVO)objServiceArgs.get("commonOtherData");

			//ExpressionMasterDAOImpl expDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
			//	List<HrPayExpressionMst> expList = new ArrayList();

			String editMode = objServiceArgs.get("editMode").toString(); 
			//HrEisEmpMst newHrEisEmpMst = otherDtlsVO.getHrEisEmpMst();
			HrEisEmpMst newHrEisEmpMst = commonOtherData.getHrEisEmpMst();
			//OrgEmpMst newOrgEmpMst = newHrEisEmpMst.getOrgEmpMst();
			OrgEmpMst newOrgEmpMst = commonOtherData.getOrgEmpMst();
			long employeeId = newHrEisEmpMst.getEmpId();
			long empCurrentStatus=1;
			long empTypeId = newHrEisEmpMst.getEmpType();

			HrEisScaleMst scaleMst=null;
			//HrEisSgdMpg sgdMap = otherDtlsVO.getHrEisSgdMpg()!=null?otherDtlsVO.getHrEisSgdMpg():null;
			String salaryRuleClassPath="com.tcs.sgv.allowance.service.SalaryRules";		// Default for Fix-Pay and Contrctual Employees.
			long payCommissionId = 2500340;				// Default Pay-Commission Id.
			/*if(sgdMap!=null){
				scaleMst = sgdMap.getHrEisScaleMst();
				//for 6th pay commission
				HrPayCommissionMst payCommMst = scaleMst.getHrPayCommissionMst();
				payCommissionId = payCommMst.getId();
				salaryRuleClassPath = payCommMst.getRuleEngineClassPath();
			}*/
			
			if(commonOtherData.getHrPayCommissionMst()!=null)
			{
				payCommissionId = commonOtherData.getHrPayCommissionMst().getId();
				salaryRuleClassPath = commonOtherData.getHrPayCommissionMst().getRuleEngineClassPath();
			}

			logger.info("The salary rule class path is "+salaryRuleClassPath);
			//Class salaryRuleClass = 


			

			//Class salRules = Class.forName(salaryRuleClassPath);
			//Constructor constructor =	salRules.getConstructor(null);
			//Object invoker =constructor.newInstance(null);

			//SalaryRules_6thPay salRules = new SalaryRules_6thPay();


			logger.info("*****************************************payCommissionId "+payCommissionId);
			//below code is commented by manish to remove expression master
			/*	if(allowId.equals(""))
				expList=expDAO.getExprData(payCommissionId);
			    else if(allowId.length()>0)
				{	
					expList=expDAO.getExpfromAllowCodes(allowId,payCommissionId);
				} 
			 */
			//EmpInfoDAOImpl empInfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());   

			//ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(com.tcs.sgv.allowance.valueobject.HrPayComponentMst.class,serv.getSessionFactory());
			//List<com.tcs.sgv.allowance.valueobject.HrPayComponentMst> componentList =componentDAO.getListByColumnAndValue("componentDesc", "DP");

			EmpAllwMapDAOImpl empAllwMapDao = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());

			DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			HrPayDeductionDtls empDeducDtls = null;	

			List<HrPayDeductionDtls> deducList= null;


			//Added By Urvin Shah
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			int contractEmpType = Integer.parseInt(resourceBundle.getString("contract"));
			int fixedEmpType = Integer.parseInt(resourceBundle.getString("fixed"));
			//End
			//	To add Basic value in the totalSalary. Added By Urvin shah.
			totalSalary+=otherDtlsVO.getotherCurrentBasic();
			//	End By Urvin shah.

			//manish started
		
			/*List gisList = changeGISDetailsDAOImpl.getEmpData(newHrEisEmpMst.getOrgEmpMst().getEmpId());
			if(gisList!= null && gisList.size()>0)
				hrEisGISDtls=(HrEisGISDtls)gisList.get(0);*/
			hrEisGISDtls = commonOtherData.getHrEisGISDtls();
			
			GenerateBillServiceCoreLogic generateBillServiceCoreLogic=new GenerateBillServiceCoreLogic();
			GenerateBillService  generateBillService =new GenerateBillService();
			Map inputMap=new HashMap();
			Date sysDate=new Date();
			int daysOfPostNew=0;
			int month=sysDate.getMonth()+1;;

			int year=sysDate.getYear()+1900;
			Set orguserPostrlt=otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();
			Map dayOfPostMap=generateBillService.checkMaxDayOfPostRecord(orguserPostrlt, month, year);
			daysOfPostNew=(int)Long.parseLong(dayOfPostMap.get("maxDaysOfPost").toString());
			//added by manish 
			/*for (Iterator upIt = orguserPostrlt.iterator(); upIt.hasNext();) {
				OrgUserpostRlt orgUPRltMaxDays = (OrgUserpostRlt) upIt.next();
				if(orgUPRltMaxDays.getActivateFlag() == 1)
					postIdsStr= orgUPRltMaxDays.getOrgPostMstByPostId().getPostId();
			}*/
			
			postIdsStr = commonOtherData.getPostId();
			 
			Map empOfficeMap = new HashMap();
			HrPayOfficePostMpgDAOImpl hrPayOfficePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class,serv.getSessionFactory());
			List officePostList = hrPayOfficePostMpgDAOImpl.getOfficeClass(String.valueOf(postIdsStr));
			String OfficeCityClass = "";
			for(int officeCnt=0;officeCnt<officePostList.size();officeCnt++){
				Object[] row = (Object[])officePostList.get(officeCnt);
				long officePostId = row[0]!=null?Long.valueOf(row[0].toString()):0;
				OfficeCityClass  = row[1]!=null?String.valueOf(row[1].toString()):"";			
				
				if(!empOfficeMap.containsKey(officePostId)) {
					empOfficeMap.put(officePostId, OfficeCityClass);
				}
			}
			
			Long billNo = null;
			if(objServiceArgs.get("billGroupId") != null)
			{
				billNo = (Long)objServiceArgs.get("billGroupId");
			}
			else
			{
				billNo=otherdtlsDao.getBillNo(employeeId);
			}
			
			if(billNo != null){
				List lLst = otherdtlsDao.getEmpForSalaryConfig(locId, billNo, new Date(), employeeId);
				logger.info("Size of List is"+lLst.size());
				if(lLst != null && lLst.size() >0){
					Object[] row = (Object[])lLst.get(0);
					if(row!=null) {
						orgEmpId = row[0]!=null ? Long.valueOf(row[0].toString()):0;
						eisEmpId = row[1]!=null ? Long.valueOf(row[1].toString()):0;
						basicAmt = row[2]!=null ? Long.valueOf(row[2].toString()):0;
						incomeTax = row[3]!=null ? Long.valueOf(row[3].toString()):0;
						gradeId = row[4]!=null ? Long.valueOf(row[4].toString()):0; 
						desigId = row[5]!=null ? Long.valueOf(row[5].toString()):0;
						scaleId = row[6]!=null ? Long.valueOf(row[6].toString()):0;
						scaleStartAmt = row[7]!=null ? Long.valueOf(row[7].toString()):0;
						scaleEndAmt = row[8]!=null ? Long.valueOf(row[8].toString()):0;
						dcpsOrGPF = row[9]!=null ? row[9].toString():"";
						/*salPostId = row[10]!=null ? Long.valueOf(row[10].toString()):0;
						salUserId = row[11]!=null ? Long.valueOf(row[11].toString()):0;*/
						salPayCommissionId = row[12]!=null ? Long.valueOf(row[12].toString()):0;
						salEmpType = row[13]!=null ? Long.valueOf(row[13].toString()):0;
						gradeCode = row[14]!=null ? Long.valueOf(row[14].toString()):0;
						isAvailedHRA = row[15]!=null ? Integer.valueOf(row[15].toString()):0;
						empDOB = row[16]!=null ? (Date)row[16]:null;
						empSrvcExp = row[17]!=null ? (Date)row[17]:null;
						empDOJ = row[18]!=null ? (Date)row[18]:null;
						isPhyHandicapped = row[19]!=null ? row[19].toString():"";
						logger.info("isPhyHandicapped is " + isPhyHandicapped);
						gradePay = row[20]!=null ? Long.valueOf(row[20].toString()):0;
						empCity =  row[21]!=null ? Long.valueOf(row[21].toString()):0;
						logger.info("city is "+empCity);
						//qtrRentAmt =  row[22]!=null ? Long.valueOf(row[22].toString()):0;
						postPSRNo =  row[22]!=null ? Long.valueOf(row[22].toString()):0;
						otherDtlsId =  row[23]!=null ? Long.valueOf(row[23].toString()):0;
						
						if(empOfficeMap.containsKey(postIdsStr) || empOfficeMap.containsKey(""+postIdsStr) ){
							empPaybill.setPostCityClass(empOfficeMap.get(postIdsStr).toString());
						}
						
						if(orgEmpId!=0 && eisEmpId!=0 && desigId!=0 && gradeId!=0 && scaleId!=0 && postId!=0 && userId!=0) {
						   empPaybill.setOrgEmpId(orgEmpId);
						   empPaybill.setEisEmpId(eisEmpId);
						   empPaybill.setBasicAmt(basicAmt);
						   empPaybill.setDesigId(desigId);
						   empPaybill.setGradeId(gradeId);
						   empPaybill.setScaleId(scaleId);
						   empPaybill.setScaleStartAmt(scaleStartAmt);
						   empPaybill.setScaleEndAmt(scaleEndAmt);
						   empPaybill.setDcpsOrGPF(dcpsOrGPF);
						   empPaybill.setPostId(postId);
						   empPaybill.setIncomeTax(incomeTax);
						   empPaybill.setUserId(userId);
						   empPaybill.setPayCommissionId(salPayCommissionId);
						   empPaybill.setEmpType(salEmpType);
						   empPaybill.setGradeCode(gradeCode);
						   empPaybill.setIsAvailedHRA(isAvailedHRA);
						   logger.info("IsAvailed HRA Fetched is"+isAvailedHRA);
						   empPaybill.setEmpDOB(empDOB);
						   empPaybill.setEmpDOJ(empDOJ);
						   empPaybill.setEmpSrvcExp(empSrvcExp);
						   empPaybill.setIsPhyHandicapped(isPhyHandicapped);
						   empPaybill.setGradePay(gradePay);
						   empPaybill.setEmpCity(empCity);
						   empPaybill.setQtrRentAmt(qtrRentAmt);
						   empPaybill.setPostPSRNo(postPSRNo);
						   empPaybill.setOtherDtlsId(otherDtlsId);
						   empPaybill.setPostCityClass(OfficeCityClass);
						   //added by manish for Gis Change
						   if(hrEisGISDtls != null){
							   empPaybill.setGisGradeId(hrEisGISDtls.getOrgGradeMst().getGradeId());
							   empPaybill.setGisGradeCode(Long.valueOf(hrEisGISDtls.getOrgGradeMst().getGradeCode()));
							   logger.info("Gis gradeId is "+hrEisGISDtls.getOrgGradeMst().getGradeId() +" Gis Grade Code is "+Long.valueOf(hrEisGISDtls.getOrgGradeMst().getGradeCode()));
						}
							//ended by manish    
						   					   
						}}
			}}
			else
				logger.info("Bill No is null");
			//ended by manish 
			inputMap.put("serviceLocator", serv);
					//inputMap.put("hrEisOtherDtls",otherDtlsVO);
			inputMap.put("hrEisOtherDtls",empPaybill);
			inputMap.put("monthGiven", month);
			inputMap.put("yearGiven", year);
			inputMap.put("daysOfPost", daysOfPostNew);

			logger.info("serviceLocator"+serv);
			logger.info("hrEisOtherDtls"+otherDtlsVO);
			logger.info("monthGiven"+month);
			logger.info("yearGiven"+year);
			logger.info("daysOfPost"+daysOfPostNew);


			Map genericMap= generateBillServiceCoreLogic.generatePassMap(inputMap);
			logger.info("gpValue=======>1112");

			if(payCommissionId==fifthPayCommLookupId || payCommissionId == commissionSevenId)
			{
				dpValue = Double.parseDouble(genericMap.get("DP").toString());				
				logger.info("the dpValue is "+dpValue);
			}
			else
				dpValue=0; 
			/*if(sgdMap!=null)
				gpValue =sgdMap.getHrEisScaleMst().getScaleGradePay();*/
			logger.info("gpValue=======>233");
			gpValue = commonOtherData.getHrEisScaleMst().getScaleGradePay();
			logger.info("gpValue=======>"+gpValue);
			parResult=0;

			EmpAllwMapDAOImpl empAllwMapDAOImpl =new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			long payCommissionIdToBePassed = 0;
			if(payCommissionId == sixthPayCommLookupId || payCommissionId == commissionFourId || payCommissionId == commissionThreeId || payCommissionId == commissionTwoId || payCommissionId == commissionOneId)
				payCommissionIdToBePassed = sixthPayCommLookupId;
			else
				payCommissionIdToBePassed = fifthPayCommLookupId;
			//List<HrPayAllowTypeMst> allowTypeMst=empAllwMapDAOImpl.getAllAllowanceTypeMstForAllowTypeMst(payCommissionIdToBePassed,locId,otherDtlsVO.getHrEisEmpMst().getEmpId());
			List allowTypeMst = empAllwMapDAOImpl.getAllAllowanceDetailsForAllowTypeMst(payCommissionIdToBePassed,locId,otherDtlsVO.getHrEisEmpMst().getEmpId());
			SalaryRules salaryRules=new SalaryRules();
			SalaryRules_6thPay salRules6th = new SalaryRules_6thPay();

			/*PayrollCommonDAO payrollCommonDAO =new PayrollCommonDAO(serv.getSessionFactory());
			Map allowEdpMap =payrollCommonDAO.getEdpAllwMap(payCommissionId, locId);*/



			String edpCode=null;
			boolean hraAvailFlag = false;
			for(int i=0;i<allowTypeMst.size();i++)
			{
				parResult=-1;
				HrPayEmpallowMpg empAllwMpg = new HrPayEmpallowMpg();
				//HrPayEmpallowMpg empAllwMpgMst = new HrPayEmpallowMpg();
				//HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
				HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
				long allowCode = 0;
				CommonAllowanceDetailsVO allowDetailsVO = (CommonAllowanceDetailsVO)allowTypeMst.get(i);
				allowCode=allowDetailsVO.getAllowCode();
				logger.info("allow code is "+allowCode);
				
				if(allowCode==hraId) hraAvailFlag = true;
				/*if(allowEdpMap.get(""+allowCode)!=null)
					edpCode=allowEdpMap.get(""+allowCode).toString();*/
				edpCode = allowDetailsVO.getEdpCode();
				logger.info("allwedpcode is "+ edpCode);
				boolean flag  = false,flagMst=false;
				//flag  = empAllwMapDao.checkHrPayEmpallowMpg(employeeId, allowCode,month,year);
				//flagMst  = empAllwMapDao.checkHrPayEmpallowMpg(employeeId, allowCode,-1,-1);

				String methodName="calculate"+allowDetailsVO.getAllowDesc();
				logger.info("Method name in empallmapsrvc "+methodName);
				Class class1;
				if(payCommissionId == sixthPayCommLookupId || payCommissionId == commissionFourId || payCommissionId == commissionThreeId || payCommissionId == commissionTwoId || payCommissionId == commissionOneId)
				{
					class1 = salRules6th.getClass();
				}
				else
				{
					class1=salaryRules.getClass();
				}
				Method method=null;
				try
				{
					method=class1.getMethod(methodName,Map.class);
				}
				catch(Exception e)
				{
					logger.info("No Rule found for "+allowDetailsVO.getAllowDesc());
				}
				if(method!=null)
				{
					if(payCommissionId == sixthPayCommLookupId || payCommissionId == commissionFourId || payCommissionId == commissionThreeId || payCommissionId == commissionTwoId || payCommissionId == commissionOneId)
						parResult=(Double)method.invoke(salRules6th, genericMap);
					else
						parResult=(Double)method.invoke(salaryRules, genericMap);

					logger.info("Result is "+parResult+ " for allow code "+allowCode +" and edp code "+edpCode+ " method name is "+methodName);
					logger.info("Result is "+parResult + "for allow code"+allowCode);
					parResult= Math.round(parResult);
					//String mthdName ="setAllow"+edpCode;
				}
				else
				{
					logger.info("Method is null "+methodName);
				}


				//empAllwMpgMst  = (HrPayEmpallowMpg)empAllwMapDao.getHrPayEmpallowMpg(empInfoDao.read(employeeId).getOrgEmpMst().getEmpId(), allowCode,-1,-1);
				if(editMode.equalsIgnoreCase("Y") && method!=null) 
				{
					if(allowCode!=0)
					{
						if((payCommissionId == commissionThreeId || payCommissionId == commissionSevenId) && (allowCode == 9 || allowCode == 14)){
							parResult = 0;
							logger.info("HRA and TA 0 condition due to Padmanabhan and Shetty");
						}
						
						//empAllwMpg  = (HrPayEmpallowMpg)empAllwMapDao.getHrPayEmpallowMpg(commonOtherData.getOrgEmpMst().getEmpId(), allowCode,-1,-1);
						logger.info("Now updating allowances with update sql query");
						int updateCount = empAllwMapDao.updateEmpAllowResult(parResult,orgPostMst.getPostId(),orgUserMst.getUserId(),sysdate,allowCode,commonOtherData.getHrEisEmpMst().getEmpId(),-1,-1);
						/*if(empAllwMpg!=null)
						{
							empAllwMpg  = (HrPayEmpallowMpg)empAllwMapDao.getHrPayEmpallowMpg(empInfoDao.read(employeeId).getOrgEmpMst().getEmpId(), allowCode,-1,-1);
							logger.info("setting allowance value for allowance ::"+allowCode+" and value is "+parResult +" in if ");
							empAllwMpg.setEmpAllowAmount(parResult); //NEED TO BE CHANGED.
							empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
							empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
							empAllwMpg.setUpdatedDate(sysdate);				
							empAllwMapDao.update(empAllwMpg);
						}*/
						//else
						if(updateCount < 1)
						{
							logger.info("setting allowance value for allowance ::"+allowCode+" and value is "+parResult +" in else ");
							empAllwMpg=new HrPayEmpallowMpg();
							IdGenerator idGen = new IdGenerator();

							logger.info("in allownace loop new insertion else part :: allowCode"+allowDetailsVO.getAllowCode()+" emp id is "+newHrEisEmpMst.getEmpId()+" month is "+ -1 +" year is "+-1);
							Long id= idGen.PKGenerator("HR_PAY_EMPALLOW_MPG",objServiceArgs);			
							//Long id= IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG",objServiceArgs);

							logger.info("****************************the id generated is "+id);
							empAllwMpg.setAllowCode(id);
							//hrEisEmpMst.setEmpId(newHrEisEmpMst.getEmpId());
							logger.info("The allowance code after the setting is"+ allowCode);
							hrPayAllowTypeMst.setAllowCode(allowCode);
							empAllwMpg.setMonth(-1);
							empAllwMpg.setYear(-1);
							empAllwMpg.setEmpAllowAmount(parResult); //NEED TO BE CHANGED.
							empAllwMpg.setHrEisEmpMst(newHrEisEmpMst);
							empAllwMpg.setHrPayAllowTypeMst(hrPayAllowTypeMst);
							empAllwMpg.setEmpCurrentStatus(1);
							empAllwMpg.setCreatedDate(sysdate);						
							empAllwMpg.setCmnDatabaseMst(cmnDatabaseMst);
							empAllwMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
							empAllwMpg.setOrgPostMstByCreatedByPost(orgPostMst);
							empAllwMpg.setCmnLocationMst(cmnLocationMst);		
							empAllwMpg.setOrgUserMstByCreatedBy(orgUserMst);
							empAllwMpg.setOrgUserMstByUpdatedBy(orgUserMst);
							empAllwMpg.setUpdatedDate(sysdate);		
							empAllwMpg.setTrnCounter(new Integer(1));
							empAllwMapDao.create(empAllwMpg);
						}




					}
				}

			}


			//this block causes problem......

			//added by Ankit Bhatt - For inserting 0 values for Allowances which have no Expression

			/*HrEisEmpMst hrEisEmpVo = new HrEisEmpMst();




			if(payCommissionId==fifthPayCommLookupId && sgdMap!=null)
			{
				logger.info("in fifth commision ta rule");
				//SalaryRules_6thPay salaryRules_6thPay=new SalaryRules_6thPay();
				hrEisEmpVo = otherDtlsVO.getHrEisEmpMst();
				double salary=otherDtlsVO.getotherCurrentBasic();
				logger.info("salary=======>"+salary);
				long city=otherDtlsVO.getCity();
				boolean phyHandicapped=Boolean.getBoolean(otherDtlsVO.getPhyChallenged());
				long empType=hrEisEmpVo.getEmpType();
				long distance=0;
				boolean isVehAvailed=false;
				// get sixth scale for grade pay
				ScaleCommissionMpg scaleCommissionMpgVO=new ScaleCommissionMpg();
				ScaleCommissionMpgDAO scaleCommissionMpgDAOImpl=new ScaleCommissionMpgDAOImpl(ScaleCommissionMpg.class,serv.getSessionFactory());
				scaleCommissionMpgVO=scaleCommissionMpgDAOImpl.getNewScaleFromOldScale(otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleId());
				logger.info("otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleId()======>"+otherDtlsVO.getHrEisSgdMpg().getHrEisScaleMst().getScaleId());
				HrEisScaleMst hrEisScaleMstVO=new HrEisScaleMst();
				GenericDaoHibernateImpl genericDAO = new GenericDaoHibernateImpl(HrEisScaleMst.class);
				genericDAO.setSessionFactory(serv.getSessionFactory());
				hrEisScaleMstVO=(HrEisScaleMst) genericDAO.read(scaleCommissionMpgVO.getCommissionSix().getScaleId());
				logger.info("scaleCommissionMpgVO.getCommissionSix()=======>"+scaleCommissionMpgVO.getCommissionSix());

				//long gradePay=hrEisScaleMstVO.getScaleGradePay();
				//logger.info("grade pay=====>"+gradePay);
				//ended for six pay and grade pay

				//for distance vehicle availed
				HrEmpTraMpg hrEmpTraMpg = new HrEmpTraMpg();
				HrEmpTraMpgDaoImpl hrEmpTraMpgDao = new HrEmpTraMpgDaoImpl(HrEmpTraMpg.class,serv.getSessionFactory());
				List vehicalAvailedList = new ArrayList();
				vehicalAvailedList= hrEmpTraMpgDao.getHrEmpTraData(hrEisEmpVo.getEmpId());
				for(int count=0;count<vehicalAvailedList.size();count++)
				{
					hrEmpTraMpg =(HrEmpTraMpg)vehicalAvailedList.get(count);

					distance=hrEmpTraMpg.getDistance();
					isVehAvailed=Boolean.getBoolean(hrEmpTraMpg.getVehicalAvailed().toLowerCase());
				}
				logger.info("distance=====>"+distance);
				logger.info("isVehAvailed=====>"+isVehAvailed);

				//ended for distance and is aviled

				double ta=salaryRules.calculateTA(genericMap);
				logger.info("ta=====>"+ta);
				double cla=salaryRules.calculateCLA(genericMap);
				logger.info("ta=====>"+ta +"cla is====>"+cla);

				EmpAllwMapDAOImpl empAllwMapDaoobj = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				MaintainHistoryDaoImpl mec=new MaintainHistoryDaoImpl(HrPayEmpallowMpgHst.class,serv.getSessionFactory());

				long hrEisempId = hrEisEmpVo.getOrgEmpMst().getEmpId();
				HrPayEmpallowMpg empAllowMapobj=new HrPayEmpallowMpg();

			}
*/



			// Start By Urvin shah. Update the value of the totalSalary of the HrEisOtherDetails.
			logger.info("The Value of the total salary is:-"+totalSalary);
			logger.info("The Value of the total salary is:-"+Math.round(totalSalary));
			otherDtlsVO.setTotalSalary(Math.round(totalSalary));
			otherdtlsDao.update(otherDtlsVO);		// Updating the Other Detials' object to update the value of the totalSalary

			DeductionDtlsDAOImpl deductionDaoImpl=new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			payCommissionIdToBePassed = 0;
			if(payCommissionId == sixthPayCommLookupId || payCommissionId == commissionFourId || payCommissionId == commissionThreeId || payCommissionId == commissionTwoId || payCommissionId == commissionOneId)
				payCommissionIdToBePassed = sixthPayCommLookupId;
			else
				payCommissionIdToBePassed = fifthPayCommLookupId;
			List<HrPayDeducTypeMst> deducTypeMst=deductionDaoImpl.getDeductionTypeDeducDtlsMst(payCommissionIdToBePassed,locId,otherDtlsVO.getHrEisEmpMst().getEmpId());

			//salRules6thlogger.info("Manual calculation of PT"+salaryRules.calculatePT(genericMap)+"and for sith pay commission "+salRules6th.calculatePT(genericMap));
			logger.info("map value is "+genericMap); 
			boolean flag =false;
			logger.info("  testing... size is "+ deducTypeMst.size());
			boolean dcpsIdFlag = false,dcpsDaIdFlag = false,dcpsPayIdFlag = false,dcpsDelayIdFlag = false;
			boolean hrrAvailFlag = false;
			for(int i=0;i<deducTypeMst.size();i++)
			{
				//HrPayEmpallowMpg empAllwMpg = new HrPayEmpallowMpg();
				//HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
				//	HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
				HrPayDeducTypeMst hrPayDeducTypeMst=new HrPayDeducTypeMst();
				long deducCode = 0;
				deducCode=deducTypeMst.get(i).getDeducCode();
				logger.info("deducCode code is "+deducCode);
				
				
				if(dcpsId==deducCode) dcpsIdFlag = true;
				if(dcpsDaId==deducCode) dcpsDaIdFlag = true;
				if(dcpsPayId==deducCode) dcpsPayIdFlag = true;
				if(dcpsDelayId==deducCode) dcpsDelayIdFlag = true;
				if(hrrId==deducCode) hrrAvailFlag = true;
				
				logger.info("  testing ... deduc code "+deducCode+" deduc description  "+deducTypeMst.get(i).getDeducDisplayName()+" method is "+"calculate"+((HrPayDeducTypeMst)deducTypeMst.get(i)).getDeducDesc() );
				parResult=0;

				//flag  = empDuducDtlsDAO.checkHrPayDeductionDtls(employeeId, deducCode,-1,-1);

				logger.info("  testing..  flag is "+flag);
				/*boolean flag  = false;
				flag  = empAllwMapDao.checkHrPayEmpallowMpg(employeeId, allowCode);*/
				String methodName="calculate"+((HrPayDeducTypeMst)deducTypeMst.get(i)).getDeducDesc();
				logger.info("Method name in empallmapsrvc for Deduction is "+methodName);
				Class class1 = null;

				if(payCommissionId == sixthPayCommLookupId || payCommissionId == commissionFourId || payCommissionId == commissionThreeId || payCommissionId == commissionTwoId || payCommissionId == commissionOneId)
					class1=salRules6th.getClass();
				else
					class1=salaryRules.getClass();


				Method method=null;
				try{
					method=class1.getMethod(methodName,Map.class);
				}
				catch(Exception e)
				{
					logger.info("No Rule found for "+((HrPayDeducTypeMst)deducTypeMst.get(i)).getDeducDesc());
					logger.info("  testing..... rule not found ...");
				}
				if(method!=null)
				{
					if(payCommissionId == sixthPayCommLookupId || payCommissionId == commissionFourId || payCommissionId == commissionThreeId || payCommissionId == commissionTwoId || payCommissionId == commissionOneId)
						parResult=(Double)method.invoke(salRules6th, genericMap);
					else
						parResult=(Double)method.invoke(salaryRules, genericMap);
					logger.info("Result is "+parResult+ "for deducCode code"+deducCode);
					logger.info("Result is "+parResult + "for deducCode code"+deducCode);
					logger.info( "  testing.. Result is "+parResult +"....for  deducCode code "+ deducCode);

					parResult = Math.round(parResult);
				}
				else
				{
					logger.info("Method is null "+methodName);
				}


				logger.info(" editMode is "+editMode);
				if(editMode.equalsIgnoreCase("Y") && method != null)
				{



					/*if(flag)
					{

						deducList= empDuducDtlsDAO.getHrPayDeductionDtls(empInfoDao.read(employeeId).getEmpId(),empCurrentStatus,deducCode,-1,-1);
						if(deducList != null && deducList.size()>0)
							empDeducDtls=deducList.get(0);
						//logger.info("master data found ...........updating...");
						logger.info("master data deduc list is "+deducCode + " empDeducDtls "+empDeducDtls.getHrPayDeducTypeMst().getDeducName() + " value is "+ empDeducDtls.getEmpDeducAmount()+" employee id is "+employeeId);
						empDeducDtls.setEmpDeducAmount(parResult);
						empDeducDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						empDeducDtls.setOrgUserMstByUpdatedBy(orgUserMst);
						empDeducDtls.setUpdatedDate(sysdate);

						logger.info("after update deduc name "+empDeducDtls.getHrPayDeducTypeMst().getDeducName() + " value is "+ empDeducDtls.getEmpDeducAmount());
						empDuducDtlsDAO.update(empDeducDtls);
						//deducList= empDuducDtlsDAO.getHrPayDeductionDtls(empInfoDao.read(employeeId).getEmpId(),empCurrentStatus,deducCode,-1,-1);
						//logger.info("Amount from Db is "+deducList.get(0).getEmpDeducAmount());

						msg=1;
					}*/
					msg=1;
					int updateCount = empDuducDtlsDAO.updateHrPayDeductionDtls(parResult, orgPostMst.getPostId(), orgUserMst.getUserId(), sysdate, deducCode, commonOtherData.getHrEisEmpMst().getEmpId(), -1, -1);
					//else
					if(updateCount < 1)
					{
						logger.info("Master data not found ...inserting values...");
						empDeducDtls=new HrPayDeductionDtls();
						IdGenerator idGen = new IdGenerator();
						Long deducid =idGen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objServiceArgs);			
						//Long deducid =IDGenerateDelegate.getNextId("hr_pay_deduction_dtls", objServiceArgs);
						logger.info("****************************the id generated is "+deducid);
						//empAllwMpg.setAllowCode(deducid);     		
						empDeducDtls.setDeducDtlsCode(deducid);     		

						empDeducDtls.setCreatedDate(sysdate);						
						empDeducDtls.setCmnDatabaseMst(cmnDatabaseMst);
						empDeducDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
						empDeducDtls.setUpdatedDate(sysdate);
						empDeducDtls.setOrgPostMstByCreatedByPost(orgPostMst);
						empDeducDtls.setCmnLocationMst(cmnLocationMst);		
						empDeducDtls.setOrgUserMstByCreatedBy(orgUserMst);

						empDeducDtls.setEmpCurrentStatus(1);
						//hrEisEmpMst = new HrEisEmpMst();
						//hrEisEmpMst.setEmpId(newHrEisEmpMst.getEmpId());

						hrPayDeducTypeMst = new HrPayDeducTypeMst();
						hrPayDeducTypeMst.setDeducCode(deducCode);
						empDeducDtls.setMonth(-1);
						empDeducDtls.setYear(-1);
						empDeducDtls.setEmpDeducAmount(parResult);
						empDeducDtls.setHrPayDeducTypeMst(hrPayDeducTypeMst);
						empDeducDtls.setHrEisEmpMst(newHrEisEmpMst);			 			
						empDeducDtls.setOrgUserMstByUpdatedBy(orgUserMst);		
						empDeducDtls.setTrnCounter(new Integer(1));
						empDuducDtlsDAO.create(empDeducDtls);
					}

					//List<HrPayDeductionDtls> deducList1= new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory()).getHrPayDeductionDtls(822,1,35,-1,-1);
					//logger.info("Amount from Db is AFTER ELSE    "+deducList1.get(0).getEmpDeducAmount());
				}


			}


			//List<HrPayDeductionDtls> deducList2= new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory()).getHrPayDeductionDtls(822,1,35,-1,-1);
			//logger.info("Amount from Db after for  "+deducList2.get(0).getEmpDeducAmount());
			EmpCompMpgDAOImpl empCompoMpg = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class,serv.getSessionFactory());
			/*List<HrEisEmpCompMpg> newList = empCompoMpg.getSpecificEmpActiveList(""+dcpsId,dedycType,  locId);

			List<HrEisEmpCompMpg> newList1 = empCompoMpg.getSpecificEmpActiveList(""+dcpsDelayId,dedycType,  locId);
			List<HrEisEmpCompMpg> newList2 = empCompoMpg.getSpecificEmpActiveList(""+dcpsPayId,dedycType,  locId);
			List<HrEisEmpCompMpg> newList3 = empCompoMpg.getSpecificEmpActiveList(""+dcpsDaId,dedycType,  locId);*/

			PayrollCommonDAO commonDAO = new PayrollCommonDAO(serv.getSessionFactory());
			SgvcFinYearMst sgvcFinYearMst = commonDAO.getFinYrInfo(new Date(),langId);

			long empId=commonOtherData.getOrgEmpMst().getEmpId();
			logger.info("finyr id is"+sgvcFinYearMst.getFinYearId()+" month id is "+month+" empId is "+empId);
			//if(newList!=null && newList.size()>0)
			Map dcpsMap = empCompoMpg.getDCPSValues(empId,month,sgvcFinYearMst.getFinYearId());
			if(dcpsIdFlag)
			{
				logger.info("DCPS is mapped....");
				/*List<HrPayDeductionDtls> deduclstDcps = empDuducDtlsDAO.getDeductionDtls(empId, dcpsId,-1,-1);
				logger.info("deduclstDcps:::"+deduclstDcps.size());
				if(deduclstDcps!=null && deduclstDcps.size()>0)
				{
					logger.info("DCPS is mapped....Master Data found ....");
					HrPayDeductionDtls HrPayDe = deduclstDcps.get(0);
					//double amt = empCompoMpg.getDCPSValue(  empId,  month,  sgvcFinYearMst.getFinYearId());
					//double amt1 = empCompoMpg.getDCPSValue1(  empId,  month,  sgvcFinYearMst.getFinYearId());
					double amt1 = (Double)dcpsMap.get("");
					logger.info("amt1:::"+amt1);
					HrPayDe.setEmpDeducAmount(amt1);
					empDuducDtlsDAO.update(HrPayDe);
				}*/
				if(dcpsMap!=null && dcpsMap.get("700046")!=null)
				{
					double amt1 = (Double)dcpsMap.get("700046");
					empDuducDtlsDAO.updateHrPayDeductionDtls(amt1, orgPostMst.getPostId(), orgUserMst.getUserId(), sysdate, dcpsId, commonOtherData.getHrEisEmpMst().getEmpId(), -1, -1);
				}

			}
			//if(newList1!=null && newList1.size()>0)
			if(dcpsDelayIdFlag)
			{
				logger.info("DCPS is mapped....");
				/*List<HrPayDeductionDtls> deduclstDcps = empDuducDtlsDAO.getDeductionDtls(empId,dcpsDelayId ,-1,-1);
				if(deduclstDcps!=null && deduclstDcps.size()>0)
				{
					logger.info("DCPS is mapped....Master Data found ....");
					HrPayDeductionDtls HrPayDe = deduclstDcps.get(0);

					double amt2 = (Double)dcpsMap.get("");;//empCompoMpg.getDCPSValue2(  empId,  month,  sgvcFinYearMst.getFinYearId());
					logger.info("amt2:::"+amt2);
					HrPayDe.setEmpDeducAmount(amt2);
					empDuducDtlsDAO.update(HrPayDe);
				}*/
				if(dcpsMap!=null && dcpsMap.get("700047")!=null)
				{
					double amt2 = (Double)dcpsMap.get("700047");
					empDuducDtlsDAO.updateHrPayDeductionDtls(amt2, orgPostMst.getPostId(), orgUserMst.getUserId(), sysdate, dcpsId, commonOtherData.getHrEisEmpMst().getEmpId(), -1, -1);
				}

			}
			//if(newList2!=null && newList2.size()>0)
			if(dcpsPayIdFlag)
			{
				logger.info("DCPS is mapped....");
				/*List<HrPayDeductionDtls> deduclstDcps = empDuducDtlsDAO.getDeductionDtls(empId, dcpsPayId,-1,-1);
				if(deduclstDcps!=null && deduclstDcps.size()>0)
				{
					logger.info("DCPS is mapped....Master Data found ....");
					HrPayDeductionDtls HrPayDe = deduclstDcps.get(0);

					double amt4 = (Double)dcpsMap.get("");//empCompoMpg.getDCPSValue3(  empId,  month,  sgvcFinYearMst.getFinYearId());
					HrPayDe.setEmpDeducAmount(amt4);
					logger.info("amt4:::"+amt4);
					empDuducDtlsDAO.update(HrPayDe);
				}*/
				
				if(dcpsMap!=null && dcpsMap.get("700049")!=null)
				{
					double amt4 = (Double)dcpsMap.get("700049");
					empDuducDtlsDAO.updateHrPayDeductionDtls(amt4, orgPostMst.getPostId(), orgUserMst.getUserId(), sysdate, dcpsId, commonOtherData.getHrEisEmpMst().getEmpId(), -1, -1);
				}

			}
			//if(newList3!=null && newList3.size()>0)
			if(dcpsDaIdFlag)
			{
				logger.info("DCPS is mapped....");
				/*List<HrPayDeductionDtls> deduclstDcps = empDuducDtlsDAO.getDeductionDtls(empId, dcpsDaId,-1,-1);
				if(deduclstDcps!=null && deduclstDcps.size()>0)
				{
					logger.info("DCPS is mapped....Master Data found ....");
					HrPayDeductionDtls HrPayDe = deduclstDcps.get(0);

					double amt3 = (Double)dcpsMap.get("");//empCompoMpg.getDCPSValue4(  empId,  month,  sgvcFinYearMst.getFinYearId());
					HrPayDe.setEmpDeducAmount(amt3);
					logger.info("amt3:::"+amt3);
					empDuducDtlsDAO.update(HrPayDe);
				}*/
				
				if(dcpsMap!=null && dcpsMap.get("700048")!=null)
				{
					double amt3 = (Double)dcpsMap.get("700048");
					empDuducDtlsDAO.updateHrPayDeductionDtls(amt3, orgPostMst.getPostId(), orgUserMst.getUserId(), sysdate, dcpsId, commonOtherData.getHrEisEmpMst().getEmpId(), -1, -1);
				}

			}

			/*else
			{
				logger.info("DCPS is NOT mapped....for this employee");
			}*/




			RevisedHrrAndHra revisedHrrAndHra = new RevisedHrrAndHra();
			//Calendar calGiven = Calendar.getInstance();
			//Date givenDate = calGiven.getTime();
			/*int monthGiven=revisedHrrAndHra.monthofDate(givenDate);
			int yearGiven=revisedHrrAndHra.yearofDate(givenDate);
			int maxDay = calGiven.getActualMaximum(5);*/

			HrPayEmpallowMpg empAllowMpgVo  = new HrPayEmpallowMpg();
			if((allowId.equals("") && deducId.equals(""))|| allowId.indexOf(hraId+"")>0|| deducId.indexOf(hrrId+"")>0)
			{

				logger.info("in HRR AND HRA SETTING CODE>>>>");


				if(empTypeId!=contractEmpType && empTypeId!=fixedEmpType)
				{

					logger.info("Employee is not contract ..not fixed pay...>>>>");

					long userIdForQtr = newOrgEmpMst.getOrgUserMst().getUserId();

					genericMap.put("userIdForQtr",userIdForQtr);

					//Calendar calc = Calendar.getInstance();
					// by khushal for removal default comp
					HrPayEmpallowMpg empAllwMpg = (HrPayEmpallowMpg)empAllwMapDao.getHrPayEmpallowMpg(newOrgEmpMst.getEmpId(), 9,-1,-1);//for HRA
					boolean hrrFlag = empDuducDtlsDAO.checkHrPayDeductionDtls(employeeId, 28, -1, -1);
					logger.info("empId is for calculateHrrAndHra::"+commonOtherData.getOrgEmpMst().getEmpId());
					logger.info("hrrFlag::::"+hrrFlag);
					
					if(empAllwMpg!= null || hrrFlag == true)  {
						//ended here
						//Map hrrAndHraMap = revisedHrrAndHra.calculateHrrAndHra(genericMap);
						Map hrrAndHraMap = genericMap;

				/*		List quaterIdList = (List)hrrAndHraMap.get("quaterIdList");
						List quaterDaysList = (List)hrrAndHraMap.get("quaterDaysList");
						List lstQuarterRate = (List)hrrAndHraMap.get("lstQuarterRate");
*/
						hrrAndHraMap.put("otherDtlsVO", empPaybill);
						hrrAndHraMap.put("dpValue", dpValue);
						

						
						
						Map resultHrrAndHra = revisedHrrAndHra.calculateRevisedHraAndHra(hrrAndHraMap);

						//double hrr = empPaybill.getQtrRentAmt();
						//double oldHra=0;
						double oldHra= resultHrrAndHra.get("oldHra") != null?Double.valueOf(resultHrrAndHra.get("oldHra").toString()):0;
						double hrr =resultHrrAndHra.get("qtrRent") != null?Double.valueOf(resultHrrAndHra.get("qtrRent").toString()):0;
						double revisedHra=0;
						//06 jan 2012 changes for vaccant quaters
						
						Map returnMapHRR = empDuducDtlsDAO.getHrrValueForEmp(userIdForQtr,month,year);
						hrr = (Double) returnMapHRR.get("hrr");
						Boolean isAdjustedHRRPartial = (Boolean)returnMapHRR.get("isAdjustedHRRPartial");
						Integer leftDaysHRA = (Integer)returnMapHRR.get("leftHRADays");	
						Integer daysInMonthHRA = (Integer) returnMapHRR.get("daysInMonthHRA");
						if(isAdjustedHRRPartial)
						{
							revisedHra = Math.round((revisedHra/daysInMonthHRA)*leftDaysHRA);
						}
						
						logger.info("hrr value is "+hrr+"old hra avlue is "+oldHra+"revised Hra value is "+revisedHra);
						logger.info("HRR VALUE NEW SETTING IS "+hrr);
						logger.info("Hrr Id is "+ hrrId);
						//List<HrPayDeductionDtls> deduclst = empDuducDtlsDAO.getDeductionDtls(empId, hrrId,-1,-1);

						//if(deduclst!=null && deduclst.size()>0)
						int updateCount = 0;
						if(hrrAvailFlag)
						{
							/*HrPayDeductionDtls deducDtlsVo = deduclst.get(0);

							deducDtlsVo.setEmpDeducAmount(hrr);
							deducDtlsVo.setOrgPostMstByUpdatedByPost(orgPostMst);
							deducDtlsVo.setOrgUserMstByUpdatedBy(orgUserMst);
							deducDtlsVo.setUpdatedDate(sysdate);
							deducDtlsVo.setEmpCurrentStatus(1);
							logger.info("going to update hrr revised one "+hrr);

							empDuducDtlsDAO.update(deducDtlsVo);
							logger.info("finally deduc amount is "+deducDtlsVo.getEmpDeducAmount());

							logger.info("updated hrr revised one ");*/
							updateCount = empDuducDtlsDAO.updateHrPayDeductionDtls(hrr, orgPostMst.getPostId(), orgUserMst.getUserId(), sysdate, hrrId, commonOtherData.getHrEisEmpMst().getEmpId(), -1, -1);
						
						//else
						if(updateCount < 1)
						{
							HrPayDeductionDtls deducDtlsVo = new HrPayDeductionDtls();
							IdGenerator idgen = new IdGenerator();
							long id = idgen.PKGenerator("HR_PAY_DEDUCTION_DTLS", objServiceArgs);
							//	long id = IDGenerateDelegate.getNextId("HR_PAY_DEDUCTION_DTLS", objServiceArgs);
							deducDtlsVo.setDeducDtlsCode(id);
							deducDtlsVo.setEmpDeducAmount(hrr);
							deducDtlsVo.setOrgPostMstByUpdatedByPost(orgPostMst);
							deducDtlsVo.setOrgUserMstByUpdatedBy(orgUserMst);
							deducDtlsVo.setUpdatedDate(sysdate);
							HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
							hrPayDeducTypeMst.setDeducCode(hrrId);
							deducDtlsVo.setHrPayDeducTypeMst(hrPayDeducTypeMst);
							deducDtlsVo.setCmnDatabaseMst(cmnDatabaseMst);
							deducDtlsVo.setCmnLocationMst(cmnLocationMst);
							deducDtlsVo.setCreatedDate(sysdate);
							deducDtlsVo.setMonth(-1);
							deducDtlsVo.setYear(-1);
							deducDtlsVo.setHrEisEmpMst(newHrEisEmpMst);
							deducDtlsVo.setTrnCounter(1);
							deducDtlsVo.setEmpCurrentStatus(1);
							deducDtlsVo.setOrgPostMstByCreatedByPost(orgPostMst);
							deducDtlsVo.setOrgUserMstByCreatedBy(orgUserMst);
							empDuducDtlsDAO.create(deducDtlsVo);

						}
						}

						//empAllowMpgVo = empAllwMapDao.getHrPayEmpallowMpg(empId, hraId,-1,-1);
						//logger.info("Brfore If Cond"+empAllowMpgVo);
						//if(empAllowMpgVo!=null )
						if(hraAvailFlag)
						{
							logger.info("After If Cond");	
						//	empAllowMpgVo.setEmpAllowAmount(revisedHra);
							/*empAllowMpgVo.setEmpAllowAmount(oldHra);

							empAllowMpgVo.setOrgPostMstByUpdatedByPost(orgPostMst);
							empAllowMpgVo.setOrgUserMstByUpdatedBy(orgUserMst);
							empAllowMpgVo.setUpdatedDate(sysdate);
							empAllowMpgVo.setEmpCurrentStatus(1);

							//logger.info("going to update hra revised one "+revisedHra);
							//logger.info("going to update hra revised one "+revisedHra);

							empAllwMapDao.update(empAllowMpgVo);

							logger.info("updated hra revised one ");*/
							
							updateCount = empAllwMapDao.updateEmpAllowResult(oldHra,orgPostMst.getPostId(),orgUserMst.getUserId(),sysdate,hraId,commonOtherData.getHrEisEmpMst().getEmpId(),-1,-1);
							
						
						//else
						if(updateCount < 1)
						{
							empAllowMpgVo = new HrPayEmpallowMpg();
							IdGenerator idgen = new IdGenerator();
							long id = idgen.PKGenerator("HR_PAY_EMPALLOW_MPG", objServiceArgs);
							//long id = IDGenerateDelegate.getNextId("HR_PAY_EMPALLOW_MPG", objServiceArgs);
							empAllowMpgVo.setAllowCode(id);
							empAllowMpgVo.setEmpAllowAmount(oldHra);
							empAllowMpgVo.setOrgPostMstByUpdatedByPost(orgPostMst);
							empAllowMpgVo.setOrgUserMstByUpdatedBy(orgUserMst);
							empAllowMpgVo.setUpdatedDate(sysdate);
							HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
							hrPayAllowTypeMst.setAllowCode(hraId);
							empAllowMpgVo.setHrPayAllowTypeMst(hrPayAllowTypeMst);
							empAllowMpgVo.setCmnDatabaseMst(cmnDatabaseMst);
							empAllowMpgVo.setCmnLocationMst(cmnLocationMst);
							empAllowMpgVo.setCreatedDate(sysdate);
							empAllowMpgVo.setMonth(-1);
							empAllowMpgVo.setYear(-1);
							empAllowMpgVo.setHrEisEmpMst(newHrEisEmpMst);
							empAllowMpgVo.setEmpCurrentStatus(1);
							empAllowMpgVo.setTrnCounter(1);
							empAllowMpgVo.setOrgPostMstByCreatedByPost(orgPostMst);
							empAllowMpgVo.setOrgUserMstByCreatedBy(orgUserMst);
							empAllwMapDao.create(empAllowMpgVo);
						}
						}

					}

				}
			}//ended by khushal

			////code for Pt revised...

			long totalAllw=0;
			/*List actionList = empAllwMapDAOImpl.getAllAllowanceType(payCommissionId,locId,newHrEisEmpMst.getOrgEmpMst().getEmpId());
			for(int k =0;k<actionList.size();k++)
			{
				Object[] arr=(Object[]) actionList.get(k);
				long amount =empAllwMapDAOImpl.getAmountForAllowance(Long.valueOf(arr[0].toString()),newHrEisEmpMst.getEmpId(),sysdate.getMonth()+1,sysdate.getYear()+1900);
				totalAllw+=amount;
				logger.info(" amount is for "+amount+" for "+arr[1].toString());
			}*/
			//// ended by japen
			
			totalAllw = empAllwMapDAOImpl.getSumOfAllAllowanceValuesByOrgEmpId(payCommissionId, locId, commonOtherData.getHrEisEmpMst().getEmpId());

			List<HrPayDeductionDtls> deduclstPT = empDuducDtlsDAO.getDeductionDtls(otherDtlsVO.getHrEisEmpMst().getOrgEmpMst().getEmpId(), 35 ,-1,-1); /// for PT
			if(deduclstPT!=null && deduclstPT.size()>0)
			{
				double basicBk = Double.parseDouble(genericMap.get("basic").toString());
				genericMap.put("basic",(totalAllw+basicBk));
				logger.info("Going to calulate PT with Gross Value "  + (totalAllw+basicBk));
				double ptAmt=0;
				if(payCommissionId == sixthPayCommLookupId || payCommissionId == commissionFourId || payCommissionId == commissionThreeId || payCommissionId == commissionTwoId || payCommissionId == commissionOneId)
					ptAmt = salRules6th.calculatePT(genericMap);
				else
					ptAmt = salaryRules.calculatePT(genericMap);

				logger.info("ptAmt after calculation is  "  + ptAmt);
				HrPayDeductionDtls ptObj = deduclstPT.get(0);
				ptObj.setEmpDeducAmount(ptAmt);
				empDuducDtlsDAO.update(ptObj);

				genericMap.put("basic",basicBk );
			}



			//List<HrPayDeductionDtls> deducList3= new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory()).getHrPayDeductionDtls(822,1,35,-1,-1);
			//info("Amount from Db after hra hrr calculation  "+deducList3.get(0).getEmpDeducAmount());
			//logger.info("after if  deduc amount is "+empDuducDtlsDAO.getDeductionDtls(empId, hrrId,-1,-1).get(0).getEmpDeducAmount());

			/*long daysofVehiAvailed=0;
			long daysofVehiAvailedHst=0;
			List VehiAvailedDaysList = new ArrayList();
			boolean isVechAvailed=false;
			long taDistance=0;
			 */
			/*HrEmpTraMpg hrEmpTraMpg = new HrEmpTraMpg();
			HrEmpTraMpgHst hrEmpTraMpgHst = new HrEmpTraMpgHst();
			HrEmpTraMpgHst hrEmpTraMpgHstPrev = new HrEmpTraMpgHst();*/
			//HrEmpTraMpgDaoImpl hrEmpTraMpgDao = new HrEmpTraMpgDaoImpl(HrEmpTraMpg.class,serv.getSessionFactory());
			/*List vehicalAvailedList = new ArrayList();
			List vehicalAvailedHstList = new ArrayList();*/

			long empid = newHrEisEmpMst.getEmpId();
			logger.info(" going to calculate vehical availed days for empid "+empid);

			//vehicalAvailedList = hrEmpTraMpgDao.getHrEmpTraData(empid);


			Map redirectMap = new HashMap();


			//added by ravysh

			if(MergedScreen.equals("YES")){
				redirectMap.put("actionFlag", "getOtherDataMerged");	

			}else{
				redirectMap.put("actionFlag", "getOtherData");

			}





			if(msg==1)
				objServiceArgs.put("MESSAGECODE",300006);
			else
				objServiceArgs.put("MESSAGECODE",300005);


			objServiceArgs.put("redirectMap", redirectMap);	
			objServiceArgs.put("fromScaleRevision", "Y");	
			resultObject.setResultCode(ErrorConstants.SUCCESS);


			resultObject.setResultValue(objServiceArgs);


			if(!MergedScreen.equals("YES")){
				if(msg==1){
					resultObject.setViewName("otherEditList");
					//resultObject.setViewName("redirect view");

				}else
					resultObject.setViewName("newOtherMaster");
				//resultObject.setViewName("redirect view");
			}
			if(MergedScreen.equals("YES")){


				if(msg==1){
					resultObject.setViewName("OtherEditListMerged");
					//resultObject.setViewName("redirect view");
				}else
					resultObject.setViewName("newOtherMaster");
				//resultObject.setViewName("redirect view");


			}

			String updatePaybillFlg = "n";
			int paybillMonth=0;
			int paybillYear=0;



			if(objServiceArgs.get("updatePaybillFlg")!=null)
			{	
				updatePaybillFlg=objServiceArgs.get("updatePaybillFlg").toString();
			}

			if(objServiceArgs.get("paybillMonth")!=null)
			{	
				paybillMonth=Integer.parseInt(objServiceArgs.get("paybillMonth").toString());
			}	
			if(objServiceArgs.get("paybillYear")!=null)
			{	
				paybillYear=Integer.parseInt(objServiceArgs.get("paybillYear").toString());
			}

			//logger.info("before returning...   deduc amount is "+empDuducDtlsDAO.getDeductionDtls(empId, hrrId,-1,-1).get(0).getEmpDeducAmount());
			if(updatePaybillFlg.equalsIgnoreCase("y"))
			{

				UpdatePaybillDAOImpl paybillUpdataDAO = new UpdatePaybillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				long otherDtlPK = paybillUpdataDAO.getOtherDetailPKFromEmployeeID(empid);

				OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
				HrEisOtherDtls otherDtlsVo = new  HrEisOtherDtls();
				otherDtlsVo = otherDao.read(otherDtlPK);
				objServiceArgs.put("empId",otherDtlsVo.getHrEisEmpMst().getOrgEmpMst().getEmpId());
				HashMap voToService = new HashMap();
				HttpServletRequest request =(HttpServletRequest)objServiceArgs.get("requestObj");
				long otherId=0;
				if(request.getParameter("otherId")!=null)
					otherId =Long.valueOf(request.getParameter("otherId").toString());
				if(request.getParameter("otherId")!=null)
					otherId =Long.valueOf(request.getParameter("otherId").toString());

				logger.info("test teste   test  "+otherId);

				voToService.put("otherId", otherId);

				//logger.info("before returning...   deduc amount is "+empDuducDtlsDAO.getDeductionDtls(empId, hrrId,-1,-1).get(0).getEmpDeducAmount());

				voToService.put("edit", "N");
				voToService.put("Employee_ID_EmpOtherSearch", String.valueOf(empid));
				objServiceArgs.put("voToServiceMap", voToService);
				objServiceArgs.put("paybillMonth", paybillMonth);
				objServiceArgs.put("paybillYear", paybillYear);
				ResultObject resultObj = serv.executeService("viewTemporaryPayBillSrvc", objServiceArgs);
				objServiceArgs =(Map) resultObj.getResultValue();
				ResultObject resultObj1 = serv.executeService("fillPaybillData", objServiceArgs);
				objServiceArgs =(Map) resultObj1.getResultValue();
				resultObject.setViewName("updatePaybill");
			}

		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...");
			objServiceArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objServiceArgs.put("fromScaleRevision", "N");
			resultObject.setResultValue(objServiceArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ ne.getMessage());
		}

		catch(Exception e)
		{							
			objServiceArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objServiceArgs.put("fromScaleRevision", "N");
			resultObject.setResultValue(objServiceArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
		}

		//List<HrPayDeductionDtls> deducList= new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory()).getHrPayDeductionDtls(822,1,35,-1,-1);
		//logger.info("Amount from Db is FINALLY AT THE END  "+deducList.get(0).getEmpDeducAmount());

		return resultObject;
	}


	//ADDED FOR SALRULES FOR CALLING GENERIC METHOD
	public double getCalculatedData(String methodName,Object[] objArgs,Class salRules,Constructor constructor)
	{
		/*logger.info("I m in the getCalculated Data Method");
		Method[] method1 = salRules.getDeclaredMethods();
		double AllowValue=0;
		String str;
		try
		{

			Object invoker =constructor.newInstance(null);

			for(int l=0;l<method1.length;l++)
			{
					if(method1[l].getName().equals(methodName))
					{	
							Class[] argTypes = method1[l].getParameterTypes();
							if(objArgs != null)
							{

								for(int objcnt =0;objcnt<objArgs.length;objcnt++)
								{
									if(objArgs[objcnt]!=null)
									{
										str = objArgs[objcnt].toString();

										if(argTypes[objcnt].equals(double.class))
										{

											objArgs[objcnt] = new Double(str);
										}
										else if(argTypes[objcnt].equals(long.class))
										{

											objArgs[objcnt] = new Long(str);
										}
										else if(argTypes[objcnt].equals(int.class))
										{

											objArgs[objcnt] = new Integer(str);
										}
										else if(argTypes[objcnt].equals(boolean.class))
										{

											objArgs[objcnt] = new Boolean(str);
										}
										else if(argTypes[objcnt].equals(java.util.Date.class))
										{
								            SimpleDateFormat dd = new SimpleDateFormat("dd");
								            SimpleDateFormat MM = new SimpleDateFormat("MM");
								            SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
											SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
											int day=Integer.parseInt(dd.format(sdformat.parse(str)));
											int month=Integer.parseInt(MM.format(sdformat.parse(str)))-1;
											int year=Integer.parseInt(yyyy.format(sdformat.parse(str)));
								    		Date utilDate= new Date();
								    		utilDate = new GregorianCalendar(year,month,day).getTime();
											objArgs[objcnt] =utilDate;
										}
									}
									else if(objArgs[objcnt]==null)
									{
										objArgs[objcnt] = "";//new Integer(0);
									}
									logger.info("The string value after if is "+objArgs[objcnt]);
								}
								AllowValue = (Double)method1[l].invoke(invoker, objArgs);
							}
							else
							{	
								AllowValue = (Double)method1[l].invoke(invoker, null);
							}	

							logger.info("The "+methodName+" Allowance Value is from calculated data function is in else" +AllowValue);
					}

				} 
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			logger.info("The Exception is " +e);

		}*/
		return 0;
	}


}