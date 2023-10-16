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
import com.tcs.sgv.dcps.valueobject.DDOInformationDetail;
import com.tcs.sgv.dcps.valueobject.DdoOffice;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 26, 2011
 */
public interface DdoOfficeDAO extends GenericDao {

	public List getAllOffices(String lStrDdoCode);
	
	public List getApproveDdoOffice(Long PostId, String talukaId, String ddoSelected);

	public DDOInformationDetail getDdoInfo(String lStrDdoCode);

	public DdoOffice getDdoOfficeDtls(Long ddoOfficeId);

	public void updateDdoOffice(String lStrDdoOffice, String lStrDdoCode);

	public String getDefaultDdoOffice(String lStrDdoCode);
	
	public Long getrltZpDDOPostId(Long PostId); 
	//added by vaibhav tyagi: start
	public void updateLocationMst(Long officeId, String officeName);
	//added by vaibhav tyagi: end
	
	// Mayuresh
	public void updateDdoOffice777(String lStrDdoOffice, String lStrDdoCode, Long lstrLocationCode);
	// End by Mayuresh

	public String districtName(String ddoCode);

	public List allTaluka(String districtID);

	public String getCityClass(String city);

	public void updateName(Long longDdoOfficeId);
}
