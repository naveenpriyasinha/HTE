/**
 * package : com.tcs.sgv.onlinebillprep.dao 
 * @verion : 1.0
 * @author : Keyur Shah, Amit Singh. 
 ** 
 */
package com.tcs.sgv.onlinebillprep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.SgvaMonthMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.SgvoDeptMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;

/**
*	BillCommonDAOImpl
*	Its a DAO for Methods Common to all Bills..
*	
*	@author Keyur Shah, Amit Singh
*	@version 1.0
*/

public class BillCommonDAOImpl implements BillCommonDAO
{
	/* Global Variable for Logger Class */
	private Log logger = LogFactory.getLog(getClass());

	/* Global Variable for Session Class */
	Session ghibSession = null;
    Connection lconn = null;

    public BillCommonDAOImpl(SessionFactory sessionFactory)
    {
        ghibSession = sessionFactory.getCurrentSession();
    }


    /**
     * Method for get a Financial Year
     * @author 602399
     * 
     */
    public SgvcFinYearMst getFinYrInfo(Date lDate, Long lLangId)
    {
        StringBuffer lSBQuery = new StringBuffer();
        SgvcFinYearMst lObjFinYrVO = null;

        try
        {
            lSBQuery.append(" FROM SgvcFinYearMst A WHERE ");
            lSBQuery.append(" :date BETWEEN A.fromDate AND A.toDate ");
            lSBQuery.append(" AND A.langId = :langId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            SimpleDateFormat lDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            lQuery.setParameter("date", lDateFormat.parse(lDateFormat.format(lDate)));
            lQuery.setParameter("langId", OnlineBillUtil.getLangById(lLangId));

            logger.info("Query for getFinYrInfo : " + lQuery.toString());
            logger.info("And Parameter is " + lDate + " " + lLangId);

            List lList = lQuery.list();

            if (lList != null && lList.size() > 0)
            {
                lObjFinYrVO = (SgvcFinYearMst) lList.get(0);
            }
        }
        catch (Exception e)
        {
            logger.error("Error in getFinYrInfo is : " + e, e);
        }

        return lObjFinYrVO;
    }


    /**
     * Method for get a Department Details.
     * @author 602399
     * 
     */
    public SgvoDeptMst getDeptDtls(String lStrDeptId, Long lLngLangId)
    {
        SgvoDeptMst lReturnObj = null;

        try
        {
            StringBuffer lSBQuery = new StringBuffer();

            lSBQuery.append(" FROM SgvoDeptMst A ");
            lSBQuery.append(" WHERE A.id.deptId = :deptId ");
            lSBQuery.append(" AND A.id.langId = :langId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("deptId", lStrDeptId);
            lQuery.setParameter("langId", OnlineBillUtil.getLangById(lLngLangId));

            logger.info("Query for getFinYrInfo : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDeptId + " " + lLngLangId);

            List lList = lQuery.list();

            if (lList != null && lList.size() > 0)
            {
                lReturnObj = (SgvoDeptMst) lList.get(0);
            }
        }
        catch (Exception e)
        {
            logger.error("Error in getDeptDtls is : " + e, e);
        }

        return lReturnObj;
    }


    /**
     * Method for get a Month Details.
     * @author 602399
     * 
     */
    public List getMonthDtls(Long lLngLangId)
    {
        List<CommonVO> lReturnList = null;

        try
        {
            StringBuffer lSBQuery = new StringBuffer();

            lSBQuery.append(" FROM SgvaMonthMst A ");
            lSBQuery.append(" WHERE A.langId = :langId ");
            lSBQuery.append(" ORDER BY A.monthNo ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("langId", OnlineBillUtil.getLangById(lLngLangId));

            logger.info("Query for getMonthDtls : " + lQuery.toString());
            logger.info("And Parameter is " + lLngLangId);

            List lList = lQuery.list();
            if (lList != null && lList.size() > 0)
            {
                lReturnList = new ArrayList<CommonVO>();

                CommonVO lComVo = null;
                Iterator lItr = lList.iterator();

                while (lItr.hasNext())
                {
                    SgvaMonthMst lMonthMstObj = (SgvaMonthMst) lItr.next();
                    lComVo = new CommonVO();
                    lComVo.setCommonId(lMonthMstObj.getMonthCode());
                    lComVo.setCommonDesc(lMonthMstObj.getMonthName());

                    lReturnList.add(lComVo);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in getMonthDtls is : " + e, e);
        }

        return lReturnList;
    }
    
    /**
     * 	Method for get a Parent Post Id.
     */
    public List getParentPost(Long lLngPostId)
    {
        StringBuffer lSB = new StringBuffer();

        lSB.append(" SELECT sQuery.POST_ID FROM (SELECT P.POST_ID, P.PARENT_POST_ID FROM ORG_POST_MST P, ORG_POST_DETAILS_RLT PD ");
        lSB.append(" WHERE PD.POST_ID = P.POST_ID START WITH P.POST_ID = :postId");
        lSB.append(" CONNECT BY PRIOR P.PARENT_POST_ID = P.POST_ID) sQuery ");
        lSB.append(" WHERE sQuery.PARENT_POST_ID = -1");

        Query lQuery = ghibSession.createSQLQuery(lSB.toString());
        lQuery.setParameter("postId", lLngPostId);

        List lLstParentPost = lQuery.list();

        return lLstParentPost;
    }

    /**
     * Saves the Digital Signatures Mapping Table Details
     * 
     * @param String ,int,String,String,int,String,String
     *            lstrappId,lintseq,lstrpkVal,lstrfkVal,lintrowNum,lstrrowId,lstrkey
     * @return Integer
     */

    public int insertDigiMapping(String lstrappId, int lintseq, String lstrpkVal, String lstrfkVal, int lintrowNum,
            String lstrrowId, String lstrkey)
    {

        if (lstrfkVal == null)
        {
            lstrfkVal = "";
        }
        if (lstrrowId == null)
        {
            lstrrowId = "";
        }
        int result;
        try
        {
            lconn = ghibSession.connection();
            PreparedStatement pst = lconn.prepareStatement("insert into SGVA_MAPPING_KEY_TEMP values(?,?,?,?,?,?,?)");
            pst.setString(1, lstrappId);
            pst.setInt(2, lintseq);
            pst.setString(3, lstrpkVal);
            pst.setString(4, "");
            pst.setInt(5, lintrowNum);
            pst.setString(6, "");
            pst.setString(7, lstrkey);
            result = pst.executeUpdate();
            lconn.commit();
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }

    }
    
    /**
     * Returns Month Name from the Specified Bill No and LangId. 
     *  
     * @param Long,Long
     *           lLngLangId,lLngBillNo
     * @return lStrMnthName String
     */
    public String getMonthName(Long lLngLangId, Long lLngBillNo)
    {	
    	String lStrMnthName = null;
    	StringBuffer lSB = new StringBuffer();
    	
    	String lStrLangName = OnlineBillUtil.getLangById( lLngLangId);
    	try{
    		lSB.append(" SELECT m.monthName FROM TrnMedblHdr h, SgvaMonthMst m ");
        	lSB.append(" WHERE m.monthCode = h.monthCode AND m.langId = :langName ");
        	lSB.append(" AND h.billNo = :billNo " );
        	
        	
        	Query lQuery = ghibSession.createQuery(lSB.toString());
        	lQuery.setParameter("billNo", lLngBillNo);
        	lQuery.setParameter("langName", lStrLangName);
        
        	List lList = lQuery.list();
        	   	
        	if(lList != null && lList.size() > 0)
        	{
        		lStrMnthName = lList.get(0).toString();
        	}

    	}
    	
    	 catch (Exception e)
         {
             logger.error("Error in getMonthName is : " + e, e);
         }
    	    	
    		return lStrMnthName;
    }
    
    /**
     * Returns List of EDP Details from the Specified Bill No and LangId. 
     *  
     * @param Long,Long
     *           lLngBillNo,lLngLangId
     * @return lLstEDPDtls List
     */
    public List getBillEDPDtls(Long lLngBillNo, Long lLngLangId)
    {
    	StringBuffer lSB = new StringBuffer();
    	Map lMapInput = new HashMap();
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    	
    	lSB.append(" SELECT BE.objHdCode, BE.edpCode,  BE.edpDesc, BE.addDedFlag, TBE.edpAmt " );
    	lSB.append(" FROM RltBillTypeEdp BE, TrnBillEdpDtls TBE WHERE BE.typeEdpId = TBE.typeEdpId" );
    	lSB.append(" AND BE.langId = :langId AND TBE.billNo = :billNo " );
    	
    	Query lQuery = ghibSession.createQuery(lSB.toString());
    	
    	lQuery.setParameter("billNo", lLngBillNo);
    	lQuery.setParameter("langId", lLngLangId);
        
        objRes.setResultValue(lMapInput);
        List lLstEDPDtls = lQuery.list();
	   	
    	return lLstEDPDtls;
    	
    }
    
    /**
     * Returns List of EDPId associated with the Specified Bill No 
     *  
     * @param Long
     *           lLngBillNo
     * @return lLstEDPDtls List
     */
    public List getBillEdpId(Long lLngBillNo)
    {

        StringBuffer lSB = new StringBuffer();
        Map lMapInput = new HashMap();
       try{
           
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        //System.out.println("*******************This is at edp id method");
        lSB.append(" SELECT TBE.billEdpId ");
        lSB.append(" FROM TrnBillEdpDtls TBE ,RltBillTypeEdp BE WHERE BE.typeEdpId = TBE.typeEdpId");
        lSB.append(" AND TBE.billNo = :billNo ");

        Query lQuery = ghibSession.createQuery(lSB.toString());

        lQuery.setParameter("billNo", lLngBillNo);
       

        objRes.setResultValue(lMapInput);
        List lLstEDPDtls = lQuery.list();
        //System.out.println("This is at end of edp id method");
        return lLstEDPDtls;
       }
       catch(Exception e){
           logger.equals("error is at get edpId's "+e);
           return null;
       }
   }
}