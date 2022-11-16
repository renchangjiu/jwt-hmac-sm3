package cc.kkon.jwt;

import cc.kkon.jwt.model.Header;
import cc.kkon.jwt.model.Payload;
import cc.kkon.jwt.utils.Bins;
import cc.kkon.jwt.utils.GMUtils;
import cc.kkon.jwt.utils.Jsons;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class Jwts {


    public static String create(String key, Payload payload) {
        return create(key.getBytes(StandardCharsets.UTF_8), payload);
    }

    public static String create(byte[] key, Payload payload) {
        Header header = new Header();
        header.setAlg("Hmac-SM3" );
        header.setTyp("jws" );

        String part1 = Jsons.toJson(header);
        if (payload == null) {
            payload = new Payload();
        }

        String part2 = Jsons.toJson(payload.getContent());

        Base64.Encoder b64encoder = Base64.getUrlEncoder();
        byte[] b641 = b64encoder.encode(part1.getBytes(StandardCharsets.UTF_8));
        byte[] b642 = b64encoder.encode(part2.getBytes(StandardCharsets.UTF_8));
        byte[] msg = new byte[b641.length + b642.length + 1];
        int i = 0;
        for (byte b : b641) {
            msg[i++] = b;
        }
        msg[i++] = 46;

        for (byte b : b642) {
            msg[i++] = b;
        }
        byte[] bytes = GMUtils.HmacSM3(msg, key);
        String signature = Bins.binToHex(bytes);
        return new String(b641)
                + "."
                + new String(b642)
                + "."
                + signature;
    }


    public static Payload parse(String jwt, byte[] key) {
        boolean verified = verified(jwt, key);
        if (!verified) {
            throw new RuntimeException("签名错误" );
        }
        String[] parts = jwt.split("\\." );
        byte[] decode = Base64.getUrlDecoder().decode(parts[1].getBytes(StandardCharsets.UTF_8));
        String json = new String(decode);
        Map<String, Object> map = Jsons.toBean(json, Map.class);
        return new Payload(map);
    }

    public static boolean verified(String jwt, String key) {
        return verified(jwt, key.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean verified(String jwt, byte[] key) {
        String[] parts = jwt.split("\\." );
        if (parts.length != 3) {
            return false;
        }
        String con = parts[0] + "." + parts[1];
        byte[] msg = con.getBytes(StandardCharsets.UTF_8);
        byte[] bytes = GMUtils.HmacSM3(msg, key);
        String hex = Bins.binToHex(bytes);
        String hex2 = parts[2];
        return hex.equals(hex2);
    }

}
