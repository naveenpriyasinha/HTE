<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<STYLE type="text/css">		
#currentApplication{
	display:none;
}
</STYLE>
<fmt:setBundle basename="resources.acl.acl" var="aclLabels" scope="request"/>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<div id="tabmenu"> <ul id="maintab" class="shadetabs"> 
						<li class="selected" >  <a href="#" rel="tcontent1"> <hdiits:caption captionid="ChangePwdTitle" bundle="${aclLabels}"/></a> </li>
                   </ul>
</div>  
<div class="tabcontentstyle" > <div id="tcontent1" class="tabcontent"  tabno="0">
<hdiits:table width="100%" align="center">
<hdiits:tr>
<hdiits:td align="center"><span class="titles"><hdiits:caption captionid="pwdSuccess" bundle="${aclLabels}"/> </span></hdiits:td>
</hdiits:tr>
</hdiits:table>
</div>
</div>
          <script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>