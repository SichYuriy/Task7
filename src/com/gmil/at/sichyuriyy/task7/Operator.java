package com.gmil.at.sichyuriyy.task7;

import java.util.ArrayList;

public class Operator {
	String name;
	ArrayList<Integer> phoneNumbers = new ArrayList<Integer>();
	ArrayList<OperatorPackage> packages = new ArrayList<OperatorPackage>();
	ArrayList<Integer> bases = new ArrayList<Integer>();

	public Operator(String name) {
		this.name = name;
		packages = new ArrayList<OperatorPackage>();
		bases = new ArrayList<Integer>();
	}

	public Operator(String name, ArrayList<Integer> bases) {
		this.name = name;
		this.bases = bases;
		packages = new ArrayList<OperatorPackage>();

	}

	public void addPackage(String name, int rateCall,
			int rateCallOtherOperator, int rateSms, int rateNetwork) {
		packages.add(new OperatorPackage(name, rateCall, rateCallOtherOperator,
				rateSms, rateNetwork));
	}

	public void addPackage(String name, int periodPayment, long period) {
		packages.add(new OperatorPackage(name, periodPayment, period));
	}

	public void addBase(Integer base) {
		bases.add(base);
	}

	public void addPhone(Integer phoneNumber) {
		phoneNumbers.add(phoneNumber);
	}

	public void addDefaultPackage(int rateCall, int rateCallOtherOperator,
			int rateSms, int rateNetwork) {
		packages.add(new OperatorPackage("Default", rateCall,
				rateCallOtherOperator, rateSms, rateNetwork));

	}
	
	public OperatorPackage getPackage(int index) {
		return packages.get(index);
	}
	
	public String getName() {
		return name;
	}
	

}
