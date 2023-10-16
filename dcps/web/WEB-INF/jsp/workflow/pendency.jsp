<%
try
{
%>
<%@ include file="../core/include.jsp"%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="fromPostEmpLst" value="${resultMap.fromPostEmpLst}"></c:set>
<c:set var="revfromPostlst" value="${resultMap.revfromPostlst}"></c:set>
<c:set var="toPostlst" value="${resultMap.toPostlst}"></c:set>
<c:set var="pendingdaylst" value="${resultMap.pendingdaylst}"></c:set>
<c:set var="rcvddatelst" value="${resultMap.rcvddatelst}"></c:set>
<c:set var="deptnamelst" value="${resultMap.deptnamelst}"></c:set>
<c:set var="userlst" value="${resultMap.userlst}"></c:set>
<c:set var="userlevellst" value="${resultMap.userlevellst}"></c:set>
<c:set var="userflaglst" value="${resultMap.userflaglst}"></c:set>
<c:set var="sentdatelst" value="${resultMap.sentdatelst}"></c:set>
<c:set var="corrNo" value="${resultMap.corrNo}"></c:set>
<c:set var="desc" value="${resultMap.desc}"></c:set>
<c:set var="jobMovementFlag"  value="${resultMap.jobMovementFlag}"></c:set>
<c:set var="fromFile"  value="${resultMap.fromFile}"></c:set>

<script>
function showGraphicalPen()
{
		    document.getElementById("pndencyform").method="post";
			document.getElementById("pndencyform").action="${contextPath}/hdiits.htm?actionFlag=viewPendencyDetails&corrId=${resultMap.corrId}&penType=1"
			document.getElementById("pndencyform").submit();
			
}
function showprint()
{
	window.print();
}
</script>


					<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
					<BR>
					<hdiits:form name="pndencyform" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">
					<center><b><font size="4"><fmt:message key="WF.Pendency" bundle="${fmsLables}"></fmt:message></font></b></center>
					<br>
					<br>
					<table width="100%">
						<tr>
							<td>
							<c:if test="${fromFile eq 'yes'}">
							<b><fmt:message  key="WF.FileNo" bundle="${fmsLables}" /></b>
							</c:if>
							
							<c:if test="${fromFile ne 'yes'}">
							<b><fmt:message  key="WF.CorrNo" bundle="${fmsLables}" /></b>
							</c:if>
							
							<c:out value="${resultMap.corrNo}"></c:out>
							</td>
							<td>
							<font font size="2"><b><a href="#" onclick="javascript:showGraphicalPen();"></a></b></font>
							</td>
						</tr>
						<tr>
							<td><b><fmt:message  key="WF.Subject" bundle="${fmsLables}" /></b>
							<c:out value="${resultMap.subject}"></c:out>
							</td>
							<td>
							<font style="text-align: right"><b><a href="#" onclick="showprint()" >Print</a></b></font> 
							</td>
						</tr>	
					
					</table>
					<br>
					<br>
					<Table class="datatable" id="add2" style="${DisableStyle};table-layout:fixed;" border="1" >
					  <TR>
					  		<td class="datatableheader" width="10%"><b><hdiits:caption captionid="WF.Srno" bundle="${fmsLables}"/></b></td>
					  		<td class="datatableheader" width="10%"><b><center><hdiits:caption captionid="WF.OfficerName" bundle="${fmsLables}"/></center></b></td>
							<td class="datatableheader" width="10%"><b><center><hdiits:caption captionid="WF.Dept" bundle="${fmsLables}"/></center></b></td>					  		
					  		<td class="datatableheader" width="10%"><b><center><hdiits:caption captionid="WF.PenRcvdDate" bundle="${fmsLables}"/></center></b></td>
					  		<td class="datatableheader" width="10%"><center><b><hdiits:caption captionid="WF.SendDate" bundle="${fmsLables}"/></b></center></td> 		
					  		<td class="datatableheader" width="10%"><center><b><hdiits:caption captionid="WF.PenDay" bundle="${fmsLables}"/></b></center></td> 		
					  </TR>
					  
					   <c:forEach items="${fromPostEmpLst}" var="fromPostEmpLst" varStatus="status" >
											<c:set var="counter" value="${counter+1}"></c:set>	
											<tr>
												<td>${counter}</td>
												<td>${fromPostEmpLst}</td>
												<td>${deptnamelst[status.index]}</td>
												<td>${rcvddatelst[status.index]}</td>
												<td>${sentdatelst[status.index]}</td>
												<td>${pendingdaylst[status.index]}</td>
																				
												
											</tr>		
											
													
					 </c:forEach>
					
					 
					</Table>
					<br>
					<br>
					
					<br>
					<center><hdiits:button name="closebtn" type="button" captionid="WF.Close" bundle="${fmsLables}" onclick="javascript:window.close()"/> </center>
					  
					</hdiits:form>
			

<script>
//document.getElementById('add1').style.display='none';
</script>
<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>

