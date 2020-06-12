package sbi.kiosk.swayam.common.dto;

import lombok.Data;

@Data
public class FileInfo {
	private String name;
    private String path;
 
    public FileInfo(String name, String path) {
        super();
        this.name = name;
        this.path = path;
    }
 

}
