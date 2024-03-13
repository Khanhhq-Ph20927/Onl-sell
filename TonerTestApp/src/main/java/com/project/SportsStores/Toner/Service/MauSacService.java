package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSac> getAll();
    MauSac getById(Integer id);
}
