package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompGrpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayCompGrpMst;

@SuppressWarnings("unchecked")
public class EmpCompMpgDAOImpl extends GenericDaoHibernateImpl<HrEisEmpCompMpg, Long> implements EmpCompMpgDAO
{

	Log logger = LogFactory.getLog(getClass());
	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");

	public EmpCompMpgDAOImpl(Class<HrEisEmpCompMpg> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public HrEisEmpCompMpg getDataIsPresnetORNot(long empId, long CompoId, long CompoType)
	{
		logger.info("==> in getDataIsPresnetORNot..........");

		List HrEisEmpCompMpgList = null;
		HrEisEmpCompMpg hreisempCompMpg = null;

		Session hibsession = getSession();
		Query query;

		String HQL_QUERY = "select hreisCompMpg from HrEisEmpCompMpg hreisCompMpg where hreisCompMpg.hrEisEmpMst.empId=" + empId + " and hreisCompMpg.cmnLookupMst.lookupId=" + CompoType + " and hreisCompMpg.compId=" + CompoId;

		query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> in getDataIsPresnetORNot query :: " + query);
		HrEisEmpCompMpgList = query.list();

		if (HrEisEmpCompMpgList != null && HrEisEmpCompMpgList.size() != 0)
		{
			logger.info("==> HrEisEmpCompMpgList :: " + HrEisEmpCompMpgList.size());
			hreisempCompMpg = (HrEisEmpCompMpg) query.uniqueResult();
		}
		return hreisempCompMpg;
	}

	public List getDataIDisPresent(String[] lArrallowList, String[] lArrDeductList, long SizeofChkValofAllow, long SizeofChkValofDedu)
	{

		List HrEisEmpCompList = null;
		Session hibSession = getSession();
		logger.info("going to execute query....");
		String AllowID = "";
		String DeductID = "";

		StringBuffer lStrBufHrEisEmpComp = new StringBuffer();
		lStrBufHrEisEmpComp.append("from HrEisEmpCompMpg mpg where mpg.compId not in(");
		logger.info("==> SizeofChkValofAllow :: " + SizeofChkValofAllow);
		if (SizeofChkValofAllow != 0)
		{
			for (int i = 0; i < SizeofChkValofAllow; i++)
			{
				AllowID = lArrallowList[i];
				logger.info("==> in dao AllowID :: " + AllowID);
				if (i == (SizeofChkValofAllow - 1))
				{
					lStrBufHrEisEmpComp.append(AllowID);
				}
				else
				{
					logger.info("====> in ,,, ");
					lStrBufHrEisEmpComp.append(AllowID);
					lStrBufHrEisEmpComp.append(",");
				}
			}
		}

		logger.info("==> SizeofChkValofDedu :: " + SizeofChkValofDedu);
		if (SizeofChkValofDedu != 0)
		{
			lStrBufHrEisEmpComp.append(",");

			for (int i = 0; i < SizeofChkValofDedu; i++)
			{
				DeductID = lArrDeductList[i];
				logger.info("==> in dao DeductID :: " + DeductID);
				if (i == (SizeofChkValofDedu - 1))
				{
					logger.info("====> in if ");
					lStrBufHrEisEmpComp.append(DeductID);
				}
				else
				{
					logger.info("====> in ,,, ");
					lStrBufHrEisEmpComp.append(DeductID);
					lStrBufHrEisEmpComp.append(",");
				}
			}
		}
		lStrBufHrEisEmpComp.append(")");
		lStrBufHrEisEmpComp.append("and mpg.isactive=" + 1);

		logger.info("====> lStrBufHrEisEmpComp :: " + lStrBufHrEisEmpComp);

		Query query = hibSession.createQuery(lStrBufHrEisEmpComp.toString());
		logger.info("====> getDataIDisPresent in Dao query :: " + query);
		HrEisEmpCompList = query.list();
		logger.info("===> getDataIDisPresent ind dao list.size() :: " + HrEisEmpCompList.size());

		if (HrEisEmpCompList.size() != 0)
		{
			logger.info("====> getDataIDisPresent list of size :: " + HrEisEmpCompList.size());
		}
		return HrEisEmpCompList;
	}

	public List<HrEisEmpCompMpg> getDataforHst(long empId)
	{
		logger.info("==> in getDataIsPresnetORNot..........");

		List HrEisEmpCompMpgList = null;

		Session hibsession = getSession();
		Query query;

		String HQL_QUERY = "select hreisCompMpg from HrEisEmpCompMpg hreisCompMpg where hreisCompMpg.hrEisEmpMst.empId=" + empId;

		query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getDataforHst in getDataIsPresnetORNot query :: " + query);
		HrEisEmpCompMpgList = query.list();

		/*if(HrEisEmpCompMpgList!=null && HrEisEmpCompMpgList.size()!=0)
		{
			logger.info("==>getDataforHst  HrEisEmpCompMpgHst :: "+HrEisEmpCompMpgList.size());
			hreisempCompMpg = (HrEisEmpCompMpg) query.list();
			
		}*/
		return HrEisEmpCompMpgList;
	}

	public List getBillNo(long finYrId, long locId)
	{
		List resList = null;

		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select hrPayBillHeadMpg from HrPayBillHeadMpg hrPayBillHeadMpg where finYearId=" + finYrId);
		query.append(" and cmnLocationMst.locId=" + locId + " order by billId");
		logger.info("Query for get bill no is---->>>>" + query.toString());

		Query sqlQuery = hibSession.createQuery(query.toString());
		resList = sqlQuery.list();
		logger.info("the getBillNo size of resList is ::" + resList.size());
		return resList;
	}

	public List<HrEisOtherDtls> getEmpPayID(long empId)
	{
		List resList = null;
		Session hibSession = getSession();
		StringBuffer query = new StringBuffer();
		query.append("select hreisdtls from HrEisOtherDtls hreisdtls where hreisdtls.hrEisEmpMst.empId=" + empId);
		Query sqlQuery = hibSession.createQuery(query.toString());
		logger.info("the getEmpPayID sqlQuery ::" + sqlQuery);
		resList = sqlQuery.list();
		logger.info("the getEmpPayID size of resList is ::" + resList.size());
		return resList;

	}

	public List<HrEisEmpCompGrpMst> getMstDataIsPresent(long EmpID, String EffectD)
	{
		List HrPayMstData = null;
		/*SimpleDateFormat simpledt = new SimpleDateFormat("yyyy-MM-dd");
		String EffectD = simpledt.format(EffectDate);*/

		/*	logger.info("===> in EffectD :: "+EffectD);*/

		Session hibsession = getSession();
		String HQL_QUERY = "select hreisComMst from HrEisEmpCompGrpMst hreisComMst where "
		//+"hreisComMst.month="+month+""
				//+" and hreisComMst.year="+year
				//+" and hreisComMst.payComissionId="+PayID
				+ " hreisComMst.hrEisEmpMst.empId=" + EmpID + " and hreisComMst.wefDate='" + EffectD + "'";
		//+" and hreisComMst.payComissionId="+PayId;
		//+" and hreisComMst.wefDate="+EffectD;

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getMstDataIsPresent By Date query :: " + query);

		HrPayMstData = query.list();
		logger.info("==> HrPayMstData :: " + HrPayMstData.size());
		return HrPayMstData;
	}

	public List<HrEisEmpCompGrpMst> getMstDataFromEmpID(long EmpID)
	{
		List HrPayMstData = null;
		Session hibsession = getSession();
		String HQL_QUERY = "select hrEisComMst from HrEisEmpCompGrpMst hrEisComMst where hrEisComMst.isactive=1 and  hrEisComMst.hrEisEmpMst.empId=" + EmpID;

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getMstDataFromEmpID query :: " + query);

		HrPayMstData = query.list();
		logger.info("==> getMstDataFromLocID :: " + HrPayMstData.size());
		return HrPayMstData;
	}

	public List<HrPayCompGrpMst> getHrPayCompGrpMst(long locId)
	{
		List HrPayMstData = null;
		Session hibsession = getSession();
		String HQL_QUERY = "select hrPayComMst from HrPayCompGrpMst hrPayComMst where hrPayComMst.isactive=1 and hrPayComMst.cmnLocationMst.locId=" + locId;

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getHrPayCompGrpMst query :: " + query);

		HrPayMstData = query.list();
		logger.info("==> getHrPayCompGrpMst :: " + HrPayMstData.size());
		return HrPayMstData;
	}

	public List<HrEisEmpCompMpg> getHrEisMpgDataFromEmpID(long EmpId)
	{
		List HrEisMpgData = null;
		Session hibsession = getSession();
		String HQL_QUERY = "select hreismpg from HrEisEmpCompMpg hreismpg,HrEisEmpCompGrpMst hrEisComMst where hrEisComMst.EmpCompGrpId=hreismpg.hrEisEmpCompGrpMst.EmpCompGrpId" + " and hrEisComMst.isactive=0";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getHrEisMpgDataFromEmpID query :: " + query);

		HrEisMpgData = query.list();
		logger.info("==> getHrEisMpgDataFromEmpID :: " + HrEisMpgData.size());
		return HrEisMpgData;
	}

	public HrEisEmpCompMpg getDataIDisPresent(long Empid, long AllowId, long GrpID, long ID)
	{
		List hrEisMpgData = null;
		Session hibsession = getSession();
		HrEisEmpCompMpg hreisempMPG = null;

		String HQL_QUERY = "select hreismpg from HrEisEmpCompMpg hreismpg where hreismpg.hrEisEmpCompGrpMst.hrEisEmpMst.empId=" + Empid
		//+" and hreismpg.hrEisEmpCompGrpMst.isactive=1"
				+ " and hreismpg.compId=" + AllowId + " and hreismpg.hrEisEmpCompGrpMst.EmpCompGrpId=" + GrpID + " and hreismpg.cmnLookupMst.lookupId=" + ID;

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getDataIDisPresent query :: " + query);
		hrEisMpgData = query.list();

		if (hrEisMpgData != null && hrEisMpgData.size() > 0)
		{
			hreisempMPG = (HrEisEmpCompMpg) query.uniqueResult();
		}

		return hreisempMPG;
	}

	public List getDataAllowChcked(long empId)
	{
		List HrPayLocMpgAllowList = null;

		Session hibSession = getSession();

		long AllowID = 2500134;
		logger.info("in getDataAllowChcked :: " + empId + "AllowId :: " + AllowID);
		// long DeductID=300135;
		// String strQuery = "select mpg from HrPayLocComMpg mpg where mpg.hrpaycomgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+AllowID+" and mpg.isactive="+1;

		/*  String strQuery = "select mpg from HrEisEmpCompMpg mpg, HrEisEmpCompGrpMst mst where mst.orgEmpMst.empId="+empId+" and mpg.cmnLookupMst.lookupId="+AllowID+" and mpg.isactive="+1
		  					+" and mpg.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId=mst.hrPayCompGrpMst.compoGrpId and mst.isactive=1";*/
		String HQL_QUERY = " select mpg from HrEisEmpCompMpg mpg where mpg.cmnLookupMst.lookupId=" + AllowID + " and mpg.isactive=" + 1 + "" + " and mpg.hrEisEmpCompGrpMst.EmpCompGrpId in (select mst.EmpCompGrpId from HrEisEmpCompGrpMst mst where mst.isactive=1 and mst.hrEisEmpMst.empId=" + empId + ")";

		Query query = hibSession.createQuery(HQL_QUERY);
		logger.info("getDataAllowChcked queryqueryquery  ::" + query);
		HrPayLocMpgAllowList = query.list();

		logger.info("===> ind dao list.size() :: " + HrPayLocMpgAllowList.size());

		return HrPayLocMpgAllowList;
	}

	public List getDataDeductChcked(long empId)
	{
		List HrPayLocMpgDeductList = null;
		Session hibSession = getSession();
		logger.info("going to execute query....");
		//long AllowID=300134;
		long DeductID = 2500135;

		//String strQuery = "select mpg from HrPayLocComMpg mpg where mpg.hrpaycomgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+DeductID+"and mpg.isactive="+1;

		/*   String strQuery = "select mpg from HrEisEmpCompMpg mpg, HrEisEmpCompGrpMst mst where mst.orgEmpMst.empId="+empId+" and mpg.cmnLookupMst.lookupId="+DeductID+" and mpg.isactive="+1
			+" and mpg.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId=mst.hrPayCompGrpMst.compoGrpId and mst.isactive=1";*/
		String HQL_QUERY = " select mpg from HrEisEmpCompMpg mpg where mpg.cmnLookupMst.lookupId=" + DeductID + " and mpg.isactive=" + 1 + "" + " and mpg.hrEisEmpCompGrpMst.EmpCompGrpId in (select mst.EmpCompGrpId from HrEisEmpCompGrpMst mst where mst.isactive=1 and mst.hrEisEmpMst.empId=" + empId + ")";

		Query query = hibSession.createQuery(HQL_QUERY);
		logger.info("getDataDeductChcked queryqueryquery  ::" + query);
		HrPayLocMpgDeductList = query.list();

		logger.info("===> ind dao list.size() :: " + HrPayLocMpgDeductList.size());

		return HrPayLocMpgDeductList;
	}

	public List getDataIDisPresent(String[] lArrallowList, String[] lArrDeductList, long SizeofAllow, long SizeOfDeduct, long grpId)
	{
		List HrPayEmpCompMpgList = null;
		Session hibSession = getSession();
		logger.info("going to execute query....");
		String AllowID = "";
		String DeductID = "";
		//  long lenth= lArrallowList.length;
		// logger.info("===> lenth :: "+lenth);
		//  logger.info("===> lenth :: "+(lenth-1));
		//  long len=(lenth-1);

		StringBuffer HrPayloc = new StringBuffer();
		HrPayloc.append("select mpg from HrEisEmpCompMpg mpg where mpg.hrEisEmpCompGrpMst.EmpCompGrpId=" + grpId + " and mpg.compId not in(");
		// String strQuery = "from HrPayLocComMpg mpg where mpg.compId not in(";
		logger.info("==> SizeofAllow :: " + SizeofAllow);
		if (SizeofAllow != 0)
		{
			for (int i = 0; i < SizeofAllow; i++)
			{
				AllowID = lArrallowList[i];
				logger.info("==> in dao AllowID :: " + AllowID);
				if (i == (SizeofAllow - 1))
				{
					HrPayloc.append(AllowID);
				}
				else
				{
					logger.info("====> in ,,, ");
					HrPayloc.append(AllowID);
					HrPayloc.append(",");
				}
			}
		}

		logger.info("==> SizeOfDeduct :: " + SizeOfDeduct);
		if (SizeOfDeduct != 0)
		{
			HrPayloc.append(",");

			for (int i = 0; i < SizeOfDeduct; i++)
			{
				DeductID = lArrDeductList[i];
				logger.info("==> in dao DeductID :: " + DeductID);
				if (i == (SizeOfDeduct - 1))
				{
					logger.info("====> in if ");
					HrPayloc.append(DeductID);
				}
				else
				{
					logger.info("====> in ,,, ");
					HrPayloc.append(DeductID);
					HrPayloc.append(",");
				}
			}
		}
		HrPayloc.append(")");
		HrPayloc.append("and mpg.isactive=" + 1);

		logger.info("====> HrPayloc :: " + HrPayloc);

		Query query = hibSession.createQuery(HrPayloc.toString());
		logger.info("====> in Dao query :: " + query);
		logger.info("==> getDataIDisPresent query :: " + query);
		//list = query.list();
		HrPayEmpCompMpgList = query.list();
		//HrPayLocComMpg hrpayLocComMpg=null;
		logger.info("===> ind dao list.size() :: " + HrPayEmpCompMpgList.size());

		if (HrPayEmpCompMpgList.size() != 0)
		{
			logger.info("====> list of size :: " + HrPayEmpCompMpgList.size());
		}
		/* if(list!=null&&list.size()>0)
		 {
			 hrpayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
		 }*/
		return HrPayEmpCompMpgList;
	}

	public String getDesigNameFromEmpId(String empId)
	{
		String DesigName = "";
		Session hibsession = getSession();

		String HQL_QUERY = "select det.orgDesignationMst.dsgnName from OrgUserpostRlt up, OrgPostDetailsRlt det,HrEisEmpMst eisEmp where eisEmp.empId=" + empId + "" + " and up.orgUserMst.userId = eisEmp.orgEmpMst.orgUserMst.userId and det.orgPostMst.postId=up.orgPostMstByPostId.postId and up.activateFlag=1";

		Query lQuery = hibsession.createQuery(HQL_QUERY);
		if (lQuery.uniqueResult().toString() != null)
			DesigName = lQuery.uniqueResult().toString();
		logger.info("===> getDesigName lQuery :: " + lQuery);
		logger.info("===> in getDesigName :: " + DesigName);
		return DesigName;

	}

	public Date getWEFDataFromEmpId(long Empid)
	{
		Date WEFDate = null;

		List list = new ArrayList();
		Session hibsession = getSession();
		//String HQL_QUERY ="select hreismpg from HrEisEmpCompMpg hreismpg where hreismpg.hrEisEmpCompGrpMst.orgEmpMst.empId="+Empid
		String HQL_QUERY = "select hrEisComMst.wefDate from HrEisEmpCompGrpMst hrEisComMst where hrEisComMst.hrEisEmpMst.empId=" + Empid + "" + " and hrEisComMst.isactive=1";

		Query lQuery = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getWEFDataFromEmpId lQuery :: " + lQuery);
		list = lQuery.list();
		if (list.size() > 0)
		{
			WEFDate = (Date) list.get(0);
			logger.info("===> in getWEFDataFromEmpId :: " + WEFDate);
		}
		/*if(lQuery.uniqueResult()!=null)
		{
		    	WEFDate= lQuery.uniqueResult().toString();
		    	logger.info("===> in getWEFDataFromEmpId :: "+WEFDate);
		}*/

		return WEFDate;
	}

	public String getRemarks(long Empid)
	{

		List list = new ArrayList();

		String remarks = "";
		Session hibSession = getSession();

		String HQL_QUERY = "select hrEisComMst.remarks from HrEisEmpCompGrpMst hrEisComMst where hrEisComMst.hrEisEmpMst.empId=" + Empid + "" + " and hrEisComMst.isactive=1";

		Query sqlQuery = hibSession.createQuery(HQL_QUERY.toString());
		list = sqlQuery.list();
		if (list.size() > 0)
		{
			if (list.get(0) != null)
			{
				remarks = list.get(0).toString();
			}
		}

		else
		{
			remarks = "";
		}
		logger.info("returning value of cmnLookupId is" + remarks);
		return remarks;
	}

	public List<HrEisEmpCompMpg> getSpecificEmpActiveList(String compoList, long compoType, long locId)
	{
		List<HrEisEmpCompMpg> list = new ArrayList();
		Session hibsession = getSession();
		//String HQL_QUERY = "select empCompoMpg from HrEisEmpCompMpg empCompoMpg where empCompoMpg.hrEisEmpCompGrpMst.isactive = 1 and empCompoMpg.cmnLookupMst.lookupId = " + compoType + " and empCompoMpg.hrEisEmpCompGrpMst.hrPayCompGrpMst.cmnLocationMst.locId= " + locId + " and empCompoMpg.isactive=1 and empCompoMpg.compId in (" + compoList + ")";
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("SELECT eisCmp FROM HrEisEmpCompMpg eisCmp, HrEisEmpCompGrpMst grp, ");
		stringBuffer.append("HrEisEmpMst eis, OrgEmpMst org, OrgUserpostRlt userpost, OrgPostMst post ");
		stringBuffer.append("WHERE eisCmp.hrEisEmpCompGrpMst.EmpCompGrpId = grp.EmpCompGrpId ");
		stringBuffer.append("and grp.hrEisEmpMst.empId = eis.empId ");
		stringBuffer.append("and eis.orgEmpMst.empId = org.empId ");
		stringBuffer.append("and org.orgUserMst.userId =  userpost.orgUserMst.userId ");
		stringBuffer.append("and userpost.orgPostMstByPostId.postId = post.postId ");
		stringBuffer.append("and post.locationCode = " + locId + " ");
		stringBuffer.append("and post.activateFlag = 1 ");
		stringBuffer.append("and grp.isactive = 1 ");
		stringBuffer.append("and eisCmp.isactive = 1 and userpost.activateFlag=1 ");
		stringBuffer.append("and eisCmp.compId in (" + compoList + ") ");
		stringBuffer.append("and eisCmp.cmnLookupMst.lookupId = " + compoType + " ");
		Query lQuery = hibsession.createQuery(stringBuffer.toString());
		list = lQuery.list();
		return list;
	}

	public List getPeviousMappedCompoIDfromCompMpg(long empId, long ID)
	{
		List AllowMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select mpg from HrEisEmpCompMpg mpg,HrEisEmpCompGrpMst mst where mpg.hrEisEmpCompGrpMst.hrEisEmpMst.empId=" + empId + " and mpg.isactive=1 and mpg.cmnLookupMst.lookupId=" + ID + "" + " and mpg.hrEisEmpCompGrpMst.EmpCompGrpId=mst.EmpCompGrpId and mst.isactive=1";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getPeviousMappedCompoIDfromCompMpg query :: " + query);
		AllowMappedList = query.list();
		logger.info("====> AllowMappedList :: " + AllowMappedList.size());
		return AllowMappedList;
	}

	public List getNewMappedCompoIDfromCompMpg(long empId, String CurrentMappedList, String PreviouslStrMappedComoId)
	{
		List NewAllowMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select mpg from HrEisEmpCompMpg mpg where mpg.hrEisEmpCompGrpMst.hrEisEmpMst.empId=" + empId + " and mpg.isactive=1 and mpg.compId in(" + CurrentMappedList + ") and mpg.compId not in (" + PreviouslStrMappedComoId + ")";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getNewMappedCompoIDfromCompMpg query :: " + query);

		NewAllowMappedList = query.list();
		logger.info("====> NewAllowMappedList :: " + NewAllowMappedList.size());

		return NewAllowMappedList;
	}

	public List getRemovedMappedCompoIDfromCompMpg(long empId, String PreviouslStrMappedComoId, String CurrentMappedList, long ID)
	{
		List RemoveAllowMappedList = new ArrayList();
		Session hibsessionRemove = getSession();
		logger.info("===> before Passing Remove in(" + PreviouslStrMappedComoId + ") and not in(" + CurrentMappedList + ")");
		String HQL_QUERY = "select mpg from HrEisEmpCompMpg mpg where mpg.hrEisEmpCompGrpMst.hrEisEmpMst.empId=" + empId + " and mpg.isactive=1 and mpg.hrEisEmpCompGrpMst.isactive=1 and mpg.compId in(" + PreviouslStrMappedComoId + ") and mpg.compId not in (" + CurrentMappedList + ") " + " and mpg.cmnLookupMst.lookupId=" + ID;

		Query queryRemove = hibsessionRemove.createQuery(HQL_QUERY);
		logger.info("===> getRemovedMappedCompoIDfromCompMpg queryRemove :: " + queryRemove);

		logger.info("====> Size of query :: " + queryRemove.list().size());
		RemoveAllowMappedList = queryRemove.list();
		logger.info("====> RemoveAllowMappedList :: " + RemoveAllowMappedList.size());

		return RemoveAllowMappedList;
	}

	public List getSameMappedCompoIDfromCompMpg(long empId, String CurrentMappedList, String PreviouslStrMappedComoId, long ID)
	{
		List SameAllowMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select mpg from HrEisEmpCompMpg mpg where mpg.hrEisEmpCompGrpMst.hrEisEmpMst.empId=" + empId + " and mpg.isactive=1 and mpg.cmnLookupMst.lookupId=" + ID + " and mpg.compId in(" + CurrentMappedList + ")" + " and mpg.compId in(" + PreviouslStrMappedComoId + ")";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getSameMappedCompoIDfromCompMpg query :: " + query);

		SameAllowMappedList = query.list();
		logger.info("====> AllowMappedList :: " + SameAllowMappedList.size());

		return SameAllowMappedList;
	}

	//Modified By Kishan - Start
	public Map<Long, List> getDataChcked(long billNo, int month, int year)
	{
		List empMpgCompList = null;
		Session hibSession = getSession();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("SELECT paybill.EMP_ID,empCompo.COMPO_TYPE,empCompo.COMPO_ID  ");
		sqlBuffer.append("FROM PAYBILL_HEAD_MPG head, HR_PAY_PAYBILL paybill, HR_PAY_PAYBILL_EMP_COMP_MPG empCompo  ");
		sqlBuffer.append("where head.BILL_NO = " + billNo + " ");
		sqlBuffer.append("and head.PAYBILL_MONTH = " + month + " and head.PAYBILL_YEAR = " + year + " and head.APPROVE_FLAG = 1 ");
		sqlBuffer.append("and paybill.PAYBILL_GRP_ID = head.PAYBILL_ID and empCompo.HR_PAY_PAYBILL_ID = paybill.ID ");
		sqlBuffer.append("order by paybill.EMP_ID ");
		/*sqlBuffer.append("select mpg,mst.hrEisEmpMst.empId ");
		sqlBuffer.append("from HrEisEmpCompMpg mpg,HrEisEmpCompGrpMst mst ");
		sqlBuffer.append("where mpg.cmnLookupMst.lookupId in (2500134,2500135,2500136,2500137,300160) ");
		sqlBuffer.append("and mpg.isactive = 1 ");
		sqlBuffer.append("and mpg.hrEisEmpCompGrpMst.EmpCompGrpId = mst.EmpCompGrpId ");
		sqlBuffer.append("and mst.isactive = 1 ");
		sqlBuffer.append("and mst.hrEisEmpMst.empId in ( " + empId + ") ");
		sqlBuffer.append("order by mst.hrEisEmpMst.empId ");*/
		Query query = hibSession.createSQLQuery(sqlBuffer.toString());
		logger.info("getDataAllowChcked queryqueryquery  ::" + query);
		empMpgCompList = query.list();
		int size = empMpgCompList.size();
		Object[] data = null;
		long previousEmpId = 0;
		Map<Long, List> compoMap = new HashMap();
		for (int i = 0; i < size; i++)
		{
			data = (Object[]) empMpgCompList.get(i);
			long empIdDB = Long.valueOf(data[0].toString());
			long compoType = Long.valueOf(data[1].toString());
			String compoId = data[2].toString();
			long currEmpId = empIdDB;

			if (currEmpId != previousEmpId)
			{
				List mappedCompoList = new ArrayList();
				Object[] dataPut = { compoType, compoId };
				mappedCompoList.add(dataPut);
				compoMap.put(currEmpId, mappedCompoList);
				previousEmpId = currEmpId;
			}
			else
			{
				List mappedCompoList = (List) compoMap.get(currEmpId);
				Object[] dataPut = { compoType, compoId };
				mappedCompoList.add(dataPut);
				compoMap.put(currEmpId, mappedCompoList);
			}

		}
		logger.info("===> ind dao list.size() :: " + empMpgCompList.size());
		return compoMap;
	}

	//Modified By Kishan - end

	public double getDCPSValue(long empId, int month, long finYrId)
	{
		double dcps = 0d;
		Session hibsession = getSession();
		//String  strQuery = "SELECT sum(contribute.contribution) FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and emp.orgEmpMstId= "+empId+"  and contribute.monthId = "+month+" and contribute.finYearId= "+finYrId  ;
		String strQuery = "SELECT sum(contribute.contribution) FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and emp.orgEmpMstId= " + empId + "  and contribute.monthId = " + month + " and contribute.finYearId= " + finYrId;
		Query query = hibsession.createQuery(strQuery);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (Double) (query.uniqueResult() != null && query.list().get(0) != null ? query.uniqueResult() : dcps);
		else
			return 0;

	}

	public List getDCPSValues(String empIds, int month, long finYrId, long locId, long billNo, Date givenDate)
	{
		Session hiSession = getSession();

		logger.info("payrollBundle.getString(getDCPSValues1) " + payrollBundle.getString("getDCPSValues1"));
		logger.info("payrollBundle.getString(getDCPSValues2) " + payrollBundle.getString("getDCPSValues2"));
		logger.info("payrollBundle.getString(getDCPSValues3) " + payrollBundle.getString("getDCPSValues3"));
		logger.info("payrollBundle.getString(getDCPSValues4) " + payrollBundle.getString("getDCPSValues4"));

		logger.info("EmpIDStr is " + empIds);
		logger.info("month is " + month);
		logger.info("Fin yr id is " + finYrId);
		StringBuffer strQuery = new StringBuffer(payrollBundle.getString("getDCPSValues1"));
		strQuery.append(payrollBundle.getString("getDCPSValues2"));
		strQuery.append(payrollBundle.getString("getOrgEmpIdList"));
		strQuery.append(payrollBundle.getString("getDCPSValues3"));
		strQuery.append(payrollBundle.getString("getDCPSValues4"));
		strQuery.append(" order by mstemp1_.ORG_EMP_MST_ID");

		logger.info("Query is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		query.setParameter("monthId", month);
		query.setParameter("finYrId", finYrId);
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);
		return query.list();
	}

	public Map getDCPSValues(long empId, int month, long finYrId)
	{
		Session hibsession = getSession();
		Map dcpsMap = new HashMap();
		String strQuery = "SELECT contribute FROM  TrnDcpsContribution contribute , MstEmp emp " + "where emp.dcpsEmpId = contribute.dcpsEmpId " + " and contribute.typeOfPayment in (700046,700047,700049,700048) " + " and emp.orgEmpMstId= " + empId + " and contribute.monthId = " + month + " and contribute.finYearId= " + finYrId + " order by contribute.typeOfPayment ";

		Query dcpsQuery = hibsession.createQuery(strQuery);
		List dcpsList = dcpsQuery.list();

		Iterator dcpsIter = dcpsList.iterator();

		while (dcpsIter.hasNext())
		{
			TrnDcpsContribution contribution = (TrnDcpsContribution) dcpsIter.next();
			dcpsMap.put(contribution.getTypeOfPayment(), contribution.getContribution());
		}

		return dcpsMap;
	}
	
	/*NPS Allowances and deduction through online entry */ 
	public List getNPSOnlineDCPSValues(String empIds, int month, long finYrId, long locId, long billNo, Date givenDate)
	{
		Session hiSession = getSession();

		logger.info("payrollBundle.getString(getNPSOnlineDCPSValues1) " + payrollBundle.getString("getNPSOnlineDCPSValues1"));
		logger.info("payrollBundle.getString(getNPSOnlineDCPSValues2) " + payrollBundle.getString("getNPSOnlineDCPSValues2"));
		logger.info("payrollBundle.getString(getNPSOnlineDCPSValues3) " + payrollBundle.getString("getNPSOnlineDCPSValues3"));
		logger.info("payrollBundle.getString(getNPSOnlineDCPSValues4) " + payrollBundle.getString("getNPSOnlineDCPSValues4"));

		logger.info("EmpIDStr is " + empIds);
		logger.info("month is " + month);
		logger.info("Fin yr id is " + finYrId);
		StringBuffer strQuery = new StringBuffer(payrollBundle.getString("getNPSOnlineDCPSValues1"));
		strQuery.append(payrollBundle.getString("getNPSOnlineDCPSValues2"));
		strQuery.append(payrollBundle.getString("getOrgEmpIdList"));
		strQuery.append(payrollBundle.getString("getNPSOnlineDCPSValues3"));
		strQuery.append(payrollBundle.getString("getNPSOnlineDCPSValues4"));
		strQuery.append(" order by mstemp1_.ORG_EMP_MST_ID");

		logger.info("Query is " + strQuery);
		Query query = hiSession.createSQLQuery(strQuery.toString());
		query.setParameter("monthId", month);
		query.setParameter("finYrId", finYrId);
		query.setParameter("locId", locId);
		query.setParameter("billNo", billNo);
		query.setParameter("givenDate", givenDate);
		return query.list();
	}
	
	public Map  getNPSOnlineDCPSValues(long empId, int month, long finYrId)
	{
		Session hibsession = getSession();
		Map dcpsMap = new HashMap();
		String strQuery = "SELECT NPS_EMPLR_CONTRI_DED FROM  TrnDcpsContribution contribute , MstEmp emp " + "where emp.dcpsEmpId = contribute.dcpsEmpId " + " and contribute.typeOfPayment in (700046,700047,700049,700048) " + " and emp.orgEmpMstId= " + empId + " and contribute.monthId = " + month + " and contribute.finYearId= " + finYrId + " order by contribute.typeOfPayment ";

		Query dcpsQuery = hibsession.createQuery(strQuery);
		List dcpsList = dcpsQuery.list();

		Iterator dcpsIter = dcpsList.iterator();

		while (dcpsIter.hasNext())
		{
			TrnDcpsContribution contribution = (TrnDcpsContribution) dcpsIter.next();
			dcpsMap.put(contribution.getTypeOfPayment(), contribution.getContributionNps());
		}

		return dcpsMap;
	}
	
	
	/*NPS Allowances and deduction through online entry */
	

	public double getDCPSValue1(long empId, int month, long finYrId)
	{
		logger.info("Executing method for  DCPS valu::::");
		double dcps = 0d;
		Session hibsession = getSession();
		//String  strQuery = "SELECT sum(contribute.contribution) FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and emp.orgEmpMstId= "+empId+"  and contribute.monthId = "+month+" and contribute.finYearId= "+finYrId  ;
		String strQuery = "SELECT contribute.contribution FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and contribute.typeOfPayment=700046 and emp.orgEmpMstId= " + empId + "  and contribute.monthId = " + month + " and contribute.finYearId= " + finYrId;
		Query query = hibsession.createQuery(strQuery);
		logger.info("query******************" + query);
		logger.info("query******************" + query.toString());
		if (query != null && query.list() != null && query.list().size() > 0)
			return (Double) (query.uniqueResult() != null && query.list().get(0) != null ? query.uniqueResult() : dcps);
		else
			return 0;

	}

	public double getDCPSValue2(long empId, int month, long finYrId)
	{
		double dcps = 0d;
		Session hibsession = getSession();
		//String  strQuery = "SELECT sum(contribute.contribution) FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and emp.orgEmpMstId= "+empId+"  and contribute.monthId = "+month+" and contribute.finYearId= "+finYrId  ;
		String strQuery = "SELECT contribute.contribution FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and contribute.typeOfPayment=700047 and emp.orgEmpMstId= " + empId + "  and contribute.monthId = " + month + " and contribute.finYearId= " + finYrId;
		Query query = hibsession.createQuery(strQuery);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (Double) (query.uniqueResult() != null && query.list().get(0) != null ? query.uniqueResult() : dcps);
		else
			return 0;

	}

	public double getDCPSValue3(long empId, int month, long finYrId)
	{
		double dcps = 0d;
		Session hibsession = getSession();
		//String  strQuery = "SELECT sum(contribute.contribution) FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and emp.orgEmpMstId= "+empId+"  and contribute.monthId = "+month+" and contribute.finYearId= "+finYrId  ;
		String strQuery = "SELECT contribute.contribution FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and contribute.typeOfPayment=700049 and emp.orgEmpMstId= " + empId + "  and contribute.monthId = " + month + " and contribute.finYearId= " + finYrId;
		Query query = hibsession.createQuery(strQuery);
		if (query != null && query.list() != null && query.list().size() > 0)
		{
			logger.info(" query is for getDCPSValue3  return value " + (Double) (query.uniqueResult() != null && query.list().get(0) != null ? query.uniqueResult() : dcps));
			return (Double) (query.uniqueResult() != null && query.list().get(0) != null ? query.uniqueResult() : dcps);
		}
		else
		{
			logger.info(" query is for getDCPSValue3  return value " + 0);
			return 0;
		}

	}

	public double getDCPSValue4(long empId, int month, long finYrId)
	{
		double dcps = 0d;
		Session hibsession = getSession();
		//String  strQuery = "SELECT sum(contribute.contribution) FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and emp.orgEmpMstId= "+empId+"  and contribute.monthId = "+month+" and contribute.finYearId= "+finYrId  ;
		String strQuery = "SELECT contribute.contribution FROM  TrnDcpsContribution contribute , MstEmp emp where emp.dcpsEmpId = contribute.dcpsEmpId and contribute.typeOfPayment=700048 and emp.orgEmpMstId= " + empId + "  and contribute.monthId = " + month + " and contribute.finYearId= " + finYrId;
		Query query = hibsession.createQuery(strQuery);
		if (query != null && query.list() != null && query.list().size() > 0)
			return (Double) (query.uniqueResult() != null && query.list().get(0) != null ? query.uniqueResult() : dcps);
		else
			return 0;

	}

	//09 jan 2012 for default component checks
	public boolean isDCPS(long empId)
	{
		logger.info(" Check if the empid " + empId + " is a dcps or not");
		boolean result = false;
		try
		{
			Session session = getSession();
			Query q1 = session.createSQLQuery("SELECT DCPS_OR_GPF FROM MST_DCPS_EMP where ORG_EMP_MST_ID in (SELECT EMP_MPG_ID FROM HR_EIS_EMP_MST where EMP_ID = " + empId + ")");
			String o1 = (q1.uniqueResult()).toString();
			if (o1.equalsIgnoreCase("Y"))
				result = true;
		}
		catch (Exception e)
		{
			logger.error(" Employee not found in MST_DCPS_EMP ");
		}
		logger.info(" Result is " + result);
		return result;
	}

	public char isDCPS1(long empId) {
	    List<Character> l = null;
	    this.logger.info(" pandey  Check  isDCPS1 new method if the empid " + empId + " is a dcps or not");
	    Session session = getSession();
	    SQLQuery sQLQuery = session.createSQLQuery("SELECT DCPS_OR_GPF FROM MST_DCPS_EMP where ORG_EMP_MST_ID in (SELECT EMP_MPG_ID FROM HR_EIS_EMP_MST where EMP_ID = " + empId + ")");
	    l = sQLQuery.list();
	    char result = ((Character)l.get(0)).charValue();
	    return result;
	  }
	  
	  public String isDCPS2(long empId) {
	    List<String> l = null;
	    this.logger.info("  new Pandey 21 jan 2021  " + empId + " is a dcps or not");
	    Session session = getSession();
	    SQLQuery sQLQuery = session.createSQLQuery("SELECT case WHEN DCPS_OR_GPF ='Y' THEN 'DCPS' WHEN DCPS_OR_GPF ='N' THEN 'GPF' END FROM MST_DCPS_EMP where ORG_EMP_MST_ID in (SELECT EMP_MPG_ID FROM HR_EIS_EMP_MST where EMP_ID = " + empId + ")");
	    l = sQLQuery.list();
	    String result = l.get(0);
	    return result;
	  }
	  
	  public boolean isDCPSStop(long empId) {
	    this.logger.info(" Check if the empid 11" + empId + " is a dcps or not");
	    boolean result = false;
	    String var4 = "";
	    try {
	      Session hibSession = getSession();
	      StringBuffer strBfr = new StringBuffer();
	      strBfr.append("SELECT * FROM MST_DCPS_EMP a,ORG_DDO_MST b,hr_eis_emp_mst c where a.ORG_EMP_MST_ID = c.EMP_MPG_ID and  a.DDO_CODE = b.DDO_CODE and a.REG_STATUS = 1 and (a.ddo_code <> '1111222222' or a.ddo_code is null)  and c.emp_id = " + empId);//and a.EMP_ELIGIBILITY_FLAG = 'Y'
	      SQLQuery sQLQuery = hibSession.createSQLQuery(strBfr.toString());
	      this.logger.info(" Cquery 11 --------" + sQLQuery);
	      if (sQLQuery != null && sQLQuery.list().size() > 0)
	        result = true; 
	    } catch (Exception var8) {
	      this.logger.error(" Employee not found in MST_DCPS_EMP ");
	    } 
	    this.logger.info(" Result is " + result);
	    return result;
	  }
	  
	
	
	public boolean isNewConfig(long empId)
	{
		List hrEisMpgData = null;
		Session hibsession = getSession();
		boolean result = false;
		String HQL_QUERY = "select hreismpg from HrEisEmpCompMpg hreismpg where hreismpg.hrEisEmpCompGrpMst.hrEisEmpMst.empId=" + empId;
		logger.info(" Check if it is new configuration ");

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getDataIDisPresent query :: " + query);
		hrEisMpgData = query.list();

		if (hrEisMpgData == null || hrEisMpgData.size() <= 0)
		{
			result = true;

		}

		logger.info(" Value for new config " + result);
		return result;
	}

	public void updateHRAinHrEisOtherDtls(long empId, int status)
	{
		/*GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(HrEisOtherDtls.class);
		Session session = genDAO.getSession();
		Criteria crit = session.createCriteria(HrEisOtherDtls.class);
		crit.add(Restrictions.eq("HrEisEmpMst",)*/
		logger.info(" updating isAvailed HRA in HrEisOtherDtls for empid " + empId + " and status " + status);
		String HQL_QUERY = "update HrEisOtherDtls set isAvailedHRA = " + status + " where hrEisEmpMst = " + empId;
		Query q1 = getSession().createQuery(HQL_QUERY);
		if (q1.executeUpdate() >= 0)
			logger.info(" Updated ");

	}

	//Added by Kishan
	public List getAllEmployeeByLocId(long locID)
	{
		logger.info("getAllEmployeeByLocId start");
		Session session = getSession();
		StringBuffer HQL_QUERY = new StringBuffer();
		HQL_QUERY.append("select grp from HrEisEmpCompGrpMst grp, HrEisEmpMst eis, OrgEmpMst org, OrgUserpostRlt userpost, OrgPostMst post ");
		HQL_QUERY.append("where grp.hrEisEmpMst.empId = eis.empId ");
		HQL_QUERY.append("and eis.orgEmpMst.empId = org.empId ");
		HQL_QUERY.append("and org.orgUserMst.userId = userpost.orgUserMst.userId ");
		HQL_QUERY.append("and userpost.orgPostMstByPostId.postId = post.postId ");
		HQL_QUERY.append("and post.locationCode = " + locID + " ");
		HQL_QUERY.append("and post.activateFlag = 1 ");
		HQL_QUERY.append("and grp.isactive = 1 ");
		HQL_QUERY.append("and userpost.activateFlag = 1 ");
		Query query = session.createQuery(HQL_QUERY.toString());
		logger.info("getAllEmployeeByLocId end");
		return query.list();
	}

	public Map getDuplicateDataFromCompoMpg(long locID, long compoType, String compoId)
	{
		logger.info("getDuplicateDataFromCompoMpg start");
		Session session = getSession();
		StringBuffer HQL_QUERY = new StringBuffer();

		HQL_QUERY.append("select cmpmpg from HrEisEmpCompGrpMst grp, HrEisEmpMst eis, OrgEmpMst org, OrgUserpostRlt userpost, OrgPostMst post, HrEisEmpCompMpg cmpmpg ");
		HQL_QUERY.append("where grp.hrEisEmpMst.empId = eis.empId ");
		HQL_QUERY.append("and eis.orgEmpMst.empId = org.empId ");
		HQL_QUERY.append("and org.orgUserMst.userId = userpost.orgUserMst.userId ");
		HQL_QUERY.append("and userpost.orgPostMstByPostId.postId = post.postId ");
		HQL_QUERY.append("and post.locationCode = " + locID + " ");
		HQL_QUERY.append("and post.activateFlag = 1 ");
		HQL_QUERY.append("and grp.isactive = 1 ");
		HQL_QUERY.append("and userpost.activateFlag = 1 ");
		HQL_QUERY.append("and cmpmpg.hrEisEmpCompGrpMst.EmpCompGrpId = grp.EmpCompGrpId ");
		HQL_QUERY.append("and cmpmpg.cmnLookupMst.lookupId in (" + compoType + ") ");
		HQL_QUERY.append("and cmpmpg.compId in (" + compoId + ") ");
		//		HQL_QUERY.append("and cmpmpg.isactive = 0 ");
		Query query = session.createQuery(HQL_QUERY.toString());
		List resultList = query.list();
		logger.info("Result list size ->" + resultList.size());
		Map<String, HrEisEmpCompMpg> duplicateDataMap = new HashMap();
		for (int p = 0; p < resultList.size(); p++)
		{
			HrEisEmpCompMpg hrEisEmpCompMpg = (HrEisEmpCompMpg) resultList.get(p);
			String key = "" + hrEisEmpCompMpg.getHrEisEmpCompGrpMst().getEmpCompGrpId() + hrEisEmpCompMpg.getCompId();
			duplicateDataMap.put(key, hrEisEmpCompMpg);
		}
		logger.info("getDuplicateDataFromCompoMpg end");
		return duplicateDataMap;
	}
	//Ended by kishan
}
