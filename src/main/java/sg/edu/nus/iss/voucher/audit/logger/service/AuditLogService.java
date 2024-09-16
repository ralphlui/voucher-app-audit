package sg.edu.nus.iss.voucher.audit.logger.service;

import sg.edu.nus.iss.voucher.audit.logger.dto.AuditLogDTO;

public interface AuditLogService {
	
	//AuditLogDTO executeAuditLog(AuditLog auditLog);
	
	void executeAuditLog(AuditLogDTO auditLogDTO);

	//void executeAuditLog(AuditLog auditLog);

}
