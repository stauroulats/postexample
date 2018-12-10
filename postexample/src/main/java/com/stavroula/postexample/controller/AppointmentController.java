package com.stavroula.postexample.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stavroula.postexample.entity.Appointment;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.service.AppointmentService;
import com.stavroula.postexample.service.DriverService;
import com.stavroula.postexample.service.RiderService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	RiderService riderService;
	
	@Autowired
	DriverService driverService;
	
	
	@RequestMapping(value = "/rider/{riderId}", method = RequestMethod.GET)
	public ResponseEntity<List<Appointment>> getAllRiderAppointments(@PathVariable Long riderId){
			//TODO find user from token/get appointments/for driver 
			Rider rider = riderService.getRider(riderId);
			List<Appointment> appointments = appointmentService.getAllRiderAppointments(rider);
			return ResponseEntity.accepted().body(appointments);
		}
	
	@RequestMapping(value = "/driver/{driverId}", method = RequestMethod.GET)
	public ResponseEntity<List<Appointment>> getAllDriversAppointments(@PathVariable Long driverId){
			//TODO find user from token/get appointments/for driver 
			Driver driver =driverService.getDriver(driverId);
			List<Appointment> appointments = appointmentService.getAllDriverAppointments(driver);
			return ResponseEntity.accepted().body(appointments);
		}
	
	@RequestMapping(value = "/pending" , method = RequestMethod.GET)
	public ResponseEntity <List<Appointment>> getPentingAppointments(){
		List<Appointment> pendingAppointments = appointmentService.getAllPendingAppointments();
		return ResponseEntity.accepted().body(pendingAppointments);
	}
	
	@RequestMapping(value = "/scheduled" , method = RequestMethod.GET)
	public ResponseEntity <List<Appointment>> getScheduledAppointments(){
		List<Appointment> scheduledAppointments = appointmentService.getAllScheduledAppointments();
		return ResponseEntity.accepted().body(scheduledAppointments);
	}
	
	@RequestMapping(value="/{riderId}/create" , method = RequestMethod.POST)
	public ResponseEntity<Appointment> createAppointment(@RequestBody String jsonStr , @PathVariable Long riderId) throws ParseException{
		
		JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String pickUpPoint = jsonObject.get("pickUpPoint").getAsString();
		String destination = jsonObject.get("destination").getAsString();
		Date date = sdf.parse(jsonObject.get("date").getAsString());
		//Date start = jsonObject.get("time").getAsInt();
		
		//TODO get rider from token
		Rider rider = riderService.getRider(riderId);
		//TODO if rider doesn't exist return error
		
		Appointment appointment = appointmentService.createAppointment(rider, pickUpPoint , destination, date);
		return ResponseEntity.accepted().body(appointment);//TODO send request to drivers
	}
	
	@RequestMapping(value = "/cancelAppointment/{appointmentId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Appointment> cancelAppointment(@PathVariable Long appointmentId){
		Appointment appointment = appointmentService.cancelAppointment(appointmentId);
		return ResponseEntity.accepted().body(appointment);
	}

}
