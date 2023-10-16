package com.tcs.sgv.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.PartyMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.MstParty;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * @author 157045
 * @see This is used to get party details for all TOs
 * 
 * Date of Creation : 2nd July 2007
 * Revision History 
 * =================
 * Vidhya Mashru  23-Oct-2007  To make changes for code formatting
 */
public class PartyServiceImpl extends ServiceImpl implements PartyService {
	Log logger = LogFactory.getLog(getClass());
	/**
	 * This method will return party details of all Treasury offices
	 * 
	 * @param inputMap
	 * @return
	 */
	public ResultObject getPartyList(Map inputMap) {
		ResultObject rs = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			PartyMstDAOImpl partyDAO = new PartyMstDAOImpl(MstParty.class, serv
					.getSessionFactory());
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			List partyList = partyDAO.getAllPartyByLocation(SessionHelper
					.getLocationCode(inputMap));
			List bankPartyList = partyDAO
					.getAllBankPartyByLocation(SessionHelper
							.getLocationCode(inputMap));
			//System.out.println("bankPartyList ::::::::: "					+ bankPartyList.size());
			inputMap.put("PartyList", partyList);
			inputMap.put("bankPartyList", bankPartyList);
			String lStrIndex = (String) request.getParameter("Index");
			//System.out.println(" ******* lStrIndex" + lStrIndex);
			inputMap.put("Index", lStrIndex);

			rs.setResultValue(inputMap);
			rs.setSessionValues("PartyList", partyList);
			rs.setViewName("PartyListPage");
		} catch (Exception e) {
			rs.setResultValue(null);
			rs.setThrowable(e);
			rs.setResultCode(ErrorConstants.ERROR);
			rs.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getPartyList" + e,e);
		}
		return rs;
	}

}
