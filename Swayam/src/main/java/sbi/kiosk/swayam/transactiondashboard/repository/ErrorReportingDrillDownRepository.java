package sbi.kiosk.swayam.transactiondashboard.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.DrillDown;
import sbi.kiosk.swayam.common.entity.ErrorReportingDrillDown;

@Repository
public interface ErrorReportingDrillDownRepository
		extends PagingAndSortingRepository<ErrorReportingDrillDown, Serializable> {

	
/*	
	@Query(value =" SELECT  b.crcl_code   code, b.crcl_name  name, COUNT(b.branch_code) branch_code_count, "
			+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
			+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
			+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
			+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
			+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
			+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
			+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
			+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
			+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
			+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
			+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
			+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
			+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
			+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
			+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
			+ " ORDER BY  data1.branch_code    ) M ON b.branch_code = M.branch_code GROUP BY b.crcl_name, b.crcl_code ORDER BY"
			+ "  b.crcl_name ASC",nativeQuery = true,countQuery = " SELECT   count(b.crcl_code )  code, b.crcl_name  name, COUNT(b.branch_code) branch_code_count, "
					+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
					+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
					+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
					+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
					+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
					+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
					+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
					+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
					+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
					+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
					+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
					+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
					+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
					+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
					+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
					+ " ORDER BY  data1.branch_code    ) M ON b.branch_code = M.branch_code GROUP BY b.crcl_name, b.crcl_code ORDER BY"
					+ "  b.crcl_name ASC")
	
	*/
	
	@Query(value ="select bm.crcl_code AS CODE ,bm.crcl_name AS NAME,"
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') "
			+ " group by bm.crcl_code,bm.crcl_name ORDER BY   bm.crcl_name ASC",
			nativeQuery = true,countQuery = "select count(bm.crcl_code) as CODE "
					+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
					+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') "
					+ " group by bm.crcl_code,bm.crcl_name ORDER BY   bm.crcl_name ASC ")

	Page<ErrorReportingDrillDown> findByDate(@Param("fromdate") String fromdate, @Param("todate") String todate, Pageable pageable);

	/*
	@Query(value =" SELECT  b.network NAME, B.NETWORK CODE, COUNT(b.branch_code) branch_code_count, "
			+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
			+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
			+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
			+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
			+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
			+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
			+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
			+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
			+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
			+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
			+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
			+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
			+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
			+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
			+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
			+ " ORDER BY  data1.branch_code    ) M "
			+ " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code " + "	GROUP BY B.NETWORK "
			+ " ORDER BY   B.NETWORK ASC "
			,nativeQuery = true,countQuery = " SELECT count(B.NETWORK) AS CODE, b.network NAME, COUNT(b.branch_code) branch_code_count, "
					+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
					+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
					+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
					+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
					+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
					+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
					+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
					+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
					+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
					+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
					+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
					+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
					+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
					+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
					+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
					+ " ORDER BY  data1.branch_code    ) M "
					+ " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code " + "	GROUP BY B.NETWORK "
					+ " ORDER BY   B.NETWORK ASC ")
	
	*/
	
	@Query(value =" select bm.network as CODE,bm.network NAME ,"
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
			+ " group by bm.network,bm.network ORDER BY   bm.NETWORK ASC ",
			nativeQuery = true,countQuery = "select count(bm.network) as CODE "
					+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
					+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
					+ "	 group by bm.network,bm.network ORDER BY   bm.NETWORK AS")
	

	Page<ErrorReportingDrillDown> findByDate(@Param("fromdate") String fromdate, @Param("todate") String todate,
			@Param("in_circle_code") String in_circle_code, Pageable pageable);


	
	/*
	
	@Query(value =" SELECT  b.MODULE name,b.mod_code code,COUNT(b.branch_code) branch_code_count, "
			+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
			+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
			+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
			+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
			+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
			+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
			+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
			+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
			+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
			+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
			+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
			+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
			+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
			+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
			+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
			+ " ORDER BY  data1.branch_code    ) M "
			+ " ON B.BRANCH_CODE = M.BRANCH_CODE   " + " where b.crcl_code=:in_circle_code and "
			+ " b.network=:in_network_code " + " GROUP BY b.MODULE,b.mod_code"
			+ " ORDER BY b.MODULE "
			,countQuery = " SELECT count(b.mod_code) as code, b.MODULE name, COUNT(b.branch_code) branch_code_count, "
					+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
					+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
					+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
					+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
					+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
					+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
					+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
					+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
					+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
					+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
					+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
					+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
					+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
					+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
					+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
					+ " ORDER BY  data1.branch_code    ) M "
					+ " ON B.BRANCH_CODE = M.BRANCH_CODE   " + " where b.crcl_code=:in_circle_code and "
					+ " b.network=:in_network_code " + " GROUP BY b.MODULE,b.mod_code"
					+ " ORDER BY b.MODULE ", nativeQuery = true)
	
	*/
	
	
	@Query(value ="  select bm.mod_code CODE ,bm.MODULE as NAME,"
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
			+ " and bm.NETWORK=:in_network_code  group by bm.mod_code, bm.MODULE ORDER BY   bm.MODULE ASC ",
			nativeQuery = true,countQuery = "select count(bm.mod_code) as CODE "
					+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
					+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
					+ "	and bm.NETWORK=:in_network_code  group by bm.mod_code, bm.MODULE ORDER BY   bm.MODULE ASC ")
	
	

	Page<ErrorReportingDrillDown> findByDate(@Param("fromdate") String fromdate, @Param("todate") String todate,
			@Param("in_circle_code") String in_circle_code, @Param("in_network_code") String in_network_code,
			Pageable pageable);

	/*
	
	@Query(value =" SELECT b.region NAME,  b.region CODE,COUNT(b.branch_code) branch_code_count, "
			+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
			+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
			+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
			+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
			+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
			+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
			+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
			+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
			+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
			+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
			+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
			+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
			+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
			+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
			+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
			+ " ORDER BY  data1.branch_code    ) M "
			+ " ON B.BRANCH_CODE = M.BRANCH_CODE   "
			+ " WHERE b.branch_code = m.branch_code and "
			+ " b.crcl_code=:in_circle_code and b.network=:in_network_code and "
			+ " b.mod_code=:in_module_code  GROUP BY b.region , b.region  ORDER BY b.region "

			,countQuery = " SELECT count(b.region) CODE, b.region NAME, COUNT(b.branch_code) branch_code_count, "
					+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
					+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
					+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
					+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
					+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
					+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
					+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
					+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
					+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
					+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
					+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
					+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
					+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
					+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
					+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
					+ " ORDER BY  data1.branch_code    ) M "
					+ " ON B.BRANCH_CODE = M.BRANCH_CODE   "
					+ " WHERE b.branch_code = m.branch_code and "
					+ " b.crcl_code=:in_circle_code and b.network=:in_network_code and "
					+ " b.mod_code=:in_module_code  GROUP BY b.region , b.region " + " ORDER BY b.region ", nativeQuery = true)
	
	         */
	
	
	@Query(value ="  select bm.region CODE ,bm.region as NAME, "
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
			+ " and bm.NETWORK=:in_network_code and bm.mod_code=:in_module_code group by bm.region,bm.region ORDER BY   bm.region ASC ",
			nativeQuery = true,countQuery = "select count(bm.region) as CODE "
					+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
					+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
					+ "	and bm.NETWORK=:in_network_code and bm.mod_code=:in_module_code group by bm.region,bm.region ORDER BY   bm.region ASC  ")

	Page<ErrorReportingDrillDown> findByDate(@Param("fromdate") String fromdate, @Param("todate") String todate,
			@Param("in_circle_code") String in_circle_code, @Param("in_network_code") String in_network_code,
			@Param("in_module_code") String in_module_code, Pageable pageable);


	/*
	
	@Query(value =" SELECT  b.branch_name NAME, b.branch_code CODE,COUNT(b.branch_code) branch_code_count, "
			+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
			+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
			+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
			+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
			+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
			+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
			+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
			+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
			+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
			+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
			+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
			+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
			+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
			+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
			+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
			+ " ORDER BY  data1.branch_code    ) M "
			+ " ON B.BRANCH_CODE = M.BRANCH_CODE   "
			+ " WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
			+ "   b.mod_code=:in_module_code and b.region=:in_region_code  GROUP BY b.branch_name, "
			+ " b.branch_code  ORDER BY b.branch_name "

			,countQuery = " SELECT count(b.branch_code) as code, COUNT(b.branch_code) branch_code_count, "
					+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
					+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
					+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
					+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
					+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
					+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
					+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
					+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
					+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
					+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
					+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
					+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
					+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
					+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
					+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
					+ " ORDER BY  data1.branch_code    ) M "
					+ " ON B.BRANCH_CODE = M.BRANCH_CODE   "
					+ " WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
					+ "   b.mod_code=:in_module_code and b.region=:in_region_code  GROUP BY b.branch_name, "
					+ " b.branch_code  ORDER BY b.branch_name ", nativeQuery = true)
	
	*/
	
	
	
	@Query(value ="  select bm.branch_code CODE ,bm.branch_name as NAME, "
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
			+ " and bm.NETWORK=:in_network_code and bm.mod_code=:in_module_code  and bm.region=:in_region_code "
			+ " group by bm.branch_code,bm.branch_name ORDER BY   bm.branch_name ASC ",
			nativeQuery = true,countQuery = "select count(bm.branch_code) as CODE "
					+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
					+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
					+ "	and bm.NETWORK=:in_network_code and bm.mod_code=:in_module_code  and bm.region=:in_region_code "
					+ " group by bm.branch_code,bm.branch_name ORDER BY   bm.branch_name ASC  ")


	Page<ErrorReportingDrillDown> findByDate(@Param("fromdate") String fromdate, @Param("todate") String todate,
			@Param("in_circle_code") String in_circle_code, @Param("in_network_code") String in_network_code,
			@Param("in_module_code") String in_module_code, @Param("in_region_code") String in_region_code,
			Pageable pageable);

/*
	@Query(value =" SELECT  b.branch_name NAME, b.branch_code CODE,COUNT(b.branch_code) branch_code_count, "
			+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
			+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
			+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
			+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
			+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
			+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
			+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
			+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
			+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
			+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
			+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
			+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
			+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
			+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
			+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
			+ " ORDER BY  data1.branch_code    ) M "
			+ " ON B.BRANCH_CODE = M.BRANCH_CODE   "
			+ " WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
			+ "   b.mod_code=:in_module_code and b.region=:in_region_code and ( b.BRANCH_CODE=:searchText or b.branch_name = :searchText)  GROUP BY b.branch_name, "
			+ " b.branch_code  ORDER BY b.branch_name "

			,countQuery = " SELECT count(b.branch_code) as code, COUNT(b.branch_code) branch_code_count, "
					+ " SUM(m.total_swayam_kiosks) total_swayam_kiosks,  nvl(SUM(br_txn.manual_txns), 0) manual_txns,"
					+ " SUM(total_no_of_txn)  total_no_of_txn, SUM(total_no_of_succ_txns) total_no_of_succ_txns,"
					+ " SUM(no_of_fail_txns)  no_of_fail_txns,SUM(no_of_tech_fail_txns)  no_of_tech_fail_txns,"
					+ " SUM(no_of_business_fail_txns)   no_of_business_fail_txns	FROM  tbl_branch_master  b"
					+ " LEFT JOIN ( SELECT  branch_no, nvl(SUM(no_of_accounts), 0) manual_txns  FROM  tbl_branch_txn_daily data2"
					+ " WHERE to_date(last_pbk_dt, 'yyyy-mm-dd') BETWEEN trunc(to_date(:fromdate, 'dd-mm-yy')) AND trunc(to_date(:todate, 'dd-mm-yy')) "
					+ " GROUP BY branch_no  )  br_txn ON br_txn.branch_no = b.branch_code"
					+ "  INNER JOIN ( SELECT  branch_code, SUM(no_of_kiosks) total_swayam_kiosks, SUM(total_no_of_txn)   total_no_of_txn,"
					+ "  SUM(total_no_of_succ_txns)  total_no_of_succ_txns, SUM(no_of_fail_txns)  no_of_fail_txns, SUM(no_of_tech_fail_txns) "
					+ " no_of_tech_fail_txns, SUM(no_of_business_fail_txns)     no_of_business_fail_txns  FROM  (SELECT  mst.branch_code,"
					+ "  COUNT(DISTINCT mst.kiosk_id) no_of_kiosks, nvl(SUM(dtl.no_of_txns), 0)  total_no_of_txn, nvl(SUM(dtl.no_of_success_txns), 0) "
					+ "  total_no_of_succ_txns, nvl(SUM(dtl.no_of_fail), 0)  no_of_fail_txns, nvl(SUM(dtl.no_of_tech_fail), 0)  no_of_tech_fail_txns,"
					+ "  nvl(SUM(dtl.no_of_business_fail), 0)  no_of_business_fail_txns FROM  tbl_kiosk_master  mst, tbl_swayam_txn_report  dtl"
					+ "  WHERE  UPPER(mst.kiosk_id) = UPPER(dtl.kiosk_id(+))  AND txn_date BETWEEN TRUNC(TO_DATE(:fromdate, 'dd-mm-yy')) "
					+ " AND TRUNC(TO_DATE(:todate, 'dd-mm-yy')) GROUP BY mst.branch_code ORDER BY mst.branch_code ) data1 GROUP BY  data1.branch_code"
					+ " ORDER BY  data1.branch_code    ) M "
					+ " ON B.BRANCH_CODE = M.BRANCH_CODE   "
					+ " WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
					+ "   b.mod_code=:in_module_code and b.region=:in_region_code and ( b.BRANCH_CODE=:searchText or b.branch_name = :searchText) "
					+ " GROUP BY b.branch_name, "
					+ " b.branch_code  ORDER BY b.branch_name ", nativeQuery = true)
	
	*/
	
	@Query(value ="  select bm.branch_code CODE ,bm.branch_name as NAME, "
			+ " SUM(NO_OF_TXNS) AS No_of_Txns,"
			+ "	sum(txn.no_of_success_txns) as No_of_success_Txns,sum(txn.no_of_fail) as No_of_failure_Txns, "
			+ " sum(txn.no_of_tech_fail) as No_of_technical_failure_Txns, sum(txn.no_of_business_fail) as No_of_business_failure_Txns from "
			+ "	 tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
			+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
			+ " and bm.NETWORK=:in_network_code and bm.mod_code=:in_module_code  and bm.region=:in_region_code "
			+ " and ( bb.branch_code=:searchText or bm.branch_name = :searchText) "
			+ " group by bm.branch_code,bm.branch_name ORDER BY   bm.branch_name ASC ",
			nativeQuery = true,countQuery = "select count(bm.branch_code) as CODE "
					+ "	from  tbl_swayam_txn_report txn inner join tbl_branch_master bm on txn.branch_code=bm.branch_code "
					+ " where to_date(txn_date,'dd-mm-yy') BETWEEN TO_DATE(:fromdate, 'dd-mm-yy') AND TO_DATE(:todate, 'dd-mm-yy') and crcl_code=:in_circle_code "
					+ "	and bm.NETWORK=:in_network_code and bm.mod_code=:in_module_code  and bm.region=:in_region_code "
					+ " and ( bb.branch_code=:searchText or bm.branch_name = :searchText) "
					+ " group by bm.branch_code,bm.branch_name ORDER BY   bm.branch_name ASC  ")
	
	
	Page<ErrorReportingDrillDown> findByDateSearchNext(@Param("fromdate") String fromdate, @Param("todate") String todate,
			@Param("in_circle_code") String in_circle_code, @Param("in_network_code") String in_network_code,
			@Param("in_module_code") String in_module_code, @Param("in_region_code") String in_region_code,
			@Param("searchText") String searchText, Pageable pageable);
}
