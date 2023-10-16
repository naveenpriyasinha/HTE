<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="resources.common.BranchMasterLables" var="LocBrachMpg" scope="request" />

<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<br>

<hdiits:fieldGroup titleCaptionId="LOC_BRANCH_MPG" bundle="${LocBrachMpg}" id="loc_branch_mpg_id">

	<table id="locbranch_mpg_tbl_id" border="0" align="center" width="50%" borderColor="black" align="center" width="70%" cellpadding="1" cellspacing="5" style="border-collapse: collapse;">
    	<tr align="center">
    		<td> 
    			<b><hdiits:caption captionid="UNASSGND_BRANCH" bundle="${LocBrachMpg}" /></b>
    		</td> 
    		<td></td>
    		<td>
    			<b><hdiits:caption captionid="ASSGND_BRANCH" bundle="${LocBrachMpg}" /></b>
    		</td>  
    	</tr>
    	<tr align="center">
	        <td>
	            <hdiits:select name="unassignedBranch" id="unassignedBranch" multiple="true">
	            </hdiits:select>
	        </td>
	        <td>
	        	  <table>
		        	  	<tr>
		        	  		<td>
		        	  			<hdiits:button name="next" type="button" value=" > " onclick="moveBranch('Next')"></hdiits:button>
		        	  		</td>
		        	  	</tr>
		        	  	<tr></tr><tr></tr>
		        	  	<tr>	
		        	  		<td>	
		        	  			<hdiits:button name="prvs" type="button" value=" < " onclick="moveBranch('Prev')"></hdiits:button>
		        	  		</td>	
		        	  	</tr>
	        	  </table> 	
	         </td>
	        <td>
	            <hdiits:select name="assignedBranch" id="assignedBranch" multiple="true">
	    		</hdiits:select>
	        </td>
    	</tr>
 	</table>

</hdiits:fieldGroup>