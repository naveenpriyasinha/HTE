<%try{ %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="AttributeList" value="${resultMap.AttributeList}"></c:set>
<c:set var="CategoryName" value="${resultMap.CategoryName}"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="RefDocsAttr_Eng" value="${resultMap.RefDocsAttrList_Eng}"></c:set>
<c:set var="RefDocsAttr_Guj" value="${resultMap.RefDocsAttrList_Guj}"></c:set>
<c:set var="counterForEnglish" value="${0}"></c:set>
<c:set var="counterForGujarati" value="${0}"></c:set>
<c:set var="tempcountEng" value="${1}"></c:set>
<c:set var="tempcountGuj" value="${1}"></c:set>
<c:set var="FmsTemplateMetadataTxnDtl" value="${resultMap.fmsTemplateMetadataTxnDtl}"/>
<c:set var="deptId" value="${resultMap.deptId}"></c:set>
<c:set var="locationCode" value="${resultMap.locCode}"></c:set>
<c:set var="brId" value="${resultMap.branchId}"></c:set>
<c:set var="METADATA_SRC" value="${resultMap.METADATA_SRC}"></c:set>
<c:set var="DefaultAttrList" value="${resultMap.DefaultAttrList}"></c:set>
<c:set var="branchLst" value="${resultMap.branchLst}"></c:set>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<fmt:formatDate value="${resultMap.currentDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="currentDate"/> 

<hdiits:form name="TemplateDetailsForm"  method="POST" action="./hdiits.htm?actionFlag=fms_insertRefDocTemplates" validate="true">

<hdiits:hidden name="CategoryTemplateMpgCode" default="${resultMap.CategoryTemplateMpgCode}" />
<hdiits:hidden name="departmentId" id="id_deptId" default="${resultMap.deptId}"/>
<hdiits:hidden name="locCode" id="id_locCode" default="${resultMap.locCode}"/>
<hdiits:hidden name="FmsTemplateMetadataTxnDetails" default="${resultMap.fmsTemplateMetadataTxnDtl}"/>
<hdiits:hidden name="fromDraftsFlag" default="${resultMap.fromDraftsFlag}"/>
<hdiits:hidden name="loginuserPostId" id="loginuserPostId" default="${resultMap.loginuserPostId}"/>

<script>
window.moveTo(0,0);
window.resizeTo(screen.availWidth,screen.availHeight);
</script>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.Search" bundle="${fmsLables}"/></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
	<b>
			<c:out value="Category Name : ${CategoryName}"> </c:out>
	</b>
	<br>
	<br>
	<hdiits:radio name="rad_LangSel" id="radid_guj" value="Guj" default="Guj" captionid="WF.GUJSEL" bundle="${fmsLables}"/>
	<hdiits:radio name="rad_LangSel"  id="radid_eng" value="Eng" captionid="WF.ENGSEL" bundle="${fmsLables}"/>
	<br>
	<br>
	<fieldset>
		<b><c:out value="List Of Attributes in English"></c:out></b><br>
		<br>
		<br>
		<table width="100%">
			<c:forEach items="${RefDocsAttr_Eng}" var="refDocsAttr">
				<c:set var="counterForEnglish" value="${counterForEnglish +1}"></c:set>
				<c:choose>
					<c:when test="${refDocsAttr.mandatory eq 'Y'}">
						<c:set var="color" value="lightyellow"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="color" value="white"></c:set>
					</c:otherwise>
				</c:choose>
				
				
				<c:if test="${counterForEnglish eq 1 or counterForEnglish-1 mod 3 eq 0}">
					<c:set var="tempcountEng" value="${tempcountEng+3}"></c:set>
					<tr>
					
				</c:if>
				
						<td>
							<hdiits:hidden id="attCodeEng_${counterForEnglish}" name="attCode_${counterForEnglish}" default="${refDocsAttr.attributeCode}"/>
							<label id="attNameEng_${counterForEnglish}"><c:out value="${refDocsAttr.attributeName}"> </c:out> </label>
						</td>
						<td>
							<c:choose>
								<c:when test="${refDocsAttr.dataType eq 'DateTime'}">
								<c:if test="${METADATA_SRC eq 'Online'}">
									 <hdiits:dateTime name="attCode_value_${counterForEnglish}"  captionid="WF.DATE" bundle="${fmsLables}"  validation="txt.isrequired" condition="checkValidation('Eng','${refDocsAttr.mandatory}')" default="${refDocsAttr.attributeValueDate}" minvalue="${currentDate}"></hdiits:dateTime>
								</c:if>
								<c:if test="${METADATA_SRC eq 'Offline'}">
									 <hdiits:dateTime name="attCode_value_${counterForEnglish}"  captionid="WF.DATE" bundle="${fmsLables}"  validation="txt.isrequired" condition="checkValidation('Eng','${refDocsAttr.mandatory}')" default="${refDocsAttr.attributeValueDate}" minvalue="" maxvalue=""></hdiits:dateTime>
								</c:if>
									 <c:if test="${refDocsAttr.mandatory eq 'Y'}">
										<font color="red" size="4">*</font>
									 </c:if>
								</c:when>
								<c:otherwise>
									<hdiits:text style="background: ${color}" id="attCode_value_${counterForEnglish}" name="attCode_value_${counterForEnglish}" caption="${refDocsAttr.attributeName}" validation="txt.isrequired,,${refDocsAttr.validation}" maxlength="${refDocsAttr.maxLength}" condition="checkValidation('Eng','${refDocsAttr.mandatory}')"  default="${refDocsAttr.attributeValue}"/>
									<c:if test="${refDocsAttr.mandatory eq 'Y'}">
										<font color="red" size="4">*</font>
									</c:if>
								</c:otherwise>
							</c:choose>
						</td>
				<c:if test="${counterForEnglish eq tempcountEng }">
					</tr>	
				</c:if>
			</c:forEach>
		</table>
	
	</fieldset>
	<br>
	<br>
	<fieldset id="bbb">
		<b><c:out value="List Of Attributes in Gujarati"></c:out></b><br>
		<br>
		<br>
		<table width="100%">
			<c:forEach items="${RefDocsAttr_Guj}" var="refDocsAttr">
			<c:set var="counterForGujarati" value="${counterForGujarati +1}"></c:set>
			
			<c:choose>
				<c:when test="${refDocsAttr.mandatory eq 'Y'}">
					<c:set var="color" value="lightyellow"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="color" value="white"></c:set>
				</c:otherwise>
			</c:choose>
			<c:if test="${counterForGujarati eq 1 or counterForGujarati-1 mod 3 eq 0}">
			<c:set var="tempcountGuj" value="${tempcountGuj+3}"></c:set>
				<tr>
			</c:if>
					<td>
						<hdiits:hidden  id="attCodeGuj_${counterForGujarati}" name="attCodeGuj_${counterForGujarati}" default="${refDocsAttr.attributeCode}"/>
						<label id="attNameGuj_${counterForGujarati}"><c:out value="${refDocsAttr.attributeName}"> </c:out> </label>
					</td>
					<td>
							<c:choose>
								<c:when test="${refDocsAttr.dataType eq 'DateTime'}">
								<c:if test="${METADATA_SRC eq 'Online'}">
									<hdiits:dateTime name="attCode_Guj_${counterForGujarati}" captionid="WF.DATE"  bundle="${fmsLables}" validation="txt.isrequired" condition="checkValidation('Guj','${refDocsAttr.mandatory}')" default="${refDocsAttr.attributeValueDate}" minvalue="${currentDate}"></hdiits:dateTime>
								</c:if>
								<c:if test="${METADATA_SRC eq 'Offline'}">
									<hdiits:dateTime name="attCode_Guj_${counterForGujarati}" captionid="WF.DATE"  bundle="${fmsLables}" validation="txt.isrequired" condition="checkValidation('Guj','${refDocsAttr.mandatory}')" default="${refDocsAttr.attributeValueDate}" minvalue="" maxvalue=""></hdiits:dateTime>
								</c:if>
									<c:if test="${refDocsAttr.mandatory eq 'Y'}">
										<font color="red" size="4">*</font>
									</c:if>
								</c:when>
								<c:otherwise>
									<hdiits:text style="background: ${color}" id="attCode_Guj_${counterForGujarati}" name="attCode_Guj_${counterForGujarati}" caption="${refDocsAttr.attributeName}" validation="txt.isrequired"  condition="checkValidation('Guj','${refDocsAttr.mandatory}')" default="${refDocsAttr.attributeValue}"/>
									<c:if test="${refDocsAttr.mandatory eq 'Y'}">
										<font color="red" size="4">*</font>
									</c:if>
								</c:otherwise>
							</c:choose>
					</td>
				<c:if test="${counterForGujarati eq tempcountGuj }">
					</tr>	
				</c:if>
			</c:forEach>
		</table>
	</fieldset>

	<hdiits:hidden name="counter" default="${counterForEnglish}"/>
	<br>
	<br>
	<c:if test="${FmsTemplateMetadataTxnDtl eq 'notfilled' and empty locationCode}">
		<jsp:include page="/WEB-INF/jsp/common/SearchLocation.jsp">
			<jsp:param name="SearchLocation" value="RefDocsLoc"/>
			<jsp:param name="mandatory" value="Yes"/>
			<jsp:param name="searchLocationTitle" value="Location Search"/>
			<jsp:param name="hideFields"  value="Addr,City-Dist,State-Pin" />
		</jsp:include>	
		<br>
		<br>
		<hdiits:button name="bt_branch" type="button" value="Get Branches" onclick="loadBranchesFromLocation()"/>
		<br>
	</c:if>
	<c:if test="${empty resultMap.branchId}">
	<hdiits:select name="branch" id="dd_section" >
	<c:forEach var="branch" items="${branchLst}">
		<option value="${branch.branchId}">${branch.branchName}</option>
	</c:forEach>
	</hdiits:select>
	</c:if>
	<hdiits:jsField name="checkForBlankSpaceAttributes" jsFunction="checkForBlankSpaceAttributes()" />
		
</div>
<center><hdiits:button type="button"  name="close" id="close" captionid="WF.CLOSE" bundle="${fmsLables}" onclick="javascript:closewindow();" /></center>
<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

</hdiits:form>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script>

function closewindow()
{
	
	window.close();
}
function checkValidation(language,mandatoryFlag)
{
	//alert("viral");
	var radButtonValue;
	if(document.getElementById('radid_guj').checked)
	{
		radButtonValue = 'Guj';
	}
	else{
		radButtonValue = 'Eng';
	}
	if(radButtonValue==language)
	{
		if(mandatoryFlag=='Y')
			return true;
		else 
			return false;
	}
	else
	{
		return false;
	}
}


function loadBranchesFromLocation()
{

	var locId = document.getElementById('LOCATION_ID_RefDocsLoc').value;
	var locCode = document.getElementById('LOCATION_CODE_RefDocsLoc').value;
	var postId=document.getElementById('loginuserPostId').value;
	document.getElementById('id_locCode').value=locCode;
	var deptid=document.getElementById('DEPT_ID_RefDocsLoc').value;
	document.getElementById('id_deptId').value=deptid;



	if(locCode!='')
	{
		try
	    	{   
	    	// Firefox, Opera 8.0+, Safari    
	    		xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
				try
	      		{
	      			
	      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	      		   
	      		}
			    catch (e)
			    {
			          try
	        		  {
	                	      //alert("here2");
	        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	        		  }
				      catch (e)
				      {
				              alert("Your browser does not support AJAX!");        
				              return false;        
				      }
				 }
	    	}
	    
	    
			//document.getElementById('submitBT').disabled=false;
			
			document.getElementById('dd_section').style.display='';
			var locBranchFlag= 'Loc';
			
	    	var url = "${contextPath}/hdiits.htm?actionFlag=getSubjectsFromBranch&id="+postId+"&comboVal="+locBranchFlag;
	    	var comboid = document.getElementById('dd_section');
	    	var tagName = 'Branch';
	    		
	    	       xmlHttp.onreadystatechange = function()
					{
						if (xmlHttp.readyState == 4) 
						{     
							if (xmlHttp.status == 200) 
							{
								var XMLDoc=xmlHttp.responseXML.documentElement;
								var tableentries = XMLDoc.getElementsByTagName(tagName);	
								if(comboid.length > 1)
							    		  {
							    		     clearList(comboid);
							    		  }
			           			for ( var i = 0 ; i < tableentries.length ; i++ )
			     				{
			     					
			     					id=tableentries[i].childNodes[0].text;
			     					name=tableentries[i].childNodes[1].text;
			     					var element=document.createElement('option');
				     				
				     				
				     				element.text=name
				     				element.value=id
				     				
				     				try
								    {
								    comboid.add(element,null); // standards compliant
								    }
								    catch(ex)
								    {
								    comboid.add(element); // IE only
								    }
			
							    }// end of for
							    	
							    }//end of if
							
						}
				    }
				    
				   
			        xmlHttp.open("POST", encodeURI(url) , false);    
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
					xmlHttp.send(encodeURIComponent(null));
	 }
	 else
	 {
	 	alert('<fmt:message key="WF.SELLOCMSG" bundle="${fmsLables}"/>');
	 } 
    
}
</script>

<script>

	function checkForBlankSpaceAttributes()
	{
		var txnDtlsId = document.getElementById('FmsTemplateMetadataTxnDetails').value;
		var fromDraftsFlag = document.getElementById('fromDraftsFlag').value;
		if(txnDtlsId=='notfilled')
		{
			if(fromDraftsFlag!='Y')
			{
				var branchId = document.getElementById('dd_section').value;
				
				/*if(branchId=='')
				{
					alert("Please enter branch");
					return false;
				}
			
				else
				{*/
					if(checkBlankEnglishSpace())
					{
						if(checkBlankGujaratiSpace())
							return true;
						else
							return false;
					}
					else 
						return false;
				//}
			}
			else{
				if(checkBlankEnglishSpace())
				{
					if(checkBlankGujaratiSpace())
						return true;
					else
						return false;
				}
				else 
					return false;
			}
		}
		else{
			
			if(checkBlankEnglishSpace())
				{
					if(checkBlankGujaratiSpace())
						return true;
					else
						return false;
				}
				else 
					return false;
		}
		
				
	}


	function checkBlankGujaratiSpace()
	{
			var totalGujAttributes = '${counterForGujarati}';
			
			for(var i=1;i<=parseInt(totalGujAttributes);i++)
			{
				if(document.getElementById("attCode_Guj_"+(parseInt(i))) != null)
				{
					var name = "attCode_Guj_"+(parseInt(i));
					if(document.getElementById(name).value == "")
					{
						if(confirm(" You have not enetered Gujarati values for Attribute " + document.getElementById("attNameGuj_"+(parseInt(i))).innerHTML))
						{
							document.getElementById("attCodeGuj_"+(parseInt(i))).value = document.getElementById("attCodeEng_"+(parseInt(i))).value.trim();
							document.getElementById("attCode_Guj_"+(parseInt(i))).value = document.getElementById("attCode_value_"+(parseInt(i))).value.trim();
						}
						else
						{
							document.getElementById("attCode_Guj_"+(parseInt(i))).focus();
							return false;
						}
					}
				}
			}
			return true;
		
	}
	
	function checkBlankEnglishSpace()
	{
			var totalEngAttributes = '${counterForEnglish}';
			for(var i=1;i<=parseInt(totalEngAttributes);i++)
			{
				if(document.getElementsByName("attCode_value_"+(parseInt(i))) != null)
				{
					var name = "attCode_value_"+(parseInt(i));
					if(document.getElementById(name).value == "")
					{
						if(confirm(" You have not enetered English values for Attribute " +document.getElementById("attNameEng_"+(parseInt(i))).innerHTML))
						{
							document.getElementById("attCodeEng_"+(parseInt(i))).value = document.getElementById("attCodeGuj_"+(parseInt(i))).value.trim();
							document.getElementById("attCode_value_"+(parseInt(i))).value = document.getElementById("attCode_Guj_"+(parseInt(i))).value.trim();
						}
						else
						{
							document.getElementById("attCode_value_"+(parseInt(i))).focus();
							return false;
						}
					}
				}
			}
			return true;
	}
</script>
<%}
catch(Exception e)
{
	e.printStackTrace();
}

%>