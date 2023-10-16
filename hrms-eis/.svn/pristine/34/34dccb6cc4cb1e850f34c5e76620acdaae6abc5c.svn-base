package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.SearcheEmployeeCriteria;
import com.tcs.sgv.ess.valueobject.EmployeeSearchCriteria;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;


public  class PayrollEmpSearchDAOImpl extends GenericDaoHibernateImpl<OrgEmpMst,Long> 
{
	private static final Logger logger = Logger.getLogger(PayrollEmpSearchDAOImpl.class.getName());

	public PayrollEmpSearchDAOImpl(Class<OrgEmpMst> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}


	public List searchEmployeeByAll(String empFname,String empId)
	{

		List<OrgEmpMst> searchEmpList=null;
		Session hibSession = getSession();

		try 												
		{			
			Criteria criteria =hibSession.createCriteria(OrgEmpMst.class); 
			if (empFname != null) {
				Criterion criterion=Restrictions.ilike("empFname",empFname+"%");
				logger.info(criterion);
				criteria.add(criterion);
				logger.info("empFname"+empFname);

			}

			if (empId != null) {

				if(empId.trim().length()>0){
					Long tempempId = Long.parseLong(empId);
					Criterion criterion=Restrictions.eq("empId",tempempId);
					logger.info(criterion);
					criteria.add(criterion);
					logger.info("empId"+empId);
				}
			}

			searchEmpList = criteria.list();

			logger.info("searchEmpList::"+searchEmpList);							
			logger.info("searchEmpList Size::"+searchEmpList.size());
			logger.info("searchEmployeeByAll DAO end...................");

		}		
		catch(Exception e)
		{
			logger.error("ERROR : ",e);
		}



		return searchEmpList;

	}

	/**
	 * <pre> This method is used to retrieve employee details for a given user.It returns OrgEmpMst object 
	 * for a specified User and langId.</pre>
	 * @param  OrgUserMst User,Long langId
	 * @throws Exception
	 * @return OrgEmpMst
	 */	



	public OrgEmpMst getEngGujEmployee (OrgEmpMst orgEmpMst,Long langId)
	{
		logger.info("--getEngGujEmployee method started--");
		List<OrgEmpMst> viewEmployeeList = new ArrayList();
		Session hibSession = getSession();
		Criteria criteria = hibSession.createCriteria(OrgEmpMst.class);
		// Applying restriction on user and language
		criteria.add(Restrictions.eq("orgUserMst",orgEmpMst.getOrgUserMst()));
		criteria.add(Restrictions.eq("cmnLanguageMst.langId",langId));
		viewEmployeeList = criteria.list();
		logger.info("----list size---"+viewEmployeeList.size());
		OrgEmpMst objEmpMst = (OrgEmpMst)viewEmployeeList.get(0);
		return objEmpMst;
	}


	/**
	 * This method is returns List containing OrgEmpMst object for a specified search criteria by user.
	 * @param  OrgEmpMst orgEmpMst,List locSrchList,CmnLanguageMst 
	 * cmnLanguageMst,OrgDesignationMst orgDesignationMst
	 * @return ArrayList
	 */	


	public List SearchEmployeeUpdate (EmployeeSearchCriteria empSrchCrit,int startIndex,int pagesize,boolean flag) throws Exception
	{
		List<OrgEmpMst> searchEmpList=new ArrayList();
		try 
		{
			logger.info("--SearchEmployeeUpdate DAO started--");

			Session hibSession = getSession();
			Criteria criteria = hibSession.createCriteria(OrgEmpMst.class);
			criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
			if(flag==true)
			{
				criteria.setFirstResult(startIndex);
				criteria.setMaxResults(pagesize);
			}
			Date currentDate = DBUtility.getCurrentDateFromDB();
			
			
			
			logger.info(" Searchemployeeupdate srchnamegpftext "+   empSrchCrit.getSrchnameGPFtext());
			
			if(!"".equals(empSrchCrit.getSrchnameGPFtext()) && empSrchCrit.getSrchnameGPFtext() !=null)
			{
				
				logger.info("First If Condition");
				
				String srchNameText = empSrchCrit.getSrchnameGPFtext().trim();
				if(srchNameText.indexOf(' ') != -1)
				{
					logger.info("space between search string");
					
					
					String[] srchNameArr = srchNameText.split(" ");
					String fnSrchNmTxt="";
					String middleSrchNmTxt="";
					String lnSrchNmTxt ="";
					
					if(srchNameArr.length==3)
					{
					  fnSrchNmTxt = srchNameArr[0];
					
					  middleSrchNmTxt = srchNameArr[1];
					  lnSrchNmTxt = srchNameArr[2];
					}
					else if(srchNameArr.length==2)
					{
						
						 fnSrchNmTxt = srchNameArr[0];
						
						 middleSrchNmTxt = srchNameArr[1];
					}
					else 
					{
						 fnSrchNmTxt = srchNameArr[0];
					}
					
					if(fnSrchNmTxt!=null && !"".equalsIgnoreCase(fnSrchNmTxt))
					{
						criteria.add(Restrictions.ilike("empFname",fnSrchNmTxt.trim()+"%"));
					}
					if(middleSrchNmTxt!=null && !"".equalsIgnoreCase(middleSrchNmTxt))
					{
						criteria.add(Restrictions.ilike("empMname",middleSrchNmTxt.trim()+"%"));
					}
					if(lnSrchNmTxt!=null && !"".equalsIgnoreCase(lnSrchNmTxt))
					{
						criteria.add(Restrictions.ilike("empLname",lnSrchNmTxt.trim()+"%"));
					}
					/*
					
					Criterion fnLnChk = Restrictions.and(Restrictions.ilike("empFname",fnSrchNmTxt.trim()+"%"),
							Restrictions.ilike("empLname",lnSrchNmTxt.trim()+"%")); 
					Criterion lnFnChk = Restrictions.and(Restrictions.ilike("empFname",lnSrchNmTxt.trim()+"%"),
							Restrictions.ilike("empLname",fnSrchNmTxt.trim()+"%"));
					criteria.add(Restrictions.or(fnLnChk,lnFnChk));*/
				}
				else
				{
					logger.info("employee search by PRE SRCH CRITERIA");
					Criterion nameRestrCritr = Restrictions.or(Restrictions.ilike("empFname",srchNameText.trim()+"%"),
							Restrictions.ilike("empLname",srchNameText.trim()+"%"));
					Criterion gpfRestrCritr = Restrictions.ilike("empGPFnumber",srchNameText.trim(),MatchMode.ANYWHERE);
					criteria.add(Restrictions.or(nameRestrCritr,gpfRestrCritr));
				}
			}
			else
			{
				
				logger.info("First Else Condition");
				
				
				//				applying restriction on firstname
				OrgEmpMst orgEmpMst = empSrchCrit.getOrgEmpMst();
				logger.info("Employee First Name" + orgEmpMst.getEmpFname());
				
				if(orgEmpMst.getEmpFname()!=null)
				{
					criteria.add(Restrictions.like("empFname",orgEmpMst.getEmpFname().trim()+"%").ignoreCase());
				}
				//applying restriction on lastname
				if(orgEmpMst.getEmpLname()!=null)
				{
					criteria.add(Restrictions.like("empLname",orgEmpMst.getEmpLname().trim()+"%").ignoreCase());
				}
				//applying restriction on birth date
				
				logger.info("First Else Condition dateofbirth" + orgEmpMst.getEmpDob());
				
				if(orgEmpMst.getEmpDob()!=null)
				{
					
					logger.info("First If Condition in First Else Conditon");
					
					
					Date currDateStart = orgEmpMst.getEmpDob();
					Date currDateEnd = currDateStart;
					
					logger.info("currDateEnd*****"+ currDateEnd);
					
					currDateStart.setHours(00);
					currDateStart.setMinutes(00);
					currDateStart.setSeconds(00);
					currDateEnd.setHours(23);
					currDateEnd.setMinutes(59);
					currDateEnd.setSeconds(59);
					
					logger.info("currDateEnd1111*****"+ currDateEnd);
					
					criteria.add(Restrictions.between("empDob",currDateStart,currDateEnd));
				}
			}

			//creating alias for criteria query to fetch user-->userpostrlts-->postmst-->postdetails
			criteria.createAlias("orgUserMst","user");
			criteria.createAlias("user.orgUserpostRlts", "userPosts");
			criteria.add(Restrictions.eq("userPosts.activateFlag", 1L));
			criteria.createAlias("userPosts.orgPostMstByPostId", "postMst");
			criteria.setFetchMode("postMst", FetchMode.JOIN);
			criteria.createAlias("postMst.orgPostDetailsRlt", "postDetailsRlt");


			//applying location restriction
			
			logger.info("cmnlocationmst***" + empSrchCrit.getLocSrchList().size());
			
			if(empSrchCrit.getLocSrchList().size()>0)
			{
				logger.info("applying location criteria as object is not null");

				criteria.add(Restrictions.in("postDetailsRlt.cmnLocationMst",empSrchCrit.getLocSrchList()));
			}
			
			logger.info("cmnbranchmst*********" + empSrchCrit.getCmnBranchMst());
			
			if(empSrchCrit.getCmnBranchMst()!=null)
			{
				logger.info("applying brach criteria as object is not null");
				logger.info("empSrchCrit.getCmnBranchMst()"+empSrchCrit.getCmnBranchMst().getBranchId());
				logger.info("empSrchCrit.getCmnBranchMst()"+empSrchCrit.getCmnBranchMst());

				criteria.add(Restrictions.eq("postDetailsRlt.cmnBranchMst",empSrchCrit.getCmnBranchMst()));
			}
			/*applying restriction on post level id.The following restriction is filters data as per the 
			 * post level of employee i.e user can view employees who hold post higher,lower or equal than 
			 * the current loggedIn user.  
			 */
			
			
			
			logger.info("orgpostmst**********"+empSrchCrit.getOrgPostMst());
			logger.info("orgpostmst**********"+empSrchCrit.getPostLevelSortParam());
			logger.info("orgpostmst**********"+empSrchCrit.getOrgPostMst());
			
			OrgPostMst orgPostMst = empSrchCrit.getOrgPostMst();
			if(("GT_LOGIN").equals(empSrchCrit.getPostLevelSortParam()))
			{
				logger.info("for post----greater than case");
				criteria.add(Restrictions.gt("postMst.postLevelId",orgPostMst.getPostLevelId()));
			}
			if(("GTE_LOGIN").equals(empSrchCrit.getPostLevelSortParam()))
			{
				logger.info("for post----greater than and equal case");
				criteria.add(Restrictions.ge("postMst.postLevelId",orgPostMst.getPostLevelId()));
			}
			if(("LT_LOGIN").equals(empSrchCrit.getPostLevelSortParam()))
			{
				logger.info("for post----lesser than case");
				criteria.add(Restrictions.lt("postMst.postLevelId",orgPostMst.getPostLevelId()));
			}
			if(("LTE_LOGIN").equals(empSrchCrit.getPostLevelSortParam()))
			{
				logger.info("for post----lesser than and equal case");
				criteria.add(Restrictions.le("postMst.postLevelId",orgPostMst.getPostLevelId()));
			}
			if(("EQ_LOGIN").equals(empSrchCrit.getPostLevelSortParam()))
			{
				logger.info("for post----equal case");
				criteria.add(Restrictions.eq("postMst.postLevelId",orgPostMst.getPostLevelId()));
			}
			
			
			
			
			logger.info("orgdesignationmst**********"+empSrchCrit.getOrgDesignationMst());
			logger.info("orgdesignationmst**********"+empSrchCrit.getDsgnSpecCodesList());
			
			//applying designation restriction
			OrgDesignationMst orgDesignationMst = empSrchCrit.getOrgDesignationMst();
			List empDsgnSpecCodes = empSrchCrit.getDsgnSpecCodesList();
			if(orgDesignationMst!=null)
			{
				logger.info("orgDesignationMst is not null");
				criteria.createAlias("postMst.cmnLookupMst","lookup");
				criteria.add(Restrictions.like("lookup.lookupName","Primary_Post").ignoreCase());
				criteria.add(Restrictions.like("postDetailsRlt.orgDesignationMst",orgDesignationMst));
			}
			else if(empDsgnSpecCodes!=null && !empDsgnSpecCodes.isEmpty())
			{
				logger.info("empDsgnSpecCodes list is not null");
				criteria.createAlias("postMst.cmnLookupMst","lookup");
				criteria.add(Restrictions.like("lookup.lookupName","Primary_Post").ignoreCase());
				criteria.createAlias("postDetailsRlt.orgDesignationMst","objDsgn");
				criteria.add(Restrictions.in("objDsgn.dsgnCode",empDsgnSpecCodes));
			}

			
			logger.info("gpf number*************" + empSrchCrit.getSrchnameGPFtext());
			//applying language restriction for Bilingual search
			if(!"".equals(empSrchCrit.getSrchnameGPFtext()) && empSrchCrit.getSrchnameGPFtext() !=null)
			{
				criteria.add(Restrictions.like("cmnLanguageMst.langId",empSrchCrit.getCmnLanguageMst().getLangId()));
			}
			else
			{
				OrgEmpMst orgEmpMst1 = empSrchCrit.getOrgEmpMst();
				logger.info("now found what is empFname"+orgEmpMst1.getEmpFname().toString().trim());
				logger.info("now found what is empLname"+orgEmpMst1.getEmpLname().toString().trim());
				
				if((orgEmpMst1.getEmpFname().toString().trim().equals("%") && orgEmpMst1.getEmpLname().toString().trim().equals("")) ||(orgEmpMst1.getEmpFname().toString().trim().equals("") && orgEmpMst1.getEmpLname().toString().trim().equals("")))
				{
					
					logger.info("inside empFname and empLname");
					
					criteria.add(Restrictions.like("cmnLanguageMst.langId",empSrchCrit.getCmnLanguageMst().getLangId()));
				}

			}
			
			logger.info("order empfname" + Order.asc("empFname"));
			logger.info("order emplname" + Order.asc("empLname"));
			
			//ordering results by firstname and lastname
			criteria.addOrder(Order.asc("empFname"));
			criteria.addOrder(Order.asc("empLname"));
			//setting query cache true for speeding up search 
			criteria.setCacheable(true);
			logger.info("**************emp search query************");
			searchEmpList = criteria.list();
			logger.info("**************emp search query************");		
			logger.info("employee list size in SearchEmployeeUpdate method ----- "+searchEmpList.size());
		} 
		catch (Exception e) 
		{
			logger.info("Error in EmpDaoImpl-->EmplyeeSearchUpdate Method"+e);
			throw e;
		}
		return searchEmpList;
	}



	public List<OrgEmpMst> getAllEmployeesByname(String empFname)
	{
		//For Get All Employee Using Emp ID	

		logger.info("getAllEmployeesByname DAO called");
		List<OrgEmpMst> empList=null;

		try 
		{

			Session hibSession = getSession();
			logger.info("executing HQL===========");
			Criteria objCrt = hibSession.createCriteria(OrgEmpMst.class);

			objCrt.add( Restrictions.like("empFname", "a%"));
			empList=objCrt.list();



		}
		catch(Exception e)
		{
			logger.error("ERROR : ",e);
		}
		return empList;
	}


	public List<OrgEmpMst> getAllEmployees()
	{
		//For Get All Employees

		logger.info("getAllEmployees DAO called");
		List<OrgEmpMst> employeesList=null;

		try 
		{
			Session hibSession = getSession();
			logger.info("executing HQL===========");
			Criteria objCrt = hibSession.createCriteria(OrgEmpMst.class);				
			employeesList=objCrt.list();


		}
		catch(Exception e)
		{
			logger.error("ERROR : ",e);
		}
		return employeesList;
	}



	public List<OrgEmpMst> getAllEmployees(long empId)
	{
		//For Get All Employee Using Emp ID	

		logger.info("getAllEmployees(long empId) DAO called");
		List<OrgEmpMst> empIdList=null;

		try 
		{
			Session hibSession = getSession();
			logger.info("executing HQL===========");
			Criteria objCrt = hibSession.createCriteria(OrgEmpMst.class);		
			objCrt.add(Restrictions.eq("empId", empId));
			//objCrt.addOrder(Order.asc("orderNo"));
			empIdList=objCrt.list();


		}
		catch(Exception e)
		{
			logger.error("ERROR : ",e);
		}
		return empIdList;
	}
	public List<OrgEmpMst> getAllEmployees(long orgUserMst, long cmnLanguageMst)
	{
		//For Get All Employees Using User Id and Lang Id
		logger.info("getAllEmployees(long orgUserMst, long cmnLanguageMst) DAO called");
		List<OrgEmpMst> empIdsList=null;

		try 
		{
			Session hibSession = getSession();
			logger.info("executing HQL===========");

			CmnLanguageMst objOrgEmpMst = new CmnLanguageMst();
			objOrgEmpMst.setLangId(cmnLanguageMst);

			OrgUserMst objOrgEmpMst1 = new OrgUserMst();
			objOrgEmpMst1.setUserId(orgUserMst);


			Criteria objCrt = hibSession.createCriteria(OrgEmpMst.class);		
			objCrt.add(Restrictions.eq("orgUserMst", objOrgEmpMst1));
			objCrt.add(Restrictions.eq("cmnLanguageMst", objOrgEmpMst));

			empIdsList=objCrt.list();	    				
		}

		catch(Exception e)
		{
			logger.error("ERROR : ",e);
		}
		return empIdsList;
	}




	public OrgEmpMst getEmployee(long userId, long langId)
	{

		logger.info("getAllEmployees(long orgUserMst, long cmnLanguageMst) DAO called");

		OrgEmpMst objOrgEmpMst=null;
		try 
		{
			Session hibSession = getSession();
			logger.info("executing HQL===========");

			CmnLanguageMst objOrgLangMst = new CmnLanguageMst();
			objOrgLangMst.setLangId(langId);

			OrgUserMst objOrgUserMst = new OrgUserMst();
			objOrgUserMst.setUserId(userId);


			Criteria objCrt = hibSession.createCriteria(OrgEmpMst.class);		
			objCrt.add(Restrictions.eq("orgUserMst", objOrgUserMst));
			objCrt.add(Restrictions.eq("cmnLanguageMst", objOrgLangMst));

			objOrgEmpMst=(OrgEmpMst)objCrt.uniqueResult();

		}


		catch(Exception e)
		{
			logger.error("ERROR : ",e);
		}
		return objOrgEmpMst;
	}



	public 	OrgEmpMst getEmployeeVO(long empId)
	{
		return (OrgEmpMst)read(Long.valueOf( empId ));
	}

    public OrgPostDetailsRlt  getPostDetailsRltFromEmployee(OrgEmpMst objOrgEmpMst)
    {
        OrgPostDetailsRlt   objOrgPostDetailsRlt=null;
        try
        {
            if(objOrgEmpMst!= null)
            {
            Long empId = objOrgEmpMst.getEmpId();
             if(objOrgEmpMst.getCmnLanguageMst()!= null)
             {
            Long langId = objOrgEmpMst.getCmnLanguageMst().getLangId();
            
            objOrgPostDetailsRlt = this.getPostDetailsRltFromEmployeeIdAndLangId(empId, langId);
             }
            }
            
        }catch(Exception e)
        {
            logger.error("Error in getPostDetailsRltFromEmployee",e);
        }
        
        return objOrgPostDetailsRlt;
    }
	
    public OrgPostDetailsRlt  getPostDetailsRltFromEmployeeIdAndLangId(Long empId,Long langId)
    {
        OrgPostDetailsRlt objOrgPostDetailsRlt=null;
        
        try
        {
            Session hibSession = getSession();
            Criteria objCrtEmp = hibSession.createCriteria(OrgEmpMst.class);
            CmnLanguageMst objCmnLanguageMst = new CmnLanguageMst();
            objCmnLanguageMst.setLangId(langId);
            objCrtEmp.add(Restrictions.eq("empId",empId));
            objCrtEmp.add(Restrictions.eq("cmnLanguageMst",objCmnLanguageMst));
            
            OrgEmpMst orgEmpMst =(OrgEmpMst) objCrtEmp.uniqueResult();
            
            if(orgEmpMst!= null)
            {
                if(orgEmpMst.getOrgUserMst()!= null)
                {
                objOrgPostDetailsRlt = this.getPostDetailsRltFromUserIdAndLangId(orgEmpMst.getOrgUserMst().getUserId(), langId);
                }
            }
            
        }catch(Exception e)
        {
            logger.error("Error In getPostDetailsRltFromEmployeeIdAndLangId",e);
        }
        
        return objOrgPostDetailsRlt;
    }
	

    public OrgPostDetailsRlt getPostDetailsRltFromUserIdAndLangId(Long userId,Long langId)
    {
        OrgPostDetailsRlt   objOrgPostDetailsRlt=null;
        try
        {
            Session hibSession = getSession();
            Criteria objCrt = hibSession.createCriteria(OrgUserpostRlt.class);
            objCrt.add(Restrictions.eq("orgUserMst.userId",userId));
            List<OrgUserpostRlt> lstOrgUserpostRlt = objCrt.list();
		
            logger.info("lstOrgUserpostRlt size is  "+lstOrgUserpostRlt.size());	

            Iterator lstOrgUserpostRltIterator = lstOrgUserpostRlt.iterator();
            while(lstOrgUserpostRltIterator.hasNext())
            {
                OrgUserpostRlt objOrgUserpostRlt =(OrgUserpostRlt) lstOrgUserpostRltIterator.next();
                
               OrgPostMst objOrgPostMst =objOrgUserpostRlt.getOrgPostMstByPostId();
                
                if(objOrgPostMst!= null)
                {
                    Set postDetailsSet= objOrgPostMst.getOrgPostDetailsRlt();
                    Iterator postDetailsSetIterator = postDetailsSet.iterator();
                    while(postDetailsSetIterator.hasNext())
                    {
                        OrgPostDetailsRlt objOrgPostDetails =(OrgPostDetailsRlt) postDetailsSetIterator.next();
                        if(objOrgPostDetails!=null && objOrgPostDetails.getCmnLanguageMst().getLangId()==langId)
                        {
                            objOrgPostDetailsRlt=objOrgPostDetails;
                           
                        }
                    }
                }
            }
        }catch(Exception e)
        {
            logger.error("Error in getPostDetailsRltFromUserIdAndLangId",e);
        }
        
        return objOrgPostDetailsRlt;
    }
    
    public List getEmpListFromHreisempMst(long empId)
	{
		logger.info("in getEmpListFromHreisempMst...........");
		List list = new ArrayList();
		
		long employeeid =0;
		
		Session hibSession = getSession();
		
		StringBuffer query = new StringBuffer();
		
		query.append(" select  rlt.empId, rlt.sevarthEmpCode from HrEisEmpMst rlt");  
		query.append(" where rlt.orgEmpMst.empId ="+empId+" "); 
		
		
			Query sqlQuery = hibSession.createQuery(query.toString());
			
	        logger.info("==> getEmpIdFromHreisempMst query :: "+sqlQuery);
	      
	        list= sqlQuery.list();	 
	        if(list != null && list.size()>0)
	        {
	          return list;
	        }
		   	    	 			 			 		
	        else
	        {
	        	return null;
	        }
	}
    
}
