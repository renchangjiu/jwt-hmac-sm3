package cc.kkon.jwt.model;

import java.util.*;

/**
 * 载荷
 *
 * @author yui
 */
public class Payload {


    private static final String[] ARR = new String[]{
            "iss" ,
            "exp" ,
            "sub" ,
            "aud" ,
            "nbf" ,
            "iat" ,
            "jti"
    };
    private static final Set<String> PREDEFINED = new HashSet<>(Arrays.asList(ARR));


    private Map<String, Object> content;


    public Payload(Map<String, Object> content) {
        this.content = content;
    }

    public Payload() {
        this(new HashMap<>());
    }

    public Object get(String key) {
        return this.content.get(key);
    }

    /**
     * 设置自定义字段
     */
    public Payload set(String key, Object val) {
        if (PREDEFINED.contains(key)) {
            throw new RuntimeException("Key can not in predefined" );
        }
        return this.set0(key, val);
    }

    private Payload set0(String key, Object val) {
        this.content.put(key, val);
        return this;
    }

    /**
     * 发行人
     */
    public String getIss() {
        return (String) get("iss" );
    }

    /**
     * 发行人
     */
    public Payload setIss(String iss) {
        return this.set0("iss" , iss);
    }

    /**
     * 到期时间
     */
    public Date getExp() {
        Object date = get("exp" );
        if (date == null) {
            return null;
        } else {
            return new Date((Long) date);
        }
    }

    /**
     * 到期时间
     */
    public Payload setExp(Date exp) {
        return this.set0("exp" , exp);
    }

    /**
     * 主题
     */
    public String getSub() {
        return (String) get("sub" );
    }

    /**
     * 主题
     */
    public Payload setSub(String sub) {
        return this.set0("sub" , sub);
    }


    /**
     * 用户
     */
    public String getAud() {
        return (String) get("aud" );
    }

    /**
     * 用户
     */
    public Payload setAud(String aud) {
        return this.set0("aud" , aud);
    }

    /**
     * 在此之前不可用
     */
    public Date getNbf() {
        Object date = get("nbf" );
        if (date == null) {
            return null;
        } else {
            return new Date((Long) date);
        }
    }

    /**
     * 在此之前不可用
     */
    public Payload setNbf(Date nbf) {
        return this.set0("nbf" , nbf);
    }

    /**
     * 发布时间
     */
    public Date getIat() {
        Object date = get("iat" );
        if (date == null) {
            return null;
        } else {
            return new Date((Long) date);
        }
    }

    /**
     * 发布时间
     */
    public Payload setIat(Date iat) {
        return this.set0("iat" , iat);
    }

    /**
     * JWT ID用于标识该JWT
     */
    public String getJti() {
        return (String) get("jti" );
    }

    /**
     * JWT ID用于标识该JWT
     */
    public Payload setJti(String jti) {
        return this.set0("jti" , jti);
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
}
