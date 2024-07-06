package org.project4.back_end.output;

import org.project4.back_end.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderOutPut {
    private int page;
    private int totalPage;
    private List<OrderDTO> listResult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<OrderDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<OrderDTO> listResult) {
        this.listResult = listResult;
    }
}
