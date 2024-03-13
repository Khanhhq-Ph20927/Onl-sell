package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Repository.SanPhamRepository;
import com.project.SportsStores.Toner.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {


    @Autowired
    private SanPhamRepository rp;

    @Override
    public List<SanPham> getAll() {
        return rp.findAll();
    }

    @Override
    public Page<SanPham> getPagination(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    public Page<SanPham> searchAndfilterByCollection(String keyword, Pageable pageable, String collection) {
        return rp.searchAndFilterProductByCollection(keyword, pageable, collection);
    }

    @Override
    public Page<SanPham> searchAndfilterByStatus(String keyword, Pageable pageable, String status) {
        return rp.searchAndFilterProductByStatus(keyword, pageable, status);
    }

    @Override
    public Page<SanPham> searchAndfilterByStatusAndCollection(String keyword, Pageable pageable, String status, String collection) {
        return rp.searchAndFilterProductByStatusAndCollection(pageable, keyword, status, collection);
    }

    @Override
    public Page<SanPham> filterByPrice(String keyword, Pageable pageable, String priceStart, String priceEnd) {
        return rp.searchAndFilterProductByPrice(keyword, pageable, priceStart, priceEnd);
    }

    @Override
    public Page<SanPham> filterByStatusAndPrice(String keyword, Pageable pageable, String status, String priceStart, String priceEnd) {
        return rp.searchAndFilterProductByStatusAndPrice(keyword, pageable, status, priceStart, priceEnd);
    }

    @Override
    public Page<SanPham> filterByCollectionAndPrice(String keyword, Pageable pageable, String collection, String priceStart, String priceEnd) {
        return rp.searchAndFilterProductByCollectionAndPrice(keyword, pageable, collection, priceStart, priceEnd);
    }

    @Override
    public Page<SanPham> filterByAll(String keyword, Pageable pageable, String status, String collection, String priceStart, String priceEnd) {
        return rp.searchAndFilterProductByAll(keyword, pageable, status, collection, priceStart, priceEnd);
    }

    @Override
    public Page<SanPham> pageClient(Pageable pageable) {
        return rp.pageClient(pageable);

    }

    @Override
    public Page<SanPham> search(Pageable pageable, String keyword) {
        return rp.search(pageable, keyword);
    }

    @Override
    public Page<SanPham> searchAndPriceMin(Pageable pageable, String keyword, String priceStart) {
        return rp.searchAndPriceMin(pageable, keyword, priceStart);
    }

    @Override
    public Page<SanPham> searchAndPriceMax(Pageable pageable, String keyword, String priceEnd) {
        return rp.searchAndPriceMax(pageable, keyword, priceEnd);
    }

    @Override
    public Page<SanPham> searchAndPrice(Pageable pageable, String keyword, String priceStart, String priceEnd) {
        return rp.searchAndPrice(pageable, keyword, priceStart, priceEnd);
    }

    @Override
    public Page<SanPham> searchAndFilterColor(Pageable pageable, String keyword,  List<String> color) {
        List<Page<SanPham>> pages = new ArrayList<>();

        // Duyệt qua mỗi màu sắc và gọi rp.filterColor() cho mỗi màu
        for (String a : color) {
            Page<SanPham> page = rp.searchAndFilterColor(pageable, keyword,a);
            pages.add(page);
        }
        // Kết hợp các trang từ danh sách thành một trang duy nhất
        List<SanPham> combinedResults = new ArrayList<>();
        for (Page<SanPham> page : pages) {
            combinedResults.addAll(page.getContent());
        }

        return new PageImpl<>(combinedResults, pageable,combinedResults.size());
    }

    @Override
    public Page<SanPham> searchAndFilterSize(Pageable pageable, String keyword,  List<String> size) {
        List<Page<SanPham>> pages = new ArrayList<>();

        // Duyệt qua mỗi màu sắc và gọi rp.filterColor() cho mỗi màu
        for (String a : size) {
            Page<SanPham> page = rp.searchAndFilterSize(pageable, keyword,a);
            pages.add(page);
        }
        // Kết hợp các trang từ danh sách thành một trang duy nhất
        List<SanPham> combinedResults = new ArrayList<>();
        for (Page<SanPham> page : pages) {
            combinedResults.addAll(page.getContent());
        }
        return new PageImpl<>(combinedResults, pageable,combinedResults.size());
    }

    @Override
    public Page<SanPham> searchAndFilterColorAndSize(Pageable pageable, String keyword,  List<String> color,  List<String> size) {
        List<Page<SanPham>> pages = new ArrayList<>();
        // Duyệt qua mỗi màu sắc và gọi rp.filterColor() cho mỗi màu
        for (String a : color) {
            for (String b : size) {
                Page<SanPham> page = rp.searchAndFilterColorAndSize(pageable, keyword,a,b);
                pages.add(page);
            }
        }
        // Kết hợp các trang từ danh sách thành một trang duy nhất
        List<SanPham> combinedResults = new ArrayList<>();
        for (Page<SanPham> page : pages) {
            combinedResults.addAll(page.getContent());
        }
        return new PageImpl<>(combinedResults, pageable,combinedResults.size());
    }

    @Override
    public Page<SanPham> searchAndPriceMinAndFilterColor(Pageable pageable, String keyword, String priceStart, String color) {
        return rp.searchAndPriceMinAndFilterColor(pageable, keyword, priceStart, color);
    }

    @Override
    public Page<SanPham> searchAndPriceMaxAndFilterColor(Pageable pageable, String keyword, String priceEnd, String color) {
        return null;
    }

    @Override
    public Page<SanPham> searchAndPriceAndFilterColor(Pageable pageable, String keyword, String priceStart, String priceEnd, String color) {
        return rp.searchAndPriceAndFilterColor(pageable, keyword, priceStart, priceEnd, color);
    }

    @Override
    public Page<SanPham> searchAndPriceMinAndFilterSize(Pageable pageable, String keyword, String priceStart, String size) {
        return rp.searchAndPriceMinAndFilterSize(pageable, keyword, priceStart, size);
    }

    @Override
    public Page<SanPham> searchAndPriceMaxAndFilterSize(Pageable pageable, String keyword, String priceEnd, String size) {
        return rp.searchAndPriceMaxAndFilterSize(pageable, keyword, priceEnd, size);
    }

    @Override
    public Page<SanPham> searchAndPriceAndFilterSize(Pageable pageable, String keyword, String priceStart, String priceEnd, String size) {
        return rp.searchAndPriceAndFilterSize(pageable, keyword, priceStart, priceEnd, size);
    }

    @Override
    public Page<SanPham> searchAndPriceMinAndFilterColorAndSize(Pageable pageable, String keyword, String priceStart, String color, String size) {
        return rp.searchAndPriceMinAndFilterColorAndSize(pageable, keyword, priceStart, color, size);
    }

    @Override
    public Page<SanPham> searchAndPriceMaxAndFilterColorAndSize(Pageable pageable, String keyword, String priceEnd, String color, String size) {
        return rp.searchAndPriceMaxAndFilterColorAndSize(pageable, keyword, priceEnd, color, size);
    }

    @Override
    public Page<SanPham> searchAndPriceAndFilterColorAndSize(Pageable pageable, String keyword, String priceStart, String priceEnd, String color, String size) {
        return rp.searchAndPriceAndFilterColorAndSize(pageable, keyword, priceStart, priceEnd, color, size);
    }

    @Override
    public Page<SanPham> priceMin(Pageable pageable, String priceStart) {
        return rp.priceMin(pageable, priceStart);
    }

    @Override
    public Page<SanPham> priceMax(Pageable pageable, String priceEnd) {
        return rp.priceMax(pageable, priceEnd);
    }

    @Override
    public Page<SanPham> price(Pageable pageable, String priceStart, String priceEnd) {
        return rp.price(pageable, priceStart, priceEnd);
    }

    @Override
    public Page<SanPham> priceMinAndFilterColor(Pageable pageable, String priceStart, String color) {
        return rp.priceMinAndFilterColor(pageable, priceStart, color);
    }

    @Override
    public Page<SanPham> priceMaxAndFilterColor(Pageable pageable, String priceEnd, String color) {
        return rp.priceMaxAndFilterColor(pageable, priceEnd, color);
    }

    @Override
    public Page<SanPham> priceAndFilterColor(Pageable pageable, String priceStart, String priceEnd, String color) {
        return rp.priceAndFilterColor(pageable, priceStart, priceEnd, color);
    }

    @Override
    public Page<SanPham> priceMinAndFilterSize(Pageable pageable, String priceStart, String size) {
        return rp.priceMinAndFilterSize(pageable, priceStart, size);
    }

    @Override
    public Page<SanPham> priceMaxAndFilterSize(Pageable pageable, String priceEnd, String size) {
        return rp.priceMaxAndFilterSize(pageable, priceEnd, size);
    }

    @Override
    public Page<SanPham> priceAndFilterSize(Pageable pageable, String priceStart, String priceEnd, String size) {
        return rp.priceAndFilterSize(pageable, priceStart, priceEnd, size);
    }

    @Override
    public Page<SanPham> priceMinAndFilterColorAndSize(Pageable pageable, String priceStart, String color, String size) {
        return rp.priceMinAndFilterColorAndSize(pageable, priceStart, color, size);
    }

    @Override
    public Page<SanPham> priceMaxAndFilterColorAndSize(Pageable pageable, String priceEnd, String color, String size) {
        return rp.priceMaxAndFilterColorAndSize(pageable, priceEnd, color, size);
    }

    @Override
    public Page<SanPham> priceAndFilterColorAndSize(Pageable pageable, String priceStart, String priceEnd, String color, String size) {
        return rp.priceAndFilterColorAndSize(pageable, priceStart, priceEnd, color, size);
    }

    @Override
    public Page<SanPham> filterColor(Pageable pageable, List<String> color) {
        List<Page<SanPham>> pages = new ArrayList<>();

        // Duyệt qua mỗi màu sắc và gọi rp.filterColor() cho mỗi màu
        for (String a : color) {
            Page<SanPham> page = rp.filterColor(pageable, a);
            pages.add(page);
        }
        // Kết hợp các trang từ danh sách thành một trang duy nhất
        List<SanPham> combinedResults = new ArrayList<>();
        for (Page<SanPham> page : pages) {
            combinedResults.addAll(page.getContent());
        }

        return new PageImpl<>(combinedResults, pageable, combinedResults.size());
    }

    @Override
    public Page<SanPham> filterSize(Pageable pageable, List<String> size) {
        List<Page<SanPham>> pages = new ArrayList<>();

        // Duyệt qua mỗi màu sắc và gọi rp.filterColor() cho mỗi màu
        for (String a : size) {
            Page<SanPham> page = rp.filterSize(pageable, a);
            pages.add(page);
        }
        // Kết hợp các trang từ danh sách thành một trang duy nhất
        List<SanPham> combinedResults = new ArrayList<>();
        for (Page<SanPham> page : pages) {
            combinedResults.addAll(page.getContent());
        }

        return new PageImpl<>(combinedResults, pageable, combinedResults.size());
    }

    @Override
    public Page<SanPham> filterColorAndSize(Pageable pageable, List<String> color, List<String> size) {
        List<Page<SanPham>> pages = new ArrayList<>();
        // Duyệt qua mỗi màu sắc và gọi rp.filterColor() cho mỗi màu
        for (String a : color) {
            Page<SanPham> page = rp.filterColor(pageable, a);
            pages.add(page);
        }
        for (String b : size) {
            Page<SanPham> page = rp.filterSize(pageable, b);
            pages.add(page);
        }
        // Kết hợp các trang từ danh sách thành một trang duy nhất
        List<SanPham> combinedResults = new ArrayList<>();
        for (Page<SanPham> page : pages) {
            combinedResults.addAll(page.getContent());
        }

        return new PageImpl<>(combinedResults, pageable, combinedResults.size());
    }

    @Override
    public Page<SanPham> searchfilterColorAndSizeIn(Pageable pageable, String keyword, List<String> color, List<String> size) {
        return rp.SearchfilterColorAndSizeIn(pageable,keyword,color,size);
    }

    @Override
    public Optional<SanPham> getById(Long id) {
        return rp.findById(id);
    }

    @Override
    public boolean save(SanPham sp) {
        rp.saveAndFlush(sp);
        return true;
    }

    @Override
    public boolean update(SanPham sp) {
        rp.saveAndFlush(sp);
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        rp.deleteById(id);
        return true;
    }

    @Override
    public Page<SanPham> seacrhProduct(String keyword, Pageable pageable) {
        return rp.searchProduct(keyword, pageable);
    }

    @Override
    public Page<SanPham> filterByStatusNoSearch(Pageable pageable, String status) {
        return rp.filterProductByStatus(pageable, status);
    }

    @Override
    public Page<SanPham> filterByCollectionNoSearch(Pageable pageable, String collection) {
        return rp.filterProductByCollection(pageable, collection);
    }

    @Override
    public Page<SanPham> filterByStatusAndCollectionNoSearch(Pageable pageable, String status, String collection) {
        return rp.filterProductByStatusAndCollection(pageable, status, collection);
    }
}
