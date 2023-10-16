package com.tcs.sgv.common.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;

import com.tcs.sgv.common.valueobject.BillEdpVO;
import com.tcs.sgv.common.valueobject.MstEdp;
import com.tcs.sgv.common.valueobject.RltBillTypeEdp;

import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * ClassName BillEdpDAOImpl
 * 
 * Description Implementaton of edp related services
 * Date of Creation 1 august 2007
 *  
 * @author 602409
 * Revision History ===================== Bhavesh 23-Oct-2007 For making
 * changes for code formating
 *
 */
public class BillEdpDAOImpl extends GenericDaoHibernateImpl<TrnBillEdpDtls,Long> implements BillEdpDAO {

  private static Log logger = LogFactory.getLog(PostConfigurationDAOImpl.class);

  public BillEdpDAOImpl(Class<TrnBillEdpDtls> type, SessionFactory sessionFactory) {
    super(type);
    setSessionFactory(sessionFactory);
  }
  public TrnBillEdpDtls getBillEdpDtl(String edpCode, Long billNo,String sLocCode,String sExpRecRcp) {
    TrnBillEdpDtls edpDtls = null;
    logger.info("Inside The Get Details1"+edpCode+"-"+billNo);
    try {
      Session hibSession = getSession();
      List resList = hibSession.createCriteria(TrnBillEdpDtls.class)
      .add(Expression.eq("edpCode",edpCode))
      .add(Expression.eq("billNo",billNo))
      .add(Expression.eq("expRcpRec",sExpRecRcp)).list();
      logger.info("size Of List is:-"+resList.size());
      if (resList!=null && resList.size()>0) {
        edpDtls = (TrnBillEdpDtls) resList.get(0);
      }

    } catch(Exception ex) {
      logger.error("Exception occured in BillEdpDAOImpl.getBillEdpDtl #\n"+ex);
    }
    logger.info("edp Detail Vo is :-"+edpDtls);
    return edpDtls;
  }
  
  
  //added by Ankit Bhatt
  public RltBillTypeEdp getEdpDtls(String typeEdpId, String sLocCode) {
	  com.tcs.sgv.common.valueobject.RltBillTypeEdp rltBillTypeEdp = new RltBillTypeEdp();
	    
	    try {
	    	logger.info("TypeEdpId and locationCode is " + typeEdpId + " " + sLocCode);
	      Session hibSession = getSession();
	      String strQuery = "from RltBillTypeEdp where typeEdpId = " + Long.parseLong(typeEdpId);
	      Query hibQuery = hibSession.createQuery(strQuery);
	      List resList = hibQuery.list();
	      //.add(Expression.eq("locationCode",sLocCode)).list();   //should be added if there are location specific entries in rlt_bill_type_edp
	      logger.info("size Of List from getEdpDtls is:-"+resList.size());
	      if (resList!=null && resList.size()>0) {
	    	  rltBillTypeEdp = (com.tcs.sgv.common.valueobject.RltBillTypeEdp) resList.get(0);
	      }

	    } catch(Exception ex) {
	      logger.error("Exception occured in getEdpDtls #\n"+ex);
	    }
	    logger.info("rltBillTypeEdp is :-"+rltBillTypeEdp);
	    return rltBillTypeEdp;
	  }
//ended by Ankit Bhatt  

  /**
   * Method to get MstEdp vo by edpCode and lang Id.
   * @param edpCode
   * @param landId
   * @return MstEdp
   */
  public MstEdp getMstEdpVoDtls(String edpCode,Long landId)
  {
    MstEdp mstEdpVO=new MstEdp();
    try{
      Session hibSession = getSession();
      List resList = hibSession.createCriteria(MstEdp.class)
      .add(Expression.eq("edpCode",edpCode))
      .add(Expression.eq("langId",landId))
      .add(Expression.eq("receiptEdp","Y")).list();
      logger.info("size Of List is:-"+resList.size());
      if (resList!=null && resList.size()>0) {
        mstEdpVO = (MstEdp) resList.get(0);
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    return mstEdpVO;
  }

  /**
   * Method to get Recovery Object head details.
   * @param subjectId
   * @param landId
   * @param billNo
   * @param slocCode
   * @return List
   */
  public List getRecObjHdDtlByBillType(Long subjectId, Long billNo, Long langId, String slocCode) 
  {
    List dataList = new ArrayList();
    String sExpRecRcp="REC";
    try {
      Session session = getSession();
      StringBuffer hqlQuery = new StringBuffer();
      hqlQuery.append("select rbe.typeEdpId, me.budobjhdCode, rbe.addDedFlag, rbe.edpCategory, me.edpCode, me.edpDesc ");
      hqlQuery.append("from RltBillTypeEdp rbe, MstEdp me where me.edpCode=rbe.edpCode and rbe.subjectId="+subjectId+" and me.receiptEdp='N' and rbe.expRcpRec = 'REC'");
      hqlQuery.append(" and me.langId="+langId );
      hqlQuery.append(" order by me.edpCode");

      List resList  = session.createQuery(hqlQuery.toString()).list();
      logger.info("Inside The Recovery Details:-"+resList.size());
      if (resList !=null) {
        Iterator it = resList.iterator();				
        while(it.hasNext()){
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setTypeEdpId((Long) tuple[0]);
          billEdpVO.setObjHdCode((String) tuple[1]);
          billEdpVO.setAddDedFlag((String)tuple[2]);
          billEdpVO.setEdpCategory((String)tuple[3]);
          billEdpVO.setEdpCode((String)tuple[4]);
          billEdpVO.setEdpDesc((String)tuple[5]);	
          billEdpVO.setEdpAmt(new BigDecimal(0));
          if (billNo!=null) {
            TrnBillEdpDtls edpDtls = this.getBillEdpDtl((String) tuple[4], billNo,slocCode,sExpRecRcp);
            if (edpDtls!=null)	billEdpVO.setEdpAmt(edpDtls.getEdpAmt());
            else billEdpVO.setEdpAmt(new BigDecimal(0));					
          }					
          dataList.add(billEdpVO);
        }
      }
    } catch(Exception ex) {
      logger.error("Exception occured in BillEdpDAOImpl.getRecObjHdDtlByBillType #\n"+ex);
    }

    return dataList;

  }

  /**
   * Method to get Receipt Object head details By Bill Type.
   * @param subjectId
   * @param landId
   * @param billNo
   * @param slocCode
   * @param tcBill
   * @param majorHead
   * @return List
   */
  public List getRcptEdpDtlByBillType(Long subjectId,Long billNo,Long langId,String slocCode,String tcBill,String majorHead) 
  {
    List dataList = new ArrayList();
    String sExpRecRcp="RCP";
    try {

      Session session = getSession();
      StringBuffer hqlQuery = new StringBuffer();
      if(tcBill!=null && tcBill.equals("TC"))
      {
        hqlQuery.append("select rbe.typeEdpId, me.budobjhdCode, rbe.addDedFlag, rbe.edpCategory, me.edpCode, ");
        hqlQuery.append(" me.edpDesc, me.budmjrhdCode, me.budsubmjrhdCode, me.budminhdCode, me.budsubhdCode, me.buddtlhdCode, me.budobjhdCode ");
        hqlQuery.append(" from RltBillTypeEdp rbe, MstEdp me where rbe.edpCode=me.edpCode and  me.receiptEdp='Y' and rbe.subjectId="+subjectId + " and rbe.expRcpRec='RCP'");
        hqlQuery.append(" and me.langId="+langId+" and me.budmjrhdCode='"+majorHead+"'");
        hqlQuery.append(" order by rbe.edpCategory,me.edpCode");
      }
      else
      {
        hqlQuery.append("select rbe.typeEdpId, me.budobjhdCode, rbe.addDedFlag, rbe.edpCategory, me.edpCode, ");
        hqlQuery.append(" me.edpDesc, me.budmjrhdCode, me.budsubmjrhdCode, me.budminhdCode, me.budsubhdCode, me.buddtlhdCode, me.budobjhdCode ");
        hqlQuery.append(" from RltBillTypeEdp rbe, MstEdp me where rbe.edpCode=me.edpCode and  me.receiptEdp='Y' and rbe.subjectId="+subjectId + " and rbe.expRcpRec='RCP'");
        hqlQuery.append(" and me.langId="+langId );
        hqlQuery.append(" order by rbe.edpCategory,me.edpCode");
      }

      List resList  = session.createQuery(hqlQuery.toString()).list();
      if (resList !=null) {
        Iterator it = resList.iterator();				
        while(it.hasNext()){
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setTypeEdpId((Long) tuple[0]);
          billEdpVO.setObjHdCode((String) tuple[1]);
          billEdpVO.setAddDedFlag((String)tuple[2]);					
          billEdpVO.setEdpCategory((String)tuple[3]);
          billEdpVO.setEdpCode((String)tuple[4]);
          billEdpVO.setEdpDesc((String)tuple[5]);
          billEdpVO.setBudmjrHd((String)tuple[6]);
          billEdpVO.setBudsubmjrHd((String)tuple[7]);
          billEdpVO.setBudminHd((String)tuple[8]);
          billEdpVO.setBudsubHd((String)tuple[9]);
          billEdpVO.setBuddtlHd((String)tuple[10]);
          billEdpVO.setBudobjHd((String)tuple[11]);
          billEdpVO.setEdpAmt(new BigDecimal(0));
          if (billNo!=null) {
            TrnBillEdpDtls edpDtls = this.getBillEdpDtl((String) tuple[4], billNo,slocCode,sExpRecRcp);
            if (edpDtls!=null)	billEdpVO.setEdpAmt(edpDtls.getEdpAmt());
            else billEdpVO.setEdpAmt(new BigDecimal(0));
          }					
          dataList.add(billEdpVO);
        }
      }			
    } catch(Exception ex) {
      logger.error("Exception occured in BillEdpDAOImpl.getRcptEdpDtlByBillType #\n"+ex);
    }

    return dataList;
  }

  /**
   * Method to get Expenditure Object head details by bill Type.
   * @param subjectId
   * @param landId
   * @param billNo
   * @param slocCode
   * @return List
   */
  public List getExpObjHdDtlByBillType(Long subjectId, Long billNo, Long langId, String slocCode) 
  {
    List dataList = new ArrayList();
    String sExpRecRcp="EXP";
    try {
      Session session = getSession();

      StringBuffer hqlQuery = new StringBuffer();
      hqlQuery.append("select rbe.typeEdpId, me.budobjhdCode, rbe.addDedFlag, rbe.edpCategory, me.edpCode, me.edpDesc ");
      hqlQuery.append("from RltBillTypeEdp rbe,MstEdp me where me.edpCode=rbe.edpCode and rbe.subjectId="+subjectId+" and me.receiptEdp='N' and rbe.expRcpRec = 'EXP'");
      hqlQuery.append(" and me.langId="+langId );
      hqlQuery.append(" order by me.edpCode");

      List resList  = session.createQuery(hqlQuery.toString()).list();

      if (resList !=null) {
        Iterator it = resList.iterator();				
        while(it.hasNext()){
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setTypeEdpId((Long) tuple[0]);
          billEdpVO.setObjHdCode((String) tuple[1]);
          billEdpVO.setAddDedFlag((String)tuple[2]);
          billEdpVO.setEdpCategory((String)tuple[3]);
          billEdpVO.setEdpCode((String)tuple[4]);
          billEdpVO.setEdpDesc((String)tuple[5]);
          billEdpVO.setEdpAmt(new BigDecimal(0));
          if (billNo!=null) {						
            TrnBillEdpDtls edpDtls = this.getBillEdpDtl((String)tuple[4], billNo,slocCode,sExpRecRcp);
            if (edpDtls!=null)
            {
              billEdpVO.setEdpAmt(edpDtls.getEdpAmt());
              logger.info("Inside The set Amount");
            }
            else
            {
              billEdpVO.setEdpAmt(new BigDecimal(0));
              logger.info("Inside The set Amount ZERO");
            }
          }	

          dataList.add(billEdpVO);
        }
      }
    } catch(Exception ex) {
      logger.error("Exception occured in BillEdpDAOImpl.getExpObjHdDtlByBillType #\n"+ex);			
    }
    return dataList;
  }

  /**
   * Method to get Expenditure Object head details by bill No.
   * @param landId
   * @param billNo
   * @param slocCode
   * @return List
   */
  public List getExpObjHdDtlByBillNo(Long billNo, Long langId, String slocCode) 
  {
    List dataList = new ArrayList();
    try {
      Session session = getSession();

      StringBuffer hqlQuery = new StringBuffer();
      hqlQuery.append("select tbe.edpCode, me.budobjhdCode, tbe.addDedFlag, tbe.edpCategory, me.edpDesc,tbe.edpAmt");
      hqlQuery.append(" from TrnBillEdpDtls tbe, MstEdp me where me.edpCode=tbe.edpCode and tbe.billNo="+billNo+" and tbe.autoAdd='N' and me.receiptEdp='N' and tbe.expRcpRec = 'EXP'");
      hqlQuery.append(" and me.langId="+langId);

      List resList  = session.createQuery(hqlQuery.toString()).list();
      logger.info("Size of the added List is:-"+resList.size());
      if (resList !=null) {
        Iterator it = resList.iterator();				
        while(it.hasNext()){
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setEdpCode((String)tuple[0]);
          billEdpVO.setObjHdCode((String) tuple[1]);
          billEdpVO.setAddDedFlag((String)tuple[2]);
          billEdpVO.setEdpCategory((String)tuple[3]);
          billEdpVO.setEdpDesc((String) tuple[4]);
          billEdpVO.setEdpAmt(new BigDecimal(tuple[5].toString()));
          dataList.add(billEdpVO);
        }
      }
    } catch(Exception ex) {
      ex.printStackTrace();
      logger.error("Exception occured in BillEdpDAOImpl.getExpObjHdDtlByBillNo #\n"+ex);			
    }
    return dataList;
  }
  
  /**
   * Method to get Recovery Object head details by bill No.
   * @param landId
   * @param billNo
   * @param slocCode
   * @return List
   */

  public List getRecObjHdDtlByBillNo(Long billNo, Long langId, String slocCode) 
  {
    List dataList = new ArrayList();
    try {
      Session session = getSession();
      StringBuffer hqlQuery = new StringBuffer();
      hqlQuery.append("select tbe.edpCode, me.budobjhdCode, tbe.addDedFlag, tbe.edpCategory, me.edpDesc,tbe.edpAmt");
      hqlQuery.append(" from TrnBillEdpDtls tbe, MstEdp me where me.edpCode=tbe.edpCode and tbe.billNo="+billNo+" and tbe.autoAdd='N' and me.receiptEdp='N' and tbe.expRcpRec = 'REC'");
      hqlQuery.append(" and me.langId="+langId);

      List resList  = session.createQuery(hqlQuery.toString()).list();
      logger.info("Size of the added List is:-"+resList.size());
      if (resList !=null) {
        Iterator it = resList.iterator();				
        while(it.hasNext()){
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setEdpCode((String)tuple[0]);
          billEdpVO.setObjHdCode((String) tuple[1]);
          billEdpVO.setAddDedFlag((String)tuple[2]);
          billEdpVO.setEdpCategory((String)tuple[3]);
          billEdpVO.setEdpDesc((String) tuple[4]);
          billEdpVO.setEdpAmt(new BigDecimal(tuple[5].toString()));
          dataList.add(billEdpVO);
        }
      }
    } catch(Exception ex) {
      ex.printStackTrace();
      logger.error("Exception occured in BillEdpDAOImpl.getRecObjHdDtlByBillNo #\n"+ex);			
    }
    return dataList;
  }

  /**
   * Method to get Receipt Object head details by bill No.
   * @param landId
   * @param billNo
   * @param slocCode
   * @return List
   */
  public List getRcptObjHdDtlByBillNo(Long billNo, Long langId, String slocCode) 
  {
    List dataList = new ArrayList();
    try {
      Session session = getSession();

      StringBuffer hqlQuery = new StringBuffer();

      hqlQuery.append("select tbe.edpCode, me.budobjhdCode, tbe.addDedFlag, tbe.edpCategory, me.edpDesc,tbe.edpAmt, ");
      hqlQuery.append(" me.budmjrhdCode, me.budsubmjrhdCode, me.budminhdCode, me.budsubhdCode, me.buddtlhdCode ");
      hqlQuery.append(" from TrnBillEdpDtls tbe, MstEdp me where me.edpCode=tbe.edpCode and tbe.billNo="+billNo+" and tbe.autoAdd='N' and tbe.expRcpRec = 'RCP' and me.receiptEdp='Y'");
      hqlQuery.append(" and me.langId="+langId);

      List resList  = session.createQuery(hqlQuery.toString()).list();
      logger.info("Size of the added List is:-"+resList.size());
      if (resList !=null) {
        Iterator it = resList.iterator();				
        while(it.hasNext()){
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setEdpCode((String)tuple[0]);
          billEdpVO.setObjHdCode((String) tuple[1]);
          billEdpVO.setAddDedFlag((String)tuple[2]);
          billEdpVO.setEdpCategory((String)tuple[3]);
          billEdpVO.setEdpDesc((String) tuple[4]);
          billEdpVO.setEdpAmt(new BigDecimal(tuple[5].toString()));
          billEdpVO.setBudmjrHd((String) tuple[6]);
          billEdpVO.setBudsubmjrHd((String) tuple[7]);
          billEdpVO.setBudminHd((String) tuple[8]);
          billEdpVO.setBudsubHd((String) tuple[9]);
          billEdpVO.setBuddtlHd((String) tuple[10]);
          dataList.add(billEdpVO);
        }
      }
    } catch(Exception ex) {
      ex.printStackTrace();
      logger.error("Exception occured in BillEdpDAOImpl.getRcptObjHdDtlByBillNo #\n"+ex);			
    }
    return dataList;
  }

  /**
   * Method to get all Edp details.
   * @param landId
   * @param slocCode
   * @param tcBill
   * @param majorHead
   * @return List
   */
  public List getExpEdpDtl(Long subjectId, Long langId, String slocCode,String tcBill,String majorHead) 
  {
    List dataList = new ArrayList();
    try {

      Session session = getSession();
      logger.info("Inside The ExpeDetails Getting");
      StringBuffer hqlQuery = new StringBuffer();
      hqlQuery.append("select me.edpCode, me.edpDesc, me.budobjhdCode,me.receiptEdp, me.budmjrhdCode,me.budsubmjrhdCode,me.budminhdCode,me.budsubhdCode,me.buddtlhdCode,me.budobjhdCode ");
      hqlQuery.append(" from MstEdp me where me.langId="+langId+" and me.receiptEdp='N' and me.edpCode NOT IN(select rbe.edpCode from RltBillTypeEdp rbe where rbe.subjectId="+subjectId+")");

      List resList  = session.createQuery(hqlQuery.toString()).list();

      if (resList !=null) {
        Iterator it = resList.iterator();				
        while(it.hasNext()){
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setEdpCode((String)tuple[0]);
          billEdpVO.setEdpDesc((String)tuple[1]);
          billEdpVO.setObjHdCode((String) tuple[2]);
          billEdpVO.setReceiptEdp((String) tuple[3]);
          billEdpVO.setBudmjrHd((String) tuple[4]);
          billEdpVO.setBudsubmjrHd((String) tuple[5]);
          billEdpVO.setBudminHd((String) tuple[6]);
          billEdpVO.setBudsubHd((String) tuple[7]);
          billEdpVO.setBuddtlHd((String) tuple[8]);
          billEdpVO.setBudobjHd((String) tuple[9]);
          dataList.add(billEdpVO);
        }
      }
      StringBuffer hqlRcpQuery = new StringBuffer();
      if(tcBill!=null && tcBill.equals("TC"))
      {
        hqlRcpQuery.append("select me.edpCode, me.edpDesc, me.budobjhdCode,me.receiptEdp, me.budmjrhdCode,me.budsubmjrhdCode,me.budminhdCode,me.budsubhdCode,me.buddtlhdCode,me.budobjhdCode ");
        hqlRcpQuery.append(" from MstEdp me where me.langId="+langId+" and me.receiptEdp='Y' and me.budmjrhdCode='"+majorHead+"' and me.edpCode NOT IN(select rbe.edpCode from RltBillTypeEdp rbe where rbe.subjectId="+subjectId+")");
      }
      else
      {
        hqlRcpQuery.append("select me.edpCode, me.edpDesc, me.budobjhdCode,me.receiptEdp, me.budmjrhdCode,me.budsubmjrhdCode,me.budminhdCode,me.budsubhdCode,me.buddtlhdCode,me.budobjhdCode ");
        hqlRcpQuery.append(" from MstEdp me where me.langId="+langId+" and me.receiptEdp='Y' and me.edpCode NOT IN(select rbe.edpCode from RltBillTypeEdp rbe where rbe.subjectId="+subjectId+")");
      }
      List resListRcp  = session.createQuery(hqlRcpQuery.toString()).list();

      if (resListRcp !=null) {
        Iterator it = resListRcp.iterator();				
        while(it.hasNext()){
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setEdpCode((String)tuple[0]);
          billEdpVO.setEdpDesc((String)tuple[1]);
          billEdpVO.setObjHdCode((String) tuple[2]);
          billEdpVO.setReceiptEdp((String) tuple[3]);
          billEdpVO.setBudmjrHd((String) tuple[4]);
          billEdpVO.setBudsubmjrHd((String) tuple[5]);
          billEdpVO.setBudminHd((String) tuple[6]);
          billEdpVO.setBudsubHd((String) tuple[7]);
          billEdpVO.setBuddtlHd((String) tuple[8]);
          billEdpVO.setBudobjHd((String) tuple[9]);
          dataList.add(billEdpVO);
        }
      }
    } catch(Exception ex) {
      ex.printStackTrace();
      logger.error("Exception occured in BillEdpDAOImpl.getExpEdpDtl #\n"+ex);			
    }
    return dataList;
  }
  /**
   * Method to get all Edp details for sub treasury details posting.
   * @param landId
   * @return List
   */
  public List getExpEdpDtl(Long langId) 
  {
    List dataList = new ArrayList();
    try 
    {
      Session session = getSession();
      StringBuffer hqlQuery = new StringBuffer();
      hqlQuery.append("select me.edpCode, me.edpDesc, me.budobjhdCode,me.receiptEdp, me.budmjrhdCode,me.budsubmjrhdCode,me.budminhdCode,me.budsubhdCode,me.buddtlhdCode,me.budobjhdCode ");
      hqlQuery.append(" from MstEdp me where me.langId="+langId);
      List resList  = session.createQuery(hqlQuery.toString()).list();
      if (resList !=null)
      {
        Iterator it = resList.iterator();				
        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          BillEdpVO billEdpVO = new BillEdpVO();
          billEdpVO.setEdpCode((String)tuple[0]);
          billEdpVO.setEdpDesc((String)tuple[1]);
          billEdpVO.setObjHdCode((String) tuple[2]);
          billEdpVO.setReceiptEdp((String) tuple[3]);
          billEdpVO.setBudmjrHd((String) tuple[4]);
          billEdpVO.setBudsubmjrHd((String) tuple[5]);
          billEdpVO.setBudminHd((String) tuple[6]);
          billEdpVO.setBudsubHd((String) tuple[7]);
          billEdpVO.setBuddtlHd((String) tuple[8]);
          billEdpVO.setBudobjHd((String) tuple[9]);
          dataList.add(billEdpVO);
        }
      }
    } 
    catch(Exception ex) 
    {
      ex.printStackTrace();
      logger.error("Exception occured in BillEdpDAOImpl.getExpEdpDtl #\n"+ex);			
    }
    return dataList;
  }
}
