package com.tcs.sgv.exprcpt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.TrnVerifiedAccount;
import com.tcs.sgv.exprcpt.valueobject.VoucherVO;

/**
 * ClassName VerifiedAccountDAOImpl
 * 
 * Description Implementaton Freeze account services
 * Date of Creation 15 oct 2007
 * @author 602409
 *
 */
public class VerifiedAccountDAOImpl extends GenericDaoHibernateImpl<TrnVerifiedAccount,Integer> 
{
		Log logger = LogFactory.getLog(getClass());
		
		public VerifiedAccountDAOImpl(Class<TrnVerifiedAccount> type, SessionFactory sessionFactory)
	    {
	        super(type);
	        setSessionFactory(sessionFactory);
	    }

		/**
     * Method to get end date By location wise.
		 * @param slocCode
		 * @return
		 */
		public Date getEndDateFromVerAcc(String slocCode)
		{
			Date endDate=null;
			try
			{
				Session hibSession = getSession();
				Query sqlQuery=hibSession.createSQLQuery("SELECT A.END_DATE FROM TRN_VERIFIED_ACCOUNT A WHERE A.AC_ID = (SELECT MAX(A.AC_ID) FROM TRN_VERIFIED_ACCOUNT A WHERE A.LOCATION_CODE='"+slocCode+"' AND A.ACTIVE=1)");
				
				List resList=sqlQuery.list();
				if(resList!=null && resList.size()>0)
				{
					endDate=(Date) resList.get(0);
					//System.out.println("End date is :-"+endDate);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
        logger.error("Exception occured in VoucherDAOImpl.getEndDateFromVerAcc # \n"+e);
			}
			return endDate;
		}
		
		
		/**
     * This method to display a list, whom freezed voucher and which date
		 * @param sLocCode
		 * @return
		 */
		public List getFreezedDateAndPost(String sLocCode)
		{
			List dataList = null;
			try 
			{
				Session hibSession = getSession();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT VA.END_DATE,EM.EMP_FNAME,EM.EMP_MNAME,EM.EMP_LNAME FROM TRN_VERIFIED_ACCOUNT VA,ORG_EMP_MST EM,ORG_USERPOST_RLT PR WHERE VA.POST_ID=PR.POST_ID AND PR.USER_ID=EM.USER_ID AND VA.LOCATION_CODE='"+sLocCode+"' AND VA.ACTIVE=1 ORDER BY VA.AC_ID DESC");
				Query sqlQuery=hibSession.createSQLQuery(sql.toString());
				List resList=sqlQuery.list();
				if (resList!=null && resList.size()>0) 
				{
					dataList = new ArrayList();
					Iterator it = resList.iterator();
					while(it.hasNext()) 
					{
						VoucherVO vo=new VoucherVO();
						Object[] tuple = (Object[])it.next();
						vo.setVoucherDate((Date) tuple[0]);
						vo.setDdoName(tuple[1]+" "+tuple[2]+" "+tuple[3]);
						//System.out.println(tuple[0]+" "+tuple[1]+" "+tuple[2]);
						dataList.add(vo);
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error("Exception occured in VoucherDAOImpl.getFreezedDateAndPost # \n"+e);
			}
			return dataList;
		}
		/**
     * Method to update active flag to 1 when unfreezed account. 
		 * @param sLocCode
		 */
		public void updateUnFreezeVoucher(String sLocCode)
		{
			try 
			{
				Session hibSession = getSession();
				StringBuffer sql = new StringBuffer();
				sql.append("UPDATE TRN_VERIFIED_ACCOUNT A SET A.ACTIVE=0 WHERE A.AC_ID = (SELECT MAX(A.AC_ID) FROM TRN_VERIFIED_ACCOUNT A WHERE A.LOCATION_CODE='"+sLocCode+"' AND A.ACTIVE=1)");
				Query sqlQuery=hibSession.createSQLQuery(sql.toString());
				sqlQuery.executeUpdate();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.error("Exception occured in VoucherDAOImpl.updateUnFreezeVoucher # \n"+e);
			}
		}
}
