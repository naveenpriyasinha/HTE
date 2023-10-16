<%try { %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="postIdLst" value="${resultMap.postIdLst}"></c:set>
<c:set var="postNameLst" value="${resultMap.postNameLst}"></c:set>
<c:set var="locationNameLst" value="${resultMap.locationNameLst}"></c:set>
<c:set var="desgnNameLst" value="${resultMap.desgnNameLst}"></c:set>
<c:set var="empNameLst" value="${resultMap.empNameLst}"></c:set>

<script type="text/javascript">

	function addPrfrdEmpinTable()
	{
		if('${postIdLst}'!='[]')
		{
		var postIdLst1 = '${postIdLst}';
		new1 = postIdLst1.split('[');
	    new2 = new1[1].split(']');
	    var postIdLst = new2[0].split(',');

		var postNameLst1 = '${postNameLst}';
		new1 = postNameLst1.split('[');
	    new2 = new1[1].split(']');
	    var postNameLst = new2[0].split(',');
		
		var locationNameLst1 = '${locationNameLst}';
		new1 = locationNameLst1.split('[');
	    new2 = new1[1].split(']');
	    var locationNameLst = new2[0].split(',');
		
		var desgnNameLst1 = '${desgnNameLst}';
		new1 = desgnNameLst1.split('[');
	    new2 = new1[1].split(']');
	    var desgnNameLst = new2[0].split(',');
		
		var empNameLst1 = '${empNameLst}';
		new1 = empNameLst1.split('[');
	    new2 = new1[1].split(']');
	    var empNameLst = new2[0].split(',');
		var flag=true;
		for(var i=0;i<postIdLst.length;i++)
		{
			var row = document.getElementById("tab_new").insertRow();
//			row.setAttribute("id", i);
			var cell1=row.insertCell(0);
			var cell2=row.insertCell(1);
			var cell3=row.insertCell(2);
			var cell4=row.insertCell(3);
			var cell5=row.insertCell(4);
			var cell6=row.insertCell(5);

			cell1.innerHTML=postIdLst[i];
			cell2.innerHTML=empNameLst[i];
			cell3.innerHTML=postNameLst[i];
			cell4.innerHTML=desgnNameLst[i];
		    cell5.innerHTML=locationNameLst[i];
			cell6.innerHTML='<a href="javaScript:delete_me('+postIdLst[i] + ', ' + parent.document.getElementById('grpId').value + ')">Delete</a>';
		}
		}
	}
	
</script>
<script type="text/javascript">
	function delete_me(splicedpost, groupId)
	{
		if(confirm("Are You Sure want to delete?"))
		{
//		var idNo =document.getElementById(recNo).rowIndex;
//		document.getElementById('tab_new').deleteRow(idNo);
		var tab_ref = document.getElementById("tab_new");
		for(var i=1;i<tab_ref.rows.length;i++)
		{
			if(tab_ref.rows[i].cells[0].innerHTML == splicedpost)
			{
				tab_ref.deleteRow(i);
			}
		}		
		if(parent.document.frames['locationList'].document.getElementById(splicedpost) != null)
			parent.document.frames['locationList'].document.getElementById(splicedpost).checked=false;

		for(i=0;i<parent.postIdArray.length;i++)
		{
			if(parseInt(parent.postIdArray[i])==splicedpost)
			{
				parent.postIdArray.splice(i,1);
			}
		}
		
		ajaxDelete(splicedpost, groupId);
		}
		else
		{}
//		alert(parent.postIdArray);
	}
	function ajaxDelete(splicedpost, groupId)
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

		var url = "hdiits.htm?actionFlag=delPrfdUsr&postId="+splicedpost+"&groupId="+groupId;  
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
				}
			}
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
		//alert(document.getElementById('LocationTable').innerHTML);
	
	}
	//action="hdiits.htm?actionFlag=populatePrfdLst"
</script>

<hdiits:form name="prfdLst" method="POST" validate="true" action="./hdiits.htm">
<br>
<center>
	<font color="blue" size="4">
		<label id="heading" ></label>
	</font>
</center>
	<br>
	<table id="tab_new" border="1" width="100%">
		<thead>
			<tr>
				<th>Post Id</th>
				<th>Employee Name</th>
				<th>Post Name</th>
				<th>Designation Name</th>
				<th>Location Name</th>
				<th>Delete</th>	
			</tr>
		</thead>
	</table>
	
	<script type="text/javascript">
		addPrfrdEmpinTable();
	</script>
	<c:if test="${postIdLst ne null}">
	<c:forEach items="${postIdLst}" var="postIdLst">
		<script type="text/javascript">
			parent.postIdArray.push('${postIdLst}');
		</script>
	</c:forEach>
	</c:if>
	<center><hdiits:button name="returnMe" type="button" value="Return with Selection" onclick="returnWithSelection()"/></center>
</hdiits:form>
<script type="text/javascript">
document.getElementById('heading').innerHTML = "Preferred List";

function returnWithSelection()
{
	parent.window.opener.document.forms[0].action='hdiits.htm?actionFlag=populatePrfdLst&updflag=false';
	parent.window.opener.document.forms[0].submit();
	parent.window.close();
}
</script>
<% 
}catch(Exception e)
{
	 e.printStackTrace();
}
%>