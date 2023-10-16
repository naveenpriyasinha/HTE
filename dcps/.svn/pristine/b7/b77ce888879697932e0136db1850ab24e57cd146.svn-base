<%try{ %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="../../../core/include.jsp"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
	  
	
    

	<c:set var="resultObj" value="${result}" > </c:set>	
	<c:set var="resultValue" value="${resultObj.resultValue}"> </c:set>
	<c:set var="allDep" value="${resultValue.Department}"> </c:set>
	<c:set var="allLoc" value="${resultValue.Locaton}"> </c:set>
	<c:set var="allDsgn" value="${resultValue.Disgnation}"> </c:set>

	<c:set var="selectLoc" value="${resultValue.selectLoc}"> </c:set>
	<c:set var="selectDep" value="${resultValue.selectDep}"> </c:set>
	<c:set var="selectDsgn" value="${resultValue.selectDsgn}"> </c:set>	
		
 
 <fmt:setBundle basename="resources.search.search" var="comLable" scope="request"/>	

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
  function abc()
  {
  alert(2);
	var temp="";
	var j=0;
	for(i=0;i<str.length;i++)
	{
		if(str.charCodeAt(i) == 10)
		{
			mycars[j]=temp;
			j++;
		}else
		{
			temp=temp+str.charAt(i);
		}

	}
	arrlength=j;
  }
  function searchName(constr)
  {
    alert(3);
  		var str2= searchEmployee.empName.value;
  		for(i=0;i<arrlength;i++)
  		{
  			mycars[i].search(constr);
  		}
  		
  }
  function tabFocus() 
  {
  
  	alert("focus on");
  	document.searchEmployee.focus();
  }
  function addName()
  {
	var newElem;
	var index=document.searchEmployee.emplist.selectedIndex;
    newElem = document.createElement("option");
    newElem.text = document.searchEmployee.emplist.options[index].text;
    newElem.value = document.searchEmployee.emplist.options[index].value;
  	var le=document.searchEmployee.newemplist.length;
	if(index!=0 && le==1)
	{  	
  		document.searchEmployee.newemplist.add(newElem);
  	}
  	
  }
 function removeName()
  {

	var index=document.searchEmployee.newemplist.selectedIndex;
	if(index!=0)
	{
	  	document.searchEmployee.newemplist.remove(index);
	  
	}
  }  
  </script>
  <script>
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

	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.hempId.value=arrStr[0];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.empName.value=arrStr[1];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.hempUserId.value=arrStr[2];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.empUser.value=arrStr[3];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.hempPostId.value=arrStr[4];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.empPost.value=arrStr[5];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.hempDsgnId.value=arrStr[6];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.empDsgn.value=arrStr[7];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.hempLocId.value=arrStr[8];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.empLoc.value=arrStr[9];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.hempDepId.value=arrStr[10];
	window.opener.parent.document.<%=""+session.getAttribute("frmname")%>.empDep.value=arrStr[11];
  }

								
</script>
	

<hdiits:form name="searchEmployee" id="searchEmployee" validate="true" method="POST"> 
<br>
<br>
<br>
	<div id="leave" name="leave" >  
  <table border="0" height="100%" width="100%">
  <tr>
  <td>
 	<div>

 	
 	<table align="center" >	
			
			<tr >
				<td><br></td>
				
				<td>
					<hdiits:caption captionid="search" bundle="${comLable}"/>
						<input type="radio" name="radiolist" value="preferred" >
						<hdiits:caption captionid="preferred" bundle="${comLable}"/>
						<input type="radio" name="radiolist" value="new" checked="checked" tabindex="1">
					<hdiits:caption captionid="new" bundle="${comLable}"/>
				</td>
				<td><br></td>
				<td><br></td>
				<td><br></td>
			</tr>

			<tr>
				<td>
						<hdiits:caption captionid="department" bundle="${comLable}"/>
				</td>
				<td>
						<hdiits:select name="depId" size="1"  caption="drop_down"  onchange="OnChange(this,'department')" tabindex="2">
								<hdiits:option value="0">-----Select-----</hdiits:option>
								<c:set var="selectedDep" value=""/>
								<c:forEach var="DepObj" items="${allDep}">
									<c:if test="${DepObj.depId == selectDep}">
							    	<c:set var="selectedDep" value="${selectDep}"/>	
							    	</c:if>											
							    		<hdiits:option   value= "${DepObj.depId}">${DepObj.depName}  </hdiits:option>
								</c:forEach>						

						</hdiits:select>
				</td>

				<td><br></td>
				<td><br></td>
				<td><br></td>
			</tr>
			<tr>
	
				<td>
						<hdiits:caption captionid="location" bundle="${comLable}"/>
				</td>
				<td>
						<hdiits:select name="locId" size="1"  onchange="OnChange(this,'location')" tabindex="3">

								<hdiits:option value="0">-----Select-----</hdiits:option>
								<c:set var="selectedLoc" value=""/>
								<c:forEach var="LocObj" items="${allLoc}">
									<c:if test="${LocObj.locId == selectLoc}">
							    	<c:set var="selectedLoc" value="${selectLoc}"/>	
							    	</c:if>											
							    		<hdiits:option 	value="${LocObj.locId}" > ${LocObj.locName} </hdiits:option>
								</c:forEach>
						</hdiits:select>
				</td>
				<td><br></td>
				<td><br></td>
				<td><br></td>
			</tr>			
			<tr>
				<td>
						<hdiits:caption captionid="post" bundle="${comLable}"/>
				</td>
				<td>
						<hdiits:select name="dsgnId" size="1" caption="dsgnId"  mandatory="false" onchange="OnChange(this,'post')" tabindex="4">
								<hdiits:option value="0">-----Select-----</hdiits:option>
								<c:set var="selected" value=""/>
								<c:forEach var="DsgnObj" items="${allDsgn}">
									<c:if test="${DsgnObj.dsgnId == selectDsgn}">
							    	<c:set var="selected" value="${selectDsgn}"/>	
							    	</c:if>											
							    		<hdiits:option 	value="${DsgnObj.dsgnId}" > ${DsgnObj.dsgnName} </hdiits:option>
								</c:forEach>
						</hdiits:select>
				</td>
				<td><br></td>
				<td>
						
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
						<hdiits:caption captionid="name" bundle="${comLable}"/>
				</td>
				<td>
						<hdiits:select name="nameselect" size="1"  tabindex="5" >
							<hdiits:option  value="Select">-----Select-----</hdiits:option>
							<hdiits:option  value="Contain">Contain</hdiits:option>
							<hdiits:option  value="Startwith">Start with</hdiits:option>
						</hdiits:select>
				</td>
				
				<td>
						<hdiits:text name="empName"  size="30" tabindex="6"/>
				</td>
				<td><hdiits:button name="go"  value="  Go  " type="button" tabindex="6" onclick="searchGo()"/></td>
				<td><br></td>
			</tr>			
			<tr>
				<td><hdiits:caption captionid="employee" bundle="${comLable}"/></td>
				<td><br></td>
				<td><hdiits:caption captionid="newsubpath" bundle="${comLable}"/></td>
				<td><br></td>
				<td>
				
				</td>
			</tr>
			<tr>
				<td>
						<c:set var="allEmp" value="${resultValue.Employee}"> </c:set>
						
						
						<hdiits:select name="emplist" size="7" tabindex="7">
							<hdiits:option value="0">----------------------------------------------</hdiits:option>
								<c:forEach var="EmpObj" items="${allEmp}">
														
							    		<hdiits:option 	value="${EmpObj.empId},${EmpObj.empfName} ${EmpObj.empmName} ${EmpObj.emplName},
							    		${EmpObj.userId}, ${EmpObj.userName},${EmpObj.postId}, ${EmpObj.postName},
							    		${EmpObj.dsgnId} ,${EmpObj.dsgnName},${EmpObj.locId}, ${EmpObj.locName},
							    		${EmpObj.depId}, ${EmpObj.depName}," >
							    		${EmpObj.empfName}<%=" "%>${EmpObj.empmName}<%=" "%>${EmpObj.emplName}</hdiits:option>
							    		
								</c:forEach>
						</hdiits:select>
						
					  
				</td>
				
				<td align="center">
					<br>
					<hdiits:button type="button" name="hdiits:select" value="  >  " tabindex="8" onclick="addName()"/>
					<br>
					<br>
					<hdiits:button type="button" name="remove" value="  <  "  tabindex="9" onclick="removeName()"/>
				</td>
								
				<td>
						<hdiits:select name="newemplist" size="7" tabindex="10">
							<hdiits:option value="0">----------------------------------------------</hdiits:option>
						</hdiits:select>
						

				</td>
			</tr>
			
		</table>
		</div>
		<div>
		<table align="center">	
			<tr><td><br></td></tr>
			<tr align="center">    
				<td align="center">

						 <hdiits:button name="Submit" type="button"  value="Submit" tabindex="11" onclick="windowclose()" />
						 <hdiits:resetbutton name="Reset" type="button" value="Reset" tabindex="12"/>
 					       
 						
 				</td>		
 			</tr>			
		</table> 	
		</div>
	</td>
	</tr>						
  </table>
  </div>



<jsp:include page="../common/include/tabnavigation.jsp"/>      
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>

						
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
