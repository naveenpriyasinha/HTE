<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="reportFlag" value="${resValue.reportFlag}" ></c:set>
<script type="text/javascript">	

var reportFlag=${reportFlag};

function back()
{
	//alert("inside submit function");
	document.SheetRemarksSubmitted.method="POST";
	document.SheetRemarksSubmitted.action="./hrms.htm?actionFlag=SheetRemarksEmpSearch";
	showProgressbar('Opening Search Page<br>Please wait...');
	document.SheetRemarksSubmitted.submit();
}	
function submit4()
{
	if(reportFlag==1)
	window.close();
	
	document.SheetRemarksSubmitted.method="POST";
	document.SheetRemarksSubmitted.action="./hrms.htm?actionFlag=SheetRemarksEmpSearch";
	showProgressbar('Opening Search Page...<br>Please wait...');
	document.SheetRemarksSubmitted.submit();
}
</script>






<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks" var="commonLables" scope="request"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<b> 
			<hdiits:caption captionid="HR.ESS.Sheet" bundle="${commonLables}" id="Sheet"/>
		</b>
		</a>
		</li>
	</ul>
</div>

<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">

<hdiits:form name="SheetRemarksSubmitted" validate="true"   encType="multipart/form-data" >

	<table align="center">
		
			<tr>
			<td>
					<strong><b><fmt:message bundle="${commonLables}" key="HR.ESS.Submitted" /></b></strong>
			</td>
			</tr>
			
			
	</table>
	
	

	<table align="center">  

				<tr colspan="2">
				<td>
				<hdiits:submitbutton captionid="HR.ESS.BackToSearch" bundle="${commonLables}" name="BackToSearch" type="button" onclick="back();" />
			</td>
	
			<td>
				<hdiits:submitbutton captionid="HR.ESS.Close" bundle="${commonLables}" name="Close" type="button" onclick="submit4();" />
			</td>
				</tr>
	</table>

</hdiits:form>

	 </div>
	</div>
	
<script type="text/javascript">
	initializetabcontent("maintab")
</script>

	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>



<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>