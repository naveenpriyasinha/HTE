package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;

@SuppressWarnings("unchecked")
public class DeductionDtlsDAOImpl extends GenericDaoHibernateImpl<HrPayDeductionDtls, Long> implements DeductionDtlsDAO {

	public DeductionDtlsDAOImpl(Class <HrPayDeductionDtls> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getAllDeductionTypeFromMaster()
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		String queryStr ="select deducCode,deducName from HrPayDeducTypeMst";

		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();


		//logger.info("objCrt.list().size()"+allowanceLst.size());
		return deducList;
	}
	public List getDeductionType()
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		String queryStr ="select deducCode,deducDisplayName from HrPayDeducTypeMst";

		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();


		//logger.info("objCrt.list().size()"+allowanceLst.size());
		return deducList;
	}

	/*public List getDeductionTypeDeducDtls(long payCommissionId,long locId)
    {
		List deducList=new ArrayList();

            Session hibSession = getSession();
            logger.info("Inside Deduction DAO");
            logger.info("Inside Deduction DAO");
            String queryStr ="select deducMst.deducCode,deducMst.deducDisplayName from HrPayDeducTypeMst as deducMst " +
            		"where deducMst.deducType not in (300160) and" 
           +" deducMst.payCommissionId in ("+ payCommissionId +  ",-1) and deducMst.deducCode in (" 
           +"select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.cmnLookupMst.lookupId=2500135 and deptCompo.hrpaycompgrpmst.isactive=1   " +
			" and deptCompo.isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" + " order by deducMst.sequence_no";

            Query query= hibSession.createQuery(queryStr);
            deducList = query.list();


        logger.info("size that i want is "+deducList.size());
        return deducList;
    }*/
	public List getDeductionTypeDeducDtls(long payCommissionId,long locId,long empId)
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		String queryStr = "select   empDeduc.deducCode, empDeduc.deducDisplayName from HrPayDeducTypeMst as empDeduc "
			+"where empDeduc.deducType  not in (300160) and empDeduc.payCommissionId in ("+ payCommissionId +  ",-1) and  empDeduc.deducCode in ("
			+" select empCompo.compId from HrEisEmpCompMpg as empCompo where empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.orgEmpMst.empId = "+empId +" and  empCompo.hrEisEmpCompGrpMst.isactive =1  and empCompo.cmnLookupMst.lookupId = 2500135)";

		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();


		return deducList;

	}

	/*public List getAllDeductionTypeFromMpg(long empid)
    {
		List deducList=new ArrayList();

            Session hibSession = getSession();
            //deducDtls.empCurrentStatus=1
            String queryStr ="select deducDtls.deducDtlsCode,deducDtls.hrPayDeducTypeMst,round(deducDtls.empDeducAmount),deducDtls.empCurrentStatus from HrPayDeductionDtls as deducDtls where deducDtls.hrEisEmpMst.empId=" + empid+" order by deducDtls.hrPayDeducTypeMst.sequence_no";

            Query query= hibSession.createQuery(queryStr);
            deducList = query.list();


        logger.info("deducList.size()"+deducList.size());
        return deducList;
    }
	 */

	public List getAllDeductionTypeFromMpg(long empid,int month ,int year)
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		//deducDtls.empCurrentStatus=1
		//String queryStr ="select deducDtls.deducDtlsCode,deducDtls.hrPayDeducTypeMst,round(deducDtls.empDeducAmount),deducDtls.empCurrentStatus from HrPayDeductionDtls as deducDtls where deducDtls.hrEisEmpMst.empId=" + empid+" order by deducDtls.hrPayDeducTypeMst.sequence_no";

		String queryStr ="select deducDtls.deducDtlsCode,deducDtls.hrPayDeducTypeMst,round(deducDtls.empDeducAmount),deducDtls.empCurrentStatus,deducDtls.month,deducDtls.year "
			+" from HrPayDeductionDtls as deducDtls "
			+" where deducDtls.hrEisEmpMst.empId=" + empid+" and deducDtls.empCurrentStatus=1 " +
					"and deducDtls.month="+month+" and deducDtls.year="+year 
			/*				"or " +
							"(deducDtls.hrPayDeducTypeMst.deducCode not in  "
			+" (select mpgSub.hrPayDeducTypeMst.deducCode from HrPayDeductionDtls as mpgSub where mpgSub.hrEisEmpMst.orgEmpMst.empId=" + empid+" " +
					"and mpgSub.month = "+month+" and mpgSub.year = "+year+" )))  "
			
			+" order by deducDtls.month	"*/;


		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();


		logger.info("deducList.size()"+deducList.size());
		return deducList;
	}

	/*	public HrPayDeductionDtls getHrPayDeductionDtls(long empId,long deducCode) {
		HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();

			Session hibSession = getSession();
			String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
			+ " and objEmpDeducMap.hrEisEmpMst.orgEmpMst.empId="+empId;
			Query query=hibSession.createQuery(strQuery);
			hrPayDeductionDtls= (HrPayDeductionDtls)query.uniqueResult();

		return hrPayDeductionDtls;
	}
	 */

	public HrPayDeductionDtls getHrPayDeductionDtls(long empId,long deducCode) {
		HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();

		Session hibSession = getSession();
		String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
		+ " and objEmpDeducMap.month=-1 and objEmpDeducMap.year=-1 and objEmpDeducMap.hrEisEmpMst.orgEmpMst.empId="+empId+" order by objEmpDeducMap.month desc";
		Query query=hibSession.createQuery(strQuery);
		hrPayDeductionDtls= (HrPayDeductionDtls)query.uniqueResult();

		return hrPayDeductionDtls;
	}
	public List getHrPayDeductionDtls(long empId,long empCurrentStatus,long deducCode,int month,int year) {
		//HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();

		List hrPayDeductionDtls=new ArrayList();

		Session hibSession = getSession();
		String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
		+ " and objEmpDeducMap.empCurrentStatus ="+empCurrentStatus+" and objEmpDeducMap.hrEisEmpMst.empId="+empId+" and  objEmpDeducMap.month in (-1) and objEmpDeducMap.year in (-1) ";
		Query query=hibSession.createQuery(strQuery);

		//hrPayDeductionDtls= (HrPayDeductionDtls)query.uniqueResult();
		/*"from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.month in (-1,"+month+") and objEmpAllowMap.year in (-1,"+year+") and objEmpAllowMap.hrEisEmpMst.empId="+empId+" order by objEmpAllowMap.month desc";*/
		hrPayDeductionDtls=query.list();
		//logger.info(" Editable Deduction is "+hrPayDeductionDtls);
		//logger.info(" Editable Deduction is "+hrPayDeductionDtls);
		return hrPayDeductionDtls;
	}
	public List getHrPayDeductionDtls(long empId,long empCurrentStatus,String deducCode,int month,int year) {
		//HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();

		List hrPayDeductionDtls=new ArrayList();

		Session hibSession = getSession();
		String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode in ( "+deducCode
		+ " ) and objEmpDeducMap.empCurrentStatus ="+empCurrentStatus+" and objEmpDeducMap.hrEisEmpMst.empId="+empId+" and  objEmpDeducMap.month in (-1) and objEmpDeducMap.year in (-1)";
		Query query=hibSession.createQuery(strQuery);

		//hrPayDeductionDtls= (HrPayDeductionDtls)query.uniqueResult();
		/*"from HrPayEmpallowMpg as objEmpAllowMap where objEmpAllowMap.hrPayAllowTypeMst.allowCode = "+allowCode
			+ " and objEmpAllowMap.empCurrentStatus ="+empCurrentStatus+" and objEmpAllowMap.month in (-1,"+month+") and objEmpAllowMap.year in (-1,"+year+") and objEmpAllowMap.hrEisEmpMst.empId="+empId+" order by objEmpAllowMap.month desc";*/
		hrPayDeductionDtls=query.list();
		//logger.info(" Editable Deduction is "+hrPayDeductionDtls);
		//logger.info(" Editable Deduction is "+hrPayDeductionDtls);
		return hrPayDeductionDtls;
	}
	/*public boolean checkHrPayDeductionDtls(long empId,long deducCode) {
		HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();

			Session hibSession = getSession();
			String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
			+ " and objEmpDeducMap.hrEisEmpMst.empId="+empId;
			Query query=hibSession.createQuery(strQuery);

			if(query.list()!=null&&query.list().size()>0)
			{	
				return true;
			}
			else
			{
				return false;	
			}	}	*/

	//by manoj for payroll generation


	public boolean checkHrPayDeductionDtls(long empId,long deducCode,int month,int year) {

		Session hibSession = getSession();
		String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
		+ " and objEmpDeducMap.hrEisEmpMst.empId="+empId+ " and objEmpDeducMap.month = "+month+" and objEmpDeducMap.year = "+year;
		Query query=hibSession.createQuery(strQuery);

		if(query.list()!=null&&query.list().size()>0)
		{	
			return true;
		}
		else
		{
			return false;	
		}	

	}	

	/*public List<HrPayDeductionDtls> getDeductionDtls(long orgEmpId,long deducCode) {
		HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();

			Session hibSession = getSession();
			String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
			+ " and objEmpDeducMap.hrEisEmpMst.orgEmpMst.empId="+orgEmpId;
			Query query=hibSession.createQuery(strQuery);


		return query.list();
	}	
	 */
	public List<HrPayDeductionDtls> getDeductionDtls(long orgEmpId,long deducCode,int month,int year) 
	{

		Session hibSession = getSession();
		String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
		+ " and objEmpDeducMap.hrEisEmpMst.orgEmpMst.empId="+orgEmpId +" and objEmpDeducMap.month in ( -1,"+month+") and objEmpDeducMap.year in (-1,"+ year+") order by objEmpDeducMap.month DESC";
		Query query=hibSession.createQuery(strQuery);

		logger.info("query is"+query.toString());

		return query.list();
	}	
	/*public List<HrPayDeductionDtls> getDeductionDtlsFromEmpId(long empId,long deducCode) {
		HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();

			Session hibSession = getSession();
			String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
			+ " and objEmpDeducMap.hrEisEmpMst.empId="+empId;
			Query query=hibSession.createQuery(strQuery);


		return query.list();
	}	*/


	public List getDeductionTypeDeducDtlsMst(long payCommissionId,long locId)
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		logger.info("Inside Deduction DAO");
		logger.info("Inside Deduction DAO");
		String queryStr ="select deducMst from HrPayDeducTypeMst as deducMst " +
		"where deducMst.deducType not in (300160) and "
		+" deducMst.payCommissionId in ("+ payCommissionId +  ",-1) and deducMst.deducCode in (" 
		+"select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo  where deptCompo.cmnLookupMst.lookupId=2500135 and deptCompo.hrpaycompgrpmst.isactive=1 and " +
		" deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId + 
		" and deptCompo.isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)" + " order by deducMst.sequence_no";

		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();



		//logger.info("objCrt.list().size()"+allowanceLst.size());
		return deducList;
	}

	public Map getEdpDeductMap(long payCommissionId,long locId)
	{
		List deducList=new ArrayList();
		Map map =new HashMap();
		Session hibSession = getSession();
		logger.info("Inside Deduction DAO");
		String queryStr="SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID = 2500135";
		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();
		for(int i=0;i<deducList.size();i++)
		{
			Object[] arr=(Object[])deducList.get(0);
			map.put(arr[1].toString(),arr[0].toString());
		}
		return map;
	}
	/*	public List getDeduListfromDeptCompMpg(String lStrMappedComoId)
	{
		List DeduMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select dedutypemst from HrPayDeducTypeMst dedutypemst where dedutypemst.deducCode in("+lStrMappedComoId+")"
		+ " order by dedutypemst.payCommissionId asc";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getDeduListfromDeptCompMpg query :: "+query);
		DeduMappedList = query.list();
		logger.info("====> DeductionMappedList :: "+DeduMappedList.size());
		return DeduMappedList;
	}*/


	/*public List getMappedCompoIDfromCompMpg(long locid)
		{
			List AllowMappedList = new ArrayList();
			Session hibsession = getSession();

			String HQL_QUERY = "select hrpaylocmpg from HrPayLocComMpg hrpaylocmpg where hrpaylocmpg.hrpaycompgrpmst.cmnLocationMst.locId="+locid+
								" and hrpaylocmpg.hrpaycompgrpmst.isactive=1  and hrpaylocmpg.cmnLookupMst.lookupId=2500135 and hrpaylocmpg.isactive=1";

			Query query = hibsession.createQuery(HQL_QUERY);
			logger.info("===> getAllowListfromDeptCompMpg query :: "+query);
			AllowMappedList = query.list();
			logger.info("====> AllowMappedList :: "+AllowMappedList.size());
			return AllowMappedList;
		}*/

	public long getAmountForDeduction(long deduc_id,long empId)
	{
		long amount=0;
		List amntList=new ArrayList();

		String query="select * from HR_PAY_DEDUCTION_DTLS where EMP_DEDUC_ID ="+deduc_id+"and EMP_ID ="+empId;
		Session hibsession = getSession();
		Query sqlQuery = hibsession.createSQLQuery(query);
		amntList = sqlQuery.list();
		if(amntList != null && amntList.size()>0 )
		{
			amount=Long.valueOf(((Object[])amntList.get(0))[3].toString());
		}
		logger.info("deduction amount is"+amount );
		return amount;
	}
	public List getDeductionTypeMst()
	{
		List<HrPayDeducTypeMst> deducList=new ArrayList();

		Session hibSession = getSession();
		String queryStr =" from HrPayDeducTypeMst";

		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();


		//logger.info("objCrt.list().size()"+allowanceLst.size());
		return deducList;
	}


	//modified by kishan
	public List getDeductionTypeForDept()
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		String queryStr ="from HrPayDeducTypeMst where deducType <> 300160 order by deducDisplayName asc";

		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();


		//logger.info("objCrt.list().size()"+allowanceLst.size());
		return deducList;
	}

	public List getMappedCompoIDfromCompMpg(long locid)//For Emp Comp Mpg Screen
	{
		List AllowMappedList = new ArrayList();
		Session hibsession = getSession();

		//String HQL_QUERY = "select hrpaylocmpg from HrPayLocComMpg hrpaylocmpg where hrpaylocmpg.hrpaycompgrpmst.cmnLocationMst.locId="+locid+
		//					" and hrpaylocmpg.cmnLookupMst.lookupId=2500135 and hrpaylocmpg.isactive=1";

		String HQL_QUERY = "select hrpaylocmpg from HrPayLocComMpg hrpaylocmpg,HrPayCompGrpMst hrpaycompgrpmst where hrpaylocmpg.hrpaycompgrpmst.cmnLocationMst.locId="+locid+
		" and hrpaylocmpg.cmnLookupMst.lookupId=2500135 and hrpaylocmpg.isactive=1"
		+" and hrpaylocmpg.hrpaycompgrpmst.compoGrpId=hrpaycompgrpmst.compoGrpId and hrpaycompgrpmst.isactive=1";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getAllowListfromDeptCompMpg query :: "+query);
		AllowMappedList = query.list();
		logger.info("====> AllowMappedList :: "+AllowMappedList.size());
		return AllowMappedList;
	}
	public List getDeduListfromDeptCompMpg(long payId,String lStrMappedComoId)//For Emp Comp Mpg Screen
	{
		List DeduMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select dedutypemst from HrPayDeducTypeMst dedutypemst where dedutypemst.payCommissionId in (-1,"+payId+") and dedutypemst.deducCode in("+lStrMappedComoId+")"
		+ " order by dedutypemst.deducDisplayName asc";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getDeduListfromDeptCompMpg query :: "+query);
		DeduMappedList = query.list();
		logger.info("====> DeductionMappedList :: "+DeduMappedList.size());
		return DeduMappedList;
	}

	//added by manish
	/*public List getDeductionTypeDeducDtlsMst(long payCommissionId,long locId,long empId)
    {
		List deducList=new ArrayList();

            Session hibSession = getSession();
           logger.info("manish here");
            String queryStr ="select deducMst.deducCode,deducMst.deducDisplayName from HrPayDeducTypeMst as deducMst  " +
            		"where  " //deducMst.deducType not in (300160) and
           +" deducMst.payCommissionId in ("+ payCommissionId +  ",-1) and deducMst.deducCode in " 
           +"(select empCompo.compId from  HrEisEmpCompMpg as empCompo where empCompo.hrEisEmpCompGrpMst.isactive=1 and empCompo.hrEisEmpCompGrpMst.orgEmpMst.empId="+empId+" and empCompo.cmnLookupMst.lookupId=2500135 and empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.cmnLocationMst.locId="+locId+" and empCompo.isactive=1 ) order by deducMst.sequence_no ";



            queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowDisplayName from HrPayAllowTypeMst as hrEmpAllMap  where " +
    		"  hrEmpAllMap.payCommissionId in (2500340,-1) and hrEmpAllMap.allowCode in " +
    		" (select empCompo.compId from  HrEisEmpCompMpg as empCompo where empCompo.hrEisEmpCompGrpMst.isactive=1 and empCompo.hrEisEmpCompGrpMst.orgEmpMst.empId=" +empId +" and empCompo.cmnLookupMst.lookupId=2500134 and empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.cmnLocationMst.locId=300022 and empCompo.isactive=1 ) order by hrEmpAllMap.sequence_no ";
            Query query= hibSession.createQuery(queryStr);
            deducList = query.list();



        //logger.info("objCrt.list().size()"+allowanceLst.size());
            Query query= hibSession.createQuery(queryStr);
            deducList = query.list();
            logger.info("size for manish is "+deducList.size());
        return deducList;

    }*/

	public List getDeductionTypeDeducDtls(String empIdStr)
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		logger.info("Inside Deduction DAO");		
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("SELECT grpMst.EMP_ID,deducMst.DEDUC_CODE,deducMst.DEDUC_DESC FROM HR_PAY_DEDUC_TYPE_MST deducMst, HR_EIS_EMP_COMPONENT_MPG compoMpg, "); 
		queryStr.append("HR_EIS_EMP_COMPONENT_GRP_MST grpMst where "); 
		queryStr.append("compoMpg.COMPO_GROUP_ID = grpMst.EMP_COMPO_GRP_ID and deducMst.DEDUC_CODE = compoMpg.COMPO_ID and COMPO_TYPE=2500135 "); 
		queryStr.append("and deducMst.DEDUC_TYPE!=300160 and compoMpg.IS_ACTIVE=1 and grpMst.IS_ACTIVE = 1 "); 
		queryStr.append("and grpMst.EMP_ID in( ");
		queryStr.append(empIdStr);
		queryStr.append(" ) order by grpMst.EMP_ID,deducMst.SEQUENCE_NO");
		
		Query query= hibSession.createSQLQuery(queryStr.toString());
		deducList = query.list();



		logger.info("objCrt.list().size()"+deducList.size());
		return deducList;
	}
	/*public List getMappedDeductionsList(String empIdStr,long locId){
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		List deductionsList =new ArrayList();
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(rb.getString("getMappedDeductions1"));
	    sb.append(rb.getString("getMappedDeductions2"));
	    sb.append(rb.getString("getMappedDeductions3"));
	    sb.append(rb.getString("getMappedDeductions4"));
	    sb.append(rb.getString("getMappedDeductions5"));
	    sb.append(rb.getString("getMappedDeductions6"));
	    sb.append(empIdStr);
	    sb.append(rb.getString("getMappedDeductions7"));
	    
	    logger.info("Location Id to Get Deductions is :"+locId);
	    logger.info("Query for getting all Deductions "+sb.toString());
	    Query query= hibSession.createSQLQuery(sb.toString());
	    
	    query.setParameter("locId", locId);
	    deductionsList = query.list();
	    return deductionsList;
	}*/
	public List getMappedDeductionsList(String empIdStr,long locId,long billNo,Date givenDate){
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		List deductionsList =new ArrayList();
	    Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    sb.append(rb.getString("getMappedDeductions1"));
	    sb.append(rb.getString("getEmployeeList"));
	    sb.append(rb.getString("getMappedDeductions2"));
	    sb.append(rb.getString("getMappedDeductions3"));
	    sb.append(rb.getString("getMappedDeductions4"));
	    sb.append(rb.getString("getMappedDeductions5"));
	    sb.append(rb.getString("getMappedDeductions6"));
	   // sb.append(empIdStr);
	    sb.append(rb.getString("getMappedDeductions7"));
	    
	    logger.info("Location Id to Get Deductions is :"+locId);
	    logger.info("Query for getting all Deductions "+sb.toString());
	    Query query= hibSession.createSQLQuery(sb.toString());
	    
	    query.setParameter("locId", locId);
	    query.setParameter("billNo", billNo);
	    query.setParameter("givenDate", givenDate);
	    deductionsList = query.list();
	    return deductionsList;
	}
	public List getDeductionTypeDeducDtlsMst(long payCommissionId,long locId,long empId)
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		logger.info("Inside Deduction DAO");
		logger.info("Inside Deduction DAO");
		String queryStr ="select deducMst from HrPayDeducTypeMst as deducMst " +
		"where deducMst.deducType not in (300160) and "
		+" deducMst.payCommissionId in ("+ payCommissionId +  ",-1) and deducMst.deducCode in (" 
		+" select empCompo.compId from HrEisEmpCompMpg as empCompo where empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.empId = "+empId +" and  empCompo.hrEisEmpCompGrpMst.isactive =1  and empCompo.cmnLookupMst.lookupId = 2500135)";

		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();



		logger.info("objCrt.list().size()"+deducList.size());
		return deducList;
	}

	//ended
	public List getDeductionTypeDeducDtlsMstEmpId(long payCommissionId,long locId,long empId,int month,int year)
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		logger.info("Inside Deduction DAO");
		logger.info("Inside Deduction DAO");
		String queryStr ="select deducMst from HrPayDeducTypeMst as deducMst " +
		"where deducMst.deducType not in (300160) and "
		+" deducMst.payCommissionId in ("+ payCommissionId +  ",-1) and deducMst.deducCode in (" 
		+" select empCompo.compId from HrEisEmpCompMpg as empCompo where empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.orgEmpMst.empId = "+empId +"   and empCompo.cmnLookupMst.lookupId = 2500135  and  empCompo.hrEisEmpCompGrpMst.EmpCompGrpId in ( "
		+"  select max(a.empCompGrpId) from HrEisEmpCompGrpMst as a where (a.month = (select max(b.month) from HrEisEmpCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+")  "
		+" or   "
		+"  ( a.year = (select max(c.year) from HrEisEmpCompGrpMst as c where c.year<"+year+" ) and a.month =( select max(d.month) from HrEisEmpCompGrpMst as d where d.year = (select max(e.year) from HrEisEmpCompGrpMst as e where e.year<"+year+"))) and a.orgEmpMst.empId =   "+empId
		+"))";
		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();



		logger.info("objCrt.list().size()"+deducList.size());
		return deducList;
	}
	public long getAmountForDeduction(long deduc_id,long empId,int month,int year)
	{
		double amount=0;
		List amntList=new ArrayList();

		String query="select * from HR_PAY_DEDUCTION_DTLS where EMP_DEDUC_ID ="+deduc_id+" and EMP_ID ="+empId+ " and month in(-1) and year in (-1) ";
		Session hibsession = getSession();
		Query sqlQuery = hibsession.createSQLQuery(query);
		amntList = sqlQuery.list();
		if(amntList != null && amntList.size()>0 )
		{
			amount=Double.parseDouble(((Object[])amntList.get(0))[3].toString());
			//amount=Math.round(amount);

		}
		logger.info("deduction amount is"+amount );
		long amt=(new Double(amount)).longValue();
		return amt;
	}
	/*public List getDeductionTypeDeducDtlsMst(long payCommissionId,long locId,long empId)
    {
		List deducList=new ArrayList();

            Session hibSession = getSession();
           logger.info("manish here");
            String queryStr ="select deducMst.deducCode,deducMst.deducDisplayName from HrPayDeducTypeMst as deducMst  " +
            		"where  " //deducMst.deducType not in (300160) and
           +" deducMst.payCommissionId in ("+ payCommissionId +  ",-1) and deducMst.deducCode in " 
           +"(select empCompo.compId from  HrEisEmpCompMpg as empCompo where empCompo.hrEisEmpCompGrpMst.isactive=1 and empCompo.hrEisEmpCompGrpMst.orgEmpMst.empId="+empId+" and empCompo.cmnLookupMst.lookupId=2500135 and empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.cmnLocationMst.locId="+locId+" and empCompo.isactive=1 ) order by deducMst.sequence_no ";



            queryStr ="select hrEmpAllMap.allowCode,hrEmpAllMap.allowDisplayName from HrPayAllowTypeMst as hrEmpAllMap  where " +
    		"  hrEmpAllMap.payCommissionId in (2500340,-1) and hrEmpAllMap.allowCode in " +
    		" (select empCompo.compId from  HrEisEmpCompMpg as empCompo where empCompo.hrEisEmpCompGrpMst.isactive=1 and empCompo.hrEisEmpCompGrpMst.orgEmpMst.empId=" +empId +" and empCompo.cmnLookupMst.lookupId=2500134 and empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.cmnLocationMst.locId=300022 and empCompo.isactive=1 ) order by hrEmpAllMap.sequence_no ";
            Query query= hibSession.createQuery(queryStr);
            deducList = query.list();



        //logger.info("objCrt.list().size()"+allowanceLst.size());
            Query query= hibSession.createQuery(queryStr);
            deducList = query.list();
            logger.info("size for manish is "+deducList.size());
        return deducList;

    }*/
	public List getDeduListfromDeptCompMpg(String lStrMappedComoId)
	{
		List DeduMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select dedutypemst from HrPayDeducTypeMst dedutypemst where dedutypemst.deducCode in("+lStrMappedComoId+")"
		+ " order by dedutypemst.deducName asc";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getDeduListfromDeptCompMpg query :: "+query);
		DeduMappedList = query.list();
		logger.info("====> DeductionMappedList :: "+DeduMappedList.size());
		return DeduMappedList;
	}

	//added by Manish
	public HrPayDeductionDtls getHrPayDeductionDtlsByHrEmp(long empId,long deducCode,int month,int year) {
		HrPayDeductionDtls hrPayDeductionDtls = new HrPayDeductionDtls();

		Session hibSession = getSession();
		String strQuery = "from HrPayDeductionDtls as objEmpDeducMap where objEmpDeducMap.hrPayDeducTypeMst.deducCode = "+deducCode
		+ " and objEmpDeducMap.hrEisEmpMst.orgEmpMst.empId="+empId+" and  objEmpDeducMap.month = "+month+" and objEmpDeducMap.year="+year;
		Query query=hibSession.createQuery(strQuery);
		hrPayDeductionDtls= (HrPayDeductionDtls)query.uniqueResult();

		return hrPayDeductionDtls;
	}

/*	public double getHrrValueForEmp(long userId)
	{
		Session hibSession = getSession();
		String strQuery = "from HrEisQtrMst as empQtrMpg where  empQtrMpg.orgUserMstByAllocatedTo.userId ="+userId;
		Query query=hibSession.createQuery(strQuery);
		HrEisQtrMst hrEisQtrMst = query.list()!=null && query.list().size()>0? (HrEisQtrMst)query.list().get(0):null;
		if(hrEisQtrMst != null)
		{
			//return hrEisQtrMst.getGarageCharge()+hrEisQtrMst.getQuarterRent()+hrEisQtrMst.getServiceCharge();
			return hrEisQtrMst.getQuarterRent();
		}
		else
		{
			return 0;
		}
	}*/


	@SuppressWarnings("deprecation")
	public Map getHrrValueForEmp(long userId,int monthGiven,int yearGiven)
	{
		Map returnMap = new HashMap();
		returnMap.put("hrr", new Double(0));
		returnMap.put("isAdjustedHRRPartial", new Boolean(false));
		returnMap.put("leftHRADays",new Integer(0));
		returnMap.put("daysInMonthHRA",new Integer(0));
		logger.info(" inside getHrrValueForEmp ");
		Session hibSession = getSession();
		String strQuery = "from HrEisQtrMst as empQtrMpg where  empQtrMpg.orgUserMstByAllocatedTo.userId ="+userId;
		Query query=hibSession.createQuery(strQuery);
		HrEisQtrMst hrEisQtrMst = query.list()!=null && query.list().size()>0? (HrEisQtrMst)query.list().get(0):null;
		if(hrEisQtrMst != null)
		{
			//return hrEisQtrMst.getGarageCharge()+hrEisQtrMst.getQuarterRent()+hrEisQtrMst.getServiceCharge();
			//int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) :(Calendar.getInstance().get(Calendar.MONTH)-1);
			//int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : Calendar.getInstance().get(Calendar.YEAR);
			Date vaccantDate = hrEisQtrMst.getAllocationEndDate();
			if(hrEisQtrMst.getAllocationEndDate()==null)
			{
				logger.info(" quater end date is not set");
				returnMap.put("hrr",new Double(hrEisQtrMst.getQuarterRent()));
				return returnMap;
			}
			Calendar calGiven = Calendar.getInstance();

			calGiven.set(Calendar.YEAR, yearGiven);
			calGiven.set(Calendar.MONTH,monthGiven-1);
			calGiven.set(Calendar.DAY_OF_MONTH, 1);
			calGiven.set(Calendar.HOUR_OF_DAY, 0);
			calGiven.set(Calendar.MINUTE, 0);
			calGiven.set(Calendar.SECOND, 0);
			calGiven.set(Calendar.MILLISECOND, 0);
			
			Date billStartDate =  calGiven.getTime();
			int maxDay=calGiven.getActualMaximum(Calendar.DAY_OF_MONTH);
			calGiven.set(Calendar.HOUR_OF_DAY, 23);
			calGiven.set(Calendar.MINUTE, 59);
			calGiven.set(Calendar.SECOND, 59);
			calGiven.set(Calendar.MILLISECOND, 999);
			calGiven.set(Calendar.DAY_OF_MONTH,maxDay);
			Date billEndDate = calGiven.getTime();

			
			logger.info(" Quater end date "+vaccantDate.toString()+" Bill Start date "+billStartDate.toString()+" Bill end date "+billEndDate.toString());
			if(vaccantDate.getMonth() >= Calendar.getInstance().getTime().getMonth() || vaccantDate == null){
				Boolean isAdjustedHRRPartial = new Boolean(false);
				Integer leftHRADays = billEndDate.getDate() - vaccantDate.getDate();
				//Integer daysInMonthHRA = (int) daysInMonth;
				returnMap.put("hrr", new Double(hrEisQtrMst.getQuarterRent()));
				returnMap.put("isAdjustedHRRPartial", isAdjustedHRRPartial);
				returnMap.put("leftHRADays", leftHRADays);
				//returnMap.put("daysInMonthHRA", daysInMonthHRA);
			}else{
			if(vaccantDate.before(billStartDate))
			{
				logger.info(" quater end date is before bill start date -> rent = 0");
				returnMap.put("hrr", new Double(0));
				//return 0;
			}
			else if(vaccantDate.compareTo(billEndDate)>=0)
			{
				logger.info(" quater end date is after bill end date -> take entire rent");
				returnMap.put("hrr",new Double(hrEisQtrMst.getQuarterRent()));
				//return hrEisQtrMst.getQuarterRent();
			}
			else
			{
				long daysInMonth = billEndDate.getDate() - billStartDate.getDate()+1;
				long daysOfQuaters = vaccantDate.getDate() - billStartDate.getDate()+1;
				Double adjustedHRR = new Double((double)Math.round((((double)hrEisQtrMst.getQuarterRent())/daysInMonth) * daysOfQuaters));
				logger.info(" quater end date is after bill end date -> take partial house rent");
				logger.info(" daysOfQuaters "+daysOfQuaters+" daysInMonth "+daysInMonth+" adjustedHRR "+adjustedHRR);
				Boolean isAdjustedHRRPartial = new Boolean(true);
				Integer leftHRADays = billEndDate.getDate() - vaccantDate.getDate();
				Integer daysInMonthHRA = (int) daysInMonth;
				
				returnMap.put("hrr", adjustedHRR);
				returnMap.put("isAdjustedHRRPartial", isAdjustedHRRPartial);
				returnMap.put("leftHRADays", leftHRADays);
				returnMap.put("daysInMonthHRA", daysInMonthHRA);
				//return (int)adjustedHRR;

			}}


		}
		else
		{
			logger.info(" No quater records found ");
			//return 0;
		}
		return returnMap;
	}
	//ended

	/**
	 * @author Amish Parikh
	 * @purpose	To prepare Pay Certificate 
	 * @return	List of rows of employee deduction mapping
	 * This method will return all the deductions mapped to an employee.
	 */


	public List getMasterdataForEmpDedMpg(long empid)
	{

		List deducLst=new ArrayList();
		Session hibSession = getSession();

		String queryStr ="from HrPayDeductionDtls as hrEmpDedDtls where hrEmpDedDtls.hrEisEmpMst.orgEmpMst.empId=" + empid+" and hrEmpDedDtls.empCurrentStatus=1 and hrEmpDedDtls.month=-1 and hrEmpDedDtls.year=-1"
		+" and hrEmpDedDtls.hrPayDeducTypeMst.deducCode in ( select hrEisEmpCompMpg.compId from HrEisEmpCompMpg  as hrEisEmpCompMpg where hrEisEmpCompMpg.hrEisEmpCompGrpMst.hrEisEmpMst.orgEmpMst.empId = " +
		empid +
		" and hrEisEmpCompMpg.hrEisEmpCompGrpMst.isactive=1 " +
		" and hrEisEmpCompMpg.isactive =1 " +
		" and hrEisEmpCompMpg.cmnLookupMst.lookupId= 2500135"+
		")";

		Query query= hibSession.createQuery(queryStr);
		//logger.info("query is "+query);
		deducLst = query.list();
		return deducLst;

	}

	//End Amish

	public List getDeduListfforBulk(String lStrMappedComoId)//For Emp Comp Mpg Screen
	{
		List DeduMappedList = new ArrayList();
		Session hibsession = getSession();

		String HQL_QUERY = "select dedutypemst from HrPayDeducTypeMst dedutypemst where dedutypemst.deducType <> 300160 and dedutypemst.deducCode <> 80 and dedutypemst.deducCode not in (120,121,122,59) and dedutypemst.deducCode in("+lStrMappedComoId+")"
		+ " and dedutypemst.deducCode not in (select expMst.hrPayDeducTypeMst.deducCode from HrDeducExpMst as expMst) order by dedutypemst.deducDisplayName asc";

		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("===> getDeduListfromDeptCompMpg query :: "+query);
		DeduMappedList = query.list();
		logger.info("====> DeductionMappedList :: "+DeduMappedList.size());
		return DeduMappedList;
	}
	
	//Added By Kishan
	public List getNonGovDeductionTypeForDept()
	{
		List deducList=new ArrayList();

		Session hibSession = getSession();
		String queryStr ="from HrPayDeducTypeMst where deducType = 300160 order by deducDisplayName asc";

		Query query= hibSession.createQuery(queryStr);
		deducList = query.list();


		//logger.info("objCrt.list().size()"+allowanceLst.size());
		return deducList;
	}
	
	
	public int updateHrPayDeductionDtls(double deducAmount, long postMstId, long userMstId, Date updDate, long deducCode, long empId, long month, long year)
	{
		Session hibSession = getSession();
		
		StringBuffer strQuery = new StringBuffer(); 
		strQuery.append("update HrPayDeductionDtls as empDed set ");
		strQuery.append(" empDed.empDeducAmount = :deducAmount, ");
		strQuery.append(" empDed.orgPostMstByUpdatedByPost.postId = :postMstId, ");
		strQuery.append(" empDed.orgUserMstByUpdatedBy.userId = :userMstId, ");
		strQuery.append(" empDed.updatedDate = :updDate ");
		strQuery.append(" where empDed.hrPayDeducTypeMst.deducCode = :deducCode ");
		strQuery.append(" and empDed.hrEisEmpMst.empId=:empId and empDed.month = :month and empDed.year = :year");
		
		Query updateQuery = hibSession.createQuery(strQuery.toString());
		updateQuery.setDouble("deducAmount", deducAmount);
		updateQuery.setLong("postMstId", postMstId);
		updateQuery.setLong("userMstId", userMstId);
		updateQuery.setLong("deducCode", deducCode);
		updateQuery.setLong("empId", empId);
		updateQuery.setLong("month", month);
		updateQuery.setLong("year", year);
		updateQuery.setDate("updDate", updDate);
		
		int updateCount = updateQuery.executeUpdate();
		
		return updateCount;
	}
	
	public int insertHrPayDeductionDtls(long deducCode, long empId, long empDeducId, double deducAmt, long empCurrStatus, 
			long locId, long dbId, long createdBy, Date createdDate, long createdByPost)
	{
		int insertCount = 0;
		Session hibSession = getSession();
		
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append(" insert into HR_PAY_DEDUCTION_DTLS " );
		queryBuff.append(" (DEDUC_DTLS_CODE,EMP_ID,EMP_DEDUC_ID,EMP_DEDUC_AMOUNT,EMP_CURRENT_STATUS, ");
		queryBuff.append(" LOC_ID,DB_ID,CREATED_BY,CREATED_DATE,CREATED_BY_POST,UPDATED_BY,UPDATED_DATE,UPDATED_BY_POST,TRN_COUNTER,month,year) ");
		queryBuff.append(" values  ");
		queryBuff.append(" (:deducCode,:empId,:empDeducId,:deducAmt,:empCurrStatus,:locId,:dbId,:createdBy,:createdDate,:createdByPost,null,null,null,1,-1,-1) ");
		
		Query insertQuery = hibSession.createSQLQuery(queryBuff.toString());
		insertQuery.setLong("deducCode", deducCode);
		insertQuery.setLong("empId", empId);
		insertQuery.setLong("empDeducId", empDeducId);
		insertQuery.setDouble("deducAmt", deducAmt);
		insertQuery.setLong("empCurrStatus", empCurrStatus);
		insertQuery.setLong("locId", locId);
		insertQuery.setLong("dbId", dbId);
		insertQuery.setLong("createdBy", createdBy);
		insertQuery.setDate("createdDate", createdDate);
		insertQuery.setLong("createdByPost", createdByPost);
		
		insertCount = insertQuery.executeUpdate();
		
		return insertCount;
		
	}
}

