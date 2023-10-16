
<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>

<c:set var="designationId" value="${resultValue.designationId}"></c:set>
<c:set var="allDep" value="${resultValue.Department}"></c:set>
<c:set var="allLoc" value="${resultValue.Locaton}"></c:set>
<c:set var="allDsgn" value="${resultValue.Disgnation}"></c:set>
<c:set var="selectLoc" value="${resultValue.selectLoc}"></c:set>
<c:set var="selectDep" value="${resultValue.selectDep}"></c:set>
<c:set var="selectDsgn" value="${resultValue.selectDsgn}"></c:set>
<c:set var="bIsMultiple" value="${resultValue.bIsMultiple}"></c:set>
<c:set var="empName" value="${resultValue.empName}"></c:set>
<c:set var="SearchFieldName" value="${resultValue.strSearchFieldName}"></c:set>

<fmt:setBundle basename="resources.ess.utils.search.search" var="comLable" scope="request" />

<script>
  	var mycars=new Array();

	var arrlength;

	var fromLocationCombo = -1;	
		
  function searchGo()
  {
  			var depCode = document.getElementById('depId').value;
  			var locCode = document.getElementById('locId').value;
  			var dsgnCode = document.getElementById('dsgnId').value;
  			
  			startProcess();
  			document.searchEmployee.action="./hdiits.htm?actionFlag=getRosterEmpSearchData&userId=-1&multiple=${bIsMultiple}&searchFieldName=${SearchFieldName}&code=ROSTER&depId="+depCode+"&locId="+locCode+"&dsgnId="+dsgnCode;
			document.searchEmployee.submit();
  }  
  
  
  
  </script>
<script><!--

var emparray = new Array();
var empcount = 0 ;
var finalEmpArr = new Array();


	
	
	
	
	function windowclose()
	{
		
		parseStr();
		window.close(); 
	}  
  function parseStr()
  {
  	
  	var newElem;
  	newElem = document.createElement("option");
  	newElem.value = document.searchEmployee.newemplist.options[1].value;
  	var str=newElem.value;
  	var arrStr=new Array();
  	arrStr=str.split(',');

	
  }






function submmitReq(){

for(var i=0; i<emparray.length; i++){

finalEmpArr[i] = document.getElementById(emparray[i]).value;


}

if (window.dialogArguments) 
    {
    	window.opener = window.dialogArguments;
  		}

if (finalEmpArr.length == 0)
{
	alert('<fmt:message  bundle="${comLable}" key="eis.oneSelect"/>');
	return false;
}
else
{
	window.opener.empSearch(finalEmpArr,document.getElementById("SearchFieldName").value);
	window.close();
}

}


function checkRadio(form)
{
	emparray[empcount]=form.id;
}

function checkclick(form){

if(form.checked == true)
{
emparray[empcount]=form.id;
empcount++;

}else
{ 
  for(var i=0; i<emparray.length; i++)
  {  

  
  if(emparray[i]== form.id){
  emparray.splice(i,1);
    empcount--;
  }

  
  }

}

}

	function showOfficeName(flag, selDepartment, setLocation)
	{
		var url = new String("");
		if (flag == 'location')
		{
			departmentCode = eval("document.searchEmployee."+ selDepartment +".value") ;
			url = "hrms.htm?actionFlag=getLocationFromDepartment&departmentCode="+ departmentCode +"&locationFieldName="+ setLocation;
		}
		
		try
		{
		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
			}
			catch (e)
			{							
				return false; 
			}
		}
	    	
        xmlHttp.open("POST", encodeURI(url) , false); 
		
	 	if (flag == 'location')	
	       xmlHttp.onreadystatechange = processResponseforBlocks; 
	        
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null)); 
	}
	
	function processResponseforBlocks()
	{
		if (xmlHttp.readyState == 4) 
		{ 				
			if (xmlHttp.status == 200) 
			{ 					
				var xmlStr = xmlHttp.responseText; 
				var XMLDoc=getDOMFromXML(xmlStr); 
				if(XMLDoc.getElementsByTagName('option')!= null)
				{
					var officeNm=XMLDoc.getElementsByTagName('option'); 	
					var locationFieldName = XMLDoc.getElementsByTagName('locationFieldName')[0].childNodes[0].text; 
					objOfficeNameCombo = eval("document.searchEmployee."+ locationFieldName) ;
					
					var locationName = new String("");
					objOfficeNameCombo.length = 1; 	
					var selectedOption = "";
					for ( var iter = 0 ; iter < officeNm.length ; iter++ )
					{
						var objoption = document.createElement('option');
						if (officeNm[iter].childNodes[0] != null)
						{
						 	locationName = officeNm[iter].childNodes[0].text;
						 	objoption.text = locationName.replace("amp;","&");
						}
						objoption.value=officeNm[iter].getAttribute('value');
						
						if (officeNm[iter].getAttribute('tag') == fromLocationCombo)
							selectedOption = objoption.value;
						
						try
						{
							objOfficeNameCombo.add(objoption,null); 
						}
						catch(ex)
						{ 
							objOfficeNameCombo.add(objoption); 
						}
					}
					
					if (fromLocationCombo != -1 && selectedOption != "")
					{
						objOfficeNameCombo.value = selectedOption;
						fromLocationCombo = -1;
					}	
					else
						objOfficeNameCombo.value = '0';
				} 	
			}
		}
	}
	
	function resetValues()
	{
		document.searchEmployee.depId.value="0";
		document.searchEmployee.locId.value="0";
		document.searchEmployee.dsgnId.value="0";
		document.searchEmployee.empName.value="";
	}
-->
</script>


<hdiits:form name="searchEmployee" id="searchEmployee" validate="true"
	method="POST">
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="employeeSearch" bundle="${comLable}" /></a></li>
	</ul>
	</div>
	
	<div class="tabcontentstyle" style="height: 100%">
		<div id="tcontent1" class="tabcontent" tabno="0"><br>
		
			<br>
			<br>
			<br>
			
			<table align="center" >
		
				<tr>
		
		
					
					<td><hdiits:caption captionid="department" bundle="${comLable}" />
					</td>
					<td><hdiits:select  readonly="true" name="depId" id="depId" size="1" caption="drop_down"
						onchange="showOfficeName('location','depId','locId');" tabindex="2" sort="false">
						<hdiits:option value="0"><fmt:message key="select" bundle="${comLable}" /></hdiits:option>
						<c:set var="selectedDep" value="" />
						<c:forEach var="DepObj" items="${allDep}">
							<c:if test="${DepObj.departmentId == selectDep}">
								<hdiits:option value="${DepObj.depCode}" selected="true">${DepObj.depName}  </hdiits:option>
							</c:if>
							<c:if test="${DepObj.departmentId != selectDep}">
								<hdiits:option value="${DepObj.depCode}">${DepObj.depName}  </hdiits:option>
							</c:if>	
						</c:forEach>
					</hdiits:select></td>
					<td><hdiits:caption captionid="location" bundle="${comLable}" />
					</td>
					<td><hdiits:select name="locId" readonly="true" id="locId" size="1"
						tabindex="3" sort="false">
		
						<hdiits:option value="0"><fmt:message key="select" bundle="${comLable}" /></hdiits:option>
					</hdiits:select></td>
		
		
				</tr>
		
		
				<tr></tr>
				<tr>
					<td><hdiits:caption captionid="designation" bundle="${comLable}" /></td>
					<td><hdiits:select readonly="true" name="dsgnId" size="1" caption="dsgnId"
						mandatory="false" tabindex="4" sort="false">
						<hdiits:option value="0"><fmt:message key="select" bundle="${comLable}" /></hdiits:option>
						<c:set var="selected" value="" />
						<c:forEach var="DsgnObj" items="${allDsgn}">
							<c:if test="${DsgnObj.dsgnId == selectDsgn}">
								<hdiits:option value="${DsgnObj.dsgnCode}" selected="true"> ${DsgnObj.dsgnName} </hdiits:option>
							</c:if>
							<c:if test="${DsgnObj.dsgnId != selectDsgn}">
								<hdiits:option value="${DsgnObj.dsgnCode}"> ${DsgnObj.dsgnName} </hdiits:option>
							</c:if>	
						</c:forEach>
					</hdiits:select></td>
					<td><hdiits:caption captionid="name" bundle="${comLable}" /></td>
		
					<td><hdiits:text name="empName" size="30" tabindex="6" default="${empName}"/></td>
					<td><hdiits:button name="go" value="Search" type="button"
						tabindex="6" onclick="searchGo()" /></td>
				</tr>
				<tr>
		
					<td><br>
					</td>
				</tr>
				
			<br>
			<br>
			<br>
			
			<tr>
			<td></td>
			</tr>
			
			
			<c:set var="i" value="0" /> <% int a=0; %>
			
				
				<display:table list="${resultValue.Employee}" id="row" requestURI="" pagesize="10"  export="false" style="width:100%" offset="1" >
				
								<display:setProperty name="paging.banner.placement" value="bottom"/>
				<fmt:formatDate var="doj" value="${row.doj}" pattern="dd/MM/yyyy"/>
				
				<display:column class="tablecelltext" title=""
					headerClass="datatableheader" style="text-align: center" >
					
					<c:if test="${empty bIsMultiple or bIsMultiple eq true}">
						<hdiits:checkbox id="check${i}" name="check" value="${row.empId}~${row.empfName} ${row.empmName} ${row.emplName}~${row.userId}~${row.userName}~${row.postId}~${row.postName}~${row.dsgnId}~${row.dsgnName}~${row.locId}~${row.locName}~${row.depId}~${row.depName}~${doj}~${row.categoryDesc}~${row.categoryId}"  onclick="checkclick(this);" />
					</c:if>
					<c:if test="${bIsMultiple eq false}">
						<hdiits:radio id="check${i}" name="check" value="${row.empId}~${row.empfName} ${row.empmName} ${row.emplName}~${row.userId}~${row.userName}~${row.postId}~${row.postName}~${row.dsgnId}~${row.dsgnName}~${row.locId}~${row.locName}~${row.depId}~${row.depName}~${doj}~${row.categoryDesc}~${row.categoryId}"  onclick="checkRadio(this);" />
					</c:if>
					
				</display:column>
				
				<display:column class="tablecelltext"   titleKey="srNo"	headerClass="datatableheader" value="<%=a=a+1 %>"
					style="text-align: center" sortable="false">
				</display:column>
				<display:column class="tablecelltext" titleKey="name"
					headerClass="datatableheader" style="text-align: center"
					sortable="true" >
					${row.empfName}&nbsp;${row.empmName}&nbsp;${row.emplName}
					
				</display:column>
				
				
				<display:column class="tablecelltext" titleKey="designation" 
					headerClass="datatableheader" style="text-align: center"
					sortable="true">
					${row.dsgnName} 
					
				</display:column>
				
				<display:column class="tablecelltext" titleKey="post"
					headerClass="datatableheader" style="text-align: center"
					sortable="true">
					${row.postName} 
				</display:column>
				
				<display:column class="tablecelltext" titleKey="department"
					headerClass="datatableheader" style="text-align: center"
					sortable="true">
					${row.depName} 
					
				</display:column>
				
				<display:column class="tablecelltext" titleKey="department"
					headerClass="datatableheader" style="text-align: center"
					sortable="true">
					${row.categoryDesc} 
					
				</display:column>
				
				<display:column class="tablecelltext" titleKey="DateOfJoining"
					headerClass="datatableheader" style="text-align: center"
					sortable="true">
				
					<fmt:formatDate value="${row.doj}" pattern="dd-MM-yyyy"/>
					</td> 
					
					 
				</display:column>
				
				<c:set var="i" value="${i+1}" />
				
				<display:footer media="html"></display:footer>		
				
			</display:table>
	
			</table>
		</div>


		<table align="center">
			<tr>
				<td><br>
				</td>
			</tr>
			<tr align="center">
				<td align="center"><hdiits:button name="Submit" type="button"
					value="Submit" tabindex="11" onclick="submmitReq()" /> <hdiits:button
					name="Reset" type="button" value="Reset" tabindex="12" onclick="resetValues()"/></td>
			</tr>
		</table>
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab");
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

<hdiits:hidden name="multiple" id="multiple" default="${bIsMultiple}"></hdiits:hidden>
<hdiits:hidden name="designationId" id="designationId" default="${designationId}"></hdiits:hidden>
<hdiits:hidden name="SearchFieldName" id="SearchFieldName" default="${SearchFieldName}"></hdiits:hidden>

<script type="text/javascript">

	setWindowName(window, document.searchEmployee);
	fromLocationCombo = "${selectLoc}";
	showOfficeName('location','depId','locId');

</script>	


<%
	}catch(Exception e){
	e.printStackTrace();
}
%>
