package com.example.band_data.data.club;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ClubData {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long clubId;
    @NotNull
    private Integer date;

    private Integer memberRegisterCount;
    private Integer memberLeftCount;
    private Integer memberBanCount;

    private Integer actCloseCount;
    private Integer actCancelCount;

    private Integer income;
    private Integer expense;

    public ClubData(Long clubId, Integer date) {
        this.clubId = clubId;
        this.date = date;

        this.memberRegisterCount = 0;
        this.memberLeftCount = 0;
        this.memberBanCount = 0;

        this.actCloseCount = 0;
        this.actCancelCount = 0;

        this.income = 0;
        this.expense = 0;
    }

    //테스트용
    public ClubData(Long id, Long clubId, Integer date, Integer memberRegisterCount, Integer memberLeftCount, Integer memberBanCount, Integer actCloseCount, Integer actCancelCount, Integer income, Integer expense) {
        this.id = id;
        this.clubId = clubId;
        this.date = date;
        this.memberRegisterCount = memberRegisterCount;
        this.memberLeftCount = memberLeftCount;
        this.memberBanCount = memberBanCount;
        this.actCloseCount = actCloseCount;
        this.actCancelCount = actCancelCount;
        this.income = income;
        this.expense = expense;
    }

    public void increaseMemRegister(){
        this.memberRegisterCount++;
    }

    public void increaseMemLeft(){
        this.memberLeftCount++;
    }

    public void increaseMemBan(){
        this.memberBanCount++;
    }


    public void increaseActClose(){
        this.actCloseCount++;
    }

    public void increaseActCancel(){
        this.actCancelCount++;
    }

    public void increaseIncome(int amount){
        this.income += amount;
    }

    public void increaseExpense(int amount){
        this.expense += amount;
    }
}
