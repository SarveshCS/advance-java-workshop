<%@ page import="java.util.List" %>
<%@ page import="com.workshop.todo.model.TodoItem" %>
<%@ page import="com.workshop.todo.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<TodoItem> todoItems = (List<TodoItem>) request.getAttribute("todoItems");
    boolean hideCompleted = Boolean.TRUE.equals(request.getAttribute("hideCompleted"));
    String sortBy = (String) request.getAttribute("sortBy");
    int totalCount = (Integer) request.getAttribute("totalCount");
    int completedCount = (Integer) request.getAttribute("completedCount");
    int pendingCount = (Integer) request.getAttribute("pendingCount");
    int completionPercent = totalCount == 0 ? 0 : Math.round((completedCount * 100.0f) / totalCount);
    String sortQuery = "manual".equals(sortBy) ? "" : "&sortBy=" + sortBy;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo Dashboard</title>
    <link rel="stylesheet" href="assets/styles.css">
    <script src="https://unpkg.com/lucide@latest" defer></script>
    <script src="assets/todo.js" defer></script>
</head>
<body>
    <header class="app-nav">
        <a href="dashboard" class="brand-mark" aria-label="Todo dashboard home">
            <span class="brand-icon"><i data-lucide="list-checks"></i></span>
            <span>
                <strong>TaskFlow</strong>
                <small>College Workshop</small>
            </span>
        </a>
        <nav class="nav-actions" aria-label="Dashboard actions">
            <button type="button" class="icon-button" id="themeToggle" aria-label="Switch theme" title="Switch theme">
                <i data-lucide="moon"></i>
            </button>
            <a href="logout" class="icon-button" aria-label="Logout" title="Logout">
                <i data-lucide="log-out"></i>
            </a>
        </nav>
    </header>

    <main class="workspace">
        <section class="page-head">
            <div>
                <p class="eyebrow">Welcome back, <%= HtmlUtil.escape((String) session.getAttribute("userName")) %></p>
                <h1>Tasks</h1>
                <div class="progress-line" aria-label="Completion progress">
                    <span style="width: <%= completionPercent %>%"></span>
                </div>
                <p class="progress-copy"><strong id="progressPercent"><%= completionPercent %>%</strong> completed</p>
            </div>
            <div class="metric-grid" aria-label="Todo summary">
                <article class="metric-card">
                    <i data-lucide="layers-3"></i>
                    <span>Total</span>
                    <strong id="totalCount"><%= totalCount %></strong>
                </article>
                <article class="metric-card">
                    <i data-lucide="loader-circle"></i>
                    <span>Open</span>
                    <strong id="pendingCount"><%= pendingCount %></strong>
                </article>
                <article class="metric-card">
                    <i data-lucide="badge-check"></i>
                    <span>Done</span>
                    <strong id="completedCount"><%= completedCount %></strong>
                </article>
            </div>
        </section>

        <section class="board-panel">
            <div class="board-toolbar">
                <div>
                    <h2>Your Tasks</h2>
                    <p class="muted">Search, edit, complete, and reorder without leaving the board.</p>
                </div>
                <div class="toolbar-actions">
                    <label class="task-search" aria-label="Search tasks">
                        <i data-lucide="search"></i>
                        <input type="search" id="taskSearch" placeholder="Search tasks">
                    </label>
                    <a class="pill-action <%= hideCompleted ? "active" : "" %>" href="dashboard?<%= hideCompleted ? sortQuery.replaceFirst("^&", "") : "hideCompleted=true" + sortQuery %>">
                        <i data-lucide="eye-off"></i>
                        <span><%= hideCompleted ? "Show completed" : "Hide completed" %></span>
                    </a>
                    <div class="sort-tabs" aria-label="Sort todo list">
                        <a class="sort-tab <%= "manual".equals(sortBy) ? "active" : "" %>" href="dashboard<%= hideCompleted ? "?hideCompleted=true" : "" %>" title="Drag order"><i data-lucide="grip"></i><span>Order</span></a>
                        <a class="sort-tab <%= "dueDate".equals(sortBy) ? "active" : "" %>" href="dashboard?sortBy=dueDate<%= hideCompleted ? "&hideCompleted=true" : "" %>" title="Due date"><i data-lucide="calendar-days"></i><span>Date</span></a>
                        <a class="sort-tab <%= "title".equals(sortBy) ? "active" : "" %>" href="dashboard?sortBy=title<%= hideCompleted ? "&hideCompleted=true" : "" %>" title="Title"><i data-lucide="arrow-down-a-z"></i><span>Title</span></a>
                    </div>
                </div>
            </div>

            <% if (todoItems == null || todoItems.isEmpty()) { %>
                <div class="empty-state">
                    <i data-lucide="sparkles"></i>
                    <strong>No tasks yet.</strong>
                    <span>Use the composer to create your first clean, trackable item.</span>
                </div>
            <% } else { %>
                <div class="todo-list" id="todoList" data-reorder-url="todo/reorder">
                    <% for (TodoItem todoItem : todoItems) { %>
                        <article class="todo-row <%= todoItem.isCompleted() ? "completed" : "" %>" draggable="true" data-id="<%= todoItem.getId() %>" data-completed="<%= todoItem.isCompleted() %>">
                            <button type="button" class="drag-handle" aria-label="Drag to reorder" title="Drag to reorder">
                                <i data-lucide="grip-vertical"></i>
                            </button>
                            <form action="todo/update" method="post" class="todo-edit-form auto-save-form">
                                <input type="hidden" name="id" value="<%= todoItem.getId() %>">
                                <div class="task-mainline">
                                    <label class="sr-only" for="title-<%= todoItem.getId() %>">Title</label>
                                    <input id="title-<%= todoItem.getId() %>" class="task-title-input" type="text" name="title" value="<%= HtmlUtil.escape(todoItem.getTitle()) %>" maxlength="120" required>
                                    <label class="task-date-field" title="Due date">
                                        <i data-lucide="calendar"></i>
                                        <input type="date" name="dueDate" value="<%= todoItem.getDueDate() == null ? "" : todoItem.getDueDate() %>" aria-label="Due date">
                                    </label>
                                </div>
                                <label class="sr-only" for="description-<%= todoItem.getId() %>">Description</label>
                                <textarea id="description-<%= todoItem.getId() %>" class="task-description-input" name="description" rows="1" placeholder="Add a short note"><%= HtmlUtil.escape(todoItem.getDescription()) %></textarea>
                            </form>
                            <div class="todo-actions">
                                <form action="todo/toggle" method="post" class="task-action-form" data-action="toggle">
                                    <input type="hidden" name="id" value="<%= todoItem.getId() %>">
                                    <button type="submit" class="complete-toggle" aria-label="<%= todoItem.isCompleted() ? "Mark pending" : "Mark complete" %>" title="<%= todoItem.isCompleted() ? "Mark pending" : "Mark complete" %>">
                                        <i data-lucide="<%= todoItem.isCompleted() ? "circle-check" : "circle" %>"></i>
                                    </button>
                                </form>
                                <form action="todo/delete" method="post" class="task-action-form" data-action="delete">
                                    <input type="hidden" name="id" value="<%= todoItem.getId() %>">
                                    <button type="submit" class="ghost-action danger-button" aria-label="Delete todo" title="Delete todo">
                                        <i data-lucide="trash-2"></i>
                                    </button>
                                </form>
                            </div>
                        </article>
                    <% } %>
                </div>
                <div class="empty-state filtered-empty" id="filteredEmpty" hidden>
                    <i data-lucide="search-x"></i>
                    <strong>No matching tasks.</strong>
                    <span>Try a different title or description.</span>
                </div>
                <p class="autosave-status" id="autosaveStatus" role="status" aria-live="polite">Drag order auto-saves.</p>
            <% } %>
        </section>

        <section class="composer-panel" aria-label="Create a new todo">
            <div>
                <p class="eyebrow">Command</p>
                <h2>Quick add</h2>
            </div>
            <form action="todo/add" method="post" class="composer-form">
                <label>Title
                    <input type="text" name="title" maxlength="120" placeholder="Example: Submit workshop practical" required>
                </label>
                <label>Description
                    <textarea name="description" rows="1" placeholder="Add details, links, or notes"></textarea>
                </label>
                <label>Due Date
                    <input type="date" name="dueDate">
                </label>
                <button type="submit" class="add-button">
                    <i data-lucide="plus"></i>
                    <span>Add Todo</span>
                </button>
            </form>
        </section>
        <div class="status-toast" id="statusToast" role="status" aria-live="polite" hidden></div>
    </main>
</body>
</html>