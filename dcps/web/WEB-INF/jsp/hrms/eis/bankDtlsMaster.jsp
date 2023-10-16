<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
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
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	 
<c:set var="bankList" value="${resValue.bankList}" />
<c:set var="accTypes" value="${resValue.bankList}" />
<c:set var="empList" value="${resValue.empList}" />
<c:set var="actionList" value="${resValue.actionList}" />
<c:set var="sysdate" value="${resValue.date}" />
<c:set var="flag" value="${resValue.flag}" />
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="empName" value="${resValue.empName}" />
 <!-- added by ravysh -->
 <c:set var="FromBasicDtlsNew" value="${resValue.FromBasicDtlsNew}" > </c:set>
 
 
<fmt:formatDate value="${resValue.date}" pattern="dd/MM/yyyy" dateStyle="medium" var="date"/>
<script>
var todayDate;
function checkUpdatedtl()
{
if(${flag!='edit'})
{
 if(chkFunc())
 getBranchNames();
}
else
{ 
getBranchNames();
}
 
}
function getBranchNames()
{ 

 if(document.frmInsertBankDtls.cmbBankName.value=='Select')
 {
  alert('Please Enter Bank Name');
  document.frmInsertBankDtls.cmbBankName.value='Select';
 }
 else
 {  
 clearBranchCombo();
  var bankId = document.frmInsertBankDtls.cmbBankName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= '&bankId=' + bankId;
		  var actionf="getBranchNames";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
//          alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=get_Branchs;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
	}

	
 }

function clearBranchCombo()
{
	var v=document.getElementById("branchID").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("branchID").options.length -1;
			document.getElementById("branchID").options[lgth] = null;
	}		
}

function get_Branchs()
{
if (xmlHttp.readyState==complete_state)
 { 						
   var cmbBranchName = document.getElementById("branchID");
   var XMLDoc=xmlHttp.responseXML.documentElement;	

   var branchEntries = XMLDoc.getElementsByTagName('branch-mapping');
   if(branchEntries.length == 0)
   {
     window.status = 'No Records Found.';
   }
   else
   {
    window.status='';
    //alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
    if(branchEntries.length != 0)
    {   
      for ( var i = 0 ; i < branchEntries.length ; i++ )
	  {                 
       val=branchEntries[i].childNodes[0].text;    
       text = branchEntries[i].childNodes[1].text; 
       var y = document.createElement('option')   
	   y.value=val;
	   y.text=text;
	    			        
 	   try
 	   {	      				    					
         cmbBranchName.add(y,null);
	   }
	   catch(ex)
	   {
	     cmbBranchName.add(y); 
	   }
	  }
    }
  }
 }
}


function checkifWrongFromDate()
{
	todayDate='${date}';
	var txtFromDate=document.frmInsertBankDtls.txtStartDate.value;
	var result=compareDate(txtFromDate,todayDate);

				if(result>=0)
				{
				return true;
				}
				else
				{
					alert("<fmt:message key="ALERT.FROMDATETODAY" bundle="${commonLables}" />");
					return false;						
				}
}
function chkFunc()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	var retValue=true;
	if(empId=="")
	{
		alert("Please search the employee first");
		document.frmInsertBankDtls.cmbBankName.value='Select';
		retValue=false;
	}
	else
	{
		try 
		{   
			xmlHttp=new XMLHttpRequest();
	   	}
		catch(e)
		{    
			// Internet Explorer    
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
				    retValue=false;     
				}
			}
		}
		var url = "hrms.htm?actionFlag=getBankDtlsView&empId="+empId+"&chk=1";  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
					if(loanAdvMapping.length != 0) {
					    //-1 for no emp record ,0 for ban deatils constarint,1 for add allow flag	
						LoanNo = loanAdvMapping[0].childNodes[0].text;
						
						if(LoanNo==-1){
							var res=confirm("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered into the system.\n Want to Insert the information.");
							
							if(res){
							
							var url = "./hrms.htm?actionFlag=newEmpData&newEntryEmpId="+empId;
							document.frmInsertBankDtls.action=url;
							document.frmInsertBankDtls.submit();
							retValue=true;
							}
							else
							{
								retValue=false;
							}
						}
						else if(LoanNo==-2){
							var res=confirm("The other Details for "+document.getElementById("Employee_Name_EmpSearch").value+" is  not entered into the system.\n Want to Insert the information.");
							
							if(res){
							
							var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&newEntryEmpId="+empId;
							document.frmInsertBankDtls.action=url;
							document.frmInsertBankDtls.submit();
							retValue=true;
							}
							else
							{
								retValue=false;
							}
						}
						else if(LoanNo==0){
							alert("The details for "+document.getElementById("Employee_Name_EmpSearch").value+" is  already entered in bank Details");
							document.frmInsertBankDtls.cmbBankName.value='Select';
							retValue=false;
							
						}
						else
						{
							retValue=true;
						}
						
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	}
}
function validateForm1()
{	
 var uri = "./hrms.htm?actionFlag=";
 var url = uri + document.frmInsertBankDtls.txtAction.value;
 document.frmInsertBankDtls.action = url;
 document.frmInsertBankDtls.submit();	
}
function validateForm()
{

 if(${flag!='edit'})
 document.frmInsertBankDtls.cmbEmpName.value=document.getElementById("Employee_ID_EmpSearch").value;

 if(${flag!='edit'}&&chkFunc()!=true)
 {
   return false;
 }
 else if(checkifWrongFromDate())
  return true;
 else 
 {
   document.frmInsertBankDtls.txtStartDate.value='';
   return false;
  }
}


function compareDate()
{
		var myDate=new Date();
		var today=new Date();
	var accStrtdt=document.frmInsertBankDtls.txtStartDate.value;
	var accdate= accStrtdt.split("/");
	
	myDate.setFullYear(accdate[2],accdate[1]-1,accdate[0]);
	if(myDate>today){
		alert('Account start date cannot be greater than todays date');
return false;
	}
	else 
		return true;
}

</script>


<c:if test="${flag ne 'edit'}">
<body>
</c:if>
<hdiits:form name="frmInsertBankDtls" validate="true" method="POST"
	action="javascript:validateForm1()">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	    <c:choose>
		 <c:when test="${flag eq 'edit'}">
		  <li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.updatebankdtls" bundle="${commonLables}"/> </b></a></li>
         </c:when>
         <c:otherwise>
		  <li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.addbankdtls" bundle="${commonLables}"/> </b></a></li>
		 </c:otherwise>          
        </c:choose>
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">  

   <TABLE  align="center" width="85%">
   <tr><td colspan="4">&nbsp;</td></tr>
   <c:if test="${flag eq 'edit'}">
<hdiits:hidden name="bankDtlsId" default="${actionList.bankDtlId}"/>   
</c:if>

 <tr>
			<c:set var="readonly" value="false"/>
			<c:choose>
			<c:when test="${flag=='edit'}">
			<TD> <b><fmt:message key="EMP.NAME" bundle="${commonLables}"/> </b></TD>			
			 
			<TD>
			<c:set var="readonly" value="true"/> 
			<hdiits:text name="empName" readonly="true" default="${actionList.hrEisEmpMst.orgEmpMst.empFname} ${actionList.hrEisEmpMst.orgEmpMst.empMname} ${actionList.hrEisEmpMst.orgEmpMst.empLname}"/>
			<!--  //<hdiits:select name="cmbEmpName" validation="sel.isrequired" id="empID" size="1"  mandatory="true" 
			caption="Employee Name" sort="false" readonly="${readonly}">
	        <hdiits:option value="Select">-------------------Select-------------------</hdiits:option>

				
		      <c:forEach items ="${resValue.empList}" var="list">
		      <c:choose>
		      <c:when test="${list.orgEmpMst.empId eq  actionList.hrEisEmpMst.orgEmpMst.empId}">
                <hdiits:option value="${actionList.hrEisEmpMst.empId}" selected="true">${actionList.hrEisEmpMst.orgEmpMst.empFname} ${actionList.hrEisEmpMst.orgEmpMst.empMname} ${actionList.hrEisEmpMst.orgEmpMst.empLname} </hdiits:option>		      
              </c:when>
              <c:otherwise>
			    <hdiits:option value="${list.orgEmpMst.empId}">${list.orgEmpMst.empFname} ${list.orgEmpMst.empMname} ${list.orgEmpMst.empLname} </hdiits:option>
			  </c:otherwise>
			  </c:choose>
			  </c:forEach>
		    </hdiits:select>-->
		   </td>
          </c:when>
    	     <c:otherwise>
				<TD class="fieldLabel" colspan="4">
							<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
								<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
								<jsp:param name="SearchEmployee" value="EmpSearch"/>
								<jsp:param name="formName" value="empLeave"/>
								<jsp:param name="functionName" value="chkFunc"/>
					</jsp:include>
					<hdiits:hidden id="cmbEmpName" name = "cmbEmpName"   />
				 </td>
			  </c:otherwise>          
          </c:choose>
		</TR>
<tr>
   <tr><td colspan="4">&nbsp;</td></tr>
<td align="left" width="15%" ><b><fmt:message key="BM.bankName" bundle="${commonLables}"/> </b></TD>			
<td align="left" width="30%" >	<hdiits:select name="cmbBankName" sort="false" validation="sel.isrequired" id="bankID" size="1"  mandatory="true" 
			caption="Bank Name" onchange="checkUpdatedtl()">
			<hdiits:option value="Select">-------------------Select-------------------</hdiits:option>

		     <c:forEach items ="${resValue.bankList}" var="bankList">
		     <c:choose>
		     <c:when test="${bankList.bankId eq actionList.hrEisBankMst.bankId}">
    			  <hdiits:option value="${actionList.hrEisBankMst.bankId}" selected="true"> ${actionList.hrEisBankMst.bankName} </hdiits:option>
    	     </c:when>
    	     <c:otherwise>
			   <hdiits:option value="${bankList.bankId}"> ${bankList.bankName} </hdiits:option>
			  </c:otherwise>
			 </c:choose>
			</c:forEach>  
			</hdiits:select>		       
			</TD>
<td align="left" width="15%" ><b><fmt:message key="BR.NAME" bundle="${commonLables}"/> </b></TD>			
<td align="left" width="30%" >	<hdiits:select name="cmbBranchName" validation="sel.isrequired" id="branchID" size="1"  mandatory="true" 
			caption="Branch Name" sort="false">
          <hdiits:option value="Select">-------------------Select-------------------</hdiits:option>
          <c:if test="${actionList ne null}">
          	  <hdiits:option value="${actionList.hrEisBranchMst.branchId}" selected="true"> ${actionList.hrEisBranchMst.branchName} </hdiits:option>
          </c:if>
         </hdiits:select>
     </td>
 
 
 <tr>
			<TD> <b><fmt:message key="ACC.TYPE" bundle="${commonLables}"/></b></TD>
			<TD><hdiits:select name="cmbAccType" size ="1" captionid="accType" caption="Acc Type"  id="cmbAccType"
			validation="sel.isrequired"  sort="false" mandatory="true"> 
			<hdiits:option value="select">-------------------Select------------------- </hdiits:option>
 
			<c:forEach items ="${resValue.accTypes}" var="list">
			<c:choose>
			<c:when test="${list.lookupId eq actionList.bankAcctType}">
			<hdiits:option value="${list.lookupId}" selected="true"> ${list.lookupDesc} </hdiits:option>
			</c:when>
			<c:otherwise>
			<hdiits:option value="${list.lookupId}"> ${list.lookupDesc} </hdiits:option>			
			</c:otherwise>
			</c:choose>
			</c:forEach>  
			</hdiits:select></TD>	


<c:choose>
	<c:when test="${resValue.flag eq 'edit'}">
      <hdiits:hidden name="txtAction" default="insertBankDtls&edit=Y"/>
     </c:when>
    <c:otherwise>
      <hdiits:hidden name="txtAction" default="insertBankDtls&edit=N"/>
    </c:otherwise>
   </c:choose>

<!-- added by ravysh -->
 <hdiits:hidden name="FromBasicDtlsNew" default="${FromBasicDtlsNew}"/>

	<TD align="left" width="15%" > <b><fmt:message key="ACC.NO" bundle="${commonLables}"/></b></TD>
			<TD align="left" width="30%" > <hdiits:text
				name="txtAccNo" captionid="ACC.NO" bundle="${commonLables}"
				 size="30" maxlength="19" default="${actionList.bankAcctNo}" id="txtAccNo" mandatory="true" validation="txt.isrequired" onblur="checkSplCharExceptArg(this,'-/')"/>
				</TD>
</TR>
<tr>

</tr>
 
 <tr>
				
					 				
             <TD align="left" width="15%" > <b><fmt:message key="ACC.ST.DATE" bundle="${commonLables}"/></b></TD>
			<TD align="left" width="30%" >
			<c:choose>
			<c:when test="${flag ne 'insert'}">
				<hdiits:dateTime name="txtStartDate" mandatory="true" validation="txt.isdt,txt.isrequired" captionid="ACC.ST.DATE" bundle="${commonLables}" default="${actionList.bankAcctStartDt}"/>	 
			</c:when>
			<c:otherwise>
				<hdiits:dateTime name="txtStartDate" mandatory="true"  validation="txt.isdt,txt.isrequired" captionid="ACC.ST.DATE" bundle="${commonLables}"/>	 		
			</c:otherwise>
			</c:choose>
				</TD>	
 </tr>
 	
 
 
	</table> 
    <br>
 </div>
 
<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
	 <hdiits:hidden default="getBankDtlsView" name="givenurl"/>
	
	 <c:if test="${FromBasicDtlsNew eq 'YES'}">
<hdiits:hidden default="YES" name="closeWindow"/>
</c:if>
	<c:choose>
 		<c:when test="${resValue.flag eq 'edit'}">
 		
 			<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
			<jsp:include page="../../core/PayTabnavigation.jsp" />
		</c:when>
	  <c:otherwise>
			<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>
	        <jsp:include page="../../core/PayTabnavigation.jsp" />
	  </c:otherwise>
	</c:choose>
	
	
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${empName}"!=null&&"${empName}"!='')
		{
				document.frmInsertBankDtls.Employee_srchNameText_EmpSearch.value="${empName}";
			    GoToNewPageEmpSearch();
		}
		
		
	    document.frmInsertBankDtls.cmbBankName.focus();
	
	
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			
			alert("${msg}");
			
			if("${FromBasicDtlsNew}"!='YES')
			{
			var url="./hrms.htm?actionFlag=getBankDtlsView";
			document.frmInsertBankDtls.action=url;
			document.frmInsertBankDtls.submit();
			}
			else{
				
				window.close();
			}
		}
		
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

