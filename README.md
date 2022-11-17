# jwt-hmac-sm3
支持国密 hmac-sm3 算法的 jwt 库。

一、使用。
```java
byte[] key = "123456789".getBytes(StandardCharsets.UTF_8);
Payload pay1 = new Payload();
pay1.setExp(new Date());
String userId = UUID.randomUUID().toString();
pay1.set("userId" , userId);

// 创建 token
String jwt = Jwts.create(key, pay1);

// 验证
boolean signVerified = Jwts.verified(jwt, key);

assert signVerified;

// 获取载荷
Payload pay2 = Jwts.parse(jwt, key);
String userId1 = pay2.get("userId" ).toString();

assert userId.equals(userId1);
System.out.println(jwt);
```

二、Maven 坐标。
```xml
<dependency>
    <groupId>cc.kkon</groupId>
    <artifactId>jwt-hmac-sm3</artifactId>
    <version>0.1.1</version>
</dependency>
```
