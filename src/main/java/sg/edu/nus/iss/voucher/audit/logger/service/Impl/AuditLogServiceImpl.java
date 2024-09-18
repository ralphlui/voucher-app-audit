package sg.edu.nus.iss.voucher.audit.logger.service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.voucher.audit.logger.dto.AuditLogDTO;
import sg.edu.nus.iss.voucher.audit.logger.entity.AuditLog;
import sg.edu.nus.iss.voucher.audit.logger.repository.AuditLogRepository;
import sg.edu.nus.iss.voucher.audit.logger.service.AuditLogService;
import sg.edu.nus.iss.voucher.audit.logger.util.DTOMapper;

@Service
public class AuditLogServiceImpl implements AuditLogService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuditLogService.class);
	
	@Autowired
	private AuditLogRepository auditLogRepository;

	
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
	
	public Map<Long, List<AuditLogDTO>> retrieveAllAuditLogs(Pageable pageable) {
		logger.info("Retrieving all audit trail data...");
		Map<Long, List<AuditLogDTO>> result = new HashMap<>();
		List<AuditLogDTO> auditLogDTOList = new ArrayList<>();

		Page<AuditLog> auditPages = auditLogRepository.retrieveAuditLogWith(pageable);
		long totalRecord = auditPages.getTotalElements();

		if (totalRecord > 0) {
			for (AuditLog auditLog : auditPages.getContent()) {
				
				AuditLogDTO auditLogDTO = DTOMapper.toauditLogDTO(auditLog);
				auditLogDTOList.add(auditLogDTO);
			}

		} else {
			logger.info("There is no audit log...");
		}

		result.put(totalRecord, auditLogDTOList);

		return result;
	}

	

}
