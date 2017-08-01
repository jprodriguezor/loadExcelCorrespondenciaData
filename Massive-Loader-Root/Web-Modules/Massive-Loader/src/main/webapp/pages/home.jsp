<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<ui:composition>
    <jsp:attribute name="header">
        <script type="text/javascript" src="/Document-Manager/resources/js-controllers/home-controller.js"></script>
    </jsp:attribute>

    <jsp:body>

        <div class="ng-scope">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="ng-scope"> ¡Bienvenido!</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="text-justify ng-scope">
                        <p>
                            Los Instrumentos Archivísticos son herramientas con propósitos específicos, que tienen por objeto
                            apoyar el adecuado desarrollo e implementación de la gestión documental y la función archivística.
                        </p>

                        <p>
                            SOAINT ha desarrollado una solución integral para la gestión documental de su empresa que le facilitará
                            el gobierno documental a través de las siguientes funcionalidades:
                            <br/>
                            <li>Organigrama</li>
                            <li>Cuadro de Clasificación Documental</li>
                            <li>Tabla de Retención Documental</li>
                            <li>Banco Terminológico</li>
                            <li>Reportes</li>
                            <li>Gestión de versiones</li>
                            <li>Integración con LDAP</li>
                            <li>Integración con ECM</li>
                            <li>Control de acceso</li>
                            <br/>
                        </p>
                    </div>
                </div>

                <div class="col-md-6">
                    <img class="img-responsive"
                         src="/Document-Manager/resources/soaint-ui/img/document-management-software.jpg"
                         style="margin:auto"/>
                </div>
            </div>

        </div>
    </jsp:body>
</ui:composition>