<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="commlist" value="${resValue.commlist}"></c:set>
<c:set var="aclElementMstList" value="${resValue.aclElementMstList}" scope="session"></c:set>
<c:set var="hideMenuLookupID" value="${resValue.hideMenuLookupID}"></c:set>
<%@page import="java.util.ArrayList"%>
<style type="text/css">
.displaySpecific
{
	background-color:#EEDFCC;
} 

.displayGeneric
{
	background-color:rgb(233,233,233);
}
</style>

<%

request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
{

    public String addRowClass()
    {
    	try
    	{
    				String commcat=(((ArrayList)getCurrentRowObject()).get(4)).toString();
    				System.out.println("commcat"+commcat);
    				
    		    	if(commcat.equalsIgnoreCase("Generic"))
		        	{
		        		getPageContext().setAttribute("className","displayDataalt");
		        		return "displayGeneric";
		        	}
		        	else
		        	{
		        		getPageContext().setAttribute("className","displayData");
		        		return "displaySpecific";
		        	}
		        
	     }
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return "displayDataalt";
    	}
    }
});
 %>
<script>
function viewCommuniqueDtl(commnumber,intiatorcommno,topost)
{
	document.getElementById('fromPosthdn').value="";
	document.getElementById('toPosthdn').value=topost;
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,type=fullWindow,fullscreen'; 
	window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&moduleName=WorkList&menuName=commonRecAction&attchment=yes&commno='+commnumber+'&intiatorcommno='+intiatorcommno,'',urlStyle);
}
function viewDefaulterList()
{

	var commnumber=document.getElementById('commnohdn').value
	if(commnumber!='')
	{
		var category=document.getElementById('commcathdn').value;
		if(category=='Generic')
		{
			alert("Defaulter List Can not be gnerated on Generic Communqiue");
		}
		else
		{		
			var urlStyle ='width=500,height=300,toolbar=no,status=no,menubar=no,location=no,scrollbars=yes,top=250,left=250'; 
			window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDefaulterList&commno='+commnumber,"defaulterlist",urlStyle);
		}	
	}
	else
	{
		alert('<fmt:message key="WF.SelOneCommMsg" bundle="${fmsLables}"></fmt:message>')
	}	
}
function preparedList(commno,category,frompost,status,crtpost,initiatorcommno,toPost)
{
	
	document.getElementById('commnohdn').value=commno;
	document.getElementById('commcathdn').value=category;
	document.getElementById('fromPosthdn').value=frompost;
	document.getElementById('statushdn').value=status;
	document.getElementById('creatorposthdn').value=crtpost;
	document.getElementById('initiatorcommnohdn').value=initiatorcommno;
	document.getElementById('toPosthdn').value=toPost;
}
function CommuniqueAction(action)
{
	var commnumber=document.getElementById('commnohdn').value
	if(commnumber!='')
	{
		
		var commnumber=document.getElementById('commnohdn').value;
		var intiatorcommno=document.getElementById('initiatorcommnohdn').value;
		var commact=action;
		var frompost=document.getElementById('fromPosthdn').value;
		var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,type=fullWindow,fullscreen'; 
		if(commact=='fwdattach')
		{
			
			window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=yes&forward=yes&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno,'',urlStyle);
		}
		else if(commact=='fwd')
		{
			window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=no&forward=yes&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno,'',urlStyle);
		}
		else if(commact=='fwdasnew')
		{
			window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&forwardasnew=yes&attchment=yes&forward=yes&replyflag=yes&commno='+commnumber+'&fromPost='+frompost,'',urlStyle);
		}
	}
	else
	{
		alert('<fmt:message key="WF.SelOneCommMsg" bundle="${fmsLables}"></fmt:message>')
	}
}
function change_parent_url()
{
	document.location='hdiits.htm?actionFlag=FMS_viewSentDetailList&moduleName=WorkList&menuName=CommuniqueSentMenu';
}
function delfromlist()
{
		var commnumber=document.getElementById('commnohdn').value
		var category=document.getElementById('commcathdn').value;
		var urlStyle ='width=500,height=300,toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=250,left=250'; 
		var to_post=document.getElementById('toPosthdn').value
		if(commnumber!='')
		{
			if(category=='Generic')
			{
					if(confirm('<fmt:message key="WF.CommDelConfirmMsg" bundle="${fmsLables}"></fmt:message>'))
					{
				
						window.open('hdiits.htm?actionFlag=FMS_Close_Communique&action=deletecomm&callfrom=sentitem&commno='+commnumber+'&to_post='+to_post,"delcomm",urlStyle);
						//window.document.forms[0].action ="hdiits.htm?actionFlag=FMS_Close_Communique&action=deletecomm&commno=+commnumber';
						//window.document.forms[0].submit();
					 
					}
				
			}
			else
			{	
				var status=document.getElementById('statushdn').value;
				if(status=='Close')
				{
					if(confirm('<fmt:message key="WF.CommDelConfirmMsg" bundle="${fmsLables}"></fmt:message>'))
					{
				
						window.open('hdiits.htm?actionFlag=FMS_Close_Communique&action=deletecomm&callfrom=sentitem&commno='+commnumber+'&to_post='+to_post,"delcomm",urlStyle);
						//window.document.forms[0].action ="hdiits.htm?actionFlag=FMS_Close_Communique&action=deletecomm&commno=+commnumber';
						//window.document.forms[0].submit();
					 
					}
				}
				else
				{
					alert('<fmt:message key="WF.SelCommClosedMsg" bundle="${fmsLables}"></fmt:message>')
				}	
			}
		}
		else
		{
			alert('<fmt:message key="WF.SelOneCommMsg" bundle="${fmsLables}"></fmt:message>')
		}
			

}
function closecommunique()
{
	var urlStyle ='width=500,height=300,toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=250,left=250'; 

	var category=document.getElementById('commcathdn').value;
	var commnumber=document.getElementById('commnohdn').value
	var status=document.getElementById('statushdn').value;
	var to_post=document.getElementById('toPosthdn').value
	
	if(category!='')
	{
	
		if(status!='Close')
		{
			if(category=='Generic')
			{
				
				//	window.document.forms[0].action ="hdiits.htm?actionFlag=FMS_Close_Communique&commno="+commnumber;
				//	window.document.forms[0].submit();
				alert('<fmt:message key="WF.GenCommCloseMsg" bundle="${fmsLables}"></fmt:message>')	
			}
			else
			{
				var loginPost='${resValue.loginPost}';
				var crt_post=document.getElementById('creatorposthdn').value;
				if(loginPost==crt_post)
				{
			     	if(confirm('<fmt:message key="WF.CommCloseConfirmMsg" bundle="${fmsLables}"></fmt:message>'))
					{
						window.open('hdiits.htm?actionFlag=FMS_Close_Communique&action=closecomm&callfrom=sentitem&commno='+commnumber+'&to_post='+to_post,"delcomm",urlStyle);
						//window.document.forms[0].action ='hdiits.htm?actionFlag=FMS_Close_Communique&action=closecomm&commno='+commnumber;
						//window.document.forms[0].submit();
					}
				}
				else
				{
					alert('<fmt:message key="WF.CommCloseMsg" bundle="${fmsLables}"></fmt:message>');
				}	
			}
		}
		else
		{
			alert('<fmt:message key="WF.SelCommCloseMsg" bundle="${fmsLables}"></fmt:message>');
		}	
	}
	else
	{
		alert('<fmt:message key="WF.SelOneCommMsg" bundle="${fmsLables}"></fmt:message>')
	}
	
}
</script>
<hdiits:form name="communiquelistfrm" method="post" validate="true" encType="multipart/form-data">
<hdiits:hidden name="commnohdn" />
<hdiits:hidden name="commcathdn" />
<hdiits:hidden name="fromPosthdn" />
<hdiits:hidden name="toPosthdn" />
<hdiits:hidden name="statushdn" />
<hdiits:hidden name="creatorposthdn" />
<hdiits:hidden name="initiatorcommnohdn" />
<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
</jsp:include>
<br>
	<fieldset>
	<b><legend ><fmt:message key="WF.Legend" bundle="${fmsLables}"></fmt:message></legend></b>

	<table  >
		<tr>
			<td bgcolor="#EEDFCC" width="10%" bordercolor="black" >
			</td>
			<td style="border: none">
				Specific Communique
			</td width="40%">
			<td bgcolor="rgb(233,233,233)" width="10%" bordercolor="black" >
			</td>
			<td width="40%" style="border: none">
				Generic Communique
			</td>
		</tr>
	</table>
	</fieldset>
	<display:table list="${commlist}" id="row" style="width:100%" pagesize="10" requestURI=""  decorator="dyndecorator" >
		
		
		<display:column class="tablRecelltext" titleKey="rad"  headerClass="datatableheader" >
		<hdiits:radio name="SelectCheck"  value="test" onclick="preparedList('${row[0]}','${row[4]}','${row[7]}','${row[5]}','${row[8]}','${row[9]}','${row[10]}')"/>
		</display:column> 	 
		
		<c:if test="${row[4] eq 'Generic'}">
		<display:column class="tablRecelltext" titleKey="WF.CommNo" headerClass="datatableheader"  ><a href="#"  onclick="viewCommuniqueDtl('${row[0]}','${row[6]}','${row[10]}')">${row[0]}</a></display:column>
		</c:if>
		<c:if test="${row[4] ne 'Generic'}">
		<display:column class="tablRecelltext" titleKey="WF.CommNo" headerClass="datatableheader"  ><a href="#"  onclick="viewCommuniqueDtl('${row[0]}','${row[6]}','${row[10]}')">${row[0]}(${row[12]})</a></display:column>
		</c:if>
		<display:column class="tablRecelltext" titleKey="WF.CommTo"  headerClass="datatableheader"  >${row[11]}</display:column>
		
		<display:column class="tablRecelltext"  titleKey="WF.ReplyRec" headerClass="datatableheader"    >${row[13]}</display:column>
		<display:column class="tablRecelltext" titleKey="WF.ReplyNotRec" headerClass="datatableheader"    >${row[14]}</display:column>
		
		<fmt:formatDate value="${row[2]}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="sentDate"/>
		<display:column class="tablRecelltext" titleKey="WF.SendDate"   headerClass="datatableheader"  >${sentDate}</display:column>
		<display:column class="tablRecelltext" titleKey="WF.CommSubject"  headerClass="datatableheader"  >${row[3]}</display:column>
		<display:column class="tablRecelltext" titleKey="WF.CommCat"  headerClass="datatableheader"  >${row[4]}</display:column>
		<display:column class="tablRecelltext" titleKey="WF.CommStatus"  headerClass="datatableheader"  >${row[5]}</display:column>
	</display:table>




</hdiits:form>



<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>