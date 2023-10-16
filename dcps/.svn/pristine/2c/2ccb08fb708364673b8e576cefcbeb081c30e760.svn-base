<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">


function submit1()
{


	document.HierarchyChangeSubmittedByAdmin.method="POST";

	document.HierarchyChangeSubmittedByAdmin.action="./hrms.htm?actionFlag=getDocListOfWorkList";
	
	document.HierarchyChangeSubmittedByAdmin.submit();
	
}


</script>





<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> 
			<hdiits:caption captionid="HR.ACR.ACR" bundle="${commonLables}"/>
		</b></a></li>
	</ul>
</div>
<div >
	 <div id="tcontent1"  tabno="0">




<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	


<c:set var="msgFlag" value="${resValue.msgFlag}"></c:set>




<hdiits:form name="HierarchyChangeSubmittedByAdmin" method="POST" validate="true"  encType="multipart/form-data" >
		<hr width="80%" align="center">
		<table class="tabtable" align="center">
				<tr>
					<td align="center" class="fieldLabel" width="100%">
						<c:if test="${msgFlag == 0}">
							<b><hdiits:caption captionid="HR.ACR.HierarchySetUser" bundle="${commonLables}"/></b>
						
						</c:if>
						<c:if test="${msgFlag == 1}">
							<b><hdiits:caption captionid="HR.ACR.HierarchySetGroup" bundle="${commonLables}"/></b>
						
						</c:if>
						
						
					</td>
				</tr>
					
		</table>
	<hr width="80%" align="center">
		<br><br><br><br>
		<table id="submit"  align="center">
		<tr colspan="1">
							
							<td>
								<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="window.close();"/>
		
							</td>
				</tr>
			
		</table>
		
		


</hdiits:form>	
	<jsp:include page="../core/PayTabnavigation.jsp" />
	 
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
<hdiits:validate locale="${locale}" controlNames="" /></div>
</div>


	<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	