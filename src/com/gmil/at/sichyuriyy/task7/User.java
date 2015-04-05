package com.gmil.at.sichyuriyy.task7;

public class User {
	String name;
	User call = null;
	long phoneNumber;
	int operatorId;
	int packageId;
	int money;
	boolean active = false;
	boolean busy = false;
	int[] position = new int[2];
	int station;

	public User(String name, int phoneNumber, int operatorId, int packageId,
			int money, int positionX, int positionY) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.operatorId = operatorId;
		this.packageId = packageId;
		this.money = money;
		this.position = new int[2];
		this.position[0] = positionX;
		this.position[1] = positionY;

	}
	
	

	public boolean isBusy() {
		if (busy)
			return true;
		else
			return false;
	}

	public boolean isActive() {
		if (active)
			return true;
		else
			return false;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setStaion(int station) {
		this.station = station;
	}

	public double getRange(BaseStation station) {
		return Math.sqrt((this.position[0] - station.position[0])
				* (this.position[0] - station.position[0])
				+ (this.position[1] - station.position[1])
				* (this.position[1] - station.position[1]));
	}
	
	public int getOperator() {
		return operatorId;
	}
	
	public int getPackage() {
		return packageId;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void pay(int sum) {
		if(money - sum > 0)
			money-=sum;
		else money = 0;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	
	public void setCall(User user) {
		this.call = user;
	}
	
	public User getCall() {
		return this.call;
	}
	
	public boolean isTalking(User user) {
		if(user == null) return false;
		if(this.call == user)
			return true;
		else
			return false;
	}
	
	public void increaseBalance(int sum) {
		System.out.println("User " + name + " increased balance: " + money + "->" + (money+sum));
		money += sum;
	}
	
	public void stopTalking(){
		if(!this.isActive()) {
			System.out.println("#error " + this.getName() + " is offline");
			return;
		}
		if(!this.isBusy()) {
			System.out.println("#error " + this.getName() + " is not talking");
			return;
		}
		this.setCall(null);
		Main.sleep(1000);
	}
	
	public int chekBalance() {
		System.out.println(name + "->Balance: " + money);
		return money;
	}
	
	public void setStation(int station) {
		this.station = station;
	}
	
	public int getStation() {
		return station;
	}
}
