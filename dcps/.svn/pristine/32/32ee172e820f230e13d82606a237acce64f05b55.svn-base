<html>
<head>
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<!-- Added by Paurav -->

<!-- Ended By Paurav -->
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="arrearBill" value="${resValue.arrearBill}" ></c:set>


<hdiits:form name="frmPaybillPara" validate="true" method="POST" action="./hrms.htm?actionFlag=deleteArrearBill" >
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>Arrear Bills</b></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">	  
	<div id="tcontent1" class="halftabcontent">
	 <div align="center"> <h1>
	   <c:out value="${resultValue.msg}" />  </h1> </div> <br>
		  <a href= "./hrms.htm?actionFlag=getPaybillPara">  New Arrear Bill </a>  
		  
		  <display:table name="${arrearBill}" requestURI="" pagesize="15" defaultsort="1" defaultorder="ascending" id="row" export="true" style="width:100%">
			
				<!-- Added by Paurav for Bill Forwarding Selection -->
				<display:column class="tablecelltext" title=" " headerClass="datatableheader"  > 
					<input type="checkbox" name="chkArrearId" value="${row.paybillGrpId}">
					
 				</display:column>
				<!-- Ended by Paurav -->
		  	 <%-- %><display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader">
		  	 <a href="./hrms.htm?actionFlag=getOtherData&otherId=${row.otherId}&edit=Y" > ${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname} </a>
		  	 </display:column>--%>	
			  <display:column class="tablecelltext" title="Bill No" headerClass="datatableheader" value="${row.billNo}"  > </display:column>	
			  <display:column class="tablecelltext" title="Month" headerClass="datatableheader" value="${row.month}"  > </display:column>	
			  <display:column class="tablecelltext" title="Year" headerClass="datatableheader" value="${row.year}"  > </display:column>	
		
			  <display:column class="tablecelltext" title="Gross Amount" headerClass="datatableheader" value="${row.grossAmt}"  > </display:column>	
			  <display:column class="tablecelltext" title="Net Total" headerClass="datatableheader" value="${row.netTotal}"  > </display:column>	
			   <fmt:formatDate var="orderDate" pattern="dd/MM/yyyy" value="${row.salRevId.revOrderDate}"/>
			  <display:column class="tablecelltext" title="Order" headerClass="datatableheader" value="${row.salRevId.revOrderNo} ${orderDate} "  > </display:column>	
		
		<display:column class="tablecelltext" title=" Outer" headerClass="datatableheader"  > 
					<a href= "./hrms.htm?actionFlag=generateArrearOuter&arrearGrpId = ${row.paybillGrpId}">  Generate </a>
					
 				</display:column>
		
		</display:table>

	</tr>
	</table>
 	</div>
 	<div id="tcontent2" class="tabcontent" tabno="1">
 	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		

		</script>
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
	<!-- Added by Paurav for providing Bill Forwarding functionality -->	
	<hdiits:submitbutton name="deleteButton" caption="Delete" value="Delete" />	
	<!-- Ended By Paurav -->
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</html>
	