create or replace NONEDITIONABLE PROCEDURE sp_drill_down_proc_test (
    fromdate      IN DATE,
    todate        IN DATE,
    circle_code   IN VARCHAR2,
    network_code  IN VARCHAR2,
    module_code   IN VARCHAR2,
    region_code   IN VARCHAR2,
    result_set    OUT SYS_REFCURSOR
) AS
    crcl_code_v   VARCHAR2(10);
    net_code_v    VARCHAR2(10);
    mod_code_v    VARCHAR2(10);
    reg_code_v    VARCHAR2(10);

BEGIN
    IF
        crcl_code_v = circle_code and fromdate is not null and todate is not null
    THEN 
        BEGIN
            OPEN result_set FOR
                SELECT
                    crcl_code,
                    network,
                    network_code,
                    100 AS total_swayam_branches,
                    100 AS total_swayam_kiosks,
                    SUM(a_kiosk_count) as a_kiosk_count,
                    SUM(a_txn_count) as a_txn_count,
                    SUM(b_kiosk_count) as b_kiosk_count,
                    SUM(b_txn_count) as b_txn_count,
                    SUM(c_kiosk_count) as c_kiosk_count,
                    SUM(c_txn_count) as c_txn_count,
                    SUM(branch_txn) as branch_txn,
                    SUM(swayam_txn) as swayam_txn,
                    SUM(migration_percentage) as migration_percentage
                FROM
                    (
                        SELECT
                            bm.br_id,
                            bm.crcl_name,
                            bm.crcl_code,
                            concat(
                                'NET-0',
                                substr(
                                    bm.network,
                                    1,
                                    1
                                )
                            ) AS network,
                            bm.network AS network_code,
                            bm.module,
                            bm.mod_code,
                            bm.region,
                            bm.region_code,
                            bm.branch_name,
                            100 AS total_swayam_branches,
                            100 AS total_swayam_kiosks,
                            nvl(a_kiosk_count,0) AS a_kiosk_count,
                            nvl(a_txn_count,0) AS a_txn_count,
                            nvl(b_kiosk_count,0) AS b_kiosk_count,
                            nvl(b_txn_count,0) AS b_txn_count,
                            nvl(c_kiosk_count,0) AS c_kiosk_count,
                            nvl(c_txn_count,0) AS c_txn_count,
                            nvl(bt.no_of_accounts,0) AS branch_txn,
                            nvl(a.swayam_txn,0) AS swayam_txn,
                            nvl(
                                round(
                                    (swayam_txn) * 100 / (bt.no_of_accounts + swayam_txn),
                                    2
                                ),
                                '0'
                            ) AS migration_percentage
                        FROM
                            tbl_branch_master bm
                            LEFT OUTER JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code
                            INNER JOIN tbl_branch_txn_daily bt ON bm.branch_code = bt.branch_no
                            INNER JOIN (
                                SELECT
                                    branch_code,
                                    SUM(no_of_txns) AS swayam_txn
                                FROM
                                    tbl_swayam_txn_report
                                GROUP BY
                                    branch_code
                            ) a ON bm.branch_code = a.branch_code
                            INNER JOIN (
                                SELECT
                                    branch_code,
                                    a_kiosk_count,
                                    a_txn_count,
                                    b_kiosk_count,
                                    b_txn_count,
                                    c_kiosk_count,
                                    c_txn_count
                                FROM
                                    (
                                        SELECT
                                            km1.branch_code AS branch_code,
                                            km1.vendor AS vendor,
                                            km1.kiosk_count AS kiosk_count,
                                            str1.sum_txn AS txn_count
                                        FROM
                                            (
                                                SELECT
                                                    branch_code,
                                                    vendor,
                                                    COUNT(*) AS kiosk_count
                                                FROM
                                                    tbl_kiosk_master
                                                GROUP BY
                                                    branch_code,
                                                    vendor
                                            ) km1
                                            INNER JOIN (
                                                SELECT
                                                    branch_code,
                                                    vendor,
                                                    SUM(no_of_txns) AS sum_txn
                                                FROM
                                                    tbl_swayam_txn_report str

                                                WHERE
                                                        TO_DATE(str.txn_date,'dd-MM-yy') >= trunc(fromdate)
                                                    AND
                                                        TO_DATE(str.txn_date,'dd-MM-yy') <= trunc(todate)
                                                GROUP BY
                                                    branch_code,
                                                    vendor
                                            ) str1 ON
                                                km1.branch_code = str1.branch_code
                                            AND
                                                km1.vendor = str1.vendor
                                    )
                                        PIVOT ( SUM ( kiosk_count ) AS kiosk_count,SUM ( txn_count ) AS txn_count
                                            FOR ( vendor )
                                            IN ( 'LIPI' AS a,'FORBES' AS b,'CMS' AS c )
                                        )
                            ) p ON p.branch_code = bm.branch_code
                        WHERE
                            EXISTS (
                                SELECT
                                    1
                                FROM
                                    tbl_kiosk_master km
                                WHERE
                                    bm.branch_code = km.branch_code
                            )
                    )
                GROUP BY network;

        EXCEPTION
            WHEN no_data_found THEN
                NULL;
        END;


    ELSIF nvl(circle_code, '') = '' and nvl(network_code,'') = '' and nvl(module_code,'') = '' and nvl(region_code,'') = '' and fromdate is not null and todate is not null THEN
        BEGIN
            OPEN result_set FOR
                SELECT crcl_name, crcl_code, 100 total_swayam_branches, 100 total_swayam_kiosks, SUM(a_kiosk_count) a_kiosk_count,
                       SUM(a_txn_count) a_txn_count, SUM(b_kiosk_count) b_kiosk_count, SUM(b_txn_count) b_txn_count, SUM(c_kiosk_count) c_kiosk_count,
                       SUM(c_txn_count) c_txn_count, SUM(branch_txn) branch_txn, SUM(swayam_txn) swayam_txn, SUM(migration_percentage) migration_percentage
                  FROM ( SELECT bm.br_id, bm.crcl_name, bm.crcl_code, concat( 'NET-0', substr(bm.network,1,1)) AS network, bm.network AS network_code,
                                bm.module, bm.mod_code, bm.region, bm.region_code, bm.branch_name, 100 AS total_swayam_branches, 100 AS total_swayam_kiosks,
                                nvl(a_kiosk_count,0) AS a_kiosk_count, nvl(a_txn_count,0) AS a_txn_count, nvl(b_kiosk_count,0) AS b_kiosk_count,
                                nvl(b_txn_count,0) AS b_txn_count, nvl(c_kiosk_count,0) AS c_kiosk_count, nvl(c_txn_count,0) AS c_txn_count,
                                nvl(bt.no_of_accounts,0) AS branch_txn, nvl(a.swayam_txn,0) AS swayam_txn,
                                nvl(round((swayam_txn) * 100 / (bt.no_of_accounts + swayam_txn),2),'0') AS migration_percentage
                           FROM tbl_branch_master bm
                           LEFT OUTER JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code
                           INNER JOIN tbl_branch_txn_daily bt ON bm.branch_code = bt.branch_no
                           INNER JOIN (SELECT branch_code, SUM(no_of_txns) AS swayam_txn
                                         FROM tbl_swayam_txn_report
                                        GROUP BY branch_code
                                      ) a ON bm.branch_code = a.branch_code
                           INNER JOIN (SELECT branch_code, a_kiosk_count, a_txn_count, b_kiosk_count, b_txn_count, c_kiosk_count, c_txn_count 
                                         FROM (SELECT km1.branch_code AS branch_code, km1.vendor AS vendor, km1.kiosk_count AS kiosk_count,
                                                      str1.sum_txn AS txn_count
                                                 FROM (SELECT branch_code,vendor, COUNT(*) AS kiosk_count
                                                         FROM tbl_kiosk_master
                                                        GROUP BY branch_code, vendor) km1
                                                INNER JOIN ( SELECT branch_code, vendor, SUM(no_of_txns) AS sum_txn
                                                               FROM tbl_swayam_txn_report str
                                                              WHERE TO_DATE(str.txn_date,'dd-MM-yyyy') >= trunc(fromdate)
                                                                AND TO_DATE(str.txn_date,'dd-MM-yyyy') <= trunc(todate)
                                                              GROUP BY branch_code, vendor) str1 
                                                        ON     km1.branch_code = str1.branch_code
                                                           AND km1.vendor = str1.vendor
                                               )
                                         PIVOT ( SUM ( kiosk_count ) AS kiosk_count,SUM ( txn_count ) AS txn_count
                                           FOR ( vendor ) IN ( 'LIPI' AS a,'FORBES' AS b,'CMS' AS c ))
                                       ) p ON p.branch_code = bm.branch_code
                           WHERE EXISTS (SELECT 1 FROM tbl_kiosk_master km
                                          WHERE bm.branch_code = km.branch_code)
                       )
                 GROUP BY crcl_name, crcl_code
                 Order By 1;

        EXCEPTION
            WHEN no_data_found THEN
                NULL;
        END;


        ELSIF crcl_code_v = circle_code and net_code_v = network_code and fromdate is not null and todate is not null THEN
        BEGIN
            OPEN result_set FOR
                SELECT
                    crcl_code,
                    network_code,
                    module,
                    mod_code,                
                    100 AS total_swayam_branches,
                    100 AS total_swayam_kiosks,
                    SUM(a_kiosk_count) as a_kiosk_count,
                    SUM(a_txn_count) as a_txn_count,
                    SUM(b_kiosk_count) as b_kiosk_count,
                    SUM(b_txn_count) as b_txn_count,
                    SUM(c_kiosk_count) as c_kiosk_count,
                    SUM(c_txn_count) as c_txn_count,
                    SUM(branch_txn) as branch_txn,
                    SUM(swayam_txn) as swayam_txn,
                    SUM(migration_percentage) as migration_percentage
                FROM
                    (
                        SELECT
                            bm.br_id,
                            bm.crcl_name,
                            bm.crcl_code,
                            concat(
                                'NET-0',
                                substr(
                                    bm.network,
                                    1,
                                    1
                                )
                            ) AS network,
                            bm.network AS network_code,
                            bm.module,
                            bm.mod_code,
                            bm.region,
                            bm.region_code,
                            bm.branch_name,
                            100 AS total_swayam_branches,
                            100 AS total_swayam_kiosks,
                            nvl(a_kiosk_count,0) AS a_kiosk_count,
                            nvl(a_txn_count,0) AS a_txn_count,
                            nvl(b_kiosk_count,0) AS b_kiosk_count,
                            nvl(b_txn_count,0) AS b_txn_count,
                            nvl(c_kiosk_count,0) AS c_kiosk_count,
                            nvl(c_txn_count,0) AS c_txn_count,
                            nvl(bt.no_of_accounts,0) AS branch_txn,
                            nvl(a.swayam_txn,0) AS swayam_txn,
                            nvl(
                                round(
                                    (swayam_txn) * 100 / (bt.no_of_accounts + swayam_txn),
                                    2
                                ),
                                '0'
                            ) AS migration_percentage
                        FROM
                            tbl_branch_master bm
                            LEFT OUTER JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code
                            INNER JOIN tbl_branch_txn_daily bt ON bm.branch_code = bt.branch_no
                            INNER JOIN (
                                SELECT
                                    branch_code,
                                    SUM(no_of_txns) AS swayam_txn
                                FROM
                                    tbl_swayam_txn_report
                                GROUP BY
                                    branch_code
                            ) a ON bm.branch_code = a.branch_code
                            INNER JOIN (
                                SELECT
                                    branch_code,
                                    a_kiosk_count,
                                    a_txn_count,
                                    b_kiosk_count,
                                    b_txn_count,
                                    c_kiosk_count,
                                    c_txn_count
                                FROM
                                    (
                                        SELECT
                                            km1.branch_code AS branch_code,
                                            km1.vendor AS vendor,
                                            km1.kiosk_count AS kiosk_count,
                                            str1.sum_txn AS txn_count
                                        FROM
                                            (
                                                SELECT
                                                    branch_code,
                                                    vendor,
                                                    COUNT(*) AS kiosk_count
                                                FROM
                                                    tbl_kiosk_master
                                                GROUP BY
                                                    branch_code,
                                                    vendor
                                            ) km1
                                            INNER JOIN (
                                                SELECT
                                                    branch_code,
                                                    vendor,
                                                    SUM(no_of_txns) AS sum_txn
                                                FROM
                                                    tbl_swayam_txn_report str

                                              WHERE
                                                      TO_DATE(str.txn_date,'dd-MM-yy') >= trunc(fromdate)
                                                  AND
                                                      TO_DATE(str.txn_date,'dd-MM-yy') <= trunc(todate)
                                                GROUP BY
                                                    branch_code,
                                                    vendor
                                            ) str1 ON
                                                km1.branch_code = str1.branch_code
                                            AND
                                                km1.vendor = str1.vendor
                                    )
                                        PIVOT ( SUM ( kiosk_count ) AS kiosk_count,SUM ( txn_count ) AS txn_count
                                            FOR ( vendor )
                                            IN ( 'LIPI' AS a,'FORBES' AS b,'CMS' AS c )
                                        )
                            ) p ON p.branch_code = bm.branch_code
                        WHERE
                            EXISTS (
                                SELECT
                                    1
                                FROM
                                    tbl_kiosk_master km
                                WHERE
                                    bm.branch_code = km.branch_code
                            )
                    )
                GROUP BY
                    module;

        EXCEPTION
            WHEN no_data_found THEN
                NULL;
        END;


        ELSIF crcl_code_v = circle_code and net_code_v = network_code and mod_code_v = module_code and fromdate is not null and todate is not null THEN
        BEGIN
            OPEN result_set FOR
                SELECT
                    crcl_code,
                    network_code,
                    mod_code,    
                    region,
                    region_code,
                    100 AS total_swayam_branches,
                    100 AS total_swayam_kiosks,
                    SUM(a_kiosk_count) as a_kiosk_count,
                    SUM(a_txn_count) as a_txn_count,
                    SUM(b_kiosk_count) as b_kiosk_count,
                    SUM(b_txn_count) as b_txn_count,
                    SUM(c_kiosk_count) as c_kiosk_count,
                    SUM(c_txn_count) as c_txn_count,
                    SUM(branch_txn) as branch_txn,
                    SUM(swayam_txn) as swayam_txn,
                    SUM(migration_percentage) as migration_percentage
                FROM
                    (
                        SELECT
                            bm.br_id,
                            bm.crcl_name,
                            bm.crcl_code,
                            concat(
                                'NET-0',
                                substr(
                                    bm.network,
                                    1,
                                    1
                                )
                            ) AS network,
                            bm.network AS network_code,
                            bm.module,
                            bm.mod_code,
                            bm.region,
                            bm.region_code,
                            bm.branch_name,
                            100 AS total_swayam_branches,
                            100 AS total_swayam_kiosks,
                            nvl(a_kiosk_count,0) AS a_kiosk_count,
                            nvl(a_txn_count,0) AS a_txn_count,
                            nvl(b_kiosk_count,0) AS b_kiosk_count,
                            nvl(b_txn_count,0) AS b_txn_count,
                            nvl(c_kiosk_count,0) AS c_kiosk_count,
                            nvl(c_txn_count,0) AS c_txn_count,
                            nvl(bt.no_of_accounts,0) AS branch_txn,
                            nvl(a.swayam_txn,0) AS swayam_txn,
                            nvl(
                                round(
                                    (swayam_txn) * 100 / (bt.no_of_accounts + swayam_txn),
                                    2
                                ),
                                '0'
                            ) AS migration_percentage
                        FROM
                            tbl_branch_master bm
                            LEFT OUTER JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code
                            INNER JOIN tbl_branch_txn_daily bt ON bm.branch_code = bt.branch_no
                            INNER JOIN (
                                SELECT
                                    branch_code,
                                    SUM(no_of_txns) AS swayam_txn
                                FROM
                                    tbl_swayam_txn_report
                                GROUP BY
                                    branch_code
                            ) a ON bm.branch_code = a.branch_code
                            INNER JOIN (
                                SELECT
                                    branch_code,
                                    a_kiosk_count,
                                    a_txn_count,
                                    b_kiosk_count,
                                    b_txn_count,
                                    c_kiosk_count,
                                    c_txn_count
                                FROM
                                    (
                                        SELECT
                                            km1.branch_code AS branch_code,
                                            km1.vendor AS vendor,
                                            km1.kiosk_count AS kiosk_count,
                                            str1.sum_txn AS txn_count
                                        FROM
                                            (
                                                SELECT
                                                    branch_code,
                                                    vendor,
                                                    COUNT(*) AS kiosk_count
                                                FROM
                                                    tbl_kiosk_master
                                                GROUP BY
                                                    branch_code,
                                                    vendor
                                            ) km1
                                            INNER JOIN (
                                                SELECT
                                                    branch_code,
                                                    vendor,
                                                    SUM(no_of_txns) AS sum_txn
                                                FROM
                                                    tbl_swayam_txn_report str

                                              WHERE
                                                      TO_DATE(str.txn_date,'dd-MM-yy') >= trunc(fromdate)
                                                  AND
                                                      TO_DATE(str.txn_date,'dd-MM-yy') <= trunc(todate)
                                                GROUP BY
                                                    branch_code,
                                                    vendor
                                            ) str1 ON
                                                km1.branch_code = str1.branch_code
                                            AND
                                                km1.vendor = str1.vendor
                                    )
                                        PIVOT ( SUM ( kiosk_count ) AS kiosk_count,SUM ( txn_count ) AS txn_count
                                            FOR ( vendor )
                                            IN ( 'LIPI' AS a,'FORBES' AS b,'CMS' AS c )
                                        )
                            ) p ON p.branch_code = bm.branch_code
                        WHERE
                            EXISTS (
                                SELECT
                                    1
                                FROM
                                    tbl_kiosk_master km
                                WHERE
                                    bm.branch_code = km.branch_code
                            )
                    )
                GROUP BY
                    region;

        EXCEPTION
            WHEN no_data_found THEN
                NULL;
        END;


        ELSIF crcl_code_v = circle_code and net_code_v = network_code and mod_code_v = module_code and reg_code_v = region_code and fromdate is not null and todate is not null THEN
        BEGIN
            OPEN result_set FOR
                SELECT
                    branch_name,
                    100 AS total_swayam_branches,
                    100 AS total_swayam_kiosks,
                    SUM(a_kiosk_count) as a_kiosk_count,
                    SUM(a_txn_count) as a_txn_count,
                    SUM(b_kiosk_count) as b_kiosk_count,
                    SUM(b_txn_count) as b_txn_count,
                    SUM(c_kiosk_count) as c_kiosk_count,
                    SUM(c_txn_count) as c_txn_count,
                    SUM(branch_txn) as branch_txn,
                    SUM(swayam_txn) as swayam_txn,
                    SUM(migration_percentage) as migration_percentage
                FROM
                    (
                        SELECT
                            bm.br_id,
                            bm.crcl_name,
                            bm.crcl_code,
                            concat(
                                'NET-0',
                                substr(
                                    bm.network,
                                    1,
                                    1
                                )
                            ) AS network,
                            bm.network AS network_code,
                            bm.module,
                            bm.mod_code,
                            bm.region,
                            bm.region_code,
                            bm.branch_name,
                            100 AS total_swayam_branches,
                            100 AS total_swayam_kiosks,
                            nvl(a_kiosk_count,0) AS a_kiosk_count,
                            nvl(a_txn_count,0) AS a_txn_count,
                            nvl(b_kiosk_count,0) AS b_kiosk_count,
                            nvl(b_txn_count,0) AS b_txn_count,
                            nvl(c_kiosk_count,0) AS c_kiosk_count,
                            nvl(c_txn_count,0) AS c_txn_count,
                            nvl(bt.no_of_accounts,0) AS branch_txn,
                            nvl(a.swayam_txn,0) AS swayam_txn,
                            nvl(
                                round(
                                    (swayam_txn) * 100 / (bt.no_of_accounts + swayam_txn),
                                    2
                                ),
                                '0'
                            ) AS migration_percentage
                        FROM
                            tbl_branch_master bm
                            LEFT OUTER JOIN tbl_kiosk_master km ON bm.branch_code = km.branch_code
                            INNER JOIN tbl_branch_txn_daily bt ON bm.branch_code = bt.branch_no
                            INNER JOIN (
                                SELECT
                                    branch_code,
                                    SUM(no_of_txns) AS swayam_txn
                                FROM
                                    tbl_swayam_txn_report
                                GROUP BY
                                    branch_code
                            ) a ON bm.branch_code = a.branch_code
                            INNER JOIN (
                                SELECT
                                    branch_code,
                                    a_kiosk_count,
                                    a_txn_count,
                                    b_kiosk_count,
                                    b_txn_count,
                                    c_kiosk_count,
                                    c_txn_count
                                FROM
                                    (
                                        SELECT
                                            km1.branch_code AS branch_code,
                                            km1.vendor AS vendor,
                                            km1.kiosk_count AS kiosk_count,
                                            str1.sum_txn AS txn_count
                                        FROM
                                            (
                                                SELECT
                                                    branch_code,
                                                    vendor,
                                                    COUNT(*) AS kiosk_count
                                                FROM
                                                    tbl_kiosk_master
                                                GROUP BY
                                                    branch_code,
                                                    vendor
                                            ) km1
                                            INNER JOIN (
                                                SELECT
                                                    branch_code,
                                                    vendor,
                                                    SUM(no_of_txns) AS sum_txn
                                                FROM
                                                    tbl_swayam_txn_report str

                                              WHERE
                                                      TO_DATE(str.txn_date,'dd-MM-yy') >= trunc(fromdate)
                                                  AND
                                                      TO_DATE(str.txn_date,'dd-MM-yy') <= trunc(todate)
                                                GROUP BY
                                                    branch_code,
                                                    vendor
                                            ) str1 ON
                                                km1.branch_code = str1.branch_code
                                            AND
                                                km1.vendor = str1.vendor
                                    )
                                        PIVOT ( SUM ( kiosk_count ) AS kiosk_count,SUM ( txn_count ) AS txn_count
                                            FOR ( vendor )
                                            IN ( 'LIPI' AS a,'FORBES' AS b,'CMS' AS c )
                                        )
                            ) p ON p.branch_code = bm.branch_code
                        WHERE
                            EXISTS (
                                SELECT
                                    1
                                FROM
                                    tbl_kiosk_master km
                                WHERE
                                    bm.branch_code = km.branch_code
                            )
                    )
                GROUP BY
                    branch_name;

         EXCEPTION
            WHEN no_data_found THEN
                NULL;
        END;



   END IF;
END sp_drill_down_proc_test;