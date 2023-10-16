 
<%try{%>



<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" >
	var navDisplay = false;
</script>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="screenlist" value="${resValue.serviceList}"> </c:set>	  

 <hdiits:form name="screendetail" validate="true" method="post" action="./hdiits.htm">  
 <div id="tabmenu">
          <ul id="maintab" class="shadetabs">
           <li class="selected" >
            <a href="#" rel="tcontent1">
          		Select Table 
            </a>
           </li>
           </ul>
          </div>  
          
   			 <div class="tabcontentstyle" >
          	<div id="tcontent1" class="tabcontent" tabno="0">
   
			<table>
			<tr>
             <td>Select Table from List</td>
             <td>
            
	            <hdiits:select name="screenId" caption="Table"  validation="sel.isrequired">
			    <hdiits:option value="ss">Select from List</hdiits:option>
			    <c:forEach var="srvc" items="${screenlist}">
			    <hdiits:option value="${srvc[1]}"><c:out value="${srvc[0]}"/></hdiits:option>
              	   		         </c:forEach>
    		         </hdiits:select> 	
        		        
            </td>
           </tr>
           <tr>
						<td colspan="2" align="center">
							<input type="hidden" name="actionFlag" value="getAllData" > 		
							
						</td>
					</tr>
				</table>
 		</div>		

<jsp:include page="../core/tabnavigation.jsp?"/>  
         <script type="text/javascript">
         	initializetabcontent("maintab")
         </script>
         </div>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale") %>' />
		</hdiits:form>

<%}catch(Exception e)
{
	e.printStackTrace();
}
%>
