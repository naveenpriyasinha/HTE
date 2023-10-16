package com.tcs.sgv.lna.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class LNADataEntryFormDAOImpl extends GenericDaoHibernateImpl implements LNADataEntryFormDAO {
	Session ghibSession = null;

	public LNADataEntryFormDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public List<ComboValuesVO> getFinYear() {
		List<ComboValuesVO> lLstFinYear = new ArrayList<ComboValuesVO>();
		List<Object> lLstTemp = null;
		Object[] obj;
		ComboValuesVO cmbVO = null;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select finYearId,finYearCode");
		lSBQuery.append(" FROM SgvcFinYearMst");
		lSBQuery.append(" WHERE finYearCode between '2007' and '2012'");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lLstTemp = lQuery.list();

		if (!lLstTemp.isEmpty()) {
			for (Integer lInt = 0; lInt < lLstTemp.size(); lInt++) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) lLstTemp.get(lInt);
				cmbVO.setId(obj[0].toString());
				cmbVO.setDesc(obj[1].toString());
				lLstFinYear.add(cmbVO);
			}
		}
		return lLstFinYear;
	}

	public List getEmpDtls(String lStrEmpCode, String lStrHodLocCode) {
		List lLstEmpDtls = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT ME.name,ODM.dsgnName,DO.dcpsDdoOfficeName");
			lSBQuery.append(" FROM MstEmp ME,OrgDesignationMst ODM,DdoOffice DO,OrgDdoMst DM");
			lSBQuery.append(" WHERE ME.sevarthId = :sevarthId AND ME.designation = ODM.dsgnId AND ME.currOff = DO.dcpsDdoOfficeIdPk");
			lSBQuery.append(" AND ME.ddoCode = DM.ddoCode AND DM.hodLocCode = :HodLocCode");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevarthId", lStrEmpCode);
			lQuery.setParameter("HodLocCode", lStrHodLocCode);
			lLstEmpDtls = lQuery.list();

		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstEmpDtls;
	}

	public Long getBillGroupId(String lStrSevaarthId) {
		Long lLngBillGroupId = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT billGroupId ");
			lSBQuery.append("FROM MstEmp ");
			lSBQuery.append("WHERE sevarthId = :sevarthId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevarthId", lStrSevaarthId);
			lLngBillGroupId = (Long) lQuery.list().get(0);
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLngBillGroupId;
	}

	public List getDraftReq(String lStrUserType, String lStrHodLocCode) {
		List lLstDraftReq = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT ELD FROM TrnEmpLoanDtls ELD,MstEmp ME,OrgDdoMst DM ");
			if ("HODASST".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("WHERE ELD.status = 'D'");
			} else if ("HOD".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("WHERE ELD.status = 'F'");
			}
			lSBQuery.append(" AND ME.sevarthId = ELD.sevaarthId AND ME.ddoCode = DM.ddoCode AND DM.hodLocCode = :HodLocCode");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("HodLocCode", lStrHodLocCode);
			lLstDraftReq = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstDraftReq;
	}

	public List getCompAdvance(String lStrSevaarthId, String lStrUserType) {
		List lLstDraftReq = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT amountSanctioned,sanctionedDate,advanceSubType,interestRate,orderNo,orderDate,sancInstallments,installmentLeft,transactionId");
			lSBQuery.append(",userRemarks,computerAdvanceId FROM MstLnaCompAdvance ");
			lSBQuery.append("WHERE sevaarthId = :sevaarthId ");
			if ("HODASST".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and statusFlag = 'D'");
			} else if ("HOD".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and statusFlag = 'F'");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lLstDraftReq = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstDraftReq;
	}

	public List getHouseAdvance(String lStrSevaarthId, String lStrUserType) {
		List lLstDraftReq = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT amountSanctioned,sanctionedDate,advanceSubType,interestRate,orderNo,orderDate,interestAmount,sancPrinInst,sancInterestInst");
			lSBQuery.append(",principalInstLeft,InterestInstLeft,transactionId,userRemarks,houseAdvanceId,releaseDateOne,releaseDateTwo,releaseDateThree,releaseDateFour ");
			lSBQuery.append("FROM MstLnaHouseAdvance ");
			lSBQuery.append("WHERE sevaarthId = :sevaarthId ");
			if ("HODASST".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and statusFlag = 'D'");
			} else if ("HOD".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and statusFlag = 'F'");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lLstDraftReq = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstDraftReq;
	}

	public List getMotorAdvance(String lStrSevaarthId, String lStrUserType) {

		List lLstDraftReq = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT amountSanctioned,sanctionedDate,advanceSubType,interestRate,orderNo,orderDate,interestAmount,sancCapitalInst,");
			lSBQuery.append("sancInterestInst,capitalInstLeft,InterestInstLeft,transactionId");
			lSBQuery.append(",userRemarks,motorAdvanceId FROM MstLnaMotorAdvance ");
			lSBQuery.append("WHERE sevaarthId = :sevaarthId ");
			if ("HODASST".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and statusFlag = 'D'");
			} else if ("HOD".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and statusFlag = 'F'");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lLstDraftReq = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstDraftReq;
	}

	public List getVoucherDtls(String lStrSevaarthId, Long lLngAdvance, String lStrUserType) {

		List lLstDraftReq = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT 'V',instNo,monthId,finYearId,installmentAmount,openingBalance,voucherNo,voucherDate,lnaMonthlyId,prinOrInterestAmount ");
			lSBQuery.append("FROM MstLnaMonthly ");
			lSBQuery.append("WHERE sevaarthId = :sevaarthId and advanceType = :advanceType ");
			if ("HODASST".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and status = 'D'");
			} else if ("HOD".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and status = 'F'");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lQuery.setParameter("advanceType", lLngAdvance);
			lLstDraftReq = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstDraftReq;
	}

	public List getChallanDtls(String lStrSevaarthId, String lStrTransactionId, String lStrUserType) {

		List lLstDraftReq = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT 'C',instFrom,instTo,installmentAmount,challanNo,challanDate,lnaChallanDtlsId,openingBalance,prinOrInterestAmount");
			lSBQuery.append(",monthId,finYearId FROM MstLnaChallanDtls ");
			lSBQuery.append("WHERE sevaarthId = :sevaarthId and transactionId = :transactionId ");
			if ("HODASST".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and statusFlag = 'D'");
			} else if ("HOD".equalsIgnoreCase(lStrUserType)) {
				lSBQuery.append("and statusFlag = 'F'");
			}
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lQuery.setParameter("transactionId", lStrTransactionId);
			lLstDraftReq = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstDraftReq;
	}

	public List getPrevChallanDtls(String lStrSevaarthId, String lStrTransactionId) {
		List lLstDraftReq = null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("FROM MstLnaChallanDtls ");
			lSBQuery.append("WHERE sevaarthId = :sevaarthId and statusFlag = 'D' and transactionId = :transactionId");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lQuery.setParameter("transactionId", lStrTransactionId);
			lLstDraftReq = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstDraftReq;
	}

	public List getPrevVoucherDtls(String lStrSevaarthId, Long lLngAdvance) {
		List lLstDraftReq = null;
		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("FROM MstLnaMonthly ");
			lSBQuery.append("WHERE sevaarthId = :sevaarthId and status = 'D' and advanceType = :advanceType");
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("sevaarthId", lStrSevaarthId);
			lQuery.setParameter("advanceType", lLngAdvance);
			lLstDraftReq = lQuery.list();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
		return lLstDraftReq;
	}

	public void updateChallanDtls(String lStrSevaarthId) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		try {
			lSBQuery.append("update MstLnaChallanDtls ");
			lSBQuery.append("set statusFlag = 'A' WHERE sevaarthId = :SevaarthId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("SevaarthId", lStrSevaarthId);
			lQuery.executeUpdate();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
	}

	public void updateVoucherDtls(String lStrSevaarthId) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		try {
			lSBQuery.append("update MstLnaMonthly ");
			lSBQuery.append("set status = 'A' WHERE sevaarthId = :SevaarthId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("SevaarthId", lStrSevaarthId);
			lQuery.executeUpdate();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
	}

	public void updateCompAdvance(String lStrCompAdvanceId, String lStrRemark) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		try {
			lSBQuery.append("update MstLnaCompAdvance ");
			lSBQuery.append("set statusFlag = 'A',hoRemarks = :Remark,hodActionDate=sysdate WHERE computerAdvanceId = :CompAdvanceId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("CompAdvanceId", Long.parseLong(lStrCompAdvanceId));
			lQuery.setParameter("Remark", lStrRemark);
			lQuery.executeUpdate();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
	}

	public void updateHouseAdvance(String lStrHouseAdvanceId, String lStrRemark) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		try {
			lSBQuery.append("update MstLnaHouseAdvance ");
			lSBQuery.append("set statusFlag = 'A',hoRemarks = :Remark,hodActionDate=sysdate WHERE houseAdvanceId = :HouseAdvanceId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("HouseAdvanceId", Long.parseLong(lStrHouseAdvanceId));
			lQuery.setParameter("Remark", lStrRemark);
			lQuery.executeUpdate();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
	}

	public void updateMotorAdvance(String lStrMotorAdvanceId, String lStrRemark) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		try {
			lSBQuery.append("update MstLnaMotorAdvance ");
			lSBQuery.append("set statusFlag = 'A',hoRemarks = :Remark,hodActionDate=sysdate WHERE motorAdvanceId = :MotorAdvanceId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("MotorAdvanceId", Long.parseLong(lStrMotorAdvanceId));
			lQuery.setParameter("Remark", lStrRemark);
			lQuery.executeUpdate();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
	}

	public void updateEmpLoanDtls(String lStrEmpLoanDtlsId) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;

		try {
			lSBQuery.append("update TrnEmpLoanDtls ");
			lSBQuery.append("set status = 'A' WHERE empLoanDtlsId = :EmpLoanDtlsId");
			lQuery = ghibSession.createQuery(lSBQuery.toString());
			lQuery.setParameter("EmpLoanDtlsId", Long.parseLong(lStrEmpLoanDtlsId));
			lQuery.executeUpdate();
		} catch (Exception e) {
			logger.error(" Error is : " + e, e);
		}
	}

	public String requestPendingStatus(String lStrSevaarthId) {
		List lLstPendingRequest = new ArrayList();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select empLoanDtlsId");
		lSBQuery.append(" FROM TrnEmpLoanDtls");
		lSBQuery.append(" WHERE sevaarthId = :SevaarthId AND status = 'F')");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("SevaarthId", lStrSevaarthId);
		lLstPendingRequest = lQuery.list();
		if (lLstPendingRequest.isEmpty()) {
			return "No";
		} else {
			return "Yes";
		}
	}
}
