<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<fmt:setBundle	basename="resources.dcps.DCPSConstants" var="DCPSConstants" scope="request" />

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script language="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>


<hdiits:form name="frmDCPS" encType="multipart/form-data" validate="true" method="post">

<table border = "1">
<tr>
<td valign="top">
<table id="tblInfo"  align="left" class="datatable"  cellpadding="4px" cellspacing="4px" border="2" bordercolor="black" >
<tr align="center">
			<th>
				SELECT EMPLOYEE
			</th>
		</tr>
		<tr>
		<td>
CHINTAN
		</td>
		</tr>
		<tr>
		<td>
DIP
		</td>
		</tr>
		<tr>
		<td>
PREYAS
		</td>
				</tr>
		<tr>
		<td>
JAYRAJ
		</td>
		</tr>
		<tr>
		<td>
SHAILESH
		</td>
				</tr>
				<tr>
		<td>
BHARGAV
		</td>
				</tr>
	
	</table>

</td>
<td style="overflow:auto;" >
	
	<div id="tabmenu" align="left">
	<ul id="maintab">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="NAMEANDDESIGINFO" bundle="${dcpsLables}"></fmt:message> </a></li>
		<li><a href="#" rel="tcontent2"> <fmt:message
			key="CONTACTADDRINFO" bundle="${dcpsLables}"></fmt:message> </a></li>
		<li><a href="#" rel="tcontent3"> <fmt:message
			key="GENDERINFO" bundle="${dcpsLables}"></fmt:message> </a></li>
		<li><a href="#" rel="tcontent4"> <fmt:message
			key="DOBANDDOJINFO" bundle="${dcpsLables}"></fmt:message> </a></li>
		
	</ul>
	</div>
	<div class="tabcontentstyle">
	
	<!-- ------------------Name & Designation Details--------------- -->
	<div id="tcontent1" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/dcps/mstNameDesigInfo.jsp" /></div>

	<!-- ------------------Contact Address Details--------------- -->
	<div id="tcontent2" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/dcps/mstContactAddrInfo.jsp" /></div>
		
	<!-- ------------------Gender Details--------------- -->
	<div id="tcontent3" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/dcps/mstGenderInfo.jsp" /></div>
		
	<!-- ------------------DOB and DOJ Details--------------- -->
	<div id="tcontent4" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/dcps/mstBirthJoiningDateInfo.jsp" /></div>
	
	
	
		
	</div>
	
	</td>
</tr>

<tr align="center">

<td>
<hdiits:button type="button"  style="align:center" captionid="BTN.EXIT" bundle="${dcpsLables}"  name="btnexit" id="btnexit"/>
</td>
<td align="center">
<hdiits:button type="button"  style="align:center" name="btnSave" captionid="BTN.SAVE" bundle="${dcpsLables}" id="btnSave"/>
</td>
</tr>
</table>

	

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />


</hdiits:form> 