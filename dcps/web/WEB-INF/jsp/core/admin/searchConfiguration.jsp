<%@ include file="../include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>	
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
try{%>
   
  <script type="text/javascript" src="script/common/tabcontent.js"></script>	
   <script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
  <script type="text/javascript" src="script/common/ajaxtags-1.1.5.js"></script>
  
  
  <script ="text/javascript" src="script/common/tabcontent.js">
</script>
	   
	  
        <form name="frmcsearch" method="POST" action="./hdiits.htm">
         <!-- body part of any page start-->
         <div id="tabmenu">
          <ul id="maintab" class="shadetabs">
           		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="CON.SEARCH"/></a></li>
          </ul>
         </div>
         <div class="tabcontentstyle">
			<div id="tcontent1" class="tabcontent">
				
				<table border="0" align="center">
					<tr>
						<td width="50%">Search On Service</td> 
						<td>
						  <select name="make" id="make" onclick="select_show(this)">
						  <option>SelectFromList
						  <option value="serviceClass">Service Class
						  <option value="serviceDesc">Service Description  
						  <option value="serviceType">Service Type 		
						  <option value="serviceMethod">Service Method 						  
						  <option value="serviceStatus">Service Status 	
						  <option value="serviceName">Service Name 						  
						  <option value="serviceId">Service ID					  
						  <option value="actionName">Action Name					  
						  <option value="actionDesc">Action Description					  
						  						  			  
						  						  
 						   </select>
						</td>
					</tr>
					<tr>
						<td width="50%"  id="hide1" style="display:none">Enter the value </td>
						<td width="50%">
						<input type="text"  id="txtval" name="txtSearchValue" style="display:none"/> 
						</td>						
					</tr>
					<tr>
						<td width="50%" id="hide2" style="display:none"> Select the value </td>
						<td width="50%">
								<select id="comboss1" name="comboss" disabled="disabled" style="display:none">
							      <option value="">Select Type</option>
								</select>
						</td>
					</tr>	
																		
					<tr>
						
						<td colspan="2" align="center">
							<input type="submit" value="Search" class="DispButton"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="Reset" class="DispButton"/>
							<input type="hidden" name="actionFlag" value="searchConfiguration"/>							
						</td>
					</tr>
				</table>

			</div>
         </div>
         
       
         
         <script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		
		//AJAX CODE STARTS
		function handleEmpty() 
		{
			  document.getElementById("serviceType").options.length = 0;
			  document.getElementById("serviceType").options[0] = new Option("None", "");
			  document.getElementById("serviceType").disabled = true;
		}		
		function select_show(l)
			{ 
				var id = l.value;
				if(id == '')
				{
					return;
				}
				if(id == 'serviceType'||id == 'serviceMethod'||id== 'serviceStatus')
				{
					document.getElementById('comboss1').style.display='';	
					document.getElementById('txtval').style.display='none';									
					document.getElementById('hide1').style.display='none';									
					document.getElementById('hide2').style.display='';									
				}
				else
				{
					document.getElementById('txtval').style.display='';
					document.getElementById('comboss1').style.display='none';
					document.getElementById('hide1').style.display='';									
					document.getElementById('hide2').style.display='none';		
				}
					
			}				
		</script>
		 </form>
		         
		 <!-- AJAX CODE STARTS HERE -->
		 
          <c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
          <ajax:select
		  baseUrl="${contextPath}/hdiits.htm?actionFlag=searchvaluesforcombo"
		  source="make"
		  target="comboss"
		  parameters="make={make}"
		  postFunction="handleEmpty" />
		
		<!-- body part of any page end-->
         <%
}catch(Exception e){
	e.printStackTrace();
}
        %>