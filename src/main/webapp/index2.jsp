<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/index.css" type="text/css"></link>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>
<form action="">
    <h1 align="center" font="16px">模拟某平台推送招标项目阶段数据</h1>
    <table>
        <table>
            <tr> <th colspan="2">PROJECT的数据</th></tr>
            <tr><td>PROJECT_CODE</td>
                <td>
                    <input type="text" value="M1100000015013153" name="PROJECT_CODE">
                </td>
            </tr>
            <tr><td>PROJECT_NAME</td>
                <td>
                    <input type="text" value="中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程" name="PROJECT_NAME">
                </td>
            </tr>
        </table>
        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <table>
            <tr><th colspan="2">TENDER_PROJECT的数据</th></tr>
            <tr><td >TENDER_PROJECT_CODE</td>
                <td>
                    <input type="text" value="M1100000015013153001" name="TENDER_PROJECT_CODE">
                </td>
            </tr>
            <tr><td>TENDER_PROJECT_NAME</td>
                <td>
                    <input type="text" value="中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程" name="TENDER_PROJECT_NAME">
                </td>
            </tr>
            <tr><td>REGION_CODE</td>
                <td>
                    <input type="text" value="110000" name="REGION_CODE">
                </td>
            </tr>
            <tr><td>TENDER_CONTENT</td>
                <td>
                    <input type="text" value="墙体拆除、屋顶拆除、车间洁净板装修、固化剂与自流平地面装修、洁净车间温度及换风控制。" name="TENDER_CONTENT">
                </td>
            </tr>
            <tr><td>TENDER_MODE</td>
                <td>
                    <input type="text" value="1" name="TENDER_MODE">
                </td>
            </tr>
            <tr><td>SUPERVISE_DEPT_NAME(监督部门名称)</td>
                <td>
                    <input type="text" value="中粮集团" name="SUPERVISE_DEPT_NAME">
                </td>
            </tr>
            <tr><td>REPORT_DUTY(申报责任人)</td>
                <td>
                    <input type="text" value="顾立华" name="REPORT_DUTY">
                </td>
            </tr>
            <tr><td>TRADE_PLAT</td>
                <td>
                    <input type="text" value="M1100000015" name="TRADE_PLAT">
                </td>
            </tr>
            <tr><td>CREATE_TIME</td>
                <td>
                    <input type="text" value="20180212041529" name="CREATE_TIME">
                </td>
            </tr>
        </table>
        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <table>
            <tr><th colspan="2">TENDERERS的数据</th></tr>
            <table>
                <tr><td >TENDERER</td><td></tr>
                <tr><td>TENDERER_CODE</td>
                    <td>
                        <input type="text" value="123456" name="TENDERER_CODE">
                    </td>
                </tr>
                <tr><td>TENDERER_NAME</td>
                    <td>
                        <input type="text" value="中粮丰通（北京）食品有限公司" name="TENDERER_NAME">
                    </td>
                </tr>
            </table>
            <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
            <table>
                <tr><td>TENDERER</td><td></tr>
                <tr><td>TENDER_CODE_TYPE</td>
                    <td>
                        <input type="text" value="98" name="TENDER_CODE_TYPE">
                    </td>
                </tr>
                <tr><td>TENDERER_NAME</td>
                    <td>
                        <input type="text" value="中粮丰通（天津）食品有限公司" name="TENDERER_NAME">
                    </td>
                </tr>
            </table>
        </table>
        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <table>
            <tr><th colspan="2">TENDER_AGENCYS的数据</th></tr>
            <table>
                <tr><td>TENDER_AGENCY</td><td></tr>
                <tr><td>TENDER_AGENCY_CODE_TYPE</td>
                    <td>
                        <input type="text" value="97" name="TENDER_AGENCY_CODE_TYPE">
                    </td>
                </tr>
                <tr><td>TENDER_AGENCY_CODE</td>
                    <td>
                        <input type="text" value="91110108710925518R" name="TENDER_AGENCY_CODE">
                    </td>
                </tr>
                <tr><td>TENDER_AGENCY_NAME</td>
                    <td>
                        <input type="text" value="国信招标集团股份有限公司" name="TENDER_AGENCY_NAME">
                    </td>
                </tr>
            </table>
        </table>
        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <table>
            <tr><th colspan="2">BID_SECTIONS的数据</th></tr>
            <table>
                <tr><td>BID_SECTION</td><td></tr>
                <tr><td>BID_SECTION_CODE</td>
                    <td>
                        <input type="text" value="M1100000015013153001001" name="BID_SECTION_CODE">
                    </td>
                </tr>
                <tr><td>BID_SECTION_NAME</td>
                    <td>
                        <input type="text" value="中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程" name="BID_SECTION_NAME">
                    </td>
                </tr>
                <tr><td>CONTRACT_RECKON_PRICE(标段合同估算价)</td>
                    <td>
                        <input type="text" value="900000.000000" name="CONTRACT_RECKON_PRICE">
                    </td>
                </tr>
                <tr><td>BIDDING_ELIGIBILITY(投标人资格条件)</td>
                    <td>
                        <input type="text" value="合格" name="BIDDING_ELIGIBILITY">
                    </td>
                </tr>
            </table>
        </table>
    </table>
</form>
</body>
</html>