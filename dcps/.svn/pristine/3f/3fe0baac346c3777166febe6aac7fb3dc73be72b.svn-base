<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.ArrayList,
                 java.util.Hashtable,
                 java.util.Date, 
                 java.util.Enumeration,
                 java.text.SimpleDateFormat,
                 org.apache.log4j.Logger,
                 java.lang.String,
                 java.util.Locale,
                 java.util.ResourceBundle,
                 java.util.StringTokenizer,
                 com.tcs.sgv.common.util.VerificationUtil,
                 com.tcs.sgv.common.valuebeans.DigiSignDetailsVO "%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>
Signature Verification Details
</title>
<!-- <style type="text/css">@import url("Reports/style/WebGUIStandards.css");</style>
<style type="text/css">@import url("Reports/style/TableBorders.css");</style> -->
<style type="text/css">@import url(/IWAS/APPS/style/WebGUIStandards.css);</style>
<style type="text/css">@import url(/IWAS/APPS/style/TableBorders.css);</style>

<style type="text/css">
.VerifyLabel {background: #E3E3E3}
.TableBorderLTRBN {border-top-style : solid ; border-top-color :"#375472"; border-top-width: 1px;border-bottom-style : solid ; border-bottom-color :"#375472"; border-bottom-width: 1px;border-left-style : solid ; border-left-color :"#375472"; border-left-width: 1px;border-right-style : solid ; border-right-color :"#375472"; border-right-width: 1px;}
</style>
</head>
<body>
<table cellpadding=0 cellspacing=0 width = 90% align = center  class="TableBorderLTRBN"> <!-- class="TableBorderLTRBN" -->
<tr bgcolor="#AEBAC6"><TD align=center class="Label"><b>Data Verification Status</b></td></tr>
</table>
<%
  try{
  System.out.println("We are here for signature Verification");
  //ArrayList DigNotings = (ArrayList)session.getAttribute("DigNotings");
  Hashtable hashSigned = (Hashtable)session.getAttribute("hashSigned");
  Enumeration enumKeys = null;
  if(hashSigned!=null) enumKeys = hashSigned.keys();
  
  //for(int i=0;i<DigNotings.size();i++)
  if(enumKeys!=null)
  while(enumKeys.hasMoreElements())
  {
      System.out.println("Inside while loop");
     //DigiSignVO digSignVO= (DigiSignVO)DigNotings.get(i);
     DigiSignDetailsVO digSignVO = (DigiSignDetailsVO)hashSigned.get(enumKeys.nextElement());
%>
    <table class="TableBorderLTRBN" cellpadding=0 cellspacing=0 width = 90% align = center >
    <Br></br>
    <tr><TD class="Label3 VerifyLabel TableBorderRBN" align=center><b>Signer Name</b></td>
    <TD class="Label3 TableBorderBN" align=center>
    <%--<% if(!digSignVO.getIsDataTempered()) { %>--%>
    <%=digSignVO.getSignerName()%>
    <%--<% } %>--%>
    </td>
    </tr>
    
    <tr><TD class="Label3 VerifyLabel TableBorderRBN" align=center><b>Field Name</b></td>
    <TD class="Label3 TableBorderBN" align=center>
    <%=digSignVO.getFieldName()%>
    </td>
    </tr>
    
    <!--<tr><B><TD class="Label3 VerifyLabel TableBorderRBN" align=center><b>Signer MessageContent</td></b><TD class="Label3 TableBorderBN" align=center><%=digSignVO.getMessageContent()%></td></tr>-->
    <tr><TD class="Label3 VerifyLabel TableBorderRBN" align=center><b>Verification Status</b></td>
<% if(digSignVO.getIsDataTempered()){
%><td  class="Label TableBorderBN"  align="center">
    <table border=0>
    <tr>
      <td width=30% align=right><img src="images/tampered.gif"></td>
      <td align=left>Data has been Tampered</td>
    </tr>
    <tr>
      <td width=30% align=right><img src="images/tampered.gif"></td>
      <td align=left>Signer Certificate might not be Valid</td>
    </tr>
    </table>
</td>
<% }else{%>
<td  class="Label TableBorderBN"  align=center valign=center>
    <table border=0>
    <tr>
      <td width=30% align=right><img src="images/tick.jpg"></td>
      <td align=left>Data not been Tampered</td>
    </tr>
    <tr>
      <td width=30% align=right><img src="images/tick.jpg"></td>
      <td align=left>Signer Certificate is Valid</td>
    </tr>
    </table>
</td>
<%}%>
</tr>
    <%--<tr><TD class="Label3 VerifyLabel TableBorderBN" align=center><b>Signer SubjectDN</td></b><TD class="Label3 VerifyLabel TableBorderBN" align=center>&nbsp;</td></tr>
    <tr><td colspan ="2"><table cellpadding=0 cellspacing=0 width =70%>
    
    <%
      if(digSignVO.getSubjectDN()!=null && !digSignVO.getIsDataTempered())
      {
      StringTokenizer lstrtok1 = new StringTokenizer(digSignVO.getSubjectDN(),",",false);
      
      while(lstrtok1.hasMoreTokens())
      {
        String lstrdetail = lstrtok1.nextToken();
        System.out.println("The token is " + lstrdetail);
        VerificationUtil vu = new VerificationUtil();
        String[] fullDetails = vu.getFromCertificateDetail(lstrdetail);
        %>
        <tr>
      <TD class="Label3" align="right" width=50% valign=top>
        <%=fullDetails[0]%>:
      </td>
      <TD class="Label3" align="left" width=50%>
        <%=fullDetails[1]%>
      </td>
      </tr>
        
  <% }
    }
    %>
 
    </table>
    </td>
    </tr>
    <tr><TD class="Label3 VerifyLabel TableBorderTBN" align=center><b>Signer IssuerDN</td></b><TD class="Label3 VerifyLabel TableBorderTBN" align=center>&nbsp;</td></tr>
    <tr><td colspan ="2"><table cellpadding=0 cellspacing=0 width =70%>
    
    <%
      if(digSignVO.getIssuerDN()!=null && !digSignVO.getIsDataTempered())
      {
      StringTokenizer lstrtok2 = new StringTokenizer(digSignVO.getIssuerDN(),",",false);
      
      while(lstrtok2.hasMoreTokens())
      {
        String lstrdetail = lstrtok2.nextToken();
        System.out.println("The token is " + lstrdetail);
        VerificationUtil vu = new VerificationUtil();
        String[] fullDetails = vu.getFromCertificateDetail(lstrdetail);
        %>
        <tr>
      <TD class="Label3" align="right" width=50% valign=top>
        <%=fullDetails[0]%>:
      </td>
      <TD class="Label3" align="left" width=50%>
        <%=fullDetails[1]%>
      </td>
      </tr>
        
  <% }
    }
    %>
 
    </table>
    </td>
    </tr>
    <tr><TD class="Label3 VerifyLabel TableBorderTRBN" align=center><b>Signer SerialNo</td></b>
    <TD class="Label3 TableBorderTBN" align=center>
    <% if(!digSignVO.getIsDataTempered()) { %>
    <%=digSignVO.getSerialNo()%>
    <% } %>
    </td>
    </tr>
    --%>
    
</table>
<%
  }
%>
<h2>
</h2>
</body>
</html>
<% } catch (Exception e)
{
    e.printStackTrace();
  }
  %>