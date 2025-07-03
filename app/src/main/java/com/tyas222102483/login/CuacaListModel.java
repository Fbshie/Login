package com.tyas222102483.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuacaListModel {
    @SerializedName("main")
    private CuacaMainModel mainModel;
    @SerializedName("weather")
    private List<CuacaWeatherModel> weatherModelList;
    private String dt_txt;

    public CuacaListModel() {}

    public CuacaMainModel getMainModel() { return mainModel; }

    public void setMainModel(CuacaMainModel mainModel) { this.mainModel = mainModel; }

    public List<CuacaWeatherModel> getWeatherModelList() { return weatherModelList; }

    public void setWeatherModelList(List<CuacaWeatherModel> weatherModelList) {
        this.weatherModelList = weatherModelList;
    }

    public String getDt_txt() { return dt_txt; }
}
