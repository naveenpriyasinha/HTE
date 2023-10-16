package com.tcs.sgv.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.util.IWDMSDBConnection;
import com.tcs.sgv.common.valueobject.SgvaBudbpnMapping;
import com.tcs.sgv.onlinebillprep.util.OnlineBillUtil;

/**
 * @author 602399
 */

public class BudgetHdDtlsDAOImpl implements BudgetHdDtlsDAO
{
	
    private Log glogger = LogFactory.getLog(getClass());

    private Session ghibSession = null;

    private Long gLngFinYrId = null;


    public BudgetHdDtlsDAOImpl(Long lLngFinYrId, SessionFactory sessionFactory)
    {
        this.gLngFinYrId = lLngFinYrId;
        ghibSession = sessionFactory.getCurrentSession();
    }


    public BudgetHdDtlsDAOImpl(SessionFactory sessionFactory)
    {
        this.gLngFinYrId = getNextFinYrID(sessionFactory);
        ghibSession = sessionFactory.getCurrentSession();
    }


    public boolean isValidBudgetHeads(String lStrDemandCode, String lStrBudMjrHdCode, String lStrBudSubMjrHdCode,
            String lStrBudMinHdCode, String lStrBudSubHdCode, String lStrDtlsHd, Long lLongLangId, String lStrMjrHdType)
    {
        boolean isValid = false;

        try
        {
            StringBuffer lSBQuery = new StringBuffer();
            Query lQuery = null;

            //if (lStrDtlsHd.equals("00"))
            {
                lSBQuery.append(" SELECT x.demandCode FROM SgvaBudsubhdMst x WHERE ");
                boolean isAddAND = false;

                if (!lStrDemandCode.equals(""))
                {
                    isAddAND = true;
                    lSBQuery.append(" x.demandCode = :demandCode ");
                }
                if (!lStrBudMjrHdCode.equals(""))
                {
                    if (isAddAND)
                    {
                        lSBQuery.append(" AND ");
                    }
                    else
                    {
                        isAddAND = true;
                    }
                    lSBQuery.append(" x.budmjrhdCode = :budmjrhdCode ");
                }
                
                if((!lStrBudMjrHdCode.equals("")) && (! lStrMjrHdType.equals("")))
                {
                    if(lStrMjrHdType.equalsIgnoreCase("R"))
                    {
                        if (isAddAND)
                        {
                            lSBQuery.append(" AND ");
                        }
                        else
                        {
                            isAddAND = true;
                        }
                        lSBQuery.append(" cast(x.budmjrhdCode as int) < 2000 ");
                    }
                }
                
                if (!lStrBudSubMjrHdCode.equals(""))
                {
                    if (isAddAND)
                    {
                        lSBQuery.append(" AND ");
                    }
                    else
                    {
                        isAddAND = true;
                    }
                    lSBQuery.append(" x.budsubmjrhdCode = :budsubmjrhdCode ");
                }
                if (!lStrBudMinHdCode.equals(""))
                {
                    if (isAddAND)
                    {
                        lSBQuery.append(" AND ");
                    }
                    else
                    {
                        isAddAND = true;
                    }
                    lSBQuery.append(" x.budminhdCode = :budminhdCode ");
                }
                if (!lStrBudSubHdCode.equals(""))
                {
                    if (isAddAND)
                    {
                        lSBQuery.append(" AND ");
                    }
                    else
                    {
                        isAddAND = true;
                    }
                    lSBQuery.append(" x.budsubhdCode = :budsubhdCode ");
                }
                if (isAddAND)
                {
                    lSBQuery.append(" AND x.langId = :langId ");

                }
                else
                {
                    lSBQuery.append(" x.langId = :langId ");
                }

                lSBQuery.append(" AND x.finYrId = :finYrId ");

                // Query is create
                lQuery = ghibSession.createQuery(lSBQuery.toString());

                if (!lStrDemandCode.equals(""))
                {
                    lQuery.setParameter("demandCode", lStrDemandCode);
                }

                if (!lStrBudMjrHdCode.equals(""))
                {
                    lQuery.setParameter("budmjrhdCode", lStrBudMjrHdCode);
                }

                if (!lStrBudSubMjrHdCode.equals(""))
                {
                    lQuery.setParameter("budsubmjrhdCode", lStrBudSubMjrHdCode);
                }

                if (!lStrBudMinHdCode.equals(""))
                {
                    lQuery.setParameter("budminhdCode", lStrBudMinHdCode);
                }

                if (!lStrBudSubHdCode.equals(""))
                {
                    lQuery.setParameter("budsubhdCode", lStrBudSubHdCode);
                }

                lQuery.setParameter("langId", OnlineBillUtil.getLangById(lLongLangId));
                lQuery.setParameter("finYrId", gLngFinYrId);
            }
            /*
            else
            {
                lSBQuery.append(" SELECT x.buddemandCode FROM SgvaBuddtlhdMst x WHERE ");
                boolean isAddAND = false;

                if (!lStrDemandCode.equals(""))
                {
                    isAddAND = true;
                    lSBQuery.append(" x.buddemandCode = :buddemandCode ");
                }
                if (!lStrBudMjrHdCode.equals(""))
                {
                    if (isAddAND)
                    {
                        lSBQuery.append(" AND ");
                    }
                    else
                    {
                        isAddAND = true;
                    }
                    lSBQuery.append(" x.budmjrhdCode = :budmjrhdCode ");
                }
                if (!lStrBudSubMjrHdCode.equals(""))
                {
                    if (isAddAND)
                    {
                        lSBQuery.append(" AND ");
                    }
                    else
                    {
                        isAddAND = true;
                    }
                    lSBQuery.append(" x.budsubmjrhdCode = :budsubmjrhdCode ");
                }
                if (!lStrBudMinHdCode.equals(""))
                {
                    if (isAddAND)
                    {
                        lSBQuery.append(" AND ");
                    }
                    else
                    {
                        isAddAND = true;
                    }
                    lSBQuery.append(" x.budminhdCode = :budminhdCode ");
                }
                if (!lStrBudSubHdCode.equals(""))
                {
                    if (isAddAND)
                    {
                        lSBQuery.append(" AND ");
                    }
                    else
                    {
                        isAddAND = true;
                    }
                    lSBQuery.append(" x.budsubhdCode = :budsubhdCode ");
                }
                if (isAddAND)
                {
                    lSBQuery.append(" AND ");
                }
                else
                {
                    isAddAND = true;
                }

                lSBQuery.append(" x.buddtlhdCode = :buddtlhdCode ");

                if (isAddAND == true)
                {
                    lSBQuery.append(" AND ");
                }
                lSBQuery.append(" x.langId = :langId ");

                // Query is Created.
                lQuery = ghibSession.createQuery(lSBQuery.toString());

                if (!lStrDemandCode.equals(""))
                {
                    lQuery.setParameter("buddemandCode", lStrDemandCode);
                }
                if (!lStrBudMjrHdCode.equals(""))
                {
                    lQuery.setParameter("budmjrhdCode", lStrBudMjrHdCode);
                }
                if (!lStrBudSubMjrHdCode.equals(""))
                {
                    lQuery.setParameter("budsubmjrhdCode", lStrBudSubMjrHdCode);
                }
                if (!lStrBudMinHdCode.equals(""))
                {
                    lQuery.setParameter("budminhdCode", lStrBudMinHdCode);
                }
                if (!lStrBudSubHdCode.equals(""))
                {
                    lQuery.setParameter("budsubhdCode", lStrBudSubHdCode);
                }
                lQuery.setParameter("buddtlhdCode", lStrDtlsHd);
                lQuery.setParameter("langId", OnlineBillUtil.getLangById(lLongLangId));
            }
            */

            glogger.info("Query From isValidBudgetHeads " + lQuery.toString());
            glogger.info("And Parameter is " + lStrDemandCode + " " + lStrBudMjrHdCode + " " + lStrBudSubMjrHdCode
                    + " " + lStrBudMinHdCode + " " + lStrBudSubHdCode + " " + lStrDtlsHd + " " + lLongLangId);

            List lList = lQuery.list();

            if (lList != null && lList.size() > 0)
            {
                isValid = true;
            }
        }
        catch (Exception e)
        {
            glogger.error("Error in validateBudgetHeads and Error is : " + e, e);
        }

        return isValid;
    }


    public List getGrantHeadsForDDO(String lStrDDOCode, String lStrPlNpl, String lStrDDOStatus, Long lLngFinYrId, Long lLngLangId)
    {
        Connection lCon = null;
        PreparedStatement lPstmt = null;
        ResultSet lRs = null;

        List<Object[]> lListReturn = new ArrayList<Object[]>();
        StringBuilder lSBQuery = new StringBuilder();
        
        
        
        try
        {
            lCon = IWDMSDBConnection.getConnection();
            
            lSBQuery.append("SELECT A.PLNGCODE, A.BUDSUBHD_DESC_LONG, B.DEMAND_CODE, B.BUDMJRHD_CODE,");
            lSBQuery.append(" B.BUDSUBMJRHD_CODE, B.BUDMINHD_CODE, B.BUDSUBHD_CODE");
            lSBQuery.append(" FROM SGVA_BUDSUBHD_MST A, SGVA_GRANT_SCHEME_CO_DDO_MPG B");
            lSBQuery.append(" WHERE A.DEMAND_CODE = B.DEMAND_CODE AND A.BUDMJRHD_CODE = ");
            lSBQuery.append(" B.BUDMJRHD_CODE AND A.BUDSUBMJRHD_CODE = B.BUDSUBMJRHD_CODE ");
            lSBQuery.append(" AND A.BUDMINHD_CODE = B.BUDMINHD_CODE AND");
            lSBQuery.append(" A.BUDSUBHD_CODE = B.BUDSUBHD_CODE AND A.FIN_YR_ID = ? AND");
            lSBQuery.append(" A.LANG_ID = ? AND B.CO_DDO_TYPE = 'DDO' AND");
            lSBQuery.append(" B.CO_DDO_CODE = ? AND B.STATUS = ?");
            
            if(lStrPlNpl != null && lStrPlNpl.trim().length() > 0)
            {
                lSBQuery.append(" AND B.PLAN_NONPLAN = ?");
            }
            
            lPstmt = lCon.prepareStatement(lSBQuery.toString());
            
            lPstmt.setLong(1,lLngFinYrId);
            lPstmt.setString(2,OnlineBillUtil.getLangById(lLngLangId));
            lPstmt.setString(3,lStrDDOCode);
            lPstmt.setString(4,lStrDDOStatus);
            
            if(lStrPlNpl != null && lStrPlNpl.trim().length() > 0)
            {
                lPstmt.setString(5, lStrPlNpl);
            }
            
            lRs = lPstmt.executeQuery();
            
            while (lRs.next())
            {
                Object[] lObj = new Object[7]; 
                
                lObj [0] = lRs.getString(1);
                lObj [1] = lRs.getString(2);
                lObj [2] = lRs.getString(3);
                lObj [3] = lRs.getString(4);
                lObj [4] = lRs.getString(5);
                lObj [5] = lRs.getString(6);
                lObj [6] = lRs.getString(7);
                
                lListReturn.add(lObj);
            }
                
            glogger.info("Query From getGrantHeadsForDDO " + lSBQuery.toString());
            glogger.info("And Parameter is " + lStrDDOCode + "--" + lStrDDOStatus + "--" + lStrPlNpl 
            			+ "--" + lLngFinYrId + "--" + lLngLangId);

        }
        catch(SQLException se)
        {
            glogger.error("SQL Error in getGrantHeadsForDDO. Error is : " + se,se);
        }
        catch(Exception e)
        {
            glogger.error("Error in getGrantHeadsForDDO. Error is : " + e,e);
        }
        finally
        {
            try
            {
                lRs.close();
                lPstmt.close();
                lCon.close();
                
                lRs = null;
                lPstmt = null;
                lCon = null;
            }
            catch(SQLException se)
            {
                glogger.error("Error in closing DB Resources in getGrantHeadsForDDO. Error is : " + se,se);
            }
        }
        
        return lListReturn;
    }    
    
    
    public List getHeadsForDDOBySchemeNo(String lStrSchemeNo, String lStrDDOStatus, Long lLngFinYrId, Long lLngLangId)
    {
    	List lListReturn = null;
        StringBuilder lSBQuery = new StringBuilder();

        try
        {
            lSBQuery.append(" SELECT A.demandCode, A.budmjrhdCode, ");
            lSBQuery.append(" A.budsubmjrhdCode, A.budminhdCode, A.budsubhdCode ");
            lSBQuery.append(" FROM SgvaBudsubhdMst A ");
            lSBQuery.append(" WHERE A.plngcode = :plngcode AND A.finYrId = :finYrId AND A.langId= :langId ");
            lSBQuery.append(" AND A.status = :status ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("plngcode", lStrSchemeNo);
            lQuery.setParameter("status", lStrDDOStatus);
            lQuery.setParameter("finYrId", lLngFinYrId);
            lQuery.setParameter("langId", OnlineBillUtil.getLangById(lLngLangId));
            
            glogger.info("Query From getGrantHeadsForDDOBySchemeNo " + lQuery.toString());
            glogger.info("And Parameter is " + lStrDDOStatus + "--" + lLngFinYrId + "--" + lLngLangId);

            lListReturn = lQuery.list();            
        }
        catch (Exception e)
        {
        	glogger.error("Error in getGrantHeadsForDDOBySchemeNo and Error is : " + e, e);   
        }
        
        return lListReturn;
    }    


    public List getSchemeNoByDDOGrantHead(String lStrDemandCode, String lStrMajorHead, String lStrSubMajorHead, String lStrMinorHead, String lStrSubHead , String lStrDDOStatus, Long lLngFinYrId, Long lLngLangId)
    {
    	List lListReturn = null;
        StringBuilder lSBQuery = new StringBuilder();

        try
        {
        	lSBQuery.append(" SELECT A.plngcode ");
            lSBQuery.append(" FROM SgvaBudsubhdMst A ");
            lSBQuery.append(" WHERE A.demandCode = :demandCode AND A.budmjrhdCode = :budmjrhdCode ");
            lSBQuery.append(" AND A.budsubmjrhdCode = :budsubmjrhdCode AND A.budminhdCode = :budminhdCode ");
            lSBQuery.append(" AND A.budsubhdCode = :budsubhdCode ");
            lSBQuery.append(" AND A.finYrId = :finYrId AND A.langId= :langId ");
            lSBQuery.append(" AND A.status = :status ");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("demandCode", lStrDemandCode);
            lQuery.setParameter("budmjrhdCode", lStrMajorHead);
            lQuery.setParameter("budsubmjrhdCode", lStrSubMajorHead);
            lQuery.setParameter("budminhdCode", lStrMinorHead);
            lQuery.setParameter("budsubhdCode", lStrSubHead);
            lQuery.setParameter("status", lStrDDOStatus);
            lQuery.setParameter("finYrId", lLngFinYrId);
            lQuery.setParameter("langId", OnlineBillUtil.getLangById(lLngLangId));
            
            glogger.info("Query From getSchemeNoByDDOGrantHead " + lQuery.toString());
            glogger.info("And Parameter is " + lStrDemandCode + "--" + lStrMajorHead 
            		+ "--" + lStrSubMajorHead + "--" + lStrMinorHead + "--" 
            		+ lStrSubHead + "--" + lStrDDOStatus + "--" + lLngFinYrId + "--" 
            		+ lLngLangId);

            lListReturn = lQuery.list();            
        }
        catch (Exception e)
        {
        	glogger.error("Error in getSchemeNoByDDOGrantHead and Error is : " + e, e);   
        }
        
        return lListReturn;
    }    
    
    
    private Long getNextFinYrID(SessionFactory sessionFactory)
    {
    	if(ghibSession == null)
    		ghibSession = sessionFactory.getCurrentSession();
    	
        StringBuilder lsb = new StringBuilder();
        Long lLngFinYrId = 0L;

        int lintCurrYear = Calendar.YEAR;
        int lintCurrMonth = (Calendar.MONTH + 1);

        if (lintCurrMonth >= 4)
        {
            lintCurrYear = lintCurrYear + 1;
        }

        Query lQuery = null;
        Object[] lObj = null;

        try
        {
            lsb.append(" SELECT finYearId FROM SgvcFinYearMst WHERE finYearCode = :finyearCode");

            lQuery = ghibSession.createQuery(lsb.toString());
            lQuery.setParameter("finyearCode", String.valueOf(lintCurrYear));

            List qList = lQuery.list();

            if (qList != null && qList.size() > 0)
            {
                lObj = (Object[])qList.get(0);
                lLngFinYrId = (Long) lObj[0];
            }
        }
        catch (Exception e)
        {
            glogger.error("Error in getNextFinYrID() ::Exception " + e, e);
            return 0L;
        }

        return lLngFinYrId;
    }

    
    public SgvaBudbpnMapping getBPNInfoFrmDmd(String lStrDmdCode,Long lLngLangId)
    {
        SgvaBudbpnMapping lObjBPN = null;
        StringBuilder lSBQuery = new StringBuilder();
        
        Object[] lObj = null;
        
        try
        {
            lSBQuery.append(" select B.deptId, B.bpncode, B.bpnDesc from SgvaBuddemandMst D, SgvaBudbpnMapping B");
            lSBQuery.append(" where B.status = 'Active' AND D.bpncode = B.bpncode");
            lSBQuery.append(" AND D.demandCode = :demandCode AND D.langId = :langId And D.locId = 'LC1' AND");
            lSBQuery.append(" B.locId = 'LC1' AND B.finYrId = :finYearId And D.finYrId = :finYearId");
            lSBQuery.append(" AND B.langId = :langId");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("demandCode", lStrDmdCode);
            lQuery.setParameter("langId", OnlineBillUtil.getLangById(lLngLangId));
            lQuery.setParameter("finYearId", gLngFinYrId);
            
            List qList = lQuery.list();
            
            if(qList != null && qList.size() > 0)
            {
                lObj = (Object[]) qList.get(0);
                
                lObjBPN = new SgvaBudbpnMapping();
                
                lObjBPN.setDeptId((lObj[0] != null) ? lObj[0].toString() : "");
                lObjBPN.setBpncode(lObj[1].toString());
                lObjBPN.setBpnDesc(lObj[2].toString());
            }
        }
        catch(Exception e)
        {
            glogger.error("Error in getBPNInfoFrmDmd. Error is : " + e,e);
        }
        
        return lObjBPN;
    }
    
}
