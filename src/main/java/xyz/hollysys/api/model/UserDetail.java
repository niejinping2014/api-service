package xyz.hollysys.api.model;

import java.math.BigDecimal;

/**
 * 用户详情信息
 * @author sanhao
 *
 */
public class UserDetail {
	private int user_id;
	private String user_idcard;
	private String user_idcardtype;
	private String user_head;
	private String user_nickname;

	private String user_sex;
	private int user_birth_year;
	private int user_birth_month;
	private int user_birth_day;
	private String user_default_address;
	private String user_default_mobile;
	
	public String getUser_nickname() {
		return user_nickname;
	}
	public String getUser_default_address() {
		return user_default_address;
	}
	public void setUser_default_address(String user_default_address) {
		this.user_default_address = user_default_address;
	}
	public String getUser_default_mobile() {
		return user_default_mobile;
	}
	public void setUser_default_mobile(String user_default_mobile) {
		this.user_default_mobile = user_default_mobile;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_idcard() {
		return user_idcard;
	}
	public void setUser_idcard(String user_idcard) {
		this.user_idcard = user_idcard;
	}
	public String getUser_idcardtype() {
		return user_idcardtype;
	}
	public void setUser_idcardtype(String user_idcardtype) {
		this.user_idcardtype = user_idcardtype;
	}
	public String getUser_head() {
		return user_head;
	}
	public void setUser_head(String user_head) {
		this.user_head = user_head;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public int getUser_birth_year() {
		return user_birth_year;
	}
	public void setUser_birth_year(int user_birth_year) {
		this.user_birth_year = user_birth_year;
	}
	public int getUser_birth_month() {
		return user_birth_month;
	}
	public void setUser_birth_month(int user_birth_month) {
		this.user_birth_month = user_birth_month;
	}
	public int getUser_birth_day() {
		return user_birth_day;
	}
	public void setUser_birth_day(int user_birth_day) {
		this.user_birth_day = user_birth_day;
	}

	@Override
	public String toString() {
		return "UserDetail [user_id=" + user_id + ", user_idcard=" + user_idcard + ", user_idcardtype="
				+ user_idcardtype + ", user_head=" + user_head + ", user_nickname=" + user_nickname + ", user_sex="
				+ user_sex + ", user_birth_year=" + user_birth_year + ", user_birth_month=" + user_birth_month
				+ ", user_birth_day=" + user_birth_day + ", user_default_address=" + user_default_address
				+ ", user_default_mobile=" + user_default_mobile + "]";
	}
}
