<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<hdiits:form name="formFeedbacksForSRKA" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle"><legend> <b><fmt:message
				key="CMN.FEEDBACKSFROMDDO" bundle="${dcpsLables}"></fmt:message></b> </legend>
				<br>
				<div align="center">
					<div style="width:100%;height: 400px;overflow:auto">	
						<display:table list="${resValue.lListFeedbacks}" size="10"  id="vo" pagesize="10" cellpadding="5" style="width:100%" requestURI="" >
						<display:setProperty name="paging.banner.placement" value="bottom" />

						<display:column style="text-align: center;width:20%"  class="oddcentre" titleKey="CMN.DDO" headerClass="datatableheader"  sortable="true" ><c:out value="${vo[1]}  (${vo[0]})"/></display:column>
						<display:column style="text-align: center;width:33%"  class="oddcentre" titleKey="CMN.FEEDBACK" headerClass="datatableheader"  sortable="true" >
							<textarea name="textarea" style="width:100%" readonly="readonly" >${vo[2]}</textarea>
						</display:column>
						
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}" var="feedBackDate"/>
						<display:column style="text-align: left;width:7%"  titleKey="CMN.FEEDBACKDATE" headerClass="datatableheader" sortable="true" >
							<c:out value="${feedBackDate}"/>
						</display:column>
						
						<display:column style="text-align: center;width:15%"  class="oddcentre" titleKey="CMN.EMAIL" headerClass="datatableheader"  sortable="true" >
							<c:out value="${vo[4]}"/>
						</display:column>
						
						<display:column style="text-align: center;width:10%"  class="oddcentre" titleKey="DCPS.TELNO(1)" headerClass="datatableheader"  sortable="true" >
							<c:out value="${vo[5]}"/>
						</display:column>
						
						</display:table>
					</div>
				</div>
</fieldset>
</hdiits:form>