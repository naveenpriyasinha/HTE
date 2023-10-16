
<%
try {
%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>





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

<c:set var="hrDeputreqmtdistDtlObj"
	value="${resultValue.hrDeputreqmtdistDtlObj}">
</c:set>

<c:set var="hrDeputreqmtselnDtl"
	value="${resultValue.hrDeputreqmtselnDtl}">
</c:set>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="white"></c:set>
<c:set var="xmlFilePathNameForMulAdd" value="${resultValue.xmlFileName}" />

<c:set var="hdInvvisitPanchMpgSet" value="${resultValue.HrDeputEmpList}" />




<hdiits:form name="selectEmp" validate="true" method="POST"
	action="./hrms.htm?actionFlag=depCandidate">
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
	<div><script type="text/javascript">

function SearchEmp(){
		var href='./hrms.htm?actionFlag=allData';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}

var empArray = new Array();
var empuserid = new Array();
var dupempid = new Array();
var i=0;
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

									addDBDataInTable('deputEmpTable','depPunch',displayFieldA,xmlfilepath,'','deleteDBRecord');
				    			}
				   }			
	     						
	}				
}
function submitRecord()
{          
	
		document.selectEmp.action='./hrms.htm?actionFlag=depCandidate&DestId='+${hrDeputreqmtdistDtlObj.distributionId}+'&parentFileId='+${hrDeputreqmtDtl.deputationreqmtId}+'&FileId='+${hrDeputreqmtselnDtl.fileId};
		document.selectEmp.submit()	
}

</script> <hdiits:hidden name="BranchHeadPostID" id="BranchHeadPostID" /> 
<hdiits:hidden name="DestId" id="DestId" default="${hrDeputreqmtdistDtlObj.distributionId}" /> 
<hdiits:hidden name="parentFileId" id="parentFileId" default="${hrDeputreqmtDtl.deputationreqmtId}" /> 
<hdiits:hidden name="FileId" id="FileId" default="${hrDeputreqmtselnDtl.fileId}" />

	<hdiits:fieldGroup titleCaptionId="requirmentDetails" bundle="${comLable}"  collapseOnLoad="true">
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
			<hdiits:fieldGroup titleCaptionId="eligibilitycriteria" bundle="${comLable}"  collapseOnLoad="true">
			<table align="center" width="80%" border="1" id="criteria"  border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable">
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
			<td></td>
		</tr>
	</table>
</hdiits:fieldGroup>
<c:if test="${not empty resultValue.hrDepuDistList}">
<hdiits:fieldGroup titleCaptionId="broadcastdeputDetails" bundle="${comLable}"  collapseOnLoad="true">
	<table width="100%">
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="broadcastdeputDetails" bundle="${comLable}"></fmt:message>
 </u></strong> </font></td>
		</tr>-->
	</table>



	<c:set var="i" value="0" /> <%
 int a=0;
 %> <display:table
		list="${resultValue.hrDepuDistList}" id="row" requestURI=""
		  style="width:100%" offset="1">


		<display:setProperty name="paging.banner.placement" value="bottom" />

		<display:column class="tablecelltext" titleKey="SrNo"
			headerClass="tablerow" value="<%=a=a+1 %>"
			style="text-align: center" >
		</display:column>



		<display:column class="tablecelltext" titleKey="locationName"
			headerClass="tablerow" style="text-align: center"
			>
			
			${row.locName}
		</display:column>

		<display:column class="tablecelltext" titleKey="branchNm"
			headerClass="tablerow" style="text-align: center"
			>
			
			${row.branchName}
		</display:column>
		<display:column class="tablecelltext" titleKey="receiveBy"
			headerClass="tablerow" style="text-align: center"
			>
			
			${row.empName}
		</display:column>

		<display:column class="tablecelltext" titleKey="requestStatus"
			headerClass="tablerow" style="text-align: center">
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

		<display:footer media="html"></display:footer>

	</display:table>
	</hdiits:fieldGroup>
	</div>
	
</c:if>



<hdiits:fieldGroup titleCaptionId="employeeSearch" bundle="${comLable}"  collapseOnLoad="true">
<!-- 	<table width="100%" id="empSearchTbl">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>
				<fmt:message key="employeeSearch" bundle="${comLable}"></fmt:message>
 </u></strong> </font></td>
		</tr>

	</table>-->
	<input type="hidden" id="userId" name="userid">


	<table width="100%" id="deputEmpTable" name="deputEmpTable" border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" class="tabtable" >
		<tr style="background-color:${tdBGColor}">

		
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
	<c:forEach items="${hdInvvisitPanchMpgSet}"
		var="hdInvvisitPanchMpgTuples" varStatus="x">

		<c:set var="curXMLFileName"
			value="${xmlFilePathNameForMulAdd[x.index]}"></c:set>

		<c:set var="userId" value="${hdInvvisitPanchMpgTuples.userId}"></c:set>

		<c:set var="employeeName" value="${hdInvvisitPanchMpgTuples.empfName}"></c:set>
		<c:set var="post" value="${hdInvvisitPanchMpgTuples.postName}"></c:set>
		<c:set var="currentLocation"
			value="${hdInvvisitPanchMpgTuples.locName}"></c:set>
		<c:set var="dateOfBirth" value="${hdInvvisitPanchMpgTuples.dateB}"></c:set>
		<fmt:formatDate var="dateOfBirth"
			value="${hdInvvisitPanchMpgTuples.dateB}" pattern="dd/MM/yyyy" />
		<c:set var="dateOfJoin" value="${hdInvvisitPanchMpgTuples.datej}"></c:set>
		<fmt:formatDate var="dateOfJoin"
			value="${hdInvvisitPanchMpgTuples.datej}" pattern="dd/MM/yyyy" />
		<c:set var="dateOfRetire" value="${hdInvvisitPanchMpgTuples.dateR}"></c:set>
		<fmt:formatDate var="dateOfRetire"
			value="${hdInvvisitPanchMpgTuples.dateR}" pattern="dd/MM/yyyy" />
		<script type="text/javascript">
										var xmlFileName = '${curXMLFileName}';

 									dupempid[i]=${userId};
 									i++;
							var displayFieldA  = new Array('${employeeName}','${post}','${currentLocation}','${dateOfBirth}','${dateOfJoin}','${dateOfRetire}');

							addDBDataInTable('deputEmpTable','addedPunch',displayFieldA,xmlFileName,'', '');
							</script>
	</c:forEach>

	

</hdiits:form>



<hdiits:validate controlNames="text"
	locale='<%=(String)session.getAttribute("locale")%>' />



<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
