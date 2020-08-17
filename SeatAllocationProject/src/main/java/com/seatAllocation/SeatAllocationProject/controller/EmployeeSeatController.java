package com.seatAllocation.SeatAllocationProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seatAllocation.SeatAllocationProject.Exception.CommonException;
import com.seatAllocation.SeatAllocationProject.Exception.EmployeeNotFoundException;
import com.seatAllocation.SeatAllocationProject.Exception.NoDuplicateAllocationException;
import com.seatAllocation.SeatAllocationProject.Exception.SeatNotAvailableException;
import com.seatAllocation.SeatAllocationProject.Model.EmployeeSeatModel;
import com.seatAllocation.SeatAllocationProject.Service.SeatAllocationServiceImpl;
@RestController
public class EmployeeSeatController {
	
	
	
	@Autowired
	SeatAllocationServiceImpl serviceEmp;
	


@PostMapping("/allocate-seat")
public ResponseEntity<?> alloacteSeats(@RequestBody EmployeeSeatModel empSeat) throws Exception,CommonException,SeatNotAvailableException,NoDuplicateAllocationException{
	
	try {
		EmployeeSeatModel emp=serviceEmp.allocateSeat(empSeat);
		return new ResponseEntity<EmployeeSeatModel>(emp,HttpStatus.CREATED);

	}
	catch(SeatNotAvailableException  e) {
		return new ResponseEntity<String>("Seat Not Available.It is already allocated.Please do allocate any other available seats",HttpStatus.NOT_FOUND);
	}
	catch(NoDuplicateAllocationException  e) {
		return new ResponseEntity<String>("One Employee cannot have more than one seat",HttpStatus.CONFLICT);
	}
	catch(CommonException e) {
		return new ResponseEntity<String>("Either Building no or floor no is invalid.Please select building no less than or equal to 8 and Floor no less than or equal to 10",HttpStatus.NOT_FOUND);


	}
	catch(Exception e) {
		return new ResponseEntity<String>("Please enter all mandatory fields",HttpStatus.BAD_REQUEST);
	}
	
	

	
	
}


@PutMapping("/allocate-seat")
public ResponseEntity<?> updateSeatDetails(@RequestBody EmployeeSeatModel empSeat) throws EmployeeNotFoundException,SeatNotAvailableException{
	
	try {
		EmployeeSeatModel emp=serviceEmp.updateSeat(empSeat);
		return new ResponseEntity<EmployeeSeatModel>(emp,HttpStatus.OK);

	}
	catch(EmployeeNotFoundException  e) {
		return new ResponseEntity<String>("Enter proper employee id",HttpStatus.NOT_FOUND);
	}
	catch(SeatNotAvailableException  e) {
		return new ResponseEntity<String>("Seat Not Available.It is already allocated.Please do allocate any other available seats",HttpStatus.NOT_FOUND);
	}
	
	
	
}

@GetMapping("/employee/{id}")
public ResponseEntity<?> fetchEmpSeatDetails(@PathVariable("id") String id) throws EmployeeNotFoundException{

	try {
		EmployeeSeatModel emp=serviceEmp.findEmployee(id);
		return new ResponseEntity<EmployeeSeatModel>(emp,HttpStatus.OK);

	}
	catch(EmployeeNotFoundException  e) {
		return new ResponseEntity<String>("Employee Not Found",HttpStatus.NOT_FOUND);
	}
	
	


}
@GetMapping("/find-all-employee-details")
	public ResponseEntity<?> findAllEmployeeDetails(){

	
		List<EmployeeSeatModel>  emp=serviceEmp.findAllEmployeeDetails();
			return new ResponseEntity<List<EmployeeSeatModel> >(emp,HttpStatus.OK);

		
	
}

@GetMapping("/filter-basedOn-buildingNo")
public ResponseEntity<?> findFilteredEmployee(@RequestParam("buildingNo") String buildingNo){
	List<EmployeeSeatModel>  emp=serviceEmp.findEmployeeBasedOnBuilding(buildingNo);
	return new ResponseEntity<List<EmployeeSeatModel> >(emp,HttpStatus.OK);


}



}
