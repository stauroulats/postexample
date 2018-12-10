package com.stavroula.postexample.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Appointment;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;

@Service
public interface AppointmentService {

	public Appointment getAppointment(Long appointmentId);
	public List<Appointment> getAllRiderAppointments(Rider rider);
	public List<Appointment> getAllDriverAppointments(Driver driver);
	public List<Appointment> getAllPendingAppointments();
	public List<Appointment> getAllScheduledAppointments();
	public Appointment createAppointment(Rider rider, String pickUpPoint, String destination , Date date);
	public Appointment saveAppointment(Driver driver,Appointment appointment);
	public Appointment cancelAppointment(Long appointmentId);
	
	
}
