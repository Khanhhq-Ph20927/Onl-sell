package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.DTO.ChiTietSanPhamDTO;
import com.project.SportsStores.Toner.Model.DTO.SanPhamChiTietDTO;
import com.project.SportsStores.Toner.Model.GioHang;
import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Service.GioHangChiTietService;
import com.project.SportsStores.Toner.Service.GioHangService;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping({"/api/client/cart_detail", "/api/cart_detail"
})
public class GioHangChiTietRestController {
    @Autowired
    GioHangChiTietService service;
    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    GioHangService gioHangService;
    @Autowired
    GioHangChiTietService gioHangChiTietService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private ResponseEntity<?> paginationCart() {
        Pageable pageable = PageRequest.of(0, 4, Sort.by("ngaySua").descending());
        GioHang gioHang = gioHangService.findByIdKH(Long.valueOf(1)).get();
        Page<GioHangChiTiet> page = service.getByIdGH(String.valueOf(gioHang.getId()), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    private ResponseEntity<?> pagination(@PathVariable("pageNumber") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 4, Sort.by("ngaySua").descending());
        GioHang gioHang = gioHangService.findByIdKH(Long.valueOf(1)).get();
        Page<GioHangChiTiet> page = service.getByIdGH(String.valueOf(gioHang.getId()), pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private ResponseEntity<?> add(@RequestBody ChiTietSanPhamDTO dto) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findIdProductByColorAndSize(dto.getIdSP(), dto.getMs(), dto.getSize());
        GioHang gioHang = gioHangService.getById("19");
        boolean isCheck = false;
        GioHangChiTiet ghctCa = null;
        for (GioHangChiTiet ghct : gioHangChiTietService.getByIdGHList("19")) {

            if (ghct.getSpct().getId() == sanPhamChiTiet.getId()) {
                isCheck = true;
                ghctCa = ghct;
                break;
            }
        }
        if (isCheck == false) {
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGh(gioHang);
            gioHangChiTiet.setSoLuong(1);
            gioHangChiTiet.setNgaySua(LocalDateTime.now());
            gioHangChiTiet.setSpct(sanPhamChiTiet);
            System.out.println("isCheck" + 1);
            service.save(gioHangChiTiet);
        } else {
            ghctCa.setSoLuong(ghctCa.getSoLuong() + 1);
            System.out.println("isCheck" + 2);
            service.save(ghctCa);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(String.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity<?> deleteALl() {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/addByProductDetailPage/{idPD}", method = RequestMethod.POST)
    private ResponseEntity<?> addByProductDetailPage(@PathVariable("idPD") Long idPD, @RequestBody SanPhamChiTietDTO dto) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.findIdProductByColorAndSize(String.valueOf(idPD), dto.getMs(), dto.getSize());
        GioHang gioHang = gioHangService.getById("19");
        boolean isCheck = false;
        GioHangChiTiet ghctAssign = null;
        for (GioHangChiTiet ghct : gioHangChiTietService.getByIdGHList("19")) {

            if (ghct.getSpct().getId() == sanPhamChiTiet.getId()) {
                isCheck = true;
                ghctAssign = ghct;
                break;
            }
        }
        if (isCheck == false) {
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGh(gioHang);
            gioHangChiTiet.setSoLuong(Integer.valueOf(dto.getSl()));
            gioHangChiTiet.setNgaySua(LocalDateTime.now());
            gioHangChiTiet.setSpct(sanPhamChiTiet);
            service.save(gioHangChiTiet);
        } else {
            ghctAssign.setSoLuong(ghctAssign.getSoLuong() + Integer.valueOf(dto.getSl()));
            service.save(ghctAssign);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
//    @GetMapping("/add-quantity/{id}")
//    public ResponseEntity<?> addQuantity(@PathVariable("id") String id) {
//        GioHangChiTiet ghct = ghctsv.getById(id);
//        SanPhamChiTiet spct = spsv.getById(String.valueOf(ghct.getSpct().getId()));
//
//        if (ghct.getSoLuong() >= spct.getSoLuong()) {
//            System.out.println("quantity of products in cart not more than quantity of products in stock");
//            return new ResponseEntity<>("failure", HttpStatus.OK);
//        } else {
//            ghct.setSoLuong(ghct.getSoLuong() + 1);
//            ghctsv.save(ghct);
//            return new ResponseEntity<>("success", HttpStatus.OK);
//        }
//    }
//
//    @GetMapping("/minus-quantity/{id}")
//    public ResponseEntity<?> minusQuantity(@PathVariable("id") String id) {
//        GioHangChiTiet ghct = ghctsv.getById(id);
//
//        if (ghct.getSoLuong() <= 0) {
//            System.out.println("quantity of products in cart not less than 0");
//            ghctsv.delete(id);
//        } else {
//            ghct.setSoLuong(ghct.getSoLuong() - 1);
//            ghctsv.save(ghct);
//        }
//        return new ResponseEntity<>("success", HttpStatus.OK);
//    }

}
