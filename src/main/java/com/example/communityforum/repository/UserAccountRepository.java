package com.example.communityforum.repository;

import com.example.communityforum.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
}
