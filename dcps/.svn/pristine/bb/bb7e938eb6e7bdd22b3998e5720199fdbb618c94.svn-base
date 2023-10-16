<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks_AlertMessages" var="commonLables1" scope="request"/>

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
	
	

<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>



<script type="text/javascript">


function submit4()
{
	//alert("inside submit function");
	document.sheetremarkDisplay.method="POST";


	document.sheetremarkDisplay.action="./hdiits.htm?actionFlag=getDocListOfWorkList&elementId=300045";
	document.sheetremarkDisplay.submit();
}
	
	
</script>



<!--



function showTable(){	
	alert("in function");
	
	
	
	
	var row = document.getElementById("previousYear");	
	if (row.style.display == '')  
	{

 		
		row.style.display = 'none';
		document.getElementById("img1").src="/hrms/images/greenDown.gif";
		 
			
	}
	else
	{
		 row.style.display = '';
		 document.getElementById("img1").src="/hrms/images/redUp1.gif";
 		
 		
	}
}
-->


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="labelValue" value="${resValue.elementLabelprevList}"></c:set>
<c:set var="lookupDataList" value="${resValue.lookupDataList}"></c:set>	
<c:set var="trnDataList" value="${resValue.trnDataList}"></c:set>	
<c:set var="seqDataList" value="${resValue.seqDataList}"></c:set>	
<c:set var="suggestionList" value="${resValue.suggestionList}"></c:set>	


<!--  
<c:set var="reportFlag" value="${resValue.reportFlag}"></c:set>		
-->

<c:set var="suggestionPrev" value="${resValue.suggestionPrev}"></c:set>
<c:set var="prevselectlookupList" value="${resValue.prevselectlookupList}"></c:set>
<c:set var="seqPrevDataList" value="${resValue.seqPrevDataList}"></c:set>
<c:set var="trnPrevDataList" value="${resValue.trnPrevDataList}"></c:set>
<c:set var="prevRecord" value="${resValue.prevRecord}"></c:set>


<c:set var="name" value="${resValue.name}"></c:set>
<c:set var="designationName" value="${resValue.designationName}"></c:set>
<c:set var="basicSalary" value="${resValue.basicSalary}"></c:set>
<c:set var="dateofjoining" value="${resValue.dateofjoining}"></c:set>
<c:set var="dateofretirement" value="${resValue.dateofretirement}"></c:set>
<c:set var="cityName" value="${resValue.cityName}"></c:set>
<c:set var="payScaleStart" value="${resValue.payScaleStart}"></c:set>
<c:set var="payScaleInc" value="${resValue.payScaleInc}"></c:set>
<c:set var="payScaleEnd" value="${resValue.payScaleEnd}"></c:set>


<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks" var="commonLables" scope="request"/>


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		 <b>
			<hdiits:caption captionid="HR.ESS.Sheet" bundle="${commonLables}" id="Sheet" />
		</b>
		</a>
		</li>
	</ul>
</div>


<hdiits:form name="sheetremarkDisplay" validate="true"   encType="multipart/form-data" >
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">
<table width="100%">
			<tr>
			<td class="fieldLabel" colspan="4"><%@ include
				file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
</table>  


<c:if test="${prevRecord eq 1 }">

<hdiits:fieldGroup bundle="${commonLables}"  id="PreviousYearDtl" expandable="true" collapseOnLoad="true" titleCaptionId="HR.ESS.SheetRemarksPrevious">

<hdiits:caption captionid="HR.ESS.Previous" bundle="${commonLables}" id="Previous" />:<c:out value="${date}"/>

<table id="previousYear" class="tabtable">
            	<c:set var="y" value= "0"/>
			<c:forEach var="labels" items="${labelValue}" varStatus="x">
			<tr colspan="5">
		<c:set var="prevselect" value= "${prevselectlookupList[x.index]}"/>
		<c:set var="prevtrn" value="${trnPrevDataList[x.index]}"/>
		
		
	
  				<td width="10%">
  					<c:out value="${labels}"/>: 
  				
  				</td>
  				
    			<td width="10%">
    				<c:out value="${prevselect.lookupDesc}"/>
    				<c:set var="count" value="${labelsCount.count}"/>
 				 	
				</td>

				
				<td width="10%">
				<c:out value="${prevtrn.ratingValue}"/>
				</td>

				<td width="10%">

					<hdiits:caption captionid="HR.ESS.Remarks" bundle="${commonLables}" id="Remarks" />:
				</td>

				<td width="10%">

						
					<c:out value="${prevtrn.sheetrFieldTxtarea}"/>
					<hdiits:hidden name="Code${y}" default="${y+1}"/>
				

				</td>
				</tr>
			<c:set var="y" value="${y+1}"/>
			

			</c:forEach>
			
			<tr colspan="2">
      
      		<td width="10%"><hdiits:caption captionid="HR.ESS.Evaluationwork" bundle="${commonLables}" id="Evaluation" />: </td>
     			<td>
      		 <c:out value="${seqPrevDataList.evaluationOfWork}"></c:out>
     	</td>
      		</tr>
      
     		 <tr colspan="2">
      
      		<td width="10%"><hdiits:caption captionid="HR.ESS.Representation" bundle="${commonLables}" id="Representation" />: </td>
      		<td>
      		<c:out value="${seqPrevDataList.representation}"></c:out>
     
      		</td>
      		</tr>
      
      
      <tr colspan="4">
      
      <td width="10%"></td>
      <td width="10%"></td>
       
       <td width="10%" align="center"><hdiits:caption captionid="HR.ESS.Average" bundle="${commonLables}" id="Average" />: </td>
     
       
      <td align="center">
      <c:out value="${seqPrevDataList.averageRating}"></c:out>
      </td>
       
      
      </tr>
			
      
      
      <tr colspan="2"><td width="10%"><hdiits:caption captionid="HR.ESS.Specific" bundle="${commonLables}" id="Specific" />: </td><td>
       <c:out value="${suggestionPrev.lookupDesc}"></c:out>
       </td>
      </tr>
			
		
</table>
</hdiits:fieldGroup>
</c:if>


<hdiits:fieldGroup bundle="${commonLables}"  id="SheetRemarksDetails" expandable="true" titleCaptionId="HR.ESS.SheetRemarks">


<table class="tabtable">

<c:forEach var="labels" items="${labelValue}" varStatus="x">
<c:set var="transaction" value="${trnDataList[x.index]}"/>
<c:set var="lookup" value="${lookupDataList[x.index]}"/>
<tr colspan="5">
		
		
		
	
  <td width="10%"><c:out value="${labels}"/>: </td>




<td width="10%">
<c:out value="${lookup.lookupDesc}"/>
</td>



<td width="10%">
</td>

<td width="10%">

<hdiits:caption captionid="HR.ESS.Remarks" bundle="${commonLables}" id="Remark" />: 
</td>

<td width="10%">
<c:out value="${transaction.sheetrFieldTxtarea}"/>
</td>


</tr>

</c:forEach>

				
      <tr colspan="2">
      
      		<td width="10%"><hdiits:caption captionid="HR.ESS.Evaluationwork" bundle="${commonLables}" id="Evaluation" />: </td>
     			<td>
      		 <c:out value="${seqDataList.evaluationOfWork}"></c:out>
     	</td>
      		</tr>
      
     		 <tr colspan="2">
      
      		<td width="10%"><hdiits:caption captionid="HR.ESS.Representation" bundle="${commonLables}" id="Representation" />: </td>
      		<td>
      		<c:out value="${seqDataList.representation}"></c:out>
     
      		</td>
      		</tr>
      
      
      <tr colspan="5">
      
      <td width="10%"></td>
      <td width="10%"></td>
      <td width="10%"></td> 
       <td width="10%" ><hdiits:caption captionid="HR.ESS.Average" bundle="${commonLables}" id="Average" />: </td>
     
       
      <td >
      <c:out value="${seqDataList.averageRating}"></c:out>
      </td>
       
      
      </tr>
			
      
      
      <tr colspan="2"><td width="10%"><hdiits:caption captionid="HR.ESS.Specific" bundle="${commonLables}" id="Specific" />: </td><td>
       <c:out value="${suggestionList.lookupDesc}"></c:out>
       </td>
      </tr>
         
      </table>
               
</hdiits:fieldGroup>

	<table id="submit"  align="center">
		<tr colspan="1">
							
			<td>
				<b><hdiits:button captionid="HR.ESS.Close" bundle="${commonLables}" name="Close" type="button" onclick="window.close();" /></b>
			</td>
		</tr>
			
	</table>
		<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>
	
	 </div>
	</div>
</hdiits:form>		
	
	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>