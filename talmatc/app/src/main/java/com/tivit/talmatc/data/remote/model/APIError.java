package com.tivit.talmatc.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class APIError {

    @SerializedName("status")
    private String status;

    @SerializedName("messages")
    private List<String> messages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

}
