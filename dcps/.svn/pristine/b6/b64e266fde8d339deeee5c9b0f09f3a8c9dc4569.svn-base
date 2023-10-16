<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="commonLables" scope="request"/>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>

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
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:message var="adminLoginID" key="ADMINID" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="adminLoginIDFD" key="ADMINIDFD" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="userRoleId" key="roleId" bundle="${constantVariables}" scope="request"> </fmt:message>

	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="loginLocId" value = "${resValue.locId}" />
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="classList" value="${resValue.classList}" ></c:set>
<c:set var="desgnList" value="${resValue.dsgnList}" ></c:set>
<c:set var="loginId" value="${resValue.loginId}" ></c:set>
<c:set var="cmnLocVoList" value="${resValue.cmnLocVoList}" ></c:set>
<c:set var="billList" value="${resValue.billList}" ></c:set>
<c:set var="isAdminRole" value="${resValue.isAdminRole}"></c:set>
<c:set var="billNosList" value="${resValue.billNosList}"></c:set>
<%
// added for current month and current year in the select month and year field ...

SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
Date dt = new Date();
int curYear=Integer.parseInt(sdfObj.format(dt));
sdfObj = new SimpleDateFormat("MM");
int curMonth=Integer.parseInt(sdfObj.format(dt));
pageContext.setAttribute("curMonth",curMonth);
pageContext.setAttribute("curYear",curYear);
%>

<script>
var emplWindowObj="";
var childUrl="";
function checkValidation() {
	if(document.getElementById('selMonth').value == ''){
		alert("Please Select Month");
		document.getElementById('selMonth').focus;
		return false;
	}
	if(document.getElementById('selYear').value == ''){
		alert("Please Select Year");
		document.getElementById('selYear').focus;
		return false;
	}
	/*if(document.getElementById('billNo').value == '' && document.getElementById("Employee_ID_EmpSearch").value == '')
	{
		alert("Please Select either Bill No or employee");
		document.getElementById('billNo').focus;
		return false;
	}*/
	else
		return true;
	
}
function getClassFromBillNo()
{
	var v=document.getElementById("ClassId").length;
	var billNo=document.frmPayslipPara.billNo.value;
	var payslipMonth=1;
    var payslipYear=1;
    payslipMonth=document.getElementById('selMonth').value;
	payslipYear=document.getElementById('selYear').value;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("ClassId").options.length -1;
			document.getElementById("ClassId").options[lgth] = null;
	}		
		  xmlHttp=GetXmlHttpObject();
		  
		  if (xmlHttp==null)
		  {
			  return;
		  } 
		  
		  var url=''; 
		  var actionf="getClass";
		  url='./hrms.htm?actionFlag='+actionf+'&billNo='+billNo+'&payslipMonth='+payslipMonth+'&payslipYear='+payslipYear;
		  xmlHttp.onreadystatechange=get_class;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function get_class()
	{	

	try
	{
		if (xmlHttp.readyState==complete_state)
		{ 
			var classes= document.getElementById("ClassId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('class-mapping');
					var val=0;
					var text='';
					var v=document.getElementById("DsgnId").length;
					for(i=1;i<v;i++)
						{
								lgth = document.getElementById("DsgnId").options.length -1;
								document.getElementById("DsgnId").options[lgth] = null;
						}
					for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;  
     				    text = entries[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;
     			        try
   				        {      				    					
     			        	classes.add(y,null);
           			    }
 				        catch(ex)
   				        {
 				        	classes.add(y); 
   			   	        }	
	                 }
             }
	 }
	catch(e)
	{
		window.status('Error ' + e);
	}
}

function getDsgnFromClass(classArray)
  {
	//alert("inside getDsgnFromClass");
	
	     var v=document.getElementById("DsgnId").length;
	     var billNo=document.frmPayslipPara.billNo.value;
	     var payslipMonth=1;
	     var payslipYear=1;
	     payslipMonth=document.getElementById('selMonth').value;
		 payslipYear=document.getElementById('selYear').value;
	     for(i=1;i<v;i++)
	      {
			lgth = document.getElementById("DsgnId").options.length -1;
			document.getElementById("DsgnId").options[lgth] = null;
	      }
	
		  xmlHttp=GetXmlHttpObject();
		  
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  var url=''; 
		  var actionf="getDesignation";
		  url='./hrms.htm?actionFlag='+actionf+'&classArray='+classArray+'&billNo='+billNo+'&payslipMonth='+payslipMonth+'&payslipYear='+payslipYear;
		   
		  xmlHttp.onreadystatechange=get_designation;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		
   }
function get_designation()
   {

	try
	{
		if (xmlHttp.readyState==complete_state)
		{ 
			var designations= document.getElementById("DsgnId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('designation-mapping');
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
     			        	designations.add(y,null);
           			    }
 				        catch(ex)
   				        {
 				        	designations.add(y); 
   			   	        }	
	                
	                 }
             }
	 }
	catch(e)
	{
		window.status('Error ' + e);
	}
  }

function selectedClass()
  {
	var classObj=document.getElementById('ClassId');
	var classLgth=document.getElementById('ClassId').length;
	var classArray='';
	var y=0;
	for(i=0;i<classLgth;i++)
	{
		if(classObj.options[i].selected)
		{
			y++;
			if(y==1)
			{
				classArray += classObj.options[i].value;
			}
			else
			{
				classArray += ' , ' + classObj.options[i].value;
			}
			
		}
	}
	getDsgnFromClass(classArray);
  }

function generatePayslip(classArray)
 {
	if(!checkValidation())
	{
		return false;
    }
	var WND_W_MARGIN = 0;
	var WND_H_MARGIN = 30;
	var deptId = 0;
	var payslipEmpid = 0;

	var dsgnObj = document.getElementById('DsgnId');
	if(dsgnObj!=null) {
	var dsgnLgth = document.getElementById('DsgnId').length;
	var dsgnArray='';
	var x=0;
	var classObj=document.getElementById('ClassId');
	var classLgth=document.getElementById('ClassId').length;
	var classArray='';
	var y=0;
    for(i=0;i<dsgnLgth;i++) 
     {
	if(dsgnObj.options[i].selected) 
		{
		x++;
		if(x==1)
		{
			dsgnArray += dsgnObj.options[i].value;
		}
		else
		{
			dsgnArray += ' , ' + dsgnObj.options[i].value;
		}
	    }
     }
    for(i=0;i<classLgth;i++)
	{
		if(classObj.options[i].selected)
		{
			y++;
			if(y==1)
			{
				classArray += classObj.options[i].value;
			}
			else
			{
				classArray += ' , ' + classObj.options[i].value;
			}
			
		}
	}
	}
	 var wndWidth = typeof(screen.availWidth) != "undefined" ? screen.availWidth : screen.width - WND_W_MARGIN; 
	 var wndHeight = typeof(screen.availHeight) != "undefined" ?	screen.availHeight : screen.height - WND_H_MARGIN; 
	 var payslipMonth=1,payslipYear=1,payslipEmpid=1;
	 payslipMonth=document.getElementById('selMonth').value;
	 
	 payslipYear=document.getElementById('selYear').value;
	 deptId =  document.getElementById('selDept').value;
	
	 var billNo=document.getElementById('billNo').value;
	 	
	 payslipEmpid = document.getElementById("Employee_ID_EmpSearch").value;		
	 var txtEmpId=document.frmPayslipPara.txtEmpId.value;
     if(txtEmpId!=-1)
     {
     		 emplWindowObj=window.open("./hrms.htm?actionFlag=getcommonPayslipData&selMonth="+payslipMonth+"&selYear="+payslipYear+"&txtEmpId="+txtEmpId+"&deptId="+deptId,"Payslip","status=yes, toolbar=yes,resizable=1,height="+wndHeight+", width="+wndWidth+",scrollbars=1");
   	 }
	 else
	 {
	 	     emplWindowObj=window.open("./hrms.htm?actionFlag=getcommonPayslipData&selMonth="+payslipMonth+"&selYear="+payslipYear+"&txtEmpId="+payslipEmpid+"&billNo="+billNo+"&dsgnIdArray="+dsgnArray+"&classArray="+classArray+"&deptId="+deptId,"Payslip","status=yes, toolbar=yes,resizable=1,height="+wndHeight+", width="+wndWidth+",scrollbars=1");
	 }
	 emplWindowObj.moveTo(0, 0); 
     emplWindowObj.focus();		     			
  }


</script>
<style type="text/css">
.boxed {
	padding: 20px 50px 20px 30px;
}
.orange {
	margin: 20px 30px 20px 30px;
	
}
</style>

<hdiits:form name="frmPayslipPara" validate="true" method="POST"
	action="./hrms.htm?actionFlag=generatecommonPayslip" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="Payslip Parameters" caption="Payslip Parameters"/> </b></a></li>
	</ul>`
	</div><br><br>
	<center><h2><font color="blue"> P A Y S L I P </font></h2><br></center>
   <c:choose>
   <c:when test = "${userRoleId eq isAdminRole}">
	
	<br><center>
<div style="width:75%">
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpSearch"/>
						<jsp:param name="formName" value="frmPayslipPara"/>
						<jsp:param name="functionName" value="generatePayslip"/>
					</jsp:include>
</div>			</center>
	<br>
	</c:when>
	<c:otherwise>
   <hdiits:hidden name="Employee_ID_EmpSearch" id="Employee_ID_EmpSearch" default="-1"/>
			
	</c:otherwise>
	</c:choose>
	
	<br>
<DIV class="boxed orange" align = "center">
 <table class="commonPaysliptb" >	 
   <tr>	
		<TD> <b><hdiits:caption caption="Month" captionid="Month"/></b></TD>
		<td> 
		<hdiits:select name="selMonth" size="1" sort="false" caption="Month" onchange="getClassFromBillNo()" id="selMonth" mandatory="true"  
						 validation="sel.isrequired">
		<hdiits:option value=""> -- -- -- -- -- -- -- --  S E L E C T  -- -- -- -- -- -- -- -- </hdiits:option>
		<c:forEach items="${monthList}" var="month">
		<c:choose>
			<c:when test="${month.lookupShortName == curMonth}">
		<hdiits:option value="${month.lookupShortName}" selected="true" > ${month.lookupDesc} </hdiits:option>
		</c:when>
		<c:otherwise>
			<hdiits:option value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</hdiits:select>
		</td>
   	<TR>	
   		<TD> <b><hdiits:caption caption="Year" captionid="Year"/></b></TD>
		<td>  
		<hdiits:select name="selYear" size="1" sort="false" caption="Year" id="selYear" onchange="getClassFromBillNo()" mandatory="true" validation="sel.isrequired">
		<hdiits:option value=""> -- -- -- -- -- -- -- --  S E L E C T  -- -- -- -- -- -- -- -- </hdiits:option>
		<c:forEach items="${yearList}" var="yearList">
		<c:choose>
		<c:when test="${yearList.lookupShortName == curYear}">
		<hdiits:option value="${yearList.lookupShortName}" selected="true" > ${yearList.lookupDesc} </hdiits:option>
		</c:when>
		<c:otherwise>
			<hdiits:option value="${yearList.lookupShortName}"> ${yearList.lookupDesc} </hdiits:option>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</hdiits:select>
		</td>
	</TR>		
  
   <c:choose>
   <c:when test = "${userRoleId eq isAdminRole}">
  
   <hdiits:hidden  name="selDept"  id="selDept" default="${loginLocId}"/>
	
	<tr>
			<TD> <b><hdiits:caption captionid="Bill No" caption="Bill No"/> </b> &nbsp;&nbsp;&nbsp;&nbsp;</td>
			 <TD><hdiits:select name="billNo" validation="sel.isrequired" id="billNo" size="1" caption="Bill No" sort="false" mandatory="true" onchange="getClassFromBillNo()">
			<hdiits:option value="" selected="true"> -- -- -- -- -- -- -- --  S E L E C T  -- -- -- -- -- -- -- -- </hdiits:option>
			<c:forEach items="${billNosList}" var="billNosList">
			<hdiits:option value="${billNosList.billHeadId}"> ${billNosList.billId}</hdiits:option>
			</c:forEach>
			</hdiits:select>		       
			</TD>
	</TR>
	 <TR>
		<TD >
		<b><fmt:message key="BHM.class" bundle="${commonLables}"/></b>
		</TD>			
		<TD >
			<hdiits:select style="width:100%" captionid="BHM.class" bundle="${commonLables}"  id="ClassId" name="Class"  multiple="true" size ="5" onchange="selectedClass()">
		    </hdiits:select>
		</TD>
	</TR>
	<TR>
		<TD >
		<b><fmt:message key="BHM.designation" bundle="${commonLables}"/></b>
		</TD>			
		<TD >
			<hdiits:select style="width:100%" captionid="BHM.designation" bundle="${commonLables}"  id="DsgnId" name="dsgn"  multiple="true" size ="5">
			</hdiits:select>

		</TD>
	</TR>
	 
	<hdiits:hidden name="txtEmpId" default="-1"/>
	</c:when>
   <c:otherwise>
   <hdiits:hidden  name="selDept" id="selDept" default="${loginLocId}"/>
   <hdiits:hidden name="txtEmpId" default="${loginId}"/>
   <hdiits:hidden name="billNo" default=""/>
   </c:otherwise>
   </c:choose>	
</table> 
<br><br>

	<hdiits:button name="btn1" value="Generate Payslip" caption="Generate Payslip" 
	id="btnSubmit1" captionid="Generate Payslip" onclick="generatePayslip()" type="button" />
	 <br><br><br>
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.		
		getClassFromBillNo();
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

   	 