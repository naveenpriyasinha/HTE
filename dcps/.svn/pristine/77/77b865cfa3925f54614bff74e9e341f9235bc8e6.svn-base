
<%
try
{%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ include file="../core/include.jsp" %>
<fmt:setBundle basename="resources.radt.Lables" var="lables" scope="request"/>
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="/script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="/script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script language="JavaScript">

function entervalue()
{
	alert("plz enter sm value");
}
function ajaxfun()
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
		var url = "${contextPath}/hdiits.htm?actionFlag=getfiles1&moduleName="+document.form1.moduleName.value;   
		
		xmlHttp.open("POST", encodeURI(url) , true);
		
		xmlHttp.onreadystatechange = processResponse;
		//alert("in ProcessResponse");
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
	
	
	function processResponse()
		{
	
			
			if (xmlHttp.readyState == 4) 
			{     
					
				if (xmlHttp.status == 200) 
				{          
				
					var z=document.getElementById("moduleVer");
					//	alert("z===",z);
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
				    var entries = XMLDoc.getElementsByTagName('element');
				//  alert("Entries of " + entries.length);	
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     					//alert(text);
	     				    //deleteComboValues();
	     					var y=document.createElement('option');
		 					
							y.value=text;
							y.text=text;
							try
	   						{
	   					//		alert("in try");
	    						z.add(y,null); 
	    			
	   						}
	 						catch(ex)
	   						{
	   			 		//		alert("in catch");
	   			 				z.add(y); 
	   						}    					
	     					
	           			}	


				}
				else 
				{  
			
					//alert("ERROR");
					//alert(xmlHttp.status);
					//alert(xmlHttp.responseText);
					//document.getElementById("res").innerHTML=xmlHttp.responseText;
					//alert("after Div");
				}
			}
	    }
function deleteComboValues(obj)
		{			
			var UserEntries=document.getElementById("ModuleCombo");
			for(var i=0;i<UserEntries.length;i++)
			{
					//alert(UserEntries.options[i].text);
					UserEntries.remove(i);
					i = i - 1;
			}
			
			
		}
function checkPress() {
alert('hello');
   if (event.keyCode == 13) {
      
     alert('enter pressed');
     return false;
   }
  
}
</script>
<hdiits:form method="POST" name="form1" validate="true"  action="./hdiits.htm" >
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="FILE_DETAILS_TITLE" bundle="${lables}"/></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">


<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tcontent1" class="tabcontent" tabno="0">
<hdiits:table>
 <tr>
  <td class="fieldLabel" width="25%"><hdiits:caption captionid="MOD_NAME" bundle="${lables}"/></td>
 <td><hdiits:text name="moduleName" captionid="MOD_NAME" validation="txt.isrequired"  bundle="${lables}" ></hdiits:text></td>

  </tr>
  <BR>
  <tr>
  <td class="fieldLabel" width="25%"><hdiits:caption captionid="MOD_VER" bundle="${lables}"/></td>
 <td>
 
 <hdiits:select name="moduleVer"  captionid="MOD_VER" validation="sel.isrequired" mandatory="true" bundle="${lables}" >
 	<option value='-1' selected><fmt:message key="select"  bundle="${lables}"/></option>
 	
 </hdiits:select></td>
  </tr>
  <BR>

<tr> 
<td colspan="2"><input type="hidden" name="actionFlag" value="getfiledetails"></td>
</tr>
</hdiits:table>
</div>
<a href= "./hdiits.htm?viewName=mainopt">  <fmt:message key="GO_TO_MAIN_MENU" /> <BR><BR>
<jsp:include page="../core/tabnavigation.jsp" /></div>
<script type="text/javascript">
	
		initializetabcontent("maintab")
		</script>
				<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
						
		</hdiits:form>

        <!--  AJAX PART STARTS -->
     	<ajax:autocomplete
 		 source="moduleName"
		 target="moduleName"
		 baseUrl="${contextPath}/hdiits.htm?actionFlag=getfiles"
    	 className="autocomplete"
		 parameters="parameter={moduleName}"
		 progressStyle="throbbing"
		 postFunction="ajaxfun"
		 emptyFunction="entervalue" 		 
		 />   
        
		
	        
<%}catch(Exception e)
  {
  e.printStackTrace();
  }
  %>