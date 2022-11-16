package cc.kkon;

import cc.kkon.jwt.Jwts;
import cc.kkon.jwt.model.Payload;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class TestJwt {

    @Test
    public void testSuccess() throws Exception {
        byte[] key = "123456789".getBytes(StandardCharsets.UTF_8);
        Payload pay1 = new Payload();
        pay1.setExp(new Date());
        String userId = UUID.randomUUID().toString();
        pay1.set("userId" , userId);

        String jwt = Jwts.create(key, pay1);

        boolean signVerified = Jwts.verified(jwt, key);

        assert signVerified;

        Payload pay2 = Jwts.parse(jwt, key);
        String userId1 = pay2.get("userId" ).toString();

        assert userId.equals(userId1);
        System.out.println(jwt);
    }

    @Test
    public void testFailed1() throws Exception {
        String key1 = "123456789";
        String key2 = "987654321";
        Payload pay = new Payload();
        pay.setExp(new Date());
        pay.set("userId" , UUID.randomUUID().toString());

        String jwt1 = Jwts.create(key1, pay);
        String jwt2 = Jwts.create(key2, pay);

        boolean b1 = Jwts.verified(jwt1, key2);
        boolean b2 = Jwts.verified(jwt2, key1);
        assert !b1;
        assert !b2;
        System.out.println(jwt1);
        System.out.println(jwt2);
    }

    @Test
    public void testFailed2() throws Exception {
        byte[] key = "123456789".getBytes(StandardCharsets.UTF_8);
        Payload pay1 = new Payload();
        pay1.setExp(new Date());
        pay1.set("userId" , UUID.randomUUID().toString());


        String jwt1 = Jwts.create(key, pay1);
        System.out.println(jwt1);

        String jwt2 = 1 + jwt1;
        boolean b = Jwts.verified(jwt2, key);
        assert !b;
        System.out.println(jwt2);
    }
}
