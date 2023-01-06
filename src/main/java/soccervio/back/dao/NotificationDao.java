package soccervio.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soccervio.back.entities.Notification;

import java.util.List;

@Repository
public interface NotificationDao extends JpaRepository<Notification, Long> {

    List<Notification> findByUserTo_Id(long id);


    List<Notification> findByUserTo_IdAndDeliveredFalse(long id);

}
