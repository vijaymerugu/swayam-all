package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.DrillDown;

@Repository
public interface DrillDownRepository extends PagingAndSortingRepository<DrillDown, String> {
	
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
    
    
    @Query(value="SELECT b.crcl_name NAME, b.crcl_code CODE, "+			
            "count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, sum(m.lipi_kiosk_cnt) LIPI_KIOSK_COUNT, "+
            "sum(m.lipi_txn_cnt) LIPI_TXN_COUNT,sum(m.FORBES_kiosk_cnt) FORBES_KIOSK_COUNT,sum(m.FORBES_txn_cnt) FORBES_TXN_COUNT,sum(m.cms_kiosk_cnt) CMS_KIOSK_COUNT, "+
            "sum(m.cms_txn_cnt) CMS_TXN_COUNT,sum(m.total_swayam_txns) TOTAL_SWAYAM_TXNS,sum(m.manual_txns) BRANCH_TXNS, "+
            //"round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) mig_prcnt "+
            "(case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt "+
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
              // "where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
        "where data1.branch_code = data2.branch_no(+) "+
        "group by data1.branch_code) m, tbl_branch_master b "+
        "WHERE b.branch_code = m.branch_code "+        
        "GROUP BY b.crcl_name,b.crcl_code", 
             
             countQuery ="SELECT count(b.crcl_code) "+
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
              // "where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
               "where data1.branch_code = data2.branch_no(+) "+
               "group by data1.branch_code) m, tbl_branch_master b "+
               "WHERE b.branch_code = m.branch_code "+        
               "GROUP BY b.crcl_code",               
             nativeQuery=true)
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,Pageable pageable);
    
    @Query(value="SELECT b.network NAME, b.network CODE, "+			
            "count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, sum(m.lipi_kiosk_cnt) LIPI_KIOSK_COUNT, "+
            "sum(m.lipi_txn_cnt) LIPI_TXN_COUNT,sum(m.FORBES_kiosk_cnt) FORBES_KIOSK_COUNT,sum(m.FORBES_txn_cnt) FORBES_TXN_COUNT,sum(m.cms_kiosk_cnt) CMS_KIOSK_COUNT, "+
            "sum(m.cms_txn_cnt) CMS_TXN_COUNT,sum(m.total_swayam_txns) TOTAL_SWAYAM_TXNS,sum(m.manual_txns) BRANCH_TXNS, "+
           // "round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) mig_prcnt "+
           "(case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt "+
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
              // "where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
        "where data1.branch_code = data2.branch_no(+) "+
        "group by data1.branch_code) m, tbl_branch_master b "+
        "WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code "+
        "GROUP BY b.network",
             
             countQuery ="SELECT count(b.network) "+
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
               //"where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
               "where data1.branch_code = data2.branch_no(+) "+
               "group by data1.branch_code) m, tbl_branch_master b "+
               "WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code "+
               "GROUP BY b.network",
             nativeQuery=true)
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,Pageable pageable);

    @Query(value="SELECT b.MODULE NAME, b.mod_code CODE, "+			
            "count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, sum(m.lipi_kiosk_cnt) LIPI_KIOSK_COUNT, "+
            "sum(m.lipi_txn_cnt) LIPI_TXN_COUNT,sum(m.FORBES_kiosk_cnt) FORBES_KIOSK_COUNT,sum(m.FORBES_txn_cnt) FORBES_TXN_COUNT,sum(m.cms_kiosk_cnt) CMS_KIOSK_COUNT, "+
            "sum(m.cms_txn_cnt) CMS_TXN_COUNT,sum(m.total_swayam_txns) TOTAL_SWAYAM_TXNS,sum(m.manual_txns) BRANCH_TXNS, "+
           // "round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) mig_prcnt "+
           "(case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt "+
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
             //  "where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
        "where data1.branch_code = data2.branch_no(+) "+
        "group by data1.branch_code) m, tbl_branch_master b "+
        "WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code "+
        "GROUP BY b.MODULE,b.mod_code",
             
             countQuery ="SELECT count(b.mod_code) "+
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
              // "where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
               "where data1.branch_code = data2.branch_no(+) "+
               "group by data1.branch_code) m, tbl_branch_master b "+
               "WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code "+
               "GROUP BY b.mod_code",
             nativeQuery=true)
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,@Param("in_network_code") String in_network_code,Pageable pageable);
    
    
    @Query(value="SELECT b.region NAME, b.region CODE, "+			
            "count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, sum(m.lipi_kiosk_cnt) LIPI_KIOSK_COUNT, "+
            "sum(m.lipi_txn_cnt) LIPI_TXN_COUNT,sum(m.FORBES_kiosk_cnt) FORBES_KIOSK_COUNT,sum(m.FORBES_txn_cnt) FORBES_TXN_COUNT,sum(m.cms_kiosk_cnt) CMS_KIOSK_COUNT, "+
            "sum(m.cms_txn_cnt) CMS_TXN_COUNT,sum(m.total_swayam_txns) TOTAL_SWAYAM_TXNS,sum(m.manual_txns) BRANCH_TXNS, "+
            //"round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) mig_prcnt "+
            "(case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt "+
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
             //  "where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
        "where data1.branch_code = data2.branch_no(+) "+
        "group by data1.branch_code) m, tbl_branch_master b "+
        "WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code "+
        "GROUP BY b.region",
             
             countQuery ="SELECT count(b.region) "+
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
               //"where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
               "where data1.branch_code = data2.branch_no(+) "+
               "group by data1.branch_code) m, tbl_branch_master b "+
               "WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code "+
               "GROUP BY b.region",
             nativeQuery=true)
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,@Param("in_network_code") String in_network_code,@Param("in_module_code") String in_module_code,Pageable pageable);
    
    @Query(value="SELECT b.branch_name NAME, b.branch_name CODE, "+			
            "count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, sum(m.lipi_kiosk_cnt) LIPI_KIOSK_COUNT, "+
            "sum(m.lipi_txn_cnt) LIPI_TXN_COUNT,sum(m.FORBES_kiosk_cnt) FORBES_KIOSK_COUNT,sum(m.FORBES_txn_cnt) FORBES_TXN_COUNT,sum(m.cms_kiosk_cnt) CMS_KIOSK_COUNT, "+
            "sum(m.cms_txn_cnt) CMS_TXN_COUNT,sum(m.total_swayam_txns) TOTAL_SWAYAM_TXNS,sum(m.manual_txns) BRANCH_TXNS, "+
            "(case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt "+
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
               //"where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
        "where data1.branch_code = data2.branch_no(+) "+
        "group by data1.branch_code) m, tbl_branch_master b "+
        "WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code and b.region=:in_region_code "+
        "GROUP BY b.branch_name",
             
             countQuery ="SELECT count(b.branch_name) "+
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
             //  "where mst.kiosk_id = dtl.kiosk_id(+) "+
               " where upper(mst.kiosk_id) = upper(dtl.kiosk_id( +)) "+ 
                 "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1, "+
               "tbl_branch_txn_daily data2 "+
               "where data1.branch_code = data2.branch_no(+) "+
               "group by data1.branch_code) m, tbl_branch_master b "+
               "WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code and b.region=:in_region_code "+
               "GROUP BY b.branch_name",
             nativeQuery=true)
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,@Param("in_network_code") String in_network_code,@Param("in_module_code") String in_module_code,@Param("in_region_code") String in_region_code,Pageable pageable);

    
 // 12c
 		@Query(value="select to_date(last_pbk_dt,'yyyy-mm-dd') from tbl_branch_txn_daily order by last_pbk_dt desc fetch first 1 row only ",nativeQuery = true )
 		//for 11g
 		//@Query(value="select to_date(last_pbk_dt,'yyyy-mm-dd') from tbl_branch_txn_daily where rownum <= 1 order by last_pbk_dt desc ",nativeQuery = true )
 		String findCurrentDateAuditJob();
    
}
