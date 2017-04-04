package com.ramola.vibhav.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sahil on 30/3/17.
 */

public class UpdateStudentResponse implements Parcelable {

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private boolean success;

    @SerializedName("rfId")
    private String rfId;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    public UpdateStudentResponse(String message, boolean success, String rfId, String phoneNumber) {
        this.message = message;
        this.success = success;
        this.rfId = rfId;
        this.phoneNumber = phoneNumber;
    }

    protected UpdateStudentResponse(Parcel in) {
        message = in.readString();
        success = in.readByte() != 0;
        rfId = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<UpdateStudentResponse> CREATOR = new Creator<UpdateStudentResponse>() {
        @Override
        public UpdateStudentResponse createFromParcel(Parcel in) {
            return new UpdateStudentResponse(in);
        }

        @Override
        public UpdateStudentResponse[] newArray(int size) {
            return new UpdateStudentResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(rfId);
        dest.writeString(phoneNumber);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static Creator<UpdateStudentResponse> getCREATOR() {
        return CREATOR;
    }
}
