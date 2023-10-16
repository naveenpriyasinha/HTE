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
import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;


public class DSSRptExpEdpDtlsDAOImpl extends GenericDaoHibernateImpl<RptExpEdpDtls,BigDecimal> implements DssDataServiceDAO
{
	public DSSRptExpEdpDtlsDAOImpl(Class<RptExpEdpDtls> type, SessionFactory sessionFac)
	{
		super(type);
		setSessionFactory(sessionFac);
	}


	Log logger = LogFactory.getLog(getClass());
	String voname = "-";

	public HashMap insertVO(ArrayList lArrayLstRptExpEdpDtlsVO_DAO)
	{
		int i =0;
		HashMap returnMap = new HashMap();
		boolean flag = false;
		String reason = "parsed successfully";
		try
		{
			for(i =0; i < lArrayLstRptExpEdpDtlsVO_DAO.size();i++ )
			{
				logger.info("-------------------------insertVO functon of DSSRPtExpEdpDtlsDAOImpl Strarts------------------");

				RptExpEdpDtls lObjRptExpEdpDtlsVO = (RptExpEdpDtls)lArrayLstRptExpEdpDtlsVO_DAO.get(i);
				
				lObjRptExpEdpDtlsVO.toString();
				
				create(lObjRptExpEdpDtlsVO);
				flag = true;
			}

		}catch(Exception e)
		{

			logger.error("This is Error: -", e);	
			flag = false;
			reason = e.toString();
			voname = this.toString();
		}
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", voname);
		return returnMap;
	}

	public HashMap deleteVo(BigDecimal bgdcexpId,BigDecimal trnExpEdpId)
	{

		HashMap returnMap = new HashMap();
		boolean flag = false;
		String reason = null;
		double lGrossAmnt = 0 ;
		double lDedA = 0;
		double lDedB =0 ;
		double lRecvAmnt = 0;
		double lNetAmnt = 0;
		double lExpAmnt = 0;
		double lDispAmnt = 0;
		String str = "A";
		double amount = 0.0;
		int EdpType = 2;
		int totalRows = 0;
		Connection lcon =  null;
		Statement stmt = null;
		int i = 0;

		try
		{
			logger.info("-----------------------deleteVO function of DssRptExpEdpDtls Starts-------------------------");


			Session session = getSession();

			String query = "update rpt_exp_edp_dtls edp "+
			" set edp.active = 'N' "+
			" where edp.exp_id ="+bgdcexpId+" and edp.trn_exp_edp_id ='"+trnExpEdpId+"'";

			logger.info(" Query :->" + query);

			lcon = (Connection)session.connection();
			stmt = lcon.createStatement();


			i = stmt.executeUpdate(query);

			query = " select nvl(tt.amount,0) , nvl(tt.edp_type,0) , "+
			" (select count(*) from rpt_receipt_dtls t2 "+
			" where t2.dedctn_type = 'A' and t2.exp_id = "+bgdcexpId+")  "+
			" from rpt_exp_edp_dtls tt where tt.exp_id = "+bgdcexpId+" and tt.trn_exp_edp_id = '"+trnExpEdpId+"'";

			logger.info(" Query :->" + query);

			List resList = session.createSQLQuery(query).list();

			Iterator it = resList.iterator();

			if(it.hasNext())
			{
				Object obj[] =(Object[]) it.next();
				amount = Double.parseDouble(obj[0].toString());  
				//System.out.println("Amount value in delete VO function is --> " + amount);
				EdpType = Integer.parseInt(obj[1].toString());
				//System.out.println("EdpType of Edp (deleteing) is --> " + EdpType);
				totalRows = Integer.parseInt(obj[2].toString());
				//System.out.println("totalRows of integer type is --> " + totalRows);
			}
			else
				logger.info("data is not thre in List ------------------------------");



			if(EdpType==0)
			{
				if(totalRows == 0)
				{
					query = "update rpt_expenditure_dtls expnd "+
					"set expnd.gross_amnt = (expnd.gross_amnt - "+amount+"), "+
					"expnd.net_amt        = (expnd.net_amt - "+amount+"), "+
					"expnd.exp_amt    = (expnd.gross_amnt - expnd.recovery_amt - "+amount+") "+
					"where expnd.exp_id ="+bgdcexpId;

				}
				else
				{
					query = "update rpt_expenditure_dtls expnd "+
					"set expnd.gross_amnt = (expnd.gross_amnt - "+amount+"), "+
					"expnd.net_amt        = (expnd.net_amt - "+amount+"), "+
					"expnd.exp_amt    = (expnd.net_amt + expnd.dedctna_amt - "+amount+") "+
					"where expnd.exp_id = "+bgdcexpId;

				}
			}
			else if(EdpType==1)
			{
				if(totalRows == 0)
				{
					query = "update rpt_expenditure_dtls expnd "+
					"set expnd.recovery_amt = (expnd.recovery_amt-"+amount+"), "+
					"expnd.net_amt        = (expnd.net_amt + "+amount+"), "+
					"expnd.exp_amt    = (expnd.gross_amnt - expnd.recovery_amt - "+amount+") "+
					"where expnd.exp_id = "+bgdcexpId;

				}
				else
				{
					query = "update rpt_expenditure_dtls expnd "+
					"set expnd.recovery_amt = (expnd.recovery_amt-"+amount+"), "+
					"expnd.net_amt        = (expnd.net_amt + "+amount+"), "+
					"expnd.exp_amt    = (expnd.net_amt + expnd.dedctna_amt+ "+amount+") "+
					"where expnd.exp_id = "+bgdcexpId;


				}
			}

			logger.info("The query for updating the Expenditure Amount-->  "+ query);

			i = stmt.executeUpdate(query);

			flag = true;
			reason = "ExpEdp Date Deleted";

			logger.info("-----------------------deleteVO function of DssRptExpEdpDtls ends-------------------------");
		}
		catch(Exception e)
		{
			flag = false;
			reason = e.toString();
			logger.error("This is Error: -", e);
		}
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", "RptExpEdpDtls"); 

		return returnMap;
	}

	public HashMap updateVo(RptExpEdpDtls lObjRptExpEdpDtls)
	{
		HashMap returnMap = new HashMap();
		boolean flag = false;
		String reason = null;

		Connection lCon = null;

		try
		{  
			logger.info("----------------------------updateVO function of DssRptExpEdpDtlsDAOImpl starts---------------");

			lObjRptExpEdpDtls.toString();
			
			Session session = getSessionFactory().openSession(); 
			org.hibernate.Transaction tx = session.beginTransaction();
			session.saveOrUpdate(lObjRptExpEdpDtls);
			tx.commit();

			lObjRptExpEdpDtls.getExpId();
			lObjRptExpEdpDtls.getTrnExpEdpId();

			BigDecimal expID = new BigDecimal(lObjRptExpEdpDtls.getExpId().toString());
			double lGrossAmnt = 0 ;
			double lDedA = 0;
			double lDedB = 0;
			double lRecvAmnt = 0;
			double lNetAmnt = 0;
			double lExpAmnt = 0;
			double lDispAmnt = 0;
			int count=0;
			Connection lcon =  null;
			Statement stmt = null;
			String query = "select gr.groassamnt, re.recoveryamnt, co.counter,  " +
			"(select exp.dedctna_amt"+
			"  from rpt_expenditure_dtls exp "+
			" where exp_id = "+expID +") ,"+
			"(select  exp.dedctnb_amt "+
			"  from rpt_expenditure_dtls exp "+
			" where exp_id = "+expID+") "+
			" from ( select nvl(sum(edp.amount), 0) groassamnt "+
			" from rpt_exp_edp_dtls edp "+
			" where edp.exp_id = "+expID+" and edp.edp_type = 0  and edp.active = 'Y') gr, "+
			" ( select nvl(sum(edp.amount), 0) recoveryamnt "+
			" from rpt_exp_edp_dtls edp "+
			" where edp.exp_id = "+expID+" and edp.edp_type = 1 and edp.active = 'Y') re, " +
			" ( select count(*) counter "+
			" from rpt_receipt_dtls rec "+
			" where rec.dedctn_type = 'A' and rec.exp_id = "+expID+" ) co ";

			//System.out.println("query in udpate expedp-->"+query);
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
						lGrossAmnt = Double.parseDouble(tuple[0].toString());
					}
					if(tuple[1] != null)
					{
						lRecvAmnt = Double.parseDouble(tuple[1].toString());
					}
					if(tuple[2] != null)
					{
						count = Integer.parseInt(tuple[2].toString());
					}

					if(tuple[3] != null)
					{
						lDedA = Double.parseDouble(tuple[3].toString());
					}
					else
					{
						lDedA = 0;
					}
					if(tuple[4] != null)
					{
						lDedB =Double.parseDouble(tuple[4].toString());
					}
					else
					{
						lDedB =0;
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
			BigDecimal bgdcGrossAmnt = new BigDecimal(lGrossAmnt);
			BigDecimal bgdcRecAmnt = new BigDecimal(lRecvAmnt);

			//System.out.println(" the values are "+bdgdcNetAmnt.toString() +" "+bgdcExpAmnt.toString()+" "+bgdcGrossAmnt.toString()+" "+bgdcRecAmnt.toString());
			query = "update rpt_expenditure_dtls exp "+
			" set exp.net_amt ="+bdgdcNetAmnt+", exp.exp_amt ="+bgdcExpAmnt +","+
			" exp.gross_amnt ="+bgdcGrossAmnt+", exp.recovery_amt = "+bgdcRecAmnt+
			" where exp.exp_id = "+expID;
			//System.out.println("query is "+query);

			lcon = (Connection)session.connection();
			stmt = lcon.createStatement();


			int updatedRow  = stmt.executeUpdate(query);

			//System.out.println(" Updated rows are "+updatedRow);
			reason = "ExpEdp Data Updated"	;	
			flag = true;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			flag = false;
			reason = e.toString();
		}
		returnMap.put("flag",flag);
		returnMap.put("status", reason);
		returnMap.put("voName", "RptExpEdpDtls");

		logger.info("----------------------------updateVO function of DssRptExpEdpDtlsDAOImpl ends---------------");

		return returnMap;


	}

	public RptExpEdpDtls getData(BigDecimal EXP_NO , String EXP_TYPE_CODE ,BigDecimal trnExpEdpId)
	{
		RptExpEdpDtls lObjRptExpEdpDtls = null;
		Session session = null;
		String lStrquery = null;
		SQLQuery query = null;
		List list = null;
		Iterator literator = null;
		Object lObjExpEdpId =  null;

		try
		{
			logger.info("-----------------------------getData function of DssRptExpEdpDtlsDAOImpl starts------------------");

			session = getSession();
			lStrquery = "select ed.exp_edp_id "+
			" from rpt_exp_edp_dtls ed "+
			" where ed.exp_id = "+
			" (select exp.exp_id "+
			" from rpt_expenditure_dtls exp "+
			" where exp.exp_no ="+EXP_NO + " and exp.exp_type_code = '"+EXP_TYPE_CODE+"' )" +
			" and ed.trn_exp_edp_id ="+trnExpEdpId;

			query = session.createSQLQuery(lStrquery);
			list = query.list();
			literator = list.iterator();
			lObjExpEdpId = literator.next();
			lObjRptExpEdpDtls = read((new BigDecimal(lObjExpEdpId.toString())));

			logger.info("-----------------------------getData function of DssRptExpEdpDtlsDAOImpl ends------------------");

			return lObjRptExpEdpDtls;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			return lObjRptExpEdpDtls;
		}
	}


	public boolean deleteData(BigDecimal expId)
	{
		boolean flag = false;
		Session session = null;
		String query =  null;
		SQLQuery sqlquery = null;

		try
		{
			logger.info("------------------------------deleteData function of DssRptExpEdpDTlsDAOImpl starts-------------------");

			session = getSession();
			query = "delete 	from rpt_exp_edp_dtls where exp_id = "+ expId;
			sqlquery = session.createSQLQuery(query);

			sqlquery.executeUpdate();

			logger.info("------------------------------deleteData function of DssRptExpEdpDTlsDAOImpl ends-------------------");
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			flag = false;
		}

		return flag;
	}

	public HashMap getGrossRcvryAmounts(BigDecimal expId)
	{
		HashMap lHashReturn = null;
		Session session = null;
		String query = null;
		List resList = null;
		Iterator it = null;

		try
		{

			logger.info("------------------------getGrossRcvryAmounts function of DssRptExpEdpAmounts starts---------------------");

			lHashReturn = new HashMap();
			session = getSession();
			query = "select * from "+ 
			"(select nvl(sum(amount),0) from rpt_exp_edp_dtls  where edp_type = '0' and exp_id = "+expId+" and active = 'Y'), "+
			"(select nvl(sum(amount),0) from rpt_exp_edp_dtls  where edp_type = '1' and exp_id = "+expId+" and active = 'Y') ";

			
			logger.info("------------------------Query is :-> "+ query);
			
			
			
			resList = session.createSQLQuery(query).list();
			it = resList.iterator();
			if(it.hasNext())
			{
				Object obj[] =(Object[]) it.next();
				
				logger.info("The gross Amount is :- " +obj[0].toString() );
				logger.info("The Recovery Amount is :- " +obj[1].toString() );
				
				lHashReturn.put("grossAmount", obj[0].toString());
				lHashReturn.put("recoveryAmount", obj[1].toString());
			}
			logger.info("------------------------getGrossRcvryAmounts function of DssRptExpEdpDtlsDAOImpl ends----------------------------");
			return lHashReturn;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			return null;
		}
	}


}
