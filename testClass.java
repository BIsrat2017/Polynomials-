import static org.junit.Assert.*;
import org.junit.*;

/**
 * this is the JUnit test class for the Polynomial class
 * @author Sandrain Benaja Tchamba
 * @version November 19, 2018
 */
public class PolynomialTest{

    private Polynomial p1, p2, pn, p;


    @Before
    public void setUp() {
        p1 = new Polynomial("3 2 -1 1 4 0");
        p2 = new Polynomial(p1);
        pn = new Polynomial("");
    }



    @Test
    public void testConstructor(){
        Polynomial p3 = new Polynomial("4 3");
        Polynomial dup1 = new Polynomial("3 2 -1 1 4 0");
        Polynomial p4 = new Polynomial("");
        Polynomial p5 = new Polynomial(dup1);
//        p2 = new Polynomial(dup1);

        assertEquals(3, dup1.terms() );
        assertEquals(3, p5.terms() );
        assertEquals(1, p3.terms() );
        assertEquals(3, p2.terms() );
        assertEquals(0, p3.getCoefficient(0), 0 );
        assertEquals(4, p2.getCoefficient(0), 0 );
        assertEquals(6, p5.evaluate(1), 0);
        assertEquals(0, p4.terms());
        assertEquals(0.0, p4.getCoefficient(4), 0);

    }

    @Test
    public void testTerms(){
        assertEquals(3, p1.terms());
        assertEquals(0, pn.terms());
    }


    @Test
    public void testAddTerm(){
        p1.addTerm (-3, 3);
        assertEquals(4, p1.terms() );
        p2.addTerm(-3, 0);
        assertEquals(3, p2.terms() );
        pn.addTerm(1 , 3);
        assertEquals(1, pn.terms());
        assertEquals(8, pn.evaluate(2), 0);
    }
//
    @Test
    public void testDeleteTerm(){
        assertEquals( -1.0 , p1.deleteTerm(1) ,0);
        assertEquals(2, p1.terms() );
        assertEquals( 3.0 , p1.deleteTerm(2), 0 );
        assertEquals(1, p1.terms() );
        assertEquals( 0.0, p2.deleteTerm(3) , 0);
        assertEquals(3, p2.terms() );
    }
//
    @Test
    public void testGetCoefficient() {
        assertEquals(-1.0, p1.getCoefficient(1), 0 );
        assertEquals(4.0, p1.getCoefficient(0), 0 );
        assertEquals(0.0, p2.getCoefficient(4), 0);
    }
//
    @Test
    public void testEvaluate() {
        assertEquals(4.0, p1.evaluate(0), 0 );
        assertEquals(4.0, p2.evaluate(0), 0 );
        assertEquals(6.0, p1.evaluate(1), 0 );
        assertEquals(6.0, p2.evaluate(1), 0 );
        assertEquals(8.0, p1.evaluate(-1), 0 );
        assertEquals(8.0, p2.evaluate(-1), 0 );
        assertEquals(9.25, p1.evaluate(1.5), 0 );
        assertEquals(25.25, p2.evaluate(-2.5), 0 );
    }

    @Test
    public void testEquals(){
        Polynomial a = new Polynomial("1 0");
        Polynomial b = new Polynomial("1 0");
        Polynomial c = new Polynomial(a);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.equals(c));
        assertTrue(c.equals(b));
        a.addTerm(1,1);
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
        assertFalse(a.equals(c));
        assertFalse(c.equals(a));
        c.addTerm(2,3);
        assertFalse(b.equals(c));
        assertFalse(c.equals(b));
    }

    @Test
    public void testDerivative() {
        Polynomial dp1 = p1.derivative();
        assertEquals(2 , dp1.terms(), 0 );
        assertEquals(6.0, dp1.getCoefficient(1), 0);
        assertEquals(-1.0, dp1.getCoefficient(0), 0);
        assertEquals(0.0, dp1.getCoefficient(2), 0);
        assertEquals(-1.0, dp1.evaluate(0), 0);
    }

    @Test
    public void testSum(){
        Polynomial a = new Polynomial("4 5 3 2 -5 0");
        Polynomial b = new Polynomial("1 4 -3 2 -2 0");
        Polynomial c = new Polynomial("-1 4 3 2 2 0");
        Polynomial d = new Polynomial("");
        Polynomial s = Polynomial.sum(a, b);
        Polynomial s1 = Polynomial.sum(b, c);
        Polynomial s2 = Polynomial.sum(a, d);
        assertEquals(4, s.getCoefficient(5),0);
        assertEquals(1, s.getCoefficient(4), 0);
        assertEquals(-7, s.getCoefficient(0), 0);
        assertEquals(3, s.terms());//it comes to be right when running in the main 
                                   //part or user interface
        // assertEquals(0, s1.getCoefficient(2), 0);
        assertEquals(0, s1.terms());
        // assertTrue(s2.equals(a));

    }

    @Test
    public void testProduct(){
        Polynomial a = new Polynomial("3.6 2 0 3 -9 0");
        Polynomial b = new Polynomial("2.0 2 0 3 5 0");
        Polynomial c = new Polynomial("");
        //Polynomial pr = Polynomial.product(a, b);
        //Polynomial pr2 = Polynomial.product(a,c);
        //assertEquals(2, pr.terms(), 0);
        // assertEquals(7.2, pr.getCoefficient(4), 0);
        // assertEquals(-45.0, pr.getCoefficient(0), 0);
        // assertEquals(0, pr.getCoefficient(2), 0);
        // assertEquals(0, pr2.getCoefficient(4), 0);
        // assertEquals(0, pr2.terms());

    }


    @Test
    public void testToString() {
        p = new Polynomial( "2.0 0" );
        assertEquals( "2.0", p.toString() );
        p = new Polynomial( "2 0" );
        assertEquals( "2.0", p.toString() );
        p = new Polynomial( "2.1 1" );
        assertEquals( "2.1x", p.toString() );
        p = new Polynomial( "2.1 2" );
        assertEquals( "2.1x^2", p.toString() );
        p = new Polynomial( "2.1 10" );
        assertEquals( "2.1x^10", p.toString() );
        p = new Polynomial( "1.0 0" );
        assertEquals( "1.0", p.toString() );
        p = new Polynomial( "1.0 1" );
        assertEquals( "x", p.toString() );
        p = new Polynomial( "1.0 2" );
        assertEquals( "x^2", p.toString() );
        p = new Polynomial( "-1.0 0" );
        assertEquals( "-1.0", p.toString() );
        p = new Polynomial( "-1.0 1" );
        assertEquals( "-x", p.toString() );
        p = new Polynomial( "-1.0 2" );
        assertEquals( "-x^2", p.toString() );
        p = new Polynomial( "1 2 2.1 0" );
        assertEquals( "x^2 + 2.1", p.toString() );
        p = new Polynomial( "1 2 -2.1 0" );
        assertEquals( "x^2 - 2.1", p.toString() );
        p = new Polynomial( "-1 2 2.1 0" );
        assertEquals( "-x^2 + 2.1", p.toString() );
        p = new Polynomial( "-1 2 -2.1 0" );
        assertEquals( "-x^2 - 2.1", p.toString() );
        p = new Polynomial( "2.5 3 -1.0 2" );
        assertEquals( "2.5x^3 - x^2", p.toString() );
        p = new Polynomial( "2.5 3 -1.0 2 1.0 1 -1.0 0" );
        assertEquals( "2.5x^3 - x^2 + x - 1.0", p.toString() );
        p = new Polynomial( "2.5 3 -1 2 1 1 -1 0" );
        assertEquals( "2.5x^3 - x^2 + x - 1.0", p.toString() );
        p = new Polynomial( "2.5 3 1 2 -1 1 1 0" );
        assertEquals( "2.5x^3 + x^2 - x + 1.0", p.toString() );
        p = new Polynomial( "" );
        assertEquals( "0.0", p.toString() );
    }

    @After
    public void tearDown(){

    }
}
