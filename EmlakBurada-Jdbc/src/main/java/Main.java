import java.util.List;

import com.emlakburada.dao.AdvertResository;
import com.emlakburada.dao.UserRepository;
import com.emlakburada.entity.Advert;
import com.emlakburada.entity.User;

public class Main {

	private static AdvertResository advertResository = new AdvertResository();

	public static void main(String[] args) {

		// User
		UserRepository userRepository = new UserRepository();
		userRepository.save(prepareUser(1, "cem"));
		userRepository.save(prepareUser(2, "emir"));
		userRepository.save(prepareUser(3, "nehir"));
		userRepository.save(prepareUser(4, "melike"));
		userRepository.save(prepareUser(5, "serra"));

		System.out.println("--find all user--");

		List<User> users = userRepository.findAll();

		users.stream().forEach(user -> System.out.println(user.toString()));

		System.out.println("--find user--");

		User foundUser = userRepository.findOne(2);

		System.out.println(foundUser.toString());


		//Advert
		AdvertResository advertResository = new AdvertResository();
		advertResository.save(prepareAdvert(1, "Sahibinden Acil Satılık"));
		advertResository.save(prepareAdvert(2, "Dosta GİDERRR ACİLLL!!!"));
		advertResository.save(prepareAdvert(3, "Metroya Koşarak 5 dk"));
		advertResository.save(prepareAdvert(4, "Öğrenciye ve Bekar uygun"));

		System.out.println("--find all advert--");

		List<Advert> adverts = advertResository.findAll();

		adverts.stream().forEach(advert -> System.out.println(advert.toString()));

		System.out.println("--find advert--");

		Advert foundAdvert = advertResository.findOne(2);

		System.out.println(foundAdvert.toString());

	}

	private static User prepareUser(int id, String name) {
		User user = new User();
		user.id = id;
		user.name = name;
		user.email = "cemdrman@gmail.com";
		user.bio = "bio";
		return user;
	}

	private static Advert prepareAdvert(int advertNo, String baslik) {
		Advert advert = new Advert();
		advert.setAdvertNo(advertNo);
		advert.setBaslik(baslik);
		advert.setAktifMi(true);
		advert.setOlusturulmaTarihi(advertResository.now());

		return advert;
	}

}
