package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.GioHang;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GioHangService {

    void save(GioHang gioHang);

    GioHang getById(String id);
    //Khánh
    Optional<GioHang> findByIdKH(Long id);
}
