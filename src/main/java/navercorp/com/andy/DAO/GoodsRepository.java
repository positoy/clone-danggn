package navercorp.com.andy.DAO;

import navercorp.com.andy.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, String> {
}
