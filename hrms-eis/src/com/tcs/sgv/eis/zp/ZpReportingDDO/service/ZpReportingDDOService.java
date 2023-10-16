/**
 * <This class insert/update the application >
 *
 * @class name  	: <ZpAdminOfficeServiceImpl >
 * @author			: <Code Skeleton/Generator Tool - Developed By Durgesh (486841)/Zeal (479123)>
 * @version		: <1.0>
*/
package com.tcs.sgv.eis.zp.ZpReportingDDO.service;

import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;
public interface ZpReportingDDOService
{
	//public ResultObject saveZpDepartmentMstDtls(Map objectArgs) throws Exception;
	
	public ResultObject loadReportingDDOScreen(Map objectArgs) throws Exception;
	public ResultObject getReportingSummaryPageDtls(Map objectArgs);
	public ResultObject getAdminOfficetypecodeCountDtls(Map objectArgs);
}
