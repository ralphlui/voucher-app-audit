package sg.edu.nus.iss.voucher.audit.logger.service.Impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.voucher.audit.logger.dto.AuditLogDTO;
import sg.edu.nus.iss.voucher.audit.logger.entity.AuditLog;
import sg.edu.nus.iss.voucher.audit.logger.repository.AuditLogRepository;
import sg.edu.nus.iss.voucher.audit.logger.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuditLogService.class);
	
	@Autowired
	private AuditLogRepository auditLogRepository;

//	@Override
//	public AuditLogDTO executeAuditLog(AuditLog auditLog) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
    public void executeAuditLog(AuditLogDTO auditLogDTO) {
        AuditLog auditLog = new AuditLog();
        auditLog.setStatusCode(auditLogDTO.getStatusCode());
        auditLog.setUserId(auditLogDTO.getUserId());
        auditLog.setUsername(auditLogDTO.getUsername());
        auditLog.setActivityType(auditLogDTO.getActivityType());
        auditLog.setActivityDescription(auditLogDTO.getActivityDescription());
        auditLog.setRequestActionEndpoint(auditLogDTO.getRequestActionEndpoint());
        auditLog.setResponseStatus(auditLogDTO.getResponseStatus());
        auditLog.setRequestType(auditLogDTO.getRequestType());

        auditLog.setInsertDate(new Date());
        auditLog.setLastupdatedDate(new Date());
        
        auditLog.setRemarks(auditLogDTO.getRemarks());

        auditLogRepository.save(auditLog);

        auditLogRepository.save(auditLog);
    }
	
//	@Override
//    public void executeAuditLog(AuditLog auditLog) {
//        auditLogRepository.save(auditLog);
//    }

	

}
