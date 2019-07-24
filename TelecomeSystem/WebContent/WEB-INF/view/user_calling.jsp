<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>电信客户计费系统</title>

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/my-style.css" rel="stylesheet">
	
	<script>
		var call = function(){
			window.location = "${pageContext.request.contextPath}/getBillServlet?beCalledNumber="
					+${beCalledNumber}+"&callNumber="+${callNumber}+"&callTime="+$(".callTime").html();
		};
	</script>
</head>

<body class="gray-bg top-navigation" onload="time_fun()">
	<div id="wrapper">
		<div id="page-wrapper" class="gray-bg">
			<!--导航栏-->
			<div class="row border-bottom white-bg">
				<nav class="navbar navbar-static-top" role="navigation">
					<div class="navbar-header">
						<button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                            <i class="fa fa-reorder"></i>
                        </button>
						<a href="#" class="navbar-brand">电信客户计费系统-客户端</a>
					</div>
					<div class="navbar-collapse collapse" id="navbar">
						<ul class="nav navbar-nav">
							<li class="active">
								<a aria-expanded="false" role="button" href="user.html"> 首页</a>
							</li>
							<li class="default">
								<a aria-expanded="false" role="button" href="${pageContext.request.contextPath}/pageAction/goToBill"> 打电话</a>
							</li>
							<li class="dropdown">
								<a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> 账单查询 <span class="caret"></span></a>
								<ul role="menu" class="dropdown-menu">
									<li>
										<a href="${pageContext.request.contextPath}/pageAction/goToChecking" >充值</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/pageAction/goToRecharge">充值记录查询</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/pageAction/goToBill">通话记录查询</a>
									</li>
								</ul>
							</li>

						</ul>
						<ul class="nav navbar-top-links navbar-right">
							<li>
								<a href="login.html">
									<i class="fa fa-sign-out"></i> 退出
								</a>
							</li>
						</ul>
					</div>
				</nav>
			</div>
			<!--打电话界面-->
			<div class="wrapper wrapper-content">
				<div class="container phoneview">
					<form class="bs-example bs-example-form" role="form" action="#">
        				<div class="row button-row">
							<!--计时界面-->
							<h1 id="mytime" class="callTime" name="callTime">00:00:00</h1>
        				</div>
        				<!--联系人头像-->
        				<div class="row button-row phone-icon">
        					<i class="fa fa-user"></i>
        				</div>
        				<!--挂断按钮-->
        				<div class="row button-row">
        					<div class="col-lg-4"></div>
        					<div class="col-lg-4">
        						<button onclick="call()" id="" class="btn btn-lg btn-danger btn-circle" type="button"><i class="fa fa-phone"></i></button>
        					</div>
        					<div class="col-lg-4"></div>
        				</div>
    				</form>
					
				</div>

			</div>
			<!--页尾-->
			<div class="footer">
				<div class="pull-right">
					By：
					<a href="" target="_blank">Matthew's</a>
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
	<script src="${pageContext.request.contextPath}/js/user_phone2.js" ></script>
	
	<!--计时器-->
	<script>
        function two_char(n) {
            return n >= 10 ? n : "0" + n;
        }
        function time_fun() {
            var sec=0;
            setInterval(function () {
                sec++;
                var date = new Date(0, 0)
                date.setSeconds(sec);
                var h = date.getHours(), m = date.getMinutes(), s = date.getSeconds();
                document.getElementById("mytime").innerText = two_char(h) + ":" + two_char(m) + ":" + two_char(s);
            }, 1000);
        }
    </script>
	
	<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>

