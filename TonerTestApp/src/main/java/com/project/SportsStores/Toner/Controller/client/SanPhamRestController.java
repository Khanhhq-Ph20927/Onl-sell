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

//    @RequestMapping(value = "/page/{pageNumber}/{keyWord}", method = RequestMethod.GET)
//    private ResponseEntity<?> pagination(@PathVariable("pageNumber") int pageNumber,
//                                         @PathVariable("keyWord") String keyWord) {
//        Pageable pageable = PageRequest.of(pageNumber, 8, Sort.by("ngayTao").descending());
//        if (keyWord.equalsIgnoreCase("null")) {
//            Page<SanPham> page = service.pageClient(pageable);
//            return new ResponseEntity<>(page, HttpStatus.OK);
//        } else {
//            Page<SanPham>   page = service.searchAndPrice( pageable,keyWord,priceStart,priceEnd);
//            return new ResponseEntity<>(page, HttpStatus.OK);
//        }
//    }

    @RequestMapping(value = "/page/{pageNumber}/{keyWord}", method = RequestMethod.POST)
    private ResponseEntity<?> paginationAndFilter(@PathVariable("pageNumber") String pageNumber,
                                                  @PathVariable("keyWord") String keyWord,
                                                  @RequestBody FilterRequest filterRequest) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), 8, Sort.by("ngayTao").descending());
//        Page<SanPham> page = service.pageClient(pageable);
        Page<SanPham> page = null;
        if(filterRequest.getListColors() != null) {
            for (Integer result : filterRequest.getListColors()
            ) {
                System.out.println("result :" + result);
            }
        }

        if(filterRequest.getListSizes() != null) {
            for (String result : filterRequest.getListSizes()
            ) {
                System.out.println("result :" + result);
            }
        }
        //2 done, 1 done , 3 done, 4 done , 5 done

        //search

        if (!keyWord.equals("null") && (filterRequest.getListColors() == null && filterRequest.getListSizes() == null)) {
            page = service.search(pageable, keyWord);
            System.out.println(keyWord);
            System.out.println("Case " + 1);

        } else if (keyWord.equals("null") && filterRequest.getListColors() != null && filterRequest.getListSizes() == null) {
            page = service.filterColor(pageable, filterRequest.getListColors());
            System.out.println("Case " + 2);
        }
        else if (keyWord.equals("null") && filterRequest.getListColors() == null && filterRequest.getListSizes() != null) {
            page = service.filterSize(pageable, filterRequest.getListSizes());
            System.out.println("Case " + 3);
        } else if (keyWord.equals("null") && filterRequest.getListColors() != null && filterRequest.getListSizes() != null) {
            page = service.filterColorAndSize(pageable, filterRequest.getListColors(), filterRequest.getListSizes());
            System.out.println("Case " + 4);
        } else if (!keyWord.equals("null") && filterRequest.getListColors() != null && filterRequest.getListSizes() != null) {
            page = service.searchfilterColorAndSizeIn(pageable, keyWord, filterRequest.getListColors(), filterRequest.getListSizes());
            System.out.println("Case " + 5);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }


}