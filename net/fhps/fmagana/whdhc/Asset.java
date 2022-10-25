package net.fhps.fmagana.whdhc;

public class Asset {
	private String serialNumber;
	private int assetNumber;
	private String jsonResponse;
	
	public Asset() {
		this.serialNumber = "";
		this.assetNumber = Integer.MIN_VALUE;
		this.jsonResponse = "[]";
	}
	
	public Asset(String serialNumber) {
		this.serialNumber = serialNumber;
		this.assetNumber = Integer.MIN_VALUE;
		this.jsonResponse = "[]";
	}

	public Asset(int assetNumber) {
		this.assetNumber = assetNumber;
		this.serialNumber = "";
		this.jsonResponse = "[]";
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public int getAssetNumber() {
		return this.assetNumber;
	}
	
	public void setAssetNumber(int assetNumber) {
		this.assetNumber = assetNumber;
	}
	
	public String getJSONResponse() {
		return this.jsonResponse;
	}
	
	public void setJSONResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
}