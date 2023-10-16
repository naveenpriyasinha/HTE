<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="actionList" value="${resultValue.actionList}">
</c:set>
<c:set var="filterDdoCode" value="${resultValue.filterDdoCode}">
</c:set>

<c:set var="filterDdoCodesize" value="${resultValue.filterDdoCodesize}">
</c:set>

<c:set var="ddo" value="${resultValue.ddo}">
</c:set>


<fmt:setBundle basename="resources.Payroll" var="constantVariables"
	scope="request" />
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}"
	scope="request">
</fmt:message>

</head>
<body>

<script type="text/javascript">

function filterByDDOCode(){
	//alert("hiii");
	var ddoCode= document.getElementById("cmbAsstDDO").value;
	//alert("ddoCode "+ddoCode);
	var url;

		url="ifms.htm?actionFlag=getOrderData&ddoCode="+ddoCode+"&flag=Y";;
		document.orderMstView.action= url;
		document.orderMstView.submit();
}

</script>

<hdiits:form method="POST" name="orderMstView" validate="true"
	id="orderMstView" action="./hrms.htm?viewName=orderMaster">
	
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
			key="OM.orderMaster" bundle="${commonLables}" /></b></a></li>
	</ul>
	</div>
	<div id="tcontent1"
		style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">




	<%--	<table  width="85%" align="center" name="searchTablePost" id="searchTablePost">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/hrms/eis/SearchOrder.jsp">
				<jsp:param name="formName" value="orderMstView"/>
				<jsp:param name="searchAction" value="actionFlag=getOrderData"/>
				<jsp:param name="backAction" value="actionFlag=getOrderHeadPostData&postSearchFlag=n"/>
				</jsp:include>

			</td>
		</tr>
		</table>	--%> <%-- Filter Start --%>

	<div align="center">

	<table align="center">
		<tr>
			<td><c:out value="DDO Code"></c:out></td>
		<td><select name="cmbAsstDDO"
			id="cmbAsstDDO" style="width: 85%,display: inline;">
			<option title="Select" value="-1" selected="selected"><c:out value="Select"></c:out></option>
			
			<c:forEach var="filterDdoCode" items="${resultValue.filterDdoCode}">
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

	<%-- Filter end --%> <br>
	&nbsp; <a
		href="./hrms.htm?actionFlag=getOrderData&edit=N&elementId=9000189">
	Add new Entry </a> <display:table name="${actionList}" requestURI=""
		pagesize="${pageSize}" id="row" export="false" style="width:100%">
		<%--<display:column  property="orderId" class="tablecelltext" title="Order Id"  headerClass="datatableheader"  />    --%>
		<display:column class="tablecelltext" title="Sanction Order No "
			headerClass="datatableheader"
			style="text-align: center;font-size:12px;">
			<%-- //commeneted to disable link as per defect sheet requirement
			  <a href="./hrms.htm?actionFlag=getOrderData&orderid=${row.orderId}&edit=Y&elementId=9000189">${row.orderName}  </a>
			  --%>
			<c:out value="${row.orderName}"></c:out>
		</display:column>

		<fmt:formatDate var="stDate" pattern="dd/MM/yyyy"
			value="${row.orderDate}" />
		<display:column class="tablecelltext" title="Order Date"
			value="${stDate}" headerClass="datatableheader"
			style="text-align: center;font-size:12px;" />
		<display:setProperty name="export.pdf" value="false" />
	</display:table> <br>
	&nbsp; <a
		href="./hrms.htm?actionFlag=getOrderData&edit=N&elementId=9000189">
	Add new Entry </a> <br />
	<br />
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script></div>
</hdiits:form>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</html>