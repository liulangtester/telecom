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
	var calling = function(){
// 		alert($("#called-num").val());
		window.location = "${pageContext.request.contextPath}/callingServlet?beCalledNumber="+$("#called-num").val()+"&callNumber="+$("#callNumber").val();
	};
</script>
</head>

<body class="gray-bg top-navigation">
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
								<a aria-expanded="false" role="button" href="user_home.jsp"> 首页</a>
							</li>
							<li class="default">
								<a aria-expanded="false" role="button" href="${pageContext.request.contextPath}/callServlet"> 打电话</a>
							</li>
							<li class="dropdown">
								<a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> 账单查询 <span class="caret"></span></a>
								<ul role="menu" class="dropdown-menu">
									<li>
										<a href="${pageContext.request.contextPath}/rechargeServlet" >充值</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/rechargeRecordServlet">充值记录查询</a>
									</li>
									<li>
										<a href="${pageContext.request.contextPath}/callRecordServlet">通话记录及余额查询</a>
									</li>
								</ul>
							</li>

						</ul>
						<ul class="nav navbar-top-links navbar-right">
							<li>
								<a href="${pageContext.request.contextPath}/userLogoutServlet">
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
    				    <!--主叫号码-->
    				    <div class="row button-row">
            				<div class="">
            					<label  class="col-lg-4 control-label">主叫号码：</label>
                				<div class="col-lg-8">
                    				<select class="form-control" id="num" name="num">
                    				<option id="callNumber" name="callNumber">${user.user_phone}</option>
  								  	</select>
            				    </div><!-- /input-group -->
            				    <br />
         				    </div><!-- /.col-lg-12 -->
        				</div><!-- /.row -->
        				<!--被叫号码-->
        				<div class="row button-row">
            				<div class="col-lg-12">
                				<div class="input-group">
                    				<input id="called-num" name="beCalledNumber" type="text" class="form-control" placeholder="请输入被叫号码" >
                    				<div><span style="color:red">${msg}</span></div>
                    				<span class="input-group-btn">
                        				<button id="del_num" class="btn btn-danger" type="button">
                        					<i class="fa fa-arrow-left"></i>
                        				</button>
                    				</span>
            				    </div><!-- /input-group -->
         				    </div><!-- /.col-lg-12 -->
        				</div><!-- /.row -->
        				<!--模拟键盘-->
        				<div class="row button-row">
        					<div class="col-lg-4">
        						<button id="bt1" class="btn btn-lg btn-default btn-circle" type="button">1</button>
        					</div>
        					<div class="col-lg-4">
        						<button id="bt2" class="btn btn-lg btn-default btn-circle" type="button">2</button>
        					</div>
        					<div class="col-lg-4">
        						<button id="bt3" class="btn btn-lg btn-default btn-circle" type="button">3</button>
        					</div>
        				</div>
        				<div class="row button-row">
        					<div class="col-lg-4">
        						<button id="bt4" class="btn btn-lg btn-default btn-circle" type="button">4</button>
        					</div>
        					<div class="col-lg-4">
        						<button id="bt5" class="btn btn-lg btn-default btn-circle" type="button">5</button>
        					</div>
        					<div class="col-lg-4">
        						<button id="bt6" class="btn btn-lg btn-default btn-circle" type="button">6</button>
        					</div>
        				</div>
        				<div class="row button-row">
        					<div class="col-lg-4">
        						<button id="bt7" class="btn btn-lg btn-default btn-circle" type="button">7</button>
        					</div>
        					<div class="col-lg-4">
        						<button id="bt8" class="btn btn-lg btn-default btn-circle" type="button">8</button>
        					</div>
        					<div class="col-lg-4">
        						<button id="bt9" class="btn btn-lg btn-default btn-circle" type="button">9</button>
        					</div>
        				</div>
        				<div class="row button-row">
        					<div class="col-lg-4">
        						<button id="btstar" class="btn btn-lg btn-default btn-circle" type="button">*</button>
        					</div>
        					<div class="col-lg-4">
        						<button id="bt0" class="btn btn-lg btn-default btn-circle" type="button">0</button>
        					</div>
        					<div class="col-lg-4">
        						<button id="btjing" class="btn btn-lg btn-default btn-circle" type="button">#</button>
        					</div>
        				</div>
        				<div class="row button-row">
        					<div class="col-lg-4"></div>
        					<div class="col-lg-4">
        						<button onclick="calling()" id="caaa" class="btn btn-lg btn-primary btn-circle" type="button"><i class="fa fa-phone"></i></button>
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
	<script src="${pageContext.request.contextPath}/js/user_phone.js"></script>

	
	<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>

</html>

