
package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PageInfo {

    @SerializedName("resultsPerPage")
    private Long mResultsPerPage;
    @SerializedName("totalResults")
    private Long mTotalResults;

    public Long getResultsPerPage() {
        return mResultsPerPage;
    }

    public void setResultsPerPage(Long resultsPerPage) {
        mResultsPerPage = resultsPerPage;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
