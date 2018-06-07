package com.ctpsp.common.page;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页标签
 *
 * @author tangs quanxiwei
 */
public class PageTag extends TagSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1269692046496757938L;
    private String page;//当前页
    private String total;//总页数
    private String size;//每页显示条数
    private String styleClass;//分页区域div的样式
    private String totalCount;//总记录数
    private String theme;//分页主题，支持text和number两种
    private String method;//切换页码时的http方法类型：GET/POST，有不同的处理方式。
    private String action;//分页时调用的action。这个值为空，默认执行页面加载时的action。

    public void setAction(String action) {
        this.action = action;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    @SuppressWarnings("static-access")
    public int doStartTag() throws JspException {
        Pages pages = populatePages();
        pages.render(pageContext);
        return Tag.SKIP_BODY;
    }

    @Override
    @SuppressWarnings("static-access")
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    protected Pages populatePages() {

        Pages pages = new Pages();
        pages.setPage(page);
        pages.setMethod(method);
        pages.setTotal(total);
        pages.setStyleClass(styleClass);
        pages.setTotalCount(totalCount);
        pages.setTheme(theme);
        pages.setSize(size);
        pages.setAction(action);
        return pages;
    }
}
