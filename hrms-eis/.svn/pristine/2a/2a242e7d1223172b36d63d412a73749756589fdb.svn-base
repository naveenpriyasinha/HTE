package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.InvestmentTypeMstDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrInvestEmpDtls;
import com.tcs.sgv.eis.valueobject.HrInvestTypeMst;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public class EmpInvestmentDtlsVOGen extends ServiceImpl{
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateEmpInvestEmpDtlsMap(Map objectArgs) {
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		List lstInvestType = new ArrayList();
		try {
		logger.info("Non Government Deduction Master VOGEN Called");						
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		String investDate="";	
		String editMode;
		long investTypeId;
		int counter;
		Date dtInvestDate = new Date();		
		//long empId;
		//Long investTypeId;
		long investAmount;
		String policyNo;
		String submittionProof;
		String approvalproof;
		Date sysDate = new Date();
		editMode = StringUtility.getParameter("edit", request);
		logger.info("The Value of Edit is :-"+editMode);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");				
		List lstHrInvestEmpDtls = new ArrayList();
		List lstEmpId = new ArrayList();
		HrInvestEmpDtls hrInvestEmpDtls = null;
		//HrPayDeducTypeMst hrPayDeducTypeMst = null;
		if(editMode.equalsIgnoreCase("Y")) {
			long empInvestDtlsId = Long.parseLong(StringUtility.getParameter("empInvestDtlsId", request));
			hrInvestEmpDtls = new HrInvestEmpDtls();
			submittionProof = StringUtility.getParameter("isProofSubmit", request);
			logger.info("Submit Flag:-"+submittionProof);
			investDate = StringUtility.getParameter("investDate", request);
			policyNo = StringUtility.getParameter("policyNo", request);
			investAmount = Long.parseLong(StringUtility.getParameter("investAmount", request));
			approvalproof = StringUtility.getParameter("isProofApproved", request);
			logger.info("Approval Flag:-"+approvalproof);
			dtInvestDate = sdf.parse(investDate);					
			hrInvestEmpDtls.setAmount(investAmount);				
			if(submittionProof.equals(""))
				submittionProof="0";				
			else
				submittionProof="1";
			
			if(approvalproof.equals(""))
				approvalproof="0";				
			else
				approvalproof="1";
			hrInvestEmpDtls.setApprovalFlag(approvalproof);
			hrInvestEmpDtls.setAmount(investAmount);
			hrInvestEmpDtls.setInvestDate(dtInvestDate);
			hrInvestEmpDtls.setPolicyNo(policyNo);
			hrInvestEmpDtls.setProofSubmitFlag(submittionProof);
			objectArgs.put("empInvestDtlsId",empInvestDtlsId);
			objectArgs.put("hrInvestEmpDtls",hrInvestEmpDtls);
			objectArgs.put("editMode",editMode);
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("U are out of VOGEN");
		}
		else {
			logger.info("Record Counter is:-"+StringUtility.getParameter("recCounter",request).toString());
			counter = Integer.parseInt(StringUtility.getParameter("recCounter",request).toString());
			logger.info("Counter is:-"+counter);
			for (int i=0;i<counter;i++) {				
				hrInvestEmpDtls = new HrInvestEmpDtls();
				logger.info("Employee Id is:-"+Long.parseLong(StringUtility.getParameter("empId"+i, request)));
				lstEmpId.add(Long.parseLong(StringUtility.getParameter("empId"+i, request)));	
				logger.info("Investment Type Id is:-"+Long.parseLong(StringUtility.getParameter("investTypeId"+i, request)));
				investTypeId = Long.parseLong(StringUtility.getParameter("investTypeId"+i, request));
				logger.info("Amount:-"+Long.parseLong(StringUtility.getParameter("investmentAmount"+i, request)));
				investAmount = Long.parseLong(StringUtility.getParameter("investmentAmount"+i, request));
				logger.info("Submittion Proof:-"+StringUtility.getParameter("submittionProof"+i, request));
				submittionProof = StringUtility.getParameter("submittionProof"+i, request);
				logger.info("Investment Date:-"+StringUtility.getParameter("investmentDate"+i, request));
				investDate = StringUtility.getParameter("investmentDate"+i, request);
				logger.info("Ploicy Number is:-"+StringUtility.getParameter("policyNo"+i, request));
				policyNo = StringUtility.getParameter("policyNo"+i, request);
				logger.info("Approval Flag is:-"+StringUtility.getParameter("approvalProof"+i, request));
				approvalproof = StringUtility.getParameter("approvalProof"+i, request);
				logger.info("Out:- -1");
				dtInvestDate = sdf.parse(investDate);
				logger.info("Out:-0");
				lstInvestType.add(investTypeId);				
				hrInvestEmpDtls.setAmount(investAmount);				
				if(submittionProof.equals("Yes"))
					submittionProof="1";				
				else
					submittionProof="0";
				
				if(approvalproof.equals("Yes"))
					approvalproof="1";				
				else
					approvalproof="0";
				hrInvestEmpDtls.setApprovalFlag(approvalproof);
				hrInvestEmpDtls.setCreatedDate(sysDate);
				hrInvestEmpDtls.setInvestDate(dtInvestDate);
				hrInvestEmpDtls.setPolicyNo(policyNo);
				hrInvestEmpDtls.setProofSubmitFlag(submittionProof);	
				logger.info("Out1:-");
				lstHrInvestEmpDtls.add(i, hrInvestEmpDtls);
			}
			logger.info("Out:-2");
			objectArgs.put("lstInvestType",lstInvestType);
			logger.info("Out:-3");
			objectArgs.put("lstEmpId",lstEmpId);
			logger.info("Out:-4");
			objectArgs.put("counter", counter);
			logger.info("Out:-5");
			objectArgs.put("lstHrInvestEmpDtls",lstHrInvestEmpDtls);
			logger.info("Out:-6");
			objectArgs.put("editMode",editMode);
			logger.info("Out:-7");
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			logger.info("U are out of VOGEN");	
		}
	}
		
		catch(Exception e){
			objectArgs.put("MESSAGECODE",3001);
			retObj.setResultValue(objectArgs);			
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");		
			return retObj;			
		}	
		return retObj;
		
	}
	 
    public ResultObject multiAddEmpInvest(Map objectArgs)
    {
        logger.info("in multiAddEmpInvest");
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serviceLocator.getSessionFactory());
        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serviceLocator.getSessionFactory());
        EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serviceLocator.getSessionFactory());
        OrgEmpMst orgEmpMst = new OrgEmpMst();
		EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serviceLocator.getSessionFactory());
        InvestmentTypeMstDAOImpl investmentTypeMstDAOImpl = new InvestmentTypeMstDAOImpl(HrInvestTypeMst.class,serviceLocator.getSessionFactory());
		LoginDetails objLoginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
       
        try
        {
        	Date sysDate = new Date();
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");				
    		
        	HrInvestEmpDtls hrInvestEmpDtls = new HrInvestEmpDtls();
            // HdVipdutyperMpg hdVipdutyperMpg = new HdVipdutyperMpg();
            
            String empOfficerID = StringUtility.getParameter("Employee_ID_investSearch", request);
            logger.debug("Emp ID Employee_ID_empOfficer::::" + empOfficerID);
            String empNameOff = StringUtility.getParameter("Employee_Name_investSearch", request);
            String gpfNoOff = StringUtility.getParameter("GPF_NM_investSearch", request);
            String degnOff = StringUtility.getParameter("Dsgn_NM_investSearch", request);
            String policeStationOff = StringUtility.getParameter("Police_ST_NM_investSearch", request);
            String districtOff = StringUtility.getParameter("DISTRICT_NM_investSearch", request);
            String investType = StringUtility.getParameter("investType", request);
            String investAmount = StringUtility.getParameter("investAmount", request);
            String isProofSubmit = StringUtility.getParameter("isProofSubmit", request);
            String investDate = StringUtility.getParameter("investDate", request);
            String policyNo = StringUtility.getParameter("policyNo", request);
            String isProofApproved = StringUtility.getParameter("isProofApproved", request);
            
            hrInvestEmpDtls.setAmount(Long.parseLong(investAmount));
            
            if(isProofApproved.equals("Yes"))
            	isProofApproved="1";				
			else
				isProofApproved="0";
            hrInvestEmpDtls.setApprovalFlag(isProofApproved);
            hrInvestEmpDtls.setCreatedDate(sysDate);
            
            orgEmpMst = empDAOImpl.read(Long.parseLong(empOfficerID));
			List<HrEisEmpMst> eisList  = empInfoDAOImpl.getHrEmpFromOrgEmp(orgEmpMst);
			hrInvestEmpDtls.setHrEisEmpMst((HrEisEmpMst)eisList.get(0));
            
           /* hrInvestEmpDtls.setHrEisEmpMst(empInfoDAOImpl.read(Long.parseLong(empOfficerID)));*/
            
            hrInvestEmpDtls.setHrInvestTypeMst(investmentTypeMstDAOImpl.read(Long.parseLong(investType)));
            hrInvestEmpDtls.setInvestDate(StringUtility.convertStringToDate(investDate));
            //hrInvestEmpDtls.setInvestDtlsId(investDtlsId)
            
            hrInvestEmpDtls.setPolicyNo(policyNo);
            
            if(isProofSubmit.equals(""))
            	isProofSubmit="0";				
			else
				isProofSubmit="1";
            hrInvestEmpDtls.setProofSubmitFlag(isProofSubmit);
            
            
            String hrInvestEmpDtlsXML = FileUtility.voToXmlFileByXStream(hrInvestEmpDtls);
            logger.debug("XML :" + hrInvestEmpDtlsXML);
            objectArgs.put("ajaxKey", hrInvestEmpDtlsXML);
            logger.debug("its here 6");
            objRes.setViewName("ajaxData");
            objRes.setResultValue(objectArgs);
            logger.info(" BY end of vogen hrInvestEmpDtlsXML Details");
            return objRes;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            logger.error("BZ Error in multiAddEmpInvest");
            logger.error("Error in EmpInvestmentDtlsVOGen.multiAddEmpInvest() :" + e);
            objRes.setThrowable(e);
            objRes.setResultCode(ErrorConstants.ERROR);
            objRes.setViewName("errorPage");
            return objRes;
        }

    }
	
	
	
	

}
