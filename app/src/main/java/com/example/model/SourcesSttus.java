
package com.example.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourcesSttus {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<SourceDetails> sources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SourceDetails> getSources() {
        return sources;
    }

    public void setSources(List<SourceDetails> sources) {
        this.sources = sources;
    }

}
