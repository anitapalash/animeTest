package com.koposovo.animeTest.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import org.springframework.stereotype.Controller;

@Controller
public class AnaliserInterfaceController extends UserInterfaceController {
    @FXML
    private Tab editTestsTab;

    @FXML
    private Tab statisticsTab;
}
