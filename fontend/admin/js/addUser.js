document.addEventListener('DOMContentLoaded', () => {
    const addUserForm = document.getElementById('addUserForm');

    addUserForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const userDTO = {
            userName: document.getElementById('username').value,
            password: document.getElementById('password').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            status: document.getElementById('status').value,
            gender: document.getElementById('gender').value,
            lastname: document.getElementById('lastname').value,
            firtname: document.getElementById('firtname').value,
    
        };
        const roleid = document.getElementById('role').value;

        fetch(`http://localhost:2003/admin/user/admin/createuser?roleid=${roleid}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userDTO)
        })
        .then(response => {
            if (response.ok) {
                alert('User added successfully');
                window.location.href = './UserList.html';
            } else {
                return response.json().then(error => {
                    throw new Error(error.message);
                });
            }
        })
        .catch(error => alert('Error adding user: ' + error.message));
    });
});
