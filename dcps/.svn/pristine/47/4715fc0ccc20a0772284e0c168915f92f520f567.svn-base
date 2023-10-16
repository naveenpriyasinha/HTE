<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript" src="script/common/base64.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="frompostlist" value="${resValue.frompostlist}"></c:set>
<c:set var="recdatelist" value="${resValue.recdatelist}"></c:set>
<c:set var="topostlist" value="${resValue.topostlist}"></c:set>
<c:set var="ccpostlist" value="${resValue.ccpostlist}"></c:set>
<c:set var="subjectlist" value="${resValue.subjectlist}"></c:set>

<c:set var="commdtllist" value="${resValue.commdtllist}"></c:set>
<c:set var="attachmentNameList" value="${resValue.attachmentNameList}"></c:set>
<c:set var="lurlList" value="${resValue.lurlList}"></c:set>
<c:set var="finalattachlist" value="${resValue.finalattachlist}"></c:set>
<c:set var="cnt" value="${0}"></c:set>
<c:set var="startcnt" value="${1}"></c:set>



<script>
var attcnt=0;
</script>


<br>
<center><h5><fmt:message key="WF.CommHistDetail" bundle="${fmsLables}"></fmt:message></h5></center>
<br>

<hdiits:form name="histcommdtlfrm" method="post" validate="true" encType="multipart/form-data">
<table width="100%" border="1">
		<c:forEach var="fromlistcnt" items="${frompostlist}" varStatus="status" >
		<tr>
		<td width="100%">
		<table width="100%" border="0">
			<tr >
					<td valign="top">
						<b><hdiits:caption captionid="WF.CommFrom"  bundle="${fmsLables}" /><c:out value=":${frompostlist[status.index]}"></c:out></b>
						<br>
						<c:out value="${recdatelist[status.index]}"></c:out>
						<br>
						<br>
						<b><hdiits:caption captionid="WF.Attachment"  bundle="${fmsLables}" />:</b>
						<br>
						<c:set var="cnt1" value="${cnt}"></c:set>
												
						<c:if test="${finalattachlist[status.index] gt 0}">
						<c:forEach var="a" items="${attachmentNameList}" varStatus="attstatus" begin="0" end="${finalattachlist[status.index]-1}">
							<a href="${lurlList[cnt]}"  > <c:out value="${attachmentNameList[cnt]}"> </c:out></a>
							<br>
							<c:set var="cnt" value="${cnt+1}"></c:set>
						</c:forEach>
						</c:if>
						
					</td>
									
				
					
					<td valign="top">
					<table width="100%">
							<tr>
									<td width="5%">
										<b><hdiits:caption  captionid="WF.CommTo" bundle="${fmsLables}"  />:</b>
										
									</td>	
									<td width="95%">
										
										<hdiits:textarea style="width:75%" name="tolist_${status.index}" cols="40" rows="5"  default="${topostlist[status.index]}" readonly="true"/>
									</td>
							</tr>
							<tr>		
									<td width="5%">
										<b><hdiits:caption  captionid="WF.CC" bundle="${fmsLables}"  />:</b>
										
									</td>	
									<td width="95%">
										<hdiits:textarea name="cclist_${status.index}" default="${ccpostlist[status.index]}" cols="40" rows="5" readonly="true"/>
									</td>
							</tr>
							<tr>		
									<td width="5%">
										<b><hdiits:caption  captionid="WF.CommSubject" bundle="${fmsLables}"  />:</b>	
									</td>
									<td width="95%">
										<hdiits:text name="subject_${status.index}" default="${subjectlist[status.index]}" readonly="true" size="38" />
									</td>
							</tr>
							<tr>		
									<script>
									var comm_dtl='${commdtllist[status.index]}';
									var decoded_text=decodeBase64(comm_dtl);
									
									</script>
									<td width="5%">
										<b><hdiits:caption  captionid="WF.CommDtl" bundle="${fmsLables}"  />:</b>	
									</td>
									<td width="95%" >
											<script>							
													document.writeln(decoded_text);
											</script>									
									</td>
									
									
							</tr>
							
							
						</table>
					</td>
					
				</tr>
			
				
				
		</table>
		</td>
		</tr>
		
			
	</c:forEach>
</table>

<center><hdiits:button name="closebtn" captionid="WF.Close" bundle="${fmsLables}" type="button"  onclick="window.close()"/></center>
</hdiits:form>
<script>
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>