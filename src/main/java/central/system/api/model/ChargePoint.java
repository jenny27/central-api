package central.system.api.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private UUID chargeid;
	
	@Column(name="identity", nullable=false)
	private String identity;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id")
	private Organization cpo;

	
	public ChargePoint() {
		super();
	}


	public UUID getChargeid() {
		return chargeid;
	}


	public void setChargeid(UUID chargeid) {
		this.chargeid = chargeid;
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


	public ChargePoint(UUID chargeid, String identity, Organization cpo) {
		super();
		this.chargeid = chargeid;
		this.identity = identity;
		this.cpo = cpo;
	}
	
}
