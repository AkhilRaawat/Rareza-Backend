# Rareza – Architecture & Design Decisions (Phase-1)

---

## 1. Project Overview

Rareza is a **production-grade Phase-1 MVP backend** for a customizable T-shirt startup.

The backend is implemented using **Java and Spring Boot** and follows a **modular monolith architecture**.  
Phase-1 is intentionally scoped to validate the **order intake and admin approval workflow** before introducing authentication, payments, or scale-related complexity.

Primary goals of Phase-1:
- Correctness over feature breadth
- Clear domain boundaries
- Safe, incremental evolution

---

## 2. Architectural Style

- Modular monolith
- Feature-based package structure
- Single PostgreSQL database
- REST-based synchronous APIs
- Explicit separation of command (write) and query (read) responsibilities

The system deliberately avoids premature microservices, asynchronous workflows, or distributed patterns.

---

## 3. High-Level Workflow (Phase-1)

Customer
│
│ Create Order
▼
Order Service
│
│ Status = CREATED
▼
Admin Dashboard
│
├── Approve → ADMIN_APPROVED
└── Reject → ADMIN_REJECTED (with reason)


There is **no customer-facing order tracking** in Phase-1.

---

## 4. Package Structure

com.rareza
├── admin
│ ├── AdminOrderController
│ ├── AdminOrderService
│ ├── dto
│
├── order
│ ├── Order
│ ├── OrderRepository
│ ├── OrderService
│ ├── dto
│
├── infrastructure
│ └── storage
│ └── UploadedImage
│
├── common
│ ├── enums
│ ├── exception
│
└── config

Each feature owns:
- its controllers
- its services
- its DTOs

Entities are **never exposed directly** via APIs.

---

## 5. Core Domain Model

### 5.1 Order (Aggregate Root)

The `Order` aggregate represents a **customer’s design request**, not a finalized purchase.

Key characteristics:
- Stores a snapshot of customer data (no customer login in Phase-1)
- Exactly one design source is enforced:
  - predefined `Design`
  - OR user-uploaded `UploadedImage`
- Product reference is intentionally nullable (Phase-1 decision)
- Order price is immutable once created
- All state transitions are enforced in the service layer

---

### 5.2 UploadedImage

Represents metadata for externally hosted images (e.g. Cloudinary / S3).

Responsibilities:
- Track uploaded design images
- Support temporary uploads
- Enable cleanup of unused images
- Prevent abuse and uncontrolled storage growth

Binary image data is **not** stored in the database.

---

## 6. Order State Management

### OrderStatus (Phase-1)

- `CREATED`
- `ADMIN_APPROVED`
- `ADMIN_REJECTED`

### State Rules
- Only `CREATED` orders can be approved or rejected
- Invalid transitions throw domain exceptions
- PostgreSQL CHECK constraints enforce enum validity
- Business rules live in services, not controllers

---

## 7. Command vs Query Separation

### Commands (Write Operations)
- Create order
- Approve order
- Reject order

Characteristics:
- Transactional
- Enforce domain invariants
- Do not return entities

### Queries (Read Operations)
- List pending orders (`CREATED`)
- View order details (admin-only)

Characteristics:
- Read-only
- Return projection DTOs
- Never mutate state

This separation keeps the system predictable and refactor-safe.

---

## 8. Auditing Strategy

All persistent entities extend a shared `AuditableEntity` providing:
- UUID identifiers
- `createdAt` timestamp
- `updatedAt` timestamp

Auditing is enabled via Spring Data JPA and applied consistently.

---

## 9. Security (Phase-1)

- Spring Security is enabled
- All endpoints are currently `permit-all`
- Admin APIs are grouped under `/admin/**`

Authentication and authorization are **explicitly deferred** to Phase-2.

---

## 10. Persistence Strategy

- PostgreSQL (local / development)
- Hibernate with `ddl-auto=update`
- Enum values enforced via database CHECK constraints
- Manual or scripted data seeding for testing

Flyway migrations are intentionally not introduced in Phase-1.

---

## 11. Explicit Phase-1 Constraints

The following are **intentionally NOT implemented**:

- Customer authentication
- Admin JWT / RBAC
- Payments (Razorpay)
- Customer order tracking APIs
- Product catalog & variants
- Notifications (email / SMS)
- Pagination and search
- Asynchronous or event-driven workflows

These are postponed to avoid premature coupling.

---

## 12. Future Evolution (Phase-2+)

Planned future extensions include:
- Admin authentication (JWT)
- Payment integration after admin approval
- Secure customer order visibility
- Product catalog expansion
- Flyway migrations
- Operational hardening and scalability improvements

Phase-1 is designed so these can be added **without breaking existing APIs**.

---

## 13. Architectural Philosophy

> Build only what the current phase demands,  
> but enforce rules so future phases are not blocked.

The backend prioritizes:
- clarity over cleverness
- correctness over speed
- explicit decisions over assumptions

---

## 14. Phase-1 Status

✔ Feature complete  
✔ APIs tested end-to-end  
✔ Stable local persistence  
✔ Ready for Phase-2 planning  

---
