package com.aozhi;
public class MyFor {
   static boolean out(char input){
	   System.out.print(input);
	   return true;
   }
   public static void main(String arg[]){
	   int i=0;
	   for(out('A');out('B')&&i<2;out('C')){
		   i++;
		   out('D');		   
	   }	   
   }	
}