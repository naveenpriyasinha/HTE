package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMstHstId;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
/**
 * 
 * @author 602399
 * 
 */

public class DDOInfoDAOImpl implements DDOInfoDAO
{
    Log logger = LogFactory.getLog(getClass());
    Session ghibSession = null;

    public DDOInfoDAOImpl(SessionFactory sessionFactory)
    {
    	ghibSession = sessionFactory.getCurrentSession();
    }


    public List getDDOInfo(String lStrDDOCode, Long lLngLangId, Long lLngDBId)
    {
        List lListReturn = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" FROM OrgDdoMst A WHERE A.ddoCode = :ddoCode ");
            lSBQuery.append(" AND A.langId = :langId AND A.dbId = :dbId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("ddoCode", lStrDDOCode);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getDDOInfo : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDDOCode + " " + lLngLangId + " " + lLngDBId);

            lListReturn = lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOInfo and Error is : " + e, e);
        }

        return lListReturn;
    }


    public List getDDOInfoByPost(Long lLngPostId, Long lLngLangId, Long lLngDBId)
    {
        List lListReturn = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" FROM OrgDdoMst A WHERE A.postId = ");
            lSBQuery.append(" :postId AND A.langId = :langId AND A.dbId = :dbId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("postId", lLngPostId);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getDDOInfoByPost : " + lQuery.toString());
            logger.info("And Parameter is " + lLngPostId + " " + lLngLangId + " " + lLngDBId);
            
            lListReturn = lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOInfoByPost and Error is : " + e, e);
        }

        return lListReturn;
    }


    public List getDDOInfoByCardex(String lStrCardexNo, String lStrOfficeCode, Long lLngLangId, Long lLngDBId)
    {
        List lListReturn = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" SELECT O FROM OrgDdoMst O, RltDdoOrg RO ");
            lSBQuery.append(" WHERE RO.ddoCode = O.ddoCode AND O.cardexNo = :cardexNo ");
            lSBQuery.append(" AND RO.officeCode = :officeCode ");
            lSBQuery.append(" AND O.langId = :langId AND O.dbId = :dbId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());

            lQuery.setParameter("cardexNo", lStrCardexNo);
            lQuery.setParameter("officeCode", lStrOfficeCode);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getDDOInfoByCardex : " + lQuery.toString());
            logger.info("And Parameter is " + lStrCardexNo + " "  + lLngLangId + " " + lLngDBId);
            
            lListReturn = lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOInfoByCardex and Error is : " + e, e);
            logger.error("Error is: "+ e.getMessage());
        }

        return lListReturn;
    }

    public List getDDOInfoByNo(String lStrDDONo, String lDDOType, Long lLngLangId, Long lLngDBId)
    {
        List lListReturn = null;

        try
        {
            StringBuilder lSBQuery = new StringBuilder();
            Query lQuery = null;

            if (lDDOType != null && lDDOType.trim().length() > 0)
            {
                lSBQuery.append(" FROM OrgDdoMst A WHERE A.ddoNo = :ddoNo ");
                lSBQuery.append(" AND A.ddoType = :ddoType AND ");
                lSBQuery.append(" A.langId = :langId AND A.dbId = :dbId ");
                
                lQuery = ghibSession.createQuery(lSBQuery.toString());
                lQuery.setParameter("ddoType", lDDOType);
            }
            else
            {
                lSBQuery.append(" FROM OrgDdoMst A WHERE A.ddoNo = :ddoNo ");
                lSBQuery.append(" AND A.ddoType IN('A', 'D') AND A.langId = :langId AND A.dbId = :dbId ");
                lQuery = ghibSession.createQuery(lSBQuery.toString());
            }

            lQuery.setParameter("ddoNo", lStrDDONo);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getDDOInfoByNo : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDDONo + " " + lDDOType + " " + lLngLangId + " " + lLngDBId);
            
            lListReturn = lQuery.list();
        }
        catch (Exception e)
        {
            logger.error("Error in getDDOInfoByNo and Error is : " + e, e);
        }

        return lListReturn;
    }


    public List getBillOfficeForDDO(String lStrDDOCode, Long lLngLangId,
            Long lLngDBId)
    {
        List<CommonVO> lListReturn = new ArrayList<CommonVO>();

        try
        {
            StringBuilder lSBQuery = new StringBuilder();

            lSBQuery.append(" SELECT B.locationCode, B.locName FROM RltDdoOffice A, ");
            lSBQuery.append(" CmnLocationMst B WHERE A.loctnCode = B.locationCode ");
            lSBQuery.append(" AND A.ddoCode = :ddoCode AND ");
            lSBQuery.append(" B.cmnLanguageMst.langId = :langId ");
            lSBQuery.append(" AND A.dbId = :dbId ");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("ddoCode", lStrDDOCode);
            lQuery.setParameter("langId", lLngLangId);
            lQuery.setParameter("dbId", lLngDBId);

            logger.info("Query for getBillOfficeForDDO : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDDOCode + " " + lLngLangId + " " + lLngDBId);
            
            List lListData = lQuery.list();

            if (lListData != null)
            {
                Iterator lItr = lListData.iterator();
                Object[] lObj = null;
               

                while (lItr.hasNext())
                {
                    lObj = (Object[]) lItr.next();
                    if (lObj != null)
                    {
                      
                    	Long id=new Long(lObj[0].toString());
                    	String name =(lObj[1].toString());
                    	ArrayList rowList = new ArrayList(); 
                    	rowList.add( id.toString() );
               			rowList.add( name );
                    }
                }
            }
                        
        }
        catch (Exception e)
        {
            logger.error("Error in getBillOfficeForDDO and Error is : " + e, e);
        }

        return lListReturn;
    }
    
    public List getTrsryOfficeForDDO(String lStrDDOCode, Long lLngLangId)
    {
        List<CommonVO> lListReturn = new ArrayList<CommonVO>();

        StringBuilder lSBQuery = new StringBuilder();

        try
        {
            lSBQuery.append(" select loc.locId, loc.locName from RltDdoOrg rd,");
            lSBQuery.append(" CmnLocationMst loc");
            lSBQuery.append(" where rd.ddoCode = :ddoCode and loc.locId = rd.officeCode and");
            lSBQuery.append(" loc.cmnLanguageMst.langId = :langId");

            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("ddoCode", lStrDDOCode);
            lQuery.setParameter("langId", lLngLangId);

            logger.info("Query for getTrsryOfficeForDDO : " + lQuery.toString());
            logger.info("And Parameter is " + lStrDDOCode + " " + lLngLangId);
            
            List lLstData = lQuery.list();

            if (lLstData != null)
            {
                Iterator lItr = lLstData.iterator();
                Object[] lObj = null;
                CommonVO lComVo = null;

                while (lItr.hasNext())
                {
                    lObj = (Object[]) lItr.next();
                    
                    if (lObj != null)
                    {
                        lComVo = new CommonVO();
                        lComVo.setCommonId(lObj[0].toString());
                        lComVo.setCommonDesc(lObj[1].toString());
                        lListReturn.add(lComVo);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in getTrsryOfficeForDDO and Error is : " + e, e);
        }

        return lListReturn;
    }
   
    
    
    //by palak nd jay 
    
    
    public List getperdtls()
    {
    	

	   	ArrayList lArrReturnList = new ArrayList();

	   
	   	List resultList = new ArrayList();
	   	
        try
        {
            StringBuilder lSBQuery = new StringBuilder();
            lSBQuery.append(" select org.sgvaBudsubhdMst.budsubhdId as id,bud.budsubhdDescLong as name " +
            		"from OrgSchemeMstVO org,SgvaBudsubhdMst bud" +
            		" where org.sgvaBudsubhdMst.budsubhdId=bud.budsubhdId ");
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            logger.info("\nQuery for getperdtls : " + lQuery.toString());   
          
            resultList = lQuery.list();
    	   	
    	   	for (Iterator it = resultList.iterator(); it.hasNext();) {
    	   		Object[] row = (Object[]) it.next();

    	   		Long id=new Long(row[0].toString());
    	   		String name= row[1].toString();

       			ArrayList rowList = new ArrayList(); 
       			
       			rowList.add( id.toString() );
       			rowList.add( name );
       			
       			lArrReturnList.add( rowList );

       		 logger.info("\n\n rowList gettempdtls : " + rowList + "\n"); 
    	   	}

    	   
            logger.info("\n\n lArrReturnList gettempdtls : " + lArrReturnList + "\n\n"); 
        }
        catch (Exception e)
        {
            logger.error("Error in getperdtls and Error is : " + e, e);
        }

        return lArrReturnList;
    }
    
    
    
    public List gettempdtls()
    {
    	ArrayList lArrReturnList = new ArrayList(  );

        try
        {
        	List resultList = new ArrayList();
        	
        	StringBuilder lSBQuery = new StringBuilder();
            lSBQuery.append("select org.sgvaBudsubhdMst.budsubhdId,bud.budsubhdDescLong from OrgSchemeMstVO org,SgvaBudsubhdMst bud where org.sgvaBudsubhdMst.budsubhdId=bud.budsubhdId");          
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            logger.info("\nQuery for gettempdtls : " + lQuery.toString());       
            resultList = lQuery.list();
            logger.info("\n\nlListReturn gettempdtls : " + resultList + "\n\n");   
         
    	   	for (Iterator it = resultList.iterator(); it.hasNext();) 
    	   	{
    	   		Object[] row = (Object[]) it.next();

    	   		Long id=new Long(row[0].toString());
    	   		String name= row[1].toString();
    	   		   	   		
    	   		ArrayList rowList = new ArrayList(); 
       		
       			rowList.add( id.toString() );
       			rowList.add( name );
       			       		
       			lArrReturnList.add( rowList );

    	   	}
            
            
               
        }
        catch (Exception e)
        {
            logger.error("Error in gettempdtls and Error is : " + e, e);
        }

        return lArrReturnList;
    }
    

    public List getpaydtls(Long locId)
    {
    	List lArrReturnList = null;
    	List resultList = new ArrayList();
    	  logger.info("\n------in getpaydtls--------------- ");      
        try
        {
                	 
        	
        	long empId = 300208;
        	
        	StringBuilder lSBQuery = new StringBuilder();
            lSBQuery.append("select a.empDeducAmount,b.deducDisplayName " +
            		"from HrPayDeductionDtls a,HrPayDeducTypeMst b " +
            		"where a.hrEisEmpMst.empId = :empId and a.hrPayDeducTypeMst.deducCode = b.deducCode " +
            		"and a.cmnLocationMst.locId = :locId");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("locId",locId);
            lQuery.setParameter("empId",empId);
            logger.info("---------locId--------" +locId);
            logger.info("---------empId--------" +empId);
            logger.info("\n-------Query for getpaydtls : " + lQuery.toString());       
           
			resultList = lQuery.list();
            logger.info("\n\n-------lListReturn getpaydtls for deduce------------- : " + resultList + "\n\n");   
          
        }
        catch (Exception e)
        {
            logger.error("Error in getpaydtls and Error is : " + e, e);
        }

        return resultList;
    }
    
    
    public List getpaydtls1(Long locId)
    {
    	List lArrReturnList = null;
    	List resultList = new ArrayList();
    	  logger.info("\n------in getpaydtls1--------------- ");      
        try
        {
        	
        	 
        	
        	long empId = 300208;
        	
        	
        	StringBuilder lSBQuery = new StringBuilder();
  
        	lSBQuery.append("select a.empDeducAmount,b.allowDisplayName " +
            		"from HrPayDeductionDtls a,HrPayAllowTypeMst b " +
            		"where a.hrEisEmpMst.empId = :empId and a.hrPayDeducTypeMst.deducCode = b.allowCode " +
            		"and a.cmnLocationMst.locId = :locId");
            
            Query lQuery = ghibSession.createQuery(lSBQuery.toString());
            lQuery.setParameter("locId",locId);
            lQuery.setParameter("empId",empId);
            logger.info("---------locId--------" +locId);
            logger.info("---------empID--------" +empId);
        
            logger.info("\n-------Query for getpaydtls1 : " + lQuery.toString());       
            resultList = lQuery.list();
            logger.info("\n\n-------lListReturn getpaydtls1 for allow-------------- : " + resultList + "\n\n");   
             
        }
        catch (Exception e)
        {
            logger.error("Error in getpaydtls and Error is : " + e, e);
        }

        return resultList;
    }
    

    public List getcommon(Long postId)
    {
    	

    	List lArrReturnList = null;
    	List resultList = new ArrayList();
    	  logger.info("\n------in getcommon--------------- ");      
        try
        {
  
        	StringBuilder lSBQuery = new StringBuilder();
         /*  lSBQuery.append("select a.ddoCode,a.ddoName,b.empFname,b.empMname,b.empLname,b.cadre,d.dsgnCode,e.dsgnName,rt.groupId " +
            		"from OrgDdoMst a,OrgEmpMst b, HrEisEmpMst tr,OrgUserpostRlt c,OrgPostMst d,OrgDesignationMst e,HrEisGroupMst rt " +
            		"where c.orgPostMstByPostId.postId=:postId " +
            		"and a.postId=c.orgPostMstByPostId.postId " +
            		"and b.orgUserMst.userId=c.orgUserMst.userId " +
            		"and d.postId=c.orgPostMstByPostId.postId " +
            		"and e.dsgnCode=d.dsgnCode " +
            		"and tr.orgEmpMst.empId =b.empId "+
            		"and rt.groupId=tr.hrEisGroupMst.groupId ");*/
        	
        	 lSBQuery.append("select a.DDO_CODE,a.DDO_NAME,b.EMP_FNAME,b.EMP_MNAME,b.EMP_LNAME,b.CADRE,d.DSGN_CODE,e.DSGN_NAME,tr.GROUP_ID "+
        	" from ORG_DDO_MST a,ORG_EMP_MST b, HR_EIS_EMP_MST tr,ORG_USERPOST_RLT c,ORG_POST_MST d,ORG_DESIGNATION_MST e"+
        //	" where c.POST_ID = 1000300022227"+postId
        	" where c.POST_ID ="+postId+
        	" and a.POST_ID=c.POST_ID"+ 
        	" and b.USER_ID=c.USER_ID"+ 
        	" and d.POST_ID=c.POST_ID"+ 
        	" and e.DSGN_CODE=d.DSGN_CODE"+ 
        	" and tr.EMP_MPG_ID =b.EMP_ID");
            
            Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
           // lQuery.setParameter("postId",postId);
          
            logger.info("---------postId--------" +postId);
            logger.info("\n-------Query for getcommon : " + lQuery.toString());       
           
			resultList = lQuery.list();
            logger.info("\n\n-------lListReturn getcommon for deduce------------- : " + resultList + "\n\n");   
          
        }
        catch (Exception e)
        {
            logger.error("Error in getcommon and Error is : " + e, e);
        }

        return resultList;
    }
     
    
    
    
  
  // ended by palak nd jay 


	
}
