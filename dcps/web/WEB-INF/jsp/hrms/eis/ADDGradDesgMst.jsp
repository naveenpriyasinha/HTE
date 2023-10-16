<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
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
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
  <c:set  var="graderesultSet" value='${resultObj.resultValue.graderesultSet}' />
  <c:set  var="desigresultSet" value='${resultObj.resultValue.desigresultSet}' /> 
  <c:set var="msg" value="${resultObj.resultValue.msg}" ></c:set>
  <c:set var="result" value="${resultObj.resultValue.result}" ></c:set>
  

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

${resValue.msg}


<script type="text/javascript" language="JavaScript">
function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	return true;
}
function init()
{
	//document.forms[0].deptname.focus();
}
</script>
<body onload="init()">
<hdiits:form name="deptMaster" validate="true" method="POST"
	action="./hdiits.htm?actionFlag=AddGradeDesignationmpg" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.insertGradeDesgInfo" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<br/>
	<TABLE align="center"  width = "60%">  
	<TR>
		<TD>
		  <b>	<fmt:message key="eis.grade_name" bundle="${commonLables}"/></b>
		</TD>			
			<TD><hdiits:select  mandatory="true" sort="false"  captionid="eis.grade_name" bundle="${commonLables}" validation="sel.isrequired"  name="grade" size ="1"  >
				<hdiits:option value="" selected="true">------------------------Select-----------------------</hdiits:option>
						<c:forEach var="graderesultSet" items="${resultObj.resultValue.graderesultSet}">
							<option value='<c:out value="${graderesultSet.gradeId}"/>' >
								<c:out value="${graderesultSet.gradeName}"/>
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	
	
	</tr><tr></tr>
	<TR>
		<TD>
		  <b> <fmt:message key="eis.desig_name" bundle="${commonLables}"/></b>
		</TD>			
			<TD><hdiits:select  mandatory="true" sort="false" captionid="eis.desig_name" bundle="${commonLables}" validation="sel.isrequired"  name="desig" size ="1"   >
				<hdiits:option value="" selected="true">------------------------Select-----------------------</hdiits:option>
						<c:forEach var="desigresultSet" items="${resultObj.resultValue.desigresultSet}">
							<option value='<c:out value="${desigresultSet.dsgnId}"/>' >
								<c:out value="${desigresultSet.dsgnName}"/>
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	</TABLE>
	</div> 
	 <hdiits:hidden default="getGradDesgMap" name="givenurl"/>	
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		/*if("${msg}"!=null&&"${msg}"!='')
		{
			if("${result}"!=null&&"${result}"!=''&&"${result}"=="0")
			{
				alert("${msg}");
				var url="./hrms.htm?actionFlag=GradeDesignationScaleMaster";
				document.deptMaster.action=url;
				document.deptMaster.submit();
			}
			if("${result}"!=null&&"${result}"!=''&&"${result}"=="-1")
			{
				alert("${msg}");
				var url="./hrms.htm?actionFlag=getGradDesgMap";
				document.deptMaster.action=url;
				document.deptMaster.submit();
			}
		}*/
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getGradDesgMap";
			document.deptMaster.action=url;
			document.deptMaster.submit();
		}
		document.forms[0].elements['grade'].focus();

</script>

</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


