package com.example.assignment3_mds569;

import java.util.List;

public interface Subscriber {

    //void modelChanged();

    //void iModelChanged();

    // The method any Subscriber must implement
    void getNotification(String chnl);

}