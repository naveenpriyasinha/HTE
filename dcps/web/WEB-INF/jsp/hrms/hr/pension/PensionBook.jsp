<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.hr.pension.Pension" var="pensionLabel" scope="request" />
<script type="text/javascript">
var empArray = new Array();
function searchForEmp()
{
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
	window.open(href,'cheild', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}
function empSearch(from)
{
	for(var i=0; i<from.length; i++)
	{
		empArray[i] = from[i].split("~"); 		
	}	
	if(from.length>0)
	{
		var single = empArray[0];
		userId=single[2];		
		document.getElementById('selectUserId').value=single[2];
		document.getElementById('userName').value=single[1];				
	}	
}
</script>
<hdiits:form name="pensionBook" validate="true"
action="hdiits.htm?actionFlag=SearchPensionBook" method="post" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1"> <b><fmt:message key="Button.PrintBook" bundle="${pensionLabel}"/></b> </a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<c:if test="${resultValue.msg eq 1}">
		<font color="red"><b><fmt:message key="Pension.userSelect" bundle="${pensionLabel}"/></b></font>	
	</c:if>
	<center>
			<fmt:message key="Pension.EmpName" bundle="${pensionLabel}"/> : 
			<hdiits:text name="userName" id="userName" caption="Text" readonly="true" mandatory="true" validation="txt.isrequired"></hdiits:text>
			<hdiits:hidden id="selectUserId" name="selectUserId"></hdiits:hidden>
			&nbsp;<hdiits:image id="img" name="img"
			source="./images/search_icon.gif" onclick="searchForEmp();" >
			</hdiits:image>			
			<br><br>
			<hdiits:formSubmitButton name="submitForm" type="button" captionid="Pension.btnSubmit" bundle="${pensionLabel}"/>
	</center>
	</div>
	</div>
<hdiits:validate controlNames="" locale='<%=(String)session.getAttribute("locale")%>' />		
</hdiits:form>
<script type="text/javascript">
		initializetabcontent("maintab")		
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>