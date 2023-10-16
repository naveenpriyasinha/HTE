package com.tcs.sgv.eis.service;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ScaleMstServiceTest {
	Log logger = LogFactory.getLog(getClass());
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	/*public final void testInsertScaleData() {
		fail("Not yet implemented"); // TODO
	}*/

	@Test
	public final void testGetScaleData() {
		//System.out.println("----------inside testGetScaleData---------");
		long scaleId = 1000;
		String scaleName = "BS";
		String scaleDesc = "Best Scale";
		long incrAmt = 5000;
		long strtAmt = 10000;
		long endAmt = 16000;
		Date effctToDate = new Date();
		Date effctFromDate = new Date();
		long elementCode = 1;
		
		
		
		Date createdDate= new Date();
		CmnDatabaseMst cmnDBCode = new CmnDatabaseMst();
		CmnLanguageMst cmnLangId = new CmnLanguageMst();
		CmnLocationMst cmnLocId = new CmnLocationMst();
		OrgPostMst createdByPost = new OrgPostMst();
		OrgUserMst createdBy = new OrgUserMst();
		
		HrEisScaleMst scaleMstVO=new HrEisScaleMst();
		//ServiceLocator serviceLocator = new ServiceLocator();
		
		// Setting all values in VO.
		cmnDBCode.setDbId(1);
		cmnLangId.setLangId(1);
		cmnLocId.setLocId(1000);
		createdBy.setUserId(1);
		createdByPost.setPostId(1);
		scaleMstVO.setCmnDatabaseMst(cmnDBCode);
		scaleMstVO.setCmnLanguageMst(cmnLangId);
		scaleMstVO.setCmnLocationMst(cmnLocId);
		scaleMstVO.setOrgPostMstByCreatedByPost(createdByPost);
		scaleMstVO.setOrgUserMstByCreatedBy(createdBy);
		scaleMstVO.setCreatedDate(createdDate);
		scaleMstVO.setScaleId(scaleId);
		//scaleMstVO.setScaleName(scaleName);
		//scaleMstVO.setScaleDesc(scaleDesc);
		scaleMstVO.setScaleStartAmt(strtAmt);
		scaleMstVO.setScaleIncrAmt(incrAmt);
		scaleMstVO.setScaleEndAmt(endAmt);
		scaleMstVO.setScaleEffFromDt(effctFromDate);
		scaleMstVO.setScaleEffToDt(effctToDate);
		scaleMstVO.setElementCode(elementCode);
		
		
		
		try
		{
			ResultObject resObject = new ResultObject(ErrorConstants.SUCCESS);
			ScaleMasterService scaleSrvcObj = new ScaleMasterService();
			Map scaleMap = new HashMap();
			scaleMap.put("testScaleMstVO", scaleMstVO);
			//System.out.println("Hi");
			resObject=scaleSrvcObj.insertScaleData(scaleMap);
			//System.out.println("resObject is------->"+resObject.getResultValue().toString());
			
			Map getScaleValue = new HashMap();
			//System.out.println("2");
			getScaleValue = (Map)resObject.getResultValue();
			//System.out.println("getScaleValue---------->"+getScaleValue.toString());
			List list=(List)getScaleValue.get("actionList");
			//System.out.println("The size of list is*******------------->"+list.size());
		}
		catch(Exception e)
		{
			//System.out.println("U r in exception of ScaleMstServiceTest");
			logger.error("Error is: "+ e.getMessage());
		}
		fail("Not yet implemented"); // TODO
		
		
	}

	
	
	/*public final void testFinalize() {
		fail("Not yet implemented"); // TODO
	}*/

}
