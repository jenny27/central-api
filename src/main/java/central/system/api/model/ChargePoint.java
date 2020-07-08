package central.system.api.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="chargePoint")
public class ChargePoint {

	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID id;
	
	@Column(name="identity", nullable=false)
	private String identity;
	
	@ManyToOne
	private Organization cpo;

	
	public ChargePoint(UUID id, String identity) {
		super();
		this.id = id;
		this.identity = identity;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Organization getCpo() {
		return cpo;
	}

	public void setCpo(Organization cpo) {
		this.cpo = cpo;
	}

	
}
