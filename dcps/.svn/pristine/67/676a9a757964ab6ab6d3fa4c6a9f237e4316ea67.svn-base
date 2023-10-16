<%
try {
	

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="disposeclasslst" value="${resValue.disposeclasslst}"></c:set>

<c:set var="corrIdList" value="${resValue.corrIdList}"></c:set>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<hdiits:form name="DisposeCorr" validate="true">
	<!--<hdiits:hidden name="corrIdList" default="${param.corr_list}"/>-->
	<hdiits:hidden name="fileId" default="${param.fileId}"/>
	<table  border="1" bordercolor="black">
	<tr >
	<td  bordercolor="white">
	<hdiits:table  bordercolor="blue"  >
		<hdiits:tr>
		<hdiits:td width="50%">
		 	<hdiits:caption captionid="WF.DisposeRem" bundle="${fmsLables}"/>
		</hdiits:td>
	
		<hdiits:td width="50%">
			<textarea name="ta_remarks" cols="70" rows="4"  ></textarea>
			
		</hdiits:td>
		</hdiits:tr>
		<hdiits:tr>
			<hdiits:td>
			<hdiits:caption captionid="WF.DisposeType" bundle="${fmsLables}" ></hdiits:caption>
			</hdiits:td>
			<hdiits:td>
				<hdiits:radio name="disposetype" id="disposetype1"  mandatory="true" validation="sel.isradio" value="1" default="1" captionid="WF.Positive" bundle="${fmsLables}" ></hdiits:radio>
				<hdiits:radio name="disposetype" id="disposetype2" mandatory="true"  validation="sel.isradio" value="2" captionid="WF.Negative" bundle="${fmsLables}" ></hdiits:radio>
			</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>
			<%--code added by ankita --%>  
			
	
	<hdiits:td>
	<hdiits:caption captionid="WF.DisClass" bundle="${fmsLables}"   ></hdiits:caption>
	</hdiits:td>
	<hdiits:td>
	<hdiits:select name="classification" captionid="WF.DisClass" bundle="${fmsLables}" mandatory="true" validation="sel.isrequired" >
				<hdiits:option value="-1" selected="true">
				<hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
				<c:forEach items="${disposeclasslst}" var="ClassificationLookup">
					<option value='<c:out value="${ClassificationLookup.lookupId}"/>' >
					<c:out value="${ClassificationLookup.lookupDesc}" /></option>
				</c:forEach>
	</hdiits:select>
	</hdiits:td>

			
	</hdiits:tr>
	</hdiits:table>
	<br><br>
	 </td>
    </tr>
    <tr>
    <td bordercolor="white">
	<center><hdiits:button name="tt_Ok" type="button" captionid="WF.Ok" bundle="${fmsLables}" onclick="dispose()"/>
	
	<hdiits:button name="bt_Cancel" captionid="WF.Cancel" bundle="${fmsLables}" type="button" onclick="javascript:window.close()"/></center>
	</td>
	</tr>
	</table>
	<%-- code ended by ankita   --%>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>		
</hdiits:form>
<script>
	function dispose()
	{
		var controlnames=new Array();
	    controlnames.push('ta_remarks');
	    controlnames.push('disposetype');
	    var returnVal =  validateSpecificFormFields(controlnames);
		var selectedClassification=document.getElementById('classification').value;
		if(document.getElementById('ta_remarks').value=='')
		{
			alert('<fmt:message key="WF.DisRemAlert" bundle="${fmsLables}"></fmt:message>');
			return false;
		}
		else if(document.getElementById('classification').value==-1)
		{
			alert('<fmt:message key="WF.SelClassAlert" bundle="${fmsLables}"></fmt:message>');
			return false;
		}
		else
		{
			
			if(returnVal == true)
			{	
				showProgressbar();
				document.forms[0].method='post';
				document.forms[0].action="hdiits.htm?actionFlag=fms_disposeCorr&corrIdList='${corrIdList}'&selectedClassification="+selectedClassification;
				document.forms[0].submit();
			}
		}
	}
</script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>