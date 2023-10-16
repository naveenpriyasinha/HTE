<html>
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
<c:set var="cmnLocationMst" value="${resValue.cmnLocationMst}" ></c:set>
<!-- Ended By Urvin Shah -->

<script>

// Added By Urvin shah.

function chkDate(){
	if(document.frmOrderMaster.txtEndDate.value!=null && document.frmOrderMaster.txtEndDate.value!="")
	{
	var diff = compareDate(document.frmOrderMaster.txtStartDate.value,document.frmOrderMaster.txtEndDate.value);
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

// End Urvin Shah

function checkAvailability(newOrderName)
{
	var newOrderName=newOrderName.value;
	var oldOrderName="${actionList.orderName}";	

	if(newOrderName!="")
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
	//    var url = "hrms.htm?actionFlag=getDesigData&editdesig=${desg_name}&&chk=1&dname="+dname;  
   			if(newOrderName==oldOrderName) {	
				return true;
			}
			else{
				//alert("Not eqal");
				var url = "hrms.htm?actionFlag=checkOrderNameAvailability&newOrderName="+newOrderName;  	
			}
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
							document.frmBF.orderName.value='';
							document.frmBF.orderName.focus();
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

function chkOrderName()
{ 
  if(!trim(document.frmOrderMaster.orderName.value)== '')
  {
  if(!checkSplChars(document.frmOrderMaster.orderName.value)/* || (checkAlpha(document.frmBankMaster.bankName.value)==false)*/)
  {
   alert('Order Name Contains Invalid Characters. Try Again.');
   document.frmOrderMaster.orderName.value = '';
   document.frmOrderMaster.orderName.focus();
  }
  else
  {
  var name =document.frmOrderMaster.orderName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  if(${actionList.orderName eq 'null'} || ${actionList.orderName eq ''})
  		   url= uri+'&orderName='+ document.frmOrderMaster.orderName.value;
  		  else		  
		   url= uri+ '&oldname=${actionList.orderName}&orderName=' + document.frmOrderMaster.orderName.value;
		  var actionf="chkOrderName";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
 //        alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=chk_orderName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
  }
}
}

function chk_orderName()
{
if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('order-name');
   //                 alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
                    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
                    {                    
                     alert('Order Name already exists.');
                     document.frmOrderMaster.orderName.value = '';
                     document.frmOrderMaster.orderName.focus();
                    }
  }
}

function validateForm()
{
 var uri = "./hrms.htm?actionFlag=";
 var url = uri + document.frmOrderMaster.txtAction.value;

 document.frmOrderMaster.action = url;
 document.frmOrderMaster.submit();
}

</script>
<c:set var="a" value="x"></c:set>
<c:set var="b" value="x"></c:set>

<hdiits:form name="frmOrderMaster" validate="true" method="POST" 
	action="./hrms.htm?actionFlag=AddOrderData&edit=Y&orderId=${actionList.orderId}" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OM.updateOrderMaster" bundle="${commonLables}"/></b></a></li>
			<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	<br> <br> <br>	
	<table align="center" cellsapcing="2" cellpadding="2">
	<tr>
			<td><!--<b><hdiits:caption captionid="BM.bankId" bundle="${commonLables}"/></b>-->&nbsp;</td>
			<td><hdiits:hidden name="orderId" caption="orderId"  style="BACKGROUND:GRAY" default ="${actionList.orderId}"/></td>
	</tr>
	<!-- Start Added By Urvin Shah for Fetching the Department List -->
	<tr/><tr/>
		<tr>
			
			<td	>
			<hdiits:select name="cmbDept" size="1" sort="false" caption="Department" captionid="Dept" validation="sel.isrequired" mandatory="true" onchange="GetDemandNo()" >
		    <hdiits:option value="-1">-------------------Select---------------------</hdiits:option>
		    	<c:forEach items="${deptList}" var="deptList">	    		 
				 
				 <c:choose>
	    		   <c:when test="${deptList.locId == cmnLocationMst.locId}">
		   		 	 <hdiits:option value="${deptList.locationCode}" selected="true" > ${deptList.locName} </hdiits:option>
		   		  </c:when>
		   		  <c:otherwise> 
		   		 	 <hdiits:option value="${deptList.locationCode}" > ${deptList.locName} </hdiits:option>
-   		   	  </c:otherwise> 	 	 	 
	   		 	 </c:choose>  
		    	</c:forEach>
		   	</hdiits:select>
		</td>
		</tr>
		<tr/><tr/>
		<!-- Ended By Urvin Shah -->
		
		<tr>
			<td><b><fmt:message key="OM.orderName" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="orderName" caption="Order Name"  maxlength="50" mandatory="true" validation="txt.isrequired" default ="${actionList.orderName}" 
			onblur="checkAvailability(this)" title ="${actionList.orderName}" onkeypress="if(event.keyCode == 13) event.returnValue = false;"/></td>
		</tr>
		<tr/><tr/>	    
		<tr>
			<td><b><fmt:message key="ST.DATE" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime  caption="ST.DATE" name="txtStartDate" bundle="${commonLables}"  mandatory="true" default="${actionList.orderDate}" validation="txt.isrequired,txt.isdt" /></TD>	
		</tr>
		<tr/><tr/>
		<tr>
		<td><b><fmt:message key="OM.endDate" bundle="${commonLables}"/><b></td>
		<td><hdiits:dateTime caption="OM.endDate" maxvalue=""  name="txtEndDate" onblur="chkDate()" validation="txt.isdt" default="${actionList.endDate}" bundle="${commonLables}"/> </td>
		</tr>
		<tr/><tr/>
		</table>
		<table align="center" border="1" width="40%">
			<tr>
				<td colspan="10" ><b>Uploaded Order:-</b>
		 			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
            	    	<jsp:param name="attachmentName" value="orderId" />
                		<jsp:param name="formName" value="frmOrderMaster" />
	                	<jsp:param name="attachmentType" value="Document" />
				    	<jsp:param name="multiple" value="N" />  
				    	<jsp:param name="readOnly" value="Y"/>
				    	<jsp:param name="removeAttachmentFromDB" value="Y"/>     					    	         
    				</jsp:include>
				</td>
			</tr>
		</table>
		<br/><br/>
	</div>
	<br/><br/>
 	<div>
 	 	 	<hdiits:hidden default="getOrderData" name="givenurl"/>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/> 
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
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
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


</html>