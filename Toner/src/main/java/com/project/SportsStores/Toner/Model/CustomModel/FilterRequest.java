package com.project.SportsStores.Toner.Model.CustomModel;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class FilterRequest {
    private List<String> listColors;
    private List<String> listSizes;
}
