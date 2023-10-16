
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"	scope="request" />
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="hrDeputreqmtDtl" value="${resultValue.hrDeputreqmtDtl}"></c:set>
<c:set var="cmnLocationMst" value="${resultValue.cmnLocationMst}"></c:set>
<c:set var="orgGradeMst" value="${resultValue.orgGradeMst}"></c:set>
<c:set var="orgPostDetailsRlt" value="${resultValue.orgPostDetailsRlt}"></c:set>
<c:set var="orgPostMst" value="${resultValue.orgPostMst}"></c:set>
<c:set var="setOfEligiblity" value="${resultValue.setOfEligiblity}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>
<c:set var="locations" value="${resultValue.locations}"></c:set>
<script type="text/javascript">
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>
function addRowUsingAjax()
{	
	if(!locationValidate())
	{
		return false;
	}
	
	var fieldArray=new Array();
	fieldArray[0]='location';
	fieldArray[1]='branch';
	if(verifyDuplicate('addDistrict' ,fieldArray, 'encXML','<fmt:message key="Dep.duplicateEntry" bundle="${comLable}" />')==true)
	{
			addOrUpdateRecord('addRecord','broadcastXML',fieldArray);
	}else
	{
		document.initAPP.location.options.selectedIndex=0;
		document.initAPP.branch.options.selectedIndex=0;
	}
}
function addRecord()
{
	
	if (xmlHttp.readyState == 4) 
	{
		var encXML=xmlHttp.responseText;
		var displayFieldArray= new Array();
		displayFieldArray[0]='location';
		displayFieldArray[1]='branch';
		addDataInTable('addDistrict', 'encXML', displayFieldArray, '','deleteRec');
		
	}
	resetLocation();
	
}
function deleteRec(rowId)
{
	var trow=document.getElementById(rowId);
    trow.parentNode.deleteRow(trow.rowIndex);
	deleteFromDuplicateArray('addDistrict',rowId);
	
}
function locationValidate() 
{

	  var rows=document.getElementById('addDistrict').rows;
	  if(document.initAPP.location.options.selectedIndex==0)
	  {
	  	 alert('<fmt:message bundle="${comLable}" key="Dep.locValidate"/>');
	  	return false;
	  }
	  else{
	  	if(document.initAPP.branch.options.selectedIndex==0)
	  	{
	  		alert('<fmt:message bundle="${comLable}" key="Dep.branchValidate"/>');
	  		return false;
	  	}else{
				
		  	 		return true;
			    	
	  	
	  }
	  }
	  
}
function submitValidate() 
{

	  var rows=document.getElementById('addDistrict').rows;
		if(rows.length>1)
		  	{
		  			return true;
		    }else
		    {
		      alert('<fmt:message bundle="${comLable}" key="Dep.selLocValidate"/>');
		   
		    }  	
	  	
	
	
	  
}
function resetLocation()
{
	document.initAPP.location.options.selectedIndex=0;
	document.initAPP.branch.options.selectedIndex=0;
}

</script>

<script type="text/javascript">
function getBranch(loc)
{

	var locId=loc.value;
	
    xmlHttp = GetXmlHttpObject(); 
    var url='./hdiits.htm?actionFlag=getBranchForLoc&locId='+locId;
    xmlHttp.open("POST",encodeURI(url),true);  
    xmlHttp.onreadystatechange=getBranch1;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}
function getBranch1()
{
	removePosts();
		if(xmlHttp.readyState == 4)
		{ 
			if(xmlHttp.status == 200) 
			{  
				    	
	
				    	
				    	var decXML = xmlHttp.responseText;
	
						var xmlDOM = getDOMFromXML(decXML);
					 	var BranchId = xmlDOM.getElementsByTagName('BranchId');
						var BranchDesc = xmlDOM.getElementsByTagName('BranchDesc');	
   						var BranchHeadPostId=xmlDOM.getElementsByTagName('BranchHeadPostId');	
				    	var j=0,k=1;
				    	for ( var i = 0 ; i < BranchId.length ; i++ )
				    	{
				    	    var y=document.createElement('option');
				    	 var brcID = BranchId[i].childNodes[0].text;
				    	 var brcName = BranchDesc[i].childNodes[0].text;
				    	 var brcHeadPostID=BranchHeadPostId[i].childNodes[0].text;
	
				    							
				    		
				
							 y.text=brcName;
							y.value=brcID;
							 x=document.getElementById("branch");
							 x.add(y);
			      }
			     
			}
			

		}
}
function removePosts()
{
  var x=document.getElementById("branch")

  for(i=1; i<=x.length;i++)
  {
  	x.remove(i);
  }
		
}
</script>
<hdiits:form name="initAPP" validate="true" method="POST" action="./hdiits.htm?actionFlag=saveBCList">
	<hdiits:hidden name="BranchHeadPostID" id="BranchHeadPostID" />
	<hdiits:hidden name="fileId" default="${hrDeputreqmtDtl.deputationreqmtId}" />
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
<!-- 	<table align="center" width="100%">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="broadcastdeputDetails" bundle="${comLable}"></fmt:message></u></strong> </font>
			</td>
		</tr>
		</table> -->
<hdiits:fieldGroup titleCaptionId="broadcastdeputDetails" bundle="${comLable}"  collapseOnLoad="true">
	<table align="center" width="100%">
	<!-- 	<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="deputationRequest" bundle="${comLable}"></fmt:message></u></strong> </font>
			</td>
		</tr> -->
		<tr>
		<td>
		<hdiits:fieldGroup titleCaptionId="deputationRequest" bundle="${comLable}"  collapseOnLoad="true">
		<table align="center" width="100%">
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption
						captionid="deputedplace" bundle="${comLable}" /></b></td>
					<td width="25%">${cmnLocationMst.locName}</td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table align="center" width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="address"
						bundle="${comLable}" /></b></td>
					<td width="25%">${cmnLocationMst.locAddr1}</td>
					<td width="25%"><b><hdiits:caption captionid="noofperson"
						bundle="${comLable}" /></b></td>

					<td width="25%">${hrDeputreqmtDtl.noOfPersons}</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table align="center" width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="reqforclass"
						bundle="${comLable}" /></b></td>
					<td width="25%">${orgGradeMst.gradeName}</td>
					<td width="25%"><b><hdiits:caption captionid="reqforpost"
						bundle="${comLable}" /></b></td>

					<td width="25%">${orgPostDetailsRlt.postName}</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td width="25%"><b><hdiits:caption captionid="fromdate"
						bundle="${comLable}" /></b></td>
					<td width="25%"><fmt:formatDate
						value="${hrDeputreqmtDtl.deputfromDate}" pattern="dd/MM/yyyy" /></td>
					<td width="25%"><b><hdiits:caption captionid="todate"
						bundle="${comLable}" /></b></td>

					<td width="25%"><fmt:formatDate
						value="${hrDeputreqmtDtl.deputtoDate}" pattern="dd/MM/yyyy" /></td>
				</tr>
			</table>
			</td>
		</tr>

</table>
</hdiits:fieldGroup>
</td>
</tr>

		
		<tr>
		
			<td>
			<hdiits:fieldGroup titleCaptionId="eligibilitycriteria" bundle="${comLable}"  collapseOnLoad="true">	
				<table align="center" width="80%"  id="criteria" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
				<tr style="background-color:${tdBGColor}">
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="attribute" bundle="${comLable}" /></b></td>
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="condition" bundle="${comLable}" /></b></td>
					<td align="center" colspan="50"><b><hdiits:caption
						captionid="description" bundle="${comLable}" /></b></td>
				</tr>
				<c:forEach var="eligiblity" items="${setOfEligiblity}">
					<tr>
						<td align="left" colspan="50">
						${eligiblity.cmnLookupMstByAttributeId.lookupDesc}</td>
						<td align="left" colspan="50">
						${eligiblity.cmnLookupMstByAttributeConditionId.lookupDesc}</td>
						<td align="left" colspan="50">${eligiblity.attributeValue}</td>
					</tr>
				</c:forEach>
			</table>
			</hdiits:fieldGroup>
			</td>

		</tr>
		
		<tr>
	
			<td>
			<hdiits:fieldGroup titleCaptionId="broadcastdeputDetails" bundle="${comLable}"  collapseOnLoad="true">
			<table align="center" width="100%">

			<!-- <tr bgcolor="#386CB7">
					<td class="fieldLabel" colspan="4" align="center"><font
						color="#ffffff"> <strong><u>
						<fmt:message key="broadcastdeputDetails" bundle="${comLable}"></fmt:message>

						 </u></strong> </font></td>
				</tr> -->	
			
				<tr>
					<td>
					<table width="100%">
						<tr>
							<td width="25%" align="center"><b><hdiits:caption
								captionid="location" bundle="${comLable}" /></b></td>

							<td width="25%"><hdiits:select name="location"
								onchange="getBranch(this)" id="location">
								<option value="Select"><fmt:message key="Dep.sel" bundle="${comLable}"></fmt:message></option>
								<c:forEach var="locationsObj" items="${locations}">
									<hdiits:option value="${locationsObj.locId}">${locationsObj.locName}</hdiits:option>

								</c:forEach>
							</hdiits:select></td>
							<td width="25%"><b><hdiits:caption captionid="branch"
								bundle="${comLable}" /></b></td>

							<td width="25%"><hdiits:select name="branch" id="branch">
								<option value="Select"><fmt:message key="Dep.sel" bundle="${comLable}"></fmt:message></option>
							
							</hdiits:select></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table align="center" width="20%">
						<tr>

							<td align="right" colspan="50" width="10%"><hdiits:button
								name="addB" captionid="addB" bundle="${comLable}" type="button"
								onclick="addRowUsingAjax()" tabindex="3" /></td>
							
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>

					<table id="addDistrict" border="1" align="center" width="60%" style="display: none;"  border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
						<tr style="background-color:${tdBGColor}">
							<td class="fieldLabel" width="20%" align="center"><b><hdiits:caption
								captionid="location" bundle="${comLable}" /></b></td>
							<td class="fieldLabel" width="20%" align="center"><b><hdiits:caption
								captionid="branch" bundle="${comLable}" /></b></td>
							<td class="fieldLabel" width="20%" align="center"><b><hdiits:caption
								captionid="action" bundle="${comLable}" /></b></td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</hdiits:fieldGroup>
			</td>
			</tr>
			
			
	</table>
	<table align="center" width="30%">
								<tr>

									<hdiits:jsField jsFunction="submitValidate()" name="validate" />

								</tr>
								<tr>

								</tr>
							</table>

<jsp:include page="../../../core/tabnavigation.jsp" />

</hdiits:fieldGroup>
<hdiits:validate controlNames="text"
	locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
