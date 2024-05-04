package org.iwms.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leung
 */

@Data
public class ResponseMsg<T> {

    private int count;
    private String next;
    private String previous;
    private List<T> results;

    @JsonProperty("bin_size_list")
    private List<String> binSizeList;
    @JsonProperty("bin_property_list")
    private List<String> binPropertyList;

    public List<String> getBinSizeList() {
        return binSizeList;
    }

    public void setBinSizeList(List<String> binSizeList) {
        this.binSizeList = binSizeList;
    }

    public List<String> getBinPropertyList() {
        return binPropertyList;
    }

    public void setBinPropertyList(List<String> binPropertyList) {
        this.binPropertyList = binPropertyList;
    }

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

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
