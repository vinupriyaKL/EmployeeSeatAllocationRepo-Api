package com.seatAllocation.SeatAllocationProject.Service;

import java.util.List;

import com.seatAllocation.SeatAllocationProject.Exception.CommonException;
import com.seatAllocation.SeatAllocationProject.Exception.EmployeeNotFoundException;
import com.seatAllocation.SeatAllocationProject.Exception.NoDuplicateAllocationException;
import com.seatAllocation.SeatAllocationProject.Exception.SeatNotAvailableException;
import com.seatAllocation.SeatAllocationProject.Model.EmployeeSeatModel;

public interface ISeatAllocation {
	public EmployeeSeatModel allocateSeat(EmployeeSeatModel emp) throws Exception,CommonException,SeatNotAvailableException,NoDuplicateAllocationException;
	public EmployeeSeatModel updateSeat(EmployeeSeatModel emp) throws EmployeeNotFoundException,SeatNotAvailableException;
	public EmployeeSeatModel findEmployee(String emp) throws EmployeeNotFoundException;
	public List<EmployeeSeatModel>  findAllEmployeeDetails();
	public List<EmployeeSeatModel> findEmployeeBasedOnBuilding(String buildingNo);

	
}
