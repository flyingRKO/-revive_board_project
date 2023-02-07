package com.example.communityforum.dto;

import com.example.communityforum.domain.UserAccount;
import com.example.communityforum.domain.constant.Role;
import lombok.Getter;

@Getter
public class UserAccountDto {
    private  final String userId;
    private  final String password;
    private  final String userName;
    private  final String phone;
    private  final Role role;
    private  final boolean term;

    private UserAccountDto(String userId, String password, String userName, String phone, Role role, boolean term) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.role = role;
        this.term = term;
    }

    public static UserAccountDto of(String userId, String password, String userName, String phone, Role role, boolean term){
        return new UserAccountDto(userId, password, userName, phone, role, term);
    }

    public static UserAccountDto from(UserAccount entity){
        return new UserAccountDto(
                entity.getUserId(),
                entity.getPassword(),
                entity.getUserName(),
                entity.getPhone(),
                entity.getRole(),
                entity.isTerm()
        );
    }

    public UserAccount toEntity(){
        return UserAccount.of(
                userId,
                password,
                userName,
                phone,
                role,
                term
        );
    }
}
