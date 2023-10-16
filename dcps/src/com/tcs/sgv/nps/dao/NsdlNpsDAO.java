
package com.tcs.sgv.nps.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;


public interface NsdlNpsDAO extends GenericDao {

        public List getDdoWiseTotalAmt(Long monthId, Long yrId,String treasuryCode);

        public List getDdoWiseTotalAmt_totalListForEmployeeRetired(Long monthId, Long yrId,String treasuryCode);
        
        public List getDdoWiseTotalAmtSentToNSDL(Long yearId, Long monthId, String subTrCode);
        public Long getFinYearId(String finYearCode);
        public List getFinyear();
        public List getMonths() ;
        public int checkNPSRegularFileExistsOrNot(String fileNumber,String bhid,String contriType);
        public void updatebankReftls(String fileNumber, String bhid, String month, String year,String bankRefNo,  String batch_id,String contriType);
        public int getCountOfNpsBillNotLocked(String ddoCode, int currentmonth, int currentyear) ;
        
}
