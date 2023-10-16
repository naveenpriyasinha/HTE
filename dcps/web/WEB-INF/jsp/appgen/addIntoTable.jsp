<% 
	try {
%>

<%@page
	import="java.util.List,java.util.Map,
				java.util.Set,
				com.tcs.sgv.appgen.valueobject.AdmFldConstraint,
				com.tcs.sgv.appgen.constant.Constant,
				com.tcs.sgv.appgen.valueobject.AdmFieldMst,
				com.tcs.sgv.common.valueobject.CmnLanguageMst,
				java.util.ArrayList,
				com.tcs.sgv.appgen.valueobject.AdmDtlField,
				com.tcs.sgv.common.utils.DBUtility,
				java.text.SimpleDateFormat,
				java.util.Date"
%>

<%@page import="com.tcs.sgv.core.service.ServiceLocator"%>
<%@page import="com.tcs.sgv.common.dao.CmnLookupMstDAO"%>
<%@page import="com.tcs.sgv.common.dao.CmnLookupMstDAOImpl"%>
<%@page import="com.tcs.sgv.common.valueobject.CmnLookupMst"%>
<html>

	<%@ include file="../core/include.jsp"%>

	<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

	<script type="text/javascript" >
		var navDisplay = false;
	</script>

	<script type="text/javascript" src="script/common/calendar.js">
	</script>

<%
	pageContext.setAttribute("textNotRequired",Constant.TEXTNOTREQUIRED);
	pageContext.setAttribute("radio",Constant.RADIO);
	pageContext.setAttribute("checkbox",Constant.CHECKBOX);
	pageContext.setAttribute("select",Constant.SELECT);
	pageContext.setAttribute("textarea",Constant.TEXTAREA);
	pageContext.setAttribute("textNumber",Constant.TEXTNUMBER);
	pageContext.setAttribute("textDate",Constant.TEXTDATE);
%>

	<script language="javascript">
			function loadcalendar(name,img)
			{
				var cal1 = new CalendarPopup();
				cal1.select(name,img,'dd/MM/yyyy'); return false;
			}
	</script>

	<fmt:setBundle basename="resources.apps.diary.DiaryLables"
		var="adminLables" scope="request" />

	<fmt:setLocale value="en_US" />

	<c:set var="resultObj" value="${result}">
	</c:set>

	<c:set var="resValue" value="${resultObj.resultValue}">
	</c:set>

	<c:set var="multiLang" value="${resValue.multiLang}">
	</c:set>

	<c:set var="langList" value="${resValue.langList}">
	</c:set>

	<c:set var="multiLangStatusLt" value="${resValue.multiLangStatusLt}">
	</c:set>

	<c:set var="mstFieldList" value="${resValue.mstFieldList}">
	</c:set>

	<c:set var="screenId" value="${resValue.screenId}">
	</c:set>

	<c:set var="editMode" value="${resValue.editMode}">
	</c:set>

	<c:set var="dataMap" value="${resValue.dataMap}">
	</c:set>

	<c:set var="submitName" value="Insert"/>

	<c:if test="${editMode eq 'Y'}">

	<c:set var="submitName" value="Update"/>

	</c:if>
		<c:set var="guj" value="_gu">
	</c:set>

	<script type="text/javascript">
		var navDisplay = ${multiLang};
	</script>

	<c:out value="${resValue.msg}" />

	<hdiits:form name="screendetail" method="post" action="hdiits.htm" validate="true">

	<hdiits:hidden name="actionFlag" default="insertMasterData" />

	<hdiits:hidden name="is_multilingual" default="${multiLang}" />

	<hdiits:hidden name="screen_id" default="${screenId}" />

	<hdiits:hidden name="editMode" default="${editMode}" />

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
		<%			
			List<String[]> disabledList = new ArrayList<String[]>();
			List<String[]> notDisabledList = new ArrayList<String[]>();
			List multiLangStatusLt = (List) pageContext.getAttribute("multiLangStatusLt");
			int disabledCounter = 0;
			int notDisabledCounter = 0;
			List<String> langSNameList = new ArrayList<String>();
			List<CmnLanguageMst> langList = (List) pageContext.getAttribute("langList");
			Map resValue = (Map) pageContext.getAttribute("resValue");
			Map dataMap = (Map) resValue.get("dataMap");
			String editMode = (String) resValue.get("editMode");
			if (!"Y".equals(editMode))
				editMode = "N";

			///////////////// MAKING TAB FOR ALL LANGUAGES////////////////////////////
			for(int i = 0; i<langList.size();i++)
			{
				CmnLanguageMst langVO = langList.get(i);
				String tabLangName = langVO.getLangName();
				String tabLangSName = langVO.getLangShortName();
				String tabName = "tcontent" + (i+1) ;

				if(i==0)
				{
		%>
			<li class="selected"><a href="#" rel="<%=tabName%>"><%=tabLangName%></a></li>
		<%
				}
				else
				{
		%>
			<li class="selected"><a href="#" onfocus="copyDisabled()" rel="<%=tabName%>"><%=tabLangName%></a></li>

		<%		}	// else ENDS HERE..

			}	//FOR ENDS HERE

			/////////////	MAKING TAB FOR ALL LANGUAGES ENDS HERE		///////////////
		%>

		</ul>
	</div>

	<div class="tabcontentstyle">

	<%		
		for(int i = 0; i<langList.size();i++)
		{			
			//////////////		LOOP FOR EACH LANGUAGE FORM START		/////////////
			boolean rowflag = false;
			CmnLanguageMst langVO = langList.get(i);
			String langName = langVO.getLangName();
			String langSName = langVO.getLangShortName();
			long langID = langVO.getLangId();
			boolean defaultLang = false;
			String tabName = "tcontent" + (i+1) ;

			if(langVO.getLangId() == Constant.DEFAULTLANGID)
			{
				defaultLang = true;
				pageContext.setAttribute("defaultLangName",langVO.getLangShortName());
				
			}
			else
			{
				langSNameList.add(langVO.getLangShortName());
			}

			pageContext.setAttribute("langName",langName);
			pageContext.setAttribute("langSName",langSName);
			//pageContext.setAttribute("langID",langID);
			pageContext.setAttribute("defaultLang",defaultLang);
		%>

		<div id="<%=tabName%>" class="tabcontent" tabno="<%=i%>">
		
			<table cellpadding="3" cellspacing="3">
			<%
				List<AdmFieldMst> mstFieldList = (List) pageContext.getAttribute("mstFieldList");
				for(int j = 0; j<mstFieldList.size();j++) /////////////////////// LOOP FOR EACH FIELD START HERE//////////////////////////////////////////
				{
					AdmFieldMst mstFieldVO = mstFieldList.get(j);
					
					//START///ADDED BY PRK FOR COPY ALL///////////////////////////////////////
					String copyAll = mstFieldVO.getAdmScreenMst().getIsCopyall();
					pageContext.setAttribute("copyAll",copyAll);
					//END///ADDED BY PRK FOR COPY ALL///////////////////////////////////////
					
					String objectName = mstFieldVO.getAdmObjectMst().getObjectName();
					String ObjectType = mstFieldVO.getAdmObjectMst().getObjectType();
					String controlName = mstFieldVO.getVoProperty();
					String validation = "";
					int validationcounter = 0;
					String mandatory = "false";
					String maxlength = (mstFieldVO.getFldMaxSize() == null)?Constant.DEFAULTMAXLENGTH:mstFieldVO.getFldMaxSize().toString();
					String caption = "NO CAPTION";
					String defaultValue = "";
					
					//System.out.println("===========================================objectName============================="+objectName);
					//System.out.println("===========================================ObjectType============================="+ObjectType);					
					//System.out.println("===========================================controlName============================="+controlName);

					/////////////////		ADDING VALIDATIONS FROM FIELD CONSTRAINT TABLE 		///////////////////

					Set fieldConstraintSet = mstFieldVO.getAdmFldConstraints();
					Object[] fieldConstraintArray = (Object[])fieldConstraintSet.toArray();

					if(fieldConstraintArray.length != 0)
					{
						for(int constraintLoop = 0; constraintLoop<fieldConstraintArray.length;constraintLoop++)
						{
							AdmFldConstraint fieldConstraint = (AdmFldConstraint)fieldConstraintArray[constraintLoop];

							/* if(fieldConstraint.getAdmConstraintMst().getConsName().equals(Constant.REQUIRED))
							//	if(fieldConstraint.getOnEvent().equals("taglib"))
							{
								if(validationcounter != 0)
								{
									validation = validation+",";
								}
								validation = validation + "txt.isrequired";
								mandatory = "true";

								validationcounter++;
							}
							*/
						}
					}

					////////////////////////////ADDING VALIDATIONS FROM FIELD CONSTRAINT TABLE ENDS///////////


					////////////////////////////FROM FIELD DETAILS FINDING ANG ADDING CAPTIONS START//////////
					Set detailFieldSet = mstFieldVO.getAdmDtlFields();
					Object[] fieldDetails = (Object[])detailFieldSet.toArray();

					if(fieldDetails.length != 0)
					{
						for(int detailloop = 0; detailloop<fieldDetails.length;detailloop++)
						{
							AdmDtlField tempforcaption = (AdmDtlField)fieldDetails[detailloop];
							if(tempforcaption.getLangId() == langID)
							{
								caption = tempforcaption.getAdmCaption();
	
								if(tempforcaption.getDefaultValue() != null)
									defaultValue = tempforcaption.getDefaultValue();
							}
						}
					}
			
					pageContext.setAttribute("ObjectType",ObjectType);
					pageContext.setAttribute("controlName",controlName);
					pageContext.setAttribute("caption",caption);
					pageContext.setAttribute("defaultValue",defaultValue);
					pageContext.setAttribute("maxlength",maxlength);

					String disabled = "false";
					String[] forDisabledList = new String[2];
					////115//// CONDITION IS FOR IF SPECIFIC CONTROL TO BE ADDED IN FORM OR NOT/////////////
					String multiLangStatus = (String)multiLangStatusLt.get(j);					
					
//					Map resultMap = (Map)pageContext.getAttribute("resValue");								
//					ServiceLocator serv = (ServiceLocator)resultMap.get("serviceLocator");
//					long multiLangStatusId = mstFieldVO.getIsMultilingual();
					
//					System.out.println("===========================================multiLangStatusId============================="+multiLangStatusId);
					
//					CmnLookupMstDAO lookUpDaoImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					
//					System.out.println("===========================================lookUpDaoImpl============================="+lookUpDaoImpl);
					
//					CmnLookupMst lookUpMstVO = lookUpDaoImpl.read(multiLangStatusId);								
					
//					System.out.println("===========================================lookUpMstVO============================="+lookUpMstVO);
					
//					String multiLangStatus = lookUpMstVO.getLookupName(); 	
					
					
					//String multiLangStatus = "NONMULTILINGUAL";
					
					if((defaultLang != true) && !(Constant.MULTILINGUAL.equalsIgnoreCase(multiLangStatus)))
					{
						disabled = "true";

						forDisabledList[0] = controlName;
						forDisabledList[1] = ObjectType;

						disabledList.add(forDisabledList);
						disabledCounter++;
						pageContext.setAttribute("disabledCounter",disabledCounter);
						//pageContext.setAttribute("disabledControl"+disabledCounter,controlName);
						pageContext.setAttribute("disabledList",disabledList);
					}   //START///ADDED BY PRK FOR COPY ALL ON 4th Feb 2008////////////////////////
					else if(defaultLang != true)
					{
						forDisabledList[0] = controlName;
						forDisabledList[1] = ObjectType;

						notDisabledList.add(forDisabledList);
						notDisabledCounter++;
						pageContext.setAttribute("notDisabledCounter",notDisabledCounter);
						//pageContext.setAttribute("disabledControl"+disabledCounter,controlName);
						pageContext.setAttribute("notDisabledList",notDisabledList);
						
						
						
					}  //END///ADDED BY PRK FOR COPY ALL ON 4th Feb 2008////////////////////////

					pageContext.setAttribute("disabled",disabled);

					////////////////////////////SETTING CONTROL NAME i.e. name of the text box etc. START///////////////////////	
					
					if(Constant.MULTILINGUAL.equalsIgnoreCase(multiLangStatus) || ( !(Constant.MULTILINGUAL.equalsIgnoreCase(multiLangStatus)) && defaultLang != true))
					{
						controlName = controlName + "__" + langSName;
					}

					////////////////////////////SETTING CONTROL NAME i.e. name of the text box etc. END///////////////////////		

					pageContext.setAttribute("controlName",controlName);
			%>

			<%

				///////////////Setting defaultValue for EDIT MODE/////////////
				
				if(("Y".equals(editMode)) && (disabled.equals("false")))
				{
//					defaultValue = dataMap.get(controlName).toString();
					//Change by Tapan
					if(dataMap.get(controlName) != null) {
						defaultValue = dataMap.get(controlName).toString();
					} else {
						defaultValue = "";
					}
					pageContext.setAttribute("defaultValue",defaultValue);
				}

				//change in the following two if conditions
				//SimpleDateFormat introduced to format the date
				//Change By: Tapan Maru (Emp ID: 223766)
				if (objectName.equals(Constant.CREATEDDATE) && editMode.equals("N"))
				{
					Date sysDate = DBUtility.getCurrentDateFromDB();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String dateAfterParsing = sdf.format(sysDate);
//					defaultValue = sysDate;
					defaultValue = dateAfterParsing;
					pageContext.setAttribute("defaultValue",defaultValue);
				}

				if (objectName.equals(Constant.UPDATEDDATE) && editMode.equals("Y"))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date sysDate = DBUtility.getCurrentDateFromDB();
					String dateAfterParsing = sdf.format(sysDate);
//					defaultValue = sysDate;
					defaultValue = dateAfterParsing;
					pageContext.setAttribute("defaultValue",defaultValue);
				}

				///////////////Setting defaultValue for EDIT MODE/////////////
			%>

			<%
				if(ObjectType.equals(Constant.HIDDEN))
				{
					///////////////////ADDING HIDDEN TEXT BOX
			%>

					<hdiits:hidden name="${controlName}" default="${defaultValue}" />

			<%
				}

				if (ObjectType.equals(Constant.TEXT))
				{
					//////////////		ADDING TEXT BOX AND WITH VALIDATION BY OBJECT TYPE		//////////////////////

					///////////VALIDATION FOR REQUIRED//////////////////////

					if(mstFieldVO.getFldColumnName() != null && mstFieldVO.getFldColumnName().startsWith("*"))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.isrequired";
						mandatory = "true";
						validationcounter++;
					}

					if(objectName.equals(Constant.TEXTINPUT))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}

						validation = validation+"txt.isname";
						// rtnw there is no validaiton in hdiits taglib for entering only text... so using alphanumeric input for only text
						validationcounter++;
					}
					else if(objectName.equals(Constant.EMAILINPUT))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.email";
						validationcounter++;
					}
					else if(objectName.equals(Constant.NUMBERINPUT))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}

						validation = validation+"txt.isnumber";
						validationcounter++;
					}
					else if(objectName.equals(Constant.PHONEINPUT))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.isphone";
						validationcounter++;
					}
					else if(objectName.equals(Constant.PININPUT))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.isnumber";
						validationcounter++;
					}
					else if(objectName.equals(Constant.PASSWORDINPUT))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.ispass";
						validationcounter++;
					}
					else if(objectName.equals(Constant.ALPHANUMERICINPUT))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.isname";
						validationcounter++;
					}

					pageContext.setAttribute("validate",validation);
					pageContext.setAttribute("mandatory",mandatory);
			%>

			<%
				if(!rowflag)
				{
			%>
				<tr>
			<%
				}
			%>
					<td>
						<c:out value="${caption}" />
					</td>

					<td>
						<hdiits:text id="${controlName}" name="${controlName}"  caption="${caption}" readonly="${disabled}" default="${defaultValue}" validation="${validate}" mandatory="${mandatory}" maxlength="${maxlength}"/>
					</td>

			<%
				if(rowflag)
				{
			%>
					</tr>
			<%
				}
				else
				{
			%>
					<td width="20">
			<%
				}
				rowflag = !rowflag;
			%>

			<%
				}	//////////		ADDING TEXT BOX ENDS HERE		///////// 

				////////		FOR PASSWORD INPUT NEW		////////

				if (ObjectType.equals(Constant.PASSWORD))
				{
					/////////		ADDING PASSWORD BOX AND WITH VALIDATION BY OBJECT TYPE		//////////
	
					if(mstFieldVO.getFldColumnName() != null && mstFieldVO.getFldColumnName().startsWith("*"))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.isrequired"; 
						mandatory = "true";
						validationcounter++;
					}

					if(objectName.equals(Constant.PASSWORDINPUT))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.ispass";
						validationcounter++;
					}

					pageContext.setAttribute("validate",validation);
					pageContext.setAttribute("mandatory",mandatory);
			%>

			<%
				if(!rowflag)
				{
			%>
					<tr>
			<%
				}
			%>

				<td>
					<c:out value="${caption}" />
				</td>

				<td>
					<hdiits:password  readonly="${disabled}"  id="${controlName}" type="txt-password" name="${controlName}" caption="${caption}"  default="${defaultValue}" validation="${validate}" mandatory="${mandatory}"/>
				</td>

			<%
				if(rowflag)
				{
			%>
					</tr>
			<%
				}
				else
				{
			%>
					<td width="20">
			<%
				}
				rowflag = !rowflag;
			%>

			<%
				}	//////		ADDING TEXT BOX ENDS HERE		///////

				/////////////////FOR PASSWORD INPUT NEW////////////////////////////

				if (ObjectType.equals(Constant.DATETIME))
				{
					//////////		ADDING DATE INPUT BOX		////////

					if(mstFieldVO.getFldColumnName() != null && mstFieldVO.getFldColumnName().startsWith("*"))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"txt.isrequired";
						mandatory = "true";
						validationcounter++;
					}

					pageContext.setAttribute("validate",validation);
					pageContext.setAttribute("mandatory",mandatory);

					if(editMode.equals("Y") && disabled.equals("false") && defaultValue.length() == 21)
					{
						defaultValue = defaultValue.substring(0,defaultValue.length()-2);
						pageContext.setAttribute("defaultValue",defaultValue);
					}

					//defaultValue="${defaultValue}"
			%>

			<%
				if(!rowflag)
				{
			%>
					<tr>
			<%
				}
			%>
				<td>
					<c:out value="${caption}" />
				</td>

				<td>
					<hdiits:dateTime disabled="${disabled}"  validation="${validate}"  name="${controlName}" default="${defaultValue}"  caption="${caption}" mandatory="${mandatory}"/>
				</td>

			<%
				if(rowflag)
				{
			%>
					</tr>
			<%
				}
				else
				{
			%>
				<td width="20">
			<%
				}
					rowflag = !rowflag;
			%>

			<%
				}	/////////		ADDING DATE INPUT BOX ENDS HERE		/////////

				if (ObjectType.equals(Constant.RADIO))
				{
					////////////////////////ADDING RADIO BUTTONS//////////////////////

					if(mstFieldVO.getFldColumnName() != null && mstFieldVO.getFldColumnName().startsWith("*"))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}

						validation = validation+"sel.isradio";
						mandatory = "true";
						validationcounter++;
					}
					pageContext.setAttribute("validate",validation);
					pageContext.setAttribute("mandatory",mandatory);

					String  fieldColumnNmae = mstFieldVO.getFldColumnName();
					List optionList = (List)resValue.get(fieldColumnNmae);

					pageContext.setAttribute("optionList",optionList);
			%>

			<%
				if(!rowflag)
				{
			%>
					<tr>

			<%
				}
			%>
				<td>
					<c:out value="${caption}" />
				</td>

				<td>
					<table>
						<c:forEach var="rld" items="${optionList}">
			<%
						Object[] obj = (Object[])pageContext.getAttribute("rld");

						if(obj[2].equals("0") || obj[2].equals(langID))
						{
			%>
							<tr>
								<td>
									<hdiits:radio name="${controlName}"
										value="${rld[0]}"  readonly="${disabled}" default="${defaultValue}" errCaption="${controlName}"
										validation="${validate}"  caption="${caption}" mandatory="${mandatory}"/>
								</td>
							</tr>

			<%
						}
			%>

						</c:forEach>
					</table>
				</td>

			<%
				if(rowflag)
				{
			%>
					</tr>
			<%
				}
				else
				{
			%>
					<td width="20">
			<%
				}
					rowflag = !rowflag;
			%>

			<%
				}	///////////		ADDING RADIO BUTTONS ENDS HERE		///////////

				if (ObjectType.equals(Constant.CHECKBOX))
				{
					////////////////////////ADDING CHECKBOXES//////////////////////
					if(mstFieldVO.getFldColumnName() != null && mstFieldVO.getFldColumnName().startsWith("*"))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"sel.ischeck"; 
						mandatory = "true"; 
						validationcounter++;
					}
					pageContext.setAttribute("validate",validation);
					pageContext.setAttribute("mandatory",mandatory);

					String  fieldColumnNmae = mstFieldVO.getFldColumnName();
					List optionList = (List)resValue.get(fieldColumnNmae);

					pageContext.setAttribute("optionList",optionList);
			%>

			<%
				if(!rowflag)
				{
			%>
					<tr>
			<%
				}
			%>
				<td>
					<c:out value="${caption}" />
				</td>

				<td>
					<table>
						<c:forEach var="rld" items="${optionList}">
			<%
							Object[] obj = (Object[])pageContext.getAttribute("rld");

							if(obj[2].equals("0") || obj[2].equals(langID))
							{

			%>
							<tr>
								<td>
									<hdiits:checkbox readonly="${disabled}" name="${controlName}"
											errCaption="${controlName}" value="${rld[0]}"
											validation="${validate}" caption="${caption}" mandatory="${madatory}"/>
								</td>
							</tr>

			<%
							}
			%>
						</c:forEach>
					</table>
				</td>

			<%
				if(rowflag)
				{
			%>
					</tr>
			<%
				}
				else
				{
			%>
					<td width="20">
			<%
				}
					rowflag = !rowflag;
			%>

			<%
				}		//////////		ADDING CHECKBOX ENDS HERE		////////////

				if (ObjectType.equals(Constant.SELECT))
				{
					////////////////////////ADDING SELECT(COMBOBOX)//////////////////////
					if(mstFieldVO.getFldColumnName() != null && mstFieldVO.getFldColumnName().startsWith("*"))
					{
						if(validationcounter != 0)
						{
							validation = validation+",";
						}
						validation = validation+"sel.isrequired";
						mandatory = "true";
						validationcounter++;
					}
					pageContext.setAttribute("validate",validation);
					pageContext.setAttribute("mandatory",mandatory);

					String  fieldColumnNmae = mstFieldVO.getFldColumnName();
					List optionList = (List)resValue.get(fieldColumnNmae);

					pageContext.setAttribute("optionList",optionList);
			%>

			<%
				if(!rowflag)
				{
			%>
					<tr>
			<%
				}
			
			%>
				<td>
					<c:out value="${caption}" />
				</td>

				<td>
					<hdiits:select mandatory="${mandatory}" validation="${validate}" readonly="${disabled}"
								caption="${caption}" sort="false"
								 name="${controlName}">
						<hdiits:option value="">Select Item from List</hdiits:option>
							<c:forEach var="rld" items="${optionList}">
								<c:set var="selectStatus" value="false">
								</c:set>
		  	<%
								Object[] obj = (Object[])pageContext.getAttribute("rld");

							  	if(obj[0].toString().equals(defaultValue))
							  	{
			%>
									<c:set var="selectStatus" value="true"></c:set>
			<%
								}
			%>

			<%
								if(obj[2].equals("0") || obj[2].equals(langID))
								{
			%>
									<hdiits:option selected="${selectStatus}" value="${rld[0]}">${rld[1]}</hdiits:option>
			<% 
								}
			%>
							</c:forEach>
						</hdiits:select>
					</td>
			<%
				

			
				if(rowflag)
				{
			%>
					</tr>
			<%
				}
				else
				{
			%>
					<td width="20">
			<%
				}
				rowflag = !rowflag;
			%>

			<%
				}		/////////		ADDING SELECT(COMBOBOX) ENDS HERE		////////
			%>

			<%
				////115//// IF SPECIFIC CONTROL TO BE ADDED IN FORM OR NOT ENDS HERE////////////////////
			%>

			<%
				}
					/////////////////////// LOOP FOR EACH FIELD ENDS HERE//////////////////////////////////////////
			%>
					</table>
				</div>

			<%
				}
				////////////////////////////LOOP FOR EACH LANGUAGE FORM ENDS//////////////////////////////////////
				pageContext.setAttribute("langSNameList",langSNameList);
			%>

			<jsp:include page="../core/tabnavigation.jsp?">
			<jsp:param name="beforeGoToNextTab" value="copyDisabled();"/>
			<jsp:param name="submitText" value="${submitName}"/>
			<jsp:param name="closeURL" value="hdiits.htm?screenId=${screenId}&actionFlag=getAllData"/>
			</jsp:include>
		</div>

		<script	type="text/javascript">
         	initializetabcontent("maintab")
         </script>

		<hdiits:validate locale='<%=(String)session.getAttribute("locale") %>' />
	</hdiits:form>

	<script language="javascript">

		function copyDisabled()
		{
		var dblUnderscore = "__";
		var disablecontrolcount = '${disabledCounter}';
		var dmycntr = "";
		var defaultcontrolname = "";
		var langName  = "";
		var objectType = "";

		<c:forEach var="control" items="${disabledList}">

			defaultcontrolname = "${control[0]}";
			objectType = "${control[1]}";
			dmycntr = defaultcontrolname.concat(dblUnderscore);
			//alert(defaultcontrolname);
			<c:forEach var="langSName" items="${langSNameList}">

				langName = "${langSName}";

				dmycntr = dmycntr.concat(langName);

				//alert(dmycntr);
				//alert(document.getElementsByName(defaultcontrolname).item(0).value);

				if(objectType != "${radio}")
				{
				//alert (objectType);
					document.getElementsByName(dmycntr).item(0).value=document.getElementsByName(defaultcontrolname).item(0).value;
				}
				else
				{
					var defaultRadio = document.getElementsByName(defaultcontrolname);
					var disabledRadio = document.getElementsByName(dmycntr);

					for(i=0;i<defaultRadio.length;i++)
					{
						if(defaultRadio[i].checked)
							{
								disabledRadio[i].checked=true;
							}
					}
				}
			</c:forEach>
		</c:forEach>
		
		
		//START///ADDED BY PRK FOR COPY ALL ON 4th Feb 2008////////////////////////
		
		<c:forEach var="control" items="${notDisabledList}">
			//alert('in not disable');
			defaultcontrolname = "${control[0]}";
			
			
			objectType = "${control[1]}";
			dmycntr = defaultcontrolname.concat(dblUnderscore);
			//alert(defaultcontrolname);
			
			defaultcontrolname = defaultcontrolname + dblUnderscore + "${defaultLangName}";
			
			
			if(objectType != "hidden" && "Y" == "${copyAll}")
			{
				<c:forEach var="langSName" items="${langSNameList}">
	
					langName = "${langSName}";
					
					dmycntr = dmycntr.concat(langName);
	
					//alert(dmycntr);
					//alert(document.getElementsByName(defaultcontrolname).item(0).value);
	
					if(objectType != "${radio}")
					{
					//alert (objectType);
					//alert(dmycntr);
					//alert(defaultcontrolname);
					//alert(document.getElementsByName(dmycntr).item(0).value);
					
					
					
					
					if(document.getElementsByName(dmycntr).item(0).value == "" )
						{				
						document.getElementsByName(dmycntr).item(0).value=document.getElementsByName(defaultcontrolname).item(0).value;
						}
					
					}
					else
					{
						var defaultRadio = document.getElementsByName(defaultcontrolname);
						var disabledRadio = document.getElementsByName(dmycntr);
	
						for(i=0;i<defaultRadio.length;i++)
						{
							if(defaultRadio[i].checked)
								{
									disabledRadio[i].checked=true;
								}
						}
					}
				</c:forEach>
				}
			</c:forEach>
			
			
			
			
			//END///ADDED BY PRK FOR COPY ALL ON 4th Feb 2008////////////////////////
			
			
			
			
			//tempcontrols = '${disabledControlArray}';
			//return true;
		}
	</script>

</html>
<%

	}catch(Exception e)
	{
		e.printStackTrace();
	}
%>
