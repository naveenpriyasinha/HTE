package com.tcs.sgv.exprcpt.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.exprcpt.valueobject.BudHdAmtVO;
import com.tcs.sgv.exprcpt.valueobject.MstListPayRcpt;

 
/**
*Class Name : ReportDAOImpl
*Description Class that implements ReportDAO
*Date of Creation  26 Jun 07
*Author Jignesh Sakhiya
*/

public class ReportDAOImpl implements ReportDAO 
{	
	private static Log logger = LogFactory.getLog(ReportDAOImpl.class);	
	public static String LIST_OF_PAY = "P"; 
	public static String LIST_OF_RCPT = "R";
	public static String OP_NEG="Negative";
	public static String OP_POS="Positive";	
		
	private SessionFactory sessionFactory;
	
	public ReportDAOImpl(SessionFactory sessionFactory) 
    {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Returns array of summation of payment group by budget head structure.
	 * 
	 * @param trsyLocId Location Id of a tresury office
	 * @param fromDate From date
	 * @param toDate To date
	 * @return ArrayList
	 */
	public List getPayByBudHds(String sTsryLocId, Date fromDate, Date toDate, boolean subTrsy,long lang_id) 
	{		
		List<BudHdAmtVO> dataList = new ArrayList<BudHdAmtVO>();
		try 
		{
			Session hibSession = this.sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT sum(exp_amt) as amount, (lpad(NVL(mjr_hd,0),4,'0')||lpad(NVL(sub_mjr_hd,0), 2, '0')||lpad(NVL(min_hd,0), 3, '0')||lpad(NVL(sub_hd,0), 2, '0')) as formula from rpt_expenditure_dtls where ");
			sql.append(" exp_status_code = 'DTL_PSTNG_DONE' and active='Y' and ");
			if(!subTrsy) sql.append("tsry_code = ? ");
			 else 
		    sql.append(" tsry_code in (select location_code from cmn_location_mst  where (location_code=? and lang_id = ?) or parent_loc_id in (select loc_id from cmn_location_mst  where location_code=? and lang_id =?))");
				
			sql.append(" and exp_dt between ? and ?   group by mjr_hd, sub_mjr_hd, min_hd, sub_hd  ");

			//System.out.println("SQL Query : "+sql.toString());
			logger.debug("SQL Query : " + sql.toString());
			
			SQLQuery oSql = hibSession.createSQLQuery(sql.toString());
			
			if(subTrsy)
			{
				oSql.setString(0, sTsryLocId);
				oSql.setLong(1,lang_id );
				oSql.setString(2, sTsryLocId);
				oSql.setLong(3,lang_id );
				if(fromDate !=null && toDate !=null)
				{
					oSql.setDate(4,fromDate);
					oSql.setDate(5,toDate);
				}
				else
				{
					oSql.setDate(4,new Date());
					oSql.setDate(5,new Date());
				}
			}
			else
			{
				oSql.setString(0, sTsryLocId);
				if(fromDate !=null && toDate !=null)
				{
					oSql.setDate(1,fromDate);
					oSql.setDate(2,toDate);
				}
				else
				{
					oSql.setDate(1,new Date());
					oSql.setDate(2,new Date());
				}	
			}
				
			List resList =oSql.list();
				
			if(resList!=null && resList.size()>0) 
			{				
				logger.debug("Query Result Size : " + resList.size());
				dataList = new ArrayList<BudHdAmtVO>();
				for (int i=0; i<resList.size(); i++) 
				{
					Object[] tuple = (Object[]) resList.get(i);
					if(tuple[0]!=null && tuple[1]!=null) 
					{
						BudHdAmtVO VO = new BudHdAmtVO();
						VO.setAmount(new BigDecimal(tuple[0].toString()));
						VO.setFormula(tuple[1].toString());
						dataList.add(VO);
					}
				}
			 } else {
				logger.debug("Query Result Size : 0");
			}			
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error("Exception occured #\n"+ex);
		}
		return dataList;
	}
	
	/**
	 * Returns array of list of payment/recipt meta data.
	 * 
	 * @return ArrayList
	 */
	public List getRptMetaData(String sType) 
	{
		List<MstListPayRcpt> dataList = new ArrayList<MstListPayRcpt>();
		try 
		{
			Session hibSession = this.sessionFactory.getCurrentSession();
			 Query oQuery=  hibSession.createQuery("select text, formula, isFormula," +
                    " belongsTo, total,id from MstListPayRcpt " +
                    "pr where pr.type=? order by pr.srNo");

             oQuery.setString(0,sType);
			 List resList =oQuery.list();

			 for (int i=0; i < resList.size(); i++) 
			 {
				Object[] tuple = (Object[])resList.get(i);
				MstListPayRcpt VO =  new MstListPayRcpt();
				VO.setText((String)tuple[0]);
				VO.setFormula((String)tuple[1]);
				VO.setIsFormula((Character)(tuple[2]==null?' ':tuple[2]) );
				VO.setBelongsTo((String)tuple[3]);
				VO.setTotal((Character)tuple[4]);
				VO.setId((BigDecimal)tuple[5]);
				//if(VO.getText()!=null) VO.setText(VO.getText().replace(" ","&nbsp;"));
				dataList.add(VO);
			}
		} 
		catch(Exception ex) 
		{
			ex.printStackTrace();
			logger.error("Exception occured #\n"+ex);
		}		
		return dataList;
	}
	
     /**
     * @param fromDate
     * @param toDate
     * @param tsryLocId
     * @param fromMajorHead
     * @param ToMajorHead
     * @param lStrLangId
     * @return String
     */

	public List getRcptByBudHd(String sTsryLocId, Date fromDate, Date toDate, boolean subTrsy,String fromMjrHd, String toMjrHd, String operator,long langId) 
	   {
		   List<BudHdAmtVO> dataList = new ArrayList<BudHdAmtVO>();
		   try 
		   {
			Session hibSession = this.sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer();
			sql.append("select sum(amount) as amount, (lpad(NVL(mjr_hd,0),4,'0')||lpad(NVL(sub_mjr_hd,0), 2, '0')||lpad(NVL(min_hd,0), 3, '0')||lpad(NVL(sub_hd,0), 2, '0')) as formula  from rpt_receipt_dtls where ");
			sql.append(" rcpt_status_code = 'Approved' and active='Y' and ");
			
			if(!subTrsy)  
				sql.append(" tsry_code = ? ");
			 else 
				sql.append(" tsry_code in (select location_code from cmn_location_mst  where (location_code=? and lang_id = ?) or parent_loc_id in (select loc_id from cmn_location_mst  where location_code=? and lang_id =?))");

			sql.append(" and revenue_dt between ? and ? ");
			sql.append(" and ((rcpt_type_code = 'Challan' and Dedctn_type = 'O') or (rcpt_type_code = 'Bill'  and Dedctn_type = 'A')) ");
			sql.append(" and mjr_hd between ? and ? ");
			sql.append(" group by mjr_hd, sub_mjr_hd, min_hd, sub_hd ");

			
			//System.out.println("SQL Query : "+sql.toString());
			logger.debug("SQL Query : " + sql.toString());
			
			SQLQuery oSql = hibSession.createSQLQuery(sql.toString());
			
			if(subTrsy)
			{
				oSql.setString(0, sTsryLocId);
				oSql.setLong(1,langId );
				oSql.setString(2, sTsryLocId);
				oSql.setLong(3,langId );
				if(fromDate !=null && toDate !=null)
				{
					oSql.setDate(4,fromDate);
					oSql.setDate(5,toDate);
				}
				else
				{
					oSql.setDate(4,new Date());
					oSql.setDate(5,new Date());
				}
				oSql.setString(6,fromMjrHd);
				oSql.setString(7,toMjrHd);
			}
			else
			{
				oSql.setString(0, sTsryLocId);
				if(fromDate !=null && toDate !=null)
				{
					oSql.setDate(1,fromDate);
					oSql.setDate(2,toDate);
				}
				else
				{
					oSql.setDate(1,new Date());
					oSql.setDate(2,new Date());
				}
					oSql.setString(3,fromMjrHd);
					oSql.setString(4,toMjrHd);
			}
			
			List resList = oSql.list();
			if(resList!=null && resList.size()>0) 
			{				
				logger.debug("Query Result Size : " + resList.size());
				dataList = new ArrayList<BudHdAmtVO>();
				BigDecimal sign = new BigDecimal(1);
				if (operator!=null && operator.equalsIgnoreCase(ReportDAOImpl.OP_NEG)) sign = new BigDecimal(-1);
				for (int i=0; i<resList.size(); i++) 
				{
					Object[] tuple = (Object[]) resList.get(i);
					if(tuple[0]!=null && tuple[1]!=null) 
					{
						BudHdAmtVO VO = new BudHdAmtVO();
						BigDecimal finalAmount =new BigDecimal(tuple[0].toString());
						VO.setAmount(finalAmount.multiply(sign));
						VO.setFormula(tuple[1].toString());
						dataList.add(VO);
					}
				}
			 } 
			else 
				logger.debug("Query Result Size : 0");
		} catch(Exception ex) 
		{
			ex.printStackTrace();
			logger.error("Exception occured #\n"+ex);
		}
		return dataList;
	}

      /**
     * @param fromDate
     * @param toDate
     * @param tsryLocId
     * @param fromMajorHead
     * @param ToMajorHead
     * @param lStrLangId
     * @return List
     */
     public List getReceiptByBudHds(String tsryLocId, Date fromDate, Date toDate, boolean subTrsy,long langId) 
     {		
       List<BudHdAmtVO> dataList = new ArrayList<BudHdAmtVO>();
       try 
       {
           Session hibSession = this.sessionFactory.getCurrentSession();
           StringBuffer sql = new StringBuffer();
           sql.append("select sum(Amount) as amount, (lpad(NVL(mjr_hd,0),4,'0')||lpad(NVL(sub_mjr_hd,0), 2, '0')||lpad(NVL(min_hd,0), 3, '0')||lpad(NVL(sub_hd,0), 2, '0')) as formula from rpt_receipt_dtls where ");
           sql.append(" rcpt_status_code = 'Approved' and active='Y' and ");
           if(!subTrsy) 
               sql.append("tsry_code =:LocationId ");
           else 
               sql.append(" tsry_code in (select location_code from cmn_location_mst  where (location_code=:LocationId and lang_id = :LangId) or parent_loc_id in (select loc_id from cmn_location_mst  where location_code=:LocationId and lang_id =:LangId))");

           sql.append(" and ((rcpt_type_code = 'Challan' and Dedctn_type = 'O') or (rcpt_type_code = 'Bill' and Dedctn_type = 'A')) ");

           if(fromDate!=null && toDate!=null) {
               String fDate = new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
               String tDate = new SimpleDateFormat("yyyy-MM-dd").format(toDate);
               sql.append(" and revenue_dt between to_date('"+fDate+"','yyyy-mm-dd') and to_date('"+tDate+"','yyyy-mm-dd')");
           }
           sql.append(" group by mjr_hd, sub_mjr_hd, min_hd, sub_hd  ");

           //System.out.println("SQL Query : "+sql.toString());
           logger.debug("SQL Query : " + sql.toString());

            SQLQuery oSql = hibSession.createSQLQuery(sql.toString());
            
            oSql.setString("LocationId",tsryLocId );
            oSql.setLong("LangId",langId );
            
            List resList = oSql.list();
            if(resList!=null && resList.size()>0) 
            {				
               logger.debug("Query Result Size : " + resList.size());

               for (int i=0; i<resList.size(); i++) {
                   Object[] tuple = (Object[]) resList.get(i);
                   if(tuple[0]!=null && tuple[1]!=null) {
                       BudHdAmtVO VO = new BudHdAmtVO();
                       BigDecimal finalAmount =new BigDecimal(tuple[0].toString());
                       VO.setAmount(finalAmount);
                       VO.setFormula(tuple[1].toString());
                       dataList.add(VO);
                   }
               }
            } 
            else 
            {
               logger.debug("Query Result Size : 0");
            }			
       } 
       catch(Exception ex) 
       {
           ex.printStackTrace();
           logger.error("Exception occured #\n"+ex);
       }
       return dataList;
   }
	
  /**
   * @param fromDate
   * @param toDate
   * @param tsryLocId
   * @param fromMajorHead
   * @param ToMajorHead
   * @param lStrLangId
   * @return List
   */
  public List getPaymentAsDisbursment(String tsryLocId, Date fromDate, Date toDate, boolean subTrsy,long lang_id) 
    {		
		List<BudHdAmtVO> dataList = new ArrayList<BudHdAmtVO>();
		try 
        {
			Session hibSession = this.sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer();
			sql.append("select sum(DISBURSEMENT_AMT) as amount, (lpad(NVL(mjr_hd,0),4,'0')||lpad(NVL(sub_mjr_hd,0), 2, '0')||lpad(NVL(min_hd,0), 3, '0')||lpad(NVL(sub_hd,0), 2, '0')) as formula from rpt_receipt_dtls where ");
			sql.append(" rcpt_status_code = 'Approved' and active='Y' and ");
			if(!subTrsy) 
            {
				sql.append("tsry_code in (-1,'"+tsryLocId+"') ");
			} 
            else 
            {
				sql.append(" tsry_code in (select location_code from cmn_location_mst  where (location_code='"+tsryLocId+"' and lang_id = " + lang_id  +") or parent_loc_id in (select loc_id from cmn_location_mst  where location_code='"+tsryLocId+"' and lang_id ="+lang_id+"))");
			}
			
			if(fromDate!=null && toDate!=null) 
            {
				String fDate = new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
				String tDate = new SimpleDateFormat("yyyy-MM-dd").format(toDate);
				sql.append(" and revenue_dt between to_date('"+fDate+"','yyyy-mm-dd') and to_date('"+tDate+"','yyyy-mm-dd')");
			}
			sql.append(" group by mjr_hd, sub_mjr_hd, min_hd, sub_hd  ");

			//System.out.println("SQL Query : "+sql.toString());
			logger.debug("SQL Query : " + sql.toString());
			
			List resList = hibSession.createSQLQuery(sql.toString()).list();
			
			if(resList!=null && resList.size()>0) 
            {				
				logger.debug("Query Result Size : " + resList.size());
				
				for (int i=0; i<resList.size(); i++) 
                {
					Object[] tuple = (Object[]) resList.get(i);
					if(tuple[0]!=null && tuple[1]!=null) 
                    {
						BudHdAmtVO VO = new BudHdAmtVO();
						BigDecimal finalAmount =new BigDecimal(tuple[0].toString());
						VO.setAmount(finalAmount);
						VO.setFormula(tuple[1].toString());
						dataList.add(VO);
					}
				}
			}
            else 
            {
				logger.debug("Query Result Size : 0");
			}			
		}catch(Exception ex) 
        {
			ex.printStackTrace();
			logger.error("Exception occured #\n"+ex);
		}
		return dataList;
	}
      /**
     * @param fromDate
     * @param toDate
     * @param tsryLocId
     * @param fromMajorHead
     * @param ToMajorHead
     * @param lStrLangId
     * @return String
     */
	public String getMajHdStatusPayRpt(Date fromDate, Date toDate, String tsryLocId, String subtsryLocId,String[] majorHead, String type, String lStrLangId, Long sLocId)
	{

	    StringBuffer oSb = new StringBuffer();
	    try
	    {
	        Session hibSession = this.sessionFactory.getCurrentSession();
	        StringBuffer sql = new StringBuffer();
	        BigDecimal oTotal = new BigDecimal(0);
            Query sqlQuery = null;
	        sql.append("select re.mjr_hd ,max(sm.budmjrhd_desc_long),count(exp_no),sum(re.net_amt) from rpt_expenditure_dtls re , sgva_budmjrhd_mst sm");
	        sql.append(" where re.mjr_hd = sm.budmjrhd_code and sm.lang_id = ? and sm.fin_yr_id = ? and sm.demand_code = re.demand_no");
	        sql.append(" and re.tsry_code = ? and re.exp_type_code = ? and re.active = 'Y'");
	        if(fromDate!=null && toDate!=null) 
	        {
	            String fDate = new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
	            String tDate = new SimpleDateFormat("yyyy-MM-dd").format(toDate);
	            sql.append(" and re.exp_dt between to_date('"+fDate+"','yyyy-mm-dd') and to_date('"+tDate+"','yyyy-mm-dd')");

	        }
	        sql.append(" and re.mjr_hd in ( :MList ) group by re.mjr_hd ");
	        //System.out.println("Sql query is:" +sql.toString());

	        sqlQuery = hibSession.createSQLQuery(sql.toString());
	        sqlQuery.setString(0,lStrLangId);
	        sqlQuery.setInteger(1,21 );
	        sqlQuery.setString(2,tsryLocId);
	        sqlQuery.setString(3,DBConstants.RPT_RCPT_TYPE_BILL);
	        sqlQuery.setParameterList("MList",majorHead);

	        List resList = sqlQuery.list();

	        StringBuffer oSbLine = new StringBuffer();

	        for(int i=0;i<80;i++)oSbLine.append('-');
	        oSbLine.append("\n");
	        oSb.append("\t\t\t\t"  +"MajorHead Wise Payment Report" + "\n");
	        oSb.append(" From Date :" + new SimpleDateFormat("dd/MM/yyyy").format(fromDate));
	        oSb.append(" To Date :" + new SimpleDateFormat("dd/MM/yyyy").format(toDate));
	        oSb.append("\n");
	        oSb.append(oSbLine.toString());
            oSb.append(String.format("%-10s","Major Head"));
            oSb.append(String.format("%-40s","        Description "));
            oSb.append(String.format("%-9s","Vouchers "));
            oSb.append(String.format("%-20s","     Total Amt"));
            oSb.append("\n");
            oSb.append(oSbLine.toString());
            
	        if (resList != null) 
	        {
	            Iterator it = resList.iterator();
	            int lCount = 1;
	            while(it.hasNext())
	            {
	                Object[] tuple = (Object[])it.next();
	                oSb.append(String.format("%-9s",tuple[0].toString()) + " ");
	                oSb.append(String.format("%-39s",tuple[1].toString()) + " ");
	                oSb.append(String.format("%7s",tuple[2].toString()) + " ");
	                oSb.append(String.format("%21.2f",tuple[3]) + " ");
                    oTotal = oTotal.add((BigDecimal)tuple[3]); 
                    oSb.append("\r\n");
	                lCount++;
	            }	
	        }
            oSb.append(oSbLine.toString());
            String sFooter =  "Total Amount " + String.format("%22.2f", oTotal);
            oSb.append(String.format("%80s",sFooter+"\r\n"));
            oSb.append(oSbLine.toString());
	    }
	    catch(Exception ex)
	    {
	        ex.printStackTrace();
	        logger.error("Exception occured #\n"+ex);
	    }
	    return oSb.toString();
	}
	
    /**
     * @param fromDate
     * @param toDate
     * @param tsryLocId
     * @param fromMajorHead
     * @param ToMajorHead
     * @param lStrLangId
     * @return String
     */
	public String getMajHdStatusRcptRpt(Date fromDate, Date toDate, String tsryLocId, String subtsryLocId,String[] majorHead, String type, String lStrLangId, Long sLocId)
	{
	    StringBuffer oSb = new StringBuffer();
	    try
	    {
	        Session hibSession = this.sessionFactory.getCurrentSession();
	        StringBuffer sql = new StringBuffer();
            BigDecimal oTotal = new BigDecimal(0);
            Query sqlQuery = null;
            
	        sql.append("select r.mjr_hd, max(sm.budmjrhd_desc_long), count(r.rcpt_no), sum(r.amount) from rpt_receipt_dtls r, sgva_budmjrhd_mst sm");
	        sql.append(" where r.mjr_hd=sm.budmjrhd_code and sm.lang_id = ? and sm.fin_yr_id = 21 ");
	        sql.append(" and r.tsry_code= ? and r.rcpt_type_code = ? and r.active = 'Y'");
	        sql.append(" and r.revenue_dt between ? and ? ");
	        sql.append(" and r.mjr_hd in ( :MHList ) ");
	        sql.append(" group by r.mjr_hd ");

	        sqlQuery = hibSession.createSQLQuery(sql.toString());
	        sqlQuery.setString(0,lStrLangId );
	        sqlQuery.setString(1,tsryLocId );
	        sqlQuery.setString(2,DBConstants.RPT_RCPT_TYPE_CHALLAN );
	        sqlQuery.setDate(3,fromDate);
	        sqlQuery.setDate(4,toDate);
	        sqlQuery.setParameterList("MHList",majorHead);
	        sqlQuery.scroll(ScrollMode.FORWARD_ONLY);

	        List resList = sqlQuery.list();
	        StringBuffer oSbLine = new StringBuffer();

            
            for(int i=0;i<80;i++)oSbLine.append('-'); 
	        
	        if (resList != null) 
	        {
	             
	            oSbLine.append("\n");
	            oSb.append(oSbLine.toString());
	            oSb.append("\t\t\t\t" +"MajorHead Wise Receipt Report" + "\n");
                oSb.append(" From Date :" + new SimpleDateFormat("dd/MM/yyyy").format(fromDate));
                oSb.append(" To Date :" + new SimpleDateFormat("dd/MM/yyyy").format(toDate));
                oSb.append("\n");
                oSb.append(oSbLine.toString());
                oSb.append(String.format("%-10s","Major Head"));
                oSb.append(String.format("%-40s","        Description "));
                oSb.append(String.format("%-9s","Challans"));
                oSb.append(String.format("%-20s","     Total Amt"));
                oSb.append("\n");
                oSb.append(oSbLine.toString());
                
                if (resList != null) 
                {
                    Iterator it = resList.iterator();
                    int lCount = 1;
                    while(it.hasNext())
                    {
                        Object[] tuple = (Object[])it.next();
                        oSb.append(String.format("%-9s",tuple[0].toString()) + " ");
                        oSb.append(String.format("%-37s",tuple[1].toString()) + " ");
                        oSb.append(String.format("%8s",tuple[2].toString()) + " ");
                        oSb.append(String.format("%22.2f",tuple[3]) + " ");
                        oTotal = oTotal.add((BigDecimal)tuple[3]);
                        oSb.append("\r\n");
                        lCount++;
	                }
                    
                    oSb.append(oSbLine.toString());
                    String sFooter =  "Total Amount " + String.format("%22.2f", oTotal);
                    oSb.append(String.format("%80s",sFooter+"\r\n"));
                    oSb.append(oSbLine.toString());
	            }
	        }
	    }
	    catch(Exception ex)
	    {
	        ex.printStackTrace();
	        logger.error("Exception occured #\n"+ex);
	    }
	    return oSb.toString();
	}
	
	/**
	 * @param fromDate
	 * @param toDate
	 * @param tsryLocId
	 * @param fromMajorHead
	 * @param ToMajorHead
	 * @param lStrLangId
	 * @return String[]
	 */
	public String[] getPaymentSubsideryRegister(Date fromDate, Date toDate, String tsryLocId,String fromMajorHead,String ToMajorHead, Long lStrLangId)
	{
		try
		{
			String aryReturnObj[] = new String[2];
			Session hibSession = this.sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer();
			String fDate="",tDate="";
			String sMajorHeadString = "";
			
			sql.append(" select tb.bill_no, vd.voucher_no,vd.voucher_date , tb.budmjr_hd ");   
			sql.append(" , odm.ddo_name , mtb.subject_desc , tb.bill_net_amount, ted.edp_code ," +
          "me.edp_desc,ted.edp_amt , sbm.budmjrhd_desc_long , ted.exp_rcp_rec ");
			sql.append(" from trn_voucher_details vd, trn_bill_register tb, ");
			sql.append(" trn_bill_budhead_dtls tbd , trn_bill_edp_dtls ted , mst_edp me , mst_bill_type mtb ");
			sql.append(" , sgva_budmjrhd_mst sbm, org_ddo_mst odm ");
			sql.append(" where vd.bill_no = tb.bill_no ");
			sql.append(" and tb.bill_no = tbd.bill_no ");
			sql.append(" and tb.bill_no = ted.bill_no ");
			sql.append(" and tb.subject_id = mtb.subject_id ");
			sql.append(" and sbm.demand_code = tb.demand_code ");
			sql.append(" and sbm.budmjrhd_code = tb.budmjr_hd ");
			sql.append(" and odm.ddo_code = tb.ddo_code ");
			sql.append(" and sbm.lang_id = :LangName and sbm.fin_yr_id =:FinYear ");
			sql.append(" and mtb.lang_id =:LangId ");
			sql.append(" and me.edp_code = ted.edp_code ");
			sql.append(" and tb.curr_bill_status = 'DTL_PSTNG_DONE' ");
			sql.append(" and tb.tsry_office_code in (select location_code from cmn_location_mst  " +
          "where (location_code=:LocationId and lang_id = :LangId) or parent_loc_id in " +
          "(select loc_id from cmn_location_mst  where location_code=:LocationId and lang_id =:LangId)) ");
			
			
			if(fromMajorHead !=null && !fromMajorHead.equals("") && !fromMajorHead.equals("-1") )
			{
				if(ToMajorHead !=null && !ToMajorHead.equals(""))
					sMajorHeadString = " and tb.budmjr_hd between  '" + fromMajorHead + "' and '" + ToMajorHead +"' ";
				else
					sMajorHeadString = " and tb.budmjr_hd >=  '" + fromMajorHead + "' ";
			}
			else if(ToMajorHead !=null && !ToMajorHead.equals("") && !ToMajorHead.equals("-1"))
				sMajorHeadString =  " and tb.budmjr_hd <=  '" + ToMajorHead + "' ";
			
      sql.append(sMajorHeadString);
			
      if(fromDate!=null && toDate!=null) 
			{
				 fDate = new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
				 tDate = new SimpleDateFormat("yyyy-MM-dd").format(toDate);
				sql.append(" and vd.voucher_date between to_date('"+fDate+"','yyyy-mm-dd') and to_date('"+tDate+"','yyyy-mm-dd')");
			}
			sql.append( " order by tb.budmjr_hd ");
			
			   //System.out.println("SQL Query : "+sql.toString());
			   logger.debug("SQL Query : " + sql.toString());
			
			   SQLQuery oSql  = hibSession.createSQLQuery(sql.toString());
			   oSql.setString("LocationId", tsryLocId);
			   oSql.setLong("LangId",lStrLangId);
			   oSql.setParameter("LangName","en_US");
			   oSql.setInteger("FinYear",new Integer(21));
			   
			   List resList  = oSql.list();
			   TreeMap<Object ,TreeMap<Object, ExpSubsidery>> tm = new TreeMap<Object, TreeMap<Object, ExpSubsidery>>();
			   TreeMap<Object, Object> edpDesc = null;
			   TreeMap<Object, Object> MajorHeadDesc = new TreeMap<Object, Object>();
			   HashMap<Object,TreeMap> oMajorHeadEdpDesc = new HashMap<Object,TreeMap>();
			   
			    for (int i=0; i<resList.size(); i++) 
				{
					Object[] tuple = (Object[]) resList.get(i);
					Object oMajorHead = tuple[3];
					if(tm.containsKey(oMajorHead))
					{
						TreeMap<Object, ExpSubsidery> subMap =  tm.get(oMajorHead);
						Object Bill_No = tuple[0];
						if(subMap.containsKey(Bill_No))
						{
							ExpSubsidery expObject = (ExpSubsidery) subMap.get(Bill_No);
							if(tuple[11].equals("RCP"))
							{
								expObject.edpCodes.put(tuple[7], tuple[9]);
								
								if(oMajorHeadEdpDesc.containsKey(oMajorHead))
								  edpDesc = oMajorHeadEdpDesc.get(oMajorHead);								
								else
								   edpDesc = new TreeMap<Object, Object>();
								
								edpDesc.put(tuple[7], tuple[8]);
								subMap.put(Bill_No,expObject);
								oMajorHeadEdpDesc.put(oMajorHead,edpDesc);
							}
							MajorHeadDesc.put(oMajorHead, tuple[10]);
						}
						else
						{
							
							ExpSubsidery expObject = new ExpSubsidery(); 
							expObject.VoucherNo = (BigDecimal)tuple[1];
							expObject.VoucherDate = (Date)tuple[2];
							expObject.sDDO_Name = tuple[4].toString();
							expObject.sBillType = tuple[5].toString();
							expObject.NetAmt = (BigDecimal)tuple[6];
							if(tuple[11].equals("RCP"))
							{
								expObject.edpCodes.put(tuple[7], tuple[9]);
								if(oMajorHeadEdpDesc.containsKey(oMajorHead))
									  edpDesc = oMajorHeadEdpDesc.get(oMajorHead);								
									else
									   edpDesc = new TreeMap<Object, Object>();
							    edpDesc.put(tuple[7], tuple[8]);
							    oMajorHeadEdpDesc.put(oMajorHead,edpDesc);
							}
							subMap.put(Bill_No,expObject);
							MajorHeadDesc.put(oMajorHead, tuple[10]);
						}
						tm.put(oMajorHead,subMap);
					}
					else
					{
						TreeMap<Object, ExpSubsidery> subMap = new TreeMap<Object, ExpSubsidery>() ;
						    Object Bill_No = tuple[0];
							ExpSubsidery expObject = new ExpSubsidery(); 
							expObject.VoucherNo = (BigDecimal)tuple[1];
							expObject.VoucherDate = (Date)tuple[2];
							expObject.sDDO_Name = tuple[4].toString();
							expObject.sBillType = tuple[5].toString();
							expObject.NetAmt = (BigDecimal)tuple[6];
							
							subMap.put(Bill_No,expObject);
							if(tuple[11].equals("RCP"))
							{
								 
								 expObject.edpCodes.put(tuple[7], tuple[9]);
								 if(oMajorHeadEdpDesc.containsKey(oMajorHead))
									  edpDesc = oMajorHeadEdpDesc.get(oMajorHead);								
									else
									   edpDesc = new TreeMap<Object, Object>();
								 edpDesc.put(tuple[7], tuple[8]);
								 oMajorHeadEdpDesc.put(oMajorHead,edpDesc);
							}
							tm.put(oMajorHead,subMap);
							MajorHeadDesc.put(oMajorHead, tuple[10]);
					}
				}
			   StringBuffer sOutput = new StringBuffer();
			   StringBuffer sHeading = new StringBuffer();
			   StringBuffer objColumnHeading = new StringBuffer();
				StringBuffer sbLine = new StringBuffer(83);
				for(int i=0;i<132;i++)sbLine.append('-');
				sbLine.append("\n");
			   
				sHeading.append(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date()) + "\n");
				sHeading.append(getCenterAlign("Payment Subsidery Register",132) + "\n");
			    sHeading.append( " From Date: " + fDate + "  To Date: " + tDate +"\n" );
			      
			   SimpleDateFormat sm = new SimpleDateFormat("dd");
			   BigDecimal TotalCash = new BigDecimal(0);
			   BigDecimal TotalTransfer = new BigDecimal(0);
			   sOutput.append(sHeading.toString());
			   for (Object oMH : tm.keySet()) 
			   {
				   BigDecimal TotalCashMajorHead = new BigDecimal(0);
				   BigDecimal TotalTransferMajorHead = new BigDecimal(0);
				   int nNumLines = 0;
				   TreeMap subMap = tm.get(oMH);
				   
				    edpDesc = oMajorHeadEdpDesc.get(oMH);
				    if(edpDesc == null)edpDesc = new TreeMap();
				    objColumnHeading = new StringBuffer();
				    objColumnHeading.append(String.format("%-3s","Dt")+ " ");
				    objColumnHeading.append(String.format("%-4s","VHNo") + " ");
				    objColumnHeading.append(String.format("%-30s","DDO Name")+ " ");
				    objColumnHeading.append(String.format("%-15s","Bill Types") + " ");
				    objColumnHeading.append(String.format("%15s","Cash Paid") + " ");
				    
				    if(edpDesc !=null)
				    {
				    	for(Object edpCode : edpDesc.keySet())
				    	{
				    		objColumnHeading.append(String.format("%15s", edpCode.toString()) + " ");
						}
				    	objColumnHeading.append("\n                                                                        ");
				    	for(Object edpCode : edpDesc.keySet())
						{
				    	objColumnHeading.append(String.format("%15.15s", edpDesc.get(edpCode).toString()) + " ");
						}
				    }  
				    objColumnHeading.append(String.format("%15s","Total"));
				      objColumnHeading.append("\n" + sbLine.toString());
				      
				   sOutput.append(" Major Head   " + oMH.toString() +" " + MajorHeadDesc.get(oMH).toString()  + "\n");
				   sOutput.append(sbLine.toString()); 
				   sOutput.append(objColumnHeading.toString());
				   for(Object BillNo : subMap.keySet() )
				   {
					   ExpSubsidery expObject = (ExpSubsidery) subMap.get(BillNo);
					   sOutput.append(String.format("%-3s",sm.format(expObject.VoucherDate)) + " ");
					   sOutput.append(String.format("%-3s",expObject.VoucherNo.toString()) + " ");
					   sOutput.append(String.format("%-30.30s", expObject.sDDO_Name)+ " ");
					   sOutput.append(String.format("%-15.15s", expObject.sBillType) + " ");
					   sOutput.append(String.format("%15.2f", expObject.NetAmt) + " ");
					   
					   BigDecimal TotalTc = new BigDecimal(0);
					   for(Object edpCode : edpDesc.keySet())
					   {
						   if(expObject.edpCodes.containsKey(edpCode))
						   {	 
							   BigDecimal val = (BigDecimal)expObject.edpCodes.get(edpCode);
							   TotalTc = TotalTc.add(val);
							   sOutput.append(String.format("%15.2f",val )+ " ");
						   }
						   else
							   sOutput.append(String.format("%15s","") + " "); 
					   }
					   BigDecimal RowTotal = TotalTc.add(expObject.NetAmt);
					   sOutput.append(String.format("%15.2f",RowTotal) + " ");
					   sOutput.append("\n");
					   nNumLines++;
					   
					   if(nNumLines % 60 == 0)
					   {
					   }
						   
					   TotalCashMajorHead = TotalCashMajorHead.add(expObject.NetAmt);
					   TotalTransferMajorHead = TotalTransferMajorHead.add(TotalTc);
				   }
				   sOutput.append(sbLine.toString());
				   sOutput.append("  Total Cash Paid       "  + String.format("%20.2f",TotalCashMajorHead));sOutput.append("\n");
				   sOutput.append("  Total Book Transfer   "  + String.format("%20.2f",TotalTransferMajorHead));sOutput.append("\n");
				   sOutput.append("  ------------------------------------------------------\n" );
				   sOutput.append("  Total Amount          "  + String.format("%20.2f",TotalTransferMajorHead.add(TotalCashMajorHead)));
				   sOutput.append("\n\n");
				   sOutput.append("  In Words :"  + com.tcs.sgv.common.util.EnglishDecimalFormat.convert((TotalTransferMajorHead.add(TotalCashMajorHead)).longValue()) );
				   sOutput.append("\n");
				   TotalCash = TotalCash.add(TotalCashMajorHead);
				   TotalTransfer = TotalTransfer.add(TotalTransferMajorHead); 
				   
			   }
			   aryReturnObj[0] = getPaymentSubsideryRegisterPrintableFormat(fDate,tDate,MajorHeadDesc,oMajorHeadEdpDesc,tm);
			   aryReturnObj[1]= sOutput.toString();
			   return aryReturnObj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * @param fromDate
	 * @param toDate
	 * @param MajorHeadDesc
	 * @param oMajorHeadEdpDesc
	 * @param tm
	 * @return String
	 */
	public String getPaymentSubsideryRegisterPrintableFormat(String fromDate,String toDate,Map MajorHeadDesc,
	        Map oMajorHeadEdpDesc,TreeMap<Object, TreeMap<Object, ExpSubsidery>> tm )
	{
	    try
	    {
	        StringBuffer sOutput = new StringBuffer();
	        StringBuffer sHeading = new StringBuffer();
	        StringBuffer objColumnHeading = new StringBuffer();
	        StringBuffer sbLine = new StringBuffer(83);

	        for(int i=0;i<132;i++)sbLine.append('-');
	        sbLine.append("\n");

	        sHeading.append("<div><pre>");
	        sHeading.append(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date()) + "\n");
	        sHeading.append(getCenterAlign("Payment Subsidery Register",132) + "\n");
	        sHeading.append( " From Date: " + fromDate + "  To Date: " + toDate +"\n" );

	        SimpleDateFormat sm = new SimpleDateFormat("dd");
	        BigDecimal TotalCash = new BigDecimal(0);
	        BigDecimal TotalTransfer = new BigDecimal(0);

	        sOutput.append(sHeading.toString());
	        for (Object oMH : tm.keySet()) 
	        {	
	            BigDecimal TotalCashMajorHead = new BigDecimal(0);
	            BigDecimal TotalTransferMajorHead = new BigDecimal(0);
	            int nNumLines = 0;
	            TreeMap subMap = tm.get(oMH);

	            objColumnHeading = new StringBuffer(); 
	            objColumnHeading.append(String.format("%-3s","Dt")+ " ");
	            objColumnHeading.append(String.format("%-4s","VHNo") + " ");
	            objColumnHeading.append(String.format("%-30s","DDO Name")+ " ");
	            objColumnHeading.append(String.format("%-15s","Bill Types") + " ");
	            objColumnHeading.append(String.format("%15s","Cash Paid") + " ");
	            TreeMap edpDesc = new TreeMap(); 

	            if(oMajorHeadEdpDesc.containsKey(oMH))
	                edpDesc = (TreeMap)oMajorHeadEdpDesc.get(oMH);

	            for(Object edpCode : edpDesc.keySet())
	            {
	                objColumnHeading.append(String.format("%15s", edpCode.toString()) + " ");
	            }
	            objColumnHeading.append("\n                                                                        ");
	            for(Object edpCode : edpDesc.keySet())
	            {
	                objColumnHeading.append(String.format("%15.15s", edpDesc.get(edpCode).toString()) + " ");
	            }
	            objColumnHeading.append(String.format("%15s","Total"));
	            objColumnHeading.append("\n" + sbLine.toString());


	            sOutput.append(" Major Head   " + oMH.toString() +" " + MajorHeadDesc.get(oMH).toString()  + "\n");
	            sOutput.append(sbLine.toString()); 
	            sOutput.append(objColumnHeading.toString());

	            for(Object BillNo : subMap.keySet() )
	            {
	                ExpSubsidery expObject = (ExpSubsidery) subMap.get(BillNo);
	                sOutput.append(String.format("%-3s",sm.format(expObject.VoucherDate)) + " ");
	                sOutput.append(String.format("%-3s",expObject.VoucherNo.toString()) + " ");

	                ArrayList tokens = getSplitedString(expObject.sDDO_Name,30);

	                if(tokens.size() > 0 )
	                    sOutput.append(String.format("%-30s", tokens.get(0).toString())+ " ");

	                sOutput.append(String.format("%-15.15s", expObject.sBillType) + " ");
	                sOutput.append(String.format("%15.2f", expObject.NetAmt) + " ");

	                BigDecimal TotalTc = new BigDecimal(0);
	                TotalTc.add(expObject.NetAmt);
	                for(Object edpCode : edpDesc.keySet())
	                {
	                    if(expObject.edpCodes.containsKey(edpCode))
	                    {	 
	                        BigDecimal val = (BigDecimal)expObject.edpCodes.get(edpCode);
	                        TotalTc = TotalTc.add(val);
	                        sOutput.append(String.format("%15.2f",val )+ " ");
	                    }
	                    else
	                        sOutput.append(String.format("%15s","") + " "); 
	                }
	                BigDecimal RowTotal = TotalTc.add(expObject.NetAmt);
	                sOutput.append(String.format("%15.2f",RowTotal) + " ");
	                sOutput.append("\n");

	                nNumLines++;
	                for (int i=1 ;i< tokens.size() ;i++,nNumLines++ )
	                    sOutput.append("         "+ tokens.get(i) + "\n" );

	                if(nNumLines % 60 == 0)
	                {
	                    sOutput.append("</div></pre>");
	                    sOutput.append(sHeading.toString());
	                    sOutput.append(" Major Head   " + oMH.toString() +" " + MajorHeadDesc.get(oMH).toString()  + "\n");
	                    sOutput.append(sbLine.toString()); 
	                    sOutput.append(objColumnHeading.toString());
	                }

	                TotalCashMajorHead = TotalCashMajorHead.add(expObject.NetAmt);
	                TotalTransferMajorHead = TotalTransferMajorHead.add(TotalTc);
	            }
	            sOutput.append(sbLine.toString());
	            sOutput.append("  Total Cash Paid       "  + String.format("%20.2f",TotalCashMajorHead));sOutput.append("\n");
	            sOutput.append("  Total Book Transfer   "  + String.format("%20.2f",TotalTransferMajorHead));sOutput.append("\n");
	            sOutput.append("  ------------------------------------------------------\n" );
	            sOutput.append("  Total Amount          "  + String.format("%20.2f",TotalTransferMajorHead.add(TotalCashMajorHead)));
	            sOutput.append("\n\n");
	            sOutput.append("  In Words :"  + com.tcs.sgv.common.util.EnglishDecimalFormat.convert((TotalTransferMajorHead.add(TotalCashMajorHead)).longValue()) );
	            sOutput.append("\n");

	            TotalCash = TotalCash.add(TotalCashMajorHead);
	            TotalTransfer = TotalTransfer.add(TotalTransferMajorHead); 
	            sOutput.append("</div></pre>");
	            sOutput.append(sHeading.toString());
	        }
	        return sOutput.toString();

	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();

	    }
	    return "";
	}
	/**
	 * @param sValue
	 * @param length
	 * @return String
	 */
	private String getCenterAlign(String sValue,int length)
	{
	    StringBuffer sb = new StringBuffer(length);
	    int nStartPos = (length - sValue.length())/2;
	   	for(int i = 0; i < nStartPos ; i++) sb.append(" ");
	    sb.append(sValue);
	    for(int i = sb.length(); i < length ; i++) sb.append(" ");
	    return sb.toString();
	}
	/**
	 * @param strValue
	 * @param Width
	 * @return ArrayList
	 */
	private ArrayList arrangeString(String  strValue,int Width)
    {
    	ArrayList<String> al = new ArrayList<String>();
    	StringTokenizer oTokenizer = new StringTokenizer(strValue," ");
    	String LastLine = "";
    	while(oTokenizer.hasMoreElements())
    	{
    		String strToken =oTokenizer.nextToken();
    		if(strToken.length() > Width )
    		{
    			ArrayList oSpl = getSplitedString(strToken,Width);
    			for(int i = 0;i < oSpl.size()-1 ; i++)
    				al.add(oSpl.get(i).toString());
    			
    			LastLine = oSpl.get(oSpl.size()-1).toString().trim();
    		}
    		else
    		{
    			if((LastLine.length() + strToken.length()) <= Width)
    				LastLine = LastLine + " " + strToken;
    			else
    			{
    				al.add(String.format("%-"+Width + "s", LastLine.trim()));
    				LastLine =strToken;
    			}
    		}
    	}
    	if(!LastLine.equals(""))al.add(String.format("%-"+Width + "s", LastLine));
    	return al;
    }

   
    /**
     * @param strValue
     * @param Width
     * @return ArrayList
     */
    private ArrayList getSplitedString(String  strValue,int Width)
    {
    	ArrayList<String> al = new ArrayList<String>();
    	int beginIndex = 0, endIndex = Width;
    	strValue = strValue.trim();
    	int sLength = strValue.length();
    	String sFormat = "%-" + Width + "s";
    	
    	if(sLength <= Width )
    	{  
    		al.add(String.format(sFormat , strValue));
    		return al;
    	}
    	 while(endIndex < sLength)
    	 {
    	    String token = strValue.substring(beginIndex,endIndex);
    		al.add(String.format(sFormat , token));
    		beginIndex = endIndex;
    		endIndex = beginIndex + Width;
    	 }
    	 if(beginIndex < sLength)
    		 al.add(String.format(sFormat ,strValue.substring(beginIndex)));
    	 
    	 return al;
    }
    
    public String getBranchWiseChallanSummary(String sLocationCode,Date oFromDate, Date oToDate,Long langId,String sMajorHead)
    {
      ArrayList oOutputList = new ArrayList();
      StringBuffer sOutput = new StringBuffer();
        
        sOutput.append("SELECT MAX(MST_BANK.BANK_NAME), ");
        sOutput.append("MAX(RLT_BANK_BRANCH.BRANCH_NAME), ");
        sOutput.append("COUNT(RD.RECEIPT_DETAIL_ID), ");
        sOutput.append("SUM(RD.AMOUNT), ");
        sOutput.append("(LPAD(NVL(RBD.BUD_MAJOR_HEAD, 0), 4, '0') || ");
        sOutput.append("LPAD(NVL(RBD.BUD_SUBMJR_HEAD, 0), 2, '0') || ");
        sOutput.append("LPAD(NVL(RBD.BUD_MIN_HEAD, 0), 3, '0') || ");
        sOutput.append("LPAD(NVL(RBD.BUD_SUB_HEAD, 0), 2, '0')) AS FORMULA, ");
        sOutput.append("MST_BANK.BANK_CODE, ");
        sOutput.append("RLT_BANK_BRANCH.BRANCH_CODE ");
        sOutput.append("FROM TRN_RECEIPT_DETAILS RD, ");
        sOutput.append("TRN_RCPT_BUDHEAD_DTLS RBD, ");
        sOutput.append("TRN_RCPT_MVMNT TM, ");
        sOutput.append("MST_BANK, ");
        sOutput.append("RLT_BANK_BRANCH,");
        sOutput.append("(SELECT MAX(MOVEMNT_ID) AS M_ID, RECEIPT_DETAIL_ID ");
        sOutput.append("FROM TRN_RCPT_MVMNT ");
        sOutput.append("GROUP BY RECEIPT_DETAIL_ID) TEMP ");
        sOutput.append("WHERE RD.RECEIPT_DETAIL_ID = RBD.RECEIPT_DETAIL_ID AND ");
        sOutput.append("RD.BANK_BRANCH_CODE = RLT_BANK_BRANCH.BRANCH_CODE AND ");
        sOutput.append("RD.BANK_CODE = MST_BANK.BANK_CODE AND ");
        sOutput.append("MST_BANK.BANK_CODE = RLT_BANK_BRANCH.BANK_CODE AND ");
        sOutput.append("RD.RECEIPT_DETAIL_ID = TM.RECEIPT_DETAIL_ID AND ");
        sOutput.append("TEMP.M_ID = TM.MOVEMNT_ID AND ");
        sOutput.append("TEMP.RECEIPT_DETAIL_ID = TM.RECEIPT_DETAIL_ID AND ");
        sOutput.append("TM.MVMNT_STATUS = :status AND ");
        sOutput.append("RD.RCVD_BY_BANK_DATE BETWEEN :fromDate AND ");
        sOutput.append(":toDate AND MST_BANK.LANG_ID = :langId AND ");
        sOutput.append("RLT_BANK_BRANCH.LOCATION_CODE = :locationCode AND RD.MAJOR_HEAD = :majorHead ");
        sOutput.append("GROUP BY MST_BANK.BANK_CODE, ");
        sOutput.append("RLT_BANK_BRANCH.BRANCH_CODE, ");
        sOutput.append("RBD.BUD_MAJOR_HEAD, ");
        sOutput.append("RBD.BUD_SUBMJR_HEAD, ");
        sOutput.append("RBD.BUD_MIN_HEAD, ");
        sOutput.append("RBD.BUD_SUB_HEAD ");
        Session hibSession = this.sessionFactory.getCurrentSession();
        SQLQuery oSql  = hibSession.createSQLQuery(sOutput.toString());
        
        oSql.setString("majorHead", sMajorHead);
        oSql.setLong("langId",langId);
        oSql.setDate("fromDate", oFromDate);
        oSql.setDate("toDate", oToDate);
        oSql.setString("locationCode",sLocationCode);
        oSql.setString("status",DBConstants.ST_DTL_PSTNG_DONE );
        
        List lstResult = oSql.list();
        HashMap subHeadDesc =  getSubheadDesc(sMajorHead,"en_US",new Long(21));
        HashMap oBankDesc = new HashMap();
        HashMap oBrachDesc = new HashMap();
        HashSet heading = new HashSet();
        
        TreeMap<Object,TreeMap> tmBank = new TreeMap<Object,TreeMap>();
        for(Object key : lstResult)
        {
          Object[] keys = (Object[])key;
          if(tmBank.containsKey(keys[5]))
          {
            TreeMap<Object,TreeMap> tmBranch =  tmBank.get(keys[5]);
            if(tmBranch.containsKey(keys[6]))
            {
              TreeMap<Object, BankChallan> tmFormulas = tmBranch.get(keys[6]);
              BankChallan bc = new BankChallan();
                bc.numChallan = (BigDecimal)keys[2];
                bc.sFormula = keys[4].toString();
                bc.oAmount = (BigDecimal)keys[3];
                tmFormulas.put(keys[4],bc);
                tmBranch.put(keys[6],tmFormulas);
                tmBank.put(keys[5], tmBranch);
                heading.add(keys[4].toString());
            }
            else
            {
                TreeMap<Object, BankChallan> tmFormulas = new TreeMap<Object, BankChallan>();
                BankChallan bc = new BankChallan();
                bc.numChallan = (BigDecimal)keys[2];
                bc.sFormula = keys[4].toString();
                bc.oAmount = (BigDecimal)keys[3];
                tmFormulas.put(keys[4],bc);
                tmBranch.put(keys[6],tmFormulas);
                tmBank.put(keys[5], tmBranch);
                oBrachDesc.put(keys[6],keys[1]);
                heading.add(keys[4].toString());
            }
          }
          else
          {
            TreeMap tmBranch = new TreeMap();
            TreeMap<Object, BankChallan> tmFormulas = new TreeMap<Object, BankChallan>();
            BankChallan bc = new BankChallan();
            bc.numChallan = (BigDecimal)keys[2];
            bc.sFormula = keys[4].toString();
            bc.oAmount = (BigDecimal)keys[3];
            tmFormulas.put(keys[4],bc);
            tmBranch.put(keys[6],tmFormulas);
            tmBank.put(keys[5], tmBranch);
            oBankDesc.put(keys[5], keys[0]);
            oBrachDesc.put(keys[6],keys[1]);
            heading.add(keys[4].toString());
          }
         }  // for loop
        
        ArrayList<Object> row = new ArrayList<Object>();
        
        ArrayList lstHeading  = new ArrayList();
        lstHeading.add("BRANCH NAME");
        lstHeading.add("TOTAL CHALLANS");
        for(Object keyFormulaValue : heading.toArray())
        {
          if(subHeadDesc.containsKey(keyFormulaValue))
            lstHeading.add(subHeadDesc.get(keyFormulaValue));
          else
            lstHeading.add("Other");
        }
        lstHeading.add("Total");
        
        
        for(Object bank : tmBank.keySet())
        {
          
          ArrayList temp = new ArrayList();
          
          temp.add("BANK NAME ");
          temp.add(oBankDesc.get(bank));
          row.add(temp);
          HashMap rowTotal = new HashMap();
          BigDecimal total = new BigDecimal(0);
          
          rowTotal.put("Total",new BigDecimal(0));
          
          TreeMap branch = tmBank.get(bank);
          for(Object branches : branch.keySet())
          {
               TreeMap formula = (TreeMap)branch.get(branches);
                   ArrayList branchRecord = new ArrayList();
                   ArrayList colVals = new ArrayList();
                   branchRecord.add(oBrachDesc.get(branches));
                   BigDecimal totChallans = new BigDecimal(0);
                   BigDecimal totAmount = new BigDecimal(0);
                   
                   for(Object keyFormulaValue : heading.toArray())
                   {
                     if(formula.containsKey(keyFormulaValue))
                     {
                       BankChallan bc = (BankChallan)formula.get(keyFormulaValue);
                       totChallans = totChallans.add(bc.numChallan);
                       totAmount = totAmount.add(bc.oAmount);
                       colVals.add(bc.oAmount);
                       
                       if(rowTotal.containsKey(keyFormulaValue))
                       {
                         BigDecimal val = (BigDecimal)rowTotal.get(keyFormulaValue);
                         val = val.add(bc.oAmount);
                         rowTotal.put(keyFormulaValue, val);
                       }
                       else
                       {
                         rowTotal.put(keyFormulaValue, bc.oAmount);
                       } 
                       
                     } 
                     else 
                     colVals.add(new BigDecimal(0));
                   }
                   branchRecord.add(totChallans);
                   branchRecord.addAll(colVals);
                   branchRecord.add(totAmount);
                   total = total.add(totChallans);
                   BigDecimal val = (BigDecimal)rowTotal.get("Total");
                   val = val.add(totAmount);
                   rowTotal.put("Total", val);
                   row.add(branchRecord);
            }   
              
               ArrayList al = new ArrayList();
               al.add("TOTAL");
               al.add(total);
               for(Object keyFormulaValue : heading.toArray())
               {
                 if(rowTotal.containsKey(keyFormulaValue))
                   al.add(rowTotal.get(keyFormulaValue));
                 else
                   al.add(new BigDecimal(0));
               }
               al.add(rowTotal.get("Total"));
               row.add(al);
         
        }
        String output = getBankwiseChallanDisplay(row,lstHeading,oFromDate,oToDate,sMajorHead);
        //System.out.println(output);
        return output;  
    }
    /**
     * @param data
     * @return
     */
    private String getBankwiseChallanDisplay(ArrayList data,ArrayList Heading,Date fromDate,Date toDate,String majorHead)
    {
      StringBuffer outPut = new StringBuffer();
      StringBuffer header = new StringBuffer();
      StringBuffer line = new StringBuffer();
      SimpleDateFormat format  = new SimpleDateFormat("dd/MM/yyyy");
      
      outPut.append( getCenterAlign("Branchwise Challan Summary\r\n",200));
      outPut.append( "MAJOR HEAD :" +majorHead + " FROM DATE :" + format.format(fromDate) +  " TO DATE : " + format.format(toDate) );
      outPut.append("\n");
      for(int i=0;i<230;i++)line.append('-');
      
      ArrayList[] arrangedHeads = new ArrayList[Heading.size()];
      int maxRow = 1;
        
      int columnSize[] = new  int[Heading.size()];
      columnSize[0]=20;
      columnSize[1]=6;
      
      for(int i=2;i<columnSize.length;i++)columnSize[i]=17;
      
      for(int j=0; j< Heading.size() ;j++)
      {
           String sColumnValue =Heading.get(j).toString();
           arrangedHeads[j] = arrangeString(sColumnValue.trim(),columnSize[j]);
           if(arrangedHeads[j].size() > maxRow)maxRow = arrangedHeads[j].size();
      }
       
       StringBuffer sbHead= new StringBuffer();
       for(int rCnt = 0 ;rCnt < maxRow ; rCnt ++)
       {
         for(int nCnt=0;nCnt < arrangedHeads.length; nCnt++ )
         {
            if(arrangedHeads[nCnt].size() > rCnt)
                sbHead.append(arrangedHeads[nCnt].get(rCnt).toString() + " ");
            else
                sbHead.append( String.format("%" + columnSize[nCnt] +"s","") + " ");
         }
         header.append(sbHead.toString() + "\r\n");
         sbHead = new StringBuffer();
       }   
      
       for(Object row : data)
       {
          ArrayList objRow = (ArrayList)row;
          
          if(objRow.size() == 2 )
          {
            outPut.append(line.toString());
            outPut.append("\n");
            outPut.append(String.format("%20s",objRow.get(0).toString()) + " " );
            outPut.append(String.format("%20s",objRow.get(1).toString()));
            outPut.append("\n");
            outPut.append(line.toString());
            outPut.append("\n");
            outPut.append(header.toString());
            outPut.append(line.toString());
            outPut.append("\n");
          }
          else
          { 
            if(objRow.get(0).equals("TOTAL"))outPut.append(line.toString() +"\r\n");
            for(int i=0;i < objRow.size();i++)
            {
              Object objCell = objRow.get(i);
              
              if(i==0)
                outPut.append(String.format("%-20.20s",objCell.toString()) + " ");
              else if(i==1)
                  outPut.append(String.format("%6s",objCell.toString()) + " ");
              else
                outPut.append(String.format("%17.2f",objCell) + " ");
              
            }
            if(objRow.get(0).equals("Total"))outPut.append("\r\n" + line.toString());
          }
          outPut.append("\n");
       }
      return outPut.toString();
    }
    private String getBranchwiseChallanDisplay(ArrayList data,ArrayList Heading,Date fromDate,Date toDate,String majorHead)
    {
      StringBuffer outPut = new StringBuffer();
      StringBuffer header = new StringBuffer();
      StringBuffer line = new StringBuffer();
      SimpleDateFormat format  = new SimpleDateFormat("dd/MM/yyyy");
      
      outPut.append( getCenterAlign("Bankwise Challan Summary\r\n",200));
      outPut.append( "MAJOR HEAD :" +majorHead + " FROM DATE :" + format.format(fromDate) +  " TO DATE : " + format.format(toDate) );
      outPut.append("\n");
      for(int i=0;i<230;i++)line.append('-');
      
      ArrayList[] arrangedHeads = new ArrayList[Heading.size()];
      int maxRow = 1;
        
      int columnSize[] = new  int[Heading.size()];
      columnSize[0]=20;
      columnSize[1]=6;
      
      for(int i=2;i<columnSize.length;i++)columnSize[i]=17;
      
      for(int j=0; j< Heading.size() ;j++)
      {
           String sColumnValue =Heading.get(j).toString();
           arrangedHeads[j] = arrangeString(sColumnValue.trim(),columnSize[j]);
           if(arrangedHeads[j].size() > maxRow)maxRow = arrangedHeads[j].size();
      }
       
       StringBuffer sbHead= new StringBuffer();
       for(int rCnt = 0 ;rCnt < maxRow ; rCnt ++)
       {
         for(int nCnt=0;nCnt < arrangedHeads.length; nCnt++ )
         {
            if(arrangedHeads[nCnt].size() > rCnt)
                sbHead.append(arrangedHeads[nCnt].get(rCnt).toString() + " ");
            else
                sbHead.append( String.format("%" + columnSize[nCnt] +"s","") + " ");
         }
         header.append(sbHead.toString() + "\r\n");
         sbHead = new StringBuffer();
       }   
       
       outPut.append(line.toString());
       outPut.append("\n");
       outPut.append(header.toString());
       outPut.append(line.toString());
       outPut.append("\n");
       
       for(Object row : data)
       {
          ArrayList objRow = (ArrayList)row;
          
            if(objRow.get(0).equals("TOTAL"))outPut.append(line.toString() +"\r\n");
            for(int i=0;i < objRow.size();i++)
            {
              Object objCell = objRow.get(i);
              if(i==0)
                outPut.append(String.format("%-20.20s",objCell.toString()) + " ");
              else if(i==1)
                  outPut.append(String.format("%6s",objCell.toString()) + " ");
              else
                outPut.append(String.format("%17.2f",objCell) + " ");
            }
            if(objRow.get(0).equals("Total"))outPut.append("\r\n" + line.toString());
            outPut.append("\n");
       }
      return outPut.toString();
    }
    private HashMap getSubheadDesc(String sMajorHead,String sLangid,Long finYearId)
    {
      
      StringBuffer sb = new StringBuffer();
      Session hibSession = this.sessionFactory.getCurrentSession();
      HashMap<Object, Object> hmDesc = new HashMap<Object, Object>();
      sb.append("SELECT (SM.BUDMJRHD_CODE || SM.BUDSUBMJRHD_CODE || SM.BUDMINHD_CODE " +
          "|| SM.BUDSUBHD_CODE),UPPER(SM.BUDSUBHD_DESC_LONG) FROM SGVA_BUDSUBHD_MST SM WHERE " + 
          " SM.BUDMJRHD_CODE = :majorHead " +  
          " AND LANG_ID = :langId AND SM.FIN_YR_ID=:finYearId ") ;

         SQLQuery oSql  = hibSession.createSQLQuery(sb.toString());
         oSql.setString("majorHead", sMajorHead);
         oSql.setLong("finYearId",finYearId);
         oSql.setString("langId",sLangid);
         
         List lstResult = oSql.list();
         for(Object key : lstResult)
         {
           Object keys[] = (Object[])key;
           hmDesc.put(keys[0], keys[1]);
         }
        return hmDesc;
    }
    
    public String getBankWiseChallanSummary(String sLocationCode,Date oFromDate, Date oToDate,Long langId,String sMajorHead)
    {
      ArrayList oOutputList = new ArrayList();
      StringBuffer sb = new StringBuffer();
        
      sb.append("SELECT MAX(MST_BANK.BANK_NAME),");
      sb.append("         COUNT(RD.RECEIPT_DETAIL_ID),");
      sb.append("         SUM(RD.AMOUNT),");
      sb.append("         MAX(UPPER(SM.BUDSUBHD_DESC_LONG)),");
      sb.append("         MST_BANK.BANK_CODE");
      sb.append("    FROM TRN_RECEIPT_DETAILS RD,");
      sb.append("         TRN_RCPT_BUDHEAD_DTLS RBD,");
      sb.append("         TRN_RCPT_MVMNT TM,");
      sb.append("         MST_BANK,");
      sb.append("         SGVA_BUDSUBHD_MST SM,");
      sb.append("         (SELECT MAX(MOVEMNT_ID) AS M_ID, RECEIPT_DETAIL_ID");
      sb.append("            FROM TRN_RCPT_MVMNT");
      sb.append("           GROUP BY RECEIPT_DETAIL_ID) TEMP");
      sb.append("   WHERE RD.RECEIPT_DETAIL_ID = RBD.RECEIPT_DETAIL_ID AND");
      sb.append("         RD.BANK_CODE = MST_BANK.BANK_CODE AND");
      sb.append("         RD.RECEIPT_DETAIL_ID = TM.RECEIPT_DETAIL_ID AND");
      sb.append("         TEMP.M_ID = TM.MOVEMNT_ID AND");
      sb.append("         TEMP.RECEIPT_DETAIL_ID = TM.RECEIPT_DETAIL_ID AND");
      sb.append("         SM.BUDMJRHD_CODE = RBD.BUD_MAJOR_HEAD AND");
      sb.append("         SM.BUDMINHD_CODE = RBD.BUD_MIN_HEAD AND");
      sb.append("         SM.BUDSUBMJRHD_CODE = RBD.BUD_SUBMJR_HEAD AND");
      sb.append("         SM.BUDSUBHD_CODE = RBD.BUD_SUB_HEAD AND");
      sb.append("         SM.FIN_YR_ID = 21 AND");
      sb.append("         SM.LANG_ID = 'en_US' AND RD.LOCATION_CODE= :locationCode AND");
      sb.append("         TM.MVMNT_STATUS = :status AND");
      sb.append("         RD.RCVD_BY_BANK_DATE BETWEEN :fromDate AND");
      sb.append("         :toDate AND MST_BANK.LANG_ID = :langId AND");
      sb.append("         RD.MAJOR_HEAD = :majorHead ");
      sb.append("   GROUP BY MST_BANK.BANK_CODE,");
      sb.append("            RBD.BUD_MAJOR_HEAD,");
      sb.append("            RBD.BUD_SUBMJR_HEAD,");
      sb.append("            RBD.BUD_MIN_HEAD,");
      sb.append("            RBD.BUD_SUB_HEAD");


        Session hibSession = this.sessionFactory.getCurrentSession();
        SQLQuery oSql  = hibSession.createSQLQuery(sb.toString());
        
        oSql.setString("majorHead", sMajorHead);
        oSql.setLong("langId",langId);
        oSql.setDate("fromDate", oFromDate);
        oSql.setDate("toDate", oToDate);
        oSql.setString("locationCode",sLocationCode);
        oSql.setString("status",DBConstants.ST_DTL_PSTNG_DONE );
        
        List lstResult = oSql.list();
        HashMap subHeadDesc =  getSubheadDesc(sMajorHead,"en_US",new Long(21));
        HashMap<Object, String> oBankDesc = new HashMap<Object, String> ();
        HashSet<Object> heading = new HashSet<Object>();
        
        TreeMap<Object,TreeMap<Object, BankChallan>> tmBank = new TreeMap<Object,TreeMap<Object, BankChallan>>();
        for(Object key : lstResult)
        {
          Object[] keys = (Object[])key;
          if(tmBank.containsKey(keys[4]))
          {
            
            TreeMap<Object, BankChallan> tmFormulas = tmBank.get(keys[4]);
              BankChallan bc = new BankChallan();
              bc.numChallan = (BigDecimal)keys[1];
              bc.sFormula = keys[3].toString();
              bc.oAmount = (BigDecimal)keys[2];
              tmFormulas.put(keys[3],bc);
              tmBank.put(keys[4], tmFormulas);
              heading.add(keys[3].toString());
         }
         else
         {
            TreeMap<Object, BankChallan> tmFormulas = new TreeMap<Object, BankChallan>();
            BankChallan bc = new BankChallan();
            bc.numChallan = (BigDecimal)keys[1];
            bc.sFormula = keys[3].toString();
            bc.oAmount = (BigDecimal)keys[2];
            tmFormulas.put(keys[3],bc);
            tmBank.put(keys[4], tmFormulas);
            oBankDesc.put(keys[4], keys[0].toString());
            heading.add(keys[3].toString());
          }
        }  // for loop
        
        ArrayList<Object> row = new ArrayList<Object>();
        
        ArrayList<String> lstHeading  = new ArrayList<String>();
        lstHeading.add("BANK NAME");
        lstHeading.add("TOTAL CHALLANS".toString());
        
        for(Object keyFormulaValue : heading.toArray())
        {
            lstHeading.add(keyFormulaValue.toString());
        }
        lstHeading.add("Total");
        
        HashMap<Object, Object> rowTotal = new HashMap<Object, Object>();
        BigDecimal total = new BigDecimal(0);
        rowTotal.put("Total",new BigDecimal(0));
        for(Object bank : tmBank.keySet())
        {
          ArrayList<Object> temp = new ArrayList<Object>();
          ArrayList<Object> columnValues = new ArrayList<Object>();
          temp.add(oBankDesc.get(bank));
          TreeMap formula = (TreeMap)tmBank.get(bank);
          BigDecimal totChallans = new BigDecimal(0);
          BigDecimal totAmount = new BigDecimal(0);
                   
          for(Object keyFormulaValue : heading.toArray())
          {
               if(formula.containsKey(keyFormulaValue))
                {
                    BankChallan bc = (BankChallan)formula.get(keyFormulaValue);
                    totChallans = totChallans.add(bc.numChallan);
                    totAmount = totAmount.add(bc.oAmount);
                    columnValues.add(bc.oAmount);
                       
                    if(rowTotal.containsKey(keyFormulaValue))
                    {
                         BigDecimal val = (BigDecimal)rowTotal.get(keyFormulaValue);
                         val = val.add(bc.oAmount);
                         rowTotal.put(keyFormulaValue, val);
                    }
                    else
                    {
                         rowTotal.put(keyFormulaValue, bc.oAmount);
                    } 
                       
               } 
                else
                  columnValues.add(new BigDecimal(0));
           }
           temp.add(totChallans);
           temp.addAll(columnValues);
           temp.add(totAmount);
           
           total = total.add(totChallans);
           BigDecimal val = (BigDecimal)rowTotal.get("Total");
           val = val.add(totAmount);
           rowTotal.put("Total", val);
           row.add(temp);
        }
        ArrayList<Object> al = new ArrayList<Object>();
        al.add("TOTAL");
        al.add(total);
        for(Object keyFormulaValue : heading.toArray())
        {
          if(rowTotal.containsKey(keyFormulaValue))
            al.add(rowTotal.get(keyFormulaValue));
          else
            al.add(new BigDecimal(0));
        }
        al.add(rowTotal.get("Total"));
        row.add(al);
        
        String output = getBranchwiseChallanDisplay(row,lstHeading,oFromDate,oToDate,sMajorHead);
        //System.out.println(output);
        return output;  
    }
	/*Created By Jignesh */
	/* To Fetch DateWise Payment for CashBook Report  */
	/*
	public List getBookPaymentByBudHds(Long tsryLocId, Date fromDate, Date toDate, boolean subTrsy) 
	{		
		HashMap dataList = new HashMap();
		ArrayList oPayData = new ArrayList<BudHdAmtVO>();
		try 
		{
			Session hibSession = this.sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer();
			sql.append("select sum(exp_amt) as amount, (lpad(NVL(mjr_hd,0),4,'0')||lpad(NVL(sub_mjr_hd,0), 2, '0')||lpad(NVL(min_hd,0), 3, '0')||lpad(NVL(sub_hd,0), 2, '0')) as formula,exp_dt from rpt_expenditure_dtls where ");
			if(!subTrsy) {
				sql.append("tsry_code in (-1,"+tsryLocId+") ");
			} else {
				sql.append("(tsry_code =-1 or tsry_code in (select loc_id from cmn_location_mst where loc_id="+tsryLocId+" or parent_loc_id="+tsryLocId+"))");
			}
			
			if(fromDate!=null && toDate!=null) {
				String fDate = new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
				String tDate = new SimpleDateFormat("yyyy-MM-dd").format(toDate);
				sql.append(" and exp_dt between to_date('"+fDate+"','yyyy-mm-dd') and to_date('"+tDate+"','yyyy-mm-dd')");
			}
			sql.append(" group by mjr_hd, sub_mjr_hd, min_hd, sub_hd,exp_dt");

			//System.out.println("SQL Query : "+sql.toString());
			logger.debug("SQL Query : " + sql.toString());
			
			List resList = hibSession.createSQLQuery(sql.toString()).list();
			
			if(resList!=null && resList.size()>0) 
			{				
				logger.debug("Query Result Size : " + resList.size());
				BigDecimal sign = new BigDecimal(1);
				for (int i=0; i<resList.size(); i++) 
				{
					Object[] values = (Object[]) resList.get(i);
					//System.out.println(values[2].toString() + "----------");
					if(values[0]!=null && values[1]!=null) 
					{
						if(dataList.containsKey(values[1]))
						{
							HashMap hm = (HashMap)dataList.get(values[1]);
							hm.put(values[2].toString(),new BigDecimal(values[0].toString()).multiply(sign));
							dataList.put(values[1],hm );
						}	
						else
						{	
							HashMap hm = new HashMap();
							hm.put(values[2].toString(),new BigDecimal(values[0].toString()).multiply(sign));
							dataList.put(values[1],hm );
						}
					}
				}
				
				Iterator itr = dataList.keySet().iterator(); 
				while(itr.hasNext())
				{
					Object key = itr.next();
					BudHdAmtVO bVo = new BudHdAmtVO();
					bVo.setFormula(key.toString());
					bVo.setHashMap((HashMap)dataList.get(key));
					
					oPayData.add(bVo);
				}
			} 
			else 
			{
				logger.debug("Query Result Size : 0");
			}			
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error("Exception occured #\n"+ex);
		}
		
		return oPayData;
	}
	*/
	
	/**
	 * Author Jignesh Sakhiya
	 * To get all the receipt Data Date for generating CashBook Receipt 
	 */
/*	public List getBookReceiptByBudHds(Long tsryLocId, Date fromDate, Date toDate, boolean subTrsy) 
	{		
		HashMap dataList = new HashMap();
		ArrayList oPayData = new ArrayList<BudHdAmtVO>();
		try 
		{
			Session hibSession = this.sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer();
			sql.append("select sum(AMOUNT) as amount, (lpad(NVL(mjr_hd,0),4,'0')||lpad(NVL(sub_mjr_hd,0), 2, '0')||lpad(NVL(min_hd,0), 3, '0')||lpad(NVL(sub_hd,0), 2, '0')) as formula,REVENUE_DT,sum(DISBURSEMENT_AMT)	 from rpt_receipt_dtls where ");
			if(!subTrsy) {
				sql.append("tsry_code in (-1,"+tsryLocId+") ");
			} else {
				sql.append("(tsry_code =-1 or tsry_code in (select loc_id from cmn_location_mst where loc_id="+tsryLocId+" or parent_loc_id="+tsryLocId+"))");
			}
			
			if(fromDate!=null && toDate!=null) {
				String fDate = new SimpleDateFormat("yyyy-MM-dd").format(fromDate);
				String tDate = new SimpleDateFormat("yyyy-MM-dd").format(toDate);
				sql.append(" and REVENUE_DT between to_date('"+fDate+"','yyyy-mm-dd') and to_date('"+tDate+"','yyyy-mm-dd')");
			}
			sql.append(" group by mjr_hd, sub_mjr_hd, min_hd, sub_hd,REVENUE_DT");

			//System.out.println("SQL Query : "+sql.toString());
			logger.debug("SQL Query : " + sql.toString());
			
			List resList = hibSession.createSQLQuery(sql.toString()).list();
			
			if(resList!=null && resList.size()>0) 
			{				
				logger.debug("Query Result Size : " + resList.size());
				BigDecimal sign = new BigDecimal(1);
				for (int i=0; i<resList.size(); i++) 
				{
					Object[] values = (Object[]) resList.get(i);
					if(values[0]!=null && values[1]!=null && values[2]!=null && values[3]!=null  ) 
					{
						if(dataList.containsKey(values[1]))
						{
							HashMap hm = (HashMap)dataList.get(values[1]);
							BigDecimal finalAmount = new BigDecimal(values[0].toString()).subtract(new BigDecimal(values[3].toString()));
							hm.put(values[2],finalAmount.multiply(sign));
							dataList.put(values[1],hm);
						}	
						else
						{	
							HashMap hm = new HashMap();
							BigDecimal finalAmount = new BigDecimal(values[0].toString()).subtract(new BigDecimal(values[3].toString()));
							hm.put(values[2],finalAmount.multiply(sign));
							dataList.put(values[1],hm );
						}
					}
				}
				
				Iterator itr = dataList.keySet().iterator(); 
				while(itr.hasNext())
				{
					Object key = itr.next();
					BudHdAmtVO bVo = new BudHdAmtVO();
					bVo.setFormula(key.toString());
					bVo.setHashMap((HashMap)dataList.get(key));
					oPayData.add(bVo);
				}
			} 
			else 
			{
				logger.debug("Query Result Size : 0");
			}			
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error("Exception occured #\n"+ex);
		}
		
		return oPayData;
	}
*/
	
}
class BankChallan
{
  public BigDecimal numChallan=new BigDecimal(0);
  public BigDecimal oAmount = new BigDecimal(0);
  public String sFormula="";

}
class ExpSubsidery
{
	public String sMajorHead =   "";
	public String sMajorHeadDesc = "";
	public String sBillType ="";
	public String sDDO_Name ="";
	public BigDecimal NetAmt =new BigDecimal(0);
	public Date VoucherDate;
	public BigDecimal VoucherNo =new BigDecimal(0);
	public TreeMap<Object, Object> edpCodes =new TreeMap();
}
