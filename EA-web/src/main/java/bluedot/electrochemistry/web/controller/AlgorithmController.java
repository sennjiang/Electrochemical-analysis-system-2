package bluedot.electrochemistry.web.controller;

import bluedot.electrochemistry.common.LogUtil;
import bluedot.electrochemistry.service.edit.EditService;
import bluedot.electrochemistry.service.factory.MapperFactory;
import bluedot.electrochemistry.simplespring.core.annotation.Controller;
import bluedot.electrochemistry.simplespring.core.annotation.RequestMapping;
import bluedot.electrochemistry.simplespring.inject.annotation.Autowired;
import bluedot.electrochemistry.simplespring.mvc.file.MultipartFile;
import bluedot.electrochemistry.web.controller.base.Result;
import org.slf4j.Logger;

/**
 * 算法-控制器
 * @Author zero
 * @Create 2022/1/29 20:13
 */
@Controller
@RequestMapping("/algorithm")
public class AlgorithmController {

    @Autowired
    private MapperFactory factory;

    @Autowired
    EditService editService;

    private static final Logger LOGGER = LogUtil.getLogger(AlgorithmController.class);

    //TODO
    @RequestMapping("/run")
    public Result addAlgorithm(int fid, String name, String[] xData, String[] yData){
        return null;
    }

}
