package sg.edu.nus.iss.voucher.audit.logger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.voucher.audit.logger.dto.AuditLogDTO;
import sg.edu.nus.iss.voucher.audit.logger.service.AuditLogService;

@RestController
@Validated
@RequestMapping("/api/audit")
public class AuditLogController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuditLogController.class);
	
	@Autowired
	private AuditLogService auditLogService;
	
	@PostMapping("/execute")
    public ResponseEntity<String> addAuditLog(@RequestBody AuditLogDTO auditLogDTO) {
        auditLogService.executeAuditLog(auditLogDTO);
        return ResponseEntity.ok("Audit log saved successfully!");
    }

}
