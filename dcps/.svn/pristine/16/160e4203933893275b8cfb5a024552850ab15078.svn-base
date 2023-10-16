

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.rcptacct.rcptacct" var="rcptacctLabels" scope="application"/>
<fmt:setBundle basename="resources.rcptacct.rcptacct" var="rcptacctLabels" scope="request"/>
<fmt:setBundle basename="resources.expacct.expacct" var="expacctLabels" scope="request"/>
<fmt:setBundle basename="resources.expacct.expacctAlerts" var="expacctAlert" scope="request"/>
<fmt:setBundle basename="resources.expacct.expacctHeads" var="expacctHeads" scope="request"/>


<link rel="stylesheet" href="<c:url value="/themes/${themename}/statusbar1.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/themes/${themename}/exprcpt.css"/>" type="text/css" />

<script type="text/javascript">
function checkUncheckAll(theElement) 
{
     var theForm = theElement.form, z = 0;
	 for(z=0; z<theForm.length;z++)
	 {
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect')
	      {
			  theForm[z].checked = theElement.checked;
		  }
     }
}
function submitForm()
{
		var checkList = document.getElementsByName("AccessId");
		var flag = 0;
		for(var i = 0; i < checkList.length; i++)
		{
			if(checkList[i].checked)
			{
				flag = 1;
				break;
			}
		}
		if(flag == 0)
		{
			alert("<fmt:message key="CA.SELECT_CHECKBOX" bundle="${expacctAlert}"/>");
			return;
		}
		document.frm_DealerDtls.actionBtn.value = 'Save';
		saveDealerDataUsingAjaxValidate();
}
function saveDealerDataUsingAjaxValidate()
{
	disable();
	showProgressbar();	
	window.setTimeout("handleDealerDataAjaxSave()",1000);
}

function handleDealerDataAjaxSave()
{
	xmlHttp=GetXmlHttpObject();		
	if (xmlHttp==null)
	{
		alert ("<fmt:message key="CMN.BROWSERSUPPORT" bundle="${expacctAlert}"/>");
		return;
	} 
	  
	var url=run(); 
	var actionf=document.forms[0].actionFlag.value
	var uri='ifms.htm?actionFlag='+actionf;
	url=uri + url;           
	xmlHttp.onreadystatechange=dealerDatastateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);
         //			alert(xmlHttp.responseText.length);
    if(xmlHttp.responseText!='true')
   	{
		if(xmlHttp.responseText.length<=100)
			alert(xmlHttp.responseText);
	}
		
}

function dealerDatastateChanged() 
{ 
	if (xmlHttp.readyState==complete_state)
	{ 
		hide_img();
		enable_div();
		hideProgressbar();
		enable();
	    var state = xmlHttp.responseText;
	    if(state=='true')
		{
			saveDealerDataUsingAjax()
		}
		if(state == 'Data saved successfully')
		{
			document.dummyForm.submit();
			return;
		}
		else
		{
			alert("Server alert.Data cannot be saved.");
			document.dummyForm.submit();
		}
	}
}

function saveDealerDataUsingAjax()
{
	disable();
	showProgressbar();	
	window.setTimeout("handleDealerDataAjaxSave()",1000);
}

</script>
<style>
.tabstyle {
	border-width: 5px 1px 1px 1px; 
	border-color: #2065c0;
	border-style: solid;
	padding-top:5px;
	padding-bottom:5px;
	padding-left:5px;
	padding-right:5px;
	}
	legend {
	
	padding-left:5px;
	padding-right:5px;
	font-weight:bold; 
	font-family:sans-serif;
	font-color:#2065c0;

	border-width: 0px 0px 1px 0px; 
	border-color: #2065c0;
	border-style: solid 
	}
	.inp
	{
    border-width: 1px 1px 1px 1px; 
	border-color: #2065c0;
	border-style: solid ;
 	background-color: white;
	font-family: verdana;
	padding: 2px 2px 2px 2px; 
	}
	select
	{
    border-width: 1px 1px 1px 1px; 
	border-color: #2065c0;
	border-style: solid ;
 	background-color: white;
	font-family: verdana;
	padding: 2px 2px 2px 2px; 
	}
</style>


<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>		
<c:set var="scrollDtlsList" value="${resValue.scrollDtlsList}"></c:set>

<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
<div id="progressImage" style="display:none"></div>

<table cellspacing="0" cellpadding="0" align="center" width="98%">
	<tr> 
	    <td  align="center" class="formTitle">Sales Tax Dealer Details</td>
	</tr>
	<tr>
		<td>
			<fieldset class="tabstyle">
			<legend  id="headingMsg">Accept Dealer Details</legend>
			<table align="center" width="100%">
	
				<tr height="10">
					<td>
					</td>
				</tr>
				<hdiits:form validate="true" method="post" name="frm_DealerDtls">						
					<c:choose>
						<c:when test="${scrollDtlsList!=null}">
							<tr>
								<td>	
								<div style="height: 500px; overflow-x: hidden; overflow-y: auto;">	
			 						 <display:table list="${scrollDtlsList}" pagesize="12" id="vo" excludedParams="ajax" export="false" partialList="" style="width:98%" requestURI="ifms.htm?actionFlag=showSalestaxDealerdtls">
									    
									    <display:setProperty name="paging.banner.placement" value="both"/>
										   <display:column class="oddcentre" sortable="true" headerClass="datatableheader" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>" style="text-align:center"><input type="checkbox" name="AccessId" value="${vo.accessId}"></display:column>
										<display:setProperty name="paging.banner.placement" value="top"/>  
									       <display:column class="oddcentre" sortable="true" property="uploadedDate"  title="Uploaded Date"  headerClass="tableHeader" style="text-align:center" />       
										   <display:column class="oddcentre" sortable="true" property ="userName"  title="User Name"  headerClass="tableHeader" style="text-align:center"/>
										   <display:column class="oddcentre" sortable="true" property ="latestDate"  title="Latest Dealer Data Date"  headerClass="tableHeader" style="text-align:center"/>
							 			   <display:column class="oddcentre" sortable="true" title="File Name"  headerClass="datatableheader" style="text-align:center"><a href='ifms.htm?actionFlag=readAttachment&attachmentId=${vo.attachmentId}&fileName=${vo.fileName}'><c:out value="${vo.fileName}"></c:out></a></display:column>
							 			   <display:column class="oddcentre" sortable="true" property="noOfRecords"  title="No Of Records"  headerClass="tableHeader" style="text-align:center"/>
							    <display:footer media="html">
									  	</display:footer>
						 			</display:table>
				 				</div>
								</td>
				</tr> 
				
				<tr>
					<td height="20"></td>
				</tr>
				<tr>
					<td align="center">
							<input type="hidden" name="actionFlag" value="accSalesTaxDealerDtls" />
							<input type="button" name="btnSubmit"  value="     Accept     " onclick="submitForm()" class = "bigbutton">
							<input type ="button" name="btnClose"  value="     Close      " class= "bigbutton" onclick="window.location.href='ifms.htm?viewName=homePage'" >
							<input type="hidden" name="actionBtn" value="" id="actionBtn"/>
					</td>
				</tr>
				
				</c:when>
				<c:otherwise>
				<tr>
					<td height="20" align="center">NO DATA AVAILABLE</td>
				</tr>
				<tr>
					<td height="20" align="center"></td>
				</tr>
				<tr>
					<td height="20" align="center"><hdiits:button name="btnCloseo" type="button" value="Close" onclick="window.location.href='ifms.htm?viewName=homePage'"/></td>
				</tr>
				</c:otherwise>
			</c:choose>
		</hdiits:form>
	</table>
	</fieldset>
	</td>
	</tr>
</table>

<hdiits:form name="dummyForm"  id="id_dummyForm" method="post" validate="true" action="ifms.htm?actionFlag=showSalestaxDealerdtls">
</hdiits:form>		 