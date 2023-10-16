<%
try {
	

%>

<%@ include file="../core/include.jsp"%>
<%@ page autoFlush="true" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request" />

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fileDetails" value="${resValue.lfileStatList}"></c:set>


<hdiits:form name="HierarchyForm" method="POST" validate="true" action="./hdiits.htm">
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
<hdiits:hidden name="successFlag" default="${resValue.Success}"/>
<hdiits:hidden name="filePrecedenceCases" />
	
	<display:table list="${fileDetails}" pagesize="12" requestURI="" id="row"  style="width:100%">
		<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="checkbox"  name="radio"  value="${row.fileId}" onclick="addPrecendenceCases('${row.fileId}',this)"> </display:column>
		<display:column  titleKey="WF.FileNo" headerClass="datatableheader">${row.fileNo}</display:column>
		
		<display:column titleKey="WF.Lyingwith" headerClass="datatableheader">${row.lyingWith}</display:column>
		
		<display:column titleKey="WF.Description" headerClass="datatableheader">${row.description}</display:column>
	
	</display:table>
	
	
	
	<center><td><hdiits:button type="button"  name="Addfile" id="Addfile" caption="Add" onclick="Add()" /></td></center>
</hdiits:form>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>

<script>
	function Add()
	{
	
		if(document.getElementById("filePrecedenceCases").value!=''){		
			var action="${contextPath}/hdiits.htm?actionFlag=addPrecendenceCases&fileId=${resValue.fileId}&cases="+document.getElementById("filePrecedenceCases").value;
			document.getElementById("HierarchyForm").method="post";
			document.getElementById("HierarchyForm").action=action;									
			document.getElementById("HierarchyForm").submit();
		}else{
			alert("<fmt:message key="WF.ADDFILE" bundle="${fmsLables}"></fmt:message>");			
		}	
					
	}
	
	
	function addPrecendenceCases(srNo,src){

		if(src.checked == true)
		{			
			if(document.getElementById("filePrecedenceCases").value=='')
				document.getElementById("filePrecedenceCases").value=srNo
			else
				document.getElementById("filePrecedenceCases").value=document.getElementById("filePrecedenceCases").value+','+srNo;
		}else
		{			
			var value=document.getElementById("filePrecedenceCases").value;				
			var index=value.lastIndexOf(srNo);		
			value1=value.substring(0,index-1);			
			if(value1==''){
				var index=value.indexOf(',');	
				value2=value.substring(index+1,value.length);
				//alert(value2);
				}
			else{					
					var index1=value.indexOf(',',index);
					if(index1==-1)
						value2='';
					else
						value2=value.substring(index1,value.length);					
				}
			
			value=value1+value2;
			
			document.getElementById("filePrecedenceCases").value=value;		
		}	
}
	window.onload=test
	function test()
	{	
			
		if(document.getElementById('successFlag').value=="True")
		{	
				var url="hdiits.htm?actionFlag=loadPrecedentCases&fileId="+document.getElementById("fileId").value;				
				window.opener.parent.frames['Target_frame'].document.forms[0].action=url;
				window.opener.parent.frames['Target_frame'].document.forms[0].submit();				
				window.close();
		}
	}
	
</script>
