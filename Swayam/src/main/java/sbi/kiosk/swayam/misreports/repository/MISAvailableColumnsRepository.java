/**
 * 
 */
package sbi.kiosk.swayam.misreports.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.MISAvailableColumns;

/**
 * @author vmph2371481
 *
 */
@Repository
public interface MISAvailableColumnsRepository extends CrudRepository<MISAvailableColumns, Integer>{

}
