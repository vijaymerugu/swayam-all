create or replace PROCEDURE SP_REAL_TIME_PROC(
    fromdate_param IN VARCHAR2 ,
    cur OUT SYS_REFCURSOR )
AS
  fromDate VARCHAR2(100);
BEGIN
  IF fromdate_param IS NULL THEN
  select  to_char(trunc(SYSDATE),'dd-mm-yyyy') into fromdate from dual;
  ELSE
    fromDate:= fromdate_param;
  END IF;
  DBMS_OUTPUT.PUT_LINE(fromdate);
  OPEN cur FOR 
    SELECT  BM.CRCL_NAME, BM.NETWORK, BM.MODULE, BM.REGION, BM.BRANCH_CODE, BM.BRANCH_NAME, 
            STR.KIOSK_ID, STR.NO_OF_TXNS, STR.VENDOR 
            FROM TBL_BRANCH_MASTER BM
    JOIN TBL_SWAYAM_TXN_REPORT STR ON BM.BRANCH_CODE = STR.BRANCH_CODE 
    AND STR.TXN_DATE=trunc(to_date(fromDate,'dd-mm-yyyy'))  ORDER BY STR.TXN_DATE  DESC ;
END SP_REAL_TIME_PROC;