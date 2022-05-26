package com.example.lotto_game_mvp.utils;

public class WinNumberDto {
    SixNumDto numbers = new SixNumDto();
    int bNum;

    public void setNumbers(SixNumDto numbers) {
        this.numbers = numbers;
    }

    public void setbNum(int bNum) {
        this.bNum = bNum;
    }

    public WinNumberDto(SixNumDto sixNumDto, int bNum){
        this.numbers = sixNumDto;
        this.bNum = bNum;
    }

    public SixNumDto getSixNum() {
        return numbers;
    }

    public int getbNum() {
        return bNum;
    }

    public WinNumberDto() {
    }
}
