package loveinliuy.bill.controller;

import com.google.common.base.Preconditions;
import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.CostType;
import loveinliuy.bill.model.Message;
import loveinliuy.bill.model.User;
import loveinliuy.bill.service.CostTypeService;
import loveinliuy.bill.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * description:
 *
 * @author zhangshibo  [2018/1/4].
 */
@Controller
@RequestMapping(value = "/costType")
public class CostTypeAct {

    @Autowired
    private CostTypeService service;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "costType/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(CostType costType) {
        Preconditions.checkNotNull(costType);
        Preconditions.checkNotNull(costType.getType());
        Preconditions.checkArgument(StringUtils.isNotEmpty(costType.getName()));

        service.save(costType);
        return Message.builder().type(Message.Type.SUCCESS).message("保存消费类型信息成功！")
                .url("/costType").build().toParamString();
    }

    @ResponseBody
    @RequestMapping(value = "/name/validate", method = RequestMethod.GET)
    public Map<String, Boolean> validate(Bill.Type type, String name) {
        return Collections.singletonMap("valid", !service.duplicate(type, name));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        User user = SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new);
        model.addAttribute("costTypes", service.getCostTypesByBillType(user, Bill.Type.values()));
        return "costType/list";
    }
}
