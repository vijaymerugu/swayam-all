package sbi.kiosk.swayam.common.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import sbi.kiosk.swayam.common.entity.User;

@Repository
public interface UserRepositoryPaging extends PagingAndSortingRepository<User, Long> {

}
