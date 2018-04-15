<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>자유게시판</title>

<!-- 부트스트랩 -->
<link href="/Board/resources/bootstrap-dist/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="Board/resources/bootstrap-dist/js/bootstrap.min.js"></script>

<script>
$(function(){
	$('#content').val("${bean.content}");
	$('#realModifyBtn').hide();
});

$(function(){
	$('#deleteBtn').click(function(){
		
		if($('#password').val() != '')
		{
			
			var password = $('#password').val();
			var registerNo = $('#registerNo').val();

			$.ajax({
				url:'/Board/check.do',
				type:'POST',
				data:{
					"password" : password,
					"registerNo" : registerNo
				},
				success:function(response){
					var data=JSON.parse(response);
					
					if(data.result.trim() == 'SUCCESS')
					{
						var result = confirm('정말로 삭제하시겠습니까?');

				        if(result) {
							alert('삭제되었습니다');
							$('#inputForm').attr('action', '/Board/delete.do');
							$('#inputForm').attr('method', 'GET');
					 		$('#inputForm').submit();
				        } 
				        else
							$('#password').val('');
					}
					else if(data.result.trim() == 'FAIL')
					{

						$('#password').val('');
						alert('비밀번호가 틀렸어요');
					}

					

				},
				error:function(){
				}
			});
			
		}
		else
			alert('삭제하려면 비밀번호를 입력하세요');
	})
	
	$('#modifyBtn').click(function(){
		
		if($('#password').val() != '')
		{
				
				var password = $('#password').val();
				var registerNo = $('#registerNo').val();

				$.ajax({
					url:'/Board/check.do',
					type:'POST',
					data:{
						"password" : password,
						"registerNo" : registerNo
					},
					success:function(response){
						var data=JSON.parse(response);
						if(data.result.trim() == 'SUCCESS')
						{
							$('#title').attr('disabled', false);
							$('#content').attr('disabled', false);
							$('#writer').attr('disabled', false);
							$('#password').val('');
							$('#password').attr('placeholder', '비밀번호를 입력하세요');
							
							$('#deleteBtn').hide();
							$('#modifyBtn').hide();
							$('#realModifyBtn').show();
							
							$(function(){

								$('#realModifyBtn').click(function(){
									
									if($('#title').val() == '')
										alert('제목을 입력하세요');
									else if($('#content').val() == '')
										alert('내용을 입력하세요');
									else if($('#writer').val() == '')
										alert('작성자를 입력하세요');
									else if($('#password').val() == '')
										alert('비밀번호를 입력하세요');
									
									else
									{
										$('#inputForm').attr('action', '/Board/modify.do');
										$('#inputForm').attr('method', 'POST');
										$('#inputForm').submit();
										alert('수정이 완료되었습니다');
									}
								
								})
								
								$('#cancelBtn').click(function(){
									var result = confirm('정말로 취소하시겠습니까? 작성한 내용이 사라져요');

							        if(result) {
								 		location.href="/Board/search.do";
							        } 
								})
							})
							//location.href="/Board/modifyPost.jsp";
						}
						else if(data.result.trim() == 'FAIL')
						{
							$('#password').val('');
							alert('비밀번호가 틀렸어요');
						}

					},
					error:function(){
					}
				});
				
		}
		else
			alert('수정하려면 비밀번호를 입력하세요');
	})
	
	
	$('#cancelBtn').click(function(){
 		location.href="/Board/search.do";
	})
})



$(function(){
	$('#main_h1').on(click, function(){
		location.href='/Board/search.do';
	});
})
</script>


</head>
<body>
<div class="col-md-1" ></div>
<h1 id="main_h1">자유게시판 <small>> 게시글보기</small></h1>
<hr width="90%"/>
<br><br>

		<form id="inputForm" class="form-horizontal" action="/delete.do" method="GET">
		<input type="hidden" class="form-control" id="registerNo" name="registerNo" value="${bean.registerNo}">
		
		  <div class="form-group">
		    <label for="date" class="col-md-2 control-label">작성일</label>
		    <div class="col-md-8">
		      <input type="text" class="form-control" id="date" name="date" value="${bean.writeDate}" disabled>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="title" class="col-md-2 control-label">제목</label>
		    <div class="col-md-8">
		      <input type="text" class="form-control" id="title" name="title" value="${bean.title}" disabled>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="content" class="col-md-2 control-label">내용</label>
		    <div class="col-md-8">
	    		<textarea class="form-control" rows="10" id="content" name="content" disabled></textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="writer" class="col-md-2 control-label">작성자</label>
		    <div class="col-md-3">
		      <input type="text" class="form-control" id="writer" name="writer" value="${bean.writer}" disabled>
		    </div>
		    <label for="password" class="col-md-2 control-label">비밀번호</label>
		    <div class="col-md-3">
		      <input type="password" class="form-control" id="password" name="password" placeholder="삭제/수정하려면 비밀번호를 입력하세요">
		    </div>
		  </div>
		  
		  <br><br>
		
		<div class="form-group">
		    <div class="col-md-offset-5 col-md-10">
	 		 	<div class="btn btn-default" id="deleteBtn">삭제</div>
	 		 	<div class="btn btn-default" id="modifyBtn">수정</div>
	 		 	<div class="btn btn-default" id="realModifyBtn">수정</div>
	 		 	<div class="btn btn-default" id="cancelBtn">돌아가기</div>
		    </div>
		  </div>
	</form>

</body>
</html>