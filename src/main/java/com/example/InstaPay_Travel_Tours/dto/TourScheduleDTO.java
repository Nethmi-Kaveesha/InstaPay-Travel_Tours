package com.example.InstaPay_Travel_Tours.dto;

import java.time.LocalDateTime;

public class TourScheduleDTO {
    private int scheduleId;
    private int tourid;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String meetingPoint;
    private Integer guideId; // Nullable field
    private LocalDateTime createdAt;

    public TourScheduleDTO() {
    }

    public TourScheduleDTO(int scheduleId, int tourid, LocalDateTime startDate, LocalDateTime endDate, String meetingPoint, Integer guideId, LocalDateTime createdAt) {
        this.scheduleId = scheduleId;
        this.tourid = tourid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.meetingPoint = meetingPoint;
        this.guideId = guideId;
        this.createdAt = createdAt;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getTourId() {
        return tourid;
    }

    public void setTourId(int tourId) {
        this.tourid = tourId;
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

    public Integer getGuideId() {
        return guideId;
    }

    public void setGuideId(Integer guideId) {
        this.guideId = guideId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TourScheduleDTO{" +
                "scheduleId=" + scheduleId +
                ", tourId=" + tourid +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", meetingPoint='" + meetingPoint + '\'' +
                ", guideId=" + guideId +
                ", createdAt=" + createdAt +
                '}';
    }
}
