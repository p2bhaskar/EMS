<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Search and Filter Section -->
<div class="row mb-4">
    <div class="col-md-4">
        <div class="input-group">
            <span class="input-group-text"><i class="fas fa-search"></i></span>
            <input type="text" id="searchName" class="form-control" placeholder="Search by name...">
        </div>
    </div>
    <div class="col-md-4">
        <div class="input-group">
            <span class="input-group-text"><i class="fas fa-filter"></i></span>
            <select id="departmentFilter" class="form-select">
                <option value="">All Departments</option>
                <c:forEach items="${departments}" var="dept">
                    <option value="${dept}">${dept}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-4 text-end">
        <button onclick="location.href='/add-employee'" class="btn btn-primary">
            <i class="fas fa-plus"></i> Add Employee
        </button>
    </div>
</div>

<!-- Employee Table -->
<div class="card">
    <div class="card-header">
        <h5 class="mb-0">Employee List</h5>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Department</th>
                        <th>Designation</th>
                        <th>Joining Date</th>
                        <th>Salary</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employees.content}" var="employee">
                        <tr>
                            <td>${employee.name}</td>
                            <td>${employee.email}</td>
                            <td>${employee.department}</td>
                            <td>${employee.designation}</td>
                            <td><fmt:formatDate value="${employee.joiningDate}" pattern="yyyy-MM-dd"/></td>
                            <td>$<fmt:formatNumber value="${employee.salary}" pattern="#,##0.00"/></td>
                            <td>
                                <div class="btn-group">
                                    <button onclick="location.href='/edit-employee?id=${employee.id}'"
                                            class="btn btn-sm btn-warning">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button onclick="deleteEmployee(${employee.id})"
                                            class="btn btn-sm btn-danger">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Pagination -->
        <nav aria-label="Page navigation" class="mt-4">
            <ul class="pagination justify-content-center">
                <c:forEach begin="0" end="${employees.totalPages - 1}" var="page">
                    <li class="page-item ${page == employees.number ? 'active' : ''}">
                        <a class="page-link" href="?page=${page}&size=${employees.size}">${page + 1}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>