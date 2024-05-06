package org.iwms.asn.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author leung
 */
public class ASNListResponseMsg<T> {

    private int count;
    private String next;
    private String previous;
    @JsonProperty("supplier_list")
    private List<String> supplierList;
    private List<T> results;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<String> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<String> supplierList) {
        this.supplierList = supplierList;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
