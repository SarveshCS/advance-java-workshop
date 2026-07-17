document.addEventListener("DOMContentLoaded", () => {
    setupThemeToggle();
    setupDragReorder();
    setupEditAutosave();
    setupTaskActions();
    setupDescriptionResize();
    setupTaskSearch();
    updateProgress();

    if (window.lucide) {
        window.lucide.createIcons();
    }
});

function setupThemeToggle() {
    const themeToggle = document.getElementById("themeToggle");
    const savedTheme = localStorage.getItem("todo-theme") || "light";
    document.documentElement.dataset.theme = savedTheme;
    updateThemeIcon(savedTheme);

    if (!themeToggle) {
        return;
    }

    themeToggle.addEventListener("click", () => {
        const nextTheme = document.documentElement.dataset.theme === "dark" ? "light" : "dark";
        document.documentElement.dataset.theme = nextTheme;
        localStorage.setItem("todo-theme", nextTheme);
        updateThemeIcon(nextTheme);
        if (window.lucide) {
            window.lucide.createIcons();
        }
    });
}

function updateThemeIcon(theme) {
    const themeToggle = document.getElementById("themeToggle");
    if (!themeToggle) {
        return;
    }

    const iconName = theme === "dark" ? "sun" : "moon";
    themeToggle.innerHTML = `<i data-lucide="${iconName}"></i>`;
}

function setupDragReorder() {
    const todoList = document.getElementById("todoList");
    const autosaveStatus = document.getElementById("autosaveStatus");

    if (!todoList) {
        return;
    }

    const reorderUrl = todoList.dataset.reorderUrl || "todo/reorder";
    let draggedRow = null;

    todoList.addEventListener("dragstart", (event) => {
        draggedRow = event.target.closest(".todo-row");
        if (draggedRow) {
            draggedRow.classList.add("dragging");
            event.dataTransfer.effectAllowed = "move";
        }
    });

    todoList.addEventListener("dragend", () => {
        if (draggedRow) {
            draggedRow.classList.remove("dragging");
            draggedRow = null;
            saveOrder(todoList, reorderUrl, autosaveStatus);
        }
    });

    todoList.addEventListener("dragover", (event) => {
        event.preventDefault();
        const afterElement = getDragAfterElement(todoList, event.clientY);
        const draggingElement = todoList.querySelector(".dragging");

        if (!draggingElement) {
            return;
        }

        if (afterElement == null) {
            todoList.appendChild(draggingElement);
        } else {
            todoList.insertBefore(draggingElement, afterElement);
        }
    });
}

function saveOrder(todoList, reorderUrl, autosaveStatus) {
    const formData = new URLSearchParams();
    todoList.querySelectorAll(".todo-row").forEach((todoRow) => {
        formData.append("todoId", todoRow.dataset.id);
    });

    if (autosaveStatus) {
        autosaveStatus.textContent = "Saving order...";
    }
    showStatus("Saving order...");

    fetch(reorderUrl, {
        method: "POST",
        body: formData,
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
            "X-Requested-With": "XMLHttpRequest"
        }
    }).then((response) => {
        if (!response.ok) {
            throw new Error("Order save failed");
        }
        if (autosaveStatus) {
            autosaveStatus.textContent = "Order saved.";
        }
        showStatus("Order saved");
    }).catch(() => {
        if (autosaveStatus) {
            autosaveStatus.textContent = "Order could not be saved. Try refreshing the page.";
        }
        showStatus("Order could not be saved", true);
    });
}

function getDragAfterElement(container, mouseY) {
    const draggableElements = [...container.querySelectorAll(".todo-row:not(.dragging)")];

    return draggableElements.reduce((closest, child) => {
        const box = child.getBoundingClientRect();
        const offset = mouseY - box.top - box.height / 2;

        if (offset < 0 && offset > closest.offset) {
            return { offset: offset, element: child };
        }

        return closest;
    }, { offset: Number.NEGATIVE_INFINITY }).element;
}

function setupEditAutosave() {
    const autosaveStatus = document.getElementById("autosaveStatus");
    document.querySelectorAll(".auto-save-form").forEach((form) => {
        let saveTimer = null;
        const save = () => {
            clearTimeout(saveTimer);
            saveTimer = setTimeout(() => saveEdit(form, autosaveStatus), 450);
        };

        form.querySelectorAll("input[name='title'], textarea[name='description']").forEach((field) => {
            field.addEventListener("input", save);
        });

        const dueDate = form.querySelector("input[name='dueDate']");
        if (dueDate) {
            dueDate.addEventListener("change", () => saveEdit(form, autosaveStatus));
        }
    });
}

function saveEdit(form, autosaveStatus) {
    const title = form.querySelector("input[name='title']");
    if (!title || title.value.trim() === "") {
        return;
    }

    if (autosaveStatus) {
        autosaveStatus.textContent = "Saving edits...";
    }
    showStatus("Saving changes...");

    fetch(form.action, {
        method: "POST",
        body: new URLSearchParams(new FormData(form)),
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
            "X-Requested-With": "XMLHttpRequest"
        }
    }).then((response) => {
        if (!response.ok) {
            throw new Error("Edit save failed");
        }
        if (autosaveStatus) {
            autosaveStatus.textContent = "All changes saved.";
        }
        showStatus("Changes saved");
    }).catch(() => {
        if (autosaveStatus) {
            autosaveStatus.textContent = "Edit could not be saved. Try again.";
        }
        showStatus("Edit could not be saved", true);
    });
}

function setupTaskActions() {
    document.querySelectorAll(".task-action-form").forEach((form) => {
        form.addEventListener("submit", (event) => {
            event.preventDefault();
            submitTaskAction(form);
        });
    });
}

function submitTaskAction(form) {
    const todoRow = form.closest(".todo-row");
    if (!todoRow) {
        return;
    }

    fetch(form.action, {
        method: "POST",
        body: new URLSearchParams(new FormData(form)),
        headers: {
            "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
            "X-Requested-With": "XMLHttpRequest"
        }
    }).then((response) => {
        if (!response.ok) {
            throw new Error("Task action failed");
        }

        if (form.dataset.action === "toggle") {
            toggleTaskRow(todoRow, form);
            showStatus(todoRow.dataset.completed === "true" ? "Marked complete" : "Marked open");
        } else if (form.dataset.action === "delete") {
            removeTaskRow(todoRow);
            showStatus("Task deleted");
        }
    }).catch(() => {
        showStatus("Task action failed", true);
    });
}

function toggleTaskRow(todoRow, form) {
    const wasCompleted = todoRow.dataset.completed === "true";
    const isCompleted = !wasCompleted;
    todoRow.dataset.completed = String(isCompleted);
    todoRow.classList.toggle("completed", isCompleted);

    const button = form.querySelector("button");
    const icon = form.querySelector("i");
    if (button) {
        button.setAttribute("aria-label", isCompleted ? "Mark pending" : "Mark complete");
        button.setAttribute("title", isCompleted ? "Mark pending" : "Mark complete");
    }
    if (icon) {
        icon.setAttribute("data-lucide", isCompleted ? "circle-check" : "circle");
    }

    updateCount("pendingCount", isCompleted ? -1 : 1);
    updateCount("completedCount", isCompleted ? 1 : -1);
    updateProgress();

    if (window.lucide) {
        window.lucide.createIcons();
    }
}

function removeTaskRow(todoRow) {
    const wasCompleted = todoRow.dataset.completed === "true";
    todoRow.classList.add("removing");
    setTimeout(() => todoRow.remove(), 160);
    updateCount("totalCount", -1);
    updateCount(wasCompleted ? "completedCount" : "pendingCount", -1);
    updateProgress();
    setTimeout(updateFilteredEmptyState, 180);
}

function updateCount(id, delta) {
    const element = document.getElementById(id);
    if (!element) {
        return;
    }

    const nextValue = Math.max(0, Number(element.textContent || 0) + delta);
    element.textContent = String(nextValue);
}

function setupDescriptionResize() {
    document.querySelectorAll(".task-description-input").forEach((field) => {
        resizeDescription(field);
        field.addEventListener("input", () => resizeDescription(field));
    });
}

function resizeDescription(field) {
    field.style.height = "0px";
    field.style.height = `${Math.max(28, field.scrollHeight)}px`;
}

function setupTaskSearch() {
    const taskSearch = document.getElementById("taskSearch");
    if (!taskSearch) {
        return;
    }

    taskSearch.addEventListener("input", () => {
        const query = taskSearch.value.trim().toLowerCase();
        document.querySelectorAll(".todo-row").forEach((todoRow) => {
            const title = todoRow.querySelector("input[name='title']")?.value || "";
            const description = todoRow.querySelector("textarea[name='description']")?.value || "";
            const dueDate = todoRow.querySelector("input[name='dueDate']")?.value || "";
            const text = `${title} ${description} ${dueDate}`.toLowerCase();
            todoRow.hidden = query !== "" && !text.includes(query);
        });
        updateFilteredEmptyState();
    });
}

function updateFilteredEmptyState() {
    const filteredEmpty = document.getElementById("filteredEmpty");
    const taskSearch = document.getElementById("taskSearch");
    if (!filteredEmpty || !taskSearch) {
        return;
    }

    const hasQuery = taskSearch.value.trim() !== "";
    const visibleRows = [...document.querySelectorAll(".todo-row")].filter((todoRow) => !todoRow.hidden);
    filteredEmpty.hidden = !hasQuery || visibleRows.length > 0;
}

function updateProgress() {
    const totalCount = Number(document.getElementById("totalCount")?.textContent || 0);
    const completedCount = Number(document.getElementById("completedCount")?.textContent || 0);
    const progressPercent = totalCount === 0 ? 0 : Math.round((completedCount / totalCount) * 100);
    const progressFill = document.querySelector(".progress-line span");
    const progressCopy = document.getElementById("progressPercent");

    if (progressFill) {
        progressFill.style.width = `${progressPercent}%`;
    }
    if (progressCopy) {
        progressCopy.textContent = `${progressPercent}%`;
    }
}

function showStatus(message, isError = false) {
    const statusToast = document.getElementById("statusToast");
    if (!statusToast) {
        return;
    }

    statusToast.textContent = message;
    statusToast.hidden = false;
    statusToast.classList.toggle("error", isError);
    clearTimeout(showStatus.timeoutId);
    showStatus.timeoutId = setTimeout(() => {
        statusToast.hidden = true;
    }, isError ? 2800 : 1500);
}