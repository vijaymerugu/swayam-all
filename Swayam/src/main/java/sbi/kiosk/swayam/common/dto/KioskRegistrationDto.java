package sbi.kiosk.swayam.common.dto;

import javax.persistence.Column;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.KioskRegistration;



@Data
public class KioskRegistrationDto {

	

	public KioskRegistrationDto() {
		  
	  }
	 
	public KioskRegistrationDto(KioskRegistration master){
		this.circle = master.getCircle();
		 this.branchCode = master.getBranchCode();  
	      
		    this.vendor = master.getVendor();  
		      
		    this.kioskId = master.getKioskId();  
		    
		    this.kioskSerialNo = master.getKioskSerialNo();  
		    
		    this.kioskMacAddress = master.getKioskMacAddress();  
		      
		    this.kioskIp = master.getKioskIp();  
		     
		    this.refId = master.getRefId();  
		  
		    this.username = master.getUsername();  
		   
		    this.phoneNo = master.getPhoneNo(); 

		
	}
	
    private String circle;  
      
    private String branchCode;  
      
    private String vendor;  
      
    private String kioskId;  
    
    private String kioskSerialNo;  
    
    private String kioskMacAddress;  
      
    private String kioskIp;  
     
    private String refId;  
  
    private String username;  
   
    private String phoneNo; 

 

}
