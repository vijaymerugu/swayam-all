package sbi.kiosk.swayam.common.dto;

import lombok.Data;
import sbi.kiosk.swayam.common.entity.RequestResponse;

@Data
public class RequestResponseLogDto {

	public RequestResponseLogDto() {
		
	}
	
	public RequestResponseLogDto(RequestResponse reqRespDto) {
		this.id = reqRespDto.getId();
		this.request = reqRespDto.getRequest();
		this.response = reqRespDto.getResponse();
		this.success = reqRespDto.getSuccess();
		this.error = reqRespDto.getError();
		this.token = reqRespDto.getToken();
		this.url = reqRespDto.getUrl();
		this.userId = reqRespDto.getUserId();
	}

	private Integer id;
	private String userId;
	private String request;
	private String response;
	private String success;
	private String error;
	private String token;
	private String url;
	@Override
	public String toString() {
		return "RequestResponseLogDto [id=" + id + ", request=" + request + ", response=" + response + ", success="
				+ success + ", error=" + error + ", token=" + token + ", url=" + url + "]";
	}

}
