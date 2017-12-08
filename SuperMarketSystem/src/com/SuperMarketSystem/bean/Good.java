package com.SuperMarketSystem.bean;

public class Good {
	private int gno;
	private String gname;
	private int gprice;
	private int account;
	private String gtype;
	private String gimagepath;
	private String gdes;
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getGprice() {
		return gprice;
	}
	public void setGprice(int gprice) {
		this.gprice = gprice;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getGtype() {
		return gtype;
	}
	public void setGtype(String gtype) {
		this.gtype = gtype;
	}
	public String getGimagepath() {
		return gimagepath;
	}
	public void setGimagepath(String gimagepath) {
		this.gimagepath = gimagepath;
	}
	public String getGdes() {
		return gdes;
	}
	public void setGdes(String gdes) {
		this.gdes = gdes;
	}
	@Override
	public String toString() {
		return "Good [gno=" + gno + ", gname=" + gname + ", gprice=" + gprice + ", account=" + account + ", gtype="
				+ gtype + ", gimagepath=" + gimagepath + ", gdes=" + gdes + "]";
	}
	
}
