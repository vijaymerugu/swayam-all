/**
 * 
 */
package sbi.kiosk.swayam.notify.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbi.kiosk.swayam.common.entity.Notify;
import sbi.kiosk.swayam.notify.repository.NotifyRepository;

/**
 * @author vmph2371481
 *
 */
@Service
public class NotifyServiceImpl implements NotifyService {
	
	Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);

	@Autowired
	NotifyRepository notifyRepository;
	
	@Override
	public List<Notify> getNotifyListByUserId(String userId) {
		try {
		return notifyRepository.findByUserId(userId);
		} catch (Exception e) {
			logger.error("Exception in NotifyServiceImpl:" + e.getMessage());
			return new ArrayList<Notify>();
		}
	}

	@Override
	public Notify updateNotificationStatus(Integer notifyId, String status) {
		Optional<Notify> notifyObj = notifyRepository.findById(notifyId);
		if(notifyObj.isPresent()) {
			notifyObj.get().setStatus(status);
			notifyObj.get().setUpdatedDate(new Date());
			notifyRepository.save(notifyObj.get());
		}
		return notifyObj.get();
	}
}
