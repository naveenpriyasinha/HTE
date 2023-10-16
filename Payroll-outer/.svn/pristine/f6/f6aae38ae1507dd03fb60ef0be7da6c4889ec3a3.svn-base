package com.tcs.sgv.common.util;

public class EnglishDecimalFormat 
{
  public EnglishDecimalFormat()
  {
  }


  private static final String[] majorNames = {
    "",
    "Thousand",
    "Lac",
    "Crore",
    "Trillion",
    "Quadrillion",
    "Quintillion"
    };

  private static final String[] tensNames = {
    "",
    "Ten",
    "Twenty",
    "Thirty",
    "Fourty",
    "Fifty",
    "Sixty",
    "Seventy",
    "Eighty",
    "Ninety"
    };

  private static final String[] numNames = {
    "",
    "One",
    "Two",
    "Three",
    "Four",
    "Five",
    "Six",
    "Seven",
    "Eight",
    "Nine",
    "Ten",
    "Eleven",
    "Twelve",
    "Thirteen",
    "Fourteen",
    "Fifteen",
    "Sixteen",
    "Seventeen",
    "Eighteen",
    "Nineteen"
    };

 private static String convertLessThanOneThousand(int number) {
    String soFar;

    if (number % 100 < 20){
        soFar = numNames[number % 100];
        number /= 100;
       }
    else {
        soFar = numNames[number % 10];
        number /= 10;

        soFar = tensNames[number % 10] + soFar;
        number /= 10;
       }
    if (number == 0) return soFar;
    return numNames[number] + "Hundred" + soFar;
}
 public static String convert(long number) {
	 String s ="";
	 
	 if(number  > 10000000 )
	 {
		s = convert1(number/10000000) + " Crore"; 
		if (number%100000000 != 0)
			s+=convert1(number%10000000);  
	 }else
		s = convert1(number); 
	 return s;
 }
public static String convert1(long number) {
    /* special case */
    if (number == 0) { return "zero"; }

    String prefix = "";

    if (number < 0) {
        number = -number;
        prefix = "negative";
      }

    String soFar = "";
    int place = 0;
    
    int n = (int)number % 1000;
    if (n != 0)
    {
       String s = convertLessThanOneThousand(n);
       soFar = s + majorNames[place] + soFar;
     }
    place++;
    number /= 1000;
    
    while (number > 0)
    {
    	n = (int)number % 100;
      if (n != 0)
      {
         String s = convertLessThanOneThousand(n);
         soFar = s + majorNames[place] + soFar;
      }
      place++;
      number /= 100;
     } 

    return (prefix + soFar).trim();
  }
	

    public static void main(String a[])
	{
			//System.out.println(convert(2000000  ));
		
	}
}

