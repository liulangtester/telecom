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
	var submit = function(){
// 		alert("输入内容不能为空");
			//获取select下拉框选中的option里的内容
// 			var v = document.getElementsByTagName("select")[0].value;
// 	 		alert(v);
// 	 		alert($("#phone").val());
// 	 		alert($("#name").val());
// 	 		alert($("#customerType").val());
// 	 		alert($("#address").val());
// 	 		alert($("#manager").val());
			if($("#phone").val() == null || "" == $("#phone").val() 
					|| $("#name").val() == null || "" == $("#name").val() 
					|| $("#account").val() == null || "" == $("#account").val()
					|| $("#bank").val() == null || "" == $("#bank").val()
					|| $("#s_bank").val() == null || "" == $("#s_bank").val()
					|| $("#remain_money").val() == null || "" == $("#remain_money").val()
					|| $("#total_cost").val() == null || "" == $("#total_cost").val()
					|| $("#month_cost").val() == null || "" == $("#month_cost").val()
					){
				alert("输入内容不能为空");
			}
			else{
		
				window.location = "${pageContext.request.contextPath}/workerAccountServlet?method=addOrUpdateToReserve&phone="
					+$("#phone").val()+"&name="+$("#name").val()+"&account="+$("#account").val()
					+"&bank="+$("#bank").val()+"&s_bank="+$("#s_bank").val()+"&remain_money="+$("#remain_money").val()
					+"&total_cost="+$("#total_cost").val()+"&month_cost="+$("#month_cost").val();
			}
	
		
		
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
                    
                    <div>
                        <h4 class="example-title">账户资料管理</h4>
                    </div>    
                    	
                				<div style="text-align:center; margin-top:200px; margin-bottom:200px">
                					<div >
                					<span style="font-size:17px; "></span>用户手机：<input id="phone" value="${account.user_phone}">
                					</div>
                					<div style="marigin-top:20px">
                						<span style="font-size:17px"></span>用户姓名：<input id="name" value="${account.user_name}">
                					</div>
                					<div>
                						<span style="font-size:17px"></span>银行账号：<input id="account" value="${account.bank_account}">
                					</div>
                					<div>
                					<span style="font-size:17px"></span>开户银行：<input id="bank" value="${account.open_bank}">
                					</div>
                					<div>
                						<span style="font-size:17px"></span>开户支行：<input id="s_bank" value="${account.sub_bank}">
                					</div>
                					<div>
                						<span style="font-size:17px"></span>账户余额：<input id="remain_money" value="${account.remainder_money}">
                					</div>
                					<div>
                						<span style="font-size:17px"></span>总消费额：<input id="total_cost" value="${account.cost_money}">
                					</div>
                					<div>
                						<span style="font-size:17px"></span>本月消费额：<input id="month_cost" value="${account.month_money}">
                					</div>
                					<div>
          
  								  		<button onclick="submit()" style="margin-top:20px">提交</button>
                					</div>
                	
                    			</div>
                    
				
			</div>
			
			
			<div class="footer" >
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
