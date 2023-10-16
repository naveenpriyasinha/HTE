<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.qtr.qtr_AlertMessages" var="commonLables1" scope="request"/>

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


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="fieldList" value="${resValue.fieldList}"></c:set>
<c:set var="counter" value="${resValue.counter}" ></c:set>
<c:set var="uId" value="${resValue.entryId}" ></c:set>
<c:set var="qtr" value="${resValue.qtr}" ></c:set>
<c:set var="yr" value="${resValue.yr}" ></c:set>
<c:set var="EmpDetVO" value="${resValue.EmpDet}" />
<c:set var="reportFlag" value="${resValue.reportFlag}" ></c:set>


<script>
var reportFlag=${reportFlag};
function chkSpChars(control)
			{
				var iChars = "#^+[]\\\;/|\:<>";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) != -1) 
  						{
  							alert("<fmt:message key="PS.SPECIALCHARS" bundle="${commonLables1}"/>");
  							setFocusSelection(control.name);
  							control.focus();
  							return false;
  						}
  					}
			}

var txt = new Array();

	function checkvalidate(total){	
	
	for(var i=1; i<=total; i++)
	{	
	   txt[i] = document.getElementById("view"+i);
	    
	   if((txt[i].value) == "" )
	    {
	    alert("<fmt:message key="QTR.MAND_FIELD" bundle="${commonLables1}" /> "+i);
	    setFocusSelection("view"+i);
	    txt[i].focus();	
	    return;
        }
      
	 }
	
	 
	
////// if all are filled
	//alert("inside else ");
	submit1();
	

}
function submit1(){

	//alert("submit done");
	document.qtrEntry.method="POST";
	document.qtrEntry.action="./hrms.htm?actionFlag=getQtrDoEntry";
	showProgressbar('Submitting Request...<br>Please wait...');
	document.qtrEntry.submit();
}
function displayNone()
{
document.getElementById('tab2').style.display="none";
document.getElementById('tab3').style.display="none";
return;
}

function submit4()
{
	//alert("inside submit function");
	if(reportFlag==1)
	window.close();
	document.qtrEntry.method="POST";

	document.qtrEntry.action="./hrms.htm?actionFlag=getQtrSearchEntry";
	// document.qtrEntry.action="./hdiits.htm?actionFlag=validateLogin&theme=hdiits";
	showProgressbar('Opening Page...<br>Please wait...');
	document.qtrEntry.submit();
}
</script>


<fmt:setBundle basename="resources.hr.qtr.qtr" var="commonLables" scope="request"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.QTR.QTR" bundle="${commonLables}" /></b></a></li>
	</ul>
</div>

<hdiits:form name="qtrEntry"  validate="true" method="POST"  encType="multipart/form-data" action="">


<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">
<table width="100%">
			<tr>
			<td class="fieldLabel" colspan="4"><%@ include
				file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
</table>

<hdiits:fieldGroup bundle="${commonLables}"  id="PreviousDetails" titleCaptionId="HR.QTR.PREVIOUS" expandable="true" collapseOnLoad="true">

<c:set var="x" value= "1"/>
<c:forEach var="yearPrv" items="${counter}" varStatus="statusCounter" step="4">
	<c:set var="quarterPrv" value="${counter[statusCounter.index+1]}"/>
	<c:set var="fieldListPrv" value="${counter[statusCounter.index+2]}"/>
	<c:set var="viewListPrv" value="${counter[statusCounter.index+3]}"/>
	<c:set var="n" value="${x}"/>
	
	<table align="center" width="50%" class="tabtable">
	 <tr colspan="4"  width="50%">
	    <td width="15%" align="center"><hdiits:caption captionid="HR.QTR.year" bundle="${commonLables}" id="year1" />:</td>
	    <td width="35%"><b><label class="captionTag">"${yearPrv}"</label></b></td>
	    <td width="15%" align="center"><hdiits:caption captionid="HR.QTR.quarter" bundle="${commonLables}" id="quarter1" />:</td>
	    <td width="35%"><b><label class="captionTag">${quarterPrv}"</label></b></td>
	  </tr> 
	</table> 

	
	<table align="center" width="100%">
		<tr>
		<td width="10%"></td>
			<td>
				<table class="tabtable" id="t${n}" align="center" border="1">
					<tr style="color: white; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #386CB7;">
						<td><center><b><fmt:message key="HR.QTR.goals" bundle="${commonLables}"/></b></center></td>
						<td><center><b><fmt:message key="HR.QTR.remarks" bundle="${commonLables}"/></b></center></td>
					</tr>
					<c:forEach var="viewListPrvItems" items="${viewListPrv}" varStatus="statusView">
					   <tr colspan="2">
						    <td width="25%">
								<label class="captionTag">${fieldListPrv[statusView.index].field}</label>
						    </td>
						    <td width="75%">
						        <label class="captionTag">${viewListPrvItems.fieldView}</label>
						    </td>
					   </tr> 	
					</c:forEach>					
				</table> 
			</td>
		<td width="10%"></td>
		</tr>
	</table>
	<br>
<c:set var="x" value="${x+1}"/>
</c:forEach>

</hdiits:fieldGroup>
<c:if test="${x==1}" >
   <tr align="center">
   <td width="25%" align="center"><b><hdiits:caption captionid="HR.QTR.doentry" bundle="${commonLables}" /></b> </td>
   </tr>
   <br><br>
</c:if>

<hdiits:fieldGroup bundle="${commonLables}"  id="doEntryGrp" titleCaptionId="HR.QTR.doentry" expandable="true">

<table class="tabtable" id="tab2" width="80%">
	<tr colspan="4"> 
		<td width="15%" align="center"><hdiits:caption captionid="HR.QTR.year" bundle="${commonLables}" id="year1" />:</td>
		<td width="35%"><b><label class="captionTag">"${yr}"</label></b></td>
		<td width="15%" align="center"><hdiits:caption captionid="HR.QTR.quarter" bundle="${commonLables}" id="quarter1" />:</td> 
		<td width="35%"><b><label class="captionTag">"${qtr}"</label></b></td>
	</tr>   
</table>   
<hdiits:hidden name="reportFlag" id="reportFlag" default="${reportFlag}" />
<br>
<table  id="tab3" class="tabtable" width="100%">		
	<tr colspan="2">
		<td width="20%" align="center">
		<b><hdiits:caption captionid="HR.QTR.srno" bundle="${commonLables}" /></b><br>
		</td>
		<td width="35%" align="left">
		<b><hdiits:caption captionid="HR.QTR.goals" bundle="${commonLables}" /></b><br>
		</td>		
		<td width="45%" align="left">
		<b><hdiits:caption captionid="HR.QTR.remarks" bundle="${commonLables}" /></b><br>
		</td>
	</tr>		
	<c:set var="y" value= "1"/>
	<c:forEach var="resValue121" items="${fieldList}">		
	<tr colspan="2">			
		<td width="20%" align="center">
		<label class="captionTag">${y}</label>
		<br><br>
		</td>				
		<td width="30%" align="left">  
		<label class="captionTag">${resValue121.field} </label><br><br>
		<hdiits:hidden name="field${y}" default="${resValue121.field}"/>			
		</td>			
		<td width="50%" align="left">  
		<hdiits:textarea mandatory="true" rows="3" cols="50" name="view${y}" tabindex="${y}" id="view${y}" maxlength="2000" onblur="chkSpChars(this)"/><br><br>
		</td>				
		<c:set var="y" value="${y+1}"/>			
	</tr>	  
	</c:forEach> 
	<hdiits:hidden name="counter" id="counter" default="${y-1}"/>	   
</table> 

</hdiits:fieldGroup>
<hdiits:hidden name="userid" default="${uId}"/>
<hdiits:hidden name="quarter" default="${qtr}"/>
<hdiits:hidden name="year" default="${yr}"/>

<script>
var pointsConfigured=${y};

if(pointsConfigured==1)
{
displayNone();
}
</script>

<c:if test="${y==1}" >
   <tr align="center">
   <td width="25%" align="center"><b><hdiits:caption captionid="HR.QTR.NOPOINTSCONFIG" bundle="${commonLables}" /></b> </td>
   </tr>
   <br><br>
</c:if>

<br><br>        
<table class="tabtable">

<tr>
<td colspan ="4" align= "center">		

<hdiits:button captionid="HR.QTR.Submit" bundle="${commonLables}"  name="Submit" type="button" onclick="checkvalidate(counter.value);" tabindex="${y+1}"/>
	<hdiits:button captionid="HR.QTR.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="submit4();" tabindex="${y+2}"/></td>

 </tr>
</table>
	</div>
	</div>
<hdiits:validate controlNames="" locale='${locale}' />
</hdiits:form>

	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>
		
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>