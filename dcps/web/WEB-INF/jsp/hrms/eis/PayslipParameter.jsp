<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<fmt:setBundle basename="resources.Payroll" var="constant" scope="request"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="constantVariables" scope="request"/>
<fmt:message var="adminLoginID" key="ADMINID" bundle="${constant}" scope="request"> </fmt:message>
	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="loginLocId" value = "${resValue.locId}" />
 <c:set var="empList" value="${resValue.empList}" > </c:set> 
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="loginId" value="${resValue.loginId}" ></c:set>
<c:set var="cmnLocVoList" value="${resValue.cmnLocVoList}" ></c:set>
<c:set var="billList" value="${resValue.billList}" ></c:set>
<script>

var emplWindowObj="";
var childUrl="";
function chkKey(e)
{
	
	if(e.keyCode=="13")
	{
		return false;
	}
	else
	{
		
		return true;
	}
}
function findEmployee()
{
	var deptId = document.frmPayslipPara.selDept.value;
	xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  var givenMonth=document.frmPayslipPara.selMonth.value; 
		  var givenYear=document.frmPayslipPara.selYear.value; 
		 
		  url= uri+'&deptId='+ deptId+'&givenMonth='+givenMonth+'&givenYear='+givenYear;
		  var actionf="findEmpNamePayslip";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
  			//alert(' ' + url);	  		  		  
		 xmlHttp.onreadystatechange=find_EmpName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	 
}



function getEmpFirstNames()
{ 
  var empname =document.getElementById("txtEmpFirstName").value;
  var deptId =document.getElementById("selDept").value;
  if(deptId=='')
  {
  	alert("select the department first");
  	document.frmPayslipPara.selDept.focus();
  }
  else
  {
  //alert('Name is ' + empname);
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  var givenMonth=document.frmPayslipPara.selMonth.value; 
		  var givenYear=document.frmPayslipPara.selYear.value; 
		  
		  url= uri+'&emp_first_name='+ empname+'&deptId='+ deptId+'&givenMonth='+givenMonth+'&givenYear='+givenYear;
		  var actionf="findEmpNamePayslip";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
          xmlHttp.onreadystatechange=find_EmpName;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);	
	}
}

function find_EmpName()
{

	 

if (xmlHttp.readyState==complete_state )
 { 			  			
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var village = document.getElementById("cmbEmpNames");		
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
               			clearEmployeeCombo();
               			
                        var namesEntries = XMLDoc.getElementsByTagName('emp-mapping');
                        //alert('Length ' + namesEntries.length);
	           			for ( var i = 0 ; i < namesEntries.length ; i++ )
	     				{
	     				    val=namesEntries[i].childNodes[0].firstChild.nodeValue;    
	     				    text = namesEntries[i].childNodes[1].firstChild.nodeValue + ' ' + namesEntries[i].childNodes[2].firstChild.nodeValue + ' ' + namesEntries[i].childNodes[3].firstChild.nodeValue;  
	//     				    alert('Village val is:' + val + 'and text is:-' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               village.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    village.add(y); 
	   						 }
	   		          }
	   		         }
  }
}


function clearEmployeeCombo()
{
	var v=document.getElementById("cmbEmpNames").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbEmpNames").options.length -1;
			document.getElementById("cmbEmpNames").options[lgth] = null;
	}
		
}

function setEmpId()
{
 
 	document.frmPayslipPara.txtEmpId.value = document.frmPayslipPara.cmbEmpNames.value;
 	
}


function checkValidation() {
//alert('c v start' + ' ' + document.getElementById('selMonth').value + ' ' + document.getElementById('selYear').value + ' ' + document.getElementById('selDept').value );
	if(document.getElementById('selMonth').value == -1){
		alert("Please Select Month");
		document.getElementById('selMonth').focus;
		return false;
	}
	if(document.getElementById('selYear').value == -1){
		alert("Please Select Year");
		document.getElementById('selYear').focus;
		return false;
	}
	/*if(document.getElementById('selDept').value == -1){
		alert("Please Select Department");
		document.getElementById('selDept').focus;
		return false;
	}*/
	if(document.getElementById('selBillNo').value == -1){
		alert("Please Select Bill No");
		document.getElementById('selBillNo').focus;
		return false;
	}
	else
		return true;
	
}

function generatePayslip()
{

	if(!checkValidation()){
		return false;
	}

	var WND_W_MARGIN = 0;
	var WND_H_MARGIN = 30;
	var deptId = 0;
	var wndWidth = typeof(screen.availWidth) != "undefined" ? 
   	screen.availWidth : screen.width - WND_W_MARGIN; 
	var wndHeight = typeof(screen.availHeight) != "undefined" ? 
   	screen.availHeight : screen.height - WND_H_MARGIN; 
	 var payslipMonth=1,payslipYear=1,payslipEmpid=1;
 
	 payslipMonth=document.getElementById('selMonth').value;
	 payslipBill=document.getElementById('selBillNo').value;
	
	 payslipYear=document.getElementById('selYear').value;

	 var dsgnId=document.getElementById("DsgnId").value;
	 var employeeid=document.getElementById("employeeId").value;

	 emplWindowObj=window.open("./hrms.htm?actionFlag=generatePayslip&selMonth="+payslipMonth+"&selYear="+payslipYear+"&selBill="+payslipBill+"&employeeid="+employeeid+"&dsgnId="+dsgnId,"Payslip","status=yes, toolbar=yes,resizable=1,height="+wndHeight+", width="+wndWidth+",scrollbars=1");
     emplWindowObj.moveTo(0, 0); 
     emplWindowObj.focus();
}


function getDsgnFromBillNo()
{

	clearCombo();
	
	var v=document.getElementById("DsgnId").length;
	var billNo=document.frmPayslipPara.selBillNo.value;
	var payslipMonth=1;
    var payslipYear=1;
    payslipMonth=document.getElementById('selMonth').value;
	payslipYear=document.getElementById('selYear').value;
		
		  xmlHttp=GetXmlHttpObject();
		  
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		 
		  var url=''; 
		  var actionf="getDsgnFromBill";
		  url='./hrms.htm?actionFlag='+actionf+'&billNo='+billNo+'&payslipMonth='+payslipMonth+'&payslipYear='+payslipYear;
		  //showProgressbar("Please wait...");
		  xmlHttp.onreadystatechange=get_dsgn;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);	
		   	
}

function clearCombo()
{
	//alert('coming');
	var v=document.getElementById("DsgnId").length;
	var w=document.getElementById("employeeId").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("DsgnId").options.length -1;
			document.getElementById("DsgnId").options[lgth] = null;
	}	

	for(j=1;j<w;j++)
	{
			lgth = document.getElementById("employeeId").options.length -1;
			document.getElementById("employeeId").options[lgth] = null;
	}	
		
}

function get_dsgn()
	{	

	try
	{
		if (xmlHttp.readyState==complete_state)
		{ 
			var classes= document.getElementById("DsgnId");
			var employees= document.getElementById("employeeId");

			 
				var xmlDoc=xmlHttp.responseXML.documentElement; 
			
					
				    var entries = xmlDoc.getElementsByTagName('class-mapping');
			 
				    if(entries.length == 0)
				    {
				      window.status = 'No Records Found.';
				    }
				    else
				    {
					var val=0;
					var text="";
					var previousval=0;
					
					for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
						
		     			 if(entries[i].childNodes[0].firstChild != null ){
		     				val=entries[i].childNodes[0].firstChild.nodeValue;
	     				    text = entries[i].childNodes[1].firstChild.nodeValue;
     				    
     				    
     				    if(text != "") {
     				   	 	var y = document.createElement('option')   
     			       		y.value=val;
     			       			  			      
     			       		 y.text=text;
     			    
     			        	try
   				        	{      	
     			        		if(val!=previousval)   			    					
     			        		classes.add(y,null);
     			        	
           			    	}	
     			       
 				        	catch(ex)
   				        	{	
 				        		classes.add(y); 
   			   	        	}	

 				       		previousval=val;
     				    }
		     			 }
	                 }

					for ( var j = 0 ; j < entries.length ; j++ )
	     			 {
						 if(entries[j].childNodes[2].firstChild != null ){
						
    				      val=entries[j].childNodes[2].firstChild.nodeValue;;  
    				    text = entries[j].childNodes[3].firstChild.nodeValue;;  
    				    var z = document.createElement('option')   
    			        z.value=val;
    			        z.text=text;
    			        try
  				        {      				    					
    			        	employees.add(z,null);
          			    }
				        catch(ex)
  				        {
				        	employees.add(z); 
  			   	        }
	     			 }	
	                 }
	                 
				    }
             }
		// showProgressbar("Please wait...");	
	 }
	catch(e)
	{
		//window.console('Error ' + e);
		console.log("Asas"+ e);

		
	}
}

function closeForm()
{
	//alert("in close...");
	document.frmPayslipPara.action='./hrms.htm?actionFlag=getHomePage';
	document.frmPayslipPara.submit();
}

function resetForm()
{
	if(confirm("All the entered values will be cleard ,Please confirm!") == true)
	{
		
		
		var DsgnId= document.getElementById("DsgnId").value;
		if(DsgnId>0)
		{
		  document.getElementById("employeeId").disabled=false;
		}
		var employeeId= document.getElementById("employeeId").value;
		if(employeeId>0)
		{
		  document.getElementById("DsgnId").disabled=false;
		}
		document.forms[0].reset();
	}
				  	
}

function getDisableEmployee()
{
	//alert('DsgnId'+document.getElementById("DsgnId").value); 
	var DsgnId= document.getElementById("DsgnId").value;
	if(DsgnId>0)
	{
	  document.getElementById("employeeId").disabled=true;
	}
	else
	{
		 document.getElementById("employeeId").disabled=false;
	}
	
}
function getDisableDsgn()
{
	//alert('employeeId'+document.getElementById("employeeId").value); 
	var employeeId= document.getElementById("employeeId").value;
	if(employeeId>0)
	{
	  document.getElementById("DsgnId").disabled=true;
	}
	else
	{
	document.getElementById("DsgnId").disabled=false;
	}
	
}
</script>
<style>/* added by Pratik 09-08-23 */
table.PayslipparaTb select {
    max-width: 230px;
}
</style>

<hdiits:form name="frmPayslipPara" validate="true" method="POST"
	action="./hrms.htm?actionFlag=generatePayslip" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="Payslip Parameters" caption="Payslip Parameters"/> </b></a></li>
	</ul>
	</div>
	
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">  
<div align="center">
 <table class="PayslipparaTb">
   	 <tr style="width:120px">
   	 
				<TD > <b><hdiits:caption caption="Month" captionid="Month"/></b></TD>
				<td align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<hdiits:select name="selMonth" size="1" sort="false"  caption="Month"    id="selMonth" mandatory="true" validation="sel.isrequired">
						<hdiits:option value="-1"> --Select-- </hdiits:option>
						<c:forEach items="${monthList}" var="month">
						<hdiits:option value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
						</c:forEach>
						</hdiits:select>
				</td>
			   
				<TD >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><hdiits:caption caption="Year" captionid="Year"/></b></TD>
				<td >
					<hdiits:select name="selYear" size="1" sort="false"  caption="Year"    id="selYear" mandatory="true" validation="sel.isrequired">
					<hdiits:option value="-1"> --Select-- </hdiits:option>
					<c:forEach items="${yearList}" var="yearList">
					<hdiits:option value="${yearList.lookupShortName}"> ${yearList.lookupDesc} </hdiits:option>
					</c:forEach>
					</hdiits:select>
				</td>
	</tr>

	<tr>
		<td><hdiits:caption caption="BillNo" captionid="BillNo" bundle="${constantVariables}"/></td>
		<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<select  name="selBillNo" size="1" id="selBillNo" sort="false"   captionid="billNo" caption="billNo"
			validation="sel.isrequired" mandatory="true" onchange="getDsgnFromBillNo()" style="width:335px">
		   <option value="-1">-------------------Select-------------------</option>
		    <c:forEach var="billList" items="${billList}">
	         <option value="${billList.billHeadId}" title="${billList.billId}"><c:out value="${billList.billId}"> </c:out></option>
	         </c:forEach>
		   </select>
		   <label class="mandatoryindicator">*</label>
		</td>
	</tr>
	
	<TR>
		<TD align="left" >
		 <fmt:message key="BHM.designation" bundle="${commonLables}"/> 
		</TD>			
				
		<TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<hdiits:select captionid="BHM.designation" bundle="${commonLables}"	id="DsgnId" name="dsgn"  sort="false" onchange="getDisableEmployee()">
          <hdiits:option value="-1">-------------------Select-------------------</hdiits:option>
         </hdiits:select>
         	</TD>
         	
	</TR>
	
	<TR >
	<TD >
		 Employee Name 
		</TD>	
	<TD align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<hdiits:select name="employeeId" validation="sel.isrequired" id="employeeId" size="1"  mandatory="false" 
			caption="Employee Name" sort="false" onchange="getDisableDsgn()">
          <hdiits:option value="-1">-------------------Select-------------------</hdiits:option>
         </hdiits:select>
         	</TD>
     </TR>
	
	</div>
   </table>
   <br> 
   <table>
   
   	 
   <c:choose>
   <c:when test = "${adminLoginID eq loginId}">
   <tr>
	   <td>
		  <b> <hdiits:caption captionid="OT.empDept" bundle="${commonLables}"></hdiits:caption></b>
			<hdiits:select name="selDept" size="1" id="selDept" captionid="OT.empDept" bundle="${commonLables}" sort="false" validation="sel.isrequired"  mandatory="true" onchange="findEmployee()">
			<hdiits:option value="-1"> --Select-- </hdiits:option>
			
			<c:forEach items="${cmnLocVoList}" var="cmnLocVoList">
			
			<c:choose>
									<c:when test="${cmnLocVoList.locId==loginLocId}">
										<hdiits:option value="${cmnLocVoList.locId}" selected="true" > ${cmnLocVoList.locName}</hdiits:option>
								    </c:when>
									<c:otherwise>
									     <hdiits:option value="${cmnLocVoList.locId}"> ${cmnLocVoList.locName}</hdiits:option>
									</c:otherwise>
			</c:choose>
			</c:forEach>
			</hdiits:select>
		</td>
    </tr>
   <tr>
   <td> <b>
   <hdiits:caption captionid="Search Name" caption="Search Name "/> </b>
    <input type="text" name="txtEmpFirstName" onkeyup="getEmpFirstNames()" onkeypress="return chkKey(event)"/>
    </td>
    </tr>
    <tr> <td> </td> </tr>
 <tr>  									
			<TD> <b><hdiits:caption captionid="Employee Name" caption="Employee Name"/> </b>
			 <hdiits:select name="cmbEmpNames" validation="sel.isrequired" id="empID" size="10" 
			caption="Employee Name" sort="false" onchange="setEmpId()">
			<hdiits:option value="-1" selected="true">-------Selected--------</hdiits:option>
		     
			</hdiits:select>		       
			</TD>
	</TR>
	<hdiits:hidden name="txtEmpId" default="-1"/>
	</c:when>
   <c:otherwise>
   <hdiits:select style='display:none' name="selDept" size="1" id="selDept" captionid="OT.empDept" bundle="${commonLables}">
			<hdiits:option value="-11"> --Select-- </hdiits:option>
	</hdiits:select>
   <hdiits:hidden name="txtEmpId" default="${loginId}"/>
   </c:otherwise>
   </c:choose>
	
	<tr> <td> </td></tr>
	<tr> <td> </td></tr>
	<tr>
	<td align="center" >
	<hdiits:button name="btn1" value="Generate" caption="Generate" 
	id="btnSubmit1" captionid="Generate" onclick="generatePayslip()" 
	   type="button" />
	  
	  
	  <hdiits:button name="Back" type="button" value="Close" onclick="closeForm()"/>
	  
	  <hdiits:button name="Reset" type="button" value="Reset" onclick="resetForm()"/>
	  </td>
	  </tr>	
	</table> 
    <br>
 </div>
 
	
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

   	 