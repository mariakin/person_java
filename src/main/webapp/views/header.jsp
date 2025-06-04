<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!-- Настройка viewport -->
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<!-- Header -->
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<!-- jQuery -->
<script defer src="js/jquery-3.6.4.js"></script>
<!-- Bootstrap JS + Popper JS -->

<script defer src="js/bootstrap.bundle.min.js"></script>

<a href="/persons/">
<img alt="Логотип" id="top-image" src="images/main.png" width="50">
</a>

<nav class="navbar navbar-light bg-primary">
 <div class="container-fluid">
 <a class="navbar-brand" href="#">
 <img src="images/person.png" alt="" width="80"
height="80" >
 </a>
 <h2>Группы</h2>
 </div>
</nav>
<button type="button" class="btn btn-primary" data-toggle="popover"
title="Сообщение" data-content="Ура, Bootstrap 5 работает">
Поднеси ко мне курсор
</button>
<!-- /Header -->
</body>
</html>