package sbi.kiosk.swayam.common.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;
import sbi.kiosk.swayam.common.dto.KioskRegistrationDto;

@SuppressWarnings("serial")
@Data
@Entity
@IdClass(value = KioskRegistrationCompositeId.class)
public class KioskRegistration implements Serializable {

	public KioskRegistration() {
		// TODO Auto-generated constructor stub
	}
	public KioskRegistration(KioskRegistrationDto dto) {
		
		this.circle = dto.getCircle();
		this.branchCode = dto.getBranchCode();
		this.vendor = dto.getVendor();
		this.kioskId = dto.getKioskId();
		this.kioskIp = dto.getKioskIp();
		this.kioskMacAddress = dto.getKioskMacAddress();
		this.kioskSerialNo = dto.getKioskSerialNo();
		this.refId = dto.getRefId();
		this.username = dto.getUsername();
		this.phoneNo = dto.getPhoneNo();
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
    
    @Column(name = "USERNAME")  
    private String username;  
    
     @Column(name = "PHONENO")  
    private String phoneNo; 

}
