package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ForwardEmpStatisticsDAOImpl extends GenericDaoHibernateImpl implements ForwardEmpStatisticsDAO{

	Logger logger = Logger.getLogger(ForwardEmpStatisticsDAOImpl.class );
	Session hibSession=null;
	public ForwardEmpStatisticsDAOImpl(Class<ForwardEmpStatisticsDAOImpl> class1,
			SessionFactory sessionFactory) {	
		super(class1);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public String getDDOCodeByLocId(String locId){
		String ddoCode="";
		StringBuffer str= new StringBuffer();
		str.append("select ddo_code from org_ddo_mst where location_code='"+locId+"'");
		logger.info("getFwdEmpList DAO------"+str.toString());
		Query query= hibSession.createSQLQuery(str.toString());
		ddoCode=query.uniqueResult().toString();
		logger.info("ddoCode..."+ddoCode);
		return ddoCode;
	}
	@Override
	public List getFwdEmpList(String ddoCode) {
		
		List totalFwdEmpConf=null;
		StringBuffer str= new StringBuffer();
		
		//commented by vaibhav tyagi
		/*str.append("select dcps.EMP_NAME, dcps.DOB, dcps.TYPE_EMP, dcps.EMP_GROUP, dcps.DOJ,pd.POST_NAME,pm.START_DATE,PM.END_DATE,look.LOOKUP_NAME, dcps.BASIC_PAY,scale.SCALE_DESC, pay.PF_SERIES_DESC, pay.PHY_CHALLANGED, bank.BANK_NAME, branch.BRANCH_NAME, dcps.BANK_ACNT_NO from mst_dcps_emp dcps ");
		str.append("left outer join MST_BANK_PAY bank on dcps.BANK_NAME=bank.BANK_CODE ");
		str.append("left outer join RLT_BANK_BRANCH_PAY branch on dcps.BRANCH_NAME= branch.BRANCH_CODE ");
		str.append("left outer join RLT_DCPS_PAYROLL_EMP pay on dcps.DCPS_EMP_ID=pay.DCPS_EMP_ID ");
		str.append("left outer join ORG_POST_DETAILS_RLT pd on pd.post_id= pay.post_id ");
		str.append("left outer join ORG_POST_MST pm on pm.POST_ID=pd.POST_ID ");
		str.append("left outer join CMN_LOOKUP_MST look on look.LOOKUP_ID=pm.POST_TYPE_LOOKUP_ID ");
		str.append("left outer join HR_EIS_SCALE_MST scale on scale.SCALE_ID=dcps.PAYSCALE ");
		str.append("where dcps.DDO_CODE="+ddoCode+" and dcps.REG_STATUS=0 and dcps.FORM_STATUS=1 ");*/
		
		//added by vaibhav tyagi: start
		str.append("select dcps.UID_NO,dcps.EID_NO, dcps.EMP_NAME, dcps.DOB, dcps.GENDER, dcps.DOJ, pay.PHY_CHALLANGED, ");
		str.append("dcps.PAN_NO, cadre.CADRE_NAME, dcps.SUPER_ANN_DATE,lookup.LOOKUP_NAME,scale.SCALE_DESC, ");
		str.append("dcps.PAY_IN_PAY_BAND,dcps.GRADE_PAY, dcps.BASIC_PAY,pd.POST_NAME||subpost.SUBJECT_NAME,pm.START_DATE,PM.END_DATE, ");
		str.append("off.OFF_NAME,office.APPOINTMENT_DATE,office.FIRST_DESIGNATION,dcps.INDIVISUAL_NO,dcps.INDIVISUAL_DATE, ");
		str.append("dcps.TYPE_EMP,bank.BANK_NAME, branch.BRANCH_NAME, dcps.BANK_ACNT_NO,branch.MICR_CODE,lookupdcps.LOOKUP_NAME new, ");
		str.append("pay.PF_ACNO,pay.GIS_MEMBERSHIP_DATE,nmn.DCPS_NMN_NAME,nmn.DCPS_NMN_DOB,nmn.dcps_nmn_rlt, ");
		str.append("nmn.DCPS_NMN_SHARE from mst_dcps_emp dcps ");
		str.append("left outer join MST_BANK_PAY bank on dcps.BANK_NAME=bank.BANK_CODE ");
		str.append("left outer join RLT_BANK_BRANCH_PAY branch on dcps.BRANCH_NAME= branch.BRANCH_CODE ");
		str.append("left outer join RLT_DCPS_PAYROLL_EMP pay on dcps.DCPS_EMP_ID=pay.DCPS_EMP_ID ");
		str.append("left outer join HST_DCPS_OFFICE_CHANGES office on office.DCPS_EMP_ID= dcps.DCPS_EMP_ID ");
		str.append("left outer join ORG_POST_DETAILS_RLT pd on pd.post_id= pay.post_id ");
		str.append("left outer join FRM_SUB_POSTID_MPG subpost on subpost.POST_ID = pd.POST_ID ");
		str.append("left outer join ORG_POST_MST pm on pm.POST_ID=pd.POST_ID ");
		str.append("left outer join CMN_LOOKUP_MST lookup on lookup.LOOKUP_ID= dcps.PAY_COMMISSION ");
		str.append("left outer join CMN_LOOKUP_MST lookupdcps on lookupdcps.LOOKUP_ID= dcps.AC_DCPS_MAINTAINED_BY ");
		str.append("left outer join MST_DCPS_CADRE cadre on cadre.CADRE_ID= dcps.CADRE ");
		str.append("left outer join HR_EIS_SCALE_MST scale on scale.SCALE_ID=dcps.PAYSCALE ");
		str.append("left outer join MST_DCPS_EMP_NMN nmn on nmn.DCPS_EMP_ID =dcps.DCPS_EMP_ID ");
		str.append("left outer join HR_PAY_OFFICEPOST_MPG offpost on offpost.POST_ID=pay.POST_ID ");
		str.append("left outer join MST_DCPS_DDO_OFFICE off on offpost.OFFICE_ID=off.DCPS_DDO_OFFICE_MST_ID ");
		str.append("where dcps.DDO_CODE='"+ddoCode+"' and dcps.REG_STATUS=0 and dcps.FORM_STATUS=0 ");
		//added by vaibhav tyagi end
		str.append("order by dcps.EMP_NAME");
		logger.info("getFwdEmpList DAO------"+str.toString());
		Query query= hibSession.createSQLQuery(str.toString());
		
		if((query.list()!=null)){
			totalFwdEmpConf= query.list();
		}
		
		logger.info("totalEmpConf size: "+totalFwdEmpConf.size());
		return totalFwdEmpConf;
	}

}