<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ListTextSection" %>
<%@ page import="com.urise.webapp.model.Organization" %>
<%@ page import="com.urise.webapp.model.Period" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--форма редактирования--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Изменение резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
<%--        поле имени--%>
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
<%--        цикл по контактам тип из ContactType = PHONE, SKYPE и т. д. --%>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=40 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
<%--        цикл по секциям, тип из SectionType = OBJECTIVE, PERSONAL и т. д. --%>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd>
                    <c:choose>
<%--                отображение позиция и личные качества    --%>
                        <c:when test="${type.name()==SectionType.OBJECTIVE || type.name()==SectionType.PERSONAL}">
                            <input type="text" name="${type.name()}" size=150
                                   value="${resume.getSection(type.name()).sections}">
                        </c:when>
                    </c:choose>

<%--                форма достижения и квалификация    --%>
                    <c:choose>
                        <c:when test="${type.name()==SectionType.ACHIEVEMENT || type.name()==SectionType.QUALIFICATIONS}">
                            <c:forEach var="organization" items="${resume.getSection(type.name()).sections}">
                                <input type="text" name="${type.name()}" size=150
                                       value="${organization}">
                                <br>
                            </c:forEach>

                        </c:when>
                    </c:choose>

                    <c:choose>
                        <c:when test="${type.name()==SectionType.EXPERIENCE || type.name()==SectionType.EDUCATION}">
                            <c:forEach var="organization" items="${resume.getSection(type.name()).sections}">
                                <dl>
                                    <dt>Название организации</dt>
                                    <dd><input type="text" name="${type.name()}" size=150
                                               value="${organization.name}">
                                        <br>
                                    </dd>
                                    <dt>Вебсайт</dt>
                                    <dd><input type="text" name="${type.name()}" size=150
                                               value="${organization.website}">
                                        <br>
                                    </dd>
                                    <dt>Периоды</dt>
                                    <dd><c:forEach var="period" items="${organization.getPeriods()}">
                                            <input type="text" name="" size=150
                                                   value="${period.startDate}">
                                            <br>
                                            <input type="text" name="" size=150
                                                   value="${period.endDate}">
                                            <br>
                                            <input type="text" name="" size=150
                                                   value="${period.title}">
                                            <br>
                                            <input type="text" name="" size=150
                                                   value="${period.description}">
                                            <br>
                                        </c:forEach>
                                    </dd>
                                </dl>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </dd>

            </dl>
        </c:forEach>

        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset">Сбросить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
