# Rareza – Architecture & Design Decisions

## 1. Project Overview
Rareza is a production-grade MVP backend for a customizable T-shirt startup.
The system is designed as a modular monolith using Java and Spring Boot.

Phase-1 focuses on:
- Single product type (T-shirt)
- Predefined and user-uploaded designs
- Admin approval workflow
- Secure image handling
- Razorpay-based payments

---

## 2. Architectural Style
- Modular monolith
- Feature-based package structure
- Single PostgreSQL database
- REST-based communication

The system prioritizes simplicity and correctness over premature scalability.

---

## 3. Core Domain Model

### 3.1 Order
- Represents a customer order
- Stores customer snapshot data (no user login in Phase-1)
- Enforces exactly one design source:
  - Predefined Design OR Uploaded Image
- Order price is immutable once created

### 3.2 Payment
- Separate aggregate from Order
- Integrates with Razorpay
- Stores gateway identifiers
- Amount is immutable
- Payment lifecycle enforced in service layer

### 3.3 Product
- Represents a catalog item
- Currently limited to T-shirts
- Designed to support future product types
- Soft-deletable using `isActive`

### 3.4 UploadedImage
- Stores metadata for externally hosted images
- Supports temporary uploads
- Includes abuse and cost control mechanisms
- Clean-up strategy for unused images

---

## 4. Auditing Strategy
All entities extend a common `AuditableEntity` which provides:
- UUID-based identity
- Created and last-modified timestamps
- Consistent lifecycle handling via Spring Data auditing

---

## 5. Phase-1 Constraints
- No user authentication for customers
- Admin-only authentication
- Single payment per order
- No inventory or variant modeling
- No search or pagination optimizations

These constraints are intentional and will be revisited in later phases.

---

## 6. Future Considerations
- User accounts and order history
- Multiple product types and variants
- Payment retries and refunds
- Search and catalog expansion
- Scalability optimizations

---

## 7. Milestones
- v0.1-domain-model: Core entities and persistence model stabilized
