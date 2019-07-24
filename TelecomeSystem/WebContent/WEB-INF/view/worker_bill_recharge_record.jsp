<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/index_v5.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:51 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>电信客户计费系统</title>

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="c${pageContext.request.contextPath}/ss/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/my-style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<script>
	var get = function(){
// 			alert($("#get").val())
			window.location = "${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&search="+$("#get").val();
	};
</script>
</head>

<body class="gray-bg top-navigation">
	<div id="wrapper">
		<div id="page-wrapper" class="gray-bg" >
			<!--导航栏-->
				<div class="row border-bottom white-bg">
					<nav class="navbar navbar-static-top" role="navigation">
					<div class="navbar-header">
						<button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                            <i class="fa fa-reorder"></i>
                        </button>
						<a href="#" class="navbar-brand">电信客户计费系统-管理端</a>
					</div>
					<div class="navbar-collapse collapse" id="navbar">
						<ul class="nav navbar-nav">
							<li class="active">
								<a aria-expanded="false" role="button" href="worker_home.jsp"> 首页</a>
							</li>
							<li class="dropdown">
								<a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> 客户资料管理 <span class="caret"></span></a>
								<ul role="menu" class="dropdown-menu">
									<li>
										<a href="${pageContext.request.contextPath}/workerCustomerServlet?method=add">客户资料添加</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/workerCustomerServlet?method=query">客户资料查询</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/workerCustomerServlet?method=updateOrDelete">客户资料修改及删除</a>
									</li>
							
									
								</ul>
							</li>
								<li class="dropdown">
								<a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> 账户资料管理 <span class="caret"></span></a>
								<ul role="menu" class="dropdown-menu">
									<li>
										<a href="${pageContext.request.contextPath}/workerAccountServlet?method=add">账户资料添加</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/workerAccountServlet?method=query">账户资料查询</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/workerAccountServlet?method=updateOrDelete">账户资料修改或删除</a>
									</li>					
								</ul>
							</li>
									<li class="dropdown">
								<a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> 用户资料管理 <span class="caret"></span></a>
								<ul role="menu" class="dropdown-menu">
									<li>
										<a href="${pageContext.request.contextPath}/workerUserServlet?method=add">用户资料添加</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/workerUserServlet?method=query">用户资料查询</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/workerUserServlet?method=updateOrDelete">用户资料修改或删除</a>
									</li>					
								</ul>
							</li>
							<li class="dropdown">
								<a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> 账单查询 <span class="caret"></span></a>
								<span style="color:red">${msg}</span>
								<ul role="menu" class="dropdown-menu">
									<li>
										<a href="${pageContext.request.contextPath}/workerBillServlet?method=callRecord">通话记录查询</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord">充值记录查询</a>
									</li>
								
									
								</ul>
							</li>

						</ul>
						<ul class="nav navbar-top-links navbar-right">
							<li>
								<a href="${pageContext.request.contextPath}/workerLogoutServlet">
									<i class="fa fa-sign-out"></i> 退出
								</a>
							</li>
						</ul>
					</div>
				</nav>
			</div>
	        
			<div class="row clearfix">
				<div class="col-md-12 column">
                    
                    <div class="col-sm-12">
                        <h4 class="example-title">充值记录查询</h4>
                        
                        <div class="toolbar-menu">
                        	<div class="btn-group hidden-xs" id="toolbar" role="group">
            	        	<input id="get" style="margin-right:10px"><button onclick="get()">搜索</button> <span style="color:red">${msg}</span>
                        		
                    		</div>
                        </div>
                        
                        <div class="example">
                            <table id="bill-table" data-mobile-responsive="true">
                                
                                    <tr>
                                    	<td><input id="checked" class="checkbox"  type="checkbox"/> </td>
                                        <th data-field="check_id">充值记录id</th>
                                        <th data-field="phone_id">电话号码</th>
                                        <th data-field="account_id">账户号码</th>
                                        <th data-field="check_balance">充值金额</th>
                                        <th data-field="time">充值时间</th>
                                    </tr>
                                    
                                    <!--                                         迭代数据 -->
											<c:forEach items="${rechargeRecord}" var="r">
                                        <tr class="field" style="border-bottom:#f3d5d6 1px dashed;">
                                        	<td><input id="checkbox" class="checkbox" value="${r.id}" id="box_${r.id}" type="checkbox"/> </td>                                 
							            	<td>${r.id}</td>
							            	<td>${r.phone}</td>
							                <td>${r.account}</td>
							                <td>${r.money }</td>
							                <td>${r.time }</td>
				
							             </tr>   
							                </c:forEach>
                               
                            </table>
                        </div>
                    </div>
                    
				</div>
			</div>
			
			  <div align="center">
	    	<!-- 控制首页 与 上一页  -->
	    	<c:if test="${notices.pageNum==1 && notices.pageNum==notices.totalPage}">
<!-- 	    	只有一页，四个都不可点 -->
	    		【首页】
	    		【下一页】
	    		【上一页】
	    		【尾页】
	    	</c:if>
			
			<c:if test="${notices.pageNum==1 && notices.pageNum<notices.totalPage}">
<!-- 	    	在第一页，且不止一页，首页，上一页不可点 -->
	    		【首页】
	    		【上一页】
	    		【<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&thisPage=${notices.pageNum+1}&search=${notices.search}">下一页</a>】
	    		【<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&thisPage=${notices.totalPage}&search=${notices.search}">尾页</a>】
	    	</c:if>
	    	    		
	    	<c:if test="${notices.pageNum>1 && notices.pageNum<notices.totalPage}">
<!-- 	    	全都可点 -->
	    		【<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&thisPage=1&search=${notices.search}">首页</a>】
	    		【<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&thisPage=${notices.pageNum-1}&search=${notices.search}">上一页</a>】
	    		【<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&thisPage=${notices.pageNum+1}&search=${notices.search}">下一页</a>】
	    		【<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&thisPage=${notices.totalPage}&search=${notices.search}">尾页</a>】
	    	</c:if>
	    	
	    	<c:if test="${notices.pageNum!=1 && notices.pageNum==notices.totalPage}">
<!-- 	    	在最后一页，下一页和尾页不可点 -->
	    		【<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&thisPage=1&search=${notices.search}">首页</a>】
	    		【<a href="${pageContext.request.contextPath}/workerBillServlet?method=rechargeRecord&thisPage=${notices.pageNum-1}&search=${notices.search}">上一页</a>】
	    		【下一页】
	    		【尾页】
	    	</c:if>
	    </div>
			
			
			<div class="footer">
				<div class="pull-right">
					By：
					<a href="" target="_blank">刘学生</a>
				</div>
				<div>
					<strong>Telecom</strong> 电信计费 &copy; 2019
				</div>
			</div>
			
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.resize.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/chartJs/Chart.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/peity/jquery.peity.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/demo/peity-demo.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/demo/bootstrap-table-demo.min.js"></script>	
    
    <script src="${pageContext.request.contextPath}/js/user_check.js"></script>	
    
	<script>
		$(document).ready(function() {
			var d1 = [
				[1262304000000, 6],
				[1264982400000, 3057],
				[1267401600000, 20434],
				[1270080000000, 31982],
				[1272672000000, 26602],
				[1275350400000, 27826],
				[1277942400000, 24302],
				[1280620800000, 24237],
				[1283299200000, 21004],
				[1285891200000, 12144],
				[1288569600000, 10577],
				[1291161600000, 10295]
			];
			var d2 = [
				[1262304000000, 5],
				[1264982400000, 200],
				[1267401600000, 1605],
				[1270080000000, 6129],
				[1272672000000, 11643],
				[1275350400000, 19055],
				[1277942400000, 30062],
				[1280620800000, 39197],
				[1283299200000, 37000],
				[1285891200000, 27000],
				[1288569600000, 21000],
				[1291161600000, 17000]
			];
			var data1 = [{
				label: "数据1",
				data: d1,
				color: "#17a084"
			}, {
				label: "数据2",
				data: d2,
				color: "#127e68"
			}];
			$.plot($("#flot-chart1"), data1, {
				xaxis: {
					tickDecimals: 0
				},
				series: {
					lines: {
						show: true,
						fill: true,
						fillColor: {
							colors: [{
								opacity: 1
							}, {
								opacity: 1
							}]
						},
					},
					points: {
						width: 0.1,
						show: false
					},
				},
				grid: {
					show: false,
					borderWidth: 0
				},
				legend: {
					show: false,
				}
			});
			var lineData = {
				labels: ["一月", "二月", "三月", "四月", "五月", "六月", "七月"],
				datasets: [{
					label: "示例数据",
					fillColor: "rgba(220,220,220,0.5)",
					strokeColor: "rgba(220,220,220,1)",
					pointColor: "rgba(220,220,220,1)",
					pointStrokeColor: "#fff",
					pointHighlightFill: "#fff",
					pointHighlightStroke: "rgba(220,220,220,1)",
					data: [65, 59, 40, 51, 36, 25, 40]
				}, {
					label: "示例数据",
					fillColor: "rgba(26,179,148,0.5)",
					strokeColor: "rgba(26,179,148,0.7)",
					pointColor: "rgba(26,179,148,1)",
					pointStrokeColor: "#fff",
					pointHighlightFill: "#fff",
					pointHighlightStroke: "rgba(26,179,148,1)",
					data: [48, 48, 60, 39, 56, 37, 30]
				}]
			};
			var lineOptions = {
				scaleShowGridLines: true,
				scaleGridLineColor: "rgba(0,0,0,.05)",
				scaleGridLineWidth: 1,
				bezierCurve: true,
				bezierCurveTension: 0.4,
				pointDot: true,
				pointDotRadius: 4,
				pointDotStrokeWidth: 1,
				pointHitDetectionRadius: 20,
				datasetStroke: true,
				datasetStrokeWidth: 2,
				datasetFill: true,
				responsive: true,
			};
			var ctx = document.getElementById("lineChart").getContext("2d");
			var myNewChart = new Chart(ctx).Line(lineData, lineOptions)
		});
	</script>
	<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

<!-- Mirrored from www.zi-han.net/theme/hplus/index_v5.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:52 GMT -->
</html>
