package guru.springframework.beerservice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
	private UUID id;

	@Version    // Gives us optimistic locking
	private Long version;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate;

	@UpdateTimestamp
	@Column(updatable = false)
	private Timestamp lastModifiedDate;

	private String beerName;
	private String beerStyle;

	@Column(unique = true)
	private Long upc;

	private BigDecimal price;
	private Integer minOnHand;
	private Integer quantityToBrew;
}
