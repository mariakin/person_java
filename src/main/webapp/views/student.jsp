<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Student"%>
<%@ page import="domain.Group"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Студенты</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script defer src="js/jquery-3.6.4.js"></script>
    <script defer src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <jsp:include page="/views/header.jsp" />
        <div class="container-fluid">
            <div class="row justify-content-start">
                <div class="col-8 border bg-light px-4">
                    <h3>Список студентов</h3>
                    <table class="table">
                        <thead>
                            <th scope="col">Код</th>
                            <th scope="col">Фамилия</th>
                            <th scope="col">Имя</th>
                            <th scope="col">Отчество</th>
                            <th scope="col">Дата рождения</th>
                            <th scope="col">Телефон</th>
                            <th scope="col">E-mail</th>
                            <th scope="col">Группа</th>
                            <th scope="col">Редактировать</th>
                            <th scope="col">Удалить</th>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.getId()}</td>
                                    <td>${student.getLastName()}</td>
                                    <td>${student.getFirstName()}</td>
                                    <td>${student.getMiddleName() != null ? student.getMiddleName() : '-'}</td>
                                    <td>${student.getBirthDate()}</td>
                                    <td>${student.getPhone()}</td>
                                    <td>${student.getEmail()}</td>
                                    <td>${student.getGroup().getName()}</td>
                                    <td width="20">
                                        <a href="#" role="button" class="btn btn-outline-primary">
                                            <img alt="Редактировать" src="images/edit.png" width="20">
                                        </a>
                                    </td>
                                    <td width="20">
                                        <a href="#" role="button" class="btn btn-outline-primary">
                                            <img alt="Удалить" src="images/delete.png" width="20">
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <div class="col-4 border px-4">
                    <form method="POST" action="">
                        <h3>Новый студент</h3>
                        <br>
                        <div class="mb-3 row">
                            <label for="lastName" class="col-sm-3 col-form-label">Фамилия</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="lastName" name="lastName" maxlength="50" required />
                            </div>
                        </div>
                        
                        <div class="mb-3 row">
                            <label for="firstName" class="col-sm-3 col-form-label">Имя</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="firstName" name="firstName" maxlength="50" required />
                            </div>
                        </div>
                        
                        <div class="mb-3 row">
                            <label for="middleName" class="col-sm-3 col-form-label">Отчество</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="middleName" name="middleName" maxlength="50" />
                            </div>
                        </div>
                        
                        <div class="mb-3 row">
                            <label for="birthDate" class="col-sm-3 col-form-label">Дата рождения</label>
                            <div class="col-sm-7">
                                <input type="date" class="form-control" id="birthDate" name="birthDate" required />
                            </div>
                        </div>
                        
                        <div class="mb-3 row">
                            <label for="phone" class="col-sm-3 col-form-label">Телефон</label>
                            <div class="col-sm-7">
                                <input type="tel" class="form-control" id="phone" name="phone" maxlength="20" required />
                            </div>
                        </div>
                        
                        <div class="mb-3 row">
                            <label for="email" class="col-sm-3 col-form-label">E-mail</label>
                            <div class="col-sm-7">
                                <input type="email" class="form-control" id="email" name="email" maxlength="100" required />
                            </div>
                        </div>
                        
                        <div class="mb-3 row">
                            <label for="id" class="col-sm-3 col-form-label">Код</label>
                            <div class="col-sm-7">
                                <input type="number" class="form-control" id="id" name="id" />
                            </div>
                        </div>
                        
                        <div class="mb-3 row">
                            <label for="groups" class="col-sm-3 col-form-label">Группа</label>
                            <div class="col-sm-7">
                                <select name="groups" class="form-control" required>
                                    <option value="">Выберите группу</option>
                                    <c:forEach var="group" items="${groups}">
                                        <option value="${group.id}">${group.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        
                        <p><br>
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </p>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/footer.jsp" />
    </div>
</body>
</html>