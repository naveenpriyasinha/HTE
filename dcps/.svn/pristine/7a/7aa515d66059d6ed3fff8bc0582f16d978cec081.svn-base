package com.tcs.sgv.nps.report;
 

import com.tcs.sgv.common.exception.reports.ReportException;
//import com.tcs.sgv.nps.service.DcpsCommonDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import com.tcs.sgv.common.valuebeans.reports.StyledData;

import java.util.HashMap;
import java.util.ResourceBundle;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.core.service.ServiceLocator;
import org.hibernate.SessionFactory;
import java.util.Map;
import org.apache.log4j.Logger;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;

public class NsdlNpsFileEmpWiseReport extends DefaultReportDataFinder
{
    private static final Logger gLogger;
    String Lang_Id;
    String Loc_Id;
    Map requestAttributes;
    SessionFactory lObjSessionFactory;
    ServiceLocator serviceLocator;
    
    static {
        gLogger = Logger.getLogger((Class)NsdlNpsFileEmpWiseReport.class);
    }
    
    public NsdlNpsFileEmpWiseReport() {
        this.Lang_Id = "en_US";
        this.Loc_Id = "LC1";
        this.requestAttributes = null;
        this.lObjSessionFactory = null;
        this.serviceLocator = null;
    }
    
    public Collection findReportData(final ReportVO report, final Object criteria) throws ReportException {
        final String locId = report.getLocId();
        Connection con = null;
        criteria.getClass();
        Statement smt = null;
        ResultSet rs = null;
        final ArrayList dataList = new ArrayList();
        final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final StyleVO[] CenterAlignVO = { new StyleVO(), null };
        CenterAlignVO[0].setStyleId(40);
        CenterAlignVO[0].setStyleValue("center");
        (CenterAlignVO[1] = new StyleVO()).setStyleId(1);
        CenterAlignVO[1].setStyleValue("none");
        final StyleVO[] noBorder = { new StyleVO() };
        noBorder[0].setStyleId(1);
        noBorder[0].setStyleValue("none");
        final StyleVO[] leftboldStyleVO = { new StyleVO(), null, null };
        leftboldStyleVO[0].setStyleId(37);
        leftboldStyleVO[0].setStyleValue("normal");
        (leftboldStyleVO[1] = new StyleVO()).setStyleId(40);
        leftboldStyleVO[1].setStyleValue("Left");
        (leftboldStyleVO[2] = new StyleVO()).setStyleId(1);
        leftboldStyleVO[2].setStyleValue("none");
        try {
        	System.out.println("Scienctist ");
            this.requestAttributes = (Map) ((Map)criteria).get("REQUEST_ATTRIBUTES");
            this.serviceLocator = (ServiceLocator) this.requestAttributes.get("serviceLocator");
            this.lObjSessionFactory = this.serviceLocator.getSessionFactorySlave();
            final Map requestAttributes = (Map) ((Map)criteria).get("REQUEST_ATTRIBUTES");
            final ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
            final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/dcpsLabels");
            final SessionFactory lObjSessionFactory = serviceLocator.getSessionFactorySlave();
            con = lObjSessionFactory.getCurrentSession().connection();
            smt = con.createStatement();
            final Map sessionKeys = (Map) ((Map)criteria).get("SESSION_KEYS");
            final Map loginDetail = (Map) sessionKeys.get("loginDetailsMap");
            final Long locationId = (Long) loginDetail.get("locationId");
            String StrSqlQuery = "";
            if (report.getReportCode().equals("80000941")) { 
            	System.out.println("If Scienctist success");
                ArrayList rowList = new ArrayList();
                final String fileNo = (report.getParameterValue("fileNo")!=null) ? report.getParameterValue("fileNo").toString() : "";//report.getParameterValue("fileNo").toString();
                final String year = (String)report.getParameterValue("year");
                final String month = (String)report.getParameterValue("month");
                final String lStrDDOCode = null;
                final String lStrDDOName = "";
                //final DcpsCommonDAO lObjDcpsCommonDAO = (DcpsCommonDAO)new DcpsCommonDAOImpl((Class)null, serviceLocator.getSessionFactory());
                String url = "";
                url = "ifms.htm?actionFlag=loadNPSNSDLGenFiles&cmbYear=" + year + "&cmbMonth=" + month;
                StyleVO[] lObjStyleVO = new StyleVO[report.getStyleList().length];
                lObjStyleVO = report.getStyleList();
                for (Integer lInt = 0; lInt < report.getStyleList().length; ++lInt) {
                    if (lObjStyleVO[lInt].getStyleId() == 26) {
                        lObjStyleVO[lInt].setStyleValue(url);
                    }
                } 
                final StringBuilder SBQuery = new StringBuilder();
                /*SBQuery.append("SELECT distinct emp.SEVARTH_ID,emp.EMP_NAME,sd.SD_PRAN_NO,emp.DDO_CODE,sd.DDO_REG_NO,sd.SD_REMARK,sd.SD_EMPLR_AMOUNT,sd.SD_EMP_AMOUNT,sd.SD_TOTAL_AMT,case when emp.SUPER_ANN_DATE > sysdate then 'Employee Not Retired' else 'Employee Retired' end  FROM MST_DCPS_EMP emp ");
                SBQuery.append("INNER JOIN NSDL_SD_DTLS sd ON sd.SD_PRAN_NO = emp.PRAN_NO  ");
                SBQuery.append("INNER JOIN NSDL_BH_DTLS bh ON bh.FILE_NAME = sd.FILE_NAME AND bh.STATUS <> '-1' AND sd.FILE_NAME = '" + fileNo + "'");*/
               /* SBQuery.append("SELECT  temp.SEVARTH_ID,temp.EMP_NAME,temp.SD_PRAN_NO,temp.DDO_CODE,temp.DDO_REG_NO,sum(cast(temp.employer_amt as bigint)),"
                		+ "sum(cast(temp.employee_amount as bigint)) ,sum(cast(temp.employer_amt_int as bigint)),sum(cast(temp.employee_amount_int as bigint)),"
                		+ "sum(cast(temp.SD_TOTAL_AMT as bigint)),temp.employee_status, date(temp.CONTRI_START_DATE),date(temp.CONTRI_END_DATE) FROM "
                		+ "( SELECT distinct emp.SEVARTH_ID as SEVARTH_ID,emp.EMP_NAME as EMP_NAME,sd.SD_PRAN_NO as SD_PRAN_NO,emp.DDO_CODE as DDO_CODE,"
                		+ "sd.DDO_REG_NO as DDO_REG_NO,sd.SD_EMPLR_AMOUNT as employer_amt,sd.SD_EMP_AMOUNT as employee_amount,"
                		+ "0 as employer_amt_int,0 as employee_amount_int, sd.SD_TOTAL_AMT,case when emp.SUPER_ANN_DATE > sysdate then 'Employee Not Retired' "
                		+ "else 'Employee Retired' end  as employee_status, legacy.CONTRI_START_DATE as CONTRI_START_DATE,legacy.CONTRI_END_DATE as CONTRI_END_DATE FROM ifms.MST_DCPS_EMP emp "
                		+ "INNER JOIN ifms.NSDL_SD_DTLS sd ON sd.SD_PRAN_NO = emp.PRAN_NO INNER JOIN ifms.NSDL_BH_DTLS bh ON bh.FILE_NAME = sd.FILE_NAME AND bh.STATUS <> '-1' AND sd.FILE_NAME = '" + fileNo + "'"
                		+ " and MOD(sd.SD_NO_3,2) <>0 inner join ifms.DCPS_LEGACY_DATA as legacy on legacy.BATCH_ID=substr(BH.FILE_NAME,2,length(BH.FILE_NAME))  union all SELECT distinct emp.SEVARTH_ID as SEVARTH_ID,emp.EMP_NAME as EMP_NAME,sd.SD_PRAN_NO as SD_PRAN_NO,"
                		+ "emp.DDO_CODE as DDO_CODE,sd.DDO_REG_NO as DDO_REG_NO, 0 as employer_amt,0 as employee_amount, sd.SD_EMPLR_AMOUNT as employer_amt_int,"
                		+ "sd.SD_EMP_AMOUNT as employee_amount_int, SD_TOTAL_AMT as SD_TOTAL_AMT,case when emp.SUPER_ANN_DATE > sysdate then 'Employee Not Retired' "
                		+ "else 'Employee Retired' end as employee_status, legacy.CONTRI_START_DATE as CONTRI_START_DATE,legacy.CONTRI_END_DATE as CONTRI_END_DATE FROM ifms.MST_DCPS_EMP emp INNER JOIN ifms.NSDL_SD_DTLS sd ON sd.SD_PRAN_NO = emp.PRAN_NO "
                		+ "INNER JOIN ifms.NSDL_BH_DTLS bh ON bh.FILE_NAME = sd.FILE_NAME AND bh.STATUS <> '-1' AND sd.FILE_NAME = '" + fileNo + "' "
                		+ "and MOD(sd.SD_NO_3,2) =0 inner join ifms.DCPS_LEGACY_DATA as legacy on legacy.BATCH_ID=substr(BH.FILE_NAME,2,length(BH.FILE_NAME)) ) as temp group by temp.SEVARTH_ID,temp.EMP_NAME,temp.SD_PRAN_NO,temp.DDO_CODE,temp.DDO_REG_NO,temp.employee_status, temp.CONTRI_START_DATE,temp.CONTRI_END_DATE");
               */
                SBQuery.append("SELECT distinct  temp.SEVARTH_ID,temp.EMP_NAME,temp.SD_PRAN_NO,temp.DDO_CODE,temp.DDO_REG_NO, sum(decimal(temp.employer_amt,16,2)),"
                		+ " sum(decimal(temp.employee_amount,16,2)) ,  sum(decimal(temp.employer_amt_int,16,2)),sum(decimal(temp.employee_amount_int,16,2)) , "
                		+ "sum(decimal(temp.SD_TOTAL_AMT,16,2)) ,temp.employee_status, date(temp.CONTRI_START_DATE), date(temp.CONTRI_END_DATE) FROM "
                		+ "(SELECT distinct emp.SEVARTH_ID as SEVARTH_ID,emp.EMP_NAME as EMP_NAME,sd.SD_PRAN_NO as SD_PRAN_NO,emp.DDO_CODE as DDO_CODE,"
                		+ "sd.DDO_REG_NO as DDO_REG_NO,sd.SD_EMPLR_AMOUNT as employer_amt, sd.SD_EMP_AMOUNT as employee_amount,0 as employer_amt_int,"
                		+ "0 as employee_amount_int, sd.SD_TOTAL_AMT,case when emp.SUPER_ANN_DATE > sysdate then 'Employee Not Retired' else 'Employee Retired' end "
                		+ " as employee_status, legacy.CONTRI_START_DATE as CONTRI_START_DATE,legacy.CONTRI_END_DATE as CONTRI_END_DATE FROM ifms.MST_DCPS_EMP emp "
                		+ "inner join ifms.DCPS_LEGACY_DATA as legacy on legacy.SEVARTH_ID=emp.SEVARTH_ID INNER JOIN ifms.NSDL_BH_DTLS bh ON "
                		+ "legacy.BATCH_ID=substr(BH.FILE_NAME,2,length(BH.FILE_NAME)) INNER JOIN  ifms.NSDL_SD_DTLS sd ON sd.SD_PRAN_NO = emp.PRAN_NO and "
                		+ " bh.FILE_NAME = sd.FILE_NAME  where bh.STATUS <> '-1' AND sd.FILE_NAME = '" + fileNo + "' and bh.IS_LEGACY_DATA='Y' and MOD(sd.SD_NO_3,2) <>0 "
                		+ " union all  SELECT distinct emp.SEVARTH_ID as SEVARTH_ID,emp.EMP_NAME as EMP_NAME,sd.SD_PRAN_NO as SD_PRAN_NO,emp.DDO_CODE as DDO_CODE,"
                		+ "sd.DDO_REG_NO as DDO_REG_NO, 0 as employer_amt,0 as employee_amount, sd.SD_EMPLR_AMOUNT as employer_amt_int,sd.SD_EMP_AMOUNT "
                		+ "as employee_amount_int, SD_TOTAL_AMT as SD_TOTAL_AMT,case when emp.SUPER_ANN_DATE > sysdate then 'Employee Not Retired' else"
                		+ " 'Employee Retired' end as employee_status, legacy.CONTRI_START_DATE as CONTRI_START_DATE,legacy.CONTRI_END_DATE as CONTRI_END_DATE "
                		+ "FROM ifms.MST_DCPS_EMP emp inner join ifms.DCPS_LEGACY_DATA as legacy on legacy.SEVARTH_ID=emp.SEVARTH_ID INNER JOIN ifms.NSDL_BH_DTLS"
                		+ " bh ON legacy.BATCH_ID=substr(BH.FILE_NAME,2,length(BH.FILE_NAME)) INNER JOIN  ifms.NSDL_SD_DTLS sd ON sd.SD_PRAN_NO = emp.PRAN_NO and"
                		+ "  bh.FILE_NAME = sd.FILE_NAME where bh.STATUS <> '-1' AND sd.FILE_NAME = '" + fileNo + "'  and bh.IS_LEGACY_DATA='Y' and MOD(sd.SD_NO_3,2) =0 ) as "
                		+ "temp group by temp.SEVARTH_ID,temp.EMP_NAME,temp.SD_PRAN_NO,temp.DDO_CODE,temp.DDO_REG_NO,temp.employee_status,temp.CONTRI_START_DATE,"
                		+ "temp.CONTRI_END_DATE");
                
                
                NsdlNpsFileEmpWiseReport.gLogger.info((Object)("StrSqlQuery***********" + SBQuery.toString()));
                StrSqlQuery = SBQuery.toString();
                rs = smt.executeQuery(StrSqlQuery);
                Integer counter = 1;
                while (rs.next()) {
                    rowList = new ArrayList();
                    rowList.add(new StyledData((Object)counter, CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(1), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(2), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(3), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(4), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(5), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(12), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(13), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(6), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(7), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(8), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(9), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(10), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(11), CenterAlignVO));
                    dataList.add(rowList);
                    ++counter;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            NsdlNpsFileEmpWiseReport.gLogger.error((Object)("Exception :" + e), (Throwable)e);
            try {
                if (smt != null) {
                    smt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
                smt = null;
                rs = null;
                con = null;
            }
            catch (Exception e2) {
                e2.printStackTrace();
                NsdlNpsFileEmpWiseReport.gLogger.error((Object)("Exception :" + e2), (Throwable)e2);
            }
            return dataList;
        }
        finally {
            try {
                if (smt != null) {
                    smt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
                smt = null;
                rs = null;
                con = null;
            }
            catch (Exception e2) {
                e2.printStackTrace();
                NsdlNpsFileEmpWiseReport.gLogger.error((Object)("Exception :" + e2), (Throwable)e2);
            }
        }
        try {
            if (smt != null) {
                smt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
            smt = null;
            rs = null;
            con = null;
        }
        catch (Exception e2) {
            e2.printStackTrace();
            NsdlNpsFileEmpWiseReport.gLogger.error((Object)("Exception :" + e2), (Throwable)e2);
        }
        return dataList;
    }
    
    public String space(final int noOfSpace) {
        String blank = "";
        for (int i = 0; i < noOfSpace; ++i) {
            blank = String.valueOf(blank) + " ";
        }
        return blank;
    }
}
