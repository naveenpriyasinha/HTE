
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<fmt:setBundle basename="resources.ess.wll.wll_AlertMessages" var="commonLables1" scope="request"/>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript">
homepage()
{
alert("asfasgf");
	
	document.welfaresubmit.method="POST";

	document.welfaresubmit.action="./hdiits.htm?actionFlag=getHomePage";
	document.welfaresubmit.submit();

}
</script>


<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="id" value="${resultValue.id}"></c:set>
<c:set var="fwdTo" value="${resultValue.fwdTo}"></c:set>
<br>
<br>
<br>
<br>
<br>

<fmt:setBundle basename="resources.ess.wll.wll" var="commonLables" scope="request"/>

<hdiits:form name="welfaresubmit" validate="true"   encType="multipart/form-data" >

<table height="100">
		<tr>
			<td rowspan="100" colspan="40"></td>
		</tr>
		<tr rowspan="30" colspan="40">
			<th rowspan="30" colspan="40"></th>
		</tr>
				<tr>
			<th rowspan="30" colspan="40"></th>
		</tr>
	</table>

      <hr align="center" width=" 50%">


<table width="50%" align="center">
   
  
  <tr  ></tr>

    <tr><th align="center">
    
   
    <td><b><hdiits:caption captionid="HR.EIS.RequestNo" bundle="${commonLables}"/></b>
	
	
	<c:out value="${id}"></c:out>

	

 	<b><hdiits:caption captionid="HR.EIS.Fwded" bundle="${commonLables}"/></b>
	
	 
	<c:out value="${fwdTo}"></c:out>

	
	
	 <b><hdiits:caption captionid="HR.EIS.Apprvl" bundle="${commonLables}"/></b></td>
	  
    
    
    </th></tr>
  <tr></tr>
  

</table >
	

  <hr align="center" width="50%">

<br>
<br>
<br>
<br>
<br>
<br>


<table align="center">
<tr colspan="1">
	<td>
		<hdiits:submitbutton captionid="HR.EIS.Close" bundle="${commonLables}" name="Close" type="button"  onclick="homepage();"/>
	</td>
</tr>
</table>
	




</hdiits:form>
