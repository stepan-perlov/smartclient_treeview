package common;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import probe.common.Helpers;
import probe.common.Serializable;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class TestHelpers {
    private static Logger log = Logger.getLogger(TestHelpers.class);

    private static TestJsonSerializable item1;
    private static TestJsonSerializable item2;
    private static TestJsonSerializable item3;

    @BeforeClass
    public static void before() {
        item1 = new TestJsonSerializable(1, "name #1");
        item2 = new TestJsonSerializable(2, "name #2");
        item3 = new TestJsonSerializable(3, "name #3");
    }

    @Test
    public void testListToJson() {
        ArrayList<TestJsonSerializable> testList = new ArrayList<>();
        TestCase.assertEquals(Helpers.listToJson(testList), "[]");

        testList.add(item1);
        TestCase.assertEquals(Helpers.listToJson(testList), "[{\"num\":1,\"name\":\"name #1\"}]");

        testList.add(item2);
        testList.add(item3);
        TestCase.assertEquals(Helpers.listToJson(testList), "[" +
            "{\"num\":1,\"name\":\"name #1\"}," +
            "{\"num\":2,\"name\":\"name #2\"}," +
            "{\"num\":3,\"name\":\"name #3\"}" +
        "]");
    }

    static class TestJsonSerializable extends Serializable {
        int num;
        String name;

        public TestJsonSerializable(
            int iNum,
            @NotNull String iName
        ) {
            num = iNum;
            name = iName;
        }

        public int getNum() {
            return num;
        }

        public @NotNull String getName() {
            return name;
        }
    }
}
