package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.eis.valueobject.RLTPaybandGPState7PC;

public class GradDesgScaleMapDAO extends
		GenericDaoHibernateImpl<HrEisSgdMpg, Long> {
	Log logger = LogFactory.getLog(getClass());

	public GradDesgScaleMapDAO(Class<HrEisSgdMpg> type,
			SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List<HrEisSgdMpg> getAllMasterData() {
		Criteria objCrt = null;
		try {
			Session hibSession = getSession();
			hibSession = getSession();
			objCrt = hibSession.createCriteria(HrEisSgdMpg.class);

			objCrt.setFetchMode("hrEisGdMpg", FetchMode.JOIN);
			objCrt.createAlias("hrEisGdMpg", "HrEisGdMpg");

			objCrt.setFetchMode("HrEisGdMpg.orgGradeMst", FetchMode.JOIN);
			objCrt.createAlias("HrEisGdMpg.orgGradeMst", "orgGrade");

			objCrt.setFetchMode("HrEisGdMpg.orgDesignationMst", FetchMode.JOIN);
			objCrt.createAlias("HrEisGdMpg.orgDesignationMst", "orgDesig");

			objCrt.setFetchMode("hrEisScaleMst", FetchMode.JOIN);
			objCrt.createAlias("hrEisScaleMst", "scaleMst");

			objCrt.addOrder(Order.asc("orgGrade.gradeName"));
			objCrt.addOrder(Order.asc("orgDesig.dsgnName"));
			objCrt.addOrder(Order.asc("scaleMst.scaleStartAmt"));
			objCrt.addOrder(Order.asc("scaleMst.scaleEndAmt"));

		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		List<HrEisSgdMpg> resultSet = objCrt.list();
		return resultSet;
	}

	public List getdesigsfromgrades(Long sgdgradeId, Long langID) {
		Criteria objCrt = null;
		try {
			Session hibSession = getSession();
			OrgGradeMst orgGradeMst = new OrgGradeMst();
			orgGradeMst.setGradeId(sgdgradeId);
			objCrt = hibSession.createCriteria(HrEisGdMpg.class);
			objCrt.add(Restrictions.eq("orgGradeMst", orgGradeMst));

			objCrt.setFetchMode("orgDesignationMst", FetchMode.JOIN);
			objCrt.createAlias("orgDesignationMst", "OrgDesignationMst");

			objCrt.addOrder(Order.asc("OrgDesignationMst.dsgnName"));
		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		logger.info("******objCrt******" + objCrt.list().size());
		return objCrt.list();
	}

	public List getScalefromGD(Long gdMapId, Long langID) {
		logger.info("getScalefromGD( gdMapId, langID): gdMapId =" + gdMapId
				+ "and langID = " + langID);
		Criteria objCrt = null;
		// List scales = null;
		try {
			Session hibSession = getSession();
			HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
			hrEisGdMpg.setGdMapId(gdMapId);
			// added by manish
			/*HrPayCommissionMst commissionMst = new HrPayCommissionMst();
			long ll = 2500341;
			commissionMst.setId(ll);
			HrEisScaleMst hrEisScaleMst = new HrEisScaleMst();
			logger.info("manish hyere 2 ");
			// hrEisScaleMst.getHrPayCommissionMst().setId(ll);
			 hrEisScaleMst.getHrPayCommissionMst();*/
		//	hrEisScaleMst.setHrPayCommissionMst(commissionMst);

		
			 
			objCrt = hibSession.createCriteria(HrEisSgdMpg.class);
			objCrt.add(Restrictions.eq("hrEisGdMpg", hrEisGdMpg));
			//objCrt.add(Restrictions.eq("hrEisScaleMst", hrEisScaleMst));

			/*
			 * Session hibSession = getSession(); scales =
			 * hibSession.createCriteria(HrEisSgdMpg.class)
			 * .add(Restrictions.eq("gdMapId",gdMapId )) .list();
			 */
		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}

		logger.info("******objCrt******" + objCrt.list().size());
		// logger.info("******scales******"+scales.size());
		return objCrt.list();
		// return scales;
	}

	public HrEisSgdMpg getScaleGradeDesgMasterData(Long sgdMapId) {
		HrEisSgdMpg hrEisSgdMpg = new HrEisSgdMpg();
		try {
			Session hibSession = getSession();
			String query1 = "from HrEisSgdMpg as a where a.sgdMapId ="
					+ sgdMapId;
			Query sqlQuery1 = hibSession.createQuery(query1);
			hrEisSgdMpg = (HrEisSgdMpg) sqlQuery1.uniqueResult();
		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		logger.info("******MPGID*****" + hrEisSgdMpg.getSgdMapId());
		return hrEisSgdMpg;
	}

	public List getScaleGradeDesgMasterData(Long gdMapId, Long scaleId) {
		Criteria objCrt = null;
		try {
			Session hibSession = getSession();
			HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
			hrEisGdMpg.setGdMapId(gdMapId);
			objCrt = hibSession.createCriteria(HrEisSgdMpg.class);
			objCrt.add(Restrictions.eq("hrEisGdMpg", hrEisGdMpg));
			HrEisScaleMst hrEisScaleMst = new HrEisScaleMst();
			hrEisScaleMst.setScaleId(scaleId);
			objCrt.add(Restrictions.eq("hrEisScaleMst", hrEisScaleMst));
			
		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		logger.info("******objCrt******" + objCrt.list().size());
		return objCrt.list();
	}
	//added by manish for making sgd mpg location specific
	public List getScaleGradeDesgMasterData(Long gdMapId, Long scaleId,long locId) {
		
		List resList= null;
		try {
			
			Session hibSession = getSession();
			String  query = " from HrEisSgdMpg sgdMpg where sgdMpg.cmnLocationMst.locId="+locId+" and sgdMpg.hrEisGdMpg.gdMapId="+gdMapId+" and sgdMpg.hrEisScaleMst.scaleId="+scaleId ;
			Query sqlQuery = hibSession.createQuery(query);
			resList = sqlQuery.list();
			logger.info(" Results Returend from getScaleGradeDesgMasterData(Long gdMapId, Long scaleId,long locId)  size "+resList.size());
			
			
		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
			
		}
		return resList;
		
	}

	//ended by manish 

	// added by jigna and hemant to get data by designation on 18-7-2009
	public List<HrEisSgdMpg> getAllMasterData(String lDsgnname) {
		Criteria objCrt = null;
		try {
			logger
					.info("===============================lDsgnname==============="
							+ lDsgnname);
			Session hibSession = getSession();
			hibSession = getSession();
			objCrt = hibSession.createCriteria(HrEisSgdMpg.class);

			objCrt.setFetchMode("hrEisGdMpg", FetchMode.JOIN);
			objCrt.createAlias("hrEisGdMpg", "HrEisGdMpg");

			objCrt.setFetchMode("HrEisGdMpg.orgGradeMst", FetchMode.JOIN);
			objCrt.createAlias("HrEisGdMpg.orgGradeMst", "orgGrade");

			objCrt.setFetchMode("HrEisGdMpg.orgDesignationMst", FetchMode.JOIN);
			objCrt.createAlias("HrEisGdMpg.orgDesignationMst", "orgDesig");
			if (lDsgnname != null && !lDsgnname.equals(""))
				objCrt.add(Restrictions.like("orgDesig.dsgnName",
						"%" + lDsgnname + "%").ignoreCase());

			objCrt.setFetchMode("hrEisScaleMst", FetchMode.JOIN);
			objCrt.createAlias("hrEisScaleMst", "scaleMst");

			objCrt.addOrder(Order.asc("orgGrade.gradeName"));
			objCrt.addOrder(Order.asc("orgDesig.dsgnName"));
			objCrt.addOrder(Order.asc("scaleMst.scaleStartAmt"));
			objCrt.addOrder(Order.asc("scaleMst.scaleEndAmt"));

		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		List<HrEisSgdMpg> resultSet = objCrt.list();
		return resultSet;
	}
	// end
	
	
	public List<HrEisScaleMst> getScalefromDsgnComm( Long commissionId) {
		
		 List<HrEisScaleMst> scales = new ArrayList();
		try {
			
			Session hibSession = getSession();
			HrEisGdMpg hrEisGdMpg = new HrEisGdMpg();
			
			
			String hqlQuery = "from HrEisScaleMst hrEisScaleMst where hrEisScaleMst.hrPayCommissionMst.id = " +commissionId;
			logger.info(hqlQuery);
			
			Query query = hibSession.createQuery(hqlQuery);
			scales= query.list();
			
			
		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		return scales;
		
		
	}
	
	//Added By Ashish for Change Details
		public List<RLTPaybandGPState7PC> getSvnPayscale() {

			Session hibSession = getSession();
			Query query = hibSession.createQuery("select rltpay from RLTPaybandGPState7PC rltpay order by rltpay.levelId");
			logger.info("Mani Testing query= "+query);
			logger.info(query);
			List<RLTPaybandGPState7PC> scales = query.list();
			return scales;
		}	
		//Ended by Ashish for Change Details
		
				
}
