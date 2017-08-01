<!DOCTYPE html>

<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ attribute name="header" fragment="true" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.soaint.com/webframework/security-functions/1.0.0" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Instrumentos Archiv&iacute;sticos</title>

    <!-- soaint custom components -->
    <link href="${pageContext.request.contextPath}/resources/soaint-ui/css/style.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/bootstrap.js"></script>

    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/fnReloadAjax.js"></script>

    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/datatables.bootstrap.js"></script>


    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/chosen.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/jquery.bootstrap-growl.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/bootstrap-confirmation.js"></script>
    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/jquery.blockUI.js"></script>

    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/infinityScroll.js"></script>
    <script src="${pageContext.request.contextPath}/resources/soaint-ui/js/vendor/resumable.js"></script>

    <!-- bootstrap custom components -->
    <script src="${pageContext.request.contextPath}/resources/js/custom-ui.js"></script>
    <script src="${pageContext.request.contextPath}/resources/prime-ui/jquery-ui.min.js"></script>

    <!-- bootstrap file input -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap-fileinput/css/fileinput.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/bootstrap-fileinput/js/fileinput.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap-fileinput/js/es.js"
            type="text/javascript"></script>

    <!-- mvc controller -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js-controllers/mvc-controller.js"></script>

    <jsp:invoke fragment="header"/>
</head>

<body>
<header>
    <div class="container">
        <div class="pull-left">
            <a href="${pageContext.request.contextPath}/">
                <div class="logo-ugpp"></div>
            </a>
        </div>
        <div class="pull-right">
            <div class="logo-admindoc"></div>
        </div>
        <div class="clearfix"></div>
    </div>
</header>

<sec:securityChecker roles="PUBLIC">
    <jsp:include page="/fragments/menu.jsp"/>
</sec:securityChecker>

<div class="container">

    <ui:messages/>
    <jsp:doBody/>

</div>

    <jsp:include page="/fragments/footer.jsp"/>

</body>
</html>