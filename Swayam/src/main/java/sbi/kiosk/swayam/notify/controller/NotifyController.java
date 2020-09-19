/**
 * 
 */
package sbi.kiosk.swayam.notify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbi.kiosk.swayam.common.entity.Notify;
import sbi.kiosk.swayam.notify.service.NotifyService;

/**
 * @author vmph2371481
 *
 */
@RestController
public class NotifyController {
	
	@Autowired
	NotifyService notifyService;
	
	@RequestMapping(value="notify/get", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Notify>> getNotifyListByUserId(String userId){
		ResponseEntity<List<Notify>> responseEntity = ResponseEntity.ok(notifyService.getNotifyListByUserId(userId));
		return responseEntity;
	}

	@RequestMapping(value="notify/update", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Notify> updateNotificationStatus(Integer notifyId, String status){
		ResponseEntity<Notify> responseEntity = ResponseEntity.ok(notifyService.updateNotificationStatus(notifyId, status));
		return responseEntity;
	}
}
