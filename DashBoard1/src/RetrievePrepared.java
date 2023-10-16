import java.sql.*;
class RetrievePrepared{
public static void main(String args[]){
try{
Class.forName("com.ibm.db2.jcc.DB2Driver");

Connection con=DriverManager.getConnection(
"jdbc:db2://124.153.117.34:55000/SOJ","ifms","ifms1234");

PreparedStatement stmt=con.prepareStatement("select temp1.mont as month ,monthname ((current_date -1 month) ,'YYYY-MM-DD')||'-'||year(current_date-1 month) as MONTH_YEAR,sum(pay_bill_gen) as pay_bill_generated,sum(pay_bill_consol) as pay_bill_consolidated,sum(pay_bill_forw) as pay_bill_forwded_BEams ,sum(total_emp) as Total_employees  from (select temp.mont,sum(temp.count_2020) as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp from ( select b.PAYBILL_MONTH as mont,b.APPROVE_FLAG,b.status,count(*)  as count_2020  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id  where  b.PAYBILL_MONTH =3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG in (0,1)  and b.status in (0,4)  GROUP BY b.PAYBILL_MONTH,b.APPROVE_FLAG,b.status) as temp  group by  temp.mont union all  select b.PAYBILL_MONTH as mont,0 as pay_bill_gen,count(*)  as pay_bill_consol,0 as pay_bill_forw, 0 as total_emp  from ifms.HR_PAY_PAYBILL as a  inner join  ifms.paybill_head_mpg as b on a.PAYBILL_GRP_ID=b.paybill_id  where  b.PAYBILL_MONTH=3 and b.PAYBILL_YEAR=2020  and b.APPROVE_FLAG=0   and b.status=4 GROUP BY b.PAYBILL_MONTH  union all select CBMST.month as mont,0 as pay_bill_gen,0  as pay_bill_consol,count(*) as pay_bill_forw, 0 as total_emp  from  ifms.HR_PAY_PAYBILL  as HPB inner join ifms.PAYBILL_HEAD_MPG as PHM on HPB.PAYBILL_GRP_ID=PHM.PAYBILL_ID inner join ifms.CONSOLIDATED_BILL_MPG as CBM on PHM.PAYBILL_ID=CBM.PAYBILL_ID inner join ifms.CONSOLIDATED_BILL_MST as CBMST on CBM.CONS_BILL_ID=CBMST.CONS_BILL_ID where CBMST.month =3 and CBMST.year=2020 and CBMST.AUTH_NUMBER is not null  group by CBMST.month union all select 3 as mont,0 as pay_bill_gen,0 as pay_bill_consol,0 as pay_bill_forw, count(*) as total_emp    from ifms.MST_DCPS_EMP where ddo_code in (select ZP_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1) and zp_status=10) as temp1 group by temp1.mont");
ResultSet rs=stmt.executeQuery();
while(rs.next()){
System.out.println(rs.getBigDecimal(1)+" "+rs.getString(2));
}

con.close();

}catch(Exception e){ System.out.println(e);}

}
}