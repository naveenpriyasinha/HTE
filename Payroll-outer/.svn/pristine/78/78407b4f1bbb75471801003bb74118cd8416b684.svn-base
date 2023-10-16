package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.service.PensionBillServiceImpl;
import com.tcs.sgv.pension.valueobject.AuditorVO;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.SavedCasesVO;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

public class PensionCaseDAOImpl extends GenericDaoHibernateImpl<MstPensionerHdr, Long> implements PensionCaseDAO
{

    /* Global Variable for Logger Class */
    private Log logger = LogFactory.getLog(getClass());


    public PensionCaseDAOImpl(Class<MstPensionerHdr> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }

    public List getSavedCaseDtls(Map inputMap) throws Exception
    {
        String savedCases = inputMap.get("SavedCases").toString();
        ArrayList arrPnsnCaseDtls = new ArrayList();
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        String lStrSearchValue = "";
        String lstrSearchStr = "";
        try
        {
        	String lStrFrmLevel = (String) inputMap.get("caseFromlevel");
        	PensionBillServiceImpl lobjPensionBillservice = new PensionBillServiceImpl();
            Short lShrtCurrCaseStatus = 0;
        	if(savedCases.equals("()"))
            {
            	savedCases = "(-1)";
            }
        	if(StringUtility.getParameter("txtSearch",request) != null)
        	{
        		lStrSearchValue = StringUtility.getParameter("txtSearch",request); 
        	}
        	if(StringUtility.getParameter("cmbSearch",request) != null)
        	{
        		lstrSearchStr = StringUtility.getParameter("cmbSearch",request);
        	}
        	String status = (String) inputMap.get("status");
            String[] columnValues = new String[]{"", "r.ppoNo", "p.firstName || p.middleName || p.lastName","r.typeFlag","r.ppoInwardDate", "r.commensionDate"};
            Session hiSession = getSession();
            String query = "";
            if (status.equals("0"))
	        {
            	query = "select distinct r.ppoNo, p.firstName,  p.middleName, p.lastName, r.ppoInwardDate, r.commensionDate, r.pensionRequestId,p.pensionerId,r.typeFlag,r.caseStatus,p.pnsnrPrefix,r.authority from TrnPensionRqstHdr r, MstPensionerHdr p ";
	        }
            else
            {
            	query = "select r.ppoNo, p.firstName,  p.middleName, p.lastName, r.ppoInwardDate, r.commensionDate, r.pensionRequestId,p.pensionerId,r.typeFlag,r.caseStatus,p.pnsnrPrefix,r.authority from TrnPensionRqstHdr r, MstPensionerHdr p ";
            }
            
            if(lStrSearchValue.trim().length() > 0 && lstrSearchStr.trim().length() > 0 && ! lstrSearchStr.equals("-1") && lstrSearchStr.equalsIgnoreCase("mpd.branCode"))
            {
            	  query = query+ " ,MstPensionerDtls mpd ";
            }
            
             if (status.equals("0"))
	        {
	    		query = query+ " ,RltPensioncaseBill b ";
	        }
            query = query + " where p.pensionerCode = r.pensionerCode ";
           
            if(savedCases != null && savedCases.length() > 0 && !"()".equals(savedCases))
	        {
	        	 //lSBQuery.append(" AND T.pensionRequestId in "+lStrMyCases);
	        	int i  = savedCases.split("~").length;
	        	if(i > 0)
	        	{
	        		query  += "AND ( r.pensionRequestId in ";
	        	}
	        	for(int k = 0;k <i;k++)
	        	{
	        		query  +=  savedCases.split("~")[k];
	        		if(i > 1 && k<i-1)
	        		{
	        			query  += " OR r.pensionRequestId in";
	        		}
	        	}
	        	query  += ")";
	        }
	        else
	        {
	        	query  += " AND (1 = 2 )";
	        }
            if (status.equals("0"))
            {
            	if(lStrFrmLevel.equals("5"))
            	{
            		lShrtCurrCaseStatus = 1;
            	}
            	else if(lStrFrmLevel.equals("10"))
            	{
            		lShrtCurrCaseStatus = 3;
            	}
            	else if(lStrFrmLevel.equals("20"))
            	{
            		lShrtCurrCaseStatus = 5;
            	}
            	query += " and ((r.approveStatus = 'CASEINWARD') or ((b.billNo is null) and (r.ppoNo = b.ppoNo))) and (r.currCaseStatus = "+ lShrtCurrCaseStatus +" )";
            }
            else
            {
                query += " AND (r.currCaseStatus = 75 OR r.currCaseStatus = " + lobjPensionBillservice.getStatusByLevel(Short.valueOf(lStrFrmLevel)) + ") AND r.caseStatus = p.caseStatus ";
            }
          
            if(lstrSearchStr.length() >0 && lStrSearchValue.length() > 0 && ! lstrSearchStr.equals("-1"))
            {
            	 if (lstrSearchStr.equalsIgnoreCase("name"))
                 {
                   if(request.getParameter("txtSearch") != null)
                   {
                       query += " AND " + columnValues[2] + " like '%"+lStrSearchValue+"%'";
                   }
                 } 
            	 else if (lstrSearchStr.equalsIgnoreCase("r.typeFlag"))
                 {
                     if (lStrSearchValue.equalsIgnoreCase("P"))
                     {
                         query += " AND r.typeFlag = '" + lStrSearchValue + "'";
                     }
                     else
                     {
                         query += " AND( r.typeFlag = 'C' OR r.typeFlag = 'R')";
                     }
                 }
                 else   if (lstrSearchStr.equalsIgnoreCase("r.ppoNo"))
                 {
                     query += " AND r.ppoNo LIKE '%" + lStrSearchValue + "%'";
                 }
                 else if (lstrSearchStr.equalsIgnoreCase("mpd.branCode"))
                 {
                 	query += " AND mpd.branchCode = '"+ lStrSearchValue +"'";
                 	query += " AND r.pensionerCode = mpd.pensionerCode AND r.caseStatus = mpd.caseStatus ";
                 }
                 else if (lstrSearchStr.equalsIgnoreCase("r.headCode"))
                 {
                 	query += " AND r.headCode = "+lStrSearchValue;
                 }
                 else
                 {
                     if(lStrSearchValue != null && lStrSearchValue.trim().length()>0)
                     {
                         String fromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy")
                             .parse(lStrSearchValue));
                         query += " AND " + lstrSearchStr + " = '" + fromDate + "'";
                     }
                 }
            }
            /* FOR EXTERNAL PAGING - START */
            Integer pageNumber = IFMSCommonServiceImpl.getPageNumber(request, "vo");
            Integer orderType = IFMSCommonServiceImpl.getOrderType(request, "vo");
            Integer sortColumn = IFMSCommonServiceImpl.getSortIndex(request, "vo");
           
            /* FOR EXTERNAL PAGING - END */
            String lStrSort = "ASC";
            if (orderType == 2)
                lStrSort = "DESC";
           
          
            	if(sortColumn != null && sortColumn != -1)
                {
                    query += " order by "+columnValues[sortColumn]+" "+lStrSort;
                }
                else
                {
                    query += " order by r.ppoInwardDate ASC, r.ppoNo ASC, r.caseStatus desc";
                }
           
            Query hbQuery = hiSession.createQuery(query);
            
            // hbQuery.setFirstResult((pageNumber - 1) * Constants.PAGE_SIZE);
            // hbQuery.setMaxResults(Constants.PAGE_SIZE);
            List listPnsnCaseDtls = hbQuery.list();
            
            if (listPnsnCaseDtls != null && listPnsnCaseDtls.size() > 0)
            {
                Iterator it = listPnsnCaseDtls.iterator();
                while (it.hasNext())
                {
                    Object tuple[] = (Object[]) it.next();
                        SavedCasesVO savedCasesVo = new SavedCasesVO();
    
                        String Name = "";
                        if(tuple[10] != null)
                            Name =  tuple[10].toString();
                        if (tuple[1] != null)
                            Name = Name + " " +tuple[1].toString();
                        if (tuple[2] != null)
                            Name = Name + " " + tuple[2].toString();
                        if (tuple[3] != null)
                            Name = Name + " " + tuple[3].toString();
                        
                        savedCasesVo.setPpoNo(tuple[0].toString());
                        savedCasesVo.setName(Name);
                        if (tuple[4] != null)
                            savedCasesVo.setCaseInwdDate((Date) tuple[4]);
                        if (tuple[5] != null)
                            savedCasesVo.setCommensionDate((Date) (tuple[5]));
                        if (tuple[6] != null)
                            savedCasesVo.setTrnPensionRqstHdrId((Long) tuple[6]);
                        if (tuple[7] != null)
                            savedCasesVo.setMstPensionerHdrId((Long) tuple[7]);
                        if (tuple[8] != null)
                            savedCasesVo.setTypeFlag(tuple[8].toString());
                        if (tuple[11] != null)
                        	savedCasesVo.setSanctAuthority(tuple[11].toString());
                        arrPnsnCaseDtls.add(savedCasesVo);
                    }
                }
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        return arrPnsnCaseDtls;
    }
    public List getCaseUsersLsitgetUserFromPost(String[] postId,String[] levelId,Long lLngLangId,Map orgsMap,String MRFlag)
    {
    	List userList = new ArrayList();
    	try
    	{
    		Session hibSession = getSession();
    		int lIntLoopI = 0;
    		String str = "SELECT EMP_FNAME, EMP_LNAME, R.POST_ID,EMP_MNAME,MM.DSGN_NAME, L.LEVEL_DESC FROM ORG_EMP_MST O, " +
			"ORG_USERPOST_RLT R ,ORG_DESIGNATION_MST  MM,ORG_POST_MST P, RLT_LEVEL_STATUS L  " +
			"WHERE  R.POST_ID = P.POST_ID AND P.DSGN_CODE = MM.DSGN_CODE and O.LANG_ID = " + lLngLangId + " AND R.POST_ID IN(";
			for (lIntLoopI = 0; lIntLoopI < postId.length - 1; lIntLoopI++) {
				str = str + postId[lIntLoopI] + ", ";
			}
			str = str+postId[postId.length-1]+") AND R.USER_ID=O.USER_ID AND L.LEVEL_CODE = " + levelId[0];
			/*if(MRFlag.equals("Y"))
			  str = str + " AND L.CATEGORY = '" + DBConstants.CATEGORY_MRCASE + "'";
			else
			 str = str + " AND L.CATEGORY = '" + DBConstants.CATEGORY_PPO + "'";*/
    		if(orgsMap.get("svdBillCurLevel") != null && Integer.parseInt(orgsMap.get("svdBillCurLevel").toString()) == 10)
    		{
    			str = str + " AND R.POST_ID IN (SELECT APR.POST_ID FROM ACL_POSTROLE_RLT APR WHERE APR.ROLE_ID = 210004)";
    		}
    		Query query = hibSession.createSQLQuery(str);
    		List lLstQList = query.list();
    		Iterator it = lLstQList.iterator();
    		int lIntLoopJ = 0;
    		while (it.hasNext()) {
    			Object[] tuple = (Object[]) it.next();
    			String[] result = new String[4];
    			String middleName = tuple[3] != null ? tuple[3].toString() : "";
    			String name = " " + tuple[0] + " " + middleName + " " + tuple[1] + " [" + tuple[4] + "]";
    			result[0] = name;
    			result[1] = tuple[2].toString();
    			result[2] = levelId[lIntLoopJ];
    			result[3] = tuple[5].toString();
    			userList.add(result);
    			lIntLoopJ++;
    		}
    	}
    	catch(Exception e)
    	{
    		logger.error("Error is"+e,e);
    	}
    	return userList;
    }
    public Long getSavedCaseDtlsCount(Map inputMap)
    {
        String savedCases = inputMap.get("SavedCases").toString();
        Long lObjcount = 0L;
        Long lLngCount = 0L;
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        try
        {
        	String lStrFrmLevel = (String) inputMap.get("caseFromlevel");
        	PensionBillServiceImpl lobjPensionBillservice = new PensionBillServiceImpl();
            Short lShrtCurrCaseStatus = 0;
        	if(savedCases.equals("()"))
            {
            	savedCases = "(-1)";
            }
        	String status = (String) inputMap.get("status");
            String[] columnValues = new String[]{"", "r.ppoNo", "p.firstName || p.middleName || p.lastName","r.typeFlag","r.ppoInwardDate", "r.commensionDate"};
            Session hiSession = getSession();
            String query = "select Count( distinct r.ppoNo) from TrnPensionRqstHdr r, MstPensionerHdr p , RltPensioncaseBill b"
                    + " where p.pensionerCode = r.pensionerCode ";
            if(savedCases != null && savedCases.length() > 0 && !"()".equals(savedCases))
	        {
	        	 //lSBQuery.append(" AND T.pensionRequestId in "+lStrMyCases);
	        	int i  = savedCases.split("~").length;
	        	if(i > 0)
	        	{
	        		query  += "AND ( r.pensionRequestId in ";
	        	}
	        	for(int k = 0;k <i;k++)
	        	{
	        		query  +=  savedCases.split("~")[k];
	        		if(i > 1 && k<i-1)
	        		{
	        			query  += " OR r.pensionRequestId in";
	        		}
	        	}
	        	query  += ")";
	        }
	        else
	        {
	        	query  += " AND (1 = 2 )";
	        }
            if (status.equals("0"))
            {
            	if(lStrFrmLevel.equals("5"))
            	{
            		lShrtCurrCaseStatus = 1;
            	}
            	else if(lStrFrmLevel.equals("10"))
            	{
            		lShrtCurrCaseStatus = 3;
            	}
            	else if(lStrFrmLevel.equals("20"))
            	{
            		lShrtCurrCaseStatus = 5;
            	}
            	query += " and ((r.approveStatus = 'CASEINWARD') or ((b.billNo is null) and (r.ppoNo = b.ppoNo))) and (r.currCaseStatus = "+ lShrtCurrCaseStatus +" )";
            }
            else
            {
                query += " AND (r.currCaseStatus = 75 OR r.currCaseStatus = " + lobjPensionBillservice.getStatusByLevel(Short.valueOf(lStrFrmLevel)) + ") AND r.caseStatus = p.caseStatus ";
            }
            String lstrSearchStr = null;
            String lStrSearchValue = null;
            if (request.getParameter("cmbSearch") != null && ! request.getParameter("cmbSearch").toString().equalsIgnoreCase("-1"))
            {
                lstrSearchStr = request.getParameter("cmbSearch").toString();
                if (request.getParameter("txtSearch") != null)
                {
                    lStrSearchValue = request.getParameter("txtSearch").toString();
                    if (lstrSearchStr.equalsIgnoreCase("name"))
                    {
                      if(request.getParameter("txtSearch") != null)
                      {
                          query += " AND " + columnValues[2] + " like '%"+lStrSearchValue+"%'";
                      }
                    }
                    else if (lstrSearchStr.equalsIgnoreCase("r.typeFlag"))
                    {
                        if (lStrSearchValue.equalsIgnoreCase("P"))
                        {
                            query += " AND r.typeFlag = '" + lStrSearchValue + "'";
                        }
                        else
                        {
                            query += " AND( r.typeFlag = 'C' OR r.typeFlag = 'R')";
                        }
                    }
                    else   if (lstrSearchStr.equalsIgnoreCase("r.ppoNo"))
                    {
                        query += " AND r.ppoNo LIKE '%" + lStrSearchValue + "%'";
                    }
                    else
                    {
                        if(lStrSearchValue != null && lStrSearchValue.trim().length()>0)
                        {
                            String fromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy")
                                .parse(lStrSearchValue));
                            query += " AND " + lstrSearchStr + " = '" + fromDate + "'";
                        }
                    }
                }
            }
            Query hbQuery = hiSession.createQuery(query);
            List listPnsnCaseDtls = hbQuery.list();
            lObjcount  = (Long) listPnsnCaseDtls.get(0);
            
            lLngCount = Long.valueOf(lObjcount.toString());
        }
        catch(Exception e)
        {
           logger.error("Error is :"+e,e);
        }
        return lLngCount;
    }

    // for pension case tracking
    // Rupsa
    public int getSentCaseDtlsCount(Map inputMap,Long gLngLangId,Long gLngUserId)
    {
    	int count = 0;
        StringBuffer lSBQuery = new StringBuffer();
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        try
        {
            lSBQuery.append(" select Count(a.ppoNo) ");
            lSBQuery.append(" 	FROM TrnPensionCaseMvmnt a,");
            lSBQuery.append("   TrnPensionRqstHdr r,");
            lSBQuery.append("   MstPensionerHdr p,");
            lSBQuery.append("   OrgEmpMst e ");
            lSBQuery.append(" 	WHERE (a.ppoNo,a.movementId) IN (SELECT ppoNo, MAX(movementId)");
            lSBQuery.append(" 									 FROM TrnPensionCaseMvmnt WHERE ppoNo IN ");
            lSBQuery.append(" 										  (SELECT t.ppoNo FROM TrnPensionCaseMvmnt t WHERE "
                    + " t.movementStatus = 'FORWARD' AND t.status = 'CONTINUE' AND t.createdUserId = "+gLngUserId+" ) AND p.caseStatus = 'NEW' AND r.caseStatus = 'NEW'  ");
            lSBQuery.append("  								     GROUP BY ppoNo) ");
            lSBQuery
                    .append(" AND a.ppoNo = r.ppoNo AND r.pensionerCode = p.pensionerCode AND e.orgUserMst.userId = a.currentUserId AND e.cmnLanguageMst.langId ="
                            + gLngLangId);
            if(request.getParameter("cmbSearch")!= null)
            {
                String lStrSearchValue = null;
                
                String lStrSrchStr = request.getParameter("cmbSearch").toString().trim();
                lStrSearchValue = request.getParameter("txtSearch").toString();
                if(lStrSrchStr.equals("name"))
                {
                    String lStrFname = null;
                    String lStrMname = null;
                    String lStrLname = null;
                    
                    if (request.getParameter("txtSearchName1") != null && request.getParameter("txtSearchName1").length() > 0)
                    {
                        lStrFname = request.getParameter("txtSearchName1").toString();
                    }
                    if (request.getParameter("txtSearchName2") != null && request.getParameter("txtSearchName2").length() > 0)
                    {
                        lStrMname = request.getParameter("txtSearchName2").toString();
                    }
                    if (request.getParameter("txtSearchName3") != null && request.getParameter("txtSearchName3").length() > 0)
                    {
                        lStrLname = request.getParameter("txtSearchName3").toString();
                    }
                    lSBQuery.append(" AND (");
                    if (lStrFname != null)
                    {
                        lSBQuery.append(" p.firstName LIKE '%"+lStrFname+"%'");
                    }
                    if (lStrMname != null)
                    {
                        if(lStrFname != null)
                        {
                            lSBQuery.append(" OR ");
                        }
                        lSBQuery.append(" p.middleName LIKE '%"+lStrMname+"%'");
                    }
                    if (lStrLname != null)
                    {
                        if(lStrMname != null || lStrFname!= null )
                        {
                            lSBQuery.append(" OR ");
                        }
                        lSBQuery .append(" p.lastName LIKE '%"+lStrLname+"%'");
                    }
                    lSBQuery.append( " )");
                }
                else if(lStrSrchStr.equalsIgnoreCase("r.ppoNo"))
                {
                    lSBQuery.append(" AND r.ppoNo LIKE '%" + lStrSearchValue + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("lying"))
                {
                    lSBQuery.append(" AND e.empFname||''||e.empMname||''||e.empLname like '%" + lStrSearchValue.trim() + "%'");
                }
                else 
                {

                    String fromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy")
                            .parse(lStrSearchValue));
                    lSBQuery.append(" AND " + lStrSrchStr + " = '" + fromDate + "'");
                }
            }
            
            Session hiSession = getSession();
            Query hqlQuery = hiSession.createQuery(lSBQuery.toString());
            List resList = hqlQuery.list();
            count = ((Long) resList.get(0)).intValue();
        }
        catch(Exception e)
        {
        	logger.error("Error is :"+e,e);
        }

    	return count; 
    }
    public List getSentCaseDtls(Map inputMap,Long gLngUserId, Long gLngLangId) throws Exception
    {
        ArrayList arrPnsnCaseDtls = new ArrayList();
        Iterator it;
        StringBuffer lSBQuery = new StringBuffer();
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        try
        {
            String[] columnValues = new String[]{"","a.ppoNo","p.firstName||''||p.middleName||''||p.lastName","r.commensionDate","r.ppoInwardDate", "a.createdDate","e.empFname||''||e.empMname||''||e.empLname"};
        	
            lSBQuery.append(" 	SELECT DISTINCT a.ppoNo, ");
            lSBQuery.append("   a.createdDate, ");
            lSBQuery.append("   p.firstName||''||p.middleName||''||p.lastName,");
            lSBQuery.append("   r.ppoInwardDate,");
            lSBQuery.append("   r.commensionDate,");
            lSBQuery.append("   r.pensionerCode, ");
            lSBQuery.append("   r.typeFlag, ");
            lSBQuery.append("   e.empFname||''||e.empMname||''||e.empLname, ");
            lSBQuery.append("   a.currentPostId ");
            lSBQuery.append(" 	FROM TrnPensionCaseMvmnt a,");
            lSBQuery.append("   TrnPensionRqstHdr r,");
            lSBQuery.append("   MstPensionerHdr p,");
            lSBQuery.append("   OrgEmpMst e ");
            lSBQuery.append(" 	WHERE (a.ppoNo,a.movementId) IN (SELECT ppoNo, MAX(movementId)");
            lSBQuery.append(" 									 FROM TrnPensionCaseMvmnt WHERE ppoNo IN ");
            lSBQuery.append(" 										  (SELECT t.ppoNo FROM TrnPensionCaseMvmnt t WHERE "
                    + " t.movementStatus = 'FORWARD' AND t.status = 'CONTINUE' AND t.createdUserId = "+gLngUserId+" ) AND p.caseStatus = 'NEW' AND r.caseStatus = 'NEW'  ");
            lSBQuery.append("  								     GROUP BY ppoNo) ");
            lSBQuery
                    .append(" AND a.ppoNo = r.ppoNo AND r.pensionerCode = p.pensionerCode AND e.orgUserMst.userId = a.currentUserId AND e.cmnLanguageMst.langId ="
                            + gLngLangId);
            if(request.getParameter("cmbSearch")!= null)
            {
                String lStrSearchValue = null;
                
                String lStrSrchStr = request.getParameter("cmbSearch").toString().trim();
                lStrSearchValue = request.getParameter("txtSearch").toString();
                if(lStrSrchStr.equals("name"))
                {
                    String lStrFname = null;
                    String lStrMname = null;
                    String lStrLname = null;
                    
                    if (request.getParameter("txtSearchName1") != null && request.getParameter("txtSearchName1").length() > 0)
                    {
                        lStrFname = request.getParameter("txtSearchName1").toString();
                    }
                    if (request.getParameter("txtSearchName2") != null && request.getParameter("txtSearchName2").length() > 0)
                    {
                        lStrMname = request.getParameter("txtSearchName2").toString();
                    }
                    if (request.getParameter("txtSearchName3") != null && request.getParameter("txtSearchName3").length() > 0)
                    {
                        lStrLname = request.getParameter("txtSearchName3").toString();
                    }
                    lSBQuery.append(" AND (");
                    if (lStrFname != null)
                    {
                        lSBQuery.append(" p.firstName LIKE '%"+lStrFname+"%'");
                    }
                    if (lStrMname != null)
                    {
                        if(lStrFname != null)
                        {
                            lSBQuery.append(" OR ");
                        }
                        lSBQuery.append(" p.middleName LIKE '%"+lStrMname+"%'");
                    }
                    if (lStrLname != null)
                    {
                        if(lStrMname != null || lStrFname!= null )
                        {
                            lSBQuery.append(" OR ");
                        }
                        lSBQuery .append(" p.lastName LIKE '%"+lStrLname+"%'");
                    }
                    lSBQuery.append( " )");
                }
                else if(lStrSrchStr.equalsIgnoreCase("r.ppoNo"))
                {
                    lSBQuery.append(" AND r.ppoNo LIKE '%" + lStrSearchValue + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("lying"))
                {
                    lSBQuery.append(" AND e.empFname||''||e.empMname||''||e.empLname like '%" + lStrSearchValue.trim() + "%'");
                }
                else 
                {

                    String fromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy")
                            .parse(lStrSearchValue));
                    lSBQuery.append(" AND " + lStrSrchStr + " = '" + fromDate + "'");
                }
            }
            
            /* FOR EXTERNAL PAGING - START */
            Integer pageNumber = IFMSCommonServiceImpl.getPageNumber(request, "vo");
            Integer orderType = IFMSCommonServiceImpl.getOrderType(request, "vo");
            Integer sortColumn = 1;
            if(IFMSCommonServiceImpl.getSortIndex(request, "vo") > 0)
            {
                sortColumn =  IFMSCommonServiceImpl.getSortIndex(request, "vo");
            }
            /* FOR EXTERNAL PAGING - END */
            String lStrSort = "ASC";
            if (orderType == 2)
                lStrSort = "DESC";
            
            lSBQuery.append(" ORDER BY "+columnValues[sortColumn]+" "+lStrSort);
            
            Session hiSession = getSession();
            Query hqlQuery = hiSession.createQuery(lSBQuery.toString());
            
            hqlQuery.setFirstResult((pageNumber - 1) * Constants.PAGE_SIZE);
            hqlQuery.setMaxResults(Constants.PAGE_SIZE);
            
            List resultList = hqlQuery.list();

            if (resultList != null && resultList.size() > 0)
            {
                it = resultList.iterator();
                while (it.hasNext())
                {
                    Object tuple[] = (Object[]) it.next();
                    SavedCasesVO savedCasesVo = new SavedCasesVO();

                    savedCasesVo.setPpoNo(tuple[0].toString());
                    savedCasesVo.setCaseSentDate((Date) (tuple[1]));
                    savedCasesVo.setName(tuple[2].toString());
                    savedCasesVo.setCaseInwdDate((Date) tuple[3]);
                    savedCasesVo.setCommensionDate((Date) (tuple[4]));
                    savedCasesVo.setTypeFlag(tuple[6].toString());
                    savedCasesVo.setLyingName(tuple[7].toString());
                    // savedCasesVo.setCurrentPostId(new BigDecimal());
                    savedCasesVo.setCurrentPostId((BigDecimal) tuple[8]);
                    arrPnsnCaseDtls.add(savedCasesVo);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        return arrPnsnCaseDtls;
    }


    // get designation list using hierarchy_id
    // Rupsa
    public List getDesignationList(String lStrLocId, Long lLngDocumentId) throws Exception
    {
        StringBuffer lSBQuery = new StringBuffer();
        List lLstDesignation = null;

        try
        {
        	
            Session hiSession = getSession();

            lSBQuery.append(" SELECT d.dsgn_name,p.post_id,mp.level_id,oe.emp_Fname||''||oe.emp_Mname||''||oe.emp_Lname  ");
            lSBQuery.append(" FROM org_designation_mst d,org_post_details_rlt p,wf_hierachy_post_mpg mp,wf_hierarchy_reference_mst hr,org_emp_mst oe, org_userpost_rlt ou ");
            lSBQuery.append(" WHERE  d.dsgn_id = p.dsgn_id ");
            lSBQuery.append(" AND  mp.post_id = p.post_id ");
            lSBQuery.append(" AND ou.user_id = oe.user_id ");
            lSBQuery.append(" AND ou.post_id = p.post_id ");
            lSBQuery.append(" AND mp.hierachy_ref_id = hr.hierachy_ref_id ");
            lSBQuery.append(" AND mp.loc_id = '" + lStrLocId + "' AND hr.loc_id = '" + lStrLocId
                    + "' AND hr.doc_id = " + lLngDocumentId + " ");
            lSBQuery.append("ORDER BY mp.level_id ");
            
            Query hqlQuery = hiSession.createSQLQuery(lSBQuery.toString());

            lLstDesignation = hqlQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error is " + e, e);
            throw (e);
        }
        return lLstDesignation;
    }
    
    public BigDecimal getPostIdOfPresentAuditor(String lStrLocId,Long lLngDocumentId,String lStrPpoNo) throws Exception
    {
        StringBuffer lSBQuery = new StringBuffer();
        List lLstAuditor = null;
        BigDecimal lBDAuditorId = null;

        try
        {
            Session hiSession = getSession();
            
            lSBQuery.append(" SELECT tt.created_post_id FROM trn_pension_case_mvmnt tt ");
            lSBQuery.append(" WHERE tt.ppo_no='"+lStrPpoNo+"' AND tt.current_post_id = ");
            lSBQuery.append(" (SELECT p.post_id FROM org_designation_mst d, org_post_details_rlt p, wf_hierachy_post_mpg mp,wf_hierarchy_reference_mst hr,org_emp_mst oe, org_userpost_rlt ou ");
            lSBQuery.append(" WHERE d.dsgn_id = p.dsgn_id AND mp.post_id = p.post_id AND ou.user_id = oe.user_id AND ou.post_id = p.post_id ");
            lSBQuery.append(" AND mp.hierachy_ref_id = hr.hierachy_ref_id ");
            lSBQuery.append(" AND mp.loc_id = '" + lStrLocId + "' AND hr.loc_id = '" + lStrLocId
                    + "' AND hr.doc_id = " + lLngDocumentId + " AND d.dsgn_name='Head Accountant') AND tt.status = 'CONTINUE' ");
            
            Query hqlQuery = hiSession.createSQLQuery(lSBQuery.toString());

            lLstAuditor = hqlQuery.list();
            if (lLstAuditor != null && lLstAuditor.size() > 0)
            {
            	lBDAuditorId = (BigDecimal) lLstAuditor.get(0);
            }
           
        }
       
        catch (Exception e)
        {
            logger.error("Error is " + e, e);
            throw (e);
        }

        return lBDAuditorId;
    }
    
    
    public List getCasefromPpoCode(Map inputMap) throws Exception // hv to
    // addd
    // readflag
    {
        Session hiSesion = getSession();
        try
        {
            String caseNoList = (String) inputMap.get("ppoCodeList");
            StringBuilder query = new StringBuilder();
            query.append(" select r.pensionRequestId from TrnPensionRqstHdr r ");
            query.append("r.ppoNo in (" + caseNoList + ")");
            Query sqlQuery = hiSesion.createQuery(query.toString());
            List listHeadCode = sqlQuery.list();
            ArrayList arrCaseList = new ArrayList();
            if (listHeadCode != null && listHeadCode.size() > 0)
            {
                Iterator it = listHeadCode.iterator();
                while (it.hasNext())
                {
                    Object tuple = (Object) it.next();
                    arrCaseList.add(tuple.toString());
                }
            }
            return arrCaseList;
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
    }


    public Map getBillCaseDtls(Map inputMap) throws Exception
    {
        Session hiSession = getSession();
        StringBuilder query = new StringBuilder();
        try
        {
            String ppoNo = (String) inputMap.get("ppoNo");
            query.append(" from RltPensioncaseBill r ");
            query.append(" where r.ppoNo = :ppoNo and r.status = 'Y'");
            query.append(" order by r.billType ");

            Query sqlQuery = hiSession.createQuery(query.toString());
            sqlQuery.setParameter("ppoNo", ppoNo);
            List listBillCaseDtls = sqlQuery.list();
            ArrayList arrBillCaseDtls = new ArrayList();
            if (listBillCaseDtls != null && listBillCaseDtls.size() > 0)
            {
                inputMap.put("BillGenerated", "1");
                Iterator itr = listBillCaseDtls.iterator();
                while (itr.hasNext())
                {
                	RltPensioncaseBill tuple = (RltPensioncaseBill) itr.next();
                    RltPensioncaseBill rltPensioncaseBillVo = new RltPensioncaseBill();
                    rltPensioncaseBillVo = tuple;
                    arrBillCaseDtls.add(rltPensioncaseBillVo);
                }
                inputMap.put("BillCaseDtlsList", arrBillCaseDtls);
            }
            return inputMap;
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
    }


    public Long getCaseIdfromPpoNo(String ppoNo) throws Exception // check
    {
        Session hiSession = getSession();
        Long lbgdcCaseId = null;
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        try
        {
            strQuery.append(" SELECT r.pensionRequestId ");
            strQuery.append(" FROM TrnPensionRqstHdr r ");
            strQuery.append(" WHERE r.ppoNo =:ppoNo");

            Query hqlQuery = hiSession.createQuery(strQuery.toString());
            hqlQuery.setString("ppoNo", ppoNo.trim());
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)

                lbgdcCaseId = (Long) resultList.get(0);
            return lbgdcCaseId;
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
    }


    public Long getCaseIdfromPpoNo(String ppoNo, String Status) throws Exception // check
    {
        Session hiSession = getSession();
        Long lbgdcCaseId = 0L;
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        try
        {
            strQuery.append(" SELECT r.pensionRequestId ");
            strQuery.append(" FROM TrnPensionRqstHdr r ");
            strQuery.append(" WHERE r.ppoNo =:ppoNo and r.caseStatus =:status");

            Query hqlQuery = hiSession.createQuery(strQuery.toString());
            hqlQuery.setString("ppoNo", ppoNo.trim());
            hqlQuery.setString("status", Status.trim());
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
                lbgdcCaseId = (Long) resultList.get(0);
            return lbgdcCaseId;
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
    }


    public String getPensionerCodefromPpoNo(String ppoNo, String Status) throws Exception // check
    {
        Session hiSession = getSession();
        String lstrPnsnrCode = "";
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        try
        {
            strQuery.append(" SELECT r.pensionerCode ");
            strQuery.append(" FROM TrnPensionRqstHdr r ");
            strQuery.append(" WHERE r.ppoNo =:ppoNo and r.caseStatus =:status");
            Query hqlQuery = hiSession.createQuery(strQuery.toString());
            hqlQuery.setString("ppoNo", ppoNo.trim());
            hqlQuery.setString("status", Status.trim());
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
                lstrPnsnrCode = (String) resultList.get(0);
            return lstrPnsnrCode;
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
    }


    public int getNextMovementId(Map inputMap) throws Exception
    {
        Session session = getSession();
        StringBuilder strQuery = new StringBuilder();
        String ppoNo = (String) inputMap.get("ppoNo");
        List resultList;
        Integer movementId = null;
        try
        {
            strQuery.append(" SELECT max(m.movementId) ");
            strQuery.append(" FROM TrnPensionCaseMvmnt m");
            strQuery.append(" WHERE m.ppoNo =:ppoNo ");
           // strQuery.append(" and m.status =:status");
            Query hqlQuery = session.createQuery(strQuery.toString());
           // hqlQuery.setString("status", "CONTINUE");
            hqlQuery.setString("ppoNo", ppoNo);
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
                movementId = Integer.parseInt(resultList.get(0).toString());
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        return movementId;
    }


    /**
     * Method to check valid PPO No.
     * 
     * @param String :
     *            lStrPPONo
     * @return boolean
     */
    public boolean isValidPPONo(String lStrPPONo) throws Exception
    {
        StringBuffer lSBQuery = new StringBuffer();

        Session session = getSession();
        try
        {
            lSBQuery.append(" FROM TrnPensionRqstHdr tpr WHERE");
            lSBQuery.append(" tpr.ppoNo = :PPONo ");

            Query lQuery = session.createQuery(lSBQuery.toString());

            lQuery.setParameter("PPONo", lStrPPONo);

            List lList = lQuery.list();
            if (lList != null && lList.size() > 0)
            {
                return true;
            }
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        // logger.info("isValidateToken ended");
        return false;
    }


    public boolean isValidateCaseBill(String strPPONo) throws Exception
    {
        Session session = getSession();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        Object[] tuple;
        Iterator itr;
        try
        {
            strQuery.append(" SELECT count(billNo), count(billType)");
            strQuery.append(" FROM RltPensioncaseBill ");
            strQuery.append(" WHERE ppoNo =:ppoNo");

            Query hqlQuery = session.createQuery(strQuery.toString());
            hqlQuery.setString("ppoNo", strPPONo);
            resultList = hqlQuery.list();
            if ((resultList != null) && resultList.size() > 0)
            {
                itr = resultList.iterator();
                tuple = (Object[]) itr.next();
                long billCount = (Long) tuple[0];
                long totalBillCount = (Long) tuple[1];
                if (billCount == totalBillCount)
                {
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        return false;
    }
    public List getCaseAuditorDtls(Map inputMap)
    {
        String caseIdString = (String) inputMap.get("caseIdString");
        ArrayList arrlst = new ArrayList();
        StringBuilder query = new StringBuilder();
        try
        {
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            //String[] columnValues = new String[]{"", "th.ppo_no", "ph.first_name || ph.middle_name || ph.last_name ","m.bank_name","b.branch_name", "pd.acount_no","em.emp_fname || em.emp_mname || em.emp_lname"};
            query.append(" select th.ppo_no, ph.pensioner_code, th.pension_request_id, ph.first_name,  ");
            query.append(" ph.middle_name, ph.last_name, m.bank_name, b.branch_name, ra.user_id, ra.post_id, ");
            query.append(" em.emp_fname, em.emp_mname, em.emp_lname, pd.acount_no, ra.pension_scheme ");
            query.append(" from mst_pensioner_dtls pd  ");
            query.append(" left join rlt_auditor_bank ra on pd.bank_code = ra.bank_code and pd.branch_code = ra.branch_code ");
            query.append(" join mst_pensioner_hdr ph on pd.pensioner_code = ph.pensioner_code ");
            query.append(" join trn_pension_rqst_hdr th on pd.pensioner_code = th.pensioner_code ");
            query.append(" left join org_emp_mst em on ra.user_id = em.user_id ");
            query.append(" left join mst_bank m on ra.bank_code = m.bank_code ");
            query.append(" left join rlt_bank_branch b on ra.branch_code = b.branch_code ");
            query.append(" where th.approve_status = 'APPROVED'  ");

            if(caseIdString != null && caseIdString.length() > 0 && !"()".equals(caseIdString))
	        {
	        	 //lSBQuery.append(" AND T.pensionRequestId in "+lStrMyCases);
	        	int i  = caseIdString.split("~").length;
	        	if(i > 0)
	        	{
	        		query.append("AND ( th.pension_request_id in ");
	        	}
	        	for(int k = 0;k <i;k++)
	        	{
	        		query.append( caseIdString.split("~")[k]);
	        		if(i > 1 && k<i-1)
	        		{
	        			query.append(" OR th.pension_request_id in ");
	        		}
	        	}
	        	 query.append(" )");
	        }
	        else
	        {
	        	query.append(" AND (1 = 2 )" );
	        }
            if(request.getParameter("cmbSearch") != null && request.getParameter("cmbSearch").length() >0)
            {
                String lstrSearchText = request.getParameter("cmbSearch").trim().toString();
                if(lstrSearchText.equalsIgnoreCase("bank"))
                {
                    if(request.getParameter("cmbBank2") != null)
                    {
                        query.append(" And m.bank_code = "+ request.getParameter("cmbBank2").toString());
                    }
                }
                else if(lstrSearchText.equalsIgnoreCase("branch"))
                {
                    if(request.getParameter("txtSearchbranchBank") != null)
                    {
                        query.append(" And m.bank_code = "+ request.getParameter("txtSearchbranchBank").toString());
                    }
                    if(request.getParameter("txtSearchBranch") != null)
                    {
                        query.append(" And b.branch_code = "+ request.getParameter("txtSearchBranch").toString());
                    }
                }
                else if (lstrSearchText.equalsIgnoreCase("name"))
                {
                    String lStrFname = null;
                    String lStrMname = null;
                    String lStrLname = null;
                    if (request.getParameter("txtSearchName1") != null && request.getParameter("txtSearchName1").length() > 0)
                    {
                        lStrFname = request.getParameter("txtSearchName1").toString();
                    }
                    if (request.getParameter("txtSearchName2") != null && request.getParameter("txtSearchName2").length() > 0)
                    {
                        lStrMname = request.getParameter("txtSearchName2").toString();
                    }
                    if (request.getParameter("txtSearchName3") != null && request.getParameter("txtSearchName3").length() > 0)
                    {
                        lStrLname = request.getParameter("txtSearchName3").toString();
                    }
                    query.append(" AND (");
                    if (lStrFname != null)
                    {
                        query.append(" ph.first_name LIKE '%"+lStrFname+"%' ");
                    }
                    if (lStrMname != null)
                    {
                        if(lStrFname != null)
                        {
                            query.append(" OR ");
                        }
                        query.append(" ph.middle_name LIKE '%"+lStrMname+"%' ");
                    }
                    if (lStrLname != null)
                    {
                        if(lStrMname != null || lStrFname != null)
                        {
                            query.append(" OR ");
                        }
                        query.append(" ph.last_name LIKE '%"+lStrLname+"%' ");
                    }
                    query.append(" )");
                }
                else
                {
                    if(request.getParameter("txtSearch") != null && request.getParameter("txtSearch").trim().length() >0 )
                    {
                        query.append(" AND "+ lstrSearchText + " LIKE '%"+request.getParameter("txtSearch").toString()+"%' ");
                    }
                }
                
            }
            query.append("  order by th.ppo_no ");
            
            //String orderString =  displayTag.get(Constants.KEY_SORT_ORDER).toString();
            //Integer orderbypara = (displayTag.containsKey(Constants.KEY_SORT_PARA)?(Integer)displayTag.get(Constants.KEY_SORT_PARA):1);
           
            //query.append(columnValues[orderbypara.intValue()]+" "+orderString);

            Session hiSession = getSession();
            Query hbQuery = hiSession.createSQLQuery(query.toString());
            //Integer pageNo = (displayTag.containsKey(Constants.KEY_PAGE_NO)?(Integer)displayTag.get(Constants.KEY_PAGE_NO):1);
            //hbQuery.setFirstResult((pageNo.intValue() -1 ) * Constants.PAGE_SIZE);
            //hbQuery.setMaxResults(Constants.PAGE_SIZE);
            List resultList = hbQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                Iterator it = resultList.iterator();
                Map map = new HashMap();
                AuditorVO auditorVO = new AuditorVO();
                List list1 = null;
                String name = null;
                String auditorName = null;
                ComboValuesVO cmbVo = new ComboValuesVO();
                while (it.hasNext())
                {
                    Object tuple[] = (Object[]) it.next();
                    if (!map.containsKey(tuple[0].toString()))
                    {
                        map.put(tuple[0].toString(), tuple[0].toString());
                        if (list1 != null)
                        {
                            auditorVO.setAuditorList(list1);
                            arrlst.add(auditorVO);
                        }
                        auditorVO = new AuditorVO();
                        auditorVO.setPpoNo(tuple[0].toString());
                        auditorVO.setPensionerCode((String) tuple[1]);
                        auditorVO.setRqstHdrId(Long.valueOf(tuple[2].toString()));
                        if (tuple[4] != null)
                            name = tuple[3].toString() + " " + tuple[4].toString() + " " + tuple[5].toString();
                        else
                            name = tuple[3].toString() + " " + tuple[5].toString();
                        auditorVO.setName(name);

                        if (tuple[6] == null)
                            auditorVO.setBank("");
                        else
                            auditorVO.setBank(tuple[6].toString());

                        if (tuple[7] == null)
                            auditorVO.setBranch("");
                        else
                            auditorVO.setBranch(tuple[7].toString());

                        if (tuple[13] != null)
                            auditorVO.setAccNo(tuple[13].toString());
                        else
                            auditorVO.setAccNo("");

                        list1 = new ArrayList();
                        if (tuple[6] != null && tuple[7] != null && tuple[13] != null && tuple[10] != null
                                && tuple[11] != null && tuple[12] != null)
                        {
                            if (tuple[14] != null)
                            {
                                if ("IRLA".equalsIgnoreCase(tuple[14].toString()))
                                {
                                    auditorName = tuple[10].toString() + " " + tuple[11].toString() + " "
                                            + tuple[12].toString();
                                    cmbVo = new ComboValuesVO();
                                    cmbVo.setDesc(auditorName);
                                    cmbVo.setId(tuple[8].toString());
                                    list1.add(cmbVo);
                                }
                            }
                        }
                    }
                    else
                    {
                        if (tuple[6] != null && tuple[7] != null && tuple[13] != null && tuple[10] != null
                                && tuple[11] != null && tuple[12] != null)
                        {
                            if (tuple[14] != null)
                            {
                                if ("IRLA".equalsIgnoreCase(tuple[14].toString()))
                                {
                                    auditorName = tuple[10].toString() + " " + tuple[11].toString() + " "
                                            + tuple[12].toString();
                                    cmbVo = new ComboValuesVO();
                                    cmbVo.setDesc(auditorName);
                                    cmbVo.setId(tuple[8].toString());
                                    list1.add(cmbVo);
                                }
                            }
                        }
                    }
                    if (!it.hasNext())
                    {
                        auditorVO.setAuditorList(list1);
                        arrlst.add(auditorVO);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
        }
        return arrlst;
    }
    public int getCaseAuditorDtlsCount(Map inputMap)
    {
        int count = 0;
        String caseIdString = (String) inputMap.get("caseIdString");
        StringBuilder query = new StringBuilder();
        try
        {
            HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
            query.append(" select count(th.ppo_no)");
            query.append(" from mst_pensioner_dtls pd  ");
            query.append(" left join rlt_auditor_bank ra on pd.bank_code = ra.bank_code and pd.branch_code = ra.branch_code ");
            query.append(" join mst_pensioner_hdr ph on pd.pensioner_code = ph.pensioner_code ");
            query.append(" join trn_pension_rqst_hdr th on pd.pensioner_code = th.pensioner_code ");
            query.append(" left join org_emp_mst em on ra.user_id = em.user_id ");
            query.append(" left join mst_bank m on ra.bank_code = m.bank_code ");
            query.append(" left join rlt_bank_branch b on ra.branch_code = b.branch_code ");
            query.append(" where th.approve_status = 'APPROVED'  ");

            if (caseIdString != null && caseIdString.length() > 0 && !"()".equals(caseIdString))
                query.append(" and th.pension_Request_Id in " + caseIdString);
            else
                query.append(" and 1 = 2 ");
            if(request.getParameter("cmbSearch") != null && request.getParameter("cmbSearch").length() >0)
            {
                String lstrSearchText = request.getParameter("cmbSearch").trim().toString();
                if(lstrSearchText.equalsIgnoreCase("bank"))
                {
                    if(request.getParameter("cmbBank2") != null)
                    {
                        query.append(" And m.bank_code = "+ request.getParameter("cmbBank2").toString());
                    }
                }
                else if(lstrSearchText.equalsIgnoreCase("branch"))
                {
                    if(request.getParameter("cmbBank") != null)
                    {
                        query.append(" And m.bank_code = "+ request.getParameter("cmbBank").toString());
                    }
                    if(request.getParameter("cmbBranch") != null)
                    {
                        query.append(" And b.branch_code = "+ request.getParameter("cmbBranch").toString());
                    }
                }
                else if (lstrSearchText.equalsIgnoreCase("name"))
                {
                    String lStrFname = null;
                    String lStrMname = null;
                    String lStrLname = null;
                    if (request.getParameter("txtSearchName1") != null && request.getParameter("txtSearchName1").length() > 0)
                    {
                        lStrFname = request.getParameter("txtSearchName1").toString();
                    }
                    if (request.getParameter("txtSearchName2") != null && request.getParameter("txtSearchName2").length() > 0)
                    {
                        lStrMname = request.getParameter("txtSearchName2").toString();
                    }
                    if (request.getParameter("txtSearchName3") != null && request.getParameter("txtSearchName3").length() > 0)
                    {
                        lStrLname = request.getParameter("txtSearchName3").toString();
                    }
                    query.append(" AND (");
                    if (lStrFname != null)
                    {
                        query.append(" ph.first_name LIKE '%"+lStrFname+"%' ");
                    }
                    if (lStrMname != null)
                    {
                        if(lStrFname != null)
                        {
                            query.append(" OR ");
                        }
                        query.append(" ph.middle_name LIKE '%"+lStrMname+"%' ");
                    }
                    if (lStrLname != null)
                    {
                        if(lStrMname != null || lStrFname != null)
                        {
                            query.append(" OR ");
                        }
                        query.append(" ph.last_name LIKE '%"+lStrLname+"%' ");
                    }
                    query.append(" )");
                }
                else
                {
                    if(request.getParameter("txtSearch") != null && request.getParameter("txtSearch").trim().length() >0 )
                    {
                        query.append(" AND "+ lstrSearchText + " LIKE '%"+request.getParameter("txtSearch").toString()+"%' ");
                    }
                }
            }
            Integer pageNumber = IFMSCommonServiceImpl.getPageNumber(request, "vo");
            Session hiSession = getSession();
            Query hbQuery = hiSession.createSQLQuery(query.toString());
            hbQuery.setFirstResult((pageNumber - 1) * Constants.PAGE_SIZE);
            hbQuery.setMaxResults(Constants.PAGE_SIZE);
            List resultList = hbQuery.list();
            if(resultList != null && resultList.size() >0 && resultList.get(0) != new BigDecimal(0) )
            {
                count  = ((BigDecimal) resultList.get(0)).intValue();
            }
            
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
        }
        return count;
    }


    public List getCaseIdListfromPpoNo(String ppoNo) throws Exception // check
    {
        Session hiSession = getSession();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        try
        {
            strQuery.append(" SELECT r.pensionRequestId ");
            strQuery.append(" FROM TrnPensionRqstHdr r ");
            strQuery.append(" WHERE r.ppoNo =:ppoNo");

            Query hqlQuery = hiSession.createQuery(strQuery.toString());
            hqlQuery.setString("ppoNo", ppoNo.trim());
            resultList = hqlQuery.list();
            return resultList;
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
    }
    public TrnPensionRqstHdr getChangedRqstHdrVo(String lStrPPoNo)
    {
    	 Session hiSession = getSession();
         StringBuilder strQuery = new StringBuilder();
         List resultList;
         TrnPensionRqstHdr lObjRqstHdr = new TrnPensionRqstHdr();
         try
         {
             strQuery.append(" FROM TrnPensionRqstHdr r ");
             strQuery.append(" WHERE r.ppoNo =:ppoNo AND caseStatus = 'CHANGED' ORDER BY createdDate,pensionRequestId ");

             Query hqlQuery = hiSession.createQuery(strQuery.toString());
             hqlQuery.setString("ppoNo", lStrPPoNo.trim());
             resultList = hqlQuery.list();
             if(resultList != null && resultList.size() > 0)
             {
            	 lObjRqstHdr = (TrnPensionRqstHdr) resultList.get(0);
             }
         }
         catch (Exception e)
         {
             logger.error("Error is" + e, e);
         }
		return lObjRqstHdr;
        
    }
    public List getPkListForPnsnCaseOutwardDtls(String lStrPPONo) throws Exception
    {
        Session hiSession = getSession();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        List lLstRes = new ArrayList();
        Iterator itr;
        try
        {
            strQuery.append(" SELECT caseOutwrdId ");
            strQuery.append(" FROM TrnPensionCaseOutwrd ");
            strQuery.append(" WHERE ppoNo =:ppoNo");

            Query hqlQuery = hiSession.createQuery(strQuery.toString());
            hqlQuery.setString("ppoNo", lStrPPONo.trim());
            resultList = hqlQuery.list();
            if(resultList != null && resultList.size() > 0)
            {
                itr = resultList.iterator();
                while(itr.hasNext())
                {
                    Object tuple = null;
                    tuple = (Object)itr.next();
                    lLstRes.add((Long)tuple);
                }
            }
            return lLstRes;
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
    }
}