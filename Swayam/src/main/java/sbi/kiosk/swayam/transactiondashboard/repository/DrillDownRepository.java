package sbi.kiosk.swayam.transactiondashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import sbi.kiosk.swayam.common.entity.DrillDown;

@Repository
public interface DrillDownRepository extends PagingAndSortingRepository<DrillDown, String> {
	
	@Query(value="SELECT * FROM VW_DRILL_DOWN",nativeQuery=true)
	
	Page<DrillDown> findAll(Pageable pageable);
	
    @Query(value="SELECT * FROM VW_DRILL_DOWN where CRCL_NAME=:circleName",nativeQuery=true)

    Page<DrillDown> findAllByCircle(Pageable pageable, @Param("circleName") String circleName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where NETWORK=:networkName",nativeQuery=true)

    Page<DrillDown> findAllByNetwork(Pageable pageable, @Param("networkName") String networkName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where MODULE=:moduleName",nativeQuery=true)

    Page<DrillDown> findAllByModule(Pageable pageable, @Param("moduleName") String moduleName);
    
    @Query(value="SELECT * FROM VW_DRILL_DOWN where REGION=:regionName",nativeQuery=true)

    Page<DrillDown> findAllByRegion(Pageable pageable, @Param("regionName") String regionName);
    
 
    
   /* @Query(value="  SELECT B.CRCL_NAME NAME,  B.CRCL_CODE CODE ,"
            + "  count(b.branch_code) BRANCH_CODE_COUNT, "
            + "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,"
            + "  SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
            + "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,"
            + "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
            + "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,"
            + "  SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
            + "  SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
            + "  SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
            + "  SUM(BR_TXN.MANUAL_TXNS) MANUAL_TXNS,"
            + "  (     CASE"
            + "     WHEN NVL("
            + "  SUM(M.TOTAL_SWAYAM_TXNS),"
            + "  0  ) = 0 THEN 0"
            + "    ELSE ROUND("
            + "   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + SUM(BR_TXN.MANUAL_TXNS) ),"
            + "  2"
            + "    )   END   ) MIG_PRCNT "
            + "  FROM   TBL_BRANCH_MASTER B  "
            + "  INNER JOIN (SELECT   BRANCH_NO,"
            + "  NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS "
            + "  FROM   TBL_BRANCH_TXN_DAILY DATA2 "
            + "  WHERE"
            + "  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
            + "  BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
            + "  GROUP BY  BRANCH_NO ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
            + "  INNER JOIN (  SELECT   BRANCH_CODE,"
            + "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
            + "  SUM( CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS"
            + "  ELSE 0    END  ) LIPI_KIOSK_CNT,  "
            + "  SUM( CASE VENDOR"
            + "  WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
            + "  ELSE 0  END ) LIPI_TXN_CNT,"
            + "  SUM( CASE VENDOR"
            + "  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
            + "  ELSE 0   END) FORBES_KIOSK_CNT,"
            + "  SUM( CASE VENDOR"
            + "  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
            + "  ELSE 0    END ) FORBES_TXN_CNT,"
            + "  SUM( CASE VENDOR"
            + "  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
            + "  ELSE 0   END ) CMS_KIOSK_CNT,"
            + "  SUM( CASE VENDOR"
            + "  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
            + "  ELSE 0   END  ) CMS_TXN_CNT,"
            + "  SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
                //    --nvl(sum(distinct data2.manual_txns),0) manual_txns  
                    +"   FROM"
                    + "  ( SELECT MST.BRANCH_CODE,   MST.VENDOR, COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS, "
                    + "  NVL("
                    + "  SUM(DTL.NO_OF_TXNS), "
                    + "  0   ) SWAYAM_TXNS "
                    + "  FROM     TBL_KIOSK_MASTER MST, "
                    + "  TBL_SWAYAM_TXN_REPORT DTL   "
                               //  where mst.kiosk_id = dtl.kiosk_id( )  
                    +"  WHERE "
                    +"  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )   "
                    + "  AND  TO_DATE(TXN_DATE,'dd-mm-yyyy') "
                    + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
                    + " GROUP BY   MST.BRANCH_CODE,  MST.VENDOR "
                    + " ORDER BY "
                    + "  MST.BRANCH_CODE, MST.VENDOR  ) DATA1 "
           //  --where data1.branch_code = data2.branch_no
                    +" GROUP BY   DATA1.BRANCH_CODE "
                    + " ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
                    + " GROUP BY  B.CRCL_NAME,   B.CRCL_CODE" ,
                   
                    countQuery = "  SELECT  count(B.CRCL_CODE) "
                    + "  FROM   TBL_BRANCH_MASTER B  "
                    + "  INNER JOIN (SELECT   BRANCH_NO,"
                    + "  NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS "
                    + "  FROM   TBL_BRANCH_TXN_DAILY DATA2 "
                    + "  WHERE "
                    + "  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
                    + "  BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
                    + "  GROUP BY  BRANCH_NO ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
                    + "  INNER JOIN (  SELECT   BRANCH_CODE, "
                    + "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
                    + "  SUM( CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS "
                    + "  ELSE 0    END  ) LIPI_KIOSK_CNT,  "
                    + "  SUM( CASE VENDOR"
                    + "  WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS "
                    + "  ELSE 0  END ) LIPI_TXN_CNT, "
                    + "  SUM( CASE VENDOR "
                    + "  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS "
                    + "  ELSE 0   END) FORBES_KIOSK_CNT, "
                    + "  SUM( CASE VENDOR "
                    + "  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS "
                    + "  ELSE 0    END ) FORBES_TXN_CNT, "
                    + "  SUM( CASE VENDOR"
                    + "  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS "
                    + "  ELSE 0   END ) CMS_KIOSK_CNT, "
                    + "  SUM( CASE VENDOR "
                    + "  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS "
                    + "  ELSE 0   END  ) CMS_TXN_CNT, "
                    + "  SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
                        //    --nvl(sum(distinct data2.manual_txns),0) manual_txns  
                            +"   FROM "
                            + "  ( SELECT MST.BRANCH_CODE,   MST.VENDOR, COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS, "
                            + "  NVL( "
                            + "  SUM(DTL.NO_OF_TXNS), "
                            + "  0   ) SWAYAM_TXNS "
                            + "  FROM     TBL_KIOSK_MASTER MST, "
                            + "  TBL_SWAYAM_TXN_REPORT DTL   "
                                       //  where mst.kiosk_id = dtl.kiosk_id( )  
                            +"  WHERE "
                            +"  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )   "
                            + "  AND  TO_DATE(TXN_DATE,'dd-mm-yyyy') "
                            + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
                            + " GROUP BY   MST.BRANCH_CODE,  MST.VENDOR "
                            + " ORDER BY "
                            + "  MST.BRANCH_CODE, MST.VENDOR  ) DATA1 "
                   //  --where data1.branch_code = data2.branch_no
                            +" GROUP BY   DATA1.BRANCH_CODE "
                            + " ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
                            + " GROUP BY  B.CRCL_NAME,   B.CRCL_CODE ",
                   
                                         
                            nativeQuery=true) */
    
    
    /// chagess
    
	/* Commented by Manisha on 20-Jan-2021
	 * @Query(
	 * value=" SELECT B.CRCL_NAME NAME, B.CRCL_CODE CODE, count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
	 * +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
	 * + " ( CASE     WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0" +
	 * "  ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) "
	 * +
	 * " ),  2  )  END ) MIG_PRCNT FROM  TBL_BRANCH_MASTER B  LEFT JOIN ( SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0)"
	 * +
	 * " MANUAL_TXNS  FROM  TBL_BRANCH_TXN_DAILY DATA2  WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * + " GROUP BY BRANCH_NO ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE " +
	 * " INNER JOIN ( SELECT BRANCH_CODE,SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR "
	 * +
	 * "  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END ) LIPI_KIOSK_CNT, SUM( CASE VENDOR "
	 * +
	 * " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT, SUM( CASE VENDOR"
	 * +
	 * " WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,SUM("
	 * +
	 * " CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS ELSE 0  END  ) FORBES_TXN_CNT,"
	 * +
	 * " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS ELSE 0 END ) CMS_KIOSK_CNT,"
	 * +
	 * " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS ELSE 0 END ) CMS_TXN_CNT,"
	 * +
	 * " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS FROM (SELECT MST.BRANCH_CODE,MST.VENDOR,"
	 * +
	 * " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS, NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS"
	 * + " FROM TBL_KIOSK_MASTER MST,TBL_SWAYAM_TXN_REPORT DTL" +
	 * " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
	 * +
	 * " GROUP BY MST.BRANCH_CODE, MST.VENDOR  ORDER BY MST.BRANCH_CODE, MST.VENDOR) DATA1"
	 * +
	 * " GROUP BY DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
	 * + " GROUP BY   B.CRCL_NAME, B.CRCL_CODE " + "     ORDER BY   " +
	 * " B.CRCL_NAME ASC ", nativeQuery=true,
	 * countQuery=" SELECT count(B.CRCL_CODE) CODE,B.CRCL_NAME NAME, count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
	 * +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
	 * + " ( CASE     WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0" +
	 * "  ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) "
	 * +
	 * " ),  2  )  END ) MIG_PRCNT FROM  TBL_BRANCH_MASTER B  LEFT JOIN ( SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0)"
	 * +
	 * " MANUAL_TXNS  FROM  TBL_BRANCH_TXN_DAILY DATA2  WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * + " GROUP BY BRANCH_NO ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE " +
	 * " INNER JOIN ( SELECT BRANCH_CODE,SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR "
	 * +
	 * "  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END ) LIPI_KIOSK_CNT, SUM( CASE VENDOR "
	 * +
	 * " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT, SUM( CASE VENDOR"
	 * +
	 * " WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,SUM("
	 * +
	 * " CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS ELSE 0  END  ) FORBES_TXN_CNT,"
	 * +
	 * "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS ELSE 0 END ) CMS_KIOSK_CNT,"
	 * +
	 * " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS ELSE 0 END ) CMS_TXN_CNT,"
	 * +
	 * " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS FROM (SELECT MST.BRANCH_CODE,MST.VENDOR,"
	 * +
	 * " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS, NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS"
	 * + " FROM TBL_KIOSK_MASTER MST,TBL_SWAYAM_TXN_REPORT DTL" +
	 * " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
	 * +
	 * " GROUP BY MST.BRANCH_CODE, MST.VENDOR  ORDER BY MST.BRANCH_CODE, MST.VENDOR) DATA1"
	 * +
	 * " GROUP BY DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
	 * + " GROUP BY   B.CRCL_NAME, B.CRCL_CODE " + "     ORDER BY   " +
	 * " B.CRCL_NAME ASC " )
	 */
    
    @Query(value=" SELECT B.CRCL_NAME NAME, B.CRCL_CODE CODE, count(b.branch_code) BRANCH_CODE_COUNT, "
    	    + " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
    	    + " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
    	    + " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
    	    + " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
    	    + " ( CASE     WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0"
    	    + "  ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) "
    	    + " ),  2  )  END ) MIG_PRCNT FROM  TBL_BRANCH_MASTER B  LEFT JOIN ( SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0)"
    	    + " MANUAL_TXNS  FROM  TBL_BRANCH_TXN_DAILY DATA2  WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
    	    + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	    + " GROUP BY BRANCH_NO ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
    	    + " INNER JOIN ( SELECT BRANCH_CODE,SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR "
    	    + "  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END ) LIPI_KIOSK_CNT, SUM( CASE VENDOR "
    	    + " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT, SUM( CASE VENDOR"
    	    + " WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,SUM("
    	    + " CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS ELSE 0  END  ) FORBES_TXN_CNT,"
    	    + " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS ELSE 0 END ) CMS_KIOSK_CNT,"
    	    + " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS ELSE 0 END ) CMS_TXN_CNT,"
    	    + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS FROM (SELECT MST.BRANCH_CODE,MST.VENDOR,"
    	    + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS, NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS"
    	    + " FROM TBL_KIOSK_MASTER MST,TBL_SWAYAM_TXN_REPORT DTL"
    	    + " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND TXN_DATE "
    	    + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	    + " GROUP BY MST.BRANCH_CODE, MST.VENDOR  ORDER BY MST.BRANCH_CODE, MST.VENDOR) DATA1"
    	    + " GROUP BY DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
    	    + " GROUP BY   B.CRCL_NAME, B.CRCL_CODE "
    	    + "     ORDER BY   "
    	    + " B.CRCL_NAME ASC ",
    	    nativeQuery=true,
    	    countQuery=" SELECT count(B.CRCL_CODE) CODE,B.CRCL_NAME NAME, count(b.branch_code) BRANCH_CODE_COUNT, "
    	        + " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
    	        + " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
    	        + " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
    	        + " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
    	        + " ( CASE     WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0"
    	        + "  ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) "
    	        + " ),  2  )  END ) MIG_PRCNT FROM  TBL_BRANCH_MASTER B  LEFT JOIN ( SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0)"
    	        + " MANUAL_TXNS  FROM  TBL_BRANCH_TXN_DAILY DATA2  WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
    	        + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	        + " GROUP BY BRANCH_NO ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
    	        + " INNER JOIN ( SELECT BRANCH_CODE,SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR "
    	        + "  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END ) LIPI_KIOSK_CNT, SUM( CASE VENDOR "
    	        + " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT, SUM( CASE VENDOR"
    	        + " WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,SUM("
    	        + " CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS ELSE 0  END  ) FORBES_TXN_CNT,"
    	        + "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS ELSE 0 END ) CMS_KIOSK_CNT,"
    	        + " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS ELSE 0 END ) CMS_TXN_CNT,"
    	        + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS FROM (SELECT MST.BRANCH_CODE,MST.VENDOR,"
    	        + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS, NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS"
    	        + " FROM TBL_KIOSK_MASTER MST,TBL_SWAYAM_TXN_REPORT DTL"
    	        + " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND TXN_DATE "
    	        + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	        + " GROUP BY MST.BRANCH_CODE, MST.VENDOR  ORDER BY MST.BRANCH_CODE, MST.VENDOR) DATA1"
    	        + " GROUP BY DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
    	        + " GROUP BY   B.CRCL_NAME, B.CRCL_CODE "
    	        + "     ORDER BY   "
    	        + " B.CRCL_NAME ASC "
    	       )
    
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,Pageable pageable);
 
	/*
	 * @Query(
	 * value=" SELECT B.CRCL_NAME NAME, B.CRCL_CODE CODE, count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
	 * +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
	 * + " ( CASE     WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0" +
	 * "  ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) "
	 * +
	 * " ),  2  )  END ) MIG_PRCNT FROM  TBL_BRANCH_MASTER B  LEFT JOIN ( SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0)"
	 * +
	 * " MANUAL_TXNS  FROM  TBL_BRANCH_TXN_DAILY DATA2  WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * + " GROUP BY BRANCH_NO ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE " +
	 * " INNER JOIN ( SELECT BRANCH_CODE,SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR "
	 * +
	 * "  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END ) LIPI_KIOSK_CNT, SUM( CASE VENDOR "
	 * +
	 * " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT, SUM( CASE VENDOR"
	 * +
	 * " WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,SUM("
	 * +
	 * " CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS ELSE 0  END  ) FORBES_TXN_CNT,"
	 * +
	 * " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS ELSE 0 END ) CMS_KIOSK_CNT,"
	 * +
	 * " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS ELSE 0 END ) CMS_TXN_CNT,"
	 * +
	 * " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS FROM (SELECT MST.BRANCH_CODE,MST.VENDOR,"
	 * +
	 * " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS, NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS"
	 * + " FROM TBL_KIOSK_MASTER MST,TBL_SWAYAM_TXN_REPORT DTL" +
	 * " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
	 * +
	 * " GROUP BY MST.BRANCH_CODE, MST.VENDOR  ORDER BY MST.BRANCH_CODE, MST.VENDOR) DATA1"
	 * +
	 * " GROUP BY DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
	 * + " GROUP BY   B.CRCL_NAME, B.CRCL_CODE " + "     ORDER BY   " +
	 * " B.CRCL_NAME ASC ", nativeQuery=true,
	 * countQuery=" SELECT count(B.CRCL_CODE) CODE,B.CRCL_NAME NAME, count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
	 * +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
	 * + " ( CASE     WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0" +
	 * "  ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) "
	 * +
	 * " ),  2  )  END ) MIG_PRCNT FROM  TBL_BRANCH_MASTER B  LEFT JOIN ( SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0)"
	 * +
	 * " MANUAL_TXNS  FROM  TBL_BRANCH_TXN_DAILY DATA2  WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * + " GROUP BY BRANCH_NO ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE " +
	 * " INNER JOIN ( SELECT BRANCH_CODE,SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR "
	 * +
	 * "  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END ) LIPI_KIOSK_CNT, SUM( CASE VENDOR "
	 * +
	 * " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT, SUM( CASE VENDOR"
	 * +
	 * " WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,SUM("
	 * +
	 * " CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS ELSE 0  END  ) FORBES_TXN_CNT,"
	 * +
	 * "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS ELSE 0 END ) CMS_KIOSK_CNT,"
	 * +
	 * " SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS ELSE 0 END ) CMS_TXN_CNT,"
	 * +
	 * " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS FROM (SELECT MST.BRANCH_CODE,MST.VENDOR,"
	 * +
	 * " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS, NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS"
	 * + " FROM TBL_KIOSK_MASTER MST,TBL_SWAYAM_TXN_REPORT DTL" +
	 * " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
	 * +
	 * " GROUP BY MST.BRANCH_CODE, MST.VENDOR  ORDER BY MST.BRANCH_CODE, MST.VENDOR) DATA1"
	 * +
	 * " GROUP BY DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
	 * + " GROUP BY   B.CRCL_NAME, B.CRCL_CODE " + "     ORDER BY   " +
	 * " B.CRCL_NAME ASC " )
	 * 
	 * 
	 * 
	 * Page<DrillDown> findByDateSearchNext(@Param("fromdate") String
	 * fromdate,@Param("todate") String todate,@Param("searchText") String
	 * searchText,Pageable pageable);
	 */
 /*   @Query(value=" SELECT b.network NAME, B.NETWORK CODE,  count(b.branch_code) BRANCH_CODE_COUNT, "
    	    + "    sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,"
    	    + "    SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
    	    + "    SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,"
    	    + "    SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
    	    + "    SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,"
    	    + "    SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
    	    + "    SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
    	    + "    SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
    	    + "    SUM(BR_TXN.MANUAL_TXNS) MANUAL_TXNS,"
    	    + "    ("
    	    + "        CASE"
    	    + "    WHEN "
    	    + " NVL("
    	    + "  SUM(M.TOTAL_SWAYAM_TXNS),"
    	    + "   0"
    	    + "       ) = 0 THEN 0"
    	    + "      ELSE ROUND("
    	    + "   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + SUM(BR_TXN.MANUAL_TXNS)"
    	    + "  ),"
    	    + "    2     )"
    	    + "    END   ) MIG_PRCNT "
    	    + " FROM"
    	    + "   TBL_BRANCH_MASTER B"
    	    + "   INNER JOIN ("
    	    + "   SELECT  BRANCH_NO,"
    	    + "   NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS  FROM"
    	    + "     TBL_BRANCH_TXN_DAILY DATA2"
    	    + "  WHERE"
    	    + "   TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN"
    	    + " TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	    + "    GROUP BY"
    	    + "   BRANCH_NO"
    	    + "  ) BR_TXN "
    	    + " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE"
    	    + "  INNER JOIN "
    	    + "( SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	    + "  SUM( CASE VENDOR    WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS "
    	    + "  ELSE 0"
    	    + "   END ) LIPI_KIOSK_CNT,"
    	    + " SUM( CASE VENDOR    WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
    	    + "   ELSE 0"
    	    + "    END"
    	    + "      ) LIPI_TXN_CNT,"
    	    + "  SUM( CASE VENDOR   WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
    	    + "   ELSE 0"
    	    + "  END"
    	    + "   ) FORBES_KIOSK_CNT,"
    	    + "  SUM( CASE VENDOR   WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
    	    + "  ELSE 0 "
    	    + " END  ) FORBES_TXN_CNT,"
    	    + "   SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
    	    + "   ELSE 0   END"
    	    + "   ) CMS_KIOSK_CNT,"
    	    + "  SUM(   CASE VENDOR   WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
    	    + "   ELSE 0 END ) CMS_TXN_CNT,"
    	    + "  SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
    	    //+ "     --nvl(sum(distinct data2.manual_txns),0) manual_txns  
    	           +" FROM   "
    	           + " ( SELECT   MST.BRANCH_CODE,"
    	           + " MST.VENDOR,"
    	           + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
    	           + "  NVL( SUM(DTL.NO_OF_TXNS),"
    	           + "  0 ) SWAYAM_TXNS"
    	           + "    FROM  TBL_KIOSK_MASTER MST,"
    	           + "  TBL_SWAYAM_TXN_REPORT DTL"
    	    //        --  where mst.kiosk_id = dtl.kiosk_id( )  
    	              +"  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )"
    	              + " AND TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy')"
    	              + " ) "
    	              + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	              + "  GROUP BY"
    	              + "   MST.BRANCH_CODE,"
    	              + "   MST.VENDOR"
    	              + "  ORDER BY"
    	              + "    MST.BRANCH_CODE,"
    	              + " MST.VENDOR ) DATA1"
    	              //        --where data1.branch_code = data2.branch_no
    	        +" GROUP BY "
    	        + " DATA1.BRANCH_CODE"
    	        + "  ORDER BY DATA1.BRANCH_CODE  ) M "
    	        + " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code "
    	        + " GROUP BY   B.NETWORK ",
    	       
    	        countQuery = " SELECT  count(B.NETWORK) "
    	        + " FROM "
    	        + "   TBL_BRANCH_MASTER B "
    	        + "   INNER JOIN ("
    	        + "   SELECT  BRANCH_NO,"
    	        + "   NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS  FROM"
    	        + "     TBL_BRANCH_TXN_DAILY DATA2"
    	        + "  WHERE"
    	        + "   TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN"
    	        + " TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	        + "    GROUP BY"
    	        + "   BRANCH_NO"
    	        + "  ) BR_TXN "
    	        + " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE"
    	        + "  INNER JOIN "
    	        + "( SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	        + "  SUM( CASE VENDOR    WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS "
    	        + "  ELSE 0"
    	        + "   END ) LIPI_KIOSK_CNT,"
    	        + " SUM( CASE VENDOR    WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
    	        + "   ELSE 0"
    	        + "    END"
    	        + "      ) LIPI_TXN_CNT,"
    	        + "  SUM( CASE VENDOR   WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
    	        + "   ELSE 0"
    	        + "  END"
    	        + "   ) FORBES_KIOSK_CNT,"
    	        + "  SUM( CASE VENDOR   WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
    	        + "  ELSE 0 "
    	        + " END  ) FORBES_TXN_CNT,"
    	        + "   SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
    	        + "   ELSE 0   END"
    	        + "   ) CMS_KIOSK_CNT,"
    	        + "  SUM(   CASE VENDOR   WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
    	        + "   ELSE 0 END ) CMS_TXN_CNT,"
    	        + "  SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
    	        //+ "     --nvl(sum(distinct data2.manual_txns),0) manual_txns  
    	               +" FROM   "
    	               + " ( SELECT   MST.BRANCH_CODE,"
    	               + " MST.VENDOR,"
    	               + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
    	               + "  NVL( SUM(DTL.NO_OF_TXNS),"
    	               + "  0 ) SWAYAM_TXNS"
    	               + "    FROM  TBL_KIOSK_MASTER MST,"
    	               + "  TBL_SWAYAM_TXN_REPORT DTL"
    	        //        --  where mst.kiosk_id = dtl.kiosk_id( )  
    	                  +"  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )"
    	                  + " AND TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy')"
    	                  + " ) "
    	                  + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	                  + "  GROUP BY"
    	                  + "   MST.BRANCH_CODE,"
    	                  + "   MST.VENDOR"
    	                  + "  ORDER BY"
    	                  + "    MST.BRANCH_CODE,"
    	                  + " MST.VENDOR ) DATA1"
    	                  //        --where data1.branch_code = data2.branch_no
    	            +" GROUP BY "
    	            + " DATA1.BRANCH_CODE"
    	            + "  ORDER BY DATA1.BRANCH_CODE  ) M "
    	            + " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code "
    	            + " GROUP BY   B.NETWORK"
    	       
    	       , nativeQuery=true) */
    
    ////
    
    
	/*
	 * @Query(value=
	 * "SELECT  b.network NAME, B.NETWORK CODE, count(b.branch_code) BRANCH_CODE_COUNT, "
	 * + "   sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS," +
	 * "   SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT," +
	 * "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT," +
	 * "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT," +
	 * "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT," +
	 * " SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT," + " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT," +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS," +
	 * " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  (     CASE" + "    WHEN NVL("
	 * + "  SUM(M.TOTAL_SWAYAM_TXNS),  0   ) = 0 THEN 0 " + "    ELSE ROUND( " +
	 * "   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
	 * + "            2    )      END	    ) MIG_PRCNT" +
	 * "  	FROM   TBL_BRANCH_MASTER B" +
	 * "    LEFT JOIN (   SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS    FROM   TBL_BRANCH_TXN_DAILY DATA2"
	 * +
	 * "    WHERE   TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )" +
	 * "        GROUP BY  BRANCH_NO  ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
	 * + "  INNER JOIN (   SELECT   BRANCH_CODE," +
	 * "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS," +
	 * "  SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS " +
	 * "    ELSE 0   END  ) LIPI_KIOSK_CNT," +
	 * "   SUM(CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS " +
	 * "   ELSE 0  END  ) LIPI_TXN_CNT," +
	 * "   SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS     ELSE 0   END   ) FORBES_KIOSK_CNT,"
	 * +
	 * "   SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS     ELSE 0   END ) FORBES_TXN_CNT,"
	 * +
	 * "   SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END  ) CMS_KIOSK_CNT,"
	 * +
	 * "   SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS    ELSE 0   END   ) CMS_TXN_CNT,"
	 * +
	 * "   SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS     FROM     (   SELECT  MST.BRANCH_CODE,  MST.VENDOR,"
	 * +
	 * "   COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,   NVL(SUM(DTL.NO_OF_TXNS),  0   ) SWAYAM_TXNS"
	 * + "   FROM    TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL   " +
	 * " WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )     AND" +
	 * " TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * + "  GROUP BY  MST.BRANCH_CODE, MST.VENDOR" +
	 * " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY   DATA1.BRANCH_CODE"
	 * + "  ORDER BY DATA1.BRANCH_CODE ) M " +
	 * " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code " +
	 * "	GROUP BY B.NETWORK " + " ORDER BY   B.NETWORK ASC ",
	 * countQuery="SELECT  count(B.NETWORK) AS CODE, b.network NAME, count(b.branch_code) BRANCH_CODE_COUNT, "
	 * + "   sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS," +
	 * "   SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT," +
	 * "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT," +
	 * "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT," +
	 * "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT," +
	 * " SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT," + " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT," +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS," +
	 * " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  (     CASE" + "    WHEN NVL("
	 * + "  SUM(M.TOTAL_SWAYAM_TXNS),  0   ) = 0 THEN 0 " + "    ELSE ROUND( " +
	 * "   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
	 * + "            2    )      END	    ) MIG_PRCNT" +
	 * "  	FROM   TBL_BRANCH_MASTER B" +
	 * "    LEFT JOIN (   SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS    FROM   TBL_BRANCH_TXN_DAILY DATA2"
	 * +
	 * "    WHERE   TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )" +
	 * "        GROUP BY  BRANCH_NO  ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
	 * + "  INNER JOIN (   SELECT   BRANCH_CODE," +
	 * "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS," +
	 * "  SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS " +
	 * "    ELSE 0   END  ) LIPI_KIOSK_CNT," +
	 * "   SUM(CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS " +
	 * "   ELSE 0  END  ) LIPI_TXN_CNT," +
	 * "   SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS     ELSE 0   END   ) FORBES_KIOSK_CNT,"
	 * +
	 * "   SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS     ELSE 0   END ) FORBES_TXN_CNT,"
	 * +
	 * "   SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END  ) CMS_KIOSK_CNT,"
	 * +
	 * "   SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS    ELSE 0   END   ) CMS_TXN_CNT,"
	 * +
	 * "   SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS     FROM     (   SELECT  MST.BRANCH_CODE,  MST.VENDOR,"
	 * +
	 * "   COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,   NVL(SUM(DTL.NO_OF_TXNS),  0   ) SWAYAM_TXNS"
	 * + "   FROM    TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL   " +
	 * " WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )     AND" +
	 * " TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * + "  GROUP BY  MST.BRANCH_CODE, MST.VENDOR" +
	 * " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY   DATA1.BRANCH_CODE"
	 * + "  ORDER BY DATA1.BRANCH_CODE ) M " +
	 * " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code " +
	 * "	GROUP BY B.NETWORK " + " ORDER BY   B.NETWORK ASC " ,nativeQuery=true)
	 */
    
    @Query(value=
    		"SELECT  b.network NAME, B.NETWORK CODE, count(b.branch_code) BRANCH_CODE_COUNT, "
    		+ "   sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,"
    		+ "   SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
    		+ "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,"
    		+ "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
    		+ "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,"
    		+ " SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
    		+ " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
    		+ " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
    		+ " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  (     CASE"
    		+ "    WHEN NVL("
    		+ "  SUM(M.TOTAL_SWAYAM_TXNS),  0   ) = 0 THEN 0 "
    		+ "    ELSE ROUND( "
    		+ "   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
    		+ "            2    )      END	    ) MIG_PRCNT"
    		+ "  	FROM   TBL_BRANCH_MASTER B"
    		+ "    LEFT JOIN (   SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS    FROM   TBL_BRANCH_TXN_DAILY DATA2"
    		+ "    WHERE   TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
    		+ " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    		+ "        GROUP BY  BRANCH_NO  ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
    		+ "  INNER JOIN (   SELECT   BRANCH_CODE,"
    		+ "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    		+ "  SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS "
    		+ "    ELSE 0   END  ) LIPI_KIOSK_CNT,"
    		+ "   SUM(CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS "
    		+ "   ELSE 0  END  ) LIPI_TXN_CNT,"
    		+ "   SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS     ELSE 0   END   ) FORBES_KIOSK_CNT,"
    		+ "   SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS     ELSE 0   END ) FORBES_TXN_CNT,"
    		+ "   SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END  ) CMS_KIOSK_CNT,"
    		+ "   SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS    ELSE 0   END   ) CMS_TXN_CNT,"
    		+ "   SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS     FROM     (   SELECT  MST.BRANCH_CODE,  MST.VENDOR,"
    		+ "   COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,   NVL(SUM(DTL.NO_OF_TXNS),  0   ) SWAYAM_TXNS"
    		+ "   FROM    TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL   "
    		+ " WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )     AND"
    		+ " TXN_DATE BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    		+ "  GROUP BY  MST.BRANCH_CODE, MST.VENDOR"
    		+ " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY   DATA1.BRANCH_CODE"
    		+ "  ORDER BY DATA1.BRANCH_CODE ) M "
    		+ " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code "
    		+ "	GROUP BY B.NETWORK "
    		+ " ORDER BY   B.NETWORK ASC ",
    	   countQuery="SELECT  count(B.NETWORK) AS CODE, b.network NAME, count(b.branch_code) BRANCH_CODE_COUNT, "
    	    		+ "   sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,"
    	    		+ "   SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
    	    		+ "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,"
    	    		+ "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
    	    		+ "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,"
    	    		+ " SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
    	    		+ " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
    	    		+ " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
    	    		+ " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  (     CASE"
    	    		+ "    WHEN NVL("
    	    		+ "  SUM(M.TOTAL_SWAYAM_TXNS),  0   ) = 0 THEN 0 "
    	    		+ "    ELSE ROUND( "
    	    		+ "   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
    	    		+ "            2    )      END	    ) MIG_PRCNT"
    	    		+ "  	FROM   TBL_BRANCH_MASTER B"
    	    		+ "    LEFT JOIN (   SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS    FROM   TBL_BRANCH_TXN_DAILY DATA2"
    	    		+ "    WHERE   TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
    	    		+ " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	    		+ "        GROUP BY  BRANCH_NO  ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
    	    		+ "  INNER JOIN (   SELECT   BRANCH_CODE,"
    	    		+ "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	    		+ "  SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS "
    	    		+ "    ELSE 0   END  ) LIPI_KIOSK_CNT,"
    	    		+ "   SUM(CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS "
    	    		+ "   ELSE 0  END  ) LIPI_TXN_CNT,"
    	    		+ "   SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS     ELSE 0   END   ) FORBES_KIOSK_CNT,"
    	    		+ "   SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS     ELSE 0   END ) FORBES_TXN_CNT,"
    	    		+ "   SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END  ) CMS_KIOSK_CNT,"
    	    		+ "   SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS    ELSE 0   END   ) CMS_TXN_CNT,"
    	    		+ "   SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS     FROM     (   SELECT  MST.BRANCH_CODE,  MST.VENDOR,"
    	    		+ "   COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,   NVL(SUM(DTL.NO_OF_TXNS),  0   ) SWAYAM_TXNS"
    	    		+ "   FROM    TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL   "
    	    		+ " WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )     AND"
    	    		+ " TXN_DATE BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	    		+ "  GROUP BY  MST.BRANCH_CODE, MST.VENDOR"
    	    		+ " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY   DATA1.BRANCH_CODE"
    	    		+ "  ORDER BY DATA1.BRANCH_CODE ) M "
    	    		+ " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code "
    	    		+ "	GROUP BY B.NETWORK "
    	    		+ " ORDER BY   B.NETWORK ASC " ,nativeQuery=true)
    
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,Pageable pageable);

	/*
	 * @Query(value=
	 * "SELECT  b.network NAME, B.NETWORK CODE, count(b.branch_code) BRANCH_CODE_COUNT, "
	 * + "   sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS," +
	 * "   SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT," +
	 * "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT," +
	 * "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT," +
	 * "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT," +
	 * " SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT," + " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT," +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS," +
	 * " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  (     CASE" + "    WHEN NVL("
	 * + "  SUM(M.TOTAL_SWAYAM_TXNS),  0   ) = 0 THEN 0 " + "    ELSE ROUND( " +
	 * "   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
	 * + "            2    )      END	    ) MIG_PRCNT" +
	 * "  	FROM   TBL_BRANCH_MASTER B" +
	 * "    LEFT JOIN (   SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS    FROM   TBL_BRANCH_TXN_DAILY DATA2"
	 * +
	 * "    WHERE   TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )" +
	 * "        GROUP BY  BRANCH_NO  ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
	 * + "  INNER JOIN (   SELECT   BRANCH_CODE," +
	 * "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS," +
	 * "  SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS " +
	 * "    ELSE 0   END  ) LIPI_KIOSK_CNT," +
	 * "   SUM(CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS " +
	 * "   ELSE 0  END  ) LIPI_TXN_CNT," +
	 * "   SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS     ELSE 0   END   ) FORBES_KIOSK_CNT,"
	 * +
	 * "   SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS     ELSE 0   END ) FORBES_TXN_CNT,"
	 * +
	 * "   SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END  ) CMS_KIOSK_CNT,"
	 * +
	 * "   SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS    ELSE 0   END   ) CMS_TXN_CNT,"
	 * +
	 * "   SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS     FROM     (   SELECT  MST.BRANCH_CODE,  MST.VENDOR,"
	 * +
	 * "   COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,   NVL(SUM(DTL.NO_OF_TXNS),  0   ) SWAYAM_TXNS"
	 * + "   FROM    TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL   " +
	 * " WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )     AND" +
	 * " TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * + "  GROUP BY  MST.BRANCH_CODE, MST.VENDOR" +
	 * " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY   DATA1.BRANCH_CODE"
	 * + "  ORDER BY DATA1.BRANCH_CODE ) M " +
	 * " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code " +
	 * "	GROUP BY B.NETWORK " + " ORDER BY   B.NETWORK ASC ",
	 * countQuery="SELECT  count(B.NETWORK) AS CODE, b.network NAME, count(b.branch_code) BRANCH_CODE_COUNT, "
	 * + "   sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS," +
	 * "   SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT," +
	 * "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT," +
	 * "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT," +
	 * "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT," +
	 * " SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT," + " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT," +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS," +
	 * " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  (     CASE" + "    WHEN NVL("
	 * + "  SUM(M.TOTAL_SWAYAM_TXNS),  0   ) = 0 THEN 0 " + "    ELSE ROUND( " +
	 * "   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
	 * + "            2    )      END	    ) MIG_PRCNT" +
	 * "  	FROM   TBL_BRANCH_MASTER B" +
	 * "    LEFT JOIN (   SELECT  BRANCH_NO, NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS    FROM   TBL_BRANCH_TXN_DAILY DATA2"
	 * +
	 * "    WHERE   TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )" +
	 * "        GROUP BY  BRANCH_NO  ) BR_TXN ON BR_TXN.BRANCH_NO = B.BRANCH_CODE "
	 * + "  INNER JOIN (   SELECT   BRANCH_CODE," +
	 * "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS," +
	 * "  SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS " +
	 * "    ELSE 0   END  ) LIPI_KIOSK_CNT," +
	 * "   SUM(CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS " +
	 * "   ELSE 0  END  ) LIPI_TXN_CNT," +
	 * "   SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS     ELSE 0   END   ) FORBES_KIOSK_CNT,"
	 * +
	 * "   SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS     ELSE 0   END ) FORBES_TXN_CNT,"
	 * +
	 * "   SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END  ) CMS_KIOSK_CNT,"
	 * +
	 * "   SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS    ELSE 0   END   ) CMS_TXN_CNT,"
	 * +
	 * "   SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS     FROM     (   SELECT  MST.BRANCH_CODE,  MST.VENDOR,"
	 * +
	 * "   COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,   NVL(SUM(DTL.NO_OF_TXNS),  0   ) SWAYAM_TXNS"
	 * + "   FROM    TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL   " +
	 * " WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )     AND" +
	 * " TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * + "  GROUP BY  MST.BRANCH_CODE, MST.VENDOR" +
	 * " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY   DATA1.BRANCH_CODE"
	 * + "  ORDER BY DATA1.BRANCH_CODE ) M " +
	 * " ON B.BRANCH_CODE = M.BRANCH_CODE where  B.CRCL_CODE=:in_circle_code " +
	 * "	GROUP BY B.NETWORK " + " ORDER BY   B.NETWORK ASC " ,nativeQuery=true)
	 * 
	 * 
	 * Page<DrillDown> findByDateSearchNext(@Param("fromdate") String
	 * fromdate,@Param("todate") String todate,@Param("in_circle_code") String
	 * in_circle_code,@Param("searchtext") String searchtext,Pageable pageable);
	 */

/*    
    @Query(value=" SELECT b.MODULE NAME, b.mod_code CODE,"
    	    + "   count(b.branch_code) BRANCH_CODE_COUNT,"
    	    + " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,"
    	    + " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
    	    + " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,"
    	    + " SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
    	    + " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,"
    	    + " SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
    	    + " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
    	    + " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
    	    + " SUM(BR_TXN.MANUAL_TXNS) MANUAL_TXNS,  "
    	             // --  round(sum(m.total_swayam_txns) * 100 / (sum(m.total_swayam_txns)   sum(m.manual_txns)), 2) mig_prcnt  
    	       +" ("
    	       + "  CASE"
    	       + "  WHEN NVL("
    	       + "  SUM(M.TOTAL_SWAYAM_TXNS),"
    	       + "  0  ) = 0 THEN 0  ELSE ROUND("
    	       + " SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + SUM(BR_TXN.MANUAL_TXNS) ),"
    	       + " 2   )"
    	       + "   END   ) MIG_PRCNT"
    	       + " FROM "
    	       + " TBL_BRANCH_MASTER B"
    	       + " INNER JOIN ("
    	       + "  SELECT  BRANCH_NO,"
    	       + " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
    	       + "  FROM TBL_BRANCH_TXN_DAILY DATA2"
    	       + "  WHERE"
    	       + "  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN"
    	       + "  TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	       + "  GROUP BY   BRANCH_NO  ) BR_TXN "
    	       + "  ON BR_TXN.BRANCH_NO = B.BRANCH_CODE"
    	       + "  INNER JOIN ("
    	       + "  SELECT  BRANCH_CODE,"
    	       + "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	       + " SUM(  CASE VENDOR"
    	       + "   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS"
    	       + "   ELSE 0"
    	       + "   END ) LIPI_KIOSK_CNT,"
    	       + "   SUM( CASE VENDOR"
    	       + "   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
    	       + "   ELSE 0"
    	       + "   END  ) LIPI_TXN_CNT,"
    	       + "   SUM( CASE VENDOR   WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
    	       + "   ELSE 0  END ) FORBES_KIOSK_CNT,"
    	       + "   SUM(  CASE VENDOR   WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
    	       + "   ELSE 0 "
    	       + "   END   ) FORBES_TXN_CNT,"
    	       + "   SUM(   CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
    	       + "  ELSE 0"
    	       + "   END  ) CMS_KIOSK_CNT,"
    	       + "  SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
    	       + "   ELSE 0"
    	       + "    END   ) CMS_TXN_CNT,  "
    	       + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  "
    	       + " FROM   "
    	       + " (   SELECT   MST.BRANCH_CODE, MST.VENDOR,"
    	       + "  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
    	       + "   NVL( SUM(DTL.NO_OF_TXNS),"
    	       + " 0 ) SWAYAM_TXNS"
    	       + "   FROM"
    	       + "   TBL_KIOSK_MASTER MST,"
    	       + "  TBL_SWAYAM_TXN_REPORT DTL "  
    	                //--  where mst.kiosk_id = dtl.kiosk_id( )    
    	    +"  WHERE "
    	    + " UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )"
    	    + " AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
    	    + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	    + "  GROUP BY"
    	    + "  MST.BRANCH_CODE,  MST.VENDOR"
    	    + "  ORDER BY"
    	    + "  MST.BRANCH_CODE,MST.VENDOR    ) DATA1 "  
    	                     //--where data1.branch_code = data2.branch_no
    	    +" GROUP BY "
    	    + " DATA1.BRANCH_CODE"
    	    + " ORDER BY "
    	    + " DATA1.BRANCH_CODE ) M  "
    	    + " ON B.BRANCH_CODE = M.BRANCH_CODE "
    	    + "  where"
    	    + " b.crcl_code=:in_circle_code and  b.network=:in_network_code   "
    	    + "   GROUP BY b.MODULE,b.mod_code ",
    	    countQuery=" SELECT count(b.MODULE) "
    	    + " FROM "
    	       + " TBL_BRANCH_MASTER B"
    	       + " INNER JOIN ("
    	       + "  SELECT  BRANCH_NO,"
    	       + " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
    	       + "  FROM TBL_BRANCH_TXN_DAILY DATA2"
    	       + "  WHERE"
    	       + "  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN"
    	       + "  TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	       + "  GROUP BY   BRANCH_NO  ) BR_TXN "
    	       + "  ON BR_TXN.BRANCH_NO = B.BRANCH_CODE"
    	       + "  INNER JOIN ("
    	       + "  SELECT  BRANCH_CODE,"
    	       + "  SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	       + " SUM(  CASE VENDOR"
    	       + "   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS"
    	       + "   ELSE 0"
    	       + "   END ) LIPI_KIOSK_CNT,"
    	       + "   SUM( CASE VENDOR"
    	       + "   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
    	       + "   ELSE 0"
    	       + "   END  ) LIPI_TXN_CNT,"
    	       + "   SUM( CASE VENDOR   WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
    	       + "   ELSE 0  END ) FORBES_KIOSK_CNT,"
    	       + "   SUM(  CASE VENDOR   WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
    	       + "   ELSE 0 "
    	       + "   END   ) FORBES_TXN_CNT,"
    	       + "   SUM(   CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
    	       + "  ELSE 0"
    	       + "   END  ) CMS_KIOSK_CNT,"
    	       + "  SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
    	       + "   ELSE 0"
    	       + "    END   ) CMS_TXN_CNT,  "
    	       + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  "
    	       + " FROM   "
    	       + " (   SELECT   MST.BRANCH_CODE, MST.VENDOR,"
    	       + "  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
    	       + "   NVL( SUM(DTL.NO_OF_TXNS),"
    	       + " 0 ) SWAYAM_TXNS"
    	       + "   FROM"
    	       + "   TBL_KIOSK_MASTER MST,"
    	       + "  TBL_SWAYAM_TXN_REPORT DTL "  
    	                //--  where mst.kiosk_id = dtl.kiosk_id( )    
    	    +"  WHERE "
    	    + " UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )"
    	    + " AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
    	    + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	    + "  GROUP BY"
    	    + "  MST.BRANCH_CODE,  MST.VENDOR"
    	    + "  ORDER BY"
    	    + "  MST.BRANCH_CODE,MST.VENDOR    ) DATA1 "  
    	                     //--where data1.branch_code = data2.branch_no
    	    +" GROUP BY "
    	    + " DATA1.BRANCH_CODE"
    	    + " ORDER BY "
    	    + " DATA1.BRANCH_CODE ) M  "
    	    + " ON B.BRANCH_CODE = M.BRANCH_CODE "
    	    + "  where"
    	    + " b.crcl_code=:in_circle_code and  b.network=:in_network_code   "
    	    + "   GROUP BY b.MODULE,b.mod_code ",nativeQuery=true)  */
    
    
	/*
	 * @Query(
	 * value=" SELECT b.MODULE name,b.mod_code code,count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, "
	 * +
	 * " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS, "
	 * + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  " +
	 * " ( CASE WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0  ELSE ROUND( " +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
	 * +
	 * " 2 ) END ) MIG_PRCNT  FROM TBL_BRANCH_MASTER B LEFT JOIN (SELECT BRANCH_NO, "
	 * +
	 * " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM TBL_BRANCH_TXN_DAILY DATA2 "
	 * +
	 * " WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') )"
	 * + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) GROUP BY BRANCH_NO ) BR_TXN " +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT BRANCH_CODE, " +
	 * " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR WHEN 'LIPI'   " +
	 * " THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT, " +
	 * " SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT,"
	 * +
	 * " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END ) FORBES_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) CMS_KIOSK_CNT,"
	 * +
	 * "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS  ELSE 0 END  ) CMS_TXN_CNT,"
	 * +
	 * " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  FROM  (SELECT MST.BRANCH_CODE,  MST.VENDOR,"
	 * +
	 * " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS "
	 * + "  FROM  TBL_KIOSK_MASTER MST, TBL_SWAYAM_TXN_REPORT DTL " +
	 * " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )   AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * +
	 * " GROUP BY MST.BRANCH_CODE, MST.VENDOR ORDER BY MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
	 * + " GROUP BY  DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE ) M  " +
	 * " ON B.BRANCH_CODE = M.BRANCH_CODE   where b.crcl_code=:in_circle_code and "
	 * + " b.network=:in_network_code GROUP BY b.MODULE,b.mod_code " +
	 * " ORDER BY b.MODULE" ,
	 * countQuery=" SELECT count(b.mod_code) as code, b.MODULE name,count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, "
	 * +
	 * " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS, "
	 * + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  " +
	 * " ( CASE WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0  ELSE ROUND( " +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
	 * +
	 * " 2 ) END ) MIG_PRCNT  FROM TBL_BRANCH_MASTER B LEFT JOIN (SELECT BRANCH_NO, "
	 * +
	 * " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM TBL_BRANCH_TXN_DAILY DATA2 "
	 * +
	 * " WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') )"
	 * + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) GROUP BY BRANCH_NO ) BR_TXN " +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT BRANCH_CODE, " +
	 * " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR WHEN 'LIPI'   " +
	 * " THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT, " +
	 * " SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT,"
	 * +
	 * " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END ) FORBES_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) CMS_KIOSK_CNT,"
	 * +
	 * "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS  ELSE 0 END  ) CMS_TXN_CNT,"
	 * +
	 * " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  FROM  (SELECT MST.BRANCH_CODE,  MST.VENDOR,"
	 * +
	 * " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS "
	 * + "  FROM  TBL_KIOSK_MASTER MST, TBL_SWAYAM_TXN_REPORT DTL " +
	 * " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )   AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * +
	 * " GROUP BY MST.BRANCH_CODE, MST.VENDOR ORDER BY MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
	 * + " GROUP BY  DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE ) M  " +
	 * " ON B.BRANCH_CODE = M.BRANCH_CODE   " +
	 * " where b.crcl_code=:in_circle_code and " + " b.network=:in_network_code " +
	 * " GROUP BY b.MODULE,b.mod_code" + " ORDER BY b.MODULE ",nativeQuery=true)
	 */
    
    @Query(value=" SELECT b.MODULE name,b.mod_code code,count(b.branch_code) BRANCH_CODE_COUNT, "
    	    + "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
    	    + " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
    	    + "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, "
    	    + " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS, "
    	    + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  "
    	    + " ( CASE WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0  ELSE ROUND( "
    	    + " SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
    	    + " 2 ) END ) MIG_PRCNT  FROM TBL_BRANCH_MASTER B LEFT JOIN (SELECT BRANCH_NO, "
    	    + " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM TBL_BRANCH_TXN_DAILY DATA2 "
    	    + " WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') )"
    	    + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) GROUP BY BRANCH_NO ) BR_TXN "
    	    + " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT BRANCH_CODE, "
    	    + " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR WHEN 'LIPI'   "
    	    + " THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT, "
    	    + " SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT,"
    	    + " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,"
    	    + " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END ) FORBES_TXN_CNT,"
    	    + " SUM( CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) CMS_KIOSK_CNT,"
    	    + "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS  ELSE 0 END  ) CMS_TXN_CNT,"
    	    + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  FROM  (SELECT MST.BRANCH_CODE,  MST.VENDOR,"
    	    + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS "
    	    + "  FROM  TBL_KIOSK_MASTER MST, TBL_SWAYAM_TXN_REPORT DTL "
    	    + " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )   AND TXN_DATE "
    	    + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	    + " GROUP BY MST.BRANCH_CODE, MST.VENDOR ORDER BY MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
    	    + " GROUP BY  DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE ) M  "
    	    + " ON B.BRANCH_CODE = M.BRANCH_CODE   where b.crcl_code=:in_circle_code and "
    	    + " b.network=:in_network_code GROUP BY b.MODULE,b.mod_code "
    	    + " ORDER BY b.MODULE"
    	    ,
    	    countQuery=" SELECT count(b.mod_code) as code, b.MODULE name,count(b.branch_code) BRANCH_CODE_COUNT, "
    	        + "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
    	        + " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
    	        + "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, "
    	        + " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS, "
    	        + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  "
    	        + " ( CASE WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0  ELSE ROUND( "
    	        + " SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
    	        + " 2 ) END ) MIG_PRCNT  FROM TBL_BRANCH_MASTER B LEFT JOIN (SELECT BRANCH_NO, "
    	        + " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM TBL_BRANCH_TXN_DAILY DATA2 "
    	        + " WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') )"
    	        + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) GROUP BY BRANCH_NO ) BR_TXN "
    	        + " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT BRANCH_CODE, "
    	        + " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR WHEN 'LIPI'   "
    	        + " THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT, "
    	        + " SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT,"
    	        + " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,"
    	        + " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END ) FORBES_TXN_CNT,"
    	        + " SUM( CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) CMS_KIOSK_CNT,"
    	        + "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS  ELSE 0 END  ) CMS_TXN_CNT,"
    	        + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  FROM  (SELECT MST.BRANCH_CODE,  MST.VENDOR,"
    	        + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS "
    	        + "  FROM  TBL_KIOSK_MASTER MST, TBL_SWAYAM_TXN_REPORT DTL "
    	        + " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )   AND TXN_DATE "
    	        + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	        + " GROUP BY MST.BRANCH_CODE, MST.VENDOR ORDER BY MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
    	        + " GROUP BY  DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE ) M  "
    	        + " ON B.BRANCH_CODE = M.BRANCH_CODE   "
    	        + " where b.crcl_code=:in_circle_code and "
    	        + " b.network=:in_network_code "
    	        + " GROUP BY b.MODULE,b.mod_code"
    	        + " ORDER BY b.MODULE ",nativeQuery=true)
    
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,@Param("in_network_code") String in_network_code,Pageable pageable);
    
	/*
	 * @Query(
	 * value=" SELECT b.MODULE name,b.mod_code code,count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, "
	 * +
	 * " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS, "
	 * + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  " +
	 * " ( CASE WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0  ELSE ROUND( " +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
	 * +
	 * " 2 ) END ) MIG_PRCNT  FROM TBL_BRANCH_MASTER B LEFT JOIN (SELECT BRANCH_NO, "
	 * +
	 * " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM TBL_BRANCH_TXN_DAILY DATA2 "
	 * +
	 * " WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') )"
	 * + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) GROUP BY BRANCH_NO ) BR_TXN " +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT BRANCH_CODE, " +
	 * " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR WHEN 'LIPI'   " +
	 * " THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT, " +
	 * " SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT,"
	 * +
	 * " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END ) FORBES_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) CMS_KIOSK_CNT,"
	 * +
	 * "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS  ELSE 0 END  ) CMS_TXN_CNT,"
	 * +
	 * " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  FROM  (SELECT MST.BRANCH_CODE,  MST.VENDOR,"
	 * +
	 * " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS "
	 * + "  FROM  TBL_KIOSK_MASTER MST, TBL_SWAYAM_TXN_REPORT DTL " +
	 * " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )   AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * +
	 * " GROUP BY MST.BRANCH_CODE, MST.VENDOR ORDER BY MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
	 * + " GROUP BY  DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE ) M  " +
	 * " ON B.BRANCH_CODE = M.BRANCH_CODE   where b.crcl_code=:in_circle_code and "
	 * + " b.network=:in_network_code GROUP BY b.MODULE,b.mod_code " +
	 * " ORDER BY b.MODULE" ,
	 * countQuery=" SELECT count(b.mod_code) as code, b.MODULE name,count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT, "
	 * +
	 * " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS, "
	 * + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,  " +
	 * " ( CASE WHEN NVL(SUM(M.TOTAL_SWAYAM_TXNS), 0 ) = 0 THEN 0  ELSE ROUND( " +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
	 * +
	 * " 2 ) END ) MIG_PRCNT  FROM TBL_BRANCH_MASTER B LEFT JOIN (SELECT BRANCH_NO, "
	 * +
	 * " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM TBL_BRANCH_TXN_DAILY DATA2 "
	 * +
	 * " WHERE TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') )"
	 * + " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) GROUP BY BRANCH_NO ) BR_TXN " +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT BRANCH_CODE, " +
	 * " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, SUM(CASE VENDOR WHEN 'LIPI'   " +
	 * " THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT, " +
	 * " SUM(CASE VENDOR  WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS  ELSE 0  END ) LIPI_TXN_CNT,"
	 * +
	 * " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM(CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END ) FORBES_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS  ELSE 0 END ) CMS_KIOSK_CNT,"
	 * +
	 * "  SUM(CASE VENDOR WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS  ELSE 0 END  ) CMS_TXN_CNT,"
	 * +
	 * " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  FROM  (SELECT MST.BRANCH_CODE,  MST.VENDOR,"
	 * +
	 * " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS "
	 * + "  FROM  TBL_KIOSK_MASTER MST, TBL_SWAYAM_TXN_REPORT DTL " +
	 * " WHERE UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )   AND TO_DATE(TXN_DATE,'dd-mm-yyyy') "
	 * +
	 * " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * +
	 * " GROUP BY MST.BRANCH_CODE, MST.VENDOR ORDER BY MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
	 * + " GROUP BY  DATA1.BRANCH_CODE ORDER BY DATA1.BRANCH_CODE ) M  " +
	 * " ON B.BRANCH_CODE = M.BRANCH_CODE   " +
	 * " where b.crcl_code=:in_circle_code and " + " b.network=:in_network_code " +
	 * " GROUP BY b.MODULE,b.mod_code" + " ORDER BY b.MODULE ",nativeQuery=true)
	 * 
	 * 
	 * Page<DrillDown> findByDateSearchNext(@Param("fromdate") String
	 * fromdate,@Param("todate") String todate,@Param("in_circle_code") String
	 * in_circle_code,@Param("in_network_code") String
	 * in_network_code,@Param("searchText") String searchText,Pageable pageable);
	 */  
  /*  
    
    @Query(value=" SELECT b.region NAME,b.region CODE,"
    	    + "  count(b.branch_code) BRANCH_CODE_COUNT, "
    	    + "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,"
    	    + "  SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
    	    + "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,"
    	    + "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
    	    + "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,"
    	    + "  SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
    	    + "  SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
    	    + "  SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
    	    + "  SUM(BR_TXN.MANUAL_TXNS) MANUAL_TXNS,"
    	    + "   ("
    	    + " CASE"
    	    + "   WHEN NVL("
    	    + " SUM(M.TOTAL_SWAYAM_TXNS),"
    	    + "  0 "
    	    + "  ) = 0 THEN 0"
    	    + "  ELSE ROUND("
    	    + "  SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + SUM(BR_TXN.MANUAL_TXNS)"
    	    + "  ),"
    	    + "  2   )  "
    	    + " END ) MIG_PRCNT"
    	    + " FROM"
    	    + " TBL_BRANCH_MASTER B"
    	    + " INNER JOIN ("
    	    + " SELECT  BRANCH_NO,"
    	    + " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
    	    + " FROM "
    	    + " TBL_BRANCH_TXN_DAILY DATA2"
    	    + " WHERE"
    	    + " TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
    	    + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	    + " GROUP BY"
    	    + " BRANCH_NO    ) BR_TXN "
    	    + " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE"
    	    + " INNER JOIN ( SELECT BRANCH_CODE, "
    	    + " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	    + " SUM("
    	    + " CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS"
    	    + " ELSE 0"
    	    + " END  ) LIPI_KIOSK_CNT, "
    	    + " SUM( CASE VENDOR"
    	    + " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
    	    + " ELSE 0"
    	    + " END ) LIPI_TXN_CNT,"
    	    + " SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
    	    + " ELSE 0"
    	    + " END   ) FORBES_KIOSK_CNT, "
    	    + " SUM(    CASE VENDOR"
    	    + " WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
    	    + " ELSE 0"
    	    + " END  ) FORBES_TXN_CNT,"
    	    + " SUM(  CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
    	    + " ELSE 0"
    	    + " END  ) CMS_KIOSK_CNT,"
    	    + " SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
    	    + " ELSE 0"
    	    + "  END  ) CMS_TXN_CNT,"
    	    + "  SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
    	    //+ "--nvl(sum(distinct data2.manual_txns),0) manual_txns  
    	            + " FROM "
    	            + "   (  SELECT MST.BRANCH_CODE,  MST.VENDOR, "
    	            + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  "
    	            + " NVL(  SUM(DTL.NO_OF_TXNS),"
    	            + " 0   ) SWAYAM_TXNS"
    	            + " FROM  TBL_KIOSK_MASTER MST,"
    	            + " TBL_SWAYAM_TXN_REPORT DTL "
    	            //+ "  --  where mst.kiosk_id = dtl.kiosk_id( )  
    	             +  " WHERE"
    	             + "  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) ) "
    	             + "   AND  TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN "
    	             + " TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	             + "  GROUP BY"
    	             + " MST.BRANCH_CODE,  MST.VENDOR"
    	             + "   ORDER BY   MST.BRANCH_CODE,  MST.VENDOR ) DATA1   "
    	            // + " --where data1.branch_code = data2.branch_no  "
    	             + " GROUP BY "
    	             + " DATA1.BRANCH_CODE"
    	             + "  ORDER BY DATA1.BRANCH_CODE"
    	             + "   ) M "
    	             + " ON B.BRANCH_CODE = M.BRANCH_CODE "
    	             + "  WHERE "
    	             + " b.branch_code = m.branch_code and "
    	             + "   b.crcl_code=:in_circle_code and "
    	             + " b.network=:in_network_code and "
    	             + " b.mod_code=:in_module_code"
    	             + "  GROUP BY b.region , b.region ",  
    	             
    	             countQuery=
    	            " SELECT  count(b.branch_code) BRANCH_CODE_COUNT "
    	                + " FROM "
    	                + " TBL_BRANCH_MASTER B"
    	                + " INNER JOIN ("
    	                + " SELECT  BRANCH_NO,"
    	                + " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
    	                + " FROM "
    	                + " TBL_BRANCH_TXN_DAILY DATA2"
    	                + " WHERE"
    	                + " TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
    	                + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	                + " GROUP BY"
    	                + " BRANCH_NO    ) BR_TXN "
    	                + " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE"
    	                + " INNER JOIN ( SELECT BRANCH_CODE, "
    	                + " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	                + " SUM("
    	                + " CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS"
    	                + " ELSE 0"
    	                + " END  ) LIPI_KIOSK_CNT, "
    	                + " SUM( CASE VENDOR"
    	                + " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
    	                + " ELSE 0"
    	                + " END ) LIPI_TXN_CNT,"
    	                + " SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
    	                + " ELSE 0"
    	                + " END   ) FORBES_KIOSK_CNT, "
    	                + " SUM(    CASE VENDOR"
    	                + " WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
    	                + " ELSE 0"
    	                + " END  ) FORBES_TXN_CNT,"
    	                + " SUM(  CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
    	                + " ELSE 0"
    	                + " END  ) CMS_KIOSK_CNT,"
    	                + " SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
    	                + " ELSE 0"
    	                + "  END  ) CMS_TXN_CNT,"
    	                + "  SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
    	                //+ "--nvl(sum(distinct data2.manual_txns),0) manual_txns  
    	                       + " FROM "
    	                       + "   (  SELECT MST.BRANCH_CODE,  MST.VENDOR, "
    	                       + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  "
    	                       + " NVL(  SUM(DTL.NO_OF_TXNS),"
    	                       + " 0   ) SWAYAM_TXNS"
    	                       + " FROM  TBL_KIOSK_MASTER MST,"
    	                       + " TBL_SWAYAM_TXN_REPORT DTL "
    	                       //+ "  --  where mst.kiosk_id = dtl.kiosk_id( )  
    	                        +  " WHERE"
    	                        + "  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) ) "
    	                        + "   AND  TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN "
    	                        + " TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	                        + "  GROUP BY"
    	                        + " MST.BRANCH_CODE,  MST.VENDOR"
    	                        + "   ORDER BY   MST.BRANCH_CODE,  MST.VENDOR ) DATA1   "
    	                       // + " --where data1.branch_code = data2.branch_no  "
    	                        + " GROUP BY "
    	                        + " DATA1.BRANCH_CODE"
    	                        + "  ORDER BY DATA1.BRANCH_CODE"
    	                        + "   ) M "
    	                        + " ON B.BRANCH_CODE = M.BRANCH_CODE "
    	                        + "  WHERE "
    	                        + " b.branch_code = m.branch_code and "
    	                        + "   b.crcl_code=:in_circle_code and "
    	                        + " b.network=:in_network_code and "
    	                        + " b.mod_code=:in_module_code"
    	                        + "  GROUP BY b.region , b.region ",
    	    nativeQuery=true)   */
    
    
    /// changes
    
	/*
	 * @Query(
	 * value="  SELECT  b.region NAME,  b.region CODE,	count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
	 * +
	 * "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
	 * +
	 * " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
	 * + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS," +
	 * " (  CASE  WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),  0  ) = 0 THEN 0   " +
	 * " ELSE ROUND(   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
	 * +
	 * " 2    )    END ) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (   SELECT  BRANCH_NO, "
	 * +
	 * " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM   TBL_BRANCH_TXN_DAILY DATA2"
	 * +
	 * " WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * +
	 * " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )    GROUP BY   BRANCH_NO  ) BR_TXN "
	 * +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN ( SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
	 * +
	 * "  SUM( CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS    ELSE 0  END ) LIPI_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END  ) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END   ) FORBES_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END ) CMS_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END   ) CMS_TXN_CNT,"
	 * + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS " +
	 * " FROM  (  SELECT MST.BRANCH_CODE, MST.VENDOR, COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
	 * + " NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS   FROM  TBL_KIOSK_MASTER MST, "
	 * +
	 * " TBL_SWAYAM_TXN_REPORT DTL WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND "
	 * +
	 * " TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND "
	 * +
	 * " TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )     GROUP BY  MST.BRANCH_CODE, MST.VENDOR"
	 * +
	 * " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY  DATA1.BRANCH_CODE"
	 * + " ORDER BY DATA1.BRANCH_CODE ) M ON B.BRANCH_CODE = M.BRANCH_CODE" +
	 * " WHERE b.branch_code = m.branch_code and " +
	 * " b.crcl_code=:in_circle_code and b.network=:in_network_code and " +
	 * " b.mod_code=:in_module_code  GROUP BY b.region , b.region" +
	 * " ORDER BY b.region ", nativeQuery=true,
	 * countQuery=" SELECT  count(b.region) CODE, b.region NAME, 	count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
	 * +
	 * "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
	 * +
	 * " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
	 * + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS," +
	 * " (  CASE  WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),  0  ) = 0 THEN 0   " +
	 * " ELSE ROUND(   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
	 * +
	 * " 2    )    END ) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (   SELECT  BRANCH_NO, "
	 * +
	 * " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM   TBL_BRANCH_TXN_DAILY DATA2"
	 * +
	 * " WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * +
	 * " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )    GROUP BY   BRANCH_NO  ) BR_TXN "
	 * +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN ( SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
	 * +
	 * "  SUM( CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS    ELSE 0  END ) LIPI_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END  ) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END   ) FORBES_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END ) CMS_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END   ) CMS_TXN_CNT,"
	 * + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS " +
	 * " FROM  (  SELECT MST.BRANCH_CODE, MST.VENDOR, COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
	 * + " NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS   FROM  TBL_KIOSK_MASTER MST, "
	 * +
	 * " TBL_SWAYAM_TXN_REPORT DTL WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND "
	 * +
	 * " TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND "
	 * +
	 * " TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )     GROUP BY  MST.BRANCH_CODE, MST.VENDOR"
	 * +
	 * " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY  DATA1.BRANCH_CODE"
	 * + " ORDER BY DATA1.BRANCH_CODE ) M ON B.BRANCH_CODE = M.BRANCH_CODE" +
	 * " WHERE b.branch_code = m.branch_code and " +
	 * " b.crcl_code=:in_circle_code and b.network=:in_network_code and " +
	 * " b.mod_code=:in_module_code  GROUP BY b.region , b.region " +
	 * " ORDER BY b.region ")
	 */
    
    @Query(value="  SELECT  b.region NAME,  b.region CODE,	count(b.branch_code) BRANCH_CODE_COUNT, "
    		+ " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
    		+ "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
    		+ " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
    		+ " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
    		+ " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,"
    		+ " (  CASE  WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),  0  ) = 0 THEN 0   "
    		+ " ELSE ROUND(   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
    		+ " 2    )    END ) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (   SELECT  BRANCH_NO, "
    		+ " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM   TBL_BRANCH_TXN_DAILY DATA2"
    		+ " WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
    		+ " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )    GROUP BY   BRANCH_NO  ) BR_TXN "
    		+ " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN ( SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    		+ "  SUM( CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT,"
    		+ " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS    ELSE 0  END ) LIPI_TXN_CNT,"
    		+ " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END  ) FORBES_KIOSK_CNT,"
    		+ " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END   ) FORBES_TXN_CNT,"
    		+ " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END ) CMS_KIOSK_CNT,"
    		+ " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END   ) CMS_TXN_CNT,"
    		+ " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
    		+ " FROM  (  SELECT MST.BRANCH_CODE, MST.VENDOR, COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
    		+ " NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS   FROM  TBL_KIOSK_MASTER MST, "
    		+ " TBL_SWAYAM_TXN_REPORT DTL WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND "
    		+ " TXN_DATE BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND "
    		+ " TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )     GROUP BY  MST.BRANCH_CODE, MST.VENDOR"
    		+ " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY  DATA1.BRANCH_CODE"
    		+ " ORDER BY DATA1.BRANCH_CODE ) M ON B.BRANCH_CODE = M.BRANCH_CODE"
    		+ " WHERE b.branch_code = m.branch_code and "
    		+ " b.crcl_code=:in_circle_code and b.network=:in_network_code and "
    		+ " b.mod_code=:in_module_code  GROUP BY b.region , b.region"
    		+ " ORDER BY b.region ",
    		nativeQuery=true,countQuery=" SELECT  count(b.region) CODE, b.region NAME, 	count(b.branch_code) BRANCH_CODE_COUNT, "
    	    		+ " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
    	    		+ "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
    	    		+ " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
    	    		+ " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
    	    		+ " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS,"
    	    		+ " (  CASE  WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),  0  ) = 0 THEN 0   "
    	    		+ " ELSE ROUND(   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
    	    		+ " 2    )    END ) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (   SELECT  BRANCH_NO, "
    	    		+ " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM   TBL_BRANCH_TXN_DAILY DATA2"
    	    		+ " WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
    	    		+ " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )    GROUP BY   BRANCH_NO  ) BR_TXN "
    	    		+ " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN ( SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	    		+ "  SUM( CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT,"
    	    		+ " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS    ELSE 0  END ) LIPI_TXN_CNT,"
    	    		+ " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END  ) FORBES_KIOSK_CNT,"
    	    		+ " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END   ) FORBES_TXN_CNT,"
    	    		+ " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END ) CMS_KIOSK_CNT,"
    	    		+ " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END   ) CMS_TXN_CNT,"
    	    		+ " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
    	    		+ " FROM  (  SELECT MST.BRANCH_CODE, MST.VENDOR, COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
    	    		+ " NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS   FROM  TBL_KIOSK_MASTER MST, "
    	    		+ " TBL_SWAYAM_TXN_REPORT DTL WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND "
    	    		+ " TXN_DATE BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND "
    	    		+ " TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )     GROUP BY  MST.BRANCH_CODE, MST.VENDOR"
    	    		+ " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY  DATA1.BRANCH_CODE"
    	    		+ " ORDER BY DATA1.BRANCH_CODE ) M ON B.BRANCH_CODE = M.BRANCH_CODE"
    	    		+ " WHERE b.branch_code = m.branch_code and "
    	    		+ " b.crcl_code=:in_circle_code and b.network=:in_network_code and "
    	    		+ " b.mod_code=:in_module_code  GROUP BY b.region , b.region "
    	    		+ " ORDER BY b.region ")
    
    
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,@Param("in_network_code") String in_network_code,@Param("in_module_code") String in_module_code,Pageable pageable);
	/*
	 * @Query(
	 * value="  SELECT  b.region NAME,  b.region CODE,	count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
	 * +
	 * "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
	 * +
	 * " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
	 * + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS," +
	 * " (  CASE  WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),  0  ) = 0 THEN 0   " +
	 * " ELSE ROUND(   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
	 * +
	 * " 2    )    END ) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (   SELECT  BRANCH_NO, "
	 * +
	 * " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM   TBL_BRANCH_TXN_DAILY DATA2"
	 * +
	 * " WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * +
	 * " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )    GROUP BY   BRANCH_NO  ) BR_TXN "
	 * +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN ( SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
	 * +
	 * "  SUM( CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS    ELSE 0  END ) LIPI_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END  ) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END   ) FORBES_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END ) CMS_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END   ) CMS_TXN_CNT,"
	 * + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS " +
	 * " FROM  (  SELECT MST.BRANCH_CODE, MST.VENDOR, COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
	 * + " NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS   FROM  TBL_KIOSK_MASTER MST, "
	 * +
	 * " TBL_SWAYAM_TXN_REPORT DTL WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND "
	 * +
	 * " TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND "
	 * +
	 * " TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )     GROUP BY  MST.BRANCH_CODE, MST.VENDOR"
	 * +
	 * " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY  DATA1.BRANCH_CODE"
	 * + " ORDER BY DATA1.BRANCH_CODE ) M ON B.BRANCH_CODE = M.BRANCH_CODE" +
	 * " WHERE b.branch_code = m.branch_code and " +
	 * " b.crcl_code=:in_circle_code and b.network=:in_network_code and " +
	 * " b.mod_code=:in_module_code  GROUP BY b.region , b.region" +
	 * " ORDER BY b.region ", nativeQuery=true,
	 * countQuery=" SELECT  count(b.region) CODE, b.region NAME, 	count(b.branch_code) BRANCH_CODE_COUNT, "
	 * +
	 * " sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
	 * +
	 * "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT, SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
	 * +
	 * " SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
	 * + " NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS," +
	 * " (  CASE  WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),  0  ) = 0 THEN 0   " +
	 * " ELSE ROUND(   SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ),"
	 * +
	 * " 2    )    END ) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (   SELECT  BRANCH_NO, "
	 * +
	 * " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS   FROM   TBL_BRANCH_TXN_DAILY DATA2"
	 * +
	 * " WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * +
	 * " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )    GROUP BY   BRANCH_NO  ) BR_TXN "
	 * +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN ( SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
	 * +
	 * "  SUM( CASE VENDOR  WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END ) LIPI_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS    ELSE 0  END ) LIPI_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS  ELSE 0   END  ) FORBES_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS  ELSE 0   END   ) FORBES_TXN_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS   ELSE 0    END ) CMS_KIOSK_CNT,"
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END   ) CMS_TXN_CNT,"
	 * + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS " +
	 * " FROM  (  SELECT MST.BRANCH_CODE, MST.VENDOR, COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,"
	 * + " NVL(SUM(DTL.NO_OF_TXNS), 0 ) SWAYAM_TXNS   FROM  TBL_KIOSK_MASTER MST, "
	 * +
	 * " TBL_SWAYAM_TXN_REPORT DTL WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND "
	 * +
	 * " TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND "
	 * +
	 * " TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )     GROUP BY  MST.BRANCH_CODE, MST.VENDOR"
	 * +
	 * " ORDER BY  MST.BRANCH_CODE,  MST.VENDOR  ) DATA1  GROUP BY  DATA1.BRANCH_CODE"
	 * + " ORDER BY DATA1.BRANCH_CODE ) M ON B.BRANCH_CODE = M.BRANCH_CODE" +
	 * " WHERE b.branch_code = m.branch_code and " +
	 * " b.crcl_code=:in_circle_code and b.network=:in_network_code and " +
	 * " b.mod_code=:in_module_code  GROUP BY b.region , b.region " +
	 * " ORDER BY b.region ")
	 * 
	 * Page<DrillDown> findByDateSearchNext(@Param("fromdate") String
	 * fromdate,@Param("todate") String todate,@Param("in_circle_code") String
	 * in_circle_code,@Param("in_network_code") String
	 * in_network_code,@Param("in_module_code") String
	 * in_module_code,@Param("searchText") String searchText, Pageable pageable);
	 */
/*
    
    
    @Query(value=" SELECT b.branch_name NAME , b.branch_code CODE, "
    	    + "  count(b.branch_code) BRANCH_CODE_COUNT, "
    	    + "  sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS,"
    	    + "  SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT,"
    	    + "  SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT,"
    	    + "  SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT,"
    	    + "  SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,"
    	    + "  SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,"
    	    + "  SUM(M.CMS_TXN_CNT) CMS_TXN_CNT,"
    	    + "  SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,"
    	    + "  SUM(BR_TXN.MANUAL_TXNS) MANUAL_TXNS,"
    	    + "   ("
    	    + " CASE"
    	    + "   WHEN NVL("
    	    + " SUM(M.TOTAL_SWAYAM_TXNS),"
    	    + "  0 "
    	    + "  ) = 0 THEN 0"
    	    + "  ELSE ROUND("
    	    + "  SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + SUM(BR_TXN.MANUAL_TXNS)"
    	    + "  ),"
    	    + "  2   )  "
    	    + " END ) MIG_PRCNT"
    	    + " FROM"
    	    + " TBL_BRANCH_MASTER B"
    	    + " INNER JOIN ("
    	    + " SELECT  BRANCH_NO,"
    	    + " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
    	    + " FROM "
    	    + " TBL_BRANCH_TXN_DAILY DATA2"
    	    + " WHERE"
    	    + " TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
    	    + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	    + " GROUP BY"
    	    + " BRANCH_NO    ) BR_TXN "
    	    + " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE"
    	    + " INNER JOIN ( SELECT BRANCH_CODE, "
    	    + " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	    + " SUM("
    	    + " CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS"
    	    + " ELSE 0"
    	    + " END  ) LIPI_KIOSK_CNT, "
    	    + " SUM( CASE VENDOR"
    	    + " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
    	    + " ELSE 0"
    	    + " END ) LIPI_TXN_CNT,"
    	    + " SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
    	    + " ELSE 0"
    	    + " END   ) FORBES_KIOSK_CNT, "
    	    + " SUM(    CASE VENDOR"
    	    + " WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
    	    + " ELSE 0"
    	    + " END  ) FORBES_TXN_CNT,"
    	    + " SUM(  CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
    	    + " ELSE 0"
    	    + " END  ) CMS_KIOSK_CNT,"
    	    + " SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
    	    + " ELSE 0"
    	    + "  END  ) CMS_TXN_CNT,"
    	    + "  SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
    	    //+ "--nvl(sum(distinct data2.manual_txns),0) manual_txns  
    	            + " FROM "
    	            + "   (  SELECT MST.BRANCH_CODE,  MST.VENDOR, "
    	            + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  "
    	            + " NVL(  SUM(DTL.NO_OF_TXNS),"
    	            + " 0   ) SWAYAM_TXNS"
    	            + " FROM  TBL_KIOSK_MASTER MST,"
    	            + " TBL_SWAYAM_TXN_REPORT DTL "
    	            //+ "  --  where mst.kiosk_id = dtl.kiosk_id( )  
    	             +  " WHERE"
    	             + "  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) ) "
    	             + "   AND  TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN "
    	             + " TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	             + "  GROUP BY"
    	             + " MST.BRANCH_CODE,  MST.VENDOR"
    	             + "   ORDER BY   MST.BRANCH_CODE,  MST.VENDOR ) DATA1   "
    	            // + " --where data1.branch_code = data2.branch_no  "
    	             + " GROUP BY "
    	             + " DATA1.BRANCH_CODE"
    	             + "  ORDER BY DATA1.BRANCH_CODE"
    	             + "   ) M "
    	             + " ON B.BRANCH_CODE = M.BRANCH_CODE "
    	             + "  WHERE "
    	             + " b.branch_code = m.branch_code and "
    	             + "   b.crcl_code=:in_circle_code and "
    	             + " b.network=:in_network_code and "
    	             + " b.mod_code=:in_module_code and "
    	             + " b.region=:in_region_code "
    	             + "  GROUP BY b.branch_name,b.branch_code ",  
    	             countQuery=
    	            " SELECT   count(b.branch_code) BRANCH_CODE_COUNT "
    	                + " FROM "
    	                + " TBL_BRANCH_MASTER B"
    	                + " INNER JOIN ("
    	                + " SELECT  BRANCH_NO,"
    	                + " NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
    	                + " FROM "
    	                + " TBL_BRANCH_TXN_DAILY DATA2"
    	                + " WHERE"
    	                + " TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') "
    	                + " BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	                + " GROUP BY"
    	                + " BRANCH_NO    ) BR_TXN "
    	                + " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE"
    	                + " INNER JOIN ( SELECT BRANCH_CODE, "
    	                + " SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS,"
    	                + " SUM("
    	                + " CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS"
    	                + " ELSE 0"
    	                + " END  ) LIPI_KIOSK_CNT, "
    	                + " SUM( CASE VENDOR"
    	                + " WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS"
    	                + " ELSE 0"
    	                + " END ) LIPI_TXN_CNT,"
    	                + " SUM(  CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS"
    	                + " ELSE 0"
    	                + " END   ) FORBES_KIOSK_CNT, "
    	                + " SUM(    CASE VENDOR"
    	                + " WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS"
    	                + " ELSE 0"
    	                + " END  ) FORBES_TXN_CNT,"
    	                + " SUM(  CASE VENDOR   WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS"
    	                + " ELSE 0"
    	                + " END  ) CMS_KIOSK_CNT,"
    	                + " SUM( CASE VENDOR   WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS"
    	                + " ELSE 0"
    	                + "  END  ) CMS_TXN_CNT,"
    	                + "  SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS "
    	                //+ "--nvl(sum(distinct data2.manual_txns),0) manual_txns  
    	                       + " FROM "
    	                       + "   (  SELECT MST.BRANCH_CODE,  MST.VENDOR, "
    	                       + " COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  "
    	                       + " NVL(  SUM(DTL.NO_OF_TXNS),"
    	                       + " 0   ) SWAYAM_TXNS"
    	                       + " FROM  TBL_KIOSK_MASTER MST,"
    	                       + " TBL_SWAYAM_TXN_REPORT DTL "
    	                       //+ "  --  where mst.kiosk_id = dtl.kiosk_id( )  
    	                        +  " WHERE"
    	                        + "  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) ) "
    	                        + "   AND  TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN "
    	                        + " TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )"
    	                        + "  GROUP BY"
    	                        + " MST.BRANCH_CODE,  MST.VENDOR"
    	                        + "   ORDER BY   MST.BRANCH_CODE,  MST.VENDOR ) DATA1   "
    	                       // + " --where data1.branch_code = data2.branch_no  "
    	                        + " GROUP BY "
    	                        + " DATA1.BRANCH_CODE"
    	                        + "  ORDER BY DATA1.BRANCH_CODE"
    	                        + "   ) M "
    	                        + " ON B.BRANCH_CODE = M.BRANCH_CODE "
    	                        + "  WHERE "
    	                        + " b.branch_code = m.branch_code and "
    	                        + "   b.crcl_code=:in_circle_code and "
    	                        + " b.network=:in_network_code and "
    	                        + " b.mod_code=:in_module_code and "
    	                        + " b.region=:in_region_code "
    	                        + "  GROUP BY b.branch_name, b.branch_code",
    	    nativeQuery=true)  */
    
    
	/*
	 * @Query(
	 * value=" SELECT  b.branch_name NAME, b.branch_code CODE,count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, "
	 * +
	 * " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, "
	 * +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,  NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
	 * + " (CASE WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),    0 ) = 0 THEN 0   " +
	 * " ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ) , "
	 * +
	 * " 2 )  END) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (SELECT  BRANCH_NO,NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS "
	 * +
	 * " FROM  TBL_BRANCH_TXN_DAILY DATA2    WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * +
	 * " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )   GROUP BY  BRANCH_NO  ) BR_TXN "
	 * +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END  ) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS   ELSE 0   END  ) LIPI_TXN_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS ELSE 0   END  ) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END  ) FORBES_TXN_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS    ELSE 0    END   ) CMS_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0  END  ) CMS_TXN_CNT, "
	 * + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  " +
	 * " FROM  ( SELECT  MST.BRANCH_CODE,  MST.VENDOR,  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL( SUM(DTL.NO_OF_TXNS), "
	 * +
	 * "  0  ) SWAYAM_TXNS  FROM  TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL "
	 * + "  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND " +
	 * "  TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * +
	 * "  GROUP BY  MST.BRANCH_CODE,  MST.VENDOR  ORDER BY  MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
	 * +
	 * "  GROUP BY DATA1.BRANCH_CODE  ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
	 * +
	 * "  WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
	 * + "   b.mod_code=:in_module_code and b.region=:in_region_code  " +
	 * " GROUP BY b.branch_name , b.branch_code " + " ORDER BY b.branch_name "
	 * 
	 * , nativeQuery = true,countQuery =
	 * " SELECT  count(b.branch_code) as code,count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, "
	 * +
	 * " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, "
	 * +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,  NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
	 * + " (CASE WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),    0 ) = 0 THEN 0   " +
	 * " ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
	 * +
	 * " 2 )  END) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (SELECT  BRANCH_NO,NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
	 * +
	 * " FROM  TBL_BRANCH_TXN_DAILY DATA2    WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * +
	 * " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )   GROUP BY  BRANCH_NO  ) BR_TXN "
	 * +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END  ) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS   ELSE 0   END  ) LIPI_TXN_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS ELSE 0   END  ) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END  ) FORBES_TXN_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS    ELSE 0    END   ) CMS_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0  END  ) CMS_TXN_CNT, "
	 * + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  " +
	 * " FROM  ( SELECT  MST.BRANCH_CODE,  MST.VENDOR,  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL( SUM(DTL.NO_OF_TXNS), "
	 * +
	 * "  0  ) SWAYAM_TXNS  FROM  TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL "
	 * + "  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND" +
	 * "  TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * +
	 * "  GROUP BY  MST.BRANCH_CODE,  MST.VENDOR  ORDER BY  MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
	 * +
	 * "  GROUP BY DATA1.BRANCH_CODE  ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
	 * +
	 * " WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
	 * +
	 * "   b.mod_code=:in_module_code and b.region=:in_region_code  GROUP BY b.branch_name, "
	 * + " b.branch_code " + " ORDER BY b.branch_name ")
	 */
    
    @Query(value=" SELECT  b.branch_name NAME, b.branch_code CODE,count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, "
    		+ " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
    		+ " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, "
    		+ " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,  NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
    		+ " (CASE WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),    0 ) = 0 THEN 0   "
    		+ " ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ) , "
    		+ " 2 )  END) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (SELECT  BRANCH_NO,NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS "
    		+ " FROM  TBL_BRANCH_TXN_DAILY DATA2    WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
    		+ " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )   GROUP BY  BRANCH_NO  ) BR_TXN "
    		+ " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
    		+ " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END  ) LIPI_KIOSK_CNT, "
    		+ " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS   ELSE 0   END  ) LIPI_TXN_CNT, "
    		+ " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS ELSE 0   END  ) FORBES_KIOSK_CNT, "
    		+ " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END  ) FORBES_TXN_CNT, "
    		+ " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS    ELSE 0    END   ) CMS_KIOSK_CNT, "
    		+ " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0  END  ) CMS_TXN_CNT, "
    		+ " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  "
    		+ " FROM  ( SELECT  MST.BRANCH_CODE,  MST.VENDOR,  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL( SUM(DTL.NO_OF_TXNS), "
    		+ "  0  ) SWAYAM_TXNS  FROM  TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL "
    		+ "  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND "
    		+ "  TXN_DATE BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    		+ "  GROUP BY  MST.BRANCH_CODE,  MST.VENDOR  ORDER BY  MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
    		+ "  GROUP BY DATA1.BRANCH_CODE  ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
    		+ "  WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
    		+ "   b.mod_code=:in_module_code and b.region=:in_region_code  "
    		+ " GROUP BY b.branch_name , b.branch_code " 
    		+ " ORDER BY b.branch_name "
    		
    		,
    		nativeQuery = true,countQuery = " SELECT  count(b.branch_code) as code,count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, "
    	    		+ " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
    	    		+ " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, "
    	    		+ " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,  NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
    	    		+ " (CASE WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),    0 ) = 0 THEN 0   "
    	    		+ " ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
    	    		+ " 2 )  END) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (SELECT  BRANCH_NO,NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
    	    		+ " FROM  TBL_BRANCH_TXN_DAILY DATA2    WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
    	    		+ " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )   GROUP BY  BRANCH_NO  ) BR_TXN "
    	    		+ " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
    	    		+ " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END  ) LIPI_KIOSK_CNT, "
    	    		+ " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS   ELSE 0   END  ) LIPI_TXN_CNT, "
    	    		+ " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS ELSE 0   END  ) FORBES_KIOSK_CNT, "
    	    		+ " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END  ) FORBES_TXN_CNT, "
    	    		+ " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS    ELSE 0    END   ) CMS_KIOSK_CNT, "
    	    		+ " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0  END  ) CMS_TXN_CNT, "
    	    		+ " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  "
    	    		+ " FROM  ( SELECT  MST.BRANCH_CODE,  MST.VENDOR,  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL( SUM(DTL.NO_OF_TXNS), "
    	    		+ "  0  ) SWAYAM_TXNS  FROM  TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL "
    	    		+ "  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND"
    	    		+ "  TXN_DATE BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    	    		+ "  GROUP BY  MST.BRANCH_CODE,  MST.VENDOR  ORDER BY  MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
    	    		+ "  GROUP BY DATA1.BRANCH_CODE  ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
    	    		+ " WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
    	    		+ "   b.mod_code=:in_module_code and b.region=:in_region_code  GROUP BY b.branch_name, "
    	    		+ " b.branch_code "
    	    		+ " ORDER BY b.branch_name ")
    
    Page<DrillDown> findByDate(@Param("fromdate") String fromdate,@Param("todate") String todate,@Param("in_circle_code") String in_circle_code,@Param("in_network_code") String in_network_code,@Param("in_module_code") String in_module_code,@Param("in_region_code") String in_region_code,Pageable pageable);

	
	/*
	 * @Query(
	 * value=" SELECT  b.branch_name NAME, b.branch_code CODE,count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, "
	 * +
	 * " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, "
	 * +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,  NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
	 * + " (CASE WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),    0 ) = 0 THEN 0   " +
	 * " ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ) , "
	 * +
	 * " 2 )  END) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (SELECT  BRANCH_NO,NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS "
	 * +
	 * " FROM  TBL_BRANCH_TXN_DAILY DATA2    WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * +
	 * " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )   GROUP BY  BRANCH_NO  ) BR_TXN "
	 * +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END  ) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS   ELSE 0   END  ) LIPI_TXN_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS ELSE 0   END  ) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END  ) FORBES_TXN_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS    ELSE 0    END   ) CMS_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0  END  ) CMS_TXN_CNT, "
	 * + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  " +
	 * " FROM  ( SELECT  MST.BRANCH_CODE,  MST.VENDOR,  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL( SUM(DTL.NO_OF_TXNS), "
	 * +
	 * "  0  ) SWAYAM_TXNS  FROM  TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL "
	 * + "  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND " +
	 * "  TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * +
	 * "  GROUP BY  MST.BRANCH_CODE,  MST.VENDOR  ORDER BY  MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
	 * +
	 * "  GROUP BY DATA1.BRANCH_CODE  ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
	 * +
	 * "  WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
	 * +
	 * "   b.mod_code=:in_module_code and b.region=:in_region_code and ( b.BRANCH_CODE=:searchText or b.branch_name = :searchText)  "
	 * + " GROUP BY b.branch_name , b.branch_code " + " ORDER BY b.branch_name "
	 * 
	 * , nativeQuery = true,countQuery =
	 * " SELECT  count(b.branch_code) as code,count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, "
	 * +
	 * " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, "
	 * +
	 * " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,  NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
	 * + " (CASE WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),    0 ) = 0 THEN 0   " +
	 * " ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
	 * +
	 * " 2 )  END) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (SELECT  BRANCH_NO,NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
	 * +
	 * " FROM  TBL_BRANCH_TXN_DAILY DATA2    WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
	 * +
	 * " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )   GROUP BY  BRANCH_NO  ) BR_TXN "
	 * +
	 * " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END  ) LIPI_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS   ELSE 0   END  ) LIPI_TXN_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS ELSE 0   END  ) FORBES_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END  ) FORBES_TXN_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS    ELSE 0    END   ) CMS_KIOSK_CNT, "
	 * +
	 * " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0  END  ) CMS_TXN_CNT, "
	 * + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  " +
	 * " FROM  ( SELECT  MST.BRANCH_CODE,  MST.VENDOR,  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL( SUM(DTL.NO_OF_TXNS), "
	 * +
	 * "  0  ) SWAYAM_TXNS  FROM  TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL "
	 * + "  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND" +
	 * "  TO_DATE(TXN_DATE,'dd-mm-yyyy') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
	 * +
	 * "  GROUP BY  MST.BRANCH_CODE,  MST.VENDOR  ORDER BY  MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
	 * +
	 * "  GROUP BY DATA1.BRANCH_CODE  ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
	 * +
	 * " WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
	 * +
	 * "   b.mod_code=:in_module_code and b.region=:in_region_code and ( b.BRANCH_CODE=:searchText or b.branch_name = :searchText) "
	 * + "  GROUP BY b.branch_name,  b.branch_code " + " ORDER BY b.branch_name ")
	 */
	  
    @Query(
    		  value=" SELECT  b.branch_name NAME, b.branch_code CODE,count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, "
    		  +
    		  " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
    		  +
    		  " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, "
    		  +
    		  " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,  NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
    		  + " (CASE WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),    0 ) = 0 THEN 0   " +
    		  " ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ) , "
    		  +
    		  " 2 )  END) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (SELECT  BRANCH_NO,NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS "
    		  +
    		  " FROM  TBL_BRANCH_TXN_DAILY DATA2    WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
    		  +
    		  " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )   GROUP BY  BRANCH_NO  ) BR_TXN "
    		  +
    		  " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
    		  +
    		  " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END  ) LIPI_KIOSK_CNT, "
    		  +
    		  " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS   ELSE 0   END  ) LIPI_TXN_CNT, "
    		  +
    		  " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS ELSE 0   END  ) FORBES_KIOSK_CNT, "
    		  +
    		  " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END  ) FORBES_TXN_CNT, "
    		  +
    		  " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS    ELSE 0    END   ) CMS_KIOSK_CNT, "
    		  +
    		  " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0  END  ) CMS_TXN_CNT, "
    		  + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  " +
    		  " FROM  ( SELECT  MST.BRANCH_CODE,  MST.VENDOR,  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL( SUM(DTL.NO_OF_TXNS), "
    		  +
    		  "  0  ) SWAYAM_TXNS  FROM  TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL "
    		  + "  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND " +
    		  "  TXN_DATE BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    		  +
    		  "  GROUP BY  MST.BRANCH_CODE,  MST.VENDOR  ORDER BY  MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
    		  +
    		  "  GROUP BY DATA1.BRANCH_CODE  ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
    		  +
    		  "  WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
    		  +
    		  "   b.mod_code=:in_module_code and b.region=:in_region_code and ( b.BRANCH_CODE=UPPER(:searchText) or b.branch_name = UPPER(:searchText))  "
    		  + " GROUP BY b.branch_name , b.branch_code " + " ORDER BY b.branch_name "
    		  
    		  , nativeQuery = true,countQuery =
    		  " SELECT  count(b.branch_code) as code,count(b.branch_code) BRANCH_CODE_COUNT, sum(m.total_swayam_kiosks) TOTAL_SWAYAM_KIOSKS, "
    		  +
    		  " SUM(M.LIPI_KIOSK_CNT) LIPI_KIOSK_CNT, SUM(M.LIPI_TXN_CNT) LIPI_TXN_CNT, SUM(M.FORBES_KIOSK_CNT) FORBES_KIOSK_CNT, "
    		  +
    		  " SUM(M.FORBES_TXN_CNT) FORBES_TXN_CNT,SUM(M.CMS_KIOSK_CNT) CMS_KIOSK_CNT,SUM(M.CMS_TXN_CNT) CMS_TXN_CNT, "
    		  +
    		  " SUM(M.TOTAL_SWAYAM_TXNS) TOTAL_SWAYAM_TXNS,  NVL(SUM(BR_TXN.MANUAL_TXNS),0) MANUAL_TXNS, "
    		  + " (CASE WHEN NVL(  SUM(M.TOTAL_SWAYAM_TXNS),    0 ) = 0 THEN 0   " +
    		  " ELSE ROUND(SUM(M.TOTAL_SWAYAM_TXNS) * 100 / (SUM(M.TOTAL_SWAYAM_TXNS) + NVL(SUM(BR_TXN.MANUAL_TXNS),0) ), "
    		  +
    		  " 2 )  END) MIG_PRCNT  FROM  TBL_BRANCH_MASTER B  LEFT JOIN (SELECT  BRANCH_NO,NVL(SUM(NO_OF_ACCOUNTS),0) MANUAL_TXNS"
    		  +
    		  " FROM  TBL_BRANCH_TXN_DAILY DATA2    WHERE  TO_DATE(LAST_PBK_DT,'yyyy-mm-dd') BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) "
    		  +
    		  " AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') )   GROUP BY  BRANCH_NO  ) BR_TXN "
    		  +
    		  " ON BR_TXN.BRANCH_NO = B.BRANCH_CODE  INNER JOIN (SELECT  BRANCH_CODE, SUM(NO_OF_KIOSKS) TOTAL_SWAYAM_KIOSKS, "
    		  +
    		  " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.NO_OF_KIOSKS  ELSE 0  END  ) LIPI_KIOSK_CNT, "
    		  +
    		  " SUM( CASE VENDOR   WHEN 'LIPI'   THEN DATA1.SWAYAM_TXNS   ELSE 0   END  ) LIPI_TXN_CNT, "
    		  +
    		  " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.NO_OF_KIOSKS ELSE 0   END  ) FORBES_KIOSK_CNT, "
    		  +
    		  " SUM( CASE VENDOR  WHEN 'FORBES'   THEN DATA1.SWAYAM_TXNS   ELSE 0    END  ) FORBES_TXN_CNT, "
    		  +
    		  " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.NO_OF_KIOSKS    ELSE 0    END   ) CMS_KIOSK_CNT, "
    		  +
    		  " SUM( CASE VENDOR  WHEN 'CMS'   THEN DATA1.SWAYAM_TXNS   ELSE 0  END  ) CMS_TXN_CNT, "
    		  + " SUM(SWAYAM_TXNS) TOTAL_SWAYAM_TXNS  " +
    		  " FROM  ( SELECT  MST.BRANCH_CODE,  MST.VENDOR,  COUNT(DISTINCT MST.KIOSK_ID) NO_OF_KIOSKS,  NVL( SUM(DTL.NO_OF_TXNS), "
    		  +
    		  "  0  ) SWAYAM_TXNS  FROM  TBL_KIOSK_MASTER MST,  TBL_SWAYAM_TXN_REPORT DTL "
    		  + "  WHERE  UPPER(MST.KIOSK_ID) = UPPER(DTL.KIOSK_ID(+) )  AND" +
    		  "  TXN_DATE BETWEEN TRUNC(TO_DATE(:fromdate,'dd-mm-yyyy') ) AND TRUNC(TO_DATE(:todate,'dd-mm-yyyy') ) "
    		  +
    		  "  GROUP BY  MST.BRANCH_CODE,  MST.VENDOR  ORDER BY  MST.BRANCH_CODE, MST.VENDOR ) DATA1 "
    		  +
    		  "  GROUP BY DATA1.BRANCH_CODE  ORDER BY DATA1.BRANCH_CODE) M ON B.BRANCH_CODE = M.BRANCH_CODE "
    		  +
    		  " WHERE b.branch_code = m.branch_code and  b.crcl_code=:in_circle_code and b.network=:in_network_code and "
    		  +
    		  "   b.mod_code=:in_module_code and b.region=:in_region_code and ( b.BRANCH_CODE=UPPER(:searchText) or b.branch_name = UPPER(:searchText)) "
    		  + "  GROUP BY b.branch_name,  b.branch_code " + " ORDER BY b.branch_name ")
    
	  Page<DrillDown> findByDateSearchNext(@Param("fromdate") String
	  fromdate,@Param("todate") String todate,@Param("in_circle_code") String
	  in_circle_code,@Param("in_network_code") String
	  in_network_code,@Param("in_module_code") String
	  in_module_code,@Param("in_region_code") String
	  in_region_code, @Param("searchText") String searchText,Pageable pageable);
	 
    
 // 12c
 	//	@Query(value="select to_date(last_pbk_dt,'yyyy-mm-dd') from tbl_branch_txn_daily order by last_pbk_dt desc fetch first 1 row only ",nativeQuery = true )
 // new query commented for data type change in last_pbk_dt
 	//		@Query(value="select to_char(to_date(last_pbk_dt,'dd-mm-yyyy'),'dd-mm-yyyy') from tbl_branch_txn_daily order by last_pbk_dt desc fetch first 1 row only",nativeQuery = true)
 		// new query
 			@Query(value="select to_char(last_pbk_dt,'dd-mm-yyyy') from tbl_branch_txn_daily order by last_pbk_dt desc fetch first 1 row only",nativeQuery = true)
 	
 			//for 11g
 	//	@Query(value="select to_date(last_pbk_dt,'dd-mm-yyyy') from tbl_branch_txn_daily where rownum <= 1 order by last_pbk_dt desc ",nativeQuery = true )
 	//		@Query(value="select to_char(to_date(last_pbk_dt,'dd-mm-yyyy'),'dd-mm-yyyy') from tbl_branch_txn_daily where rownum <= 1 order by last_pbk_dt desc ",nativeQuery = true )
 			
    //@Query(value="select to_char(last_pbk_dt,'dd-mm-yyyy') from tbl_branch_txn_daily where rownum <= 1 order by last_pbk_dt desc",nativeQuery = true)
 			String findCurrentDateAuditJob();
    
}
