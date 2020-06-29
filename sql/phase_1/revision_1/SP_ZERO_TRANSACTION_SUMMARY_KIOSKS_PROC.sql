create or replace procedure SP_ZERO_TRANSACTION_SUMMARY_KIOSKS_PROC
(
    fromdate_param      IN VARCHAR2,
    toDate_param        IN VARCHAR2,
    cur OUT SYS_REFCURSOR
) as 
    fromDate VARCHAR2(20);
    toDate VARCHAR2(20);
BEGIN
    IF fromdate_param IS NULL and toDate_param is NULL THEN
        select  to_char(trunc(SYSDATE),'dd-mm-yyyy') into fromdate from dual;
        select  to_char(trunc(SYSDATE),'dd-mm-yyyy') into toDate from dual;
    ELSE
        fromDate:= fromDate_param;
        toDate:=  toDate_param;
    END IF;
    
open cur for

SELECT BM.CRCL_NAME, concat('NET-0',substr(BM.NETWORK,1,1)) as NETWORK,
    BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, KM.KIOSK_ID, KM.VENDOR
FROM TBL_BRANCH_MASTER BM
JOIN TBL_KIOSK_MASTER KM ON BM.BRANCH_CODE = KM.BRANCH_CODE
WHERE KM.KIOSK_ID NOT IN (
            SELECT STR.KIOSK_ID FROM TBL_SWAYAM_TXN_REPORT STR
            WHERE to_date(STR.TXN_DATE,'dd-mm-yyyy')>= to_date(fromDate, 'dd-mm-yyyy') 
               and to_date(STR.TXN_DATE,'dd-mm-yyyy')<= to_date(todate, 'dd-mm-yyyy')
        );

end SP_ZERO_TRANSACTION_SUMMARY_KIOSKS_PROC;