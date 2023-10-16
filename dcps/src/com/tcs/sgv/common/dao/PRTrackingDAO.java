package com.tcs.sgv.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tcs.sgv.common.valueobject.OrgTicketMst;
import com.tcs.sgv.core.dao.GenericDao;

public interface PRTrackingDAO extends GenericDao
{

    List getScreenMaster();

    void insertTicketMst(String title, Long screen, String desc, String credentials, Long contactNo, String emailId,
            String userName, String ddoCode, Date acknowledgeDate, Map inputMap, Long fileId) throws Exception;

    void updateTicketMst(Long ticketId, Long status, /*Long priority,*/ String remarks, Map inputMap, String user, String prevRemarks, Long severity, Long rootCause,Long fileId) throws Exception;

    String getRoleID(String postid);

    List getLoadEmpTickets(long locId, String roleId, long status, String fromDate, String toDate);

    String getPrevRemarks(Long ticketId);

    void updateTicketFlag(Long ticketId);

	void updateHistoryMst(OrgTicketMst orgTicketMst) throws Exception;

}
