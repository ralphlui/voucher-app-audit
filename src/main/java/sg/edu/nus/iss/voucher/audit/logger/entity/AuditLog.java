package sg.edu.nus.iss.voucher.audit.logger.entity;

import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuditLog {
	
	public AuditLog() {
		super();
	}
	
	@Id
	@UuidGenerator(style = UuidGenerator.Style.AUTO)
	private Integer auditId;
	
	@Column(nullable = true)
	private String errorCode;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = true)
	private String activityType;
	
	@Column(nullable = true)
	private String activityDescription;
	
	@Column(nullable = true)
	private String requestActionEndpoint;
	
	@Column(nullable = true)
	private String responseStatus;
	
	@Column(nullable = true)
	private String requestType;
	
	@Column(nullable = true, columnDefinition = "datetime default now()")
	private Date insertDate;
	
	@Column(nullable = true, columnDefinition = "datetime default now()")
	private Date lastupdatedDate;
	
	@Column(nullable = true)
	private String remarks;
	
}
