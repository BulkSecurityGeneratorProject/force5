package com.force.five.app.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the PaySheets entity.
 */
public class PaySheetsDTO implements Serializable {

    private Long id;

    private String month;

    private String year;

    private Integer regularDays = 0;

    private Integer daysWorked = 0;

    private Integer weeklyOff = 0;

    private Integer compOff = 0;

    private Integer holidays = 0;

    private Integer overtime = 0;

    private String clientName;

    private String employeeName;

    private Long assignmentsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public Integer getRegularDays() {
        return regularDays;
    }

    public void setRegularDays(Integer regularDays) {
        this.regularDays = regularDays;
    }
    public Integer getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(Integer daysWorked) {
        this.daysWorked = daysWorked;
    }
    public Integer getWeeklyOff() {
        return weeklyOff;
    }

    public void setWeeklyOff(Integer weeklyOff) {
        this.weeklyOff = weeklyOff;
    }
    public Integer getCompOff() {
        return compOff;
    }

    public void setCompOff(Integer compOff) {
        this.compOff = compOff;
    }
    public Integer getHolidays() {
        return holidays;
    }

    public void setHolidays(Integer holidays) {
        this.holidays = holidays;
    }
    public Integer getOvertime() {
        return overtime;
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getAssignmentsId() {
        return assignmentsId;
    }

    public void setAssignmentsId(Long assignmentsId) {
        this.assignmentsId = assignmentsId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaySheetsDTO paySheetsDTO = (PaySheetsDTO) o;

        if ( ! Objects.equals(id, paySheetsDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaySheetsDTO{" +
            "id=" + id +
            ", month='" + month + "'" +
            ", year='" + year + "'" +
            ", regularDays='" + regularDays + "'" +
            ", daysWorked='" + daysWorked + "'" +
            ", weeklyOff='" + weeklyOff + "'" +
            ", compOff='" + compOff + "'" +
            ", holidays='" + holidays + "'" +
            ", overtime='" + overtime + "'" +
            ", clientName='" + clientName + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", assignmentsId=" + assignmentsId +
            '}';
    }
}
