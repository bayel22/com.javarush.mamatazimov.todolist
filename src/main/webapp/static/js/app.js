document.addEventListener('DOMContentLoaded', () => {
    const addTaskForm = document.getElementById('addTaskForm');
    const tasksTable = document.getElementById('tasksTable').getElementsByTagName('tbody')[0];

    // Функция для создания строки задачи в таблице
    function createTaskRow(task) {
        const tr = document.createElement('tr');
        tr.dataset.id = task.id;

        const tdDesc = document.createElement('td');
        tdDesc.className = 'description';
        tdDesc.textContent = task.description;

        const tdStatus = document.createElement('td');
        tdStatus.className = 'status';
        tdStatus.textContent = task.status;

        const tdActions = document.createElement('td');

        const editBtn = document.createElement('button');
        editBtn.className = 'btn btn-sm btn-warning me-2 edit-btn';
        editBtn.textContent = 'Edit';

        const deleteBtn = document.createElement('button');
        deleteBtn.className = 'btn btn-sm btn-danger delete-btn';
        deleteBtn.textContent = 'Delete';

        tdActions.appendChild(editBtn);
        tdActions.appendChild(deleteBtn);

        tr.appendChild(tdDesc);
        tr.appendChild(tdStatus);
        tr.appendChild(tdActions);

        return tr;
    }

    // Добавление задачи
    addTaskForm.addEventListener('submit', e => {
        e.preventDefault();

        const descInput = document.getElementById('newDescription');
        const statusSelect = document.getElementById('newStatus');

        const newTask = {
            description: descInput.value.trim(),
            status: statusSelect.value
        };

        if (!newTask.description) {
            alert('Task description cannot be empty!');
            return;
        }

        fetch('/', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newTask)
        }).then(res => {
            if (res.ok) {
                // Обновляем список задач, запросив их с сервера (лучше, чем reload)
                loadTasks();
                descInput.value = '';
                statusSelect.value = 'IN_PROGRESS';
            } else {
                alert('Failed to add task');
            }
        });
    });

    // Загрузка задач с сервера (GET /, возвращает HTML, но мы можем обновить страницу)
    // Но лучше сделаем отдельный эндпоинт для JSON - если хочешь, могу помочь
    function loadTasks() {
        location.reload();
    }

    // Делегирование событий на таблицу (редактировать и удалить)
    tasksTable.addEventListener('click', e => {
        const target = e.target;
        const row = target.closest('tr');
        if (!row) return;
        const id = row.dataset.id;

        if (target.classList.contains('edit-btn')) {
            if (target.textContent === 'Edit') {
                // Включаем редактирование
                const desc = row.querySelector('.description').textContent;
                const status = row.querySelector('.status').textContent;

                row.querySelector('.description').innerHTML = `<input type="text" class="form-control form-control-sm desc-edit" value="${desc}">`;

                row.querySelector('.status').innerHTML = `
                    <select class="form-select form-select-sm status-edit">
                        <option value="IN_PROGRESS" ${status === 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                        <option value="DONE" ${status === 'DONE' ? 'selected' : ''}>Done</option>
                        <option value="PAUSED" ${status === 'PAUSED' ? 'selected' : ''}>Paused</option>
                    </select>
                `;

                target.textContent = 'Save';
                row.querySelector('.delete-btn').disabled = true;

            } else {
                // Сохраняем изменения
                const newDesc = row.querySelector('.desc-edit').value.trim();
                const newStatus = row.querySelector('.status-edit').value;

                if (!newDesc) {
                    alert('Description cannot be empty');
                    return;
                }

                fetch(`/${id}`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ description: newDesc, status: newStatus })
                }).then(res => {
                    if (res.ok) {
                        row.querySelector('.description').textContent = newDesc;
                        row.querySelector('.status').textContent = newStatus;
                        target.textContent = 'Edit';
                        row.querySelector('.delete-btn').disabled = false;
                    } else {
                        alert('Failed to update task');
                    }
                });
            }
        } else if (target.classList.contains('delete-btn')) {
            if (confirm('Are you sure you want to delete this task?')) {
                fetch(`/${id}`, { method: 'DELETE' }).then(res => {
                    if (res.ok) {
                        row.remove();
                    } else {
                        alert('Failed to delete task');
                    }
                });
            }
        }
    });
});
