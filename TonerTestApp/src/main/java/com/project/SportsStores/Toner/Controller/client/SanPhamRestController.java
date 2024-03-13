package com.project.SportsStores.Toner.Controller.client;

import com.project.SportsStores.Toner.Model.CustomModel.FilterRequest;
import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import com.project.SportsStores.Toner.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/client/product",
        "/api/product"})
public class SanPhamRestController {
    @Autowired
    SanPhamService service;
    @Autowired
    SanPhamChiTietService chiTietService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private ResponseEntity<?> getALL() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/page/{pageNumber}/{keyWord}", method = RequestMethod.POST)
    private ResponseEntity<?> paginationAndFilter(@PathVariable("pageNumber") String pageNumber,
                                                  @PathVariable("keyWord") String keyWord,
                                                  @RequestBody FilterRequest filterRequest,
                                                  @RequestParam("sort") int sort) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, Sort.by("ngayTao").descending());
        Page<SanPham> page = null;
        if (sort == 1) {
            Sort sortCriteria = Sort.by(
                    Sort.Order.asc("tenSP"),
                    Sort.Order.desc("ngayTao")
            );
            pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, sortCriteria);
            System.out.println("sort A-Z");
        } else if (sort == 2) {
            Sort sortCriteria = Sort.by(
                    Sort.Order.desc("tenSP"),
                    Sort.Order.desc("ngayTao")
            );
            pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, sortCriteria);
            System.out.println("sort Z-A");
        } else if (sort == 3) {
            Sort sortCriteria = Sort.by(
                    Sort.Order.asc("donGia"),
                    Sort.Order.desc("ngayTao")

            );
            pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, sortCriteria);
            System.out.println("sort price desc");
        } else if (sort == 4) {
            Sort sortCriteria = Sort.by(
                    Sort.Order.desc("donGia"),
                    Sort.Order.desc("ngayTao")
            );
            pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, sortCriteria);
            System.out.println("sort price asc");
        } else {
            System.out.println("sort default");
        }
        if (keyWord.equals("null") && (filterRequest.getListColors() == null && filterRequest.getListSizes() == null)) {
            page = service.pageClient(pageable);
            System.out.println("Case Default");

        } else if (!keyWord.equals("null") && (filterRequest.getListColors() == null && filterRequest.getListSizes() == null)) {
            page = service.search(pageable, keyWord);
            System.out.println(keyWord);
            System.out.println("Case " + 1);

        } else if (keyWord.equals("null") && filterRequest.getListColors() != null && filterRequest.getListSizes() == null) {

            page = service.filterColor(pageable, filterRequest.getListColors());
            System.out.println("Case " + 2);
        } else if (keyWord.equals("null") && filterRequest.getListColors() == null && filterRequest.getListSizes() != null) {

            page = service.filterSize(pageable, filterRequest.getListSizes());
            System.out.println("Case " + 3);
        } else if (keyWord.equals("null") && filterRequest.getListColors() != null && filterRequest.getListSizes() != null) {

            page = service.filterColorAndSize(pageable, filterRequest.getListColors(), filterRequest.getListSizes());
            System.out.println("Case " + 4);
        } else if (!keyWord.equals("null") && filterRequest.getListColors() != null && filterRequest.getListSizes() != null) {

            page = service.searchfilterColorAndSizeIn(pageable, keyWord, filterRequest.getListColors(), filterRequest.getListSizes());
            System.out.println("Case " + 5);
        } else {
            System.out.println("Error??");
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


}
