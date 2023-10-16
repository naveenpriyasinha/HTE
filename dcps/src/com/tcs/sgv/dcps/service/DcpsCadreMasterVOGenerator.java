/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 23, 2011		Bhargav Trivedi								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.DcpsCadreMst;
import com.tcs.sgv.dcps.valueobject.MstDcpsDesignation;

/**
 * Class Description - 
 *
 *
 * @author Bhargav Trivedi
 * @version 0.1
 * @since JDK 5.0
 * Mar 23, 2011
 */
public interface DcpsCadreMasterVOGenerator 
{
	ResultObject generateMap(Map inputMap);
	DcpsCadreMst generateDcpsCadreMstVO(Map inputMap);

}
