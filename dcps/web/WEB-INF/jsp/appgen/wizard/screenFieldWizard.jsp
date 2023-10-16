  
<%
try 
{
//	By Keyur Patel - 202428
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page	import="java.util.List,java.util.Iterator"%>
<%@page	import="com.tcs.sgv.appgen.valueobject.AdmFieldMst,com.tcs.sgv.appgen.valueobject.AdmFldConstraint"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../../core/include.jsp"%>
<%@page import="com.tcs.sgv.appgen.valueobject.AdmConstraintMst"%>
<%@page import="com.tcs.sgv.appgen.valueobject.AdmDtlField"%>
<%@page import="java.util.Set"%>
<%@page import="com.tcs.sgv.appgen.valueobject.AdmObjectMst"%>

<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="java.util.Date"%>
<%@page import="com.tcs.sgv.appgen.constant.Constant"%>
<%@page import="com.tcs.sgv.common.dao.CmnLookupMstDAO"%>
<%@page import="com.tcs.sgv.common.valueobject.CmnLookupMst"%>
<%@page import="com.tcs.sgv.core.service.ServiceLocator"%>
<%@page import="java.util.Map"%>
<%@page import="com.tcs.sgv.common.dao.CmnLookupMstDAOImpl"%>
<fmt:setBundle basename="resources.appgen.Labels" var="appgenLables" scope="request"/>
<fmt:setBundle basename="resources.apps.diary.adminLables" 	var="adminLables" scope="request" />


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fieldList" value="${resValue.tableFieldDetailsAL}"></c:set>
<c:set var="objectDetailList" value="${resValue.objectDetailList}"></c:set>
<c:set var="editStatus" value="${resValue.editStatus}"></c:set>

<c:set var="multipleLang" value="${resValue.multipleLang}"></c:set>
<c:set var="allLanguagesList" value="${resValue.allLanguagesList}"></c:set>
<c:set var="displayStatusList" value="${resValue.displayStatusList}"></c:set>
<c:set var="multilingualStatusList" value="${resValue.multilingualStatusList}"></c:set>

<c:set var="i" value="0" />
<c:set var="fieldMstRecordIndex" value="0" />
<c:set var="fieldOrder" value="1"/>
<c:set var="languageCount" value='<%=StringUtility.getParameter("languageCount",request)%>'></c:set>
<c:set var="isCopyAllValues" value='<%=StringUtility.getParameter("isCopyAllValues",request)%>'></c:set>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="validation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>

<script type="text/javascript">
	var navDisplay = true;
</script>

<script type="text/javascript">
	var navDisplay = true;

	function resetForm()
	{
		  	if(confirm("All values will be reseted please confirm !") == true)
		  	{
			  		document.forms[0].reset();
		  	}								  				  	
	}
	
	function submitFunction()
	{	
		var multipleLang = document.getElementById('multipleLang').value;
		//alert("Multiple lang status ---> "+multipleLang);
				
		var multiLangStatusValue = document.getElementById('multiLangStatus').value;
		//alert("Multiple lang status ---> "+multiLangStatusValue);		
						
		var validationError = false;					
	
		if(multiLangStatusValue == 'YES')
		{
			//alert("Screen is multilingual...... ");		
			
			var multipleSelectError = false;
			var typeMissingError = false;
			var multiLingualStatusForCodeTypeError = false;
			var multiLingualStatusForLangTypeError = false;
						
			var multipleSelectErrorMsg = "Do only single ";
			var typeMissingErrorMsg = "Screen is multilingual - Provide one Object Type as ";
			var multiLingualStatusForCodeTypeErrorMsg;
			var multiLingualStatusForLangTypeErrorMsg;
			
			
			var finalErrorMsg;
			
			var langTypeCount = 0;
			var codeTypeCount = 0;
			
			var langTypeGiven = false;
			var codeTypeGiven = false;
			
			var objectTypeComboName;
			var multiLingualStatusNameForField;
						
			var recordCount   =	document.getElementById('recordCount').value;
			//alert("Total records  --->"+recordCount);
			
			for(var i=0; i <recordCount ;i++)
			{			
				objectTypeComboName = "object_id_common_"+i;
				
				var selectedObject = document.getElementById(objectTypeComboName);
				var objectId = selectedObject.value;
				var objectName = selectedObject.options[selectedObject.selectedIndex].text;
				//alert("Object ["+i+"] Name ---> "+objectName);
				
				if(objectName.search('langType') != -1)
				{
					langTypeGiven = true;
					++langTypeCount;	
					//checks that langType field must be multiLingual	
					multiLingualStatusNameForField = "isMultilangual_common_"+i;
					var multilingualStatus = document.getElementById(multiLingualStatusNameForField); 
					//alert("Multilingual status of the field having langType---> "+multilingualStatus.checked);
					
					if(!multilingualStatus.checked)					
					{
						multiLingualStatusForLangTypeError = true;
						multiLingualStatusForLangTypeErrorMsg = "Please make langType field multilingual";						
					}									
				}
				
				if(objectName.search('codeType') != -1)
				{
					codeTypeGiven = true;
					++codeTypeCount;
					
					//checks that codeType field must not be multiLingual	
					multiLingualStatusNameForField = "isMultilangual_common_"+i;
					var multilingualStatus = document.getElementById(multiLingualStatusNameForField); 
					//alert("Multilingual status of the field having codeType---> "+multilingualStatus.checked);
					
					if(multilingualStatus.checked)					
					{
						multiLingualStatusForCodeTypeError = true;
						multiLingualStatusForCodeTypeErrorMsg = "Please make codeType field non multilingual";						
					}					
									
				}
			}	
			
			//alert("Lang Type ---> "+langTypeGiven);
			//alert("Code Type ---> "+codeTypeGiven);	
			
			if(!langTypeGiven)
			{
				typeMissingError = true;
				typeMissingErrorMsg = typeMissingErrorMsg+" langType";
			}
			
			if(langTypeCount > 1)
			{
				multipleSelectError = true;
				multipleSelectErrorMsg = multipleSelectErrorMsg+" langType selection";
			}
						
			if(!codeTypeGiven)
			{
				typeMissingError = true;
				if(!langTypeGiven)
					typeMissingErrorMsg = typeMissingErrorMsg+" and one as codeType";
				else
					typeMissingErrorMsg = typeMissingErrorMsg+" codeType";
			}	
						
			if(codeTypeCount > 1)
			{
				multipleSelectError = true;
				if(langTypeCount > 1)
					multipleSelectErrorMsg = multipleSelectErrorMsg+" and codeType selection";
				else	
					multipleSelectErrorMsg = multipleSelectErrorMsg+" codeType selection";
			}			
			
			if(typeMissingError)
			{
				alert(typeMissingErrorMsg);		
			}
			if(multipleSelectError)
			{
				alert(multipleSelectErrorMsg);
			}
			
			if(multiLingualStatusForCodeTypeError)
			{
				alert(multiLingualStatusForCodeTypeErrorMsg);
			}			
			
			if(multiLingualStatusForLangTypeError)
			{
				alert(multiLingualStatusForLangTypeErrorMsg);
			}			

			if(!validateForm_screenFieldWizard())
			{
				validationError = true;										  	  									  				  	
			}
			
						
			if(typeMissingError || multipleSelectError || multiLingualStatusForCodeTypeError || multiLingualStatusForLangTypeError || validationError)
			{
				return true;
			}			
			else
			{
				//alert("Submit button is clicked.......");	  	
			  	document.forms[0].submit();		  									  				  	
			}
		}
		else
		{
			//alert("Screen is not multilingual...... ");
			if(!validateForm_screenFieldWizard())
			{
				validationError = true;										  	  									  				  	
			}	
			if(validationError)
			{
				return true;
			}		
			else
			{
		  		document.forms[0].submit();		  									  				  	
		  	}
		}
	}
</script>


 
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
	<c:set var="tabIter" value="1"></c:set>
	<c:forEach items="${allLanguagesList}" var="lang">				
			<li class="selected">
				<a href="#" rel= "tcontent${tabIter}" onfocus="copyDisabled()">${lang.langName}</a>
			</li>
			<c:set var="tabIter" value="${tabIter+1}"></c:set>	
	</c:forEach>		
</ul>
</div>

<hdiits:form name="screenFieldWizard" method="post" action="hdiits.htm" validate="true">
	<div class="tabcontentstyle">

	<c:set var="languageIndex" value="0"></c:set>
	<c:set var="tabIter" value="1"></c:set>	
	<c:forEach items="${allLanguagesList}" var="lang">				
		<c:set var="currentLangId" value="${lang.langId}">
		</c:set>
		
		<div id = "tcontent${tabIter}" class="tabcontent" tabno="${languageIndex}"><br />
			<br />
			<center>
			<h2><fmt:message key="FIELD_WIZARD_${lang.langId}"  bundle="${appgenLables}"/></h2>
			</center>
			<br />
			<br />
		
			<table cellspacing="0" cellpadding="0" height="10%" width="100%">
				<tr>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_COLUMN_NAME_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_ORDER_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_DATA_TYPE_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_OBJECT_ID_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_VO_PROPERTY_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_MAX_SIZE_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					
					<th class="datatableheader">
					<center><fmt:message key="FIELD_ASSOCIATE_VO_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_DEFAULT_VALUE_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_CAPTION_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_TOOLTIP_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					<th class="datatableheader">
					<center><fmt:message key="FIELD_DISPLAY_STATUS_${lang.langId}"  bundle="${appgenLables}"/></center>
					</th>
					
					<c:if test="${multipleLang eq true}">
					<th class="datatableheader">
					<center><fmt:message key="IS_MULTILINGUAL_${lang.langId}"  bundle="${appgenLables}"/></center>	
					</th>	
					</c:if>				
				</tr>
				
				<%
				Log logger = LogFactory.getLog(getClass());
				List voList = (List) pageContext.getAttribute("fieldList");
				logger.info("-------------- Adm  Field Master List size in fieldWizard.jsp ---> "+ voList.size());
				String editStatus = ((String) pageContext.getAttribute("editStatus"));
				logger.info("-------------- editStatus ---> " + editStatus);	
				List displayStatusList = (List)pageContext.getAttribute("displayStatusList");
				List multilingualStatusList = (List)pageContext.getAttribute("multilingualStatusList");
				
				long currentLangId = (Long)pageContext.getAttribute("currentLangId");
				
				AdmFieldMst admFieldMst;
				
				if(editStatus.equals("YES"))
				{
					admFieldMst = (AdmFieldMst)voList.get(0);
					long createdBy,createdByPost;
					Date createdDate;
					createdBy = admFieldMst.getCreatedBy();
					createdByPost = admFieldMst.getCreatedByPost();
					createdDate = admFieldMst.getCreatedDate();
					pageContext.setAttribute("createdBy",createdBy);
					pageContext.setAttribute("createdByPost",createdByPost);
					pageContext.setAttribute("createdDate",createdDate);
				}
					
				AdmConstraintMst admConstraintMst = null;
				Set<AdmFldConstraint> s;
				
				//Loop for all the Adm Mst Field VO
				for (int i = 0; i < voList.size(); i++) {
					admFieldMst = (AdmFieldMst) voList.get(i);
					
					
					if(editStatus.equals("NO"))
					{
						//For getting child VO "Adm Fld Constraint" to show the null property of the field
						s = (Set) admFieldMst.getAdmFldConstraints();
						logger.info("-------------- AdmFldConstraints set size ---> " + s.size());
						Iterator<AdmFldConstraint> iter = s.iterator();					
						admConstraintMst = iter.next().getAdmConstraintMst();
						String consName = admConstraintMst.getConsName();
						if (consName.equalsIgnoreCase("REQUIRED")) {
							admConstraintMst.setConsName("NOT NULL");
						} else if (consName.equalsIgnoreCase("NOT REQUIRED")) {
							admConstraintMst.setConsName("NULL");
						}
					}
					logger.info("-------------- FldColumnName ---> "+ admFieldMst.getFldColumnName());
	
					if(editStatus.equals("YES"))
					{
						//For getting child VO "Adm Fld Detail" for showing adm fld details entered by the user
						logger.info("-------------- AdmDtlField set Size ---> "+admFieldMst.getAdmDtlFields().size());
						AdmDtlField admDtlFieldVO_eng = null;				
						Set admDtlFldSet = admFieldMst.getAdmDtlFields();
						for(Iterator itera=admDtlFldSet.iterator();itera.hasNext();)
						{
							AdmDtlField admDtlFieldTemp=(AdmDtlField) itera.next();
							logger.info("-------------- admDtlFieldTemp Caption"+admDtlFieldTemp.getAdmCaption());
							logger.info("-------------- admDtlFieldTemp DefaultValue"+admDtlFieldTemp.getDefaultValue());
							logger.info("-------------- admDtlFieldTemp Tooltip"+admDtlFieldTemp.getAdmTooltip());
							if(admDtlFieldTemp.getLangId()== currentLangId){
								logger.info("-------------- Current lang id ---> "+currentLangId );
								pageContext.setAttribute("admDtlFieldVO_eng",admDtlFieldTemp);
							}
						}	
						
						//Getting Parent VO "Adm Object Mst" to show the object name for the perticular field
						AdmObjectMst admObjectMstVO = admFieldMst.getAdmObjectMst();				
						pageContext.setAttribute("admObjectMstVO",admObjectMstVO);
					}
						
					pageContext.setAttribute("admFieldMst", admFieldMst);
					pageContext.setAttribute("admConstraintMst",admConstraintMst);
					%>
					<tr>			
						<td class="tablecelltext">						
						<center>
								<c:if test="${editStatus eq 'NO'}">
								<c:choose>
									<c:when test="${admConstraintMst.consName eq 'NOT NULL'}">
										<c:choose>
											<c:when test="${admFieldMst.isPrimaryKey eq ''}">
												<hdiits:text name="fld_column_name_common_displayonly_${i}"size="18" default="* ${admFieldMst.fldColumnName}" readonly="true"/>	
											</c:when>
											<c:otherwise>
												<hdiits:text name="fld_column_name_common_displayonly_${i}"size="18" default="* ${admFieldMst.fldColumnName} (${admFieldMst.isPrimaryKey})" readonly="true" />									
											</c:otherwise>
										</c:choose>
										<input type="hidden" name="fld_column_name_common_${i}" value="*${admFieldMst.fldColumnName}">																										
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${admFieldMst.isPrimaryKey eq ''}">
												<hdiits:text name="fld_column_name_common_displayonly_${i}"size="18" default="${admFieldMst.fldColumnName}" readonly="true"/>									
											</c:when>
											<c:otherwise>
												<hdiits:text name="fld_column_name_common_displayonly_${i}"size="18" default="${admFieldMst.fldColumnName} (${admFieldMst.isPrimaryKey})" readonly="true"/>
											</c:otherwise>
										</c:choose>
										<input type="hidden" name="fld_column_name_common_${i}" value="${admFieldMst.fldColumnName}">																										
									</c:otherwise>
								</c:choose>				
								</c:if>									
								<c:if test="${editStatus eq 'YES'}">
									<c:choose>
										<c:when test="${admFieldMst.isPrimaryKey eq ''}">
											<hdiits:text name="fld_column_name_common_displayonly_${i}"size="18" default="${admFieldMst.fldColumnName}" readonly="true"/>	
										</c:when>
										<c:when test="${admFieldMst.isPrimaryKey eq 'PK' or admFieldMst.isPrimaryKey eq 'FK'}">
											<hdiits:text name="fld_column_name_common_displayonly_${i}"size="18" default="${admFieldMst.fldColumnName} (${admFieldMst.isPrimaryKey})" readonly="true" />									
										</c:when>
										<c:otherwise>											
											<hdiits:text name="fld_column_name_common_displayonly_${i}"size="18" default="${admFieldMst.fldColumnName} ${admFieldMst.isPrimaryKey}" readonly="true" />									
										</c:otherwise>
									</c:choose>
									<input type="hidden" name="fld_column_name_common_${i}" value="${admFieldMst.fldColumnName}">																										
								</c:if>	
						</center>
						</td>								
								
						<td class="tablecellnumber">
						<center>				
							<c:choose>
								<c:when test="${languageIndex+1 eq 1}">
									<hdiits:text name="fld_display_order_common_${i}" size="1" 
										default="${fieldOrder}" captionid="FIELD_ORDER_${lang.langId}" 
										bundle="${appgenLables}" validation="txt.isrequired,txt.isnumber"/>
								</c:when>
								<c:otherwise>
									<hdiits:text name="fld_display_order_common_${i}" size="1" 
										default="${fieldOrder}" readonly="true" captionid="FIELD_ORDER_${lang.langId}" 
										bundle="${appgenLables}" />
								</c:otherwise>
							</c:choose>			
						</center>
						</td>								
						
						<td class="tablecelltext">
						<center><hdiits:text name="datatype_common_${i}" size="2"
							default="${admFieldMst.fldDatatype}" readonly="true" /></center>
						</td>
						
						<input type="hidden" name="key_common_${i}" value="${admFieldMst.isPrimaryKey}">
												
						<td>
						<center>
						<c:choose>
							<c:when test="${editStatus eq 'NO'}">
								
								<c:choose>
									<c:when test="${languageIndex+1 eq 1}">
										<hdiits:select name="object_id_common_${i}" validation="sel.isrequired" captionid="DROP_DOWN_${lang.langId}" bundle="${appgenLables}">
										<hdiits:option value="ss"><fmt:message key="DEFAULT_OPTION_VALUE_${lang.langId}" bundle="${appgenLables}"/></hdiits:option>
										<c:forEach items="${objectDetailList}" var="objectDetail">
											<hdiits:option value="${objectDetail[0]}"><c:out value="${objectDetail[1]}" /></hdiits:option>
										</c:forEach>								
										</hdiits:select>												
									</c:when>
									<c:otherwise>
										<hdiits:text name="objectType_common_${i}" default="Select in main tab" size="9" readonly="true"></hdiits:text>										
									</c:otherwise>
								</c:choose>
																																	
							</c:when>
							
							<c:when test="${editStatus eq 'YES'}">
							
								<c:choose>
									<c:when test="${languageIndex+1 eq 1}">
										<hdiits:select captionid="drop_down" bundle="${adminLables}" name="object_id_common_${i}" caption="Table">
											<hdiits:option value="${admObjectMstVO.objectId}"><c:out value="${admObjectMstVO.objectName}" /></hdiits:option>
											<c:forEach items="${objectDetailList}" var="objectDetail">
												<hdiits:option value="${objectDetail[0]}"><c:out value="${objectDetail[1]}" /></hdiits:option>
											</c:forEach>										
										</hdiits:select>
									</c:when>
									<c:otherwise>
										<hdiits:text name="objectType_common_${i}" default="${admObjectMstVO.objectName}" size="9" readonly="true"></hdiits:text>			
									</c:otherwise>
								</c:choose>								

							</c:when>
						</c:choose>
						</center>
						</td>
						
						<td class="tablecelltext">
						<center>
							<c:choose>
								<c:when test="${languageIndex+1 eq 1}">
									<hdiits:text name="vo_property_common_${i}" size="7"
										default="${admFieldMst.voProperty}" readonly="true"/>
								</c:when>
								<c:otherwise>
									<hdiits:text name="vo_property_common_${i}" size="7"
										default="${admFieldMst.voProperty}" readonly="true"/>
								</c:otherwise>
							</c:choose>									
						
						</center>
						</td>

						<input type="hidden" name="null_common_${i}" value="${admConstraintMst.consName}">
						
						<td class="tablecellnumber">
						<center><hdiits:text name="fld_max_size_common_${i}" size="1"
							default="${admFieldMst.fldMaxSize}" readonly="true" /></center>
						</td>
						<%						
						//String editStatus = ((String) pageContext.getAttribute("editStatus"));
						logger.info("-------------- editStatus ---> " + editStatus);
						%>											
												
						<c:choose>
							<c:when test="${editStatus eq 'NO'}">

								<td class="tablecelltext">
								<center>
									<c:choose>
										<c:when test="${languageIndex+1 eq 1}">
											<hdiits:text name="associated_vo_common_${i}"
												bundle="${adminLables}" captionid="associateVOEng_${lang.langId}"
												default="${admFieldMst.associatedVo}" size="7"  readonly="true"/>
										</c:when>
										<c:otherwise>
											<hdiits:text name="associated_vo_common_${i}"
												bundle="${adminLables}" captionid="associateVOEng_${lang.langId}"
												default="${admFieldMst.associatedVo}" size="7"  readonly="true"/>
										</c:otherwise>
									</c:choose>									
								</center>
								</td>
								<td class="tablecelltext">
								<center><hdiits:text name="default_value_${fieldMstRecordIndex}_${languageIndex}"
									bundle="${adminLables}" captionid="defaultValueEng"
									default="" size="5"  /></center>
								</td>
								<td class="tablecelltext">
								<center><hdiits:text name="caption_${fieldMstRecordIndex}_${languageIndex}"
									captionid="FIELD_CAPTION_${lang.langId}" bundle="${appgenLables}" 
									default="${admFieldMst.voProperty}"
									size="6" validation="txt.isrequired" /></center>
								</td>
								<td class="tablecelltext">
								<center><hdiits:text name="tooltip_${fieldMstRecordIndex}_${languageIndex}"
									bundle="${adminLables}" captionid="toolTipEng" default="${admFieldMst.voProperty}"
									size="3"  /></center>
								</td>
								<c:choose>
									<c:when test="${languageIndex+1 eq 1}">
										<td>
										<center>
											<hdiits:checkbox name="displayStatus_common_${i}" value="YES" captionid="YES_${lang.langId}" 
												bundle="${appgenLables}" ></hdiits:checkbox>
										</center>
										</td>									
									</c:when>								
									<c:otherwise>
										<td class="tablecelltext">
										<center>
											<hdiits:checkbox name="displayStatus_common_${i}" value="YES" captionid="YES_${lang.langId}" 
												bundle="${appgenLables}"  readonly="true"></hdiits:checkbox>
										</center>
										</td>																												
									</c:otherwise>
								</c:choose>																
								<c:if test="${multipleLang eq true}">
									<c:choose>
										<c:when test="${languageIndex+1 eq 1}">
										
											<c:choose>
												<c:when test="${admFieldMst.isPrimaryKey eq 'PK'}">
													<td>
													<center>
														<hdiits:checkbox name="isMultilangual_common_${i}" value="YES" captionid="YES_${lang.langId}" bundle="${appgenLables}" default="YES"></hdiits:checkbox>
													</center>
													</td>
												</c:when>
												<c:otherwise>
													<td>
													<center>
														<hdiits:checkbox name="isMultilangual_common_${i}" value="YES" captionid="YES_${lang.langId}" bundle="${appgenLables}"></hdiits:checkbox>
													</center>
													</td>											
												</c:otherwise>
											</c:choose>
																				
										</c:when>								
										<c:otherwise>
											<td>
											<center>
												<hdiits:checkbox name="isMultilangual_common_${i}" value="YES" captionid="YES_${lang.langId}" bundle="${appgenLables}" readonly="true"></hdiits:checkbox>
											</center>
											</td>																												
										</c:otherwise>
									</c:choose>								
								</c:if>								
							</c:when>
							<c:when test="${editStatus eq 'YES'}">
								<td class="tablecelltext">
								<center>
									<c:choose>
										<c:when test="${languageIndex+1 eq 1}">
											<hdiits:text name="associated_vo_common_${i}"
												bundle="${adminLables}" captionid="associateVOEng"
												default="${admFieldMst.associatedVo}" size="7"
												readonly="true" />
										</c:when>
										<c:otherwise>
											<hdiits:text name="associated_vo_common_${i}"
												bundle="${adminLables}" captionid="associateVOEng"
												default="${admFieldMst.associatedVo}" size="7"
												 readonly="true"/>
										</c:otherwise>
									</c:choose>																	
								</center>
								</td>
								<td class="tablecelltext">
								<center><hdiits:text name="default_value_${fieldMstRecordIndex}_${languageIndex}"
									bundle="${adminLables}" captionid="defaultValueEng"
									default="${admDtlFieldVO_eng.defaultValue}" size="5"
									 /></center>
								</td>
								<td class="tablecelltext">
								<center><hdiits:text name="caption_${fieldMstRecordIndex}_${languageIndex}"
									captionid="FIELD_CAPTION_${lang.langId}" bundle="${appgenLables}"
									default="${admDtlFieldVO_eng.admCaption}" size="6"
									validation="txt.isrequired" /></center>
								</td>
								<td class="tablecelltext">
								<center><hdiits:text name="tooltip_${fieldMstRecordIndex}_${languageIndex}"
									bundle="${adminLables}" captionid="toolTipEng"
									default="${admDtlFieldVO_eng.admTooltip}" size="3"
									 /></center>
								</td>
								
								<input type="hidden" value="${admDtlFieldVO_eng.admDtlId}" name="dtlFld_id_${fieldMstRecordIndex}_${languageIndex}">				
								<input type="hidden" value="${admFieldMst.uniqueFldId}" name="field_mst_id_${i}">		
								<%									
								String displayStatus = (String)displayStatusList.get(i);
								String multiLangStatus = (String)multilingualStatusList.get(i); 	
								
								
								if(Constant.NOTDISPLAYABLE.equalsIgnoreCase(displayStatus))
								{
								%>
									<td>
									<center>								
										<c:choose>
											<c:when test="${languageIndex+1 eq 1}">
												<hdiits:checkbox name="displayStatus_common_${i}" value="YES" captionid="YES_${lang.langId}" 
													bundle="${appgenLables}" ></hdiits:checkbox>
											</c:when>
											<c:otherwise>
												<hdiits:checkbox name="displayStatus_common_${i}" value="YES" captionid="YES_${lang.langId}" 
												bundle="${appgenLables}" readonly="true" ></hdiits:checkbox>
											</c:otherwise>
										</c:choose>
									</center>
									</td>									
								<%
								}else if(Constant.DISPLAYABLE.equalsIgnoreCase(displayStatus))
								{
								%>
									<td>
									<center>								
										<c:choose>
											<c:when test="${languageIndex+1 eq 1}">
												<hdiits:checkbox name="displayStatus_common_${i}" value="YES" captionid="YES_${lang.langId}" 
													bundle="${appgenLables}" default="YES" ></hdiits:checkbox>
											</c:when>
											<c:otherwise>
												<hdiits:checkbox name="displayStatus_common_${i}" value="YES" captionid="YES_${lang.langId}" 
													bundle="${appgenLables}" default="YES" readonly="true"></hdiits:checkbox>
											</c:otherwise>
										</c:choose>
									</center>
									</td>						
								<%
								}
								%>
								<c:if test="${multipleLang eq true}">
								<%
									if(Constant.NONMULTILINGUAL.equalsIgnoreCase(multiLangStatus))
									{
									%>
										<td>
										<center>								
											<c:choose>
												<c:when test="${languageIndex+1 eq 1}">
													<hdiits:checkbox name="isMultilangual_common_${i}" value="YES" captionid="YES_${lang.langId}" 
														bundle="${appgenLables}"></hdiits:checkbox>
												</c:when>
												<c:otherwise>
													<hdiits:checkbox name="isMultilangual_common_${i}" value="YES" captionid="YES_${lang.langId}" 
														bundle="${appgenLables}" readonly="true"></hdiits:checkbox>
												</c:otherwise>
											</c:choose>
										</center>
										</td>						
									<% 
									}else if(Constant.MULTILINGUAL.equalsIgnoreCase(multiLangStatus))
									{
									%>
										<td>
										<center>								
											<c:choose>
												<c:when test="${languageIndex+1 eq 1}">
													<hdiits:checkbox name="isMultilangual_common_${i}" value="YES" captionid="YES_${lang.langId}" 
														bundle="${appgenLables}" default="YES" ></hdiits:checkbox>
												</c:when>
												<c:otherwise>
													<hdiits:checkbox name="isMultilangual_common_${i}" value="YES" captionid="YES_${lang.langId}" 
														bundle="${appgenLables}" default="YES" readonly="true"></hdiits:checkbox>
												</c:otherwise>
											</c:choose>
										</center>
										</td>
									<%	
									}
									%>
								</c:if>
											
							</c:when>								
						</c:choose>							
					</tr>
					<c:set var="i" value="${i+1}" />
					<c:set var="fieldOrder" value="${fieldOrder+1}" />	
					<c:set var="fieldMstRecordIndex" value="${fieldMstRecordIndex+1}" />
					<%
						logger.info("-------------- Loop iteration ends for AdmFieldMst VO");
					%>
				<%
				}
				%>
			
			</table>
			
			<c:if test="${languageIndex+1 eq 1}">
				<td><input type="hidden" name="recordCount" value="${i}"></td>
			</c:if>
					
			<br />
			<br />
			<br />
			<br />
			<br />
			
			<b><h1><fmt:message key="SYMBOL_DESCRIPTION_${lang.langId}"  bundle="${appgenLables}"/></h1></b>
			<table>
				<tr>
					<td align="right" width="900">
						<hdiits:button name="resetButton_${i}" value="Reset" onclick="resetForm()" type="button"/>
						
						<c:choose>
							<c:when test="${languageCount eq 1}">
								<hdiits:button name="submitButton_${i}" value="Finish" onclick="${copyDisabled}submitFunction()" type="button"/>								
							</c:when>
							<c:when test="${languageIndex+1 lt languageCount}">
								<c:choose>
									<c:when test="${languageIndex+1 eq 1}">
										<hdiits:button name="nextButton_${languageIndex}" value="Next" onclick="${copyDisabled}goToNextTab()" type="button"/>						           	
									</c:when>	
									<c:otherwise>
										<hdiits:button name="prevButton_${languageIndex}" value="Previous" onclick="goToPrevTab()" type="button"/>
										<hdiits:button name="nextButton_${languageIndex}" value="Next" onclick="${copyDisabled}goToNextTab()" type="button"/>						           	
									</c:otherwise>
								</c:choose>							
							</c:when>
							<c:otherwise>
								<hdiits:button name="prevButton_${languageIndex}" value="Previous" onclick="goToPrevTab()" type="button"/>
								<hdiits:button name="submitButton_${languageIndex}" value="Finish" onclick="submitFunction()" type="button"/>       		
							</c:otherwise>								
						</c:choose>
								
					</td>
				</tr>
			</table>
		
		</div>
	
		<c:set var="languageIndex" value="${languageIndex+1}"></c:set>
		<c:set var="tabIter" value="${tabIter+1}"></c:set>
		<c:set var="fieldOrder" value="1"></c:set>
		<c:set var="fieldMstRecordIndex" value="0"/>
		
		<%
				logger.info("-------------- Exit time for language loop iteration ");
		%>
		
	</c:forEach>		
	
	<script type="text/javascript">
	       	initializetabcontent("maintab")
	</script>
	
	<c:set var="i" value="0"></c:set>	
	<%int i = 0; %>
	<c:forEach items="${allLanguagesList}" var="lang">
		<input type="hidden" value="<%=StringUtility.getParameter("screenDtlId_"+i,request)%>" name="screenDtlId_${i}">
		<input type="hidden" value="<%=StringUtility.getParameter("screenCap_Lang_"+i,request)%>" name="screenCap_Lang_${i}">
		<input type="hidden" value="<%=StringUtility.getParameter("screenTitl_Lang_"+i,request)%>" name="screenTitl_Lang_${i}">
		<input type="hidden" value="${lang.langId}" name="langId_${i}">
		<c:set var="i" value="${i+1}"></c:set>	
		<%i++;%>
	</c:forEach>		
	
	<input type="hidden" value="<%=StringUtility.getParameter("editScreenId",request)%>" name="screenMstId">
	<input type="hidden" value="<%=StringUtility.getParameter("screenName_default",request)%>" name="screenName_default">
	<input type="hidden" value="<%=StringUtility.getParameter("tableId",request)%>" name="tableId">
	<input type="hidden" value="<%=StringUtility.getParameter("tableName",request)%>" name="tableName">
	<input type="hidden" value="<%=StringUtility.getParameter("voClassName",request)%>" name="voClassName">		
	
	<input type="hidden" value="${editStatus}" name="editStatus">
	<input type="hidden" value="${multipleLang}" name="multipleLang">
	<input type="hidden" value="${languageCount}" name="languageCount">
	<input type="hidden" value="<%=StringUtility.getParameter("defaultLangId",request)%>" name="defaultLangId">		
	<input type="hidden" value="<%=StringUtility.getParameter("defaultLangName",request)%>" name="defaultLangName">
	<input type="hidden" value="<%=StringUtility.getParameter("multilingualStatus",request)%>" name="multiLangStatus">	
	<input type="hidden" value="${isCopyAllValues}" name="isCopyAllValues">	
	<c:if test="${editStatus eq 'YES'}">
		<input type="hidden" value="${createdBy}" name="createdBy">
		<input type="hidden" value="${createdByPost}" name="createdByPost">	
		<input type="hidden" value="${createdDate}" name="createdDate">
	</c:if>
	
	<input type="hidden" value="addOrUpdateScreenWizardDataIntoDB" name="actionFlag">	
	</div>
		
	<hdiits:validate locale='<%=(String)session.getAttribute("locale") %>'/>
</hdiits:form>



<script language="javascript">

	function copyDisabled()
	{
	
		var sourceIndex = 0;
		<%
		List l1 = (List)pageContext.getAttribute("fieldList");
		%>
		
		var list_size  = <%=l1.size()%> 
		
		<c:forEach var="field" items="${fieldList}">
		
		var object_id_common_name_source = "object_id_common_" + sourceIndex ;
		var selectedObjectName = document.getElementById(object_id_common_name_source);
		var objectName = selectedObjectName.options[selectedObjectName.selectedIndex].text;
		
 		var fld_display_order_common_source = "fld_display_order_common_" + sourceIndex ;
		var displyOrder = document.getElementById(fld_display_order_common_source).value;
		
		var displayStatus_common_source = "displayStatus_common_" + sourceIndex;
		var displayStatus = document.getElementById(displayStatus_common_source).checked;
		
		var isMultilangual_common_source = "isMultilangual_common_" + sourceIndex;

		var langNo = 0;
			
			<c:forEach var="langSName" items="${allLanguagesList}">
			
			if(langNo != 0)
			{
			
			var multilingualStatus = document.getElementById(isMultilangual_common_source).checked;
			
			var destIndex = (list_size * langNo) + sourceIndex;
			
			var objectType_common_name = "objectType_common_" + destIndex;
			document.getElementsByName(objectType_common_name).item(0).value = objectName;
			
			var fld_display_order_common_dest = "fld_display_order_common_" + destIndex;
			document.getElementsByName(fld_display_order_common_dest).item(0).value = displyOrder;
			
			var displayStatus_common_dest = "displayStatus_common_" + destIndex;
			document.getElementsByName(displayStatus_common_dest).item(0).checked = displayStatus;
			
			var isMultilangual_common_dest = "isMultilangual_common_" + destIndex;
			document.getElementsByName(isMultilangual_common_dest).item(0).checked = multilingualStatus;
			
			}
			
			langNo = langNo + 1;
			</c:forEach>
			
		sourceIndex = sourceIndex + 1;	
		</c:forEach>	
		
	}
		
	</script>

<!-- By Prakshal Ends -->

<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
