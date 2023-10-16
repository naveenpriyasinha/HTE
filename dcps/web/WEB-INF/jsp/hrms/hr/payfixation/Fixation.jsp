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
<c:set var="Empfix" value="${resValue.EmpfixList}"> </c:set>
<c:set var="reasonfix" value="${resValue.reasonfixList}"> </c:set>
<html>

<script type="text/javascript">



   

function accep(){
document.frmVac.action="hrms.htm?actionFlag=empacc";
document.frmVac.submit();

}
function Closebt()
{	
	method="POST";
	document.frmVac.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmVac.submit();
}




</script>

</html>

<hdiits:form name="frmVac" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Pf.PayFixation" bundle="${PayLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
	<hdiits:hidden name="actionFlag" default="empacc"/>
	<div id="tcontent1" class="tabcontent" tabno="0">	
 <hdiits:fieldGroup titleCaptionId="Pf.fix" bundle="${PayLab}" mandatory="true" id="payfixfieldGrp">	

	
<table width="100%">
	
	 
	  <tr>
	  <td width="25%"> 	
	  </td>
	   <td width="25%" align="center"><b><hdiits:caption captionid="Pf.available" bundle="${PayLab}"></hdiits:caption></b></td>
		
   
		<td width="25%" align="left"><hdiits:select name="scalemst" size="1" captionid="Pf.available"                
                       bundle="${PayLab}" mandatory="true" validation="sel.isrequired"  onchange="accep();">
        <hdiits:option value=""><fmt:message key="Payfix.select" bundle="${PayLab}" /></hdiits:option>
        <c:forEach var="Empfix" items="${resValue.EmpfixList}">
         <hdiits:option value="${Empfix.payFixId}" ><c:out value="${reasonfix}"/><c:out value="---"/>(<fmt:formatDate value="${Empfix.payFixDate}" pattern="dd/MM/yyyy" />) </hdiits:option>
       </c:forEach>
        </hdiits:select >
        </td>
        <td width="25%"> 	
	  </td>
       </tr>
     
      
       
		   </table>	
		   </hdiits:fieldGroup>
		   <br>
		    <br>
		     <br>
		      <br>
		   <table align="center">
               <tr>
               <td align="center">
					<hdiits:button type="button" captionid="Pay.Close" name="Close" value="Close" onclick="Closebt()" bundle="${PayLab}"/>
					</td>
 	</tr>
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
