<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
h2 strong {
    font-weight: 700;
    font-size: 18px;
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
	text-align: center;
}

p.caption {
	color: #000;
	font-size: 20px;
	text-align: center;
	font-weight: 600;
	background: #fdfb08;
	margin-bottom: 0 !important;
}
@media screen and (max-width:767px){
p.caption {
    font-size: 11px;
}
.table>caption+thead>tr:first-child>td, .table>caption+thead>tr:first-child>th, .table>colgroup+thead>tr:first-child>td, .table>colgroup+thead>tr:first-child>th, .table>thead:first-child>tr:first-child>td, .table>thead:first-child>tr:first-child>th {
    border-top: 0;
    word-break: break-word;
    font-size: 8px;
}
.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
    padding: 0px 2px;
    font-size: 9px;
    vertical-align: middle !important;
    text-align: center;
}
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










			<%
				String driverName = "com.ibm.db2.jcc.DB2Driver";
				/* String connectionUrl = "jdbc:db2://124.153.117.34:55000/"; */
				/* connectionUrl = "jdbc:db2://10.10.10.3:55000/";
				dbName = "DMER"; */
				String connectionUrl = "jdbc:db2://10.187.201.229:50001/";
				String dbName = "SALAARTH";

				String userId = "ifms";
				String password = "ifms@0721";

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
					String sql = "select Sr_No, DEPARTMENT_NAME as department , PAYBILL_MONTH_YEAR as month_year,TOTAL_EMPLOYEE as Total_employees, PAYBILL_GENERATED_BY_LEVEL1 as pay_bill_generated,PAYBILL_CONSOLIDATED_BY_LEVEL2 as pay_bill_consolidated,PAYBILL_APRROVED_BY_BEAMS as pay_bill_forwded_BEams,To_CHar(CREATED_DATE,'DD/MM/YYYY HH24:MI:SS') as CREATED_DATE,to_char(Updated_DATE,'DD/MM/YYYY HH24:MI:SS') as Updated_DATE,SALARY_CREDIT_CNT as SALARY_CREDIT_CNT from Paybill_Report_Status";

					resultSet = statement.executeQuery(sql);
			%>

			<%
				int i = 0;
					while (resultSet.next()) {
						i++;
						if (i == 1) {
			%>
			<!-- <table><tr><td  colspan="6" class="text-center reportHead2">Customised Sevaarth Paybill Status Report</td></tr></table> -->
			<p class="caption">Customised Sevaarth Paybill Status Report</p>
			<table class="table table-bordered" id="paybillTable">
				<thead>
					<tr>
						<th>Sr No</th>
						<th>Department Name(Application)</th>
						<!--   <th>Paybill Month</th>   -->
						<th>Total Employee</th>
						<th>Paybill Generated by Level1 (Employee's) </br><%=resultSet.getString("month_year")%></th>
						<th>Paybill Consolidated by Level2 (Employee's)</br><%=resultSet.getString("month_year")%></th>
						<th>Paybill Approved by Beams (Employee)</br><%=resultSet.getString("month_year")%></th>
						<!-- <th>Created Date</th> -->
						<th>Salary Credit Count</th>
						<th>Updated Date</th>
					</tr>
				</thead>
				<tbody>
					<tr>

						<td><%=resultSet.getString("Sr_No")%></td>
						<td><%=resultSet.getString("department")%></td>
						<%--   <td><%=resultSet.getString("month_year") %></td> --%>
						<td><center><%=resultSet.getString("Total_employees")%></center>
							<%-- <c:out value="${item[4]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
						<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>
						<%-- <td><%=resultSet.getString("CREATED_DATE") %> --%>
						<td><%=resultSet.getString("SALARY_CREDIT_CNT") %>
						<td><%=resultSet.getString("UPDATED_DATE")%>
					</tr>
					<%
						} else {
					%>

					<td><%=resultSet.getString("Sr_No")%></td>
					<td><%=resultSet.getString("department")%></td>
					<%--     <td><%=resultSet.getString("month_year") %></td> --%>
					<td><center><%=resultSet.getString("Total_employees")%></center>
						<%-- <c:out value="${item[4]}"/> --%></td>
					<td><%=resultSet.getString("pay_bill_generated")%><%-- <c:out value="${item[1]}"/> --%></td>
					<td><%=resultSet.getString("pay_bill_consolidated")%><%-- <c:out value="${item[2]}"/> --%></td>
					<td><%=resultSet.getString("pay_bill_forwded_BEams")%><%-- <c:out value="${item[3]}"/> --%></td>
					<%-- <td><%=resultSet.getString("CREATED_DATE") %> --%>
					<td><%=resultSet.getString("SALARY_CREDIT_CNT") %>
					<td><%=resultSet.getString("UPDATED_DATE")%>
					</tr>

					<%
						}
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

		<script>
			sortTable();
			function sortTable() {
				var table, rows, switching, i, x, y, shouldSwitch;
				table = document.getElementById("paybillTable");
				switching = true;
				/*Make a loop that will continue until
				no switching has been done:*/
				while (switching) {
					//start by saying: no switching is done:
					switching = false;
					rows = table.rows;
					/*Loop through all table rows (except the
					first, which contains table headers):*/
					for (i = 1; i < (rows.length - 1); i++) {
						//start by saying there should be no switching:
						shouldSwitch = false;
						/*Get the two elements you want to compare,
						one from current row and one from the next:*/
						x = rows[i].getElementsByTagName("TD")[0];
						y = rows[i + 1].getElementsByTagName("TD")[0];
						//check if the two rows should switch place:
						if (Number(x.innerHTML) > Number(y.innerHTML)) {
							//if so, mark as a switch and break the loop:
							shouldSwitch = true;
							break;
						}
					}
					if (shouldSwitch) {
						/*If a switch has been marked, make the switch
						and mark that a switch has been done:*/
						rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
						switching = true;
					}
				}
			}
		</script>