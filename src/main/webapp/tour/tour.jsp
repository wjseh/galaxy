<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js" integrity="sha256-6XMVI0zB8cRzfZjqKcD01PBsAy3FlDASrlC8SxCpInY=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script>
	
	$(document).ready(function(){
	$('#currentDate').datepicker({
		'showOn': 'focus',
		'dateFormat': 'yymmdd'
	})
		TourStnInfo();
	})
	
	function TourStnInfo(){
		$('#btn2').on('click', function(){
			$.ajax({
				url: '/OPENAPI/TourStnInfo.do',
				type: 'get',
				dataType: 'json',
				success: function(responseText){
					$('focInfo').empty();
					$.each($(responseText).find('item'), function(i, item){
						var tr = '<tr>';
						tr += '<td>' + $(item).find('tm').text() + '</td>';
						tr += '<td>' + $(item).find('courseld').text() + '</td>';
						tr += '<td>' + $(item).find('courseAreaName').text() + '</td>';
						tr += '<td>' + $(item).find('spotAreaName').text() + '</td>';
						tr += '<td>' + $(item).find('courseName').text() + '</td>';
						tr += '<td>' + $(item).find('spotName').text() + '</td>';
						tr += '<td>' + $(item).find('thema').text() + '</td>';
						tr += '<td>' + $(item).find('wd').text() + '</td>';
						tr += '<td>' + $(item).find('ws').text() + '</td>';
						tr += '<td>' + $(item).find('rhm').text() + '</td>';
						tr += '<td>' + $(item).find('pop').text() + '</td>';
						tr += '<td>' + $(item).find('rn').text() + '</td>';
						$('#focInfo').append(tr);
					})
				}
			
			})
		})
	}
</script>
</head>
<body>
	<input type="text" id="currentDate" name="currentDate">
	<div>
		<form id="TourStnInfo">
			<label for="query">
				검색
				<input type="text" name="query" id="query">
			</label>
				<input type="button" value="검색" id="btnTourStnInfo">
		</form>
	</div>

	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>동네예보시각</td>
				<td>지역코스</td>
				<td>지역이름</td>
				<td>지점이름</td>
				<td>코스명</td>
				<td>관광지명</td>
				<td>테마</td>
				<td>풍향</td>
				<td>풍속</td>
				<td>습도</td>
				<td>강수확률</td>
				<td>강수량</td>
			</tr>
		</thead>
		<tbody id="focInfo"></tbody>
	</table>

</body>
</html>