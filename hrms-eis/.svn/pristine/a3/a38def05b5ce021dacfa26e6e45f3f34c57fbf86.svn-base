package com.tcs.sgv.filter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tcs.sgv.acl.acegilogin.exception.RequestSecurityFaliureException;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.filter.dao.DashBoardDAOImpl;
//import com.tcs.sgv.filter.service.XSSFilterImpl;

public class DashBoard extends XSSFilterImpl implements Filter
{
    protected ApplicationEventPublisher eventPublisher;
    protected MessageSourceAccessor messages;
    private CommonsMultipartResolver multipartResolver;
    private static ResourceBundle skipParamResourceBundle = null;
    private String errorPage;
    private String dashboardPage;
    static final String FILTER_APPLIED = "_dashboard_filter";

    public DashBoard()
    {
        this.messages = SpringSecurityMessageSource.getAccessor();
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull(this.multipartResolver, "multipartResolver must be specified");
    }

    private boolean checkForSecurity(final HttpServletRequest paramHttpServletRequest)
            throws RequestSecurityFaliureException, ParseException
    {
        Boolean flag = false;
        this.logger.info("Inside our filter for dashboard");
        final Enumeration localEnumeration = paramHttpServletRequest.getParameterNames();
        String str = null;
        String str1 = null;
        String executeFlag = null;
        // String chartType=null;
        String logedInUsers = null;
        String noOfOfficesCovered[] = new String[2];
        String divisionWiseSchools[] = new String[2];
        String divisionWiseEmp[] = new String[2];
        String divisionWisePaybillGenerated[] = new String[2];
        String divisionWiseAmountDisbursed[] = new String[2];
        String displaySchoolEmpCountDivisionWise = null;
        String strDisplaySchoolEmpCountDivisionWise = null;
        String divisionWiseAmountDisbursedForLast13MonthsLineChart = null;

        String displayPayBillCountForFY1415DivisionWise = null;
        String strDisplayPayBillCountForFY1415DivisionWise = null;

        String displayAmountDisbursedForFY1415DivisionWise = null;
        String strDisplayAmountDisbursedForFY1415DivisionWise = null;

        String displaySchoolCountAdminWise = null;
        String strDisplaySchoolCountAdminWise = null;

        // String displayString=null;
        // String displayCount=null;

        while (localEnumeration.hasMoreElements())
        {
            str = (String) localEnumeration.nextElement();
            str1 = paramHttpServletRequest.getParameter(str);
            this.logger.info("str1 is ::::::" + str1);
            this.logger.info("str is ::::::" + str);

            if (str != null && !str.equals("") && str.equals("viewName") && str1.equals("dashboardRedirect"))
            {
                flag = true;
            }
            if (str != null && !str.equals("") && str.equals("executeFlag"))
            {
                executeFlag = str1;
                this.logger.info("executeFlag" + executeFlag);
            }

            if (str != null && !str.equals("") && str.equals("displaySchoolEmpCountDivisionWise"))
            {
                displaySchoolEmpCountDivisionWise = str1;
                this.logger.info("displaySchoolEmpCountDivisionWise (division): " + displaySchoolEmpCountDivisionWise);
            }
            if (str != null && !str.equals("") && str.equals("displayPayBillCountForFY1415DivisionWise"))
            {
                displayPayBillCountForFY1415DivisionWise = str1;
                this.logger.info("displaySchoolEmpCountDivisionWise (division): "
                        + displayPayBillCountForFY1415DivisionWise);
            }
            if (str != null && !str.equals("") && str.equals("displayAmountDisbursedForFY1415DivisionWise"))
            {
                displayAmountDisbursedForFY1415DivisionWise = str1;
                this.logger.info("displayAmountDisbursedForFY1415DivisionWise (division): "
                        + displayAmountDisbursedForFY1415DivisionWise);
            }
            if (str != null && !str.equals("") && str.equals("displaySchoolCountAdminWise"))
            {
                displaySchoolCountAdminWise = str1;
                this.logger.info("displayAmountDisbursedForFY1415DivisionWise (division): "
                        + displaySchoolCountAdminWise);
            }

            /*
             * if(str!= null && !str.equals("") && str.equals("displayString")){
             * displayString = str1;logger.info(
             * "displayString for Division Wise Number of Schools Covered: "
             * +displayString); } if(str!= null && !str.equals("") &&
             * str.equals("displayCount")){ displayCount = str1;logger.info(
             * "displayCount for Division Wise Number of Schools Covered: "
             * +displayCount); }
             */
        }
        final ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();
        final DashBoardDAOImpl objDashBoardDAOImpl = new DashBoardDAOImpl(null, serviceLocator.getSessionFactory());

        // noOfEmployees= objDashBoardDAOImpl.getNoOfEmployees();
        // paramHttpServletRequest.setAttribute("noOfEmployees", noOfEmployees);
        if (executeFlag != null && !executeFlag.equals("") && executeFlag.equals("1"))
        {

            logedInUsers = objDashBoardDAOImpl.getLogedinusers();
            paramHttpServletRequest.setAttribute("logedInUsers", logedInUsers);

            // Commented by Kinjal for Changes in SOJ
            /*
             * noOfOfficesCovered=objDashBoardDAOImpl.getNoOfOfficesCovered();
             * paramHttpServletRequest
             * .setAttribute("strNoOfOfficesCoveredAdminWise",
             * noOfOfficesCovered[0]);
             * paramHttpServletRequest.setAttribute("strTotalNoOfOfficesCovered"
             * , noOfOfficesCovered[1]);
             */

            divisionWiseSchools = objDashBoardDAOImpl.getNoOfDivisionWiseSchools();
            paramHttpServletRequest.setAttribute("strNoOfDivisionWiseSchoolsTodisplay", divisionWiseSchools[0]);
            paramHttpServletRequest.setAttribute("strTotalNoOfDivisionWiseSchools", divisionWiseSchools[1]);

            divisionWiseEmp = objDashBoardDAOImpl.getNoOfDivisionWiseEmp();
            paramHttpServletRequest.setAttribute("strNoOfDivisionWiseEmpToDisplay", divisionWiseEmp[0]);
            paramHttpServletRequest.setAttribute("strTotalNoOfDivisionWiseEmp", divisionWiseEmp[1]);

            // changed from paybills to employees in paybills for fy 2014 15

            // Commented by Kinjal for Changes in SOJ
            /*
             * divisionWisePaybillGenerated=objDashBoardDAOImpl.getNoOfDivisionWisePaybillGenerated
             * ();paramHttpServletRequest.setAttribute(
             * "strNoOfDivisionWisePaybillGeneratedToDisplay",
             * divisionWisePaybillGenerated[0]);
             * paramHttpServletRequest.setAttribute
             * ("strTotalNoOfDivisionWisePaybillGenerated",
             * divisionWisePaybillGenerated[1]);
             */

            divisionWiseAmountDisbursed = objDashBoardDAOImpl.getDivisionWiseAmountDisbursed();
            paramHttpServletRequest.setAttribute("strDivisionWiseAmountDisbursedToDisplay",
                    divisionWiseAmountDisbursed[0]);
            paramHttpServletRequest.setAttribute("strTotalDivisionWiseAmountDisbursed", divisionWiseAmountDisbursed[1]);

            // commented for not to display chart 6
            /*
             * divisionWiseAmountDisbursedCurrentMonth=objDashBoardDAOImpl.getDivisionWiseAmountDisbursedCurrentMonth
             * ();paramHttpServletRequest.setAttribute(
             * "strDivisionWiseAmountDisbursedCurrentMonthToDisplay",
             * divisionWiseAmountDisbursedCurrentMonth[0]);
             * paramHttpServletRequest
             * .setAttribute("strTotalDivisionWiseAmountDisbursedCurrentMonth",
             * divisionWiseAmountDisbursedCurrentMonth[1]);
             */

            divisionWiseAmountDisbursedForLast13MonthsLineChart = objDashBoardDAOImpl
                    .getDivisionWiseAmountDisbursedForLast13MonthsLineChart();
            paramHttpServletRequest.setAttribute("divisionWiseAmountDisbursedForLast13MonthsLineChart",
                    divisionWiseAmountDisbursedForLast13MonthsLineChart);

            // /Commented by Kinjal for Changes in SOJ
            /*
             * divisionWiseEmployeesCoveredinCurrentMonthPaybill=objDashBoardDAOImpl
             * .getDivisionWiseEmployeesCoveredinCurrentMonthPaybill();
             * paramHttpServletRequest.setAttribute(
             * "strNoOfDivisionWisePaybillGeneratedCurrentMonthToDisplay",
             * divisionWiseEmployeesCoveredinCurrentMonthPaybill[0]);
             * paramHttpServletRequest
             * .setAttribute("strTotalNoOfDivisionWisePaybillGeneratedCurrentMonth"
             * , divisionWiseEmployeesCoveredinCurrentMonthPaybill[1]);
             */

            // for chart1 popup
            if (displaySchoolCountAdminWise != null && !displaySchoolCountAdminWise.equals(""))
            {

                final String typeOfSchool = displaySchoolCountAdminWise;
                this.logger.info("typeOfSchool: " + typeOfSchool);

                noOfOfficesCovered = objDashBoardDAOImpl.getNoOfOfficesCovered();
                strDisplaySchoolCountAdminWise = objDashBoardDAOImpl.getDisplaySchoolCountAdminWise(typeOfSchool);
                paramHttpServletRequest.setAttribute("strNoOfOfficesCoveredAdminWise", noOfOfficesCovered[0]);
                paramHttpServletRequest.setAttribute("strTotalNoOfOfficesCovered", noOfOfficesCovered[1]);

                paramHttpServletRequest.setAttribute("displayPopupFlag", "Y");
                paramHttpServletRequest.setAttribute("displayPopupString", strDisplaySchoolCountAdminWise);
            }

            // for chart2 popup
            if (displaySchoolEmpCountDivisionWise != null && !displaySchoolEmpCountDivisionWise.equals(""))
            {
                final String division = displaySchoolEmpCountDivisionWise;
                this.logger.info("division: " + division);
                // executeFlag=1&chartType=2

                divisionWiseSchools = objDashBoardDAOImpl.getNoOfDivisionWiseSchools();
                strDisplaySchoolEmpCountDivisionWise = objDashBoardDAOImpl
                        .getDisplaySchoolEmpCountDivisionWise(division);

                paramHttpServletRequest.setAttribute("strNoOfDivisionWiseSchoolsTodisplay", divisionWiseSchools[0]);
                paramHttpServletRequest.setAttribute("strTotalNoOfDivisionWiseSchools", divisionWiseSchools[1]);

                paramHttpServletRequest.setAttribute("displayPopupFlag", "Y");
                paramHttpServletRequest.setAttribute("displayPopupString", strDisplaySchoolEmpCountDivisionWise);
            }

            // for chart4 popup
            if (displayPayBillCountForFY1415DivisionWise != null
                    && !displayPayBillCountForFY1415DivisionWise.equals(""))
            {
                final String division = displayPayBillCountForFY1415DivisionWise;
                this.logger.info("division: " + division);

                divisionWisePaybillGenerated = objDashBoardDAOImpl.getNoOfDivisionWisePaybillGenerated();
                strDisplayPayBillCountForFY1415DivisionWise = objDashBoardDAOImpl
                        .getDisplayPayBillCountForFY1415DivisionWise(division);

                paramHttpServletRequest.setAttribute("strNoOfDivisionWisePaybillGeneratedToDisplay",
                        divisionWisePaybillGenerated[0]);
                paramHttpServletRequest.setAttribute("strTotalNoOfDivisionWisePaybillGenerated",
                        divisionWisePaybillGenerated[1]);

                paramHttpServletRequest.setAttribute("displayPopupFlag", "Y");
                paramHttpServletRequest.setAttribute("displayPopupString", strDisplayPayBillCountForFY1415DivisionWise);
            }

            // for chart5 popup
            if (displayAmountDisbursedForFY1415DivisionWise != null
                    && !displayAmountDisbursedForFY1415DivisionWise.equals(""))
            {
                final String division = displayAmountDisbursedForFY1415DivisionWise;
                this.logger.info("division: " + division);

                divisionWiseAmountDisbursed = objDashBoardDAOImpl.getDivisionWiseAmountDisbursed();
                strDisplayAmountDisbursedForFY1415DivisionWise = objDashBoardDAOImpl
                        .getDisplayAmountDisbursedForFY1415DivisionWise(division);
                paramHttpServletRequest.setAttribute("strDivisionWiseAmountDisbursedToDisplay",
                        divisionWiseAmountDisbursed[0]);
                paramHttpServletRequest.setAttribute("strTotalDivisionWiseAmountDisbursed",
                        divisionWiseAmountDisbursed[1]);

                paramHttpServletRequest.setAttribute("displayPopupFlag", "Y");
                paramHttpServletRequest.setAttribute("displayPopupString",
                        strDisplayAmountDisbursedForFY1415DivisionWise);
            }

            // paramHttpServletRequest.setAttribute("msg",
            // "hi this is samadhan");

        }

        else
        {
            this.logger.info("Called from outside!");
        }

        return flag;
    }

    @Override
    @SuppressWarnings("null")
    protected void doFilterHttp(HttpServletRequest paramHttpServletRequest,
            final HttpServletResponse paramHttpServletResponse, final FilterChain paramFilterChain) throws IOException,
            ServletException
    {

        this.logger.info("Inside Dashboard");

        if (paramHttpServletRequest.getAttribute("_dashboard_filter") != null)
        {
            paramHttpServletRequest.setAttribute("_dashboard_filter", Boolean.TRUE);
            paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);

            return;
        }
        try
        {
            final Boolean flag = this.checkForSecurity(paramHttpServletRequest);
            if (flag)
            {
                this.logger.info("before request dispatcher for dashboard");
                final RequestDispatcher localRequestDispatcher = paramHttpServletRequest
                        .getRequestDispatcher("/WEB-INF/jsp/common/DashBoardView.jsp");
                localRequestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
                this.logger.info("after request dispatcher for dashboard");
            }

            else
            {
                paramHttpServletResponse.addHeader("X-FRAME-OPTIONS", "DENY");
                paramFilterChain.doFilter(paramHttpServletRequest, paramHttpServletResponse);
            }
        } catch (final Exception e)
        {
            this.logger.info("Inside catch in filter");

            e.printStackTrace();
            paramHttpServletRequest = null;
            final RequestDispatcher localRequestDispatcher = paramHttpServletRequest
                    .getRequestDispatcher(this.errorPage);
            localRequestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
        }
    }

    public String getDashboardPage()
    {
        return this.dashboardPage;
    }

    @Override
    public CommonsMultipartResolver getMultipartResolver()
    {
        return this.multipartResolver;
    }

    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher paramApplicationEventPublisher)
    {
        this.eventPublisher = paramApplicationEventPublisher;
    }

    public void setDashboardPage(final String dashboardPage)
    {
        if (dashboardPage != null && !dashboardPage.startsWith("/"))
        {
            throw new IllegalArgumentException("dashboardPage must begin with '/'");
        }
        this.dashboardPage = dashboardPage;
    }

    @Override
    public void setErrorPage(final String paramString)
    {
        if (paramString != null && !paramString.startsWith("/"))
        {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = paramString;
    }

    @Override
    public void setMessageSource(final MessageSource paramMessageSource)
    {
        this.messages = new MessageSourceAccessor(paramMessageSource);
    }

    @Override
    public void setMultipartResolver(final CommonsMultipartResolver paramCommonsMultipartResolver)
    {
        this.multipartResolver = paramCommonsMultipartResolver;
    }

}
