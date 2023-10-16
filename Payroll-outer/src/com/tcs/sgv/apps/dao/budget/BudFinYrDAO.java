
/*
   * PACKAGE         : com.tcs.sgv.apps.dao.budget
   * filename        : BudFinYrDAO. java
*/  


package com.tcs.sgv.apps.dao.budget;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
/**
 * @version 1.0
 */
public interface BudFinYrDAO 
{

/******************************************Getting Financial Year **********************************

/**
* Method getFinYr returns all Financial Year from SGVC_FIN_YEAR_MST

* @return ArrayList which contains All Financial Years
*/    
  public ArrayList getFinYr() throws SQLException;
  /**
* Method getFinanceYr returns all Financial Year from SGVC_FIN_YEAR_MST

* @return ArrayList which contains All Financial Years
*/    

  public ArrayList getFinanceYr(String lStrLangID, String lStrLocID) throws SQLException;
    /**
* Method getFinYrDesc returns  Financial Year from SGVC_FIN_YEAR_MST

* @return Hashtable which contains NEXT,PREV,CURR Financial Years
*/ 
  public Hashtable getFinYrDesc(long lngCurYr) throws SQLException;
  

  public Map getFinYrInfo(int lIntFinYrId) throws SQLException,Exception;

}

