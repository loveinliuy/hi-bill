package loveinliuy.bill.repository;

import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/12].
 */
//@Ignore
public class InitData extends BaseSpringBootTest {

    @Autowired
    private BillRepository repository;


    @Test
    public void userData() {
        Object o  = repository.userTotalBillBetweenDate("-1", DateTime.now().withDayOfMonth(1).withTimeAtStartOfDay().toDate(),
                DateTime.now().withFieldAdded(DurationFieldType.months(), 1).withDayOfMonth(1).withTimeAtStartOfDay().toDate());
        System.out.println(o);
    }
}