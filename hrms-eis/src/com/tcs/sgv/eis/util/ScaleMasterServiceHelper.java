package com.tcs.sgv.eis.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.ScaleMasterDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ScaleMasterServiceHelper
{
Log logger = LogFactory.getLog(getClass());
	
	ServiceLocator serv;
	
	public ScaleMasterServiceHelper(ServiceLocator serv)
	{
	super();
	this.serv=serv;
	}
	
	
	public long getScaleId(HrEisScaleMst scaleMstVO)
	{
		return scaleMstVO.getScaleId();
	}
	public void insertScaleMasterDtls(HrEisScaleMst scaleMstVO,long langId,long dbId,long postId,long locId,long userId) throws Exception
	{
		Date sysdate = new Date();
		long elementCode=1;
		
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
		
		ScaleMasterDAOImpl scalemstDao = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
		
		IdGenerator idgen=new IdGenerator();
		Long scaleId=idgen.PKGeneratorWebService("HR_EIS_SCALE_MST", serv, userId, langId, locId);
		scaleMstVO.setScaleId(scaleId);
		
		
		
		scaleMstVO.setCmnLanguageMst(cmnLanguageMst);
		scaleMstVO.setCmnDatabaseMst(cmnDatabaseMst);
		//scaleMstVO.setOrgPostMstByUpdatedByPost(orgPostMst);
		scaleMstVO.setOrgPostMstByCreatedByPost(orgPostMst);
		scaleMstVO.setCmnLocationMst(cmnLocationMst);
		//scaleMstVO.setOrgUserMstByUpdatedBy(orgUserMst);
		scaleMstVO.setOrgUserMstByCreatedBy(orgUserMst);
		scaleMstVO.setCreatedDate(sysdate);
		scaleMstVO.setElementCode(elementCode);
		//scaleMstVO.setPayBandId(payBandId);
		scaleMstVO.setIncrementDate(scaleMstVO.getIncrementDate());
		scaleMstVO.setTrnCounter(new Integer(1));
		scaleMstVO.setMigratedScale(0);
		logger.info("pay commission id is: " + scaleMstVO.getTrnCounter() );
		logger.info("pay commission id is: " + scaleMstVO.getHrPayCommissionMst().getId());
		logger.info("pay scale Type id is: " + scaleMstVO.getPayScaleType().getLookupId());
		logger.info("pay scale Type id is: " + scaleMstVO.getPayScaleType().getLookupDesc());
		
		
		scalemstDao.create(scaleMstVO);
	}
	
	public void updateScaleMasterDtls(HrEisScaleMst scaleMstVO ,long userId,long postId) throws Exception
	{
		ScaleMasterDAOImpl scalemstDao = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
		HrEisScaleMst hrEisScaleMst=scalemstDao.read(this.getScaleId(scaleMstVO));//this goes
		
		
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
		
		
		//logger.info("the old scale is "+hrEisScaleMst.getScaleStartAmt()+"-"+hrEisScaleMst.getScaleIncrAmt()+"-"+hrEisScaleMst.getScaleEndAmt()+"-"+hrEisScaleMst.getScaleHigherIncrAmt()+"-"+hrEisScaleMst.getScaleHigherEndAmt());
		//logger.info("the new scale is "+scaleMstVO.getScaleStartAmt()+"-"+scaleMstVO.getScaleIncrAmt()+"-"+scaleMstVO.getScaleEndAmt()+"-"+scaleMstVO.getScaleHigherIncrAmt()+"-"+scaleMstVO.getScaleHigherEndAmt());

		//by manoj for updating other detail and emp allow map when scale get revised
		/*GradDesgScaleMapDAO sgdDao = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());//this goes
		OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());//this goes
		
		List otherDtlsListFromSgdId ;//this goes
		HrEisSgdMpg sgdVo ;//this goes
		HrEisOtherDtls otherDtlsVo ;//this goes
*/
		/*long oldSalary = 0;//this goes
		long newSalary =0;//this goes
		long remainder =0,remainder1=0;//this goes
*/
		//List sgdListFromScaleId = sgdDao.getListByColumnAndValue("hrEisScaleMst.scaleId",scaleId );
		//List sgdListFromScaleId = sgdDao.getListByColumnAndValue("hrEisScaleMst.scaleId",this.getScaleId(scaleMstVO));//this goes
		
		/*if(sgdListFromScaleId!=null && sgdListFromScaleId.size()>0)
		{//modularizing this
			
			for(Iterator it = sgdListFromScaleId.iterator();it.hasNext();)
			{
				sgdVo = (HrEisSgdMpg)it.next();
				otherDtlsListFromSgdId = otherDtlsDao.getListByColumnAndValue("HrEisSgdMpg", sgdVo);

				if(otherDtlsListFromSgdId!=null && otherDtlsListFromSgdId.size()>0)
				{
					for(int i=0;i<otherDtlsListFromSgdId.size();i++)
					{
						otherDtlsVo = (HrEisOtherDtls)otherDtlsListFromSgdId.get(i);
						oldSalary = otherDtlsVo.getotherCurrentBasic();

						logger.info("the emp id is "+otherDtlsVo.getHrEisEmpMst().getEmpId());
						newSalary=oldSalary+(scaleMstVO.getScaleStartAmt()-hrEisScaleMst.getScaleStartAmt());
						logger.info("the old salary is "+oldSalary+" and new salary is "+newSalary);

						if(newSalary<scaleMstVO.getScaleEndAmt())
						{
							remainder = Math.abs(newSalary-scaleMstVO.getScaleStartAmt())%scaleMstVO.getScaleIncrAmt();
							logger.info("the new remainder is "+remainder);
							if(remainder>0)
							{
								newSalary= Math.round(newSalary+(scaleMstVO.getScaleIncrAmt()-((newSalary-scaleMstVO.getScaleStartAmt())%scaleMstVO.getScaleIncrAmt())));
								logger.info("the new salary is "+newSalary);
							}
						}
						else if(newSalary>scaleMstVO.getScaleEndAmt() && newSalary<scaleMstVO.getScaleHigherEndAmt())
						{
							remainder1=Math.abs(newSalary-scaleMstVO.getScaleEndAmt())%scaleMstVO.getScaleHigherIncrAmt();
							logger.info("the new remainder1 is "+remainder1);
							if(remainder1>0)
							{
								newSalary = Math.round(newSalary+(scaleMstVO.getScaleHigherIncrAmt()-((newSalary-scaleMstVO.getScaleEndAmt())%scaleMstVO.getScaleHigherIncrAmt())));
								logger.info("the new salary is "+newSalary);
							}
						}
						else if(newSalary>scaleMstVO.getScaleEndAmt())
						{
							newSalary=scaleMstVO.getScaleEndAmt();
						}
						else if(newSalary<scaleMstVO.getScaleStartAmt())
						{
							newSalary=scaleMstVO.getScaleStartAmt();
						}
						otherDtlsVo.setotherCurrentBasic(newSalary);
						otherDtlsDao.update(otherDtlsVo);
						
						ResultObject resultObj = serv.executeService("insertAllowDeducData", passMap);
						Map objServiceArgs =(Map) resultObj.getResultValue();
						
						
						if(fromScaleRevision.equalsIgnoreCase("n"))
						{
							throw new NullPointerException();
						}
						
						logger.info("insertAllowDeducData executed successfull");
					}
				}
				
			}
		}*/
		
		//end by manoj for updating other detail and emp allow map when scale get revised
			logger.info("scaleid is for updating *****************"+scaleMstVO.getScaleId());
			
		Date sysdate = new Date();
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
		hrEisScaleMst.setScaleStartAmt(scaleMstVO.getScaleStartAmt());
		hrEisScaleMst.setScaleIncrAmt(scaleMstVO.getScaleIncrAmt());
		hrEisScaleMst.setScaleEndAmt(scaleMstVO.getScaleEndAmt());
		hrEisScaleMst.setIncrementDate(scaleMstVO.getIncrementDate());
		hrEisScaleMst.setScaleEffFromDt(scaleMstVO.getScaleEffFromDt());
		hrEisScaleMst.setScaleEffToDt(scaleMstVO.getScaleEffToDt());
		
		hrEisScaleMst.setScaleHigherIncrAmt(scaleMstVO.getScaleHigherIncrAmt());
		hrEisScaleMst.setScaleHigherEndAmt(scaleMstVO.getScaleHigherEndAmt());
		hrEisScaleMst.setScaleGradePay(scaleMstVO.getScaleGradePay());
		hrEisScaleMst.setPayScaleType(scaleMstVO.getPayScaleType());
		
		hrEisScaleMst.setOrgPostMstByUpdatedByPost(orgPostMst);
		scaleMstVO.setOrgUserMstByUpdatedBy(orgUserMst);
		hrEisScaleMst.setUpdatedDate(sysdate);
		scalemstDao.update(hrEisScaleMst);
		
		logger.info("Updated successfully**************");
		
		
	}
	
	
	//Ended by Abhilash
	
}
