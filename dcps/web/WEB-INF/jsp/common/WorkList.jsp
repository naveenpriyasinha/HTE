
<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tcs.sgv.wf.valueobject.WfDocNotificationListVo"%>
<%@page import="org.displaytag.decorator.MultilevelTotalTableDecorator"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="docNameList" value="${resultMap.docNameList}"></c:set>
<c:set var="fullNameString" value="${resultMap.fullNameString}"></c:set>
<c:set var="allDate" value="${resultMap.allDates}"></c:set>
<c:set var="subjectFilter" value="${resultMap.subjectFilter}"></c:set>
<c:set var="recieveDateFrom" value="${resultMap.recieveDateFrom}"></c:set>
<c:set var="recieveDateTo" value="${resultMap.recieveDateTo}"></c:set>
<c:set var="aclElementMstList" value="${resultMap.aclElementMstList}" scope="session"></c:set>
<c:set var="noOfLevelsInMenu" value="${resultMap.noOfLevelsInMenu}" scope="session"></c:set>
<c:set var="hideMenuLookupID" value="${resultMap.hideMenuLookupID}"></c:set>

<%
int i = 1;
try{
	
	%>

	<script ="text/javascript" src="script/common/tabcontent.js"></script>
	<script type="text/javascript"></script>

<fmt:setBundle basename="resources.WFLables" var="commonLables" scope="request"/>



<script type="text/javascript">

 window.onblur = function() {
 		try
 		{
        docWindow.focus();
        }
        catch(e)
        {
        }
    }
    
</script>
    



<script language="javascript">

	function openDocument(url)
	{
	
	var urlstyle="location=0,status=0,scrollbars=1,width=screen.availWidth,height=screen.availHeight"
	//docWindow = window.open (url,"Document","location=0,status=0,scrollbars=1"); 
	docWindow = window.open (url,"Document",urlstyle); 
	docWindow.resizeTo(screen.availWidth,screen.availHeight)
	docWindow.moveTo(0,0);  
	
	}
	
	function setSelectedCheckboxValue(src,obj,objJobRefId,objFrmPost)
	{
		
		var firstIndex = obj.lastIndexOf("corrId=");	
		
		var lastIndex = obj.lastIndexOf("&");	

		   
		var selectedId  = obj.substring(eval(firstIndex)+7,obj.lastIndexOf("&"));
		
		if(src.checked == true)
		{
			if(document.forms[0].selectedCheckboxex.value.indexOf(selectedId) == -1)
			{
				if(document.forms[0].selectedCheckboxex.value != ''){
					document.forms[0].selectedCheckboxex.value = document.forms[0].selectedCheckboxex.value + "," + selectedId;
					document.forms[0].lstActPostIds.value = document.forms[0].lstActPostIds.value + "," + objFrmPost;
					document.forms[0].jobRefIds.value = document.forms[0].jobRefIds.value + "," + objJobRefId;
					
				}	
				else {
					document.forms[0].selectedCheckboxex.value = selectedId;
					document.forms[0].lstActPostIds.value = objFrmPost;
					document.forms[0].jobRefIds.value = objJobRefId;
				}	
			}
			else
			{
				alert(selectedId + '<fmt:message key="WF.WF.AlreadySelected" bundle="${commonLables}"></fmt:message>');

			}	
		}	
		else
		{
					var corr_id = document.forms[0].selectedCheckboxex.value.split(",");
					var post_id = document.forms[0].lstActPostIds.value.split(",");
					var jobref_id = document.forms[0].jobRefIds.value.split(",");
					var tempId = ""; // for temp storage.......
					var tempPostId = "";
					var tempJobId = "";
					for (var i = 0; i < corr_id.length; i++) {
						
						
						if(corr_id[i] == selectedId){
							
							;
						}
						else {
							
							if(tempId == '') {
								tempId = corr_id[i];
								tempPostId = post_id[i];
								tempJobId = jobref_id[i];
								
							}
							else {
								tempId = tempId + "," + corr_id[i];
								tempPostId = tempPostId + "," + post_id[i];
								tempJobId = tempJobId + "," + jobref_id[i] 
							}
							
						}
							
					}
					
				
				document.forms[0].selectedCheckboxex.value = tempId;
				document.forms[0].lstActPostIds.value = tempPostId;
				document.forms[0].jobRefIds.value = tempJobId;
				document.forms[0].selectedCorrespondenceForFile.value = '';
				
			
		}
		
		  
			//alert(document.forms[0].lstActPostIds.value);
			//alert(document.forms[0].jobRefIds.value);
				
	}

</script>

<hdiits:form name="frmcsearch" validate="true" method="post" action="./ifms.htm">

<style type="text/css">
.displayData
{
	color:red;
	background-color:#E6E6E6;
} 

.displayDataalt
{
	color:black;
	background-color:white;
}

.displayNoti
{
	color:black;
	background-color:white;
}
</style>
<%

request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
{

    public String addRowClass()
    {
    	try
    	{
    		
    		
    		WfDocNotificationListVo lDocNotificationListVo = (WfDocNotificationListVo)getCurrentRowObject();
    		String exFlag ="";
    		if(lDocNotificationListVo.getExpiredFlag()!=null)
    		exFlag = lDocNotificationListVo.getExpiredFlag().toString();
	    	//System.out.println("cccccccc"+exFlag);
	    	
	    	if(!exFlag.equals(""))
	    	{
	    		if(exFlag.equals("true"))
		        {
	    			
		        	return "displayData";
		        	
		        }
		        else
		        {
		        	
		        	return "displayDataalt";
		        }
	    	
	    	}
	    	else
	    	{
	    		return "displayNoti";
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
 <% pageContext.setAttribute("className","displayData"); %>
 
<hdiits:hidden name="actionFlag" default="getFilteredfWorkList" />
<hdiits:hidden name="docType" default="${resultMap.DocFilterKey}" />
 
<hdiits:hidden name="selectedCheckboxex" />
<hdiits:hidden name="lstActPostIds" />
<hdiits:hidden name="selectedCorrespondenceForFile" />
<hdiits:hidden name="jobRefIds" />
<hdiits:hidden name="moduleName" default="${param.moduleName}"/>
<hdiits:hidden name="menuName" default="${param.menuName}"/>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="DocList" value="${resultValue.lstDocs}" > </c:set>
<c:set var="fileId" value="${resultValue.fileId}" > </c:set>
<hdiits:hidden name="successFlag" default="${resultValue.Success}"/>
	<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
	</jsp:include>
	<br>
	
	<jsp:include flush="true" page="../workflow/commonSearch.jsp">
		<jsp:param name="Subjects" value="1"/>
		<jsp:param name="FullName" value="${fullNameString}"/>
		<jsp:param name="allDates" value="${allDate}"/>
		<jsp:param name="subjectFilter" value="${subjectFilter}"/>
		<jsp:param name="recieveDateFrom" value="${recieveDateFrom}"/>
		<jsp:param name="recieveDateTo" value="${recieveDateTo}"/>
		<jsp:param name="finalRecDateFrom" value="${finalRecDateFrom}"/>
	</jsp:include>
	
<br>	
<c:set var="serialNumber" value="${1}" />
  <display:table list="${DocList}" id="row" style="width:100%" pagesize="10" requestURI="" defaultsort="1"  defaultorder="ascending" decorator="dyndecorator">
  <display:setProperty name="paging.banner.placement" value="bottom" />    
	<c:choose>
		<c:when test="${row.docType eq 'DOC'}">  
				<display:column class="tablecelltext" titleKey="Sr_No" headerClass="datatableheader" sortable="false" value="${serialNumber}" style="text-align: center"></display:column>
				<c:set var="serialNumber" value="${serialNumber+1}" />
			    <display:column class="tablecelltext" titleKey="Document_Name" sortable="false" headerClass="datatableheader"  >${row.docName}</display:column>
			    <c:choose>
					<c:when test="${row.expiredFlag eq 'true'}">  
				    	<display:column class="tablecelltext" titleKey="Ref_Id" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><a href= "javascript:openDocument('${row.docUrl}')" ><font color='red'>${row.transactionNumber}</font></a> </display:column> 
				    </c:when>
				    <c:otherwise>
   				    	<display:column class="tablecelltext" titleKey="Ref_Id" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><a href= "javascript:openDocument('${row.docUrl}')" ><font color='black'>${row.transactionNumber}</font></a> </display:column> 
				    </c:otherwise>
				</c:choose>    
				    
				    
			   	<display:column class="tablecelltext" titleKey="Description"  sortable="false" headerClass="datatableheader" >${row.docDesc} </display:column>  	
			   	<fmt:formatDate value="${row.createdDate}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="stmtDate"/>
			   	<display:column class="tablecelltext" titleKey="From_Usr"  sortable="false" headerClass="datatableheader" >${row.fromPostId}</display:column>  	
			   	<display:column class="tablecelltext" titleKey="WF.RECVDATE"  sortable="false" headerClass="datatableheader" >${stmtDate} </display:column>  	
				<display:column class="tablecelltext" titleKey="Job_Status"  sortable="false" headerClass="datatableheader" >${row.jobStatus} </display:column>  	
				<display:column  class="tablecelltext" titleKey="rad" headerClass="datatableheader" style="background: ${colorVal}">
					<c:choose>
							<c:when test="${(row.jobTitle eq 'file') or (row.jobTitle eq 'Correspondence')}">  
								<hdiits:checkbox name="SelectCheck" value="${row.docUrl}" onclick="javascript:setSelectedCheckboxValue(this,'${row.docUrl}','${row.jobRefId}','${row.lstActPostId}')"/>
						</c:when>
						<c:otherwise>
							<hdiits:checkbox name="SelectCheck" value="${row.docUrl}" onclick="javascript:setSelectedCheckboxValue(this,'${row.docUrl}','${row.jobRefId}','${row.lstActPostId}')" readonly="true"/>
						</c:otherwise>		
						</c:choose>
				</display:column>
			
	    </c:when>
	    <c:otherwise>
			<display:column class="tablecelltext" titleKey="Sr_No" headerClass="datatableheader" sortable="false" value="${serialNumber}" style="text-align: center"></display:column>
			<c:set var="serialNumber" value="${serialNumber+1}" />
		    <display:column class="tablecelltext" titleKey="Document_Name" sortable="false" headerClass="datatableheader"  ><b>${row.docName}</b></display:column>
		    <display:column class="tablecelltext" titleKey="Ref_Id" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><a href= "javascript:openDocument('${row.docUrl}')" ><b>${row.transactionNumber}</b></a> </display:column> 
		   	<display:column class="tablecelltext" titleKey="Description"  sortable="false" headerClass="datatableheader" ><b>${row.docDesc}</b> </display:column>  	
			<fmt:formatDate value="${row.createdDate}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="stmtDate"/>
			<display:column class="tablecelltext" titleKey="From_Usr"  sortable="false" headerClass="datatableheader" >${row.fromPostId}</display:column>  	
		   	<display:column class="tablecelltext" titleKey="WF.RECVDATE"  sortable="false" headerClass="datatableheader" >${stmtDate} </display:column>  	
		    <display:column class="tablecelltext" titleKey="Job_Status"  sortable="false" headerClass="datatableheader" ><b>${row.jobStatus}</b> </display:column>  	
		    <display:column  class="tablecelltext" titleKey="rad" headerClass="datatableheader" style="background: ${colorVal}">
							<hdiits:checkbox name="SelectCheck" value="${row.docUrl}" onclick="javascript:setSelectedCheckboxValue(this,'${row.docUrl}','${row.jobRefId}','${row.lstActPostId}')" readonly="true"/>
			</display:column>
		</c:otherwise>
	</c:choose>   
    <!-- <display:footer media="html"> </display:footer>  -->
    <%=++i %>
  </display:table>  
  <hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'></hdiits:validate>
	</hdiits:form>

<%}catch(Exception e){
	e.printStackTrace();
}%>

<script language="javascript">
	function getSelectedValue()
	{
		/*	var elementLength = '<%=i%>';
			alert("elementLength"+elementLength);
			var flag = false;
			var counter = 0;
			for(var len=0; len < eval(elementLength)-1; len++)
			{
				if(document.forms[0].SelectCheck[len].checked == true)
				{
					flag = true;
					counter  = eval(counter) + 1;
					document.forms[0].selectedCorrespondenceForFile.value = document.forms[0].SelectCheck[len].value;
					if(eval(counter) > 1) {
						alert("Please select only one correspondence");
						return false;
					}
						
				}
			} 
			if(flag == false)
			{
				alert("please select atleast one correspondence");
				return false;
			}
			
			var index = document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("=");
			var selectedId = document.forms[0].selectedCorrespondenceForFile.value.substring(eval(index) + 1);*/			
			document.getElementById("frmcsearch").action="${contextPath}/ifms.htm?actionFlag=addCorrinFile&fileId=${fileId}"
			document.getElementById("frmcsearch").submit();
	}
	function createFile()
	{
			
			var selectCheckArrayLength = '<%=i%>';
			if((eval(selectCheckArrayLength) == 2) && (document.forms[0].SelectCheck != null) && document.forms[0].SelectCheck.checked)
			{
				document.forms[0].selectedCorrespondenceForFile.value = document.forms[0].SelectCheck.value
			}
			else if(document.forms[0].SelectCheck != null)
			{
				var arrLength = document.forms[0].SelectCheck.length;
				var flag = false;
				var counter = 0;
				for(var len=0; len < eval(arrLength); len++)
				{
					if(document.forms[0].SelectCheck[len].checked == true)
					{
						flag = true;
						counter  = eval(counter) + 1;
						document.forms[0].selectedCorrespondenceForFile.value = document.forms[0].SelectCheck[len].value;
						if(eval(counter) > 1) {
							alert('<fmt:message key="WF.OnlyOneCorr" bundle="${commonLables}"></fmt:message>');
							return false;
						}
							
					}
				} 
				
				
				
				//if(flag == false)
				//{
					//alert("please select atleast one correspondence");
					//return false;
				//}
				
				
			}// end of else condition.....
			
			
			/*if(document.forms[0].selectedCorrespondenceForFile.value.indexOf("fileId") != -1)
			{
				alert("You can not create file from a file,\n please select corresondence to create file.");
				return false;
			}*/
			//var selectedId = document.forms[0].selectedCorrespondenceForFile.value;
			
			//var corrId=document.forms[0].selectedCorrespondenceForFile.value.substring(0,document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("&"))
			var firstIndex = document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("corrId=");	
		
			var lastIndex = document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("&");	

		    //var selectedId = document.forms[0].selectedCorrespondenceForFile.value.substring(eval(firstIndex) + 7,eval(lastIndex));
		    var selectedId  = document.forms[0].selectedCorrespondenceForFile.value.substring(eval(firstIndex)+7,document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("&"));
			//var selectedId  = obj.substring(eval(firstIndex)+7,obj.lastIndexOf("&"));

		    if(selectedId.indexOf("fileId") != -1)
		    {
		    	alert('<fmt:message key="WF.OnlyOneCorrCrtFile" bundle="${commonLables}"></fmt:message>');
		    	return false;
		    }
		    
		    var urlStyle ='toolbar=no,menubar=no,location=no,top=150,left=200,width=400,height=400,scrollbars=yes';		   
		    if(document.forms[0].selectedCorrespondenceForFile.value.indexOf("fileId") == -1)
			{
				//displayModalDialog('${contextPath}/ifms.htm?actionFlag=loadUnit&createdFromInbox=yes&corrId='+selectedId, 'test', urlStyle) ;
				//alert("selectedId"+selectedId);
				window.open('${contextPath}/ifms.htm?actionFlag=loadUnit&createdFromInbox=yes&corrId='+selectedId,'worklist',urlStyle);	
			}			
			else
			{
				//displayModalDialog('${contextPath}/ifms.htm?actionFlag=loadUnit&createdFromInbox=yes','test',urlStyle) ;
				window.open('${contextPath}/ifms.htm?actionFlag=loadUnit&createdFromInbox=yes','worklist',urlStyle);	
			}
			window.close();	
	}
	window.onload=test

	function test()
	{
				if(document.getElementById('successFlag').value=="True")
				{		window.opener.frames['workflow_tab'].document.getElementById('tab2').onclick();	
						window.opener.frames['Target_frame'].document.forms[0].action="ifms.htm?actionFlag=showaddedCorrespondence&fileId=${fileId}";
						window.opener.frames['Target_frame'].document.forms[0].submit();
						//window.opener.frames['workflow_tab'].document.getElementById("tab2_Link").onclick();
					    document.location = window.opener.frames['workflow_tab'].document.getElementById("tab2_Link");
						//var obj=document.getElementById('tab8');
						//changecolor(obj);
						window.close();
				}
	}			
</script>

<script language="javascript">
	if(document.getElementById("viewdocnameid") != null)
	{
		var cmb = document.getElementById("viewdocnameid");
		for(var cntComboLength=0;cntComboLength<cmb.length;cntComboLength++)
		{
			if("${subjectFilter}" == cmb.options[cntComboLength].value)
			{
				cmb.options[cntComboLength].selected = true;
				break;
			}
		}
	}
</script>
