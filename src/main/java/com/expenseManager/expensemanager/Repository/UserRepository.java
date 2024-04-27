package com.expenseManager.expensemanager.Repository;

import com.expenseManager.expensemanager.Enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

//    @Modifying
//    @Query(value = "UPDATE User_table e SET e.AmountPaid = e.AmountPaid + :additionalAmount WHERE e.Username = :name;", nativeQuery = true)
//    void updateExpenseAmount(@Param("name") String expenseId, @Param("additionalAmount") float additionalAmount);

    @Modifying
    @Query(value = "UPDATE User_table e SET e.Amount_Paid = e.Amount_Paid + :additionalAmount WHERE e.Username = :username", nativeQuery = true)
    void updateExpenseAmount(@Param("username") String username, @Param("additionalAmount") float additionalAmount);

    @Modifying
    @Query(value = "UPDATE User_table e SET e.Amount_Paid = e.Amount_Paid - :additionalAmount WHERE e.Username = :username", nativeQuery = true)
    void updateExpenseRemoveAmount(@Param("username") String username, @Param("additionalAmount") float additionalAmount);


    @Modifying
    @Query(value = "UPDATE User_table e SET e.Share = e.Share + :additionalAmount , e.Excess_Amount = :excessAmount, e.Due_Amount = :dueAmount WHERE e.Username = :username", nativeQuery = true)
    void updateShareAmountwhenadd(@Param("username") String username, @Param("additionalAmount") float additionalAmount, @Param("excessAmount") float excessAmount, @Param("dueAmount") float dueAmount);

    @Modifying
    @Query(value = "UPDATE User_table e SET e.Share = e.Share - :additionalAmount , e.Excess_Amount = :excessAmount, e.Due_Amount = :dueAmount WHERE e.Username = :username", nativeQuery = true)
    void updateShareAmountwhenRemove(@Param("username") String username, @Param("additionalAmount") float additionalAmount, @Param("excessAmount") float excessAmount, @Param("dueAmount") float dueAmount);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.DueAmount = ?1, u.ExcessAmount = ?2 where u.Username = ?3")
    void updateUserBalance(double DueAmount, double ExcessAmount, String Username);

    @Transactional
    @Modifying
    @Query("delete from User u where u.id = ?1")
    void deleteByUserID(Long id);

    @Query("select u from User u where u.Username = ?1")
    Optional<User> findUserByName(String Username);


}
