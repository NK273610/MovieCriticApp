package com.dalhousie.moviecritic.Data;

public class TheatreFactory {




    public static PasswordSalt getPasswordObject(String hashPassword, String salt){

    return new PasswordSalt(hashPassword,salt);

    }

    public  static  SlotTime getSlotTimeObject(){

        return new SlotTime();

    }


    public static Theatre getTheatreObject(){

        return new Theatre();
    }


}
