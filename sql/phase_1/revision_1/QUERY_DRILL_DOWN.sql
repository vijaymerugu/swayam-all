---------------------------------------START-------------------------------------  
        --1----Circle--------Default-----Pass toDate And FromDate as 2 days past---------------------
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
                 and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromDate, 'dd-mm-yyyy'))
                                                 and trunc(to_date(:toDate, 'dd-mm-yyyy'))
               group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,
               tbl_branch_txn_daily data2
        where data1.branch_code = data2.branch_no(+)
        group by data1.branch_code)
       
        SELECT b.crcl_name, count(b.branch_code), sum(m.total_swayam_kiosks), sum(m.lipi_kiosk_cnt),
            sum(m.lipi_txn_cnt),sum(m.FORBES_kiosk_cnt),sum(m.FORBES_txn_cnt),sum(m.cms_kiosk_cnt),
            sum(m.cms_txn_cnt),sum(m.total_swayam_txns),sum(m.manual_txns),
            (case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt
        FROM main_data m, tbl_branch_master b
        WHERE b.branch_code = m.branch_code
        GROUP BY b.crcl_name;
       
        --2---Network-----------------------------------
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
                 and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromDate, 'dd-mm-yyyy'))
                                                 and trunc(to_date(:toDate, 'dd-mm-yyyy'))
               group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,
               tbl_branch_txn_daily data2
        where data1.branch_code = data2.branch_no(+)
        group by data1.branch_code)
       
        SELECT b.network, count(b.branch_code), sum(m.total_swayam_kiosks), sum(m.lipi_kiosk_cnt),
            sum(m.lipi_txn_cnt),sum(m.FORBES_kiosk_cnt),sum(m.FORBES_txn_cnt),sum(m.cms_kiosk_cnt),
            sum(m.cms_txn_cnt),sum(m.total_swayam_txns),sum(m.manual_txns),
            (case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt
        FROM main_data m, tbl_branch_master b
        WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code
        GROUP BY b.network;
       
        --3---Module-----------------------------------
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
                 and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromDate, 'dd-mm-yyyy'))
                                                 and trunc(to_date(:toDate, 'dd-mm-yyyy'))
               group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,
               tbl_branch_txn_daily data2
        where data1.branch_code = data2.branch_no(+)
        group by data1.branch_code)
       
        SELECT b.mod_code, count(b.branch_code), sum(m.total_swayam_kiosks), sum(m.lipi_kiosk_cnt),
            sum(m.lipi_txn_cnt),sum(m.FORBES_kiosk_cnt),sum(m.FORBES_txn_cnt),sum(m.cms_kiosk_cnt),
            sum(m.cms_txn_cnt),sum(m.total_swayam_txns),sum(m.manual_txns),
            (case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt
        FROM main_data m, tbl_branch_master b
        WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code
        GROUP BY b.mod_code;
       
        --4---Region-----------------------------------
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
                 and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromDate, 'dd-mm-yyyy'))
                                                 and trunc(to_date(:toDate, 'dd-mm-yyyy'))
               group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,
               tbl_branch_txn_daily data2
        where data1.branch_code = data2.branch_no(+)
        group by data1.branch_code)
       
        SELECT b.region, count(b.branch_code), sum(m.total_swayam_kiosks), sum(m.lipi_kiosk_cnt),
            sum(m.lipi_txn_cnt),sum(m.FORBES_kiosk_cnt),sum(m.FORBES_txn_cnt),sum(m.cms_kiosk_cnt),
            sum(m.cms_txn_cnt),sum(m.total_swayam_txns),sum(m.manual_txns),
            (case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt
        FROM main_data m, tbl_branch_master b
        WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code
        GROUP BY b.region;
       
        --5---Branch-----------------------------------
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
                 and to_date(txn_date, 'dd-mm-yyyy') between trunc(to_date(:fromDate, 'dd-mm-yyyy'))
                                                 and trunc(to_date(:toDate, 'dd-mm-yyyy'))
               group by mst.BRANCH_CODE, mst.KIOSK_ID, mst.vendor) data1,
               tbl_branch_txn_daily data2
        where data1.branch_code = data2.branch_no(+)
        group by data1.branch_code)
       
        SELECT b.branch_name, count(b.branch_code), sum(m.total_swayam_kiosks), sum(m.lipi_kiosk_cnt),
            sum(m.lipi_txn_cnt),sum(m.FORBES_kiosk_cnt),sum(m.FORBES_txn_cnt),sum(m.cms_kiosk_cnt),
            sum(m.cms_txn_cnt),sum(m.total_swayam_txns),sum(m.manual_txns),
            (case when nvl(sum(m.total_swayam_txns),0)=0 then 0 else round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns) + sum(m.manual_txns)), 2) end) mig_prcnt
        FROM main_data m, tbl_branch_master b
        WHERE b.branch_code = m.branch_code and b.crcl_code=:in_circle_code and b.network=:in_network_code and b.mod_code=:in_module_code and b.region=:in_region_code
        GROUP BY b.branch_name;
       
---------------------------THE----------END------------------------------------        
