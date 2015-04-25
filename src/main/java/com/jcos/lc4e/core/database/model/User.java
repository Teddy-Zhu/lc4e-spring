package com.jcos.lc4e.core.database.model;

public class User {
	private Integer intuserid;

	private String strusername;

	private String strusermail;

	private String strusernick;

	private String struserpass;

	private String struserpasssalt;

	private Integer intlocked;

	public User() {
	}

	public User(String strusername, String struserpass) {
		super();
		this.intuserid = null;
		this.strusername = strusername;
		this.strusermail = "";
		this.strusernick = "";
		this.struserpass = struserpass;
		this.struserpasssalt = "";
		this.intlocked = 0;
	}

	public Integer getIntuserid() {
		return intuserid;
	}

	public void setIntuserid(Integer intuserid) {
		this.intuserid = intuserid;
	}

	public String getStrusername() {
		return strusername;
	}

	public void setStrusername(String strusername) {
		this.strusername = strusername == null ? null : strusername.trim();
	}

	public String getStrusermail() {
		return strusermail;
	}

	public void setStrusermail(String strusermail) {
		this.strusermail = strusermail == null ? null : strusermail.trim();
	}

	public String getStrusernick() {
		return strusernick;
	}

	public void setStrusernick(String strusernick) {
		this.strusernick = strusernick == null ? null : strusernick.trim();
	}

	public String getStruserpass() {
		return struserpass;
	}

	public void setStruserpass(String struserpass) {
		this.struserpass = struserpass == null ? null : struserpass.trim();
	}

	public String getStruserpasssalt() {
		return struserpasssalt;
	}

	public void setStruserpasssalt(String struserpasssalt) {
		this.struserpasssalt = struserpasssalt == null ? null : struserpasssalt.trim();
	}

	public Integer getIntlocked() {
		return intlocked;
	}

	public void setIntlocked(Integer intlocked) {
		this.intlocked = intlocked;
	}
}