package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.valueobject.SgvaMonthMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeRate;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeSpecial;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public class CommonPensionDAOImpl implements CommonPensionDAO
{
    /* Global Variable for Logger Class */
    private Log logger = LogFactory.getLog(getClass());

    ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pension/PensionConstants");

    private SessionFactory sessionFactory = null;
    
    public CommonPensionDAOImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() 
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
    /* Method to get all headcode */
    public List getAllHeadCode() throws Exception
    {
        ArrayList arrHeadCode = new ArrayList();
        Iterator it;
        ComboValuesVO vo;
        Object tuple;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT headCode FROM MstPensionHeadcode order by headCode");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                it = resultList.iterator();
                while (it.hasNext())
                {
                    vo = new ComboValuesVO();
                    tuple = (Object) it.next();
                    vo.setId(tuple.toString());
                    vo.setDesc(tuple.toString());
                    arrHeadCode.add(vo);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return arrHeadCode;
    }


    /* Method to get discription of headcode */
    public String getAllHeadCodeDesc(String strHeadCode, long langId) throws Exception
    {
        String strHeadDesc = null;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            StringBuilder strQuery = new StringBuilder();
            strQuery.append(" SELECT distinct description ");
            strQuery.append(" FROM MstPensionHeadcode ");
            strQuery.append(" WHERE headCode =:headCode ");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setString("headCode", strHeadCode);

            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                strHeadDesc = resultList.get(0).toString();
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return strHeadDesc;
    }


    /* Method to get pensioncaseId, pensionID from PPo NO */
    public List getPensionerDtlsfromPpoNo(String ppoNo, long langId) throws Exception
    {
        ArrayList arrPnsnrDtls = new ArrayList();
        StringBuilder strQuery = new StringBuilder();
        Iterator itr;
        Object[] tuple;
        List resultList;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" SELECT r.pensionRequestId , m.pensionerId ");
            strQuery.append(" FROM TrnPensionRqstHdr r, MstPensionerHdr m");
            strQuery.append(" WHERE r.ppoNo = :ppoNo");
            strQuery.append(" and r.pensionerCode = m.pensionerCode ");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setString("ppoNo", ppoNo.trim());
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                itr = resultList.iterator();
                while (itr.hasNext())
                {
                    tuple = (Object[]) itr.next();
                    if (tuple[0] != null)
                        arrPnsnrDtls.add((Long) tuple[0]);
                    if (tuple[1] != null)
                        arrPnsnrDtls.add((Long) tuple[1]);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return arrPnsnrDtls;
    }
    
    public TrnPensionRqstHdr getTrnPensionRqstHdrDtls(String lStrPPONO, String lStrStatus) throws Exception
	{
		TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
		
		try
		{
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
			
	        lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.ppoNo = :lppoNo AND A.status = :lStatus ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	
	        lQuery.setParameter("lppoNo", lStrPPONO.toString());
	        lQuery.setParameter("lStatus", lStrStatus);
	        
	        List lLstVO = lQuery.list();
	          
	            if(lLstVO != null && lLstVO.size() > 0)
	            {
	            	lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
	            }
	   }
 	   catch(Exception e)
	   {
 		  logger.error("Error in getTrnPensionRqstHdrDtls. Error is : " + e, e);
 		  throw(e);
	   }

		return lobjPensionRqstHdr;
	}
	
    
    public List getAllState(long langId) throws Exception
    {
        ArrayList<ComboValuesVO> arrStateVo = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        Iterator it;
        ComboValuesVO cmbVO;
        Object[] obj;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" Select stateId,stateName ");
            strQuery.append(" From CmnStateMst");
            strQuery.append(" WHERE cmnLanguageMst.langId=:langId order by stateName");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setLong("langId", langId);
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                it = resultList.iterator();
                while (it.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) it.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrStateVo.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return arrStateVo;
    }


    public List getDistrictsOfState(BigDecimal stateId, long langId) throws Exception
    {
        ArrayList<ComboValuesVO> arrDistrict = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        ComboValuesVO cmbVO;
        Object[] obj;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" Select districtId,districtName ");
            strQuery.append(" FROM CmnDistrictMst ");
            strQuery.append(" WHERE cmnLanguageMst.langId = :langId");
            strQuery.append(" and cmnStateMst.stateId = :stateId  order by districtName");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setLong("langId", langId);
            hqlQuery.setParameter("stateId", Long.valueOf(stateId.toString()));
            resultList = hqlQuery.list();
            cmbVO = new ComboValuesVO();
            cmbVO.setId("-1");
            cmbVO.setDesc("--Select--");
            arrDistrict.add(cmbVO);
            if (resultList != null && resultList.size() > 0)
            {
                Iterator it = resultList.iterator();
                while (it.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) it.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrDistrict.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return arrDistrict;
    }


    public List getAllBank(Long langId,String locCode) throws Exception
    {
        ArrayList<ComboValuesVO> arrBankVO = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" Select bankCode,bankName ");
            strQuery.append(" FROM MstBank ");
            strQuery.append(" WHERE langId =:langId AND bankCode in (SELECT DISTINCT bankCode FROM RltBankBranch WHERE locationCode = '"+locCode+"') order by bankName");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setLong("langId", langId);
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                Iterator itr = resultList.iterator();
                while (itr.hasNext())
                {
                    ComboValuesVO cmbVO = new ComboValuesVO();
                    Object[] obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrBankVO.add(cmbVO);
                }
            }
            return arrBankVO;
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
    }


    public List getBranchsOfBank(String bankCode, Long langId,String locCode) throws Exception
    {
        ArrayList<ComboValuesVO> arrBranchVO = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        ComboValuesVO cmbVO;
        Object[] obj;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" SELECT branchCode,branchName ");
            strQuery.append(" FROM RltBankBranch ");
            strQuery.append(" WHERE bankCode =:bankCode AND locationCode =:locationCode order by branchName");

            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setLong("bankCode", Long.parseLong(bankCode));
            hqlQuery.setParameter("locationCode", locCode);
            resultList = hqlQuery.list();
            cmbVO = new ComboValuesVO();
            cmbVO.setId("-1");
            cmbVO.setDesc("--Select--");
            arrBranchVO.add(cmbVO);
            if (resultList != null && resultList.size() > 0)
            {
                Iterator itr = resultList.iterator();
                while (itr.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrBranchVO.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return arrBranchVO;
    }


    public List getBranchByBranchId(String branchCode, Long langId,String locCode) throws Exception
    {
        StringBuilder strQuery = new StringBuilder();
        List resultList = new ArrayList();
        List lLstDtlsList = new ArrayList();
        Iterator it;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" SELECT bankCode,branchCode,branchName ");
            strQuery.append(" FROM RltBankBranch ");
            strQuery.append(" WHERE branchCode =:branchCode and locationCode =:locationCode ");

            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setParameter("branchCode", Long.parseLong(branchCode));
            hqlQuery.setParameter("locationCode", locCode);
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                it = resultList.iterator();
                while (it.hasNext())
                {
                    Object tuple[] = (Object[]) it.next();
                    lLstDtlsList.add(tuple[0].toString());
                    lLstDtlsList.add(tuple[1].toString());
                    lLstDtlsList.add(tuple[2].toString());
                }
            }
        }
        catch (Exception e)
        {
            logger.info("Error is:" + e, e);
        }
        return lLstDtlsList;
    }


    public List getAllDepartment(long langId) throws Exception
    {
        ArrayList<ComboValuesVO> arrDepartnent = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        ComboValuesVO cmbVO;
        List resultList;
        Iterator itr;
        Object[] obj;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" SELECT dept.deptId, dept.deptName FROM MstPensionDept dept ");
            strQuery.append(" WHERE dept.deptIdentifier =:Identifier  ");
            strQuery.append(" and dept.langId =:langId order by deptName");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setString("Identifier", "DEPT");
            hqlQuery.setLong("langId", langId);
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                itr = resultList.iterator();
                while (itr.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrDepartnent.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return arrDepartnent;
    }


    public List getAllDesignation(long langId) throws Exception
    {
        ArrayList<ComboValuesVO> arrDesignation = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        Iterator itr;
        Object[] obj;
        ComboValuesVO cmbVO;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" SELECT dsgnCode,dsgnName ");
            strQuery.append(" FROM OrgDesignationMst ");
            strQuery.append(" WHERE cmnLanguageMst.langId =:langId order by dsgnName ");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setLong("langId", langId);
            resultList = hqlQuery.list();

            if (resultList != null && resultList.size() > 0)
            {
                itr = resultList.iterator();
                while (itr.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrDesignation.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return arrDesignation;
    }


    public List getSubTreasurysOfTreasury(String locCode, long langId) throws Exception
    {
        ArrayList<ComboValuesVO> arrBranchVO = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        ComboValuesVO cmbVO = null;
        List resultList;
        Iterator itr;
        Object[] obj;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" SELECT locationCode,locName ");
            strQuery.append(" FROM CmnLocationMst ");
            strQuery.append(" WHERE parentLocId=:parentLocId ");
            strQuery.append(" and cmnLanguageMst.langId=:langId ");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setString("parentLocId", locCode);
            hqlQuery.setLong("langId", langId);
            resultList = hqlQuery.list();
            cmbVO = new ComboValuesVO();
            cmbVO.setId("-1");
            cmbVO.setDesc("--Select--");
            arrBranchVO.add(cmbVO);
            if (resultList != null && resultList.size() > 0)
            {
                itr = resultList.iterator();
                while (itr.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrBranchVO.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return arrBranchVO;
    }


    public List getAllTreasury(long langId) throws Exception
    {
        ArrayList<ComboValuesVO> arrTreasury = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        ComboValuesVO cmbVO;
        Object[] obj;
        try
        {
            Session ghibSession = getSession();
            strQuery.append("SELECT locationCode, locName ");
            strQuery.append(" FROM CmnLocationMst");
            strQuery.append(" WHERE parentLocId = ");
            strQuery.append(" (SELECT locId FROM CmnLocationMst WHERE locShortName = '"
                    + gObjRsrcBndle.getString("CMN.DAT") + "' and cmnLanguageMst.langId = " + langId + ") and ");
            strQuery.append(" departmentId in ");
            strQuery.append(" (SELECT departmentId ");
            strQuery.append(" FROM OrgDepartmentMst ");
            strQuery.append("       WHERE depShortName = '" + gObjRsrcBndle.getString("CMN.TO")
                    + "' or depShortName = '" + gObjRsrcBndle.getString("CMN.PPO") + "') and ");
            strQuery.append("  cmnLanguageMst.langId =" + langId);
            Query hiQuery = ghibSession.createQuery(strQuery.toString());
            resultList = hiQuery.list();

            if (resultList != null && resultList.size() > 0)
            {
                Iterator itr = resultList.iterator();
                while (itr.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrTreasury.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return arrTreasury;
    }


    public List getHodFromDepartmet(BigDecimal strDepCode, long langId) throws Exception
    {
        ComboValuesVO vo = null;
        ArrayList arrHOD = new ArrayList();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        Object[] tuple;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" SELECT dept.deptId,dept.deptName ");
            strQuery.append(" FROM MstPensionDept dept ");
            strQuery.append(" WHERE dept.deptIdentifier =:Identifier ");
            strQuery.append(" and dept.parentDeptId =:parentDeptId");
            strQuery.append(" and dept.langId =:langId order by deptName");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setString("Identifier", "HOD");
            hqlQuery.setLong("parentDeptId", Long.valueOf(strDepCode.toString()));
            hqlQuery.setLong("langId", langId);
            resultList = hqlQuery.list();
            vo = new ComboValuesVO();
            vo.setId("-1");
            vo.setDesc("--Select--");
            arrHOD.add(vo);
            if (resultList != null && resultList.size() > 0)
            {
                Iterator it = resultList.iterator();
                while (it.hasNext())
                {
                    vo = new ComboValuesVO();
                    tuple = (Object[]) it.next();
                    vo.setId(tuple[0].toString());
                    vo.setDesc(tuple[1].toString());
                    arrHOD.add(vo);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        return arrHOD;
    }


    public List getAuditorBankCodeList(BigDecimal lBDUserId, BigDecimal lBDPostId) throws Exception
    {
        ArrayList<ComboValuesVO> arrBankVO = new ArrayList<ComboValuesVO>();
        List<String> lLstReturn = null;

        StringBuffer lSBQuery = new StringBuffer();

        try
        {
            Session ghibSession = getSession();
            lSBQuery.append(" SELECT DISTINCT A.bankCode,B.bankName ");
            lSBQuery.append(" FROM RltAuditorBank A,MstBank B");
            lSBQuery.append(" WHERE A.bankCode = B.bankCode ");
            lSBQuery.append(" AND A.userId = :userId ");
            lSBQuery.append(" AND A.postId = :postId ");
            lSBQuery.append(" ORDER BY A.bankCode ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("userId", lBDUserId);
            lQuery.setParameter("postId", lBDPostId);

            lLstReturn = (List<String>) lQuery.list();

            if (lLstReturn != null && lLstReturn.size() > 0)
            {
                Iterator itr = lLstReturn.iterator();
                while (itr.hasNext())
                {
                    ComboValuesVO cmbVO = new ComboValuesVO();
                    Object[] obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrBankVO.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            logger.info("Error is : " + e, e);
            throw (e);
        }
        return arrBankVO;
    }


    public List getAuditorBranchCodeList(BigDecimal lBDUserId, BigDecimal lBDPostId, String lStrAuditorBankCode, String lStrLocCode)
    throws Exception
{
ArrayList<ComboValuesVO> arrBranchVO = new ArrayList<ComboValuesVO>();
List<String> lLstReturn = null;

StringBuffer lSBQuery = new StringBuffer();

try
{
    Session ghibSession = getSession();

    lSBQuery.append(" SELECT DISTINCT A.branchCode,B.branchName ");
    lSBQuery.append(" FROM RltAuditorBank A,RltBankBranch B");
    lSBQuery.append(" WHERE A.branchCode = B.branchCode ");
    lSBQuery.append(" AND A.bankCode = B.bankCode ");
    lSBQuery.append(" AND A.userId = :userId ");
    lSBQuery.append(" AND A.postId = :postId ");
    lSBQuery.append(" AND A.bankCode = :bankCode ");
    lSBQuery.append(" AND B.locationCode = :locCode ");

    Query lQuery = ghibSession.createQuery(lSBQuery.toString());

    lQuery.setParameter("userId", lBDUserId);
    lQuery.setParameter("postId", lBDPostId);
    lQuery.setParameter("bankCode", lStrAuditorBankCode);
    lQuery.setParameter("locCode", lStrLocCode);

    lLstReturn = (List<String>) lQuery.list();

    if (lLstReturn != null && lLstReturn.size() > 0)
    {
        Iterator itr = lLstReturn.iterator();
        while (itr.hasNext())
        {
            ComboValuesVO cmbVO = new ComboValuesVO();
            Object[] obj = (Object[]) itr.next();
            cmbVO.setId(obj[0].toString());
            cmbVO.setDesc(obj[1].toString());
            arrBranchVO.add(cmbVO);
        }
    }
}
catch (Exception e)
{
    logger.info("Error is : " + e, e);
    throw (e);
}
return arrBranchVO;
}


    /**
     * To populate month combo with Sgva_Month_Mst table records
     * 
     * @param String:
     *            Lang Id
     * @return Array of VOs : of type SgvaMonthMst
     */
    public List<SgvaMonthMst> getSgvaMonthMstVO(final String lStrLangId) throws Exception
    {
        List<SgvaMonthMst> lObjSgvaMonthMst = new ArrayList<SgvaMonthMst>();

        StringBuffer lSBQuery = new StringBuffer(100);

        String lStrLangFlag = "";

        Query lQuery = null;

        try
        {
            Session ghibSession = getSession();
            if ("1".equals(lStrLangId))
            {
                lStrLangFlag = "en_US";
            }
            else
            {
                lStrLangFlag = "gu";
            }

            lSBQuery.append(" FROM SgvaMonthMst WHERE langId = :langId ORDER BY monthId ");

            lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("langId", lStrLangFlag);

            lObjSgvaMonthMst = (ArrayList<SgvaMonthMst>) lQuery.list();
        }
        catch (Exception e)
        {
            logger.info("Error is : " + e, e);
            throw (e);
        }
        return lObjSgvaMonthMst;
    }


    /**
     * To get the two digit month number from Sgva_Month_Mst
     * 
     * @param String:
     *            lStrLangId,lStrMonthName
     * @return String lStrReturnMonthNo
     */
    public String getMonthNo(final String lStrLangId, final String lStrMonthName) throws Exception
    {
        StringBuffer lSBQuery = new StringBuffer(100);

        String lStrReturnMonthNo = "";
        String lStrLangFlag = "";

        try
        {
            Session ghibSession = getSession();
            if ("1".equals(lStrLangId))
            {
                lStrLangFlag = "en_US";
            }
            else
            {
                lStrLangFlag = "gu";
            }

            lSBQuery.append(" SELECT monthNo FROM SgvaMonthMst WHERE langId= :langId AND monthName= :monthName ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("langId", lStrLangFlag);
            lQuery.setParameter("monthName", lStrMonthName);

            List lList = lQuery.list();

            if (lList != null && !lList.isEmpty())
            {
                lStrReturnMonthNo = lList.get(0).toString();
            }
        }
        catch (Exception e)
        {
            logger.info("Error is : " + e, e);
            throw (e);
        }
        return lStrReturnMonthNo;
    }


    /**
     * To populate month combo with Sgvc_Fin_Year_Mst table records
     * 
     * @param String:
     *            Lang Id
     * @return Array of VOs : of type SgvcFinYearMst
     */
    public List<SgvcFinYearMst> getSgvcFinYearMstVO(String lStrLangId) throws Exception
    {
        List<SgvcFinYearMst> lObjSgvcFinYearMst = new ArrayList<SgvcFinYearMst>();

        StringBuffer lSBQuery = new StringBuffer(100);

        String lStrLangFlag = "";

        Query lQuery = null;

        try
        {
            Session ghibSession = getSession();
            if ("1".equals(lStrLangId))
            {
                lStrLangFlag = "en_US";
            }
            else
            {
                lStrLangFlag = "gu";
            }

            lSBQuery.append(" FROM SgvcFinYearMst WHERE langId = :langId ORDER BY finYearCode ");

            lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("langId", lStrLangFlag);

            lObjSgvcFinYearMst = (ArrayList<SgvcFinYearMst>) lQuery.list();
        }
        catch (Exception e)
        {
            logger.info("Error is : " + e, e);
            throw (e);
        }
        return lObjSgvcFinYearMst;
    }
    
    public String getBankCodeForPensioner(String lStrPensionerCode,String lStrBranchCode) throws Exception
	{
		String lStrbankCode = "";
		StringBuilder strQuery = new StringBuilder();
		List resultList;
		try 
		{
			
            Session ghibSession = getSession();
            strQuery.append(" SELECT bankCode ");
			strQuery.append(" FROM MstPensionerDtls ");
			strQuery.append(" WHERE pensionerCode = :pensionerCode ");
			strQuery.append(" AND branchCode = :branchCode ");
			
			Query hqlQuery = ghibSession.createQuery(strQuery.toString());
			hqlQuery.setString("pensionerCode", lStrPensionerCode.trim());
			hqlQuery.setString("branchCode", lStrBranchCode.trim());
			resultList = hqlQuery.list();
			if (resultList != null && resultList.size() > 0) 
			{
				lStrbankCode = resultList.get(0).toString();
			}
		} 
		catch (Exception e) 
		{
            logger.error("Error is :"+e,e);
			throw e;
		}
		return lStrbankCode;
	}

    public String getPensionerCodefromPpoNo(String ppoNo) throws Exception 
    {
		String strPensionerCode = "";
		StringBuilder strQuery = new StringBuilder();
		List resultList;
		try 
		{
            Session ghibSession = getSession();
            strQuery.append(" SELECT pensionerCode ");
			strQuery.append(" FROM TrnPensionRqstHdr");
			strQuery.append(" WHERE ppoNo = :ppoNo");
			
			Query hqlQuery = ghibSession.createQuery(strQuery.toString());
			hqlQuery.setString("ppoNo", ppoNo.trim());
			resultList = hqlQuery.list();
			if (resultList != null && resultList.size() > 0) 
			{
				strPensionerCode = resultList.get(0).toString();
			}
		} 
		catch (Exception e) 
		{
            logger.error("Error is :"+e,e);
			throw e;
		}
		return strPensionerCode;
	}

	public List getAllBranchsForLocation(String langId, String locationCode) throws Exception 
	{
		ArrayList<ComboValuesVO> arrBranchVO = new ArrayList<ComboValuesVO>();
		StringBuilder strQuery = new StringBuilder();
		List resultList;
		ComboValuesVO cmbVO;
		Object[] obj;
		try
		{			
            Session ghibSession = getSession();
            strQuery.append(" SELECT branchCode,branchName ");
			strQuery.append(" FROM RltBankBranch ");
			strQuery.append(" where locationCode=:locationCode order by branchName");				
			Query hqlQuery = ghibSession.createQuery(strQuery.toString());			
			hqlQuery.setString("locationCode", locationCode);
			resultList = hqlQuery.list();
			cmbVO = new ComboValuesVO();
			cmbVO.setId("-1");
			cmbVO.setDesc("--Select--");
			arrBranchVO.add(cmbVO);
			if (resultList != null && resultList.size() > 0)
			{
				Iterator itr = resultList.iterator();
				while (itr.hasNext())
				{
					cmbVO = new ComboValuesVO();
					obj = (Object[]) itr.next();
					cmbVO.setId(obj[0].toString());
					cmbVO.setDesc(obj[1].toString());
					arrBranchVO.add(cmbVO);
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return arrBranchVO;
	}

    public List getPensionderDtlsFromBillNo(long BIllNo) throws Exception
    {
        ArrayList arrOuter = new ArrayList();
        StringBuilder query = new StringBuilder();
        try
        {
            Session ghibSession = getSession();
            /*
             * String query = " select
             * d.pension_request_id,d.pensioner_code,d.recovery_amount " + "
             * from trn_pension_bill_hdr h, trn_pension_bill_dtls d " + " where
             * h.trn_pension_bill_hdr_id = d.trn_pension_bill_hdr_id " + " and
             * h.bill_no = " + BIllNo;
             */

            query.append(" select d.pensionRequestId,d.pensionerCode,d.recoveryAmount ");
            query.append(" from TrnPensionBillHdr h, TrnPensionBillDtls d  ");
            query.append(" where h.trnPensionBillHdrId = d.trnPensionBillHdrId ");
            query.append(" and h.billNo = :billNo");

            Query sqlQuery = ghibSession.createQuery(query.toString());
            sqlQuery.setParameter("billNo", String.valueOf(BIllNo));
            List listHeadCode = sqlQuery.list();
            ArrayList arrInner = new ArrayList();
            if (listHeadCode != null && listHeadCode.size() > 0)
            {
                Iterator it = listHeadCode.iterator();
                while (it.hasNext())
                {
                    arrInner = new ArrayList();
                    Object[] tuple = (Object[]) it.next();
                    arrInner.add((Long) tuple[0]);
                    arrInner.add(tuple[1].toString());
                    arrInner.add((BigDecimal) tuple[2]);
                    arrOuter.add(arrInner);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        return arrOuter;
    }
    public Double getArrearCutAmtDtls(String lStrPnsnCode) throws Exception
    {
        List lLstAmt = new ArrayList();
        StringBuffer lSBQuery = new StringBuffer();

        int lIntCmpDate = 0;
        Query lQuery ;
        BigDecimal lBdAmt = null;
        Double lDblAmt = 0.00;
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date lDate = cal.getTime();
        StringBuffer lStrCmprDate = new StringBuffer();
        try
        {
            Session ghibSession = getSession();
            lStrCmprDate.append(lObjDateFormat.format(lDate).toString().substring(6, 10));
            lStrCmprDate.append(lObjDateFormat.format(lDate).toString().substring(3, 5));
            lIntCmpDate = Integer.valueOf(lStrCmprDate.toString());
            
            lSBQuery.append(" SELECT sum(differenceAmount) FROM  TrnPensionArrearDtls WHERE pensionerCode = :lPnsrCode ");
            lSBQuery.append(" AND paymentFromYyyymm <=" + lIntCmpDate + " AND paymentToYyyymm >=" + lIntCmpDate);

            lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("lPnsrCode", lStrPnsnCode);

            lLstAmt = lQuery.list();
            
            if(lLstAmt.size() > 0)
            {
                lBdAmt = (BigDecimal) lLstAmt.get(0);
            }
            if(lBdAmt != null)
            {
                lDblAmt = Double.valueOf(lBdAmt.toString());
            }
            
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        return lDblAmt;
    }
    public Double getRecoverAmtDtls(String pensionerCode) throws Exception
    {
       
        List lLstAmt = new ArrayList();
        StringBuffer lSBQuery = new StringBuffer();

        int lIntCmpDate = 0;
        Query lQuery ;
        BigDecimal lBdAmt = null;
        Double lDblAmt = 0.00;
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date lDate = cal.getTime();
        StringBuffer lStrCmprDate = new StringBuffer();
        try
        {
            Session ghibSession = getSession();
            lStrCmprDate.append(lObjDateFormat.format(lDate).toString().substring(6, 10));
            lStrCmprDate.append(lObjDateFormat.format(lDate).toString().substring(3, 5));
            lIntCmpDate = Integer.valueOf(lStrCmprDate.toString());
            
            lSBQuery.append(" SELECT sum(amount) FROM  TrnPensionRecoveryDtls WHERE pensionerCode = :lPnsrCode ");
            lSBQuery.append(" AND fromMonth <=" + lIntCmpDate + " AND toMonth >=" + lIntCmpDate);

            lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("lPnsrCode", pensionerCode);

            lLstAmt = lQuery.list();
            
            if(lLstAmt.size() > 0)
            {
                lBdAmt = (BigDecimal) lLstAmt.get(0);
            }
            if(lBdAmt != null)
            {
                lDblAmt = Double.valueOf(lBdAmt.toString());
            }
            
        }
        catch (Exception e)
        {
            logger.error("Error is" + e, e);
            throw e;
        }
        return lDblAmt;
        
    }
    public List getBillTypeFromHeadCode(BigDecimal lBDHeadCode) throws Exception
    {
        
        StringBuffer lSBQuery = new StringBuffer();
        Query lQuery = null;
        List lLstResLst = new ArrayList();
        try
        {
            Session ghibSession = getSession();
            lSBQuery.append(" SELECT RPC.billType FROM RltPensionHeadcodeChargable RPC,MstPensionHeadcode MPC WHERE RPC.headCode = MPC.headCode ");
            lSBQuery.append(" AND MPC.headCode =:headCode  ");
            lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setBigDecimal("headCode", lBDHeadCode);
            lLstResLst = lQuery.list();
        }
        catch(Exception e)
        {
        	logger.error("Error is" + e, e);
        	throw e;
        }
        
        return lLstResLst;
    }
    public List<TrnPensionRqstHdr> getPensionerDtlsDiffFromPpoNo(String ppoNo) throws Exception
    {
    	   StringBuffer lSBQuery = new StringBuffer();
           Query lQuery = null;
           List<TrnPensionRqstHdr> lLstResLst = new ArrayList<TrnPensionRqstHdr>();
           try
           {
               Session ghibSession = getSession();
               lSBQuery.append(" FROM TrnPensionRqstHdr D WHERE D.ppoNo  = '"+ ppoNo +"' AND ");
        	   lSBQuery.append(" (D.caseStatus = 'NEW' OR D.caseStatus = 'APPROVED') ");
        	   lQuery = ghibSession.createQuery(lSBQuery.toString());

        	   lLstResLst = (List<TrnPensionRqstHdr>)lQuery.list();
           }
           catch(Exception e)
           {
        	   logger.error("Error is" + e, e);
        	   throw e;
           }
    	return lLstResLst;
}
    
    public List getCurrentUserFromPPO(String lstrPPONo, Long lLngLangId) throws Exception
    {
        StringBuffer lQuery = new StringBuffer();
        List<String[]> userList = new ArrayList<String[]>();
        try
        {
            Session ghibSession = getSession();
            lQuery.append(" SELECT ur.orgPostMstByPostId.postId,em.empFname, em.empMname,em.empLname,MM.dsgnName ");
            lQuery.append(" from TrnPensionRqstHdr th,OrgUserpostRlt ur,OrgEmpMst em,OrgDesignationMst MM,OrgPostMst P ");
            lQuery.append(" where th.caseOwner = ur.orgPostMstByPostId.postId and ur.orgPostMstByPostId.postId = em.orgUserMst.userId ");
            lQuery.append(" and ur.orgPostMstByPostId.postId = P.postId AND P.dsgnCode = MM.dsgnCode ");
            lQuery.append(" and th.ppoNo = :ppoNo ");
           
            Query query = ghibSession.createQuery(lQuery.toString());
            query.setParameter("ppoNo", lstrPPONo);
            
            List lLstQList = query.list();
            Iterator it = lLstQList.iterator();
            while (it.hasNext()) {
                Object[] tuple = (Object[]) it.next();
                String[] result = new String[3];
                String middleName = tuple[2] != null ? tuple[2].toString() : "";
                String name = " " + tuple[1] + " " + middleName + " " + tuple[3]
                        + " [" + tuple[4] + "]";
                logger.info("Name of Audiotr for the List to be forwarded ::: "
                        + name);
                logger.info("Post of Auditor  for the List to be forwarded :::: "
                        + tuple[2]);
                
                result[0] = name;
                result[1] = tuple[0].toString();
                result[2] = "5";
                userList.add(result);
            }
        }
        catch(Exception e)
        {
           logger.error("Error is" + e, e);
           throw e;
        }
        return userList;
    } 
    
    
    /**
     * get Totat Pensioner IT Cut For the Given Month.
     * 
     * Written By Sagar
     */
    public int getLastBillCreatedMonth(String lStrPensionerCode) throws Exception
    {
        int lLastBillCrtdMnth = 0;
        
        String lStrPensionFlag = gObjRsrcBndle.getString("RECOVERY.PENSION");
        String lStrMonthlyFlag = gObjRsrcBndle.getString("RECOVERY.MONTHLY");
        
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            
            /*SELECT Max(hd.for_month) FROM Trn_Pension_Bill_Dtls dt, Trn_Pension_Bill_Hdr hd 
              WHERE dt.trn_pension_bill_hdr_id = hd.trn_pension_bill_hdr_id AND
              (hd.bill_type = 'PENSION' OR hd.bill_type = 'Monthly') AND dt.pensioner_code = '10001000338'
              GROUP BY dt.pensioner_code*/
            
            lSBQuery.append(" SELECT Max(hd.forMonth) FROM TrnPensionBillDtls dt, TrnPensionBillHdr hd ");
            lSBQuery.append(" WHERE dt.trnPensionBillHdrId = hd.trnPensionBillHdrId ");
            lSBQuery.append(" AND (hd.billType = :PensionFlag OR hd.billType = :MonthlyFlag)"); 
            lSBQuery.append(" AND dt.pensionerCode = :PensionerCode GROUP BY dt.pensionerCode"); 
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            
            lQuery.setParameter("PensionerCode", lStrPensionerCode);
            lQuery.setParameter("PensionFlag", lStrPensionFlag);
            lQuery.setParameter("MonthlyFlag", lStrMonthlyFlag);
            
            List lLstVO = lQuery.list();
              
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    if(lLstVO.get(0) != null)
                    {
                        lLastBillCrtdMnth = Integer.parseInt(lLstVO.get(0).toString());
                    }
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lLastBillCrtdMnth;
    }
    public List getDescFromAnyTablesByCode(List inputParameters) throws Exception
    {
         StringBuilder strQuery = new StringBuilder();
         List resultList = new ArrayList();
         try
         {
             Session ghibSession = getSession();
             strQuery.append(" SELECT "+ inputParameters.get(0).toString() );
             strQuery.append(" FROM "+ inputParameters.get(1).toString() );
             strQuery.append(" WHERE "+ inputParameters.get(2).toString() +" = " + inputParameters.get(3).toString());
             if(inputParameters.get(4).toString() != "-1"){
             strQuery.append(" AND "+ inputParameters.get(4).toString() +" = "+ inputParameters.get(5).toString());
             }
             Query hqlQuery = ghibSession.createQuery(strQuery.toString());
             resultList = hqlQuery.list();
            
         }
         catch (Exception e)
         {
             logger.error("Error is :" + e, e);
             throw e;
         }
         return resultList;
    }
    public RltPensionHeadcodeRate getTiAndMdeAllowFromByHeadCode(String lStrHeadCode,String lStrRetDate,String lStrTypeFlag,String lStrBasicPension) throws Exception
    {
         StringBuilder strQuery = new StringBuilder();
         List resultList = new ArrayList();
         List<RltPensionHeadcodeRate> lLStRes = new ArrayList<RltPensionHeadcodeRate>();
         SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
         RltPensionHeadcodeRate lObjVo = null;
         Iterator it;
         try
         {
             Session ghibSession = getSession();
             strQuery.append(" FROM RltPensionHeadcodeRate RPR ");
             strQuery.append(" WHERE RPR.headCode = '"+lStrHeadCode+"'");
             if(lStrRetDate != null)
             {
            	 strQuery.append(" AND (");
            	 strQuery.append("  ( RPR.effectiveFromDate <='"+ lStrRetDate +"' AND effectiveToDate >= '"+lStrRetDate+"') ");
            	 strQuery.append("  OR ( RPR.effectiveFromDate <='"+ lStrRetDate +"' AND effectiveToDate IS NULL ) )");
            	 
             }
             if(lStrTypeFlag != null)
             {
            	 strQuery.append(" AND RPR.fieldType = '"+ lStrTypeFlag +"'");
             }
             if(lStrBasicPension != null)
             {
            	 strQuery.append(" AND RPR.uptoBasic = "+ lStrBasicPension);
             }
             Query hqlQuery = ghibSession.createQuery(strQuery.toString());
             resultList = hqlQuery.list();
             
             if(resultList != null && resultList.size() >0 )
             {
                 it = resultList.iterator();
                 while (it.hasNext())
                 {
                	 RltPensionHeadcodeRate tuple = (RltPensionHeadcodeRate) it.next();
                     if(tuple != null)
                     {
                    	 lLStRes.add(tuple);
                     }
                 }
             }
             if(lLStRes != null && lLStRes.size() > 0 )
             {
            	 lObjVo = lLStRes.get(0);
             }
         }
         catch (Exception e)
         {
             logger.error("Error is :" + e, e);
             throw e;
         }
         return lObjVo;
    }
    public List getSanctionAuthPrefix(Long lLngId) throws Exception
    {
        ArrayList<ComboValuesVO> lLstCmbSanAuth = new ArrayList<ComboValuesVO>();
        List<String> lLstReturn = null;

        StringBuffer lSBQuery = new StringBuffer();

        try
        {
            Session ghibSession = getSession();
            lSBQuery.append(" SELECT authorityCode,authorityName ");
            lSBQuery.append(" FROM MstPensionAuthority ");
            lSBQuery.append(" WHERE langId =:lLngId ");
            lSBQuery.append(" ORDER BY authorityName ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("lLngId",BigDecimal.valueOf(lLngId));
          
            lLstReturn = (List<String>) lQuery.list();

            if (lLstReturn != null && lLstReturn.size() > 0)
            {
                Iterator itr = lLstReturn.iterator();
                while (itr.hasNext())
                {
                    ComboValuesVO cmbVO = new ComboValuesVO();
                    Object[] obj = (Object[]) itr.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    lLstCmbSanAuth.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            logger.info("Error is : " + e, e);
            throw (e);
        }
        return lLstCmbSanAuth;
    }
    public  String getPrifixSancAuth(Long authorityCode,Long lLngId)
    {
        StringBuffer lSBQuery = new StringBuffer();
        String lStrResPrifix = "";
        List lLstRes = null;
        try
        {
            Session ghibSession = getSession();
            lSBQuery.append(" SELECT authorityPrefix ");
            lSBQuery.append(" FROM MstPensionAuthority ");
            lSBQuery.append(" WHERE langId =:lLngId AND authorityCode =:authorityCode ");
            lSBQuery.append(" ORDER BY authorityName ");
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("lLngId",BigDecimal.valueOf(lLngId));
            lQuery.setParameter("authorityCode",authorityCode);
            lLstRes = lQuery.list();
            if(lLstRes != null && lLstRes.size() >0 )
            {
                lStrResPrifix = lLstRes.get(0).toString();
            }
        }
        catch(Exception e)
        {
            logger.info("Error is : " + e, e);
        }
        return lStrResPrifix;
    }
    public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsByPPONoAndStatus(String lStrPPONO, String lStrStatus) throws Exception
    {
        TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.ppoNo = :lppoNo AND A.status = :lStatus ");
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("lppoNo", lStrPPONO.toString());
            lQuery.setParameter("lStatus", lStrStatus);
            List lLstVO = lQuery.list();
                if(lLstVO != null &&! lLstVO.isEmpty())
                {
                    lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
                }
       }
       catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }

        return lobjPensionRqstHdr;
    }
    public TrnPensionRqstHdr getTrnPensionRqstHdrDtlsByPnsnerCodeAndStatus(String lStrPnsnrCode,String lStrStatus) throws Exception
    {
        TrnPensionRqstHdr lobjPensionRqstHdr = new TrnPensionRqstHdr();
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            lSBQuery.append(" FROM TrnPensionRqstHdr A WHERE A.pensionerCode = :lpnsrCode AND A.status = :lStatus ");
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("lpnsrCode", lStrPnsnrCode);
            lQuery.setParameter("lStatus", lStrStatus);
            List lLstVO = lQuery.list();
                if(lLstVO != null && lLstVO.size() > 0)
                {
                    lobjPensionRqstHdr = (TrnPensionRqstHdr) lLstVO.get(0);
                }
       }
        catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }
        return lobjPensionRqstHdr;
    }
    public Long getPKForBillHdr(Long lLngBillNo) throws Exception
    {
    	 List lLstVO = null;
         Long lLngPkId = 0L; 
         try
         {
             Session ghibSession = getSession();
             StringBuilder lSBQuery = new StringBuilder();
             lSBQuery.append("SELECT trnPensionBillHdrId FROM TrnPensionBillHdr  WHERE billNo = " + lLngBillNo);
             Query lQuery = ghibSession.createQuery(lSBQuery.toString());
             lLstVO = lQuery.list();
             Object tuple = (Object) lLstVO.get(0);
             lLstVO = lQuery.list();
             if(lLstVO != null && lLstVO.size() > 0)
             {
	             if(tuple != null)
	             {
	             	lLngPkId = (Long) tuple;
	             }
             }
        }
         catch(Exception e)
        {
           logger.error("Error is : " + e, e);
           throw(e);
        }
         return lLngPkId;
    }  
    public Long getPKForRltpensionCaseBill(Long lLngBillNo) throws Exception
    {
        List lLstVO = null;
        Long lLngPkId = 0L; 
        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();
            lSBQuery.append("SELECT rltPensioncaseBillId FROM RltPensioncaseBill WHERE billNo = " + lLngBillNo);
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lLstVO = lQuery.list();
            if(lLstVO != null && lLstVO.size() > 0)
            {
            	 Object tuple = (Object) lLstVO.get(0);
                 if(tuple != null)
                 {
                 	lLngPkId = (Long) tuple;
                 }
            }
       }
        catch(Exception e)
       {
          logger.error("Error is : " + e, e);
          throw(e);
       }
        return lLngPkId;
    }
    public TrnBillMvmnt getBillMvmntVo(Long lLngBillNo)
    {
    	TrnBillMvmnt lObjMvmntVo = new TrnBillMvmnt();
         try
         {
             Session ghibSession = getSession();
             StringBuilder lSBQuery = new StringBuilder();
             lSBQuery.append(" FROM TrnBillMvmnt A WHERE A.billNo = :billNo AND A.movemntId = (Select MAX(movemntId) FROM TrnBillMvmnt WHERE billNo ="+ lLngBillNo +" ) ");
             Query lQuery = ghibSession.createQuery(lSBQuery.toString());
             lQuery.setParameter("billNo", lLngBillNo);
             List lLstVO = lQuery.list();
                 if(lLstVO != null && lLstVO.size() > 0)
                 {
                	 lObjMvmntVo = (TrnBillMvmnt) lLstVO.get(0);
                 }
        }
         catch(Exception e)
        {
           logger.error("Error is : " + e, e);
        }
         return lObjMvmntVo;
    }
    
    public List getPayScaleList(Map inputMap,Long lLngId) throws Exception
    {
        ArrayList<ComboValuesVO> arrPayScaleVo = new ArrayList<ComboValuesVO>();
        StringBuilder strQuery = new StringBuilder();
        List resultList;
        Iterator it;
        ComboValuesVO cmbVO;
        Object[] obj;
        try
        {
            Session ghibSession = getSession();
            strQuery.append(" Select payscale,payscaleDesc ");
            strQuery.append(" From CmnPayscaleMst");
            strQuery.append(" WHERE langId=:langId order by payscale");
            Query hqlQuery = ghibSession.createQuery(strQuery.toString());
            hqlQuery.setLong("langId", lLngId);
            resultList = hqlQuery.list();
            if (resultList != null && resultList.size() > 0)
            {
                it = resultList.iterator();
                while (it.hasNext())
                {
                    cmbVO = new ComboValuesVO();
                    obj = (Object[]) it.next();
                    cmbVO.setId(obj[0].toString());
                    cmbVO.setDesc(obj[1].toString());
                    arrPayScaleVo.add(cmbVO);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is :" + e, e);
            throw e;
        }
        return arrPayScaleVo;
    }
    public Long getLocCodeFromRltPPOTrsryMapping(Long lLngLocCode) throws Exception
    {
    	 StringBuilder strQuery = new StringBuilder();
    	 BigDecimal lLngRes = new BigDecimal(0);
    	 List resultList;
    	 try
    	 {
    		 Session ghibSession = getSession();
    		 strQuery.append("SELECT TRSYRY_LOC_CODE FROM RLT_PPO_TRSYRY_MAPPING WHERE PPO_LOC_CODE = "+lLngLocCode);
    		 Query hqlQuery = ghibSession.createSQLQuery(strQuery.toString());
    		 resultList = hqlQuery.list();
    		 if(resultList != null && resultList.size() > 0)
    		 {
    			 lLngRes = (BigDecimal) resultList.get(0);
    		 }
    		 
    	 }
    	 catch(Exception e)
    	 {
    		 logger.error("Error is :" + e, e);
             throw e;
    	 }
    	return Long.parseLong(lLngRes.toString());
    }
    public RltPensionHeadcodeSpecial getTiRateForSpecialHeadCode(String lStrHeadCode,String lStrRetDate,String lStrBasic) throws Exception
    {
    	StringBuilder strQuery = new StringBuilder();
    	List resultList = new ArrayList();
    	 List<RltPensionHeadcodeSpecial> lLStRes = new ArrayList<RltPensionHeadcodeSpecial>();
    	 RltPensionHeadcodeSpecial lObjVo = null;
         Iterator it;
    	try
    	{
    		 Session ghibSession = getSession();
             
			strQuery.append(" FROM RltPensionHeadcodeSpecial RPR ");
             strQuery.append(" WHERE RPR.headCode = '"+lStrHeadCode+"'");
             if(lStrRetDate != null)
             {
            	 strQuery.append(" AND (");
            	 strQuery.append("  ( RPR.fromDate <='"+ lStrRetDate +"' AND RPR.toDate >= '"+lStrRetDate+"') ");
            	 strQuery.append("  OR ( RPR.fromDate <='"+ lStrRetDate +"' AND RPR.toDate IS NULL ) )");
            	 
             }
             if(lStrBasic != null && lStrBasic.length() > 0)
             {
            	 strQuery.append(" AND RPR.oldAmount = "+lStrBasic);
             }
             Query hqlQuery = ghibSession.createQuery(strQuery.toString());
             resultList = hqlQuery.list();
             
             if(resultList != null && resultList.size() >0 )
             {
                 it = resultList.iterator();
                 while (it.hasNext())
                 {
                	 RltPensionHeadcodeSpecial tuple = (RltPensionHeadcodeSpecial) it.next();
                     if(tuple != null)
                     {
                    	 lLStRes.add(tuple);
                     }
                 }
             }
             if(lLStRes != null && lLStRes.size() > 0 )
             {
            	 lObjVo = lLStRes.get(0);
             }
    	}
    	catch(Exception e)
    	{
    		 logger.error("Error is :" + e, e);
             throw e;
    	}
    	return lObjVo;
    }
}
