
<%
try {
%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>


<script type="text/javascript" src="common/script/tabcontent.js"></script>


<link rel="stylesheet"
	href="<c:url value="/common/css/tabcontent.css" />" />

<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"
	scope="request" />
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="hrDeputreqmtDtl" value="${resultValue.hrDeputreqmtDtl}">
</c:set>
<c:set var="cmnLocationMst" value="${resultValue.cmnLocationMst}">
</c:set>
<c:set var="orgGradeMst" value="${resultValue.orgGradeMst}">
</c:set>
<c:set var="orgPostDetailsRlt" value="${resultValue.orgPostDetailsRlt}">
</c:set>
<c:set var="orgPostMst" value="${resultValue.orgPostMst}">
</c:set>
<c:set var="setOfEligiblity" value="${resultValue.setOfEligiblity}">
</c:set>

<c:set var="locations" value="${resultValue.locations}">
</c:set>
<c:set var="hrDeputreqmtdistDtlObj"
	value="${resultValue.hrDeputreqmtdistDtlObj}">
</c:set>

<c:set var="xmlFilePathNameForMulAdd"
	value="${resultValue.xmlFilePathNameForMulAdd}" />

<c:set var="hdInvvisitPanchMpgSet" value="${resultValue.hrDepuDistList}" />

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>

<style type="text/css">

/* All form elements are within the definition list for this example */
.tablecell{
   border-bottom: solid 1px #DEDEDE;
	border:1;
	border-color:black;
	font-size: 11px;
	font-style: normal;
	font-weight: normal;
	background: white;
	text-align: left;
	padding: 1px;
	
}
.tablerow{
  border-top: solid 1px #333333;
	border-left: solid 1px #666666;
	border-right: solid 0px #888888;
	border-bottom: solid 1px #666666; 
	font-size: 11px;
	font-style: normal;
	font-weight: bold;
	text-align: center;
	background: #C9DFFF;
	color: black;
	padding: 1px;
	
	
}
</style>

<hdiits:form name="initAPP" validate="true" method="POST">

	<script type="text/javascript">

var sendUserId=new Array();
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
	document.getElementById('buttonTbl').style.display='';
	document.getElementById('buttonTbl1').style.display='none';
	
	resetLocation();
}
function deleteRec(rowId)
{
	var trow=document.getElementById(rowId);
    trow.parentNode.deleteRow(trow.rowIndex);
	deleteFromDuplicateArray('addDistrict',rowId);
	
}
function returndata()
{
					document.initAPP.action='./hrms.htm?actionFlag=receiveBroadCast';
		  	 		document.initAPP.submit()
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
function locationValidate() 
{
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


function SearchEmp(){
	var href='./hrms.htm?actionFlag=allData';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}

var empArray = new Array();
var empuserid = new Array();
var dupempid = new Array();

function empSearch(from){

		 	for(var i=0; i<from.length; i++){
				empArray = from[i].split("~"); 
				empuserid[i]=empArray[2];
 			
 									
				}

				
  
  			for(var k=0;k<empuserid.length;k++){
	
				for(var j=0;j<dupempid.length;j++){
			
					if(empuserid[k]==dupempid[j]){
				
							empuserid.splice(k,1);
				
					}
		
				}
		
		   }

     
     		for(var i=0; i<empuserid.length;i++){
 	       			
 	       			dupempid[dupempid.length + 1] = empuserid[i];
         	}
    getListOfEmp();

}



function getListOfEmp(){
	
		 try{   
    	
    			xmlHttp=new XMLHttpRequest();    
	    
	    	}catch (e){
			    // Internet Explorer    
					try{
					
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
      				
      				}catch (e){
		          		
		          		try{
		          		
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
       		  			
       		  			}catch (e){
       		  			
			           	   	  alert('not supported');        
			            	  return false;        
			      		}
			 		}			 
        	}	
        	 
       	var userId = document.getElementById('userId').value;
		
			if(empuserid != ""){
			
					//alert(empuserid);
					var url = "hrms.htm?actionFlag=deputEmpDetails&empArray="+empuserid;  
					
			}
			
			for(var b=0;b<empuserid.length;b++){
			
					empuserid.splice(b,1);
			
			}  

			
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = processResponseforEmpTrans;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
 		
 	 
}	




function processResponseforEmpTrans(){

	if (xmlHttp.readyState == 4){
	     
				if (xmlHttp.status == 200){    
				    	
				    	var decXML = xmlHttp.responseText;

						var xmlDOM = getDOMFromXML(decXML);

					 	var xmlFilePath = xmlDOM.getElementsByTagName('xmlfile');

						
						var empUserId = xmlDOM.getElementsByTagName('userId');	
   						var empName =xmlDOM.getElementsByTagName('empName');
   						var empPost = xmlDOM.getElementsByTagName('empPostName');
   						var empLoc = xmlDOM.getElementsByTagName('location');
   						var empDateR = xmlDOM.getElementsByTagName('dater');
   						var empDateJ = xmlDOM.getElementsByTagName('datej');
   						var empDateB = xmlDOM.getElementsByTagName('dateb');
					   
				    				    	
				    	
				    	var j=0,k=1;
				    			for ( var i = 0 ; i < empName.length ; i++ ){
				    	   
				    	 					var xmlfilepath = xmlFilePath[i].childNodes[0].text;
				    	 					var userid = empUserId[i].childNodes[0].text;
				    	 					var name = empName[i].childNodes[0].text;
				    	 					var post = empPost[i].childNodes[0].text;
				    	 					var loc  = empLoc[i].childNodes[0].text;
				    	 					var dateb = empDateB[i].childNodes[0].text;
				    	 					var datej = empDateJ[i].childNodes[0].text;
				    	 					var dater = empDateR[i].childNodes[0].text;
				    	 					
				    	 					//alert('Name::'+name+' '+'Date of Birth::'+dateb);
				    	 					
				    	 /*					trow= document.getElementById('deputEmpTable').insertRow();
				    	 
									    	trow.insertCell(j).innerHTML = '<hdiits:checkbox id="'+userid+'" name="userid" value="'+userid+'" caption="'+userid+'" onclick="checkclick(this)" />';
				   							trow.insertCell(j+1).innerHTML=name;
				   							trow.insertCell(j+2).innerHTML=post;
				   							
	   										trow.insertCell(j+3).innerHTML=loc;
						   					trow.insertCell(j+4).innerHTML=dateb;	   					
					    					trow.insertCell(j+5).innerHTML=datej;	   					
						   					trow.insertCell(j+6).innerHTML=dater;
	   										
	   										j=0;
	   										sendUserId[i]=userid
				    	 */
				    	 
				    	 			var displayFieldA  = new Array(userid,name,post,loc,dateb,datej,dater);

									addDBDataInTable('deputEmpTable','depPunch',displayFieldA,xmlfilepath,'','','');

				    				document.getElementById('buttonTbl').style.display='';
				    				document.getElementById('buttonTbl1').style.display='none';
				    			}
				   }			
	     						
	}				
}
function submitRecord()
{          
			
 		var rows=document.getElementById('addDistrict').rows;	
		 var depemp=document.getElementById('deputEmpTable').rows;	
		if(rows.length>1 || depemp.length>1)
		{		
				document.initAPP.action='./hrms.htm?actionFlag=createFileForEmp&DestId='+${hrDeputreqmtdistDtlObj.distributionId}+'&parentFileId='+${hrDeputreqmtDtl.deputationreqmtId};
				document.initAPP.submit()	
		}else{
			alert("you must select atleast One Record")
		}
	
			
			
		
		    
}

function showBroadCastTable()
{


	document.getElementById('broadcastdistTbl').style.display='';
	document.getElementById('empSearchTbl').style.display='none';
	document.getElementById('deputEmpTable').style.display='none';
	

}
function searchTable()
{
	document.getElementById('empSearchTbl').style.display='';
	document.getElementById('deputEmpTable').style.display='';
	
		document.getElementById('broadcastdistTbl').style.display='none';
		
	
		
}




</script>




	<hdiits:hidden name="BranchHeadPostID" id="BranchHeadPostID" />

	<hdiits:hidden name="DistReqId" id="DistReqId"
		default="${hrDeputreqmtdistDtlObj.distributionId}" />
	<hdiits:hidden name="fileId"
		default="${hrDeputreqmtDtl.deputationreqmtId}" />
	<hdiits:fieldGroup titleCaptionId="requirmentDetails" bundle="${comLable}" id="requirmentId" collapseOnLoad="false">
	<table align="center" width="100%">
	<!-- 	<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="requirmentDetails" bundle="${comLable}"></fmt:message>
</u></strong> </font></td>
		</tr>-->

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
		<tr>
			<td>
				<hdiits:fieldGroup titleCaptionId="eligibilitycriteria" bundle="${comLable}" id="eligibilityId" collapseOnLoad="false">
			<table align="center" width="80%" id="criteria" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
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
			
			</td>
		</tr>
	</table>
	</hdiits:fieldGroup>
<table align="center" width="20%">
				<tr>

					<td align="right" colspan="50"><hdiits:button
						name="broadcastB1" captionid="broadcastB" bundle="${comLable}"
						type="button" onclick="showBroadCastTable();" /></td>

					<td colspan="50"><hdiits:button name="searchEmp"
						captionid="searchEmp" bundle="${comLable}" type="button"
						onclick="searchTable();" /></td>

				</tr>


			</table>
		
	<table id="broadcastdistTbl" align="center" width="100%"
		style="display: none;">
		<!--  <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="broadcastdeputDetails" bundle="${comLable}"></fmt:message></u></strong> </font></td>
		</tr> -->

		<tr>
			<td>
			<hdiits:fieldGroup titleCaptionId="broadcastdeputDetails" bundle="${comLable}"  collapseOnLoad="true">
			<Table width="100%" align="center">
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

					<td align="center" colspan="50" width="10%"><hdiits:button
						name="addB" captionid="addB" bundle="${comLable}" type="button"
						onclick="addRowUsingAjax()" tabindex="3" /></td>

				</tr>
			</table>
			</td>
		</tr>
</table>

		<tr>
			<td>

			<table id="addDistrict" align="center" width="60%"
				style="display: none;" border="1" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
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
</hdiits:fieldGroup>
	</table>


	<table width="100%" id="empSearchTbl" style="display: none;">
	<tr>
	<td>
	<hdiits:fieldGroup titleCaptionId="employeeSearch" bundle="${comLable}"  collapseOnLoad="true">
	<table width="100%">
	<tr>
	<td>
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="employeeSearch" bundle="${comLable}"></fmt:message>
				 </u></strong> </font></td>
		</tr> -->
		<tr>
			<td width="25%" align="center"><b><hdiits:caption
				captionid="name" bundle="${comLable}" /></b></td>
			<td width="25%"><hdiits:text name="searchName" id="searchName" /></td>
			<td><hdiits:button name="search" captionid="search"
				bundle="${comLable}" type="button" onclick="SearchEmp();" /></td>
		</tr>
	</table>
	<input type="hidden" id="userId" name="userid">


	<table width="100%" id="deputEmpTable" name="deputEmpTable" 
		style="display: none;" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
		<tr style="background-color:${tdBGColor}">

			<td width="10%"><hdiits:caption captionid="userId"
				bundle="${comLable}" /></td>
			<td width="10%"><hdiits:caption captionid="employeeName"
				bundle="${comLable}" /></td>
			<td width="10%"><hdiits:caption captionid="post"
				bundle="${comLable}" /></td>
			<td width="15%"><hdiits:caption captionid="currentLocation"
				bundle="${comLable}" /></td>
			<td width="15%"><hdiits:caption captionid="dateOfBirth"
				bundle="${comLable}" /></td>
			<td width="10%"><hdiits:caption captionid="dateOfJoin"
				bundle="${comLable}" /></td>
			<td width="10%"><hdiits:caption captionid="dateOfRetire"
				bundle="${comLable}" /></td>

		</tr>

	</table>
	</hdiits:fieldGroup>
	</td>
	</tr>
	
	</table>
	
	<table align="center" width="100%" id="buttonTbl" style="display: none;">
		<tr>

			<td align="right" colspan="50"><hdiits:button name="submitB"
				captionid="submitB" bundle="${comLable}" type="button"
				onclick="submitRecord();" /></td>
			<td colspan="50"><hdiits:button name="closeB" captionid="closeB"
				bundle="${comLable}" type="button" onclick="returndata();" /></td>

		</tr>


	</table>
	
	<table align="center" width="100%" id="buttonTbl1" >
		<tr>

		
			<td colspan="50" align="center"><hdiits:button name="closeB1" captionid="closeB"
				bundle="${comLable}" type="button" onclick="returndata();" /></td>

		</tr>


	</table>

	<c:if test="${not empty resultValue.hrDepuDistList}">
		<table align="center" width="100%">

			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font
					color="#ffffff"> <strong><u>
					<fmt:message key="broadcastdeputDetails" bundle="${comLable}"></fmt:message>
						 </u></strong> </font></td>
			</tr>
		</table>


		<c:set var="i" value="0" />
		<%
		int a=0;
		%>
		<display:table list="${resultValue.hrDepuDistList}" id="row"
			requestURI="" pagesize="10" style="width:100%" offset="1">


			<display:setProperty name="paging.banner.placement" value="bottom" />

			<display:column class="tablecell" titleKey="SrNo" 
				headerClass="tablerow" value="<%=a=a+1 %>"
				style="text-align: center" >
			</display:column>



			<display:column class="tablecell" titleKey="locationName"
				headerClass="tablerow" style="text-align: center"
				>
			
			${row.locName}
		</display:column>

			<display:column class="tablecell" titleKey="branchNm"
				headerClass="tablerow" style="text-align: center"
				>
			
			${row.branchName}
		</display:column>
			<display:column class="tablecell" titleKey="receiveBy"
				headerClass="tablerow" style="text-align: center"
				>
			
			${row.empName}
		</display:column>

			<display:column class="tablecell" titleKey="requestStatus"
				headerClass="tablerow" style="text-align: center"
				>
				<font color="red"> ${row.statusFlage}</font>
			</display:column>

			<c:set var="i" value="${i+1}" />

			<display:footer media="html"></display:footer>

		</display:table>
	</c:if>



</hdiits:form>





<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
