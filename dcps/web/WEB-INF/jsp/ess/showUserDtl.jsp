<%
try {
%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<fmt:setBundle basename="resources.ess.UserPost" var="userPostLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="arLangSpecificEmpls" value="${resultValue.arLangSpecificEmpls}"></c:set>
<c:set var="searchType" value="${resultValue.searchType}"></c:set>
<c:set var="searchKey" value="${resultValue.searchKey}"></c:set>
<c:set var="isRadio" value="${resultValue.radio}"></c:set>
<c:set var="tdBGColor" value="#76A9E2"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<script type="text/javascript">
	function addNewEntry()
	{
		document.forms[0].action='hdiits.htm?actionFlag=showEmployeeEntry&userId=0';
		document.forms[0].submit();
	}
	function editRecord(userId)
	{
		document.forms[0].action="hdiits.htm?actionFlag=showEmployeeEntry&userId="+userId;
		document.forms[0].submit();		
	}
	
	function deleteData()
	{	
		var isChecked = false;
	
		for (var i = 0; i < document.forms[0].deletedata.length; i++) 
		{
   			if (document.forms[0].deletedata[i].checked) 
   			{
     			isChecked = true;
  			}
		}
		if(isChecked)
		{
			var answer=confirm("Are you sure want to delete the selected data?");
			if(answer)
			{
				document.forms[0].action='hdiits.htm?actionFlag=deleteFrmUserMst';
				document.forms[0].submit();
			}
		}
		else
		{
			alert("Please select the checkbox");
		}
	}
	
	function closeWindow()
	{
		document.frmUserDtl.action = "hrms.htm?actionFlag=getHomePage";
	   	document.frmUserDtl.submit();
	}
	
	function selectUser()
	{
		 var formElementsLength = document.forms[0].elements.length;
		  
		 var noOfRowSelected = 0;
		 var selectedRowIndex;
		  
		 for(var iElement=0; iElement<formElementsLength; iElement++)
		 	if(document.forms[0].elements[iElement].type == "radio")
				if(document.forms[0].elements[iElement].checked == true)
				{
					noOfRowSelected= 1;
		 			selectedRowIndex = iElement;
		 		}
			       
	  	if (noOfRowSelected == 0)
	    {
	    	alert("Please select any User");
	    	return false;
	    }
	    
	   // alert("=="+ document.forms[0].elements[selectedRowIndex].value+"===="+document.forms[0].elements[selectedRowIndex].parentNode.parentNode.childNodes[2].innerHTML);
		window.opener.parent.document.getElementById("userTableId").style.display='';
		window.opener.parent.document.frmUserRoleMpg.UserName.value=document.forms[0].elements[selectedRowIndex].parentNode.parentNode.childNodes[2].innerHTML;
		window.opener.parent.document.frmUserRoleMpg.userId.value=document.forms[0].elements[selectedRowIndex].value;
		window.opener.parent.sendAjexRequestForSelectedUserDtls();
		window.close();
		return true;
	}
	function serachUser()
	{
		var radio = '${isRadio}';
		document.frmUserDtl.action = "hdiits.htm?actionFlag=showUsersList&radio="+radio;
	   	document.frmUserDtl.submit();
	}
</script>

<hdiits:form name="frmUserDtl" validate="true" method="post" action="hdiits.htm?actionFlag=showUsersList&radio=${isRadio}">
	<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected">
					<a href="#" rel="tcontent1"><fmt:message key="EmplDtls" bundle="${userPostLables}"></fmt:message></a>
				</li>
			</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<table width="50%" align="center">
			<tr>
				<td align="right">
					<hdiits:caption captionid="SEARCH_TYPE" bundle="${userPostLables}"/>
				</td>
				<td align="center">
					<hdiits:select id="searchType" name="searchType" captionid="SEARCH_TYPE" bundle="${userPostLables}" default="${searchType}">
						<option value="name"><fmt:message key="USER_NAME" bundle="${userPostLables}"></fmt:message></option>
						<option value="gpfno"><fmt:message key="EIS.GpfNo" bundle="${userPostLables}"></fmt:message></option>
					</hdiits:select>
				</td>
				<td>
					<hdiits:text id="txtSearchType" name="txtSearchType" captionid="SEARCH_TYPE" bundle="${userPostLables}" default="${searchKey}"/>
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td align="center">
					<hdiits:button name="btnSearch" id="btnSearch" type="button" captionid="admin.Search" bundle="${userPostLables}" onclick="serachUser()" />
				</td>
			</tr>
		</table>	
		<c:set var="i" value="1" />
		
			<display:table list="${arLangSpecificEmpls}" id="emplObj" requestURI="" pagesize="10"  style="width:100%" offset="1" export="false" decorator="com.tcs.sgv.ess.decorator.AdminOrgEmpMstDecorator">
				<display:setProperty name="paging.banner.placement" value="bottom"/>
					
				<c:if test="${isRadio eq true}">
					<display:column class="tablecelltext" title="" headerClass="datatableheader" style="text-align: center" >
						<hdiits:radio id="selRadio" name="selRadio" value="${emplObj.orgUserMst.userId}"/>
					</display:column>
				</c:if>
							
				<display:column class="tablecelltext" titleKey="srNumber" headerClass="datatableheader" style="text-align: center" sortable="true" >${i}</display:column>

				<display:column class="tablecelltext" titleKey="USER_NAME"  headerClass="datatableheader" style="text-align: center" sortable="true" >${emplObj.orgUserMst.userName}</display:column>
				
				<display:column class="tablecelltext" titleKey="EIS.GpfNo"  headerClass="datatableheader" style="text-align: center" sortable="true" >${emplObj.empGPFnumber}</display:column>
				
				<display:column class="tablecelltext" titleKey="EmplName" headerClass="datatableheader" style="text-align: center;" sortable="true" >${emplObj.empFname} ${emplObj.empMname} ${emplObj.empLname}</display:column>
				
				<c:if test="${isRadio ne true}">
					<display:column property="link2" class="tablecelltext" media="html" titleKey="Actions"  style="text-align: center;" headerClass="datatableheader"></display:column>
				</c:if>
				
				<c:set var="i" value="${i+1}" />
				<display:footer media="html"></display:footer>		
				
			</display:table>
		
		<br><br>
		<table align="center">
		<tr>
			<c:if test="${isRadio ne true}">
				<td>
					<hdiits:button name="addNewEntry_button" captionid="AddRec" bundle="${userPostLables}"  onclick="addNewEntry()" type="button"/>
					<hdiits:button captionid="delete" bundle="${userPostLables}" onclick="deleteData()" name="delete" type="button"/>
					<hdiits:button name="btnClose" type="button" captionid="close" onclick="closeWindow();" bundle="${userPostLables}" />
				</td>
			</c:if>
			
			<c:if test="${isRadio eq true}">
				<td>
					<hdiits:button name="submitbtn" type="button" captionid="SUBMIT" onclick="selectUser();" bundle="${userPostLables}" />
					<hdiits:button name="btn_close" type="button" captionid="close" onclick="window.close();" bundle="${userPostLables}" />
				</td>
			</c:if>
		</tr>
		</table>
		</div>	
	</div>
</hdiits:form>
<script type="text/javascript">
	initializetabcontent("maintab")
</script>
<%
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
%>