package com.example.database.demo.entity;

import java.util.Date;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
public class User {

    private Long id;
    private String name;
    private String address;
    private Integer num;
    private Integer delFlag;
    private Date createDate;
    private String type;
    private Long teacherId;
    private String teacherName;
    private Long schoolId;
    private String schoolName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + ", num=" + num + ", delFlag=" + delFlag + ", createDate="
               + createDate + ", type='" + type + '\'' + ", teacherId=" + teacherId + ", teacherName='" + teacherName + '\'' + ", schoolId=" + schoolId
               + ", schoolName='" + schoolName + '\'' + '}';
    }
}
