package emlakburada.model;

import emlakburada.model.enums.KonutType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RealEstate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToOne
	private Address adres;
	@Enumerated(EnumType.STRING)
	private KonutType konutType;
	private String odaSayisi = "3+1";
	private int alan = 145;
	private int bulunduguKat = 3;

}