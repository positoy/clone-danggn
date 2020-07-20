package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.User;
import navercorp.com.andy.naver.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class GoodsController {

    final static Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    FileStorageService fileService;

    final String REDIRECT_TO_HOME = "redirect:/goods";

    final String TEMPLATE_HOME = "home";
    final String TEMPLATE_ITEM = "item";

    @GetMapping("/")
    public String getGoods() {
        return REDIRECT_TO_HOME;
    }

    @GetMapping("/goods")
    public String getGoods(Model model, @RequestParam(defaultValue = "") String code, @RequestParam(defaultValue = "") String state, HttpServletRequest request) {

        logger.info("code : " + code);
        logger.info("state : " + state);

        model.addAttribute("list", goodsService.getDummy());

        if (!state.isEmpty() || !code.isEmpty()) {

            if (state.equals(NaverConfiguration.VAL.state) && !code.isEmpty()) {
                logger.info("valid naver login try");

                User user = userService.getUser(code);
                if (user != null) {
                    logger.info("naver login successful with authcode:" + code + " , userid:" + user.getId());
                    model.addAttribute("newlogin", user.getId());
                    request.getSession().setAttribute("user", user);
                } else {
                    logger.warn("naver login failed");
                }

            } else {
                logger.warn("invalid naver login try");
            }
        }

        Util.updateModelUserFromSession(request, model);

        return TEMPLATE_HOME;
    }

    @GetMapping("/goods/{id}")
    public String getGoods(@PathVariable String id, Model model, @RequestParam(defaultValue = "") String userid, HttpServletRequest request) {
        logger.info(id);
        Goods good = goodsService.getGoods(id);
        User goodsuser = goodsService.getUserWithGoodsId(id);

        if (good == null || goodsuser == null) {
            logger.info("goods or user is null");

            return REDIRECT_TO_HOME;
        }

        logger.info(good.toString());
        logger.info(goodsuser.toString());

        model.addAttribute("goods", good);
        model.addAttribute("goodsuser", goodsuser);

        Util.updateModelUserFromSession(request, model);

//
//        if (!userid.isEmpty())
//            model.addAttribute("user", userService.getUserById(userid));

        return TEMPLATE_ITEM;
    }

    @PostMapping("/goods")
    @ResponseBody
    public String postGoods(Goods good, @RequestParam MultipartFile pics, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
        logger.info(good.toString());
        User user = (User) request.getSession().getAttribute("user");
        good = goodsService.refineGoods(good, user);

        if (!pics.getOriginalFilename().isEmpty()) {
            String filename = fileService.generateFileName(pics, good.getTimestamp(), user.getId());
            fileService.storeGoodsImg(pics, filename);

            ArrayList<String> imgs = new ArrayList<>();
            String filepath = fileService.generateGoodsImgPath(filename);
            imgs.add(filepath);
            goodsService.setImgs(good, imgs);
        }

        logger.info(good.toString());
        goodsService.saveGoods(good);

        Util.updateModelUserFromSession(request, model);

        return REDIRECT_TO_HOME;
    }

}
