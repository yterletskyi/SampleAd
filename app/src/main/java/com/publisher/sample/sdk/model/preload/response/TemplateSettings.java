
package com.publisher.sample.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TemplateSettings {

    @SerializedName("normal_replacements")
    @Expose
    public NormalReplacements normalReplacements;
    @SerializedName("cacheable_replacements")
    @Expose
    public CacheableReplacements cacheableReplacements;

    public TemplateSettings withNormalReplacements(NormalReplacements normalReplacements) {
        this.normalReplacements = normalReplacements;
        return this;
    }

    public TemplateSettings withCacheableReplacements(CacheableReplacements cacheableReplacements) {
        this.cacheableReplacements = cacheableReplacements;
        return this;
    }

}
