package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.BillEdpVO;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.dao.BillHeadMpgDAO;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAO;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PaybillRegMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.*;
public class OuterPrintService<T> extends ServiceImpl{
	Log logger = LogFactory.getLog( getClass() );	
	
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");
	
    
    public final int arrearbillTypeId=Integer.parseInt(payrollBundle.getString("arrearbillTypeId"));//arrear bill type id is 2500338
	public final int bill_type_id=Integer.parseInt(payrollBundle.getString("paybillTypeId"));//arrear bill type id is 2500337
	
	public ResultObject printOuter(Map objectArgs)
	{
		
		
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		 
		try
		{		
			List rcptObjHds = null;
	        List expObjHds = null;
	        List recObjHds = null;
	        List expEdpList = null;
	        String classtoprint = "";
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
            CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);
            //logger.info("Bill No in Outer Print bill is " + voToService.get("billNo"));
            //objectArgs.put("billNo", 991000028);
            ResultObject billData = serv.executeService("GET_BILL_DATA", objectArgs);
            classtoprint = voToService.get("classtoprint")!= null && !voToService.get("classtoprint").equals("")? voToService.get("classtoprint").toString():"";
            
            
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
            
            
            //by manoj for paybill outer for hba and mca priinci and int
            PaybillBillregMpg billRegMpg = new PaybillBillregMpg();
            
            PaybillRegMpgDAOImpl billRegDao = new PaybillRegMpgDAOImpl(PaybillBillregMpg.class,serv.getSessionFactory());
            
            
           List  billRegMpgList = billRegDao.getData(lObjTrnBillRegister.getBillNo());
           
           if(billRegMpgList!=null && billRegMpgList.size()>0)
           billRegMpg = (PaybillBillregMpg)billRegMpgList.get(0);
           
           long paybillGrpId = 0;
           String billType = "";
           PayBillDAOImpl paybillDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
           List paybillList = new ArrayList();
           if(billRegMpg.getHrPayPaybill()!=0 && billRegMpg.getBillTypeId().getLookupId() == bill_type_id)//For paybill
           {
        	   paybillGrpId = billRegMpg.getHrPayPaybill();
        	   
        	   paybillList   = paybillDao.getPaybillListForOuter(paybillGrpId);
        	   
        	   billType = "paybill";
        	   
           }
           else
           {
        	   if(billRegMpg.getHrPayPaybill()!=0 && billRegMpg.getBillTypeId().getLookupId() == arrearbillTypeId)//For arrear bill
        	   {
        		   paybillGrpId = billRegMpg.getHrPayPaybill();
        		   
        		   paybillList   = paybillDao.getArrearbillListForOuter(paybillGrpId); 
        		   
        		   billType = "arrear";
        		   
        		   
        	   }
           }
           
           objectArgs.put("billType",billType);
           
           String mca = "0";
           String mcaInt = "0";
           String hba ="0";
           String hbaInt = "0";
           long arrearBillId = 0;
           if(paybillList!=null && paybillList.size()>0){
         
            
           Object[] row = (Object[])paybillList.get(0);
           HrPayArrearPaybill arrearbill = new HrPayArrearPaybill();
           arrearBillId=Long.parseLong(row[0]!=null ? row[0].toString():"0");
           GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(HrPayArrearPaybill.class);
           genDao.setSessionFactory(serv.getSessionFactory());
           if(billRegMpg.getHrPayPaybill()!=0 && billRegMpg.getBillTypeId().getLookupId() == arrearbillTypeId)//For arrear bill
    	   {
        	   arrearbill = (HrPayArrearPaybill)genDao.read(arrearBillId);
    	   }
    	   objectArgs.put("arrearbill",arrearbill);

           mca = row[4]!=null?row[4].toString():"0";
           mcaInt = row[5]!=null?row[5].toString():"0";
           hba = row[2]!=null?row[2].toString():"0";
           hbaInt = row[3]!=null?row[3].toString():"0";
           }
           objectArgs.put("mca",mca);
           objectArgs.put("mcaInt",mcaInt);
           objectArgs.put("hba",hba);
           objectArgs.put("hbaInt",hbaInt);
           
           
           
           //by manoj for paybill outer for hba and mca priinci and int
            
            String demandCode="";
            String mjrHeadCode = "";
            String subMjrCode= "";
            String mnrCode= "";
            String subHeadCode= "";
            String ddoCode = "";
            String billCntrlNo = "";
            String headChargable = "";
            long billNo=0;
            StringTokenizer strToken = null;
            HrPayBillHeadMpg billHeadMpg = new HrPayBillHeadMpg();
            BillHeadMpgDAO billHeadMpgDAO = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());
            List billHeadList = null;
            String desigName="";
            String deptName="";
            String cityName="";
            String cardexCode="";
            OrgDdoMst orgDdo = new OrgDdoMst();
            PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
            Object[] objArry = null;
            List lstSignInfo = new ArrayList();
            
            if (lObjTrnBillRegister != null && lObjTrnBudHdDtls != null)
            {
            	demandCode= lObjTrnBillRegister.getDemandCode();
            	mjrHeadCode= lObjTrnBillRegister.getBudmjrHd();
            	subMjrCode=lObjTrnBudHdDtls.getBudSubmjrHd();
            	mnrCode=lObjTrnBudHdDtls.getBudMinHd();
            	subHeadCode=lObjTrnBudHdDtls.getBudSubHd();                
            	ddoCode=lObjTrnBillRegister.getDdoCode();
            	billCntrlNo=lObjTrnBillRegister.getBillCntrlNo();
            	strToken = new StringTokenizer(billCntrlNo,"(");
            	
            	billCntrlNo=strToken.nextToken().trim();
            	logger.info("The Bill no is:-"+billCntrlNo);
            	billCntrlNo=strToken.nextToken().trim();
            	logger.info("The Bill no is:-"+billCntrlNo);
            	//logger.info("The Bill Number is:-"+strToken.nextToken().trim());
            	billNo = Integer.parseInt(billCntrlNo);
            	billHeadList = new ArrayList(); 
            	
            	long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
            	String cols[] = {"billId","cmnLocationMst.locId"};
   			    Object vals[] = {billNo,locId};
   			    
   			 
            	billHeadList = billHeadMpgDAO.getListByColumnAndValue(cols, vals);
            	logger.info("The Size of the List is:-"+billHeadList.size());
            	if(billHeadList!=null && billHeadList.size()>0){
            		billHeadMpg= (HrPayBillHeadMpg)billHeadList.get(0);

                	//PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory()); 

            		GenericDaoHibernateImpl PaybillHeadMpgDtls = new GenericDaoHibernateImpl(PaybillHeadMpg.class);
            		PaybillHeadMpgDtls.setSessionFactory(serv.getSessionFactory());
            		List<PaybillHeadMpg> paybillHeadMpgList;
            		
            		
            		logger.info("***********************billRegMpg.getHrPayPaybill()"+billRegMpg.getHrPayPaybill());
            		
            		Object valsLoanEmpDtls[] = {billRegMpg.getHrPayPaybill()}; 
            		String colsLoanEmpDtls[] = {"hrPayPaybill"}; 
            		paybillHeadMpgList = PaybillHeadMpgDtls.getListByColumnAndValue(colsLoanEmpDtls, valsLoanEmpDtls);
                    
            		if(paybillHeadMpgList!=null && paybillHeadMpgList.size()>0 && paybillHeadMpgList.get(0).getHeadChargable()!=null && !paybillHeadMpgList.get(0).getHeadChargable().equals(""))
            			headChargable = paybillHeadMpgList.get(0).getHeadChargable();
            		else 
            		if(billHeadMpg.getHeadChargable()!=null && !billHeadMpg.getHeadChargable().equals(""))
            			headChargable = billHeadMpg.getHeadChargable();
            		logger.info("The Head Chargable is :-"+headChargable);
            	}
            	logger.info("The Bill No is:-"+lObjTrnBillRegister.getBillNo());
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
            	
            	billDate = lObjTrnBillRegister.getBillDate();
            }
            SimpleDateFormat formatBillDate = new SimpleDateFormat("MM yy");
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
            objectArgs.put("desigName",desigName);
            objectArgs.put("deptName",deptName);
            objectArgs.put("cardexCode",cardexCode);
            objectArgs.put("cityName",cityName);
            objectArgs.put("billNo",billNo);
            
            objectArgs.put("demandCode",demandCode);
            objectArgs.put("mjrHeadCode",mjrHeadCode);
            objectArgs.put("subMjrCode",subMjrCode);
            objectArgs.put("mnrCode",mnrCode);
            objectArgs.put("subHeadCode",subHeadCode);
            objectArgs.put("ddoCode",ddoCode);
            objectArgs.put("headChargable",headChargable);
            objectArgs.put("selected_expenditure",selected_expenditure);
            objectArgs.put("selected_Fund",selected_Fund);
            objectArgs.put("budTypeSel",budTypeSel); 
            objectArgs.put("billSelDate",billSelDate);
            
            
            objectArgs.put("classtoprint",classtoprint);
            objectArgs.put("expObjHds",expObjHds);
            objectArgs.put("rcptObjHds",rcptObjHds);
            objectArgs.put("expEdpList",expEdpList); 
            objectArgs.put("recObjHds",recObjHds);
            resultObject.setResultValue(objectArgs);
            resultObject.setViewName("printOuter");
                        
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
}


