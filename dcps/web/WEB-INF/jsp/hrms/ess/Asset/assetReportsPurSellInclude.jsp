<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%
try
{%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<fmt:setBundle basename="resources.ess.asset.AssetReports" var="assetReportsLables" scope="request"/> 


  <tr>
              
            <td>&nbsp;</td>
            <td class="captionTag"><hdiits:caption captionid="ASSET.REPORTS_REQ_TYPE" bundle="${assetReportsLables}"/>
            </td>
            <td>&nbsp;:</td>
              
              
                <td colspan="1"> 
              
                            
              
              
                  <SELECT class="selecttag" style="width: 155;" size="1" name="reqType" id="reqType" attrTitle="reqType" type="select-one" onchange="displayActuals(this)">
                <option value="-1"><fmt:message key="ASSET.REPORTS_DROPDOWN_SELECT" bundle="${assetReportsLables}"/></option>
				<option value="Both"><fmt:message key="ASSET.REPORTS_BOTH" bundle="${assetReportsLables}"/></option>
				<option value="Permission"><fmt:message key="ASSET.REPORTS_REQ_PERMISSION" bundle="${assetReportsLables}"/></option>
				<option value="Intimation"><fmt:message key="ASSET.REPORTS_REQ_INTIMATION" bundle="${assetReportsLables}"/></option>
              </select>
            </td>
            
            <td>&nbsp;</td>
            
            <td>&nbsp;</td>
            <td class="captionTag" id="ActualSub"><hdiits:caption captionid="ASSET.REPORTS_ACTUAL_SUB" bundle="${assetReportsLables}"/>
            </td>
            <td id="ActualSubCol">&nbsp;:</td>
              
              
                <td colspan="1"> 
              
                            
              
              
     <SELECT class="selecttag" style="width: 155;" size="1" name="actualsubmitted" id="actualsubmitted" attrTitle="actualsubmitted" type="select-one" >
    <option value="-1"><fmt:message key="ASSET.REPORTS_DROPDOWN_SELECT" bundle="${assetReportsLables}"/></option>
    <option value="Both"><fmt:message key="ASSET.REPORTS_BOTH" bundle="${assetReportsLables}"/></option>
	<option value="YES"><fmt:message key="ASSET.REPORTS_ACTUAL_YES" bundle="${assetReportsLables}"/></option>
	<option value="NO"><fmt:message key="ASSET.REPORTS_ACTUAL_NO" bundle="${assetReportsLables}"/></option>
                
              </select>
            </td>
            
            <td>&nbsp;</td>
                 
                      </tr>
<script language="javascript">
function displayActuals(l)
{	
	var actuals = l.value;
	//alert(actuals);
	if(actuals == '')
	{
		return;
	}
	if(actuals == 'Intimation' || actuals == '-1')
	{
		document.getElementById("ActualSub").style.display = "none";
		document.getElementById("actualsubmitted").style.display = "none";
		document.getElementById("ActualSubCol").style.display = "none";
	}
	else
	{
		document.getElementById("ActualSub").style.display = "";
		document.getElementById("actualsubmitted").style.display = "";
		document.getElementById("ActualSubCol").style.display = "";
	}
}


</script>
<%}catch(Exception e)
  {
  e.printStackTrace();
  }
%>