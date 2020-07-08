package central.system.api.model;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="organization")
public class Organization {
	
	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="legalEntity")
	private String legalEntity;

	public Organization() {
		super();
	}

	public Organization(String name, String legalEntity) {
		super();
		this.name = name;
		this.legalEntity = legalEntity;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", legalEntity=" + legalEntity + "]";
	}

	
}