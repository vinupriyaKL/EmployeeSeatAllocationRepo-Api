package com.seatAllocation.SeatAllocationProject.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmployeeSeatModel {
	@Id
    @Column(length = 64)

	private String empId;
	private String empName;
	private String buildingNo;
	private String floorNo;
	private String seatNo;
	@Override
	public String toString() {
		return "EmployeeSeatModel [empId=" + empId + ", empName=" + empName + ", projectCode=" + projectCode
				+ ", buildingNo=" + buildingNo + ", floorNo=" + floorNo + ", seatNo=" + seatNo + "]";
	}
	private String projectCode;
	public EmployeeSeatModel(String empId, String empName, String projectCode, String buildingNo, String floorNo,
			String seatNo) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.projectCode = projectCode;
		this.buildingNo = buildingNo;
		this.floorNo = floorNo;
		this.seatNo = seatNo;
	}
	public EmployeeSeatModel() {}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	
	

}
