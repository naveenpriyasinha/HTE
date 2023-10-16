
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<%@page import="com.tcs.sgv.common.utils.DateUtility"%>

<fmt:setBundle basename="resources.trng.TrainingMstLables" var="trngLables" scope="request" />
<fmt:setBundle basename="resources.trng.ResultLables" var="resLables" scope="request" />
<fmt:setBundle basename="resources.trng.NominationLables" var="nmLables" scope="request" />

<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/hrms/training/resultscreen.js"/></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="trngVO" value="${resValue.trainingVO}"></c:set>	
<c:set var="schVO" value="${resValue.schVO}"></c:set>	
<c:set var="resultVOList" value="${resValue.resultVOList}"></c:set>
<c:set var="counter" value="0" > </c:set>
<c:set var="size" value="${resValue.size}" > </c:set>
<c:set var="schId" value="${resValue.schId}" > </c:set>
<c:set var="attachId" value="${resValue.attachId}" > </c:set>

<hdiits:form name="result" validate="true" method="POST" encType="multipart/form-data" action="./hdiits.htm?actionFlag=insertResult">

<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.RESULT" bundle="${resLables}"></hdiits:caption> </a></li>
		</ul>
</div>

<div id="tcontent1" class="tabcontent" tabno="0">
<TABLE class="tabtable">
		<tr> 
		<td class="fieldLabel" width="20%">
		<b><hdiits:caption captionid="TR.TrainingName"	bundle="${trngLables}" /></b>
		<hdiits:hidden name="hdntrngId" id="hdntrngId" default="${trngVO.trainingMstId}"/>	
		<hdiits:hidden name="hdnschId" id="hdnschId" default="${schId}"/>	
		<hdiits:hidden name="counter" />
		<script>
			document.forms[0].counter.value= ${counter};
		</script>
		
		</td>
		<td class="fieldLabel" width="25%"><c:out value="${trngVO.trainingName}" /></td>
		<td class="fieldLabel" width="20%"><b><hdiits:caption captionid="TR.TrainingType"	bundle="${trngLables}" /></b></td>
		<td class="fieldLabel" width="25%"><c:out value="${trngVO.cmnLookupMstTrainingTypeLookupId.lookupName}" /></td>
		</tr>
		<tr>
				<fmt:formatDate value="${schVO.startDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="sDate"/>
				<fmt:formatDate value="${schVO.endDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="eDate"/>
		<td class="fieldLabel"><b><hdiits:caption captionid="TR.FromDate"	bundle="${trngLables}" /></b></td>
		<td class="fieldLabel"><c:out value="${sDate}" /></td>
		<td class="fieldLabel"><b><hdiits:caption captionid="TR.ToDate"	bundle="${trngLables}" /></b></td>
		<td class="fieldLabel"><c:out value="${eDate}" /></td>
		</tr>

		<tr height="10"></tr>
		<tr>
		<td bgcolor="blue" class="fieldLabel" colspan="6"><font color="white"><b><hdiits:caption captionid="TR.NOMINATION" bundle="${nmLables}"/></b></font></td>
		</tr>
		
		<tr>
		<c:if test="${size == 0}">
		<table id="noDataFound">
		<tr>
		<td>
		<hdiits:caption captionid="TR.NODATAFOUND" bundle="${resLables}"/>
		</td>
		</tr>
		</table>
		</c:if>
		</tr>
		
		
		
		<c:if test="${size >= 0}">
		<tr>
		<td colspan="6" width="100%">
		<table id="nomTable" border="1">
		<tr>
		<td><b><hdiits:caption captionid="TR.SRNO"	bundle="${nmLables}" /></b></td>
		<td><b><hdiits:caption captionid="TR.EMPNAME"	bundle="${nmLables}" /></b></td>
		<td><b><hdiits:caption captionid="TR.DESIGNATION"	bundle="${nmLables}" /></b></td>
		<td><b><hdiits:caption captionid="TR.OFFICE"	bundle="${resLables}" /></b></td>
		<td><b><hdiits:caption captionid="TR.DISTRICT"	bundle="${nmLables}" /></b></td>
		<td><b><hdiits:caption captionid="TR.PASS"	bundle="${resLables}" /></b></td>
		<td><b><hdiits:caption captionid="TR.CERTIISSUED"	bundle="${resLables}" /></b></td>
		<td><b><hdiits:caption captionid="TR.REMARKS"	bundle="${nmLables}" /></b></td>
		<td id="cert" style="display: none"><b><hdiits:caption captionid="TR.CERT"	bundle="${resLables}" /></b></td>
		</tr>
		<c:forEach var="row" items="${resultVOList}">
		<c:set var="counter" value="${counter + 1}" > </c:set>
		<script>
		document.forms[0].counter.value= ${counter};
		</script>
		<tr>
		<td><hdiits:hidden name="Id_${counter}" id="Id_${counter}" default="${row.resultId}"/><c:out value="${row.srNo}"/></td>
		<td><hdiits:hidden name="empId_${counter}" id="empId_${counter}" default="${row.empId}"/><c:out value="${row.empName}"/></td>
		<td><hdiits:hidden name="desig_${counter}" id="desig_${counter}" default="${row.designation}"/><c:out value="${row.designation}"/></td>
		<td><hdiits:hidden name="flag_${counter}" id="flag_${counter}" default="${row.flag}"/><c:out value="${row.office}"/></td>
		<td><hdiits:hidden name="dist_${counter}" id="dist_${counter}" default="${row.district}"/><c:out value="${row.district}"/></td>
		<td><hdiits:radio name="result_${counter}" caption="1st" value="1" default="${row.result}" validation="sel.isradio" />
			<hdiits:radio name="result_${counter}" caption="2nd" value="2" default="${row.result}" validation="sel.isradio" />
			<hdiits:radio name="result_${counter}" caption="P" value="P" default="${row.result}" validation="sel.isradio"   />
			<hdiits:radio name="result_${counter}" caption="F" value="F" default="${row.result}" validation="sel.isradio"  />
		</td>
		<td><hdiits:radio name="certi_${counter}" caption="Y" value="Y" default="${row.certiIssued}" validation="sel.isradio" onclick="checkDisplay(this,'${counter}')"/>
			<hdiits:radio name="certi_${counter}" caption="N" value="N" default="${row.certiIssued}" validation="sel.isradio" onclick="checkDisplay(this,'${counter}')"/>
		</td>
		<td><hdiits:textarea name="remarks_${counter}" caption="remarks" default="${row.remarks}"/></td>
		<td id="certf_${counter}" style="display: none" align="center"><hdiits:a  href="#"   bundle="${resLables}" captionid="TR.PRINT" onclick="genratereport(	'${schId}','${attachId}','${pageContext.request.contextPath}','${counter}')" /></td>
		</tr>
		</c:forEach>
		</table>
		</td>
		</tr>
		</c:if>
		
		
		
		
</table>  

<script type="text/javascript">
var cntsize=document.forms[0].counter.value;
document.getElementById('cert').style.display='';
		for(var i=1;i<=cntsize;i++)
		{
			var rdoChkVlauePresent = document.getElementsByName('certi_'+i);
			   if(rdoChkVlauePresent[0].checked == true)
			   {
						
						document.getElementById('certf_'+i).style.display='';
				}
				else
				{
						document.getElementById('certf_'+i).style.display='none';
				}
		}
		
</script>
		

</div>
<jsp:include page="../../core/tabnavigation.jsp" />
<script	type="text/javascript">
		initializetabcontent("maintab")
		setBackUrl('${pageContext.request.contextPath}'+"/hrms.htm?viewName=selectTrngforResult");
</script> 
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
