// Employee management scripts
document.addEventListener('DOMContentLoaded', function() {
    // Search functionality with debounce
    let searchTimeout;
    const searchInput = document.getElementById('searchName');
    if (searchInput) {
        searchInput.addEventListener('input', function() {
            clearTimeout(searchTimeout);
            searchTimeout = setTimeout(() => {
                const searchTerm = this.value.trim();
                if (searchTerm) {
                    window.location.href = `/search?name=${encodeURIComponent(searchTerm)}`;
                } else {
                    window.location.href = '/';
                }
            }, 500);
        });
    }

    // Department filter
    const departmentFilter = document.getElementById('departmentFilter');
    if (departmentFilter) {
        departmentFilter.addEventListener('change', function() {
            const department = this.value.trim();
            if (department) {
                window.location.href = `/filter?department=${encodeURIComponent(department)}`;
            } else {
                window.location.href = '/';
            }
        });
    }

    // Form handling
    const employeeForm = document.getElementById('employeeForm');
    if (employeeForm) {
        employeeForm.addEventListener('submit', handleFormSubmit);
    }
});

// Delete employee function
function deleteEmployee(id) {
    if (confirm('Are you sure you want to delete this employee?')) {
        fetch(`/api/employees/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                window.location.reload();
            } else {
                throw new Error('Failed to delete employee');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to delete employee');
        });
    }
}

// Form handling function
async function handleFormSubmit(e) {
    e.preventDefault();
    if (!validateEmployeeForm()) return;

    const form = e.target;
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    const id = data.id || '';
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/api/employees/${id}` : '/api/employees';

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) throw new Error('Failed to save employee');

        window.location.href = '/';
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to save employee');
    }
}

// Form validation
function validateEmployeeForm() {
    const form = document.getElementById('employeeForm');
    if (!form.checkValidity()) {
        form.reportValidity();
        return false;
    }
    return true;
}