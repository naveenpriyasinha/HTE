/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 21, 2011		Shivani Rana								
 *******************************************************************************
 */
package com.tcs.sgv.pensionpay.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * Class Description -
 * 
 * 
 * @author Shivani Rana
 * @version 0.1
 * @since JDK 5.0 Mar 21, 2011
 */
public interface ReadExcelDAO {

	/**
	 * <H3>Description -</H3>
	 * 
	 * 
	 * 
	 * @author Shivani Rana
	 * @param xlsData
	 * @param size
	 * @param strTableName
	 * @throws SQLException
	 */
	void insertIntoTable(Object[][] xlsData, int size, String lStrFileName, Map inputMap) throws Exception;

	List getStgFileErrorDtls(Long lLngDelvId) throws Exception; 
	
	String getAttachmentIdFromDelvId(Long lLngDelvId) throws Exception; 
}
