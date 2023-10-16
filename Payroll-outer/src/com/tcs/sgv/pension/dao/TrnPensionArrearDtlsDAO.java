package com.tcs.sgv.pension.dao;

import java.util.ArrayList;
import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;

public interface TrnPensionArrearDtlsDAO extends GenericDao<TrnPensionArrearDtls, Long>{

    public List<TrnPensionArrearDtls> getTrnPensionArrearDtlsVo(String lStrPnsnCode) throws Exception;
    public Double getArrearForMonth(Integer lForMonth,String lStrPnsnCode) throws Exception;
    public Double getTIArrearForMonth(Integer lForMonth,String lStrPnsnCode) throws Exception;
    public ArrayList getArrearDtls(String lStrPensionerCode,Integer lStrForMonth) throws Exception;
}
