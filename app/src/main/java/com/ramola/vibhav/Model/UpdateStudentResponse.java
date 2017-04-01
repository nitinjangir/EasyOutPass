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

    public UpdateStudentResponse(String message, boolean success, String rfId) {
        this.message = message;
        this.success = success;
        this.rfId = rfId;
    }

    protected UpdateStudentResponse(Parcel in) {
        message = in.readString();
        success = in.readByte() != 0;
        rfId = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(rfId);
    }
}
