<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Group"%>

<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Группы</title>
<head>
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
                <div class="col-4 border px-4">
                    <form method="POST" action="">
                        <h3>Новая группа</h3>
                        <div class="mb-3">
                            <label for="inputGroupName" class="col-sm-3 col-form-label">Наименование</label>
                            <div class="col-sm-6">
                                <input type="text" name="groupName" class="form-control" id="inputGroupName" maxlength="50" required />
                            </div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="inputFaculty" class="col-sm-3 col-form-label">Факультет</label>
                            <div class="col-sm-6">
                                <input type="text" name="faculty" class="form-control" id="inputFaculty" maxlength="100" required />
                            </div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="inputCourse" class="col-sm-3 col-form-label">Курс</label>
                            <div class="col-sm-6">
                                <select name="course" class="form-control" id="inputCourse" required>
                                    <option value="">Выберите курс</option>
                                    <option value="1">1 курс</option>
                                    <option value="2">2 курс</option>
                                    <option value="3">3 курс</option>
                                    <option value="4">4 курс</option>
                                    <option value="5">5 курс</option>
                                    <option value="6">6 курс</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="inputEducationType" class="col-sm-3 col-form-label">Вид обучения</label>
                            <div class="col-sm-6">
                                <select name="educationType" class="form-control" id="inputEducationType" required>
                                    <option value="">Выберите вид обучения</option>
                                    <option value="Очная">Очная</option>
                                    <option value="Очно-заочная">Очно-заочная</option>
                                    <option value="Заочная">Заочная</option>
                                </select>
                            </div>
                        </div>
                        
                        <p>
                            <br><br><br>
                            <button type="submit" class="btn btn-primary">Добавить</button>
                            <br>
                        </p>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/footer.jsp" />
    </div>
</body>
</html>