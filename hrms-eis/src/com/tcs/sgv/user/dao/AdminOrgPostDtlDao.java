
package com.tcs.sgv.user.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;

public interface AdminOrgPostDtlDao
    extends GenericDao
{

    public abstract List getAllLocationList(long l);

    public abstract List getAllBranchList(long l);

    public abstract List getMaximumPsr(long l);
    
    public abstract List getAllOfficeList();
    public abstract List getAllPostList();
    public abstract List getAllSchemeListMapped();
    public List getAllBillsFromLocation(long locId);
    public List getAllOfficesFromLocation(long locId);
    public List getAllOfficesFromDDO(long ddoCode);
    public List getActiveDesig(long activeflag);
    public List getEmployeeDetailsListFromDsgnIdForPostDtls(long designationId,long locId,long BillGroupId);
    public List getPostNameForDisplayWithSearch(long locId,String lPostName,String PsrNo,String BillNo,String Dsgn,long designationId,long BillGroupId);
    public List getActiveDsgnList(long locId);
    public List getActiveDsgnListOfFieldDept(long locId);
    public List getDDODtls(String DDOCode);
    
    public List getSubOfficesFromDDO(long postId);
    
    
   // public List getFieldDept(String DDOCode);
    
    // Added by Mayuresh
    public List submitSubject0707(long postId);
    
    public abstract List getAllddoList(String ddoCode);
    
    
    
}