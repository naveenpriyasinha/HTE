package com.tcs.sgv.onlinebillprep.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ReportQueryDAOImpl extends GenericDaoHibernateImpl<DDODetailsVO, Integer> implements ReportQueryDAO 
{
	private static final Logger glogger=Logger.getLogger(ReportQueryDAOImpl.class);

	public ReportQueryDAOImpl(Class<DDODetailsVO> type,SessionFactory sessionFactory) 
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	public ReportQueryDAOImpl() 
	{
		super(DDODetailsVO.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tcs.sgv.onlinebillprep.report.ReportQueryDAO#getBillTrackingReportForDDo(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 * 
	 * Create By Sagar.
	 * 
	 * For Generate Bill Tracking Report For DDO. 
	 */
	
	public List getBillTrackingReportForDDo(String strFromDate,String strToDate,Long subjectId,String strLocID,String strLocationCode,Long langId,Long postId)
	{
		List billRegisterForDDO=new ArrayList();
		Session hibSession = getSession();
		StringBuffer lSBQuery = new StringBuffer();	
	
		lSBQuery.append(" SELECT BR.billCntrlNo, BT.subjectDesc, BR.billNetAmount,");
        lSBQuery.append(" DD.ddoName, BR.tokenNum,");
        lSBQuery.append(" (select EM.empFname|| ' ' || EM.empMname || ' ' || EM.empLname ");
        lSBQuery.append(" from OrgEmpMst EM where EM.orgUserMst.userId  = BM.statusUpdtUserid AND EM.cmnLanguageMst.langId = :langId),");
        
        lSBQuery.append(" (select SL.statusName from MstStatusLiability SL where SL.statusCode = BR.currBillStatus), ");
        
        lSBQuery.append(" BM.statusUpdtDate");
                              
        lSBQuery.append(" FROM TrnBillRegister BR, OrgDdoMst DD, MstBillType BT, TrnBillMvmnt BM");
        
        lSBQuery.append(" WHERE BR.billNo = BM.billNo AND BR.ddoCode = DD.ddoCode ");
        lSBQuery.append(" AND BR.subjectId = BT.subjectId AND DD.langId = BT.langId AND DD.langId = :langId  ");
        
        if(subjectId != -1)
		{
        	lSBQuery.append(" AND BR.subjectId="+subjectId);
		}
        if(strFromDate != null && strToDate != null)
		{
        	lSBQuery.append(" and to_char(BM.createdDate,'yyyy-mm-dd') >= '"+strFromDate+"' and to_char(BM.createdDate,'yyyy-mm-dd')<='"+strToDate+"'");
		}
        
        lSBQuery.append(" AND BM.movemntId = (select MAX(m.movemntId) from TrnBillMvmnt m where m.billNo = BR.billNo) ");
        
        lSBQuery.append(" and BR.billNo in (Select m.billNo from TrnBillMvmnt m, TrnBillRegister r ");
        lSBQuery.append(" Where m.billNo = r.billNo and m.mvmntStatus = 'BSNT_TO' and");
        lSBQuery.append(" m.createdPostId = :postId group by m.billNo)");
        
        lSBQuery.append(" order by BM.statusUpdtDate desc");
        
        Query lQuery = hibSession.createQuery(lSBQuery.toString());
        
               
        lQuery.setParameter("langId", langId);
        lQuery.setParameter("postId", postId);
                
		 //System.out.println("query rpt :Sagar: "+ lSBQuery.toString());
		    List resList=lQuery.list();
		    if(resList.size()==0)
		    {
		    	return null;
		    }
		    
		    Iterator it=resList.iterator();
			   
		    int Counter=0;
		    //System.out.println("While before");
		    while(it.hasNext())
		    {
		    	Object[] tuple=(Object[])it.next();
		    	ArrayList innerList = new ArrayList();
		    	innerList.add(++Counter);
				try {
				    innerList.add(tuple[0].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
				    innerList.add(tuple[1].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					DecimalFormat dFormat=new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount=Double.parseDouble(tuple[2].toString());
					String strAmount=dFormat.format(dAmount);
					innerList.add(strAmount.toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
				    innerList.add(tuple[3].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
				    innerList.add(tuple[4].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
				    innerList.add(tuple[5].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
				    innerList.add(tuple[6].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[7].toString())));
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				billRegisterForDDO.add(innerList);
		    }
		
		return billRegisterForDDO;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tcs.sgv.onlinebillprep.report.ReportQueryDAO#getBillTrackingReportForCashBranch(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
	 * 
	 * Create By Sagar.
	 * For Bill Tracking Report For Cash Branch....
	 */
	
	public List getBillTrackingReportForCashBranch(String strFromDate,String strToDate,Long subjectId,String strLocID,String strLocationCode,Long langId,Long postId)
	{
		List billRegisterForDDO=new ArrayList();
		Session hibSession = getSession();
		StringBuffer lSBQuery = new StringBuffer();	
		
        lSBQuery.append(" SELECT BR.billCntrlNo, BR.createdDate, BR.billNetAmount, DD.ddoName, ");
        
        lSBQuery.append(" (Select m.createdDate from TrnBillMvmnt m Where m.mvmntStatus = 'BSNT_TO' and " );
        lSBQuery.append("  m.createdPostId = :postId and m.billNo = BR.billNo),BR.tokenNum, ");
        
        lSBQuery.append(" (select EM.empFname|| ' ' || EM.empMname || ' ' || EM.empLname ");
        lSBQuery.append(" from OrgEmpMst EM where EM.orgUserMst.userId  = BM.statusUpdtUserid AND EM.cmnLanguageMst.langId = :langId),");
      
        lSBQuery.append(" BM.statusUpdtDate,");
        lSBQuery.append(" (select EM.empFname || ' ' || EM.empMname || ' ' || EM.empLname from OrgEmpMst EM ");
        lSBQuery.append("  where EM.orgUserMst.userId  = BM.statusUpdtUserid AND EM.cmnLanguageMst.langId = :langId");
        lSBQuery.append("  AND BM.mvmntStatus IN ('BAPPRV_AUD','BRJCT_AUD')),");
        
        lSBQuery.append(" (SELECT BM.createdDate FROM TrnBillMvmnt BM WHERE BM.billNo = BR.billNo ");
        lSBQuery.append("  AND BM.mvmntStatus IN ('BAPPRV_AUD','BRJCT_AUD')),");
        lSBQuery.append(" BR.billNo");
        
        lSBQuery.append(" FROM TrnBillRegister BR, OrgDdoMst DD, MstBillType BT, TrnBillMvmnt BM");
        
        lSBQuery.append(" WHERE BR.billNo = BM.billNo AND BR.ddoCode = DD.ddoCode ");
        lSBQuery.append(" AND BR.subjectId = BT.subjectId AND DD.langId = BT.langId AND DD.langId = :langId  ");
        
        if(subjectId != -1)
		{
        	lSBQuery.append(" AND BR.subjectId="+subjectId);
		}
        if(strFromDate != null && strToDate != null)
		{
        	lSBQuery.append(" and to_char(BM.createdDate,'yyyy-mm-dd') >= '"+strFromDate+"' and to_char(BM.createdDate,'yyyy-mm-dd')<='"+strToDate+"'");
		}
        
        lSBQuery.append(" AND BM.movemntId = (select MAX(m.movemntId) from TrnBillMvmnt m where m.billNo = BR.billNo) ");
        
        lSBQuery.append(" and BR.billNo in (Select m.billNo from TrnBillMvmnt m, TrnBillRegister r ");
        lSBQuery.append(" Where m.billNo = r.billNo and m.mvmntStatus = 'BSNT_TO' and");
        lSBQuery.append(" m.createdPostId = :postId group by m.billNo)");
        
        lSBQuery.append(" order by BM.statusUpdtDate desc");
        
        Query lQuery = hibSession.createQuery(lSBQuery.toString());
        
               
        lQuery.setParameter("langId", langId);
        lQuery.setParameter("postId", postId);
                
		 //System.out.println("query rpt :Sagar: "+ lSBQuery.toString());
		    List resList=lQuery.list();
		    if(resList.size()==0)
		    {
		    	return null;
		    }
		    
		    Iterator it=resList.iterator();
			   
		    int Counter=0;
		    //System.out.println("While before");
		    while(it.hasNext())
		    {
		    	Object[] tuple=(Object[])it.next();
		    	ArrayList innerList = new ArrayList();
		    	innerList.add(++Counter);
				try {
				    innerList.add(tuple[0].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[1].toString())));
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					DecimalFormat dFormat=new DecimalFormat();
					dFormat.applyPattern("#0.00");
					double dAmount=Double.parseDouble(tuple[2].toString());
					String strAmount=dFormat.format(dAmount);
					innerList.add(strAmount.toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(tuple[3].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[4].toString())));
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(tuple[5].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(tuple[6].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[7].toString())));
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(tuple[8].toString());
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					innerList.add(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[9].toString())));
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				try {
					
					hibSession = getSession();
					
					String BR_billNo = tuple[10].toString();
					
					//System.out.println("BR_billNo rpt :Sagar: "+ BR_billNo);
					
					StringBuffer lChkQuery = new StringBuffer();
					StringBuffer lLstChkNo = new StringBuffer();
					
					lChkQuery.append(" select ch.chequeNo from TrnChequeDtls ch, RltBillCheque rbp ");
					lChkQuery.append(" where ch.chequeId = rbp.chequeId and rbp.billNo = " + BR_billNo);
										
					Query lQuery1 = hibSession.createQuery(lChkQuery.toString());
					
					//lQuery.setParameter("BillNo", Integer.parseInt(BR_billNo));
	                					
					List chkresList=lQuery1.list();
					//System.out.println("chkresList.size :Sagar: "+ chkresList.size());
					
					//System.out.println("BR_billNo rpt :Sagar: "+ BR_billNo);
					
					for(int i=0;i<chkresList.size();i++){
						lLstChkNo.append(chkresList.get(i)+",\n");
					}
					
					innerList.add(lLstChkNo.toString());
					
				}
				catch(Exception Ex)
				{
				    innerList.add("-");
				}
				
				billRegisterForDDO.add(innerList);
		    }
		
		return billRegisterForDDO;
	}	
	
	public ArrayList getBillType(String lStrLangId, String lstrLocId) 
    {
		ArrayList arrBillType = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String subjectId = null;
        String subjectDesc = null;
        
        try
        {
            lCon = DBConnection.getConnection(  );
            StringBuffer lsb = new StringBuffer(  );
            lsb = new StringBuffer( "select subject_id,subject_desc from mst_bill_type where activate_flag=1 " );
            lStmt = lCon.prepareStatement( lsb.toString() );
            lRs = lStmt.executeQuery();
            while(lRs.next())
            {
            	ComboValuesVO vo= new ComboValuesVO(  );
                subjectId = lRs.getString("subject_id");
                subjectDesc = lRs.getString("subject_desc");
                vo.setId(subjectId);
                vo.setDesc(subjectDesc);
                arrBillType.add(vo);
            }
        }
        catch( SQLException se )
        {
            glogger.error( "SQLException::"+se.getMessage(), se );
           
        }
        catch( Exception e )
        {
            glogger.error( "Exception::"+e.getMessage(), e );
        }
        return arrBillType;
    }
}
