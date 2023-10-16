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

<script type="text/javascript">

function submit4()
{
	//alert("inside submit function");
	document.sheetremarksPointMst.method="POST";


	document.sheetremarksPointMst.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	showProgressbar('Opening Home Page....<br>Please wait...');
	document.sheetremarksPointMst.submit();
}
	

</script>
<fmt:setBundle basename="resources.hr.sheetremarks.SheetRemarks" var="commonLables" scope="request"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				<b>
					<hdiits:caption captionid="HR.ESS.Sheet" bundle="${commonLables}" />
				</b>
			</a>
		</li>
	</ul>
</div>





<hdiits:form name="sheetremarksPointMst" method="POST" validate="true"  encType="multipart/form-data" >	 
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">
					<table class="tabtable">
							<td class="fieldLabel" width="100%" align="center">
									<b><fmt:message bundle="${commonLables}" key="HR.ESS.PointSubmitted" /></b>
							</td>
					</table>

<table align="center">
<tr colspan="1">
	<td>
		<hdiits:submitbutton captionid="HR.ESS.Close" bundle="${commonLables}" name="Close" type="button"  onclick="submit4();"/>
	</td>
</tr>
</table>
	
	</div>
		</div>	
		
	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>'/>	
</hdiits:form>

<script type="text/javascript">
		initializetabcontent("maintab")
</script>	



	<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	