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
<script type="text/javascript" src="common/script/commonfunctions.js"></script>




<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.schedulerList}" ></c:set>

<c:set var="msg" value="${resValue.msg}" ></c:set>

<c:set var="hrEisEmpMst" value="${resValue.hrEisEmpMst}" ></c:set>
<c:set var="DesgName" value="${resValue.DesgName}" ></c:set>
<c:set var="PostName" value="${resValue.PostName}" ></c:set>
<c:set var="DCG" value="${resValue.DCG}" ></c:set>
<c:set var="ListofReason" value="${resValue.ListofReason}" ></c:set>


<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<script>
if("${msg}"!='')
alert('${msg}');



function InsertDate()
{
	//alert("in submit..");
	if(document.getElementById("EndDate").value=="") 
	{
		alert('Please enter end of service date');
		return;
	}
	if(document.getElementById("ReasonForEnd").value=="Select") 
	{
		alert('Please select reason for end of service.');
		return;
	}
	if(document.getElementById("oderRefNo").value=="") 
	{
		alert('Please enter oder/ref. letter no.');
		return;
	}
	
	if(document.getElementById("OrderDated").value=="") 
	{
		alert('Please enter order date.');
		return;
	}

	
	var answer = confirm ("Do you want to submit?");
	  if (answer)
	  {
		document.EmpEndDate.action='hrms.htm?actionFlag=InsertEmpEndDate';
		document.EmpEndDate.submit();
	  }
	  
}
function onBackfn()
{
	//alert("in close...");
	document.EmpEndDate.action='./hrms.htm?actionFlag=getEmpDataForEndDate&elementId=9000224';
	document.EmpEndDate.submit();
}
function resetForm()
{
	
	if(confirm("All entered values will be cleared, please confirm!") == true)
	{
		document.forms[0].reset();
	}			  			
		
}

function onlyAlphaWithSpecialChar(control) 
{                
              var iChars = "QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzasdfghjklpoiuytrewq1234567890/ ";
              var value="";
              var valid=true;
              
              for (var i=0; i<control.value.length;i++) 
               {  
               if (iChars.indexOf(control.value.charAt(i))!=-1) 
                 {
            	   re = /['QWERTYUIOPLKJHGFDSAZXCVBNMmnbvcxzasdfghjklpoiuytrewq']/;
            	   
            	    if((!re.test(control.value)) ) {
            	      alert("Order no must contain at least one alphabet!");
            	      control.focus();
            	      return false;
            	    }
            	    
                    value=value+control.value.charAt(i);
                 }               
                 else
                 {                 
                    valid=false;
                 }
              }                   
              if(!valid)
              {              
                   control.value="";
                  alert('Special characters are not allowed.');             
                  control.value="";
                  control.focus();
                  return false;
              }
              return true;              
}
function onlyNumbers(control) 
{          
			var e1=  control.value;
			control.value = (e1.replace(/^\W+/,'')).replace(/\W+$/,'');     
           var iChars = "1234567890";
           var value="";
           var valid=true;
           
           for (var i=0; i<control.value.length;i++) 
            {              
               if (iChars.indexOf(control.value.charAt(i))!=-1) 
              {
              
                 value=value+control.value.charAt(i);
              }               
              else
              {                 
                 valid=false;
              }
           }                   
           if(!valid)
           {              
               alert('Alphabets and special characters are not allowed');             
               control.value="";
               control.focus();
               return false;
           }
           return true;              
}
</script>



<hdiits:form name="EmpEndDate" validate="true" method="POST" action="" encType="multipart/form-data" >

   	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1" ><b><fmt:message key="EmpEndDate.Head" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	<table class="tabtable" border="0" bordercolor="black">
			
			<TR>
			<td></td>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="EmpEndDate.EmpName" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<td><b>:</b></td>
			<TD>
				  ${hrEisEmpMst.orgEmpMst.empPrefix} ${hrEisEmpMst.orgEmpMst.empFname} ${hrEisEmpMst.orgEmpMst.empMname} ${hrEisEmpMst.orgEmpMst.empLname}
				
			</TD>
			<td width="18%">
			</td>
			</TR>
			
	<%-- 
			<TR>
			<td></td>
			<TD class="fieldLabel" ><b><hdiits:caption  captionid="EmpEndDate.Post" bundle="${commonLables}"></hdiits:caption>:</b>
			</TD>
			<TD>
				 ${PostName} 
			</TD>
			<td width="18%">
			</TR>
			--%>		
			
			<TR>
			<td></td>
			<TD class="fieldLabel" ><b><hdiits:caption  captionid="EmpEndDate.Desig" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<td><b>:</b></td>
			<TD>
				 ${DesgName} 
			</TD>
			<td width="18%">
			</TR>
			
			<TR>
					<td width="22%"></td>
					<TD class="fieldLabel">DCPS / GPF </TD>
					<td><b>:</b></td>
					<TD>
					      ${DCG}
					</TD>
					<td width="18%">
				</TR>
			
			<tr bgcolor="#9F5C04">
			<td class="fieldLabel" colspan="10">
 			<font color="#ffffff">
			<strong>End of Service Details</strong></font></td>
			</tr>
			
			
			<TR>
			<td></td>
			<td class="fieldLabel"><b><hdiits:caption captionid="EmpEndDate.EndDateServe" bundle="${commonLables}"/></b></td>
			<td><b>:</b></td>
			<td class="fieldLabel"><hdiits:dateTime name="EndDate" captionid="EmpEndDate.EndDateServe"  bundle="${commonLables}" mandatory="true"
			validation="txt.isdt,txt.isrequired"  minvalue=""></hdiits:dateTime></td>
			
			<td width="30%"></td>
			</TR>
			
			<tr>
			<td></td>
			<td class="fieldLabel"><b><hdiits:caption captionid="EmpEndDate.Resason" bundle="${commonLables}"></hdiits:caption></b></td>
			<td><b>:</b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select id="ReasonForEnd" name="ReasonForEnd" captionid="EmpEndDate.Resason" bundle="${commonLables}"	validation="sel.isrequired" mandatory="true">
					<hdiits:option value="Select">--<fmt:message	key="eis.select" bundle="${commonLables}"></fmt:message>--</hdiits:option>
					<c:forEach var="Reason" items="${ListofReason}">
						<option value=<c:out value="${Reason.lookupId}"/>><c:out value="${Reason.lookupDesc}" /></option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td width="18%">
			</tr>
			
			<tr>
				<td></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="EmpEndDate.ElPension" bundle="${commonLables}"/></b></td>
				<td><b>:</b></td>
				<td class="fieldLabel" >
					<hdiits:radio name="rdoEligiblePen" id="rdoEligPenYes" value="true" default="true" captionid="EmpEndDate.Yes" bundle="${commonLables}" />
					<hdiits:radio name="rdoEligiblePen"  id="rdoEliPenNo" value="false" captionid="EmpEndDate.No"  bundle="${commonLables}" />
				</td>
				<td width="18%">
			</tr>
			
			<tr>
				<td></td>
				<td class="fieldLabel"><b><hdiits:caption captionid="EmpEndDate.ElGrauity" bundle="${commonLables}"/></b></td>
				<td><b>:</b></td>
				<td class="fieldLabel" >
					<hdiits:radio name="rdoEligibleGrat" id="rdoEliGratYes" value="true"  default="true" captionid="EmpEndDate.Yes" bundle="${commonLables}" />
					<hdiits:radio name="rdoEligibleGrat"  id="rdoEliGratNo" value="false" captionid="EmpEndDate.No"  bundle="${commonLables}" />
				</td>
				<td width="18%">
			</tr>
			
			<tr bgcolor="#9F5C04">
			<td class="fieldLabel" colspan="10">
 			<font color="#ffffff">
			<strong>Order Details</strong></font></td>
			</tr>
			
			<TR>
			<td></td>
			<td class="fieldLabel"><b><hdiits:caption captionid="EmpEndDate.orderRef" bundle="${commonLables}"/></b></td>
			<td><b>:</b></td>
			<td class="fieldLabel"><hdiits:text name="oderRefNo" id="oderRefNo" mandatory="true" captionid="EmpEndDate.orderRef" bundle="${commonLables}" validation="txt.isrequired" maxlength="25" onblur="onlyAlphaWithSpecialChar(this)"></hdiits:text></td>
			<td width="18%">
			</TR>
			
			<TR>
			<td></td>
			<TD class="fieldLabel"><b><hdiits:caption captionid="EmpEndDate.Dated" bundle="${commonLables}"/></b></td>
			<td><b>:</b></td>
			<td width="30%" class="fieldLabel"><hdiits:dateTime name="OrderDated" captionid="EmpEndDate.Dated"  bundle="${commonLables}" mandatory="true" validation="txt.isdt,txt.isrequired" minvalue="" ></hdiits:dateTime></td>
			<td width="18%"></td>
			</TR>
			
			<TR>
			<td></td>
			<TD class="fieldLabel"><b><hdiits:caption captionid="EmpEndDate.Remarks" bundle="${commonLables}"/></b></td>
			<td><b>:</b></td>
			<td width="40%" height="50%"><hdiits:textarea rows="3" cols="30" name="Remarks" id="Remarks" captionid="EmpEndDate.Remarks"  bundle="${commonLables}" maxlength="200"  onblur=""></hdiits:textarea></td>
			<td width="18%"></td>
			</TR>
			
			
		</table>	

	 </div>
	 <br/>
	 <div>	
 	
 		<table class="tabNavigationBar">
 			<tr>
 			
			<td class="tabnavtd" id="tabnavtd" align="justify" width="35%"></td>
			<td align="justify" width="2%">
				<hdiits:button type="button" name="formSubmitButton" onclick="InsertDate()"  value="Save"  />
				</td>
				<td  align="justify" width="2%">
			    <hdiits:button type="button" name="backButton" value="Close" onclick="onBackfn()" /></td>
			    <td  align="justify" width="2%">
	    		<hdiits:button type="button" name="resetButton" value="Reset" onclick="resetForm()" /></td>
	    		<td width="30%">
	    	</td>
	    	
			</tr>
		</table>
 	</div>
 	<hdiits:hidden default="${hrEisEmpMst.empId}" id="hdnEmpId" name="hdnEmpId"></hdiits:hidden>

 	 
<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
		
<script type="text/javascript"> initializetabcontent("maintab");

//document.getElementById("rdoEligPenYes").checked=true;
//document.getElementById("rdoEligPenYes").checked=true;

</script>	
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</html>
	