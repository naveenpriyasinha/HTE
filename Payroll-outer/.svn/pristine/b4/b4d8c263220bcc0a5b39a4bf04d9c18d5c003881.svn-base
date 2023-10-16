package com.tcs.sgv.common.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valuebeans.CommonVO;

/**
 * 
 * @author 602399
 * 
 */

public class DDOInfoDAOImpl implements DDOInfoDAO
{
    Log logger = LogFactory.getLog(getClass());
    Session ghibSession = null;

    public DDOInfoDAOImpl(SessionFactory sessionFactory)
    {
    	ghibSession = sessionFactory.getCurrentSession();
    }


    public List getDDOInfo(String lStrDDOCode, Long lLngLangId, Long lLngDBId)
    {
        List lListReturn = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" FROM OrgDdoMst A WHERE A.ddoCode = :ddoCode ");
            lSBQuery.append(" AND A.langId = :langId AND A.dbId = :dbId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("ddoCode", lStrDDOCode);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getDDOInfo : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDDOCode + " " + lLngLangId + " " + lLngDBId);

            lListReturn = lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOInfo and Error is : " + e, e);
        }

        return lListReturn;
    }


    public List getDDOInfoByPost(Long lLngPostId, Long lLngLangId, Long lLngDBId)
    {
        List lListReturn = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" FROM OrgDdoMst A WHERE A.postId = ");
            lSBQuery.append(" :postId AND A.langId = :langId AND A.dbId = :dbId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("postId", lLngPostId);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getDDOInfoByPost : " + lQuery.toString());
            logger.info("And Parameter is " + lLngPostId + " " + lLngLangId + " " + lLngDBId);
            
            lListReturn = lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOInfoByPost and Error is : " + e, e);
        }

        return lListReturn;
    }


    public List getDDOInfoByCardex(String lStrCardexNo, String lStrOfficeCode, Long lLngLangId, Long lLngDBId)
    {
        List lListReturn = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" SELECT O FROM OrgDdoMst O, RltDdoOrg RO ");
            lSBQuery.append(" WHERE RO.ddoCode = O.ddoCode AND O.cardexNo = :cardexNo ");
            lSBQuery.append(" AND RO.officeCode = :officeCode ");
            lSBQuery.append(" AND O.langId = :langId AND O.dbId = :dbId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("cardexNo", lStrCardexNo);
            lQuery.setParameter("officeCode", lStrOfficeCode);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getDDOInfoByCardex : " + lQuery.toString());
            logger.info("And Parameter is " + lStrCardexNo + " "  + lLngLangId + " " + lLngDBId);
            
            lListReturn = lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOInfoByCardex and Error is : " + e, e);
            e.printStackTrace();
        }

        return lListReturn;
    }

    public List getDDOInfoByNo(String lStrDDONo, String lDDOType, Long lLngLangId, Long lLngDBId)
    {
        List lListReturn = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();
            Query lQuery = null;

            if (lDDOType != null && lDDOType.trim().length() > 0)
            {
                lSBQuery.append(" FROM OrgDdoMst A WHERE A.ddoNo = :ddoNo ");
                lSBQuery.append(" AND A.ddoType = :ddoType AND ");
                lSBQuery.append(" A.langId = :langId AND A.dbId = :dbId ");
                
                lQuery = ghibSession.createQuery(lSBQuery.toString());
                lQuery.setParameter("ddoType", lDDOType);
            }
            else
            {
                lSBQuery.append(" FROM OrgDdoMst A WHERE A.ddoNo = :ddoNo ");
                lSBQuery.append(" AND A.ddoType IN('A', 'D') AND A.langId = :langId AND A.dbId = :dbId ");
                lQuery = ghibSession.createQuery(lSBQuery.toString());
            }

            lQuery.setParameter("ddoNo", lStrDDONo);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getDDOInfoByNo : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDDONo + " " + lDDOType + " " + lLngLangId + " " + lLngDBId);
            
            lListReturn = lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOInfoByNo and Error is : " + e, e);
        }

        return lListReturn;
    }


    public List getBillOfficeForDDO(String lStrDDOCode, Long lLngLangId,
            Long lLngDBId)
    {
        List<CommonVO> lListReturn = new ArrayList<CommonVO>();

        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" SELECT B.locationCode, B.locName FROM RltDdoOffice A, ");
            lSBQuery.append(" CmnLocationMst B WHERE A.loctnCode = B.locationCode ");
            lSBQuery.append(" AND A.ddoCode = :ddoCode AND ");
            lSBQuery.append(" B.cmnLanguageMst.langId = :langId ");
            lSBQuery.append(" AND A.dbId = :dbId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("ddoCode", lStrDDOCode);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getBillOfficeForDDO : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDDOCode + " " + lLngLangId + " " + lLngDBId);
            
            List lListData = lQuery.list();

            if (lListData != null)
            {
                Iterator lItr = lListData.iterator();
                Object[] lObj = null;
                CommonVO lComVo = null;

                while (lItr.hasNext())
                {
                    lObj = (Object[]) lItr.next();
                    if (lObj != null)
                    {
                        lComVo = new CommonVO();
                        lComVo.setCommonId(lObj[0].toString());
                        lComVo.setCommonDesc(lObj[1].toString());
                        lListReturn.add(lComVo);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in getBillOfficeForDDO and Error is : " + e, e);
        }

        return lListReturn;
    }
    
    public List getTrsryOfficeForDDO(String lStrDDOCode, Long lLngLangId)
    {
        List<CommonVO> lListReturn = new ArrayList<CommonVO>();

        StringBuilder lSBQuery = new StringBuilder();

        try
        {
            lSBQuery.append(" select loc.locId, loc.locName from RltDdoOrg rd,");
            lSBQuery.append(" CmnLocationMst loc");
            lSBQuery.append(" where rd.ddoCode = :ddoCode and loc.locId = rd.officeCode and");
            lSBQuery.append(" loc.cmnLanguageMst.langId = :langId");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("ddoCode", lStrDDOCode);
            lQuery.setParameter("langId", lLngLangId);

            logger.info("Query for getTrsryOfficeForDDO : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDDOCode + " " + lLngLangId);
            
            List lLstData = lQuery.list();

            if (lLstData != null)
            {
                Iterator lItr = lLstData.iterator();
                Object[] lObj = null;
                CommonVO lComVo = null;

                while (lItr.hasNext())
                {
                    lObj = (Object[]) lItr.next();
                    
                    if (lObj != null)
                    {
                        lComVo = new CommonVO();
                        lComVo.setCommonId(lObj[0].toString());
                        lComVo.setCommonDesc(lObj[1].toString());
                        lListReturn.add(lComVo);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in getTrsryOfficeForDDO and Error is : " + e, e);
        }

        return lListReturn;
    }
}
