# 🧱 데이터베이스 모델링

# 1. customers

   | 필드명          | 타입       | 제약조건                         | 설명              |
   | --------------- | ---------- | -------------------------------- | ----------------- |
   | `id`            | BIGINT     | PK, AUTO_INCREMENT               | 고객 고유 ID       |
   | `point`         | INT        | DEFAULT 0                        | 고객 보유 포인트   |
   | `name`          | STRING     | NOT NULL                         | 고객 이름          |
   | `phone_number`  | STRING     | NOT NULL                         | 고객 전화번호      |
   | `address1`      | STRING     | NOT NULL                         | 주소 (기본)        |
   | `address2`      | STRING     | NULLABLE                         | 주소 (상세)        |
   | `email`         | STRING     | NOT NULL, UNIQUE                 | 이메일             |
   | `birth_date`    | DATE       | NULLABLE                         | 생년월일          |
   | `created_at`    | DATETIME   | DEFAULT CURRENT_TIMESTAMP        | 생성 시각         |
   | `updated_at`    | DATETIME   | ON UPDATE CURRENT_TIMESTAMP      | 갱신 시각         |

# 2. products

   | 필드명                 | 타입       | 제약조건                         | 설명              |
   | ---------------------- | ---------- | -------------------------------- | ----------------- |
   | `id`                  | BIGINT     | PK, AUTO_INCREMENT               | 상품 고유 ID       |
   | `product_name`         | STRING     | NOT NULL                         | 상품명            |
   | `product_description`  | STRING     | NULLABLE                         | 상품 설명         |
   | `product_image_url`    | STRING     | NULLABLE                         | 상품 이미지 URL    |
   | `product_category`     | STRING     | NULLABLE                         | 상품 카테고리      |
   | `product_price`        | INT        | NOT NULL                         | 상품 가격         |
   | `product_stock`        | INT        | DEFAULT 0                        | 상품 재고         |
   | `product_is_active`    | BOOLEAN    | DEFAULT TRUE                     | 상품 활성화 여부   |
   | `product_created_at`   | DATETIME   | DEFAULT CURRENT_TIMESTAMP        | 상품 등록 시각    |
   | `product_updated_at`   | DATETIME   | ON UPDATE CURRENT_TIMESTAMP      | 상품 정보 갱신 시각 |

# 3. orders

   | 필드명              | 타입       | 제약조건                         | 설명              |
   | ------------------- | ---------- | -------------------------------- | ----------------- |
   | `id`               | BIGINT     | PK, AUTO_INCREMENT               | 주문 고유 ID       |
   | `customer_id`       | BIGINT     | FK (`customers.id`)              | 고객 ID           |
   | `product_id`        | BIGINT     | FK (`products.id`)               | 상품 ID           |
   | `product_price`     | INT        | NOT NULL                         | 상품 가격         |
   | `quantity`          | INT        | NOT NULL                         | 주문 수량         |
   | `total_price`       | INT        | NOT NULL                         | 총 주문 금액      |
   | `is_paid`           | BOOLEAN    | DEFAULT FALSE                    | 결제 여부          |
   | `order_date`        | DATETIME   | DEFAULT CURRENT_TIMESTAMP        | 주문 날짜         |
   | `status`            | STRING     | DEFAULT 'pending'                | 주문 상태         |
   | `delivery_address`  | STRING     | NOT NULL                         | 배송지 주소       |
   | `payment_method`    | STRING     | NOT NULL                         | 결제 방법         |
   | `delivery_date`     | DATETIME   | NULLABLE                         | 배송 날짜         |
   | `created_at`        | DATETIME   | DEFAULT CURRENT_TIMESTAMP        | 생성 시각         |
   | `updated_at`        | DATETIME   | ON UPDATE CURRENT_TIMESTAMP      | 갱신 시각         |

# 4. coupons

| 필드명              | 타입       | 제약조건                         | 설명              |
| ------------------- | ---------- | -------------------------------- | ----------------- |
| `id`               | BIGINT     | PK, AUTO_INCREMENT               | 쿠폰 고유 ID       |
| `coupon_price`      | INT        | NULLABLE                         | 할인 금액         |
| `coupon_discount_rate` | INT    | DEFAULT 0                        | 할인율            |
| `issued_count`      | INT        | DEFAULT 0                        | 발행된 횟수       |
| `quota`             | INT        | NOT NULL                         | 발행 가능 수량    |
| `coupon_name`       | STRING     | NOT NULL                         | 쿠폰 이름         |
| `coupon_description`| STRING     | NULLABLE                         | 쿠폰 설명         |
| `coupon_status`     | STRING     | DEFAULT 'active'                 | 쿠폰 상태         |
| `coupon_type`       | STRING     | NOT NULL                         | 쿠폰 유형         |
| `coupon_start_date` | DATE       | NOT NULL                         | 쿠폰 시작 날짜    |
| `coupon_end_date`   | DATE       | NOT NULL                         | 쿠폰 종료 날짜    |

# 5. points

| 필드명              | 타입       | 제약조건                         | 설명              |
| ------------------- | ---------- | -------------------------------- | ----------------- |
| `id`               | BIGINT     | PK, AUTO_INCREMENT               | 포인트 기록 ID     |
| `customer_id`       | BIGINT     | FK (`customers.id`)              | 고객 ID           |
| `point`             | INT        | NOT NULL                         | 변경되는 포인트 값 |
| `type`              | ENUM       | (e.g., add, subtract)            | 포인트 유형        |
| `update_millis`     | BIGINT     | NOT NULL                         | 업데이트 밀리초    |


# 6. CustomerPointHistory

| 필드명          | 타입            | 설명              |
| --------------- | --------------- | ----------------- |
| `id`            | long            | 포인트 기록 ID    |
| `customerId`    | long            | 고객 ID           |
| `point`         | int             | 포인트 변화량     |
| `type`          | TransactionType | 트랜잭션 유형     |
| `updateMillis`  | long            | 업데이트 밀리초   |

# 7. Payments

| 필드명          | 타입    | 설명               |
| --------------- | ------- | ------------------ |
| `id`            | Long    | 결제 고유 ID       |
| `userId`        | Long    | 사용자 ID          |
| `productId`     | Long    | 상품 ID            |
| `orderId`       | Long    | 주문 ID            |
| `paymentPrice`  | int     | 결제 금액          |
| `paymentMethod` | String  | 결제 방법 ("P")    |
| `paymentStatus` | String  | 결제 상태          |
| `paymentDate`   | String  | 결제 날짜          |
| `paymentAmount` | String  | 결제된 금액        |
| `paymentCreatedAt` | String | 결제 생성 시각   |
| `paymentUpdatedAt` | String | 결제 갱신 시각   |

# 8. UserCoupons 

| 필드명          | 타입      | 설명               |
| --------------- | --------- | ------------------ |
| `id`            | Long      | 사용자 쿠폰 ID     |
| `customers`     | Customers | 고객 객체          |
| `coupon`        | Coupons   | 쿠폰 객체          |
| `isUsed`        | boolean   | 사용 여부          |
| `issueDate`     | Date      | 발급 날짜          |
| `usageDate`     | Date      | 사용 날짜          |
| `expiryDate`    | Date      | 만료 날짜          |

---

## 🔗 테이블 관계도 (관계 설명)

```mermaid

classDiagram
    class Customers {
        -Long id
        -int point          
        -String name
        -String phoneNumber
        -String address1
        -String address2
        -String email
        -Date birthDate
        -String createdAt
        -String updatedAt
    }

    class Products {
        -Long id
        -String productName
        -String productDescription
        -String productImageUrl
        -String productCategory
        -int productPrice
        -int productStock
        -boolean productIsActive
        -String productCreatedAt
        -String productUpdatedAt
    }
    
    class Orders {
        -Long id
        -Long customerId
        -Long productId
        -int productPrice
        -int quantity
        -int totalPrice
        -boolean isPaid
        -String orderDate
        -String status
        -String deliveryAddress
        -String paymentMethod
        -String deliveryDate
        -String createdAt
        -String orderUpdatedAt
    } 

     class Coupons {
        -Long id
        -int couponPrice
        -int couponDiscountRate
        -int issuedCount
        -int quota
        -String couponName
        -String couponDescription
        -String couponStatus
        -String couponType
        -Date couponStartDate
        -Date couponEndDate
    }
    
      class Points {
        -long id
        -long customerId
        -int point
        -TransactionType type
        -long updateMillis
    }
    
     class CustomerPointHistory {
        -long id
        -long customerId
        -int point
        -TransactionType type
        -long updateMillis
    }
    
     class Payments {
        -Long id
        -Long userId
        -Long productId
        -Long orderId
        -int paymentPrice
        -String paymentMethod = "P"
        -String paymentStatus
        -String paymentDate
        -String paymentAmount
        -String paymentCreatedAt
        -String paymentUpdatedAt
    }

    class UserCoupons {
        -Long id
        -Customers customers
        -Coupons coupon
        -boolean isUsed
        -Date issueDate
        -Date usageDate
        -Date expiryDate
    }
}    
  
```


 ## USERS ||--o{ ORDERS : ""
    USERS ||--|| COUPONS : ""
    PRODUCTS ||--o{ ORDER_ITEMS : ""
    ORDERS ||--|{ ORDER_ITEMS : ""
    ORDERS ||--o| COUPONS : ""
    ORDERS ||--o| ORDER_EVENT_LOGS : ""