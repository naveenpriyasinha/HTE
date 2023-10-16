<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>


<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.FrmServiceMst"%>
<%@page import="com.tcs.sgv.common.valueobject.TrnBillRegister"%>
<script type="text/javascript">
function checkUncheckAll(theElement) {
     var theForm = theElement.form, z = 0;
	 for(z=0; z<theForm.length;z++){
      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
	  theForm[z].checked = theElement.checked;
	  }
     }
     }
     function showBill(url)
			{
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
			}
			 function chking()
			 {
			 alert("Select Any Check Box");
			 return false;
			 }
	 
</script>
<%int i=1; %>
      <c:set var="resultObj" value="${result}" > </c:set>
	  <c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
	  <c:set var="billList" value="${resValue.billList}"> </c:set>
	  <c:set var="name" value="${resValue.name}"> </c:set>


	  <table border="1" cellspacing="0" cellpadding="0" align="center" width="100%">
	  	<tr class="TableHeaderBG"> 
			<td  align="center"><c:out value="${name}"/> | <c:out value="${userPost}"/></td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td height="20"><br></td>
					</tr>
					<tr>
						<td>
						<%--<jsp:include page="/WEB-INF/jsp/common/include/billproc/billSearch.jsp" /></td>--%>
						</tr>
					<tr>
						<td>
						<%--<jsp:include page="/WEB-INF/jsp/common/include/billproc/pagination.jsp" /></td>--%>
						</tr>
					<tr>
						<td height="20"><br></td>
					</tr>

					<tr><td>
					<hdiits:form name="rm_accvousfrom" validate="true" action="ifms.htm?actionFlag=updateAcceptBills&page=accBills" method="post"  >
						<hdiits:table bordercolor="green">
													<hdiits:tr>
		   					<hdiits:td>
		   						<hdiits:checkbox name="chkSelect" value="" onclick="javascript:checkUncheckAll(this);"></hdiits:checkbox>
		   					</hdiits:td>
   							<hdiits:td>
   								<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />			    	   								
   							</hdiits:td>
   							<hdiits:td>
		   						<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />			    			   						
		   					</hdiits:td>
		   					<hdiits:td>
		   						<fmt:message key="CMN.BILL_AMOUNT" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />			    			   						
		   					</hdiits:td>	
		   					<hdiits:td>
		   						<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />			    			   						
		   					</hdiits:td>
   							<hdiits:td>
	   							<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />			    		   							
	   						</hdiits:td>
		   					<hdiits:td>
			   					<fmt:message key="CNTR.DDO_NO" bundle="${billprocLabels}"></fmt:message>
		   						(<fmt:message key="CMN.CARDEX_NO" bundle="${billprocLabels}"></fmt:message>)
								<jsp:include page="/common/include/billproc/sort.jsp" />			    			   						
		   					</hdiits:td>	
   							<hdiits:td>
   								<fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />			    	   								
   							</hdiits:td>	
		   					<hdiits:td>
		   						<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />			    			   						
		   					</hdiits:td>	
		   					<hdiits:td>
		   						<fmt:message key="CMN.AUDITOR_NAME" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />
		   					</hdiits:td>
		   					<hdiits:td> 
	   							<fmt:message key="CMN.BILL_CATEGORY" bundle="${billprocLabels}"></fmt:message>
								<jsp:include page="/common/include/billproc/sort.jsp" />			    		   						
   							</hdiits:td>		   					
   						</hdiits:tr>
						<c:forEach var="VO" items="${billList}">
							<% i++; %>
							<hdiits:tr>
								<%-- <hdiits:td><c:out value="${VO}"/></hdiits:td> --%>	
								<%-- <hdiits:td><c:out value="${VO}"/></hdiits:td> --%>

  		 					<hdiits:td>
  		 					<%-- 	<hdiits:checkbox name="chkSelect1"  value=""></hdiits:checkbox>  --%>
  		 					
  		 					<input type="checkbox" name="chkbox"  value="<c:out value="${VO.billNo}"/>~<c:out value="${VO.tokenNum}"/>">  
  		 					</hdiits:td>
		   					<hdiits:td>
								<hdiits:a href="javascript:void(0)" onclick="javascript:showBill('ifms.htm?viewName=MRBillPage')"><c:out value="${VO.billCntrlNo}"/></hdiits:a>
							</hdiits:td>
		   					<hdiits:td><c:out value="${VO.tokenNum}"/></hdiits:td>	
		   					<hdiits:td><c:out value="${VO.billGrossAmount}"/></hdiits:td>	
   							<hdiits:td><c:out value="${VO.subjectDesc}"/></hdiits:td>
   							<hdiits:td><c:out value="${VO.inwardDt}"/></hdiits:td>	      
		   					<hdiits:td><c:out value="${VO.ddoUserId}"/>(<c:out value="${VO.cardexNo}"/>)</hdiits:td>	
   							<hdiits:td><c:out value="${VO.employeeName}"/></hdiits:td>	
   							<hdiits:td><c:out value="${VO.budmjrHd}"/></hdiits:td>		
   							<hdiits:td>
   									<c:out value="${VO.audName}"/>
   							</hdiits:td>
   							<c:choose>
   							<c:when test="${VO.tcBill == '0'}"  >
   							<hdiits:td><c:out value="Tc"/></hdiits:td>	
   							</c:when>
   							<c:otherwise>
	   							<hdiits:td><c:out value="Regular"/></hdiits:td>	   								
	   						</c:otherwise>
	   							</c:choose>
		   				 </hdiits:tr>
					    </c:forEach>
					      				
					     <hdiits:tr>
					   		<hdiits:td align="center" colspan="10">
							 <hdiits:submitbutton name="btnReceive" type="button" value="Receive" />	
   						     
					   	    </hdiits:td>
					   	</hdiits:tr>  				
						</hdiits:table>	
						</hdiits:form>
		 				</td>
					</tr>
	  			</table>
	  		</td>
	  	</tr>
	  	</table>	



