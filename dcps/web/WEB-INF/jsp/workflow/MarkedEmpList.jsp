<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<script language="javascript">
if(window.dialogArguments) 
{
  window.parent.opener = window.dialogArguments;
} 
function changeLevel()
{	
	
			try
			{
				var newob1 = window.parent.opener.document.getElementById("markedList");
				window.parent.opener.document.getElementById("markedList").value="";
				window.parent.opener.document.getElementById("isMark").value="yes";
			}
			catch(e)
			{
				alert(" in inner cat " + e.message);
				
			}	
			
			
			window.parent.opener.document.getElementById("markedList").value='';
			var choice = document.frm.choice2;
			for(var i = 0; i < choice.length; i++)
			{
				var tempArr = new Array();
				tempArr = choice.options[i].value.split(',');
				
				if(i!=choice.length-1)
				{
					
					window.parent.opener.document.getElementById("markedList").value+=tempArr[0]+',';
				}
				else
				{
					
					window.parent.opener.document.getElementById("markedList").value+=tempArr[0]+',';
				}
				choice.options[i].value = tempArr[0]+','+(i+1);
				
			}			
}

function myFunc(flag, choice1, choice2)
{
	var just = 1;
	if(choice1.selectedIndex == -1)
	{
		if(flag == 1)
		{
			for(var i = 0; i<choice1.length; i++)
			{
				choice1.options[i].selected = true;
			}	
		}
		else
		{
			alert('<fmt:message key="WF.SELOPMSG"  bundle="${fmsLables}"/>');
		}
	}
	var j = choice2.length;
	for(var i = 0; (i<choice1.length)&&(just!=0); i++)
	{
		if(choice1.selectedIndex == i)
		{
			if(choice1.options[i].value.indexOf(",") != -1)
			{
				choice1.options[i].value = choice1.options[i].value.substring(0,choice1.options[i].value.indexOf(","));
			}

			var op = new Option(choice1.options[i].text, choice1.options[i].value, false, false);
			choice2.options[j++] = op;
			if(parseInt(choice2.size) > 3)
				choice2.size=parseInt(choice2.size)+1;  
				choice2.removeAttribute("style");
			choice1.options[i] = null;
			if(parseInt(choice1.size) > 3)
				choice1.size=parseInt(choice1.size)-1;
			if(flag == 0)
				just=0;
			else
				i--;
		}
	}
	changeLevel();
}

function up_mult(choice)
{
	if(choice.selectedIndex == -1)
	{
		alert('<fmt:message key="WF.SELOPMSG"  bundle="${fmsLables}"/>');
		return;
	}
	var arr = new Array(choice.length);
	for(var i = 0; i < choice.length; i++)
	{
		if(choice.selectedIndex == i)
		{
			if(i != 0)
			{
				var temp1 = new Option(choice.options[i-1].text, choice.options[i-1].value, false, false);
				var temp2 = new Option(choice.options[i].text, choice.options[i].value, false, false);
				choice.options[i-1] = temp2;      
				choice.options[i] = temp1;
				arr[i-1] = 1 ;
				arr[i] = 0;
			}
			else
			{
				alert('<fmt:message key="WF.MOVEUPMSG"  bundle="${fmsLables}"/>');
				return;
			}
		}
	}
	for(i = 0; i < choice.length; i++)
	{
		if(arr[i] == 1)
			choice.options[i].selected = true;
	}
	changeLevel();
}

function down_mult(choice)
{
	if(choice.selectedIndex == -1)
	{
		alert('<fmt:message key="WF.SELOPMSG"  bundle="${fmsLables}"/>');
		return;
	}
	var total = choice.length;
	var temp1, temp2;
	var flag = 0;
	var arr = new Array(choice.length);
	for(var j = 0; j < total / 2; j++)
	{
		if(choice.options[j].selected)
			arr[total-j-1] = 1;
		if(choice.options[total-j-1].selected)
			arr[j] = 1;
		temp1 = new Option(choice.options[j].text, choice.options[j].value, false, false);
		temp2 = new Option(choice.options[total-j-1].text, choice.options[total-j-1].value, false, false);
		choice.options[j] = temp2;
		choice.options[total-j-1] = temp1;      
	}
	for(var i = 0; i < total; i++)
	{
		if(arr[i] == 1)
			choice.options[i].selected = true;
	}
	for(var i = 0; i < total; i++)
	{
		if(choice.selectedIndex == i)
		{
			if(i != 0)
			{
				var temp1 = new Option(choice.options[i-1].text, choice.options[i-1].value, false, false);
				var temp2 = new Option(choice.options[i].text, choice.options[i].value, false, false);
				choice.options[i-1] = temp2;      
				choice.options[i] = temp1;
				arr[i-1] = 1 ;
				arr[i] = 0;
			}
			else
				flag = 1;
		}
	}
	for(var j = 0; j < total / 2; j++)
	{
		temp1 = new Option(choice.options[j].text, choice.options[j].value, false, false);
		temp2 = new Option(choice.options[total-j-1].text, choice.options[total-j-1].value, false, false);
		choice.options[j] = temp2;      
		choice.options[total-j-1] = temp1;      
	}
	for(i = 0; i < total; i++)
	{
		if(arr[i] == 1)
			choice.options[total-i-1].selected = true;
	}
	if(flag == 1)
	{
		alert('<fmt:message key="WF.MOVEDOWNMSG"  bundle="${fmsLables}"/>');
		return;
	}
	changeLevel();
}

function forwardDoc()
{
	window.parent.forward();
	window.close();	
}

function close1()
{
	if(confirm('<fmt:message key="WF.EXMSG"  bundle="${fmsLables}"/>'))
	{
			window.close();			
	}
}
</script>
<% 
try
{
%>


<hdiits:form name="frm" id="f2"  validate="false" >
<hdiits:hidden name="Employees" id="hid_employees" default="${param.empArray}"/>
<hdiits:hidden name="Post" id="hid_post" default="${param.postIdArray}"/>
<hdiits:hidden name="Designation" id="hid_desig" default="${param.desgnArray}"/>
  <hdiits:table align="center" height="10%">
    <hdiits:tr>
      <hdiits:td>
        <hdiits:table>
          <hdiits:tr>
            <hdiits:td>
              <select id="dd_choice1_id" multiple="multiple" name="choice1" size="3" style="width:200px">
              </select>
            </hdiits:td>
            <hdiits:td>
              <hdiits:table>
                <hdiits:tr align="center">
                  <hdiits:td>
                    <hdiits:button type="button" name="btnOne" id="btn1" onclick="myFunc(0,document.frm.choice1,document.frm.choice2)" value=">" style="width: 75px;height:15%"/>
                  </hdiits:td>
                  <hdiits:td>
                    <hdiits:button type="button" name="btnTwo" id="btn2" onclick="myFunc(0,document.frm.choice2,document.frm.choice1)" value="<" style="width: 75px;height:15%" />
                  </hdiits:td>
                </hdiits:tr>
                <hdiits:tr align="center">
                  <hdiits:td>
                    <hdiits:button type="button" name="btnThree" id="btn3" onclick="myFunc(1,document.frm.choice1,document.frm.choice2)" value=">>" style="width: 75px;height:15%"/>
                  </hdiits:td>
                  <hdiits:td>
                    <hdiits:button type="button" name="btnFour" id="btn4" onclick="myFunc(1,document.frm.choice2,document.frm.choice1)" value="<<" style="width: 75px;height:15%"/>
                  </hdiits:td>
                </hdiits:tr>
              </hdiits:table>
            </hdiits:td>
          </hdiits:tr>
        </hdiits:table>
      </hdiits:td>
      <hdiits:td>
      </hdiits:td>
      <hdiits:td>
        <hdiits:table>
          <hdiits:tr>
            <hdiits:td>
              <select id="dd_choice2_id" multiple="multiple" name="choice2" size="3" style="width:200px">
              </select>
            </hdiits:td>
            <hdiits:td>
              <hdiits:table>
                <hdiits:tr align="center">
                  <hdiits:td>
                    <hdiits:button type="button" name="btnFive" id="btn5" onclick="up_mult(document.frm.choice2)" captionid="WF.UP" bundle="${fmsLables}" style="width: 75px"/>
                  </hdiits:td>
                </hdiits:tr>
                <hdiits:tr align="center">
                  <hdiits:td>
                    <hdiits:button type="button" name="btnSix" id="btn6" onclick="down_mult(document.frm.choice2)" captionid="WF.DOWN" bundle="${fmsLables}" style="width: 75px"/>
                  </hdiits:td>
                </hdiits:tr>
              </hdiits:table>
            </hdiits:td>
          </hdiits:tr>
        </hdiits:table>
      </hdiits:td>
    </hdiits:tr>
    <hdiits:tr align="right">
     <hdiits:td>
    	<hdiits:button type="button" name="btnSeven" id="btn7" onclick="parent.forwardDoc()" captionid="WF.Send" bundle="${fmsLables}"  style="width: 75px"/>
     </hdiits:td>
    </hdiits:tr>
  </hdiits:table> 
  
</hdiits:form>
<script type="text/javascript">
	/*var comboid = document.getElementById('dd_choice1_id');
	
	var splitEmpArray = new Array();
	var splitDesignationArray = new Array();
	var splitPostArray = new Array();
	
	var empNamesList = document.getElementById('hid_employees').value;	   
	if(empNamesList!='' && empNamesList!=null)
	{
		var desgList = document.getElementById('hid_desig').value;	   
		var postList = document.getElementById('hid_post').value;	     		
		splitEmpArray = empNamesList.split(",");
		splitDesignationArray = desgList.split(",");
		splitPostArray = postList.split(",");
		if(splitEmpArray.length>0)
		{
			for(var cnt=0;cnt<splitEmpArray.length;cnt++)
			{
				var element=document.createElement('option');
				element.text=splitEmpArray[cnt]+", "+splitDesignationArray[cnt];
				element.value=splitPostArray[cnt];
				comboid.add(element);
			 }
		}
	}*/
</script>
<script type="text/javascript">
var addedEmp = document.getElementById('dd_choice2_id');
for(var i=0;i<addedEmp.length;i++)
{
	parent.addedEmpArray_text.push();
}
</script>
<% 
}catch(Exception e)
{
	 e.printStackTrace();
}
%>