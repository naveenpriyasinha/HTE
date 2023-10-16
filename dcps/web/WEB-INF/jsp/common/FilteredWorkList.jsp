<%@ include file="../core/include.jsp" %> 
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.tcs.sgv.wf.valueobject.WfDocNotificationListVo"%>
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
<c:set var="showButton" value="${param.menuName}"></c:set>
<c:set var="showAddCorrButton" value="${param.showAddCorrButton}"></c:set>
<c:set var="hideMenuLookupID" value="${resultMap.hideMenuLookupID}"></c:set>
<c:set var="showOnlyClose" value="${resultMap.showOnlyClose}"></c:set>
<c:set var="langId" value="${resultMap.langId}"></c:set> 
<c:set var="docIdForSearch" value="${resultMap.docId}"></c:set> 
<c:set var="noOfRecords" value="${resultMap.noOfRecords}"></c:set>
<c:set var="pageNo" value="${resultMap.pageNo}"></c:set>
<c:set var="noOfPage" value="${resultMap.noOfPage}"></c:set>
<c:set var="inboxType" value="${resultMap.inboxType}"></c:set>
<c:set var="unread" value="${resultMap.unread}"></c:set>
<c:set var="documentDesc" value="${resultMap.docDesc}"></c:set>
<c:set var="subjFilter" value="${resultMap.subjectFilter}"></c:set>
<c:set var="transactionNo" value="${resultMap.transNo}"></c:set>
<c:set var="empFirstName" value="${resultMap.empFName}"></c:set>
<c:set var="empMiddleName" value="${resultMap.empMName}"></c:set>
<c:set var="empLastName" value="${resultMap.empLName}"></c:set>
<c:set var="dateTo" value="${resultMap.recieveDateTo}"></c:set>
<c:set var="dateFrom" value="${resultMap.recieveDateFrom}"></c:set>
<c:set var="fileId" value="${resultMap.fileId}"></c:set>
<c:set var="docId" value="${resultMap.docId}"></c:set>
<c:set var="countForNumOfRecordsDisplayed" value="1"></c:set>


<%
int i = 1;
try{
	
	%>

	<script ="text/javascript" src="script/common/tabcontent.js"></script>
	<script type="text/javascript"></script>

<fmt:setBundle basename="resources.WFLables" var="commonLables" scope="request"/>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />


<script language="javascript">


 window.onblur = function() {
 		try
 		{
        docWindow.focus();
        }
        catch(e)
        {
        }
    }


	function openDocument(url)
	{
			var urlstyle="location=0,status=0,scrollbars=1,type=fullWindow,fullscreen"
			//docWindow = window.open (url,"Document","location=0,status=0,scrollbars=1"); 
			if("${showOnlyClose}" == "true")
			{
				docWindow = window.open (url,"DocumentFromAdd",urlstyle); 
			}
			else
			{
				docWindow = window.open (url,"Document",urlstyle); 
			}
			docWindow.resizeTo(screen.availWidth,screen.availHeight)
			docWindow.moveTo(0,0);
	}
	
	function setSelectedCheckboxValue(src,obj,objJobRefId,objFrmPost)
	{
	
		var firstIndex = obj.lastIndexOf("corrId=");	
		
		var lastIndex = obj.lastIndexOf("docId");	

		   
		var selectedId  = obj.substring(eval(firstIndex)+7,obj.lastIndexOf("docId")-1);
		
		//var index = obj.lastIndexOf("=");
		
		//var selectedId = obj.substring(eval(index) + 1);
		
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

var priorityListEng = new Array();
priorityListEng.push('Routine');
priorityListEng.push('Immediate');
priorityListEng.push('Today');
priorityListEng.push('Urgent');
priorityListEng.push('DateSet');

var priorityListGuj = new Array();
priorityListGuj.push('રુટિન');
priorityListGuj.push('તાત્કાલીક');
priorityListGuj.push('આજે');
priorityListGuj.push('અજન્ટ ');
priorityListGuj.push('તારીખ સેટ કરો');
    
var finalPriority = new Array();
var count = 0;    
</script>

<hdiits:form name="frmcsearch" validate="true" method="post" action="./hdiits.htm">
<hdiits:hidden name="searchVal" default="${resultMap.searchVal}"/>
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
	    	System.out.println("cccccccc"+exFlag);
	    	
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
<hdiits:hidden name="docType" default="${resultMap.DocFilterKey}" />
<hdiits:hidden  name="selectedCheckboxex" />
<hdiits:hidden name="lstActPostIds" />
<hdiits:hidden name="selectedCorrespondenceForFile" />
<hdiits:hidden name="jobRefIds" />
<hdiits:hidden name="moduleName" default="${param.moduleName}"/>
<hdiits:hidden name="menuName" default="${param.menuName}"/>
<c:set var="fileIdForSearch" value="forFile"></c:set>
<c:set var="colorAsc" value="rgb(233,233,233)"></c:set>

<c:if test="${(param.fileId != null)}">
	<c:set var="fileIdForSearch" value="${param.fileId}"></c:set>
</c:if>
<c:if test="${(param.fileIdForSearch != null) && (param.fileIdForSearch != '')}">
	<c:set var="fileIdForSearch" value="${param.fileIdForSearch}"></c:set>
</c:if>

<hdiits:hidden name="fileIdForSearch" default="${fileIdForSearch}"/>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="DocList" value="${resultValue.lstDocs}" > </c:set>
<c:set var="fileId" value="${resultValue.fileId}" > </c:set>
<hdiits:hidden name="successFlag" default="${resultValue.Success}"/>
<c:choose>
	<c:when test="${empty showButton && !empty showAddCorrButton}">
		<hdiits:button name="addCorrToFile" type="button" id="addCorrToFile" caption="Add Correspondence To File"  onclick="return getSelectedValue()"/>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${empty showButton}">
				<hdiits:button name="addCorrToFile" type="button" id="addCorrToFile" captionid="WF.AddToFile" bundle="${commonLables}" onclick="javascript:checkfmsaction()"/>
			</c:when>
			<c:otherwise>
				<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
					<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
				</jsp:include>
			</c:otherwise>
		</c:choose>
	</c:otherwise>	
</c:choose>
	
<br>
	<jsp:include flush="true" page="../workflow/commonSearch.jsp">
		<jsp:param name="Subjects" value="1"/>
		<jsp:param name="empFName" value="${empFirstName}"/>
		<jsp:param name="empMName" value="${empMiddleName}"/>
		<jsp:param name="empLName" value="${empLastName}"/>
		<jsp:param name="recieveDateTo" value="${dateTo}"/>
		<jsp:param name="recieveDateFrom" value="${dateFrom}"/>
		<jsp:param name="subjectFilter" value="${subjFilter}"/>
		<jsp:param name="docDesc" value="${documentDesc}"/>
		<jsp:param name="transNo" value="${transactionNo}"/>
		<jsp:param name="fileIdForSearch" value="${fileId}"/>
		<jsp:param name="fromOutBox" value="false"/>
	</jsp:include>
	
		<c:if test="${not empty DocList}">
		<c:forEach items="${DocList}" var="tempLst"> 
			<script type="text/javascript">
				var flag = 1;
				for(var i=0;i<priorityListEng.length;i++)
				{
					if(priorityListEng[i] == '${tempLst.priority}')
					{
						if('${langId}' == 1)
						{
							finalPriority.push(priorityListEng[i]);
						}
						else
						{
							finalPriority.push(priorityListGuj[i]);
						}
						flag = 0;
						break;
					}
				}
				if(flag != 0)
				{
					for(var i=0;i<priorityListGuj.length;i++)
					{
						if(priorityListGuj[i] == '${tempLst.priority}')
						{
							if('${langId}' == 1)
							{
								finalPriority.push(priorityListEng[i]);
							}
							else
							{
								finalPriority.push(priorityListGuj[i]);
							}
							break;
						}
					}				
				}
			</script>
		</c:forEach>	
	</c:if>	
	
<br>
<c:choose>
<c:when test="${noOfRecords eq 0}">
	<c:out value="No Record found to display..."></c:out>
</c:when>
<c:otherwise>
<c:set var="serialNumber" value="${pageNo*10-9}" />
  <table style="width:100%;">
  	<thead align="center">
	<tr class="datatableheader">
		<td><hdiits:caption captionid="Sr_No" bundle="${commonLables}" /></td>
		<td><hdiits:caption captionid="Document_Name" bundle="${commonLables}" /></td>
		<td><hdiits:caption captionid="Ref_Id" bundle="${commonLables}" /></td>
		<td><hdiits:caption captionid="Description" bundle="${commonLables}" /></td>
		<td><hdiits:caption captionid="From_Usr" bundle="${commonLables}" /></td>
		<td><hdiits:caption captionid="WF.RECVDATE" bundle="${commonLables}" /></td>
		<td><hdiits:caption captionid="Location" bundle="${commonLables}" /></td>
		<td><hdiits:caption captionid="Priority" bundle="${commonLables}" /></td>
		<td></td>
  	</tr>
  	</thead>
  	<c:forEach items="${DocList}" var="row" begin="0" end="9" step="1">
	<c:set var="countForNumOfRecordsDisplayed" value="${countForNumOfRecordsDisplayed + 1}"></c:set>
  	<c:if test="${serialNumber lt pageNo*10+1}">
	<c:choose>
		<c:when test="${colorAsc eq 'rgb(233,233,233)'}">
			<c:set var="colorAsc" value="rgb(255,255,255)"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="colorAsc" value="rgb(233,233,233)"></c:set>
		</c:otherwise>
	</c:choose>
		<c:choose>
		<c:when test="${row.newFlag eq 'Y'}">
			<c:set var="jobColor" value="red"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="jobColor" value="black"></c:set>
		</c:otherwise>
	</c:choose>
	<tr id="${serialNumber}" class="tablecelltext" style="background-color: ${colorAsc}; color: ${jobColor};" onmouseover="changeToBlue(this)" onmouseout="changeToOriginal(this)">
		<td align="center">${serialNumber}</td>
		<c:set var="serialNumber" value="${serialNumber+1}" />
		<td>${row.docName}</td>
    	<td><u><a style="cursor: hand" onclick="javascript:openDocument('${row.docUrl}')" >${row.transactionNumber}</a></u></td>
	   	<td>${row.docDesc}</td>
	   	<fmt:formatDate value="${row.createdDate}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="stmtDate"/>
	    <td>${row.fromPostId}</td>
	   	<td>${stmtDate}</td>
		<td>${row.location}</td>
		<td>
			<script>
				document.write(finalPriority[count++]);
			</script>
		</td>
		<td style="background: ${colorVal}">
			<c:choose>
				<c:when test="${empty showButton && !empty showAddCorrButton}">
			    	<hdiits:radio name="SelectCheck" value="${row.docUrl}"/>
			    </c:when>
			    <c:otherwise>
					<hdiits:checkbox name="SelectCheck" value="${row.docUrl}" onclick="javascript:setSelectedCheckboxValue(this,'${row.docUrl}','${row.jobRefId}','${row.lstActPostId}')"/>
				</c:otherwise>
			</c:choose>
		</td>			
	</tr>
	</c:if>
	</c:forEach>
</table>
<br>
<div id="pageDisplay" style="background-color: rgb(223,223,223); border-width: 1px; border-style: dashed; width: 100%">
<b>
<c:out value="${noOfRecords} Records found, displaying ${(pageNo-1)*10+1} to "></c:out>
<script type="text/javascript">
	if('${noOfRecords}' < ('${pageNo}'*10))
		document.write('${noOfRecords}');
	else
		document.write('${pageNo}'*10);
</script>
<c:out value=" Records."></c:out>
</b>
<br>
<c:if test="${noOfPage ne 1}">
<c:choose>
	<c:when test="${pageNo eq 1}">
		<c:out value="[First/Prev]"></c:out>
	</c:when>
	<c:otherwise>
		<c:out value="["></c:out>
		<c:choose>
			<c:when test="${inboxType eq 'file'}">
				<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=3&moduleName=WorkList&menuName=forFileInbox&pageNo=1&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">First</a>
			</c:when>
			<c:otherwise>
				<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=2&moduleName=WorkList&menuName=forCorrespondenceInbox&pageNo=1&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&fileId=${fileId}&docId=${docId}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">First</a>
			</c:otherwise>
		</c:choose>
		<c:out value="/"></c:out>
		<c:choose>
			<c:when test="${inboxType eq 'file'}">
				<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=3&moduleName=WorkList&menuName=forFileInbox&pageNo=${pageNo-1}&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">Prev</a>
			</c:when>
			<c:otherwise>
				<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=2&moduleName=WorkList&menuName=forCorrespondenceInbox&pageNo=${pageNo-1}&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&fileId=${fileId}&docId=${docId}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">Prev</a>
			</c:otherwise>
		</c:choose>
		<c:out value="] "></c:out>
	</c:otherwise>
</c:choose>
</c:if>
<c:set var="startPage" value="1"></c:set>
<c:if test="${noOfPage gt 10 and pageNo gt 5}">
<c:choose>
	<c:when test="${(noOfPage-pageNo) lt 5}">
		<c:set var="startPage" value="${noOfPage-9}"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="startPage" value="${pageNo-4}"></c:set>
	</c:otherwise>
</c:choose>
</c:if>
<c:set var="i" value="${startPage}"></c:set>
<c:forEach begin="${i}" end="${noOfPage}" step="1">
	<c:choose>
	<c:when test="${(i-startPage) lt 10}">
	<c:if test="${i ne startPage}">
		<c:out value=","></c:out>
	</c:if>
	<c:choose>
		<c:when test="${i eq pageNo}">
			<b><c:out value="${i}"></c:out></b>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${inboxType eq 'file'}">
					<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=3&moduleName=WorkList&menuName=forFileInbox&pageNo=${i}&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">${i}</a>
				</c:when>
				<c:otherwise>
					<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=2&moduleName=WorkList&menuName=forCorrespondenceInbox&pageNo=${i}&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&fileId=${fileId}&docId=${docId}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<c:set var="i" value="${i+1}"></c:set>
	</c:when>
	</c:choose>
</c:forEach>
<c:if test="${noOfPage ne 1}">
<c:choose>
	<c:when test="${pageNo eq noOfPage and noOfPage ne 1}">
		<c:out value="[Next/Last]"></c:out>
	</c:when>
	<c:otherwise>
		<c:out value="["></c:out>
		<c:choose>
			<c:when test="${inboxType eq 'file'}">
				<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=3&moduleName=WorkList&menuName=forFileInbox&pageNo=${pageNo+1}&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">Next</a>
			</c:when>
			<c:otherwise>
				<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=2&moduleName=WorkList&menuName=forCorrespondenceInbox&pageNo=${pageNo+1}&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&fileId=${fileId}&docId=${docId}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">Next</a>
			</c:otherwise>
		</c:choose>
		<c:out value="/"></c:out>
		<c:choose>
			<c:when test="${inboxType eq 'file'}">
				<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=3&moduleName=WorkList&menuName=forFileInbox&pageNo=${noOfPage}&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">Last</a>
			</c:when>
			<c:otherwise>
				<a href="hdiits.htm?actionFlag=getDocListOfWorkList&docType=2&moduleName=WorkList&menuName=forCorrespondenceInbox&pageNo=${noOfPage}&empFirstName=${empFirstName}&empMiddleName=${empMiddleName}&empLastName=${empLastName}&dateTo=${dateTo}&dateFrom=${dateFrom}&subjFilter=${subjFilter}&documentDesc=${documentDesc}&transactionNo=${transactionNo}&fileId=${fileId}&docId=${docId}&searchVal=${resultMap.searchVal}&unread=${resultMap.unread}">Last</a>
			</c:otherwise>
		</c:choose>
		<c:out value="] "></c:out>
	</c:otherwise>
</c:choose>
</c:if>
</div>
</c:otherwise>
</c:choose>
<table border="0">
	<tr>
		<td>
			<b><legend><fmt:message key="WF.Legend" bundle="${fmsLables}"></fmt:message></legend></b>
		</td>
		<td bgcolor="red" width="7%">
		</td>
		<td>
			New File
		</td>
		<td bgcolor="black" width="7%">
		</td>
		<td>
			Previously Seen Files
		</td>
	</tr>
</table>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'></hdiits:validate>
<center><hdiits:button name="Close" captionid="WF.CLOSE" bundle="${commonLables}" type="button" onclick="javascript:self.close();parent.focus();" /></center>
</hdiits:form>

<%}catch(Exception e){
	e.printStackTrace();
}%>

<script language="javascript">

	var original;
	function changeToBlue(obj)
	{
		original = document.getElementById(obj.id).style.backgroundColor;
		document.getElementById(obj.id).style.backgroundColor='rgb(200,200,255)';
	}
	
	function changeToOriginal(obj)
	{
		document.getElementById(obj.id).style.backgroundColor=original;
	}

function checkfmsaction()
	{
	if(document.forms[0].selectedCheckboxex.value == "")
	{
	
		alert('<fmt:message key="WF.SelOneCorrAlert" bundle="${commonLables}"></fmt:message>');
		return false;
	}
	
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
		
			   var fileId='${resultValue.fileId}';
			   var corrList=document.forms[0].selectedCheckboxex.value;
 		       var url = "${contextPath}/hdiits.htm?actionFlag=FMS_ADDCORRINTOFILE&actionId=7&fileId="+fileId+"&corrIdList="+corrList;
		       
		
		        xmlHttp.onreadystatechange = function()
				{
					
					if (xmlHttp.readyState == 4) 
					{     
						if (xmlHttp.status == 200) 
						{
							if(xmlHttp.responseXML.documentElement!=null)
							{
										var XMLDoc=xmlHttp.responseXML.documentElement;
										var FmsActionDetail = XMLDoc.getElementsByTagName('FmsActionDetail');
										
										if(FmsActionDetail.length != 0)
										{					
											ProceedFmsAction=FmsActionDetail[0].childNodes[0].text;
											
											if(ProceedFmsAction=='Yes')
											{
												getSelectedValue();
											}
											else
											{
												FmsAlertMsg=FmsActionDetail[0].childNodes[1].text;
												alert(FmsAlertMsg);
												return false;
											}
										}
										
							 }	     		
			     		}
					}
						    	
			    }
			    
		        xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));
				return true;
	}
	function getSelectedValue()
	{
			try
			{
			var flag = false;
			var counter = 0;
			var fileIdToAddCorr ;
			if(document.forms[0].SelectCheck != null)
			{

				var elementLength = 1;
				if("1" == "${countForNumOfRecordsDisplayed}")
					elementLength = 2;
				else
					elementLength = "${countForNumOfRecordsDisplayed}";
				for(var len=0; len < eval(elementLength)-1; len++)
				{
					if(document.forms[0].SelectCheck[len] != null)
					{
						if(document.forms[0].SelectCheck[len].checked == true)
						{
							flag = true;
			
							fileIdToAddCorr = setFileIdToAdd(document.forms[0].SelectCheck[len].value);
			
							/*counter  = eval(counter) + 1;
							document.forms[0].selectedCorrespondenceForFile.value = document.forms[0].SelectCheck[len].value;
							if(eval(counter) > 1) {
								alert("Please select only one correspondence");
								return false;
							}*/
								
						}
					}
					else 
					{
					
						if(document.forms[0].SelectCheck.checked == true)
						{
							flag = true;
						}
					}
				}
			}
			else
				flag = true;
				
				 
			if(flag == false && ("${showAddCorrButton}" != "true"))
			{
				alert('<fmt:message key="WF.SelOneCorrAlert" bundle="${commonLables}"></fmt:message>');
				return false;
			}
			else if(flag == false && ("${showAddCorrButton}" == "true"))
			{
				
				alert('<fmt:message key="WF.SelOneFileAlert" bundle="${commonLables}"></fmt:message>');
				return false;
			}
			
			var index = document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("=");
			var selectedId = document.forms[0].selectedCorrespondenceForFile.value.substring(eval(index) + 1);			

			if(("${showAddCorrButton}" == "true") && ("${showButton}" == ""))
			{
				document.getElementById("selectedCheckboxex").value = window.opener.document.forms[0].selectedCheckboxex.value;
				document.forms[0].jobRefIds.value = window.opener.document.forms[0].jobRefIds.value;
				document.forms[0].lstActPostIds.value = window.opener.document.forms[0].lstActPostIds.value;
				document.getElementById("frmcsearch").action="${contextPath}/hdiits.htm?actionFlag=addCorrinFile&fileId="+fileIdToAddCorr+"&showAddCorr=true";
				document.getElementById("frmcsearch").submit();
			}
			else
			{
				document.getElementById("frmcsearch").action="${contextPath}/hdiits.htm?actionFlag=addCorrinFile&fileId=${fileId}&showAddCorr=true"
				document.getElementById("frmcsearch").submit();
			}
			return true;
			}
			catch(e)
			{
				alert(e);
				return false;
			}
	}
	
	function createFile()
	{
			var selectCheckArrayLength =1;
			if("1" == "${countForNumOfRecordsDisplayed}")
					selectCheckArrayLength = 2;
				else
					selectCheckArrayLength = "${countForNumOfRecordsDisplayed}";
					
			if((eval(selectCheckArrayLength) <= 2) && (document.forms[0].SelectCheck != null)  && document.forms[0].SelectCheck.checked)
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
			
			
			var firstIndex = document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("corrId=");	
		
			var lastIndex = document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("&");	

		   
		    //var selectedId  = document.forms[0].selectedCorrespondenceForFile.value.substring(eval(firstIndex)+7);
			var selectedId  = document.forms[0].selectedCorrespondenceForFile.value.substring(eval(firstIndex)+7,document.forms[0].selectedCorrespondenceForFile.value.lastIndexOf("&"));

		    if(selectedId.indexOf("fileId") != -1)
		    {
		    	alert('<fmt:message key="WF.OnlyOneCorrCrtFile" bundle="${commonLables}"></fmt:message>');
		    	return false;
		    }
		    
		     var urlStyle ='toolbar=no,menubar=no,location=no,top=150,left=200,width=400,height=400,scrollbars=yes';		   
		    if(document.forms[0].selectedCorrespondenceForFile.value.indexOf("fileId") == -1)
			{
				//displayModalDialog('${contextPath}/hdiits.htm?actionFlag=loadUnit&createdFromInbox=yes&corrId='+selectedId, 'test', urlStyle) ;
				window.open('${contextPath}/hdiits.htm?actionFlag=loadUnit&createdFromInbox=yes&corrId='+selectedId+'&sendBackTo=File','worklist',urlStyle);	
			}			
			else
			{
				//displayModalDialog('${contextPath}/hdiits.htm?actionFlag=loadUnit&createdFromInbox=yes','test',urlStyle) ;
				window.open('${contextPath}/hdiits.htm?actionFlag=loadUnit&createdFromInbox=yes','worklist',urlStyle);	
			}
			window.close();	
	}
	window.onload=test
	function test()
	{
		if(document.getElementById('successFlag').value=="True")
		{	
				window.opener.document.getElementById('addtocorrflag').value="yes";
				//window.opener.frames['workflow_tab'].document.getElementById('tab2').onclick();	
				window.opener.frames['Target_frame'].document.forms[0].action="hdiits.htm?actionFlag=showaddedCorrespondence&corrCriteria=Incoming&fileId=${fileId}";
				window.opener.frames['Target_frame'].document.forms[0].submit();
				document.location = window.opener.frames['workflow_tab'].document.getElementById("tab2_Link");
				window.close();
		}
	}
	
	function getSelectedValue11()
	{
		var isCorrSelected = false;
		
		if(document.forms[0].SelectCheck.length == undefined && document.forms[0].SelectCheck.checked)
		{
			isCorrSelected = true;
		}
		for(var i=0;i<document.forms[0].SelectCheck.length;i++)
		{
			if(document.forms[0].SelectCheck[i].checked)
			{
				isCorrSelected = true;
			}
		}
		if(isCorrSelected)
		{
			var urlStyle ='width=400,height=400,toolbar=no,menubar=no,location=no,top=150,left=200';
			window.open('hdiits.htm?actionFlag=getFilteredfWorkList&docType=3&showAddCorrButton=true','addToFile');
			return true;
		}
		else
		{
			
			alert('<fmt:message key="WF.OnlyOneCorrAddFile" bundle="${commonLables}"></fmt:message>');
			return false;
		}
	}
	
	function setFileIdToAdd(obj)
	{
		var firstIndex = obj.lastIndexOf("fileId=");	
		var lastIndex = obj.lastIndexOf("&");	
		var selectedId  = obj.substring(eval(firstIndex)+7,obj.lastIndexOf("&"));
		return  selectedId;
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

	function forAlertToUserForWorkFlow()
	{
		if(document.getElementById("InboxDiv") != null)
		{
			homepageajaxfunction("Inbox");
		}
		else if(document.getElementById("dataFrame") != null)
		{
			var dynFrameSrc = document.getElementById("dataFrame").src; 
			document.getElementById("dataFrame").src = dynFrameSrc;
		}
	}
</script>