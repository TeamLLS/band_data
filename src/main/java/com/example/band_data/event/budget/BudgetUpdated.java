package com.example.band_data.event.budget;

import com.example.band_data.event.Event;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BudgetUpdated extends Event {
    private String description;
    private Integer amount;


}
