package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tours")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourID; // Auto-increment primary key

    @Column(name = "TourName", nullable = false)
    private String tourName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Location", nullable = false)
    private String location;

    @Column(name = "Duration", nullable = false)
    private Integer duration; // Duration in days

    @Column(name = "Price", nullable = false)
    private Double price; // Price of the tour

    @Column(name = "TourType", nullable = false)
    private String tourType; // Type of the tour (Adventure, Family, etc.)

    @Column(name = "AvailableSeats", nullable = false)
    private Integer availableSeats;

    @Column(name = "StartDate", nullable = false)
    private String startDate;

    @Column(name = "EndDate", nullable = false)
    private String endDate;

    @Column(name = "TourOperatorID", nullable = false)
    private UUID tourOperatorID; // UUID as a standard UUID object

    @Column(name = "Images")
    private String images;

    @Column(name = "created_at", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt; // Timestamp when the tour was created

    // Automatically set the created_at field before persisting the entity
    @PrePersist
    protected void onCreate() {
        createdAt = new java.util.Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TourOperatorID", referencedColumnName = "uid", insertable = false, updatable = false)
    private User tourOperator;

}
