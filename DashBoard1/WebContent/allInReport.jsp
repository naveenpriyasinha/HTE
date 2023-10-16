<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<head>
<title>MahaSevaarth Paybill Status</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script type="text/javascript" src="js/tableHTMLExport.js" /></script>
<script type="text/javascript" src="js/findbrowser.js" /></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<style>
.text-center {
	text-align: center;
}
</style>

<link
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">


<link
	href="https://cdn.datatables.net/buttons/1.6.1/css/buttons.dataTables.min.css">





<!--   <script src="https://code.jquery.com/jquery-3.3.1.js"></script> -->
<script
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.6.1/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.6.1/js/buttons.html5.min.js"></script>
<script
	src="https://cdn.rawgit.com/rainabba/jquery-table2excel/1.1.0/dist/jquery.table2excel.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>

<script>
	/* $(document).ready(function() {
	 $("#excelbtn").click(function(){
	 $("#example").table2excel({
	        filename: "Employees.xls"
	    });
	 });
	} ); */
</script>


<!--  <script>
     $('#excelbtn').click(function () {
    	    alert("hello");
    	   
    	});
     </script>
      -->

<script>
	$(document)
			.ready(
					function() {
						var MyDate = new Date();
						var MyDateString;

						var firstDay = new Date(MyDate.getFullYear(), MyDate
								.getMonth(), 1);

						//console.log(firstDay);

						fromDate = MyDate.getFullYear() + '-'
								+ ('0' + (MyDate.getMonth() + 1)).slice(-2)
								+ '-' + ('0' + firstDay.getDate()).slice(-2);

						MyDateString = MyDate.getFullYear() + '-'
								+ ('0' + (MyDate.getMonth() + 1)).slice(-2)
								+ '-' + ('0' + MyDate.getDate()).slice(-2);

						var curData = ('0' + MyDate.getDate()).slice(-2) + '-'
								+ ('0' + (MyDate.getMonth() + 1)).slice(-2)
								+ '-' + MyDate.getFullYear();

						var c = document.createElement('canvas');
						var img = document.getElementById('image');
						c.height = img.naturalHeight;
						c.width = img.naturalWidth;
						var ctx = c.getContext('2d');

						ctx.drawImage(img, 0, 0, c.width, c.height);
						var logo = c.toDataURL();

						$('#example')
								.append(
										'<caption style="caption-side: top">Customised Sevaarth Paybill Status Report</caption>');

						$('#example').DataTable({
							dom : 'Bfrtip',
							buttons : [ 'copy', 'csv', 'excel', 'pdf' ],
							columnDefs : [ {
								className : 'text-right',
								targets : [ 0, 1, 2 ]
							}, {
								className : 'text-center',
								targets : [ 3, 4, 5, 6 ]
							}, ]
						});

						/* $("#excelbtn").click(function(){
						 $("#example").table2excel({
						        filename: "Paybill_Status_Report-"+curData+".xls"
						    });
						 });
						
							// DataTable initialisation
							$('#example').DataTable(
								{
									stripeClasses: [],
									"dom": '<"dt-buttons"Bf><"clear">lirtp',
									"paging": true,
								//	"autoWidth": true,
								columnDefs: [
										             { className: 'text-right', targets: [0,1,2] },
										             { className: 'text-center', targets: [3,4,5,6] },
										           ],
									"buttons": [
										{
											text: 'PDF',
											extend: 'pdfHtml5',
											filename: 'Paybill_Status_Report-'+curData,
											orientation: 'landscape', //portrait
											pageSize: 'A4', //A3 , A5 , A6 , legal , letter
											exportOptions: {
												columns: ':visible',
												search: 'applied',
												order: 'applied'
											},
											customize: function (doc) {
												//Remove the title created by datatTables
												doc.content.splice(0,1);
												//Create a date string that we use in the footer. Format is dd-mm-yyyy
												var now = new Date();
												var jsDate = now.getDate()+'-'+(now.getMonth()+1)+'-'+now.getFullYear()+' '+now.getHours()+':'+now.getMinutes()+':'+now.getSeconds();
												
												doc.pageMargins = [20,100,20,30];
												// Set the font size fot the entire document
												doc.defaultStyle.fontSize = 10;
												// Set the fontsize for the table header
												doc.styles.tableHeader.fontSize = 10;
											//	doc.styles.tableHeader.marginBotton = 1000;
												
												// Create a header object with 3 columns
												// Left side: Logo
												// Middle: brandname
												// Right side: A document title
												doc['header']=(function() {
													return {
														columns: [
															{
																image: logo,
																width: 810
															}
														],
														margin: 20
													}
												});
												// Create a footer object with 2 columns
												// Left side: report creation date
												// Right side: current page and total pages
												doc['footer']=(function(page, pages) {
													return {
														columns: [
															{
																alignment: 'left',
																text: ['Generated by: ', { text: 'MahaSevaarth'}]
															},
															{
																alignment: 'center',
																text: ['Page : ',  { text: page.toString() },	' of ',	{ text: pages.toString() }]
															},
															{
																alignment: 'right',
																text: ['Generated on : ', { text:MyDate }]
																//text: ['Date and Time ', { text: page.toString() },	' of ',	{ text: pages.toString() }]
																
															}
														],
														margin: 10
													}
												});
												// Change dataTable layout (Table styling)
												// To use predefined layouts uncomment the line below and comment the custom lines below
												// doc.content[0].layout = 'lightHorizontalLines'; // noBorders , headerLineOnly
												var objLayout = {};
												objLayout['hLineWidth'] = function(i) { return .5; };
												objLayout['vLineWidth'] = function(i) { return .5; };
												objLayout['hLineColor'] = function(i) { return '#aaa'; };
												objLayout['vLineColor'] = function(i) { return '#aaa'; };
												objLayout['paddingLeft'] = function(i) { return 4; };
												objLayout['paddingRight'] = function(i) { return 4; };
												//objLayout['paddingTop'] = function(i) { return 40; };
												//fillColor: function(row, col, node) { return row > 0 && row % 2 ? 'white' : 'white'; }
												objLayout['fillColor'] =  function(i) { '#000' };
												doc.content[0].layout = objLayout;
										}
										}]
										
								}); */
					});
</script>

<style type="text/css">
.pageTitle {
	text-align: center;
	font-weight: 600;
	border: none !important;
}
/* table#example td:nth-child(5),table#example td:nth-child(4),table#example td:nth-child(6),table#example td:nth-child(7) {
    text-align: center;
    align-items:center;
} */
.body-contents {
	margin-top: 90px;
	box-shadow: 0px 0px 7px #ccc;
	min-height: 550px;
}

.logo-mainn {
	border-bottom: 2px solid orange;
	padding-bottom: 5px;
}

.navbar-inverse {
	background-color: #37bec3;
	border-color: #37bec3;
}

.navbar-nav li a {
	color: #fff !IMPORTANT;
}

.navbar-inverse .navbar-nav>.active>a {
	background-color: #157478 !important;
}

.navbar-inverse .navbar-nav>li:hover {
	background-color: #157478 !important;
}

.m-title {
	font-size: 25px;
	font-family: 'Alfa Slab One', arial;
	letter-spacing: 0px;
	color: #ef7c01;
	font-weight: 900;
	margin-bottom: 0;
}

.logo-sub-text {
	position: relative;
	top: 5px;
	color: #004b8f;
}

.logo-Title {
	margin: auto 0;
	padding: 0 15px;
}

.flex {
	display: flex;
	align-items: center;
}

img.logo-img {
	width: 100%;
	max-width: 90px;
}

img.logo-secondary {
	max-width: 75px;
}

img.logo-img2 {
	max-width: 90px;
}

.navbar {
	min-height: 30px !important;
}

@media screen and (max-width: 750px) {
	.logobar1 {
		display: none;
	}
	.body-contents {
		min-height: auto !important;
	}
	caption {
		font-size: 14px;
	}
}

th {
	background: #fee4d7 !IMPORTANT;
	text-align: center;
}

td:nth-child(2) {
	text-align: left !important;
}

.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
	.table>thead>tr>td, .table>thead>tr>th {
	padding: 8px;
	vertical-align: middle !important;
	text-align:center;
}

caption {
	color: #000;
	font-size: 20px;
	text-align: center;
	font-weight: 600;
	background: #fdfb08;
}

.next, .previous, .dt-button {
	border-radius: 4px;
	border: 1px solid #428bca;
	padding: 3px 12px;
	margin-left: 5px;
	background: #428bca;
	color: #fff;
}

.dt-buttons {
	margin-bottom: 20px;
}

div#example_info {
	text-align: right;
}

div#example_paginate {
	text-align: right;
	margin-top: 10px;
}
</style>




</head>
</div>
<img src="images/mahasevaarth.png" id="image" name="image"
	crossorigin="anonymous" style="display: none;">
<!-- Added Brijoy  -->
<div class="container body-contents">
	<div class="hearder-top">
		<div class="user-name-main row">
			<div id="txt" class="Heading-timer col-md-4"></div>
			<div class="col-md-4 deptNameheaderTop">
				<input type="hidden" name="departmentCode" id="departmentCode"
					th:value="${departmentCode}" /> <span th:text="${departmentName}"></span>
			</div>
			<div class="col-md-4 user-name-main-top">
				<span class="User-name2"><span id="userName"
					th:text="${loggedInUserType} +' |' "></span></span> <span
					class="User-name"> <span id="userName"
					th:text="${loggedInUserName}"> </span>
				</span>
			</div>
		</div>

		<div class=" logo-mainn">
			<div class="row logo-row">
				<div class="col-md-9 flex">
					<div class="logobar ">
						<a class="logo" href="#"><img src="images/logo-v16.png"
							class="logo-img"></a>
					</div>
					<h5 class="logo-Title">
						<p class="m-title">MahaSevaarth</p>
						<p class="logo-sub-text">Official Website of Government of
							Maharashtra</p>
					</h5>
				</div>
				<div class="col-md-3 text-right">
					<div class="logobar1">
						<a class="logo-secondary-m" href="#"><img
							src="images/mahait_logo.png" class="logo-secondary"></a> <a
							class="logo" href="#"><img src="images/GOM.png"
							class="logo-img2"></a>
					</div>
				</div>
			</div>
		</div>

		<!-- Ennd by Brijoy09042020 -->



		<h2 align="center">
			<font><strong>MahaSevaarth Paybill Status</strong></font>
		</h2>



		<!-- </tr>
<tr bgcolor="#A52A2A">
<td><b>Month</b></td>
<td><b>Year</b></td>




</tr> -->
		<img src="/images/report.png" id="image" name="image"
			crossorigin="anonymous" style="display: none;">
		<div class="table-main">

			<!-- <button id="excelbtn" class="chrome dt-button buttons-pdf buttons-html5">EXCEL </button> -->
			<!-- Table goes here -->
			<table class="table table-bordered" id="example">
				<thead>
					<tr>
						<th>Sr No</th>
						<th>Department Name(Application)</th>
						<th>Paybill Month</th>
						<th>Total Employee</th>
						<th>Paybill Generated by Level1 (Employee's)</th>
						<th>Paybill Consolidated by Level2 (Employee's)</th>
						<th>Paybill Approved by Beams (Employee)</th>

					</tr>
				</thead>
				<tbody>




					<%
						String driverName = "com.ibm.db2.jcc.DB2Driver";
						/* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
						/* String connectionUrl = "jdbc:db2://10.10.10.3:55000/";
						 String dbName = "RDD"; */
						String connectionUrl = "jdbc:db2://10.187.201.241:50001/";
						String dbName = "SALAARTH";

						String userId = "ifms";
						String password = "2020ifms";

						try {
							Class.forName(driverName);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

						Connection connection = null;
						Statement statement = null;
						ResultSet resultSet = null;
					%>
					<%
						try {
							connection = DriverManager.getConnection(
									connectionUrl + dbName, userId, password);
							statement = connection.createStatement();
							String sql = "select temp1.mont as month,monthname ((current_date -1 month) ,'YYYY-MM-DD')||'-'||year(current_date-1 month) as MONTH_YEAR ,sum(pay_bill_gen) as pay_bill_generated,sum(pay_bill_consol) as pay_bill_consolidated,sum(pay_bill_forw) as pay_bill_forwded_BEams ,sum(total_emp) as Total_employees  from (select temp.mont,sum(temp.count_2020) as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp from ( select b.PAYBILL_MONTH as mont,b.APPROVE_FLAG,b.status,count(*)  as count_2020  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH =3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG in (0,1)  and b.status in (0,2,3,4) GROUP BY b.PAYBILL_MONTH,b.APPROVE_FLAG,b.status) as temp  group by  temp.mont union all select b.PAYBILL_MONTH as mont,0 as pay_bill_gen,count(*)  as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH=3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG=0   and b.status=4 GROUP BY b.PAYBILL_MONTH  union all select HPB.paybill_month as mont,0 as pay_bill_gen,0  as pay_bill_consol,count(*) as pay_bill_forw, 0 as total_emp  from  ifms.HR_PAY_PAYBILL  as HPB inner join ifms.PAYBILL_HEAD_MPG as PHM on HPB.PAYBILL_GRP_ID=PHM.paybill_id  and HPB.PAYBILL_MONTH=PHM.PAYBILL_MONTH and HPB.PAYBILL_YEAR=PHM.PAYBILL_YEAR inner join ifms.CONSOLIDATED_BILL_MPG as CBM on PHM.PAYBILL_ID=CBM.PAYBILL_ID inner join ifms.CONSOLIDATED_BILL_MST as CBMST on CBM.CONS_BILL_ID=CBMST.CONS_BILL_ID where HPB.paybill_month=3 and HPB.paybill_year =2020 and CBMST.month =3 and CBMST.year=2020 and CBMST.status in (5) group by HPB.paybill_month union all select 3 as mont,0 as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, count(*) as total_emp from ifms.MST_DCPS_EMP where ddo_code in (select ZP_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1) and zp_status=10) as temp1 group by temp1.mont";

							resultSet = statement.executeQuery(sql);
							while (resultSet.next()) {
					%>
					<td>5</td>
					<td>Rural Development Department (PanchayatRajSevaarth)</td>
					<td><%=resultSet.getString("month_year")%></td>
					<td><center><%=resultSet.getString("Total_employees")%></center>
						<%-- <c:out value="${item[4]}"/> --%></td>
					<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
					<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
					<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>

					</tr>

					<%
						}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {

							if (resultSet != null) {
								try {
									resultSet.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (statement != null) {
								try {
									statement.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (connection != null) {
								try {
									connection.close();
								} catch (Exception e) { /* ignored */
								}
							}

						}
					%>





					<tr>
						<%
							driverName = "com.ibm.db2.jcc.DB2Driver";
							/* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
							/* connectionUrl = "jdbc:db2://10.10.10.3:55000/";
							 dbName = "SOJ"; */
							connectionUrl = "jdbc:db2://10.187.201.232:50001/";
							dbName = "SALAARTH";

							userId = "ifms";
							password = "ifms123";

							try {
								Class.forName(driverName);
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}

							connection = null;
							statement = null;
							resultSet = null;
						%>
						<%
							try {
								connection = DriverManager.getConnection(
										connectionUrl + dbName, userId, password);
								statement = connection.createStatement();
								String sql = "select temp1.mont as month,monthname ((current_date -1 month) ,'YYYY-MM-DD')||'-'||year(current_date-1 month) as MONTH_YEAR ,sum(pay_bill_gen) as pay_bill_generated,sum(pay_bill_consol) as pay_bill_consolidated,sum(pay_bill_forw) as pay_bill_forwded_BEams ,sum(total_emp) as Total_employees  from (select temp.mont,sum(temp.count_2020) as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp from ( select b.PAYBILL_MONTH as mont,b.APPROVE_FLAG,b.status,count(*)  as count_2020  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH =3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG in (0,1)  and b.status in (0,2,3,4) GROUP BY b.PAYBILL_MONTH,b.APPROVE_FLAG,b.status) as temp  group by  temp.mont union all select b.PAYBILL_MONTH as mont,0 as pay_bill_gen,count(*)  as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH=3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG=0   and b.status=4 GROUP BY b.PAYBILL_MONTH  union all select HPB.paybill_month as mont,0 as pay_bill_gen,0  as pay_bill_consol,count(*) as pay_bill_forw, 0 as total_emp  from  ifms.HR_PAY_PAYBILL  as HPB inner join ifms.PAYBILL_HEAD_MPG as PHM on HPB.PAYBILL_GRP_ID=PHM.paybill_id  and HPB.PAYBILL_MONTH=PHM.PAYBILL_MONTH and HPB.PAYBILL_YEAR=PHM.PAYBILL_YEAR inner join ifms.CONSOLIDATED_BILL_MPG as CBM on PHM.PAYBILL_ID=CBM.PAYBILL_ID inner join ifms.CONSOLIDATED_BILL_MST as CBMST on CBM.CONS_BILL_ID=CBMST.CONS_BILL_ID where HPB.paybill_month=3 and HPB.paybill_year =2020 and CBMST.month =3 and CBMST.year=2020 and CBMST.status in (5) group by HPB.paybill_month union all select 3 as mont,0 as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, count(*) as total_emp from ifms.MST_DCPS_EMP where ddo_code in (select ZP_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1) and zp_status=10) as temp1 group by temp1.mont";

								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
						%>
						<td>6</td>
						<td>Social Justice / VJNT Department (SamajSevaarth)</td>
						<td><%=resultSet.getString("month_year")%></td>
						<td><center><%=resultSet.getString("Total_employees")%></center>
							<%-- <c:out value="${item[4]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>

					</tr>

					<%
						}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {

							if (resultSet != null) {
								try {
									resultSet.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (statement != null) {
								try {
									statement.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (connection != null) {
								try {
									connection.close();
								} catch (Exception e) { /* ignored */
								}
							}

						}
					%>


					<tr>
						<%
							driverName = "com.ibm.db2.jcc.DB2Driver";
							/* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
							/* connectionUrl = "jdbc:db2://10.10.10.3:55000/";
							dbName = "HTE"; */
							connectionUrl = "jdbc:db2://10.187.201.236:50001/";
							dbName = "SALAARTH";
							userId = "ifms";
							password = "ifms123";
							try {
								Class.forName(driverName);
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}

							connection = null;
							statement = null;
							resultSet = null;
						%>
						<%
							try {
								connection = DriverManager.getConnection(
										connectionUrl + dbName, userId, password);
								statement = connection.createStatement();
								String sql = "select temp1.mont as month ,monthname ((current_date -1 month) ,'YYYY-MM-DD')||'-'||year(current_date-1 month) as MONTH_YEAR, sum(pay_bill_gen) as pay_bill_generated,sum(pay_bill_consol) as pay_bill_consolidated,sum(pay_bill_forw) as pay_bill_forwded_BEams ,sum(total_emp) as Total_employees  from (select temp.mont,sum(temp.count_2020) as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp from ( select b.PAYBILL_MONTH as mont,b.APPROVE_FLAG,b.status,count(*)  as count_2020  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH =3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG in (0,1)  and b.status in (0,2,3,4) GROUP BY b.PAYBILL_MONTH,b.APPROVE_FLAG,b.status) as temp  group by  temp.mont union all select b.PAYBILL_MONTH as mont,0 as pay_bill_gen,count(*)  as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH=3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG=0   and b.status=4 GROUP BY b.PAYBILL_MONTH union all select 3 as mont,0 as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, count(*) as total_emp from ifms.MST_DCPS_EMP where ddo_code in (select ZP_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1) and zp_status=10) as temp1 group by temp1.mont";

								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
						%>
						<td>3</td>
						<td>Higher and Technical Education Department (HTESevaarth)</td>
						<td><%=resultSet.getString("month_year")%></td>
						<td><center><%=resultSet.getString("Total_employees")%></center>
							<%-- <c:out value="${item[4]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>

					</tr>

					<%
						}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {

							if (resultSet != null) {
								try {
									resultSet.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (statement != null) {
								try {
									statement.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (connection != null) {
								try {
									connection.close();
								} catch (Exception e) { /* ignored */
								}
							}

						}
					%>












					<tr>
						<%
							driverName = "com.ibm.db2.jcc.DB2Driver";
							/* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
							/* connectionUrl = "jdbc:db2://10.10.10.3:55000/";
							dbName = "SALAARTH";
							 */
							connectionUrl = "jdbc:db2://10.187.201.244:50001/";
							dbName = "SALAARTH";

							userId = "ifms";
							password = "ifms123";
							try {
								Class.forName(driverName);
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}

							connection = null;
							statement = null;
							resultSet = null;
						%>
						<%
							try {
								connection = DriverManager.getConnection(
										connectionUrl + dbName, userId, password);
								statement = connection.createStatement();
								String sql = "select temp1.mont as month,monthname ((current_date -1 month) ,'YYYY-MM-DD')||'-'||year(current_date-1 month) as MONTH_YEAR ,sum(pay_bill_gen) as pay_bill_generated,sum(pay_bill_consol) as pay_bill_consolidated,sum(pay_bill_forw) as pay_bill_forwded_BEams ,sum(total_emp) as Total_employees  from (select temp.mont,sum(temp.count_2020) as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp from ( select b.PAYBILL_MONTH as mont,b.APPROVE_FLAG,b.status,count(*)  as count_2020  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH =3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG in (0,1)  and b.status in (0,2,3,4) GROUP BY b.PAYBILL_MONTH,b.APPROVE_FLAG,b.status) as temp  group by  temp.mont union all select b.PAYBILL_MONTH as mont,0 as pay_bill_gen,count(*)  as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH=3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG=0   and b.status=4 GROUP BY b.PAYBILL_MONTH  union all select HPB.paybill_month as mont,0 as pay_bill_gen,0  as pay_bill_consol,count(*) as pay_bill_forw, 0 as total_emp  from  ifms.HR_PAY_PAYBILL  as HPB inner join ifms.PAYBILL_HEAD_MPG as PHM on HPB.PAYBILL_GRP_ID=PHM.paybill_id  and HPB.PAYBILL_MONTH=PHM.PAYBILL_MONTH and HPB.PAYBILL_YEAR=PHM.PAYBILL_YEAR inner join ifms.CONSOLIDATED_BILL_MPG as CBM on PHM.PAYBILL_ID=CBM.PAYBILL_ID inner join ifms.CONSOLIDATED_BILL_MST as CBMST on CBM.CONS_BILL_ID=CBMST.CONS_BILL_ID where HPB.paybill_month=3 and HPB.paybill_year =2020 and CBMST.month =3 and CBMST.year=2020 and CBMST.status in (5) group by HPB.paybill_month union all select 3 as mont,0 as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, count(*) as total_emp from ifms.MST_DCPS_EMP where ddo_code in (select ZP_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1) and zp_status=10) as temp1 group by temp1.mont";

								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
						%>
						<td>2</td>
						<td>Animal Husbandry and Fisheries Department (MafSuaarth)</td>
						<td><%=resultSet.getString("month_year")%></td>
						<td><center><%=resultSet.getString("Total_employees")%></center>
							<%-- <c:out value="${item[4]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>

					</tr>

					<%
						}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {

							if (resultSet != null) {
								try {
									resultSet.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (statement != null) {
								try {
									statement.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (connection != null) {
								try {
									connection.close();
								} catch (Exception e) { /* ignored */
								}
							}

						}
					%>






					<tr>
						<%
							driverName = "com.ibm.db2.jcc.DB2Driver";
							/* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
							/* connectionUrl = "jdbc:db2://10.10.10.3:55000/";
							dbName = "TRIBAL"; */
							connectionUrl = "jdbc:db2://10.187.201.238:50001/";
							dbName = "SALAARTH";

							userId = "ifms";
							password = "ifms123";
							try {
								Class.forName(driverName);
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}

							connection = null;
							statement = null;
							resultSet = null;
						%>
						<%
							try {
								connection = DriverManager.getConnection(
										connectionUrl + dbName, userId, password);
								statement = connection.createStatement();
								String sql = "select temp1.mont as month,monthname ((current_date -1 month) ,'YYYY-MM-DD')||'-'||year(current_date-1 month) as MONTH_YEAR ,sum(pay_bill_gen) as pay_bill_generated,sum(pay_bill_consol) as pay_bill_consolidated,sum(pay_bill_forw) as pay_bill_forwded_BEams ,sum(total_emp) as Total_employees  from (select temp.mont,sum(temp.count_2020) as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp from ( select b.PAYBILL_MONTH as mont,b.APPROVE_FLAG,b.status,count(*)  as count_2020  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH =3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG in (0,1)  and b.status in (0,2,3,4) GROUP BY b.PAYBILL_MONTH,b.APPROVE_FLAG,b.status) as temp  group by  temp.mont union all select b.PAYBILL_MONTH as mont,0 as pay_bill_gen,count(*)  as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH=3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG=0   and b.status=4 GROUP BY b.PAYBILL_MONTH  union all select HPB.paybill_month as mont,0 as pay_bill_gen,0  as pay_bill_consol,count(*) as pay_bill_forw, 0 as total_emp  from  ifms.HR_PAY_PAYBILL  as HPB inner join ifms.PAYBILL_HEAD_MPG as PHM on HPB.PAYBILL_GRP_ID=PHM.paybill_id  and HPB.PAYBILL_MONTH=PHM.PAYBILL_MONTH and HPB.PAYBILL_YEAR=PHM.PAYBILL_YEAR inner join ifms.CONSOLIDATED_BILL_MPG as CBM on PHM.PAYBILL_ID=CBM.PAYBILL_ID inner join ifms.CONSOLIDATED_BILL_MST as CBMST on CBM.CONS_BILL_ID=CBMST.CONS_BILL_ID where HPB.paybill_month=3 and HPB.paybill_year =2020 and CBMST.month =3 and CBMST.year=2020 and CBMST.status in (5) group by HPB.paybill_month union all select 3 as mont,0 as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, count(*) as total_emp from ifms.MST_DCPS_EMP where ddo_code in (select ZP_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1) and zp_status=10) as temp1 group by temp1.mont";

								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
						%>
						<td>7</td>
						<td>Tribal Development Department (AshramShalaarth)</td>
						<td><%=resultSet.getString("month_year")%></td>
						<td><center><%=resultSet.getString("Total_employees")%></center>
							<%-- <c:out value="${item[4]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>

					</tr>

					<%
						}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {

							if (resultSet != null) {
								try {
									resultSet.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (statement != null) {
								try {
									statement.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (connection != null) {
								try {
									connection.close();
								} catch (Exception e) { /* ignored */
								}
							}

						}
					%>







					<tr>
						<%
							driverName = "com.ibm.db2.jcc.DB2Driver";
							/* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
							/* connectionUrl = "jdbc:db2://10.10.10.3:55000/";
							dbName = "DMER"; */
							connectionUrl = "jdbc:db2://10.187.201.229:50001/";
							dbName = "SALAARTH";

							userId = "ifms";
							password = "ifms123";
							try {
								Class.forName(driverName);
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}

							connection = null;
							statement = null;
							resultSet = null;
						%>
						<%
							try {
								connection = DriverManager.getConnection(
										connectionUrl + dbName, userId, password);
								statement = connection.createStatement();
								String sql = "select temp1.mont as month,monthname ((current_date -1 month) ,'YYYY-MM-DD')||'-'||year(current_date-1 month) as MONTH_YEAR ,sum(pay_bill_gen) as pay_bill_generated,sum(pay_bill_consol) as pay_bill_consolidated,sum(pay_bill_forw) as pay_bill_forwded_BEams ,sum(total_emp) as Total_employees  from (select temp.mont,sum(temp.count_2020) as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp from ( select b.PAYBILL_MONTH as mont,b.APPROVE_FLAG,b.status,count(*)  as count_2020  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH =3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG in (0,1)  and b.status in (0,2,3,4) GROUP BY b.PAYBILL_MONTH,b.APPROVE_FLAG,b.status) as temp  group by  temp.mont union all select b.PAYBILL_MONTH as mont,0 as pay_bill_gen,count(*)  as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH=3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG=0   and b.status=4 GROUP BY b.PAYBILL_MONTH  union all select HPB.paybill_month as mont,0 as pay_bill_gen,0  as pay_bill_consol,count(*) as pay_bill_forw, 0 as total_emp  from  ifms.HR_PAY_PAYBILL  as HPB inner join ifms.PAYBILL_HEAD_MPG as PHM on HPB.PAYBILL_GRP_ID=PHM.paybill_id  and HPB.PAYBILL_MONTH=PHM.PAYBILL_MONTH and HPB.PAYBILL_YEAR=PHM.PAYBILL_YEAR inner join ifms.CONSOLIDATED_BILL_MPG as CBM on PHM.PAYBILL_ID=CBM.PAYBILL_ID inner join ifms.CONSOLIDATED_BILL_MST as CBMST on CBM.CONS_BILL_ID=CBMST.CONS_BILL_ID where HPB.paybill_month=3 and HPB.paybill_year =2020 and CBMST.month =3 and CBMST.year=2020 and CBMST.status in (5) group by HPB.paybill_month union all select 3 as mont,0 as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, count(*) as total_emp from ifms.MST_DCPS_EMP where ddo_code in (select ZP_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1) and zp_status=10) as temp1 group by temp1.mont";

								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
						%>
						<td>4</td>
						<td>Medical Department (Ayurvedaarth)</td>
						<td><%=resultSet.getString("month_year")%></td>
						<td><center><%=resultSet.getString("Total_employees")%></center>
							<%-- <c:out value="${item[4]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>

					</tr>

					<%
						}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {

							if (resultSet != null) {
								try {
									resultSet.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (statement != null) {
								try {
									statement.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (connection != null) {
								try {
									connection.close();
								} catch (Exception e) { /* ignored */
								}
							}

						}
					%>



					<tr>
						<%
							driverName = "com.ibm.db2.jcc.DB2Driver";
							/* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
							/* connectionUrl = "jdbc:db2://10.10.10.3:55000/";
							dbName = "AGRI";
							 */
							connectionUrl = "jdbc:db2://10.187.201.217:50001/";

							dbName = "SALAARTH";
							userId = "ifms";
							password = "ifms@0721";
							try {
								Class.forName(driverName);
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}

							connection = null;
							statement = null;
							resultSet = null;
						%>
						<%
							try {
								connection = DriverManager.getConnection(
										connectionUrl + dbName, userId, password);
								statement = connection.createStatement();
								String sql = "select temp1.mont as month,monthname ((current_date -1 month) ,'YYYY-MM-DD')||'-'||year(current_date-1 month) as MONTH_YEAR ,sum(pay_bill_gen) as pay_bill_generated,sum(pay_bill_consol) as pay_bill_consolidated,sum(pay_bill_forw) as pay_bill_forwded_BEams ,sum(total_emp) as Total_employees  from (select temp.mont,sum(temp.count_2020) as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp from ( select b.PAYBILL_MONTH as mont,b.APPROVE_FLAG,b.status,count(*)  as count_2020  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH =3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG in (0,1)  and b.status in (0,2,3,4) GROUP BY b.PAYBILL_MONTH,b.APPROVE_FLAG,b.status) as temp  group by  temp.mont union all select b.PAYBILL_MONTH as mont,0 as pay_bill_gen,count(*)  as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id and a.PAYBILL_MONTH=b.PAYBILL_MONTH and a.PAYBILL_YEAR=b.PAYBILL_YEAR where  b.PAYBILL_MONTH=3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG=0   and b.status=4 GROUP BY b.PAYBILL_MONTH  union all select HPB.paybill_month as mont,0 as pay_bill_gen,0  as pay_bill_consol,count(*) as pay_bill_forw, 0 as total_emp  from  ifms.HR_PAY_PAYBILL  as HPB inner join ifms.PAYBILL_HEAD_MPG as PHM on HPB.PAYBILL_GRP_ID=PHM.paybill_id  and HPB.PAYBILL_MONTH=PHM.PAYBILL_MONTH and HPB.PAYBILL_YEAR=PHM.PAYBILL_YEAR inner join ifms.CONSOLIDATED_BILL_MPG as CBM on PHM.PAYBILL_ID=CBM.PAYBILL_ID inner join ifms.CONSOLIDATED_BILL_MST as CBMST on CBM.CONS_BILL_ID=CBMST.CONS_BILL_ID where HPB.paybill_month=3 and HPB.paybill_year =2020 and CBMST.month =3 and CBMST.year=2020 and CBMST.status in (5) group by HPB.paybill_month union all select 3 as mont,0 as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, count(*) as total_emp from ifms.MST_DCPS_EMP where ddo_code in (select ZP_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1) and zp_status=10) as temp1 group by temp1.mont";

								resultSet = statement.executeQuery(sql);
								while (resultSet.next()) {
						%>
						<td>1</td>
						<td>Agriculture Department (SauSevaarth)</td>
						<td><%=resultSet.getString("month_year")%></td>
						<td><center><%=resultSet.getString("Total_employees")%></center>
							<%-- <c:out value="${item[4]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>

					</tr>

					<%
						}

						} catch (Exception e) {
							e.printStackTrace();
						} finally {

							if (resultSet != null) {
								try {
									resultSet.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (statement != null) {
								try {
									statement.close();
								} catch (Exception e) { /* ignored */
								}
							}
							if (connection != null) {
								try {
									connection.close();
								} catch (Exception e) { /* ignored */
								}
							}

						}
					%>






				</tbody>

			</table>


		</div>
		<center>
			<a href="index.html">
				<button class="btn btn-md dt-button">Back</button>
			</a>
		</center>