
<%
try{
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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
<c:set var="retCmbLst" value="${resultValue.retCmbLst}"> </c:set>	
<c:set var="code" value="${resultValue.code}"></c:set>   											
<fmt:setBundle basename="resources.search.search" var="comLable"
	scope="request" />
<fmt:setBundle basename="resources.hr.gratuityLeaveencashment.commonretirement" var="awdlbl" scope="request"/>
<script>
  	var mycars=new Array();

	var arrlength;



function submitReq()
{
	//alert('inside submitReq');
	//alert(emparray.length);
	
	
	
	for(var i=0; i<emparray.length; i++){
	
	finalEmpArr[i] = document.getElementById(emparray[i]).value;
	
	
	}
	//window.opener.empSearch(finalEmpArr);
	//window.close();
	if(document.forms[0].userId.value!="" && eval(document.forms[0].userId.value)!=eval(-1) ){
	if( '${code}' == 'LE' )
	{
		document.forms[0].action = "hrms.htm?actionFlag=leaveencashment";
		if(confirm('<fmt:message  bundle="${awdlbl}" key="HRMS.SubmitConfirmation"/>')){
			document.forms[0].submit();
		}
	}
	else if ( '${code}' == 'GA' )
	{
		document.forms[0].action = "hrms.htm?actionFlag=calgratuity";
		if(confirm('<fmt:message  bundle="${awdlbl}" key="HRMS.SubmitConfirmation"/>')){
		document.forms[0].submit();
		}
	}
	
	else if ( '${code}' == 'RTA_RI' )
	{
		document.forms[0].action = "hrms.htm?actionFlag=viewRtaTravelRequest";
		if(confirm('<fmt:message  bundle="${awdlbl}" key="HRMS.SubmitConfirmation"/>')){
		document.forms[0].submit();
		}
	}
	
	else if ( '${code}' == 'GI' )
	{
		document.forms[0].action = "hrms.htm?actionFlag=viewgroupinsurance";
		if(confirm('<fmt:message  bundle="${awdlbl}" key="HRMS.SubmitConfirmation"/>')){
		document.forms[0].submit();
		}
	}
	
	}
	else{
		alert("No User is selected");
	}
	
}

		
		
function selectedCombo(value,combo)
{
	var length=combo.options.length;
	
	for(i=0;i<length;i++)
	{
		if(value == combo.options[i].value)
		{
			combo.options[i].selected=true;
			break;
		}
	}
}




  function OnChange(obj,ctry)
  {
  		
 var mainPgFieldArray= new Array('retFromDate','retToDate','retTypecmb');
 statusMainPgValidation =  validateSpecificFormFields(mainPgFieldArray);
if(!statusMainPgValidation)
{
return;
}
 
  		if(ctry == 'department')
  		{

   		
  			document.searchEmployee.action="./hdiits.htm?actionFlag=upList_LE&userId=-1";
			document.searchEmployee.submit();
  		
  		}
  		
 		if(ctry == 'location')
  		{
   		
  			document.searchEmployee.action="./hdiits.htm?actionFlag=upList_LE&userId=-1";
			document.searchEmployee.submit();
  		
  		}
 		if(ctry == 'post')
  		{
  		
	  		document.searchEmployee.action="./hdiits.htm?actionFlag=upList_LE&userId=-1";
			document.searchEmployee.submit();
  		}
  }
  function searchGo()
  {
  
  		
 var mainPgFieldArray= new Array('retFromDate','retToDate','retTypecmb');
 statusMainPgValidation =  validateSpecificFormFields(mainPgFieldArray);
if(!statusMainPgValidation)
{
return;
}
  			document.searchEmployee.action="./hdiits.htm?actionFlag=searchEmp_LE&userId=-1";
			document.searchEmployee.submit();
  }  
  
  
  
  </script>
<script>


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

function highlight(which,color){
if (document.all||document.getElementById)
which.style.backgroundColor=color
}




function submmitReq(){
//alert(emparray.length);
for(var i=0; i<emparray.length; i++){

finalEmpArr[i] = document.getElementById(emparray[i]).value;


}
window.opener.empSearch(finalEmpArr);
window.close();

}


function checkclick(selectedRadio){
var empcount=0;
	/*if(form.checked == true)
	{
			emparray[empcount]=form.id;
	}
	else
		{ 
			  for(var i=0; i<emparray.length; i++){  
					/*alert(emparray[i]+'=='+ form.id);
					  if(emparray[i]== form.id){
						  emparray.splice(i,1);
						    empcount--;*/
			 /* }
		}*/



	document.forms[0].userId.value=selectedRadio.value;
	document.forms[0].subButton.disabled=false;
}

function selectCountry()
	{
		 var formElementsLength = document.forms[0].elements.length;
		  
		 var noOfRowSelected = 0;
		 var selectedRowIndex;
		  
		 for(var iElement=0; iElement<formElementsLength; iElement++)
		 	if(document.forms[0].elements[iElement].type == "radio")
				if(document.forms[0].elements[iElement].checked == true)
				{
		 			noOfRowSelected= noOfRowSelected + 1;
		 			selectedRowIndex = iElement;
		 		}
			       
	  	
	      alert(document.forms[0].elements[selectedRowIndex].parentNode.parentNode.childNodes[2].childNodes[0].innerHTML);  
	  
 	    //opener.document.forms[0].name_txtCountryname1.value = document.forms[0].elements[selectedRowIndex].parentNode.parentNode.childNodes[2].innerHTML;
    	//opener.document.forms[0].hdnCountryId.value = document.forms[0].elements[selectedRowIndex].value;
   
  		//window.close();
  	
 	   // setSelectedItem("txtCountryname1", document.forms[0].elements[selectedRowIndex].parentNode.parentNode.childNodes[2].innerHTML, document.forms[0].elements[selectedRowIndex].value);
 	   
	    return true;
	}
	
	function setSelectedItem(searchFieldName, displayText, displayTextId)
	{
		
		
		
		if (window.opener.document != null)
		{
			if (eval("window.opener.document.getElementsByName('name_"+ searchFieldName +"')") != null)
				eval("window.opener.document.getElementsByName('name_"+ searchFieldName +"')[0].value='"+ displayText +"'");
			
			if (eval("window.opener.document.getElementById('"+ searchFieldName +"')") != null)	
		    	eval("window.opener.document.getElementById('"+ searchFieldName +"').value='"+ displayTextId +"'");
		}

  		window.close();
	}
									
</script>
<hdiits:form name="searchEmployee" id="searchEmployee" validate="true"
	method="POST">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message key="HRMS.employeesearchTab"/>
		</a></li>
	</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>
	<table align="center" >

		<tr>
			<td><hdiits:caption captionid="HRMS.department" bundle="${awdlbl}" />
			</td>
			<td><hdiits:select name="depId" size="1" caption="drop_down"
				onchange="OnChange(this,'department')" tabindex="2" sort="false">
				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:set var="selectedDep" value="" />
				<c:forEach var="DepObj" items="${allDep}">
					<c:if test="${DepObj.depId == selectDep}">
						<c:set var="selectedDep" value="${selectDep}" />
					</c:if>
					<hdiits:option value="${DepObj.depId}">${DepObj.depName}  </hdiits:option>
				</c:forEach>

			</hdiits:select></td>
			<td><hdiits:caption captionid="HRMS.location" bundle="${awdlbl}" />
			</td>
			<td><hdiits:select name="locId" size="1"
				onchange="OnChange(this,'location')" tabindex="3" sort="false">

				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:set var="selectedLoc" value="" />
				<c:forEach var="LocObj" items="${allLoc}">
					<c:if test="${LocObj.locId == selectLoc}">
						<c:set var="selectedLoc" value="${selectLoc}" />
					</c:if>
					<hdiits:option value="${LocObj.locId}"> ${LocObj.locName} </hdiits:option>
				</c:forEach>
			</hdiits:select></td>


		</tr>


		<tr></tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.post" bundle="${awdlbl}" /></td>
			<td><hdiits:select name="dsgnId" size="1" caption="dsgnId"
				mandatory="false" onchange="OnChange(this,'post')" tabindex="4" sort="false">
				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:set var="selected" value="" />
				<c:forEach var="DsgnObj" items="${allDsgn}">
					<c:if test="${DsgnObj.dsgnId == selectDsgn}">
						<c:set var="selected" value="${selectDsgn}" />
					</c:if>
					<hdiits:option value="${DsgnObj.dsgnId}"> ${DsgnObj.dsgnName} </hdiits:option>
				</c:forEach>
			</hdiits:select></td>
			<td><hdiits:caption captionid="HRMS.name" bundle="${awdlbl}" /></td>

			<td><hdiits:text name="empName" size="30" tabindex="6" /></td>
		</tr>
		
			<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="HRMS.retirintbtw" bundle="${awdlbl}"></hdiits:caption></td>
			<td class="fieldLabel" width="25%">
				<hdiits:dateTime name="retFromDate" validation="txt.isrequired"  mandatory="true" captionid="HRMS.retirefromdate" bundle="${awdlbl}" default="${resultValue.retFromDate}" maxvalue="31/12/9999"></hdiits:dateTime>
			</td>
			
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="HRMS.to" bundle="${awdlbl}"></hdiits:caption></td>
			<td class="fieldLabel" width="25%">
				<hdiits:dateTime name="retToDate" validation="txt.isrequired"  mandatory="true" captionid="HRMS.retiretodate" bundle="${awdlbl}" default="${resultValue.retToDate}" maxvalue="31/12/9999"></hdiits:dateTime>
			</td>
		
			</tr>
			
		
		<tr align="left">
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="HRMS.type" bundle="${awdlbl}"></hdiits:caption></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select name="retTypecmb" captionid="HRMS.type" bundle="${awdlbl}" validation="sel.isrequired" id="retTypecmb" mandatory="true"  >
					<hdiits:option value="Select"><fmt:message key="HRMS.select" /></hdiits:option>
					<c:forEach var="cmbType" items="${retCmbLst}">
		    					<option value="<c:out value="${cmbType.lookupName}"/>">
								<c:out value="${cmbType.lookupDesc}"/></option>						
					</c:forEach>
				</hdiits:select>

			</td>

	
		</tr>
		<tr align="center">
	<td colspan="6" align="center">
			<br/>
			

	<hdiits:button  name="go" captionid="HRMS.employeesearch" type="button" bundle="${awdlbl}" onclick="searchGo()" />
	<br>
			</td>
		</tr>
	<tr>
	<td colspan="8" align="center">
	<br/>	<br/>
	<b>
	<c:set var="i" value="0" /> <% int a=0; %>
		<display:table list="${resultValue.Employee}" id="row" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1" >
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		<display:column class="tablecelltext" title=" Select"
			headerClass="datatableheader" style="text-align: center" >
		<!-- 	<hdiits:radio id="check${i}" name="check" value="${row.empId}~${row.empfName} ${row.empmName} ${row.emplName}~
							    		${row.userId}~ ${row.userName}~${row.postId}~${row.postName}~
							    		${row.dsgnId}~${row.dsgnName}~${row.locId}~${row.locName}~
							    		${row.depId}~${row.depName}"  onclick="checkclick(this);" /> -->

		 	<hdiits:radio id="check${i}" name="check" value="${row.userId}"  onclick="checkclick(this);" />
							    		
							    		
		</display:column>
		
		<display:column class="tablecelltext"  	title=" Sr.No"	headerClass="datatableheader" value="<%=a=a+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" title=" Name"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.empfName} ${row.empmName} ${row.emplName}
			
		</display:column>
		
		
		<display:column class="tablecelltext" title="Designation"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
			${row.dsgnName} 
			
		</display:column>
		
		<display:column class="tablecelltext" title="Department"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
			${row.depName} 
			
		</display:column>
		
		<c:set var="i" value="${i+1}" />
		
		<display:footer media="html"></display:footer>		
		
	</display:table>
	</b>
	</td>
	</tr>
	
	</table>
	</div>


	<div>
	<table align="center">
		<tr>
			<td><br>
			</td>
		</tr>
<c:if test="${code == 'LE'}">
	<c:set var="submitoptn" value="HRMS.LeaveEncashment" />
	</c:if>	
	
	<c:if test="${code == 'GA'}">
		<c:set var="submitoptn" value="HRMS.Gratuity" />
	
	</c:if>	
	
	
	<c:if test="${code == 'RTA_RI'}">
		<c:set var="submitoptn" value="HRMS.Reimbursement" />
	
	</c:if>	
	
	<c:if test="${code == 'GI'}">
		<c:set var="submitoptn" value="HRMS.GroupInsurance" />
	
	</c:if>	
	
	

		<tr align="center">
			<td align="center">
			
			<!--<hdiits:button name="Submit" type="button"
				value="Submit" tabindex="11" onclick="submmitReq()" /> -->
				
		<hdiits:resetbutton title="Reset" value="Reset" name="Reset" type="button" tabindex="12" onclick="document.forms[0].subButton.disabled=true;"/>
					<script>
						  var resetButton='<fmt:message key="HRMS.reset"/>';
					      document.forms[0].Reset.value=resetButton;
				    </script>
		
		<hdiits:button type="button"  name="subButton"   id="subButton" captionid="${submitoptn}" onclick="submitReq()" bundle="${awdlbl}" readonly="true" />
			<hdiits:hidden caption ="userId" name="userId"  default="-1"/> 
			<hdiits:hidden caption ="code" name="code" default="${code}" /> 
			
				</td>
		</tr>
	</table>


	</div>
	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'controlNames="retFromDate,retToDate,retTypecmb" />


</hdiits:form>
<c:if test="${selectedDep ne ''}">
	<script>
									selectedCombo("${selectedDep}",document.forms[0].depId);
								</script>
</c:if>
<c:if test="${selectedLoc ne ''}">
	<script>
									selectedCombo("${selectedLoc}",document.forms[0].locId);
								</script>
</c:if>
<c:if test="${selected ne ''}">
	<script>
									selectedCombo("${selected}",document.forms[0].dsgnId);
								</script>
</c:if>




		
<c:if test="${resultValue.retType  ne ''}">
	<script>
			selectedCombo("${resultValue.retType}",document.forms[0].retTypecmb);
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
//alert(document.forms[0].userId.value);
			if(document.forms[0].userId.value=="" || document.forms[0].userId.value=="-1"){
			document.forms[0].subButton.disabled=true;
			}
			else{
			document.forms[0].subButton.disabled=false;
			}
			
	</script>

<%
	}catch(Exception e){
	e.printStackTrace();
}
%>
			