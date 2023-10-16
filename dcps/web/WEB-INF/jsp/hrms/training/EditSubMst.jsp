<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp" %>
<%@page import="java.util.ResourceBundle"%>
<%ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.trng.submasLables");		
String btn= resourceBundle.getString("TN.SUBMIT"); 
%>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<fmt:setBundle basename="resources.trng.submasLables" var="submasLables" scope="request" />

<fmt:message key="TR.SELECT" bundle="${submasLables}" var="selSubject"></fmt:message>

<caption><font color="blue"> 
			<c:set var="resultObj"	value="${result}"/>
			<c:set var="resultMap" value="${resultObj.resultValue}"/>
			<c:set var="msg" value="${resultMap.msg}"> </c:set>
			<c:out value="${msg}"/>			 
 		</font></caption>
 	 
			<c:set var="SubjectnameList" value="${resultMap.SubNameList}"/>

<hdiits:form name="frmSubjectMst" validate="true" method="post" action="./hdiits.htm?actionFlag=viewSubject" encType="multipart/form-data">

<hdiits:hidden name="editFlag" default="yes"/>
<div id="tabmenu">
   <ul id="maintab" class="shadetabs"><li  class="selected"><a href="#"  rel="tcontent1"><hdiits:caption	captionid="TR.submst" bundle="${submasLables}" /> </a></li></ul>
</div>

<div class="tabcontentstyle">  
	<div id="tcontent1" class="tabcontent" tabno="fmkghcf">
	<table class="tabtable">
     <tr>
    		<td class="fieldLabel" width="25%">
    			<hdiits:caption captionid="TR.SUB_NAME" bundle="${submasLables}"/>
    		</td>

			<td class="fieldLabel" width="25%">		
			<hdiits:select captionid="TR.SUB_NAME" bundle="${submasLables}" size="1" style="width:67%" name="sub_id" validation="sel.isrequired" mandatory="true">	
		      	<hdiits:option value="SubjectId" selected="true"><c:out value='${selSubject}'/>
					<c:forEach var="obj" items="${SubjectnameList}">
						<option value="${obj.subjectId}" >${obj.subjectName}</option>
					</c:forEach>	
				</hdiits:option>				
			</hdiits:select> 
				
 			</td>	
    		<td class="fieldLabel" width="25%">		</td>	
    		<td class="fieldLabel" width="25%">		</td>				
	</tr>
	
</table>
</div>
<script type="text/javascript">var navDisplay = false;</script>
</div> 
	<jsp:include page="../../core/tabnavigation.jsp" >
	<jsp:param name="disableReset" value="${resetBtnDisabled}" />
	<jsp:param name="disableSubmit" value="${submitBtnDisabled}" />
	<jsp:param name="submitText" value='${btn}'/>
	</jsp:include>	 

<script type="text/javascript">
		initializetabcontent("maintab")
</script>
   <hdiits:validate locale="${ sessionScope.locale }" />
</hdiits:form>