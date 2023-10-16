package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ArrearReceiptDAOImpl;
import com.tcs.sgv.eis.dao.MapEmpWithArrearDAO;
import com.tcs.sgv.eis.dao.MapEmpWithArrearDAOImpl;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAO;
import com.tcs.sgv.eis.dao.NewEmployeeConfigDAOImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class ArrearReceiptService extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg=0;

	ResourceBundle constantsBundle = ResourceBundle.getBundle("../resources/Payroll");
	ResourceBundle eisBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	long AISGradeID = Long.parseLong(eisBundle.getString("AISGradeCode").toString() );
	long GradeCode4 = Long.parseLong(eisBundle.getString("GradeCode4").toString() );
	long GradeCode3 = Long.parseLong(eisBundle.getString("GradeCode3").toString() );

	public ResultObject getArrearReceiptPara(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			logger.info("************Inside getArrearReceiptPara of  ArrearReceiptService*****************");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			// for arrear list
			MapEmpWithArrearDAO mapEmpWithArrearDAO = new MapEmpWithArrearDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
			List<HrPaySalRevMst> salRevMstVOList = new ArrayList();
			salRevMstVOList = mapEmpWithArrearDAO.getSalRevOrderData(locId);
			objectArgs.put("salRevMstVOList", salRevMstVOList);

			DBsysdateConfiguration sbConf=new DBsysdateConfiguration();			
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = sbConf.GetDateFormat();
			String dt = sdf.format(currDate);
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());			
			int finYrId = finYearDAOImpl.getFinYearId(dt);

			// for bill no list 
			ArrayList billList = new ArrayList();  
			List billNoList = new ArrayList();
			NewEmployeeConfigDAO newEmpConfigDAO = new NewEmployeeConfigDAOImpl(OrgUserpostRlt.class,serv.getSessionFactory());			
			billNoList = newEmpConfigDAO.getBillNoData(finYrId,locId);
			logger.info("The size of billNoList is---->"+billNoList.size());

			for(Iterator itr=billNoList.iterator();itr.hasNext();)
			{
				HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
				Object[] row = (Object[])itr.next();
				String billNo = row[1].toString();
				String billHeadId = row[0].toString();
				billNoCustomVO.setBillId((billNo));
				billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
				billList.add(billNoCustomVO);
			}
			//
			objectArgs.put("billNoList", billList);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ArrearReceiptPara");

		}
		catch(Exception ex)
		{
			logger.info("There is some problem in getArrearReceiptPara  of  ArrearReceiptService******");
			ex.printStackTrace();
		}
		return resultObject;
	}

	public ResultObject generateArrearReceipt(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try{
			logger.info("************Inside generateArrearReceipt of  ArrearReceiptService*****************");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	

			long salRevOrder = ( voToService.get("salRevOrder")!=null && !voToService.get("salRevOrder").equals("") )?Long.parseLong(voToService.get("salRevOrder").toString()):0 ;
			long billNo = ( voToService.get("billNo")!=null && !voToService.get("billNo").equals("") )?Long.parseLong(voToService.get("billNo").toString()):0 ;
			long orgEmpId = ( voToService.get("orgEmpId")!=null && !voToService.get("orgEmpId").equals("") )?Long.parseLong(voToService.get("orgEmpId").toString()):0 ;

			ArrearReceiptDAOImpl arrearReceiptDAOImpl = new ArrearReceiptDAOImpl(HrPayArrearPaybill.class,serv.getSessionFactory());
			List arrearReceiptDataList = new ArrayList();
			arrearReceiptDataList = arrearReceiptDAOImpl.getArrearReceiptData(salRevOrder, billNo, orgEmpId,locId);

			objectArgs.put("arrearReceiptDataList",arrearReceiptDataList);

			HrPaySalRevMst salRevMst = new HrPaySalRevMst();				
			
			GenericDaoHibernateImpl salRevReadDaoImpl = new GenericDaoHibernateImpl(HrPaySalRevMst.class);
			salRevReadDaoImpl.setSessionFactory(serv.getSessionFactory());
			salRevMst =(HrPaySalRevMst)salRevReadDaoImpl.read(salRevOrder);
			
			List signatureData= arrearReceiptDAOImpl.getSignaturebyMonthandLocId(locId,salRevMst.getRevPayOutDate().getMonth()+1,salRevMst.getRevPayOutDate().getYear()+1900);
			String signEmpName="";
			String signDsgnName="";
			String signDeptName="";
			if(signatureData!=null && signatureData.size()==1)
			{
				Object[] signDataList = new Object[3];
				signDataList = (Object[])signatureData.get(0);
				signEmpName=signDataList[3].toString();
				signDsgnName=signDataList[2].toString();
				signDeptName=signDataList[4].toString();
			}
			

			objectArgs.put("signEmpName",signEmpName);
			objectArgs.put("desigName",signDsgnName);
			objectArgs.put("deptName",signDeptName);
			objectArgs.put("cmnLocationMstObj",cmnLocationMst);


			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ArrearReceiptPage");

		}
		catch(Exception ex)
		{
			logger.info("There is some problem in generateArrearReceipt  of  ArrearReceiptService******");
			ex.printStackTrace();
		}
		return resultObject;
	}

}