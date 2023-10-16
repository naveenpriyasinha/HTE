
<%
try {
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
<%@page import="com.tcs.sgv.fms.valueobject.FmsCorrNotings"%>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hod/ps/common.js"/>"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/treeScript/menu.js"></script>
<script type="text/javascript" src="<c:url value="script/treeScript/wz_tooltip.js"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>">
</script>



<script type="text/javascript" src="script/common/mainNavJs.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="filestagelist" value="${resultMap.filestagelist}"></c:set>
<c:set var="filestatuslist" value="${resultMap.filestatuslist}"></c:set>
<c:set var="Prioritylist" value="${resultMap.Prioritylist}"></c:set>
<c:set var="Confidentialitylist" value="${resultMap.Confidentialitylist}"></c:set>
<c:set var="AttachmentNameList" value="${resultMap.AttachmentNames}"></c:set>
<c:set var="users" value="${resultMap.OrgUsers}"></c:set>
<c:set var="Notings" value="${resultMap.Notings}"></c:set>
<c:set var="AttachmentNameList" value="${resultMap.AttachmentNames}"></c:set>
<c:set var="DocList" value="${resultMap.WfDocMstList}"></c:set>
<c:set var="CorrespondenceList"	value="${resultMap.CmnLookupMstByCorrespondenceList}"></c:set>
<c:set var="CorrVO" value="${resultMap.FmsCorrMst}"></c:set>
<c:set var="urlval" value="${resultMap.url}"></c:set>
<c:set var="LoginUserPostName" value="${resultMap.LoginUserPostName}"></c:set>
<c:set var="LoginUserPost" value="${resultMap.PostDetailsList}"></c:set>
<c:set var="LoginUserLoc" value="${resultMap.LoginUserLoc}"></c:set>
<c:set var="CmnLocationMstForCrtLoc" value="${resultMap.CmnLocationMstForCrtLoc}"></c:set>
<c:set var="tabclickcnt" value="${resultMap.tabclickcnt}"></c:set>
<c:set var="viewInfoTableflag" value="${resultMap.viewInfoTableflag }"></c:set>
<c:set var="postIdtoReturn" value="${resultMap.postIdtoReturn }"></c:set>
<c:set var="LoginEmployee" value="${resultMap.LoginEmployee }"></c:set>
<c:set var="aclElementMstList" value="${resultMap.aclElementMstList}" scope="session"></c:set>
<c:set var="noOfLevelsInMenu" value="${resultMap.noOfLevelsInMenu}" scope="session"></c:set>
<c:set var="disableflag" value="${resultMap.disableflag}"></c:set>
<c:set var="sendascorr" value="${resultMap.sendascorr}"></c:set>
<c:set var="fromDispose" value="${param.fromDispose}"> </c:set>
<c:set var="fromdisposeFlagOrNot" value="${param.fordisableNoting}"> </c:set>
<c:set var="lSelFileTablst" value="${resultMap.lSelFileTablst}"></c:set>
<c:set var="corrDisposeFlag" value="${resultMap.corrDisposeFlag}"></c:set>
<c:set var="saveNoting" value="${resultMap.saveNoting}"></c:set>
<c:set var="fromSave" value="${resultMap.fromSaveButton}"></c:set>
<c:set var="departmentId" value="${resultMap.deptId}"></c:set>
<c:set var="sendBackTo" value="${resultMap.sendBackTo}"></c:set>


<c:forEach items="${lSelFileTablst}" var="TabNO" >
			<script>		
			tab_arr.push('${TabNO}');
			</script>					
							
							
</c:forEach>

<fmt:formatDate value="${CorrVO.dueDate}" pattern="dd/MM/yyyy"	dateStyle="medium" var="formateduedate" />
<fmt:formatDate value="${CorrVO.dueDate}" pattern="MM/dd/yyyy"	dateStyle="medium" var="formateddate" />
<fmt:formatDate value="${CorrVO.crtDate}" pattern="MM/dd/yyyy"	dateStyle="medium" var="corrcreateddate" />
<fmt:formatDate value="${resultMap.currentDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="currentDateJs"/>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<%
		ResultObject result = (ResultObject) request.getAttribute("result");
		Map resultMap = (Map) result.getResultValue();
		ArrayList tablinkURLArls = (ArrayList) resultMap.get("tabUrlArls");
		List notingsList=(List)resultMap.get("notingsList");
		List displayNotingList=(List)resultMap.get("displayNotingList");
		session.setAttribute("displayNotingList",displayNotingList);
		
		 String langId=resultMap.get("landId").toString();
		 session.setAttribute("langId",langId);
		session.setAttribute("serviceLocator",resultMap.get("serviceLocator"));
		request.setAttribute("notingsList",notingsList);
		ArrayList users = (ArrayList) resultMap.get("OrgUsers");
		int notecnt = users.size() + 1;
		List date = new ArrayList((Set) resultMap.get("CrtDate"));
		long corrId = Long.parseLong(resultMap.get("corrId").toString());
		List listPara = (List) resultMap.get("listPara");		
		
		int counter_div = 1;
		int counter = 0;
		int notingCounter = 0;
		int paraCounter=0; 
		int note_type_cnt = 0, usercount = 0, datecounter = 0, richTextCounter = 1, attachmentCounter = 1;
		String newValue = "DocumentAttachment" + attachmentCounter++, user = null;
		boolean flag = false;
%>
<Script Language=VBScript>

Function vbMsg(isTxt)

testVal = MsgBox(isTxt,3)
isChoice = testVal

End Function
</script>
<script type="text/javascript">
if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
} 

//Code for adding the outward link for inserting the CorrId:
function openCorrOutward()
{
	var corrId="${CorrVO.corrId}";
	var corr="Correspondence";
	var url = "${contextPath}/hdiits.htm?actionFlag=loadFileCategoryDtls&txtCorrId="+corrId+"&Sender="+corr;
	//alert("Url for corr::"+url);
	window.open(url, '', 'status=no,toolbar=no,scrollbars=yes,width=1500,height=700');		
}






showProgressbar();
var headerinfo='${viewInfoTableflag}'
var tabtoclick='${tabclickcnt}'
var tabinterval;

var t1=new Date();
var duedate;
var currdate=t1.getDate()  + "/" + eval(t1.getMonth()+1) + "/" + t1.getFullYear() ;
var urlstyle = 'width=500,height=300,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
var expandflag="false";
var expandflag2="false";
var flag="true";




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
function closeParent()
{
	if("${resultMap.dashBoardMenu}" != "")
	{
	self.close();
	return;
	}
	var fromCommonHomePage="${resultMap.fromCommonHomePage}";
	if((forSWRCloseRequest) || ("${fromOutBox}" == "true"))
	{
		if(window.opener.parent.document.getElementById("dataFrame") != null){
			
			window.opener.document.forms[0].action="hdiits.htm?actionFlag=getDocListOfWorkList&docType=-1";
			window.opener.document.forms[0].submit();
			window.close();
			return;
		}else{
			
		self.close();
		return ;
		}
	}
	
	corrAddInFile='${resultMap.corrAddInFile }';
	if(corrAddInFile!="yes")
	{
	
				vbMsg('<fmt:message key="WF.SaveMsg" bundle="${fmsLables}"></fmt:message>')
				if(isChoice==6)
				{
					if("${fromdisposeFlagOrNot}" == "yes")
					{
						window.opener.focus();
						self.close();
					}
					else
					{
						if(submitForm(''))
						{
							if(fromCommonHomePage=="yes")
							{
								parent.focus();
								self.close();
							}
							else
							{
								window.opener.document.forms[0].action="hdiits.htm?actionFlag=getDocListOfWorkList&docType=-1";
						    	window.opener.document.forms[0].submit();
						    	window.close();
							}
						}
					}
				}
				else if(isChoice==7)
				{
						if("${fromdisposeFlagOrNot}" == "yes")
						{
							window.opener.focus();
							self.close();
						}
						else
						{
							if(fromCommonHomePage=="yes")
							{
								parent.focus();
								self.close();
							}
							else
							{
								window.opener.document.forms[0].action="hdiits.htm?actionFlag=getDocListOfWorkList&docType=-1";
								window.opener.document.forms[0].submit();
								window.close();
							}
						}
				}
		}	
		else
		{
				self.close();
		}
	
				
}
function Expand() 
{
   divs=document.getElementsByTagName("DIV");
   for (i=0;i<divs.length;i++){
     divs[i].style.display="block";
     key=document.getElementById("x" + divs[i].id);

   }
}

function Collapse()
{
   divs=document.getElementsByTagName("DIV");
   for (i=0;i<divs.length;i++) {
     divs[i].style.display="none";
     key=document.getElementById("x" + divs[i].id);

   }
}
function toggle(item,imgobj)
{	
		var imglast=imgobj.src.substr(imgobj.src.lastIndexOf("/")+1,imgobj.length);	
		if(imglast!="collapse.gif"){	
			imgobj.src="themes/defaulttheme/images/workflow/collapse.gif"
			flag="false";
		}
		else{		
			imgobj.src="themes/defaulttheme/images/workflow/expand.gif" 		
	 		flag="true";
		}	
		arguments.length=1
		if(arguments.length == 1){
	    	
	   		obj=document.getElementById(item);
	   		visible=(obj.style.display!="none");   
	   		key=document.getElementById("x" + item);   
		  		 if (visible){
		    	 obj.style.display="none";
		   		 } 
		   		 else{  
		      		obj.style.display="block";
		  		 }
	    }
	    else{
	    
		   		for (var i = 1; i < arguments.length; i ++)
		   		{
		   			obj=document.getElementById(arguments[i]);
		    		visible=(obj.style.display!="none");
		    		if (!visible)
		   			toggle(arguments[i]);
		  		 }
		}
  
}
function replace(string,text,by)
{
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
function edit(src1,src2)
{
		if(document.menuform.elements['PKval'].value==""){
			document.menuform.elements['PKval'].value=src2
		}
		else{
				if(confirm("Click ok to save changes else click cancel")){
					submitForm(document.menuform.elements['nextButtonid']);
				}
				else{
					var oRTE = document.getElementById('rte1').contentWindow.document;		
					var decoded_text=decodeBase64(src1);
					var output = escape(decoded_text);	
					output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
					output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");					
					oRTE.body.innerHTML = unescape(output);
					return
				}
		}
		
		var oRTE = document.getElementById('rte1').contentWindow.document;		
		var decoded_text=decodeBase64(src1);
		var output = escape(decoded_text);	
		output = output.replace("%3CP%3E%0D%0A%3CHR%3E", "%3CHR%3E");
		output = output.replace("%3CHR%3E%0D%0A%3C/P%3E", "%3CHR%3E");					
		oRTE.body.innerHTML = unescape(output);
}
function submitForm(src)
{	
	
	
	document.forms[0].RichTextVals.value = ''
	if(document.getElementById('infotable').style.display=="none")
		document.getElementById('viewInfoTableflag').value="false";
	else
		document.getElementById('viewInfoTableflag').value="true";
	
	var action;
		//var currentDate=document.getElementById('currentDate').value);
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
	
	if(src=='next')	{
	
				action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forCorr"
				document.menuform.elements['note_type'].value="MN"
				var tillcount=document.menuform.elements['conutMn'].value
				//alert(tillcount)
				var counter=0;	
				while(counter<tillcount){
					//alert('rte'+counter);
					var str=document.menuform.elements['rte'+eval(counter+1)].value 
					//alert(str);
					var converted_text=encodeBase64(str);
					//alert(converted_text);	
					var converted_text = replace(converted_text,'\n',' ');  
				  	document.menuform.elements['rte'+eval(counter+1)].value=converted_text;
				  	counter++;
				}	
				document.getElementById("fromSaveButton").value='no';			
			  	document.getElementById("menuform").method="post";
				document.getElementById("menuform").action=action;
				document.getElementById("Converted_text").value = '';
				document.getElementById("menuform").submit();
		
	}			
	else{
					
				
						if(src=="save")
						{
						document.getElementById("saveflag").value = "yes";
						document.getElementById("fromSaveButton").value='yes';
						}

						
			if(whichLinkClicked) {
			
							if(parent.frames['Target_frame'].document.forms[0]!=null)
							{
										var formname=parent.frames['Target_frame'].document.forms[0].name;
										
										
										if(eval("parent.document.getElementById('Target_frame').contentWindow.validateForm_"+formname+"()")){
									
											if(document.getElementById('isMark').value=="yes")
											{
											
												action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forCorr&isMark=yes"
											}
											else
											{
												if(("Inbox" == "${sendBackTo}") && (src.toLowerCase() != "Save".toLowerCase()))
												{
													action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forWorkList&sendBackTo=${sendBackTo}";
												}
												else
												{
													action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forCorr&sendBackTo=${sendBackTo}";
												}		
											}
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
																			
											window.frames['Target_frame'].document.forms[0].submit();
											document.getElementById("menuform").method="post";
											document.getElementById("menuform").action=action;
											document.getElementById("Converted_text").value = '';
											document.getElementById("menuform").submit();
												return true;
									
										}
							}
							else
							{
									if(document.getElementById('isMark').value=="yes")
											{
											
												action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forCorr&isMark=yes"
											}
											else
											{
												if(("Inbox" == "${sendBackTo}") && (src.toLowerCase() != "Save".toLowerCase()))
												{
													action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forWorkList&sendBackTo=${sendBackTo}";
												}
												else
												{
													action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forCorr&sendBackTo=${sendBackTo}";
												}		
											}
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
											
											document.getElementById("menuform").method="post";
											document.getElementById("menuform").action=action;
											document.getElementById("Converted_text").value = '';
											document.getElementById("menuform").submit();
											return true;
							}		
										
				}
				else {
							
								if(document.getElementById('isMark').value=="yes")
								{
								
									action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forCorr&isMark=yes"
								}
								else
								{
									if(("Inbox" == "${sendBackTo}") && (src.toLowerCase() != "Save".toLowerCase()))
									{
										action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forWorkList&sendBackTo=${sendBackTo}";
									}
									else
									{
										action="${contextPath}/hdiits.htm?actionFlag=showCorrespondence&moduleName=WorkList&menuName=forCorr&sendBackTo=${sendBackTo}";
									}		
								}
								
							
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
								//window.frames['Target_frame'].document.forms[0].submit();
							
								document.getElementById("menuform").method="post";
								document.getElementById("menuform").action=action;
								document.getElementById("Converted_text").value = '';
								document.getElementById("menuform").submit();	
								return true;
				}	
		}		
}
function forward()
{
	
	document.getElementById('wfAction').value="forward";
	document.getElementById("Converted_text").value = '';
	submitForm('');

}
function forwardjob()
{
	document.getElementById("Converted_text").value = '';
	document.getElementById("isMark").value="no";
	var corrId = document.getElementById('corrId').value;
	var urlstyle = 'width=650,height=250,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
	try
	{
	//window.open('hdiits.htm?actionFlag=getCorrNextNode&action=forward&isMark=no&corrId='+corrId, "test", urlstyle);
	window.open('hdiits.htm?actionFlag=getCorrNextNode&action=forward&isMark=no&corrId='+corrId+"&sendBackTo=${sendBackTo}", "wfaction_window", urlstyle);
	}
	catch(e)
	{
	alert("Exception");
	}
	//window.open('hdiits.htm?actionFlag=getCorrNextNode&action=forward&isMark=no&corrId='+corrId,'test',urlstyle);		
}

function returndoc()
{
	document.getElementById('wfAction').value="return";
	document.getElementById("Converted_text").value = '';
	submitForm('');
	
}
function returnjob()
{	
	var urlstyle = 'width=650,height=250,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
	document.getElementById("Converted_text").value = '';
	var corrId = document.getElementById('corrId').value;
	window.open('hdiits.htm?actionFlag=getCorrNextNode&action=return&&corrId='+corrId+"&sendBackTo=${sendBackTo}", "wfaction_window", urlstyle);
	//window.open('hdiits.htm?actionFlag=getCorrNextNode&action=return&&corrId='+corrId,'test',urlstyle);	
}


function Approvejob()
{
	document.getElementById("Converted_text").value = '';	
	document.getElementById('wfAction').value='Approve';
	var urlStyle ='width=500,height=350,toolbar=no,menubar=no,location=no,top=150,left=200,scrollbars=yes'; 
	if(confirm('<fmt:message key="WF.CorrApproveActMsg" bundle="${fmsLables}"></fmt:message>'))
	{
			var corrId = document.getElementById('corrId').value;
			displayModalDialog('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&approvFlag=2&corrId='+corrId+"&sendBackTo=${sendBackTo}", "test", urlStyle);
			//window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&approvFlag=2&corrId='+corrId,'test',urlStyle);
	}			
			
}

function returndown()
{	

	document.getElementById('wfAction').value="returnDown";
	document.getElementById("Converted_text").value = '';
	submitForm('');
}

function returndownjob()
{	
	var urlstyle = 'width=650,height=250,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
	document.getElementById("Converted_text").value = '';
	var corrId = document.getElementById('corrId').value;
	window.open('hdiits.htm?actionFlag=getCorrNextNode&action=returnDown&corrId='+corrId, "wfaction_window", urlstyle);
	//window.open('hdiits.htm?actionFlag=getCorrNextNode&action=returnDown&corrId='+corrId,'test',urlstyle);	
}

function show()
{
	document.getElementById('wfAction').value="forward";
	var urlStyle1 ='width=650,height=380,toolbar=no,menubar=no,scrollbars=yes,location=no,top=150,left=200'; 
	var corrId = document.getElementById('corrId').value;
	window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&fordisposeFlag=yes&corrId='+corrId, "wfaction_window", urlStyle1);
	//window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&fordisposeFlag=yes&corrId='+corrId,'test',urlStyle1);
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



	
function viewGraphicalPendency()
{
	var corrId = document.getElementById('corrId').value;
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0'; 
	displayModalDialog('hdiits.htm?actionFlag=viewPendencyDetails&corrId='+corrId+'&penType=1', "", urlStyle);
	//window.open('hdiits.htm?actionFlag=viewPendencyDetails&corrId='+corrId+'&penType=1','',urlStyle);
	
}
function viewTabularPendency()
{
	var corrId = document.getElementById('corrId').value;
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0'; 
	displayModalDialog('hdiits.htm?actionFlag=viewPendencyDetails&corrId='+corrId+'&penType=2', "", urlStyle);
	//window.open('hdiits.htm?actionFlag=viewPendencyDetails&corrId='+corrId+'&penType=2','',urlStyle);
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

			var t2=new Date('${corrcreateddate}');
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
					document.getElementById('txtduedateid').value=currdate;
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
function sendToAnyOne()
{
	//var urlStyle_depsrch ='toolbar=no,status=yes,menubar=no,location=no,width=800,height=600,scrollbars=yes'; 	
	checkfmsaction(12);
	document.getElementById('wfAction').value="forward";

	var loginlocId = document.getElementById('loginLocId').value;
	var retval=document.getElementById('ProceedFmsAction').value;
	if(retval=="yes")
	{
		var urlstyle="location=0,status=0,scrollbars=1,type=fullWindow,fullscreen";
		//displayModalDialog('hdiits.htm?actionFlag=DeptSearchData', "", urlStyle_depsrch);
		//var docWindow = window.open('hdiits.htm?actionFlag=DeptSearchData&methodFlag=forward','',urlstyle);
		var urlLoc ='hdiits.htm?viewName=wf-locationBranchSrch&methodFlag=forward&deptId=${departmentId}&SendAsCorr=no&loginLocId='+loginlocId;
		var docWindow = window.open(urlLoc,'',urlstyle);
		docWindow.resizeTo(screen.availWidth,screen.availHeight)
		docWindow.moveTo(0,0);  
	}
		
}


function status_onchange()
{
	var x = document.getElementById('status');
	var status=x.options[x.selectedIndex].title;
	
	if(status=='fms_Dispose')
	{
	
			if(confirm('<fmt:message key="WF.CorrDisposeAlert" bundle="${fmsLables}"></fmt:message>'))
			{
			 		
						var urlStyle1 ='width=830,height=450,toolbar=no,menubar=no,scrollbars=yes,location=no,top=150,left=100'; 
						var corrId = document.getElementById('corrId').value;
						displayModalDialog('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&disposedFlag=yes&corrId='+corrId, "test", urlStyle1);
						//window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&disposedFlag=yes&corrId='+corrId,'test',urlStyle1);
					
					
			}
	}
}
function brSub_onchange()
{
	var x = document.getElementById('brSub');
	var url=x.options[x.selectedIndex].title;
	document.getElementById('Target_frame').src=url
	window.frames['workflow_tab'].document.getElementById('tab3_Link').style.display='';
	
	window.frames['workflow_tab'].document.getElementById('tab3_Link').className="selected";
	window.frames['workflow_tab'].document.getElementById('tab2_Link').className="unselected";
	
}
function rejectCorr()
{
	document.getElementById("Converted_text").value = '';	
	document.getElementById('wfAction').value='Approve';
	document.getElementById('reject').value='yes';
	var urlStyle ='width=500,height=350,toolbar=no,menubar=no,location=no,top=150,left=200,scrollbars=yes'; 
	if(confirm('<fmt:message key="WF.CorrRejectActMsg" bundle="${fmsLables}"></fmt:message>'))
	{
			var corrId = document.getElementById('corrId').value;
			displayModalDialog('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&rejectflag=yes&approvFlag=2&corrId='+corrId, "test", urlStyle);
			//window.open('hdiits.htm?actionFlag=getRolePostHierarchy&Passingflag=corr&approvFlag=2&corrId='+corrId,'test',urlStyle);
	}		
}

function sendCorrToMany()
{

	
	//var urlStyle_depsrch ='toolbar=no,status=yes,menubar=no,location=no,width=800,height=600,scrollbars=yes'; 	
	var urlstyle="location=0,status=0,scrollbars=1,width=screen.availWidth,height=screen.availHeight";
	//displayModalDialog('hdiits.htm?actionFlag=DeptSearchData', "", urlStyle_depsrch);
	var docWindow = window.open('hdiits.htm?actionFlag=DeptSearchData&methodFlag=sendToMany','',urlstyle);
	docWindow.resizeTo(screen.availWidth,screen.availHeight)
	docWindow.moveTo(0,0);  
		
}
function sendToMany()
{
	document.getElementById("Converted_text").value = '';	
	document.getElementById('wfAction').value='sendToMany';	
	submitForm('');
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
		
		       corrId=document.getElementById('corrId').value;
		       var url = "${contextPath}/hdiits.htm?actionFlag=FMS_ADDCORRINTOFILE&actionId="+actionId+"&corrId="+corrId;
		       
			
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
function printNotings()
{
	var corrId = document.getElementById('corrId').value;
	var urlStyle = 'toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,type=fullWindow,fullscreen'; 
	displayModalDialog('hdiits.htm?actionFlag=FMS_viewAllNotings&corrId='+corrId,"printnoting", urlStyle);
	
}
function setdisposeinfotable()
{

	if(document.getElementById('disposeinfotable').style.display=="none")
	{		
			document.getElementById('disposeinfotable').style.display='';
			document.getElementById('showCorrDisposeInfoLink').innerHTML='<fmt:message key="WF.HideDisposeInfo" bundle="${fmsLables}"></fmt:message>';


			document.getElementById('infotable').style.display='none';
			document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.ShowCorrInfo" bundle="${fmsLables}"></fmt:message>';
			
			
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
				document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.HideCorrInfo" bundle="${fmsLables}"></fmt:message>';

				document.getElementById('disposeinfotable').style.display='none';
				document.getElementById('showCorrDisposeInfoLink').innerHTML='<fmt:message key="WF.ShowDisposeInfo" bundle="${fmsLables}"></fmt:message>';
		}
		else
		{
				document.getElementById('infotable').style.display='none';
				document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.ShowCorrInfo" bundle="${fmsLables}"></fmt:message>';
		}
}
</script>
<hdiits:form name="menuform" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="currentDate" id="currentDate" default="${currentDateJs}"/>
<hdiits:hidden name="corrId" default="${CorrVO.corrId}" />
<hdiits:hidden name="pk_FrmCorrMst" default="${CorrVO.corrId}" />
<hdiits:hidden name="saveflag" default="no" />
<hdiits:hidden name="sender" default="no" />
<hdiits:hidden name="DecisionFlag" default="corr" />
<hdiits:hidden name="ProceedFmsAction" default="yes" />
<hdiits:hidden name="loginLocId" default="${resultMap.loginLocId}"/>
<!--  Convert to hdiits:hidden and move the js function to js file...Jaspal  -->
<input type="hidden" name="Converted_text" />
<input type="hidden" name="postId_Hidden" />
<input type="hidden" name="note_counter" value="<%=notecnt%>" />
<input type="hidden" name="note_type" />
<hdiits:hidden name="PKval" default="${resultMap.pkval}" />
<input type="hidden" name="tabcnt" value="3" />
<hdiits:hidden name="postList" />
<input type="hidden" name="conutMn" value="1" />
<input type="hidden" name="RichTextVals" />
<input type="hidden" name="RichTextObj" />
<hdiits:hidden name="wfAction" default="temp" />
<hdiits:hidden name="loggedInUserId" default="${resultMap.LoggedinUser}" />
<hdiits:hidden name="UserIdFromEmpSrch" />
<hdiits:hidden name="wfcorrId_hidden" />
<hdiits:hidden name="wfcorrNo_hidden" />
<hdiits:hidden name="isMark" default="no" />
<hdiits:hidden name="markedList" />
<hdiits:hidden name="infotabflag" default="${resultMap.infotabflag}" />
<hdiits:hidden name="fromCommonHomePage" default="${resultMap.fromCommonHomePage}"/>
<input type="hidden" name="tabclickcnt" />
<input type="hidden" name="viewInfoTableflag" />
<hdiits:hidden name="roleId_Hidden" />
<hdiits:hidden name="wfWindowName" />
<hdiits:hidden name="disposedFlag" />
<input type="hidden" name="isNormalHierachySelected"/>
<input type="hidden" name="isDocAlreadyMarked"/>
<input type="hidden" name="isDocAlreadyMarked"/>
<hdiits:hidden name="reject"  default="no"/>
<hdiits:hidden name="jobOwnerPostId"  default="${resultMap.jobOwnerPostId}" />
<hdiits:hidden name="disposeremarks"  />
<hdiits:hidden name="disposetype"  />
<hdiits:hidden name="disposeclass"  />
<hdiits:hidden name="saveNoting" default="${resultMap.saveNoting}"/>
<hdiits:hidden name="saveNotingId" default="${resultMap.saveNotingId}"/>
<hdiits:hidden name="fromSaveButton"  default="${resultMap.fromSaveButton}"/>
<hdiits:hidden name="forwardFromSupportHandler" default="no"/>
<hdiits:hidden name="fromwithinHiearchy" default="no"/>
<table width="100%" border="1" bordercolor="black"  cellspacing="0" cellpadding="2" >
<tr>
	<td>
	

<div>
<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp" />
</div>
<hdiits:fieldGroup titleCaptionId="WF.CorrInfo" bundle="${fmsLables}" >
<div>
	<center>
	<a href="#" onclick="setinfotable()" id="showFileInfoLink"><u> <fmt:message key="WF.ShowCorrInfo" bundle="${fmsLables}"></fmt:message></u></a>
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
<table  style="display:none" id="infotable">

		<tr>
		
			<td style="border: none"><hdiits:caption captionid="WF.CrNo"	bundle="${fmsLables}" /></td>

			<td style="border: none">
			<hdiits:text name="crrCat" readonly="true" default="${CorrVO.corrNo}" size="31"/>
			</td>
			<td style="border: none"><hdiits:caption captionid="WF.Ref"	bundle="${fmsLables}" /></td>
			<td style="border: none"><hdiits:text name="refNo" size="31" default="${CorrVO.corrRefNo}" readonly="true" captionid="WF.Ref" bundle="${fmsLables}" /></td>
			
			
					
			
			<td style="border: none">
			<hdiits:caption captionid="WF.BrSub" bundle="${fmsLables}" />
			</td>
			
			<c:if test="${sendascorr ne  'yes'}">
			<td style="border: none;"  >
				<hdiits:text name="subtxt" readonly="true" default="${resultMap.DocName}"/>
			</td>
			</c:if>
			
			<c:if test="${sendascorr eq  'yes'}">
			<td style="border: none;"  >
			<c:choose>
				<c:when test="${not empty  DocList}">
								<hdiits:select name="brSub" mandatory="true" validation="sel.isrequired" captionid="WF.BrSub" bundle="${fmsLables}" sort="false" onchange="brSub_onchange()">
									<hdiits:option value="-1" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
										<c:forEach var="DocList" items="${DocList}">
											
												<option value="${DocList.wfDocMst.docId}" title="${DocList.wfDocMst.url}" >
												<c:out value="${DocList.docName}"/></option>
											
							 			</c:forEach>	
								</hdiits:select>
				</c:when>
			</c:choose>	
			</td>
			</c:if>
		</tr>
		<tr>


			<td style="border: none"><hdiits:caption captionid="WF.Desc" bundle="${fmsLables}" />
			 <hdiits:caption captionid="WF.LtSub" bundle="${fmsLables}" /></td>
			<td style="border: none"><hdiits:textarea name="lettersubDesc"
				cols="35" maxlength="128" default="${CorrVO.corrDesc}" />
			<hdiits:a href="#" onclick="openCompDesc('lettersubDesc','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>	
				</td>

			<td><hdiits:caption captionid="WF.Post" bundle="${fmsLables}" />
			</td>
			<td><hdiits:text name="userPost" size="15"
				default="${LoginUserPostName}" readonly="true" captionid="WF.Post"
				bundle="${fmsLables}" /></td>

			<td align="left"><hdiits:caption captionid="WF.LOCNAME"
				bundle="${fmsLables}" /></td>
			<td><hdiits:text name="userLoc" size="15"
				default="${CmnLocationMstForCrtLoc.locName}" readonly="true"
				captionid="WF.LOCNAME" bundle="${fmsLables}" /></td>
		</tr>
		<tr>
			<td>
			<hdiits:caption captionid="WF.Notings" bundle="${fmsLables}"/>
			</td>
			<td>
					<hdiits:textarea  name="notings" cols="25" maxlength="128" readonly="true" default="${CorrVO.notingDesc}"/>
					<hdiits:a href="#" onclick="openCompDesc('notings','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
			</td>
			<td>
					<hdiits:caption captionid="WF.Encl" bundle="${fmsLables}"/>
			</td>
			<td>
					
					<hdiits:textarea  name="enclousure" cols="25" maxlength="128" readonly="true" default="${CorrVO.enclosureDesc}"/>
					<hdiits:a href="#" onclick="openCompDesc('enclousure','9900')" caption=""><hdiits:image source="images/plus.gif"/></hdiits:a>
				
			
			</td>	
			
			<td >	<hdiits:caption captionid="WF.TypeOfCorr" bundle="${fmsLables}"/>
			</td>
				
			<td>
					<hdiits:text name="txtcorrtype" readonly="true" default="${CorrVO.cmnLookupMstByTypeOfCorr.lookupDesc}"></hdiits:text>
			</td>

	   </tr>
	   
	   
		<tr>
			<td><b><hdiits:caption captionid="WF.Status" bundle="${fmsLables}" /></b></td>
			<td><hdiits:select size="1" name="status" sort="false" onchange="status_onchange()" id="status" readonly="${disableflag}">
				<c:forEach items="${filestatuslist}" var="filestatusLookup">
					<c:if test="${filestatusLookup.lookupName eq (CorrVO.cmnLookupMstByCorrStatus.lookupName)}">
						<option value='<c:out value="${filestatusLookup.lookupId}"/>'selected="true" title="${filestatusLookup.lookupName}">
						<c:out	value="${filestatusLookup.lookupDesc}" /></option>
					</c:if>
					<c:if test="${filestatusLookup.lookupName ne (CorrVO.cmnLookupMstByCorrStatus.lookupName)}">
						<option value='<c:out value="${filestatusLookup.lookupId}"/>' title="${filestatusLookup.lookupName}">
						<c:out value="${filestatusLookup.lookupDesc}" /></option>
					</c:if>
				</c:forEach>
			</hdiits:select></td>
			<td style="border: none"><b><hdiits:caption
				captionid="WF.Confidentiality" bundle="${fmsLables}" /></b></td>
			<td><hdiits:select name="Confidentiality" sort="false" readonly="${disableflag}">
				<c:forEach items="${Confidentialitylist}"
					var="ConfidentialityLookup">
					<c:if
						test="${ConfidentialityLookup.lookupId ne (CorrVO.cmnLookupMstByConfidentiality.lookupId)}">
						<option value='<c:out value="${ConfidentialityLookup.lookupId}"/>'>
						<c:out value="${ConfidentialityLookup.lookupDesc}" /></option>
					</c:if>
					<c:out value="${fmsFileVo.cmnLookupMstByConfidentiality.lookupId}" />
					<c:if
						test="${ConfidentialityLookup.lookupId eq (CorrVO.cmnLookupMstByConfidentiality.lookupId)}">
						<option value='<c:out value="${ConfidentialityLookup.lookupId}"/>'
							selected="true"><c:out
							value="${ConfidentialityLookup.lookupDesc}" /></option>
					</c:if>
				</c:forEach>
			</hdiits:select></td>

			<!-- <td><hdiits:caption captionid="WF.Stage" bundle="${fmsLables}" />
			</td>
			<td><hdiits:select id="stageid" name="stage" sort="false">
				<c:forEach items="${filestagelist}" var="filestagelistLookup">
					<c:if
						test="${filestagelistLookup.lookupId ne (CorrVO.cmnLookupMstByCorrStage.lookupId)}">
						<option value='<c:out value="${filestagelistLookup.lookupId}"/>'>
						<c:out value="${filestagelistLookup.lookupDesc}" /></option>
					</c:if>
					<c:if
						test="${filestagelistLookup.lookupId eq (CorrVO.cmnLookupMstByCorrStage.lookupId)}">
						<option value='<c:out value="${filestagelistLookup.lookupId}"/>'
							selected="true"><c:out
							value="${filestagelistLookup.lookupDesc}" /></option>
					</c:if>
				</c:forEach>

			</hdiits:select></td> -->
			<td width="10%"><hdiits:caption captionid="WF.LtDate" bundle="${fmsLables}" /></td>
			<td width="10%">
			<hdiits:dateTime name="letterDate" captionid="WF.LtDate" bundle="${fmsLables}" validation="txt.isrequired" mandatory="true" default="${CorrVO.corrDated}" disabled="${disableflag}"></hdiits:dateTime>
			</td>

		</tr>
		<tr>
			<td><b><hdiits:caption captionid="WF.Priority" bundle="${fmsLables}" /></b>
			</td>
				
			<td>
		
				<c:if test="${CorrVO.cmnLookupMstByPriority.lookupShortName ne '5#DateSet'}">
				
						<hdiits:select id="priorityId" name="priority" sort="false"	onchange="priority_onchange()" readonly="${disableflag}">
						<c:forEach items="${Prioritylist}" var="PriorityLookup">
						
							
						<c:if test="${PriorityLookup.lookupName ne (CorrVO.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>' title="${PriorityLookup.lookupShortName}">
						<c:out value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>
						<c:if test="${PriorityLookup.lookupName eq (CorrVO.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>'title="${PriorityLookup.lookupShortName}" selected="true">
						<c:out	value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>		
							
							
						</c:forEach>
						</hdiits:select>
				</c:if>
				<c:if test="${CorrVO.cmnLookupMstByPriority.lookupShortName eq '5#DateSet'}">
				<hdiits:select id="priorityId" name="priority" sort="false" >
						<c:forEach items="${Prioritylist}" var="PriorityLookup">
						
							
						
						<c:if test="${PriorityLookup.lookupName eq (CorrVO.cmnLookupMstByPriority.lookupName)}">
						<option value='<c:out value="${PriorityLookup.lookupId}"/>'title="${PriorityLookup.lookupShortName}" selected="true">
						<c:out	value="${PriorityLookup.lookupDesc}" /></option>
						</c:if>		
							
							
						</c:forEach>
				</hdiits:select>		
				</c:if>
				
				</td>
			<td><hdiits:caption captionid="WF.DueDate" bundle="${fmsLables}" />
			</td>

			<td style="border: none" id="duedateid1">
				<hdiits:text id="txtduedateid" name="txtduedate" size="10" readonly="true" captionid="WF.DueDate" bundle="${fmsLables}" /></td>
			
			<td style="border: none;display: none;" id="duedateid2">
				<hdiits:dateTime name="txtsetDate" captionid="WF.DueDate" bundle="${fmsLables}" maxvalue=""/>
			</td>
			<td style="border: none"><hdiits:caption captionid="WF.CorrCat"	bundle="${fmsLables}" /></td>

			<td style="border: none">
			<hdiits:text name="crrCat1" readonly="true" default="${CorrVO.cmnLookupMstByCorrCtgry.lookupDesc}"/>
			</td>	
			
		</tr>

</table>
</hdiits:fieldGroup>

<iframe name="workflow_tab"	src="hdiits.htm?viewName=wf-showCorrespondsTabs" width="100%" height="35" frameborder="0" scrolling="no"> </iframe>
	<table width="100%" class="tabcontentstyle">
		<tr>
			<td width="20%" valign="top" id="t1">
				<hdiits:fieldGroup titleCaptionId="WF.Notings" bundle="${fmsLables}">
				
			<table cellspacing="0" cellpadding="2">
				<tr>
					<td>
					<hdiits:button readonly="${disableflag}" type="button" name="nextButton"	id="nextButtonid" captionid="WF.NextNoting" bundle="${fmsLables}" onclick="submitForm('next');" /> 
					<hdiits:button type="button"  name="" id="expandbutton" captionid="WF.Expand"  bundle="${fmsLables}"  onclick="expand_onclick();"  />
					<hdiits:button name="btnprint" type="button" captionid="WF.ViewAllNoting" bundle="${fmsLables}" onclick="printNotings()"/>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td valign="top" width="10%">
					<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp" >
						<jsp:param name="richTextName" value="<%=richTextCounter++%>" />
					</jsp:include></td>
				</tr>
				<tr>
					<td valign="top" width="10%">
					<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp">
						<jsp:param name="attachmentName" value="<%=newValue%>" />
						<jsp:param name="formName" value="menuform" />
						<jsp:param name="attachmentType" value="Document" />
						
						<jsp:param name="attachmentTitle" value="${Attach}" />
					</jsp:include></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td>
						<table width="100%" border="1" bordercolor="green" cellpadding="2" cellspacing="0">
						
						<%
						int i = 1, k = 0;
						%>

						<c:forEach var="Noting" items="${Notings}">
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


								<table border="0" width="100%" cellspacing="0" cellpadding="2" >
									<tr>
										<td width="10%">
										<script type="text/javascript">	
											var text='${note}';							
											var decoded_text=decodeBase64(text);								
											document.menuform.elements['Converted_text'].value=decoded_text								
											var text=document.menuform.elements['Converted_text'].value											
										</script> 
								<%
								try
								{
						 			ResultObject resObj;
						 			resObj = (ResultObject) request.getAttribute("result");
						 			Map resValueMap = (HashMap) resObj.getResultValue();
						 			String[] noteTypeList;
						 			String pkval,disableflagvar;
						 			List noteing = new ArrayList();
						 			noteTypeList = (String[]) resValueMap.get("NoteTypeList");
						 			pkval = (String) resValueMap.get("pkval").toString();
						 			List<FmsCorrNotings> corrNotingData = (List<FmsCorrNotings>) resValueMap.get("FmsCorrNotings");
						 			noteing = (List) resValueMap.get("Notings");
						 			disableflagvar=resValueMap.get("disableflag").toString();
						 			request.setAttribute("FmsCorrNotings",corrNotingData);
						 			if (noteTypeList[note_type_cnt++] == "MN" && (disableflagvar.equalsIgnoreCase("false")))
		 							{
		 								flag = true;
 								%> 
						 				<script type="text/javascript">
												document.menuform.elements['note_counter'].value=<%=noteing.size()+1%>																		
										</script> 
									<%
 											newValue = "DocumentAttachment"+ attachmentCounter++;
	 								%> 
				 			 			<jsp:include page="/WEB-INF/jsp/workflow/RichText.jsp">
										<jsp:param name="richTextName" value="<%=richTextCounter%>" />
										</jsp:include>
							 			<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp">
															<jsp:param name="attachmentName" value="<%=newValue%>" />
															<jsp:param name="formName" value="menuform" />
															<jsp:param name="attachmentType" value="Document" />
															
															<jsp:param name="attachmentTitle" value="${Attach}" />
										</jsp:include>
									<%
					 				}
 					 				else
 					 				{
 									%> 
								 			<script type="text/javascript">
														document.writeln(text);
											</script>
 									<%
 									}
 								} 						
								catch (Exception e) 
								{
 										e.printStackTrace();
 								}
 								%>
										</td>
									</tr>
									<tr>
										<td>
										<%if(!flag){%>
										
										<h3>Attachments</h3>
										
										<%}%>
										<%if(!flag){
											
													try
												{
													Hashtable attachmentTable = new Hashtable();
													ResultObject resObj;
													resObj = (ResultObject) request.getAttribute("result");
													Map resValueMap = (HashMap) resObj.getResultValue();
													attachmentTable = (Hashtable) resValueMap.get("AttachmentTable");
													List objAttachmentList = (List) attachmentTable.get("AttachmentUrlList" + k);
													List attachmentNamesList = (List) resValueMap.get("AttachmentNames");
													List<FmsCorrNotings> corrNotingsList = (List) resValueMap.get("FmsCorrNotings");
													if(corrNotingsList.get(notingCounter++).getCmnAttachmentMst() != null) 
													{
														for (int m = 0; m < objAttachmentList.size(); m++)
														{
										%> 
														
														<hdiits:a href="<%=(String)objAttachmentList.get(m)%>" caption="<%=(String)attachmentNamesList.get(counter++)%>"></hdiits:a>
														<br>
										<%
														}
														k++;
													}
												}
												catch(Exception e)
												{
													e.printStackTrace();
												}
										}
 												if(flag)
 												{
 													flag = false;
										%>
													<script>
													document.menuform.elements['conutMn'].value=<%=richTextCounter%>;
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
												}
										%>													
										</td>
									</tr>
								</table>
						</c:forEach>
					</table>

					</td>
				</tr>
			</table>
			</hdiits:fieldGroup>
			</td>

			
			<td width="80%" valign="top" id="t2">
			<hdiits:fieldGroup titleCaptionId="WF.BasicInfo" bundle="${fmsLables}" >
								<table width="100%" id="tab2">
								<tr>
									<td width="100%">
										<hdiits:button type="button" name="expandbutton2" id="expandbutton2" captionid="WF.Expand"  bundle="${fmsLables}" onclick="expand_onclick2()"  />
									</td>
								</tr>	
								<tr>
									<td width="100%">
											<iframe align="top" id="myFrame" name="Target_frame" src="${resultMap.basicInfoUrl}" width="100%"	height="670" scrolling="auto" frameborder="0"></iframe>
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

	window.frames['workflow_tab'].document.getElementById("content").style.paddingLeft=0
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
			   

				var intervalid;
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
				document.forms[0].RichTextVals.value = '';
				document.forms[0].RichTextObj.value = '';
				document.getElementById('txtduedateid').value= '${formateduedate}';
				
				window.clearInterval(intervalid);
			    intervalid=window.setInterval("settablink();",5000);
			    window.clearInterval(tabinterval);
			    tabinterval=window.setInterval("setTabLinkUrl();",5000);
				
				document.forms[0].wfcorrId_hidden.value = '${CorrVO.corrId}';
   		        document.forms[0].wfcorrNo_hidden.value = '${CorrVO.corrNo}';
   		        
   		       
		        if(headerinfo=="true")
				{
					document.getElementById('infotable').style.display='';
					document.getElementById('showFileInfoLink').innerHTML='<fmt:message key="WF.HideCorrInfo" bundle="${fmsLables}"></fmt:message>';
				}
					
					document.getElementById('wfWindowName').value=window.opener.parent.name;
					
					if('${CorrVO.cmnLookupMstByPriority.lookupShortName}'=='5#DateSet')
					{
							document.getElementById('duedateid2').style.display='';
							document.getElementById('duedateid1').style.display="none";
							document.getElementById('txtsetDate').value='${formateduedate}';
							
					}		
							
			
			
		
		
		
		
		
			
</script>
<script>
var stage='${CorrVO.cmnLookupMstByCorrStage.lookupName}'

if(stage=="fms_Approve")
{
		document.getElementById('nextButtonid').disabled='true';	
}

var corrDisposeFlag='${corrDisposeFlag}';
if(corrDisposeFlag=="true")
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
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
