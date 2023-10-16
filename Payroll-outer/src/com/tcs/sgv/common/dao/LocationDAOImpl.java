package com.tcs.sgv.common.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.RltLocationDepartment;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class LocationDAOImpl   extends GenericDaoHibernateImpl<CmnLocationMst,Long> implements LocationDAO {

	Log logger = LogFactory.getLog(CmnLocationMst.class);
	
	public LocationDAOImpl(Class<CmnLocationMst> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * This method returns department location by lookup name and language name 
	 * @param lookupName
	 * @param langName
	 * @return
	 */
	public List getDeptLocation(String lookupName,String langName) 
	{			
		List dataList = null;
		try 
		{
			Session hibSession = getSession();
			List resList=hibSession.createSQLQuery(" select loc_id, loc_name, loc_short_name, department_id,parent_loc_id from Cmn_Location_Mst where lang_Id = (select lang_Id from Cmn_Language_Mst where lang_Short_Name='"+langName+"') and type_Lookup_Id = (select lookup_Id from Cmn_Lookup_Mst where lookup_Name='"+lookupName+"') order by loc_name").list();;
			if (resList!=null && resList.size()>0) 
			{
				dataList = new ArrayList();
				Iterator it = resList.iterator();
				while(it.hasNext()) 
				{
					Object[] obj = (Object[])it.next();
					CmnLocationMst vo= new CmnLocationMst();
					
					vo.setLocId(Long.parseLong(obj[0].toString()));
					vo.setLocName((String)obj[1]);
					vo.setLocShortName((String)obj[2]);					
					vo.setDepartmentId(Long.parseLong(obj[3].toString()));
//					vo.setParentLocId((java.math.BigDecimal)obj[4] );
					
					vo.setParentLocId(Long.parseLong(obj[4].toString()));
					dataList.add(vo);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception occured in LOcationDAO .getDeptLocation # \n"+e);
		}
		return dataList;
	}
	
	
	public String getDeptCodeByLocaId(long billNo,String langName) 
	{			
		String ddoCode = null;
		try 
		{
			Session hibSession = getSession();
			Query sqlQuery=hibSession.createSQLQuery("select DEPT_ID from sgvo_dept_mst where LANG_ID='"+langName+"' and DEPT_ID=(select DEPARTMENT_ID from rlt_location_department where LOC_CODE=(select DDO_DEPT_ID from trn_bill_register where BILL_NO="+billNo+"))");										
			List resList=sqlQuery.list();
			if (resList!=null && resList.size()>0) 
			{
				
				Iterator it = resList.iterator();
				while(it.hasNext()) 
				{
					Object tuple = (Object)it.next();
					ddoCode=tuple.toString();
					//System.out.println("-----Department Code is :----"+tuple+"---");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception occured in VoucherDAOImpl.getVouchersListForDet # \n"+e);
		}
		return ddoCode;
	}
	
	public String getDeptNameByLocCode(String LocCode,String LangId) 
	{			
		String LocName = "";
		try 
		{
			Session hibSession = getSession();
			Query sqlQuery=hibSession.createSQLQuery("select Loc_name from cmn_location_mst where location_code = '" + LocCode +"' and LANG_ID="+LangId);										
			List resList=sqlQuery.list();
			if (resList!=null && resList.size()>0) 
			{
				
				Iterator it = resList.iterator();
				while(it.hasNext()) 
				{
					Object tuple = (Object)it.next();
					LocName=tuple.toString();
				
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception occured in LocationDAO IMPL  \n"+e);
		}
		return LocName;
	}
	
	/**
	 * This method reutnrs department code by location id
	 * @param locId location of the department
	 * @param langName locale 
	 * @return String
	 */
	public String getDeptCodeByLocId(String locCode, String langName) 
	{			
		String ddoCode = null;
		try 
		{
			Session hibSession = getSession();
			Query sqlQuery=hibSession.createSQLQuery("select DEPT_ID from sgvo_dept_mst where LANG_ID='"+langName+"' and DEPT_ID=(select DEPARTMENT_ID from rlt_location_department where LOCATION_CODE="+locCode+")");										
			List resList=sqlQuery.list();
			if (resList!=null && resList.size()>0) 
			{
				
				Iterator it = resList.iterator();
				while(it.hasNext()) 
				{
					Object tuple = (Object)it.next();
					ddoCode=tuple.toString();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception occured # \n"+e);
		}
		return ddoCode;
	}
	
	/**
	 * This method returns all location of a department
	 * 
	 * @param deptName Name of the department
	 * @return List
	 */
	public List getLocByDept(String deptName) {
		List dataList = null;
			try {
				Session hibSession = getSession();
				Query query = hibSession.createQuery(" from CmnLocationMst cl where cl.departmentId in (select departmentId from OrgDepartmentMst dp where dp.depName = '"+deptName+"')");
				dataList = query.list();
			}catch(Exception ex){
				ex.printStackTrace();
				logger.error("Exception occured # \n"+ex);
			}
		return dataList;
	}
	
	/**
	 * This method returns child locations by a parent location id
	 * 
	 * @param parentLocId id of the parent location
	 * @return List
	 */
	public List getChdLocByPrntLocId(Long parentLocId){
		List dataList = null;
		try {
			Session hibSession = getSession();
			Query query = hibSession.createQuery(" from CmnLocationMst cl where cl.parentLocId = " + parentLocId);
			List resList = query.list();
			if(resList!=null && resList.size()>0) {
				Iterator it = resList.iterator();
				dataList = new ArrayList();
				while(it.hasNext()){
					CmnLocationMst cmnLocVO = (CmnLocationMst)it.next();
					dataList.add(new Long(cmnLocVO.getLocId()));
				}
			}
		} catch(Exception ex){
			logger.error("Exception occured #\n"+ex);
		}
		return dataList;
	}
	
	/**
     * Method getLocByDept returns Location Id from RLT_LOCATION_DEPARTMENT for passed department, lang_id, db_id
     *
     * @param lStrDeptCode Department Code
     * @param lStrLangId Language ID
     * @param lLngDbId DataBase ID
     *
     * @return ArrayList Containing Location ID
     */
		
	public List getLocByDept(String lStrDeptCode, Long lLngLangId, Long lLngDbId)
	{
		List lLstResult = null;
		List lLstData = null;
		try
		{
			Session hibSession = getSession();
			Query query = hibSession.createQuery(" from RltLocationDepartment rld where rld.langId = " +lLngLangId 
									+" and dbId = " +lLngDbId +" and departmentId = '" +lStrDeptCode +"'");
			logger.info("Query for getLocByDept : " +query);
			
			lLstResult = query.list();
			logger.info(" ----- " + lLstResult.size());
			if(lLstResult!=null && lLstResult.size()>0) 
			{
				Iterator it = lLstResult.iterator();
				lLstData = new ArrayList();
				while(it.hasNext())
				{
					RltLocationDepartment lObjRltLocDept = (RltLocationDepartment)it.next();
					lLstData.add(lObjRltLocDept);
				}
			}
			
		}
		catch(Exception e)
		{
			logger.error("Exception in getLocByDept : " +e);
		}
		return lLstData;
	}
	
	public CmnDistrictMst getDistCodeFrmLocId(String lStrLocCode, Long lLngLangId)
	{
		//System.out.println("Inside getDistCodeFrmLocId");
		List lLstResult = null;
		List lLstData = null;
		CmnDistrictMst lObjCmnDistMst = null;
		try
		{
			Session hibSession = getSession();
			Query query = hibSession.createQuery(" from CmnDistrictMst cdm where cdm.districtId in (select clm.locDistrictId from CmnLocationMst clm where clm.locationCode='" +lStrLocCode +"')"); 
//			Query query = hibSession.createSQLQuery("select d.* from cmn_district_mst d where d.district_id in (select l.loc_district_id from cmn_location_mst l where l.loc_id=" +lLngLocId +" and l.lang_id=" +lLngLangId +")");
			//System.out.println("Query for getDistCodeFrmLocId : " +query);
			
			lLstResult = query.list();
			//System.out.println("Size of Listttt ----- " + lLstResult.size());
			if(lLstResult!=null && lLstResult.size()>0) 
			{
				Iterator it = lLstResult.iterator();
				lLstData = new ArrayList();
				while(it.hasNext())
				{
					lObjCmnDistMst = (CmnDistrictMst)it.next();
				}
			}
			
		}
		catch(Exception e)
		{
			logger.error("Exception in getLocByDept : " +e);
		}
		return lObjCmnDistMst;
	}
}
