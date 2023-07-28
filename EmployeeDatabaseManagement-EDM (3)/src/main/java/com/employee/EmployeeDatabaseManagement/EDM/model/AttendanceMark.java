package com.employee.EmployeeDatabaseManagement.EDM.model;

import lombok.Data;

public enum AttendanceMark {
    P("Present"),A("Absent"),PL("PaidLeave"),UP("UnpaidLeave");
    public String type;
    AttendanceMark(String type) {
        this.type = type;
    }
    public String getType(){
        return type;
    }
}
