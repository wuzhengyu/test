package com.aozhi;

public class Test  extends Thread {
public static void main(String[] args) {
	 int  x=6;
     System.out.println("value  is  "+ ((x>6) ? 99.9 :9));
}

@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
 class Test2 implements Runnable{

	 @Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}