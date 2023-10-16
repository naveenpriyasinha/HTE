<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstEmp" value="${resValue.lLstEmp}" />
<c:set var="UserType" value="${resValue.UserType}" />
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript">
var selectedYear="";
function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
function displayDataForGivenYear()
{
	selectedYear = document.getElementById("cmbFinancialYear").text ;
	self.location.href='ifms.htm?actionFlag=loadSixPCArrearAmountSchedule&UserType=DDOAsst&yearId='+document.getElementById("cmbFinancialYear").value;
	document.getElementById("year").value = document.getElementById("cmbFinancialYear").text ;
}
function displayDataForGivenYearDDO()
{
	selectedYear = document.getElementById("cmbFinancialYear").text ;
	self.location.href='ifms.htm?actionFlag=loadSixPCArrearAmountScheduleDDO&UserType=DDO&yearId='+document.getElementById("cmbFinancialYear").value;
	document.getElementById("year").value = document.getElementById("cmbFinancialYear").text ;
}
function printArrearScheduleReport()
{
				
				var cmb=document.forms[0].chkbxFormVeri;
				var flag=0;
				var Emp_Id="";
				
				if(cmb.checked == true)
				{
					flag = 1;
					Emp_Id = cmb.value;
				}
				else
				{	
					var selectedFlag=false;
					if(cmb!=null )
					{
						if(cmb.length != null)
						{
							for(i=0;i<cmb.length;i++)
							{
								if(cmb[i].checked == true)
								{
									flag = 1;
									if(i==cmb.length-1)
									{
									Emp_Id += cmb[i].value ;
									}
									else
									{
									Emp_Id += cmb[i].value + "~";
									}
								}
							}
						}
					}
				}
				if(flag ==1)
				{
					
					url = "ifms.htm?actionFlag=reportService&reportCode=700012&action=generateReport&empid="+Emp_Id;
					window_new_update(url);
				}
				else
				{
					alert("Please select an employee to print report");
				}
}
</script>

<hdiits:form name="DCPSArrearAmtSchedule" id="DCPSArrearAmtSchedule" encType="multipart/form-data" validate="true" method="post" >
<fieldset style="width: 100%" class="tabstyle">
<legend><b>Select Financial Year</b></legend>
<table align="center" width="50%">
   <tr align="center">
	       <td align="left" width="20%"><fmt:message key="CMN.FINANCIALYEAR"
					bundle="${dcpsLabels}"></fmt:message>
		   </td>
		   
		   <td align="left" width="60%">
		       <select name="cmbFinancialYear" id="cmbFinancialYear" style="width: 40%" >				
				 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>  
				 <c:forEach var="yearVar" items="${resValue.lListYears}">					
						<option value="${yearVar.id}">
								<c:out value="${yearVar.desc}"></c:out>					
						</option>
				 </c:forEach>
			   </select>
		   </td>
	   </tr>
	 <tr></tr>
	 <tr></tr>
	<tr align="center">
	    <td align="left" width="20%"></td>
	  	   <td align="center" >
	  	   
	  	   <c:if test="${UserType == 'DDOAsst'}">
	  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
		               bundle="${dcpsLabels}" onclick="displayDataForGivenYear();" style="size:25"/>
		               </c:if>
		                  <c:if test="${UserType == 'DDO'}">
	  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
		               bundle="${dcpsLabels}" onclick="displayDataForGivenYearDDO();" style="size:25"/>
		               </c:if>
		   </td>
		 
</tr>
</table>
</fieldset>
<c:set var="counter" value="1" />
<br></br>
<c:if test="${(resValue.lLstEmp != null )}">
<fieldset style="width: 100%" class="tabstyle">
<legend><b> Yearly Process of 6th PC Arrear Amount Deposition To DCPS Print Schedule</b></legend>
    <display:table list="${lLstEmp}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri" id="chkbxFormVeri" value="${vo[0]}"/>
		
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.EMPSRNO" >		
				<c:out value="${counter}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.FINYEAR" >		
			<c:out value="${vo[5]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.DCPSID" >		
				<c:out value="${vo[1]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<c:out value="${vo[2]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.YEARLYAMOUNT" >		
				<c:out value="${vo[3]}" />
		</display:column>
	
			
			<c:set var="counter" value="${counter+1}" />
 			
	</display:table>
	</fieldset>
</c:if>
	
	<c:if test="${resValue.lLstEmp != null}">
	<br></br>
	<table align="center">
		<tr>
		<td>
			<hdiits:button name="btnPrint" id="btnPrint" type="button"  captionid="BTN.PRINT" bundle="${dcpsLabels}" onclick="printArrearScheduleReport();"/>
		</td>
		</tr>
	</table>
		
	</c:if>
	

</hdiits:form>	
