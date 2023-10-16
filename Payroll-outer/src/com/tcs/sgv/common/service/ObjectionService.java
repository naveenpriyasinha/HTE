/**
 * 
 */
package com.tcs.sgv.common.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

/** ObjectionServiceImpl
 *  This interface contains method to get Objection Details for the objections raised by end-user.
 *  This interface is implemented in  ObjectionServiceImpl
 *  
 * 	Date of Creation : 13th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *  
 */
public interface ObjectionService 
{
	public ResultObject getObjDetails(Map inputMap);

}
