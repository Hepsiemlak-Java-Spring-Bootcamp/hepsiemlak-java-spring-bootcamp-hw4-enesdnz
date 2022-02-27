package emlakburada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import emlakburada.repository.BannerRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.dto.request.BannerRequest;
import emlakburada.dto.response.BannerResponse;
import emlakburada.model.Banner;

@Service
public class BannerService extends BannerBaseService{

	private BannerRepostitory repository;

	public List<BannerResponse> getAllBanners() {
		List<Banner> banners = repository.findAll();
		return banners.stream().map(banner -> convertToBannerResponse(banner)).collect(Collectors.toList());
	}

	public void saveBanner(BannerRequest bannerRequest) {
		repository.save(convertToBannerEntity(bannerRequest));
	}

	public BannerResponse convertToBannerResponse(Banner banner) {
		BannerResponse response = new BannerResponse();
		response.setAdvertNo(banner.getAdvertNo());
		response.setPhone(banner.getPhone());
		response.setTotal(banner.getTotal());
		return response;
	}

	public Banner convertToBannerEntity(BannerRequest request) {
		Banner banner = new Banner();
		banner.setAdvertNo(request.getAdvertNo());
		banner.setPhone(request.getPhone());
		banner.setTotal(request.getTotal());
		return banner;
	}


}
