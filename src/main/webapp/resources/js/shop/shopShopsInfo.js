$(function() {
	initOtherFunction();
});


function refush(){
	$("#refush").click();
}

function createShop(){
	$(".modal-title").html("商铺创建");
	$('#signupForm')[0].reset();
	$("#myModal").modal("show");
}

function initOtherFunction(){
	$("#refush").click(function(){
		window.location.href="list";
	});
	$("#update").click(function(){
		$(".modal-title").html("商铺编辑");
		var id = $("#id").val();
		$.ajax({
			url:"queryById",
			data:{id:id},
			type:"post",
			dataType:"json",
			success:function(data){
				$("#shopName").val(data.shopName);
				$("#shopIntroduce").val(data.shopIntroduce);
				$("#shopType").val(data.shopType);
				$("#id").val(data.id);
				$("#preview").attr("src","../image/view/shop?path="+data.shopImage);
				$("#myModal").modal("show");
			},
			error:function(data){
				swal(data.msg, "", "error");
			}
		});
	});
	$("#save").click(function() {
		var options = {
			url:"save",
			//dataType:'json',
			type : 'post', // get和post两种方式
			clearForm : true, // 表示成功提交后清除所有表单字段值
			resetForm : true,// 表示成功提交后重置所有字段
			beforeSubmit:function(){
				var shopName = $("#shopName").val();
				if(shopName.trim() == ""){
					swal("请输入商铺名称", "", "warning");
					return false;
				}
				var shopType = $("#shopType").val();
				if(shopType.trim() == ""){
					swal("请输入商铺类型", "", "warning");
					return false;
				}
				var image = $("#image").val();
				if(image == ""){
					swal("请选择图片", "", "warning");
					return false;
				}
			},
			success : function(data) {
				if (data.success) {
					$("#myModal").modal('hide');
					swal(data.msg, "", "success");
					refush();
				} else {
					swal(data.msg, "", "error");
				}
			},
			error : function(){
				swal('异常提交', "", "error");
			}
		};
		$("#signupForm").ajaxSubmit(options);
	});
}