package com.tcs.sgv.eis.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ReportQueryDAOImpl extends GenericDaoHibernateImpl<DDODetailsVO, Integer> implements ReportQueryDAO {

	private static final Logger glogger=Logger.getLogger(ReportQueryDAOImpl.class);

	public ReportQueryDAOImpl(Class<DDODetailsVO> type,SessionFactory sessionFactory) 
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public ReportQueryDAOImpl() 
	{
		super(DDODetailsVO.class);
	}
	public ArrayList getLocationList(String lStrLangId, String lstrLocId) 
    {
		glogger.info("------------------------------------Inside location  Type query--------------------");
		glogger.info("lang id is "+lStrLangId);
		glogger.info("location id is "+lstrLocId);
		ArrayList arrOfficeName = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String locId=null;
        String lName=null;
        try
        {
            lCon = DBConnection.getConnection(  );
            StringBuffer lsb = new StringBuffer(  );
            lsb = new StringBuffer("SELECT loc_id , Loc_name FROM cmn_location_mst WHERE lang_id=1");
            lStmt = lCon.prepareStatement( lsb.toString() );
            lRs = lStmt.executeQuery();
            while(lRs.next())
            {
            	ComboValuesVO vo= new ComboValuesVO(  );
            	locId = lRs.getString("loc_id");
                lName = lRs.getString("Loc_name");
                glogger.info("Vlaue of loc Name" +lName);
                vo.setId(locId);
                vo.setDesc(lName);
                arrOfficeName.add(vo);
            }
        }
        catch( SQLException se )
        {
            se.printStackTrace();
        	glogger.error( "SQLException::"+se.getMessage(), se );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        return arrOfficeName;
    }
	
	public ArrayList getRecursiveoption(String lStrLangId, String lstrLocId) 
    {
		glogger.info("------------------------------------Inside option type  Type query--------------------");

		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the locId is "+lstrLocId);
		
		ArrayList arrOfficeName = new ArrayList();
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        String locId=null;
        String lName=null;
        try
        {
            lCon = DBConnection.getConnection(  );
            StringBuffer lsb = new StringBuffer(  );
            lsb = new StringBuffer("SELECT lookup_id,lookup_name " +
            		"FROM cmn_lookup_mst " +
            		"where parent_lookup_id in(select lookup_id from cmn_lookup_mst where lookup_name = 'yes/no')");
            lStmt = lCon.prepareStatement( lsb.toString() );
            lRs = lStmt.executeQuery();
            while(lRs.next())
            {
            	ComboValuesVO vo= new ComboValuesVO(  );
            	locId = lRs.getString("lookup_name");
                lName = lRs.getString("lookup_name");
                glogger.info("Vlaue" +lName);
                vo.setId(locId);
                vo.setDesc(lName);
                arrOfficeName.add(vo);
            }
        }
        catch( SQLException se )
        {
            se.printStackTrace();
        	glogger.error( "SQLException::"+se.getMessage(), se );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        return arrOfficeName;
    }
		
public ArrayList getLocationDtlRpt(String lLocation,String lRecursiveOption)
{
		glogger.info("Location id is "+lLocation+" & type is "+lRecursiveOption);
			glogger.info("------------------------------------Inside Location main  Type query--------------------");
			ArrayList arrLocationName = new ArrayList();
		try
		{
				String lsb = null;
				Session hibSession = getSession();
				if(!lRecursiveOption.equals("Yes"))
				{
					glogger.info(" In without recor...");	
					lsb = "SELECT lom.loc_id,lom.loc_name, lom.loc_short_name, dep.dep_name " + 
							" FROM cmn_location_mst lom,org_department_mst dep "+
								" where lom.department_id = dep.department_id ";
				 		//System.out.println(lsb);
			
				 		if(!lLocation.equals("-1"))
				 		{
				 			glogger.info("LLocation selection"+lLocation);
				 			lsb = lsb + " and lom.loc_id= "+lLocation;
				 		}
				 		else
				 		{
				 			glogger.info("without location");
				 		}
				} 
				else
				{
					glogger.info("in recur///..");
					ArrayList arrLocation = new ArrayList();
					StringBuffer arrLocation1 = new StringBuffer();
					glogger.info("-----in the recursive selection");
					glogger.info("Sort By"+lRecursiveOption);
					List locationList  = new ArrayList();
					arrLocation.add("5001");
					ArrayList arrOuter = new ArrayList();
					arrOuter.add("5001");
					boolean flag = false;
					arrLocation1.append(lLocation);
					arrOuter = getAllLocations(lLocation);
					while (!flag) 
					{
						ArrayList arrTemp = new ArrayList();	
						for(int i=0 ; i < arrOuter.size() ;++i)
							{
									glogger.info("------- First Level size-----");
									glogger.info("before getting element");
									Object loc = arrOuter.get(i);
									glogger.info("object is" + loc);
									String loc_id_S = loc.toString();
									glogger.info("String is "+ loc_id_S);
									ArrayList arrTemp1 = getAllLocations(loc_id_S);
									for(int j=0;j<arrTemp1.size();j++)arrTemp.add(arrTemp1.get(j));
									arrLocation.add(loc);
									arrLocation1.append(", "+loc);
							}
						arrOuter = arrTemp;
						if(arrOuter.isEmpty()) {
							flag = true;
						}
					}	
					for(int i=0 ; i < arrLocation.size() ;i++)
						glogger.info(arrLocation.get(i).toString());
				 lsb = "SELECT lom.loc_id,lom.loc_name, lom.loc_short_name, dep.dep_name " + 
								" FROM cmn_location_mst lom,org_department_mst dep "+
									" where lom.department_id = dep.department_id  and lom.loc_id in ("+arrLocation1+")";
					glogger.info(lsb);
				}
				Query sqlQuery=hibSession.createSQLQuery(lsb);
				List resList=sqlQuery.list();
				glogger.info("size of List-->"+resList.size());
				if (resList!=null && resList.size()>0) 
				{
					
					Iterator it = resList.iterator();
					while(it.hasNext()) 
					{
						Object[] tuple = (Object[])it.next();
						ArrayList arrInner =new ArrayList();
						arrInner.add(tuple[1]);
						arrInner.add(tuple[2]);
						arrInner.add(tuple[3]);
						arrInner.add(tuple[0]);
						glogger.info("tuples :-" + tuple[1] +" "+ tuple[2]+" "+tuple[3]+" " +tuple[0]);
						arrLocationName.add(arrInner);
						
					}
				} 
			}
	        catch(Exception e )
	        {
	      
	        	glogger.error( "Exception::"+e.getMessage(), e );
	            
	        }
	        return arrLocationName;
		}

		public ArrayList getAllLocations(String locId)
		{
			glogger.info("in the get objection method");
			Session hibSession = getSession();
			ArrayList arrInner = new ArrayList();
			String lsb = "select loc_id from cmn_location_mst where loc_id in (SELECT loc_id FROM cmn_location_mst c where parent_loc_id ='"+ locId+"' )";
			Query sqlQuery=hibSession.createSQLQuery(lsb);
			List resList=sqlQuery.list();
			glogger.info("Query executed");
			if(resList!= null && resList.size()>0 )
			{
				glogger.info("In the result set");	
				Iterator it = resList.iterator();
				try
				{
				while(it.hasNext())
					{
						glogger.info(" in the iterator");
						Object tuple = it.next();
						glogger.info("After tuple" + tuple.getClass());
						glogger.info("After adding in arrInner");
						glogger.info("After adding in arrLocation");
						glogger.info("loc_id = " +tuple.toString());
						arrInner.add(tuple.toString());
			
					}
				}catch(Exception e){e.printStackTrace();}
			}
			return arrInner;
		}
		
		public ArrayList getEmplDtlRpt(String loffice)
		{
		
			glogger.info("------------------------------------Inside Employee dtl  Type query--------------------");
			ArrayList arrOfficeName = new ArrayList();
			try
			{	
				Session hibSession = getSession();

				String lsb = "select em.emp_id , em.emp_fname,em.emp_mname,em.emp_lname, pom.post_name,dsm.dsgn_name,em.emp_dob, em.emp_doj,ecm.contact_number, eam.emp_addr_1, eam.emp_addr_2 "+
				" from org_emp_mst em left join org_empcontact_mst ecm on em.emp_id = ecm.emp_id "+
				" left join org_empaddress_mst eam on  em.emp_id = eam.emp_id, "+
				" org_userpost_rlt upr, org_post_mst pom, org_designation_mst dsm "+
				" where em.user_id = upr.user_id "+
				" and upr.post_id = pom.post_id "+
				" and dsm.dsgn_id = pom.dsgn_id " +
				" and upr.activate_flag = 1 " +
				" and pom.loc_id = '"+loffice+"'";
				
				
				Query sqlQuery=hibSession.createSQLQuery(lsb);
				List resList=sqlQuery.list();
				glogger.info("size of List-->"+resList.size());
				if (resList!=null && resList.size()>0) 
				{
					
					Iterator it = resList.iterator();
					while(it.hasNext()) 
					{
						Object[] tuple = (Object[])it.next();
						ArrayList arrInner =new ArrayList();

						arrInner.add(tuple[0]);
						arrInner.add(tuple[1] + " " + tuple[2] + " " + tuple[3]);
						arrInner.add(tuple[4]);
						arrInner.add(tuple[5]);
						if(tuple[6]!=null)
						{
							String actionDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[6].toString()));
							arrInner.add(actionDate);
						}
						else
						{
							arrInner.add("-");
						}
						
						if(tuple[7]!=null)
						{
							String actionDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[7].toString()));
							arrInner.add(actionDate);
						}
						else
						{
							arrInner.add("-");
						}
						arrInner.add(tuple[8]);
						if(tuple[9]!=null)
						{
							
							arrInner.add(tuple[9]);
						}
						else
						{
							arrInner.add("-");
						}
				
						glogger.info("tuple is "+ tuple[0] +" "+tuple[1]+" "+tuple[2]+" "+tuple[3]+" "+tuple[4] +" "+tuple[5]+" "+tuple[6]+" "+tuple[7]+" "+tuple[8]+" "+tuple[9]);
						arrOfficeName.add(arrInner);
						
					}
				}
	
	        }
	      
	        catch( Exception e )
	        {
	            glogger.error( "Exception::"+e.getMessage(), e );
	            e.printStackTrace();
	        }
	        return arrOfficeName;
		}
		
		public ArrayList getEmployeeTrackDtls(Long empId) 
		{			
			ArrayList arrOuter = null;
			try 
			{
				Session hibSession = getSession();
					glogger.info("----------------------------- in final link ------------");
				
				String lsb = "SELECT em.emp_id,em.emp_fname,em.emp_mname,em.emp_lname, pom.post_name, upr.start_date, upr.end_date "+
							" FROM org_userpost_rlt upr, org_post_mst pom, org_emp_mst em "+
							" where  upr.user_id = em.user_id "+
							" and upr.post_id = pom.post_id "+
							" and em.emp_id= " +empId +
							" order by upr.start_date";
				Query sqlQuery=hibSession.createSQLQuery(lsb);
				List resList=sqlQuery.list();
				glogger.info("size of List-->"+resList.size());
				if (resList!=null && resList.size()>0) 
				{
					arrOuter=new ArrayList();
					Iterator it = resList.iterator();
					while(it.hasNext()) 
					{
						Object[] tuple = (Object[])it.next();
						ArrayList arrInner =new ArrayList();
						arrInner.add(tuple[1] + " " + tuple[2] + " " + tuple[3]);
						arrInner.add(tuple[4]);
						
						if(tuple[5]!=null)
						{
							String actionDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[5].toString()));
							arrInner.add(actionDate);
						}
						else
						{
							arrInner.add("-");
						}
						if(tuple[6]!=null)
						{
							String actionDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[6].toString()));
							arrInner.add(actionDate);
						}
						else
						{
							arrInner.add("-");
						}
						arrOuter.add(arrInner);
						
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
			}		
			return arrOuter;
		}
		
		public ArrayList getPostDtlRpt(String lLocation)
		{
			glogger.info("------------------------------------Inside office  getPostlDtlRpt--------------------");
			ArrayList arrOfficeName = new ArrayList();
			try
			{
				
				Session hibSession = getSession();
				String lsb = "select pom.post_id,pom.post_name, loc.loc_name " +
							" from org_post_mst pom, cmn_location_mst loc " +
							" where loc.loc_id = pom.loc_id " +
							" and loc.loc_id = "+lLocation;
			
				Query sqlQuery=hibSession.createSQLQuery(lsb);
				List resList=sqlQuery.list();
				glogger.info("--------------size of List-->"+resList.size());
				ArrayList vacantpost = getVacantPost(lLocation);
				
				if (resList!=null && resList.size()>0) 
				{
					Iterator it = resList.iterator();
					while(it.hasNext()) 
					{
						Object[] tuple = (Object[])it.next();
						ArrayList arrInner =new ArrayList();
						String option = "Alloted";
						for(int i =0;i<vacantpost.size();i++)
						{
							if((tuple[0].toString()).equals((vacantpost.get(i)).toString()))
							{
								option = "Vacant";
							}
							
						}
						arrInner.add(tuple[0].toString());
						arrInner.add(tuple[1].toString());
						arrInner.add(tuple[2].toString());
						arrInner.add(option);
						glogger.info("----------vacant position"+tuple[0] + " "+ tuple[1]);
						arrOfficeName.add(arrInner);
						
					}
				}
			}
				 catch( Exception e )
			        {
			            glogger.error( "Exception::"+e.getMessage(), e );
			            e.printStackTrace();
			        }
			        return arrOfficeName;
		
		}
		
		public ArrayList getVacantPost(String loffice)
		{
			glogger.info("------------------------------------Inside office  getPostlDtlRpt--------------------");
			ArrayList arrOfficeName = new ArrayList();
			try
			{
				
				Session hibSession = getSession();
				String lsb = "select pom.post_id from org_post_mst pom, cmn_location_mst loc where post_id not in (select post_id from org_userpost_rlt where activate_flag=1) and loc.loc_id = pom.loc_id and loc.loc_id = "+loffice;

				Query sqlQuery=hibSession.createSQLQuery(lsb);
				List resList=sqlQuery.list();
				glogger.info("--------------size of List-->"+resList.size());
				if (resList!=null && resList.size()>0) 
				{
					Iterator it = resList.iterator();
					while(it.hasNext()) 
					{
						Object tuple = (Object)it.next();
						glogger.info("----------vacant position"+tuple + " ");
						arrOfficeName.add(tuple);
					}
				}
			}
				 catch( Exception e )
			        {
			            glogger.error( "Exception::"+e.getMessage(), e );
			            e.printStackTrace();
			        }
			        return arrOfficeName;
		
		}
		
		public ArrayList getPostTrackDtls(Long postId) 
		{			
			ArrayList arrOuter = null;
			try 
			{
				Session hibSession = getSession();
				String lsb = "SELECT pom.post_name , em.emp_fname, em.emp_lname,upr.start_date , upr.end_date"+
								" FROM org_userpost_rlt upr, org_post_mst pom, org_emp_mst em "+
								" where pom.post_id = upr.post_id "+
								" and upr.user_id = em.user_id"+
								" and pom.post_id="+ postId +" order by upr.start_date ";
				Query sqlQuery=hibSession.createSQLQuery(lsb);
				List resList=sqlQuery.list();
				glogger.info("size of List-->"+resList.size());
				if (resList!=null && resList.size()>0) 
				{
					arrOuter=new ArrayList();
					Iterator it = resList.iterator();
					while(it.hasNext()) 
					{
						Object[] tuple = (Object[])it.next();
						ArrayList arrInner =new ArrayList();
						arrInner.add(tuple[0]);
						arrInner.add(tuple[1]+" "+tuple[2]);
						if(tuple[3]!=null)
						{
							String actionDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[3].toString()));
							glogger.info("Start date is"+actionDate);
							arrInner.add(actionDate);
						}
						else
							arrInner.add("-");
						
						if(tuple[4]!=null)
						{
							String actionDate=new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(tuple[4].toString()));
							glogger.info("end date is "+actionDate);
							arrInner.add(actionDate);
						}
						else
						{
							arrInner.add("-");
							glogger.info("in the else");
						}
						glogger.info(" Start date & end date is "+ tuple[3] +" "+tuple[4]);
						arrOuter.add(arrInner);
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				glogger.error("Exception occured in ReportQueryDAOImpl.getVoucherDtls # \n"+e);
			}		
			return arrOuter;
		}
}

