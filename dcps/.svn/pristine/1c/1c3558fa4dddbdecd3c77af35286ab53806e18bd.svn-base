<html>
<head>
<%
try {
%> 
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="common/script/commonfunctions.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.schedulerList}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="deptList" value="${resValue.deptList}"></c:set>
<c:set var="billList" value="${resValue.billList}"></c:set>
<c:set var="listSize" value="${resValue.listSize}" > </c:set>
<c:set var="duplicateMsg" value="${resValue.duplicateMsg}" > </c:set>


<script>


function validateForm()
{
 var uri = "./hrms.htm?actionFlag=";
 var url = uri + document.scheduler.txtAction.value;

 document.scheduler.action = url;
 document.scheduler.submit();
}

if('${msg}'!= null && '${msg}'!='')
	//alert('${msg}');
</script>
<script>

function getBillData()
{ 
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&LocationCode='+ document.forms[0].cmbDept.value;
		 // alert('xml doc ' + url);	
		  var actionf="BillnoFromDept"
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 		 
          		  		  
			xmlHttp.onreadystatechange=fillAllComboBox;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
  }

function clearBillNoCombo()
{	
	var v=document.getElementById("billNoList").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("billNoList").options.length -1;
			document.getElementById("billNoList").options[lgth] = null;
	}		
}

function fillAllComboBox()
{

if (xmlHttp.readyState==complete_state)
 { 						

	clearBillNoCombo()
					var XMLDoc=xmlHttp.responseXML.documentElement;
					//alert('xml doc ' + XMLDoc);			
                    var namesEntries = XMLDoc.getElementsByTagName('LocationCode');
					//alert('namesEntries ' + namesEntries.length)
                  // alert('succeed');
					var billNoListObject = document.getElementById('billNoList');
					for(var k = 0; k < namesEntries.length;k++)
					{
	                   var y1 = document.createElement('option');  
	                   val=namesEntries[k].childNodes[0].text;    
    				    text = namesEntries[k].childNodes[1].text; 
     			        y1.value=val;
     			        y1.text=text;	
     			        try
   				        {      				    					
     			        	billNoListObject.add(y1,null);
           			    }
 				        catch(ex)
   				        {
 				        	billNoListObject.add(y1); 
   			   	        }	
                    }                                                        	
  }
 
}





function addDeptDataToTable()
{

	
		//alert('inside addDeptDataToTable()');

		//-- Send Scheduler information to VoGen --
		var loanDataForVOGEN = new Array('cmbDept','billNoList','Day');
		addOrUpdateRecord('addRecord', 'addMultipleData', loanDataForVOGEN);

	
	
}//end function: addDeptDataToTable()


function validateAddForm()
{
	if(document.getElementById("cmbDept").value == "-1"){
	   // alert("Enter the Department");
	    document.scheduler.cmbDept.focus();
    	return false;
    }

    if(document.getElementById("billNoList").value == "-1"){
	   // alert("Enter the Bill Number");
	    document.scheduler.billNoList.focus();
    	return false;
    }
    if(document.getElementById("Day").value == ''){
	    //alert("Enter the Day");
	    document.scheduler.Day.focus();
    	return false;
    }
    valid=true;
    return true;
}

function validateOnSave()
{

	Rowlength = document.getElementById("tblempLoan").rows.length - 1;
//	alert("sdbvfmhj..."+Rowlength);
	if(Rowlength == 0)
	{
		return true;
	}
	else
	{
		return false;
	}

	
	/*if(document.getElementById("cmbDept").value != "-1" && document.getElementById("billNoList").value != "-1" && document.getElementById("Day").value != ''){
	  return true;
    }

    
   return false;*/
    
}



function addRecord(){

	//alert("addRecord() called: ");	

    if(validateAddForm())
    {
	var cmbDept=document.scheduler.cmbDept.value;
	var billNoList=document.scheduler.billNoList.value;
	var Day=document.scheduler.Day.value;
	//varun
 	if (xmlHttp.readyState == 4)
	 { 	
 		//alert('checking for xmlHttp.readystate = 4');

 		//-- Add data to table --
		var schedulerDataForTableDisplay = new Array('cmbDept','billNoList','Day');

		//alert("trying to execute addDataInTable() ");
		var rowId = addDataInTable('tblempLoan','encXML',schedulerDataForTableDisplay,'','deleteRecord');

		
		resetFields();
	    return true;
	  }
    }
}//end method: addRecord()
function resetFields(){
    
	document.scheduler.cmbDept.value="-1";
	document.scheduler.billNoList.value="-1";
	document.scheduler.Day.value='';
	
}

function checkForm()
{
	   
        if(validateOnSave())
        {
           
            	//alert("Please add row");
            	return false;
        }
       
	
	return true;
	
}

<%--
function checkbillunique()
{
	//var billNoList=document.scheduler.billNoList.value;
	//var location_Id=document.scheduler.cmbDept.value;
	xmlHttp=GetXmlHttpObject();
	  if (xmlHttp==null)
	  {
		  alert ("Your browser does not support AJAX!");
		  return;
	  } 
	
	  var url; 
	  var uri='';
	  url= uri+'&LocationCode='+ document.forms[0].cmbDept.value+'&billId='+document.forms[0].billNoList.value;
	  alert("url" + url);
	  var actionf="checkbillnounique"
	  uri='./hrms.htm?actionFlag='+actionf;
	  url=uri+url; 
      		  		  
		xmlHttp.onreadystatechange=checkStatus;
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);	
		
	

		}	
		

function checkStatus()
{
	
				if (xmlHttp.readyState==complete_state)
		 		{ 						
							var XMLDoc=xmlHttp.responseXML.documentElement;	
							alert("XMLDoc" + XMLDoc);
							  var namesEntries = XMLDoc.getElementsByTagName('LocationCode');
							  alert("namesEntries...." + namesEntries[0].childNodes[0].text);
								if(namesEntries[0].childNodes[0].text=='true')
								{
								//added by ravysh to check number of supplimentary bills
								
								
							
								alert('map already exist');
							    document.getElementById('billNoList').options[0].text = 'Select';
							    document.getElementById('billNoList').value = '-1';
							    document.getElementById('cmbDept').options[0].text = 'Select';
							    document.getElementById('cmbDept').value = '-1';
								clearAllData();
								clearGradeDsgnListBox();
								
							}
								     
								}        
		
	
}
		
		
}
--%>

function checkbillunique()
{
	var billId=document.scheduler.billNoList.value;
	var LocationCode=document.scheduler.cmbDept.value;
	
	
	if(billId!=""&&LocationCode!="")
	{
	try
   	{   
   		xmlHttp=new XMLHttpRequest();    
   	}
	catch (e)
	{    // Internet Explorer    
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
		        alert("Your browser does not support AJAX!");        
		        return false;        
		    }
		}
	}
	//var schedulerId=0;
	
	
    var url = "hrms.htm?actionFlag=checkbillnounique&billId="+billId+"&LocationCode="+LocationCode;
    //+"&schedulerId="+schedulerId;  
//alert("url" + url);
    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				var XMLDoc=xmlHttp.responseXML.documentElement;
				//alert("mpgflag" + XMLDoc);
				var mpgflag = XMLDoc.getElementsByTagName('LocationCode');	
				//alert("mpgflag1" + mpgflag.length);
				if(mpgflag.length != 0)
				{
					if(mpgflag[0].childNodes[0].text!='null'&&mpgflag[0].childNodes[0].text!='-1')
					{			
						alert("Mapping already Exists");
						document.scheduler.billId.value = '';
						document.scheduler.billId.focus();
						chkflag=0;
					}
					else
					chkflag=1;
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}

}
function validateForm()
{
	


 var uri = "./hrms.htm?actionFlag=";
 var url = uri + document.multipleAddScheduler.txtAction.value;

 document.multipleAddScheduler.action = url;
 document.multipleAddScheduler.submit();
}
if('${msg}'!= null && '${msg}'!='')
	//alert("Record " + '${msg}');

</script>
<%--
<script>alert('Now ur going to scheduler entry JSP');</script>  --%>

<%--
<hdiits:form name="scheduler" validate="true" method="POST"	action="hrms.htm?actionFlag=scheduler&edit=N" encType="multipart/form-data">
--%>

<hdiits:form name="scheduler" validate="true" method="POST"	action="hrms.htm?actionFlag=multipleAddScheduler" encType="multipart/form-data">

 <c:if test="${duplicateMsg ne null}">
 <script> alert(${duplicateMsg}); </script>
 </c:if>
 

   	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="SR.SchedulerEntry" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	
<div class="halftabcontentstyle">
	<div id="tcontent1" class=halftabcontent tabno="0">
	<br> <br> <br>	<br> 
	<table align="center" cellsapcing="2" cellpadding="2">
	
		 <TD  align="left" class="Label" width="15%">
		 <b><fmt:message key="SR.Dept" bundle="${commonLables}"/></b></TD>
    	<TD	width ="55%"><hdiits:select style="width:70%"  name="cmbDept" size="1" sort="false" caption="Department" captionid="Dept" mandatory="true" onchange="getBillData();" >
	    <hdiits:option value="-1">-------------------------Select---------------------------</hdiits:option>
	  			<c:forEach items="${deptList}" var="deptList">
	    		   <hdiits:option value="${deptList.locationCode}"> ${deptList.locName} </hdiits:option>
	    		</c:forEach>
	   	</hdiits:select>
	   </TD>  
	   
<br><br>

<c:if test="${billList ne null}">
	<tr>
	<TD  align="left" class="Label"> <b><fmt:message key="SR.BillNo" bundle="${commonLables}"/></b></TD>

	<td>
	<hdiits:select name="billNoList" size="1" id="billNoList" sort="false"  caption="BillNo" captionid="billNo" mandatory="true" onchange="checkbillunique();"   >
	   <hdiits:option value="-1"> ----Select---- </hdiits:option>
	    
	   </hdiits:select>
	  </td>
	  </tr>
	 </c:if>
	 
<br><br>

<tr>
	<TD  align="left" class="Label"> <b><fmt:message key="SR.Day" bundle="${commonLables}"/></b></TD>
		<td align = "left">
			<hdiits:select name="Day" size="1" sort="false" caption="Day" id="Day"  mandatory="true" > 
					<hdiits:option value="">----Select----</hdiits:option>
					<%
					int i=1;
					for(i=1;i<=31;i++) {
						pageContext.setAttribute("counter",i);
					%>															
					<hdiits:option value="${counter}"> ${counter}</hdiits:option>
					<%
					}
					pageContext.removeAttribute("counter");
					%>
					
					
					
						   </hdiits:select>
        </td>
   	  </tr>
		</table>
	
 
 	
 	</div>
 	<hdiits:hidden default="ListPageOfSchedulerDetails" name="givenurl"/> 
 	<td width="30%"><hdiits:hidden name="Counter" captionid="Counter" default="0"></hdiits:hidden></td>
 	
 	<table id="btnAdd" style="" align="center">
		<tr>
			<td class="fieldLabel" align="center" colspan="4">
				<hdiits:button  name="add"  type="button"  caption="Add" onclick="addDeptDataToTable()"></hdiits:button>
			</td>
		</tr>
	</table>
	
	<table id="tblempLoan" border="1" bgcolor="ffffff" bordercolor="aaaaaa" align="center">
 			<tr>
 				
 			<%--	<td align="center">Sr.No</td>  --%>
 				<td align="center">Department</td>
 				<td align="center">Bill No</td>
 				<td align="center">Day</td>
 				<td align="center"><hdiits:hidden name="recCounter" id="recCounter" default="0"/> </td>
 			</tr>
 		</table>      
	
	
	
	
	
	
	
	<hdiits:jsField  name="validate" jsFunction="checkForm()" /> 
	
<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
		
		<script type="text/javascript">
		initializetabcontent("maintab");	 
	</script>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</html>
	