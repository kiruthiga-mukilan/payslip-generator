package com.agaram.employee;

public class EmployeePay {

	private String name;
	private String month;
	private String employmentType;
	private String location;
	private String bankName;
	private String bankNumber;
	private String CTC;
	private String payPerMonth;
	private String basic;
	private String HRA;
	private String conveyance;
	private String medical;
	private String netPay;
	private String date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getCTC() {
		return CTC;
	}

	public void setCTC(String CTC) {
		this.CTC = CTC;
	}

	public String getPayPerMonth() {
		return payPerMonth;
	}

	public void setPayPerMonth(String payPerMonth) {
		this.payPerMonth = payPerMonth;
	}

	public String getBasic() {
		return basic;
	}

	public void setBasic(String basic) {
		this.basic = basic;
	}

	public String getHRA() {
		return HRA;
	}

	public void setHRA(String HRA) {
		this.HRA = HRA;
	}

	public String getConveyance() {
		return conveyance;
	}

	public void setConveyance(String conveyance) {
		this.conveyance = conveyance;
	}

	public String getMedical() {
		return medical;
	}

	public void setMedical(String medical) {
		this.medical = medical;
	}

	public String getNetPay() {
		return netPay;
	}

	public void setNetPay(String netPay) {
		this.netPay = netPay;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Employee{" +
			"name='" + name + '\'' +
			", month='" + month + '\'' +
			", employmentType='" + employmentType + '\'' +
			", location='" + location + '\'' +
			", bankName='" + bankName + '\'' +
			", bankNumber='" + bankNumber + '\'' +
			", CTC='" + CTC + '\'' +
			", payPerMonth='" + payPerMonth + '\'' +
			", basic='" + basic + '\'' +
			", HRA='" + HRA + '\'' +
			", conveyance='" + conveyance + '\'' +
			", medical='" + medical + '\'' +
			", netPay='" + netPay + '\'' +
			", date='" + date + '\'' +
			'}';
	}
}
