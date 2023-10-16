<%	try{ %>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"> </script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript">
var outwardIdArr=new Array();
var fileDescArr=new Array();
</script>


<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="lfmsfiletabdtlsList" value="${resultMap.lfmsfiletabdtlsList}"></c:set>
<c:set var="lfileNo" value="${resultMap.lfileNo}"></c:set>
<c:set var="lfmscorrtabdtlsList" value="${resultMap.lfmscorrtabdtlsList}"></c:set>
<c:set var="lcorrNo" value="${resultMap.lcorrNo}"></c:set>
<c:set var="lflag" value="${resultMap.lflag}"></c:set>


<hdiits:form name="addOrderFrm" validate="true"  encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="hiddFileNo" default="${lfileNo}"/>
<hdiits:hidden name="hiddlflag" default="${lflag}"/>
	<table width="100%" border="0">
		<tr>
			<td>
				<center>
				<a>
				<b>
					<hdiits:caption	captionid="FMS.DRAFT" bundle="${fmsLables}"/>
				</b>
				</a>
				</center>
			</td>
		</tr>
	</table>	
	<c:set var="srNo" value="${1}" />		
	<c:if test="${not empty lfmsfiletabdtlsList}">
		<display:table list="${lfmsfiletabdtlsList}" id="lfmsfiletabdtlsList" style="width:100%" pagesize="10" requestURI="">								
			<display:column style="text-align:center" class="tablecelltext"  media="HTML">				
				<input type="checkbox" id="${lfmsfiletabdtlsList.refFileCorrId}" value="${lfmsfiletabdtlsList.refFileCorrId}" name="viewDraft" onclick="getOutwardId('${lfmsfiletabdtlsList.refFileCorrId}', '${lfmsfiletabdtlsList.fmsFileMst.fileDesc}')"/>				
			</display:column>																													   
			<display:column style="text-align:center" class="tablecelltext" titleKey="WF.SRNO" sortable="false" headerClass="datatableheader"  value="${srNo}"></display:column>
			<c:set var="srNo" value="${srNo+1}" />											
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.DOCNAME"  sortable="false" headerClass="datatableheader">${lfmsfiletabdtlsList.draftName}</display:column>  							
			<display:column style="text-align:center" class="tablecelltext"  title="DraftId"  sortable="false" headerClass="datatableheader">${lfmsfiletabdtlsList.refFileCorrId}</display:column>  							
			<display:column style="text-align:center" class="tablecelltext"  title="File Desc"  sortable="false" headerClass="datatableheader">${lfmsfiletabdtlsList.fmsFileMst.fileDesc}</display:column>  													
		</display:table> 	
	</c:if>	
		
	<c:if test="${not empty lfmscorrtabdtlsList}">
		<display:table list="${lfmscorrtabdtlsList}" id="lfmscorrtabdtlsList" style="width:100%" pagesize="10" requestURI="">								
			<display:column style="text-align:center" class="tablecelltext"  media="HTML">				
				<input type="checkbox" id="${lfmscorrtabdtlsList.tabRefId}" value="${lfmscorrtabdtlsList.tabRefId}" name="viewDraft" onclick="getOutwardId('${lfmscorrtabdtlsList.tabRefId}', '${lfmscorrtabdtlsList.fmsCorrMst.corrDesc}')"/>				
			</display:column>																													   
			<display:column style="text-align:center" class="tablecelltext" titleKey="WF.SRNO" sortable="false" headerClass="datatableheader"  value="${srNo}"></display:column>
			<c:set var="srNo" value="${srNo+1}" />											
			<display:column style="text-align:center" class="tablecelltext"  titleKey="WF.DOCNAME"  sortable="false" headerClass="datatableheader">${lfmscorrtabdtlsList.draftName}</display:column>  							
			<display:column style="text-align:center" class="tablecelltext"  title="DraftId"  sortable="false" headerClass="datatableheader">${lfmscorrtabdtlsList.tabRefId}</display:column>  							
			<display:column style="text-align:center" class="tablecelltext"  title="Corr Desc"  sortable="false" headerClass="datatableheader">${lfmscorrtabdtlsList.fmsCorrMst.corrDesc}</display:column>  													
		</display:table> 	
	</c:if>	
	
	<c:if test="${empty lfmscorrtabdtlsList and empty lfmsfiletabdtlsList}">	
		<center>
				<hdiits:caption  captionid="WF.NORCRD" bundle="${fmsLables}" />	
		</center>		
	</c:if>	
		
	<table align="center" border="0">
		<tr><td>
			<hdiits:button type="button"  name="btnOk" value="Ok" onclick="storeData()"/>
		</td></tr>
	</table>
</hdiits:form>
<script type="text/javascript">
var hiddoutwardId;
var fileDesc;
var len;

function getOutwardId(outwardId, desc)
{		
	if(document.getElementById(outwardId).checked==true)
	{
		outwardIdArr.push(outwardId);
		fileDescArr.push(desc);	
	}	
	else
	{
		for(var i=0;i<outwardIdArr.length;i++)
		{
			if(outwardIdArr[i] == outwardId)
			{
				outwardIdArr.splice(i,1);
			}
		}
	}			
}	

function storeData()
{			
	len=outwardIdArr.length;			
	window.opener.displayDraftTab(outwardIdArr, fileDescArr, len);	
	window.close();
}
</script>
<% 
}catch(Exception e)
{
	e.printStackTrace();	
}

%>
