package com.tcs.sgv.pension.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.service.ServiceLocator;

import com.tcs.sgv.onlinebillprep.dao.BillCommonDAO;
import com.tcs.sgv.onlinebillprep.dao.BillCommonDAOImpl;

public class OnlineBillUtil
{
    private static final Log logger = LogFactory.getLog(OnlineBillUtil.class);

    private static ResourceBundle bundleConst = ResourceBundle
            .getBundle("resources/onlinebillprep/OnlineBillConstants");


    public static String requestSetter(String lStrParam, HttpServletRequest request)
    {
        String lStrVal = "";

        try
        {
            if ((String) StringUtility.getParameter(lStrParam, request) != null)
            {
                lStrVal = (String) StringUtility.getParameter(lStrParam, request);
            }
        }
        catch (Exception e)
        {
            logger.error("Error in OnlineBillUtil -> requestSetter. Error while setting parameter for : " + lStrParam);
        }
        return lStrVal.trim();
    }


    public static String[] requestArraySetter(String lStrParam, HttpServletRequest request)
    {
        String lStrVal[] = null;

        try
        {
            lStrVal = (String[]) StringUtility.getParameterValues(lStrParam, request);

            if (lStrVal != null)
            {
                for (int lIntCnt = 0; lIntCnt < lStrVal.length; lIntCnt++)
                {
                    if (lStrVal[lIntCnt] == null)
                    {
                        lStrVal[lIntCnt] = "";
                    }
                    else
                    {
                        lStrVal[lIntCnt] = lStrVal[lIntCnt].trim();
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in OnlineBillUtil -> requestArraySetter. Error while setting parameter for : "
                    + lStrParam);
        }
        return lStrVal;
    }


    public static String getLangById(Long lLngLangId)
    {
        String lStrReturn = null;

        try
        {
            if (lLngLangId == 1)
            {
                lStrReturn = bundleConst.getString("Lang.EN");
            }
            else if (lLngLangId == 2)
            {
                lStrReturn = bundleConst.getString("Lang.GU");
            }
        }
        catch (Exception e)
        {
            logger.error("Error in getLangById is : " + e, e);
        }

        return lStrReturn;
    }


    public static Long getParentPost(Long lLngPostId, SessionFactory objSessionFactory)
    {
        List lLstParentPost = null;
        Long lLngParentPost = null;
        BigDecimal lBdecParentPost = null;
        BillCommonDAO lObjBillCmnDAO = null;

        try
        {
            lObjBillCmnDAO = new BillCommonDAOImpl(objSessionFactory);
            lLstParentPost = lObjBillCmnDAO.getParentPost(lLngPostId);
            lBdecParentPost = (BigDecimal) lLstParentPost.get(0);
            lLngParentPost = Long.parseLong(lBdecParentPost.toString());
        }
        catch (Exception e)
        {
            logger.error("Error in getParentPost is : " + e, e);
        }
        return lLngParentPost;
    }
}
