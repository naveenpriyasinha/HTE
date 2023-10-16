package com.tcs.sgv.dss.dao;



import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;

public class DssRptExpenditureDtlsDAOImpl extends GenericDaoHibernateImpl<RptExpenditureDtls,BigDecimal> implements DssDataServiceDAO
{
	
	public DssRptExpenditureDtlsDAOImpl(Class<RptExpenditureDtls> type, SessionFactory sessionFac)
	{
		super(type);
		setSessionFactory(sessionFac);
	}
	
	Log logger = LogFactory.getLog(getClass());
	String voname = "-"; 
	public HashMap insertVO(RptExpenditureDtls lObjRptExpenditureDtls)
	{
		HashMap returnMap = new HashMap();
		boolean flag = false;
		String reason = "parsed succesfully";
		try
		{
			logger.info("-----------------------insertVO function of DssRptExpenditureDtlsDAOImpl starts--------------------");

			if(lObjRptExpenditureDtls.getFinYrId().equals(new BigDecimal(0)))
				lObjRptExpenditureDtls.setFinYrId(getFinYrId());
			
			lObjRptExpenditureDtls.toString();
			
			Session session = getSession();
			create(lObjRptExpenditureDtls);
			flag = true;
			logger.info("-----------------------insertVO function of DssRptExpenditureDtlsDAOImpl ends--------------------");
				
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
	
	
	public HashMap updateVo(RptExpenditureDtls lObjRptExpenditureDtls)
 	{
 		
 		HashMap returnMap = new HashMap();
 		boolean flag = false;
 		String reason = null;
 		
 		try
		{
 			lObjRptExpenditureDtls.toString();
 			
 			logger.info("------------------------------------updateVO function of DssRptExpenditureDtlsDAOImpl starts------------------");
 			
	 		Session session = getSession();
	 		
			session.update(lObjRptExpenditureDtls);
		
			flag = true;
			reason = "Expenditure data updated succesfully";
					
		}
 		catch(Exception e)
 		{
 			flag = false;
 			reason = e.toString();
 			logger.error("This is Error: -", e);
 		}
 		returnMap.put("flag",flag);
		returnMap.put("Status", reason);
		returnMap.put("voName", "RptExpenditureDtls"); 
		logger.info("------------------------------------updateVO function of DssRptExpenditureDtlsDAOImpl ends------------------");
		return returnMap;
 		
 		
 	}

 	
 	
	public HashMap deleteVo(BigDecimal Exp_no,String Exp_Type_code)
 	{
 		HashMap returnMap = new HashMap();
 		boolean flag = false;
 		Connection lcon = null;
 		Statement stmt = null;
 		String reason;
 		try
		{
 			logger.info("---------------deleteVO function of DssRptExpendiotureDtlsDAOImpl starts------------------");
 		
 				Session session = getSession();
 				
	 			String query = "update rpt_expenditure_dtls t "+
	 						   "set t.active = 'N' "+
	 						   "where t.exp_id in (select exp_id "+
	 			               "from rpt_expenditure_dtls "+
	 			               "where exp_no = "+Exp_no+" and exp_type_code = '"+Exp_Type_code+"')";
	 			               
	 			lcon = (Connection)session.connection();
	 			stmt = lcon.createStatement();
	 			 
	 		    logger.info("The query for updating the expEdp deletin is --> " + query);
	 			int i = stmt.executeUpdate(query);
	 			
	 			query = "update rpt_exp_edp_dtls t "+
				   "set t.active = 'N' "+
				   "where t.exp_id in (select exp_id "+
	               "from rpt_expenditure_dtls "+
	               "where exp_no = "+Exp_no+" and exp_type_code = '"+Exp_Type_code+"')";
	               
	 			lcon = (Connection)session.connection();
	 			stmt = lcon.createStatement();
	 			 
	 			logger.info("The query for updating the expEdp deletin is --> " + query);
	 			i = stmt.executeUpdate(query);
	 			
	 			query = "update rpt_receipt_dtls rec "+
				 			" set rec.active= 'N' " +
				 			" where rec.exp_id = (select exp_id "+
	               "from rpt_expenditure_dtls "+
	               "where exp_no = "+Exp_no+" and exp_type_code = '"+Exp_Type_code+"')";
	 			
	 			lcon = (Connection)session.connection();
	 			stmt = lcon.createStatement();
	 			 
	 			logger.info("The query for updating the expEdp deletin is --> " + query);
	 			i = stmt.executeUpdate(query);
	 			
	 			flag = true;
	 			reason = "Expenditure data deleted";
		}
 		catch(Exception e)
 		{
 			flag = false;
 			reason = e.toString();
 			logger.error("This is Error: -", e);
 		}
 		
 		returnMap.put("flag",flag);
		returnMap.put("Status", reason);
		returnMap.put("voName", "RptExpenditureDtls");
		logger.info("---------------deleteVO function of DssRptExpendiotureDtlsDAOImpl ends------------------");
 		return returnMap;
 		
 	}
	public RptExpenditureDtls getData(BigDecimal EXP_NO , String EXP_TYPE_CODE)
 	{
		logger.info("-----------------------getData Function of RptExpenditureDTlsDAOImpl starts----------------------");
		
		HashMap amountMap = null;
		Character lCharacter = null;
		RptExpenditureDtls lObjRptExpenditureDtls = new RptExpenditureDtls();
 		Session session = getSession();
 		String lStrquery = " select exp_id ,"+
 						" dept_code , office_code , tsry_code , district_code , ddo_code , bill_grp_code ,"+
 						" cls_exp_code , fund_type_code , bud_type_code , demand_no  , scheme , mjr_hd , "+
 						" sub_mjr_hd , min_hd , sub_hd , dtl_hd , fin_yr_id , exp_dt , gross_amnt , "+
 						" exp_status_code , exp_status_dt , exp_crt_dt , ag_apprvl , dedctna_amt , "+
 						" dedctnb_amt , net_amt , exp_amt , recovery_amt , client_data , active " +
 						" from rpt_expenditure_dtls "+
 						" where exp_no = "+EXP_NO+" and exp_type_code = '"+EXP_TYPE_CODE+"'";
 		
 		try
 		{
	 		SQLQuery query = session.createSQLQuery(lStrquery);
	 		
	 		logger.info("--------- Query :->" + query);
	 		
	 		List list = query.list();
	   		Iterator literator = list.iterator();
	  		
	 		if(literator.hasNext())
	 		{
	 			
	 			Object[] lObj = (Object[])literator.next();
	 	 			
	 			
	 			lObjRptExpenditureDtls.setExpId(new BigDecimal(lObj[0].toString()));
	 			
	 			if(lObj[1] != null)
	 			lObjRptExpenditureDtls.setDeptCode(lObj[1].toString());
	 			
	 			if(lObj[2] != null)
	 			lObjRptExpenditureDtls.setOfficeCode(lObj[2].toString());
	 			
	 			if(lObj[3] != null)
	 			lObjRptExpenditureDtls.setTsryCode(lObj[3].toString());
	 			
	 			if(lObj[4] != null)
	 			lObjRptExpenditureDtls.setDistrictCode(lObj[4].toString());
	 			
	 			if(lObj[5] != null)
	 			lObjRptExpenditureDtls.setDdoCode(lObj[5].toString());
	 			
	 			
	 			lObjRptExpenditureDtls.setExpNo(EXP_NO);
	 			
	 			
	 			lObjRptExpenditureDtls.setExpTypeCode(EXP_TYPE_CODE);
	 			
	 			if(lObj[6] != null)
	 			lObjRptExpenditureDtls.setBillGrpCode(lObj[6].toString());
	 			
	 			if(lObj[7] != null)
	 			lObjRptExpenditureDtls.setClsExpCode(lObj[7].toString());
	 			
	 			if(lObj[8] != null)
	 			lObjRptExpenditureDtls.setFundTypeCode(lObj[8].toString());
	 			if(lObj[9] != null)
	 			lObjRptExpenditureDtls.setBudTypeCode(lObj[9].toString());
	 			if(lObj[10] != null)
	 			lObjRptExpenditureDtls.setDemandNo(lObj[10].toString());
	 			if(lObj[11] != null)
	 			lObjRptExpenditureDtls.setScheme(Integer.parseInt(lObj[11].toString()));
	 			if(lObj[12] != null)
	 			lObjRptExpenditureDtls.setMjrHd(lObj[12].toString());
	 			if(lObj[13] != null)
	 			lObjRptExpenditureDtls.setSubMjrHd(lObj[13].toString());
	 			if(lObj[14] != null)
	 			lObjRptExpenditureDtls.setMinHd(lObj[14].toString());
	 			if(lObj[15] != null)
	 			lObjRptExpenditureDtls.setSubHd(lObj[15].toString());
	 			if(lObj[16] != null)
	 			lObjRptExpenditureDtls.setDtlHd(lObj[16].toString());
	 			if(lObj[17] != null)
	 			lObjRptExpenditureDtls.setFinYrId(new BigDecimal(lObj[17].toString()));
	 			
	 			if(lObj[18] != null)
	 			{
	 				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 				//System.out.println(" the date is ----"+lObj[18].toString());
	 				//System.out.println("the date formted is ---------- +"+sdf.parse(lObj[18].toString()));
	 				lObjRptExpenditureDtls.setExpDt(sdf.parse(lObj[18].toString()));
	 			}
	 				
	 			if(lObj[19] != null)
	 			{
	 				amountMap = new HashMap();
	 				amountMap.put("amount",lObj[19].toString());
	 				lObjRptExpenditureDtls.setGrossAmnt(amountMap);
	 			}
	 			if(lObj[20] != null)
	 			lObjRptExpenditureDtls.setExpStatusCode(lObj[20].toString());
	 			
	 			if(lObj[21] != null)
	 			{
	 				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 				
	 				lObjRptExpenditureDtls.setExpStatusDt(sdf.parse(lObj[21].toString()));
	 			}
	 			
	 			
	 			
	 			
	 			if(lObj[22] != null)
	 			{
	 				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 				lObjRptExpenditureDtls.setExpCrtDt(sdf.parse(lObj[22].toString()));
	 			}
	 			
	 			if(lObj[23] != null)
	 			{
	 				lCharacter = (Character)lObj[23];
	 				lObjRptExpenditureDtls.setAgApprvl(lCharacter.charValue());
	 			}
	 			
	 			if(lObj[24] != null)
	 			{
	 				amountMap = new HashMap();
	 				amountMap.put("amount",lObj[24].toString());
	 				lObjRptExpenditureDtls.setDedctnaAmt(amountMap);
	 			}
	 			
	 			if(lObj[25] != null)
	 			{
	 				amountMap = new HashMap();
	 				amountMap.put("amount",lObj[25].toString());
	   				lObjRptExpenditureDtls.setDedctnbAmt(amountMap);
	 			}
	 			
	 			if(lObj[26] != null)
	 			{
	 				amountMap = new HashMap();
	 				amountMap.put("amount",lObj[26].toString());
	 				lObjRptExpenditureDtls.setNetAmt(amountMap);
	 			}
	 			
	 			if(lObj[27] != null)
	 			{
	 				amountMap = new HashMap();
	 				amountMap.put("amount",lObj[27].toString());
	 				lObjRptExpenditureDtls.setExpAmt(amountMap);
	 			}
	 			
	 			if(lObj[28] != null)
	 			{
	 				amountMap = new HashMap();
	 				amountMap.put("amount",lObj[28].toString());
	 				lObjRptExpenditureDtls.setRecoveryAmt(amountMap);
	 			}
	 			
	 			if(lObj[29] != null)
	 			{
	 				lCharacter = (Character)lObj[29];
	 				lObjRptExpenditureDtls.setClientData(lCharacter.charValue());
	 			}
	 			
	 			
	 			
	 			if(lObj[30] != null)
	 			{
	 				lCharacter = (Character)lObj[30];
	 				lObjRptExpenditureDtls.setActive(lCharacter.charValue());
	 			}
	 		
	 		}
	 		logger.info("-----------------------getData Function of RptExpenditureDTlsDAOImpl ends----------------------");
	 		
 		}
 		catch(Exception e)
 		{
 			logger.error("Hi this is error", e);
 		}
 		
 		return lObjRptExpenditureDtls;
 		
  	}

	
	
	public int getTotalRowsDdctnA(BigDecimal expId)
	{
		int totalRows = 0;
		try
		{
			logger.info("------------------------getTotalRowsDdctnA of DssRptExpenditureDtls starts------------------");
			
			String query = "select count(*) "+
							" from rpt_receipt_dtls tt "+
							" where tt.exp_id = "+expId+" and tt.dedctn_type = 'A'";
					 
			logger.info("Query is :-" + query);
			
			Session session = getSession();
			List lst = session.createSQLQuery(query).list();
			Iterator itr = lst.iterator();
			totalRows = Integer.parseInt((itr.next().toString()));
			
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			
		}
		return totalRows;
	}
	
	public boolean updateAmounts(BigDecimal expId,BigDecimal grossAmount,BigDecimal deductionA,BigDecimal deductionB,BigDecimal netAmount,BigDecimal expAmount,BigDecimal recoveryAmount)
	{
		boolean flag = true;
		try
		{
			logger.info("----------------------updateAmounts function of DSsRptExpenditureDTlsDAOImpl starts----------------");
			Session session = getSession();
			
			String query = "update rpt_expenditure_dtls t "+
							" set t.gross_amnt ="+grossAmount+", "+
							" t.dedctna_amt ="+deductionA+","+
							" t.dedctnb_amt ="+ deductionB+","+
							" t.net_amt ="+ netAmount +","+
							" t.recovery_amt ="+recoveryAmount+","+
							" t.exp_amt ="+expAmount+
							" where t.exp_id ="+expId;
			
			Connection lcon = (Connection)session.connection();
			 Statement stmt = lcon.createStatement();
			 
			 //System.out.println("The query for updating the expEdp deletin is --> " + query);
			int i = stmt.executeUpdate(query);
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
		}
		
		return flag;
	}
	
	public BigDecimal  getExpId(BigDecimal expNo,String expTypeCode)
	{
		try
		{
			logger.info("-----------------getExpId function of DssRptExpenditureDTlsDAOImpl starts--------------------");
			Session session = getSession();
			String lStrquery = "select tt.exp_id "+
									"from rpt_expenditure_dtls tt "+
									"where tt.exp_no = "+expNo+"  and tt.exp_type_code = '"+expTypeCode+"' and tt.active='Y'";
			SQLQuery query = session.createSQLQuery(lStrquery);
			List list = query.list();
			Iterator literator = list.iterator();
			Object lObjExpEdpId = literator.next();
			BigDecimal lbgdcExpId = new BigDecimal(lObjExpEdpId.toString());
				
			return lbgdcExpId;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			return null;
		}
		
	}
	
	public HashMap getAmount(BigDecimal expNo,String expTypeCode)
	{
		try
		{
			logger.info("---------------------getAmount function of DssRptEXpenditureDTlsDAOImpl starts----------------");
			
			//System.out.println("---------------------getAmount function of DssRptEXpenditureDTlsDAOImpl starts----------------");
			
				HashMap lHashReturn = new HashMap();
				Session session = getSession();
				Object lObjExpEdpId = null;
				String grossAmount = null;
				String recoveryAmount = null;
				String dedctnAamount = null;
				String dedctnBamount = null;
				String expId = null;
				
				
				
				
				String lStrquery = " select NVL(tt.gross_amnt,0),"+
									" NVL(tt.recovery_amt,0),"+
									" NVL(tt.dedctna_amt,0),"+
									" NVL(tt.dedctnb_amt,0),"+
									" NVL(tt.exp_id,0) "+
									" from rpt_expenditure_dtls tt"+
									" where tt.exp_id = (select exp_id from rpt_expenditure_dtls"+
									" where exp_no = "+expNo+" and exp_type_code = '"+expTypeCode+"')";
		
				
				logger.info("The query is :-> " + lStrquery );
				
				//System.out.println("The query is :-> " + lStrquery);
				
				SQLQuery query = session.createSQLQuery(lStrquery);
				
				List list = query.list();
				
				Iterator literator = list.iterator();
				
				if(literator.hasNext())
					{
						Object obj[] =(Object[]) literator.next();
						
						 grossAmount = (obj[0].toString());  
						
						recoveryAmount = (obj[1].toString());
						
			  			 dedctnAamount = (obj[2].toString());
			  			 dedctnBamount = (obj[3].toString());
			  			 expId = (obj[4].toString());
			  			
			  			
			  		}
				
				
				lHashReturn.put("grossAmount",grossAmount);
				lHashReturn.put("recoveryAmount",recoveryAmount);
				lHashReturn.put("dedctnaAmount",dedctnAamount);
				lHashReturn.put("dedctnbAmount",dedctnBamount);
				lHashReturn.put("expId",expId);
				
				logger.info("-----------------Returning Back from getAmount Function of DssRptExpenditureDTlsDAOImpl------------------------");
				
				return lHashReturn;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			return null;
		}
	}
	
	public BigDecimal getFinYrId()
	{
		try
		{
			BigDecimal lFinYrIdReturn =null;
			BigDecimal bgdc =  null;
			
			logger.info("-------------------------getFinYrId Function of DssRptExpenditureDtlsDAOImpl starts--------------------");
			
				String lStrquery = "select mst.fin_year_id from sgvc_fin_year_mst mst "+
						"where sysdate between mst.from_date and mst.to_date ";
				
				logger.info("The query is :- " + lStrquery);
				
				
				
				Session session = getSession();
			
				SQLQuery query = session.createSQLQuery(lStrquery);
			
				List list = query.list();
			
				Iterator literator = list.iterator();
			
				if(literator.hasNext())
				{
					Object obj = (Object) literator.next();
					lFinYrIdReturn = new BigDecimal(obj.toString());
				}
				
				return bgdc;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			return null;
		}
	}
	
	

}