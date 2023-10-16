package com.tcs.sgv.address.dao;

import java.util.List;
import java.util.Map;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public interface AddressDao extends GenericDao{
	OrgEmpMst getOrgEmpMstVO(long empid);
	List getUsers(long  userId);
	List<HrEisEmpMst> getUser(long userId);
	Map  getAllAddress(long userId);
}
