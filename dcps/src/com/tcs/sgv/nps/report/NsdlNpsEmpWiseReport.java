package com.tcs.sgv.nps.report;



import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.helper.SessionHelper;

import org.hibernate.SQLQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import com.tcs.sgv.nps.dao.NsdlNpsDAOImpl;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
//import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.core.service.ServiceLocator;
import org.hibernate.SessionFactory;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;

public class NsdlNpsEmpWiseReport extends DefaultReportDataFinder
{
    private static final Logger gLogger;
    String Lang_Id;
    String Loc_Id;
    Map requestAttributes;
    SessionFactory lObjSessionFactory;
    ServiceLocator serviceLocator;
    
    static {
        gLogger = Logger.getLogger((Class)NsdlNpsEmpWiseReport.class);
    }
    /* Global Variable for Logger Class */
	//private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrLocationCode = null;

	private Long gLngPostId = null;

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private String gStrUserId = null; /* STRING USER ID */

	private HttpServletResponse response= null;/* RESPONSE OBJECT*/
	/* Global Variable for UserId */
	Long gLngUserId = null;

	private Long gLngDBId = null; /* DB ID */
    private void setSessionInfo(Map inputMap) {

		try {
			response = (HttpServletResponse) inputMap.get("responseObj");
			request = (HttpServletRequest) inputMap.get("requestObj");
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			session = request.getSession();
			gStrPostId = SessionHelper.getPostId(inputMap).toString();
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngUserId = SessionHelper.getUserId(inputMap);
			gLogger.info(" gLngUserId ssssss : " + gLngUserId);
			gStrUserId = gLngUserId.toString();
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gLogger.info("gStrLocationCode++++++++++++++"+gStrLocationCode);

		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}
	}
    
    public NsdlNpsEmpWiseReport() {
        this.Lang_Id = "en_US";
        this.Loc_Id = "LC1";
        this.requestAttributes = null;
        this.lObjSessionFactory = null;
        this.serviceLocator = null;
    }
    
    public Collection findReportData(final ReportVO report, final Object criteria,Map inputMap) throws ReportException {
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
        Map loginMap = (Map)inputMap.get("baseLoginMap");
       // setSessionInfo(inputMap);
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
            String ddoCode="";
            if (report.getReportCode().equals("80000955")) { 
            	System.out.println("If Scienctist success");
                ArrayList rowList = new ArrayList();
                final String fileNo = (report.getParameterValue("fileNo")!=null) ? report.getParameterValue("fileNo").toString() : "";//report.getParameterValue("fileNo").toString();
                final String year = (String)report.getParameterValue("year");
                final String month = (String)report.getParameterValue("month");
                final String lStrDDOCode = null;
                final String lStrDDOName = "";
                DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serviceLocator.getSessionFactory());
                if(gLngPostId != null) {
                	ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
                }else {
                	ddoCode="2222222222";
                }
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
                SBQuery.append("SELECT distinct emp.SEVARTH_ID as SEVARTH_ID,emp.EMP_NAME as EMP_NAME,emp.PRAN_NO as PRAN_NO,emp.DDO_CODE as DDO_CODE, sd.DDO_REG_NO as DDO_REG_NO,");
                SBQuery.append("sd.SD_EMPLR_AMOUNT as employer_amt, sd.SD_EMP_AMOUNT as employee_amount,(sd.SD_EMPLR_AMOUNT+sd.SD_EMP_AMOUNT) as total_amt,case when emp.SUPER_ANN_DATE > sysdate then 'Employee Not Retired' else ");
                SBQuery.append(" 'Employee Retired' end   as  employee_status,bh.month||'/'||bh.year FROM ifms.MST_DCPS_EMP emp  inner join ifms.TRN_DCPS_CONTRIBUTION as trn  ");
                SBQuery.append(" on emp.DCPS_EMP_ID=trn.DCPS_EMP_ID INNER JOIN ifms.NSDL_BH_DTLS bh ON  bh.DDO_CODE='"+ddoCode+"'"); 
                if(ddoCode.equals("2222222222")) {
                	SBQuery.append(" and trn.treasury_code='5401' ");
                }else {
                	SBQuery.append(" and trn.treasury_code='"+ddoCode.substring(1,4)+"' ");
                }
                SBQuery.append(" INNER JOIN  ifms.NSDL_SD_DTLS sd  ON sd.SD_PRAN_NO = emp.PRAN_NO and  bh.FILE_NAME = sd.FILE_NAME  where  bh.IS_LEGACY_DATA='N' and bh.FILE_NAME = '"+fileNo+"'");
                SBQuery.append(" and bh.YEAR="+year+" and bh.MONTH="+month );
                SBQuery.append(" and trn.VOUCHER_NO is not null and trn.VOUCHER_DATE is not null order by emp.EMP_NAME");
                
                
                NsdlNpsEmpWiseReport.gLogger.info((Object)("StrSqlQuery***********" + SBQuery.toString()));
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
                    rowList.add(new StyledData((Object)rs.getString(10), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(6), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(7), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(6), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(8), CenterAlignVO));
                    rowList.add(new StyledData((Object)rs.getString(9), CenterAlignVO));
                   
                 //   rowList.add(new StyledData((Object)rs.getString(11), CenterAlignVO));
                    dataList.add(rowList);
                    ++counter;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            NsdlNpsEmpWiseReport.gLogger.error((Object)("Exception :" + e), (Throwable)e);
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
                NsdlNpsEmpWiseReport.gLogger.error((Object)("Exception :" + e2), (Throwable)e2);
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
                NsdlNpsEmpWiseReport.gLogger.error((Object)("Exception :" + e2), (Throwable)e2);
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
            NsdlNpsEmpWiseReport.gLogger.error((Object)("Exception :" + e2), (Throwable)e2);
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
