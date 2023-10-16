package com.tcs.sgv.nps.dao;

import java.util.List;
import java.util.Map;

public interface LegacyDataEntryDao {

	

	String getDdoCode(final Long p0);
    
    List getAllEmp(final String p0, final String p1, final String p2, final String p3);
    
    int updateForRejected(final String p0, final long p1, final double p2, final double p3, final double p4, final double p5, final double p6, final String p7);
    
    int saveData(final String p0, final String p1, final long p2, final double p3, final double p4, final double p5, final double p6, final double p7, final String p8, final String p9, final Map p10, final String p11);
    
    String validationOfPeriod(final String p0, final String p1);
    
    List viewLegacyDataEntry(final String p0, final String p1, final String p2, final String p3);
    
    int verifySavedData(final String p0, final String p1);
    
    List getAllTreasuries();
    
    int rejectSavedData(final String p0, final String p1, final String p2);

    int validationOfPeriodWithStatus(String lStrSevaarthId, String period); 
}
