package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.MauSac;
import com.project.SportsStores.Toner.Repository.MauSacRepository;
import com.project.SportsStores.Toner.Service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository rp;

    @Override
    public List<MauSac> getAll() {
        return rp.findAll();
    }

    @Override
    public MauSac getById(Integer id) {
        if (rp.findById(id).isPresent()) {
            return rp.findById(id).get();
        } else {
            return null;
        }
    }
}
