<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>">
</script>
<script type="text/javascript" 
	src="script/hod/ps/common.js">
</script>
<script type="text/javascript" 
	src="script/common/person.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>">

</script>



<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="empName" value="${resValue.empName}"> </c:set>
<c:set var="isPunished" value="${resValue.isPunished}"> </c:set>


<c:set var="resultObj1" value="${result}" > </c:set>
<c:set var="resValue1" value="${resultObj1.resultValue}" > </c:set>	
<c:set var="gradeName" value="${resValue1.gradeName}"> </c:set>
<c:set var="postName" value="${resValue1.postName}"> </c:set>
<c:set var="desigName" value="${resValue1.desigName}"> </c:set>
<c:set var="scaleId" value="${resValue.scaleId}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set> 
<c:set var="empType" value="${resValue.empType}" ></c:set>
<c:set var="empId" value="${resValue.empId}" ></c:set>
<c:set var="otherId" value="${resValue.otherId}" ></c:set>

<c:set var="empAllRecOther" value="${resValue.empAllRecOther}" ></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>
<c:set var="updatePaybillFlg" value="${resValue.updatePaybillFlg}" ></c:set>
<c:set var="paybillMonth" value="${resValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resValue.paybillYear}" ></c:set>

<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="contractEmpType" key="contract" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="fixedEmpType" key="fixed" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="probationEmpType" key="probation" bundle="${commonLable}" scope="request"> </fmt:message>


<c:set var="empAllRecFlag" value="${resValue.empAllRecFlag}" ></c:set>
<c:set var="empId" value="${resValue.empId}" ></c:set>
<!--<c:set var="deptName" value="${resValue1.deptList}"> </c:set>
  <c:out value="${deptName}"> </c:out> -->



<!--  <c:set var="resultObj3" value="${result}" > </c:set>
<c:set var="resValue3" value="${resultObj3.resultValue}" > </c:set>	
<c:set var="otherList" value="${resValue3.otherList}"> </c:set> -->

<c:set var="otherList" value="${resValue1.otherList}"> </c:set>
<c:set var="cityList" value="${resValue1.cityList}"> </c:set>
<c:set var="quaterTypeList" value="${resValue1.quaterTypeList}"> </c:set> 
<c:set var="resultObj2" value="${result}" > </c:set>
<c:set var="resValue2" value="${resultObj2.resultValue}" > </c:set>	
<%--<c:set var="gradeName" value="${resValue2.gradeList}"> </c:set>
<c:set var="hrScaleMst" value="${resValue2.hrScaleMst}"> </c:set>
<c:set var="orgGradeMst" value="${resValue2.orgGradeMst}"> </c:set>
<c:set var="gdMapId" value="${resValue2.gdMapId}"> </c:set>
<c:set var="sgdMapId" value="${resValue2.sgdMapId}"> </c:set>--%>
<!--  for Fetching all scales for a Designation. -->
<c:set var="scaleList" value="${resValue2.scaleList}"> </c:set>
<!--  by manoj for hrEmpTraMpg -->
<c:set var="hrEmpTraMpg" value="${resValue2.hrEmpTraMpg}"> </c:set>
<!--end  by manoj for hrEmpTraMpg -->
<!--  Fetching all Designation of a perticlular Grade -->
<c:set var="desgList" value="${resValue2.desgList}"> </c:set>
<c:set var="gradeId" value="${resValue2.gradeId}"> </c:set>
<fmt:setBundle basename="resources.eis.eis_Constants" var="enConstants" scope="request"/>
<fmt:message var="p1" key="AISGradeCode" bundle="${enConstants}" scope="request" />


<script type="text/javascript" language="JavaScript">

function chkKey(e)
{
	
	if(window.event) // IE
	{
		if(e.keyCode=="13")
			{
				return false;
			}
		else
			return true;
	}
}

//added Mrugesh
// Start By Urvin shah To Clear the Combos 

function clearCombo(cmbName)
{	
	
	var v=document.getElementById(cmbName).length;
	
	for(i=0;i<v-1;i++)
	{	
			
			lgth = document.getElementById(cmbName).options.length -1;
			document.getElementById(cmbName).options[lgth] = null;
	}		
}

// Ended By Urvin shah.
var flag=0;
function disableFields(empTypeId) {


//if(empTypeId==300018 || empTypeId==300225)
 if(empTypeId=="${contractEmpType}" || empTypeId=="${fixedEmpType}")
    {
    	
    	var cmbName="scale";
    	//alert('alert if');
    	clearCombo(cmbName);
    	/*if(empTypeId==300018)
	    	document.getElementById("incometax").disabled=true;*/
    	//document.getElementById("is_handicapped").disabled=true;
    	//document.getElementById("quaterType").disabled=true;
    	document.getElementById("medAllowance").disabled=true;
    	document.getElementById("uniformAvailed").disabled=true;
    	document.getElementById("sis1979Flag").disabled=true;
    	document.getElementById("FamilyPlannig").disabled=true;
    	//document.frmOtherMst.scale(document.frmOtherMst.scale.selectedIndex).text="0-0-0";
    	//document.frmOtherMst.scale(document.frmOtherMst.scale.selectedIndex).value="0";
    	document.getElementById("scale").value="0";
    	document.getElementById("scale").disabled=true;
    	
    	//document.getElementById("empCity").value="0";
    	//document.getElementById("empCity").disabled=true;
    	flag=1;
    	//by manoj for tra mapping
    	document.getElementById("traTable").style.display='none';
    	document.getElementById("taAvailed").checked=false;
    	//end by manoj for tra mapping
    }
    else
    {
    	//alert('alert else'); 
    	   	
    	//document.getElementById("incometax").disabled=false;
    	//document.getElementById("is_handicapped").disabled=false;
    	//document.getElementById("quaterType").disabled=false;
    	document.getElementById("medAllowance").disabled=false;
    	document.getElementById("uniformAvailed").disabled=false;
    	document.getElementById("sis1979Flag").disabled=false;
    	document.getElementById("FamilyPlannig").disabled=false;
    	document.getElementById("scale").disabled=false;
    	document.frmOtherMst.scale(document.frmOtherMst.scale.oprions).value="";
    	document.frmOtherMst.scale(document.frmOtherMst.scale.oprions).text="--------------Select--------------";
		
		if(eval(flag)==1)
		{			
			clearCombo("scale");
			//getgradedesgpost();
			flag=0;
    	}
    	//by manoj for tra mapping
    	document.getElementById("taAvailed").checked=true;
    	if(document.getElementById("taAvailed").checked==true)
    	{
    		document.getElementById("traTable").style.display='';
    	}
    	//end by manoj for tra mapping
    	//document.frmOtherMst.initialBasic.value='';
    	//document.getElementById("empCity").value="";
    	//document.getElementById("empCity").disabled=false;
    }
    if(${p1}==${gradeId})
    {
     //alert('grade match');
     document.getElementById("sis1979Flag").disabled=true;
     document.getElementById("isSis").style.visibility ='visible';
     document.getElementById("sisLabel").style.visibility ='visible';
     
     }else{
      //alert('in else');
     document.getElementById("isSis").style.visibility='hidden';
     document.getElementById("sisLabel").style.visibility ='hidden';
     }
    

}


//ended by Mrugesh
function chkval()
{
	
	if(document.frmOtherMst.sgdMapId.value== '${sgdMapId}' && document.frmOtherMst.initialBasic.value==''){
		return true;
	}
	if(document.frmOtherMst.initialBasic.value!='')
	{
		if( document.frmOtherMst.initialBasic.value < 1)
		{
			alert("Please Enter Valid Value of Current basic");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		var intialBasic=parseFloat(document.frmOtherMst.initialBasic.value);
		if(!document.forms[0].sgdMapId.disabled)//start if only if not fixed pay 
		{		
		var scaleValue=document.forms[0].sgdMapId.options[document.forms[0].sgdMapId.selectedIndex].text;
		var initialValue=parseFloat(scaleValue.substring(0,scaleValue.indexOf("-")));
		var temp=scaleValue.substring(scaleValue.indexOf("-")+1);
		var incrementValue = parseFloat(temp.substring(0,temp.indexOf("-")));
		var endValue= parseFloat(temp.substring(temp.indexOf("-")+1));
		var sixPayScale=temp.substring(0,temp.indexOf("-"));
		var gpString=temp.substring(temp.indexOf("(")+1);
		gpString=gpString.substring(0,gpString.indexOf(")"))
		//alert(sixPayScale);
		//alert(gpString);
		var maxBasicSixPay=eval(sixPayScale)+eval(gpString);
		//alert(maxBasicSixPay);
		var minBasicSixPay=initialValue+eval(gpString);
		//alert(minBasicSixPay);
		if(sixPayScale>initialValue)
		{
			if(intialBasic < minBasicSixPay){
				alert("Please Enter Higher value of Current basic then scale's Initial Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;
			}
			if(intialBasic > maxBasicSixPay){
				alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;
			}
				
			return true;
		}
		temp=temp.substring(temp.indexOf("-")+1);
		

		if(temp.indexOf("-")>0) // this if loop is for higher scale  
		{
		var higherIncVal=temp.substring(temp.indexOf("-")+1);
		higherIncVal=higherIncVal.substring(0,higherIncVal.indexOf("-"));
		temp=temp.substring(temp.indexOf("-")+1);
		var higherEndVal=temp.substring(temp.indexOf("-")+1);
		
		// Amount is straight away equals start ,middle or last values return
		
        if(intialBasic == initialValue||intialBasic == endValue||intialBasic == higherEndVal ){
			return true;
		}
		
		if(intialBasic < initialValue){
			alert("Please Enter Higher value of Current basic then scale's Initial Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		if(intialBasic > higherEndVal){
			alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		
		// this code is same as normal scale as values is less than higherscale range
		if(intialBasic<=endValue)
		{
		var tempValue = intialBasic - initialValue;
		if(incrementValue!=0 && tempValue%incrementValue == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(incrementValue!=0 && tempValue%incrementValue != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
				}
			}
		}			
		else // validation if scale is lying in higher scale range
		{
		var tempValue = intialBasic - endValue;
		if(higherIncVal!=0 && tempValue%higherIncVal == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(higherIncVal!=0 && tempValue%higherIncVal != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
				}
			}
		}
		}//  end if loop is for higher scale 
		else  // this else loop is for normal scale retained as it is  
		{
		if(intialBasic == initialValue){
			return true;
		}
		if(intialBasic == endValue){
			return true;
		}
		if(intialBasic < initialValue){
			alert("Please Enter Higher value of Current basic then scale's Initial Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		if(intialBasic > endValue){
			alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		var tempValue = intialBasic - initialValue;
		if(incrementValue!=0 && tempValue%incrementValue == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(incrementValue!=0 && tempValue%incrementValue != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
				}
			}
	    }		
	}//end start if only if not fixed pay 
	}
}



function GetDesignations()
{
	
	var v=document.getElementById("desig").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("desig").options.length -1;
			document.getElementById("desig").options[lgth] = null;
	}	
		  xmlHttp=GetXmlHttpObject();
		  
		  if (xmlHttp==null)
		 
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  
		  url= uri+'&gradeID='+ document.frmOtherMst.empGrade.value;
		  
		  var actionf="GetDesignations";
		  
		  uri='./ifms.htm?actionFlag='+actionf;
		  
		  url=uri+url; 
		  xmlHttp.onreadystatechange=gradechangeddesigs;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function gradechangeddesigs()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var Designations = document.getElementById("desig");
			
								var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('Designations-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text = entries[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        
     			        try
   				        {      				    					
                            Designations.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       Designations.add(y); 
   			   	        }	
	                
	                 }
  }
}

function GetScales()
{

	var v=document.getElementById("scale").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("scale").options.length -1;
			document.getElementById("scale").options[lgth] = null;
	}	
		  xmlHttp=GetXmlHttpObject();
		  
		  if (xmlHttp==null)
		 
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  
		  url= uri+'&gdMapId='+ document.frmOtherMst.empDesg.value;
		  
		  var actionf="GetScales";
		  
		  uri='./ifms.htm?actionFlag='+actionf;
		  
		  url=uri+url; 
            
		  xmlHttp.onreadystatechange=gdChangeScale;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function gdChangeScale()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var scales = document.getElementById("scale");
			
								var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('Scale-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text = entries[i].childNodes[1].text;

     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        
     			        try
   				        {      				    					
                            scales.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       scales.add(y); 
   			   	        }	
	                
	                 }
  }
}


//by manoj for tra mapping
function taAvailed()
{
	return document.getElementById("taAvailed").checked;
}
function vehiAvailed()
{
	return document.getElementById("chkVehicalAvailed").checked;
}

/*
Made By:- Urvin Shah
Purpose:- This method will fetch the other details for the searched employee.
*/

function submitFormAuto()
{
	var empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	var url="./ifms.htm?actionFlag=getOtherData&empId="+empId;
	document.frmOtherMst.action=url;
	document.frmOtherMst.submit();
}
function onclosefn()
{
	
	if('${empAllRec}'=='true')
		window.location="./ifms.htm?actionFlag=getOtherData&Employee_ID_EmpOtherSearch=${empId}&empAllRec=true";
	else
		window.location="./ifms.htm?actionFlag=getOtherData";
	
}

function submitForm()
{
  var uri = "./ifms.htm?actionFlag=";
  if('${empAllRec}'=='true'){
	  if(confirm("Are you sure to update records"))
	   {
		    var url = uri + "insertOtherData&edit=Y&otherId=${otherList.otherId}&empAllRec=true";
		    document.frmOtherMst.action = url;
		 	document.frmOtherMst.submit();
	   }
	  else
	   {
			hideProgressbar();
			document.getElementById("Update").disabled=false;
	   }
   
  }
   else
   {
    var confirmalert='Are you sure to update records?';
   	if(document.forms[0].updatePaybillFlg.value!=null&&document.forms[0].updatePaybillFlg.value=='y') 
   		confirmalert='Are you sure to update records and paybill as well?';
	   
   if(confirm(confirmalert))
   {
       var url = uri + "insertOtherData&edit=Y&otherId=${otherList.otherId}&empAllRec=false";
	   document.frmOtherMst.action = url;
	   document.frmOtherMst.submit();
   }
   else
   {
	   hideProgressbar();
	   document.getElementById("Update").disabled=false;
	 }
   }
}
//end by manoj for tra mapping

//Added By varun$harm@ for Family Planning
function enableFamilyPln()
{
	if ( document.frmOtherMst.FamilyPlannig.checked==true )
	{
		var currentFmlyPlnAmt = document.frmOtherMst.FamilyPlnAmt.value;
		var scaleAmt = document.frmOtherMst.sgdMapId.options[document.frmOtherMst.sgdMapId.selectedIndex].text;
			scaleAmt = scaleAmt.split('-');
		if(parseFloat(scaleAmt[1])>parseFloat(scaleAmt[0]))
		{
			document.frmOtherMst.FamilyPlnAmt.value = 0;
			document.getElementById("FamilyPlnAmt").style.display='';
			return true;
		}
		if ( currentFmlyPlnAmt == null || currentFmlyPlnAmt.trim() =='' || currentFmlyPlnAmt == 0) //i.e. if a fresh case
		{
			//make it visible
			document.getElementById("FamilyPlnAmt").style.display='';
			//set fmlyAmt as per the scale
			document.frmOtherMst.FamilyPlnAmt.value = scaleAmt[1];
		}
		//else: i.e. if not  a fresh case den let the amt as it is.
	}
	else
	{
		//if checkbox has been deselected set the value to zero.
		document.frmOtherMst.FamilyPlnAmt.value = 0;
		document.getElementById("FamilyPlnAmt").style.display='none';
	}
}
//Ended by varun$harm@

function toggleIsAvailedHRA()
{
	if( document.frmOtherMst.chkBxIsAvailedHRA.checked==true)
	{
		document.frmOtherMst.chkBxIsAvailedHRA.value=1;
		
	}
	else
	{
		document.frmOtherMst.chkBxIsAvailedHRA.value=0;
		
	}
}

</script>
<body>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	    <c:choose>
	    <c:when test="${updatePaybillFlg eq 'y'}" >
		<li class="selected"><a href="#" rel="tcontent1"><b>Update basic details and paybill</b></a></li>
		</c:when>
		<c:otherwise>
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OT.updateOtherDetailInfo" bundle="${commonLables}"/></b></a></li>
        </c:otherwise>
        </c:choose>
    </ul>
</div>
	
<hdiits:form name="frmOtherMst"  validate="true" method="POST" 
             action="javascript:submitForm()"  >


	<div style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<table><tr><td><hdiits:hidden name="updatePaybillFlg" default="${updatePaybillFlg}" />
			     <hdiits:hidden name="paybillMonth" default="${paybillMonth}" />
			     <hdiits:hidden name="paybillYear" default="${paybillYear}" />
	</td></tr></table>	
	<c:if test="${empAllRec eq 'false'}">
	<table  width="85%" align="center">
	    <tr>
			<td>
			     <c:if test="${updatePaybillFlg eq 'y'}" >
			     <a href="./ifms.htm?actionFlag=fillPaybillData&paybillYear=${paybillYear}&paybillMonth=${paybillMonth}&updatePaybillEmpId=${otherList.hrEisEmpMst.orgEmpMst.empId}&searchData=y">Back</a>
			     </c:if>
			     
		</tr>
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpOtherSearch"/>
						<jsp:param name="formName" value="frmOtherMst"/>
						<jsp:param name="functionName" value="submitFormAuto"/>
					</jsp:include>
			</td>
		</tr>
		<c:if test="${listSize ge 2}">
		<c:set value="display:none" var="displayStyle"/>
		</c:if>
		<tr style="${displayStyle}">
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<!--<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="return chkValue()"/>	
				-->
				</fieldset>
			</td>
		</tr>	
	</table>
	</c:if>
	
<table align="center" width="100%">
	
	<tr>
		<!-- <td><hdiits:caption captionid="OT.otherId" bundle="${commonLables}"/></td>
		<td><hdiits:text name="otherId" default="" caption="otherId"  maxlength="10" readonly="true" style="background:gray;color:white" size="20"/> 
		</td> -->
	</tr>
	<tr> </tr> <tr> </tr>
	<tr>
		<hdiits:hidden name="empName" default="${otherList.hrEisEmpMst.empId}" />
		<TD width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empName" bundle="${commonLables}"></hdiits:caption></b>
		</TD><td width="30%" align="left" ><hdiits:text name="empName" default="${empName}" caption="empName" readonly="true" size="25"/>
      <%-- <td><hdiits:select name="empNameSel" size="1" readonly="true" 
                       captionid="OT.empName"   
                       validation="sel.isrequired"
                       bundle="${commonLables}" 
                       sort="false">
        <hdiits:option value="">---------------Select---------------</hdiits:option>
        
		 
         <c:forEach items ="${resValue.empList}" var="list">
			
			<c:choose>

			<c:when test="${list.empId == otherList.hrEisEmpMst.empId}">
			  
 			    <hdiits:option  value="${list.empId}" selected="true"> ${list.orgEmpMst.empFname} ${list.orgEmpMst.empMname} ${list.orgEmpMst.empLname}</hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.empId}"> ${list.orgEmpMst.empFname} ${list.orgEmpMst.empMname} ${list.orgEmpMst.empLname} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			</hdiits:select>
			 --%>
        </td><td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <TD width="16%" align="left"   class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empGrade" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td  width="30%" align="left" ><hdiits:text name="empGrade" default="${gradeName}" caption="empGrade" readonly="true"  size="25"/>
	<%--	<hdiits:select name="empGrade" size="1"
                       captionid="OT.empGrade"  
                       sort="false"
                       mandatory="true" 
                       validation="sel.isrequired"
                       bundle="${commonLables}" 
                       onchange="GetDesignations()" >
                       
        <hdiits:option value="">---------------Select---------------</hdiits:option>
        
        
        <c:forEach items ="${resValue2.gradeList}" var="list">
			
			<c:choose>

			
			<c:when test="${list.gradeId == otherList.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId}">
			 
 			    <hdiits:option  value="${list.gradeId}" selected="true"> ${list.gradeName} </hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.gradeId}"> ${list.gradeName} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
        
        </hdiits:select > --%>
		</td>
	</tr>
<tr> </tr> <tr> </tr>	
	
	
	<tr >
		<TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empDesg"  bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" ><hdiits:text name="empDesg" default="${desigName}" caption="empDesg" readonly="true" size="25"/>
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><TD  width="16%" align="left"  class="fieldLabel" >
		<!--<b><hdiits:caption captionid="hr.other.post"  bundle="${commonLables}"></hdiits:caption></b>-->
		</TD>
		<td width="30%" align="left" >  	<%--<hdiits:text name="empPost" default="${postName}" caption="empPost" readonly="true" size="25"/>
	<hdiits:select id="desig" name="empDesg" size="1"
                       captionid="OT.empDesg"   
                       validation="sel.isrequired"
                       mandatory="true" 
                       sort="false"
                       bundle="${commonLables}"  onchange="GetScales()">
        <hdiits:option value="">---------------Select---------------</hdiits:option>
        <!--<hdiits:option value="1">Class 1</hdiits:option>-->
         <c:forEach items ="${resValue2.desgList}" var="list">
			
			 <c:choose>
			<c:when test="${list.gdMapId == gdMapId}">
			  
 			    <hdiits:option  value="${list.gdMapId}" selected="true"> ${list.orgDesignationMst.dsgnName} </hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.gdMapId}"> ${list.orgDesignationMst.dsgnName} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>      
        
        
        </hdiits:select > --%>
		</td>
		
		
		
	</tr>
	
	
	
	
	<tr> </tr> <tr> </tr>
	<tr><TD width="16%" align="left"   class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empScale" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" ><hdiits:select id="scale" name="sgdMapId" size="1"
                       captionid="OT.empScale"   
                       validation="sel.isrequired"
                       mandatory="true" 
                       sort="false"
                       bundle="${commonLables}" onchange="document.frmOtherMst.initialBasic.value='' ">
                       
        <hdiits:option value="">---------------Select---------------</hdiits:option>      
        <c:forEach items ="${resValue2.scaleList}" var="list">
			
			 <c:choose>
			<c:when test="${list.hrEisScaleMst.scaleId == scaleId}">
			            <c:choose>
						  <c:when test="${list.hrEisScaleMst.scaleHigherIncrAmt == 0}">
						  	<c:choose>
						  		<c:when test="${list.hrEisScaleMst.scaleGradePay == 0}" >
						  			<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt} </hdiits:option>
						  		</c:when>
						  		<c:otherwise>
						  			<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleEndAmt}-(${list.hrEisScaleMst.scaleGradePay}) </hdiits:option>
						  		</c:otherwise>
						  	</c:choose>
						  </c:when>
						  <c:otherwise>
						  		<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt}-${list.hrEisScaleMst.scaleHigherIncrAmt}-${list.hrEisScaleMst.scaleHigherEndAmt} </hdiits:option>
						  </c:otherwise>
			 			</c:choose>
 			    
		
			  </c:when>
			  <c:otherwise>
			            <c:choose>
						  <c:when test="${list.hrEisScaleMst.scaleHigherIncrAmt == 0}">
						    <c:choose>
						  		<c:when test="${list.hrEisScaleMst.scaleGradePay == 0}" >
						  		  <hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt} </hdiits:option>
						  		</c:when>
						  		<c:otherwise>
							  		<hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleEndAmt}-(${list.hrEisScaleMst.scaleGradePay})</hdiits:option>
					  		   </c:otherwise>
						  	</c:choose>
						  </c:when>
						  <c:otherwise>
						  		<hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt}-${list.hrEisScaleMst.scaleHigherIncrAmt}-${list.hrEisScaleMst.scaleHigherEndAmt} </hdiits:option>
						  </c:otherwise>
			 			</c:choose>
			
			</c:otherwise>
			</c:choose>
			</c:forEach>    
        </hdiits:select >
		</td><td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td width="16%" align="left"  ><b><hdiits:caption captionid="currentBasic" bundle="${commonLables}"/></b></td>
		<td width="30%" align="left" ><hdiits:number name="initialBasic" id="initialBasic" default="${otherList.otherCurrentBasic}" caption="Current basic"  validation="txt.isrequired,txt.isnumber" mandatory="true"    maxlength="10"   size="25"/> </td>
		
	</tr><tr> </tr>
	<tr>
	
	    <TD width="16%" align="left"   class="fieldLabel" >
		<b><hdiits:caption captionid="OT.city" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" ><hdiits:select name="empCity" size="1"
                       captionid="OT.city"   
                       validation="sel.isrequired"
                       mandatory="true" 
                       sort="false"
                       bundle="${commonLables}" > <c:out value="cityList"/>
        <hdiits:option value="">---------------Select---------------</hdiits:option>
       
        <c:forEach var="cityList" items="${cityList}">
        
        <c:choose>
			<c:when test="${cityList.cityId == otherList.city}">
			  
 			    <hdiits:option  value="${cityList.cityId}" selected="true"> ${cityList.cityName} </hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${cityList.cityId}"> ${cityList.cityName}</hdiits:option>
			</c:otherwise>
			</c:choose>
        </c:forEach>
        </hdiits:select >
		</td><td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.Phy_handi" bundle="${commonLables}"></hdiits:caption></b>
		</TD>

		<td width="30%" align="left" >
		
		<c:set var="flag" value="TRUE" ></c:set>
		<c:choose>
		<c:when test="${otherList.phyChallenged=='TRUE'}" >
			<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}" value="TRUE" default="TRUE" name="is_handicapped" id="is_handicapped" />
			<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}" value="FALSE"  name="is_handicapped" id="is_handicapped"/>
		</c:when> 
		<c:otherwise>
			<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}" value="TRUE"  name="is_handicapped"  />
			<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}" value="FALSE" default="FALSE" name="is_handicapped" />
		</c:otherwise>
     	</c:choose>
        </td>
		
	</tr><tr> </tr>
	<tr>
	<!-- Modified By Mrugesh for hiding income-tax text box -->
		<!-- <td width="16%" align="left"  ><b><hdiits:caption captionid="OT.incomeTax" bundle="${commonLables}"/></b></td>
		<td width="30%" align="left" ><hdiits:number id="incometax" name="incometax" default="${otherList.incomeTax}" captionid="OT.incomeTax" bundle="${commonLables}"  validation="txt.isnumber" maxlength="10"   size="25"/> </td> -->
		

    <!-- <td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>  -->
    <hdiits:hidden id="incometax" name="incometax"></hdiits:hidden>
    <!-- Ended by Mrugesh -->
		<td width="16%" align="left" colspan="1"><b><hdiits:caption  captionid="incrementDate" bundle="${commonLables}"/></b></td>
		<td  width="30%" align="left" colspan="2"><hdiits:dateTime captionid="incrementDate" caption="incrementDate"  name="IncrementDate" default="${otherList.incrementDate}"  bundle="${commonLables}" validation="txt.isdt" /> </td>
					<td width="16%" align="left"   colspan="1"><b><hdiits:caption id="effctivedate" captionid="effctivedate" bundle="${commonLables}"/></b></td>
		<td  width="30%" align="left" colspan="2"><hdiits:dateTime caption="effctivedate"  name="effctivedate" default="${otherList.commissionAcceptanceDate}"  captionid="effctivedate" bundle="${commonLables}"  validation="txt.isdt"/> </td>
	</tr>
	
	
	<!-- <tr id="cmbQuaterType" style="">
	
		<TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.quaterType" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" ><hdiits:select id="quaterType" name="quaterType" size="1"
                       captionid="OT.quaterType"   
                        condition="chkquaterAvail()"
                       sort="false"
                       bundle="${commonLables}">
                       
        <hdiits:option value="" >---------------Select---------------</hdiits:option>
        <c:forEach var="list" items="${quaterTypeList}">
         
         <c:choose>
			<c:when test="${list.quaId == otherList.hrQuaterTypeMst.quaId}">
			 
 			    <hdiits:option  value="${list.quaId}" selected="true"> ${list.quaType} </hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.quaId}"> ${list.quaType}</hdiits:option>
			</c:otherwise>
			</c:choose>
         </c:forEach>
        </hdiits:select >
		</td>
	</tr><tr> </tr>  -->
	<%-- Added By Paurav  --%>
	<tr><td colspan="5">	<c:choose>
			<c:when test="${otherList.isSis eq 1}">
				<input type="checkbox" id="isSis" name="isSis" value="1" checked>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="isSis" name="isSis" value="1">
			</c:otherwise>
			</c:choose>
			<span id="sisLabel">
			<b><hdiits:caption captionid="OT.ISSIS" id="sisLabel"  bundle="${commonLables}"></hdiits:caption></b>
			</span></td></tr>
	 <tr>
	 
		<td align="left" colspan="2">
			<%-- <hdiits:checkbox name="medAllowance" value="1" />  --%>
			<c:choose>
			<c:when test="${otherList.medAllowance eq 1}">
				<input type="checkbox" id="medAllowance" name="medAllowance" value="1" checked>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="medAllowance" name="medAllowance" value="1">
			</c:otherwise>
			</c:choose>
			<b><hdiits:caption captionid="OT.Medical" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
			<TD>&nbsp;</TD>
		<td align="left" colspan="2">
			<%-- <hdiits:checkbox name="uniformAvailed" value="3"/> --%>
			<c:choose>
			<c:when test="${otherList.uniformAvailed eq 1}">
				<input type="checkbox" id="uniformAvailed" name="uniformAvailed" value="1" checked>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="uniformAvailed" name="uniformAvailed" value="1">
			</c:otherwise>
			</c:choose>
		
			<b><hdiits:caption captionid="OT.Uniform" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		
	</tr>
	<%-- Ended By Paurav --%>
	
	 <tr>
	 
		<td align="left" colspan="2">
			<%-- <hdiits:checkbox name="medAllowance" value="1" />  --%>
			<c:choose>
			<c:when test="${otherList.sis1979Flag eq 'y'}">
				<input type="checkbox" id="sis1979Flag" name="sis1979Flag" value="1" checked>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="sis1979Flag" name="sis1979Flag" value="1">
			</c:otherwise>
			</c:choose>
			<b><hdiits:caption captionid="OTHER.SIS1979" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
			<TD>&nbsp;</TD>
		
		<td align="left" colspan="2">
			<%-- <hdiits:checkbox name="medAllowance" value="1" />  --%>
			<c:choose>
			<c:when test="${otherList.othFamilyPlanning eq 'y'}">
				<input type="checkbox" id="FamilyPlannig" name="FamilyPlannig" value="1" checked onclick="javascript:enableFamilyPln()">
				<b><hdiits:caption captionid="OTHER.FamilyPlanning" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
				<hdiits:text caption="Family Planning" validation="txt.isnumber" style="" default="${otherList.familyPlnAmt}" id="FamilyPlnAmt" name="FamilyPlnAmt"></hdiits:text>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="FamilyPlannig" name="FamilyPlannig" value="1" onclick="javascript:enableFamilyPln()">
				<b><hdiits:caption captionid="OTHER.FamilyPlanning" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
				<hdiits:text caption="Family Planning" validation="txt.isnumber" style="display:none" id="FamilyPlnAmt" name="FamilyPlnAmt"></hdiits:text>
			</c:otherwise>
			</c:choose>
		</TD>

	</tr>
	<tr>
		<td>
			<c:choose>
			<c:when test="${otherList.isAvailedHRA eq '1'}">
				<input type="checkbox" id="isAvailedHRA" name="chkBxIsAvailedHRA" value="1" checked onclick="javascript:toggleIsAvailedHRA()">
				<b><hdiits:caption captionid="OT.IsAvailedHRA" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="isAvailedHRA" name="chkBxIsAvailedHRA" value="0" onclick="javascript:toggleIsAvailedHRA()">
				<b><hdiits:caption captionid="OT.IsAvailedHRA" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<!-- added by manoj for tra mapping -->
	<tr  style="display:none">
			<td colspan="5" >
			<c:choose>
			
			<c:when test="${hrEmpTraMpg.id ne 0}">
				<input type="checkbox" name="taAvailed" id="taAvailed" value="<c:out value="${hrEmpTraMpg.id}"/>" checked>
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="taAvailed" id="taAvailed" value="0">
			</c:otherwise>
			</c:choose>
				
				
			</td>
	</tr>
	<tr id="traTable" style="display:none">
	<td colspan="5">
		<table  width="100%" >
		<tr>
			<td>
				<jsp:include page="/WEB-INF/jsp/hrms/eis/HrEmpTraMpgMaster.jsp">
					<jsp:param name="distance" value="${hrEmpTraMpg.distance}"/>
					<jsp:param name="vehicalAvailed" value="${hrEmpTraMpg.vehicalAvailed}"/>
					<jsp:param name="vehiNo" value="${hrEmpTraMpg.vehicalNo}"/>
					<jsp:param name="vehicalAvailDate" value="${hrEmpTraMpg.vehicalAvailDate}"/>
					<jsp:param name="vehicalLeaveDate" value="${hrEmpTraMpg.vehicalLeaveDate}"/>
				</jsp:include>
			</td>
		</tr>
		</table>	
	</td>
	</tr>
	<%-- Ended By manoj for tra mapping --%>
	
	</table>
	<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>
	<div align="center">
    <hdiits:formSubmitButton name="Update" id="Update" type="button" caption="Update" onclick="submitForm()"/>
     <hdiits:button name="btnClose1" type="button" captionid="eis.close" bundle="${Lables}" onclick="onclosefn()" />    
    </div>
	<hr color="blue" size="4">
	<span style="background-color: #ffffee">
		<jsp:include page="/WEB-INF/jsp/hrms/eis/empAllowMapView.jsp">
	  		<jsp:param name="empId" value="${otherList.hrEisEmpMst.orgEmpMst.empId}" />
	    	<jsp:param name="otherId" value="${otherList.otherId}" />
	    	<jsp:param name="updatePaybillFlg" value="${updatePaybillFlg}" />
	    	<jsp:param name="paybillMonth" value="${paybillMonth}" />
	    	<jsp:param name="paybillYear" value="${paybillYear}" />
	</jsp:include>
	</span>
	
	

	</div>
	<script type="text/javascript">
		
	    
		initializetabcontent("maintab")
		
		if("${msg}"!=null&&"${msg}"!='' && "${empAllRecOther}"=='false')
		{
		
			alert("${msg}");
			var url="./ifms.htm?actionFlag=getOtherData&otherId="+${otherId}+"&edit=Y&empAllRec=false";
			document.frmOtherMst.action=url;
			document.frmOtherMst.submit();
		}	
		else if("${msg}"!=null&&"${msg}"!=''&& "${empAllRecOther}"=='true' )
		{
	
            alert("${msg}");
			var url="./ifms.htm?actionFlag=getOtherData&empId=${empId}&empAllRec=true&otherId="+${otherId}+"&edit=Y";
		
			document.frmOtherMst.action=url;
			document.frmOtherMst.submit();
		}
	
 		 disableFields('${empType}'); 
 		 document.getElementById("initialBasic").focus();	
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
	
	
</hdiits:form>
</body>


<%
		} catch (Exception e) {
			e.printStackTrace();

	}
%>