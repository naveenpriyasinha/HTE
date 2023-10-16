package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;

public class InsertLoanDataThread extends Thread{

	Log logger = LogFactory.getLog(getClass());
	List<HrPayPaybillLoanDtls> loanDtlsVOList = new ArrayList<HrPayPaybillLoanDtls>();
	Transaction tx = null;
	SessionFactory sf = null;
	Session session = null;
	HrPayPaybill payBillVO = null;

	public InsertLoanDataThread() {
	}

	public InsertLoanDataThread(List<HrPayPaybillLoanDtls> lstLoanDtlsVO,HrPayPaybill paybillVO) {
		this.loanDtlsVOList = lstLoanDtlsVO;
		this.payBillVO = paybillVO;
	}

	public void run() {
		try {

			logger.info("Inside run of InsertLoanDataThread");
			if (loanDtlsVOList != null)
				logger.info("size of Loan vo list to be inserted is " + loanDtlsVOList.size());
			
			Configuration cfg = new Configuration().configure();
			/*
			 * cfg.addJar(new java.io.File("WEB-INF/lib/hrms-eis.jar"));
			 * cfg.addJar(new java.io.File("hrms-ess.jar")); cfg.addJar(new
			 * java.io.File("hrms-hr.jar")); cfg.addJar(new
			 * java.io.File("hrms-payroll.jar")); cfg.addJar(new
			 * java.io.File("hrms-common.jar")); cfg.addJar(new
			 * java.io.File("hrms-payslip.jar")); cfg.addJar(new
			 * java.io.File("hdiits-common.jar")); cfg.addJar(new
			 * java.io.File("hdiits-hod-common.jar")); cfg.addJar(new
			 * java.io.File("hdiits-core.jar")); cfg.addJar(new
			 * java.io.File("hdiits-ess.jar"));
			 */
			if (cfg != null) {
				sf = cfg.buildSessionFactory();
				logger.info("SEssion factory build");
			}
			if (sf != null) {
				session = sf.openSession();
				logger.info("Sesion open");
			}
			if (session != null) {
				tx = session.beginTransaction();
				logger.info("Transaction begin");
			}

			for (HrPayPaybillLoanDtls loanDtlsVO : loanDtlsVOList) {
				logger.info("inserting record through Thread -- Loan");
				// payDao.create(paybillVO);
				loanDtlsVO.setPaybillId(this.payBillVO);
				session.saveOrUpdate(loanDtlsVO);
			}
			session.flush();
			tx.commit();
			session.clear();
			session.close();

		} catch (Exception e) {
			logger.info("Exception occurs in InsertLoan Thread");
			logger.error("Error is: "+ e.getMessage());
			if (tx != null)
				tx.rollback();
		} finally {
			if (sf != null)
				sf.close();
		}
	}
}
