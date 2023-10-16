
<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables"	scope="request" />
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="wfRefList"	value="${resultMap.wfRefList}"></c:set> 

<hdiits:form name="frmWfType" id="frmWfType" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="insertType" />
<script>
	var srno=0;
	var Counter, selPostorRole;
	var getpostid, getroleid, getlevelid;
	var postval, roleval, refidval,levelval;
	var delpostid=new Array();	
	var delroleid=new Array();
	var dellevelid=new Array();
	var chkpostid=new Array();
	var chklevelid=new Array();	
	var chkroleid=new Array();
	var booleanArrayPost=new Array();
	var booleanArrayRole=new Array();		
	var buttonvalP;
	var buttonvalR;
	var b=new Boolean(true);		
</script>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="WF.HEADING1" bundle="${wfLables}" />
			</a>
		</li>
	</ul>
</div>
<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent">
	<br><br>
	<fieldset style="background: #eeeeee;padding: 2px;">
	<b>
		<legend style="font-weight:bold;"><hdiits:caption captionid="WF.HIERARCHYTYPE" bundle="${wfLables}" /></legend>
	</b>
	<table align="center" border="0" width="40%" class="tabtable">			
		<tr>
			<td align="center" class="fieldLabel" width="25%" >
				<hdiits:caption	captionid="WF.HIERARCHYTYPE" bundle="${wfLables}" />
			</td>
			<td align="left" width="75%">				
				<hdiits:radio name="radiobtn"  id="rdPost" value="P"  captionid="WF.POSTHIR" bundle="${wfLables}"  validation="sel.isradio" onclick="javascript:show()" />
				<hdiits:radio name="radiobtn"  id="rdRole" value="R" captionid="WF.ROLEHIR" bundle="${wfLables}"  validation="sel.isradio" onclick="javascript:show()" />
		    </td>
		</tr>
	</table>
	<br>
	</fieldset>
	
	<br>
	<b><u><hdiits:caption captionid="WF.HEADING1" bundle="${wfLables}" /></u></b>
	<br><br>
	<hr>			
	<table align="left">
	<tr><td>
		<table align="left" id="post" border="0" style="display:none;"> 
			<tr>
				<td width="100px"><hdiits:caption captionid="WF.POST" bundle="${wfLables}" /></td>				
				<td align="left"><hdiits:number name="txtpost" captionid="WF.POST" bundle="${wfLables}" condition="chkconditionrole()" validation="txt.isrequired" mandatory="true"  maxlength="20" onblur="ajaxfunctionPost()"/></td>
			</tr>
		</table>
	</td></tr>
	<tr><td>	
		<table align="left" id="role" border="0" style="display:none;"> 
			<tr>
				<td width="100px"><hdiits:caption captionid="WF.ROLE" bundle="${wfLables}" /></td>
				<td align="left"><hdiits:number name="txtrole" captionid="WF.ROLE" bundle="${wfLables}" condition="chkconditionpost()" validation="txt.isrequired" mandatory="true"  maxlength="12" onblur="ajaxfunctionRole()"/></td>
			</tr>
		</table>
	</td></tr>
	<tr><td>
	<table align="left" id="table1" border="0"> 	
	<tr>
		<td width="100px"><hdiits:caption captionid="WF.LEVELID" bundle="${wfLables}" /></td>
		<td align="left"><hdiits:number name="txtlevelid" captionid="WF.LEVELID" bundle="${wfLables}" condition="isCompulsory()" mandatory="true" validation="txt.isrequired" maxlength="3"/></td>		
	</tr>		
	<tr>
		<td width="100px"><hdiits:caption captionid="WF.HIRREFID" bundle="${wfLables}" /></td>		
		<td align="left">
				<hdiits:select name="selrefid" id="selrefid1" captionid="WF.HIRREFID" validation="sel.isrequired" bundle="${wfLables}" mandatory="true" onchange="ajaxFunction()">
					<option value="0">Select</option>
					<c:forEach  items="${wfRefList}" var="wflookup">
							<option value='<c:out value="${wflookup.hierachyRefId}"/>' selected="true">
								<c:out value="${wflookup.referenceName}" />
							</option>								
					</c:forEach>
				</hdiits:select>					
				<hdiits:button type="button" value="  ADD  " name="add" id="addBtn" onclick="displayTable()"/>
		 </td>	
	</tr>		
	</table>
	</td></tr>
	<tr><td>
		<table id="hirTypeDetails" align="center" style="display:none;width: 50%;" border="1">	
			<tr>	
				<td align="center" class="datatableheader"><b>SrNo</b></td>	
				<td id="rolecol" style="display:none" align="center" class="datatableheader"><b>Role Id</b></td>
				<td id="postcol" style="display:none" align="center" class="datatableheader"><b>Post Id</b></td>
				<td align="center" class="datatableheader"><b>LevelId</b></td>	
				<td align="center" class="datatableheader">&nbsp;&nbsp;&nbsp; </td>
			</tr>
		</table>
	</td></tr>	
	</table>
		
	<table id="heading1" border="0" align="center" style="display:none">
		<br>
		<tr><th>Post Based Hierarchy Records!!!</th></tr>
	</table>
	<table id="heading2" border="0" align="center" style="display:none">
		<br>
		<tr><th>Role Based Hierarchy Records!!!</th></tr>		
	</table>			
	<table id="dispTable" border="2" width="30%" style="display:none" align="center">
		<tr><th class="datatableheader">Id</th>
		<th class="datatableheader">LevelId</th></tr>
	</table>		

	<hdiits:hidden id="postmsg" name="alerthid" captionid="WF.POSTMSG" bundle="${wfLables}" />	
	<hdiits:hidden id="rolemsg" name="alerthid" captionid="WF.ROLEMSG" bundle="${wfLables}" />		
	<hdiits:hidden  name="txtCounter" default="0" />	
	<hdiits:jsField name="jsAddTab" jsFunction="addTab()" />		
</div>
<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script language="javascript"><!--
	function show()
	{				
		if(document.forms[0].radiobtn[0].checked==true)
			{	
				document.getElementById('postcol').style.display = '';	
				document.getElementById('rolecol').style.display = 'none';			
				document.getElementById('post').style.display='';	
				document.getElementById('role').style.display='none';			
			}
		else if(document.forms[0].radiobtn[1].checked==true)
			{
   				document.getElementById('rolecol').style.display = '';	
   				document.getElementById('postcol').style.display = 'none';
   				document.getElementById('role').style.display='';
   				document.getElementById('post').style.display='none';		
			}		
	}
	
	function displayTable()
	{		
			postval=document.getElementById('txtpost').value ;
		    refidval=document.getElementById('selrefid').value;
		    levelval=document.getElementById('txtlevelid').value;		  
			buttonvalP=document.getElementById('rdPost');
		  	buttonvalR=document.getElementById('rdRole');	
		  		  	
		    if(document.getElementById('selrefid').value !='0')
		    { 		    
		    	document.getElementById('hirTypeDetails').style.display = '';	
				if(document.forms[0].radiobtn[0].checked==true)
				{		
					if(postval!= '' && levelval!= '' &&  refidval!= '0')
					{	
							 getpostid=postval;
							 getlevelid=levelval;
							 buttonvalR.disabled=false;	
							 if(doProcessingPost(getpostid, getlevelid, chkpostid, chklevelid, booleanArrayPost)) 
							 {		 
							 		 srno++;							
									 var b=document.getElementById('hirTypeDetails').insertRow();
									 var col1=b.insertCell(0);				
									 var col2=b.insertCell(1);
									 var col3=b.insertCell(2);	
									 var col4=b.insertCell(3);
									 col1.align="center";
									 col2.align="center";
									 col3.align="center";	
									 col4.align="center";
								 	 Counter=document.forms[0].txtCounter.value;														
								     chkpostid[eval(Counter)]=postval;
								     chklevelid[eval(Counter)]=levelval;
								     booleanArrayPost[Counter]="Yes";
								     Counter++;								     
									 col1.innerHTML=srno;
						    		 col2.innerHTML=document.frmWfType.txtpost.value + '<input type="hidden" name="txtHidden_post" value=" ' + document.frmWfType.txtpost.value + ' " >';
						 			 col3.innerHTML=document.frmWfType.txtlevelid.value + '<input type="hidden" name="txtHidden_levelid" value=" ' + document.frmWfType.txtlevelid.value + ' " >';
									// col4.innerHTML= "<a href='#' style=\"cursor:hand\" onClick=\"deleteRow(this)\"><hdiits:caption captionid="WF.DELETE" bundle="${wfLables}"/></a>";											
									 var delString = "<a href='#' style=\"cursor:hand\" onClick=\"deleteRow(this" + "," + postval + "," +levelval+ ")\"><hdiits:caption captionid="WF.DELETE" bundle="${wfLables}"/></a>";											
									 col4.innerHTML = delString;								
							 }		
							 
							 		document.getElementById('txtpost').value='';
							 		document.getElementById('txtlevelid').value='';
								 	document.forms[0].txtCounter.value=Counter;		
					}	
					else
					{
						alert("<fmt:message key='WF.ALERT' bundle='${wfLables}'/>");
						document.getElementById('txtpost').focus();
					}	
				}
				else if(document.forms[0].radiobtn[1].checked==true)
				{
					roleval=document.getElementById('txtrole').value;
					refidval=document.getElementById('selrefid').value
					levelval=document.getElementById('txtlevelid').value;
					if(roleval != '' &&  levelval!= '' && refidval != '0')
					{
						getroleid=roleval;
						getlevelid=levelval;
						buttonvalP.disabled=false;											
						if(doProcessingRole(getroleid, getlevelid, chkroleid, chklevelid, booleanArrayRole)) 
						{							    
								srno++;						
								var b=document.getElementById('hirTypeDetails').insertRow();
								var col1=b.insertCell(0);				
								var col2=b.insertCell(1);
								var col3=b.insertCell(2);
								var col4=b.insertCell(3);
								Counter=document.forms[0].txtCounter.value;														
								chkroleid[eval(Counter)]=roleval;
								chklevelid[eval(Counter)]=levelval;
								booleanArrayRole[Counter]="Yes";															
								Counter++;	
								col1.align="center";
								col2.align="center";
								col3.align="center"; 
								col4.align="center"; 
								col1.innerHTML=srno;
			    			    col2.innerHTML=document.frmWfType.txtrole.value + '<input type="hidden" name="txtHidden_role" value=" ' + document.frmWfType.txtrole.value + ' " >';
			 					col3.innerHTML=document.frmWfType.txtlevelid.value + '<input type="hidden" name="txtHidden_levelid" value=" ' + document.frmWfType.txtlevelid.value + ' " >';
								//col4.innerHTML= "<a href='#' style=\"cursor:hand\" onClick=\"deleteRow(this)\"><hdiits:caption captionid="WF.DELETE" bundle="${wfLables}"/></a>";											
								var delString = "<a href='#' style=\"cursor:hand\" onClick=\"deleteRow(this" + "," + roleval + "," +levelval+ ")\"><hdiits:caption captionid="WF.DELETE" bundle="${wfLables}"/></a>";											
								col4.innerHTML = delString;	
						}
								document.getElementById('txtrole').value='';
								document.getElementById('txtlevelid').value='';
								document.forms[0].txtCounter.value=Counter;		
					}	
					else
					{
						alert("<fmt:message key='WF.ALERT' bundle='${wfLables}'/>");
						document.getElementById('txtrole').focus();
					}	
				}	
			}
			else
			{
					alert("<fmt:message key='WF.ALERT' bundle='${wfLables}'/>");
					document.getElementById('selrefid').focus();
			}				
			
			if(document.forms[0].radiobtn[0].checked==true)	
			{
				buttonvalR.disabled=true;
			}	
			else if(document.forms[0].radiobtn[1].checked==true)									
			{
				buttonvalP.disabled=true;
			}					
	}

	var xyz=0;	
	var pqr=0;		
	function doProcessingPost(getpostid, getlevelid, chkpostid, chklevelid, booleanArrayPost)	
	{					
		for(i=0; i<chkpostid.length; i++)
		{	
			if(chkpostid[i]==getpostid && chklevelid[i]==getlevelid)
			{					
				if(booleanArrayPost[i] == 'Yes')
				{
					alert("<fmt:message key='WF.POST' bundle='${wfLables}'/> "+ getpostid + " <fmt:message key='WF.LEVELID' bundle='${wfLables}'/> "+getlevelid+ " <fmt:message key='WF.EXISTS' bundle='${wfLables}'/>")			
					b=false;
					return false;													
				}
				else if(booleanArrayPost[i] == 'No')
				{
					srno++;	
					var b=document.getElementById('hirTypeDetails').insertRow();
					var col1=b.insertCell(0);				
					var col2=b.insertCell(1);
					var col3=b.insertCell(2);	
					var col4=b.insertCell(3);
					col1.align="center";
					col2.align="center";
					col3.align="center";	
					col4.align="center";
				    col1.innerHTML=srno;
   		            col2.innerHTML=document.frmWfType.txtpost.value + '<input type="hidden" name="txtHidden_post" value=" ' + document.frmWfType.txtpost.value + ' " >';
					col3.innerHTML=document.frmWfType.txtlevelid.value + '<input type="hidden" name="txtHidden_levelid" value=" ' + document.frmWfType.txtlevelid.value + ' " >';
					var delString = "<a href='#' style=\"cursor:hand\" onClick=\"deleteRow(this" + "," + postval + "," +levelval+ ")\"><hdiits:caption captionid="WF.DELETE" bundle="${wfLables}"/></a>";											
					col4.innerHTML = delString;
					booleanArrayPost[i] = 'Yes';				
					b=false; 
					return false;
				}						
			}							 
		}		
		b=true;	
		return true;		 					
	}
		
	function doProcessingRole(getroleid, getlevelid, chkroleid, chklevelid, booleanArrayRole)	
	{				
		for(i=0; i<chkroleid.length; i++)
			{	
				if(chkroleid[i]==getroleid && chklevelid[i]==getlevelid)
				{							
					if(booleanArrayRole[i] == 'Yes')
					{						
						alert("<fmt:message key='WF.ROLE' bundle='${wfLables}'/> "+ getroleid + " <fmt:message key='WF.LEVELID' bundle='${wfLables}'/> "+getlevelid+" <fmt:message key='WF.EXISTS' bundle='${wfLables}'/> ")
						b=false;							
						return false;												
					}
				    else if(booleanArrayRole[i] == 'No')
					{					
						srno++;		
						var b=document.getElementById('hirTypeDetails').insertRow();
						var col1=b.insertCell(0);				
						var col2=b.insertCell(1);
						var col3=b.insertCell(2);	
						var col4=b.insertCell(3);
						col1.align="center";
						col2.align="center";
						col3.align="center";	
						col4.align="center";
					    col1.innerHTML=srno;	   		            
	   		            col2.innerHTML=document.frmWfType.txtrole.value + '<input type="hidden" name="txtHidden_role" value=" ' + document.frmWfType.txtrole.value + ' " >';
						col3.innerHTML=document.frmWfType.txtlevelid.value + '<input type="hidden" name="txtHidden_levelid" value=" ' + document.frmWfType.txtlevelid.value + ' " >';
						var delString = "<a href='#' style=\"cursor:hand\" onClick=\"deleteRow(this" + "," + roleval + "," +levelval+ ")\"><hdiits:caption captionid="WF.DELETE" bundle="${wfLables}"/></a>";											
						col4.innerHTML = delString;
						booleanArrayRole[i] = 'Yes';				
						b=false;
						return false;						
					}						
				}										
			}
					b=true;		
					return true;					
	}
	
	
	function deleteRow(src, idObj, levelObj)
		{											
			if(confirm("Are You Sure You Want To Delete the Record?")==true)
			{	
				srno--;				
				for(i=0; i<chkpostid.length; i++)
				{	
					if(chkpostid[i]==idObj && chklevelid[i]==levelObj)
					{	
						booleanArrayPost[i]="No";						
					}					
				}	
				for(i=0; i<chkroleid.length; i++)
				{	
					if(chkroleid[i]==idObj && chklevelid[i]==levelObj)
					{	
						booleanArrayRole[i]="No";				
					}					
				}		
				
				var row1 = src.parentElement.parentElement;
				document.all("hirTypeDetails").deleteRow(row1.rowIndex);				
			}	
				var rowlen=document.getElementById('hirTypeDetails').rows.length;	
				if(rowlen==1)
				{
					buttonvalP=document.getElementById('rdPost');
			  		buttonvalR=document.getElementById('rdRole');		  			
	    			buttonvalP.disabled=false; 
			  		buttonvalR.disabled=false;	
			  		
			  		document.getElementById('txtpost').value='';
				    document.getElementById('txtrole').value='';
				    document.getElementById('txtlevelid').value='';		
			  			
				}			
				rowlen = document.getElementById('hirTypeDetails').rows.length;
				for(i=1;i<rowlen;i++)
				{
					var x=document.getElementById('hirTypeDetails').rows[i].cells;
					x[0].innerHTML=i;
				}	
	}	
	
    getpostid=document.getElementById('txtpost').value ;
	getlevelid=document.getElementById('txtlevelid').value;		
	getroleid=document.getElementById('txtrole').value;	
	function addTab()
	{		
		if(document.forms[0].radiobtn[0].checked==true) 
		{
			if(document.forms[0].txtpost.value != '' && document.forms[0].txtlevelid.value != '')
			{
				if(confirm("As you have provided both PostId and LevelId, Press OK to add \n\n Cancel to submit the form.") == true)
				{
					var buttonvalAdd=document.getElementById('addBtn');
					buttonvalAdd.click();	
				}
			}
		}
		if(document.forms[0].radiobtn[1].checked==true) 
		{
			if(document.forms[0].txtrole.value != '' && document.forms[0].txtlevelid.value != '')
			{
				if(confirm("As you have provided both RoleId and LevelId, Press OK to add \n\n Cancel to submit the form.") == true)
				{
					var buttonvalAdd=document.getElementById('addBtn');
					buttonvalAdd.click();	
				}
			}
		}		
		if(b == false)	
		{				
			alert("<fmt:message key='WF.VALIDLEVEL' bundle='${wfLables}'/>");
			document.forms[0].txtlevelid.focus();
			document.forms[0].txtlevelid.value='';
			return false;

		}	
		else
		{							 
			document.forms[0].submit();	
		}
	}
	
	
	function ajaxFunction()
	{	
		chkpostid=new Array();
		chklevelid=new Array();	
		chkroleid=new Array();
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
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}    	
        var url = "${contextPath}/hdiits.htm?actionFlag=viewHierarchyRef&refId="+document.forms[0].selrefid.value;        
        xmlHttp.onreadystatechange = function()
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;					
					
					document.getElementById('dispTable').style.display = '';   	
					var totalRows=document.getElementById("dispTable").rows.length;
					for(i=1;i<eval(totalRows);i++)
					{							
						var trow=document.getElementById('dispTable');      		
						trow.deleteRow(1);
					}
					var tableentries1 = XMLDoc.getElementsByTagName('postIdMapped');
					var tableentries2 = XMLDoc.getElementsByTagName('roleIdMapped');	
					
					var tableentries = null;	
					var tflag = 0;					
					if(tableentries1 != null && tableentries1.length != 0)
					{			
							document.getElementById('heading1').style.display = '';
							document.getElementById('heading2').style.display = 'none';  	
												
							tableentries = tableentries1;
							tflag = 1;
					}
					else if(tableentries2 != null && tableentries2.length != 0)
					{		
							document.getElementById('heading1').style.display = 'none'; 
							document.getElementById('heading2').style.display = ''; 
											
							tableentries= tableentries2;
							tflag = 1;
					}					
					
					getpostid=document.getElementById('txtpost').value ;
					getlevelid=document.getElementById('txtlevelid').value;
					getroleid=document.getElementById('txtrole').value;					 
					
					buttonvalP=document.getElementById('rdPost');
	   				buttonvalR=document.getElementById('rdRole');
	   				
	   				document.forms[0].txtCounter.value=0;
					if(tflag == 1)
					{
						for(i = 0 ; i < tableentries.length ; i++ )
	   					{	   						   							
	   						document.getElementById('dispTable').style.display = '';   					
	   						Id = tableentries[i].childNodes[0].text;
	   						levelId = tableentries[i].childNodes[1].text;	
	   						selPostorRole= tableentries[i].childNodes[3].text;	 
	   						if(selPostorRole=="PostBased")
	   						{	  
	   							buttonvalP.click();	
	   							buttonvalP.disabled=false; 		   						
	   							buttonvalR.disabled=true;			
	   							if(doProcessingPost(Id, levelId, chkpostid, chklevelid, booleanArrayPost)) 
								{	
	   								var a=document.getElementById('dispTable').insertRow();
					     			var col1=a.insertCell(0);
									var col2=a.insertCell(1);	
									Counter=document.forms[0].txtCounter.value;														
									chkpostid[eval(Counter)]=Id;
									chklevelid[eval(Counter)]=levelId;
									Counter++;										
									booleanArrayPost[i] = 'Yes';									
									col1.align="center";
									col2.align="center";							
									col1.innerHTML=Id;
									col2.innerHTML=levelId;										
		   						}
		   							document.forms[0].txtCounter.value=Counter;			   									
	   						}
	   						else if(selPostorRole=="RoleBased")
	   						{
	   							buttonvalR.click();	 
	   							buttonvalR.disabled=false;  							
   								buttonvalP.disabled=true; 
	   							if(doProcessingRole(Id, levelId, chkroleid, chklevelid, booleanArrayRole))
	   							{	   								
	   								var a=document.getElementById('dispTable').insertRow();
					     			var col1=a.insertCell(0);
									var col2=a.insertCell(1);	
									Counter=document.forms[0].txtCounter.value;														
									chkroleid[eval(Counter)]=Id;
									chklevelid[eval(Counter)]=levelId;
									Counter++;	
									booleanArrayRole[i] = 'Yes';	
									col1.align="center";
									col2.align="center";							
									col1.innerHTML=Id;
									col2.innerHTML=levelId;											
	   							}
	   								document.forms[0].txtCounter.value=Counter;		   								
	   						}							
						 } 
					}
					else
					{					
							document.getElementById('heading1').style.display = 'none';
							document.getElementById('heading2').style.display = 'none';  
   							document.getElementById('dispTable').style.display = 'none';  
   							buttonvalR.disabled=false;  							
   							buttonvalP.disabled=false;  
							alert("<fmt:message key='WF.NORECORDS' bundle='${wfLables}'/>");
   					}
				 }				
			}
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
		xmlHttp.send(encodeURIComponent(null));
		return true;
	}
		
--></script>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script language="javascript">
	function ajaxfunctionPost()
	{		
	if(document.forms[0].txtpost.value != '')
	{
		try
    	{       		 
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{   			
			try
      		{      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");         		   
      		}
		    catch (e)
		    {
		          try
        		  {                	     
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}    	
        var url = "${contextPath}/hdiits.htm?actionFlag=checkpostId&postId="+document.forms[0].txtpost.value;  
        xmlHttp.onreadystatechange = function()
		{			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var tableentries = XMLDoc.getElementsByTagName('postIdMapped');	
					for (i = 0 ; i < tableentries.length ; i++ )
     				{
     					if(tableentries[i].childNodes[0].text == 'false')
     					{
   							alert(document.getElementById('postmsg').value);  						
     						document.forms[0].txtpost.value = '';   
     						//document.forms[0].txtpost.focus();  						
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
	}
	
	function ajaxfunctionRole()
	{
		if(document.forms[0].txtrole.value !='')
		{
		try
    	{   
    		// Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{   
			// Internet Explorer    
			try
      		{      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");         		   
      		}
		    catch (e)
		    {
		          try
        		  {                	      
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}    	
        var url = "${contextPath}/hdiits.htm?actionFlag=checkroleId&roleId="+document.forms[0].txtrole.value;  
        xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var tableentries = XMLDoc.getElementsByTagName('roleIdMapped');	
					for (i = 0 ; i < tableentries.length ; i++ )
     				{
     					if(tableentries[i].childNodes[0].text == 'false')
     					{
   							alert(document.getElementById('rolemsg').value);  						
     						document.forms[0].txtrole.value = '';   
     						//document.forms[0].txtrole.focus();  						
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
	}
	
	function chkconditionpost()
	{			
			if(document.forms[0].radiobtn[0].checked==true || document.getElementById('hirTypeDetails').rows.length>1)
			{				
					return false;				
			}
			else 
			{
					return true;
			}
	}
	function chkconditionrole()
	{			
			if(document.forms[0].radiobtn[1].checked==true || document.getElementById('hirTypeDetails').rows.length>1)
			{	
					return false;				
			}
			else 
			{
					return true;
			}
	}
	
	
	function isCompulsory()
	{
		if(document.getElementById('hirTypeDetails').rows.length>1)
			{
				return false;
			}
			else
			{
				return true;
			}
	}
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
