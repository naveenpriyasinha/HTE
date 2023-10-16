<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/leave/DateVal.js"/>"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/foreignToursTravel/fttcommom.js"/>"></script>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.ess.foreigntourtravel.foreigntourtravel" var="commonLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="hrFtourRetDtl" value="${resValue.hrFtourRetDtl}" />
<c:set var="hrFtourReqDtl" value="${resValue.hrFtourReqDtl}" />
<c:set var="prevRetDtlList" value="${resValue.prevRetDtlList}" />
<c:set var="reportView" value="${resValue.reportView}" />

<hdiits:form name="foreignTourtravel" validate="true"
	method="post">

<c:if test="${not empty prevRetDtlList}">
<hdiits:fieldGroup bundle="${commonLables}" expandable="true" id="prevRetDetails" titleCaptionId="HRMS.prevRetDtl">
<table name="previousDetails" width="100%" align="center">

<tr width="100%" >
	<td align="center" bgcolor="#C9DFFF">
		 <strong><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.srno"/></b></strong>
	</td>
	
	<td align="center" bgcolor="#C9DFFF">
		<strong><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.prevRetDtlAsOn"/></b></strong>
	</td>
	
	<td align="center" bgcolor="#C9DFFF">
		<strong><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.prevReturnDetails"/></b></strong>
	</td>
	

 </tr>
 <c:set var="srNo" value="1"/>
 <c:forEach var="hrFtourRetPrevDtl" items="${prevRetDtlList}">
	<tr>
		
		<td align="center">
			${srNo}
		</td>
			
			<td align="center">
				<fmt:formatDate value="${hrFtourRetPrevDtl.createdDate}"
					pattern="dd/MM/yyyy" var="fromDate" />
				${fromDate}
			
			</td>
			
			<td align="center">
				<a href="#" tabindex="5" onclick="window.open('hrms.htm?actionFlag=viewFTTReturnDtl&code=view&fileId=${hrFtourRetPrevDtl.retId}');" >View</a>
			</td>
		
	</tr>	
<c:set var="srNo" value="${srNo+1}"/>
</c:forEach>
</table>
</hdiits:fieldGroup>
</c:if>

<hdiits:fieldGroup bundle="${commonLables}" expandable="true" id="AnnextureDetails" titleCaptionId="HRMS.annexure">
<table class="tabtable" name="travelRequest" id="travelRequest" width="100%">

	<!-- <tr>
<a href="#" tabindex="5" onclick="window.open('hrms.htm?actionFlag= ');" >View Previous Return Details</a>    -->
     
	<tr>
		<hdiits:hidden name="retId" id="retId" default="${hrFtourRetDtl.retId}"/>
	</tr>

<tr>	
<td width="25%" ><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.NAME"/></b></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="NAME" tabindex="8" id="NAME" 
                                      readonly="true" default="${hrFtourRetDtl.name}" />
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX2I"/></b></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX2I" tabindex="8" id="ANX2I" 
     captionid="HRMS.ANX2I"  bundle="${commonLables}" readonly="true" default="${hrFtourRetDtl.anx2i}"  />
                                    </td>
                                    </tr>

<tr>	
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX2II"/></b></td>
	<td width="25%">
	<hdiits:text mandatory="false"  name="ANX2II" tabindex="8" id="ANX2II" 
      captionid="HRMS.ANX2II"  bundle="${commonLables}" readonly="true" default="${hrFtourRetDtl.anx2ii}" />
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX3"/></b></td>
	<td width="25%">
	
	<hdiits:text mandatory="false"  name="ANX3" tabindex="8" id="ANX3" readonly="true"
     captionid="HRMS.ANX3"  bundle="${commonLables}" default="${hrFtourRetDtl.anx3}"/>
                                    </td>
                                    </tr>
                                    
<tr>
 <td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX4I"/></b></td>
<td width="25%"> 
<hdiits:text  name="ANX4I" tabindex="8" id="ANX4I" readonly="true" default="${hrFtourRetDtl.anx4i}"
  captionid="HRMS.ANX4I"  bundle="${commonLables}" />
                                    </td> 
 <td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX4II"/></b></td>
<td width="25%">
<hdiits:text  name="ANX4II" tabindex="8" id="ANX4II" readonly="true" default="${hrFtourRetDtl.anx4ii}"
   captionid="HRMS.ANX4II"  bundle="${commonLables}" size="6" />
       
<fmt:formatDate value="${hrFtourRetDtl.anx4iid}" pattern="dd/MM/yyyy" var="anx4iid"/>
${anx4iid} 
	<c:if test="${empty hrFtourRetDtl.anx4iid}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.Nil"/>
	 	</c:if>                            

</td>                                                                    
</tr> 

<tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX5" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx5 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx5 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx5 eq '2'}">
	 			-------
	 		</c:if>
		</td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX6"/></b></td>
	<td width="25%">
	<hdiits:textarea captionid="HRMS.ANX6" rows="2" cols="30" name="ANX6" tabindex="7" id="ANX6" 
	        readonly="true" default="${hrFtourRetDtl.anx6}"  />
	</td>
	</tr>
	
	<tr>	
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX7I"/></b></td>
	<td width="25%">
	<hdiits:text name="telecode" tabindex="8" id="ANX7I" 
     readonly="true" default="${hrFtourRetDtl.anx7i}"  captionid="HRMS.ANX7I"  bundle="${commonLables}"/>
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX7II"/></b></td>
	<td width="25%">
	<hdiits:text name="ANX7II" tabindex="8" id="ANX7II" 
      readonly="true" default="${hrFtourRetDtl.anx7ii}" captionid="HRMS.ANX7II"  bundle="${commonLables}" />
                                    </td>
</tr>
                                    
 <tr>	
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX8"/></b></td>
	<td width="25%">
	<hdiits:text name="ANX8" tabindex="8" id="ANX8" 
     readonly="true" default="${hrFtourRetDtl.anx8}" captionid="HRMS.ANX8"  bundle="${commonLables}"/>
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX9"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX9" tabindex="8" id="ANX9" 
    readonly="true" default="${hrFtourRetDtl.anx9}" captionid="HRMS.ANX9"  bundle="${commonLables}" />
                                    </td>
 </tr> 
 
 <tr>	
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX10I"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX10I" tabindex="8" id="ANX10I" 
    readonly="true" default="${hrFtourRetDtl.anx10i}"   captionid="HRMS.ANX10I"  bundle="${commonLables}"/>
                                    </td>
 <td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX11" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx11 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx11 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx11 eq '2'}">
	 			-------
	 		</c:if>
		</td>
		</tr> 
		
		<tr>
 <td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX12I"/></b></td>
<td width="25%">
<hdiits:text  name="ANX12I" tabindex="8" id="ANX12I" 
readonly="true" default="${hrFtourRetDtl.anx12i}"  captionid="HRMS.ANX12I"  bundle="${commonLables}" />
                                    </td> 
 <td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX12II"/></b></td>
<td width="25%">
<hdiits:text  name="ANX12II" tabindex="8" id="ANX12II" 
readonly="true" default="${hrFtourRetDtl.anx12ii}"   captionid="HRMS.ANX12II"  bundle="${commonLables}" size="6"/>

<fmt:formatDate value="${hrFtourRetDtl.anx12iid}" pattern="dd/MM/yyyy" var="anx12iid"/>
${anx12iid}
	<c:if test="${empty hrFtourRetDtl.anx12iid}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.Nil"/>
	 	</c:if>

</td>                                                                    
</tr> 

<tr>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX12III"/></b></td>
<td width="25%">
<hdiits:text  name="ANX12III" tabindex="8" id="ANX12III" 
readonly="true" default="${hrFtourRetDtl.anx12iii}"  captionid="HRMS.ANX12III"  bundle="${commonLables}" size="6"/>

<fmt:formatDate value="${hrFtourRetDtl.anx12iiid}" pattern="dd/MM/yyyy" var="anx12iiid"/>
${anx12iiid}
	<c:if test="${empty hrFtourRetDtl.anx12iiid}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.Nil"/>
	 	</c:if>

</td>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX12IV" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx12iv eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx12iv eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx12iv eq '2'}">
	 			-------
	 		</c:if>
		</td>                                                                    
</tr>

<tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX13I" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx13i eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx13i eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx13i eq '2'}">
	 			-------
	 		</c:if>
		</td>                
		<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX13II" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx13ii eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx13ii eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx13ii eq '2'}">
	 			-------
	 		</c:if>
		</td>                
</tr>	

<tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX13III" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx13iii eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx13iii eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx13iii eq '2'}">
	 			-------
	 		</c:if>
		</td>                
		<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX13IV" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx13iv eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx13iv eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx13iv eq '2'}">
	 			-------
	 		</c:if>
		</td>                
</tr>

<tr>	
<td width="25%"> <b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX13V"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX13V" tabindex="8" id="ANX13V" 
    readonly="true" default="${hrFtourRetDtl.anx13v}"  captionid="HRMS.ANX13V"  bundle="${commonLables}"/>
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX13VI"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX13VI" tabindex="8" id="ANX13VI" 
    readonly="true" default="${hrFtourRetDtl.anx13vi}" captionid="HRMS.ANX13VI"  bundle="${commonLables}" />
                                    </td>
                                    </tr>
                                    
<tr>	
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX13VII"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX13VII" tabindex="8" id="ANX13VII" 
    readonly="true" default="${hrFtourRetDtl.anx13vii}" captionid="HRMS.ANX13VII"  bundle="${commonLables}"/>
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX13VIII"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX13VIII" tabindex="8" id="ANX13VIII" 
    readonly="true" default="${hrFtourRetDtl.anx13viii}" captionid="HRMS.ANX13VIII"  bundle="${commonLables}" />
                                    </td>
                                    </tr> 
 
 <tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX14I" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx14i eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx14i eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx14i eq '2'}">
	 			-------
	 		</c:if>
		</td>                
		<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX14II" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx14ii eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx14ii eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx14ii eq '2'}">
	 			-------
	 		</c:if>
		</td>                
</tr>

<tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX14III" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx14iii eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx14iii eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx14iii eq '2'}">
	 			-------
	 		</c:if>
		</td>                
		<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX15" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx15 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx15 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx15 eq '2'}">
	 			-------
	 		</c:if>
		</td>                
</tr>

<tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX16" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx16 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx16 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx16 eq '2'}">
	 			-------
	 		</c:if>
		</td>                
		<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX17" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx17 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx17 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx17 eq '2'}">
	 			-------
	 		</c:if>
		</td>                
</tr>                                   
 
 <tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX18" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx18 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx18 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx18 eq '2'}">
	 			-------
	 		</c:if>
		</td>                
		<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX19" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx19 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx19 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx19 eq '2'}">
	 			-------
	 		</c:if>
		</td>                
</tr> 

<tr>	
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX20I"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX20I" tabindex="8" id="ANX20I" 
    readonly="true" default="${hrFtourRetDtl.anx20i}"  captionid="HRMS.ANX20I"  bundle="${commonLables}"/>
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX20II"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX20II" tabindex="8" id="ANX20II" 
    readonly="true" default="${hrFtourRetDtl.anx20ii}"  captionid="HRMS.ANX20II"  bundle="${commonLables}" />
                                    </td>
                                    </tr>  
                                    
 <tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX21" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx21 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx21 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx21 eq '2'}">
	 			-------
	 		</c:if>
		</td>                
		<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX22I" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx22i eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx22i eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx22i eq '2'}">
	 			-------
	 		</c:if>
		</td>                
</tr> 

 <tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX22II" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx22ii eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx22ii eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx22ii eq '2'}">
	 			-------
	 		</c:if>
		</td>
		<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX23"/></b></td>
	<td width="25%">
	<hdiits:text name="ANX23" tabindex="8" id="ANX23" 
     readonly="true" default="${hrFtourRetDtl.anx23}" captionid="HRMS.ANX23"  bundle="${commonLables}" />
                                    </td>
                                    </tr> 
                                    
 <tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx24 eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx24 eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx24 eq '2'}">
	 			-------
	 		</c:if>
		</td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24IA"/></b></td>
<td width="25%">

<fmt:formatDate value="${hrFtourRetDtl.anx24ia}" pattern="dd/MM/yyyy" var="anx24ia"/>
        ${anx24ia}
        <c:if test="${empty hrFtourRetDtl.anx24ia}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.Nil"/>
	 	</c:if>                            
</td>                                                                    
</tr> 

<tr>	
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24IB"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX24IB" tabindex="8" id="ANX24IB" 
    readonly="true" default="${hrFtourRetDtl.anx24ib}"  captionid="HRMS.ANX24IB"  bundle="${commonLables}"/>
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24IC"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX24IC" tabindex="8" id="ANX24IC" 
    readonly="true" default="${hrFtourRetDtl.anx24ic}"  captionid="HRMS.ANX24IC"  bundle="${commonLables}" />                                    </td>
                                    </tr>  
                                    
<tr>	
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24ID"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX24ID" tabindex="8" id="ANX24ID" 
     readonly="true" default="${hrFtourRetDtl.anx24id}"  captionid="HRMS.ANX24ID"  bundle="${commonLables}"/>
                                    </td>
<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24II"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX24II" tabindex="8" id="ANX24II" 
    readonly="true" default="${hrFtourRetDtl.anx24ii}"  captionid="HRMS.ANX24II"  bundle="${commonLables}" />
                                    </td>
                                    </tr> 
                                    
 <tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24III" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx24iii eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx24iii eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx24iii eq '2'}">
	 			-------
	 		</c:if>
		</td>
		<td width="25%"><b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24IV"/></b></td>
	<td width="25%">
	<hdiits:text  name="ANX24IV" tabindex="8" id="ANX24IV" 
     readonly="true" default="${hrFtourRetDtl.anx24iv}"  captionid="HRMS.ANX24IV"  bundle="${commonLables}" />
                                    </td>
                                    </tr> 
                                    
 <tr>
<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24V" /></b>
  	</td>
<td width="25%">	
	 		<c:if test="${hrFtourRetDtl.anx24v eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx24v eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx24v eq '2'}">
	 			-------
	 		</c:if>
		</td>                
		<td align="left" width="25%">
  		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.ANX24VI" /></b>
  	</td>
<td width="25%"> 	
	 		<c:if test="${hrFtourRetDtl.anx24vi eq '0'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.no"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx24vi eq '1'}">
	 			<hdiits:caption  bundle="${commonLables}"  captionid="HRMS.yes"/>
	 		</c:if>
	 		<c:if test="${hrFtourRetDtl.anx24vi eq '2'}">
	 			-------
	 		</c:if>
		</td>                
</tr>      
                                                                               		                                                                                                                               	                                                                 
</table>
</hdiits:fieldGroup>

<table class="tabtable" width="100%">
	<c:if test="${reportView}">
			<tr align="center">
			<td>
				<hdiits:button type="button" id="Close"
					name="Close" captionid="HRMS.close" bundle="${commonLables}"
					onclick="window.close()" />
			</td>
			</tr>
			
		</c:if>
</table>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

