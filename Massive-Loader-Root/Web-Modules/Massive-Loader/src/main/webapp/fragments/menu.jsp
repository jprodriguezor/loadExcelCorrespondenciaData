<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.soaint.com/webframework/security-functions/1.0.0" %>

<div class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">

                <li class="menu-item dropdown">
                    <a href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> Home </a>
                </li>

                <sec:securityChecker roles="GG-INT-GD-DOCMAGER-ADMIN">
                    <li class="menu-item dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-wrench"></i>
                            Administraci&oacute;n
                            <b class="caret"></b></a>


                        <ul class="dropdown-menu" style="z-index: 2147483647;">

                            <li>
                                <a href="${pageContext.request.contextPath}/organigrama"><i class="fa fa-sitemap"></i>
                                    Organigrama Administrativo</a>
                            </li>

                            <li class="divider"></li>

                            <li>
                                <a href="${pageContext.request.contextPath}/series"><i class="fa fa-th-list"></i> Series</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/subseries"><i class="fa fa-list"></i>
                                    Subseries</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/tipologiasdoc"><i
                                        class="glyphicon glyphicon-list-alt"></i> Tipos documentales</a>
                            </li>
                            <li>
                                <a href="/Document-Manager/asociacion"><i class="fa fa-bars"></i> Asociaci&oacute;n de
                                    Tipos
                                    documentales a Series/Subseries</a>
                            </li>
                            <li class="divider"></li>

                            <li class="menu-item ">
                                <a href="${pageContext.request.contextPath}/retencionDisposicion"><i
                                        class="glyphicon glyphicon-time"></i> Retenci&oacute;n Documental y Disposici&oacute;n
                                    Final</a>
                            </li>
                            <li class="divider"></li>

                            <li>
                                <a href="${pageContext.request.contextPath}/ccd"><i class="fa fa-th"></i> Cuadro de
                                    Clasificaci&oacute;n Documental</a>
                            </li>
                            <li class="divider"></li>

                            <li class="menu-item ">
                                <a href="${pageContext.request.contextPath}/trd"><i class="fa fa-indent"></i> Generar
                                    TRD
                                    por Oficina Productora</a>
                            </li>
                            <li class="divider"></li>

                            <li class="menu-item ">
                                <a href="${pageContext.request.contextPath}/trdEcm"><i class="glyphicon glyphicon-indent-left">
                                </i> Generar Estructura ECM TRD</a>
                            </li>
                            <li class="divider"></li>

                            <li class="menu-item ">
                                <a href="${pageContext.request.contextPath}/relEquivalencia"><i
                                        class="glyphicon glyphicon-transfer"></i> Relaci&oacute;n de Equivalencia</a>
                            </li>

                        </ul>

                    </li>
                </sec:securityChecker>

                <sec:securityChecker roles="GG-INT-GD-DOCMAGER-ADMIN,GG-INT-GD-DOGMANAGER-CONSULTA">
                    <li>
                        <a style="font-size:14px" class="dropdown-toggle" data-toggle="dropdown" href="#"><i
                                class="fa fa-eye"></i> Visualizaci&oacute;n <b class="caret"></b></a>

                        <ul class="dropdown-menu" style="z-index: 2147483647;">


                            <li><a href="${pageContext.request.contextPath}/visualOrganigrama"><i
                                    class="fa fa-sitemap"></i>
                                Organigrama</a>
                            <li><a href="${pageContext.request.contextPath}/versionCcd"><i class="fa fa-th"></i> Cuadro
                                de
                                Clasificaci&oacute;n Documental</a></li>
                            <li><a href="${pageContext.request.contextPath}/versionTrd"><i
                                    class="fa fa-indent"></i> Tabla de Retenci&oacute;n Documental</a>

                        </ul>

                    </li>
                </sec:securityChecker>
                <sec:securityChecker roles="GG-INT-GD-DOCMAGER-ADMIN">
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-cogs"></i>
                            Configuraci&oacute;n
                            <b class="caret"></b></a>


                        <ul class="dropdown-menu" style="z-index: 2147483647;">

                            <li>
                                <a href="/Document-Manager/cargaMasiva"><i class="fa fa-cog"></i> Administraci&oacute;n
                                    Carga Masiva</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="/Document-Manager/disposicionFinal"><i class="fa fa-paperclip"></i> Disposici&oacute;nes
                                    Finales</a>
                            </li>
                            <li>
                                <a href="/Document-Manager/motivosDoc"><i class="fa fa-paperclip"></i> Motivos de creaci&oacute;n</a>
                            </li>

                        </ul>

                    </li>
                </sec:securityChecker>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a id="login" href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>
                        <input type="text" name="version" class="form-control hidden" id="loginame"
                               disabled value="${pageContext.request.userPrincipal.name}">


                        <b class="caret"></b></a>

                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/security/processLogout">
                            <i class="fa fa-sign-out"></i> Cerrar sesi&oacute;n</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>