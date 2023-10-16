<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="Locations" value="${resValue.LocationList}"></c:set>
<c:set var="deptId" value="${resValue.LocationList}"></c:set>
<hdiits:form name="AjaxForm" id="AjaxForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<fmt:setBundle basename="resources.common.CommonLables" var="locLables" scope="request"/>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<hdiits:hidden name="locationList" id="loclist_id"/>
	
	<display:table list="${Locations}" pagesize="20" requestURI="" id="row"  defaultsort="1" style="width:100%">
		<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="checkbox" name="check" onClick="appendLocations(this)" id="loc${row.locId}" value="${row.locId}"> </display:column>
		<display:column  titleKey="LOC.LOCATION" headerClass="datatableheader" sortable="true">${row.locName}</display:column>
		<display:column  titleKey="LOC.ADDR1" headerClass="datatableheader" sortable="true">${row.locAddr1}</display:column>
		<display:column  titleKey="LOC.ADDR2" headerClass="datatableheader" sortable="true">${row.locAddr2}</display:column>
		<display:column  titleKey="LOC.CITY" headerClass="datatableheader" sortable="true">${row.cityName}</display:column>
		<display:column  titleKey="LOC.DIST" headerClass="datatableheader" sortable="true">${row.districtName}</display:column>
		<display:column  titleKey="LOC.STATE" headerClass="datatableheader" sortable="true">${row.stateName}</display:column>
		<display:column  titleKey="LOC.PIN" headerClass="datatableheader" sortable="true">${row.locPin}</display:column>
	</display:table>
</hdiits:form>




<script type="text/javascript">

var FramePageHeight = document.getElementById("AjaxForm").scrollHeight + 10;
if(FramePageHeight < 30)
{
	FramePageHeight = 30;
	parent.document.getElementById("location_id").style.height = FramePageHeight+"%";
	parent.document.getElementById("empFrame_id").style.height = (100 - parseInt(FramePageHeight))+"%";
}

function appendLocations(obj)
{
	var checkboxId = obj.id;
	if(document.getElementById(checkboxId).checked)
	{
		parent.locArray.push(obj.value);
	}
	else 
	{
		for(var i =0;i<parent.locArray.length;i++)
		{
			if(parent.locArray[i]==obj.value)
				{
					parent.locArray.splice(i,1);
					break;
				}
		}
	}
	//alert(parent.locArray);
	
}

function getBranches()
{
	var radButIdForBranchEmpDecision ;
	if(document.getElementById('branchHeadsOnly').checked)
	{
		radButIdForBranchEmpDecision = document.getElementById('branchHeadsOnly').value;
		parent.GetBranchsFromLoc(radButIdForBranchEmpDecision);
	}
	else if(document.getElementById('allEmps').checked)
	{
		radButIdForBranchEmpDecision = document.getElementById('allEmps').value;
		parent.GetBranchsFromLoc(radButIdForBranchEmpDecision);
	}
}

/*function DepSearch(levelId,identifier)
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
	
		//alert("deparmentId"+deptId);
		//alert("locVal"+locVal);
		
		if(identifier == 'Dep')
		{
			var url = "${contextPath}/hdiits.htm?actionFlag=depSearch&deptId="+levelId;  
			var tableName = "LocationTable";
		}
		else if(identifier == 'Loc')
		{
			var url = "${contextPath}/hdiits.htm?actionFlag=depSearch&locId="+levelId;  
			var tableName = "BranchTable";
		}
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					var tableentries = XMLDoc.getElementsByTagName('Location');	
					//alert(tableentries.length);
           			for ( var i = 0 ; i < tableentries.length ; i++ )
     				{
     					
     					id=tableentries[i].childNodes[0].text;
     					name=tableentries[i].childNodes[1].text;
     					
     					var element_row=document.createElement('tr');
	     				var element_col=document.createElement('td');
	     				
	     				//var element_a=document.createElement('a');
	     				//element_a.innerHTML=name;
	     				//element_col.appendChild(element_a);
	     				if(identifier == 'Dep')
	     				{
	     					element_col.innerHTML="<input type=checkbox name=check onClick=appendLocations(this) id=loc"+id+" value="+id+"> <a style=cursor:hand; onClick=DepSearch('"+id+"'"+",Loc)>"+name+"</a>";
	     					//element_col.innerHTML="<input type=checkbox name=check onClick=appendLocations(this) value=loc"+id+">";
	     				}
	     				else if(identifier == 'Loc')
	     				{
	     					//element_col.innerHTML="<a style='cursor:hand;' onClick='DepSearch("+id+")'>"+name+"</a>";
	     				}
	     				//element.value=id
	     				element_row.appendChild(element_col);
	     				document.getElementById(tableName).appendChild(element_row);
						//alert("name"+name);
				    }// end of for
				    }//end of if
				
			}
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
		//alert(document.getElementById('LocationTable').innerHTML);
	}*/
	

</script>
<script language="javascript">
	if(parent.locArray != null)
	{
		for(var cnt=0;cnt<parent.locArray.length;cnt++)
			{
				if(document.getElementById("loc"+parent.locArray[cnt]) != null)
				{
					document.getElementById("loc"+parent.locArray[cnt]).checked = true;
				}
			}
	}
</script>
