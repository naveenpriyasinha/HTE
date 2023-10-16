<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="PayLab" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="strNewPayFixDate" value="${resValue.strNewPayFixDate}" />
<c:set var="option2" value="${resValue.option2}" />
<c:set var="option2date" value="${resValue.option2date}" />
<c:set var="option11" value="${resValue.option11}" />
<c:set var="option11date" value="${resValue.option11date}" />
<c:set var="option12" value="${resValue.option12}" />
<c:set var="option12date" value="${resValue.option12date}" />


<hdiits:form name="frmVac" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="PAY_CAL" bundle="${PayLab}"></hdiits:caption></b></a></li>
	</ul>
</div>
	
<div class="tabcontentstyle">
	
<div id="tcontent1" class="tabcontent" tabno="0">	

	<table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
			<td><font class="Label3" align="center" ><u><font class="Label3" align="center" color="white"><b><fmt:message key="PAY_CAL" bundle="${PayLab}"></fmt:message></b></u><span class="UserText" lang="en-us"></span></font></td>
		</tr> 
	</table>
   	<br><br>

	<table id="comparison" width="60%" align="center" border="1"  bordercolor="black" >
		<tr>
			<td width="15%" align="center" class="tabcontentstyle"><b><hdiits:caption captionid="Pf.Options" bundle="${PayLab}"/></b></td>
			<td width="15%" align="center" class="tabcontentstyle"><b><hdiits:caption captionid="Pf.efffrom" bundle="${PayLab}"/></b>
			</td>
			<td width="15%" align="center" class="tabcontentstyle"><b><hdiits:caption captionid="Pf.newbasic" bundle="${PayLab}"/></b>
			</td>
			<td width="15%" align="center" class="tabcontentstyle"><b><hdiits:caption captionid="Pf.nextincr" bundle="${PayLab}"/></b>
			</td>
		</tr>
		
		<tr>
			<th width="15%" rowspan="2" align="center" class="tabcontentstyle"><b><hdiits:caption captionid="Pf.option1" bundle="${PayLab}"/></b></th>
			<td width="15%" align="center"><fmt:formatDate value="${strNewPayFixDate}" pattern="dd/MM/yyyy"/></td>
			<td width="15%" align="center"><c:out value="${option11}" default=""/></td>
			<td width="15%" align="center"><c:out value="${option11date}" default=""/></td>
		</tr>
		
		<tr>
			<td width="15%" align="center"><c:out value="${option11date}" default=""/></td>
			<td width="15%" align="center"><c:out value="${option12}" default=""/></td>
			<td width="15%" align="center"><c:out value="${option12date}" default=""/></td>
		</tr>
		
		<tr>
			<td width="15%" align="center" class="tabcontentstyle"><b><hdiits:caption captionid="Pf.option2" bundle="${PayLab}"/></b></td>
			<td width="15%" align="center"><fmt:formatDate value="${strNewPayFixDate}" pattern="dd/MM/yyyy"/></td>
			<td width="15%" align="center"><c:out value="${option2}" default=""/></td>
			<td width="15%" align="center"><c:out value="${option2date}" default=""/></td>
		</tr>
	</table>

<BR><BR><BR>
<table align="center">
	<tr><td>
			<hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${PayLab}" onclick="window.close();"></hdiits:button>
	</td></tr>
</table>


	</div>	
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	
	

</hdiits:form>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
