<%
try {
	

%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="listfmsCorrMst" value="${resValue.listfmsCorrMst}"></c:set>
<c:set var="listfmsFileNotings" value="${resValue.listfmsFileNotings}"></c:set>
<c:set var="listCmnLookupMst" value="${resValue.listCmnLookupMst}"></c:set>

<c:set var="criteria" value="${resValue.Criteria}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<c:out value="${criteria }"></c:out>
<hdiits:form name="TappalSide" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
<hdiits:hidden name="Criteria" default="${resValue.Criteria}"/>
<hdiits:hidden name="richtextId" default="${resValue.richtextId}"/>
<hdiits:hidden name="selectCorrId" />


<table><tr><td>


		<hdiits:select id="search_key" name="search_key" sort="false"	onchange="changeCrit()">
			<c:forEach items="${listCmnLookupMst}" var="PriorityLookup">							
				
			<c:if test="${PriorityLookup.lookupName ne (criteria)}">
			<option value='<c:out value="${PriorityLookup.lookupId}"/>' title="${PriorityLookup.lookupName}">
			<c:out value="${PriorityLookup.lookupName}" /></option>
			</c:if>
			<c:if test="${PriorityLookup.lookupName eq (criteria)}">
			<option value='<c:out value="${PriorityLookup.lookupId}"/>'title="${PriorityLookup.lookupName}" selected="true">
			<c:out	value="${PriorityLookup.lookupName}" /></option>
			</c:if>		
				
				
			</c:forEach>
		</hdiits:select>



</td>
</tr>
<c:set var="srNo_ic" value="${1}" />
<tr><td>
	<c:if test="${not empty listfmsCorrMst}">
		<display:table list="${listfmsCorrMst}" pagesize="12" requestURI="" id="row"  defaultsort="1" style="width:100%">
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML"> <input type="radio" name="corrRadio"  value="${row.corrId}" checked="checked" />	</display:column>				
			<display:column titleKey="WF.SRNO" headerClass="datatableheader" sortable="true">${srNo_ic}</display:column>
			<display:column titleKey="WF.AddedCorr" headerClass="datatableheader" media="HTML"><a style="cursor:hand;"  onclick="openCorr('${row.corrId}')"><font color="blue">${row.corrNo}</font></a></display:column>
			<display:column titleKey="WF.ViewCORR" headerClass="datatableheader" media="HTML"><a style="cursor:hand;"  onclick="parent.changeFrameSrc('${row.corrId}','${param.fileId}')"><font color="blue"><fmt:message bundle="${fmsLables}" key="WF.ViewCORR"></fmt:message></font></a></display:column>
			<c:set var="srNo_ic" value="${srNo_ic+1}" />
		</display:table>
	</c:if>
	<c:if test="${not empty listfmsFileNotings}">
		<display:table list="${listfmsFileNotings}" pagesize="12" requestURI="" id="row"  defaultsort="1" style="width:100%">
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML"> <input type="radio" name="corrRadio"  value="${row.notingId}" checked="checked" />	</display:column>				
			<display:column titleKey="WF.SRNO" headerClass="datatableheader" sortable="true">${srNo_ic}</display:column>
			<display:column titleKey="WF.AddedCorr" headerClass="datatableheader" media="HTML"><a style="cursor:hand;" ><font color="blue">${row.htmlNoteContent}</font></a></display:column>
			<c:set var="srNo_ic" value="${srNo_ic+1}" />
		</display:table>
	</c:if>
</td>
</tr>
</table>
<center><hdiits:button name="Close" caption="CLOSE" type="button" onclick="javascript:self.close();parent.focus();" />
<hdiits:button name="Adde" caption="Add" type="button" onclick="javascript:addUrl()" />
</hdiits:form>




<script language="javascript">


function changeCrit()
{
	
	var key=document.getElementById('search_key').value;
	if(key==1)
		document.getElementById('Criteria')	.value='Correspondence';
	else if(key==2)
		document.getElementById('Criteria')	.value='Notings';
	else if(key==3)
		document.getElementById('Criteria')	.value='Precendence cases';
	else if(key==4)
		document.getElementById('Criteria')	.value='Knowledge Bank';
	else if(key==5)
		document.getElementById('Criteria')	.value='Comminque';
	
	var action="hdiits.htm?actionFlag=fms_showaddedCorrespondenceWithFile&Criteria="+document.getElementById('Criteria').value+"&fileId=${fileId}&DocFilterKey=2&showOnlyClose=true&docId=${docId}&richtextId=${richtextId}"
	
	document.getElementById("TappalSide").method="post";
	document.getElementById("TappalSide").action=action;	
					
	document.getElementById("TappalSide").submit();
}


function addUrl()
{
			var radioObj = document.getElementsByName('corrRadio');
			
			var radLength = document.getElementsByName('corrRadio').length;
			var richText=document.getElementById('richtextId').value;
			
			for(var counter=0; counter<radLength; counter++)
			{
				if(radioObj[counter].checked==true)
				{
					document.getElementById('selectCorrId').value=radioObj[counter].value;				
					
				}
			}
			var key=document.getElementById('search_key').value;
			
			if(key==2)
				viewNoting();
	
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
		
			   var fileId='${resValue.fileId}';
			  
			 	var url = "${contextPath}/hdiits.htm?actionFlag=FMS_GETATTACHMENT&corrIdList="+document.getElementById('selectCorrId').value;
 		       
				
		        xmlHttp.onreadystatechange = function()
				{
					
					if (xmlHttp.readyState == 4) 
					{     
						if (xmlHttp.status == 200) 
						{
							if(xmlHttp.responseXML.documentElement!=null)
							{
							
							
									var XMLDoc=xmlHttp.responseXML.documentElement;
									var FmsActionDetail = XMLDoc.getElementsByTagName('Attachment');
									
									var id=FmsActionDetail[0].childNodes[0].text;									
									var srNo=FmsActionDetail[0].childNodes[1].text;
									var OutURL="hdiits.htm?actionFlag=viewAttachment&attachmentId="+id+"&attachmentSerialNumber="+srNo;	
									
									var html='<A href="'+OutURL+'" target=_new style="cursor:hand" contentEditable="false" unselectable="On" >view Attachment</a>';
									window.opener.insertHTML(html,richText);
    								window.close();
										
							 }	     		
			     		}
					}
						    	
			    }
			    
		        xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));
				return true;

}
function viewNoting()
{
	
	var notingId=document.getElementById('selectCorrId').value;
	var OutURL="hdiits.htm?actionFlag=fms_showAddedNoting&notingId="+notingId;
	var richText=document.getElementById('richtextId').value;
	var html='<A href="'+OutURL+'" target=_new style="cursor:hand" contentEditable="false" unselectable="On" >view Noting</a>';
	window.opener.insertHTML(html,richText);
    window.close(); 

}

</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>