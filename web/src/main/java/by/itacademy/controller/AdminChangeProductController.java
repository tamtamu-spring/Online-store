package by.itacademy.controller;

import by.itacademy.entity.productEntity.Characteristic;
import by.itacademy.entity.productEntity.Product;
import by.itacademy.service.CharacteristicService;
import by.itacademy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminChangeProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CharacteristicService characteristicService;

    @GetMapping("/change-product/{id}")
    public String getProduct(@PathVariable("id") Long productId,
                             @RequestParam(value = "error", required = false) Integer error,
                             Model model) {
        Product product = productService.getByID(productId);
        List<Characteristic> characteristics = characteristicService.getByProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("characteristics", characteristics);
        if(error != null) {
            model.addAttribute("optimisticLock", true);
        }
        return "admin-change-product";
    }

    @PostMapping("/change-product/{id}")
    public String changeProduct(@PathVariable("id") Long productId, Product product, Model model, HttpServletRequest request) {
        Product originalProduct = productService.getByID(productId);
        System.out.println("PRODUCT: " + product);
        product.setCategory(originalProduct.getCategory());
        try{
            productService.update(product);
        } catch (HibernateOptimisticLockingFailureException e) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer + "?error=1";
        }
        return "redirect:/main_page";
    }
}