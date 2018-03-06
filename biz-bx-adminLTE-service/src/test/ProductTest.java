import com.ly.fn.admin.modules.product.domain.ProductBaseInfo;
import com.ly.fn.admin.modules.product.service.ProductBaseInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-context.xml"})
public class ProductTest {

    @Resource
    private ProductBaseInfoService productBaseInfoService;

    @Test
    public void getById() {
        ProductBaseInfo productBaseInfo = productBaseInfoService.getById(188L);
        System.out.println(productBaseInfo.getProductName());
    }
}
