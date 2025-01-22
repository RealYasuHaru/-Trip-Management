package com.example.controller;

import com.example.model.Trip;
import com.example.model.User;
import com.example.repository.TripRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trip")
@CrossOrigin(origins = "http://127.0.0.1")
public class TripController {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Autowired
    public TripController(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/search/{username}")
    public List<Trip> searchTrips(@PathVariable("username") String username, @RequestParam("destination") String destination) {
        return tripRepository.findByUsernameAndDestinationContainingIgnoreCase(username, destination);
    }
    // 添加行程
    @PostMapping("/add")
    public ResponseEntity<?> addTrip(@RequestBody Trip trip) {
        try {
            tripRepository.save(trip);
            return ResponseEntity.ok("Trip added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add trip");
        }
    }

    // 更新行程
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable Long id, @RequestBody Trip updatedTrip) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (!tripOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        try {
            Trip existingTrip = tripOptional.get();
            existingTrip.setDestination(updatedTrip.getDestination());
            existingTrip.setStartDate(updatedTrip.getStartDate());
            existingTrip.setEndDate(updatedTrip.getEndDate());
            existingTrip.setDescription(updatedTrip.getDescription());

            tripRepository.save(existingTrip);

            return ResponseEntity.ok("Trip updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update trip");
        }
    }

    public Trip getById(Long tripId) {
        return tripRepository.findById(tripId).orElse(null);
    }
    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long tripId) {

        Trip trip = getById(tripId);
        if (trip != null) {
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 删除行程
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (!tripOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        try {
            tripRepository.deleteById(id);
            return ResponseEntity.ok("Trip deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete trip");
        }
    }

    // 获取用户的所有行程
    @GetMapping("/list")
    public ResponseEntity<?> getUserTrips(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with username: " + username);
        }

        try {
            List<Trip> trips = tripRepository.findByUsername(username);
            return ResponseEntity.ok(trips);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch trips");
        }
    }

    // 获取行程统计信息
    @GetMapping("/statistics")
    public ResponseEntity<?> getTripStatistics(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with username: " + username);
        }

        try {
            List<Trip> trips = tripRepository.findByUsername(username);
            if (trips.isEmpty()) {
                return ResponseEntity.ok("No trips found for user: " + username);
            }

            int totalTrips = trips.size();
            double averageDuration = calculateAverageDuration(trips);
            Trip longestTrip = findLongestTrip(trips);
            String mostVisitedDestination = findMostVisitedDestination(trips);

            TripStatistics statistics = new TripStatistics(totalTrips, averageDuration, longestTrip, mostVisitedDestination);
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch trip statistics");
        }
    }

    // 计算平均行程持续时间
    private double calculateAverageDuration(List<Trip> trips) {
        int totalDuration = 0;
        for (Trip trip : trips) {
            totalDuration += getDurationById(trip.getId());
        }
        return (double) totalDuration / trips.size();
    }

    // 找到持续时间最长的行程
    private Trip findLongestTrip(List<Trip> trips) {
        Trip longestTrip = null;
        int maxDuration = 0;
        for (Trip trip : trips) {
            int duration = getDurationById(trip.getId());
            if (duration > maxDuration) {
                maxDuration = duration;
                longestTrip = trip;
            }
        }
        return longestTrip;
    }

    // 找到最常访问的目的地
    private String findMostVisitedDestination(List<Trip> trips) {
        // 简单示例：假设每个行程都有唯一的目的地
        // 实际应用中可能需要更复杂的逻辑
        String mostVisitedDestination = null;
        int maxCount = 0;

        for (Trip trip : trips) {
            int count = 0;
            for (Trip t : trips) {
                if (t.getDestination().equals(trip.getDestination())) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostVisitedDestination = trip.getDestination();
            }
        }

        return mostVisitedDestination != null ? mostVisitedDestination : "N/A";
    }

    // 获取行程持续时间
    private int getDurationById(Long tripId) {
        // 此处假设有一个可以根据 tripId 查询行程持续时间的方法
        // 实际情况根据数据库和数据结构进行实现
        return tripRepository.getDurationById(tripId);
    }

    // 内部类：用于封装行程统计信息
    private static class TripStatistics {
        private int totalTrips;
        private double averageDuration;
        private Trip longestTrip;
        private String mostVisitedDestination;

        public TripStatistics(int totalTrips, double averageDuration, Trip longestTrip, String mostVisitedDestination) {
            this.totalTrips = totalTrips;
            this.averageDuration = averageDuration;
            this.longestTrip = longestTrip;
            this.mostVisitedDestination = mostVisitedDestination;
        }

        public int getTotalTrips() {
            return totalTrips;
        }

        public void setTotalTrips(int totalTrips) {
            this.totalTrips = totalTrips;
        }

        public double getAverageDuration() {
            return averageDuration;
        }

        public void setAverageDuration(double averageDuration) {
            this.averageDuration = averageDuration;
        }

        public Trip getLongestTrip() {
            return longestTrip;
        }

        public void setLongestTrip(Trip longestTrip) {
            this.longestTrip = longestTrip;
        }

        public String getMostVisitedDestination() {
            return mostVisitedDestination;
        }

        public void setMostVisitedDestination(String mostVisitedDestination) {
            this.mostVisitedDestination = mostVisitedDestination;
        }
    }

}
