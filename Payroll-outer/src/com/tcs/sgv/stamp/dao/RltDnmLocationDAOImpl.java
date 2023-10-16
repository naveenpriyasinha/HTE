package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;

public class RltDnmLocationDAOImpl extends GenericDaoHibernateImpl<RltDnmLocation,BigDecimal>
{
	public RltDnmLocationDAOImpl(Class<RltDnmLocation> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public ArrayList[] insertMinStockData(Hashtable table,ServiceLocator serv,Map objectArgs)
	{
		ArrayList dnmLocationID=new ArrayList();
		ArrayList groupList=new ArrayList();
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		
		Enumeration ie=table.keys();
		String strKey="";
		String strValue="";
		String strWhereCondition="";
		String strDenominationCode="";
		String strGroupCode="";
		while(ie.hasMoreElements())
		{
			strKey=ie.nextElement().toString();
			strValue=table.get(strKey).toString();
			strWhereCondition="dnm_id ="+strKey;
			List result=commonDAO.selectRecord("mst_stamp_denomination","grp_code,dnm_code",strWhereCondition);
			if(result==null)
			{
				continue;
			}
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				strGroupCode=tuple[0].toString();
				groupList.add(strGroupCode);
				strDenominationCode=tuple[1].toString();
				Hashtable fieldList=new Hashtable();
				fieldList.put("grp_code",strGroupCode);
				fieldList.put("dnm_code", strDenominationCode);
				String strDnmLocID=commonDAO.isPresent("rlt_dnm_location", fieldList,"dnm_loc_id");
				RltDnmLocation locationVO=new RltDnmLocation();
				if(strDnmLocID!=null && strDnmLocID.length()>0)
				{
					RltDnmLocation updateVO=this.read(new BigDecimal(strDnmLocID));	
					updateVO.setDnmLocId(new BigDecimal(strDnmLocID));
					updateVO.setMinStock(Integer.parseInt(strValue));
					updateVO.setStatus("1");
					dnmLocationID.add(strDnmLocID);
					
					//this.update(updateVO);
				}
				else
				{
					BigDecimal lbgdcMstGrpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("rlt_dnm_location", objectArgs));
					String strLocationCode=SessionHelper.getLocationCode(objectArgs);
					locationVO.setDnmLocId(lbgdcMstGrpId);
					locationVO.setDnmCode(Integer.parseInt(strDenominationCode));
					locationVO.setGrpCode(Short.parseShort(strGroupCode));
					locationVO.setMinStock(Integer.parseInt(strValue));
					locationVO.setLocCode(Long.parseLong(strLocationCode));
					locationVO.setStatus("1");
					dnmLocationID.add(lbgdcMstGrpId);
					//this.create(locationVO);					
				}
				
			}
			
		}
		ArrayList[] bothArray=new ArrayList[2];
		bothArray[0]=dnmLocationID;
		bothArray[1]=groupList;
		return bothArray;
	}
	public void updateStatus(ArrayList[] dnmLocationIDAndGroupCode,ServiceLocator serv, Map objectArgs,String strGroupCode)
	{
		ArrayList dnmLocationID=dnmLocationIDAndGroupCode[0];
		ArrayList groupList=dnmLocationIDAndGroupCode[1];
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		String strwhereCondition="dnm_loc_id not in (";
		int count=0;
		if(dnmLocationID==null ||dnmLocationID.size()==0)
		{
			strwhereCondition="Loc_code="+SessionHelper.getLocationCode(objectArgs)+" and grp_code="+strGroupCode;;
		}
		else
		{
			for(int c=0;c<dnmLocationID.size();c++)
			{
				String strKey=dnmLocationID.get(c).toString();
				strwhereCondition=strwhereCondition+" "+strKey;
				if(count<(dnmLocationID.size()-1))
				{
					strwhereCondition=strwhereCondition+",";
				}
				count++;
			}
			strwhereCondition=strwhereCondition+") "+" and Loc_code="+SessionHelper.getLocationCode(objectArgs)+
						" and grp_code="+strGroupCode;
		}		
		Iterator itResult=commonDAO.getIterator("rlt_Dnm_Location", "dnm_loc_id,status",strwhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				String strDnmLocID=tuple[0]!=null?tuple[0].toString():"";
				RltDnmLocation updateVO=this.read(new BigDecimal(strDnmLocID));
				if(dnmLocationID.contains(strDnmLocID)==false)
				{
					updateVO.setDnmLocId(new BigDecimal(strDnmLocID));
					updateVO.setStatus("0");
					//this.update(updateVO);
				}
			}
		}
		
	}
	public ArrayList getCheckBoxStatus(String strGroupCode,ServiceLocator serv)
	{
		ArrayList checkboxStatus=new ArrayList();
		String strTablename=" mst_stamp_denomination deno,rlt_dnm_location loc ";
		String strFieldList="deno.dnm_id";
		String strWhereClause="deno.dnm_code = loc.dnm_code(+) and deno.grp_code =loc.grp_code(+) "+ 
		 						 " and deno.grp_code="+strGroupCode +" and loc.status=0";
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		Iterator it=commonDAO.getIterator(strTablename,strFieldList,strWhereClause);
		if(it!=null)
		{
			while(it.hasNext())
			{
				checkboxStatus.add(it.next().toString());
			}
		}
		return checkboxStatus;
	}
	
	

}
