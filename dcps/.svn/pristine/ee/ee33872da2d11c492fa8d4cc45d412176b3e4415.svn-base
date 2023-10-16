   <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
	<script type="text/javascript" src="script/leave/DateVal.js"></script>
	<script type="text/javascript" src="script/common/calendar.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="common/script/tabcontent.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
	<script type="text/javascript" src="script/leave/DateDifference.js"></script>
	<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
	<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.groupInsurance.groupInsurance" var="constants" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="userId" value="${resValue.userId}"/>
<c:set var="EmpDetVO" value="${resValue.EmpDet}"/>
<c:set var="groupList" value="${resValue.GroupList}"/>
<c:set var="currentDate" value="${resValue.currentDate}" />

		
		<script language="javascript"><!--
		var fromDateStr;
		var toDateStr;
		var statusForSubmit=0;

		var	GroupArr = new  Array();
		var mulFact = new Array();

function changeColor(dateComponent)
{
	dateComponent.style.backgroundColor='white';
}

function rowReset(rowNum)
{
	document.forms[0].elements['grp_'+rowNum].value='0';
	document.forms[0].elements['from_'+rowNum].value='';
	document.forms[0].elements['to_'+rowNum].value='';
}




		function SubmitRequest(){

		for(k=0;k<=eval(GroupArr.length-1);k++){

				if(document.getElementById("amount_"+k).value==''){
				alert('<fmt:message key="HRMS.amtreq" bundle="${constants}"/>');
				document.getElementById('amount_'+k).focus();
				statusForSubmit=0;
				break;
				}
			else{
				statusForSubmit=1;
				}
		}
		if(statusForSubmit==1){
			
			document.getElementById("subButton").disabled=true;
			
			document.groupInsurance.submit();
		}
		
		}
		
	function goBack()
	{
	history.go(-1);
	}
	
	var temp=0;
	var mfvalue=0;
	var total=0;
	var erase="";
	var grossAmt=0;
	var	FromDateArr = new  Array();
	var	toDateArr = new  Array();
	
/*function DeleteRecordFromTable(id){
		mulFact.splice(id,1);
		GroupArr.splice(id,1);
		FromDateArr.splice(id,1);
		toDateArr.splice(id,1);
		var tbody = document.getElementById("calcDetail").getElementsByTagName("tbody")[1];
		var amt=document.getElementById("totalAmt_"+id).value;
		var grossAmt=document.getElementById("grsamt").value;
		grossAmt=grossAmt-amt;
		document.getElementById("grsamt").value=grossAmt;
		tbody.removeChild(document.getElementById('rowencXML'+id));
		//subtract amount
		
	}*/
	
function setTableId(tableId,nextId)
	{	
			
			var trow=tableId.insertRow();
			trow.id = 'rowencXML'+ nextId;

			td=trow.insertCell(0);
			td.setAttribute("align","center");
			td.innerHTML="<input type='text'  name='Group_"+nextId+"' readonly='true'   id = 'Group_"+nextId+"'/>";

			td1=trow.insertCell(1);
			td1.setAttribute("align","center");
			td1.innerHTML="<input type='text'  name='fromDate"+nextId+"' readonly='true'   id='from_"+nextId+"'/>";		
			
			td2=trow.insertCell(2);
			td2.setAttribute("align","center");
			td2.innerHTML="<input type='text'  name='toDate"+nextId+"' readonly='true'  id='to_"+nextId+"' />"; 

			td3=trow.insertCell(3);
			td3.setAttribute("align","right");
			td3.innerHTML="<input type='text'  style=\"text-align: right\"  name='amount"+nextId+"'  id='amount_"+nextId+"' onchange='calcamt(this);' />"; 

			td4=trow.insertCell(4);
			td4.setAttribute("align","right");
			td4.innerHTML="<input type='text' style=\"text-align: right\"   name='multiplyingFactor"+nextId+"' readonly='true'  id='multiplyingFactor_"+nextId+"' />"; 

			td5=trow.insertCell(5);
			td5.setAttribute("align","right");
			td5.innerHTML="<input type='text' style=\"text-align: right\"   name='totalAmt"+nextId+"' readonly='true' style='background-color:lightblue' id='totalAmt_"+nextId+"' />"; 

			td7=trow.insertCell(7);
			td7.setAttribute("align","center");
			td7.innerHTML="<input type='hidden'  id='rowSrvcId"+nextId+"'  name='rowSrvcId"+nextId+"' value='0'/>"

			td8=trow.insertCell(8);
			td8.setAttribute("align","center");
			td8.innerHTML="<input type='hidden'  name='GroupDtl"+nextId+"' readonly='true'   id = 'GroupDtl"+nextId+"'/>";
			
			/*td9=trow.insertCell(9);
			td9.setAttribute("align","center");
			td9.innerHTML="<a href='#' onclick='DeleteRecordFromTable("+nextId+");'>Delete</a>";*/
			return nextId;
		}


function setTable(tableId,counter){

	var validDate=true;
	var validGrp=true;
	var tbody =document.getElementById("calcDetail").getElementsByTagName("tbody")[1]; 	
	//tbody.innerText=erase;
	document.getElementById('grsamt').value="";
	document.getElementById('subButton').disabled=true;
			j=0;
			temp=0;
			total=0;
			mfvalue=0;
			z=0;
			var prev=-1;
			grp=0;
			grossAmt=0;
			var trackGrp=0;
			GroupArr = new  Array();
		    mulFact = new Array();
			

			//var todayDateArr='${currentDate}'.split('/');
			currDate=new Date('${currentDate}');
			currMonth=currDate.getMonth()+1;
			
			if(currMonth < 10)
			{

				var sysDate= currDate.getDate().toString() + "/0" + currMonth.toString() + "/" + currDate.getYear().toString();
			}
			else{
							
				var sysDate= currDate.getDate().toString() + "/" + currMonth.toString() + "/" + currDate.getYear().toString();
				}

			
			
				//CODE FOR VALIDATION OF DATE AND GROUP
			
				
				for(i=0;i<counter;i++){
						
						var str=document.getElementById('grp_'+i).value;
						var grp;
						var from;
						var to;
						var prevfrom;
						
					
							if(document.getElementById('grp_'+i).value=='0'){
								continue;
							}
							else{
								
								/*var mainPgFieldArray= new Array('from_'+i,'to_'+i);
								statusMainPgValidation =  validateSpecificFormFields(mainPgFieldArray);
								if(!statusMainPgValidation){
									return;
								}*/
								GroupArr[z]=document.getElementById('grp_'+i).value;
								FromDateArr[z]=document.getElementById('from_'+i).value;
								toDateArr[z]=document.getElementById('to_'+i).value;
								
								from=FromDateArr[z];
								from=from.split("/");
								starttime = new Date(from[2],from[1]-1,from[0]); 
								starttime = new Date(starttime.valueOf());
								
								to=toDateArr[z];
								to=to.split("/");
								endtime = new Date(to[2],to[1]-1,to[0]); 
								endtime = new Date(endtime.valueOf()); 	
		
									//From date required
									if(document.getElementById("from_"+i).value==''){
										alert(innerHTML="<fmt:message key="HRMS.fromdatereq" />");
										validDate=false;
										document.getElementById("from_"+i).style.backgroundColor='lightblue';
										document.getElementById("from_"+i).focus();
										return;
									}
									//To date required
									if(document.getElementById("to_"+i).value==''){
										alert(innerHTML="<fmt:message key="HRMS.todatereq" />");
										validDate=false;
											document.getElementById("to_"+i).style.backgroundColor='lightblue';
										document.getElementById("to_"+i).focus();
										return;
									}
									//Compare to sysDate
										if( endtime>currDate)
										{
											alert(innerHTML="<fmt:message key="HRMS.sysdatecomp"/>");
											tbody.innerText=erase;
											validDate=false;
											document.getElementById('to_'+eval(i)).focus();
											return;
										}
							
								 if(document.getElementById("from_"+i).value!=''&& document.getElementById("to_"+i).value!='' && document.getElementById('grp_'+i).value==0)
								 {
								 	alert(innerHTML="<fmt:message key="HRMS.grpreq" />");
								 	document.getElementById('grp_'+i).focus();
								 }
								 
								 
								 grp=str.split('_')[1];

									//Group ordering
									if(prev>=eval(grp)){
										tbody.innerText=erase;
										alert(innerHTML="<fmt:message key="HRMS.grpmismatch" />");
										validGrp=false;
										document.getElementById('grp_'+eval(i-1)).focus();
										return;
									}
										prev=eval(grp);
									
									//Same group date validation
									if(starttime>endtime){
										alert(innerHTML="<fmt:message key="HRMS.lesdate" />");
										tbody.innerText=erase;
										validDate=false;
										document.getElementById('from_'+i).focus();
										return;
									}
									//Inter Group date validation
									if(z>0){
									prevfrom=FromDateArr[z-1];
									prevfrom=prevfrom.split("/");
									prevstarttime = new Date(prevfrom[2],prevfrom[1]-1,prevfrom[0]); 
									prevstarttime = new Date(prevstarttime.valueOf());
									}
									
									if(z>0 && prevstarttime<=endtime){
											alert(innerHTML="<fmt:message key="HRMS.dateinvalid" />");
											tbody.innerText=erase;
											validDate=false;
											document.getElementById('from_'+eval(i-1)).focus();
											return;
										}
									
																	
								z++;	
						}
						tbody.innerText=erase;
					}

//			alert("Validation ENDS and date is "+validDate+" and group is "+validGrp);	

	grp=0;
		
		//AFTER VALIDATION ONLY		
		if(validDate==true && validGrp==true){
					j=0;
					temp=0;
					total=0;
					mfvalue=0;
					z=0;
				for(i=0;i<counter;i++){
					if(document.getElementById('grp_'+i).value=='0'){
					continue;
					}
		
					else{
							var str=document.getElementById('grp_'+i).value;
					    	
					    	document.getElementById('calcDetail').style.display='';
							GroupArr[j]=document.getElementById('grp_'+i).value.split('_')[1];
							FromDateArr[j]=document.getElementById('from_'+i).value;
							toDateArr[j]=document.getElementById('to_'+i).value;
							var id = setTableId(tableId,j);		
							grp=str.split('_')[1];
							if(id!=undefined && id>=0)
							{	
								document.forms[0].elements['Group_'+j].value=document.getElementById('grp_'+i).options[eval(grp)].innerText;
								document.forms[0].elements['fromDate'+j].value=document.getElementById('from_'+i).value;
								document.forms[0].elements['toDate'+j].value=document.getElementById('to_'+i).value;
								document.forms[0].elements['GroupDtl'+j].value=document.getElementById('grp_'+i).options.value;
							}
						  j++;
						}
				}
	

	//CODE TO GENERATE MULT FACT:	

				for(k=eval(GroupArr.length-1);k>=0;k--){
							
							if(GroupArr[k]=='4'){
								mfvalue=1;
							}
							
							if(GroupArr[k]=='3'){
								if(temp==0)
									mfvalue=2;
								else
									mfvalue=2-total;
							}
								
							if(GroupArr[k]=='2'){
								if(temp==0)
									mfvalue=4;
								else
									mfvalue=4-total;
							}		
				
							if(GroupArr[k]=='1'){
								if(temp==0)
									mfvalue=8;
								else
									mfvalue=8-total;
							}
							
						temp++;
					    total+=mfvalue;
					 	document.forms[0].elements['multiplyingFactor'+k].value=eval(mfvalue);
				trackGrp++;
				}
		}
		// setting values from Group Details screen into the below table :- Ends

		if(trackGrp==0)	{
			alert('<fmt:message key="HRMS.grpreq" bundle="${constants}"/>');
			document.getElementById('grp_0').focus();
		}   
	document.forms[0].elements['appliedGroups'].value=eval(GroupArr.length);
}

var tempamt=0;

function calcamt(component){
 		
		var str=component.id;
		var splitted=str.split('_');
		if(isNaN(component.value) || component.value<0){
				component.value='';
				alert('<fmt:message key="HRMS.amtinvalid" bundle="${constants}"/>');
				document.getElementById(component.id).focus();
				document.getElementById('subButton').disabled=true;
				document.getElementById('totalAmt_'+splitted[1]).value="";
				document.getElementById('grsamt').value="";
				return;	
			}
		var amt=eval(component.value*eval(document.getElementById('multiplyingFactor_'+splitted[1]).value));
		tempamt=document.getElementById('totalAmt_'+splitted[1]).value;
			if(tempamt==''){
			tempamt=0;
				}
	//var prevamt=eval(tempamt*eval(document.getElementById('multiplyingFactor_'+splitted[1]).value));
			grossAmt-=tempamt;
			grossAmt+=eval(amt);
				document.getElementById('totalAmt_'+splitted[1]).value=amt;
				document.getElementById('grsamt').value=grossAmt;
		
		if(component.value==0||component.value==''){
			document.getElementById('subButton').disabled=true;
			alert('<fmt:message key="HRMS.amtreq" bundle="${constants}"/>');
			document.getElementById(component.id).focus();
			return;
			}
		else{
			document.getElementById('subButton').disabled=false;
			}
		
		if( '${EmpDetVO.empName}'=='' ||  '${ EmpDetVO.designation}'=='No Record' || '${ EmpDetVO.designation}'=='' || '${EmpDetVO.salary}'=='' || '${EmpDetVO.doj}'=='' || '${EmpDetVO.dor}'=='' )
		{
			//document.getElementById('subButton').disabled=true;
		}
	}


--></script>


<hdiits:form name="groupInsurance" validate="true" action="./hrms.htm?actionFlag=groupInsurance" method="post">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>


<br>

<table class="tabtable" border=0 id="getGroupDetails">

	<tr bgcolor="#386CB7" id="Grpdtls" >
		 <td class="fieldLabel" colspan="8" align="center" >
		 <font color="#ffffff">
	<strong><u><fmt:message key="HRMS.grpdtls" /></u></strong>
	     </font>
		</td>
	</tr>
		<tr>
	
		<td align="left" colspan="6" ><font color="#8D8D92">
			&nbsp&nbsp<b><fmt:message key="HRMS.note" /></b>
		</font></td>
	</tr>
		
		<c:set var="counter" value="0"/>
		<c:set var="grpDates" value=""/>
		
		<c:forEach var="group" begin="1" end="4">

  <tr>

		<td align="center" width="13%"><b><fmt:message key="HRMS.group" /></b></td>
	
    <td>	
	 <hdiits:select name="group_${counter}" size="1" id="grp_${counter}" caption="group" mandatory="false" sort="false" > 
               <hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
               <c:forEach var="subGroup" items="${groupList}">
               <hdiits:option  value="${subGroup.lookupId}_${subGroup.orderNo}">${subGroup.lookupDesc}</hdiits:option>
               </c:forEach>
	</hdiits:select>	
	</td>
	<td>
	<b><fmt:message key="HRMS.frmdt" /></b></td>
		<td><hdiits:dateTime   validation="txt.isrequired" name="from_${counter}" captionid="HRMS.frmdt"  bundle="${constants}" onblur="" afterDateSelect="changeColor(document.forms[0].from_${counter});"/>
		<c:set var="grpDates" value="from_${counter},${grpDates}" />
		<script>
		FromDateArr['${counter}']=document.forms[0].from_${counter};
		fromDateStr+="from_${counter},";
		
		document.forms[0].from_${counter}.readOnly=true;
		</script>
	</td>
	
	
	<td>
	<b><fmt:message key="HRMS.todt" /></b></td>
		<td><hdiits:dateTime  validation="txt.isrequired" name="to_${counter}"  captionid="HRMS.todt" bundle="${constants}" onblur=""  afterDateSelect="changeColor(document.forms[0].to_${counter});"/>
		<c:set var="grpDates" value="to_${counter},${grpDates}"/>
		<script>
		toDateArr['${counter}']=document.forms[0].to_${counter};
		toDateStr+="to_${counter},";
		document.forms[0].to_${counter}.readOnly=true;
		</script>
	</td>
	<td align="left">
		<a href="#" tabindex="5" onclick="rowReset(${counter});" ><b><fmt:message key="HRMS.reset" /></b></a>
	</td>
	
  </tr>
		<c:set var="counter" value="${counter+1}"/>

</c:forEach>


  <tr align="centre">
	 <td align="center" colspan="8">
	  <hdiits:button type="button" captionid="HRMS.calculate" bundle="${constants}" id="calculate"  name="calculate" onclick="setTable(document.getElementById('GrpDetails'),'${counter}')" />
	  <hdiits:button type="button" name="close" captionid="HRMS.close"  bundle="${constants}" onclick="goBack();"/>

	 		<hdiits:resetbutton  title="Reset" value="Reset" name="Reset" type="button" tabindex="12"/>
	  <script>
	  var resetButton='<fmt:message key="HRMS.reset"/>';
		document.forms[0].Reset.value=resetButton;
	  </script>
	

	 </td>
  </tr>			


</table>
			
				
				
<table width="100%" id="calcDetail"  style="display:none" >
  <tbody id="calDetHead" >
	 <tr bgcolor="#386CB7" id="Grpdtls" >
	     <td class="fieldLabel" colspan="8" align="center" >
			<font color="#ffffff">
				<strong><u><fmt:message key="HRMS.caldtls" /></u></strong>
				</font>
		 </td>
	</tr>	
		
	<tr id="caldtls"  >
											<td align="center" ><b><fmt:message
												key="HRMS.group" /></b></td>
											<td align="center" ><b><fmt:message
												key="HRMS.frmdt" /></b></td>
											<td align="center" ><b><fmt:message
												key="HRMS.todt" /></b></td>
											<td align="center" ><b><fmt:message
												key="HRMS.amt" /></b></td>
											<td align="center" ><b><fmt:message
												key="HRMS.multifctr" /></b></td>
											<td align="center" ><b><fmt:message
												key="HRMS.totamt" /></b></td>
				
	</tr>

  </tbody>

<tbody id="GrpDetails">
</tbody>
  <tbody>	
			<tr id="totamt">
		      <td colspan="4">
		      &nbsp;
		      </td>
		      <td  align="center">
		      
		      	<b>
		      		<hdiits:caption captionid="HRMS.grosamt" bundle="${constants}" id="totalamt"  />:
		      	</b>
		      </td>
		      
		      
		      <td align="right">
		      		<hdiits:text readonly="true" captionid ="HRMS.grosamt" style="background-color:lightblue;text-align: right" name="grossAmt" id="grsamt" mandatory="false" validation="txt.isrequired"/>

				<hdiits:hidden caption="userid" name="userId" default="${userId}"/>
				<hdiits:hidden  name="status" caption="status" default="0" />
				<hdiits:hidden  name="appliedGroups" caption="appliedGroups" default="0" />

		      </td>  
		    </tr>
			
						
			<tr>
					<td align="center" id="btn" colspan="8">
						<hdiits:button id="subButton" type="button" readonly="true" name="Submit" captionid="HRMS.submit" bundle="${constants}" onclick="SubmitRequest();" />
						
					</td>
			</tr>
			
	
   </tbody>
</table>
		<c:if test="${EmpDetVO.empName==''} ||  ${ EmpDetVO.designation==''} || ${EmpDetVO.salary=='0' } || ${EmpDetVO.doj==''} || ${EmpDetVO.dor==''}">
			<script>
				document.forms[0].Submit.disabled=true;
			</script>
		</c:if>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'controlNames="${grpDates}"/>

</hdiits:form>
 
  <%
}
   	catch(Exception e)
   	{
		e.printStackTrace();
	}

%>
 
   		  
	 