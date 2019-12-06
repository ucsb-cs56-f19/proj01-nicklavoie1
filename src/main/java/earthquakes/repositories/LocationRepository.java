package earthquakes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import earthquakes.entities.*;
import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

   List<Location> findByPlaceId(String placeId);
   List<Location> findByUid(String uid);
}
