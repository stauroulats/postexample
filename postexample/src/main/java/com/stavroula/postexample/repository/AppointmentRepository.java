package com.stavroula.postexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.Appointment;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;

@Repository("appointmentRepository")
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	@Query("SELECT r FROM Appointment r WHERE rider =:rider")
	public List<Appointment> findAllRiderAppointments(@Param("rider") Rider rider);
	
	@Query("SELECT r FROM Appointment r WHERE driver =:driver")
	public List<Appointment> findAllDriverAppointments(@Param("driver") Driver driver);

	@Query("SELECT r FROM Appointment r WHERE status = 'pending'")
	public List<Appointment> findAllPendingAppointments();
	
	@Query("SELECT r FROM Appointment r WHERE status = 'scheduled' ORDER BY r.date DESC")
	public List<Appointment> findAllScheduledAppointments();
}

