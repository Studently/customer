package cn.edu.stu.customer.domain;

import java.util.List;

public class PageBean<T> {

    private int pc;//当前页码 page current
    private int tp;//总页数 total page
    private int tr;//总记录 total record
    private int ps;//每页的条数 page size
    private List<T> beanList;//当前页数据
    private String url;//存放访问连接

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    /**
     * 总页数是根据总记录数和每页记录数计算出来的，所以不因该提供设置
     * 如果总记录数除以每页记录数是整除，直接返回相除结果，否则加1
     * @return
     */
    public int getTp() {
        tp=tr/ps;
        return tr%ps==0?tp:tp+1;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
