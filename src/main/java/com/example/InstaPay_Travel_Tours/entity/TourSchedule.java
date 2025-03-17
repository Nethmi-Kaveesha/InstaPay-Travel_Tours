package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tour_schedule")
public class TourSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScheduleID")
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "tourid", nullable = false)
    private Tour tourid;

    @Column(name = "StartDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "MeetingPoint", length = 255)
    private String meetingPoint;

    @ManyToOne
    @JoinColumn(name = "GuideID")
    private TourGuide  guideid ;

    @Column(name = "CreatedAt", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // Constructors
    public TourSchedule() {
    }

    public TourSchedule(Tour tourid, LocalDateTime startDate, LocalDateTime endDate, String meetingPoint, TourGuide guide) {
        this.tourid = tourid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.meetingPoint = meetingPoint;
        this. guideid  = guide;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Tour getTour() {
        return tourid;
    }

    public void setTour(Tour tour) {
        this.tourid = tour;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public TourGuide getGuide() {
        return  guideid ;
    }

    public void setGuide(TourGuide guide) {
        this. guideid  = guide;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // No setter for createdAt since it should be set automatically

    @Override
    public String toString() {
        return "TourSchedule{" +
                "scheduleId=" + scheduleId +
                ", tour=" + (tourid != null ? tourid.getTourID() : "null") +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", meetingPoint='" + meetingPoint + '\'' +
                ", guide=" + ( guideid  != null ?  guideid .getGuideID() : "null") +
                ", createdAt=" + createdAt +
                '}';
    }
}
