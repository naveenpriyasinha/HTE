package com.tcs.sgv.common.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * DDOService
 * This interface contains method to get Details of DDO and their department 
 * based on Cardex Number provided by end-user
 * This interface is implemented in DDOServiceImpl
 * 
 * Date of Creation : 11th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * ===================== 
 * Hiral 23-Oct-2007 For making changes for code formating
 */
public interface DDOService 
{
	public ResultObject getDDOData( Map inputMap );
}
