<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.tcs.sgv.ess.valueobject.*" %>
<html>
<head>


<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="empActData" value="${resultValue.empActData}" > </c:set>
<c:set var="empDctData" value="${resultValue.empDctData}" > </c:set>
<c:set var="empActDataSize" value="${resultValue.empActDataSize}" > </c:set>
<c:set var="empDctDataSize" value="${resultValue.empDctDataSize}" ></c:set>
<c:set var="msg" value="${resultValue.msg}" > </c:set>
<c:set var="action" value="${resultValue.action}" ></c:set>
<c:set var="billNoList" value="${resultValue.billNoList}" ></c:set>


<c:if test="${action eq 'a'}">
<c:set var="list" value="${resultValue.empActData}" ></c:set>
<c:set var="listSize" value="${resultValue.empActDataSize}" ></c:set>
</c:if>

<c:if test="${action eq 'd'}">
<c:set var="list" value="${resultValue.empDctData}" ></c:set>
<c:set var="listSize" value="${resultValue.empDctDataSize}" ></c:set>
</c:if>



<script language="javascript">


function getUpdatation()
{
	
	var postId=getSelectedPostId();
	document.getElementById('allPostId').value=postId;
	//alert(postId);
	var startDate=getSelectedStartDate();
	document.getElementById('allStartDates').value=startDate;
	
	var endDate= getSelectedEndDate();
	document.getElementById('allEndDates').value=endDate;
	
	var dates=checkDates();
	
	var length = '${empActDataSize}';
	
	if(postId != "-1" && postId != false && startDate!="-1" && startDate!=false && endDate!="-1" && endDate!=false && dates!=false && dates!="-1")
	{
		var answer = confirm (" Are You sure, to DEACTIVATE this Bill(s)?");
		if(answer)
		{	
				//alert("./hrms.htm?actionFlag=deactivatePayBill&length="+length+"&Flag=A");
				document.UpdatePayBillStatus.action = "./hrms.htm?actionFlag=deactivatePayBill&Flag=D";// D= Deactivate
				document.UpdatePayBillStatus.submit();
				document.UpdatePayBillStatus.Deactivate.disabled=true; 
				showProgressbar("Please wait<br>While Deactivating Bill(s) ...");
		}
	}
	else
	{
	//alert("Select any checkbox and fill proper start date and end date");
		}
}

function getSelectedPostId()
{
	//alert("called 1 1");
	var length = '${empActDataSize}';
	 var postId ; //document.getElementById("tnrbillnoId").value;//this will give you the bill sub head id of selected bill number
		//alert("called 1 2");
	 var endDate;
	 var check = true;
	 var str;
	 var str1;
	 var chk;
	 //alert(length);
	 if(length == 0)
	 {
	 	alert("No Records to Save ! ! !");
	 	return false;
	 }
	
	 for(var i = 1; i <= length ; i++)
	 {
		 
		 str='DOS'+i;
		 str1='DOE'+i;
		 chk='chk'+i;
		 //alert(chk);
		 if(document.getElementById(chk).checked) 
		 {
			 //alert("called 1 3 "+i);
			 check = false;
			 if(postId == null)
				 postId=document.getElementById("hdnPostId"+i).value;
			 else
				 postId+="," + document.getElementById("hdnPostId"+i).value;

			 
			 //alert("called 1 4 "+i);
		 }
	 }
	 //alert("finished");
	 if(check)
	 {
		 //alert("check=true");
		 alert("Please Select At Least One Check Box And Fill Start Date and End Date");
		 return false;
	 }	
	 //alert(postId);
	 return postId;
}

function getSelectedStartDate()
{
	 var startDate;
	 var check = true;
	 var length = '${empActDataSize}';
	 var chk;
	 if(length == 0)
	 {
	 	alert("No Records to Save ! ! !");
	 	return false;
	 }
	 for(var i = 1; i <= length ; i++)
	 {
		 str='DOS'+i;
		 str1='DOE'+i;
		 chk='chk'+i;
		 if( document.getElementById(chk).checked  )
		 {
			 if(document.UpdatePayBillStatus.elements[str].value!="")
			 {
			 	check = false;

			 	if(startDate == null)
				 {
					 startDate=document.UpdatePayBillStatus.elements[str].value;
				 }
			 	else
				 {
					 startDate+="," + document.UpdatePayBillStatus.elements[str].value;
				 }
			 }
			 else
			 {
			 		alert("Fill values in Start date");
					return false;
				}
		 }
		 
	 }
	 if(check)
	 {
		 
		 return false;
	 }	
	// alert(startDate);
	 return startDate;
			
}


function getSelectedEndDate()
{
	 var endDate;
	 var check = true;
	 var chk;
	 var length = '${empActDataSize}';
	 if(length == 0)
	 {
	 	alert("No Records to Save ! ! !");
	 	return false;
	 }
	 for(var i = 1; i <= length ; i++)
	 {
		 str='DOS'+i;
		 str1='DOE'+i;
		 chk='chk'+i;
		 if( document.getElementById(chk).checked )
		 {
			 if(document.UpdatePayBillStatus.elements[str1].value!="")
			 {
			 	check = false;

			 	if(endDate == null)
					 endDate=document.UpdatePayBillStatus.elements[str1].value;
			 	else
					 endDate+="," + document.UpdatePayBillStatus.elements[str1].value;
			 }
			 else
			 {
			 	alert("please fill values in end date");
				return false;
				 }
			 
		 }
		 
	 }
	 if(check)
	 {
		 
		 return false;
	 }	
	// alert(endDate);
	 return endDate;
			
}


function activateDates(field,posId)
{
	
	
		if(document.getElementById("chk"+posId).checked)
		{
			//alert("true");
			var str='DOS'+posId;
			var str1='DOE'+posId;
			//alert(document.UpdatePayBillStatus.elements[str].value);
			//document.UpdatePayBillStatus.elements[str].disabled =true;
			//document.UpdatePayBillStatus.elements[str1].disabled = true;
			//document.UpdatePayBillStatus.elements[str].focus();
			
			
		}
		else
		{
			//alert("false");	
			var str='DOS'+posId;
			var str1='DOE'+posId;
			//document.UpdatePayBillStatus.elements[str].value = "";
			//document.UpdatePayBillStatus.elements[str1].value = "";		
		
			//document.UpdatePayBillStatus.elements[str].disabled =false;
			//document.UpdatePayBillStatus.elements[str1].disabled =false;
			
		
	}
	//alert("postId" + postId);
	
}

function checkDates()
{
var length = '${empActDataSize}';
var str1;
var str;
var chk;
var check=true;
var dos;
var doe;
var currDate= new Date();
var currMonth = currDate.getMonth()+1;
var currYr = currDate.getFullYear();
//alert(currDate);
for(var i = 1; i <= length ; i++)
	 {
		 
		  str='DOS'+i;
		  str1='DOE'+i;
		 chk='chk'+i;
		 //alert(chk);
		 if(document.getElementById(chk).checked) 
		 {
			 //alert("called 1 3 "+i);
			  if(document.UpdatePayBillStatus.elements[str].value!="" && document.UpdatePayBillStatus.elements[str1].value!="")
			 {
			 	dos=document.UpdatePayBillStatus.elements[str].value;
			 	doe=document.UpdatePayBillStatus.elements[str1].value
			 		check = false;
			 		var str1 = dos
    				var str2 = doe
    				var dt1  = parseInt(str1.substring(0,2),10); 
   					 var mon1 = parseInt(str1.substring(3,5),10); 
   					 var yr1  = parseInt(str1.substring(6,10),10); 
    				var dt2  = parseInt(str2.substring(0,2),10); 
   					 var mon2 = parseInt(str2.substring(3,5),10); 
  					  var yr2  = parseInt(str2.substring(6,10),10); 
  					  var date1 = new Date(yr1, mon1, dt1); 
    					var date2 = new Date(yr2, mon2, dt2); 

    			
   				if(currYr==yr1)
   				{
					if(mon1<currMonth)
					{
						alert("Select start date of current month or grater month");
						UpdatePayBillStatus.elements[str].focus();
						return false;
					
					}
				}
				else if(currYr>yr1)
			 	{
			 		alert("Select start date of current month or grater month");
			 		UpdatePayBillStatus.elements[str].focus();
					return false;
			 	}
			 	else{}
			 	
			 	if(date2 < date1) 
    			{ 
    			    alert("From date cannot be greater than To date"); 
    			    UpdatePayBillStatus.elements[str].focus();
     				   return false; 
   				 } 
			 //alert("called 1 4 "+i);
			 }
			 else
			 {
			 	return false;
			 }
	 	}
	
	}
	return true;
}



function getUpdatationActivate()
{

	var postId=getSelectedPostIdForAct();
	document.getElementById("allPostId").value=postId;
	
	//var startDate=getSelectedStartDateForAct();
	//document.getElementById("allStartDates").value=startDate;
	
	//var endDate=getSelectedEndDateForAct();
	//document.getElementById("allEndDates").value=endDate;
	 
	var length = '${listSize}';
	
	if(postId != "-1" && postId != false)
	{
		var answer = confirm (" Are You sure, to ACTIVATE this Bill(s)?");
		if(answer)
		{
			//alert("./hrms.htm?actionFlag=deactivatePayBill&length="+length+"&Flag=A");
			document.UpdatePayBillStatus.action = "./hrms.htm?actionFlag=deactivatePayBill&Flag=A";// A= activate
			document.UpdatePayBillStatus.submit();
			document.UpdatePayBillStatus.Deactivate.disabled=true; 
			showProgressbar("Please wait<br>While Activating Bill(s) ...");
		}
	}
	else
	{
		alert("Select at least one post ");
	}
	
}


function getSelectedPostIdForAct()
{
	//alert("called 1 1");
	var length = '${empDctDataSize}';
	 var postId ; //document.getElementById("tnrbillnoId").value;//this will give you the bill sub head id of selected bill number
		//alert("called 1 2");
	
	 var check = true;
	 
	 var chk;
	 //alert(length);
	 if(length == 0)
	 {
	 	alert("No Records to Save ! ! !");
	 	return false;
	 }
	
	 for(var i = 1; i <= length ; i++)
	 {
		 
		 chk='chkd'+i;
		 //alert(chk);
		 if(document.getElementById(chk).checked) 
		 {
			 //alert("called 1 3 "+i);
			 check = false;
			 if(postId == null)
				 postId=document.getElementById("hdnPostIdD"+i).value;
			 else
				 postId+="," + document.getElementById("hdnPostIdD"+i).value;

			 
			 //alert("called 1 4 "+i);
		 }
	 }
	 //alert("finished");
	 if(check)
	 {
		 //alert("check=true");
		 //alert("Please Select The Check Box And Fill Start Date and End Date");
		 return false;
	 }	
	 //alert(postId);
	 return postId;
}


function getSelectedStartDateForAct()
{
	var startDate;
	 var check = true;
	 var length = '${empDctDataSize}';
	 var chk;
	 if(length == 0)
	 {
	 	alert("No Records to Save ! ! !");
	 	return false;
	 }
	 for(var i = 1; i <= length ; i++)
	 {
		 str='DOS'+i;
		 
		 chk='chk'+i;
		 if( document.getElementById(chk).checked  )
		 {
		 	check=false;
			 if(startDate == null)
				 startDate=document.getElementById("DOS"+i).value;
			 else
				 startDate+="," + document.getElementById("DOS"+i).value;
			 
		 }
		 
		 
	 }
	 if(check)
	 {
		 
		 return false;
	 }	
	// alert(startDate);
	 return startDate;
}


function getSelectedEndDateForAct()
{
	var endDate;
	 var check = true;
	 var length = '${empDctDataSize}';
	 var chk;
	 if(length == 0)
	 {
	 	alert("No Records to Save ! ! !");
	 	return false;
	 }
	 for(var i = 1; i <= length ; i++)
	 {
		 str='DOE'+i;
		 
		 chk='chk'+i;
		 if( document.getElementById(chk).checked  )
		 {
		 	check=false;
			 if(endDate == null)
				 endDate=document.getElementById("DOE"+i).value;
			 else
				 endDate+="," + document.getElementById("DOE"+i).value;
		 }
		 
	 }
	 if(check)
	 {
		 
		 return false;
	 }	
	// alert(startDate);
	 return endDate;
}

function submitToSeach()
{
	
	var act = '${action}';
	
	var url = "./hrms.htm?actionFlag=srchEmpOnBillNo&action="+act;
	
	document.UpdatePayBillStatus.action = url;
	document.UpdatePayBillStatus.submit();
	document.UpdatePayBillStatus.Deactivate.disabled=true; 
	showProgressbar("Searching ...");
}



</script>
</head>



<body>

<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="Selected"><a href="hrms.htm?actionFlag=updatePayBillStatus&action=a" rel="tcontent1"><b>Active Bills</b></a></li>
			<li class="Selected"><a href="hrms.htm?actionFlag=updatePayBillStatus&action=d" rel="tcontent1"><b>Deactivated Bills</b></a></li> 
		</ul>
	</div>
	




<div class="halftabcontentstyle">	  
	<div id="tcontent1" class="halftabcontent">
	





<hdiits:form method="POST" name="UpdatePayBillStatus" validate="true" >
		
		<table  width="85%" align="center" bgcolor="#D0D0D0" name="searchTable" id="searchTable">
<tr><td><h2><b>Search Employee</b></h2></td></tr>
		<tr>
			<td >				
				Employee Name: 
			</td>
			<td>
			<hdiits:text name="empName" onkeypress="if(event.keyCode == 13) { document.getElementById('SearchEmployee').click();}"/>
			</td>
			<td>
				Bill Nunber:
			</td>
			<TD	width ="25%"><hdiits:select style="width:30%"  name="bill" size="1" sort="false" caption="Bill No." captionid="bill"    >
	    <hdiits:option value="-1">----Select----</hdiits:option>
        
		 <c:forEach var="billHeadMpg" items="${billNoList}">
         <hdiits:option value="${billHeadMpg.billHeadId}"><c:out value="${billHeadMpg.billId}"> </c:out></hdiits:option>
         </c:forEach>
		 </hdiits:select > 
	   </TD><td>
	   <hdiits:button name="SearchEmployee" id="SearchEmployee" type="button" caption="Search Employee" onclick="submitToSeach()"/>
	   </td>
		</tr>
		
		
		
	</table>
		
		
		
		
		
	
		<c:if test="${listSize ne 0}">
		<c:set var = "i" value = "1" ></c:set>
		<center>
	<display:table name="${list}" requestURI="" pagesize="${listSize}" id="row" export="true" style="width:85%" >
		
		<display:column class="tablecelltext" title="select" headerClass="datatableheader" > 
		
		<c:if test="${action eq 'a'}">
			<input type="checkbox" name="chk${i}" id="chk${i}" onclick="activateDates(this,'${i}')"/>
			<input type="hidden" name="hdnPostId${i}"  value="${row.postId}" />
		</c:if>
		
		<c:if test="${action eq 'd'}">
			<input type="checkbox" name="chkd${i}" id="chkd${i}" />
			<input type="hidden" name="hdnPostIdD${i}" value="${row.postId}" />
		</c:if>
		
		</display:column>
		
		<display:column class="tablecelltext" title="Post Id" headerClass="datatableheader" value="${row.postId}"></display:column> 
		<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" value="${row.empName}"  > </display:column>	
		<display:column class="tablecelltext" title="Post Name" headerClass="datatableheader" style="text-align:center" value="${row.postName}"></display:column>
		
		<c:if test="${action eq 'a'}">
			<display:column class="tablecelltext" title="Start Date" headerClass="datatableheader"> 
			<hdiits:dateTime name="DOS${i}"  captionid="PBA.startDate" bundle="${commonLables}"  validation="txt.isdt"  caption="PBA.startDate"
			maxvalue="31/12/2099" />
			</display:column>
			<display:column class="tablecelltext" title="End Date" headerClass="datatableheader"> 
			<hdiits:dateTime name="DOE${i}" captionid="PBA.endDate" bundle="${commonLables}" validation="txt.isdt"  caption="PBA.endDate"/> 
			</display:column>
		</c:if>
		
		<c:if test="${action eq 'd'}">
			<display:column class="tablecelltext" title="Start Date" headerClass="datatableheader" >
			<fmt:formatDate type="date"  value="${row.startDate}" />
			
			</display:column>
			<display:column class="tablecelltext" title="End Date" headerClass="datatableheader" >
			<fmt:formatDate type="date"  value="${row.endDate}" />
			</display:column>
		</c:if>
		
		<c:set var="i" value="${i+1}"></c:set>	
	</display:table>
	</center>
	<hdiits:hidden name = "allPostId"  id="allPostId"/>
	<hdiits:hidden name = "allStartDates"  id="allStartDates"/>
	<hdiits:hidden name = "allEndDates"  id="allEndDates"/>
	<center>
	<c:if test="${action eq 'a'}">
	<hdiits:button name="Deactivate" value="Deactivate" caption="Deactivate PayBills" 
	id="Deactivate" captionid="Deactivate Bill" onclick="getUpdatation()" type="button"/>
	</c:if>
	
	<c:if test="${action eq 'd'}">
	<hdiits:button name="Activate" value="Activate" caption="Activate PayBills" 
	id="Aeactivate" captionid="Aeactivate Bill" onclick="getUpdatationActivate()" type="button"/>
	</c:if>
	
	</center>
     </c:if>
	 <c:if test="${listSize eq 0}"><center>
	 <h1>
	 <c:out value=" "></c:out>
	 <c:out value=" "></c:out>
	 <c:out value="No Records to display"></c:out>
	 
	 </h1></center>
	 </c:if>

</hdiits:form>
</div>

</div>




</body>
</html>