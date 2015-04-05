package com.gmil.at.sichyuriyy.task7;

public class TryCall implements Runnable {
	User user1;
	User user2;
	int rateCall;

	public TryCall(User user1, User user2, int rateCall) {
		this.user1 = user1;
		this.user2 = user2;
		this.rateCall = rateCall;
	}

	public void run() {
		while (user1.isBusy() && user2.isBusy() && user1.isTalking(user2)
				&& user2.isTalking(user1) && user1.getMoney() > 0) {

			try {
				user1.pay(rateCall);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}

		if (!user1.isTalking(user2)) {
			System.out.println(user1.getName() + " stoped talk with user "
					+ user2.getName());
		} else if (!user2.isTalking(user1)) {
			System.out.println(user2.getName() + " stoped talk with user "
					+ user1.getName());
		} else {
			System.out.println(user1.getName()
					+ " have no money, conversation is over :(");
		}

		user1.setBusy(false);
		user2.setBusy(false);
		user1.setCall(null);
		user2.setCall(null);
	}
}
