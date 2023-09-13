package utils;


import org.testng.asserts.SoftAssert;

public class SoftAssertionUtil {

    private static SoftAssert softAssert;

    private SoftAssertionUtil(){}

    public static SoftAssert getInstance()
    {
        if(softAssert == null){
            softAssert = new SoftAssert();
        }
        return softAssert;

    }

    public static void assertEquals(Object actual, Object expected, String assertionmessage){
        try{
            getInstance().assertEquals(actual, expected, assertionmessage);
        }catch(AssertionError e){
            getInstance().fail(assertionmessage);
        }

    }

    public static void assertAll(){
        softAssert.assertAll();
    }
}
