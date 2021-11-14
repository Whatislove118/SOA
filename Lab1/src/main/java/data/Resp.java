package data;

import java.util.List;

public class Resp {
    private int total;
    private List content;

    public Resp(int total, List content) {
        this.total = total;
        this.content = content;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }
}
