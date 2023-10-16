package com.tcs.sgv.exprcpt.report;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * ReportQueryDAOImpl
 * Implementation ReportQueryDAO
 * @since  26 Jun 207
 * @author 219480
 */

public class ReportQueryDAOImpl extends GenericDaoHibernateImpl<DDODetailsVO, Integer> implements ReportQueryDAO 
{
  private static final Logger glogger=Logger.getLogger(ReportQueryDAOImpl.class);
  static NumberFormat df =  NumberFormat.getInstance();
  static SimpleDateFormat oDateFormat ; 
  static
  {
    df.setMinimumFractionDigits(2);
    df.setMaximumFractionDigits(2);
    df.setGroupingUsed(false);
    oDateFormat =  new SimpleDateFormat("dd/MM/yyyy");
  }

  public ReportQueryDAOImpl(Class<DDODetailsVO> type,SessionFactory sessionFactory) 
  {
    super(type);
    setSessionFactory(sessionFactory);
  }

  public ReportQueryDAOImpl() 
  {
    super(DDODetailsVO.class);
  }
  public ArrayList<ComboValuesVO> getMajorHead(String lStrLangId) 
  {
    ArrayList<ComboValuesVO> arrMjrHead = new ArrayList<ComboValuesVO>();
    String mjrCode = null;
    try
    {
      Session hibSession = getSession();
      StringBuffer lsb = new StringBuffer(  );
      lsb = new StringBuffer( "select distinct BUDMJRHD_CODE from sgva_budmjrhd_mst " +
          "where  LANG_ID='"+lStrLangId+"' order by BUDMJRHD_CODE" );
      SQLQuery sqlQuery = hibSession.createSQLQuery(lsb.toString());
      List resCash = sqlQuery.list();

      ComboValuesVO vo= new ComboValuesVO(  );
      vo.setDesc("Select");
      vo.setId("-1");
      arrMjrHead.add(vo);
      for (Object obj : resCash) 
      {	
        vo= new ComboValuesVO(  );
        mjrCode = obj.toString();
        vo.setId(mjrCode);
        vo.setDesc(mjrCode);
        arrMjrHead.add(vo);
      }
    }
    catch( Exception e )
    {
      glogger.error( "SQLException::"+e.getMessage(), e );
    }

    return arrMjrHead;
  }
  public ArrayList<ComboValuesVO> getMajorHead(String lStrLangId, String lstrLocId) 
  {
    ArrayList<ComboValuesVO> arrMjrHead = new ArrayList<ComboValuesVO>();
    Connection lCon = null;
    PreparedStatement lStmt = null;
    ResultSet lRs = null;
    String mjrCode = null;

    try
    {
      lCon = DBConnection.getConnection(  );
      StringBuffer lsb = new StringBuffer(  );
      lsb = new StringBuffer( "select distinct BUDMJRHD_CODE from sgva_budmjrhd_mst " +
          "where  LANG_ID='"+lStrLangId+"' order by BUDMJRHD_CODE" );
      lStmt = lCon.prepareStatement( lsb.toString() );
      lRs = lStmt.executeQuery();
      while(lRs.next())
      {
        ComboValuesVO vo= new ComboValuesVO(  );
        mjrCode = lRs.getString("BUDMJRHD_CODE");
        vo.setId(mjrCode);
        vo.setDesc(mjrCode);
        arrMjrHead.add(vo);
      }
    }
    catch( SQLException se )
    {
      glogger.error( "SQLException::"+se.getMessage(), se );
    }

    return arrMjrHead;
  }

  public ArrayList getMonth(String lStrLangId, String lstrLocId)
  {
    ArrayList<ComboValuesVO> arrMonth = new ArrayList<ComboValuesVO>();
    try
    {
      for(Integer i=1;i<=12;i++)
      {
        ComboValuesVO vo= new ComboValuesVO();
        String month= i.toString();
        vo.setId(month);
        vo.setDesc(month);
        arrMonth.add(vo);			
      }
    }
    catch(Exception e)
    {
      glogger.error( "Exception::"+e.getMessage(), e );
      e.printStackTrace();
    }

    return arrMonth;

  }
  public ArrayList getYear(String lStrLangId, String lstrLocId)
  {
    ArrayList<ComboValuesVO> arrYear = new ArrayList<ComboValuesVO>();
    try
    {	
      for(Integer i= 2000;i<=2015;i++)
      {
        ComboValuesVO vo= new ComboValuesVO();
        String year= i.toString();
        vo.setId(year);
        vo.setDesc(year);
        arrYear.add(vo);
      }
    }
    catch(Exception e)
    {
      glogger.error( "Exception::"+e.getMessage(), e );
      e.printStackTrace();
    }
    return arrYear;
  }



  public ArrayList<ArrayList<Object>> getDateWiseReceiptRpt(String fMjrHead, 
      String tMjrHead, String fromDate,String toDate,String locId, String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;
    Map<Object, Object> cashDtls = new TreeMap<Object, Object>();
    Query sqlQueryCash = null;
    Query sqlQueryTc = null;
    try 
    {
      Object[] tuple = null;
      Object[] tuple1 = null;
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      StringBuffer query1 = new StringBuffer();
      String sMajorHeadString = "";
      if(fMjrHead !=null && !fMjrHead.equals("-1"))
      {
        if(tMjrHead !=null && !tMjrHead.equals("-1"))
          sMajorHeadString = " and rd.mjr_hd between '"+fMjrHead+"' and '" +tMjrHead+ "' ";
        else
          sMajorHeadString = " and rd.mjr_hd between '"+fMjrHead+"' and '9999' ";

      }else
      {
        if(tMjrHead !=null && !tMjrHead.equals("-1"))
          sMajorHeadString = " and rd.mjr_hd between '-1' and '" +tMjrHead+ "'  ";  
      }
      query.append("select rd.revenue_dt ,sum(Amount),count(rd.trn_receipt_id) " +
          "from rpt_receipt_dtls rd where rd.active ='Y' and rd.rcpt_status_code = 'Approved' " +
          "and rd.rcpt_type_code= 'Challan' ");
      
      if(sMajorHeadString!=null) query.append(sMajorHeadString);
      if(fromDate != null && toDate != null)
      {
        query.append(" and  rd.revenue_dt between  to_date('" + fromDate + "','dd-mm-yy')  " +
            "and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query.append("and rd.tsry_code in (select location_code from cmn_location_mst  " +
          "where (location_code = :LocationCode and lang_id = :LangId ) or  parent_loc_id " +
          "in (select loc_id  from cmn_location_mst where location_code = :LocationCode " +
          "and lang_id = :LangId )) ");
      query.append(" group by rd.revenue_dt having rd.revenue_dt is not null");

      //System.out.println("SQL QUERY : " + query.toString());
      query1.append("select rd.revenue_dt ,sum(Amount) from rpt_receipt_dtls rd where " +
          "rd.active ='Y' and rd.rcpt_status_code = 'Approved' and rd.dedctn_type = 'A' ");
      
      if(fromDate != null && toDate != null)
      {
        query1.append(" and  rd.revenue_dt between  to_date('" + fromDate + "','dd-mm-yy') " +
            " and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      
      query1.append("and rd.tsry_code in (select location_code from cmn_location_mst  where" +
          " (location_code = :LocationCode and lang_id = :LangId) or  parent_loc_id in " +
          "(select loc_id  from cmn_location_mst where location_code = :LocationCode and lang_id = :LangId )) ");
      
      if(sMajorHeadString!=null) query.append(sMajorHeadString);
      query1.append(" group by rd.revenue_dt having rd.revenue_dt is not null");

      //System.out.println("SQL QUERY1 : " + query1.toString());

      sqlQueryCash = hibSession.createSQLQuery(query.toString());
      sqlQueryTc = hibSession.createSQLQuery(query1.toString());

      sqlQueryCash.setString("LocationCode", locId);
      sqlQueryTc.setString("LocationCode", locId);

      sqlQueryCash.setLong("LangId", new Long(langId));
      sqlQueryTc.setLong("LangId", new Long(langId));

      List resCash = sqlQueryCash.list();
      List resTc = sqlQueryTc.list();

      if (resTc != null) 
      {	
        Iterator it = resTc.iterator();
        while(it.hasNext())
        {
          tuple = (Object[])it.next();
          cashDtls.put(tuple[0],tuple[1]);
        }	
      }
      if (resCash != null) 
      {
        Iterator it = resCash.iterator();
        arrOuter = new ArrayList<ArrayList<Object>>();
        while(it.hasNext())
        {
          tuple1 = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          BigDecimal Cash = null;
          BigDecimal Tc = (BigDecimal)(tuple1[1]);
          BigDecimal Total = null;
          if(cashDtls.containsKey(tuple1[0]))
          {

            arrInner.add(tuple1[0]);
            Cash = (BigDecimal)(cashDtls.get(tuple1[0]));
            arrInner.add(tuple1[2]);
            arrInner.add(df.format(Cash));
            arrInner.add(df.format(Tc));
            Total = new BigDecimal(Cash.doubleValue() + Tc.doubleValue());
            arrInner.add(df.format(Total));
            cashDtls.remove(tuple1[0]);
          }
          else
          {
            arrInner.add(tuple1[0]);	
            arrInner.add(tuple1[2]);
            arrInner.add(df.format(0));
            arrInner.add(df.format(Tc));
            Total =  Tc;
            arrInner.add(df.format(Total));
          }
          arrOuter.add(arrInner);

        }
      }
      Iterator<Object> itr = cashDtls.keySet().iterator();
//    arrOuter = new ArrayList();
      while(itr.hasNext())
      {

        ArrayList<Object> arrInner = new ArrayList<Object>() ;
        Object tupleTemp = itr.next();
        arrInner.add(tupleTemp);
        arrInner.add(0);
        BigDecimal Tc =  (BigDecimal)cashDtls.get(tupleTemp);
        arrInner.add(df.format(Tc));
        arrInner.add(df.format(0));
        arrInner.add(df.format(Tc));
        arrOuter.add(arrInner);

      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }
    return arrOuter;
  }



  public ArrayList<ArrayList<Object>> getHeadWiseChallanRpt(Date fromDate, Date toDate,String locId, String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;
    Query sqlQuery = null;
    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();

      query.append(" select r.mjr_hd ,count( r.rcpt_no),sum(r.amount)  from rpt_receipt_dtls  r ");
      query.append("    where r.rcpt_type_code = 'Challan' and r.rcpt_status_code = 'Approved' and Active='Y'" );
      query.append(" and r.revenue_dt between  to_date('" + oDateFormat.format(fromDate) + "','dd-mm-yy')  and " +
          "to_date('" + oDateFormat.format(toDate)  + "','dd-mm-yy') ");
      query.append("  and r.tsry_code in ");
      query.append("   (select location_code    from cmn_location_mst  where" +
          " (location_code = :LocationCode and lang_id = :LangId) or   parent_loc_id in" );
      query.append(" (select loc_id from cmn_location_mst where " +
          "location_code = :LocationCode and lang_id = :LangId)) ");
      query.append(" group by r.mjr_hd" );


      //System.out.println("SQL QUERY : " + query.toString());

      sqlQuery = hibSession.createSQLQuery(query.toString());
      sqlQuery.setParameter("LocationCode", locId);
      sqlQuery.setLong("LangId",new Long(langId));
//    sqlQuery.setDate("fromDate",fromDate);
//    sqlQuery.setDate("toDate",fromDate);

      //System.out.println( " from date" + fromDate);
      //System.out.println( " from date" + toDate);
      List resList = sqlQuery.list();
      if (resList != null) 
      {
        arrOuter = new ArrayList<ArrayList<Object>>();
        Iterator it = resList.iterator();
        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          arrInner.add(tuple[0]);
          arrInner.add(tuple[1]);
          arrInner.add(df.format((BigDecimal)tuple[2]));
          arrOuter.add(arrInner);
        }	
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }
    return arrOuter;
  }

  public ArrayList<ArrayList> getBankWiseChallanRpt(String fMjrHead, String tMjrHead , 
      Date fromDate,Date toDate,String locId, String langId)
  {
    ArrayList<ArrayList> alMain = new ArrayList<ArrayList>();
    Query sqlQuery = null;

    try 
    {

      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();

      query
      .append("select  max(rlt_bank_branch.branch_name), count(*), sum(rd.amount) , "
              + "(lpad(NVL(rbd.bud_major_head"
              + ", 0), 4, '0') ||lpad(NVL(rbd.bud_submjr_head, 0), 2, '0') ||lpad(NVL(rbd.bud_min_head, 0), "
              + "3, '0') "
              + "||lpad(NVL(rbd.bud_sub_head, 0), 2, '0')) as formula, max(mst_bank.bank_name) from "
              + "trn_receipt_details rd, trn_rcpt_budhead_dtls rbd, mst_bank, rlt_bank_branch "
              + "where rd.receipt_detail_id = rbd.receipt_detail_id and rd.bank_branch_code = "
              + "rlt_bank_branch.branch_code and rd.bank_code = mst_bank.bank_code and mst_bank.bank_code= "
              + "rlt_bank_branch.bank_code and  "
              + " rd.rcvd_by_bank_date between to_date('"
              + oDateFormat.format(fromDate)
              + "','dd-mm-yy')  and to_date('"
              + oDateFormat.format(toDate)
              + "','dd-mm-yy') "
              + "  and mst_bank.lang_id = :LangId  and rd.location_code in "
              + "(select location_code from cmn_location_mst where (location_code = :LocationCode and lang_id = "
              + ":LangId) or parent_loc_id in (select loc_id from cmn_location_mst where location_code = "
              + ":LocationCode and lang_id = :LangId))"
              + " and  (lpad(NVL(rbd.bud_major_head, 0), 4, '0') || lpad(NVL(rbd.bud_submjr_head, 0), 2, '0') "
              + "||  lpad(NVL(rbd.bud_min_head, 0), 3, '0') || lpad(NVL(rbd.bud_sub_head, 0), 2, '0')) "
              + "in ('00400010100','00400010200','00400010205','00400011001')"
              + " and rlt_bank_branch.location_code='"
              + locId
              + "' group by mst_bank.bank_code, rlt_bank_branch.branch_code, rbd.bud_major_head, r"
              + "bd.bud_submjr_head, rbd.bud_min_head, rbd.bud_sub_head");

      //System.out.println("SQL QUERY : " + query.toString());

      sqlQuery = hibSession.createSQLQuery(query.toString());

      sqlQuery.setParameter("LocationCode", locId);
      sqlQuery.setLong("LangId",new Long(langId));

      List resList = sqlQuery.list();

      if (resList != null) 
      {
        Iterator it = resList.iterator();
        HashMap<String,HashMap> hmBank = new HashMap<String,HashMap>();
        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          if(hmBank.containsKey(tuple[4]))
          {
            if(hmBank.containsKey(tuple[4]))
            {		
              HashMap<String,HashMap> hmBankBranch = hmBank.get(tuple[4]);
              HashMap<Object,Object[]> headStrct = new HashMap<Object,Object[]>();
              Object ary[] = new Object[2];
              ary[0]=tuple[1];
              ary[1]=tuple[2];
              headStrct.put(tuple[3],ary);
              hmBankBranch.put(tuple[0].toString(),headStrct);
              hmBank.put(tuple[4].toString(),hmBankBranch );

            }else
            {
              HashMap hmBankBranch = new HashMap<String,HashMap>();
              HashMap<Object,Object[]> headStrct = new HashMap<Object,Object[]>();
              Object ary[] = new Object[2];
              ary[0]=tuple[1];
              ary[1]=tuple[2];
              headStrct.put(tuple[3],ary);
              hmBankBranch.put(tuple[0].toString(),headStrct);
              hmBank.put(tuple[4].toString(),hmBankBranch );
            }
          }else
          {
            HashMap<String, HashMap> hmBankBranch = new HashMap<String,HashMap>();
            HashMap<Object,Object[]> headStrct = new HashMap<Object,Object[]>();
            Object ary[] = new Object[2];
            ary[0]=tuple[1];
            ary[1]=tuple[2];
            headStrct.put(tuple[3],ary);
            hmBankBranch.put(tuple[0].toString(),headStrct);
            hmBank.put(tuple[4].toString(),hmBankBranch );
          }
        }

        Iterator<String> itr = hmBank.keySet().iterator();


        while(itr.hasNext())
        {
          Object Key = itr.next();
          HashMap BankBranch = hmBank.get(Key);
          Iterator<String> Branches = BankBranch.keySet().iterator();

          while(Branches.hasNext())
          {
            long numChallan = 0 ;
            double total = 0;

            Object BranchName = Branches.next();
            HashMap HeadStct = (HashMap)BankBranch.get(BranchName.toString());
            ArrayList<Object> inner = new ArrayList<Object>(10);
            inner.add(BranchName.toString());
            inner.add("0");
            if(HeadStct.containsKey("00400010100"))
            {
              Object keys[] = (Object[])HeadStct.get("00400010100");
              numChallan += Long.parseLong(keys[0].toString());
              total += Double.parseDouble(keys[1].toString());
              inner.add(df.format(keys[1]));
            }
            else inner.add("0.00");
            if(HeadStct.containsKey("00400010200"))
            {
              Object keys[] = (Object[])HeadStct.get("00400010200");
              numChallan += Long.parseLong(keys[0].toString());
              total += Double.parseDouble(keys[1].toString());
              inner.add(df.format(keys[1]));
            }	
            else inner.add("0.00");
            if(HeadStct.containsKey("00400010205"))
            {
              Object keys[] = (Object[])HeadStct.get("00400010205");
              numChallan += Long.parseLong(keys[0].toString());
              total += Double.parseDouble(keys[1].toString());
              inner.add(df.format(keys[1]));
            }	
            else inner.add("0.00");
            if(HeadStct.containsKey("00400011001"))
            {
              Object keys[] = (Object[])HeadStct.get("00400011001");
              numChallan += Long.parseLong(keys[0].toString());
              total += Double.parseDouble(keys[1].toString());
              inner.add(df.format(keys[1]));
            }
            else inner.add("0.00");

            inner.add(df.format(total));
            inner.set(1, numChallan);
            inner.add(Key.toString());
            alMain.add(inner);
          }
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }


    return alMain;
  }


  public ArrayList<ArrayList<Object>> getReceiptSubHeadRpt(String fMjrHead, String tMjrHead, Date fromDate,Date toDate,String locId, String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;
    Query sqlQuery = null;

    try 
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      String sMajorHeadString = "";
      if(fMjrHead !=null && !fMjrHead.equals("-1"))
      {
        if(tMjrHead !=null && !tMjrHead.equals("-1"))
          sMajorHeadString = " AND RD.MJR_HD BETWEEN '"+fMjrHead+"' AND '" +tMjrHead+ "' ";
        else
          sMajorHeadString = " AND RD.MJR_HD BETWEEN '"+fMjrHead+"' AND '9999'  ";

      }
      else
      {
        if(tMjrHead !=null && !tMjrHead.equals("-1"))
          sMajorHeadString = " AND RD.MJR_HD BETWEEN '-1' AND '" +tMjrHead+ "'  ";  
      }

      query.append(" SELECT RD.MJR_HD,");
      query.append(" MAX(BM.BUDMJRHD_DESC_LONG),");
      query.append(" RD.SUB_MJR_HD,");
      query.append(" RD.MIN_HD,");
      query.append(" RD.SUB_HD,");
      query.append(" MAX(B.BUDSUBHD_DESC_LONG),");
      query.append(" SUM(RD.AMOUNT)");
      query.append(" FROM RPT_RECEIPT_DTLS RD,");
      query.append(" SGVA_BUDSUBHD_MST B,");
      query.append(" SGVA_BUDMJRHD_MST BM");
      query.append(" WHERE RD.MJR_HD = B.BUDMJRHD_CODE AND RD.SUB_MJR_HD = B.BUDSUBMJRHD_CODE AND");
      query.append(" RD.MIN_HD = B.BUDMINHD_CODE AND RD.SUB_HD = B.BUDSUBHD_CODE AND");
      query.append(" B.FIN_YR_ID = :finYear AND B.LANG_ID = :langName AND");
      query.append(" RD.MJR_HD = BM.BUDMJRHD_CODE AND RD.RCPT_STATUS_CODE = 'Approved' AND");
      query.append(" RD.ACTIVE = 'Y' AND  BM.LANG_ID = :langName AND BM.FIN_YR_ID =:finYear AND");
      query.append(" RD.REVENUE_DT BETWEEN TO_DATE('" + oDateFormat.format(fromDate) + "', 'dd-MM-yy') AND");
      query.append(" TO_DATE('" + oDateFormat.format(toDate)  + "', 'dd-MM-yy') AND");
      query.append(" RD.TSRY_CODE IN");
      query.append(" (SELECT LOCATION_CODE");
      query.append(" FROM CMN_LOCATION_MST");
      query.append(" WHERE (LOCATION_CODE = :locationId AND LANG_ID = :langId) OR");
      query.append(" PARENT_LOC_ID IN");
      query.append(" (SELECT LOC_ID");
      query.append(" FROM CMN_LOCATION_MST");
      query.append(" WHERE LOCATION_CODE = :locationId AND LANG_ID = :langId))");
      if(!sMajorHeadString.equals("")) query.append(sMajorHeadString);
      query.append(" GROUP BY RD.MJR_HD, RD.SUB_MJR_HD, RD.MIN_HD, RD.SUB_HD");

      //System.out.println("SQL QUERY : " + query.toString());

      sqlQuery = hibSession.createSQLQuery(query.toString());
      sqlQuery.setParameter("langName", getLangName(new Long(langId)));
      sqlQuery.setParameter("locationId", locId);
      sqlQuery.setLong("langId",new Long(langId));
      sqlQuery.setLong("finYear",new Long(21));
      
      List resList = sqlQuery.list();

      if (resList != null) 
      {
        arrOuter = new ArrayList<ArrayList<Object>>();
        Iterator it = resList.iterator();

        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          arrInner.add(tuple[0] +"-" +tuple[2] +"-" +tuple[3] +"-" +tuple[4]);
          arrInner.add(tuple[5]);
          arrInner.add(df.format(tuple[6]));
          arrInner.add(tuple[0] + " " + tuple[1] );
          arrOuter.add(arrInner);

        }	
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }
    return arrOuter;
  }


  public ArrayList<ArrayList> getPaymentSubHeadRpt(String fMjrHead, String tMjrHead , String fromDate,String toDate,String locId, String langId )
  {
    ArrayList<ArrayList> arrOuter = null;


    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      Query sqlQuery = null;
      String sMajorHeadString = "";
      if(fMjrHead !=null && !fMjrHead.equals("-1"))
      {
        if(tMjrHead !=null && !tMjrHead.equals("-1"))
          sMajorHeadString = " and e.mjr_hd between '"+fMjrHead+"' and '" +tMjrHead+ "' ";
        else
          sMajorHeadString = " and e.mjr_hd between '"+fMjrHead+"' and '9999' ";

      }else
      {
        if(tMjrHead !=null && !tMjrHead.equals("-1"))
          sMajorHeadString = " and rd.mjr_hd between ('-1' and '" +tMjrHead+ "') ";  
      }

      query.append(" SELECT  E.MJR_HD, MAX(SM.BUDMJRHD_DESC_LONG), E.SUB_MJR_HD, E.MIN_HD, E.SUB_HD, MAX(SS.BUDSUBHD_DESC_LONG), SUM(E.GROSS_AMNT-E.RECOVERY_AMT) FROM RPT_EXPENDITURE_DTLS E, SGVA_BUDMJRHD_MST SM , SGVA_BUDSUBHD_MST SS " );
      query.append(" WHERE E.MJR_HD=SM.BUDMJRHD_CODE AND E.SUB_MJR_HD=SS.BUDSUBMJRHD_CODE AND E.MJR_HD=SS.BUDMJRHD_CODE AND E.MIN_HD=SS.BUDMINHD_CODE AND E.SUB_HD=SS.BUDSUBHD_CODE ");

      if(fromDate != null && toDate != null)
        query.append(" AND E.EXP_DT BETWEEN  TO_DATE(' " + fromDate + "','dd-MM-YY')  AND TO_DATE('" + toDate  + "','dd-mm-yy') ");
      query.append(" AND SM.FIN_YR_ID= 21  AND SS.FIN_YR_ID = 21 AND E.DEMAND_NO=SM.DEMAND_CODE AND E.DEMAND_NO=SS.DEMAND_CODE AND E.EXP_STATUS_CODE ='DTL_PSTNG_DONE' AND E.ACTIVE= 'Y' ");
      query.append(" AND E.TSRY_CODE IN (SELECT LOCATION_CODE FROM CMN_LOCATION_MST  WHERE (LOCATION_CODE = '"+ locId + "' AND LANG_ID = " + langId +") OR  PARENT_LOC_ID IN (SELECT LOC_ID  FROM CMN_LOCATION_MST WHERE LOCATION_CODE = '" + locId + "' AND LANG_ID = " + langId + "))");
      query.append(" AND SM.LANG_ID = '"+getLangName(new Long(langId))+"' AND SS.LANG_ID = '"+getLangName(new Long(langId))+ "' ");			
      if(!sMajorHeadString.equals(""))query.append(sMajorHeadString);
      query.append("  GROUP BY E.MJR_HD,E.SUB_MJR_HD,E.MIN_HD,E.SUB_HD ");
      //System.out.println("SQL QUERY : " + query.toString());

      sqlQuery = hibSession.createSQLQuery(query.toString());

      List resList = sqlQuery.list();
      if (resList != null) 
      {
        arrOuter = new ArrayList<ArrayList>();
        Iterator it = resList.iterator();

        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          arrInner.add(tuple[0] +"-" +tuple[2] +"-" +tuple[3] +"-" +tuple[4]);
          arrInner.add(tuple[5]);
          arrInner.add( df.format((BigDecimal)tuple[6]));
          arrInner.add(tuple[0] + " " + tuple[1] );
          arrOuter.add(arrInner);
        }	
      }
      //System.out.println("after Retrive :-"+resList.size());
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }
    return arrOuter;
  }

  public ArrayList<ArrayList<Object>> getPaidChequeSummaryRpt( String fromDate, String toDate, String locId, String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;

    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      Query sqlQuery = null;
      query.append("select cleared_dt, count(*), sum(cheque_amt) from trn_cheque_dtls  where cleared_dt is not null");
      if(fromDate != null && toDate != null)
      {
        query.append(" and cleared_dt between  to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query.append(" and location_code in (select location_code from cmn_location_mst  where (location_code = '"+ locId + "' and lang_id = " + langId+") or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = '" + locId+ "' and lang_id = " +langId + "))");
      query.append(" group by cleared_dt");
      //System.out.println("SQL QUERY : " + query.toString());
      sqlQuery = hibSession.createSQLQuery(query.toString());
      List resList = sqlQuery.list();
      //System.out.println("Size of List is :-"+resList.size());
      if (resList != null) 
      {
        arrOuter = new ArrayList<ArrayList<Object>>();
        Iterator it = resList.iterator();

        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          arrInner.add(tuple[0]);
          arrInner.add(tuple[1]);
          arrInner.add(df.format((BigDecimal)tuple[2]));
          arrOuter.add(arrInner);
        }	
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }
    return arrOuter;
  }

  public ArrayList getPaidChequeRpt( String fromDate, String toDate, String locId, String langId)
  {   
    ArrayList<ArrayList<Object>> arrOuter = null;
    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      Query sqlQuery = null;
      query.append("select  print_date as Issue_date , advice_no, cleared_dt as paid_date , cheque_no, cheque_amt  from trn_cheque_dtls where cleared_dt is not null");
      if(fromDate != null && toDate != null)
      {
        query.append(" and cleared_dt between  to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query.append(" and location_code  in (select location_code from cmn_location_mst  where (location_code = '"+ locId + "' and lang_id = " + langId+") or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = '" + locId+ "' and lang_id = " +langId + "))");
      //System.out.println("SQL QUERY : " + query.toString());
      sqlQuery = hibSession.createSQLQuery(query.toString());
      List resList = sqlQuery.list();
      //System.out.println("Result List is  :-"+resList);

      if (resList != null) 
      {
        arrOuter = new ArrayList<ArrayList<Object>>();
        Iterator it = resList.iterator();
        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          arrInner.add(tuple[0]);
          arrInner.add(tuple[1]);
          arrInner.add(tuple[2]);
          arrInner.add(tuple[3]);
          arrInner.add(tuple[4]);
          arrOuter.add(arrInner);
        }	
      }
      //System.out.println("Size of List is :-"+arrOuter.size());
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.PaidCheque Report # \n"+e);
    }
    return arrOuter;
  }
  public ArrayList<ArrayList<Object>> getUnPaidChequeSummaryRpt( String fromDate, String toDate, String locId, String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;
    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      Query sqlQuery = null;
      query.append("select   advice_dt, count(*), sum(cheque_amt)from trn_cheque_dtls where cleared_dt is  null and status= 'CHQ_DSPTCH_DDO' ");
      if(fromDate != null && toDate != null)
      {
        query.append(" and advice_dt between  to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query.append(" and Location_code in (select location_code from cmn_location_mst  where (location_code = '"+ locId + "' and lang_id = " + langId+") or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = '" + locId+ "' and lang_id = " +langId + "))");
      query.append(" group by advice_dt");
      //System.out.println("SQL QUERY : " + query.toString());
      sqlQuery = hibSession.createSQLQuery(query.toString());
      List resList = sqlQuery.list();
      //System.out.println("Result List is  :-"+resList);
      //System.out.println("Size of List is :-"+resList.size());
      if (resList != null) 
      {
        arrOuter = new ArrayList<ArrayList<Object>>();
        Iterator it = resList.iterator();

        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          arrInner.add(tuple[0]);
          arrInner.add(tuple[1]);
          arrInner.add(df.format((BigDecimal)tuple[2]));
          arrOuter.add(arrInner);
        }	
      }
    }

    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }

    return arrOuter;
  }

  public ArrayList<ArrayList<Object>> getCancelledlapsedTCRpt( String fromDate, String toDate, String locId, String langId, Long chequeTypeId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 

      Date parsefdt = sdf.parse(fromDate);
      Date parsetdt = sdf.parse(toDate);
      Calendar cln = Calendar.getInstance();
      Calendar cln1 = Calendar.getInstance();

      Date bound1 ;
      Date bound2;

      cln.setTime(parsefdt);
      cln.add(Calendar.MONTH, -4);
      bound1 = cln.getTime();

      cln1.setTime(parsetdt);
      cln1.add(Calendar.MONTH, -4);
      bound2 = cln1.getTime();
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      Query sqlQuery = null;
      query.append("select  advice_dt, advice_no, cheque_no, party_name,cheque_amt  from trn_cheque_dtls where " );
      if(fromDate != null && toDate != null)
      {
        query.append("  advice_dt between to_date('" + sdf.format(bound1) + "','dd/mm/yyyy')  and to_date('" + sdf.format(bound2)  + "','dd/mm/yyyy') ");
      }
      query.append(" and location_code in (select location_code from cmn_location_mst  where (location_code = '"+ locId + "' and lang_id = " + langId+") or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = '" + locId+ "' and lang_id = " +langId + "))");
      query.append("and status = 'CHQ_DSPTCH_DDO' and cleared_dt is null	");
      //System.out.println("SQL QUERY : " + query.toString());
      sqlQuery = hibSession.createSQLQuery(query.toString());
      List resList = sqlQuery.list();
      //System.out.println("Result List is  :-"+resList);
      //System.out.println("Size of List is :-"+resList.size());
      if (resList != null) 
      {
        arrOuter = new ArrayList<ArrayList<Object>>();
        Iterator it = resList.iterator();

        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          arrInner.add(tuple[0]);
          arrInner.add(tuple[1]);
          arrInner.add(tuple[2]);
          arrInner.add(tuple[3]);
          arrInner.add(df.format((BigDecimal)tuple[4]));
          arrOuter.add(arrInner);

        }	
      }
    }

    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }
    return arrOuter;
  }


  public ArrayList<ArrayList<Object>> getRBDRpt( String fromDate, String toDate, String locId, String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;
    Map<Object, Object> expDtls = new TreeMap<Object, Object>();

    try
    {
      Object[] tuple = null;
      Object[] tuple1 = null;
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      StringBuffer query1 = new StringBuffer();
      Query sqlQuery = null;
      Query sqlQuery1 = null;
      //----------------------Query For Payment Details-----------------------------------------------------------
      query.append("select p.chq_clr_dt, sum(p.amount)  from rpt_payment_dtls p where  p.chq_clr_dt is not null");
      if(fromDate != null && toDate != null)
      {
        query.append(" and  p.chq_clr_dt between  to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query.append(" and p.tsry_code in (select location_code from cmn_location_mst  where (location_code = '"+ locId + "' and lang_id = " + langId+") or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = '" + locId+ "' and lang_id = " +langId + "))");
      query.append(" and p.active='Y'");
      query.append(" group by  p.chq_clr_dt");
      //System.out.println("SQL QUERY : " + query.toString());
      sqlQuery = hibSession.createSQLQuery(query.toString());
      List resList = sqlQuery.list();

      if (resList != null) 
      {
        Iterator it = resList.iterator();
        while(it.hasNext())
        {
          tuple = (Object[])it.next();		
          expDtls.put(tuple[0],tuple[1]);	
        }	
      }
      //System.out.println("size of Map:" +expDtls.size());


//    --------------------------------------Query For Receipt Details --------------------------------------------
      query1.append("select r.revenue_dt, sum(r.amount) from rpt_receipt_dtls r where r.rcpt_type_code='Challan' and r.rcpt_status_code = 'Approved' and r.active='Y'" );
      if(fromDate != null && toDate != null)
      {
        query1.append(" and  r.revenue_dt between  to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query1.append("and r.tsry_code in (select location_code from cmn_location_mst  where (location_code = '"+ locId + "' and lang_id = " + langId+") or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = '" + locId+ "' and lang_id = " +langId + "))");
      query1.append("group by r.revenue_dt");
      //System.out.println("SQL QUERY : " + query1.toString());
      sqlQuery1 = hibSession.createSQLQuery(query1.toString());
      List resList1 = sqlQuery1.list();
      int count = 1;
      if (resList1 != null) 
      {
        arrOuter = new ArrayList<ArrayList<Object>>();
        Iterator it = resList1.iterator();

        while(it.hasNext())
        {
          tuple1 = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          BigDecimal payment = null;
          BigDecimal receipt= (BigDecimal)(tuple1[1]);
          BigDecimal RBD = new BigDecimal(0);
          if(expDtls.containsKey(tuple1[0]))
          {
            arrInner.add(count);
            arrInner.add(tuple1[0]);
            payment = (BigDecimal)(expDtls.get(tuple1[0]));

            arrInner.add(df.format(payment));
            arrInner.add(df.format(receipt));

            if(payment.doubleValue() >= receipt.doubleValue())
            {
              RBD = payment.subtract(receipt) ;
              arrInner.add(df.format(RBD));
            }
            else
            {
              RBD = receipt.subtract(payment) ;
              arrInner.add(df.format(RBD));
            }
            expDtls.remove(tuple1[0]);

          }
          else 
          {
            arrInner.add(count);
            arrInner.add(tuple1[0]);
            arrInner.add(df.format( new BigDecimal(0.00)));
            arrInner.add(df.format(receipt));
            arrInner.add(df.format(receipt));
          }
          arrOuter.add(arrInner);
          count++;
        }
      }


      Iterator<Object> itr = expDtls.keySet().iterator();
      while(itr.hasNext())
      {
        Object Key = itr.next();
        ArrayList<Object> arrInner = new ArrayList<Object>(); 

        arrInner.add(count);
        BigDecimal payment = (BigDecimal)(expDtls.get(Key));
        arrInner.add(Key);
        arrInner.add(df.format(payment));
        arrInner.add(df.format(new BigDecimal(0)));
        BigDecimal RBD= payment ;
        arrInner.add(df.format(RBD));
        arrOuter.add(arrInner);
        count++;

      }


    }

    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n"+e);
    }

    return arrOuter;

  }


  public ArrayList<ArrayList<Object>> getTCMemoRegRpt(String fMjrHead, String tMjrHead , String fromDate,String toDate,String locId,String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;
    Map<Object, Object> cashDtls = new TreeMap<Object, Object>();
    try
    {
      Object[] tuple = null;
      Object[] tuple1 = null;
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      StringBuffer query1 = new StringBuffer();
      Query sqlQuery = null;
      Query sqlQuery1 = null;



      query.append("select r.mjr_hd,  sum(r.amount)from rpt_receipt_dtls r where r.dedctn_type='O' and r.rcpt_status_code='Approved' and r.active ='Y' ");
      if(fromDate != null && toDate != null)
      {
        query.append(" and r.revenue_dt between  to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query.append(" and r.tsry_code in (select location_code from cmn_location_mst  where (location_code = '"+ locId + "' and lang_id = " + langId+") or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = '" + locId+ "' and lang_id = " +langId + "))");
      query.append(" group by r.mjr_hd");




      query1.append("select r.mjr_hd, sum(r.amount)from rpt_receipt_dtls r where r.active = 'Y' and (r.dedctn_type = 'A' ) and r.rcpt_status_code = 'Approved' and");
      if(fromDate != null && toDate != null)
      {
        query1.append("  r.revenue_dt between  to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query1.append(" and r.tsry_code  in (select location_code from cmn_location_mst  where (location_code = '"+ locId + "' and lang_id = " + langId+") or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = '" + locId+ "' and lang_id = " +langId + "))");
      query1.append(" group by r.mjr_hd");



      //System.out.println("SQL QUERY : " + query.toString());
      //System.out.println("SQL QUERY : " + query1.toString());
      sqlQuery = hibSession.createSQLQuery(query.toString());
      List resList = sqlQuery.list();

      sqlQuery1 = hibSession.createSQLQuery(query1.toString());
      List resList1 = sqlQuery1.list();
      //System.out.println("Size of Cash :-"+resList.size());
      //System.out.println("Size of  Tc :-"+resList1.size());


      if (resList != null) 
      {	
        Iterator it = resList.iterator();
        while(it.hasNext())
        {
          tuple = (Object[])it.next();
          cashDtls.put(tuple[0],tuple[1]);
        }	
      }

      //System.out.println("size of Map:" +cashDtls.size());
      arrOuter = new ArrayList<ArrayList<Object>>();
      int count = 1;			
      if (resList1 != null) 
      {
        Iterator it = resList1.iterator();
        while(it.hasNext())
        {
          tuple1 = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          BigDecimal Cash = null;
          BigDecimal Tc = (BigDecimal)tuple1[1];

          BigDecimal Total = null;
          if(cashDtls.containsKey(tuple1[0]))
          {
            arrInner.add(count);
            arrInner.add(tuple1[0]);
            Cash = (BigDecimal)cashDtls.get(tuple1[0]);
            arrInner.add(df.format(Cash));
            arrInner.add(df.format(Tc));
            Total = Cash.add(Tc);
            arrInner.add(df.format(Total));
            cashDtls.remove(tuple1[0]);
          }
          else
          {
            arrInner.add(count);
            arrInner.add(tuple1[0]);
            arrInner.add(df.format(new BigDecimal(0)));
            arrInner.add(df.format(Tc));
            Total =  Tc;
            arrInner.add(df.format(Total));

          }
          arrInner.add(fromDate);
          arrInner.add(toDate);
          arrOuter.add(arrInner);
          count++;
        }
      }
      Iterator<Object> itr = cashDtls.keySet().iterator();


      while(itr.hasNext())
      {
        ArrayList<Object> arrInner = new ArrayList<Object>() ;
        arrInner.add(count);
        Object tupleTemp = itr.next();
        arrInner.add(tupleTemp);
        BigDecimal Cash = new BigDecimal(cashDtls.get(tupleTemp).toString());
        arrInner.add(df.format(Cash));
        arrInner.add(df.format(new BigDecimal(0)));
        arrInner.add(df.format(Cash));
        arrInner.add(fromDate);
        arrInner.add(toDate);
        arrOuter.add(arrInner);
        count++;
      }

    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n" +e);
    }

    return arrOuter;	
  }

  public ArrayList getSubTCMemoRpt(String fromDate,String toDate, String majorHead,String locId,String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = null;
    try
    {
      Session hibSession = getSession();
      StringBuffer query1 = new StringBuffer();

      Query sqlQuery1 = null;
      query1.append("select  rd.edp_code, max(em.edp_desc), sum(rd.amount) from rpt_receipt_dtls rd ,mst_edp em where rd.edp_code is not null and em.edp_code = rd.edp_code and rd.mjr_hd = '" +majorHead +"'  and rd.active = 'Y'  and rd.rcpt_status_code= 'Approved' and (rd.dedctn_type='A' ) and em.receipt_edp = 'Y' and rd.Amount > 0  and rd.revenue_dt between  to_date('"+ fromDate +"','dd-mm-yy')  and to_date('" +toDate +"','dd-mm-yy') and em.lang_id=" +langId+" and rd.tsry_code = '"+locId +"'  group by rd.edp_code");

      //System.out.println("SQL QUERY : " + query1.toString());
      sqlQuery1 = hibSession.createSQLQuery(query1.toString());

      List resList1 = sqlQuery1.list();
      if (resList1 != null) 
      {
        arrOuter = new ArrayList<ArrayList<Object>>();
        Iterator it = resList1.iterator();
        int count=1;
        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          ArrayList<Object> arrInner = new ArrayList<Object>();
          //arrInner.add(MajorHead);
          arrInner.add(count);
          arrInner.add(tuple[0]);
          arrInner.add(tuple[1]);
          arrInner.add(df.format(tuple[2]));
          arrOuter.add(arrInner);
          count=count+1;
        }	
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n" +e);
    }

    return arrOuter;
  }

  public ArrayList getTCMemoRegPayRpt(String fMjrHead, String tMjrHead , String fromDate,String toDate,String locId,String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = new ArrayList<ArrayList<Object>>();
    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      Query sqlQuery = null;
      query.append("select re.mjr_hd , sum(re.net_amt) , sum(re.dedctna_amt) , (sum(re.net_amt + re.dedctna_amt))  from rpt_expenditure_dtls re where re.exp_type_code = 'Bill' and re.active = 'Y'  and re.exp_status_code = 'DTL_PSTNG_DONE'");
      if(fromDate != null && toDate != null)
      {
        query.append("and re.exp_dt between to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      String sMajorHeadString = "";
      if(fMjrHead !=null && !fMjrHead.equals("-1"))
      {
        if(tMjrHead !=null && !tMjrHead.equals("-1"))
          sMajorHeadString = " and re.mjr_hd between '"+fMjrHead+"' and '" +tMjrHead+ "' ";
        else
          sMajorHeadString = " and re.mjr_hd between '"+fMjrHead+"' and '9999' ";

      }else
      {
        if(tMjrHead !=null && !tMjrHead.equals("-1"))
          sMajorHeadString = " and re.mjr_hd between '-1' and '" +tMjrHead+ "'  ";  
      }
      if(sMajorHeadString!=null) query.append(sMajorHeadString);
      query.append("and re.tsry_code in (select location_code from cmn_location_mst  where (location_code = :LocationCode and lang_id = :LangId ) or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = :LocationCode and lang_id = :LangId )) group by re.mjr_hd ");
      //System.out.println("SQL QUERY : " + query.toString());
      sqlQuery = hibSession.createSQLQuery(query.toString());
      sqlQuery.setString("LocationCode", locId);
      sqlQuery.setLong("LangId", new Long(langId));

      List res = sqlQuery.list();
      Iterator it = res.iterator();
      int count = 1;

      while(it.hasNext())
      {
        Object[] tuple = (Object[])it.next();
        ArrayList<Object> arrInner = new ArrayList<Object>();
        arrInner.add(count);
        arrInner.add(tuple[0]);
        arrInner.add(df.format(tuple[1]));
        arrInner.add(df.format((tuple[2]==null?0:tuple[2])));
        arrInner.add(df.format((tuple[3]==null?0:tuple[3])));
        arrInner.add(fromDate);
        arrInner.add(toDate);
        arrOuter.add(arrInner);
        count++;
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n" +e);
    }
    return arrOuter;
  }
  public ArrayList getSubTCMemoPayRpt(String fromDate,String toDate, String majorHead, String locId, String langId)
  {
    ArrayList<ArrayList<Object>> arrOuter = new ArrayList<ArrayList<Object>>();
    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();
      Query sqlQuery = null;
      query.append("select rd.edp_code, max(md.edp_desc), sum(rd.amount) from rpt_expenditure_dtls re , rpt_receipt_dtls rd, mst_edp md where re.exp_no = rd.rcpt_no and md.edp_code = rd.edp_code and md.receipt_edp  ='Y'");
      query.append("and re.exp_type_code  = 'Bill'");
      query.append("and re.tsry_code in (select location_code from cmn_location_mst  where (location_code = :LocationCode and lang_id = :LangId ) or  parent_loc_id in (select loc_id  from cmn_location_mst where location_code = :LocationCode and lang_id = :LangId ))");
      query.append(" and re.exp_status_code = 'DTL_PSTNG_DONE' and re.active = 'Y' and rd.active = 'Y' and rd.dedctn_type = 'A'");
      query.append("and re.mjr_hd='" +majorHead +"'");
      if(fromDate != null && toDate != null)
      {
        query.append("and re.exp_dt between to_date('" + fromDate + "','dd-mm-yy')  and to_date('" + toDate  + "','dd-mm-yy') ");
      }
      query.append("group by rd.edp_code");
      //System.out.println("SQL QUERY : " + query.toString());
      sqlQuery = hibSession.createSQLQuery(query.toString());
      sqlQuery.setString("LocationCode", locId);
      sqlQuery.setLong("LangId", new Long(langId));
      List res = sqlQuery.list();
      Iterator it = res.iterator();
      int count=1;
      while(it.hasNext())
      {
        Object[] tuple = (Object[])it.next();
        ArrayList<Object> arrInner =  new ArrayList<Object>();
        arrInner.add(count);
        arrInner.add(tuple[0]);
        arrInner.add(tuple[1]);
        arrInner.add(df.format(tuple[2]));
        arrOuter.add(arrInner);
        count++;
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getChequeDrawnRegisterRpt # \n" +e);
    }
    return arrOuter;
  }
  public String getLangName(Long LangId)
  {
    String langId = "";
    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();

      Query sqlQuery = null;
      query.append("select lang_short_name from cmn_language_mst where lang_id="+LangId );
      sqlQuery = hibSession.createSQLQuery(query.toString());
      List resList = sqlQuery.list();
      if (resList != null) 
      {
        Iterator it = resList.iterator();
        if(it.hasNext())
        {
          Object tuple = it.next();
          langId = tuple.toString();
        }	
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getLangName # \n" +e);
    }
    return langId;
  }

  public Long getLangId(String LangId)
  {
    Long langId = null;
    try
    {
      Session hibSession = getSession();
      StringBuffer query = new StringBuffer();

      Query sqlQuery = null;
      query.append("select  lang_id  from cmn_language_mst where lang_short_name = '" + LangId +"'");
      sqlQuery = hibSession.createSQLQuery(query.toString());

      List resList = sqlQuery.list();
      if (resList != null) 
      {
        Iterator it = resList.iterator();
        if(it.hasNext())
        {
          Object tuple = it.next();
          langId = Long.parseLong(tuple.toString());
        }	
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      glogger.error("Exception occured in ReportQueryDAOImpl.getLangId # \n" +e);
    }

    return langId;
  }
  public List getSubTsry(String sLocationCode)
  {
    try
    {
      Session hibSession = getSession();
      ArrayList<ComboValuesVO> oComboValues = new ArrayList<ComboValuesVO>();
      ComboValuesVO vo= new ComboValuesVO();
      vo.setId("0");
      vo.setDesc("-Select-");
      oComboValues.add(vo);

      Query query = hibSession.createSQLQuery(" select location_code, loc_name from cmn_location_mst where parent_loc_id='" +sLocationCode + "'");
      List subTsryList = query.list();

      if(subTsryList!=null)
      {
        Iterator it = subTsryList.iterator();
        while(it.hasNext())
        {
          Object[] tuple = (Object[])it.next();
          vo= new ComboValuesVO();
          vo.setId(tuple[0].toString());
          vo.setDesc(tuple[1].toString());
          oComboValues.add(vo);
        }	
      }
      return oComboValues;
    }
    catch(Exception e)
    {
      glogger.error("Exception occured  #\n"+e);
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList getSubTreasuryYN(String lStrLangId, String lstrLocId) 
  {
    ArrayList<ComboValuesVO> arrBillMode = new ArrayList<ComboValuesVO>( );
    //System.out.println("I am Here for getting value");
    try
    {
      ComboValuesVO vo = new ComboValuesVO();
      String yesId = "0";
      String yesName = "Yes";
      vo.setId(yesId);
      vo.setDesc(yesName);
      arrBillMode.add(vo);
      ComboValuesVO vo1 = new ComboValuesVO();
      String yesId1 = "1";
      String yesName1 = "No";
      vo1.setId(yesId1);
      vo1.setDesc(yesName1);
      arrBillMode.add(vo1);
    }
    catch( Exception e )
    {
      glogger.error( "Exception::"+e.getMessage(), e );
    }
    return arrBillMode;
  }
  public List getSubTreasury(String locCode)
  {
    Session hibSession = getSession();
    ArrayList<CmnLocationMst> dataList = new ArrayList<CmnLocationMst>();
    String query = "select location_code, loc_name from cmn_location_mst where parent_loc_id='" +locCode + "'";
    Iterator subTreasury= hibSession.createSQLQuery(query.toString()).list().iterator();

    CmnLocationMst subTreasuryVO = new CmnLocationMst();
    subTreasuryVO.setLocationCode("-1");
    subTreasuryVO.setLocName("-Select-");
    dataList.add(subTreasuryVO);
    while (subTreasury.hasNext())
    {
      subTreasuryVO = new CmnLocationMst();
      Object[] row = (Object[])subTreasury.next();
      subTreasuryVO.setLocationCode(row[0].toString());
      subTreasuryVO.setLocName(row[1].toString());
      dataList.add(subTreasuryVO);
    }
    return(dataList);
  }
  public List getOutstandingBalanceReport(String sYear,String sMonth,String sLocation_code)
  {

    Session hibSession = getSession();
    SQLQuery sQuery;
    ArrayList<ArrayList<Object>> oDataList = new ArrayList<ArrayList<Object>>();
    java.util.Calendar cln = Calendar.getInstance();
    ArrayList<BigDecimal> listAmounts = new ArrayList<BigDecimal>();
    BigDecimal oAmount = null;
    cln.set(Integer.parseInt(sYear), Integer.parseInt(sMonth)-1, 1);
    Date oStartDate = cln.getTime();
    int maxDay = cln.getActualMaximum(Calendar.DAY_OF_MONTH);
    cln.set(Calendar.DAY_OF_MONTH, maxDay);
    Date oEndDate = cln.getTime();

    cln.add(Calendar.MONTH,-1);
    maxDay = cln.getActualMaximum(Calendar.DAY_OF_MONTH);
    cln.set(Calendar.DAY_OF_MONTH, maxDay);
    Date oLastMonthEndDate = cln.getTime();

    cln.add(Calendar.MONTH, -4);
    Date oLapsedDate = cln.getTime();

    String strQueryRow1 = " select sum(tc.cheque_amt) from trn_cheque_dtls tc where cleared_dt is null and status = 'CHQ_DSPTCH_DDO' " +
    " and tc.advice_dt <= ? and tc.location_code = ? ";

    String strQueryRow2 = " select sum(tc.cheque_amt) from trn_cheque_dtls tc where  status = 'CHQ_DSPTCH_DDO' " +
    " and tc.advice_dt between ? and ? and tc.location_code = ? ";

    String strQueryRow3 = " select sum(tc.cheque_amt) from trn_cheque_dtls tc where  status = 'CHQ_DSPTCH_DDO' " +
    "	and tc.cleared_dt between ? and ? and tc.location_code = ? " ;


    String strQueryRow4 = " select sum(tc.cheque_amt) from trn_cheque_dtls tc where  status = 'CHQ_DSPTCH_DDO' " +
    " and tc.cleared_dt is null and tc.advice_dt <=  ? and tc.location_code = ? ";


//  Row 1

    sQuery =  hibSession.createSQLQuery(strQueryRow1);
    sQuery.setDate(0,oLastMonthEndDate );
    sQuery.setString(1, sLocation_code);
    sQuery.scroll(ScrollMode.FORWARD_ONLY);

    List lResult = sQuery.list();

    if(lResult != null) 
    {	 oAmount = (BigDecimal)lResult.get(0);
    if(oAmount == null) oAmount = new BigDecimal(0);
    listAmounts.add(oAmount); // 0
    ArrayList<Object> oRow = new ArrayList<Object>();
    oRow.add("Unpaid Cheques Outstanding as on " + oDateFormat.format(oLastMonthEndDate) );
    oRow.add(df.format(oAmount));
    oDataList.add(oRow);
    }
//  Row 2 
    sQuery =  hibSession.createSQLQuery(strQueryRow2);
    sQuery.setDate(0,oStartDate);
    sQuery.setDate(1,oEndDate );
    sQuery.setString(2, sLocation_code);
    sQuery.scroll(ScrollMode.FORWARD_ONLY);

    lResult = sQuery.list();

    if(lResult != null) 
    {	 oAmount = (BigDecimal)lResult.get(0);
    if(oAmount == null) oAmount = new BigDecimal(0);
    listAmounts.add(oAmount);  //1
    ArrayList<Object> oRow = new ArrayList<Object>();
    oRow.add("Cheques Delivered during the Month of " + new SimpleDateFormat("mm/yy").format(oStartDate) );
    oRow.add(df.format(oAmount) );
    oDataList.add(oRow);

    }
//  Row 3
    ArrayList<Object> oRow = new ArrayList<Object>();
    oRow.add("Cash Recovery"); oRow.add(df.format(new BigDecimal(0)));
    oDataList.add(oRow);


    // Row 4
    oRow = new ArrayList<Object>();
    oRow.add("Sub Total 1 "); 
    BigDecimal Row1 = listAmounts.get(0);
    BigDecimal Row2 = listAmounts.get(1);
    BigDecimal Row4 =Row1.add(Row2);
    oRow.add(df.format(Row4));
    oDataList.add(oRow);
    listAmounts.add(Row4);  //2

//  Row 5
    sQuery =  hibSession.createSQLQuery(strQueryRow3);
    sQuery.setDate(0,oStartDate);
    sQuery.setDate(1,oEndDate );
    sQuery.setString(2, sLocation_code);
    sQuery.scroll(ScrollMode.FORWARD_ONLY);

    lResult = sQuery.list();

    if(lResult != null) 
    {	 oAmount = (BigDecimal)lResult.get(0);
    if(oAmount == null) oAmount = new BigDecimal(0);
    listAmounts.add(oAmount);  //3
    oRow = new ArrayList<Object>();
    oRow.add("Cheques encashed during the Month  " + new SimpleDateFormat("MM/yy").format(oStartDate) );
    oRow.add(df.format(oAmount) );
    oDataList.add(oRow);

    }
//  Row 6
    oRow = new ArrayList<Object>();
    oRow.add("Sub Total 2 "); 
    BigDecimal oRow4 = listAmounts.get(2);
    BigDecimal oRow5 = listAmounts.get(3);
    BigDecimal Row6 =oRow4.subtract(oRow5);
    oRow.add(df.format(Row6));
    listAmounts.add(Row6);  // 4;
    oDataList.add(oRow);

//  Row 7 
    oRow = new ArrayList<Object>();
    oRow.add("Cash Expenditure "); 
    oRow.add(df.format(new BigDecimal(0)));
    oDataList.add(oRow);

//  Row 8
    oRow = new ArrayList<Object>();
    oRow.add("Sub Total 3 "); oRow.add(df.format(listAmounts.get(4)));
    oDataList.add(oRow);

//  Row 9
    oAmount = new BigDecimal(0);
    oRow = new ArrayList<Object>();
    oRow.add("Over Payment during the month    " + new SimpleDateFormat("MM/yy").format(oStartDate) );
    oRow.add(df.format(oAmount) );
    oDataList.add(oRow);


    // Row 10
    oRow = new ArrayList<Object>();
    oRow.add("Sub Total 4 "); 

    BigDecimal oRow9 = listAmounts.get(4);
    oRow.add(df.format(oRow9));
    oDataList.add(oRow);

    // Row 11
    oAmount = new BigDecimal(0);
    oRow = new ArrayList<Object>();
    oRow.add("Less : Payment during the month  " + new SimpleDateFormat("MM/yy").format(oStartDate) );
    oRow.add(df.format(oAmount) );
    oDataList.add(oRow);


//  Row 12
    oRow = new ArrayList<Object>();
    oRow.add("Sub Total 5 "); 
    oRow9 = listAmounts.get(4);;
    oRow.add(df.format(oRow9));
    oDataList.add(oRow);


    // Row 13
    sQuery =  hibSession.createSQLQuery(strQueryRow4);
    sQuery.setDate(0,oLapsedDate);
    sQuery.setString(1, sLocation_code);
    sQuery.scroll(ScrollMode.FORWARD_ONLY);

    lResult = sQuery.list();

    if(lResult != null)  
    {	 oAmount = (BigDecimal)lResult.get(0);
    if(oAmount == null ) oAmount = new BigDecimal(0);
    listAmounts.add(oAmount);  // 5
    oRow = new ArrayList<Object>();
    oRow.add("	Less : Lapsed cheques up to the Month  " + new SimpleDateFormat("MM/yy").format(oLapsedDate) );
    oRow.add(df.format(oAmount) );
    oDataList.add(oRow); // 10
    }

//  Row 14
    oRow = new ArrayList<Object>();
    oRow.add("Total Outstanding cheques up to the month " + new SimpleDateFormat("MM/yy").format(oStartDate)); 
    BigDecimal oRow7 = listAmounts.get(4);
    BigDecimal oRow8 = listAmounts.get(5);
    oRow9 = oRow7.subtract(oRow8);
    oRow.add(df.format(oRow9));
    oDataList.add(oRow);

    return oDataList;
  }
}

