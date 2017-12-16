package loveinliuy.bill.service;

import loveinliuy.bill.model.Code;
import loveinliuy.bill.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/15].
 */
@Service
public class CodeServiceImpl implements CodeService{

    @Autowired
    private CodeRepository repository;

    @Override
    public List<Code> getAllCostType() {
        return repository.findByTypeName(Code.COST_TYPE);
    }
}
