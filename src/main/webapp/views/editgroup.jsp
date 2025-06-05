<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Group"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Группы</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- jQuery -->
    <script defer src="js/jquery-3.6.4.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <jsp:include page="/views/header.jsp" />
        <div class="container-fluid">
            <div class="row justify-content-start">
                <div class="col-6 border bg-light px-4">
                    <h3>Список групп</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Код</th>
                                <th scope="col">Наименование</th>
                                <th scope="col">Факультет</th>
                                <th scope="col">Курс</th>
                                <th scope="col">Вид обучения</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="group" items="${groups}">
                                <tr>
                                    <td>${group.id}</td>
                                    <td>${group.name}</td>
                                    <td>${group.faculty}</td>
                                    <td>${group.course}</td>
                                    <td>${group.educationType}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-6 border px-4">
                    <form method="POST" action="">
                        <h3>Редактировать группу</h3>
                        <br><br>
                        <div class="mb-3 row">
                            <label for="idGroup" class="col-sm-3 col-form-label">Код группы</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly
                                    value="${groupEdit.id}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputName" class="col-sm-3 col-form-label">Наименование</label>
                            <div class="col-sm-6">
                                <input type="text" name="name" class="form-control"
                                    value="${groupEdit.name}" id="inputName" required />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputFaculty" class="col-sm-3 col-form-label">Факультет</label>
                            <div class="col-sm-6">
                                <input type="text" name="faculty" class="form-control"
                                    value="${groupEdit.faculty}" id="inputFaculty" required />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputCourse" class="col-sm-3 col-form-label">Курс</label>
                            <div class="col-sm-6">
                                <input type="number" name="course" class="form-control"
                                    value="${groupEdit.course}" id="inputCourse" required min="1" max="6" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="selectEducationType" class="col-sm-3 col-form-label">Вид обучения</label>
                            <div class="col-sm-6">
                                <select name="educationType" class="form-control" id="selectEducationType" required>
                                    <option value="">-- Выберите вид --</option>
                                    <option value="Очное" ${groupEdit.educationType == 'Очное' ? 'selected' : ''}>Очное</option>
                                    <option value="Заочное" ${groupEdit.educationType == 'Заочное' ? 'selected' : ''}>Заочное</option>
                                    <option value="Вечернее" ${groupEdit.educationType == 'Вечернее' ? 'selected' : ''}>Вечернее</option>
                                </select>
                            </div>
                        </div>
                        <p>
                            <br><br><br>
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <a href='<c:url value="/group" />' role="button"
                                class="btn btn-secondary">Отменить</a>
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