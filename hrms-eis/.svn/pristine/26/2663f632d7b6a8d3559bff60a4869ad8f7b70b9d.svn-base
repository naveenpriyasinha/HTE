package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.AllowTypeMasterDAOImpl;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;



public class updateAllOtherDtls extends ServiceImpl  {
	Log logger = LogFactory.getLog( getClass() );
	public ResultObject updateAllOtherDetails(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			Calendar calTime = Calendar.getInstance();
			logger.info("The Inintial time for the updateAllOther Details' VOGEN is:-"+calTime.getTimeInMillis());
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			Map passMap = new HashMap();
			passMap.put("baseLoginMap", loginMap);
			passMap.put("serviceLocator", serv);
			
			CmnLocationMst cmnLocationMst = (CmnLocationMst)loginMap.get("locationVO");
			
			Map voToServiceMap = new HashMap();
			passMap.put("editMode","y");
			
			OtherDetailDAOImpl otherDtlsDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			long empId=0;
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			long locationId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			List otherDtlsList = otherDtlsDAO.getAllOtherData(cmnLocationMst.getLocationCode(),langId,empId);
			int i=0;
			Object[] row;
			String allowId="";
			allowId=objectArgs.get("allowId")!=null && !objectArgs.get("allowId").equals("")?(objectArgs.get("allowId").toString()):"";
			String deducId="";
			deducId=objectArgs.get("deducId")!=null && !objectArgs.get("deducId").equals("")?(objectArgs.get("deducId").toString()):"";
			logger.info(deducId+"deducId ******************Inside updateAllOtherDetails***************** allowId"+allowId);
			
			passMap.put("allowId",allowId);
			passMap.put("deducId",deducId);
			
			//for(counter=0;counter<otherDtlsList.size();counter++)
			if(otherDtlsList!=null)
			for(i=0;i<otherDtlsList.size();i++)
			{
				row = (Object[])otherDtlsList.get(i);
				long otherId = 0;
				otherId=Long.parseLong(row[0]!=null?row[0].toString():"0") ; 
		    		
				HrEisOtherDtls hrEisOtherDtls=new HrEisOtherDtls();
				hrEisOtherDtls=otherDtlsDAO.read(otherId);
				/*long empId = hrEisOtherDtls.getHrEisEmpMst().getEmpId(); 
				long otherId = hrEisOtherDtls.getOtherId();
				long incomeTax = hrEisOtherDtls.getIncomeTax();
				long cityId = hrEisOtherDtls.getCity();
				String isHandicapped = hrEisOtherDtls.getPhyChallenged();
				long initialBasic=hrEisOtherDtls.getotherCurrentBasic();
				long medAllow=hrEisOtherDtls.getMedAllowance();				
				String sis1979=hrEisOtherDtls.getSis1979Flag();
				long uniformAvailed = hrEisOtherDtls.getUniformAvailed();
				long sgdMapId = hrEisOtherDtls.getHrEisSgdMpg().getSgdMapId(); */
				passMap.put("empOtherVO", hrEisOtherDtls);
				ResultObject updateAllowDeducResult = serv.executeService("insertAllowDeducData", passMap);												
				logger.info("updated Allowance for " + hrEisOtherDtls.getHrEisEmpMst().getEmpId());				
			}
			
			AllowTypeMasterDAOImpl allowTypeMasterDaoImpl = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());
			List AllowTypeMstList = allowTypeMasterDaoImpl.getAllowTypeMasterData( langId);
			objectArgs.put("AllowTypeMstList", AllowTypeMstList);
			
			DeducTypeMasterDAOImpl deducTypeMasterDAOImpl = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			List DeducTypeMasterList = deducTypeMasterDAOImpl.getDeducTypeMasterData(langId);
			objectArgs.put("DeducTypeMasterList", DeducTypeMasterList);
			objectArgs.put("MESSAGECODE",300006);
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("AllOtherView");
			resultObject.setResultValue(objectArgs);
			calTime = Calendar.getInstance();
		    logger.info("The End  for the updateAllOther Details' VOGEN is:-"+calTime.getTimeInMillis());
	
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			resultObject.setResultCode(-1);
			resultObject.setThrowable(e);
			resultObject.setViewName("errorPage");
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
		}
	public ResultObject getAllOtherView(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			AllowTypeMasterDAOImpl allowTypeMasterDaoImpl = new AllowTypeMasterDAOImpl(HrPayAllowTypeMst.class,serv.getSessionFactory());
			List AllowTypeMstList = allowTypeMasterDaoImpl.getAllowTypeMasterData( langId);
			objectArgs.put("AllowTypeMstList", AllowTypeMstList);
			
			DeducTypeMasterDAOImpl deducTypeMasterDAOImpl = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			List DeducTypeMasterList = deducTypeMasterDAOImpl.getDeducTypeMasterData(langId);
			objectArgs.put("DeducTypeMasterList", DeducTypeMasterList);

			resultObject.setViewName("AllOtherView");
			resultObject.setResultValue(objectArgs);
           
	
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
		}	
}
