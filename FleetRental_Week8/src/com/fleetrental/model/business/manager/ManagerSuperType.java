package com.fleetrental.model.business.manager;

import com.fleetrental.model.business.exception.PropertyFileNotFoundException;
import com.fleetrental.model.domain.RentalComposite;
import com.fleetrental.model.services.manager.PropertyManager;

/**
 * 
 * NOTE: Certain features in this abstract class are not discussed in class slides, 
 *       however recommended to understand and highly encouraged to apply in your homework.
 *       
 */       
public abstract class ManagerSuperType {

	/**
	 * What you seeing below, is called a static initializer block,
	 * which gets executed at the time when the class that contains it or extends it is referenced.
	 * 
	 * What we hope to achieve in this application is that when the FleetRentalManager(which extends this class)
	 * is referenced, we want the application properties to be loaded up so the properties contents are available
	 * for use by all tiers under the business tier.
	 * 
	 * Reference site on static initializer block:
	 * http://www.developer.com/java/other/article.php/2238491/The-Essence-of-OOP-using-Java-Static-Initializer-Blocks.htm
	 * 
	 */
	static
	{
    	try
		{
    		ManagerSuperType.loadProperties();  
		}
    	catch (PropertyFileNotFoundException propertyFileNotFoundException)
		{
    	   System.out.println ("Application Properties failed to be loaded - Application exiting...");
    	   System.exit(1); // since we can't load the properties and this being crucial we'll exit the application!
		}				
	} // end of static initializer block
	
	/** 
	 * Generic method that all clients of this class can call to perform certain actions.
	 * 
	 * @param commandString 
	 *                      Holds the service name to be invoked	                       
	 * @param rentalComposite
	 *                      Holds application specific domain state
	 * @return
	 *         false
	 *              if action failed
	 *         true
	 *              if action is successful
	 */
	public abstract boolean performAction(String commandString, RentalComposite rentalComposite); 
	
	
	/**
	 * Loads the property file into memory so its available for use by all tiers (business and below)
	 * @throws PropertyFileNotFoundException
	 *                                Properties file could bot be loaded.
	 */
    public static void loadProperties () throws PropertyFileNotFoundException
	{
    	System.out.println ("---->Inside ManagerSuperType::loadProperties");
     
      // In lieu of using the System.getProperty, we follow the steps in the slides, which has us
      // copy the config folder into the build folder which contains the jar file as well
	     String propertyFileLocation = "config\\application.properties";

     // Now that we have the property file location, lets have the 
		   // PropertyManager class load it up
    	PropertyManager.loadProperties(propertyFileLocation);
		
	} //end loadProperties	
    
}
