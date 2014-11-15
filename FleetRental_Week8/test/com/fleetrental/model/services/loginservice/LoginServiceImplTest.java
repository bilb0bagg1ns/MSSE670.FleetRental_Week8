package com.fleetrental.model.services.loginservice;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.fleetrental.model.business.exception.ServiceLoadException;
import com.fleetrental.model.services.manager.PropertyManager;
import com.fleetrental.model.domain.Customer;
import com.fleetrental.model.domain.RentalComposite;
import com.fleetrental.model.services.exception.LoginException;
import com.fleetrental.model.services.factory.ServiceFactory;
import com.fleetrental.model.services.loginservice.LoginServiceImpl;

public class LoginServiceImplTest extends TestCase {

	private ServiceFactory serviceFactory;
	private Customer customer;
	private RentalComposite rentalComposite = new RentalComposite();

	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
		super.setUp();

		// Hmmm, we have load the properties via the PropertyManager
	    String propertyFileLocation = System.getProperty("prop_location");
		
        // Now that we have the property file location, lets have the 
		// PropertyManager class load it up
		PropertyManager propertyManager = new PropertyManager();
    	propertyManager.loadProperties(propertyFileLocation);		

		
		serviceFactory = ServiceFactory.getInstance();

		customer = new Customer ("Simpson", "Homer", "homer@duff.com", "duff", "619.111.1234","619.111.1234");
		
		rentalComposite.setCustomer(customer);
	}

	/**
	 * Test method for {@link com.fleetrental.model.services.LoginService.LoginServiceImpl#authenticateCustomer(com.fleetrental.model.domain.Customer)}.
	 */
	public final void testAuthenticateCustomer() {

		//
		// USE THIS APPROOACH OF CASTING TO AN INTERFACE
		//
		// Here we are casting Factory output to ILoginService, which
		// means that loginService will only see methods declared in
		// the interface and implemented by LoginServiceImpl
		//
		ILoginService loginService;
		try {
			loginService = (ILoginService)serviceFactory.getService(ILoginService.NAME);
			Assert.assertTrue(loginService.authenticateCustomer(rentalComposite));
		} catch (ServiceLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("ServiceLoadException");
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("LoginException");

		}
		

		//
		// THIS TO ILLUSTRATE THAT YOU BY CASTING TO AN IMPL AND THE METHOD
        // VISIBILITY RULES THAT GET IMPOSED!!
        //
		// Here we are casting Factory output to LoginServiceImpl, which
		// means that loginServiceImpl will see not only methods declared in
		// the interface and implemented by LoginServiceImpl but also any
		// other additional public methods declared in LoginServiceImpl
		// but *not* private methods!!
		//
		// Good practice is to limit the impl to the methods declared in the interface and
		// additional methods(if really needed) be declared as private!
		//
	
		try {
			LoginServiceImpl loginServiceImpl = (LoginServiceImpl)serviceFactory.getService(ILoginService.NAME);
			Assert.assertTrue(loginServiceImpl.authenticateCustomer(rentalComposite));
		} catch (ServiceLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("ServiceLoadException");
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("LoginException");

		}
	}

}
