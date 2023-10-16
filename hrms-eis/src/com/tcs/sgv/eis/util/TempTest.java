package com.tcs.sgv.eis.util;

class TempTest
{
  public static void main(String a[])
  {
	  int k=0;
	  for (int i=0;i<=10;i++)
	  {
		  System.out.println("i--->");;
		  if(i==5)
		  {
			 k=disp1(k);
		  }
	  }
  }
  
  public static int disp1(int k)
  {
	  for(int i=0;i<=10;i++)
	  {
		  System.out.println("i in next--." + i);
		  if(i==5)
			  k=disp1(k);
	  }
	  return k;
  }
}