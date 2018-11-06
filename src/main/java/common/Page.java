package common;

import java.util.List;

public class Page<T> {
    public Page() {
    }

    public Page(long total, List<T> list) {
        super();
        this.total = total;
        this.list = list;
    }

    private long total;
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
