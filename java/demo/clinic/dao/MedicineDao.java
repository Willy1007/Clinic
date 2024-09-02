package demo.clinic.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.clinic.model.Medicine;


public interface MedicineDao extends JpaRepository<Medicine, String>{
	
	Medicine findByMedIdOrMedName(String medId, String medName);
	
	@Query(value = "SELECT * FROM medicine ORDER BY CAST(medId AS UNSIGNED)", nativeQuery = true)
	Page<Medicine> findAllOrderByMedIdAsNumberNative(Pageable paging);
	
}
