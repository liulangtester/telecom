
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
		var calledPhoen = $("#called-num").val();
		var callPhone = $("#num").val();
		$.ajax({
			url:'/Telecom/phoneAction/call',
			dataType:'json',
			type:'post',
			data:{
				callPhone:callPhone,
				calleePhone:calledPhoen
			},
			success:function(data){
				if(data==true){
					window.location.href="/Telecom/pageAction/goToPhone2";
				}
			}
		});
	});
	
	//提交数据
	
});



