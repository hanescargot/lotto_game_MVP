package com.example.lotto_game_mvp.utils;

import java.util.ArrayList;

public class WinNumberDto {
    ArrayList<Integer> numbers = new ArrayList<>();
    int bNum;

    public void setNumbers(ArrayList<Integer> sixNum) {
        this.numbers = sixNum;
    }

    public void setbNum(int bNum) {
        this.bNum = bNum;
    }

    public WinNumberDto(ArrayList<Integer> sixNumDto, int bNum){
        this.numbers = sixNumDto;
        this.bNum = bNum;
    }

    public ArrayList<Integer> getSixNum() {
        return numbers;
    }

    public int getbNum() {
        return bNum;
    }

    public WinNumberDto() {
    }
}
