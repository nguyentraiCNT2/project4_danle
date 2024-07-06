document.addEventListener('DOMContentLoaded', () => {
    const apiBaseUrl = 'http://localhost:2003/admin/user';
    const userTableBody = document.getElementById('userTableBody');
    const pagination = document.getElementById('pagination');
    const searchForm = document.getElementById('searchForm');
    const searchInput = document.getElementById('searchInput');

    let currentPage = 1;
    const limit = 10;

    const fetchData = (page, limit, search = '') => {
        const url = search 
            ? `${apiBaseUrl}/getbyusername/${search}?page=${page}&limit=${limit}` 
            : `${apiBaseUrl}/getAllUsers?page=${page}&limit=${limit}`;
        
        fetch(url)
            .then(response => response.json())
            .then(data => {
                populateTable(data.listResult); 
                setupPagination(data.page, data.totalPage);
            })
            .catch(error => console.error('Error fetching data:', error));
    };

    const populateTable = (users) => {
        userTableBody.innerHTML = '';
        users.forEach((user, index) => {
            const row = `<tr>
                <th scope="row">${index + 1}</th>
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <td><button type="button" class="btn btn-warning" onclick="editUser(${user.userId})">Edit</button></td>
                <td><button type="button" class="btn btn-danger" onclick="deleteUser(${user.userId})">Delete</button></td>
            </tr>`;
            userTableBody.insertAdjacentHTML('beforeend', row);
        });
    };

    const setupPagination = (page, totalPages) => {
        pagination.innerHTML = '';
        for (let i = 1; i <= totalPages; i++) {
            const pageItem = `<li class="page-item ${i === page ? 'active' : ''}">
                <a class="page-link" href="#" data-page="${i}">${i}</a>
            </li>`;
            pagination.insertAdjacentHTML('beforeend', pageItem);
        }

        document.querySelectorAll('.page-link').forEach(link => {
            link.addEventListener('click', (e) => {
                e.preventDefault();
                currentPage = parseInt(e.target.getAttribute('data-page'));
                fetchData(currentPage, limit, searchInput.value);
            });
        });
    };

    searchForm.addEventListener('submit', (e) => {
        e.preventDefault();
        fetchData(1, limit, searchInput.value);
    });

    fetchData(currentPage, limit,searchInput.value);
});

const editUser = (userId) => {
  localStorage.setItem('userid', userId);
  window.location.href = './EditUser.html';
};

const Userinfo = () =>{

}

const deleteUser = (userId) => {
    if (confirm('Are you sure you want to delete this user?')) {
        fetch(`http://localhost:2003/admin/user/admin/deleteuser/${userId}`, {
            method: 'DELETE',
        })
        .then(response => response.json())
        .then(data => {
            alert('User deleted successfully');
            fetchData(currentPage, limit);
            window.location.reload();
        })
        .catch(error => console.error('Error deleting user:', error));
    }
};
