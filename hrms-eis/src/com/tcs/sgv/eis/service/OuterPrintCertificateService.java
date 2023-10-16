package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.PayBillDAO;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.pension.dao.TrnBillRegisterDAOImpl;

public class OuterPrintCertificateService<T> extends ServiceImpl{

	
	Log logger = LogFactory.getLog( getClass() );	 
	public ResultObject printCertificate(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		/*Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");*/
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		 
		try
		{		
			/*List rcptObjHds = null;
	        List expObjHds = null;
	        List recObjHds = null;
	        List expEdpList = null;*/
	        
			/*long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
            CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);*/
            
            long billNo = 0;
            
            if(voToService.get("billNo")!=null && !"".equals(voToService.get("billNo").toString().trim()))
            	billNo=Long.parseLong(voToService.get("billNo").toString());
            
            logger.info("Bill No in Outer Print bill is " + billNo);
            
            TrnBillRegisterDAOImpl billRegisterDAOImpl = new TrnBillRegisterDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
            
            TrnBillRegister lObjTrnBillRegister = null;
            lObjTrnBillRegister=  billRegisterDAOImpl.read(billNo);
            
            /*ResultObject billData = serv.executeService("GET_BILL_DATA", objectArgs);
            
            String selected_expenditure = ""; //Selected Expenditure Type
            String selected_Fund=""; //selected Fund Type
            String budTypeSel=""; //Selected Budget Type
            Date billDate = null;
            
            Map lMap = (Map) billData.getResultValue();
            rcptObjHds = (List) lMap.get("rcptObjHds");
            expObjHds = (List) lMap.get("expObjHds");
            recObjHds = (List) lMap.get("recObjHds");
            expEdpList = (List) lMap.get("expEdpList");
                        
            Map ClassOfExp = (Map)lMap.get("Selected_ExpCls");
            Map fundSelected = (Map)lMap.get("Selected_Fund");
            Map budTypeSelected = (Map)lMap.get("Selected_BudType");
            
            TrnBillBudheadDtls lObjTrnBudHdDtls = null; //Object for Getting Head Structure Values              
            lObjTrnBudHdDtls = (TrnBillBudheadDtls)lMap.get("TrnBillBudheadDtls");
            
            TrnBillRegister lObjTrnBillRegister = null;
            lObjTrnBillRegister=(TrnBillRegister)lMap.get("TrnBillRegister");
            String demandCode="";
            String mjrHeadCode = "";
            String subMjrCode= "";
            String mnrCode= "";
            String subHeadCode= "";
            String ddoCode = "";
            if (lObjTrnBillRegister != null && lObjTrnBudHdDtls != null)
            {
            	demandCode= lObjTrnBillRegister.getDemandCode();
            	mjrHeadCode= lObjTrnBillRegister.getBudmjrHd();
            	subMjrCode=lObjTrnBudHdDtls.getBudSubmjrHd();
            	mnrCode=lObjTrnBudHdDtls.getBudMinHd();
            	subHeadCode=lObjTrnBudHdDtls.getBudSubHd();                
            	ddoCode=lObjTrnBillRegister.getDdoCode();
            	billDate = lObjTrnBillRegister.getBillDate();
            }
            SimpleDateFormat formatBillDate = new SimpleDateFormat("dd/MM/yyyy");
            String billSelDate = formatBillDate.format(billDate);
            logger.info("Bill Date in OuterPrintService " + billSelDate);
            if(ClassOfExp!=null && ClassOfExp.size()>0)            	
            {
            	logger.info("Class Of Expenditure is " + ClassOfExp.size());
              logger.info("Class Of Expenditure is " + ClassOfExp.get("Value"));
              logger.info("Class Of Expenditure is " + ClassOfExp.get("Label"));
              selected_expenditure = (String)ClassOfExp.get("Label");
            }
            
            if(fundSelected!=null && fundSelected.size()>0)            	
            {
            	logger.info("Class Of Expenditure is " + fundSelected.size());
              logger.info("Class Of Expenditure is " + fundSelected.get("Value"));
              logger.info("Class Of Expenditure is " + fundSelected.get("Label"));
              selected_Fund = (String)fundSelected.get("Label");
            }
            
            if(budTypeSelected!=null && budTypeSelected.size()>0)            	
            {
            	logger.info("Class Of Expenditure is " + budTypeSelected.size());
              logger.info("Class Of Expenditure is " + budTypeSelected.get("Value"));
              logger.info("Class Of Expenditure is " + budTypeSelected.get("Label"));
              budTypeSel = (String)budTypeSelected.get("Label");
            }
            
            if(expEdpList!=null && expEdpList.size()>0)
            {
            	BillEdpVO billVO = (BillEdpVO)expEdpList.get(0);
            	logger.info("expEdpList.get(0)" + billVO.getBillEdpId() + " Amount is " + billVO.getEdpAmt());
            }
            
            if(expObjHds!=null && expObjHds.size()>0)
            {
            	BillEdpVO billVO = (BillEdpVO)expObjHds.get(0);            	
            	logger.info("expObjHds.get(0)" + billVO.getBillEdpId() + " Amount is " + billVO.getEdpAmt());
            
            }
            */
//          added by Ankit Bhatt for Signature at end of Certificate
            List lstSignInfo = new ArrayList();
            String desigName="";
            String deptName="";
            String cityName="";
            String cardexCode="";
            OrgDdoMst orgDdo = new OrgDdoMst();
            PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
            Object[] objArry = null;
            
            lstSignInfo = billDAO.getOuterSignature(lObjTrnBillRegister.getBillNo());
        	if(lstSignInfo.get(0)!=null) {
        		objArry =(Object[]) lstSignInfo.get(0);
        		logger.info("The Designation is:-"+objArry[0].toString());
        		desigName=objArry[0].toString();
        		logger.info("The Department name is:-"+objArry[1].toString());
        		deptName=objArry[1].toString();
        		logger.info("The Caredx No is:-"+objArry[2].toString());
        		cardexCode=objArry[2].toString();
        		logger.info("The City Name is:-"+objArry[3].toString());
        		cityName=objArry[3].toString();            		
        	}
        	
        	objectArgs.put("desigName",desigName);
        	objectArgs.put("TrnBillRegister",lObjTrnBillRegister);
            objectArgs.put("deptName",deptName);
            objectArgs.put("cardexCode",cardexCode);
            objectArgs.put("cityName",cityName);
            //ended by Ankit Bhatt
            
            /*objectArgs.put("demandCode",demandCode);
            objectArgs.put("mjrHeadCode",mjrHeadCode);
            objectArgs.put("subMjrCode",subMjrCode);
            objectArgs.put("mnrCode",mnrCode);
            objectArgs.put("subHeadCode",subHeadCode);
            objectArgs.put("ddoCode",ddoCode);
            
            objectArgs.put("selected_expenditure",selected_expenditure);
            objectArgs.put("selected_Fund",selected_Fund);
            objectArgs.put("budTypeSel",budTypeSel); 
            objectArgs.put("billSelDate",billSelDate);
            
            
            objectArgs.put("expObjHds",expObjHds);
            objectArgs.put("rcptObjHds",rcptObjHds);
            objectArgs.put("expEdpList",expEdpList); 
            objectArgs.put("recObjHds",recObjHds);*/
            resultObject.setResultValue(objectArgs);
            resultObject.setViewName("printcertificate");
                        
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
}
