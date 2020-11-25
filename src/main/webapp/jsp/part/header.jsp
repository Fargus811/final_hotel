<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/header.css" type="text/css">
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
    <div class='header' style="z-index:2">
        <div><a class='site-name' href="${pageContext.request.contextPath}/index.jsp"><fmt:message
                key="text.hotel"/></a></div>
        <div class='button'><a style="color: white" href="${pageContext.request.contextPath}/index.jsp"><fmt:message
                key="text.catalog"/></a></div>
        <a class='button' href="partnership"><fmt:message key="text.partnership"/></a>
        <div class="lang-select">
            <select id="lang-select" cur-lang="${locale}">
                <c:choose>
                    <c:when test="${locale == 'en' }">
                        <option locale="en">🇺🇸EN</option>
                        <option locale="ru">🇷🇺RU</option>
                    </c:when>
                    <c:otherwise>
                        <option locale="ru">🇷🇺RU</option>
                        <option locale="en">🇺🇸EN</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
        <c:choose>
            <c:when test="${empty sessionScope.sessionUser}">
                <div class="user-block unauthorized">
                    <a class='button auth primary'
                       href="${pageContext.request.contextPath}/controller?command=pass_to_registration"><fmt:message
                            key="text.registration.title"/></a>
                    <a class='button auth secondary'
                       href="${pageContext.request.contextPath}/controller?command=pass_to_login"><fmt:message
                            key="text.logIn.title"/></a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="user-block authorized">
                    <div class="username">${sessionUser.firstName}</div>
                    <img src="/resources/images/profile.jpg" alt="Profile-Img"
                         style="float: right;width: 60px; margin-top: 5px;">
                    <div class='userbox'>
                        <div class='personal-menu-button'><a style="color: white"
                                                             href="${pageContext.servletContext.contextPath}/controller?command=show_my_profile"><fmt:message
                                key="text.client.profile"/></a>
                        </div>
                        <c:if test="${sessionUser.role == 'USER'}">
                        <div class='personal-menu-button'><a style="color: white"
                                                             href="${pageContext.servletContext.contextPath}/controller?command=show_user_bookings"><fmt:message
                                key="text.client.bookings"/></a></div>
                         </c:if>
                        <c:if test="${sessionUser.role == 'ADMIN'}">
                            <div class='personal-menu-button'><a style="color: white"
                                                                 href="${pageContext.servletContext.contextPath}/controller?command=show_all_bookings"><fmt:message
                                    key="text.client.bookings"/></a></div>
                        </c:if>
                        <div class='personal-menu-button'><a style="color: white"
                                                             href="${pageContext.servletContext.contextPath}/controller?command=logout"><fmt:message
                                key="text.client.logout"/></a>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <form id="ruForm" action="${pageContext.request.contextPath}/controller" method="post" style="display:none;">
        <input type="hidden" name="command" value="language"/>
        <input type="hidden" name="lang" value="ru"/>
    </form>
    <form id="enForm" action="${pageContext.request.contextPath}/controller" method="post" style="display:none;">
        <input type="hidden" name="command" value="language"/>
        <input type="hidden" name="lang" value="en"/>
    </form>
    </div>
    <div class="header-correction"></div>
    <script>
        document.getElementById("lang-select").addEventListener('change', function () {
            let langSelect = document.getElementById("lang-select")
            let locale = langSelect.options[langSelect.selectedIndex].getAttribute('locale');
            document.getElementById(locale + 'Form').submit()
        })
    </script>
</fmt:bundle>
