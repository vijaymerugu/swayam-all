create or replace NONEDITIONABLE procedure sp_zero_transaction_kiosks
(
fromdate in date,
todate in date,
cur OUT SYS_REFCURSOR
) as 
BEGIN

open cur for
SELECT
    BM.BR_ID,
    BM.CRCL_NAME,
    concat('NET-0',substr(BM.NETWORK,1,1)) as NETWORK,
    BM.MODULE,
    BM.REGION,
    BM.BRANCH_CODE,
    BM.BRANCH_NAME,
    KM.KIOSK_ID,
    KM.VENDOR
FROM
    TBL_BRANCH_MASTER BM
    INNER JOIN TBL_KIOSK_MASTER KM ON BM.BRANCH_CODE = KM.BRANCH_CODE
WHERE
    NOT
        EXISTS (
            SELECT
                1
            FROM
                TBL_SWAYAM_TXN_REPORT STR
            WHERE
                KM.KIOSK_ID = STR.KIOSK_ID AND
                to_date(STR.TXN_DATE,'dd-MM-yy')>= sp_zero_transaction_kiosks.fromdate and to_date(STR.TXN_DATE,'dd-MM-yy')<= sp_zero_transaction_kiosks.todate
        );

end ;