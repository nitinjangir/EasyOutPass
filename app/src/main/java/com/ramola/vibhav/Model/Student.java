package com.ramola.vibhav.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sahil on 30/3/17.
 */

public class Student implements Parcelable {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

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
    private int whenOut;

    @SerializedName("roomNo")
    private String roomNo;

    @SerializedName("location")
    private String location;

    @SerializedName("whenIn")
    private int whenIn;

    public Student(boolean success, String message, int id, String name, String rollNo, String phoneNumber, String rfId, String picUrl, boolean isOut, int whenOut, String roomNo, String location, int whenIn) {
        this.success = success;
        this.message = message;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public int getWhenOut() {
        return whenOut;
    }

    public void setWhenOut(int whenOut) {
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

    public int getWhenIn() {
        return whenIn;
    }

    public void setWhenIn(int whenIn) {
        this.whenIn = whenIn;
    }

    public static Creator<Student> getCREATOR() {
        return CREATOR;
    }

    protected Student(Parcel in) {
        success = in.readByte() != 0;
        message = in.readString();
        id = in.readInt();
        name = in.readString();
        rollNo = in.readString();
        phoneNumber = in.readString();
        rfId = in.readString();
        picUrl = in.readString();
        isOut = in.readByte() != 0;
        whenOut = in.readInt();
        roomNo = in.readString();
        location = in.readString();
        whenIn = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(message);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(rollNo);
        dest.writeString(phoneNumber);
        dest.writeString(rfId);
        dest.writeString(picUrl);
        dest.writeByte((byte) (isOut ? 1 : 0));
        dest.writeInt(whenOut);
        dest.writeString(roomNo);
        dest.writeString(location);
        dest.writeInt(whenIn);
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
}
