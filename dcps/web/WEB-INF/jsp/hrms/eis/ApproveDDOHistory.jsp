<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ddoList" value="${resValue.ddoHistoryList}"></c:set>
<script>
function approve(srNo,ddoCode,ddoName,userID)
{
	//alert("inside approve SrNo.: "+srNo+" DDO Code: "+ddoCode+" DDO Name: "+ddoName+" user ID: "+userID);

	//Commented by roshan
	//var url = "ifms.htm?actionFlag=aprvDDOHistory&srNo="+srNo+"&ddoCode"+ddoCode+"&ddoName"+ddoName+"&userID"+userID;
	//ADDED BY ROSHAN
	var url = "ifms.htm?actionFlag=aprvDDOHistory&srNo="+srNo+"&ddoCode="+ddoCode+"&ddoName="+ddoName+"&userID="+userID;
	document.approveDDOHistory.action = url ;
	alert("The DDO has been Approved successfully!");
	document.approveDDOHistory.submit();
}
</script>

<script>
function reject(srNo,ddoCode,ddoName,userID)
{
	//alert("inside reject SrNo.: "+srNo+" DDO Code: "+ddoCode+" DDO Name: "+ddoName);

	var url = "ifms.htm?actionFlag=rjctDDOHistory&srNo="+srNo+"&ddoCode"+ddoCode+"&ddoName"+ddoName+"&userID"+userID;
	document.approveDDOHistory.action = url ;
	document.approveDDOHistory.submit();
}
</script>



<hdiits:form name="approveDDOHistory" id="approveDDOHistory" encType="multipart/form-data" validate="true" method="post">
<br/><br/>
<fieldset class="tabstyle" ><legend>Approve/Reject DDO History.</legend>
<br/>

<display:table list="${ddoList}"  id="vo" style="width:100%"  pagesize="500" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="DDO Name" >					
				<c:out value="${vo[2]}"></c:out>
				
		</display:column>
				
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="DDO Code" >		
				<c:out value="${vo[1]}"></c:out>
		</display:column>
				
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="From date" >
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}" var="stDate"/>					
				<c:out value="${stDate}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="To Date" >	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="edDate"/>
				<c:out value="${edDate}"></c:out>
		</display:column>
		
		<!--
		
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Status">
		<c:if test="${vo[5]=='0'}"><c:out value="Saved"></c:out> </c:if>
		<c:if test="${vo[5]=='1'}"><font color="green"><c:out value="Approved"></c:out></font> </c:if>
		<c:if test="${vo[5]=='2'}"><font color="red"><c:out value="Rejected"></c:out></font> </c:if>
		</display:column>
		
		-->
		
		<display:column  headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Approve/Reject" >	
			<a href="#" onclick="approve('${vo[0]}','${vo[1]}','${vo[2]}','${vo[6]}')">Approve</a>&nbsp;/&nbsp;<a href="#" onclick="reject('${vo[0]}','${vo[1]}','${vo[2]}')">Reject</a>
		</display:column>
		
		
</display:table>

<br/>
<%--
<div align="center">
		<hdiits:button	name="BTN.UPDATE" id="btnUpdate" type="button"
											captionid="BTN.UPDATE" bundle="${commonLables}"
											onclick="updateDDODetailsDetails();" />
	</div> 
	--%>
</fieldset>



</hdiits:form>