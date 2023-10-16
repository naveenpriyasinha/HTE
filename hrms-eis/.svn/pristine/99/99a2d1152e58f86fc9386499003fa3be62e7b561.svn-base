package com.tcs.sgv.eis.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.allowance.valueobject.HrPayArgumentMst;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.CommAllOtherDetailsVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGISDtls;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtlsHst;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

@SuppressWarnings("unchecked")
public class OtherDetailDAOImpl extends GenericDaoHibernateImpl<HrEisOtherDtls, Long> implements OtherDetailDAO
{

	Log logger = LogFactory.getLog(getClass());
	DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
	String sdate = sbConf.getSysdate();

	private ResourceBundle constantBundle = ResourceBundle.getBundle("../resources/Payroll");
	private ResourceBundle eisConstantBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");
	private String sixPayCommissionId = constantBundle.getString("commissionSixId");
	private String serviceActive = constantBundle.getString("serviceActive");
	long allowanceComponent = Long.parseLong(payrollBundle.getString("allowanceComponent").toString());
	private long gradeCode1 = Long.parseLong(eisConstantBundle.getString("GradeCode1"));
	private long gradeCode2 = Long.parseLong(eisConstantBundle.getString("GradeCode2"));

	private long PB2 = Long.parseLong(constantBundle.getString("PB2")); //Pay Band - 2
	private long PB3 = Long.parseLong(constantBundle.getString("PB3")); //Pay band - 3

	public OtherDetailDAOImpl(Class<HrEisOtherDtls> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getAllOtherData()
	{
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisOtherDtls.class);

		objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
		objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");

		objCrt.setFetchMode("hrEisEmpMst.orgEmpMst", FetchMode.JOIN);
		objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");
		//11 jan 2012
		objCrt.addOrder(Order.asc("orgEmpMst.empLname"));
		objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
		objCrt.addOrder(Order.asc("orgEmpMst.empMname"));

		return objCrt.list();
	}

	public List getAllEmpId()//get empId of othersDtls
	{
		List list = new ArrayList();
		Session hibSession = getSession();
		Query query = hibSession.createQuery("select hrEisEmpMst.empId from HrEisOtherDtls");
		list = query.list();
		return list;
	}

	/**
	 * @author : varun sharma
	 * @purpose: returns SgdId of an employee for a given pay commission: 6th pay
	 */
	public List getSgdId(long empId)
	{
		List updatedList = null;
		Query query = null;
		Session session = getSession();

		try
		{
			//fetch grade (i.e. class) to which this employee belongs

			Criteria objCrt = null;
			Session hibSession = getSession();
			objCrt = hibSession.createCriteria(HrEisOtherDtls.class);

			objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
			objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");

			objCrt.setFetchMode("hrEisEmpMst.orgEmpMst", FetchMode.JOIN);
			objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");

			objCrt.add(Restrictions.eq("orgEmpMst.empId", empId));

			HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls) objCrt.list().get(0);

			long gradeId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeId();
			long payBandId = 0;

			logger.info("gradeId in dao:" + gradeId);

			if (gradeId == gradeCode1) //if class-1
			{
				payBandId = PB3;
				logger.info("pay band id is PB2: " + payBandId);
			}
			else if (gradeId == gradeCode2)//if class-2
			{
				payBandId = PB2;
				logger.info("pay band id is PB3: " + payBandId);
			}

			StringBuffer strQuery = new StringBuffer();

			strQuery.append("SELECT ( SELECT s FROM HrEisSgdMpg s																					").append("	  		WHERE 	s.hrEisScaleMst.scaleId=scaleCommissionMpg.commissionSix.scaleId									").append("	and s.hrEisGdMpg.gdMapId=hrEisSgdMpg.hrEisGdMpg.gdMapId and hrEisSgdMpg.hrEisGdMpg.gdMapId = s.hrEisGdMpg.gdMapId ) ").append("	FROM HrEisSgdMpg hrEisSgdMpg, HrEisEmpMst hrEisEmpMst, HrEisOtherDtls hrEisOtherDtls, 								").append("		 HrEisScaleMst hrEisScaleMst, ScaleCommissionMpg scaleCommissionMpg, OrgEmpMst orgEmpMst 						").append("	WHERE 		orgEmpMst.empId=" + empId).append("			and orgEmpMst.empId=hrEisEmpMst.orgEmpMst.empId 															").append("			and hrEisOtherDtls.hrEisEmpMst.empId=hrEisEmpMst.empId 														").append("			and hrEisOtherDtls.hrEisSgdMpg.sgdMapId=hrEisSgdMpg.sgdMapId 												").append("			and hrEisSgdMpg.hrEisScaleMst.scaleId=hrEisScaleMst.scaleId 												").append("			and hrEisScaleMst.scaleId=scaleCommissionMpg.commissionFive.scaleId											");

			if (payBandId != 0)
			{
				logger.info("considering paybandId: " + payBandId);
				strQuery.append("		and	scaleCommissionMpg.payBand.lookupId=" + payBandId);
			}

			//create query
			query = session.createQuery(strQuery.toString());

			//execute query
			updatedList = query.list();

		}
		catch (Exception e)
		{
			logger.info("Exception in executing query..");
			logger.info(e);
			logger.error("Error is: " + e.getMessage());
		}

		return updatedList;
	}

	/**
	 * @author : varun sharma
	 * @purpose: returns list of bill nos.
	 */
	public List<MstDcpsBillGroup> getBillNos(long locId, long finYrId)
	{
		Session hibSession = getSession();
		Query query = null;

		StringBuffer strQuery = new StringBuffer();

		strQuery.append("  FROM MstDcpsBillGroup   MstDcpsBillGroup   							");
		strQuery.append("		 WHERE 	MstDcpsBillGroup.LocId=" + locId);
		//strQuery.append("		  		and MstDcpsBillGroup  .finYearId="+finYrId 			 );
		strQuery.append("	ORDER BY MstDcpsBillGroup.dcpsDdoBillGroupId								");

		logger.info("Query for get getBillNos is---->>>>" + strQuery.toString());

		//create query
		query = hibSession.createQuery(strQuery.toString());

		//execute query
		return query.list();

	}//end method

	/**
	 * @author : varun sharma
	 * @purpose: returns list of bill wise all the employees which does not belong to sixth pay commission.
	 */
	public List getBillwiseAllOldCommissionEmployeees(long langId, long locId, long billId)
	{

		Session hibSession = getSession();
		List oldEmpList = null;

		StringBuffer strQuery = new StringBuffer();

		try
		{

			strQuery.append("SELECT orgEmpMst.empId,																						");
			strQuery.append("		concat(coalesce(orgEmpMst.empPrefix,' '),' ',coalesce(orgEmpMst.empLname,' '),' ',coalesce(orgEmpMst.empFname,' '),' ',coalesce(orgEmpMst.empMname,' '),' ')");
			strQuery.append("  FROM OrgEmpMst orgEmpMst, HrEisEmpMst hrEisEmpMst, HrEisOtherDtls hrEisOtherDtls,  							");
			strQuery.append("		HrEisSgdMpg hrEisSgdMpg, HrEisScaleMst hrEisScaleMst, OrgUserpostRlt orgUserPostRlt,					");
			strQuery.append("		HrPayPsrPostMpg hrPayPsrPostMpg																			");
			strQuery.append("	WHERE 	orgEmpMst.cmnLanguageMst.langId=" + langId);
			strQuery.append("			and orgEmpMst.empSrvcExp>:todayDate																	");
			/*strQuery.append("			and orgEmpMst.activateFlag=" +serviceActive							     		 					 );*/
			strQuery.append("			and orgEmpMst.empId = hrEisEmpMst.orgEmpMst.empId													");
			strQuery.append("			and hrEisEmpMst.cmnLocationMst.locId=" + locId);
			strQuery.append("			and orgEmpMst.orgUserMst.userId=orgUserPostRlt.orgUserMst.userId									");
			strQuery.append("			and orgUserPostRlt.activateFlag=" + serviceActive);
			strQuery.append("			and hrEisEmpMst.empId = hrEisOtherDtls.hrEisEmpMst.empId											");
			strQuery.append("			and hrEisOtherDtls.hrEisSgdMpg.sgdMapId = hrEisSgdMpg.sgdMapId										");
			strQuery.append("			and hrEisSgdMpg.hrEisScaleMst.scaleId = hrEisScaleMst.scaleId										");
			strQuery.append("			and hrEisScaleMst.hrPayCommissionMst.id!=" + sixPayCommissionId);
			strQuery.append("			and hrPayPsrPostMpg.billNo=" + billId);
			strQuery.append("			and hrPayPsrPostMpg.loc_id=" + locId);
			strQuery.append("			and hrPayPsrPostMpg.postId=orgUserPostRlt.orgPostMstByPostId.postId									");
			strQuery.append("	ORDER BY orgEmpMst.empLname,orgEmpMst.empFname,orgEmpMst.empMname											");

			//create query
			Query query = hibSession.createQuery(strQuery.toString());
			query.setParameter("todayDate", new java.util.Date());

			//execute query
			oldEmpList = query.list();

			logger.info("TTTTTTTTTTThew List is " + oldEmpList.size());
		}
		catch (Exception e)
		{
			logger.info("Exception occured: \n" + e);
		}
		return oldEmpList;
	}//end method

	/* 	public HrEisOtherDtls getOtherData(String sid)
	    {
	 		HrEisOtherDtls hrOtherInfo = new HrEisOtherDtls();
	        Session hibSession = getSession();
	        String query1 = "from HrEisOtherDtls as empLookup where empLookup.otherId = "
	                    + sid ;
	        Query sqlQuery1 = hibSession.createQuery(query1);
	        hrOtherInfo = (HrEisOtherDtls)sqlQuery1.uniqueResult();
	        logger.info("query is----"+sqlQuery1);
	        return hrOtherInfo;
	    }*/

	public HrEisOtherDtls getOtherData(Long empId)
	{
		logger.info("empId is: " + empId);

		HrEisOtherDtls hrOtherInfo = new HrEisOtherDtls();
		Session hibSession = getSession();
		String query1 = "from HrEisOtherDtls as empLookup where empLookup.hrEisEmpMst.orgEmpMst.empId = " + empId;
		Query sqlQuery1 = hibSession.createQuery(query1);

		if (sqlQuery1.list().size() < 1)
		{
			hrOtherInfo.setOtherId(0);
			logger.info("list size is : " + sqlQuery1.list().size());
		}
		else
		{
			hrOtherInfo = (HrEisOtherDtls) sqlQuery1.uniqueResult();
			logger.info("setting sqlQuery's uniqueResult");
		}

		logger.info("query is----" + sqlQuery1);
		return hrOtherInfo;
	}

	public HrEisOtherDtls getOtherDataByHrEis(Long empId)
	{
		logger.info("empId is: " + empId);

		HrEisOtherDtls hrOtherInfo = new HrEisOtherDtls();
		Session hibSession = getSession();
		String query1 = "from HrEisOtherDtls as empLookup where empLookup.hrEisEmpMst.empId = " + empId;
		Query sqlQuery1 = hibSession.createQuery(query1);

		if (sqlQuery1.list().size() < 1)
		{
			hrOtherInfo.setOtherId(0);
			logger.info("list size is : " + sqlQuery1.list().size());
		}
		else
		{
			hrOtherInfo = (HrEisOtherDtls) sqlQuery1.uniqueResult();
			logger.info("setting sqlQuery's uniqueResult");
		}

		logger.info("query is----" + sqlQuery1);
		return hrOtherInfo;
	}

	//added by samir joshi for 6th pay commoision
	public HrEisScaleMst getOldScaleFromEmpid(Long empId)
	{
		HrEisScaleMst hrEisScaleMst = new HrEisScaleMst();
		Session hibSession = getSession();
		String query1 = " from HrEisScaleMst as sm where sm.scaleId in " + " (select sgd.hrEisScaleMst.scaleId from HrEisSgdMpg as sgd where sgd.sgdMapId in" + " (select oth.hrEisSgdMpg.sgdMapId from HrEisOtherDtlsHst as oth where oth.id.trnCounter in" + " (select max(oth1.id.trnCounter)  from HrEisOtherDtlsHst as oth1 where " + // oth1.updatedDate >='1-mar-2009' and remove because no employee found
				"  ( oth1.updatedDate <='31-mar-2009' or oth1.createdDate >'31-mar-2009') and oth1.hrEisEmpMst.empId=" + empId + " ) and" + " oth.hrEisEmpMst.empId=" + empId + " ))";

		Query sqlQuery1 = hibSession.createQuery(query1);
		hrEisScaleMst = (HrEisScaleMst) sqlQuery1.uniqueResult();
		logger.info("query is----" + sqlQuery1);
		return hrEisScaleMst;
	}

	//ended by samir

	/*	public List getAllChildren(long aStrLookUpName)
	   {
	       List dataList = new ArrayList();
	       Log logger = LogFactory.getLog(getClass());
	       Session hibSession = getSession(); 
	       StringBuffer sb = new StringBuffer();
	       sb.append("SELECT    SUBSTR ( path, 1, INSTR ( path , ' / ' )- 1)  AS ParentId, argument_id,parent_argue_id,table_name,selcol_name,condcol_name FROM (SELECT parent_argue_id,argument_id,table_name,selcol_name,condcol_name,SUBSTR ( SYS_CONNECT_BY_PATH ( h1.parent_argue_id , ' / '), 1 + LENGTH (' / ')");
	       sb.append(")");
	       sb.append("|| ' / 'AS path ");
	       sb.append("FROM  hr_pay_argument_mst h1");
	       sb.append(" CONNECT BY  h1.parent_argue_id = prior h1.argument_id ");
	       sb.append(")");
	       sb.append(" where ");
	       sb.append(" SUBSTR ( path  , 1  , INSTR ( path  , ' / ' ) - 1  ) = "); 
	       sb.append(aStrLookUpName);
	       sb.append(" or argument_id = ");
	       sb.append(aStrLookUpName);
	       sb.append(" ORDER BY argument_id ,");
	       sb.append(" ParentId");
	       Query selectQuery=hibSession.createSQLQuery(sb.toString());
	       
	       logger.info("The String is" + sb.toString());
	       dataList = selectQuery.list();
	       logger.info("total children found = "+ dataList.size());
	       return dataList;
	           
	   }*/

	public List getAllChildren(long aStrLookUpName)
	{

		List argumentList = new ArrayList();

		List newArgumentList = new ArrayList();
		long parentId;

		List rowArray = new ArrayList();

		Session hibSession = getSession();
		HrPayArgumentMst hrPayArgumentMst = new HrPayArgumentMst();

		Criteria crtArgument = hibSession.createCriteria(HrPayArgumentMst.class);
		crtArgument.addOrder(Order.asc("argumentId"));
		argumentList = crtArgument.list();
		HrPayArgumentMst tempArgumentMstVO = new HrPayArgumentMst();

		//logger.info("The Size of List is:-"+argumentList.size());
		int i = 0;
		int j = 0;
		for (i = 0; i < argumentList.size(); i++)
		{
			hrPayArgumentMst = (HrPayArgumentMst) argumentList.get(i);
			parentId = hrPayArgumentMst.getParentArgueId();

			//    		added by Ankit Bhatt
			if (parentId == aStrLookUpName || hrPayArgumentMst.getArgumentId() == aStrLookUpName)
			{
				//	logger.info("The ParentId is:-"+parentId);
				rowArray = new ArrayList();
				rowArray.add(hrPayArgumentMst.getParentArgueId());
				rowArray.add(hrPayArgumentMst.getArgumentId());
				rowArray.add(hrPayArgumentMst.getParentArgueId());
				rowArray.add(hrPayArgumentMst.getTableName());
				rowArray.add(hrPayArgumentMst.getSelcolName());
				rowArray.add(hrPayArgumentMst.getCondcolName());
				newArgumentList.add(rowArray);
			}
			//ended by Ankit Bhatt

			//logger.info("Continue The Size of Temp List1 is:-"+tempArgumentList.size());
			//logger.info("End of Outer Loop");
			if (parentId == -1)
			{
				continue;
			}
			else
			{

				for (j = 0; j < argumentList.size(); j++)
				{
					tempArgumentMstVO = (HrPayArgumentMst) argumentList.get(j);
					long parentTemp = tempArgumentMstVO.getParentArgueId();
					//parentId = tempArgumentMstVO.getParentArgueId();
					if (parentId == tempArgumentMstVO.getArgumentId())
					{
						//added by Ankit Bhatt
						if (parentTemp == aStrLookUpName || hrPayArgumentMst.getArgumentId() == aStrLookUpName)
						{
							//	logger.info("The ParentId is:-"+parentId);
							rowArray = new ArrayList();
							rowArray.add(tempArgumentMstVO.getParentArgueId());
							rowArray.add(hrPayArgumentMst.getArgumentId());
							rowArray.add(hrPayArgumentMst.getParentArgueId());
							rowArray.add(hrPayArgumentMst.getTableName());
							rowArray.add(hrPayArgumentMst.getSelcolName());
							rowArray.add(hrPayArgumentMst.getCondcolName());
							newArgumentList.add(rowArray);
						}
						parentId = tempArgumentMstVO.getParentArgueId();
						//ended by Ankit Bhatt 			  				
						//	logger.info("The Size of Temp List2 is:-"+tempArgumentList.size());
					}
					//	logger.info("inner loop "+j);
					//}
				}
			}
		}
		logger.info("newArgumentList.size()" + newArgumentList.size());
		return newArgumentList;
	}

	public List getTableData(String tableName, String selColName, String conColName, String conColValue)
	{
		List dataList = new ArrayList();
		Log logger = LogFactory.getLog(getClass());
		Session hibSession = getSession();

		StringBuffer sb = new StringBuffer();
		sb.append("Select ");
		sb.append(selColName);
		sb.append(" from ");
		sb.append(tableName);
		sb.append(" where ");
		sb.append(conColName);
		sb.append("= ");
		sb.append(conColValue);

		logger.info("The query is " + sb.toString());
		Query selectQuery = hibSession.createSQLQuery(sb.toString());
		dataList = selectQuery.list();
		logger.info("The Size of List is:-" + dataList.size());
		return dataList;

	}

	public List<CmnCityMst> getAllcity(CmnLanguageMst cmnLanguageMst)
	{
		List dataList = new ArrayList();
		Criteria objCrt = null;
		Log logger = LogFactory.getLog(getClass());
		Session hibSession = getSession();
		long flag = 1;
		objCrt = hibSession.createCriteria(CmnCityMst.class);
		objCrt.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
		objCrt.add(Restrictions.eq("activateFlag", flag));
		objCrt.addOrder(Order.asc("cityName"));
		dataList = objCrt.list();
		logger.info("total children found = " + dataList.size());
		return dataList;

	}

	public List getEmpAvailable(long empId)
	{
		List hrOtherInfo = new ArrayList();

		Session hibSession = getSession();
		String query = "from HrEisOtherDtls as eisOthDtls where eisOthDtls.hrEisEmpMst.orgEmpMst.empId = " + empId;
		Query sqlQuery = hibSession.createQuery(query);
		hrOtherInfo = sqlQuery.list();
		return hrOtherInfo;
	}

	public List getQuaterType(long langId)
	{
		List lstQuaterType = new ArrayList();
		Session hibSession = getSession();

		String query = "from HrQuaterTypeMst as quaterType where quaterType.cmnLanguageMst.langId = " + langId + " order by quaterType.quaType";
		Query sqlQuery = hibSession.createQuery(query);
		lstQuaterType = sqlQuery.list();

		return lstQuaterType;
	}

	public List getQuaterType()
	{
		List lstQuaterType = new ArrayList();
		Session hibSession = getSession();
		String query = "from HrQuaterTypeMst as quaterType order by quaterType.quaType";

		Query sqlQuery = hibSession.createQuery(query);
		lstQuaterType = sqlQuery.list();

		return lstQuaterType;
	}

	public List getCityName(String ElementCode, long langId)
	{
		Criteria objCrt = null;
		try
		{
			Session hibSession = getSession();
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			logger.info("***********************" + ElementCode);
			objCrt = hibSession.createCriteria(CmnCityMst.class);
			objCrt.add(Restrictions.eq("cityCode", ElementCode));
			objCrt.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
			logger.info("returning" + cmnLanguageMst.getLangId());
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}
		return objCrt.list();
	}

	public List getPostDetailData(long langId, long userId)
	{
		List orgPostDetailsRlt = new ArrayList();

		Session hibSession = getSession();
//		String rownum = sbConf.getRowNum();
		String query = " select rlt.orgPostMstByPostId.postId,rlt.empPostId from OrgUserpostRlt rlt where rlt.activateFlag=1 and rlt.orgUserMst.userId=" + userId + "  order by rlt.startDate desc  ";
		Query sqlQuery = hibSession.createQuery(query);
		List postIdList = sqlQuery.list();
		if (postIdList != null && postIdList.size() > 0)
		{
			Object[] postList = (Object[]) postIdList.get(0);
			query = " from OrgPostDetailsRlt post where post.cmnLanguageMst.langId=" + langId + " and post.orgPostMst.postId in (" + postList[0] + ") order by post.orgPostMst.startDate desc ";
		}
		sqlQuery = hibSession.createQuery(query);

		orgPostDetailsRlt = sqlQuery.list();
		logger.info("*****************" + orgPostDetailsRlt.size());
		return orgPostDetailsRlt;
	}

	public List getAllOtherData(List userList)
	{
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisOtherDtls.class);
		if (userList != null && userList.size() > 0)
		{
			objCrt.createAlias("hrEisEmpMst", "hrEisEmp");
			objCrt.createAlias("hrEisEmp.orgEmpMst", "orgEmpMst");
			if (userList != null && userList.size() > 0)
				objCrt.add(Restrictions.in("orgEmpMst.orgUserMst", userList));
			objCrt.addOrder(Order.asc("orgEmpMst.empLname"));
			objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
			objCrt.addOrder(Order.asc("orgEmpMst.empMname"));

		}

		return objCrt.list();
	}

	public List getAllEmpData(List empIdList, List userList)
	{
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class);
		objCrt.setFetchMode("orgEmpMst", FetchMode.JOIN);
		objCrt.createAlias("orgEmpMst", "orgEmpMst");
		if (empIdList != null && empIdList.size() > 0)
			objCrt.add(Restrictions.not(Restrictions.in("orgEmpMst.empId", empIdList)));
		if (userList != null && userList.size() > 0)
			objCrt.add(Restrictions.in("orgEmpMst.orgUserMst", userList));
		return objCrt.list();
	}

	/*public List getAllOtherData(long userId,long langId,long empId)
	{
	   Criteria objCrt = null;
	   Session hibSession = getSession();
	   StringBuffer query = new StringBuffer();
	   
	   query.append(" select ot.other_id, o.emp_prefix||' '||o.emp_fname||' '||o.emp_mname||' '||o.emp_lname,grade.grade_name,dsgn.dsgn_name,ot.other_current_basic,o.emp_id ");
	   query.append(" from hr_eis_other_dtls    ot, ");
	   query.append(" org_userpost_rlt     up, ");
	   query.append(" org_post_details_rlt pd, ");
	   query.append(" org_emp_mst          o, ");
	   query.append(" org_grade_mst        grade, ");
	   query.append(" org_designation_mst  dsgn ");
	   query.append(" where up.user_id = pd.post_id and pd.lang_id = "+langId+" and ot.emp_id = o.emp_id and ");
	   query.append(" o.user_id = up.user_id and ");
	   query.append(" pd.loc_id in (select c.loc_id ");
	   query.append(" from cmn_location_mst     c, ");
	   query.append(" org_post_details_rlt pd, ");
	   query.append(" org_userpost_rlt     up ");
	   query.append(" where up.user_id =  "+userId+"  and up.post_id = pd.post_id and ");
	   query.append(" pd.loc_id = c.loc_id and up.post_id = pd.post_id and ");
	   query.append(" pd.lang_id =  "+langId+"  and c.lang_id =  "+langId+" ) ");
	   query.append(" and o.grade_id = grade.grade_id and dsgn.dsgn_id = pd.dsgn_id ");
	   query.append(" and grade.lang_id  =  "+langId+"  and dsgn.lang_id =  "+langId+"  and up.activate_flag = 1 ");
	   if(empId!=0)
	   	query.append(" and o.emp_id =  "+empId+"   ");	
	   query.append(" order by o.emp_fname,o.emp_mname,o.emp_lname ");
		Query sqlQuery = hibSession.createSQLQuery(query.toString());	      	
	   ArrayList dataList=new ArrayList();
	   List RowList=sqlQuery.list(); 	
	   return RowList;
	} 	
	
	*/

	/**
	 * 	For Other Details Display.
	 */
	public List getAllOtherData(String locationCode, long langId, long empId)
	{

		Calendar cal = Calendar.getInstance();

//		Date dt = new Date();

		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdfObj1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date endMonthDate = cal.getTime();

//		DBsysdateConfiguration sbConf = new DBsysdateConfiguration();
//		SimpleDateFormat sdfObj = sbConf.GetDateFormat();

		//String endDate  = sdfObj.format(endMonthDate);
		String endDate = sdfObj1.format(endMonthDate);

//		Criteria objCrt = null;
		Session hibSession = getSession();
		logger.info("the sysdeat value is===========>" + sdate);
		StringBuffer query = new StringBuffer();
		query.append("select ot.otherId,");

		//12 jan 2012
		//query.append("emp.empFname || ' ' || emp.empMname || ' ' || emp.empLname,");
		query.append("concat(coalesce(emp.empPrefix,' '),' ',coalesce(emp.empLname,' '),' ',coalesce(emp.empFname,' '),' ',coalesce(emp.empMname,' ')),");
		//end
		//  query.append(" concat(emp.empFname,' ', emp.empMname,' ',emp.empLname,' '),");
		//  query.append(" concat(emp.empFname,' ',emp.empLname),");
		query.append("grade.gradeName,");
		query.append("dsgn.dsgnName,");
		query.append("ot.otherCurrentBasic, ");
		query.append("up.startDate, ");
		query.append("up.endDate, ");
		query.append("eisEmp.sevarthEmpCode ");
		query.append("from HrEisOtherDtls ot,");
		query.append("OrgEmpMst emp,");
		query.append("HrEisEmpMst eisEmp,");
		query.append("OrgGradeMst grade,");
		query.append("OrgDesignationMst dsgn,");
		query.append("OrgUserpostRlt up,");
		query.append("OrgPostDetailsRlt pd ");
		query.append("WHERE ot.hrEisEmpMst.empId = eisEmp.empId and eisEmp.orgEmpMst.empId = emp.empId and emp.empSrvcExp >= " + sdate + " and ");

		query.append("emp.orgGradeMst.gradeId = grade.gradeId and emp.orgUserMst.userId = up.orgUserMst.userId and ");
		query.append("pd.cmnLocationMst.locId in (SELECT cmnLocMst.locId FROM CmnLocationMst cmnLocMst WHERE cmnLocMst.locationCode='" + locationCode + "' and cmnLocMst.cmnLanguageMst.langId=" + langId + ") and ");
		query.append("pd.orgPostMst.postId = up.orgPostMstByPostId.postId and ");//up.activateFlag=1 and ");
		//for lang id
		query.append("emp.cmnLanguageMst.langId=" + langId + "and " + "grade.cmnLanguageMst.langId=" + langId + "and " + "dsgn.cmnLanguageMst.langId=" + langId + "and " + "pd.cmnLanguageMst.langId=" + langId);
		query.append(" and pd.orgDesignationMst.dsgnId = dsgn.dsgnId and ( up.endDate = null or up.endDate >= '" + endDate + "')");

		if (empId != 0)
		{
			query.append(" and emp.empId = " + empId);
		}
		//12 jan 2012
		query.append(" order by emp.empLname,emp.empFname,emp.empMname ");
		Query sqlQuery = hibSession.createQuery(query.toString());
//		ArrayList dataList = new ArrayList();
		List RowList = sqlQuery.list();
		logger.info("Hum sop pakday gayu " + RowList.size());
		return RowList;
	}

	// for allowances and deduction display
	public List getAllOtherDataForAllow(long locationId, long langId, long empId)
	{
        Criteria objCrt = null;
        Session hibSession = getSession();
        StringBuffer query = new StringBuffer();
        
        query.append(" select ot.otherId, concat(coalesce(o.empPrefix,' '),' ',coalesce(o.empLname,' '),' ',coalesce(o.empFname,' '),' ',coalesce(o.empMname,' '),' '),grade.gradeName,dsgn.dsgnName,e.empType,o.empId,ot.incomeTax ");
        query.append(" from HrEisOtherDtls    ot, ");
        query.append(" OrgUserpostRlt     up, ");
        query.append(" OrgPostDetailsRlt pd, ");
        query.append(" OrgEmpMst          o,HrEisEmpMst       e, ");
        query.append(" OrgGradeMst        grade, ");
        query.append(" OrgDesignationMst  dsgn ");
        query.append(" where up.orgPostMstByPostId.postId = pd.orgPostMst.postId and pd.cmnLanguageMst.langId = "+langId+" and ot.hrEisEmpMst.orgEmpMst.empId = o.empId and ");
        query.append(" o.orgUserMst.userId = up.orgUserMst.userId and ");
        query.append(" pd.cmnLocationMst.locId = "+locationId);
        query.append(" and o.orgGradeMst.gradeId = grade.gradeId and dsgn.dsgnId = pd.orgDesignationMst.dsgnId and o.empId = e.orgEmpMst.empId  and up.activateFlag = 1 ");
        query.append(" and grade.cmnLanguageMst.langId  =  "+langId+"  and dsgn.cmnLanguageMst.langId =  "+langId+"  ");
        if(empId!=0)
        	query.append(" and e.empId =  "+empId+"   ");	
        query.append(" order by  o.empLname,o.empFname,o.empMname ");
        
        logger.info("the new query from otherDetail Dao is "+query.toString());
     	Query sqlQuery = hibSession.createQuery(query.toString());	      	
     	ArrayList dataList=new ArrayList();
        List RowList=sqlQuery.list(); 	
        return RowList;
    }

	public List getAllOtherEmpData(long userId)
	{
		List empList = new ArrayList();
		Session hibSession = getSession();

		String query1 = " from  HrEisEmpMst e where  ";
		if (userId != 0)//location specific
		{
			query1 += "  e.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId and up.activateFlag=1 and pd.cmnLocationMst.locId in ";
			query1 += " ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = " + userId + "  ) ) ";
		}
		query1 += " and e.empId not in (select ot.hrEisEmpMst.empId from HrEisOtherDtls ot) ";

		query1 += " order by e.orgEmpMst.empLname,e.orgEmpMst.empFname,e.orgEmpMst.empMname ";
		Query sqlQuery = hibSession.createQuery(query1);

		empList = sqlQuery.list();
		return empList;
	}

	/* 	public List getAllOtherEmpDataHrr(long userId)
	    {
	        List empList=new ArrayList();
	        
	 		Session hibSession = getSession();
	 		Criteria criteria=hibSession.createCriteria(HrEisOtherDtls.class);
	 		criteria.createAlias("HrEisOtherDtls", "hrothedtl");
	 		criteria.createAlias("hrEisEmpMst", "hrEisEmp");
	 		criteria.createAlias("hrEisEmp.orgEmpMst", "orgEmpMst");
	 		criteria.createAlias("orgEmpMst.orgUserMst", "orgUserMst");
	 		criteria=criteria.setFetchMode("hrEisEmp", FetchMode.JOIN).setFetchMode("orgEmpMst", FetchMode.JOIN).add(Restrictions.eq("orgUserMst.userId", userId)).setProjection(Projections.property("hrothedtl.*"));
	 		empList=criteria.list();
	        return empList;
	    }*/

	public List<HrEisOtherDtls> getAllOtherEmpDataHrr(long userId)
	{
		List empList = new ArrayList();
		logger.info("User Id is:-" + userId);
		String queryFrom = " from  HrEisOtherDtls ot where ot.hrEisEmpMst.orgEmpMst.empId in (select em.empId from OrgEmpMst em where em.orgUserMst.userId=" + userId + " and em.cmnLanguageMst.langId=1)";
		Session hibSession = getSession();
		Query sqlQuery = hibSession.createQuery(queryFrom);
		logger.info("The Values are:-" + queryFrom);
		logger.info("jay mataji");
		empList = sqlQuery.list();
		logger.info("jay mataji");
		return empList;
	}

	public List getOtherDataFromHst(Long empId, int month, int year)
	{
//		HrEisOtherDtlsHst hrOtherInfo = new HrEisOtherDtlsHst();
		Session hibSession = getSession();
		List hrOtherDtlsList = new ArrayList();

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, year);
		cal2.set(Calendar.MONTH, month);

		int maxDays = cal2.getActualMaximum(5);

		cal2.set(Calendar.DATE, maxDays);

		java.util.Date currDate1 = cal2.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

		String currDate = sdf.format(currDate1);

		logger.info("the date is " + currDate);

		String query1 = "from HrEisOtherDtlsHst as empLookup where empLookup.hrEisEmpMst.empId = " + empId + " and empLookup.updatedDate <= '" + currDate + "' order by empLookup.updatedDate desc,empLookup.id.trnCounter desc";

		Query sqlQuery1 = hibSession.createQuery(query1);

		logger.info("query is----" + sqlQuery1);

		hrOtherDtlsList = sqlQuery1.list();

		if (hrOtherDtlsList == null || hrOtherDtlsList.size() <= 0)
		{
			String query = "from HrEisOtherDtlsHst as empLookup where empLookup.hrEisEmpMst.empId = " + empId + " order by empLookup.updatedDate asc";

			Query sqlQuery = hibSession.createQuery(query);

			logger.info("query is----" + sqlQuery);

			hrOtherDtlsList = sqlQuery.list();
		}

		return hrOtherDtlsList;

	}

	public Set getUserPostRltForEmp(OrgUserMst orgUserMst, int month, int year, CmnLocationMst cmnLocationMst)
	{
//		HrEisOtherDtlsHst hrOtherInfo = new HrEisOtherDtlsHst();
		Session hibSession = getSession();
		Set userPostRltSet = new HashSet(0);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, year);
		cal2.set(Calendar.MONTH, month);
		cal2.set(Calendar.DATE, 1);

		Date currDate1 = cal2.getTime();
		String currStartDate = sdf.format(currDate1);

		int maxDays = cal2.getActualMaximum(5);
		cal2.set(Calendar.DATE, maxDays);
		currDate1 = cal2.getTime();
		String currEndDate = sdf.format(currDate1);

		logger.info("the date is " + currStartDate + " and end date is " + currEndDate);

		String query1 = "select up from OrgUserpostRlt as up,OrgPostDetailsRlt as pd where up.orgUserMst.userId = " + orgUserMst.getUserId() + " and up.startDate <= '" + currEndDate + "'" + " and (up.endDate is Null or up.endDate >= '" + currStartDate + "' )" + " and up.orgPostMstByPostId.postId = pd.orgPostMst.postId and pd.cmnLocationMst.locId = '" + cmnLocationMst.getLocId() + "'  order by up.startDate desc";
		Query sqlQuery1 = hibSession.createQuery(query1);

		List userPostList = sqlQuery1.list();

		logger.info("query is----" + sqlQuery1);

		if (userPostList != null && userPostList.size() > 0)
		{
			for (int j = 0; j < userPostList.size(); j++)
			{
				OrgUserpostRlt orgUserPostRlt = (OrgUserpostRlt) userPostList.get(j);

				userPostRltSet.add(orgUserPostRlt);

			}
		}

		return userPostRltSet;
	}

	public List getPayFixFromUser(OrgUserMst orgUserMst, SgvcFinYearMst finYrMst)
	{

		Session hibSession = getSession();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date startDate = finYrMst.getFromDate();
		String finStartDate = sdf.format(startDate);
		Date endDate = finYrMst.getToDate();
		String finEndDate = sdf.format(endDate);

		logger.info("the date is " + startDate + " and end date is " + endDate);

		String query1 = "from HrPayfixMst as payfix where payfix.userId.userId = " + orgUserMst.getUserId() + " and payfix.payFixDate <= '" + finEndDate + "' and payfix.payFixDate >= '" + finStartDate + "' and payfix.flagType = 'N' and " + "  payfix.prevPayScale.scaleId != payfix.revPayScale.scaleId " + "  order by payfix.payFixDate desc";

		Query sqlQuery1 = hibSession.createQuery(query1);

		List hrPayfixMstList = sqlQuery1.list();

		logger.info("the list size of payfix is +" + hrPayfixMstList.size());

		return hrPayfixMstList;
	}

	/**
	 * Author 	:- Urvin Shah
	 * Date		:- 08-04-2009 
	 * @param userId
	 * @return
	 */
	public List getBranchByOrgEmpId(long userId, String currDate)
	{
		Session hibSession = getSession();
//		long statusFlag = Long.parseLong(constantBundle.getString("activate"));
		String strQuery = "from OrgPostDetailsRlt a where a.orgPostMst.postId in ( select up.orgPostMstByPostId.postId From OrgUserpostRlt up where up.orgUserMst.userId=" + userId + ")";
		//" and up.activateFlag="+statusFlag+" and (up.endDate is null or up.endDate>'"+currDate+"'))";
		// " and up.activateFlag="+statusFlag+")";//+" and (up.endDate is null or up.endDate>'"+currDate+"'))";

		Query queryBranch = hibSession.createQuery(strQuery);
		//queryBranch.setParameter("currentDate",new Date());

		List lstBranch = queryBranch.list();
		logger.info("the list size of payfix is +" + lstBranch.size());
		return lstBranch;
	}

	//added by ravysh to fetch bill no and psr no
	public HashMap getBillNoPsrNoByOrgEmpId(long empId, String locId)
	{
		HashMap resultMap = new HashMap();

		/*Date today=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		String todaydate=sdf.format(today);
		
		HashMap resultMap=new HashMap();
		String lStrPsrNo="";
		String lStrBillNo="";
		long BillId=0;
		
		Session hibSession = getSession();
		
		String strQuery = "select pp.psrId,sm.dcpsDdoBillDescription,pp.billNo  from OrgEmpMst m, OrgUserpostRlt ur, MstDcpsBillGroup   sm,HrPayPsrPostMpg pp" +
			" where m.empId="+empId+" and m.orgUserMst.userId=ur.orgUserMst.userId and pp.postId=ur.orgPostMstByPostId.postId and " +
			" sm.dcpsDdoBillGroupId=pp.billNo and (ur.endDate is null or ur.endDate>:Today) ";
		
		
		Query queryBillNo = hibSession.createQuery(strQuery);
		queryBillNo.setParameter("Today",today);
		
		List lstBillNo = queryBillNo.list();
		for (Iterator it = lstBillNo.iterator(); it.hasNext();) {
		   Object[] row = (Object[]) it.next();
		    lStrPsrNo = String.valueOf((Long)row[0]);
		    lStrBillNo = String.valueOf(row[1]);
		    BillId=(Long)row[2];
		 
		   resultMap.put("CurrPsrNo", lStrPsrNo);
		   resultMap.put("CurrBillNo", lStrBillNo);
		}
		
		if(BillId!=0){
		
		logger.info(" Entered into block to get next other id ");
		strQuery = "select hh.psrId,ur.orgUserMst.userId,dd.otherId from HrPayPsrPostMpg hh, OrgUserpostRlt ur, HrEisOtherDtls dd" +
		" where hh.billNo="+BillId+" and dd.hrEisEmpMst.empId= (select ee.empId from HrEisEmpMst ee, OrgEmpMst em " +
		" where ee.orgEmpMst.empId=em.empId and em.orgUserMst.userId=ur.orgUserMst.userId)" +
		" and hh.postId=ur.orgPostMstByPostId.postId and (ur.endDate is null or ur.endDate>:Today) and hh.psrId>"+lStrPsrNo+" order by hh.psrId ";
		
		queryBillNo = hibSession.createQuery(strQuery);
		queryBillNo.setParameter("Today",today);
		
		queryBillNo.setMaxResults(1);
		List lstNextPsrNo = queryBillNo.list();
		for (Iterator it = lstNextPsrNo.iterator(); it.hasNext();) {
		   Object[] row = (Object[]) it.next();
		   
		   resultMap.put("NextPsrNo", String.valueOf((Long)row[0]));
		   resultMap.put("NextOtherId", String.valueOf((Long)row[2]));
		   resultMap.put("NextBillNo", lStrBillNo);
		  
		 }
		
		
		
		if(lstNextPsrNo.size()==0)
		{
		strQuery="select ss.dcpsDdoBillDescription, pp.psrId,dd.otherId from HrEisOtherDtls dd, OrgUserpostRlt rl, " +
			"HrEisEmpMst hem , OrgEmpMst oem, MstDcpsBillGroup   ss , HrPayPsrPostMpg pp where pp.loc_id=ss.LocId and " +
			"pp.loc_id="+locId+" and  rl.orgPostMstByPostId.postId=pp.postId and dd.hrEisEmpMst.empId=hem.empId " +
			"and hem.orgEmpMst.empId=oem.empId and rl.orgUserMst.userId=oem.orgUserMst.userId and " +
			"(rl.endDate>:Today or rl.endDate is null) and ss.dcpsDdoBillGroupId>"+lStrBillNo+" and ss.dcpsDdoBillGroupId=pp.billNo order by ss.dcpsDdoBillGroupId, pp.psrId";
		
		queryBillNo = hibSession.createQuery(strQuery);
		queryBillNo.setParameter("Today",today);
		
		queryBillNo.setMaxResults(1);
		List lstNextBillAndPsrNo = queryBillNo.list();
		
		for (Iterator it = lstNextBillAndPsrNo.iterator(); it.hasNext();) {
		   Object[] row = (Object[]) it.next();
		   
		   resultMap.put("NextBillNo", String.valueOf((Long)row[0]));
		   resultMap.put("NextPsrNo", String.valueOf((Long)row[1]));
		   resultMap.put("NextOtherId", String.valueOf((Long)row[2]));
		   
		  
		 }
		
		}
		
		}
		
		// Added following code block to get previos other empId
		
		
		if(BillId!=0){
		logger.info(" Entered into block to get previos other id ");
		strQuery = "select hh.psrId,ur.orgUserMst.userId,dd.otherId from HrPayPsrPostMpg hh, OrgUserpostRlt ur, HrEisOtherDtls dd" +
		" where hh.billNo="+BillId+" and dd.hrEisEmpMst.empId= (select ee.empId from HrEisEmpMst ee, OrgEmpMst em " +
		" where ee.orgEmpMst.empId=em.empId and em.orgUserMst.userId=ur.orgUserMst.userId)" +
		" and hh.postId=ur.orgPostMstByPostId.postId and (ur.endDate is null or ur.endDate>:Today) and hh.psrId<"+lStrPsrNo+" order by hh.psrId desc";

		queryBillNo = hibSession.createQuery(strQuery);
		queryBillNo.setParameter("Today",today);

		queryBillNo.setMaxResults(1);
		List lstPreviousPsrNo = queryBillNo.list();
		for (Iterator it = lstPreviousPsrNo.iterator(); it.hasNext();) {
			Object[] row = (Object[]) it.next();

			resultMap.put("PreviousPsrNo", String.valueOf((Long)row[0]));
			resultMap.put("PreviousOtherId", String.valueOf((Long)row[2]));
			resultMap.put("PreviousBillNo", lStrBillNo);

		}



		if(lstPreviousPsrNo.size()==0)
		{
			strQuery="select ss.billId, pp.psrId,dd.otherId from HrEisOtherDtls dd, OrgUserpostRlt rl, " +
			"HrEisEmpMst hem , OrgEmpMst oem, MstDcpsBillGroup   ss , HrPayPsrPostMpg pp where pp.loc_id=ss.LocId and " +
			"pp.loc_id="+locId+" and  rl.orgPostMstByPostId.postId=pp.postId and dd.hrEisEmpMst.empId=hem.empId " +
			"and hem.orgEmpMst.empId=oem.empId and rl.orgUserMst.userId=oem.orgUserMst.userId and " +
			"(rl.endDate>:Today or rl.endDate is null) and ss.dcpsDdoBillGroupId<"+lStrBillNo+" and ss.dcpsDdoBillGroupId=pp.billNo order by ss.dcpsDdoBillGroupId desc, pp.psrId desc";

			queryBillNo = hibSession.createQuery(strQuery);
			queryBillNo.setParameter("Today",today);

			queryBillNo.setMaxResults(1);
			List lstNextBillAndPsrNo = queryBillNo.list();

			for (Iterator it = lstNextBillAndPsrNo.iterator(); it.hasNext();) {
				Object[] row = (Object[]) it.next();

				resultMap.put("PreviousBillNo", String.valueOf((Long)row[0]));
				resultMap.put("PreviousPsrNo", String.valueOf((Long)row[1]));
				resultMap.put("PreviousOtherId", String.valueOf((Long)row[2]));


			}

		}

		}
		
		// Ended
		*/return resultMap;
	}

	//added by manish
	public List getPostIdUserId(long empID)
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select  post.post_id,post.user_id from ORG_USERPOST_RLT post,ORG_EMP_MST org where org.USER_ID=post.USER_ID and org.EMP_ID=" + empID);
		logger.info("Query for get getPostId is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		resList = sqlQuery.list();

		return resList;
	}

	//ended by manish
	//added by manish 
	public List getPayCommission()
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" from HrPayCommissionMst");
		logger.info("Query for get PayCommission is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();

		return resList;
	}

	// added by khushal for basic innner
	public HrEisOtherDtlsHst getHrEisOtherDtlsHst(long otherId, int trnCounter)
	{
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" from HrEisOtherDtlsHst hst where hst.id.otherId =" + otherId + " and hst.id.trnCounter=" + trnCounter + "");
		logger.info("Query for getHrEisOtherDtlsHst is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		if (sqlQuery.list() != null && sqlQuery.list().size() > 0)
			return (HrEisOtherDtlsHst) sqlQuery.uniqueResult();
		else
			return null;

	}

	public OrgEmpMst getOrgEmpMst(long empId)
	{
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" from OrgEmpMst oem where oem.empId=" + empId + "");
		logger.info("Query for getHrEisOtherDtlsHst is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		if (sqlQuery.list() != null && sqlQuery.list().size() > 0)
			return (OrgEmpMst) sqlQuery.uniqueResult();
		else
			return null;
	}

	public HrEisEmpMst getHrEisEmpMst(long empId)
	{
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append(" from HrEisEmpMst hem where hem.orgEmpMst.empId=" + empId + "");
		logger.info("Query for getHrEisOtherDtlsHst is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		if (sqlQuery.list() != null && sqlQuery.list().size() > 0)
			return (HrEisEmpMst) sqlQuery.uniqueResult();
		else
			return null;

	}

	public CmnLocationMst getCmnLocationMst(long locId)
	{
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select hem  from CmnLocationMst hem where hem.locId=" + locId + "");
		logger.info("Query for getHrEisOtherDtlsHst is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		if (sqlQuery.list() != null && sqlQuery.list().size() > 0)
			return (CmnLocationMst) sqlQuery.uniqueResult();
		else
			return null;

	}

	public long getCount(String tempSevarthEmpCode)
	{
		logger.info("Inside getCmnLookupId method");
		List list = new ArrayList();
		long count = 0;
		Session hibSession = getSession();
		String hql = "select max(hem.sevarthEmpCode) from HrEisEmpMst hem where hem.sevarthEmpCode like  '%" + tempSevarthEmpCode + "%'";
		Query sqlQuery = hibSession.createQuery(hql.toString());
		list = sqlQuery.list();
		if (list != null && list.size() > 0)
		{
			if (list.get(0) != null)
			{
				String maxSevaarthCode = list.get(0).toString();
				count = Long.parseLong(maxSevaarthCode.substring(maxSevaarthCode.length() - 2, maxSevaarthCode.length()));
			}
		}
		else
		{
			count = 0;
		}
		logger.info("returning value of cmnLookupId is" + count);
		return count;
	}

	//ended by khushal

	/*public List getGradePay(long id)
	{
		List resList=null;
		
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer(); 
		query.append(" from HrEisScaleMst where HrEisScaleMst.");
		logger.info("Query for get PayCommission is---->>>>"+query.toString());
		Query sqlQuery=hibSession.createQuery(query.toString());										
		resList=sqlQuery.list();
			
	return resList;
	}*/
	//ended by manish
	// Added by Muneendra
	public List getDCPSFlag(long empId)
	{
//		List lstDCPSFlag = new ArrayList();
		Session hibSession = getSession();
		String query = "Select me.dcpsOrGpf from MstEmp as me where me.orgEmpMstId = " + empId;

		Query sqlQuery = hibSession.createQuery(query);
		// lstDCPSFlag = sqlQuery.list();

		if (sqlQuery.list() != null && sqlQuery.list().size() > 0)
		{
			logger.info("lstDCPSFlag size:::::::::::::" + sqlQuery.list().size());
			return sqlQuery.list();

		}
		else
			return null;

		//return lstDCPSFlag;
	}

	public List getDCPSData(long empId, int month, long finYrId)
	{
		List lstDCPSData = new ArrayList();
		Session hibsession = getSession();
		String strQuery = "Select contribute.regStatus FROM TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and emp.orgEmpMstId= " + empId + "  and contribute.monthId = " + month + " and contribute.finYearId= " + finYrId;
		Query query = hibsession.createQuery(strQuery);

		lstDCPSData = query.list();
		logger.info("lstDCPSData size:::::::::::::" + lstDCPSData.size());

		return lstDCPSData;

	} // Ended by Muneendra

	/* (non-Javadoc)
	 * author@manish khunt
	 * @see com.tcs.sgv.eis.dao.OtherDetailDAO#getDCPSData(String, int, long)
	 */
	public List getDCPSData(String empId, int month, long finYrId)
	{
		List lstDCPSData = new ArrayList();
		Session hibsession = getSession();
		String strQuery = "";
		strQuery = "select * from TRN_DCPS_CONTRIBUTION trn , mst_dcps_emp emp  where emp.DCPS_EMP_ID = trn.DCPS_EMP_ID and trn.MONTH_ID =" + month;
		strQuery += " and trn.FIN_YEAR_ID =" + finYrId + " and trn.REG_STATUS in (3,1,4) and trn.TYPE_OF_PAYMENT = 700046 and emp.ORG_EMP_MST_ID in (" + empId;
		strQuery += ")";
		Query query = hibsession.createSQLQuery(strQuery);

		lstDCPSData = query.list();
		logger.info("lstDCPSData size:::::::::::::" + lstDCPSData.size());

		return lstDCPSData;

	}

	/* author@manish khunt
	 * (non-Javadoc)
	 * This method is used to get post's configuraton where employee will be transfered
	 * @see com.tcs.sgv.eis.dao.OtherDetailDAO#getPostConfiguration(long)
	 */
	public List getPostConfiguration(long postId)
	{
		List lLst = new ArrayList();
		Session hibsession = getSession();
		StringBuffer stringBuffer = new StringBuffer();

		/*	stringBuffer.append("select psr.billNo,bill.dcpsDdoCode,ddo.locationCode  from HrPayPsrPostMpg psr , MstDcpsBillGroup bill,OrgDdoMst ddo,OrgUserpostRlt up ");
			stringBuffer.append("where ddo.ddoCode=bill.dcpsDdoCode and psr.billNo=bill.dcpsDdoBillGroupId and psr.postId = up.orgPostMstByPostId.postId and psr.postId=");
		*/
		stringBuffer.append("select psr.billNo,bill.dcpsDdoCode,ddo.locationCode  from HrPayPsrPostMpg psr , MstDcpsBillGroup bill,OrgDdoMst ddo,OrgPostMst up ");
		stringBuffer.append("where ddo.ddoCode=bill.dcpsDdoCode and psr.billNo=bill.dcpsDdoBillGroupId and psr.postId = up.postId and psr.postId=");
		stringBuffer.append(postId);

		Query query = hibsession.createQuery(stringBuffer.toString());
		logger.info("The query for getPostConfiguration"+query.toString());
		try
		{
			lLst = query.list();
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
			logger.info("Error occured in OtherDetailDAOImpl#getPostConfiguration" + e.getMessage());
		}
		return lLst;

	}

	/**
	 * 
	 * author@manish khunt
	 * This method is used to get the employee's configuration details required to transfer him/her
	 * @param orgEmpId
	 * @return
	 */
	public OrgUserpostRlt getEmplConfiguration(long orgEmpId, boolean isTransfer)
	{
		OrgUserpostRlt userpostRlt = null;
		try
		{

			Session hibsession = getSession();
			StringBuffer stringBuffer = new StringBuffer();

			stringBuffer.append("select up from OrgEmpMst org,OrgUserpostRlt up, HrPayPsrPostMpg psr ");
			stringBuffer.append(" where  org.orgUserMst.userId=up.orgUserMst.userId and psr.postId = up.orgPostMstByPostId.postId and org.empId=");
			stringBuffer.append(orgEmpId);

			if (isTransfer == false)
				stringBuffer.append(" and up.activateFlag=1");

			Query query = hibsession.createQuery(stringBuffer.toString());

			if (query.list() != null && query.list().size() != 0)
			{
				userpostRlt = (OrgUserpostRlt) query.list().get(0);
			}
			else if (isTransfer == false && query.list() == null)
			{
				StringBuffer stringBufferNoInitialPost = new StringBuffer();
				stringBufferNoInitialPost.append("select up from OrgEmpMst org,OrgUserpostRlt up, HrPayPsrPostMpg psr, OrgPostMst op ");
				stringBufferNoInitialPost.append(" where  org.orgUserMst.userId=up.orgUserMst.userId and psr.postId = up.orgPostMstByPostId.postId and op.postId = up.orgPostMstByPostId.postId and org.empId=");
				stringBufferNoInitialPost.append(orgEmpId);

				if (isTransfer == false)
					stringBufferNoInitialPost.append(" and op.postTypeLookupId.lookupId = 10001198135");

				Query queryForNoInitialPost = hibsession.createQuery(stringBufferNoInitialPost.toString());
				if (queryForNoInitialPost.list() != null && !queryForNoInitialPost.list().isEmpty())
					userpostRlt = (OrgUserpostRlt) queryForNoInitialPost.list().get(0);

			}

			//logger.info("Primary Key of orgUserPostRlt is "+userpostRlt.getEmpPostId());
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
			logger.info("Error occured in OtherDetailDAOImpl#getEmplConfiguration" + e.getMessage());
		}
		return userpostRlt;

	}

	/**
	 *author@manish khunt
	 *This method is used to check whether a post is vacant ot not
	 * @param postId
	 * @return OrgUserpostRlt of old post if post is not vacant 
	 */
	public OrgUserpostRlt checkVacant(long postId)
	{
		OrgUserpostRlt userpostRlt = null;
		Session hibsession = getSession();
		StringBuffer stringBuffer = new StringBuffer();
		List<OrgUserpostRlt> lst = null;

		stringBuffer.append("select up  from OrgUserpostRlt up where up.orgPostMstByPostId.postId=");
		stringBuffer.append(postId);
		Query query = hibsession.createQuery(stringBuffer.toString());
		try
		{

			lst = query.list();
			if (lst != null && lst.size() > 0)
			{
				userpostRlt = lst.get(0);
			}
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
			logger.info("Error occured in OtherDetailDAOImpl#getEmplConfiguration" + e.getMessage());
		}
		return userpostRlt;
	}

	public CmnLocationMst getCmnLocationMst(String ddoCode)
	{
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select hem from CmnLocationMst hem,OrgDdoMst ddo where hem.locId=ddo.hodLocCode and ddo.ddoCode='" + ddoCode + "'");
		logger.info("Query for getHrEisOtherDtlsHst is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		if (sqlQuery.list() != null && sqlQuery.list().size() > 0)
			return (CmnLocationMst) sqlQuery.uniqueResult();
		else
			return null;

	}

	public long getEisEmpIdUsingDcpsId(long dcpsEmpId)
	{
		Session hibsession = getSession();
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append("select eisMst.empId from MstEmp dcpsMst,OrgEmpMst empMst,HrEisEmpMst eisMst where dcpsMst.orgEmpMstId=empMst.empId ");
		stringBuffer.append(" and eisMst.orgEmpMst.empId = empMst.empId  and dcpsMst.dcpsEmpId=" + dcpsEmpId);

		Query query = null;
		query = hibsession.createQuery(stringBuffer.toString());

		if (query.uniqueResult().toString() != null && query.uniqueResult().toString().length() > 0)
			return Long.parseLong(query.uniqueResult().toString());

		return 0;

	}

	public boolean changePersonalDtls(Map changedDtlsMap)
	{
		logger.info("Inside changePersonalDtls DAO Method ");
//		int noOfRows = 0;
		char changedGender = 'A';
		String physicallyChallenged = "";
		String changedSalutation = "";
		String changedDOJ = "";
		String changedDOB = "";
		String changedPFSeries = "";
		String changedPFAcctNo = "";
		String empFname = "";
		String empMname = "";
		String empLname = "";
		String changedPAN = "";

		if (changedDtlsMap.get("changedGender") != null)
			changedGender = changedDtlsMap.get("changedGender").toString().charAt(0);
		if (changedDtlsMap.get("physicallyChallenged") != null)
			physicallyChallenged = changedDtlsMap.get("physicallyChallenged").toString();
		if (changedDtlsMap.get("changedSalutation") != null)
			changedSalutation = changedDtlsMap.get("changedSalutation").toString();
		if (changedDtlsMap.get("changedDOJ") != null)
			changedDOJ = changedDtlsMap.get("changedDOJ").toString();
		if (changedDtlsMap.get("changedDOB") != null)
			changedDOB = changedDtlsMap.get("changedDOB").toString();
		if (changedDtlsMap.get("changedPAN") != null)
			changedPAN = changedDtlsMap.get("changedPAN").toString();
		if (changedDtlsMap.get("changedPFSeries") != null)
			changedPFSeries = changedDtlsMap.get("changedPFSeries").toString();
		if (changedDtlsMap.get("changedPFAcctNo") != null)
			changedPFAcctNo = changedDtlsMap.get("changedPFAcctNo").toString();
		if (changedDtlsMap.get("empFname") != null)
			empFname = changedDtlsMap.get("empFname").toString();
		if (changedDtlsMap.get("empMname") != null)
			empMname = changedDtlsMap.get("empMname").toString();
		if (changedDtlsMap.get("empLname") != null)
			empLname = changedDtlsMap.get("empLname").toString();

		long eisEmpId = Long.valueOf(changedDtlsMap.get("eisEmpId").toString());
		long orgEmpId = Long.valueOf(changedDtlsMap.get("orgEmpId").toString());
		long userId = Long.valueOf(changedDtlsMap.get("userId").toString());

		Session hibsession = getSession();
		StringBuffer stringBuffer = new StringBuffer();

		Query query = null;
		if (changedGender != 'A' && changedGender != ' ')
		{
			query = null;
			stringBuffer.append("update HR_EIS_EMP_MST set EMP_GENDER ='" + changedGender + "' where EMP_ID =" + eisEmpId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (physicallyChallenged != null && physicallyChallenged.length() > 0 && physicallyChallenged != "")
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update HR_EIS_OTHER_DTLS set PHY_CHALLENGED ='" + physicallyChallenged + "' where EMP_ID =" + eisEmpId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (changedSalutation != null && changedSalutation.length() > 0 && changedSalutation != "")
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update ORG_EMP_MST set EMP_PREFIX ='" + changedSalutation + "' where EMP_ID =" + orgEmpId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (changedDOJ != null && changedDOJ.toString() != "" && changedDOJ.toString().length() > 0)
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update ORG_EMP_MST set EMP_DOJ ='" + changedDOJ + "' where EMP_ID =" + orgEmpId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (changedDOB != null && changedDOB.toString() != "" && changedDOB.toString().length() > 0)
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update ORG_EMP_MST set EMP_DOB ='" + changedDOB + "' where EMP_ID =" + orgEmpId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (changedPAN != null && changedPAN != "" && changedPAN.length() > 0)
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update HR_EIS_PROOF_DTL set PROOF_NUM ='" + changedPAN + "' where USER_ID =" + userId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (changedPFSeries != null && changedPFSeries.toString().length() > 0 && changedPFSeries != "" && changedPFAcctNo != "" && changedPFAcctNo != null && changedPFAcctNo.toString().length() > 0)
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update HR_PAY_GPF_DETAILS set PF_SERIES =" + changedPFSeries + " ,GPF_ACC_NO =" + changedPFAcctNo + " where USER_ID = " + userId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (empLname != null && empLname != "" && empMname != null && empMname != "" && empFname != null && empFname != "")
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update org_emp_mst set EMP_FNAME ='" + empFname + "',EMP_MNAME='" + empMname + "',EMP_LNAME ='" + empLname + "'  where EMP_ID =" + orgEmpId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}

		return true;

	}

	public boolean changeOtherDtls(Map changedDtlsMap)
	{
		logger.info("Inside changeOtherDtls DAO Method ");
//		int noOfRows = 0;
		String changedPFSeries = "";
		String changedPFAcctNo = "";
		String changedBankActNo = "";
		String changedBankBranch = "";

		if (changedDtlsMap.get("PFSeries") != null)
			changedPFSeries = changedDtlsMap.get("PFSeries").toString();
		if (changedDtlsMap.get("PFActNo") != null)
			changedPFAcctNo = changedDtlsMap.get("PFActNo").toString();
		if (changedDtlsMap.get("BankActNo") != null)
			changedBankActNo = changedDtlsMap.get("BankActNo").toString();
		if (changedDtlsMap.get("BankBranch") != null)
			changedBankBranch = changedDtlsMap.get("BankBranch").toString();

		long eisEmpId = Long.valueOf(changedDtlsMap.get("eisEmpId").toString());
//		long orgEmpId = Long.valueOf(changedDtlsMap.get("orgEmpId").toString());
		long userId = Long.valueOf(changedDtlsMap.get("userId").toString());

		Session hibsession = getSession();
		StringBuffer stringBuffer = new StringBuffer();

		Query query = null;
		if (changedPFSeries != null && changedPFSeries.toString().length() > 0 && changedPFSeries != "")
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update HR_PAY_GPF_DETAILS set PF_SERIES ='" + changedPFSeries + "' where USER_ID = " + userId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (changedPFAcctNo != "" && changedPFAcctNo != null && changedPFAcctNo.toString().length() > 0)
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update HR_PAY_GPF_DETAILS set GPF_ACC_NO ='" + changedPFAcctNo + "' where USER_ID = " + userId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (changedBankActNo != "" && changedBankActNo != null && changedBankActNo.toString().length() > 0)
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update HR_EIS_BANK_DTLS set BANK_ACCT_NO ='" + changedBankActNo + "' where BANK_EMP_ID = " + eisEmpId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}
		if (changedBankBranch != "" && changedBankBranch != null && changedBankBranch.toString().length() > 0)
		{
			query = null;
			stringBuffer = new StringBuffer();
			stringBuffer.append("update HR_EIS_BANK_DTLS set BANK_BRANCH_ID ='" + changedBankBranch + "' where BANK_EMP_ID = " + eisEmpId);
			query = hibsession.createSQLQuery(stringBuffer.toString());
			logger.info("Query is---->" + query.toString());
//			noOfRows = 
			query.executeUpdate();
		}

		return true;

	}

	/**
	 * author@manish khunt
	 * to get the bill no from eis emp id 
	 * @param eisEmpId
	 * @return
	 */
	public Long getBillNo(long eisEmpId)
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select billgrp.dcpsDdoBillGroupId from MstDcpsBillGroup  billgrp, OrgUserpostRlt up,HrPayPsrPostMpg psr , HrEisEmpMst eis ");
		query.append(" where eis.empId=");
		query.append(eisEmpId);
		query.append(" and eis.orgEmpMst.orgUserMst.userId=up.orgUserMst.userId and up.orgPostMstByPostId.postId=psr.postId");
		query.append(" and psr.billNo=billgrp.dcpsDdoBillGroupId and up.activateFlag=1");

		logger.info("Query for get bill no from eisEmpId  is---->>>>" + query.toString());
		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();
		if (resList != null && resList.size() > 0)
		{
			logger.info("Query for get bill no from eisEmpId  size is ::" + resList.size());
			return Long.valueOf(resList.get(0).toString());
		}
		else
			return null;
	}

	/**
	 * author@manish khunt for Salary Configuration
	 * @param locId
	 * @param billNo
	 * @param givenDate
	 * @param eisEmpId
	 * @return
	 */
	public List getEmpForSalaryConfig(long locId, long billNo, Date givenDate, long eisEmpId)
	{
//		List trnBillRegList = null;
		Session hiSession = getSession();

		logger.info("payrollBundle.getString(getEligibleEmpForPaybill1) " + payrollBundle.getString("getEligibleEmpForPaybill1"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill2) " + payrollBundle.getString("getEligibleEmpForPaybill2"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill3) " + payrollBundle.getString("getEligibleEmpForPaybill3"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill4) " + payrollBundle.getString("getEligibleEmpForPaybill4"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill5) " + payrollBundle.getString("getEligibleEmpForPaybill5"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill6) " + payrollBundle.getString("getEligibleEmpForPaybill6"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill7) " + payrollBundle.getString("getEligibleEmpForPaybill7"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill8) " + payrollBundle.getString("getEligibleEmpForPaybill8"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill9) " + payrollBundle.getString("getEligibleEmpForPaybill9"));
		logger.info("payrollBundle.getString(getEligibleEmpForPaybill10) " + payrollBundle.getString("getEligibleEmpForPaybill10"));
		logger.info("payrollBundle.getString(getEligibleEmpForSalConfig)" + payrollBundle.getString("getEligibleEmpForSalConfig"));

		StringBuffer strQuery = new StringBuffer(payrollBundle.getString("getEligibleEmpForPaybill1"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill2"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill3"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill4"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill5"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill6"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill7"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill8"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill9"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill10"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill11"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForPaybill12"));
		strQuery.append(payrollBundle.getString("getEligibleEmpForSalConfig"));

		logger.info("Query is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);
		query.setParameter("eisEmpId", eisEmpId);

		logger.info("Query for getting non vacant post for bill no " + billNo + " is ");
		logger.info(" " + query.toString());

		return query.list();
	}

	public Map getEdpAllwMap(long payCommissionId, long locId)
	{
//		List deducList = new ArrayList();
		Map map = new HashMap();
		Session hibSession = getSession();
		try
		{

			/*Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			logger.info("Inside Deduction DAO");*/
			String queryStr = "SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID =" + allowanceComponent;
			//Query query= hibSession.createQuery(queryStr);
			/*ResultSet lRs1 =lStmt.executeQuery(queryStr);*/
			Query query = hibSession.createSQLQuery(queryStr.toString());
//			query.setCacheable(true);
			String edpCode;
			String typeId;
			int count = 0;
			List resultList = query.list();
			for (int i = 0; i < resultList.size(); i++)
			{
				Object[] row = (Object[]) resultList.get(i);
				if (row != null)
				{
					edpCode = row[0] != null ? String.valueOf(row[0].toString()) : "";
					typeId = row[1] != null ? String.valueOf(row[1].toString()) : "";
					map.put(typeId, edpCode);
				}
				count++;
			}
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}

		return map;
	}

	public Map getEdpDeducMap(long payCommissionId, long locId)
	{
//		List deducList = new ArrayList();
		Map map = new HashMap();
		Session hibSession = getSession();
		try
		{

			/*Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);*/
			logger.info("Inside Deduction DAO");
			String queryStr = "SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID = 2500135";
			Query query = hibSession.createSQLQuery(queryStr.toString());
//			query.setCacheable(true);
			String edpCode;
			String typeId;
			int count = 0;
			List resultList = query.list();
			for (int i = 0; i < resultList.size(); i++)
			{
				Object[] row = (Object[]) resultList.get(i);
				if (row != null)
				{
					edpCode = row[0] != null ? String.valueOf(row[0].toString()) : "";
					typeId = row[1] != null ? String.valueOf(row[1].toString()) : "";
					map.put(typeId, edpCode);
				}
				count++;
			}
			//Query query= hibSession.createQuery(queryStr);
			/*ResultSet lRs1 =lStmt.executeQuery(queryStr);*/

			/* while(lRs1.next())
			 {
			 	Object[] arr=(Object[])deducList.get(0);
			 	map.put(lRs1.getString(2).toString(),lRs1.getString(1).toString());
			 	count++;
			 }*/

			//System.out.println("count is for deduction "+count);
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}

		return map;
	}

	public CommAllOtherDetailsVO getAllOtherDetailsByOtherId(Long otherId, Long langId)
	{
		CommAllOtherDetailsVO otherData = null;
		Session hibSession = getSession();

		try
		{
			StringBuffer queryBuff = new StringBuffer();

			queryBuff.append(" select otherDtlsVO, otherDtlsVO.hrEisSgdMpg, otherDtlsVO.hrEisSgdMpg.hrEisScaleMst, ");
			queryBuff.append(" otherDtlsVO.hrEisSgdMpg.hrEisScaleMst.hrPayCommissionMst, ");
			queryBuff.append(" otherDtlsVO.hrEisEmpMst.orgEmpMst.empDoj, otherDtlsVO.hrEisEmpMst.empId, ");
			queryBuff.append(" otherDtlsVO.hrEisEmpMst.orgEmpMst.orgUserMst.userId, up.orgPostMstByPostId.postId, otherDtlsVO.hrEisEmpMst, ");
			queryBuff.append(" psr.billNo, otherDtlsVO.hrEisEmpMst.orgEmpMst, up.orgPostMstByPostId, ");
			queryBuff.append(" hem ");
			queryBuff.append(" from HrEisOtherDtls otherDtlsVO, OrgUserpostRlt up, ");
			queryBuff.append(" HrPayPsrPostMpg psr, HrEisGISDtls hem ");
			queryBuff.append(" where otherDtlsVO.otherId = :otherId  ");
			queryBuff.append(" and up.orgUserMst.userId = otherDtlsVO.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
			queryBuff.append(" and up.orgPostMstByPostId.postId=psr.postId  ");
			queryBuff.append(" and up.activateFlag=1 ");
			queryBuff.append(" and hem.hrEisEmpMst.orgEmpMst.empId = otherDtlsVO.hrEisEmpMst.orgEmpMst.empId ");

			String query = queryBuff.toString();

			Query otherDetailsQuery = hibSession.createQuery(query);
			otherDetailsQuery.setLong("otherId", otherId);

			List detailsList = otherDetailsQuery.list();

			if (detailsList != null && !detailsList.isEmpty())
			{
				Object row[] = (Object[]) detailsList.get(0);

				otherData = new CommAllOtherDetailsVO();
				otherData.setOtherDetailsVO((HrEisOtherDtls) row[0]);
				otherData.setEmpDoj((Date) row[4]);
				otherData.setEmpId((Long) row[5]);
				otherData.setUserId((Long) row[6]);
				otherData.setPostId((Long) row[7]);
				otherData.setHrEisEmpMst((HrEisEmpMst) row[8]);
				otherData.setDcpsDdoBillGroupId((Long) row[9]);
				otherData.setOrgEmpMst((OrgEmpMst) row[10]);
				otherData.setOrgPostMst((OrgPostMst) row[11]);
				otherData.setHrEisSgdMpg((HrEisSgdMpg) row[1]);
				otherData.setHrEisScaleMst((HrEisScaleMst) row[2]);
				otherData.setHrEisGISDtls((HrEisGISDtls) row[12]);
				otherData.setHrPayCommissionMst((HrPayCommissionMst) row[3]);

			}

		}
		catch (Exception exp)
		{
			logger.error("Error in getAllOtherDetailsByOtherId ", exp);
			exp.printStackTrace();
		}
		return otherData;
	}
	
	public boolean ChckContriAtTreasury(int month, long finYrId, long billNo)
	{
		List lstDCPSData = new ArrayList();
		Session hibsession = getSession();
		boolean returnFlg= true;
		//String strQuery = "";
		//strQuery = "select VOUCHER_STATUS from MST_DCPS_CONTRI_VOUCHER_DTLS where MONTH_ID =" + month;
		//strQuery += " and YEAR_ID =" + finYrId + " and BILL_GROUP_ID = "+ billNo;
		
		StringBuilder lStrQuery = new StringBuilder();
		lStrQuery.append(" select distinct CV.VOUCHER_STATUS from MST_DCPS_CONTRI_VOUCHER_DTLS CV ");
		lStrQuery.append(" join TRN_DCPS_CONTRIBUTION TR on CV.MST_DCPS_CONTRI_VOUCHER_DTLS = TR.RLT_CONTRI_VOUCHER_ID");
		lStrQuery.append(" where CV.MONTH_ID = " +  month  + " and CV.YEAR_ID = " + finYrId + " and CV.BILL_GROUP_ID = " + billNo);
		
		Query query = hibsession.createSQLQuery(lStrQuery.toString());
		String voucherStat="1";
		lstDCPSData = query.list();
		logger.info("lstDCPSData size:::::::::::::" + lstDCPSData.size());
		if(lstDCPSData != null && !lstDCPSData.equals("") && lstDCPSData.size() > 0)
		{
			for(Iterator itr=lstDCPSData.iterator();itr.hasNext();)
			{
				BigInteger rowList = (BigInteger)itr.next();
				if(rowList!=null && !rowList.equals("")){
				voucherStat = rowList.toString(); 
				}
				
			}
		}
			if(voucherStat.equalsIgnoreCase("1"))
				returnFlg= true;
			else
				returnFlg= false;

		return returnFlg;

	}

}//end class
