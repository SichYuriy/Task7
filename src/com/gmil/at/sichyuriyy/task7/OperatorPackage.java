package com.gmil.at.sichyuriyy.task7;

public class OperatorPackage {
	String name;
	int type;
	int rateCall;
	int rateCallOtherOperator;
	int rateSms;
	int rateNetwork;
	int periodPayment;
	long period;

	OperatorPackage(String name, int rateCall, int rateCallOtherOperator,
			int rateSms, int rateNetwork) {
		this.type = 1;
		this.name = name;
		this.rateCall = rateCall;
		this.rateCallOtherOperator = rateCallOtherOperator;
		this.rateSms = rateSms;
		this.rateNetwork = rateNetwork;
	}

	OperatorPackage(String name, int periodPayment, long period) {
		this.type = 2;
		this.name = name;
		this.periodPayment = periodPayment;
		this.period = period;
	}
	
	public int getType() {
		return type;
	}
	
	public int getRateSms() {
		return rateSms;
	}
	public int getRateCall() {
		if(type == 1)
			return rateCall;
		else 
			return 0;
	}
	
	public int getRateCallOtherOperator() {
		if(type == 1)
			return rateCallOtherOperator;
		else
			return 0;
	}
}
