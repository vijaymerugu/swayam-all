create or replace PROCEDURE SP_MIGRATION_SUMMARY_PROC(
    fromDate_param      IN VARCHAR2,
    toDate_param        IN VARCHAR2,
    cur OUT SYS_REFCURSOR )
AS
    fromDate VARCHAR2(20);
    toDate VARCHAR2(20);
BEGIN
    IF fromdate_param IS NULL and toDate_param is NULL THEN
        select  to_char(trunc(SYSDATE-2),'dd-mm-yyyy') into fromdate from dual;
        select  to_char(trunc(SYSDATE-2),'dd-mm-yyyy') into toDate from dual;
    ELSE
        fromDate:= fromDate_param;
        toDate:=  toDate_param;
    END IF;
  
    OPEN cur FOR

    with main_data as (  
        select branch_code,
               count(KIOSK_ID) total_swayam_kiosks,
               sum(case vendor when 'LIPI' then 1 else 0 end) lipi_kiosk_cnt,
               sum(case vendor when 'LIPI' then swayam_txns else 0 end) lipi_txn_cnt,
               sum(case vendor when 'FORBES' then 1 else 0 end) FORBES_kiosk_cnt,
               sum(case vendor when 'FORBES' then swayam_txns else 0 end) FORBES_txn_cnt,
               sum(case vendor when 'CMS' then 1 else 0 end) cms_kiosk_cnt,
               sum(case vendor when 'CMS' then swayam_txns else 0 end) cms_txn_cnt,
               sum(swayam_txns) total_swayam_txns,
               nvl(sum(no_of_accounts),0) manual_txns
        from (select mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor, 
                     count(1) no_of_kiosks, nvl(sum(dtl.no_of_txns),0) swayam_txns 
                from tbl_kiosk_master mst, 
                     tbl_swayam_txn_report dtl
               where mst.kiosk_id = dtl.kiosk_id(+)
                and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(fromDate, 'dd-mm-yyyy')) 
                                                 and trunc(to_date(toDate, 'dd-mm-yyyy'))
               group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,
               tbl_branch_txn_daily data2
        where data1.branch_code = data2.branch_no(+)
        group by data1.branch_code) 

        select b.crcl_name,b.network,b.module,b.region,b.branch_code,b.branch_name,
               sum(m.lipi_kiosk_cnt),sum(m.lipi_txn_cnt),sum(m.FORBES_kiosk_cnt),sum(m.FORBES_txn_cnt),
               sum(m.cms_kiosk_cnt),sum(m.cms_txn_cnt),sum(m.total_swayam_txns),sum(m.manual_txns),
               round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) mig_prcnt
        from main_data m, tbl_branch_master b
        where b.branch_code = m.branch_code
        group by b.crcl_name,b.network,b.module,b.region,b.branch_code,b.branch_name;

END SP_MIGRATION_SUMMARY_PROC;