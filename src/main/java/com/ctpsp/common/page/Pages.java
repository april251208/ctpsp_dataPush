package com.ctpsp.common.page;


import com.ctpsp.common.cipher.MD5;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 分页逻辑Bean
 *
 * @author tangs quanxiwei
 */
public class Pages {

    Integer linkSize = 6;//number new 模式，显示6个连接
    private String page;
    private String total;
    private String totalCount;
    private String size;
    private String styleClass;
    private String theme;
    private String method;
    private String action;
    private boolean changePage = true;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
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

    /**
     * 构建分页html。
     *
     * @param page       当前页
     * @param size       每页显示条数
     * @param total      中记录数
     * @param action     跳转url
     * @param method     方法。GET，POST
     * @param sytleClass 生成html的css
     * @param params     参数
     */
    public StringBuilder render2(String page, String size, String total, String action, String method, HashMap<String, Object> params, boolean changePage, String sytleClass) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.action = action;
        this.method = method;
        this.styleClass = sytleClass;
        this.changePage = changePage;
        this.theme = "text";

        String url = null;
        if (StringUtils.isNotBlank(action)) {
            url = action;
        }


        StringBuilder pagination = null;
        try {
            // 文本样式
            if (theme == null || "text".equals(theme)) {
                pagination = buildTextStylePagination(params, url);
            } else if ("number".equals(theme)) {
                pagination = buildNumberStylePagination(params, url);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return pagination;
    }

    public void render(PageContext pageContext) {
        Writer writer = pageContext.getOut();
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();

        String url;
        if (StringUtils.isNotBlank(action)) {
            url = action;
        }

        url = (String) req.getAttribute("javax.servlet.forward.request_uri");

        @SuppressWarnings("unchecked")
        Map<String, Object> pageParms = (Map<String, Object>) req
                .getAttribute(PageParamHandlerInterceptor.REQUEST_PAGE_PARAM);
        StringBuilder pagination = null;
        try {
            // 文本样式
            if (theme == null || "text".equals(theme)) {
                pagination = buildTextStylePagination(pageParms, url);
            } else if ("number".equals(theme)) {
//                                pagination = buildNumberStylePagination(pageParms, url);
                //采用了新的number样式，显示样式和前端bootstrap的一致。
                pagination = buildNumberNewStylePagination(pageParms, url);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            if (pagination != null) {
                writer.write(pagination.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected StringBuilder buildTextStylePagination(Map<String, Object> pageParms,
                                                     String url) throws UnsupportedEncodingException {
        StringBuilder pagination = new StringBuilder();
        String jsmethod = "";
        String extraUrl = "";
        // 当前页与总页数相等
        if ("post".equalsIgnoreCase(this.method)) {
            jsmethod = buildForm(pagination, url, pageParms);
            buildRefreshScript(pagination, false, jsmethod, url, null);
            buildJumpScript(pagination, false, jsmethod, url, null);
        } else {
            extraUrl = buildExtraUrl(pageParms);
            buildRefreshScript(pagination, true, null, url, extraUrl);
            buildJumpScript(pagination, true, null, url, extraUrl);
        }

        pagination.append("<div id='pagination'><span ");
        if (styleClass != null) {
            pagination.append(" class='" + styleClass + "'>");
        } else {
            pagination.append(">");
        }

        if ("post".equalsIgnoreCase(this.method)) {
            buildSelectPageSize(pagination);
            buildTextStylePaginationPost(pagination, jsmethod);
            buildJumpPageSelection(pagination, url, extraUrl);
        } else {
            buildSelectPageSize(pagination);
            buildTextStylePaginationGet(pagination, url, extraUrl);
            buildJumpPageSelection(pagination, url, extraUrl);
        }

        pagination.append("</span></div>");
        return pagination;
    }

    protected StringBuilder buildNumberStylePagination(
            Map<String, Object> pageParms, String url)
            throws UnsupportedEncodingException {
        StringBuilder pagination = new StringBuilder();
        String jsmethod = "";
        if ("post".equalsIgnoreCase(this.method)) {
            jsmethod = buildForm(pagination, url, pageParms);
        }
        pagination.append("<div id='pagination'><span ");
        if (styleClass != null) {
            pagination.append(" class='" + styleClass + "'>");
        } else {
            pagination.append(">");
        }

        if ("post".equalsIgnoreCase(this.method)) {
            buildNumberStylePaginationPost(pagination, jsmethod);
        } else {
            buildNumberStylePaginationGet(pagination, url,
                    buildExtraUrl(pageParms));
        }

        pagination.append("</span></div>");
        return pagination;
    }

    protected StringBuilder buildNumberNewStylePagination(
            Map<String, Object> pageParms, String url)
            throws UnsupportedEncodingException {
        StringBuilder pagination = new StringBuilder();

        //pagination-text是预留的样式，请需要修改分页样式的，设置pagination-text这个css。
        pagination.append("<div class=\"mem-block data-nums pagination-text\"><p>共 <span>" + total + "</span> 页");
        if (StringUtils.isNotBlank(totalCount)) {
            pagination.append("，<span>" + totalCount + "</span> 条数据</p>");
        }
        pagination.append("</div>");
        String jsmethod = "";
        if ("post".equalsIgnoreCase(this.method)) {
            jsmethod = buildForm(pagination, url, pageParms);
        }

        //add condition,如果只有一页，则不需显示分页
        if (StringUtils.isNumeric(total) && Integer.valueOf(total) > 1) {
            //设置styleClass.如果为空，设置默认值
            if (StringUtils.isBlank(styleClass)) {
                styleClass = "mem-block pagination";
            }
            pagination.append("<div class='" + styleClass + "'>");
            if ("post".equalsIgnoreCase(this.method)) {
                buildNumberNewStylePaginationPost(pagination, jsmethod);
            } else {
                buildNumberNewStylePaginationGet(pagination, url,
                        buildExtraUrl(pageParms));
            }

            pagination.append("</div>");
        }

        return pagination;
    }

    protected String buildForm(StringBuilder pagination2, String action,
                               Map<String, Object> pageParms) {
        String id = MD5.GetMD5Code(action);
        String jsMethod = "submit" + id;
        StringBuilder pagination = new StringBuilder();
        pagination.append("<form id='" + id + "' action='" + action + "' method='POST' style=\"padding:0px;margin:0px;\">");
        if (pageParms != null && pageParms.size() > 0) {
            Set<Entry<String, Object>> entrySet = pageParms.entrySet();
            for (Entry<String, Object> entry : entrySet) {
                entry.getKey();
                entry.getValue();
                pagination.append("<input type='hidden' name='" + entry.getKey() + "' value='" + entry.getValue() + "'>");
            }
        }
        if (pageParms != null && !pageParms.containsKey("page")) {
            pagination.append("<input type='hidden' name='page' value=''>");
        }
        if (pageParms != null && !pageParms.containsKey("size")) {
            pagination.append("<input type='hidden' name='size' value=''>");
        }

        pagination.append("</form>");
        // 构建一个javascript代码
        pagination.append("<script type='text/javascript'>");
        pagination.append("function " + jsMethod + "(_page,_size){");
        pagination.append("document.getElementById('" + id + "').page.value=_page;");
        pagination.append("document.getElementById('" + id + "').size.value=_size;");
        pagination.append("document.getElementById('" + id + "').submit();");
        pagination.append("}");
        pagination.append("</script>");
        pagination2.insert(0, pagination.toString());
        return jsMethod;
    }

    private void buildTextStylePaginationGet(StringBuilder pagination,
                                             String url, String perUrl) {
        Integer cpageInt = Integer.valueOf(page);
        // str.append("&nbsp;[共 " + total + " 页]");
        if (page.equals(total)) {
            // 如果total = 1，则无需分页，显示“[第1页] [共1页]”
            if ("1".equals(total)) {
            } else {
                // 到达最后一页,显示“[首页] [上一页] [末页]”
                pagination.append("&nbsp;&nbsp;<a href='" + url + "?" + "size=" + size + "&" + "page=1" + perUrl + "'>[首页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + (cpageInt - 1) + perUrl + "'>[上一页]</a>&nbsp;&nbsp;");
                pagination.append(" <a href='" + url + "?" + "size=" + size + "&" + "page=" + total + perUrl + "'>[末页]</a>&nbsp;&nbsp;");
            }
        } else {
            // 当前页与总页数不相同
            if ("1".equals(page)) {
                // 第一页，显示“[首页] [下一页] [末页]”
                pagination.append("&nbsp;&nbsp;<a href='" + url + "?" + "size=" + size + "&" + "page=1" + perUrl + "'>[首页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + (cpageInt + 1) + perUrl + "'>[下一页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + total + perUrl + "'>[末页]</a>&nbsp;&nbsp;");
            } else {
                // 不是第一页，显示“[首页] [上一页] [下一页] [末页]”
                pagination.append("&nbsp;&nbsp;<a href='" + url + "?" + "size=" + size + "&" + "page=1" + perUrl + "'>[首页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + (cpageInt - 1) + perUrl + "'>[上一页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + (cpageInt + 1) + perUrl + "'>[下一页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + total + perUrl + "'>[末页]</a>&nbsp;&nbsp;");
            }
        }
    }

    private void buildTextStylePaginationPost(StringBuilder pagination,
                                              String jsmethod) {
        Integer cpageInt = 1;
        if (page != null && !"".equals(page)) {
            cpageInt = Integer.valueOf(page);

        }
        if (page.equals(total)) {
            if ("1".equals(total)) {
            } else {
                pagination.append("&nbsp;&nbsp;<a href='javascript:" + jsmethod + "(1," + size + ")'>[首页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='javascript:" + jsmethod + "(" + (cpageInt - 1) + "," + size + ")'>[上一页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='javascript:" + jsmethod + "(" + total + "," + size + ")'>[末页]</a>&nbsp;&nbsp;");
            }
        } else {
            if ("1".equals(page)) {
                if (!"0".equals(total))//没有记录
                {
                    pagination.append("&nbsp;&nbsp;<a href='javascript:" + jsmethod + "(1," + size + ")'>[首页]</a>&nbsp;&nbsp;");
                    pagination.append("<a href='javascript:" + jsmethod + "(" + (cpageInt + 1) + "," + size + ")'>[下一页]</a>&nbsp;&nbsp;");
                    pagination.append("<a href='javascript:" + jsmethod + "(" + total + "," + size + ")'>[末页]</a>&nbsp;&nbsp;");
                }

            } else {
                pagination.append("&nbsp;&nbsp;<a href='javascript:" + jsmethod + "(1," + size + ")'>[首页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='javascript:" + jsmethod + "(" + (cpageInt - 1) + "," + size + ")'>[上一页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='javascript:" + jsmethod + "(" + (cpageInt + 1) + "," + size + ")'>[下一页]</a>&nbsp;&nbsp;");
                pagination.append("<a href='javascript:" + jsmethod + "(" + total + "," + size + ")'>[末页]</a>&nbsp;&nbsp;");
            }
        }
    }

    private void buildNumberStylePaginationGet(StringBuilder pagination,
                                               String url, String perUrl) {
        // 数字样式 [1 2 3 4 5 6 7 8 9
        // 10 > >>]
        Integer cpageInt = Integer.valueOf(page);
        Integer totalInt = Integer.valueOf(total);

        // 如果只有一页，则无需分页
        pagination.append("[&nbsp;");
        if (totalInt == 1) {
            pagination.append("<strong>1</strong>&nbsp;");
        } else {
            if (cpageInt > 1) {
                // 当前不是第一组，要显示“<< <”
                // <<：返回前一组第一页
                // <：返回前一页
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=1" + perUrl + "'>«</a>&nbsp;");
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + (cpageInt - 1) + perUrl);
                pagination.append("'>‹</a>&nbsp;");
            } else {
                pagination.append("«&nbsp;‹&nbsp;");
            }

            int v = (cpageInt - 4) > 0 ? (cpageInt - 4) : 1;
            int v1 = (cpageInt + 4) < totalInt ? (cpageInt + 4) : totalInt;
            if (v1 == totalInt) {
                v = totalInt - 10;
                v = v > 0 ? v : 1;
            } else if (v == 1 && v1 < totalInt) {
                v1 = totalInt > 10 ? 10 : totalInt;
            }
            // 10个为一组显示
            for (int i = v; i <= v1; i++) {
                if (cpageInt == i) { // 当前页要加粗显示
                    pagination.append("<strong>" + i + "</strong>&nbsp;");
                } else {
                    pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + i + perUrl + "'>" + i + "</a>&nbsp;");
                }
            }
            // 如果多于1组并且不是最后一组，显示“> >>”
            if (cpageInt < totalInt) {
                // >>：返回下一组最后一页
                // >：返回下一页
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + (cpageInt + 1) + perUrl + "'>›</a>&nbsp;");
                pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + totalInt + perUrl + "'>»</a>&nbsp;");
            } else {
                pagination.append("›&nbsp;»&nbsp;");
            }
        }
        pagination.append("]");

    }


    private void buildNumberNewStylePaginationGet(StringBuilder pagination,
                                                  String url, String perUrl) {
        Integer cpageInt = Integer.valueOf(page);
        Integer totalInt = Integer.valueOf(total);

        pagination.append("<ul>");
        if (totalInt == 1) {
            // 如果只有一页，则无需分页
//                        pagination.append("<li class=\"disabled\"><a href=\"#\">首页</a></li>");
        } else {
            if (cpageInt > 1) {
                // 当前不是第一组，要显示“<< <”
                // <<：返回前一组第一页
                // <：返回前一页
                pagination.append("<li><a href='" + url + "?" + "size=" + size + "&" + "page=1" + perUrl + "'>首页</a></li>");
                pagination.append("<li><a href='" + url + "?" + "size=" + size + "&" + "page=" + (cpageInt - 1) + perUrl + "'>上一页</a></li>");
            } else {
                pagination.append("<li class=\"disabled\"><a href=\"#\">首页</a></li>");
                pagination.append("<li class=\"disabled\"><a href=\"#\">上一页</a></li>");
            }

            int pageStart = 0;
            int pageEnd = 0;
            if (totalInt < linkSize) {
                pageStart = 1;
                pageEnd = totalInt;
            } else {
                pageStart = cpageInt - new Float(Math.floor(linkSize / 2)).intValue();
                pageStart = pageStart < 1 ? 1 : pageStart;
                if (pageStart + linkSize > totalInt) {
                    pageStart = totalInt - linkSize + 1;
                    pageStart = pageStart < 1 ? 1 : pageStart;
                    pageEnd = totalInt;
                } else {
                    pageEnd = pageStart + linkSize - 1;
                }
            }

            for (int i = pageStart; i <= pageEnd; i++) {
                if (cpageInt == i) {

                    pagination.append("<li class=\"active\"><a>" + i + "</a></li>");
                } else {
                    pagination.append("<li><a href='" + url + "?" + "size=" + size + "&" + "page=" + i + perUrl + "'>" + i + "</a></li>");
                }
            }


//                        int v = (cpageInt - 4) > 0 ? (cpageInt - 4) : 1;
//                        int v1 = (cpageInt + 4) < totalInt ? (cpageInt + 4) : totalInt;
//                        if (v1 == totalInt) {
//                                v = totalInt - 10;
//                                v = v > 0 ? v : 1;
//                        } else if (v == 1 && v1 < totalInt) {
//                                v1 = totalInt > 10 ? 10 : totalInt;
//                        }
//                        // 10个为一组显示
//                        for (int i = v; i <= v1; i++) {
//                                if (cpageInt == i) { // 当前页要加粗显示
//                                        pagination.append("<strong>" + i + "</strong>&nbsp;");
//                                } else {
//                                        pagination.append("<a href='" + url + "?" + "size=" + size + "&" + "page=" + i + perUrl + "'>" + i + "</a>&nbsp;");
//                                }
//                        }
            // 如果多于1组并且不是最后一组，显示“> >>”
            if (cpageInt < totalInt) {
                // >>：返回下一组最后一页
                // >：返回下一页
                pagination.append("<li><a href='" + url + "?" + "size=" + size + "&" + "page=" + (cpageInt + 1) + perUrl + "'>下一页</a></li>");
                pagination.append("<li><a href='" + url + "?" + "size=" + size + "&" + "page=" + totalInt + perUrl + "'>末页</a></li>");
            } else {
                pagination.append("<li class=\"disabled\"><a href=\"#\">下一页</a></li>");
                pagination.append("<li class=\"disabled\"><a href=\"#\">末页</a></li>");
            }
        }
        pagination.append("</ul>");
    }

    private void buildNumberStylePaginationPost(StringBuilder pagination,
                                                String jsmethod) {

        // 数字样式 [1 2 3 4 5 6 7 8 9
        // 10 > >>]
        Integer cpageInt = Integer.valueOf(page);
        Integer totalInt = Integer.valueOf(total);

        // 如果只有一页，则无需分页
        pagination.append("[&nbsp;");
        if (totalInt == 1) {
            pagination.append("<strong>1</strong>&nbsp;");
        } else {
            if (cpageInt > 1) {
                // 当前不是第一组，要显示“<< <”
                // <<：返回前一组第一页
                // <：返回前一页
                pagination.append("<a href='javascript:" + jsmethod + "(1," + size + ")'>«</a>&nbsp;");
                pagination.append("<a href='javascript:" + jsmethod + "(" + (cpageInt - 1) + "," + size + ")'>‹</a>&nbsp;");
            } else {
                pagination.append("«&nbsp;‹&nbsp;");
            }

            int v = (cpageInt - 4) > 0 ? (cpageInt - 4) : 1;
            int v1 = (cpageInt + 4) < totalInt ? (cpageInt + 4) : totalInt;
            if (v1 == totalInt) {
                v = totalInt - 10;
                v = v > 0 ? v : 1;
            } else if (v == 1 && v1 < totalInt) {
                v1 = totalInt > 10 ? 10 : totalInt;
            }
            // 10个为一组显示
            for (int i = v; i <= v1; i++) {
                if (cpageInt == i) { // 当前页要加粗显示
                    pagination.append("<strong>" + i + "</strong>&nbsp;");
                } else {
                    pagination.append("<a href='javascript:" + jsmethod + "(" + i + "," + size + ")'>" + i + "</a>&nbsp;");
                }
            }
            // 如果多于1组并且不是最后一组，显示“> >>”
            if (cpageInt < totalInt) {
                // >>：返回下一组最后一页
                // >：返回下一页
                pagination.append("<a href='javascript:" + jsmethod + "(" + (cpageInt + 1) + "," + size + ")'>›</a>&nbsp;");
                pagination.append("<a href='javascript:" + jsmethod + "(" + (cpageInt + 1) + "," + size + ")'>»</a>&nbsp;");
            } else {
                pagination.append("›&nbsp;»&nbsp;");
            }
        }
        pagination.append("]");

    }

    private void buildNumberNewStylePaginationPost(StringBuilder pagination,
                                                   String jsmethod) {
        Integer cpageInt = Integer.valueOf(page);
        Integer totalInt = Integer.valueOf(total);

        pagination.append("<ul>");
        if (totalInt == 1) {
            // 如果只有一页，则无需分页
            //pagination.append("<strong>1</strong>&nbsp;");
        } else {
            if (cpageInt > 1) {
                // 当前不是第一组，要显示“<< <”
                // <<：返回前一组第一页
                // <：返回前一页
                pagination.append("<li><a href='javascript:" + jsmethod + "(1," + size + ")'>首页</a></li>");
                pagination.append("<li><a href='javascript:" + jsmethod + "(" + (cpageInt - 1) + "," + size + ")'>上一页</a></li>");
            } else {
                pagination.append("<li class=\"disabled\"><a href=\"#\">首页</a></li>");
                pagination.append("<li class=\"disabled\"><a href=\"#\">上一页</a></li>");
            }


            int pageStart = 0;
            int pageEnd = 0;
            if (totalInt < linkSize) {
                pageStart = 1;
                pageEnd = totalInt;
            } else {
                pageStart = cpageInt - new Float(Math.floor(linkSize / 2)).intValue();
                pageStart = pageStart < 1 ? 1 : pageStart;
                if (pageStart + linkSize > totalInt) {
                    pageStart = totalInt - linkSize + 1;
                    pageStart = pageStart < 1 ? 1 : pageStart;
                    pageEnd = totalInt;
                } else {
                    pageEnd = pageStart + linkSize - 1;
                }
            }

            for (int i = pageStart; i <= pageEnd; i++) {
                if (cpageInt == i) {

                    pagination.append("<li class=\"active\"><a>" + i + "</a></li>");
                } else {
                    pagination.append("<li><a href='javascript:" + jsmethod + "(" + i + "," + size + ")'>" + i + "</a></li>");
                }
            }

//                        int v = (cpageInt - 4) > 0 ? (cpageInt - 4) : 1;
//                        int v1 = (cpageInt + 4) < totalInt ? (cpageInt + 4) : totalInt;
//                        if (v1 == totalInt) {
//                                v = totalInt - 10;
//                                v = v > 0 ? v : 1;
//                        } else if (v == 1 && v1 < totalInt) {
//                                v1 = totalInt > 10 ? 10 : totalInt;
//                        }
//                        // 10个为一组显示
//                        for (int i = v; i <= v1; i++) {
//                                if (cpageInt == i) { // 当前页要加粗显示
//                                        pagination.append("<strong>" + i + "</strong>&nbsp;");
//                                } else {
//                                        pagination.append("<a href='javascript:" + jsmethod + "(" + i + "," + size + ")'>" + i + "</a>&nbsp;");
//                                }
//                        }
            // 如果多于1组并且不是最后一组，显示“> >>”
            if (cpageInt < totalInt) {
                // >>：返回下一组最后一页
                // >：返回下一页
                pagination.append("<li><a href='javascript:" + jsmethod + "(" + (cpageInt + 1) + "," + size + ")'>下一页</a></li>");
                pagination.append("<li><a href='javascript:" + jsmethod + "(" + (cpageInt + 1) + "," + size + ")'>末页</a></li>");
            } else {
                pagination.append("<li class=\"disabled\"><a href=\"#\">下一页</a></li>");
                pagination.append("<li class=\"disabled\"><a href=\"#\">末页</a></li>");
            }
        }
        pagination.append("<ul/>");
    }

    private String buildExtraUrl(Map<String, Object> params)
            throws UnsupportedEncodingException {

        StringBuffer perUrl = new StringBuffer("");
        Set<Entry<String, Object>> entrySet = params.entrySet();
        for (Entry<String, Object> entry : entrySet) {
            String permName = entry.getKey();
            if ("page".equals(permName) || "size".equals(permName)) {
                continue;
            }


            Object _obj = entry.getValue();
            // if (_obj == null) {
            // _obj = req.getAttribute(permName);
            // }
            if (_obj == null || "".equals(_obj)) {
                continue;
            }

            perUrl.append("&" + permName);
            perUrl.append("=" + parserParamObj(_obj));
        }
        return perUrl.toString();
    }

    private String parserParamObj(Object _obj)
            throws UnsupportedEncodingException {
        String vType = _obj.getClass().toString();
        String vValue = "";
        vType = vType.substring(vType.lastIndexOf(".") + 1, vType.length());
        if ("String".equals(vType)) {
            String tmp = (String) _obj;
            tmp = URLEncoder.encode(tmp, "UTF-8");
            vValue = tmp.toString();
        } else if ("Long".equals(vType)) {
            Long tmp = (Long) _obj;
            vValue = tmp.toString();
        } else if ("Float".equals(vType)) {
            Float tmp = (Float) _obj;
            vValue = tmp.toString();
        } else if ("Integer".equals(vType)) {
            Integer tmp = (Integer) _obj;
            vValue = tmp.toString();
        } else if ("Boolean".equals(vType)) {
            Boolean tmp = (Boolean) _obj;
            vValue = tmp.toString();
        } else if ("Date".equals(vType)) {
            Date tmp = (Date) _obj;
            vValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tmp);
        }
        return vValue;
    }

    private void buildRefreshScript(StringBuilder pagination, boolean isGet,
                                    String jsmethod, String url, String perUrl) {
        if (!changePage) {
            return;
        }
        if (isGet) {
            pagination.append("<script>");
            pagination.append("function _refreshPage(p){");
            pagination.append("window.location.href=\"" + url + "?" + "page=1&"
                    + "size=\"+p");
            if (perUrl != null && perUrl.length() > 0) {
                pagination.append("+\"" + perUrl + "\"");
            }
            pagination.append("}");
            pagination.append("</script>");
        } else {
            pagination.append("<script>");
            pagination.append("function _refreshPage(p)");
            pagination.append("{" + jsmethod + "(1,p);}");
            pagination.append("</script>");
        }
    }

    private void buildJumpScript(StringBuilder pagination, boolean isGet,
                                 String jsmethod, String url, String perUrl) {
        if (changePage) {
            return;
        }
        if (isGet) {
            pagination.append("<script>");
            pagination.append("function _jumpPage(p){");
            pagination.append("window.location.href=\"" + url + "?" + "size=" + size + "&"
                    + "page=\"+p");
            if (perUrl != null && perUrl.length() > 0) {
                pagination.append("+\"" + perUrl + "\"");
            }
            pagination.append("}");
            pagination.append("</script>");
        } else {
            pagination.append("<script>");
            pagination.append("function _jumpPage(p)");
            pagination.append("{" + jsmethod + "(p," + size + ");}");
            pagination.append("</script>");
        }
    }

    private void buildSelectPageSize(StringBuilder pagination) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(10);
        set.add(20);
        set.add(50);
        set.add(100);
        if (!changePage) {
            if (StringUtils.isNotBlank(totalCount)) {
                pagination.append("共" + totalCount + "条记录&nbsp");
            }
//                        pagination.append("");
            pagination.append(page + "/" + total + "页");
            return;
        }

        if (StringUtils.isNotBlank(totalCount)) {
            pagination.append("共" + totalCount + "条记录&nbsp");
        }
        pagination.append("每页<select id=\"sizeSel\" class=\"input-mini\" onchange=\"_refreshPage(this.value);\" >");
        pagination.append("<option value='10' ");
        int ps = 10;
        if (size != null && !"".equals(size)) {
            ps = Integer.valueOf(size);
            set.add(ps);
        }
        for (Integer integer : set) {
            pagination.append("<option value='" + integer + "' ");
            if (integer == ps) {
                pagination.append("selected");
            }
            pagination.append(">" + integer + "</option>");
        }

//                if (ps == 10) {
//                        pagination.append("selected");
//                }
//                pagination.append(">10</option><option value='20' ");
//                if (ps == 20) {
//                        pagination.append("selected");
//                }
//                pagination.append(">20</option><option value='50' ");
//                if (ps == 50) {
//                        pagination.append("selected");
//                }
//                pagination.append(">50</option><option value='100' ");
//                if (ps == 100) {
//                        pagination.append("selected");
//                }
        pagination.append("</select>条&nbsp;");

        pagination.append("&nbsp" + page + "/" + total + " 页");
    }

    private void buildJumpPageSelection(StringBuilder pagination, String url, String extraUrl) {
        if (changePage) {
            return;
        }

        pagination.append("&nbsp;跳转至<select style=\"width:60px\" onchange=\"_jumpPage(this.value);\">");
        for (int i = 1; i <= Integer.valueOf(total); i++) {
            if (StringUtils.isNumeric(page) && i == Integer.parseInt(page)) {
                pagination.append("<option value='" + i + "' selected>" + i + "</option>");
            } else {
                pagination.append("<option value='" + i + "'>" + i + "</option>");
            }
        }
        pagination.append("</select>页");
    }
}
