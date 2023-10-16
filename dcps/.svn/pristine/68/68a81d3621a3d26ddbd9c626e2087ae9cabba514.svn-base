
<%@page import="com.ibm.icu.text.SimpleDateFormat"%><html>
<head>
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.actionList}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<!-- Start Added By Urvin Shah for Fetching the Department List -->
<c:set var="deptList" value="${resValue.deptList}" ></c:set>
<c:set var="locationCode" value="${resValue.locationCode}" ></c:set>
<c:set var="GRTYPEList" value="${resValue.GRTYPEList}" ></c:set>

<!-- started By roshan kumar-->
<c:set var="talukaList" value="${resValue.talukaList}" ></c:set>
<c:set var="talukaId" value="${resValue.talukaId}" ></c:set>
<c:set var="ddoSelected" value="${resValue.ddoSelected}" ></c:set>
<!-- started By roshan kumar-->
<!-- Ended By Urvin Shah -->

<script type="text/javascript">
function filterInstitute(){
	var ddoCode= document.getElementById("ddoCode").value;
	var taluka= document.getElementById("cmbTaluka").value;
	var url;

		url="./hrms.htm?actionFlag=getOrderData&edit=N&elementId=9000189&taluka="+taluka+"&ddoCode="+ddoCode;
		document.frmOrderMaster.action= url;
		document.frmOrderMaster.submit();
}

function validateForm()
{
  	
 document.frmOrderMaster.action ="./hrms.htm?actionFlag=AddOrderData&edit=N";
 document.frmOrderMaster.submit();
}

// Start By Urvin Shah.

function chkDate()
{
	
	if(document.frmOrderMaster.txtEndDate.value!=null && document.frmOrderMaster.txtEndDate.value!="")
	{	
		var diff = compareDate(document.frmOrderMaster.txtStartDate.value,document.frmOrderMaster.txtEndDate.value);
		//alert('diff'+ diff);
		
		 if(diff<0)
		 {
	    	//alert("System should allow Order End Date Greater than or Equal to Order date");
	    	alert(" End Date Should e Greater than or Equal to Order date");
	    	frmOrderMaster.txtEndDate.value=''; 
	    	frmOrderMaster.txtEndDate.focus();
	    	//document.getElementById("endDate").focus();	
	    	return false;
	    }
		 else if(diff==0)
		 {
			// alert(" diff is zero");
			 
		 }

		 else
		 {

			 alert("System allows Order End Date Greater than or equal to Order date");
			
			 
		 }
		 

		
		
		
		
	}
	return true;    
}

// End By Urvin Shah.
function checkAvailability()
{

	var newOrderName=trim(document.frmOrderMaster.orderName.value);
	var dept=document.frmOrderMaster.cmbDept.value;
	var orderDate=document.frmOrderMaster.txtStartDate.value;
	document.getElementById("depLoc").value=dept;
	if(newOrderName!=""&&dept!=""&&orderDate!="")
	{
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		var url = "hrms.htm?actionFlag=checkOrderNameAvailability&newOrderName="+newOrderName+"&dept="+dept+"&date="+orderDate;  	


    xmlHttp.onreadystatechange = function() {	

		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var orderNameMapping = XMLDocForAjax.getElementsByTagName('orderNameMapping');	

				var flag="true";				
				if(orderNameMapping.length != 0) {		

						if(orderNameMapping[0].childNodes[0].text==flag)
						{			
							alert("Order Name is already Exists, Please Enter other Name");
							document.frmOrderMaster.orderName.value='';
							document.frmOrderMaster.orderName.focus();
						;	
					    	return false;
						}
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
	
 function addOrderData()
 {
 	var displayFieldArray = new Array('cmbDept','orderName','txtStartDate','txtEndDate','cmbgrtype');
	addOrUpdateRecord('addRecordInTable', 'multipleAddOrderData', displayFieldArray );		
 }
 
 function addRecordInTable()
 {
 		if(document.frmOrderMaster.cmbDept.value=="-1")
 		{
 			alert('Please Select Department');
 			document.frmOrderMaster.cmbDept.focus();
 			return false;
 		}
 		else if(document.frmOrderMaster.orderName.value==null || document.frmOrderMaster.orderName.value=="")
 		{
 			alert('Please Enter Order No');
 			document.frmOrderMaster.orderName.focus();
 			return false;
 		}
 		else if(document.frmOrderMaster.txtStartDate.value==null || document.frmOrderMaster.txtStartDate.value=="")
 		{
 			alert('Please Enter Start Date');
 			document.frmOrderMaster.txtStartDate.focus();
 			return false;
 		}
 		if(document.frmOrderMaster.cmbgrtype.value=="-1")
 		{
 			alert('Please Select GR Type');
 			document.frmOrderMaster.cmbgrtype.focus();
 			return false;
 		}
 		else{
 		if (xmlHttp.readyState == 4)
	  	{ 	
	  	  	var displayFieldArray;
		  	displayFieldArray = new Array('cmbDept','orderName','txtStartDate','txtEndDate','cmbgrtype');
		  	//var attch_chk = mandatory_Attachment('CommAttachment','Please Add Attachment');
		  	//if(attch_chk){
		  		var rowNum = addDataInTableAttachment('orderDataTable', 'encXML', displayFieldArray, '','deleteRecord');				
		   		
		   		resetFormData(rowNum);	
		   //}
		    
		}
		}	
 }
 
 function resetFormData(rowNum)
 {
 	document.forms[0].cmbDept.value="-1"; 
    document.forms[0].orderName.value=""; 
    document.forms[0].txtStartDate.value=""; 
    document.forms[0].txtEndDate.value="";
    removeRowFromTableorderId(rowNum); 
    
 }
 
 function chkDataInTable()
 {
 	var tableLength = document.getElementById('orderDataTable').rows.length;
 	if(tableLength > 1)
 		return true;
 	else 
 	{
 		alert('Please add record');
 		return false;
 	}
 }
</script>


<hdiits:form name="frmOrderMaster" validate="true" method="POST" 
	action="javascript:validateForm()" encType="multipart/form-data">
	
	
	<%--Added by roshan --%>
	<fieldset class="tabstyle"><legend> <b>Filter Institute</b> </legend>
<table align="center">
<tr>
<td><c:out value="Taluka"></c:out></td>

<td><select name="cmbTaluka"
			id="cmbTaluka" style="width: 85%,display: inline;">
			<option title="Select" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="talukaList" items="${talukaList}">
			<c:choose> 
			<c:when test="${talukaId==talukaList[0]}">
							<option value="${talukaList[0]}" title="${talukaList[1]}" selected="selected">
						<c:out value="${talukaList[1]}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${talukaList[0]}"/>" title="${talukaList[1]}">
						<c:out value="${talukaList[1]}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
		</select></td>


<td><c:out value=" DDO Code/Office Name"></c:out></td>
<td>
<c:choose>
<c:when test="${ddoSelected!=null}">
<!--<hdiits:text id = "ddoCode" name="ddoCode" captionid="${ddoSelected}" validation="" maxlength="200" size="30"
			 default=""/>
-->
<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" value="${ddoSelected}" size="30"/>
</c:when>
<c:otherwise>
<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" size="30"/>
</c:otherwise>
</c:choose>			 
</td>


		</tr>
	<tr>	
<td colspan="4" align="center"><input id="btnFilter" class="buttontag" type="button" size="5" maxlength="5"
		value="Filter" onclick="filterInstitute();"
		name="btnFilter" style="width: 120px;" /></td>
</tr>
</table>
</fieldset>
<%--Added by roshan --%>

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
	
      <li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OM.insertOrderMaster" bundle="${commonLables}"/></b></a></li>
    	<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
	</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	<br/> <br/>  
	<table align="center" cellsapcing="2" cellpadding="2">
	<!-- Start Added By Urvin Shah for Fetching the Department List -->
	<tr>
		<td   class="Label" >
		
		 <b><fmt:message key="PR.DEPT" bundle="${commonLables}"/></b></TD>
		<td	>
		<select name="cmbDept" size="1" id="cmbDept" onchange="checkAvailability()">
	   <option value="-1">-------------------Select---------------------</option>
	  
	     		<c:forEach items="${deptList}" var="deptList">
	     			<c:choose>
	     			<c:when test="${ddoSelected==deptList[2]}">
	     				<option selected="selected" value="${deptList[0]}">${deptList[1]}(${deptList[2]})</option>
	     				
	     			</c:when>
	     			<c:otherwise>
	     			 	<option value="${deptList[0]}" title="${deptList[1]}(${deptList[2]})">${deptList[1]}(${deptList[2]})</option>
	     			 </c:otherwise>
	     			</c:choose>
	    			 </c:forEach>
	   	</select>
	   	<input type="hidden" name="depLoc" value="-1" id="depLoc">
	</td>
	</tr>
	<!-- Ended By Urvin Shah -->
		<tr>
			<td><b><fmt:message key="OM.orderName" bundle="${commonLables}"/><b></td>
			<td><hdiits:text id = "orderName" name="orderName" caption="Order Name" mandatory="true" validation="" maxlength="50" size="30"
			onblur="checkAvailability()" default=""  onkeypress="if(event.keyCode == 13) event.returnValue = false;"/></td></tr><br>
		<tr>
		<td><b><fmt:message key="ST.DATE" bundle="${commonLables}"/><b></td>
		<td><hdiits:dateTime caption="ST.DATE"  name="txtStartDate" mandatory="true"  onblur="checkAvailability()" validation="txt.isdt" bundle="${commonLables}"/> </td>
		</tr>
				<%-- <tr>
		<td><b><fmt:message key="OM.endDate" bundle="${commonLables}"/><b></td>
		<td><hdiits:dateTime caption="OM.endDate" maxvalue="" name="txtEndDate" onblur="chkDate()" validation="txt.isdt" bundle="${commonLables}" /> </td>
		
		</tr>--%>
		<tr>
		<td>
		<input type="hidden" name="txtEndDate" id="txtEndDate"/>
		<b><fmt:message key="PR.GRTYPE" bundle="${commonLables}"/><b></td>
		<td	>
		<hdiits:select name="cmbgrtype" id="cmbgrtype" sort="false" size="100px" caption="PR.GRTYPE" captionid="PR.GRTYPE" >
	<%--  <hdiits:option value="-1">----Select----</hdiits:option> --%> 
	  
	     		<c:forEach items="${GRTYPEList}" var="GRTYPEList">
	     		
	     		<c:if test="${GRTYPEList.lookupShortName==6}">
	     			<option value="<c:out value="${GRTYPEList.lookupShortName}"/>">
					<c:out value="${GRTYPEList.lookupName}"/></option>
					</c:if>
	    			 </c:forEach>
	   	</hdiits:select>
	</td>
		</tr>
	</table>
	<table align="center" border="1">
		<tr>
			<td colspan="10"><b>Order Upload:-</b>
					   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="orderId" />
            	    		<jsp:param name="formName" value="frmOrderMaster" />
	            	    	<jsp:param name="attachmentType" value="Document" />
				    		<jsp:param name="multiple" value="Y" />  
				    		<jsp:param name="mandatory" value="N"/>   
				    		<jsp:param name="rowNumber" value="1" />            
	    				</jsp:include>
			</td>
		</tr>		
	</table>
	
	<table id="btnAdd" style="" align="center">
			<tr>
				<td class="fieldLabel" align="center" colspan="4"><hdiits:button  name="add"  type="button"  caption="Add" onclick="addOrderData()"></hdiits:button></td>
			</tr>
		</table>
		
		<table id="orderDataTable" border="1" bgcolor="ffffff" bordercolor="aaaaaa" align="center">
 			<tr>
 				
 				<td align="center"><b>Department</b></td>
 				<td align="center"><b>Section Order No</b></td>
 				<td align="center"><b>Order Date</b></td>
 				<td align="center"><b>Order End Date</b></td>
 				<td align="center"><b>GR Type</b></td>	
 				<td align="center"><b>Delete</b></td>			
 			</tr>
 		</table>
	
 	</div>
 	
 	 <br/>
 	<div>
 	<hdiits:hidden default="getOrderData" name="givenurl"/>
 	
 	 <fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>
     <jsp:include page="../../core/PayTabnavigation.jsp" />
     </div>
    	<hdiits:jsField  name="validate" jsFunction="chkDataInTable()" />
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		document.frmOrderMaster.cmbDept.focus();
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getOrderData";
			document.frmOrderMaster.action=url;
			document.frmOrderMaster.submit();
		}
		</script>
		
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</html>