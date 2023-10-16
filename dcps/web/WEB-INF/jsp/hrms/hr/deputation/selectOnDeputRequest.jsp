
<%
try {
%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"
	scope="request" />

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="adddepLst" value="${resultValue.adddepLst}">
</c:set>
<c:set var="statusFlag" value="${resultValue.statusFlag}">
</c:set>

<style type="text/css">


.tablecell{
   border-bottom: solid 1px #DEDEDE;
	border:1;
	border-color:black;
	font-size: 11px;
	font-style: normal;
	font-weight: normal;
	background: white;
	text-align: left;
	padding: 1px;
	
}
.tablerow{
  border-top: solid 1px #333333;
	border-left: solid 1px #666666;
	border-right: solid 0px #888888;
	border-bottom: solid 1px #666666; 
	font-size: 11px;
	font-style: normal;
	font-weight: bold;
	text-align: center;
	background: #C9DFFF;
	color: black;
	padding: 1px;
	
	
}
</style>
<script type="text/javascript">

function clearf(form)
{
			var idf=form.id; 
			var href='./hrms.htm?actionFlag=deputReqViewDtl&fileId='+idf;
		window.open(href,'chield', 'width=750,height=400,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
			
				
}

</script>



<hdiits:form name="selectOndeputReq" validate="true" method="POST">
 
<c:if test="${resultValue.statusFlag eq \"REJECT\"}">
<table width="100%">
<tr>
<td align="center" width="100%">
<font color="RED" size="3%" > <b>
		<fmt:message key="Dep.RequestIsReject" bundle="${comLable}"></fmt:message></b> </font>
 </td>
 </tr>
 </table>
</c:if>
<div>
	
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<c:if test="${ empty resultValue.ActvAddLst }">	
<hdiits:fieldGroup titleCaptionId="Dep.EmpDeputReq" bundle="${comLable}"  collapseOnLoad="true">
	<table width="100%">

		<tr>
			<td>

		
			<c:set var="j" value="0" /> <%
 int b=0;
 %> <display:table list="${resultValue.adddepLst}" id="row"
				requestURI="" export="false" style="width:100%" offset="1">


				<display:setProperty name="paging.banner.placement" value="bottom" />

				<display:column class="tablecell" titleKey="SrNo" 
					headerClass="tablerow" value="<%=b=b+1 %>"
					style="text-align: center" >
				</display:column>

				<display:column class="tablecell" titleKey="reqforclass"
					headerClass="tablerow" style="text-align: center"
					>
			
			${row.reqForClass}
		</display:column>
				<display:column class="tablecell" titleKey="reqforpost" 
					headerClass="tablerow" style="text-align: center"
					>
			
			${row.reqForPost}
		</display:column>


				<display:column class="tablecell" titleKey="deputFromDate"
					headerClass="tablerow" style="text-align: center"
					>
			<fmt:formatDate value="${row.reqFromDate}" pattern="dd/MM/yyyy"/>
			
		</display:column>

				<display:column class="tablecell" titleKey="deputToDate"
					headerClass="tablerow" style="text-align: center"
					>
			<fmt:formatDate value="${row.reqToDate}" pattern="dd/MM/yyyy"/>
			
		</display:column>


				<display:column class="tablecell" titleKey="addressForDeputn"
					headerClass="tablerow" style="text-align: center"
					>
			
			${row.address}
		</display:column>
				<display:column class="tablecell" titleKey="viewDetails" 
					headerClass="tablerow" style="text-align: center"
					>

					<a href="#" name="${row.deputationreqmtId}"
						id="${row.deputationreqmtId}" onclick="clearf(this)">Details </a>
				</display:column>

				<c:set var="j" value="${j+1}" />

			</display:table></td>
		</tr>
	



	</table>
	</hdiits:fieldGroup>
	</c:if>
<c:if test="${not empty resultValue.ActvAddLst }">	
<hdiits:fieldGroup titleCaptionId="Dep.EmpDeputReq" bundle="${comLable}"  collapseOnLoad="true">
	<table width="100%">

		<tr>
			<td>

		
			<c:set var="k" value="0" /> <%
 int c=0;
 %> <display:table list="${resultValue.ActvAddLst}" id="row"
				requestURI="" export="false" style="width:100%" offset="1">


				<display:setProperty name="paging.banner.placement" value="bottom" />

				<display:column class="tablecell" titleKey="SrNo" 
					headerClass="tablerow" value="<%=c=c+1 %>"
					style="text-align: center" >
				</display:column>

				<display:column class="tablecell"  titleKey="Dep.select"
					headerClass="tablerow" style="text-align: center">
					<hdiits:checkbox id="${row.distributionId}" name="checked"
						value="YES" default="YES" readonly="true" />

				</display:column>


				<display:column class="tablecell" titleKey="reqforclass"
					headerClass="tablerow" style="text-align: center"
					>
			
			${row.reqForClass}
		</display:column>
				<display:column class="tablecell" titleKey="reqforpost" 
					headerClass="tablerow" style="text-align: center"
					>
			
			${row.reqForPost}
		</display:column>


				<display:column class="tablecell" titleKey="deputFromDate"
					headerClass="tablerow" style="text-align: center"
					>
			<fmt:formatDate value="${row.reqFromDate}" pattern="dd/MM/yyyy"/>
			
		</display:column>

				<display:column class="tablecell" titleKey="deputToDate"
					headerClass="tablerow" style="text-align: center"
					>
		<fmt:formatDate value="${row.reqToDate}" pattern="dd/MM/yyyy"/>
			
		</display:column>


				<display:column class="tablecell" titleKey="addressForDeputn"
					headerClass="tablerow" style="text-align: center"
					>
			
			${row.address}
		</display:column>
				<display:column class="tablecell" titleKey="viewDetails" 
					headerClass="tablerow" style="text-align: center"
					>

					<a href="#" name="${row.deputationreqmtId}"
						id="${row.deputationreqmtId}" onclick="clearf(this)">Details </a>
				</display:column>

				<c:set var="k" value="${k+1}" />

			</display:table></td>
		</tr>
		<tr>
			<td>
			
			</td>

		</tr>
	</table>
	</hdiits:fieldGroup>
	</c:if>
		
	</div>
	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
