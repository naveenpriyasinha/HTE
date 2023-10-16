
<%
try {
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript">
var subjTypeArr=new Array();
</script>

<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables"  scope="request" />
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="wfDocList"	value="${resultMap.wfDocList}"></c:set> 
<c:set var="messageToDisplay"	value="${resultMap.messageToDisplay}"></c:set>
<c:set var="lmessageToDisplay"	value="${resultMap.lmessageToDisplay}"></c:set>

<hdiits:form name="frmWfDoc" id="frmWfDoc" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="insertHierarchyDetails" />
<hdiits:hidden name="subjectValue" default="0"/>
<hdiits:hidden name="documentId" default="0"/>
<hdiits:jsField  name="hiddlocation" jsFunction="chkLocation()"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="WF.HEADING" bundle="${wfLables}" />
			</a>
		</li>
	</ul>
</div>

<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent">
	<br>
	<c:if test="${lmessageToDisplay ne ''}" >
			<a><b><font color="red" size="2">${lmessageToDisplay}</font></b></a>	
	</c:if>
	
	<br><br>
		<b><u><hdiits:caption captionid="WF.HEADING" bundle="${wfLables}" /></u></b> 
	<br>	
	<table align="center" border="0" id="showDocid"></table>
	<br>
	
	<c:set var="srNo" value="${1}" />		
	<c:if test="${not empty wfDocList}">
		<display:table list="${wfDocList}" id="wfDocList" style="width:100%" pagesize="10" requestURI="">								
			<display:column style="text-align:center" class="tablecelltext" titleKey="WF.SRNO" sortable="false" headerClass="datatableheader"  value="${srNo}"></display:column>
			<c:set var="srNo" value="${srNo+1}" />											
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.DOCNAME"  sortable="false" headerClass="datatableheader">
				<a href="javascript:chkSubjectType('${wfDocList.subjectType}', '${wfDocList.docId}', '${wfDocList.docName}')" id="hrefSubjId">${wfDocList.docName}</a>
			</display:column>  							
		</display:table> 	
	</c:if>			
	
	<table align="center" border="1" class="tabtable">	
		<tr>
			<td align="center" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.NAME" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">
				<hdiits:text name="txtrefName" captionid="WF.NAME" bundle="${wfLables}" maxlength="100" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>

			<td align="center" class="fieldLabel" style="border:none">
				<hdiits:caption	captionid="WF.DESC" bundle="${wfLables}" />
			</td>
			<td align="left" style="border:none">
				<hdiits:textarea name="txtdesc"	captionid="WF.DESC" bundle="${wfLables}" maxlength="200" cols="50%" rows="3%" validation="txt.isname" />
			</td>
		</tr>		
		<tr>
		<td class="fieldLabel" colspan='4' width="100%" style="display:none" id="srchLocationId">	
			<br>
			<div id="locSearch_id">
				<jsp:include page="/WEB-INF/jsp/common/SearchLocation.jsp">
					<jsp:param name="SearchLocation" value="HierarchyLoc"/>
					<jsp:param name="mandatory" value="No"/>
					<jsp:param name="searchLocationTitle" value="Location Search"/>
				</jsp:include>
			</div>
			<br>
		</td>		
	</tr>
	</table>
	</div>
<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<script type="text/javascript">
function chkLocation()
{		
	if(document.getElementById('subjectValue').value=="1")
	{		
		if(document.forms[0].LOCATION_ID_HierarchyLoc.value=="")
		{
			//alert("Please Select The Location!!!!!");
			alert("<fmt:message key='WF.ENTERLOC' bundle='${wfLables}'/>");
			return false;		
		}	
		else 
			return true;				
	}
	else if(document.getElementById('subjectValue').value=="0")
	{
			//alert("Please Select The Document!!!!!");
			alert("<fmt:message key='WF.ENTERDOC' bundle='${wfLables}'/>");
			return false;	
	}
	else
			return true;		
}

function chkSubjectType(subjectType, docId, docName)
{		
	document.getElementById('showDocid').style.display = 'none';
		
	if(subjectType==1)
		document.getElementById('srchLocationId').style.display='';
	else
		document.getElementById('srchLocationId').style.display='none';
		
	document.getElementById('subjectValue').value=subjectType;	
	document.getElementById('documentId').value=docId;	
	
	
	var totalRows=document.getElementById("showDocid").rows.length;
	if(totalRows>0)
	{
		for(i=0;i<eval(totalRows);i++)
		{							
			var trow=document.getElementById('showDocid');      		
			trow.deleteRow(i);
		}
	}
	document.getElementById('showDocid').style.display = '';	
	var b=document.getElementById('showDocid').insertRow();		
    var col1=b.insertCell(0);	
	col1.align="left";						
    col1.innerHTML="Selected Document Name:: "+docName;	 
    document.getElementById('showDocid').style.fontWeight="900";   
}
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
