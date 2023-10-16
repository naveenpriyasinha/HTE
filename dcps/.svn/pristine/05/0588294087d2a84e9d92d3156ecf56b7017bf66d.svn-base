
<%
try{
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<fmt:setBundle basename="resources.hr.incharge.InchargeAlertMessage" var="alertLables"	scope="request" />
<fmt:setBundle basename="resources.hr.incharge.InchargeLabels" var="CapLabels"	scope="request" />
	
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


<fmt:setBundle basename="resources.ess.utils.search.search" var="comLable"
	scope="request" />

<script>
  	var mycars=new Array();

	var arrlength;

		
		
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
  		if(ctry == 'department')
  		{
   		
  			document.searchEmployee.action="./hdiits.htm?actionFlag=upList&userId=-1";
			document.searchEmployee.submit();
  		
  		}
  		
 		if(ctry == 'location')
  		{
   		
  			document.searchEmployee.action="./hdiits.htm?actionFlag=upList&userId=-1";
			document.searchEmployee.submit();
  		
  		}
 		if(ctry == 'post')
  		{
  		
	  		document.searchEmployee.action="./hdiits.htm?actionFlag=upList&userId=-1";
			document.searchEmployee.submit();
  		}
  }
  function searchGo()
  {
  			document.searchEmployee.action="./hdiits.htm?actionFlag=searchEmp&userId=-1";
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

for(var i=0; i<emparray.length; i++){

finalEmpArr[i] = document.getElementById(emparray[i]).value;


}

window.opener.showSearchInchg(finalEmpArr);
window.close();

}


function checkclick(form){

if(form.checked == true)
{
emparray[0]=form.id;
empcount=0;

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

function selectCountry()
	{
		 var formElementsLength = document.forms[0].elements.length;
		  
		 var noOfRowSelected = 0;
		 var selectedRowIndex;
		  
		 for(var iElement=0; iElement<formElementsLength; iElement++)
		 //	if(document.forms[0].elements[iElement].type == "checkbox")
		if(document.forms[0].elements[iElement].type == "radio") 
				if(document.forms[0].elements[iElement].checked == true)
				{
		 			noOfRowSelected= noOfRowSelected + 1;
		 			selectedRowIndex = iElement;
		 		}
			       
	  	
	 //     alert(document.forms[0].elements[selectedRowIndex].parentNode.parentNode.childNodes[2].childNodes[0].innerHTML);  
	  
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
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption captionid="IC.empSearch" bundle="${CapLabels}" captionLang="single"/>  </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0">
 
	<table align="center" >

		<tr>

			
			<td><hdiits:caption captionid="IC.department" bundle="${CapLabels}" /> 
			</td>
			<td><hdiits:select name="depId" size="1" caption="drop_down"
				onchange="OnChange(this,'department')" tabindex="2" sort="false">
				<hdiits:option value="0"><fmt:message key="IC.select" bundle="${CapLabels}"></fmt:message></hdiits:option>
				<c:set var="selectedDep" value="" />
				<c:forEach var="DepObj" items="${allDep}">
					<c:if test="${DepObj.depId == selectDep}">
						<c:set var="selectedDep" value="${selectDep}" />
					</c:if>
					<hdiits:option value="${DepObj.depId}">${DepObj.depName}  </hdiits:option>
				</c:forEach>

			</hdiits:select></td>
			<td><hdiits:caption captionid="IC.location" bundle="${CapLabels}" />
			</td>
			<td><hdiits:select name="locId" size="1"
				onchange="OnChange(this,'location')" tabindex="3" sort="false">

				<hdiits:option value="0"><fmt:message key="IC.select" bundle="${CapLabels}"></fmt:message></hdiits:option>
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
			<td><hdiits:caption captionid="IC.post" bundle="${CapLabels}" /></td>
			<td><hdiits:select name="dsgnId" size="1" caption="dsgnId"
				mandatory="false" onchange="OnChange(this,'post')" tabindex="4" sort="false">
				<hdiits:option value="0"><fmt:message key="IC.select" bundle="${CapLabels}"></fmt:message></hdiits:option>
				<c:set var="selected" value="" />
				<c:forEach var="DsgnObj" items="${allDsgn}">
					<c:if test="${DsgnObj.dsgnId == selectDsgn}">
						<c:set var="selected" value="${selectDsgn}" />
					</c:if>
					<hdiits:option value="${DsgnObj.dsgnId}"> ${DsgnObj.dsgnName} </hdiits:option>
				</c:forEach>
			</hdiits:select></td>
			<td><hdiits:caption captionid="IC.name" bundle="${CapLabels}" /></td>

			<td><hdiits:text name="empName" size="30" tabindex="6" /></td>
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
	
		
		<display:table list="${resultValue.Employee}" id="row" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1" >
		
		
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
		
		<display:column class="tablecelltext" title=" Select"
			headerClass="datatableheader" style="text-align: center" > 
			<hdiits:radio id="check${i}" name="check" value="${row.empId}~${row.empfName} ${row.empmName} ${row.emplName}~
							    		${row.userId}~ ${row.userName}~${row.postId}~${row.postName}~
							    		${row.dsgnId}~${row.dsgnName}~${row.locId}~${row.locName}~
							    		${row.depId}~${row.depName}"  onclick="checkclick(this);" />
		</display:column>
		
		<display:column class="tablecelltext"  	title=" Sr.No"	headerClass="datatableheader" value="<%=(a++) %>" style="text-align: center" sortable="true">
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
	
	</table>
	</div>


	<div>
	<table align="center">
		<tr>
			<td><br>
			</td>
		</tr>
		<tr align="center">
			<td align="center"><hdiits:button name="Submit" type="button" value="Submit" tabindex="11" onclick="submmitReq()" /> <hdiits:resetbutton
				name="Reset" type="button" value="Reset" tabindex="12" /></td>
		</tr>
	</table>


	</div>
	</div>


	
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />


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

<%
	}catch(Exception e){
	e.printStackTrace();
}
%>
