package com.tcs.sgv.eis.dao;

import java.util.List;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
/**
 * Interface for ScaleMaster D
 * @author Ankit C Shah 
 */
public interface ScaleMasterDAO extends GenericDao<HrEisScaleMst, Long>{
	public List getAllScaleData();
	public HrEisScaleMst getScaleData(String sid, Long langid);
	public List getScaleDataByLangId(Long langid);
	public List<HrPayCommissionMst> getPayCommisions(long langId, long locId);
	public List<CmnLookupMst> getPayBands();
}
