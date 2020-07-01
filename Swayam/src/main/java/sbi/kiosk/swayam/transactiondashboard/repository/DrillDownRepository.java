package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.DrillDown;

@Repository
public interface DrillDownRepository extends CrudRepository<DrillDown, String> {
	
	@Query(value="SELECT * FROM VW_DRILL_DOWN",nativeQuery=true)
	
	Page<DrillDown> findAll(Pageable pageable);
	
    @Query(value="SELECT * FROM VW_DRILL_DOWN where CRCL_NAME=:circleName",nativeQuery=true)

    Page<DrillDown> findAllByCircle(Pageable pageable, @Param("circleName") String circleName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where NETWORK=:networkName",nativeQuery=true)

    Page<DrillDown> findAllByNetwork(Pageable pageable, @Param("networkName") String networkName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where MODULE=:moduleName",nativeQuery=true)

    Page<DrillDown> findAllByModule(Pageable pageable, @Param("moduleName") String moduleName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where REGION=:regionName",nativeQuery=true)

    Page<DrillDown> findAllByRegion(Pageable pageable, @Param("regionName") String regionName);
    
    
    @Query(value="SELECT (CASE when :in_circle_code is null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null THEN b.crcl_name "+
            "when :in_circle_code is not null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null THEN b.network "+
            "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is null AND :in_region_code is null THEN b.module "+
            "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is null THEN b.region "+
            "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is not null THEN b.branch_name end) NAME, "+					
            "count(b.branch_code), sum(m.total_swayam_kiosks), sum(m.lipi_kiosk_cnt), "+
            "sum(m.lipi_txn_cnt),sum(m.FORBES_kiosk_cnt),sum(m.FORBES_txn_cnt),sum(m.cms_kiosk_cnt), "+
            "sum(m.cms_txn_cnt),sum(m.total_swayam_txns),sum(m.manual_txns), "+
            "round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) mig_prcnt "+
        "FROM (   "+
        "select branch_code, "+
               "count(KIOSK_ID) total_swayam_kiosks, "+
               "sum(case vendor when 'LIPI' then 1 else 0 end) lipi_kiosk_cnt, "+
               "sum(case vendor when 'LIPI' then swayam_txns else 0 end) lipi_txn_cnt, "+
               "sum(case vendor when 'FORBES' then 1 else 0 end) FORBES_kiosk_cnt, "+
               "sum(case vendor when 'FORBES' then swayam_txns else 0 end) FORBES_txn_cnt, "+
               "sum(case vendor when 'CMS' then 1 else 0 end) cms_kiosk_cnt, "+
               "sum(case vendor when 'CMS' then swayam_txns else 0 end) cms_txn_cnt, "+
               "sum(swayam_txns) total_swayam_txns, "+
               "nvl(sum(no_of_accounts),0) manual_txns "+              
        "from (select mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor, "+
                     "count(1) no_of_kiosks, nvl(sum(dtl.no_of_txns),0) swayam_txns "+
                "from tbl_kiosk_master mst, "+
                     "tbl_swayam_txn_report dtl "+
               "where mst.kiosk_id = dtl.kiosk_id(+) "+
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
        "where data1.branch_code = data2.branch_no(+) "+
        "group by data1.branch_code) m, tbl_branch_master b "+
        "WHERE b.branch_code = m.branch_code "+        
        "and ((:in_circle_code is null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null) OR "+
            "(:in_circle_code is not null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null and b.crcl_code=:in_circle_code) OR "+
            "(:in_circle_code is not null AND :in_network_code is not null AND :in_module_code is null AND :in_region_code is null and b.crcl_code=:in_circle_code and b.network=:in_network_code) OR "+
            "(:in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is null and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code) OR "+
            "(:in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is not null and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code and b.region=:in_region_code)) "+       
        "GROUP BY (CASE WHEN :in_circle_code is null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null THEN b.crcl_name "+
             "when :in_circle_code is not null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null THEN b.network "+
             "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is null AND :in_region_code is null THEN b.module "+
             "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is null THEN b.region "+
             "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is not null THEN b.branch_name END)", 
             countQuery ="SELECT count(m.BRANCH_CODE) "+
               "FROM (  "+
               "select branch_code, "+
               "count(KIOSK_ID) total_swayam_kiosks, "+
               "sum(case vendor when 'LIPI' then 1 else 0 end) lipi_kiosk_cnt, "+
               "sum(case vendor when 'LIPI' then swayam_txns else 0 end) lipi_txn_cnt, "+
               "sum(case vendor when 'FORBES' then 1 else 0 end) FORBES_kiosk_cnt, "+
               "sum(case vendor when 'FORBES' then swayam_txns else 0 end) FORBES_txn_cnt, "+
               "sum(case vendor when 'CMS' then 1 else 0 end) cms_kiosk_cnt, "+
               "sum(case vendor when 'CMS' then swayam_txns else 0 end) cms_txn_cnt, "+
               "sum(swayam_txns) total_swayam_txns, "+
               "nvl(sum(no_of_accounts),0) manual_txns "+
        "from (select mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor, "+
                     "count(1) no_of_kiosks, nvl(sum(dtl.no_of_txns),0) swayam_txns "+
                "from tbl_kiosk_master mst, "+
                     "tbl_swayam_txn_report dtl "+
               "where mst.kiosk_id = dtl.kiosk_id(+) "+
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
               "where data1.branch_code = data2.branch_no(+) "+
               "group by data1.branch_code) m, tbl_branch_master b "+
               "WHERE b.branch_code = m.branch_code "+        
               "and ((:in_circle_code is null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null) OR "+
            "(:in_circle_code is not null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null and b.crcl_code=:in_circle_code) OR "+
            "(:in_circle_code is not null AND :in_network_code is not null AND :in_module_code is null AND :in_region_code is null and b.crcl_code=:in_circle_code and b.network=:in_network_code) OR "+
            "(:in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is null and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code) OR "+
            "(:in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is not null and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code and b.region=:in_region_code)) "+       
        "GROUP BY (CASE WHEN :in_circle_code is null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null THEN b.crcl_name "+
             "when :in_circle_code is not null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null THEN b.network "+
             "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is null AND :in_region_code is null THEN b.module "+
             "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is null THEN b.region "+
             "when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is not null THEN b.branch_name END),"
             + " (CASE when :in_circle_code is null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null THEN b.crcl_code"
             + "   when :in_circle_code is not null AND :in_network_code is null AND :in_module_code is null AND :in_region_code is null THEN b.network"
             + "  when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is null AND :in_region_code is null THEN b.mod_code "
             + " when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is null THEN b.region"
             + "   when :in_circle_code is not null AND :in_network_code is not null AND :in_module_code is not null AND :in_region_code is not null THEN b.branch_name end) ",  
             nativeQuery=true)
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,@Param("in_network_code") String in_network_code,@Param("in_region_code") String in_region_code,@Param("in_module_code") String in_module_code,Pageable pageable);
}
