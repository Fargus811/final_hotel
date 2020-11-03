<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/footer.css" type="text/css">
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
<div class="footer" >
  <div class="container">
        <div class="row">
            <div class="col-1">
                <div class="footer-name"><fmt:message key="text.footer.hotel"/></div>
                <div class="copyright"><fmt:message key="text.footer.copyright"/></div>
            </div>
            <div class="col-md-auto">
                <div class="block-list">
                    <div class="block-list-element"><fmt:message key="text.footer.terms"/></div>
                    <div class="block-list-element"><fmt:message key="text.footer.media"/></div>
                    <div class="block-list-element"><fmt:message key="text.footer.privacy"/></div>
                </div>
            </div>
            <div class="col-md-auto">
                <div class="block-list">
                    <div class="block-list-element"><fmt:message key="text.footer.services"/></div>
                    <div class="block-list-element"><fmt:message key="text.footer.investor"/></div>
                    <div class="block-list-element"><fmt:message key="text.footer.legalNotes"/></div>
                </div>
            </div>
            <div class="col-md-auto">
                <div class="block-list">
                    <div title="Click here to contact us" class="block-list-element"><fmt:message key="text.footer.helpCenter"/></div>
                    <div class="block-list-element"><fmt:message key="text.footer.jobs"/></div>
                    <div class="block-list-element"><fmt:message key="text.footer.cookie"/></div>
                </div>
            </div>
            <div class="col-md-5"></div>
            <form id = "ruForm" action="${pageContext.request.contextPath}/controller" method = "post" style = "display:none;">
                <input type="hidden" name="command" value="language" />
                <input type="hidden" name="lang" value="ru"/>
            </form>
            <form id = "enForm" action="${pageContext.request.contextPath}/controller" method = "post" style = "display:none;">
                <input type="hidden" name="command" value="language" />
                <input type="hidden" name="lang" value="en"/>
            </form>
        </div>
</div>
</div>
</fmt:bundle>
