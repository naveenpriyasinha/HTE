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

<c:set var="vacantPost" value="${resValue.vacantPost}"></c:set>
<c:set var="filledPost" value="${resValue.filledPost}"></c:set>
<c:set var="totalNetSalary" value="${resValue.totalNetSalary}"></c:set>
<c:set var="totalNoOfPost" value="${resValue.totalNoOfPost}"></c:set>

<c:set var="userpostlist" value="${resValue.userpostlist}"></c:set>
<c:set var="fieldList" value="${resValue.fieldCaptionList}"></c:set>
<%--<c:set var="orgPostDtlList" value="${resValue.orgPostDtlList}"></c:set>--%>
<c:set var="orderList" value="${resultObj.resultValue.orderList}"></c:set>


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
		var postType = document.getElementById('postTypeCmbBox').value;
		var subPostTemp = document.getElementById("postSubTypeCmbBoxTemp").value;
		var subPostPerm = document.getElementById("postSubTypeCmbBoxPerm").value;
		if(postType == -1){
			//alert('Please select post type');
			document.getElementById("postTypeCmbBox").focus();
			return false;
		}
		else if(postType == 10001198129)
		 {
			 //alert("i am in permenet");
			
			 if(subPostPerm ==-1){
				 	 alert('Please select sub post type');
					document.getElementById("postSubTypeCmbBoxTemp").focus();
					return false;
				 }
			 else {
				 var url="./ifms.htm?actionFlag=showPostReports&subPostPerm="+subPostPerm;  // +"&billGrpId="+billGrpId;
					document.frmAdminCrtPost.action=url;
					document.frmAdminCrtPost.submit();
					showProgressbar("Please wait...");
					return true;
				 }
		 }
		 else if(postType == 10001198130)
		 {
			//alert("i am in temporary");
			
			 if(subPostTemp ==-1){
				 	 alert('Please select sub post type');
					document.getElementById("postSubTypeCmbBoxPerm").focus();
					return false;
				 }
			 else {
				 var url1="./ifms.htm?actionFlag=showPostReports&subPostTemp="+subPostTemp;   //"&billGrpId="+billGrpId;
					document.frmAdminCrtPost.action=url1;
					document.frmAdminCrtPost.submit();
					showProgressbar("Please wait...");
					return true;
				 }
		 }
		 

		
	}

	function fieldDisplay()
	{
		
		 var commId = document.frmAdminCrtPost.postTypeCmbBox.value;
			//alert('in function');
		 if(commId == 10001198129)
		 {
			// alert("i am in permenet");
			
			 document.getElementById("tempSubPost").style.display="none";
			 document.getElementById("permSubPost").style.display="block";
			 document.getElementById("tempSubPost1").style.display="none";
			 document.getElementById("permSubPost1").style.display="block";
		 }
		 else if(commId == 10001198130)
		 {
			//alert("i am in temporary");
			 
			 document.getElementById("tempSubPost").style.display="block";
			 document.getElementById("permSubPost").style.display="none";
			 document.getElementById("tempSubPost1").style.display="block";
			 document.getElementById("permSubPost1").style.display="none";

		 }
		 else if (commId == -1)
		 {
				alert("i am in select");
				 
				 document.getElementById("tempSubPost").style.display="none";
				 document.getElementById("permSubPost").style.display="none";
				 document.getElementById("tempSubPost1").style.display="none";
				 document.getElementById("permSubPost1").style.display="none";

			 }
		 	
		 }
</script>
</head>
<body>
<c:set value="0" var="i"></c:set>
<hdiits:form name="frmAdminCrtPost" validate="true" method="post" encType="multipart/form-data">
		<table align="center" width="85%">
		<tr>
			
			
			<td><!-- Nature of Post(Temp/permanent)  --> <hdiits:caption
				captionid="admin.SelectTypePost" bundle="${adminCreatePostLabel}" />
			&nbsp;</td>

			<td><hdiits:select name="postTypeCmbBox" mandatory="true"
				id="postTypeCmbBox" onchange="fieldDisplay();">
			 	<hdiits:option value="-1">-- Select -- </hdiits:option>
				<hdiits:option value="10001198130"> Temporary </hdiits:option>	 
				<hdiits:option value="10001198129" > Permanent </hdiits:option>
				<hdiits:option value="10001198155" > Statutory </hdiits:option>
			</hdiits:select>
		</td>
		
		
		<td id="tempSubPost" style="display:none">
		<hdiits:caption captionid="admin.SelectSubTypePost" bundle="${adminCreatePostLabel}" />
			&nbsp;</td>
			<td id="tempSubPost1" style="display:none">
			<hdiits:select name="postSubTypeCmbBoxTemp" mandatory="true" id="postSubTypeCmbBoxTemp" >
			 	<hdiits:option value="-1">-- Select -- </hdiits:option>
				<hdiits:option value="10001198157"> Adhoc </hdiits:option>	 
				<hdiits:option value="10001198155" > Statutory </hdiits:option>			
				<hdiits:option value="10001198154" > Honorary </hdiits:option>
				<hdiits:option value="10001198153" > Contract </hdiits:option>
				<hdiits:option value="10001198175" > Clock Hour Basis </hdiits:option>
			</hdiits:select>
			</td>
			
		
		<td id="permSubPost" style="display:none">
			<hdiits:caption captionid="admin.SelectSubTypePost" bundle="${adminCreatePostLabel}" />
			&nbsp;</td>
			<td id="permSubPost1" style="display:none">
			<hdiits:select name="postSubTypeCmbBoxPerm" mandatory="true" id="postSubTypeCmbBoxPerm" >
			 	<hdiits:option value="-1">-- Select -- </hdiits:option>
			 	<hdiits:option value="10001198174"> Regular </hdiits:option>
				<hdiits:option value="10001198159"> Protected </hdiits:option>	 
				<hdiits:option value="10001198156" > Surplus </hdiits:option>			
			</hdiits:select>
		</td>
		
		
		<td><input name="Search" value="Search" type="button" id="Search" onclick=searchFunction();></td>
			
			
		</tr>
		<!--<tr>
		<td><a href="./hrms.htm?actionFlag=loadRenewalOfPost" style="size: 7pxl;">Renewal Of Post</a></td>
		<td/><td/><td/><td/>
	
		</tr>
		
	
	--></table>
		
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
			
		
		<display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="HTESevaarth ID" headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.sevaarthId}
		</display:column>
		
		<display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="Post Name" headerClass="datatableheader">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.postname}
		</display:column>
		
		<display:column class="tablecelltext" title="Designation"
			headerClass="datatableheader" style="text-align: LEFT;font-size:12px;"
			sortable="true">${row.dsgnname}</display:column>
			
	   <display:column class="tablecelltext" title="Salary" headerClass="datatableheader" style="text-align: LEFT;font-size:12px;" sortable="true">${row.netSalary}</display:column>
 		
		<display:setProperty name="export.pdf" value="false" />
		<c:set var="i" value="${i+1}"></c:set>
		
	</display:table>
	<c:if test="${vacantPost ne 0}">
	<display:table pagesize="20" name="${vacantPost}" id="row"
		export="false" requestURI="hrms.htm?actionFlag=showAdminPostDtl"
		requestURIcontext="false" style="width:100%;"
		>
		<display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="Total Filled Post" headerClass="datatableheader">
			${filledPost}
		</display:column>
		<display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="Total Vacant Post" headerClass="datatableheader">
			${vacantPost}
		</display:column>
		<display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="Total No. of Post" headerClass="datatableheader">
			${totalNoOfPost}
				</display:column>
		<display:column style="text-align: LEFT;font-size:12px;" class="tablecelltext" title="Total Expenditure" headerClass="datatableheader">
			${totalNetSalary}
		</display:column>
	</display:table>
	</c:if>
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
