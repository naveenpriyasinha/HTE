/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 26, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;

import com.tcs.sgv.core.dao.GenericDao;


/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 26, 2011
 */
public interface DdoSchemeDAO extends GenericDao {

	public List getSchemeListForDDO(String lStrDDOCode,int i, String strSchemeCode);

	public List getSchemeNamesFromCode(String schemeCode, String lStrDdoCode, String asstDdoCode);

	public String getSchemeNameFromCode(String schemeCode);

	public long findLevel(String ddoCode);

	public long findUsertype(String ddoCode);
	

	//Added by saurabh for subScheme description on 7th June 2017
	public String getSubSchemeNameFromCode(String schemeCode);
	public List getSubSchemeNamesFromCode(String schemeCode);
	public Long getDeptCode(String lStrDdoCode);
	public int CheckSubSchemeExistOrNot(String strSchemeCode,
			String strSubSchemeCode);
	public int CheckSubSchemeExist(String strSchemeCode);
	public List getSchemeListForDDO1(String lStrDDOCode);
	public List getAllSchemesForDDO(String strDDOCode);
	

}
