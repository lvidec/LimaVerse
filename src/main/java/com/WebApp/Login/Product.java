package com.WebApp.Login;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public abstract class Product {

	@Id
	private Integer pid;
	
	private String pname;
	private String pdescription;
	private String ppicPath;
	private Float pprice;

	
	public Product(Integer pid, String pname, String pdescription, String ppicPath, Float pprice) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pdescription = pdescription;
		this.ppicPath = ppicPath;
		this.pprice = pprice;
	}
	
//	public abstract int size();


	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", pdescription=" + pdescription + ", ppicPath=" + ppicPath
				+ ", pprice=" + pprice + "]";
	}


	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public String getPpicPath() {
		return ppicPath;
	}
	public void setPpicPath(String ppicPath) {
		this.ppicPath = ppicPath;
	}
	public Float getPprice() {
		return pprice;
	}
	public void setPprice(Float pprice) {
		this.pprice = pprice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pdescription == null) ? 0 : pdescription.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((ppicPath == null) ? 0 : ppicPath.hashCode());
		result = prime * result + ((pprice == null) ? 0 : pprice.hashCode());
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
		Product other = (Product) obj;
		if (pdescription == null) {
			if (other.pdescription != null)
				return false;
		} else if (!pdescription.equals(other.pdescription))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (ppicPath == null) {
			if (other.ppicPath != null)
				return false;
		} else if (!ppicPath.equals(other.ppicPath))
			return false;
		if (pprice == null) {
			if (other.pprice != null)
				return false;
		} else if (!pprice.equals(other.pprice))
			return false;
		return true;
	}



	






}

