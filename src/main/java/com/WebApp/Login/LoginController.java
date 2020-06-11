package com.WebApp.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import database.Database;
import exception.DatabaseException;



@Controller
public class LoginController {

	public static List<Laptop> bucketLaptops = new ArrayList<>();
	public static List<Tv> bucketTvs = new ArrayList<>();
	public static List<Headphone> bucketHeadphones = new ArrayList<>();

	public static int numOfItemsBucket = 0;
	public static Float price = (float) 0;
	
	public static boolean isRegistered = false;

	int cidAutomatic = 0;

//	@Autowired
//	CustomerRepository repo;



	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createAcc() {
	
		return "createAcc.jsp";
	}
	
	@RequestMapping("createAcc")
	public String createAcc(@Valid @ModelAttribute Customer customer,
							Errors errors, Model model) throws DatabaseException {

///	PAZI KASNIJE MOZDA JE TU DUPLO POVECANJE CID-a (ovdje, i u data.sql(auto_increment))	
//		cidAutomatic = Database.getCustomers(null).size();
//		customer.setCid(cidAutomatic);
//		cidAutomatic++;

		if( errors.hasErrors()) {
			List<FieldError> listOfErrors = errors.getFieldErrors();
		    
			for (FieldError error : listOfErrors ) {
				String s = error.getDefaultMessage();
		    	if( s.startsWith("N"))
		    		model.addAttribute("nameError", "Name must have at least 2 characters!");
				if( s.startsWith("E"))
					model.addAttribute("emailError", "Enter an email address!");
		    	if( s.startsWith("P"))
		    		model.addAttribute("passwordError", "Password must be longer than 8 characters!");
			}    	
		    
			return "createAcc.jsp";
		
		}
			    
//		repo.save(customer);
		Database.setCustomer(customer);
		
		List<Laptop> listOfLaptops = Database.getLaptop(null);
		model.addAttribute("laptops", listOfLaptops);
		
		List<Tv> listOfTvs = Database.getTv(null);
		model.addAttribute("tvs", listOfTvs);
		
		List<Headphone> listOfHeadphones = Database.getHeadphone(null);
		model.addAttribute("headphones", listOfHeadphones);
		
		numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
		model.addAttribute("numOfItemsBucket", numOfItemsBucket);
	
		return "website.jsp";
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() throws DatabaseException {
		
		List<Customer> listofCustomers = Database.getCustomers(null);
		
		listofCustomers.forEach(x -> System.out.println(x.toString()));
		System.out.println();
		
		return "login.jsp";
	}
	
	@RequestMapping(value = "getDetails", method = RequestMethod.POST)
	public String getDetails(@RequestParam("cemail") String email,
							 @RequestParam("cpassword") String password, 
							 ModelMap map) throws DatabaseException {
				
		if( Database.isRegistered(email, password) ) {
			
			isRegistered = true;
			
			List<Laptop> listOfLaptops = Database.getLaptop(null);
			map.addAttribute("laptops", listOfLaptops);
			
			List<Tv> listOfTvs = Database.getTv(null);
			map.addAttribute("tvs", listOfTvs);
			
			List<Headphone> listOfHeadphones = Database.getHeadphone(null);
			map.addAttribute("headphones", listOfHeadphones);
			
			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);
			
			return "website.jsp";
		}
		else
			return "login.jsp";

	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String website(ModelMap map, @RequestParam(defaultValue = "-10", name = "showAll") int showAll,
										@RequestParam(defaultValue = "-10", name = "showLaptops") int showLaptops,
										@RequestParam(defaultValue = "-10", name = "showTvs") int showTvs,
										@RequestParam(defaultValue = "-10", name = "showHeadphones") int showHeadphones,
										@RequestParam(defaultValue = "-10", name = "filter") int filter) throws DatabaseException, JsonProcessingException{
		
		if( filter != -10 ) {
			System.out.println("\n\n\n\n\nFILTER " + filter);
			
			List<Laptop> listOfLaptops = Database.getLaptop(null);
			List<Laptop> listOfFilteredLaptops = listOfLaptops.stream().filter(l -> l.getPprice() < filter).collect(Collectors.toList());
			
			List<Tv> listOfTvs = Database.getTv(null);
			List<Tv> listOfFilteredTvs = listOfTvs.stream().filter(t -> t.getPprice() < filter).collect(Collectors.toList());
			
			List<Headphone> listOfHeadphones = Database.getHeadphone(null);
			List<Headphone> listOfFilteredHeadphones = listOfHeadphones.stream().filter(h -> h.getPprice() < filter).collect(Collectors.toList());
			
//			if( showLaptops != -10) {
//				map.addAttribute("laptops", listOfFilteredLaptops);
//
//				return "website.jsp";
//			}
//			
//			if( showTvs != -10) {
//				map.addAttribute("tvs", listOfFilteredTvs);
//
//				return "website.jsp";
//			}
//			
//			if( showHeadphones != -10) {
//				map.addAttribute("headphones", listOfFilteredHeadphones);
//
//				return "website.jsp";
//			}
			
			
//			Float maxPricedLaptop = listOfLaptops.get(0).getPprice();
//			for( int i = 0; i < listOfLaptops.size(); i++) {
//				if( maxPricedLaptop < listOfLaptops.get(i).getPprice())
//					maxPricedLaptop = listOfLaptops.get(i).getPprice();
//			}
//
//			Float maxPricedTv = listOfTvs.get(0).getPprice();
//			for( int i = 0; i < listOfTvs.size(); i++) {
//				if( maxPricedTv < listOfTvs.get(i).getPprice())
//					maxPricedTv = listOfTvs.get(i).getPprice();
//			}
//
//			Float maxPricedHeadphone = listOfHeadphones.get(0).getPprice();
//			for( int i = 0; i < listOfHeadphones.size(); i++) {
//				if( maxPricedHeadphone < listOfHeadphones.get(i).getPprice())
//					maxPricedHeadphone = listOfHeadphones.get(i).getPprice();
//			}
//			
//			Double maxProductPrice = DoubleStream.of(maxPricedLaptop, maxPricedTv, maxPricedHeadphone).max().getAsDouble();
//
//			map.addAttribute("maxProductPrice", maxProductPrice);

			
			map.addAttribute("laptops", listOfFilteredLaptops);
			map.addAttribute("tvs", listOfFilteredTvs);
			map.addAttribute("headphones", listOfFilteredHeadphones);
			
			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);

			return "website.jsp";
		}
		
		if( showAll != -10) {
			List<Laptop> listOfLaptops = Database.getLaptop(null);
			map.addAttribute("laptops", listOfLaptops);
			
			List<Tv> listOfTvs = Database.getTv(null);
			map.addAttribute("tvs", listOfTvs);
			
			List<Headphone> listOfHeadphones = Database.getHeadphone(null);
			map.addAttribute("headphones", listOfHeadphones);
			
			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);
		}

		if( showLaptops != -10) {
			List<Laptop> listOfLaptops = Database.getLaptop(null);
			map.addAttribute("laptops", listOfLaptops);
			
			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);
			
			return "website.jsp";
		}
		
		if( showTvs != -10) {
			List<Tv> listOfTvs = Database.getTv(null);
			map.addAttribute("tvs", listOfTvs);

			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);
			
			return "website.jsp";
		}
		
		if( showHeadphones != -10) {
			List<Headphone> listOfHeadphones = Database.getHeadphone(null);
			map.addAttribute("headphones", listOfHeadphones);

			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);		
			
			return "website.jsp";
		}
		
		List<Laptop> listOfLaptops = Database.getLaptop(null);
		map.addAttribute("laptops", listOfLaptops);
		
		
		List<Tv> listOfTvs = Database.getTv(null);
		map.addAttribute("tvs", listOfTvs);
				
		
		List<Headphone> listOfHeadphones = Database.getHeadphone(null);
		map.addAttribute("headphones", listOfHeadphones); 
		

		numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
		map.addAttribute("numOfItemsBucket", numOfItemsBucket);		
		
		return "website.jsp";
		
	}
	
	
	
	@RequestMapping(value = "bucket", method = RequestMethod.GET)
	public String bucket(ModelMap map, @RequestParam(defaultValue = "-10", name = "laptopButton") int lbutton,
									   @RequestParam(defaultValue = "-10", name = "tvButton") int tbutton,
									   @RequestParam(defaultValue = "-10", name = "headphoneButton") int hbutton, 
									   @RequestParam(defaultValue = "notDetected", name = "delete") String delete) throws DatabaseException  {
		if( lbutton != -10) {
			List<Laptop> listOfLaptops = Database.getLaptop(null);
			map.addAttribute("laptops", listOfLaptops);
			
			List<Tv> listOfTvs = Database.getTv(null);
			map.addAttribute("tvs", listOfTvs);
			
			List<Headphone> listOfHeadphones = Database.getHeadphone(null);
			map.addAttribute("headphones", listOfHeadphones);
			
			System.out.println("Lbutton is pressed boiii " + lbutton);
			
			List<Laptop> l = Database.getLaptop(new Laptop(lbutton, null, null, null, null, null));
			
			bucketLaptops.add(l.get(0));

			price += l.get(0).getPprice();
			
			map.addAttribute("bucketLaptops", bucketLaptops);
			map.addAttribute("bucketTvs", bucketTvs);
			map.addAttribute("bucketHeadphones", bucketHeadphones);
			map.addAttribute("price", price);

			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);		
			
			return "website.jsp";
		}
		
		if(tbutton != -10) {
			List<Laptop> listOfLaptops = Database.getLaptop(null);
			map.addAttribute("laptops", listOfLaptops);
			
			List<Tv> listOfTvs = Database.getTv(null);
			map.addAttribute("tvs", listOfTvs);
					
			List<Headphone> listOfHeadphones = Database.getHeadphone(null);
			map.addAttribute("headphones", listOfHeadphones);
			
			
			
			System.out.println("Tbutton is pressed boiii " + tbutton);
			
			List<Tv> t = Database.getTv(new Tv(tbutton, null, null, null, null, null));
			
			bucketTvs.add(t.get(0));
			
			price += t.get(0).getPprice();
			
			map.addAttribute("bucketLaptops", bucketLaptops);
			map.addAttribute("bucketTvs", bucketTvs);
			map.addAttribute("bucketHeadphones", bucketHeadphones);
			map.addAttribute("price", price);

			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);
			
			return "website.jsp";
		}
		
		if(hbutton != -10) {
			List<Laptop> listOfLaptops = Database.getLaptop(null);
			map.addAttribute("laptops", listOfLaptops);
			
			List<Tv> listOfTvs = Database.getTv(null);
			map.addAttribute("tvs", listOfTvs);
			
			List<Headphone> listOfHeadphones = Database.getHeadphone(null);
			map.addAttribute("headphones", listOfHeadphones);
			
			
			
			System.out.println("Hbutton is pressed boiii");
			
			List<Headphone> h = Database.getHeadphone(new Headphone(hbutton, null, null, null, null));
			
			bucketHeadphones.add(h.get(0));
			
			price += h.get(0).getPprice();
			
			map.addAttribute("bucketLaptops", bucketLaptops);
			map.addAttribute("bucketTvs", bucketTvs);
			map.addAttribute("bucketHeadphones", bucketHeadphones);
			map.addAttribute("price", price);

			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);			
			
			return "website.jsp";
		}		
		
		if( delete != "notDetected") {
			List<Laptop> l = Database.getLaptop(new Laptop(null, delete, null, null, null, null));
			List<Tv> t = Database.getTv(new Tv(null, delete, null, null, null, null));
			List<Headphone> h = Database.getHeadphone(new Headphone(null, delete, null, null, null));
			
			if( l.size() > 0) {
				if( bucketLaptops.contains(l.get(0))) {
					bucketLaptops.remove(l.get(0));
					price -= l.get(0).getPprice();
				}
			}

			if( t.size() > 0) {
				if( bucketTvs.contains(t.get(0))) {
					bucketTvs.remove(t.get(0));
					price -= t.get(0).getPprice();
				}
			}
			
			if( h.size() > 0) {
				if( bucketHeadphones.contains(h.get(0))) {
					bucketHeadphones.remove(h.get(0));
					price -= h.get(0).getPprice();
				}
			}
			
			map.addAttribute("bucketLaptops", bucketLaptops);
			map.addAttribute("bucketTvs", bucketTvs);
			map.addAttribute("bucketHeadphones", bucketHeadphones);
			map.addAttribute("price", price);

			numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
			map.addAttribute("numOfItemsBucket", numOfItemsBucket);
			
			return "customerBucket.jsp";
		}
		
		map.addAttribute("bucketLaptops", bucketLaptops);
		map.addAttribute("bucketTvs", bucketTvs);
		map.addAttribute("bucketHeadphones", bucketHeadphones);
		map.addAttribute("price", price);

		numOfItemsBucket = bucketLaptops.size() + bucketTvs.size() + bucketHeadphones.size();
		map.addAttribute("numOfItemsBucket", numOfItemsBucket);
		
		return "customerBucket.jsp";
		
	}

	
	
	
	
	
//MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
	
	
	
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String createAcc() {
//	
//		return "createAcc.jsp";
//	}
//	
//	@RequestMapping(value = "createAcc", method = RequestMethod.GET)
//	public String createAcc(Customer customer) throws DatabaseException {
//		
//		customer.setCid(cidAutomatic);
//		cidAutomatic++;
//		
////		repo.save(customer);
//		Database.setCustomer(customer);
//		
//		return "createAcc.jsp";
//	}
//	
//	
//	@RequestMapping(value = "login", method = RequestMethod.GET)
//	public String login() throws DatabaseException {
//		
//		List<Customer> listofCustomers = Database.getCustomers(null);
//		
//		listofCustomers.forEach(x -> System.out.println(x.toString()));
//		return "login.jsp";
//	}
//	
//	
//	@RequestMapping(value = "getDetails", method = RequestMethod.POST)
//	public String getDetails(@Valid @RequestParam("cemail") String email,
//							 @Valid @RequestParam("cpassword") String password, 
//							 Errors errors) throws DatabaseException{
//
//		if( errors.hasErrors()) {
//			System.out.println("\n\n\n\n\n\n\n\nERROR\n\n\n\n\n\n\n");
//			errors.toString();
//			System.out.println("\n\n\n\n");
//			System.out.println(errors.getAllErrors());
//		}
//		
//		if( Database.isRegistered(email, password) )
//			return "website.jsp";
//		else
//			return "login.jsp";
//
//	}
		
	

	
	
	
	
	
//	@PostMapping("getDetails")
//	public ModelAndView getDetails(@RequestParam("cid") Integer id){
//		
//		ModelAndView mv = new ModelAndView("retrieve.jsp");
//		Customer customer = repo.findById(id).orElse(null);
//		mv.addObject(customer);
//		
//		return mv;
//	}
		
		
//	@PostMapping("details")
//	public String details(@RequestParam("cname") String username, 
//						  @RequestParam("cemail") String email, 
//						  @RequestParam("cpassword") String password, 
//						  ModelMap modelMap) {	
//		
//		modelMap.put("cname", username);
//		modelMap.put("cemail", email);
//		modelMap.put("cpassword", password);
//		
//		
//		return "retrieve.jsp";
//	}






}
