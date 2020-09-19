/**
 * 
 */
package sbi.kiosk.swayam.notify.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sbi.kiosk.swayam.common.entity.Notify;

/**
 * @author vmph2371481
 *
 */
public interface NotifyRepository extends CrudRepository<Notify, Integer> {

	List<Notify> findByUserId(String userId);
	
}
