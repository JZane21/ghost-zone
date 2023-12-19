package com.ghostzone.history_service.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="History")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long historyId;
    @Column(name="SONG_ID")
    private long songId;
    @Column(name="USER_ID")
    private long userId;
}
