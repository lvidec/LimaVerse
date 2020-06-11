package com.WebApp.Login;

import java.io.File;

public class Tv extends Product{

	private String resolution;

	
	public Tv(Integer pid, String pname, String pdescription, String ppicPath, Float pprice, String resolution) {
		super(pid, pname, pdescription, ppicPath, pprice);
		this.setResolution(resolution);
	}


	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tv other = (Tv) obj;
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		return true;
	}
	

	public static int size() {
        
		File f = new File("C:\\Java\\Spring Boot\\Login\\src\\main\\WebApp\\images\\TVs");
        int count = 0;
        
        for (File file : f.listFiles()) {
                if (file.isFile()) {
                        count++;
                }
        }
       
        return count;
	}

	
	
}
