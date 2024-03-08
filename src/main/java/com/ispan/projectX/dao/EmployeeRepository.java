package com.ispan.projectX.dao;


import com.ispan.projectX.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee findByEmployeeId(Integer Id);

//    @Query("from Complaint where employee = :employee")
//    List<Complaint> findComplaintByEmployee(@Param("employee") Employee employee);



}
