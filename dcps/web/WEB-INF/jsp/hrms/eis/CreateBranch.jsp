
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.List"%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<%
//System.out.println("\n\n-------------------------------------------in CreateBranch.jsp------------------------------------------------------------\n\n");
%>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>

<script>


function submitForm()
{
	var val = document.CreateBranch.txtddoName.value;
	var val2 = document.CreateBranch.txt1.value;
	  if( val == "" )
	  {
	    alert('Please Enter The Value IN Branch Name.');
	   
	    document.CreateBranch.txtddoName.focus();
	    return false;
	  }
	  else if( val2 == "" )
	  { 
		 alert('Please Enter The Value IN Branch Code.');
	   
	    document.CreateBranch.txt1.focus();
	    return false;
	  }
	
	var txtddoName = document.getElementById("txtddoName").value;
	var txt1 = document.getElementById("txt1").value;
	var actionFlag = "insertBranchDtls";
	if(validateSpecificFormFields(new Array('txtddoName','txt1')))
	{
		document.CreateBranch.action="hrms.htm?actionFlag=insertBranchDtls&txtddoName"+txtddoName + "&txt1=" +txt1;
		document.CreateBranch.submit(); 
		alert('Data is submitted successeully');
		Back();		
	}

}


function Name(P)
{	
	var Rem = document.getElementById(P).value;
	var len=Rem.length;
	str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz. /\\-\n"; 
	for(var i=0;i<len;i++)
	{
	     if((str.indexOf(Rem.charAt(i))) == -1)
	     {
	       if(Rem.charAt(i) != '\n' && Rem.charAt(i) != '\r')
	       {
	        alert("Please Enter Proper Characters");
	        document.getElementById(P).focus();
	        document.getElementById(P).style.background="#ccccff";
	        return false;
	       }
	     }
	     document.getElementById(P).style.background="";
	 }
  return true;
}

function Remarks(P)
{	
	var Rem = document.getElementById(P).value;
	var len=Rem.length;
	str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 /\\-\n"; 
	for(var i=0;i<len;i++)
	{
	     if((str.indexOf(Rem.charAt(i))) == -1)
	     {
	       if(Rem.charAt(i) != '\n' && Rem.charAt(i) != '\r')
	       {
	        alert("Please Enter Proper Language");
	        document.getElementById(P).focus();
	        document.getElementById(P).style.background="#ccccff";
	        return false;
	       }
	     }
	     document.getElementById(P).style.background="";
	 }
  return true;
}

function Back()
{
	history.go(-1);
	return false;
	}

function validateForm()
	  {
		var val1 = document.getElementById("txtddoName");
		var val2 = document.getElementById("txt1");
		ValidatorEnable(val1,true);
		ValidatorEnable(val2,true);
		
	   var uri = "./hrms.htm?actionFlag=";
	  // var url = uri + document.CreateBranch.txtAction.value + "&branchname="+txtddoName+"&branchcode"+txt1+"&branchdesc"+txt2+"&address"+txt3+"&active"+txt4+"&mainbranch"+txt5;
	   var url = uri + document.CreateBranch.txtAction.value;
	   document.CreateBranch.action = url;
	   document.CreateBranch.submit();
	  }


</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:out value="${resValue.msg}"></c:out>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<hdiits:form method="Post" name="CreateBranch" validate="true" action="hrms.htm?actionFlag=getbranchdtls&elementId=300712"  encType="multipart/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs" >
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="CB.CreateBranch" bundle="${commonLables}"/></b></a></li>
		
	</ul>
</div>
 
  <hdiits:hidden name="txtAction" default="insertBranchDtls&edit=Y"/>

<table width="60%" border="1" align="center" bordercolor="green">
<tr>
<td>
<table width="100%" border="0" align="center" >
<tr>
<td width="25%" align="center"> 
   <label for="txtddoName"><b><fmt:message key="CB.BranchName"  bundle="${commonLables}"/></b></label><br>
</td>
 <td width="25%" > 
   <hdiits:text id="txtddoName" name="txtddoName"  caption ="txtddoName" mandatory="true" validation="sel.isrequired" maxlength="150" default="" onblur ="Name(this.id)" /><br>
</TD>
</tr>
<tr>
<td width="25%" align="center"> 
   <label for="txt1"><b><fmt:message key="CB.BranchCode" bundle="${commonLables}"/></b></label><br>
</td>
 <td width="25%" > 
   <hdiits:text id="txt1" name="txt1"  caption ="txt1" mandatory="true" validation="sel.isrequired" maxlength="150" default=""  onblur ="Remarks(this.id)" /><br>
</TD>
</tr>
<tr>
<td width="25%" align="center"> 
   <label for="txt2"><b><fmt:message key="CB.BranchDesc" bundle="${commonLables}" /></b></label><br>
</td>
 <td width="25%" > 
   <hdiits:text id="txt2" name="txt2"  caption ="txt2" maxlength="150" default=""  onblur ="Remarks(this.id)"/><br>
</TD>
</tr>
<tr>
<td width="25%" align="center"> 
   <label for="txt3"><b><fmt:message key="CB.Address" bundle="${commonLables}"/></b></label><br>
</td>
 <td width="25%" > 
   <hdiits:textarea   cols="17" name="txt3" default="" maxlength="150"></hdiits:textarea><br>
</TD>
</tr>
<tr>
<td width="25%" align="center"> 

 <br> <label for="txt4"><b><fmt:message key="CB.Active" bundle="${commonLables}"/></b></label><br>
</td>
 <td width="25%" > 
 
 <br> <input type="radio" name="txt4" value="1">Yes    
<input type="radio" name="txt4" value="2">No<br>
  
</TD>
</tr>
<tr>
<td width="25%" align="center"> 
   <label for="txt5"><b><fmt:message key="CB.MainBranch" bundle="${commonLables}"/></b></label><br>
</td>
 <td width="25%" > 
 
   <br><input type="radio" name="txt5" value="1">Yes    
<input type="radio" name="txt5" value="0">No<br><br> 
  
</TD>

</tr>

</table>

<table align="center" width="100%" border="0">
<tr align="center">
<td width="100%" align="center">

<hdiits:button name="Submit" type="button" onclick="submitForm()" value="Submit" />
 <hdiits:button name="Back" type="button" value="Back" onclick="history.go(-1);return false;"/><br>

</td>
</tr>
</table>
</td>
</tr>
</table>


<script type="text/javascript">
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
			window.FILL_COMBO_BOX_TAB_WISE = false;
			initializetabcontent("maintab")
		</script>


</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>