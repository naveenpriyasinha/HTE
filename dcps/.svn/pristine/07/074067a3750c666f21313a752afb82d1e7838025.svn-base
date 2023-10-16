<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setBundle basename="resources.eis.eisLables_en_US"	var="adminCreatePostLabel" scope="request" />
<%@page import="java.util.List"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dataList" value="${resValue.recordList}"></c:set>
<c:set var="userpostlist" value="${resValue.userpostlist}"></c:set>
<c:set var="fieldList" value="${resValue.fieldCaptionList}"></c:set>
<%--<c:set var="orgPostDtlList" value="${resValue.orgPostDtlList}"></c:set>--%>
<c:set var="orderList" value="${resultObj.resultValue.orderList}"></c:set>
<c:set var="filterDdoCode" value="${resValue.filterDdoCode}">
</c:set>
<c:set var="ddo" value="${resValue.ddo}">
</c:set>

<c:set var="empName" value="${resValue.empName}"></c:set>
<c:set var="designation" value="${resValue.Designation}"></c:set>
<c:set var="billList" value="${resValue.billList}"></c:set>
<%
List dataList = (List) pageContext.getAttribute("dataList");
int size = dataList.size();
pageContext.setAttribute("listSize",size);
%>
<html>
<head>


<script type="text/javascript" src="common/script/tabcontent.js"></script>


<script type="text/javascript">
function EnterkeyPressed(e,form)
{	
  
  var whichCode = (window.Event) ? e.which : e.keyCode;
	if ((e.keyCode==13))
	{
		searchvalues();
	}
}   


	function GetPostfromOrder() {
		//clearPostList();

		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		var url = '';
		var uri = '';
		url = uri + '&orderId=' + document.frmAdminCrtPost.order.value;
		var actionf = "GetPostfromOrder";
		uri = './hrms.htm?actionFlag=' + actionf;
		url = uri + url + "&editFlag=0";
		xmlHttp.onreadystatechange = GetPostsfromOrder;
		xmlHttp.open("POST", encodeURI(url), true);
		xmlHttp.send(null);
	}

	function GetPostsfromOrder() {
		if (xmlHttp.readyState == complete_state) {
			var post = document.getElementById("post");

			var XMLDoc = xmlHttp.responseXML.documentElement;
			var entries = XMLDoc.getElementsByTagName('post-mapping');
			var val = 0;
			var text = '';
			for ( var i = 0; i < entries.length; i++) {
				val = entries[i].childNodes[0].text;
				text = entries[i].childNodes[1].text;
				var y = document.createElement('option')
				y.value = val;
				y.text = text;
			
				try {
					post.add(y, null);
				} catch (ex) {
					post.add(y);
				}

			}
		}
	}

	function clearOrderList() {
		var v = document.getElementById("order").length;
		for (i = 1; i < v; i++) {
			lgth = document.getElementById("order").options.length - 1;
			document.getElementById("order").options[lgth] = null;
		}
	}

	function clearPostList() {
		var v = document.getElementById("post").length;
		for (i = 1; i < v; i++) {
			lgth = document.getElementById("post").options.length - 1;
			document.getElementById("post").options[lgth] = null;
		}
	}

	function getOrderList() {
		alert('function called');
		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		var url;
		var uri = '';
		url = uri + '&cmbDept=' + document.forms[0].cmbDept.value;
		var actionf = "getOrderListByLocation";
		uri = './hrms.htm?actionFlag=' + actionf;
		url = uri + url;
		//alert(' ' + url);	  		  		  
		xmlHttp.onreadystatechange = deptChanged;
		xmlHttp.open("POST", encodeURI(url), true);
		xmlHttp.send(null);
	}

	function deptChanged() {
		if (xmlHttp.readyState == complete_state) {

			clearOrderList();

			var order = document.getElementById("order");

			var XMLDoc = xmlHttp.responseXML.documentElement;

			if (XMLDoc == null) {
				window.status = 'No Records Found.';
			} else {
				window.status = '';
				var orderList = XMLDoc.getElementsByTagName('order-mapping');
				for ( var i = 0; i < orderList.length; i++) {
					val = orderList[i].childNodes[0].text;
					text = orderList[i].childNodes[1].text;
					//     				    alert('Village val is:   ' + val + 'and text is:-   ' + text);
					var y = document.createElement('option')
					y.value = val;
					y.text = text;

					try {
						order.add(y, null);
					}

					catch (ex) {
						order.add(y);
					}
				}
			}
		}
	}
	function addNewEntry() 
	{
		document.forms[0].action = 'hdiits.htm?actionFlag=addAdminPostDtl';
		document.forms[0].submit();
	}
	function test(reqId) 
	{
		document.forms[0].action = "hdiits.htm?actionFlag=editAdminOrgPostData&reqId="
				+ reqId;
		document.forms[0].submit();
	}
	function editData() 
	{
		var postId = document.frmAdminCrtPost.post.value;
		if (postId == "" || postId == null) {
			alert('Please Select Post to Edit!!!');
			return;
		} else {
			document.forms[0].action = "hdiits.htm?actionFlag=editAdminOrgPostData&reqId="
					+ postId;
			document.forms[0].submit();
		}
	}	
	function deleteData() 
	{
		var isChecked = false;
		for ( var i = 0; i < document.forms[0].deletedata.length; i++) {
			if (document.forms[0].deletedata[i].checked) {
				isChecked = true;
			}
		}
		if (isChecked) {
			
			var answer = confirm("Are you sure want to delete the selected data?");
			if (answer) {
				document.forms[0].action = 'hdiits.htm?actionFlag=deleteAdminOrgPostData';
				document.forms[0].submit();
			}
		} else {
			alert("Please select the checkbox");
		}
	}
	function searchData() 
	{
		document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl';
		document.forms[0].submit();
	}
	function submitFormAuto()
	{
		if(chkValue())
		{
			var empId = document.getElementById("Employee_ID_viewPostSearch").value;
			document.forms[0].action='hdiits.htm?actionFlag=showAdminPostDtl&empId='+empId;
			document.forms[0].submit();
			//document.frmAdminCrtPostsearch.submit();
		}
		return true;
	}
	function chkValue()
	{
		var empId = document.getElementById("Employee_ID_viewPostSearch").value;
		if(empId=="")
		{			
			alert("Please search the employee first");
			return false;
		}
		else
		{			
			return true;
		}	
	}	
	function setStyle(x)
	{
	document.getElementById(x).style.background="#ccccff";
	}
	function searchvalues()
	{
		var value = document.getElementById("value").value;
		var option_selected = document.getElementById("option_selected").value;
		if(option_selected == "Post")
		{
			document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl&Post=' + value;
			document.forms[0].submit();
		}
		if(option_selected == "PsrNo")
		{
			if(check4number(value))
			{
				document.forms[0].action='hdiits.htm?actionFlag=showAdminPostDtl&PsrNo='+value;
				document.forms[0].submit();
			}	
		}
		if(option_selected == "BillNo")
		{
			if(check4number(value))
			{
				document.forms[0].action='hdiits.htm?actionFlag=showAdminPostDtl&BillNo='+value;
				document.forms[0].submit();
			}	
		}	
		if(option_selected == "Dsgn")
		{
			document.forms[0].action='hdiits.htm?actionFlag=showAdminPostDtl&Dsgn='+value;
			document.forms[0].submit();
		}
		if(option_selected == "")
			alert("Please Select Criteria For Search");
	}
	function check4number(n)
	{	
		var flag = true;
		if(isNaN(n) == true)
	    {
			flag = false;
			alert ("This Value Is Not A Number. \nPlease Enter A Valid Field Value.");
	    }
	    if(n == "")
	    {
	    	flag = false;
			alert ("The Field is blank ");
		}
	    return flag;
	}
		
	function searchFunction()
	{

		var dsgnId = document.getElementById('designationCmb').value;
		var billGrpId = document.getElementById('billCmb').value;

		if(dsgnId>0 && billGrpId>0)
		{
			
			//alert('dsgnId'+dsgnId);
			//alert('billGrpId'+billGrpId);
			
			//document.frmAdminCrtPost.action="hrms.htm?actionFlag=fillPostDetails&dsgnId="+dsgnId+"&billGrpId="+billGrpId;
			var url="./ifms.htm?actionFlag=fillPostDetails&dsgnId="+dsgnId+"&billGrpId="+billGrpId;
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
		}
		else if(dsgnId>0)
		{
			//alert('dsgnId'+dsgnId);
			var url="./ifms.htm?actionFlag=fillPostDetails&dsgnId="+dsgnId;
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
			
		}
		else if(billGrpId>0)
		{
			var url="./ifms.htm?actionFlag=fillPostDetails&billGrpId="+billGrpId;
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
			
			
		}
		else
		{
			//alert('just search');
			var url="./ifms.htm?actionFlag=fillPostDetails&billGrpId";
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
			
		}
	}
</script>

<script type="text/javascript">

function filterByDDOCode(){
	//alert("hiii");
	var ddoCode= document.getElementById("cmbAsstDDO").value;
	//alert("ddoCode "+ddoCode);
	var url;

		url="ifms.htm?actionFlag=showAdminPostDtl&ddoCode="+ddoCode+"&flag=Y";;
		document.frmAdminCrtPost.action= url;
		document.frmAdminCrtPost.submit();
}

</script>

</head>
<body>
<c:set value="0" var="i"></c:set>
<hdiits:form name="frmAdminCrtPost" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <hdiits:caption
			captionid="admin.ShowPostDtl" bundle="${adminCreatePostLabel}" style="color:black"></hdiits:caption>
		</b></a></li>
	</ul>
	</div>
	
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	<%--<TR>
		
		<TD align="left" width="15%"><b><hdiits:caption
			captionid="OM.orderName" bundle="${adminCreatePostLabel}" /></b></TD>
			
		<TD align="left" width="25%"><hdiits:select style="width:20%"
			mandatory="true" sort="true" captionid="OM.orderName"
			bundle="${commonLables}" validation="sel.isrequired" name="order"
			size="1" onchange="GetPostfromOrder();">
			<hdiits:option value="" selected="true">---------------Select---------------</hdiits:option>
				<c:forEach items="${orderList}" var="orderList">
						<option value="<c:out value="${orderList.orderId}"/>">
						<c:out value="${orderList.orderName}" /></option>
					</c:forEach>


		</hdiits:select> </TD><%-- Edited by HemanT(307727) on date 21 july'09
		
		<td width="10%"> 
		</td>
		<TD align="left" width="15%"><b><hdiits:caption
			captionid="OHP.postname" bundle="${adminCreatePostLabel}" /></b></TD>
		<TD align="left" width="25%"><hdiits:select style="width:20%"
			mandatory="true" sort="true" captionid="OHP.postname"
			bundle="${commonLables}" validation="sel.isrequired" size="1"
			onchange="editData()" name="post">
			<hdiits:option value="" selected="true">-----------------Select------------------</hdiits:option>

		</hdiits:select></TD>
	</TR>--%>
	
	<%--<table  width="85%" align="center" name="searchTable" id="searchTable">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="viewPostSearch"/>
						<jsp:param name="formName" value="frmAdminCrtPost"/>
						<jsp:param name="functionName" value="submitFormAuto"/>
					</jsp:include>
			</td>
		</tr>
	</table>
			<br>
			
	<br>--%>
	<div align="center">

	<table align="center">
		<tr>
			<td><c:out value="DDO Code"></c:out></td>
		<td><select name="cmbAsstDDO"
			id="cmbAsstDDO" style="width: 85%,display: inline;">
			<option title="Select" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="filterDdoCode" items="${resValue.filterDdoCode}">
				<c:choose>
					<c:when test="${ddo == filterDdoCode[0]}">
						<option value="${filterDdoCode[0]}" selected="selected" title="${filterDdoCode[1]}(${filterDdoCode[0]})"><c:out
							value="${filterDdoCode[1]} (${filterDdoCode[0]})"></c:out></option>
					</c:when>
					<c:otherwise>
						<option title="${filterDdoCode[1]}(${filterDdoCode[0]})" value="${filterDdoCode[0]}"><c:out value="${filterDdoCode[1]}(${filterDdoCode[0]})"></c:out></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select> </td>
		
		
			<td><input id="btnFilter" class="buttontag" type="button"
				align="center" size="5" maxlength="5" value="Filter"
				onclick="filterByDDOCode();" name="btnFilter" style="width: 120px;" /></td>
		</tr>
	</table>
	</div>
	
	<br/>
	<table align="center" width="85%">
		<tr>
			<td>
			<a	href="./hrms.htm?actionFlag=addAdminPostDtl&elementId=9000219" style="size: 7pxl;"> Add New Entry </a> <BR>
			
			</td>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.designation" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:select name="designationCmb"	id="designationCmb" captionid="admin.designation"
				bundle="${adminCreatePostLabel}" mandatory="false" validation="sel.isrequired" >
				<hdiits:option value="">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${designation}" var="desi">
					<option value="<c:out value="${desi.dsgnCode}"/>"><c:out
						value="${desi.dsgnName}" /></option>
				</c:forEach>
			</hdiits:select></td>
			
			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.bill" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><select name="billCmb" id="billCmb" captionid="admin.bill" style="width:330px"
				bundle="${adminCreatePostLabel}"  mandatory="false" validation="sel.isrequired">
				<option value="-1">--------------------Select-------------------</option>
				<c:forEach items="${billList}" var="billList">
					<option value="<c:out value="${billList.dcpsDdoBillGroupId}"/>" title="${billList.dcpsDdoBillDescription}">
					<c:out value="${billList.dcpsDdoBillDescription}" /></option>
				</c:forEach>
			</select></td>
			
		
		<td><input name="Search" value="Search" type="button" id="Search" onclick=searchFunction();></td>
			
			
		</tr>
		<tr>
		<td><a href="./hrms.htm?actionFlag=loadRenewalOfPost" style="size: 7pxl;">Renewal Of Post</a></td>
		<td/><td/><td/><td/>
	
		</tr>
		
	
	</table>
		
	 <display:table pagesize="20" name="${dataList}" id="row"
		export="false" requestURI="hrms.htm?actionFlag=showAdminPostDtl"
		requestURIcontext="false" style="width:100%;"
		decorator="com.tcs.sgv.user.service.AdminOrgPostDtlDecorator">

		<display:column style="text-align: center;font-size:12px;" class="tablecelltext" title="Sr. No." headerClass="datatableheader" sortable="true" value="${i+1}">
			<c:out value="${i}"></c:out></display:column> 
			
	 <c:set value="${row.postId}" var="otherIdForLink"/>	  
 	<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" sortable="true"
			style="text-align:LEFT;font-size:12px;">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y" id = "otherLink${row.postId}">
								${row.empFullName} </a>--%>
	${row.empFullName}
	</display:column>
			
		
		<display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="Post Name" headerClass="datatableheader">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.postname}
		</display:column>
		
		
		<display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="Post Type" headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.postType}
		</display:column>
		
		
		<%-- <display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="Subject Name" headerClass="datatableheader" sortable="true">
			<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>
		${row.subjectName}
		</display:column> --%>
		
		
		
		<display:column class="tablecelltext" title="Designation"
			headerClass="datatableheader" style="text-align: LEFT;font-size:12px;"
			sortable="true">${row.dsgnname}</display:column>
			
	   <display:column class="tablecelltext" title="Bill Group Name" headerClass="datatableheader" style="text-align: LEFT;font-size:12px;" sortable="true">${row.billNo}</display:column>

		<display:setProperty name="export.pdf" value="false" />
		<c:set var="i" value="${i+1}"></c:set>
		
		</display:table>
	<table width="100%">
		<tr width="100%">
			<td align="center"><%--	<hdiits:button name="addNewEntry_button" captionid="admin.AddEntry" bundle="${adminCreatePostLabel}" onclick="addNewEntry()" type="button"/> --%>
			<%--	<hdiits:button captionid="admin.Delete" bundle="${adminCreatePostLabel}" onclick="deleteData()" name="cmdDel2" type="button"/> --%>
			</td>
		</tr>
	</table>
 	 <c:choose>
 	<c:when test="${listSize eq 1 && empName != null && not empty empName }">
    	<script>
        	document.getElementById("otherLink${otherIdForLink}").click();
        </script>
	</c:when>
</c:choose> 
	
	</div>
	

<%--Modified up to here --%>
<hdiits:validate locale="${locale}" controlNames="" />
<script type="text/javascript">
	initializetabcontent("maintab")
</script>
	<script type="text/javascript">


	
</script>

</hdiits:form>
</body>
</html>
<%
}catch(Exception e) {e.printStackTrace();}
%>
