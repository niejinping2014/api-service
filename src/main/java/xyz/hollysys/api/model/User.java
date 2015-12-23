package xyz.hollysys.api.model;

/**
 * �û���Ϣ
 * @author sanhao
 *
 */
public class User {
	private long user_id;
	private String user_account;
	private String user_name;
	private String user_address;
	private String user_cardtype;
	private String user_idcard;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_cardtype() {
		return user_cardtype;
	}
	public void setUser_cardtype(String user_cardtype) {
		this.user_cardtype = user_cardtype;
	}
	public String getUser_idcard() {
		return user_idcard;
	}
	public void setUser_idcard(String user_idcard) {
		this.user_idcard = user_idcard;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_account=" + user_account + ", user_name=" + user_name
				+ ", user_address=" + user_address + ", user_cardtype=" + user_cardtype + ", user_idcard=" + user_idcard
				+ ", password=" + password + "]";
	}
	
	
	
}
