
<%
try
{%>

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
<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="MOD_VER_DETAILS_TITLE" bundle="${lables}"/></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">


<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tcontent1" class="tabcontent" tabno="0">
<hdiits:table>
 <tr>
  <td class="fieldLabel" width="25%"><hdiits:caption captionid="DB_ID" bundle="${lables}"/></td>
 <td><hdiits:text name="db_id" captionid="DB_ID" validation="txt.isrequired" bundle="${lables}" size="10"></hdiits:text></td>

  </tr>
  <BR>
  <tr>
  <td class="fieldLabel" width="25%"><hdiits:caption captionid="ST_DT" bundle="${lables}"/></td>
 <td><hdiits:dateTime name="start_date"  default="2004-01-01 00:00:00" validation="txt.isrequired" captionid="ST_DT" bundle="${lables}" /></td>
  </tr>
  <BR>

<tr> 
<td colspan="2"><input type="hidden" name="actionFlag" value="getmods"></td>
</tr>
</hdiits:table>
</div>
<a href= "./hdiits.htm?viewName=mainopt">  <fmt:message key="GO_TO_MAIN_MENU" /> <BR><BR>
<jsp:include page="../core/tabnavigation.jsp" /></div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
				<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
		</hdiits:form>


<%}catch(Exception e)
  {
  e.printStackTrace();
  }
  %>