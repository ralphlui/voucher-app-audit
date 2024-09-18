package sg.edu.nus.iss.voucher.audit.logger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.voucher.audit.logger.entity.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {

	//Page<AuditLog> retrieveAuditLogWith(Pageable pageable);
	
	@Query("SELECT c FROM AuditLog c")
    Page<AuditLog> retrieveAuditLogWith(Pageable pageable);
	

}
