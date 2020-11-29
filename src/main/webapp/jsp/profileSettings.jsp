<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User Settings</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/profileSettings.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/base.css" type="text/css">
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="text">
<jsp:include page="/jsp/part/header.jsp"/>
<div class="container">
    <div class="row gutters-sm" style="margin-top: 50px; margin-bottom: 25px">
        <div class="col-md-4 d-none d-md-block">
            <div class="card">
                <div class="card-body">
                    <nav class="nav flex-column nav-pills nav-gap-y-1">
                        <a href="#profile" data-toggle="tab" class="nav-item nav-link has-icon nav-link-faded active">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-user mr-2">
                                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                <circle cx="12" cy="7" r="4"></circle>
                            </svg>
                           <fmt:message key="text.profileSettings.profile"/>
                        </a>
                        <a href="#account" data-toggle="tab" class="nav-item nav-link has-icon nav-link-faded">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-settings mr-2">
                                <circle cx="12" cy="12" r="3"></circle>
                                <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path>
                            </svg>
                            <fmt:message key="text.profileSettings.settings"/>
                        </a>
                        <a href="#security" data-toggle="tab" class="nav-item nav-link has-icon nav-link-faded">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-shield mr-2">
                                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path>
                            </svg>
                            <fmt:message key="text.profileSettings.security"/>
                        </a>
                        <c:if test="${sessionUser.role == 'USER'}">
                        <a href="#billing" data-toggle="tab" class="nav-item nav-link has-icon nav-link-faded">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                 fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                 stroke-linejoin="round" class="feather feather-credit-card mr-2">
                                <rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect>
                                <line x1="1" y1="10" x2="23" y2="10"></line>
                            </svg>
                            <fmt:message key="text.profileSettings.wallet"/>
                        </a>
                        </c:if>
                    </nav>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <div class="card">
                <div class="card-header border-bottom mb-3 d-flex d-md-none">
                    <ul class="nav nav-tabs card-header-tabs nav-gap-x-1" role="tablist">
                        <li class="nav-item">
                            <a href="#profile" data-toggle="tab" class="nav-link has-icon active">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-user">
                                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                    <circle cx="12" cy="7" r="4"></circle>
                                </svg>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#account" data-toggle="tab" class="nav-link has-icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-settings">
                                    <circle cx="12" cy="12" r="3"></circle>
                                    <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path>
                                </svg>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#security" data-toggle="tab" class="nav-link has-icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-shield">
                                    <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"></path>
                                </svg>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#notification" data-toggle="tab" class="nav-link has-icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-bell">
                                    <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
                                    <path d="M13.73 21a2 2 0 0 1-3.46 0"></path>
                                </svg>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#billing" data-toggle="tab" class="nav-link has-icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-credit-card">
                                    <rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect>
                                    <line x1="1" y1="10" x2="23" y2="10"></line>
                                </svg>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="card-body tab-content">
                    <div class="tab-pane active" id="profile">
                        <h6><fmt:message key="text.profileSettings.h1.info"/></h6>
                        <hr>
                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="update_user_info"/>
                            <div class="form-group">
                                <label for="firstName"><fmt:message key="text.registration.firstName"/></label>
                                <input pattern="[A-ZА-Я][a-zа-я\-]{1,32}" title="<fmt:message key="text.profileSettings.placeholder.firstName"/>" class="form-control" name="firstName" id="firstName"
                                       aria-describedby="firstNameHelp"
                                       placeholder="Enter your firstName" value="${user.firstName}">
                                <small id="firstNameHelp" class="form-text text-muted"><fmt:message key="text.profileSettings.infoFirstName"/></small>
                            </div>
                            <hr>
                            <div class="form-group">
                                <label for="lastName"><fmt:message key="text.registration.lastName"/></label>
                                <input pattern="[A-ZА-Я][a-zа-я\-]{1,32}" title="<fmt:message key="text.profileSettings.placeholder.lastName"/>" class="form-control" name="lastName" id="lastName"
                                       aria-describedby="lastNameHelp"
                                       placeholder="Enter your lastName" value="${user.lastName}">
                                <small id="lastNameHelp" class="form-text text-muted"><fmt:message key="text.profileSettings.infoLastName"/></small>
                            </div>
                            <hr>
                            <div class="form-group">
                                <label for="email"><fmt:message key="text.registration.email"/></label>
                                <input type="email" class="form-control" name="email" id="email"
                                       aria-describedby="emailHelp"
                                       placeholder="<fmt:message key="text.registration.email"/>"
                                       value="${user.email}">
                                <small id="emailHelp" class="form-text text-muted"><fmt:message key="text.profileSettings.infoEmail"/></small>
                            </div>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#infoModal"><fmt:message key="text.profileSettings.update"/>
                            </button>
                            <button type="reset" class="btn btn-light"><fmt:message key="text.profileSettings.reset"/></button>
                            <c:if test="${not empty errorInfo}">
                                <div class="alert alert-danger" role="alert" style="margin: 20px"><fmt:message key="text.profileSettings.invalidData"/></div>
                            </c:if>
                            <!-- Modal -->
                            <div class="modal fade" id="infoModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="infoModalLabel"><fmt:message key="text.profileSettings.pleaseConfirm"/></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body" style="margin-bottom: 25px"><fmt:message key="text.profileSettings.confirm"/></div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="text.profileSettings.close"/></button>
                                            <button type="submit" class="btn btn-primary"><fmt:message key="text.profileSettings.save"/></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="account">
                        <h6><fmt:message key="text.profileSettings.h1.infoSettings"/></h6>
                        <hr>
                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="change_account_status"/>
                            <input type="hidden" name="accountStatusId" value="2"/>
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <input type="hidden" name="delete" value="true"/>
                            <div class="form-group">
                                <label class="d-block"><fmt:message key="text.logIn.password.confirm"/></label>
                                <input pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$" title="<fmt:message key="text.registration.passwordError"/>"
                                       class="form-control" id="password" name="password"
                                       placeholder="<fmt:message key="text.profileSettings.enterPassword"/>" required>
                                <br>
                                <label class="d-block text-danger"><fmt:message key="text.profileSettings.deleteAccount"/></label>
                                <p class="text-muted font-size-sm"><fmt:message key="text.profileSettings.deleteAccountInfo"/></p>
                            </div>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#deleteModal"><fmt:message key="text.profileSettings.deleteAccount"/>
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="deleteModalLabel"><fmt:message key="text.profileSettings.pleaseConfirm"/></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body" style="margin-bottom: 25px;"><fmt:message key="text.profileSettings.confirmDeleteAccount"/></div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="text.profileSettings.close"/>
                                            </button>
                                            <button type="submit" class="btn btn-danger"><fmt:message key="text.profileSettings.deleteAccount"/></button>
                                            <c:if test="${not empty error}">
                                                <span class="text-danger"><fmt:message key="text.client.errorPassword"/></span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="security">
                        <h6><fmt:message key="text.profileSettings.h1.security"/></h6>
                        <hr>
                        <form action="${pageContext.request.contextPath}/controller" method="POST" id="updatePassword">
                            <input type="hidden" name="command" value="update_user_password"/>
                            <div class="form-group">
                                <label class="d-block"><fmt:message key="text.profileSettings.changePassword"/></label>
                                <input pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$" type="password" id="oldPassword" name="oldPassword" class="form-control"
                                       placeholder="<fmt:message key="text.profileSettings.enterOldPassword"/>" title="<fmt:message key="text.registration.passwordError"/>"
                                        required>
                                <hr>
                                <input pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$" type="password" id="newPassword" name="newPassword" class="form-control mt-1"
                                       placeholder="<fmt:message key="text.profileSettings.enterNewPassword"/>" title="<fmt:message key="text.registration.passwordError"/>" required>
                                <hr>
                                <input pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,25}$" type="password" id="confirmPassword" name="confirmPassword"
                                       class="form-control mt-1" title="<fmt:message key="text.registration.passwordError"/>"
                                       placeholder="<fmt:message key="text.profileSettings.enterNewPassword"/>" required>
                                <hr>
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#passwordModal"><fmt:message key="text.profileSettings.updatePassword"/>
                                </button>
                            </div>
                            <!-- Modal -->
                            <div class="modal fade" id="passwordModal" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="text.profileSettings.pleaseConfirm"/></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body" style="margin-bottom: 25px">
                                            <fmt:message key="text.profileSettings.confirmChangePassword"/>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="text.profileSettings.close"/>
                                            </button>
                                            <button type="submit" class="btn btn-primary"><fmt:message key="text.profileSettings.save"/></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <hr>
                    </div>
                    <div class="tab-pane" id="billing">
                        <h6><fmt:message key="text.profileSettings.h1.wallet"/></h6>
                        <hr>
                        <div class="form-group">
                            <h3><fmt:message key="text.profileSettings.wallet.balance"/> ${user.balance}</h3>
                            <label class="d-block mb-0"><fmt:message key="text.profileSettings.wallet.payment"/></label>
                            <div class="row" style="margin: 20px">
                                <form action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="add_balance"/>
                                    <input type="hidden" name="amount" value="250">
                                    <button class="btn btn-info" type="submit" style="margin: 20px"><fmt:message key="text.profileSettings.add"/> 250💵
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="add_balance"/>
                                    <input type="hidden" name="amount" value="500">
                                    <button class="btn btn-info" type="submit" style="margin: 20px"><fmt:message key="text.profileSettings.add"/> 500💵
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="add_balance"/>
                                    <input type="hidden" name="amount" value="1000">
                                    <button class="btn btn-info" type="submit" style="margin: 20px"><fmt:message key="text.profileSettings.add"/> 1000💵
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/controller" method="POST">
                                    <input type="hidden" name="command" value="add_balance"/>
                                    <input type="hidden" name="amount" value="2500">
                                    <button class="btn btn-info" type="submit" style="margin: 20px"><fmt:message key="text.profileSettings.add"/> 2500💵
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</fmt:bundle>
</body>
<jsp:include page="/jsp/part/footer.jsp"/>
</html>
