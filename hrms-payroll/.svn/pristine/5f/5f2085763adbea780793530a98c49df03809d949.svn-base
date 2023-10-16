/*
 * This class has been generated. Do not change it, if you need to modify functionality - subclass it
 */

package com.tcs.sgv.allowance.service;

import org.openl.util.Log;
import org.openl.util.RuntimeExceptionWrapper;
import org.openl.types.java.JavaOpenClass;
import org.openl.types.IOpenClass;
import org.openl.conf.UserContext;
import org.openl.impl.OpenClassJavaWrapper;
public class IncomeTaxRules implements org.openl.main.OpenLWrapper
{
  Object __instance;

  public static org.openl.vm.IRuntimeEnv __env;

  public static org.openl.types.IOpenClass __class;

  public static org.openl.CompiledOpenClass __compiledClass;

  public static String __openlName = "org.openl.xls";

  public static String __src = "resources/payroll/rules/IncomeTaxRules.xls";

  public static String __userHome = ".";

  public IncomeTaxRules(){
    this(false);
  }

  public IncomeTaxRules(boolean ignoreErrors){
    __init();
    if (!ignoreErrors) __compiledClass.throwErrorExceptionsIfAny();
    __instance = __class.newInstance(__env);
  }



  static org.openl.types.IOpenField this_Field;

  public org.openl.types.impl.DynamicObject getThis()
  {
   Object __res = this_Field.get(__instance, __env);
   return (org.openl.types.impl.DynamicObject)__res;
  }


  public void setThis(org.openl.types.impl.DynamicObject __var)
  {
   this_Field.set(__instance, __var, __env);
  }



  static org.openl.types.IOpenMethod calculateIncomeTax_Method;
  public double calculateIncomeTax(double salary, int level, java.lang.String gender)  {
    Object[] __params = new Object[3];
    __params[0] = new Double(salary);
    __params[1] = new Integer(level);
    __params[2] = gender;
    try
    {
    Object __myInstance = __instance;
    Object __res = calculateIncomeTax_Method.invoke(__myInstance, __params, __env);
   return ((Double)__res).doubleValue();  }
  catch(Throwable t)
  {
    Log.error("Java Wrapper execution error:", t);
    throw RuntimeExceptionWrapper.wrap(t);
  }

  }


  static org.openl.types.IOpenMethod calculateEduCess_Method;
  public double calculateEduCess(double amount)  {
    Object[] __params = new Object[1];
    __params[0] = new Double(amount);
    try
    {
    Object __myInstance = __instance;
    Object __res = calculateEduCess_Method.invoke(__myInstance, __params, __env);
   return ((Double)__res).doubleValue();  }
  catch(Throwable t)
  {
    Log.error("Java Wrapper execution error:", t);
    throw RuntimeExceptionWrapper.wrap(t);
  }

  }


  static org.openl.types.IOpenMethod calculateHRAExemption_Method;
  public double calculateHRAExemption(double basic_da, double hra)  {
    Object[] __params = new Object[2];
    __params[0] = new Double(basic_da);
    __params[1] = new Double(hra);
    try
    {
    Object __myInstance = __instance;
    Object __res = calculateHRAExemption_Method.invoke(__myInstance, __params, __env);
   return ((Double)__res).doubleValue();  }
  catch(Throwable t)
  {
    Log.error("Java Wrapper execution error:", t);
    throw RuntimeExceptionWrapper.wrap(t);
  }

  }


  static org.openl.types.IOpenMethod calculateSurcharge_Method;
  public double calculateSurcharge(double amount)  {
    Object[] __params = new Object[1];
    __params[0] = new Double(amount);
    try
    {
    Object __myInstance = __instance;
    Object __res = calculateSurcharge_Method.invoke(__myInstance, __params, __env);
   return ((Double)__res).doubleValue();  }
  catch(Throwable t)
  {
    Log.error("Java Wrapper execution error:", t);
    throw RuntimeExceptionWrapper.wrap(t);
  }

  }


  static org.openl.types.IOpenMethod calculateSurchargeAmt_Method;
  public double calculateSurchargeAmt(double amount)  {
    Object[] __params = new Object[1];
    __params[0] = new Double(amount);
    try
    {
    Object __myInstance = __instance;
    Object __res = calculateSurchargeAmt_Method.invoke(__myInstance, __params, __env);
   return ((Double)__res).doubleValue();  }
  catch(Throwable t)
  {
    Log.error("Java Wrapper execution error:", t);
    throw RuntimeExceptionWrapper.wrap(t);
  }

  }


  static org.openl.types.IOpenMethod getInvestmentAmount_Method;
  public double getInvestmentAmount(double amount)  {
    Object[] __params = new Object[1];
    __params[0] = new Double(amount);
    try
    {
    Object __myInstance = __instance;
    Object __res = getInvestmentAmount_Method.invoke(__myInstance, __params, __env);
   return ((Double)__res).doubleValue();  }
  catch(Throwable t)
  {
    Log.error("Java Wrapper execution error:", t);
    throw RuntimeExceptionWrapper.wrap(t);
  }

  }


  static org.openl.types.IOpenMethod calculateTax_Method;
  public double calculateTax(double salary, int level, java.lang.String gender)  {
    Object[] __params = new Object[3];
    __params[0] = new Double(salary);
    __params[1] = new Integer(level);
    __params[2] = gender;
    try
    {
    Object __myInstance = __instance;
    Object __res = calculateTax_Method.invoke(__myInstance, __params, __env);
   return ((Double)__res).doubleValue();  }
  catch(Throwable t)
  {
    Log.error("Java Wrapper execution error:", t);
    throw RuntimeExceptionWrapper.wrap(t);
  }

  }
  static boolean __initialized = false;

  static public void reset(){__initialized = false;}

public Object getInstance(){return __instance;}

public IOpenClass getOpenClass(){return __class;}

public org.openl.CompiledOpenClass getCompiledOpenClass(){return __compiledClass;}

public synchronized void  reload(){reset();__init();__instance = __class.newInstance(__env);}

  static synchronized protected void __init()
  {
    if (__initialized)
      return;

    UserContext ucxt = new UserContext(Thread.currentThread().getContextClassLoader(), __userHome);
    OpenClassJavaWrapper wrapper = OpenClassJavaWrapper.createWrapper(__openlName, ucxt , __src);
    __compiledClass = wrapper.getCompiledClass();
    __class = wrapper.getOpenClassWithErrors();
    __env = wrapper.getEnv();

    this_Field = __class.getField("this");
    calculateIncomeTax_Method = __class.getMatchingMethod("calculateIncomeTax", new IOpenClass[] {
      JavaOpenClass.getOpenClass(double.class),
      JavaOpenClass.getOpenClass(int.class),
      JavaOpenClass.getOpenClass(java.lang.String.class)});
    calculateEduCess_Method = __class.getMatchingMethod("calculateEduCess", new IOpenClass[] {
      JavaOpenClass.getOpenClass(double.class)});
    calculateHRAExemption_Method = __class.getMatchingMethod("calculateHRAExemption", new IOpenClass[] {
      JavaOpenClass.getOpenClass(double.class),
      JavaOpenClass.getOpenClass(double.class)});
    calculateSurcharge_Method = __class.getMatchingMethod("calculateSurcharge", new IOpenClass[] {
      JavaOpenClass.getOpenClass(double.class)});
    calculateSurchargeAmt_Method = __class.getMatchingMethod("calculateSurchargeAmt", new IOpenClass[] {
      JavaOpenClass.getOpenClass(double.class)});
    getInvestmentAmount_Method = __class.getMatchingMethod("getInvestmentAmount", new IOpenClass[] {
      JavaOpenClass.getOpenClass(double.class)});
    calculateTax_Method = __class.getMatchingMethod("calculateTax", new IOpenClass[] {
      JavaOpenClass.getOpenClass(double.class),
      JavaOpenClass.getOpenClass(int.class),
      JavaOpenClass.getOpenClass(java.lang.String.class)});

    __initialized=true;
  }
}