package com.suchismitacodes.mealbox;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.suchismitacodes.mealbox.entity.Admin;
import com.suchismitacodes.mealbox.entity.Product;
import com.suchismitacodes.mealbox.entity.User;
import com.suchismitacodes.mealbox.service.AdminService;
import com.suchismitacodes.mealbox.service.ProductService;
import com.suchismitacodes.mealbox.service.UserService;

@SpringBootApplication
public class MealboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealboxApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeDefaultData(AdminService adminService,
												   UserService userService,
												   ProductService productService) {
		return args -> {
			// Default admin
			if (adminService.getAll().isEmpty()) {
				Admin defaultAdmin = new Admin();
				defaultAdmin.setAdminEmail("admin@mealbox.com");
				defaultAdmin.setAdminPassword("admin123");
				defaultAdmin.setAdminName("Default Admin");
				adminService.addAdmin(defaultAdmin);
				System.out.println("Default admin created: admin@mealbox.com / admin123");
			}
			// Default user
			if (userService.getAllUser().isEmpty()) {
				User defaultUser = new User();
				defaultUser.setUemail("user@mealbox.com");
				defaultUser.setUpassword("user123");
				defaultUser.setUname("Default User");
				defaultUser.setUnumber(1234567890L);
				userService.addUser(defaultUser);
				System.out.println("Default user created: user@mealbox.com / user123");
			}
			// Seed products if the table is empty
			if (productService.count() == 0) {

				// ── Biryani (8 items) ──
				productService.addProduct(new Product("Hyderabadi Biryani", 250,
						"Authentic Hyderabadi dum biryani with aromatic spices", "Biryani",
						"/Images/biryani/Biryani_of_Hyderabadi.jpg", 5));
				productService.addProduct(new Product("Dum Biryani", 230,
						"Slow-cooked dum biryani with layered rice and spices", "Biryani",
						"/Images/biryani/Dum-Biryani.jfif", 5));
				productService.addProduct(new Product("Egg Biryani", 200,
						"Flavorful biryani with perfectly boiled eggs", "Biryani",
						"/Images/biryani/Egg-biryani.jpg", 4));
				productService.addProduct(new Product("Egg Dum Biryani", 220,
						"Dum-style egg biryani with fragrant basmati rice", "Biryani",
						"/Images/biryani/egg-dum-biryani.jpg", 4));
				productService.addProduct(new Product("Kolkata Biryani", 260,
						"Light and aromatic Kolkata-style biryani with potato and egg", "Biryani",
						"/Images/biryani/kolkata biryani.jpg", 5));
				productService.addProduct(new Product("Lucknowi Biryani", 270,
						"Royal Awadhi-style Lucknowi biryani with tender meat", "Biryani",
						"/Images/biryani/Lucknowi-biryani.jpg", 5));
				productService.addProduct(new Product("Veg Biryani", 180,
						"Aromatic vegetable biryani with fresh seasonal veggies", "Biryani",
						"/Images/biryani/veg-biryani.jpg", 4));
				productService.addProduct(new Product("Veg Dum Biryani", 190,
						"Slow-cooked vegetable dum biryani with saffron rice", "Biryani",
						"/Images/biryani/veg-dum-biryani.jpeg", 4));

				// ── Chicken (5 items) ──
				productService.addProduct(new Product("Afghani Chicken Curry", 260,
						"Creamy and mild Afghani-style chicken curry", "Chicken",
						"/Images/chicken/Afgani-chicken-curry.jpg", 5));
				productService.addProduct(new Product("Butter Chicken", 230,
						"Creamy butter chicken in rich tomato gravy", "Chicken",
						"/Images/chicken/butter-chicken.jpg", 5));
				productService.addProduct(new Product("Chicken Kadai", 240,
						"Spicy chicken kadai cooked with capsicum and onions", "Chicken",
						"/Images/chicken/chicken-karahi-kadai.jpg", 4));
				productService.addProduct(new Product("Matka Chicken", 280,
						"Tender chicken slow-cooked in an earthen pot", "Chicken",
						"/Images/chicken/Matka-Chicken.jpg", 5));
				productService.addProduct(new Product("Tandoori Chicken Tikka", 250,
						"Smoky charcoal-grilled tandoori chicken tikka", "Chicken",
						"/Images/chicken/Tandoori-chicken-tikka.jpg", 5));

				// ── Chinese (4 items) ──
				productService.addProduct(new Product("Chilli Paneer", 180,
						"Crispy paneer tossed in spicy chilli sauce", "Chinese",
						"/Images/chinese/chilli-Paneer.jfif", 4));
				productService.addProduct(new Product("Chowmein", 130,
						"Stir-fried noodles with vegetables and soy sauce", "Chinese",
						"/Images/chinese/chowmein.jpg", 4));
				productService.addProduct(new Product("Fried Rice with Manchurian", 160,
						"Indo-Chinese combo of fried rice and veg manchurian", "Chinese",
						"/Images/chinese/Fried-rice-manchurian.jpg", 5));
				productService.addProduct(new Product("Momos", 120,
						"Steamed dumplings filled with spiced vegetables", "Chinese",
						"/Images/chinese/momo.webp", 5));

				// ── North India (6 items) ──
				productService.addProduct(new Product("Chole Bhature", 140,
						"Fluffy fried bhature served with spicy chole", "North India",
						"/Images/north-india-food/chola-bhatura.jpg", 5));
				productService.addProduct(new Product("Gulab Jamun", 80,
						"Soft deep-fried dumplings soaked in sugar syrup", "North India",
						"/Images/north-india-food/Gulab Jamun.jpg", 5));
				productService.addProduct(new Product("Honey Chilli Potato", 150,
						"Crispy potato fingers in sweet and spicy honey chilli glaze", "North India",
						"/Images/north-india-food/Honey-Chilli-Potato.jpg", 4));
				productService.addProduct(new Product("Kheer", 90,
						"Traditional creamy rice pudding with cardamom and nuts", "North India",
						"/Images/north-india-food/Khir.jpg", 4));
				productService.addProduct(new Product("Laccha Paratha", 50,
						"Layered flaky paratha with crispy texture", "North India",
						"/Images/north-india-food/Laccha-Paratha.jpg", 4));
				productService.addProduct(new Product("Soya Chaap", 170,
						"Marinated soya chaap grilled with tandoori spices", "North India",
						"/Images/north-india-food/Soya-Chaap.jpg", 4));

				// ── Paneer (4 items) ──
				productService.addProduct(new Product("Kadai Paneer", 200,
						"Spicy paneer with capsicum in kadai masala gravy", "Paneer",
						"/Images/paneer/Kadai-Paneer.jpg", 5));
				productService.addProduct(new Product("Matar Paneer", 180,
						"Cottage cheese and green peas in a rich tomato gravy", "Paneer",
						"/Images/paneer/Matar-Paneer.jpg", 4));
				productService.addProduct(new Product("Paneer Butter Masala", 190,
						"Rich and creamy paneer in tomato-butter gravy", "Paneer",
						"/Images/paneer/paneer-butter-masala.jpg", 5));
				productService.addProduct(new Product("Paneer Do Pyaza", 200,
						"Paneer cubes cooked with double onion masala", "Paneer",
						"/Images/paneer/paneer-do-pyaza.jpg", 4));

				// ── Vegetable (1 item) ──
				productService.addProduct(new Product("Mix Veg Curry", 140,
						"Seasonal vegetables in a spiced curry gravy", "Vegetable",
						"/Images/vegetable/veg.jpg", 4));

				System.out.println("Seeded 28 products into the database.");
			}
		};
	}

}
