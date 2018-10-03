package com.spheremall.core.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ElasticSearchError {

    public Error error;
    public String status;

    public static class Error {
        @SerializedName("root_cause")
        public List<RootCause> causes;
        @SerializedName("caused_by")
        public CausedBy causedBy;
    }

    public static class RootCause {
        public String type;
        public String reason;
    }

    public static class CausedBy {
        public String type;
        public String reason;
    }
}
