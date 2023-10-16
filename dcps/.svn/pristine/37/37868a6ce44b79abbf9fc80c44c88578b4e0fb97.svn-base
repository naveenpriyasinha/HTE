<%@ include file="../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script>

</script>
<hdiits:form name="templateForm" id="templateFormId" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true" >
<hdiits:hidden name="actionFlag" default="BasicInfoDemoFlag"/>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption caption="Insert" captionid="insertTab"/></a></li>
			<li><a href="#" rel="tcontent2"><hdiits:caption caption="View" captionid="viewTab"/></a></li>
	</ul>
</div>

<div class="tabcontentstyle">

<div id="tcontent1" class="tabcontent" tabno="0">

	DEMO PAGE FOR BASIC INFO APPLICATIONS.......

</div>

<div id="tcontent2" class="tabcontent" tabno="1" >

	DEMO PAGE FOR BASIC INFO APPLICATIONS.......
	
</div>



<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>		
<script>

</script>