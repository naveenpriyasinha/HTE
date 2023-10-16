/**
 * Interface for Data access object for Deduction Type Master.
 * Made By:- Mrugesh Rajda.
 * Date:- 26-07-2007.
 */
package com.tcs.sgv.deduction.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;

public interface DeducTypeMasterDAO extends GenericDao<HrPayDeducTypeMst, Long>{

	List getDeducTypeMasterData(long langId);
	public HrPayDeducTypeMst getDeducTypeMasterDataByCode(long langid,long deducCode);
	public List checkDeducNameAvailability(String newDeducName);
    public List getDeductionTypes(long langId);
    public HrPayDeducTypeMst getDeducTypeMasterByCode(long elementCode,long langId);
    public HrPayDeducTypeMst getDeducTypeMasterData(long deducCode,long langId);
}
