/**
 * 
 */
package sbi.kiosk.swayam.misreports.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.MISGroupingCriteria;

/**
 * @author vmph2371481
 *
 */
@Repository
public interface MISGroupingCriteriaRepository extends CrudRepository<MISGroupingCriteria, Integer>{
	

}
