package com.mealkit.repository;

import com.mealkit.domain.DTO.MainDto;
import com.mealkit.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByUserEmail(String userEmail);
    UserAccount findByUserEmail(String userEmail);

/*    @Query(value = "select count(user_email) from user_account where user_email=userEmail")
    int result(@Param("userEmail") String userEmail);*/
    //Optional<UserAccount> findByUserName(String username);

    @Query(value=" select home_title AS homeTitle, home_id AS homeId from admin_post ORDER BY modified_at DESC LIMIT 6 ", nativeQuery = true)
    List<MainDto> adminResult();

    @Query(value=" select user_post_id AS userPostId, title AS title from user_post ORDER BY modified_at DESC LIMIT 6", nativeQuery = true)
    List<MainDto> userResult();
}