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

<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
.tg .tg-88nc{font-weight:bold;border-color:inherit;text-align:center}
.tg .tg-0r18{font-size:14px;border-color:inherit;text-align:center;vertical-align:top}
.tg .tg-57x1{color:#fe0000;border-color:inherit;vertical-align:top}
.tg .tg-l711{border-color:inherit}
.tg .tg-xevm{color:#fe0000;border-color:inherit}
.tg .tg-us36{border-color:inherit;vertical-align:top}
.tg .tg-7btt{font-weight:bold;border-color:inherit;text-align:center;vertical-align:top}
</style>
<table class="tg">
  <tr>
    <th class="tg-0r18" colspan="4">模拟某平台推送招标项目阶段数据</th>
  </tr>
  <tr>
    <td class="tg-88nc" colspan="2">TENDER_PROJECT的数据</td>
    <td class="tg-l711" ></td>
    <td class="tg-88nc" colspan="2">PROJECT的数据</td>
    <td class="tg-l711"></td>
  </tr>
  <tr>
    <td class="tg-l711">TENDER_PROJECT_CODE</td>
    <td class="tg-l711" width="60%"><input type="text" value="M1100000015013153001" name="TENDER_PROJECT_CODE"></td>
    <td class="tg-l711">PROJECT_CODE</td>
    <td class="tg-l711" width="60%"><input type="text" value="M1100000015013153" name="PROJECT_CODE"></td>
  </tr>
  <tr>
    <td class="tg-l711">TENDER_PROJECT_NAME</td>
    <td class="tg-l711"><input type="text" value="中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程" name="TENDER_PROJECT_NAME"></td>
    <td class="tg-l711">PROJECT_NAME</td>
    <td class="tg-l711"><input type="text" value="中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程" name="PROJECT_NAME"></td>
  </tr>
  <tr>
    <td class="tg-l711">REGION_CODE</td>
    <td class="tg-l711"><input type="text" value="110000" name="REGION_CODE"></td>
    <td class="tg-88nc" colspan="2">BID_SECTIONS的数据</td>
  </tr>
  <tr>
    <td class="tg-l711">TENDER_CONTENT</td>
    <td class="tg-l711"><input type="text" value="墙体拆除、屋顶拆除、车间洁净板装修、固化剂与自流平地面装修、洁净车间温度及换风控制。" name="TENDER_CONTENT"></td>
    <td class="tg-xevm">BID_SECTION</td>
    <td class="tg-l711"></td>
  </tr>
  <tr>
    <td class="tg-l711">TENDER_MODE</td>
    <td class="tg-l711"><input type="text" value="1" name="TENDER_MODE"></td>
    <td class="tg-l711">BID_SECTION_CODE</td>
    <td class="tg-l711"><input type="text" value="M1100000015013153001001" name="BID_SECTION_CODE"></td>
  </tr>
  <tr>
    <td class="tg-l711">SUPERVISE_DEPT_NAME(监督部门名称)</td>
    <td class="tg-l711"><input type="text" value="中粮集团" name="SUPERVISE_DEPT_NAME"></td>
    <td class="tg-l711">BID_SECTION_NAME</td>
    <td class="tg-l711"><input type="text" value="中粮丰通（北京）食品有限公司中式糕点线技改项目土建工程" name="BID_SECTION_NAME"></td>
  </tr>
  <tr>
    <td class="tg-l711">REPORT_DUTY(申报责任人)</td>
    <td class="tg-l711"><input type="text" value="顾立华" name="REPORT_DUTY"></td>
    <td class="tg-l711">CONTRACT_RECKON_PRICE(标段合同估算价)<br></td>
    <td class="tg-l711"><input type="text" value="900000.000000" name="CONTRACT_RECKON_PRICE"></td>
  </tr>
  <tr>
    <td class="tg-l711">TRADE_PLAT</td>
    <td class="tg-l711"><input type="text" value="M1100000015" name="TRADE_PLAT"></td>
    <td class="tg-l711">BIDDING_ELIGIBILITY(投标人资格条件)</td>
    <td class="tg-l711"><input type="text" value="合格" name="BIDDING_ELIGIBILITY"></td>
  </tr>
  <tr>
    <td class="tg-l711">CREATE_TIME</td>
    <td class="tg-l711"><input type="text" value="20180212041529" name="CREATE_TIME"></td>
    <td class="tg-88nc" colspan="2">TENDER_AGENCYS的数据</td>
    <td class="tg-l711"></td>
  </tr>
  <tr>
    <td class="tg-7btt">TENDERERS的数据</td>
    <td class="tg-us36"></td>
    <td class="tg-57x1">TENDER_AGENCY</td>
    <td class="tg-us36"></td>
  </tr>
  <tr>
    <td class="tg-57x1">TENDERER</td>
    <td class="tg-us36"></td>
    <td class="tg-us36">TENDER_AGENCY_CODE_TYPE</td>
    <td class="tg-us36"><input type="text" value="97" name="TENDER_AGENCY_CODE_TYPE"></td>
  </tr>
  <tr>
    <td class="tg-us36">TENDERER_CODE</td>
    <td class="tg-us36"><input type="text" value="123456" name="TENDERER_CODE"></td>
    <td class="tg-us36">TENDER_AGENCY_CODE</td>
    <td class="tg-us36"><input type="text" value="91110108710925518R" name="TENDER_AGENCY_CODE"></td>
  </tr>
  <tr>
    <td class="tg-us36">TENDERER_NAME</td>
    <td class="tg-us36"><input type="text" value="中粮丰通（北京）食品有限公司" name="TENDERER_NAME"></td>
    <td class="tg-us36">TENDER_AGENCY_NAME</td>
    <td class="tg-us36"><input type="text" value="国信招标集团股份有限公司" name="TENDER_AGENCY_NAME"></td>
  </tr>
  <tr>
    <td class="tg-57x1">TENDERER</td>
    <td class="tg-us36"></td>
    <td class="tg-us36"></td>
    <td class="tg-us36"></td>
  </tr>
  <tr>
    <td class="tg-us36">TENDER_CODE_TYPE</td>
    <td class="tg-us36"><input type="text" value="98" name="TENDER_CODE_TYPE"></td>
    <td class="tg-us36"></td>
    <td class="tg-us36"></td>
  </tr>
  <tr>
    <td class="tg-us36">TENDERER_NAME</td>
    <td class="tg-us36"><input type="text" value="中粮丰通（天津）食品有限公司" name="TENDERER_NAME"></td>
    <td class="tg-us36"></td>
    <td class="tg-us36"></td>
  </tr>
</table>
</form>