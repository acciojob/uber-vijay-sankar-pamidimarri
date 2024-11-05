package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
         Optional<Customer> customer=customerRepository2.findById(customerId);
		 if(customer!=null){
			 customerRepository2.save(customer.get());
		 }
	}

@Override
public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception {
    // Fetch the customer by ID
    Optional<Customer> customerOptional = customerRepository2.findById(customerId);

    if (!customerOptional.isPresent()) {
        throw new Exception("Customer not found!");
    }

    Customer customer = customerOptional.get();
    List<Driver> drivers = driverRepository2.findAll();

    Driver availableDriver = null; // Track the driver with the lowest ID

    // Find the available driver with the lowest ID
    for (Driver driver : drivers) {
        if (driver.getCab().getCabavailability() == cabAvailability.AVAILABLE) {
            // Check for the first available driver with the lowest ID
            if (availableDriver == null || driver.getId() < availableDriver.getId()) {
                availableDriver = driver;
            }
        }
    }

    // If no driver is available, throw an exception
    if (availableDriver == null) {
        throw new Exception("No cab available!");
    }

    // Create a new TripBooking
    TripBooking tripBooking = new TripBooking();
    tripBooking.setCustomer(customer);
    tripBooking.setLocation(fromLocation + " to " + toLocation);
    tripBooking.setDistance(distanceInKm);
    tripBooking.setBill(distanceInKm * availableDriver.getCab().getRatesOfValue()); // Assuming you have rates defined in Cab
    tripBooking.setStatus(TripStatus.CONFIRMED); // Set status accordingly

    // Save the TripBooking
    tripBookingRepository2.save(tripBooking);

    // Mark the cab as unavailable
    availableDriver.getCab().setCabavailability(cabAvailability.NOTAVAILABLE);
    driverRepository2.save(availableDriver);

    return tripBooking; // Return the created TripBooking
}


	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		tripBookingRepository2.deleteById(tripId);
	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
        Optional<TripBooking> tripBooking=tripBookingRepository2.findById(tripId);
		tripBooking.get().setStatus(TripStatus.COMPLETED);
	}
}
