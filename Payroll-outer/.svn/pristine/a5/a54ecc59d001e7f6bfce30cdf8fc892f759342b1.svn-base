package com.tcs.sgv.dss.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;

public class DSSRptReceiptDtlsDAOImpl extends GenericDaoHibernateImpl<RptReceiptDtls,BigDecimal> implements DssDataServiceDAO 
{
	public DSSRptReceiptDtlsDAOImpl(Class<RptReceiptDtls> type, SessionFactory sessionFac)
	{
		super(type);
		setSessionFactory(sessionFac);
	}
	Log logger = LogFactory.getLog(getClass());
	String voname = "-";
	public HashMap insertVO(ArrayList lArrayLstRptReceiptDtlsVO_DAO)
	{
		HashMap returnMap = new HashMap();
		boolean flag = false;
		String reason = "inserted successfully";
		
		try
		{
			logger.info("----------------------------insertVO function of DssReceiptDtlsDAOImpl starts---------------------");
			
			for(int i =0; i < lArrayLstRptReceiptDtlsVO_DAO.size();i++ )
			{
				RptReceiptDtls lObjRptReceiptDtlsVO = (RptReceiptDtls)lArrayLstRptReceiptDtlsVO_DAO.get(i);
				
				if(lObjRptReceiptDtlsVO.getFinYrId().equals(new BigDecimal(0)))
					lObjRptReceiptDtlsVO.setFinYrId(getFinYrId());
				
				lObjRptReceiptDtlsVO.toString();
				
				
				create(lObjRptReceiptDtlsVO);
				flag = true;
			}
 						
			logger.info("----------------------------insertVO function of DssReceiptDtlsDAOImpl ends---------------------");
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			reason = e.toString();
			voname = this.toString();
		}
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", voname);
		return returnMap;
	}
	 

	public HashMap deleteVo(BigDecimal bgdcRcptNo,String lStrRcptTypeCode,String lStrChallanCatgCode, BigDecimal trnReceiptId)
	{
		HashMap returnMap = new HashMap();
		String reason = "";
		boolean flag = false;
		double lGrossAmnt = 0 ;
		double lDedA = 0;
		double lDedB =0 ;
		double lRecvAmnt = 0;
		double lNetAmnt = 0;
		double lExpAmnt = 0;
		double lDisbAmnt = 0;
		double lDedctnAmount = 0;
		long expID = 0;
		String strDectnType = null;
		char  dedctnType = 'A'  ;
		int totalRows = 0;
		
		try
		{
		
			logger.info("-----------------------deleteVO function of DssReceiptDtlsDAO starts---------------------------");
			
			Session session = getSession();
			
			String query = "select nvl(rec.disbursement_amt,0), nvl(exp_id,0) ,dedctn_type,nvl(rec.amount,0)  "+
			 " from rpt_receipt_dtls rec "+
		         " where rec.trn_receipt_id = "+trnReceiptId +"  and rec.rcpt_no = "+ bgdcRcptNo +" and " +
		         		" rec.active = 'Y' and"+
		               " rec.rcpt_type_code ='"+lStrRcptTypeCode+"'  and rec.challan_catg_code = '"+lStrChallanCatgCode+"'";
			
			logger.info("The Query:-> " + query);
			
			List list = session.createSQLQuery(query).list();
			
 			if(list.size() > 0 )
 			{
 				Iterator it = list.iterator();
 				
 				if(it.hasNext())
 				{
 					Object[] obj = (Object[])it.next();
	 				lDisbAmnt = Double.parseDouble(obj[0].toString());
	 				expID = Long.parseLong(obj[1].toString());
	 				dedctnType = (Character)(obj[2]);
	 				lDedctnAmount = Double.parseDouble(obj[3].toString());
 				}
 			}
 			
 			query = "select count(*)  from rpt_receipt_dtls rcpt "+
 			         " where rcpt.exp_id = "+expID+" and rcpt.dedctn_type = 'A' and rcpt.active = 'Y' ";
 			
 			logger.info("The Query :-> "+ query);
 			
 			list = session.createSQLQuery(query).list();
 			if(list.size() > 0 )
 			{	
 				Iterator it = list.iterator();
 				totalRows = Integer.parseInt(((Object)it.next()).toString());
 			}
 			
 			 query = "update rpt_receipt_dtls r set r.active='N'   "+
			   " where rcpt_no = "+bgdcRcptNo+" " +
			   " and rcpt_type_code = '"+lStrRcptTypeCode+"' " +
			   " and challan_catg_code= '"+lStrChallanCatgCode+"' " +
			   " and trn_receipt_id = "+trnReceiptId;
			
			
 			 logger.info("The Query :-> " + query);
 			 
 			 Connection lcon = (Connection)session.connection();
 			 Statement stmt = lcon.createStatement();
 			 
 			 int i = stmt.executeUpdate(query);
 			 
 			
 			
 			if(dedctnType == 'A')
 			{
 				
 				if(totalRows >= 2)
 				{
 					query = "update rpt_expenditure_dtls expd"+
 				    			" set expd.dedctna_amt = expd.dedctna_amt - "+lDedctnAmount+", "+
 				    			" expd.net_amt     = expd.net_amt + ("+lDedctnAmount+" + "+lDisbAmnt+") "+
 				    			" where expd.exp_id = "+expID;
 				}
 				else
 				{
 					query = "update rpt_expenditure_dtls expd"+
		    			" set expd.dedctna_amt = expd.dedctna_amt - "+lDedctnAmount+", "+
		    			" expd.net_amt     = expd.net_amt + ("+lDedctnAmount+" + "+lDisbAmnt+"), "+
		    			" expd.exp_amt     = expd.gross_amnt - expd.recovery_amt"+
		    			" where expd.exp_id = "+expID;
 					
 				}
 				
 			}
 			else if(dedctnType == 'B')
 			{
 				if(totalRows > 0)
 				{
 					query = "update rpt_expenditure_dtls expd"+
 				    			" set expd.dedctnb_amt = expd.dedctnb_amt - "+lDedctnAmount+", "+
 				    			" expd.net_amt     = expd.net_amt + ("+lDedctnAmount+" + "+lDisbAmnt+"), "+
 				    			" expd.exp_amt     = (expd.exp_amt + "+lDedctnAmount+")"+
 				    			" where expd.exp_id = "+expID;
 				}
 				else
 				{
 					query = "update rpt_expenditure_dtls expd"+
	    			" set expd.dedctnb_amt = (expd.dedctnb_amt - "+lDedctnAmount+"), "+
	    			" expd.net_amt     = (expd.net_amt + ("+lDedctnAmount+" + "+lDisbAmnt+")), "+
	    			" expd.exp_amt     = (expd.gross_amnt - expd.recovery_amt)"+
	    			" where expd.exp_id = "+expID;
 					
 				}
 				
 				
 				
 				
 				
 				
 				
 			}
 			
 			logger.info("The Query :-> " + query);
 				
 			 i = stmt.executeUpdate(query);
			 

 			
 
 			reason = "Deleted receipt data successfully";
 			flag = true;
 			
 			logger.info("-----------------------deleteVO function of DssReceiptDtlsDAO ends---------------------------");
		}
		catch(Exception e)
		{
			flag = false;
			reason = e.toString();
			logger.error("This is Error: -", e);
		}
		
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", "RptReceiptDtls"); 
		return returnMap;
	}	
	
	
	public HashMap updateVo(RptReceiptDtls lObjRptReceiptDtls)
	{
		HashMap returnMap = new HashMap();
		String reason = "";
		boolean flag = false;
		try
		{
			logger.info("-----------------------updateVO function of DssReceiptDtlsDAO starts---------------------------");
			
			
			
			
			
			 
			Session session = getSessionFactory().openSession(); 
			org.hibernate.Transaction tx = session.beginTransaction();
			session.update(lObjRptReceiptDtls);
			tx.commit();
			
 	 		
 			BigDecimal bgdcRcptNo = new BigDecimal(lObjRptReceiptDtls.getRcptNo().toString());
 			String lStrRcptTypeCode= lObjRptReceiptDtls.getRcptTypeCode().toString();
 			String lStrChallanCatgCode = lObjRptReceiptDtls.getChallanCatgCode().toString();
 			BigDecimal trnReceiptId = new BigDecimal(lObjRptReceiptDtls.getTrnReceiptId().toString());
	
 			Connection lcon =  null;
 			Statement stmt = null;
 			double lGrossAmnt = 0;
 			double lDedA = 0;
 			double lDedB =0 ;
 			double lRecvAmnt = 0;
 			double lNetAmnt = 0;
 			double lExpAmnt = 0;
 			double lDispAmnt = 0;
			long expID =0;
			int count = 0;
 			if(lObjRptReceiptDtls.getExpId() != null && !lObjRptReceiptDtls.getExpId().toString().equals(""))
 			{
 				lObjRptReceiptDtls.toString();
 				
 				BigDecimal bgdcExpId = new BigDecimal(lObjRptReceiptDtls.getExpId().toString());
 				String chDidctnType = lObjRptReceiptDtls.getDedctnType().toString();
 				
 				String query = "select dedAamnt, "+
						       "dedBamnt, "+
						       "(select nvl(exp.gross_amnt,0) "+ 
						          "from rpt_expenditure_dtls exp "+
						         "where exp.exp_id = "+bgdcExpId+") grossAmt, "+ 
						       "(select nvl(exp.recovery_amt,0) "+ 
						          "from rpt_expenditure_dtls exp "+
						         "where exp.exp_id = "+bgdcExpId+") rcryAmt, "+
						       " ( select count(*) counter "+
				                 " from rpt_receipt_dtls rec "+
				                " where rec.dedctn_type = 'A' and rec.exp_id = "+bgdcExpId+" ) co "+
						  "from (select nvl(sum(rec.amount), 0) dedAamnt "+
						          "from rpt_receipt_dtls rec "+
						         "where rec.exp_id = "+bgdcExpId+" and rec.active = 'Y' and "+
						               "rec.dedctn_type = 'A'), "+
						       "(select nvl(sum(rec.amount), 0) dedBamnt "+
						          "from rpt_receipt_dtls rec "+
						        " where rec.exp_id = "+bgdcExpId+" and rec.active = 'Y' and "+
						               "rec.dedctn_type = 'B') ";
						      
						               
				//System.out.println("Query1 in UpdateReceipt() --- "+query);		               
 				 session = getSession();        
 				List rsList = session.createSQLQuery(query).list();
 	 			
 	 			if(rsList.size() >0)
 	 			{
 	 				Iterator it = rsList.iterator();
 	 				while(it.hasNext())
 	 				{
 	 					Object[] tuple = (Object[])it.next();
 	 					if(tuple[0] != null)
 	 					{
 	 						lDedA = Double.parseDouble(tuple[0].toString());
 	 					}
 	 					if(tuple[1] != null)
 	 					{
 	 						lDedB = Double.parseDouble(tuple[1].toString());
 	 					}
 	 					if (tuple[2] != null )
 	 					{
 	 						lGrossAmnt = Double.parseDouble(tuple[2].toString());
 	 					}
 	 					if(tuple[3] != null)
 	 					 {
 	 						 lRecvAmnt =Double.parseDouble(tuple[3].toString());
 	 					 }
 	 					if(tuple[4] != null)
	 					 {
 	 						count = Integer.parseInt(tuple[4].toString());
	 					 }
 	 				
 	 				}
 	 			}
 		
 				lNetAmnt = lGrossAmnt - (lDedA + lDedB + lRecvAmnt + lDispAmnt);
 				if(count > 0)
 				{
 					lExpAmnt = lNetAmnt + lDedA;
 				}
 				else
 				{
 					lExpAmnt = (lGrossAmnt - lRecvAmnt);
 				}
 				
 			BigDecimal bdgdcNetAmnt = new BigDecimal(lNetAmnt);
 			BigDecimal bgdcExpAmnt = new BigDecimal(lExpAmnt);
 		//	BigDecimal bgdcGrossAmnt = new BigDecimal(lGrossAmnt);
 		//	BigDecimal bgdcRecAmnt = new BigDecimal(lRecvAmnt);
 			BigDecimal bgdcDedAamnt = new BigDecimal(lDedA);
 			BigDecimal bgdcDedBamnt = new BigDecimal(lDedB);
 			//System.out.println(" the values are "+bdgdcNetAmnt.toString() +" "+bgdcExpAmnt.toString()+" "+bgdcDedAamnt.toString()+" "+bgdcDedBamnt.toString());
 			query = "update rpt_expenditure_dtls exp "+
 					" set exp.net_amt ="+bdgdcNetAmnt+", exp.exp_amt ="+bgdcExpAmnt +","+
 					" exp.dedctna_amt = "+bgdcDedAamnt+", exp.dedctnb_amt ="+bgdcDedBamnt+
 					 " where exp.exp_id = "+bgdcExpId;
 			//System.out.println("the query is "+query);
 			 lcon = (Connection)session.connection();
 			 stmt = lcon.createStatement();
 			 
 			 
 			 int updatedRow  = stmt.executeUpdate(query);
 		    
 			 //System.out.println(" Updated rows are "+updatedRow);
 			reason = "ExpEdp Data Updated"	;	
 			flag = true;
		}
 		else
 		{
 			flag = true;
 			reason = "Updated successfully";
 		}		
		}
		catch(Exception e)
		{
			flag = false;
			reason = e.toString();
			logger.error("This is Error: -", e);
		}
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", "RptReceiptDtls"); 
		
		logger.info("-----------------------updateVO function of DssReceiptDtlsDAO ends---------------------------");
		return returnMap;
	}

	public RptReceiptDtls getData(BigDecimal RCPT_NO , String rcpt_type_code ,String challan_catg_code,BigDecimal trnReceiptId)
	{
		RptReceiptDtls lObjRptReceiptDtls = null;
		Object lObjExpId = null;
		Iterator literator = null;
		List list = null;
		SQLQuery query = null;
		
		try
		{
			logger.info("-------------------------getData function of DssRptReceiptDtlsDAOImpl starts-----------------------");
			
			Session session = getSession();
			String lStrquery = "select rcpt_id "+
	 			               "from rpt_receipt_dtls "+
	 			               "where RCPT_NO = "+RCPT_NO +" and rcpt_type_code = '"+rcpt_type_code+"' and " +
			               		"challan_catg_code = '"+challan_catg_code+"'" +
			               				"and trn_receipt_id =" +trnReceiptId;
			
			query = session.createSQLQuery(lStrquery);
			list = query.list();
			literator = list.iterator();
			lObjExpId = literator.next();
			lObjRptReceiptDtls = read(new BigDecimal(lObjExpId.toString()));
			
			logger.info("-------------------------getData function of DssRptReceiptDtlsDAOImpl ends-----------------------");
			
			
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
		}
		
		return lObjRptReceiptDtls;
		
	}

	
	public boolean deleteData(BigDecimal expId)
	{
		boolean flag = false;
		SQLQuery sqlquery = null;
		String query = null;
		Session session = null;
		
		try
		{
			logger.info("---------------------------deleteData Function of DssReceiptDtls starts--------------------");
			
			session = getSession();
			query = "delete 	from rpt_receipt_dtls where exp_id = "+ expId;
			sqlquery = session.createSQLQuery(query);
			sqlquery.executeUpdate();
			logger.info("---------------------------deleteData Function of DssReceiptDtls ends--------------------");
		}
		catch(Exception e)
		{
			flag = false;
			logger.error("This is Error: -", e);
		}
		return flag;
	}
	
	public BigDecimal getFinYrId()
	{
		BigDecimal lFinYrIdReturn = null;
		Session session =  null;
		SQLQuery query = null;
		List list = null;
		Iterator literator = null;
		
		try
		{
			logger.info("-------------------------getFinYrId Function of DssRptReceiptDtlsDAOImpl starts--------------------");
			
			String lStrquery = "select mst.fin_year_id from sgvc_fin_year_mst mst "+
						"where sysdate between mst.from_date and mst.to_date ";
				
			logger.info("The query is :- " + lStrquery);
			lFinYrIdReturn = new BigDecimal(0);
			session = getSession();
			query = session.createSQLQuery(lStrquery);
			list = query.list();
			literator = list.iterator();
			if(literator.hasNext())
			{
				Object obj = (Object) literator.next();
				lFinYrIdReturn = new BigDecimal(obj.toString());
			}
				
			logger.info("-------------------------getFinYrId Function of DssRptReceiptDtlsDAOImpl ends--------------------");
			return lFinYrIdReturn;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			return null;
		}
	}
	
}

	




