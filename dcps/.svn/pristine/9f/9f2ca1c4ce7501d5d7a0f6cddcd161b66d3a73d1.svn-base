<%
	try
	{
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="branchLst" value="${resultMap.branchLst}"></c:set>
<c:set var="fileformatlist" value="${resultMap.fileformatlist}"></c:set>
<c:out value="${resultMap.msg}"/> 

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<script type="text/javascript">
var attrib_val =new Array();
function validateFileNoFormat()
{
		var flag="false";
		for(i=1;i<=5;i++)
		{
			var attribone=eval("document.getElementById('attrib"+i+"').value");
			
			
			for(j=i+1;j<=5;j++)
			{
				var attribtwo=eval("document.getElementById('attrib"+j+"').value");
				if(attribone==attribtwo)
				{
					flag="true";
					break;
				}
				
			}
		}		
		if(flag=="true")
		{
				alert("Two attribute doesn't contain same value");
				return false;
		}
		else
		{
			return true;
		}					

	
}
function getBranchFileNoFormat()
{
		document.getElementById('filenoformat').style.display='none';
		document.getElementById('srNo').value=0;
		
		if(document.getElementById('docType').value!=0)
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
		
		       var url = "${contextPath}/hdiits.htm?actionFlag=getBranchFileNoFormatDetails&ajaxBranchId="+document.fileNoFormatForm.branchcode.value+"&docType="+document.fileNoFormatForm.docType.value;
		       
				
		        xmlHttp.onreadystatechange = function()
				{
					
					if (xmlHttp.readyState == 4) 
					{     
						if (xmlHttp.status == 200) 
						{
							
							if(xmlHttp.responseXML.documentElement!=null)
							{
										var XMLDoc=xmlHttp.responseXML.documentElement;
										var filenoformat = XMLDoc.getElementsByTagName('FileNoFormat');
										var srno=XMLDoc.getElementsByTagName('SrNo');
										if(filenoformat.length != 0)
										{					
											attrib1=filenoformat[0].childNodes[0].text;
											attrib2=filenoformat[0].childNodes[1].text; 
					   						attrib3=filenoformat[0].childNodes[2].text; 
					   						attrib4=filenoformat[0].childNodes[3].text;
					   						attrib5=filenoformat[0].childNodes[4].text;
					   						
										}
									
										if(srno.length!=0)
										{
											no=srno[0].childNodes[0].text;
											document.getElementById('srNo').value=no;
										}
										
										if(document.getElementById('docType').value=='Y')
										{
												document.getElementById('txtfilenoformat').value=attrib1+"/"+attrib2+"/"+attrib3+"/"+attrib4+"/"+attrib5;
										}
										else
										{	
												document.getElementById('txtfilenoformat').value=attrib1+"/"+attrib2+"/"+attrib3;
										}		
							     		document.getElementById('filenoformat').style.display='';	
							     		
							}	     		
			     		}
					}
						    	
			    }
			    
		        xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));
				return true;
				
		}
		else
		{
			alert("Please Select DocumentType");
		}		
}
function docType_onchange()
{
			
			
			document.getElementById('filenoformat').style.display='none';
			if(document.getElementById('docType').value=='N')
			{
				attrib_val.push(document.getElementById('attrib1').options[1].text);
				attrib_val.push(document.getElementById('attrib1').options[2].text);	
											
				document.getElementById('attrib1').removeChild(document.getElementById('attrib1').options[1]);
				document.getElementById('attrib1').removeChild(document.getElementById('attrib1').options[1]);
				
				document.getElementById('attrib2').removeChild(document.getElementById('attrib2').options[1]);
				document.getElementById('attrib2').removeChild(document.getElementById('attrib2').options[1]);
				
				document.getElementById('attrib3').removeChild(document.getElementById('attrib3').options[1]);
				document.getElementById('attrib3').removeChild(document.getElementById('attrib3').options[1]);
				
				document.getElementById('attrib4').removeChild(document.getElementById('attrib4').options[1]);
				document.getElementById('attrib4').removeChild(document.getElementById('attrib4').options[1]);
				
				document.getElementById('attrib5').removeChild(document.getElementById('attrib5').options[1]);
				document.getElementById('attrib5').removeChild(document.getElementById('attrib5').options[1]);
				
				document.getElementById('att4').style.display='none';	
				document.getElementById('att5').style.display='none';
			}	
			else
			{
				for(totCombo=0;totCombo<5;totCombo++)
				{
					for(i=1;i<=attrib_val.length;i++)
					{
						newOpt = document.createElement("option");
						newOpt.text=attrib_val[i-1];
						newOpt.value=attrib_val[i-1];
						document.getElementById("attrib"+parseInt(totCombo+1)).options.add(newOpt,i) ;
					}
				}	
				for(i=1;i<=attrib_val.length;i++)
				{
					attrib_val.pop();
					attrib_val.pop();
				}
			
				document.getElementById('att4').style.display='';	
				document.getElementById('att5').style.display='';
			}
			document.getElementById('branchcodeid').options(0).selected=true;
			document.getElementById('attrib1').options(0).selected=true;
			document.getElementById('attrib2').options(0).selected=true;
			document.getElementById('attrib3').options(0).selected=true;
			document.getElementById('attrib4').options(0).selected=true;
			document.getElementById('attrib5').options(0).selected=true;
			
			
}

function validate_attrib()
{
		if(document.getElementById('docType').value=='N')
		{
		
				document.getElementById('attrib4').value='0';
				document.getElementById('attrib5').value='1';
				return false;
				
		}
		else
		{
			return true;				
		}	
}
</script>
<hdiits:form name="fileNoFormatForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">
<hdiits:hidden name="actionFlag"  default="insertFileFormatNumberDetails" />
<hdiits:hidden name="srNo" id="srNo" default="0" />
<div id="tabmenu">
	<ul id="maintab" class="shadetab">
			<li class="selected"><a href="#" rel="tab1" ><hdiits:caption caption="BranchFileNumberFormatDetails" captionid=""/></a></li>
	</ul>		
</div>

<div class="tabcontentstyle">

<div id="tab1" class="tabcontent">
		<center><b><font size="3"><fmt:message key="WF.branchTitle"  bundle="${fmsLables}"></fmt:message></font></b></center>
		<table align="center" border="1" >
					
					<br>
					<br>
					<td style="border: none" ><hdiits:caption captionid="WF.docType" bundle="${fmsLables}"/>
						</td>
						<td style="border: none">
							<hdiits:select name="docType" id="docType" mandatory="true" validation="sel.isrequired" captionid="WF.attrib1" bundle="${fmsLables}" sort="false" onchange="docType_onchange()">
									<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
										<option value='Y'>
										<c:out value="File"/></option>
				 			            <option value='N'>
										<c:out value="Correspodence"/></option>
								</hdiits:select>
						</td>
					
					<tr>
						
						<td style="border: none"><hdiits:caption captionid="WF.branchName" bundle="${fmsLables}"/>
						</td>
						<td style="border: none">
						<hdiits:select name="branchcode" id="branchcodeid" mandatory="true" validation="sel.isrequired" captionid="WF.branchName" bundle="${fmsLables}" sort="false" onchange="getBranchFileNoFormat()">
						<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
							<c:forEach var="branchList" items="${branchLst}">
									<option value='<c:out value="${branchList.branchId}"/>'>
									<c:out value="${branchList.branchCode}"/></option>
				 			</c:forEach>	
						</hdiits:select>
						
						
						</td>
							
					</tr>	
							
					<tr id="filenoformat" style="display: none">
							<td style="border: none;" id="filenoformattd"><hdiits:caption captionid="WF.fileNoformat" bundle="${fmsLables}"  />
							</td>
							<td style="border: none;" id="txtfilenoformattd">
							<hdiits:text name="txtfilenoformat" id="txtfilenoformat"  readonly="true" size="40" />
							</td>
					</tr>	
					<tr>
					</tr>
					<tr id="att1">
						<td style="border: none" ><hdiits:caption captionid="WF.attrib1" bundle="${fmsLables}"/>
						</td>
						<td style="border: none">
								<hdiits:select name="attrib1" id="attrib1" mandatory="true" validation="sel.isrequired" captionid="WF.attrib1" bundle="${fmsLables}" sort="false" >
									<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
									<c:forEach var="fileformatlistlookup" items="${fileformatlist}">
										<option value='<c:out value="${fileformatlistlookup.lookupName}"/>'>
										<c:out value="${fileformatlistlookup.lookupDesc}"/></option>
				 			        </c:forEach>
				 			        
				 			        <option value='<c:out value="${resultMap.curryear}"/>'>
									<c:out value="${resultMap.curryear}"/></option>
								</hdiits:select>
						</td>
					</tr>	
					
					<tr id="att2">
						<td style="border: none"><hdiits:caption captionid="WF.attrib2" bundle="${fmsLables}"/>
						</td>
						<td style="border: none">
								<hdiits:select name="attrib2" id="attrib2" mandatory="true" validation="sel.isrequired" captionid="WF.attrib2" bundle="${fmsLables}" sort="false">
									<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
									<c:forEach var="fileformatlistlookup" items="${fileformatlist}">
										<option value='<c:out value="${fileformatlistlookup.lookupName}"/>'>
										<c:out value="${fileformatlistlookup.lookupDesc}"/></option>
				 			        </c:forEach>
				 			        <option value='<c:out value="${resultMap.curryear}"/>'>
									<c:out value="${resultMap.curryear}"/></option>
								</hdiits:select>
						</td>
					</tr>	
					<tr>
					</tr>	
					<tr>
					</tr>	
					<tr id="att3">
						<td style="border: none"><hdiits:caption captionid="WF.attrib3" bundle="${fmsLables}"/>
						</td>
						<td style="border: none">
								<hdiits:select name="attrib3" id="attrib3" mandatory="true" validation="sel.isrequired" captionid="WF.attrib3" bundle="${fmsLables}" sort="false">
									<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
									<c:forEach var="fileformatlistlookup" items="${fileformatlist}">
										<option value='<c:out value="${fileformatlistlookup.lookupName}"/>'>
										<c:out value="${fileformatlistlookup.lookupDesc}"/></option>
				 			        </c:forEach>
				 			        <option value='<c:out value="${resultMap.curryear}"/>'>
									<c:out value="${resultMap.curryear}"/></option>
								</hdiits:select>
						</td>
					</tr>
					<tr>
					</tr>	
					<tr>
					</tr>		
					<tr id="att4">
						<td style="border: none" ><hdiits:caption captionid="WF.attrib4" bundle="${fmsLables}"/>
						</td>
						<td style="border: none">
								<hdiits:select name="attrib4" id="attrib4" mandatory="true" validation="sel.isrequired" captionid="WF.attrib4" bundle="${fmsLables}" sort="false" condition="validate_attrib()">
									<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
									<c:forEach var="fileformatlistlookup" items="${fileformatlist}">
										<option value='<c:out value="${fileformatlistlookup.lookupName}"/>'>
										<c:out value="${fileformatlistlookup.lookupDesc}"/></option>
				 			        </c:forEach>
				 			        <option value='<c:out value="${resultMap.curryear}"/>'>
									<c:out value="${resultMap.curryear}"/></option>
								</hdiits:select>
						</td>
					</tr>
					<tr>
					</tr>	
					<tr>
					</tr>		
					<tr id="att5">
						<td style="border: none"><hdiits:caption captionid="WF.attrib5" bundle="${fmsLables}"/>
						</td>
						<td style="border: none">
								<hdiits:select name="attrib5" id="attrib5" mandatory="true" validation="sel.isrequired" captionid="WF.attrib5" bundle="${fmsLables}" sort="false" condition="validate_attrib()">
									<hdiits:option value="0" selected="true"><hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
									<c:forEach var="fileformatlistlookup" items="${fileformatlist}">
										<option value='<c:out value="${fileformatlistlookup.lookupName}"/>'>
										<c:out value="${fileformatlistlookup.lookupDesc}"/></option>
				 			        </c:forEach>
				 			        <option value='<c:out value="${resultMap.curryear}"/>'>
									<c:out value="${resultMap.curryear}"/></option>
								</hdiits:select>
						</td>
					</tr>					
						
	   </table>

</div>
<hdiits:jsField name="jsFileNoFormat" jsFunction="validateFileNoFormat()" />	
<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>