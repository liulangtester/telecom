//customer-table
$('#customer-table').bootstrapTable({
  url: 'data.json',
  dataType: "json",
  toolbar: '#toolbar',
  pagination: true,
  search: true,
  clickToSelect: true,    //是否启用点击选中行
  height: 500,
  showRefresh: true,
  pageSize: 10,           //每页显示的记录数
  data: [{
        Tid: 1,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }, {
        Tid: 2,
        First: '斯蒂芬',
        sex: '男',
        Score: 87
    }]
 });