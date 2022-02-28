package emlakburada.service;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.UserRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.Advert;
import emlakburada.model.RealEstate;
import emlakburada.model.User;

public class AdvertBaseService {

    protected AdvertResponse convertToAdvertResponse(Advert savedAdvert) {
        AdvertResponse response = new AdvertResponse();
        response.setBaslik(savedAdvert.getBaslik());
        response.setFiyat(savedAdvert.getFiyat());
        response.setAdvertNo(savedAdvert.getAdvertNo());
        return response;
    }

    protected Advert convertToAdvertEntity(AdvertRequest request) {
        Advert advert = new Advert();
        advert.setBaslik(request.getBaslik());
        advert.setFiyat(request.getFiyat());
        return advert;
    }
}
