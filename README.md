# 个人行程管理系统 (Personal Trip Management System)

这是一个基于 Java Spring Boot 和原生 JavaScript 构建的全栈Web应用程序。它提供了一个简单而实用的平台，允许用户注册、登录、管理自己的旅行计划，并查看相关的统计信息。

---

## ✨ 主要功能

-   **用户管理**:
    -   👤 用户注册与登录
    -   📄 查看和修改个人信息（姓名、邮箱、电话）
    -   🔐 修改密码

-   **行程管理 (CRUD)**:
    -   ➕ **创建 (Create)**: 添加新的行程计划，包括目的地、起止日期和描述。
    -   📄 **读取 (Read)**: 查看个人所有行程的列表。
    -   ✏️ **更新 (Update)**: 编辑已存在的行程信息。
    -   🗑️ **删除 (Delete)**: 删除不再需要的行程。
    -   🔍 **搜索**: 根据目的地关键词快速筛选行程。

-   **数据统计**:
    -   📊 在个人中心页面，自动生成行程统计报告，包括：
        -   总行程数量
        -   平均行程时长
        -   最长的行程
        -   最常访问的目的地

---

## 🛠️ 技术栈

-   **后端 (Backend)**:
    -   Java
    -   Spring Boot
    -   Spring Web (for RESTful APIs)
    -   Spring Data JPA (for database interaction)
    -   Hibernate
    -   Maven (for dependency management)

-   **前端 (Frontend)**:
    -   HTML5
    -   CSS3
    -   JavaScript (Vanilla JS, ES6+)

-   **数据库 (Database)**:
    -   MySQL

---

## 🚀 快速开始

请按照以下步骤在本地运行此项目。

### 1. 先决条件

-   Java Development Kit (JDK) 8 或更高版本
-   Apache Maven
-   MySQL 数据库

### 2. 后端设置

1.  **克隆或下载项目**

2.  **创建数据库**:
    在您的 MySQL 服务器中创建一个新的数据库。
    ```sql
    CREATE DATABASE traveldb;
    ```

3.  **配置数据库连接**:
    打开 `src/main/resources/application.properties` 文件，根据您的 MySQL 配置修改以下行：
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/traveldb
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    ```
    *注意：请将端口 `3306`、`your_mysql_username` 和 `your_mysql_password` 替换为您的实际配置。*

4.  **运行后端服务**:
    在项目根目录下打开终端，运行以下命令：
    ```bash
    mvn spring-boot:run
    ```
    或者，直接在您的IDE（如IntelliJ IDEA或Eclipse）中运行 `Application.java` 的 `main` 方法。

    后端服务将在 `http://localhost:8080` 上启动。

### 3. 前端设置

1.  **确保后端服务正在运行**。

2.  **打开前端页面**:
    直接在浏览器中打开 `index.html` 文件。由于后端已经配置了CORS (`@CrossOrigin`)，这通常可以正常工作。

    **推荐方式**: 为了获得最佳体验并避免潜在的浏览器安全限制，建议使用一个简单的本地Web服务器来托管前端文件。如果您使用VS Code，可以安装 "Live Server" 扩展，右键点击 `index.html` 并选择 "Open with Live Server"。

---

## 📁 项目结构

```
.
├── pom.xml                   # Maven 配置文件
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── Application.java        # Spring Boot 启动类
│   │   │           ├── controller              # API 控制器 (UserController, TripController)
│   │   │           ├── model                   # 数据实体 (User, Trip)
│   │   │           └── repository              # 数据访问层 (UserRepository, TripRepository)
│   │   └── resources
│   │       └── application.properties # Spring Boot 配置文件
├── index.html                # 登录/注册页面
├── trips.html                # 行程管理页面
└── personal_center.html      # 个人中心页面
```

---

## 🌐 API 端点

### 用户 API (`/api/user`)

| 方法   | 路径                  | 描述             |
| :----- | :-------------------- | :--------------- |
| `POST` | `/register`           | 注册新用户       |
| `POST` | `/login`              | 用户登录         |
| `GET`  | `/info`               | 获取用户信息     |
| `PUT`  | `/update`             | 更新用户信息     |
| `PUT`  | `/changePassword`     | 修改密码         |

### 行程 API (`/api/trip`)

| 方法      | 路径                     | 描述               |
| :-------- | :----------------------- | :----------------- |
| `POST`    | `/add`                   | 添加新行程         |
| `PUT`     | `/update/{id}`           | 更新指定ID的行程   |
| `DELETE`  | `/delete/{id}`           | 删除指定ID的行程   |
| `GET`     | `/list`                  | 获取用户的所有行程 |
| `GET`     | `/{tripId}`              | 获取单个行程信息   |
| `GET`     | `/search/{username}`     | 根据目的地搜索行程 |
| `GET`     | `/statistics`            | 获取行程统计信息   |

---

## 💡 未来改进方向

-   **安全性增强**:
    -   使用 Spring Security 对密码进行哈希加密（如 BCrypt）。
    -   为API端点添加基于JWT (JSON Web Token) 的认证和授权。
-   **代码重构**:
    -   在后端引入Service层，将业务逻辑从Controller中分离出来。
-   **数据库优化**:
    -   在 `Trip` 和 `User` 实体之间建立 `@ManyToOne` 的外键关联，而不是直接存储 `username` 字符串。
-   **前端升级**:
    -   迁移到现代前端框架（如 Vue.js, React）以提高开发效率和可维护性。
-   **错误处理**:
    -   在前端和后端实现更友好、更详细的错误提示。
