<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="MediType" value="${resultValue.MediType}">
</c:set>
<c:set var="MediCategory" value="${resultValue.MediCategory}">
</c:set>
<c:set var="MedName" value="${resultValue.MedName}">
</c:set>
<c:set var="MedType" value="${resultValue.MedType}">
</c:set>
<c:set var="MedCat" value="${resultValue.MedCat}">
</c:set>

<fmt:setBundle basename="resources.ess.MRB.MRBLables" var="cmnLables" scope="request" />
<script>

function getMediDtl()
{
	var MedName=document.getElementById("MedName").value;
	
	var iChars = "#^+=\\\;|\<>";
	for (var i = 0; i < MedName.length; i++)
  	{
  		if (iChars.indexOf(MedName.charAt(i)) != -1) 
  		{
  			alert('<fmt:message  bundle="${cmnLables}"  key="MRB.SpclChars"/>');
  			document.getElementById("MedName").value="";
  			document.getElementById("MedName").focus();
  			return false;
  		}
  	}
	
	var MedType=document.getElementById("MedType").value;
	
	var MedCat=document.getElementById("MedCat").value;
	

	document.MRBFRM1.action="hrms.htm?actionFlag=getMediSearchResult&MediName="+MedName+"&MediType="+MedType+"&MediCat="+MedCat; 
	document.MRBFRM1.submit();
			
}

</script>

<hdiits:form name="MRBFRM1" validate="true" method="POST" action=""
	encType="multipart/form-data">
	
	<c:set var="notAvl" value="[]"></c:set>
	<table id="MedicineDtl" width="100%">
		<tbody>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.MED_NAME"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:text name="MedName" id="MedName"
					 validation="txt.isrequired" mandatory="true" maxlength="50" captionid="MRB.MED_NAME"
					bundle="${cmnLables}" />
				</td>

				<td width="25%"><hdiits:caption captionid="MRB.MED_TYPE"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:select name="MedType" id="MedType"
					  captionid="MRB.MED_TYPE" sort="true"
					bundle="${cmnLables}" default="${MedType}">
					<hdiits:option value="">------------Select----------</hdiits:option>
					<c:forEach var="Medicine_type" items="${MediType}">
					<hdiits:option value="${Medicine_type.lookupName}">
				${Medicine_type.lookupDesc}</hdiits:option>
				</c:forEach>
					</hdiits:select>
				</td>
			</tr>
			<tr>
				<td width="25%"><hdiits:caption captionid="MRB.MED_CAT"
					bundle="${cmnLables}" /></td>
				<td width="25%"><hdiits:select name="MedCat" id="MedCat"
					  captionid="MRB.MED_CAT" sort="true"
					bundle="${cmnLables}" default="${MedCat}">
				<hdiits:option value="">------------Select-----------</hdiits:option>
				<c:forEach var="Medicine_Category" items="${MediCategory}">
					<hdiits:option value="${Medicine_Category.lookupName}">
				${Medicine_Category.lookupDesc}</hdiits:option>
				</c:forEach>
				</hdiits:select>
				</td>
				
				<c:set var="NoData" value=""></c:set>
				<c:if test="${resultValue.MediSrchReslt eq '[]'}">
					<td  width="25%" id="MediCaption" style="display:none" >
					<hdiits:caption captionid="MRB.MED_WT" bundle="${cmnLables}" />
					</td>
					<td width="25%">
						<hdiits:text name="MediWt" style="display:none" id="MediWt"/>
					</td>
				</c:if>
				
			</tr>
		</tbody>
	</table>
	<br>
	<center>
		<hdiits:button name="SearchMed" type="button" value="Search" onclick="getMediDtl()" captionid="MRB.Search" bundle="${cmnLables}"/>
	</center>
	<br>
	
	
	<c:if test="${resultValue.MediSrchReslt ne 'null' and  resultValue.MediSrchReslt ne '[]'}">
	<c:set var="i" value="0" /> <% int a=0; %>
	<display:table list="${resultValue.MediSrchReslt}" id="row" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1" >
		
		
		<display:setProperty name="paging.banner.placement" value="bottom"  />
		
		
		<display:column class="tablecelltext" title=" Select"
			headerClass="datatableheader" style="text-align: center" >
			<hdiits:checkbox id="check${i}" name="check" value="${row.medicineName}~${row.medicineType}~${row.medicineCat}~${row.medicineWt}~${row.medicineId}"/>
		</display:column>
		
		<display:column class="tablecelltext"  	title=" Sr.No"	headerClass="datatableheader" value="<%=a=a+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext"  titleKey="MRB.MED_NAME"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.medicineName} 
		</display:column>
		
		
		<display:column class="tablecelltext"  titleKey="MRB.MED_TYPE"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
			${row.medicineType} 
			
		</display:column>
		<display:column class="tablecelltext"  titleKey="MRB.MED_CAT"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
			${row.medicineCat} 
			
		</display:column>
		<display:column class="tablecelltext"  titleKey="MRB.MED_WT"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
			${row.medicineWt} 
			
		</display:column>
		<c:set var="i" value="${i+1}" />
	</display:table>
	</c:if>	
	<!--  </div>
	</div>-->	
	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' /> 
	

	<script>
	if("${resultValue.MediSrchReslt}"=="[]")
	{
		var answer=confirm('<fmt:message  bundle="${cmnLables}"  key="MRB.SearchResultNull"/>')
		if(answer)
		{
			document.getElementById("MediWt").style.display="";
			document.getElementById("MediCaption").style.display="";
			document.getElementById("SearchMed").style.display="";
			document.getElementById("MedName").value="${MedName}";
		}
		else
		{
			document.getElementById("MedType").value="-1";
			document.getElementById("MedCat").value="-1";
			//document.getElementById("MediWt").style.display="none";
			document.getElementById("MediCaption").style.display="none";
		}
	}
	</script>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
