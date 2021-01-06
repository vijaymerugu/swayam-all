package sbi.kiosk.swayam.billingpayment.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sbi.kiosk.swayam.common.entity.BpRequest;

@Repository
public interface BpRequestRepository extends CrudRepository<BpRequest, Integer>,
					PagingAndSortingRepository<BpRequest, Integer>  {
	
	@Query(value ="SELECT count(STATUS) FROM TBL_ALLREQUEST_DETAILS where STATUS=:status",nativeQuery=true)
	int findByStatus(@Param("status") String status);
	
	@Query(value ="SELECT count(STATUS) FROM TBL_ALLREQUEST_DETAILS where REQ_TYPE=:type",nativeQuery=true)
	int findCountRequestType(@Param("type") String type);
	
	/*
	 * @Query(value ="SELECT count(STATUS) FROM TBL_ALLREQUEST_DETAILS " +
	 * "where REQUEST_ID=:type AND STATUS='Approved' OR STATUS='Rejected'"
	 * ,nativeQuery=true) int findCountApproved(@Param("reqId") int reqId);
	 */
	
	
	@Query(value ="SELECT count(STATUS) FROM TBL_ALLREQUEST_DETAILS "
			+ "where REQUEST_ID=:reqId AND STATUS='Approved'",nativeQuery=true)
	int findApproved(@Param("reqId") int reqId);
	
	@Query(value ="SELECT count(STATUS) FROM TBL_ALLREQUEST_DETAILS "
			+ "where REQUEST_ID=:reqId AND STATUS='Rejected'",nativeQuery=true)
	int findRejected(@Param("reqId") int reqId);
	
	@Query(value ="SELECT * FROM TBL_ALLREQUEST_DETAILS where STATUS=:status",nativeQuery=true)
	Page<BpRequest> findPageByStatus(@Param("status") String status,Pageable pageable);
	
	
	@Query(value ="SELECT * FROM TBL_ALLREQUEST_DETAILS where REQ_TYPE=:type",nativeQuery=true)
	Page<BpRequest> findPageType(@Param("type") String type,Pageable pageable);
	
	
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE 	TBL_ALLREQUEST_DETAILS r SET r.STATUS='Approved'  , r.CLOSE_DATE=:date, r.CH_COMMNET=:commnets, r.CH_PFID=:pfid "
			+ "WHERE r.REQUEST_ID=:reqId ",nativeQuery = true)
	int updateApprove(@Param("reqId") Integer reqId,@Param("date")Date date,@Param("commnets") String commnets,
			@Param("pfid") String pfid); 
	
	
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE 	TBL_ALLREQUEST_DETAILS r SET r.STATUS='Rejected' , r.CLOSE_DATE=:date, r.CH_COMMNET=:commnets, r.CH_PFID=:pfid "
			+ "WHERE r.REQUEST_ID=:reqId ",nativeQuery = true)
	int updateReject(@Param("reqId") Integer reqId,@Param("date")Date date,@Param("commnets") String commnets,
			@Param("pfid") String pfid); 
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE 	TBL_ALLREQUEST_DETAILS r SET r.MK_COMMENT=:commnets, r.MK_PFID=:pfid "
			+ "WHERE r.REQUEST_ID=:reqId ",nativeQuery = true)
	int updateComment(@Param("reqId") Integer reqId,@Param("commnets") String commnets,
			@Param("pfid") String pfid); 
	

}
