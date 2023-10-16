package com.tcs.sgv.exprcpt.helper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.exprcpt.valueobject.BudHdAmtVO;
import com.tcs.sgv.exprcpt.valueobject.MstListPayRcpt;

import edu.emory.mathcs.backport.java.util.TreeSet;

/**
 * A class for report related computation.
 * @author Jignesh Sakhiya
 * @author 219480
 * @version 1.0.1
 */
public class ReportHelper
{

  public static Log log = LogFactory.getLog(ReportHelper.class);


  /**
   * This method generate list of payment/receipt report
   * 
   * @param payList
   * @param rptMtData
   */
  public static void procLstOfPayRcpt(List payList, List rptMtData)
  {
    try
    {
      Map<Integer, String> totsIndx = new HashMap<Integer, String>();
      Map<String, BigDecimal> totsVals = new HashMap<String, BigDecimal>();

      if (payList != null && rptMtData != null)
      {
        for (int i = 0; i < rptMtData.size(); i++)
        {
          MstListPayRcpt lstPayRcpt = (MstListPayRcpt) rptMtData.get(i);

          if (lstPayRcpt.getIsFormula() == 'Y'
            && lstPayRcpt.getFormula() != null)
          {
            for (int j = 0; j < payList.size(); j++)
            {
              BudHdAmtVO budHdAmtVO = (BudHdAmtVO) payList.get(j);
              if (budHdAmtVO.getFormula().startsWith(lstPayRcpt.getFormula())
                  && budHdAmtVO.getAmount() != null)
              {
                if (lstPayRcpt.getAmount() != null)
                  lstPayRcpt.setAmount(lstPayRcpt.getAmount().add(
                      budHdAmtVO.getAmount()));
                else
                  lstPayRcpt.setAmount(budHdAmtVO.getAmount());
                if (lstPayRcpt.getBelongsTo() != null)
                {
                  BigDecimal total = (BigDecimal) totsVals.get(lstPayRcpt
                      .getBelongsTo());
                  if (total == null)
                    total = new BigDecimal("0.0");

                  total = total.add(budHdAmtVO.getAmount());
                  totsVals.put(lstPayRcpt.getBelongsTo(), total);
                }
              }
            }
            /** end of inner for */
          }
          if (lstPayRcpt.getTotal() == 'Y')
          {
            totsIndx.put(i, lstPayRcpt.getFormula());
          }
        }
        /** end of for */
      }
      /** end of if */

      /** process group total .. */
      Iterator itTotsIndx = new TreeSet(totsIndx.keySet()).iterator();
      HashMap<String, BigDecimal> oGrandTotal = new HashMap();
      while (itTotsIndx.hasNext())
      {
        Integer indx = (Integer) itTotsIndx.next();
        if (indx != null)
        {
          String formula = (String) totsIndx.get(indx);
          if (formula != null)
          {
            formula = formula.replace("+", ":");
            String tots[] = formula.split(":");
            BigDecimal sum = new BigDecimal("0.0");
            if (tots != null)
            {
              for (int i = 0; i < tots.length; i++)
              {
                BigDecimal val = (BigDecimal) totsVals.get(tots[i]);
                if (val != null)
                {
                  sum = sum.add(val);
                }
              }
            }
            MstListPayRcpt lstPayRcpt = (MstListPayRcpt) rptMtData.get(indx);
            if (lstPayRcpt.getBelongsTo() != null)
            {
              if (oGrandTotal.containsKey(lstPayRcpt.getBelongsTo()))
              {
                BigDecimal oAmount = (BigDecimal) oGrandTotal.get(lstPayRcpt
                    .getBelongsTo());
                oAmount = oAmount.add(sum);
                oGrandTotal.put(lstPayRcpt.getBelongsTo(), oAmount);
              }
              else
              {
                BigDecimal oAmount = new BigDecimal("0.0");
                oAmount = oAmount.add(sum);
                oGrandTotal.put(lstPayRcpt.getBelongsTo(), oAmount);
              }
            }
            if (lstPayRcpt.getFormula() != null
                && lstPayRcpt.getFormula().equals("1"))
              lstPayRcpt.setAmount((BigDecimal) oGrandTotal.get("1"));
            else if (lstPayRcpt != null)
              lstPayRcpt.setAmount(sum);
            // //System.out.println("----"+ indx + " - "+ lstPayRcpt.getText() + "
            // - " + lstPayRcpt.getFormula() + " . " + lstPayRcpt.getAmount());
            // if(lstPayRcpt.getFormula().equals("A"))
            // //System.out.println("----"+ indx + " - "+ lstPayRcpt.getFormula()
            // + " . " + lstPayRcpt.getAmount());
          }
        }
      }
      /** end of while(itTotsIndx.hasNext()) */
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      log.error("Exception occured # \n" + ex);
    }
  }


  /**
   * This method generate list of payment/receipt report
   * 
   * @param payList
   * @param rptMtData
   */
  public static void procCashBookPayRec(List payList, List rptMtData,
      List DateRange)
  {
    try
    {
      Map totsIndx = new HashMap();
      Map<Object, HashMap> totsVals = new HashMap();

      if (payList != null && rptMtData != null)
      {
        for (int i = 0; i < rptMtData.size(); i++)
        {
          MstListPayRcpt lstPayRcpt = (MstListPayRcpt) rptMtData.get(i);

          if (lstPayRcpt.getIsFormula() == 'Y'
            && lstPayRcpt.getFormula() != null)
          {
            for (int j = 0; j < payList.size(); j++)
            {
              BudHdAmtVO budHdAmtVO = (BudHdAmtVO) payList.get(j);
              if (budHdAmtVO.getFormula().startsWith(lstPayRcpt.getFormula()))
              {
                HashMap hm = (HashMap) lstPayRcpt.getHmDateValues();
                HashMap amtMap = (HashMap) budHdAmtVO.getHashMap();
                Iterator itr = amtMap.keySet().iterator();

                while (itr.hasNext())
                {
                  Object Key = itr.next();
                  if (hm.containsKey(Key))
                  {
                    BigDecimal val = (BigDecimal) hm.get(Key);
                    val.add((BigDecimal) amtMap.get(Key));
                    hm.put(Key, val);
                  }
                  else
                  {
                    hm.put(Key, (BigDecimal) amtMap.get(Key));
                  }
                }
                lstPayRcpt.setHmDateValues(hm);

                if (lstPayRcpt.getBelongsTo() != null)
                {
                  itr = amtMap.keySet().iterator();
                  if (totsVals.containsKey(lstPayRcpt.getBelongsTo()))
                  {
                    HashMap<Object, BigDecimal> totals = (HashMap) totsVals
                    .get(lstPayRcpt.getBelongsTo());
                    while (itr.hasNext())
                    {
                      Object Key = itr.hasNext();
                      if (totals.containsKey(Key))
                      {
                        BigDecimal total = (BigDecimal) totals.get(Key);
                        totals
                        .put(Key, total.add((BigDecimal) amtMap.get(Key)));
                      }
                      else
                        totals.put(Key, (BigDecimal) amtMap.get(Key));
                    }
                  }
                  else
                    totsVals.put(lstPayRcpt.getBelongsTo(), amtMap);
                }
              }
            }
            /** end of inner for */
          }
          if (lstPayRcpt.getTotal() == 'Y')
          {
            totsIndx.put(i, lstPayRcpt.getFormula());
          }
        }
        /** end of for */
      }
      /** end of if */

      /** process group total .. */
      TreeSet ts = new TreeSet(totsIndx.keySet());
      Iterator itTotsIndx = ts.iterator();
      HashMap GrandTotal = new HashMap();

      while (itTotsIndx.hasNext())
      {
        Integer indx = (Integer) itTotsIndx.next();

        if (indx != null)
        {
          MstListPayRcpt lstPayRcpt = (MstListPayRcpt) rptMtData.get(indx);
          String formula = (String) totsIndx.get(indx);
          String sBelongsTo = (lstPayRcpt.getBelongsTo() != null ? lstPayRcpt
              .getBelongsTo() : "");

          if (formula != null)
          {
            formula = formula.replace("+", ":");
            String tots[] = formula.split(":");
            HashMap subTotal = new HashMap();
            if (tots != null)
            {
              for (int i = 0; i < tots.length; i++)
              {
                HashMap values = (HashMap) totsVals.get(tots[i]);
                if (values != null)
                {
                  Iterator itr = values.keySet().iterator();
                  while (itr.hasNext())
                  {
                    Object Key = itr.next();
                    if (subTotal.containsKey(Key))
                    {
                      BigDecimal val = (BigDecimal) values.get(Key);
                      BigDecimal PreVal = (BigDecimal) subTotal.get(Key);
                      if (val != null)
                        PreVal = PreVal.add(val);
                      subTotal.put(Key, PreVal);
                    }
                    else
                    {
                      subTotal.put(Key, values.get(Key));
                    }
                  } // while
                } // if
              } // for loop
            }
            if (sBelongsTo != null && !sBelongsTo.equals(""))
            {
              Iterator ite = subTotal.keySet().iterator();
              TreeMap oValues = null;
              if (GrandTotal.containsKey(sBelongsTo))
                oValues = (TreeMap) GrandTotal.get(sBelongsTo);
              else
                oValues = new TreeMap();

              while (ite.hasNext())
              {
                Object Key = ite.next();
                if (oValues.containsKey(Key))
                {
                  BigDecimal val = (BigDecimal) oValues.get(Key);
                  val = val.add((BigDecimal) subTotal.get(Key));
                  oValues.put(Key, val);
                }
                else
                {
                  oValues.put(Key, (BigDecimal) subTotal.get(Key));
                }
              }
              GrandTotal.put(sBelongsTo, oValues);
            }
            if (lstPayRcpt != null)
              lstPayRcpt.setHmDateValues(subTotal);
          }
        } // index not null
      }
      /** end of while(itTotsIndx.hasNext()) */

      Iterator itr = rptMtData.iterator();
      while (itr.hasNext())
      {

        MstListPayRcpt objListPayRcpt = (MstListPayRcpt) itr.next();
        TreeMap tm = new TreeMap();
        HashMap hm = (HashMap) objListPayRcpt.getHmDateValues();
        BigDecimal gTotal = new BigDecimal("0.0");
        for (int i = 0; i < DateRange.size(); i++)
        {
          Object Key = DateRange.get(i);
          if (hm.containsKey(Key))
          {
            BigDecimal oTotal = (BigDecimal) hm.get(Key);
            gTotal = gTotal.add(oTotal);
            tm.put(Key, oTotal);
          }
          else
            tm.put(Key, new BigDecimal("0.0"));
        }
        tm.put("Total", gTotal);
        if (objListPayRcpt.getFormula() != null
            && objListPayRcpt.getFormula().equals("1"))
          objListPayRcpt.setHmDateValues((Map) GrandTotal.get(objListPayRcpt
              .getFormula()));
        else
          objListPayRcpt.setHmDateValues(tm);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      log.error("Exception occured # \n" + ex);
    }
  }
}
