<%
/*
* Filename	  : ReportColumnRules.jsp
* Purpose	  : 
* @VERSION    : 1.0
* @AUTHOR     : Samir Vadariya(156957)
* Date        : 20-Mar-2006
* 
* REV. HISTORY:
*
*-----------------------------------------------------------------------------
* DATE        AUTHOR                    DESCRIPTION
* 
*-----------------------------------------------------------------------------
*/
%>
<%@include file="rptcommon.jsp"%>
<%@page language="java" import="com.tcs.sgv.common.util.reports.*,
                                com.tcs.sgv.common.valuebeans.reports.*,
                                com.tcs.sgv.valuebeans.re.ConditionVO,
                                java.util.Collection,
                                java.util.Locale,
                                java.util.ResourceBundle,
                                java.util.Iterator,
                                java.util.ArrayList,
                                java.util.Hashtable" %>

<%
try
{
    ReportVO reportVO = null;
    Locale userLocale = null;
    String lStrQuery = "";
    ArrayList conditionList = new ArrayList();
    if (session.getAttribute("LocaleID") != null && !(session.getAttribute("LocaleID").equals("null")) )
      userLocale = (Locale) session.getAttribute("LocaleID") ;
    else
      userLocale = request.getLocale();
    
    ResourceBundle localMsgBundle = ResourceBundle.getBundle("resources/reports/SGVAlertMsgReports",userLocale) ;    
    reportVO = (ReportVO)session.getAttribute( "reportVO"+request.getParameter("reportCode")); 
    conditionList = (ArrayList)session.getAttribute( "conditionList"+request.getParameter("reportCode")); 
    ReportColumnVO[] columnVO = reportVO.getReportColumns();
    if(conditionList != null &&  conditionList.size() > 0)
    {
       for(int k=0;k<conditionList.size();k++)
       {
         lStrQuery = lStrQuery + ((ConditionVO)conditionList.get(k)).toString();
       }  
    }  
  %>
    <script language="JavaScript" src="script/calendar.js"></script>
  <script language="JavaScript" src="WEB-INF/jsp/reports/scripts/prmt_validations.js"></script>
  <script language="JavaScript" src="WEB-INF/jsp/reports/scripts/alertmsgutils.js"></script>
  <script language="JavaScript" >
  
  
  function validateColumnRule()
  {
    <% String msg=null; %>
    if(document.forms[0].txt_FinalQuery.value != null && document.forms[0].txt_FinalQuery.value != "")
    {
      if(document.forms[0].cmb_LogOper.value == "-1")
      {
        <%  msg = localMsgBundle.getString( "DropdownMandatoryField" );
        %>
        fvalue = document.forms[0].cmb_LogOper.value;
        var flds = new Array( document.forms[0].cmb_LogOper.attrTitle );
        if( fvalue == "-1" )
        {
          alert( getAlertMessage( "<%=msg%>", flds) );
          document.forms[0].cmb_LogOper.focus();
          return false;
        }
      }  
    }
    if(document.forms[0].cmb_Columns.value == "-1")
    {
      <%  msg = localMsgBundle.getString( "DropdownMandatoryField" );
        %>
        fvalue = document.forms[0].cmb_Columns.value;
        var flds = new Array( document.forms[0].cmb_Columns.attrTitle );
        if( fvalue == "-1" )
        {
          alert( getAlertMessage( "<%=msg%>", flds) );
          document.forms[0].cmb_Columns.focus();
        }
        
        return false;
    }
    if(document.forms[0].cmb_Operator.value == "-1")
    {
      <%  msg = localMsgBundle.getString( "DropdownMandatoryField" );
        %>
        fvalue = document.forms[0].cmb_Operator.value;
        var flds = new Array( document.forms[0].cmb_Operator.attrTitle );
        if( fvalue == "-1" )
        {
          alert( getAlertMessage( "<%=msg%>", flds) );
          document.forms[0].cmb_Operator.focus();
          return false;
        }
    }    
    if(document.forms[0].txt_Value.value == "")
    {
      <%  msg = localMsgBundle.getString( "MandatoryField" );
        %>
        fvalue = document.forms[0].txt_Value.value;
        var flds = new Array( document.forms[0].txt_Value.attrTitle );
        if( fvalue == "" )
        {
          alert( getAlertMessage( "<%=msg%>", flds) );
          document.forms[0].txt_Value.focus();
          return false;
        }
    }  
    return true;
  }
  </script>
      
      <table width="100%">
      
      <tr>
      <td width="10%"  align = "left" >&nbsp;   </td>
      <td width="10%" align = "left" >&nbsp;   </td>
      <td width="15%"  align = "center" >&nbsp; </td>
      <td width="18%" align = "left" >&nbsp;   </td>
      <td width="13%" align = "left" >&nbsp;   </td>
      <td width="10%" align = "left" >&nbsp;   </td>
      <td width="10%"  align = "center" >&nbsp; </td>
      <td width="9%" align = "left" >&nbsp;   </td>
      <td width="5%"  align = "left" >&nbsp;   </td>
      </tr>
      
      <tr><td colspan=9>&nbsp;</td></tr>  
      
      <tr>      
      <td  >&nbsp;   </td>
      <td  align="center" > 
              <SELECT  class=ListBoxGUI style="width: 50;" size="1" name="cmb_LogOper" attrTitle="Logical Operator" type = "select-one" >
              <option value="-1" >--</option>  
              <option value="&&" >AND</option>
              <option value="||" >OR </option>
      </SELECT>
      </td>
      <td align="right" >
                <SELECT  class=ListBoxGUI style="width: 150;" size="1" name="cmb_Columns" attrTitle="Column List" type = "select-one" >
                <option value="-1" >--select--</option>
                <%
                   for(int i =0; i<columnVO.length ; i++)
                   { %>
                     <option value="<%=columnVO[i].getColumnName()%>" ><%=columnVO[i].getColumnHeader()%></option>
                     
                  <% }
                
                %>
                
      </SELECT>
      </td>                  
      <td align="left"  > 
                <SELECT  class=ListBoxGUI style="width:50;" size="1" name="cmb_Operator" attrTitle="Operator" type = "select-one" >
                <option value="-1" >--</option>
                <option value="<" > < </option>
                <option value=">" > > </option>
                <option value="<=" > <= </option>
                <option value=">=" > >= </option>
                <option value="==" > = </option>
                
      </SELECT>
      </td>
      <td align="center"><input type="text" name="txt_Value" attrTitle="Value" size="20" maxlength="20" value="" ></td>
      
      <td align="right" ><input type="submit" name="Add"  value="Add" class="ActionButton" onclick="addToQuery()"></td>
      <td  >&nbsp;   </td>
      </tr> 
      <tr><td colspan=9>&nbsp;</td></tr>
      
      <tr> 
      <td colspan=9>
      <table colspan="12" align="center" border ="0" width="100%" >
        <tr>
          <td width="8%"></td>
          <td width="82%"></td>
          <td width="5%"></td>
        </tr>
        <tr>
         <td  >&nbsp;   </td>
         <td align="left">
         <textarea rows="4" name="txt_FinalQuery" cols="75" readonly><%=lStrQuery%></textarea>
         </td>
         <td  >&nbsp;   </td>
        </tr>
      </table>
      </td>
       </tr>
      <tr><td colspan=9>&nbsp;</td></tr> 
      </table>
<%
}
catch(Exception e)
{
  e.printStackTrace(); 
}  
%>