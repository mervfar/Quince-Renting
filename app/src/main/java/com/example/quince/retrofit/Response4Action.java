package com.example.quince.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Response4Action {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("result")
    @Expose
    private List<String> result = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
