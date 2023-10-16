<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.core.lables" var="lables"
	scope="request" />
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables"
	scope="request" />

<%--<fmt:setBundle basename="resources.core.lables" var="lables" scope="request"/>
<fmt:message key="TABNAV.SUBMIT" bundle="${lables}" var="submitText"></fmt:message>
<fmt:message key="TABNAV.CLOSE" bundle="${lables}" var="closeText"></fmt:message>
--%>
<table class="tabNavigationBar">
	<tr align="center">
		<td class="tabnavtdcenter" id="tabnavtdcenter"><hdiits:formSubmitButton
				name="formSubmitButton" value="Save" type="button"
				captionid="eis.save" bundle="${Lables}" id="formSubmitButtonTemp" />
			<hdiits:button name="Close" value="Close" type="button"
				captionid="eis.close" bundle="${Lables}" onclick="onBackfn();" />
			<%-- <hdiits:button
			name="Back" value="Back" type="button" captionid="eis.back"
			bundle="${Lables}" onclick="onBackfn();" />  <hdiits:resetbutton type="button" value="Reset" name="reset" />  --%>
			<hdiits:button name="Reset" type="button" value="Reset"
				onclick="resetForm()" /> <!-- <input type="reset" value="Reset"/>-->
			<script language="javaScript">             
              //if (navDisplay)
              //{
				//document.write('<input type="button" value="Reset" onClick="resetForm()">');
				//document.write('<input type="button" value="Previous" onClick="goToPrevTab()">');
				//document.write('<input type="button" value="Next" onClick="goToNextTab()">');
			 // }

			  /*function resetForm()
			  {
			  	if(confirm("All values will be reseted please confirm !") == true)
			  	{
			  		document.forms[0].reset();
			  	}

			  }*/
			  function onclosefn()
			  {
						document.forms[0].action="./hrms.htm?actionFlag=getHomePage";
						document.forms[0].submit();
			  }
			  function onBackfn()
			  {						
						if(document.forms[0].givenurl!=null)
						{
						var url="./hrms.htm?actionFlag="+document.forms[0].givenurl.value;
						document.forms[0].action=url;
						}
						else
						{
						document.forms[0].action="./hrms.htm?actionFlag=getHomePage";
						}
						document.forms[0].submit();
			  }
			  function resetForm()
			  {
			  	if(confirm("<fmt:message key="TABNAV.RESETCONFIRM" bundle="${lables}"></fmt:message>") == true)
			  	{
			  		document.forms[0].reset();
			  		<c:if test="${param.afterReset gt ''}">
						${param.afterReset};
					</c:if>
					window.location.reload();
			  	}

			  }

				</script> <!-- Message in gujarati is still remains....Jaspal.. --></td>
	</tr>
</table>
