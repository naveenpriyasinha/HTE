
<html>
<head>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="DistrictOfficeLables" scope="request" />
 
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" >

function setDDOdtls(myAjax,field,srno){
	
	var divId=srno.toString()+field.toString();
	var XMLDoc = myAjax.responseXML.documentElement;
	var officeNameObj = XMLDoc.getElementsByTagName('officeName');
	var officeNameval = officeNameObj[0].firstChild.nodeValue;
	var dsgDtlObj = XMLDoc.getElementsByTagName('dsgDtl');
	var dsgDtlval = dsgDtlObj[0].firstChild.nodeValue;
	document.getElementById(divId).innerHTML='<a href="#" onclick="hideDtls('+field+','+srno+')">'+field+'<br>'+dsgDtlval+','+officeNameval+'</a>';
	
		
}
function hideDtls(field,srno){

	var divId=srno.toString()+field.toString();
	alert(document.getElementById(divId).innerHTML);
	document.getElementById(divId).innerHTML='<a href="#" onclick="popupDtls('+field+','+srno+');">'+field+'</a>';
	alert(divId);
}
function test()
{
	var district_id = document.getElementById("distViewLsts").value;
	alert(district_id);
	//var district_Id=document.getElementById("demoViewLst").value;
	//alert(district_Id);
	
	var url="./hrms.htm?actionFlag=employeeStatisticsReport&district_id="+district_id;
	
	alert(url);
	document.DemoView.action = url;
	document.DemoView.submit();
	 
}	
</script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="demoViewLst" value="${resultValue.demoViewLst}"></c:set>


</head>

<body>
<hdiits:form name="DemoView"  method="post" validate="true" encType="multipart/form-data" >
<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" > 
<table>
<tr align="justify">
District :
<select  name="distViewLsts" id="distViewLsts" >
<option value="-1">Select</option>
<option value="1">All</option>
<c:forEach items="${demoViewLst}" var="demoViewLst">
 <c:choose>
							<c:when test="${demoViewLst[0]=='01' || demoViewLstv[0]=='1' }">
								<option value='<c:out value="${demoViewLst[0]}"/>' disabled="disabled">
								<c:out value="${demoViewLst[1]}"/>
									</option>
							</c:when>
							<c:otherwise>
	     						 <option value='<c:out value="${demoViewLst[0]}"/>'>
	         					   <c:out value="${demoViewLst[1]}"/>
	       						</option>
							</c:otherwise>
						</c:choose>						 
					
</c:forEach>
</select>
</tr>


<tr align="justify">
			<td colspan="6">
				
				<hdiits:button name="ViewReports" type="button" captionid="EIS.VIEWREPORTS" bundle="${commonLables}" onclick="test()"></hdiits:button>
			
				
			</td>
		</tr>
</table>

</div>

</hdiits:form>

</body>
</body>
</html>