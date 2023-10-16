package com.tcs.sgv.hr.payfixation.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.hr.payfixation.valueobject.PayfixationEmpInfoVO;
public class PayFixationInfoDaoImpl extends GenericDaoHibernateImpl implements PayFixationInfoDao{
	
	public PayFixationInfoDaoImpl(Class type, SessionFactory sessionFactory){
        super(type);
        setSessionFactory(sessionFactory);
    }

	
	 /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationInfoDao#findByEmpIDOtherDetail(long, long, long)
	 */
	public PayfixationEmpInfoVO findByEmpIDOtherDetail(long EmpIdE,long langid,long empid){
		 
		
		 List otherDtl=new ArrayList();
		 Session hibSession=null;
		 hibSession = getSession();
		 //OrgEmpMst hrEisMst =(OrgEmpMst) hibSession.get(OrgEmpMst.class, EmpId);
		 /*OrgEmpMst hrEisMst = new OrgEmpMst();
		 hrEisMst.setEmpId(EmpId);*/
		 PayfixationEmpInfoVO genEmpVO =new PayfixationEmpInfoVO();
		 List allDetails=new ArrayList();
		
		 Criteria EmpCrit = hibSession.createCriteria(OrgEmpMst.class);
		 
		 if(langid==1)
		 {
		 EmpCrit .add(Restrictions.eq("empId",EmpIdE));
		 
		 }
		 if(langid==2)
		 {
			 EmpCrit .add(Restrictions.eq("empId",empid)); 
		 }
			otherDtl= EmpCrit .list();
			OrgEmpMst empDet=(OrgEmpMst)otherDtl.get(0);
			
			
			genEmpVO.setEmpName(empDet.getEmpFname()+" "+empDet.getEmpMname()+" "+empDet.getEmpLname());
			genEmpVO.setDoj(empDet.getEmpDoj());
			genEmpVO.setDor(empDet.getEmpSrvcExp());
			
			long userId=empDet.getOrgUserMst().getUserId();
			
			
			genEmpVO.setUserId(userId);
			OrgUserpostRlt uprlt = (OrgUserpostRlt)hibSession.get(OrgUserpostRlt.class, userId);
			
			long postId=uprlt.getOrgPostMstByPostId().getPostId();
			
			
			 String query1="from OrgPostDetailsRlt  where orgPostMst.postId like "+postId+" and cmnLanguageMst.langId="+langid;

		        Query sqlQuery1 = hibSession.createQuery(query1);
		        List emppay = new ArrayList();
		        emppay = sqlQuery1.list();
		        OrgPostDetailsRlt EmpMaster=new OrgPostDetailsRlt();
		        EmpMaster=(OrgPostDetailsRlt)emppay.get(0);
		       
			//OrgPostDetailsRlt pdrlt =(OrgPostDetailsRlt)hibSession.get(OrgPostDetailsRlt.class, postId);
			//System.out.println(pdrlt.getOrgDesignationMst().getDsgnName());
			//System.out.println(pdrlt.getOrgDesignationMst().getDsgnId());
			//String desgName	=pdrlt.getOrgDesignationMst().getDsgnName();
			//long desigid=pdrlt.getOrgDesignationMst().getDsgnId();
			
			
			genEmpVO.setDesignation(EmpMaster.getOrgDesignationMst().getDsgnName());
			genEmpVO.setDesigid(EmpMaster.getOrgDesignationMst().getDsgnId());
			
			
			Criteria EmpCrit1 = hibSession.createCriteria(HrEisOtherDtls.class);
			 
			 EmpCrit1.add(Restrictions.eq("hrEisEmpMst.empId",EmpIdE));
				otherDtl= EmpCrit1 .list();
			
			HrEisOtherDtls hrEisSalDet =(HrEisOtherDtls)otherDtl.get(0);
			long salary=hrEisSalDet.getotherCurrentBasic();
			genEmpVO.setSalary(salary);
			
			
			return genEmpVO;
	 }	
	
	
	
}
