package moneo;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: MrPwet
 * Date: 09/02/14
 * Time: 23:43
 */
public class Code {
    private String code;
    private int essai;
    private boolean revele;
    private boolean bloque;

    public Code(Random random) {
        StringBuilder code = new StringBuilder(4);
        for(int i = 0 ; i < 4 ; i++) {
            code.append(random.nextInt(10));
        }
        this.code = code.toString();
        revele = false;
        bloque = false;
        essai = 3;
    }

    public String revelerCode() {
        if(!revele) {
            revele = true;
            return code;
        }
        else {
            return("XXXX");
        }
    }

    public Boolean verifierCode(String codeFourni) throws PurseBloqueException {
        if(essai <= 0) {
            throw new PurseBloqueException();
        }
        if(!(codeFourni.equals(code))) {
            essai--;
            return false;
        }
        else {
            essai = 3;
            return true;
        }
    }
}
