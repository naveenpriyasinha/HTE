<%
try {
	
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>


<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="PayLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="scalemst" value="${resValue.scalemstList}"> </c:set>
<c:set var="PayFixationList" value="${resValue.PayFixationList}" ></c:set>
<c:set var="hrPayfixMst" value="${resValue.hrPayfixMst}" />
<c:set var="currentDate" value="${resValue.currentDate}" />




<script type="text/javascript">


  
   

function insert(){
document.frmVac.action="hrms.htm?actionFlag=transac";
document.frmVac.submit();

}

function Closebt()
{	
	method="POST";
	document.frmVac.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmVac.submit();
}


</script>


<hdiits:form name="frmVac" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Pf.PayFixation" bundle="${PayLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
	<hdiits:hidden name="actionFlag" default="transac"/>
	<div id="tcontent1" class="tabcontent" tabno="0">	
	
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
  
   
    <br>
    <hdiits:fieldGroup titleCaptionId="Pf.fix" bundle="${PayLab}" mandatory="true" id="payfixfieldGrp">
  
	
	<table width="100%">
	<tr>
	<td width="25%">
	<b><hdiits:caption captionid="Pf.adddate" bundle="${PayLab}"></hdiits:caption></b>
	</td>
	<td id="date1" width="25%">
	${currentDate}
	</td>
	
	  
	  <td width="25%">
		<b><hdiits:caption captionid="Pf.prepay" bundle="${PayLab}"></hdiits:caption></b></td>
		 
		 <hdiits:hidden name="userid" default="${hrPayfixMst.userId.userId}" />
		   <td width="25%">
		  
		   <c:out  value="${hrPayfixMst.revPay}"default=""></c:out>
	       </td>
	       </tr>
	       
		
		
		<tr>
		<td width="25%">
		<b><hdiits:caption captionid="Pf.presentscale" bundle="${PayLab}"></hdiits:caption></b>
	       </td>
	       
	       <hdiits:hidden name="currscalid" default="${hrPayfixMst.revPayScale}" />
	       <td width="25%">
	        <c:out  value="${hrPayfixMst.revPayScale.scaleStartAmt}"default=""></c:out><c:out value="-"/><c:out  value="${hrPayfixMst.revPayScale.scaleIncrAmt}"default=""/><c:out value="-"/><c:out  value="${hrPayfixMst.revPayScale.scaleEndAmt}"default=""/>
	       </td>
	       
	
	       	
    <td width="25%" ><b><hdiits:caption captionid="Pf.scale" bundle="${PayLab}"></hdiits:caption></b></td>
		
		<td width="25%"><hdiits:select name="scalemst" size="1"
                       captionid="Pf.scale"   
                       
                       bundle="${PayLab}" mandatory="true" validation="sel.isrequired" sort="false">
        <hdiits:option value=""><fmt:message key="Payfix.select" bundle="${PayLab}" /></hdiits:option>
        <c:forEach var="scalemst" items="${resValue.scalemstList}">
         <hdiits:option value="${scalemst.scaleId}"><c:out value="${scalemst.scaleStartAmt}"/><c:out value="-"/><c:out value="${scalemst.scaleIncrAmt}"/><c:out value="-"/><c:out value="${scalemst.scaleEndAmt}"/> </hdiits:option>
        </c:forEach>
        </hdiits:select >
        </td>
          
            </tr>
	
	<tr></tr>
	<tr>
<td width="25%"><b><hdiits:caption captionid="Pf.fixDate" bundle="${PayLab}"/></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td width="25%"><hdiits:dateTime name="fromdate" captionid="Pf.fixDate" bundle="${PayLab}" mandatory="true" validation="txt.isrequired" maxvalue="31/12/2099"></hdiits:dateTime></td>

	
            
<td width="25%">
<b><hdiits:caption captionid="Pf.payfixation" bundle="${PayLab}"></hdiits:caption></b></td>
			<td width="25%">
			<hdiits:select captionid="Pf.payfixation" bundle="${PayLab}" name="payfixation" mandatory="true" validation="sel.isrequired" sort="true"> 
			<hdiits:option value="Select"><fmt:message key="Payfix.select" bundle="${PayLab}" /></hdiits:option>
			<c:forEach var="payfixation" items="${PayFixationList}">
	    					<option value=<c:out value="${payfixation.lookupId}"/>>
							<c:out value="${payfixation.lookupDesc}"/></option>
			</c:forEach>	
			</hdiits:select>	
</td>
</tr>
	      
 
	
		   </table>	
		   </hdiits:fieldGroup>
<br><br><br><br>
<table align="center">
                 <hdiits:formSubmitButton name="Submit" type="button" captionid="Pay.Submit" bundle="${PayLab}"/>
					<hdiits:button type="button" captionid="Pay.Close" name="Close" value="Close" onclick="Closebt()" bundle="${PayLab}"/>
 	
 	</table>

	</div>	
		
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>	

	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
