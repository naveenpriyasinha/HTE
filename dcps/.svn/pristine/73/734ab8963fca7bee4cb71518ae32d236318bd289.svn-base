
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
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="allDep" value="${resultValue.Department}">
</c:set>
<c:set var="allLoc" value="${resultValue.Locaton}">
</c:set>
<c:set var="allDsgn" value="${resultValue.Disgnation}">
</c:set>

<c:set var="selectLoc" value="${resultValue.selectLoc}">
</c:set>
<c:set var="selectDep" value="${resultValue.selectDep}">
</c:set>
<c:set var="selectDsgn" value="${resultValue.selectDsgn}">
</c:set>
<c:set var="bIsMultiple" value="${resultValue.bIsMultiple}">
</c:set>
<c:set var="empName" value="${resultValue.empName}">
</c:set>
<c:set var="SearchFieldName" value="${resultValue.strSearchFieldName}"></c:set>


<c:set var="retCmbLst" value="${resultValue.retCmbLst}"></c:set>	
<c:set var="code" value="${resultValue.code}"></c:set>

<fmt:setBundle basename="resources.ess.utils.search.search" var="comLable"
	scope="request" />

<script>
  	var mycars=new Array();

	var arrlength;

	var fromLocationCombo = -1;	
		
  function searchGo()
  {
  			startProcess();
  			document.searchEmployee.action="./hdiits.htm?actionFlag=getEmpSearchData&userId=-1&multiple=${bIsMultiple}&searchFieldName=${SearchFieldName}&code=${code}";
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






function submitReq(){
	
	for(var i=0; i<emparray.length; i++)
	{
		finalEmpArr[i] = document.getElementById(emparray[i]).value;
	}
	if("${code}"=='')
	{
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
	else
	{
		
		showProgressbar('Submitting Request...<br>Please wait...');
		if(finalEmpArr.length != 0)
		{
			if( '${code}' == 'LE' )
			{
				document.forms[0].action = "hrms.htm?actionFlag=leaveencashment";
				document.forms[0].submit();
			}
			else if ( '${code}' == 'GA' )
			{
				document.forms[0].action = "hrms.htm?actionFlag=calgratuity";
				document.forms[0].submit();
			}
			
			else if ( '${code}' == 'RTA_RI' )
			{
				document.forms[0].action = "hrms.htm?actionFlag=viewRtaTravelRequest";
				document.forms[0].submit();
			}
			
			else if ( '${code}' == 'GI' )
			{
				document.forms[0].action = "hrms.htm?actionFlag=viewgroupinsurance";
				document.forms[0].submit();
			}
		}
		else{
			alert('<fmt:message  bundle="${comLable}" key="eis.oneSelect"/>');
			hideProgressbar();
			return;
			
		}
		
	}
}


function checkRadio(form)
{
	
	emparray[empcount]=form.id;
	document.getElementById('userId').value=form.value;
}

function checkclick(form)
{
	if(form.checked == true)
	{
		emparray[empcount]=form.id;
		empcount++;
	}
	else
	{ 
	  	for(var i=0; i<emparray.length; i++)
	  	{  
		  if(emparray[i]== form.id)
		  {
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
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="employeeSearch" bundle="${comLable}" /></a></li>
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
					<td><hdiits:select name="depId" id="depId" size="1" caption="drop_down"
						onchange="showOfficeName('location','depId','locId');" tabindex="2" sort="false">
						<hdiits:option value="0"><hdiits:caption captionid="select" bundle="${comLable}" /></hdiits:option>
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
					<td><hdiits:select name="locId" id="locId" size="1"
						tabindex="3" sort="false">
		
						<hdiits:option value="0"><hdiits:caption captionid="select" bundle="${comLable}" /></hdiits:option>
					</hdiits:select></td>
		
		
				</tr>
		
		
				<tr></tr>
				<tr>
					<td><hdiits:caption captionid="designation" bundle="${comLable}" /></td>
					<td><hdiits:select name="dsgnId" size="1" caption="dsgnId"
						mandatory="false" tabindex="4" sort="false">
						<hdiits:option value="0"><hdiits:caption captionid="select" bundle="${comLable}" /></hdiits:option>
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
					
				</tr>
				
				<tr>
					<td class="fieldLabel" ><hdiits:caption captionid="retirintbtw" bundle="${comLable}"></hdiits:caption></td>
					<td class="fieldLabel" >
						<hdiits:dateTime name="retFromDate" validation="txt.isrequired"  mandatory="true" captionid="retirefromdate" bundle="${comLable}" default="${resultValue.retFromDate}" maxvalue="31/12/9999"></hdiits:dateTime>
					</td>
					
					<td class="fieldLabel" ><hdiits:caption captionid="to" bundle="${comLable}"></hdiits:caption></td>
					<td class="fieldLabel" >
						<hdiits:dateTime name="retToDate" validation="txt.isrequired"  mandatory="true" captionid="retiretodate" bundle="${comLable}" default="${resultValue.retToDate}" maxvalue="31/12/9999"></hdiits:dateTime>
					</td>
			
				</tr>
				
				<tr >
					<td class="fieldLabel" ><hdiits:caption captionid="type" bundle="${comLable}"></hdiits:caption></td>
					<td class="fieldLabel" >
						<hdiits:select name="retTypecmb" captionid="type" bundle="${comLable}" validation="sel.isrequired" id="retTypecmb" mandatory="true"  >
							<hdiits:option value="Select"><hdiits:caption bundle="${comLable}" captionid ="select" /></hdiits:option>
							<c:forEach var="cmbType" items="${retCmbLst}">
				    					<option value="<c:out value="${cmbType.lookupName}"/>">
										<c:out value="${cmbType.lookupDesc}"/></option>						
							</c:forEach>
						</hdiits:select>
					</td>
				</tr>
				
				<tr>
					<td align="center" width="100%" colspan="4"><hdiits:button name="go" value="Search" type="button"
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
				
				
				<display:column class="tablecelltext" title=""
					headerClass="datatableheader" style="text-align: center" >
					
					<c:if test="${empty bIsMultiple or bIsMultiple eq true}">
						<hdiits:checkbox id="check${i}" name="check" value="${row.empId}~${row.empfName} ${row.empmName} ${row.emplName}~${row.userId}~${row.userName}~${row.postId}~${row.postName}~${row.dsgnId}~${row.dsgnName}~${row.locId}~${row.locName}~${row.depId}~${row.depName}"  onclick="checkclick(this);" />
					</c:if>
					<c:if test="${bIsMultiple eq false}">
						<hdiits:radio id="check${i}" name="check" value="${row.userId}"  onclick="checkRadio(this);" />
					</c:if>
					
				</display:column>
				
				<display:column class="tablecelltext"   titleKey="srNo"	headerClass="datatableheader" value="<%=a=a+1 %>"
					style="text-align: center" sortable="false">
				</display:column>
				<display:column class="tablecelltext" titleKey="name"
					headerClass="datatableheader" style="text-align: center"
					sortable="true" >
					${row.empfName} ${row.empmName} ${row.emplName}
					
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
			
			<c:if test="${code == 'LE'}">
				<c:set var="submitoptn" value="LeaveEncashment" />
			</c:if>	
			
			<c:if test="${code == 'GA'}">
				<c:set var="submitoptn" value="Gratuity" />
			</c:if>	
			
			
			<c:if test="${code == 'RTA_RI'}">
				<c:set var="submitoptn" value="Reimbursement" />
			</c:if>	
			
			<c:if test="${code == 'GI'}">
				<c:set var="submitoptn" value="GroupInsurance" />
			</c:if>	
			
			
			<tr align="center">
				<td align="center"><hdiits:button name="Submit" type="button" 
					value="${submitoptn}" tabindex="11" onclick="submitReq()" /> <hdiits:button
					name="Reset" type="button" value="Reset" tabindex="12" onclick="resetValues()"/></td>
			</tr>
		</table>
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab");
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
<hdiits:hidden caption="userId" id="userId" name="userId"  default="-1"/> 
<hdiits:hidden caption="code" id="" name="code" default="${code}" /> 
</hdiits:form>

<hdiits:hidden name="multiple" id="multiple" default="${bIsMultiple}"></hdiits:hidden>
<hdiits:hidden name="SearchFieldName" id="SearchFieldName" default="${SearchFieldName}"></hdiits:hidden>



<script type="text/javascript">

	setWindowName(window, document.searchEmployee);
	fromLocationCombo = "${selectLoc}";
	showOfficeName('location','depId','locId');

</script>	

<c:if test="${selectedDep ne ''}">
	<script>
									//selectedCombo("${selectedDep}",document.forms[0].depId);
								</script>
</c:if>
<c:if test="${selectedLoc ne ''}">
	<script>
									//selectedCombo("${selectedLoc}",document.forms[0].locId);
								</script>
</c:if>
<c:if test="${selected ne ''}">
	<script>
									//selectedCombo("${selected}",document.forms[0].dsgnId);
								</script>
</c:if>

<c:if test="${resultValue.retType  ne ''}">
	<script>
			//selectedCombo("${resultValue.retType}",document.forms[0].retTypecmb);
	</script>
</c:if>

<c:if test="${resultValue.retFrom  ne ''}">
	<script>
		document.forms[0].retFromDate.value='${resultValue.retFrom}';
	</script>
</c:if>

<c:if test="${resultValue.retTo  ne ''}">
	<script>
		document.forms[0].retToDate.value='${resultValue.retTo}';
	</script>
</c:if>

	<script>
			/*
			if(document.forms[0].userId.value=="" || document.forms[0].userId.value=="-1"){
			document.forms[0].subButton.disabled=true;
			}
			else{
			document.forms[0].subButton.disabled=false;
			}
			*/
	</script>


<%
	}catch(Exception e){
	e.printStackTrace();
}
%>
