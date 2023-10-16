<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>

<fmt:setBundle basename="resources.workflow.FMS_TEMPLables"	var="fmsTempLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="fmsCategoryList" value="${resultMap.fmsCategoryList}"></c:set>

<hdiits:form name="frmFmsTmpRefDocs" id="frmFmsTmpAttrib" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1" onfocus="show(1)"> 
				<hdiits:caption	captionid="FMS.INSERT" bundle="${fmsTempLables}" /> 
			</a>
		</li>
		<li class="selected">
			<a href="#" rel="tcontent2" onfocus="show(2)"> 
				<hdiits:caption	captionid="FMS.VIEW" bundle="${fmsTempLables}" /> 
			</a>
		</li>
	</ul>
	</div>
	<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent"><br>
	<br>
	<b><font style="font-size: 4;font-style: normal">
		<center>
			<hdiits:caption captionid="FMS.INSERTTEMPDETAILS" bundle="${fmsTempLables}" />
		</center>
		</font></b> <br>
	<br>
	<hr>
	<table align="center" border="0" class="tabtable">	
		<tr>	
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.TEMPNAME" bundle="${fmsTempLables}" />
			</td>	
		</tr>
		<tr>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.ENGLISH" bundle="${fmsTempLables}" />
			</td>	
			<td align="left" style="border:none">
				<hdiits:text name="txtTempEngName" captionid="FMS.ENGTEMPNAME" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.DESC" bundle="${fmsTempLables}" />
			</td>	
			<td align="left" style="border:none">
				<hdiits:text name="txtTempDescEng" captionid="FMS.ENGDESC" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>			
		</tr>	
		<tr>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.GUJARATI" bundle="${fmsTempLables}" />
			</td>		
			<td align="left" style="border:none">
				<hdiits:text name="txtTempGujName" captionid="FMS.GUJTEMPNAME" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.DESC" bundle="${fmsTempLables}" />
			</td>		
			<td align="left" style="border:none">
				<hdiits:text name="txtTempDescGuj" captionid="FMS.GUJDESC" bundle="${fmsTempLables}" mandatory="true" validation="txt.isrequired,txt.isname" />
			</td>
		</tr>	
		<tr>
			<td align="right" style="border:none">
				<hdiits:caption	captionid="FMS.CATEGORY" bundle="${fmsTempLables}" />
			</td>
			<td style="border:none" align="left">
				<hdiits:select name="selCategory" id="selCategoryid" captionid="FMS.CATEGORY" bundle="${fmsTempLables}" mandatory="true">
					<option value="0">Select</option>						
					<c:forEach  items="${fmsCategoryList}" var="fmslookup">
						<option value='<c:out value="${fmslookup.categoryCode}"/>' selected="true">
							<c:out value="${fmslookup.categoryName}" />
						</option>								
					</c:forEach>						
				</hdiits:select>
			</td>		
		</tr>							
	</table>	
	
	<br><br>		
	<fmt:message key="FMS.TEMPATTACH" bundle="${fmsTempLables}" var="attachmentTitle"></fmt:message>
	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
	    <jsp:param name="attachmentName" value="TempAttach" />
	    <jsp:param name="formName" value="frmFmsTmpRefDocs" />
	    <jsp:param name="attachmentType" value="Document" />   
		<jsp:param name="attachmentTitle" value="${attachmentTitle}" />  		            
	</jsp:include> 		
	<br><br>	
	<table align="center" border="0">
		<tr>
			<td align="center">
			<center>				
				<hdiits:button type="button" name="btnSubmit" value="SUBMIT" onclick="sendData()"/>				
			</center>	
			</td>
			<td align="center">
			<center>
				<hdiits:button type="button" name="btnReset" value="RESET" onclick="clearAll()"/>		
			</center>
			</td>		
		</tr>
	</table>	
	<hr>	
</div>
<!-- Code For The View JSP --> 	
	<div id="tcontent2" class="tabcontent"><br>
	<br>
	<b>
	<font style="font-size: 4;font-style: normal">
		<center>
			<hdiits:caption captionid="FMS.VIEWTEMPDETAILS" bundle="${fmsTempLables}" />
		</center>
	</font>
	</b> 
	<br>
	<br>
	<hr>
	<table align=center border="0" class="tabtable">
		<tr>			
			<td align="right" style="border:none">
				<hdiits:caption captionid="FMS.DESC" bundle="${fmsTempLables}"/>
			</td>
			<td align="left" style="border:none">
				<hdiits:text name="txtDesc" captionid="FMS.DESC" bundle="${fmsTempLables}"/>
		 	</td>	
		 </tr> 
	</table>
	<table align="center" border="0">
		<tr>
			<td align="center">
			<center>				
				<hdiits:button type="button" name="btnUpdate" value="UPDATE"/>				
			</center>
			</td>
		</tr>
	</table>		
	<hr>
	</div>	
</div>
<script language="javascript">
	function combofill()
	{
		if(document.forms[0].selCategory.value==0)
		{
			//alert("Please Select The Category!!!!")
			alert("<fmt:message key='FMS.ALERTCOMBO' bundle='${fmsTempLables}'/>");
			return false;
		}	
			return true;
	}

	function sendData()
	{	
		if(document.forms[0].txtTempEngName.value == "")
		{
			if(confirm("English AttributeName Not Entered, Do You Want To Assign The Default Value?")==true)
			{
				document.forms[0].txtTempEngName.value="";
				alert("English AttributeName Not  Entered, So assigned "+document.forms[0].txtTempGujName.value+"_Eng");			
				document.forms[0].txtTempEngName.value =document.forms[0].txtTempGujName.value+"_Eng";
			}
			else
				return false;
		}
		else if(document.forms[0].txtTempGujName.value == "")
		{
			if(confirm("Gujarati AttributeName Not Entered, Do You Want To Assign The Default Value?")==true)
			{
				document.forms[0].txtTempGujName.value="";
				alert("Gujarati AttributeName Not Entered, So assigned "+document.forms[0].txtTempEngName.value+"_Guj");			
				document.forms[0].txtTempGujName.value =document.forms[0].txtTempEngName.value+"_Guj";
			}			
			else
				return false;
		}	
					
		if(document.forms[0].txtTempDescEng.value == "" )	
		{
			if(confirm("English Description Not Entered , Do You Want To Assign The Default Value?")==true)
			{	
				 document.forms[0].txtTempDescEng.value="";
				 alert("English Description Not Entered, So assigned "+ document.forms[0].txtTempDescGuj.value+"_Eng");			
				 document.forms[0].txtTempDescEng.value=document.forms[0].txtTempDescGuj.value+"_Eng";
			}
			else
				return false;
		}
		else if(document.forms[0].txtTempDescGuj.value == "")
		{
			if(confirm("Gujarati Description Not Entered , Do You Want To Assign The Default Value?")==true)
			{
				document.forms[0].txtTempDescGuj.value="";
				alert("Gujarati Description Not  Entered, So assigned "+document.forms[0].txtTempDescEng.value+"_Guj");	
				document.forms[0].txtTempDescGuj.value=document.forms[0].txtTempDescEng.value+"_Guj";		
			}
			else
				return false;
		}											     			
			
		//var url = "${contextPath}/hdiits.htm?actionFlag=insertRefDoc&txtTempNameEng="+document.forms[0].txtTempEngName.value+"&txtDescEng="+document.forms[0].txtDescEng.value+"&txtTempGujName="+document.forms[0].txtTempGujName.value+"&txtDescGuj="+document.forms[0].txtDescGuj.value;
		if(combofill())
		{
			var url = "${contextPath}/hdiits.htm?actionFlag=insertRefDoc&txtTempNameEng="+document.forms[0].txtTempEngName.value+"&txtDescEng="+document.forms[0].txtTempDescEng.value+"&txtTempGujName="+document.forms[0].txtTempGujName.value+"&txtDescGuj="+document.forms[0].txtTempDescGuj.value+"&categoryCode="+document.forms[0].selCategory.value;
			alert(url);
			document.forms[0].action=url;		
			document.forms[0].submit();		
		}
	}
</script>
<script type="text/javascript">
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
	initializetabcontent("maintab")
</script>
<hdiits:validate locale="<%=(String)session.getAttribute("locale")%>" />
</hdiits:form>
<%
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>
