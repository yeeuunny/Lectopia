<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="/Board/resources/bootstrap-dist/js/bootstrap.min.js"></script>

<script>
/*
 * table 내용  
 */
function setPage(pageNum){
	$('#prev').removeClass('disabled');
	$('#next').removeClass('disabled');
	$('#board tbody').hide();
	$('.page_item').removeClass('active');
	
	if(pageNum == 1)
		$('#prev').addClass('disabled');
	
	else if(pageNum == $('#board tbody').length)
		$('#next').addClass('disabled');
	
	/* eq는 0부터 시작 */
	$('#board tbody').eq(pageNum-1).show();
	$('.page_item').eq(pageNum-1).addClass('active');
	
}

function pagination(){
	$('.page_item a').on('click', function(){
		var cur = $(this).html();
		setPage(cur);
	})
	
	$('#prev').on('click', function(){
		var cur = $('.page_item.active a').html();
		setPage(cur - 1);
	})
	$('#next').on('click', function(){
		var cur = $('.page_item.active a').html();
		setPage(Number(cur) + 1);
	})
}
$(function(){
	setPage(1);
	pagination();
	click();
})
 
/*
 * 글쓰기 버튼 클릭 시 글쓰기 화면 전환 
 */
$(function(){
	$('#writeBtn').click(function(){
		location.href="/Board/register.do";
	});
});

/*
 * selectBox 선택 값 ---- 일시 검색창 비활성화
 */
$(function(){
	$("#select").change(function() {
		
		if($('#select option:selected').val() == '전체보기')
			{
				$('#inputSearch').attr('disabled', true);
				$('#inputSearch').val('');
			}
		else
			$('#inputSearch').attr('disabled', false);
	});
	
})


/*
 * 검색 비동기 처리 
 */
$(function(){
	$('#searchBtn').click(function(){
		
		var inputSearch = $('#inputSearch').val();
		var select = $('#select').val();

		if(select != '전체보기' && inputSearch == '' )
			alert('검색어를 입력하세요');
		
		else
		{
		var form = $('#searchForm').serialize();
		$.ajax({
			url:'/Board/search.do',
			type:'GET',
			data:{
				"select" : select,
				"inputSearch" : inputSearch
			},
			success:function(response){
				var data=JSON.parse(response);

				$('#board tbody').remove();
				$('.page_item').remove();

			
				for(var i=0; i < data.length/10; i++){
					$('#next').before('<li class="page_item"><a href="#">' + (i+1) + '</a></li>');
				}

				$.each( data, function( key, value ) {
					
					if(key % 10 == 0)
						$('#board').append('<tbody>');

					$('#board > tbody:last-child').append('<tr><td style="display:none">' + data[key].registerNo 
							+ '</td><td>' + data[key].no + '</td><td>' + data[key].title + '</td><td>'
						+ data[key].writer + '</td><td>' + data[key].writeDate + '</td><td>' + data[key].count + '</td></tr>');
				
					if(key % 10 == 9)
						$('#board').append('</tbody>');
					});
				
				
				setPage(1);
				pagination();
				click()
			},
			error:function(){
				alert('검색결과가 없습니다');
			}
		});
		}
	});

});


function click(){
	$('#board tbody tr').click(function(){
		var num=$(this).find('td:eq(0)').html();
		location.href='/Board/getDetail.do?num='+num;
	})
}


$(function(){
	$('#main_h1').on(click, function(){
		location.hef='/Board/search.do';
	});
})
</script>


</head>
<body>
	<div class="col-md-1"></div>
	<h1 id="main_h1">자유게시판</h1>
	<hr width="90%"/>
	
	<br>
	<!-- 검색 -->
  	<div class="col-md-offset-8">
	<form id="searchForm" class="form-inline">
	  <div class="form-group">
	    <select id="select" name="select" class="form-control">
		 	<option>전체보기</option>
		 	<option>번호</option>
		  	<option>제목</option>
		  	<option>내용</option>
		  	<option>작성자</option>
		  	<option>작성일</option>
		</select>
	  </div>
	  <div class="form-group">
   	 	<input type="text" class="form-control" id="inputSearch" name="inputSearch" maxlength="30" disabled>
	  </div>
	  
		<div class="btn btn-default" id="searchBtn">
	    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
	    검색</div>
	</form>
  	</div>
  	
  	<br>
  	
  	<!-- 테이블 -->
<div class="row">
<div class="col-md-10 col-md-offset-1" >	
	<table id="board" class="table table table-hover">
	  <thead>
	    <tr class="danger">
	      <th style="display:none">번호</th>
	      <th>번호</th>
	      <th width="50%">제목</th>
	      <th width="15%">작성자</th>
	      <th>작성일</th>
	      <th width="5%">조회수</th>
	    </tr>
	    
	    <c:forEach var="item" items="${requestScope.data}" varStatus="status">
	    	<c:if test="${status.count % 10 eq 1}">
	    		<tbody>
	    	</c:if>
	    	<tr>
	    		<td hidden>${item.registerNo}</td>
	    		<td>${status.count}</td>
	    		<td>${item.title}</td>
	    		<td>${item.writer}</td>
	    		<td>${item.writeDate}</td>
	    		<td>${item.count}</td>
	    	</tr>
	    	<c:if test="${status.count % 10 eq 0}">
	    		</tbody>
	    	</c:if>
    	</c:forEach>
	  </thead>
	</table>
</div>
</div>
<br>
<div class="col-md-offset-5" >
	<nav id="page_nav">
	  <ul class="pagination" id="page_ul">
	    <li>
	      <a id="prev" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    
	    <c:forEach var="item" items="${requestScope.data}" varStatus="status" step="10">
	    	<li class="page_item"><a href="#">${status.count}</a></li>
	    </c:forEach>
	    <li>
	      <a id="next" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	  </ul>
	</nav>
</div>

<!-- 글쓰기 버튼 -->
<div class="col-md-10" ></div>
<button class="btn btn-default" id="writeBtn">
<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
글쓰기</button>
<br><br>
</body>
</html>