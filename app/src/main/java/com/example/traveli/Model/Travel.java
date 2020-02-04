package com.example.traveli.Model;

public class Travel {

    private String name;
    private String departureDate;
    private String endDate;

    public Travel(String name, String departureDate, String endDate) {
        this.name = name;
        this.departureDate = departureDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
