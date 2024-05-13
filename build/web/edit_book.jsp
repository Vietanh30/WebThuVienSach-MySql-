<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!-- Main content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0 text-dark"></h1>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <!-- general form elements -->
                    <form role="form" method="post" action="${pageContext.request.contextPath}/EditBook" enctype="multipart/form-data">
                        <div class="card card-primary">
                            <div class="card-header">
                                <h3 class="card-title">Chỉnh sửa tên thể loại</h3>
                            </div>
                            <div class="row justify-content-center" style="margin-top: 15px; margin-bottom: -15px;">
                                <div style="color: red;">${errorString}</div>
                            </div>
                            <!-- /.card-header -->
                            <!-- form start -->
                            <div class="card-body">
                                <input type="hidden" name="id" value="${book.id}" />
                                <div class="form-group">
                                    <label>Tên sách</label>
                                    <input type="text" class="form-control" id="name" name="name" value="${book.getName()}">
                                </div>
                                <div class="form-group">
                                    <label>Thể loại</label>
                                    <select name="category" id="category" class="form-control" data-selected="${book.getCategory().getName()}" required>
                                        <c:forEach items="${categoryList}" var="category">
                                            <option value="${Integer.toString(category.getId())}" <c:if test="${category.getId() == book.getCategory().getId()}">selected="true"</c:if>>${category.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Số lượng</label>
                                    <input type="number" class="form-control" id="count" name="count" min="1" value="${book.getAmount()}">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputFile">Thay ảnh bìa (Nếu cần)</label>
                                    <div class="input-group">
                                        <div class="custom-file">
                                            <input type="hidden" name="image_str" value="${book.getImage()}" />
                                            <input type="file" accept="image/png, image/jpeg" class="custom-file-input" id="customFile" name="fileImage">
                                            <label class="custom-file-label" for="customFile" style="color: #a6b0ba;">Nhấn đây để chọn file</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary">Lưu</button>
                                <input type="button" value="Trở lại" class="btn btn-primary" onclick="location.href = '${pageContext.request.contextPath}/ManageBook'">
                            </div>
                        </div>
                    </form>
                </div>
                <!-- /.card -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
    <%@ include file="footer.jsp"%>
    <!-- jQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
    <!-- bs-custom-file-input -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bs-custom-file-input/1.3.4/bs-custom-file-input.min.js"></script>
    <!-- AdminLTE App -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/js/demo.min.js"></script>
    <script type="text/javascript">
                                    $(document).ready(function () {
                                        bsCustomFileInput.init();
                                    });
    </script>
</div>
