package moneo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: MrPwet
 * Date: 20/01/14
 * Time: 11:23
 */
public class PurseUnitTest {

    @Test
    public void testCredite() throws Exception {
        Purse purse = new Purse();
        float solde = purse.getSolde();
        purse.credite(50f);
        Assert.assertEquals(solde+50, purse.getSolde(), 0);
    }

    @Test
    public void testDebite() throws Exception {
        Purse purse = new Purse();
        purse.credite(200);
        float solde = purse.getSolde();
        purse.debite(50f);
        Assert.assertEquals(solde-50, purse.getSolde(), 0);
    }

    @Test (expected = SoldeNegatifException.class)
    public void testSoldeJamaisNegatif() throws Exception {
        Purse purse = new Purse();
        purse.debite(purse.getSolde()+1);
    }

    @Test (expected = PlafondDepasseException.class)
    public void testSoldeToujoursInferieurAuPlafond() throws Exception, PlafondDepasseException {
        Purse purse = new Purse(100f);
        purse.credite(110f);
    }

    @Test (expected = MontantNegatifException.class)
    public void testMontantCreditPositif() throws Exception {
        Purse purse = new Purse(100);
        purse.credite(-10);
    }

    @Test (expected = MontantNegatifException.class)
    public void testMontantDebitPositif() throws Exception {
        Purse purse = new Purse(100);
        purse.debite(-10);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testNombreOperationCreditLimite() throws Exception {
        Purse purse = new Purse(100, 5);

        purse.credite(10);
        purse.credite(10);
        purse.credite(10);
        purse.credite(10);
        purse.credite(10);

        exception.expect(NombreOperationMaxAtteint.class);
        purse.credite(10);

    }

    @Test
    public void testNombreOperationDebitLimite() throws Exception {
        Purse purse = new Purse(100, 3);

        purse.credite(90);
        purse.debite(10);
        purse.debite(10);

        exception.expect(NombreOperationMaxAtteint.class);
        purse.debite(10);
    }
}
