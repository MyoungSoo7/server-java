# 📦 클래스 다이어그램 📦

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

UserCoupons --> Customers : 사용자 연관 관계
UserCoupons --> Coupons : 쿠폰 연관 관계

    Customers "1" --> "*" Orders : 주문
    Customers "1" --> "*" UserCoupons : 보유    
    Orders "1" --> "*" Products : 포함
    Orders "1" --> "0..1" Coupons : 사용
    Orders "1" --> "1" Points : 결제
 
