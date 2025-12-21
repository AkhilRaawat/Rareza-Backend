# Rareza Backend – Phase 1 MVP

Rareza is a customizable T-shirt startup.  
This repository contains the **Phase-1 backend MVP**, focused on order intake and admin approval workflows.

The goal of Phase-1 is to validate the **design approval flow** before introducing payments, authentication, or scale-related concerns.

---

## 🧩 Tech Stack

- **Language**: Java 17
- **Framework**: Spring Boot
- **Architecture**: Modular monolith (feature-based packages)
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA (Hibernate)
- **API Style**: REST (Spring MVC)
- **Security**: Spring Security (permit-all for Phase-1)

---

## 🎯 Phase-1 Scope (Implemented)

### Customer Flow
- Create an order for a **single fixed T-shirt product**
- Choose:
  - predefined design **OR**
  - upload an image (stored separately)
- Order is created with status `CREATED`
- No customer login
- No order status lookup for customers

### Admin Flow
- View list of **new orders pending approval**
- View full order details (read-only)
- Approve an order
- Reject an order with a mandatory rejection reason
- Strict order state transitions enforced

---

## 🔄 Order Lifecycle (Phase-1)

CREATED
│
├── ADMIN_APPROVED
│
└── ADMIN_REJECTED (with rejection reason)


Invalid transitions are blocked at the service layer and database layer.

---

## 🗂️ API Overview

### Customer API
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/orders` | Create a new order |

### Admin APIs
| Method | Endpoint | Description |
|------|---------|------------|
| GET | `/admin/orders` | List pending orders (CREATED) |
| GET | `/admin/orders/{orderId}` | View order details |
| POST | `/admin/orders/{orderId}/approve` | Approve order |
| POST | `/admin/orders/{orderId}/reject` | Reject order with reason |

---

## 🚫 Explicitly Not Implemented (Phase-1)

These are intentionally deferred:

- Authentication / JWT
- Payments (Razorpay)
- Customer order tracking APIs
- Notifications (email / SMS)
- Product catalog expansion
- Database migrations (Flyway)

---

## 🧠 Design Principles

- Commands and queries are clearly separated
- Controllers are thin
- Business rules live in services
- Entities are never exposed directly
- Scope is intentionally constrained

---

## 📌 Phase-1 Status

✔ Feature complete  
✔ APIs tested end-to-end  
✔ Stable local persistence  

Phase-1 backend is **ready for demo and further iteration**.

