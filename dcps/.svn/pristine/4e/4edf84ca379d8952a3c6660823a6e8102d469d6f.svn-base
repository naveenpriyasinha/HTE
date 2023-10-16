<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<html>
<%@ include file="../include.jsp" %>
<script type="text/javascript">
function validate_required(field,field1,field2,field3,alerttxt)
{ 
	with (field)
	{if (value==null||value=="")
	  	{	alert(alerttxt);return false  	}
	
	}
	with (field1)
	{if (value==null||value=="")
	  	{	alert(alerttxt);return false  	}
	
	}
	with (field2)
	{if (value==null||value=="")
	  	{	alert(alerttxt);return false  	}
	
	}
	with (field3)
	{if (value==null||value=="")
	  	{	alert(alerttxt);return false  	}
	else {return true}
	}
}
function validate_form(thisform)
{
	with (thisform)
	{
		if (validate_required(serviceclass,servicemethod,servicedesc,servicename,"One or More Textbox(s) Empty")==false)
		{
				serviceclass.focus();return false
				servicemethod.focus();return false
				servicedesc.focus();return false
				servicename.focus();return false
		}
	}
}
</script>

<form method="POST"  action="./hdiits.htm" onsubmit="return validate_form(this)" method="post">
 <div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>
<div id="progressImage" style="display:none"></div>

 <ul id="maintab" class="shadetabs"> 
           <li class="selected" >
            <a href="#" rel="tcontent1">
             CONFIGURATION
            </a>
           </li>
          </ul>
 <div>
 
 <table class="tabtable">

 <tr>
 <td>Service Type</td>
 <td><select name="servicetype"><option value="VOGEN">VOGEN</option><option value="SRVC">SRVC</option></select> </td>
 </tr>
 <tr>
 <td>Service Class</td>
 <td><input type="text" name="serviceclass"  size="50" value=<%=StringUtility.getParameter("srvclass",request)%>></td>
 </tr>
 <tr>
 <td>Service Method</td>
 <td><input type="text" name="servicemethod" size="50" value=<%=StringUtility.getParameter("srvmethod",request)%>></td>
 </tr>
 <tr>
 <td>Service Description</td>
 <td><input type="text" name="servicedesc" size="50" value=<%=StringUtility.getParameter("srvdesc",request)%>></td>
 </tr>
 <tr>
 <td>Service Name</td>
 <td><input type="text" name="servicename" size="50" value=<%=StringUtility.getParameter("srvname",request)%>></td>
 </tr>
 <tr>
 <td>Service Status</td>
 <% String srvstatus=StringUtility.getParameter("srvstatus",request);%>
 <%if(srvstatus.equals("ACTV")){%>
 <td><input type="checkbox" name="servicestatus" checked="checked" value="ACTV" >Active</td>
 <%}else{%>
 <td><input type="checkbox" name="servicestatus"   >Active</td>
 <%}%>
 
 	
 	
 <%System.out.println("status========"+srvstatus);%>
 <td align="left" class="tabnavtd" id="tabnavtd"><input type=button value="Save Data" onClick="insertdt()" /></td>
 
 </tr>
<tr> <td colspan="2"> 
<% String str = (String) request.getParameter("editmode"); %>
<% if (str!=null && str.equals("y")) { %> 
  <input type="hidden" name="actionFlag" value="editConfigurationDetails"> 
<%}else{%>
	<input type="hidden" name="actionFlag" value="insertServiceConfiguration" > 		
<%}%>
</td></tr>
 </table>
 <%System.out.println("Crete Date"+request.getParameter("crtdate"));%>
 <input type="hidden" name="serviceid" value=<%=request.getParameter("srvcid") %>>
 </div>

 <jsp:include page="../tabnavigation.jsp?"/>


 </form>
 
</html>