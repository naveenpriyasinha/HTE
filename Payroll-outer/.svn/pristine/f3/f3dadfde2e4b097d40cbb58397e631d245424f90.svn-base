package com.tcs.sgv.exprcpt.service;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.common.dao.BillEdpDAOImpl;
import com.tcs.sgv.common.dao.BudHeadDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.RltBillTypeEdp;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillEdpDtls;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.exprcpt.dao.VoucherDAOImpl;
import com.tcs.sgv.exprcpt.valueobject.MstListPayRcpt;
import com.tcs.sgv.exprcpt.valueobject.TrnVoucherDetails;
import com.tcs.sgv.exprcpt.valueobject.VoucherVO;

/**
 * ClassName VoucherServiceImpl
 * 
 * Description :- Genaration of voucher vo.
 * Date of Creation 26 July 2007
 * @author 602409
 * Revision History ===================== Bhavesh 23-Oct-2007 For making
 * changes for code formating
 */
public class VoucherVOGeneratorImpl extends ServiceImpl implements VoucherVOGenerator
{
  Log logger = LogFactory.getLog(getClass());


  /**
   * This method to generate voucher details VO. 
   * 
   * @param objectArgs 
   * @return ResultObject  
   */
  public ResultObject generateMap(Map p_objServiceArgs) 
  {
    ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
    HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");
    try
    {
      String netAmt=StringUtility.getParameter("txtNetAmount", request);
      String grossInt=StringUtility.getParameter("txtGrossInt", request);
      String principle=StringUtility.getParameter("txtPrinciple", request);
      String incomeTax=StringUtility.getParameter("txtIncomeTax", request);
      String billNo=StringUtility.getParameter("hdbillNo", request);
      String classOfExp=StringUtility.getParameter("cmbClassExp", request);
      String typeOfBud=StringUtility.getParameter("cmbTypeOfBgt", request);
      String fund=StringUtility.getParameter("cmbFund", request);
      String majorHead=StringUtility.getParameter("cmbMajorHead", request);
      String demandNo=StringUtility.getParameter("cmbDemand", request);
      String SubMHead=StringUtility.getParameter("cmbSubMajorHead", request);
      String schemeNo=StringUtility.getParameter("txtSchemeCode", request);
      String minorHead=StringUtility.getParameter("cmbMinorHead", request);
      String headCrg=StringUtility.getParameter("txtHeadChar", request);
      String subHead=StringUtility.getParameter("cmbSubHead", request);
      String detailHead=StringUtility.getParameter("cmbDetailHead", request);

      logger.info("\n\n\n\n-----Principle Is ----"+principle+"-------\n\n\n");
      VoucherVO vouchVo = new VoucherVO();
      if(grossInt!=null && grossInt.length()>0)
        vouchVo.setBillGrossInt(new BigDecimal(grossInt));
      if(netAmt!=null && netAmt.length()>0)
        vouchVo.setBillNetAmount(new BigDecimal(netAmt));
      if(principle!=null && principle.length()>0)	
        vouchVo.setPrinciple(new BigDecimal(principle));
      if(incomeTax!=null && incomeTax.length()>0)
        vouchVo.setIncomeTax(new BigDecimal(incomeTax));
      vouchVo.setBillNo(Long.parseLong(billNo));

      TrnBillBudheadDtls budHeadVo= new BudHeadDAOImpl(TrnBillBudheadDtls.class,serv.getSessionFactory()).getBudHdDtlVOByBillNo(Long.parseLong(billNo));

      if(budHeadVo==null || budHeadVo.getBillBudId()==0) {
        budHeadVo = new TrnBillBudheadDtls();
        budHeadVo.setCreatedUserId(SessionHelper.getUserId(request));
        budHeadVo.setCreatedPostId(SessionHelper.getPostId(p_objServiceArgs));
        budHeadVo.setCreatedDate(new Date(System.currentTimeMillis()));
      }
      budHeadVo.setBillNo(Long.parseLong(billNo));
      budHeadVo.setClsExp(classOfExp);
      if (typeOfBud!=null) budHeadVo.setBudType(typeOfBud);
      budHeadVo.setFund(fund);
      budHeadVo.setBudMjrHd(majorHead);
      budHeadVo.setDmndNo(demandNo);
      budHeadVo.setBudSubmjrHd(SubMHead);
      budHeadVo.setSchemeNo(schemeNo);
      budHeadVo.setBudMinHd(minorHead);
      budHeadVo.setHeadChrg(headCrg);
      budHeadVo.setBudSubHd(subHead);
      budHeadVo.setBudDtlHd(detailHead);
      budHeadVo.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
      budHeadVo.setDbId(SessionHelper.getDbId(p_objServiceArgs));

      p_objServiceArgs.put("vouchVo", vouchVo);
      p_objServiceArgs.put("budHeadVo", budHeadVo);
      p_objServiceArgs.put("billNo", billNo);
      //p_objServiceArgs.put("rcptVOlist", rcptVOlist);
      retObj.setResultValue(p_objServiceArgs);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Error Occurred In VoucherVOGeneratorImpl.generateMap"+e);
    }
    return retObj;
  }

  /**
   * This method to generate Edp  details VO. 
   * 
   * @param objectArgs 
   * @return ResultObject  
   */
  public ResultObject generateEdpDtlsMap(Map p_objServiceArgs) 
  {
    ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
    HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");

    try
    {
      String billNo=(String) p_objServiceArgs.get("billNo");
      
      
      
      
      
      /*String tempArray1[] =new String[tempArray.length];
      
      boolean flag=true;
      int counter=-1;
      for(int i=0;i<tempArray.length;i++)
      {
    	  String s = tempArray[i];
    	  logger.info("S is " + s);
    	  for(int k=i+1;k<tempArray.length;k++)
    	  {
    		  if(tempArray[k].equals(s))
    		  {
    			  tempArray[k]="";
    			 flag=false;
    			 break;
    		  }
    		  else
    			  flag=true;
    		 
    	  }
    	  if(flag)
    	  {
    		  counter++;
    		  tempArray1[counter]=s;
    		  logger.info("payEdpId added " +  tempArray1[counter]);
    		  
    	  }
      }*/
      
      

      
      
      
      
      
      
      String payAddDed[] =StringUtility.getParameterValues("hdPayAddDed", request);
      String payEdpCat[] =StringUtility.getParameterValues("hdPayEdpCate", request);
      String payEdpCode[] =StringUtility.getParameterValues("hdPayEdpCode", request);
      List payVOlist=new ArrayList();
      
      String tempArray[] = StringUtility.getParameterValues("hdPayEdpId", request);
      String tempArray1[] = new String[tempArray.length];
      
      logger.info("tempArray is " + tempArray.length);
      
      
      Arrays.sort(tempArray);
      
      for(int j=0;j<tempArray.length;j++)
    	  logger.info("TempArray element is " + tempArray[j]);

      int counter = 0;

      for (int i = 0; i < tempArray.length; i++)
      {
      if (i > 0 && tempArray[i].equals(tempArray[i -1]))
      continue;

      tempArray1[counter++] = tempArray[i];
      }

      String payEdpId[] = new String[counter];
	  System.arraycopy(tempArray1, 0, payEdpId, 0, counter);
	  logger.info("payEdpId is " + payEdpId.length);
	  
	  for(int j=0;j<payEdpId.length;j++)
    	  logger.info("TempArray element is " + payEdpId[j]);
      
      BillEdpDAOImpl billEdpImpl=new BillEdpDAOImpl(TrnBillEdpDtls.class,serv.getSessionFactory());
      for(int i=0;i<payEdpId.length;i++)
      {
    	  //added by Ankit Bhatt
    	  logger.info("I is " + i);
    	  String strLocCode = SessionHelper.getLocationCode(p_objServiceArgs);
    	  com.tcs.sgv.common.valueobject.RltBillTypeEdp rltBillTypeEdp = (RltBillTypeEdp)billEdpImpl.getEdpDtls(payEdpId[i], strLocCode);
    	  //ended by Ankit Bhatt.
        String sExp = "EXP";
        //String amount=StringUtility.getParameter("txtAmt"+payEdpCode[i], request);
        //added by Ankit Bhatt
        String amount=StringUtility.getParameter("txtAmt"+rltBillTypeEdp.getEdpCode(), request);
        //ended 
        logger.info("Ankit Before Check Condition Amount Is:-"+amount);
        //if(Double.parseDouble(amount)!=0)  // removed because to entry all edp codes along with 0 amount for payroll updation purpose.
        {
         logger.info("Inside Conditon TRUE Amount Is:-"+amount + "      Edp Is " + rltBillTypeEdp.getEdpCode());
          TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
          //billEdpVO=billEdpImpl.getBillEdpDtl(payEdpCode[i],Long.parseLong(billNo),SessionHelper.getLocationCode(p_objServiceArgs),"EXP");
          //added by Ankit Bhatt
          billEdpVO=billEdpImpl.getBillEdpDtl(rltBillTypeEdp.getEdpCode(),Long.parseLong(billNo),SessionHelper.getLocationCode(p_objServiceArgs),"EXP");
          //ended
          logger.info("Bill EDP Vo is:-"+billEdpVO);
          if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
          {
            billEdpVO=new TrnBillEdpDtls();
            billEdpVO.setTrnCounter(new Integer(1));
          }
          //billEdpVO.setBillBudId(Long.parseLong("2"));
          billEdpVO.setTypeEdpId(Long.parseLong(payEdpId[i]));
          billEdpVO.setBillNo(Long.parseLong(billNo));
          billEdpVO.setEdpAmt(new BigDecimal(amount));
          billEdpVO.setVersionId(Long.parseLong("1"));
          billEdpVO.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
          billEdpVO.setDbId(SessionHelper.getDbId(p_objServiceArgs));
          //commented by Ankit Bhatt
          /*billEdpVO.setAddDedFlag(payAddDed[i]);
          billEdpVO.setEdpCategory(payEdpCat[i]);
          billEdpVO.setEdpCode(payEdpCode[i]);*/
          //ended
          
          //added by Ankit Bhatt
          billEdpVO.setAddDedFlag(rltBillTypeEdp.getAddDedFlag());
          billEdpVO.setEdpCategory(rltBillTypeEdp.getEdpCategory());
          billEdpVO.setEdpCode(rltBillTypeEdp.getEdpCode());
          //ended
          
          billEdpVO.setExpRcpRec("EXP");
          billEdpVO.setAutoAdd("Y");
          payVOlist.add(billEdpVO);
        }
      }
      String expRows[] = StringUtility.getParameterValues("exprows", request);		
      for(int i=0;i<expRows.length;i++)
      {
        String edpCode=StringUtility.getParameter("txtEdpCode"+expRows[i], request);
        String addDedFlag=StringUtility.getParameter("txtAddDed"+expRows[i], request);
        String edpCategory=StringUtility.getParameter("txtDedType"+expRows[i], request);
        String amount=StringUtility.getParameter("txtAmt"+expRows[i], request);
        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
        if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
        {
          billEdpVO=new TrnBillEdpDtls();
          billEdpVO.setTrnCounter(new Integer(1));
        }
        if(Double.parseDouble(amount)!=0)
        {
          if(edpCode!=null)
          {
            if(!edpCode.trim().equalsIgnoreCase(""))
            {
              billEdpVO.setBillNo(Long.parseLong(billNo));
              billEdpVO.setEdpAmt(new BigDecimal(amount));
              billEdpVO.setVersionId(Long.parseLong("1"));
              billEdpVO.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
              billEdpVO.setDbId(SessionHelper.getDbId(p_objServiceArgs));
              billEdpVO.setAddDedFlag(addDedFlag);
              billEdpVO.setEdpCategory(edpCategory);
              billEdpVO.setEdpCode(edpCode);
              billEdpVO.setExpRcpRec("EXP");
              billEdpVO.setAutoAdd("N");
              payVOlist.add(billEdpVO);
            }
          }
        }
      }

      // Recovery

      
      String recAddDed[] =StringUtility.getParameterValues("hdRecAddDed", request);
      String recEdpCat[] =StringUtility.getParameterValues("hdRecEdpCate", request);
      String recEdpCode[] =StringUtility.getParameterValues("hdRecEdpCode", request);
      
      
      //added by Ankit Bhatt
      String tempArrayRec[] = StringUtility.getParameterValues("hdRecEdpId", request);
      
      Arrays.sort(tempArrayRec);

      int counterRec = 0;

      for (int i = 0; i < tempArrayRec.length; i++)
      {
      if (i > 0 && tempArrayRec[i].equals(tempArrayRec[i -1]))
      continue;

      tempArrayRec[counterRec++] = tempArrayRec[i];
      }

      String recEdpId[] = new String[counterRec];
      System.arraycopy(tempArrayRec, 0, recEdpId, 0, counterRec);
      
      
      
      
      
      
      
      /*logger.info("tempArrayRec is " + tempArrayRec.length);
      
      String tempArrayRec1[] =new String[tempArrayRec.length];
      
      boolean flagRec=true;
      int counterRec=-1;
      for(int i=0;i<tempArrayRec.length;i++)
      {
    	  String s = tempArrayRec[i];
    	  logger.info("S is " + s);
    	  for(int k=i+1;k<tempArrayRec.length;k++)
    	  {
    		  if(tempArrayRec[k].equals(s))
    		  {
    			  tempArrayRec[k]="";
    			 flagRec=false;
    			 break;
    		  }
    		  else
    			  flagRec=true;
    		 
    	  }
    	  if(flagRec)
    	  {
    		  counterRec++;
    		  tempArrayRec1[counterRec]=s;
    		  logger.info("recEdpId added " +  tempArrayRec1[counterRec]);
    		  
    	  }
      }
      */
      
    	  //ended by Ankit Bhatt
    	  
    	  
    	  
      List recVOlist=new ArrayList();
      for(int i=0;i<recEdpId.length;i++)
      {

//    	added by Ankit Bhatt
    	  logger.info("I is " + i);
    	  String strLocCode = SessionHelper.getLocationCode(p_objServiceArgs);
    	  com.tcs.sgv.common.valueobject.RltBillTypeEdp rltBillTypeEdp = (RltBillTypeEdp)billEdpImpl.getEdpDtls(recEdpId[i], strLocCode);
    	  //ended by Ankit Bhatt.
    	  
        String amount=StringUtility.getParameter("txtRecAmt"+rltBillTypeEdp.getEdpCode(), request);
        logger.info("Amount Is:-"+amount);
        //if(Double.parseDouble(amount)!=0)
        {
          TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
          billEdpVO=billEdpImpl.getBillEdpDtl(rltBillTypeEdp.getEdpCode(),Long.parseLong(billNo),SessionHelper.getLocationCode(p_objServiceArgs),"REC");
          logger.info("Bill EDP Vo is:-"+billEdpVO);
          if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
          {
            billEdpVO=new TrnBillEdpDtls();
            billEdpVO.setTrnCounter(new Integer(1));
          }
          //billEdpVO.setBillBudId(Long.parseLong("2"));
          billEdpVO.setTypeEdpId(Long.parseLong(recEdpId[i]));
          billEdpVO.setBillNo(Long.parseLong(billNo));
          billEdpVO.setEdpAmt(new BigDecimal(amount));
          billEdpVO.setVersionId(Long.parseLong("1"));
          billEdpVO.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
          billEdpVO.setDbId(SessionHelper.getDbId(p_objServiceArgs));
          
          
          //billEdpVO.setAddDedFlag(rltBillTypeEdp.getAddDedFlag());
          //changed by ravysh
          billEdpVO.setAddDedFlag("-");
          
          billEdpVO.setEdpCategory(rltBillTypeEdp.getEdpCategory());
          billEdpVO.setEdpCode(rltBillTypeEdp.getEdpCode());
          billEdpVO.setExpRcpRec("REC");
          billEdpVO.setAutoAdd("Y");
          recVOlist.add(billEdpVO);
        }
      }
      String recRows[] = StringUtility.getParameterValues("recRows", request);		
      for(int i=0;i<recRows.length;i++)
      {
        String reEdpCode=null;
        reEdpCode=StringUtility.getParameter("txtRecEdpCode"+recRows[i], request);
        String recAddDedFlag=StringUtility.getParameter("txtRecAddDed"+recRows[i], request);
        String recEdpCategory=StringUtility.getParameter("txtRecDedType"+recRows[i], request);
        String recAmount=StringUtility.getParameter("txtRecAmt"+recRows[i], request);
        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
        logger.info("Edp Code Is At VOGENERATOR:-"+reEdpCode+"----"+recAmount);
        if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
        {
          billEdpVO=new TrnBillEdpDtls();
          billEdpVO.setTrnCounter(new Integer(1));
        }
        if(Double.parseDouble(recAmount)!=0)
        {
          if(reEdpCode!=null)
          {
            if(!reEdpCode.trim().equalsIgnoreCase(""))
            {	
              logger.info("Edp Code Is At VOGENERATOR: Inside If:-"+reEdpCode+"----"+recAmount);
              billEdpVO.setBillNo(Long.parseLong(billNo));
              billEdpVO.setEdpAmt(new BigDecimal(recAmount));
              billEdpVO.setVersionId(Long.parseLong("1"));
              billEdpVO.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
              billEdpVO.setDbId(SessionHelper.getDbId(p_objServiceArgs));
              
              
              //billEdpVO.setAddDedFlag(recAddDedFlag);
            //changed by ravysh
              billEdpVO.setAddDedFlag("-");
              
              
              billEdpVO.setEdpCategory(recEdpCategory);
              billEdpVO.setEdpCode(reEdpCode);
              billEdpVO.setExpRcpRec("REC");
              billEdpVO.setAutoAdd("N");
              recVOlist.add(billEdpVO);
            }
          }
        }
      }

      // Receipt

      
      String rcptEdpCat[] =StringUtility.getParameterValues("hdRcptEdpCate", request);
      String rcptEdpCode[] =StringUtility.getParameterValues("hdRcptEdpCode", request);
      List rcptVOlist=new ArrayList();
      
      
      //added by Ankit Bhatt
      String tempArrayRcpt[] = StringUtility.getParameterValues("hdRcptEdpId", request);
      
      logger.info("tempArrayRec is " + tempArrayRcpt.length);
      
      
      Arrays.sort(tempArrayRcpt);

      int counterRcpt = 0;

      for (int i = 0; i < tempArrayRcpt.length; i++)
      {
      if (i > 0 && tempArrayRcpt[i].equals(tempArrayRcpt[i -1]))
      continue;

      tempArrayRcpt[counterRcpt++] = tempArrayRcpt[i];
      }

      String rcptEdpId[] = new String[counterRcpt];      
      System.arraycopy(tempArrayRcpt, 0, rcptEdpId, 0, counterRcpt);
      
      
      
      /*String tempArrayRcpt1[] =new String[tempArrayRcpt.length];
      
      boolean flagRcpt=true;
      int counterRcpt=-1;
      for(int i=0;i<tempArrayRcpt.length;i++)
      {
    	  String s = tempArrayRcpt[i];
    	  logger.info("S is " + s);
    	  for(int k=i+1;k<tempArrayRcpt.length;k++)
    	  {
    		  if(tempArrayRcpt[k].equals(s))
    		  {
    			  tempArrayRcpt[k]="";
    			  flagRcpt=false;
    			 break;
    		  }
    		  else
    			  flagRcpt=true;
    		 
    	  }
    	  if(flagRcpt)
    	  {
    		  counterRcpt++;
    		  tempArrayRcpt1[counterRcpt]=s;
    		  logger.info("payEdpId added " +  tempArrayRcpt1[counterRcpt]);
    		  
    	  }
      }
      */
      
    	  //ended by Ankit Bhatt
    
      for(int i=0;i<rcptEdpId.length;i++)
      {
    	  
//      	added by Ankit Bhatt
    	  logger.info("I is " + i);
    	  String strLocCode = SessionHelper.getLocationCode(p_objServiceArgs);
    	  com.tcs.sgv.common.valueobject.RltBillTypeEdp rltBillTypeEdp = (RltBillTypeEdp)billEdpImpl.getEdpDtls(rcptEdpId[i], strLocCode);
    	  //ended by Ankit Bhatt.
    	  
    	  
        String amount=StringUtility.getParameter("txtAmt"+rltBillTypeEdp.getEdpCode(), request);
        //if(Double.parseDouble(amount)!=0)
        {
          logger.info("Amount Is:-"+amount);
          TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
          billEdpVO=billEdpImpl.getBillEdpDtl(rltBillTypeEdp.getEdpCode(),Long.parseLong(billNo),SessionHelper.getLocationCode(p_objServiceArgs),"RCP");
          logger.info("Bill EDP Vo is:-"+billEdpVO);
          if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
          {
            billEdpVO=new TrnBillEdpDtls();
            billEdpVO.setTrnCounter(new Integer(1));
          }
          //billEdpVO.setBillBudId(Long.parseLong("2"));
          billEdpVO.setTypeEdpId(Long.parseLong(rcptEdpId[i]));
          billEdpVO.setBillNo(Long.parseLong(billNo));
          billEdpVO.setEdpAmt(new BigDecimal(amount));
          billEdpVO.setVersionId(Long.parseLong("1"));
          billEdpVO.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
          billEdpVO.setDbId(SessionHelper.getDbId(p_objServiceArgs));
          billEdpVO.setAddDedFlag("-");
          billEdpVO.setEdpCategory(rltBillTypeEdp.getEdpCategory());
          billEdpVO.setEdpCode(rltBillTypeEdp.getEdpCode());
          billEdpVO.setExpRcpRec("RCP");
          billEdpVO.setAutoAdd("Y");
          rcptVOlist.add(billEdpVO);
        }
      }
      String rcptRows[] = StringUtility.getParameterValues("rcptRows", request);		
      for(int i=0;i<rcptRows.length;i++)
      {
        String rptEdpCode=StringUtility.getParameter("txtRcptEdpCode"+rcptRows[i], request);
        String rcptEdpCategory=StringUtility.getParameter("txtRcptDedType"+rcptRows[i], request);
        String rcptAmount=StringUtility.getParameter("txtRcptAmt"+rcptRows[i], request);
        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
        if(billEdpVO==null || billEdpVO.getBillEdpId()==0)
        {
          billEdpVO=new TrnBillEdpDtls();
          billEdpVO.setTrnCounter(new Integer(1));
        }
        if(Double.parseDouble(rcptAmount)!=0)
        {
          if(rptEdpCode!=null)
          {
            if(!rptEdpCode.trim().equalsIgnoreCase(""))
            {
              billEdpVO.setBillNo(Long.parseLong(billNo));
              billEdpVO.setEdpAmt(new BigDecimal(rcptAmount));
              billEdpVO.setVersionId(Long.parseLong("1"));
              billEdpVO.setLocationCode(SessionHelper.getLocationCode(p_objServiceArgs));
              billEdpVO.setDbId(SessionHelper.getDbId(p_objServiceArgs));
              billEdpVO.setAddDedFlag("-");
              billEdpVO.setEdpCategory(rcptEdpCategory);
              billEdpVO.setEdpCode(rptEdpCode);
              billEdpVO.setExpRcpRec("RCP");
              billEdpVO.setAutoAdd("N");
              logger.info("Receipt Edp Code Is:-"+rptEdpCode);
              rcptVOlist.add(billEdpVO);
            }
          }
        }
      }
      p_objServiceArgs.put("payVOlist", payVOlist);
      p_objServiceArgs.put("recVOlist", recVOlist);
      p_objServiceArgs.put("rcptVOlist", rcptVOlist);
      retObj.setResultValue(p_objServiceArgs);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Error Occurred In VoucherVOGeneratorImpl.generateEdpDtlsMap"+e);
    }
    return retObj;
  }

  /**
   * This method to generate SUB-Treasury voucher and edp details VO. 
   * 
   * @param objectArgs 
   * @return ResultObject  
   */
  public ResultObject generateSubTrsyMap(Map p_objServiceArgs) 
  {
    ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
    HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");
    try
    {
      VoucherDAOImpl vouchDAOImpl = new VoucherDAOImpl(TrnVoucherDetails.class,serv.getSessionFactory());
      PhyBillDAOImpl phyBillDAO = new PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
      BudHeadDAOImpl budHeadImpl = new BudHeadDAOImpl(TrnBillBudheadDtls.class,serv.getSessionFactory());
      FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());

      String lstrLocCode = SessionHelper.getLocationCode(p_objServiceArgs);
      Long lLngDbId = SessionHelper.getDbId(p_objServiceArgs);
      Long lLngUserId = SessionHelper.getUserId(request);
      Long lLngPostId = SessionHelper.getPostId(p_objServiceArgs);			
      Long lLngLangId = SessionHelper.getLangId(p_objServiceArgs);
      String lStrDeptCode = "";
      List payVOlist=new ArrayList();
      List recVOlist=new ArrayList();
      List rcptVOlist=new ArrayList();
      String lStrDdoCode = (StringUtility.getParameter("DDOCode", request).length()>0) ? StringUtility.getParameter("DDOCode", request) : null;				
      String lDtBillDate = StringUtility.getParameter("txtBillDate",request);
      String lStrBillCtgry = (StringUtility.getParameter("cmbTCCtgry", request).length()>0) ? StringUtility.getParameter("cmbTCCtgry", request) : null;

      BigDecimal lBdNetAmt = (StringUtility.getParameter("txtNetAmt", request).length()>0) ? new java.math.BigDecimal(StringUtility.getParameter("txtNetAmt", request)) : null;
      BigDecimal lBdGrossAmt = (StringUtility.getParameter("txtGrossAmt", request).length()>0) ? new java.math.BigDecimal(StringUtility.getParameter("txtGrossAmt", request)) : null;

      Long lLngSubjectId = (StringUtility.getParameter("cmbBillType", request).length()>0) ? Long.parseLong(StringUtility.getParameter("cmbBillType", request)) : null;
      String lStrGoNgo = (StringUtility.getParameter("cmbGONGO", request).length()>0) ? StringUtility.getParameter("cmbGONGO", request) : null;

      String subTrsyName=StringUtility.getParameter("cmbSubTrsy", request);
      String classOfExp=StringUtility.getParameter("cmbClassExp", request);
      String typeOfBud=StringUtility.getParameter("cmbTypeOfBgt", request);
      String fund=StringUtility.getParameter("cmbFund", request);
      String majorHead=StringUtility.getParameter("cmbMajorHead", request);
      String demandNo=StringUtility.getParameter("cmbDemand", request);
      String SubMHead=StringUtility.getParameter("cmbSubMajorHead", request);
      String schemeNo=StringUtility.getParameter("txtSchemeCode", request);
      String minorHead=StringUtility.getParameter("cmbMinorHead", request);
      String headCrg=StringUtility.getParameter("txtHeadChar", request);
      String subHead=StringUtility.getParameter("cmbSubHead", request);
      String detailHead=StringUtility.getParameter("cmbDetailHead", request);
      Long lngBillNO = null;
      logger.info("billNo is :-"+request.getParameter("BillNo"));
      TrnBillRegister lObjBillRegister=null;
      if(request.getParameter("BillNo")!=null && !request.getParameter("BillNo").equals(""))
      {
        lngBillNO = Long.parseLong(request.getParameter("BillNo"));
        logger.info("Inside The null condition"+lngBillNO);
        lObjBillRegister = phyBillDAO.read(lngBillNO); 
      }
      else
      {
        lngBillNO = BptmCommonServiceImpl.getNextSeqNum("trn_bill_register", p_objServiceArgs);
        logger.info("Inside create key condition");
        lObjBillRegister=new TrnBillRegister();
        lObjBillRegister.setBillNo(lngBillNO);
      }

      lObjBillRegister.setBillDate(new SimpleDateFormat("dd/MM/yyyy").parse(lDtBillDate));
      lObjBillRegister.setSubjectId(lLngSubjectId);
      lObjBillRegister.setPhyBill(1);
      lObjBillRegister.setDemandCode(demandNo);
      lObjBillRegister.setBudmjrHd(majorHead);
      lObjBillRegister.setDdoCode(lStrDdoCode);
      lObjBillRegister.setTcBill(lStrBillCtgry);
      lObjBillRegister.setBillNetAmount(lBdNetAmt);
      lObjBillRegister.setBillGrossAmount(lBdGrossAmt);
      lObjBillRegister.setGoNgo(lStrGoNgo);		
      if(request.getParameter("txtDepartment")!=null)
        if(!request.getParameter("txtDepartment").toString().equalsIgnoreCase("-1"))
          lObjBillRegister.setDdoDeptId(request.getParameter("txtDepartment"));
      lObjBillRegister.setVersionId(Long.parseLong("1"));
      if(demandNo!=null)
        lStrDeptCode = BptmCommonServiceImpl.getDeptByDemand(demandNo, lLngLangId, lLngDbId, serv);
      if(lStrDeptCode!=null)
        lObjBillRegister.setDeptCode(lStrDeptCode);

      lObjBillRegister.setTrnCounter(new Integer(1));			
      lObjBillRegister.setCreatedUserId(lLngUserId);
      lObjBillRegister.setCreatedPostId(lLngPostId);
      lObjBillRegister.setCreatedDate(new Date());
      lObjBillRegister.setTsryOfficeCode(subTrsyName);
      lObjBillRegister.setLocationCode(subTrsyName);
      lObjBillRegister.setDbId(lLngDbId);
      Integer inFinYearId = lObjFinYearDAOImpl.getFinYearIdByCurDate();
      lObjBillRegister.setFinYearId(inFinYearId.toString());

      p_objServiceArgs.put("billRegVO",lObjBillRegister);
      p_objServiceArgs.put("BillNo",request.getParameter("BillNo"));
      Long lngBillBudId = null;
      TrnBillBudheadDtls lObjBudHeadVo = null;
      if(request.getParameter("BillNo")!=null && !request.getParameter("BillNo").equals(""))
      {
        lngBillBudId = vouchDAOImpl.getBudHeadId(lngBillNO);
        lObjBudHeadVo = budHeadImpl.read(lngBillBudId);
      }
      else
      {
        lngBillBudId = BptmCommonServiceImpl.getNextSeqNum("trn_bill_budhead_dtls", p_objServiceArgs);
        lObjBudHeadVo = new TrnBillBudheadDtls();
        lObjBudHeadVo.setBillBudId(lngBillBudId);
      }

      lObjBudHeadVo.setBillNo(lngBillNO);
      lObjBudHeadVo.setClsExp(classOfExp);
      if (typeOfBud!=null) lObjBudHeadVo.setBudType(typeOfBud);
      lObjBudHeadVo.setFund(fund);
      lObjBudHeadVo.setBudMjrHd(majorHead);
      lObjBudHeadVo.setDmndNo(demandNo);
      lObjBudHeadVo.setBudSubmjrHd(SubMHead);
      lObjBudHeadVo.setSchemeNo(schemeNo);
      lObjBudHeadVo.setBudMinHd(minorHead);
      lObjBudHeadVo.setHeadChrg(headCrg);
      lObjBudHeadVo.setBudSubHd(subHead);
      lObjBudHeadVo.setBudDtlHd(detailHead);
      lObjBudHeadVo.setTrnCounter(new Integer(1));	

      lObjBudHeadVo.setCreatedUserId(lLngUserId);
      lObjBudHeadVo.setCreatedPostId(lLngPostId);
      lObjBudHeadVo.setCreatedDate(new Date());

      lObjBudHeadVo.setLocationCode(subTrsyName);
      lObjBudHeadVo.setDbId(lLngDbId);

      p_objServiceArgs.put("budHeadVo",lObjBudHeadVo);

      Long lngVouchId = null;
      TrnVoucherDetails lObjVoucherDtls = null;

      if(request.getParameter("BillNo")!=null && !request.getParameter("BillNo").equals(""))
      {
        lngVouchId = vouchDAOImpl.getVouchDtlId(lngBillNO);
        lObjVoucherDtls = vouchDAOImpl.read(lngVouchId);
      }
      else
      {
        TrnVoucherDetails lObjVouDtls = vouchDAOImpl.getNextVoucherMjrHdWise(lngBillNO.toString(), subTrsyName);
        lngVouchId = BptmCommonServiceImpl.getNextSeqNum("trn_voucher_details", p_objServiceArgs);
        lObjVoucherDtls = new TrnVoucherDetails();
        lObjVoucherDtls.setVoucherDetailId(lngVouchId);
        lObjVoucherDtls.setVoucherNo(lObjVouDtls.getVoucherNo());
      }
      lObjVoucherDtls.setVoucherDate(new Date());
      lObjVoucherDtls.setBillNo(lngBillNO);
      lObjVoucherDtls.setMajorHead(majorHead);
      lObjVoucherDtls.setCreatedUserId(lLngUserId);
      lObjVoucherDtls.setCreatedPostId(lLngPostId);
      lObjVoucherDtls.setCreatedDate(new Date());
      lObjVoucherDtls.setLocationCode(subTrsyName);
      lObjVoucherDtls.setDbId(lLngDbId);
      lObjVoucherDtls.setDistributed(Short.parseShort("1"));

      p_objServiceArgs.put("voucherDtlsVO",lObjVoucherDtls);

      String expRows[] = StringUtility.getParameterValues("exprows", request);		
      for(int i=0;i<expRows.length;i++)
      {
        String edpCode=StringUtility.getParameter("txtEdpCode"+expRows[i], request);
        String addDedFlag=StringUtility.getParameter("txtAddDed"+expRows[i], request);
        String edpCategory=StringUtility.getParameter("txtDedType"+expRows[i], request);
        String amount=StringUtility.getParameter("txtAmt"+expRows[i], request);
        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
        if(Double.parseDouble(amount)!=0)
        {
          if(edpCode!=null)
          {
            if(!edpCode.trim().equalsIgnoreCase(""))
            {
              billEdpVO.setBillNo(lngBillNO);
              billEdpVO.setEdpAmt(new BigDecimal(amount));
              billEdpVO.setVersionId(Long.parseLong("1"));
              billEdpVO.setLocationCode(subTrsyName);
              billEdpVO.setDbId(lLngDbId);
              billEdpVO.setAddDedFlag(addDedFlag);
              billEdpVO.setEdpCategory(edpCategory);
              billEdpVO.setEdpCode(edpCode);
              billEdpVO.setExpRcpRec("EXP");
              billEdpVO.setAutoAdd("N");
              billEdpVO.setTrnCounter(1);
              payVOlist.add(billEdpVO);
            }
          }
        }
      }

      String recRows[] = StringUtility.getParameterValues("recRows", request);		
      for(int i=0;i<recRows.length;i++)
      {
        String reEdpCode=null;
        reEdpCode=StringUtility.getParameter("txtRecEdpCode"+recRows[i], request);
        String recAddDedFlag=StringUtility.getParameter("txtRecAddDed"+recRows[i], request);
        String recEdpCategory=StringUtility.getParameter("txtRecDedType"+recRows[i], request);
        String recAmount=StringUtility.getParameter("txtRecAmt"+recRows[i], request);
        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
        logger.info("Edp Code Is At VOGENERATOR:-"+reEdpCode+"----"+recAmount);

        if(Double.parseDouble(recAmount)!=0)
        {
          if(reEdpCode!=null)
          {
            if(!reEdpCode.trim().equalsIgnoreCase(""))
            {	
              logger.info("Edp Code Is At VOGENERATOR: Inside If:-"+reEdpCode+"----"+recAmount);
              billEdpVO.setBillNo(lngBillNO);
              billEdpVO.setEdpAmt(new BigDecimal(recAmount));
              billEdpVO.setVersionId(Long.parseLong("1"));
              billEdpVO.setLocationCode(subTrsyName);
              billEdpVO.setDbId(lLngDbId);
              billEdpVO.setAddDedFlag(recAddDedFlag);
              billEdpVO.setEdpCategory(recEdpCategory);
              billEdpVO.setEdpCode(reEdpCode);
              billEdpVO.setExpRcpRec("REC");
              billEdpVO.setTrnCounter(1);
              billEdpVO.setAutoAdd("N");
              recVOlist.add(billEdpVO);
            }
          }
        }
      }

      String rcptRows[] = StringUtility.getParameterValues("rcptRows", request);		
      for(int i=0;i<rcptRows.length;i++)
      {
        String rptEdpCode=StringUtility.getParameter("txtRcptEdpCode"+rcptRows[i], request);
        String rcptEdpCategory=StringUtility.getParameter("txtRcptDedType"+rcptRows[i], request);
        String rcptAmount=StringUtility.getParameter("txtRcptAmt"+rcptRows[i], request);
        TrnBillEdpDtls billEdpVO=new TrnBillEdpDtls();
        if(Double.parseDouble(rcptAmount)!=0)
        {
          if(rptEdpCode!=null)
          {
            if(!rptEdpCode.trim().equalsIgnoreCase(""))
            {
              billEdpVO.setBillNo(lngBillNO);
              billEdpVO.setEdpAmt(new BigDecimal(rcptAmount));
              billEdpVO.setVersionId(Long.parseLong("1"));
              billEdpVO.setLocationCode(subTrsyName);
              billEdpVO.setDbId(lLngDbId);
              billEdpVO.setAddDedFlag("-");
              billEdpVO.setEdpCategory(rcptEdpCategory);
              billEdpVO.setEdpCode(rptEdpCode);
              billEdpVO.setExpRcpRec("RCP");
              billEdpVO.setTrnCounter(1);
              billEdpVO.setAutoAdd("N");
              logger.info("Receipt Edp Code Is:-"+rptEdpCode);
              rcptVOlist.add(billEdpVO);
            }
          }
        }
      }
      p_objServiceArgs.put("payVOlist", payVOlist);
      p_objServiceArgs.put("recVOlist", recVOlist);
      p_objServiceArgs.put("rcptVOlist", rcptVOlist);

      retObj.setResultValue(p_objServiceArgs);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Error Occurred In VoucherVOGeneratorImpl.generateSubTrsyMap"+e);
    }
    return retObj;
  }

  /**
   * This method to generate LOP/LOR details vo 
   * 
   * @param objectArgs 
   * @return ResultObject  
   */
  public ResultObject generateLopLorMap(Map p_objServiceArgs) 
  {
    ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
    HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");
    ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");
    try
    {
      List lstFormula = new ArrayList();
      String strFormulaId[] = StringUtility.getParameterValues("chkFormula", request);
      logger.info("inside th generate map"+strFormulaId.length);
      if(strFormulaId!=null)
      {
        for(int iCounter=0;iCounter<strFormulaId.length;iCounter++)
        {

          String strFormula = StringUtility.getParameter(strFormulaId[iCounter], request);
          MstListPayRcpt lObjlstPayRcpt=new MstListPayRcpt();
          lObjlstPayRcpt.setId(new BigDecimal(strFormulaId[iCounter]));
          lObjlstPayRcpt.setFormula(strFormula);
          lstFormula.add(lObjlstPayRcpt);
        }
      }
      p_objServiceArgs.put("lstFormula", lstFormula);
      retObj.setResultValue(p_objServiceArgs);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      logger.error("Error Occurred In VoucherVOGeneratorImpl.generateLopLorMap"+e);
    }
    return retObj;
  }
}
