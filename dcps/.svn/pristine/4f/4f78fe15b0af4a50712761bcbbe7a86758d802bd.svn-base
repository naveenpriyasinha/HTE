<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import ="com.tcs.sgv.eis.valueobject.HrEisEmpMst,java.text.SimpleDateFormat" %>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="ptId" key="ptId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="vpfId" key="vpfId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="cpfId" key="cpfId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="surItId" key="surItId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="GpfId" key="GpfId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="pliId" key="pliId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="itId" key="itId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="contractEmpType" key="contract" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="fixedEmpType" key="fixed" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="probationEmpType" key="probation" bundle="${constantVariables}" scope="request"> </fmt:message>
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
	
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.deducActionList}" ></c:set>
<c:set var="deducNamesFromMpg" value="${resValue.deducNamesFromMpg}" ></c:set>
<c:set var="empList" value="${resValue.empList}" ></c:set>
<c:set var="deducMpgSize" value="${resValue.deducMpgSize}" > </c:set>
<c:set var="deducNamesFromExpr" value="${resValue.deducNamesFromExpr}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="hrEisMst" value="${resValue.hrEisMst}" > </c:set>
<c:set var="otherList" value="${resValue.dataList}" > </c:set>
<c:set var="incometax" value="${resValue.incometax}" > </c:set>


<script>
var size=0;

function showAllowances()
{ 
 
    var empid = document.getElementById("Employee_ID_EmpSearch").value;
    
    if(document.insEmpMpg.cstrStatus.value=="1")
    {
    url = "hrms.htm?actionFlag=getAllwType&EmpAllowMpg=Y&EmpId=" + empid;   
  	document.insEmpMpg.action = url;
    document.insEmpMpg.submit();
    }
   
} 

function showEmpName()
{
 document.getElementById('deduction').style.display='none';
 //resetMainFormCombo();
 childWindow = window.open("hrms.htm?actionFlag=getEmpData&EmpMpg=N","EmployeeNames","toolbar=no, location=no, directories=no,status=no, menubar=no, scrollbars=yes, resizable=yes, resize = no, width = 800, height = 550, copyhistory=no, top=80,left=80");
 if (childWindow.opener == null) childWindow.opener = self;
}



//function which shows values after insert.
function checkComboStates(allow_code,val,amount,status,vpfAmt)
{
//alert('in checkstate funct..' + val + 'amount ' + amount);



 for(i=0;i<document.insEmpMpg.tax_name.length;i++)
  if(document.insEmpMpg.tax_name[i].value == val)
   {
     //document.insEmpMpg.tax_name[i].checked = true;     
     //document.insEmpMpg.tax_name[i].disabled = true;
     
      var txtname = 'txt' + val; 
      var txtIsChecked = 'txtIsChecked' + val;
      var allowcodeId = 'txtDeducCode' + val;
//      alert('textbox name ' + txtname);
//      alert('value of textbox is ' + opener.document.getElementById(txtname).value);

if(status==0)
{
amount=vpfAmt;
}

     document.getElementById(txtname).value = amount;
     document.getElementById(txtIsChecked).value="1";
     document.getElementById(txtname).style.display = '';
     //document.getElementById(txtname).disabled=true;
     var btnAdd = 'btn_add' + val; 
     document.getElementById(btnAdd).style.display = 'none';
     document.getElementById(btnAdd).value='Edit';
     document.getElementById(allowcodeId).value=allow_code;
   }
}

function insertEmpAllowMpg1()
{
  //status=document.insEmpMpg.status.value;
  //if(status== 'Add')
  //{
  //  var empid = document.insEmpMpg.txtEmpId.value;
  //  url = "hrms.htm?actionFlag=insertEmpAllowMpg&batchupdate=true&edit=N&size="+size+"&txtEmpId=" + empid;
  //}
  
  //else //if(status== 'update')
  
  {
   var empid = document.getElementById("Employee_ID_EmpSearch").value;
    url = "hrms.htm?actionFlag=insertDeducDtls&batchupdate=true&edit=Y&size="+size+"&txtEmpId=" + empid;
  }
   document.insEmpMpg.action = url;
   document.insEmpMpg.submit();
  
}


function checkComboStatesDefault(allow_code,val,amount,status,vpfAmt)
{
//alert('in checkComboStatesDefault main page funct..' + val + 'amount ' + amount);

 for(i=0;i<document.insEmpMpg.tax_name.length;i++)
  if(document.insEmpMpg.tax_name[i].value == val)
   {
     //document.insEmpMpg.tax_name[i].checked = true;     
     //document.insEmpMpg.tax_name[i].disabled = true;
     

     if(status==0)
     {
     	amount=vpfAmt;
     }

      var txtname = 'txt' + val; 
      var txtIsChecked = 'txtIsChecked' + val;
      var allowcodeId = 'txtDeducCode' + val;
      
  //          alert('textbox name ' + txtname);
//      alert('value of textbox is ' + opener.document.getElementById(txtname).value);
     document.getElementById(txtname).value = amount;
     document.getElementById(txtIsChecked).value="1";
     document.getElementById(txtname).style.display = '';
     document.getElementById(txtname).disabled=true;
     var btnAdd = 'btn_add' + val; 
     
     
     document.getElementById(btnAdd).style.display = 'none';
     document.getElementById(btnAdd).value='Edit';
     document.getElementById(allowcodeId).value=allow_code;
   }
}

function chkValue()
{
	
	var empId=document.getElementById("Employee_ID_EmpAllowSearch").value;
	if(empId=="")
	{
		alert("Please search the employee first");
	}
	else
	{

		document.getElementById("Employee_ID_EmpSearch").value=empId;
		document.getElementById("Emp_allow").value='y';
		
		addOrUpdateRecord('ChkEmp', 'chkEmpDetail', new Array('Employee_ID_EmpSearch','Emp_allow'));
		
		document.getElementById("Employee_ID_EmpSearch").value='';
		document.getElementById("Emp_allow").value='';
	}
	
}

function ChkEmp()
{
	var empId=document.getElementById("Employee_ID_EmpAllowSearch").value;
	if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
				var empMapping = XMLDocForAjax.getElementsByTagName('emp-mapping');	
					
					if(empMapping.length != 0) {	
							var emp = empMapping[0].childNodes[0].text;		
							//added by samir for contractual and fixe scale employee
							var emptype = empMapping[0].childNodes[1].text;		
							//if(emptype==300225 || emptype==300018)
							if(emptype=="{fixedEmpType}" || emptype=="{contractEmpType}")
							{
							clearEmployee("EmpAllowSearch");
							alert("Not Accessible For Fixed and Contractual Employee!!");
							return;
							}
							//end
							
								if(emp<0)
								{
									alert("The Employee information is not entered in the system.");
									return false;
								}
								if(emp=="n")
								{
									alert("The Employee other Detail is not entered in the System.");
									return false;
								}
								
								if(emp=="y")
								{
									url = "hrms.htm?actionFlag=getDeducType&EmpDeducDtls=Y&EmpId=" + empId;
									window.location=url;
								}
							}
								
			}
		}
}
function chknumber1(number1)
{
        name1='txt'+number1;
		var field_value=document.getElementById(name1).value;
        var periodpos="";
		var atpos="";
        var rule_num="0123456789.";
		if(field_value!="")
		{
		
		for(var i=0;i<field_value.length;i++)
		{
        var cchar=field_value.charAt(i);
        if(rule_num.indexOf(cchar)==-1)
        {
          alert("Enter Valid amount");
          document.getElementById(name1).value="";
          document.getElementById(name1).focus();
          return false;
        }
        }
        } 
}

</script>
<body>
<hdiits:form name="insEmpMpg" validate="true" method="POST" 
	          action="javascript:insertEmpAllowMpg1()">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>Employee Deduction Details</b></a></li>
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">
<div id="tcontent1" class="halftabcontent" tabno="0">

    <table class="tabtable" width="60%">

   <!--<TR>
   <td colspan="3" align="right"><b><hdiits:caption captionid="EMPMAP.empName" bundle="${commonLables}"/><b></td>

   
   <td>
   <hdiits:text name="txtEmpName" caption="Employee Name" captionid="EmpName"
   default="${empList[0].orgEmpMst.empFname} ${empList[0].orgEmpMst.empMname} ${empList[0].orgEmpMst.empLname}" validation="txt.isrequired" mandatory="true" readonly="true"/>
   <hdiits:hidden name="txtEmpId" caption="Employee ID" captionid="EmpID" default="${empList[0].empId}"/>

        

 
<img src="images/payroll/search_icon.gif" onclick="showEmpName()">
   </TD>
   

   </TR>
   --></table>
   
<tr><td colspan="4"><table id="jsptable" align="center"><tr><td>
   		<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
			<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
			<jsp:param name="SearchEmployee" value="EmpAllowSearch"/>
			<jsp:param name="formName" value="insEmpMpg"/>
		</jsp:include>  
	<hdiits:hidden name="Employee_ID_EmpSearch"></hdiits:hidden>
	<hdiits:hidden name="Emp_allow"></hdiits:hidden>
	</td></tr>
	<tr><td align="center">
		<fieldset style="background: #eeeeee;">
				<hdiits:button type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="chkValue()"/>	
		</fieldset>
	</td></tr>
	</table>
	<table id="jsptext" style="display:none"  width="65%" align="center">
		<tr>
		<td width="50%"><b><hdiits:caption captionid="EMPMAP.empName" bundle="${commonLables}"/></b></td>
		<td width="50%"><hdiits:text name="EmpName" caption="EmpName" captionid="EMPMAP.empName" id="txtEmpName"  readonly="true"   /></td>
		</tr>
		
		<tr>
			<td><b>Options</b></td>
		<td >
	
                <hdiits:select onchange="showAllowances()" default ="2" name="cstrStatus" size ="1" captionid="drop_down"  >
				<hdiits:option  value="1">Allowances </hdiits:option>
				<hdiits:option value="2">Deductions </hdiits:option>
				</hdiits:select>    
	</td></tr>	
	</table>
	</td>
	
	</tr>
   </table>
  <br>
   <table width="100%" id="empTable"> 
 <tr width="100%">
 <td width="100%">
	  	 
   <display:table name="${otherList}" requestURI="" pagesize="${pageSize}"    id="row" export="true" >
   		<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" >
   
		 <c:choose>
			<c:when test="${row.empType eq fixedEmpType}">
			${row.empName} [Fix Pay]
			</c:when>
			<c:when test="${row.empType eq contractEmpType}">
			${row.empName} [Contr.]
			</c:when>
			<c:otherwise>
			<a href="./hrms.htm?actionFlag=getDeducType&EmpDeducDtls=Y&EmpId=${row.empId}"> ${row.empName} </a> 
			</c:otherwise>
		 </c:choose>
		 </display:column>
		 <display:column  class="tablecelltext" title="Class Name"  headerClass="datatableheader" > 
             ${row.gradeName}
		 </display:column>
		 <display:column  class="tablecelltext" title="Designation Name"  headerClass="datatableheader" >  
		     ${row.dsgnName}
		 </display:column>
		  <display:setProperty name="export.pdf" value="true" />
			
	</display:table>
	  	 
	</td></tr></table>
   
	<c:set var="deduccode" value="${0}"/>
   <c:choose>
   <c:when test="${deducMpgSize eq 0}">
   <table  width="65%" align="center" id="deduction" style="display:none">
    </c:when>
    <c:otherwise>
      <table width="65%" align="center"   id="deduction">
    </c:otherwise>
  </c:choose>	
			<c:forEach var="deducType" items="${actionList}">
			<script>
			size++;
			</script>
			<c:if test="${deducType[0] ne ptId }">
			   <c:if test="${deducType[0] ne vpfId }">
			     <c:if test="${deducType[0] ne cpfId }">
			   <c:if test="${deducType[0] ne surItId }">
			    <c:if test="${deducType[0] ne GpfId }">
			    <c:if test="${deducType[0] ne pliId }">
			
			   <TR>
			   <TD width="50%"> 

					<hdiits:hidden default="${deducType[0]}" name="tax_name" id="tax_name" />
							<b><c:out value="${deducType[1]}"/></b>
							</TD>
							<td width="50%">
							<c:set var="deduccode" value="${deduccode+1}"/>
		                    <hdiits:text onblur="chknumber1(${deducType[0]})" name="txt${deducType[0]}" caption="Allowance" captionid = "Allowance" readonly="true" id="txt${deducType[0]}"  />
		                    <hdiits:button name="btn_add${deducType[0]}" type="button" style="display:none" 
		                    value="Add" onclick="insertEmpDeducDtls(${deducType[0]})"/>		                
		                    <hdiits:hidden name="txtIsChecked${deducType[0]}" caption="Check Status" captionid="CheckStatus" />
		                    <hdiits:hidden name="txtDeducCode${deducType[0]}" />
		                    <hdiits:hidden default="${deducType[0]}" name="deduccode${deduccode}" />
		                    </td>
			
							  	</TR> 
							  	
							  	</c:if>
							  		  	</c:if>
							  		  	</c:if>
							  		  	</c:if>
							  		  	</c:if>
							  		  	</c:if>
			</c:forEach>
			<fmt:setBundle basename="resources.eis.eis_common_lables" var="genLables" scope="page"/>
			<tr><td align="center" colspan="2">
	<%--<jsp:include page="../../core/PayTabnavigation.jsp" />--%>
	
			<table class="tabNavigationBar">
				<tr align="center">
					<td class="tabnavtdcenter" id="tabnavtdcenter" >
					    <hdiits:button name="Back" type="button" captionid="eis.close" bundle="${genLables}" onclick="history.go(-1);return false;"/>
					</td>
				</tr>
			</table>	
			</td></tr>				   
     	</table>
     	
     	
     	 <c:if test="${deducMpgSize ne 0}">
     	 <script>
   				document.getElementById("jsptext").style.display='';
   				document.getElementById("jsptable").style.display='none';
   				document.getElementById("empTable").style.display='none';
   				var value="${hrEisMst.orgEmpMst.empFname} ${hrEisMst.orgEmpMst.empMname} ${hrEisMst.orgEmpMst.empLname}";
   				document.getElementById("EmpName").value=value;
   				document.getElementById("Employee_ID_EmpSearch").value="${hrEisMst.orgEmpMst.empId}";
   				document.getElementById("txt"+${itId}).value="${incometax}";
   	   		</script>
     	    <c:forEach  var="list" items="${deducNamesFromMpg}">  
   	    
        <script>
           checkComboStates(${list[0]}, ${list[1].deducCode}, ${list[2]},${list[3]},${list[4]});
       </script>
    </c:forEach>
    </c:if>
    
     	 <c:forEach  var="exprList" items="${deducNamesFromExpr}"> 
           <c:forEach  var="list" items="${deducNamesFromMpg}">
            <c:choose>
             <c:when test="${list[1].deducCode==exprList.hrPayDeducTypeMst.deducCode}">
            <script>
                 checkComboStatesDefault(${list[0]}, ${list[1].deducCode}, ${list[2]},${list[3]},${list[4]});    
           </script>
          </c:when>
        </c:choose>
       </c:forEach>
    </c:forEach>
  
</div>


	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	   if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getDeducType";
			document.insEmpMpg.action=url;
			document.insEmpMpg.submit();
		}
		</script>
 
 

	
	<hdiits:validate controlNames="tesxt"
		//locale='<%=(String)session.getAttribute("locale")%>' />
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

