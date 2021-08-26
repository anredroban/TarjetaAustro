<%@ page import="utilitarios.Util"%>

<div id="sidebar-links">
	<div class="menu-header">
		<i class="fa fa-fw fa-bars fa-lg"></i>
	</div>
	<ul class="nav">
		<li
			<g:if test="${actionName == "dashboard"}">
				class="nav-active"
			</g:if>>
			<a href="${createLink(uri: '/')}">
				<i class="fa fa-fw fa-dashboard"></i><span class="menu-name">&nbsp;Dashboard</span>
			</a>
		</li>

		<g:if test="${Util.checkAccess(session.user.usuario, '/permiso/index') || Util.checkAccess(session.user.usuario, '/rol/index') || Util.checkAccess(session.user.usuario, '/usuario/index')}">
			<li>
				<a class="parent-sub" data-toggle="collapse" data-target="#sub-security">
					<i class="fa fa-fw fa-lock sidebar-icon"></i><span class="menu-name">&nbsp;Seguridad</span>
					<i class="fa fa-fw fa-caret-down"></i>
				</a>
				<ul id="sub-security" class="collapse nav-sub">
					<g:if test="${Util.checkAccess(session.user.usuario, '/permiso/index')}">
						<li class="nav-security
							<g:if test="${controllerName == "permiso"}">
                            	nav-active subnav-expanded
                        	</g:if>
                        ">
							<a href="${createLink(uri: '/permiso/')}">
								<i class="fa fa-fw fa-star"></i><span class="menu-name">&nbsp;Permisos</span>
							</a>
						</li>
					</g:if>
					<g:if test="${Util.checkAccess(session.user.usuario, '/rol/index')}">
						<li class="nav-security
							<g:if test="${controllerName == "rol"}">
                            	nav-active subnav-expanded
	                        </g:if>
                        ">
							<a href="${createLink(uri: '/rol/')}">
								<i class="fa fa-fw fa-th"></i><span class="menu-name">&nbsp;Roles</span>
							</a>
						</li>
					</g:if>
					<g:if test="${Util.checkAccess(session.user.usuario, '/usuario/index')}">
						<li class="nav-security
							<g:if test="${controllerName == "usuario" && actionName != "dashboard"}">
	                            nav-active subnav-expanded
	                        </g:if>
                        ">
							<a href="${createLink(uri: '/usuario/')}">
								<i class="fa fa-fw fa-user sidebar-icon"></i><span class="menu-name">&nbsp;Usuarios</span>
							</a>
						</li>
					</g:if>
				</ul>
				<a class="parent-sub" data-toggle="collapse" data-target="#sub-campaign">
					<i class="fa fa-fw fa-list-alt sidebar-icon"></i><span class="menu-name">&nbsp;Base</span>
					<i class="fa fa-fw fa-caret-down"></i>
				</a>
				<ul id="sub-campaign" class="collapse nav-sub">
					<li class="nav-campaign
						<g:if test="${controllerName == "gestion" && actionName == "cargarBase"}">
						nav-active subnav-expanded
					</g:if>
					">
						<a href="${createLink(uri: '/gestion/cargarBase')}">
							<i class="fa fa-fw fa-comments-o"></i><span class="menu-name">&nbsp;Cargar Base</span>
						</a>
					</li>
					<li class="nav-campaign
						<g:if test="${controllerName == "cargarNovedades"}">
						nav-active subnav-expanded
					</g:if>
					">
						<a href="${createLink(uri: '/cargarNovedades/cargarBase')}">
							<i class="fa fa-fw fa-comments-o"></i><span class="menu-name">&nbsp;Cargar Novedades</span>
						</a>
					</li>
					<li class="nav-campaign
						<g:if test="${controllerName == "gestion" && actionName == "retirarBase"}">
							nav-active subnav-expanded
						</g:if>
					">
						<a href="${createLink(uri: '/gestion/retirarBase')}">
							<i class="fa fa-fw fa-comments-o"></i><span class="menu-name">&nbsp;Retirar Base</span>
						</a>
					</li>
					<li class="nav-campaign
						<g:if test="${controllerName == "gestion" && actionName == "retirarBaseNovedades"}">
						nav-active subnav-expanded
					</g:if>
					">
						<a href="${createLink(uri: '/gestion/retirarBaseNovedades')}">
							<i class="fa fa-fw fa-comments-o"></i><span class="menu-name">&nbsp;Retirar Novedades</span>
						</a>
					</li>
					<li class="nav-campaign
						<g:if test="${controllerName == "asignarBaseNew" && actionName == "index"}">
							nav-active subnav-expanded
						</g:if>
					">
						<a href="${createLink(uri: '/asignarBaseNew/index')}">
							<i class="fa fa-fw fa-upload"></i><span class="menu-name">&nbsp;Asignar Base</span>
						</a>
					</li>
					<li class="nav-campaign
						<g:if test="${controllerName == "asignarNovedades"}">
						nav-active subnav-expanded
					</g:if>
					">
						<a href="${createLink(uri: '/asignarNovedades/index')}">
							<i class="fa fa-fw fa-upload"></i><span class="menu-name">&nbsp;Asignar Novedades</span>
						</a>
					</li>
					<li class="nav-campaign
						<g:if test="${controllerName == "adicionalesCreadas"}">
						nav-active subnav-expanded
					</g:if>
					">
						<a href="${createLink(uri: '/adicionalesCreadas/cargarBase')}">
							<i class="fa fa-fw fa-upload"></i><span class="menu-name">&nbsp;Cargar Adicionales</span>
						</a>
					</li>
				</ul>
			<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/bitacoraConversiones')||Util.checkAccess(session.user.usuario, '/reportes/gerencial')}">
				<a class="parent-sub" data-toggle="collapse" data-target="#sub-reportes">
					<i class="fa fa-fw fa-table sidebar-icon"></i><span class="menu-name">&nbsp;Reportes</span>
					<i class="fa fa-fw fa-caret-down"></i>
				</a>

				<ul id="sub-reportes" class="collapse nav-sub">
					<%--<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/asistencias')}">
						<li class="nav-reports
							<g:if test="${actionName == "asistencias"}">
								nav-active subnav-expanded
							</g:if>
						">
							<a href="${createLink(uri: '/reportes/asistencias')}">
								<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Asistencias</span>
							</a>
						</li>
					</g:if>--%>
					<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/bitacora')}">
						<li class="nav-reports
						<g:if test="${actionName == "bitacora"}">
							nav-active subnav-expanded
						</g:if>
						">
							<a href="${createLink(uri: '/reportes/bitacora')}">
								<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Bitácora Adicionales</span>
							</a>
						</li>
					</g:if>
					<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/bitacoraGestion')}">
						<li class="nav-reports
							<g:if test="${actionName == "bitacoraGestion"}">
							nav-active subnav-expanded
						</g:if>
						">
							<a href="${createLink(uri: '/reportes/bitacoraGestion')}">
								<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Trama Gestión Completa</span>
							</a>
						</li>
					</g:if>

					<%--<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/bitacoraAdicional')}">
						<li class="nav-reports
						<g:if test="${actionName == "bitacoraAdicional"}">
							nav-active subnav-expanded
						</g:if>
						">
							<a href="${createLink(uri: '/reportes/bitacoraAdicional')}">
								<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Bitácora Seguro D.</span>
							</a>
						</li>
					</g:if>--%>


					<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/indicadoresGestion')}">
						<li class="nav-reports
						<g:if test="${actionName == "indicadoresGestion"}">
							nav-active subnav-expanded
						</g:if>
						">
							<a href="${createLink(uri: '/reportes/indicadoresGestion')}">
								<i class="fa fa-fw fa-line-chart"></i><span class="menu-name">&nbsp;Indicadores</span>
							</a>
						</li>
					</g:if>
					<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/tiemposBreak')}">
						<li class="nav-reports
						<g:if test="${actionName == "tiemposBreak"}">
							nav-active subnav-expanded
						</g:if>
						">
							<a href="${createLink(uri: '/reportes/tiemposBreak')}">
								<i class="fa fa-fw fa-clock-o"></i><span class="menu-name">&nbsp;Tiempos Break</span>
							</a>
						</li>
					</g:if>

					<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/baseGestionada')}">
						<li class="nav-reports
							<g:if test="${actionName == "baseGestionada"}">
							nav-active subnav-expanded
						</g:if>
						">
							<a href="${createLink(uri: '/reportes/baseGestionada')}">
								<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Base Gestionada</span>
							</a>
						</li>
					</g:if>

					<%--<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/bitacoraNovedades')}">
						<li class="nav-reports
						<g:if test="${actionName == "bitacoraNovedades"}">
							nav-active subnav-expanded
						</g:if>
						">
							<a href="${createLink(uri: '/reportes/bitacoraNovedades')}">
								<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Bitácora Novedades</span>
							</a>
						</li>
					</g:if>
					<g:if test="${Util.checkAccess(session.user.usuario, '/reportes/gestionNovedades')}">
						<li class="nav-reports
						<g:if test="${actionName == "gestionNovedades"}">
							nav-active subnav-expanded
						</g:if>
						">
							<a href="${createLink(uri: '/reportes/gestionNovedades')}">
								<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Gestión Novedades</span>
							</a>
						</li>
					</g:if>--%>
				</ul>
			</g:if>

				<g:if test="${Util.checkAccess(session.user.usuario, '/provincia/index')||Util.checkAccess(session.user.usuario, '/ciudad/index')||Util.checkAccess(session.user.usuario, '/parroquia/index')}">
					<a class="parent-sub" data-toggle="collapse" data-target="#sub-catalogos">
						<i class="fa fa-fw fa-table sidebar-icon"></i><span class="menu-name">&nbsp;Configuración</span>
						<i class="fa fa-fw fa-caret-down"></i>
					</a>

					<ul id="sub-catalogos" class="collapse nav-sub">
						<g:if test="${Util.checkAccess(session.user.usuario, '/configuracion/cambiarInicioGestion')}">
							<li class="nav-catalogos
						<g:if test="${controllerName == "configuracion" && actionName == "cambiarInicioGestion"}">
								nav-active subnav-expanded
							</g:if>
							">
								<a href="${createLink(uri: '/configuracion/cambiarInicioGestion')}">
									<i class="fa fa-fw fa-calendar"></i><span class="menu-name">&nbsp;Inicio Gestión</span>
								</a>
							</li>
						</g:if>
						<g:if test="${Util.checkAccess(session.user.usuario, '/configuracion/deshabilitarBases')}">
							<li class="nav-catalogos
						<g:if test="${controllerName == "configuracion" && actionName == "deshabilitarBases"}">
								nav-active subnav-expanded
							</g:if>
							">
								<a href="${createLink(uri: '/configuracion/deshabilitarBases')}">
									<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Deshabilitar Bases</span>
								</a>
							</li>
						</g:if>
						%{--<g:if test="${Util.checkAccess(session.user.usuario, '/parroquia/index')}">--}%
							%{--<li class="nav-catalogos--}%
						%{--<g:if test="${controllerName == "parroquia"}">--}%
								%{--nav-active subnav-expanded--}%
							%{--</g:if>--}%
							%{--">--}%
								%{--<a href="${createLink(uri: '/parroquia/index')}">--}%
									%{--<i class="fa fa-fw fa-table"></i><span class="menu-name">&nbsp;Parroquias</span>--}%
								%{--</a>--}%
							%{--</li>--}%
						%{--</g:if>--}%
					</ul>
				</g:if>

			</li>
		</g:if>
		<li
			<g:if test="${controllerName == "gestion" && actionName == "index"}">
				class="nav-active"
			</g:if>>
			<a href="${createLink(uri: '/gestion/index')}">
				<i class="fa fa-fw fa-arrow-circle-right"></i><span class="menu-name">&nbsp;Gestionar</span>
			</a>
		</li>
		<li
			<g:if test="${controllerName == "gestionNovedades"}">
				class="nav-active"
			</g:if>>
			<a href="${createLink(uri: '/gestionNovedades/index')}">
				<i class="fa fa-fw fa-arrow-circle-right"></i><span class="menu-name">&nbsp;Gestionar Novedades</span>
			</a>
		</li>
		%{--<li class="box-workaround-bug">&nbsp;</li>--}%


		<li class="box-workaround-bug">&nbsp;</li>

	</ul>
</div>
