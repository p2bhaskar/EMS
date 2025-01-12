<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card">
    <div class="card-header">
        <h5 class="mb-0">${empty employee.id ? 'Add New Employee' : 'Edit Employee'}</h5>
    </div>
    <div class="card-body">
        <form id="employeeForm">
            <input type="hidden" name="id" value="${employee.id}">

            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="${employee.name}" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="${employee.email}" required>
            </div>

            <div class="mb-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department"
                       value="${employee.department}" required>
            </div>

            <div class="mb-3">
                <label for="designation" class="form-label">Designation</label>
                <input type="text" class="form-control" id="designation" name="designation"
                       value="${employee.designation}" required>
            </div>

            <div class="mb-3">
                <label for="joiningDate" class="form-label">Joining Date</label>
                <input type="date" class="form-control" id="joiningDate" name="joiningDate"
                       value="<fmt:formatDate value="${employee.joiningDate}" pattern="yyyy-MM-dd"/>" required>
            </div>

            <div class="mb-3">
                <label for="salary" class="form-label">Salary</label>
                <input type="number" class="form-control" id="salary" name="salary"
                       value="${employee.salary}" step="0.01" required>
            </div>

            <div class="text-end">
                <button type="button" onclick="location.href='/'" class="btn btn-secondary">Cancel</button>
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
</div>