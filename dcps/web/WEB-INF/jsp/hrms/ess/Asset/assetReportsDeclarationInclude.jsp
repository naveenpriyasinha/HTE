<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%
try
{%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<fmt:setBundle basename="resources.ess.asset.AssetReports" var="assetReportsLables" scope="request"/> 
<fmt:setBundle basename="resources.ess.asset.AssetAlertsMsg" var="as1" scope="request"/>

  <tr>
              
            <td>&nbsp;</td>
            <td class="captionTag">
            	<hdiits:caption captionid="ASSET.REPORTS_REPORT_FOR" bundle="${assetReportsLables}"/>
            	<FONT COLOR=RED>*</FONT>
            </td>
            <td>&nbsp;:</td>
                <td colspan="1"> 
	                <select class="selecttag" style="width: 155;" size="1" name="reportFor" id="reportFor" attrTitle="reportFor" type="select-one" onchange="displayClassOfficer(this)">
		                <option value="-1"><fmt:message key="ASSET.REPORTS_DROPDOWN_SELECT" bundle="${assetReportsLables}"/></option>
						<option value="declareJoining"><fmt:message key="ASSET.REPORTS_REPORT_DECLARE_JOINING" bundle="${assetReportsLables}"/></option>
						<option value="movableAsset"><fmt:message key="ASSET.REPORTS_REPORT_MOVABLE_ASSET" bundle="${assetReportsLables}"/></option>
						<option value="fixedAsset"><fmt:message key="ASSET.REPORTS_REPORT_FIXED_ASSET" bundle="${assetReportsLables}"/></option>
	              	</select>
            	</td>
            	
            	 <td>&nbsp;</td>
            
	            <td>&nbsp;</td>
	            <td class="captionTag" id="captionClassOfficer" style="display:none"><hdiits:caption captionid="ASSET.REPORTS_CLASS_OFFICER" bundle="${assetReportsLables}"/>
	            </td>
	            <td id="classOfficerCol" style="display:none">&nbsp;:</td>
            
            	
            	
            	<td id="tdClassOfficer" style="display:none">
					<SELECT class="selecttag" style="width: 155;" size="1" name="classOfficer" id="classOfficer" attrTitle="classOfficer" type="select-one" onchange="getDesignationByClass()">
		                <option value="-1"><fmt:message key="ASSET.REPORTS_DROPDOWN_SELECT" bundle="${assetReportsLables}"/></option>
						<option value="gazettedOfficer"><fmt:message key="ASSET.REPORTS_GAZETTED_OFFICER" bundle="${assetReportsLables}"/></option>
						<option value="nonGazettedOfficer"><fmt:message key="ASSET.REPORTS_NON_GAZETED_OFFICER" bundle="${assetReportsLables}"/></option>
	              	</select>            		
            	</td>
              <td>&nbsp;</td>
       </tr>
    
       <tr>
                      	<td>&nbsp;</td>
            <td class="captionTag">
            	<hdiits:caption captionid="ASSET.REPORT_DESIGNATION" bundle="${assetReportsLables}"/>
            </td>
            <td>&nbsp;:</td>
                <td colspan="1"> 
	                <SELECT class="selecttag" style="width: 155;" size="1" name="designation" id="designation" attrTitle="designation" type="select-one" >
		                <option value="-1"><fmt:message key="ASSET.REPORTS_DROPDOWN_SELECT" bundle="${assetReportsLables}"/></option>
	              	</select>
            	</td>
            	  <td>&nbsp;</td>
      </tr>   
<script language="javascript">
function displayClassOfficer(cmbOptn)
{	
	var reportFor = cmbOptn.value;
	if(reportFor == '')
	{
		alert();
		return;
	}
	else if(reportFor == '-1')
	{
		document.getElementById("classOfficer").value="-1";
		document.getElementById("classOfficerCol").style.display = "none";
		document.getElementById("captionClassOfficer").style.display = "none";
		document.getElementById("tdClassOfficer").style.display = "none";
	}
	else if(reportFor == 'declareJoining')
	{
		document.getElementById("classOfficer").value="-1";
		document.getElementById("classOfficerCol").style.display = "none";
		document.getElementById("captionClassOfficer").style.display = "none";
		document.getElementById("tdClassOfficer").style.display = "none";
	}
	else if(reportFor == 'movableAsset')
	{
		document.getElementById("captionClassOfficer").style.display = "";
		document.getElementById("tdClassOfficer").style.display = "";
		document.getElementById("classOfficer").value="-1";
		document.getElementById("classOfficerCol").style.display = "";
	}
	else if(reportFor == 'fixedAsset')
	{
		document.getElementById("captionClassOfficer").style.display = "none";
		document.getElementById("tdClassOfficer").style.display = "none";
		document.getElementById("classOfficer").value="-1";
		document.getElementById("classOfficerCol").style.display = "none";
	}
	getDesignationByClass();
}
function getDesignationByClass()
{
	var locationElmnt=document.getElementById('location');
	var branchElmnt=document.getElementById('branch_Name');
	var location = new Array();
	var designation=document.getElementById("designation");
	
	for (var i=designation.length;i>0;i--)
	{
		designation.remove(i);
	}
	for (var i = 0; i < locationElmnt.options.length; i++)
    {
    	if (locationElmnt.options[i].selected)
    	{
    		location.push(locationElmnt.options[i].value);
    	}
    }
    var branch = new Array();
	for (var i = 0; i < branchElmnt.options.length; i++)
    {
    	if (branchElmnt.options[i].selected)
    	{
    		branch.push(branchElmnt.options[i].value);
    	}
    }
    
	var reportFor = document.getElementById("reportFor").value;
	var classOfficer = document.getElementById('classOfficer').value;
	
	if(reportFor=='-1')
	{
		return;
	}
	try{   
    	xmlHttp=new XMLHttpRequest();    
	}
	catch (e)
	{    // Internet Explorer    
		try{
    		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
		}
		catch (e){
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
	var url = "hrms.htm?actionFlag=getDesignationByClass"
	url=url+"&location="+location+"&branchName="+branch+"&reportFor="+reportFor+"&classOfficer="+classOfficer;
	
	xmlHttp.open("POST", encodeURI(url) , true);
	xmlHttp.onreadystatechange = processDesignation;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
	
}
function processDesignation()
{
	if (xmlHttp.readyState == 4) 
	{
	    if (xmlHttp.status == 200) 
	    {
	    	
			var xmlStr = xmlHttp.responseText;
			
			var XMLDoc = getDOMFromXML(xmlStr);
			var dsgnCode = XMLDoc.getElementsByTagName('dsgnCode');
			var dsgnName = XMLDoc.getElementsByTagName('dsgnName');
			var rowNo=dsgnCode.length;
			for(j=0;j<rowNo;j++)
			{
				var designation=document.getElementById('designation');
				var	orgDesignationMstDsgnCode=dsgnCode[j].text;
				var	orgDesignationMstDsgnName=dsgnName[j].text;
				var option1=document.createElement('option');
				option1.text=orgDesignationMstDsgnName;
				option1.value=orgDesignationMstDsgnCode;
				try
				{
					designation.add(option1,null);
				}
				catch(ex)
				{
					designation.add(option1);
				}
			}
		}
	}
}
function refreshasset()
{
	document.getElementById("classOfficer").value="-1";
	document.getElementById("classOfficerCol").style.display = "none";
	document.getElementById("captionClassOfficer").style.display = "none";
	document.getElementById("tdClassOfficer").style.display = "none";
	document.getElementById("reportFor").value="-1";
	getDesignationByClass();
	hideProgressbar();
}
function validateControls1()
{
	var reportFor=document.getElementById("reportFor").value;
	if(reportFor=="-1")
	{
		alert('<fmt:message  bundle="${as1}" key="ASSET.REPORT_FOR"/>');
		return false;
	}
	return true;
	//alert("before generate");
}
</script>
<%}catch(Exception e)
  {
  e.printStackTrace();
  }
%>