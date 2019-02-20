package hhu.propra2.illegalskillsexception.frently.backend.Controllers;

import hhu.propra2.illegalskillsexception.frently.backend.Services.ProPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propray")
public class TestController {
    @Autowired
    private ProPayService proPayService;

    @RequestMapping("/test")
    public void test1() {
        System.out.println(proPayService.createAccount("Peter", 100000));
    }
}
