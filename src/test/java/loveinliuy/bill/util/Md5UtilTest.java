package loveinliuy.bill.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/12].
 */
public class Md5UtilTest {

    @Test
    public void encrypt() {
        final String t1 = "123";
        final String t2 = "123456";

        final String e1 = "202cb962ac59075b964b07152d234b70";
        final String e2 = "e10adc3949ba59abbe56e057f20f883e";

        assertEquals(e1, Md5Util.encrypt(t1, 1));
        assertEquals(e2, Md5Util.encrypt(t2, 1));
    }
}