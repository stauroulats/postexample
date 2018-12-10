package com.stavroula.postexample.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Appointment;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Appointment.Status;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.repository.AppointmentRepository;

@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService{
	@Autowired
	AppointmentRepository appointmentRepository;
	
	public Appointment getAppointment(Long appointmentId) {
		Optional<Appointment> optinalEntity =  appointmentRepository.findById(appointmentId);
		Appointment appointment = optinalEntity.isPresent() ? optinalEntity.get():null;
		return appointment;
	}
	
	public List<Appointment> getAllRiderAppointments(Rider rider){
		return appointmentRepository.findAllRiderAppointments(rider);
	}

	public List<Appointment> getAllDriverAppointments(Driver driver){
		return appointmentRepository.findAllDriverAppointments(driver);
	}
	
	public List<Appointment> getAllPendingAppointments(){
		return appointmentRepository.findAllPendingAppointments();
		}
	
	public List<Appointment> getAllScheduledAppointments(){
		return appointmentRepository.findAllScheduledAppointments();
	}
	
	public Appointment createAppointment(Rider rider, String pickUpPoint, String destination ,Date date) {
		Appointment appointment = new Appointment();
		appointment.setRider(rider);
		appointment.setPickUpPoint(pickUpPoint);
		appointment.setDestination(destination);
		appointment.setDate(date);
		//appointment.setTime(time);
		appointment.setStatus(Status.pending);
		appointment = appointmentRepository.saveAndFlush(appointment);
		return appointment;
	}
	
	public Appointment saveAppointment(Driver driver,Appointment appointment) {
		if( (appointment.getStatus() == com.stavroula.postexample.entity.Appointment.Status.pending) ){
			appointment.setDriver(driver);
			appointment.setStatus(com.stavroula.postexample.entity.Appointment.Status.scheduled);
			appointmentRepository.saveAndFlush(appointment);
		}
		return appointment;
	}

	public Appointment cancelAppointment(Long appointmentId) {
		Appointment appointment = getAppointment(appointmentId);
		appointment.setStatus(Status.cancelled);//TODO notification ston driver i rider
		appointmentRepository.saveAndFlush(appointment);
		return appointment;
	}

}
