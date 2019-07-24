//customer-table
$(function(){
	$('#bill-table').bootstrapTable({
		url: '/Telecom/checkAction/listAll',
		dataType: "json",
		toolbar: '#toolbar',
		pagination: true,
		search: true,
		clickToSelect: true,    //是否启用点击选中行
		height: 510,
		showRefresh: true,
		pageSize: 10,           //每页显示的记录数
		striped: true
	});
})
 
 
 
//删除数据
$('#bt-del').click(function(){
	$row = $('#bill-table').bootstrapTable('getSelections');
	//alert(JSON.stringify($row));
	alert($row[0].a);
});
