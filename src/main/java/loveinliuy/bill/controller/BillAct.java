package loveinliuy.bill.controller;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.Code;
import loveinliuy.bill.service.BillService;
import loveinliuy.bill.service.CodeService;
import loveinliuy.bill.util.FrontUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
@Slf4j
@Controller
@RequestMapping(value = "/bill")
public class BillAct {

    @Autowired
    private BillService service;

    @Autowired
    private CodeService codeService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String save(Model model){
        List<Code> types = codeService.getAllCostType();

        model.addAttribute("costType", types);
        return "bill/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Bill bill, Model model){
        Preconditions.checkNotNull(bill, "账单信息不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(bill.getDescription()), "描述信息不能为空");
        Preconditions.checkNotNull(bill.getCost(), "花费信息不能为空！");

        log.debug("get bill: {}", bill);
        service.save(bill);
        FrontUtil.success(model, "保存账单信息成功！");
        return "redirect:/index";
    }
}
