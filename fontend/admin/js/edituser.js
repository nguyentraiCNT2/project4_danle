document.addEventListener('DOMContentLoaded', () => {
    const apiBaseUrl = 'http://localhost:2003/admin/user';
    const formId = document.getElementById('editUserForm');
    const pagination = document.getElementById('pagination');
    const searchForm = document.getElementById('searchForm');
    const searchInput = document.getElementById('searchInput');
    const id = localStorage.getItem('userid');
    const thongbao = document.getElementById('thongbao');
    const fetchData = () => {
        const url = `http://localhost:2003/admin/user/getuserbyid/${id}`;

        fetch(url)
            .then(response => response.json())
            .then(data => {
                populateTable(data);
            })
            .catch(error => console.error('Error fetching data:', error));
    };

    const populateTable = (user) => {
        formId.innerHTML =
            `<div class="mb-3">
            <label for="username" class="form-label">User Name</label>
            <input type="text" class="form-control" id="username" value="${user.userName}" required>
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">firt Name</label>
            <input type="text" class="form-control" id="firtname" value="${user.firstName}" required>
        </div>
        
        <div class="mb-3">
            <label for="username" class="form-label">last Name</label>
            <input type="text" class="form-control" id="lastname" value="${user.lastName}" required>
        </div>
        
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" value="${user.password}" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" value="${user.email}" required>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" value="${user.phone}" required>
        </div>
        <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <select class="form-select" id="role" required>
                <option value="" ${user.role.roleId === '' ? 'selected' : ''}>Select role</option>
                <option value="1" ${user.role.roleId === 1 ? 'selected' : ''}>Admin</option>
                <option value="2" ${user.role.roleId === 2 ? 'selected' : ''}>User</option>
            </select>
        </div>
        <div class="mb-3">
                        <label for="role" class="form-label">Status</label>
                        <select class="form-select" id="status" required>
                            <option value="true" ${user.status === ''? 'selected' : ''}>Select status</option>
                            <option value="" ${user.status === true? 'selected' : ''} >Active</option>
                            <option value="false" ${user.status === false? 'selected' : ''}>Unactive</option>
                            <!-- Add more options as needed -->
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="role" class="form-label">gender</label>
                        <select class="form-select" id="gender" required>
                            <option value=""  ${user.gender === '' ? 'selected' : ''}>Select gender</option>
                            <option value="true"  ${user.gender === true? 'selected' : ''}>Nam</option>
                            <option value="false"  ${user.gender === false? 'selected' : ''}>Nữ</option>
                            <!-- Add more options as needed -->
                        </select>
        <button type="submit" class="btn btn-primary" id="updateButton">Update</button>`;

        document.getElementById('updateButton').addEventListener('click', updateUser);
    };

    const updateUser = () => {
        const user = {
            userName: document.getElementById('username').value,
            password: document.getElementById('password').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            lastName: document.getElementById('lastname').value,
            firstName: document.getElementById('firtname').value,
            gender: document.getElementById('gender').value,
            status: document.getElementById('status').value,
            role: {
                roleId: parseInt(document.getElementById('role').value)
            }
        };

        fetch(`http://localhost:2003/admin/user/admin/updateuser/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(response => response.json())
            .then(data => {
                window.location.href = 'http://127.0.0.1:5500/User/UserList.html';
            })

            .catch(error => console.error('Error updating user:', error));
    };

    fetchData();
});
