package com.example.campus_connect.Service;

import com.example.campus_connect.Model.OrderStatus;
import com.example.campus_connect.Model.ServiceOrder;
import com.example.campus_connect.Model.ServiceReview;
import com.example.campus_connect.Repository.ServiceOrderRepository;
import com.example.campus_connect.Repository.ServiceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceReviewService {
    @Autowired
    private ServiceReviewRepository repo;
    @Autowired
    private ServiceOrderRepository orderRepo;

    public String save(UUID serviceOrderId, ServiceReview review) {
        Optional<ServiceOrder> found = orderRepo.findById(serviceOrderId);
        if (found.isEmpty()) {
            return "The service order with that id does not exist";
        }
        ServiceOrder order = found.get();
        if (order.getStatus() != OrderStatus.DELIVERED) {
            return "Service reviews are only for DELIVERED service orders";
        }
        review.setServiceOrder(order);
        review.setReviewDate(LocalDate.now());
        repo.save(review);
        return "Service review saved successfully";
    }

    public List<ServiceReview> getAll() {
        return repo.findAll();
    }

    public List<ServiceReview> getByStudent(String studentNumber) {
        return repo.historyOfStudent(studentNumber);
    }

    public String delete(UUID id) {
        if (!repo.existsById(id)) {
            return "The service review with that id does not exist";
        }
        repo.deleteById(id);
        return "Service review deleted successfully";
    }
}