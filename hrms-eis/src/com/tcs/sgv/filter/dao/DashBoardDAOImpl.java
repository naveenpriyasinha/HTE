package com.tcs.sgv.filter.dao;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class DashBoardDAOImpl extends GenericDaoHibernateImpl
{
    public DashBoardDAOImpl(final Class<OrgUserMst> type, final SessionFactory sessionFactory)
    {
        super(type);
        this.setSessionFactory(sessionFactory);
    }

    public String calculatePercentage(final long lngTotalNoOfOfficesCovered, final String count)
    {
        // TODO Auto-generated method stub
        String percentage = "";
        try
        {
            final DecimalFormat df = new DecimalFormat("0.00");
            final double total = lngTotalNoOfOfficesCovered;
            final double countOfSchools = Double.parseDouble(count);
            percentage = df.format((countOfSchools / total * 100)).concat("%");
            this.logger.info("calculated percentage: " + percentage);
        } catch (final Exception e)
        {
            // TODO: handle exception
        }
        return percentage;
    }

    // chart 5 popup
    public String getDisplayAmountDisbursedForFY1415DivisionWise(final String division)
    {

        final Session session = this.getSession();
        List lstDisplayAmountDisbursedForFY1415DivisionWise = null;
        final StringBuffer strDisplayAmountDisbursedForFY1415DivisionWise = new StringBuffer();
        final StringBuffer sb = new StringBuffer();
        long totalCount04_14 = 0;
        long totalCount05_14 = 0;
        long totalCount06_14 = 0;
        long totalCount07_14 = 0;
        long totalCount08_14 = 0;
        long totalCount09_14 = 0;
        long totalCount10_14 = 0;
        long totalCount11_14 = 0;
        long totalCount12_14 = 0;
        long totalCount01_15 = 0;
        long totalCount02_15 = 0;
        long totalCount03_15 = 0;

        // //commented to use cron job table
        /*
         * sb.append(" SELECT region.REGION_NAME||'##'||dist.DISTRICT_NAME||'##'||admin.ADMIN_NAME||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 4 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 5 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 6 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 7 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 8 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 9 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 10 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 11 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 12 and pay.PAYBILL_YEAR=2014 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 1 and pay.PAYBILL_YEAR=2015 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 2 and pay.PAYBILL_YEAR=2015 then pay.BILL_NET_AMOUNT  else 0 end)||'##'|| "
         * );sb.append(
         * " sum(case when pay.PAYBILL_MONTH = 3 and pay.PAYBILL_YEAR=2015 then pay.BILL_NET_AMOUNT  else 0 end) "
         * ); sb.append(" FROM rlt_zp_ddo_map rlt ");
         * sb.append(" inner join ORG_DDO_MST ddo on rlt.zp_ddo_code=ddo.ddo_code "
         * );sb.append(
         * " inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code and upper(off.ddo_office)='YES' "
         * );sb.append(
         * " inner join paybill_head_mpg pay on ddo.LOCATION_CODE=pay.LOC_ID and pay.APPROVE_FLAG in(0,1) "
         * );sb.append(
         * " inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID "
         * );sb.append(
         * " inner join ZP_REGION_NAME_MST region on dist.REGION_CODE=region.REGION_CODE "
         * );
         * sb.append(" INNER join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.ID "
         * ); sb.append(" where rlt.STATUS in(0,1) ");sb.append(
         * " and ((pay.paybill_month in (1,2,3) and pay.paybill_year = 2015) or (pay.paybill_month in (4,5,6,7,8,9,10,11,12) and pay.paybill_year = 2014)) "
         * );
         * sb.append(" and upper(region.REGION_NAME)='"+division.toUpperCase()
         * +"' ");sb.append(
         * " group by  region.REGION_NAME ,dist.DISTRICT_NAME,admin.ADMIN_NAME "
         * );
         */

        sb.append(" SELECT dashReport.DESCRIPTION||'##'||dashReport.data FROM DASHBOARD_REPORT dashReport ");
        sb.append(" where dashReport.CHART_ID = '5' and upper(dashReport.DESCRIPTION) = '" + division.toUpperCase()
                + "' order by dashReport.CHART_SUB_ID ");

        this.logger.info("Query to get popup details of amount disbursed for FY 2014-2015: " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstDisplayAmountDisbursedForFY1415DivisionWise = query.list();
        if (lstDisplayAmountDisbursedForFY1415DivisionWise.size() > 0)
        {
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<html><body>");
            strDisplayAmountDisbursedForFY1415DivisionWise
                    .append("<center><b>Divisionwise district and admin office and amount disbursed of: " + division
                            + "</b></center>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<table border=\"1\" width=\"100%\"><tr>");

            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>Division</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>District and Admin Office</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise
                    .append("<td colspan=\"12\">Amount Disbursed in  FY 2014/2015</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("</tr>");

            strDisplayAmountDisbursedForFY1415DivisionWise.append("<tr>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>&nbsp;</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>&nbsp;</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>04/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>05/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>06/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>07/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>08/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>09/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>10/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>11/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>12/2014</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>01/2015</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>02/2015</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>03/2015</td>");
            strDisplayAmountDisbursedForFY1415DivisionWise.append("</tr>");

            for (int i = 0; i < lstDisplayAmountDisbursedForFY1415DivisionWise.size(); i++)
            {
                final String tmp[] = lstDisplayAmountDisbursedForFY1415DivisionWise.get(i).toString().split("##");
                strDisplayAmountDisbursedForFY1415DivisionWise.append("<tr><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[0].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[1].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append(",");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[2].toString().replace("\n", "").replace("\r",
                        ""));
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[3].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[4].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[5].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[6].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[7].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[8].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[9].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[10].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[11].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[12].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[13].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td><td>");
                strDisplayAmountDisbursedForFY1415DivisionWise.append(tmp[14].toString());
                strDisplayAmountDisbursedForFY1415DivisionWise.append("</td></tr>");

                totalCount04_14 = totalCount04_14 + Long.parseLong(tmp[3].toString());
                totalCount05_14 = totalCount05_14 + Long.parseLong(tmp[4].toString());
                totalCount06_14 = totalCount06_14 + Long.parseLong(tmp[5].toString());
                totalCount07_14 = totalCount07_14 + Long.parseLong(tmp[6].toString());
                totalCount08_14 = totalCount08_14 + Long.parseLong(tmp[7].toString());
                totalCount09_14 = totalCount09_14 + Long.parseLong(tmp[8].toString());
                totalCount10_14 = totalCount10_14 + Long.parseLong(tmp[9].toString());
                totalCount11_14 = totalCount11_14 + Long.parseLong(tmp[10].toString());
                totalCount12_14 = totalCount12_14 + Long.parseLong(tmp[11].toString());
                totalCount01_15 = totalCount01_15 + Long.parseLong(tmp[12].toString());
                totalCount02_15 = totalCount02_15 + Long.parseLong(tmp[13].toString());
                totalCount03_15 = totalCount03_15 + Long.parseLong(tmp[14].toString());

                if (i == lstDisplayAmountDisbursedForFY1415DivisionWise.size() - 1)
                {
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<tr><td></td><td>Total</td>");

                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount04_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount05_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount06_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount07_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount08_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount09_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount10_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount11_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount12_14));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount01_15));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount02_15));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("<td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append(String.valueOf(totalCount03_15));
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</td>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</tr></table>");
                    strDisplayAmountDisbursedForFY1415DivisionWise
                            .append("<br><center><input type=\"button\" value=\"Print\"  onclick=\"javascript:window.print();\" width=\"50%\"/></center>");
                    strDisplayAmountDisbursedForFY1415DivisionWise.append("</body></html>");

                }
            }

            strDisplayAmountDisbursedForFY1415DivisionWise.toString().replace("\n", "").replace("\r", "");
            this.logger.info("Table html to be display on screen: "
                    + strDisplayAmountDisbursedForFY1415DivisionWise.toString());
        }

        return strDisplayAmountDisbursedForFY1415DivisionWise.toString();
    }

    // chart 4 popup
    public String getDisplayPayBillCountForFY1415DivisionWise(final String division)
    {

        final Session session = this.getSession();
        List lstDisplayPayBillCountForFY1415DivisionWise = null;
        final StringBuffer strDisplayPayBillCountForFY1415DivisionWise = new StringBuffer();
        final StringBuffer sb = new StringBuffer();
        long totalCount04_14 = 0;
        long totalCount05_14 = 0;
        long totalCount06_14 = 0;
        long totalCount07_14 = 0;
        long totalCount08_14 = 0;
        long totalCount09_14 = 0;
        long totalCount10_14 = 0;
        long totalCount11_14 = 0;
        long totalCount12_14 = 0;
        long totalCount01_15 = 0;
        long totalCount02_15 = 0;
        long totalCount03_15 = 0;

        // commented to use cron job table
        /*
         * sb.append(" SELECT region.REGION_NAME||'##'||dist.DISTRICT_NAME||'##'||admin.ADMIN_NAME||'##'|| "
         * );sb.append(
         * " count(case when pay.PAYBILL_MONTH = 4 and pay.PAYBILL_YEAR=2014 then 1 else null end) ||'##'||  "
         * );//as Apr_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 5 and pay.PAYBILL_YEAR=2014 then 1 else null end) ||'##'|| "
         * );//as May_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 6 and pay.PAYBILL_YEAR=2014 then 1 else null end) ||'##'|| "
         * );//as Jun_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 7 and pay.PAYBILL_YEAR=2014 then 1 else null end) ||'##'|| "
         * );//as Jul_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 8 and pay.PAYBILL_YEAR=2014 then 1 else null end) ||'##'|| "
         * );//as Aug_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 9 and pay.PAYBILL_YEAR=2014 then 1 else null end) ||'##'|| "
         * );//as Sep_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 10 and pay.PAYBILL_YEAR=2014 then 1 else null end)||'##'|| "
         * );//as Oct_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 11 and pay.PAYBILL_YEAR=2014 then 1 else null end)||'##'|| "
         * );// as Nov_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 12 and pay.PAYBILL_YEAR=2014 then 1 else null end)||'##'|| "
         * );//as Dec_2014sb.append(
         * " count(case when pay.PAYBILL_MONTH = 1 and pay.PAYBILL_YEAR=2015 then 1 else null end) ||'##'|| "
         * );//as Jan_2015sb.append(
         * " count(case when pay.PAYBILL_MONTH = 2 and pay.PAYBILL_YEAR=2015 then 1 else null end) ||'##'|| "
         * );//as Feb_2015sb.append(
         * " count(case when pay.PAYBILL_MONTH = 3 and pay.PAYBILL_YEAR=2015 then 1 else null end) "
         * );//as Mar_2015sb.append(
         * " FROM rlt_zp_ddo_map rlt  inner join ORG_DDO_MST ddo on rlt.zp_ddo_code=ddo.ddo_code   "
         * );sb.append(
         * " inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code and upper(off.ddo_office)='YES'   "
         * );sb.append(
         * " inner join paybill_head_mpg pay on ddo.LOCATION_CODE=pay.LOC_ID and pay.APPROVE_FLAG in(0,1) "
         * );sb.append(
         * " inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID "
         * );sb.append(
         * " inner join ZP_REGION_NAME_MST region on dist.REGION_CODE=region.REGION_CODE "
         * );
         * sb.append(" INNER join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.ID "
         * );sb.append(
         * " where rlt.STATUS in(0,1) and (pay.paybill_month in (1,2,3)  and pay.paybill_year = 2015) or (pay.paybill_month in (4,5,6,7,8,9,10,11,12) "
         * );sb.append(
         * " and pay.paybill_year = 2014) and pay.approve_flag in(0,1) and upper(region.REGION_NAME)='"
         * +division.toUpperCase()+"' ");sb.append(
         * " group by  region.REGION_NAME ,dist.DISTRICT_NAME,admin.ADMIN_NAME "
         * );
         */

        sb.append(" SELECT dashReport.DESCRIPTION||'##'||dashReport.data FROM DASHBOARD_REPORT dashReport ");
        sb.append(" where dashReport.CHART_ID = '4' and upper(dashReport.DESCRIPTION) = '" + division.toUpperCase()
                + "' order by dashReport.CHART_SUB_ID ");

        this.logger.info("Query to get popup details of paybills generated for FY 2014-2015: " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstDisplayPayBillCountForFY1415DivisionWise = query.list();
        if (lstDisplayPayBillCountForFY1415DivisionWise.size() > 0)
        {
            strDisplayPayBillCountForFY1415DivisionWise.append("<html><body>");
            strDisplayPayBillCountForFY1415DivisionWise
                    .append("<center><b>Divisionwise district and admin office and no. of paybills generated of: "
                            + division + "</b></center>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<table border=\"1\" width=\"100%\"><tr>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>Division</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>District and Admin Office</td>");
            strDisplayPayBillCountForFY1415DivisionWise
                    .append("<td colspan=\"12\">No. Of paybills generated FY 2014/2015</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("</tr>");

            strDisplayPayBillCountForFY1415DivisionWise.append("<tr>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>&nbsp;</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>&nbsp;</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>04/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>05/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>06/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>07/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>08/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>09/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>10/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>11/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>12/2014</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>01/2015</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>02/2015</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("<td>03/2015</td>");
            strDisplayPayBillCountForFY1415DivisionWise.append("</tr>");

            for (int i = 0; i < lstDisplayPayBillCountForFY1415DivisionWise.size(); i++)
            {
                final String tmp[] = lstDisplayPayBillCountForFY1415DivisionWise.get(i).toString().split("##");
                strDisplayPayBillCountForFY1415DivisionWise.append("<tr><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[0].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[1].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append(",");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[2].toString().replace("\n", "")
                        .replace("\r", ""));
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[3].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[4].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[5].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[6].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[7].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[8].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[9].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[10].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[11].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[12].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[13].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td><td>");
                strDisplayPayBillCountForFY1415DivisionWise.append(tmp[14].toString());
                strDisplayPayBillCountForFY1415DivisionWise.append("</td></tr>");

                totalCount04_14 = totalCount04_14 + Long.parseLong(tmp[3].toString());
                totalCount05_14 = totalCount05_14 + Long.parseLong(tmp[4].toString());
                totalCount06_14 = totalCount06_14 + Long.parseLong(tmp[5].toString());
                totalCount07_14 = totalCount07_14 + Long.parseLong(tmp[6].toString());
                totalCount08_14 = totalCount08_14 + Long.parseLong(tmp[7].toString());
                totalCount09_14 = totalCount09_14 + Long.parseLong(tmp[8].toString());
                totalCount10_14 = totalCount10_14 + Long.parseLong(tmp[9].toString());
                totalCount11_14 = totalCount11_14 + Long.parseLong(tmp[10].toString());
                totalCount12_14 = totalCount12_14 + Long.parseLong(tmp[11].toString());
                totalCount01_15 = totalCount01_15 + Long.parseLong(tmp[12].toString());
                totalCount02_15 = totalCount02_15 + Long.parseLong(tmp[13].toString());
                totalCount03_15 = totalCount03_15 + Long.parseLong(tmp[14].toString());

                if (i == lstDisplayPayBillCountForFY1415DivisionWise.size() - 1)
                {
                    strDisplayPayBillCountForFY1415DivisionWise.append("<tr><td></td><td>Total</td>");

                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount04_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount05_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount06_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount07_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount08_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount09_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount10_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount11_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount12_14));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount01_15));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount02_15));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("<td>");
                    strDisplayPayBillCountForFY1415DivisionWise.append(String.valueOf(totalCount03_15));
                    strDisplayPayBillCountForFY1415DivisionWise.append("</td>");

                    strDisplayPayBillCountForFY1415DivisionWise.append("</tr></table>");
                    strDisplayPayBillCountForFY1415DivisionWise
                            .append("<br><center><input type=\"button\" value=\"Print\"  onclick=\"javascript:window.print();\" width=\"50%\"/></center>");
                    strDisplayPayBillCountForFY1415DivisionWise.append("</body></html>");
                }
            }

            strDisplayPayBillCountForFY1415DivisionWise.toString().replace("\n", "").replace("\r", "");
            this.logger.info("Table html to be display on screen: "
                    + strDisplayPayBillCountForFY1415DivisionWise.toString());
        }

        return strDisplayPayBillCountForFY1415DivisionWise.toString();
    }

    // chart 1 popup
    public String getDisplaySchoolCountAdminWise(final String typeOfSchool)
    {

        final Session session = this.getSession();
        List lstDisplaySchoolCountAdminWise = null;
        final StringBuffer strDisplaySchoolCountAdminWise = new StringBuffer();
        final StringBuffer sb = new StringBuffer();
        long totalSchoolsForPopUp = 0;
        // commented to use cron job table
        /*
         * sb.append(" select admin.ADMIN_NAME||'##'||cmn.DISTRICT_NAME ||'##'||count(org.ddo_code)  "
         * );sb.append(
         * " from ORG_DDO_MST org,ZP_ADMIN_NAME_MST admin  , RLT_ZP_DDO_MAP rlt ,MST_DCPS_DDO_OFFICE mst ,  "
         * ); sb.append(" CMN_DISTRICT_MST cmn , ZP_REGION_NAME_MST zp   ");
         * sb.append(
         * " where rlt.ZP_DDO_CODE = org.DDO_CODE and org.DDO_TYPE = admin.ADMIN_CODE  "
         * );sb.append(
         * " and admin.ADMIN_CODE in (2,3,4,5,12,13,27,26,25,24,23,22,21,20) and rlt.status in (0,1)  "
         * );sb.append(
         * " and mst.DDO_CODE = org.DDO_CODE and mst.DISTRICT = cmn.DISTRICT_ID  "
         * ); sb.append(" and cmn.LANG_ID = '1' and cmn.STATE_ID = '15'  ");
         * sb.append(" and zp.REGION_CODE = cmn.REGION_CODE  ");
         * sb.append(" and upper(admin.ADMIN_NAME)='"
         * +typeOfSchool.toUpperCase()+"' ");
         * sb.append(" group by admin.ADMIN_NAME  ,cmn.DISTRICT_NAME ");
         */
        sb.append(" SELECT dashReport.DESCRIPTION||'##'||dashReport.data FROM DASHBOARD_REPORT dashReport ");
        sb.append(" where dashReport.CHART_ID ='1' and upper(dashReport.DESCRIPTION) = '" + typeOfSchool.toUpperCase()
                + "' order by dashReport.CHART_SUB_ID ");

        this.logger.info("Query to get popup details of schools covered: " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstDisplaySchoolCountAdminWise = query.list();
        if (lstDisplaySchoolCountAdminWise.size() > 0)
        {
            strDisplaySchoolCountAdminWise.append("<html><body>");
            strDisplaySchoolCountAdminWise.append("<center><b>District wise no. of schools of: " + typeOfSchool
                    + "</b></center>");
            strDisplaySchoolCountAdminWise.append("<table border=\"1\" width=\"100%\"><tr>");
            strDisplaySchoolCountAdminWise.append("<td>Type of school</td>");
            strDisplaySchoolCountAdminWise.append("<td>District</td>");
            strDisplaySchoolCountAdminWise.append("<td>Number of Schools</td> </tr>");

            for (int i = 0; i < lstDisplaySchoolCountAdminWise.size(); i++)
            {
                final String tmp[] = lstDisplaySchoolCountAdminWise.get(i).toString().split("##");
                strDisplaySchoolCountAdminWise.append("<tr><td>");
                strDisplaySchoolCountAdminWise.append(tmp[0].toString().replace("\n", "").replace("\r", ""));
                strDisplaySchoolCountAdminWise.append("</td><td>");
                strDisplaySchoolCountAdminWise.append(tmp[1].toString());
                strDisplaySchoolCountAdminWise.append("</td><td>");
                strDisplaySchoolCountAdminWise.append(tmp[2]);
                strDisplaySchoolCountAdminWise.append("</td></tr>");

                totalSchoolsForPopUp = totalSchoolsForPopUp + Long.parseLong(tmp[2].toString());

                if (i == lstDisplaySchoolCountAdminWise.size() - 1)
                {
                    strDisplaySchoolCountAdminWise.append("<tr><td></td><td>Total</td><td>");
                    strDisplaySchoolCountAdminWise.append(String.valueOf(totalSchoolsForPopUp));
                    strDisplaySchoolCountAdminWise.append("</td></tr>");
                    strDisplaySchoolCountAdminWise.append("</table>");
                    strDisplaySchoolCountAdminWise
                            .append("<br><center><input type=\"button\" value=\"Print\"  onclick=\"javascript:window.print();\" width=\"50%\"/></center>");
                    strDisplaySchoolCountAdminWise.append("</body></html>");
                }
            }

            strDisplaySchoolCountAdminWise.toString().replace("\n", "").replace("\r", "");
            this.logger.info("Table html to be display on screen: " + strDisplaySchoolCountAdminWise.toString());
        }

        return strDisplaySchoolCountAdminWise.toString();
    }

    // chart 2 popup
    public String getDisplaySchoolEmpCountDivisionWise(final String division)
    {

        final Session session = this.getSession();
        List lstDisplaySchoolEmpCountDivisionWise = null;
        final StringBuffer strDisplaySchoolEmpCountDivisionWise = new StringBuffer();
        final StringBuffer sb = new StringBuffer();
        long totalNoOfSchoolsForPopUp = 0;
        long totalNoOfEmpsForPopUp = 0;

        // commented to use cron job table
        /*
         * sb.append(" SELECT region.REGION_NAME||'##'|| dist.DISTRICT_NAME||'##'||admin.ADMIN_NAME||'##'||count(distinct emp.ddo_code)||'##'||sum(case when emp.ddo_code is not null then 1 else null end) FROM rlt_zp_ddo_map zp "
         * );sb.append(
         * " inner join MST_DCPS_DDO_OFFICE off on zp.ZP_DDO_CODE =  off.DDO_CODE and upper(off.DDO_OFFICE) = 'YES' "
         * );sb.append(
         * " inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE = substr(off.DDO_CODE,1,2) "
         * );sb.append(
         * " inner join CMN_DISTRICT_MST dist on off.DISTRICT = dist.DISTRICT_ID and dist.STATE_ID =15 and dist.LANG_ID = 1 "
         * );sb.append(
         * " inner join ZP_REGION_NAME_MST region on region.REGION_CODE = dist.REGION_CODE "
         * );sb.append(
         * " inner join mst_dcps_emp emp on emp.ddo_code = zp.ZP_DDO_CODE ");
         * sb.
         * append(" where zp.STATUS in (0,1) and upper(region.REGION_NAME)='"+
         * division.toUpperCase()+"' ");sb.append(
         * " group by region.REGION_CODE, dist.REGION_ORDER , region.REGION_NAME, dist.DISTRICT_NAME, admin.ADMIN_NAME "
         * );
         */
        sb.append(" SELECT dashReport.DESCRIPTION||'##'||dashReport.data FROM DASHBOARD_REPORT dashReport ");
        sb.append(" where dashReport.CHART_ID = '2' and upper(dashReport.DESCRIPTION) = '" + division.toUpperCase()
                + "' order by dashReport.CHART_SUB_ID ");

        this.logger.info("Query to get popup details of emp and schools: " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstDisplaySchoolEmpCountDivisionWise = query.list();
        if (lstDisplaySchoolEmpCountDivisionWise.size() > 0)
        {
            strDisplaySchoolEmpCountDivisionWise.append("<html><body>");
            strDisplaySchoolEmpCountDivisionWise
                    .append("<center><b>Divisionwise admin offices and No. of schools and No. of employees of: "
                            + division + "</b></center>");
            strDisplaySchoolEmpCountDivisionWise.append("<table border=\"1\" width=\"100%\"><tr>");
            strDisplaySchoolEmpCountDivisionWise.append("<td>Division</td>");
            strDisplaySchoolEmpCountDivisionWise.append("<td>District and Admin Office</td>");
            strDisplaySchoolEmpCountDivisionWise.append("<td>Number of Schools</td>");
            strDisplaySchoolEmpCountDivisionWise.append("<td>Number of Employees</td> </tr>");
            for (int i = 0; i < lstDisplaySchoolEmpCountDivisionWise.size(); i++)
            {
                final String tmp[] = lstDisplaySchoolEmpCountDivisionWise.get(i).toString().split("##");
                strDisplaySchoolEmpCountDivisionWise.append("<tr><td>");
                strDisplaySchoolEmpCountDivisionWise.append(tmp[0].toString());
                strDisplaySchoolEmpCountDivisionWise.append("</td><td>");
                strDisplaySchoolEmpCountDivisionWise.append(tmp[1].toString());
                strDisplaySchoolEmpCountDivisionWise.append(",");
                strDisplaySchoolEmpCountDivisionWise.append(tmp[2].toString().replace("\n", "").replace("\r", ""));
                strDisplaySchoolEmpCountDivisionWise.append("</td><td>");
                strDisplaySchoolEmpCountDivisionWise.append(tmp[3].toString());
                strDisplaySchoolEmpCountDivisionWise.append("</td><td>");
                strDisplaySchoolEmpCountDivisionWise.append(tmp[4].toString());
                strDisplaySchoolEmpCountDivisionWise.append("</td></tr>");
                totalNoOfSchoolsForPopUp = totalNoOfSchoolsForPopUp + Long.parseLong(tmp[3].toString());
                totalNoOfEmpsForPopUp = totalNoOfEmpsForPopUp + Long.parseLong(tmp[4].toString());
                if (i == lstDisplaySchoolEmpCountDivisionWise.size() - 1)
                {
                    strDisplaySchoolEmpCountDivisionWise.append("<tr><td></td><td>Total</td><td>");
                    strDisplaySchoolEmpCountDivisionWise.append(String.valueOf(totalNoOfSchoolsForPopUp));
                    strDisplaySchoolEmpCountDivisionWise.append("</td><td>");
                    strDisplaySchoolEmpCountDivisionWise.append(String.valueOf(totalNoOfEmpsForPopUp));
                    strDisplaySchoolEmpCountDivisionWise.append("</td></tr></table>");
                    strDisplaySchoolEmpCountDivisionWise
                            .append("<br><center><input type=\"button\" value=\"Print\"  onclick=\"javascript:window.print();\" width=\"50%\"/></center>");
                    strDisplaySchoolEmpCountDivisionWise.append("</body></html>");
                }
            }

            strDisplaySchoolEmpCountDivisionWise.toString().replace("\n", "").replace("\r", "");
            this.logger.info("Table html to be display on screen: " + strDisplaySchoolEmpCountDivisionWise.toString());
        }

        return strDisplaySchoolEmpCountDivisionWise.toString();
    }

    public String[] getDivisionWiseAmountDisbursed()
    {

        final Session session = this.getSession();
        List lstDivisionWiseAmountDisbursed = null;
        final String strDivisionWiseAmountDisbursed[] = new String[2];
        String strDivisionWiseAmountDisbursedToDisplay = " ";
        String strTotalDivisionWiseAmountDisbursed = null;
        long lngTotalDivisionWiseAmountDisbursed = 0;
        final StringBuffer sb = new StringBuffer();

        // commented to use cron job table
        /*
         * sb.append(" SELECT region.REGION_NAME||'##'||sum(pay.BILL_NET_AMOUNT) "
         * ); sb.append(" FROM rlt_zp_ddo_map rlt ");
         * sb.append(" inner join ORG_DDO_MST ddo on rlt.zp_ddo_code=ddo.ddo_code "
         * );sb.append(
         * " inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code and upper(off.ddo_office)='YES' "
         * );sb.append(
         * " inner join paybill_head_mpg pay on ddo.LOCATION_CODE=pay.LOC_ID and pay.APPROVE_FLAG in(0,1) "
         * );sb.append(
         * " inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID "
         * );sb.append(
         * " inner join ZP_REGION_NAME_MST region on dist.REGION_CODE=region.REGION_CODE "
         * );
         * sb.append(" INNER join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.ID "
         * );sb.append(
         * " where rlt.STATUS in(0,1) and (pay.paybill_month in (1,2,3) and pay.paybill_year = 2015) or (pay.paybill_month in (4,5,6,7,8,9,10,11,12) and pay.paybill_year = 2014) "
         * ); sb.append(" group by  region.REGION_NAME ");
         */
        sb
                .append(" select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '5' ORDER by CHART_SUB_ID ");

        this.logger.info("Query to get amount disbursed divisionWise : " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstDivisionWiseAmountDisbursed = query.list();
        final DecimalFormat df = new DecimalFormat("0.00");
        if (lstDivisionWiseAmountDisbursed.size() > 0)
        {
            for (int i = 0; i < lstDivisionWiseAmountDisbursed.size(); i++)
            {

                final String tmp[] = lstDivisionWiseAmountDisbursed.get(i).toString().split("##");
                strDivisionWiseAmountDisbursedToDisplay = strDivisionWiseAmountDisbursedToDisplay.replace("\n", "")
                        .replace("\r", "").concat(
                                "['".concat(tmp[0]).concat("',").concat(
                                        df.format(Double.parseDouble(tmp[1]) / 10000000)).concat("],"));
                // strDivisionWiseAmountDisbursed=strDivisionWiseAmountDisbursed.replace("\n",
                // "").replace("\r",
                // "").concat("['".concat(tmp[0]).concat("',").concat(tmp[1]).concat(",'").concat(tmp[1]).concat("'").concat("],"));
                lngTotalDivisionWiseAmountDisbursed = lngTotalDivisionWiseAmountDisbursed + Long.parseLong(tmp[1]);
            }
            strDivisionWiseAmountDisbursedToDisplay.replace("\n", "").replace("\r", "");
            strDivisionWiseAmountDisbursedToDisplay = strDivisionWiseAmountDisbursedToDisplay.substring(0,
                    strDivisionWiseAmountDisbursedToDisplay.length() - 1);
            this.logger.info("String to be display on screen: " + strDivisionWiseAmountDisbursedToDisplay);
        }

        strTotalDivisionWiseAmountDisbursed = String.valueOf(lngTotalDivisionWiseAmountDisbursed);
        this.logger.info("total amount disbursed divisionWise : " + strTotalDivisionWiseAmountDisbursed);

        strDivisionWiseAmountDisbursed[0] = strDivisionWiseAmountDisbursedToDisplay;
        strDivisionWiseAmountDisbursed[1] = df
                .format(Double.parseDouble(strTotalDivisionWiseAmountDisbursed) / 10000000);
        return strDivisionWiseAmountDisbursed;
    }

    public String[] getDivisionWiseAmountDisbursedCurrentMonth()
    {

        final Session session = this.getSession();
        List lstDivisionWiseAmountDisbursedCurrentMonth = null;
        final String strDivisionWiseAmountDisbursedCurrentMonth[] = new String[2];
        String strDivisionWiseAmountDisbursedCurrentMonthToDisplay = " ";
        String strTotalDivisionWiseAmountDisbursedCurrentMonth = null;
        long lngTotalDivisionWiseAmountDisbursedCurrentMonth = 0;
        final StringBuffer sb = new StringBuffer();

        // commented to use cron job table
        /*
         * sb.append(" SELECT region.REGION_NAME||'##'||sum(pay.BILL_NET_AMOUNT) "
         * ); sb.append(" FROM rlt_zp_ddo_map rlt ");
         * sb.append(" inner join ORG_DDO_MST ddo on rlt.zp_ddo_code=ddo.ddo_code "
         * );sb.append(
         * " inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code and upper(off.ddo_office)='YES' "
         * );sb.append(
         * " inner join paybill_head_mpg pay on ddo.LOCATION_CODE=pay.LOC_ID and pay.APPROVE_FLAG in(0,1) "
         * );sb.append(
         * " inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID "
         * );sb.append(
         * " inner join ZP_REGION_NAME_MST region on dist.REGION_CODE=region.REGION_CODE "
         * );
         * sb.append(" INNER join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.ID "
         * );sb.append(
         * " where rlt.STATUS in(0,1) and pay.paybill_month = month(sysdate) and pay.paybill_year = year(sysdate)  "
         * ); sb.append(" group by  region.REGION_NAME ");
         */

        sb
                .append(" select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '6' ORDER by CHART_SUB_ID ");

        this.logger.info("Query to get amount disbursed divisionWise for current month: " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstDivisionWiseAmountDisbursedCurrentMonth = query.list();
        if (lstDivisionWiseAmountDisbursedCurrentMonth.size() > 0)
        {
            for (int i = 0; i < lstDivisionWiseAmountDisbursedCurrentMonth.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedCurrentMonth.get(i).toString().split("##");
                strDivisionWiseAmountDisbursedCurrentMonthToDisplay = strDivisionWiseAmountDisbursedCurrentMonthToDisplay
                        .replace("\n", "").replace("\r", "").concat(
                                "['".concat(tmp[0]).concat("',").concat(tmp[1]).concat("],"));
                lngTotalDivisionWiseAmountDisbursedCurrentMonth = lngTotalDivisionWiseAmountDisbursedCurrentMonth
                        + Long.parseLong(tmp[1]);
            }
            strDivisionWiseAmountDisbursedCurrentMonthToDisplay.replace("\n", "").replace("\r", "");
            strDivisionWiseAmountDisbursedCurrentMonthToDisplay = strDivisionWiseAmountDisbursedCurrentMonthToDisplay
                    .substring(0, strDivisionWiseAmountDisbursedCurrentMonthToDisplay.length() - 1);
            this.logger.info("String to be display on screen: " + strDivisionWiseAmountDisbursedCurrentMonthToDisplay);
        }

        strTotalDivisionWiseAmountDisbursedCurrentMonth = String
                .valueOf(lngTotalDivisionWiseAmountDisbursedCurrentMonth);

        strDivisionWiseAmountDisbursedCurrentMonth[0] = strDivisionWiseAmountDisbursedCurrentMonthToDisplay;
        strDivisionWiseAmountDisbursedCurrentMonth[1] = strTotalDivisionWiseAmountDisbursedCurrentMonth;
        return strDivisionWiseAmountDisbursedCurrentMonth;
    }

    public String getDivisionWiseAmountDisbursedForLast13MonthsLineChart() throws ParseException
    {

        final Session session = this.getSession();
        List lstDivisionWiseAmountDisbursedForLast13MonthsLineChart = null;
        String strDivisionWiseAmountDisbursedForLast13MonthsLineChart = " ";
        final StringBuffer sb = new StringBuffer();

        // commented to use cron job table
        /*
         * sb.append(" SELECT region.REGION_NAME ||'##'|| ");sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-12)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-12)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-11)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-11)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-10)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-10)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-9)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-9)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-8)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-8)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-7)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-7)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-6)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-6)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-5)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-5)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-4)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-4)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-3)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-3)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-2)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-2)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(add_months(sysdate,-1)) and pay.PAYBILL_YEAR=year(add_months(sysdate,-1)) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0))||'##'|| "
         * );sb.append(
         * " sum(nvl(case when pay.PAYBILL_MONTH=month(sysdate) and pay.PAYBILL_YEAR=year(sysdate) and pay.APPROVE_FLAG in(0,1) then pay.BILL_NET_AMOUNT else null end ,0)) "
         * );sb.append(
         * " from ZP_REGION_NAME_MST region left outer join CMN_DISTRICT_MST dist on region.REGION_CODE = dist.REGION_CODE "
         * );sb.append(
         * " left outer join MST_DCPS_DDO_OFFICE off on cast (dist.DISTRICT_ID as  varchar) = off.DISTRICT and upper(off.ddo_office)='YES' "
         * );sb.append(
         * " left outer join ORG_DDO_MST ddo on off.DDO_CODE = ddo.DDO_CODE ");
         * sb.append(
         * " LEFT outer JOIN paybill_head_mpg pay on ddo.LOCATION_CODE = pay.LOC_ID  and pay.APPROVE_FLAG in(0,1) and ((pay.paybill_month in (1,2,3,4,5,6,7,8) and pay.paybill_year = 2014) or (pay.paybill_month in (8,9,10,11,12) and pay.paybill_year = 2013) ) "
         * ); sb.append(" group by region.REGION_NAME ");
         */
        sb
                .append(" select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '7' ORDER by CHART_SUB_ID ");

        this.logger.info("Query to get amount disbursed divisionWise for last 12 months: " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstDivisionWiseAmountDisbursedForLast13MonthsLineChart = query.list();
        final String monthList[] = { "", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
                "Dec" };
        if (lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size() > 0)
        {
            final DateFormat inputDF = new SimpleDateFormat("MM/dd/yy");
            final Date date1 = inputDF.parse(inputDF.format(new Date()));

            final Calendar cal = Calendar.getInstance();
            cal.setTime(date1);

            final int month = cal.get(Calendar.MONTH) + 1;
            final int day = cal.get(Calendar.DAY_OF_MONTH);
            final Integer year = cal.get(Calendar.YEAR);
            this.logger.info("Current month:" + month + "-" + year);
            final DecimalFormat df = new DecimalFormat("0.00");
            // first row
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = "['Month-Year','Amravati','Aurangabad','Kolhapur','Konkan','Latur','Nagpur','Nashik','Pune'],";

            // current-12 month
            cal.add(Calendar.MONTH, -12);
            final Integer year_0 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[month]).concat("-").concat(year_0.toString()).concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[1]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-11 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_1 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_1.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[2]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-10 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_2 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_2.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[3]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-9 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_3 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_3.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[4]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-8 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_4 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_4.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[5]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-7 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_5 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_5.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[6]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-6 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_6 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_6.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[7]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-5 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_7 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_7.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[8]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-4 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_8 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_8.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[9]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-3 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_9 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_9.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[10]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-2 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_10 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_10.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[11]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current-1 month
            cal.add(Calendar.MONTH, 1);
            final Integer year_11 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_11.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[12]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("],");

            // current month
            cal.add(Calendar.MONTH, 1);
            final Integer year_12 = cal.get(Calendar.YEAR);
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .concat("['".concat(monthList[cal.get(Calendar.MONTH) + 1]).concat("-").concat(year_12.toString())
                            .concat("',"));
            for (int i = 0; i < lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.size(); i++)
            {
                final String tmp[] = lstDivisionWiseAmountDisbursedForLast13MonthsLineChart.get(i).toString().split(
                        "##");
                strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                        .concat(df.format(Double.parseDouble(tmp[13]) / 100000)).concat(",");
            }
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1).concat("]  ");

            strDivisionWiseAmountDisbursedForLast13MonthsLineChart.replace("\n", "").replace("\r", "");
            strDivisionWiseAmountDisbursedForLast13MonthsLineChart = strDivisionWiseAmountDisbursedForLast13MonthsLineChart
                    .substring(0, strDivisionWiseAmountDisbursedForLast13MonthsLineChart.length() - 1);
            this.logger.info("String to be display on screen: "
                    + strDivisionWiseAmountDisbursedForLast13MonthsLineChart);
        }

        return strDivisionWiseAmountDisbursedForLast13MonthsLineChart;
    }

    public String[] getDivisionWiseEmployeesCoveredinCurrentMonthPaybill()
    {

        final Session session = this.getSession();
        List lstNoOfDivisionWisePaybillGenerated = null;
        final String strNoOfDivisionWisePaybillGenerated[] = new String[2];
        String strNoOfDivisionWisePaybillGeneratedToDisplay = " ";
        String strTotalNoOfDivisionWisePaybillGenerated = null;
        long lngTotalNoOfDivisionWisePaybillGenerated = 0;
        final StringBuffer sb = new StringBuffer();

        // commented to use cron job table
        // Division Wise Number of PayBills Generated For FY 2014-15:
        /*
         * sb.append(" SELECT region.REGION_NAME||'##'||count(case when pay.approve_flag in(0,1) then 1 else null end) as total_paybill_genearted "
         * ); sb.append(" FROM rlt_zp_ddo_map rlt ");
         * sb.append(" inner join ORG_DDO_MST ddo on rlt.zp_ddo_code=ddo.ddo_code "
         * );sb.append(
         * " inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code and upper(off.ddo_office)='YES' "
         * );sb.append(
         * " inner join paybill_head_mpg pay on ddo.LOCATION_CODE=pay.LOC_ID and pay.APPROVE_FLAG in(0,1) "
         * );sb.append(
         * " inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID "
         * );sb.append(
         * " inner join ZP_REGION_NAME_MST region on dist.REGION_CODE=region.REGION_CODE "
         * );
         * sb.append(" INNER join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.ID "
         * );sb.append(
         * " where rlt.STATUS in(0,1) and (pay.paybill_month in (1,2,3) and pay.paybill_year = 2015) or (pay.paybill_month in (4,5,6,7,8,9,10,11,12) and pay.paybill_year = 2014) "
         * ); sb.append(" group by  region.REGION_NAME ");
         */

        /*
         * Query to get no of division wise employees covered in paybills for fy
         * 2014 15 SELECT '0' as chart_id,row_number()OVER (Order by
         * month(frm.LOGIN_DATE_TIME)) as chart_sub_id,
         * region.REGION_NAME,sum(pay.NO_OF_EMP) FROM PAYBILL_HEAD_MPG pay inner
         * join ORG_DDO_MST ddo on pay.LOC_ID = ddo.LOCATION_CODE inner join
         * MST_DCPS_DDO_OFFICE mstddo on ddo.DDO_CODE = mstddo.DDO_CODE inner
         * join RLT_ZP_DDO_MAP rlt on mstddo.DDO_CODE=rlt.ZP_DDO_CODE inner join
         * CMN_DISTRICT_MST dist on mstddo.DISTRICT=dist.DISTRICT_ID inner join
         * ZP_REGION_NAME_MST region on dist.REGION_CODE=region.ID where
         * pay.APPROVE_FLAG in (0,1) and pay.STATUS in (0,2,4) and
         * pay.PAYBILL_MONTH = 10 and pay.PAYBILL_YEAR=2014 group by
         * region.REGION_NAME
         */

        sb
                .append(" select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '8' ORDER by CHART_SUB_ID ");
        this.logger.info("Query to get no of division wise employees covered in paybills current month : "
                + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstNoOfDivisionWisePaybillGenerated = query.list();
        if (lstNoOfDivisionWisePaybillGenerated.size() > 0)
        {
            for (int i = 0; i < lstNoOfDivisionWisePaybillGenerated.size(); i++)
            {
                final String tmp[] = lstNoOfDivisionWisePaybillGenerated.get(i).toString().split("##");
                lngTotalNoOfDivisionWisePaybillGenerated = lngTotalNoOfDivisionWisePaybillGenerated
                        + Long.parseLong(tmp[1]);

            }
            for (int i = 0; i < lstNoOfDivisionWisePaybillGenerated.size(); i++)
            {
                final String tmp[] = lstNoOfDivisionWisePaybillGenerated.get(i).toString().split("##");
                // lngTotalNoOfDivisionWisePaybillGenerated=lngTotalNoOfDivisionWisePaybillGenerated+Long.parseLong(tmp[1]);
                // strNoOfDivisionWisePaybillGenerated=strNoOfDivisionWisePaybillGenerated.replace("\n",
                // "").replace("\r",
                // "").concat("['".concat(tmp[0]).concat("',").concat(tmp[1]).concat("],"));
                this.logger.info("total no.: " + lngTotalNoOfDivisionWisePaybillGenerated + " " + tmp[1]);
                this.logger.info("calculatePercentage(lngTotalNoOfDivisionWisePaybillGenerated,tmp[1]): "
                        + this.calculatePercentage(lngTotalNoOfDivisionWisePaybillGenerated, tmp[1]));
                strNoOfDivisionWisePaybillGeneratedToDisplay = strNoOfDivisionWisePaybillGeneratedToDisplay.replace(
                        "\n", "").replace("\r", "").concat(
                        "['".concat(tmp[0]).concat("',").concat(tmp[1]).concat(",'").concat(
                                this.calculatePercentage(lngTotalNoOfDivisionWisePaybillGenerated, tmp[1])).concat("'")
                                .concat("],"));

            }
            strNoOfDivisionWisePaybillGeneratedToDisplay.replace("\n", "").replace("\r", "");
            strNoOfDivisionWisePaybillGeneratedToDisplay = strNoOfDivisionWisePaybillGeneratedToDisplay.substring(0,
                    strNoOfDivisionWisePaybillGeneratedToDisplay.length() - 1);
            this.logger.info("String to be display on screen: " + strNoOfDivisionWisePaybillGeneratedToDisplay);
        }

        strTotalNoOfDivisionWisePaybillGenerated = String.valueOf(lngTotalNoOfDivisionWisePaybillGenerated);
        this.logger.info("total no of division wise paybills generated: " + strTotalNoOfDivisionWisePaybillGenerated);

        strNoOfDivisionWisePaybillGenerated[0] = strNoOfDivisionWisePaybillGeneratedToDisplay;
        strNoOfDivisionWisePaybillGenerated[1] = strTotalNoOfDivisionWisePaybillGenerated;
        return strNoOfDivisionWisePaybillGenerated;
    }

    public String getLogedinusers()
    {

        final Session session = this.getSession();
        List lstLogedinUsers = null;
        String strLogedinUsers = " ";
        final StringBuffer sb = new StringBuffer();
        // commented to use cron job table
        /*
         * sb.append(" SELECT cmn.LOOKUP_NAME || '##' || count(1)FROM FRM_LOGIN_AUDIT frm,CMN_LOOKUP_MST cmn where frm.LOGIN_DATE_TIME > '2014-01-01' "
         * );sb.append(
         * " and frm.LOGIN_STATUS = 142  and cmn.PARENT_LOOKUP_ID='340000' and month(frm.LOGIN_DATE_TIME) =cmn.LOOKUP_SHORT_NAME "
         * );sb.append(
         * " GROUP by month(frm.LOGIN_DATE_TIME) , cmn.LOOKUP_NAME order by month(frm.LOGIN_DATE_TIME)"
         * );
         */

        sb
                .append(" select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '0' ORDER by CHART_SUB_ID ");

        this.logger.info("Query to get loged in users count: " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstLogedinUsers = query.list();
        this.logger.info("lstLogedinUsers.size(): " + lstLogedinUsers.size());
        if (lstLogedinUsers.size() > 0)
        {
            // strLogedinUsers=lstLogedinUsers.get(0).toString();
            for (int i = 0; i < lstLogedinUsers.size(); i++)
            {
                final String tmp[] = lstLogedinUsers.get(i).toString().split("##");
                strLogedinUsers = strLogedinUsers.replace("\n", "").replace("\r", "").concat(
                        "['".concat(tmp[0]).concat("',").concat("{v:").concat(tmp[1]).concat(",f:'").concat(tmp[1])
                                .concat("'}").concat("],"));
            }
            strLogedinUsers.replace("\n", "").replace("\r", "");
            strLogedinUsers = strLogedinUsers.substring(0, strLogedinUsers.length() - 1);
            this.logger.info("String to be display on screen: " + strLogedinUsers);
        }

        return strLogedinUsers;
    }

    public String[] getNoOfDivisionWiseEmp()
    {
        final Session session = this.getSession();
        List lstNoOfDivisionWiseEmp = null;
        final String strNoOfDivisionWiseEmp[] = new String[2];
        String strNoOfDivisionWiseEmpToDisplay = " ";
        String strTotalNoOfDivisionWiseEmp = null;
        long lngTotalNoOfDivisionWiseEmp = 0;
        final StringBuffer sb = new StringBuffer();

        // commented to use cron job table
        /*
         * sb.append(" select region.REGION_NAME||'##'||count(emp.DCPS_EMP_ID) from ORG_DDO_MST org, RLT_ZP_DDO_MAP rlt ,  ZP_REGION_NAME_MST region,MST_DCPS_DDO_OFFICE mst,cmn_district_mst cmn ,MST_DCPS_EMP emp "
         * );sb.append(
         * " where rlt.ZP_DDO_CODE = org.DDO_CODE and org.DDO_CODE = mst.DDO_CODE and mst.district = cmn.district_id  and cmn.region_code=region.region_code and emp.DDO_CODE = rlt.ZP_DDO_CODE "
         * );sb.append(
         * " and (emp.EMP_SERVEND_DT > sysdate or emp.EMP_SERVEND_DT is null) and emp.REG_STATUS in (1,2) and rlt.status in (0,1) GROUP by region.REGION_NAME "
         * );
         */

        sb
                .append(" select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '3' ORDER by CHART_SUB_ID ");

        this.logger.info("Query to get no of division wise emps : " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstNoOfDivisionWiseEmp = query.list();
        if (lstNoOfDivisionWiseEmp.size() > 0)
        {
            for (int i = 0; i < lstNoOfDivisionWiseEmp.size(); i++)
            {
                final String tmp[] = lstNoOfDivisionWiseEmp.get(i).toString().split("##");
                lngTotalNoOfDivisionWiseEmp = lngTotalNoOfDivisionWiseEmp + Long.parseLong(tmp[1]);

            }
            for (int i = 0; i < lstNoOfDivisionWiseEmp.size(); i++)
            {
                final String tmp[] = lstNoOfDivisionWiseEmp.get(i).toString().split("##");
                // lngTotalNoOfDivisionWiseEmp=lngTotalNoOfDivisionWiseEmp+Long.parseLong(tmp[1]);
                // strNoOfDivisionWiseEmp=strNoOfDivisionWiseEmp.replace("\n",
                // "").replace("\r",
                // "").concat("['".concat(tmp[0]).concat("',").concat(tmp[1]).concat("],"));
                this.logger.info("total no.: " + lngTotalNoOfDivisionWiseEmp + " " + tmp[1]);
                this.logger.info("calculatePercentage(lngTotalNoOfDivisionWiseEmp,tmp[1]): "
                        + this.calculatePercentage(lngTotalNoOfDivisionWiseEmp, tmp[1]));
                strNoOfDivisionWiseEmpToDisplay = strNoOfDivisionWiseEmpToDisplay.replace("\n", "").replace("\r", "")
                        .concat(
                                "['".concat(tmp[0]).concat("',").concat(tmp[1]).concat(",'").concat(
                                        this.calculatePercentage(lngTotalNoOfDivisionWiseEmp, tmp[1])).concat("'")
                                        .concat("],"));

            }
            strNoOfDivisionWiseEmpToDisplay.replace("\n", "").replace("\r", "");
            strNoOfDivisionWiseEmpToDisplay = strNoOfDivisionWiseEmpToDisplay.substring(0,
                    strNoOfDivisionWiseEmpToDisplay.length() - 1);
            this.logger.info("String to be display on screen: " + strNoOfDivisionWiseEmpToDisplay);
        }

        strTotalNoOfDivisionWiseEmp = String.valueOf(lngTotalNoOfDivisionWiseEmp);
        this.logger.info("total number of employees division wise: " + strTotalNoOfDivisionWiseEmp);

        strNoOfDivisionWiseEmp[0] = strNoOfDivisionWiseEmpToDisplay;
        strNoOfDivisionWiseEmp[1] = strTotalNoOfDivisionWiseEmp;
        return strNoOfDivisionWiseEmp;
    }

    public String[] getNoOfDivisionWisePaybillGenerated()
    {

        final Session session = this.getSession();
        List lstNoOfDivisionWisePaybillGenerated = null;
        final String strNoOfDivisionWisePaybillGenerated[] = new String[2];
        String strNoOfDivisionWisePaybillGeneratedToDisplay = " ";
        String strTotalNoOfDivisionWisePaybillGenerated = null;
        long lngTotalNoOfDivisionWisePaybillGenerated = 0;
        final StringBuffer sb = new StringBuffer();

        // commented to use cron job table
        // Division Wise Number of PayBills Generated For FY 2014-15:
        /*
         * sb.append(" SELECT region.REGION_NAME||'##'||count(case when pay.approve_flag in(0,1) then 1 else null end) as total_paybill_genearted "
         * ); sb.append(" FROM rlt_zp_ddo_map rlt ");
         * sb.append(" inner join ORG_DDO_MST ddo on rlt.zp_ddo_code=ddo.ddo_code "
         * );sb.append(
         * " inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code and upper(off.ddo_office)='YES' "
         * );sb.append(
         * " inner join paybill_head_mpg pay on ddo.LOCATION_CODE=pay.LOC_ID and pay.APPROVE_FLAG in(0,1) "
         * );sb.append(
         * " inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID "
         * );sb.append(
         * " inner join ZP_REGION_NAME_MST region on dist.REGION_CODE=region.REGION_CODE "
         * );
         * sb.append(" INNER join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.ID "
         * );sb.append(
         * " where rlt.STATUS in(0,1) and (pay.paybill_month in (1,2,3) and pay.paybill_year = 2015) or (pay.paybill_month in (4,5,6,7,8,9,10,11,12) and pay.paybill_year = 2014) "
         * ); sb.append(" group by  region.REGION_NAME ");
         */

        /*
         * Query to get no of division wise employees covered in paybills for fy
         * 2014 15 SELECT '0' as chart_id,row_number()OVER (Order by
         * month(frm.LOGIN_DATE_TIME)) as chart_sub_id,
         * region.REGION_NAME,sum(pay.NO_OF_EMP) FROM PAYBILL_HEAD_MPG pay inner
         * join ORG_DDO_MST ddo on pay.LOC_ID = ddo.LOCATION_CODE inner join
         * MST_DCPS_DDO_OFFICE mstddo on ddo.DDO_CODE = mstddo.DDO_CODE inner
         * join RLT_ZP_DDO_MAP rlt on mstddo.DDO_CODE=rlt.ZP_DDO_CODE inner join
         * CMN_DISTRICT_MST dist on mstddo.DISTRICT=dist.DISTRICT_ID inner join
         * ZP_REGION_NAME_MST region on dist.REGION_CODE=region.ID where
         * pay.APPROVE_FLAG in (0,1) and pay.STATUS in (0,2,4) and
         * (pay.paybill_month in (1,2,3) and pay.paybill_year = 2015) or
         * (pay.paybill_month in (4,5,6,7,8,9,10,11,12) and pay.paybill_year =
         * 2014) group by region.REGION_NAME
         */

        sb
                .append(" select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '4' ORDER by CHART_SUB_ID ");
        this.logger.info("Query to get no of division wise employees covered in paybills for fy 2014 15 : "
                + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstNoOfDivisionWisePaybillGenerated = query.list();
        if (lstNoOfDivisionWisePaybillGenerated.size() > 0)
        {
            for (int i = 0; i < lstNoOfDivisionWisePaybillGenerated.size(); i++)
            {
                final String tmp[] = lstNoOfDivisionWisePaybillGenerated.get(i).toString().split("##");
                lngTotalNoOfDivisionWisePaybillGenerated = lngTotalNoOfDivisionWisePaybillGenerated
                        + Long.parseLong(tmp[1]);

            }
            for (int i = 0; i < lstNoOfDivisionWisePaybillGenerated.size(); i++)
            {
                final String tmp[] = lstNoOfDivisionWisePaybillGenerated.get(i).toString().split("##");
                // lngTotalNoOfDivisionWisePaybillGenerated=lngTotalNoOfDivisionWisePaybillGenerated+Long.parseLong(tmp[1]);
                // strNoOfDivisionWisePaybillGenerated=strNoOfDivisionWisePaybillGenerated.replace("\n",
                // "").replace("\r",
                // "").concat("['".concat(tmp[0]).concat("',").concat(tmp[1]).concat("],"));
                this.logger.info("total no.: " + lngTotalNoOfDivisionWisePaybillGenerated + " " + tmp[1]);
                this.logger.info("calculatePercentage(lngTotalNoOfDivisionWisePaybillGenerated,tmp[1]): "
                        + this.calculatePercentage(lngTotalNoOfDivisionWisePaybillGenerated, tmp[1]));
                strNoOfDivisionWisePaybillGeneratedToDisplay = strNoOfDivisionWisePaybillGeneratedToDisplay.replace(
                        "\n", "").replace("\r", "").concat(
                        "['".concat(tmp[0]).concat("',").concat(tmp[1]).concat(",'").concat(
                                this.calculatePercentage(lngTotalNoOfDivisionWisePaybillGenerated, tmp[1])).concat("'")
                                .concat("],"));

            }
            strNoOfDivisionWisePaybillGeneratedToDisplay.replace("\n", "").replace("\r", "");
            strNoOfDivisionWisePaybillGeneratedToDisplay = strNoOfDivisionWisePaybillGeneratedToDisplay.substring(0,
                    strNoOfDivisionWisePaybillGeneratedToDisplay.length() - 1);
            this.logger.info("String to be display on screen: " + strNoOfDivisionWisePaybillGeneratedToDisplay);
        }

        strTotalNoOfDivisionWisePaybillGenerated = String.valueOf(lngTotalNoOfDivisionWisePaybillGenerated);
        this.logger.info("total no of division wise paybills generated: " + strTotalNoOfDivisionWisePaybillGenerated);

        strNoOfDivisionWisePaybillGenerated[0] = strNoOfDivisionWisePaybillGeneratedToDisplay;
        strNoOfDivisionWisePaybillGenerated[1] = strTotalNoOfDivisionWisePaybillGenerated;
        return strNoOfDivisionWisePaybillGenerated;
    }

    public String[] getNoOfDivisionWiseSchools()
    {
        final Session session = this.getSession();
        List lstNoOfDivisionWiseSchools = null;
        final String strNoOfDivisionWiseSchools[] = new String[2];
        String strNoOfDivisionWiseSchoolsTodisplay = " ";
        String strTotalNoOfDivisionWiseSchools = null;
        long lngTotalNoOfDivisionWiseSchools = 0;
        final StringBuffer sb = new StringBuffer();

        // commented to use cron job table
        /*
         * sb.append(" select region.REGION_NAME|| '##' ||count(org.DDO_CODE) from ORG_DDO_MST org, RLT_ZP_DDO_MAP rlt , "
         * );sb.append(
         * " ZP_REGION_NAME_MST region,MST_DCPS_DDO_OFFICE mst ,cmn_district_mst cmn where rlt.ZP_DDO_CODE = org.DDO_CODE and org.DDO_CODE = mst.DDO_CODE "
         * );sb.append(
         * " and mst.district = cmn.district_id  and cmn.region_code=region.region_code and rlt.status in (0,1) GROUP  by region.REGION_NAME  "
         * );
         */
        sb
                .append(" select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '2' ORDER by CHART_SUB_ID  ");

        this.logger.info("Query to get no of division wise schools : " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstNoOfDivisionWiseSchools = query.list();
        if (lstNoOfDivisionWiseSchools.size() > 0)
        {
            for (int i = 0; i < lstNoOfDivisionWiseSchools.size(); i++)
            {
                final String tmp[] = lstNoOfDivisionWiseSchools.get(i).toString().split("##");
                lngTotalNoOfDivisionWiseSchools = lngTotalNoOfDivisionWiseSchools + Long.parseLong(tmp[1]);

            }
            for (int i = 0; i < lstNoOfDivisionWiseSchools.size(); i++)
            {
                final String tmp[] = lstNoOfDivisionWiseSchools.get(i).toString().split("##");
                // lngTotalNoOfDivisionWiseSchools=lngTotalNoOfDivisionWiseSchools+Long.parseLong(tmp[1]);
                // strNoOfDivisionWiseSchools=strNoOfDivisionWiseSchools.replace("\n",
                // "").replace("\r",
                // "").concat("['".concat(tmp[0]).concat("',").concat(tmp[1]).concat("],"));
                this.logger.info("total no.: " + lngTotalNoOfDivisionWiseSchools + " " + tmp[1]);
                this.logger.info("calculatePercentage(lngTotalNoOfDivisionWiseSchools,tmp[1]): "
                        + this.calculatePercentage(lngTotalNoOfDivisionWiseSchools, tmp[1]));
                strNoOfDivisionWiseSchoolsTodisplay = strNoOfDivisionWiseSchoolsTodisplay.replace("\n", "").replace(
                        "\r", "").concat(
                        "['".concat(tmp[0]).concat("',").concat(tmp[1]).concat(",'").concat(
                                this.calculatePercentage(lngTotalNoOfDivisionWiseSchools, tmp[1])).concat("'").concat(
                                "],"));

            }
            strNoOfDivisionWiseSchoolsTodisplay.replace("\n", "").replace("\r", "");
            strNoOfDivisionWiseSchoolsTodisplay = strNoOfDivisionWiseSchoolsTodisplay.substring(0,
                    strNoOfDivisionWiseSchoolsTodisplay.length() - 1);
            this.logger.info("String to be display on screen: " + strNoOfDivisionWiseSchoolsTodisplay);
        }

        strTotalNoOfDivisionWiseSchools = String.valueOf(lngTotalNoOfDivisionWiseSchools);
        this.logger.info("total number of schools divisionwise: " + strTotalNoOfDivisionWiseSchools);

        strNoOfDivisionWiseSchools[0] = strNoOfDivisionWiseSchoolsTodisplay;
        strNoOfDivisionWiseSchools[1] = strTotalNoOfDivisionWiseSchools;
        return strNoOfDivisionWiseSchools;
    }

    public String getNoOfEmployees()
    {

        final Session session = this.getSession();
        List lstNoOfEmployees = null;
        String strNoOfEmployees = null;
        final StringBuffer sb = new StringBuffer();
        sb.append(" SELECT count(1) FROM MST_DCPS_EMP where DDO_CODE in (select zp_ddo_code from RLT_ZP_DDO_MAP)  ");
        sb.append(" and (EMP_SERVEND_DT > sysdate or EMP_SERVEND_DT is null) and REG_STATUS in (1,2) ");
        final Query query = session.createSQLQuery(sb.toString());
        lstNoOfEmployees = query.list();
        if (lstNoOfEmployees.size() > 0)
        {
            strNoOfEmployees = lstNoOfEmployees.get(0).toString();
        }

        return strNoOfEmployees;
    }

    public String[] getNoOfOfficesCovered()
    {

        final Session session = this.getSession();
        List lstNoOfOfficesCovered = null;
        final String strNoOfOfficesCovered[] = new String[2];
        String strNoOfOfficesCoveredAdminWise = " ";
        String strTotalNoOfOfficesCovered = null;
        long lngTotalNoOfOfficesCovered = 0;
        final StringBuffer sb = new StringBuffer();

        // commented to use cron job table
        /*
         * sb.append(" select admin.ADMIN_NAME || '##' ||count(org.ddo_code) from ORG_DDO_MST org,ZP_ADMIN_NAME_MST admin  , RLT_ZP_DDO_MAP rlt ,MST_DCPS_DDO_OFFICE mst , CMN_DISTRICT_MST cmn , ZP_REGION_NAME_MST zp "
         * );sb.append(
         * " where rlt.ZP_DDO_CODE = org.DDO_CODE and org.DDO_TYPE = admin.ADMIN_CODE and admin.ADMIN_CODE in (2,3,4,5,12,13,27,26,25,24,23,22,21,20) and rlt.status in (0,1) and mst.DDO_CODE = org.DDO_CODE and mst.DISTRICT = cmn.DISTRICT_ID and cmn.LANG_ID = '1' and cmn.STATE_ID = '15' and zp.REGION_CODE = cmn.REGION_CODE group by admin.ADMIN_NAME  "
         * );
         */
        sb
                .append("  select DESCRIPTION||'##'||DATA_COUNT  from DASHBOARD_CHART where CHART_ID = '1' ORDER by CHART_SUB_ID  ");

        this.logger.info("Query to get no of offices coverd: " + sb.toString());
        final Query query = session.createSQLQuery(sb.toString());
        lstNoOfOfficesCovered = query.list();
        if (lstNoOfOfficesCovered.size() > 0)
        {
            for (int i = 0; i < lstNoOfOfficesCovered.size(); i++)
            {
                final String tmp[] = lstNoOfOfficesCovered.get(i).toString().split("##");
                lngTotalNoOfOfficesCovered = lngTotalNoOfOfficesCovered + Long.parseLong(tmp[1]);
            }
            for (int i = 0; i < lstNoOfOfficesCovered.size(); i++)
            {
                final String tmp[] = lstNoOfOfficesCovered.get(i).toString().split("##");
                // lngTotalNoOfOfficesCovered=lngTotalNoOfOfficesCovered+Long.parseLong(tmp[1]);
                // strNoOfOfficesCovered=strNoOfOfficesCovered.replace("\n",
                // "").replace("\r",
                // "").concat("['".concat(tmp[0]).concat("',").concat(tmp[1]).concat("],"));
                this.logger.info("total no.: " + lngTotalNoOfOfficesCovered + " " + tmp[1]);
                this.logger.info("calculatePercentage(lngTotalNoOfOfficesCovered,tmp[1]): "
                        + this.calculatePercentage(lngTotalNoOfOfficesCovered, tmp[1]));
                strNoOfOfficesCoveredAdminWise = strNoOfOfficesCoveredAdminWise.replace("\n", "").replace("\r", "")
                        .concat(
                                "['".concat(tmp[0]).concat("',").concat(tmp[1]).concat(",'").concat(
                                        this.calculatePercentage(lngTotalNoOfOfficesCovered, tmp[1])).concat("'")
                                        .concat("],"));

            }
            strNoOfOfficesCoveredAdminWise.replace("\n", "").replace("\r", "");
            strNoOfOfficesCoveredAdminWise = strNoOfOfficesCoveredAdminWise.substring(0, strNoOfOfficesCoveredAdminWise
                    .length() - 1);
            this.logger.info("String to be display on screen: " + strNoOfOfficesCoveredAdminWise);
        }

        strTotalNoOfOfficesCovered = String.valueOf(lngTotalNoOfOfficesCovered);
        this.logger.info("Total number of schools#######: " + strTotalNoOfOfficesCovered);

        strNoOfOfficesCovered[0] = strNoOfOfficesCoveredAdminWise;
        strNoOfOfficesCovered[1] = strTotalNoOfOfficesCovered;
        return strNoOfOfficesCovered;
    }
}
