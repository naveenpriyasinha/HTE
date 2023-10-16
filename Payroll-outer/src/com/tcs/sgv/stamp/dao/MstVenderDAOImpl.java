package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.MstVendor;
import com.tcs.sgv.stamp.valueobject.MstVendorVO;

public class MstVenderDAOImpl extends GenericDaoHibernateImpl<MstVendor,BigDecimal>
{
	public MstVenderDAOImpl(Class<MstVendor> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getAllMstVenderData()
	{
		List venderData=new ArrayList();
		Session session=getSession();
		String strQuery="select vendor.ven_id, " +
		  "vendor.ven_code, " +
	      "vendor.ven_name, " +
	      "vendor.ven_reg_no, " +
	      "vendor.disc_allowed, " +
	      "vendor.active, " +
	      "to_char(vendor.start_date, 'dd/MM/yyyy') as startDate, " +
	       "to_char(vendor.end_date, 'dd/MM/yyyy') as endDate " +
	  "from mst_vendor vendor where vendor.status='1'" ;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				MstVendorVO venderVO=new MstVendorVO();
				Object[] tuple=(Object[])itResult.next();
				venderVO.setVenId(BigDecimal.valueOf(Long.parseLong(tuple[0].toString())));
				venderVO.setVenCode(Short.parseShort(tuple[1].toString()));
				venderVO.setVenName(tuple[2].toString());
				venderVO.setVenRegNo(tuple[3].toString());
				String discAllowed=((Character)tuple[4]).toString();
				if("Y".equalsIgnoreCase(discAllowed))
					venderVO.setDiscAllowed("Yes");
				else
					venderVO.setDiscAllowed("No");
				String active=((Character)tuple[5]).toString();
				if("Y".equalsIgnoreCase(active))
					venderVO.setActive("Active");
				else
					venderVO.setActive("Inactive");
				
				Date date=null;
				String dateString="";
				try
				{
					date=new SimpleDateFormat("dd/MM/yyyy").parse(tuple[6].toString());
					Calendar rightNow = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
					dateString =formatter.format(date);
				}
				catch(Exception ex)
				{
					
				}
				venderVO.setStartDate(dateString);
				try
				{
					date=new SimpleDateFormat("dd/MM/yyyy").parse(tuple[7].toString());
					Calendar rightNow = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
					dateString =formatter.format(date);
				}
				catch(Exception ex){}
				venderVO.setEndDate(dateString);
				venderData.add(venderVO);
			}
		}
		return venderData;		
	}
	public void deleteDataForMstVender(String[] vendorID)
	{
		for(int count=0;count<vendorID.length;count++)
		{
			MstVendor updateVO=new MstVendor();
			updateVO=this.read(new BigDecimal(vendorID[count].toString()));
			updateVO.setStatus("0");
			//this.update(updateVO);
		}
	}
	public MstVendorVO getUpdateDataForMstVendor(String vendorID)
	{
		MstVendorVO vendorVO=new MstVendorVO();
		Session session=getSession();
		String strQuery="select ven.ven_id, " +
	       "ven.ven_code, " +
	       "ven.ven_name, " +
	       "ven.ven_add, " +
	       "ven.ven_reg_no, " +
	       "ven.place_of_business, " +
	       "ven.disc_allowed, " +
	       "ven.active, " +
	       "to_char(ven.start_date, 'dd/MM/yyyy') as startDate, " +
	       "to_char(ven.end_date, 'dd/MM/yyyy') as endDate " +
	  "from mst_vendor ven " +
	 "where ven.ven_id = " + vendorID;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				vendorVO.setVenId(BigDecimal.valueOf(Long.parseLong(tuple[0].toString())));
				vendorVO.setVenCode(Short.parseShort(tuple[1].toString()));
				vendorVO.setVenName(tuple[2].toString());
				String address = (tuple[3] != null) ? tuple[3].toString() : "" ;
				vendorVO.setVenAdd(address);
				vendorVO.setVenRegNo(tuple[4].toString());
				String placeOfBusiness = (tuple[5] != null) ? tuple[5].toString() : "" ;
				vendorVO.setPlaceOfBusiness(placeOfBusiness);

				String discAllowed=tuple[6].toString();
				vendorVO.setDiscAllowed(discAllowed);
				String active=tuple[7].toString();
				vendorVO.setActive(active);
				Date date=null;
				String dateString="";
				try
				{
					date=new SimpleDateFormat("dd/MM/yyyy").parse(tuple[8].toString());
					Calendar rightNow = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
					dateString =formatter.format(date);
				}catch(ParseException ex){}
				vendorVO.setStartDate(dateString);

			
				try
				{
					date=new SimpleDateFormat("dd/MM/yyyy").parse(tuple[9].toString());
					Calendar rightNow = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
					dateString =formatter.format(date);
					
				}catch(ParseException ex){}
				vendorVO.setEndDate(dateString);				
			}
		}
		return vendorVO;
	}
	public String getVendorName(String strVendorCode,StampCommonDAOImpl commonDAO)
	{
		String strVendorName="";
		String strTablename="mst_vendor a";
		String strFieldList="a.ven_name";
		String strWhereCondition="a.ven_code="+strVendorCode;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strVendorName=itResult.next().toString();
			}
		}
		//System.out.println(""+strVendorName);
		return strVendorName;
	}
}
