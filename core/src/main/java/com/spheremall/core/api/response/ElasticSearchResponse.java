package com.spheremall.core.api.response;

import com.google.gson.annotations.SerializedName;
import com.spheremall.core.entities.Entity;

import java.util.List;

public class ElasticSearchResponse extends Entity {

    @SerializedName("took")
    public int took;
    @SerializedName("timed_out")
    public boolean timedOut;

    @SerializedName("hits")
    public Hit hits;

    public static class Hit {
        public int total;
        public List<Hits> hits;
    }

    public static class Hits {
        @SerializedName("_index")
        public String index;

        @SerializedName("_type")
        public String type;

        @SerializedName("_id")
        public String id;

        @SerializedName("_score")
        public String score;

        @SerializedName("_source")
        public HitSource source;
    }

    public static class HitSource {
        public String scope;
    }
}