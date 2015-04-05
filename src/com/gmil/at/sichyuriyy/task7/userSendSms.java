package com.gmil.at.sichyuriyy.task7;

public class userSendSms implements Runnable {
	User user1, user2;
	String sms;

	public userSendSms(User user1, User user2, String sms) {
		this.user1 = user1;
		this.user2 = user2;
		this.sms = sms;
	}

	public void run() {
		while (!user2.isActive())
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		System.out.println("User " + user2.getName()
				+ " received sms from the user " + user1.getName() + "; sms: "
				+ sms);
	}

}
