package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class KioskRegistration implements Serializable {

	public KioskRegistration() {
		// TODO Auto-generated constructor stub
	}
	
	 @Id
	@Column(name = "CIRCLE")  
    private String circle;  
    
    @Column(name = "BRANCH_CODE")  
    private String branchCode;  
    
    @Column(name = "VENDOR")  
    private String vendor;  
    
    
    @Column(name = "KIOSK_ID")  
    private String kioskId;  
    
    @Column(name = "SERIAL_NO")  
    private String kioskSerialNo;  
    
    @Column(name = "MAC_ID")  
    private String kioskMacAddress;  
    
      	@Column(name = "KIOSK_IP")  
    private String kioskIp;  
    
    @Column(name = "RFP_ID")  
    private String refId;  
    
    @Column(name = "ASSIGNED_CMF")  
    private String username;  
    
     @Column(name = "ASSIGNED_CMF_PHONE_NO")  
    private String phoneNo; 

}
