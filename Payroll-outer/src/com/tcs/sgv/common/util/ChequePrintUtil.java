
package com.tcs.sgv.common.util;
import java.util.ArrayList;

import com.tcs.sgv.billproc.cheque.valueobject.TrnChequeDtls;

public class ChequePrintUtil 
{
  public ChequePrintUtil()
  {
  }
  
  EnglishDecimalFormat edf = new EnglishDecimalFormat();
  
  public String generatePDF(ArrayList lArrChequeList,ArrayList tokenList)
  {    
	StringBuffer sb = new StringBuffer();
	String lStrResult ="";
    try 
    {
        for(int i=0;i<lArrChequeList.size();i++)
        {
        	String table = generateChequeTemplate((TrnChequeDtls)lArrChequeList.get(i),(String)tokenList.get(i));
        	sb.append(table);
        }
        if(sb.toString().length() >0)
        {
        	lStrResult = sb.toString().substring(0,sb.length()-5);
        }
    }
    catch (Exception de) 
    {
      de.printStackTrace();
    }
    
    return lStrResult;
  }
  
  
  public String generateChequeTemplate(TrnChequeDtls chequeVO,String token)
  {
    StringBuffer sb = new StringBuffer();
    String sBlank ="                                                                      ";
    /*int nLable1 = 7;
    int nLable2 = 13 ;
    int nLable3 = 11;
    int nLable4 = 12;
    int nLable5 = 62;
    int nLable4_1= 6;
    int nLable6 = 20; */
    int nLable1 = 4;
    int nLable2 = 10 ;
    int nLable3 = 8;
    int nLable4 = 9;
    int nLable5 = 59;
    int nLable6 = 17;

    String LineSep = "\r\n";
    try 
    {      
    	sb.append("." +LineSep); //1
    	sb.append(LineSep); //2

      if(chequeVO.getDdoParty() != null && chequeVO.getDdoParty().equals("PARTY") )
      {
    	  sb.append(sBlank.substring(0,nLable1) + "------------");sb.append(LineSep); //3
          sb.append(sBlank.substring(0,nLable1) + "A/c. Payee Only ");sb.append(LineSep); //4
          sb.append(sBlank.substring(0,nLable1) + "------------");sb.append(LineSep); //5
          //System.out.println(" Came in Party cheque  ");
      }
      else if(chequeVO.getDdoParty() != null && !chequeVO.getDdoParty().equals("PARTY") )
      {
    	  //System.out.println(" Came in DDO cheque  ");
    	  sb.append(LineSep);sb.append(LineSep);sb.append(LineSep);
      }
      
      //sb.append(LineSep);  //6
      //  FIELD - UNDER RS.
      //  ------------------
      
      String lUnderAmt = edf.convert(chequeVO.getChequeAmt().intValue() + 1)+" Only.";
      
      if(lUnderAmt.length() > 47 )
      {
    	  int index = lUnderAmt.indexOf(" ",37);
    	  if(index > 0)
    	  {
             String str1 = lUnderAmt.substring(0,index);
             String str2 = lUnderAmt.substring(index).trim();
    		  sb.append(sBlank.substring(0,nLable2)+ String.format("%-48s",str1 +"-" ));                  
              sb.append(LineSep); //6
              sb.append(sBlank.substring(0,nLable2)+ String.format("%-48s",str2 ));
              sb.append(LineSep); //7
    	  }
    	  else
    	  {
    		  sb.append(sBlank.substring(0,nLable2)+ String.format("%-48s",lUnderAmt ));                  
    	      sb.append(LineSep);sb.append(LineSep); //6,7
    		  
    	  }	 
      }
      else
      {
            sb.append(sBlank.substring(0,nLable2)+ String.format("%-48s",lUnderAmt ));                  
            sb.append(LineSep);sb.append(LineSep); //6,7
      }
    //  sb.append(LineSep);  //8
      sb.append(sBlank.substring(0,nLable3) + chequeVO.getPartyName());
      
      sb.append(LineSep);  //8
      sb.append(LineSep); //9
      //sb.append(LineSep); //10
      
      String lStrPartyAmt = edf.convert(chequeVO.getChequeAmt().intValue()) + " Only";
      if(lStrPartyAmt.length() > 60)
      {
    	  int index = lUnderAmt.substring(0,59).lastIndexOf(" ");
    	  if(index > 0)
    	  {
             String str1 = lStrPartyAmt.substring(0,index);
             String str2 = lStrPartyAmt.substring(index).trim();
             sb.append(sBlank.substring(0,nLable4) + str1 );   
             sb.append(LineSep); //10
             sb.append(sBlank.substring(0,nLable5) + "**" + String.valueOf(chequeVO.getChequeAmt() )+"/-");
             sb.append(LineSep); //11
             sb.append(sBlank.substring(0,nLable4) + str2 );
             
    	  }
    	  else
    	  {
    		  sb.append(sBlank.substring(0,nLable4) + lStrPartyAmt);   
    	      sb.append(LineSep); //10
    	      sb.append(sBlank.substring(0,nLable5) + "**" + String.valueOf(chequeVO.getChequeAmt() )+"/-");
    	      sb.append(LineSep); //11
    	  }
      }
      else
      {
    	sb.append(sBlank.substring(0,nLable4) + lStrPartyAmt);   
      	sb.append(LineSep); //10
      	sb.append(sBlank.substring(0,nLable5) + "**" + String.valueOf(chequeVO.getChequeAmt() )+"/-");
      	sb.append(LineSep); //11
      }
      sb.append(LineSep); //12
      sb.append(LineSep); //13
      sb.append(LineSep); //14
      sb.append(sBlank.substring(0,nLable6)+  token); //15
      sb.append(LineSep); //16
      sb.append(LineSep); //17
      sb.append(LineSep); //18
      sb.append(LineSep); //19
      sb.append(LineSep); //20 
      sb.append(LineSep); //21
      sb.append(LineSep); //22 
      sb.append(LineSep); //23
      sb.append(LineSep); //24
      char c = '\u000c';
      sb.append(c);
     // sb.append(LineSep); //25
    //  sb.append(LineSep); //26
    //  sb.append(LineSep); //27
    }
    catch (Exception de) 
    {
      de.printStackTrace();
    }
    return sb.toString();
  }
}