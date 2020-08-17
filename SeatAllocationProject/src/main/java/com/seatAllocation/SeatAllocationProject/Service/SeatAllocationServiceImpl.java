package com.seatAllocation.SeatAllocationProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seatAllocation.SeatAllocationProject.Exception.CommonException;
import com.seatAllocation.SeatAllocationProject.Exception.EmployeeNotFoundException;
import com.seatAllocation.SeatAllocationProject.Exception.NoDuplicateAllocationException;
import com.seatAllocation.SeatAllocationProject.Exception.SeatNotAvailableException;
import com.seatAllocation.SeatAllocationProject.Model.EmployeeSeatModel;
import com.seatAllocation.SeatAllocationProject.Repo.ISeatAllocationRepo;
@Service
public class SeatAllocationServiceImpl implements ISeatAllocation {
	@Autowired
	ISeatAllocationRepo repoEmp;

	@Override
	public EmployeeSeatModel allocateSeat(EmployeeSeatModel emp) throws SeatNotAvailableException,CommonException,NoDuplicateAllocationException,Exception {
		
		boolean flag=findAllDetails(emp);
		
		Optional<EmployeeSeatModel> empId=repoEmp.findById(emp.getEmpId());
		if(Integer.parseInt(emp.getBuildingNo())>8 || Integer.parseInt(emp.getFloorNo())>10) {
			throw new CommonException("Either Building no or floor no is invalid.Please select building no less than or eaual to 8 and Floor no less than or equal to 10");
		}
		if(empId.isPresent()) {
			throw new NoDuplicateAllocationException("One employee cannot have more than one seat");
		}
		if(emp.getBuildingNo()==null || emp.getEmpId()==null || emp.getEmpName()==null || emp.getFloorNo()==null || emp.getProjectCode()==null || emp.getSeatNo()==null) {
			throw new Exception("Please enter mandatory fields");
		}
		if(flag==true) {
			throw new SeatNotAvailableException("Seat is already Occupied");

			
		}
		else {
			return repoEmp.save(emp);
		}
		

		
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmployeeSeatModel updateSeat(EmployeeSeatModel emp) throws EmployeeNotFoundException,SeatNotAvailableException {
		Optional<EmployeeSeatModel> empById=repoEmp.findById(emp.getEmpId());
		
		if(empById.isPresent()) {
			EmployeeSeatModel newValueToUpdate=empById.get();
			
		
				newValueToUpdate.setBuildingNo(emp.getBuildingNo());
				newValueToUpdate.setEmpName(emp.getEmpName());
				newValueToUpdate.setFloorNo(emp.getFloorNo());
				newValueToUpdate.setProjectCode(emp.getProjectCode());
				newValueToUpdate.setSeatNo(emp.getSeatNo());
				repoEmp.save(newValueToUpdate);
				return newValueToUpdate;
		
		}
		
		else {
			throw new EmployeeNotFoundException("Please enter proper employee id");
		}
		
		// TODO Auto-generated method stub
		
	}
	
	public boolean findAllDetails(EmployeeSeatModel emp) {
		boolean flag=false;

		List<EmployeeSeatModel> empList=repoEmp.findAll();
		for(EmployeeSeatModel employee:empList) {
			if((employee.getBuildingNo()!=null)&&employee.getBuildingNo().equals(emp.getBuildingNo())) {
				if((employee.getFloorNo()!=null)&&employee.getFloorNo().equals(emp.getFloorNo())) {
					if((employee.getSeatNo()!=null)&&employee.getSeatNo().equals(emp.getSeatNo())) {
						flag=true;
					}
					
				}
				
			}
		}
		return flag;
	}
	
	public EmployeeSeatModel findEmployee(String id) throws EmployeeNotFoundException{
		if(repoEmp.findById(id).isEmpty()){
			throw new EmployeeNotFoundException("Employee not found");

			
		}
		else {
			return repoEmp.findById(id).get();

		}
		
	}

	@Override
	public List<EmployeeSeatModel> findAllEmployeeDetails() {
		// TODO Auto-generated method stub
		return repoEmp.findAll();
	}
	@Override
	public List<EmployeeSeatModel> findEmployeeBasedOnBuilding(String buildingNo){
		System.out.println(buildingNo);
		
		return repoEmp.findByBuildingNo(buildingNo);
		
	}

	

}
