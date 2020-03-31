package sbi.kiosk.swayam.kioskmanagement.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sbi.kiosk.swayam.common.entity.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
	
	@Query("FROM User where ENABLED='1' AND USERNAME !=:username")
	List<User> findAll(@Param("username") String username);
	
	@Query(value="select * FROM TBL_USER where ENABLED='1'", nativeQuery = true)
	List<User> findAll(PageRequest pageRequest);
	
	
	
	@Query(value = "SELECT * FROM TBL_USER where ENABLED='1' AND ROLE NOT IN ('SA','CC','LA') AND CIRCLE=:circle", nativeQuery = true)
	List<User> findAllForLA(@Param("circle") String circle);
	
	@Query(value = "SELECT * FROM TBL_USER where ENABLED='1' AND ROLE NOT IN ('SA') AND USERNAME !=:username", nativeQuery = true)
	List<User> findAllForCC(@Param("username") String username);
	
	
	//String GET_USERS_ON_USERNAME = " select * from TBL_USER u  where  u.USERNAME like '%?1%' ";
	 @Query(value="select * from TBL_USER where USERNAME LIKE  %?1%",nativeQuery=true)
	 List<User> findByUserName(String userName);
	//@Query( value=GET_USERS_ON_USERNAME,nativeQuery=true)
	//List<User> findByUserName(String userName);


	@Query(value = "select user_id_seq.nextval from dual", nativeQuery = true)
	String findSeq();

	@Query(value = "UPDATE TBL_USER u SET USERNAME=?1 ,ROLE=?2 ,KIOSK_ID=?3,"
			+ " FIRSTNAME=?4 ,LASTNAME=?5,ADDRESS=?6,ADDRESSLINE1=?7, "
			+ " ADDRESSLINE2=?8,CITY=?9,STATE=?10,MAIL_ID=?11,MOBILENO=?12,GENDER=?13,"
			+ "MODIFIED_BY=?14,MODIFIED_DATE=?15 WHERE u.USER_ID=?16 and u.ENABLED=?16", nativeQuery = true)

	boolean updateByUserId(User User);

	@Query(value = "UPDATE TBL_USER e set ENABLED=?1 where e.USER_ID=?2 ", nativeQuery = true)
	boolean deActivateUserById(String enabled ,Integer userId);
	
	User findByUsername(String username);
	
}
