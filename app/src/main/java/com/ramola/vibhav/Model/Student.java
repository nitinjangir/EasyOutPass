package com.ramola.vibhav.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sahil on 30/3/17.
 */

public class Student implements  Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("rollNo")
    private String rollNo;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("rfId")
    private String rfId;

    @SerializedName("picUrl")
    private String picUrl;

    @SerializedName("isOut")
    private boolean isOut;

    @SerializedName("whenOut")
    private long whenOut;

    @SerializedName("roomNo")
    private String roomNo;

    @SerializedName("location")
    private String location;

    @SerializedName("whenIn")
    private long whenIn;


    public Student(int id, String name, String rollNo, String phoneNumber, String rfId, String picUrl, boolean isOut, long whenOut, String roomNo, String location, long whenIn) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.phoneNumber = phoneNumber;
        this.rfId = rfId;
        this.picUrl = picUrl;
        this.isOut = isOut;
        this.whenOut = whenOut;
        this.roomNo = roomNo;
        this.location = location;
        this.whenIn = whenIn;
    }

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        rollNo = in.readString();
        phoneNumber = in.readString();
        rfId = in.readString();
        picUrl = in.readString();
        isOut = in.readByte() != 0;
        whenOut = in.readLong();
        roomNo = in.readString();
        location = in.readString();
        whenIn = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(rollNo);
        dest.writeString(phoneNumber);
        dest.writeString(rfId);
        dest.writeString(picUrl);
        dest.writeByte((byte) (isOut ? 1 : 0));
        dest.writeLong(whenOut);
        dest.writeString(roomNo);
        dest.writeString(location);
        dest.writeLong(whenIn);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public long getWhenOut() {
        return whenOut;
    }

    public void setWhenOut(long whenOut) {
        this.whenOut = whenOut;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getWhenIn() {
        return whenIn;
    }

    public void setWhenIn(long whenIn) {
        this.whenIn = whenIn;
    }
}
