<%try{ %>
<%@ include file="../core/include.jsp"%>

<c:set var="resultObj"	value="${result}"> </c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="DeptData" value="${resultMap.DeptData}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<c:set var="SendAsCorr" value="${resultMap.SendAsCorr}" />

<c:set var="fileId" value="${resultMap.fileId}" />
<c:set var="otherSender" value="${param.otherSender}" scope="session"></c:set>
<c:set var="urlupd" value="${param.upd}"></c:set>
<c:set var="groupId" value="${param.groupId}"></c:set>
<input type="hidden" name="SendAsCorr" id="SendAsCorr" value="${resultMap.SendAsCorr}"/>
<input type="hidden" name="fileId" id="fileId" value="${resultMap.fileId}"/>
<input type="hidden"  name="methodFlag" value="${resultMap.methodFlag}"/>
<html>
	<head>
	<script>
		
	</script>
		<script type="text/javascript" src="script/treeScript/ECOTree.js"></script>
		<link type="text/css" rel="stylesheet" href="script/treeScript/ECOTree.css" />
		<xml:namespace ns="urn:schemas-microsoft-com:vml" prefix="v"/>
		<style>v\:*{ behavior:url(#default#VML);}</style> 
		<style>
			label, select, option, input, a {
				font-family : "Verdana";				
				font-size : 10px;				
				color : black;
			}
			.copy {
				font-family : "Verdana";				
				font-size : 10px;
				color : #CCCCCC;
			}
		</style>
		
	</head>
	<body>
		<form>
			 <table align="left" style="display: none" id = "t1"><tr><td><a style="font-size: 18"  id="a1" href="#" onclick="closeWin()" ><fmt:message bundle="${fmsLables}" key="WF.CLOSE"> </fmt:message></a></td></tr></table>
			<table>
				<tr>
					<td style="display: none">
						<label>Root position:</label>
						<select id="rootPosition" onchange="ChangePosition();" >				
							<option value="0" selected>Top</option>
							<option value="1">Bottom</option>
							<option value="2">Right</option>
							<option value="3">Left</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td>
						<div id="sample2" style="text-align: center;">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>


	    		<script>
	    		// code started by abhi
	    		  var id ='${winId}';
	    		  
	    		   if(id = 1)
	    		   { document.getElementById('t1').style.display = '';
	    			   }
	    		 function closeWin()
	    		  {   window.close();
	    		   
	    		  }
	    			newTable = new ECOTree('newTable','sample2', '${urlupd}', '${groupId}');
	    				    			
	    		</script>
	    	<c:set var="chk" value="-1"></c:set>
	    	
	    		    
	    	
	    	<c:forEach items="${DeptData}" var="DeptData">
	    
	    	<c:if test="${DeptData.parentDepId eq chk}">
	    	<script>
	    		try{
	    				
		    			newTable.add('${DeptData.departmentId}','${chk}','${DeptData.depName}');
		    		}
		    		catch(e)
		    		{
		    			alert(" in 1 " + e);
		    		}
		   	</script>
	    	</c:if>
	    	<c:if test="${DeptData.parentDepId ne chk}">
	    	<script>
	    	try{
		    			newTable.add('${DeptData.departmentId}','${DeptData.parentDepId}','${DeptData.depName}');
		    		}
		    		catch(e)
		    		{
		    				    			alert(" in 2 " + e);
		    		}
		   	</script>
	    	</c:if>
	    	</c:forEach>
	    	<script>
	    	try{
					
					newTable.UpdateTree();
				}
				catch(e)
				{
						    			alert(" in 3 " + e);
				}
			</script>
	    	<% 
	    	
	}
	catch(Exception e)
	{
		System.out.println(" error in DeptData. " );
		e.printStackTrace();
	}
%>
