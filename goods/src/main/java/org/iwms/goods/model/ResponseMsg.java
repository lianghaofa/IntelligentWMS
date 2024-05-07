package org.iwms.goods.model;

import lombok.Data;

import java.util.List;

/**
 * @author leung
 */

@Data
public class ResponseMsg<T> {

    private int count;
    private String next;
    private String previous;

    private List<String> goodsUnitList;
    private List<String> goodsClassList;
    private List<String> goodsColorList;
    private List<String> goodsShapeList;
    private List<String> goodsSpecsList;
    private List<String> goodsOriginList;
    private List<String> supplierList;

    private List<T> results;

    public List<String> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<String> supplierList) {
        this.supplierList = supplierList;
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

    public List<String> getGoodsUnitList() {
        return goodsUnitList;
    }

    public void setGoodsUnitList(List<String> goodsUnitList) {
        this.goodsUnitList = goodsUnitList;
    }

    public List<String> getGoodsClassList() {
        return goodsClassList;
    }

    public void setGoodsClassList(List<String> goodsClassList) {
        this.goodsClassList = goodsClassList;
    }

    public List<String> getGoodsColorList() {
        return goodsColorList;
    }

    public void setGoodsColorList(List<String> goodsColorList) {
        this.goodsColorList = goodsColorList;
    }

    public List<String> getGoodsShapeList() {
        return goodsShapeList;
    }

    public void setGoodsShapeList(List<String> goodsShapeList) {
        this.goodsShapeList = goodsShapeList;
    }

    public List<String> getGoodsSpecsList() {
        return goodsSpecsList;
    }

    public void setGoodsSpecsList(List<String> goodsSpecsList) {
        this.goodsSpecsList = goodsSpecsList;
    }

    public List<String> getGoodsOriginList() {
        return goodsOriginList;
    }

    public void setGoodsOriginList(List<String> goodsOriginList) {
        this.goodsOriginList = goodsOriginList;
    }
}
