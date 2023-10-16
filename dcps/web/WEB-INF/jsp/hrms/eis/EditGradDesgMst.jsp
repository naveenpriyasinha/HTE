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
<c:set var="resvalue" value="${resultObj.resultValue}" > </c:set>
<c:set  var="graderesultSet" value='${resvalue.graderesultSet}' />
<c:set  var="desigresultSet" value='${resvalue.desigresultSet}' /> 
<c:set  var="graddesgresult" value='${resvalue.graddesgresultSet}' /> 

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript" language="JavaScript">
function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	return true;
}
function init()
{
	//alert(${resValue.msg});
	//document.forms[0].deptname.focus();
}
</script>
<body onload="init()">
<hdiits:form name="deptMaster" validate="true" method="POST"
	action="./hdiits.htm?actionFlag=AddGradeDesignationmpg&updateflag=true&GdMapId=${graddesgresult.gdMapId}"  encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="eis.updateGradeDesgInfo" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<br>
	<TABLE align="center" width = "60%">  
	<TR>
		<TD>
		  <b><hdiits:caption captionid="eis.grade_name" bundle="${commonLables}"/>:</b>
		</TD>			
			<TD><hdiits:select  mandatory="true" name="grade" sort="false"  captionid="eis.grade_name" bundle="${commonLables}" validation="sel.isrequired" size ="1" sort="false"  >
				<hdiits:option value="" >-------------Select---------------</hdiits:option>
						<c:forEach var="graderesultSet" items="${graderesultSet}">
								<c:choose>
									<c:when test="${graddesgresult.hrEisGradeMst.gradeId==graderesultSet.gradeId}">
										<option value='<c:out value="${graderesultSet.gradeId}"/>' selected="true">
										<c:out value="${graderesultSet.gradeName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${graderesultSet.gradeId}"/>'>
										<c:out value="${graderesultSet.gradeName}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	
	
	</tr><tr></tr>
	<TR>
		<TD>
		  <b><hdiits:caption captionid="eis.desig_name" bundle="${commonLables}"/>:</b>
		</TD>			
			
			<TD><hdiits:select  mandatory="true" sort="false" captionid="eis.desig_name" bundle="${commonLables}" validation="sel.isrequired"  name="desig" size ="1" sort="false"   >
				<hdiits:option value="" >-------------Select--------------</hdiits:option>
						<c:forEach var="desigresultSet" items="${desigresultSet}">
								<c:choose>
									<c:when test="${graddesgresult.orgDesignationMst.dsgnId==desigresultSet.dsgnId}">
										<option value='<c:out value="${desigresultSet.dsgnId}"/>' selected="true">
										<c:out value="${desigresultSet.dsgnName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${desigresultSet.dsgnId}"/>'>
										<c:out value="${desigresultSet.dsgnName}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
				</hdiits:select>
			</TD>
			
	</tr>
	
	</TABLE>
	</div> 
	

	<jsp:include page="../../core/PayTabnavigation.jsp" />
</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />



</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


										