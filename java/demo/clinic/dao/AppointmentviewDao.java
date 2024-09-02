package demo.clinic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.clinic.model.view.Appointmentview;


public interface AppointmentviewDao extends JpaRepository<Appointmentview, String> {
	
	List<Appointmentview> findByPatIdAndStatusOrderByApDateDesc(String patId, String status);
	List<Appointmentview> findByPatIdAndStatusNotOrderByStatusDesc(String patId, String status);
	List<Appointmentview> findByStafNameAndStatusOrderByApDateDesc(String stafName, String status);
	List<Appointmentview> findByStafNameAndStatusNotOrderByApDateDesc(String stafName, String status);
}
