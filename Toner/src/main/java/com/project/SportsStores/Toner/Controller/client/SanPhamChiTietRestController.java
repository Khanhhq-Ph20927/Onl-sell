package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Service.Impl.SanPhamChiTietServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/client/product_detail", "/api/product_detail"
})
public class SanPhamChiTietRestController {
    @Autowired
    private SanPhamChiTietServiceImpl sv;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private ResponseEntity<?> getAll() {
        return new ResponseEntity<>(sv.getAll(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
//    private ResponseEntity<?> getProductDetailById(@PathVariable("id") String id) {
//        return ResponseEntity.ok().body(sv.getById(id));
//    }
    @RequestMapping(value = "/detailPD/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getByid(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(sv.getListByIdSp(id));
    }
    @RequestMapping(value = "/detail/{id}/{color}", method = RequestMethod.GET)
    private ResponseEntity<?> findIdProductAndColor(@PathVariable("id") String id,@PathVariable("color") String color) {
        return ResponseEntity.ok().body(sv.findListProductByColor(id,color));
    }

}
