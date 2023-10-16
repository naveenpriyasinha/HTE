<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.acl.acl" var="aclLabels" scope="request" />
<fmt:setBundle basename="resources.Constants" var="Constants" scope="request" />
<%@page language="java" import="com.tcs.sgv.core.valueobject.ResultObject, java.util.Map" %>
<html>
<script type="text/javascript" src='<c:url value="/script/common/tagLibValidation.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/commonfunctions.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/tabcontent.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/validation.js"/>'></script>
<link rel="stylesheet" href="<c:url value="/themes/${themename}/exprcpt.css"/>" type="text/css" />
<link rel="stylesheet" href='<c:url value="/themes/ifmsblue/login.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/themes/ifmsblue/taglib.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/themes/ifmsblue/keyboard.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/themes/ifmsblue/statusbar.css"/>' type="text/css">
<link rel="stylesheet" href='<c:url value="/themes/ifmsblue/exprcpt.css"/>' type="text/css" />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<script>
function goToLoginPage()
{
	//alert('back');
	document.forms[0].action = "./login.jsp";
	document.forms[0].submit();
}



</script>

<%--START Script for logedin users bar chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["table"]});
    //google.setOnLoadCallback(drawChart);

    function loadChart0(){
    	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Month');
        data.addColumn('number', 'Number of DDOs generating the paybills(month wise) Since 01/01/2017');
        data.addRows([
          <%=(String)request.getAttribute("logedInUsers") %>
          ]);

      var options = {
        //title: 'Number of Successful Logged in users since 01/01/2014',
        //vAxis: {title: 'Number of User',  titleTextStyle: {color: 'red'} , logScale:true},
        //hAxis: {title: 'Month',  titleTextStyle: {color: 'red'}}  
        sort:'disable'
            //,showRowNumber: true
      };

      var chart = new google.visualization.Table(document.getElementById('logedinUsers_div'));
      chart.draw(data,options);
    }
</script>
<%--END Script for logedin users bar chart--%>

<%--START Script for bar chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    //google.setOnLoadCallback(drawChart);
    
    function loadChart1(){
    	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Type Of Schools/Colleges');
        data.addColumn('number', 'Number of Schools/Colleges');
        data.addColumn({type:'string', role:'annotation'});
        data.addRows([
          <%=(String)request.getAttribute("strNoOfOfficesCoveredAdminWise") %>
          ]);

      var options = {
        title: 'Number of Schools/Colleges Covered: '+<%=(String)request.getAttribute("strTotalNoOfOfficesCovered") %>,
        vAxis: {title: 'Number of Schools/Colleges',  titleTextStyle: {color: 'red'} , viewWindowMode: 'explicit',
            viewWindow: {
            //max: 180,
            min: 500
          },
          gridlines: {
            count: 4}
            },
        hAxis: {title: 'Type of Schools/Colleges',  titleTextStyle: {color: 'red'}},
        colors: ['#c06f20', 'blue'],
        fontSize: 10
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('noOfOfficesBarChart_div'));

      function selectHandler() {
          var selectedItem = chart.getSelection()[0];
          if (selectedItem) {
            var typeOfSchool = data.getValue(selectedItem.row, 0);
           var noOfSchools = data.getValue(selectedItem.row, 1);
            alert('The user selected ' + typeOfSchool + ' with number of Schools: '+ noOfSchools );
            document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1&chartType=1&displaySchoolCountAdminWise="+typeOfSchool;
        	document.forms[0].submit();
          }
        }

      google.visualization.events.addListener(chart, 'select', selectHandler);
      
      chart.draw(data, options);
    }
</script>
<%--END Script for bar chart--%>

<%--START Script for division wise no of schools bar chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    //google.setOnLoadCallback(drawChart);
    
    function loadChart2(){
    	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Division');
        data.addColumn('number', 'Number of Schools');
        data.addColumn({type:'string', role:'annotation'});
        data.addRows([
          <%=(String)request.getAttribute("strNoOfDivisionWiseSchoolsTodisplay") %>
          ]);

      var options = {
        title: 'Division Wise Number of Schools Covered: '+<%=(String)request.getAttribute("strTotalNoOfDivisionWiseSchools") %>,
        vAxis: {title: 'Number of Schools',  titleTextStyle: {color: 'red'} , viewWindowMode: 'explicit',
            viewWindow: {
            //max: 180,
            min: 100
          },
          gridlines: {
            count: 4
          }},
        hAxis: {title: 'Division',  titleTextStyle: {color: 'red'}},
        colors: ['#c06f20', 'blue'],
        fontSize: 10
        
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('divisionWiseSchoolsBarChart_div'));
      function selectHandler() {
          var selectedItem = chart.getSelection()[0];
          if (selectedItem) {
            var region = data.getValue(selectedItem.row, 0);
           var noOfSchools = data.getValue(selectedItem.row, 1);
            alert('The user selected ' + region + ' with number of Schools: '+ noOfSchools );
            
            document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1&executeFlag=1&chartType=2&displaySchoolEmpCountDivisionWise="+region;
        	document.forms[0].submit();
          }
        }
      google.visualization.events.addListener(chart, 'select', selectHandler);
      chart.draw(data, options);
    }
    </script>
<%--END SScript for division wise no of schools bar chart--%>

<%--START Script for division wise no of emp bar chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    //google.setOnLoadCallback(drawChart);

    function loadChart3(){
    	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Division');
        data.addColumn('number', 'Number of Employees');
        data.addColumn({type:'string', role:'annotation'});
        data.addRows([
          <%=(String)request.getAttribute("strNoOfDivisionWiseEmpToDisplay") %>
          ]);

      var options = {
        title: 'Division Wise Number of Employees Covered '+<%=(String)request.getAttribute("strTotalNoOfDivisionWiseEmp") %>,
        vAxis: {title: 'Number of Employees',  titleTextStyle: {color: 'red'} , viewWindowMode: 'explicit',
            viewWindow: {
            //max: 180,
            min: 500
          },
          gridlines: {
            count: 4
          }},
        hAxis: {title: 'Division',  titleTextStyle: {color: 'red'},logScale:true},
        colors: ['#c06f20', 'blue'],
        fontSize: 10
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('divisionWiseEmpBarChart_div'));
      function selectHandler() {
          var selectedItem = chart.getSelection()[0];
          if (selectedItem) {
            var region = data.getValue(selectedItem.row, 0);
           var noOfSchools = data.getValue(selectedItem.row, 1);
            alert('The user selected ' + region + ' with number of Schools: '+ noOfSchools );
            
            document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1&executeFlag=1&chartType=3&displaySchoolEmpCountDivisionWise="+region;
        	document.forms[0].submit();
          }
        }
      google.visualization.events.addListener(chart, 'select', selectHandler);
      chart.draw(data, options);
    }
 </script>
<%--END Script for division wise no of emp bar chart--%>

<%--START Script for division wise no of paybill generated bar chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    //google.setOnLoadCallback(drawChart);
    
    function loadChart4(){
    	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Division');
        data.addColumn('number', 'Number of PayBillsGenerated');
        data.addColumn({type:'string', role:'annotation'});
        data.addRows([
          <%=(String)request.getAttribute("strNoOfDivisionWisePaybillGeneratedToDisplay") %>
          ]);

      var options = {
        title: 'Division Wise Number of Employees covered in paybills Generated For FY 2016-17: '+<%=(String)request.getAttribute("strTotalNoOfDivisionWisePaybillGenerated") %>,
        vAxis: {title: 'Number of PayBills',  titleTextStyle: {color: 'red'} , viewWindowMode: 'explicit',
            viewWindow: {
            //max: 180,
            min: 0
          },
          gridlines: {
            count: 4
          }},
        hAxis: {title: 'Division',  titleTextStyle: {color: 'red'}},
        colors: ['#c06f20', 'blue'],
        fontSize: 10
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('divisionWisePayBillGeneratedBarChart_div'));
      function selectHandler() {
          var selectedItem = chart.getSelection()[0];
          if (selectedItem) {
            var region = data.getValue(selectedItem.row, 0);
           var noOfPayBills = data.getValue(selectedItem.row, 1);
            alert('The user selected ' + region + ' with number of payBills: '+ noOfPayBills );
            document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1&executeFlag=1&chartType=4&displayPayBillCountForFY1415DivisionWise="+region;
        	document.forms[0].submit();
          }
        }
      google.visualization.events.addListener(chart, 'select', selectHandler);
      chart.draw(data, options);
    }

</script>
<%--END Script for division wise no of paybill generated bar chart--%>

<%--START Script for division wise amount disbursed pie chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    //google.setOnLoadCallback(drawChart);
    
    function loadChart5(){
    	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Division');
        data.addColumn('number', 'Amount disbursed');
       // data.addColumn({type:'string', role:'annotation'});
        data.addRows([
          <%=(String)request.getAttribute("strDivisionWiseAmountDisbursedToDisplay") %>
          ]);

      var options = {
    	title: 'Division Wise Amount disbursed till date in FY 2016-17 in Rs. '+<%=(String)request.getAttribute("strTotalDivisionWiseAmountDisbursed") %>+' Crore. (All values in graph are in Crore.)',
        is3D: true,
        pieSliceText: 'value',
        legend: {position: 'labeled'},
        fontSize: 10
        
      };

      var chart = new google.visualization.PieChart(document.getElementById('divisionWiseAmountDisbursedPieChart_div'));
      function selectHandler() {
          var selectedItem = chart.getSelection()[0];
          if (selectedItem) {
            var region = data.getValue(selectedItem.row, 0);
           var amountDisbursed = data.getValue(selectedItem.row, 1);
            alert('The user selected ' + region + ' with amount: '+ amountDisbursed +' Crore.');
            document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1&executeFlag=1&chartType=5&displayAmountDisbursedForFY1415DivisionWise="+region;
        	document.forms[0].submit();
          }
        }
      google.visualization.events.addListener(chart, 'select', selectHandler);
      chart.draw(data, options);
    }
</script>
<%--END Script for division wise amount disbursed pie chart--%>

<%--START Script for division wise amount disbursed for current month pie chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    //google.setOnLoadCallback(drawChart);
    
    function loadChart6(){
    	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Division');
        data.addColumn('number', 'Amount disbursed');
        data.addRows([
          <%=(String)request.getAttribute("strDivisionWiseAmountDisbursedCurrentMonthToDisplay") %>
          ]);

    var dt = new Date();
	var currentMonth=dt.getMonth() + 1 + "/" +dt.getFullYear();;
    var options = {
        title: 'Division wise amount disbursed till date in current month: '+currentMonth+' in Rs. '+<%=(String)request.getAttribute("strTotalDivisionWiseAmountDisbursedCurrentMonth") %>,
        is3D: true,
        pieSliceText: 'value',
        legend: {position: 'labeled'},
        fontSize: 10
      };

      var chart = new google.visualization.PieChart(document.getElementById('divisionWiseAmountDisbursedCurrentMonthPieChart_div'));
      function selectHandler() {
          var selectedItem = chart.getSelection()[0];
          if (selectedItem) {
            var region = data.getValue(selectedItem.row, 0);
           var amount = data.getValue(selectedItem.row, 1);
            //alert('The user selected ' + region + ' with amount: '+ amount );
           
          }
        }
      google.visualization.events.addListener(chart, 'select', selectHandler);
      chart.draw(data, options);
    }
    </script>
<%--END Script for division wise amount disbursed for current month pie chart--%>




<%--START Script for Amounts disbursed Trend chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    //google.setOnLoadCallback(drawChart);
    
    function loadChart7(){

        var data = google.visualization.arrayToDataTable([
			<%=(String)request.getAttribute("divisionWiseAmountDisbursedForLast13MonthsLineChart") %>
        ]);
        
      var options = {
        title: 'Division Wise Amounts(in Millions(10 Lakhs)) disbursed Trend',
        vAxis: {title: 'Amount in Rs. Crore',  titleTextStyle: {color: 'red'}},
        hAxis: {title: 'Amount Disbursed during given period',  titleTextStyle: {color: 'red'}},
        lineWidth: 2,
        fontSize: 10
        //curveType: 'function'
        //legend: { position: 'bottom' } //position of region names
      };

      var chart = new google.visualization.LineChart(document.getElementById('divisionWiseAmountDisbursedLineChart_div'));
      chart.draw(data, options);
    }
 </script>
<%--END Script for Amounts disbursed Trend chart--%>



<%--START Script for division wise no of paybill generated bar chart--%>
<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    //google.setOnLoadCallback(drawChart);
    
    function loadChart8(){
    	var data = new google.visualization.DataTable();
        data.addColumn('string', 'Division');
        data.addColumn('number', 'Number of PayBillsGenerated');
        data.addColumn({type:'string', role:'annotation'});
        data.addRows([
          <%=(String)request.getAttribute("strNoOfDivisionWisePaybillGeneratedCurrentMonthToDisplay") %>
          ]);
        var dt = new Date();
    	var currentMonth=dt.getMonth() + 1 + "/" +dt.getFullYear();;
      var options = {
        title: 'Division Wise Number of Employees covered in paybills Generated For current month '+currentMonth+': '+<%=(String)request.getAttribute("strTotalNoOfDivisionWisePaybillGeneratedCurrentMonth") %>,
        vAxis: {title: 'Number of PayBills',  titleTextStyle: {color: 'red'} , viewWindowMode: 'explicit',
            viewWindow: {
            //max: 180,
            min: 0
          },
          gridlines: {
            count: 4
          }},
        hAxis: {title: 'Division',  titleTextStyle: {color: 'red'}},
        colors: ['#c06f20', 'blue'],
        fontSize: 10
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('divisionWisePayBillGeneratedBarChartCurrentMonth_div'));
      function selectHandler() {
          var selectedItem = chart.getSelection()[0];
          if (selectedItem) {
            var region = data.getValue(selectedItem.row, 0);
           var noOfPayBills = data.getValue(selectedItem.row, 1);
            alert('The user selected ' + region + ' with number of payBills: '+ noOfPayBills );
            document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1&executeFlag=1&chartType=4&displayPayBillCountForFY1415DivisionWise="+region;
        	document.forms[0].submit();
          }
        }
      google.visualization.events.addListener(chart, 'select', selectHandler);
      chart.draw(data, options);
    }

</script>
<%--END Script for division wise no of paybill generated bar chart--%>



<%--START Script for pie chart
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() 
      {
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Division');
          data.addColumn('number', 'Number of employees');
          data.addRows([
            ['Konkan',     11],
            ['Nashik',      2],
            ['Pune',  5],
            ['Kolhapur', 4],
            ['Aurangabad',    7],
            ['Latur',    8],
            ['Nagpur',    10],
            ['Amravati',    12]
          ]);
          

          
          var options = {
            title: 'Division wise number of employees',
            is3D: true
          };
          

          var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
          function selectHandler() {
              var selectedItem = chart.getSelection()[0];
              if (selectedItem) {
                var region = data.getValue(selectedItem.row, 0);
               var noOfEmp = data.getValue(selectedItem.row, 1);
                alert('The user selected ' + region + ' with Employees: '+ noOfEmp );
               
              }
            }
          google.visualization.events.addListener(chart, 'select', selectHandler);
          chart.draw(data, options);
        }
</script>
END Script for pie chart--%>

<script>
//code to print indicidual graph
function prindDoc(divName)
{
	//PrintPage
	var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;
	document.body.innerHTML = printContents;
	window.print();
	document.body.innerHTML = originalContents;

	document.forms[0].action = "hdiits.htm?viewName=dashboardRedirect&executeFlag=1";
	document.forms[0].submit();
}
function loadChart()
{		
		//drawChart0();
		loadChart0();

		//drawChart1();
		//loadChart1();
		
		//drawChart2();
		loadChart2();
		
		//drawChart3();
		loadChart3();

		//drawChart4();
		//loadChart4();

		//drawChart5();
		loadChart5();

		//drawChart6();
		//loadChart6();		//commented to not load chart 6

		//drawChart7();
		loadChart7();

		//loadChart8();

}
</script>
<body onload="loadChart();">
<img width="100%" height="82" alt="" src="images/HomePageImages/FianlHomePG_1_11.jpg">


<hdiits:form name="DashBoard" method="POST" validate="true">

	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent">


	<fieldset class="tabstyle" style="border-color: #BD6C03"><legend><h3>DashBoard</h3></legend> 
	
	

	
	<%-- links for reports --%>
	<table width="100%" align="center" style="display: none;">
	<tr><td width="25%"></td><td width="50%"><a href="#" onclick="drawChart0();">Successful Logged in users since 01/01/2017</a></td><td width="25%"></td></tr>
	<tr><td width="25%"></td><td width="50%"><a href="#" onclick="drawChart1();">Number of Schools/Colleges Covered</a></td><td width="25%"></td></tr>
	<tr><td width="25%"></td><td width="50%"><a href="#" onclick="drawChart2();">Division Wise Number of Schools Covered</a></td><td width="25%"></td></tr>
	<tr><td width="25%"></td><td width="50%"><a href="#" onclick="drawChart3();">Division Wise Number of Employees Covered</a></td><td width="25%"></td></tr>
	<tr><td width="25%"></td><td width="50%"><a href="#" onclick="drawChart4();">Division Wise Number of PayBills Generated For FY 2016-17</a></td><td width="25%"></td></tr>
	<tr><td width="25%"></td><td width="50%"><a href="#" onclick="drawChart5();">Division Wise Amount disbursed till date in FY 2016-17</a></td><td width="25%"></td></tr>
	<tr><td width="25%"></td><td width="50%"><a href="#" onclick="drawChart6();">Division wise amount disbursed till date in current month</a></td><td width="25%"></td></tr>
	<tr><td width="25%"></td><td width="50%"><a href="#" onclick="drawChart7();">Division wise amount disbursed for last 13 months</a></td><td width="25%"></td></tr>
	</table>
	
	<%--<%=(String)request.getAttribute("msg") %><br>--%> 
	<%--<p id="logedinUsers_div" style="color: red ; display: none;">Successful Logged in users since 01/01/2014: <b> <%=(String)request.getAttribute("logedinUsers_div") %></b> </p>--%>
	<%--  Total No. of employyes <%=(String)request.getAttribute("noOfEmployees")%> --%> 
	
	
	<table width="100%">
	<tr>
	<td width="10%">&nbsp;</td>
	<td width="40%">
	<%--START no of logedin users Bar chart--%>
	<center>
		<div id="logedinUsers_div" style="width: 100%; height: 300px; border-style: solid; border-color: #000000; "></div>
		
		<input id="logedinUsers_btn" type="button" onclick="prindDoc('logedinUsers_div')" value="Print Above Chart" width="100%" style="width: 35% ; " class="buttontag">
	</center>
	<%--END no of logedin users Bar chart--%>
	</td>
	
	<td width="40%">
	<%--START division wise no of schools Bar chart--%>
	<center>
		<div id="divisionWiseSchoolsBarChart_div" style="width: 100%; height: 300px; border-style: solid; border-color: #000000; "></div>
		
		<input id="divisionWiseSchoolsBarChart_btn" type="button" onclick="prindDoc('divisionWiseSchoolsBarChart_div')" value="Print Above Chart" width="100%" style="width: 35% ; " class="buttontag">
	</center>
	<%--END division wise no of schools Bar chart--%> 
	</td>
		<td width="10%">&nbsp;</td>
	</tr>
	
	<tr>
		<td width="10%">&nbsp;</td>
	<td width="40%">
		<%--START division wise no of emp Bar chart--%>
	<center>
		<div id="divisionWiseEmpBarChart_div" style="width: 100%; height: 300px; border-style: solid; border-color: #000000; "></div>
		
		<input id="divisionWiseEmpBarChart_btn" type="button" onclick="prindDoc('divisionWiseEmpBarChart_div')" value="Print Above Chart" width="100%" style="width: 35% ; " class="buttontag"></center>
	<%--END division wise no of emp Bar chart--%> 
	</td>
	
	<td width="40%">
		<%--START division wise Amount disbursed pie chart--%>
	<center>
		<div id="divisionWiseAmountDisbursedPieChart_div" style="width: 100%; height: 300px; border-style: solid; border-color: #000000; "></div>
		
		<input id="divisionWiseAmountDisbursedPieChart_btn" type="button" onclick="prindDoc('divisionWiseAmountDisbursedPieChart_div')" value="Print Above Chart" width="100%" style="width: 35% ; " class="buttontag"></center>
	<%--END division wise Amount disbursed pie chart--%>
	</td>
		<td width="10%">&nbsp;</td>
	</tr>
	
	
	<tr>
	<td width="10%">&nbsp;</td>
	<td width="80%" colspan="2" id="lastReport">
		<%--START Script for Amounts disbursed Trend chart--%>
	<center>
		<div id="divisionWiseAmountDisbursedLineChart_div" style="width: 100%; height: 300px; border-style: solid; border-color: #000000; "></div>
		
		<input id="divisionWiseAmountDisbursedLineChart_btn" type="button" onclick="prindDoc('divisionWiseAmountDisbursedLineChart_div')" value="Print Above Chart" width="100%" style="width: 35%; "  class="buttontag"></center>
		
	<%--END Script for Amounts disbursed Trend chart--%>
	</td>
	
	
	<td width="10%">&nbsp;</td>
	</tr>
	</table>

	<%-- START Pie chart not displaying! 
	<center>
		<div id="piechart_3d" style="width: 900px; height: 500px; border-style: solid; border-color: #000000;"></div>
		<br>
		<input type="button" onclick="prindDoc('piechart_3d')" value="Print Above Chart" width="100%" style="width: 15%" class="buttontag">
	</center>
	
	
	END Pie chart--%>
	
	
	</fieldset>
	<br>
	<br>
	
	
	<input type="button" value="Back" class="buttontag" onclick="goToLoginPage();" width="50%">

	<%--<input type="button" value="print" class="buttontag" onclick="javascript:window.print();" width="50%">--%>
	

	<br/><br/>

	<table width="100%"  cellspacing=0 cellpadding=0>
		<tr height="47" style="background: #2f679c url('images/HomePageImages/FianlHomePG_1_13.jpg') top repeat-x; font-size: 10pt; color: black;">
			<td valign="middle" align="left" style="font-size: 8pt;">
				<li>Fields marked with <font color="red">'*'</font> are mandatory.</li>
				<li>All amounts are in INR.</li>
				<li>All the dates are in DD/MM/YYYY format.<sub>1</sub></li>
			</td>
			
			<td valign="center" align="left">
			</td>

		</tr>
	</table>
	</div>
	</div>
</hdiits:form>


	<script>
	//alert('hello');
	var flag='<%=request.getAttribute("displayPopupFlag") %>';
	var x='<%=request.getAttribute("displayPopupString") %>';
	
	if(flag!='' && flag!=null && flag == 'Y' && x!='' && x!=null)
	{
		//alert('hello');
		
		var newWindow = window.open("", null, "height=500,width=1200,status=yes,toolbar=no,menubar=no,location=no,scrollbars=1,resizable=1");
		//newWindow.document.write('');
		newWindow.document.write(x);
		newWindow.document.close();
		newWindow.focus();
		//newWindow.print();
		//newWindow.close();
	}
	</script>

</body>
</html>