<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/OuterPaybillPara.js"/>"></script>
	

	<c:set var="resultObj" value="${result}" > </c:set>
	<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
	<c:set  var="orderresultSet" value='${resultValue.orderresultSet}' />
    <c:set  var="headresultSet" value='${resultValue.headresultSet}' /> 
    <c:set var="msg" value="${resultValue.msg}" ></c:set>
    <c:set var="result" value="${resultValue.result}" ></c:set>
    <c:set var="deptList" value="${resultValue.deptList}" ></c:set>
    <c:set var="hrPayOrderHeadMpg" value="${resultValue.hrPayOrderHeadMpg}" ></c:set>
    <c:set var="demandNoList" value="${resultValue.demandNoList}" ></c:set>
    <c:set var="mjrHeadList" value="${resultValue.mjrHeadList}" ></c:set>
    <c:set var="subMjrHeadList" value="${resultValue.subMjrHeadList}" ></c:set>
    <c:set var="minorHeadList" value="${resultValue.minorHeadList}" ></c:set>
    <c:set var="subHeadList" value="${resultValue.subHeadList}" ></c:set>
    
   <!--   <c:set var="headStructure" value="${document.orderHeadMaster.headstructure.value}"></c:set> -->
   
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

${resValue.msg}


<script type="text/javascript" language="JavaScript">

function GetChange(){
//document.orderHeadMaster.headstructure.value='';
document.getElementById("headSt").style.display='none';

}
function GetHeadStructure(){

	document.getElementById("headSt").style.display='';
	var demandnumber = document.orderHeadMaster.cmbDemand.value;
	var cmbMjrHead = document.orderHeadMaster.cmbMjrHead.value;
	var cmbSubMjrHead = document.orderHeadMaster.cmbSubMjrHead.value;
	var cmbMnrHead = document.orderHeadMaster.cmbMnrHead.value;
	var head = document.orderHeadMaster.head.options[document.orderHeadMaster.head.selectedIndex].text;
	//document.orderHeadMaster.headstructure.value=demandnumber+'-'+cmbMjrHead+'-'+cmbSubMjrHead+'-'+cmbMnrHead+'-'+head;
	document.getElementById("headStructure").innerHTML=demandnumber+'-'+cmbMjrHead+'-'+cmbSubMjrHead+'-'+cmbMnrHead+'-'+head;
	
	}

function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	return true;
}
function chkorderHeadunique()
{
	var order=document.orderHeadMaster.order.value;
	var head=document.orderHeadMaster.head.value;
	
	if(order!=""&&head!="")
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
	var mpgId=${hrPayOrderHeadMpg.orderHeadId};
    var url = "hrms.htm?actionFlag=chkorderHeadunique&order="+order+"&head="+head+"&ohMapId="+mpgId;  
    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var mpgflag = XMLDocForAjax.getElementsByTagName('oh-mapping');	
				
				if(mpgflag.length != 0)
				{
					if(mpgflag[0].childNodes[0].text!='null'&&mpgflag[0].childNodes[0].text!='-1')
					{			
						alert("Mapping already Exists ");
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
	chkorderHeadunique();
	if(chkflag==0)
	return false;
	else
	return true;
}

function beforeSubmit()
{
		document.orderHeadMaster.action="./hdiits.htm?actionFlag=AddOrderHeadData&updateflag=Y&ohMapId=${hrPayOrderHeadMpg.orderHeadId}";
		document.orderHeadMaster.submit();
}	



</script>
<hdiits:form name="orderHeadMaster" validate="true" method="POST"
	action="javascript:beforeSubmit()" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OM.orderheadEdit" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<br/>
	<TABLE align="center" width="80%">  
	<TR>
		<TD  align="left" class="Label" width="15%">
		 <b><fmt:message key="PR.DEPT" bundle="${commonLables}"/></b></TD>
    	<TD	width ="25%">
    	<hdiits:select style="width:90%"  name="cmbDept" size="1" sort="false" caption="Department" captionid="Dept" validation="sel.isrequired" mandatory="true" onchange="getOrderList();GetChange();" >
	    <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${deptList}" var="deptList">
								<c:choose>
									<c:when test="${hrPayOrderHeadMpg.locationCode==deptList.locationCode}">
										<option selected="true" value='<c:out value="${hrPayOrderHeadMpg.locationCode}"/>' >
										<c:out  value="${deptList.locName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${deptList.locationCode}"/>'>
										<c:out value="${deptList.locName}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	   	</hdiits:select>
	   </TD>
	
		<TD align="left" width = "15%">
		  <b><fmt:message key="OM.orderName" bundle="${commonLables}"/></b></TD>
		<TD align="left" width ="25%"><hdiits:select  style="width:95%"  mandatory="true" sort="false"  captionid="OM.orderName" bundle="${commonLables}" validation="sel.isrequired"  name="order" size ="1" onchange="GetDemandNo();GetChange();" >
				<hdiits:option value="" selected="true">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${orderresultSet}" var="orderresultSet">
								<c:choose>
									<c:when test="${hrPayOrderHeadMpg.orderId==orderresultSet.orderId}">
										<option selected="true" value='<c:out value="${orderresultSet.orderId}"/>' >
										<c:out  value="${orderresultSet.orderName}  (Date:${orderresultSet.orderDate})"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${orderresultSet.orderId}"/>'>
										<c:out value="${orderresultSet.orderName} (Date:${orderresultSet.orderDate})"/></option>
									</c:otherwise>
								</c:choose>	
				    	</c:forEach>		
				</hdiits:select>
			</TD>

	
	</TR>
	<tr></tr>

<tr>		
			
	<TD  align="left" class="Label" width="15%">
			<b><fmt:message key="PR.DEMAND" bundle="${commonLables}"/></b></TD>
	<td align="left" width ="25%">
	 <hdiits:select  style="width:90%" name="cmbDemand" id="cmbDemand" size="1" mandatory="true" caption="Demand No" captionid="DmdNo"	validation="sel.isrequired" sort="false" onchange="GetMjrHeadNo();GetChange();">
	   <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${demandNoList}" var="demandNoList">
								<c:choose>
									<c:when test="${hrPayOrderHeadMpg.demandNo==demandNoList.demandNo}">
										<option selected="true" value='<c:out value="${hrPayOrderHeadMpg.demandNo}"/>' >
										<c:out  value="${demandNoList.demandNo}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${demandNoList.demandNo}"/>'>
										<c:out value="${demandNoList.demandNo}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	 </hdiits:select>
	 </td>
	
	
	<TD align="left" width="15%"> 
		<b><fmt:message key="PR.MJRHEAD" bundle="${commonLables}"/></b></TD>
	<TD width ="25%">
	 <hdiits:select  style="width:95%" name="cmbMjrHead" caption="Major Head" captionid="MjrHeadNo" id="mjrHead" size="1" sort="false" mandatory="true" validation="sel.isrequired"  onchange="GetSubMjrHeadNo();GetChange();">
	   <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${mjrHeadList}" var="mjrHeadList">
								<c:choose>
									<c:when test="${hrPayOrderHeadMpg.mjrHead==mjrHeadList.mjrHead}">
										<option selected="true" value='<c:out value="${hrPayOrderHeadMpg.mjrHead}"/>' >
										<c:out  value="${mjrHeadList.mjrHead}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${mjrHeadList.mjrHead}"/>'>
										<c:out value="${mjrHeadList.mjrHead}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	</hdiits:select>
	</TD>
	</tr>
<tr></tr>
	<tr>
	<TD align="left" class="Label" width="15%"> 
		<b><fmt:message key="PR.SUBMJRHEAD" bundle="${commonLables}"/></b></TD>
	<TD align="left" width ="25%">
	  <hdiits:select  style="width:90%" name="cmbSubMjrHead" id="cmbSubMjrHead" size="1" caption="Sub Major Head"
		 captionid="subMjrHeadNo"
		validation="sel.isrequired" mandatory="true" sort="false" onchange="GetMnrHeads();GetChange();">
	    <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${subMjrHeadList}" var="subMjrHeadList">
								<c:choose>
									<c:when test="${hrPayOrderHeadMpg.subMjrHead==subMjrHeadList.subMjrHead}">
										<option selected="true" value='<c:out value="${hrPayOrderHeadMpg.subMjrHead}"/>' >
										<c:out  value="${subMjrHeadList.subMjrHead}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${subMjrHeadList.subMjrHead}"/>'>
										<c:out value="${subMjrHeadList.subMjrHead}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	  </hdiits:select>
	  </TD>
	
	
	<TD align="left" align="right" width ="15%"> 
		<b><fmt:message key="PR.MNRHEAD" bundle="${commonLables}"/></b></TD>
	<td width ="25%">
	 <hdiits:select  style="width:95%" name="cmbMnrHead" id="cmbMnrHead" size="1" sort="false"  caption="Minor Head" captionid="MnrHeadNo"
		mandatory="true" validation="sel.isrequired" onchange="GetSubHeads1();GetChange();">
	   <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${minorHeadList}" var="minorHeadList">
								<c:choose>
									<c:when test="${hrPayOrderHeadMpg.minorHead==minorHeadList.minorHead}">
										<option selected="true" value='<c:out value="${hrPayOrderHeadMpg.minorHead}"/>' >
										<c:out  value="${minorHeadList.minorHead}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${minorHeadList.minorHead}"/>'>
										<c:out value="${minorHeadList.minorHead}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	 </hdiits:select>
	 </td>
	</tr>
	<tr></tr>
	<tr>
	<TD align="left" width ="15%" >
		 <b><fmt:message key="PR.SUBHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width ="30%"><hdiits:select name="head" id="cmbSubHead"  size="1" sort="false" style="width:90%" caption="Sub Head" captionid="OM.headName" mandatory="true" validation="sel.isrequired" onchange="GetDtlHeads();GetHeadStructure();">
	  <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${subHeadList}" var="subHeadList">
								<c:choose>
									<c:when test="${hrPayOrderHeadMpg.subHead==subHeadList.subHead}">
										<option selected="true" value='<c:out value="${hrPayOrderHeadMpg.subHeadId}"/>' >
										<c:out  value="${subHeadList.subHeadName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${subHeadList.subHeadId}"/>'>
										<c:out value="${subHeadList.subHeadName}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	 </hdiits:select>
	 </td>
	
	
	<TD align="left" width ="15%"> 
		<b><fmt:message key="PR.DTLHEAD" bundle="${commonLables}"/></b></TD>
	<td width ="30%"><hdiits:select name="cmbDtlHead" id="cmbDtlHead" size="1" sort="false" style="width:95%">
	
	  <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	 </hdiits:select>
	 </td>
</tr> 

<tr id="headSt">
	<td align="left" width="15%">
	<b>Head Structure</b></td>
	<td align="left" width="100%" colspan="3"  ><span id="headStructure"></span>  </td>
	<c:out value="${headStructure}"></c:out>
	
	</tr>
	<hdiits:hidden name="Demand" id="Demand" default="document.frmPaybillPara.cmbDemand.value"/>
	<hdiits:hidden name="MjHd" id="MjHd" default="document.frmPaybillPara.cmbMjrHead.value"/>
	<hdiits:hidden name="SubMjHd" id="SubMjHd" default="document.frmPaybillPara.cmbSubMjrHead.value"/>
    <hdiits:hidden name="MnrHd" id="MnrHd" default="document.frmPaybillPara.cmbMnrHead.value"/>
  	<hdiits:hidden name="txtSbHd" id="txtSbHd" default="document.frmPaybillPara.cmbSubHead.value"/>
  	
  	<hdiits:hidden name="grade" id="grade" default="document.frmPaybillPara.cmbSelGrade.value"/>
	<tr></tr>
	
	<tr><td colspan="2">&nbsp;</td></tr>
	</TABLE>
	</div> 
	
 	<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
 	<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
	<hdiits:hidden default="getOrderHeadData" name="givenurl"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
		{
		var demandnumber = document.orderHeadMaster.cmbDemand.value;
		var cmbMjrHead = document.orderHeadMaster.cmbMjrHead.value;
		var cmbSubMjrHead = document.orderHeadMaster.cmbSubMjrHead.value;
		var cmbMnrHead = document.orderHeadMaster.cmbMnrHead.value;
		var head = document.orderHeadMaster.head.options[document.orderHeadMaster.head.selectedIndex].text
		//document.orderHeadMaster.headstructure.value=demandnumber+'-'+cmbMjrHead+'-'+cmbSubMjrHead+'-'+cmbMnrHead+'-'+head;
		document.getElementById("headStructure").innerHTML=demandnumber+'-'+cmbMjrHead+'-'+cmbSubMjrHead+'-'+cmbMnrHead+'-'+head;
		//alert("Order Id is:-"+"${hrPayOrderHeadMpg.orderId}");
		//document.orderHeadMaster.order.value= "${hrPayOrderHeadMpg.orderId}";
		
		}
				

		if("${msg}"!=null&&"${msg}"!='')
		{
				alert("${msg}");				
				var url="./hrms.htm?actionFlag=getOrderHeadData";				
				document.orderHeadMaster.action=url;
				document.orderHeadMaster.submit();
			
				
				
				
				
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">
document.orderHeadMaster.cmbDept.focus();
</script>

</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


