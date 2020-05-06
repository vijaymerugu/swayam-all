package sbi.kiosk.swayam.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

	//User findByUsername(String username);
	
	User findByPfId(String pfId);
	
	@Query(value ="select * from TBL_USER WHERE CIRCLE= :circle AND USERNAME != :username AND ROLE NOT IN ('SA','LA','CC','C','CMS')",
			nativeQuery=true)
	public List<User> fetchAllUsersByCircleAdmin(@Param("username") String username, @Param("circle") String circle);
	
	@Query(value ="select * from TBL_USER WHERE CIRCLE= :circle AND USERNAME != :username AND ROLE NOT IN ('SA','LA','CC','CMF','C')",
			nativeQuery=true)
	public List<User> fetchAllCmsUsersByCircleAdmin(@Param("username") String username, @Param("circle") String circle);
	
	@Query(value ="SELECT * FROM TBL_USER WHERE USERNAME IN (SELECT USERNAME FROM TBL_USER_KIOSK_MAPPING WHERE USERNAME NOT IN (SELECT CMF_USERNAME FROM TBL_CMS_CMF_MAPPING) AND KIOSK_ID IN (SELECT KIOSK_ID FROM TBL_KIOSK_MASTER WHERE BRANCH_CODE IN (SELECT BRANCH_CODE FROM TBL_BRANCH_MASTER WHERE CIRCLE =:circle)))",
			nativeQuery=true)
	public List<User> fetchAllCmfUsersByCircleAndInUserKioskMapping(@Param("circle") String circle);
    
    @Query(value="SELECT COUNT(*) FROM TBL_USER WHERE PF_ID=:pfid",nativeQuery=true)
    int geByPfId(@Param("pfid") String pfId);
    
    // for api userId
    @Query(value="SELECT PF_ID FROM TBL_USER WHERE PF_ID=:pfid",nativeQuery=true)
    String findIdByPfId(@Param("pfid") String pfid);
    
    @Query(value=" select * from TBL_USER where USER_ID in(select user_id from TBL_USER_KIOSK_MAPPING where KIOSK_ID=:kioskId )",nativeQuery=true)
    User findIdByKioskId(@Param("kioskId") String kioskId);

}
