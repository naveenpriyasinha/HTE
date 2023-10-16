<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.ess.wll.wll_AlertMessages" var="commonLables1" scope="request"/>
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
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>		

<fmt:setBundle basename="resources.ess.wll.wll" var="commonLables" scope="request"/>
<hdiits:form name="welfareNotify" validate="true" method="POST"  encType="multipart/form-data"
	>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.EIS.Welfare" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">



<table height="100">
		<tr>
			<td rowspan="100" colspan="40"></td>
		</tr>
		<tr rowspan="30" colspan="40">
			<th rowspan="30" colspan="40"></th>
		</tr>
				<tr>
			<th rowspan="30" colspan="40"></th>
		</tr>
	</table>

      <hr align="center" width=" 50%">
<table width="100%" align="center">
   
  
  <tr  ></tr>

    <tr><th align="center"><b><hdiits:caption captionid="HR.EIS.WelfReqApproved" bundle="${commonLables}"/></b>
	</th></tr>
  <tr></tr>
  

</table>
  <hr align="center" width="50%"/>





<br>
<br>
<br>
<br>
<br>
<br>


<table width="100%">

<tr>
<td colspan ="1" align= "center">
<hdiits:button name="Closey1" type="button" caption="Close" onclick="window.close()"  />
</td>
</tr>
</table>

</div>	
	
	</div> 
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />


</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


