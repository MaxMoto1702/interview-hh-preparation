package hh;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * Created by m on 23.03.2015.
 */
public class SimpleTest {
    private HhApi api;
    private UserProperties user;

    @BeforeClass
    public void setUpClass() {
        user = new UserProperties();
        api = new HhApi();
    }

    @Test
    public void simpleTest() {
        String response = api.executeGet("http://api.hh.ru/me");
        LoggerFactory.getLogger(SimpleTest.class).info("Response " + response);
        JSONObject jsonObject = new JSONObject(response);

    }
}
