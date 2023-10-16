package com.tcs.sgv.payslip.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.NonGovDeducDAO;
import com.tcs.sgv.eis.dao.NonGovDeducDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPayslipNonGovt;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.payslip.dao.commonPayslipArgsDAOImpl;
import com.tcs.sgv.payslip.dao.commonPayslipDAOImpl;
import com.tcs.sgv.payslip.valueobject.HrCommonPayslipArgs;
import com.tcs.sgv.payslip.valueobject.commonPayslipVO;

public class commonPayslipServiceImpl extends ServiceImpl implements commonPayslipService{
	Log logger = LogFactory.getLog( getClass() );
	
	public ResultObject getcommonPayslipData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		int month = Integer.parseInt(objectArgs.get("month").toString());
		int year = Integer.parseInt(objectArgs.get("year").toString());
		long selectedEmpId = Long.parseLong(objectArgs.get("selectedEmpId").toString());
		long selectedDeptId = Long.parseLong(objectArgs.get("selectedDeptId").toString());
		String billNo=objectArgs.get("billNo")!=null?objectArgs.get("billNo").toString():"";
		String classArray=objectArgs.get("classArray")!=null?objectArgs.get("classArray").toString():"";
		String dsgnArray=objectArgs.get("dsgnArray")!=null?objectArgs.get("dsgnArray").toString():"";
		//end by manoj for non govt in paylip
		SimpleDateFormat formatCaseDate = new SimpleDateFormat("dd/MM/yyyy");	 
		long CMRFAmount = 0;		// chief minister relief fund Id from property File.
		long SSFAmount = 0;		// salary saving fund Id from Property File.
	    Date formatSysDate = null;
	    Calendar c = Calendar.getInstance();
	    c.set(Calendar.MONTH, (month-1));
	    c.set(Calendar.YEAR, year);
	    SimpleDateFormat sdfMonth =  new SimpleDateFormat("MMMM");
	    String selectedDate = c.getActualMaximum(Calendar.DATE) + "/" + month + "/" + year;
	    logger.info("Maximum days in the" + month  + " Month is " + c.getActualMaximum(Calendar.DATE));

	    Date currDate = formatCaseDate.parse(selectedDate);
		Calendar c1 = Calendar.getInstance();
	    c1.set(Calendar.MONTH, (month-1));
	    Date MonthDate = c1.getTime();
        String monthGiven=sdfMonth.format(MonthDate);
	    Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
 		CmnLocationMstDaoImpl cmnLocDao = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
    	long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

		commonPayslipArgsDAOImpl paySlipArgsDAOImpl = new commonPayslipArgsDAOImpl(HrCommonPayslipArgs.class,serv.getSessionFactory());
		commonPayslipDAOImpl paySlipArgsDAO = new commonPayslipDAOImpl(HrCommonPayslipArgs.class,serv.getSessionFactory());
		NonGovDeducDAO nonGovDeducDAO = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class,serv.getSessionFactory());
		HrPayNonGovDeduction hrPayNonGovDeduction = new HrPayNonGovDeduction();
		HrPayPayslipNonGovt hrPayPayslipNonGovt = new HrPayPayslipNonGovt();
		List paySlipObjList = new ArrayList();
		paySlipObjList = paySlipArgsDAO.getPayslipData(month, year, selectedEmpId, selectedDeptId,billNo,classArray,dsgnArray);
		
		////////  Data Fetching starts here
		List paySlipArgsAllowList = paySlipArgsDAOImpl.getAllAllowanceData();
		List paySlipArgsDeducList = paySlipArgsDAOImpl.getAllDeducData();
		List paySlipArgsNonGovDeducList = paySlipArgsDAOImpl.getAllNonGovDeducData();
		List paySlipDataList=new ArrayList();
		//by manoj for non govt deduc in payslip
		HrPayNonGovDeduction hrPayNonGovDeductionPayslip = new HrPayNonGovDeduction();
		//end by manoj for non govt deduc in payslip
		String scale="";
		String quarterName="";
		if( paySlipObjList!=null && paySlipObjList.size()>0 )
	     {
			Object[] row = new Object[paySlipObjList.size()+1];
			logger.info("Payslip List " + paySlipObjList.size());
			long payslipId =0;
			 long empId=0;
	    	 for(int k=0;k<paySlipObjList.size();k++) {
	    		    row = (Object[])paySlipObjList.get(k);
	    		     payslipId= Long.parseLong((row[0]!=null?row[0].toString():"0"));
			        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
			        
			    
				scale=Long.parseLong(row[47]!=null?row[47].toString():"0")+"";
				if(scale.equals("")||scale.equals("0"))
					scale=Long.parseLong(row[14]!=null?row[14].toString():"0")+"";
				else if(Long.parseLong(row[125]!=null?row[125].toString():"0")==0 && Long.parseLong(row[50]!=null?row[50].toString():"0")==0)
					scale+=	"-"+(Long.parseLong(row[48]!=null?row[48].toString():"0"))+"-"+(Long.parseLong(row[49]!=null?row[49].toString():"0"));
				else if(Long.parseLong(row[125]!=null?row[125].toString():"0")>0 && Long.parseLong(row[50]!=null?row[50].toString():"0")==0)
					scale+=	"-"+(Long.parseLong(row[49]!=null?row[49].toString():"0")+"("+Long.parseLong(row[125]!=null?row[125].toString():"0")+")");
				else// higher scale
					scale+=	"-"+(Long.parseLong(row[48]!=null?row[48].toString():"0"))+"-"+(Long.parseLong(row[49]!=null?row[49].toString():"0"))+"-"+(Long.parseLong(row[50]!=null?row[50].toString():"0"))+"-"+(Long.parseLong(row[51]!=null?row[51].toString():"0"));
				 
			    commonPayslipVO commonPayslipVO = new commonPayslipVO();
				commonPayslipVO.setBankAccNo("");
				commonPayslipVO.setSalary(Long.parseLong(row[14]!=null?row[14].toString():"0"));
				commonPayslipVO.setBillMonth(monthGiven);
				commonPayslipVO.setBudgetHead("");
				commonPayslipVO.setVouchDtls("");
				commonPayslipVO.setKarmachari(Long.parseLong(row[56]!=null?row[56].toString():"0"));
				commonPayslipVO.setDAGPF(0);
				commonPayslipVO.setBillYear(row[4]!=null?row[4].toString():"");
				commonPayslipVO.setBillNo(row[5]!=null?row[5].toString():"");
				commonPayslipVO.setBillDate(row[13]!=null?(sdf.format(row[13])).toString():"");
				commonPayslipVO.setBirthDate(row[44]!=null?(sdf.format(row[44])).toString():"");
				commonPayslipVO.setBycyAdv(0);
				commonPayslipVO.setBycyInt(0);
				commonPayslipVO.setCLA(Long.parseLong(row[19]!=null?row[19].toString():"0"));
				commonPayslipVO.setDA(Long.parseLong(row[18]!=null?row[18].toString():"0"));
				commonPayslipVO.setDAGPF(0);
				commonPayslipVO.setDeptName(row[53]!=null?row[53].toString():"");
				commonPayslipVO.setDoj(row[45]!=null?(sdf.format(row[45])).toString():"");
				commonPayslipVO.setDP(Long.parseLong(row[37]!=null?row[37].toString():"0"));
				commonPayslipVO.setDsgnName(row[52]!=null?row[52].toString():"");
				commonPayslipVO.setEmpName((row[123]!=null?row[123].toString():"")+" "+(row[41]!=null?row[41].toString():"")+" "+(row[42]!=null?row[42].toString():"")+" "+(row[43]!=null?row[43].toString():""));
				
				if (locId==300024){
					commonPayslipVO.setEmpNo(k+1+"");
				}else{
					commonPayslipVO.setEmpNo((row[122]!=null&&!row[122].equals("0")?row[122].toString():""));
				}
				commonPayslipVO.setFanAdv(Long.parseLong(row[33]!=null?row[33].toString():"0"));
				commonPayslipVO.setFanIntAdv(0);
				commonPayslipVO.setFestAdv(Long.parseLong(row[31]!=null?row[31].toString():"0"));
				commonPayslipVO.setFoodAdv(Long.parseLong(row[32]!=null?row[32].toString():"0"));
				commonPayslipVO.setGIS79(0);
				commonPayslipVO.setGISIF(0);
				commonPayslipVO.setGISSF(0);
				commonPayslipVO.setGPF(Long.parseLong(row[29]!=null?row[29].toString():"0"));
				commonPayslipVO.setGPFAccNo((row[9]!=null?row[9]:"").toString());
				commonPayslipVO.setGPFAdv(Long.parseLong(row[30]!=null?row[30].toString():"0"));
				commonPayslipVO.setGPFOilSug(0);
				commonPayslipVO.setHBA(Long.parseLong(row[34]!=null?row[34].toString():"0"));
				commonPayslipVO.setHBInt(0);
				commonPayslipVO.setHRA(Long.parseLong(row[20]!=null?row[20].toString():"0"));
				commonPayslipVO.setHRR(Long.parseLong(row[26]!=null?row[26].toString():"0"));
				commonPayslipVO.setIncrDate(row[46]!=null?(sdfMonth.format(row[46])).toString():"");
				commonPayslipVO.setIT(Long.parseLong(row[25]!=null?row[25].toString():"0"));
				commonPayslipVO.setJeepRent(0);
				commonPayslipVO.setLIC(Long.parseLong(row[58]!=null?row[58].toString():"0"));
				commonPayslipVO.setLS(Long.parseLong(row[15]!=null?row[15].toString():"0"));
				commonPayslipVO.setMA(Long.parseLong(row[22]!=null?row[22].toString():"0"));
				commonPayslipVO.setMCA(Long.parseLong(row[35]!=null?row[35].toString():"0"));
				commonPayslipVO.setMCInt(0);
				commonPayslipVO.setMiscRecv(Long.parseLong(row[36]!=null?row[36].toString():"0"));
				commonPayslipVO.setNagrik(Long.parseLong(row[57]!=null?row[57].toString():"0"));
				commonPayslipVO.setNetPay(Long.parseLong(row[40]!=null?row[40].toString():"0"));
				commonPayslipVO.setNewCredit(Long.parseLong(row[55]!=null?row[55].toString():"0"));
				commonPayslipVO.setPor(Long.parseLong(row[59]!=null?row[59].toString():"0"));
				commonPayslipVO.setCmrf(CMRFAmount);
				commonPayslipVO.setSsf(SSFAmount);
				commonPayslipVO.setNPA(0);
				commonPayslipVO.setOldCredit(Long.parseLong(row[54]!=null?row[54].toString():"0"));
				commonPayslipVO.setUnderSecGAD(Long.parseLong(row[127]!=null?row[127].toString():"0")); // Under Secretary - General Admin. Dept. non-gov type
				
				
				commonPayslipVO.setOthAllow(0);
				commonPayslipVO.setPLI(0);
				commonPayslipVO.setPostRecv(Long.parseLong(row[59]!=null?row[59].toString():"0"));
				commonPayslipVO.setPT(Long.parseLong(row[27]!=null?row[27].toString():"0"));
				commonPayslipVO.setPTA(0);
				commonPayslipVO.setPTARecv(0);
				commonPayslipVO.setRontoAll(0);
				commonPayslipVO.setScale(scale);
				commonPayslipVO.setSPay(Long.parseLong(row[16]!=null?row[16].toString():"0"));
				commonPayslipVO.setSplPayServ(0);
				commonPayslipVO.setTA(Long.parseLong(row[24]!=null?row[24].toString():"0"));
				commonPayslipVO.setWA(Long.parseLong(row[21]!=null?row[21].toString():"0"));
				commonPayslipVO.setBudgetHead((row[12]!=null?row[12]:"").toString());
				commonPayslipVO.setBankAccNo(row[60]!=null?row[60].toString():"");
				commonPayslipVO.setBranch(row[61]!=null?row[61].toString():"");
				
				commonPayslipVO.setFanIntAdv(Long.parseLong(row[65]!=null?row[65].toString():"0"));
				commonPayslipVO.setGIS79(Long.parseLong(row[68]!=null?row[68].toString():"0"));
				commonPayslipVO.setGISIF(Long.parseLong(row[69]!=null?row[69].toString():"0"));
				commonPayslipVO.setGISSF(Long.parseLong(row[70]!=null?row[70].toString():"0"));
				commonPayslipVO.setMCInt(Long.parseLong(row[63]!=null?row[63].toString():"0"));
				commonPayslipVO.setBycyAdv(Long.parseLong(row[66]!=null?row[66].toString():"0"));
				commonPayslipVO.setBycyInt(Long.parseLong(row[67]!=null?row[67].toString():"0"));
				commonPayslipVO.setJeepRent(Long.parseLong(row[62]!=null?row[62].toString():"0"));
				commonPayslipVO.setMCInt(Long.parseLong(row[63]!=null?row[63].toString():"0"));
				commonPayslipVO.setHBInt(Long.parseLong(row[64]!=null?row[64].toString():"0"));
				commonPayslipVO.setPersonalPay(Long.parseLong(row[17]!=null?row[17].toString():"0"));
				
				if((commonPayslipVO.getHBA()+commonPayslipVO.getHBInt())>0)
					commonPayslipVO.setHBAAccNo(row[71]!=null?row[71].toString():"");
				else
					commonPayslipVO.setHBAAccNo("");  
					
				if((commonPayslipVO.getMCA()+commonPayslipVO.getMCInt())>0)
					commonPayslipVO.setMCAAccNo(row[72]!=null?row[72].toString():"");
				else
					commonPayslipVO.setMCAAccNo("");  
						
				commonPayslipVO.setGpfRecvd(Long.parseLong(row[73]!=null&&commonPayslipVO.getGPFAdv()!=0?row[73].toString():"0"));
				commonPayslipVO.setGpfCurrInst(Long.parseLong(row[74]!=null&&commonPayslipVO.getGPFAdv()!=0?row[74].toString():"0"));
				commonPayslipVO.setGpfTotalInst(Long.parseLong(row[75]!=null&&commonPayslipVO.getGPFAdv()!=0?row[75].toString():"0"));
				commonPayslipVO.setGpfPrin(Long.parseLong(row[76]!=null&&commonPayslipVO.getGPFAdv()!=0?row[76].toString():"0"));
								
				commonPayslipVO.setFestRecvd(Long.parseLong(row[77]!=null&&commonPayslipVO.getFestAdv()!=0?row[77].toString():"0"));
				commonPayslipVO.setFestCurrInst(Long.parseLong(row[78]!=null&&commonPayslipVO.getFestAdv()!=0?row[78].toString():"0"));
				commonPayslipVO.setFestTotalInst(Long.parseLong(row[79]!=null&&commonPayslipVO.getFestAdv()!=0?row[79].toString():"0"));
				commonPayslipVO.setFestPrin(Long.parseLong(row[80]!=null&&commonPayslipVO.getFestAdv()!=0?row[80].toString():"0"));
				
				
				commonPayslipVO.setFoodRecvd(Long.parseLong(row[81]!=null&&commonPayslipVO.getFoodAdv()!=0?row[81].toString():"0"));
				commonPayslipVO.setFoodCurrInst(Long.parseLong(row[82]!=null&&commonPayslipVO.getFoodAdv()!=0?row[82].toString():"0"));
				commonPayslipVO.setFoodTotalInst(Long.parseLong(row[83]!=null&&commonPayslipVO.getFoodAdv()!=0?row[83].toString():"0"));
				commonPayslipVO.setFoodPrin(Long.parseLong(row[84]!=null&&commonPayslipVO.getFoodAdv()!=0?row[84].toString():"0"));
				
				commonPayslipVO.setFanRecvd(Long.parseLong(row[85]!=null&&commonPayslipVO.getFanAdv()!=0?row[85].toString():"0"));
				commonPayslipVO.setFanCurrInst(Long.parseLong(row[86]!=null&&commonPayslipVO.getFanAdv()!=0?row[86].toString():"0"));
				commonPayslipVO.setFanTotalInst(Long.parseLong(row[87]!=null&&commonPayslipVO.getFanAdv()!=0?row[87].toString():"0"));
				commonPayslipVO.setFanPrin(Long.parseLong(row[88]!=null&&commonPayslipVO.getFanAdv()!=0?row[88].toString():"0"));
				
				commonPayslipVO.setOcaRecvd(Long.parseLong(row[89]!=null&&commonPayslipVO.getBycyAdv()!=0?row[89].toString():"0"));
				commonPayslipVO.setOcaCurrInst(Long.parseLong(row[90]!=null&&commonPayslipVO.getBycyAdv()!=0?row[90].toString():"0"));
				commonPayslipVO.setOcaTotalInst(Long.parseLong(row[91]!=null&&commonPayslipVO.getBycyAdv()!=0?row[91].toString():"0"));
				commonPayslipVO.setOcaPrin(Long.parseLong(row[92]!=null&&commonPayslipVO.getBycyAdv()!=0?row[92].toString():"0"));
				
				commonPayslipVO.setHbaRecvd(Long.parseLong(row[93]!=null&&commonPayslipVO.getHBA()!=0?row[93].toString():"0"));
				commonPayslipVO.setHbaCurrInst(Long.parseLong(row[94]!=null&&commonPayslipVO.getHBA()!=0?row[94].toString():"0"));
				commonPayslipVO.setHbaTotalInst(Long.parseLong(row[95]!=null&&commonPayslipVO.getHBA()!=0?row[95].toString():"0"));
				commonPayslipVO.setHbaPrin(Long.parseLong(row[96]!=null&&commonPayslipVO.getHBA()!=0?row[96].toString():"0"));
				
				commonPayslipVO.setMcaRecvd(Long.parseLong(row[97]!=null&&commonPayslipVO.getMCA()!=0?row[97].toString():"0"));
				commonPayslipVO.setMcaCurrInst(Long.parseLong(row[98]!=null&&commonPayslipVO.getMCA()!=0?row[98].toString():"0"));
				commonPayslipVO.setMcaTotalInst(Long.parseLong(row[99]!=null&&commonPayslipVO.getMCA()!=0?row[99].toString():"0"));
				commonPayslipVO.setMcaPrin(Long.parseLong(row[100]!=null&&commonPayslipVO.getMCA()!=0?row[100].toString():"0"));
				
				commonPayslipVO.setFanIntRecvd(Long.parseLong(row[101]!=null&&commonPayslipVO.getFanIntAdv()!=0?row[101].toString():"0"));
				commonPayslipVO.setFanIntCurrInst(Long.parseLong(row[102]!=null&&commonPayslipVO.getFanIntAdv()!=0?row[102].toString():"0"));
				commonPayslipVO.setFanIntTotalInst(Long.parseLong(row[103]!=null&&commonPayslipVO.getFanIntAdv()!=0?row[103].toString():"0"));
				commonPayslipVO.setFanIntPrin(Long.parseLong(row[104]!=null&&commonPayslipVO.getFanIntAdv()!=0?row[104].toString():"0"));
				
				commonPayslipVO.setOcaIntRecvd(Long.parseLong(row[105]!=null&&commonPayslipVO.getBycyInt()!=0?row[105].toString():"0"));
				commonPayslipVO.setOcaIntCurrInst(Long.parseLong(row[106]!=null&&commonPayslipVO.getBycyInt()!=0?row[106].toString():"0"));
				commonPayslipVO.setOcaIntTotalInst(Long.parseLong(row[107]!=null&&commonPayslipVO.getBycyInt()!=0?row[107].toString():"0"));
				commonPayslipVO.setOcaIntPrin(Long.parseLong(row[108]!=null&&commonPayslipVO.getBycyInt()!=0?row[108].toString():"0"));
				
				commonPayslipVO.setHbaIntRecvd(Long.parseLong(row[109]!=null&&commonPayslipVO.getHBInt()!=0?row[109].toString():"0"));
				commonPayslipVO.setHbaIntCurrInst(Long.parseLong(row[110]!=null&&commonPayslipVO.getHBInt()!=0?row[110].toString():"0"));
				commonPayslipVO.setHbaIntTotalInst(Long.parseLong(row[111]!=null&&commonPayslipVO.getHBInt()!=0?row[111].toString():"0"));
				commonPayslipVO.setHbaIntPrin(Long.parseLong(row[112]!=null&&commonPayslipVO.getHBInt()!=0?row[112].toString():"0"));
				
				commonPayslipVO.setMcaIntRecvd(Long.parseLong(row[113]!=null&&commonPayslipVO.getMCInt()!=0?row[113].toString():"0"));
				commonPayslipVO.setMcaIntCurrInst(Long.parseLong(row[114]!=null&&commonPayslipVO.getMCInt()!=0?row[114].toString():"0"));
				commonPayslipVO.setMcaIntTotalInst(Long.parseLong(row[115]!=null&&commonPayslipVO.getMCInt()!=0?row[115].toString():"0"));
				commonPayslipVO.setMcaIntPrin(Long.parseLong(row[116]!=null&&commonPayslipVO.getMCInt()!=0?row[116].toString():"0"));
				
				commonPayslipVO.setVouchDtls(row[117]!=null?(row[117].toString()+" / "+(row[118]!=null?(sdf.format(row[118]).toString()):"")):"");
				commonPayslipVO.setBillNetAmt(Long.parseLong(row[119]!=null?row[119].toString():"0"));
				commonPayslipVO.setPanNo(row[120]!=null?(String)row[120]:"");
			
				commonPayslipVO.setPayRecv(Long.parseLong(row[121]!=null?row[121].toString():"0"));
				
				commonPayslipVO.setBonus(Long.parseLong(row[124]!=null?row[124].toString():"0"));
				commonPayslipVO.setDAGPF(Long.parseLong(row[126]!=null?row[126].toString():"0"));
				quarterName = row[128]!= null?row[128].toString():" ";
				quarterName = quarterName.trim();
				if (!quarterName.equals("") && quarterName.length()>1 && quarterName.startsWith(","))
					quarterName = quarterName.substring(1);	
				commonPayslipVO.setQuarterNo(quarterName);
				paySlipDataList.add(commonPayslipVO);
				
		}
	 }
		
		
		
		String dept_id = rb.getString("GAD");
		
		List signatureData= paySlipArgsDAO.getSignaturebyMonthandLocId(locId,month,year);
		String signDataName="";
		String signDsgnName="";
		String signDeptName="";
		if(signatureData!=null && signatureData.size()==1)
		{
			Object[] signDataList = new Object[3];
			signDataList = (Object[])signatureData.get(0);
			signDataName=signDataList[3].toString();
			signDsgnName=signDataList[2].toString();
			signDeptName=signDataList[4].toString();
		}
		
		objectArgs.put("signDataName",signDataName);
		objectArgs.put("signDsgnName",signDsgnName);
		objectArgs.put("signDeptName",signDeptName);
		
		objectArgs.put("paySlipArgsAllowList",paySlipArgsAllowList);
		objectArgs.put("paySlipArgsDeducList",paySlipArgsDeducList);
		objectArgs.put("paySlipAgrsNonGovDeducList",paySlipArgsNonGovDeducList);
		objectArgs.put("paySlipDataList",paySlipDataList);
		objectArgs.put("locId",locId);
		objectArgs.put("dept_id", dept_id);
        ////////Data Fetching ends here

		
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("commonPayslipPage");
		}
		catch(Exception e)
		{			
			e.printStackTrace();
		}
		return resultObject;
		
	}
	
	public ResultObject getcommonPayslipPara(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
		
			ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
			
			long adminRoleId = Long.parseLong(rb.getString("roleId")!=null ?rb.getString("roleId"):"-1");
			
		List<CmnLocationMst> newCmnList = new ArrayList();
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		
 		CmnLocationMstDaoImpl cmnLocDao = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		
    	long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
    	
        
    	newCmnList = cmnLocDao.getLocationList(langId);
		
        
        long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
        
		
		CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
		List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
		
		//Added By Mrugesh for getting Role Id
		//System.out.println("The login details values are--->>>"+loginDetailsMap);
		List <AclRoleMst> aclRole = (List)loginDetailsMap.get("userRoles");
		long isAdminRole=0;
		
		if(aclRole!=null && aclRole.size()>0)
		{
			for(AclRoleMst roleMst : aclRole)
			{
				if(roleMst.getRoleId()==adminRoleId)
				{
					isAdminRole=adminRoleId;
				}
			}
				
		}
				//Ended By Mrugesh		

		
	    List<HrPayBillHeadMpg> billNosList = new ArrayList();		
	//	PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
        commonPayslipDAOImpl paySlipArgsDAO = new commonPayslipDAOImpl(HrCommonPayslipArgs.class,serv.getSessionFactory());
        List billNoList = paySlipArgsDAO.getBillNoData(locId);
		ArrayList billList = new ArrayList();    		
		for(Iterator itr=billNoList.iterator();itr.hasNext();)
		{    			
			Object[] row = (Object[])itr.next();
			HrPayBillHeadMpg hrPayBillHeadMpg = new HrPayBillHeadMpg();
			
			hrPayBillHeadMpg.setBillId(Long.parseLong(row[0].toString()));
			hrPayBillHeadMpg.setBillHeadId(Long.parseLong(row[1].toString()));		 	
			billNosList.add(hrPayBillHeadMpg);
		}   
		
	
		objectArgs.put("billNosList",billNosList);
		objectArgs.put("loginId",userId);
		objectArgs.put("locId",locId);
		objectArgs.put("monthList", monthList);
		objectArgs.put("cmnLocVoList", newCmnList);
		objectArgs.put("yearList", yearList);
		objectArgs.put("isAdminRole", isAdminRole);

		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("commonPayslipPara");
		}
		catch(Exception e)
		{			
			e.printStackTrace();
		}
		return resultObject;
		
	}
	
	public ResultObject getbillNos(Map objectArgs)
	{
		logger.info("IN findEmpNamePayslip Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
        Map voToService = (Map)objectArgs.get("voToServiceMap");
        Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
        
		
		
		StringBuffer propertyList = new StringBuffer();		
        try{
        	long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
        	List empNames  =  new ArrayList();
     	    
        	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
    		//long finYearId=Long.parseLong(resourceBundle.getString("finYearId"));
    		
    		PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
    		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
    		
    		String givenMonth="";
    	    
    	    String givenYear="";
    	    
    	    if(voToService.get("givenMonth")!=null && !voToService.get("givenMonth").toString().equals(""))
    	    	givenMonth=voToService.get("givenMonth").toString();
    	    if(voToService.get("givenYear")!=null && !voToService.get("givenYear").toString().equals(""))
    	    	givenYear=voToService.get("givenYear").toString();
            
    		Calendar cal = Calendar.getInstance();
    		
    		cal.set(Calendar.YEAR,Integer.parseInt( givenYear));
            cal.set(Calendar.MONTH,Integer.parseInt( givenMonth)-1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            java.util.Date finYrDate = cal.getTime();
            SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId); 
            
            String PermanentPost = resourceBundle.getString("PermanentPost");
            String  TemporaryPost = resourceBundle.getString("TemporaryPost");
    		
            commonPayslipDAOImpl paySlipArgsDAO = new commonPayslipDAOImpl(HrCommonPayslipArgs.class,serv.getSessionFactory());
            
            List billNoList = paySlipArgsDAO.getBillData(finYrMst.getFinYearId(),locId);
            
    		ArrayList billList = new ArrayList();    		
    		for(Iterator itr=billNoList.iterator();itr.hasNext();)
    		{
    			
    			Object[] row = (Object[])itr.next();
    			String SubHead = row[0].toString();
    			String classIds = row[1].toString();
    			String dsgnIds = row[2].toString();
    			String billNo = row[3].toString();
    			String postType= "";
    			
    			if(row[4]!=null)
    			{
    				postType=row[4].toString();
    			}
    			else
    			{
    				postType=PermanentPost+","+TemporaryPost;
    			}
    			
    			String billId = row[5].toString();
    			
    			propertyList.append("<billList-mapping>");
              	/*propertyList.append("<bill-Id>").append("<![CDATA[").append(SubHead+"~"+classIds+"~"+dsgnIds+"~"+postType).append("]]>").append("</bill-Id>");*
              	 */
    			propertyList.append("<bill-Id>").append("<![CDATA[").append(billId).append("]]>").append("</bill-Id>"); 
                propertyList.append("<bill-No>").append("<![CDATA[").append(billNo).append("]]>").append("</bill-No>");             
                propertyList.append("</billList-mapping>");
                
    		}
    		            
        	logger.info("  Bill Nos are "+propertyList.toString().toString());
        	Map result = new HashMap();
            String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
            result.put("ajaxKey", stateNameIdStr);               
            resultObject.setResultValue(result);
            resultObject.setViewName("ajaxData");                    	   
	}
	catch(Exception e)
	{
		logger.info("Exception occures...");
		e.printStackTrace();
		logger.info("Exception Occures.");
		resultObject.setResultValue(objectArgs);
		resultObject.setThrowable(e);
		resultObject.setResultCode(-1);
		resultObject.setViewName("errorPage");
	}		
		return resultObject;
	}	
	
	
	public ResultObject getClasses(Map objectArgs)
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();		
	    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
		int month=0;
		int year=0;
		if(request.getParameter("payslipMonth")!=null)
		   month=Integer.parseInt(request.getParameter("payslipMonth").toString());
		logger.info("the selected month  is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + month);
			    
		if(request.getParameter("payslipYear")!=null)
		  year=Integer.parseInt(request.getParameter("payslipYear").toString());
		logger.info("the selected year  is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + year);	
	    
		String billNo="";                   
		if(request.getParameter("billNo")!=null && !request.getParameter("billNo").toString().equals(""))
		billNo=request.getParameter("billNo").toString();
	    logger.info("the bill no is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + billNo);
	  	
	    //commonPayslipDAOImpl billclassmpgdao = new commonPayslipDAOImpl(HrPayBillHeadMpg.class,serv.getSessionFactory());
	    commonPayslipDAOImpl billclassmpgdao = new commonPayslipDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
		List classList = (List)billclassmpgdao.getClassFromBillNo(month,year,billNo);
	    StringBuffer propertyList = new StringBuffer();
	    Object[] row;
		String grade="";
		long gradeId=0;
		logger.info("classList.size is "+classList.size());
		 
		 if(classList!=null &&  classList.size() >0 )
		 {     
			 for(Iterator itr=classList.iterator();itr.hasNext();)
 		     {
 			 row = (Object[])itr.next();
		    	
		    		  if(row[0]!=null)
		    		  {
		    			  logger.info("row[0].toString() "+row[0].toString());
		    			  gradeId = Long.parseLong(row[0].toString());
		    			  logger.info("gradeid "+gradeId);
		    		  }
	 		    	  if(row[1]!=null || !row[1].toString().equals(""))
	 		    		  grade =row[1].toString();
   		  propertyList.append("<class-mapping>");   	
          propertyList.append("<class-Id>").append(gradeId).append("</class-Id>");
          propertyList.append("<class-name>").append("<![CDATA[").append(grade).append("]]>").append("</class-name>");               
          propertyList.append("</class-mapping>");
         }
		 }
   	     Map result = new HashMap();
         String classData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         logger.info("The Ajax Key is:-"+classData);
         result.put("ajaxKey", classData);            
         resultObject.setResultValue(result);
         resultObject.setViewName("ajaxData");  
		}
		catch(Exception e)
		{
			logger.info("Exception occurs...");
			e.printStackTrace();
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}		
        return resultObject;
		
	}
	
	public ResultObject getDesignation(Map objectArgs)
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session=request.getSession();		
	    Map loginDetailsMap =(Map)session.getAttribute("loginDetailsMap");
	    ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			
		int month=0;
		int year=0;
		if(request.getParameter("payslipMonth")!=null)
		   month=Integer.parseInt(request.getParameter("payslipMonth").toString());
		logger.info("the selected month  is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + month);
		    
		if(request.getParameter("payslipYear")!=null)
		  year=Integer.parseInt(request.getParameter("payslipYear").toString());
		logger.info("the selected year  is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + year);
		
		String classArray="";                   
		if(request.getParameter("classArray")!=null && !request.getParameter("classArray").toString().equals(""))
			classArray=request.getParameter("classArray").toString();
	    logger.info("the classArray is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + classArray);
	    
	    String billNo="";                   
		if(request.getParameter("billNo")!=null && !request.getParameter("billNo").toString().equals(""))
		billNo=request.getParameter("billNo").toString();
	    logger.info("the bill no is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + billNo);
	  	
	    commonPayslipDAOImpl billclassdesignationmpgdao = new commonPayslipDAOImpl(OrgDesignationMst.class,serv.getSessionFactory());
		List dsgnList = (List)billclassdesignationmpgdao.getDesignationFromClassId(month,year,classArray,billNo);
	    StringBuffer propertyList = new StringBuffer();
	    Object[] row;
		String dsgnName="";
		long dsgnId=0;
		logger.info("dsgnList.size() "+dsgnList.size());
		   
			
		 if(dsgnList!=null &&  dsgnList.size() >0 )
		 {   
			 for(Iterator itr=dsgnList.iterator();itr.hasNext();)
 		     {
				 row = (Object[])itr.next();
		    		  if(row[0]!=null)
		    		  {
		    			  logger.info("row[0].toString() "+row[0].toString());
		    			  dsgnId = Long.parseLong(row[0].toString());
		    			  logger.info("gradeid "+dsgnId);
		    		  }
	 		    	  if(row[1]!=null || !row[1].toString().equals(""))
	 		    		 dsgnName =row[1].toString()+"("+ row[2].toString() + ")";
							
   		  propertyList.append("<designation-mapping>");   	
          propertyList.append("<dsgn-Id>").append(dsgnId).append("</dsgn-Id>");
          propertyList.append("<dsgn-name>").append("<![CDATA[").append(dsgnName).append("]]>").append("</dsgn-name>");               
          propertyList.append("</designation-mapping>");
               }
		 }
   	     Map result = new HashMap();
         String dsgnData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
         logger.info("The Ajax Key is:-"+dsgnData);
         result.put("ajaxKey", dsgnData);            
         resultObject.setResultValue(result);
         resultObject.setViewName("ajaxData");  
		}
		catch(Exception e)
		{
			logger.info("Exception occurs...");
			e.printStackTrace();
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}		
        return resultObject;
		
	}
	
}
