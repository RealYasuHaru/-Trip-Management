行程管理
Travel Management System
项目简介
这是一个基于Spring Boot和Spring Data JPA的全栈Web应用程序，用于管理用户的行程信息。它支持用户注册、登录、添加行程、编辑行程、删除行程以及查看行程统计信息。
Project Introduction
This is a full-stack Web application built with Spring Boot and Spring Data JPA for managing user trips. It supports user registration, login, adding trips, editing trips, deleting trips, and viewing trip statistics.
功能特性
功能描述
用户管理
用户注册和登录功能。
用户可以查看和修改个人信息（姓名、邮箱、电话号码）。
用户可以修改密码。
行程管理
用户可以添加新的行程，包括目的地、开始日期、结束日期和描述。
用户可以编辑和删除已有的行程。
支持通过目的地搜索行程。
提供行程统计信息，包括总行程数、平均行程时长、最长行程和最常访问的目的地。
前端界面
使用HTML、CSS和JavaScript构建的简单前端页面。
包含登录/注册页面、行程管理页面和个人中心页面。
使用模态框（Modal）实现表单的弹出显示。
后端服务
提供RESTful API接口，支持用户和行程的CRUD操作。
使用Spring Data JPA进行数据库操作，结合MySQL存储数据。
提供行程统计功能，通过自定义SQL和Java逻辑实现。
Feature Description
User Management
User registration and login.
Users can view and update their personal information (full name, email, phone number).
Users can change their password.
Trip Management
Users can add new trips with destination, start date, end date, and description.
Users can edit and delete existing trips.
Support for searching trips by destination.
Provides trip statistics, including total trips, average trip duration, longest trip, and most visited destination.
Frontend Interface
Simple frontend pages built with HTML, CSS, and JavaScript.
Includes login/registration, trip management, and personal center pages.
Uses modals for form pop-ups.
Backend Service
Provides RESTful API endpoints for CRUD operations on users and trips.
Uses Spring Data JPA for database operations with MySQL.
Includes trip statistics functionality with custom SQL and Java logic.
项目模块
前端页面
index.html：登录和注册页面。
trips.html：行程管理页面。
personal_center.html：个人中心页面。
后端接口
UserController.java：处理用户相关的操作。
TripController.java：处理行程相关的操作。
数据模型
User.java：用户实体类。
Trip.java：行程实体类。
数据访问层
UserRepository.java：用户数据访问接口。
TripRepository.java：行程数据访问接口。
主程序入口
Application.java：Spring Boot应用程序的入口。
依赖管理
pom.xml：Maven配置文件，管理项目依赖。
Project Structure
Frontend Pages
index.html: Login and registration page.
trips.html: Trip management page.
personal_center.html: Personal center page.
Backend Controllers
UserController.java: Handles user-related operations.
TripController.java: Handles trip-related operations.
Data Models
User.java: User entity class.
Trip.java: Trip entity class.
Data Access Layer
UserRepository.java: User data access interface.
TripRepository.java: Trip data access interface.
Main Application Entry
Application.java: Entry point for the Spring Boot application.
Dependency Management
pom.xml: Maven configuration file for managing project dependencies.
技术栈
技术描述
前端
HTML、CSS、JavaScript
使用原生JavaScript进行DOM操作和API调用。
后端
Spring Boot
Spring Data JPA
Spring MVC
数据库
MySQL
开发工具
Maven：项目管理和构建工具
IntelliJ IDEA 或 Eclipse：Java开发环境
Technology Stack
Frontend
HTML, CSS, JavaScript
Native JavaScript for DOM manipulation and API calls.
Backend
Spring Boot
Spring Data JPA
Spring MVC
Database
MySQL
Development Tools
Maven: Project management and build tool.
IntelliJ IDEA or Eclipse: Java development environment.
运行项目
环境准备
安装Java Development Kit (JDK) 1.8或更高版本。
安装Maven。
安装MySQL数据库，并创建对应的数据库和用户。
配置application.properties文件，设置数据库连接信息。
运行步骤
启动MySQL数据库：
确保MySQL服务已启动，并创建一个名为travel的数据库。
构建项目：
在项目根目录下运行以下命令：
bash复制
mvn clean install
运行项目：
在项目根目录下运行以下命令：
bash复制
mvn spring-boot:run
或者直接在IDE中运行Application.java。
访问页面：
打开浏览器，访问以下地址：
登录/注册页面：http://localhost:8080
行程管理页面：http://localhost:8080/trips.html
个人中心页面：http://localhost:8080/personal_center.html
Environment Setup
Install Java Development Kit (JDK) version 1.8 or higher.
Install Maven.
Install MySQL database and create a database and user for the project.
Configure the application.properties file to set up the database connection.
Running the Project
Start MySQL Database:
Ensure the MySQL service is running and create a database named travel.
Build the Project:
Run the following command in the project root directory:
bash复制
mvn clean install
Run the Project:
Run the following command in the project root directory:
bash复制
mvn spring-boot:run
Alternatively, run Application.java directly from your IDE.
Access the Pages:
Open your browser and navigate to the following addresses:
Login/Registration Page: http://localhost:8080
Trip Management Page: http://localhost:8080/trips.html
Personal Center Page: http://localhost:8080/personal_center.html
数据库设计
数据库表结构
用户表（Users）
id：主键，自增。
username：用户名，唯一。
password：用户密码。
fullName：用户全名。
email：用户邮箱。
phone：用户电话号码。
行程表（Trips）
id：主键，自增。
destination：目的地。
startDate：开始日期。
endDate：结束日期。
description：行程描述。
username：所属用户，外键关联到用户表。
Database Design
Users Table
id: Primary key, auto-increment.
username: Unique username.
password: User password.
fullName: Full name of the user.
email: User email.
phone: User phone number.
Trips Table
id: Primary key, auto-increment.
destination: Trip destination.
startDate: Start date of the trip.
endDate: End date of the trip.
description: Trip description.
username: Foreign key linking to the Users table.
API接口文档
用户接口
方法URL描述POST/api/user/register用户注册POST/api/user/login用户登录GET/api/user/info获取用户信息PUT/api/user/changePassword修改用户密码PUT/api/user/update更新用户信息
Trip Interfaces
MethodURLDescriptionPOST/api/user/registerUser registrationPOST/api/user/loginUser loginGET/api/user/infoGet user informationPUT/api/user/changePasswordChange user passwordPUT/api/user/updateUpdate user information
行程接口
方法URL描述POST/api/trip/add添加行程PUT/api/trip/update/{id}更新行程GET/api/trip/list获取用户所有行程GET/api/trip/statistics获取行程统计信息DELETE/api/trip/delete/{id}删除行程
Trip Interfaces
MethodURLDescriptionPOST/api/trip/addAdd a new tripPUT/api/trip/update/{id}Update an existing tripGET/api/trip/listGet all trips for a userGET/api/trip/statisticsGet trip statisticsDELETE/api/trip/delete/{id}Delete a trip
改进建议
安全性增强：
使用BCrypt对密码进行加密存储。
引入JWT或OAuth2进行用户认证和授权。
前端优化：
使用现代前端框架（如React、Vue或Angular）提升用户体验。
使用CSS框架（如Bootstrap）优化页面样式。
API文档：
使用Swagger生成API文档，方便开发和维护。
数据校验：
在后端增加更严格的数据校验逻辑，确保数据的完整性和合法性。
国际化支持：
支持多语言界面，提升用户体验。
Improvement Suggestions
Enhanced Security:
Use BCrypt to encrypt passwords.
Introduce JWT or OAuth2 for user authentication and authorization.
Frontend Optimization:
Use modern frontend frameworks (React, Vue, Angular) to improve user experience.
Use CSS frameworks (Bootstrap) to optimize page styles.
API Documentation:
Use Swagger to generate API documentation for easier development and maintenance.
Data Validation:
Add stricter data validation logic in the backend to ensure data integrity.
Internationalization:
Support multiple languages for the user interface.

版权说明
本项目代码版权归作者所有。未经授权，禁止用于商业用途。
Copyright Statement
The code for this project is copyrighted by the author. Unauthorized commercial use is prohibited.
