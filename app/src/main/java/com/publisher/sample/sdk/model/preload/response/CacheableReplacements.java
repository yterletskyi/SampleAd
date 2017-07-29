
package com.publisher.sample.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CacheableReplacements {

    @SerializedName("POWERED_BY_VUNGLE")
    @Expose
    public POWEREDBYVUNGLE pOWEREDBYVUNGLE;
    @SerializedName("APP_RATING")
    @Expose
    public APPRATING aPPRATING;
    @SerializedName("CAROUSEL_IMAGE_1")
    @Expose
    public CAROUSELIMAGE1 cAROUSELIMAGE1;
    @SerializedName("CAROUSEL_IMAGE_2")
    @Expose
    public CAROUSELIMAGE2 cAROUSELIMAGE2;
    @SerializedName("CAROUSEL_IMAGE_5")
    @Expose
    public CAROUSELIMAGE5 cAROUSELIMAGE5;
    @SerializedName("MAIN_VIDEO")
    @Expose
    public MAINVIDEO mAINVIDEO;
    @SerializedName("APP_ICON")
    @Expose
    public APPICON aPPICON;
    @SerializedName("CAROUSEL_IMAGE_3")
    @Expose
    public CAROUSELIMAGE3 cAROUSELIMAGE3;
    @SerializedName("CAROUSEL_IMAGE_4")
    @Expose
    public CAROUSELIMAGE4 cAROUSELIMAGE4;

    public CacheableReplacements withPOWEREDBYVUNGLE(POWEREDBYVUNGLE pOWEREDBYVUNGLE) {
        this.pOWEREDBYVUNGLE = pOWEREDBYVUNGLE;
        return this;
    }

    public CacheableReplacements withAPPRATING(APPRATING aPPRATING) {
        this.aPPRATING = aPPRATING;
        return this;
    }

    public CacheableReplacements withCAROUSELIMAGE1(CAROUSELIMAGE1 cAROUSELIMAGE1) {
        this.cAROUSELIMAGE1 = cAROUSELIMAGE1;
        return this;
    }

    public CacheableReplacements withCAROUSELIMAGE2(CAROUSELIMAGE2 cAROUSELIMAGE2) {
        this.cAROUSELIMAGE2 = cAROUSELIMAGE2;
        return this;
    }

    public CacheableReplacements withCAROUSELIMAGE5(CAROUSELIMAGE5 cAROUSELIMAGE5) {
        this.cAROUSELIMAGE5 = cAROUSELIMAGE5;
        return this;
    }

    public CacheableReplacements withMAINVIDEO(MAINVIDEO mAINVIDEO) {
        this.mAINVIDEO = mAINVIDEO;
        return this;
    }

    public CacheableReplacements withAPPICON(APPICON aPPICON) {
        this.aPPICON = aPPICON;
        return this;
    }

    public CacheableReplacements withCAROUSELIMAGE3(CAROUSELIMAGE3 cAROUSELIMAGE3) {
        this.cAROUSELIMAGE3 = cAROUSELIMAGE3;
        return this;
    }

    public CacheableReplacements withCAROUSELIMAGE4(CAROUSELIMAGE4 cAROUSELIMAGE4) {
        this.cAROUSELIMAGE4 = cAROUSELIMAGE4;
        return this;
    }

}
