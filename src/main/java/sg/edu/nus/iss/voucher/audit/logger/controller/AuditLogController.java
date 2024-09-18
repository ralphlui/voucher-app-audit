package sg.edu.nus.iss.voucher.audit.logger.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.voucher.audit.logger.dto.*;
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
	
	@GetMapping(value = "/retrieveAllAuditLogs", produces = "application/json")
	public ResponseEntity<APIResponse<List<AuditLogDTO>>> getAllAuditLogs(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		logger.info("Retrieving all audit logs ...", page, size);

		try {
			Pageable pageable = PageRequest.of(page, size, Sort.by("lastupdatedDate").ascending());
			Map<Long, List<AuditLogDTO>> resultMap = auditLogService.retrieveAllAuditLogs(pageable);

				if (resultMap.isEmpty()) {
					String message = "No audit logs are existing.";
					logger.error(message);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error(message));
				}
	
				long totalRecord = resultMap.keySet().stream().findFirst().orElse(0L);
	
				List<AuditLogDTO> auditLogDTOList = resultMap.getOrDefault(totalRecord, new ArrayList<>());
	
				logger.info("Total records: {}", totalRecord);
				logger.info("AuditLogDTO List: {}", auditLogDTOList);
	
				if (auditLogDTOList.size() > 0) {
					return ResponseEntity.status(HttpStatus.OK).body(
							APIResponse.success(auditLogDTOList, "Successfully get all audit logs data.", totalRecord));
	
				} else {
					String message = "No audit logs found.";
					logger.error(message);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error(message));
				}

		} catch (Exception ex) {
			logger.error("An error occurred while processing getAllAuditLogs API.", ex);
			throw ex;
		}
	}

}
