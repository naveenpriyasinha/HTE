<%
 try {
 %>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>">
</script>
<script type="text/javascript" src="/script/common/CalendarPopup.js">
</script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>">
</script>
<fmt:setBundle basename="resources.gnl.CourtCases.CCLables" var="ccLables" scope="request" />


<table width="100%">
  
  <tr>
    <td>
    <hdiits:fieldGroup bundle="${ccLables}" expandable="true" id="applDtlFldGrp" titleCaptionId="HRMS.CRT.applDetails">
      <table width="100%" >
        <tr>
          <td>
            <b><hdiits:caption captionid="HRMS.CRT.applFieldBy" bundle="${ccLables}"/></b>
          </td>
          <td>  
            <hdiits:select name="applFieldBy" size ="1" captionid="drop_down">
              <hdiits:option value="1" >
                <fmt:message key="HRMS.CRT.party" bundle="${ccLables}"/>
              </hdiits:option>
              <hdiits:option value="2">
                <fmt:message key="HRMS.CRT.govt" bundle="${ccLables}"/>
              </hdiits:option>
            </hdiits:select>
          </td>
          <td>
            <b><hdiits:caption captionid="HRMS.CRT.dtOfFlngAppl" bundle="${ccLables}"/></b>
          </td>
          <td>
            <hdiits:dateTime captionid="HRMS.CRT.dtOfFlngAppl" bundle="${ccLables}" caption="Date Of filing Appeal" name="dtOfFlngAppl" maxvalue="31/12/2099"/>	
          </td>
        </tr>
        <tr>
          <td>
            <b><hdiits:caption captionid="HRMS.CRT.rmrks" bundle="${ccLables}"/></b>
          </td>
          <td>  
            <hdiits:textarea rows="3" cols="40" name="rmrks" caption ="Remarks" mandatory="true"/>
          </td>
          <td>
            <b><hdiits:caption captionid="HRMS.CRT.apprvlAppl" bundle="${ccLables}"/></b>
          </td>
          <td>  
            <hdiits:text name="apprvlAppl" caption ="Approval For Appleal By" mandatory="true"/>
          </td>
        </tr>
        <tr>
          <td>
            <b><hdiits:caption captionid="HRMS.CRT.instByLglDpt" bundle="${ccLables}"/></b>
          </td>
          <td>  
            <hdiits:textarea rows="3" cols="40" name="instByLglDpt" caption ="Insruction For Appel By Legal Department"/>
          </td>
          <td>
            <b><hdiits:caption captionid="HRMS.CRT.dlyCondnd" bundle="${ccLables}"/></b>
          </td>
          <td>  
            <hdiits:select name="dlyCondnd" size ="1" captionid="drop_down">
              <hdiits:option value="y" >
                <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
              </hdiits:option>
              <hdiits:option value="n">
                <fmt:message key="HRMS.No" bundle="${ccLables}"/>
              </hdiits:option>
            </hdiits:select>
          </td>
        </tr>
        <tr>
          <td>
            <b><hdiits:caption captionid="HRMS.CRT.smmrRejct" bundle="${ccLables}"/></b>
          </td>
          <td>  
            <hdiits:textarea rows="3" cols="40" name="smmrRejct" caption ="Summery Rejected"/>
          </td>
          <td>
            <b><hdiits:caption captionid="HRMS.CRT.applAdmtd" bundle="${ccLables}"/></b> 
          </td>
          <td>  
            <hdiits:select name="applAdmtd" size ="1" captionid="drop_down">
              <hdiits:option value="y" >
                <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
              </hdiits:option>
              <hdiits:option value="n">
                <fmt:message key="HRMS.No" bundle="${ccLables}"/>
              </hdiits:option>
            </hdiits:select>
          </td>
        </tr>
      </table>	
      </hdiits:fieldGroup>
    </td>
  </tr>
  
</table>
<%
 }
 catch(Exception e)
 {
 e.printStackTrace();
 }
 %>