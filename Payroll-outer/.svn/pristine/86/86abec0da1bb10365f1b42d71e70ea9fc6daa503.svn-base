package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.valueobject.RltBillParty;
import com.tcs.sgv.exprcpt.helper.ReportHelper;
import com.tcs.sgv.pension.valueobject.MonthlyPensionBillVO;
import com.tcs.sgv.pension.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.MstPensionerNomineeDtls;
import com.tcs.sgv.pension.valueobject.SavedPensionBillVO;

/**
 * PensionBillDAOImpl Its a DAO for Pension Bill Preparation.
 * 
 * @author Sagar Patel
 * @version 1.0
 */
public class PensionBillDAOImpl implements PensionBillDAO
{

    /* Global Variable for Logger Class */
    private Log logger = LogFactory.getLog(getClass());

    /* Global Variable for Session Class */

    ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");

    private SessionFactory sessionFactory = null;


    public PensionBillDAOImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }


    public Session getSession()
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }


    /**
     * Get Claimant inforation by post.
     * 
     * @param Long
     *            lClaimantpostID
     * @return lDDOInfo List
     */
    public List getClaimantDDOInfo(Long lLngLocCode) throws Exception
    {
        List lDDOInfo = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();
            Session ghibSession = getSession();
            lSBQuery.append(" select em.empFname || ' ' || em.empMname || ' ' || em.empLname,");
            lSBQuery.append(" dm.dsgnName, ddm.ddoName, ddm.ddoCode,ddm.cardexNo ");
            lSBQuery.append(" from OrgDesignationMst dm, OrgEmpMst em, OrgUserpostRlt up, ");
            lSBQuery.append(" OrgPostDetailsRlt pd, CmnLocationMst cl, OrgDdoMst ddm");
            lSBQuery
                    .append(" where dm.dsgnId = pd.orgDesignationMst.dsgnId and em.orgUserMst.userId = up.orgUserMst.userId and ");
            lSBQuery
                    .append(" up.orgPostMstByPostId.postId = pd.orgPostMst.postId and ddm.postId = pd.orgPostMst.postId and");
            lSBQuery.append(" pd.cmnLocationMst.locId = cl.locId and pd.cmnLocationMst.locId = "+lLngLocCode);
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lDDOInfo = (List) lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw (e);
        }

        return lDDOInfo;
    }


    public List getOtherUsersList(String lStrLevel, String lStrHirarchyRefId, String lStrOtherType, String lStrPostId,String lStrCategry,String lStrSubName)
    {

        StringBuilder lSBQuery = new StringBuilder();
        Session ghibSession = getSession();
        List lLstRes = new ArrayList();
        try
        {
            lSBQuery
                    .append(" SELECT RLS.LEVEL_CODE,RLS.LEVEL_DESC,WPM.POST_ID,OUM.EMP_FNAME || OUM.EMP_MNAME || OUM.EMP_LNAME ");
            lSBQuery.append(" FROM RLT_LEVEL_STATUS RLS,WF_HIERACHY_POST_MPG WPM , ORG_EMP_MST OUM ");
            if (lStrOtherType != null && lStrOtherType.equals("SP"))
            {
                //lSBQuery.append(" WHERE WPM.LEVEL_ID = " + lStrLevel + " and WPM.POST_ID != " + lStrPostId + " AND RLS.CATEGORY ='"+ DBConstants.CATEGORY_PPO +"' AND ");
            }
            else if (lStrOtherType != null && lStrOtherType.equals("SOT"))
            {
                lSBQuery.append(" WHERE WPM.LEVEL_ID < " + lStrLevel + " and ");
            }
            lSBQuery.append(" WPM.LEVEL_ID = RLS.LEVEL_CODE ");
            lSBQuery.append(" AND  RLS.CATEGORY = '"+ lStrCategry+"'");
            if(lStrSubName != null && lStrSubName.equals("PensionBill Processing"))
            {
            	lSBQuery.append(" AND WPM.LEVEL_ID >= 20 ");
            }
            lSBQuery.append(" AND WPM.HIERACHY_REF_ID = " + lStrHirarchyRefId
                    + " AND OUM.USER_ID = WPM.POST_ID ORDER BY RLS.LEVEL_CODE ");
            
            Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

            List lLstQList = lQuery.list();
            Iterator it = lLstQList.iterator();
            int lIntLoopJ = 0;
            while (it.hasNext())
            {
                Object[] tuple = (Object[]) it.next();
                String[] result = new String[4];
                result[0] = tuple[0].toString();
                result[1] = tuple[1].toString();
                result[2] = tuple[2].toString();
                result[3] = tuple[3].toString();
                lLstRes.add(result);
                lIntLoopJ++;
            }
        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
        }
        return lLstRes;
    }


    /**
     * Get Pension Bills Party Details...
     * 
     * @author Sagar.
     */

    public List<RltBillParty> getPensionerPartyDtls(Map inputMap) throws Exception
    {
        String lPnsnsBillType = inputMap.get("subjectId").toString();
        String lPnsnPPONo = inputMap.get("PPONo").toString();
        String lPensionType = null;
        String lPensionScheme = null;
        String lPPOCaseStatus = bundleConst.getString("STATUS.CONTINUE");
        String lPnsnCode = null;

        String lStrPnsnBillType = bundleConst.getString("BILLTYPE.PENSION");
        String lMRBillType = "43";
        String lStrCVPBillType = bundleConst.getString("BILLTYPE.CVP");
        String lStrDCRGBillType = bundleConst.getString("BILLTYPE.DCRG");

        if (inputMap.containsKey("PensionType"))
        {
            lPensionType = inputMap.get("PensionType").toString();
        }
        if (inputMap.containsKey("PensionScheme"))
        {
            lPensionScheme = inputMap.get("PensionScheme").toString();
        }

        RltBillParty lObjRltBillPartyVO = null;
        List<RltBillParty> lLstRltBillPartyVO = new ArrayList<RltBillParty>();
        MstPensionerHdr lObjPensionerHdr = null;

        List lresNomineeList = null;
        List lresFmlyMemberList = null;

        lPnsnCode = getPnsnCodeFromPPONo(lPnsnPPONo, lPPOCaseStatus);

        try
        {
            Session ghibSession = getSession();
            if ((lPnsnsBillType.equalsIgnoreCase(lStrPnsnBillType) && "Monthly".equals(lPensionType)) ||  ("MR".equals(lPensionType)))
            {
                if ("IRLA".equals(lPensionScheme) || ("MR".equals(lPensionType)))
                {
                    lObjRltBillPartyVO = new RltBillParty();

                    StringBuilder lSBQuery = new StringBuilder();

                    lSBQuery
                            .append(" select t.branchName from RltBankBranch t where t.bankCode= :lBankCode and t.branchCode= :lBranchCode ");

                    Query lQuery = ghibSession.createQuery(lSBQuery.toString());

                    lQuery.setParameter("lBankCode", Long.parseLong(inputMap.get("Bank").toString()));
                    lQuery.setParameter("lBranchCode", Long.parseLong(inputMap.get("Branch").toString()));

                    String lBankName = lQuery.list().get(0).toString();

                    lObjRltBillPartyVO.setPartyName(lBankName);
                    // lObjRltBillPartyVO.setPartyAddr(lBankName);
                    Double amount = 0D;
                    if (inputMap.get("TotalNetAmt") != null)
                    {
                        amount = Double.parseDouble(inputMap.get("TotalNetAmt").toString());
                    }
                    long lLngAmount = Math.round(amount);
                    lObjRltBillPartyVO.setPartyAmt(new BigDecimal(lLngAmount));

                    lLstRltBillPartyVO.add(lObjRltBillPartyVO);
                }
                else if ("RUBARU".equals(lPensionScheme))
                {
                    MonthlyPensionBillVO lObjMonthlyPensionVO = null;
                    List<MonthlyPensionBillVO> lLstlObjMonthlyPensionVO = new ArrayList<MonthlyPensionBillVO>();
                    lLstlObjMonthlyPensionVO = (ArrayList<MonthlyPensionBillVO>) inputMap.get("MonthlyPensionList");

                    if (lLstlObjMonthlyPensionVO != null && lLstlObjMonthlyPensionVO.size() > 0)
                    {
                        for (int i = 0; i < lLstlObjMonthlyPensionVO.size(); i++)
                        {
                            lObjRltBillPartyVO = new RltBillParty();

                            lObjMonthlyPensionVO = lLstlObjMonthlyPensionVO.get(i);

                            if (lObjMonthlyPensionVO != null)
                            {
                                lObjRltBillPartyVO.setPartyName(lObjMonthlyPensionVO.getPensionerName());
                                // lObjRltBillPartyVO.setPartyAddr(lBankName);
                                Double amount = Double.parseDouble(lObjMonthlyPensionVO.getNetPensionAmount()
                                        .toString());
                                long lLngAmount = Math.round(amount);
                                lObjRltBillPartyVO.setPartyAmt(new BigDecimal(lLngAmount));

                                lLstRltBillPartyVO.add(lObjRltBillPartyVO);
                            }

                        }
                    }
                }
                else if ("MONEY ORDER".equals(lPensionScheme))
                {
                    lObjRltBillPartyVO = new RltBillParty();

                    StringBuilder lSBQuery = new StringBuilder();

                    lSBQuery.append(" select t.name from MstPensionPostmaster t ");
                    lSBQuery.append(" where t.locationCode= :lLocCode ");

                    Query lQuery = ghibSession.createQuery(lSBQuery.toString());

                    lQuery.setParameter("lLocCode", SessionHelper.getLocationCode(inputMap).toString());
                    
                    String lPartyName = lQuery.list().get(0).toString();

                    lObjRltBillPartyVO.setPartyName(lPartyName);
                    Double amount = 0D;
                    if (inputMap.get("TotalNetAmt") != null)
                    {
                        amount = Double.parseDouble(inputMap.get("TotalNetAmt").toString());
                    }
                    long lLngAmount = Math.round(amount);
                    lObjRltBillPartyVO.setPartyAmt(new BigDecimal(lLngAmount));

                    lLstRltBillPartyVO.add(lObjRltBillPartyVO);
                }

            }
            else
            {
                StringBuilder lSBQuery = new StringBuilder();

                lSBQuery.append(" FROM MstPensionerHdr A WHERE A.pensionerCode = :lPensionerCode ");

                Query lQuery = ghibSession.createQuery(lSBQuery.toString());

                lQuery.setParameter("lPensionerCode", lPnsnCode);

                lObjPensionerHdr = (MstPensionerHdr) lQuery.list().get(0);

                // List lLstAcNoDtls =
                // getPartyAcNoDtls(lObjPensionerHdr.getPensionerCode());

                if (lObjPensionerHdr.getDateOfDeath() != null)
                {
                    if (lPnsnsBillType.equalsIgnoreCase(lStrDCRGBillType))
                    {
                        lresNomineeList = getNomineeDtls(lPnsnCode);

                        if (lresNomineeList != null)
                        {
                            for (int i = 0; i < lresNomineeList.size(); i++)
                            {
                                MstPensionerNomineeDtls lTempObj = (MstPensionerNomineeDtls) lresNomineeList.get(i);
                                lObjRltBillPartyVO = new RltBillParty();

                                lObjRltBillPartyVO.setPartyName(lTempObj.getName().toString().toUpperCase());

                                Long tPartyAmt = (Long) inputMap.get("NetAmount");

                                tPartyAmt = (tPartyAmt * Long.parseLong(lTempObj.getPercent().toString())) / 100;

                                lObjRltBillPartyVO.setPartyAmt(new BigDecimal(tPartyAmt));

                                lLstRltBillPartyVO.add(lObjRltBillPartyVO);
                            }
                        }

                    }
                    else if (lPnsnsBillType.equalsIgnoreCase(lStrCVPBillType)
                            || lPnsnsBillType.equalsIgnoreCase(lStrPnsnBillType)) // /
                    // CVP
                    // &
                    // Pension
                    {
                        lresFmlyMemberList = getFmlyMemberDtls(lPnsnCode);

                        if (lresFmlyMemberList != null)
                        {
                            for (int i = 0; i < lresFmlyMemberList.size(); i++)
                            {
                                MstPensionerFamilyDtls lObjMstPensionerFamilyDtlsVO = (MstPensionerFamilyDtls) lresFmlyMemberList
                                        .get(i);
                                lObjRltBillPartyVO = new RltBillParty();

                                Double lPercentage = 0D;
                                Double salary = 0D;

                                if ("Husband/Wife".equals(lObjMstPensionerFamilyDtlsVO.getRelationship())
                                        || "Mother".equals(lObjMstPensionerFamilyDtlsVO.getRelationship())
                                        || "Father".equals(lObjMstPensionerFamilyDtlsVO.getRelationship()))
                                {
                                    lObjRltBillPartyVO.setPartyName(lObjMstPensionerFamilyDtlsVO.getName());
                                    lPercentage = Double.valueOf(lObjMstPensionerFamilyDtlsVO.getPercentage()
                                            .toString());
                                    lObjRltBillPartyVO.setAccntNo(lObjMstPensionerFamilyDtlsVO.getAccntNo());
                                }
                                else
                                {
                                    if ("N".equals(lObjMstPensionerFamilyDtlsVO.getMarriedFlag()))
                                    {
                                        if (lObjMstPensionerFamilyDtlsVO.getSalary() != null)
                                        {
                                            salary = Double.parseDouble(lObjMstPensionerFamilyDtlsVO.getSalary()
                                                    .toString());
                                        }
                                        else
                                        {
                                            salary = 0.00;
                                        }

                                        if (salary <= 2550)
                                        {
                                            if ("N".equals(lObjMstPensionerFamilyDtlsVO.getMajorFlag()))
                                            {
                                                if (lObjMstPensionerFamilyDtlsVO.getGuardianName() != null)
                                                {
                                                    lObjRltBillPartyVO.setPartyName(lObjMstPensionerFamilyDtlsVO
                                                            .getGuardianName());
                                                }
                                                else
                                                {
                                                    lObjRltBillPartyVO.setPartyName(" ");
                                                }
                                                lPercentage = Double.valueOf(lObjMstPensionerFamilyDtlsVO
                                                        .getPercentage().toString());
                                                lObjRltBillPartyVO.setAccntNo(lObjMstPensionerFamilyDtlsVO
                                                        .getAccntNo());
                                            }
                                            else
                                            {
                                                lObjRltBillPartyVO.setPartyName(lObjMstPensionerFamilyDtlsVO
                                                        .getName());
                                                lPercentage = Double.valueOf(lObjMstPensionerFamilyDtlsVO
                                                        .getPercentage().toString());
                                                lObjRltBillPartyVO.setAccntNo(lObjMstPensionerFamilyDtlsVO
                                                        .getAccntNo());
                                            }
                                        }
                                    }
                                }

                                if (lPnsnsBillType.equalsIgnoreCase(lStrCVPBillType))
                                {
                                    Double lNetAmt = Double.valueOf(inputMap.get("NetAmount").toString());
                                    Double lPaybleAmt = (lNetAmt * lPercentage) / 100;
                                    lObjRltBillPartyVO.setPartyAmt(new BigDecimal(lPaybleAmt));

                                }
                                else if (lPnsnsBillType.equalsIgnoreCase(lStrPnsnBillType))
                                {
                                    Double lNetAmt = Double.valueOf(inputMap.get("lNetPensionAmt").toString());
                                    Double lPaybleAmt = (lNetAmt * lPercentage) / 100;
                                    lObjRltBillPartyVO.setPartyAmt(new BigDecimal(lPaybleAmt));
                                }

                                lLstRltBillPartyVO.add(lObjRltBillPartyVO);
                            }
                        }
                    }

                }
                else
                {
                    lObjRltBillPartyVO = new RltBillParty();
                    String lPartName = null;
                    if (lObjPensionerHdr.getMiddleName() != null && lObjPensionerHdr.getMiddleName().length() > 0)
                    {
                        lPartName = lObjPensionerHdr.getFirstName()+ " " + lObjPensionerHdr.getMiddleName() + " "
                                + lObjPensionerHdr.getLastName();
                    }
                    else
                    {
                        lPartName = lObjPensionerHdr.getFirstName()+ " " + lObjPensionerHdr.getLastName();
                    }

                    lObjRltBillPartyVO.setPartyName(lPartName.toString().toUpperCase());
                    lObjRltBillPartyVO.setPartyAddr(lObjPensionerHdr.getPensnerAddr());
                    // lObjRltBillPartyVO.setAccntNo(lLstAcNoDtls.get(0).toString());

                    if (lPnsnsBillType.equalsIgnoreCase(lStrCVPBillType))
                    {
                        lObjRltBillPartyVO.setPartyAmt(new BigDecimal(inputMap.get("NetAmount").toString()));

                    }
                    else if (lPnsnsBillType.equalsIgnoreCase(lStrDCRGBillType))
                    {
                        lObjRltBillPartyVO.setPartyAmt(new BigDecimal(inputMap.get("NetAmount").toString()));

                    }
                    else if (lPnsnsBillType.equalsIgnoreCase(lStrPnsnBillType))
                    {
                        lObjRltBillPartyVO.setPartyAmt(new BigDecimal(inputMap.get("lNetPensionAmt").toString()));
                    }

                    lLstRltBillPartyVO.add(lObjRltBillPartyVO);
                }

            }

        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw (e);
        }

        return (List<RltBillParty>) lLstRltBillPartyVO;
    }


    public String getPnsnCodeFromPPONo(String lStrPPONO, String lStrStatus) throws Exception
    {
        String lrestString = null;

        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery
                    .append("Select A.pensionerCode FROM TrnPensionRqstHdr A WHERE A.ppoNo = :lPPONO AND A.status = :lStatus ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("lPPONO", lStrPPONO.toString());
            lQuery.setParameter("lStatus", lStrStatus);

            List lLstVO = lQuery.list();

            if (lLstVO != null && lLstVO.size() > 0)
            {
                lrestString = lLstVO.get(0).toString();
            }
        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw (e);
        }

        return lrestString;
    }


    /**
     * Get Nominee Detls
     * 
     * @author Sagar
     */
    private List getNomineeDtls(String lPnsnCode) throws Exception
    {
        List lresLst = null;
        List<MstPensionerNomineeDtls> lObjNomineeDtls = null;

        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" FROM MstPensionerNomineeDtls PN WHERE PN.pensionerCode = :lPensionerCode");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("lPensionerCode", lPnsnCode);

            lresLst = (List) lQuery.list();

            if (lresLst.size() > 0)
            {
                lObjNomineeDtls = new ArrayList<MstPensionerNomineeDtls>();
                for (int i = 0; i < lresLst.size(); i++)
                {
                    lObjNomineeDtls.add((MstPensionerNomineeDtls) lresLst.get(i));
                }
            }

        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw (e);
        }

        return lObjNomineeDtls;
    }


    /**
     * Get Family Member Dtls Details
     * 
     * @author Sagar
     */
    private List getFmlyMemberDtls(String lPnsnCode) throws Exception
    {
        List lresLst = null;
        List<MstPensionerFamilyDtls> lObjFmlyMemDtls = null;

        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" FROM MstPensionerFamilyDtls PN WHERE PN.pensionerCode = :lPensionerCode");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("lPensionerCode", lPnsnCode);

            lresLst = (List) lQuery.list();

            if (lresLst.size() > 0)
            {
                lObjFmlyMemDtls = new ArrayList<MstPensionerFamilyDtls>();
                for (int i = 0; i < lresLst.size(); i++)
                {
                    lObjFmlyMemDtls.add((MstPensionerFamilyDtls) lresLst.get(i));
                }
            }

        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw (e);
        }

        return lObjFmlyMemDtls;
    }


    /**
     * Getting a Party Address And Account No Details . . .
     */
    public List getPartyAcNoDtls(String lStrPnsnerCode) throws Exception
    {
        List lresList = new ArrayList();

        try
        {
            Session ghibSession = getSession();
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" SELECT pd.acountNo FROM MstPensionerDtls pd");
            lSBQuery.append(" WHERE pd.pensionerCode = :lPensionerCode AND pd.activeFlag = :lActiveFlg ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("lPensionerCode", lStrPnsnerCode);
            lQuery.setParameter("lActiveFlg", "Y");

            List lresult = (List) lQuery.list();

            if (lresult.size() > 0)
            {
                lresList.add(lresult.get(0).toString());
            }

        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw (e);
        }

        return lresList;
    }


    public List<SavedPensionBillVO> getMyBills(Long userId, Long langId, String lMyBillList, Map orgsMap)
            throws Exception
    {

        String[] lStrSortCols = new String[] { "", "tbr.ref_num", "tbr.token_Num", "tbr.budmjr_hd,tpbh.head_code",
                "tpbh.scheme", "tbr.ppo_no", "RBP.Party_Name", "mbt.short_name", "tbr.bill_net_amount", "tbr.bill_Date",
                "tbr.tc_bill", "" };
        List<SavedPensionBillVO> dataList = new ArrayList<SavedPensionBillVO>();
        List<SavedPensionBillVO> dataList2 = new ArrayList<SavedPensionBillVO>();
        HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");
        Integer orderType = IFMSCommonServiceImpl.getOrderType(request, "vo");
        Integer sortColumn = IFMSCommonServiceImpl.getSortIndex(request, "vo");
        List<Long> lLngList = new ArrayList<Long>(); 
        try
        {
            Session ghibSession = getSession();
            if (lMyBillList != null && lMyBillList.length() > 0)
            {
                if (lMyBillList.equals("()"))
                    lMyBillList = "(-1)";

                StringBuilder queryBuilder = new StringBuilder();

                queryBuilder
                        .append("SELECT tbr.tc_bill,tbr.bill_No,tbr.bill_gross_amount, tbr.bill_net_amount, tbr.bill_Date,");
                queryBuilder.append("tbr.budmjr_hd,tbr.token_Num, tbr.ref_num, tbr.aud_post_id, tbr.subject_id,");
                queryBuilder
                        .append("tbr.curr_bill_status,tbr.hierarchy_ref_id,tbr.ppo_no, mbt.short_name as billdesc,");
                queryBuilder.append("oem.emp_fname, oem.emp_mname, oem.emp_lname, tmp.nObj, tbr.bill_cntrl_no,");
                queryBuilder.append("tpbh.head_code,tpbh.scheme,rbp.party_name ");
                queryBuilder
                        .append(" FROM trn_bill_register tbr JOIN mst_bill_type mbt ON tbr.subject_id = mbt.subject_id");
                queryBuilder
                        .append(" LEFT JOIN RLT_BILL_PARTY  RBP ON RBP.BILL_NO = TBR.BILL_NO JOIN org_ddo_mst odm ON tbr.ddo_code = odm.ddo_code join trn_pension_bill_hdr tpbh on tbr.bill_no = tpbh.bill_no");
                queryBuilder.append(" LEFT OUTER JOIN org_userpost_rlt our ON tbr.aud_post_id = our.post_id");
                queryBuilder.append(" LEFT OUTER JOIN org_emp_mst oem ON our.user_id = oem.user_id");
                queryBuilder
                        .append(" LEFT OUTER JOIN (SELECT rbo.bill_no AS bNo, count(rbo.bill_objc_id) AS nObj FROM rlt_bill_objection rbo "
                                + "WHERE ( rbo.bill_no IN ");
                if(lMyBillList != null && lMyBillList.length() > 0 && !"(-1)".equals(lMyBillList))
    	        {
                	int i  = lMyBillList.split("~").length;
    	        	for(int k = 0;k <i;k++)
    	        	{
    	        		queryBuilder.append( lMyBillList.split("~")[k] );
    	        		if(i > 1 && k<i-1)
    	        		{
    	        			queryBuilder.append(" OR rbo.bill_no in");
    	        		}
    	        	}
    	        }
                else
                {
                	 queryBuilder.append(lMyBillList);
                }
               queryBuilder.append(" ) GROUP BY rbo.bill_no) tmp ON tbr.bill_no = tmp.bNo");
                queryBuilder.append(" WHERE ( tbr.bill_no IN " );
        		 if(lMyBillList != null && lMyBillList.length() > 0 && !"(-1)".equals(lMyBillList))
      	        {
                  	int i  = lMyBillList.split("~").length;
      	        	for(int k = 0;k <i;k++)
      	        	{
      	        		queryBuilder.append( lMyBillList.split("~")[k] );
      	        		if(i > 1 && k<i-1)
      	        		{
      	        			queryBuilder.append(" OR tbr.bill_no in");
      	        		}
      	        	}
      	        }
	              else
	              {
	              	 queryBuilder.append(lMyBillList);
	              } 		
                queryBuilder.append(" ) AND mbt.lang_id = " + langId
                        + " AND (oem.lang_id = " + langId + " OR oem.lang_id IS NULL)");
                queryBuilder
                        .append(" AND (tbr.ppo_No is NULL OR (tbr.bill_No IN (SELECT rpb.bill_No FROM rlt_pensioncase_bill rpb WHERE tbr.bill_No = rpb.bill_No)))");

               /*if (Short.parseShort(orgsMap.get("savedBillsStatus").toString())== DBConstants.ST_BAPRV_AUD)
               {
                  	 queryBuilder.append(" AND tbr.curr_bill_status in ("+ DBConstants.ST_BAPRV_AUD +","+ DBConstants.ST_BRJCT_AUD +")" );
               }
                else  if (orgsMap.get("savedBillsStatus") != null)
                {
                    queryBuilder.append(" AND tbr.curr_bill_status = " + orgsMap.get("savedBillsStatus").toString());
                }
               */
                
                // Added By Chandra sekhar for Search Option Start
                String lStrSearchStr = null;
                String lStrSearchVale = null;
                if (request.getParameter("cmbSearch") != null && ! request.getParameter("cmbSearch").toString().equals("-1"))
                {
                    lStrSearchStr = request.getParameter("cmbSearch").toString();
                if (request.getParameter("txtSearch") != null && request.getParameter("txtSearch").toString().trim().length() >0 )
                {
                    lStrSearchVale = request.getParameter("txtSearch").toString();
                }
                if (lStrSearchStr != null && lStrSearchStr != "-1")
                {
                    if (lStrSearchVale != null && lStrSearchStr.trim().length() > 0)
                    {
                        if (lStrSearchStr.equalsIgnoreCase("tbr.bill_Date"))
                        {
                            String fromDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat(
                                    "dd/MM/yyyy").parse(lStrSearchVale));
                            queryBuilder.append("AND tbr.bill_Date = '" + fromDate + "'");
                        }
                        else if (lStrSearchStr.equalsIgnoreCase("tbr.ref_num"))
                        {
                            queryBuilder.append("AND tbr.ref_num = " + lStrSearchVale + "");
                        }
                        else
                        {
                            queryBuilder.append(" AND " + lStrSearchStr + " LIKE  '%" + lStrSearchVale + "%'");
                        }

                    }
                }
                }
                String lStrSort = "ASC";
                if (orderType == 2)
                    lStrSort = "DESC";
                if(sortColumn != null && sortColumn != -1 && lStrSortCols[sortColumn].length()>0)
                {
                    queryBuilder.append(" ORDER BY ");
                    queryBuilder.append(lStrSortCols[sortColumn]+" "+lStrSort);
                }
                else
                {
                    queryBuilder.append(" ORDER BY ");
                    queryBuilder.append(" tbr.created_date");
                }
                // Added By Chandra sekhar for Search Option End

                Query sqlquery = ghibSession.createSQLQuery(queryBuilder.toString());

                List resList = sqlquery.list();

                if (resList != null && resList.size() > 0)
                {
                    for (int x = 0; x < resList.size(); x++)
                    {
                        SavedPensionBillVO lObjVo = new SavedPensionBillVO();
                        Object[] tuple = (Object[]) resList.get(x);
                        String lStrName = "";
                        if (tuple[0] != null)
                            lObjVo.setBillCtgry(tuple[0].toString());
                        if (tuple[1] != null)
                            lObjVo.setBillNo(Long.parseLong(tuple[1].toString()));
                        if (tuple[2] != null)
                            lObjVo.setBillGrossAmt((BigDecimal) tuple[2]);
                        if (tuple[3] != null)
                            lObjVo.setBillNetAmt((BigDecimal) tuple[3]);
                        if (tuple[4] != null)
                            lObjVo.setBillDate((Date) tuple[4]);
                        if (tuple[5] != null)
                            lObjVo.setBdjtMjrHd(tuple[5].toString());
                        if (tuple[6] != null)
                            lObjVo.setTokenNum(Long.parseLong(tuple[6].toString()));
                        if (tuple[7] != null)
                            lObjVo.setRefNum(Long.parseLong(tuple[7].toString()));
                        if (tuple[8] != null)
                            lObjVo.setAudPostId(Long.valueOf(tuple[8].toString()));
                        if (tuple[9] != null)
                            lObjVo.setSubjectID(Long.valueOf(tuple[9].toString()));
                        if (tuple[10] != null)
                            lObjVo.setCurrBillStatus((BigDecimal) tuple[10]);
                        if (tuple[11] != null)
                            lObjVo.setHierarchyRefId(Long.parseLong(tuple[11].toString()));
                        if (tuple[12] != null)
                            lObjVo.setPpoNo(tuple[12].toString());
                        if (tuple[13] != null)
                            lObjVo.setBillDesc(tuple[13].toString());
                        if (tuple[14] != null)
                            lStrName = tuple[14].toString();
                        if (tuple[15] != null)
                            lStrName = lStrName +" "+ tuple[15].toString();
                        if (tuple[16] != null)
                            lStrName = lStrName +" "+ tuple[16].toString();
                        lObjVo.setAudtrName(lStrName);
                        if (tuple[17] != null)
                            lObjVo.setObjCount(Integer.parseInt(tuple[17].toString()));
                        if (tuple[18] != null)
                            lObjVo.setBillCntrlNo(tuple[18].toString());
                        if (tuple[19] != null)
                            lObjVo.setHeadCode((BigDecimal) tuple[19]);
                        if (tuple[20] != null)
                            lObjVo.setScheme(tuple[20].toString());
                        if (tuple[21] != null)
                            lObjVo.setPartyName(tuple[21].toString());
                        if(! lLngList.contains(Long.parseLong(tuple[1].toString())))
                        {
                            dataList.add(lObjVo);
                            lLngList.add(Long.parseLong(tuple[1].toString()));
                        }
                        else
                        {
                            dataList2.add(lObjVo);
                        }
                    }
                }
            }
            if(dataList2 != null && dataList2.size() > 0)
            {
                for(int i = 0; i<dataList.size(); i++)
                {
                   for(int k=0;k<dataList2.size();k++ )
                   {
                       if(dataList.get(i).getBillNo().equals(dataList2.get(k).getBillNo()))
                       {
                           dataList.get(i).setPartyName(dataList.get(i).getPartyName()+","+dataList2.get(k).getPartyName());
                       }
                   }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error is " + e, e);
            throw e;
        }
        return dataList;
    }
    public Map genBillTranRegForAudit(Long userId,String vitoType, String selectedBills, String strLocationCode,Long langID, Long gLngPostId,Map billPostMap,Map audPostMap) 
    {
        Double total = 0.0;
        int totalBill = 0;
        Map vitoMap = new HashMap();
        List vitoSummaryList = new ArrayList();
        List vitoDataList = new ArrayList();
        try {
            Session hibSession = getSession();
            Query sqlQuery = null;
            StringBuffer query = new StringBuffer();

            query.append("select br.refNum,br.tokenNum,br.budmjrHd,bt.shortName,br.billNetAmount,dm.cardexNo, "
                        + " dm.shortName,em.empFname,em.empMname,em.empLname,br.audPostId,br.billNo "
                            + " from TrnBillRegister br,MstBillType bt,OrgEmpMst em, OrgDdoMst dm,OrgUserpostRlt pm"
                            + " where em.orgUserMst.userId = pm.orgUserMst.userId and pm.orgPostMstByPostId.postId = br.audPostId "
                            + " and br.ddoCode = dm.ddoCode and br.subjectId=bt.subjectId "
                            + " and br.tokenNum is not null "                                                      
                            + " and br.billNo IN (" + selectedBills + ") ");
                            //+ " and br.currBillStatus = " + DBConstants.ST_BS_TO);
            query.append(" and br.tsryOfficeCode = :locationCode and bt.langId=:langId and em.cmnLanguageMst.langId=:langId and dm.langId=:langId");
            query.append(" order by br.audPostId, br.refNum asc");
            
            logger.info("input parameters for bill transit register for Audit : "+ "\n userId : "+ userId + "\n vitoType: "+vitoType
                + "\n selectedBills: "+selectedBills + "\n strLocationCode: "+strLocationCode + "\n langID: "+ langID + "\n gLngPostId: "+gLngPostId);
            logger.info("query for bill transit register for audit : "+query.toString());
            
            sqlQuery = hibSession.createQuery(query.toString());
                        
            sqlQuery.setString("locationCode",strLocationCode);
            sqlQuery.setLong("langId",langID);
            List resList = sqlQuery.list();
            List vitoList = null;
            String prevAudPostId = "";
            String audPostId = "";
            String prevAudName = "";    
            String audName = "";
            List vitoSummaryInnerList = null;
            List vitoStringList = new ArrayList();
            int listSize = resList.size();
            int counter = 0;
            int summaryCount = 1;
            double grandTotal = 0.0;
            int grandTotalBills = 0;
            if (resList != null && !resList.isEmpty()) 
            {
                
                for (Object row : resList) 
                {                   
                    Object[] tuple = (Object[]) row;    
                    //audPostId = tuple[10].toString();
                    audPostId = (String)billPostMap.get(tuple[11].toString());
                    //audName = tuple[7] + " " + tuple[8] + " " + tuple[9];
                    audName = audPostMap.get(audPostId).toString();
                    if(counter == 0 )
                    {
                        prevAudPostId = audPostId;
                        prevAudName = audName;
                    }
                    if(!prevAudPostId.equals(audPostId) || counter == listSize)
                    {                         
                        vitoList = new ArrayList();
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoStringList.add(vitoList);
                        vitoList = new ArrayList();
                        vitoList.add("Total ");
                        vitoList.add("Bills ");
                        vitoList.add(":"+totalBill);
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add(" Total Amount : ");
                        //vitoList.add(ReportHelper.numberFormat(new BigDecimal(total), 2));
                        vitoStringList.add(vitoList);
                        
                        //vitoMap.put(prevAudPostId, vitoStringList);
                        vitoDataList.add(vitoStringList);
                        vitoSummaryInnerList = new ArrayList();
                        vitoSummaryInnerList.add(summaryCount);
                        vitoSummaryInnerList.add(prevAudName);                                      
                        vitoSummaryInnerList.add(totalBill);
                        //vitoSummaryInnerList.add(ReportHelper.numberFormat(new BigDecimal(total), 2));
                        vitoSummaryList.add(vitoSummaryInnerList);
                        grandTotalBills = grandTotalBills + totalBill;
                        grandTotal = grandTotal + total;
                        vitoStringList = new ArrayList();                       
                        total = 0.0;
                        totalBill = 0;
                        summaryCount++;
                    }
                    vitoList = new ArrayList();
                    vitoList.add(tuple[0].toString());
                    if (tuple[1] != null) 
                        vitoList.add(tuple[1].toString());
                    else
                        vitoList.add("0");
                    vitoList.add(tuple[2].toString());
                    vitoList.add((String) tuple[3]);
                    vitoList.add(tuple[5].toString());
                    vitoList.add((String) tuple[6]);
                    //vitoList.add(ReportHelper.numberFormat((BigDecimal) tuple[4], 2));
                    vitoStringList.add(vitoList);                   
                    total = total + Double.parseDouble(tuple[4].toString());
                    totalBill++;                
                                    
                    prevAudPostId = audPostId;
                    prevAudName = audName;
                    counter++;
                    if(counter == listSize){
                        vitoList = new ArrayList();
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoStringList.add(vitoList);
                        vitoList = new ArrayList();
                        vitoList.add("Total ");
                        vitoList.add("Bills ");
                        vitoList.add(":"+totalBill);
                        vitoList.add("-");
                        vitoList.add("-");
                        vitoList.add(" Total Amount : ");
                        //vitoList.add(ReportHelper.numberFormat(new BigDecimal(total), 2));
                        vitoStringList.add(vitoList);
                        
                        //vitoMap.put(audPostId, vitoStringList);
                        vitoDataList.add(vitoStringList);
                        vitoSummaryInnerList = new ArrayList();
                        vitoSummaryInnerList.add(summaryCount);
                        vitoSummaryInnerList.add(audName);                                      
                        vitoSummaryInnerList.add(totalBill);
                       // vitoSummaryInnerList.add(ReportHelper.numberFormat(new BigDecimal(total), 2));
                        vitoSummaryList.add(vitoSummaryInnerList);
                        grandTotalBills = grandTotalBills + totalBill;
                        grandTotal = grandTotal + total;
                        vitoStringList = new ArrayList();                       
                        total = 0.0;
                        totalBill = 0;
                        summaryCount++;
                    }
                }
                vitoSummaryInnerList = new ArrayList();
                vitoSummaryInnerList.add("");
                vitoSummaryInnerList.add("Total");                                      
                vitoSummaryInnerList.add(grandTotalBills);
                //vitoSummaryInnerList.add(ReportHelper.numberFormat(new BigDecimal(grandTotal), 2));
                vitoSummaryList.add(vitoSummaryInnerList);
            }
            vitoMap.put("summaryList",vitoSummaryList);
            vitoMap.put("vitoDataList",vitoDataList);
        }       
        catch (Exception e) 
        {
            e.printStackTrace();
            logger.error("Exception occured in PensionBillDao .genBillTranRegForAudit # \n" + e);
        }
        return vitoMap;
    }

    public String isAllBillsCreated(String lStrppoNo) throws Exception
    {
        try
        {
            Session ghibSession = getSession();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" FROM RltPensioncaseBill RPB ");
            queryBuilder.append(" WHERE RPB.ppoNo =:ppoNo AND billNo IS NULL ");
            Query hqlQuery = ghibSession.createQuery(queryBuilder.toString());
            hqlQuery.setParameter("ppoNo", lStrppoNo);
            List lStRes = hqlQuery.list();
            if (lStRes != null && lStRes.size() > 0)
            {
                return "N";
            }
        }
        catch (Exception e)
        {
            logger.error("Error is : " + e, e);
            throw e;
        }
        return "Y";
    }
}