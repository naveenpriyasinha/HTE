package com.tcs.sgv.eis.util;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tcs.sgv.eis.service.GenerateBillService;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;

class sizeOf {
	MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
	List<MemoryPoolMXBean> mempoolsmbeans = ManagementFactory.getMemoryPoolMXBeans();
	List<GarbageCollectorMXBean> gcmbeans = ManagementFactory.getGarbageCollectorMXBeans();
	Set loadedClass = new HashSet();
	Map map = new HashMap();
	public void getSizeOfObject(Object o)
	{
		long objectSize=0;
		Class c = o.getClass();
		loadedClass.add(java.lang.Class.class);
		System.out.println("Class name is " + c.getCanonicalName());
		Constructor constructors[]  = c.getDeclaredConstructors();
		for (int i =0 ;i<constructors.length;i++)
		{
			Constructor c1 = constructors[i];
			System.out.println("Constructors are " + c1.getName());
		}
		
		Field arrField[] = o.getClass().getDeclaredFields();
		System.out.println("Field array size is " + arrField.length);
		for (int i =0 ;i<arrField.length;i++)
		{
			Field f = arrField[i];
			
			System.out.println("ankit " + f.getType().toString() + " " + f.getName() + "\nHeap Usage is " + memorymbean.getHeapMemoryUsage());
			if(!loadedClass.contains(f.getType())) 
			{
				if(f.getType()==boolean.class)
					objectSize += 1;
				else if(f.getType()==byte.class)objectSize += 8;
				else if(f.getType()==char.class)objectSize += 8;			
				else if(f.getType()==short.class)objectSize += 16;
				else if(f.getType()==int.class)objectSize += 32;
				else if(f.getType()==float.class)objectSize += 32;
				else if(f.getType()==long.class)objectSize += 64;
				else if(f.getType()==double.class)objectSize += 64;
				
				else /*if(f instanceof Object)*/
				{
					if(!f.getType().toString().startsWith("class java"))
					                loadedClass.add(f.getType());
					map.put("objectSize", objectSize);
					map.put("loadedClass", loadedClass);
					
					map = calculateObject(f.getType(),objectSize,loadedClass,map);
					objectSize = Long.parseLong(map.get("objectSize").toString());
					loadedClass = (Set)map.get("loadedClass");
					System.out.println("Object Size is before continue " + objectSize);
					continue;
				}
				//loadedClass.add(f.getType());
				map.put("objectSize", objectSize);
				map.put("loadedClass", loadedClass);
				
			}
			System.out.println("Object Size is abc " + objectSize);
		}
		/*Method methods[] = c.getDeclaredMethods();
		for (int i =0 ;i<methods.length;i++)
		{
			Method method = methods[i];
			 
		}*/
		System.out.println("Object Size in last is " + objectSize);
		
	}
	
	public Map calculateObject(Class c, long objectSize,Set loadedClass,Map map)
	{
		
		objectSize = Long.parseLong(map.get("objectSize").toString());
		loadedClass = (Set)map.get("loadedClass");
		
		System.out.println("Class name in calculateObject is " + c.getCanonicalName());
		
		Field arrField[] = c.getDeclaredFields();
		
		System.out.println("Field array size is in " +  c.getCanonicalName() + " " +  arrField.length);
		for (int i =0 ;i<arrField.length;i++)
		{
			Field f = arrField[i];
			System.out.println(f.getType() + " " + f.getName());
			if(!loadedClass.contains(f.getType())) 
			{
				if(f.getType()==boolean.class)objectSize += 1;
				else if(f.getType()==byte.class)objectSize += 8;
				else if(f.getType()==char.class)objectSize += 8;			
				else if(f.getType()==short.class)objectSize += 16;
				else if(f.getType()==int.class)objectSize += 32;
				else if(f.getType()==float.class)objectSize += 32;
				else if(f.getType()==long.class)objectSize += 64;
				else if(f.getType()==double.class)objectSize += 64;
				else /*if(f instanceof Object)*/
				{
					if(!f.getType().toString().startsWith("class java"))
					                loadedClass.add(f.getType());
					map.put("objectSize", objectSize);
					map.put("loadedClass", loadedClass);
					
					map = calculateObject(f.getType(),objectSize,loadedClass,map);
					objectSize = Long.parseLong(map.get("objectSize").toString());
					loadedClass = (Set)map.get("loadedClass");
					System.out.println("Object Size is before continue " + objectSize);
					continue;
				}
				//loadedClass.add(f.getType());
				map.put("objectSize", objectSize);
				map.put("loadedClass", loadedClass);
				System.out.println("Object Size is " + objectSize);
			}
			else
			{
				System.out.println("\n " + f.getType() + " Class is alreay loaded.");
			}
		
	  }
		return map;
	}

}

public class Temp
{
	int i;
	String s = "Hello";
	double d;
	HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
	Temp() { }
	Temp(String s) { }
	public void disp1() { }
	public static void main(String a[])
	{		
		sizeOf s = new sizeOf();
		s.getSizeOfObject(new GenerateBillService());
	}
	
}
