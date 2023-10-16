
<%
try {
%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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
<c:set var="hrDeputreqmtdistDtlObj"	value="${resultValue.hrDeputreqmtdistDtlObj}"></c:set>
<c:set var="hrDeputdistIdSFNMObj" value="${resultValue.hrDeputdistIdSFNMObj}"></c:set>
<c:set var="hrDeputreqmtselnDtl" value="${resultValue.hrDeputreqmtselnDtl}"></c:set>
<c:set var="hrDeputdistIdObj" value="${resultValue.hrDeputdistIdObj}"></c:set>
<c:set var="xmlFilePathNameForMulAdd" value="${resultValue.xmlFileName}" />
<c:set var="childFileId" value="${resultValue.fileId}"></c:set>
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
<c:set var="hdInvvisitPanchMpgSet" value="${resultValue.HrDeputEmpList}" />

<hdiits:form name="selectEmp" validate="true" method="POST" action="./hrms.htm?actionFlag=depCandidate">
<div>

<script type="text/javascript">

var emplst = new Array();

var empcount = 0 ;

function checkclick(form){

	if(form.checked == true)
	{

	
		emplst[empcount]=form.id;
		empcount++;
		
		
	}
	else{
	for(var i=0; i<emplst.length; i++)
  	{  
		
  
 		 if(emplst[i]== form.id){
  			emplst.splice(i,1);
  			
		    empcount--;
  
  }
	}
}
document.getElementById('emplst').value=emplst;
}
function validCheckBOx()
{
			if(emplst.length==0)
			{	
				alert("You must select atleast one CheckBox");
				return false;
			}
			  
		   	return true;
			 
	
		

}
</script>
<script type="text/javascript">

function SearchEmp(){
		var href='./hrms.htm?actionFlag=allData';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}

var empArray = new Array();
var empuserid = new Array();
var dupempid = new Array();
var i=0;
var j=100;
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
					   
				    				    	
				    	var radBox=new Array();
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
				    	 					
				    
										    	 
					    	 		var radiotest="<input type='radio' name='"+userid+"' id='"+userid+"' value='yes' caption='Yes' onclick='radiofunction(this);'/>" +  "Y"  + "<input type='radio' name='"+userid+"' id='"+userid+"' value='no' caption='No' checked='checked' onclick='radiofunction(this);'/>" + "N";
										    									
									
				    	 			var displayFieldA  = new Array(name,post,loc,dateb,datej,dater,radiotest);
																		    	
									addDBDataInTable('deputEmpTable','depPunch',displayFieldA,xmlfilepath,'','deleteDBRecord');
																			    	
				    			}
				   }			
	     						
	}				
}
var EmpOpYes =new Array();
var EmpOpNO = new Array();
var EmpOpYescount=0;
var EmpOpNocount=0;
function radiofunction(form){
	
		if(form.value=='yes')
		{
			
		
			EmpOpYes[EmpOpYescount]=form.id;
			EmpOpYescount++;
			for(var i=0; i<EmpOpNO.length; i++)
  			{  
 				 if(EmpOpNO[i]== form.id){
  				EmpOpNO.splice(i,1);
  				EmpOpNocount--;
 			 }
 		 }
		
		}
		else
		{
	
		for(var i=0; i<EmpOpYes.length; i++)
  		{  
 		 if(EmpOpYes[i]== form.id){
  			EmpOpYes.splice(i,1);
  			EmpOpYescount--;
 		 }
 		 }
		EmpOpNO[EmpOpNocount]=form.id;
		EmpOpNocount++;
		
	
		}
	
	
document.getElementById('EmpopinoinYes').value=EmpOpYes;
document.getElementById('EmpopinoinNo').value=EmpOpNO;
}


var selfNmemplst = new Array();

var selfNmcount = 0 ;

function checkSelfNm(form){

	if(form.checked == true)
	{

		
		selfNmemplst[selfNmcount]=form.id;
		selfNmcount++;
		
		
	}
	else{
	for(var i=0; i<selfNmemplst.length; i++)
  	{  
		
  
 		 if(selfNmemplst[i]== form.id){
  			selfNmemplst.splice(i,1);
  			
		    selfNmcount--;
  
  }
	}
}
document.getElementById('selfNmemplst').value=selfNmemplst;
}
function pagevalidattion()
{
	
return true;
												
}

</script>

<hdiits:hidden name="distributionId" id="distributionId" default="${hrDeputdistIdObj.distributionId}" />
<hdiits:hidden name="DestId" id="DestId" default="${hrDeputreqmtdistDtlObj.distributionId}" />
<hdiits:hidden name="parentFileId" id="parentFileId" default="${hrDeputreqmtDtl.deputationreqmtId}" />
<hdiits:hidden name="FileId" id="FileId" default="${hrDeputreqmtselnDtl.fileId}" />
<hdiits:hidden name="childFileId" id="childFileId" 	default="${childFileId}" />
<hdiits:hidden name="distributionIdSF" id="distributionIdSF" default="${hrDeputdistIdSFNMObj.distributionId}" />
<hdiits:hidden name="emplst" id="emplst"/>
<hdiits:hidden name="selfNmemplst" id="selfNmemplst"/>
<hdiits:hidden name="EmpopinoinYes" id="EmpopinoinYes"/>
<hdiits:hidden name="EmpopinoinNo" id="EmpopinoinNo"/>
<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />

<hdiits:fieldGroup titleCaptionId="requirmentDetails" bundle="${comLable}" id="requirmentDetailId" collapseOnLoad="false">
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
	<hdiits:fieldGroup titleCaptionId="eligibilitycriteria" bundle="${comLable}"  collapseOnLoad="true">
	<table width="100%" id="empSearchTbl" >
		
		<tr>
			<td width="25%" align="center"><b><hdiits:caption
				captionid="name" bundle="${comLable}" /></b></td>
			<td width="25%"><hdiits:text name="searchName" id="searchName" /></td>
			<td><hdiits:button name="search" captionid="search"
				bundle="${comLable}" type="button" onclick="SearchEmp();" /></td>
		</tr>
		
	
			</table>
			</hdiits:fieldGroup>
			
			<input type="hidden" id="userId" name="userid">

<hdiits:fieldGroup titleCaptionId="empSearch" bundle="${comLable}"  collapseOnLoad="true">
			<table width="100%" id="deputEmpTable" name="deputEmpTable"
				border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable" >
				<tr style="background-color:${tdBGColor}">

					
					<td width="12%"><hdiits:caption captionid="employeeName"
						bundle="${comLable}" /></td>
					<td width="10%"><hdiits:caption captionid="post"
						bundle="${comLable}" /></td>
					<td width="13%"><hdiits:caption captionid="currentLocation"
						bundle="${comLable}" /></td>
					<td width="10%"><hdiits:caption captionid="dateOfBirth"
						bundle="${comLable}" /></td>
					<td width="10%"><hdiits:caption captionid="dateOfJoin"
						bundle="${comLable}" /></td>
					<td width="12%"><hdiits:caption captionid="dateOfRetire"
						bundle="${comLable}" /></td>
												
					<td width="28%"><hdiits:caption captionid="whetherempoptionreq"
						bundle="${comLable}" /></td>
					<td width="5%"><hdiits:caption captionid="action"
						bundle="${comLable}" /></td>
					
					
				</tr>
			
			</table>
			</hdiits:fieldGroup>
							<c:forEach items="${hdInvvisitPanchMpgSet}"
								var="hdInvvisitPanchMpgTuples" varStatus="x">

								<c:set var="curXMLFileName"
									value="${xmlFilePathNameForMulAdd[x.index]}"></c:set>
								
								<c:set var="userId"
									value="${hdInvvisitPanchMpgTuples.userId}"></c:set>
								
								<c:set var="employeeName"
									value="${hdInvvisitPanchMpgTuples.empfName}"></c:set>
								<c:set var="post"
									value="${hdInvvisitPanchMpgTuples.postName}"></c:set>
								<c:set var="currentLocation"
									value="${hdInvvisitPanchMpgTuples.locName}"></c:set>
								<c:set var="dateOfBirth"
									value="${hdInvvisitPanchMpgTuples.dateB}"></c:set>
								<fmt:formatDate var="dateOfBirth" value="${hdInvvisitPanchMpgTuples.dateB}" pattern="dd/MM/yyyy"/>
									<c:set var="dateOfJoin"
									value="${hdInvvisitPanchMpgTuples.datej}"></c:set>
								<fmt:formatDate var="dateOfJoin" value="${hdInvvisitPanchMpgTuples.datej}" pattern="dd/MM/yyyy"/>
									<c:set var="dateOfRetire"
									value="${hdInvvisitPanchMpgTuples.dateR}"></c:set>
									<fmt:formatDate var="dateOfRetire" value="${hdInvvisitPanchMpgTuples.dateR}" pattern="dd/MM/yyyy"/>
						
									<c:set var="empOpinoin"
									value="${hdInvvisitPanchMpgTuples.empOpinoin}"></c:set>
								<script type="text/javascript">
									var xmlFileName = '${curXMLFileName}';
									
										var radiotest="";
										if('${empOpinoin}'=="Y")
										{
											
											radiotest="<input type='radio' name='"+${userId}+"' id='"+${userId}+"' value='yes' caption='Yes' checked='checked' onclick='radiofunction(this);'/>" +  "Y"  + "<input type='radio' name='"+${userId}+"' id='"+${userId}+"' value='no' caption='No' onclick='radiofunction(this);'/>" + "N";
										}else if('${empOpinoin}'=="N"){
											
											radiotest="<input type='radio' name='"+${userId}+"' id='"+${userId}+"' value='yes' caption='Yes'  onclick='radiofunction(this);'/>" +  "Y"  + "<input type='radio' name='"+${userId}+"' id='"+${userId}+"' value='no' caption='No' checked='checked' onclick='radiofunction(this);'/>" + "N";
										}
										else if('${empOpinoin}'=="Agree")
										{
											radiotest = '${empOpinoin}';
											
										}
										else if('${empOpinoin}'=="DisAgree")
										{
											radiotest = '${empOpinoin}';
										}
										else
										{
											radiotest="<input type='radio' name='"+${userId}+"' id='"+${userId}+"' value='yes' caption='Yes'  onclick='radiofunction(this);'/>" +  "Y"  + "<input type='radio' name='"+${userId}+"' id='"+${userId}+"' value='no' caption='No' onclick='radiofunction(this);'/>" + "N";
										}

 									dupempid[i]=${userId};
 									i++;
							var displayFieldA  = new Array('${employeeName}','${post}','${currentLocation}','${dateOfBirth}','${dateOfJoin}','${dateOfRetire}',radiotest);
						
							addDBDataInTable('deputEmpTable','addedPunch',displayFieldA,xmlFileName,'', 'deleteDBRecord');
							</script>
							</c:forEach>
			
			<c:if test="${not empty resultValue.selfnominatedLst }">	
			<hdiits:fieldGroup titleCaptionId="Dep.selfNominatedEmp" bundle="${comLable}"  collapseOnLoad="true">

	
		<c:set var="k" value="0" /> <% int c=0; %>
	<input type="hidden" id="userId" name="userid">
	
		<display:table list="${resultValue.selfnominatedLst}"  id="row" requestURI=""  export="false" style="width:100%" offset="1" >
		

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column class="tablecell"  	titleKey="SrNo" 	headerClass="tablerow" value="<%=c=c+1 %>" style="text-align: center" >
		</display:column>
		<display:column class="tablecell" titleKey="Dep.select"
			headerClass="tablerow" style="text-align: center" >
			<hdiits:checkbox id="${row.userId}" name="check1" value="${row.userId}"  
			onclick="checkSelfNm(this);"/>
			</display:column>
		<display:column class="tablecell" titleKey="employeeName"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.empfName}
		</display:column>
		
			
		
		<display:column class="tablecell" titleKey="post"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.postName}
		</display:column>
	
			<display:column class="tablecell" titleKey="currentLocation"
			headerClass="tablerow" style="text-align: center"
			>
			
			${row.locName}
		</display:column>
	
			<display:column class="tablecell" titleKey="dateOfBirth"
			headerClass="tablerow" style="text-align: center"
			  >
			<fmt:formatDate value="${row.dateB}" pattern="dd/MM/yyyy"/>

		</display:column>

			<display:column class="tablecell" titleKey="dateOfJoin"
			headerClass="tablerow" style="text-align: center"
			 >
						<fmt:formatDate value="${row.datej}" pattern="dd/MM/yyyy"/>
			
		</display:column>
					<display:column class="tablecell" titleKey="dateOfRetire"
			headerClass="tablerow" style="text-align: center"
			 >
						<fmt:formatDate value="${row.dateR}" pattern="dd/MM/yyyy"/>
			
		</display:column>
				<display:column class="tablecell" titleKey="locationName"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.locationName}
		</display:column>
		
		<c:set var="k" value="${k+1}" />
		
	</display:table>
	</hdiits:fieldGroup>
			</c:if>
			
	
	
			<c:if test="${not empty resultValue.hrDepuDistList }">	
			<hdiits:fieldGroup titleCaptionId="broadcastdeputDetails" bundle="${comLable}"  collapseOnLoad="true">
			<c:set var="i" value="0" /> <% int a=0; %>
		
		<display:table list="${resultValue.hrDepuDistList}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
		
		
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
		<display:column class="tablecell"  	titleKey="SrNo" 	headerClass="tablerow" value="<%=a=a+1 %>" style="text-align: center" >
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
			<c:if test="${row.statusFlage eq \"PENDING\"}">
				<font color="red">
				<fmt:message key="Dep.pending" bundle="${comLable}"></fmt:message>  </font>
			</c:if>
			<c:if test="${row.statusFlage eq \"ACTIVE\"}">
				<font color="red">
				<fmt:message key="Dep.active" bundle="${comLable}"></fmt:message>  </font>
			</c:if>
			<c:if test="${row.statusFlage eq \"COMPLETED\"}">
				<font color="red">
				<fmt:message key="Dep.completed" bundle="${comLable}"></fmt:message>  </font>
			</c:if>
			
			
			
		
		</display:column>
		
		<c:set var="i" value="${i+1}" />
		
	
		
	</display:table>
	</hdiits:fieldGroup>
	</c:if>
			<table align="center" width="30%">
								<tr><td>
									<hdiits:jsField jsFunction="pagevalidattion()" name="validate"/>
	       						</td>
	       						</tr>
								<tr>
								</tr>
							</table>
		<c:if test="${not empty resultValue.approveChildEmp }">
		<hdiits:fieldGroup titleCaptionId="listOfEmpReceive" bundle="${comLable}"  collapseOnLoad="true">
				
		<c:set var="j" value="0" /> <% int b=0; %>
	<input type="hidden" id="userId" name="userid">
	
		<display:table list="${resultValue.approveChildEmp}"  id="row" requestURI=""  export="false" style="width:100%" offset="1" >
		

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column class="tablecell"  	titleKey="SrNo" 	headerClass="tablerow" value="<%=b=b+1 %>" style="text-align: center" >
		</display:column>

			<display:column class="tablecell" titleKey="Dep.select"
			headerClass="tablerow" style="text-align: center" >
			<hdiits:checkbox id="${row.userId}" name="check" value="${row.userId}"  
			onclick="checkclick(this);" />
			
		</display:column>
		
	
		<display:column class="tablecell" titleKey="employeeName"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.empfName}
		</display:column>
		
		<display:column class="tablecell" titleKey="post"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.postName}
		</display:column>
	
			<display:column class="tablecell" titleKey="currentLocation"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.locName}
		</display:column>
	
			<display:column class="tablecell" titleKey="dateOfBirth"
			headerClass="tablerow" style="text-align: center"
			 >
			<fmt:formatDate value="${row.dateB}" pattern="dd/MM/yyyy"/>

		</display:column>

			<display:column class="tablecell" titleKey="dateOfJoin"
			headerClass="tablerow" style="text-align: center"
			>
						<fmt:formatDate value="${row.datej}" pattern="dd/MM/yyyy"/>
			
		</display:column>
					<display:column class="tablecell" titleKey="dateOfRetire"
			headerClass="tablerow" style="text-align: center"
			 >
						<fmt:formatDate value="${row.dateR}" pattern="dd/MM/yyyy"/>
			
		</display:column>
				<display:column class="tablecell" titleKey="locationName"
			headerClass="tablerow" style="text-align: center"
			 >
			
			${row.locationName}
		</display:column>
		
		<c:set var="j" value="${j+1}" />
		
	</display:table>
			</hdiits:fieldGroup>
			
		</c:if>		
			
			
				
	</div>			





<jsp:include page="../../../core/tabnavigation.jsp" />



<hdiits:validate controlNames="text"
	locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
