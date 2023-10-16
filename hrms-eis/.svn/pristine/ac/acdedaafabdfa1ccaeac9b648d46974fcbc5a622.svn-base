package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;

public class BillGroupPostMpgDAOImpl extends GenericDaoHibernateImpl<HrPayPsrPostMpg, Long> implements BillGroupPostMpgDAO
{
		Log logger = LogFactory.getLog(getClass());
		public BillGroupPostMpgDAOImpl(Class<HrPayPsrPostMpg> type,SessionFactory sessionFactory)
		{
			super(type);
			setSessionFactory(sessionFactory);
		}
		
		public List<HrPayBillHeadMpg> getAllUniqueBbillNo()
		{
			List HrpaybillNoList=null;
			Session hibSession = getSession();
			String HQL_QUERY = "select distinct hrbillmpg.billId from HrPayBillHeadMpg hrbillmpg order by hrbillmpg.billId";
			Query query = hibSession.createQuery(HQL_QUERY);
			logger.info("===> getAllUniqueBbillNo query :: "+query);
			HrpaybillNoList = query.list();
			logger.info("===> HrpaybillNoList.size() :: "+HrpaybillNoList.size());
			return HrpaybillNoList;
		}
		public List<OrgPostDetailsRlt> getPostNameNotMapped(long billNo)
		{
			List orgpostDtlsNotMappedList=null;
			Session session = getSession();
			String HQL_QUERY="select orgpostdtls from OrgPostDetailsRlt orgpostdtls where orgpostdtls.orgPostMst.postId not in "+
			"(select psrmpg.postId from HrPayPsrPostMpg psrmpg where psrmpg.billNo="+billNo+")";

			Query query = session.createQuery(HQL_QUERY);
			logger.info("getPostNameNotMapped query :: "+query);
			orgpostDtlsNotMappedList = query.list();
			logger.info("==> size of orgpostDtlsNotMappedList :: "+orgpostDtlsNotMappedList.size());
			return orgpostDtlsNotMappedList;
		}
		public List<OrgPostDetailsRlt> getPostNameMapped(long billNo)
		{
			List orgpostDtlsMappedList=null;
			Session session = getSession();
			String HQL_QUERY="select orgpostdtls from OrgPostDetailsRlt orgpostdtls,HrPayPsrPostMpg psrmpg where orgpostdtls.orgPostMst.postId"+
			"=psrmpg.postId and psrmpg.billNo="+billNo;
			Query query = session.createQuery(HQL_QUERY);
			logger.info("getPostNameMapped query :: "+query);
			orgpostDtlsMappedList = query.list();
			logger.info("size of orgpostDtlsMappedList... :: "+orgpostDtlsMappedList.size());
			return orgpostDtlsMappedList;
		}
		
		public List<HrPayPsrPostMpg> getUpdateListAs0(String OldMappedPostIdList,String NewMappedPostId,long BillNO)
		{
			List UpdateList=null;
			Session session = getSession();
			
			String HQL_QUERY ="select psr from HrPayPsrPostMpg psr where psr.postId in("+OldMappedPostIdList+")"+
							  "and psr.postId not in("+NewMappedPostId+")"+
							  "and psr.billNo="+BillNO;
			
			Query query = session.createQuery(HQL_QUERY);
			logger.info("=====>in getUpdateListAs0 :: "+query);
			UpdateList = query.list();
			logger.info("===> in getUpdateListAs0 Size of :: "+UpdateList.size());
			
			return UpdateList; 
		}
		public List<HrPayPsrPostMpg> getUpdateListAsBillNo(String NewMappedPostId,String OldMappedPostIdList)
		{
			List InsertList=null;
			Session session = getSession();
			
			String HQL_QUERY = "select psr from HrPayPsrPostMpg psr where psr.postId in("+NewMappedPostId+")"+
							   "and psr.postId not in("+OldMappedPostIdList+")";
			
			Query query = session.createQuery(HQL_QUERY);
			logger.info("====> in getUpdateListAsBillNo :: "+query);
			InsertList = query.list();
			logger.info("===> in getUpdateListAsBillNo size of :: "+InsertList.size());
			
			return InsertList;
		}
}
