package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SanPhamService {

    List<SanPham> getAll();

    Page<SanPham> getPagination(Pageable pageable);

    Optional<SanPham> getById(Long id);

    boolean save(SanPham sp);

    boolean update(SanPham sp);

    boolean deleteById(Long id);

    Page<SanPham> seacrhProduct(String keyword, Pageable pageable);

    Page<SanPham> searchAndfilterByCollection(String keyword, Pageable pageable, String collection);

    Page<SanPham> searchAndfilterByStatus(String keyword, Pageable pageable, String status);

    Page<SanPham> searchAndfilterByStatusAndCollection(String keyword, Pageable pageable, String status, String collection);

    Page<SanPham> filterByStatusNoSearch(Pageable pageable, String status);

    Page<SanPham> filterByCollectionNoSearch(Pageable pageable, String collection);

    Page<SanPham> filterByStatusAndCollectionNoSearch(Pageable pageable, String status, String collection);

    Page<SanPham> filterByPrice(String keyword, Pageable pageable, String priceStart, String priceEnd);

    Page<SanPham> filterByStatusAndPrice(String keyword, Pageable pageable, String status, String priceStart, String priceEnd);

    Page<SanPham> filterByCollectionAndPrice(String keyword, Pageable pageable, String collection, String priceStart, String priceEnd);

    Page<SanPham> filterByAll(String keyword, Pageable pageable, String status, String collection, String priceStart, String priceEnd);

    //Khánh
    Page<SanPham> pageClient(Pageable pageable);

    //search
    Page<SanPham> search(Pageable pageable, String keyword);

    //search+priceMin
    Page<SanPham> searchAndPriceMin(Pageable pageable, String keyword, String priceStart);

    //search+priceMax
    Page<SanPham> searchAndPriceMax(Pageable pageable, String keyword, String priceEnd);

    //search+price
    Page<SanPham> searchAndPrice(Pageable pageable, String keyword, String priceStart, String priceEnd);

    //search+color
    Page<SanPham> searchAndFilterColor(Pageable pageable, String keyword, List<String> color);

    //search+size
    Page<SanPham> searchAndFilterSize(Pageable pageable, String keyword, List<String> size);

    //search+color+size
    Page<SanPham> searchAndFilterColorAndSize(Pageable pageable, String keyword, List<String> color, List<String> size);

    //search+priceMin+color
    Page<SanPham> searchAndPriceMinAndFilterColor(Pageable pageable, String keyword, String priceStart, String color);

    //search+priceMax+color
    Page<SanPham> searchAndPriceMaxAndFilterColor(Pageable pageable, String keyword, String priceEnd, String color);

    //search+price+color
    Page<SanPham> searchAndPriceAndFilterColor(Pageable pageable, String keyword, String priceStart, String priceEnd, String color);

    //search+priceMin+size
    Page<SanPham> searchAndPriceMinAndFilterSize(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart, @Param("size") String size);

    //search+priceMax+size
    Page<SanPham> searchAndPriceMaxAndFilterSize(Pageable pageable, @Param("keyword") String keyword, @Param("priceEnd") String priceEnd, @Param("size") String size);

    //search+price+size
    Page<SanPham> searchAndPriceAndFilterSize(Pageable pageable, String keyword, String priceStart, String priceEnd, String size);

    //search+priceMin+color+size
    Page<SanPham> searchAndPriceMinAndFilterColorAndSize(Pageable pageable, String keyword, String priceStart, String color, String size);

    //search+priceMax+color+size
    Page<SanPham> searchAndPriceMaxAndFilterColorAndSize(Pageable pageable, String keyword, String priceEnd, String color, String size);

    //search+price+color+size
    Page<SanPham> searchAndPriceAndFilterColorAndSize(Pageable pageable, String keyword, String priceStart, String priceEnd, String color, String size);

    //priceMin
    Page<SanPham> priceMin(Pageable pageable, String priceStart);

    //priceMax
    Page<SanPham> priceMax(Pageable pageable, String priceEnd);

    //price
    Page<SanPham> price(Pageable pageable, String priceStart, String priceEnd);

    //priceMin+color
    Page<SanPham> priceMinAndFilterColor(Pageable pageable, String priceStart, String color);

    //priceMax+color
    Page<SanPham> priceMaxAndFilterColor(Pageable pageable, String priceEnd, String color);

    //price+color
    Page<SanPham> priceAndFilterColor(Pageable pageable, String priceStart, String priceEnd, String color);

    //priceMin+size
    Page<SanPham> priceMinAndFilterSize(Pageable pageable, String priceStart, String size);

    //priceMax+size
    Page<SanPham> priceMaxAndFilterSize(Pageable pageable, String priceEnd, String size);

    //price+size
    Page<SanPham> priceAndFilterSize(Pageable pageable, String priceStart, String priceEnd, String size);

    //priceMin+color+size
    Page<SanPham> priceMinAndFilterColorAndSize(Pageable pageable, String priceStart, String color, String size);

    //priceMax+color+size
    Page<SanPham> priceMaxAndFilterColorAndSize(Pageable pageable, String priceEnd, String color, String size);

    //price+color+size
    Page<SanPham> priceAndFilterColorAndSize(Pageable pageable, String priceStart, String priceEnd, String color, String size);

    //color
    Page<SanPham> filterColor(Pageable pageable, List<String> color);

    //size
    Page<SanPham> filterSize(Pageable pageable, List<String> size);

    //color+size
    Page<SanPham> filterColorAndSize(Pageable pageable, List<String> color, List<String> size);

    Page<SanPham> searchfilterColorAndSizeIn(Pageable pageable, String keyword, List<String> color, List<String> size);

}
