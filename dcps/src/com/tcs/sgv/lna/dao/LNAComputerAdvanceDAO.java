package com.tcs.sgv.lna.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;

public interface LNAComputerAdvanceDAO extends GenericDao {

	public List getComputerAdvance(String lStrSevaarthId, Long lLngRequestType);

	public List getComAdvanceToDEOApprover(Long lLngComAdvnId);

	public Boolean requestDataAlreadyExists(String lStrSevaarthId);

	public Boolean requestPendingStatus(String lStrSevaarthId);

}
