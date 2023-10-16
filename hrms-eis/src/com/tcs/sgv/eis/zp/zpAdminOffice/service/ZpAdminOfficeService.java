/**
 * <This class insert/update the application >
 *
 * @class name  	: <ZpAdminOfficeServiceImpl >
 * @author			: <Code Skeleton/Generator Tool - Developed By Durgesh (486841)/Zeal (479123)>
 * @version		: <1.0>
*/
package com.tcs.sgv.eis.zp.zpAdminOffice.service;

import java.util.Map;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
public interface ZpAdminOfficeService
{
	public ResultObject saveZpAdminOfficeDtls(Map objectArgs) throws Exception;
	public ResultObject retrieveZpAdminOfficeDtls(Map objectArgs) throws Exception;
}
