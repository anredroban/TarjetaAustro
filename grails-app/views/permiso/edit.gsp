<%@ page import="com.pw.security.Permiso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'permiso.label', default: 'Permiso')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-permiso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav sombra" role="navigation">
			<ul>				
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div><br>
		<div id="edit-permiso" class="content scaffold-edit" role="main">
<%--			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>--%>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${permisoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${permisoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:permisoInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${permisoInstance?.version}" />
				<div class="row">
                    <div class="col-lg-12 col-md-6">
                        <div class="panel panel-border-blue-u">
                            <div class="panel-heading panel-body-blue-u">
                                <h3 class="panel-title" style="color:white"><i class="fa fa-star fa-fw"></i> Editar Permiso</h3>								
                            </div>
                            <div class="panel-body">
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				</div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
