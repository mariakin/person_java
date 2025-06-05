<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Student"%>
<%@ page import="domain.Group"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Студенты</title>
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
                    <h3>Список студентов</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Фамилия</th>
                                <th scope="col">Имя</th>
                                <th scope="col">Отчество</th>
                                <th scope="col">Группа</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.id}</td>
                                    <td>${student.lastName}</td>
                                    <td>${student.firstName}</td>
                                    <td>${student.middleName}</td>
                                    <td>${student.group.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-6 border px-4">
                    <form method="POST" action="">
                        <h3>Редактировать студента</h3>
                        <br><br>
                        <div class="mb-3 row">
                            <label for="idStudent" class="col-sm-3 col-form-label">ID студента</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly
                                    value="${studentEdit.id}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputLastName" class="col-sm-3 col-form-label">Фамилия</label>
                            <div class="col-sm-6">
                                <input type="text" name="lastName" class="form-control"
                                    value="${studentEdit.lastName}" id="inputLastName" required />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputFirstName" class="col-sm-3 col-form-label">Имя</label>
                            <div class="col-sm-6">
                                <input type="text" name="firstName" class="form-control"
                                    value="${studentEdit.firstName}" id="inputFirstName" required />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputMiddleName" class="col-sm-3 col-form-label">Отчество</label>
                            <div class="col-sm-6">
                                <input type="text" name="middleName" class="form-control"
                                    value="${studentEdit.middleName}" id="inputMiddleName" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputBirthDate" class="col-sm-3 col-form-label">Дата рождения</label>
                            <div class="col-sm-6">
                                <input type="date" name="birthDate" class="form-control"
                                    value="${studentEdit.birthDate}" id="inputBirthDate" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputPhone" class="col-sm-3 col-form-label">Телефон</label>
                            <div class="col-sm-6">
                                <input type="tel" name="phone" class="form-control"
                                    value="${studentEdit.phone}" id="inputPhone" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputEmail" class="col-sm-3 col-form-label">Email</label>
                            <div class="col-sm-6">
                                <input type="email" name="email" class="form-control"
                                    value="${studentEdit.email}" id="inputEmail" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="selectGroup" class="col-sm-3 col-form-label">Группа</label>
                            <div class="col-sm-6">
                                <select name="groupId" class="form-control" id="selectGroup" required>
                                    <option value="">-- Выберите группу --</option>
                                    <c:forEach items="${groups}" var="group">
                                        <option value="${group.id}" 
                                            ${studentEdit.group.id == group.id ? 'selected' : ''}>
                                            ${group.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <p>
                            <br><br><br>
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <a href='<c:url value="/student" />' role="button"
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