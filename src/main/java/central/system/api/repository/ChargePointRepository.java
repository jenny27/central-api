package central.system.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import central.system.api.model.ChargePoint;

@Repository
public interface ChargePointRepository extends JpaRepository<ChargePoint, UUID>{
	
}
