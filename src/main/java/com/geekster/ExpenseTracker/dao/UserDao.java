package com.geekster.ExpenseTracker.dao;

import com.geekster.ExpenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findAllByUserName(String userName);


}
