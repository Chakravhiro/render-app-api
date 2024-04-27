package com.expenseManager.expensemanager.Repository;

import com.expenseManager.expensemanager.Enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    @Modifying
    @Query(value = "UPDATE user_table SET Amount_Paid = Amount_Paid + :additionalAmount WHERE Username = :username", nativeQuery = true)
    void updateExpenseAmount(@Param("username") String username, @Param("additionalAmount") float additionalAmount);

    @Modifying
    @Query(value = "UPDATE user_table SET Amount_Paid = Amount_Paid - :additionalAmount WHERE Username = :username", nativeQuery = true)
    void updateExpenseRemoveAmount(@Param("username") String username, @Param("additionalAmount") float additionalAmount);


    @Modifying
    @Query(value = "UPDATE user_table SET Share = Share + :additionalAmount, Excess_Amount = :excessAmount, Due_Amount = :dueAmount WHERE Username = :username", nativeQuery = true)
    void updateShareAmountwhenadd(@Param("username") String username, @Param("additionalAmount") float additionalAmount, @Param("excessAmount") float excessAmount, @Param("dueAmount") float dueAmount);

    @Modifying
    @Query(value = "UPDATE User_table SET Share = Share - :additionalAmount , Excess_Amount = :excessAmount, Due_Amount = :dueAmount WHERE Username = :username", nativeQuery = true)
    void updateShareAmountwhenRemove(@Param("username") String username, @Param("additionalAmount") float additionalAmount, @Param("excessAmount") float excessAmount, @Param("dueAmount") float dueAmount);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user_table SET Due_Amount = ?1, Excess_Amount = ?2 WHERE Username = ?3", nativeQuery = true)

    void updateUserBalance(double DueAmount, double ExcessAmount, String Username);

    @Transactional
    @Modifying
    @Query(value = "delete from User where id = ?1",nativeQuery = true)
    void deleteByUserID(Long id);

    @Query(value = "select from User u where Username = ?1",nativeQuery = true)
    Optional<User> findUserByName(String Username);


}
