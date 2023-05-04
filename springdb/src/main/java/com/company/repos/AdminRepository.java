package com.company.repos;


import com.company.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("Select u from Admin u where u.username=:username")
    public Admin getAdmin(@Param("username")String username);
}
