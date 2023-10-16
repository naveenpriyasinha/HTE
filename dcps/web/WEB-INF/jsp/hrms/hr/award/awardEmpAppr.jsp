

<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>
<fmt:setBundle basename="resources.hr.award.award_AlertMessages" var="awdalert" scope="request"/>
<fmt:setBundle basename="resources.hr.award.award" var="awdlbl" scope="request"/>
<html>
   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/hrms/hr/award/award.js"></script>
<script type="text/javascript">
	var awardEmpApprMsg = new Array();
	awardEmpApprMsg[0] = '<fmt:message bundle="${awdalert}" key="ess.SelEmp"/>'; //Please Select an Employee from the List
	
</script>

</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="MyList" value="${resValue.actionList}"> </c:set>
<c:set var="awardIdListTrans" value="${resValue.awardIdListTrans}"> </c:set>	
<c:set var="awardIdListMst" value="${resValue.awardIdListMst}"> </c:set>
<c:set var="awardIdListMst2" value="${resValue.AwardDtList}"> </c:set>
<c:set var="awardIdListMst3" value="${resValue.AwardDtListReject}"> </c:set>
<c:set var="AwdEmpStatus" value="${resValue.AwdEmpStatus}"></c:set>
<c:set var="awardReqID" value="${resValue.awardReqID}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<body>

<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">

<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1"><b><hdiits:caption captionid="ess.awards" bundle="${awdlbl}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
</div>
<div class="tabcontentstyle" style="height: 30%">
<div id="tcontent1" class="tabcontent" tabno="0">
<BR>
<BR>				

<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.SelEmp">
	<table class="TableBorderLTRBN" id="FinalListDtlsTab" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" name= "FinalListDtlsTab" align="center" width="95%" borderColor="black" style="border-collapse: collapse;background-color: ${tableBGColor}">					
	<tr>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.select" bundle="${awdlbl}"></hdiits:caption></b></td>	
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.srno" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.name" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.desig" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdCat" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdType" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdName" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdedFor" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.preAwdPmt" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.othrDtls" bundle="${awdlbl}"></hdiits:caption></b></td>
		<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.reasnofrec" bundle="${awdlbl}"></hdiits:caption></b></td>
	</tr>
			<c:set var ="i" value="1" />
			<c:if test="${not empty awardIdListMst }">
			<c:forEach var="k" items="${awardIdListMst}">
			<tr>
			<td> 
				<hdiits:radio name="SelEmp" id="SelEmp" value="${k.userID}~${k.srno}~${awardReqID}" mandatory="false" onclick="" />
			</td>
			<td>
				<c:out value="${i}"></c:out>
				<c:set var ="i" value="${i+1}"/>
			</td>
			<td><c:out value="${k.userName}" ></c:out></td>
			<td><c:out value="${k.designation}" ></c:out></td>
			
				
			<td>
					
						<c:out value="${k.awardCategory}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${k.awardType}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${k.awardName}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${k.awardedFor}" ></c:out>
					
			</td>
			
			<td><A href='hdiits.htm?actionFlag=getEmpDtlsAtSel&id=${k.userID}' target='blank'>
			<b><center> ${k.awardNum}&nbsp/&nbsp${k.punishNum}&nbsp
			(<fmt:message  bundle="${awdlbl}" key="ess.clickView"/>)</center></b></A>
			</td>
			
			
			<td><A href='hdiits.htm?actionFlag=getEmpDtlsAtSel2&id=${k.userID}' target='blank'>
			<b><center>(<fmt:message  bundle="${awdlbl}" key="ess.clickView"/>)</center></b></A>
			</td>
					
			
			<td><A href='hdiits.htm?actionFlag=getAwdRsnFrmDb&id=${k.userID}&FileId=<%=request.getParameter("fileId")%>' target='blank'>
			<b><center>(<fmt:message  bundle="${awdlbl}" key="ess.clickViewEdit"/>)</center></b></A>
			</td>	
			</tr>
			
			<tr>
			<td  style="display:none" >${k.srno}</td>
			
			</tr>
			</c:forEach>
			</c:if>		
			
			
					
	</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.ApprEmp">	
	<table class="TableBorderLTRBN" id="FinalListDtlsTab" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" name= "FinalListDtlsTab" align="center" width="95%" borderColor="black" style="border-collapse: collapse;background-color: ${tableBGColor}">					
		<tr>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.select" bundle="${awdlbl}"></hdiits:caption></b></td>	
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.srno" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.name" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.desig" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdCat" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdType" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdName" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdedFor" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.preAwdPmt" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.othrDtls" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.reasnofrec" bundle="${awdlbl}"></hdiits:caption></b></td>
		</tr>
			
			
			<c:if test="${not empty awardIdListMst2 }">
			<c:forEach var="m" items="${awardIdListMst2}">
			<tr>
			<td>
				<hdiits:radio name="SelEmp" id="SelEmpAppr" value="${m.userID}~${m.srno}~${awardReqID}" mandatory="false" onclick="" /> 
               
			</td>
			<td>
				<c:out value="${i}"></c:out>
				<c:set var ="i"  value="${i+1}"/>
			</td>
			<td><c:out value="${m.userName}" ></c:out></td>
			<td><c:out value="${m.designation}" ></c:out></td>
			
				
			<td>
					
						<c:out value="${m.awardCategory}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${m.awardType}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${m.awardName}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${m.awardedFor}" ></c:out>
					
			</td>
			
			<td><A href='hdiits.htm?actionFlag=getEmpDtlsAtSel&id=${m.userID}' target='blank'>
			<b><center>(<fmt:message  bundle="${awdlbl}" key="ess.clickView"/>)</center></b></A>
			</td>
			
			
			<td><A href='hdiits.htm?actionFlag=getEmpDtlsAtSel2&id=${m.userID}' target='blank'>
			<b><center>(<fmt:message  bundle="${awdlbl}" key="ess.clickView"/>)</center></b></A>
			</td>
					
			
			<td><A href='hdiits.htm?actionFlag=getAwdRsnFrmDb&id=${m.userID}&FileId=<%=request.getParameter("fileId")%>' target='blank'>
			<b><center>(<fmt:message  bundle="${awdlbl}" key="ess.clickViewEdit"/>)</center></b></A>
			</td>	
			</tr>
			
			<tr>
			<td  style="display:none" >${m.srno}</td>
			
			</tr>
			</c:forEach>
			</c:if>
			
					
	</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.RejEmp">		
	<table class="TableBorderLTRBN" id="FinalListDtlsTab" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" name= "FinalListDtlsTab" align="center" width="95%" borderColor="black" style="border-collapse: collapse;background-color: ${tableBGColor}">					
		<tr>		
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.select" bundle="${awdlbl}"></hdiits:caption></b></td>	
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.srno" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.name" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.desig" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdCat" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdType" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdName" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="ess.awdedFor" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.preAwdPmt" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.othrDtls" bundle="${awdlbl}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}" style="cursor:hand"><b><hdiits:caption captionid="ess.reasnofrec" bundle="${awdlbl}"></hdiits:caption></b></td>
		</tr>
			
			<c:if test="${not empty awardIdListMst3 }">
			<c:forEach var="n" items="${awardIdListMst3}">
			<tr>
			<td> 

				<hdiits:radio name="SelEmp" id="SelEmpRej" value="${n.userID}~${n.srno}~${awardReqID}" mandatory="false" onclick="" /> 
			</td>
			<td>
				<c:out value="${i}"></c:out>
				<c:set var ="i"  value="${i+1}"/>
			</td>
			<td><c:out value="${n.userName}" ></c:out></td>
			<td><c:out value="${n.designation}" ></c:out></td>
			
				
			<td>
					
						<c:out value="${n.awardCategory}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${n.awardType}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${n.awardName}" ></c:out>
					
			</td>
			<td>
					
						<c:out value="${n.awardedFor}" ></c:out>
					
			</td>
			
			<td><A href='hdiits.htm?actionFlag=getEmpDtlsAtSel&id=${n.userID}' target='blank'>
			<b><center>(<fmt:message  bundle="${awdlbl}" key="ess.clickView"/>)</center></b></A>
			</td>
			
			
			<td><A href='hdiits.htm?actionFlag=getEmpDtlsAtSel2&id=${n.userID}' target='blank'>
			<b><center>(<fmt:message  bundle="${awdlbl}" key="ess.clickView"/>)</center></b></A>
			</td>
					
			
			<td><A href='hdiits.htm?actionFlag=getAwdRsnFrmDb&id=${n.userID}&FileId=<%=request.getParameter("fileId")%>' target='blank'>
			<b><center>(<fmt:message  bundle="${awdlbl}" key="ess.clickViewEdit"/>)</center></b></A>
			</td>	
			</tr>
			
			<tr>
			<td  style="display:none" >${n.srno}</td>
			
			</tr>
			</c:forEach>
			</c:if>		
	
	</table>
</hdiits:fieldGroup>
	<br>
<table width="100%">
	<tr width="100%">
	<td width="100%"><center>
	<hdiits:button type="button"
		name="VerButton"  id="VerButtonRejec" captionid="ess.Verify" bundle="${awdlbl}"
		onclick="verifyAwdDtls(${i});"  style="width:120px"/></center>
	</td>	
</tr>	

</table>
</div>
 

<table align="center">
<tr>
	<hdiits:hidden name="ReqId" id="ReqId" default="${awardReqID}"></hdiits:hidden>
	
	</tr>
</table>
</div>
<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</body>
</html>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>

			
					
						
