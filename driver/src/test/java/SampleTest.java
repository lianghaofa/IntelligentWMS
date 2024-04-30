import org.iwms.driver.mapper.DriverMapper;
import org.iwms.driver.model.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private DriverMapper driverMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Driver> driverList = driverMapper.selectList(null);
        Assert.isTrue(5 == driverList.size(), "");
        driverList.forEach(System.out::println);
    }

}
