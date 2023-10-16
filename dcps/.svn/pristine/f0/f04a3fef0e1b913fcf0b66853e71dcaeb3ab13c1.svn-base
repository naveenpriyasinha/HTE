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

function viewCommuniqueDtl(commnumber,frompost,initatorcommno)
{
	intiatorcommno=initatorcommno;
	document.getElementById('toPosthdn').value="";
	document.getElementById('fromPosthdn').value=frompost;
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,type=fullWindow,fullscreen'; 
	window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&moduleName=WorkList&menuName=commonRecAction&attchment=yes&commno='+commnumber+'&intiatorcommno='+intiatorcommno,'',urlStyle);
}
function showNewCommunique()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,type=fullWindow,fullscreen'; 
	window.open('hdiits.htm?actionFlag=FMS_viewNewCommunique','',urlStyle);
}
function change_parent_url()
{
	document.location='hdiits.htm?actionFlag=FMS_getCommuniqueDetailList&moduleName=WorkList&menuName=commonRecAction';
}
function delfromlist()
{
		var commnumber=document.getElementById('commnohdn').value
		var category=document.getElementById('commcathdn').value;
		var urlStyle ='width=500,height=300,toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=250,left=250'; 
		
		frompost=document.getElementById('fromPosthdn').value
		if(commnumber!='')
		{
			if(category=='Generic')
			{
				
				if(confirm('<fmt:message key="WF.CommDelConfirmMsg" bundle="${fmsLables}"></fmt:message>'))
				{
			
				window.open('hdiits.htm?actionFlag=FMS_Close_Communique&action=deletecomm&callfrom=recieveditem&commno='+commnumber+'&to_post='+frompost,"delcomm",urlStyle);
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
				
					window.open('hdiits.htm?actionFlag=FMS_Close_Communique&action=deletecomm&callfrom=recieveditem&commno='+commnumber+'&to_post='+frompost,"delcomm",urlStyle);
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
function preparedList(commno,category,frompost,status,crtpost,initiatorcommno)
{
	document.getElementById('commnohdn').value=commno;
	document.getElementById('commcathdn').value=category;
	document.getElementById('fromPosthdn').value=frompost;
	document.getElementById('statushdn').value=status;
	document.getElementById('creatorposthdn').value=crtpost;
	document.getElementById('initiatorcommnohdn').value=initiatorcommno;
	
}
function closecommunique()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 

	var category=document.getElementById('commcathdn').value;
	var commnumber=document.getElementById('commnohdn').value
	var status=document.getElementById('statushdn').value;
	frompost=document.getElementById('fromPosthdn').value
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
						window.open('hdiits.htm?actionFlag=FMS_Close_Communique&action=closecomm&callfrom=recieveditem&commno='+commnumber+'&to_post='+frompost,"delcomm",urlStyle);
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
		
		if(commact=='replyattach')
		{
	
			window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=yes&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno,'',urlStyle);
		}
		else if(commact=='reply')
		{
			window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=no&replyflag=yes&commno='+commnumber+'&fromPost='+frompost+'&intiatorcommno='+intiatorcommno,'',urlStyle);
		}
		else if(commact=='fwdattach')
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
function showPrint()
{
	window.print();
	
	//var lenth = document.getElementById("row").getElementsByTagName("tr").length;
	
	/*for(var i=0;i<lenth;i++)
	{
		document.getElementById("row").getElementsByTagName("tr")[i].style.fontSize=24;
	}*/
	
}

</script>
<hdiits:form name="communiquelistfrm" method="post" validate="true" encType="multipart/form-data">
<hdiits:hidden name="commnohdn" />
<hdiits:hidden name="commcathdn" />
<hdiits:hidden name="fromPosthdn" />
<hdiits:hidden name="statushdn" />
<hdiits:hidden name="creatorposthdn" />
<hdiits:hidden name="initiatorcommnohdn" />
<hdiits:hidden name="toPosthdn" />


	<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
	</jsp:include>
	<hdiits:button style="display:none"  name="btnprint" type="button" captionid="WF.Print" bundle="${fmsLables}" onclick="showPrint()"/>
	<fieldset>
	<b><legend ><fmt:message key="WF.Legend" bundle="${fmsLables}"></fmt:message></legend></b>

	<table border="0" >
		<tr>
			<td bgcolor="#EEDFCC" width="10%">
			</td>
			<td>
				Specific Communique
			</td width="40%">
			<td bgcolor="rgb(233,233,233)" width="10%">
			</td>
			<td width="40%">
				Generic Communique
			</td>
		</tr>
	</table>
	</fieldset>
	

	<display:table list="${commlist}" id="row" style="width:100%" pagesize="10" requestURI=""  decorator="dyndecorator" >
		
		
		<display:column class="tablecelltext" titleKey="rad"  headerClass="datatableheader" >
		<hdiits:radio name="SelectCheck"  value="test" onclick="preparedList('${row[0]}','${row[4]}','${row[6]}','${row[5]}','${row[7]}','${row[8]}')"/>
		</display:column>

	 	<display:column class="tablecelltext" titleKey="WF.CommNo"  headerClass="datatableheader"  ><a href="#" onclick="viewCommuniqueDtl('${row[0]}','${row[6]}','${row[8]}')">${row[0]}</a></display:column>
		<display:column class="tablecelltext" titleKey="WF.CommFrom"  headerClass="datatableheader"  >${row[1]}</display:column>
		<fmt:formatDate value="${row[2]}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="recDate"/>
		<display:column class="tablecelltext" titleKey="WF.CommRecDate"   headerClass="datatableheader"  >${recDate}</display:column>
		<display:column class="tablecelltext" titleKey="WF.CommSubject"   headerClass="datatableheader"  >${row[3]}</display:column>
		<display:column class="tablecelltext" titleKey="WF.CommCat"  headerClass="datatableheader"  >${row[4]}</display:column>
		<display:column class="tablecelltext" titleKey="WF.CommStatus"   headerClass="datatableheader"  >${row[5]}</display:column>
	</display:table>




</hdiits:form>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>