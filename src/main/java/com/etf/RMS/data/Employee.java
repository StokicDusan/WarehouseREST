package com.etf.RMS.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class Employee implements Serializable {

    /*
    Atributi klase su kolone
    u tabeli u bazi podataka
     */
    private int employee_id;
    private String last_name;
    private String first_name;
    private String birthday;

    public Employee() {
    }

    /*
    Konstruktori sa i bez id-a iz baze
     */
    public Employee(int employee_id, String last_name, String first_name, String birthday) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
    }

    public Employee(String last_name, String first_name, String birthday) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
    }

    /*
    Geteri i seteri svih atributa klase
     */
    public int getEmployee_id() {
        return employee_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee{employee_id=").append(employee_id);
        sb.append(", last_name=").append(last_name);
        sb.append(", first_name=").append(first_name);
        sb.append(", birthday=").append(birthday);
        sb.append('}');
        return sb.toString();
    }

}
