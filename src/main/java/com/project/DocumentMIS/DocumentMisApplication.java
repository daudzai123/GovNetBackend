package com.project.DocumentMIS;
import java.awt.GraphicsEnvironment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.project.DocumentMIS.config.InitializationService;
import com.project.DocumentMIS.user.UsersRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.project.DocumentMIS")
public class DocumentMisApplication {

	public static void main(String[] args) {

        //  // Replace "Arial" with the name of the font you want to check
        //  String fontName = "Arial";

        //  // Get the local graphics environment
        //  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
 
        //  // Get an array of all available font family names
        //  String[] availableFontFamilyNames = ge.getAvailableFontFamilyNames();
 
        //  // Check if the desired font is available
        //  boolean isFontAvailable = false;
        //  for (String fontFamily : availableFontFamilyNames) {
        //      if (fontFamily.equals(fontName)) {
        //          isFontAvailable = true;
        //          break;
        //      }
        //  }
 
        //  // Print the result
        //  if (isFontAvailable) {
        //      System.out.println("The font '" + fontName + "' is available to the JVM.");
        //  } else {
        //      System.out.println("The font '" + fontName + "' is not available to the JVM.");
        //  }

		SpringApplication.run(DocumentMisApplication.class, args);
	}

    @Bean
    public CommandLineRunner initData(UsersRepository userRepository, InitializationService initializationService) {
        return args -> {
            if (true) {
                initializationService.initialize();
            }
        };
    }
    // @PostConstruct  is used when we want to run this code befor appproperty loaded.
    // public void init() {}
}
