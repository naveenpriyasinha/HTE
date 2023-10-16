package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.pension.valueobject.MRCasesVO;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;

public class MRCaseDAOImpl implements MRCaseDAO
{
    /* Global Variable for Logger Class */
    private Log logger = LogFactory.getLog(getClass());
    
    /* Global Variable for PostId */
    Long gLngPostId = null;

    ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pension/PensionConstants");

    private SessionFactory sessionFactory = null;
    
    public MRCaseDAOImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getSession() 
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
    //---------------------------------------MR CASE starts----------------------------------------------------->   
    
    public String getFinYearDesc(long gLngFinYearId) throws Exception
    {
        String lStrFinyrdesc = null;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            StringBuilder strQuery = new StringBuilder();
            
            strQuery.append(" SELECT finYearDesc ");
            strQuery.append(" FROM SgvcFinYearMst ");
            strQuery.append(" WHERE finYearId = :finYearId");
            
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("finYearId", gLngFinYearId);

            resultList = hqlQuery.list();
            
            if (resultList != null && resultList.size() > 0)
            {
            	lStrFinyrdesc = resultList.get(0).toString();
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return lStrFinyrdesc;
    }
    //Method for generating reference no.
    public int getEntriesCount(Date currDate) throws Exception
    {
        int lIntRefNo = 0;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            StringBuilder strQuery = new StringBuilder();
            
            strQuery.append(" SELECT count(*) ");
            strQuery.append(" FROM TrnPensionMedRembrsmnt ");
            strQuery.append(" WHERE  createdDate = :currentDate");
            
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("currentDate", currDate);

            resultList = hqlQuery.list();
            
            if (resultList != null && resultList.size() > 0)
            {
            	lIntRefNo = Integer.parseInt(resultList.get(0).toString());
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return lIntRefNo;
    }
    //Method for getting saved MR cases count
    public int getSavedMRCaseDtlsCount(Map inputMap) throws Exception
    {
    	int count = 0;
        StringBuffer lSBQuery = new StringBuffer();
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        String apprRejcCases = null;
       // String apprRejcCasesIds = null;

        String savedCases = inputMap.get("SavedCases").toString();
        BigDecimal BDPostId = new BigDecimal(inputMap.get("gLngPostId").toString());
        
        
        if((inputMap.get("approvedCase") != null) && (! inputMap.get("approvedCase").equals("")))
        {
        	apprRejcCases = inputMap.get("approvedCase").toString();
        }  
        String lStrstatus = inputMap.get("RqstFrmLevel").toString();
        Integer lIntStatus = Integer.parseInt(lStrstatus);
        
        try
        {
        	if(savedCases.equals("()"))
            {
            	savedCases = "(-1)";
            }  
            lSBQuery.append(" 	SELECT Count(tpmr.medRembrsmntId) ");
            lSBQuery.append("   FROM TrnPensionMedRembrsmnt tpmr,MstPensionerHdr mph,TrnPensionRqstHdr tprh ");
            lSBQuery.append("   WHERE tpmr.ppoNo = tprh.ppoNo AND tprh.pensionerCode = mph.pensionerCode ");            
            
            if((apprRejcCases == null) || (apprRejcCases.equals(""))) //not approved or rejected 
            {
            	lSBQuery.append("   AND tpmr.medRembrsmntId IN " + savedCases);
            	lSBQuery.append("   AND tpmr.status = " + lIntStatus);
            }            
            else//for approved or rejected 
            {
            	lSBQuery.append(" AND tpmr.status in (60,70) AND tpmr.auditorPostId ="+BDPostId );
            	lSBQuery.append(" AND tpmr.billHdrId IS NULL ");
            }
            
            if (request.getParameter("cmbSearch") != null && ! request.getParameter("cmbSearch").toString().equalsIgnoreCase("-1"))
            {
                String lStrSearchValue = null;
                
                String lStrSrchStr = request.getParameter("cmbSearch").toString().trim();
                if(request.getParameter("txtSearch").toString() != null)
                {
                	lStrSearchValue = request.getParameter("txtSearch").toString().trim();
                }
                if(lStrSrchStr.equals("name"))
                {
                    String lStrFname = null;
                    String lStrMname = null;
                    String lStrLname = null;
                    
                    if (request.getParameter("txtSearchName1") != null && request.getParameter("txtSearchName1").length() > 0)
                    {
                        lStrFname = request.getParameter("txtSearchName1").toString().trim();
                    }
                    if (request.getParameter("txtSearchName2") != null && request.getParameter("txtSearchName2").length() > 0)
                    {
                        lStrMname = request.getParameter("txtSearchName2").toString().trim();
                    }
                    if (request.getParameter("txtSearchName3") != null && request.getParameter("txtSearchName3").length() > 0)
                    {
                        lStrLname = request.getParameter("txtSearchName3").toString().trim();
                    }
                    lSBQuery.append(" AND (");
                    if (lStrFname != null)
                    {
                        lSBQuery.append(" mph.firstName LIKE '%"+lStrFname+"%'");
                    }
                    if (lStrMname != null)
                    {
                        if(lStrFname != null)
                        {
                            lSBQuery.append(" OR ");
                        }
                        lSBQuery.append(" mph.middleName LIKE '%"+lStrMname+"%'");
                    }
                    if (lStrLname != null)
                    {
                        if(lStrMname != null || lStrFname!= null )
                        {
                            lSBQuery.append(" OR ");
                        }
                        lSBQuery .append(" mph.lastName LIKE '%"+lStrLname+"%'");
                    }
                    lSBQuery.append( " )");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.ppoNo"))
                {
                    lSBQuery.append(" AND tpmr.ppoNo LIKE '%" + lStrSearchValue + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.refNum"))
                {
                    lSBQuery.append(" AND tpmr.refNum like '%" + lStrSearchValue.trim() + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.inwdNo"))
                {
                    lSBQuery.append(" AND tpmr.inwdNo like '%" + lStrSearchValue.trim() + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.remAmnt"))
                {
                    lSBQuery.append(" AND tpmr.remAmnt like '%" + lStrSearchValue.trim() + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.grantAmnt"))
                {
                    lSBQuery.append(" AND tpmr.grantAmnt like '%" + lStrSearchValue.trim() + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.statusA"))
                {
                    lSBQuery.append(" AND tpmr.status =" + 60 + " ");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.statusR"))
                {
                    lSBQuery.append(" AND tpmr.status =" + 70 + " ");
                }
                else 
                {
                	if(lStrSearchValue != null && lStrSearchValue.trim().length()>0)
                	{
                		String fromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy")
                            .parse(lStrSearchValue));
                		lSBQuery.append(" AND " + lStrSrchStr + " = '" + fromDate + "'");
                	}
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
//  Method for getting saved MR cases details
    public List getSavedMRCaseDtls(Map inputMap) throws Exception
    {
        ArrayList arrMRCaseDtls = new ArrayList();
        Iterator it;
        StringBuffer lSBQuery = new StringBuffer();
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("yyyy-MM-dd");
        
        String apprRejcCases = null;
       // String apprRejcCasesIds = null;
        
        String savedCases = inputMap.get("SavedCases").toString();
        BigDecimal BDPostId = new BigDecimal(inputMap.get("gLngPostId").toString());
        
        if((inputMap.get("approvedCase") != null) && (! inputMap.get("approvedCase").equals("")))
        {
        	apprRejcCases = inputMap.get("approvedCase").toString();
        }  
        String lStrstatus = inputMap.get("RqstFrmLevel").toString();
        Integer lIntStatus = Integer.parseInt(lStrstatus);
        	
        try
        {
        	if(savedCases.equals("()"))
            {
            	savedCases = "(-1)";
            }        	
            String[] columnValues = new String[]{"","tpmr.inwdNo","tpmr.refNum","tpmr.inwdDate","tpmr.ppoNo","mph.pnsnrPrefix||''||mph.firstName||''||mph.middleName||''||mph.lastName","tpmr.remAmnt","tpmr.medRembrsmntId,tpmr.auditorPostId,tpmr.grantAmnt,tpmr.status,tpmr.status"};
        	
            lSBQuery.append(" 	SELECT tpmr.inwdNo,tpmr.refNum,tpmr.inwdDate,tpmr.ppoNo,tpmr.remAmnt, ");
            lSBQuery.append("   mph.pnsnrPrefix||''||mph.firstName||''||mph.middleName||''||mph.lastName,tpmr.medRembrsmntId, ");
            lSBQuery.append("   tprh.typeFlag,tprh.pensionRequestId,mph.pensionerId,tpmr.auditorPostId,tpmr.grantAmnt,tpmr.status,tpmr.branchCode ");
            lSBQuery.append("   FROM TrnPensionMedRembrsmnt tpmr,MstPensionerHdr mph,TrnPensionRqstHdr tprh ");
            lSBQuery.append("   WHERE tpmr.ppoNo = tprh.ppoNo AND tprh.pensionerCode = mph.pensionerCode "); 
            
            if((apprRejcCases == null) || (apprRejcCases.equals(""))) //not approved or rejected 
            {
            	lSBQuery.append("   AND tpmr.medRembrsmntId IN " + savedCases);
            	lSBQuery.append("   AND tpmr.status = " + lIntStatus);
            }            
            else//for approved or rejected 
            {
            	lSBQuery.append(" AND tpmr.status in (60,70) AND tpmr.auditorPostId ="+BDPostId );
            	lSBQuery.append(" AND tpmr.billHdrId IS NULL ");
            }
            
            if (request.getParameter("cmbSearch") != null && ! request.getParameter("cmbSearch").toString().equalsIgnoreCase("-1"))
            {
                String lStrSearchValue = null;
                
                String lStrSrchStr = request.getParameter("cmbSearch").toString().trim();
                lStrSearchValue = request.getParameter("txtSearch").toString().trim();
                if(lStrSrchStr.equals("name"))
                {
                    String lStrFname = null;
                    String lStrMname = null;
                    String lStrLname = null;
                    
                    if (request.getParameter("txtSearchName1") != null && request.getParameter("txtSearchName1").length() > 0)
                    {
                        lStrFname = request.getParameter("txtSearchName1").toString().trim();
                    }
                    if (request.getParameter("txtSearchName2") != null && request.getParameter("txtSearchName2").length() > 0)
                    {
                        lStrMname = request.getParameter("txtSearchName2").toString().trim();
                    }
                    if (request.getParameter("txtSearchName3") != null && request.getParameter("txtSearchName3").length() > 0)
                    {
                        lStrLname = request.getParameter("txtSearchName3").toString().trim();
                    }
                    lSBQuery.append(" AND (");
                    if (lStrFname != null)
                    {
                        lSBQuery.append(" mph.firstName LIKE '%"+lStrFname+"%'");
                    }
                    if (lStrMname != null)
                    {
                        if(lStrFname != null)
                        {
                            lSBQuery.append(" OR ");
                        }
                        lSBQuery.append(" mph.middleName LIKE '%"+lStrMname+"%'");
                    }
                    if (lStrLname != null)
                    {
                        if(lStrMname != null || lStrFname!= null )
                        {
                            lSBQuery.append(" OR ");
                        }
                        lSBQuery .append(" mph.lastName LIKE '%"+lStrLname+"%'");
                    }
                    lSBQuery.append( " )");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.ppoNo"))
                {
                    lSBQuery.append(" AND tpmr.ppoNo LIKE '%" + lStrSearchValue + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.refNum"))
                {
                    lSBQuery.append(" AND tpmr.refNum like '%" + lStrSearchValue.trim() + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.inwdNo"))
                {
                    lSBQuery.append(" AND tpmr.inwdNo like '%" + lStrSearchValue.trim() + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.remAmnt"))
                {
                    lSBQuery.append(" AND tpmr.remAmnt like '%" + lStrSearchValue.trim() + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.grantAmnt"))
                {
                    lSBQuery.append(" AND tpmr.grantAmnt like '%" + lStrSearchValue.trim() + "%'");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.statusA"))
                {
                	lSBQuery.append(" AND tpmr.status =" + 60 + " ");
                }
                else if(lStrSrchStr.equalsIgnoreCase("tpmr.statusR"))
                {
                	lSBQuery.append(" AND tpmr.status =" + 70 + " ");
                }
                else 
                {
                	 if(lStrSearchValue != null && lStrSearchValue.trim().length()>0)
                	 {
                		 String fromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy")
                            .parse(lStrSearchValue));
                		 lSBQuery.append(" AND " + lStrSrchStr + " = '" + fromDate + "'");
                	 }
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
                    MRCasesVO mrCasesVo = new MRCasesVO();

                    mrCasesVo.setinwdNo(tuple[0].toString());
                    mrCasesVo.setrefNum(new BigDecimal(tuple[1].toString()));
                    String r = tuple[2].toString();
                    mrCasesVo.setinwdDate(lObjSmplDtFmt.parse(tuple[2].toString()));
                    mrCasesVo.setppoNo(tuple[3].toString());
                    mrCasesVo.setpensionerName(tuple[5].toString());
                    mrCasesVo.setremAmnt(new BigDecimal(tuple[4].toString()));
                    mrCasesVo.setmedRembrsmntId(Long.parseLong(tuple[6].toString()));
                    mrCasesVo.setMstPensionerHdrId(Long.parseLong(tuple[9].toString()));
                    mrCasesVo.setTrnPensionRqstHdrId(Long.parseLong(tuple[8].toString()));
                    mrCasesVo.setTypeFlag(tuple[7].toString()); 
                    mrCasesVo.setauditorPostId(new BigDecimal(tuple[10].toString()));
                    if(tuple[11] != null)
                    {
                    	mrCasesVo.setgrantAmnt(new BigDecimal(tuple[11].toString()));
                    }
                    else
                    {
                    	mrCasesVo.setgrantAmnt(new BigDecimal(0));
                    }
                    if(tuple[12].toString().equals("60"))
                    {
                    	mrCasesVo.setapprOrRejctd("APPROVED");
                    }
                    else if(tuple[12].toString().equals("70"))
                    {
                    	mrCasesVo.setapprOrRejctd("REJECTED");
                    }
                    else
                    {
                    	mrCasesVo.setapprOrRejctd("");
                    }
                    if(tuple[13] != null)
                    {
                    	mrCasesVo.setbranchCode(tuple[13].toString());
                    }
                    
                    arrMRCaseDtls.add(mrCasesVo);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        return arrMRCaseDtls;
    }
    public String getPpoNoFromMedRemId(Long lLngmedRembrsmntId) throws Exception
    {
        String lStrPPONo = null;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            StringBuilder strQuery = new StringBuilder();
            
            strQuery.append(" SELECT ppoNo ");
            strQuery.append(" FROM TrnPensionMedRembrsmnt ");
            strQuery.append(" WHERE medRembrsmntId = :medRembrsmntId");
            
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("medRembrsmntId", lLngmedRembrsmntId);

            resultList = hqlQuery.list();
            
            if (resultList != null && resultList.size() > 0)
            {
            	lStrPPONo = resultList.get(0).toString();
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return lStrPPONo;
    }
    public String getBranchCodeFromMedRemId(Long lLngmedRembrsmntId) throws Exception
    {
        String lStrBranchCode = null;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            StringBuilder strQuery = new StringBuilder();
            
            strQuery.append(" SELECT branchCode ");
            strQuery.append(" FROM TrnPensionMedRembrsmnt ");
            strQuery.append(" WHERE medRembrsmntId = :medRembrsmntId");
            
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("medRembrsmntId", lLngmedRembrsmntId);

            resultList = hqlQuery.list();
            
            if (resultList != null && resultList.size() > 0)
            {
            	lStrBranchCode = resultList.get(0).toString();
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return lStrBranchCode;
    }
    public  String getPensionerNameFromPPO(String ppoNo) throws Exception
    {
		String lStrName = null;
		
		List<String> lLstResult = null;
		
		StringBuilder strQuery = new StringBuilder();
		
		Query lQuery =null;
		try 
		{
            Session hiSession = getSession();   
            strQuery.append(" SELECT a.pnsnrPrefix||''||a.firstName||' '||a.middleName||' '||a.lastName FROM MstPensionerHdr a,TrnPensionRqstHdr b,TrnPensionMedRembrsmnt c ");
            strQuery.append(" WHERE a.pensionerCode = b.pensionerCode AND b.ppoNo = c.ppoNo AND c.ppoNo = :ppoNo ");
			
			lQuery = hiSession.createQuery(strQuery.toString());
			lQuery.setParameter("ppoNo",ppoNo);
			
			lLstResult = lQuery.list();	
			
			if(lLstResult != null &&! lLstResult.isEmpty())
			{
				lStrName = (String) lLstResult.get(0);
			}			
		} 
		catch (Exception e) 
		{
			logger.info("Error is : "+e,e);
			throw (e);
		}
		return lStrName;
    }
    public TrnPensionMedRembrsmnt getTrnPensionMedRembrsmntVO(Long lLngmedRembrsmntId) throws Exception
    {
		TrnPensionMedRembrsmnt lLstobjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
		
		try
		{
			Session ghibSession = getSession();
			StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionMedRembrsmnt A WHERE A.medRembrsmntId = :medRembrsmntId ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("medRembrsmntId", lLngmedRembrsmntId);
	        
	        List lLstVO = lQuery.list();
	        
            if(lLstVO != null && !lLstVO.isEmpty())
            {
            	lLstobjTrnPensionMedRembrsmnt = (TrnPensionMedRembrsmnt) lLstVO.get(0);
            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error(" Error is : " + e, e);
 		  throw(e);
	   }

		return  lLstobjTrnPensionMedRembrsmnt;
    }
    public String getBranchByBranchId(String branchCode,Long gLngLangId,String gStrLocationCode) throws Exception
    {
        StringBuilder strQuery = new StringBuilder();
        String BranchName = null;
        List<String> lLstResult = null;
        
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" SELECT branchName ");
            strQuery.append(" FROM RltBankBranch ");
            strQuery.append(" WHERE branchCode = :branchCode and locationCode = :locationCode ");

            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("branchCode", Long.parseLong(branchCode));
            hqlQuery.setParameter("locationCode", gStrLocationCode);
            
			lLstResult = hqlQuery.list();	
			
			if(lLstResult != null && !lLstResult.isEmpty())
			{
				BranchName = (String) lLstResult.get(0);
			}			
        }
        catch (Exception e)
        {
            logger.info("Error is:" + e, e);
        }
        return BranchName;
    }
    //to get list of VO s for vito generation
    public ArrayList<TrnPensionMedRembrsmnt> getTrnPensionMedRembrsmntVOArray(String strSelectedRequests) throws Exception       
    {
        ArrayList<TrnPensionMedRembrsmnt> lObjTrnPensionMedRembrsmntArray = new ArrayList<TrnPensionMedRembrsmnt>();

        StringBuffer lSBQuery = new StringBuffer();

        Query lQuery = null;

        try
        {
        	Session ghibSession = getSession();
        	
            lSBQuery.append(" FROM TrnPensionMedRembrsmnt ");
            lSBQuery.append(" WHERE medRembrsmntId in ("+ strSelectedRequests + ")" );
            
            lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lObjTrnPensionMedRembrsmntArray = (ArrayList<TrnPensionMedRembrsmnt>) lQuery.list();
            
        }
        catch (Exception e)
        {
        	logger.error("Error is " + e, e);
            throw (e);
        }
        return lObjTrnPensionMedRembrsmntArray;
    }
    //to get vito heading
    public List getLocationReport(String locCode,long langID)
    {
    	List resList = null;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			lSBQuery.append(" SELECT cl.locName, cl.locAddr1 || ' ' || cl.locAddr2 ");
			lSBQuery.append(" FROM CmnLocationMst cl ");
			lSBQuery.append(" WHERE cl.locationCode = :lLocationcode ");
			lSBQuery.append(" AND cl.cmnLanguageMst.langId = :langId ");
			
			Query Query=ghibSession.createQuery(lSBQuery.toString());
			Query.setParameter("lLocationcode", locCode);
			Query.setParameter("langId", langID);
			
			resList=Query.list();
		}
    	catch(Exception e)
    	{
    		logger.error(e);
    		e.printStackTrace();
    	}
    	return resList;
    }
    public BigDecimal getAuditorPostId(String lStrBranchCode,String lStrScheme) throws Exception
    {
    	BigDecimal BDAuditorPostId = null;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            StringBuilder strQuery = new StringBuilder();
            
            strQuery.append(" SELECT postId ");
            strQuery.append(" FROM RltAuditorBank ");
            strQuery.append(" WHERE branchCode = :lStrBranchCode AND pensionScheme =:lStrScheme ");
            
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("lStrBranchCode", lStrBranchCode);
            hqlQuery.setParameter("lStrScheme", lStrScheme);
            
            resultList = hqlQuery.list();
            
            if (resultList != null && resultList.size() > 0)
            {
            	BDAuditorPostId = new BigDecimal(resultList.get(0).toString());
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return BDAuditorPostId;
    	
    }
    public List getAuditorNameListFromPostId(String strSelectedAuditors) throws Exception
    {
    	List resList = null;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			lSBQuery.append(" SELECT oem.emp_fname||oem.emp_mname||oem.emp_lname,oupr.post_Id ");
			lSBQuery.append(" FROM Org_Emp_Mst oem,Org_Userpost_Rlt oupr ");
			lSBQuery.append(" WHERE  oem.user_Id = oupr.user_Id ");
			lSBQuery.append(" AND oupr.post_id IN( "+ strSelectedAuditors+ ")" );
			
			Query Query=ghibSession.createSQLQuery(lSBQuery.toString());
			
			resList=Query.list();
			
		}
    	catch(Exception e)
    	{
    		logger.error("Error is " + e, e);
            throw (e);
    	}
    	return resList;
    }
    public String getUserName(Long gLngUserId) throws Exception
    {
    	String lStrName = null;
		
		List<String> lLstResult = null;
		
		StringBuilder strQuery = new StringBuilder();
		
		Query lQuery =null;
		try 
		{
            Session hiSession = getSession();   
            strQuery.append(" SELECT oem.emp_fname||oem.emp_mname||oem.emp_lname FROM org_emp_mst oem ");
            strQuery.append(" WHERE oem.user_id = "+gLngUserId);
			
			lQuery = hiSession.createSQLQuery(strQuery.toString());
			
			lLstResult = lQuery.list();	
			
			if(lLstResult != null &&! lLstResult.isEmpty())
			{
				lStrName = (String) lLstResult.get(0);
			}			
		} 
		catch (Exception e) 
		{
			logger.info("Error is : "+e,e);
			throw (e);
		}
		return lStrName;    	
    }
    public BigDecimal getAuditorPostIdByMedRemId(Long lLngMedRemId) throws Exception
    {
    	BigDecimal BDAuditorPostId = null;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            StringBuilder strQuery = new StringBuilder();
            
            strQuery.append(" SELECT auditorPostId ");
            strQuery.append(" FROM TrnPensionMedRembrsmnt ");
            strQuery.append(" WHERE medRembrsmntId = :lStrMedRemId ");
            
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("lStrMedRemId", lLngMedRemId);
            
            resultList = hqlQuery.list();
            
            if (resultList != null && resultList.size() > 0)
            {
            	BDAuditorPostId = new BigDecimal(resultList.get(0).toString());
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return BDAuditorPostId;
    	
    }

    public List getMRCasesListWithinPeriod(String fromDate,String toDate) throws Exception
    {
    	List resList = null;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			SimpleDateFormat lDateFormatOut = new SimpleDateFormat("MM/dd/yyyy");
			//---
			SimpleDateFormat lDateFormatIn = new SimpleDateFormat("dd/MM/yyyy");			
			//----				
			Date lDtFrom = lDateFormatIn.parse(fromDate);
			Date lDtTo = lDateFormatIn.parse(toDate);
			
			lSBQuery.append(" select * FROM Trn_Pension_Med_Rembrsmnt  ");
			lSBQuery.append(" WHERE  inwd_Date BETWEEN to_date('"+ lDateFormatOut.format(lDtFrom).toString() +"','MM/dd/yyyy') " + " AND to_date('"+ lDateFormatOut.format(lDtTo).toString()+"','MM/dd/yyyy')");
			lSBQuery.append(" AND status IN(60,70) AND bill_hdr_id IS NULL ");
								
			Query Query=ghibSession.createSQLQuery(lSBQuery.toString());
						
			/*SimpleDateFormat lDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			
			Date lDtFrom = lDateFormat.parse(fromDate);
			Date lDtTo = lDateFormat.parse(toDate);
			
			//System.out.println("-------"+lDtFrom);
			//System.out.println("-------"+lDtTo);
			
			Query.setParameter("FromDate", lDtFrom);
			Query.setParameter("ToDate", lDtTo);
*/
			
			resList=Query.list();
			
		}
    	catch(Exception e)
    	{
    		logger.error("Error is " + e, e);
            throw (e);
    	}
    	return resList;
    }
    public List getAuditorNameListWithinPeriod(String fromDate,String toDate) throws Exception
    {
    	List resList = null;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			SimpleDateFormat lDateFormatOut = new SimpleDateFormat("MM/dd/yyyy");
			//---
			SimpleDateFormat lDateFormatIn = new SimpleDateFormat("dd/MM/yyyy");			
			//----				
			Date lDtFrom = lDateFormatIn.parse(fromDate);
			Date lDtTo = lDateFormatIn.parse(toDate);
			
			//Date lDtFrom = lDateFormat.parse(fromDate);
			//Date lDtTo = lDateFormat.parse(toDate);
			
			lSBQuery.append(" SELECT oem.emp_fname||oem.emp_mname||oem.emp_lname,oupr.post_Id ");
			lSBQuery.append(" FROM Org_Emp_Mst oem,Org_Userpost_Rlt oupr,Trn_Pension_Med_Rembrsmnt tpmr ");
			lSBQuery.append(" WHERE  oem.user_Id = oupr.user_Id ");
			lSBQuery.append(" AND oupr.post_id = tpmr.auditor_Post_Id ");
			lSBQuery.append(" AND tpmr.inwd_Date BETWEEN to_date('"+ lDateFormatOut.format(lDtFrom).toString() +"','MM/dd/yyyy') " + " AND to_date('"+ lDateFormatOut.format(lDtTo).toString()+"','MM/dd/yyyy')");
			lSBQuery.append(" AND tpmr.status IN("+ 60+","+70+ ")");
			
			Query Query=ghibSession.createSQLQuery(lSBQuery.toString());			
			
			resList=Query.list();
			
		}
    	catch(Exception e)
    	{
    		logger.error("Error is " + e, e);
            throw (e);
    	}
    	return resList;
    }
    public List getIdsForWithinPeriodRequests(String fromDate,String toDate) throws Exception
    {
    	List resList = null;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();	
			
			SimpleDateFormat lDateFormatOut = new SimpleDateFormat("MM/dd/yyyy");
			//---
			SimpleDateFormat lDateFormatIn = new SimpleDateFormat("dd/MM/yyyy");			
			//----				
			Date lDtFrom = lDateFormatIn.parse(fromDate);
			Date lDtTo = lDateFormatIn.parse(toDate);
					
			lSBQuery.append(" SELECT tpmr.med_Rembrsmnt_Id ");
			lSBQuery.append(" FROM Trn_Pension_Med_Rembrsmnt tpmr ");
			lSBQuery.append(" WHERE  tpmr.inwd_Date BETWEEN to_date('"+ lDateFormatOut.format(lDtFrom).toString() +"','MM/dd/yyyy') " + " AND to_date('"+ lDateFormatOut.format(lDtTo).toString()+"','MM/dd/yyyy')");
			lSBQuery.append(" AND tpmr.status IN("+ 60+","+70+ ") AND tpmr.bill_hdr_id IS NULL ");
			
			Query Query=ghibSession.createSQLQuery(lSBQuery.toString());
			
			/*SimpleDateFormat lDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			
			Date lDtFrom = lDateFormat.parse(fromDate);
			//System.out.println("11111111111111111111111111111111111111111111111111111111"+lDtFrom);
			Date lDtTo = lDateFormat.parse(toDate);
			//System.out.println("11111111111111111111111111111111111111111111111111111111"+lDtTo);
			
			Query.setParameter("FromDate", lDtFrom);
			Query.setParameter("ToDate", lDtTo);*/
			
			resList=Query.list();
			
		}
    	catch(Exception e)
    	{
    		logger.error("Error is " + e, e);
            throw (e);
    	}
    	return resList;
    }
    public String getAuditorNameFromPostId(BigDecimal postId) throws Exception
    {
    	String auditorName = null;
    	List lLstResult = null;
    	
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			lSBQuery.append(" SELECT oem.emp_fname||oem.emp_mname||oem.emp_lname ");
			lSBQuery.append(" FROM Org_Emp_Mst oem,Org_Userpost_Rlt oupr ");
			lSBQuery.append(" WHERE  oem.user_Id = oupr.user_Id ");
			lSBQuery.append(" AND oupr.post_id = "+postId);			
            
            Query hqlQuery = ghibSession.createSQLQuery(lSBQuery.toString());
            
            lLstResult = hqlQuery.list();
            
            if (lLstResult != null && lLstResult.size() > 0)
            {
            	auditorName = (String)lLstResult.get(0).toString();
            }
		}
    	catch(Exception e)
    	{
    		logger.error("Error is " + e, e);
            throw (e);
    	}
    	return auditorName;
    }
    
    //---------------------------------------MR CASE ends----------------------------------------------------->    
    
    //---------------------------------------MR BILL starts----------------------------------------------------->
    public List getMRBillData(String fromDate,String toDate,String lStrBranchCode,BigDecimal BDHeadCode) throws Exception
    {
    	List resList = null;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			SimpleDateFormat lDateFormatOut = new SimpleDateFormat("MM/dd/yyyy");
			//---
			SimpleDateFormat lDateFormatIn = new SimpleDateFormat("dd/MM/yyyy");			
			//----				
			Date lDtFrom = lDateFormatIn.parse(fromDate);
			Date lDtTo = lDateFormatIn.parse(toDate);
			
			lSBQuery.append(" SELECT r.inwd_no,r.ppo_no,r.pensioner_name,r.rem_amnt,r.grant_amnt,oem.emp_fname||''||oem.emp_mname||''||oem.emp_lname,r.med_rembrsmnt_id ");
			lSBQuery.append(" FROM trn_pension_med_rembrsmnt r,org_emp_mst oem ,org_userpost_rlt t ");
			lSBQuery.append(" WHERE  r.branch_code ="+ lStrBranchCode + " AND r.head_code ="+ BDHeadCode+" AND r.status = 60 AND oem.user_id = t.user_id and t.post_id = r.authoriser_post_id AND r.bill_hdr_id IS NULL ");
			lSBQuery.append(" AND r.inwd_Date BETWEEN to_date('"+ lDateFormatOut.format(lDtFrom).toString() +"','MM/dd/yyyy') " + " AND to_date('"+ lDateFormatOut.format(lDtTo).toString()+"','MM/dd/yyyy')");
			
			
			Query Query=ghibSession.createSQLQuery(lSBQuery.toString());
			
			resList=Query.list();
		}
    	catch(Exception e)
    	{
    		logger.error(e);
    		e.printStackTrace();
    	}
    	return resList;

    }
	public List getTrnPensionMedRembrsmnt(Long lTrnPensionBillHdrPK) throws Exception
	{
		List resList = null;
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			lSBQuery.append(" SELECT r.inwd_no,r.ppo_no,r.pensioner_name,r.rem_amnt,r.grant_amnt,oem.emp_fname||''||oem.emp_mname||''||oem.emp_lname,r.med_rembrsmnt_id ");
			lSBQuery.append(" FROM trn_pension_med_rembrsmnt r,org_emp_mst oem ,org_userpost_rlt t ");
			lSBQuery.append(" WHERE r.bill_Hdr_Id ="+lTrnPensionBillHdrPK+" and oem.user_id = t.user_id and t.post_id = r.authoriser_post_id ");
	        
	        Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
	        
	        resList=lQuery.list();
	          
	   }
	   catch(Exception e)
	   {
			  logger.error(" Error is : " + e, e);
			  throw(e);
	   }
		return resList;
		
	}
	public List getBnkBrnchHdcodeList(Long lTrnPensionBillHdrPK) throws Exception
	{
		List resList = null;
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			lSBQuery.append(" SELECT rbb.bankCode,r.branchCode,r.headCode ");
			lSBQuery.append(" FROM TrnPensionMedRembrsmnt r,RltBankBranch rbb ");
			lSBQuery.append(" WHERE r.billHdrId ="+lTrnPensionBillHdrPK+" AND r.branchCode = rbb.branchCode ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        resList=lQuery.list();
	          
	   }
	   catch(Exception e)
	   {
			  logger.error(" Error is : " + e, e);
			  throw(e);
	   }
		return resList;		
	}    
    //---------------------------------------MR BILL ends----------------------------------------------------->	
}
