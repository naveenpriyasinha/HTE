package com.tcs.sgv.eis.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.CMPRecord;
import com.tcs.sgv.eis.valueobject.HrPayCmpDtls;

@Transactional
public class BillDetailsDAOImpl extends GenericDaoHibernateImpl implements BillDetailsDAO
{
    Logger logger = Logger.getLogger(BillDetailsDAOImpl.class);
    Session hibSession = null;

    public BillDetailsDAOImpl(final Class class1, final SessionFactory sessionFactory)
    {
        super(class1);
        this.setSessionFactory(sessionFactory);
        this.hibSession = this.getSession();

    }

    public List getCommonDetails(final String authNo)
    {

        final Session session = this.getSession();
        List commonDtls = null;
        final StringBuffer str = new StringBuffer();
        str
                .append("select distinct rlt.LOCATION_CODE,map.rept_ddo_code,bill.scheme_code, VARCHAR_format(head.UPDATED_DATE,'DD/MM/YYYY') as Bill_Date,");
        str
                .append("cons.NET_AMT, paybill.id,cons.CONS_BILL_ID||'-'||consBillMpg.PAYBILL_ID as DDO_BILL_NUMBER,cons.auth_number,emp.EMP_NAME,emp.bank_acnt_no,emp.IFSC_CODE, ");
        str
                .append("paybill.NET_TOTAL,emp.uid_no,emp.uid_seeded,emp.EMAIL_ID,emp.CELL_NO,bill.ddo_code from consolidated_bill_mst cons ");
        str.append("inner join CONSOLIDATED_BILL_MPG consBillMpg on cons.CONS_BILL_ID=consBillMpg.CONS_BILL_ID ");
        str.append("INNER join  paybill_head_mpg head  on head.PAYBILL_ID=consBillMpg.PAYBILL_ID ");
        str.append("inner join HR_PAY_PAYBILL paybill on paybill.PAYBILL_GRP_ID=head.PAYBILL_ID ");
        str.append("inner join HR_EIS_EMP_MST eis on eis.emp_id= paybill.EMP_ID and paybill.EMP_ID is not null ");
        str.append("inner join MST_DCPS_EMP emp on emp.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
        str.append("inner join MST_DCPS_BILL_GROUP bill on head.bill_no=bill.bill_group_id ");
        str.append("inner join RLT_ZP_DDO_MAP map on bill.ddo_code=map.ZP_DDO_CODE ");
        str.append("inner join RLT_DDO_ORG rlt on rlt.DDO_CODE=bill.DDO_CODE ");
        str.append("where cons.AUTH_NUMBER = :AuthNo ");
        str.append("order by bill.ddo_code ");
        this.logger.info("getTotalEmpConfig DAO------" + str.toString());
        final Query query = session.createSQLQuery(str.toString());
        query.setString("AuthNo", authNo);
        if (query.list() != null)
        {
            commonDtls = query.list();
        }
        this.logger.info("totalEmpConf size: " + commonDtls.size());
        return commonDtls;
    }

    public List getCommonDetailsforUID(final String AuthNo)
    {

        final Session session = this.getSession();
        List commonDtls = null;
        final StringBuffer str = new StringBuffer();
        str
                .append("select distinct rlt.LOCATION_CODE,bill.ddo_code,bill.scheme_code, VARCHAR_format(head.UPDATED_DATE,'DD/MM/YYYY') as Bill_Date,");
        str
                .append("head.bill_net_amount, emp.EMP_NAME,emp.uid_no,emp.IFSC_CODE,paybill.NET_TOTAL from paybill_head_mpg head ");
        str.append("inner join HR_PAY_PAYBILL paybill on paybill.PAYBILL_GRP_ID=head.PAYBILL_ID ");
        str.append("inner join HR_EIS_EMP_MST eis on eis.emp_id= paybill.EMP_ID and paybill.EMP_ID is not null ");
        str.append("inner join MST_DCPS_EMP emp on emp.ORG_EMP_MST_ID=eis.EMP_MPG_ID ");
        str.append("inner join MST_DCPS_BILL_GROUP bill on head.bill_no=bill.bill_group_id ");
        str.append("inner join RLT_DDO_ORG rlt on rlt.DDO_CODE=bill.DDO_CODE ");
        str.append("where head.AUTH_NO =" + AuthNo + "");
        this.logger.info("getCommonDetailsforUID DAO------" + str.toString());
        final Query query = session.createSQLQuery(str.toString());

        if (query.list() != null)
        {
            commonDtls = query.list();
        }

        this.logger.info("totalEmpConf size: " + commonDtls.size());
        return commonDtls;
    }

    public List getDdoDtls(final String ddoCode)
    {
        final Session session = this.getSession();
        List ddoDtls = null;
        final StringBuffer str = new StringBuffer();
        str.append("SELECT ddo.ddo_name,ddo.account_no,ddo.ifs_code,office.EMAIL,office.TEL_NO2 ");
        str.append("FROM MST_DCPS_DDO_OFFICE office inner join org_ddo_mst ddo on ddo.DDO_CODE=office.DDO_CODE ");
        str.append(" and upper(office.DDO_OFFICE)='YES' and ddo.dDO_CODE=:ddocode ");
        final Query query = session.createSQLQuery(str.toString());
        query.setString("ddocode", ddoCode);
        ddoDtls = query.list();
        return ddoDtls;
    }

    public List getIndividualDetails()
    {

        final Session session = this.getSession();
        List indivdlDtls = null;
        final StringBuffer str = new StringBuffer();
        str.append("SELECT mst.emp_name,mst.bank_acnt_no,mst.ifsc_code, pay.net_total   FROM HR_PAY_PAYBILL pay ");
        str.append("inner join hr_eis_emp_mst eis on pay.emp_id=eis.emp_id ");
        str.append("inner join mst_dcps_emp mst on eis.emp_mpg_id=mst.org_emp_mst_id ");
        str.append("where pay.paybill_grp_id=999910000138911");

        this.logger.info("getTotalEmpConfig DAO------" + str.toString());
        final Query query = session.createSQLQuery(str.toString());

        if (query.list() != null)
        {
            indivdlDtls = query.list();
        }

        this.logger.info("totalEmpConf size: " + indivdlDtls.size());
        return indivdlDtls;
    }

    @Override
    public List<CMPRecord> getListOfBillDetails(final String authNo)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public List getNonGovDedFromPaybillID(final String authNo)
    {

        final Session session = this.getSession();
        List nonGovdedDtls = null;
        final StringBuffer str = new StringBuffer();
        str.append("SELECT nonGovt.PAYBILL_ID ,sum(nonGovt.NON_GOV_DEDUC_AMOUNT) as nonGov,paybill.LOC_ID  ");
    	str.append(" FROM HR_PAY_PAYSLIP_NON_GOVT nonGovt");
    	str.append(" inner join HR_PAY_PAYBILL paybill on nonGovt.PAYBILL_ID=paybill.id ");
    	str.append("  inner join CONSOLIDATED_BILL_MPG mpg on mpg.PAYBILL_ID=paybill.PAYBILL_GRP_ID inner join CONSOLIDATED_BILL_MST cons on cons.CONS_BILL_ID=mpg.CONS_BILL_ID ");
    	str.append(" where trim(cons.AUTH_NUMBER)='"+authNo.trim()+"' group by nonGovt.PAYBILL_ID,paybill.LOC_ID  order by paybill.LOC_ID  ");
      /*  str.append("SELECT nonGovt.PAYBILL_ID ,sum(nonGovt.NON_GOV_DEDUC_AMOUNT) as nonGov,paybill.LOC_ID  ");
        str.append(" FROM HR_PAY_PAYSLIP_NON_GOVT nonGovt");
        str.append(" inner join HR_PAY_PAYBILL paybill on nonGovt.PAYBILL_ID=paybill.id ");
        str.append(" inner join PAYBILL_HEAD_MPG pay on paybill.PAYBILL_GRP_ID=pay.PAYBILL_ID ");
        str.append(" inner join ifms.CONSOLIDATED_BILL_MPG cmpg on pay.PAYBILL_ID=cmpg.PAYBILL_ID ");
        str.append(" INNER join ifms.CONSOLIDATED_BILL_MST cmst on cmpg.CONS_BILL_ID= cmst.cons_bill_id ");
        str.append(" where cmst.cons_bill_id=:paybillGrpId ");
        str.append(" group by nonGovt.PAYBILL_ID,paybill.LOC_ID ");
        str.append(" order by paybill.LOC_ID ");*/
        final Query query = session.createSQLQuery(str.toString());
     //   query.setLong("paybillGrpId", paybillGrpId);
        this.logger.info("getNonGovDedFromPaybillID DAO------" + str.toString());

        if (query.list() != null)
        {

            nonGovdedDtls = query.list();
            this.logger.info("payBillDate---------" + nonGovdedDtls);
            /* payBillId = lLstPaybillId.get(0).toString(); */
        }
        return nonGovdedDtls;
    }

    public List getNonGovDeductionforDDO(final String authNo)
    {

        final Session session = this.getSession();
        List commonDtls = null;

        final String authNoValue = authNo.trim();

        this.logger.info("Auth No inside before query is NGR Details is ::" + authNo);
        final StringBuffer str = new StringBuffer();
        str
                .append("SELECT distinct sum(payslip.NON_GOV_DEDUC_AMOUNT) as NGR,billgrp.DDO_CODE,cons.cons_bill_id FROM paybill_head_mpg paybill ");
        str.append(" inner join HR_PAY_PAYBILL pay on pay.PAYBILL_GRP_ID = paybill.PAYBILL_ID ");
        str.append(" left outer join HR_PAY_PAYSLIP_NON_GOVT payslip on pay.ID=payslip.paybill_id  ");
        str.append(" inner join MST_DCPS_BILL_GROUP billgrp on paybill.BILL_NO = billgrp.BILL_GROUP_ID ");
        str.append(" inner join CONSOLIDATED_BILL_MPG cmpg on cmpg.PAYBILL_ID = paybill.PAYBILL_ID  ");
        str.append(" inner join CONSOLIDATED_BILL_MST cons on cmpg.cons_BILL_ID = cons.cons_BILL_ID ");
        str.append(" where cons.AUTH_NUMBER= '" + authNoValue + "'  and paybill.approve_flag in(0,1) ");
        str.append(" group by billgrp.DDO_CODE,cons.cons_bill_id ");
        str.append(" order by billgrp.DDO_CODE");
        // having sum(payslip.NON_GOV_DEDUC_AMOUNT)>0 ");
        final Query query = session.createSQLQuery(str.toString());
        // query.setString("AuthNo", authNo);
        this.logger.info("getNonGovDeductionforDDO DAO------" + str.toString());
        this.logger.info("Auth No inside after query is NGR Details is ::" + authNo);

        if (query.list() != null && query.list().size() > 0)
        {
            commonDtls = query.list();
            this.logger.info("NGR List size is ::" + commonDtls.size());
            this.logger.info("NGR List index 0 is ::" + commonDtls.get(0).toString());
            /*
             * if(commonDtls.get(0)!=null && !commonDtls.get(0).equals("")){
             * temp = (commonDtls.get(0).toString()); }
             */
        }
        /*
         * if(temp!=null && !temp.equals("")){ ngrAmount = Long.parseLong(temp);
         * }
         */
        /*
         * else{ commonDtls.add(0);
         * logger.info("NGR List size is ::"+commonDtls.size()); }
         */
        this.logger.info("query is" + str.toString());
        return commonDtls;

    }

    public List getOtherDedForDDO(final String authNo)
    {
        final Session session = this.getSession();
        List otherDedList = null;
        final String authNoValue = authNo.trim();
        
    	int count = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from consolidated_bill_mst where ((month<8 and year=2015) or year<2015) and   "); 
		sb.append(" Auth_number='"+authNoValue+"' ");
		logger.info("---- getCount DAo---"+sb.toString());
		Query query1 = hibSession.createSQLQuery(sb.toString());
		
		count = Integer.parseInt(query1.uniqueResult().toString());
		
		
        final StringBuffer str = new StringBuffer();
      // if(count==0)
        str.append("SELECT sum(pay.TOTAL_DED-pay.pt-pay.GPF_GRP_ABC-pay.GPF_GRP_D-pay.GPF_ADV_GRP_ABC-pay.GPF_ADV_GRP_D-pay.GPF_ABC_ARR_MR-pay.GPF_D_ARR_MR-pay.dcps-pay.DCPS_DA-pay.DCPS_DELAY-pay.DCPS_PAY-pay.JANJULGISARR-sum(pay.ACC_POLICY)) as other_ded ,bill.DDO_CODE  from HR_PAY_PAYBILL pay ");
      /* else
    	   str.append("SELECT sum(pay.TOTAL_DED-pay.pt-pay.pt_Arr-pay.GPF_GRP_ABC-pay.GPF_GRP_D-pay.GPF_ADV_GRP_ABC-pay.GPF_ADV_GRP_D-pay.GPF_ABC_ARR_MR-pay.GPF_D_ARR_MR-pay.it-pay.SERVICE_CHARGE-pay.hrr) as other_ded ,bill.DDO_CODE  from HR_PAY_PAYBILL pay ");*/   
       str.append("inner join PAYBILL_HEAD_MPG paybill on pay.PAYBILL_GRP_ID=paybill.PAYBILL_ID ");
        str.append("inner join ifms.CONSOLIDATED_BILL_MPG cmpg on paybill.PAYBILL_ID=cmpg.PAYBILL_ID ");
        str.append("INNER join ifms.CONSOLIDATED_BILL_MST cmst on cmpg.CONS_BILL_ID= cmst.cons_bill_id ");
        str.append("INNER join MST_DCPS_BILL_GROUP bill on paybill.BILL_NO=bill.BILL_GROUP_ID ");
        str.append("where cmst.auth_number='" + authNoValue + "' and paybill.APPROVE_FLAG in(0,1) ");
        str.append("group by bill.DDO_CODE order by bill.DDO_CODE ");
        this.logger.info("getOtherDED DAO------" + str.toString());
        final Query query = session.createSQLQuery(str.toString());
        // query.setString("AuthNo", authNo);

        otherDedList = query.list();

        return otherDedList;
    }

    public List getPaybillIdFromAuthNo(final String authNo)
    {

        final Session session = this.getSession();
        List payBillDate = null;
        final StringBuffer str = new StringBuffer();
        str
                .append("SELECT mst.month,mst.year,mst.cons_bill_id,mst.status,mst.cmp_file_flag FROM CONSOLIDATED_BILL_MST mst, CONSOLIDATED_BILL_MPG mpg  ");
        str.append("where mst.CONS_BILL_ID=mpg.CONS_BILL_ID and AUTH_Number = :AuthNo");
        final Query query = session.createSQLQuery(str.toString());
        query.setString("AuthNo", authNo);
        this.logger.info("getPaybillIdFromAuthNo DAO------" + str.toString());

        if (query.list() != null)
        {

            payBillDate = query.list();
            this.logger.info("payBillDate---------" + payBillDate);
            /* payBillId = lLstPaybillId.get(0).toString(); */
        }
        return payBillDate;
    }

    public synchronized long PRNGenerator(final String treasurycode, final int noOfUpdates)
    {
        final Session session = this.getSession();
        Long srNoLong = 0L;
        try
        {
            final StringBuffer str = new StringBuffer();
            str.append("SELECT GENERATED_PNR FROM HR_PAY_CMP_PRN_GENERATOR where TRESURY_CODE=:treasurycode");
            final Query query = session.createSQLQuery(str.toString());
            query.setString("treasurycode", treasurycode.trim());
            srNoLong = Long.parseLong(query.uniqueResult().toString());
            final StringBuffer updateQueryStr = new StringBuffer();
            updateQueryStr.append("update HR_PAY_CMP_PRN_GENERATOR set GENERATED_PNR=" + (srNoLong + noOfUpdates)
                    + " where TRESURY_CODE =:treasurycode");
            this.logger.info("PRNGenerator DAO------" + updateQueryStr.toString() + treasurycode.trim());
            final Query updateQuery = session.createSQLQuery(updateQueryStr.toString());
            updateQuery.setString("treasurycode", treasurycode.trim());
            updateQuery.executeUpdate();

        } catch (final Exception e)
        {
            this.logger.info("Exception in primaryKeyGenerator in investigation Service");
            this.logger.error("Error is: " + e.getMessage());
        }
        return srNoLong;
    }

    public void saveCmpDetails(final CMPRecord record)
    {
        final Session session = this.getSession();

        final StringBuffer str = new StringBuffer();
        str
                .append("insert into HR_PAY_CMP_RECORD_MST values(:bulkUploadFlag,:billIdentifier,:treasuryCode,:ddoCode,:benfName,:accNo,:ifscCode,:micrCode,");
        str
                .append(":accType,:amount,:prnNo,:payByDate,:schemeCode,:ddoBillNo,:authNo,:toBillNo,:billDate,:narration,:noOfPayees,:billNetAmt,:emailId,:cellNo)");
        final Query updateQuery = session.createSQLQuery(str.toString());
        updateQuery.setParameter("bulkUploadFlag", record.getBulkUploadFlag());
        updateQuery.setParameter("billIdentifier", "J");
        updateQuery.setParameter("treasuryCode", record.getTreasuryCode());
        updateQuery.setParameter("ddoCode", record.getDdoCode());
        updateQuery.setParameter("benfName", record.getBenefName());
        updateQuery.setParameter("accNo", record.getAccNo());
        updateQuery.setParameter("ifscCode", record.getIfscCode());
        updateQuery.setParameter("micrCode", record.getMicrCode());
        updateQuery.setParameter("accType", record.getAccType());
        updateQuery.setParameter("amount", record.getAmount());
        updateQuery.setParameter("prnNo", record.getPaymentRefNo());
        updateQuery.setParameter("payByDate", record.getPayBydate());
        updateQuery.setParameter("schemeCode", record.getSchemeCode());
        updateQuery.setParameter("ddoBillNo", record.getDdoBillNo());
        updateQuery.setParameter("authNo", record.getAuthNo());
        updateQuery.setParameter("toBillNo", record.getToBilNo());
        updateQuery.setParameter("billDate", record.getBillDate());
        updateQuery.setParameter("narration", record.getNarration());
        updateQuery.setParameter("noOfPayees", record.getNoOfPayees());
        updateQuery.setParameter("billNetAmt", record.getBillNetAmt());
        updateQuery.setParameter("emailId", record.getEmailId());
        updateQuery.setParameter("cellNo", record.getCellNo());

        this.logger.error("bulkUploadFlag: " + record.getBulkUploadFlag());
        this.logger.error("billIdentifier: " + "J");
        this.logger.error("treasuryCode: " + record.getTreasuryCode());
        this.logger.error("ddoCode: " + record.getDdoCode());
        this.logger.error("benfName: " + record.getBenefName());
        this.logger.error("accNo: " + record.getAccNo());
        this.logger.error("ifscCode: " + record.getIfscCode());
        this.logger.error("micrCode: " + record.getMicrCode());
        this.logger.error("accType: " + record.getAccType());
        this.logger.error("amount: " + record.getAmount());
        this.logger.error("prnNo: " + record.getPaymentRefNo());
        this.logger.error("payByDate: " + record.getPayBydate());
        this.logger.error("schemeCode: " + record.getSchemeCode());
        this.logger.error("ddoBillNo: " + record.getDdoBillNo());
        this.logger.error("authNo: " + record.getAuthNo());
        this.logger.error("toBillNo: " + record.getToBilNo());
        this.logger.error("billDate: " + record.getBillDate());
        this.logger.error("narration: " + record.getNarration());
        this.logger.error("noOfPayees: " + record.getNoOfPayees());
        this.logger.error("billNetAmt: " + record.getBillNetAmt());
        this.logger.error("emailId: " + record.getEmailId());
        this.logger.error("cellNo: " + record.getCellNo());

        this.logger.error("bulkUploadFlag: " + record.getBulkUploadFlag().length());
        this.logger.error("billIdentifier: " + "E".length());
        this.logger.error("treasuryCode: " + record.getTreasuryCode().length());
        this.logger.error("ddoCode: " + record.getDdoCode().length());
        this.logger.error("benfName: " + record.getBenefName().length());
        this.logger.error("accNo: " + record.getAccNo().length());
        this.logger.error("ifscCode: " + record.getIfscCode().length());
        this.logger.error("micrCode: " + record.getMicrCode().length());
        this.logger.error("accType: " + record.getAccType().length());
        this.logger.error("amount: " + record.getAmount().length());
        this.logger.error("prnNo: " + record.getPaymentRefNo().length());
        this.logger.error("payByDate: " + record.getPayBydate().length());
        this.logger.error("schemeCode: " + record.getSchemeCode().length());
        this.logger.error("ddoBillNo: " + record.getDdoBillNo().length());
        this.logger.error("authNo: " + record.getAuthNo().length());
        this.logger.error("toBillNo: " + record.getToBilNo().length());
        this.logger.error("billDate: " + record.getBillDate().length());
        this.logger.error("narration: " + record.getNarration().length());
        this.logger.error("noOfPayees: " + record.getNoOfPayees().length());
        this.logger.error("billNetAmt: " + record.getBillNetAmt().length());
        this.logger.error("emailId: " + record.getEmailId().length());
        this.logger.error("cellNo: " + record.getCellNo().length());

        updateQuery.executeUpdate();
    }

    public void saveCmpPayDetails(final HrPayCmpDtls cmpDtls)
    {
        final Session session = this.getSession();
        final StringBuffer str = new StringBuffer();
        str
                .append("insert into HR_PAY_CMP_DTLS values(:prnNo,:statusFlag,:cmpRefNo,:modeOfPayment,:utrRefNo,:utrDate,");
        str.append(":scrollNo,:settelmentDate,:linkFocalPoint,:remarks,:createdDate,:updatedDate, null, null)");
        final Query updateQuery = session.createSQLQuery(str.toString());
        updateQuery.setParameter("prnNo", cmpDtls.getPaymentRefNo());
        updateQuery.setParameter("statusFlag", cmpDtls.getStatusFlag());
        updateQuery.setParameter("cmpRefNo", cmpDtls.getCmpRefNo());
        updateQuery.setParameter("modeOfPayment", cmpDtls.getModeOfPayment());
        updateQuery.setParameter("utrRefNo", cmpDtls.getUtrRefNo());
        updateQuery.setParameter("utrDate", cmpDtls.getUtrDate());
        updateQuery.setParameter("scrollNo", cmpDtls.getScrollNo());
        updateQuery.setParameter("settelmentDate", cmpDtls.getSettelmentDate());
        updateQuery.setParameter("linkFocalPoint", cmpDtls.getLinkFocalPoint());
        updateQuery.setParameter("remarks", cmpDtls.getRemarks());
        updateQuery.setParameter("createdDate", cmpDtls.getCreatedDate());
        updateQuery.setParameter("updatedDate", cmpDtls.getUpdatedDate());

        updateQuery.executeUpdate();
    }

    /*
     * update flag for SBI_CMP file in Paybill head Mpg MD=Manually Downloaded
     * WD=Webservice Downloaded MA=Manually Acknowledged WA=Webservice
     * Acknowledged (non-Javadoc)
     * 
     * @see com.tcs.sgv.eis.dao.BillDetailsDAO#updateCMPFlag(java.lang.String,
     * java.lang.String)
     */
    public void updateCMPFlag(final String authNo, final String flag)
    {

        final Session session = this.getSession();
        final StringBuffer str = new StringBuffer();
        this.logger.info("authNo" + authNo);
        str.append("update CONSOLIDATED_BILL_MST set cmp_file_flag = '" + flag + "' where AUTH_Number = :AuthNo");
        final Query query = session.createSQLQuery(str.toString());
        this.logger.info("updateCMPFlag DAO------" + str.toString());
        query.setString("AuthNo", authNo.trim());

        query.executeUpdate();
        this.logger.info("Updation done");

    }

	@Override
	public int getCount(String authNo) {
		int benCount = 0;

		
		int count = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from consolidated_bill_mst where ((month<8 and year=2015) or year<2015) and   "); 
		sb.append(" Auth_number='"+authNo+"' ");
		logger.info("---- getCount DAo---"+sb.toString());
		Query query1 = hibSession.createSQLQuery(sb.toString());
		
		count = Integer.parseInt(query1.uniqueResult().toString());
		
		
		StringBuffer sbDDO = new StringBuffer();
		//if(count==0)
			//sbDDO.append("select distinct sum(nvl(nonGovt.NON_GOV_DEDUC_AMOUNT,0)) + sum(paybill.TOTAL_DED)-sum(paybill.pt)-sum(paybill.pt_Arr)-sum(paybill.GPF_GRP_ABC)-sum(paybill.GPF_GRP_D)-sum(paybill.GPF_ADV_GRP_ABC)-sum(paybill.GPF_ADV_GRP_D)-sum(paybill.GPF_ABC_ARR_MR)-sum(paybill.GPF_D_ARR_MR)-sum(paybill.it)-sum(paybill.SERVICE_CHARGE)-sum(paybill.hrr)-sum(paybill.DCPS)-sum(paybill.DCPS_DA)-sum(paybill.DCPS_DELAY)-sum(paybill.DCPS_PAY)-sum(paybill.JANJULGISARR)-sum(paybill.GIS),pay.LOC_ID from paybill_head_mpg pay  ");
			sbDDO.append("select distinct sum(nvl(nonGovt.NON_GOV_DEDUC_AMOUNT,0)) + sum(paybill.TOTAL_DED)-sum(paybill.pt)-sum(paybill.GPF_GRP_ABC)-sum(paybill.GPF_GRP_D)-sum(paybill.GPF_ADV_GRP_ABC)-sum(paybill.GPF_ADV_GRP_D)-sum(paybill.GPF_ABC_ARR_MR)-sum(paybill.GPF_D_ARR_MR)-sum(paybill.DCPS)-sum(paybill.DCPS_DA)-sum(paybill.DCPS_DELAY)-sum(paybill.DCPS_PAY)-sum(paybill.JANJULGISARR)-sum(paybill.ACC_POLICY),pay.LOC_ID from paybill_head_mpg pay  ");
		/*	else 
			sbDDO.append("select distinct sum(nvl(nonGovt.NON_GOV_DEDUC_AMOUNT,0)) + sum(paybill.TOTAL_DED)-sum(paybill.pt)-sum(paybill.pt_Arr)-sum(paybill.GPF_GRP_ABC)-sum(paybill.GPF_GRP_D)-sum(paybill.GPF_ADV_GRP_ABC)-sum(paybill.GPF_ADV_GRP_D)-sum(paybill.GPF_ABC_ARR_MR)-sum(paybill.GPF_D_ARR_MR)-sum(paybill.it)-sum(paybill.SERVICE_CHARGE)-sum(paybill.hrr),pay.LOC_ID from paybill_head_mpg pay  ");
		*/
		sbDDO.append("inner join CONSOLIDATED_BILL_MPG cmpg on pay.PAYBILL_ID=cmpg.PAYBILL_ID ");
		 sbDDO.append("inner join CONSOLIDATED_BILL_MST cnos on cnos.cons_bill_id=cmpg.CONS_BILL_ID ");
		 sbDDO.append("inner join HR_PAY_PAYBILL paybill on pay.PAYBILL_ID=paybill.PAYBILL_GRP_ID ");
		 sbDDO.append("left outer join HR_PAY_PAYSLIP_NON_GOVT nonGovt on nonGovt.PAYBILL_ID=paybill.id ");
		 sbDDO.append("where cnos.AUTH_NUMBER = '"+authNo+"'  group by pay.LOC_ID ");
		 this.logger.info("---- getCount DAo---" + sbDDO.toString());
		 Query queryDDO = this.hibSession.createSQLQuery(sbDDO.toString());
		 List DDOList = queryDDO.list();
		 Iterator itr = DDOList.iterator();
		 Object[] obj = null;
		 while (itr.hasNext())
		 {
		     obj = (Object[]) itr.next();
		     if (obj[1] != null)
		     {
		         if (obj[0].toString() != null && !obj[0].toString().equals("0"))
		
		         {
		             benCount = benCount + 1;
		         }
		     }
		
		 }
		/*StringBuffer sbDDO = new StringBuffer();
		sbDDO.append("select count(distinct loc_id) from paybill_head_mpg where paybill_id in (select paybill_id from consolidated_bill_mpg where CONS_BILL_ID ="+consBillId+")");
		
		logger.info("---- getCount DAo---"+sbDDO.toString());
		Query queryDDO = hibSession.createSQLQuery(sbDDO.toString());
		
		benCount = benCount + Integer.parseInt(queryDDO.uniqueResult().toString());
		*/
		return benCount;
	
	}

	@Override
	public List getNonGovDedFromPaybillID(long paybillId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public void modifyCMPfileGen(final String authNo)
	    {

	        final Session session = this.getSession();
	        final StringBuffer str = new StringBuffer();
	        this.logger.info("authNo" + authNo);
	        str.append("update CONSOLIDATED_BILL_MST set cmp_file_flag = null where AUTH_Number = :AuthNo");
	        final Query query = session.createSQLQuery(str.toString());
	        this.logger.info("updateCMPFlag DAO------" + str.toString());
	        query.setString("AuthNo", authNo.trim());
	        query.executeUpdate();
	        
	        final StringBuffer str1 = new StringBuffer();
	        str1.append("delete from HR_PAY_CMP_RECORD_MST where trim(AUTH_No) = :AuthNo");
	        final Query query1 = session.createSQLQuery(str1.toString());
	        this.logger.info("delete records DAO------" + str1.toString());
	        query1.setString("AuthNo", authNo.trim());
	        query1.executeUpdate();
	        
	        this.logger.info("Updation done");

	    }
}
