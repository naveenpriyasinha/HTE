 
package com.tcs.sgv.nps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/*Entity class callling */
import com.tcs.sgv.nps.valueobject.NsdlBhDtls;
import com.tcs.sgv.nps.valueobject.NsdlDhDtls;
import com.tcs.sgv.nps.valueobject.NsdlSdDtls;
//import com.tcs.sgv.eis.zp.zpDdoOffice.valueobject.ZpRltDdoMap;

public class NsdlNpsDAOImpl extends GenericDaoHibernateImpl implements NsdlNpsDAO
{

	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	private final ResourceBundle gObjRsrcBndle = ResourceBundle
	.getBundle("resources/pensionproc/PensionCaseConstants");

	public NsdlNpsDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}



	public List getDdoWiseTotalAmt(final Long yearId, final Long monthId, final String strLocationCode) {
        List lLstReturnList = null;
        final StringBuilder sb = new StringBuilder();
        try {
        	//sb.append("  SELECT temp.* from (");
            sb.append(" SELECT zp.REPT_DDO_CODE,emp.PRAN_NO, sum(paybill.GROSS_AMT) as GROSS_AMT ,sum(paybill.NET_TOTAL) as NET_TOTAL ,sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA)+ sum(JANJULGISARR)  as emp_amt, ");
            sb.append(" sum(paybill.NPS_EMPLR_CONTRI_DED)  as  DED_ADJUST,dto.DTO_REG_NO FROM mst_dcps_emp  ");
            sb.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
            sb.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
            sb.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
            sb.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
            sb.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
            sb.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
            sb.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE    ");
            sb.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.zp_DDO_CODE  ");
            sb.append(" inner join MST_DTO_REG dto on  dto.ddo_code =ddo2.ddo_code  ");
            if (strLocationCode != null && strLocationCode.equalsIgnoreCase("2222222222")) {
                sb.append("  and dto.ddo_code <> '3301' ");
            }
            else {
                sb.append("  and dto.ddo_code <> '3333' ");
            }
            sb.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=ddo2.LOCATION_CODE  ");
            sb.append(" where head.PAYBILL_YEAR= " + yearId + " and head.PAYBILL_MONTH=" + monthId + " and  zp.REPT_DDO_CODE ='" + strLocationCode + "'  ");
            sb.append(" and consMst.STATUS = 1 and emp.PRAN_NO is not null   and emp.zp_STATUS=10 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y'  ");
            sb.append(" group by zp.REPT_DDO_CODE,emp.PRAN_NO,dto.DTO_REG_NO  with ur ");
            final Query selectQuery = (Query)this.ghibSession.createSQLQuery(sb.toString());
            this.logger.info((Object)(" selectQuery  selectQuery  selectQuery  ---------" + selectQuery.toString()));
            lLstReturnList = selectQuery.list();
        }
        catch (Exception e) {
            e.printStackTrace();
            this.gLogger.error((Object)(" Error is : " + e), (Throwable)e);
        }
        return lLstReturnList;
    }
	 
	 public List getDdoWiseTotalAmt_totalListForEmployeeRetired(Long yearId, Long monthId, String strLocationCode)
	 {
		 List lLstReturnList = null;
		 StringBuilder sb = new StringBuilder();
		 try
		 {
			 sb.append(" SELECT emp.SEVARTH_ID,emp.EMP_NAME,emp.PRAN_NO,VARCHAR_FORMAT(emp.SUPER_ANN_DATE,'dd/MM/yyyy') FROM mst_dcps_emp  ");
			 sb.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
			 sb.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
			 sb.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
			 sb.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
			 sb.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
			 sb.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
			 sb.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE    ");
			/* sb.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.FINAL_DDO_CODE  ");*/  
			 /*sb.append(" inner join mst_ddo_reg reg on reg.ddo_code = zp.REPT_DDO_CODE  ");*/
			/* sb.append(" inner join MST_DTO_REG dto on substr(dto.ddo_code,2,2)=substr(ddo.ddo_code,1,2)  ");  */
			 sb.append(" inner join MST_DTO_REG dto on substr(dto.ddo_code,3,2)=substr(ddo.ddo_code,3,2)  ");  
			 /*if(strLocationCode!=null && strLocationCode.equalsIgnoreCase("3333"))
			 {
				 sb.append("  and dto.loc_id <> 3301 ");
			 }
			 else
			 {
				 sb.append("  and dto.loc_id <> 3333 ");
			 }	    	 */
			 /*sb.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo.DDO_CODE,2,4)  ");*/  
			 sb.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo.DDO_CODE,3,4)  ");  
			 sb.append(" where head.PAYBILL_YEAR= " + yearId + " and head.PAYBILL_MONTH=" + monthId + " and zp.REPT_DDO_CODE='" + strLocationCode + "'  ");  
 			 sb.append(" and consMst.STATUS = 1   and emp.zp_STATUS=10 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' and (emp.SUPER_ANN_DATE > sysdate) ");
			 /* sb.append(" and consMst.STATUS = 1 and emp.PRAN_NO is not null  and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' and (emp.SUPER_ANN_DATE < sysdate) ");*/
			/* sb.append(" and consMst.STATUS = 1 and emp.PRAN_NO is NOT null  and emp.zp_STATUS=10 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' and (emp.SUPER_ANN_DATE > sysdate) ");*/
			 sb.append(" group by emp.SEVARTH_ID,emp.EMP_NAME,emp.PRAN_NO, emp.SUPER_ANN_DATE  with ur"); 
			 
			 Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
			 this.logger.info(" selectQuery  selectQuery  selectQuery  ---------" + selectQuery.toString());
			 lLstReturnList = selectQuery.list();
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
			 this.gLogger.error(" Error is : " + e, e);
		 }
		 
		 return lLstReturnList;
	 }
	 
	 
	 public List getDdoWiseTotalAmtSentToNSDL(Long yearId, Long monthId, String subTrCode)
	  {
	    List lLstReturnList = null;
	    StringBuilder sb = new StringBuilder();
	    try
	    {
	      sb.append(" SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd ");
	      sb.append("inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
	      sb.append("and bh.YEAR=" + yearId + " and bh.MONTH=" + monthId + "  and bh.file_name like '" + subTrCode.substring(0,4) + "%' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH,sd.ddo_reg_no with ur");

	      Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
	      this.logger.info(" selectQuery  selectQuery  selectQuery  ---------" + selectQuery.toString());
	      lLstReturnList = selectQuery.list();
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      this.gLogger.error(" Error is : " + e, e);
	    }

	    return lLstReturnList;
	  }
	public List getDdoWiseTotalAmtForDeputation(Long yearId,
			Long monthId, String strLocationCode,String yearCode,String treasuryDDOCode) {

		List lLstReturnList = null;
		List lLstReturnList1 = null;
		List lLstReturnList2 = null;
		List finalList=null;
		StringBuilder sb = new StringBuilder();

		if(strLocationCode.equals("2222")) {
			strLocationCode="2222";
		}
		
		/* sb.append(" SELECT  ddo.DDO_CODE,'0','0',cast(sum(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0)) as double), cast(sum(nvl(trn.NPS_EMPLR_CONTRI_DED,0)-nvl(a.sd_amnt,0)) as double)  FROM MST_DCPS_EMP mstemp ");
		sb.append(" inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID ");
		sb.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE inner join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=mstemp.DDO_CODE ");
		sb.append("left outer join (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,");
		sb.append("bh.YEAR,bh.MONTH FROM NSDL_SD_DTLS sd inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
		sb.append("and bh.YEAR="+yearCode+" and bh.MONTH="+monthId+" and bh.file_name like '%"+strLocationCode+"%' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.SD_PRAN_NO=mstemp.pran_no ");
		sb.append(" where  rlt.REPT_DDO_CODE='"+treasuryDDOCode+"' and trn.FIN_YEAR_ID="+yearId+" and trn.MONTH_ID="+monthId);
		if(strLocationCode.equals("2222")) {
			sb.append("  and trn.TREASURY_CODE='5401' ");
		}else {
			sb.append("  and trn.TREASURY_CODE='"+strLocationCode+"' ");
		}
		sb.append(" and mstemp.DDO_CODE is not null and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1 and mstemp.DCPS_OR_GPF='Y' ");
		sb.append(" and trn.VOUCHER_NO is not null   and trn.VOUCHER_DATE is not null ");
		sb.append(" group by ddo.DDO_CODE,'0','0'  having cast(sum(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0)) as double) >0 and cast(sum(nvl(trn.NPS_EMPLR_CONTRI_DED,0)-nvl(a.sd_amnt,0)) as double)>0 ");
		/*Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		logger.info(" selectQuery  selectQuery  selectQuery  ---------"+selectQuery.toString() );
		lLstReturnList= selectQuery.list();
		logger.info(" lLstReturnList ---------"+lLstReturnList.size());*/
		
		/*
		StringBuilder sb1 = new StringBuilder();
		sb1.append(" SELECT  ddo.DDO_CODE,SUM(nvl(billmpg.BILL_GROSS_AMT,0)),SUM(nvl(billmpg.BILL_NET_AMOUNT,0)),cast(sum(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0)) as double), cast(sum(nvl(trn.NPS_EMPLR_CONTRI_DED,0)-nvl(a.sd_amnt,0)) as double) FROM MST_DCPS_EMP mstemp ");
		sb1.append(" inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID ");
		sb1.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE='"+treasuryDDOCode+"' inner join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=mstemp.DDO_CODE ");
		sb1.append(" inner join ifms.PAYBILL_HEAD_MPG as billmpg on billmpg.BILL_NO=trn.BILL_GROUP_ID ");
		sb1.append(" inner join ifms.CONSOLIDATED_BILL_MPG  as cons on billmpg.paybill_id=cons.PAYBILL_ID ");
		sb1.append(" left outer join (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,");
		sb1.append("  bh.YEAR,bh.MONTH FROM NSDL_SD_DTLS sd inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
		sb1.append("  and bh.YEAR="+yearCode+" and bh.MONTH="+monthId+" and bh.file_name like '%"+strLocationCode+"%' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.SD_PRAN_NO=mstemp.pran_no ");
		sb1.append(" where  rlt.REPT_DDO_CODE='"+treasuryDDOCode+"' and  trn.FIN_YEAR_ID="+yearId+" and trn.MONTH_ID="+monthId);
		if(strLocationCode.equals("2222")) {
			sb.append("  and trn.TREASURY_CODE='5401' ");
		}else {
			sb.append("  and trn.TREASURY_CODE='"+strLocationCode+"' ");
		}
		
		sb1.append(" and mstemp.DDO_CODE is not null   and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1 and mstemp.DCPS_OR_GPF='Y' ");
		sb1.append(" and trn.VOUCHER_NO is not null   and trn.VOUCHER_DATE is not null and cons.STATUS=1 and  billmpg.STATUS=4 and billmpg.APPROVE_FLAG=1");
		sb1.append(" group by ddo.DDO_CODE  having cast(sum(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0)) as double) >0  and cast(sum(nvl(trn.NPS_EMPLR_CONTRI_DED,0)-nvl(a.sd_amnt,0)) as double) >0 ");
		
		Query selectQuery1 = ghibSession.createSQLQuery(sb1.toString());
		lLstReturnList1=selectQuery1.list();
		logger.info(" selectQuery  selectQuery  selectQuery  ---1------"+selectQuery1.toString() );
		logger.info(" lLstReturnList -----1----"+lLstReturnList1.size());
		*/
		
		/*sb2.append(" SELECT temp.DDO_Code,'0','0',sum(temp.CONTRIBUTION),sum(temp.NPS_EMPLR_CONTRI_DED) FROM ( ");
		sb2.append(" SELECT distinct ddo.DDO_CODE ,cast(sum(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0)) as double) as CONTRIBUTION, cast(sum(nvl(trn.NPS_EMPLR_CONTRI_DED,0)-nvl(a.sd_amnt,0)) as double) as NPS_EMPLR_CONTRI_DED FROM MST_DCPS_EMP mstemp ");
		sb2.append(" inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID ");
		sb2.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE inner join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=mstemp.DDO_CODE ");
		sb2.append(" inner join ifms.PAYBILL_HEAD_MPG as billmpg on billmpg.BILL_NO=trn.BILL_GROUP_ID ");
		sb2.append(" inner join ifms.CONSOLIDATED_BILL_MPG  as cons on billmpg.paybill_id=cons.PAYBILL_ID ");
		sb2.append(" left outer join (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,cast(sum(nvl(sd.SD_EMPLR_AMOUNT,0)) as double) as sd_amnt_emplr,");
		sb2.append("  bh.YEAR,bh.MONTH FROM NSDL_SD_DTLS sd inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
		sb2.append(" and bh.YEAR="+yearCode+" and bh.MONTH="+monthId+" and bh.file_name like '%"+strLocationCode+"%' and sd.IS_LEGACY_DATA='N' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.SD_PRAN_NO=mstemp.pran_no ");
		sb2.append(" where rlt.REPT_DDO_CODE='"+treasuryDDOCode+"' and  trn.FIN_YEAR_ID="+yearId+" and trn.MONTH_ID="+monthId);
		if(strLocationCode.equals("2222")) {
			sb.append("  and trn.TREASURY_CODE='5401' ");
		}else {
			sb.append("  and trn.TREASURY_CODE='"+strLocationCode+"' ");
		}
		sb2.append(" and trn.TYPE_OF_PAYMENT='700048' and mstemp.DDO_CODE is not null and PRAN_ACTIVE=1 and mstemp.PRAN_NO is not null and mstemp.DCPS_OR_GPF='Y' ");
		sb2.append(" and trn.VOUCHER_NO is not null   and trn.VOUCHER_DATE is not null and  cons.STATUS=1 and  billmpg.STATUS=4 and billmpg.APPROVE_FLAG=1 ");
		sb2.append(" group by ddo.DDO_CODE having cast(sum(nvl(trn.NPS_EMPLR_CONTRI_DED,0)-nvl(a.sd_amnt,0)) as double) >0  and cast(sum(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0)) as double) >0  ");
		sb2.append(" )as temp group by temp.DDO_Code");
		*/
		
		/*
		sb2.append(" select temp.DDO_CODE,'0','0',cast(sum(nvl(temp.CONTRIBUTION,0)-nvl(temp.sd_emp_amnt,0)) as double) as CONTRIBUTION "); 
		sb2.append(" ,cast(sum(nvl(temp.NPS_EMPLR_CONTRI_DED,0)-nvl(temp.sd_amnt_emplr,0)) as double) as NPS_EMPLR_CONTRI_DED "); 
		*/	
		String empNotPran="SELECT count(pay.EMP_ID) FROM ifms.HR_PAY_PAYBILL as pay  inner join PAYBILL_HEAD_MPG as billmpg on "
				+ " pay.PAYBILL_GRP_ID=billmpg.PAYBILL_ID and  billmpg.PAYBILL_MONTH=pay.PAYBILL_MONTH and billmpg.PAYBILL_YEAR=pay.PAYBILL_YEAR "
				+ " inner join ifms.ORG_DDO_MST ddo on  billmpg.LOC_ID=ddo.location_code  "
				+ " inner join ifms.HR_EIS_EMP_MST as eis on eis.EMP_ID=pay.EMP_ID "
				+ " inner join ifms.MST_DCPS_EMP  as mstemp  on  mstemp.ORG_EMP_MST_ID=eis.EMP_MPG_ID  "
				+ " where billmpg.PAYBILL_YEAR in (SELECT  FIN_YEAR_CODE FROM ifms.SGVC_FIN_YEAR_MST where FIN_YEAR_ID='"+yearId+"') and billmpg.VOUCHER_NO is not null and billmpg.VOUCHER_DATE is not null"
				+ "  and billmpg.PAYBILL_MONTH="+monthId+" and mstemp.PRAN_NO is null and  ddo.DDO_CODE=temp1.DDO_CODE and  pay.dcps <>0 and pay.NPS_EMPLR_CONTRI_DED <>0";
		String empWithPran="SELECT count(pay.EMP_ID) FROM ifms.HR_PAY_PAYBILL as pay  inner join PAYBILL_HEAD_MPG as billmpg on "
				+ " pay.PAYBILL_GRP_ID=billmpg.PAYBILL_ID and  billmpg.PAYBILL_MONTH=pay.PAYBILL_MONTH and billmpg.PAYBILL_YEAR=pay.PAYBILL_YEAR "
				+ " inner join ifms.ORG_DDO_MST ddo on  billmpg.LOC_ID=ddo.location_code  "
				+ " inner join ifms.HR_EIS_EMP_MST as eis on eis.EMP_ID=pay.EMP_ID "
				+ " inner join ifms.MST_DCPS_EMP  as mstemp  on  mstemp.ORG_EMP_MST_ID=eis.EMP_MPG_ID  "
				+ " where billmpg.PAYBILL_YEAR in (SELECT  FIN_YEAR_CODE FROM ifms.SGVC_FIN_YEAR_MST where FIN_YEAR_ID='"+yearId+"')  and billmpg.VOUCHER_NO is not null and billmpg.VOUCHER_DATE is not null"
				+ "  and billmpg.PAYBILL_MONTH="+monthId+" and mstemp.PRAN_NO is not null and  ddo.DDO_CODE=temp1.DDO_CODE  and  pay.dcps <>0 and pay.NPS_EMPLR_CONTRI_DED <>0 ";
		StringBuilder sb2 = new StringBuilder();
		sb2.append(" select temp1.DDO_CODE,("+empNotPran+") as empnotpran,("+empWithPran+") as empWithPran ,sum(temp1.CONTRIBUTION) "); 
		sb2.append(" ,sum(temp1.NPS_EMPLR_CONTRI_DED) from ("); 
		sb2.append(" select temp.DDO_CODE,'0','0',temp.TYPE_OF_PAYMENT,case when temp.TYPE_OF_PAYMENT in ('700046','700047','700048','700049') then sum(nvl(temp.CONTRIBUTION,0)) else 0 end as CONTRIBUTION ");
		sb2.append(" ,case when temp.TYPE_OF_PAYMENT in ('700046') then sum(nvl(temp.NPS_EMPLR_CONTRI_DED,0)) else 0 end as NPS_EMPLR_CONTRI_DED ");
		sb2.append(" from ( ");
		/*sb2.append(" select temp.DDO_CODE,'0','0',cast(sum(nvl(temp.CONTRIBUTION,0)-nvl(temp.sd_emp_amnt,0)) as double) as CONTRIBUTION "); 
		sb2.append(" ,cast(sum(nvl(temp.NPS_EMPLR_CONTRI_DED,0)-nvl(temp.sd_amnt_emplr,0)) as double) as NPS_EMPLR_CONTRI_DED "); 
		sb2.append(" select temp.DDO_CODE,'0','0',temp.TYPE_OF_PAYMENT,case when temp.TYPE_OF_PAYMENT in ('700046','700047','700048','700049') then sum(nvl(temp.CONTRIBUTION,0)) else 0 end as CONTRIBUTION ");
		sb2.append(" ,case when temp.TYPE_OF_PAYMENT in ('700046','700047','700049') then sum(nvl(temp.NPS_EMPLR_CONTRI_DED,0)) else 0 end as NPS_EMPLR_CONTRI_DED ");
		sb2.append(" from ( ");*/
		sb2.append(" SELECT  distinct ddo.DDO_CODE,trn.DCPS_EMP_ID,trn.DCPS_EMP_ID,trn.TYPE_OF_PAYMENT,nvl(trn.CONTRIBUTION,0) as CONTRIBUTION ,nvl(a.sd_amnt,0) as sd_emp_amnt, ");
		sb2.append(" nvl(trn.NPS_EMPLR_CONTRI_DED,0) as NPS_EMPLR_CONTRI_DED,nvl(a.sd_amnt_emplr,0) as sd_amnt_emplr ");
		sb2.append(" FROM ifms.TRN_DCPS_CONTRIBUTION as trn ");
		sb2.append(" left join ifms.MST_DCPS_EMP mstemp  on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID "); 
		sb2.append(" left join ifms.ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE  ");
		sb2.append(" left join ifms.PAYBILL_HEAD_MPG as billmpg on billmpg.BILL_NO=trn.BILL_GROUP_ID ");
		sb2.append(" left join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=mstemp.DDO_CODE   ");
		sb2.append(" left outer join (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt , cast(sum(nvl(sd.SD_EMPLR_AMOUNT,0)) as double) as sd_amnt_emplr, ");
		sb2.append(" bh.YEAR,bh.MONTH FROM NSDL_SD_DTLS sd inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1  ");
		sb2.append(" and bh.YEAR="+yearCode+" and bh.MONTH="+monthId+" and bh.file_name like '%"+strLocationCode+"%' and sd.IS_LEGACY_DATA='N' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.SD_PRAN_NO=mstemp.pran_no ");
		sb2.append(" where rlt.REPT_DDO_CODE='"+treasuryDDOCode+"' and  trn.FIN_YEAR_ID="+yearId+" and trn.MONTH_ID="+monthId);
		if(strLocationCode.equals("2222")) {
			sb.append("  and trn.TREASURY_CODE='5401' ");
		}else {
			sb.append("  and trn.TREASURY_CODE='"+strLocationCode+"' ");
		}
		// sb2.append(" and trn.TYPE_OF_PAYMENT='700046' ");
		sb2.append(" and mstemp.DDO_CODE is not null and PRAN_ACTIVE=1 and mstemp.PRAN_NO is not null and mstemp.DCPS_OR_GPF='Y'  ");
		sb2.append(" and trn.VOUCHER_NO is not null   and trn.VOUCHER_DATE is not null and  billmpg.STATUS=4 and billmpg.APPROVE_FLAG=1 ");  
		sb2.append(" )as temp group by temp.DDO_CODE,'0','0',temp.TYPE_OF_PAYMENT ");
		//sb2.append(" having cast(sum(nvl(temp.CONTRIBUTION,0)-nvl(temp.sd_emp_amnt,0)) as double) >0   and cast(sum(nvl(temp.NPS_EMPLR_CONTRI_DED,0)-nvl(temp.sd_amnt_emplr,0)) as double) >0");
		sb2.append(" ) as temp1 group by temp1.DDO_CODE,'0','0' with ur");
		
		Query selectQuery2 = ghibSession.createSQLQuery(sb2.toString());
		lLstReturnList2=selectQuery2.list();
		logger.info(" selectQuery2  selectQuery  selectQuery  ---------"+selectQuery2.toString() );
		logger.info(" lLstReturnList --2-------"+lLstReturnList2.size());
		//lLstReturnList!=null && lLstReturnList.size()>0) || (lLstReturnList1!=null && lLstReturnList1.size()>0) ||
		if( (lLstReturnList2!=null && lLstReturnList2.size()>0))
		{
			finalList= new ArrayList();
			/*if(lLstReturnList!=null && lLstReturnList.size()>0)
			{
				finalList.addAll(lLstReturnList);
			}*/
			/*if(lLstReturnList1!=null && lLstReturnList1.size()>0)
			{
				finalList.addAll(lLstReturnList1);
			}*/
			if(lLstReturnList2!=null && lLstReturnList2.size()>0)
			{
				finalList.addAll(lLstReturnList2);
			}
			
			
		}
		
		return finalList;
	}

public Long getFinYearId(String finYearCode){
		
		List sev = null;
		Long FinYearId =0l;
		
		try{
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append( " SELECT SFYM.FIN_YEAR_ID FROM SGVC_FIN_YEAR_MST SFYM " ); 
		lSBQuery.append( " WHERE SFYM.FIN_YEAR_CODE =:finYearCode " );
		
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		lQuery.setParameter("finYearCode",finYearCode);		
		
		
		sev = lQuery.list();
		
		if(!sev.isEmpty() && sev.size()>0 && sev.get(0)!=null)
			FinYearId = Long.parseLong(sev.get(0).toString());
		}
		catch(Exception e){
			logger.error("Exception in getFinYearId of LNALedgerQueryDAOImpl: " , e);
		}
		return FinYearId;
	
	}
	public List getFinyear() {

		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		String query = "select finYearCode,finYearCode from SgvcFinYearMst where finYearCode between '2015' and '" + currentYear + "' order by finYearCode DESC";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		List lLstResult = selectQuery.list();
		ComboValuesVO lObjComboValuesVO = null;

		if (lLstResult != null && lLstResult.size() != 0) {
			lLstReturnList = new ArrayList<Object>();
			Object obj[];
			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());
				lObjComboValuesVO.setDesc(obj[1].toString());
				lLstReturnList.add(lObjComboValuesVO);
			}
		} else {
			lLstReturnList = new ArrayList<Object>();
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("--Select--");
			lLstReturnList.add(lObjComboValuesVO);
		}
		return lLstReturnList;
	}
	public List getMonths() {

		String query = "select monthId,monthName from SgvaMonthMst where monthId BETWEEN 1 and 12";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		List lLstResult = selectQuery.list();
		ComboValuesVO lObjComboValuesVO = null;

		if (lLstResult != null && lLstResult.size() != 0) {
			lLstReturnList = new ArrayList<Object>();
			Object obj[];
			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());
				lObjComboValuesVO.setDesc(obj[1].toString());
				lLstReturnList.add(lObjComboValuesVO);
			}
		} else {
			lLstReturnList = new ArrayList<Object>();
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("--Select--");
			lLstReturnList.add(lObjComboValuesVO);
		}
		return lLstReturnList;
	}

	public List getEmployeeListNsdl(String yrCode, String month,String locationCode,String strDDOCode) {
		List empLst = null;
		StringBuilder  Strbld = new StringBuilder();

		try{
	
			Strbld.append(" select a.EMP_NAME,a.DCPS_ID,a.PRAN_NO,cast(a.emp_amt-nvl(b.sd_amnt,0) as double) as emp_amount ,cast(a.DED_ADJUST-nvl(b.sd_emplr_amnt,0) as double) as emplr_amount,a.loc_name,a.dto_reg_no,a.ddo_reg_no,b.sd_amnt,a.CONS_BILL_ID from  ");
			Strbld.append(" (SELECT emp.EMP_NAME,emp.DCPS_ID,emp.PRAN_NO,sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR)  as emp_amt,   "
					+ " sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,loc.loc_name,dto.dto_reg_no,dto.ddo_reg_no,consMst.CONS_BILL_ID FROM mst_dcps_emp  ");
			Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
			Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
			Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
			Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE    ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.zp_DDO_CODE    ");
			Strbld.append(" inner join MST_DTO_REG dto on dto.ddo_code=zp.ZP_DDO_CODE ");   
	         if(locationCode!=null && locationCode.equalsIgnoreCase("2222"))
	         {
	         	Strbld.append("  and substr(ddo2.ddo_code,1,4) <> '3301' ");
	         }
	         else
	         {
	         	Strbld.append("  and substr(ddo2.ddo_code,1,4) <> '2222' ");
	         }
			Strbld.append(" inner join CMN_LOCATION_MST loc  on loc.LOC_ID=substr(zp.rept_DDO_CODE,1,4) ");   
			Strbld.append(" where zp.rept_DDO_CODE='"+strDDOCode+"' and  head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+"  and zp.rept_DDO_CODE='"+strDDOCode+"'  ");
			Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and  emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' and paybill.dcps>0 ");
			Strbld.append(" group by  emp.EMP_NAME,emp.DCPS_ID,emp.PRAN_NO,loc.loc_name,dto.dto_reg_no,dto.ddo_reg_no,consMst.CONS_BILL_ID ) a left outer join  ");
			Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt,cast(sum(nvl(sd.SD_EMPlr_AMOUNT,0)) as double) as sd_emplr_amnt,bh.YEAR,bh.MONTH ,sd.ddo_reg_no FROM NSDL_SD_DTLS sd "); 
			Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1  and  bh.is_LEGACY_DATA='N' ");
			Strbld.append(" and bh.YEAR="+yrCode+" and bh.MONTH="+month+" and bh.file_name like '"+locationCode+"%' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ,sd.ddo_reg_no ) b on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no  ");
			Strbld.append(" where cast(a.emp_amt-nvl(b.sd_amnt,0) as double) > 0 and cast(a.DED_ADJUST-nvl(b.sd_emplr_amnt,0) as double) > 0 order by  a.ddo_reg_no  with ur");
			
			logger.info("   ---------"+Strbld.toString() );
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());

			empLst = lQuery.list();


		}
		catch(Exception e)
		{
			logger.info("Error occer in  getEmployeeList ---------"+ e);
		}
		return empLst;


	}
	
	

	public int getDDoRegCount(String yrCode, String month, String locationCode,String strDDOCode) {
		// TODO Auto-generated method stub

		List temp=null;
		int regCount=0;
		StringBuilder  Strbld = new StringBuilder();

		Strbld.append(" select  count(distinct a.ddo_reg_no)  from  ");
		Strbld.append(" (SELECT head.LOC_ID,ddo.DDO_CODE,emp.PRAN_NO,sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR)  as emp_amt,"
				+ " sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,dto.ddo_reg_no FROM mst_dcps_emp  ");
		Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
		Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
		Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
		Strbld.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
		Strbld.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
		Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE    ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE    ");
		Strbld.append(" inner join MST_DTO_REG dto on dto.ddo_code=zp.zp_DDO_CODE   ");
        if(locationCode!=null && locationCode.equalsIgnoreCase("2222"))
        {
        	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3301' ");
        }
        else
        {
        	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3333' ");
        }		
		//Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4) ");
        Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=ddo2.LOCATION_CODE ");
		Strbld.append(" where zp.REPT_DDO_CODE='"+strDDOCode+"' and head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+"  and substr(ddo2.ddo_code,1,4)='"+locationCode+"'  "); 
		Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' "); 
		//Strbld.append(" and emp.PRAN_NO is  null  and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' "); 
		Strbld.append(" group by ddo.DDO_CODE,emp.PRAN_NO,dto.ddo_reg_no ,head.LOC_ID ) a left outer join  ");
		Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd ");  
		Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1   and  bh.is_LEGACY_DATA='N'");
		Strbld.append(" and bh.YEAR="+yrCode+" and bh.MONTH="+month+" and bh.file_name like  '"+locationCode+"%' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH,sd.ddo_reg_no ) "
				+ "b on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no where a.emp_amt -cast(nvl(b.sd_amnt,0) as double) >0 and "
				+ " cast(a.DED_ADJUST-nvl(b.sd_amnt,0) as double) >0   with ur");	
		gLogger.info("Query is ***********getDDoRegCount***"+Strbld.toString());

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


		temp=lQuery.list();
		logger.info("temp size"+temp.size());
		if(temp!=null && temp.size()>0){
			regCount=(int) temp.get(0);
			logger.info("regCount+++++++++ "+regCount);

		}

		return regCount;
	}
	
	/**
	 * @param yrCode
	 * @param month
	 * @param treasuryyno
	 * @param year
	 * @param treasuryDDOCode
	 * @return
	 */
	public Long getDDoRegCountDeputation(String yrCode, String month,
			String treasuryyno,String year,String treasuryDDOCode)
	{
		

		List empLst = null;
		List lLstReturnList1 = null;
		List lLstReturnList2 = null;
		
		Long regCount=0l;
		Long regCount1=0l;
        Long regCount2=0l;
        Long regCount3=0l;

		StringBuilder  sb = new StringBuilder();

		try{

			sb.append("  select count(distinct (abc.ddo_reg_no))   from ");
			sb.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, dto.ddo_reg_no ");
			sb.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
			sb.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
			sb.append(" and mstemp.DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' ");
			sb.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, dto.ddo_reg_no) abc ");
			sb.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
			sb.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
			sb.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
			sb.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0  with ur");
			
			
			logger.error("   ---------"+sb.toString() );
			SQLQuery lQuery = ghibSession.createSQLQuery(sb.toString());

			empLst = lQuery.list();
		
			

			StringBuilder  sb1 = new StringBuilder();
			sb1.append("  select count(distinct (abc.ddo_reg_no))   from ");
			sb1.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, dto.ddo_reg_no ");
			sb1.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE='"+treasuryDDOCode+"' inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
			sb1.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
			sb1.append(" and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is null and mstemp.DCPS_OR_GPF='Y' ");
			sb1.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, dto.ddo_reg_no) abc ");
			sb1.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
			sb1.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
			sb1.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no  with ur");
			sb1.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0  ");
			
			
			logger.error("   ---------"+sb.toString() );
			SQLQuery lQuery1= ghibSession.createSQLQuery(sb1.toString());

			lLstReturnList1 = lQuery1.list();
	 
			

			StringBuilder  sb2 = new StringBuilder();
			sb2.append("  select count(distinct (abc.ddo_reg_no))   from ");
			sb2.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, dto.ddo_reg_no ");
			sb2.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE= mstemp.DEPT_DDO_CODE inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
			sb2.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
			sb2.append(" and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' ");
			sb2.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, dto.ddo_reg_no) abc ");
			sb2.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
			sb2.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
			sb2.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no  with ur");
			sb2.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0  ");
			
			
			
			logger.error("   ---------"+sb.toString() );
			SQLQuery lQuery2= ghibSession.createSQLQuery(sb2.toString());

			lLstReturnList2 = lQuery2.list();
			
				if(empLst!=null && empLst.size()>0)
				{
					regCount1=Long.parseLong(empLst.get(0).toString());
				}
				if(lLstReturnList1!=null && lLstReturnList1.size()>0)
				{
					regCount2=Long.parseLong(lLstReturnList1.get(0).toString());
				}
				if(lLstReturnList2!=null && lLstReturnList2.size()>0)
				{
					regCount3=Long.parseLong(lLstReturnList2.get(0).toString());
				}
				
				
				regCount=regCount1+regCount2+regCount3;


		}
		catch(Exception e)
		{
			logger.info("Error occer in  getEmployeeList ---------"+ e);
		}
		return regCount;


	
	}



	public List getEmployeeContriTotalList(String yrCode, String month,
			String locationCode) {
		// TODO Auto-generated method stub

		List temp=null;
		Long regCount=null;
		StringBuilder  Strbld = new StringBuilder();

		Strbld.append(" select cast(sum(a.emp_amt-nvl(cast(b.sd_amnt as bigint),0)) as VARCHAR(20)) ||'#'|| cast(sum(a.DED_ADJUST-nvl(cast(b.sd_amnt as bigint),0)) as VARCHAR(20)) from  ");
		Strbld.append(" (SELECT sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR) as emp_amt,  sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,emp.PRAN_NO,dto.ddo_reg_no FROM mst_dcps_emp  ");
		Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
		Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
		Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID ");
		Strbld.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
		Strbld.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
		Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE  ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.zp_DDO_CODE  ");
		/*Strbld.append(" inner join mst_ddo_reg reg on reg.ddo_code = zp.REPT_DDO_CODE  ");*/
		Strbld.append(" inner join MST_DTO_REG dto on  dto.ddo_code=ddo2.ddo_code   ");
        if(locationCode!=null && locationCode.equalsIgnoreCase("3333"))
        {
        	Strbld.append("  and substr(ddo2.ddo_code,1,4) <> '3301' ");
        }
        else
        {
        	Strbld.append("  and substr(ddo2.ddo_code,1,4) <> '3333' ");
        }
		
		Strbld.append(" where head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+"  and substr(zp.REPT_DDO_CODE,1,4)='"+locationCode+"'  ");
/*		Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y'  ");*/
		Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y'  ");
		Strbld.append(" group by emp.pran_no,dto.ddo_reg_no ) a left outer join  ");
		Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd  ");
		Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1  ");
		Strbld.append(" and bh.YEAR="+yrCode+" and bh.MONTH="+month+" and bh.file_name like  '"+locationCode+"%'  group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH,sd.ddo_reg_no ) b  ");
		Strbld.append(" on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no  where a.emp_amt-cast(nvl(b.sd_amnt,0) as double) >0 and cast(a.DED_ADJUST-nvl(b.sd_amnt,0) as double) >0 fetch first 2 rows only");
		gLogger.info("Query is ***********getEmployeeContriTotalList***"+Strbld.toString());

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


		temp=lQuery.list();
		gLogger.info("Query is ***********getEmployeeContriTotalList* temp size is **"+temp.size());
		return temp;
	}
	/*******************DDO Level-2 speecififc ********************************/
	public List getEmployeeContriTotalList(String yrCode, String month,
			String locationCode,String strDDOCode) {
		// TODO Auto-generated method stub

		List temp=null;
		Long regCount=null;
		StringBuilder  Strbld = new StringBuilder();

		Strbld.append(" select cast(sum(a.emp_amt-nvl(cast(b.sd_amnt as bigint),0)) as VARCHAR(20)) ||'#'|| cast(sum(a.DED_ADJUST-nvl(cast(b.sd_amnt as bigint),0)) as VARCHAR(20)) from  ");
		Strbld.append(" (SELECT sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR) as emp_amt,  sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,emp.PRAN_NO,dto.ddo_reg_no FROM mst_dcps_emp  ");
		Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
		Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
		Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID ");
		Strbld.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
		Strbld.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
		Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE  ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.zp_DDO_CODE  ");
		/*Strbld.append(" inner join mst_ddo_reg reg on reg.ddo_code = zp.REPT_DDO_CODE  ");*/
		Strbld.append(" inner join MST_DTO_REG dto on  dto.ddo_code=ddo2.ddo_code   ");
        if(locationCode!=null && locationCode.equalsIgnoreCase("3333"))
        {
        	Strbld.append("  and substr(ddo2.ddo_code,1,4) <> '3301' ");
        }
        else
        {
        	Strbld.append("  and substr(ddo2.ddo_code,1,4) <> '3333' ");
        }
		
		Strbld.append(" where head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+"  and zp.REPT_DDO_CODE='"+strDDOCode+"' and substr(zp.REPT_DDO_CODE,1,4)='"+locationCode+"'  ");
/*		Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y'  ");*/
		Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' and PAYBILL.dcps>0 ");
		Strbld.append(" group by emp.pran_no,dto.ddo_reg_no ) a left outer join  ");
		Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd  ");
		Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1  ");
		Strbld.append(" and bh.YEAR="+yrCode+" and bh.MONTH="+month+" and bh.file_name like  '"+locationCode+"%'  group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH,sd.ddo_reg_no ) b  ");
		Strbld.append(" on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no  where a.emp_amt-cast(nvl(b.sd_amnt,0) as double) >0 and cast(a.DED_ADJUST-nvl(b.sd_amnt,0) as double) >0 ");
		gLogger.info("Query is ***********getEmployeeContriTotalList***"+Strbld.toString());

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


		temp=lQuery.list();
		gLogger.info("Query is ***********getEmployeeContriTotalList* temp size is **"+temp.size());
		return temp;
	}
	/*******************DDO Level-2 speecififc ********************************/

	public List getEmployeeContriTotalListForDeputation(String yrCode, String month,
			String treasuryyno,String year,String treasuryDDOCode ) {
		// TODO Auto-generated method stub

		List temp=null;
		
		List lLstReturnList1 = null;
		List lLstReturnList2 = null;
		List finalList=null;
		StringBuilder  sb = new StringBuilder();

		/*sb.append(" SELECT  sum(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0)) FROM MST_DCPS_EMP mstemp ");
		sb.append(" inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID ");
		sb.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE ");
		sb.append(" inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code  ");
		sb.append(" left outer join (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,");
		sb.append(" bh.YEAR,bh.MONTH FROM NSDL_SD_DTLS sd inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
		sb.append(" and bh.YEAR="+year+" and bh.MONTH="+month+" and bh.file_name like '%"+treasuryyno+"%D' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=mstemp.pran_no ");
		sb.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y'   ");
		sb.append(" and mstemp.PRAN_NO is not null and PRAN_ACTIVE=1 and mstemp.DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' ");
		*/
		
		sb.append("  select sum(abc.c-nvl(a.sd_amnt,0))   from ");
		sb.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
		sb.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
		sb.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
		sb.append(" and mstemp.DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' ");
		sb.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
		sb.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
		sb.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
		sb.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
		sb.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0  with ur");
		
		
		
		gLogger.info("Query is ***********getEmployeeContriTotalList***"+sb.toString());

		SQLQuery lQuery = ghibSession.createSQLQuery(sb.toString());


		temp=lQuery.list();
		
		 
		
		StringBuilder  sb1 = new StringBuilder();
		sb1.append("  select sum(abc.c-nvl(a.sd_amnt,0))   from ");
		sb1.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
		sb1.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE='"+treasuryDDOCode+"' inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
		sb1.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
		sb1.append(" and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is null and mstemp.DCPS_OR_GPF='Y' ");
		sb1.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
		sb1.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
		sb1.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
		sb1.append(" bh.file_name like '%"+treasuryyno+"%'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no  with ur");
		sb1.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0  ");
		
		
		gLogger.info("Query is ***********getEmployeeContriTotalList***"+sb.toString());

		SQLQuery lQuery1 = ghibSession.createSQLQuery(sb1.toString());


		lLstReturnList1=lQuery1.list();
		
		 
		
		

		StringBuilder  sb2 = new StringBuilder();
		sb2.append("  select sum(abc.c-nvl(a.sd_amnt,0))   from ");
		sb2.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
		sb2.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE= mstemp.DEPT_DDO_CODE inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
		sb2.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
		sb2.append(" and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' and   TRn.NPS_EMPLR_CONTRI_DED>0 ");
		sb2.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
		sb2.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
		sb2.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
		sb2.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
		sb2.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0   with ur");
		
		
		gLogger.info("Query is ***********getEmployeeContriTotalList***"+sb.toString());

		SQLQuery lQuery2 = ghibSession.createSQLQuery(sb2.toString());


		lLstReturnList2=lQuery2.list();
		
		if((temp!=null && temp.size()>0) || (lLstReturnList1!=null && lLstReturnList1.size()>0) || (lLstReturnList2!=null && lLstReturnList2.size()>0))
		{
			finalList= new ArrayList();
			if(temp!=null && temp.size()>0)
			{
				finalList.addAll(temp);
			}
			if(lLstReturnList1!=null && lLstReturnList1.size()>0)
			{
				finalList.addAll(lLstReturnList1);
			}
			if(lLstReturnList2!=null && lLstReturnList2.size()>0)
			{
				finalList.addAll(lLstReturnList2);
			}
			
			
		}
		
		return finalList;
	}

	public String[] getEmployeeCountDdoregNsdl(String yrCode, String month,String locationCode,String strDDOCode) {
		// TODO Auto-generated method stub

		List empLst = null;

		String [] empCountLst= (String[])null;

		StringBuilder  Strbld = new StringBuilder();
		try{
			
			/* Strbld.append(" select count( distinct a.PRAN_NO)  from   ");
			Strbld.append(" (SELECT emp.EMP_NAME,emp.DCPS_ID,emp.PRAN_NO,sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR) as emp_amt,  "
					+ "  sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,loc.loc_name,dto.dto_reg_no,dto.ddo_reg_no FROM mst_dcps_emp  ");
			Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
			Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
			Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID "); 
			Strbld.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
			Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE  ");  
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE  ");  
			Strbld.append(" inner join MST_DTO_REG dto on dto.ddo_code = zp.ZP_DDO_CODE  "); 
	         if(locationCode!=null && locationCode.equalsIgnoreCase("3333"))
	         {
	         	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3301' ");
	         }
	         else
	         {
	         	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3333' ");
	         }			
			Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)  ");  
			Strbld.append(" where zp.REPT_DDO_CODE='"+strDDOCode+"' and head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+"  and substr(ddo2.ddo_code,1,4)='"+locationCode+"'  ");
			Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y'  ");
			Strbld.append(" group by emp.EMP_NAME,emp.DCPS_ID,emp.PRAN_NO,loc.loc_name,dto.dto_reg_no,dto.ddo_reg_no ) a left outer join  "); 
			 Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd  "); 
			 Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1  "); 
			Strbld.append(" and bh.YEAR="+yrCode+" and bh.MONTH="+month+" and bh.file_name like '"+locationCode+"%' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH,sd.ddo_reg_no ) b on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no  "); 
			Strbld.append("  group by a.ddo_reg_no order by  a.ddo_reg_no  "); 
			*/
			/*Strbld.append(" select a.ddo_reg_no,count( distinct a.PRAN_NO)  from   ");
			Strbld.append(" (SELECT  emp.PRAN_NO,dto.dto_reg_no,dto.ddo_reg_no FROM ifms.mst_dcps_emp   emp inner join ifms.TRN_DCPS_CONTRIBUTION as trn on emp.DCPS_EMP_ID=trn.DCPS_EMP_ID ");
			Strbld.append(" inner  join ifms.RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE  ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE   inner join ifms.MST_DTO_REG dto on dto.ddo_code = zp.ZP_DDO_CODE   ");
			
			 if(locationCode!=null && locationCode.equalsIgnoreCase("3333"))
	         {
	         	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3301' ");
	         }
	         else
	         {
	         	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3333' ");
	         }			
			Strbld.append(" inner join  ifms.SGVC_FIN_YEAR_MST as fin on fin.FIN_YEAR_ID=trn.FIN_YEAR_ID ");
			Strbld.append(" where zp.REPT_DDO_CODE='"+strDDOCode+"'  and substr(zp.REPT_DDO_CODE,1,4)='"+locationCode+"'   and fin.fin_Year_Code="+yrCode+" and trn.MONTH_ID="+month+"  ");
			Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10   and emp.dcps_or_gpf='Y'   and trn.VOUCHER_NO is not null and trn.VOUCHER_DATE is not null  and TRn.NPS_EMPLR_CONTRI_DED>0");
			Strbld.append(" )  a left outer join   (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM ifms.NSDL_SD_DTLS sd   ");
			Strbld.append(" inner join ifms.NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 and bh.IS_LEGACY_DATA='N'  and bh.YEAR="+yrCode+" and bh.MONTH="+month+" and bh.file_name like '"+locationCode+"%' ");
			Strbld.append(" group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH,sd.ddo_reg_no ) b on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no    group by a.ddo_reg_no order by  a.ddo_reg_no   with ur");
			*/
			Strbld.append(" select  a.ddo_reg_no,count( distinct a.PRAN_NO)  from  ");
			Strbld.append(" (SELECT head.LOC_ID,ddo.DDO_CODE,emp.PRAN_NO,sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR)  as emp_amt,"
					+ " sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,dto.ddo_reg_no FROM mst_dcps_emp  ");
			Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
			Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
			Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
			Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE    ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE    ");
			Strbld.append(" inner join MST_DTO_REG dto on dto.ddo_code=zp.zp_DDO_CODE   ");
	        if(locationCode!=null && locationCode.equalsIgnoreCase("2222"))
	        {
	        	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3301' ");
	        }
	        else
	        {
	        	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3333' ");
	        }		
			Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=ddo2.LOCATION_CODE ");
			Strbld.append(" where zp.REPT_DDO_CODE='"+strDDOCode+"' and head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+"  and substr(ddo2.ddo_code,1,4)='"+locationCode+"'  "); 
			Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' "); 
			Strbld.append(" group by ddo.DDO_CODE,emp.PRAN_NO,dto.ddo_reg_no ,head.LOC_ID ) a left outer join  ");
			Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd ");  
			Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1   and  bh.is_LEGACY_DATA='N'");
			Strbld.append(" and bh.YEAR="+yrCode+" and bh.MONTH="+month+" and bh.file_name like  '"+locationCode+"%' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH,sd.ddo_reg_no ) "
					+ "b on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no where a.emp_amt -cast(nvl(b.sd_amnt,0) as double) >0 and "
					+ " cast(a.DED_ADJUST-nvl(b.sd_amnt,0) as double) >0 group by a.ddo_reg_no  with ur");
			logger.info("query for count"+Strbld.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			
			

			empLst = lQuery.list();
			empCountLst = new String[empLst.size()];
			for(int i=0;i<empLst.size();i++)
				empCountLst[i]=empLst.get(i).toString();
			

		}
		catch(Exception e)
		{
			logger.info("Error occer in  getEmployeeList ---------"+ e);
		}
		return empCountLst;

	}
	
	/********************/
	public 	Map<String, String>  getEmployeeCountDdoregNsdlMap(String yrCode, String month,String locationCode,String strDDOCode) {
		// TODO Auto-generated method stub

		List empLst = null;

		StringBuilder  Strbld = new StringBuilder();
		Map<String,String> empcountDdoregMap=new HashMap();//Creating HashMap
		try{
		
			Strbld.append(" select  a.ddo_reg_no,count( distinct a.PRAN_NO)  from  ");
			Strbld.append(" (SELECT head.LOC_ID,ddo.DDO_CODE,emp.PRAN_NO,sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR)  as emp_amt,"
					+ " sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,dto.ddo_reg_no FROM mst_dcps_emp  ");
			Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
			Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
			Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
			Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE    ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE    ");
			Strbld.append(" inner join MST_DTO_REG dto on dto.ddo_code=zp.zp_DDO_CODE   ");
	        if(locationCode!=null && locationCode.equalsIgnoreCase("2222"))
	        {
	        	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3301' ");
	        }
	        else
	        {
	        	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3333' ");
	        }		
			Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=ddo2.LOCATION_CODE ");
			Strbld.append(" where zp.REPT_DDO_CODE='"+strDDOCode+"' and head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+"  and substr(ddo2.ddo_code,1,4)='"+locationCode+"'  "); 
			Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' "); 
			Strbld.append(" group by ddo.DDO_CODE,emp.PRAN_NO,dto.ddo_reg_no ,head.LOC_ID ) a left outer join  ");
			Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd ");  
			Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1   and  bh.is_LEGACY_DATA='N'");
			Strbld.append(" and bh.YEAR="+yrCode+" and bh.MONTH="+month+" and bh.file_name like  '"+locationCode+"%' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH,sd.ddo_reg_no ) "
					+ "b on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no where a.emp_amt -cast(nvl(b.sd_amnt,0) as double) >0 and "
					+ " cast(a.DED_ADJUST-nvl(b.sd_amnt,0) as double) >0 group by a.ddo_reg_no  with ur");
			logger.info("query for count"+Strbld.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			empLst = lQuery.list();
			
			for(int i=0;i<empLst.size();i++) {
				Object[] tuple = (Object[]) empLst.get(i);
				empcountDdoregMap.put(tuple[0].toString(),tuple[1].toString()); 
			}
			

		}
		catch(Exception e)
		{
			logger.info("Error occer in  getEmployeeList ---------"+ e);
		}
		return empcountDdoregMap;

	}
	/********************/
	/*DDO Conrtribution by map*/
	public 	Map<String, String> getEmployeeListDdoregNsdlMap(String yrCode, String month, String locationCode,String strDDOCode) {
		// TODO Auto-generated method stub

		List empLst = null;

	
		StringBuilder  Strbld = new StringBuilder();

		 
		   Strbld.append(" select abc.ddo_reg_no,cast(sum(abc.employee) as VARCHAR(20)) ||'#'|| cast(sum(abc.DED_ADJUST) as VARCHAR(20)) from ");
			Strbld.append(" (select a.emp_amt-cast(nvl(b.sd_amnt,0) as double) as employee,a.DED_ADJUST-cast(nvl(b.sd_amnt,0) as double) as DED_ADJUST,a.ddo_reg_no ,a.pran_no from ");
			Strbld.append(" (SELECT emp.PRAN_NO,sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR)  as emp_amt,"
					+ " sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,dto.ddo_reg_no FROM mst_dcps_emp  ");
			Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
			Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
			Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MPG consMpg on consMpg.PAYBILL_ID = head.PAYBILL_ID  ");
			Strbld.append(" inner join CONSOLIDATED_BILL_MST consMst on consMst.CONS_BILL_ID = consMpg.CONS_BILL_ID  ");
			Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =ddo.DDO_CODE    ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE    ");
			Strbld.append(" inner join MST_DTO_REG dto on dto.ddo_code=zp.zp_DDO_CODE   ");
	        if(locationCode!=null && locationCode.equalsIgnoreCase("2222"))
	        {
	        	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3301' ");
	        }
	        else
	        {
	        	Strbld.append("  and substr(dto.ddo_code,2,4) <> '3333' ");
	        }		
			Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=ddo2.LOCATION_CODE ");
			Strbld.append(" where zp.REPT_DDO_CODE='"+strDDOCode+"' and head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+"  and substr(ddo2.ddo_code,1,4)='"+locationCode+"'  "); 
			Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10 and consMst.STATUS = 1 and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' "); 
			Strbld.append(" group by emp.PRAN_NO,dto.ddo_reg_no   ");
			Strbld.append("  ) a left outer join ");  
			Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd ");
			Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
			Strbld.append(" and bh.YEAR='"+yrCode+"' and bh.MONTH="+month+"  and bh.file_name like '"+locationCode+"%'  group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ,sd.ddo_reg_no ) b ");
			Strbld.append(" on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no  where a.emp_amt-cast(nvl(b.sd_amnt,0) as double) > 0 and cast(a.DED_ADJUST-nvl(b.sd_amnt,0) as double) >0) abc group by abc.ddo_reg_no order by abc.ddo_reg_no ");
			
			logger.info("  getEmployeeListDdoregNsdlMap ---------"+Strbld.toString() );
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("script for all employeewww ---------"+lQuery.toString() );
		 	empLst = lQuery.list();
		 	Map<String,String> empCountriDdoregMap=new HashMap();//Creating HashMap
		 	for(int i=0;i<empLst.size();i++) {
				Object[] tuple = (Object[]) empLst.get(i);
				empCountriDdoregMap.put(tuple[0].toString(),tuple[1].toString()); 
			}
			/*String [] empDdoLst = new String[lQuery.list().size()];
			for(int i=0;i<empLst.size();i++)
				empDdoLst[i]=empLst.get(i).toString();
	
			return empDdoLst;*/
		 	return empCountriDdoregMap;
	}

	/*DDO Conrtribution by map*/
	

	
	public String[] getEmployeeCountDdoregNsdlForDeputation(String yrCode, String month,
			String treasuryyno,String year,String treasuryDDOCode) {
		// TODO Auto-generated method stub

		List empLst = null;
		List lLstReturnList1 = null;
		List lLstReturnList2 = null;
		List finalList=null;

		String [] empCountLst= (String[])null;

		StringBuilder  sb = new StringBuilder();
		try{
			
		/*	sb.append(" SELECT  count( distinct mstemp.PRAN_NO) FROM MST_DCPS_EMP mstemp ");
			sb.append(" inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID ");
			sb.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE ");
			sb.append(" inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code  ");
			sb.append("left outer join (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,");
			sb.append(" bh.YEAR,bh.MONTH FROM NSDL_SD_DTLS sd inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
			sb.append(" and bh.YEAR="+year+" and bh.MONTH="+month+" and bh.file_name like '%"+treasuryyno+"%D' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=mstemp.pran_no ");
			sb.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y'  ");
			sb.append(" and mstemp.PRAN_NO is not null and PRAN_ACTIVE=1 and mstemp.DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' and cast(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0) as double) >0 ");
			
			sb.append(" group by reg.DDO_REG_NO order by   reg.DDO_REG_NO ");*/
			
			
			sb.append("  select count( distinct abc.PRAN_NO)   from ");
			sb.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
			sb.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
			sb.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
			sb.append(" and mstemp.DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' ");
			sb.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
			sb.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
			sb.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and bh.IS_LEGACY_DATA='N' and ");
			sb.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
			sb.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0 group by abc.DDO_REG_NO order by   abc.DDO_REG_NO  with ur");
			
			
		
			logger.info("query for count"+sb.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(sb.toString());


			empLst = lQuery.list();
			
		/*	StringBuilder  sb1 = new StringBuilder();
			sb1.append(" SELECT  count( distinct mstemp.PRAN_NO) FROM MST_DCPS_EMP mstemp ");
			sb1.append(" inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID ");
			sb1.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE='"+treasuryDDOCode+"' ");
			sb1.append(" inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code  ");
			sb1.append(" left outer join (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,");
			sb1.append(" bh.YEAR,bh.MONTH FROM NSDL_SD_DTLS sd inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
			sb1.append(" and bh.YEAR="+year+" and bh.MONTH="+month+" and bh.file_name like '%"+treasuryyno+"%D' group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=mstemp.pran_no ");
			sb1.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y'  ");
			sb1.append(" and mstemp.PRAN_NO is not null and PRAN_ACTIVE=1 and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is null  and mstemp.DCPS_OR_GPF='Y' and cast(nvl(trn.CONTRIBUTION,0)-nvl(a.sd_amnt,0) as double) >0 ");
			
			sb1.append("  group by reg.DDO_REG_NO order by   reg.DDO_REG_NO ");
			*/
			
			StringBuilder  sb1 = new StringBuilder();
			sb1.append("  select count( distinct abc.PRAN_NO)   from ");
			sb1.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
			sb1.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE='"+treasuryDDOCode+"' inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
			sb1.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
			sb1.append(" and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is null and mstemp.DCPS_OR_GPF='Y' ");
			sb1.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
			sb1.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
			sb1.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and bh.IS_LEGACY_DATA='N'");
			sb1.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
			sb1.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0  group by abc.DDO_REG_NO order by   abc.DDO_REG_NO  with ur");
			
		
			logger.info("query for count"+sb1.toString());
			SQLQuery lQuery1 = ghibSession.createSQLQuery(sb1.toString());


			lLstReturnList1 = lQuery1.list();
			
		 
			
			StringBuilder  sb2 = new StringBuilder();
			sb2.append("  select count( distinct abc.PRAN_NO)   from ");
			sb2.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
			sb2.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE= mstemp.DEPT_DDO_CODE inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
			sb2.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
			sb2.append(" and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' ");
			sb2.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
			sb2.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
			sb2.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
			sb2.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
			sb2.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0 group by abc.DDO_REG_NO order by   abc.DDO_REG_NO   with ur");
			
			
			logger.info("query for count"+sb1.toString());
			SQLQuery lQuery2 = ghibSession.createSQLQuery(sb2.toString());


			lLstReturnList2 = lQuery2.list();
			
			
			
			
			
			int icount=empLst.size()+lLstReturnList2.size()+lLstReturnList1.size();
			empCountLst = new String[icount];
			int counter=0;
			if(empLst!=null && empLst.size()>0)
			{
				for(int i=0;i<empLst.size();i++)
				{
					empCountLst[counter]=empLst.get(i).toString();
					counter=counter+1;
				}
			}
			if(lLstReturnList1!=null && lLstReturnList1.size()>0)
			{
				for(int i=0;i<lLstReturnList1.size();i++)
				{
					empCountLst[counter]=lLstReturnList1.get(i).toString();
					counter=counter+1;
				}
			}
			if(lLstReturnList2!=null && lLstReturnList2.size()>0)
			{
				for(int i=0;i<lLstReturnList2.size();i++)
				{
					empCountLst[counter]=lLstReturnList2.get(i).toString();
					counter=counter+1;
				}
			}
		}
		catch(Exception e)
		{
			logger.info("Error occer in  getEmployeeList ---------"+ e);
		}
		return empCountLst;

	}

	//Query to be modified.
	public String[] getEmployeeListDdoregNsdl(String yrCode, String month, String locationCode,String strDDOCode) {
		// TODO Auto-generated method stub

		List empLst = null;

	
		StringBuilder  Strbld = new StringBuilder();

		 
		   Strbld.append(" select cast(sum(abc.employee) as VARCHAR(20)) ||'#'|| cast(sum(abc.DED_ADJUST) as VARCHAR(20)) from ");
			Strbld.append(" (select a.emp_amt-cast(nvl(b.sd_amnt,0) as double) as employee,a.DED_ADJUST-cast(nvl(b.sd_amnt,0) as double) as DED_ADJUST,a.ddo_reg_no ,a.pran_no from ");
			Strbld.append(" (select temp.ddo_reg_no as ddo_reg_no,temp.PRAN_NO as pran_no ,sum(temp.emp_amt) as emp_amt,sum(temp.DED_ADJUST) as DED_ADJUST from ( ");
			Strbld.append(" SELECT  TYPE_OF_PAYMENT,case when trn.TYPE_OF_PAYMENT in ('700046','700047','700048','700049') then sum(nvl(trn.CONTRIBUTION,0)) else 0 end as emp_amt, ");
			Strbld.append(" case when TYPE_OF_PAYMENT in ('700046') then sum(nvl(NPS_EMPLR_CONTRI_DED,0)) else 0 end as DED_ADJUST,mstemp.PRAN_NO,dto.ddo_reg_no");
			Strbld.append(" FROM ifms.TRN_DCPS_CONTRIBUTION as trn  ");
			Strbld.append(" left join ifms.MST_DCPS_EMP mstemp  on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID ");
			Strbld.append(" left join ifms.ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE ");
			Strbld.append(" left join ifms.PAYBILL_HEAD_MPG as billmpg on billmpg.BILL_NO=trn.BILL_GROUP_ID ");
			Strbld.append(" left join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=mstemp.DDO_CODE ");
			Strbld.append(" inner join ifms.MST_DTO_REG dto on dto.ddo_code=ddo.ddo_code ");	
			Strbld.append("  inner join ifms.SGVC_FIN_YEAR_MST as fin on fin.FIN_YEAR_ID=trn.FIN_YEAR_ID ");	
			Strbld.append(" where rlt.REPT_DDO_CODE='"+strDDOCode+"' and billmpg.PAYBILL_YEAR="+yrCode+" and billmpg.PAYBILL_MONTH="+month+" and fin.FIN_YEAR_CODE="+yrCode+" and trn.MONTH_ID="+month+" ");
			Strbld.append(" and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1 and mstemp.zp_STATUS=10  and billmpg.APPROVE_FLAG=1 and mstemp.dcps_or_gpf='Y'   ");
			Strbld.append(" and trn.VOUCHER_NO is not null and trn.VOUCHER_DATE is not null group by  TYPE_OF_PAYMENT , mstemp.pran_no,dto.ddo_reg_no");
			Strbld.append(" ) as temp group by temp.pran_no,temp.ddo_reg_no");		   
			
			/*Strbld.append(" (	SELECT sum(paybill.DCPS)+sum(paybill.DCPS_PAY)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_DA) + sum(JANJULGISARR) as emp_amt, sum(paybill.NPS_EMPLR_CONTRI_DED) as DED_ADJUST ,emp.PRAN_NO,dto.ddo_reg_no FROM mst_dcps_emp  ");
			Strbld.append(" emp inner join HR_EIS_EMP_MST eis on eis.EMP_MPG_ID=emp.ORG_EMP_MST_ID  ");
			Strbld.append(" inner join HR_PAY_PAYBILL paybill on paybill.EMP_ID=eis.EMP_ID  ");
			Strbld.append(" inner join PAYBILL_HEAD_MPG head on head.PAYBILL_ID=paybill.PAYBILL_GRP_ID  ");
			Strbld.append(" inner join ORG_DDO_MST ddo on ddo.LOCATION_CODE=paybill.LOC_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE   "); 
			Strbld.append(" inner join MST_DTO_REG dto on dto.ddo_code=ddo.ddo_code  ");  
	         if(locationCode!=null && locationCode.equalsIgnoreCase("2222"))
	         {
	         	Strbld.append("  and substr(dto.ddo_code,1,4) <> '2222' ");
	         }
	         else
	         {
	         	Strbld.append("  and substr(dto.ddo_code,1,4) <> '2222'");
	         } 
			Strbld.append(" where zp.REPT_DDO_CODE='"+strDDOCode+"' and head.PAYBILL_YEAR="+yrCode+" and head.PAYBILL_MONTH="+month+" ");
			 if(locationCode.equals("2222")) {
				 Strbld.append("  and substr(ddo.ddo_code,1,4)='2054'  ");
			 }else {
				 Strbld.append("  and substr(ddo.ddo_code,3,4)='"+locationCode+"'  ");
			 }
			 Strbld.append(" and emp.PRAN_NO is not null and emp.PRAN_ACTIVE=1 and emp.zp_STATUS=10  and head.APPROVE_FLAG=1 and emp.dcps_or_gpf='Y' group by emp.pran_no, dto.ddo_reg_no "); 
			*/
			Strbld.append("  ) a left outer join ");  
			Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd ");
			Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1 ");
			Strbld.append(" and bh.YEAR='"+yrCode+"' and bh.MONTH="+month+"  and bh.file_name like '"+locationCode+"%'  group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ,sd.ddo_reg_no ) b ");
			Strbld.append(" on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no  where a.emp_amt-cast(nvl(b.sd_amnt,0) as double) > 0 and cast(a.DED_ADJUST-nvl(b.sd_amnt,0) as double) >0) abc group by abc.ddo_reg_no order by abc.ddo_reg_no ");
			
			 
			/*Strbld.append(" select cast(sum(abc.employee) as VARCHAR(20)) ||'#'|| cast(sum(abc.DED_ADJUST) as VARCHAR(20)) from ");
			Strbld.append(" (select a.emp_amt-cast(nvl(b.sd_amnt,0) as double) as employee,a.DED_ADJUST-cast(nvl(b.sd_amnt,0) as double) as DED_ADJUST,a.ddo_reg_no ,a.pran_no from ");
			Strbld.append(" (SELECT sum(decimal(temp.CONTRIBUTION,16,2)) as emp_amt, sum(decimal(temp.DED_ADJUST,16,2)) as DED_ADJUST, temp.ddo_reg_no ,temp.pran_no FROM ( ");
			Strbld.append(" select trn.TYPE_OF_PAYMENT,case when trn.TYPE_OF_PAYMENT in ('700046','700047','700048','700049') then sum(nvl(trn.CONTRIBUTION,0)) else 0 end as CONTRIBUTION , "); 
			Strbld.append(" case when trn.TYPE_OF_PAYMENT in ('700046') then sum(nvl(trn.NPS_EMPLR_CONTRI_DED,0)) else 0 end as DED_ADJUST, ");
			Strbld.append(" trn.TYPE_OF_PAYMENT, mstemp.PRAN_NO,dto.ddo_reg_no FROM ifms.TRN_DCPS_CONTRIBUTION as trn left join ifms.MST_DCPS_EMP mstemp  on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID "); 
			Strbld.append(" inner  join ifms.ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE ");
			Strbld.append(" inner join ifms.PAYBILL_HEAD_MPG as billmpg on billmpg.BILL_NO=trn.BILL_GROUP_ID  ");
			Strbld.append(" inner join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=mstemp.DDO_CODE  ");
			Strbld.append(" inner join ifms.MST_DTO_REG dto on dto.ddo_code=ddo.ddo_code    ");
			Strbld.append(" inner join ifms.SGVC_FIN_YEAR_MST as fin on fin.FIN_YEAR_ID=trn.FIN_YEAR_ID ");
			Strbld.append(" where rlt.REPT_DDO_CODE='"+strDDOCode+"' and billmpg.PAYBILL_YEAR="+yrCode+" and fin.FIN_YEAR_CODE="+yrCode+" and trn.MONTH_ID="+month+"  and billmpg.PAYBILL_MONTH="+month+" ");
			 if(locationCode.equals("2222")) {
				 Strbld.append("  and substr(ddo.ddo_code,1,4)='2054'  ");
			 }else {
				 Strbld.append("  and substr(ddo.ddo_code,3,4)='"+locationCode+"'  ");
			 }
			Strbld.append(" and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1 and mstemp.zp_STATUS=10  and billmpg.APPROVE_FLAG=1  ");
			Strbld.append(" and billmpg.STATUS=4 and trn.VOUCHER_NO is not null ");
			Strbld.append(" and trn.VOUCHER_DATE is not null and billmpg.VOUCHER_NO is not null and billmpg.VOUCHER_DATE is not null ");
			Strbld.append(" and mstemp.dcps_or_gpf='Y'   group by mstemp.pran_no,dto.ddo_reg_no , trn.TYPE_OF_PAYMENT) as temp  ");
			Strbld.append(" group by temp.pran_no,temp.ddo_reg_no   ) a left outer join ");
			Strbld.append(" (SELECT sd.SD_PRAN_NO,cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,bh.YEAR,bh.MONTH,sd.ddo_reg_no FROM NSDL_SD_DTLS sd ");
			Strbld.append(" inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME and bh.STATUS <>-1  and   bh.IS_LEGACY_DATA='N'");
			Strbld.append(" and bh.YEAR='"+yrCode+"' and bh.MONTH="+month+"  and bh.file_name like '"+locationCode+"%'  group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ,sd.ddo_reg_no ) b ");
			Strbld.append(" on b.SD_PRAN_NO=a.PRAN_NO and b.ddo_reg_no=a.ddo_reg_no  where a.emp_amt-cast(nvl(b.sd_amnt,0) as double) > 0 ");
		 	Strbld.append(" and cast(a.DED_ADJUST-nvl(b.sd_amnt,0) as double) >0 ");
			Strbld.append(" ) abc group by abc.ddo_reg_no order by abc.ddo_reg_no  with ur");

*/ 
			logger.info("   ---------"+Strbld.toString() );
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("script for all employeewww ---------"+lQuery.toString() );
		 	empLst = lQuery.list();
			String [] empDdoLst = new String[lQuery.list().size()];
			for(int i=0;i<empLst.size();i++)
				empDdoLst[i]=empLst.get(i).toString();
	
			return empDdoLst;
	}
	
	public String[] getEmployeeListDdoregNsdlDeputation(String yrCode, String month,
			String treasuryyno,String year,String treasuryDDOCode) {
		// TODO Auto-generated method stub

		List empLst = null;
		List lLstReturnList1 = null;
		List lLstReturnList2 = null;
		
	
		StringBuilder  sb = new StringBuilder();


		sb.append("  select sum(abc.c-nvl(a.sd_amnt,0))   from ");
		sb.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
		sb.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE=mstemp.DDO_CODE inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
		sb.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
		sb.append(" and mstemp.DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' ");
		sb.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
		sb.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
		sb.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
		sb.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
		sb.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0  group by abc.DDO_REG_NO order by   abc.DDO_REG_NO  with ur");
	
		
		gLogger.info("Query is ***********getEmployeeContriTotalList***"+sb.toString());

		SQLQuery lQuery = ghibSession.createSQLQuery(sb.toString());


		empLst=lQuery.list();
		
		StringBuilder  sb1 = new StringBuilder();
		sb1.append("  select sum(abc.c-nvl(a.sd_amnt,0))   from ");
		sb1.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
		sb1.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE='"+treasuryDDOCode+"' inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
		sb1.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
		sb1.append(" and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is null and mstemp.DCPS_OR_GPF='Y' ");
		sb1.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
		sb1.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
		sb1.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
		sb1.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
		sb1.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0  group by abc.DDO_REG_NO order by   abc.DDO_REG_NO   with ur");
		

		
		gLogger.info("Query is ***********getEmployeeContriTotalList***"+sb.toString());

		SQLQuery lQuery1 = ghibSession.createSQLQuery(sb1.toString());


		lLstReturnList1=lQuery1.list();

		StringBuilder  sb2 = new StringBuilder();
		sb2.append("  select sum(abc.c-nvl(a.sd_amnt,0))   from ");
		sb2.append(" (select mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as c, cast(sum(nvl(trn.CONTRIBUTION,0) ) as double) as d, reg.ddo_reg_no ");
		sb2.append(" FROM MST_DCPS_EMP mstemp inner join TRN_DCPS_CONTRIBUTION trn on trn.DCPS_EMP_ID=mstemp.DCPS_EMP_ID inner join ORG_DDO_MST ddo on ddo.DDO_CODE= mstemp.DEPT_DDO_CODE inner join mst_ddo_reg reg on reg.ddo_code = ddo.ddo_code ");
		sb2.append(" where trn.FIN_YEAR_ID="+yrCode+" and trn.MONTH_ID="+month+"  and substr(trn.TREASURY_CODE,1,4)='"+treasuryyno+"' and trn.IS_CHALLAN='Y' and trn.STATUS='H' and trn.IS_DEPUTATION='Y' and mstemp.PRAN_NO is not null and mstemp.PRAN_ACTIVE=1  ");
		sb2.append(" and mstemp.DDO_CODE is null and mstemp.DEPT_DDO_CODE is not null and mstemp.DCPS_OR_GPF='Y' ");
		sb2.append(" group by mstemp.EMP_NAME, mstemp.DCPS_ID, mstemp.PRAN_NO, reg.ddo_reg_no) abc ");
		sb2.append(" left outer join (    SELECT    sd.SD_PRAN_NO,    cast(sum(nvl(sd.SD_EMP_AMOUNT,0)) as double) as sd_amnt ,    bh.YEAR,    bh.MONTH    FROM NSDL_SD_DTLS sd    inner join NSDL_BH_DTLS bh on bh.FILE_NAME=sd.FILE_NAME ");
		sb2.append(" and bh.STATUS <>-1    and bh.YEAR="+year+" and bh.MONTH="+month+" and ");
		sb2.append(" bh.file_name like '%"+treasuryyno+"%D'    group by sd.SD_PRAN_NO,bh.YEAR,bh.MONTH ) a on a.sd_pran_no=abc.pran_no ");
		sb2.append(" where  cast(abc.c-nvl(a.sd_amnt,0) as double) >0 group by abc.DDO_REG_NO order by   abc.DDO_REG_NO  with ur");
		
		
		gLogger.info("Query is ***********getEmployeeContriTotalList***"+sb.toString());

		SQLQuery lQuery2 = ghibSession.createSQLQuery(sb2.toString());


		lLstReturnList2=lQuery2.list();
		
				
		int icount=empLst.size()+lLstReturnList2.size()+lLstReturnList1.size();
		
		String [] empDdoLst = new String[icount];
		int counter=0;
		if(empLst!=null && empLst.size()>0)
		{
			for(int i=0;i<empLst.size();i++)
			{
				empDdoLst[counter]=empLst.get(i).toString();
				counter=counter+1;
			}
		}
		if(lLstReturnList1!=null && lLstReturnList1.size()>0)
		{
			for(int i=0;i<lLstReturnList1.size();i++)
			{
				empDdoLst[counter]=lLstReturnList1.get(i).toString();
				counter=counter+1;
			}
		}
		if(lLstReturnList2!=null && lLstReturnList2.size()>0)
		{
			for(int i=0;i<lLstReturnList2.size();i++)
			{
				empDdoLst[counter]=lLstReturnList2.get(i).toString();
				counter=counter+1;
			}
		}

		return empDdoLst;
	}

	public void insertBatchHeader(Long lLngPkIdForBh,String bhHeader1, String bhHeader,
			String bhHeader2, String string4, String string5,
			String currentdate, String string6, long ddoCount, int count,
			String govContri, String subContri, String total,String fileName, String yrCode, String month,String strDDOCode )   {

		final Session session = this.getSession();
		final StringBuffer str = new StringBuffer();
	 
		str.append("insert into ifms.NSDL_BH_dtls values ( '"+lLngPkIdForBh+"', '"+bhHeader1+"','"+bhHeader+"','"+bhHeader2+"','"+string4+"','"+string5+"','"+currentdate+"',");
		str.append("'"+string6+"','"+ddoCount+"','"+count+"','"+govContri+"','"+subContri+"','"+total+"','"+fileName+"','"+yrCode+"','"+month+"','"+0+"',null,0,null,null,null,null,null,'0','N','"+strDDOCode+"',sysdate,null,null,null,null,null,null)");
		final Query updateQuery = session.createSQLQuery(str.toString());
		logger.info("Query to insert in batch heaqder**********"+str.toString());

		updateQuery.executeUpdate();

	}



	public String getbatchIdCount(String batchIdPrefix) {
		String lLstReturnList = "";
		StringBuilder sb = new StringBuilder();
		logger.info("getbatchIdCount1");

		sb.append(" select count(1)+1 from ifms.NSDL_BH_dtls where FILE_NAME like '"+batchIdPrefix+"%'");
		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList=  selectQuery.uniqueResult().toString();
		logger.info("getbatchIdCount1"+lLstReturnList);

		return lLstReturnList;
	}
	
	
	
	
	


	public void insertDHDetails(Long lLngPkIdForDh,int i, String string, String string2,
			int j, String ddoRegNo, Long empCount,
			String totalEmplyDHContri, String totalEmplyerDHContri, String batchId) {

		final Session session = this.getSession();
		final StringBuffer str = new StringBuffer();
		str.append("insert into NSDL_DH_dtls values (	'"+lLngPkIdForDh+"',	'"+i+"','"+string+"','"+string2+"','"+j+"','"+ddoRegNo+"','"+empCount+"',");
		
		str.append("'"+totalEmplyDHContri+"','"+totalEmplyerDHContri+"','"+batchId+"',0,'N')");
		final Query updateQuery = session.createSQLQuery(str.toString());
		logger.info("Query to insert in insertDHDetails heaqder**********"+str.toString());

			updateQuery.executeUpdate();

	}



	public void insertSDDetails(Long lLngPkIdForSd, int i, String string, String string2,
			int j, int empCount, String pranno, String govEmpContri,
			String subempContri, String string3, String string4,
			String batchId, String string5, String ddoRegNo) {

		final Session session = this.getSession();
		final StringBuffer str = new StringBuffer();
		str.append("insert into NSDL_SD_dtls values ('"+lLngPkIdForSd+"',	'"+i+"','"+string+"','"+string2+"','"+j+"','"+empCount+"','"+pranno+"',");
		
		str.append("'"+govEmpContri+"','"+subempContri+"','"+string3+"','"+string4+"','"+batchId+"','"+string5+"','"+ddoRegNo+"',0,'N')");
		final Query updateQuery = session.createSQLQuery(str.toString());
		logger.info("Query to insert in insertSDDetails heaqder**********"+str.toString());

		updateQuery.executeUpdate();

	}



	public List getAllData(String yrCode, String month,String gLocId ) throws Exception {

		List contrList = null;
		Query lQuery = null;
		StringBuilder lSBQuery = null;
		try{
			lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT  bh.FILE_NAME,bh.BH_EMP_AMOUNT,bh.BH_EMPLR_AMOUNT,NVL(bh.TRANSACTION_ID,'-') , "
					+ "decode(bh.file_status,0,cast('File not Validated' as varchar(30)),1,cast('File is validated' as varchar(30)),2,cast('File is rejected' as varchar(30)),"
					+ "3,cast('File has been modified by User' as varchar(30)),5,cast('File has been sent to NSDL' as varchar(30)),11,cast('Transaction Id Updated' as varchar(30)),"
					+ "12,cast('Bill Lock' as varchar(30)),8,cast('Transaction Id Lapsed' as varchar(30))) ,bh.file_status,bh.BH_BATCH_FIX_ID,cast(nvl(bh.ERROR_DATA,'--') as varchar(30000)) "
					+ "  FROM ifms.NSDL_BH_DTLS bh     "); 
		    lSBQuery.append(" where bh.MONTH='" +month + "' and bh.YEAR= '" + yrCode + "' and bh.file_name like '" + gLocId  + "%' and bh.STATUS <> '-1'   ");
			lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("lQuery*	******is to get the list"+lQuery);
			contrList = lQuery.list(); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
			throw(e);
		}
		return contrList;
	}
	
	public List getAllDataDeputation(String yrCode, String month,
			String gLocId) throws Exception {


		List contrList = null;
		Query lQuery = null;
		StringBuilder lSBQuery = null;
		try{
			lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT distinct bh.FILE_NAME,bh.BH_EMP_AMOUNT,bh.BH_EMPLR_AMOUNT,NVL(cast(nsdl.BILL_ID as varchar(20)),'NA'),NVL(cast(nsdl.AUTH_NUMBER as varchar(20)),'NA') , " +
					"decode(nsdl.BILL_STATUS,0,'PayBill Generated',1,'Paybill Approved',2,'Bill Rejected by Beams',3,'Bill Authorized by BEAMS','Paybill Not Generated')  , NVL(bh.TRANSACTION_ID,'-') ,  " +
					" decode(bh.file_status,0,'File not Validated',1,'File is validated',2,'File is rejected',3,'File has been modified by User',5,'File has been sent to NSDL') ,nsdl.BILL_STATUS,bh.file_status,bh.BH_BATCH_FIX_ID "+
					" FROM NSDL_BH_DTLS bh   "); 
		      lSBQuery.append("  left outer join ifms.NSDL_BILL_DTLS nsdl on nsdl.FILE_NAME=bh.FILE_NAME  and nsdl.BILL_STATUS <> -1  where bh.MONTH='" + 
		    	        month + "' and bh.YEAR= '" + yrCode + "' " + 
		    	        "and bh.file_name like '" + gLocId + "%D' and bh.STATUS <> -1   ");

			lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("lQuery*******is to get the list"+lQuery);
			contrList = lQuery.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
			throw(e);
		}
		return contrList;
	}


	public List getBHDeatils(String fileNumber) {
		// TODO Auto-generated method stub
		return null;
	}



	public String getBatchData(String fileNumber) {

		String lLstReturnList = "";
		StringBuilder sb = new StringBuilder();


//		sb.append(" select SR_NO||'^'||HEADER_NAME||'^'||BH_NO||'^'||BH_COL2||'^'||BH_FIX_NO||'^'||BH_DATE||'^'||BH_BATCH_FIX_ID||'^^'||BH_DDO_COUNT||'^'||BH_PRAN_COUNT||'^'||BH_EMP_AMOUNT||'^'||BH_EMPLR_AMOUNT||'^^'||BH_TOTAL_AMT||'^' from NSDL_BH_dtls ");
		sb.append(" select SR_NO||'^'||HEADER_NAME||'^'||BH_NO||'^'||BH_COL2||'^'||BH_FIX_NO||'^'||BH_DATE||'^'||BH_BATCH_FIX_ID||'^^'||BH_DDO_COUNT||'^'||BH_PRAN_COUNT||'^'||BH_EMPLR_AMOUNT||'^'||BH_EMP_AMOUNT||'^^'||BH_TOTAL_AMT||'^' from NSDL_BH_dtls ");
		sb.append(" where FILE_NAME='"+fileNumber+"' ");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList=  selectQuery.uniqueResult().toString();


		return lLstReturnList;

	}



	public List getDHData(String fileNumber) {
		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		//sb.append(" SELECT SR_NO||'^'||HEADER_NAME||'^'||DH_NO||'^'||DH_COL2||'^'||DH_DDO_REG_NO||'^'||BH_SD_COUNT||'^'||DH_EMP_AMOUNT||'^'||DH_EMPLR_AMOUNT||'^^',DH_DDO_REG_NO FROM NSDL_DH_dtls ");
		sb.append(" SELECT SR_NO||'^'||HEADER_NAME||'^'||DH_NO||'^'||DH_COL2||'^'||DH_DDO_REG_NO||'^'||BH_SD_COUNT||'^'||DH_EMPLR_AMOUNT||'^'||DH_EMP_AMOUNT||'^^',DH_DDO_REG_NO FROM NSDL_DH_dtls ");
		sb.append(" where FILE_NAME='"+fileNumber+"' order by SR_NO asc");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList=  selectQuery.list();


		return lLstReturnList;


	}



	public List getSDDtls(String fileNumber, String ddoRegNo) {
		
		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		//sb.append(" SELECT SR_NO||'^'||HEADER_NAME||'^'||SD_NO||'^'||SD_NO_2||'^'||SD_NO_3||'^'||SD_PRAN_NO||'^'||SD_EMP_AMOUNT||'^'||SD_EMPLR_AMOUNT||'^'||'^'||SD_TOTAL_AMT||'^'||SD_STATUS||'^'||SD_REMARK||'^' FROM NSDL_SD_DTLS  ");
		sb.append(" SELECT SR_NO||'^'||HEADER_NAME||'^'||SD_NO||'^'||SD_NO_2||'^'||SD_NO_3||'^'||SD_PRAN_NO||'^'||SD_EMPLR_AMOUNT||'^'||SD_EMP_AMOUNT||'^'||'^'||SD_TOTAL_AMT||'^'||SD_STATUS||'^'||SD_REMARK||'^' FROM NSDL_SD_DTLS  ");
		sb.append(" where   FILE_NAME='"+fileNumber+"'and DDO_REG_NO='"+ddoRegNo+"' order by SR_NO asc ");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList=  selectQuery.list();


		return lLstReturnList;
	}



	public void deleteNsdlFile(String fileNumber) {
		logger.info("in deleteNsdlFile**********");
		StringBuilder sb = new StringBuilder();
		try{
		sb.append("  update NSDL_BH_DTLS set status=-1 where FILE_NAME='"+fileNumber+"' ");
		final Query updateQuery = ghibSession.createSQLQuery(sb.toString());
		
		logger.info("Query to delete in deleteNsdlFile heaqder**********"+sb.toString());

		updateQuery.executeUpdate();
		
		
		StringBuilder sb1 = new StringBuilder();
		sb1.append("  update NSDL_DH_DTLS set DH_status=-1 where FILE_NAME='"+fileNumber+"' ");
		final Query updateQuery1 = ghibSession.createSQLQuery(sb1.toString());
		
		logger.info("Query to delete in deleteNsdlFile heaqder**********"+sb1.toString());

		updateQuery1.executeUpdate();
		
		
		StringBuilder sb2 = new StringBuilder();
		sb2.append("  update NSDL_SD_DTLS set status=-1 where FILE_NAME='"+fileNumber+"' ");
		final Query updateQuery2 = ghibSession.createSQLQuery(sb2.toString());
		
		logger.info("Query to delete in deleteNsdlFile heaqder**********"+sb2.toString());

		updateQuery2.executeUpdate();
		}
		catch(Exception e)
		{
			logger.info("Error occured in deleteNsdlFile ---------" + e);
		}
		
	}



	public void updateVoucherEntry(String month, String year, String fileNumber, String voucherNo, String vouchedate) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("  update ifms.NSDL_BILL_dtls  set BILL_STATUS=1,VOUCHER_NO="+voucherNo+",VOUCHER_DATE='"+vouchedate+"' where FILE_NAME='"+fileNumber+"' ");
		final Query updateQuery = ghibSession.createSQLQuery(sb.toString());
		
		logger.info("Query to updateVoucherEntry**********"+sb.toString());

		updateQuery.executeUpdate();
		
	}



	public void createNSDLBillGenration(Long nsdl_paybill_pk, String year,
			String month, double employeeContribution, double employerContribution,
			double totalContribution, String fileNumber) {
		StringBuilder sb = new StringBuilder();
		sb.append("  INSERT INTO NSDL_BILL_dtls  VALUES ("+nsdl_paybill_pk+","+year+","+month+","+employeeContribution+","+employerContribution+","+totalContribution+",null,null,'0',sysdate,'"+fileNumber+"',null) ");
		final Query updateQuery = ghibSession.createSQLQuery(sb.toString());
		
		logger.info("Query to createNSDLBillGenration**********"+sb.toString());

		updateQuery.executeUpdate();
		
	}



	public String getBillStatus(String fileNumber) {
		List temp=null;
		String billStatus="";
		StringBuilder  Strbld = new StringBuilder();

		Strbld.append(" SELECT count(1) FROM NSDL_BILL_dtls where  FILE_NAME='"+fileNumber+"' and bill_status<>-1 ");   
		
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());

			billStatus=lQuery.list().get(0).toString();
	
		return billStatus;
	}



	public List getBillNoDate(String fileNumber) {
		List billDetails=null;
		Long billNo=0l;
		StringBuilder  Strbld = new StringBuilder();

		Strbld.append(" SELECT BILL_ID,to_char(BILL_GENERATION_DATE,'dd/mm/yyyy'),month(BILL_GENERATION_DATE),BILL_MONTH FROM ifms.NSDL_BILL_dtls where  FILE_NAME='"+fileNumber+"' ");   
		


		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


		billDetails=lQuery.list();
		//if(billDetails!=null && billDetails.size()>0){
			//billNo=Long.parseLong(temp.get(0).toString());
		//}

		return billDetails;
	}



	public void updateMD5hash(String crypt, String fileNumber) {
		StringBuilder sb = new StringBuilder();
		sb.append("  update NSDL_BH_DTLS set NSDL_FILE_HASH='"+crypt+"' where FILE_NAME='"+fileNumber+"' ");
		final Query updateQuery = ghibSession.createSQLQuery(sb.toString());
		
		logger.info("Query to delete in deleteNsdlFile heaqder**********"+sb.toString());

		updateQuery.executeUpdate();
		
	}



	public int checkForFileDtls(String crypt, String fileno) {
		List temp=null;
		int billStatus=3;
		StringBuilder  Strbld = new StringBuilder();
		Strbld.append(" SELECT FILE_NAME FROM NSDL_BH_DTLS where  FILE_NAME ='"+fileno+"' and NSDL_FILE_HASH='"+crypt+"'");   
		logger.info("Strbld is ***************"+Strbld.toString());
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		temp=lQuery.list();
		return temp.size();
	}



	public void updateFileStatus(int fileStatus, String fileno, String errorData) {
		StringBuilder sb = new StringBuilder();
		errorData=errorData.replace("'", "");
		sb.append("  update NSDL_BH_DTLS set file_status='"+fileStatus+"'  ");
		
		if(errorData!=null && !errorData.equals(""))
		sb.append(" , error_data='"+errorData+"' ");
		
		
		sb.append("   where FILE_NAME='"+fileno+"' ");
		final Query updateQuery = ghibSession.createSQLQuery(sb.toString());
		
		logger.info("Query to delete in deleteNsdlFile heaqder**********"+sb.toString());

		updateQuery.executeUpdate();
		
	}

	public String getTransactionId(String fileNumber) {
		List temp=null;
		String transactionId="";
			StringBuilder  Strbld = new StringBuilder();

			Strbld.append(" SELECT TRANSACTION_ID FROM NSDL_BH_DTLS where FILE_NAME='"+fileNumber+"' ");   
			


			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


			temp=lQuery.list();
			logger.info("temp size"+temp.size());
			if(temp!=null && temp.size()>0){
				if(temp.get(0)!=null){
					transactionId=temp.get(0).toString();
			}
			}

			return transactionId;
}
	public String getTreasuryName(String billNo) {
		List temp=null;
	String treasuryName="";
		StringBuilder  Strbld = new StringBuilder();

		Strbld.append(" SELECT LOC_NAME FROM CMN_LOCATION_MST where LOC_ID ='"+billNo+"' ");   
		


		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


		temp=lQuery.list();
		logger.info("temp size"+temp.size());
		if(temp!=null && temp.size()>0){
			if(temp.get(0)!=null){
				treasuryName=temp.get(0).toString();
		}
		}

		return treasuryName;
	}



	public String getErrorData(String fileNumber) {

		List temp=null;
		String data="";
			StringBuilder  Strbld = new StringBuilder();

			Strbld.append(" SELECT cast(error_data as varchar(7000)) FROM NSDL_BH_DTLS where FILE_NAME='"+fileNumber+"' ");   
			


			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("Query to getErrorData in  heaqder**********"+Strbld.toString());

			temp=lQuery.list();
			logger.info("temp size"+temp.size());
			if(temp!=null && temp.size()>0){
				if(temp.get(0)!=null){
					data=temp.get(0).toString();
			}
			}

			return data;

	}



	public List getAllSubTreasury(String treasuryId) {
		List<ComboValuesVO> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT LOC.LOC_ID,LOC.LOC_NAME FROM CMN_LOCATION_MST loc inner join ORG_DDO_MST org on substr(org.DDO_CODE,1,2)||'01'=loc.LOC_ID where org.LOCATION_CODE=:loc_id  AND LOC.DEPARTMENT_ID=100003 ");
		gLogger.info("query to select sub treasury from treasury code:::" + sb);
		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		gLogger.info("sql query created");
		selectQuery.setParameter("loc_id", treasuryId);	
		

		lLstReturnList = new ArrayList<ComboValuesVO>();

		List lLstResult = selectQuery.list();
		gLogger.info("list size:" +lLstResult.size());

		ComboValuesVO lObjComboValuesVO = new ComboValuesVO();
		lObjComboValuesVO.setId("-1");
		lObjComboValuesVO.setDesc("--Select--");
		lLstReturnList.add(lObjComboValuesVO);
		if (lLstResult != null && lLstResult.size() != 0) {
			Object obj[];
			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());
				String desc=obj[1].toString();
				lObjComboValuesVO.setDesc(desc);
				lLstReturnList.add(lObjComboValuesVO);
			}
		} else {
			lLstReturnList = new ArrayList<ComboValuesVO>();
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("--Select--");
			lLstReturnList.add(lObjComboValuesVO);
		}

		return lLstReturnList;
		
	}
	
	
	
	public String getDtoRegNo(String strDDOCode) {

		List temp=null;
		String data="";
			StringBuilder  Strbld = new StringBuilder();
			
			if(!strDDOCode.equals("22223")) {
			 Strbld.append(" SELECT distinct DTO_REG_NO FROM MST_DTO_REG as dto inner join   ifms.RLT_ZP_DDO_MAP as rlt on dto.DDO_CODE=rlt.ZP_DDO_CODE  where rlt.REPT_DDO_CODE='"+strDDOCode+"' ");   
			}else {
				Strbld.append(" SELECT distinct DTO_REG_NO FROM MST_DTO_REG where substr(ddo_code,1,4) =2054" );
			}


			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("Query to getErrorData in  heaqder**********"+Strbld.toString());

			temp=lQuery.list();
			logger.info("temp size"+temp.size());
			if(temp!=null && temp.size()>0){
				if(temp.get(0)!=null){
					data=temp.get(0).toString();
			}
			}

			return data;

	}



	public int getMonthId(String fileNumber) {
		List temp=null;
		int transactionId=0;
			StringBuilder  Strbld = new StringBuilder();

			Strbld.append(" SELECT month FROM NSDL_BH_DTLS where FILE_NAME='"+fileNumber+"' ");   
			


			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


			temp=lQuery.list();
			logger.info("temp size"+temp.size());
			if(temp!=null && temp.size()>0){
				if(temp.get(0)!=null){
					transactionId=Integer.parseInt(temp.get(0).toString());
			}
			}

			return transactionId;
	}
	public List getBillId(String fileNumber, long intMonth, long intYear) {
		List billDetails=null;
		Long billNo=0l;
		StringBuilder  Strbld = new StringBuilder();

		Strbld.append(" SELECT BILL_ID,BILL_GENERATION_DATE FROM NSDL_BILL_dtls where FILE_NAME  = '"+fileNumber+"' and BILL_YEAR = "+intYear+" and BILL_MONTH = "+intMonth+"  and BILL_STATUS = 0 ");	


		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());

		billDetails=lQuery.list();		

		return billDetails;	}



	public int getCount(long intMonth, long intYear, long paybillId) {

	    Session hibSession = getSession();
	    int count = 0;
	    StringBuffer sb = new StringBuffer();
	    sb.append(" SELECT count(sd.SR_NO) FROM NSDL_SD_DTLS sd inner join NSDL_BH_DTLS bh on bh.FILE_NAME = sd.FILE_NAME ");
	    sb.append(" inner join NSDL_BILL_DTLS bill on bill.FILE_NAME = bh.FILE_NAME  ");
	    sb.append(" where bh.FILE_STATUS = 5 and bh.STATUS = 0 and bill.BILL_STATUS = 0 and bill.BILL_MONTH = "+intMonth+" and bill.BILL_YEAR = "+intYear+" and bill.BILL_ID = "+paybillId+" ");

	    Query qry = hibSession.createSQLQuery(sb.toString());
	    count = ((Integer)qry.uniqueResult()).intValue();

	    return count;
	  
	}
	public long getGrossAmount(long intMonth, long intYear, long paybillId) {

		List sev = null;
		Long grossAmount =0l;
		Double grossValue =0.0;
		
		try{
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append( " SELECT bh.BH_TOTAL_AMT FROM NSDL_BH_DTLS bh inner join NSDL_BILL_DTLS bill on bill.FILE_NAME = bh.FILE_NAME " ); 
		lSBQuery.append( "where bh.FILE_STATUS = 5 and bh.STATUS in (0,1) and bill.BILL_STATUS = 0 and bill.BILL_MONTH = "+intMonth+" and bill.BILL_YEAR = "+intYear+" and bill.BILL_ID = "+paybillId+" " ); 		
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		
		logger.info("lQuery getGrossAmount:"+lQuery);
		sev = lQuery.list();
		
		if(!sev.isEmpty() && sev.size()>0 && sev.get(0)!=null)
			grossValue = Double.parseDouble(sev.get(0).toString());
		grossAmount = grossValue.longValue();
		}
		catch(Exception e){
			logger.error("Exception in getFinYearId of LNALedgerQueryDAOImpl: " , e);
		}
		return grossAmount;
	}



	public void updateNsdlBillDetails(String authNo, long paybillId, String flag) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		if(flag.equals("Y"))
		str.append("update NSDL_BILL_DTLS set AUTH_NUMBER = '"+authNo+"',BILL_STATUS = 3 where BILL_ID = "+paybillId+" ");
		else
		str.append("update NSDL_BILL_DTLS set BILL_STATUS = 2 where BILL_ID = "+paybillId+" ");
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.executeUpdate();
	}



	

//added for paybill rejected by beams
	public void updateBillStatus(String billId, String fileNameTODelete) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update NSDL_BILL_DTLS set BILL_STATUS = -1,FILE_NAME = '"+fileNameTODelete+"' where BILL_ID = "+billId+" and BILL_STATUS = 2 ");
		
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.executeUpdate();
	}



 	public List getTreasuryDdoCode(long longLoggedInLocation) {
		List temp=null;
		String ddoCode="";
			StringBuilder  Strbld = new StringBuilder();

			Strbld.append(" select DDO_CODE,PYAMENT_MODE from MST_TREASURY_DDOCODE_MPG where LOC_ID ="+longLoggedInLocation+" and ACTIVE_FLAG = 1 ");   
			


			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


			temp=lQuery.list();
			
			
			

			return temp;
	}
 
	public String getContribType(String dcpsID) {
		List temp=null;
		String contribType="";
		try
		{
			StringBuilder  Strbld = new StringBuilder();

			Strbld.append(" select NSDL_CONTRIB_TYPE from mst_dcps_emp where DCPS_ID ='"+dcpsID+"' ");   
			/*Strbld.append(" select 0 from mst_dcps_emp where DCPS_ID ='"+dcpsID+"' ");*/


			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


			temp=lQuery.list();
			if(temp!=null && temp.size()>0)
			{
				contribType=temp.get(0).toString();
			}
		
			
		}
		catch(Exception e){
			logger.error("Exception in getContribType: " , e);
			e.printStackTrace();
		}	
			

			return contribType;
	}
	
	
	public List cehckIfTreasuryDdoCode(long ddoCode) {
		List temp=null;
		
			StringBuilder  Strbld = new StringBuilder();

			Strbld.append(" select DDO_CODE from MST_TREASURY_DDOCODE_MPG where DDO_CODE ="+ddoCode+" and ACTIVE_FLAG = 1 ");   
			


			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


			temp=lQuery.list();
			
			
			

			return temp;
	}

	public String getLocId(String treasuryyno) {
		List temp=null;
		String locId="";
			StringBuilder  Strbld = new StringBuilder();

			Strbld.append(" SELECT substr(DDO_CODE,1,4) FROM ORG_DDO_MST where LOCATION_CODE ="+treasuryyno);   
		
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());


			temp=lQuery.list();
			logger.info("temp size"+temp.size());
			if(temp!=null && temp.size()>0){
				if(temp.get(0)!=null){
					locId=temp.get(0).toString();
			}
			}

			return locId;
}
	
	public void updateFileName (String fileName,List billId) {
		Session hibSession =getSession();
		StringBuffer str = new StringBuffer();	
		
		str.append("update CONSOLIDATED_BILL_MST set FILE_NAME = '"+fileName +"' where CONS_BILL_ID in ( :billIds ) ");
		
		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.setParameterList("billIds", billId);
		logger.info("Query to updateFileName**********"+query1.toString());
		query1.executeUpdate();
	}



	public int getNPSFileContriSeq(String ddoCode) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		int NPSFILECONTRISEQLst = 0;
		//synchronized due to overlapping sequence
			//	synchronized(this) {
		strQuery.append(" select  NPS_FILECONTRI_SEQ from  ifms.NPS_FILECONTRI_SEQ where DDO_CODE='"+ddoCode.trim()+"'");
		logger.info("Query to get NPS_ACKNO_SEQ ---- " + strQuery.toString());
		
			Query query = hibSession.createSQLQuery(strQuery.toString());
			if(query.list().size()>0) {
				
			NPSFILECONTRISEQLst = (int) query.list().get(0);
				}
	//	}
		logger.info("NPSFILECONTRISEQLst is " + NPSFILECONTRISEQLst);
		return NPSFILECONTRISEQLst;
		
	}
	public void updateNPSFileContriSeq(String ddoCode) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//synchronized due to overlapping sequence
		//synchronized(this) {
		
		
		strQuery.append(" update ifms.NPS_FILECONTRI_SEQ set NPS_FILECONTRI_SEQ= NPS_FILECONTRI_SEQ+1 ");  
		strQuery.append(" where DDO_CODE='"+ddoCode.trim()+"'"); 
		Query query = hibSession.createSQLQuery(strQuery.toString());
	 	query.executeUpdate();
			//	}
	 	//hibSession.close();
	}
	
	public void insertNPSFileContriSeq(String ddoCode) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//synchronized due to overlapping sequence
		synchronized(this) {
		
				strQuery.append(" insert into ifms.NPS_FILECONTRI_SEQ ");
				strQuery.append(" (DDO_CODE,NPS_FILECONTRI_SEQ) ");
				strQuery.append(" values(:ddoCode,:NPS_FILECONTRI_SEQ)");
			
				Query query = hibSession.createSQLQuery(strQuery.toString());
				query.setParameter("ddoCode",ddoCode);	
				query.setParameter("NPS_FILECONTRI_SEQ",1); 
				query.executeUpdate();
			}
	}
	
	

public void updateTransactionId(String transactioId, String bhID) {
	// TODO Auto-generated method stub
	Session hibSession =getSession();
	StringBuffer str2 = new StringBuffer();
	str2.append("update NSDL_BH_DTLS set TRANSACTION_ID = '"+transactioId+"',FILE_STATUS = 11 ,Challan_received_created_date=sysdate where BH_BATCH_FIX_ID = '"+bhID+"'  and IS_LEGACY_DATA='N'");
	logger.error("NSDL_BH_DTLS------"+str2.toString());
	Query query3 = hibSession.createSQLQuery(str2.toString());
	query3.executeUpdate();
}



public int checkNPSRegularFileExistsOrNot(String fileNo,String BhID,String contriType) {
	List temp=null;
	int regCount=0;
	StringBuilder  Strbld = new StringBuilder();

	Strbld.append("SELECT count(*)  FROM ifms.NSDL_BH_DTLS where FILE_NAME = '"+fileNo+"' and BH_BATCH_FIX_ID = '"+BhID+"' and  STATUS = '5' and FILE_STATUS= '11' and IS_LEGACY_DATA='"+contriType+"'");
	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	temp=lQuery.list();
	logger.info("temp size"+temp.size());
	if(temp!=null && temp.size()>0){
		regCount=(int) temp.get(0);
		logger.info("regCount+++++++++ "+regCount);

	}

	return regCount;
	
} 
public void updatebankReftls(String fileNumber, String bhid, String month, String year,
		String bankRefNo,  String batch_id,String contriType) {
	
	try {
        StringBuilder lSBQuery = new StringBuilder();
        lSBQuery.append("update ifms.NSDL_BH_DTLS set BANK_REFNO =:bankRefNo,FILE_STATUS='12' where FILE_NAME = :fileNumber");
        Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
        lQuery.setParameter("fileNumber", fileNumber);
        lQuery.setParameter("bankRefNo", bankRefNo);
        lQuery.executeUpdate();
    } catch (Exception e) {
        logger.error(" Error is updateVoucherOrBDSDtls: " + e, e);
    }
	
}
 public int getCountOfNpsBillNotLocked(String ddoCode, int currentmonth, int currentyear) {
    int count = 0;
    long previousyear = currentyear - 1L;
 
    try {
       Session hibSession = this.getSession();
       StringBuilder strQuery = new StringBuilder();
       strQuery.append(" SELECT  count(*) FROM ifms.Nsdl_Bh_Dtls as  bh where bh.file_Status in(1,11,5)  and bh.ddo_Code='"+ddoCode+"' ");
       strQuery.append(" and ((bh.year = '"+currentyear+"' and bh.month < '"+currentmonth+"') or (bh.year < '"+currentyear+"' )) and bh.status <> -1 and bh.is_Legacy_Data='N' ");
       this.logger.error("Query getCountOfNpsBillNotLocked: " + strQuery.toString());
       Query query = hibSession.createSQLQuery(strQuery.toString());
       count = Integer.parseInt(query.uniqueResult().toString());
    } catch (Exception var10) {
       var10.printStackTrace();
    }

    return count;
 

}
 /*
public int getCountOfNpsBillNotLocked(String ddoCode, int currentmonth, int currentyear) {
    int count = 0;
    long previousyear = currentyear - 1L;
 
    try {
       Session hibSession = this.getSession();
       StringBuilder strQuery = new StringBuilder();
       strQuery.append(" SELECT  count(*) FROM NsdlBhDtls as  bh where bh.fileStatus in(1,11,5)  and bh.ddoCode='"+ddoCode+"' ");
       strQuery.append(" and ((bh.year = '"+currentyear+"' and bh.month < '"+currentmonth+"') or (bh.year <= '"+currentyear+"' )) and bh.status <> -1 and bh.isLegacyData='N' ");
       this.logger.error("Query getCountOfNpsBillNotLocked: " + strQuery.toString());
       Query query = hibSession.createSQLQuery(strQuery.toString());
       count = Integer.parseInt(query.uniqueResult().toString());
    } catch (Exception var10) {
       var10.printStackTrace();
    }

    return count;
 

}*/

public void getNsdlBh_details(String fileNumber, String bhid, String month, String year,
		String bankRefNo,  String batch_id,String contriType) {
	
	try {
        StringBuilder lSBQuery = new StringBuilder();
        
        ghibSession.get(NsdlBhDtls.class, 1212);
        
        Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
        lQuery.setParameter("fileNumber", fileNumber);
        lQuery.setParameter("bankRefNo", bankRefNo);
        lQuery.executeUpdate();
    } catch (Exception e) {
        logger.error(" Error is updateVoucherOrBDSDtls: " + e, e);
    }
}
}