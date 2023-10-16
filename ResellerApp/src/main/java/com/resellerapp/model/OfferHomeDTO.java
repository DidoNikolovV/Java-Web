package com.resellerapp.model;

import java.util.List;

public class OfferHomeDTO {

    private List<MyOfferDTO> myOffers;

    private List<BoughtOfferDTO> boughtOffers;

    private List<OtherOfferDTO> allOtherOffers;

    private long totalOtherOffers;

    public OfferHomeDTO(List<MyOfferDTO> myOffers, List<BoughtOfferDTO> boughtOffers, List<OtherOfferDTO> otherOffers) {
        this.myOffers = myOffers;
        this.boughtOffers = boughtOffers;
        this.allOtherOffers = otherOffers;
        this.totalOtherOffers = otherOffers.size();
    }

    public List<MyOfferDTO> getMyOffers() {
        return myOffers;
    }

    public void setMyOffers(List<MyOfferDTO> myOffers) {
        this.myOffers = myOffers;
    }

    public List<BoughtOfferDTO> getBoughtOffers() {
        return boughtOffers;
    }

    public void setBoughtOffers(List<BoughtOfferDTO> boughtOffers) {
        this.boughtOffers = boughtOffers;
    }

    public List<OtherOfferDTO> getAllOtherOffers() {
        return allOtherOffers;
    }

    public void setAllOtherOffers(List<OtherOfferDTO> allOtherOffers) {
        this.allOtherOffers = allOtherOffers;
    }

    public Long getTotalOtherOffers() {
        return totalOtherOffers;
    }

    public void setTotalOtherOffers(Long totalOtherOffers) {
        this.totalOtherOffers = totalOtherOffers;
    }
}
