package com.gmil.at.sichyuriyy.task7;

import java.util.ArrayList;

public class BaseStation {
	String name;
	int[] position = new int[2];
	ArrayList<Integer> operatorId = new ArrayList<Integer>();
	int maxUsers;
	int activeUsers = 0;

	public BaseStation(String name, int positionX, int positionY,
			ArrayList<Integer> operators, int maxUsers) {
		this.name = name;
		position[0] = positionX;
		position[1] = positionY;
		operatorId.addAll(operators);
		this.maxUsers = maxUsers;
	}
	
	public void addOperator(int operator){
		operatorId.add(operator);
	}
	
	public boolean isFree(){
		if(activeUsers < maxUsers)
			return true;
		else 
			return false;
	}
	
	public String getName() {
		return name;
	}
	public void connectUser(){
		activeUsers++;
	}
	
	public void disconnectUser() {
		activeUsers--;
	}
}
