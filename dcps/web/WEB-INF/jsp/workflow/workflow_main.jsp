 <%
	try
	{
		
%>
<script language="javascript">
	var whichLinkClicked=true;   // variable declared for checking if basic info tab clicked or not
	var allNotings = new Array();
	var tab_arr=new Array();
	var isChoice = 0;
	var forSWRCloseRequest = false;
</script>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.tcs.sgv.fms.valueobject.FmsFileNotings"%>

<link rel="stylesheet" href="<c:url value="/themes/hdiits/mainNavigation.css"/>" type="text/css" >
<link rel="stylesheet" href="<c:url value="/themes/hdiits/navigationSupport.css"/>" type="text/css" >
<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>"></script>
<script type="text/javascript" src="script/common/mainNavJs.js"></script>
<script type="text/javascript" src="script/common/partial.js"></script>
<script type="text/javascript" src="script/common/iframesplit.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/treeScript/wz_tooltip.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<fmt:formatDate value="${resultMap.current_date}" pattern="dd/MM/yyyy" dateStyle="medium" var="currentDateJs"/>
<c:set var="users" value="${resultMap.OrgUsers}"></c:set>
<c:set var="Notings" value="${resultMap.Notings}"></c:set>
<c:set var="AttachmentNameList" value="${resultMap.AttachmentNames}"></c:set>
<c:set var="fmsFileVo" value="${resultMap.FmsFile}"></c:set>
<c:set var="docId" value="${resultMap.docId}" scope="session"></c:set>
<c:set var="filestagelist" value="${resultMap.filestagelist}"></c:set>
<c:set var="filestatuslist" value="${resultMap.filestatuslist}"></c:set>
<c:set var="Prioritylist" value="${resultMap.Prioritylist}"></c:set>
<c:set var="Confidentialitylist" value="${resultMap.Confidentialitylist}"></c:set>
<c:set var="AttachmentNameList" value="${resultMap.AttachmentNames}"></c:set>
<c:set var="aclElementMstList" value="${resultMap.aclElementMstList}" scope="session"></c:set>
<c:set var="noOfLevelsInMenu" value="${resultMap.noOfLevelsInMenu}" scope="session"></c:set>
<c:set var="winName" value="${resultMap.winName}"></c:set>
<c:set var="tabclickcnt" value="${resultMap.tabclickcnt}"></c:set>
<c:set var="viewInfoTableflag" value="${resultMap.viewInfoTableflag }"></c:set>
<c:set var="fileId" value="${resultMap.fileId }" scope="session"></c:set>
<c:set var="disableflag" value="${resultMap.disableflag}"></c:set>
<c:set var="hideMenuLookupID" value="${resultMap.hideMenuLookupID}"></c:set>
<c:set var="fileNo" value="${resultMap.fileNo}"></c:set>
<c:set var="lSelFileTablst" value="${resultMap.lSelFileTablst}"></c:set>
<c:set var="sendBackTo" value="${resultMap.sendBackTo}"></c:set>
<c:set var="fromOutBox" value="${resultMap.fromOutbox}"></c:set>
<c:set var="fromDispose" value="${resultMap.fromDispose}"></c:set>
<c:set var="saveNoting" value="${resultMap.saveNoting}"></c:set>
<c:set var="fromSave" value="${resultMap.fromSaveButton}"></c:set>
<c:set var="showRichtext" value="${resultMap.showRichtext}"></c:set>
<c:set var="departmentId" value="${resultMap.deptId}"></c:set>
<c:set var="showParallelNotings" value="${resultMap.parallelFlag}"></c:set>
<c:set var="fileDisposeFlag" value="${resultMap.fileDisposeFlag}"></c:set>

<c:forEach items="${lSelFileTablst}" var="TabNO" >
			<script>		
			tab_arr.push('${TabNO}');
			</script>					
							
							
</c:forEach>


<fmt:formatDate value="${fmsFileVo.dueDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="formateduedate"/>
<fmt:formatDate value="${fmsFileVo.dueDate}" pattern="MM/dd/yyyy" dateStyle="medium" var="formateddate"/>
<fmt:formatDate value="${resultMap.createdDate}" pattern="MM/dd/yyyy" dateStyle="medium" var="createddate"/>

<script type="text/javascript" src="script/common/base64.js"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<%
ResultObject result=(ResultObject)request.getAttribute("result");
Map resultMap=(Map)result.getResultValue();
ArrayList tablinkURLArls = (ArrayList) resultMap.get("tabUrlArls");
List notingsList=(List)resultMap.get("notingsList");
List displayNotingList=(List)resultMap.get("displayNotingList");
session.setAttribute("displayNotingList",displayNotingList);
 String langId=resultMap.get("landId").toString();
session.setAttribute("langId",langId);
session.setAttribute("serviceLocator",resultMap.get("serviceLocator"));
request.setAttribute("notingsList",notingsList);
ArrayList users = (ArrayList) resultMap.get("OrgUsers");
int notecnt=users.size()+1;
List date = new ArrayList((Set) resultMap.get("CrtDate"));
List listPara = (List) resultMap.get("listPara");

String fileId = resultMap.get("fileId").toString();
//int temp_newVal = 0;

int counter_div=1; 
int counter = 0; 
int notingCounter=0; 
int paraCounter=0; 
int note_type_cnt=0,usercount=0,datecounter=0,richTextCounter=1,attachmentCounter=1;
String newValue="DocumentAttachment"+attachmentCounter++,user=null;boolean flag=false;
%>
<Script Language=VBScript>

Function vbMsg(isTxt)

testVal = MsgBox(isTxt,3)
isChoice = testVal

End Function
</script>
<script type="text/javascript">

		

showProgressbar();
var headerinfo='${viewInfoTableflag}'
var tabtoclick='${tabclickcnt}'
var intervalid,tabinterval;
var t1=new Date();
var duedate=t1.getDate()  + "/" + eval(t1.getMonth()+1) + "/" + t1.getFullYear() ;
var urlstyle = 'width=500,height=300,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
var expandflag="false";
var expandflag2="false";

function sendFileAsParallel()
{
	window.open("hdiits.htm?viewName=wf-ParallelFileEmpSelect&fileId=${param.fileId}&docId=${param.docId}", '', "location=0,status=0,scrollbars=1,height=600");
}

function sendToPreferredList()
{
	var urlstyle = "location=0,status=0,scrollbars=1,width=600,height=600";
	var url = "hdiits.htm?actionFlag=populatePrfdLst&sendToPreferredList=true";
	window.open(url, '', urlstyle);
}

//Code for adding the outward link for inserting the file no:
function openOutward()
{
	var fileNo="${fileNo}";
	var File="File";
	var url = "${contextPath}/hdiits.htm?actionFlag=loadFileCategoryDtls&txtFileNo="+fileNo+"&Sender="+File;	
	window.open(url, '', 'status=no,toolbar=no,scrollbars=yes,width=1500,height=700');			
}



function setTabLinkUrl()
{

		<%
		   
			String url_part="";
	
			for(int len=1; len<=tablinkURLArls.size();len++) {
				
				url_part = "tab" + len + "_Link";
				
				%>
				try
				{
				window.frames['workflow_tab'].document.getElementById('<%=url_part%>').href="<%=tablinkURLArls.get(len-1).toString()%>";
				}
				catch(e)
				{
				}
				<%
			}
		%>	
		window.clearInterval(tabinterval);	
}	

var flag="true";
function closeParent()
{
	if("${resultMap.dashBoardMenu}" != "")
	{
	self.close();
	return;
	}
	if((forSWRCloseRequest) || ("${fromOutBox}" == "true"))
	{
		if(window.opener.parent.document.getElementById("dataFrame") != null){
			window.opener.document.forms[0].action = "hdiits.htm?actionFlag=getDocListOfWorkList&moduleName=WorkList&menuName=forWorkList";
			window.opener.document.forms[0].submit();
			window.close();
			return;
		}else{
			self.close();
			return ;
		}
	}
	
	var fromCommonHomePage="${resultMap.fromCommonHomePage}";
	vbMsg('<fmt:message key="WF.SaveMsg" bundle="${fmsLables}"></fmt:message>')
	if(isChoice==6)
	{
		try{
				if(submitForm(''))
				{
					if(fromCommonHomePage=="yes")
					{
						parent.focus();
						self.close();
					}
					else
					{
						window.opener.document.forms[0].action = "hdiits.htm?actionFlag=getDocListOfWorkList&moduleName=WorkList&menuName=forWorkList";
						window.opener.document.forms[0].submit();
						window.close();
					}
				}
			}catch(e){
					window.parent.opener.document.forms[0].action = "hdiits.htm?actionFlag=getDocListOfWorkList&moduleName=WorkList&menuName=forWorkList";
					window.parent.opener.document.forms[0].submit();
					window.close();						
			}	
	}
	else if(isChoice==7)
	{
			try{
				if(fromCommonHomePage=="yes")
					{
						parent.focus();
						self.close();
					}
					else
					{
						window.opener.document.forms[0].action = "hdiits.htm?actionFlag=getDocListOfWorkList&moduleName=WorkList&menuName=forWorkList";
						window.opener.document.forms[0].submit();
						window.close();	
					}
				}catch(e)
				{	
					
					var wfwinname='${winName}';
					win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forWorkList",wfwinname);				
					win.focus();
					self.close();	
					
				}
	}
		
}
function Expand() {
   divs=document.getElementsByTagName("DIV");
   for (i=0;i<divs.length;i++) {
    divs[i].style.display="block";  
    key=document.getElementById("x" + divs[i].id);

   }
}


function Collapse() {
   divs=document.getElementsByTagName("DIV");
   for (i=0;i<divs.length;i++) {
    divs[i].style.display="none";   
     key=document.getElementById("x" + divs[i].id);

   }
}
function toggle(item,imgobj)
{	
	var imglast=imgobj.src.substr(imgobj.src.lastIndexOf("/")+1,imgobj.length);	
	if(imglast!="collapse.gif")
	{		
		imgobj.src="themes/defaulttheme/images/workflow/collapse.gif"				
	}
	else
	{		
		imgobj.src="themes/defaulttheme/images/workflow/expand.gif" 		
 	}	
	arguments.length=1
	if (arguments.length == 1)
    {
    	
   		obj=document.getElementById(item);
   		visible=(obj.style.display!="none"); 
   		//visible=(obj.style.display=="none");   
   		key=document.getElementById("x" + item);   
  		 if (visible) {
    	 obj.style.display="none";
   		 } 
   		 else {  
      		obj.style.display="block";
  		 }
     }
   else
   {
    
   		for (var i = 1; i < arguments.length; i ++)
   		{
   			obj=document.getElementById(arguments[i]);
    		visible=(obj.style.display!="none");
    		if (!visible)
   			toggle(arguments[i]);
  		 }
   }
  
}
function replace(string,text,by) {
// Replaces text with by in string
    var strLength = string.length, txtLength = text.length;
    if ((strLength == 0) || (txtLength == 0)) return string;

    var i = string.indexOf(text);
    if ((!i) && (text != string.substring(0,txtLength))) return string;
    if (i == -1) return string;

    var newstr = string.substring(0,i) + by;

    if (i+txtLength < strLength)
        newstr += replace(string.substring(i+txtLength,strLength),text,by);

    return newstr;
}


function clearHtmlValue(){
	document.getElementById("Converted_text").value = '';
	document.menuform.elements['note_type'].value="MN"
	var tillcount=document.menuform.elements['conutMn'].value
	var counter=0;	
	
	while(counter<tillcount){
		var str=document.menuform.elements['rte'+eval(counter+1)].value 
		var converted_text=encodeBase64(str);
		var converted_text = replace(converted_text,'\n',' ');  
	  	document.menuform.elements['rte'+eval(counter+1)].value=converted_text;
	  	counter++;
	}
}

function submitForm(src)
{	
	
	document.forms[0].RichTextVals.value = ''
	document.getElementById("Converted_text").value = '';	
	if(document.getElementById('infotable').style.display=="none")
		document.getElementById('viewInfoTableflag').value="false";
	else
		document.getElementById('viewInfoTableflag').value="true";
	
	var date1=document.getElementById('txtsetDate').value
		
		if(date1 != null && date1 != '')
		{
						var currentDate = document.getElementById('currentDate').value ;
						var result = compareDate(currentDate,date1);
						if(result < 0)
						{
							alert("Please select date which is not earlier then today's date");
							document.forms[0].txtsetDate.focus();
							return false;
						}
						
		}	
	
	
	if(src=='next')	
	{
		if(document.menuform.elements['rte1'].value=="")
		{
		alert("please write the noting");	
	    return false;
		}
		else
		{
				var action;					
				action="${contextPath}/hdiits.htm?actionFlag=saveNextDetail1&moduleName=WorkList&menuName=forFile"
				document.menuform.elements['note_type'].value="MN"
				var tillcount=document.menuform.elements['conutMn'].value
				var counter=0;	
				while(counter<tillcount){
					var str=document.menuform.elements['rte'+eval(counter+1)].value 
					var converted_text=encodeBase64(str);
					var converted_text = replace(converted_text,'\n',' ');  
				  	document.menuform.elements['rte'+eval(counter+1)].value=converted_text;
				  	counter++;
				}	

				document.getElementById("fromSaveButton").value='no';	
				document.getElementById("menuform").method="post";
				document.getElementById("menuform").action=action;	
				document.getElementById("Converted_text").value = '';	
				document.getElementById("menuform").submit();
				return true;
			}			
	}
	else
	{	
			if(src=="save")
			{
			
			document.getElementById("fromSaveButton").value='yes';
			}
			if(whichLinkClicked) {

				if(parent.frames['Target_frame'].document.forms[0]!=null)
				{
					var formname=parent.frames['Target_frame'].document.forms[0].name;
					//parent.frames['Target_frame'].document.forms[0].wffileId_hidden.value = '<%=fileId%>';
				    if(eval("parent.document.getElementById('Target_frame').contentWindow.validateForm_"+formname+"()")){

						var action;	

						if(("Inbox" == "${sendBackTo}") && (src.toLowerCase() != "Save".toLowerCase()))
						{
							action="${contextPath}/hdiits.htm?actionFlag=saveNextDetail1&moduleName=WorkList&menuName=forWorkList&sendBackTo=${sendBackTo}";
						}
						else
						{
							action="${contextPath}/hdiits.htm?actionFlag=saveNextDetail1&moduleName=WorkList&menuName=forFile&sendBackTo=${sendBackTo}";
						}

						document.menuform.elements['note_type'].value="MN"
						var tillcount=document.menuform.elements['conutMn'].value
						var counter=0;	
						while(counter<(tillcount)){
							
							var str=document.menuform.elements['rte'+eval(counter+1)].value 
							var converted_text=encodeBase64(str);
							var converted_text = replace(converted_text,'\n',' ');  
						  	document.menuform.elements['rte'+eval(counter+1)].value=converted_text;
						  	counter++;
						}

					
						window.frames['Target_frame'].document.forms[0].submit();
						document.getElementById("menuform").method="post";
						document.getElementById("menuform").action=action;	
						document.getElementById("Converted_text").value = '';					
						document.getElementById("menuform").submit();
						return true;
					}	
				}// if for checking whether form is exist or not
				else
				{
					var action;	
						if("Inbox" == "${sendBackTo}")
						{
							action="${contextPath}/hdiits.htm?actionFlag=saveNextDetail1&moduleName=WorkList&menuName=forWorkList&sendBackTo=${sendBackTo}";
						}
						else
						{
							action="${contextPath}/hdiits.htm?actionFlag=saveNextDetail1&moduleName=WorkList&menuName=forFile&sendBackTo=${sendBackTo}"
						}

						document.menuform.elements['note_type'].value="MN"
						var tillcount=document.menuform.elements['conutMn'].value
						var counter=0;	
						while(counter<(tillcount)){
							
							var str=document.menuform.elements['rte'+eval(counter+1)].value 
							var converted_text=encodeBase64(str);
							var converted_text = replace(converted_text,'\n',' ');  
						  	document.menuform.elements['rte'+eval(counter+1)].value=converted_text;
						  	counter++;
						}
						
					
						document.getElementById("menuform").method="post";
						document.getElementById("menuform").action=action;	
						document.getElementById("Converted_text").value = '';					
						document.getElementById("menuform").submit();
						return true;
				}	
			}// end of basic info click checking
			else {
				
					var action;	
					if("Inbox" == "${sendBackTo}")
					{
						action="${contextPath}/hdiits.htm?actionFlag=saveNextDetail1&moduleName=WorkList&menuName=forWorkList&sendBackTo=${sendBackTo}";
					}
					else
					{
						action="${contextPath}/hdiits.htm?actionFlag=saveNextDetail1&moduleName=WorkList&menuName=forFile&sendBackTo=${sendBackTo}"	
					}

					document.menuform.elements['note_type'].value="MN"
					var tillcount=document.menuform.elements['conutMn'].value
					var counter=0;	
					while(counter<(tillcount)){
						var str=document.menuform.elements['rte'+eval(counter+1)].value 
						
						var converted_text=encodeBase64(str);
						var converted_text = replace(converted_text,'\n',' ');  
					
					  	document.menuform.elements['rte'+eval(counter+1)].value=converted_text;
					  	counter++;
					}
					
				
					document.getElementById("menuform").method="post";
					document.getElementById("menuform").action=action;	
					document.getElementById("Converted_text").value = '';					
					document.getElementById("menuform").submit();
					return true;
			}	
			
	}		
}


function holdGroupJob(){
	var action="${contextPath}/hdiits.htm?actionFlag=holdGroupJob";
	document.getElementById("Converted_text").value = '';		
	document.getElementById("menuform").method="post";
	document.getElementById("menuform").action=action;					
	document.getElementById("menuform").submit();	
}


function releaseGroupJob(){
	document.getElementById('wfAction').value='releaseGroupJob';	
	document.getElementById("Converted_text").value = '';	
	submitForm('');
}


function assingItemTogroup(){
	document.getElementById('wfAction').value='AssignItemToGroup';	
	document.getElementById("Converted_text").value = '';	
	submitForm('');
}

function forwardjob()
{
	var urlstyle = 'width=650,height=250,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
	document.getElementById("isMark").value="no";
	document.getElementById("Converted_text").value = '';
	document.getElementById("markedList").value='';
	var fileId = document.getElementById('fileId').value;
	var urlStyle ='width=500,height=450,toolbar=no,menubar=no,location=no,top=150,left=200'; 
	window.open('hdiits.htm?actionFlag=getNextNode&action=forward&isMark=no&fileId='+fileId+'&sendBackTo=${sendBackTo}','wfaction_window_file',urlstyle);
}

function forward()
{
	
	document.getElementById('wfAction').value='forward';	
	document.getElementById("Converted_text").value = '';	
	submitForm('');		
}

function returnjob()
{	
	var urlstyle = 'width=650,height=250,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
	var fileId = document.getElementById('fileId').value;
		document.getElementById("Converted_text").value = '';	
	window.open('hdiits.htm?actionFlag=getNextNode&action=return&&fileId='+fileId,'wfaction_window_file',urlstyle);	
}

function returnDoc()
{
	document.getElementById('wfAction').value='Return';
	document.getElementById("Converted_text").value = '';	
	submitForm('');
}
function returndownjob()
{	
	var urlstyle = 'width=650,height=250,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
	document.getElementById("Converted_text").value = '';	
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=getNextNode&action=returnDown&&fileId='+fileId,'wfaction_window_file',urlstyle);	
}

function returnDownDoc()
{
	document.getElementById("Converted_text").value = '';	
	document.getElementById('wfAction').value='ReturnDown';
	submitForm('');
	
}

function Approvejob()
{
	document.getElementById("Converted_text").value = '';	
	document.getElementById('wfAction').value='Approve';
	var urlStyle ='width=500,height=350,toolbar=no,menubar=no,location=no,top=150,left=200'; 
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=file&approvFlag=1&fileId='+fileId,'test',urlStyle);
				
}
function showmenu(elmnt)
{
	document.getElementById(elmnt).style.visibility="visible"
}
function inboxOpen()
{
		window.open('hdiits.htm?actionFlag=getDocListOfWorkList&fileId=<%=fileId%>&docType=2&showOnlyClose=true&docId=${docId}','','status=no,toolbar=no,scrollbars=yes,width=1500,height=700');
}
function hidemenu(elmnt)
{
document.getElementById(elmnt).style.visibility="hidden"
}
function show()
{
document.getElementById('wfAction').value="forward";
var fileId = document.getElementById('fileId').value;
var urlstyle_withinhrchy ='toolbar=no,status=yes,menubar=no,location=no,width=800,height=600,scrollbars=yes'; 
window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=file&fordisposeFlag=yes&fileId='+fileId,'wfaction_window_file',urlstyle_withinhrchy);
}

function expand_onclick()
{
  if(expandflag=="false"){
  		document.getElementById('t2').style.display='none';
   	 	document.getElementById('t1').width="100%";
    	document.getElementById('expandbutton').value='<fmt:message key="WF.Collapse" bundle="${fmsLables}"></fmt:message>';
    	expandflag="true";
    }
    else{   
    		document.getElementById('t2').style.display=''; 	
     		document.getElementById('t1').width="20%";
    		document.getElementById('expandbutton').value='<fmt:message key="WF.Expand" bundle="${fmsLables}"></fmt:message>';
    		expandflag="false";
    }		
}

function expand_onclick2()
{
  if(expandflag2=="false"){
  		document.getElementById('t1').style.display='none';
   	 	document.getElementById('t2').width="100%";
    	document.getElementById('expandbutton2').value='<fmt:message key="WF.Collapse" bundle="${fmsLables}"></fmt:message>';
    	expandflag2="true";
    }
    else{    	
     		document.getElementById('t1').style.display='';
     		document.getElementById('t2').width="80%";
    		document.getElementById('expandbutton2').value='<fmt:message key="WF.Expand" bundle="${fmsLables}"></fmt:message>';
    		expandflag2="false";
    }		
}

function priority_onchange()
{
	var x = document.getElementById('PriorityId');
	var days
	var lookupId = x.options[x.selectedIndex].title;
	days = lookupId.substring(0,lookupId.indexOf("#"));
	var priorityvalue = lookupId.substring(parseInt(lookupId.indexOf("#")) + 1);
	
	
	if(priorityvalue=="DateSet"){

			document.getElementById('duedateid2').style.display='';
			document.getElementById('duedateid1').style.display="none";
	}
	else{
			
			
			document.getElementById('duedateid1').style.display='';
			document.getElementById('duedateid2').style.display="none";
	
	}
		
	
	if(priorityvalue!="DateSet")
	{
			
						var t2=new Date('${createddate}');
						t2.setDate(t2.getDate()+parseInt(days));
						if(parseInt(t2.getDate()) < 10)
						{
							duedate="0"+t2.getDate();
						}
						else
							duedate=t2.getDate();
						duedate = duedate+ "/";
						if((parseInt(t2.getMonth())+1) < 10)
						{
							duedate=duedate+"0"+eval(t2.getMonth()+1);
						}  
						else
							duedate=duedate+eval(t2.getMonth()+1);
							
						duedate = duedate+ "/" + t2.getFullYear() ;
						 		
						var date1=duedate;
						var currentDate = document.getElementById('currentDate').value ;
						var result = compareDate(currentDate,date1);
						
						
						if(priorityvalue=="Today")
						{			
								document.getElementById('txtduedateid').value=currentDate;
								return true;
						}
						else
						{
						
								if(result < 0)
								{
										alert("Duedate must be greater than current date");
										//document.forms[0].txtsetDate.focus();
										return false;
								}
								else
								{
										document.getElementById('txtduedateid').value=duedate;
										return true;
								}	
							
						}		
					
	}	
	else
	{
		document.getElementById('txtsetDate').value='${formateduedate}';
	}	

	
}

/*function priority_onchange()
{
	var x = document.getElementById('PriorityId');
	var priorityvalue=x.options[x.selectedIndex].text;
	t1=new Date();
	var days=x.options[x.selectedIndex].value;
	t1.setDate(t1.getDate()+parseInt(days));
	duedate=t1.getDate()  + "/" + eval(t1.getMonth()+1) + "/" + t1.getFullYear() ;
	document.getElementById('txtduedateid').value=duedate;

	if(priorityvalue=="DateSet"){

			document.getElementById('duedateid2').style.display='';
			document.getElementById('duedateid1').style.display="none";
	}
	else{
			
			document.getElementById('duedateid1').style.display='';
			document.getElementById('duedateid2').style.display="none";
	
	}
}*/


function CreateCoorespondence()
{
	var urlStyle = 'toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen';
	window.open('hdiits.htm?actionFlag=initiateCorrespondence&fileId='+document.getElementById('fileId').value,"",urlStyle);
}
function showSendToSubmenu()
{
	document.getElementById('Within_link').style.display='';
	//document.getElementById('Parallel_link').style.display='';
	
}
function showFileActionSubmenu()
{
	document.getElementById('forward_link').style.display='';
	document.getElementById('return_link').style.display='';
	document.getElementById('approve_link').style.display='';
	document.getElementById('return_link1').style.display='';
}
function showCorrespodencSubmenu()
{
	document.getElementById('create_link').style.display='';
	document.getElementById('add_link').style.display='';	
	
}
function sendToAnyOne()
{
	//var urlStyle_depsrch ='toolbar=no,status=yes,menubar=no,location=no,width=800,height=600,scrollbars=yes,top=0,left=0'; 	
	//window.open('hdiits.htm?actionFlag=DeptSearchData','',urlStyle_depsrch);
	
		//var urlStyle_depsrch ='toolbar=no,status=yes,menubar=no,location=no,width=screen.width,height=screen.height,scrollbars=yes,top=0,left=0';
	document.getElementById('wfAction').value="forward";
	checkfmsaction(11);
	var retval=document.getElementById('ProceedFmsAction').value;

	var loginlocId = document.getElementById('loginLocId').value;
	if(retval=="yes")
	{
		var urlstyle="location=0,status=0,scrollbars=1,type=fullWindow,fullscreen";
		//var SendAsCorr=document.getElementById('SendAsCorr').value;
		var fileId=document.getElementById('fileId').value;
		//var methodFlag=document.getElementById('methodFlag').value;
		
		//s.push('<a href="'+node.target+'">');
		var urlLoc ='hdiits.htm?viewName=wf-locationBranchSrch&deptId=${departmentId}&SendAsCorr=no&methodFlag=forward&fileId="'+fileId+'"&loginLocId='+loginlocId;
		//alert(urlLoc);
		var docWindow = window.open(urlLoc,'',urlstyle);
		docWindow.resizeTo(screen.availWidth,screen.availHeight)
		docWindow.moveTo(0,0); 
	}
	
		
}
function viewGraphicalPendency()
{
	var fileId = document.getElementById('fileId').value;
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0'; 
	displayModalDialog('hdiits.htm?actionFlag=viewPendencyDetails&fileId='+fileId+'&penType=1','',urlStyle);
	
}
function viewTabularPendency()
{
	var fileId = document.getElementById('fileId').value;
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0'; 
	window.open('hdiits.htm?actionFlag=viewPendencyDetails&fileId='+fileId+'&penType=2','',urlStyle);
}
function showPendencySubmenu()
{
	document.getElementById('pen_graphical_link').style.display='';
	document.getElementById('pen_tab_link').style.display='';
}
function checkfmsaction(actionId)
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
		
		       fileId=document.getElementById('fileId').value;
		       var url = "${contextPath}/hdiits.htm?actionFlag=FMS_ADDCORRINTOFILE&actionId="+actionId+"&fileId="+fileId;
		       
			
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
												document.getElementById('ProceedFmsAction').value="yes"
												return true;
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
				
}
function status_onchange()
{
	
	
	var x = document.getElementById('status');
	var status=x.options[x.selectedIndex].title;
	if(status=='fms_Dispose')
	{
					if(confirm('<fmt:message key="WF.FileDisposeAlert" bundle="${fmsLables}"></fmt:message>'))
					{
								checkfmsaction(10);
								retval=document.getElementById('ProceedFmsAction').value;
								
								if(retval=="yes")
								{
									var urlStyle1 ='width=830,height=450,toolbar=no,menubar=no,scrollbars=yes,location=no,top=150,left=100'; 
									var fileId = document.getElementById('fileId').value;
									displayModalDialog('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=file&disposedFlag=yes&fileId='+fileId, "test", urlStyle1);
														
								}							
					 	
				  }		
	}
	
}
function SendFileAsCorr()
{
	
	
	var fileId = document.getElementById('fileId').value;
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
	//window.open('hdiits.htm?actionFlag=SendFileAsCorrespondece&fileId='+fileId,'',urlStyle);
	
	window.open('hdiits.htm?actionFlag=DeptSearchData&SendAsCorr=yes&fileId='+fileId,'',urlStyle);
	
	
}
function rejectCorr()
{
	document.getElementById("Converted_text").value = '';	
	document.getElementById('wfAction').value='Approve';
	document.getElementById('reject').value='yes';
	var urlStyle ='width=500,height=350,toolbar=no,menubar=no,location=no,top=150,left=200'; 
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=file&rejectflag=yes&approvFlag=1&fileId='+fileId,'test',urlStyle);
				
}
function sendcorrasreply()
{
	var urlStyle = 'toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen'; 
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=initiateCorrespondence&fromflag=workList&responcereq=yes&fileId='+fileId,'sendfilsascorr',urlStyle);
}
function createCommunique()
{
	var urlStyle = 'toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen';
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=FMS_viewNewCommunique&fileId='+fileId,'',urlStyle);
	
}
function printNotings()
{
	
	var fileId = document.getElementById('fileId').value;
	var urlStyle = 'toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen'; 
	displayModalDialog('hdiits.htm?actionFlag=FMS_viewAllNotings&fileId='+fileId,"printnoting", urlStyle);
	
}
function printParallelNotings()
{
	var outbox = '${fromOutBox}';

	var fileId = document.getElementById('fileId').value;
	var urlStyle = 'toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen'; 
	displayModalDialog('hdiits.htm?actionFlag=printParallelNotings&fileId='+fileId+'&fromOutbox='+outbox,"printnoting", urlStyle);
	
}
function setdisposeinfotable()
{

	if(document.getElementById('disposeinfotable').style.display=="none")
	{		
			document.getElementById('disposeinfotable').style.display='';
			document.getElementById('showCorrDisposeInfoLink').innerHTML='<fmt:message key="WF.HideDisposeInfo" bundle="${fmsLables}"></fmt:message>';


			document.getElementById('infotable').style.display='none';
			document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.ShowFileInfo" bundle="${fmsLables}"></fmt:message>';
			
			
	}
	else
	{
		document.getElementById('disposeinfotable').style.display='none';
		document.getElementById('showCorrDisposeInfoLink').innerHTML='<fmt:message key="WF.ShowDisposeInfo" bundle="${fmsLables}"></fmt:message>';
	}
}
function setinfotable()
{

		if(document.getElementById('infotable').style.display=="none")
		{		
				document.getElementById('infotable').style.display='';
				document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.HideFileInfo" bundle="${fmsLables}"></fmt:message>';

				document.getElementById('disposeinfotable').style.display='none';
				document.getElementById('showCorrDisposeInfoLink').innerHTML='<fmt:message key="WF.ShowDisposeInfo" bundle="${fmsLables}"></fmt:message>';
		}
		else
		{
				document.getElementById('infotable').style.display='none';
				document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.ShowFileInfo" bundle="${fmsLables}"></fmt:message>';
		}
}
function openReminder()
{	
	var urlStyle ='toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,type=fullWindow';
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=FMS-CreateReminder&fileId='+fileId,'test',urlStyle);
			

}
function editReminder()
{	
	var urlStyle ='toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,type=fullWindow';
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=FMS-getReminderForJob&fileId='+fileId,'test',urlStyle);
			

}

</script>


	
<hdiits:form name="menuform" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true" >

<hdiits:hidden name="ProceedFmsAction" default="yes" />
<hdiits:hidden name="PKval" default="${resultMap.pkval}" />
<hdiits:hidden name="fromCommonHomePage" default="${resultMap.fromCommonHomePage}"/>
<hdiits:hidden name="DecisionFlag" default="file"/>
<hdiits:hidden name="jobOwnerPostId" default="${resultMap.jobOwnerPostId}"/>
<hdiits:hidden name="UserIdFromEmpSrch"/>
<hdiits:hidden name="isMark"/>
<hdiits:hidden name="roleId_Hidden"/>
<hdiits:hidden name="forwardFromSupportHandler" default="no"/>
<hdiits:hidden name="currentDate" id="currentDate" default="${currentDateJs}"/>
<hdiits:hidden name="loggedInUserId" default="${resultMap.LoggedinUser}"/>
<hdiits:hidden name="receivedDate" default="${resultMap.rcvdDate}"/>
<input type="hidden" name="tabclickcnt" />
<input type="hidden" name="approveDraftFlag"  value="no"/>
<input type="hidden" name="viewInfoTableflag" />
<hdiits:hidden name="infotabflag" default="${resultMap.infotabflag}" />
<input type="hidden" name="fileId" id="fileId" value="${fileId}"/>
<hdiits:hidden name="reject"  default="no"/>
<hdiits:hidden name="disposeremarks"  />
<hdiits:hidden name="disposetype"  />
<hdiits:hidden name="disposeclass"  />
<hdiits:hidden name="fromSaveButton"  default="${resultMap.fromSaveButton}"/>
<hdiits:hidden name="parallelFileFlag"  default="${resultMap.parallel}"/>
<hdiits:hidden name="parallelFileOwner"  default="${resultMap.fileOwner}"/>
<hdiits:hidden name="saveNoting" default="${resultMap.saveNoting}"/>
<hdiits:hidden name="saveNotingId" default="${resultMap.saveNotingId}"/>
<hdiits:hidden name="loginLocId" default="${resultMap.loginLocId}"/>
<!-- 
<a href="javascript:openReminder();">reminder</a>
<a href="javascript:editReminder();">edit reminder</a>
 -->

<table width="100%" border="1" bordercolor="black"  cellspacing="0" cellpadding="2" >
<tr>
	<td>
<div>
	<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param name="fromFile" value="true"/>
		<jsp:param name="fileIdFromWorkFlow" value="${fileId}"/>
		<jsp:param name="hideMenuLookupID" value="${hideMenuLookupID}" />
	</jsp:include>
</div>
<hdiits:fieldGroup titleCaptionId="WF.fileInfo" bundle="${fmsLables}"  >
<div>
	<center>
	<a href="#" onclick="setinfotable()" id="showFileInfoLink"><u> <fmt:message key="WF.ShowFileInfo" bundle="${fmsLables}"></fmt:message></u></a>
	<label style="display:none" id="sepid" ><hdiits:caption  captionid="" caption="/"   /></label>
	<a href="#" style="display: none" onclick="setdisposeinfotable()" id="showCorrDisposeInfoLink"><u> <fmt:message key="WF.ShowDisposeInfo" bundle="${fmsLables}"></fmt:message></u></a>
	</center>
</div>

<table  id="disposeinfotable" style="display:none">
	<tr>
		<td>
		<hdiits:caption captionid="WF.DisposeRem" bundle="${fmsLables}"/>
		</td>
		<td>
		<textarea id="txtdisposeremarks" name="txtdisposeremarks" cols="50" rows="2"  readonly="readonly" ></textarea>
		</td>
		<td>
		<hdiits:caption captionid="WF.DisposeType" bundle="${fmsLables}" ></hdiits:caption>
		</td>
		<td>
		<hdiits:text name="txtdistype" readonly="true" />	
		</td>
	</tr>
	<tr>	
		
		<td>
		<hdiits:caption captionid="WF.DisClass" bundle="${fmsLables}"   ></hdiits:caption>
		</td>
		<td>
		<hdiits:text name="txtdisclass" default="${resultMap.disposeclass}" readonly="true"/>
		</td>
	</tr>
</table>

<table width="100%" border="1" bordercolor="#99CC99" style="display: none" id="infotable" >

		<tr>				
				<td width="10%" style="border: none">
					<b><hdiits:caption  captionid="WF.FileNo" bundle="${fmsLables}" /></b>
		        	<font color="blue"><c:out value="${resultMap.fileNo}"></c:out></font>
	    		</td>
	    			    		    	
	    		<td  width="10%" style="border: none" id="duedateid1">
	    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			<b><hdiits:caption  captionid="WF.DueDate" bundle="${fmsLables}"/></b>&nbsp;&nbsp;
	    			<hdiits:text id="txtduedateid" name="txtduedate" size="10" readonly="true"/>
	    		</td>
	    		<td width="10%"  style="border: none;display: none;" id="duedateid2" >
	    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			<b><hdiits:caption  captionid="WF.DueDate" bundle="${fmsLables}"/></b>&nbsp;&nbsp;
		    		<hdiits:dateTime name="txtsetDate" captionid="WF.DueDate" bundle="${fmsLables}" maxvalue=""/>
	    		</td>
	    		
	    		
	    		<td width="10%"  style="border: none">
	    			<b><hdiits:caption captionid="WF.RcvdDate" bundle="${fmsLables}"/></b>&nbsp;&nbsp;<c:out value="${resultMap.rcvdDate}"></c:out>
				</td>
	    		
	    		<td width="10%" style="border: none" >
	    		
	    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			<b><hdiits:caption  captionid="WF.Status" bundle="${fmsLables}"   /></b>	    			
	    			<hdiits:select  size="1"  name="satus" sort="false" onchange="status_onchange()" id="status" readonly="${disableflag}">	
		    			 <c:forEach  items="${filestatuslist}" var="filestatusLookup">
		    				
		    				<c:if test="${filestatusLookup.lookupName eq (fmsFileVo.cmnLookupMstByFileStatus.lookupName)}">
									<option value="<c:out value="${filestatusLookup.lookupName}"/>"  selected="true" title="${filestatusLookup.lookupName}">
									<c:out value="${filestatusLookup.lookupDesc}"/></option>
							</c:if>
			    			<c:if test="${filestatusLookup.lookupName ne (fmsFileVo.cmnLookupMstByFileStatus.lookupName)}">
			    					<option value="<c:out value="${filestatusLookup.lookupName}"/>" title="${filestatusLookup.lookupName}">
									<c:out value="${filestatusLookup.lookupDesc}"/></option>
							</c:if>
							
						</c:forEach>
						 
					
						
		    		</hdiits:select> 
		    		
		    		
				</td>
		</tr>
		<tr>
	    		<td width="10%"  style="border: none">
	    		 	<b><hdiits:caption  captionid="WF.Sub" bundle="${fmsLables}"/></b>&nbsp;&nbsp;<c:out value="${resultMap.subject}"></c:out>
	    		</td>
	    		
	    		<td width="10%" style="border: none">
	    			<b><hdiits:caption  captionid="WF.CreatedtdDate" bundle="${fmsLables}" /></b>&nbsp;&nbsp;<c:out value="${createddate}"></c:out>
	    		</td>
	    		
	    		<td width="10%"  style="border: none">
	    			&nbsp;<b><hdiits:caption  captionid="WF.Classification" bundle="${fmsLables}" /></b>&nbsp;&nbsp;<c:out value="${resultMap.classification}"></c:out>
	    		</td>
	    		
	    		<td width="10%"  style="border: none" >
	    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    			<b><hdiits:caption captionid="WF.Priority" bundle="${fmsLables}"/></b>
	    			<c:if test="${fmsFileVo.cmnLookupMstByPriority.lookupShortName ne '5#DateSet'}">
				
						<hdiits:select id="priorityId" name="priority" sort="false"	onchange="priority_onchange()" readonly="${disableflag}">
						<c:forEach items="${Prioritylist}" var="PriorityLookup">
							
							
						<c:if test="${PriorityLookup.lookupName ne (fmsFileVo.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>' title="${PriorityLookup.lookupShortName}">
						<c:out value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>
						<c:if test="${PriorityLookup.lookupName eq (fmsFileVo.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>'title="${PriorityLookup.lookupShortName}" selected="true">
						<c:out	value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>		
							
							
						</c:forEach>
						</hdiits:select>
				</c:if>
				<c:if test="${fmsFileVo.cmnLookupMstByPriority.lookupShortName eq '5#DateSet'}">
				<hdiits:select id="priorityId" name="priority" sort="false" readonly="${disableflag}">
						<c:forEach items="${Prioritylist}" var="PriorityLookup">
							
						<c:if test="${PriorityLookup.lookupName eq (fmsFileVo.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>'title="${PriorityLookup.lookupShortName}" selected="true">
						<c:out	value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>		
							
							
						</c:forEach>
				</hdiits:select>		
				</c:if>
				</td>
		</tr>
		<tr>
	    		
	    		<td width="10%"  style="border: none">
	    			 <b><hdiits:caption   captionid="WF.Desc" bundle="${fmsLables}"/></b>&nbsp;&nbsp;<c:out value="${resultMap.desc}"></c:out>
	    		</td>
	    		
	    		<td width="10%"  style="border: none">
	    			&nbsp;&nbsp;<b><hdiits:caption   captionid="WF.Department" bundle="${fmsLables}" /></b>&nbsp;&nbsp;<c:out value="${resultMap.dept}"></c:out>
	    		</td>
	    		
	    		<td width="10%"  style="border: none">
	    			<b><hdiits:caption captionid="WF.SpecialCases" bundle="${fmsLables}"/></b>
	    			<hdiits:select captionid="WF.FileNo"  bundle="${fmsLables}" size="1"  name="SpecialCases" readonly="${disableflag}" >	
			    	  	<hdiits:option value="Select" selected="true"><hdiits:caption caption="General" captionid=""/>
			    	 	</hdiits:option>	
			    	 	<hdiits:option value="Select" selected="true"><hdiits:caption caption="GOI" captionid=""/>
			    	 	</hdiits:option>	
						<hdiits:option value="Select" selected="true"><hdiits:caption caption="RTI" captionid=""/>
			    	 	</hdiits:option>	
			    	 	<hdiits:option value="Select" selected="true"><hdiits:caption caption="Vigilance Realated" captionid=""/>
			    	 	</hdiits:option>	
					</hdiits:select> 
				</td>
				
				<td width="10%"  style="border: none" >	
				
	    			<b><hdiits:caption captionid="WF.Confidentiality"  bundle="${fmsLables}" /></b>
	    			<hdiits:select   name="Confidentiality" sort="false" readonly="${disableflag}">	
		    			<c:forEach  items="${Confidentialitylist}" var="ConfidentialityLookup">		    				
		    				<c:if test="${ConfidentialityLookup.lookupId ne (fmsFileVo.cmnLookupMstByConfidentiality.lookupId)}">
		    					<option value='<c:out value="${ConfidentialityLookup.lookupName}"/>' >
								<c:out value="${ConfidentialityLookup.lookupDesc}"/></option>
							</c:if>
							<c:out value="${fmsFileVo.cmnLookupMstByConfidentiality.lookupId}"/>
							<c:if test="${ConfidentialityLookup.lookupId eq (fmsFileVo.cmnLookupMstByConfidentiality.lookupId)}">
		    					<option value='<c:out value="${ConfidentialityLookup.lookupName}"/>' selected="true">
								<c:out value="${ConfidentialityLookup.lookupDesc}"/></option>
							</c:if>
						</c:forEach>
		    		</hdiits:select> 
	    		</td>
		</tr>
</table>
</hdiits:fieldGroup>


<input type="hidden" name="addtocorrflag" value="no"/>
<input type="hidden" name="Converted_text"/>
<input type="hidden" name="postId_Hidden"/>
<input type="hidden" name="isNormalHierachySelected"/>
<input type="hidden" name="isDocAlreadyMarked"/>
<input type="hidden" name="onApproveForwardFlag" value="no"/>
<hdiits:hidden name="postList"/>
<hdiits:hidden name="commonPoolItemFlag"/>
<hdiits:hidden name="wfAction" default="temp"/>
<hdiits:hidden name="markedList"/>
<hdiits:hidden name="currenttabid" default="tab8"/>

<input type="hidden" name="note_counter" value="<%=notecnt%>"/>
<input type="hidden" name="note_type"/>
<input type="hidden" name="conutMn" value="1"/>
<input type="hidden" value="" name="winName"/>
<input type="hidden" name="RichTextVals" />
<input type="hidden" name="RichTextObj" />
<hdiits:hidden name="disposedFlag" />

<iframe name="workflow_tab" src="hdiits.htm?viewName=wf-showTab" width="100%" height="35" frameborder="0" scrolling="no" >
</iframe>
<table width="100%" class="tabcontentstyle">
		<tr>
			<td width="20%" valign="top"  id="t1">
					<hdiits:fieldGroup titleCaptionId="WF.Notings" bundle="${fmsLables}">
					<table cellspacing="0" cellpadding="0">	
						<tr>
                			<td>
    			            	<hdiits:button readonly="${disableflag}" type="button"  name="nextButton" id="nextButtonid" captionid="WF.NextNoting"  bundle="${fmsLables}" onclick="submitForm('next');" />
				                <hdiits:button type="button"  name="" id="expandbutton" captionid="WF.Expand"  bundle="${fmsLables}"  onclick="expand_onclick();"  />
				                <hdiits:button name="btnprint" type="button" captionid="WF.ViewAllNoting" bundle="${fmsLables}" onclick="printNotings()"/>
				                <c:if test="${showParallelNotings eq 'Y'}">
					                <hdiits:button name="btnprintp" type="button" caption="View Parallel Notings" onclick="printParallelNotings()"/>
				                </c:if>
				             </td>               		
	    
	   				   </tr>	
	   				   <tr>
	   				   </tr>
	   				  					
						   <tr>
								<td valign="top" width="10%">
									<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp" flush="true">
										<jsp:param name="richTextName" value="<%=richTextCounter++%>" />
										
									</jsp:include>
								</td>
			                </tr>			                
			              	<tr> 
								<td valign="top" width="10%">
									<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp" >
					    			<jsp:param name="attachmentName" value="<%=newValue%>" />
					                <jsp:param name="formName" value="menuform" />
					                <jsp:param name="attachmentType" value="Document" /> 				               
					                <jsp:param name="attachmentTitle" value="${Attach}" />  
					                </jsp:include>
					           	</td>
			                </tr>
		              
		                <tr>
			            </tr>
	        	        <tr>
	                		<td>
	                			  
								 
	                			<table width="100%" border="1" bordercolor="green" cellpadding="2" cellspacing="0">
	                				  <%int i=1, k=0; %>	
	                				  							
										 <c:forEach var="Noting" items="${Notings}" varStatus="varcnt">
											<c:set var="note" value="${Noting}"></c:set>
		
											<%												
											if(user==null)
											{
												user=(String)users.get(usercount);		
				 							%>
				 										<tr>								
						 				 				<td>
						 				 				<img name="noteImage" style="cursor: hand"" src="themes/defaulttheme/images/workflow/expand.gif" onclick="toggle('Firstdiv<%=i++%>',this)"></img> 								
						 				 					<b>
						 				 						<%
						 				 						String notingUser=(String)users.get(usercount++);
						 				 						String notingUser2=notingUser.substring(notingUser.indexOf("/")+1);
						 				 						String notingUser3=notingUser.substring(0,notingUser.indexOf("/")-1);		 						
						 				 						
						 				 						out.print(notingUser3+"<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); 
						 				 						out.println(" "+notingUser2+"<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); 
						 				 						out.println((String)date.get(datecounter++)+"<BR>"); 
						 				 						out.println(listPara.get(paraCounter++));	
						 										%>
						 									</b>
						 									
						 				 										 				 								
														<div id="Firstdiv<%=counter_div++%>" style="border:thin ;">	
		
				 				 			<%
											}
											else if(!(user.equalsIgnoreCase(users.get(usercount).toString()))){
														user=(String)users.get(usercount);
											%>
														</div> 
														</td>
				 										</tr> 	
				 																	
		 												<tr>								
		 				 								<td>
		 				 								<img name="noteImage" style="cursor: hand"" src="themes/defaulttheme/images/workflow/expand.gif" onclick="toggle('div<%=i++%>',this)"></img> 								
		 				 									<b>
		 				 										<%
		 				 										String notingUser=(String)users.get(usercount++);
						 				 						String notingUser2=notingUser.substring(notingUser.indexOf("/")+1);			 				 						
						 				 						String notingUser3=notingUser.substring(0,notingUser.indexOf("/")-1);						 				 						
						 				 						
						 				 						out.print(notingUser3+"<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); 
						 				 						
						 				 						out.println(" "+notingUser2+"<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); 
						 				 						out.println((String)date.get(datecounter++)+"<BR>"); 
						 				 						out.println(listPara.get(paraCounter++));								
				 												
				 												%>
				 												
				 											</b>
		 				 								<div id="div<%=counter_div++%>" style="border:thin;">	
		
		 				 					<%
		 				 					}
											else{
												%><b>
												<%
												out.println(listPara.get(paraCounter++));											
												usercount++;
											}
											%>
											</b>			
														
											<table width="100%" cellspacing="0" cellpadding="2" >
							 				<tr>
												<td width="10%"> 	 														
												<script type="text/javascript">	
												//	document.menuform.elements['note_counter'].value=<%=i%>	
													var text='${note}';							
													var decoded_text=decodeBase64(text);								
													document.menuform.elements['Converted_text'].value=decoded_text								
													var text=document.menuform.elements['Converted_text'].value											
												</script>									
											<%try
											{
												ResultObject resObj;
												resObj = (ResultObject) request.getAttribute("result");		
												Map resValueMap = (HashMap) resObj.getResultValue();
												String[] noteTypeList;String pkval;
												List noteing=new ArrayList();
												noteTypeList =(String[])resValueMap.get("NoteTypeList");
												pkval =(String)resValueMap.get("pkval").toString();																	
												List<FmsFileNotings> fileNotingData =(List<FmsFileNotings>)resValueMap.get("FmsFileNotings");
												noteing =(List)resValueMap.get("Notings");
												
												request.setAttribute("FmsFileNotings",fileNotingData);																	
												if(noteTypeList[note_type_cnt++]=="MN")
												{	
													flag=true;
												%>								
												<script type="text/javascript">
													document.menuform.elements['note_counter'].value=<%=noteing.size()+1%>																		
												</script>				
												<% newValue="DocumentAttachment"+attachmentCounter++;%>
												<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp">
												<jsp:param name="richTextName" value="<%=richTextCounter%>"/>
												</jsp:include>
												
												<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp">
								    			<jsp:param name="attachmentName" value="<%=newValue%>" />
								                <jsp:param name="formName" value="menuform" />
								                <jsp:param name="attachmentType" value="Document" /> 								               
								                <jsp:param name="attachmentTitle" value="${Attach}" />  
								                </jsp:include>									
																	
												
												<% }else{
												%>
												<script type="text/javascript">
												document.writeln(text);
												</script>
												<%} 										
												}catch(Exception e)
												{
													e.printStackTrace();
												}
											%>									
											</td>
											</tr>
											<tr>
											<td>
																	
											<% 
											if(!flag){
											try{Hashtable attachmentTable = new Hashtable();											
												ResultObject resObj;
												resObj = (ResultObject) request.getAttribute("result");		
												Map resValueMap = (HashMap) resObj.getResultValue();
												attachmentTable = (Hashtable)resValueMap.get("AttachmentTable");										
												List objAttachmentList=(List)attachmentTable.get("AttachmentUrlList"+k);
												List attachmentNamesList = (List)resValueMap.get("AttachmentNames");
												List<FmsFileNotings> fileNotingsList= (List<FmsFileNotings>)resValueMap.get("FmsFileNotings");
												
												%>
												<%if(!flag  && objAttachmentList.size()>0){%>
										
													<h3>Attachments</h3>
										
											<%}%>	
												<%
												if(fileNotingsList!=null && fileNotingsList.get(notingCounter++).getCmnAttachmentMst() != null  && (objAttachmentList!=null && objAttachmentList.size()>0))
												{
													for(int m=0;m<objAttachmentList.size();m++)
													{													
											%>
												<hdiits:a href="<%=(String)objAttachmentList.get(m)%>" caption="<%=(String)attachmentNamesList.get(counter++)%>"></hdiits:a>
												<br>
											<%}	
													k++;
												}
											}catch(Exception e)
											{
												e.printStackTrace();
											}
											}
											if(flag){
												flag = false;
												
											%>
												<script>
													document.menuform.elements['conutMn'].value=<%=richTextCounter%>;
													//alert(" text " + text);
													allNotings.push(text);
												</script>
											<%
											richTextCounter++;
											}
											else
											{
												%>
												<script>
													allNotings.push("continue");
												</script>
												<%
												
											}%>													
											</td>
											</tr>									
											</table>	
														
											
										</c:forEach>
			                			</table>
						              
			                		</td>
				                </tr>
			                </table>	
			                </hdiits:fieldGroup >
					</td>
					
					
					<td width="80%" valign="top" id="t2">
					<hdiits:fieldGroup titleCaptionId="WF.BasicInfo" bundle="${fmsLables}"  >
					 <table width="100%" id="tab2">
								<tr>
									<td width="100%">
										<hdiits:button type="button" name="expandbutton2" id="expandbutton2" captionid="WF.Expand"  bundle="${fmsLables}" onclick="expand_onclick2()" />
									</td>
								</tr>	
								<tr>
									<td width="100%">
											<iframe  align="top"  id="myFrame" name="Target_frame" src="${resultMap.basicInfoUrl}" width="100%" height="670" scrolling="auto" ></iframe>
									</td>
								</tr>
							</table>
					</hdiits:fieldGroup>		
						
					</td> 
		</tr>			
		                
	</table>
	</td>
	</tr>
</table>	

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
		<script>
		var padInteraval=0;
		function setTabPadding()
		{
			
			if(window.frames['workflow_tab'].document.getElementById("content")!=null)
			{

			window.frames['workflow_tab'].document.getElementById("content").style.paddingLeft=0+'px'
			window.frames['workflow_tab'].document.getElementById("content").style.width='100%'
			window.clearInterval(padInteraval);
			}
		}

		padInteraval=window.setInterval("setTabPadding();",1000);
				var notingintervalid;
				var totalnotings='<%=richTextCounter%>';
				var flagsavenoting=true;
				function loadNotingContent()
				{		
						var cntForAllNotings = 0;						
						totalnotings=totalnotings-1;
						var saveNoting='${fromSave}';						
						if(saveNoting == 'yes'){
							totalnotings=totalnotings-1;
							if(flagsavenoting){
								var oRTE1= document.getElementById('rte1').contentWindow.document;		               		
			               		var string='${saveNoting}';	
			               		var decoded_text=decodeBase64(string);
								oRTE1.body.innerHTML =decoded_text; 
								flagsavenoting=false;
								window.clearInterval(notingintervalid);
								document.getElementById('hdnrte1').value=oRTE1.body.innerHTML ;								
							}
						}
						if(document.getElementById('rte'+totalnotings) != null)
						{	
							for(var iCnt=1; iCnt<parseInt("<%=richTextCounter%>");iCnt++)
							{
								//alert(document.getElementById('rte'+iCnt) + " : " + iCnt + " : " + allNotings[iCnt]);
								if(document.getElementById('rte'+iCnt) != null && iCnt != 1)
								{
									var oRTE1= document.getElementById('rte'+iCnt).contentWindow.document;
									//alert(" in for " + iCnt + " : " + oRTE1 + " : " + allNotings[iCnt]);
									if((allNotings[cntForAllNotings] == undefined) || (allNotings[cntForAllNotings] == "continue") )
									{
									//	alert(" in bried");
										continue;
									}
									var output = escape(allNotings[cntForAllNotings]);
									cntForAllNotings = parseInt(cntForAllNotings)+1;
									output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
									output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");	
										
									if(document.forms[0].RichTextVals.value == '')
									{
										document.forms[0].RichTextVals.value = unescape(output);
										document.forms[0].RichTextObj.value = oRTE1;
									}
									else
									{
										document.forms[0].RichTextVals.value = document.forms[0].RichTextVals.value + "," + unescape(output);
										document.forms[0].RichTextObj.value = document.forms[0].RichTextObj.value + "," + oRTE1;
									}
									
									oRTE1.body.innerHTML = unescape(output);	
									document.getElementById('hdnrte'+iCnt).value=oRTE1.body.innerHTML ;
								}
									
							}	
							window.clearInterval(notingintervalid);
						 }	
						 
				}
				notingintervalid=window.setInterval("loadNotingContent();",2000);		
				
				if('${fmsFileVo.dueDate}' == '')
				{
					
					document.getElementById('txtduedateid').value=duedate;	
				}
				else
				{
					
					document.getElementById('txtduedateid').value= '${formateduedate}';
				}	
				function settablink()
   		        {
   		            if(tabtoclick!="")
	   		        {
	   		        		var obj=window.frames['workflow_tab'].document.getElementById(tabtoclick);
	   		        		window.frames['workflow_tab'].changecolor(obj);
	   		        		document.getElementById('tabclickcnt').value=tabtoclick;
	   		        		window.clearInterval(intervalid);
	   		        		
			        }
		        } 
		        window.clearInterval(intervalid);
			    intervalid=window.setInterval("settablink();",5000);
			     window.clearInterval(tabinterval);
			    tabinterval=window.setInterval("setTabLinkUrl();",5000);
			  
			    if(headerinfo=="true")
				{
					document.getElementById('infotable').style.display='';
					document.getElementById('showFileInfoLink').innerHTML="HideFileInfo";
				}
					
				function changeFrameSrc(corrId,fileId)
				{
					document.getElementById("myFrame").src = "hdiits.htm?actionFlag=showCorrAttach&butFlag=Y&corrId="+corrId+"&fileId="+fileId;
				}
				function backtoFrameSrc(fileId)
				{
					document.getElementById("myFrame").src = "hdiits.htm?actionFlag=showAddedCorrespondence&fileId="+fileId;
				}
					
				
				document.forms[0].RichTextVals.value = '';
				document.forms[0].RichTextObj.value = '';
				
				if("${winName}" == "")
				{
					document.getElementById('winName').value=window.opener.parent.name;
				}
				else
				{
					document.getElementById('winName').value="${winName}";
				}
				
				
				document.getElementById('txtduedateid').value= '${formateduedate}';
				hideProgressbar();

				var fileDisposeFlag='${fileDisposeFlag}';
				if(fileDisposeFlag=="true")
				{

					
					document.getElementById('showCorrDisposeInfoLink').style.display='';
					document.getElementById('sepid').style.display='';
					
					document.getElementById('txtdisposeremarks').value='${resultMap.disposeremarks}';
					if('${resultMap.disposetype}'==1)
					{	
						document.getElementById('txtdistype').value='<fmt:message key="WF.Positive" bundle="${fmsLables}"/>'
					}
					else
					{
						document.getElementById('txtdistype').value='<fmt:message key="WF.Negative" bundle="${fmsLables}"/>'
					}
					 
				}
				
		
		</script>
	
<%

}
catch (Exception e)
{
	e.printStackTrace();	
}
%>

<script>




function getDrafts(){
	var urlStyle = 'toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen'; 
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=fms_openFileDraftPage&fileId='+fileId,'',urlStyle);
}
function sendDraftAsCommunique(){
	var urlStyle = 'toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen';
	var fileId = document.getElementById('fileId').value;
	window.open('hdiits.htm?actionFlag=fms_openFileDraftPage&draftAsCommuique=yes&fileId='+fileId,'',urlStyle);
}


</script>