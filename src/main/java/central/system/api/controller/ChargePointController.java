package central.system.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import central.system.api.exception.ResourceNotFoundException;
import central.system.api.model.ChargePoint;
import central.system.api.repository.ChargePointRepository;

@RestController
@RequestMapping("/central/api")
public class ChargePointController {

	@Autowired
	ChargePointRepository chargePointRepository;
	

	// Get organizations
	@CrossOrigin(origins="*")
	@GetMapping("chargePoint")
	public List<ChargePoint> getAllOrganization(){
		 return this.chargePointRepository.findAll();
	}
	
	
	// Get organizations by id
	
	@CrossOrigin(origins="*")
	@GetMapping("chargePoint/{id}")
	public ResponseEntity<ChargePoint> getChargePointById(@PathVariable(value = "id") UUID chargePointId) throws ResourceNotFoundException{
		ChargePoint chargePoint = chargePointRepository.findById(chargePointId)
				.orElseThrow(() -> new ResourceNotFoundException("Charge point not found for this id :: " + chargePointId));
		return ResponseEntity.ok().body(chargePoint);
	}
	
	// Save organization
	
	@CrossOrigin(origins="*")
	@PostMapping("chargePoint")
	public ChargePoint createChargePoint(@RequestBody ChargePoint chargePoint){
		return this.chargePointRepository.save(chargePoint);	
	}
	
	
	// Update organization
	
	@CrossOrigin(origins="*")
	@PutMapping("chargePoint/{id}")
	public ResponseEntity<ChargePoint> updateChargePoint(@PathVariable(value = "id") UUID chargePointId, 
			@Validated @RequestBody ChargePoint chargePointDetails) throws ResourceNotFoundException{
		ChargePoint chargePoint = chargePointRepository.findById(chargePointId)
				.orElseThrow(() -> new ResourceNotFoundException("Charge point not found for this id :: " + chargePointId));
		
		chargePoint.setIdentity(chargePointDetails.getIdentity());
		chargePoint.setCpo(chargePointDetails.getCpo());
		
		
		return ResponseEntity.ok(this.chargePointRepository.save(chargePoint));
	}
	
	// Delete organization
	@CrossOrigin(origins="*")
	@DeleteMapping("chargePoint/{id}")
	public Map<String, Boolean> deleteChargePoint(@PathVariable(value = "id") UUID chargePointId) throws ResourceNotFoundException{
		ChargePoint chargePoint = chargePointRepository.findById(chargePointId)
				.orElseThrow(() -> new ResourceNotFoundException("Charge point not found for this id :: " + chargePointId));
		
		this.chargePointRepository.delete(chargePoint);
		Map <String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
