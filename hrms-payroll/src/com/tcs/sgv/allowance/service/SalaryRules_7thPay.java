package com.tcs.sgv.allowance.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.openl.CompiledOpenClass;
import org.openl.conf.UserContext;
import org.openl.impl.OpenClassJavaWrapper;
import org.openl.main.OpenLWrapper;
import org.openl.types.IOpenClass;
import org.openl.types.IOpenField;
import org.openl.types.IOpenMethod;
import org.openl.types.impl.DynamicObject;
import org.openl.types.java.JavaOpenClass;
import org.openl.util.Log;
import org.openl.util.RuntimeExceptionWrapper;
import org.openl.vm.IRuntimeEnv;

public class SalaryRules_7thPay
  implements OpenLWrapper
{
  Object __instance;
  public static IRuntimeEnv __env;
  public static IOpenClass __class;
  public static CompiledOpenClass __compiledClass;
  public static String __openlName = "org.openl.xls";
  public static String __src = "resources/payroll/rules/SalaryRules_7thPay.xls";
  public static String __userHome = ".";
  static IOpenField this_Field;
  static IOpenMethod calculateForce1Incentive100_Method;
  static IOpenMethod calculateCLA_Method;
  static IOpenMethod calculateTACentral_Method;
  static IOpenMethod calculateLeaveDays_Method;
  static IOpenMethod CheckCPFFlag_Method;
  static IOpenMethod calculateHA_Method;
  static IOpenMethod calculateGIS_Method;
  static IOpenMethod CheckMAHAGISFlag_Method;
  static IOpenMethod getCurrentAvailBal_Method;
  static IOpenMethod calculateLeaveExcess_Method;
  static IOpenMethod isBoardCorporation_Method;
  static IOpenMethod calculateForce1Incentive25_Method;
  static IOpenMethod getValue_Method;
  static IOpenMethod calculateDCPS_Method;
  static IOpenMethod calculateATS30_Method;
  static IOpenMethod calculateGISForIFS_Method;
  static IOpenMethod calcCPFval_Method;
  static IOpenMethod newCityClassification_Method;
  static IOpenMethod getDaysInMonth_Method;
  static IOpenMethod calculatePT_Method;
  static IOpenMethod calculateATS50_Method;
  static IOpenMethod calculateCentralDA_Method;
  static IOpenMethod calculateGISForIAS_Method;
  static IOpenMethod calculateHRR_Method;
  static IOpenMethod calculateTA_Method;
  static IOpenMethod calculateDA_Method;
  static IOpenMethod getTAValue_Method;
  static IOpenMethod calculateCGEGIS_Method;
  static IOpenMethod calculateHRA_Method;
  static IOpenMethod calculatePG_Method;
  static IOpenMethod calcPFVal_Method;
  static IOpenMethod calculateGISForIPS_Method;
  static IOpenMethod getYearFromDate_Method;
  static IOpenMethod getCode_Method;
  static IOpenMethod calculateLeaveRange_Method;
  static IOpenMethod getPTValue_Method;
  static IOpenMethod calculatePF_Method;
  static IOpenMethod getDateDiff_Method;
  static IOpenMethod isdirectRecruit_Method;
  static IOpenMethod getNewCityClassificationForHRA_Method;
  static IOpenMethod calcVal_Method;
  static IOpenMethod calculateFPA_Method;
  
  public SalaryRules_7thPay()
  {
    this(false);
  }
  
  public SalaryRules_7thPay(boolean ignoreErrors)
  {
    __init();
    if (!ignoreErrors) {
      __compiledClass.throwErrorExceptionsIfAny();
    }
    this.__instance = __class.newInstance(__env);
  }
  
  public DynamicObject getThis()
  {
    Object __res = this_Field.get(this.__instance, __env);
    return (DynamicObject)__res;
  }
  
  public void setThis(DynamicObject __var)
  {
    this_Field.set(this.__instance, __var, __env);
  }
  
  public double calculateForce1Incentive100(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateForce1Incentive100_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateCLA(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateCLA_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateTACentral(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateTACentral_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public int calculateLeaveDays(Date startDate, Date endDate, int monthGiven, int yearGiven)
  {
    Object[] __params = new Object[4];
    __params[0] = startDate;
    __params[1] = endDate;
    __params[2] = new Integer(monthGiven);
    __params[3] = new Integer(yearGiven);
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateLeaveDays_Method.invoke(__myInstance, __params, __env);
      return ((Integer)__res).intValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public boolean CheckCPFFlag(Date doj)
  {
    Object[] __params = new Object[1];
    __params[0] = doj;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = CheckCPFFlag_Method.invoke(__myInstance, __params, __env);
      return ((Boolean)__res).booleanValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateHA(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateHA_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateGIS(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateGIS_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public boolean CheckMAHAGISFlag(Date doj)
  {
    Object[] __params = new Object[1];
    __params[0] = doj;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = CheckMAHAGISFlag_Method.invoke(__myInstance, __params, __env);
      return ((Boolean)__res).booleanValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public int getCurrentAvailBal(Date startDate, Date endDate, int availBalance, Calendar cal, int monthGiven, int yearGiven)
  {
    Object[] __params = new Object[6];
    __params[0] = startDate;
    __params[1] = endDate;
    __params[2] = new Integer(availBalance);
    __params[3] = cal;
    __params[4] = new Integer(monthGiven);
    __params[5] = new Integer(yearGiven);
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getCurrentAvailBal_Method.invoke(__myInstance, __params, __env);
      return ((Integer)__res).intValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public int calculateLeaveExcess(Date startDate, Date endDate, int availBalance, int leavesTaken, int monthGiven, int yearGiven)
  {
    Object[] __params = new Object[6];
    __params[0] = startDate;
    __params[1] = endDate;
    __params[2] = new Integer(availBalance);
    __params[3] = new Integer(leavesTaken);
    __params[4] = new Integer(monthGiven);
    __params[5] = new Integer(yearGiven);
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateLeaveExcess_Method.invoke(__myInstance, __params, __env);
      return ((Integer)__res).intValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public int isBoardCorporation(long empType)
  {
    Object[] __params = new Object[1];
    __params[0] = new Long(empType);
    try
    {
      Object __myInstance = this.__instance;
      Object __res = isBoardCorporation_Method.invoke(__myInstance, __params, __env);
      return ((Integer)__res).intValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateForce1Incentive25(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateForce1Incentive25_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double getValue(double val, Map input)
  {
    Object[] __params = new Object[2];
    __params[0] = new Double(val);
    __params[1] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getValue_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateDCPS(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateDCPS_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateATS30(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateATS30_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateGISForIFS(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateGISForIFS_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calcCPFval(double val, Map input)
  {
    Object[] __params = new Object[2];
    __params[0] = new Double(val);
    __params[1] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calcCPFval_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public String newCityClassification(String cityClass)
  {
    Object[] __params = new Object[1];
    __params[0] = cityClass;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = newCityClassification_Method.invoke(__myInstance, __params, __env);
      return (String)__res;
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public int getDaysInMonth(int month, int year)
  {
    Object[] __params = new Object[2];
    __params[0] = new Integer(month);
    __params[1] = new Integer(year);
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getDaysInMonth_Method.invoke(__myInstance, __params, __env);
      return ((Integer)__res).intValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculatePT(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculatePT_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateATS50(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateATS50_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateCentralDA(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateCentralDA_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateGISForIAS(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateGISForIAS_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateHRR(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateHRR_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateTA(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateTA_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateDA(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateDA_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double getTAValue(double val, Map input)
  {
    Object[] __params = new Object[2];
    __params[0] = new Double(val);
    __params[1] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getTAValue_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateCGEGIS(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateCGEGIS_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateHRA(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateHRA_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculatePG(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculatePG_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calcPFVal(double val, Map input)
  {
    Object[] __params = new Object[2];
    __params[0] = new Double(val);
    __params[1] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calcPFVal_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateGISForIPS(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateGISForIPS_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public long getYearFromDate(Date date)
  {
    Object[] __params = new Object[1];
    __params[0] = date;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getYearFromDate_Method.invoke(__myInstance, __params, __env);
      return ((Long)__res).longValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public long getCode(String code)
  {
    Object[] __params = new Object[1];
    __params[0] = code;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getCode_Method.invoke(__myInstance, __params, __env);
      return ((Long)__res).longValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public int calculateLeaveRange(Date startDate, Date endDate, int monthGiven, int yearGiven)
  {
    Object[] __params = new Object[4];
    __params[0] = startDate;
    __params[1] = endDate;
    __params[2] = new Integer(monthGiven);
    __params[3] = new Integer(yearGiven);
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateLeaveRange_Method.invoke(__myInstance, __params, __env);
      return ((Integer)__res).intValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double getPTValue(double val, Map input)
  {
    Object[] __params = new Object[2];
    __params[0] = new Double(val);
    __params[1] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getPTValue_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculatePF(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculatePF_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public long getDateDiff(Date startDate, Date endDate)
  {
    Object[] __params = new Object[2];
    __params[0] = startDate;
    __params[1] = endDate;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getDateDiff_Method.invoke(__myInstance, __params, __env);
      return ((Long)__res).longValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public int isdirectRecruit(long empType)
  {
    Object[] __params = new Object[1];
    __params[0] = new Long(empType);
    try
    {
      Object __myInstance = this.__instance;
      Object __res = isdirectRecruit_Method.invoke(__myInstance, __params, __env);
      return ((Integer)__res).intValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public String getNewCityClassificationForHRA(String cityClass)
  {
    Object[] __params = new Object[1];
    __params[0] = cityClass;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = getNewCityClassificationForHRA_Method.invoke(__myInstance, __params, __env);
      return (String)__res;
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calcVal(double value, Map input)
  {
    Object[] __params = new Object[2];
    __params[0] = new Double(value);
    __params[1] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calcVal_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  public double calculateFPA(Map input)
  {
    Object[] __params = new Object[1];
    __params[0] = input;
    try
    {
      Object __myInstance = this.__instance;
      Object __res = calculateFPA_Method.invoke(__myInstance, __params, __env);
      return ((Double)__res).doubleValue();
    }
    catch (Throwable t)
    {
      Log.error("Java Wrapper execution error:", t);
      throw RuntimeExceptionWrapper.wrap(t);
    }
  }
  
  static boolean __initialized = false;
  
  public static void reset()
  {
    __initialized = false;
  }
  
  public Object getInstance()
  {
    return this.__instance;
  }
  
  public IOpenClass getOpenClass()
  {
    return __class;
  }
  
  public CompiledOpenClass getCompiledOpenClass()
  {
    return __compiledClass;
  }
  
  public synchronized void reload()
  {
    reset();__init();this.__instance = __class.newInstance(__env);
  }
  
  protected static synchronized void __init()
  {
    if (__initialized) {
      return;
    }
    UserContext ucxt = new UserContext(Thread.currentThread().getContextClassLoader(), __userHome);
    OpenClassJavaWrapper wrapper = OpenClassJavaWrapper.createWrapper(__openlName, ucxt, __src);
    __compiledClass = wrapper.getCompiledClass();
    __class = wrapper.getOpenClassWithErrors();
    __env = wrapper.getEnv();
    
    this_Field = __class.getField("this");
    calculateForce1Incentive100_Method = __class.getMatchingMethod("calculateForce1Incentive100", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateCLA_Method = __class.getMatchingMethod("calculateCLA", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateTACentral_Method = __class.getMatchingMethod("calculateTACentral", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateLeaveDays_Method = __class.getMatchingMethod("calculateLeaveDays", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Integer.TYPE), 
      JavaOpenClass.getOpenClass(Integer.TYPE) });
    CheckCPFFlag_Method = __class.getMatchingMethod("CheckCPFFlag", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Date.class) });
    calculateHA_Method = __class.getMatchingMethod("calculateHA", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateGIS_Method = __class.getMatchingMethod("calculateGIS", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    CheckMAHAGISFlag_Method = __class.getMatchingMethod("CheckMAHAGISFlag", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Date.class) });
    getCurrentAvailBal_Method = __class.getMatchingMethod("getCurrentAvailBal", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Integer.TYPE), 
      JavaOpenClass.getOpenClass(Calendar.class), 
      JavaOpenClass.getOpenClass(Integer.TYPE), 
      JavaOpenClass.getOpenClass(Integer.TYPE) });
    calculateLeaveExcess_Method = __class.getMatchingMethod("calculateLeaveExcess", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Integer.TYPE), 
      JavaOpenClass.getOpenClass(Integer.TYPE), 
      JavaOpenClass.getOpenClass(Integer.TYPE), 
      JavaOpenClass.getOpenClass(Integer.TYPE) });
    isBoardCorporation_Method = __class.getMatchingMethod("isBoardCorporation", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Long.TYPE) });
    calculateForce1Incentive25_Method = __class.getMatchingMethod("calculateForce1Incentive25", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    getValue_Method = __class.getMatchingMethod("getValue", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Double.TYPE), 
      JavaOpenClass.getOpenClass(Map.class) });
    calculateDCPS_Method = __class.getMatchingMethod("calculateDCPS", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateATS30_Method = __class.getMatchingMethod("calculateATS30", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateGISForIFS_Method = __class.getMatchingMethod("calculateGISForIFS", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calcCPFval_Method = __class.getMatchingMethod("calcCPFval", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Double.TYPE), 
      JavaOpenClass.getOpenClass(Map.class) });
    newCityClassification_Method = __class.getMatchingMethod("newCityClassification", new IOpenClass[] {
      JavaOpenClass.getOpenClass(String.class) });
    getDaysInMonth_Method = __class.getMatchingMethod("getDaysInMonth", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Integer.TYPE), 
      JavaOpenClass.getOpenClass(Integer.TYPE) });
    calculatePT_Method = __class.getMatchingMethod("calculatePT", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateATS50_Method = __class.getMatchingMethod("calculateATS50", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateCentralDA_Method = __class.getMatchingMethod("calculateCentralDA", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateGISForIAS_Method = __class.getMatchingMethod("calculateGISForIAS", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateHRR_Method = __class.getMatchingMethod("calculateHRR", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateTA_Method = __class.getMatchingMethod("calculateTA", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateDA_Method = __class.getMatchingMethod("calculateDA", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    getTAValue_Method = __class.getMatchingMethod("getTAValue", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Double.TYPE), 
      JavaOpenClass.getOpenClass(Map.class) });
    calculateCGEGIS_Method = __class.getMatchingMethod("calculateCGEGIS", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculateHRA_Method = __class.getMatchingMethod("calculateHRA", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calculatePG_Method = __class.getMatchingMethod("calculatePG", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    calcPFVal_Method = __class.getMatchingMethod("calcPFVal", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Double.TYPE), 
      JavaOpenClass.getOpenClass(Map.class) });
    calculateGISForIPS_Method = __class.getMatchingMethod("calculateGISForIPS", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    getYearFromDate_Method = __class.getMatchingMethod("getYearFromDate", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Date.class) });
    getCode_Method = __class.getMatchingMethod("getCode", new IOpenClass[] {
      JavaOpenClass.getOpenClass(String.class) });
    calculateLeaveRange_Method = __class.getMatchingMethod("calculateLeaveRange", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Integer.TYPE), 
      JavaOpenClass.getOpenClass(Integer.TYPE) });
    getPTValue_Method = __class.getMatchingMethod("getPTValue", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Double.TYPE), 
      JavaOpenClass.getOpenClass(Map.class) });
    calculatePF_Method = __class.getMatchingMethod("calculatePF", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    getDateDiff_Method = __class.getMatchingMethod("getDateDiff", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Date.class), 
      JavaOpenClass.getOpenClass(Date.class) });
    isdirectRecruit_Method = __class.getMatchingMethod("isdirectRecruit", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Long.TYPE) });
    getNewCityClassificationForHRA_Method = __class.getMatchingMethod("getNewCityClassificationForHRA", new IOpenClass[] {
      JavaOpenClass.getOpenClass(String.class) });
    calcVal_Method = __class.getMatchingMethod("calcVal", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Double.TYPE), 
      JavaOpenClass.getOpenClass(Map.class) });
    calculateFPA_Method = __class.getMatchingMethod("calculateFPA", new IOpenClass[] {
      JavaOpenClass.getOpenClass(Map.class) });
    
    __initialized = true;
  }
}
