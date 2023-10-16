package com.tcs.sgv.exprcpt.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.exprcpt.valueobject.TrnBsRcptEntries;

/**
 * ClassName BankRcptScrollDAOImpl
 * 
 * Description Implementaton of Bank scroll Receipt method
 * Date of Creation 1 august 2007
 *  
 * @author 602409
 * Revision History ===================== Bhavesh 23-Oct-2007 For making
 * changes for code formating
 *
 */

public class BankRcptScrollDAOImpl extends GenericDaoHibernateImpl<TrnBsRcptEntries,Integer> implements  BankRcptScrollDAO {
  Log logger = LogFactory.getLog(getClass());

  public BankRcptScrollDAOImpl(Class<TrnBsRcptEntries> type, SessionFactory sessionFactory)
  {
    super(type);
    setSessionFactory(sessionFactory);
  }

  /**
   * This method returns list of all receipt entries for a given bank scroll
   * @param bsDetailId Id of a bank scroll 
   * @return List 
   */
  public List getRcptDtlsByBkSclId(long bsDetailId) {
    List dataList = null;

    try{
      dataList = getSession().createCriteria(TrnBsRcptEntries.class).add(Expression.eq("bsDetailId",bsDetailId)).list();
    } catch(Exception ex){
      ex.printStackTrace();
      logger.error("Exception occurred in BankPayScrollDAOImpl.getPayDtlsByBkScrlId # \n"+ex);
    }
    return dataList;
  }	
}
