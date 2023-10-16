package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.DnmMstTreasuryVO;
import com.tcs.sgv.stamp.valueobject.MstStampDenomination;
import com.tcs.sgv.stamp.valueobject.MstStampDenominationVO;

public class MstStampDenominationDAOImpl  extends GenericDaoHibernateImpl<MstStampDenomination,BigDecimal>
{
	public MstStampDenominationDAOImpl(Class<MstStampDenomination> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getDenominationCode(String strGroupCode)
	{
		List denominationCode=new ArrayList();
		Session session=getSession();
		String strQuery="select deno.dnm_code from Mst_Stamp_Denomination deno where deno.grp_code="+strGroupCode;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		int flag=0;
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				if(flag==0)
				{
					ComboValuesVO voSelect=new ComboValuesVO();
					voSelect.setId("0");
					voSelect.setDesc("--select--");
					denominationCode.add(voSelect);
					flag=1;
				}
				String strDenominationCode=itResult.next().toString();
				ComboValuesVO vo=new ComboValuesVO();
				vo.setId(strDenominationCode);
				vo.setDesc(strDenominationCode);
				denominationCode.add(vo);				
			}
		}
		else
		{
			ComboValuesVO vo=new ComboValuesVO();
			vo.setId("0");
			vo.setDesc("--select--");
			denominationCode.add(vo);
		}
		return denominationCode;
	}
	
	public List getAllStampDenominationData()
	{
		List stampDenominationData=new ArrayList();
		Session session=getSession();
		String strQuery="select  stampGrp.grp_code,stampGrp.grp_name,deno.dnm_code,deno.dnm_value,deno.label_per_sheet,deno.discount,deno.dnm_desc,deno.dnm_id from mst_stamp_group stampGrp, mst_stamp_denomination deno   where stampGrp.grp_code=deno.grp_code and deno.status='1'";
		
		Query query=session.createSQLQuery(strQuery);
		
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
			    Object[] tuple=(Object[])itResult.next();
				MstStampDenominationVO vo=new MstStampDenominationVO();
				vo.setStampGroupCode(tuple[0].toString());
				vo.setStampGroupName(tuple[1].toString());
				vo.setStampDenominationCode(tuple[2].toString());
				vo.setStampValue(tuple[3].toString());
				try
				{
					vo.setLabelPerSheet(tuple[4].toString());
				}
				catch(Exception ex)
				{
					vo.setLabelPerSheet("0");
				}
				try
				{
					vo.setDiscount(tuple[5].toString());
				}
				catch(Exception ex)
				{
					vo.setDiscount("0");
				}
				if(tuple[6]!=null)
				{
					vo.setDescription(tuple[6].toString());
				}
				else
				{
					vo.setDescription("");
				}
				vo.setDnmId(tuple[7].toString());
				stampDenominationData.add(vo);
			}
		}
		return stampDenominationData;
	}
	public void deleteDataForMstStampDenomination(String[] denominationID)
	{
		for(int count=0;count<denominationID.length;count++)
		{
			MstStampDenomination updateVO=new MstStampDenomination();
			updateVO=this.read(new BigDecimal(denominationID[count].toString()));
			updateVO.setDnmId(new BigDecimal(denominationID[count].toString()));
			updateVO.setStatus("0");
		}
	}
	public MstStampDenominationVO getUpdateDataForMstStampDenomination(String denominationID)
	{
		MstStampDenominationVO denominationVO=new MstStampDenominationVO();
		Session session=getSession();
		String strQuery="select deno.dnm_id,deno.grp_code,grp.grp_name,grp.grp_desc,deno.dnm_code,deno.dnm_value,deno.label_per_sheet,"+
						" deno.discount,deno.dnm_desc " +
						" from mst_stamp_denomination deno,mst_stamp_group grp "+
						" where deno.grp_code=grp.grp_code "+
						" and deno.dnm_id="+denominationID;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				denominationVO.setDnmId(tuple[0].toString());
				denominationVO.setStampGroupCode(tuple[1].toString());
				denominationVO.setStampGroupName(tuple[2].toString());
				denominationVO.setStampGroupDescription(tuple[3].toString());
				denominationVO.setStampDenominationCode(tuple[4].toString());
				denominationVO.setStampValue(tuple[5].toString());
				try
				{
					denominationVO.setLabelPerSheet(tuple[6].toString());
				}
				catch(Exception ex)
				{
					denominationVO.setLabelPerSheet("0");
				}
				try
				{
					denominationVO.setDiscount(tuple[7].toString());
				}
				catch(Exception ex)
				{
					denominationVO.setDiscount("0");
				}
				if(tuple[8]!=null)
				{
					denominationVO.setDescription(tuple[8].toString());
				}
				else
				{
					denominationVO.setDescription("");
				}
			}
		}
		return denominationVO;
	}
	
	public List setMinimumStockForTresuryAndSubTresury(String strGroupCode)
	{
		List denominationDetails=new ArrayList();
		Session session=getSession();
		String strQuery="select deno.dnm_id,"+
						" deno.dnm_code, "+
						" deno.dnm_desc,"+
						" deno.dnm_value,"+
						" deno.label_per_sheet,"+
						" deno.discount,"+
						" loc.min_stock"+
						" from "+ 
						" mst_stamp_denomination deno,rlt_dnm_location loc "+
						" where deno.dnm_code = loc.dnm_code(+) and deno.grp_code =loc.grp_code(+) "+
						" and deno.grp_code="+strGroupCode ;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				String strDnmID=tuple[0].toString();
				String strDenominationCode=tuple[1].toString();
				String strDenominationDes=tuple[2]!=null?tuple[2].toString():"";
				String strDenominationValue=tuple[3]!=null?tuple[3].toString():"";
				String strLablePerSheet=tuple[4]!=null?tuple[4].toString():"";
				String strDiscount=tuple[5]!=null?tuple[5].toString():"";
				String strMinStock=tuple[6]!=null?tuple[6].toString():"";
				DnmMstTreasuryVO treasuryVO=new DnmMstTreasuryVO();
				treasuryVO.setDnmID(strDnmID);
				treasuryVO.setDenominationCode(strDenominationCode);
				treasuryVO.setDescription(strDenominationDes);
				treasuryVO.setUnitValue(strDenominationValue);
				treasuryVO.setLabelPerSheet(strLablePerSheet);
				treasuryVO.setDiscount(strDiscount);
				treasuryVO.setMinStock(strMinStock);
				denominationDetails.add(treasuryVO);
				treasuryVO=null;
			}
		}
		return denominationDetails;
	}
	public Hashtable getDenominationCodeWithTotalSheet(StampCommonDAOImpl commonDAO)
	{
		Hashtable table=new Hashtable();
		String strTablename="mst_stamp_denomination m";
		String strFieldList="m.grp_code,m.dnm_code,m.label_per_sheet";
		List result=commonDAO.selectRecord(strTablename, strFieldList);
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				String strGroupCode=tuple[0]!=null?tuple[0].toString():"";
				String strDenominationCode=tuple[1]!=null?tuple[1].toString():"";
				String strLablePerSheet=tuple[1]!=null?tuple[1].toString():"";
				String strCombi=strGroupCode+"00"+strDenominationCode;
				table.put(strCombi, strLablePerSheet);
			}
		}
		return table;
	}
	public String getUnitValueForDenominationCode(String strDenominationCode,StampCommonDAOImpl commonDAO)
	{
		String strUnitValue="";
		String strTablename="mst_stamp_denomination deno";
		String strFieldList="deno.dnm_value";
		String strWhereCondition="deno.dnm_code="+strDenominationCode;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strUnitValue=itResult.next().toString();
			}
		}
		return strUnitValue;
	}
	public String getComissionForDenomination(String strDenominationCode,StampCommonDAOImpl commonDAO)
	{
		String strUnitValue="";
		String strTablename="mst_stamp_denomination deno";
		String strFieldList="deno.discount";
		String strWhereCondition="deno.dnm_code="+strDenominationCode;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strUnitValue=itResult.next().toString();
			}
		}
		return strUnitValue;
	}
	
	
}


