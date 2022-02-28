package emlakburada.service;

import java.util.*;
import java.util.stream.Collectors;

import emlakburada.dto.MessageRequest;
import emlakburada.dto.response.MessageResponse;
import emlakburada.model.Message;
import emlakburada.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.client.BannerClient;
import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.model.Advert;
import emlakburada.model.User;
import emlakburada.queue.QueueService;
import emlakburada.repository.AdvertRepository;
import emlakburada.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdvertService extends AdvertBaseService {

	@Autowired
	private AdvertRepository advertRepository;

	@Autowired
	private QueueService queueService;

	@Autowired
	private BannerClient bannerClient;

	public List<AdvertResponse> getAllAdvert() {
		List<Advert> adverts = advertRepository.findAll();
		return adverts.stream().map(advert -> convertToAdvertResponse(advert)).collect(Collectors.toList());
	}

	public void saveAdvert(AdvertRequest advertRequest) {
		advertRepository.save(convertToAdvertEntity(advertRequest));
		EmailMessage emailMessage = new EmailMessage("enesdeniz@gmail.com", advertRequest.getUserId());
		queueService.sendMessage(emailMessage);
		bannerClient.saveBanner();
	}

	public AdvertResponse getAdvertByAdvertNo(int advertNo) {
		Advert advert = advertRepository.getById(advertNo);
		return convertToAdvertResponse(advert);
	}


	public AdvertResponse convertToAdvertResponse(Advert savedAdvert) {
		AdvertResponse response = new AdvertResponse();
		response.setBaslik(savedAdvert.getBaslik());
		response.setFiyat(savedAdvert.getFiyat());
		response.setAdvertNo(savedAdvert.getAdvertNo());
		return response;
	}

}
