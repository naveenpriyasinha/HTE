<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.qtr.qtr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>


<fmt:setBundle basename="resources.hr.qtr.qtr" var="commonLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="flagForAction" value="${resValue.flagForAction}"></c:set>
<c:set var="reportFlag" value="${resValue.reportFlag}"></c:set>

<script>	
var flagForAction=${flagForAction};
var reportFlag=${reportFlag};
function submit1(){

	
	document.qtrEntry.method="POST";
	if(flagForAction==2)
		document.qtrEntry.action="./hrms.htm?actionFlag=getQtrSearchEntry";
	else
		document.qtrEntry.action="./hrms.htm?actionFlag=getQtrMstScr";
	showProgressbar('Opening Page...<br>Please wait...');
	document.qtrEntry.submit();
}

function submit4()
{
	//alert("inside submit function");
	
	if(reportFlag==1)
	window.close();
	document.qtrEntry.method="POST";
	document.qtrEntry.action="./hdiits.htm?actionFlag=getHomePage";
	showProgressbar('Opening Home Page...<br>Please wait...');
	document.qtrEntry.submit();
}
</script>


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.QTR.QTR" bundle="${commonLables}" /></b></a></li>
	</ul>
</div>

<hdiits:form name="qtrEntry" validate="true" method="post" encType="multipart/form-data" >
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">

<br><br><br>
<br><br><br>
<br><br><br>
<hr width="80%" align="center">
<table class="tabtable">
<tr>
	<td width="10%"></td>
	<c:if test="${flagForAction == 1 && reportFlag == 0}">
	
	<td>
	<b><center><hdiits:caption captionid="HR.QTR.intermMaster" bundle="${commonLables}" /></center></b><br>
    </td>
    </c:if>
    
    <c:if test="${flagForAction == 2 && reportFlag == 0}">
	<td>
	<b><center><hdiits:caption captionid="HR.QTR.intermField" bundle="${commonLables}" /></center></b><br>
    </td>
    </c:if>
    
    <c:if test="${reportFlag == 1}">
	<td>
	<b><center><hdiits:caption captionid="HR.QTR.intermReport" bundle="${commonLables}" /></center></b><br>
    </td>
    </c:if>
    <td width="10%"></td>
</tr>							  	
</table> 
<hr width="80%" align="center">
<br><br><br>
<br><br><br>

<table class="tabtable">
  <tr>
    <td colspan ="4" align= "center">	
    <c:if test="${reportFlag==0}">		
    <hdiits:button captionid="HR.QTR.Submit" bundle="${commonLables}"  name="Submit" type="button" onclick="submit1()"/>
    </c:if>
	<hdiits:button captionid="HR.QTR.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="submit4()"/>
	</td>
	
 </tr>
</table>

	</div>
	</div>
	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

 <script type="text/javascript">
	initializetabcontent("maintab")
 </script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>