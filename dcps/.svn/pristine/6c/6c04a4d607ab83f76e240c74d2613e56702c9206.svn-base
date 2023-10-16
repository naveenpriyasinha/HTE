<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="java.util.*"%>
<%@page import="java.sql.ResultSet"%>


<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="queryString" value="${resValue.queryString}"></c:set>
<c:set var="resultList" value="${resValue.resultList}"></c:set>
<c:set var="totalColumns" value="${resValue.totalColumns}"></c:set>
<c:set var="totalRows" value="${resValue.totalRows}"></c:set>
<c:set var="columnNameList" value="${resValue.columnNameList}"></c:set>


<html>
<head>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script>
function chkEmpty(ctrl,msg)
{
	var str = ctrl.value;
	if(str=="" || str == "-1")
	{
		alert(msg +" Cannot be Empty.");
		ctrl.focus();		
		return false;
	}		
	else
		return true;
}
function callExecuteService()
{
	
	if(chkEmpty(document.getElementById('textQuery'),'Query String') ){
		displayLists();		
	}
	
}
function displayLists()
{
	var queryString = document.getElementById("textQuery").value ;
	document.getElementById("hiddenQuery").value = queryString
	//alert('Query is -->'+document.getElementById("hiddenQuery").value);
	url = "ifms.htm?actionFlag=loadQueryExecuter";
	document.frmQueryExecuter.action = url;
	document.frmQueryExecuter.submit();
	
	
}

function checkSuccess()
{
	if("${msg}"!='')
	{
		alert('${msg}');
		url = "ifms.htm?actionFlag=loadQueryExecuter";
		self.location.href = url ;
		showProgressbar("Please wait...");
	}
}
function ReturnLoginPage()
{
	self.location.href = "ifms.htm?actionFlag=validateLogin";
}

function fullForm(event1)
{
	
	var key;
	if(window.event)
		key = event.keyCode;
	else
	key = event1.keyCode;

	
	if (key==32) 
	{
		var query = document.getElementById("textQuery").value.trim();
		if(query == 'sf' || query == 'SF')
		{
			document.getElementById("textQuery").value = 'SELECT * FROM';
		}
	}
}
</script>
</head>

<body>
<hdiits:form name="frmQueryExecuter" action="" id="frmQueryExecuter" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle">
<legend style="font-weight: bold">
	Query Executer
</legend>
<label style="padding-left:490px;color: red;" >Note : Only Select Queries Are Permissible.</label>
<div style="padding-left:175px">
<table width="80%" align="center" >
		<tr>
			<td align="left" style="width:20%;font-weight: bold;font-size: small;">Enter Your Query:
							</td>
			<td align="left" style="width:60%">
				<hdiits:textarea id = "textQuery"  name="textQuery"  style="width:450px;height:75px" onkeypress="fullForm(this)"  default="${resValue.queryString}" ></hdiits:textarea>
								<label class="mandatoryindicator">*</label>	
			</td>
		</tr>
		<tr/>
		<tr/>
</table>
<input type="hidden" name="hiddenQuery" id="hiddenQuery" value="">
<br/>
<div align="center">
			<hdiits:button name="btnGo" id="btnGo" type="button" captionid="PC.GO" bundle="${commonLables}" onclick="callExecuteService()" />
			<hdiits:button	name="btnBackWithGo" id="btnBackWithGo" type="button" captionid="eis.back" bundle="${commonLables}" onclick="ReturnLoginPage();" />
</div>
</div>
</fieldset>
<c:if test="${(resValue.totalColumns > 0)}">
<fieldset class="tabstyle">
<legend>
	<b>ResultSet for the Query:::</b>
</legend>
<br/><br/>
<legend>
Total Number of Rows are :	${totalRows}
</legend>
<br/><br/>
<div>
<table  width="80%" align="center" border="1" bordercolor="black">
	<%
	List resultList=(List)pageContext.getAttribute("resultList");
	List columnNameList=(List)pageContext.getAttribute("columnNameList");
	long  totalColumns=Long.parseLong(pageContext.getAttribute("totalColumns").toString());
	String nullString = "< null >";
	Object cellObj = new Object();
	%>
	<tr>
	<%
	for(int k=0;k<totalColumns;k++){
		cellObj = columnNameList.get(k).toString();
	%>
	
		<td align="center"><b>
		<%=cellObj.toString() %>
		</b></td>
	<%}%>
	</tr>
	<% 
	for(int i=0;i<resultList.size();){ 	 
	%>
	<tr>
		<%	for(int j=0;j<totalColumns;j++,i++){
			if(resultList.get(j) != null)
				cellObj = resultList.get(i).toString();  
			else
				cellObj=nullString.toString();
		%>
		<td align="center">
			<%=cellObj.toString()%>
		</td>
		<%} %>
	</tr>
	<%} %>
		<tr>
		<tr>
</table>
</div>

<br/>
<br/>
</fieldset >
</c:if>

</hdiits:form>
</body>
