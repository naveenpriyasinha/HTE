<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<% try  { %>

	<script ="text/javascript" src="script/common/tabcontent.js"></script>
	<script ="javascript" src="script/common/addRecord.js"></script>
	<script type="text/javascript">
		function submitForm(obj)
		{
			showProgressbar();
			try
			{
				document.forms[0].method='post';
				if(obj == 'keep')
				{
					if(document.getElementById("fromCommonHomePage").value == "yes")
					{
						window.opener.homepageajaxfunction("Notification");
					}
					else
					{
			  			document.forms[0].action = 'hdiits.htm?actionFlag=getDocListOfWorkList';
					}	
				}
				else if(obj == 'donotkeep')
				{
					var key = document.forms[0].pkval.value;
					document.forms[0].action = 'hdiits.htm?actionFlag=updateTempMsgStatus&allNotificationIdsForDelete='+key+"&fromCommonHomePage="+document.getElementById("fromCommonHomePage").value;
				}
				else
				{
					window.close();
				}

				

				if((document.getElementById("fromCommonHomePage").value != "yes") && (document.getElementById("sendBackTo").value != "Notification"))
				{
					document.forms[0].submit();
				  		
				  	window.opener.parent.frames["dataFrame"].document.forms[0].action = "hdiits.htm?actionFlag=getDocListOfWorkList&moduleName=WorkList&menuName=forWorkList";
					window.opener.parent.frames["dataFrame"].document.forms[0].method = "post";
					window.opener.parent.frames["dataFrame"].document.forms[0].submit();
				}
				if((document.getElementById("fromCommonHomePage").value == "yes"))
				{
					if(obj == 'donotkeep')
					{
						document.forms[0].submit();
						showProgressbar();
					}
					
					window.opener.homepageajaxfunction(document.getElementById("sendBackTo").value);
				}
			  	self.close();
			 }
			 catch(e)
			 {
			 }
		  
		  	
		}
	</script>
	
	
	

<fmt:setBundle basename="resources.WFLables" var="commonLables" scope="request"/>

<hdiits:form name="frmcsearch" validate="true" method="post" action="./hdiits.htm">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.NOTIINBOX" bundle="${commonLables}" />
			</a>
		</li>
	</ul>
	</div>
	<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent" tabno="0">
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="shortMsg" value="${resultValue.shortMsg}" > </c:set>
<c:set var="longMsg" value="${resultValue.longMsg}" > </c:set>
<c:set var="url" value="${resultValue.notification_url}" > </c:set>

<c:set var="NTF_FROM_POST" value="${resultValue.NTF_FROM_POST}" > </c:set>
<c:set var="NTF_FROM_EMP_ID" value="${resultValue.NTF_FROM_EMP_ID}" > </c:set>
<c:set var="NTF_RCVD_DATE" value="${resultValue.NTF_RCVD_DATE}" > </c:set>
<c:set var="fromCommonHomePage" value="${resultValue.fromCommonHomePage}" > </c:set>
<c:set var="sendBackTo" value="${resultValue.sendBackTo}" > </c:set>

<hdiits:hidden name="sendBackTo" id="sendBackTo" default="${resultValue.sendBackTo}"></hdiits:hidden>
<hdiits:hidden name="fromCommonHomePage" id="fromCommonHomePage" default="${resultValue.fromCommonHomePage}"></hdiits:hidden>
<hdiits:hidden name="pkval" default="${resultValue.pkval}"></hdiits:hidden>
  <br><br>
<table id="tabId" align="center" border="2" width="100%" class="datatable">
	<tr>
		<td class="datatableheader" colspan="4" width="100%" align="center">
			<hdiits:caption captionid="WF.NOTIDETAIL" bundle="${commonLables}" />
		</td>
	</tr>
	
	<tr>
		<td class="datatableheader" width="25%">
			<hdiits:caption captionid="WF.RecvFromPost" bundle="${commonLables}" />
		</td>
		<td colspan="3" width="75%">
			<c:choose>
				<c:when test="${not empty NTF_FROM_POST}">
					<b><c:out value="${NTF_FROM_POST}"> </c:out></b>
				</c:when>
			</c:choose>	
		</td>
	</tr>
	<tr>
		<td class="datatableheader" width="25%">
			<hdiits:caption captionid="WF.RecvFromEmp" bundle="${commonLables}" />
		</td>
		<td colspan="3" width="75%">
			<c:choose>
				<c:when test="${not empty NTF_FROM_EMP_ID}">
					<b><c:out value="${NTF_FROM_EMP_ID}"> </c:out></b>
				</c:when>
			</c:choose>	
		</td>
	</tr>
	<tr>
		<td class="datatableheader" width="25%">
			<hdiits:caption captionid="WF.RecvDate" bundle="${commonLables}" />
		</td>
		<td colspan="3" width="75%">
			<c:choose>
				<c:when test="${not empty NTF_RCVD_DATE}">
					<b><label id="recDateForNotification"><c:out value="${NTF_RCVD_DATE}"> </c:out></label></b>
				</c:when>
			</c:choose>	
		</td>
	</tr>

	

	
	
	<tr>
		<td class="datatableheader" width="25%">
			<hdiits:caption captionid="WF.SHORTDESC" bundle="${commonLables}" />
		</td>
		<td colspan="3" width="75%">
			<c:choose>
				<c:when test="${not empty url}">
					<a style="cursor:hand" href="${url}" target="_new"><font color="blue"><c:out value="${longMsg}"> </c:out></a> </font>
				</c:when>
				<c:otherwise>
					<font color="blue"><c:out value="${longMsg}"> </c:out> </font>
				</c:otherwise>	
			</c:choose>	
			
		</td>
	</tr>
	<tr>
		<td class="datatableheader" width="25%">
			<hdiits:caption captionid="WF.LONGDESC" bundle="${commonLables}" />
		</td>
		<td colspan="3" width="75%">
			<c:choose>
				<c:when test="${not empty url}">
					<a style="cursor:hand" href="${url}" target="_new"><font color="blue"><c:out value="${shortMsg}"> </c:out></a> </font>
				</c:when>
				<c:otherwise>
					<font color="blue"><c:out value="${shortMsg}"> </c:out> </font>
				</c:otherwise>	
			</c:choose>	
		</td>
	</tr>
	
</table>
 
 <br>
 <br>
 <br>
 <center>
	 <hdiits:button name="closeBtn" type="button" captionid="WF.CLOSE" bundle="${commonLables}" onclick="javascript:window.close()"/>
	 <hdiits:button name="keepBtn" type="button" captionid="WF.KEEPINWL" bundle="${commonLables}" onclick="submitForm('keep')"/>
	 <hdiits:button name="donotkeepBtn" type="button" captionid="WF.DONTKEEPINWL" bundle="${commonLables}" onclick="submitForm('donotkeep')"/>

 </center>
	    </div>
	</div>
	
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'></hdiits:validate>
	</hdiits:form>
		<script type="text/javascript">
//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
initializetabcontent("maintab")
</script>
<%}catch(Exception e){
	e.printStackTrace();
}%>

<script>
document.getElementById("recDateForNotification").innerHTML = getDateAndTimeFromDateObj (document.getElementById("recDateForNotification").innerHTML);

</script>