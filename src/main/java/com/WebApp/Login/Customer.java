package com.WebApp.Login;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;



@Entity
public class Customer {
	
	public static boolean nameError = false; 
	public static boolean emailError = false; 
	public static boolean passwordError = false; 

	@Id
	private Integer cid;
	
	@Size(min = 2, message = "Name must have at least 2 characters!")
	private String cname;
	
	@Email(message = "Enter an email address!")
	private String cemail;
	
	@Size(min = 8, max = 30, message = "Password must be longer than 8 characters!")
	private String cpassword;

	public Customer() {
		
	}
	
	
	public Customer(Integer cid, String cname, String cemail, String cpassword) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cemail = cemail;
		this.cpassword = cpassword;		
	}

	@Override
	public String toString() {
		return "Customers [cid=" + cid + ", cname=" + cname + ", cemail=" + cemail + ", cpassword=" + cpassword + "]";
	}

	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cemail == null) ? 0 : cemail.hashCode());
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((cpassword == null) ? 0 : cpassword.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (cemail == null) {
			if (other.cemail != null)
				return false;
		} else if (!cemail.equals(other.cemail))
			return false;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (cpassword == null) {
			if (other.cpassword != null)
				return false;
		} else if (!cpassword.equals(other.cpassword))
			return false;
		return true;
	}
}

