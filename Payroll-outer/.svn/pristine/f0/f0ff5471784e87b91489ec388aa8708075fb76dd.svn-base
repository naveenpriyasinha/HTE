package com.tcs.sgv.common.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * 
 * @author 602399
 *
 */

public interface GrantDtlDAO 
{
    public BigDecimal getGrantAmtForDDO(Long lLngFinYrId, String lStrPLNPL,
            String lStrDDOCode, String lStrDemandCode, String lStrBudMjrHd,
            String lStrBudSubMjrHd, String lStrBudMinHd, String lStrBudSubHd);
    public double getGrantAmtForMjrHd(int lLngFinYrId, String lStrPLNPL,
    		ArrayList larrMjrHdRange);
    public HashMap getMjrHDWiseGrantData(int StartLimit,int EndLimit,int FinYearId,String lStrPlNpl);
    
    public long getGrantPeriod(String todate,long lFinYearId);
            
}
