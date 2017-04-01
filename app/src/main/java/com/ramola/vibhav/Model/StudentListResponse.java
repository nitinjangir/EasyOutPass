package com.ramola.vibhav.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sahil on 30/3/17.
 */

public class StudentListResponse  implements Parcelable{

    @SerializedName("success")
    private boolean success;

    @SerializedName("list")
    private ArrayList<Student> getAllStudent;

    public StudentListResponse(boolean success, ArrayList<Student> getAllStudent) {
        this.success = success;
        this.getAllStudent = getAllStudent;
    }

    protected StudentListResponse(Parcel in) {
        success = in.readByte() != 0;
        getAllStudent = in.createTypedArrayList(Student.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeTypedList(getAllStudent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentListResponse> CREATOR = new Creator<StudentListResponse>() {
        @Override
        public StudentListResponse createFromParcel(Parcel in) {
            return new StudentListResponse(in);
        }

        @Override
        public StudentListResponse[] newArray(int size) {
            return new StudentListResponse[size];
        }
    };

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Student> getGetAllStudent() {
        return getAllStudent;
    }

    public void setGetAllStudent(ArrayList<Student> getAllStudent) {
        this.getAllStudent = getAllStudent;
    }
}
