package demo.clinic.service;

import java.util.List;
import java.util.Map;

import demo.clinic.model.MedicineDto;

public interface MedicineService {
	
	MedicineDto findByMedIdOrMedName(String info);
	Map<String, Object> findAll(Integer pageNo, Integer pageSize, String sortBy);
	boolean deleteById(String medId);
	MedicineDto createAndUpdate(MedicineDto dto);
	List<MedicineDto> findAll2();
}
