<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Student"%>
<%@ page import="domain.Group"%>
<%
Group g1 = new Group(1L, "ИВТ-101", "Информатика и вычислительная техника", 1, "Очная");
Group g2 = new Group(2L, "ПИ-202", "Прикладная информатика", 2, "Очно-заочная");
Group g3 = new Group(3L, "ИБ-301", "Информационная безопасность", 3, "Очная");
Group g4 = new Group(4L, "ФИ-402", "Фундаментальная информатика", 4, "Заочная");
Group[] groups = new Group[]{g1, g2, g3, g4};
pageContext.setAttribute("groups", groups);

Student s1 = new Student(1L, "Иванов", "Иван", "Иванович", "2000-05-15", "+79123456789", "ivanov@example.com", "ИВТ-101");
Student s2 = new Student(2L, "Петров", "Петр", "Петрович", "2001-03-22", "+79234567890", "petrov@example.com", "ПИ-202");
Student s3 = new Student(3L, "Сидорова", "Мария", "Сергеевна", "1999-11-30", "+79345678901", "sidorova@example.com", "ИБ-301");
Student s4 = new Student(4L, "Кузнецов", "Алексей", "Дмитриевич", "2000-07-18", "+79456789012", "kuznetsov@example.com", "ФИ-402");

Student[] students = new Student[]{s1, s2, s3, s4};
pageContext.setAttribute("students", students);
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Студенты</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script defer src="js/jquery.min.js"></script>
    <script defer src="js/bootstrap.min.js"></script>
    <style>
        .table-responsive {
            overflow-x: auto;
        }
        .table td, .table th {
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <jsp:include page="/views/header.jsp" />
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h3 class="my-4">Список студентов</h3>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Код</th>
                                    <th scope="col">Фамилия</th>
                                    <th scope="col">Имя</th>
                                    <th scope="col">Отчество</th>
                                    <th scope="col">Дата рождения</th>
                                    <th scope="col">Телефон</th>
                                    <th scope="col">E-mail</th>
                                    <th scope="col">Группа</th>
                                    <th scope="col">Действия</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="student" items="${students}">
                                    <tr>
                                        <td>${student.getId()}</td>
                                        <td>${student.getLastName()}</td>
                                        <td>${student.getFirstName()}</td>
                                        <td>${student.getMiddleName()}</td>
                                        <td>${student.getBirthDate()}</td>
                                        <td>${student.getPhone()}</td>
                                        <td>${student.getEmail()}</td>
                                        <td>${student.getGroup()}</td>
                                        <td nowrap>
                                            <a href="#" role="button" class="btn btn-sm btn-outline-primary mr-1">
                                                <img alt="Редактировать" src="images/icon-edit.png" width="16">
                                            </a>
                                            <a href="#" role="button" class="btn btn-sm btn-outline-danger">
                                                <img alt="Удалить" src="images/icon-delete.png" width="16">
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
            <div class="row mt-4">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div class="card-header">
                            <h4>Добавить нового студента</h4>
                        </div>
                        <div class="card-body">
                            <form method="POST" action="">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="lastName">Фамилия</label>
                                            <input type="text" class="form-control" id="lastName" name="lastName" required>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="firstName">Имя</label>
                                            <input type="text" class="form-control" id="firstName" name="firstName" required>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="middleName">Отчество</label>
                                            <input type="text" class="form-control" id="middleName" name="middleName">
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row mt-3">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="birthDate">Дата рождения</label>
                                            <input type="date" class="form-control" id="birthDate" name="birthDate" required>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="phone">Телефон</label>
                                            <input type="tel" class="form-control" id="phone" name="phone" required>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for="email">E-mail</label>
                                            <input type="email" class="form-control" id="email" name="email" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row mt-3">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label for="group">Группа</label>
                                            <select class="form-control" id="group" name="group" required>
                                                <option value="">Выберите группу</option>
                                                <c:forEach var="group" items="${groups}">
                                                    <option value="${group.getGroupName()}">
                                                        ${group.getGroupName()}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="row mt-3">
                                    <div class="col-md-12">
                                        <button type="submit" class="btn btn-primary">Добавить студента</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/views/footer.jsp" />
    </div>
</body>
</html>