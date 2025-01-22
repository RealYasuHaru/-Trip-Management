package com.example.repository;

import com.example.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    // 根据用户名查找用户的所有行程
    List<Trip> findByUsername(String username);

    // 计算特定用户的行程数
    Long countByUsername(String username);


    // 找到特定用户的最长行程（按持续时间）
    @Query("SELECT t FROM Trip t WHERE t.username = :username ORDER BY DATEDIFF(t.endDate, t.startDate) DESC")
    Trip findTopByOrderByDurationDesc(@Param("username") String username);
    // 根据行程ID计算行程持续时间（天数差）
    @Query("SELECT DATEDIFF(t.endDate, t.startDate) FROM Trip t WHERE t.id = :tripId")
    Integer getDurationById(@Param("tripId") Long tripId);
    // 找到特定用户最常访问的目的地
    @Query(value = "SELECT destination FROM Trip WHERE username = :username GROUP BY destination ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    String findMostVisitedDestination(@Param("username") String username);
    List<Trip> findByUsernameAndDestinationContainingIgnoreCase(String username, String destination);

}