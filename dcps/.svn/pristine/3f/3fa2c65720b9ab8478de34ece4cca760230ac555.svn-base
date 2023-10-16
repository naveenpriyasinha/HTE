<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.ess.wll.wll_AlertMessages" var="commonLables1" scope="request"/>

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



<fmt:setBundle basename="resources.ess.wll.wll" var="commonLables" scope="request"/>


<script type="text/javascript">

function submit4()
{
	//alert("inside submit function");
	document.welfarePointMst.method="POST";


	document.welfarePointMst.action="./hdiits.htm?actionFlag=getHomePage";
	document.welfarePointMst.submit();
}
	

</script>


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				<b>
					<hdiits:caption captionid="HR.EIS.Welfare" bundle="${commonLables}"/>
				</b>
			</a>
		</li>
	</ul>
</div>


	
	
	
	

<hdiits:form name="welfarePointMst" method="POST" validate="true"  encType="multipart/form-data" >	 
	 		
	

					<table class="tabtable">
						<tr bgcolor="#386CB7">
							<td class="fieldLabel" width="100%">
								<font color="#ffffff">
									<b><hdiits:caption captionid="HR.EIS.PointSubmitted" bundle="${commonLables}"/></b>
								</font>
							</td>
						</tr>
					
					</table>




<table align="center">
<tr colspan="1">
	<td>
		<hdiits:submitbutton captionid="HR.EIS.Close" bundle="${commonLables}" name="Close" type="button"  onclick="submit4();"/>
	</td>
</tr>
</table>
	
	

</hdiits:form>



	
	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />



	<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	