package moneo;

/**
 * Created with IntelliJ IDEA.
 * User: MrPwet
 * Date: 20/01/14
 * Time: 11:31
 */
public class Purse {
    private int nbOperationMax;
    private float solde;
    private float plafond;

    public Purse() {
        this(250);
    }

    public Purse(float plafond) {
        this(plafond, 150);
    }

    public Purse(float plafond, int nbOperationMax) {
        this.plafond = plafond;
        this.nbOperationMax = nbOperationMax;
    }

    public float getSolde() {
        return solde;
    }

    public void debite(float montant) throws MontantNegatifException, SoldeNegatifException, NombreOperationMaxAtteint {
        if (nbOperationMax <= 0)
            throw new NombreOperationMaxAtteint();
        if(montant < 0)
            throw new MontantNegatifException();
        if (montant > solde) throw new SoldeNegatifException();
        solde -= montant;
        nbOperationMax--;
    }

    public void credite(float montant) throws PlafondDepasseException, MontantNegatifException, NombreOperationMaxAtteint {
        if (nbOperationMax <= 0)
            throw new NombreOperationMaxAtteint();
        if(montant < 0)
            throw new MontantNegatifException();
        if(solde+montant > plafond) throw new PlafondDepasseException();
        solde += montant;
        nbOperationMax--;
    }
}
