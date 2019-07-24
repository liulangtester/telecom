$(function(){
	$("#closeCall").click(function(){
		var time = $("#mytime").html();
		$.ajax({
			url:'/Telecom/phoneAction/closeCall',
			dataType:'json',
			data:{
				time:time
			},
			error:function(){
				window.location.href="/Telecom/pageAction/goToCall";
			}
		});
	});
	
	var timer = setInterval(function(){
		$.ajax({
			url:'/Telecom/phoneAction/checkMoney',
			dataType:'json',
			success:function(data){
				if(data){
					alert("余额不足");
					$("#closeCall").click();
				}
			}
		});
	},60000);
});