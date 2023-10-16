package com.tcs.sgv.lcm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.lcm.valueobject.LcAccMstVO;
import com.tcs.sgv.lcm.valueobject.LcDivisionDtlsVO;
import com.tcs.sgv.lcm.valueobject.MstLcDivisionAccount;


public class LcDivisionAccMstDAOImpl 
extends GenericDaoHibernateImpl<MstLcDivisionAccount,Long> implements LcDivisionAccMstDAO 
{
	public LcDivisionAccMstDAOImpl(Class<MstLcDivisionAccount> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory); 
    }
	
	
	CommonVO lCmnVO = null ;
	ComboValuesVO lComboValuesVO=null;
	LcDivisionDtlsVO lDivDtlVO=null;
	final Logger glogger=Logger.getLogger(LcDivisionAccMstDAOImpl.class);
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	public List getDepartmentLookup(String lIParentLkpId, long lILangId) 
	{	
		Session hibSession = getSession();
		
		ArrayList lArrCmnVOList = new ArrayList();
				
		String lStrDeptQry = new String();
		
		lStrDeptQry = "select c.lookup_id,c.lookup_desc from cmn_lookup_mst c where c.parent_lookup_id='"+lIParentLkpId+"' and c.lang_id="+lILangId;
		
		glogger.info("Query For getDepartmentLookup  is :::: "+ lStrDeptQry);
		Query query = hibSession.createSQLQuery(lStrDeptQry);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{			
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[])it.next();
			String lStrLkpID = (tuple[0].toString());
			String lStrLkpName = (tuple[1].toString());
			
			lCmnVO.setCommonId(lStrLkpID);
			lCmnVO.setCommonDesc(lStrLkpName);
			lArrCmnVOList.add(lCmnVO);
			
		}
	
		return lArrCmnVOList;
	}

	public Map getDistrictDtls(String lStrLodCode, long lILangId)
	{		
		Session hibSession = getSession();
		ArrayList lArrCmnVOList = new ArrayList();
		HashMap lHmpDist = new HashMap();
		String lStrDeptQry = new String();
		String lStrDistName = "";
		String lStrDistId = "";
		
		lStrDeptQry = "select x.district_name,x.district_id from cmn_district_mst x where x.district_id=" +
				"(select t.loc_district_id from cmn_location_mst t where t.location_code='"+lStrLodCode+"' and t.lang_id="+lILangId+")";
		
		glogger.info("Query For getDistrictDtls  is :::: "+ lStrDeptQry);
		Query query = hibSession.createSQLQuery(lStrDeptQry);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			Object[] tuple =(Object[])it.next(); 
			lStrDistName = (tuple[0].toString());
			lStrDistId= (tuple[1].toString());
			glogger.info("District name in dao is :: "+lStrDistName );
			glogger.info("District id in dao is :: "+lStrDistId );
			lHmpDist.put("districtname", lStrDistName);
			lHmpDist.put("districtid", lStrDistId);
		}
		
		return lHmpDist;
	}
	
	public List getBankDtls(long lILangId) 
	{		
		Session hibSession = getSession();
		ArrayList lArrCmnVOList = new ArrayList();
				
		String lStrDeptQry = new String();
		
		lStrDeptQry = "select b.bank_code,b.bank_name from mst_bank b where b.lang_id="+lILangId;
		
		glogger.info("Query For getBankDtls  is :::: "+ lStrDeptQry);
		Query query = hibSession.createSQLQuery(lStrDeptQry);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[])it.next();
			String lStrBankCode = (tuple[0].toString());
			String lStrBankName = (tuple[1].toString());
			lCmnVO.setCommonId(lStrBankCode);
			lCmnVO.setCommonDesc(lStrBankName);
			lArrCmnVOList.add(lCmnVO);
		}
		
		return lArrCmnVOList;
	}
	
	public List getDivisionName(String lStrDeptId,long lILangId,String lStrLocCode) 
	{		
		glogger.info("in the divison call Dao---");
		//System.out.println("In getDivisionName method of acc master dao======");
		Session hibSession = getSession();
		ArrayList lArrCmnVOList = new ArrayList();
		HashMap lHmpDist =  new HashMap();
		String lStrDeptQry = new String();
		String lStrDistId = "";
		//System.out.println("=========dao 1=============");
		lHmpDist = (HashMap)getDistrictDtls(lStrLocCode, lILangId);
		lStrDistId=(String)lHmpDist.get("districtid");
		//System.out.println("=========dao 2=============");
		lStrDeptQry = "select c.location_code,c.loc_name,c.loc_short_name from cmn_location_mst c where c.type_lookup_id='"+lStrDeptId+"' and c.activate_flag=1 and c.loc_district_id='"+lStrDistId+"' and c.lang_id="+lILangId;
		//System.out.println("=========dao 3 query is============="+lStrDeptQry);
		glogger.info("Query For getDivisionName  is :::: "+ lStrDeptQry);
		Query query = hibSession.createSQLQuery(lStrDeptQry);
		//System.out.println("=========dao 4=============");
		List resList = query.list();
		//System.out.println("=========dao 5=============");
		Iterator it = resList.iterator();
		lCmnVO = new CommonVO();
		lCmnVO.setCommonId("0");
		lCmnVO.setCommonDesc("--Select--");
		lArrCmnVOList.add(lCmnVO);
		while(it.hasNext())
		{
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[])it.next();
			String lStrDivCode = (tuple[0].toString());
			String lStrDivName = (tuple[1].toString());
			lCmnVO.setCommonId(lStrDivCode);
			lCmnVO.setCommonDesc(lStrDivName);
			lArrCmnVOList.add(lCmnVO);
		}
		
		return lArrCmnVOList;
	}
	
	public List getDivisionDtls(String lStrDivCode,long lILangId) 
	{			
		Session hibSession = getSession();
		ArrayList lArrDivVOList = new ArrayList();
				
		String lStrDeptQry = new String();
		
		lStrDeptQry = "select m.loc_short_name,m.loc_addr_1,m.loc_addr_2,m.loc_pin from cmn_location_mst m where m.loc_id='"+lStrDivCode+"' and m.lang_id="+lILangId;
		
		glogger.info("Query For getDivisionDtls  is :::: "+ lStrDeptQry);
		//System.out.println("Query of getDiv dtl is :: "+lStrDeptQry);
		Query query = hibSession.createSQLQuery(lStrDeptQry);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			lDivDtlVO = new LcDivisionDtlsVO();
			Object[] tuple = (Object[])it.next();
		
			
			if (tuple[0] != null ) {
				lDivDtlVO.setDivisionCode(tuple[0].toString());
			}	
			else
			{
				lDivDtlVO.setDivisionCode("-");
			}
			if (tuple[1] != null ) {
				lDivDtlVO.setAdd1(tuple[1].toString());
			}	
			else
			{
				lDivDtlVO.setAdd1("-");
			}
			if (tuple[2] != null ) {
				lDivDtlVO.setAdd2(tuple[2].toString());
			}	
			else
			{
				lDivDtlVO.setAdd2("-");
			}
			if (tuple[3] != null ) {
				lDivDtlVO.setPinCode(tuple[3].toString());
			}	
			else
			{
				lDivDtlVO.setPinCode("-");
			}
				
					
			lArrDivVOList.add(lDivDtlVO);
		}
		
		return lArrDivVOList;
	}
	
	public List getBankBranchDtls(String lStrBankCode) 
	{			
		Session hibSession = getSession();
		ArrayList lArrCmnVOList = new ArrayList();
				
		String lStrDeptQry = new String();
		
		lStrDeptQry = "select b.branch_id,b.branch_name from rlt_bank_branch b where b.bank_code='"+lStrBankCode+"'";
		
		glogger.info("Query For getBankBranchDtls  is :::: "+ lStrDeptQry);
		Query query = hibSession.createSQLQuery(lStrDeptQry);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[])it.next();
			lCmnVO.setCommonId(tuple[0].toString());
			lCmnVO.setCommonDesc(tuple[1].toString());
			lArrCmnVOList.add(lCmnVO);
		}
		
		return lArrCmnVOList;
	}
	
	public String verifyExistingAccout(String lStrDivCode)
	{				
		Session hibSession = getSession();
		
		String lStrChequeBookQuery ="select acc.lc_acc_no from mst_lc_division_account acc where acc.division_code='"+lStrDivCode+"'";
		
	    glogger.info("---------verifyExistingAccout Query is ::"+lStrChequeBookQuery);
		
		Query lSQLQuery = hibSession.createSQLQuery(lStrChequeBookQuery);
		List lResList = lSQLQuery.list();
		Iterator it = lResList.iterator();
		String lStrResult="";
		
		while(it.hasNext())
		{	
			Object tuple = (Object)it.next();		
			lStrResult = tuple.toString();
		}	
		
		return lStrResult;
	}
	
	public void updateLocation(String lStrDivCode,String lStrdistCode,String lStrLocShrtNm,String lStrAdd1,String lStrAdd2,String lStrPin)
	{
		Session hibSession = getSession();
		String lStrDeptId = "";
		lStrDeptId=bundleConst.getString("LC.DIVISION_DEPT_ID");
		String lStrUpdQry ="update cmn_location_mst  set loc_short_name='"+lStrLocShrtNm+"', "+
				" loc_addr_1='"+lStrAdd1+"',loc_addr_2='"+lStrAdd2+"',loc_pin='"+lStrPin+"' where location_code='"+lStrDivCode+"' and loc_district_id ="+lStrdistCode +
				" and department_id="+lStrDeptId;
		
		glogger.info("Update query for cmn_location_mst is :: "+ lStrUpdQry);
		
		Query lSqlQry= hibSession.createSQLQuery(lStrUpdQry);
		int lIRowUpdated = lSqlQry.executeUpdate();
		glogger.info("Updated rows :: "+lIRowUpdated);
		
	}
	
	
	public List getDivAccInfo(ArrayList lArrDivs)
	{
		Session hibSession = getSession();
		StringBuffer lsb = new StringBuffer();
		
		
		LcAccMstVO lObjAccVo =  null;
		
		ArrayList lArrAccList = new ArrayList();
		
		lsb.append("select lkp.lookup_desc, 							");
		lsb.append("	loc.loc_short_name, 							");
		lsb.append("	loc.loc_name,									");
		lsb.append("	div.under_code,									");
		lsb.append("	div.under_code_desc,							");
		lsb.append("	div.lc_acc_no									");	
		lsb.append(" from mst_lc_division_account div,					");
		lsb.append("	cmn_location_mst        loc,					");
		lsb.append("	cmn_lookup_mst          lkp						");
		lsb.append(" where div.division_code = loc.location_code and	");
		lsb.append("	loc.type_lookup_id = lkp.lookup_id				");
		lsb.append(" and div.division_code in(							"); 

		for (int i=0;i<lArrDivs.size();i++)
		{
			lsb.append("'"+lArrDivs.get(i)+"'");
			if(i<(lArrDivs.size()-1))
			{
				lsb.append(",");
			}
		}
		lsb.append(")");
		
		//System.out.println("#################### Acc Master Query is :: "+lsb);
		
		Query sqlQry = hibSession.createSQLQuery(lsb.toString());
		
		List lst=sqlQry.list();
		Iterator it=lst.iterator();
		try
		{
			while(it.hasNext())
			{
				Object[] lObj = (Object[])it.next();
				
				if(lObj != null)
				{
					lObjAccVo =  new LcAccMstVO();
					if(lObj[0] !=null)
					{
						lObjAccVo.setDepartment(lObj[0].toString());
					}
					else
					{
						lObjAccVo.setDepartment("");
					}
					
					if(lObj[1] !=null)
					{
						lObjAccVo.setDivCode(lObj[1].toString());
					}
					else
					{
						lObjAccVo.setDivCode("");
					}
					if(lObj[2] !=null)
					{
						lObjAccVo.setDivName(lObj[2].toString());
					}
					else
					{
						lObjAccVo.setDivName("");
					}
					if(lObj[3] !=null)
					{
						lObjAccVo.setUnderCode(lObj[3].toString());
					}
					else
					{
						lObjAccVo.setUnderCode("");
					}
					if(lObj[4] !=null)
					{
						lObjAccVo.setUnderCodeDesc(lObj[4].toString());
					}
					else
					{
						lObjAccVo.setUnderCodeDesc("");
					}
					if(lObj[5] !=null)
					{
						lObjAccVo.setAccountNo( (Long.parseLong(lObj[5].toString())));
					}
					else
					{
						throw new Exception ("Error :::: Account Number Error..!!!");
						
					}
					
					lArrAccList.add(lObjAccVo);
				}
			}
		}
			catch(Exception ex)
			{
				ex.printStackTrace();
				//System.out.println("Error :: "+ex.toString());
			}
		
		return lArrAccList;
	}
	
	public LcAccMstVO getUpdateAccInfo(Long lLngAccNo)
	{
		Session hibSession=getSession();
		LcAccMstVO lObjAccVo = new LcAccMstVO();
		StringBuffer lsb= new StringBuffer();
		
		lsb.append("select acc.acc_crt_dt, "+
       " lkp.lookup_desc, "+
       " dist.district_name,"+
       " loc.loc_name, "+
       " loc.loc_short_name, "+
       " loc.loc_addr_1, "+
       " loc.loc_addr_2, "+
       " loc.loc_pin, " +
       " acc.under_code,acc.under_code_desc "+
       " from mst_lc_division_account acc, cmn_location_mst loc, cmn_lookup_mst lkp,cmn_district_mst dist "+
       " where loc.location_code = acc.division_code and loc.loc_district_id=dist.district_id and "+
       " acc.lc_acc_no ="+lLngAccNo+" and loc.type_lookup_id=lkp.lookup_id ");
       
		glogger.info("========= Query for getting account information for updation is :: "+lsb);
		
		Query sqlQry = hibSession.createSQLQuery(lsb.toString());
		
		List lst=sqlQry.list();
		Iterator it=lst.iterator();
		try
		{
			while(it.hasNext())
			{
				Object[] lObj=(Object[])it.next();
				if(lObj != null)
				{
					lObjAccVo =  new LcAccMstVO();
					if(lObj[0] !=null)
					{
						lObjAccVo.setAccountDt(lObj[0].toString());
					}
					else
					{
						lObjAccVo.setAccountDt("");
					}
					
					if(lObj[1] !=null)
					{
						lObjAccVo.setDepartment(lObj[1].toString());
					}
					else
					{
						lObjAccVo.setDepartment("");
					}
					if(lObj[2] !=null)
					{
						lObjAccVo.setDisctrict(lObj[2].toString());
					}
					else
					{
						lObjAccVo.setDisctrict("");
					}
					if(lObj[3] !=null)
					{
						lObjAccVo.setDivName(lObj[3].toString());
					}
					else
					{
						lObjAccVo.setDivName("");
					}
					if(lObj[4] !=null)
					{
						lObjAccVo.setDivCode(lObj[4].toString());
					}
					else
					{
						lObjAccVo.setDivCode("");
					}
					if(lObj[5] !=null)
					{
						lObjAccVo.setAddress1(lObj[5].toString());
					}
					else
					{
						lObjAccVo.setAddress1("");
					}
					if(lObj[6] !=null)
					{
						lObjAccVo.setAddress2(lObj[6].toString());
					}
					else
					{
						lObjAccVo.setAddress2("");
					}
					if(lObj[7] !=null)
					{
						lObjAccVo.setPhoneNo(lObj[7].toString());
					}
					else
					{
						lObjAccVo.setPhoneNo("");
					}
					
					if(lObj[8] !=null)
					{
						lObjAccVo.setUnderCode(lObj[8].toString());
					}
					else
					{
						lObjAccVo.setUnderCode("");
					}
					
					if(lObj[9] !=null)
					{
						lObjAccVo.setUnderCodeDesc(lObj[9].toString());
					}
					else
					{
						lObjAccVo.setUnderCodeDesc("");
					}
					
					
					glogger.info("====================== Returning from DAO with Update Account Data");			
				}
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return lObjAccVo;
	}
	
	public List getUnderCodes(String lStrDeptId,long lILangId,String lStrLocCode) 
	{		
		glogger.info("in the divison call Dao---");
		//System.out.println("In getUnderCodes method of acc master dao======");
		Session hibSession = getSession();
		ArrayList lArrCmnVOList = new ArrayList();
		HashMap lHmpDist =  new HashMap();
		String lStrDeptQry = new String();
		String lStrDistId = "";
		
		lHmpDist = (HashMap)getDistrictDtls(lStrLocCode, lILangId);
		lStrDistId=(String)lHmpDist.get("districtid");
		
		lStrDeptQry = "select c.location_code,c.loc_short_name from cmn_location_mst c where c.type_lookup_id='"+lStrDeptId+"' and c.activate_flag=1 and c.loc_district_id='"+lStrDistId+"' and c.lang_id="+lILangId;
		
		glogger.info("Query For getUnderCodes  is :::: "+ lStrDeptQry);
		Query query = hibSession.createSQLQuery(lStrDeptQry);
		//System.out.println("=========dao 4=============");
		List resList = query.list();
		//System.out.println("=========dao 5=============");
		Iterator it = resList.iterator();
		lCmnVO = new CommonVO();
		lCmnVO.setCommonId("0");
		lCmnVO.setCommonDesc("--Select--");
		lArrCmnVOList.add(lCmnVO);
		while(it.hasNext())
		{
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[])it.next();
			String lStrDivCode = (tuple[1].toString());
			String lStrDivName = (tuple[1].toString());
			lCmnVO.setCommonId(lStrDivCode);
			lCmnVO.setCommonDesc(lStrDivName);
			lArrCmnVOList.add(lCmnVO);
		}
		
		return lArrCmnVOList;
	}
	
	public void insertAccData(MstLcDivisionAccount obj,String lStrDivNm,String lStrLocShrtNm,String lStrdistCode,String lStrDivCode,String lStrDeptCode)
	{
		Session hibSession = getSession();
		
		StringBuffer lsb = new StringBuffer();
		
		lsb.append("INSERT INTO MST_LC_DIV_ACC VALUES ( ");
		lsb.append(obj.getLcAccId().toString()+",");
		lsb.append("'"+lStrdistCode+"',");
		lsb.append("'"+lStrDeptCode+"',");
		lsb.append("'"+obj.getDivisionCode().toString()+"',");
		lsb.append("'"+lStrDivNm+"',");
		lsb.append("'"+lStrLocShrtNm+"',");
		lsb.append("'"+obj.getUnderCode()+"',");
		
		lsb.append(" ) ");
	}
}
