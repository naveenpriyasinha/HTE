package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pension.valueobject.MstPensionCardexSigndtls;
import com.tcs.sgv.pension.valueobject.SavedCardexSignVo;

public class MstPensionCardexSigndtlsDao  extends GenericDaoHibernateImpl<MstPensionCardexSigndtls, Long>
{
	  private Log logger = LogFactory.getLog(getClass());
	public MstPensionCardexSigndtlsDao(Class<MstPensionCardexSigndtls> type,SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List<SavedCardexSignVo> getSavedCrdxSigns(Map argsMap)
	{
		List<SavedCardexSignVo> lLstSigns = new ArrayList<SavedCardexSignVo>();
		Query lQuery =null;
		StringBuffer lSBQuery = new StringBuffer();
		Session hiSession = getSession();
		List resLsit = new ArrayList();
		SavedCardexSignVo lObjVo = null;
		try
		{
			  lSBQuery.append(" SELECT mcs.cardexSigndtlsId,mcs.cardexSignId,mcs.authPersonName,mcs.authCode,mcs.desigCode,mcs.isActive,mpa.authorityName,odm.dsgnName");
			  lSBQuery.append(" ,CAM.srNo FROM CmnAttachmentMpg CAM,MstPensionCardexSigndtls mcs ,OrgDesignationMst odm,MstPensionAuthority mpa ");
			  lSBQuery.append(" WHERE mpa.authorityCode = mcs.authCode AND odm.dsgnCode = mcs.desigCode AND CAM.cmnAttachmentMst.attachmentId = mcs.cardexSignId");
 
			  lQuery = hiSession.createQuery(lSBQuery.toString());
			  resLsit = (List) lQuery.list();	
			  if(resLsit != null && ! resLsit.isEmpty())
			  {
				  Iterator it = resLsit.iterator();
				  while (it.hasNext())
				  {
					  Object tuple[] = (Object[]) it.next();
					  lObjVo = new SavedCardexSignVo();
					  if(tuple[0] != null)
						  lObjVo.setCardexSigndtlsId((Long) tuple[0]);
					  if(tuple[1] != null)
						  lObjVo.setCardexSignId((Long) tuple[1]);
					  if(tuple[2] != null)
						  lObjVo.setAuthPersonName((String) tuple[2]);
					  if(tuple[3] != null)
						  lObjVo.setAuthCode((Long) tuple[3]);
					  if(tuple[4] != null)
						  lObjVo.setDesigCode((String) tuple[4]); 
					  if(tuple[5] != null)
						  lObjVo.setIsActive((String) tuple[5]);
					  if(tuple[6] != null)
						  lObjVo.setAuthorityName((String) tuple[6]);		  
					  if(tuple[7] != null)
						  lObjVo.setDesigName((String) tuple[7]);
					  if(tuple[8] != null)
						  lObjVo.setSrNo((Long) tuple[8]);
					  lLstSigns.add(lObjVo);
				  }
			  }
		}
		catch(Exception e)
		{
			 logger.error("Error is" + e, e);
		}
		return lLstSigns;
	}
	public List<ComboValuesVO> getCrdxNames(String argsAuthCode)
	{
		List<ComboValuesVO> lLstSigns = new ArrayList<ComboValuesVO>();
		Query lQuery =null;
		StringBuffer lSBQuery = new StringBuffer();
		Session hiSession = getSession();
		List resLsit = new ArrayList();
		try
		{
			lSBQuery.append(" SELECT MPS.authPersonName,CAM.srNo,MPS.cardexSignId FROM MstPensionCardexSigndtls MPS ,CmnAttachmentMpg CAM ");
			lSBQuery.append(" WHERE CAM.cmnAttachmentMst.attachmentId = MPS.cardexSignId AND MPS.authCode = "+argsAuthCode );
			lSBQuery.append(" AND MPS.isActive = 'Y'");
			lQuery = hiSession.createQuery(lSBQuery.toString());
			  resLsit = (List) lQuery.list();	
			    if (resLsit != null && ! resLsit.isEmpty())
			    {
			        Iterator itr = resLsit.iterator();
			        while (itr.hasNext())
			        {
			            ComboValuesVO cmbVO = new ComboValuesVO();
			            Object[] obj = (Object[]) itr.next();
			            cmbVO.setId(obj[2].toString()+"~"+obj[1].toString());
			            cmbVO.setDesc(obj[0].toString());
			            lLstSigns.add(cmbVO);
			        }
			    }
		}
		catch(Exception e)
		{
			logger.error("Error is" + e, e);
		}
		return lLstSigns;
	}
}
