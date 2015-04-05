package com.gmil.at.sichyuriyy.task7;

import java.util.ArrayList;

public class World {
	ArrayList<Operator> operators = new ArrayList<Operator>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<BaseStation> stations = new ArrayList<BaseStation>();

	public void addOperator(String name, ArrayList<Integer> baseStations) {
		operators.add(new Operator(name, baseStations));
		for (Integer it : baseStations) {
			stations.get(it).addOperator(operators.size() - 1);
		}

	}

	public void addUser(String name, int phoneNumber, int operatorId,
			int packageId, int money, int positionX, int positionY) {
		users.add(new User(name, phoneNumber, operatorId, packageId, money,
				positionX, positionY));
		operators.get(operatorId).addPhone(phoneNumber);
	}

	public void addBase(String name, int positionX, int positionY,
			ArrayList<Integer> operators, int maxUsers) {
		stations.add(new BaseStation(name, positionX, positionY, operators,
				maxUsers));
		for (Integer it : operators) {
			this.operators.get(it).addBase(stations.size() - 1);
		}
	}

	
	public void connectUser(User user) {
		BaseStation station = null;
		BaseStation nearestStation = null;
		if (user.isActive()) {
			System.out.println("Fail: User " + user.getName()
					+ "is already connected");
			return;
		} else {
			double minRange = 999999999;
			for (int j = 0; j < operators.get(user.operatorId).bases.size(); j++) {
				station = stations.get(operators.get(user.operatorId).bases
						.get(j));
				if (user.getRange(station) < minRange && station.isFree()) {
					nearestStation = station;
					minRange = user.getRange(station);
				}

			}

			if (nearestStation == null) {
				System.out.println("User " + user.getName() +": fail: can not find free mobile tower");
				return;
			} else {
				nearestStation.connectUser();
				System.out.println("User " + user.getName()
						+ " successfully connected; tower: "
						+ nearestStation.getName());
				user.setActive(true);
				user.setStation(this.getIndexOfStation(nearestStation));
				return;
			}

		}
	}
	public void connectUserIndex(int index) {
		User user = users.get(index);
		BaseStation station = null;
		BaseStation nearestStation = null;
		if (user.isActive()) {
			System.out.println("Fail: User " + user.getName()
					+ "is already connected");
			return;
		} else {
			double minRange = 999999999;
			for (int j = 0; j < operators.get(user.operatorId).bases.size(); j++) {
				station = stations.get(operators.get(user.operatorId).bases
						.get(j));
				if (user.getRange(station) < minRange && station.isFree()) {
					nearestStation = station;
					minRange = user.getRange(station);
				}

			}

			if (nearestStation == null) {
				System.out.println("Fail: can not find free mobile tower");
				return;
			} else {
				nearestStation.connectUser();
				System.out.println("User " + user.getName()
						+ " successfully connected; tower: "
						+ nearestStation.getName());
				user.setActive(true);
				user.setStation(this.getIndexOfStation(nearestStation));
				return;
			}

		}
	}

	public void connectUser(int phoneNumber) {
		User user;
		BaseStation station = null;
		BaseStation nearestStation = null;
		for (int i = 0; i < users.size(); i++) {
			user = users.get(i);

			if (user.getPhoneNumber() == phoneNumber) {

				if (user.isActive()) {
					System.out.println("Fail: User " + user.getName()
							+ "is already connected");
					return;
				} else {
					double minRange = 999999999;
					for (int j = 0; j < operators.get(user.operatorId).bases
							.size(); j++) {
						station = stations
								.get(operators.get(user.operatorId).bases
										.get(j));
						if (user.getRange(station) < minRange
								&& station.isFree()) {
							nearestStation = station;
							minRange = user.getRange(station);
						}

					}

					if (nearestStation == null) {
						System.out
								.println("Fail: can not find free mobile tower");
						return;
					} else {
						nearestStation.connectUser();
						System.out.println("User " + user.getName()
								+ " successfully connected; tower: "
								+ nearestStation.getName());
						user.setActive(true);
						user.setStation(this.getIndexOfStation(nearestStation));
						return;
					}

				}
			}

		}
		System.out.println("Fail: phone number " + phoneNumber
				+ " is not valid");

	}

	public Operator getOperator(User user) {
		return operators.get(user.getOperator());
	}

	public Operator getOperator(int index) {
		return operators.get(index);
	}

	public Operator getOperator(String name) {
		for (int i = 0; i < operators.size(); i++)
			if (operators.get(i).getName() == name)
				return operators.get(i);
		return null;
	}

	public User getUser(int index) {
		return users.get(index);
	}

	public User getUser(String name) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).getName() == name)
				return users.get(i);
		return null;
	}

	public User getUser(long phoneNumber) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).getPhoneNumber() == phoneNumber)
				return users.get(i);
		return null;
	}

	public OperatorPackage getPackage(User user) {
		return operators.get(user.getOperator()).getPackage(user.getPackage());
	}

	public void sendSms(User user, long phoneNumber, String sms) {
		if (user == null) {
			System.out.println("#error: user does not exist");
			return;
		}
		User user2 = null;
		if (this.getPackage(user).getType() == 1
				&& this.getPackage(user).getRateSms() > user.getMoney()) {
			System.out.println("Fail: User " + user.getName()
					+ " have not money to send sms");
			return;
		} else {
			user2 = this.getUser(phoneNumber);
			if (user2 == null) {
				System.out.println(user.getName()
						+ " sending sms Fail: number " + phoneNumber
						+ "is not valid");
				return;
			}
			user.pay(this.getPackage(user).getRateSms());
			System.out.println("User " + user.getName()
					+ " send sms to the user " + user2.getName() + "; sms: "
					+ sms);
			Thread sendSms = new Thread(new userSendSms(user, user2, sms));
			sendSms.start();
		}
	}

	public void tryCall(User user, long phoneNumber) {

		if (user == null) {
			System.out.println("#error: user does not exist");
			return;
		}
		user.setBusy(true);
		User user2 = this.getUser(phoneNumber);
		if (user2 == null) {
			System.out.println(user.getName() + " call-fail: number "
					+ phoneNumber + " is not valid");
			user.setBusy(false);
			return;
		}
		if (!user2.isActive()) {
			System.out.println(user.getName() + "call-faol: user "
					+ user2.getName() + " is offline");
			user.setBusy(false);
			return;
		}
		if (user2.isBusy()) {
			System.out.println(user.getName() + "call-faol: user "
					+ user2.getName() + " is busy");
			user.setBusy(false);
			return;
		} else {
			user2.setBusy(true);
			user.setCall(user2);
			user2.setCall(user);
			System.out.println(user.getName() + " successfully called to the user "
					+ user2.getName());
			int rateCall;
			if(this.getOperator(user) == this.getOperator(user2))
				rateCall = this.getPackage(user).getRateCall();
			else
				rateCall = this.getPackage(user).getRateCallOtherOperator();
			
			Thread call = new Thread(new TryCall(user, user2, rateCall));
			call.start();
		}

	}
	
	public void disconnectUser(User user) {
		System.out.println("User " + user.getName() + " is disconnected (offline)");
		user.setActive(false);
		user.setBusy(false);
		user.setCall(null);
		stations.get(user.getStation()).disconnectUser();
		
		Main.sleep(1000);
		
	}
	
	public int getIndexOfStation(BaseStation station) {
		if(station == null) {
			System.out.println("#error");
			return -1;
		}
		for(int i=0; i < stations.size(); i++)
			if(stations.get(i) == station)
				return i;
		return -1;
	}
}
