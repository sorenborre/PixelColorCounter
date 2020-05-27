package com.example.pixelcolorcounter.Presenters;

import com.example.pixelcolorcounter.Models.PixelColorCounter;

public class MainActivityPresenter {

    PixelColorCounterView view;
    PixelColorCounter pixelColorCounter;

    public MainActivityPresenter(PixelColorCounterView view) {

        pixelColorCounter = new PixelColorCounter();

        this.view = view;
    }
}
