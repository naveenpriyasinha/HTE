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
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">


function submit1()
{
	document.GroupCreatedPage.method="POST";
	document.GroupCreatedPage.action="./hrms.htm?actionFlag=ACRHiGroupMstPage";	
	document.GroupCreatedPage.submit();
	
}

//function to be called on press of close
function closeButtonHandler()
{
	document.GroupCreatedPage.method="POST";
	document.GroupCreatedPage.action="./hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";	
	document.GroupCreatedPage.submit();
}
</script>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> 
			<fmt:message key="HR.ACR.ACR" bundle="${commonLables}"/>
		</b></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<br><br><br><br>
<hdiits:form name="GroupCreatedPage" method="POST" validate="true"  encType="multipart/form-data" >
		<hr width="80%" align="center">
		<table class="tabtable" align="center">
				<tr>
					<td class="fieldLabel" width="100%" align="center">
						
						<b><hdiits:caption captionid="HR.ACR.GroupEntered" bundle="${commonLables}"/></b>
						
					</td>
				</tr>
		</table>
	<hr width="80%" align="center">
		<br><br><br><br>
		<table id="submit"  align="center">
		<tr colspan="2">
							<td>
								<hdiits:button captionid="HR.ACR.GroupPage" bundle="${commonLables}" name="AcceptChange" type="button"   onclick="submit1();"/>
							</td>
							<td>
								<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="closeButtonHandler()"/>
							</td>
				</tr>
		</table>
</hdiits:form>	
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
	