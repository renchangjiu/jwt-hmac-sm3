package cc.kkon.jwt.model;


/**
 * 头部
 *
 * @author yui
 */
public class Header {


    private String alg;

    private String typ;

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
