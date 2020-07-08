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
import central.system.api.model.Organization;
import central.system.api.repository.OrganizationRepository;


@RestController
@RequestMapping("/central/api")
public class OrganizationController {
	
	@Autowired
	OrganizationRepository organizationRepository;
	

	// Get organizations
	@CrossOrigin(origins="*")
	@GetMapping("organization")
	public List<Organization> getAllOrganization(){
		 return this.organizationRepository.findAll();
	}
	
	
	// Get organizations by id
	
	@CrossOrigin(origins="*")
	@GetMapping("organization/{id}")
	public ResponseEntity<Organization> getOrganizationById(@PathVariable(value = "id") UUID organizationId) throws ResourceNotFoundException{
		Organization organization = organizationRepository.findById(organizationId)
				.orElseThrow(() -> new ResourceNotFoundException("Organization not found for this id :: " + organizationId));
		return ResponseEntity.ok().body(organization);
	}
	
	// Save organization
	
	@CrossOrigin(origins="*")
	@PostMapping("organization")
	public Organization createOrganization(@RequestBody Organization organization){
		return this.organizationRepository.save(organization);	
	}
	
	
	// Update organization
	
	@CrossOrigin(origins="*")
	@PutMapping("organization/{id}")
	public ResponseEntity<Organization> updateOrganization(@PathVariable(value = "id") UUID organizationId, 
			@Validated @RequestBody Organization organizationDetails) throws ResourceNotFoundException{
		Organization organization = organizationRepository.findById(organizationId)
				.orElseThrow(() -> new ResourceNotFoundException("Organization not found for this id :: " + organizationId));
		
		organization.setName(organizationDetails.getName());
		organization.setLegalEntity(organizationDetails.getLegalEntity());
		
		
		return ResponseEntity.ok(this.organizationRepository.save(organization));
	}
	
	// Delete organization
	@CrossOrigin(origins="*")
	@DeleteMapping("organization/{id}")
	public Map<String, Boolean> deleteOrganization(@PathVariable(value = "id") UUID organizationId) throws ResourceNotFoundException{
		Organization organization = organizationRepository.findById(organizationId)
				.orElseThrow(() -> new ResourceNotFoundException("Organization not found for this id :: " + organizationId));
		
		this.organizationRepository.delete(organization);
		Map <String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
