package org.project4.back_end.output;

import org.project4.back_end.dto.OrderDetailDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsOutPut {
    private int page;
    private int totalPage;
    private List<OrderDetailDTO> listResult = new ArrayList<>();

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

    public List<OrderDetailDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<OrderDetailDTO> listResult) {
        this.listResult = listResult;
    }
}
