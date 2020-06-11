package com.WebApp.Login;

import java.io.File;

public class Headphone extends Product {


	public Headphone(Integer pid, String pname, String pdescription, String ppicPath, Float pprice) {
		super(pid, pname, pdescription, ppicPath, pprice);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}





	public static int size() {
        
		File f = new File("C:\\Java\\Spring Boot\\Login\\src\\main\\WebApp\\images\\Headphones");
        int count = 0;
        
        for (File file : f.listFiles()) {
                if (file.isFile()) {
                        count++;
                }
        }
       
        return count;
	}

	
	
	
}
