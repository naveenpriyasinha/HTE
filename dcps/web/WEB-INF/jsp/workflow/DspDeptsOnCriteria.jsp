<%try{ %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>

<c:set var="resultObj"	value="${result}"> </c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="deptCrtList" value="${resultMap.deptCrtList}"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>


<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="DeptCrtSrchForm" encType="multipart/form-data" validate="true">


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	           		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.DepSrchByCrt" bundle="${fmsLables}"/></a></li>
	</ul>
</div>


<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<center><h5><a  href='javascript:dispAllDepts()'><fmt:message bundle="${fmsLables}" key="WF.DSPALLDPT"></fmt:message> </a></h5></center>
	<hdiits:table>
		<hdiits:tr>
			<hdiits:td>
				<fmt:message key="WF.SelCrt" bundle="${fmsLables}"></fmt:message>
			</hdiits:td>
			<hdiits:td>
				<hdiits:select name="crt_sel"  id="crt_sel" mandatory="true" captionid="WF.SelCrt" bundle="${fmsLables}" validation="sel.isrequired" >
				<hdiits:option value="0"><fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message> </hdiits:option>
				<c:forEach var="depCat" items="${deptCrtList}">
					<option value="${depCat.lookupName}">
						${depCat.lookupDesc}
					</option>
				</c:forEach>
				</hdiits:select>
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr>
			<hdiits:td>
				<fmt:message key="WF.DepName" bundle="${fmsLables}"></fmt:message>
			</hdiits:td>
			<hdiits:td>
				<hdiits:text name="txt_srchDept"  id="txt_srchDept" captionid="WF.DepName" mandatory="true" validation="txt.isrequired" bundle="${fmsLables}" onkeypress="inpsubmit(event)" />
			</hdiits:td>
		</hdiits:tr>
		<hdiits:tr align="center">
			<hdiits:td>
				<hdiits:button name="bt_sub" type="button" captionid="WF.Submit" bundle="${fmsLables}" onclick="validatecontrols()"/>
			</hdiits:td>
		</hdiits:tr>
	</hdiits:table>
	</div>
	
			
	</div>		
	<script type="text/javascript"> 
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
	initializetabcontent("maintab")
	</script> 
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
	<script>
	function inpsubmit(e)
	{ 
	  if(e.keyCode==13)
	  { validatecontrols();
	  }
		
	}
	function validatecontrols()
	{
		var controlnames=new Array();
			  
		controlnames.push('crt_sel');
		controlnames.push('txt_srchDept');

		window.opener.document.getElementById('crt_sel').value=document.getElementById('crt_sel').value;
		window.opener.document.getElementById('txt_srchDept').value=document.getElementById('txt_srchDept').value;
		
		var returnVal =  validateSpecificFormFields(controlnames);
				
		if(returnVal == true)
		{
			
			window.opener.parent.document.forms[0].method='post';
			window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=DeptSearchData&dispAll=yes&otherSender=${otherSender}&upd=${param.upd}&groupId=${param.groupId}&winId=1";
			window.opener.parent.document.forms[0].submit();
			window.close();	
		}

	}

	function dispAllDepts(){
	
		window.opener.parent.document.forms[0].method='post';
		window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=DeptSearchData&dispAll=yes&otherSender=${otherSender}&upd=${param.upd}&groupId=${param.groupId}";
		window.opener.parent.document.forms[0].submit();
		window.close();	
	}
	</script>		
</hdiits:form>

<% 
	    	
	}
	catch(Exception e)
	{
		System.out.println(" error in DeptData. " );
		e.printStackTrace();
	}
%>