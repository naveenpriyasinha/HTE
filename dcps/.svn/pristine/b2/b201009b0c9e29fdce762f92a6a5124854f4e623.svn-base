
<%
	try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />
<fmt:setBundle basename="resources.Payroll" var="constantVariables"
	scope="request" />
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>




<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="actionList" value="${resultValue.actionList}">
</c:set>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}"
	scope="request">
</fmt:message>


<!--  <form method="POST" name="ScaleViewList" action="./hrms.htm?viewName=newScaleMaster">-->
<form method="POST" name="ScaleViewList"
	action="./hrms.htm?viewName=newScaleMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
							key="SM.scaleMaster" bundle="${commonLables}" /></b></a></li>
		</ul>
	</div>
	<div id="tcontent1"
		style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

		<BR /> &nbsp; <a
			href="./hrms.htm?actionFlag=fillScaleDefaultComponents&elementId=9000187">
			Add new Entry </a> <br>

		<display:table name="${actionList}" requestURI=""
			pagesize="${pageSize}" id="row" export="false">

			<%-- 		 <display:column  property="scaleId" class="tablecelltext" title="Scale Id"  headerClass="datatableheader" /> --%>


			<display:column class="tablecelltext" title="Scale"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;" sortable="true">
				<c:choose>
					<c:when test="${row.hrPayCommissionMst.id eq 2500340}">
						<c:choose>
							<c:when test="${row.scaleHigherIncrAmt ne 0}">
								<c:choose>
									<c:when test="${row.migratedScale eq 0}">
										<a
											href="./hrms.htm?actionFlag=getScaleData&scaleid=${row.scaleId}&edit=Y">
											${row.scaleStartAmt} - ${row.scaleIncrAmt} -
											${row.scaleEndAmt} - ${row.scaleHigherIncrAmt} -
											${row.scaleHigherEndAmt} </a>
									</c:when>
									<c:otherwise>
		 	     ${row.scaleStartAmt} - ${row.scaleIncrAmt} - ${row.scaleEndAmt} - ${row.scaleHigherIncrAmt} - ${row.scaleHigherEndAmt}
		 	</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${row.migratedScale eq 0}">
										<a
											href="./hrms.htm?actionFlag=getScaleData&scaleid=${row.scaleId}&edit=Y">
											${row.scaleStartAmt} - ${row.scaleIncrAmt} -
											${row.scaleEndAmt} </a>
									</c:when>
									<c:otherwise>
		 	      ${row.scaleStartAmt} - ${row.scaleIncrAmt} - ${row.scaleEndAmt}
		 	</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${row.migratedScale eq 0}">
								<a
									href="./hrms.htm?actionFlag=getScaleData&scaleid=${row.scaleId}&edit=Y">
									${row.scaleStartAmt} - ${row.scaleEndAmt}</a>
							</c:when>
							<c:otherwise>
		 	${row.scaleStartAmt} - ${row.scaleEndAmt}
		 	 </c:otherwise>
						</c:choose>

					</c:otherwise>
				</c:choose>
				<%-- <a href="./hrms.htm?actionFlag=getScaleData&scaleid=${row.scaleId}&edit=Y" > ${row.scaleStartAmt} - ${row.scaleIncrAmt} - ${row.scaleEndAmt} - ${row.scaleHigherIncrAmt} - ${row.scaleHigherEndAmt}</a> --%>
			</display:column>

			<display:column class="tablecelltext" title="Grade Pay"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;" sortable="true">
				<c:choose>
					<c:when test="${row.hrPayCommissionMst.id eq 2500340}">
		   NA
		 
		   </c:when>
					<c:otherwise>
		    ${row.scaleGradePay}
		   </c:otherwise>
				</c:choose>
			</display:column>
			<fmt:formatDate var="stDate" pattern="dd/MM/yyyy"
				value="${row.scaleEffFromDt}" />
			<fmt:formatDate var="endDate" pattern="dd/MM/yyyy"
				value="${row.scaleEffToDt}" />
			<display:column class="tablecelltext" title="Start Date"
				value="${stDate}" headerClass="datatableheader"
				style="text-align: center;font-size:12px;" sortable="true" />
			<display:column class="tablecelltext" title="End Date"
				value="${endDate}" headerClass="datatableheader"
				style="text-align: center;font-size:12px;" />
			<display:column class="tablecelltext" title="Pay Commission"
				value="${row.hrPayCommissionMst.commissionName}"
				headerClass="datatableheader"
				style="text-align: center;font-size:12px;" sortable="true" />



			<display:setProperty name="export.pdf" value="false" />
		</display:table>

		<BR /> <br />
		<br />
		<script type="text/javascript">
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
			initializetabcontent("maintab")
		</script>
	</div>
</form>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
