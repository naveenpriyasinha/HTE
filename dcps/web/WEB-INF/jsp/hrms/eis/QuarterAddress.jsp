<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>

<%@page language="java" import="com.tcs.sgv.core.valueobject.ResultObject;" %>
<fmt:setBundle basename="resources.hod.common.CommonLablesAddress" var="commonLablesAddress" scope="request"/>
<fmt:message bundle="${commonLablesAddress}" key="ADDRESS.TYPE" var="defaultTitle"></fmt:message>
<fmt:message bundle="${commonLablesAddress}" key="ADDRESS.PINCODE" var="pinCodeMsg"></fmt:message>
<fmt:message bundle="${commonLablesAddress}" key="ADDRESS.SAMEAS" var="sameAsMsg"></fmt:message>
<c:set var="contextPathAddress" scope="request" value="${pageContext.request.contextPath}"></c:set>
<c:set var="name" value='<%=request.getParameter("addrName")%>' />
<c:set var="title" value='<%=request.getParameter("addressTitle")%>' />
<c:set var="lookupName" value='<%=request.getParameter("addrLookupName")%>' />
<c:set var="mandatory" value='<%=request.getParameter("mandatory")%>' />
<c:set var="condition" value='<%=request.getParameter("condition")%>' />
<c:set var="optionalParams" value='<%=request.getParameter("optionalParameters")%>' />
<c:set var="sameAsAddressTitle" value='<%=request.getParameter("sameAsAddressTitle")%>' />
<c:set var="sameAsAddrName" value='<%=request.getParameter("sameAsAddrName")%>' />
<c:set var="villageOnChangeFunction" value='<%=request.getParameter("villageOnChangeFunction")%>' />
<c:set var="collapseAddress" value='<%=request.getParameter("collapseAddress")%>' />
<c:set var="langlat" value='<%=request.getParameter("langlat")%>' />
<c:set var="countryName" value='<%=request.getParameter("countryName")%>' />
<input type="hidden" name="addressId" id="addressId<%=request.getParameter("addrName")%>" />
<input type="hidden"  name="addrContextPath${name}" id="addrContextPath${name}" value="${contextPathAddress}">
<input type="hidden"  name="hiddenlanglat${name}" value="${langlat}" id="hiddenlanglat${name}"> 
<c:set var="addressPopUP" value='<%=request.getParameter("addressPopUP")%>' />
<input type="hidden" name="addressPopUP${name}" value="${addressPopUP}" id="addressPopUP${name}">
<input type="hidden" name="countryName${name}" value="${countryName}" id="countryName${name}">
<c:set var="employeeAddress" value='<%=request.getParameter("employeeAddress")%>' />
<c:set var="villageMandatory" value='<%=request.getParameter("villageMandatory")%>' />
<% 
ResourceBundle addressBundle = ResourceBundle.getBundle("resources/Constants");
String strProjName= addressBundle.getString("PROJECT_NAME");
strProjName = "/"+strProjName+"?";
ResultObject result=(ResultObject)request.getAttribute("result");
Map resultMap=(Map)result.getResultValue();
Map myMap=(Map)resultMap.get(request.getParameter("addrName"));
Map baseLoginMap =(Map) resultMap.get("baseLoginMap");

%>
<input type="hidden"  name="addrHtmName${name}" id="addrHtmName${name}" value="<%=strProjName%>">
<%--<c:forTokens var="addressOptionalParam"  items="${optionalParams}" delims="," >
	      <c:set var="cityHouseNameValidation" value="txt.isrequired" scope="request" />
	      <c:set var="villageHouseNameValidation" value="txt.isrequired" scope="request" />
	      <c:set var="cityHouseNameMandatory" value="true" scope="request" />
	      <c:set var="villageHouseNameMandatory" value="true" scope="request" />
	   <c:if test="${addressOptionalParam eq 'txtCityHouseName'}">
	        <c:set var="cityHouseNameValidation"  /> 
	        <c:set var="cityHouseNameMandatory" value="false" /> 
	   </c:if>
	   <c:if test="${addressOptionalParam eq 'txtVillageHouseName'}">
	        <c:set var="villageHouseNameValidation"  /> 
	        <c:set var="villageHouseNameMandatory" value="false" />
	   </c:if>
</c:forTokens>--%>
<c:if test="${employeeAddress eq 'Y'}">
<input type="hidden" name="employeeAddress${name}" id="employeeAddress${name}" value="${employeeAddress}">
</c:if>
<c:if test="${employeeAddress ne 'Y'}">
<input type="hidden" name="employeeAddress${name}" id="employeeAddress${name}" value="N">
</c:if>
<c:if test="${villageMandatory eq 'No'}">
	<input type="hidden" name="villageMandatory${name}" id="villageMandatory${name}" value="${villageMandatory}">
  	<c:set var="cmbVillageValidation"/>
 	<c:set var="cmbVillageMandatory" value="false" scope="request" />
</c:if>
<c:if test="${villageMandatory ne 'No'}">
	<input type="hidden" name="villageMandatory${name}" id="villageMandatory${name}" value="Yes">
   	<c:set var="cmbVillageValidation" value="sel.isrequired" scope="request" /> 
	<c:set var="cmbVillageMandatory" value="true" scope="request"  />
</c:if>


<script type="text/javascript">

var fmtCountryOther='<fmt:message key="ADDRESS.OtherCountry" bundle="${commonLablesAddress}"/>';
var fmtStateOther= '<fmt:message key="ADDRESS.OtherState" bundle="${commonLablesAddress}"/>';	
</script>

<c:set var="addressMap" value="<%=myMap%>"> </c:set> 
<c:set var="addrbaseLoginMap" value="<%=baseLoginMap%>"> </c:set> 	
<c:set var="addrDefaultStateName" value="${addrbaseLoginMap.loggedInUserStateName}"> </c:set> 
<c:set var="addrDefaultStateCode" value=""> </c:set> 	
<c:set var="addrDefaultCountryName" value="${addrbaseLoginMap.loggedInUserCountryName}"> </c:set> 	
<c:set var="addrDefaultCountryCode" value="${addrbaseLoginMap.loggedInUserCountryCode}"> </c:set> 
<c:set var="addrCurrentLangId" value="${addrbaseLoginMap.langId}"> </c:set> 
<c:set var="addressVO" value="${addressMap.addressVO}"> </c:set> 
<c:set var="isReadOnly" value='<%=request.getParameter("isReadOnly")%>' />
<c:set var="countryList" value="${addressMap.countryList}"> </c:set>
<c:set var="stateList" value="${addressMap.stateList}"> </c:set>
<c:set var="districtList" value="${addressMap.districtList}"> </c:set>
<c:set var="cityList" value="${addressMap.cityList}"> </c:set>
<c:set var="talukaList" value="${addressMap.talukaList}"> </c:set>
<c:set var="villageList" value="${addressMap.villageList}"> </c:set>
<c:set var="cityLandmarkList" value="${addressMap.cityLandmarks}"> </c:set>
<input type="hidden" name="isReadOnly${name}" id="isReadOnly${name}" value="${isReadOnly}" }>

<c:set var="villageLandmarkList" value="${addressMap.villageLandmarks}"> </c:set>
		<c:if test='${not empty addressVO}'>
			<script language="javascript">
			   document.getElementById("addressId${name}").value=${addressVO.addressId};
			</script>
		</c:if>	
<c:set var="finalTitleAddress"></c:set>
<c:set var="mandatoryFlagAddress"></c:set>
				<c:choose>
				    <c:when test="${empty title}">
					      <c:choose>
						    <c:when test="${mandatory eq 'Yes'}">
								<c:set var="mandatoryFlagAddress" value="true"></c:set>
								<c:set var="finalTitleAddress" value="${defaultTitle}"></c:set>
							 </c:when>
							 <c:otherwise>
							   <c:set var="mandatoryFlagAddress" value="false"></c:set>
							   <c:set var="finalTitleAddress" value="${defaultTitle}"></c:set>
							 </c:otherwise>
						 </c:choose> 
					</c:when>
					<c:otherwise>
							<c:choose>
						    	<c:when test="${mandatory eq 'Yes'}">
									<c:set var="mandatoryFlagAddress" value="true"></c:set>
								    <c:set var="finalTitleAddress" value="${title}"></c:set>
							 	</c:when>
								<c:otherwise>
								   <c:set var="mandatoryFlagAddress" value="false"></c:set>
								   <c:set var="finalTitleAddress" value="${title}"></c:set>
								 </c:otherwise>
						   </c:choose> 	
				
					</c:otherwise>
				</c:choose>	
		<hdiits:fieldGroup titleCaption="${finalTitleAddress}" mandatory="${mandatoryFlagAddress}" id="fieldGroupAddress${name}" collapseOnLoad="${collapseAddress}">
			<c:if test="${not empty sameAsAddrName}">
					<table width="100%" id="copyAddressTable${name}">
					      <tr>
					     	 	<td colspan="4">&nbsp;</td>
					     	 	<td nowrap align="right">
					     	 	  <c:choose>
					     	 	  <c:when test="${addrCurrentLangId eq 1}">
										<a href="javascript:copyAddress('${sameAsAddrName}','${name}','${pageContext.request.contextPath}')" id="sameAsLink${name}">${sameAsMsg} ${sameAsAddressTitle}</a>					     	 	    
					     	 	   </c:when>
					     	 	  <c:otherwise>
					     	 	        <a href="javascript:copyAddress('${sameAsAddrName}','${name}','${pageContext.request.contextPath}')" id="sameAsLink${name}">${sameAsAddressTitle} ${sameAsMsg}</a> 
					     	 	  </c:otherwise>
					     	 	  </c:choose>
					      		</td> 
					      </tr>  
					</table>
			</c:if>	
			<table width="100%" id="countryStateTable${name}" >
			      <tr>
			      		<!--<td width="25%"><hdiits:caption captionid="ADDRESS.Country" bundle="${commonLablesAddress}"/></td>
			      		<td width="25%">
			      			<hdiits:select sort="false" mandatory="true" name="cmbCountry${name}" id="cmbCountry${name}" captionid="ADDRESS.Country" bundle="${commonLablesAddress}" validation="sel.isrequired"    onchange="populateState('cmbCountry${name}','cmbState${name}','getAllStates','${pageContext.request.contextPath}','countryCode','${name}')">
			      			 
			      			  <c:choose>
			      			 	<c:when test='${empty addressVO}'>
					        		 <hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
					        		 <hdiits:option value="${addrDefaultCountryCode}" selected="true"> <c:out value="${addrDefaultCountryName}" /></hdiits:option>
					        		 <hdiits:option value="OtherCountry"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.OtherCountry"/></hdiits:option>
					      		</c:when>
			            		<c:otherwise>
					         		<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
					         			<c:forEach var="countryVO" items="${countryList}">
					            		    <c:choose>
							                     <c:when test="${addressVO.cmnCountryMst.countryCode==countryVO.countryCode}">
							                       <hdiits:option selected="true" value="${countryVO.countryCode}">${countryVO.countryName}</hdiits:option>
							                     </c:when>
							                    <c:otherwise>
							                     <hdiits:option  value="${countryVO.countryCode}">${countryVO.countryName}</hdiits:option>
							                    </c:otherwise>
					                		</c:choose>
					           			</c:forEach> 
					           		<hdiits:option value="OtherCountry"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.OtherCountry"/></hdiits:option>	  
			            		</c:otherwise>
			            		</c:choose>
			      			</hdiits:select>
			      		</td>

			      		-->
			      		
			      		<td width="25%"><hdiits:caption captionid="ADDRESS.State" bundle="${commonLablesAddress}"/></td>
			      					      		
			      		<td width="25%" id="tdCmbState${name}">
			      			<hdiits:select mandatory="true" sort="false" name="cmbState${name}"  id="cmbState${name}" captionid="ADDRESS.State" bundle="${commonLablesAddress}" validation="sel.isrequired"  condition='checkStateValidation("${name}","Combo")' onchange="showOther('${name}','othertbl${name}',document.forms[0].cmbState${name}[document.forms[0].cmbState${name}.selectedIndex].value,document.forms[0].cmbCountry${name}[document.forms[0].cmbCountry${name}.selectedIndex].value,'${pageContext.request.contextPath}')">
			      		
			               			<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
			             				
			             				<c:forEach var="stateVO" items="${stateList}">
			         			      	<c:choose>
						                  	  	<c:when test="${addressVO.cmnStateMst.stateCode==stateVO.stateCode}">
						                       		<hdiits:option selected="true" value="${stateVO.stateCode}">${stateVO.stateName}</hdiits:option>
						                   		 </c:when>
						                   	    <c:otherwise>
						                       		<hdiits:option  value="${stateVO.stateCode}">${stateVO.stateName}</hdiits:option>
						                    	</c:otherwise>
			              	  		 		</c:choose>
						                    	
			              				</c:forEach>
			              				 <hdiits:option value="OtherState" ><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.OtherState"/></hdiits:option>
			            
			     			</hdiits:select>
			      		</td>
			      		<td width="25%" id="tdTxtState${name}" style="display:none"><hdiits:text  name="txtState${name}" id="txtState${name}" captionid="ADDRESS.State" bundle="${commonLablesAddress}" validation="txt.isrequired"  condition='checkStateValidation("${name}","Text")' default="${addressVO.stateName}" maxlength="150"></hdiits:text></td>
			      	</tr>  
				</table>	
			<table width="100%" id="radioTable${name}" style="display:none" >
				<tr>
					<td width="80%">
						<hdiits:caption captionid="ADDRESS.TYPE" bundle="${commonLablesAddress}"/>
		          			<c:choose>
		          				<c:when test="${mandatory eq 'Yes'}">
				          			  <c:choose>
								            <c:when test="${empty condition}">
								                <hdiits:radio   value="City" id="rdoAddressCity${name}"  captionid="ADDRESS.CITY" name="rdoAddress${name}" bundle="${commonLablesAddress}" validation="sel.isradio" errCaption="${title}"    onclick="showAddress('villagetbl${name}','citytbl${name}','cmbCity${name}','getAllCity','${pageContext.request.contextPath}','districtCode','','${name}')"/>
								         		<hdiits:radio value="Village" id="rdoAddressVillage${name}" captionid="ADDRESS.VILLAGE" name="rdoAddress${name}" bundle="${commonLablesAddress}"  onclick="hideAddress('villagetbl${name}','citytbl${name}','cmbDistrict${name}','getAllDistrict','${pageContext.request.contextPath}','stateCode',document.forms[0].cmbState${name}[document.forms[0].cmbState${name}.selectedIndex].value,'${name}')" />  
								         		<hdiits:radio value="OtherRadioAddress"  id="rdoAddressOther${name}"  readonly="true" captionid="ADDRESS.OTHER" name="rdoAddress${name}" bundle="${commonLablesAddress}"  onclick="showOther('${name}','othertbl${name}',document.forms[0].cmbState${name}[document.forms[0].cmbState${name}.selectedIndex].value,document.forms[0].cmbCountry${name}[document.forms[0].cmbCountry${name}.selectedIndex].value,'${pageContext.request.contextPath}')"  />  
								         	 </c:when>
								            <c:otherwise>
								                <hdiits:radio   value="City" id="rdoAddressCity${name}" condition="${condition}"  captionid="ADDRESS.CITY" name="rdoAddress${name}" bundle="${commonLablesAddress}" validation="sel.isradio" errCaption="${title}"  onclick="showAddress('villagetbl${name}','citytbl${name}','cmbCity${name}','getAllCity','${pageContext.request.contextPath}','districtCode','','${name}')"/>
								        	    <hdiits:radio value="Village" id="rdoAddressVillage${name}"  captionid="ADDRESS.VILLAGE" name="rdoAddress${name}" bundle="${commonLablesAddress}"  onclick="hideAddress('villagetbl${name}','citytbl${name}','cmbDistrict${name}','getAllDistrict','${pageContext.request.contextPath}','stateCode',document.forms[0].cmbState${name}[document.forms[0].cmbState${name}.selectedIndex].value,'${name}')" />  
								        	    <hdiits:radio value="OtherRadioAddress" id="rdoAddressOther${name}" readonly="true"   captionid="ADDRESS.OTHER" name="rdoAddress${name}" bundle="${commonLablesAddress}"  onclick="showOther('${name}','othertbl${name}',document.forms[0].cmbState${name}[document.forms[0].cmbState${name}.selectedIndex].value,document.forms[0].cmbCountry${name}[document.forms[0].cmbCountry${name}.selectedIndex].value,'${pageContext.request.contextPath}')" />
									      </c:otherwise>
					      			 </c:choose>
			    			    </c:when>
			    				<c:otherwise>
						   			  <hdiits:radio   value="City" id="rdoAddressCity${name}" name="rdoAddress${name}" captionid="ADDRESS.CITY" bundle="${commonLablesAddress}" onclick="showAddress('villagetbl${name}','citytbl${name}','cmbCity${name}','getAllCity','${pageContext.request.contextPath}','districtCode','','${name}')"/>
						    		  <hdiits:radio value="Village" id="rdoAddressVillage${name}" name="rdoAddress${name}" captionid="ADDRESS.VILLAGE" bundle="${commonLablesAddress}" onclick="hideAddress('villagetbl${name}','citytbl${name}','cmbDistrict${name}','getAllDistrict','${pageContext.request.contextPath}','stateCode',document.forms[0].cmbState${name}[document.forms[0].cmbState${name}.selectedIndex].value,'${name}')" />  
						     		  <hdiits:radio value="OtherRadioAddress" id="rdoAddressOther${name}" readonly="true"  captionid="ADDRESS.OTHER" name="rdoAddress${name}" bundle="${commonLablesAddress}"  onclick="showOther('${name}','othertbl${name}',document.forms[0].cmbState${name}[document.forms[0].cmbState${name}.selectedIndex].value,document.forms[0].cmbCountry${name}[document.forms[0].cmbCountry${name}.selectedIndex].value,'${pageContext.request.contextPath}')" />
						 		</c:otherwise>
			    		</c:choose>
	  			</td>
				<td width="20%" align="right"><hdiits:a id="resetAddressLink${name}" href="javascript:closeAddress('${name}')"  bundle="${commonLablesAddress}" captionid="ADDRESS.Reset"></hdiits:a></td>
			</tr>
		</table>  
			
			 <table class="tabtable" id="villagetbl${name}" style="display:none" >
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Housename" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text   name="txtVillageHouseName${name}" default="${addressVO.houseName}" validation="${villageHouseNameValidation}" condition='checkVillageValidation("rdoAddress${name}")' captionid="ADDRESS.Housename" 	size="20" bundle="${commonLablesAddress}" maxlength="150" ></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Faliyu" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtFaliyu${name}" default="${addressVO.faliyu}" size="20" maxlength="150"></hdiits:text></td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"> <hdiits:caption captionid="ADDRESS.District" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%">
						<hdiits:select sort="false" name="cmbDistrict${name}" id="cmbDistrict${name}" captionid="ADDRESS.District" bundle="${commonLablesAddress}" validation="sel.isrequired"  condition='checkVillageValidation("rdoAddress${name}")'  onchange="populateNextLevel('cmbDistrict${name}','cmbTaluka${name}','getAllTaluka','${pageContext.request.contextPath}','districtCode','${name}')">
							  <c:choose>
							  <c:when test='${empty addressVO}'>	
								<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
				  			  </c:when>
				    		  <c:otherwise>
				    			<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
										<c:forEach  var="dList" items="${districtList}">
										     <c:choose>
											       <c:when test="${addressVO.cmnDistrictMst.districtCode==dList.districtCode}">
											      <hdiits:option value="${dList.districtCode}" selected="true">${dList.districtName}</hdiits:option>
											       </c:when>
										      		<c:otherwise>
											      <hdiits:option value="${dList.districtCode}">${dList.districtName}</hdiits:option>
										      		</c:otherwise>
										     </c:choose>
										</c:forEach>
				    		</c:otherwise>
				    	  </c:choose>		
						</hdiits:select>
					</td>
					<td class="fieldLabel" width="25%">  <hdiits:caption captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%">
						<hdiits:select sort="false"  name="cmbTaluka${name}" id="cmbTaluka${name}" captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}" validation="sel.isrequired"  condition='checkVillageValidation("rdoAddress${name}")' onchange="populateNextLevel('cmbTaluka${name}','cmbVillage${name}','getAllVillage','${pageContext.request.contextPath}','talukaCode','${name}')">
				 			 <c:choose>
				 			 <c:when test='${empty addressVO}'>	
								<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.SelectDistrict"/></hdiits:option>
				   			 </c:when>
				  			 <c:otherwise>
				   				<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
										<c:forEach var="tList" items="${talukaList}">
										     <c:choose>
										       <c:when test="${addressVO.cmnTalukaMst.talukaCode==tList.talukaCode}">
										      <hdiits:option value="${tList.talukaCode}" selected="true">${tList.talukaName}</hdiits:option>
										      </c:when>
										      <c:otherwise>
										      <hdiits:option value="${tList.talukaCode}">${tList.talukaName}</hdiits:option>
										      </c:otherwise>
										     </c:choose>
										</c:forEach>
				   			</c:otherwise>
				   		  </c:choose>	
						</hdiits:select>
					</td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%">  <hdiits:caption captionid="ADDRESS.VILLAGE" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%">
						<hdiits:select name="cmbVillage${name}" sort="false" id="cmbVillage${name}"  captionid="ADDRESS.VILLAGE" bundle="${commonLablesAddress}" validation="${cmbVillageValidation}"  condition='checkVillageValidation("rdoAddress${name}")' onchange="populateLandmarks('cmbVillageLandmark${name}','getAllLandmark','${pageContext.request.contextPath}','landmarkType','VillageLandmarks',document.forms[0].cmbVillage${name}[document.forms[0].cmbVillage${name}.selectedIndex].value,'${name}'),eval(${villageOnChangeFunction})" >
						  <c:choose>
							 <c:when test='${empty addressVO}'>	
								<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.SelectTaluka"/></hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
										<c:forEach var="vList" items="${villageList}">
										     <c:choose>
											       <c:when test="${addressVO.cmnVillageMst.villageCode==vList.villageCode}">
											      <hdiits:option value="${vList.villageCode}" selected="true">${vList.villageName}</hdiits:option>
											      </c:when>
											      <c:otherwise>
											      <hdiits:option value="${vList.villageCode}">${vList.villageName}</hdiits:option>
											      </c:otherwise>
										     </c:choose>
									   </c:forEach>
							</c:otherwise>
						  </c:choose>	 	
						</hdiits:select>
					</td>
					<TD class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Important_land_mark" bundle="${commonLablesAddress}"/></TD>
					<td class="fieldLabel" width="25%">
						<hdiits:select sort="false" name="cmbVillageLandmark${name}" id="cmbVillageLandmark${name}">
							<c:choose>
							<c:when test='${empty addressVO}'>	
								<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.SelectVillage"/></hdiits:option>
				   	 		</c:when>
				    		<c:otherwise>
				    			<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.SelectVillage"/></hdiits:option>
											<c:forEach var="vlandmarkList" items="${villageLandmarkList}">
										    	<c:choose>
											       <c:when test="${addressVO.impLandmark.landmarkCode eq vlandmarkList.landmarkCode}">
											       <hdiits:option  value="${vlandmarkList.landmarkCode}" selected="true">${vlandmarkList.landmarkName}</hdiits:option>
											       </c:when>
											      <c:otherwise>
											      <hdiits:option value="${vlandmarkList.landmarkCode}">${vlandmarkList.landmarkName}</hdiits:option>
											      </c:otherwise>
										     	</c:choose>
											</c:forEach>
				    		</c:otherwise>
				    	  </c:choose>	
						</hdiits:select>
					</td>
				</tr>
				<tr>	
					<TD class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Other_details" bundle="${commonLablesAddress}"/></TD>
					<td><hdiits:textarea   cols="17" name="txtareaVillageOtherDetails${name}" id="txtareaVillageOtherDetails${name}" default="${addressVO.otherDetails}" maxlength="150"></hdiits:textarea></td>
					<TD class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.PINCODE" bundle="${commonLablesAddress}"/></TD>
					<td><hdiits:number validation="txt.isnumber"   name="txtVillagePincode${name}"  default="${addressVO.pincode}"   size="20" maxlength="6" captionid="ADDRESS.PINCODE" onblur="cssCheck('txtVillagePincode${name}','${pinCodeMsg}')" condition='checkVillageValidation("rdoAddress${name}")' bundle="${commonLablesAddress}" ></hdiits:number></td>
				</tr>
			</table>  
			<table id="citytbl${name}" style="display:none" class="tabtable">
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Housename" bundle="${commonLablesAddress}"    /></td>
					<td class="fieldLabel" width="25%"><hdiits:text   name="txtCityHouseName${name}" id="txtCityHouseName${name}" default="${addressVO.houseName}"	validation="${cityHouseNameValidation}" captionid="ADDRESS.Housename"  condition='checkCityValidation("rdoAddress${name}")'	size="20" bundle="${commonLablesAddress}" maxlength="150"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Society_name" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text   name="txtSocietyName${name}" default="${addressVO.socBuildName}"	size="20" maxlength="150"></hdiits:text> </td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Street" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text   name="txtStreet${name}" default="${addressVO.street}"	size="20" maxlength="150"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Area" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text captionid="ADDRESS.Area"  name="txtArea${name}" default="${addressVO.area}"	size="20" validation="txt.isrequired"  condition='checkCityValidation("rdoAddress${name}")' bundle="${commonLablesAddress}" maxlength="150"></hdiits:text></td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.CITY" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%">
				 		<hdiits:select name="cmbCity${name}" sort="false" id="cmbCity${name}" captionid="ADDRESS.CITY" validation="sel.isrequired"  condition='checkCityValidation("rdoAddress${name}")' bundle="${commonLablesAddress}" onchange="populateLandmarks('cmbCityLandmark${name}','getAllLandmark','${pageContext.request.contextPath}','landmarkType','CityLandmarks',document.forms[0].cmbCity${name}[document.forms[0].cmbCity${name}.selectedIndex].value,'${name}')" >
							<c:choose>
							<c:when test='${empty addressVO}'>
				 				<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
				    		</c:when>
				    		<c:otherwise>
				    			<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
									<c:forEach var="cList" items="${cityList}">
								   	 	<c:choose>
									      	 <c:when test="${addressVO.cmnCityMst.cityCode==cList.cityCode}">
									     	 	<hdiits:option value="${cList.cityCode}" selected="true">${cList.cityName}</hdiits:option>
									     	 </c:when>
									     	 <c:otherwise>
									       		<hdiits:option value="${cList.cityCode}">${cList.cityName}</hdiits:option>
									      	 </c:otherwise>
								    	</c:choose>
									</c:forEach>
				    		</c:otherwise>
				    		</c:choose>
						</hdiits:select>
					</td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Other_details" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:textarea cols="17" id="txtareaCityOtherDetails${name}" name="txtareaCityOtherDetails${name}" default="${addressVO.otherDetails}" maxlength="150"></hdiits:textarea></td>
				</tr>
			<tr>
					<TD class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Important_land_mark" bundle="${commonLablesAddress}"/></TD>
				<td class="fieldLabel" width="25%">
					<hdiits:select name="cmbCityLandmark${name}" sort="false" id="cmbCityLandmark${name}">
				   		<c:choose>
				   		<c:when test='${empty addressVO}'>
							<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.SelectCity"/></hdiits:option>
				   		</c:when>
				   		<c:otherwise>
				   			<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.SelectCity"/></hdiits:option>
					             <c:forEach var="clandmarkList" items="${cityLandmarkList}">
						    		 <c:choose>
						       			<c:when test="${addressVO.impLandmark.landmarkCode eq clandmarkList.landmarkCode}">
						      				<hdiits:option value="${clandmarkList.landmarkCode}" selected="true">${clandmarkList.landmarkName}</hdiits:option>
						      			</c:when>
						      			<c:otherwise>
						      				<hdiits:option value="${clandmarkList.landmarkCode}">${clandmarkList.landmarkName}</hdiits:option>
						      			</c:otherwise>
						     		</c:choose>
								</c:forEach>
				   		</c:otherwise>
				   		</c:choose>
					</hdiits:select>
				</td>
				<TD class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.PINCODE" bundle="${commonLablesAddress}"/></TD>
				<td class="fieldLabel" width="25%"><hdiits:number name="txtCityPincode${name}" validation="txt.isnumber" maxlength="6" default="${addressVO.pincode}" captionid="ADDRESS.PINCODE" onblur="cssCheck('txtCityPincode${name}','${pinCodeMsg}')" condition='checkCityValidation("rdoAddress${name}")' bundle="${commonLablesAddress}"  /></td>
			</tr>
			</table>
		<table class="tabtable" id="othertbl${name}" style="display:none" >
			<tr>
				<td class="fieldLabel" width="25%">	    <hdiits:caption captionid="ADDRESS.Housename" bundle="${commonLablesAddress}"/></td>
				<td class="fieldLabel" width="25%"> <hdiits:text mandatory="true"   name="txtOtherHouseName${name}" default="${addressVO.houseName}"  captionid="ADDRESS.Housename" 	size="20" bundle="${commonLablesAddress}" maxlength="150" ></hdiits:text></td>
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Society_name" bundle="${commonLablesAddress}"/></td>
				<td class="fieldLabel" width="25%">  <hdiits:text mandatory="true"   name="txtOtherSocietyName${name}" default="${addressVO.socBuildName}"	size="20" maxlength="150"></hdiits:text> </td>
			</tr>
			<tr>
				<td class="fieldLabel" width="25%">	<hdiits:caption captionid="ADDRESS.Area" bundle="${commonLablesAddress}"/></td>
				<td class="fieldLabel" width="25%"> <hdiits:text mandatory="true" captionid="ADDRESS.Area"  name="txtOtherArea${name}" default="${addressVO.area}"	size="20" validation="txt.isrequired"   bundle="${commonLablesAddress}" condition='checkAreaValidation("${name}")' maxlength="150"></hdiits:text></td>
				<td class="fieldLabel" width="25%"> <hdiits:caption captionid="ADDRESS.District" bundle="${commonLablesAddress}"/></td>
				<td class="fieldLabel" width="25%" id="tdCmbDistrict${name}">
						<hdiits:select sort="false" mandatory="true" name="cmbOtherDistrict${name}" id="cmbOtherDistrict${name}" captionid="ADDRESS.District" bundle="${commonLablesAddress}" validation="sel.isrequired"  condition='checkDistrictValidation("${name}")'>
							<c:choose>
							<c:when test='${empty addressVO}'>
								<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
				    		</c:when>
				    		<c:otherwise>
				    			<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
										<c:forEach  var="dListOther" items="${districtList}">
										     <c:choose>
										       	<c:when test="${addressVO.cmnDistrictMst.districtCode==dListOther.districtCode}">
										      		<hdiits:option value="${dListOther.districtCode}" selected="true">${dListOther.districtName}</hdiits:option>
										      	</c:when>
										      	<c:otherwise>
										      		<hdiits:option value="${dListOther.districtCode}">${dListOther.districtName}</hdiits:option>
										      	</c:otherwise>
										     </c:choose>
										</c:forEach>
				    		</c:otherwise>
				    		</c:choose>
						</hdiits:select>
				</td>
				<td class="fieldLabel" width="25%" id="tdTxtDistrict${name}" style="display:none"><hdiits:text name="txtOtherDistrict${name}" id="txtOtherDistrict${name}" captionid="ADDRESS.District" bundle="${commonLablesAddress}"  default="${addressVO.districtName}" maxlength="150"/></td>
			</tr>
			<tr>
				<td class="fieldLabel" width="25%">  <hdiits:caption captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}"/></td>
				<td class="fieldLabel" width="25%"><hdiits:text   name="txtOtherTaluka${name}" id="txtOtherTaluka${name}" captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}" default="${addressVO.talukaName}" maxlength="150"/></td>
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.CITYVILLAGE" bundle="${commonLablesAddress}"/></td>
				<td class="fieldLabel" width="25%">
					<hdiits:text name="txtOtherCityVillage${name}"  id="txtOtherCityVillage${name}" captionid="ADDRESS.CITYVILLAGE" validation="txt.isrequired" bundle="${commonLablesAddress}" condition='checkAreaValidation("${name}")' default="${addressVO.cityVilllageName}"  maxlength="150"/></td>
				</tr>
				<tr>	
					<TD class="fieldLabel"  width="25%" ><hdiits:caption captionid="ADDRESS.PINCODE" bundle="${commonLablesAddress}"/></TD>
					<td><hdiits:number    name="txtOtherPincode${name}" validation="txt.isnumber"   size="20" maxlength="6" captionid="ADDRESS.PINCODE"   bundle="${commonLablesAddress}" default="${addressVO.pincode}" onblur="cssCheck('txtOtherPincode${name}','${pinCodeMsg}')"></hdiits:number></td>
					<td width="25%">&nbsp;</td><td width="25%">&nbsp;</td>
				</tr>
			</table>
			
			<table class="tabtable" id="langlat${name}" style="display:none" >		
			<tr>
				<td class="fieldLabel" width="25%">  <hdiits:caption captionid="ADDRESS.Longitude" bundle="${commonLablesAddress}"/></td>
				<td class="fieldLabel" width="25%"><hdiits:number  name="txtlang${name}" id="txtlang${name}" captionid="ADDRESS.Longitude" bundle="${commonLablesAddress}" maxlength="8"  default="${addressVO.longitude}" onblur="longitudeCheck('${name}')"/></td>
				<td class="fieldLabel" width="25%">  <hdiits:caption captionid="ADDRESS.Latitude" bundle="${commonLablesAddress}"/></td>
				<td class="fieldLabel" width="25%"><hdiits:number  name="txtlat${name}" id="txtlat${name}" captionid="ADDRESS.Latitude" bundle="${commonLablesAddress}" maxlength="8"  default="${addressVO.latitude}" onblur="latitudeCheck('${name}')"/></td>
			</tr>
			</table>
		
			<table id="employeeAddresstbl${name}" style="display:none" class="tabtable">
				<tr>
					<td class="fieldLabel" width="25%"></td>
					<td class="fieldLabel" width="25%"></td>
					<td class="fieldLabel" width="25%"></td>
				<td width="20%" align="right"><hdiits:a id="resetAddressLink${name}" href="javascript:closeAddress('${name}')"  bundle="${commonLablesAddress}" captionid="ADDRESS.Reset"></hdiits:a></td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Housename" bundle="${commonLablesAddress}"    /></td>
					<td class="fieldLabel" width="25%"><hdiits:text   name="txtempAddHouseName${name}" default="${addressVO.houseName}"	 captionid="ADDRESS.Housename"  size="20" bundle="${commonLablesAddress}" maxlength="150"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Society_name" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text   name="txtempAddSocietyName${name}" default="${addressVO.socBuildName}" captionid="ADDRESS.Society_name"	size="20" maxlength="150"></hdiits:text> </td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Street" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text   name="txtempAddStreet${name}" default="${addressVO.street}" captionid="ADDRESS.Street"	size="20" maxlength="150"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Area" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text captionid="ADDRESS.Area"  name="txtempAddArea${name}"  default="${addressVO.area}"  size="20" validation="txt.isrequired"  condition='checkempAreaValidation("${name}")' bundle="${commonLablesAddress}" maxlength="150"></hdiits:text></td>
				</tr>
					<tr>
					<td class="fieldLabel" width="25%"> <hdiits:caption captionid="ADDRESS.District_City" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%" id="tdCmbempAddDistrict${name}"> 
						<hdiits:select sort="false" name="cmbempAddDistrict${name}" id="cmbempAddDistrict${name}" captionid="ADDRESS.District_City" bundle="${commonLablesAddress}" validation="sel.isrequired"  onchange="populateNextLevel('cmbempAddDistrict${name}','cmbempAddTaluka${name}','getAllTaluka','${pageContext.request.contextPath}','districtCode','${name}')" condition='checkempDistrictValidation("${name}")'>
							  <c:choose>
							  <c:when test='${empty addressVO}'>	
								<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
				  			  </c:when>
				    		  <c:otherwise>
				    			<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
										<c:forEach  var="dList" items="${districtList}">
										     <c:choose>
											       <c:when test="${addressVO.cmnDistrictMst.districtCode == dList.districtCode}">
											      <hdiits:option value="${dList.districtCode}" selected="true">${dList.districtName}</hdiits:option>
											       </c:when>
										      		<c:otherwise>
											      <hdiits:option value="${dList.districtCode}">${dList.districtName}</hdiits:option>
										      		</c:otherwise>
										     </c:choose>
										</c:forEach>
				    		</c:otherwise>
				    	  </c:choose>		
						</hdiits:select>
					</td>
					<td class="fieldLabel" width="25%" id="tdTxtempAddDistrict${name}" style="display:none"><hdiits:text name="txtempAddDistrict${name}" id="txtempAddDistrict${name}" captionid="ADDRESS.District_City" bundle="${commonLablesAddress}"  default="${addressVO.districtName}" maxlength="150"/></td>
					<td class="fieldLabel" width="25%">  <hdiits:caption captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%" id="tdcmbempAddTaluka${name}">
						<hdiits:select sort="false"  name="cmbempAddTaluka${name}" id="cmbempAddTaluka${name}" captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}">
				 			 <c:choose>
				 			 <c:when test='${empty addressVO}'>	
								<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.SelectDistrict"/></hdiits:option>
				   			 </c:when>
				  			 <c:otherwise>
				   				<hdiits:option value="Select"><fmt:message bundle="${commonLablesAddress}" key="ADDRESS.Select"/></hdiits:option>
										<c:forEach var="tList" items="${talukaList}">
										     <c:choose>
										       <c:when test="${addressVO.cmnTalukaMst.talukaCode==tList.talukaCode}">
										      <hdiits:option value="${tList.talukaCode}" selected="true">${tList.talukaName}</hdiits:option>
										      </c:when>
										      <c:otherwise>
										      <hdiits:option value="${tList.talukaCode}">${tList.talukaName}</hdiits:option>
										      </c:otherwise>
										     </c:choose>
										</c:forEach>
				   			</c:otherwise>
				   		  </c:choose>	
						</hdiits:select>
					</td>
				<td class="fieldLabel" width="25%" id="tdTxtempAddTaluka${name}" style="display:none"><hdiits:text name="txtempAddTaluka${name}" id="txtempAddTaluka${name}" captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}"  default="${addressVO.talukaName}" maxlength="150"/></td>	
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.Other_details" bundle="${commonLablesAddress}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:textarea cols="17" name="txtareaempAddOtherDetails${name}" id="txtareaempAddOtherDetails${name}" default="${addressVO.otherDetails}" maxlength="150"></hdiits:textarea></td>
					<TD class="fieldLabel" width="25%"><hdiits:caption captionid="ADDRESS.PINCODE" bundle="${commonLablesAddress}"/></TD>
				<td class="fieldLabel" width="25%"><hdiits:number name="txtempAddPincode${name}" validation="txt.isnumber" maxlength="6" default="${addressVO.pincode}" captionid="ADDRESS.PINCODE" onblur="cssCheck('txtCityPincode${name}','${pinCodeMsg}')"  bundle="${commonLablesAddress}"  /></td>
				</tr>
			</table>
			
			<input type="hidden" name="addressDefaultStateCode" id="addressDefaultStateCode${name}"  value="${addrDefaultStateCode}"/>	
			<input type="hidden" name="addressDefaultCountryCode"  id="addressDefaultCountryCode${name}"  value="${addrDefaultCountryCode}"/>
			<input type="hidden" name="addressDefaultStateName" id="addressDefaultStateName${name}"  value="${addrDefaultStateName}"/>	
			<input type="hidden" name="addressDefaultCountryName"  id="addressDefaultCountryName${name}"  value="${addrDefaultCountryName}"/>		
			<input type="hidden" name="addressNames" id="${name}"  value="${name}"/>
			<input type="hidden" name="addressLookUpNames" id="${lookupName}"  value="${lookupName}"/>
			 <c:if test='${empty addressVO}'>
				<script language="javascript">
				if(document.getElementById('addressPopUP${name}').value == 'Y')
				{
					poulateAddress('${name}');
				}
				if(document.getElementById('countryName${name}').value != null && document.getElementById('countryName${name}').value != '')
				{
					getCountryCode('${name}');
					populateState("cmbCountry${name}","cmbState${name}","getAllStates","${pageContext.request.contextPath}","countryCode","${name}");
				}
				var empAdd = document.getElementById('employeeAddress${name}').value;
				if(empAdd == 'Y')
				{
					document.getElementById('employeeAddresstbl${name}').style.display='';
					document.getElementById('radioTable${name}').style.display='none';
					populateFirstLevel('cmbempAddDistrict${name}',"getAllDistrict","${pageContext.request.contextPath}",'stateCode',document.forms[0].cmbState${name}[document.forms[0].cmbState${name}.selectedIndex].value,"${name}");	
				}
	    			//populateCountries('cmbCountry${name}','getAllCountries','${pageContext.request.contextPath}','countryCode','','${name}');	
				//	setCountry('${name}','${addrDefaultCountryCode}');
					//populateState("cmbCountry${name}","cmbState${name}","getAllStates","${pageContext.request.contextPath}","countryCode","${name}");
				//	setState('${name}','${addrDefaultStateCode}');
				</script>
			</c:if>
		</hdiits:fieldGroup>
		   <c:choose>
		       <c:when test="${not empty addressVO}">
					<c:if test="${empty stateList}">
			        	 <script language="javascript">
				             if(document.getElementById('tdCmbState${name}')!= null)
				             {
				              document.getElementById('tdCmbState${name}').style.display='none';
				             }
				             if( document.getElementById('tdCmbDistrict${name}')!= null)
				             {
				              document.getElementById('tdCmbDistrict${name}').style.display='none';
				             }
				             if(document.getElementById('tdTxtState${name}')!= null)
				             {
				              document.getElementById('tdTxtState${name}').style.display='';
				             }
				             if(document.getElementById('tdTxtDistrict${name}')!= null)
				             {
				              document.getElementById('tdTxtDistrict${name}').style.display='';
				             }
			             </script>
			        </c:if>  
					<c:if test="${isReadOnly eq 'Yes'}">
					    <script language="javascript">
					        makeReadOnly('${name}');
					    </script>
					</c:if>
		<c:choose>
		<c:when test="${addressVO.cmnLookupMstAddTypeLookupid.lookupName eq 'City'}">
		     <script language="JavaScript">
		     	  var empAddress = document.getElementById('employeeAddress${name}').value;
		     	  if(empAddress != 'Y')
		     	  {
			     	document.getElementById("citytbl${name}").style.display='';
		          	var langlat = document.getElementById("hiddenlanglat${name}");
		          	if(langlat == 'Y')
		          	{
			          	document.getElementById("langlat${name}").style.display='';
		          	}
			     	var addtype= document.getElementById("rdoAddressCity${name}");
			     	addtype.checked=true;
			     	document.getElementById("rdoAddressOther${name}").disabled=true;
		     	  }
			</script>
		</c:when>
		<c:when test="${addressVO.cmnLookupMstAddTypeLookupid.lookupName eq 'Village'}">
		     <script language="JavaScript">
		     	var empAddress = document.getElementById('employeeAddress${name}').value;
	     	  	if(empAddress != 'Y')
	     	  	{
			   		document.getElementById("villagetbl${name}").style.display='';
			   		var langlat = document.getElementById("hiddenlanglat${name}");
		          	if(langlat == 'Y')
		          	{
			          	document.getElementById("langlat${name}").style.display='';
		          	}
			     	var addtype= document.getElementById("rdoAddressVillage${name}");
			      	addtype.checked=true;
			     	document.getElementById("rdoAddressOther${name}").disabled=true;
	     	  }
			</script>
		</c:when>
		<c:otherwise>
			<script language="JavaScript">
				var empAddress = document.getElementById('employeeAddress${name}').value;
     	  		if(empAddress != 'Y')
     	  		{
			  	 	document.getElementById("othertbl${name}").style.display='';
			  	 	var langlat = document.getElementById("hiddenlanglat${name}");
		         	 if(langlat == 'Y')
		         	 {
			          	document.getElementById("langlat${name}").style.display='';
		         	 }
			     	var addtype= document.getElementById("rdoAddressOther${name}");
			      	addtype.checked=true;
			      	addtype.disabled=false;
			      	document.getElementById("rdoAddressCity${name}").disabled=true;
			      	document.getElementById("rdoAddressVillage${name}").disabled=true;
     	  		}
			</script>	
		</c:otherwise>
	</c:choose>
	<c:if test="${addressVO.cmnLookupMstAddTypeLookupid.lookupName ne 'null'}">
		<c:if test="${addressVO.cmnCountryMst.countryCode eq addrDefaultCountryCode}">
		     	<c:if test="${addressVO.cmnStateMst.stateCode eq addrDefaultStateCode}">
		     	 <script language="javascript">
		      	var empAddress = document.getElementById('employeeAddress${name}').value;
		    	if(empAddress == 'Y')
     	  		{
         	    		document.getElementById('tdTxtempAddDistrict${name}').style.display='none';
		    	    	document.getElementById('tdTxtempAddTaluka${name}').style.display='none';
		    	    	document.getElementById('tdcmbempAddDistrict${name}').style.display='';
		    	    	document.getElementById('tdcmbempAddTaluka${name}').style.display='';
     	  		}
		    	  </script>
			     </c:if>
			     <c:if test="${addressVO.cmnStateMst.stateCode ne addrDefaultStateCode}">
			     	 <script language="javascript">
			     	var empAddress = document.getElementById('employeeAddress${name}').value;
			     	if(empAddress == 'Y')
	     	  		{
			     		document.getElementById('tdTxtempAddDistrict${name}').style.display='none';
		    	    	document.getElementById('tdTxtempAddTaluka${name}').style.display='';
		    	    	document.getElementById('tdcmbempAddDistrict${name}').style.display='';
		    	    	document.getElementById('tdcmbempAddTaluka${name}').style.display='none';
	     	  		}
		    	     </script>
			     </c:if>
			     </c:if>
			     <c:if test="${addressVO.cmnCountryMst.countryCode ne addrDefaultCountryCode}">
			     <script language="javascript">
			     var empAddress = document.getElementById('employeeAddress${name}').value;
	     	  		if(empAddress == 'Y')
	     	  		{
			     		document.getElementById('tdTxtempAddDistrict${name}').style.display='';
		    	    	document.getElementById('tdTxtempAddTaluka${name}').style.display='';
		    	    	document.getElementById('tdcmbempAddDistrict${name}').style.display='none';
		    	    	document.getElementById('tdcmbempAddTaluka${name}').style.display='none';
	     	  		}
		    	  </script>
		    	  </c:if>
		    <script language="JavaScript">
		    var empAddress = document.getElementById('employeeAddress${name}').value;
 	  		if(empAddress == 'Y')
 	  		{
		   		document.getElementById('employeeAddresstbl${name}').style.display='';
				document.getElementById('radioTable${name}').style.display='none';
 	  		}	
			</script>
		</c:if>
		</c:when>
		</c:choose>
	<%
	  String strAddrName=request.getParameter("addrName");
      StringBuffer sbControlNames=new StringBuffer();
	  sbControlNames.append("rdoAddress"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtCityHouseName"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtSocietyName"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtStreet"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtArea"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("cmbCity"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtareaCityOtherDetails"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("cmbCityLandmark"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtCityPincode"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtVillageHouseName"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtFaliyu"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("cmbVillage"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("cmbTaluka"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("cmbDistrict"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("cmbVillageLandmark"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtareaVillageOtherDetails"+strAddrName);
	  sbControlNames.append(',');
	  sbControlNames.append("txtVillagePincode"+strAddrName);
      request.setAttribute(strAddrName+"_Parameters",sbControlNames.toString());
	%>
	
<input type="hidden"  name="addrContextPath${name}" id="addrContextPath${name}" value="${contextPathAddress}">

 <c:if test='${empty addressVO}'>
<script>
var temp= 'othertbl'+'<%=request.getParameter("addrName")%>';

document.getElementById("cmbState"+'<%=request.getParameter("addrName")%>').value='OtherState';

showOther('<%=request.getParameter("addrName")%>',temp,'OtherState',1,'./');
</script>
</c:if>