package com.example.database.demo.entity;

import com.example.database.frame.annotation.TableField;
import com.example.database.frame.annotation.TableId;
import com.example.database.frame.annotation.TableName;

import java.util.Date;

/**
 * @author TANGZHEN
 * @createDate 2023-11-05
 */
@TableName("user")
public class User {

    @TableId("id")
    private Long id;
    @TableField("name")
    private String name;
    @TableField("address")
    private String address;
    @TableField("num")
    private Integer num;
    @TableField("del_flag")
    private Integer delFlag;
    @TableField("create_date")
    private Date createDate;
    @TableField("type")
    private String type;
    @TableField("teacher_id")
    private Long teacherId;
    @TableField("teacher_name")
    private String teacherName;
    @TableField("school_id")
    private Long schoolId;
    @TableField("school_name")
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
