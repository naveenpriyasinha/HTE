<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<fmt:setBundle basename="resources.trng.submasLables" var="submasLables" scope="request" />
<caption>
	<font color="blue">
		<c:set var="resultObj" value="${result}"></c:set>
		<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
		<c:set var="msg" value="${resultMap.msg}"> </c:set>
 		<c:set var="subKey" value="${resultMap.SubmitKey}" />
		<c:out value="${msg}"/>
 					<c:choose>
								<c:when test="${resultMap.functnNotifictnLib eq 'edit'}">				
									<c:if test="${resultMap.sessionReq eq 'yes'}">
										<c:set var="hrTrSubjectMstEdit" value="${resultMap.hrTrSubjectMstEdit}" scope="session"/>
									</c:if>					
										<c:set var="HrTrsubjectMst" value="${resultMap.HrTrsubjectMst}"/>											
								</c:when>
								<c:otherwise> 
										<c:set var="HrTrsubjectMst" value="${resultMap.HrTrsubjectMst}" scope="session"/>
								</c:otherwise>	
				   </c:choose>
 	</font>
</caption>
 
<hdiits:form name="frmSubjectMst" validate="true" method="post" action="./hdiits.htm" encType="multipart/form-data">

<c:choose>
	<c:when test="${resultMap.functnNotifictnLib eq 'edit'}">
		<hdiits:hidden name="actionFlag" default="UpdateSubject" />
	</c:when>
	<c:otherwise>
		<hdiits:hidden name="actionFlag" default="insertSubject" />
	</c:otherwise>
</c:choose>

<hdiits:hidden name="subjectcode" default="${resultMap.subjectcode}"/>
<hdiits:hidden name="functnNotifictnLib" default="${resultMap.functnNotifictnLib}"/>
<hdiits:hidden name="subjectid1" default="${resultMap.subjectId}"/>
<hdiits:hidden name="subjectid2" default="${resultMap.subjectId1}"/>
<hdiits:hidden name="subjectcode" default="${resultMap.subjectcode}"/>
 		

<c:choose>
					<c:when test="${resultMap.sbBtnValue eq 'Submit'}" >
							<fmt:message var="submitBtnValue" key="TN.SUBMIT" bundle="${submasLables}" />								
							<c:set var="readOnly" value='${resultMap.readOnly}' />	
							<c:set var="disabled" value='true' />
							<hdiits:hidden name="hdnFirstTimeFlag" id="firstTimeFlag" default="false" />
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${resultMap.functnNotifictnLib eq 'edit'}">
								<fmt:message var="submitBtnValue" key="TN.NEXT" bundle="${submasLables}" />
								<hdiits:hidden name="hdnFirstTimeFlag" id="firstTimeFlag" default="true" />
							</c:when>
							<c:otherwise>
								<fmt:message var="submitBtnValue" key="TN.NEXT" bundle="${submasLables}" />
								<hdiits:hidden name="hdnFirstTimeFlag" id="firstTimeFlag" default="true" />
							</c:otherwise>
						</c:choose>
					</c:otherwise>
</c:choose>	


<div id="tabmenu"> 
	<ul id="maintab" class="shadetabs">
		<li  class="selected">
			<a href="#"  rel="tcontent1">
				<hdiits:caption	captionid="TR.submst" bundle="${submasLables}" captionLang="single"/> 
			</a>
		</li>
	</ul>
</div>

<div class="tabcontentstyle"> 
	<div id="tcontent1" class="tabcontent" tabno="fmkghcf">
			<table class="tabtable">
				<c:choose>
					<c:when test="${resultMap.functnNotifictnLib eq 'edit'}">	
					<tr>
					    		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.SUB_NAME" bundle="${submasLables}" captionLang="single"/>
					    		</td>
								<td class="fieldLabel" width="25%"><hdiits:text name="txtSubname" maxlength="100" 
					                             captionid="TR.SUB_NAME"  bundle="${submasLables}" validation="txt.isrequired" mandatory="true" default="${HrTrsubjectMst.subjectName}" onkeypress="return validateText(event, false)"/>  
					 			</td>
					 			<td class="fieldLabel" width="25%">		</td>	
					    		<td class="fieldLabel" width="25%">		</td>	
					</tr>
					<tr> 
					      		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.SUB_DET" bundle="${submasLables}" captionLang="single"/>
					      		</td>
								<td class="fieldLabel" width="25%"><hdiits:textarea name="txtSubdetail" rows="5" cols="50" default="${HrTrsubjectMst.subjectDetail}"/>
							    </td>
								<td class="fieldLabel" width="25%">		</td>	
			    				<td class="fieldLabel" width="25%">		</td>			
					</tr>
					</c:when>
					
					<c:otherwise>
					<tr>
				    		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.SUB_NAME" bundle="${submasLables}" captionLang="single"/>
				    		</td>
							<td class="fieldLabel" width="25%"><hdiits:text name="txtSubname" maxlength="100" 
				                             captionid="TR.SUB_NAME" mandatory="true" bundle="${submasLables}" validation="txt.isrequired" onkeypress="return validateText(event, false)"/>  
				 			</td>
							<td class="fieldLabel" width="25%">		</td>	
			    			<td class="fieldLabel" width="25%">		</td>	
				 	</tr>
				 	<tr>
					  		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.SUB_DET" bundle="${submasLables}" captionLang="single"/>
				      		</td>
							<td class="fieldLabel" width="25%"><hdiits:textarea name="txtSubdetail" rows="5" cols="50" />
						    </td>
						    <td class="fieldLabel" width="25%">		</td>	
			    			<td class="fieldLabel" width="25%">		</td>	
					</tr>
					</c:otherwise>
				</c:choose>
			</table>
	</div>

	<script type="text/javascript">var navDisplay = false;</script>
		<jsp:include page="../../core/tabnavigation.jsp" >
			<jsp:param name="submitText" value="${subKey}" />
			<jsp:param name="disableReset" value="${resetBtnDisabled}" />
			<jsp:param name="disableSubmit" value="${submitBtnDisabled}" />
 			<jsp:param name="closeText" value="${closeBtnValue}"/>
			<jsp:param name="closeURL" value="${closeUrl}"/>
			<jsp:param name="closeWindow" value="${closeWindow}"/>
 		</jsp:include>	 
</div> 

	<script type="text/javascript">
			initializetabcontent("maintab")
	</script>
	
	<hdiits:validate locale="${ sessionScope.locale }" />
</hdiits:form>


<c:if test="${resultMap.sbBtnValue eq 'Submit'}">
	<c:choose>
		<c:when test="${resultMap.localeVal eq 'en_US'}">
		<% request.getSession().setAttribute("locale", "gu");%>
		</c:when>
		<c:otherwise>
		<% request.getSession().setAttribute("locale", "en_US");%>
		</c:otherwise>
	</c:choose>	
</c:if>