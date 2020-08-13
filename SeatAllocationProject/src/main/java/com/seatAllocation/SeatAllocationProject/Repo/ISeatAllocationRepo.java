package com.seatAllocation.SeatAllocationProject.Repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seatAllocation.SeatAllocationProject.Model.EmployeeSeatModel;


@Repository
public interface ISeatAllocationRepo extends JpaRepository<EmployeeSeatModel,String> {

}
