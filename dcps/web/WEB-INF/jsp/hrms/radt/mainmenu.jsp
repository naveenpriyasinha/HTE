
<%
try
{
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp" %>
<fmt:setBundle basename="resources.radt.Lables" var="lables" scope="request"/>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="/script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="/script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
		


<hdiits:form method="POST" name="testing" validate="true" action="./hdiits.htm" >
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="MAIN_TITLE" bundle="${lables}"/></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">       
 <BR><BR> Please Select any one :
 <BR><Br>
<a href= "./hdiits.htm?actionFlag=getdbid">  <FONT size = "3"> <fmt:message key="Main_Report1" /><BR> <BR></FONT></a>
<a href= "./hdiits.htm?viewName=filedet"> <FONT size = "3"> <fmt:message key="Main_Report2" /><BR> <BR></FONT></a>
<a href= "./hdiits.htm?actionFlag=gedbdetails"> <FONT size = "3">  <fmt:message key="Main_Report3" /><BR><BR></FONT></a>
<a href= "./hdiits.htm?viewName=modversion">  <FONT size = "3"> <fmt:message key="Main_Report4" /><BR><BR></FONT></a>
<!-- <a href= "./hdiits.htm?viewName=deploydet">  <FONT size = "3"> 	5. Get  Deployment Details <BR><BR></FONT> -->
<a href= "./hdiits.htm?viewName=moddetfile">  <FONT size = "3"> 	<fmt:message key="Main_Report5" /> <BR><BR></FONT></a>
<a href= "./hdiits.htm?viewName=deploymentdate">  <FONT size = "3"> 	<fmt:message key="Main_Report6" /> <BR><BR><BR></FONT></a>
</div> 
</div> 

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>

</hdiits:form>
 	  <%
}
  	  catch(Exception e)
  	  {
  		  e.printStackTrace();
  	  }
  	  %>
 
 