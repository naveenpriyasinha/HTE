<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<%@page import="java.util.List"%><fmt:setBundle basename="resources.ess.UserPost" var="userPostMapping" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="userPostList" value="${resultValue.userPostList}"></c:set>
<c:set var="msg" value="${resultValue.msg}" ></c:set>
<c:set var="postListSize" value="${resultValue.postListSize}" ></c:set>
<c:set var="empName" value="${resultValue.empName}" > </c:set>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:setBundle basename="resources.eis.eisLables" var="commonLables" scope="request"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="eisCommonLables" scope="page"/>
<c:set var="tdBGColor" value="#76A9E2"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<%

List userPostList = (List) pageContext.getAttribute("userPostList");

int size = userPostList.size();

pageContext.setAttribute("listSize",size);

%>

<script type="text/javascript">
	function addNewEntry()
	{
		document.forms[0].action='hdiits.htm?actionFlag=fillNewEmpConfigData&edit=N';
		document.forms[0].submit();
	}
	function editRecord(userPostRltId,userId)
	{

		document.forms[0].action="hdiits.htm?actionFlag=fillNewEmpConfigData&searchFlg=N&edit=Y&userPostRltId="+userPostRltId+"&userid="+userId;
		
		document.forms[0].submit();		
	}
	function closeWindow()
	{
		document.frmViewEmployeeList.action = "hrms.htm?actionFlag=getHomePage";
	   	document.frmViewEmployeeList.submit();
	}	
	function submitForm()
	{
		var empId=document.getElementById('Employee_ID_EmployeeSearch').value;
		var url="./hrms.htm?actionFlag=getViewEmployeeList&searchFlg=Y";
		document.frmViewEmployeeList.action=url;
		document.frmViewEmployeeList.submit();
	}
	function chkValue()
	{
		var empId=document.getElementById("Employee_ID_EmployeeSearch").value;
	
		if(empId=="")
		{
			if(document.getElementById("value").value!='')
			{
				searchvalues();
				return true;
			}
			alert("Please search the employee first");
			return false;
		}
		else
		{
			return true;
		}
		
	}
function searchvalues()
{
	var value = document.getElementById("value").value;
	var option_selected = document.getElementById("option_selected").value;
	if(option_selected == "Post")
	{
		if(check4string(value, option_selected))
		{
		document.forms[0].action = 'hdiits.htm?actionFlag=getViewEmployeeList&searchFlg=Y&Post=' + value;
		document.forms[0].submit();
		}
	}
	if(option_selected == "PsrNo")
	{
		if(check4number(value, option_selected))
		{
			document.forms[0].action='hdiits.htm?actionFlag=getViewEmployeeList&searchFlg=Y&PsrNo='+value;
			document.forms[0].submit();
		}	
	}
	if(option_selected == "BillNo")
	{
		if(check4number(value, option_selected))
		{
			document.forms[0].action='hdiits.htm?actionFlag=getViewEmployeeList&searchFlg=Y&BillNo='+value;
			document.forms[0].submit();
		}	
	}	
	if(option_selected == "Dsgn")
	{
		if(check4string(value, option_selected))
		{
		document.forms[0].action='hdiits.htm?actionFlag=getViewEmployeeList&searchFlg=Y&Dsgn='+value;
		document.forms[0].submit();
		}
	}
	if(option_selected == "")
		alert("Please Select Criteria For Search");
}
function check4string(str, name)
{
	var len=str.length;
	check="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ./\\-\n"; 
	for(var i=0;i<len;i++)
	{
	     if((check.indexOf(str.charAt(i))) == -1)
	     {
	       if(str.charAt(i) != '\n' && str.charAt(i) != '\r')
	       {
	        alert("Please Enter Proper " + name + " Name");
	        document.getElementById("value").focus();
	        document.getElementById("value").style.background="#ccccff";
	        return false;
	       }
	     }	     
	 document.getElementById("value").style.background=" ";
	}
	return true;
}
function check4number(str, name)
{	
	if(isNaN(str) == true)
    {
	    var newName = "";
	    for(var i=0;i<name.length-2;i++){
	    	newName += name.charAt(i);
		}
		alert ("This Value Is Not A Number. \nPlease Enter A Valid " + newName + " Number ");
    	document.getElementById("value").focus();
    	document.getElementById("value").style.background="#ccccff";
		return false;
    }
    if(str == "")
    {
    	alert ("The " + name + " Field is blank ");
    	document.getElementById("value").focus();
    	document.getElementById("value").style.background="#ccccff";
    	return false;
	}
    document.getElementById("value").style.background=" ";
    return true;
    
}
	
</script>
<!--  -->
<hdiits:form name="frmViewEmployeeList" validate="true" method="POST" 
 action="./hrms.htm?actionFlag=getViewEmployeeList&searchFlg=N"
encType="text/form-data">
	<div id="tabmenu">
	
			<ul id="maintab" class="shadetabs">
				<li class="selected">
					<a href="#" rel="tcontent1"><b><fmt:message key="pay.viewEmpList" bundle="${eisCommonLables}"/></b></a>
				</li>
			</ul>
	</div>
	<div class="tabcontentstyle">
	
	<div id="tcontent1" class="tabcontent" tabno="0">
	<a href= "./hrms.htm?actionFlag=fillNewEmpConfigData&edit=N" >  Add new Entry </a>
	<table  width="85%" align="center">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmployeeSearch"/>
						<jsp:param name="formName" value="frmViewEmployeeList"/>
					</jsp:include>
			</td>
		</tr>
		<tr >
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="return chkValue()"/>	
				<hdiits:button type="button" captionid="viewAll" bundle="${commonLables}"  name="viewAll" onclick="submitForm()"/>				
				</fieldset>
			</td>
		</tr>	
	</table>
	<br>
	<table width="85%" border="3" bordercolor=#ccccff bgcolor="white" align="center" name="searchTable" id="searchTable">
		<TR><td align = "center"> Search By </td>
 			<td align = "center">
 				<select type = "menu" name = "search_criteria" caption="search_criteria" captionid="search_criteria" id = "option_selected">
					<option value = "PsrNo">Psr Number</option>
					<option value = "Post">Post Name</option>
					<option value = "BillNo">Bill Number</option>
					<option value = "Dsgn">Designation</option>
					</select>
			</td>
			<td align="center"><input type="text" name="srchText" captionid="search_criteria" id="value"></td>
			<td width="34%" align="left"><hdiits:button type="button" captionid="Search_Employee"
				value="&nbsp;&nbsp;Search&nbsp;&nbsp;" name="search" id="search" onclick="searchvalues()" /></td>
		</tr>
	</table>
	
	</hdiits:form >
	
	
		<fieldset class="tabstyle">
		<c:set var="i" value="1" />
			<legend  id="headingMsg"><fmt:message key="pay.viewEmpDtls" bundle="${eisCommonLables}"/></legend>
<script> 

</script>	
			<display:table pagesize="20" list="${userPostList}" id="row" requestURI="" style="width:100%" sort="list" defaultorder="ascending"   
			offset="1" export="false" decorator="com.tcs.sgv.ess.decorator.UserPostMappinglDecorator">
<!-- 				<display:setProperty name="paging.banner.placement" value="bottom"/> -->
							
				<c:set value="${row.userPostId}" var="otherIdForLink"/>	
				<display:column class="tablecelltext" title="Employee Name"  headerClass="datatableheader"  sortable="true"  style="text-align:center">
				  <a href="javaScript:editRecord(${row.userPostId},${row.userId})" id="otherLink${row.userPostId}">${row.empFullName}</display:column>
				
				<display:column class="tablecelltext" title="Post" headerClass="datatableheader" style="text-align: center;" 
							sortable="true" >${row.postname}</display:column>
				<display:column class="tablecelltext" title="Psr No." headerClass="datatableheader" style="text-align: center;" 
							sortable="true" > ${row.psrNo}</display:column>
							
							<display:column class="tablecelltext" title="Bill No." headerClass="datatableheader" style="text-align: center;" 
							sortable="true" > ${row.billNo}</display:column>
							
				<display:column class="tablecelltext" title="Designation" headerClass="datatableheader" style="text-align: center;" 
							sortable="true" >${row.dsgnname}</display:column>
							
				<display:column class="tablecelltext" title="Start Date" headerClass="datatableheader" style="text-align: center;" 
							sortable="true" > ${row.startDate}</display:column>
							
				<display:column class="tablecelltext" title="End Date" headerClass="datatableheader" style="text-align: center;" 
							sortable="true" > ${row.endDate}</display:column>
		

				<display:footer media="html"></display:footer>		
				
			</display:table>			
		</fieldset>
		<c:choose>
		 	<c:when test="${postListSize eq 1 &&  empName!=null && not empty empName}">
		    	<script>
		        	document.getElementById("otherLink${otherIdForLink}").click();
		        </script>
			</c:when>
		</c:choose> 		
		
		<br><br>
		<a href= "./hrms.htm?actionFlag=fillNewEmpConfigData&edit=N" >  Add new Entry </a>
		
		   <table align="center">
		<tr>
			 <td>
			
				<hdiits:button name="addNewEntry_button" captionid="AddRec" bundle="${userPostMapping}"  onclick="addNewEntry()" type="button"/>
			</td>
			<td>
				<hdiits:button name="btnClose" type="button" captionid="close"  bundle="${userPostMapping}" value="&nbsp;&nbsp; CLOSE &nbsp;&nbsp;"  onclick="closeWindow();" />
			</td> 
			
		</tr>
		</table> 
			</div>
			</div>

	<script type="text/javascript">
			initializetabcontent("maintab");
			//document.frmBF.Employee_srchNameText_EmpSearch.focus();	
			document.forms[0].elements['Employee_srchNameText_EmployeeSearch'].focus();			
		</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>