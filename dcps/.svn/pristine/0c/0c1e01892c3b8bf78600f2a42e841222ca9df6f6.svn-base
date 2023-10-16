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
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.lstBillDtls}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="deptList" value="${resValue.deptList}"></c:set>
<c:set var="billList" value="${resValue.billList}"></c:set>
<c:set var="day" value="${resValue.day}" > </c:set>
<c:set var="billId" value="${resValue.billId}" > </c:set>
<c:set var="locCode" value="${resValue.locCode}" > </c:set>

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
	//  var actionf="BillnoFromDept"
		 // uri='./hrms.htm?actionFlag="BillnoFromDept";
		  url='./hrms.htm?actionFlag=BillnoFromDept&LocationCode='+ document.forms[0].cmbDept.value; 		 
          //	alert("cccccc"+url);	  		  
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
	clearBillNoCombo();
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









</script>





<hdiits:form name="scheduler" validate="true" method="POST" action="./hrms.htm?actionFlag=scheduler&edit=Y" encType="multipart/form-data">

<%--<hdiits:form name="scheduler" validate="true" method="POST" action="./hrms.htm?actionFlag=scheduler&edit=Y" encType="multipart/form-data">
--%>

<c:if test="${duplicateMsg ne null}">
 <script> alert(${duplicateMsg}); </script>
 </c:if>

	
   	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="SR.SchedulerUpdate" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	
<div class="halftabcontentstyle">
	<div id="tcontent1" class=halftabcontent tabno="0"> 
	<br> <br> <br>	<br> 
	<table align="center" cellsapcing="2" cellpadding="2">
	
	<!--  Abhilash started for update -->
	<c:forEach items ="${actionList}" var="actionList">
<hdiits:hidden  default ="${actionList.schedulerId}" name="txtSchedulerID" caption="Scheduler ID"  /></TD>
</c:forEach>
<!--  Abhilash ended for update -->		



		 <TD  align="left" class="Label" width="15%">
		 <b><fmt:message key="SR.Dept" bundle="${commonLables}"/></b></TD>
    	<TD	width ="55%"><hdiits:select style="width:80%"  name="cmbDept" size="1" sort="false" caption="Department" captionid="Dept" validation="sel.isrequired" mandatory="true" onchange="getBillData();" >
	    <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			<c:forEach items ="${deptList}" var="deptList">
			<c:choose>
			<c:when test="${deptList.locationCode == locCode}">
			  <% //System.out.println("In If Loop dept Name");			  %>
 			   <hdiits:option  value="${deptList.locationCode}" selected="true">${deptList.locName}</hdiits:option>
			  </c:when>
			  <c:otherwise>
			  <% //System.out.println("In Bank Name else Loop"); %>
			<hdiits:option value="${deptList.locationCode}"> ${deptList.locName} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
	   	</hdiits:select>		
	   </TD>  
	   
	     
<br><br>

<c:if test="${billList ne null}">
	<tr>
	<TD  align="left" class="Label"> <b><fmt:message key="SR.BillNo" bundle="${commonLables}"/></b></TD>

	<td>
	<hdiits:select name="billNoList" size="1" id="billNoList" sort="false"  caption="BillNo" captionid="billNo"
		validation="sel.isrequired" mandatory="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	   <c:forEach items ="${billList}" var="billList">
			<c:choose>
			<c:when test="${billList.billId == billId}">
			  <% //System.out.println("In If Loop billList Name");			  %>
 			   <hdiits:option  value="${billList.billHeadId}" selected="true">${billList.billId}</hdiits:option>
			  </c:when>
			  <c:otherwise>
			  <% //System.out.println("In billList else Loop"); %>
			<hdiits:option value="${billList.billHeadId}"> ${billList.billId} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
	   </hdiits:select>
	  </td>
	  </tr>
	 </c:if>
	 
<br><br>	 



 <TD  align="left" class="Label" width="15%">
		 <b><fmt:message key="SR.Day" bundle="${commonLables}"/></b></TD>
    	<TD	width ="45%"><hdiits:select name="Day" size="1" sort="false" caption="Day" captionid="Day" validation="sel.isrequired" mandatory="true"  >
	    <hdiits:option value="-1">----Select----</hdiits:option>
            <%
					int i=1;
					for(i=1;i<=31;i++) {
						pageContext.setAttribute("counter",i);						
					%>											
					<c:choose>	
					<c:when test="${counter == day}">			
					<hdiits:option value="${counter}" selected="true"> ${counter}</hdiits:option>
					</c:when>
			  	<c:otherwise>			 
			<hdiits:option value="${counter}"> ${counter} </hdiits:option>
			</c:otherwise>
			</c:choose>			
					<%
					}
					pageContext.removeAttribute("counter");
					%>
					
	   	</hdiits:select>		
	   </TD>  
  	  
		</table>
	
 
 	
 	</div>
 	<hdiits:hidden default="ListPageOfSchedulerDetails" name="givenurl"/> 
 	
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
	