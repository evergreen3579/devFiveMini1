Open JDK 다운로드 : https://jdk.java.net/archive/

이클립스 톰캣 8080포트 오류 해결법 (CMD 관리자 권한으로 실행)
netstat -ano | findstr :8080  //8080포트 사용조회
taskkill /f /pid pid번호      //사용중인 PID번호 종료
