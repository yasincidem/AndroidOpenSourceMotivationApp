package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity;

/**
 * Created by yasin_000 on 9.10.2017.
 */

public class QuotesData {
    private String quote, person;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public QuotesData(String quote, String person) {

        this.quote = quote;
        this.person = person;
    }

    public QuotesData() {
    }

}
