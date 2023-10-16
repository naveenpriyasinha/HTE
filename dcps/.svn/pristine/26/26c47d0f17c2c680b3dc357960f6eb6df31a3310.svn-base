<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="finalNotingLst" value="${resultMap.finalNotingLst}"></c:set>
<c:set var="fileNotingLst" value="${resultMap.fileNotingLst}"></c:set>
<script type="text/javascript" src="script/common/base64.js"></script>
<c:set var="cnt" value="0"></c:set>
<c:set var="paracnt" value="0"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />

<script>

var notingIdArr=new Array();
var notingValArr=new Array();

</script>

<hdiits:form name="viewnotingfrm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">

<table width="100%" border="1" bordercolor="green">
<tr>
<td>
		<table border="1" width="100%" >
		<tr>
			<td style="border: none">
			<c:if test="${resultMap.fromfile eq 'yes' }">
			<fmt:message  key="WF.FileNo" bundle="${fmsLables}" />
			</c:if>
			<c:if test="${resultMap.fromfile ne 'yes' }">
			<fmt:message  key="WF.CorrNo" bundle="${fmsLables}" />
			</c:if>
			
			
			<hdiits:caption  caption="${resultMap.corr_file_no}" captionid="corrno"  />
			</td>
			<td style="border: none">
			<b><fmt:message  key="WF.Subject" bundle="${fmsLables}" /></b>
			<hdiits:caption  caption="${resultMap.subject}"  captionid="subject"/>
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
		<tr>
		</tr>
		
		<tr id="SelUnSelTrId">
			<td style="border: none">
				<a href="#" onclick="selectAllPara()" >
				<u><fmt:message key="WF.SelAll" bundle="${fmsLables}"></fmt:message></u>
				</a>
				<c:out value="/"></c:out>
				<a href="#" onclick="unselectAllPara()" >
				<u><fmt:message key="WF.UnSelAll" bundle="${fmsLables}"></fmt:message></u>
				</a>
				
				
				
			</td>
		</tr>		
		</table>
			
		<table border="0"  >
		
			
		<c:forEach items="${finalNotingLst}" var="corrNoting" varStatus="status" >
		
		
			<tr id="paraTrId${paracnt}">
				<h2>
				<td >
					<input type="checkbox" id="chkId${paracnt}" >
					<b>
					<font color="RED">
					<fmt:message  key="WF.Para" bundle="${fmsLables}"  />
					<c:out value="${status.index+1}"> </c:out></font>
					</b>
				</td>
				</h2>
			</tr>
			<tr>
				<td>
				<div id="divId${paracnt}"> 
					<table id = "table_${paracnt}">

						<c:forEach items="${corrNoting}" var="NoingLst" varStatus="varstatus" > 
						
							<c:if test="${fn:length(corrNoting) ne varstatus.index+1}">
							<tr>
									<td id="notingId${cnt}">
									<c:out value="${varstatus.index+1}."></c:out>
											<script>
																						
													var retnoting=decodeBase64('${NoingLst}');
													//var retnoting=decodeNoting();
													notingIdArr.push("notingId${cnt}");
													notingValArr.push(retnoting);
													
											</script>
											<c:set var="cnt" value="${cnt+1}"></c:set>		
											
									</td>
							</tr>
							</c:if>
							<tr>
							</tr>
							<c:if test="${fn:length(corrNoting) eq varstatus.index+1}">
							<tr>
								<h3>
								<td>				
									
										<b><c:out value="${NoingLst}"> </c:out></b>
								</td>
								</h3>
							</tr>
							</c:if>
							
							
							<tr>
							</tr>
							<tr>
							</tr>
							<tr>
							</tr>
							<tr>
							</tr>
						</c:forEach>
					<c:set var="paracnt" value="${paracnt+1}"></c:set>	
					</table>
				</div>
			</td>
			</tr>		
		</c:forEach>
		</table>
		
		
</td>
</tr>
<tr id="SelUnSelTrId1">
			<td style="border: none">
				<a href="#" onclick="selectAllPara()" >
				<u><fmt:message key="WF.SelAll" bundle="${fmsLables}"></fmt:message></u>
				</a>
				<c:out value="/"></c:out>
				<a href="#" onclick="unselectAllPara()" >
				<u><fmt:message key="WF.UnSelAll" bundle="${fmsLables}"></fmt:message></u>
				</a>
				
				
				
			</td>
		</tr>		
</table>

<center><hdiits:button name="btnPrint" type="button" onclick="printNotings()" captionid="WF.Print" bundle="${fmsLables}"/>
<hdiits:button name="closebtn" captionid="WF.Close" bundle="${fmsLables}" type="button"  onclick="window.close()"/></center>
</hdiits:form>

<script>
var totalpara='${paracnt}';
var flag =0;
var count =0 ;
var count1=2;
var bcnt=0;
	for(i=0;i<notingIdArr.length;i++)
	{
		
		document.getElementById(notingIdArr[i]).innerHTML=document.getElementById(notingIdArr[i]).innerHTML+notingValArr[i];
	}
   function resetev()
   {
       
     if(flag  == 1)
      { 
				for(var i=0;i<totalpara;i++)
				{
					if(document.getElementById("chkId"+i).checked==false)
					{
						document.getElementById("divId"+i).style.display='';
						document.getElementById("paraTrId"+i).style.display='';
				
					}
				document.getElementById("chkId"+i).style.display='';
			
			 	}

				    document.getElementById('SelUnSelTrId').style.display='';
					document.getElementById('SelUnSelTrId1').style.display='';
					document.getElementById('btnPrint').style.display='';
					
				
					document.getElementById('closebtn').style.display='';
					
     	 	}
         
      }


	function printNotings()
	{   flag=0;
	  
		var found=false;
		
		for(var j=0;j<totalpara;j++)
		{
			if(document.getElementById("chkId"+j).checked==true)
			{
					found=true;
					break;
					
			}
		}
		
		
		if(found)
		{
	        
			if(confirm('<fmt:message key="WF.PrnSelPara" bundle="${fmsLables}"></fmt:message>'))
			{
				
				for(var i=0;i<totalpara;i++)
				{
					if(document.getElementById("chkId"+i).checked==false)
					{
						document.getElementById("divId"+i).style.display='none';
						document.getElementById("paraTrId"+i).style.display='none';
						
					}
					document.getElementById("chkId"+i).style.display='none';
					
				}
				document.getElementById('SelUnSelTrId').style.display='none';
				document.getElementById('SelUnSelTrId1').style.display='none';
				document.getElementById('btnPrint').style.display='none';
				
				
				document.getElementById('closebtn').style.display='none';
				
				window.print();
		
				flag =1 ;
				
				window.onfocus = resetev; 
				
			}
			
		}
		else
		{
			alert('<fmt:message key="WF.SelOneParaMsg" bundle="${fmsLables}"></fmt:message>');
		}
		
}
function viewAllNoting()
{
	
	for(var j=0;j<totalpara;j++)
		{
			document.getElementById("divId"+j).style.display='';
			document.getElementById("paraTrId"+j).style.display='';
			document.getElementById("chkId"+j).style.display='';
			
		}
	document.getElementById('SelUnSelTrId').style.display='';
	document.getElementById('SelUnSelTrId1').style.display='';
}
function selectAllPara()
{
	for(var j=0;j<totalpara;j++)
	{
		if(document.getElementById("chkId"+j).checked==false)
		{
			document.getElementById("chkId"+j).checked=true;
		}	
	}
}
function unselectAllPara()
{
	for(var j=0;j<totalpara;j++)
	{
			if(document.getElementById("chkId"+j).checked==true)
			{
				document.getElementById("chkId"+j).checked=false;
			}	
	}
}


</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
