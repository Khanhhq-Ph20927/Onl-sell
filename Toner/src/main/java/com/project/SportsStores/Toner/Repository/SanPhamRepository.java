package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Override
    void flush();

    @Override
    <S extends SanPham> S saveAndFlush(S entity);

    @Override
    List<SanPham> findAll();

    @Override
    <S extends SanPham> S save(S entity);

    @Override
    Optional<SanPham> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Page<SanPham> findAll(Pageable pageable);

    // search query
    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%")
    Page<SanPham> searchProduct(@Param("keyword") String keyword, Pageable pageable);

    //search and filter by collection
    @Query("SELECT sp FROM SanPham sp WHERE (sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%) " +
            "and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByCollection(@Param("keyword") String keyword, Pageable pageable,
                                                     @Param("collection") String collection);

    //filter by collection
    @Query("SELECT sp FROM SanPham sp WHERE" +
            " sp.danhMuc = :collection")
    Page<SanPham> filterProductByCollection(Pageable pageable,
                                            @Param("collection") String collection);

    //search and filter by status
    @Query("SELECT sp FROM SanPham sp WHERE (sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%) " +
            "and sp.trangThai = :status")
    Page<SanPham> searchAndFilterProductByStatus(@Param("keyword") String keyword, Pageable pageable,
                                                 @Param("status") String status);

    //filter by status
    @Query("SELECT sp FROM SanPham sp WHERE " +
            " sp.trangThai = :status")
    Page<SanPham> filterProductByStatus(Pageable pageable,
                                        @Param("status") String status);

    //filter by status and collection
    @Query("SELECT sp FROM SanPham sp WHERE " +
            " sp.trangThai = :status and sp.danhMuc = :collection")
    Page<SanPham> filterProductByStatusAndCollection(Pageable pageable,
                                                     @Param("status") String status,
                                                     @Param("collection") String collection);

    // search and filter by status + collection
    @Query("SELECT sp FROM SanPham sp WHERE (sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%) " +
            "and sp.trangThai = :status and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByStatusAndCollection(Pageable pageable,
                                                              @Param("keyword") String keyword,
                                                              @Param("status") String status,
                                                              @Param("collection") String collection);

    @Query("SELECT sp FROM SanPham sp WHERE (sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%) " +
            "and sp.donGia <= :priceEnd and sp.donGia >= :priceStart")
    Page<SanPham> searchAndFilterProductByPrice(@Param("keyword") String keyword, Pageable pageable,
                                                @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.trangThai = :status and sp.donGia <= :priceEnd and sp.donGia >= :priceStart")
    Page<SanPham> searchAndFilterProductByStatusAndPrice(@Param("keyword") String keyword, Pageable pageable,
                                                         @Param("status") String status,
                                                         @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.donGia <= :priceEnd and sp.donGia >= :priceStart and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByCollectionAndPrice(@Param("keyword") String keyword, Pageable pageable,
                                                             @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd,
                                                             @Param("collection") String collection);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.donGia <= :priceEnd and sp.donGia >= :priceStart and sp.trangThai = :status and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByAll(@Param("keyword") String keyword, Pageable pageable,
                                              @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd,
                                              @Param("status") String status, @Param("collection") String collection);

    //Khánh
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1")
    Page<SanPham> pageClient(Pageable pageable);

    //search
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)")
    Page<SanPham> search(Pageable pageable, @Param("keyword") String keyword);

    //search+priceMin
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia >= :priceStart)")
    Page<SanPham> searchAndPriceMin(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart);

    //search+priceMax
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia <= :priceEnd)")
    Page<SanPham> searchAndPriceMax(Pageable pageable, @Param("keyword") String keyword, @Param("priceEnd") String priceEnd);

    //search+price
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia <= :priceEnd and sp.donGia >= :priceStart)")
    Page<SanPham> searchAndPrice(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd);

    //search+color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(:color is null or spct.ms.id=:color)")
    Page<SanPham> searchAndFilterColor(Pageable pageable, @Param("keyword") String keyword, @Param("color") String color);

    //search+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(:size is null or spct.size=:size)")
    Page<SanPham> searchAndFilterSize(Pageable pageable, @Param("keyword") String keyword, @Param("size") String size);

    //search+color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(:color is null or spct.ms.id=:color)AND(:size is null or spct.size=:size)")
    Page<SanPham> searchAndFilterColorAndSize(Pageable pageable, @Param("keyword") String keyword, @Param("color") String color, @Param("size") String size);

    //search+priceMin+color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia >= :priceStart)AND(:color is null or spct.ms.id=:color)")
    Page<SanPham> searchAndPriceMinAndFilterColor(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart, @Param("color") String color);

    //search+priceMax+color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia <= :priceEnd)AND(:color is null or spct.ms.id=:color)")
    Page<SanPham> searchAndPriceMaxAndFilterColor(Pageable pageable, @Param("keyword") String keyword, @Param("priceEnd") String priceEnd, @Param("color") String color);

    //search+price+color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(:color is null or spct.ms.id=:color)")
    Page<SanPham> searchAndPriceAndFilterColor(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("color") String color);

    //search+priceMin+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia >= :priceStart)AND(:size is null or spct.size=:size)")
    Page<SanPham> searchAndPriceMinAndFilterSize(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart, @Param("size") String size);

    //search+priceMax+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia <= :priceEnd)AND(:size is null or spct.size=:size)")
    Page<SanPham> searchAndPriceMaxAndFilterSize(Pageable pageable, @Param("keyword") String keyword, @Param("priceEnd") String priceEnd, @Param("size") String size);

    //search+price+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(:size is null or spct.size=:size)")
    Page<SanPham> searchAndPriceAndFilterSize(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("size") String size);

    //search+priceMin+color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia >= :priceStart)AND(:color is null or spct.ms.id=:color)AND(:size is null or spct.size=:size)")
    Page<SanPham> searchAndPriceMinAndFilterColorAndSize(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart, @Param("color") String color, @Param("size") String size);

    //search+priceMax+color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia <= :priceEnd)AND(:color is null or spct.ms.id=:color)AND(:size is null or spct.size=:size)")
    Page<SanPham> searchAndPriceMaxAndFilterColorAndSize(Pageable pageable, @Param("keyword") String keyword, @Param("priceEnd") String priceEnd, @Param("color") String color, @Param("size") String size);

    //search+price+color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(:color is null or spct.ms.id=:color)AND(:size is null or spct.size=:size)")
    Page<SanPham> searchAndPriceAndFilterColorAndSize(Pageable pageable, @Param("keyword") String keyword, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("color") String color, @Param("size") String size);

    //priceMin
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia >= :priceStart)")
    Page<SanPham> priceMin(Pageable pageable, @Param("priceStart") String priceStart);

    //priceMax
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd)")
    Page<SanPham> priceMax(Pageable pageable, @Param("priceEnd") String priceEnd);

    //price
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd and sp.donGia >= :priceStart)")
    Page<SanPham> price(Pageable pageable, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd);

    //priceMin+color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia >= :priceStart)AND(:color is null or spct.ms.id=:color)")
    Page<SanPham> priceMinAndFilterColor(Pageable pageable, @Param("priceStart") String priceStart, @Param("color") String color);

    //priceMax+color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd)AND(:color is null or spct.ms.id=:color)")
    Page<SanPham> priceMaxAndFilterColor(Pageable pageable, @Param("priceEnd") String priceEnd, @Param("color") String color);

    //price+color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(:color is null or spct.ms.id=:color)")
    Page<SanPham> priceAndFilterColor(Pageable pageable, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("color") String color);

    //priceMin+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia >= :priceStart)AND(:size is null or spct.size=:size)")
    Page<SanPham> priceMinAndFilterSize(Pageable pageable, @Param("priceStart") String priceStart, @Param("size") String size);

    //priceMax+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd)AND(:size is null or spct.size=:size)")
    Page<SanPham> priceMaxAndFilterSize(Pageable pageable, @Param("priceEnd") String priceEnd, @Param("size") String size);

    //price+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(:size is null or spct.size=:size)")
    Page<SanPham> priceAndFilterSize(Pageable pageable, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("size") String size);

    //priceMin+color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia >= :priceStart)AND(:color is null or spct.ms.id=:color)AND(:size is null or spct.size=:size)")
    Page<SanPham> priceMinAndFilterColorAndSize(Pageable pageable, @Param("priceStart") String priceStart, @Param("color") String color, @Param("size") String size);

    //priceMax+color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd)AND(:color is null or spct.ms.id=:color)AND(:size is null or spct.size=:size)")
    Page<SanPham> priceMaxAndFilterColorAndSize(Pageable pageable, @Param("priceEnd") String priceEnd, @Param("color") String color, @Param("size") String size);

    //price+color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(:color is null or spct.ms.id=:color)AND(:size is null or spct.size=:size)")
    Page<SanPham> priceAndFilterColorAndSize(Pageable pageable, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("color") String color, @Param("size") String size);

    //color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:color is null or spct.ms.id=:color)")
    Page<SanPham> filterColor(Pageable pageable, @Param("color") String color);

    //size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:size is null or spct.size=:size)")
    Page<SanPham> filterSize(Pageable pageable, @Param("size") String size);

    //color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:color is null or spct.ms.id=:color)AND(:size is null or spct.size=:size)")
    Page<SanPham> filterColorAndSize(Pageable pageable, @Param("color") String color, @Param("size") String size);

    @Query("SELECT distinct sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (:keyword is null or sp.tenSP like %:keyword%)AND(:color is null or spct.ms.id in :color)AND(:size is null or spct.size in :size)")
    Page<SanPham> SearchfilterColorAndSizeIn(Pageable pageable, @Param("keyword") String keyword, @Param("color") List<String> color, @Param("size") List<String> size);

}
