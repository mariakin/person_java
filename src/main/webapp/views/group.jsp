<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Group"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Группы</title>
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
                <div class="col-12 border bg-light px-4">
                    <h3>Список групп</h3>
                    <table class="table">
                        <thead>
                            <th scope="col">Код</th>
                            <th scope="col">Наименование</th>
                            <th scope="col">Факультет</th>
                            <th scope="col">Курс</th>
                            <th scope="col">Вид обучения</th>
                            <th scope="col">Редактировать</th>
                            <th scope="col">Удалить</th>
                        </thead>
                        <tbody>
                            <c:forEach var="group" items="${groups}">
                                <tr>
                                    <td>${group.getId()}</td>
                                    <td>${group.getGroupName()}</td>
                                    <td>${group.getFaculty()}</td>
                                    <td>${group.getCourse()}</td>
                                    <td>${group.getEducationType()}</td>
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
                <div class="col-12 border px-4 mt-3">
                    <form method="POST" action="">
                        <h3>Новая группа</h3>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="groupName" class="form-label">Наименование</label>
                                    <input type="text" class="form-control" id="groupName" name="groupName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="faculty" class="form-label">Факультет</label>
                                    <input type="text" class="form-control" id="faculty" name="faculty" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="course" class="form-label">Курс</label>
                                    <select class="form-select" id="course" name="course" required>
                                        <option value="">Выберите курс</option>
                                        <option value="1">1 курс</option>
                                        <option value="2">2 курс</option>
                                        <option value="3">3 курс</option>
                                        <option value="4">4 курс</option>
                                        <option value="5">5 курс</option>
                                        <option value="6">6 курс</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="educationType" class="form-label">Вид обучения</label>
                                    <select class="form-select" id="educationType" name="educationType" required>
                                        <option value="">Выберите вид обучения</option>
                                        <option value="Очная">Очная</option>
                                        <option value="Очно-заочная">Очно-заочная</option>
                                        <option value="Заочная">Заочная</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Добавить группу</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/footer.jsp" />
    </div>
</body>
</html>