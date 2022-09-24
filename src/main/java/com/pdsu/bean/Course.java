package com.pdsu.bean;

import java.util.Date;

public class Course {

    private int cid;// 课程主键
    private String courseName;//课程名称
    private String descs;//课程简介
    private String courseType;//课程的类型  1 JAVA  2 sql  3 前端
    private String courseImage;//课程图片
    private String courseVideo;//课程视频
    private double coursePrice;//课程价格
    private int status;//课程状态 0 未上架  1 上架   2 下架
    private Date createTime;//创建时间


    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", courseName='" + courseName + '\'' +
                ", descs='" + descs + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseImage='" + courseImage + '\'' +
                ", courseVideo='" + courseVideo + '\'' +
                ", coursePrice=" + coursePrice +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseVideo() {
        return courseVideo;
    }

    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo;
    }

    public double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
