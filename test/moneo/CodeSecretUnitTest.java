package moneo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created with IntelliJ IDEA.
 * User: MrPwet
 * Date: 27/01/14
 * Time: 11:58
 */
public class CodeSecretUnitTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testRevelerCodeXXXX() {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(0);
        String vraiCode, fauxCode;
        Code code = new Code(random);

        vraiCode = code.revelerCode();
        Assert.assertThat(vraiCode, is("0000"));
        fauxCode = code.revelerCode();
        Assert.assertThat(fauxCode,is(not("0000")));

    }

    @Test
    public void testVerifierCode() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(0);
        Code code = new Code(random);

        Assert.assertThat(code.verifierCode("5005"), is(false));
        Assert.assertThat(code.verifierCode("lolol"), is(false));
        Assert.assertThat(code.verifierCode("0000"), is(true));
    }

    @Test
    public void testNbEssai() throws Exception {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(0);
        Code code = new Code(random);

        //2 tentatives fausse
        Assert.assertThat(code.verifierCode("5005"), is(false));
        Assert.assertThat(code.verifierCode("lolol"), is(false));
        //La troisieme juste devrait reset le compteur nous laissant à nouveau 3 chances
        Assert.assertThat(code.verifierCode("0000"), is(true));

        //Essayons ça
        Assert.assertThat(code.verifierCode("5005"), is(false));
        Assert.assertThat(code.verifierCode("lolol"), is(false));
        Assert.assertThat(code.verifierCode("8000"), is(false));

        //Le 3eme essai raté, le 4eme devrait être impossible
        exception.expect(PurseBloqueException.class);
        code.verifierCode("5874");


    }
}
