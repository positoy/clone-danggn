# 클론당근

당근마켓을 따라 만들어봤어요.

[여기에서 확인할 수 있어요.](http://ec2-15-164-228-162.ap-northeast-2.compute.amazonaws.com:8080/goods)

## What is it?

당근마켓의 레이아웃을 토대로 글쓰기/조회하기 기능을 구현해봤어요.

화면 구성을 위해, HTML, CSS, Javascript 를 사용했고, 추가로 Spring Boot의 템플릿 엔진인 Thymeleaf 를 적용했습니다.

Spring Boot 와 H2 인메모리 데이터베이스를 활용하고 JPA 를 적용했습니다.

![image](https://user-images.githubusercontent.com/7664099/87956139-a143fe80-cae9-11ea-92bd-fbc510f96f59.png)

## 기능

1. 게시물 조회
2. 네이버 아이디로 OAuth 로그인 (글쓰기 메뉴를 누르면 로그인 화면으로 이동)
3. 글쓰기 (이미지 첨부 가능)
4. 나의당근 (네이버 프로필 연동)

## 설치

다음 명령으로 서버에서 서비스로 등록하면, 재부팅 할 때마다 자동으로 시작할 수 있어요.

프로젝트 복사하기 *{ubuntu} 계정

    cd /home/{ubuntu}/
    git clone https://github.com/positoy/clone-danggn.git
    

서비스파일 만들기

    sudo vim /usr/lib/systemd/system/spring.service
    sudo systemctl start spring

파일내용

    [Unit]
    Description=Spring Service
    After=syslog.target
    
    [Service]
    User={ubuntu}
    ExecStart=/home/{ubuntu}/clone-danggn/autostart
    
    [Install]
    WantedBy=multi-user.target

