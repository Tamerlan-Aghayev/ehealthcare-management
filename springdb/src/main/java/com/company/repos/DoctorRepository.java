package com.company.repos;


import com.company.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query("select u from Doctor u")
    List<Doctor> getAll();
    @Query("select u from Doctor u where u.name=:name and u.surname=:surname and u.field=:field")
    Doctor getByNameSurnameField(@Param("name") String name, @Param("surname")String surname, @Param("field")String field);
}
