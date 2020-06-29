create or replace PROCEDURE SP_KIOSK_ERROR_SUMMARY_PROC(
    fromDate_param IN VARCHAR2,
    toDate_param IN VARCHAR2,
    cur OUT SYS_REFCURSOR )
AS
  fromDate VARCHAR2(10);
  toDate VARCHAR2(10);
BEGIN
  IF fromDate_param IS NULL and toDate_param is null THEN
    select  to_char(trunc(SYSDATE),'dd-mm-yyyy') into fromDate from dual;
    select  to_char(trunc(SYSDATE),'dd-mm-yyyy') into toDate from dual;
  ELSE
    fromDate:= fromDate_param;
    toDate:= toDate_param;
  END IF;
  OPEN cur FOR 
    SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, 
            STR.KIOSK_ID, STR.VENDOR, (NVL(STR.NO_OF_TECH_FAIL,0) + NVL(ERR_COUNT,0)) AS NO_OF_ERRORS 
            FROM TBL_BRANCH_MASTER BM
    JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE 
    LEFT OUTER JOIN TBL_ERROR_STATS ERRs ON ERRs.KIOSK_ID = STR.KIOSK_ID
    WHERE to_date(STR.TXN_DATE, 'dd-mm-yyyy') between trunc(to_date(fromDate, 'dd-mm-yyyy')) 
          and trunc(to_date(toDate, 'dd-mm-yyyy'));
          
    DBMS_OUTPUT.PUT_LINE('-----------Done------------');
END SP_KIOSK_ERROR_SUMMARY_PROC;