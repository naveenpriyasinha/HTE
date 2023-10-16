package com.tcs.sgv.exprcpt.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.MstBank;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.RltBankBranch;
import com.tcs.sgv.common.valueobject.SgvaBuddemandMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.TrnRcptBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dss.service.DssDataServiceImpl;

/**
 * @author vimal
 *
 */
/**
 * @author 602581
 *
 */
public class CommonReceiptDAOImpl extends GenericDaoHibernateImpl<SgvaBudsubhdMst ,Long>
{
    Log logger = LogFactory.getLog(getClass());
    
    /**
     * @param type
     * @param sessionFactory
     */
    public CommonReceiptDAOImpl(Class<SgvaBudsubhdMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
    
    /**
     * @param majorHead
     * @param subMajorHead
     * @param minorHead
     * @param subHead
     * @return boolean
     */
    public boolean validateMajorHeadDetail(String majorHead, String subMajorHead, String minorHead, String subHead)
    {
        try
        {
            StringBuffer query = new StringBuffer();
            //System.out.println("Inside validate major head");
            query.append(" select count(*) from SgvaBudsubhdMst " +
                    " where budsubhdCode = '" + subHead + "' and budmjrhdCode = '" + majorHead +
                    "' and budsubmjrhdCode = '" + subMajorHead + "' and budminhdCode = '" + minorHead +"'");
            List resList = getSession().createQuery(query.toString()).list();
            //System.out.println("validation ==== " + resList.get(0).toString());
            if(Long.parseLong(resList.get(0).toString()) > 0)
            {
                return true;
            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.validateMajorHeadDetail # \n"+ex);
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * @param branchCode
     * @param langId
     * @param locCode
     * @return List
     */
    public List validateBankBranch(String branchCode,Long langId,String locCode)
    {
        List resList = null;
        try
        {
            String query ="SELECT mb.bankName, rbb.branchName, mb.bankCode, rbb.branchCode" +
            " FROM MstBank mb, RltBankBranch rbb" +
            " where mb.bankCode = rbb.bankCode" +
            " and rbb.branchCode = " + branchCode +
            " and mb.langId = " + langId + 
            " and rbb.locationCode = " + locCode;

            resList = getSession().createQuery(query).list();
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.validateBankBranch # \n"+ex);
            ex.printStackTrace();
        }
        return resList;
    }
    
    /**
     * @param receiptId
     * @return TrnReceiptDetails
     */
    public TrnReceiptDetails getReceiptDetail(Long receiptId)
    {
        TrnReceiptDetails rcptVO = new TrnReceiptDetails();
        //System.out.println("Inside DAO receipt detail");
        try
        {
            StringBuffer query = new StringBuffer();
            query.append("select receiptNo, receiptType,  majorHead, depositorName, amount, bankBranchCode, perticulars, cardexNo, taxType, clsExp, budType, fund, tc," +
                    " lcAccCode, salesTaxNo, bankCode, rcvdByBankDate, receiptDate, dedMonth, fromPeriod, toPeriod, demand, schemeNo, accNo, ddoCode, recRev, tinNo, vendorId" +
                    "  from TrnReceiptDetails " +
                    " where receiptDetailId = " + receiptId);
            Iterator itrRcptDtls = getSession().createQuery(query.toString()).iterate();
            Object[] rowObj = (Object[]) itrRcptDtls.next();
            rcptVO.setReceiptNo((String)rowObj[0]);
            rcptVO.setReceiptType((String)rowObj[1]);
            rcptVO.setMajorHead((String)rowObj[2]);
            rcptVO.setDepositorName((String)rowObj[3]);
            try{rcptVO.setAmount(new BigDecimal(rowObj[4].toString()));}catch(Exception ex){}
            rcptVO.setBankBranchCode((String)rowObj[5]);

            rcptVO.setPerticulars((String)rowObj[6]);
            rcptVO.setCardexNo((String)rowObj[7]);
            rcptVO.setTaxType((String)rowObj[8]);
            rcptVO.setClsExp((String)rowObj[9]);
            rcptVO.setBudType((String)rowObj[10]);
            rcptVO.setFund((String)rowObj[11]);
            try{rcptVO.setTc(Integer.parseInt(rowObj[12].toString()));}catch(Exception ex){}
            rcptVO.setLcAccCode((String)rowObj[13]);
            rcptVO.setSalesTaxNo((String)rowObj[14]);
            rcptVO.setBankCode((String)rowObj[15]);

            try{rcptVO.setRcvdByBankDate(new SimpleDateFormat("dd/MM/yyyy").parse(rowObj[16].toString()));}catch(Exception ex){rcptVO.setRcvdByBankDate((Date) rowObj[16]);}
            try{rcptVO.setReceiptDate(new SimpleDateFormat("dd/MM/yyyy").parse(rowObj[17].toString()));}catch(Exception ex){rcptVO.setReceiptDate((Date)rowObj[17]);}
            try{rcptVO.setDedMonth(new SimpleDateFormat("dd/MM/yyyy").parse(rowObj[18].toString()));}catch(Exception ex){rcptVO.setDedMonth((Date) rowObj[18]);}
            try{rcptVO.setFromPeriod(new SimpleDateFormat("dd/MM/yyyy").parse(rowObj[19].toString()));}catch(Exception ex){rcptVO.setFromPeriod((Date) rowObj[19]);}
            try{rcptVO.setToPeriod(new SimpleDateFormat("dd/MM/yyyy").parse(rowObj[20].toString()));}catch(Exception ex){rcptVO.setToPeriod((Date) rowObj[20]);}

            rcptVO.setDemand((String)rowObj[21]);
            rcptVO.setSchemeNo((String)rowObj[22]);
            rcptVO.setAccNo((String)rowObj[23]);
            rcptVO.setDdoCode((String)rowObj[24]);
            try{rcptVO.setRecRev(Integer.parseInt(rowObj[25].toString()));}catch(Exception ex){}
            try{rcptVO.setTinNo(Long.parseLong(rowObj[26].toString()));}catch(Exception ex){}
            try{rcptVO.setVendorId(Long.parseLong(rowObj[27].toString()));}catch(Exception ex){}
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getReceiptDetail # \n"+ex);
            ex.printStackTrace();
        }
        return rcptVO;
    }
    
    /**
     * @param receiptId
     * @return List
     */
    public List getReceiptBudHeadDetail(Long receiptId)
    {
        List<TrnRcptBudheadDtls> resList = new ArrayList<TrnRcptBudheadDtls>();
        try
        {
            StringBuffer query = new StringBuffer();
            if(receiptId != null)
            {
                query.append("select budMajorHead, budSubmjrHead, budMinHead, budSubHead, budDetailHead, budObjectCode, budEdpCode, amount" +
                        " from TrnRcptBudheadDtls " +
                        " where receiptDetailId = " + receiptId);

                Iterator itrRcptDtls = getSession().createQuery(query.toString()).iterate();
                while(itrRcptDtls.hasNext())
                {
                    TrnRcptBudheadDtls rcptBudVO = new TrnRcptBudheadDtls();
                    Object[] rowObj = (Object[]) itrRcptDtls.next();
                    rcptBudVO.setBudMajorHead((String)rowObj[0]);
                    rcptBudVO.setBudSubmjrHead((String)rowObj[1]);
                    rcptBudVO.setBudMinHead((String)rowObj[2]);
                    rcptBudVO.setBudSubHead((String)rowObj[3]);
                    try
                    {
                        rcptBudVO.setBudDetailHead((String)rowObj[4]);
                        rcptBudVO.setBudObjectCode((String)rowObj[5]);
                        rcptBudVO.setBudEdpCode((String)rowObj[6]);
                        rcptBudVO.setAmount(new BigDecimal(rowObj[7].toString()));
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    resList.add(rcptBudVO);
                }
            }

        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getReceiptBudHeadDetail # \n"+ex);
            ex.printStackTrace();
        }
        return resList;
    }
    
    /**
     * @param locCode
     * @return List
     */
    public List getDdoNameDetails(String locCode)
    {
        List<OrgDdoMst> resList = new ArrayList<OrgDdoMst>();
        try
        {

            StringBuffer query = new StringBuffer();
            //query.append("SELECT opm.pstShortName FROM OrgPostMst opm where opm.postId in (35001,35002,35003);");
            query.append("select odm.ddoName, odm.ddoCode from OrgDdoMst odm where odm.ddoCode in (select rdo.ddoCode from RltDdoOrg rdo where rdo.officeCode = '" + locCode +"')");
            Iterator itrDataList = getSession().createQuery(query.toString()).list().iterator();

            while(itrDataList.hasNext()) 
            {
                OrgDdoMst ddoVO = new OrgDdoMst();
                Object[] rowObj = (Object[]) itrDataList.next();
                ddoVO.setDdoName((String)rowObj[0]);
                ddoVO.setDdoCode((String)rowObj[1]);
                resList.add(ddoVO);
            }

        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getDdoNameDetails # \n"+ex);
            ex.printStackTrace();
        }
        return resList;
    }
    
    /**
     * @param ddoCode
     * @return List
     */
    public List getCardexNo(String  ddoCode)
    {
        List<OrgDdoMst> resList = new ArrayList<OrgDdoMst>();
        try
        {
            StringBuffer query = new StringBuffer();
            query.append("select odm.cardexNo from OrgDdoMst odm where odm.ddoCode = '" + ddoCode + "'");
            Iterator itrDataList = getSession().createQuery(query.toString()).list().iterator();
            if(itrDataList.hasNext())
            {
                while(itrDataList.hasNext()) 
                {
                    OrgDdoMst ddoVO = new OrgDdoMst();
                    Object rowObj = (Object) itrDataList.next();
                    //ddoVO.setCardexNo((String)rowObj);
                    resList.add(ddoVO);
                }
            }
            else
            {
                OrgDdoMst ddoVO = new OrgDdoMst();
                //ddoVO.setCardexNo("0");
                resList.add(ddoVO);
            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getCardexNo # \n"+ex);
            ex.printStackTrace();
        }
        return resList;
    }
    
    /**
     * @param finYrId
     * @return List
     */
    public List getCardexDemandDtls(Long finYrId)
    {
        List<SgvaBuddemandMst> resList = new ArrayList<SgvaBuddemandMst>();
        try
        {

            StringBuffer query = new StringBuffer();

            query.append("select sbm.demandId from SgvaBuddemandMst sbm where sbm.demandType = 'R' and sbm.finYrId = " + finYrId);
            Iterator itrDataList = getSession().createQuery(query.toString()).list().iterator();

            while(itrDataList.hasNext()) 
            {
                SgvaBuddemandMst sgvaDmndVO = new SgvaBuddemandMst();
                Object rowObj = (Object) itrDataList.next();
                sgvaDmndVO.setDemandId(Long.parseLong(rowObj.toString()));

                resList.add(sgvaDmndVO);
            }

        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getCardexDemandDtls # \n"+ex);
            ex.printStackTrace();
        }
        return resList;
    }


    /**
     * @param vendorCode
     * @return List
     */
    public List getVendorDetailByCode(String vendorCode)
    {
        List resList = null;
        try
        {
            String query ="select ven_name,ven_reg_no from mst_vendor where ven_code = " + vendorCode;
            resList = getSession().createSQLQuery(query).list();
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.validateBankBranch # \n"+ex);
            ex.printStackTrace();
        }
        return resList;
    }
    
//  public List getBankBranchName(Long bankId, Long branchId)
//  {
//  List resList = new ArrayList();
//  try
//  {
//  if(bankId != null && branchId != null)
//  {
//  String query = "select bankName from MstBank where bankId = " + bankId;
//  Iterator itrBankNameLst = getSession().createQuery(query).list().iterator();
//  if(itrBankNameLst.hasNext()) 
//  {
//  Object rowObj = (Object) itrBankNameLst.next();
//  String bankName = rowObj.toString();
//  resList.add(bankName);
//  }
//  query = "select branchName from RltBankBranch where branchId = " + branchId;
//  Iterator itrBranchNameList = getSession().createQuery(query).list().iterator();
//  if(itrBranchNameList.hasNext()) 
//  {
//  Object rowObj = (Object) itrBranchNameList.next();
//  String branchName = rowObj.toString();
//  resList.add(branchName);
//  }

//  }
//  }
//  catch(Exception ex)
//  {
//  ex.printStackTrace();
//  }
//  return resList;
//  }

    /**
     * @param bankCode
     * @return MstBank
     */
    public MstBank getBankName(String bankCode)
    {
        MstBank bankVO = new MstBank();
        try
        {
            if(bankCode != null && bankCode != "")
            {
                String query = "select bankName from MstBank where bankCode = " + bankCode;
                Iterator itrBankNameLst = getSession().createQuery(query).list().iterator();
                if(itrBankNameLst.hasNext()) 
                {
                    Object rowObj = (Object) itrBankNameLst.next();
                    bankVO.setBankName((String)rowObj);

                }
            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getBankName # \n"+ex);
            ex.printStackTrace();
        }
        return bankVO;
    }
    
    public String getBankCode(String branchCode)
    {
        String bankCode = null;
        try
        {
            if(branchCode != null && branchCode != "")
            {
                String query = "select bankCode from RltBankBranch where branchCode = " + branchCode;
                List itrBankcodeLst = getSession().createQuery(query).list();
                if(itrBankcodeLst != null) 
                {
                    bankCode = itrBankcodeLst.get(0).toString();
                }
            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getBankCode # \n"+ex);
            ex.printStackTrace();
        }
        return bankCode;
    }
    
    /**
     * @param branchCode
     * @param locCode
     * @return RltBankBranch
     */
    public RltBankBranch getBranchName(String branchCode,String locCode)
    {
        RltBankBranch branchVO = new RltBankBranch();
        try
        {
            if(branchCode != null && branchCode != "")
            {
                String query ="select branchName" +
                " from RltBankBranch" +
                " where branchCode = " + branchCode + 
                " and locationCode = " + locCode;
                Iterator itrBranchNameLst = getSession().createQuery(query).list().iterator();
                if(itrBranchNameLst.hasNext()) 
                {
                    Object rowObj = (Object) itrBranchNameLst.next();
                    branchVO.setBranchName((String)rowObj);

                }
            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getBranchName # \n"+ex);
            ex.printStackTrace();
        }
        return branchVO;
    }
    
    /**
     * @param receiptDetailId
     */
    public void delFromRcptBudDtls(Long receiptDetailId)
    {
        try
        {
            //System.out.println("Inside delete");
            if(receiptDetailId != null)
            {
                //System.out.println("Inside delete");
                String hql = "delete from TrnRcptBudheadDtls where receiptDetailId = " + receiptDetailId;
                Query query = getSession().createQuery(hql);
                int rowCount = query.executeUpdate();

                //System.out.println("Inside delete deleted row " + rowCount);

            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.delFromRcptBudDtls # \n"+ex);
            ex.printStackTrace();
        }
    }
    
    /**
     * @param receiptDetailId
     * @param rcptTypeCode
     * @param rcptCatCode
     * @param RcptNo
     * @param objectArgs
     */
    public void delFromRptDtls(Long receiptDetailId, String rcptTypeCode, String rcptCatCode, String RcptNo, Map objectArgs)
    {
        try
        {
            String query ="select rcptNo,rcptTypeCode from RptReceiptDtls" +
            " where (rcptTypeCode = '" +rcptTypeCode+"'" +
            " or rcptTypeCode = 'TcBill')"+
            " and active = 'Y'" +
            " and trnReceiptId =" + receiptDetailId;
            List lst  = getSession().createQuery(query).list();
            Iterator itrRptId = lst.iterator();

            //System.out.println("Size of List" + lst.size());
            while(itrRptId.hasNext())
            {

                Object row[] = (Object[]) itrRptId.next();
                DssDataServiceImpl servImpl = new DssDataServiceImpl();
                Map<String,Object> dssMap=new HashMap<String,Object>();
                dssMap.put("map",objectArgs);
                dssMap.put("trnReceiptId", receiptDetailId);
                dssMap.put("challanCatgCode",rcptCatCode);
                dssMap.put("rcptTypeCode", (String)row[1]);
                dssMap.put("rcptNo",(BigDecimal)row[0]);
                Map returnMap=servImpl.deleteReceiptData(dssMap);
                //System.out.println("in delete map "+ returnMap.get("flag"));

            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.delFromRptDtls # \n"+ex);
            ex.printStackTrace();
        }
    }

    /**
     * @param rcptTypeCode
     * @param RcptNo
     * @param objectArgs
     * @param receiptDetailId
     */
    public void delFromRptExpDtls(String rcptTypeCode, String RcptNo, Map objectArgs, Long receiptDetailId)
    {
        try
        {
            Session hibSession = getSession();

            ////System.out.println("inside par bank-par date");
            Query sqlQuery=hibSession.createQuery("select rcptBudId from TrnRcptBudheadDtls where receiptDetailId ="+receiptDetailId);
            List resList=sqlQuery.list();

            //System.out.println("qyeryyyyy = = "+ sqlQuery);

            //System.out.println("list size is ==" + resList.size());
            if(resList != null)
            {
                Iterator itr = resList.iterator();
                while(itr.hasNext())
                {
                    Object rcptBudId = itr.next();
                    //System.out.println("id==== "+ rcptBudId);
                    //System.out.println("Cat Code "+ rcptTypeCode);
                    //System.out.println("exp no" + RcptNo);
                    DssDataServiceImpl servImpl = new DssDataServiceImpl();
                    Map<String,Object> dssMap=new HashMap<String,Object>();
                    dssMap.put("map",objectArgs);
                    dssMap.put("expTypeCode",rcptTypeCode);
                    dssMap.put("expNo", rcptBudId);
                    Map returnMap=servImpl.deleteExpData(dssMap);

                    servImpl = new DssDataServiceImpl();
                    dssMap=new HashMap<String,Object>();
                    dssMap.put("map",objectArgs);
                    dssMap.put("expTypeCode",rcptTypeCode);
                    dssMap.put("trnExpEdpId", receiptDetailId);
                    dssMap.put("expNo", rcptBudId);

                    returnMap = servImpl.deleteExpEdpData(dssMap);

                    //itrRptId.next();
                    /*}*/
                }
            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.delFromRptExpDtls # \n"+ex);
            ex.printStackTrace();
        }
    }


    /**
     * @param majorHead
     * @return boolean
     */
    public boolean validateMajorHead(String majorHead)
    {
        List resList = new ArrayList();
        try
        {
            StringBuffer query = new StringBuffer();
            query.append("select count(*) from SgvaBudmjrhdMst where budmjrhdCode = '" + majorHead + "'");//cardexNo
            resList = getSession().createQuery(query.toString()).list();
            //System.out.println("validation ==== " + resList.get(0).toString());
            if(Long.parseLong(resList.get(0).toString()) > 0)
            {
                return true;
            }

        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.validateMajorHead # \n"+ex);
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * @param tinNo
     * @return List
     */
    public List getPartyName(Integer tinNo)
    {
        List resList = null;
        try
        {
            /*String query ="SELECT mb.bankName, rbb.branchName, mb.bankId, rbb.branchId" +
				" FROM MstBank mb, RltBankBranch rbb" +
				"  where mb.bankCode = rbb.bankCode and rbb.branchCode = " + tinNo;
		resList = getSession().createQuery(query).list();*/
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in CommonReceiptDAOImpl.getPartyName # \n"+ex);
            ex.printStackTrace();
        }
        return resList;
    }
    
    /**
     * @param locCode
     * @return List
     */
    public List getSubTreasury(String locCode)
    {
        List<CmnLocationMst> trsyList = new ArrayList<CmnLocationMst>();
        CmnLocationMst vo = new CmnLocationMst();
        Session hibSession = getSession();
        try
        {
            Query sqlQuery = hibSession.createSQLQuery("select clm.loc_id, clm.loc_name from cmn_location_mst clm" +
                    " where clm.parent_loc_id = " + locCode); 
            if(sqlQuery.list() != null)
            {
                Iterator itr = sqlQuery.list().iterator();
                if(itr.hasNext())
                {
                    Object row[] = (Object[]) itr.next();
                    vo.setLocId(Long.parseLong(row[0].toString()));
                    vo.setLocName(row[1].toString());
                    trsyList.add(vo);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return(trsyList);

    }
    
    public String getLcAccDetailByCode(String lcAccCode,Long langId)
    {
        String lcAccDesc = null;
        try
        {
            if(lcAccCode != null && !lcAccCode.equals(""))
            {
                String query ="select loc_name from cmn_location_Mst" +
                        " where department_Id = :DepartmentId" +
                        " and lang_Id = :LangId" +
                        " and loc_Short_Name = :laAccCode";
                 org.hibernate.SQLQuery oQuery  = getSession().createSQLQuery(query);
                 oQuery.setString("DepartmentId",DBConstants.LC_DEPT_ID);
                 oQuery.setLong("LangId", langId);
                 oQuery.setString("laAccCode", lcAccCode);
                 
                 List lstResult = oQuery.list();
                 
                 for(Object oRow: lstResult)
                 {
                    lcAccDesc = oRow.toString();
                 }
            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in getLcAccDetailByCode. # \n"+ex);
            ex.printStackTrace();
        }
        return lcAccDesc;
    }
    
    public List getonlineChallanPurpose(String receiptId, SessionFactory serv,Map objectArgs,Long userId)
    {
        
        List DataList = new ArrayList();
        try
        {
            if(receiptId != null && !receiptId.equals(""))
            {
                String query ="select wpd.wpd_major_head,wpd.wpd_sub_major_head,wpd.wpd_minor_head,wpd.wpd_sub_minor_head,wpd.wpd_amount" +
                                " from web_purpose_details wpd where wpd.wpd_challan_id = :receiptId";
                 org.hibernate.SQLQuery oQuery  = getSession().createSQLQuery(query);
                 oQuery.setString("receiptId", receiptId);
                 
                 List lstResult = oQuery.list();
                 if(lstResult != null)
                 {
                     Iterator it = lstResult.iterator();
                     while(it.hasNext())
                     {
                         Object[] row = (Object[]) it.next();
                         TrnRcptBudheadDtls rcptBudVO = new TrnRcptBudheadDtls();
                         rcptBudVO.setBudMajorHead((String)row[0]);
                         rcptBudVO.setBudSubmjrHead((String)row[1]); 
                         rcptBudVO.setBudMinHead((String)row[0]);
                         rcptBudVO.setBudSubHead((String)row[3]);
                         rcptBudVO.setBudMinHead((String)row[2]);
                         try{rcptBudVO.setAmount(new BigDecimal(row[4].toString()));}catch(Exception ex){}
                         rcptBudVO.setCreatedDate(new Date(System.currentTimeMillis()));
                         rcptBudVO.setCreatedPostId(SessionHelper.getPostId(objectArgs));
                         rcptBudVO.setCreatedUserId(userId);
                         rcptBudVO.setLocationCode(SessionHelper.getLocationCode(objectArgs));
                         rcptBudVO.setDbId(SessionHelper.getDbId(objectArgs));
                         DataList.add(rcptBudVO);
                     }
                 }
                 
            }
        }
        catch(Exception ex)
        {
            logger.error("Exception occured in getLcAccDetailByCode. # \n"+ex);
            ex.printStackTrace();
        }
        return DataList;
    }
    
    public Map getFromToDateList(Integer assId, Integer assYear)
    {
      Integer endAssId = new Integer(0);
      Map<String,Date> resMap = new HashMap<String, Date>();
      try
      {
        if(assId > 0 && assId < 10)
        {
          assId = assId + 2;
          endAssId = assId;
        }
        else if(assId >= 10 && assId < 13)
        {
          assId = assId - 10;
          endAssId = assId;
        }
        else if(assId == 13)
        {
          assId = 0;
          endAssId = assId + 3;
        }
        else if(assId == 14)
        {
          assId = 3;
          endAssId = assId + 3;
        }
        else if(assId == 15)
        {
          assId = 6;
          endAssId = assId + 3;
        }
        else if(assId == 16)
        {
          assId = 9;
          endAssId = assId + 3;
        }
        else if(assId == 16)
        {
          assId = 3;
          endAssId = assId - 1;
        }
        java.util.Calendar cln = Calendar.getInstance();
        cln.set(assYear,assId, 1);
        Date oStartDate = cln.getTime();
         
        cln.set(assYear,endAssId, 1);
        Date oEndDate = cln.getTime();

        /*int maxDay = cln.getActualMaximum(Calendar.DAY_OF_MONTH);
        cln.set(Calendar.DAY_OF_MONTH, maxDay);
        Date oEndDate = cln.getTime();*/

        //cln.add(Calendar.MONTH,-1);
        int maxDay = cln.getActualMaximum(Calendar.DAY_OF_MONTH);
        cln.set(Calendar.DAY_OF_MONTH, maxDay);
        Date oLastMonthEndDate = cln.getTime(); 
        
        
        resMap.put("fromDate", oStartDate);
        resMap.put("toDate", oLastMonthEndDate);
      }
      catch(Exception ex)
      {
          logger.error("Exception occured in getLcAccDetailByCode. # \n"+ex);
          ex.printStackTrace();
      }
      return resMap;
    }


}
/*

<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=ReceiptTinPartyName" 
			source="id_txtPartyTinno" 
			target="id_txtPartyName" 
			parameters="txtPartyTinno={id_txtPartyTinno}" 
			postFunction="postTinPartyName" 
			errorFunction="errorTinPartyName"></ajax:select>




<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getSubTsryData" 
	   		source="id_cmbTrsy" 
	   		target="id_cmbSubTrsy" 
		   	parameters="cmbTrsy={id_cmbTrsy}"
		   	postFunction="postTreasuryData"
		   	errorFunction="errorTreasuryData"></ajax:select>				
 */

