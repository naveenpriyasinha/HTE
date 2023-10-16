package com.tcs.sgv.onlinebillprep.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.core.service.ServiceLocator;

public interface TABillDAO 
{
	public List getTABillRqstDtls(Long lLngAprvdRqstId,ServiceLocator serv);
	
	public Map getTABillData(Long billNo, ServiceLocator serv);
    
    public List getPKForTable(long lLngBillNo);
}
