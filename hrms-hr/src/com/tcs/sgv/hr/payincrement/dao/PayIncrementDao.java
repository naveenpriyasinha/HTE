package com.tcs.sgv.hr.payincrement.dao;

import java.util.Date;
import java.util.List;

public interface PayIncrementDao {

	/* method to get data from transaction table */
	public  List displaydetails9(long reqid);

	/* method to get data from history table */
	public  List displaydetails10(long reqid);

	public  List actincdate(long userid);

	public  Date actualincdate_lwp(Date date, int days);

}