<%@page import="Model.Bean.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Quản lý thư viện</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bbootstrap 4 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.1.2/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/icheck-bootstrap/3.0.1/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqvmap/1.5.1/jqvmap.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.13.0/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-bs4.min.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
<!-- Bootstrap Color Picker -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/3.2.0/css/bootstrap-colorpicker.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.1.0-beta.1/css/select2.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2-bootstrap4-theme/1.0.0-beta.2/select2-bootstrap4.min.css">
<!-- Bootstrap4 Duallistbox -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap4-duallistbox/4.0.2/bootstrap-duallistbox.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="${pageContext.request.contextPath}/UserManual" class="nav-link">Hướng dẫn sử dụng</a></li>
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<a href="${pageContext.request.contextPath}/UserManual" class="brand-link">
				<img src="https://cdn.example.com/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light">Quán lý thư viện</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<%
						if (request.getSession().getAttribute("User") != null) {
					%>
					<%
						User user = (User) request.getSession().getAttribute("User");
					%>
					<div class="image">
						<img src="https://cdn.example.com/img/avatar.jpg" class="img-circle elevation-2" alt="User Image">
					</div>
					<div class="info">
						<a href="#" class="d-block"><%=user.getUsername()%></a>
					</div>
					<%
						} else {
					%>
					<div class="info" style="margin-left: 60px;">
						<a href="${pageContext.request.contextPath}/Login" class="d-block">Đăng nhập</a>
					</div>
					<%
						}
					%>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
						<li class="nav-item "><a href="${pageContext.request.contextPath}/UserManual" class="nav-link <c:if test="${sessionScope.Check.toString().equals('Manual')}">active</c:if> ">
								<i class="nav-icon fas fa-book"></i>
								<p>Hướng dẫn</p>
						</a></li>
						<li class="nav-item has-treeview <c:if test="${sessionScope.Check.toString().equals('ManageBook') || sessionScope.Check.toString().equals('AddBook')||sessionScope.Check.toString().equals('ManageCategory')}">menu-open</c:if>"><a href="#"
								class="nav-link <c:if test="${sessionScope.Check.toString().equals('ManageBook') || sessionScope.Check.toString().equals('AddBook')||sessionScope.Check.toString().equals('ManageCategory')}">active</c:if> ">
								<i class="nav-icon fas fa-book"></i>
								<p>
									Quản lý sách<i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="${pageContext.request.contextPath}/ManageBook" class="nav-link <c:if test="${sessionScope.Check.toString().equals('ManageBook')}">active</c:if> ">
										<i class="far fa-circle nav-icon"></i>
										<p>Danh sách</p>
								</a></li>
								<li class="nav-item"><a href="${pageContext.request.contextPath}/AddBook" class="nav-link <c:if test="${sessionScope.Check.toString().equals('AddBook')}">active</c:if>">
										<i class="far fa-circle nav-icon"></i>
										<p>Thêm sách</p>
								</a></li>
								<li class="nav-item"><a href="${pageContext.request.contextPath}/ManageCategory" class="nav-link <c:if test="${sessionScope.Check.toString().equals('ManageCategory')}">active</c:if>">
										<i class="far fa-circle nav-icon"></i>
										<p>Thể loại</p>
								</a></li>
							</ul></li>
						<li class="nav-item has-treeview  <c:if test="${sessionScope.Check.toString().equals('ManageReader_0') || sessionScope.Check.toString().equals('AddReader') || sessionScope.Check.toString().equals('ManageReader_1')}">menu-open</c:if>"><a href="#"
								class="nav-link <c:if test="${sessionScope.Check.toString().equals('ManageReader_0')|| sessionScope.Check.toString().equals('ManageReader_1') || sessionScope.Check.toString().equals('AddReader')}">active</c:if>">
								<i class="nav-icon fas fa-book"></i>
								<p>
									Quản lý mượn sách<i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="${pageContext.request.contextPath}/AddReader" class="nav-link <c:if test="${sessionScope.Check.toString().equals('AddReader')}">active</c:if>">
										<i class="far fa-circle nav-icon"></i>
										<p>Thêm người mượn sách</p>
								</a></li>
								<li class="nav-item"><a href="${pageContext.request.contextPath}/ManageReader" class="nav-link <c:if test="${sessionScope.Check.toString().equals('ManageReader_0')}">active</c:if>">
										<i class="far fa-circle nav-icon"></i>
										<p>Danh sách đang mượn sách</p>
								</a></li>

								<li class="nav-item"><a href="${pageContext.request.contextPath}/ManageReader?status=1" class="nav-link <c:if test="${sessionScope.Check.toString().equals('ManageReader_1')}">active</c:if>">
										<i class="far fa-circle nav-icon"></i>
										<p>Lịch sử mượn sách</p>
								</a></li>
							</ul></li>
						<%
							if (request.getSession().getAttribute("User") != null) {
						%>
						<li class="nav-item"><a href="${pageContext.request.contextPath}/Logout" class="nav-link">
								<i class="fas fa-circle nav-icon"></i>
								<p>Đăng xuất</p>
						</a></li>
						<%
							}
						%>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>
