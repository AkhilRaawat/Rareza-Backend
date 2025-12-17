# Rareza Backend

Backend service for **Rareza**, a customizable apparel startup MVP.

This project is a **production-oriented modular monolith** built with Java and Spring Boot.  
It focuses on correctness, clean domain modeling, and future extensibility rather than premature optimization.

---

## Overview

Rareza enables customers to order customized apparel (starting with T-shirts) using either predefined designs or user-uploaded images.  
Orders go through an **admin approval workflow** before printing and fulfillment.

Phase-1 is intentionally scoped to validate the business and technical foundation.

---

## Tech Stack

- **Language:** Java 17  
- **Framework:** Spring Boot  
- **Persistence:** Spring Data JPA, PostgreSQL  
- **Architecture:** Modular Monolith (feature-based packages)  
- **Payments:** Razorpay  
- **Image Storage:** External storage (S3 / Cloudinary)  
- **Security:** Admin-only authentication using JWT  

---

## Current Status

✔ Core domain entities finalized  
✔ Persistence model stabilized  
✔ Auditing and validation implemented  
✔ Phase-1 data model locked  

At this stage, the project focuses on **domain correctness and architectural clarity**.  
Service logic, APIs, and workflows will be built on top of this foundation.

Detailed design decisions are documented in **`ARCHITECTURE.md`**.

---

## Domain Model (Phase-1)

- **Product**
  - Catalog representation (currently T-shirts only)
  - Designed to support future product types
  - Soft-deletable

- **Order**
  - Stores customer snapshot data (no customer login in Phase-1)
  - Exactly one design source:
    - Predefined design **or**
    - User-uploaded image
  - Immutable order price

- **Payment**
  - Separate aggregate from Order
  - Razorpay integration
  - Immutable payment amount
  - Explicit payment lifecycle

- **UploadedImage**
  - Secure metadata storage for externally hosted images
  - Temporary uploads supported
  - Abuse and cost-control mechanisms in place

---

## Project Structure

The codebase follows a **feature-based package structure**, not a traditional layer-based one.

