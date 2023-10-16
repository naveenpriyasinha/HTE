package com.tcs.sgv.exprcpt.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.exprcpt.valueobject.TrnBankScrollDetails;

/**
 * ClassName BankUploadVOGenImpl
 * 
 * Description :- Genaration of voucher vo.
 * Date of Creation 1 august 2007
 * @author 602409
 *
 */
public class BankUploadVOGenImpl extends ServiceImpl  implements BankUploadVO
{
  Log logger = LogFactory.getLog(BankUploadVOGenImpl.class); 

  /**
   * This method to generate  bank scroll upload VO
   * @param p_objServiceArgs
   * @return ResultObject
   */
  public ResultObject generateMap(Map p_objServiceArgs) 
  {
    HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");
    ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
    try
    {			
      Long lLngAttachId = null;
      try
      {
        ResultObject resultObj = serv.executeService("FILE_UPLOAD_VOGEN", p_objServiceArgs);
        Map resultMap = (Map) resultObj.getResultValue();
        CmnAttachmentMst cmnAttach = (CmnAttachmentMst) resultMap.get("attachmentVO");
        resultObj = serv.executeService("FILE_UPLOAD_SRVC", p_objServiceArgs);
        Map attachMap = (Map)resultObj.getResultValue();
        if (attachMap.get("AttachmentId_attachment")!=null) 
        {
          lLngAttachId = Long.parseLong((attachMap.get("AttachmentId_attachment").toString()));
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
        logger.error("Exception occured in BankUploadVOGenImpl.generateMap #\n" + ex);
      }

      TrnBankScrollDetails vo = new TrnBankScrollDetails();			
      request.setAttribute("attachmentId",lLngAttachId);
      if(lLngAttachId!=null)
      {
        String selectBank=request.getParameter("cmbbank");	
        //String uploadScroll=request.getParameter("fupscroll");
        String bsDesc=request.getParameter("txtdesc");
        String date=request.getParameter("txtDate");
        String accOfficer = null;
        String codeNo = null;
        String ddoCode = null;
        String ddoName = null;

        vo.setAttachmentId(lLngAttachId);
        vo.setBsLocation("");
        vo.setBsDesc(bsDesc);
        try 
        {
          vo.setBsDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
           logger.error("Exception occured in BankUploadVOGenImpl.generateMap #\n" + ex);
        }
        vo.setBankCode(selectBank);
        vo.setAccOfficer(accOfficer);
        vo.setCodeNo(codeNo);
        vo.setDdoCode(ddoCode);
        vo.setDdoName(ddoName);			
        vo.setVerified(new Short("0"));	
        vo.setCreatedUserId(SessionHelper.getUserId(request));
        vo.setCreatedPostId(SessionHelper.getPostId(p_objServiceArgs));
        vo.setCreatedDate(new Date(System.currentTimeMillis()));
        vo.setUpdatedUserId(SessionHelper.getUserId(request));
        vo.setUpdatedPostId(SessionHelper.getPostId(p_objServiceArgs));
        vo.setUpdatedDate(new Date(System.currentTimeMillis()));
        vo.setDbId(SessionHelper.getDbId(p_objServiceArgs));
        vo.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
      }

      Map<String,Object> m = new HashMap<String,Object>();
      m.putAll(p_objServiceArgs);
      m.put("voTrnBankScrollDetails",vo);
      retObj.setResultValue(m);			
      return retObj;
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
      logger.error("Exception occured in BankUploadVOGenImpl.generateMap #\n" + ex);
      retObj = new ResultObject(ErrorConstants.ERROR);			
      return retObj;				
    }	
  }

}