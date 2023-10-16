<%
	try
	{
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="allAttachmentList" value="${resValue.refDocsAttachmentList}"></c:set>
<c:set var="allAttributeList" value="${resValue.ListOfAttributes}"></c:set>
<c:set var="allCategoryCodes" value="${resValue.allCatCodes}"></c:set>
<c:set var="srNo" value="1"></c:set>
<c:set var="loopCount" value="0"></c:set>

<script>
	var allCatCodeList = "";
</script>
<form name="first" method="post" >
<frameset border="1">
<table align="center" id="table1" border="1" width="100%" bgcolor="#EEEEEF">
	<c:if test="${!empty allAttributeList}">
	<tr>
		<td colspan="4" align="center" >
			<LABEL class="captionTag">List of Attributes</LABEL>
			<input type="hidden" id="selectedCategoryIds" name="selectedCategoryIds" value="${allCategoryCodes}"/>
			<input type="hidden" value="" id="selectedAttributeIds" name="selectedAttributeIds"/>
		</td>
	</tr>
	<tr>
				<td colspan="4">
					<table align="center" id="table1" border="0" width="100%">
	<c:choose >
		<c:when test="${empty allCategoryCodes}">
					<c:forEach items="${allAttributeList}" var="attList" varStatus="loopIndex">
						<c:if test="${loopIndex.index mod 3 eq 0}">
						</tr> 
						<tr>
					</c:if>
					<td>  
						<input type="radio" name="radioCatCode" value="${attList.categoryCode}" onclick="addRemoveCategoryCode(this)"/>
						<label><c:out value="${attList.categoryName}"></c:out></label>
					</td>
					</c:forEach>
										
		</c:when>
		<c:when test="${!empty allCategoryCodes}">
			<c:forEach items="${allAttributeList}" varStatus="loopIndex" var="attList">
			<c:if test="${loopIndex.index mod 9 eq 0}">
				</tr> 
				<tr>
			</c:if>
					
						<c:set var="loopCount" value="${loopIndex.index+1}"></c:set>
						<c:if test="${loopIndex.index mod 3 eq 0}">
						<td>  
							<input type="checkbox" id="chk_${loopIndex.index}" value="${attList}" onclick="addRemoveAttributeCode(this)"/>
						</c:if>
							<input type="hidden" id="chk_${loopIndex.index}_hidd" value="${attList}"/>
						<c:if test="${loopCount mod 3 eq 0}">
							<label><c:out value="${attList}"></c:out></label>
						</td>
						</c:if>
			</c:forEach>
		</c:when>
		
	</c:choose>
	
		
		<tr>
			<td align="center" colspan="4" style="BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: none">
				<input type="button" value="Get Data" class="buttontag" id="gtDataButton" onclick="getDataForRefDocsAttributes()"/>
			</td>
		</tr>
	</table>
				</td>
			</tr>
</c:if>
<c:if test="${empty allAttributeList}">
<tr>
		<td colspan="4" align="center" >
			<LABEL class="captionTag">List of Attributes</LABEL>
			<input type="hidden" id="selectedCategoryIds" name="selectedCategoryIds" value="${allCategoryCodes}"/>
			<input type="hidden" value="" id="selectedAttributeIds" name="selectedAttributeIds"/>
		</td>
	</tr>
	<tr>
				<td colspan="4">
					<table align="center" id="table1" border="0" width="100%">
						<tr>
							<td>
								No Records Found to Display
							</td>
						</tr>
					</table>
				</td>
			</tr>
</c:if>
</table>
</frameset>

<c:set var="srNo" value="1"></c:set>
<display:table  pagesize="10"  list="${allAttachmentList}" id="row" requestURI="" export="false">	
	<display:column class="tablecelltext" headerClass="datatableheader" titleKey="ACTION" style="width:5%">
		<label>${srNo}</label>
	</display:column>
	<display:column class="tablecelltext" headerClass="datatableheader" title="${row.attributeName1}" style="width:15%">
		<c:out value="${row.attVal1}"></c:out>
	</display:column>
	<display:column class="tablecelltext" headerClass="datatableheader" title="${row.attributeName2}" style="width:15%">
		<c:out value="${row.attVal2}"></c:out>
	</display:column>
	<display:column class="tablecelltext" headerClass="datatableheader" title="${row.attributeName3}" style="width:15%">
		<c:out value="${row.attVal3}"></c:out>
	</display:column>
	<display:column class="tablecelltext" headerClass="datatableheader" title="AttachmentName" style="width:60%">
		<a href="#" onClick="javascript:openNewWindow(${row.attachmentId},${row.attachmentId})"><c:out value="${row.attachmentName}"></c:out></a>
	</display:column>
	<c:set var="srNo" value="${srNo+1}"></c:set>
</display:table>
</form>
<script>

function openNewWindow(attachId,srNo)
{
	window.open("./servlet/FileOpenServlet?attachmentId=" +attachId+"&attachmentSerialNumber="+srNo+"&fileName=abc.txt");
}
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>

<script>
	function getDataForRefDocsAttributes()
	{
		var actionURL = "hdiits.htm?actionFlag=FMS_viewAllRefDocsAttachment";
		if("${resValue.deptId}" != "")
			actionURL = actionURL + "&departId=${resValue.deptId}";
		if("${resValue.locId}" != "")
			actionURL = actionURL + "&locationId=${resValue.locId}";
		if("${resValue.branchId}" != "")
			actionURL = actionURL + "&branchId=${resValue.branchId}";

		document.forms['first'].action=actionURL;
		document.forms['first'].submit();
		//parent.document.getElementById("dataFrame").src = "hdiits.htm?actionFlag=FMS_viewAllRefDocsAttachment&departId=300002"; 
	}

	function addRemoveCategoryCode(radioBtn)
	{
		
		if(radioBtn.checked)
		{
			if(allCatCodeList != "")
				allCatCodeList += ","+radioBtn.value;
			else
				allCatCodeList = radioBtn.value;
		}
		else
		{
			if(allCatCodeList.match(radioBtn.value) != -1)
			{
				if(allCatCodeList.indexOf(radioBtn.value) == 0)
				{
					allCatCodeList = allCatCodeList.substring(allCatCodeList.indexOf(",")+1,allCatCodeList.length);
				}
				else
				{
					allCatCodeList = allCatCodeList.substring(0,allCatCodeList.indexOf(radioBtn.value));
				}
			}
		}
		document.getElementById("selectedCategoryIds").value=allCatCodeList;
	}


	function addRemoveAttributeCode(chkBox)
	{
		var attribValue=chkBox.id.split("_");
		
		if(chkBox.checked)
		{
			if(allCatCodeList != "")
				allCatCodeList += ","+document.getElementById("chk_"+(parseInt(attribValue[1])+1)+"_hidd").value;
			else
				allCatCodeList = document.getElementById("chk_"+(parseInt(attribValue[1])+1)+"_hidd").value;
		}
		else
		{
			if(allCatCodeList.match(document.getElementById("chk_"+(parseInt(attribValue[1])+1)+"_hidd").value) != -1)
			{
				if(allCatCodeList.indexOf(document.getElementById("chk_"+(parseInt(attribValue[1])+1)+"_hidd").value) == 0)
				{
					allCatCodeList = allCatCodeList.substring(allCatCodeList.indexOf(",")+1,allCatCodeList.length);
				}
				else
				{
					allCatCodeList = allCatCodeList.substring(0,allCatCodeList.indexOf(document.getElementById("chk_"+(parseInt(attribValue[1])+1)+"_hidd").value));
				}
			}
		}
		document.getElementById("selectedAttributeIds").value=allCatCodeList;
	}

	/*var displayTableObj = document.getElementById("row");
	var tagNN = displayTableObj.getElementsByTagName("TH") 
	alert(tagNN.length);
	for(var i=0;i<tagNN.length;i++)
	{
		alert("11 " + tagNN[i].innerHTML + " : ");
		tagNN[i].innerHTML = i;
	}*/
	alert(" df");
</script>

<% System.out.println(" iknidnfidnfindifd "); %>