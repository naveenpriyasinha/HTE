<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="include.jsp"%>
<fmt:setBundle basename="resources.core.lables" var="lables" scope="request"/>

<c:set var="disableSubmit" value="${param.disableSubmit}" />
<c:set var="disableReset" value="${param.disableReset}" />
<c:set var="afterReset" value="${param.afterReset}" />

<c:set var="submitName" value="${param.submitText}" />
<c:set var="submitCopyName" value="${param.submitCopyText}" />
<c:set var="buttonWidth" value="${param.buttonWidth}" />
<c:set var="submitCopyUrl" value="${param.submitCopyUrl}" />
<c:set var="closeName" value="${param.closeText}" />
<c:set var="closeURL" value="${param.closeURL}" />
<c:set var="closeWindow" value="${param.closeWindow}"/>
<c:set var="approveName" value="${param.approveText}" />
<c:set var="approveURL" value="${param.approveURL}" />
<c:set var="InterimSave" value="${param.InterimSave}" />
<c:set var="showMandatoryMsg" value="${param.showMandatoryMsg}" />

<c:set var="nextURL" value="${param.nextURL}" />
<c:set var="previousURL" value="${param.previousURL}" />

<fmt:message key="TABNAV.SUBMIT" bundle="${lables}" var="submitText"></fmt:message>
<fmt:message key="TABNAV.CLOSE" bundle="${lables}" var="closeText"></fmt:message>
<fmt:message key="TABNAV.APPROVE" bundle="${lables}" var="approveText"></fmt:message>
<fmt:message key="TABNAV.SAVE" bundle="${lables}" var="saveText"></fmt:message>
<fmt:message key="TABNAV.SAVE" bundle="${lables}" var="saveText"></fmt:message>
<c:if test="${showMandatoryMsg eq null || showMandatoryMsg ne 'No'}">
<script language="javaScript">

              // Code to show Mandatory node as per the requirement
              var showMandatorynote = document.getElementById('mandatoryNotes');
	      if(showMandatorynote != null)
              	showMandatorynote.style.display='';
              // End Code 
</script>
</c:if>

<c:if test="${submitName gt ''}">
<c:set var="submitText" value="${submitName}" />
</c:if>

<c:if test="${closeName gt ''}">
<c:set var="saveText" value="${closeName}" />
</c:if>

<c:if test="${closeName gt ''}">
<c:set var="closeText" value="${closeName}" />
</c:if>

<c:if test="${closeWindow eq 'yes'}">
<c:set var="closeAction" value="window.close()" />
</c:if>

<c:if test="${(closeWindow ne 'yes') && (closeURL gt '')}">
<c:set var="closeAction" value="window.location=&#39;${closeURL}&#39;" />
</c:if>

<c:if test="${approveName gt ''}">
<c:set var="approveText" value="${approveName}" />
</c:if>

<c:if test="${submitCopyName gt ''}">
<c:set var="submitAndCopyText" value="${submitCopyName}" />
</c:if>

<c:if test="${buttonWidth gt ''}">
<c:set var="buttonWidthText" value="${buttonWidth}" />
</c:if>

<table class="tabNavigationBar">
	<tr>
		<td class="tabnavtd" id="tabnavtd" >
				 <c:if test="${InterimSave eq 'Yes'}">
				 	  <input type="button" name="saveDraftButton" value='<fmt:message key="TABNAV.SAVE" bundle="${lables}"></fmt:message>' onClick="saveFormData()">
				 </c:if>
		      		<c:choose>
			      	<c:when test="${disableSubmit eq 'true'}">
			     		 <hdiits:formSubmitButton name="formSubmitButton" type="button" caption="${submitText}" readonly="true"/>
			     		 <c:if test="${approveURL gt ''}">
			     		 <hdiits:formSubmitButton name="formApproveButton" type="button" formAction="${approveURL}" caption="${approveText}" readonly="true"/>
						 </c:if>
			     		 <!-- <input type="submit" value="${submitText}" disabled="disabled"/> -->
			     	</c:when>
			     	<c:otherwise>
			     		 <hdiits:formSubmitButton name="formSubmitButton" type="button" caption="${submitText}" />
			     		 <c:if test="${submitCopyUrl gt ''}">
			     		 <hdiits:formSubmitButton style="width:${buttonWidthText}" name="formSubmitCopyButton" type="button" onclick="${submitCopyUrl}()" formAction="${submitCopyUrl}" caption="${submitAndCopyText}"/>
						 </c:if>		     	
			     		 <c:if test="${approveURL gt ''}">
			     		 <hdiits:formSubmitButton name="formApproveButton" type="button" formAction="${approveURL}" caption="${approveText}"/>
						 </c:if>
			     		 <!-- <input type="submit" value="${submitText}" /> -->
			     	</c:otherwise>
			     	</c:choose>
			     	
			     	<c:choose>
			     	<c:when test="${disableReset eq 'true'}">
			     		 <input type="button" value="<fmt:message key="TABNAV.RESET" bundle="${lables}"></fmt:message>" disabled="disabled" onClick="resetForm()">
			       	</c:when>
		    		<c:otherwise>
			    		<input type="button" value="<fmt:message key="TABNAV.RESET" bundle="${lables}"></fmt:message>" onClick="resetForm()">			     		 
		    		</c:otherwise>
	    	   </c:choose>
		      
              <script language="javaScript">
              if (navDisplay)
              {	
				if("${previousURL}"!='')
					document.write('<input type="button" name="Prev" value="<fmt:message key="TABNAV.PREVIOUS" bundle="${lables}"></fmt:message>" onClick="${previousURL}(\'Y\')">&nbsp');
				else
					document.write('<input type="button" name="Prev" value="<fmt:message key="TABNAV.PREVIOUS" bundle="${lables}"></fmt:message>" onClick="${param.beforeGoToPrevTab}goToPrevTab()">&nbsp');
				
				if("${nextURL}"!='')
					document.write('<input type="button" name="Next" value="<fmt:message key="TABNAV.NEXT" bundle="${lables}"></fmt:message>" onClick="${nextURL}(\'N\')">&nbsp');
				else
					document.write('<input type="button" name="Next" value="<fmt:message key="TABNAV.NEXT" bundle="${lables}"></fmt:message>" onClick="${param.beforeGoToNextTab}goToNextTab()">&nbsp');
			  }
			 <c:if test="${(closeURL gt '') || (closeWindow eq 'yes')}">
			 
             document.write('<input type="button" value="${closeText}" onClick="javascript:showProgressbar(),${closeAction}">');
			 </c:if>
			  
			 
			 
			  function resetForm()
			  {
			  	if(confirm("<fmt:message key='TABNAV.RESETCONFIRM' bundle='${lables}'></fmt:message>") == true)
			  	{
			  		document.forms[0].reset();
			  		
			  		<c:if test="${param.afterReset gt ''}">
					${param.afterReset};
					</c:if>
			  	
			  	}
			  				  	
			  }
				</script>
		</td>
	</tr>
</table>