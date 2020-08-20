create or replace PROCEDURE SP_SWAYAM_TXN_REPORT(txnDate IN VARCHAR2, cur OUT SYS_REFCURSOR) IS  
    BEGIN  
        INSERT INTO TBL_SWAYAM_TXN_REPORT (ID,TXN_DATE,BRANCH_CODE,VENDOR,KIOSK_ID,NO_OF_TXNS,
NO_OF_SUCCESS_TXNS,NO_OF_FAIL,NO_OF_TECH_FAIL,NO_OF_BUSINESS_FAIL)  
        with main_data as (select KIOSK_ID, REQUEST_DATE_TIME, REQUESTING_BRANCH, count(ktd.KIOSK_ID) AS NO_OF_TXNS,
            sum(case status when 'RCV' then 1 else 0 end) NO_OF_SUCCESS_TXNS_1,
            sum(case status when 'NACK' then 1 else 0 end) NO_OF_SUCCESS_TXNS_2,
            sum(case status when 'NEG' then 1 else 0 end) NO_OF_TECH_FAIL,
            sum(case status when 'ERR' then 1 else 0 end) NO_OF_BUSINESS_FAIL
        FROM TBL_SWAYAM_TXN_DAILY ktd
        WHERE to_date(REQUEST_DATE_TIME, 'dd-mm-yyyy')=to_date(txnDate, 'dd-mm-yyyy')
        GROUP BY KIOSK_ID,REQUEST_DATE_TIME, REQUESTING_BRANCH)
        SELECT TBL_SWAYAM_TXN_REPORT_ID_SEQ.nextval, REQUEST_DATE_TIME, REQUESTING_BRANCH, VENDOR, tbl.KIOSK_ID,
        NVL(NO_OF_TXNS,0), (NO_OF_SUCCESS_TXNS_1+NO_OF_SUCCESS_TXNS_2) as NO_OF_SUCCESS_TXNS,
        (NO_OF_TXNS-(NO_OF_SUCCESS_TXNS_1+NO_OF_SUCCESS_TXNS_2)) as NO_OF_FAIL, NO_OF_TECH_FAIL, NO_OF_BUSINESS_FAIL        
        FROM main_data tbl
        LEFT JOIN TBL_KIOSK_MASTER km ON km.KIOSK_ID=tbl.KIOSK_ID;
    COMMIT;
END;