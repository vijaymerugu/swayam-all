/**
 * 
 */
package sbi.kiosk.swayam.misreports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import sbi.kiosk.swayam.common.entity.MISAvailableColumns;

/**
 * @author vmph2371481
 *
 */
@Repository
public interface MISAvailableColumnsRepository extends CrudRepository<MISAvailableColumns, String> {

	List<MISAvailableColumns> findByColumnIdNotIn(List<String> removeIds);

}
