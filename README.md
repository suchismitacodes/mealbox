<p align="center">

<h1 align="center">🍱 Mealbox</h1>

<p align="center">
  <strong>A full-stack online food ordering platform built with Spring Boot & Thymeleaf</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?logo=openjdk" alt="Java 17" />
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen?logo=springboot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/MySQL-8.3-blue?logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/Thymeleaf-3-green?logo=thymeleaf" alt="Thymeleaf" />
  <img src="https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker&logoColor=white" alt="Docker" />
</p>

---

## 📖 About

**Mealbox** is a server-rendered web application that lets customers browse a food menu, search for dishes, place orders, and view their order history — while giving administrators a full dashboard to manage users, admins, products, and orders.

Built as a monolithic Spring Boot MVC application with Thymeleaf templates, Spring Data JPA for persistence, and MySQL as the database.

---

## ✨ Features

### Customer Portal
- **Browse Menu** — View a curated catalog of dishes across categories (Biryani, Paneer, Chicken, North Indian, Chinese, Vegetable)
- **Search Products** — Search for specific dishes by name
- **Place Orders** — Select quantity and place orders with automatic total calculation
- **Order History** — View all past orders with product details, quantities, and dates

### Admin Dashboard
- **Admin Management** — Add, update, and delete administrator accounts
- **User Management** — Full CRUD operations on customer accounts
- **Product Management** — Add, update, and remove menu items (name, price, description)
- **Order Overview** — View all orders across all customers with user details

### General
- **Dual Login System** — Separate login panels for admins and customers
- **Global Error Handling** — Graceful error page for unhandled exceptions
- **Responsive UI** — Custom-styled pages with Font Awesome icons

---

## 🛠️ Tech Stack

| Layer           | Technology                                      |
|-----------------|------------------------------------------------|
| **Language**    | Java 17                                         |
| **Framework**   | Spring Boot 4.0.3                               |
| **Build Tool**  | Maven                                           |
| **View Engine** | Thymeleaf + Thymeleaf Extras (Spring Security 6)|
| **ORM**         | Spring Data JPA (Hibernate)                     |
| **Database**    | MySQL 8.3                                       |
| **Security**    | Spring Security (BCrypt encoder configured)     |
| **Validation**  | Jakarta Bean Validation                         |
| **Utilities**   | Lombok                                          |
| **Styling**     | Custom CSS per page, Font Awesome 6.4.2 (CDN)   |
| **Containers**  | Docker Compose (MySQL service)                  |

---

## 📁 Project Structure

```
mealbox/
├── docker-compose.yml                 # MySQL container setup
├── pom.xml                            # Maven dependencies & build config
├── src/
│   ├── main/
│   │   ├── java/com/suchismitacodes/mealbox/
│   │   │   ├── MealboxApplication.java        # Application entry point
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java        # BCrypt password encoder bean
│   │   │   ├── controller/
│   │   │   │   ├── HomeController.java        # Public pages (home, products, login)
│   │   │   │   ├── AdminController.java       # Admin/user login + admin CRUD + orders
│   │   │   │   ├── UserController.java        # User CRUD operations
│   │   │   │   ├── ProductController.java     # Product CRUD operations
│   │   │   │   └── OrderController.java       # Order endpoints
│   │   │   ├── dto/
│   │   │   │   ├── AdminLogin.java            # Admin login form DTO
│   │   │   │   └── UserLogin.java             # User login form DTO
│   │   │   ├── entity/
│   │   │   │   ├── Admin.java                 # Admin JPA entity
│   │   │   │   ├── User.java                  # User entity (has orders)
│   │   │   │   ├── Product.java               # Product/menu item entity
│   │   │   │   └── Orders.java                # Order entity (linked to user)
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java # @ControllerAdvice error handler
│   │   │   ├── repository/
│   │   │   │   ├── AdminRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── ProductRepository.java
│   │   │   │   └── OrderRepository.java
│   │   │   ├── service/
│   │   │   │   ├── AdminService.java
│   │   │   │   ├── UserService.java
│   │   │   │   ├── ProductService.java
│   │   │   │   └── OrderService.java
│   │   │   └── util/
│   │   │       └── Logic.java                 # Order total calculation utility
│   │   └── resources/
│   │       ├── application.properties         # DB connection & JPA config
│   │       ├── static/
│   │       │   ├── css/                       # Per-page stylesheets
│   │       │   ├── Images/                    # Food images by category
│   │       │   │   ├── biryani/
│   │       │   │   ├── chicken/
│   │       │   │   ├── chinese/
│   │       │   │   ├── north-india-food/
│   │       │   │   ├── paneer/
│   │       │   │   └── vegetable/
│   │       │   └── js/                        # JavaScript files
│   │       └── templates/                     # Thymeleaf HTML templates
│   │           ├── Home.html
│   │           ├── Login.html
│   │           ├── Products.html
│   │           ├── BuyProduct.html
│   │           ├── Admin_Page.html
│   │           ├── Order_success.html
│   │           └── ...                        # 17 templates total
│   └── test/                                  # Unit tests
└── target/                                    # Compiled output
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher — [Download](https://adoptium.net/)
- **Maven 3.8+** — [Download](https://maven.apache.org/download.cgi) (or use the included `mvnw` wrapper)
- **MySQL 8.x** — via Docker (recommended) or local installation
- **Docker & Docker Compose** *(optional, for database)*

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/mealbox.git
cd mealbox
```

### 2. Start the Database

**Option A: Using Docker Compose (Recommended)**

```bash
docker-compose up -d
```

This starts a MySQL 8.3 container with:
- **Database:** `mealbox`
- **User:** `mealboxuser` / **Password:** `mealboxpass`
- **Port:** `3306`

**Option B: Using a Local MySQL Installation**

Create a database and user manually:

```sql
CREATE DATABASE IF NOT EXISTS mealbox;
CREATE USER 'mealboxuser'@'localhost' IDENTIFIED BY 'mealboxpass';
GRANT ALL PRIVILEGES ON mealbox.* TO 'mealboxuser'@'localhost';
FLUSH PRIVILEGES;
```

> If using different credentials, update `src/main/resources/application.properties` accordingly.

### 3. Build & Run

```bash
# Using Maven wrapper (no Maven installation needed)
./mvnw spring-boot:run

# Or on Windows
mvnw.cmd spring-boot:run

# Or with Maven installed
mvn spring-boot:run
```

### 4. Access the Application

Open your browser and navigate to:

| Page            | URL                              |
|-----------------|----------------------------------|
| **Home**        | http://localhost:8080/            |
| **Products**    | http://localhost:8080/products    |
| **Login**       | http://localhost:8080/login       |
| **Locate Us**   | http://localhost:8080/location    |
| **About**       | http://localhost:8080/about       |

---

## 🗄️ Database Schema

Hibernate auto-generates the following tables from JPA entities (`ddl-auto=update`):

### `admin`
| Column         | Type    | Notes              |
|----------------|---------|---------------------|
| admin_id       | INT     | Primary Key, Auto-increment |
| admin_name     | VARCHAR | Not null            |
| admin_email    | VARCHAR | Not null, validated |
| admin_password | VARCHAR |                     |
| admin_number   | VARCHAR |                     |

### `user`
| Column     | Type    | Notes              |
|------------|---------|---------------------|
| u_id       | INT     | Primary Key, Auto-increment |
| uname      | VARCHAR |                     |
| uemail     | VARCHAR |                     |
| upassword  | VARCHAR |                     |
| unumber    | BIGINT  |                     |

### `product_table`
| Column        | Type    | Notes              |
|---------------|---------|---------------------|
| pid           | INT     | Primary Key, Auto-increment |
| pname         | VARCHAR |                     |
| pprice        | DOUBLE  |                     |
| pdescription  | VARCHAR |                     |

### `orders`
| Column        | Type    | Notes                      |
|---------------|---------|----------------------------|
| o_id          | INT     | Primary Key, Auto-generated |
| o_name        | VARCHAR | Product name snapshot       |
| o_price       | DOUBLE  | Product price snapshot      |
| o_quantity    | INT     |                             |
| total_ammout  | DOUBLE  | Calculated: price × quantity|
| order_date    | DATE    | Set at order time           |
| user_u_id     | INT     | Foreign Key → `user.u_id`  |

**Relationships:**
- `User` ↔ `Orders`: One-to-Many (a user can have many orders)
- Orders store a snapshot of product name and price at the time of ordering (no FK to product)

---

## 🔄 Application Flow

### Customer Journey

```
┌─────────────┐     ┌──────────────┐     ┌───────────────┐
│  Home Page  │────▶│ Products Page│────▶│  Login Page   │
│   (/ )      │     │ (/products)  │     │  (/login)     │
└─────────────┘     └──────────────┘     └───────┬───────┘
                                                  │
                                          User Login (POST)
                                                  │
                                         ┌────────▼────────┐
                                         │   Buy Product    │
                                         │  (search + order)│
                                         └────────┬────────┘
                                                  │
                                          Place Order (POST)
                                                  │
                                         ┌────────▼────────┐
                                         │ Order Success    │
                                         │  (confirmation)  │
                                         └─────────────────┘
```

### Admin Journey

```
┌──────────────┐     ┌──────────────────┐
│  Login Page  │────▶│  Admin Dashboard  │
│  (/login)    │     │ (/admin/services) │
└──────────────┘     └────────┬─────────┘
                              │
              ┌───────────────┼───────────────┐
              │               │               │
       ┌──────▼──────┐ ┌─────▼──────┐ ┌──────▼──────┐
       │Manage Admins│ │Manage Users│ │Manage Prods │
       │ (CRUD)      │ │  (CRUD)    │ │   (CRUD)    │
       └─────────────┘ └────────────┘ └─────────────┘
```

---

## 🧪 Running Tests

```bash
# Run all tests
./mvnw test

# On Windows
mvnw.cmd test
```

---

## 🐳 Docker

The included `docker-compose.yml` provides a MySQL 8.3 service with persistent storage:

```bash
# Start the database
docker-compose up -d

# Stop the database
docker-compose down

# Stop and remove data volume
docker-compose down -v
```

---

## 📄 API Routes Reference

### Public Routes

| Method | Endpoint       | Description          |
|--------|---------------|----------------------|
| GET    | `/` `/home`   | Home page            |
| GET    | `/products`   | Products catalog     |
| GET    | `/location`   | Locate Us page       |
| GET    | `/about`      | About page           |
| GET    | `/login`      | Login page           |

### Authentication

| Method | Endpoint       | Description          |
|--------|---------------|----------------------|
| POST   | `/adminLogin`  | Admin login          |
| POST   | `/userLogin`   | Customer login       |

### Admin Operations

| Method | Endpoint                    | Description              |
|--------|----------------------------|--------------------------|
| GET    | `/admin/services`          | Admin dashboard          |
| GET    | `/addAdmin`                | Add admin form           |
| POST   | `/addingAdmin`             | Save new admin           |
| GET    | `/updateAdmin/{id}`        | Edit admin form          |
| GET    | `/updatingAdmin/{id}`      | Save admin update        |
| GET    | `/deleteAdmin/{id}`        | Delete admin             |
| GET    | `/addUser`                 | Add user form            |
| POST   | `/addingUser`              | Save new user            |
| GET    | `/updateUser/{id}`         | Edit user form           |
| GET    | `/updatingUser/{id}`       | Save user update         |
| GET    | `/deleteUser/{id}`         | Delete user              |
| GET    | `/addProduct`              | Add product form         |
| POST   | `/addingProduct`           | Save new product         |
| GET    | `/updateProduct/{id}`      | Edit product form        |
| GET    | `/updatingProduct/{id}`    | Save product update      |
| GET    | `/deleteProduct/{id}`      | Delete product           |

### Customer Operations

| Method | Endpoint           | Description                  |
|--------|--------------------|------------------------------|
| POST   | `/product/search`  | Search for a product by name |
| POST   | `/product/order`   | Place an order               |
| GET    | `/product/back`    | Return to product page       |

---

## 🤝 Contributing

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

---

## 📝 License

This project is open source and available for educational and personal use.

---

## 👩‍💻 Author

**Suchismita** — [@suchismitacodes](https://github.com/suchismitacodes)

---

<p align="center">
  Made with ❤️ using Spring Boot & Thymeleaf
</p>
