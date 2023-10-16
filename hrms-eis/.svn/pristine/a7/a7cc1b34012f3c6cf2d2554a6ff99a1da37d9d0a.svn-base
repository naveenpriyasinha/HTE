package com.tcs.sgv.eis.dao;

//Comment By Maruthi For import Organisation.
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayCustOrderHeadPost;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;

public class OrderHeadPostmpgDAOImpl extends
		GenericDaoHibernateImpl<HrPayOrderHeadPostMpg, Long> implements
		OrderHeadPostmpgDAO {

	Log logger = LogFactory.getLog(getClass());

	DBsysdateConfiguration sbConf = new DBsysdateConfiguration();

	String sdate = sbConf.getSysdate();

	public OrderHeadPostmpgDAOImpl(Class<HrPayOrderHeadPostMpg> name,
			SessionFactory sessionFactory) {
		super(name);
		setSessionFactory(sessionFactory);
	}

	// remove on clause so we can change it on hql because hql not suppert on
	// clause
	public List getAllData(long locId, long langId, int finYrId) {
		List orderMstList = null;
		Session hibSession = getSession();

		// Added By Urvvin shah.
		String strQuery = " select hr_pay_order_head_post_mpg.order_head_post_id,hr_pay_order_mst.order_name,"
				+ " sgva_budsubhd_mst.budsubhd_desc_long,org_post_details_rlt.post_name,"
				+ " (select concat(concat(concat(concat(b.emp_fname,' '),b.emp_mname),' '),b.emp_lname) from org_emp_mst b"
				+ " where org_userpost_rlt.user_id = b.user_id and b.lang_id = 1 and org_userpost_rlt.activate_flag != 0)"
				+ " from hr_pay_order_mst, hr_pay_order_head_post_mpg, hr_pay_order_head_mpg,"
				+ " sgva_budsubhd_mst,hr_pay_order_subhead_mpg shm,org_post_details_rlt left outer join org_userpost_rlt on org_post_details_rlt.post_id = org_userpost_rlt.post_id"
				+ " and org_userpost_rlt.activate_flag != 0 "
				+ " where hr_pay_order_mst.order_id = hr_pay_order_head_mpg.order_id and"
				+ " hr_pay_order_head_mpg.order_head_id = hr_pay_order_head_post_mpg.order_head_id and"
				+ " hr_pay_order_head_mpg.subhead_id = shm.element_code and shm.fin_yr_id = "
				+ finYrId
				+ " and shm.budsubhd_id = sgva_budsubhd_mst.budsubhd_id and "
				+ " hr_pay_order_head_post_mpg.post_id = org_post_details_rlt.post_id and org_post_details_rlt.lang_id =  "
				+ langId
				+
				// " org_post_details_rlt.post_id = org_userpost_rlt.post_id " +
				" and org_post_details_rlt.loc_id in (select cmnLocMst.loc_id from cmn_location_mst cmnLocMst where cmnLocMst.loc_id ="
				+ locId
				+ " or cmnLocMst.parent_loc_id= "
				+ locId
				+ ") order by  hr_pay_order_mst.order_name,sgva_budsubhd_mst.budsubhd_desc_long,"
				+ " org_post_details_rlt.post_name";// +

		// end by manoj for vacant post issue
		logger.info("The Query String is:-"+strQuery);
		logger.info("The Query String is:-" + strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		orderMstList = query.list();
		//logger.info("added by
		// samir=====orderpostlistsize====>"+orderMstList.size());
		return orderMstList;
	}

	/*
	 * comented by samir joshi because it hql query and On clause use in this
	 * query and on clause not superted in HQl public List getAllData(String
	 * locationCode,long langId) { List orderMstList = null; Session hibSession =
	 * getSession();
	 * 
	 *  // Added By Urvvin shah. String strQuery=" SELECT POHPM.orderHeadPostId,
	 * POM.orderName, SBSHM.budsubhdDescLong, OPDR.postName, "+ " ( SELECT
	 * concat(concat(concat(concat(b.empFname,' '),b.empMname),' '),b.empLname) " + "
	 * FROM OrgEmpMst b " + " WHERE OUPR.orgUserMst.userId=b.orgUserMst.userId
	 * AND b.cmnLanguageMst.langId=1 AND OUPR.activateFlag != 0 ) "+ " FROM
	 * HrPayOrderMst POM, HrPayOrderHeadPostMpg POHPM, HrPayOrderHeadMpg POHM,
	 * SgvaBudsubhdMst SBSHM, " + " OrgPostDetailsRlt OPDR LEFT OUTER JOIN
	 * OrgUserpostRlt OUPR WITH
	 * OPDR.orgPostMst.postId=OUPR.orgPostMstByPostId.postId "+ " WHERE
	 * POM.orderId = POHM.orderId AND " + " POHM.orderHeadId = POHPM.orderHeadId
	 * AND "+ " SBSHM.budsubhdId = POHM.subheadId AND " + " POHPM.postId =
	 * OPDR.orgPostMst.postId AND OPDR.cmnLanguageMst.langId = 1 "+ " AND
	 * OPDR.cmnLocationMst.locId IN " + " ( SELECT cmnLocMst.locId " + " FROM
	 * CmnLocationMst cmnLocMst " + " WHERE
	 * cmnLocMst.locationCode='"+locationCode+"' AND
	 * cmnLocMst.cmnLanguageMst.langId="+langId+" ) " + " ORDER BY
	 * POM.orderName, SBSHM.budsubhdDescLong, OPDR.postName";
	 * 
	 * //end by manoj for vacant post issue logger.info("The Query String
	 * is:-"+strQuery); logger.info("The Query String is:-"+strQuery); Query
	 * query = hibSession.createQuery(strQuery); orderMstList = query.list();
	 * logger.info("added by
	 * samir=====orderpostlistsize====>"+orderMstList.size()); return
	 * orderMstList; }
	 */
	/*
	 * 
	 * Modify By samir Joshi For Sql to Hql and this method is Not Called so
	 * commented public List getAllData() { List orderMstList = null; Session
	 * hibSession = getSession(); //String strQuery = "select
	 * ohpmpg.orderHeadPostId,omst.orderName,bud.budsubhdDescLong,(select
	 * orgPostDetailsRlt.postName from OrgPostDetailsRlt orgPostDetailsRlt where
	 * orgPostDetailsRlt.postDetailId=ohpmpg.postId) post from HrPayOrderMst
	 * omst, HrPayOrderHeadMpg ohmpg ,HrPayOrderHeadPostMpg ohpmpg
	 * ,SgvaBudsubhdMst bud where omst.orderId=ohmpg.orderId and
	 * ohmpg.orderHeadId=ohpmpg.orderHeadId and ohmpg.subheadId=bud.budsubhdId";
	 * //changed by manoj for vacant post issue String strQuery=" select
	 * hr_pay_order_head_post_mpg.orderHeadPostId,HrPayOrderMst.order_name," + "
	 * sgva_budsubhd_mst.budsubhd_desc_long,OrgPostDetailsRlt.postName,"+ "
	 * b.empFname || ' ' || b.empMname || ' ' || b.empLname" + " from
	 * HrPayOrderMst, hr_pay_order_head_post_mpg, HrPayOrderHeadMpg," + "
	 * sgva_budsubhd_mst,OrgPostDetailsRlt,OrgEmpMst b, OrgUserpostRlt"+ " where
	 * HrPayOrderMst.orderId = HrPayOrderHeadMpg.orderId and b.EMP_SRVC_EXP >= "+
	 * sdate +" and "+ " HrPayOrderHeadMpg.orderHeadId =
	 * hr_pay_order_head_post_mpg.orderHeadId and"+ "
	 * sgva_budsubhd_mst.budsubhd_id = HrPayOrderHeadMpg.subheadId and" + "
	 * hr_pay_order_head_post_mpg.postId = OrgPostDetailsRlt.postId and"+ "
	 * OrgPostDetailsRlt.postId = OrgUserpostRlt.postId (+) and
	 * OrgUserpostRlt.orgUserMst.userId = b.orgUserMst.userId and b.lang_id = 1
	 * and OrgUserpostRlt.activateFlag != 0 " + " order by
	 * b.empFname,b.empMname,b.empLname ";
	 * 
	 * String strQuery = "select
	 * hr_pay_order_head_post_mpg.orderHeadPostId,HrPayOrderMst.order_name,"+
	 * "sgva_budsubhd_mst.budsubhd_desc_long, OrgPostDetailsRlt.postName,
	 * OrgEmpMst.empFname,OrgEmpMst.empMname, OrgEmpMst.empLname from
	 * HrPayOrderMst,hr_pay_order_head_post_mpg,HrPayOrderHeadMpg,"+
	 * "sgva_budsubhd_mst,OrgPostDetailsRlt,OrgUserpostRlt,OrgEmpMst where
	 * HrPayOrderMst.orderId = HrPayOrderHeadMpg.orderId and
	 * HrPayOrderHeadMpg.orderHeadId = hr_pay_order_head_post_mpg.orderHeadId"+ "
	 * and sgva_budsubhd_mst.budsubhd_id = HrPayOrderHeadMpg.subheadId"+ " and
	 * hr_pay_order_head_post_mpg.postId = OrgPostDetailsRlt.postId"+ " and
	 * OrgPostDetailsRlt.postId = OrgUserpostRlt.postId"+ " and
	 * OrgUserpostRlt.orgUserMst.userId = OrgEmpMst.orgUserMst.userId";
	 * 
	 * //end by manoj for vacant post issue Query query =
	 * hibSession.createSQLQuery(strQuery); orderMstList = query.list();
	 * logger.info("added by
	 * samir=====orderpostlistsize====>"+orderMstList.size()); return
	 * orderMstList; }
	 */

	public List getAllData(long userId, long locId, long langId,
			int finYrId) {
		List orderMstList = null;
		Session hibSession = getSession();
		// String strQuery = "select
		// ohpmpg.orderHeadPostId,omst.orderName,bud.budsubhdDescLong,(select
		// orgPostDetailsRlt.postName from OrgPostDetailsRlt orgPostDetailsRlt
		// where orgPostDetailsRlt.postDetailId=ohpmpg.postId) post from
		// HrPayOrderMst omst, HrPayOrderHeadMpg ohmpg ,HrPayOrderHeadPostMpg
		// ohpmpg ,SgvaBudsubhdMst bud where omst.orderId=ohmpg.orderId and
		// ohmpg.orderHeadId=ohpmpg.orderHeadId and
		// ohmpg.subheadId=bud.budsubhdId";

		// changed by manoj for vacant post issue
		String strQuery = " SELECT POHPM.orderHeadPostId, POM.orderName, SBSHM.budsubhdDescLong, OPDR.postName, "
				+ " 	( SELECT concat(b.empFname, concat(' ', concat(b.empMname,concat(' ',b.empLname)))) "
				+ " 		FROM OrgEmpMst b"
				+ " 			WHERE OUPR.orgUserMst.userId = b.orgUserMst.userId AND b.cmnLanguageMst.langId = 1 AND OUPR.activateFlag != 0 ) "
				+ " FROM HrPayOrderMst POM, HrPayOrderHeadPostMpg POHPM, HrPayOrderHeadMpg OHM, SgvaBudsubhdMst SBSHM, HrPayOrderSubHeadMpg shm, "
				+ " 		OrgPostDetailsRlt OPDR, OrgUserpostRlt OUPR"
				+ " WHERE POM.orderId = OHM.orderId AND "
				+ " 		OHM.orderHeadId = POHPM.orderHeadId AND "
				+ " 		OHM.subheadId = shm.element_code and shm.finYearId ="
				+ finYrId
				+ " AND shm.sgvaBudsubhdMst.budsubhdId = SBSHM.budsubhdId and "
				+ " 		POHPM.postId = OPDR.orgPostMst.postId AND "
				+ " 		OPDR.orgPostMst.postId = OUPR.orgPostMstByPostId.postId AND "
				+ "		OPDR.cmnLanguageMst.langId = 1 AND OPDR.cmnLocationMst.locId IN "
				+ " 		( SELECT cmnLocMst.locId "
				+ "			FROM CmnLocationMst cmnLocMst "
				+ "				WHERE cmnLocMst.locId ='"
				+ locId
				+ "' AND cmnLocMst.cmnLanguageMst.langId="
				+ langId
				+ " )"
				+ " 		AND OUPR.orgUserMst.userId = "
				+ userId
				+ " ORDER BY  POM.orderName, SBSHM.budsubhdDescLong, OPDR.postName";

		// end by manoj for vacant post issue
		Query query = hibSession.createQuery(strQuery);
		orderMstList = query.list();
		//logger.info("added by samir=====orderpostlistsize====> with
		// emp id "+orderMstList.size());

		return orderMstList;

		/*
		 * String strQuery = "select
		 * hr_pay_order_head_post_mpg.orderHeadPostId,HrPayOrderMst.order_name,"+
		 * "sgva_budsubhd_mst.budsubhd_desc_long, OrgPostDetailsRlt.postName,
		 * OrgEmpMst.empFname,OrgEmpMst.empMname, OrgEmpMst.empLname from
		 * HrPayOrderMst,hr_pay_order_head_post_mpg,HrPayOrderHeadMpg,"+
		 * "sgva_budsubhd_mst,OrgPostDetailsRlt,OrgUserpostRlt,OrgEmpMst where
		 * HrPayOrderMst.orderId = HrPayOrderHeadMpg.orderId and
		 * HrPayOrderHeadMpg.orderHeadId =
		 * hr_pay_order_head_post_mpg.orderHeadId"+ " and
		 * sgva_budsubhd_mst.budsubhd_id = HrPayOrderHeadMpg.subheadId"+ " and
		 * hr_pay_order_head_post_mpg.postId = OrgPostDetailsRlt.postId"+ " and
		 * OrgPostDetailsRlt.postId = OrgUserpostRlt.postId"+ " and
		 * OrgUserpostRlt.orgUserMst.userId = OrgEmpMst.orgUserMst.userId"+ "
		 * and OrgEmpMst.emp_id = "+empId;
		 */

	}

	/*
	 * * this method is not used in payroll application public List
	 * getPostListByOrderId(long orderNo) { List postList = null;
	 * 
	 * 
	 * Session hibSession = getSession();
	 * 
	 * //"13" is hard coded value coming from cmn_lookup_mst table for Primary
	 * Post. String strQuery = "select * from hr_pay_order_head_post_mpg mpg
	 * where mpg.orderHeadId=" + orderNo + " and mpg.postId in (select p.postId
	 * from org_post_mst p where p.status_lookup_id=13)"; logger.info("In
	 * OrderHeadMpgDAOImpl--Query for getting Post List from OrderNo " +
	 * strQuery); Query query = hibSession.createSQLQuery(strQuery); postList =
	 * query.list(); logger.info("Post List size in OrderHeadMpgDAOImpl from
	 * OrderNo " + postList.size()); return postList; }
	 */

	/*
	 * this method is not used in payroll application public List
	 * getorderheadpostmpg(long orderHeadPostId) { List orderheadpostList =
	 * null;
	 * 
	 * 
	 * Session hibSession = getSession();
	 * 
	 * //"13" is hard coded value coming from cmn_lookup_mst table for Primary
	 * Post. String strQuery = "select c.orderId, c.order_name,
	 * d.budsubhd_desc_long, e.postName from hr_pay_order_head_post_mpg a,"+
	 * "HrPayOrderHeadMpg b,HrPayOrderMst c, sgva_budsubhd_mst d,
	 * OrgPostDetailsRlt e where a.orderHeadPostId ="+ orderHeadPostId + "and
	 * e.postId = a.postId and a.orderHeadId = b.orderHeadId and b.orderId =
	 * c.orderId and d.lang_id = 'en_US' and d.budsubhd_id = b.subheadId"; Query
	 * query = hibSession.createSQLQuery(strQuery); orderheadpostList =
	 * query.list(); logger.info("Post List size in OrderHeadMpgDAOImpl from
	 * OrderNo " + orderheadpostList.size()); return orderheadpostList; }
	 */
	public List checkOrderheadpostAvailability(long neworderHeadId,
			long newpostId) {
		List list = null;
		Session hibSession = getSession();
		String strQuery = " From HrPayOrderHeadPostMpg as p where p.orderHeadId = "
				+ neworderHeadId + " and p.postId = " + newpostId;
		Query query = hibSession.createQuery(strQuery);
		list = query.list();
		logger.info("The Size od list is:-"+list.size());
		return list;

	}

	public List getorderHeadId(long orderId, long headId,int finYrId) {
		List orderHeadIdList = new ArrayList();

		Session hibSession = getSession();

		// "13" is hard coded value coming from cmn_lookup_mst table for Primary
		// Post.
		// String strQuery = "select b.orderHeadId from HrPayOrderMst
		// a,HrPayOrderHeadMpg b,hr_pay_order_head_post_mpg c,sgva_budsubhd_mst
		// d where b.orderId ="+ orderId +" and b.subheadId="+headId;
		String strQuery = "select b.orderHeadId,b.orderId from HrPayOrderHeadMpg b where b.orderId ="
				+ orderId + " and b.subheadId= (select shm.element_code from HrPayOrderSubHeadMpg shm where shm.sgvaBudsubhdMst.budsubhdId = "+headId+" and shm.finYearId="+finYrId+ ")";
		Query query = hibSession.createQuery(strQuery);
		if (query.list() != null && query.list().size() > 0)
			orderHeadIdList = query.list();
		logger.info("The Size of list is:-" + orderHeadIdList.size());
		return orderHeadIdList;
	}

	public List getPostIdByEmpId(long empId) {
		List orderHeadIdList = null;

		Session hibSession = getSession();

		// "13" is hard coded value coming from cmn_lookup_mst table for Primary
		// Post.
		String strQuery = "select * from OrgPostDetailsRlt r where r.orgPostMst.postId in "
				+ " (select t.orgPostMstByPostId.postId from OrgUserpostRlt t where t.orgUserMst.userId in "
				+ " (select s.orgUserMst.userId from OrgEmpMst s where s.empId= "
				+ empId + "))";
		Query query = hibSession.createQuery(strQuery);
		orderHeadIdList = query.list();
		logger.info("getPostIdByEmpId.size" + orderHeadIdList.size());
		return orderHeadIdList;
	}

	/*
	 * public HrPayOrderHeadMpg getOrderHeadId(long orderId,long headId){
	 * HrPayOrderHeadMpg hrPayOrderHeadMpg = new HrPayOrderHeadMpg(); Session
	 * hibSession = getSession(); Criteria crtHrPayOrderHeadMpg =
	 * hibSession.createCriteria(HrPayOrderHeadMpg.class);
	 * crtHrPayOrderHeadMpg.add(Restrictions.like("orderId",orderId));
	 * crtHrPayOrderHeadMpg.add(Restrictions.like("headId", headId));
	 * hrPayOrderHeadMpg = (HrPayOrderHeadMpg)
	 * crtHrPayOrderHeadMpg.uniqueResult(); return hrPayOrderHeadMpg; }
	 */
	// by manoj for vacant post issue
	public List getAllUserPostRltDatabyDesgEdit(long locId, long desgId) {
		List userpostrlt = null;
		Session hibSession = getSession();
		logger.info("Before Execution:-");
		String strQuery = " select pd.orgPostMst.postId,pd.postName,"
				+ " (select concat(b.empFname, concat(' ', concat(b.empMname,concat(' ',b.empLname)))) from OrgEmpMst b, OrgUserpostRlt     up where up.orgUserMst.userId = b.orgUserMst.userId and up.orgPostMstByPostId.postId = pd.orgPostMst.postId and b.cmnLanguageMst.langId = 1  and up.activateFlag!=0) "
				+ " from OrgPostDetailsRlt pd,OrgDesignationMst  desg "
				+ " where  desg.dsgnId = pd.orgDesignationMst.dsgnId and"
				+ " pd.cmnLocationMst.locId in (select cmnLocMst.locId from CmnLocationMst cmnLocMst where cmnLocMst.locId ="
				+ locId + " or cmnLocMst.parentLocId= " + locId
				+ ") and  pd.cmnLanguageMst.langId = 1 and desg.dsgnId ="
				+ desgId + " order by pd.postName";

		// "select a.postId,a.postName,b.empFname,b.empMname,b.empLname from
		// OrgPostDetailsRlt a,OrgEmpMst b,OrgDesignationMst desg where
		// desg.dsgnId=a.dsgnId and b.orgUserMst.userId in(select
		// c.orgUserMst.userId from org_user_mst c where c.orgUserMst.userId in
		// (select d.orgUserMst.userId from OrgUserpostRlt d where
		// d.postId=a.postId)) and a.dsgnId="+desgId+" and b.lang_id = 1 and
		// a.locId=" +locId;
		Query query = hibSession.createQuery(strQuery);
		userpostrlt = query.list();
		logger.info("List size is:-" + userpostrlt);

		return userpostrlt;
	}

	public List getAllUserPostRltDatabyDesg(long locId, long desgId) {
		List userpostrlt = null;
		Session hibSession = getSession();
		logger.info("Before Execution:-");
		String strQuery = " select pd.orgPostMst.postId,pd.postName,"
				+ " (select concat(b.empFname, concat(' ', concat(b.empMname,concat(' ',b.empLname))))"
				+ " from OrgEmpMst b,OrgUserpostRlt     up "
				+ "where up.orgUserMst.userId = b.orgUserMst.userId and b.cmnLanguageMst.langId = 1  and up.activateFlag!=0 and up.orgPostMstByPostId = pd.orgPostMst.postId) "
				+ " from OrgPostDetailsRlt pd,OrgDesignationMst  desg"
				+ " where   desg.dsgnId = pd.orgDesignationMst.dsgnId and"
				+ " pd.cmnLocationMst.locId in (select cmnLocMst.locId from CmnLocationMst cmnLocMst where cmnLocMst.locId ="
				+ locId
				+ " or cmnLocMst.parentLocId= "
				+ locId
				+ ") and  pd.cmnLanguageMst.langId = 1 and desg.dsgnId ="
				+ desgId
				+ " and pd.orgPostMst.postId not in (select distinct ohp.postId from HrPayOrderHeadPostMpg ohp ) ";

		// "select a.postId,a.postName,b.empFname,b.empMname,b.empLname from
		// OrgPostDetailsRlt a,OrgEmpMst b,OrgDesignationMst desg where
		// desg.dsgnId=a.dsgnId and b.orgUserMst.userId in(select
		// c.orgUserMst.userId from org_user_mst c where c.orgUserMst.userId in
		// (select d.orgUserMst.userId from OrgUserpostRlt d where
		// d.postId=a.postId)) and a.dsgnId="+desgId+" and b.lang_id = 1 and
		// a.locId=" +locId;
		Query query = hibSession.createQuery(strQuery);
		userpostrlt = query.list();
		logger.info("List size is:-" + userpostrlt);

		return userpostrlt;
	}

	/*
	 * this method is not used in Payroll Application public List
	 * getAllUserPostRltData(long locId) { List userpostrlt = null; Session
	 * hibSession = getSession(); logger.info("Before Execution:-"); String
	 * strQuery = " select pd.orgPostMst.postId,pd.postName," + " (select
	 * concat(b.empFname, concat(' ', concat(b.empMname,concat('
	 * ',b.empLname)))) from OrgEmpMst b where up.orgUserMst.userId =
	 * b.orgUserMst.userId and b.lang_id = 1 and up.activateFlag!=0)"+ " from
	 * OrgPostDetailsRlt pd,OrgDesignationMst desg, OrgUserpostRlt up"+ " where
	 * up.postId(+) = pd.orgPostMst.postId and desg.dsgnId = pd.dsgnId and"+ "
	 * pd.locId in (select cmnLocMst.locId from CmnLocationMst cmnLocMst where
	 * cmnLocMst.locId ="+locId+" or cmnLocMst.parentLocId= "+locId+") and
	 * pd.cmnLanguageMst.langId = 1 order by pd.postName";
	 * 
	 * 
	 * //"select a.postId,a.postName,b.empFname,b.empMname,b.empLname from
	 * OrgPostDetailsRlt a,OrgEmpMst b,OrgDesignationMst desg where
	 * desg.dsgnId=a.dsgnId and b.orgUserMst.userId in(select
	 * c.orgUserMst.userId from org_user_mst c where c.orgUserMst.userId in
	 * (select d.orgUserMst.userId from OrgUserpostRlt d where
	 * d.postId=a.postId)) and a.dsgnId="+desgId+" and b.lang_id = 1 and
	 * a.locId=" +locId; Query query = hibSession.createSQLQuery(strQuery);
	 * userpostrlt = query.list(); logger.info("List size is:-"+userpostrlt);
	 * 
	 * return userpostrlt; }
	 */
	// end by manoj for vacant post issue
	public List getheadsfromorders(long orderId, int finYrId) {
		List headList = null;

		Session hibSession = getSession();
		// "13" is hard coded value coming from cmn_lookup_mst table for Primary
		// Post.
		String strQuery = "from SgvaBudsubhdMst sgva where sgva.budsubhdId in (select shm.sgvaBudsubhdMst.budsubhdId from HrPayOrderHeadMpg mpg,HrPayOrderSubHeadMpg shm where mpg.orderId = "
				+ orderId
				+ " and mpg.subheadId = shm.element_code and shm.finYearId ="
				+ finYrId + ")";
		Query query = hibSession.createQuery(strQuery);
		headList = query.list();
		logger.info("Post List size in OrderHeadMpgDAOImpl from OrderNo "
				+ headList.size());
		return headList;

	}

	
	//varun sharma
	public List getHeadsFromOrders(long orderId, int finYrId) {
		List headList = null;

		Session hibSession = getSession();

		String strQuery = 	"SELECT shm " +
							"		FROM HrPayOrderHeadMpg mpg, HrPayOrderSubHeadMpg shm " +
							"			WHERE mpg.orderId = "
							+ 			orderId
							+ " 		AND mpg.subheadId = shm.element_code AND shm.finYearId = "
							+ 			finYrId +" ";

							
							
		Query query = hibSession.createQuery(strQuery);
		headList = query.list();
		logger.info("Post List size in OrderHeadMpgDAOImpl from OrderNo "
				+ headList.size());
		return headList;

	}
	
	public Long create(HrPayCustOrderHeadPost arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(HrPayCustOrderHeadPost arg0) {
		// TODO Auto-generated method stub

	}

	public void update(HrPayCustOrderHeadPost arg0) {
		// TODO Auto-generated method stub

	}

	public ResultObject OrderHeadPostMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getLocationCode(long userId, long langId) {
		List locId = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(" select m.locId ");
		strQuery.append(" from CmnLocationMst m ");
		strQuery.append(" where m.locationCode in ");
		strQuery.append(" (select p.locationCode ");
		strQuery.append(" from OrgUserMst         u, ");
		strQuery.append(" OrgPostDetailsRlt pd, ");
		strQuery.append(" OrgPostMst         p, ");
		strQuery.append(" OrgUserpostRlt     up ");
		strQuery
				.append(" where u.userId = up.orgUserMst.userId and pd.orgPostMst.postId = p.postId and ");
		strQuery
				.append(" up.orgPostMstByPostId.postId = p.postId and pd.cmnLanguageMst.langId = '"
						+ langId + "' and u.userId = '" + userId + "') and ");
		strQuery.append(" m.cmnLanguageMst.langId = '" + langId + "'  ");
		logger.info("Query in getSelecteddept is " + strQuery.toString());
		Query query = hibSession.createQuery(strQuery.toString());
		// logger.info("SqlQuery in getSelecteddept is " + query.);

		locId = query.list();
		return locId;

	}

	public boolean chkorderheadPostMpg(long orderId, long HeadId, long postId,
			long mpgId) {
		List checkFlag = null;

		Session hibSession = getSession();
		String strQuery = " select h.orderHeadPostId from HrPayOrderHeadPostMpg h,HrPayOrderHeadMpg oh "
				+ " where h.orderHeadPostId != "
				+ mpgId
				+ " and oh.subheadId = '"
				+ HeadId
				+ "' and oh.orderId = "
				+ orderId
				+ " and "
				+ " h.postId = "
				+ postId
				+ " and oh.orderHeadId = h.orderHeadId ";
		logger.info("query         .....\n" + strQuery);
		Query query = hibSession.createQuery(strQuery);
		checkFlag = query.list();
		if (checkFlag != null && checkFlag.size() > 0)
			return true;
		else
			return false;
	}

	/*
	 * this method is not used inpayroll application public List
	 * getAllUserPostRltDatabyOrder(String locId,long orderId) { List
	 * userpostrlt = null; Session hibSession = getSession();
	 * logger.info("Before Execution:-"); StringBuffer sb = new StringBuffer();
	 * 
	 * 
	 * 
	 * 
	 * sb.append(" select ohp.postId,pd.postName from hr_pay_order_head_post_mpg
	 * ohp ,OrgPostDetailsRlt pd , HrPayOrderHeadMpg oh , HrPayOrderMst om ");
	 * sb.append(" where ohp.orderHeadId=oh.orderHeadId and
	 * oh.orderId=om.orderId and om.orderId="+orderId+" and
	 * pd.orgPostMst.postId=ohp.postId and om.location_code = '"+locId+"' and
	 * om.lang_id = 1 and pd.cmnLanguageMst.langId = 1 ");
	 * 
	 * 
	 * Query query = hibSession.createSQLQuery(sb.toString()); userpostrlt =
	 * query.list(); logger.info("List size is:-"+userpostrlt);
	 * 
	 * return userpostrlt; }
	 */
	public List getAllUserPostRltDatabyOrder(long locId, long orderId) {
		List userpostrlt = null;
		Session hibSession = getSession();
		logger.info("Before Execution:-");
		StringBuffer sb = new StringBuffer();

		sb
				.append(" select ohp.postId,pd.postName from HrPayOrderHeadPostMpg ohp ,OrgPostDetailsRlt pd , HrPayOrderHeadMpg oh , HrPayOrderMst om  ");
		sb
				.append(" where ohp.orderHeadId=oh.orderHeadId and oh.orderId=om.orderId and om.orderId="
						+ orderId
						+ " and  pd.orgPostMst.postId=ohp.postId  and om.locationCode = "
						+ locId);

		Query query = hibSession.createQuery(sb.toString());
		userpostrlt = query.list();
		logger.info("List size is:-" + userpostrlt);

		return userpostrlt;
	}

	// added by Ankit Bhatt for Search in OHP, Order Master
	// comented by samir joshi because it hql query and On clause use in this
	// query and on clause not superted in HQl

	/*
	 * public List searchOrderPostData(String orderName,String postName,long
	 * locId) { List searchResult = new ArrayList(); Session hibSession =
	 * getSession(); StringBuffer sb = new StringBuffer(); String strQuery = new
	 * String();
	 * 
	 * 
	 * if(!orderName.equals("") && postName.equals("")) {
	 * 
	 * strQuery=" select ohp.orderHeadPostId,HrPayOrderMst.order_name," + "
	 * sgva_budsubhd_mst.budsubhd_desc_long,OrgPostDetailsRlt.postName,"+ "
	 * (select concat(concat(concat(concat(b.empFname,' '),b.empMname),'
	 * '),b.empLname) from OrgEmpMst b" + " where
	 * OrgUserpostRlt.orgUserMst.userId = b.orgUserMst.userId and b.lang_id = 1
	 * and OrgUserpostRlt.activateFlag != 0)"+ " from HrPayOrderMst,
	 * HrPayOrderHeadPostMpg ohp, HrPayOrderHeadMpg," + "
	 * sgva_budsubhd_mst,OrgPostDetailsRlt left outer join OrgUserpostRlt on
	 * OrgPostDetailsRlt.postId = OrgUserpostRlt.postId"+ " where
	 * HrPayOrderMst.orderId = HrPayOrderHeadMpg.orderId and" + "
	 * HrPayOrderHeadMpg.orderHeadId = ohp.orderHeadId and"+ "
	 * lower(HrPayOrderMst.order_name) like lower('" + orderName + "%') and " + "
	 * sgva_budsubhd_mst.budsubhd_id = HrPayOrderHeadMpg.subheadId and" + "
	 * ohp.postId = OrgPostDetailsRlt.postId "+ //" OrgPostDetailsRlt.postId =
	 * OrgUserpostRlt.postId " + " and OrgPostDetailsRlt.locId in (select
	 * cmnLocMst.locId from CmnLocationMst cmnLocMst where cmnLocMst.locId
	 * ="+locId+" or cmnLocMst.parentLocId= "+locId+ ") order by
	 * HrPayOrderMst.order_name,sgva_budsubhd_mst.budsubhd_desc_long," + "
	 * OrgPostDetailsRlt.postName"; } else if(!postName.equals("") &&
	 * orderName.equals("")) { strQuery=" select
	 * ohp.orderHeadPostId,HrPayOrderMst.order_name," + "
	 * sgva_budsubhd_mst.budsubhd_desc_long,OrgPostDetailsRlt.postName,"+ "
	 * (select concat(concat(concat(concat(b.empFname,' '),b.empMname),'
	 * '),b.empLname) from OrgEmpMst b" + " where
	 * OrgUserpostRlt.orgUserMst.userId = b.orgUserMst.userId and b.lang_id = 1
	 * and OrgUserpostRlt.activateFlag != 0)"+ " from HrPayOrderMst,
	 * HrPayOrderHeadPostMpg, HrPayOrderHeadMpg," + "
	 * sgva_budsubhd_mst,OrgPostDetailsRlt left outer join OrgUserpostRlt on
	 * OrgPostDetailsRlt.postId = OrgUserpostRlt.postId"+ " where
	 * HrPayOrderMst.orderId = HrPayOrderHeadMpg.orderId and" + "
	 * HrPayOrderHeadMpg.orderHeadId = ohp.orderHeadId "+ " and
	 * lower(OrgPostDetailsRlt.postName) like lower('" + postName + "%') and "+ "
	 * sgva_budsubhd_mst.budsubhd_id = HrPayOrderHeadMpg.subheadId and" + "
	 * ohp.postId = OrgPostDetailsRlt.postId "+ //" OrgPostDetailsRlt.postId =
	 * OrgUserpostRlt.postId " + " and OrgPostDetailsRlt.locId in (select
	 * cmnLocMst.locId from CmnLocationMst cmnLocMst where cmnLocMst.locId
	 * ="+locId+" or cmnLocMst.parentLocId= "+locId+ ") order by
	 * HrPayOrderMst.order_name,sgva_budsubhd_mst.budsubhd_desc_long," + "
	 * OrgPostDetailsRlt.postName";
	 *  } else {
	 * 
	 * strQuery=" select ohp.orderHeadPostId,HrPayOrderMst.order_name," + "
	 * sgva_budsubhd_mst.budsubhd_desc_long,OrgPostDetailsRlt.postName,"+ "
	 * (select concat(concat(concat(concat(b.empFname,' '),b.empMname),'
	 * '),b.empLname) from OrgEmpMst b" + " where
	 * OrgUserpostRlt.orgUserMst.userId = b.orgUserMst.userId and b.lang_id = 1
	 * and OrgUserpostRlt.activateFlag != 0)"+ " from HrPayOrderMst,
	 * HrPayOrderHeadPostMpg, HrPayOrderHeadMpg," + "
	 * sgva_budsubhd_mst,OrgPostDetailsRlt left outer join OrgUserpostRlt on
	 * OrgPostDetailsRlt.postId = OrgUserpostRlt.postId"+ " where
	 * HrPayOrderMst.orderId = HrPayOrderHeadMpg.orderId and" + "
	 * HrPayOrderHeadMpg.orderHeadId = ohp.orderHeadId "+ " and
	 * lower(HrPayOrderMst.order_name) like lower('" + orderName + "%') " + "
	 * and lower(OrgPostDetailsRlt.postName) like lower('" + postName + "%') and "+ "
	 * sgva_budsubhd_mst.budsubhd_id = HrPayOrderHeadMpg.subheadId and" + "
	 * ohp.postId = OrgPostDetailsRlt.postId "+ //" OrgPostDetailsRlt.postId =
	 * OrgUserpostRlt.postId " + " and OrgPostDetailsRlt.locId in (select
	 * cmnLocMst.locId from CmnLocationMst cmnLocMst where cmnLocMst.locId
	 * ="+locId+" or cmnLocMst.parentLocId= "+locId+ ") order by
	 * HrPayOrderMst.order_name,sgva_budsubhd_mst.budsubhd_desc_long," + "
	 * OrgPostDetailsRlt.postName"; }
	 * 
	 * logger.info("Query for Search is " + strQuery); Query query =
	 * hibSession.createSQLQuery(strQuery); searchResult = query.list();
	 * logger.info("List size is:-"+searchResult); return searchResult; }
	 */

	// Added By Varun For Billwise Search
	// added by Ankit Bhatt for Search in OHP, Order Master
	// restructre this query and remove on clause so we can change it to HQL
	public List searchOrderPostData(String orderName, String postName,long locId, int finYrId) {
		List searchResult = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		String strQuery = new String();

		if (!orderName.equals("") && postName.equals("")) {

			strQuery = " select hr_pay_order_head_post_mpg.order_head_post_id,hr_pay_order_mst.order_name,"
					+ " sgva_budsubhd_mst.budsubhd_desc_long,org_post_details_rlt.post_name,"
					+ " (select concat(concat(concat(concat(b.emp_fname,' '),b.emp_mname),' '),b.emp_lname) from org_emp_mst b"
					+ " where org_userpost_rlt.user_id = b.user_id and b.lang_id = 1 and org_userpost_rlt.activate_flag != 0)"
					+ " from hr_pay_order_mst, hr_pay_order_head_post_mpg, hr_pay_order_head_mpg,"
					+ " sgva_budsubhd_mst,org_post_details_rlt left outer join org_userpost_rlt on org_post_details_rlt.post_id = org_userpost_rlt.post_id"
					+ " where hr_pay_order_mst.order_id = hr_pay_order_head_mpg.order_id and"
					+ " hr_pay_order_head_mpg.order_head_id = hr_pay_order_head_post_mpg.order_head_id and"
					+ " lower(hr_pay_order_mst.order_name) like lower('"
					+ orderName
					+ "%') and "
					+ " 		sgva_budsubhd_mst.budsubhd_id = hr_pay_order_subhead_mpg.budsubhd_id and " 
					+ " 		hr_pay_order_subhead_mpg.element_code = hr_pay_order_head_mpg.subhead_id and hr_pay_order_subhead_mpg.fin_yr_id=" 
					+ finYrId 
					+ "	 and"
					+ " hr_pay_order_head_post_mpg.post_id = org_post_details_rlt.post_id "
					+
					// " org_post_details_rlt.post_id = org_userpost_rlt.post_id
					// " +
					" and org_post_details_rlt.loc_id in (select cmnLocMst.loc_id from cmn_location_mst cmnLocMst where cmnLocMst.loc_id ="
					+ locId
					+ " or cmnLocMst.parent_loc_id= "
					+ locId
					+ ") order by  hr_pay_order_mst.order_name,sgva_budsubhd_mst.budsubhd_desc_long,"
					+ " org_post_details_rlt.post_name";
		} else if (!postName.equals("") && orderName.equals("")) {
		   strQuery = " SELECT hr_pay_order_head_post_mpg.order_head_post_id, hr_pay_order_mst.order_name,"
					+ " sgva_budsubhd_mst.budsubhd_desc_long, org_post_details_rlt.post_name, "
					+ " 	(SELECT concat(concat(concat(concat(b.emp_fname,' '),b.emp_mname),' '),b.emp_lname) " 
					+ "			FROM org_emp_mst b"
					+ " 			WHERE org_userpost_rlt.user_id = b.user_id and b.lang_id = 1 and org_userpost_rlt.activate_flag != 0)"
					+ " FROM 	hr_pay_order_mst, hr_pay_order_head_post_mpg, hr_pay_order_head_mpg, hr_pay_order_subhead_mpg, "
					+ " 		sgva_budsubhd_mst,org_post_details_rlt left outer join org_userpost_rlt on org_post_details_rlt.post_id = org_userpost_rlt.post_id"
					+ " WHERE 	hr_pay_order_mst.order_id = hr_pay_order_head_mpg.order_id and"
					+ " 		hr_pay_order_head_mpg.order_head_id = hr_pay_order_head_post_mpg.order_head_id "
					+ " 		and lower(org_post_details_rlt.post_name) like lower('"
					+ postName
					+ "%') and "
					+ " 		sgva_budsubhd_mst.budsubhd_id = hr_pay_order_subhead_mpg.budsubhd_id and " 
					+ " 		hr_pay_order_subhead_mpg.element_code = hr_pay_order_head_mpg.subhead_id and hr_pay_order_subhead_mpg.fin_yr_id=" 
					+ finYrId 
					+ "	 and"
					+ " 		hr_pay_order_head_post_mpg.post_id = org_post_details_rlt.post_id "
					+
					// " org_post_details_rlt.post_id = org_userpost_rlt.post_id
					// " +
					" 			and org_post_details_rlt.loc_id in (select cmnLocMst.loc_id from cmn_location_mst cmnLocMst where cmnLocMst.loc_id ="
					+ locId
					+ " 		or cmnLocMst.parent_loc_id= "
					+ locId
					+ ") ORDER BY  hr_pay_order_mst.order_name,sgva_budsubhd_mst.budsubhd_desc_long,"
					+ " org_post_details_rlt.post_name";

		} else {

			strQuery = " select hr_pay_order_head_post_mpg.order_head_post_id,hr_pay_order_mst.order_name,"
					+ " sgva_budsubhd_mst.budsubhd_desc_long,org_post_details_rlt.post_name,"
					+ " (select concat(concat(concat(concat(b.emp_fname,' '),b.emp_mname),' '),b.emp_lname) from org_emp_mst b"
					+ " where org_userpost_rlt.user_id = b.user_id and b.lang_id = 1 and org_userpost_rlt.activate_flag != 0)"
					+ " from hr_pay_order_mst, hr_pay_order_head_post_mpg, hr_pay_order_head_mpg,"
					+ " sgva_budsubhd_mst,org_post_details_rlt left outer join org_userpost_rlt on org_post_details_rlt.post_id = org_userpost_rlt.post_id"
					+ " where hr_pay_order_mst.order_id = hr_pay_order_head_mpg.order_id and"
					+ " hr_pay_order_head_mpg.order_head_id = hr_pay_order_head_post_mpg.order_head_id "
					+ " and lower(hr_pay_order_mst.order_name) like lower('"
					+ orderName
					+ "%') "
					+ " and lower(org_post_details_rlt.post_name) like lower('"
					+ postName
					+ "%') and "
					+ " 		sgva_budsubhd_mst.budsubhd_id = hr_pay_order_subhead_mpg.budsubhd_id and " 
					+ " 		hr_pay_order_subhead_mpg.element_code = hr_pay_order_head_mpg.subhead_id and hr_pay_order_subhead_mpg.fin_yr_id=" 
					+ finYrId 
					+ "	 and"
					+ " hr_pay_order_head_post_mpg.post_id = org_post_details_rlt.post_id "
					+
					// " org_post_details_rlt.post_id = org_userpost_rlt.post_id
					// " +
					" and org_post_details_rlt.loc_id in (select cmnLocMst.loc_id from cmn_location_mst cmnLocMst where cmnLocMst.loc_id ="
					+ locId
					+ " or cmnLocMst.parent_loc_id= "
					+ locId
					+ ") order by  hr_pay_order_mst.order_name,sgva_budsubhd_mst.budsubhd_desc_long,"
					+ " org_post_details_rlt.post_name";
		}

		logger.info("Query for Search is " + strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		searchResult = query.list();
		logger.info("List size is:-" + searchResult);
		return searchResult;
	}

	public List getPostFromBill(long billId) {
		List postList = null;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("   select pd.postName,pp.psrId,ohp.orderHeadPostId  ");
		sb
				.append("  from OrgPostDetailsRlt  pd,HrPayPsrPostMpg pp,HrPayOrderHeadPostMpg ohp  ");
		sb
				.append("  where pd.orgPostMst.postId = ohp.postId and pp.postId = ohp.postId and pp.postId = pd.orgPostMst.postId and  pd.cmnLanguageMst.langId =1 and pp.billNo='"
						+ billId + "' order by pp.psrId  ");
		logger
				.info("In OrderHeadMpgDAOImpl--Query for getting Post List from OrderNo "
						+ sb.toString());
		Query query = hibSession.createQuery(sb.toString());
		postList = query.list();
		logger.info("Post List size in OrderHeadMpgDAOImpl from BillNo "
				+ postList.size());
		return postList;
	}
	// Ended By Varun For Billwise Search
	
	public String fetchSubHeadOfPost(long postId){
		String headId;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();

		sb.append("SELECT orderHead.SUBHEAD_ID  FROM HR_PAY_ORDER_HEAD_MPG orderHead,HR_PAY_ORDER_HEAD_POST_MPG headPost where "); 
		sb.append("headPost.ORDER_HEAD_ID = orderHead.ORDER_HEAD_ID and headPost.POST_ID = ");
		sb.append(postId);
		Query query = hibSession.createSQLQuery(sb.toString());
		headId = query.uniqueResult().toString();
		return headId;
	}
	
	public long isOrderHeadMpgExist(long orderId,String headId){
		long orderHeadPK = 0;
		HrPayOrderHeadMpg hrPayHeadMpg = null;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		

		sb.append("FROM HrPayOrderHeadMpg where orderId = "); 
		sb.append(orderId);
		sb.append(" and subheadId = '");
		sb.append(headId);
		sb.append("'");
		
		Query query = hibSession.createQuery(sb.toString());
		List rs = query.list();
		if(!rs.isEmpty() && rs != null){
			hrPayHeadMpg = (HrPayOrderHeadMpg)rs.get(0);
			if(hrPayHeadMpg != null)
				return hrPayHeadMpg.getOrderHeadId();
			else
				return 0;
		}
		else
			return orderHeadPK;
			
	}


}
