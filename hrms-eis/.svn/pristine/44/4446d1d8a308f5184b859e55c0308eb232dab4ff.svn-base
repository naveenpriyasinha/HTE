package com.tcs.sgv.eis.dao;

import java.util.List;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;

public interface OrderHeadMpgDAO extends GenericDao<HrPayOrderHeadMpg, Long>{
 public List getAllData();
 public List getPostListByOrderId(long orderNo);
 public List getOrderHeadMstDataByid();
 public List searchOrderHeadMst(String locCode,String strOrderName,String strStartDate,String strEndDate,int finYrId);
 public List getOrderHeadMstDataByid(String locCode,int finYrId);
 public List getAllSubhdData();
 public List getAlldeptData();
 public List getAllUserPostRltData(long loc_id);
 public String getSubHdIdFromElementCode(long subHeadId,long finYrId);
 public String getBillScheme(long billId);
}
