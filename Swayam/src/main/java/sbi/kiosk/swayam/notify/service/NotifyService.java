/**
 * 
 */
package sbi.kiosk.swayam.notify.service;

import java.util.List;

import sbi.kiosk.swayam.common.entity.Notify;

/**
 * @author vmph2371481
 *
 */
public interface NotifyService {
	
	List<Notify> getNotifyListByUserId(String userId);
	
	Notify updateNotificationStatus(Integer notifyId, String status);

}
