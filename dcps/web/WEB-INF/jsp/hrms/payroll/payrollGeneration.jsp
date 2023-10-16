<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="enLables" scope="request"/>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="month" value="${resultValue.month}"/>
<c:set var="year" value="${resultValue.year}"/>
<c:set var="demandNo" value="${resultValue.demandNo}"/>
<c:set var="mjrHead" value="${resultValue.mjrHead}"/>
<c:set var="subMjrHead" value="${resultValue.subMjrHead}"/>
<c:set var="mnrHead" value="${resultValue.mnrHead}"/>
<c:set var="subHead" value="${resultValue.subHead}"/>
<c:set var="billType" value="${resultValue.billType}"/>


	<%int i=1;%>
	<hdiits:form name="deptMaster" validate="true" method="POST"
	action="#" >
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="pay.payroll" bundle="${enLables}"/></b></a></li>
	</ul>
</div>

<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent">
   <c:set var="status" value="${resultValue.status }"/>
   <center>
   <br><br><br>
   
 <c:if test="${status == '1'}">
	   <c:out value="Payroll generated successfully." /><br>
	   
	  </c:if><c:if test="${status == '2'}">
	   <c:out value="Payroll already Generated for this Month." />
	  </c:if>
	  
	  <c:if test="${status == '11'}">
	   <c:out value="Arrear bill generated successfully." /><br>
	   
	  </c:if><c:if test="${status == '12'}">
	   <c:out value="No Arrear is active in this Month." />
	  </c:if>
	  <c:if test="${status == '13'}">
	   <c:out value="The Arrear is already paid for this month." />
	  </c:if>
	  
	   <br>
	 <BR>



  	
  </center>
  </div>

</div>
</hdiits:form>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">

</script>
  
  	  <%
}
  	  catch(Exception e)
  	  {
  		  //System.out.println("There is some error:-");
  		  e.printStackTrace();
  	  }
  	  %>
  	  