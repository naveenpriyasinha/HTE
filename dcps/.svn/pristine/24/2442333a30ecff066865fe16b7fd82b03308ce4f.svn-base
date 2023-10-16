

<hdiits:fieldGroup titleCaptionId="dept.wfStatus" bundle="${deptLables}" id="deptStatusLabel">

<table width=100%  id="deptStatus" style="display: none">
    
    <tr style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
    	<td width=20% style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
			<font color="#000000">
    		<b><hdiits:caption captionid="dept.stage" bundle="${deptLables}"/></b>
    		</font>
    	</td>
    	
    	<td width=20% style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
    		<font color="#000000">
    		<b><hdiits:caption captionid="dept.status" bundle="${deptLables}" /></b>
    		</font>
    	</td>
    	
    	<td width=20% style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
    		<font color="#000000">
    		<b><hdiits:caption captionid="dept.startDt" bundle="${deptLables}"/></b>
    		</font>
    	</td>
    	
    	<td width=20% style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
    	 	<font color="#000000">
    		<b><hdiits:caption captionid="dept.endDt" bundle="${deptLables}"/></b>
    		</font>
    	</td>
    	
    	
   </tr>
   
   <tr>	
   
   <fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].preprelimEndDate}" var="endDt"/>
    	
   		<td  width=20%>
   		<b>
   		<font color="#4AA02C">
    	<c:if test="${enqDtl[0].prePrelimStatus!='NI'}">
    	<hdiits:caption captionid="dept.prePrelimEnq" bundle="${deptLables}"/>
    	</c:if>
	   	</font>
   		</b>
   		</td>
   
   
    	<td width=20%><b>
    	<font color="#4AA02C">
    	<c:if test="${enqDtl[0].prePrelimStatus=='P'}">
    	<hdiits:caption captionid="dept.submitd" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	<font color="#8D38C9">
    	<c:if test="${enqDtl[0].prePrelimStatus=='I'}">
    	<hdiits:caption captionid="dept.initd" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	<font color="#C12283">
    	<c:if test="${enqDtl[0].prePrelimStatus=='C'}">
    	<hdiits:caption captionid="dept.closed" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	<font color="#C12283">
    	<c:if test="${enqDtl[0].prePrelimStatus=='N'}">
    	<hdiits:caption captionid="dept.rejectd" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	</b></td>
    	
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].preprelimStartDate}" var="startDt"/>
    	<b>${startDt}</b>
    	</font>
    	</td> 
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].preprelimEndDate}" var="endDt"/>
    	<b>${endDt}</b>
    	</font>
    	</td>
    	
    	
    	</tr>
    	
    	
    	<tr>	
   		<td  width=20%>
   		<font color="#4AA02C">
    	<b>
    	<c:if test="${enqDtl[0].prelimStatus!='NI'}">
    	<hdiits:caption captionid="dept.prelimEnq" bundle="${deptLables}"/>
    	</c:if>
	   	
    	</b>
    	</font>
   		</td>
   
   
    	<td width=20%><b>
    	<font color="#4AA02C">
    	<c:if test="${enqDtl[0].prelimStatus=='P'}">
    	<hdiits:caption captionid="dept.submitd" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	<font color="#8D38C9">
    	<c:if test="${enqDtl[0].prelimStatus=='I'}">
    	<hdiits:caption captionid="dept.initd" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	<font color="#C12283">
    	<c:if test="${enqDtl[0].prelimStatus=='C'}">
		<hdiits:caption captionid="dept.closed" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	</b></td>
    	
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].prelimStartDate}" var="startDt"/>
    	<b>${startDt}</b>
    	</font>
    	</td> 
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].prelimEndDate}" var="endDt"/>
    	<b>${endDt}</b>
    	</font>
    	</td>
    	
    	
    	</tr>
    	
    <c:if test="${enqDtl[0].deptStatus!='NI'}">
    	<tr>	
   		<td  width=20%>
   		<font color="#4AA02C">
	    	<b>	<hdiits:caption captionid="dept.deptEnq" bundle="${deptLables}"/></b>
    	</font>
   		</td>
   
   
    	<td width=20%><b>
    	<font color="#4AA02C">
    	<c:if test="${enqDtl[0].deptStatus=='P'}">
    	<hdiits:caption captionid="dept.submitd" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	<font color="#8D38C9">
    	<c:if test="${enqDtl[0].deptStatus=='I'}">
    	<hdiits:caption captionid="dept.initd" bundle="${deptLables}"/>
    	</c:if>
		</font>
		<font color="#C12283">
    	<c:if test="${enqDtl[0].deptStatus=='C'}">
		<hdiits:caption captionid="dept.closed" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	</b></td>
    	
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].deptStartDate}" var="startDt"/>
    	<b>${startDt}</b>
    	</font>
    	</td> 
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].deptEndDate}" var="endDt"/>
    	<b>${endDt}</b>
    	</font>
    	</td>
    	
    	
    	</tr>
    </c:if>
    
    
     <c:if test="${enqDtl[0].suspensionStatus!='NI'}">
    	<tr>	
   		<td  width=20%>
   		<font color="#4AA02C">
	    	<b><hdiits:caption captionid="dept.suspEnq" bundle="${deptLables}"/></b>
    	</font>
   		</td>
   
   
    	<td width=20%><b>
    	<font color="#4AA02C">
    	<c:if test="${enqDtl[0].suspensionStatus=='P'}">
    	<hdiits:caption captionid="dept.submitd" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	<font color="#8D38C9">
    	<c:if test="${enqDtl[0].suspensionStatus=='I'}">
    	<hdiits:caption captionid="dept.initd" bundle="${deptLables}"/>
    	</c:if>
		</font>
		<font color="#C12283">
    	<c:if test="${enqDtl[0].suspensionStatus=='C'}">
		<hdiits:caption captionid="dept.closed" bundle="${deptLables}"/>
    	</c:if>
    	
    	</font>
    	</b></td>
    	
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].suspensionStartDate}" var="startDt"/>
    	<b>${startDt}</b>
    	</font>
    	</td> 
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].suspensionEndDate}" var="endDt"/>
    	<b>${endDt}</b>
    	</font>
    	</td>
    	
    	
    	</tr>
    </c:if>
    
    <c:if test="${enqDtl[0].prosecStatus!='NI'}">
    	<tr>	
   		<td  width=20%>
   		<font color="#4AA02C">
	    	<b><hdiits:caption captionid="dept.prosecEnq" bundle="${deptLables}"/></b>
    	</font>
   		</td>
   
   
    	<td width=20%><b>
    	<font color="#4AA02C">
    	<c:if test="${enqDtl[0].prosecStatus=='P'}">
    	<hdiits:caption captionid="dept.submitd" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	<font color="#8D38C9">
    	<c:if test="${enqDtl[0].prosecStatus=='I'}">
    	<hdiits:caption captionid="dept.initd" bundle="${deptLables}"/>
    	</c:if>
		</font>
		<font color="#C12283">
    	<c:if test="${enqDtl[0].prosecStatus=='C'}">
		<hdiits:caption captionid="dept.closed" bundle="${deptLables}"/>
    	</c:if>
    	</font>
    	</b></td>
    	
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].prosecStartDate}" var="startDt"/>
    	<b>${startDt}</b>
    	</font>
    	</td> 
    	
    	<td  width=20%>
    	<font color="#4AA02C">
    	<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].prosecEndDate}" var="endDt"/>
    	<b>${endDt}</b>
    	</font>
    	</td>
    	
    	  	</tr>
    </c:if>
</table>

</hdiits:fieldGroup>
