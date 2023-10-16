<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../core/include.jsp"%>

<table class="tabNavigationBar">
	<tr align="center">
		<td class="tabnavtdcenter" id="tabnavtdcenter" >		      
              <!-- <input type="reset" value="Reset"/>-->
              
			<hdiits:button name="Add"  type="button" value="Add" onclick="return addRecord();" />	
			
              <script language="javaScript"> 
			                          
              if (navDisplay)
              {
                //document.write('<input type="button" value="Add" onclick="return addRecord();">');
				//document.write('<input type="button" value="Reset" onClick="resetFormm()">');
				//document.write('<input type="button" value="Previous" onClick="goToPrevTab()">');
				//document.write('<input type="button" value="Next" onClick="goToNextTab()">');
			  }
			  
			 /* function resetFormm()
			  {
			 
			  if(confirm("All values will be reseted please confirm !") == true)
			  	{
			  	
			  	
			  		document.forms[0].reset();
			  	}
			  				  	
			  }*/
				</script>
				<!-- Message in gujarati is still remains....Jaspal.. -->
		</td>
	</tr>
</table>