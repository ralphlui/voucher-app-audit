package sg.edu.nus.iss.voucher.audit.logger.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditLogDTO {
	
	private String statusCode;
    private String userId;
    private String username;
    private String activityType;
    private String activityDescription;
    private String requestActionEndpoint;
    private String responseStatus;
    private String requestType;
    private String remarks;
	
	public AuditLogDTO() {
		
	}

}
