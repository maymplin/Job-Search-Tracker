package org.launchcode.jobsearchtracker.data;

import org.launchcode.jobsearchtracker.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
