package demo.clinic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import demo.clinic.dao.MedicineDao;
import demo.clinic.model.Medicine;
import demo.clinic.model.MedicineChange;
import demo.clinic.model.MedicineDto;
import demo.clinic.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	private MedicineDao medicineDao;
	
	
	@Override
	public MedicineDto findByMedIdOrMedName(String info) {
		Medicine medicine;
		if(info.matches("\\d{1,3}")) {
			medicine = medicineDao.findByMedIdOrMedName(info, "");
		} else {
			medicine = medicineDao.findByMedIdOrMedName("", info);
		}
		
		if(medicine != null) {
			return MedicineChange.mapToDto(medicine);
		} else {
			return null;
		}
	}

	@Override
	public Map<String, Object> findAll(Integer pageNo, Integer pageSize, String sortBy) {
		Map<String, Object> datas = new HashMap<>();
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Medicine> medicines = medicineDao.findAllOrderByMedIdAsNumberNative(paging);
		
		if(medicines.hasContent()) {
			List<MedicineDto> dto = new ArrayList<>();
			for(Medicine m : medicines) {
				MedicineDto temp = MedicineChange.mapToDto(m);
				dto.add(temp);
			}
			
			datas.put("pageSum", medicines.getTotalPages());
			datas.put("medicines", dto);
			return datas;
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteById(String medId) {
		try {
			medicineDao.deleteById(medId);
			return true;
		} catch (Exception e) {
			System.out.println("deleteById ERROR: " + e.getMessage());
			return false;
		}
	}

	@Override
	public MedicineDto createAndUpdate(MedicineDto dto) {
		try {
			Medicine medicine = medicineDao.save(MedicineChange.mapToMedicine(dto));
			return MedicineChange.mapToDto(medicine);
		} catch (Exception e) {
			System.out.println("createAndUpdate ERROR: " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<MedicineDto> findAll2() {
		List<Medicine> medicines = medicineDao.findAll();
		
		if(medicines.size() > 0) {
			List<MedicineDto> dto = new ArrayList<>();
			for(Medicine m : medicines) {
				MedicineDto temp = MedicineChange.mapToDto(m);
				dto.add(temp);
			}
			return dto;
		} else {
			return null;
		}
	}
	
}
