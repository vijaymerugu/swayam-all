package sbi.kiosk.swayam.transactiondashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.SwayamMigrationSummary;

@Repository
public interface TransactionDashBoardRepositoryPaging extends PagingAndSortingRepository<SwayamMigrationSummary, String>{

	Page<SwayamMigrationSummary> findAll(Pageable pageable);
	
	@Query(value="select b.crcl_name CRCL_NAME,b.network NETWORK,b.module MODULE,b.region REGION,b.branch_code BRANCH_CODE,b.branch_name BRANCH_NAME,"+
               "sum(m.lipi_kiosk_cnt) lipi_kiosk_cnt,sum(m.lipi_txn_cnt) lipi_txn_cnt,sum(m.FORBES_kiosk_cnt) FORBES_kiosk_cnt,sum(m.FORBES_txn_cnt) FORBES_txn_cnt,"+
               "sum(m.cms_kiosk_cnt) cms_kiosk_cnt,sum(m.cms_txn_cnt) cms_txn_cnt,sum(m.total_swayam_txns) total_swayam_txns,sum(m.manual_txns) manual_txns,"+
               " (case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt "+
        "from (select branch_code,"+
               "count(KIOSK_ID) total_swayam_kiosks,"+
               "sum(case vendor when 'LIPI' then 1 else 0 end) lipi_kiosk_cnt,"+
               "sum(case vendor when 'LIPI' then swayam_txns else 0 end) lipi_txn_cnt,"+
               "sum(case vendor when 'FORBES' then 1 else 0 end) FORBES_kiosk_cnt,"+
               "sum(case vendor when 'FORBES' then swayam_txns else 0 end) FORBES_txn_cnt,"+
               "sum(case vendor when 'CMS' then 1 else 0 end) cms_kiosk_cnt,"+
               "sum(case vendor when 'CMS' then swayam_txns else 0 end) cms_txn_cnt,"+
               "sum(swayam_txns) total_swayam_txns,"+
               "nvl(sum(no_of_accounts),0) manual_txns "+
        "from (select mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor,"+ 
                     "count(1) no_of_kiosks, nvl(sum(dtl.no_of_txns),0) swayam_txns "+ 
                "from tbl_kiosk_master mst,"+ 
                     "tbl_swayam_txn_report dtl "+
               "where mst.kiosk_id = dtl.kiosk_id(+) "+
                "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+ 
                                                 "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
               "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,"+
               "tbl_branch_txn_daily data2 "+
        "where data1.branch_code = data2.branch_no(+) "+
        "group by data1.branch_code) m, tbl_branch_master b "+
        "where b.branch_code = m.branch_code "+
        "group by b.crcl_name,b.network,b.module,b.region,b.branch_code,b.branch_name",nativeQuery=true,countQuery = " select count(m.branch_code)\r\n" + 
        		"        from (  \r\n" + 
        		"        select branch_code,\r\n" + 
        		"               count(KIOSK_ID) total_swayam_kiosks,\r\n" + 
        		"               sum(case vendor when 'LIPI' then 1 else 0 end) lipi_kiosk_cnt,\r\n" + 
        		"               sum(case vendor when 'LIPI' then swayam_txns else 0 end) lipi_txn_cnt,\r\n" + 
        		"               sum(case vendor when 'FORBES' then 1 else 0 end) FORBES_kiosk_cnt,\r\n" + 
        		"               sum(case vendor when 'FORBES' then swayam_txns else 0 end) FORBES_txn_cnt,\r\n" + 
        		"               sum(case vendor when 'CMS' then 1 else 0 end) cms_kiosk_cnt,\r\n" + 
        		"               sum(case vendor when 'CMS' then swayam_txns else 0 end) cms_txn_cnt,\r\n" + 
        		"               sum(swayam_txns) total_swayam_txns,\r\n" + 
        		"               nvl(sum(no_of_accounts),0) manual_txns\r\n" + 
        		"        from (select mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor,\r\n" + 
        		"                     count(1) no_of_kiosks, nvl(sum(dtl.no_of_txns),0) swayam_txns\r\n" + 
        		"                from tbl_kiosk_master mst,\r\n" + 
        		"                     tbl_swayam_txn_report dtl\r\n" + 
        		"               where mst.kiosk_id = dtl.kiosk_id(+)\r\n" + 
        		"                and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy'))\r\n" + 
        		"                                                 and trunc(to_date(:todate, 'dd-mm-yyyy'))\r\n" + 
        		"               group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,\r\n" + 
        		"               tbl_branch_txn_daily data2\r\n" + 
        		"        where data1.branch_code = data2.branch_no(+)\r\n" + 
        		"        group by data1.branch_code) m, tbl_branch_master b\r\n" + 
        		"        where b.branch_code = m.branch_code\r\n" + 
        		"        group by b.crcl_name,b.network,b.module,b.region,b.branch_code,b.branch_name")
	Page<SwayamMigrationSummary> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,Pageable pageable);

	
	@Query(value="select b.crcl_name CRCL_NAME,b.network NETWORK,b.module MODULE,b.region REGION,b.branch_code BRANCH_CODE,b.branch_name BRANCH_NAME,"+
            "sum(m.lipi_kiosk_cnt) lipi_kiosk_cnt,sum(m.lipi_txn_cnt) lipi_txn_cnt,sum(m.FORBES_kiosk_cnt) FORBES_kiosk_cnt,sum(m.FORBES_txn_cnt) FORBES_txn_cnt,"+
            "sum(m.cms_kiosk_cnt) cms_kiosk_cnt,sum(m.cms_txn_cnt) cms_txn_cnt,sum(m.total_swayam_txns) total_swayam_txns,sum(m.manual_txns) manual_txns,"+
            " (case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt "+
     "from (select branch_code,"+
            "count(KIOSK_ID) total_swayam_kiosks,"+
            "sum(case vendor when 'LIPI' then 1 else 0 end) lipi_kiosk_cnt,"+
            "sum(case vendor when 'LIPI' then swayam_txns else 0 end) lipi_txn_cnt,"+
            "sum(case vendor when 'FORBES' then 1 else 0 end) FORBES_kiosk_cnt,"+
            "sum(case vendor when 'FORBES' then swayam_txns else 0 end) FORBES_txn_cnt,"+
            "sum(case vendor when 'CMS' then 1 else 0 end) cms_kiosk_cnt,"+
            "sum(case vendor when 'CMS' then swayam_txns else 0 end) cms_txn_cnt,"+
            "sum(swayam_txns) total_swayam_txns,"+
            "nvl(sum(no_of_accounts),0) manual_txns "+
     "from (select mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor,"+ 
                  "count(1) no_of_kiosks, nvl(sum(dtl.no_of_txns),0) swayam_txns "+ 
             "from tbl_kiosk_master mst,"+ 
                  "tbl_swayam_txn_report dtl "+
            "where mst.kiosk_id = dtl.kiosk_id(+) "+
             "and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy')) "+ 
                                              "and trunc(to_date(:todate, 'dd-mm-yyyy')) "+
            "group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,"+
            "tbl_branch_txn_daily data2 "+
     "where data1.branch_code = data2.branch_no(+) "+
     "group by data1.branch_code) m, tbl_branch_master b "+
     "where b.branch_code = m.branch_code "+
     "group by b.crcl_name,b.network,b.module,b.region,b.branch_code,b.branch_name",nativeQuery=true,countQuery = " select count(m.branch_code)\r\n" + 
     		"        from (  \r\n" + 
     		"        select branch_code,\r\n" + 
     		"               count(KIOSK_ID) total_swayam_kiosks,\r\n" + 
     		"               sum(case vendor when 'LIPI' then 1 else 0 end) lipi_kiosk_cnt,\r\n" + 
     		"               sum(case vendor when 'LIPI' then swayam_txns else 0 end) lipi_txn_cnt,\r\n" + 
     		"               sum(case vendor when 'FORBES' then 1 else 0 end) FORBES_kiosk_cnt,\r\n" + 
     		"               sum(case vendor when 'FORBES' then swayam_txns else 0 end) FORBES_txn_cnt,\r\n" + 
     		"               sum(case vendor when 'CMS' then 1 else 0 end) cms_kiosk_cnt,\r\n" + 
     		"               sum(case vendor when 'CMS' then swayam_txns else 0 end) cms_txn_cnt,\r\n" + 
     		"               sum(swayam_txns) total_swayam_txns,\r\n" + 
     		"               nvl(sum(no_of_accounts),0) manual_txns\r\n" + 
     		"        from (select mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor,\r\n" + 
     		"                     count(1) no_of_kiosks, nvl(sum(dtl.no_of_txns),0) swayam_txns\r\n" + 
     		"                from tbl_kiosk_master mst,\r\n" + 
     		"                     tbl_swayam_txn_report dtl\r\n" + 
     		"               where mst.kiosk_id = dtl.kiosk_id(+)\r\n" + 
     		"                and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromdate, 'dd-mm-yyyy'))\r\n" + 
     		"                                                 and trunc(to_date(:todate, 'dd-mm-yyyy'))\r\n" + 
     		"               group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,\r\n" + 
     		"               tbl_branch_txn_daily data2\r\n" + 
     		"        where data1.branch_code = data2.branch_no(+)\r\n" + 
     		"        group by data1.branch_code) m, tbl_branch_master b\r\n" + 
     		"        where b.branch_code = m.branch_code\r\n" + 
     		"        group by b.crcl_name,b.network,b.module,b.region,b.branch_code,b.branch_name")
	List<SwayamMigrationSummary> findAllByDate(@Param("fromdate") String fromdate,@Param("todate") String todate);

}
