
$(function(){
	//获取主叫号码
	$.ajax({
		url: '/Telecom/phoneAction/getPhoneNums',
		dataType:'json',
		success:function(data){
			for(var i in data){
				$("#num").append("<option value="+data[i]+">"+data[i]+"</option>");
			}
		}}
	);
	
	$.ajax({
		url: '/Telecom/customer/selAccount',
		dataType:'json',
		success:function(data){
			for(var i in data){
				$("#acc_num").append("<option value="+data[i].acc_banknum+">"+data[i].acc_banknum+"</option>");
			}
		}}
	);
	
	
	//被叫号码输入
	/*var calledNum;
	$('#bt1').click(function(){
		calledNum+=$("#called-num").val();
		alert($("#called-num").val());
	});
	$('#called-num').val()=calledNum;*/
	
	var calledNum='';
	$('.btn-default').click(function(){
		calledNum+=$(this).html();
		$('#called-num').val(calledNum);
	});
	
	$("#del_num").click(function(){
		calledNum = calledNum.substring(0,calledNum.length-1);
		$('#called-num').val(calledNum);
	});
	
	$("#call").click(function(){
		var phone_num = $("#called-num").val();
		var acc_num = $("#acc_num").val();
		var money = $("#num").val();
		$.ajax({
			url:'/Telecom/phoneAction/recharge',
			dataType:'json',
			type:'post',
			data:{
				phone_num:phone_num,
				acc_num:acc_num,
				money:money
			},
			error:function(data){
				//alert("充值成功");
				window.location.href="/Telecom/pageAction/goToCheck";
			}
		});
	});
	
	//提交数据
	
});



