package com.tcs.sgv.hr.payfixation.dao;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.hr.payfixation.valueobject.PayfixationEmpInfoVO;

public interface PayFixationInfoDao extends GenericDao{

	public abstract PayfixationEmpInfoVO findByEmpIDOtherDetail(long EmpIdE,
			long langid, long empid);

}