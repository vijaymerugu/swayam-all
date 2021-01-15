package sbi.kiosk.swayam.common.entity;


import java.io.Serializable;
import lombok.Data;


@Data
public class SwayamMigrationSummaryCompositeId implements Serializable {
	
	
		private static final long serialVersionUID = 1L;

	private String crclName;  
	  
    private String network;  
    
    private String module;  
 
    private String region;  
  
    private String branchCode;
	
	public SwayamMigrationSummaryCompositeId() {
		
	}

	public SwayamMigrationSummaryCompositeId(String crclName, String network, String module, String region,
			String branchCode) {
		super();
		this.crclName = crclName;
		this.network = network;
		this.module = module;
		this.region = region;
		this.branchCode = branchCode;
	}

}
