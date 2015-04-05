package com.gmil.at.sichyuriyy.task7;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		World earth = new World();
		earth.addBase("Kiev1", 1, 1, new ArrayList<Integer>(), 1);
		earth.addBase("Kiev2", 2, 2, new ArrayList<Integer>(), 1);
		ArrayList<Integer> bases = new ArrayList<Integer>();
		bases.add(0);
		bases.add(1);
		earth.addOperator("Operator1" , bases);
		earth.getOperator(0).addDefaultPackage(50, 110, 75, 15);
		
		earth.addUser("Yuriy", 671841013, 0, 0, 500, 0, 0);
		earth.addUser("Sergiy", 671841014, 0, 0, 100, 0, 0);
		earth.connectUserIndex(0);
		earth.connectUserIndex(1);
		earth.getUser("Yuriy").chekBalance();
		earth.getUser("Sergiy").chekBalance();
		sleep(1000);
		earth.tryCall(earth.getUser("Yuriy"), 671841014);
		sleep(100);
		earth.getUser("Yuriy").stopTalking();
		earth.getUser("Yuriy").chekBalance();
		earth.getUser("Sergiy").chekBalance();
		earth.disconnectUser(earth.getUser("Sergiy"));
		
		earth.sendSms(earth.getUser("Yuriy"), 671841014, "Hello!");
		sleep(2000);
		earth.connectUser(earth.getUser("Sergiy"));
		sleep(2000);
		earth.getUser("Sergiy").increaseBalance(25);
		sleep(500);
		earth.sendSms(earth.getUser(671841014L), 671841013L, "sms1");
		sleep(1000);
		earth.sendSms(earth.getUser(671841014L), 671841013L, "sms2");
		earth.tryCall(earth.getUser(671841013L), 671841014L);
		sleep(5000);
		earth.getUser("Yuriy").increaseBalance(100);
		
		earth.disconnectUser(earth.getUser("Yuriy"));
		sleep(500);
		earth.disconnectUser(earth.getUser("Sergiy"));
		earth.addUser("Vova", 671841015, 0, 0, 100, 3, 3);
		
		earth.connectUser(earth.getUser("Vova"));
		sleep(500);
		earth.connectUser(earth.getUser("Sergiy"));
		sleep(500);
		earth.connectUser(earth.getUser("Yuriy"));
		

	}
	
	
	 public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {
			System.out.println(e);
		}
	}

}


