package sbi.kiosk.swayam.healthmonitoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.KioskBranchMaster;

@Repository
public interface KioskMasterRepo  extends CrudRepository<KioskBranchMaster,String>{
    
	List<KioskBranchMaster> findByBranchCode(String brachCode);

@Query(value="select vendor from tbl_kiosk_master   group by vendor",nativeQuery=true)
List<String> getByBranchCode(String brachCode);

@Query(value="select * from tbl_kiosk_master   where vendor=:vendor and branch_code=:branchcode",nativeQuery=true)
List<KioskBranchMaster> findByVendor(@Param("vendor")String vendor,@Param("branchcode")String branchcode);
	 
}

