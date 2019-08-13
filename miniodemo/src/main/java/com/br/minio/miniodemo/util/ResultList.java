package com.br.minio.miniodemo.util;

import java.util.ArrayList;
import java.util.List;


public class ResultList<T> {
    public long total;
    public List<T> result;

    /**
     *
     * @param total
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     *
     * @return total
     */
    public long getTotal() {
        return total;
    }

    /**
     *
     * @param result
     */
    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     *
     * @return result
     */
    public List<T> getResult() {
        return result;
    }


    /**
     *
     * @param total
     * @param count
     * @return tmp
     */
    public static ResultList getResultList(Integer total,Integer count){
        ResultList result = new ResultList();
        List<Integer> list=new ArrayList<Integer>();
        list.add(count);
        result.setTotal(total);
        result.setResult(list);
        return result;
    }
}
