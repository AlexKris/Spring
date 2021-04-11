package com.spring.ioc.overview.domain;

import com.spring.ioc.overview.annotation.Vip;

@Vip
public class VipUsr extends Usr {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "VipUsr{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
