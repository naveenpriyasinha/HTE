package com.tcs.sgv.pension.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;

public interface RltPensioncaseBillDAO extends GenericDao<RltPensioncaseBill, Long>
{
	List getPKForRltPensioncaseBill(String lStrPPONo,String lStrBillType,String lStrStatus) throws Exception;
}
