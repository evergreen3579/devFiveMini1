Open JDK 다운로드 : https://jdk.java.net/archive/
<br>
이클립스 톰캣 8080포트 오류 해결법 (CMD 관리자 권한으로 실행)<br>
netstat -ano | findstr :8080  //8080포트 사용조회<br>
taskkill /f /pid pid번호      //사용중인 PID번호 종료<br>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
<center>
<h1>로그인</h1>
<img src="image/korean.png" width=100" height=80">
<hr>
<form action="#" method="post">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange">아이디</td>
			<td><input type="text" name="id"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">비밀번호</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="로그인"/>
			</td>
		</tr>	
	</table>
</form>
<br>
<a href="insertUser.html">회원 가입</a>
<hr>
</center>
</body>
</html>
