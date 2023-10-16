		<c:set var="e" value="0" /> <% int d=0; %>
		<display:table list="${resValue.DeptInqrDtls}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
		<%d=d+1; %>
		<display:column class="tablecelltext" titleKey="HRMS.SrNo" headerClass="datatableheader" value="<%=d%>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" titleKey="HRMS.InquiryCause" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.cause} 
			
		</display:column>
		
		<display:column class="tablecelltext" titleKey="HRMS.phase" headerClass="datatableheader" style="text-align: center" sortable="true" >
			<c:if test="${row.prosecFlag ne 'I' && row.prosecFlag ne 'NI' && row.prosecFlag ne 'C' && row.prosecFlag ne 'N'}">
						<hdiits:caption captionid="dept.prosecEnq" bundle="${commonLables}"></hdiits:caption>
			</c:if>			
			<c:if test="${row.suspFlag ne 'NI' && row.suspFlag ne 'I' && row.suspFlag ne 'C' && row.suspFlag ne 'N'}">
				<c:if test="${row.prosecFlag ne 'P'  && row.prosecFlag ne 'A'}">				
					<hdiits:caption captionid="dept.suspEnq" bundle="${commonLables}"></hdiits:caption>
				</c:if>
			</c:if>
			<c:if test="${row.deptFlag ne 'NI' && row.deptFlag ne 'I' && row.deptFlag ne 'C' && row.deptFlag ne 'N'}">
				<c:if test="${row.suspFlag ne 'P' && row.suspFlag ne 'A'}">
					<hdiits:caption captionid="dept.deptEnq" bundle="${commonLables}"></hdiits:caption> 
				</c:if>
			</c:if>	
			<c:if test="${row.prelimFlag ne 'NI' && row.prelimFlag ne 'I' && row.prelimFlag ne 'C' && row.prelimFlag ne 'N'}">
				<c:if test="${row.prePrelimFlag ne 'P' && row.prePrelimFlag ne 'A'}">
					<hdiits:caption captionid="dept.prelimEnq" bundle="${commonLables}"></hdiits:caption>
				</c:if>
			</c:if>	
			<c:if test="${row.prePrelimFlag ne 'NI' && row.prePrelimFlag ne 'I' && row.prePrelimFlag ne 'C' && row.prelimFlag ne 'P' && row.prelimFlag ne 'C' && row.prelimFlag ne 'A' && row.prePrelimFlag ne 'N'}">
					<hdiits:caption captionid="dept.prePrelimEnq" bundle="${commonLables}"></hdiits:caption>
			</c:if>			
			 
			 <c:if test="${row.prePrelimFlag eq 'C' || row.prePrelimFlag eq 'N'}">
					-
			</c:if>	
		</display:column>
		
		<display:column class="tablecelltext" titleKey="HRMS.punishment" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.punishDtlsType} 
		</display:column>
		
		<display:column class="tablecelltext" titleKey="HRMS.startDate" headerClass="datatableheader" style="text-align: center"
			sortable="true">
		
			<fmt:formatDate value="${row.prePrelimStartDate}"  pattern="dd/MM/yyyy" type="date"/>
			
			
		</display:column>
		
		<display:column class="tablecelltext" titleKey="HRMS.status" headerClass="datatableheader" style="text-align: center"
			sortable="true">
						
			<c:if test="${row.prePrelimFlag eq 'P'}">
			<hdiits:caption captionid="dept.pending" bundle="${commonLables}"></hdiits:caption>
			</c:if>
			
			<c:if test="${row.prePrelimFlag eq 'A'}">
			<hdiits:caption captionid="dept.apprv" bundle="${commonLables}"></hdiits:caption>
			</c:if>
			
			<c:if test="${row.prePrelimFlag eq 'N'}">
			<hdiits:caption captionid="dept.reject" bundle="${commonLables}"></hdiits:caption>
			</c:if>
			
			<c:if test="${row.prePrelimFlag eq 'C'}">
			<hdiits:caption captionid="dept.cancel" bundle="${commonLables}"></hdiits:caption>
			</c:if>


			</display:column>
		
				
		<c:set var="d" value="${d+1}" />
		</display:table>