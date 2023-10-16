package com.tcs.sgv.pension.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;

public class pensionUtil {
	
	private static final Log logger = LogFactory.getLog(pensionUtil.class);
	
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
	            logger.error("Error in PensionUtil -> requestArraySetter. Error while setting parameter for : "
	                    + lStrParam);
	        }
	        return lStrVal;
	    }
}
