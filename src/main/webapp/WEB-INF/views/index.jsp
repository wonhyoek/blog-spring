<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/blog">Blog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/user/login">로그인</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/user/join">회원가입</a>
      </li>
    </ul>
  </div>
</nav>
<br>

<div class="container">
 <div class="card m-2">
   <img class="card-img-top" src="img_avatar1.png" alt="Card image">
   <div class="card-body">
     <h4 class="card-title">제목 부분</h4>
     <p class="card-text">내용 부분</p>
     <a href="#" class="btn btn-primary">상세 보기</a>
   </div>
 </div>
 </div>
<div class="jumbotron text-center" style="margin-bottom:0">
  <p>Created by wh</p>
  <p>010-0000-0000</p>
  <p>충남 아산시 호서로</p>
</div>
</body>
</html>


