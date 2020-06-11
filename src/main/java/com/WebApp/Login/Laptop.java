package com.WebApp.Login;

import java.io.File;


public class Laptop extends Product{

	private String specs;
	
	
	public Laptop(Integer pid, String pname, String pdescription, String ppicPath, Float pprice, String specs) {
		super(pid, pname, pdescription, ppicPath, pprice);
		this.specs = specs;
	}

	
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((specs == null) ? 0 : specs.hashCode());
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
		Laptop other = (Laptop) obj;
		if (specs == null) {
			if (other.specs != null)
				return false;
		} else if (!specs.equals(other.specs))
			return false;
		return true;
	}


	public static int size() {
		
		File f = new File("C:\\Java\\Spring Boot\\Login\\src\\main\\WebApp\\images\\Laptops");
        int count = 0;
        
        for (File file : f.listFiles()) {
                if (file.isFile()) {
                        count++;
                }
        }
       
        return count;
	}
	
//	
//	private static String pathToLaptops = "C:\\Java\\Spring Boot\\Login\\src\\main\\WebApp\\images\\Laptops";
//	
//	public static List<ImageView> listOfLaptopImages = new ArrayList<>();
//	
//	public static final ImageView acerImage = new ImageView(new Image(pathToLaptops + "Acer Swift.png"));
//	public static final ImageView dellImage = new ImageView(new Image(pathToLaptops + "Dell XPS 15.png"));
//	public static final ImageView appleImage = new ImageView(new Image(pathToLaptops + "MacBook Pro.png"));
//	public static final ImageView razerImage = new ImageView(new Image(pathToLaptops + "Razor Blade Pro.png"));
//	public static final ImageView thinkPadImage = new ImageView(new Image(pathToLaptops + "ThinkPad X1.png"));
//	
	
	
}
