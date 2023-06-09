<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<nav class="pcoded-navbar">
	<div class="sidebar_toggle">
		<a href="#"><i class="icon-close icons"></i></a>
	</div>
	<div class="pcoded-inner-navbar main-menu">
		<div class="">
			<div class="main-menu-header">
				<img class="img-80 img-radius"
					src="<%=request.getContextPath()%>/assets/images/avatar-4.jpg"
					alt="User-Profile-Image">
				<div class="user-details">
					<span id="more-details"><%=session.getAttribute("usuario")%><i
						class="fa fa-caret-down"></i></span>
				</div>
			</div>

			<div class="main-menu-content">
				<ul>
					<li class="more-details">
					<a href="<%=request.getContextPath()%>/ServletLogin?acao=logout">
					<i class="ti-layout-sidebar-left"></i>Logout</a>
					</li>
				</ul>
			</div>
		</div>
		<ul class="pcoded-item pcoded-left-item">
			<li class="active"><a href="<%=request.getContextPath()%>/principal/principal.jsp"
				class="waves-effect waves-dark" style="margin-top:10px;"> <span class="pcoded-micon"><i
						class="ti-home"></i><b>D</b></span> <span class="pcoded-mtext"
					data-i18n="nav.dash.main">Início</span> <span
					class="pcoded-mcaret"></span>
			</a></li>
			<li class="pcoded-hasmenu"><a href="javascript:void(0)"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-layout-grid2-alt"></i></span> <span class="pcoded-mtext"
					data-i18n="nav.basic-components.main">Cadastro</span> <span
					class="pcoded-mcaret"></span>
			</a>
				<ul class="pcoded-submenu">
					<li class=" "><a
						href="<%=request.getContextPath()%>/ServeletUsuarioController?acao=listarUser"
						class="waves-effect waves-dark"> <span class="pcoded-micon"><i
								class="ti-angle-right"></i></span> <span class="pcoded-mtext"
							data-i18n="nav.basic-components.alert">Usuário</span> <span
							class="pcoded-mcaret"></span>
					</a></li>
				</ul>
		<div class="pcoded-navigation-label" data-i18n="nav.category.forms">Relatórios</div>
		<ul class="pcoded-item pcoded-left-item">
			<li><a href="form-elements-component.html"
				class="waves-effect waves-dark"> <span class="pcoded-micon"><i
						class="ti-layers"></i><b>FC</b></span> <span class="pcoded-mtext"
					data-i18n="nav.form-components.main">Relatórios de Usuários</span> <span
					class="pcoded-mcaret"></span>
			</a></li>

		</ul>

	</div>
</nav>