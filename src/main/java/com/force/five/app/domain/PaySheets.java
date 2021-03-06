package com.force.five.app.domain;

import com.force.five.app.service.util.ForceConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Objects;

/**
 * A PaySheets.
 */
@Entity
@Table(name = "pay_sheets")
public class PaySheets implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @Column(name = "regular_days")
    private Integer regularDays;

    @Column(name = "days_worked")
    private Integer daysWorked;

    @Column(name = "weekly_off")
    private Integer weeklyOff;

    @Column(name = "comp_off")
    private Integer compOff;

    @Column(name = "holidays")
    private Integer holidays;

    @Column(name = "overtime")
    private Integer overtime;

    @ManyToOne
    @JoinColumn(name = "assignments_id")
    private Assignments assignments;

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
        return (daysWorked != null ? daysWorked : 0);
    }

    public void setDaysWorked(Integer daysWorked) {
        this.daysWorked = daysWorked;
    }

    public Integer getWeeklyOff() {
        return (weeklyOff != null ? weeklyOff : 0);
    }

    public void setWeeklyOff(Integer weeklyOff) {
        this.weeklyOff = weeklyOff;
    }

    public Integer getCompOff() {
        return (compOff != null ? compOff : 0);
    }

    public void setCompOff(Integer compOff) {
        this.compOff = compOff;
    }

    public Integer getHolidays() {
        return (holidays != null ? holidays : 0);
    }

    public void setHolidays(Integer holidays) {
        this.holidays = holidays;
    }

    public Integer getOvertime() {
        return (overtime != null ? overtime : 0);
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    public Assignments getAssignments() {
        return assignments;
    }

    public void setAssignments(Assignments assignments) {
        this.assignments = assignments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaySheets paySheets = (PaySheets) o;
        if (paySheets.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, paySheets.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaySheets{" +
            "id=" + id +
            ", month='" + month + "'" +
            ", year='" + year + "'" +
            ", regularDays='" + regularDays + "'" +
            ", daysWorked='" + daysWorked + "'" +
            ", weeklyOff='" + weeklyOff + "'" +
            ", compOff='" + compOff + "'" +
            ", holidays='" + holidays + "'" +
            ", overtime='" + overtime + "'" +
            '}';
    }

    //Calculated columns
    public BigDecimal getTotalWages() {
        BigDecimal basic = this.getAssignments().getEmployee().getBasic();
        BigDecimal allowance = this.getAssignments().getEmployee().getAllowances();
        return basic.add(allowance);

    }

    public BigDecimal getEarnedBasic() {
        BigDecimal basic = this.getAssignments().getEmployee().getBasic();
        return basic.divide(ForceConstants.TOTAL_DAYS, 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(this.regularDays)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getOTWages() {
        return getTotalWages().divide(ForceConstants.TOTAL_DAYS, 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(this.overtime)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getEarnedAllowances() {
        return getAssignments().getEmployee().getAllowances().divide(ForceConstants.TOTAL_DAYS, 2, RoundingMode.HALF_EVEN).multiply(new BigDecimal(this.regularDays)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getGrossWages() {
        return getEarnedBasic().add(getOTWages()).add(getEarnedAllowances());
    }

    public BigDecimal getPF() {
        return getEarnedBasic().multiply(ForceConstants.PF_CAL).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getESIC() {
        return getEarnedBasic().multiply(ForceConstants.ESIC).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getCost() {
        return this.getAssignments().getCost();
    }

    public BigDecimal getCostPerDay() {
        BigDecimal cost = getCost();
        Calendar c = Calendar.getInstance();

        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        BigDecimal monthMaxDaysBigDecimal = new BigDecimal(monthMaxDays);

        //costperday = cost/No of days in a month
        BigDecimal costPerDay = cost.divide(monthMaxDaysBigDecimal, 2, RoundingMode.HALF_EVEN);
        return costPerDay;
    }

    public BigDecimal getTotal() {

        BigDecimal total = new BigDecimal(getDaysWorked() + getWeeklyOff() + getCompOff() + getOvertime() + getHolidays());
        return total;
    }

    //GrandTotal = PerDayCost * Total
    public BigDecimal getGrandTotal() {
        return getCostPerDay().multiply(getTotal()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}
